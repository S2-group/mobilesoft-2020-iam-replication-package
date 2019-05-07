package com.iovation.mobile.android.details.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import com.iovation.mobile.android.details.d;
import com.iovation.mobile.android.details.k;
import com.iovation.mobile.android.details.l;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class a
  implements d
{
  public static Location d;
  public Context a;
  public int b = 0;
  public final com.iovation.mobile.android.details.a.a.a c = new com.iovation.mobile.android.details.a.a.a(this);
  
  public a() {}
  
  private static ArrayList<String> a(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledApplications(128).iterator();
    for (;;)
    {
      ApplicationInfo localApplicationInfo;
      if (localIterator.hasNext()) {
        localApplicationInfo = (ApplicationInfo)localIterator.next();
      }
      try
      {
        String[] arrayOfString = localPackageManager.getPackageInfo(localApplicationInfo.packageName, 4096).requestedPermissions;
        if (arrayOfString != null)
        {
          int j = arrayOfString.length;
          int i = 0;
          while (i < j)
          {
            if ((arrayOfString[i].equals("android.permission.ACCESS_MOCK_LOCATION")) && (!localApplicationInfo.packageName.equals(paramContext.getPackageName()))) {
              localArrayList.add(localApplicationInfo.packageName);
            }
            i += 1;
          }
          return localArrayList;
        }
      }
      catch (PackageManager.NameNotFoundException|Exception localNameNotFoundException) {}
    }
  }
  
  private static int b(Context paramContext)
  {
    try
    {
      int i = Settings.Secure.getInt(paramContext.getContentResolver(), "mock_location");
      if (i == 1) {
        return 1;
      }
      return 0;
    }
    catch (Settings.SettingNotFoundException paramContext)
    {
      for (;;) {}
    }
    return -1;
  }
  
  public final String a()
  {
    return "Location";
  }
  
  public final void a(Context paramContext, k paramK)
  {
    LocationManager localLocationManager;
    String str;
    int i;
    if (l.a("android.permission.ACCESS_FINE_LOCATION", paramContext))
    {
      localLocationManager = (LocationManager)paramContext.getSystemService("location");
      str = "gps";
      i = 0;
    }
    try
    {
      bool1 = localLocationManager.isProviderEnabled("gps");
    }
    catch (Exception localException1)
    {
      boolean bool1;
      for (;;) {}
    }
    bool1 = false;
    try
    {
      boolean bool2 = localLocationManager.isProviderEnabled("network");
      i = bool2;
    }
    catch (Exception localException2)
    {
      Object localObject;
      for (;;) {}
    }
    paramK.a("LSEN", "TRUE");
    if ((bool1) && (i == 0)) {}
    for (localObject = "GPS";; localObject = "BOTH")
    {
      paramK.a("LSG", (String)localObject);
      localObject = "gps";
      break;
      if ((!bool1) && (i != 0))
      {
        paramK.a("LSG", "NET");
        localObject = "network";
        break;
      }
      if ((!bool1) && (i == 0))
      {
        paramK.a("LSG", "NONE");
        localObject = str;
        break;
      }
      localObject = str;
      if (!bool1) {
        break;
      }
      localObject = str;
      if (i == 0) {
        break;
      }
    }
    if (d == null) {
      d = localLocationManager.getLastKnownLocation((String)localObject);
    }
    if (d == null)
    {
      localObject = localLocationManager.getLastKnownLocation("network");
      d = (Location)localObject;
      if (localObject == null) {
        return;
      }
    }
    localObject = a(paramContext);
    paramK.a("LAT", Double.toString(d.getLatitude()));
    paramK.a("LON", Double.toString(d.getLongitude()));
    paramK.a("ALT", Double.toString(d.getAltitude()));
    paramK.a("GLA", Float.toString(d.getAccuracy()));
    paramK.a("GLD", Long.toString(d.getTime()));
    paramK.a("MOCK", Integer.toString(((ArrayList)localObject).size()));
    paramK.a("MLS", Integer.toString(b(paramContext)));
    localObject = "NMEA";
    for (paramContext = Integer.toString(this.b);; paramContext = "FALSE")
    {
      paramK.a((String)localObject, paramContext);
      return;
      localObject = "LSEN";
    }
  }
  
  public final void b(final Context paramContext, k paramK)
  {
    if (l.a("android.permission.ACCESS_FINE_LOCATION", paramContext))
    {
      this.b = 0;
      this.a = paramContext;
      paramContext = (LocationManager)paramContext.getSystemService("location");
      paramK = Looper.myLooper();
      if (paramContext.isProviderEnabled("network")) {
        paramContext.requestLocationUpdates("network", 1000L, 100.0F, this.c, paramK);
      }
      if (paramContext.isProviderEnabled("gps"))
      {
        paramContext.requestLocationUpdates("gps", 1000L, 100.0F, this.c, paramK);
        paramContext.addNmeaListener(this.c);
      }
      new Handler(paramK).postDelayed(new Runnable()
      {
        public final void run()
        {
          paramContext.removeUpdates(a.this.c);
        }
      }, 30000L);
    }
  }
}
