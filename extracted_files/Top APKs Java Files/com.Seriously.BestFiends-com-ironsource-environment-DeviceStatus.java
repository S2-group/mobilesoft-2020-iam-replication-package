package com.ironsource.environment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import org.json.JSONObject;

public class DeviceStatus
{
  public static final String UUID_ENABLED = "uuidEnabled";
  private static String uniqueId = null;
  
  public DeviceStatus() {}
  
  private static boolean findBinary(String paramString)
  {
    int i = 0;
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
  
  public static JSONObject getAppsInstallTime(Context paramContext, boolean paramBoolean)
  {
    Object localObject1 = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject1).addCategory("android.intent.category.LAUNCHER");
    localObject1 = paramContext.getPackageManager().queryIntentActivities((Intent)localObject1, 0);
    JSONObject localJSONObject = new JSONObject();
    paramContext = paramContext.getPackageManager();
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    int i = 0;
    for (;;)
    {
      if (i < ((List)localObject1).size())
      {
        Object localObject2 = (ResolveInfo)((List)localObject1).get(i);
        if (!paramBoolean) {}
        try
        {
          if (isSystemPackage((ResolveInfo)localObject2)) {
            break label161;
          }
          localObject2 = localSimpleDateFormat.format(new Date(paramContext.getPackageInfo(((ResolveInfo)localObject2).activityInfo.packageName, 4096).firstInstallTime));
          localJSONObject.put((String)localObject2, localJSONObject.optInt((String)localObject2, 0) + 1);
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
      return localJSONObject;
      label161:
      i += 1;
    }
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
  
  public static int getBatteryLevel(Context paramContext)
  {
    int j = 0;
    int m = -1;
    try
    {
      paramContext = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      if (paramContext != null) {}
      for (int i = paramContext.getIntExtra("level", -1);; i = 0)
      {
        if (paramContext != null) {
          j = paramContext.getIntExtra("scale", -1);
        }
        int k = m;
        if (i != -1)
        {
          k = m;
          if (j != -1) {
            k = (int)(i / j * 100.0F);
          }
        }
        return k;
      }
      return -1;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
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
  
  /* Error */
  public static String getOrGenerateOnceUniqueIdentifier(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 13	com/ironsource/environment/DeviceStatus:uniqueId	Ljava/lang/String;
    //   6: invokestatic 423	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   9: ifne +12 -> 21
    //   12: getstatic 13	com/ironsource/environment/DeviceStatus:uniqueId	Ljava/lang/String;
    //   15: astore_0
    //   16: ldc 2
    //   18: monitorexit
    //   19: aload_0
    //   20: areturn
    //   21: aload_0
    //   22: ldc_w 425
    //   25: iconst_0
    //   26: invokevirtual 429	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   29: astore_0
    //   30: aload_0
    //   31: ldc 8
    //   33: iconst_1
    //   34: invokeinterface 435 3 0
    //   39: ifeq +57 -> 96
    //   42: aload_0
    //   43: ldc_w 437
    //   46: ldc 114
    //   48: invokeinterface 441 3 0
    //   53: astore_1
    //   54: aload_1
    //   55: invokestatic 423	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   58: ifeq +45 -> 103
    //   61: invokestatic 447	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   64: invokevirtual 448	java/util/UUID:toString	()Ljava/lang/String;
    //   67: putstatic 13	com/ironsource/environment/DeviceStatus:uniqueId	Ljava/lang/String;
    //   70: aload_0
    //   71: invokeinterface 452 1 0
    //   76: astore_0
    //   77: aload_0
    //   78: ldc_w 437
    //   81: getstatic 13	com/ironsource/environment/DeviceStatus:uniqueId	Ljava/lang/String;
    //   84: invokeinterface 458 3 0
    //   89: pop
    //   90: aload_0
    //   91: invokeinterface 461 1 0
    //   96: getstatic 13	com/ironsource/environment/DeviceStatus:uniqueId	Ljava/lang/String;
    //   99: astore_0
    //   100: goto -84 -> 16
    //   103: aload_1
    //   104: putstatic 13	com/ironsource/environment/DeviceStatus:uniqueId	Ljava/lang/String;
    //   107: goto -11 -> 96
    //   110: astore_0
    //   111: aload_0
    //   112: invokevirtual 242	java/lang/Exception:printStackTrace	()V
    //   115: goto -19 -> 96
    //   118: astore_0
    //   119: ldc 2
    //   121: monitorexit
    //   122: aload_0
    //   123: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	124	0	paramContext	Context
    //   53	51	1	str	String
    // Exception table:
    //   from	to	target	type
    //   21	96	110	java/lang/Exception
    //   103	107	110	java/lang/Exception
    //   3	16	118	finally
    //   21	96	118	finally
    //   96	100	118	finally
    //   103	107	118	finally
    //   111	115	118	finally
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
