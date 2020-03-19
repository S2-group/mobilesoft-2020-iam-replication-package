package com.urbanairship.google;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.support.annotation.x;
import com.urbanairship.k;
import com.urbanairship.t;
import java.util.Iterator;
import java.util.List;

public class c
{
  public static final int a = -1;
  private static final String b = "com.google.market";
  private static final String c = "com.android.vending";
  private static final int d = 0;
  private static Boolean e;
  private static Boolean f;
  private static Boolean g;
  
  public c() {}
  
  public static void a(@x Context paramContext)
  {
    if (!a()) {}
    for (;;)
    {
      return;
      try
      {
        int i = b.a(paramContext);
        if (i != 0)
        {
          if (b.a(i))
          {
            k.d("Launching Play Services Activity to resolve error.");
            try
            {
              paramContext.startActivity(new Intent(paramContext, PlayServicesErrorActivity.class));
              return;
            }
            catch (ActivityNotFoundException paramContext)
            {
              k.e(paramContext.getMessage());
              return;
            }
          }
          k.d("Error " + i + " is not user recoverable.");
        }
      }
      catch (IllegalStateException paramContext)
      {
        k.e("Google Play services developer error: " + paramContext.getMessage());
        return;
      }
    }
  }
  
  public static boolean a()
  {
    if (e == null)
    {
      if (Build.VERSION.SDK_INT >= 8) {
        break label28;
      }
      e = Boolean.valueOf(false);
    }
    for (;;)
    {
      return e.booleanValue();
      try
      {
        label28:
        Class.forName("com.google.android.gms.common.GooglePlayServicesUtil");
        e = Boolean.valueOf(true);
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        e = Boolean.valueOf(false);
      }
    }
  }
  
  public static int b(@x Context paramContext)
  {
    if (a()) {
      return b.a(paramContext);
    }
    return -1;
  }
  
  public static boolean b()
  {
    if (f == null)
    {
      if (a()) {
        break label26;
      }
      f = Boolean.valueOf(false);
    }
    for (;;)
    {
      return f.booleanValue();
      try
      {
        label26:
        Class.forName("com.google.android.gms.gcm.GoogleCloudMessaging");
        Class.forName("com.google.android.gms.gcm.GcmReceiver");
        f = Boolean.valueOf(true);
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        f = Boolean.valueOf(false);
      }
    }
  }
  
  @Deprecated
  public static boolean c()
  {
    return d();
  }
  
  public static boolean d()
  {
    if (g == null)
    {
      if (a()) {
        break label26;
      }
      g = Boolean.valueOf(false);
    }
    for (;;)
    {
      return g.booleanValue();
      try
      {
        label26:
        Class.forName("com.google.android.gms.location.LocationServices");
        g = Boolean.valueOf(true);
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        g = Boolean.valueOf(false);
      }
    }
  }
  
  public static boolean e()
  {
    Iterator localIterator = t.e().getInstalledPackages(0).iterator();
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
