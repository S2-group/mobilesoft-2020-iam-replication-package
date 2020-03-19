package com.billingmaster.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import com.google.android.gms.common.GoogleApiAvailability;
import java.util.Iterator;
import java.util.List;

public final class FeatureUtils
{
  private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
  private static boolean sBillingChecked = false;
  private static boolean sIsBillingSupported = false;
  
  public FeatureUtils() {}
  
  public static boolean checkPlayServices(Context paramContext)
  {
    GoogleApiAvailability localGoogleApiAvailability = GoogleApiAvailability.getInstance();
    int i = localGoogleApiAvailability.isGooglePlayServicesAvailable(paramContext);
    if (i != 0)
    {
      if (localGoogleApiAvailability.isUserResolvableError(i)) {
        LogUtil.d(FeatureUtils.class, "Dialog, This device is not supported.");
      }
      for (;;)
      {
        return false;
        LogUtil.d(FeatureUtils.class, "This device is not supported.");
      }
    }
    return true;
  }
  
  public static boolean checkSdRemovable()
  {
    return Environment.isExternalStorageRemovable();
  }
  
  public static boolean isBillingSupported(Context paramContext)
  {
    if (!sBillingChecked)
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
      while (paramContext.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
        if ((localApplicationInfo.packageName != null) && (localApplicationInfo.packageName.equals("com.android.vending"))) {
          sIsBillingSupported = true;
        }
      }
      sBillingChecked = true;
    }
    return sIsBillingSupported;
  }
  
  public static boolean isExternalStorageAvailable()
  {
    if (!Environment.isExternalStorageRemovable()) {}
    for (;;)
    {
      return true;
      String str = Environment.getExternalStorageState();
      int j;
      int i;
      if ("mounted".equals(str))
      {
        j = 1;
        i = 1;
      }
      while ((i == 0) || (j == 0))
      {
        return false;
        if ("mounted_ro".equals(str))
        {
          i = 1;
          j = 0;
        }
        else
        {
          j = 0;
          i = 0;
        }
      }
    }
  }
  
  public static boolean isNetworkAvailable(Context paramContext)
  {
    paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
    if (paramContext != null)
    {
      paramContext = paramContext.getActiveNetworkInfo();
      if ((paramContext != null) && (paramContext.isConnected())) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isSDMounted()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
}
