package com.mologiq.analytics;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

@SuppressLint({"UseSparseArrays", "NewApi"})
public class MologiqDeviceEventsRunnable
  implements Runnable
{
  private final WeakReference<Context> contextReference;
  
  public MologiqDeviceEventsRunnable(Context paramContext)
  {
    this.contextReference = new WeakReference(paramContext);
  }
  
  private void scan(UserState paramUserState, Context paramContext)
  {
    UserSettings localUserSettings;
    Object localObject3;
    Object localObject2;
    Object localObject4;
    Object localObject5;
    label164:
    label396:
    double d1;
    double d2;
    Object localObject6;
    for (;;)
    {
      try
      {
        localUserSettings = UserSettings.getInstance(paramContext);
        if (localUserSettings.isEnableInstalledApps())
        {
          localObject3 = null;
          localObject1 = null;
          localObject2 = paramContext.getPackageManager().getInstalledApplications(128);
          localObject4 = paramUserState.getPackageToAppInfoLookup(paramContext);
          localObject5 = ((List)localObject2).iterator();
          if (((Iterator)localObject5).hasNext()) {
            continue;
          }
          paramUserState.setInstalledApps((List)localObject3);
          paramUserState.setClassifications((Map)localObject1);
        }
      }
      catch (Exception paramUserState)
      {
        boolean bool;
        int i;
        Utils.bb(paramUserState.getStackTrace().toString());
        return;
      }
      for (;;)
      {
        try
        {
          localObject1 = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 0);
          localObject2 = paramContext.getApplicationContext().getPackageManager();
          if ((localObject1 != null) && (localObject2 != null)) {
            paramUserState.setProduct(((PackageManager)localObject2).getApplicationLabel((ApplicationInfo)localObject1).toString());
          }
        }
        catch (Exception localException1)
        {
          Utils.bb("Application name not found");
          break;
          paramUserState.setAndroidid(Settings.Secure.getString(paramContext.getContentResolver(), "android_id"));
          break label164;
        }
        try
        {
          localObject1 = AdvertisingId.getInstance(paramContext);
          localObject2 = ((AdvertisingId)localObject1).getAdvertisingId();
          bool = ((AdvertisingId)localObject1).isLimitAdTrackingEnabled();
          if ((localObject2 == null) || (((String)localObject2).length() <= 0)) {
            continue;
          }
          paramUserState.setAndroidadvertisingid((String)localObject2);
          paramUserState.setAndroidadvertisingidoptout(bool);
        }
        catch (Exception localException2)
        {
          break label164;
          localObject5 = (ScanResult)((Iterator)localObject4).next();
          if ((localObject5 == null) || (((ScanResult)localObject5).SSID == null)) {
            break label396;
          }
          localObject6 = new WifiState();
          ((WifiState)localObject6).setSsid(((ScanResult)localObject5).SSID);
          ((List)localObject3).add(localObject6);
          break label396;
          Location localLocation = null;
          if ((!Utils.isPermissionGranted(paramContext, "android.permission.ACCESS_FINE_LOCATION")) && (!Utils.isPermissionGranted(paramContext, "android.permission.ACCESS_COARSE_LOCATION"))) {
            break label1022;
          }
          localLocation = ((LocationManager)localObject2).getLastKnownLocation("network");
          if (localLocation == null) {
            return;
          }
          d1 = localLocation.getLatitude();
          d2 = localLocation.getLongitude();
          paramContext = (int)(localUserSettings.getLocationMask() * d1) + (int)(localUserSettings.getLocationMask() * d2);
          if (((int)(paramUserState.getLastLatitude() * localUserSettings.getLocationMask()) + (int)(paramUserState.getLastLongitude() * localUserSettings.getLocationMask())).equals(paramContext)) {
            break label1134;
          }
          paramUserState.setLastLatitude(d1);
          paramUserState.setLastLongitude(d2);
          paramUserState.setLocationAccuracy(localLocation.getAccuracy());
          paramUserState.setLocationAltitude(localLocation.getAltitude());
          paramUserState.setLocationSpeed(localLocation.getSpeed());
          paramUserState.setLocationTimestamp(localLocation.getTime());
        }
      }
      paramUserState.setOs(Build.VERSION.RELEASE);
      paramUserState.setModel(Build.MODEL);
      paramUserState.setDevice(Build.DEVICE);
      paramUserState.setManufacturer(Build.MANUFACTURER);
      paramUserState.setBrand(Build.BRAND);
      Object localObject1 = TimeZone.getDefault();
      localObject2 = ((TimeZone)localObject1).getDisplayName(Locale.getDefault());
      if (localObject2 != null) {
        paramUserState.setTimezone((String)localObject2);
      }
      localObject1 = ((TimeZone)localObject1).getID();
      if (localObject1 != null) {
        paramUserState.setTimezoneId((String)localObject1);
      }
      paramUserState.setCountryCode(Locale.getDefault().getCountry());
      if ((paramContext instanceof Activity))
      {
        localObject1 = paramContext.getApplicationContext().getResources().getDisplayMetrics();
        if (localObject1 != null) {
          paramUserState.setDeviceResolution(((DisplayMetrics)localObject1).widthPixels + "*" + ((DisplayMetrics)localObject1).heightPixels);
        }
      }
      paramUserState.setLanguage(Locale.getDefault().getDisplayLanguage());
      if ((Utils.isPermissionGranted(paramContext, "android.permission.ACCESS_WIFI_STATE")) && (localUserSettings.isEnableNetworkInfo()))
      {
        paramUserState.getClass();
        localObject1 = new UserState.NetworkInfo(paramUserState);
        localObject2 = (WifiManager)paramContext.getSystemService("wifi");
        localObject4 = ((WifiManager)localObject2).getScanResults();
        localObject3 = new ArrayList();
        if (localObject4 != null)
        {
          localObject4 = ((List)localObject4).iterator();
          if (((Iterator)localObject4).hasNext()) {
            break label932;
          }
          ((UserState.NetworkInfo)localObject1).setWifilist((List)localObject3);
          localObject2 = ((WifiManager)localObject2).getConnectionInfo();
          localObject3 = new WifiState();
          ((WifiState)localObject3).setSsid(((WifiInfo)localObject2).getSSID());
          ((UserState.NetworkInfo)localObject1).setWificurrent((WifiState)localObject3);
          paramUserState.setNetworkInfo((UserState.NetworkInfo)localObject1);
        }
        paramUserState.setCarrier(((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperatorName());
      }
      paramUserState.setApi(String.valueOf(Version.getOSVersion()));
      if (!localUserSettings.isEnableLocation()) {
        return;
      }
      localObject2 = (LocationManager)paramContext.getSystemService("location");
      localObject1 = null;
      if (Utils.isPermissionGranted(paramContext, "android.permission.ACCESS_FINE_LOCATION")) {
        localObject1 = ((LocationManager)localObject2).getLastKnownLocation("gps");
      }
      if (localObject1 == null) {
        break label989;
      }
      d1 = ((Location)localObject1).getLatitude();
      d2 = ((Location)localObject1).getLongitude();
      paramContext = (int)(localUserSettings.getLocationMask() * d1) + (int)(localUserSettings.getLocationMask() * d2);
      if (!((int)(paramUserState.getLastLatitude() * localUserSettings.getLocationMask()) + (int)(paramUserState.getLastLongitude() * localUserSettings.getLocationMask())).equals(paramContext))
      {
        paramUserState.setLastLatitude(d1);
        paramUserState.setLastLongitude(d2);
      }
      paramUserState.setLocationAccuracy(((Location)localObject1).getAccuracy());
      paramUserState.setLocationAltitude(((Location)localObject1).getAltitude());
      paramUserState.setLocationSpeed(((Location)localObject1).getSpeed());
      paramUserState.setLocationTimestamp(((Location)localObject1).getTime());
      return;
      localObject6 = (ApplicationInfo)((Iterator)localObject5).next();
      if (((Map)localObject4).containsKey(((ApplicationInfo)localObject6).packageName))
      {
        localObject2 = localObject3;
        if (localObject3 == null) {
          localObject2 = new ArrayList();
        }
        localObject6 = (AppInfo)((Map)localObject4).get(((ApplicationInfo)localObject6).packageName);
        ((List)localObject2).add(Integer.valueOf(((AppInfo)localObject6).getId()));
        localObject3 = localObject2;
        if (((AppInfo)localObject6).getClassificationid() > 0)
        {
          localObject3 = localObject1;
          if (localObject1 == null) {
            localObject3 = new HashMap();
          }
          if (((Map)localObject3).containsKey(Integer.valueOf(((AppInfo)localObject6).getClassificationid())))
          {
            i = ((Integer)((Map)localObject3).get(Integer.valueOf(((AppInfo)localObject6).getClassificationid()))).intValue();
            ((Map)localObject3).put(Integer.valueOf(((AppInfo)localObject6).getClassificationid()), Integer.valueOf(i + 1));
            localObject1 = localObject3;
            localObject3 = localObject2;
          }
          else
          {
            ((Map)localObject3).put(Integer.valueOf(((AppInfo)localObject6).getClassificationid()), Integer.valueOf(1));
            localObject1 = localObject3;
            localObject3 = localObject2;
          }
        }
      }
    }
    label932:
    label989:
    label1022:
    label1134:
    return;
  }
  
  public void run()
  {
    Context localContext = null;
    do
    {
      try
      {
        if (this.contextReference == null) {
          continue;
        }
        localContext = (Context)this.contextReference.get();
      }
      catch (Exception localException)
      {
        Utils.bb(localException.getStackTrace().toString());
        return;
      }
      UserSettings localUserSettings = UserSettings.getInstance(localContext);
      if (localUserSettings.isStopForGood()) {
        break;
      }
      UserState localUserState = UserState.getInstance();
      localUserState.loadFromDisk(localContext);
      scan(localUserState, localContext);
      if ((!localUserState.checkForStateChange(localContext)) && (System.currentTimeMillis() - localUserSettings.getDeviceStateTimestamp() <= localUserSettings.getTtlDeviceStateInMs())) {
        break;
      }
      Utils localUtils = new Utils(localContext);
      if (localUserSettings.getPolicy() == 0)
      {
        str1 = localUtils.postUrl(localUserSettings.getDeviceEventsInitAndroidUrl(), "", localContext, 500, 1000, false);
        if ((str1 == null) || (str1.length() <= 0)) {
          break;
        }
        localUserSettings.setPolicy(Integer.parseInt(str1));
        localUserSettings.checkForStateChange(localContext);
        return;
      }
      String str2 = localUserSettings.getDeviceEventsAndroidUrl();
      DeviceEventsAndroidPostData localDeviceEventsAndroidPostData = new DeviceEventsAndroidPostData();
      localDeviceEventsAndroidPostData.setV("1.2.9");
      localDeviceEventsAndroidPostData.setD("2014-07-08");
      if (localException.getPackageName() == null) {}
      for (String str1 = "";; str1 = localException.getPackageName())
      {
        localDeviceEventsAndroidPostData.setP(str1);
        localDeviceEventsAndroidPostData.setDe(localUserState);
        str1 = localUtils.postUrl(str2, localDeviceEventsAndroidPostData.toJson(localException), localException, 500, 1000, true);
        if ((str1 != null) && (str1.length() > 0))
        {
          localUserSettings.fromJsonNetwork(str1);
          localUserSettings.setDeviceStateTimestamp(System.currentTimeMillis());
          localUserSettings.checkForStateChange(localException);
        }
        scan(localUserState, localException);
        localUserState.checkForStateChange(localException);
        return;
      }
    } while (localException != null);
  }
}
