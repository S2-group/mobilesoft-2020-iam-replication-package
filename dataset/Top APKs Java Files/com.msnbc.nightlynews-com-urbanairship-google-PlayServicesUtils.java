package com.urbanairship.google;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import java.util.Iterator;
import java.util.List;

public class PlayServicesUtils
{
  private static final int CONNECTION_SUCCESS = 0;
  private static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
  private static final String GOOGLE_PLAY_STORE_PACKAGE_OLD = "com.google.market";
  public static int MISSING_PLAY_SERVICE_DEPENDENCY = -1;
  private static Boolean isGooglePlayServicesDependencyAvailable;
  
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
  
  public static int isGooglePlayServicesAvailable(Context paramContext)
  {
    if (isGooglePlayServicesDependencyAvailable()) {
      return GooglePlayServicesUtilWrapper.isGooglePlayServicesAvailable(paramContext);
    }
    return MISSING_PLAY_SERVICE_DEPENDENCY;
  }
  
  public static boolean isGooglePlayServicesDependencyAvailable()
  {
    if (isGooglePlayServicesDependencyAvailable != null) {
      return isGooglePlayServicesDependencyAvailable.booleanValue();
    }
    if (Build.VERSION.SDK_INT < 8) {
      isGooglePlayServicesDependencyAvailable = Boolean.valueOf(false);
    }
    for (;;)
    {
      return isGooglePlayServicesDependencyAvailable.booleanValue();
      try
      {
        Class.forName("com.google.android.gms.common.GooglePlayServicesUtil");
        isGooglePlayServicesDependencyAvailable = Boolean.valueOf(true);
      }
      catch (ClassNotFoundException localClassNotFoundException)
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
