package com.mologiq.analytics;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class MologiqDeviceEventsRunnable
  implements Runnable
{
  private final WeakReference<Context> contextReference;
  
  public MologiqDeviceEventsRunnable(Context paramContext)
  {
    this.contextReference = new WeakReference(paramContext);
  }
  
  public void run()
  {
    label386:
    label560:
    label602:
    label886:
    label1038:
    label1126:
    do
    {
      try
      {
        if (Looper.myLooper() == Looper.getMainLooper()) {
          Utils.log("MoLogiQRunnable inside main thread");
        }
        localContext = null;
        if (this.contextReference == null) {
          continue;
        }
        localContext = (Context)this.contextReference.get();
      }
      catch (Exception localException1)
      {
        Context localContext;
        Utils.log(localException1.getStackTrace().toString());
        return;
      }
      UserSettingsAndroid localUserSettingsAndroid = UserSettingsAndroid.getInstance(localContext);
      if (localUserSettingsAndroid.isStopForGood()) {
        break;
      }
      UserState localUserState = UserState.getInstance();
      localUserState.loadFromDisk(localContext);
      if (localUserSettingsAndroid.isEnableInstalledApps())
      {
        localObject1 = new ArrayList();
        localObject3 = localContext.getPackageManager().getInstalledApplications(128).iterator();
        if (!((Iterator)localObject3).hasNext()) {
          localUserState.setInstalledapps((List)localObject1);
        }
      }
      else
      {
        if ((Utils.isPermissionGranted(localContext, "android.permission.READ_PHONE_STATE")) && (localUserState.getDeviceid() == null)) {
          localUserState.setDeviceid(((TelephonyManager)localContext.getSystemService("phone")).getDeviceId());
        }
        if (localUserState.getAndroidid() == null) {
          localUserState.setAndroidid(Settings.Secure.getString(localContext.getContentResolver(), "android_id"));
        }
      }
      try
      {
        Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
        Class.forName("com.google.android.gms.common.GooglePlayServicesNotAvailableException");
        Class.forName("com.google.android.gms.common.GooglePlayServicesRepairableException");
        i = 1;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        for (;;)
        {
          int i = 0;
          continue;
          i += 1;
          Object localObject2 = localObject3;
        }
      }
      if (i != 0) {}
      try
      {
        localObject1 = new GoogleAdvertisingID(localContext).getAdvId();
        if (localObject1 != null) {
          localUserState.setAndroidadvertisingid((String)localObject1);
        }
      }
      catch (Exception localException2)
      {
        double d1;
        double d2;
        Object localObject4;
        Object localObject5;
        Iterator localIterator;
        for (;;) {}
      }
      if (localUserSettingsAndroid.isEnableLocation())
      {
        localObject3 = (LocationManager)localContext.getSystemService("location");
        localObject1 = null;
        if (Utils.isPermissionGranted(localContext, "android.permission.ACCESS_FINE_LOCATION")) {
          localObject1 = ((LocationManager)localObject3).getLastKnownLocation("gps");
        }
        if (localObject1 == null) {
          break label886;
        }
        d1 = ((Location)localObject1).getLatitude();
        d2 = ((Location)localObject1).getLongitude();
        localObject1 = (int)(localUserSettingsAndroid.getLocationMask() * d1) + (int)(localUserSettingsAndroid.getLocationMask() * d2);
        if (!((int)(localUserState.getLastLatitude() * localUserSettingsAndroid.getLocationMask()) + (int)(localUserState.getLastLongitude() * localUserSettingsAndroid.getLocationMask())).equals(localObject1))
        {
          localUserState.setLastLatitude(d1);
          localUserState.setLastLongitude(d2);
        }
      }
      localUserState.setOs(String.valueOf(Version.getOSVersion()));
      localUserState.setDevice(Build.DEVICE);
      localUserState.setBrand(Build.BRAND);
      localUserState.setModel(Build.MODEL);
      localUserState.setProduct(Build.PRODUCT);
      localUserState.setApi(String.valueOf(Version.getOSVersion()));
      localUserState.setLanguage(Locale.getDefault().getDisplayLanguage());
      if (Utils.isPermissionGranted(localContext, "android.permission.ACCESS_WIFI_STATE"))
      {
        if (localUserState.getMacaddress() == null)
        {
          localObject1 = ((WifiManager)localContext.getSystemService("wifi")).getConnectionInfo();
          if (localObject1 != null) {
            localUserState.setMacaddress(((WifiInfo)localObject1).getMacAddress());
          }
        }
        if ((localUserSettingsAndroid.isEnableWifiList()) || (localUserSettingsAndroid.isEnableWifiConnected()))
        {
          localObject4 = (WifiManager)localContext.getSystemService("wifi");
          localObject1 = ((WifiManager)localObject4).getScanResults();
          if (localObject1 != null)
          {
            localObject5 = new ArrayList();
            localIterator = ((List)localObject1).iterator();
            if (localIterator.hasNext()) {
              break label1038;
            }
            if ((localUserSettingsAndroid.isEnableWifiList()) && (((ArrayList)localObject5).size() > 0))
            {
              localObject1 = new ArrayList();
              localObject3 = ((ArrayList)localObject5).iterator();
              if (((Iterator)localObject3).hasNext()) {
                break label1126;
              }
              localUserState.setWifilist((List)localObject1);
            }
            localObject1 = ((WifiManager)localObject4).getConnectionInfo();
            new HashMap().put(((WifiInfo)localObject1).getSSID(), ((WifiInfo)localObject1).getMacAddress());
            if (localUserSettingsAndroid.isEnableWifiConnected())
            {
              localUserState.getClass();
              localObject3 = new UserState.WifiInfo(localUserState);
              ((UserState.WifiInfo)localObject3).setBssid(((WifiInfo)localObject1).getBSSID());
              ((UserState.WifiInfo)localObject3).setSsid(((WifiInfo)localObject1).getSSID());
              localUserState.setWificurrent((UserState.WifiInfo)localObject3);
            }
          }
        }
      }
      if (!localUserState.checkForStateChange(localContext)) {
        break;
      }
      localObject3 = new Utils(localContext);
      localObject4 = localUserSettingsAndroid.getDeviceEventsAndroidUrl();
      localObject5 = new DeviceEventsAndroidPostData();
      ((DeviceEventsAndroidPostData)localObject5).setV("1.2.4");
      ((DeviceEventsAndroidPostData)localObject5).setD("2014-01-21");
      if (localContext.getPackageName() == null) {}
      for (Object localObject1 = "";; localObject1 = localException1.getPackageName())
      {
        ((DeviceEventsAndroidPostData)localObject5).setP((String)localObject1);
        ((DeviceEventsAndroidPostData)localObject5).setDe(localUserState);
        localObject1 = ((Utils)localObject3).postUrl((String)localObject4, ((DeviceEventsAndroidPostData)localObject5).toJson(), localContext, 1000, 10000, true);
        if ((localObject1 == null) || (((String)localObject1).length() <= 0)) {
          break label1184;
        }
        localUserSettingsAndroid.fromJsonNetwork((String)localObject1);
        localUserSettingsAndroid.checkForStateChange(localContext);
        return;
        localObject4 = (ApplicationInfo)((Iterator)localObject3).next();
        if (!localUserSettingsAndroid.checkAppFilter(((ApplicationInfo)localObject4).packageName)) {
          break;
        }
        ((List)localObject1).add(((ApplicationInfo)localObject4).packageName);
        break;
        localObject1 = null;
        if ((Utils.isPermissionGranted(localException1, "android.permission.ACCESS_FINE_LOCATION")) || (Utils.isPermissionGranted(localException1, "android.permission.ACCESS_COARSE_LOCATION"))) {
          localObject1 = ((LocationManager)localObject3).getLastKnownLocation("network");
        }
        if (localObject1 == null) {
          break label386;
        }
        d1 = ((Location)localObject1).getLatitude();
        d2 = ((Location)localObject1).getLongitude();
        localObject1 = (int)(localUserSettingsAndroid.getLocationMask() * d1) + (int)(localUserSettingsAndroid.getLocationMask() * d2);
        if (((int)(localUserState.getLastLatitude() * localUserSettingsAndroid.getLocationMask()) + (int)(localUserState.getLastLongitude() * localUserSettingsAndroid.getLocationMask())).equals(localObject1)) {
          break label386;
        }
        localUserState.setLastLatitude(d1);
        localUserState.setLastLongitude(d2);
        break label386;
        localObject1 = (ScanResult)localIterator.next();
        i = 0;
        if (i >= ((ArrayList)localObject5).size())
        {
          ((ArrayList)localObject5).add(localObject1);
          break label560;
        }
        localObject3 = localObject1;
        if (WifiManager.compareSignalLevel(((ScanResult)((ArrayList)localObject5).get(i)).level, ((ScanResult)localObject1).level) >= 0) {
          break label1193;
        }
        localObject3 = (ScanResult)((ArrayList)localObject5).get(i);
        ((ArrayList)localObject5).add(i, localObject1);
        break label1193;
        localObject5 = (ScanResult)((Iterator)localObject3).next();
        localUserState.getClass();
        ((List)localObject1).add(new UserState.WifiInfo(localUserState));
        break label602;
      }
    } while (localException1 != null);
    label1184:
  }
}
