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
import org.json.JSONArray;
import org.json.JSONObject;

public class Reveal
{
  public static final String BLUETOOTH_SCAN_DURATION_KEY = "BLUETOOTH_SCAN_DURATION_KEY";
  public static final String BLUETOOTH_SCAN_INTERVAL_KEY = "BLUETOOTH_SCAN_INTERVAL_KEY";
  public static final String FOUND_BEACONS_PREFERENCE_KEY = "FOUND_BEACONS";
  public static final String FOUND_BEACONS_PREFERENCE_NAME = "Reveal_Beacon_Preferences";
  private static Reveal a = null;
  private String b = null;
  private String c = "http://sdk.revealmobile.com";
  private Boolean d = Boolean.valueOf(false);
  private Boolean e = Boolean.valueOf(true);
  private Boolean f = Boolean.valueOf(true);
  private List<String> g = new ArrayList();
  private List<String> h = new ArrayList();
  private RevealScanBootstrap i;
  
  private Reveal() {}
  
  private static List<String> a(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    List localList = paramContext.getPackageManager().getInstalledPackages(0);
    int j = 0;
    for (;;)
    {
      if (j < localList.size())
      {
        Object localObject1 = (PackageInfo)localList.get(j);
        if (((PackageInfo)localObject1).versionName != null) {}
        try
        {
          localObject1 = ((PackageInfo)localObject1).applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
          if (localObject1 != null) {
            localArrayList.add(localObject1);
          }
          j += 1;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            RevealLogger.v(localException);
            Object localObject2 = null;
          }
        }
      }
    }
    return localArrayList;
  }
  
  private void a(final Context paramContext, final Boolean paramBoolean)
  {
    if (paramContext == null) {
      throw new RuntimeException("Application Context passed into Reveal must not be null");
    }
    if (this.b == null) {
      throw new RuntimeException("The Reveal SDK Requires an API Key to start");
    }
    if (AdUtils.getAdvertisingId(paramContext).isEmpty()) {
      AdUtils.setupAdvertisingId(paramContext);
    }
    RevealNetworkClient.registerDevice(paramContext, new RevealNetworkClientCallback()
    {
      public final void onFailure(String paramAnonymousString)
      {
        RevealLogger.v("error " + paramAnonymousString);
      }
      
      public final void onSuccess(JSONObject paramAnonymousJSONObject)
      {
        Object localObject = paramAnonymousJSONObject.optJSONArray("personas");
        if (localObject != null) {
          Reveal.a(Reveal.this, (JSONArray)localObject);
        }
        localObject = Boolean.valueOf(paramAnonymousJSONObject.optBoolean("discovery_enabled"));
        if ((localObject != null) && (((Boolean)localObject).booleanValue()))
        {
          if ((Reveal.a(Reveal.this).booleanValue()) && (paramBoolean.booleanValue())) {
            Reveal.a(Reveal.this, paramContext);
          }
          if (Reveal.b(Reveal.this).booleanValue())
          {
            int i = paramAnonymousJSONObject.optInt("scan_interval");
            int j = paramAnonymousJSONObject.optInt("scan_length");
            Reveal.a(Reveal.this, paramContext, Integer.valueOf(i * 1000), Integer.valueOf(j * 1000));
          }
          return;
        }
        Reveal.b(Reveal.this, paramContext);
      }
    });
    if (paramBoolean.booleanValue())
    {
      paramContext = paramContext.getSharedPreferences("Reveal_Beacon_Preferences", 0).edit();
      paramContext.putStringSet("FOUND_BEACONS", new HashSet());
      paramContext.commit();
    }
  }
  
  public static Reveal getInstance()
  {
    try
    {
      if (a == null) {
        a = new Reveal();
      }
      Reveal localReveal = a;
      return localReveal;
    }
    finally {}
  }
  
  public String getAPIBaseURL()
  {
    return this.c;
  }
  
  public String getAPIKey()
  {
    return this.b;
  }
  
  public Boolean getIsAppScanningEnabled()
  {
    return this.e;
  }
  
  public Boolean getIsBeaconScanningEnabled()
  {
    return this.f;
  }
  
  public Boolean getIsDebug()
  {
    return this.d;
  }
  
  public List<String> getPersonas()
  {
    return this.h;
  }
  
  public void restart(Context paramContext)
  {
    a(paramContext, Boolean.valueOf(false));
  }
  
  public void sendDiscoveryOfBeacon(Context paramContext, RevealBeacon paramRevealBeacon)
  {
    RevealNetworkClient.sendNotificationOfBeacon(paramContext, paramRevealBeacon, new RevealNetworkClientCallback()
    {
      public final void onFailure(String paramAnonymousString)
      {
        RevealLogger.v("error " + paramAnonymousString);
      }
      
      public final void onSuccess(JSONObject paramAnonymousJSONObject)
      {
        paramAnonymousJSONObject = paramAnonymousJSONObject.optJSONArray("personas");
        if (paramAnonymousJSONObject != null) {
          Reveal.a(Reveal.this, paramAnonymousJSONObject);
        }
      }
    });
  }
  
  public void setAPIKey(String paramString)
  {
    this.b = paramString;
  }
  
  public void setDebug(Boolean paramBoolean)
  {
    this.d = paramBoolean;
  }
  
  public void setDebugUUIDs(List<String> paramList)
  {
    this.g = paramList;
  }
  
  public void setIsAppScanningEnabled(Boolean paramBoolean)
  {
    this.e = paramBoolean;
  }
  
  public void setIsBeaconScanningEnabled(Boolean paramBoolean)
  {
    this.f = paramBoolean;
  }
  
  public void setServiceType(ServiceType paramServiceType)
  {
    if (paramServiceType == ServiceType.SANDBOX)
    {
      this.c = "http://sandboxsdk.revealmobile.com";
      return;
    }
    this.c = "http://sdk.revealmobile.com";
  }
  
  public void start(Context paramContext)
  {
    a(paramContext, Boolean.valueOf(true));
  }
}
