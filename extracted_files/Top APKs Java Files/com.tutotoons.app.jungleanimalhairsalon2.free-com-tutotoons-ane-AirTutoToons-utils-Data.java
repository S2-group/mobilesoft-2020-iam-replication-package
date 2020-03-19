package com.tutotoons.ane.AirTutoToons.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.media.MediaCodec;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.tutotoons.ane.AirTutoToons.ads.TrackEvent;
import com.tutotoons.ane.AirTutoToons.ads.TutoAds;
import com.tutotoons.ane.AirTutoToons.utils.DataStructures.AppData;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Data
{
  public static final String PLATFORM_AMAZON = "amazon";
  public static final String PLATFORM_ANDROID = "android";
  private static ArrayList<AppData> app_list;
  public static String config_url;
  public static String google_analytics_id = "null";
  private static Boolean is_first_session = Boolean.valueOf(false);
  public static int notification_id;
  private static SharedPreferences shared_preferences;
  public static boolean supports_hardware_codecs;
  
  static
  {
    config_url = "";
    notification_id = -1;
    supports_hardware_codecs = true;
  }
  
  public Data() {}
  
  private static String capitalizeString(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return paramString;
    }
    paramString = paramString.toCharArray();
    int i = 1;
    StringBuilder localStringBuilder = new StringBuilder();
    int k = paramString.length;
    int j = 0;
    if (j < k)
    {
      char c = paramString[j];
      if ((i != 0) && (Character.isLetter(c)))
      {
        localStringBuilder.append(Character.toUpperCase(c));
        i = 0;
      }
      for (;;)
      {
        j += 1;
        break;
        if (Character.isWhitespace(c)) {
          i = 1;
        }
        localStringBuilder.append(c);
      }
    }
    return localStringBuilder.toString();
  }
  
  public static String getAndroidVersion()
  {
    return "Android: " + Build.VERSION.RELEASE + " SDK: " + Build.VERSION.SDK_INT;
  }
  
  public static ArrayList<AppData> getAppList(Context paramContext)
  {
    if (app_list != null) {
      return app_list;
    }
    app_list = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    if (i < paramContext.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
      if ((localPackageInfo.versionName == null) || (localPackageInfo.versionName.equals("null")) || (localPackageInfo.packageName == null) || (localPackageInfo.packageName.equals("null"))) {}
      for (;;)
      {
        i += 1;
        break;
        if ((localPackageInfo.packageName.indexOf("com.tutotoons") != -1) || (localPackageInfo.packageName.indexOf("com.tutoplay.app") != -1) || (localPackageInfo.packageName.indexOf("com.cuteandtinybabygames") != -1)) {
          app_list.add(new AppData(localPackageInfo.packageName, localPackageInfo.versionName));
        }
      }
    }
    return app_list;
  }
  
  public static String getAppVersion(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 128).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext = TrackEvent.buildErrorMessage("Data", "getAppVersion", "NameNotFoundException", null, null, Log.getStackTraceString(paramContext));
      TrackEvent.dispatchEventError(TutoAds.getErrorEventLink(), paramContext);
    }
    return "null";
  }
  
  public static boolean getBooleanValue(Context paramContext, String paramString, Boolean paramBoolean)
  {
    initSharedPreferences(paramContext);
    if (shared_preferences == null) {
      return paramBoolean.booleanValue();
    }
    return shared_preferences.getBoolean(paramString, paramBoolean.booleanValue());
  }
  
  public static String getBuilderVersion(Context paramContext)
  {
    return AppConfigReader.getBuilderVersion(paramContext);
  }
  
  public static String getBundleID(Context paramContext)
  {
    return paramContext.getPackageName();
  }
  
  public static String getDeviceName()
  {
    String str1 = Build.MANUFACTURER;
    String str2 = Build.MODEL;
    if (str2.startsWith(str1)) {
      return capitalizeString(str2);
    }
    return capitalizeString(str1) + " " + str2;
  }
  
  public static String getPlatformName()
  {
    if (isAmazon().booleanValue()) {
      return "amazon";
    }
    return "android";
  }
  
  public static String getProductionID(Context paramContext)
  {
    return AppConfigReader.getAppProductionID(paramContext);
  }
  
  public static final int getResource(Context paramContext, String paramString1, String paramString2)
  {
    return paramContext.getResources().getIdentifier(paramString1, paramString2, getBundleID(paramContext));
  }
  
  public static int getScreenDPI(Context paramContext)
  {
    return (int)(paramContext.getResources().getDisplayMetrics().density * 160.0F);
  }
  
  public static int getScreenHeight(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().heightPixels;
  }
  
  public static int getScreenWidth(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().widthPixels;
  }
  
  public static boolean inForeground(Context paramContext)
  {
    Object localObject = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    try
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
        if ((localRunningAppProcessInfo.importance == 100) && (paramContext.getPackageName().equalsIgnoreCase(localRunningAppProcessInfo.processName)))
        {
          Logger.d("AirTutoToons", "App is in foreground");
          return true;
        }
      }
    }
    catch (NullPointerException paramContext)
    {
      paramContext = TrackEvent.buildErrorMessage("Data", "inForeground", "NullPointerException", null, null, Log.getStackTraceString(paramContext));
      TrackEvent.dispatchEventError(TutoAds.getErrorEventLink(), paramContext);
      Logger.d("AirTutoToons", "App is not in foreground");
    }
    return false;
  }
  
  public static void init(Context paramContext)
  {
    initSharedPreferences(paramContext);
    supports_hardware_codecs = supportsHardwareMP4Codecs(paramContext);
  }
  
  public static void initSharedPreferences(Context paramContext)
  {
    if (shared_preferences != null) {
      return;
    }
    shared_preferences = paramContext.getApplicationContext().getSharedPreferences(paramContext.getPackageName(), 0);
  }
  
  public static Boolean isAmazon()
  {
    if (Build.MANUFACTURER.equals("Amazon")) {
      return Boolean.valueOf(true);
    }
    return Boolean.valueOf(false);
  }
  
  public static Boolean isFirstSession(Context paramContext)
  {
    if (is_first_session.booleanValue()) {
      return Boolean.valueOf(true);
    }
    if (getBooleanValue(paramContext, "app_first_launch", Boolean.valueOf(true)))
    {
      is_first_session = Boolean.valueOf(true);
      setKeyValue(paramContext, "app_first_launch", false);
      return Boolean.valueOf(true);
    }
    return Boolean.valueOf(false);
  }
  
  public static Boolean isTablet(Activity paramActivity)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    float f1 = localDisplayMetrics.heightPixels / localDisplayMetrics.ydpi;
    float f2 = localDisplayMetrics.widthPixels / localDisplayMetrics.xdpi;
    if (Math.sqrt(f2 * f2 + f1 * f1) >= 6.5D) {
      return Boolean.valueOf(true);
    }
    return Boolean.valueOf(false);
  }
  
  public static void setKeyValue(Context paramContext, String paramString, boolean paramBoolean)
  {
    initSharedPreferences(paramContext);
    if (shared_preferences == null) {
      return;
    }
    shared_preferences.edit().putBoolean(paramString, paramBoolean).apply();
  }
  
  private static boolean supportsHardwareMP4Codecs(Context paramContext)
  {
    if (!getBooleanValue(paramContext, "supports_hardware_codecs", Boolean.valueOf(true))) {}
    for (;;)
    {
      return false;
      paramContext = new String[2];
      paramContext[0] = "OMX.TI.DUCATI1";
      paramContext[1] = "OMX.google";
      try
      {
        MediaCodec localMediaCodec = MediaCodec.createDecoderByType("video/avc");
        int i = 0;
        for (;;)
        {
          if (i >= paramContext.length) {
            break label73;
          }
          boolean bool = localMediaCodec.getName().contains(paramContext[i]);
          if (bool) {
            break;
          }
          i += 1;
        }
        return true;
      }
      catch (IOException paramContext)
      {
        return false;
      }
    }
  }
}
