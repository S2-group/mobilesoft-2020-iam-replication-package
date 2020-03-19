package com.devpotato.outstorelifebuoy.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import java.util.Iterator;
import java.util.List;

public class PlatformHelper
{
  public static boolean a(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    boolean bool2 = false;
    if (paramContext == null) {
      return false;
    }
    boolean bool1 = bool2;
    if (paramContext.isAvailable())
    {
      bool1 = bool2;
      if (paramContext.isConnected()) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean b(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 26) {
      return paramContext.getPackageManager().canRequestPackageInstalls();
    }
    boolean bool = false;
    try
    {
      int i = Settings.Secure.getInt(paramContext.getContentResolver(), "install_non_market_apps");
      if (i == 1) {
        bool = true;
      }
      return bool;
    }
    catch (Settings.SettingNotFoundException paramContext) {}
    return false;
  }
}
