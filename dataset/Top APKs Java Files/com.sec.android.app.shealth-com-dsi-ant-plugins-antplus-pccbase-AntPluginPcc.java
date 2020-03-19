package com.dsi.ant.plugins.antplus.pccbase;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.dsi.ant.plugins.antplus.pcc.defines.DeviceState;
import com.dsi.ant.plugins.antplus.pcc.defines.RequestAccessResult;
import com.dsi.ant.plugins.internal.pluginsipc.AntPluginDeviceDbProvider.DeviceDbDeviceInfo;
import com.dsi.ant.plugins.utility.log.LogAnt;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AntPluginPcc
{
  private static final String TAG = "AntPluginPcc";
  static volatile String lastMissingDependencyName = "";
  static volatile String lastMissingDependencyPkgName = "";
  AntPluginDeviceDbProvider.DeviceDbDeviceInfo deviceInfo;
  CountDownLatch deviceInitializedLatch = new CountDownLatch(1);
  boolean isInitialized = false;
  private boolean isReleased = false;
  UUID mAccessToken;
  Integer mCachedState = null;
  private Thread mCurrentCmdThread;
  private boolean mIsPluginServiceBound = false;
  Context mOwnerContext;
  private final ReentrantLock mPluginCommLock = new ReentrantLock();
  Exchanger<Message> mPluginCommMsgExch = new Exchanger();
  CyclicBarrier mPluginCommProcessingBarrier = new CyclicBarrier(2);
  volatile Handler mPluginEventHandler;
  Handler.Callback mPluginEventHandlerCb = new Handler.Callback()
  {
    public final boolean handleMessage(Message paramAnonymousMessage)
    {
      Object localObject2 = AntPluginPcc.this.mReleaseHandle;
      if (localObject2 == null) {
        return true;
      }
      try
      {
        AntPluginPcc.this.deviceInitializedLatch.await();
        synchronized (((PccReleaseHandle)localObject2).stateLock)
        {
          if (!((PccReleaseHandle)localObject2).isActive()) {
            return true;
          }
          int i = paramAnonymousMessage.what;
          if (i != 1)
          {
            if (i != 3)
            {
              localObject2 = AntPluginPcc.TAG;
              StringBuilder localStringBuilder = new StringBuilder("Unrecognized plugin event received: ");
              localStringBuilder.append(paramAnonymousMessage.arg1);
              LogAnt.w((String)localObject2, localStringBuilder.toString());
            }
            else
            {
              i = paramAnonymousMessage.arg1;
              AntPluginPcc.this.mCachedState = Integer.valueOf(i);
              paramAnonymousMessage = AntPluginPcc.TAG;
              localObject2 = new StringBuilder("State event: ");
              ((StringBuilder)localObject2).append(i);
              LogAnt.v(paramAnonymousMessage, ((StringBuilder)localObject2).toString());
              if (i == -100) {
                AntPluginPcc.this.handleConnectionBroke("Device dead");
              } else {
                AntPluginPcc.this.mStateChangeReceiver.onDeviceStateChange(DeviceState.getValueFromInt(i));
              }
            }
          }
          else {
            AntPluginPcc.this.handlePluginEvent(paramAnonymousMessage);
          }
          return true;
        }
      }
      catch (InterruptedException paramAnonymousMessage)
      {
        for (;;) {}
      }
      LogAnt.i(AntPluginPcc.TAG, "Plugin event thread interrupted while waiting for initialization to complete.");
      Thread.currentThread().interrupt();
      return true;
    }
  };
  HandlerThread mPluginEventHandlerThread = new HandlerThread("PluginPCCEventHandler");
  volatile Handler mPluginMsgHandler;
  Handler.Callback mPluginMsgHandlerCb = new Handler.Callback()
  {
    public final boolean handleMessage(Message paramAnonymousMessage)
    {
      Object localObject = AntPluginPcc.TAG;
      StringBuilder localStringBuilder = new StringBuilder("Plugin Msg Handler received: ");
      localStringBuilder.append(paramAnonymousMessage.what);
      localStringBuilder.append(", ");
      localStringBuilder.append(paramAnonymousMessage.arg1);
      LogAnt.v((String)localObject, localStringBuilder.toString());
      if (AntPluginPcc.this.mPluginCommLock.tryLock()) {
        try
        {
          AntPluginPcc.this.handleNonCmdPluginMessage(paramAnonymousMessage);
          return true;
        }
        finally
        {
          AntPluginPcc.this.mPluginCommLock.unlock();
        }
      }
      try
      {
        AntPluginPcc.this.mPluginCommMsgExch.exchange(paramAnonymousMessage);
        AntPluginPcc.this.mPluginCommProcessingBarrier.await();
        return true;
      }
      catch (BrokenBarrierException localBrokenBarrierException)
      {
        for (;;) {}
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;) {}
      }
      localObject = AntPluginPcc.this;
      localStringBuilder = new StringBuilder("InterruptedException in mPluginMsgHandler trying to fwd message ");
      localStringBuilder.append(paramAnonymousMessage.what);
      ((AntPluginPcc)localObject).handleConnectionBroke(localStringBuilder.toString());
      Thread.currentThread().interrupt();
      return true;
      localObject = AntPluginPcc.this;
      localStringBuilder = new StringBuilder("BrokenBarrierException in mPluginMsgHandler trying to fwd message ");
      localStringBuilder.append(paramAnonymousMessage.what);
      ((AntPluginPcc)localObject).handleConnectionBroke(localStringBuilder.toString());
      return true;
    }
  };
  HandlerThread mPluginMsgHandlerThread = new HandlerThread("PluginPCCMsgHandler");
  Messenger mPluginMsgr;
  private Object mPluginServiceBindChange_LOCK = new Object();
  protected volatile PccReleaseHandle<?> mReleaseHandle;
  private final Object mReleaseLock = new Object();
  Messenger mReqAccessMessenger;
  protected IDeviceStateChangeReceiver mStateChangeReceiver;
  protected int reportedServiceVersion;
  ServiceConnection serviceBindConn;
  protected boolean supportsRssiEvent;
  
  public AntPluginPcc()
  {
    this.mPluginEventHandlerThread.start();
    this.mPluginEventHandler = new Handler(this.mPluginEventHandlerThread.getLooper(), this.mPluginEventHandlerCb);
    this.mPluginMsgHandlerThread.start();
    this.mPluginMsgHandler = new Handler(this.mPluginMsgHandlerThread.getLooper(), this.mPluginMsgHandlerCb);
  }
  
  private void bindAndRequest(Context arg1, final Bundle paramBundle, Handler paramHandler)
  {
    this.mOwnerContext = ???;
    ??? = new Messenger(paramHandler);
    paramBundle.putParcelable("msgr_PluginMsgHandler", getPluginMsgReceiver());
    paramBundle.putParcelable("msgr_ReqAccResultReceiver", ???);
    LogAnt.setVersion("BBD30600");
    try
    {
      LogAnt.getDebugLevel(this.mOwnerContext.createPackageContext("com.dsi.ant.plugins.antplus", 4));
    }
    catch (PackageManager.NameNotFoundException ???)
    {
      paramHandler = TAG;
      localObject = new StringBuilder("Unable to configure logging, plugins package not found: ");
      ((StringBuilder)localObject).append(???);
      LogAnt.e(paramHandler, ((StringBuilder)localObject).toString());
    }
    paramBundle.putInt("int_PluginLibVersion", 30600);
    paramBundle.putString("string_PluginLibVersion", "3.6.0");
    paramBundle.putInt("more", 1);
    Object localObject = getServiceBindIntent();
    paramHandler = null;
    Iterator localIterator = this.mOwnerContext.getPackageManager().getInstalledPackages(0).iterator();
    do
    {
      ??? = paramHandler;
      if (!localIterator.hasNext()) {
        break;
      }
      ??? = (PackageInfo)localIterator.next();
    } while (!???.packageName.equals(((Intent)localObject).getComponent().getPackageName()));
    if (??? == null)
    {
      LogAnt.e(TAG, "Binding to plugin failed, not installed");
      sendDependencyNotInstalledMessage((Messenger)paramBundle.getParcelable("msgr_ReqAccResultReceiver"), ((Intent)localObject).getComponent().getPackageName(), "ANT+ Plugins Service");
      return;
    }
    if (???.versionCode < getRequiredServiceVersionForBind())
    {
      LogAnt.e(TAG, "Binding to plugin failed, version requirement not met");
      ??? = (Messenger)paramBundle.getParcelable("msgr_ReqAccResultReceiver");
      paramBundle = ((Intent)localObject).getComponent().getPackageName();
      paramHandler = new StringBuilder("ANT+ Plugins Service minimum v.");
      paramHandler.append(getRequiredServiceVersionForBind());
      sendDependencyNotInstalledMessage(???, paramBundle, paramHandler.toString());
      return;
    }
    this.serviceBindConn = new ServiceConnection()
    {
      public final void onServiceConnected(ComponentName arg1, IBinder paramAnonymousIBinder)
      {
        synchronized (AntPluginPcc.this.mReleaseLock)
        {
          if (AntPluginPcc.this.isReleased) {
            return;
          }
          AntPluginPcc.this.mReqAccessMessenger = new Messenger(paramAnonymousIBinder);
          paramAnonymousIBinder = Message.obtain();
          paramAnonymousIBinder.what = 0;
          paramAnonymousIBinder.setData(paramBundle);
          try
          {
            AntPluginPcc.this.mReqAccessMessenger.send(paramAnonymousIBinder);
          }
          catch (RemoteException paramAnonymousIBinder)
          {
            for (;;) {}
          }
          AntPluginPcc.this.notifyBindAndRequestFailed(paramBundle);
          return;
        }
      }
      
      public final void onServiceDisconnected(ComponentName paramAnonymousComponentName)
      {
        if (!AntPluginPcc.this.isInitialized)
        {
          AntPluginPcc.this.notifyBindAndRequestFailed(paramBundle);
          return;
        }
        AntPluginPcc.this.handleConnectionBroke("OnServiceDisconnected fired");
      }
    };
    synchronized (this.mPluginServiceBindChange_LOCK)
    {
      if (!this.mIsPluginServiceBound)
      {
        this.mIsPluginServiceBound = true;
        if (!this.mOwnerContext.bindService((Intent)localObject, this.serviceBindConn, 1))
        {
          LogAnt.e(TAG, "Binding to plugin failed");
          notifyBindAndRequestFailed(paramBundle);
        }
      }
      return;
    }
  }
  
  public static int getInstalledPluginsVersionNumber(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if (localPackageInfo.packageName.equals("com.dsi.ant.plugins.antplus"))
      {
        if (!localPackageInfo.applicationInfo.enabled) {
          return -2;
        }
        return localPackageInfo.versionCode;
      }
    }
    return -1;
  }
  
  private Messenger getPluginMsgReceiver()
  {
    return new Messenger(this.mPluginMsgHandler);
  }
  
  private void notifyBindAndRequestFailed(Bundle paramBundle)
  {
    closePluginConnection();
    paramBundle = (Messenger)paramBundle.getParcelable("msgr_ReqAccResultReceiver");
    Message localMessage = Message.obtain();
    localMessage.what = -4;
    try
    {
      paramBundle.send(localMessage);
      return;
    }
    catch (RemoteException paramBundle)
    {
      for (;;) {}
    }
    LogAnt.e(TAG, "Remote exception sending failure msg to client");
  }
  
  protected static <T extends AntPluginPcc> PccReleaseHandle<T> requestAccess_Helper_AsyncSearchByDevNumber(Context paramContext, int paramInt1, int paramInt2, T paramT, IPluginAccessResultReceiver<T> paramIPluginAccessResultReceiver, IDeviceStateChangeReceiver paramIDeviceStateChangeReceiver)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("int_RequestAccessMode", 3);
    localBundle.putInt("int_AntDeviceID", paramInt1);
    localBundle.putInt("int_ProximityBin", paramInt2);
    return requestAccess_Helper_Main(paramContext, localBundle, paramT, new RequestAccessResultHandler_AsyncSearchByDevNumber(), paramIPluginAccessResultReceiver, paramIDeviceStateChangeReceiver);
  }
  
  protected static <T extends AntPluginPcc> PccReleaseHandle<T> requestAccess_Helper_Main(Context paramContext, Bundle paramBundle, T paramT, RequestAccessResultHandler<T> paramRequestAccessResultHandler, IPluginAccessResultReceiver<T> paramIPluginAccessResultReceiver, IDeviceStateChangeReceiver paramIDeviceStateChangeReceiver)
  {
    if ((paramIPluginAccessResultReceiver != null) && (paramIDeviceStateChangeReceiver != null))
    {
      paramT.getClass();
      paramIPluginAccessResultReceiver = new StandardReleaseHandle(paramT, paramIPluginAccessResultReceiver, paramIDeviceStateChangeReceiver);
      paramT.mReleaseHandle = paramIPluginAccessResultReceiver;
      paramT.mStateChangeReceiver = paramIPluginAccessResultReceiver.stateSink;
      paramRequestAccessResultHandler.setReturnInfo(paramT, paramIPluginAccessResultReceiver.resultSink);
      requestAccess_Helper_SubMain(paramContext, paramBundle, paramT, paramRequestAccessResultHandler);
      return paramIPluginAccessResultReceiver;
    }
    paramBundle = new StringBuilder("Invalid argument: ");
    if (paramIPluginAccessResultReceiver == null) {
      paramContext = "resultReceiver ";
    } else {
      paramContext = "stateReceiver ";
    }
    paramBundle.append(paramContext);
    paramBundle.append(" is null ");
    throw new IllegalArgumentException(paramBundle.toString());
  }
  
  protected static <T extends AntPluginPcc> void requestAccess_Helper_SubMain(Context paramContext, Bundle paramBundle, T paramT, Handler paramHandler)
  {
    if (paramHandler == null) {
      throw new IllegalArgumentException("resultHandler passed from client was null");
    }
    String str = paramContext.getPackageName();
    Object localObject = paramContext.getApplicationInfo();
    localObject = paramContext.getPackageManager().getApplicationLabel((ApplicationInfo)localObject).toString();
    paramBundle.putString("str_ApplicationNamePackage", str);
    paramBundle.putString("str_ApplicationNameTitle", (String)localObject);
    if (!paramBundle.containsKey("int_RssiMode")) {
      paramBundle.putInt("int_RssiMode", 1);
    }
    paramT.bindAndRequest(paramContext, paramBundle, paramHandler);
  }
  
  private void sendDependencyNotInstalledMessage(Messenger paramMessenger, String paramString1, String paramString2)
  {
    Message localMessage = Message.obtain();
    localMessage.what = -5;
    Bundle localBundle = new Bundle();
    localBundle.putString("string_DependencyPackageName", paramString1);
    localBundle.putString("string_DependencyName", paramString2);
    localMessage.setData(localBundle);
    try
    {
      paramMessenger.send(localMessage);
      return;
    }
    catch (RemoteException paramMessenger)
    {
      for (;;) {}
    }
    handleConnectionBroke("Remote exception sending plugin 'dependency not installed' msg to client");
  }
  
  /* Error */
  private Message sendPluginCommandInternal(Message paramMessage)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 115	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginCommLock	Ljava/util/concurrent/locks/ReentrantLock;
    //   4: astore_3
    //   5: aload_3
    //   6: monitorenter
    //   7: aload_1
    //   8: getfield 363	android/os/Message:what	I
    //   11: istore_2
    //   12: aload_0
    //   13: invokestatic 481	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   16: putfield 483	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mCurrentCmdThread	Ljava/lang/Thread;
    //   19: aload_0
    //   20: getfield 485	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginMsgr	Landroid/os/Messenger;
    //   23: astore 4
    //   25: aload 4
    //   27: ifnull +442 -> 469
    //   30: aload_0
    //   31: getfield 115	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginCommLock	Ljava/util/concurrent/locks/ReentrantLock;
    //   34: ldc2_w 486
    //   37: getstatic 493	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   40: invokevirtual 497	java/util/concurrent/locks/ReentrantLock:tryLock	(JLjava/util/concurrent/TimeUnit;)Z
    //   43: ifne +11 -> 54
    //   46: new 473	java/util/concurrent/TimeoutException
    //   49: dup
    //   50: invokespecial 498	java/util/concurrent/TimeoutException:<init>	()V
    //   53: athrow
    //   54: aload_0
    //   55: getfield 485	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginMsgr	Landroid/os/Messenger;
    //   58: aload_1
    //   59: invokevirtual 367	android/os/Messenger:send	(Landroid/os/Message;)V
    //   62: aload_0
    //   63: getfield 120	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginCommMsgExch	Ljava/util/concurrent/Exchanger;
    //   66: aconst_null
    //   67: ldc2_w 499
    //   70: getstatic 503	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   73: invokevirtual 507	java/util/concurrent/Exchanger:exchange	(Ljava/lang/Object;JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;
    //   76: checkcast 356	android/os/Message
    //   79: astore_1
    //   80: aload_1
    //   81: getfield 363	android/os/Message:what	I
    //   84: iload_2
    //   85: if_icmpne +91 -> 176
    //   88: aload_1
    //   89: invokestatic 509	android/os/Message:obtain	(Landroid/os/Message;)Landroid/os/Message;
    //   92: astore_1
    //   93: aload_0
    //   94: getfield 115	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginCommLock	Ljava/util/concurrent/locks/ReentrantLock;
    //   97: invokevirtual 512	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   100: aload_0
    //   101: getfield 127	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginCommProcessingBarrier	Ljava/util/concurrent/CyclicBarrier;
    //   104: invokevirtual 515	java/util/concurrent/CyclicBarrier:await	()I
    //   107: pop
    //   108: aload_3
    //   109: monitorexit
    //   110: aload_1
    //   111: areturn
    //   112: new 222	java/lang/StringBuilder
    //   115: dup
    //   116: ldc_w 517
    //   119: invokespecial 225	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   122: astore_1
    //   123: aload_1
    //   124: iload_2
    //   125: invokevirtual 329	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   128: pop
    //   129: aload_0
    //   130: aload_1
    //   131: invokevirtual 232	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   134: invokevirtual 467	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:handleConnectionBroke	(Ljava/lang/String;)V
    //   137: invokestatic 481	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   140: invokevirtual 520	java/lang/Thread:interrupt	()V
    //   143: aload_3
    //   144: monitorexit
    //   145: aconst_null
    //   146: areturn
    //   147: new 222	java/lang/StringBuilder
    //   150: dup
    //   151: ldc_w 522
    //   154: invokespecial 225	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   157: astore_1
    //   158: aload_1
    //   159: iload_2
    //   160: invokevirtual 329	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   163: pop
    //   164: aload_0
    //   165: aload_1
    //   166: invokevirtual 232	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   169: invokevirtual 467	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:handleConnectionBroke	(Ljava/lang/String;)V
    //   172: aload_3
    //   173: monitorexit
    //   174: aconst_null
    //   175: areturn
    //   176: aload_0
    //   177: aload_1
    //   178: invokevirtual 525	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:handleNonCmdPluginMessage	(Landroid/os/Message;)V
    //   181: aload_0
    //   182: getfield 127	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginCommProcessingBarrier	Ljava/util/concurrent/CyclicBarrier;
    //   185: invokevirtual 515	java/util/concurrent/CyclicBarrier:await	()I
    //   188: pop
    //   189: goto -127 -> 62
    //   192: new 222	java/lang/StringBuilder
    //   195: dup
    //   196: ldc_w 527
    //   199: invokespecial 225	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   202: astore_1
    //   203: aload_1
    //   204: iload_2
    //   205: invokevirtual 329	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   208: pop
    //   209: aload_0
    //   210: aload_1
    //   211: invokevirtual 232	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   214: invokevirtual 467	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:handleConnectionBroke	(Ljava/lang/String;)V
    //   217: invokestatic 481	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   220: invokevirtual 520	java/lang/Thread:interrupt	()V
    //   223: aload_0
    //   224: getfield 115	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginCommLock	Ljava/util/concurrent/locks/ReentrantLock;
    //   227: invokevirtual 512	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   230: aload_3
    //   231: monitorexit
    //   232: aconst_null
    //   233: areturn
    //   234: new 222	java/lang/StringBuilder
    //   237: dup
    //   238: ldc_w 529
    //   241: invokespecial 225	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   244: astore_1
    //   245: aload_1
    //   246: iload_2
    //   247: invokevirtual 329	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   250: pop
    //   251: aload_0
    //   252: aload_1
    //   253: invokevirtual 232	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   256: invokevirtual 467	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:handleConnectionBroke	(Ljava/lang/String;)V
    //   259: aload_0
    //   260: getfield 115	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginCommLock	Ljava/util/concurrent/locks/ReentrantLock;
    //   263: invokevirtual 512	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   266: aload_3
    //   267: monitorexit
    //   268: aconst_null
    //   269: areturn
    //   270: new 222	java/lang/StringBuilder
    //   273: dup
    //   274: ldc_w 531
    //   277: invokespecial 225	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   280: astore_1
    //   281: aload_1
    //   282: iload_2
    //   283: invokevirtual 329	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   286: pop
    //   287: aload_0
    //   288: aload_1
    //   289: invokevirtual 232	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   292: invokevirtual 467	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:handleConnectionBroke	(Ljava/lang/String;)V
    //   295: aload_0
    //   296: getfield 115	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginCommLock	Ljava/util/concurrent/locks/ReentrantLock;
    //   299: invokevirtual 512	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   302: aload_3
    //   303: monitorexit
    //   304: aconst_null
    //   305: areturn
    //   306: new 222	java/lang/StringBuilder
    //   309: dup
    //   310: ldc_w 533
    //   313: invokespecial 225	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   316: astore_1
    //   317: aload_1
    //   318: iload_2
    //   319: invokevirtual 329	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   322: pop
    //   323: aload_0
    //   324: aload_1
    //   325: invokevirtual 232	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   328: invokevirtual 467	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:handleConnectionBroke	(Ljava/lang/String;)V
    //   331: invokestatic 481	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   334: invokevirtual 520	java/lang/Thread:interrupt	()V
    //   337: aload_0
    //   338: getfield 115	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginCommLock	Ljava/util/concurrent/locks/ReentrantLock;
    //   341: invokevirtual 512	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   344: aload_3
    //   345: monitorexit
    //   346: aconst_null
    //   347: areturn
    //   348: astore_1
    //   349: goto +47 -> 396
    //   352: new 222	java/lang/StringBuilder
    //   355: dup
    //   356: ldc_w 535
    //   359: invokespecial 225	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   362: astore_1
    //   363: aload_1
    //   364: iload_2
    //   365: invokevirtual 329	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   368: pop
    //   369: aload_1
    //   370: ldc_w 537
    //   373: invokevirtual 422	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   376: pop
    //   377: aload_0
    //   378: aload_1
    //   379: invokevirtual 232	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   382: invokevirtual 467	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:handleConnectionBroke	(Ljava/lang/String;)V
    //   385: aload_0
    //   386: getfield 115	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginCommLock	Ljava/util/concurrent/locks/ReentrantLock;
    //   389: invokevirtual 512	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   392: aload_3
    //   393: monitorexit
    //   394: aconst_null
    //   395: areturn
    //   396: aload_0
    //   397: getfield 115	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginCommLock	Ljava/util/concurrent/locks/ReentrantLock;
    //   400: invokevirtual 512	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   403: aload_1
    //   404: athrow
    //   405: new 222	java/lang/StringBuilder
    //   408: dup
    //   409: ldc_w 539
    //   412: invokespecial 225	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   415: astore_1
    //   416: aload_1
    //   417: iload_2
    //   418: invokevirtual 329	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   421: pop
    //   422: aload_0
    //   423: aload_1
    //   424: invokevirtual 232	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   427: invokevirtual 467	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:handleConnectionBroke	(Ljava/lang/String;)V
    //   430: aload_3
    //   431: monitorexit
    //   432: aconst_null
    //   433: areturn
    //   434: new 222	java/lang/StringBuilder
    //   437: dup
    //   438: ldc_w 541
    //   441: invokespecial 225	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   444: astore_1
    //   445: aload_1
    //   446: iload_2
    //   447: invokevirtual 329	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   450: pop
    //   451: aload_0
    //   452: aload_1
    //   453: invokevirtual 232	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   456: invokevirtual 467	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:handleConnectionBroke	(Ljava/lang/String;)V
    //   459: invokestatic 481	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   462: invokevirtual 520	java/lang/Thread:interrupt	()V
    //   465: aload_3
    //   466: monitorexit
    //   467: aconst_null
    //   468: areturn
    //   469: aload_3
    //   470: monitorexit
    //   471: aconst_null
    //   472: areturn
    //   473: astore_1
    //   474: aload_3
    //   475: monitorexit
    //   476: aload_1
    //   477: athrow
    //   478: astore_1
    //   479: goto -45 -> 434
    //   482: astore_1
    //   483: goto -78 -> 405
    //   486: astore_1
    //   487: goto -135 -> 352
    //   490: astore_1
    //   491: goto -185 -> 306
    //   494: astore_1
    //   495: goto -225 -> 270
    //   498: astore_1
    //   499: goto -352 -> 147
    //   502: astore_1
    //   503: goto -391 -> 112
    //   506: astore_1
    //   507: goto -273 -> 234
    //   510: astore_1
    //   511: goto -319 -> 192
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	514	0	this	AntPluginPcc
    //   0	514	1	paramMessage	Message
    //   11	436	2	i	int
    //   4	471	3	localReentrantLock	ReentrantLock
    //   23	3	4	localMessenger	Messenger
    // Exception table:
    //   from	to	target	type
    //   54	62	348	finally
    //   62	80	348	finally
    //   80	93	348	finally
    //   176	181	348	finally
    //   181	189	348	finally
    //   192	223	348	finally
    //   234	259	348	finally
    //   270	295	348	finally
    //   306	337	348	finally
    //   352	385	348	finally
    //   7	25	473	finally
    //   30	54	473	finally
    //   93	100	473	finally
    //   100	108	473	finally
    //   108	110	473	finally
    //   112	145	473	finally
    //   147	174	473	finally
    //   223	232	473	finally
    //   259	268	473	finally
    //   295	304	473	finally
    //   337	346	473	finally
    //   385	394	473	finally
    //   396	405	473	finally
    //   405	432	473	finally
    //   434	467	473	finally
    //   469	471	473	finally
    //   474	476	473	finally
    //   30	54	478	java/lang/InterruptedException
    //   30	54	482	java/util/concurrent/TimeoutException
    //   54	62	486	android/os/RemoteException
    //   62	80	486	android/os/RemoteException
    //   80	93	486	android/os/RemoteException
    //   176	181	486	android/os/RemoteException
    //   181	189	486	android/os/RemoteException
    //   192	223	486	android/os/RemoteException
    //   234	259	486	android/os/RemoteException
    //   270	295	486	android/os/RemoteException
    //   306	337	486	android/os/RemoteException
    //   62	80	490	java/lang/InterruptedException
    //   62	80	494	java/util/concurrent/TimeoutException
    //   100	108	498	java/util/concurrent/BrokenBarrierException
    //   100	108	502	java/lang/InterruptedException
    //   181	189	506	java/util/concurrent/BrokenBarrierException
    //   181	189	510	java/lang/InterruptedException
  }
  
  protected final void closePluginConnection()
  {
    Object localObject6;
    Object localObject7;
    synchronized (this.mReleaseLock)
    {
      if (this.isReleased) {
        return;
      }
      this.isReleased = true;
      ??? = this.mReqAccessMessenger;
      if (??? != null)
      {
        ??? = new CountDownLatch(1);
        localObject6 = new Bundle();
        ((Bundle)localObject6).putParcelable("msgr_PluginMsgHandler", getPluginMsgReceiver());
        ((Bundle)localObject6).putParcelable("msgr_ReqAccResultReceiver", new Messenger(new Handler(this.mPluginMsgHandlerThread.getLooper())
        {
          public final void handleMessage(Message paramAnonymousMessage)
          {
            this.val$latch.countDown();
          }
        }));
        localObject7 = Message.obtain();
        ((Message)localObject7).what = 1;
        ((Message)localObject7).setData((Bundle)localObject6);
      }
    }
    try
    {
      ((Messenger)???).send((Message)localObject7);
      ((CountDownLatch)???).await(500L, TimeUnit.MILLISECONDS);
      this.mPluginMsgHandlerThread.quit();
      try
      {
        this.mPluginMsgHandlerThread.join(1000L);
      }
      catch (InterruptedException localInterruptedException1)
      {
        for (;;) {}
      }
      LogAnt.e(TAG, "Plugin Msg Handler thread failed to shut down cleanly, InterruptedException");
      Thread.currentThread().interrupt();
      this.mPluginEventHandler = null;
      this.mPluginEventHandlerThread.quit();
      try
      {
        this.mPluginEventHandlerThread.join(1000L);
      }
      catch (InterruptedException localInterruptedException2)
      {
        for (;;) {}
      }
      LogAnt.e(TAG, "Plugin Event Handler thread failed to shut down cleanly, InterruptedException");
      Thread.currentThread().interrupt();
      synchronized (this.mPluginServiceBindChange_LOCK)
      {
        boolean bool = this.mIsPluginServiceBound;
        if (bool)
        {
          try
          {
            this.mOwnerContext.unbindService(this.serviceBindConn);
          }
          catch (IllegalArgumentException localIllegalArgumentException)
          {
            localObject6 = TAG;
            localObject7 = new StringBuilder("Unexpected error unbinding service, ");
            ((StringBuilder)localObject7).append(localIllegalArgumentException);
            LogAnt.e((String)localObject6, ((StringBuilder)localObject7).toString());
          }
          this.mIsPluginServiceBound = false;
        }
        if (this.mPluginCommLock.tryLock()) {
          this.mPluginCommLock.unlock();
        } else {
          this.mCurrentCmdThread.interrupt();
        }
        synchronized (this.mPluginCommLock)
        {
          this.mPluginMsgr = null;
          return;
        }
      }
      localObject2 = finally;
      throw localObject2;
    }
    catch (RemoteException|InterruptedException localRemoteException)
    {
      for (;;) {}
    }
  }
  
  protected Message createCmdMsg(int paramInt, Bundle paramBundle)
  {
    Message localMessage = Message.obtain();
    localMessage.what = paramInt;
    Bundle localBundle = paramBundle;
    if (paramBundle == null) {
      localBundle = new Bundle();
    }
    localBundle.putSerializable("uuid_AccessToken", this.mAccessToken);
    localMessage.setData(localBundle);
    return localMessage;
  }
  
  protected abstract int getRequiredServiceVersionForBind();
  
  protected abstract Intent getServiceBindIntent();
  
  final void handleConnectionBroke(String paramString)
  {
    String str = TAG;
    StringBuilder localStringBuilder = new StringBuilder("ConnectionDied: ");
    localStringBuilder.append(paramString);
    LogAnt.w(str, localStringBuilder.toString());
    if (this.mReleaseHandle != null)
    {
      if (this.mReleaseHandle.isClosed) {
        return;
      }
      releaseToken();
      this.mStateChangeReceiver.onDeviceStateChange(DeviceState.DEAD);
      return;
    }
  }
  
  protected final void handleNonCmdPluginMessage(Message paramMessage)
  {
    Handler localHandler = this.mPluginEventHandler;
    if (localHandler != null)
    {
      Message localMessage = localHandler.obtainMessage(paramMessage.what, paramMessage.arg1, paramMessage.arg2, paramMessage.obj);
      localMessage.setData(paramMessage.getData());
      localMessage.replyTo = paramMessage.replyTo;
      localHandler.sendMessage(localMessage);
    }
  }
  
  protected abstract void handlePluginEvent(Message paramMessage);
  
  final void init(AntPluginDeviceDbProvider.DeviceDbDeviceInfo paramDeviceDbDeviceInfo, UUID paramUUID, Messenger paramMessenger, int paramInt1, int paramInt2)
  {
    this.deviceInfo = paramDeviceDbDeviceInfo;
    this.mAccessToken = paramUUID;
    this.mPluginMsgr = paramMessenger;
    this.reportedServiceVersion = paramInt2;
    if (this.mCachedState == null) {
      this.mCachedState = Integer.valueOf(paramInt1);
    }
    this.isInitialized = true;
  }
  
  public final void releaseAccess()
  {
    this.mReleaseHandle.close();
  }
  
  /* Error */
  final void releaseToken()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 115	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginCommLock	Ljava/util/concurrent/locks/ReentrantLock;
    //   4: astore_1
    //   5: aload_1
    //   6: monitorenter
    //   7: aload_0
    //   8: bipush -100
    //   10: invokestatic 639	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   13: putfield 136	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mCachedState	Ljava/lang/Integer;
    //   16: aload_0
    //   17: getfield 115	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginCommLock	Ljava/util/concurrent/locks/ReentrantLock;
    //   20: astore_2
    //   21: aload_2
    //   22: monitorenter
    //   23: aload_0
    //   24: getfield 485	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginMsgr	Landroid/os/Messenger;
    //   27: ifnull +68 -> 95
    //   30: aload_0
    //   31: getfield 485	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginMsgr	Landroid/os/Messenger;
    //   34: aload_0
    //   35: sipush 10002
    //   38: aconst_null
    //   39: invokevirtual 645	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:createCmdMsg	(ILandroid/os/Bundle;)Landroid/os/Message;
    //   42: invokevirtual 367	android/os/Messenger:send	(Landroid/os/Message;)V
    //   45: goto +50 -> 95
    //   48: astore_3
    //   49: goto +55 -> 104
    //   52: getstatic 164	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:TAG	Ljava/lang/String;
    //   55: astore_3
    //   56: new 222	java/lang/StringBuilder
    //   59: dup
    //   60: ldc_w 647
    //   63: invokespecial 225	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   66: astore 4
    //   68: aload 4
    //   70: sipush 10002
    //   73: invokevirtual 329	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   76: pop
    //   77: aload 4
    //   79: ldc_w 649
    //   82: invokevirtual 422	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: pop
    //   86: aload_3
    //   87: aload 4
    //   89: invokevirtual 232	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   92: invokestatic 236	com/dsi/ant/plugins/utility/log/LogAnt:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   95: aload_2
    //   96: monitorexit
    //   97: aload_0
    //   98: invokevirtual 354	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:closePluginConnection	()V
    //   101: aload_1
    //   102: monitorexit
    //   103: return
    //   104: aload_2
    //   105: monitorexit
    //   106: aload_3
    //   107: athrow
    //   108: astore_2
    //   109: aload_0
    //   110: invokevirtual 354	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:closePluginConnection	()V
    //   113: aload_2
    //   114: athrow
    //   115: astore_2
    //   116: aload_1
    //   117: monitorexit
    //   118: aload_2
    //   119: athrow
    //   120: astore_3
    //   121: goto -69 -> 52
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	124	0	this	AntPluginPcc
    //   4	113	1	localReentrantLock1	ReentrantLock
    //   108	6	2	localObject1	Object
    //   115	4	2	localObject2	Object
    //   48	1	3	localObject3	Object
    //   55	52	3	str	String
    //   120	1	3	localRemoteException	RemoteException
    //   66	22	4	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   23	45	48	finally
    //   52	95	48	finally
    //   95	97	48	finally
    //   104	106	48	finally
    //   16	23	108	finally
    //   106	108	108	finally
    //   7	16	115	finally
    //   97	103	115	finally
    //   109	115	115	finally
    //   116	118	115	finally
    //   23	45	120	android/os/RemoteException
  }
  
  protected final Message sendPluginCommand(Message paramMessage)
  {
    Bundle localBundle2 = paramMessage.getData();
    Bundle localBundle1 = localBundle2;
    if (localBundle2 == null)
    {
      localBundle1 = new Bundle();
      paramMessage.setData(localBundle1);
    }
    localBundle1.putSerializable("uuid_AccessToken", this.mAccessToken);
    return sendPluginCommandInternal(paramMessage);
  }
  
  protected final boolean subscribeToEvent(int paramInt)
  {
    Message localMessage = createCmdMsg(10000, null);
    localMessage.arg1 = paramInt;
    localMessage = sendPluginCommand(localMessage);
    if (localMessage == null)
    {
      LogAnt.e(TAG, "subscribeToEvent died in sendPluginCommand()");
      return false;
    }
    if (localMessage.arg1 != 0)
    {
      String str = TAG;
      StringBuilder localStringBuilder = new StringBuilder("Subscribing to event ");
      localStringBuilder.append(paramInt);
      localStringBuilder.append(" failed with code ");
      localStringBuilder.append(localMessage.arg1);
      LogAnt.e(str, localStringBuilder.toString());
      localMessage.recycle();
      return false;
    }
    localMessage.recycle();
    return true;
  }
  
  protected final void unsubscribeFromEvent(int paramInt)
  {
    Message localMessage = createCmdMsg(10001, null);
    localMessage.arg1 = paramInt;
    Object localObject = sendPluginCommand(localMessage);
    if (localObject == null)
    {
      LogAnt.e(TAG, "unsubscribeFromEvent died in sendPluginCommand()");
      return;
    }
    if (((Message)localObject).arg1 != 0)
    {
      localObject = new StringBuilder("Unsubscribing to event ");
      ((StringBuilder)localObject).append(paramInt);
      ((StringBuilder)localObject).append(" failed with code ");
      ((StringBuilder)localObject).append(localMessage.arg1);
      throw new RuntimeException(((StringBuilder)localObject).toString());
    }
    ((Message)localObject).recycle();
  }
  
  public static abstract interface IDeviceStateChangeReceiver
  {
    public abstract void onDeviceStateChange(DeviceState paramDeviceState);
  }
  
  public static abstract interface IPluginAccessResultReceiver<T extends AntPluginPcc>
  {
    public abstract void onResultReceived(T paramT, RequestAccessResult paramRequestAccessResult, DeviceState paramDeviceState);
  }
  
  protected static class RequestAccessResultHandler<T extends AntPluginPcc>
    extends Handler
  {
    protected AntPluginPcc.IPluginAccessResultReceiver<T> resultReceiver;
    protected T retPccObject;
    
    public RequestAccessResultHandler() {}
    
    public void handleMessage(Message paramMessage)
    {
      Object localObject = AntPluginPcc.TAG;
      StringBuilder localStringBuilder = new StringBuilder("ReqAcc Handler received: ");
      localStringBuilder.append(paramMessage.what);
      LogAnt.v((String)localObject, localStringBuilder.toString());
      paramMessage.getData().setClassLoader(getClass().getClassLoader());
      if (!handleRequestAccessResult(paramMessage))
      {
        localObject = RequestAccessResult.getValueFromInt(paramMessage.what);
        if (localObject == RequestAccessResult.UNRECOGNIZED)
        {
          localStringBuilder = new StringBuilder("Unrecognized return code (need app lib upgrade): ");
          localStringBuilder.append(paramMessage.what);
          localStringBuilder.append("!!!");
          handleRequestAccessFailed(localStringBuilder.toString(), (RequestAccessResult)localObject);
          return;
        }
        handleRequestAccessFailed(((RequestAccessResult)localObject).toString(), (RequestAccessResult)localObject);
      }
    }
    
    public final void handleRequestAccessFailed(String paramString, RequestAccessResult paramRequestAccessResult)
    {
      String str = AntPluginPcc.TAG;
      StringBuilder localStringBuilder = new StringBuilder("RequestAccess failed: ");
      localStringBuilder.append(paramString);
      LogAnt.w(str, localStringBuilder.toString());
      this.retPccObject.releaseToken();
      this.resultReceiver.onResultReceived(null, paramRequestAccessResult, DeviceState.DEAD);
    }
    
    public boolean handleRequestAccessResult(Message paramMessage)
    {
      int i = paramMessage.what;
      if (i != -5)
      {
        if (i != 0) {
          return false;
        }
        Bundle localBundle = paramMessage.getData();
        int j = localBundle.getInt("int_ServiceVersion", 0);
        Messenger localMessenger = (Messenger)localBundle.getParcelable("msgr_PluginComm");
        UUID localUUID = (UUID)localBundle.get("uuid_AccessToken");
        int k = localBundle.getInt("int_InitialDeviceStateCode");
        AntPluginDeviceDbProvider.DeviceDbDeviceInfo localDeviceDbDeviceInfo = (AntPluginDeviceDbProvider.DeviceDbDeviceInfo)localBundle.getParcelable("parcelable_DeviceDbInfo");
        paramMessage = localDeviceDbDeviceInfo;
        if (localDeviceDbDeviceInfo == null)
        {
          paramMessage = new AntPluginDeviceDbProvider.DeviceDbDeviceInfo(0);
          paramMessage.antDeviceNumber = Integer.valueOf(localBundle.getInt("int_AntDeviceID", -1));
          paramMessage.visibleName = localBundle.getString("str_DeviceName");
          paramMessage.isPreferredDevice = Boolean.valueOf(false);
        }
        this.retPccObject.supportsRssiEvent = localBundle.getBoolean("bool_RssiSupport", false);
        this.retPccObject.init(paramMessage, localUUID, localMessenger, k, j);
        this.resultReceiver.onResultReceived(this.retPccObject, RequestAccessResult.getValueFromInt(i), DeviceState.getValueFromInt(k));
        this.retPccObject.deviceInitializedLatch.countDown();
        return true;
      }
      paramMessage = paramMessage.getData();
      AntPluginPcc.lastMissingDependencyPkgName = paramMessage.getString("string_DependencyPackageName");
      AntPluginPcc.lastMissingDependencyName = paramMessage.getString("string_DependencyName");
      paramMessage = new StringBuilder("Missing Dependency: ");
      paramMessage.append(AntPluginPcc.lastMissingDependencyPkgName);
      paramMessage.append(" not installed.");
      handleRequestAccessFailed(paramMessage.toString(), RequestAccessResult.DEPENDENCY_NOT_INSTALLED);
      return true;
    }
    
    protected void setReturnInfo(T paramT, AntPluginPcc.IPluginAccessResultReceiver<T> paramIPluginAccessResultReceiver)
    {
      this.retPccObject = paramT;
      this.resultReceiver = paramIPluginAccessResultReceiver;
    }
  }
  
  protected static class RequestAccessResultHandler_AsyncSearchByDevNumber<T extends AntPluginPcc>
    extends AntPluginPcc.RequestAccessResultHandler<T>
  {
    protected RequestAccessResultHandler_AsyncSearchByDevNumber() {}
    
    public boolean handleRequestAccessResult(Message paramMessage)
    {
      if (paramMessage.what == -7)
      {
        handleRequestAccessFailed("Search for device timed out.", RequestAccessResult.SEARCH_TIMEOUT);
        return true;
      }
      return super.handleRequestAccessResult(paramMessage);
    }
  }
  
  protected final class StandardReleaseHandle<T extends AntPluginPcc>
    extends PccReleaseHandle<T>
  {
    protected StandardReleaseHandle(AntPluginPcc.IDeviceStateChangeReceiver paramIDeviceStateChangeReceiver)
    {
      super(localIDeviceStateChangeReceiver);
    }
    
    protected final void requestCancelled()
    {
      AntPluginPcc.this.closePluginConnection();
    }
  }
}
