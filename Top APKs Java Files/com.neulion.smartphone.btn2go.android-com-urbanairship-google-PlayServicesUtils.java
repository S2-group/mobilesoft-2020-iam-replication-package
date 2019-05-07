package com.urbanairship.google;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import java.util.Iterator;
import java.util.List;

public class PlayServicesUtils
{
  public static final int MISSING_PLAY_SERVICE_DEPENDENCY = -1;
  private static Boolean a;
  private static Boolean b;
  private static Boolean c;
  
  public PlayServicesUtils() {}
  
  public static void handleAnyPlayServicesError(Context paramContext)
  {
    if (!isGooglePlayServicesDependencyAvailable()) {}
    for (;;)
    {
      return;
      try
      {
        int i = GooglePlayServicesUtilWrapper.isGooglePlayServicesAvailable(paramContext);
        if (i != 0)
        {
          if (GooglePlayServicesUtilWrapper.isUserRecoverableError(i))
          {
            Logger.info("Launching Play Services Activity to resolve error.");
            try
            {
              paramContext.startActivity(new Intent(paramContext, PlayServicesErrorActivity.class));
              return;
            }
            catch (ActivityNotFoundException paramContext)
            {
              Logger.error(paramContext.getMessage());
              return;
            }
          }
          Logger.info("Error " + i + " is not user recoverable.");
        }
      }
      catch (IllegalStateException paramContext)
      {
        Logger.error("Google Play services developer error: " + paramContext.getMessage());
        return;
      }
    }
  }
  
  @Deprecated
  public static boolean isFusedLocationDepdendencyAvailable()
  {
    return isFusedLocationDependencyAvailable();
  }
  
  public static boolean isFusedLocationDependencyAvailable()
  {
    if (c == null)
    {
      if (isGooglePlayServicesDependencyAvailable()) {
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
  
  public static boolean isGoogleCloudMessagingDependencyAvailable()
  {
    if (b == null)
    {
      if (isGooglePlayServicesDependencyAvailable()) {
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
        Class.forName("com.google.android.gms.gcm.a");
        Class.forName("com.google.android.gms.gcm.GcmReceiver");
        b = Boolean.valueOf(true);
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        b = Boolean.valueOf(false);
      }
    }
  }
  
  public static int isGooglePlayServicesAvailable(Context paramContext)
  {
    if (isGooglePlayServicesDependencyAvailable()) {
      return GooglePlayServicesUtilWrapper.isGooglePlayServicesAvailable(paramContext);
    }
    return -1;
  }
  
  public static boolean isGooglePlayServicesDependencyAvailable()
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
  
  public static boolean isGooglePlayStoreAvailable()
  {
    Iterator localIterator = UAirship.getPackageManager().getInstalledPackages(0).iterator();
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
