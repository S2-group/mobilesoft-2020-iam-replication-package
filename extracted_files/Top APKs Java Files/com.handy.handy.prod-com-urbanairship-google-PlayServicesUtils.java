package com.urbanairship.google;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import java.util.Iterator;
import java.util.List;

public class PlayServicesUtils
{
  public static final int a = -1;
  private static final String b = "com.google.market";
  private static final String c = "com.android.vending";
  private static final int d = 0;
  private static Boolean e;
  private static Boolean f;
  private static Boolean g;
  
  public PlayServicesUtils() {}
  
  public static void a(@NonNull Context paramContext)
  {
    if (!a()) {}
    for (;;)
    {
      return;
      try
      {
        int i = GooglePlayServicesUtilWrapper.a(paramContext);
        if (i != 0)
        {
          if (GooglePlayServicesUtilWrapper.a(i))
          {
            Logger.d("Launching Play Services Activity to resolve error.");
            try
            {
              paramContext.startActivity(new Intent(paramContext, PlayServicesErrorActivity.class));
              return;
            }
            catch (ActivityNotFoundException paramContext)
            {
              Logger.e(paramContext.getMessage());
              return;
            }
          }
          Logger.d("Error " + i + " is not user recoverable.");
        }
      }
      catch (IllegalStateException paramContext)
      {
        Logger.e("Google Play services developer error: " + paramContext.getMessage());
        return;
      }
    }
  }
  
  public static boolean a()
  {
    if (e == null) {}
    try
    {
      Class.forName("com.google.android.gms.common.GooglePlayServicesUtil");
      e = Boolean.valueOf(true);
      return e.booleanValue();
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;)
      {
        e = Boolean.valueOf(false);
      }
    }
  }
  
  public static int b(@NonNull Context paramContext)
  {
    if (a()) {
      return GooglePlayServicesUtilWrapper.a(paramContext);
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
    Iterator localIterator = UAirship.e().getInstalledPackages(0).iterator();
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
