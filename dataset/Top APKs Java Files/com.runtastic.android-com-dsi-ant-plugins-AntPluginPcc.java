package com.dsi.ant.plugins;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AntPluginPcc
{
  private static String lastMissingDependencyName = "";
  private static String lastMissingDependencyPkgName = "";
  int ant_DeviceId;
  private boolean connectionBroke = false;
  private final Object connectionBrokeLock = new Object();
  CountDownLatch deviceInitializedLatch = new CountDownLatch(1);
  boolean isInitialized = false;
  UUID mAccessToken;
  Integer mCachedState = null;
  private Thread mCurrentCmdThread;
  String mDeviceName;
  Context mOwnerContext;
  private final ReentrantLock mPluginCommLock = new ReentrantLock();
  Exchanger<Message> mPluginCommMsgExch = new Exchanger();
  CyclicBarrier mPluginCommProcessingBarrier = new CyclicBarrier(2);
  Handler.Callback mPluginMsgHandler = new AntPluginPcc.1(this);
  HandlerThread mPluginMsgHandlerThread = new HandlerThread("PluginPCCMsgHandler");
  Messenger mPluginMsgr;
  ExecutorService mStateChangeExecutor;
  IDeviceStateChangeReceiver mStateChangeReceiver;
  ServiceConnection serviceBindConn;
  
  public AntPluginPcc() {}
  
  private void bindAndRequest(Bundle paramBundle)
  {
    Intent localIntent = getServiceBindIntent();
    Object localObject = this.mOwnerContext.getPackageManager().getInstalledApplications(0).iterator();
    int i;
    if (!((Iterator)localObject).hasNext())
    {
      i = 0;
      label35:
      if (i != 0) {
        break label170;
      }
      Log.e("PluginService", "Binding to plugin failed, not installed");
      paramBundle = (Messenger)paramBundle.getParcelable("msgr_ReqAccResultReceiver");
      localObject = Message.obtain();
      ((Message)localObject).what = -5;
      Bundle localBundle = new Bundle();
      localBundle.putString("string_DependencyPackageName", localIntent.getComponent().getPackageName());
      localBundle.putString("string_DependencyName", getPluginPrintableName());
      ((Message)localObject).setData(localBundle);
    }
    label170:
    do
    {
      try
      {
        paramBundle.send((Message)localObject);
        return;
      }
      catch (RemoteException paramBundle)
      {
        Log.e("PluginPCC", "Remote exception sending plugin not installed msg to client");
        throw new RuntimeException("RemoteException in request access result receiver");
      }
      if (!((ApplicationInfo)((Iterator)localObject).next()).packageName.equals(localIntent.getComponent().getPackageName())) {
        break;
      }
      i = 1;
      break label35;
      this.serviceBindConn = new AntPluginPcc.2(this, paramBundle);
    } while (this.mOwnerContext.bindService(localIntent, this.serviceBindConn, 1));
    Log.e("PluginPCC", "Binding to plugin failed");
    notifyBindAndRequestFailed(paramBundle);
  }
  
  private void closePluginConnection()
  {
    this.mPluginMsgHandlerThread.quit();
    try
    {
      this.mPluginMsgHandlerThread.join(1000L);
      if (this.serviceBindConn != null)
      {
        this.mOwnerContext.unbindService(this.serviceBindConn);
        this.serviceBindConn = null;
      }
      if (this.mStateChangeExecutor != null) {
        this.mStateChangeExecutor.shutdownNow();
      }
      if (this.mPluginCommLock.tryLock())
      {
        this.mPluginCommLock.unlock();
        return;
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        Log.e("AntPluginPcc", "Plugin Msg Handler failed to shut down cleanly");
      }
      this.mCurrentCmdThread.interrupt();
    }
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
    this.mPluginMsgHandlerThread.start();
    return new Messenger(new Handler(this.mPluginMsgHandlerThread.getLooper(), this.mPluginMsgHandler));
  }
  
  private void handleConnectionBroke(String arg1)
  {
    Log.e("AntPluginPcc", "ConnectionDied: " + ???);
    Message localMessage;
    synchronized (this.connectionBrokeLock)
    {
      if (this.connectionBroke) {
        return;
      }
      this.mCachedState = Integer.valueOf(-100);
      if (this.isInitialized)
      {
        localMessage = Message.obtain();
        Bundle localBundle = new Bundle();
        localBundle.putSerializable("uuid_AccessToken", this.mAccessToken);
        localMessage.what = 10002;
        localMessage.setData(localBundle);
      }
    }
    try
    {
      this.mPluginMsgr.send(localMessage);
      this.mStateChangeReceiver.onDeviceStateChange(-100);
      closePluginConnection();
      this.connectionBroke = true;
      return;
      localObject = finally;
      throw localObject;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        Log.e("AntPluginPcc", "Unable to cleanly release access");
      }
    }
  }
  
  private void init(int paramInt1, String paramString, UUID paramUUID, Messenger paramMessenger, int paramInt2)
  {
    this.mDeviceName = paramString;
    this.mAccessToken = paramUUID;
    this.mPluginMsgr = paramMessenger;
    this.ant_DeviceId = paramInt1;
    if (this.mCachedState == null) {
      this.mCachedState = Integer.valueOf(paramInt2);
    }
    this.isInitialized = true;
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
      Log.e("PluginPCC", "Remote exception sending failure msg to client");
      throw new RuntimeException("RemoteException in request access result receiver");
    }
  }
  
  protected static <T extends AntPluginPcc> void requestAccess_Helper(Activity paramActivity, Context paramContext, boolean paramBoolean, int paramInt, T paramT, IPluginAccessResultReceiver<T> paramIPluginAccessResultReceiver, IDeviceStateChangeReceiver paramIDeviceStateChangeReceiver)
  {
    if ((paramInt > 10) || (paramInt < -1)) {
      throw new IllegalArgumentException("searchProximityThreshold was out of range -1 to 10: " + paramInt);
    }
    Bundle localBundle = new Bundle();
    localBundle.putInt("int_RequestAccessMode", 1);
    localBundle.putBoolean("b_ForceManualSelect", paramBoolean);
    localBundle.putInt("int_ProximityBin", paramInt);
    requestAccess_Helper(paramContext, localBundle, paramT, new RequestAccessResultHandlerUI(paramActivity), paramIPluginAccessResultReceiver, paramIDeviceStateChangeReceiver);
  }
  
  protected static <T extends AntPluginPcc> void requestAccess_Helper(Context paramContext, int paramInt1, int paramInt2, T paramT, IPluginAccessResultReceiver<T> paramIPluginAccessResultReceiver, IDeviceStateChangeReceiver paramIDeviceStateChangeReceiver)
  {
    if ((paramInt2 > 10) || (paramInt2 < 0)) {
      throw new IllegalArgumentException("searchProximityThreshold was out of range 0 to 10: " + paramInt2);
    }
    Bundle localBundle = new Bundle();
    localBundle.putInt("int_RequestAccessMode", 3);
    localBundle.putInt("int_AntDeviceID", paramInt1);
    localBundle.putInt("int_ProximityBin", paramInt2);
    requestAccess_Helper(paramContext, localBundle, paramT, new RequestAccessResultHandlerAsyncSearch(), paramIPluginAccessResultReceiver, paramIDeviceStateChangeReceiver);
  }
  
  protected static <T extends AntPluginPcc> void requestAccess_Helper(Context paramContext, Bundle paramBundle, T paramT, RequestAccessResultHandler<T> paramRequestAccessResultHandler, IPluginAccessResultReceiver<T> paramIPluginAccessResultReceiver, IDeviceStateChangeReceiver paramIDeviceStateChangeReceiver)
  {
    String str = paramContext.getPackageName();
    Object localObject = paramContext.getApplicationInfo();
    localObject = paramContext.getPackageManager().getApplicationLabel((ApplicationInfo)localObject).toString();
    paramBundle.putString("str_ApplicationNamePackage", str);
    paramBundle.putString("str_ApplicationNameTitle", (String)localObject);
    paramT.mOwnerContext = paramContext;
    paramT.mStateChangeReceiver = paramIDeviceStateChangeReceiver;
    paramRequestAccessResultHandler.setReturnInfo(paramT, paramIPluginAccessResultReceiver);
    paramContext = new Messenger(paramRequestAccessResultHandler);
    paramBundle.putParcelable("msgr_PluginMsgHandler", paramT.getPluginMsgReceiver());
    paramBundle.putParcelable("msgr_ReqAccResultReceiver", paramContext);
    paramT.bindAndRequest(paramBundle);
  }
  
  private void sendStateUpdate(int paramInt)
  {
    this.mStateChangeReceiver.onDeviceStateChange(paramInt);
    if (paramInt == -100) {
      closePluginConnection();
    }
  }
  
  public static String statusCodeToPrintableString(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return String.valueOf(paramInt + "(Unknown State)");
    case -100: 
      return "Dead";
    case 1: 
      return "Closed";
    case 2: 
      return "Searching";
    case 3: 
      return "Tracking";
    }
    return "Processing Request";
  }
  
  public int getAntDeviceID()
  {
    return this.ant_DeviceId;
  }
  
  public int getCurrentDeviceState()
  {
    return this.mCachedState.intValue();
  }
  
  public String getDeviceName()
  {
    return this.mDeviceName;
  }
  
  protected abstract String getPluginPrintableName();
  
  protected abstract Intent getServiceBindIntent();
  
  protected void handleNonCmdPluginMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    case 2: 
    default: 
      return;
    case 1: 
      handlePluginEvent(paramMessage);
      return;
    }
    int i = paramMessage.arg1;
    this.mCachedState = Integer.valueOf(i);
    Log.e("DBG-PccState", "State event: " + i);
    if (!this.isInitialized)
    {
      Log.e("DBG-PccState", "Queueing state event: " + i);
      if (this.mStateChangeExecutor == null) {
        this.mStateChangeExecutor = Executors.newSingleThreadExecutor();
      }
      paramMessage = new AntPluginPcc.3(this, i);
      this.mStateChangeExecutor.execute(paramMessage);
      return;
    }
    Log.e("DBG-PccState", "Sending state event: " + i);
    sendStateUpdate(i);
  }
  
  protected abstract void handlePluginEvent(Message paramMessage);
  
  public void releaseAccess()
  {
    try
    {
      Message localMessage1 = Message.obtain();
      localMessage1.what = 10002;
      Message localMessage2 = sendPluginCommand(localMessage1);
      if (localMessage2 == null)
      {
        Log.e("PluginPCC", "ReleaseAccess died in sendPluginCommand()");
        return;
      }
      if (localMessage2.arg1 != 0) {
        Log.e("PluginPCC", "ReleaseAccess failed unexpectedly with code " + localMessage1.arg1);
      }
      localMessage2.recycle();
      return;
    }
    finally
    {
      closePluginConnection();
    }
  }
  
  protected Message sendPluginCommand(Message paramMessage)
  {
    synchronized (this.mPluginCommLock)
    {
      this.mCurrentCmdThread = Thread.currentThread();
      Bundle localBundle2 = paramMessage.getData();
      Bundle localBundle1 = localBundle2;
      if (localBundle2 == null) {
        localBundle1 = new Bundle();
      }
      localBundle1.putSerializable("uuid_AccessToken", this.mAccessToken);
      paramMessage.setData(localBundle1);
      try
      {
        if (!this.mPluginCommLock.tryLock(7000L, TimeUnit.MILLISECONDS)) {
          throw new TimeoutException();
        }
      }
      catch (InterruptedException localInterruptedException1)
      {
        handleConnectionBroke("InterruptedException obtaining mPluginCommLock in sendPluginCommand on message " + paramMessage.what);
        Thread.currentThread().interrupt();
        return null;
      }
      catch (TimeoutException localTimeoutException1)
      {
        handleConnectionBroke("TimeoutException obtaining mPluginCommLock in sendPluginCommand on message " + paramMessage.what);
        return null;
      }
      try
      {
        this.mPluginMsgr.send(paramMessage);
        for (;;)
        {
          try
          {
            Message localMessage = (Message)this.mPluginCommMsgExch.exchange(null, 5L, TimeUnit.SECONDS);
            if (localMessage.what == paramMessage.what)
            {
              localMessage = Message.obtain(localMessage);
              this.mPluginCommLock.unlock();
            }
            handleNonCmdPluginMessage(localInterruptedException3);
          }
          catch (InterruptedException localInterruptedException2)
          {
            try
            {
              this.mPluginCommProcessingBarrier.await();
              return localMessage;
            }
            catch (BrokenBarrierException localBrokenBarrierException1)
            {
              handleConnectionBroke("BrokenBarrierException in sendPluginCommand finally on message " + paramMessage.what);
              return null;
            }
            catch (InterruptedException localInterruptedException3)
            {
              handleConnectionBroke("InterruptedException in sendPluginCommand finally on message " + paramMessage.what);
              Thread.currentThread().interrupt();
              return null;
            }
            localInterruptedException2 = localInterruptedException2;
            handleConnectionBroke("InterruptedException in sendPluginCommand (at mPluginCommMsgExch.exchange()) on message " + paramMessage.what);
            Thread.currentThread().interrupt();
            this.mPluginCommLock.unlock();
            return null;
          }
          catch (TimeoutException localTimeoutException2)
          {
            handleConnectionBroke("TimeoutException in sendPluginCommand (at mPluginCommMsgExch.exchange()) on message " + paramMessage.what);
            this.mPluginCommLock.unlock();
            return null;
          }
          try
          {
            this.mPluginCommProcessingBarrier.await();
          }
          catch (BrokenBarrierException localBrokenBarrierException2)
          {
            handleConnectionBroke("BrokenBarrierException in sendPluginCommand (at non-success mPluginCommProcessingBarrier) on message " + paramMessage.what);
            this.mPluginCommLock.unlock();
            return null;
          }
          catch (InterruptedException localInterruptedException4)
          {
            handleConnectionBroke("InterruptedException in sendPluginCommand (at non-success mPluginCommProcessingBarrier) on message " + paramMessage.what);
            Thread.currentThread().interrupt();
            this.mPluginCommLock.unlock();
            return null;
          }
        }
        paramMessage = finally;
      }
      catch (RemoteException localRemoteException)
      {
        handleConnectionBroke("RemoteException sending message " + paramMessage.what + " to plugin");
        this.mPluginCommLock.unlock();
        return null;
      }
      finally
      {
        this.mPluginCommLock.unlock();
      }
    }
  }
  
  protected boolean subscribeToEvent(int paramInt)
  {
    Message localMessage = Message.obtain();
    localMessage.what = 10000;
    localMessage.arg1 = paramInt;
    localMessage = sendPluginCommand(localMessage);
    if (localMessage == null)
    {
      Log.e("PluginPCC", "subscribeToEvent died in sendPluginCommand()");
      return false;
    }
    if (localMessage.arg1 != 0)
    {
      Log.e("PluginPCC", "Subscribing to event " + paramInt + " failed with code " + localMessage.arg1);
      return false;
    }
    localMessage.recycle();
    return true;
  }
  
  protected void unsubscribeFromEvent(int paramInt)
  {
    Message localMessage1 = Message.obtain();
    localMessage1.what = 10001;
    localMessage1.arg1 = paramInt;
    Message localMessage2 = sendPluginCommand(localMessage1);
    if (localMessage2 == null)
    {
      Log.e("PluginPCC", "unsubscribeFromEvent died in sendPluginCommand()");
      return;
    }
    if (localMessage2.arg1 != 0)
    {
      Log.e("PluginPCC", "Unsubscribing to event " + paramInt + " failed with code " + localMessage1.arg1);
      throw new RuntimeException("Unsubscribing to event failed internally");
    }
    localMessage2.recycle();
  }
  
  public static abstract interface IDeviceStateChangeReceiver
  {
    public abstract void onDeviceStateChange(int paramInt);
  }
  
  public static abstract interface IPluginAccessResultReceiver<T extends AntPluginPcc>
  {
    public abstract void onResultReceived(T paramT, int paramInt1, int paramInt2);
  }
  
  protected static class RequestAccessResultHandler<T extends AntPluginPcc>
    extends Handler
  {
    protected AntPluginPcc.IPluginAccessResultReceiver<T> resultReceiver;
    protected T retPccObject;
    
    public RequestAccessResultHandler() {}
    
    public void handleMessage(Message paramMessage)
    {
      Log.v("PluginPCC", "ReqAcc Handler received: " + paramMessage.what);
      if (!handleRequestAccessResult(paramMessage))
      {
        Log.e("PluginPCC", "Unhandled requestAccess returnCode: " + paramMessage.what + "!!!");
        this.retPccObject.closePluginConnection();
        this.resultReceiver.onResultReceived(null, paramMessage.what, -100);
      }
    }
    
    public boolean handleRequestAccessResult(Message paramMessage)
    {
      int i = paramMessage.what;
      switch (i)
      {
      case -1: 
      default: 
        return false;
      case 0: 
        Messenger localMessenger = (Messenger)paramMessage.getData().get("msgr_PluginComm");
        UUID localUUID = (UUID)paramMessage.getData().get("uuid_AccessToken");
        String str = paramMessage.getData().getString("str_DeviceName");
        int j = paramMessage.getData().getInt("int_InitialDeviceStateCode");
        int k = paramMessage.getData().getInt("int_AntDeviceID", -1);
        Log.e("DBG-PccState", "Initial state: " + j);
        this.retPccObject.init(k, str, localUUID, localMessenger, j);
        this.resultReceiver.onResultReceived(this.retPccObject, i, j);
        this.retPccObject.deviceInitializedLatch.countDown();
        return true;
      case -5: 
        paramMessage = paramMessage.getData();
        AntPluginPcc.lastMissingDependencyPkgName = paramMessage.getString("string_DependencyPackageName");
        AntPluginPcc.lastMissingDependencyName = paramMessage.getString("string_DependencyName");
        Log.e("PluginPCC", "requestAccess failed, " + AntPluginPcc.lastMissingDependencyPkgName + " not installed.");
        this.retPccObject.closePluginConnection();
        this.resultReceiver.onResultReceived(null, i, -100);
        return true;
      case -4: 
        Log.e("PluginPCC", "requestAccess failed");
        this.retPccObject.closePluginConnection();
        this.resultReceiver.onResultReceived(null, i, -100);
        return true;
      case -2: 
        Log.v("PluginPCC", "User Cancelled requestAccesss");
        this.retPccObject.closePluginConnection();
        this.resultReceiver.onResultReceived(null, i, -100);
        return true;
      }
      Log.d("PluginPCC", "requestAccess: channel not available");
      this.retPccObject.closePluginConnection();
      this.resultReceiver.onResultReceived(null, i, -100);
      return true;
    }
    
    void setReturnInfo(T paramT, AntPluginPcc.IPluginAccessResultReceiver<T> paramIPluginAccessResultReceiver)
    {
      this.retPccObject = paramT;
      this.resultReceiver = paramIPluginAccessResultReceiver;
    }
  }
  
  protected static class RequestAccessResultHandlerAsyncSearch<T extends AntPluginPcc>
    extends AntPluginPcc.RequestAccessResultHandler<T>
  {
    protected RequestAccessResultHandlerAsyncSearch() {}
    
    public boolean handleRequestAccessResult(Message paramMessage)
    {
      if (paramMessage.what == -7)
      {
        Log.v("PluginPCC", "RequestAccess: Search for device timed out.");
        this.retPccObject.closePluginConnection();
        this.resultReceiver.onResultReceived(null, paramMessage.what, -100);
        return true;
      }
      return super.handleRequestAccessResult(paramMessage);
    }
  }
  
  protected static class RequestAccessResultHandlerUI<T extends AntPluginPcc>
    extends AntPluginPcc.RequestAccessResultHandler<T>
  {
    private Activity foregroundActivity;
    
    public RequestAccessResultHandlerUI(Activity paramActivity)
    {
      this.foregroundActivity = paramActivity;
    }
    
    public boolean handleRequestAccessResult(Message paramMessage)
    {
      if (paramMessage.what == 1)
      {
        paramMessage = (Intent)paramMessage.getData().getParcelable("intent_ActivityToLaunch");
        this.foregroundActivity.startActivity(paramMessage);
        return true;
      }
      return super.handleRequestAccessResult(paramMessage);
    }
  }
}
