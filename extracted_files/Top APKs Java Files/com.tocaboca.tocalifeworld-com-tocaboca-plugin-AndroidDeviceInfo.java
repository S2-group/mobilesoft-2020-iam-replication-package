package com.tocaboca.plugin;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.tocaboca.Logging;
import com.unity3d.player.UnityPlayer;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AndroidDeviceInfo
{
  public static final String LOG_TAG = "AndroidDeviceInfo";
  
  public AndroidDeviceInfo() {}
  
  public static long availableMemory(Activity paramActivity)
  {
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    ((ActivityManager)paramActivity.getSystemService("activity")).getMemoryInfo(localMemoryInfo);
    return localMemoryInfo.availMem / 1048576L;
  }
  
  public static String getAndroidId()
  {
    return Settings.Secure.getString(UnityPlayer.currentActivity.getContentResolver(), "android_id");
  }
  
  public static String getAppBundleId(Activity paramActivity)
  {
    return paramActivity.getPackageName();
  }
  
  public static String getAppDisplayName(Activity paramActivity)
  {
    PackageManager localPackageManager = paramActivity.getPackageManager();
    CharSequence localCharSequence = null;
    try
    {
      paramActivity = localPackageManager.getApplicationInfo(paramActivity.getApplicationInfo().packageName, 0);
    }
    catch (PackageManager.NameNotFoundException paramActivity)
    {
      for (;;) {}
    }
    paramActivity = null;
    if (paramActivity != null) {
      localCharSequence = localPackageManager.getApplicationLabel(paramActivity);
    }
    return (String)localCharSequence;
  }
  
  public static String getAppStoreName(Context paramContext)
  {
    return paramContext.getPackageManager().getInstallerPackageName(paramContext.getPackageName());
  }
  
  public static int getAppVersionCode(Activity paramActivity)
  {
    try
    {
      paramActivity = paramActivity.getPackageManager().getPackageInfo(paramActivity.getPackageName(), 0);
      return paramActivity.versionCode;
    }
    catch (PackageManager.NameNotFoundException paramActivity) {}
    return 0;
  }
  
  public static String getAppVersionName(Activity paramActivity)
  {
    try
    {
      paramActivity = paramActivity.getPackageManager().getPackageInfo(paramActivity.getPackageName(), 0);
      return paramActivity.versionName;
    }
    catch (PackageManager.NameNotFoundException paramActivity)
    {
      for (;;) {}
    }
    return "0";
  }
  
  public static String getArchitecture()
  {
    return System.getProperty("os.arch");
  }
  
  public static String getBoard()
  {
    return Build.BOARD;
  }
  
  public static String getBrand()
  {
    return Build.BRAND;
  }
  
  public static String getCountry()
  {
    return Locale.getDefault().getCountry();
  }
  
  public static String getInstalledAppsWithPackageNamePrefix(Context paramContext, String paramString)
  {
    Iterator localIterator = paramContext.getPackageManager().getInstalledApplications(128).iterator();
    paramContext = null;
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (localApplicationInfo.packageName.startsWith(paramString))
      {
        if (paramContext == null)
        {
          paramContext = "";
        }
        else
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append(paramContext);
          localStringBuilder.append(",");
          paramContext = localStringBuilder.toString();
        }
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramContext);
        localStringBuilder.append(localApplicationInfo.packageName);
        paramContext = localStringBuilder.toString();
      }
    }
    return paramContext;
  }
  
  public static String getInstalledAppsWithPackageNames(Context paramContext, String paramString)
  {
    localObject1 = paramContext.getPackageManager().getInstalledApplications(128);
    paramContext = new Vector();
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext()) {
      paramContext.add(((ApplicationInfo)((Iterator)localObject1).next()).packageName);
    }
    localObject1 = new JSONArray();
    try
    {
      Logging.log("AndroidDeviceInfo", "Deserializing package names..");
      paramString = new JSONArray(paramString);
      Logging.log("AndroidDeviceInfo", "Iterating through package names..");
      int i = 0;
      while (i < paramString.length())
      {
        Object localObject2 = paramString.getJSONObject(i);
        if (((JSONObject)localObject2).isNull("bundle_id")) {
          Logging.logError("AndroidDeviceInfo", "bundle_id key is not found.");
        }
        if (((JSONObject)localObject2).isNull("company_name")) {
          Logging.logError("AndroidDeviceInfo", "company_name key is not found.");
        }
        if (((JSONObject)localObject2).isNull("name")) {
          Logging.logError("AndroidDeviceInfo", "name key is not found.");
        }
        String str1 = ((JSONObject)localObject2).getString("bundle_id");
        String str2 = ((JSONObject)localObject2).getString("company_name");
        localObject2 = ((JSONObject)localObject2).getString("name");
        JSONObject localJSONObject;
        if (paramContext.contains(str1))
        {
          localJSONObject = new JSONObject();
          localJSONObject.put("bundle_id", str1);
          localJSONObject.put("installed", "true");
          localJSONObject.put("company_name", str2);
          localJSONObject.put("name", localObject2);
          ((JSONArray)localObject1).put(localJSONObject);
        }
        else
        {
          localJSONObject = new JSONObject();
          localJSONObject.put("bundle_id", str1);
          localJSONObject.put("installed", "false");
          localJSONObject.put("company_name", str2);
          localJSONObject.put("name", localObject2);
          ((JSONArray)localObject1).put(localJSONObject);
        }
        i += 1;
      }
      return ((JSONArray)localObject1).toString();
    }
    catch (JSONException paramContext)
    {
      Logging.logErrorWithException("AndroidDeviceInfo", "JSONException", paramContext);
    }
  }
  
  public static String getLanguage()
  {
    return Locale.getDefault().getLanguage();
  }
  
  public static String getManufacturer()
  {
    return Build.MANUFACTURER;
  }
  
  public static String getModel()
  {
    return Build.MODEL;
  }
  
  public static int getOSVersion()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static String getProduct()
  {
    return Build.PRODUCT;
  }
  
  public static int getScreenDensityDPI(Activity paramActivity)
  {
    paramActivity = ((WindowManager)paramActivity.getApplicationContext().getSystemService("window")).getDefaultDisplay();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getMetrics(localDisplayMetrics);
    return localDisplayMetrics.densityDpi;
  }
  
  public static String getTimeZone()
  {
    return TimeZone.getDefault().getDisplayName(false, 0);
  }
  
  public static boolean hasCamera(Activity paramActivity)
  {
    return paramActivity.getPackageManager().hasSystemFeature("android.hardware.camera.any");
  }
  
  public static boolean hasFrontCamera(Activity paramActivity)
  {
    return paramActivity.getPackageManager().hasSystemFeature("android.hardware.camera.front");
  }
  
  public static boolean hasRearCamera(Activity paramActivity)
  {
    return paramActivity.getPackageManager().hasSystemFeature("android.hardware.camera");
  }
  
  public static boolean isLowMemory(Activity paramActivity)
  {
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    ((ActivityManager)paramActivity.getSystemService("activity")).getMemoryInfo(localMemoryInfo);
    return localMemoryInfo.lowMemory;
  }
  
  public static boolean isWifiConnected(Activity paramActivity)
  {
    if (paramActivity.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0) {
      return ((ConnectivityManager)paramActivity.getSystemService("connectivity")).getNetworkInfo(1).isConnected();
    }
    return false;
  }
  
  public static int screenPhysicalHeight(Activity paramActivity)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.heightPixels;
  }
  
  public static int screenPhysicalWidth(Activity paramActivity)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels;
  }
  
  @TargetApi(17)
  public static int screenRealPhysicalHeight(Activity paramActivity)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getRealMetrics(localDisplayMetrics);
    return localDisplayMetrics.heightPixels;
  }
  
  @TargetApi(17)
  public static int screenRealPhysicalWidth(Activity paramActivity)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getRealMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels;
  }
  
  @TargetApi(16)
  public static long totalMemory(Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
      ((ActivityManager)paramActivity.getSystemService("activity")).getMemoryInfo(localMemoryInfo);
      return localMemoryInfo.totalMem / 1048576L;
    }
    return -1L;
  }
}
