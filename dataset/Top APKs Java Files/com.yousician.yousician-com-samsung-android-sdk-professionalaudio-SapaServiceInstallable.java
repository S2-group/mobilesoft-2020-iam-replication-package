package com.samsung.android.sdk.professionalaudio;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.AndroidRuntimeException;
import android.util.Log;
import com.samsung.sprofessionalaudioservice.IApaService;
import com.samsung.sprofessionalaudioservice.IApaService.Stub;
import com.samsung.sprofessionalaudioservice.IApaServiceStatusListener;
import com.samsung.sprofessionalaudioservice.IApaServiceStatusListener.Stub;
import java.io.File;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;

final class SapaServiceInstallable
  implements ab
{
  private static final String TAG = "SAPA";
  private WeakReference<IApaService> mApaService;
  private WeakReference<Context> mContext;
  private IApaService mIApaService;
  
  public SapaServiceInstallable(Context paramContext)
  {
    Log.i("SAPA", "SapaServiceInstallable creating");
    try
    {
      System.loadLibrary("gnustl_shared");
      System.loadLibrary("SapaServiceJni");
      this.mApaService = null;
      this.mContext = new WeakReference(paramContext);
      return;
    }
    catch (UnsatisfiedLinkError paramContext)
    {
      paramContext.printStackTrace();
      Log.i("SAPA", paramContext.getMessage());
      throw new AndroidRuntimeException("Fail to load JNI of Sapa Service. Please check whether the libSapaServiceJni.so is located in your apk.");
    }
  }
  
  public static int getFrameworkVersionCode()
  {
    return 5;
  }
  
  private void loadJack3Lib()
  {
    Iterator localIterator = Sapa.c().getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (localApplicationInfo.packageName.equals("com.samsung.sprofessionalaudioservice") == true)
      {
        Log.d("TAG", "NativeLibraryDir of [" + localApplicationInfo.packageName + "] : " + localApplicationInfo.nativeLibraryDir);
        if (new File(localApplicationInfo.nativeLibraryDir).canRead()) {
          nativeLoadJackLib(new String(localApplicationInfo.nativeLibraryDir + "/libjack3.so"));
        }
      }
    }
  }
  
  private String makeFalseResult()
  {
    return new String("[{\"result\":\"0\"}]");
  }
  
  private String makeTrueResult()
  {
    return new String("[{\"result\":\"1\"}]");
  }
  
  private static native int nativeDeleteClient(String paramString);
  
  static native String nativeGetJackClientName(String paramString1, String paramString2, int paramInt);
  
  static native int nativeGetNativeVersionCode();
  
  static native int nativeGetTargetAPILevel();
  
  private static native int nativeIsAPILevelAllowed(int paramInt);
  
  static native int nativeIsFeatureSupport(int paramInt);
  
  static native int nativeLoadJackLib(String paramString);
  
  static native ByteBuffer nativeRequest(int paramInt, String paramString, long paramLong1, long paramLong2);
  
  static native int nativeSendCommand(int paramInt, String paramString);
  
  static native int nativeSendStream(int paramInt1, String paramString, ByteBuffer paramByteBuffer, int paramInt2);
  
  private final native int nativeSetup(Object paramObject, String paramString, int paramInt);
  
  static native int nativeUnoadJackLib();
  
  static void postDataEventFromNative(Object paramObject, int paramInt1, int paramInt2, int paramInt3, ByteBuffer paramByteBuffer)
  {
    SapaServiceInternal.postDataEventFromNative(new WeakReference(SapaService.mInstance), paramInt1, paramInt2, paramInt3, paramByteBuffer);
  }
  
  static void postEventFromNative(Object paramObject1, int paramInt1, int paramInt2, int paramInt3, Object paramObject2)
  {
    SapaServiceInternal.postEventFromNative(new WeakReference(SapaService.mInstance), paramInt1, paramInt2, paramInt3, paramObject2);
  }
  
  private boolean setPowerSavingPermissionEnabled(SapaProcessor paramSapaProcessor, boolean paramBoolean)
  {
    return false;
  }
  
  public String IsAudioUSBDeviceAttached(String paramString)
  {
    return makeFalseResult();
  }
  
  public String activateClient(String paramString)
  {
    try
    {
      if ((this.mApaService == null) || (this.mApaService.get() == null)) {
        throw new AndroidRuntimeException("Service is not ready. Please keep binding with IApaService and set a vaild interface in the SapaService.setApaService()");
      }
    }
    catch (RemoteException paramString)
    {
      Log.e("SAPA", "Failed, " + paramString.getMessage());
      return makeFalseResult();
    }
    paramString = ((IApaService)this.mApaService.get()).activateClient(paramString);
    return paramString;
  }
  
  public String callGetJackClientName(String paramString1, String paramString2, int paramInt)
  {
    return nativeGetJackClientName(paramString1, paramString2, paramInt);
  }
  
  public int callGetTargetAPILevel()
  {
    return nativeGetTargetAPILevel();
  }
  
  public int callIsFeatureSupport(int paramInt)
  {
    return nativeIsFeatureSupport(paramInt);
  }
  
  public ByteBuffer callRequest(int paramInt, String paramString, long paramLong1, long paramLong2)
  {
    return nativeRequest(paramInt, paramString, paramLong1, paramLong2);
  }
  
  public int callSendCommand(int paramInt, String paramString)
  {
    return nativeSendCommand(paramInt, paramString);
  }
  
  public int callSendStream(int paramInt1, String paramString, ByteBuffer paramByteBuffer, int paramInt2)
  {
    return nativeSendStream(paramInt1, paramString, paramByteBuffer, paramInt2);
  }
  
  public String changeDevice(String paramString)
  {
    return makeFalseResult();
  }
  
  public String connectPort(String paramString)
  {
    try
    {
      if ((this.mApaService == null) || (this.mApaService.get() == null)) {
        throw new AndroidRuntimeException("Service is not ready. Please keep binding with IApaService and set a vaild interface in the SapaService.setApaService()");
      }
    }
    catch (RemoteException paramString)
    {
      Log.e("SAPA", "Failed, " + paramString.getMessage());
      return makeFalseResult();
    }
    paramString = ((IApaService)this.mApaService.get()).connectPort(paramString);
    return paramString;
  }
  
  public String deactivateClient(String paramString)
  {
    try
    {
      if ((this.mApaService == null) || (this.mApaService.get() == null)) {
        throw new AndroidRuntimeException("Service is not ready. Please keep binding with IApaService and set a vaild interface in the SapaService.setApaService()");
      }
    }
    catch (RemoteException paramString)
    {
      Log.e("SAPA", "Failed, " + paramString.getMessage());
      return makeFalseResult();
    }
    paramString = ((IApaService)this.mApaService.get()).deactivateClient(paramString);
    return paramString;
  }
  
  public int deleteNativeClient(SapaProcessor paramSapaProcessor)
  {
    nativeDeleteClient(new String(paramSapaProcessor.f()));
    return 0;
  }
  
  public String disconnectAllPort(String paramString)
  {
    try
    {
      if ((this.mApaService == null) || (this.mApaService.get() == null)) {
        throw new AndroidRuntimeException("Service is not ready. Please keep binding with IApaService and set a vaild interface in the SapaService.setApaService()");
      }
    }
    catch (RemoteException paramString)
    {
      Log.e("SAPA", "Failed, " + paramString.getMessage());
      return makeFalseResult();
    }
    paramString = ((IApaService)this.mApaService.get()).disconnectAllPort(paramString);
    return paramString;
  }
  
  public String disconnectPort(String paramString)
  {
    try
    {
      if ((this.mApaService == null) || (this.mApaService.get() == null)) {
        throw new AndroidRuntimeException("Service is not ready. Please keep binding with IApaService and set a vaild interface in the SapaService.setApaService()");
      }
    }
    catch (RemoteException paramString)
    {
      Log.e("SAPA", "Failed, " + paramString.getMessage());
      return makeFalseResult();
    }
    paramString = ((IApaService)this.mApaService.get()).disconnectPort(paramString);
    return paramString;
  }
  
  public String dontUseUSBDevice(String paramString)
  {
    return makeFalseResult();
  }
  
  public String getConnectionList(String paramString)
  {
    try
    {
      if ((this.mApaService == null) || (this.mApaService.get() == null)) {
        throw new AndroidRuntimeException("Service is not ready. Please keep binding with IApaService and set a vaild interface in the SapaService.setApaService()");
      }
    }
    catch (RemoteException paramString)
    {
      Log.e("SAPA", "Failed, " + paramString.getMessage());
      return makeFalseResult();
    }
    paramString = ((IApaService)this.mApaService.get()).getConnectionList(paramString);
    return paramString;
  }
  
  public String getParameters(String paramString)
  {
    try
    {
      if ((this.mApaService == null) || (this.mApaService.get() == null)) {
        throw new AndroidRuntimeException("Service is not ready. Please keep binding with IApaService and set a vaild interface in the SapaService.setApaService()");
      }
    }
    catch (RemoteException paramString)
    {
      Log.e("SAPA", "Failed, " + paramString.getMessage());
      return makeFalseResult();
    }
    paramString = ((IApaService)this.mApaService.get()).getParameters(paramString);
    return paramString;
  }
  
  public String getPortList(String paramString)
  {
    try
    {
      if ((this.mApaService == null) || (this.mApaService.get() == null)) {
        throw new AndroidRuntimeException("Service is not ready. Please keep binding with IApaService and set a vaild interface in the SapaService.setApaService()");
      }
    }
    catch (RemoteException paramString)
    {
      Log.e("SAPA", "Failed, " + paramString.getMessage());
      return makeFalseResult();
    }
    paramString = ((IApaService)this.mApaService.get()).getPortList(paramString);
    return paramString;
  }
  
  public String getSettings(String paramString)
  {
    try
    {
      if ((this.mApaService == null) || (this.mApaService.get() == null)) {
        throw new AndroidRuntimeException("Service is not ready. Please keep binding with IApaService and set a vaild interface in the SapaService.setApaService()");
      }
    }
    catch (RemoteException paramString)
    {
      Log.e("SAPA", "Failed, " + paramString.getMessage());
      return makeFalseResult();
    }
    switch (((IApaService)this.mApaService.get()).getDefaultLatency())
    {
    }
    for (;;)
    {
      paramString = String.format("[{\"defaultlatency\":\"%d\"}]", new Object[] { Integer.valueOf(i) });
      return paramString;
      int i = 2;
      continue;
      i = 1;
      continue;
      i = 0;
    }
  }
  
  public String globalLatencySettingValueChangedHigh(String paramString)
  {
    return makeFalseResult();
  }
  
  public String globalLatencySettingValueChangedLow(String paramString)
  {
    return makeFalseResult();
  }
  
  public String globalLatencySettingValueChangedMid(String paramString)
  {
    return makeFalseResult();
  }
  
  public String globalUSBSettingValueChangedAndroid(String paramString)
  {
    return makeFalseResult();
  }
  
  public String globalUSBSettingValueChangedSapa(String paramString)
  {
    return makeFalseResult();
  }
  
  public boolean isAPILevelAllowed(int paramInt)
  {
    return true;
  }
  
  public boolean isStarted()
  {
    try
    {
      if ((this.mApaService != null) && (this.mApaService.get() != null)) {
        return ((IApaService)this.mApaService.get()).isStarted();
      }
      Log.d("SAPA", "ApaService is not connected or is not started.");
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        localRemoteException.printStackTrace();
      }
    }
    return false;
  }
  
  public void loadJackLibrary()
  {
    loadJack3Lib();
  }
  
  public int newNativeClient(SapaProcessor paramSapaProcessor)
    throws InstantiationException
  {
    return 0;
  }
  
  public String pauseAudioPath(String paramString)
  {
    return makeFalseResult();
  }
  
  public int releaseNativeClient(SapaProcessor paramSapaProcessor)
  {
    return 0;
  }
  
  public String resumeAudioPath(String paramString)
  {
    return makeFalseResult();
  }
  
  public void setApaService(IBinder paramIBinder)
  {
    if (paramIBinder == null)
    {
      this.mApaService = null;
      this.mIApaService = null;
      return;
    }
    this.mIApaService = IApaService.Stub.asInterface(paramIBinder);
    if ((this.mIApaService instanceof IApaService))
    {
      this.mApaService = new WeakReference(this.mIApaService);
      return;
    }
    throw new IllegalArgumentException("parameter is not a service of the Professional Audio Service");
  }
  
  public String setPowerSavingPermissionEnabled(String paramString)
  {
    return makeFalseResult();
  }
  
  public String setSettings(String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      if ((this.mApaService == null) || (this.mApaService.get() == null)) {
        throw new AndroidRuntimeException("Service is not ready. Please keep binding with IApaService and set a vaild interface in the SapaService.setApaService()");
      }
    }
    catch (RemoteException paramString)
    {
      Log.e("SAPA", "Failed, " + paramString.getMessage());
      return makeFalseResult();
    }
    switch (paramInt2)
    {
    default: 
      paramInt1 = 2;
    }
    for (;;)
    {
      ((IApaService)this.mApaService.get()).setDefaultLatency(paramInt1);
      paramString = makeTrueResult();
      return paramString;
      paramInt1 = 4;
      continue;
      paramInt1 = 1;
    }
  }
  
  public String setSyncFrame(String paramString)
  {
    try
    {
      if ((this.mApaService == null) || (this.mApaService.get() == null)) {
        throw new AndroidRuntimeException("Service is not ready. Please keep binding with IApaService and set a vaild interface in the SapaService.setApaService()");
      }
    }
    catch (RemoteException paramString)
    {
      Log.e("SAPA", "Failed, " + paramString.getMessage());
      return makeFalseResult();
    }
    paramString = ((IApaService)this.mApaService.get()).setSyncFrame(paramString);
    return paramString;
  }
  
  public String setSyncPosition(String paramString)
  {
    try
    {
      if ((this.mApaService == null) || (this.mApaService.get() == null)) {
        throw new AndroidRuntimeException("Service is not ready. Please keep binding with IApaService and set a vaild interface in the SapaService.setApaService()");
      }
    }
    catch (RemoteException paramString)
    {
      Log.e("SAPA", "Failed, " + paramString.getMessage());
      return makeFalseResult();
    }
    paramString = ((IApaService)this.mApaService.get()).setSyncPosition(paramString);
    return paramString;
  }
  
  public String setSyncTimeout(String paramString)
  {
    try
    {
      if ((this.mApaService == null) || (this.mApaService.get() == null)) {
        throw new AndroidRuntimeException("Service is not ready. Please keep binding with IApaService and set a vaild interface in the SapaService.setApaService()");
      }
    }
    catch (RemoteException paramString)
    {
      Log.e("SAPA", "Failed, " + paramString.getMessage());
      return makeFalseResult();
    }
    paramString = ((IApaService)this.mApaService.get()).setSyncTimeout(paramString);
    return paramString;
  }
  
  public int setupNativeClient(SapaProcessor paramSapaProcessor)
  {
    String str = new String(paramSapaProcessor.f());
    return nativeSetup(new WeakReference(this), str, paramSapaProcessor.b());
  }
  
  public void start(int paramInt)
    throws AndroidRuntimeException, IllegalArgumentException, RemoteException
  {
    if ((this.mApaService == null) || (this.mApaService.get() == null))
    {
      Log.e("SAPA", "ApaService is null. Service is not connected.");
      throw new AndroidRuntimeException("Service is not connected yet. Please call bindService() first.");
    }
    if (!this.mIApaService.isPermissionsGranted()) {
      throw new SecurityException("RECORD_AUDIO permission of the professional audio service app is NOT granted yet.");
    }
    try
    {
      ((IApaService)this.mApaService.get()).start(paramInt, new IApaServiceStatusListener()
      {
        public IBinder asBinder()
        {
          new IApaServiceStatusListener.Stub()
          {
            public void isAlive()
              throws RemoteException
            {}
          };
        }
        
        public void isAlive()
          throws RemoteException
        {}
      });
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      Log.e("SAPA", localException.getMessage());
      throw new AndroidRuntimeException("Error while starting apaservice");
    }
  }
  
  public String startInput(String paramString)
  {
    try
    {
      if ((this.mApaService == null) || (this.mApaService.get() == null)) {
        throw new AndroidRuntimeException("Service is not ready. Please keep binding with IApaService and set a vaild interface in the SapaService.setApaService()");
      }
    }
    catch (RemoteException paramString)
    {
      Log.e("SAPA", "Failed, " + paramString.getMessage());
      return makeFalseResult();
    }
    paramString = ((IApaService)this.mApaService.get()).startInput(paramString);
    return paramString;
  }
  
  public String startPlay(String paramString)
  {
    return makeFalseResult();
  }
  
  public String startRecord(String paramString)
  {
    return makeFalseResult();
  }
  
  public String startRecordWithDuration(String paramString)
  {
    return makeFalseResult();
  }
  
  public String startSync(String paramString)
  {
    try
    {
      if ((this.mApaService == null) || (this.mApaService.get() == null)) {
        throw new AndroidRuntimeException("Service is not ready. Please keep binding with IApaService and set a vaild interface in the SapaService.setApaService()");
      }
    }
    catch (RemoteException paramString)
    {
      Log.e("SAPA", "Failed, " + paramString.getMessage());
      return makeFalseResult();
    }
    paramString = ((IApaService)this.mApaService.get()).startSync(paramString);
    return paramString;
  }
  
  public void stop(boolean paramBoolean)
  {
    try
    {
      if ((this.mApaService != null) && (this.mApaService.get() != null))
      {
        ((IApaService)this.mApaService.get()).stop(paramBoolean);
        return;
      }
      throw new AndroidRuntimeException("Service is not ready. Please keep binding with IApaService and set a vaild interface in the SapaService.setApaService()");
    }
    catch (IllegalArgumentException localIllegalArgumentException) {}catch (RemoteException localRemoteException)
    {
      localRemoteException.printStackTrace();
    }
  }
  
  public String stopInput(String paramString)
  {
    try
    {
      if ((this.mApaService == null) || (this.mApaService.get() == null)) {
        throw new AndroidRuntimeException("Service is not ready. Please keep binding with IApaService and set a vaild interface in the SapaService.setApaService()");
      }
    }
    catch (RemoteException paramString)
    {
      Log.e("SAPA", "Failed, " + paramString.getMessage());
      return makeFalseResult();
    }
    paramString = ((IApaService)this.mApaService.get()).stopInput(paramString);
    return paramString;
  }
  
  public String stopPlay(String paramString)
  {
    return makeFalseResult();
  }
  
  public String stopRecord(String paramString)
  {
    return makeFalseResult();
  }
  
  public String stopSync(String paramString)
  {
    try
    {
      if ((this.mApaService == null) || (this.mApaService.get() == null)) {
        throw new AndroidRuntimeException("Service is not ready. Please keep binding with IApaService and set a vaild interface in the SapaService.setApaService()");
      }
    }
    catch (RemoteException paramString)
    {
      Log.e("SAPA", "Failed, " + paramString.getMessage());
      return makeFalseResult();
    }
    paramString = ((IApaService)this.mApaService.get()).stopSync(paramString);
    return paramString;
  }
  
  public void unloadJackLibrary() {}
  
  public String updateUSBDeviceInfo(String paramString, boolean paramBoolean)
  {
    return makeFalseResult();
  }
  
  public String useUSBDevice(String paramString)
  {
    return makeFalseResult();
  }
}
