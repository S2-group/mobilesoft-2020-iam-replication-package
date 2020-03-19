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
      catch (Exception localException) {}catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
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
    }
    catch (Settings.SettingNotFoundException paramContext)
    {
      return -1;
    }
    return 0;
  }
  
  public final String a()
  {
    return "Location";
  }
  
  public final void a(Context paramContext, k paramK)
  {
    int i = 0;
    if (l.a("android.permission.ACCESS_FINE_LOCATION", paramContext))
    {
      LocationManager localLocationManager = (LocationManager)paramContext.getSystemService("location");
      for (;;)
      {
        try
        {
          bool1 = localLocationManager.isProviderEnabled("gps");
          try
          {
            boolean bool2 = localLocationManager.isProviderEnabled("network");
            i = bool2;
          }
          catch (Exception localException2)
          {
            Object localObject1;
            Object localObject2;
            continue;
            String str = "gps";
            continue;
          }
          paramK.a("LSEN", "TRUE");
          if ((bool1) && (i == 0))
          {
            paramK.a("LSG", "GPS");
            localObject1 = "gps";
            if (d == null) {
              d = localLocationManager.getLastKnownLocation((String)localObject1);
            }
            if (d != null) {
              break;
            }
            localObject1 = localLocationManager.getLastKnownLocation("network");
            d = (Location)localObject1;
            if (localObject1 != null) {
              break;
            }
            return;
          }
        }
        catch (Exception localException1)
        {
          boolean bool1 = false;
          continue;
          if ((!bool1) && (i != 0))
          {
            paramK.a("LSG", "NET");
            localObject2 = "network";
            continue;
          }
          if ((!bool1) && (i == 0))
          {
            paramK.a("LSG", "NONE");
            localObject2 = "gps";
            continue;
          }
          if (!bool1) {
            break label330;
          }
        }
        if (i == 0) {
          break label330;
        }
        paramK.a("LSG", "BOTH");
        localObject2 = "gps";
      }
      localObject2 = a(paramContext);
      paramK.a("LAT", Double.toString(d.getLatitude()));
      paramK.a("LON", Double.toString(d.getLongitude()));
      paramK.a("ALT", Double.toString(d.getAltitude()));
      paramK.a("GLA", Float.toString(d.getAccuracy()));
      paramK.a("GLD", Long.toString(d.getTime()));
      paramK.a("MOCK", Integer.toString(((ArrayList)localObject2).size()));
      paramK.a("MLS", Integer.toString(b(paramContext)));
      paramK.a("NMEA", Integer.toString(this.b));
      return;
    }
    paramK.a("LSEN", "FALSE");
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
