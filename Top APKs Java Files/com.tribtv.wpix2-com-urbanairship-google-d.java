package com.urbanairship.google;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import com.urbanairship.ai;
import com.urbanairship.m;
import java.util.Iterator;
import java.util.List;

public class d
{
  public static int a = -1;
  private static Boolean b;
  private static Boolean c;
  private static Boolean d;
  
  public static void a(Context paramContext)
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
            m.d("Launching Play Services Activity to resolve error.");
            try
            {
              paramContext.startActivity(new Intent(paramContext, PlayServicesErrorActivity.class));
              return;
            }
            catch (ActivityNotFoundException paramContext)
            {
              m.e(paramContext.getMessage());
              return;
            }
          }
          m.d("Error " + i + " is not user recoverable.");
        }
      }
      catch (IllegalStateException paramContext)
      {
        m.e("Google Play services developer error: " + paramContext.getMessage());
        return;
      }
    }
  }
  
  public static boolean a()
  {
    if (b == null)
    {
      if (Build.VERSION.SDK_INT >= 8) {
        break label28;
      }
      b = Boolean.valueOf(false);
    }
    for (;;)
    {
      return b.booleanValue();
      try
      {
        label28:
        Class.forName("com.google.android.gms.common.GooglePlayServicesUtil");
        b = Boolean.valueOf(true);
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        b = Boolean.valueOf(false);
      }
    }
  }
  
  public static int b(Context paramContext)
  {
    if (a()) {
      return b.a(paramContext);
    }
    return a;
  }
  
  public static boolean b()
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
        Class.forName("com.google.android.gms.gcm.GoogleCloudMessaging");
        c = Boolean.valueOf(true);
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        c = Boolean.valueOf(false);
      }
    }
  }
  
  public static boolean c()
  {
    if (d == null)
    {
      if (a()) {
        break label26;
      }
      d = Boolean.valueOf(false);
    }
    for (;;)
    {
      return d.booleanValue();
      try
      {
        label26:
        Class.forName("com.google.android.gms.location.LocationServices");
        d = Boolean.valueOf(true);
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        d = Boolean.valueOf(false);
      }
    }
  }
  
  public static boolean d()
  {
    Iterator localIterator = ai.d().getInstalledPackages(0).iterator();
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
