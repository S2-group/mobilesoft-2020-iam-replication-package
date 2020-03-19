package com.unity3d.ads.api;

import android.content.Context;
import android.hardware.Sensor;
import android.util.SparseArray;
import com.unity3d.ads.device.Device;
import com.unity3d.ads.device.DeviceError;
import com.unity3d.ads.device.IVolumeChangeListener;
import com.unity3d.ads.device.VolumeChange;
import com.unity3d.ads.log.DeviceLog;
import com.unity3d.ads.properties.ClientProperties;
import com.unity3d.ads.webview.WebViewApp;
import com.unity3d.ads.webview.WebViewEventCategory;
import com.unity3d.ads.webview.bridge.WebViewCallback;
import com.unity3d.ads.webview.bridge.WebViewExposed;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceInfo
{
  private static SparseArray<IVolumeChangeListener> _volumeChangeListeners;
  
  public DeviceInfo() {}
  
  @WebViewExposed
  public static void getAdvertisingTrackingId(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Device.getAdvertisingTrackingId() });
  }
  
  @WebViewExposed
  public static void getAndroidId(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Device.getAndroidId() });
  }
  
  @WebViewExposed
  public static void getApiLevel(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Integer.valueOf(Device.getApiLevel()) });
  }
  
  @WebViewExposed
  public static void getBatteryLevel(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Float.valueOf(Device.getBatteryLevel()) });
  }
  
  @WebViewExposed
  public static void getBatteryStatus(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Integer.valueOf(Device.getBatteryStatus()) });
  }
  
  @WebViewExposed
  public static void getBoard(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Device.getBoard() });
  }
  
  @WebViewExposed
  public static void getBootloader(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Device.getBootloader() });
  }
  
  @WebViewExposed
  public static void getBrand(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Device.getBrand() });
  }
  
  @WebViewExposed
  public static void getConnectionType(WebViewCallback paramWebViewCallback)
  {
    String str;
    if (!Device.isUsingWifi())
    {
      if (Device.isActiveNetworkConnected()) {
        break label34;
      }
      str = "none";
    }
    for (;;)
    {
      paramWebViewCallback.invoke(new Object[] { str });
      return;
      str = "wifi";
      continue;
      label34:
      str = "cellular";
    }
  }
  
  @WebViewExposed
  public static void getDevice(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Device.getDevice() });
  }
  
  @WebViewExposed
  public static void getDeviceMaxVolume(Integer paramInteger, WebViewCallback paramWebViewCallback)
  {
    int i = Device.getStreamMaxVolume(paramInteger.intValue());
    if (i <= -1) {}
    switch (i)
    {
    default: 
      DeviceLog.error("Unhandled deviceMaxVolume error: " + i);
      return;
      paramWebViewCallback.invoke(new Object[] { Integer.valueOf(i) });
      return;
    case -1: 
      paramWebViewCallback.error(DeviceError.APPLICATION_CONTEXT_NULL, new Object[] { Integer.valueOf(i) });
      return;
    }
    paramWebViewCallback.error(DeviceError.AUDIOMANAGER_NULL, new Object[] { Integer.valueOf(i) });
  }
  
  @WebViewExposed
  public static void getDeviceVolume(Integer paramInteger, WebViewCallback paramWebViewCallback)
  {
    int i = Device.getStreamVolume(paramInteger.intValue());
    if (i <= -1) {}
    switch (i)
    {
    default: 
      DeviceLog.error("Unhandled deviceVolume error: " + i);
      return;
      paramWebViewCallback.invoke(new Object[] { Integer.valueOf(i) });
      return;
    case -1: 
      paramWebViewCallback.error(DeviceError.APPLICATION_CONTEXT_NULL, new Object[] { Integer.valueOf(i) });
      return;
    }
    paramWebViewCallback.error(DeviceError.AUDIOMANAGER_NULL, new Object[] { Integer.valueOf(i) });
  }
  
  private static File getFileForStorageType(StorageType paramStorageType)
  {
    switch (2.$SwitchMap$com$unity3d$ads$api$DeviceInfo$StorageType[paramStorageType.ordinal()])
    {
    default: 
      DeviceLog.error("Unhandled storagetype: " + paramStorageType);
      return null;
    case 1: 
      return ClientProperties.getApplicationContext().getCacheDir();
    }
    return ClientProperties.getApplicationContext().getExternalCacheDir();
  }
  
  @WebViewExposed
  public static void getFreeMemory(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Long.valueOf(Device.getFreeMemory()) });
  }
  
  @WebViewExposed
  public static void getFreeSpace(String paramString, WebViewCallback paramWebViewCallback)
  {
    StorageType localStorageType = getStorageTypeFromString(paramString);
    long l;
    if (localStorageType != null)
    {
      l = Device.getFreeSpace(getFileForStorageType(localStorageType));
      if (l > -1L) {
        break label66;
      }
    }
    label66:
    for (int i = 1; i == 0; i = 0)
    {
      paramWebViewCallback.invoke(new Object[] { Long.valueOf(l) });
      return;
      paramWebViewCallback.error(DeviceError.INVALID_STORAGETYPE, new Object[] { paramString });
      return;
    }
    paramWebViewCallback.error(DeviceError.COULDNT_GET_STORAGE_LOCATION, new Object[] { Long.valueOf(l) });
  }
  
  @WebViewExposed
  public static void getGLVersion(WebViewCallback paramWebViewCallback)
  {
    String str = Device.getGLVersion();
    if (str == null)
    {
      paramWebViewCallback.error(DeviceError.COULDNT_GET_GL_VERSION, new Object[0]);
      return;
    }
    paramWebViewCallback.invoke(new Object[] { str });
  }
  
  @WebViewExposed
  public static void getHardware(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Device.getHardware() });
  }
  
  @WebViewExposed
  public static void getHeadset(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Boolean.valueOf(Device.isWiredHeadsetOn()) });
  }
  
  @WebViewExposed
  public static void getHost(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Device.getHost() });
  }
  
  @WebViewExposed
  public static void getInstalledPackages(boolean paramBoolean, WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { new JSONArray(Device.getInstalledPackages(paramBoolean)) });
  }
  
  @WebViewExposed
  public static void getLimitAdTrackingFlag(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Boolean.valueOf(Device.isLimitAdTrackingEnabled()) });
  }
  
  @WebViewExposed
  public static void getManufacturer(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Device.getManufacturer() });
  }
  
  @WebViewExposed
  public static void getModel(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Device.getModel() });
  }
  
  @WebViewExposed
  public static void getNetworkOperator(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Device.getNetworkOperator() });
  }
  
  @WebViewExposed
  public static void getNetworkOperatorName(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Device.getNetworkOperatorName() });
  }
  
  @WebViewExposed
  public static void getNetworkType(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Integer.valueOf(Device.getNetworkType()) });
  }
  
  @WebViewExposed
  public static void getOsVersion(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Device.getOsVersion() });
  }
  
  /* Error */
  @WebViewExposed
  public static void getPackageInfo(String paramString, WebViewCallback paramWebViewCallback)
  {
    // Byte code:
    //   0: invokestatic 156	com/unity3d/ads/properties/ClientProperties:getApplicationContext	()Landroid/content/Context;
    //   3: ifnonnull +15 -> 18
    //   6: aload_1
    //   7: getstatic 124	com/unity3d/ads/device/DeviceError:APPLICATION_CONTEXT_NULL	Lcom/unity3d/ads/device/DeviceError;
    //   10: iconst_0
    //   11: anewarray 4	java/lang/Object
    //   14: invokevirtual 127	com/unity3d/ads/webview/bridge/WebViewCallback:error	(Ljava/lang/Enum;[Ljava/lang/Object;)V
    //   17: return
    //   18: invokestatic 156	com/unity3d/ads/properties/ClientProperties:getApplicationContext	()Landroid/content/Context;
    //   21: invokevirtual 255	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   24: astore_2
    //   25: aload_2
    //   26: aload_0
    //   27: iconst_0
    //   28: invokevirtual 260	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   31: astore_3
    //   32: new 262	org/json/JSONObject
    //   35: dup
    //   36: invokespecial 263	org/json/JSONObject:<init>	()V
    //   39: astore 4
    //   41: aload 4
    //   43: ldc_w 265
    //   46: aload_2
    //   47: aload_0
    //   48: invokevirtual 269	android/content/pm/PackageManager:getInstallerPackageName	(Ljava/lang/String;)Ljava/lang/String;
    //   51: invokevirtual 273	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   54: pop
    //   55: aload 4
    //   57: ldc_w 275
    //   60: aload_3
    //   61: getfield 280	android/content/pm/PackageInfo:firstInstallTime	J
    //   64: invokevirtual 283	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   67: pop
    //   68: aload 4
    //   70: ldc_w 285
    //   73: aload_3
    //   74: getfield 287	android/content/pm/PackageInfo:lastUpdateTime	J
    //   77: invokevirtual 283	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   80: pop
    //   81: aload 4
    //   83: ldc_w 289
    //   86: aload_3
    //   87: getfield 292	android/content/pm/PackageInfo:versionCode	I
    //   90: invokevirtual 295	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   93: pop
    //   94: aload 4
    //   96: ldc_w 297
    //   99: aload_3
    //   100: getfield 300	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   103: invokevirtual 273	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   106: pop
    //   107: aload 4
    //   109: ldc_w 302
    //   112: aload_3
    //   113: getfield 304	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   116: invokevirtual 273	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   119: pop
    //   120: aload_1
    //   121: iconst_1
    //   122: anewarray 4	java/lang/Object
    //   125: dup
    //   126: iconst_0
    //   127: aload 4
    //   129: aastore
    //   130: invokevirtual 36	com/unity3d/ads/webview/bridge/WebViewCallback:invoke	([Ljava/lang/Object;)V
    //   133: return
    //   134: astore_2
    //   135: aload_1
    //   136: getstatic 307	com/unity3d/ads/device/DeviceError:APPLICATION_INFO_NOT_AVAILABLE	Lcom/unity3d/ads/device/DeviceError;
    //   139: iconst_1
    //   140: anewarray 4	java/lang/Object
    //   143: dup
    //   144: iconst_0
    //   145: aload_0
    //   146: aastore
    //   147: invokevirtual 127	com/unity3d/ads/webview/bridge/WebViewCallback:error	(Ljava/lang/Enum;[Ljava/lang/Object;)V
    //   150: return
    //   151: astore_0
    //   152: aload_1
    //   153: getstatic 310	com/unity3d/ads/device/DeviceError:JSON_ERROR	Lcom/unity3d/ads/device/DeviceError;
    //   156: iconst_1
    //   157: anewarray 4	java/lang/Object
    //   160: dup
    //   161: iconst_0
    //   162: aload_0
    //   163: invokevirtual 313	org/json/JSONException:getMessage	()Ljava/lang/String;
    //   166: aastore
    //   167: invokevirtual 127	com/unity3d/ads/webview/bridge/WebViewCallback:error	(Ljava/lang/Enum;[Ljava/lang/Object;)V
    //   170: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	171	0	paramString	String
    //   0	171	1	paramWebViewCallback	WebViewCallback
    //   24	23	2	localPackageManager	android.content.pm.PackageManager
    //   134	1	2	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   31	82	3	localPackageInfo	android.content.pm.PackageInfo
    //   39	89	4	localJSONObject	JSONObject
    // Exception table:
    //   from	to	target	type
    //   25	32	134	android/content/pm/PackageManager$NameNotFoundException
    //   41	120	151	org/json/JSONException
  }
  
  @WebViewExposed
  public static void getProduct(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Device.getProduct() });
  }
  
  @WebViewExposed
  public static void getRingerMode(WebViewCallback paramWebViewCallback)
  {
    int i = Device.getRingerMode();
    if (i <= -1) {}
    switch (i)
    {
    default: 
      DeviceLog.error("Unhandled ringerMode error: " + i);
      return;
      paramWebViewCallback.invoke(new Object[] { Integer.valueOf(i) });
      return;
    case -1: 
      paramWebViewCallback.error(DeviceError.APPLICATION_CONTEXT_NULL, new Object[] { Integer.valueOf(i) });
      return;
    }
    paramWebViewCallback.error(DeviceError.AUDIOMANAGER_NULL, new Object[] { Integer.valueOf(i) });
  }
  
  @WebViewExposed
  public static void getScreenBrightness(WebViewCallback paramWebViewCallback)
  {
    int i = Device.getScreenBrightness();
    if (i <= -1) {}
    switch (i)
    {
    default: 
      DeviceLog.error("Unhandled screenBrightness error: " + i);
      return;
      paramWebViewCallback.invoke(new Object[] { Integer.valueOf(i) });
      return;
    }
    paramWebViewCallback.error(DeviceError.APPLICATION_CONTEXT_NULL, new Object[] { Integer.valueOf(i) });
  }
  
  @WebViewExposed
  public static void getScreenDensity(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Integer.valueOf(Device.getScreenDensity()) });
  }
  
  @WebViewExposed
  public static void getScreenHeight(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Integer.valueOf(Device.getScreenHeight()) });
  }
  
  @WebViewExposed
  public static void getScreenLayout(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Integer.valueOf(Device.getScreenLayout()) });
  }
  
  @WebViewExposed
  public static void getScreenWidth(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Integer.valueOf(Device.getScreenWidth()) });
  }
  
  @WebViewExposed
  public static void getSensorList(WebViewCallback paramWebViewCallback)
  {
    JSONArray localJSONArray = new JSONArray();
    Object localObject = Device.getSensorList();
    if (localObject == null)
    {
      paramWebViewCallback.invoke(new Object[] { localJSONArray });
      return;
    }
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      Sensor localSensor = (Sensor)((Iterator)localObject).next();
      JSONObject localJSONObject = new JSONObject();
      try
      {
        localJSONObject.put("name", localSensor.getName());
        localJSONObject.put("type", localSensor.getType());
        localJSONObject.put("vendor", localSensor.getVendor());
        localJSONObject.put("maximumRange", localSensor.getMaximumRange());
        localJSONObject.put("power", localSensor.getPower());
        localJSONObject.put("version", localSensor.getVersion());
        localJSONObject.put("resolution", localSensor.getResolution());
        localJSONObject.put("minDelay", localSensor.getMinDelay());
        localJSONArray.put(localJSONObject);
      }
      catch (JSONException localJSONException)
      {
        paramWebViewCallback.error(DeviceError.JSON_ERROR, new Object[] { localJSONException.getMessage() });
      }
    }
  }
  
  private static StorageType getStorageTypeFromString(String paramString)
  {
    try
    {
      StorageType localStorageType = StorageType.valueOf(paramString);
      return localStorageType;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      DeviceLog.exception("Illegal argument: " + paramString, localIllegalArgumentException);
    }
    return null;
  }
  
  @WebViewExposed
  public static void getSupportedAbis(WebViewCallback paramWebViewCallback)
  {
    JSONArray localJSONArray = new JSONArray();
    Iterator localIterator = Device.getSupportedAbis().iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        paramWebViewCallback.invoke(new Object[] { localJSONArray });
        return;
      }
      localJSONArray.put((String)localIterator.next());
    }
  }
  
  @WebViewExposed
  public static void getSystemLanguage(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Locale.getDefault().toString() });
  }
  
  @WebViewExposed
  public static void getSystemProperty(String paramString1, String paramString2, WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Device.getSystemProperty(paramString1, paramString2) });
  }
  
  @WebViewExposed
  public static void getTimeZone(Boolean paramBoolean, WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { TimeZone.getDefault().getDisplayName(paramBoolean.booleanValue(), 0, Locale.US) });
  }
  
  @WebViewExposed
  public static void getTotalMemory(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Long.valueOf(Device.getTotalMemory()) });
  }
  
  @WebViewExposed
  public static void getTotalSpace(String paramString, WebViewCallback paramWebViewCallback)
  {
    StorageType localStorageType = getStorageTypeFromString(paramString);
    long l;
    if (localStorageType != null)
    {
      l = Device.getTotalSpace(getFileForStorageType(localStorageType));
      if (l > -1L) {
        break label66;
      }
    }
    label66:
    for (int i = 1; i == 0; i = 0)
    {
      paramWebViewCallback.invoke(new Object[] { Long.valueOf(l) });
      return;
      paramWebViewCallback.error(DeviceError.INVALID_STORAGETYPE, new Object[] { paramString });
      return;
    }
    paramWebViewCallback.error(DeviceError.COULDNT_GET_STORAGE_LOCATION, new Object[] { Long.valueOf(l) });
  }
  
  @WebViewExposed
  public static void getUniqueEventId(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Device.getUniqueEventId() });
  }
  
  @WebViewExposed
  public static void isAppInstalled(String paramString, WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Boolean.valueOf(Device.isAppInstalled(paramString)) });
  }
  
  @WebViewExposed
  public static void isRooted(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Boolean.valueOf(Device.isRooted()) });
  }
  
  @WebViewExposed
  public static void registerVolumeChangeListener(Integer paramInteger, WebViewCallback paramWebViewCallback)
  {
    if (_volumeChangeListeners != null) {
      if (_volumeChangeListeners.get(paramInteger.intValue()) == null) {
        break label41;
      }
    }
    for (;;)
    {
      paramWebViewCallback.invoke(new Object[0]);
      return;
      _volumeChangeListeners = new SparseArray();
      break;
      label41:
      IVolumeChangeListener local1 = new IVolumeChangeListener()
      {
        private int _streamType = this.val$streamType.intValue();
        
        public int getStreamType()
        {
          return this._streamType;
        }
        
        public void onVolumeChanged(int paramAnonymousInt)
        {
          WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.DEVICEINFO, DeviceInfo.DeviceInfoEvent.VOLUME_CHANGED, new Object[] { Integer.valueOf(getStreamType()), Integer.valueOf(paramAnonymousInt), Integer.valueOf(Device.getStreamMaxVolume(this._streamType)) });
        }
      };
      _volumeChangeListeners.append(paramInteger.intValue(), local1);
      VolumeChange.registerListener(local1);
    }
  }
  
  @WebViewExposed
  public static void unregisterVolumeChangeListener(Integer paramInteger, WebViewCallback paramWebViewCallback)
  {
    if (_volumeChangeListeners == null) {}
    for (;;)
    {
      paramWebViewCallback.invoke(new Object[0]);
      return;
      if (_volumeChangeListeners.get(paramInteger.intValue()) != null)
      {
        VolumeChange.unregisterListener((IVolumeChangeListener)_volumeChangeListeners.get(paramInteger.intValue()));
        _volumeChangeListeners.remove(paramInteger.intValue());
      }
    }
  }
  
  public static enum DeviceInfoEvent
  {
    VOLUME_CHANGED;
    
    private DeviceInfoEvent() {}
  }
  
  public static enum StorageType
  {
    EXTERNAL,  INTERNAL;
    
    private StorageType() {}
  }
}
