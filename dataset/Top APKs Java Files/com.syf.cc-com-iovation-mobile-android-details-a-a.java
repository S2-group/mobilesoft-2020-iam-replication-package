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
  private static boolean b = false;
  private static Location f;
  protected int a = 0;
  private Context c;
  private long d = 0L;
  private com.iovation.mobile.android.details.a.a.a e = new com.iovation.mobile.android.details.a.a.a(this);
  
  public a() {}
  
  public void a(int paramInt)
  {
    this.a = paramInt;
  }
  
  public void a(Context paramContext)
  {
    ((LocationManager)paramContext.getSystemService("location")).removeUpdates(this.e);
  }
  
  public void a(Context paramContext, j paramJ)
  {
    int i = 0;
    if (k.a("android.permission.ACCESS_FINE_LOCATION", paramContext))
    {
      LocationManager localLocationManager = (LocationManager)paramContext.getSystemService("location");
      do
      {
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
              String str1;
              Object localObject;
              continue;
              String str2 = "gps";
              continue;
            }
            paramJ.a("LSEN", "TRUE");
            if ((bool1) && (i == 0))
            {
              paramJ.a("LSG", "GPS");
              str1 = "gps";
              if (str1 != null) {
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
              paramJ.a("LSG", "NET");
              localObject = "network";
              continue;
            }
            if ((!bool1) && (i == 0))
            {
              paramJ.a("LSG", "NONE");
              localObject = "gps";
              continue;
            }
            if (!bool1) {
              break label334;
            }
          }
          if (i == 0) {
            break label334;
          }
          paramJ.a("LSG", "BOTH");
          localObject = "gps";
        }
        if (f == null) {
          f = localLocationManager.getLastKnownLocation((String)localObject);
        }
        if (f != null) {
          break;
        }
        f = localLocationManager.getLastKnownLocation("network");
      } while (f == null);
      localObject = b(paramContext);
      paramJ.a("LAT", Double.toString(f.getLatitude()));
      paramJ.a("LON", Double.toString(f.getLongitude()));
      paramJ.a("ALT", Double.toString(f.getAltitude()));
      paramJ.a("GLA", Float.toString(f.getAccuracy()));
      paramJ.a("GLD", Long.toString(f.getTime()));
      paramJ.a("MOCK", Integer.toString(((ArrayList)localObject).size()));
      paramJ.a("MLS", Integer.toString(c(paramContext)));
      paramJ.a("NMEA", Integer.toString(this.a));
      return;
    }
    paramJ.a("LSEN", "FALSE");
  }
  
  public void a(Location paramLocation)
  {
    f = paramLocation;
    if (f.getAccuracy() <= 100.0F) {
      a(this.c);
    }
  }
  
  public ArrayList b(Context paramContext)
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
      catch (Exception localException) {}catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    }
  }
  
  public int c(Context paramContext)
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
}
