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
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

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
    try
    {
      if (Looper.myLooper() == Looper.getMainLooper()) {
        Utils.log("MoLogiQRunnable inside main thread");
      }
      Context localContext = null;
      if (this.contextReference != null) {
        localContext = (Context)this.contextReference.get();
      }
      label117:
      label695:
      label738:
      label890:
      while (localContext != null)
      {
        UserSettings localUserSettings = UserSettings.getInstance(localContext);
        Object localObject3 = UserState.getInstance();
        Object localObject2;
        Object localObject4;
        double d1;
        double d2;
        if (localUserSettings.isEnableInstalledApps())
        {
          localObject1 = new ArrayList();
          localObject2 = new HashSet();
          localObject4 = localUserSettings.getAppFilters().iterator();
          if (!((Iterator)localObject4).hasNext())
          {
            localObject4 = localContext.getPackageManager().getInstalledApplications(128).iterator();
            if (((Iterator)localObject4).hasNext()) {
              break label695;
            }
            ((UserState)localObject3).setInstalledApps(AppId.serialize((List)localObject1));
          }
        }
        else
        {
          if (((UserState)localObject3).getDeviceId() == null) {
            ((UserState)localObject3).setDeviceId(Settings.Secure.getString(localContext.getContentResolver(), "android_id"));
          }
          if (localUserSettings.isEnableLocation())
          {
            localObject2 = (LocationManager)localContext.getSystemService("location");
            localObject1 = null;
            if (Utils.isPermissionGranted(localContext, "android.permission.ACCESS_FINE_LOCATION")) {
              localObject1 = ((LocationManager)localObject2).getLastKnownLocation("gps");
            }
            if (localObject1 == null) {
              break label738;
            }
            d1 = ((Location)localObject1).getLatitude();
            d2 = ((Location)localObject1).getLongitude();
            localObject1 = (int)(localUserSettings.getLocationMask() * d1) + (int)(localUserSettings.getLocationMask() * d2);
            if (!((int)(((UserState)localObject3).getLastLatitude() * localUserSettings.getLocationMask()) + (int)(((UserState)localObject3).getLastLongitude() * localUserSettings.getLocationMask())).equals(localObject1))
            {
              ((UserState)localObject3).setLastLatitude(d1);
              ((UserState)localObject3).setLastLongitude(d2);
            }
          }
        }
        Iterator localIterator;
        for (;;)
        {
          ((UserState)localObject3).setOs(String.valueOf(Version.getOSVersion()));
          ((UserState)localObject3).setDevice(Build.DEVICE);
          ((UserState)localObject3).setBrand(Build.BRAND);
          ((UserState)localObject3).setModel(Build.MODEL);
          ((UserState)localObject3).setProduct(Build.PRODUCT);
          ((UserState)localObject3).setApi(String.valueOf(Version.getOSVersion()));
          ((UserState)localObject3).setLanguage(Locale.getDefault().getDisplayLanguage());
          if ((Utils.isPermissionGranted(localContext, "android.permission.ACCESS_WIFI_STATE")) && ((localUserSettings.isEnableWifiList()) || (localUserSettings.isEnableWifiConnected())))
          {
            localObject5 = (WifiManager)localContext.getSystemService("wifi");
            localObject1 = ((WifiManager)localObject5).getScanResults();
            if (localObject1 != null)
            {
              localObject4 = new ArrayList();
              localIterator = ((List)localObject1).iterator();
              if (localIterator.hasNext()) {
                break label890;
              }
              localObject1 = ((WifiManager)localObject5).getConnectionInfo();
              new HashMap().put(((WifiInfo)localObject1).getSSID(), ((WifiInfo)localObject1).getMacAddress());
              if (localUserSettings.isEnableWifiList()) {
                ((UserState)localObject3).setWifiList(WifiSSID.serialize((ArrayList)localObject4));
              }
              if (localUserSettings.isEnableWifiConnected()) {
                ((UserState)localObject3).setWifiCurrent(((WifiInfo)localObject1).getBSSID() + "~W#" + ((WifiInfo)localObject1).getSSID());
              }
            }
          }
          ((UserState)localObject3).saveToDisk(localContext);
          localObject2 = new Utils(localContext);
          localObject1 = DeviceEvents.getPendingEvents(localContext);
          if (localObject1 == null) {
            break label993;
          }
          localObject3 = localUserSettings.getDeviceEventsUrl();
          localObject4 = new JSONObject();
          ((JSONObject)localObject4).put("de", ((DeviceEventData)localObject1).getData());
          localObject2 = UserSettingsResponse.unserialize(((Utils)localObject2).postUrl((String)localObject3, (JSONObject)localObject4, localContext, 2000, 30000, false));
          localUserSettings.load((Map)localObject2);
          localUserSettings.saveToDisk(localContext);
          if ((localObject2 == null) || (((Map)localObject2).size() <= 0) || (localObject1 == null)) {
            break label993;
          }
          ((DeviceEventData)localObject1).deleteFiles();
          return;
          ((Set)localObject2).add((String)((Iterator)localObject4).next());
          break;
          Object localObject5 = (ApplicationInfo)((Iterator)localObject4).next();
          if (!((Set)localObject2).contains(((ApplicationInfo)localObject5).packageName)) {
            break label117;
          }
          ((List)localObject1).add(((ApplicationInfo)localObject5).packageName);
          break label117;
          localObject1 = null;
          if ((Utils.isPermissionGranted(localContext, "android.permission.ACCESS_FINE_LOCATION")) || (Utils.isPermissionGranted(localContext, "android.permission.ACCESS_COARSE_LOCATION"))) {
            localObject1 = ((LocationManager)localObject2).getLastKnownLocation("network");
          }
          if (localObject1 != null)
          {
            d1 = ((Location)localObject1).getLatitude();
            d2 = ((Location)localObject1).getLongitude();
            localObject1 = (int)(localUserSettings.getLocationMask() * d1) + (int)(localUserSettings.getLocationMask() * d2);
            if (!((int)(((UserState)localObject3).getLastLatitude() * localUserSettings.getLocationMask()) + (int)(((UserState)localObject3).getLastLongitude() * localUserSettings.getLocationMask())).equals(localObject1))
            {
              ((UserState)localObject3).setLastLatitude(d1);
              ((UserState)localObject3).setLastLongitude(d2);
            }
          }
        }
        Object localObject1 = (ScanResult)localIterator.next();
        int i = 0;
        for (;;)
        {
          if (i >= ((ArrayList)localObject4).size())
          {
            ((ArrayList)localObject4).add(localObject1);
            break;
          }
          localObject2 = localObject1;
          if (WifiManager.compareSignalLevel(((ScanResult)((ArrayList)localObject4).get(i)).level, ((ScanResult)localObject1).level) < 0)
          {
            localObject2 = (ScanResult)((ArrayList)localObject4).get(i);
            ((ArrayList)localObject4).add(i, localObject1);
          }
          i += 1;
          localObject1 = localObject2;
        }
      }
      label993:
      return;
    }
    catch (Exception localException) {}
  }
}
