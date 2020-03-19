package com.stepleaderdigital.reveal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.stepleaderdigital.reveal.model.RevealBeacon;
import com.stepleaderdigital.reveal.network.RevealNetworkClient;
import com.stepleaderdigital.reveal.network.RevealNetworkClientCallback;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.altbeacon.beacon.BeaconManager;
import org.json.JSONArray;
import org.json.JSONObject;

public class Reveal
{
  public static final String BLUETOOTH_SCAN_DURATION_KEY = "BLUETOOTH_SCAN_DURATION_KEY";
  public static final String BLUETOOTH_SCAN_INTERVAL_KEY = "BLUETOOTH_SCAN_INTERVAL_KEY";
  public static final String FOUND_BEACONS_PREFERENCE_KEY = "FOUND_BEACONS";
  public static final String FOUND_BEACONS_PREFERENCE_NAME = "Reveal_Beacon_Preferences";
  private static final String REVEAL_API_BASE_PRODUCTION = "http://sdk.revealmobile.com";
  private static final String REVEAL_API_BASE_SANDBOX = "http://sandboxsdk.revealmobile.com";
  private static Reveal sharedInstance = null;
  private String apiBaseURL = "http://sdk.revealmobile.com";
  private String apiKey = null;
  private BeaconManager beaconManager;
  private List<String> debugUUIDs = new ArrayList();
  private Boolean isAppScanningEnabled = Boolean.valueOf(true);
  private Boolean isBeaconScanningEnabled = Boolean.valueOf(true);
  private Boolean isDebug = Boolean.valueOf(false);
  private List<String> personas = new ArrayList();
  private RevealScanBootstrap revealScanBootstrap;
  
  private Reveal() {}
  
  private void clearBeaconsFound(Context paramContext)
  {
    paramContext = paramContext.getSharedPreferences("Reveal_Beacon_Preferences", 0).edit();
    paramContext.putStringSet("FOUND_BEACONS", new HashSet());
    paramContext.commit();
  }
  
  private String getAppPackageName(Context paramContext)
  {
    if (paramContext != null) {
      return paramContext.getApplicationContext().getPackageName();
    }
    return "";
  }
  
  private List<String> getInstalledApps(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    List localList = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    if (i < localList.size())
    {
      Object localObject2 = (PackageInfo)localList.get(i);
      if ((!paramBoolean) && (((PackageInfo)localObject2).versionName == null)) {}
      for (;;)
      {
        i += 1;
        break;
        Object localObject1 = null;
        try
        {
          localObject2 = ((PackageInfo)localObject2).applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
          localObject1 = localObject2;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            RevealLogger.v(localException);
          }
        }
        if (localObject1 != null) {
          localArrayList.add(localObject1);
        }
      }
    }
    return localArrayList;
  }
  
  public static Reveal getInstance()
  {
    try
    {
      if (sharedInstance == null) {
        sharedInstance = new Reveal();
      }
      Reveal localReveal = sharedInstance;
      return localReveal;
    }
    finally {}
  }
  
  private void registerDevice(final Context paramContext, final Boolean paramBoolean)
  {
    if (paramContext == null) {
      throw new RuntimeException("Application Context passed into Reveal must not be null");
    }
    if (this.apiKey == null) {
      throw new RuntimeException("The Reveal SDK Requires an API Key to start");
    }
    if (AdUtils.getAdvertisingId(paramContext).isEmpty()) {
      AdUtils.setupAdvertisingId(paramContext);
    }
    RevealNetworkClient.registerDevice(paramContext, new RevealNetworkClientCallback()
    {
      public void onFailure(String paramAnonymousString)
      {
        RevealLogger.v("error " + paramAnonymousString);
      }
      
      public void onSuccess(JSONObject paramAnonymousJSONObject)
      {
        Object localObject = paramAnonymousJSONObject.optJSONArray("personas");
        if (localObject != null) {
          Reveal.this.setPersonasWithJSON((JSONArray)localObject);
        }
        localObject = Boolean.valueOf(paramAnonymousJSONObject.optBoolean("discovery_enabled"));
        if ((localObject != null) && (((Boolean)localObject).booleanValue()))
        {
          if ((Reveal.this.isAppScanningEnabled.booleanValue()) && (paramBoolean.booleanValue())) {
            Reveal.this.sendInstalledApps(paramContext);
          }
          if (Reveal.this.isBeaconScanningEnabled.booleanValue())
          {
            int i = paramAnonymousJSONObject.optInt("scan_interval");
            int j = paramAnonymousJSONObject.optInt("scan_length");
            Reveal.this.startBeaconScanning(paramContext, Integer.valueOf(i * 1000), Integer.valueOf(j * 1000));
          }
          return;
        }
        Reveal.this.stopBeaconScanning(paramContext);
      }
    });
    if (paramBoolean.booleanValue()) {
      clearBeaconsFound(paramContext);
    }
  }
  
  private void sendInstalledApps(Context paramContext)
  {
    RevealNetworkClient.sendInstalledApps(paramContext, getInstalledApps(paramContext, false), new RevealNetworkClientCallback()
    {
      public void onFailure(String paramAnonymousString)
      {
        RevealLogger.v("error " + paramAnonymousString);
      }
      
      public void onSuccess(JSONObject paramAnonymousJSONObject)
      {
        paramAnonymousJSONObject = paramAnonymousJSONObject.optJSONArray("personas");
        if (paramAnonymousJSONObject != null) {
          Reveal.this.setPersonasWithJSON(paramAnonymousJSONObject);
        }
      }
    });
  }
  
  private void setPersonasWithJSON(JSONArray paramJSONArray)
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramJSONArray.length())
    {
      localArrayList.add(paramJSONArray.optString(i));
      i += 1;
    }
    this.personas = localArrayList;
  }
  
  private void startBeaconScanning(Context paramContext, Integer paramInteger1, Integer paramInteger2)
  {
    if (Utils.isBluetoothLeSupported(paramContext)) {
      this.revealScanBootstrap = new RevealScanBootstrap(paramContext, paramInteger2, paramInteger1);
    }
  }
  
  private void stopBeaconScanning(Context paramContext)
  {
    if ((Utils.isBluetoothLeSupported(paramContext)) && (this.revealScanBootstrap != null)) {
      this.revealScanBootstrap.shutdown();
    }
  }
  
  public String getAPIBaseURL()
  {
    return this.apiBaseURL;
  }
  
  public String getAPIKey()
  {
    return this.apiKey;
  }
  
  public Boolean getIsAppScanningEnabled()
  {
    return this.isAppScanningEnabled;
  }
  
  public Boolean getIsBeaconScanningEnabled()
  {
    return this.isBeaconScanningEnabled;
  }
  
  public Boolean getIsDebug()
  {
    return this.isDebug;
  }
  
  public List<String> getPersonas()
  {
    return this.personas;
  }
  
  public void restart(Context paramContext)
  {
    registerDevice(paramContext, Boolean.valueOf(false));
  }
  
  public void sendDiscoveryOfBeacon(Context paramContext, RevealBeacon paramRevealBeacon)
  {
    RevealNetworkClient.sendNotificationOfBeacon(paramContext, paramRevealBeacon, new RevealNetworkClientCallback()
    {
      public void onFailure(String paramAnonymousString)
      {
        RevealLogger.v("error " + paramAnonymousString);
      }
      
      public void onSuccess(JSONObject paramAnonymousJSONObject)
      {
        paramAnonymousJSONObject = paramAnonymousJSONObject.optJSONArray("personas");
        if (paramAnonymousJSONObject != null) {
          Reveal.this.setPersonasWithJSON(paramAnonymousJSONObject);
        }
      }
    });
  }
  
  public void setAPIKey(String paramString)
  {
    this.apiKey = paramString;
  }
  
  public void setDebug(Boolean paramBoolean)
  {
    this.isDebug = paramBoolean;
  }
  
  public void setDebugUUIDs(List<String> paramList)
  {
    this.debugUUIDs = paramList;
  }
  
  public void setIsAppScanningEnabled(Boolean paramBoolean)
  {
    this.isAppScanningEnabled = paramBoolean;
  }
  
  public void setIsBeaconScanningEnabled(Boolean paramBoolean)
  {
    this.isBeaconScanningEnabled = paramBoolean;
  }
  
  public void setServiceType(ServiceType paramServiceType)
  {
    if (paramServiceType == ServiceType.SANDBOX)
    {
      this.apiBaseURL = "http://sandboxsdk.revealmobile.com";
      return;
    }
    this.apiBaseURL = "http://sdk.revealmobile.com";
  }
  
  public void start(Context paramContext)
  {
    registerDevice(paramContext, Boolean.valueOf(true));
  }
}
