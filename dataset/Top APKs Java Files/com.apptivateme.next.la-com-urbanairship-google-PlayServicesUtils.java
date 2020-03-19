package com.urbanairship.google;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import com.urbanairship.UAirship;
import java.util.Iterator;
import java.util.List;

public class PlayServicesUtils
{
  private static Boolean isFusedLocationDependencyAvailable;
  private static Boolean isGoogleCloudMessagingDependencyAvailable;
  private static Boolean isGooglePlayServicesDependencyAvailable;
  
  public static boolean isFusedLocationDependencyAvailable()
  {
    if (isFusedLocationDependencyAvailable == null)
    {
      if (isGooglePlayServicesDependencyAvailable()) {
        break label26;
      }
      isFusedLocationDependencyAvailable = Boolean.valueOf(false);
    }
    for (;;)
    {
      return isFusedLocationDependencyAvailable.booleanValue();
      try
      {
        label26:
        Class.forName("com.google.android.gms.location.LocationServices");
        isFusedLocationDependencyAvailable = Boolean.valueOf(true);
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        isFusedLocationDependencyAvailable = Boolean.valueOf(false);
      }
    }
  }
  
  public static boolean isGoogleCloudMessagingDependencyAvailable()
  {
    if (isGoogleCloudMessagingDependencyAvailable == null)
    {
      if (isGooglePlayServicesDependencyAvailable()) {
        break label26;
      }
      isGoogleCloudMessagingDependencyAvailable = Boolean.valueOf(false);
    }
    for (;;)
    {
      return isGoogleCloudMessagingDependencyAvailable.booleanValue();
      try
      {
        label26:
        Class.forName("com.google.android.gms.gcm.GoogleCloudMessaging");
        Class.forName("com.google.android.gms.gcm.GcmReceiver");
        isGoogleCloudMessagingDependencyAvailable = Boolean.valueOf(true);
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        isGoogleCloudMessagingDependencyAvailable = Boolean.valueOf(false);
      }
    }
  }
  
  public static int isGooglePlayServicesAvailable(@NonNull Context paramContext)
  {
    if (isGooglePlayServicesDependencyAvailable()) {
      return GooglePlayServicesUtilWrapper.isGooglePlayServicesAvailable(paramContext);
    }
    return -1;
  }
  
  public static boolean isGooglePlayServicesDependencyAvailable()
  {
    if (isGooglePlayServicesDependencyAvailable == null) {}
    try
    {
      Class.forName("com.google.android.gms.common.GooglePlayServicesUtil");
      isGooglePlayServicesDependencyAvailable = Boolean.valueOf(true);
      return isGooglePlayServicesDependencyAvailable.booleanValue();
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;)
      {
        isGooglePlayServicesDependencyAvailable = Boolean.valueOf(false);
      }
    }
  }
  
  public static boolean isGooglePlayStoreAvailable()
  {
    boolean bool2 = false;
    Iterator localIterator = UAirship.getPackageManager().getInstalledPackages(0).iterator();
    PackageInfo localPackageInfo;
    do
    {
      bool1 = bool2;
      if (!localIterator.hasNext()) {
        break;
      }
      localPackageInfo = (PackageInfo)localIterator.next();
    } while ((!localPackageInfo.packageName.equals("com.android.vending")) && (!localPackageInfo.packageName.equals("com.google.market")));
    boolean bool1 = true;
    return bool1;
  }
}
