package com.supersonic.environment;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class DeviceStatus
{
  private static final String DEVICE_OS = "android";
  private static final String GOOGLE_PLAY_SERVICES_CLASS_NAME = "com.google.android.gms.ads.identifier.AdvertisingIdClient";
  private static final String GOOGLE_PLAY_SERVICES_GET_AID_INFO_METHOD_NAME = "getAdvertisingIdInfo";
  private static final String GOOGLE_PLAY_SERVICES_GET_AID_METHOD_NAME = "getId";
  private static final String GOOGLE_PLAY_SERVICES_IS_LIMITED_AD_TRACKING_METHOD_NAME = "isLimitAdTrackingEnabled";
  
  public DeviceStatus() {}
  
  private static boolean findBinary(String paramString)
  {
    boolean bool2 = false;
    try
    {
      String[] arrayOfString = new String[8];
      arrayOfString[0] = "/sbin/";
      arrayOfString[1] = "/system/bin/";
      arrayOfString[2] = "/system/xbin/";
      arrayOfString[3] = "/data/local/xbin/";
      arrayOfString[4] = "/data/local/bin/";
      arrayOfString[5] = "/system/sd/xbin/";
      arrayOfString[6] = "/system/bin/failsafe/";
      arrayOfString[7] = "/data/local/";
      int j = arrayOfString.length;
      int i = 0;
      for (;;)
      {
        boolean bool1 = bool2;
        if (i < j)
        {
          String str = arrayOfString[i];
          bool1 = new File(str + paramString).exists();
          if (bool1) {
            bool1 = true;
          }
        }
        else
        {
          return bool1;
        }
        i += 1;
      }
      return false;
    }
    catch (Exception paramString) {}
  }
  
  public static String[] getAdvertisingIdInfo(Context paramContext)
    throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException
  {
    Object localObject1 = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
    paramContext = ((Class)localObject1).getMethod("getAdvertisingIdInfo", new Class[] { Context.class }).invoke(localObject1, new Object[] { paramContext });
    Object localObject2 = paramContext.getClass().getMethod("getId", new Class[0]);
    localObject1 = paramContext.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]);
    localObject2 = ((Method)localObject2).invoke(paramContext, new Object[0]).toString();
    boolean bool = ((Boolean)((Method)localObject1).invoke(paramContext, new Object[0])).booleanValue();
    return new String[] { localObject2, "" + bool };
  }
  
  public static int getAndroidAPIVersion()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static String getAndroidOsVersion()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static int getApplicationRotation(Context paramContext)
  {
    return ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getRotation();
  }
  
  public static long getAvailableExternalMemorySizeInMegaBytes()
  {
    long l = 0L;
    if (isExternalMemoryAvailableWritable()) {
      l = getFreeStorageInBytes(Environment.getExternalStorageDirectory());
    }
    return l;
  }
  
  public static long getAvailableInternalMemorySizeInMegaBytes()
  {
    return getFreeStorageInBytes(Environment.getDataDirectory());
  }
  
  public static long getAvailableMemorySizeInMegaBytes(String paramString)
  {
    return getFreeStorageInBytes(new File(paramString));
  }
  
  public static int getDeviceDefaultOrientation(Context paramContext)
  {
    int i = getApplicationRotation(paramContext);
    int j = getDeviceOrientation(paramContext);
    if (((i != 0) && (i != 2)) || ((j == 2) || (((i == 1) || (i == 3)) && (j == 1)))) {
      return 2;
    }
    return 1;
  }
  
  public static float getDeviceDensity()
  {
    return Resources.getSystem().getDisplayMetrics().density;
  }
  
  public static int getDeviceHeight()
  {
    return Resources.getSystem().getDisplayMetrics().heightPixels;
  }
  
  public static String getDeviceLanguage(Context paramContext)
    throws Exception
  {
    return paramContext.getResources().getConfiguration().locale.getLanguage();
  }
  
  public static long getDeviceLocalTime()
  {
    return Calendar.getInstance(TimeZone.getDefault()).getTime().getTime();
  }
  
  public static String getDeviceModel()
  {
    return Build.MODEL;
  }
  
  public static String getDeviceOEM()
  {
    return Build.MANUFACTURER;
  }
  
  public static int getDeviceOrientation(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().orientation;
  }
  
  public static String getDeviceOs()
  {
    return "android";
  }
  
  public static int getDeviceTimeZoneOffsetInMinutes()
  {
    return -(TimeZone.getDefault().getOffset(getDeviceLocalTime()) / 60000);
  }
  
  public static int getDeviceWidth()
  {
    return Resources.getSystem().getDisplayMetrics().widthPixels;
  }
  
  public static File getExternalCacheDir(Context paramContext)
  {
    return paramContext.getExternalCacheDir();
  }
  
  private static long getFreeStorageInBytes(File paramFile)
  {
    paramFile = new StatFs(paramFile.getPath());
    if (Build.VERSION.SDK_INT < 19) {}
    for (long l = paramFile.getAvailableBlocks() * paramFile.getBlockSize();; l = paramFile.getAvailableBlocksLong() * paramFile.getBlockSizeLong()) {
      return l / 1048576L;
    }
  }
  
  public static List<ApplicationInfo> getInstalledApplications(Context paramContext)
  {
    return paramContext.getPackageManager().getInstalledApplications(0);
  }
  
  public static String getInternalCacheDirPath(Context paramContext)
  {
    Object localObject = null;
    File localFile = paramContext.getCacheDir();
    paramContext = localObject;
    if (localFile != null) {
      paramContext = localFile.getPath();
    }
    return paramContext;
  }
  
  public static String getMobileCarrier(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperatorName();
  }
  
  public static float getSystemVolumePercent(Context paramContext)
  {
    paramContext = (AudioManager)paramContext.getSystemService("audio");
    return paramContext.getStreamVolume(3) / paramContext.getStreamMaxVolume(3);
  }
  
  public static boolean isDeviceOrientationLocked(Context paramContext)
  {
    return Settings.System.getInt(paramContext.getContentResolver(), "accelerometer_rotation", 0) != 1;
  }
  
  public static boolean isExternalMemoryAvailableWritable()
  {
    return ("mounted".equals(Environment.getExternalStorageState())) && (Environment.isExternalStorageRemovable());
  }
  
  public static boolean isRTL(Context paramContext)
  {
    paramContext = paramContext.getResources().getConfiguration();
    return (Build.VERSION.SDK_INT >= 17) && (paramContext.getLayoutDirection() == 1);
  }
  
  public static boolean isRootedDevice()
  {
    return findBinary("su");
  }
}
