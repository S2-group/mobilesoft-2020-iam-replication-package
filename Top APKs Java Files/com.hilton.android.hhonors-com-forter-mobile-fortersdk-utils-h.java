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
import java.util.Iterator;
import java.util.List;

public abstract class h
{
  @NonNull
  public static String a(@NonNull Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 19) {}
    try
    {
      int i = Settings.Secure.getInt(paramContext.getContentResolver(), "location_mode");
      switch (i)
      {
      default: 
        return "LOCATION_MODE_FAILED";
      case 3: 
        return "LOCATION_MODE_HIGH_ACCURACY";
      case 2: 
        return "LOCATION_MODE_BATTERY_SAVING";
      case 1: 
        return "LOCATION_MODE_SENSORS_ONLY";
      }
      return "LOCATION_MODE_OFF";
    }
    catch (Settings.SettingNotFoundException paramContext)
    {
      for (;;) {}
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
  }
  
  @NonNull
  public static String b(@NonNull Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 23) {
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
      label23:
      int j;
      label158:
      for (int i = 0; localIterator.hasNext(); i = j)
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        try
        {
          String[] arrayOfString = localPackageManager.getPackageInfo(localApplicationInfo.packageName, 4096).requestedPermissions;
          if (arrayOfString == null) {
            break label23;
          }
          int m = arrayOfString.length;
          int k = 0;
          for (;;)
          {
            j = i;
            if (k >= m) {
              break label158;
            }
            j = i;
            try
            {
              if (arrayOfString[k].equals("android.permission.ACCESS_MOCK_LOCATION"))
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
            catch (PackageManager.NameNotFoundException localNameNotFoundException1) {}
          }
          new StringBuilder("Got exception ").append(localNameNotFoundException2.getMessage());
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException2) {}
        j = i;
      }
      return i;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return -2;
  }
}
