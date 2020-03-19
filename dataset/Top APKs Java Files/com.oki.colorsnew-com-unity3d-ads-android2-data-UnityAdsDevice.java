package com.unity3d.ads.android2.data;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
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
import com.unity3d.ads.android2.UnityAdsDeviceLog;
import com.unity3d.ads.android2.UnityAdsUtils;
import com.unity3d.ads.android2.properties.UnityAdsProperties;
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
      String str2 = Settings.Secure.getString(UnityAdsProperties.getCurrentActivity().getContentResolver(), "android_id");
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
    return UnityAdsProperties.getCurrentActivity().getResources().getConfiguration().screenLayout;
  }
  
  public static String getHardwareVersion()
  {
    return Build.MANUFACTURER + " " + Build.MODEL;
  }
  
  public static int getNetworkType()
  {
    Activity localActivity = UnityAdsProperties.getCurrentActivity();
    if (localActivity != null) {
      return ((TelephonyManager)localActivity.getSystemService("phone")).getNetworkType();
    }
    return 0;
  }
  
  public static String getPackageDataJson(Map paramMap)
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
  
  public static JSONArray getPackageJsonArray(Map paramMap)
  {
    Object localObject1 = null;
    localObject3 = null;
    localObject2 = localObject3;
    if (paramMap != null)
    {
      if (paramMap.size() != 0) {
        break label23;
      }
      localObject2 = localObject3;
    }
    label23:
    do
    {
      return localObject2;
      localObject4 = UnityAdsProperties.getCurrentActivity();
      localObject2 = localObject3;
    } while (localObject4 == null);
    Object localObject4 = ((Activity)localObject4).getPackageManager().getInstalledPackages(0).iterator();
    for (;;)
    {
      localObject2 = localObject1;
      if (!((Iterator)localObject4).hasNext()) {
        break;
      }
      localPackageInfo = (PackageInfo)((Iterator)localObject4).next();
      localObject2 = localObject1;
      localObject3 = localObject1;
      try
      {
        if (localPackageInfo.packageName != null)
        {
          localObject2 = localObject1;
          localObject3 = localObject1;
          if (localPackageInfo.packageName.length() > 0)
          {
            localObject3 = localObject1;
            String str = UnityAdsUtils.Md5(localPackageInfo.packageName);
            localObject2 = localObject1;
            localObject3 = localObject1;
            if (paramMap.containsKey(str))
            {
              localObject3 = localObject1;
              paramMap.get(str);
              localObject3 = localObject1;
              JSONObject localJSONObject = new JSONObject();
              localObject3 = localObject1;
              localJSONObject.put("id", paramMap.get(str));
              localObject3 = localObject1;
              if (localPackageInfo.firstInstallTime > 0L)
              {
                localObject3 = localObject1;
                localJSONObject.put("timestamp", localPackageInfo.firstInstallTime);
              }
              localObject2 = localObject1;
              if (localObject1 == null)
              {
                localObject3 = localObject1;
                localObject2 = new JSONArray();
              }
              localObject3 = localObject2;
              ((JSONArray)localObject2).put(localJSONObject);
            }
          }
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          UnityAdsDeviceLog.debug("Exception when processing package " + localPackageInfo.packageName + " " + localException);
          localObject2 = localObject3;
        }
      }
      localObject1 = localObject2;
    }
  }
  
  public static int getScreenDensity()
  {
    return UnityAdsProperties.getCurrentActivity().getResources().getDisplayMetrics().densityDpi;
  }
  
  public static int getScreenSize()
  {
    return getDeviceType();
  }
  
  public static String getSoftwareVersion()
  {
    return "" + Build.VERSION.SDK_INT;
  }
  
  public static boolean isLimitAdTrackingEnabled()
  {
    return UnityAdsAdvertisingId.getLimitedAdTracking();
  }
  
  public static boolean isUsingWifi()
  {
    Object localObject = UnityAdsProperties.getCurrentActivity();
    if (localObject == null) {
      return false;
    }
    ConnectivityManager localConnectivityManager = (ConnectivityManager)((Activity)localObject).getSystemService("connectivity");
    if (localConnectivityManager == null) {
      return false;
    }
    TelephonyManager localTelephonyManager = null;
    if (localObject != null) {
      localTelephonyManager = (TelephonyManager)((Activity)localObject).getSystemService("phone");
    }
    localObject = localConnectivityManager.getActiveNetworkInfo();
    if ((localObject == null) || (!localConnectivityManager.getBackgroundDataSetting()) || (!localConnectivityManager.getActiveNetworkInfo().isConnected()) || (localTelephonyManager == null)) {
      return false;
    }
    if (((NetworkInfo)localObject).getType() == 1) {
      return ((NetworkInfo)localObject).isConnected();
    }
    return false;
  }
}
