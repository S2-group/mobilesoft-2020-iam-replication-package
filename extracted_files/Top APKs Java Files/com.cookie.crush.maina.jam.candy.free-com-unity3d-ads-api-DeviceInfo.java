package com.unity3d.ads.api;

import com.unity3d.ads.webview.bridge.WebViewCallback;
import com.unity3d.ads.webview.bridge.WebViewExposed;
import java.io.File;

public class DeviceInfo
{
  public DeviceInfo() {}
  
  @WebViewExposed
  public static void getAdvertisingTrackingId(WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getAndroidId(WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getApiLevel(WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getBatteryLevel(WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getBatteryStatus(WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getConnectionType(WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getDeviceVolume(Integer paramInteger, WebViewCallback paramWebViewCallback) {}
  
  private static File getFileForStorageType(StorageType paramStorageType)
  {
    return null;
  }
  
  @WebViewExposed
  public static void getFreeMemory(WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getFreeSpace(String paramString, WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getHeadset(WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getInstalledPackages(boolean paramBoolean, WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getLimitAdTrackingFlag(WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getManufacturer(WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getModel(WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getNetworkOperator(WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getNetworkOperatorName(WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getNetworkType(WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getOsVersion(WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getRingerMode(WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getScreenBrightness(WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getScreenDensity(WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getScreenHeight(WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getScreenLayout(WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getScreenWidth(WebViewCallback paramWebViewCallback) {}
  
  private static StorageType getStorageTypeFromString(String paramString)
  {
    throw new Runtime("d2j fail translate: java.lang.NullPointerException\n\tat com.googlecode.dex2jar.ir.ts.ExceptionHandlerCurrectTransformer.transform(ExceptionHandlerCurrectTransformer.java:89)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:133)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  @WebViewExposed
  public static void getSystemLanguage(WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getSystemProperty(String paramString1, String paramString2, WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getTimeZone(Boolean paramBoolean, WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getTotalMemory(WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getTotalSpace(String paramString, WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void getUniqueEventId(WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void isAppInstalled(String paramString, WebViewCallback paramWebViewCallback) {}
  
  @WebViewExposed
  public static void isRooted(WebViewCallback paramWebViewCallback) {}
  
  public static enum StorageType
  {
    EXTERNAL,  INTERNAL;
    
    private StorageType() {}
  }
}
