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
import java.util.Locale;
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
      localObject2 = Settings.Secure.getString(UnityAdsProperties.APPLICATION_CONTEXT.getContentResolver(), "android_id");
      Object localObject1 = localObject2;
      if (paramBoolean)
      {
        localObject1 = UnityAdsUtils.Md5((String)localObject2).toLowerCase(Locale.US);
        return localObject1;
      }
    }
    catch (Exception localException)
    {
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Problems fetching androidId: ");
      ((StringBuilder)localObject2).append(localException.getMessage());
      UnityAdsDeviceLog.error(((StringBuilder)localObject2).toString());
      String str = "unknown";
      return str;
    }
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
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Build.MANUFACTURER);
    localStringBuilder.append(" ");
    localStringBuilder.append(Build.MODEL);
    return localStringBuilder.toString();
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
    Object localObject = new JSONObject();
    try
    {
      ((JSONObject)localObject).put("games", paramMap);
      paramMap = ((JSONObject)localObject).toString();
      return paramMap;
    }
    catch (Exception paramMap)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Exception in getPackageDataJson");
      ((StringBuilder)localObject).append(paramMap);
      UnityAdsDeviceLog.debug(((StringBuilder)localObject).toString());
    }
    return null;
  }
  
  private static JSONArray getPackageJsonArray(Map<String, String> paramMap)
  {
    Object localObject1 = null;
    if (paramMap != null)
    {
      if (paramMap.size() == 0) {
        return null;
      }
      Iterator localIterator = UnityAdsProperties.APPLICATION_CONTEXT.getPackageManager().getInstalledPackages(0).iterator();
      Object localObject2;
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        Object localObject4 = localObject1;
        try
        {
          if (localPackageInfo.packageName != null)
          {
            localObject4 = localObject1;
            if (localPackageInfo.packageName.length() > 0)
            {
              localObject4 = localObject1;
              localObject3 = UnityAdsUtils.Md5(localPackageInfo.packageName);
              localObject4 = localObject1;
              if (paramMap.containsKey(localObject3))
              {
                localObject4 = localObject1;
                paramMap.get(localObject3);
                localObject4 = localObject1;
                JSONObject localJSONObject = new JSONObject();
                localObject4 = localObject1;
                localJSONObject.put("id", paramMap.get(localObject3));
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
                localObject1 = localObject3;
              }
            }
          }
        }
        catch (Exception localException)
        {
          Object localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append("Exception when processing package ");
          ((StringBuilder)localObject3).append(localPackageInfo.packageName);
          ((StringBuilder)localObject3).append(" ");
          ((StringBuilder)localObject3).append(localException);
          UnityAdsDeviceLog.debug(((StringBuilder)localObject3).toString());
          localObject2 = localObject4;
        }
      }
      return localObject2;
    }
    return null;
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
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("");
    localStringBuilder.append(Build.VERSION.SDK_INT);
    return localStringBuilder.toString();
  }
  
  public static boolean isActiveNetworkConnected()
  {
    Object localObject = (ConnectivityManager)UnityAdsProperties.APPLICATION_CONTEXT.getSystemService("connectivity");
    boolean bool2 = false;
    if (localObject != null)
    {
      localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
      boolean bool1 = bool2;
      if (localObject != null)
      {
        bool1 = bool2;
        if (((NetworkInfo)localObject).isConnected()) {
          bool1 = true;
        }
      }
      return bool1;
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
    boolean bool2 = false;
    if (localConnectivityManager == null) {
      return false;
    }
    TelephonyManager localTelephonyManager = (TelephonyManager)UnityAdsProperties.APPLICATION_CONTEXT.getSystemService("phone");
    NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
    if ((localNetworkInfo != null) && (localConnectivityManager.getBackgroundDataSetting()) && (localConnectivityManager.getActiveNetworkInfo().isConnected()))
    {
      if (localTelephonyManager == null) {
        return false;
      }
      boolean bool1 = bool2;
      if (localNetworkInfo.getType() == 1)
      {
        bool1 = bool2;
        if (localNetworkInfo.isConnected()) {
          bool1 = true;
        }
      }
      return bool1;
    }
    return false;
  }
}
