package com.forter.mobile.fortersdk.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import java.util.Iterator;
import java.util.List;

public abstract class h
{
  private static final String a = "LOCATION_MODE_FAILED";
  
  public h() {}
  
  @NonNull
  public static String a(@NonNull Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 19) {}
    try
    {
      switch (Settings.Secure.getInt(paramContext.getContentResolver(), "location_mode"))
      {
      case 0: 
        return "LOCATION_MODE_OFF";
      }
    }
    catch (Settings.SettingNotFoundException paramContext)
    {
      for (;;) {}
      return "LOCATION_MODE_FAILED";
    }
    return "LOCATION_MODE_FAILED";
    try
    {
      if (TextUtils.isEmpty(Settings.Secure.getString(paramContext.getContentResolver(), "location_providers_allowed"))) {
        return "LOCATION_MODE_OFF";
      }
      return "LOCATION_MODE_ON";
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return "LOCATION_MODE_FAILED";
    return "LOCATION_MODE_HIGH_ACCURACY";
    return "LOCATION_MODE_BATTERY_SAVING";
    return "LOCATION_MODE_SENSORS_ONLY";
  }
  
  @NonNull
  public static String b(@NonNull Context paramContext)
  {
    if (k.j() >= 23) {
      return "N/A";
    }
    try
    {
      if (!Settings.Secure.getString(paramContext.getContentResolver(), "mock_location").equals("0")) {
        return "true";
      }
      return "false";
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return "N/A";
  }
  
  public static int c(@NonNull Context paramContext)
  {
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      Iterator localIterator = localPackageManager.getInstalledApplications(128).iterator();
      for (int i = 0;; i = j) {
        for (;;)
        {
          if (!localIterator.hasNext()) {
            break label187;
          }
          ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
          try
          {
            localObject = localPackageManager.getPackageInfo(localApplicationInfo.packageName, 4096).requestedPermissions;
            if (localObject != null)
            {
              int m = localObject.length;
              int k = 0;
              for (;;)
              {
                j = i;
                if (k < m)
                {
                  j = i;
                  try
                  {
                    if (localObject[k].equals("android.permission.ACCESS_MOCK_LOCATION"))
                    {
                      boolean bool = localApplicationInfo.packageName.equals(paramContext.getPackageName());
                      j = i;
                      if (!bool) {
                        j = i + 1;
                      }
                    }
                    k += 1;
                    i = j;
                  }
                  catch (PackageManager.NameNotFoundException localNameNotFoundException1)
                  {
                    break label143;
                  }
                }
              }
            }
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException2)
          {
            for (;;)
            {
              Object localObject = new StringBuilder();
              ((StringBuilder)localObject).append("Got exception ");
              ((StringBuilder)localObject).append(localNameNotFoundException2.getMessage());
              Log.e("tag", ((StringBuilder)localObject).toString());
              int j = i;
            }
          }
        }
      }
      label143:
      label187:
      return i;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return -2;
  }
}
