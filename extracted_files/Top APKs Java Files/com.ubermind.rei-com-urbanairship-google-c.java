package com.urbanairship.google;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.urbanairship.aq;
import java.util.Iterator;
import java.util.List;

public class c
{
  private static Boolean a;
  private static Boolean b;
  private static Boolean c;
  
  public static int a(Context paramContext)
  {
    if (a()) {
      return b.a(paramContext);
    }
    return -1;
  }
  
  public static boolean a()
  {
    if (a == null) {}
    try
    {
      Class.forName("com.google.android.gms.common.GooglePlayServicesUtil");
      a = Boolean.valueOf(true);
      return a.booleanValue();
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;)
      {
        a = Boolean.valueOf(false);
      }
    }
  }
  
  public static boolean b()
  {
    if (b == null)
    {
      if (a()) {
        break label26;
      }
      b = Boolean.valueOf(false);
    }
    for (;;)
    {
      return b.booleanValue();
      try
      {
        label26:
        Class.forName("com.google.android.gms.gcm.GoogleCloudMessaging");
        Class.forName("com.google.android.gms.gcm.GcmReceiver");
        b = Boolean.valueOf(true);
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        b = Boolean.valueOf(false);
      }
    }
  }
  
  public static boolean c()
  {
    if (c == null)
    {
      if (a()) {
        break label26;
      }
      c = Boolean.valueOf(false);
    }
    for (;;)
    {
      return c.booleanValue();
      try
      {
        label26:
        Class.forName("com.google.android.gms.location.LocationServices");
        c = Boolean.valueOf(true);
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        c = Boolean.valueOf(false);
      }
    }
  }
  
  public static boolean d()
  {
    Iterator localIterator = aq.d().getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if ((localPackageInfo.packageName.equals("com.android.vending")) || (localPackageInfo.packageName.equals("com.google.market"))) {
        return true;
      }
    }
    return false;
  }
}
