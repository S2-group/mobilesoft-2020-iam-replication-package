package com.unity3d.ads.api;

import android.content.Context;
import com.unity3d.ads.device.Device;
import com.unity3d.ads.device.DeviceError;
import com.unity3d.ads.log.DeviceLog;
import com.unity3d.ads.properties.ClientProperties;
import com.unity3d.ads.webview.bridge.WebViewCallback;
import com.unity3d.ads.webview.bridge.WebViewExposed;
import java.io.File;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONArray;

public class DeviceInfo
{
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
  public static void getConnectionType(WebViewCallback paramWebViewCallback)
  {
    String str;
    if (Device.isUsingWifi()) {
      str = "wifi";
    }
    for (;;)
    {
      paramWebViewCallback.invoke(new Object[] { str });
      return;
      if (Device.isActiveNetworkConnected()) {
        str = "cellular";
      } else {
        str = "none";
      }
    }
  }
  
  @WebViewExposed
  public static void getDeviceVolume(Integer paramInteger, WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Integer.valueOf(Device.getStreamVolume(paramInteger.intValue())) });
  }
  
  private static File getFileForStorageType(DeviceInfo.StorageType paramStorageType)
  {
    switch (DeviceInfo.1.$SwitchMap$com$unity3d$ads$api$DeviceInfo$StorageType[paramStorageType.ordinal()])
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
    DeviceInfo.StorageType localStorageType = getStorageTypeFromString(paramString);
    if (localStorageType == null)
    {
      paramWebViewCallback.error(DeviceError.INVALID_STORAGETYPE, new Object[] { paramString });
      return;
    }
    long l = Device.getFreeSpace(getFileForStorageType(localStorageType));
    if (l > -1L)
    {
      paramWebViewCallback.invoke(new Object[] { Long.valueOf(l) });
      return;
    }
    paramWebViewCallback.error(DeviceError.COULDNT_GET_STORAGE_LOCATION, new Object[] { Long.valueOf(l) });
  }
  
  @WebViewExposed
  public static void getHeadset(WebViewCallback paramWebViewCallback)
  {
    paramWebViewCallback.invoke(new Object[] { Boolean.valueOf(Device.isWiredHeadsetOn()) });
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
  
  @WebViewExposed
  public static void getRingerMode(WebViewCallback paramWebViewCallback)
  {
    int i = Device.getRingerMode();
    if (i > -1)
    {
      paramWebViewCallback.invoke(new Object[] { Integer.valueOf(i) });
      return;
    }
    switch (i)
    {
    default: 
      DeviceLog.error("Unhandled ringerMode error: " + i);
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
    if (i > -1)
    {
      paramWebViewCallback.invoke(new Object[] { Integer.valueOf(i) });
      return;
    }
    switch (i)
    {
    default: 
      DeviceLog.error("Unhandled screenBrightness error: " + i);
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
  
  private static DeviceInfo.StorageType getStorageTypeFromString(String paramString)
  {
    try
    {
      DeviceInfo.StorageType localStorageType = DeviceInfo.StorageType.valueOf(paramString);
      return localStorageType;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      DeviceLog.exception("Illegal argument: " + paramString, localIllegalArgumentException);
    }
    return null;
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
    DeviceInfo.StorageType localStorageType = getStorageTypeFromString(paramString);
    if (localStorageType == null)
    {
      paramWebViewCallback.error(DeviceError.INVALID_STORAGETYPE, new Object[] { paramString });
      return;
    }
    long l = Device.getTotalSpace(getFileForStorageType(localStorageType));
    if (l > -1L)
    {
      paramWebViewCallback.invoke(new Object[] { Long.valueOf(l) });
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
}
