package com.ironsource.environment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import org.json.JSONObject;

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
  private static String uniqueId;
  
  public DeviceStatus() {}
  
  private static boolean findBinary(String paramString)
  {
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
      while (i < j)
      {
        String str = arrayOfString[i];
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(str);
        localStringBuilder.append(paramString);
        boolean bool = new File(localStringBuilder.toString()).exists();
        if (bool) {
          return true;
        }
        i += 1;
      }
      return false;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  public static int getActivityRequestedOrientation(Context paramContext)
  {
    if ((paramContext instanceof Activity)) {
      return ((Activity)paramContext).getRequestedOrientation();
    }
    return -1;
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
    paramContext = new StringBuilder();
    paramContext.append("");
    paramContext.append(bool);
    return new String[] { localObject2, paramContext.toString() };
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
  
  public static JSONObject getAppsInstallTime(Context paramContext, boolean paramBoolean)
  {
    Object localObject1 = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject1).addCategory("android.intent.category.LAUNCHER");
    localObject1 = paramContext.getPackageManager().queryIntentActivities((Intent)localObject1, 0);
    JSONObject localJSONObject = new JSONObject();
    paramContext = paramContext.getPackageManager();
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    int i = 0;
    while (i < ((List)localObject1).size())
    {
      Object localObject2 = (ResolveInfo)((List)localObject1).get(i);
      if (!paramBoolean) {}
      try
      {
        if (isSystemPackage((ResolveInfo)localObject2)) {
          break label153;
        }
        localObject2 = localSimpleDateFormat.format(new Date(paramContext.getPackageInfo(((ResolveInfo)localObject2).activityInfo.packageName, 4096).firstInstallTime));
        localJSONObject.put((String)localObject2, localJSONObject.optInt((String)localObject2, 0) + 1);
      }
      catch (Exception localException)
      {
        label153:
        for (;;) {}
      }
      ((Exception)localObject2).printStackTrace();
      i += 1;
    }
    return localJSONObject;
  }
  
  public static long getAvailableExternalMemorySizeInMegaBytes()
  {
    if (isExternalMemoryAvailableWritable()) {
      return getFreeStorageInBytes(Environment.getExternalStorageDirectory());
    }
    return 0L;
  }
  
  public static long getAvailableInternalMemorySizeInMegaBytes()
  {
    return getFreeStorageInBytes(Environment.getDataDirectory());
  }
  
  public static long getAvailableMemorySizeInMegaBytes(String paramString)
  {
    return getFreeStorageInBytes(new File(paramString));
  }
  
  public static int getBatteryLevel(Context paramContext)
  {
    for (;;)
    {
      try
      {
        paramContext = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        int j = 0;
        if (paramContext == null) {
          break label75;
        }
        i = paramContext.getIntExtra("level", -1);
        if (paramContext != null) {
          j = paramContext.getIntExtra("scale", -1);
        }
        if ((i != -1) && (j != -1)) {
          return (int)(i / j * 100.0F);
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      return -1;
      label75:
      int i = 0;
    }
  }
  
  public static int getDeviceDefaultOrientation(Context paramContext)
  {
    int i = getApplicationRotation(paramContext);
    int j = getDeviceOrientation(paramContext);
    if (((i != 0) && (i != 2)) || (j != 2))
    {
      if (((i == 1) || (i == 3)) && (j == 1)) {
        return 2;
      }
      return 1;
    }
    return 2;
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
    long l;
    if (Build.VERSION.SDK_INT < 19) {
      l = paramFile.getAvailableBlocks() * paramFile.getBlockSize();
    } else {
      l = paramFile.getAvailableBlocksLong() * paramFile.getBlockSizeLong();
    }
    return l / 1048576L;
  }
  
  public static List<ApplicationInfo> getInstalledApplications(Context paramContext)
  {
    return paramContext.getPackageManager().getInstalledApplications(0);
  }
  
  public static String getInternalCacheDirPath(Context paramContext)
  {
    paramContext = paramContext.getCacheDir();
    if (paramContext != null) {
      return paramContext.getPath();
    }
    return null;
  }
  
  public static String getMobileCarrier(Context paramContext)
  {
    return ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperatorName();
  }
  
  public static String getOrGenerateOnceUniqueIdentifier(Context paramContext)
  {
    try
    {
      if (!TextUtils.isEmpty(uniqueId))
      {
        paramContext = uniqueId;
        return paramContext;
      }
      try
      {
        paramContext = paramContext.getSharedPreferences("Mediation_Shared_Preferences", 0);
        if (paramContext.getBoolean("uuidEnabled", true))
        {
          String str = paramContext.getString("cachedUUID", "");
          if (TextUtils.isEmpty(str))
          {
            uniqueId = UUID.randomUUID().toString();
            paramContext = paramContext.edit();
            paramContext.putString("cachedUUID", uniqueId);
            paramContext.apply();
          }
          else
          {
            uniqueId = str;
          }
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      paramContext = uniqueId;
      return paramContext;
    }
    finally {}
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
  
  @TargetApi(19)
  public static boolean isImmersiveSupported(Activity paramActivity)
  {
    int i = paramActivity.getWindow().getDecorView().getSystemUiVisibility();
    return ((i | 0x1000) == i) || ((i | 0x800) == i);
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
  
  private static boolean isSystemPackage(ResolveInfo paramResolveInfo)
  {
    return (paramResolveInfo.activityInfo.applicationInfo.flags & 0x1) != 0;
  }
}
