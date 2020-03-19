package com.sagosago.sagobiz;

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
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceInfo
{
  public static final String LOG_TAG = "DeviceInfo";
  
  public DeviceInfo() {}
  
  public static long availableMemory(Activity paramActivity)
  {
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    ((ActivityManager)paramActivity.getSystemService("activity")).getMemoryInfo(localMemoryInfo);
    return localMemoryInfo.availMem / 1048576L;
  }
  
  public static String getAndroidID(Activity paramActivity)
  {
    return Settings.Secure.getString(paramActivity.getContentResolver(), "android_id");
  }
  
  public static String getAppBundleId(Activity paramActivity)
  {
    return paramActivity.getPackageName();
  }
  
  public static String getAppDisplayName(Activity paramActivity)
  {
    PackageManager localPackageManager = paramActivity.getPackageManager();
    Object localObject = null;
    try
    {
      paramActivity = localPackageManager.getApplicationInfo(paramActivity.getApplicationInfo().packageName, 0);
      if (paramActivity != null) {}
      for (paramActivity = localPackageManager.getApplicationLabel(paramActivity);; paramActivity = null) {
        return (String)paramActivity;
      }
    }
    catch (PackageManager.NameNotFoundException paramActivity)
    {
      for (;;)
      {
        paramActivity = localObject;
      }
    }
  }
  
  public static String getAppStoreName(Activity paramActivity)
  {
    return paramActivity.getPackageManager().getInstallerPackageName(paramActivity.getPackageName());
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
    catch (PackageManager.NameNotFoundException paramActivity) {}
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
  
  public static String getHardware()
  {
    return Build.HARDWARE;
  }
  
  public static String getInstalledAppsWithPackageNamePrefix(Context paramContext, String paramString)
  {
    paramString = paramString.toLowerCase();
    Object localObject = paramContext.getPackageManager();
    paramContext = null;
    localObject = ((PackageManager)localObject).getInstalledApplications(128).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      if (localApplicationInfo.packageName.toLowerCase().startsWith(paramString))
      {
        if (paramContext == null) {}
        for (paramContext = "";; paramContext = paramContext + ",")
        {
          paramContext = paramContext + localApplicationInfo.packageName;
          break;
        }
      }
    }
    return paramContext;
  }
  
  public static String getInstalledAppsWithPackageNames(Context paramContext, String paramString)
  {
    Object localObject1 = paramContext.getPackageManager().getInstalledApplications(128);
    paramContext = new Vector();
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext()) {
      paramContext.add(((ApplicationInfo)((Iterator)localObject1).next()).packageName.toLowerCase());
    }
    localObject1 = new JSONArray();
    for (;;)
    {
      int i;
      try
      {
        SagoBizDebug.log("DeviceInfo", "Deserializing package names..");
        paramString = new JSONArray(paramString);
        SagoBizDebug.log("DeviceInfo", "Iterating through package names..");
        i = 0;
        if (i < paramString.length())
        {
          SagoBizDebug.log("DeviceInfo", "Deserializing json object at index " + i);
          Object localObject2 = paramString.getJSONObject(i);
          SagoBizDebug.log("DeviceInfo", "Getting the bundle_id key.");
          if (((JSONObject)localObject2).isNull("bundle_id")) {
            SagoBizDebug.logError("DeviceInfo", "bundle_id key is not found.");
          }
          if (((JSONObject)localObject2).isNull("company_name")) {
            SagoBizDebug.logError("DeviceInfo", "company_name key is not found.");
          }
          if (((JSONObject)localObject2).isNull("name")) {
            SagoBizDebug.logError("DeviceInfo", "name key is not found.");
          }
          String str1 = ((JSONObject)localObject2).getString("bundle_id");
          String str2 = ((JSONObject)localObject2).getString("company_name");
          localObject2 = ((JSONObject)localObject2).getString("name");
          JSONObject localJSONObject;
          if (paramContext.contains(str1.toLowerCase()))
          {
            SagoBizDebug.log("DeviceInfo", "Creating json object for packagename: " + str1);
            localJSONObject = new JSONObject();
            localJSONObject.put("bundle_id", str1);
            localJSONObject.put("installed", "true");
            localJSONObject.put("company_name", str2);
            localJSONObject.put("name", localObject2);
            SagoBizDebug.log("DeviceInfo", "Adding json object to JSONArray");
            ((JSONArray)localObject1).put(localJSONObject);
          }
          else
          {
            SagoBizDebug.log("DeviceInfo", "Creating json object for packagename: " + str1);
            localJSONObject = new JSONObject();
            localJSONObject.put("bundle_id", str1);
            localJSONObject.put("installed", "false");
            localJSONObject.put("company_name", str2);
            localJSONObject.put("name", localObject2);
            SagoBizDebug.log("DeviceInfo", "Adding json object to JSONArray");
            ((JSONArray)localObject1).put(localJSONObject);
          }
        }
      }
      catch (JSONException paramContext)
      {
        SagoBizDebug.logErrorWithException("DeviceInfo", "JSONException", paramContext);
      }
      return ((JSONArray)localObject1).toString();
      i += 1;
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
    boolean bool = false;
    if (paramActivity.getPackageManager().hasSystemFeature("android.hardware.camera.any")) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean hasFrontCamera(Activity paramActivity)
  {
    boolean bool = false;
    if (paramActivity.getPackageManager().hasSystemFeature("android.hardware.camera.front")) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean hasRearCamera(Activity paramActivity)
  {
    boolean bool = false;
    if (paramActivity.getPackageManager().hasSystemFeature("android.hardware.camera")) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean isLowMemory(Activity paramActivity)
  {
    ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
    ((ActivityManager)paramActivity.getSystemService("activity")).getMemoryInfo(localMemoryInfo);
    return localMemoryInfo.lowMemory;
  }
  
  public static boolean isWifiConnected(Activity paramActivity)
  {
    boolean bool = false;
    if (paramActivity.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0) {
      bool = ((ConnectivityManager)paramActivity.getSystemService("connectivity")).getNetworkInfo(1).isConnected();
    }
    return bool;
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
