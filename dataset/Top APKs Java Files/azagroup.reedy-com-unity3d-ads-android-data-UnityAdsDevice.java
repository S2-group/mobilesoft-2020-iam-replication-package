package com.unity3d.ads.android.data;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.UnityAdsUtils;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

@TargetApi(9)
public class UnityAdsDevice
{
  public UnityAdsDevice() {}
  
  public static String getAdvertisingTrackingId()
  {
    return UnityAdsAdvertisingId.getAdvertisingTrackingId();
  }
  
  @SuppressLint({"DefaultLocale"})
  public static String getAndroidId(boolean paramBoolean)
  {
    try
    {
      String str2 = Settings.Secure.getString(UnityAdsProperties.APPLICATION_CONTEXT.getContentResolver(), "android_id");
      String str1 = str2;
      if (paramBoolean) {
        str1 = UnityAdsUtils.Md5(str2).toLowerCase();
      }
      return str1;
    }
    catch (Exception localException)
    {
      UnityAdsDeviceLog.error("Problems fetching androidId: " + localException.getMessage());
    }
    return "unknown";
  }
  
  public static String getConnectionType()
  {
    if (isUsingWifi()) {
      return "wifi";
    }
    return "cellular";
  }
  
  public static int getDeviceType()
  {
    return UnityAdsProperties.APPLICATION_CONTEXT.getResources().getConfiguration().screenLayout;
  }
  
  public static String getHardwareVersion()
  {
    return Build.MANUFACTURER + " " + Build.MODEL;
  }
  
  public static int getNetworkType()
  {
    return ((TelephonyManager)UnityAdsProperties.APPLICATION_CONTEXT.getSystemService("phone")).getNetworkType();
  }
  
  public static String getPackageDataJson(Map<String, String> paramMap)
  {
    paramMap = getPackageJsonArray(paramMap);
    if (paramMap == null) {
      return null;
    }
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("games", paramMap);
      paramMap = localJSONObject.toString();
      return paramMap;
    }
    catch (Exception paramMap)
    {
      UnityAdsDeviceLog.debug("Exception in getPackageDataJson" + paramMap);
    }
    return null;
  }
  
  private static JSONArray getPackageJsonArray(Map<String, String> paramMap)
  {
    Object localObject1 = null;
    Object localObject4 = null;
    Object localObject3 = localObject4;
    if (paramMap != null)
    {
      if (paramMap.size() == 0) {
        localObject3 = localObject4;
      }
    }
    else {
      return localObject3;
    }
    Iterator localIterator = UnityAdsProperties.APPLICATION_CONTEXT.getPackageManager().getInstalledPackages(0).iterator();
    for (;;)
    {
      localObject3 = localObject1;
      if (!localIterator.hasNext()) {
        break;
      }
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      localObject3 = localObject1;
      localObject4 = localObject1;
      try
      {
        if (localPackageInfo.packageName != null)
        {
          localObject3 = localObject1;
          localObject4 = localObject1;
          if (localPackageInfo.packageName.length() > 0)
          {
            localObject4 = localObject1;
            String str = UnityAdsUtils.Md5(localPackageInfo.packageName);
            localObject3 = localObject1;
            localObject4 = localObject1;
            if (paramMap.containsKey(str))
            {
              localObject4 = localObject1;
              paramMap.get(str);
              localObject4 = localObject1;
              JSONObject localJSONObject = new JSONObject();
              localObject4 = localObject1;
              localJSONObject.put("id", paramMap.get(str));
              localObject4 = localObject1;
              if (localPackageInfo.firstInstallTime > 0L)
              {
                localObject4 = localObject1;
                localJSONObject.put("timestamp", localPackageInfo.firstInstallTime);
              }
              localObject3 = localObject1;
              if (localObject1 == null)
              {
                localObject4 = localObject1;
                localObject3 = new JSONArray();
              }
              localObject4 = localObject3;
              ((JSONArray)localObject3).put(localJSONObject);
            }
          }
        }
        localObject1 = localObject3;
      }
      catch (Exception localException)
      {
        UnityAdsDeviceLog.debug("Exception when processing package " + localPackageInfo.packageName + " " + localException);
        Object localObject2 = localObject4;
      }
    }
  }
  
  public static int getScreenDensity()
  {
    return UnityAdsProperties.APPLICATION_CONTEXT.getResources().getDisplayMetrics().densityDpi;
  }
  
  public static int getScreenSize()
  {
    return getDeviceType();
  }
  
  public static String getSoftwareVersion()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static boolean isActiveNetworkConnected()
  {
    Object localObject = (ConnectivityManager)UnityAdsProperties.APPLICATION_CONTEXT.getSystemService("connectivity");
    if (localObject != null)
    {
      localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
      return (localObject != null) && (((NetworkInfo)localObject).isConnected());
    }
    return false;
  }
  
  public static boolean isLimitAdTrackingEnabled()
  {
    return UnityAdsAdvertisingId.getLimitedAdTracking();
  }
  
  public static boolean isUsingWifi()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)UnityAdsProperties.APPLICATION_CONTEXT.getSystemService("connectivity");
    if (localConnectivityManager == null) {
      return false;
    }
    TelephonyManager localTelephonyManager = (TelephonyManager)UnityAdsProperties.APPLICATION_CONTEXT.getSystemService("phone");
    NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
    if ((localNetworkInfo == null) || (!localConnectivityManager.getBackgroundDataSetting()) || (!localConnectivityManager.getActiveNetworkInfo().isConnected()) || (localTelephonyManager == null)) {
      return false;
    }
    return (localNetworkInfo.getType() == 1) && (localNetworkInfo.isConnected());
  }
}
