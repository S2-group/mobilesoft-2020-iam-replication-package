package com.iovation.mobile.android.details.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import com.iovation.mobile.android.details.c;
import com.iovation.mobile.android.details.j;
import com.iovation.mobile.android.details.k;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a
  implements c
{
  public static Location d;
  private static boolean e = false;
  public Context a;
  protected int b = 0;
  public com.iovation.mobile.android.details.a.a.a c = new com.iovation.mobile.android.details.a.a.a(this);
  private long f = 0L;
  
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
          int i = 0;
          while (i < arrayOfString.length)
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
  
  public final void a(Context paramContext, j paramJ)
  {
    LocationManager localLocationManager;
    String str;
    int i;
    if (k.a("android.permission.ACCESS_FINE_LOCATION", paramContext))
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
    paramJ.a("LSEN", "TRUE");
    if ((bool1) && (i == 0))
    {
      paramJ.a("LSG", "GPS");
      localObject = "gps";
    }
    else if ((!bool1) && (i != 0))
    {
      paramJ.a("LSG", "NET");
      localObject = "network";
    }
    else if ((!bool1) && (i == 0))
    {
      paramJ.a("LSG", "NONE");
      localObject = str;
    }
    else
    {
      localObject = str;
      if (bool1)
      {
        localObject = str;
        if (i != 0)
        {
          paramJ.a("LSG", "BOTH");
          localObject = "gps";
        }
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
    paramJ.a("LAT", Double.toString(d.getLatitude()));
    paramJ.a("LON", Double.toString(d.getLongitude()));
    paramJ.a("ALT", Double.toString(d.getAltitude()));
    paramJ.a("GLA", Float.toString(d.getAccuracy()));
    paramJ.a("GLD", Long.toString(d.getTime()));
    paramJ.a("MOCK", Integer.toString(((ArrayList)localObject).size()));
    paramJ.a("MLS", Integer.toString(b(paramContext)));
    paramJ.a("NMEA", Integer.toString(this.b));
    return;
    paramJ.a("LSEN", "FALSE");
  }
  
  public final void b()
  {
    this.b = 1;
  }
}
