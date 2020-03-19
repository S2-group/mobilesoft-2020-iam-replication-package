package com.ironsource.environment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class DeviceStatus
{
  private static final String CACHED_UUID_KEY = "cachedUUID";
  private static final String DEVICE_OS = "android";
  private static final String GOOGLE_PLAY_SERVICES_CLASS_NAME = "com.google.android.gms.ads.identifier.AdvertisingIdClient";
  private static final String GOOGLE_PLAY_SERVICES_GET_AID_INFO_METHOD_NAME = "getAdvertisingIdInfo";
  private static final String GOOGLE_PLAY_SERVICES_GET_AID_METHOD_NAME = "getId";
  private static final String GOOGLE_PLAY_SERVICES_IS_LIMITED_AD_TRACKING_METHOD_NAME = "isLimitAdTrackingEnabled";
  private static final String MEDIATION_SHARED_PREFS = "Mediation_Shared_Preferences";
  public static final String UUID_ENABLED = "uuidEnabled";
  private static String uniqueId = null;
  
  public DeviceStatus() {}
  
  private static boolean findBinary(String paramString)
  {
    throw new Runtime("d2j fail translate: java.lang.NullPointerException\n\tat com.googlecode.dex2jar.ir.ts.ExceptionHandlerCurrectTransformer.transform(ExceptionHandlerCurrectTransformer.java:89)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:133)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public static int getActivityRequestedOrientation(Context paramContext)
  {
    return 0;
  }
  
  public static String[] getAdvertisingIdInfo(Context paramContext)
    throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException
  {
    return null;
  }
  
  public static int getAndroidAPIVersion()
  {
    return 0;
  }
  
  public static String getAndroidOsVersion()
  {
    return null;
  }
  
  public static int getApplicationRotation(Context paramContext)
  {
    return 0;
  }
  
  public static long getAvailableExternalMemorySizeInMegaBytes()
  {
    return 0L;
  }
  
  public static long getAvailableInternalMemorySizeInMegaBytes()
  {
    return 0L;
  }
  
  public static long getAvailableMemorySizeInMegaBytes(String paramString)
  {
    return 0L;
  }
  
  public static int getBatteryLevel(Context paramContext)
  {
    throw new Runtime("d2j fail translate: java.lang.NullPointerException\n\tat com.googlecode.dex2jar.ir.ts.ExceptionHandlerCurrectTransformer.transform(ExceptionHandlerCurrectTransformer.java:89)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:133)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public static int getDeviceDefaultOrientation(Context paramContext)
  {
    return 0;
  }
  
  public static float getDeviceDensity()
  {
    return 0.0F;
  }
  
  public static int getDeviceHeight()
  {
    return 0;
  }
  
  public static String getDeviceLanguage(Context paramContext)
    throws Exception
  {
    return null;
  }
  
  public static long getDeviceLocalTime()
  {
    return 0L;
  }
  
  public static String getDeviceModel()
  {
    return null;
  }
  
  public static String getDeviceOEM()
  {
    return null;
  }
  
  public static int getDeviceOrientation(Context paramContext)
  {
    return 0;
  }
  
  public static String getDeviceOs()
  {
    return null;
  }
  
  public static int getDeviceTimeZoneOffsetInMinutes()
  {
    return 0;
  }
  
  public static int getDeviceWidth()
  {
    return 0;
  }
  
  public static File getExternalCacheDir(Context paramContext)
  {
    return null;
  }
  
  private static long getFreeStorageInBytes(File paramFile)
  {
    return 0L;
  }
  
  public static List<ApplicationInfo> getInstalledApplications(Context paramContext)
  {
    return null;
  }
  
  public static String getInternalCacheDirPath(Context paramContext)
  {
    return null;
  }
  
  public static String getMobileCarrier(Context paramContext)
  {
    return null;
  }
  
  public static String getOrGenerateOnceUniqueIdentifier(Context paramContext)
  {
    throw new Runtime("d2j fail translate: java.lang.NullPointerException\n\tat com.googlecode.dex2jar.ir.ts.ExceptionHandlerCurrectTransformer.transform(ExceptionHandlerCurrectTransformer.java:89)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:133)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public static float getSystemVolumePercent(Context paramContext)
  {
    return 0.0F;
  }
  
  public static boolean isDeviceOrientationLocked(Context paramContext)
  {
    return false;
  }
  
  public static boolean isExternalMemoryAvailableWritable()
  {
    return false;
  }
  
  @TargetApi(19)
  public static boolean isImmersiveSupported(Activity paramActivity)
  {
    return false;
  }
  
  public static boolean isRTL(Context paramContext)
  {
    return false;
  }
  
  public static boolean isRootedDevice()
  {
    return false;
  }
}
