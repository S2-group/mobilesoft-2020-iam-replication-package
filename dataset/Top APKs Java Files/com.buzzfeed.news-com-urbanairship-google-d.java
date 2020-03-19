package com.urbanairship.google;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import com.urbanairship.ar;
import com.urbanairship.v;
import java.util.Iterator;
import java.util.List;

public class d
{
  private static Boolean a;
  private static Boolean b;
  private static Boolean c;
  
  public static void a(@NonNull Context paramContext)
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
            v.d("Launching Play Services Activity to resolve error.");
            try
            {
              paramContext.startActivity(new Intent(paramContext, PlayServicesErrorActivity.class));
              return;
            }
            catch (ActivityNotFoundException paramContext)
            {
              v.e(paramContext.getMessage());
              return;
            }
          }
          v.d("Error " + i + " is not user recoverable.");
        }
      }
      catch (IllegalStateException paramContext)
      {
        v.e("Google Play services developer error: " + paramContext.getMessage());
        return;
      }
    }
  }
  
  public static boolean a()
  {
    if (a == null) {}
    try
    {
      Class.forName("com.google.android.gms.common.e");
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
  
  public static int b(@NonNull Context paramContext)
  {
    if (a()) {
      return b.a(paramContext);
    }
    return -1;
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
        Class.forName("com.google.android.gms.b.b");
        Class.forName("com.google.android.gms.b.a");
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
        Class.forName("com.google.android.gms.location.i");
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
    Iterator localIterator = ar.d().getInstalledPackages(0).iterator();
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
