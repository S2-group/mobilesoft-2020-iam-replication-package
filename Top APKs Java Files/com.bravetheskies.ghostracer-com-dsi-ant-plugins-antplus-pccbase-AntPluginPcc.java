package com.dsi.ant.plugins.antplus.pccbase;

import android.app.Activity;
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
  private static final long CLOSE_CONNECTION_TIMEOUT_MS = 500L;
  public static final String PATH_ANTPLUS_PLUGINS_PKG = "com.dsi.ant.plugins.antplus";
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
    public boolean handleMessage(Message paramAnonymousMessage)
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
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("Unrecognized plugin event received: ");
              localStringBuilder.append(paramAnonymousMessage.arg1);
              LogAnt.w((String)localObject2, localStringBuilder.toString());
            }
            else
            {
              i = paramAnonymousMessage.arg1;
              AntPluginPcc.this.mCachedState = Integer.valueOf(i);
              paramAnonymousMessage = AntPluginPcc.TAG;
              localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append("State event: ");
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
    public boolean handleMessage(Message paramAnonymousMessage)
    {
      Object localObject = AntPluginPcc.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Plugin Msg Handler received: ");
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
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("InterruptedException in mPluginMsgHandler trying to fwd message ");
      localStringBuilder.append(paramAnonymousMessage.what);
      ((AntPluginPcc)localObject).handleConnectionBroke(localStringBuilder.toString());
      Thread.currentThread().interrupt();
      return true;
      localObject = AntPluginPcc.this;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("BrokenBarrierException in mPluginMsgHandler trying to fwd message ");
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
  
  private void bindPluginService(Intent paramIntent, Bundle paramBundle)
  {
    synchronized (this.mPluginServiceBindChange_LOCK)
    {
      if (!this.mIsPluginServiceBound)
      {
        this.mIsPluginServiceBound = true;
        if (!this.mOwnerContext.bindService(paramIntent, this.serviceBindConn, 1))
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
  
  public static String getInstalledPluginsVersionString(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if (localPackageInfo.packageName.equals("com.dsi.ant.plugins.antplus")) {
        return localPackageInfo.versionName;
      }
    }
    return null;
  }
  
  public static String getMissingDependencyName()
  {
    return lastMissingDependencyName;
  }
  
  public static String getMissingDependencyPackageName()
  {
    return lastMissingDependencyPkgName;
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
  
  protected static <T extends AntPluginPcc> AsyncScanController<T> requestAccess_Helper_AsyncScanController(Context paramContext, int paramInt, T paramT, AsyncScanController.IAsyncScanResultReceiver paramIAsyncScanResultReceiver)
  {
    paramIAsyncScanResultReceiver = new AsyncScanController(paramIAsyncScanResultReceiver, paramT);
    return requestAsyncScan_Helper_SubMain(paramContext, paramInt, new Bundle(), paramT, paramIAsyncScanResultReceiver);
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
    paramBundle = new StringBuilder();
    paramBundle.append("Invalid argument: ");
    if (paramIPluginAccessResultReceiver == null) {
      paramContext = "resultReceiver ";
    } else {
      paramContext = "stateReceiver ";
    }
    paramBundle.append(paramContext);
    paramBundle.append(" is null ");
    throw new IllegalArgumentException(paramBundle.toString());
  }
  
  protected static <T extends AntPluginPcc> PccReleaseHandle<T> requestAccess_Helper_SearchActivity(Activity paramActivity, Context paramContext, boolean paramBoolean, int paramInt, T paramT, IPluginAccessResultReceiver<T> paramIPluginAccessResultReceiver, IDeviceStateChangeReceiver paramIDeviceStateChangeReceiver)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("int_RequestAccessMode", 1);
    localBundle.putBoolean("b_ForceManualSelect", paramBoolean);
    localBundle.putInt("int_ProximityBin", paramInt);
    return requestAccess_Helper_Main(paramContext, localBundle, paramT, new RequestAccessResultHandler_UI(paramActivity), paramIPluginAccessResultReceiver, paramIDeviceStateChangeReceiver);
  }
  
  protected static <T extends AntPluginPcc> void requestAccess_Helper_SubMain(Context paramContext, Bundle paramBundle, T paramT, Handler paramHandler)
  {
    if (paramHandler != null)
    {
      String str = paramContext.getPackageName();
      Object localObject = paramContext.getApplicationInfo();
      localObject = paramContext.getPackageManager().getApplicationLabel((ApplicationInfo)localObject).toString();
      paramBundle.putString("str_ApplicationNamePackage", str);
      paramBundle.putString("str_ApplicationNameTitle", (String)localObject);
      if (!paramBundle.containsKey("int_RssiMode")) {
        paramBundle.putInt("int_RssiMode", 1);
      }
      paramT.bindAndRequest(paramContext, paramBundle, paramHandler);
      return;
    }
    throw new IllegalArgumentException("resultHandler passed from client was null");
  }
  
  protected static <T extends AntPluginPcc> AsyncScanController<T> requestAsyncScan_Helper_SubMain(Context paramContext, int paramInt, Bundle paramBundle, T paramT, AsyncScanController<T> paramAsyncScanController)
  {
    if (getInstalledPluginsVersionNumber(paramContext) < 10800)
    {
      LogAnt.e(TAG, "Binding to plugin failed, version requirement not met for async scan controller mode");
      lastMissingDependencyPkgName = paramT.getServiceBindIntent().getComponent().getPackageName();
      paramContext = new StringBuilder();
      paramContext.append(paramT.getPluginPrintableName());
      paramContext.append(" minimum v.10800");
      lastMissingDependencyName = paramContext.toString();
      paramAsyncScanController.scanResultReceiver.onSearchStopped(RequestAccessResult.DEPENDENCY_NOT_INSTALLED);
      return null;
    }
    paramBundle.putInt("int_RequestAccessMode", 2);
    paramBundle.putInt("int_ProximityBin", paramInt);
    requestAccess_Helper_SubMain(paramContext, paramBundle, paramT, paramAsyncScanController.getScanResultHandler());
    return paramAsyncScanController;
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
    //   1: getfield 125	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginCommLock	Ljava/util/concurrent/locks/ReentrantLock;
    //   4: astore 4
    //   6: aload 4
    //   8: monitorenter
    //   9: aload_1
    //   10: getfield 297	android/os/Message:what	I
    //   13: istore_2
    //   14: aload_0
    //   15: invokestatic 502	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   18: putfield 504	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mCurrentCmdThread	Ljava/lang/Thread;
    //   21: aload_0
    //   22: getfield 506	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginMsgr	Landroid/os/Messenger;
    //   25: astore 5
    //   27: aload 5
    //   29: ifnull +499 -> 528
    //   32: aload_0
    //   33: getfield 125	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginCommLock	Ljava/util/concurrent/locks/ReentrantLock;
    //   36: ldc2_w 507
    //   39: getstatic 514	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   42: invokevirtual 518	java/util/concurrent/locks/ReentrantLock:tryLock	(JLjava/util/concurrent/TimeUnit;)Z
    //   45: istore_3
    //   46: iload_3
    //   47: ifeq +397 -> 444
    //   50: aload_0
    //   51: getfield 506	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginMsgr	Landroid/os/Messenger;
    //   54: aload_1
    //   55: invokevirtual 301	android/os/Messenger:send	(Landroid/os/Message;)V
    //   58: aload_0
    //   59: getfield 130	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginCommMsgExch	Ljava/util/concurrent/Exchanger;
    //   62: aconst_null
    //   63: ldc2_w 519
    //   66: getstatic 523	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   69: invokevirtual 527	java/util/concurrent/Exchanger:exchange	(Ljava/lang/Object;JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;
    //   72: checkcast 290	android/os/Message
    //   75: astore_1
    //   76: aload_1
    //   77: getfield 297	android/os/Message:what	I
    //   80: iload_2
    //   81: if_icmpne +104 -> 185
    //   84: aload_1
    //   85: invokestatic 529	android/os/Message:obtain	(Landroid/os/Message;)Landroid/os/Message;
    //   88: astore_1
    //   89: aload_0
    //   90: getfield 125	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginCommLock	Ljava/util/concurrent/locks/ReentrantLock;
    //   93: invokevirtual 532	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   96: aload_0
    //   97: getfield 137	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginCommProcessingBarrier	Ljava/util/concurrent/CyclicBarrier;
    //   100: invokevirtual 536	java/util/concurrent/CyclicBarrier:await	()I
    //   103: pop
    //   104: aload 4
    //   106: monitorexit
    //   107: aload_1
    //   108: areturn
    //   109: new 365	java/lang/StringBuilder
    //   112: dup
    //   113: invokespecial 366	java/lang/StringBuilder:<init>	()V
    //   116: astore_1
    //   117: aload_1
    //   118: ldc_w 538
    //   121: invokevirtual 372	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: pop
    //   125: aload_1
    //   126: iload_2
    //   127: invokevirtual 541	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   130: pop
    //   131: aload_0
    //   132: aload_1
    //   133: invokevirtual 383	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   136: invokevirtual 488	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:handleConnectionBroke	(Ljava/lang/String;)V
    //   139: invokestatic 502	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   142: invokevirtual 544	java/lang/Thread:interrupt	()V
    //   145: aload 4
    //   147: monitorexit
    //   148: aconst_null
    //   149: areturn
    //   150: new 365	java/lang/StringBuilder
    //   153: dup
    //   154: invokespecial 366	java/lang/StringBuilder:<init>	()V
    //   157: astore_1
    //   158: aload_1
    //   159: ldc_w 546
    //   162: invokevirtual 372	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   165: pop
    //   166: aload_1
    //   167: iload_2
    //   168: invokevirtual 541	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   171: pop
    //   172: aload_0
    //   173: aload_1
    //   174: invokevirtual 383	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   177: invokevirtual 488	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:handleConnectionBroke	(Ljava/lang/String;)V
    //   180: aload 4
    //   182: monitorexit
    //   183: aconst_null
    //   184: areturn
    //   185: aload_0
    //   186: aload_1
    //   187: invokevirtual 549	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:handleNonCmdPluginMessage	(Landroid/os/Message;)V
    //   190: aload_0
    //   191: getfield 137	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginCommProcessingBarrier	Ljava/util/concurrent/CyclicBarrier;
    //   194: invokevirtual 536	java/util/concurrent/CyclicBarrier:await	()I
    //   197: pop
    //   198: goto -140 -> 58
    //   201: new 365	java/lang/StringBuilder
    //   204: dup
    //   205: invokespecial 366	java/lang/StringBuilder:<init>	()V
    //   208: astore_1
    //   209: aload_1
    //   210: ldc_w 551
    //   213: invokevirtual 372	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   216: pop
    //   217: aload_1
    //   218: iload_2
    //   219: invokevirtual 541	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   222: pop
    //   223: aload_0
    //   224: aload_1
    //   225: invokevirtual 383	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   228: invokevirtual 488	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:handleConnectionBroke	(Ljava/lang/String;)V
    //   231: invokestatic 502	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   234: invokevirtual 544	java/lang/Thread:interrupt	()V
    //   237: aload_0
    //   238: getfield 125	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginCommLock	Ljava/util/concurrent/locks/ReentrantLock;
    //   241: invokevirtual 532	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   244: aload 4
    //   246: monitorexit
    //   247: aconst_null
    //   248: areturn
    //   249: new 365	java/lang/StringBuilder
    //   252: dup
    //   253: invokespecial 366	java/lang/StringBuilder:<init>	()V
    //   256: astore_1
    //   257: aload_1
    //   258: ldc_w 553
    //   261: invokevirtual 372	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   264: pop
    //   265: aload_1
    //   266: iload_2
    //   267: invokevirtual 541	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   270: pop
    //   271: aload_0
    //   272: aload_1
    //   273: invokevirtual 383	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   276: invokevirtual 488	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:handleConnectionBroke	(Ljava/lang/String;)V
    //   279: aload_0
    //   280: getfield 125	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginCommLock	Ljava/util/concurrent/locks/ReentrantLock;
    //   283: invokevirtual 532	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   286: aload 4
    //   288: monitorexit
    //   289: aconst_null
    //   290: areturn
    //   291: new 365	java/lang/StringBuilder
    //   294: dup
    //   295: invokespecial 366	java/lang/StringBuilder:<init>	()V
    //   298: astore_1
    //   299: aload_1
    //   300: ldc_w 555
    //   303: invokevirtual 372	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   306: pop
    //   307: aload_1
    //   308: iload_2
    //   309: invokevirtual 541	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   312: pop
    //   313: aload_0
    //   314: aload_1
    //   315: invokevirtual 383	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   318: invokevirtual 488	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:handleConnectionBroke	(Ljava/lang/String;)V
    //   321: aload_0
    //   322: getfield 125	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginCommLock	Ljava/util/concurrent/locks/ReentrantLock;
    //   325: invokevirtual 532	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   328: aload 4
    //   330: monitorexit
    //   331: aconst_null
    //   332: areturn
    //   333: new 365	java/lang/StringBuilder
    //   336: dup
    //   337: invokespecial 366	java/lang/StringBuilder:<init>	()V
    //   340: astore_1
    //   341: aload_1
    //   342: ldc_w 557
    //   345: invokevirtual 372	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   348: pop
    //   349: aload_1
    //   350: iload_2
    //   351: invokevirtual 541	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   354: pop
    //   355: aload_0
    //   356: aload_1
    //   357: invokevirtual 383	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   360: invokevirtual 488	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:handleConnectionBroke	(Ljava/lang/String;)V
    //   363: invokestatic 502	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   366: invokevirtual 544	java/lang/Thread:interrupt	()V
    //   369: aload_0
    //   370: getfield 125	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginCommLock	Ljava/util/concurrent/locks/ReentrantLock;
    //   373: invokevirtual 532	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   376: aload 4
    //   378: monitorexit
    //   379: aconst_null
    //   380: areturn
    //   381: astore_1
    //   382: goto +53 -> 435
    //   385: new 365	java/lang/StringBuilder
    //   388: dup
    //   389: invokespecial 366	java/lang/StringBuilder:<init>	()V
    //   392: astore_1
    //   393: aload_1
    //   394: ldc_w 559
    //   397: invokevirtual 372	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   400: pop
    //   401: aload_1
    //   402: iload_2
    //   403: invokevirtual 541	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   406: pop
    //   407: aload_1
    //   408: ldc_w 561
    //   411: invokevirtual 372	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   414: pop
    //   415: aload_0
    //   416: aload_1
    //   417: invokevirtual 383	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   420: invokevirtual 488	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:handleConnectionBroke	(Ljava/lang/String;)V
    //   423: aload_0
    //   424: getfield 125	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginCommLock	Ljava/util/concurrent/locks/ReentrantLock;
    //   427: invokevirtual 532	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   430: aload 4
    //   432: monitorexit
    //   433: aconst_null
    //   434: areturn
    //   435: aload_0
    //   436: getfield 125	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:mPluginCommLock	Ljava/util/concurrent/locks/ReentrantLock;
    //   439: invokevirtual 532	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   442: aload_1
    //   443: athrow
    //   444: new 494	java/util/concurrent/TimeoutException
    //   447: dup
    //   448: invokespecial 562	java/util/concurrent/TimeoutException:<init>	()V
    //   451: athrow
    //   452: new 365	java/lang/StringBuilder
    //   455: dup
    //   456: invokespecial 366	java/lang/StringBuilder:<init>	()V
    //   459: astore_1
    //   460: aload_1
    //   461: ldc_w 564
    //   464: invokevirtual 372	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   467: pop
    //   468: aload_1
    //   469: iload_2
    //   470: invokevirtual 541	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   473: pop
    //   474: aload_0
    //   475: aload_1
    //   476: invokevirtual 383	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   479: invokevirtual 488	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:handleConnectionBroke	(Ljava/lang/String;)V
    //   482: aload 4
    //   484: monitorexit
    //   485: aconst_null
    //   486: areturn
    //   487: new 365	java/lang/StringBuilder
    //   490: dup
    //   491: invokespecial 366	java/lang/StringBuilder:<init>	()V
    //   494: astore_1
    //   495: aload_1
    //   496: ldc_w 566
    //   499: invokevirtual 372	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   502: pop
    //   503: aload_1
    //   504: iload_2
    //   505: invokevirtual 541	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   508: pop
    //   509: aload_0
    //   510: aload_1
    //   511: invokevirtual 383	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   514: invokevirtual 488	com/dsi/ant/plugins/antplus/pccbase/AntPluginPcc:handleConnectionBroke	(Ljava/lang/String;)V
    //   517: invokestatic 502	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   520: invokevirtual 544	java/lang/Thread:interrupt	()V
    //   523: aload 4
    //   525: monitorexit
    //   526: aconst_null
    //   527: areturn
    //   528: aload 4
    //   530: monitorexit
    //   531: aconst_null
    //   532: areturn
    //   533: astore_1
    //   534: aload 4
    //   536: monitorexit
    //   537: aload_1
    //   538: athrow
    //   539: astore_1
    //   540: goto -53 -> 487
    //   543: astore_1
    //   544: goto -92 -> 452
    //   547: astore_1
    //   548: goto -163 -> 385
    //   551: astore_1
    //   552: goto -219 -> 333
    //   555: astore_1
    //   556: goto -265 -> 291
    //   559: astore_1
    //   560: goto -410 -> 150
    //   563: astore_1
    //   564: goto -455 -> 109
    //   567: astore_1
    //   568: goto -319 -> 249
    //   571: astore_1
    //   572: goto -371 -> 201
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	575	0	this	AntPluginPcc
    //   0	575	1	paramMessage	Message
    //   13	492	2	i	int
    //   45	2	3	bool	boolean
    //   4	531	4	localReentrantLock	ReentrantLock
    //   25	3	5	localMessenger	Messenger
    // Exception table:
    //   from	to	target	type
    //   50	58	381	finally
    //   58	76	381	finally
    //   76	89	381	finally
    //   185	190	381	finally
    //   190	198	381	finally
    //   201	237	381	finally
    //   249	279	381	finally
    //   291	321	381	finally
    //   333	369	381	finally
    //   385	423	381	finally
    //   9	27	533	finally
    //   32	46	533	finally
    //   89	96	533	finally
    //   96	104	533	finally
    //   104	107	533	finally
    //   109	148	533	finally
    //   150	183	533	finally
    //   237	247	533	finally
    //   279	289	533	finally
    //   321	331	533	finally
    //   369	379	533	finally
    //   423	433	533	finally
    //   435	444	533	finally
    //   444	452	533	finally
    //   452	485	533	finally
    //   487	526	533	finally
    //   528	531	533	finally
    //   534	537	533	finally
    //   32	46	539	java/lang/InterruptedException
    //   444	452	539	java/lang/InterruptedException
    //   32	46	543	java/util/concurrent/TimeoutException
    //   444	452	543	java/util/concurrent/TimeoutException
    //   50	58	547	android/os/RemoteException
    //   58	76	547	android/os/RemoteException
    //   76	89	547	android/os/RemoteException
    //   185	190	547	android/os/RemoteException
    //   190	198	547	android/os/RemoteException
    //   201	237	547	android/os/RemoteException
    //   249	279	547	android/os/RemoteException
    //   291	321	547	android/os/RemoteException
    //   333	369	547	android/os/RemoteException
    //   58	76	551	java/lang/InterruptedException
    //   58	76	555	java/util/concurrent/TimeoutException
    //   96	104	559	java/util/concurrent/BrokenBarrierException
    //   96	104	563	java/lang/InterruptedException
    //   190	198	567	java/util/concurrent/BrokenBarrierException
    //   190	198	571	java/lang/InterruptedException
  }
  
  private void sendReleaseCommand(int paramInt)
  {
    try
    {
      synchronized (this.mPluginCommLock)
      {
        if (this.mPluginMsgr == null) {
          break label81;
        }
        this.mPluginMsgr.send(createCmdMsg(paramInt, null));
      }
    }
    catch (RemoteException localRemoteException)
    {
      String str;
      StringBuilder localStringBuilder;
      label81:
      for (;;) {}
    }
    str = TAG;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("RemoteException, unable to cleanly release (cmd ");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(")");
    LogAnt.e(str, localStringBuilder.toString());
    return;
    throw str;
  }
  
  public static boolean startPluginManagerActivity(Activity paramActivity)
  {
    if (getInstalledPluginsVersionNumber(paramActivity) > 0)
    {
      Intent localIntent = new Intent();
      localIntent.setClassName("com.dsi.ant.plugins.antplus", "com.dsi.ant.plugins.antplus.utility.db.Activity_PluginMgrDashboard");
      paramActivity.startActivity(localIntent);
      return true;
    }
    return false;
  }
  
  private void unbindPluginService()
  {
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
          String str = TAG;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Unexpected error unbinding service, ");
          localStringBuilder.append(localIllegalArgumentException);
          LogAnt.e(str, localStringBuilder.toString());
        }
        this.mIsPluginServiceBound = false;
      }
      return;
    }
  }
  
  protected void bindAndRequest(Context paramContext, final Bundle paramBundle, Handler paramHandler)
  {
    this.mOwnerContext = paramContext;
    paramContext = new Messenger(paramHandler);
    paramBundle.putParcelable("msgr_PluginMsgHandler", getPluginMsgReceiver());
    paramBundle.putParcelable("msgr_ReqAccResultReceiver", paramContext);
    LogAnt.setVersion("BBD30600");
    try
    {
      LogAnt.getDebugLevel(this.mOwnerContext.createPackageContext("com.dsi.ant.plugins.antplus", 4));
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramHandler = TAG;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Unable to configure logging, plugins package not found: ");
      ((StringBuilder)localObject).append(paramContext);
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
      paramContext = paramHandler;
      if (!localIterator.hasNext()) {
        break;
      }
      paramContext = (PackageInfo)localIterator.next();
    } while (!paramContext.packageName.equals(((Intent)localObject).getComponent().getPackageName()));
    if (paramContext == null)
    {
      LogAnt.e(TAG, "Binding to plugin failed, not installed");
      sendDependencyNotInstalledMessage((Messenger)paramBundle.getParcelable("msgr_ReqAccResultReceiver"), ((Intent)localObject).getComponent().getPackageName(), "ANT+ Plugins Service");
      return;
    }
    if (paramContext.versionCode < getRequiredServiceVersionForBind())
    {
      LogAnt.e(TAG, "Binding to plugin failed, version requirement not met");
      paramContext = (Messenger)paramBundle.getParcelable("msgr_ReqAccResultReceiver");
      paramBundle = ((Intent)localObject).getComponent().getPackageName();
      paramHandler = new StringBuilder();
      paramHandler.append("ANT+ Plugins Service minimum v.");
      paramHandler.append(getRequiredServiceVersionForBind());
      sendDependencyNotInstalledMessage(paramContext, paramBundle, paramHandler.toString());
      return;
    }
    this.serviceBindConn = new ServiceConnection()
    {
      public void onServiceConnected(ComponentName arg1, IBinder paramAnonymousIBinder)
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
      
      public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
      {
        paramAnonymousComponentName = AntPluginPcc.this;
        if (!paramAnonymousComponentName.isInitialized)
        {
          paramAnonymousComponentName.notifyBindAndRequestFailed(paramBundle);
          return;
        }
        paramAnonymousComponentName.handleConnectionBroke("OnServiceDisconnected fired");
      }
    };
    bindPluginService((Intent)localObject, paramBundle);
  }
  
  protected void closePluginConnection()
  {
    Message localMessage;
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
        Bundle localBundle = new Bundle();
        localBundle.putParcelable("msgr_PluginMsgHandler", getPluginMsgReceiver());
        localBundle.putParcelable("msgr_ReqAccResultReceiver", new Messenger(new Handler(this.mPluginMsgHandlerThread.getLooper())
        {
          public void handleMessage(Message paramAnonymousMessage)
          {
            this.val$latch.countDown();
          }
        }));
        localMessage = Message.obtain();
        localMessage.what = 1;
        localMessage.setData(localBundle);
      }
    }
    try
    {
      ((Messenger)???).send(localMessage);
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
      unbindPluginService();
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
      localObject2 = finally;
      throw localObject2;
    }
    catch (RemoteException|InterruptedException localRemoteException)
    {
      for (;;) {}
    }
  }
  
  <T extends AntPluginPcc> void connectToAsyncResult(AsyncScanController.AsyncScanResultDeviceInfo paramAsyncScanResultDeviceInfo, Messenger paramMessenger, Bundle paramBundle, IDeviceStateChangeReceiver paramIDeviceStateChangeReceiver)
  {
    this.mStateChangeReceiver = paramIDeviceStateChangeReceiver;
    paramIDeviceStateChangeReceiver = paramBundle;
    if (paramBundle == null) {
      paramIDeviceStateChangeReceiver = new Bundle();
    }
    paramIDeviceStateChangeReceiver.putParcelable("parcelable_AsyncScanResultDeviceInfo", paramAsyncScanResultDeviceInfo);
    paramIDeviceStateChangeReceiver.putParcelable("msgr_ReqAccResultReceiver", paramMessenger);
    paramAsyncScanResultDeviceInfo = sendPluginCommand(10100, paramIDeviceStateChangeReceiver);
    if (paramAsyncScanResultDeviceInfo == null)
    {
      LogAnt.e(TAG, "connectToAsyncResult died in sendPluginCommand()");
      paramAsyncScanResultDeviceInfo = Message.obtain();
      paramAsyncScanResultDeviceInfo.what = -4;
    }
    try
    {
      paramMessenger.send(paramAsyncScanResultDeviceInfo);
      return;
    }
    catch (RemoteException paramAsyncScanResultDeviceInfo)
    {
      for (;;) {}
    }
    handleConnectionBroke("Remote exception sending async connect failure msg to client");
    return;
    if (paramAsyncScanResultDeviceInfo.arg1 == 0)
    {
      paramAsyncScanResultDeviceInfo.recycle();
      return;
    }
    paramMessenger = new StringBuilder();
    paramMessenger.append("Request to connectToAsync Result cmd failed with code ");
    paramMessenger.append(paramAsyncScanResultDeviceInfo.arg1);
    throw new RuntimeException(paramMessenger.toString());
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
  
  public int getAntDeviceNumber()
  {
    return this.deviceInfo.antDeviceNumber.intValue();
  }
  
  public DeviceState getCurrentDeviceState()
  {
    return DeviceState.getValueFromInt(this.mCachedState.intValue());
  }
  
  public String getDeviceName()
  {
    return this.deviceInfo.visibleName;
  }
  
  protected abstract String getPluginPrintableName();
  
  protected abstract int getRequiredServiceVersionForBind();
  
  protected abstract Intent getServiceBindIntent();
  
  void handleConnectionBroke(String paramString)
  {
    String str = TAG;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("ConnectionDied: ");
    localStringBuilder.append(paramString);
    LogAnt.w(str, localStringBuilder.toString());
    if (this.mReleaseHandle != null)
    {
      if (this.mReleaseHandle.isClosed) {
        return;
      }
      releaseToken();
      this.mStateChangeReceiver.onDeviceStateChange(DeviceState.DEAD);
    }
  }
  
  protected void handleNonCmdPluginMessage(Message paramMessage)
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
  
  void init(AntPluginDeviceDbProvider.DeviceDbDeviceInfo paramDeviceDbDeviceInfo, UUID paramUUID, Messenger paramMessenger, int paramInt1, int paramInt2)
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
  
  public boolean isUserPreferredDeviceForPlugin()
  {
    return this.deviceInfo.isPreferredDevice.booleanValue() == true;
  }
  
  public boolean isUserRecognizedDevice()
  {
    return this.deviceInfo.device_dbId != null;
  }
  
  public void releaseAccess()
  {
    this.mReleaseHandle.close();
  }
  
  void releaseToken()
  {
    synchronized (this.mPluginCommLock)
    {
      this.mCachedState = Integer.valueOf(-100);
      try
      {
        sendReleaseCommand(10002);
        closePluginConnection();
        return;
      }
      finally
      {
        localObject1 = finally;
        closePluginConnection();
        throw localObject1;
      }
    }
  }
  
  protected Message sendPluginCommand(int paramInt, Bundle paramBundle)
  {
    return sendPluginCommandInternal(createCmdMsg(paramInt, paramBundle));
  }
  
  protected Message sendPluginCommand(Message paramMessage)
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
  
  void stopAsyncScan()
  {
    sendReleaseCommand(10101);
  }
  
  protected boolean subscribeToEvent(int paramInt)
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
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Subscribing to event ");
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
  
  protected void unsubscribeFromEvent(int paramInt)
  {
    Message localMessage = createCmdMsg(10001, null);
    localMessage.arg1 = paramInt;
    Object localObject = sendPluginCommand(localMessage);
    if (localObject == null)
    {
      LogAnt.e(TAG, "unsubscribeFromEvent died in sendPluginCommand()");
      return;
    }
    if (((Message)localObject).arg1 == 0)
    {
      ((Message)localObject).recycle();
      return;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Unsubscribing to event ");
    ((StringBuilder)localObject).append(paramInt);
    ((StringBuilder)localObject).append(" failed with code ");
    ((StringBuilder)localObject).append(localMessage.arg1);
    throw new RuntimeException(((StringBuilder)localObject).toString());
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
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("ReqAcc Handler received: ");
      localStringBuilder.append(paramMessage.what);
      LogAnt.v((String)localObject, localStringBuilder.toString());
      paramMessage.getData().setClassLoader(getClass().getClassLoader());
      if (!handleRequestAccessResult(paramMessage))
      {
        localObject = RequestAccessResult.getValueFromInt(paramMessage.what);
        if (localObject == RequestAccessResult.UNRECOGNIZED)
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Unrecognized return code (need app lib upgrade): ");
          localStringBuilder.append(paramMessage.what);
          localStringBuilder.append("!!!");
          handleRequestAccessFailed(localStringBuilder.toString(), (RequestAccessResult)localObject);
          return;
        }
        handleRequestAccessFailed(((Enum)localObject).toString(), (RequestAccessResult)localObject);
      }
    }
    
    public void handleRequestAccessFailed(String paramString, RequestAccessResult paramRequestAccessResult)
    {
      String str = AntPluginPcc.TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("RequestAccess failed: ");
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
      paramMessage = new StringBuilder();
      paramMessage.append("Missing Dependency: ");
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
  
  protected static class RequestAccessResultHandler_UI<T extends AntPluginPcc>
    extends AntPluginPcc.RequestAccessResultHandler<T>
  {
    private Activity foregroundActivity;
    
    public RequestAccessResultHandler_UI(Activity paramActivity)
    {
      this.foregroundActivity = paramActivity;
    }
    
    public boolean handleRequestAccessResult(Message paramMessage)
    {
      if (paramMessage.what == 1)
      {
        paramMessage = (Intent)paramMessage.getData().getParcelable("intent_ActivityToLaunch");
        if (!this.retPccObject.mReleaseHandle.isClosed) {
          this.foregroundActivity.startActivity(paramMessage);
        }
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
    
    protected void requestCancelled()
    {
      AntPluginPcc.this.closePluginConnection();
    }
  }
}
