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
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AntPluginPcc
{
  private static final long CLOSE_CONNECTION_TIMEOUT_MS = 500L;
  public static final String PATH_ANTPLUS_PLUGINS_PKG = "com.dsi.ant.plugins.antplus";
  private static final String TAG = AntPluginPcc.class.getSimpleName();
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
      PccReleaseHandle localPccReleaseHandle = AntPluginPcc.this.mReleaseHandle;
      if (localPccReleaseHandle == null) {
        return true;
      }
      try
      {
        AntPluginPcc.this.deviceInitializedLatch.await();
        synchronized (localPccReleaseHandle.stateLock)
        {
          if (!localPccReleaseHandle.isActive()) {
            return true;
          }
        }
        switch (paramAnonymousMessage.what)
        {
        }
      }
      catch (InterruptedException paramAnonymousMessage)
      {
        LogAnt.i(AntPluginPcc.TAG, "Plugin event thread interrupted while waiting for initialization to complete.");
        Thread.currentThread().interrupt();
        return true;
      }
      for (;;)
      {
        LogAnt.w(AntPluginPcc.TAG, "Unrecognized plugin event received: " + paramAnonymousMessage.arg1);
        for (;;)
        {
          return true;
          AntPluginPcc.this.handlePluginEvent(paramAnonymousMessage);
          continue;
          int i = paramAnonymousMessage.arg1;
          AntPluginPcc.this.mCachedState = Integer.valueOf(i);
          LogAnt.v(AntPluginPcc.TAG, "State event: " + i);
          if (i == -100) {
            AntPluginPcc.this.handleConnectionBroke("Device dead");
          } else {
            AntPluginPcc.this.mStateChangeReceiver.onDeviceStateChange(DeviceState.getValueFromInt(i));
          }
        }
      }
    }
  };
  HandlerThread mPluginEventHandlerThread = new HandlerThread("PluginPCCEventHandler");
  volatile Handler mPluginMsgHandler;
  Handler.Callback mPluginMsgHandlerCb = new Handler.Callback()
  {
    public boolean handleMessage(Message paramAnonymousMessage)
    {
      LogAnt.v(AntPluginPcc.TAG, "Plugin Msg Handler received: " + paramAnonymousMessage.what + ", " + paramAnonymousMessage.arg1);
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
        AntPluginPcc.this.handleConnectionBroke("BrokenBarrierException in mPluginMsgHandler trying to fwd message " + paramAnonymousMessage.what);
        return true;
      }
      catch (InterruptedException localInterruptedException)
      {
        AntPluginPcc.this.handleConnectionBroke("InterruptedException in mPluginMsgHandler trying to fwd message " + paramAnonymousMessage.what);
        Thread.currentThread().interrupt();
      }
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
      LogAnt.e(TAG, "Remote exception sending failure msg to client");
    }
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
    if ((paramIPluginAccessResultReceiver == null) || (paramIDeviceStateChangeReceiver == null))
    {
      paramBundle = new StringBuilder().append("Invalid argument: ");
      if (paramIPluginAccessResultReceiver == null) {}
      for (paramContext = "resultReceiver ";; paramContext = "stateReceiver ") {
        throw new IllegalArgumentException(paramContext + " is null ");
      }
    }
    paramT.getClass();
    paramIPluginAccessResultReceiver = new StandardReleaseHandle(paramT, paramIPluginAccessResultReceiver, paramIDeviceStateChangeReceiver);
    paramT.mReleaseHandle = paramIPluginAccessResultReceiver;
    paramT.mStateChangeReceiver = paramIPluginAccessResultReceiver.stateSink;
    paramRequestAccessResultHandler.setReturnInfo(paramT, paramIPluginAccessResultReceiver.resultSink);
    requestAccess_Helper_SubMain(paramContext, paramBundle, paramT, paramRequestAccessResultHandler);
    return paramIPluginAccessResultReceiver;
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
  
  protected static <T extends AntPluginPcc> AsyncScanController<T> requestAsyncScan_Helper_SubMain(Context paramContext, int paramInt, Bundle paramBundle, T paramT, AsyncScanController<T> paramAsyncScanController)
  {
    if (getInstalledPluginsVersionNumber(paramContext) < 10800)
    {
      LogAnt.e(TAG, "Binding to plugin failed, version requirement not met for async scan controller mode");
      lastMissingDependencyPkgName = paramT.getServiceBindIntent().getComponent().getPackageName();
      lastMissingDependencyName = paramT.getPluginPrintableName() + " minimum v.10800";
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
      handleConnectionBroke("Remote exception sending plugin 'dependency not installed' msg to client");
    }
  }
  
  private Message sendPluginCommandInternal(Message paramMessage)
  {
    int i;
    synchronized (this.mPluginCommLock)
    {
      i = paramMessage.what;
      this.mCurrentCmdThread = Thread.currentThread();
      Messenger localMessenger = this.mPluginMsgr;
      if (localMessenger == null) {
        break label929;
      }
      try
      {
        if (!this.mPluginCommLock.tryLock(7000L, TimeUnit.MILLISECONDS)) {
          throw new TimeoutException();
        }
      }
      catch (InterruptedException paramMessage)
      {
        handleConnectionBroke("InterruptedException obtaining mPluginCommLock in sendPluginCommand on message " + i);
        Thread.currentThread().interrupt();
        return null;
      }
      catch (TimeoutException paramMessage)
      {
        handleConnectionBroke("TimeoutException obtaining mPluginCommLock in sendPluginCommand on message " + i);
        return null;
      }
    }
    for (;;)
    {
      try
      {
        this.mPluginMsgr.send(paramMessage);
        try
        {
          paramMessage = (Message)this.mPluginCommMsgExch.exchange(null, 5L, TimeUnit.SECONDS);
          if (paramMessage.what != i) {
            continue;
          }
          paramMessage = Message.obtain(paramMessage);
          this.mPluginCommLock.unlock();
          if (1 == 0) {}
        }
        catch (InterruptedException paramMessage)
        {
          handleConnectionBroke("InterruptedException in sendPluginCommand (at mPluginCommMsgExch.exchange()) on message " + i);
          Thread.currentThread().interrupt();
          this.mPluginCommLock.unlock();
          if (0 != 0) {}
          try
          {
            this.mPluginCommProcessingBarrier.await();
            return null;
          }
          catch (BrokenBarrierException paramMessage)
          {
            handleConnectionBroke("BrokenBarrierException in sendPluginCommand finally on message " + i);
            return null;
          }
          catch (InterruptedException paramMessage)
          {
            handleConnectionBroke("InterruptedException in sendPluginCommand finally on message " + i);
            Thread.currentThread().interrupt();
            return null;
          }
        }
        catch (TimeoutException paramMessage)
        {
          handleConnectionBroke("TimeoutException in sendPluginCommand (at mPluginCommMsgExch.exchange()) on message " + i);
          this.mPluginCommLock.unlock();
          if (0 != 0) {}
          try
          {
            this.mPluginCommProcessingBarrier.await();
            return null;
          }
          catch (BrokenBarrierException paramMessage)
          {
            handleConnectionBroke("BrokenBarrierException in sendPluginCommand finally on message " + i);
            return null;
          }
          catch (InterruptedException paramMessage)
          {
            handleConnectionBroke("InterruptedException in sendPluginCommand finally on message " + i);
            Thread.currentThread().interrupt();
            return null;
          }
        }
        try
        {
          this.mPluginCommProcessingBarrier.await();
          return paramMessage;
        }
        catch (BrokenBarrierException paramMessage)
        {
          handleConnectionBroke("BrokenBarrierException in sendPluginCommand finally on message " + i);
          return null;
        }
        catch (InterruptedException paramMessage)
        {
          handleConnectionBroke("InterruptedException in sendPluginCommand finally on message " + i);
          Thread.currentThread().interrupt();
          return null;
        }
        paramMessage = finally;
        throw paramMessage;
      }
      catch (RemoteException paramMessage)
      {
        handleConnectionBroke("RemoteException sending message " + i + " to plugin");
        this.mPluginCommLock.unlock();
        if (0 == 0) {
          continue;
        }
        try
        {
          this.mPluginCommProcessingBarrier.await();
          return null;
        }
        catch (BrokenBarrierException paramMessage)
        {
          handleConnectionBroke("BrokenBarrierException in sendPluginCommand finally on message " + i);
          return null;
        }
        catch (InterruptedException paramMessage)
        {
          handleConnectionBroke("InterruptedException in sendPluginCommand finally on message " + i);
          Thread.currentThread().interrupt();
          return null;
        }
      }
      finally
      {
        this.mPluginCommLock.unlock();
        if (0 == 0) {
          continue;
        }
        try
        {
          this.mPluginCommProcessingBarrier.await();
          throw paramMessage;
        }
        catch (BrokenBarrierException paramMessage)
        {
          handleConnectionBroke("BrokenBarrierException in sendPluginCommand finally on message " + i);
          return null;
        }
        catch (InterruptedException paramMessage)
        {
          handleConnectionBroke("InterruptedException in sendPluginCommand finally on message " + i);
          Thread.currentThread().interrupt();
          return null;
        }
      }
      handleNonCmdPluginMessage(paramMessage);
      try
      {
        this.mPluginCommProcessingBarrier.await();
      }
      catch (BrokenBarrierException paramMessage)
      {
        handleConnectionBroke("BrokenBarrierException in sendPluginCommand (at non-success mPluginCommProcessingBarrier) on message " + i);
        this.mPluginCommLock.unlock();
        if (0 != 0) {}
        try
        {
          this.mPluginCommProcessingBarrier.await();
          return null;
        }
        catch (BrokenBarrierException paramMessage)
        {
          handleConnectionBroke("BrokenBarrierException in sendPluginCommand finally on message " + i);
          return null;
        }
        catch (InterruptedException paramMessage)
        {
          handleConnectionBroke("InterruptedException in sendPluginCommand finally on message " + i);
          Thread.currentThread().interrupt();
          return null;
        }
      }
      catch (InterruptedException paramMessage)
      {
        handleConnectionBroke("InterruptedException in sendPluginCommand (at non-success mPluginCommProcessingBarrier) on message " + i);
        Thread.currentThread().interrupt();
        this.mPluginCommLock.unlock();
        if (0 != 0) {}
        try
        {
          this.mPluginCommProcessingBarrier.await();
          return null;
        }
        catch (BrokenBarrierException paramMessage)
        {
          handleConnectionBroke("BrokenBarrierException in sendPluginCommand finally on message " + i);
          return null;
        }
        catch (InterruptedException paramMessage)
        {
          handleConnectionBroke("InterruptedException in sendPluginCommand finally on message " + i);
          Thread.currentThread().interrupt();
          return null;
        }
      }
    }
    label929:
    return null;
  }
  
  private void sendReleaseCommand(int paramInt)
  {
    synchronized (this.mPluginCommLock)
    {
      try
      {
        if (this.mPluginMsgr != null) {
          this.mPluginMsgr.send(createCmdMsg(paramInt, null));
        }
        return;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          LogAnt.e(TAG, "RemoteException, unable to cleanly release (cmd " + paramInt + ")");
        }
      }
    }
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
      if (bool) {}
      try
      {
        this.mOwnerContext.unbindService(this.serviceBindConn);
        this.mIsPluginServiceBound = false;
        return;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        for (;;)
        {
          LogAnt.e(TAG, "Unexpected error unbinding service, " + localIllegalArgumentException);
        }
      }
    }
  }
  
  protected void bindAndRequest(Context paramContext, final Bundle paramBundle, Handler paramHandler)
  {
    this.mOwnerContext = paramContext;
    paramContext = new Messenger(paramHandler);
    paramBundle.putParcelable("msgr_PluginMsgHandler", getPluginMsgReceiver());
    paramBundle.putParcelable("msgr_ReqAccResultReceiver", paramContext);
    LogAnt.setVersion("BBD30640");
    try
    {
      LogAnt.getDebugLevel(this.mOwnerContext.createPackageContext("com.dsi.ant.plugins.antplus", 4));
      paramBundle.putInt("int_PluginLibVersion", 30640);
      paramBundle.putString("string_PluginLibVersion", "3.6.40");
      paramBundle.putInt("more", 1);
      localIntent = getServiceBindIntent();
      paramHandler = null;
      Iterator localIterator = this.mOwnerContext.getPackageManager().getInstalledPackages(0).iterator();
      do
      {
        paramContext = paramHandler;
        if (!localIterator.hasNext()) {
          break;
        }
        paramContext = (PackageInfo)localIterator.next();
      } while (!paramContext.packageName.equals(localIntent.getComponent().getPackageName()));
      if (paramContext == null)
      {
        LogAnt.e(TAG, "Binding to plugin failed, not installed");
        sendDependencyNotInstalledMessage((Messenger)paramBundle.getParcelable("msgr_ReqAccResultReceiver"), localIntent.getComponent().getPackageName(), "ANT+ Plugins Service");
        return;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Intent localIntent;
      for (;;)
      {
        LogAnt.e(TAG, "Unable to configure logging, plugins package not found: " + paramContext);
      }
      if (paramContext.versionCode < getRequiredServiceVersionForBind())
      {
        LogAnt.e(TAG, "Binding to plugin failed, version requirement not met");
        sendDependencyNotInstalledMessage((Messenger)paramBundle.getParcelable("msgr_ReqAccResultReceiver"), localIntent.getComponent().getPackageName(), "ANT+ Plugins Service minimum v." + getRequiredServiceVersionForBind());
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
          }
          try
          {
            AntPluginPcc.this.mReqAccessMessenger.send(paramAnonymousIBinder);
            return;
            paramAnonymousIBinder = finally;
            throw paramAnonymousIBinder;
          }
          catch (RemoteException paramAnonymousIBinder)
          {
            for (;;)
            {
              AntPluginPcc.this.notifyBindAndRequestFailed(paramBundle);
            }
          }
        }
        
        public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
        {
          if (!AntPluginPcc.this.isInitialized)
          {
            AntPluginPcc.this.notifyBindAndRequestFailed(paramBundle);
            return;
          }
          AntPluginPcc.this.handleConnectionBroke("OnServiceDisconnected fired");
        }
      };
      bindPluginService(localIntent, paramBundle);
    }
  }
  
  protected void closePluginConnection()
  {
    Object localObject5;
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
        if (this.mOwnerContext != null)
        {
          localObject5 = this.mOwnerContext.getPackageName();
          Object localObject6 = this.mOwnerContext.getApplicationInfo();
          localObject6 = this.mOwnerContext.getPackageManager().getApplicationLabel((ApplicationInfo)localObject6).toString();
          localBundle.putString("str_ApplicationNamePackage", (String)localObject5);
          localBundle.putString("str_ApplicationNameTitle", (String)localObject6);
        }
        localBundle.putInt("int_PluginLibVersion", 30640);
        localBundle.putString("string_PluginLibVersion", "3.6.40");
        localObject5 = Message.obtain();
        ((Message)localObject5).what = 1;
        ((Message)localObject5).setData(localBundle);
      }
    }
    try
    {
      ((Messenger)???).send((Message)localObject5);
      ((CountDownLatch)???).await(500L, TimeUnit.MILLISECONDS);
      this.mPluginMsgHandlerThread.quit();
      try
      {
        this.mPluginMsgHandlerThread.join(1000L);
        this.mPluginEventHandler = null;
        this.mPluginEventHandlerThread.quit();
      }
      catch (InterruptedException localInterruptedException1)
      {
        try
        {
          this.mPluginEventHandlerThread.join(1000L);
          unbindPluginService();
          if (this.mPluginCommLock.tryLock())
          {
            this.mPluginCommLock.unlock();
            synchronized (this.mPluginCommLock)
            {
              this.mPluginMsgr = null;
              return;
            }
            localObject2 = finally;
            throw localObject2;
            localInterruptedException1 = localInterruptedException1;
            LogAnt.e(TAG, "Plugin Msg Handler thread failed to shut down cleanly, InterruptedException");
            Thread.currentThread().interrupt();
          }
        }
        catch (InterruptedException localInterruptedException2)
        {
          for (;;)
          {
            LogAnt.e(TAG, "Plugin Event Handler thread failed to shut down cleanly, InterruptedException");
            Thread.currentThread().interrupt();
            continue;
            this.mCurrentCmdThread.interrupt();
          }
        }
      }
    }
    catch (InterruptedException localInterruptedException3)
    {
      for (;;) {}
    }
    catch (RemoteException localRemoteException)
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
      try
      {
        paramMessenger.send(paramAsyncScanResultDeviceInfo);
        return;
      }
      catch (RemoteException paramAsyncScanResultDeviceInfo)
      {
        handleConnectionBroke("Remote exception sending async connect failure msg to client");
        return;
      }
    }
    if (paramAsyncScanResultDeviceInfo.arg1 != 0) {
      throw new RuntimeException("Request to connectToAsync Result cmd failed with code " + paramAsyncScanResultDeviceInfo.arg1);
    }
    paramAsyncScanResultDeviceInfo.recycle();
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
    LogAnt.w(TAG, "ConnectionDied: " + paramString);
    if ((this.mReleaseHandle == null) || (this.mReleaseHandle.isClosed)) {
      return;
    }
    releaseToken();
    this.mStateChangeReceiver.onDeviceStateChange(DeviceState.DEAD);
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
      LogAnt.e(TAG, "Subscribing to event " + paramInt + " failed with code " + localMessage.arg1);
      localMessage.recycle();
      return false;
    }
    localMessage.recycle();
    return true;
  }
  
  protected void unsubscribeFromEvent(int paramInt)
  {
    Message localMessage1 = createCmdMsg(10001, null);
    localMessage1.arg1 = paramInt;
    Message localMessage2 = sendPluginCommand(localMessage1);
    if (localMessage2 == null)
    {
      LogAnt.e(TAG, "unsubscribeFromEvent died in sendPluginCommand()");
      return;
    }
    if (localMessage2.arg1 != 0) {
      throw new RuntimeException("Unsubscribing to event " + paramInt + " failed with code " + localMessage1.arg1);
    }
    localMessage2.recycle();
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
      LogAnt.v(AntPluginPcc.TAG, "ReqAcc Handler received: " + paramMessage.what);
      paramMessage.getData().setClassLoader(getClass().getClassLoader());
      RequestAccessResult localRequestAccessResult;
      if (!handleRequestAccessResult(paramMessage))
      {
        localRequestAccessResult = RequestAccessResult.getValueFromInt(paramMessage.what);
        if (localRequestAccessResult == RequestAccessResult.UNRECOGNIZED) {
          handleRequestAccessFailed("Unrecognized return code (need app lib upgrade): " + paramMessage.what + "!!!", localRequestAccessResult);
        }
      }
      else
      {
        return;
      }
      handleRequestAccessFailed(localRequestAccessResult.toString(), localRequestAccessResult);
    }
    
    public void handleRequestAccessFailed(String paramString, RequestAccessResult paramRequestAccessResult)
    {
      LogAnt.w(AntPluginPcc.TAG, "RequestAccess failed: " + paramString);
      this.retPccObject.releaseToken();
      this.resultReceiver.onResultReceived(null, paramRequestAccessResult, DeviceState.DEAD);
    }
    
    public boolean handleRequestAccessResult(Message paramMessage)
    {
      int i = paramMessage.what;
      switch (i)
      {
      default: 
        return false;
      case 0: 
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
      handleRequestAccessFailed("Missing Dependency: " + AntPluginPcc.lastMissingDependencyPkgName + " not installed.", RequestAccessResult.DEPENDENCY_NOT_INSTALLED);
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
