package com.oceanwing.core.netscene.config.http;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.List;

public class AppUtil
{
  private AppUtil() {}
  
  public static boolean isAppInstalled(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    if (paramContext != null)
    {
      int i = 0;
      while (i < paramContext.size())
      {
        if (paramString.equals(((PackageInfo)paramContext.get(i)).packageName)) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public static boolean isOldVersion(String paramString1, String paramString2)
  {
    paramString1 = paramString1.split("\\.");
    paramString2 = paramString2.split("\\.");
    try
    {
      if (Integer.parseInt(paramString1[0]) > Integer.parseInt(paramString2[0])) {
        return false;
      }
      if (Integer.parseInt(paramString1[0]) >= Integer.parseInt(paramString2[0]))
      {
        if (Integer.parseInt(paramString1[1]) > Integer.parseInt(paramString2[1])) {
          return false;
        }
        if (Integer.parseInt(paramString1[1]) >= Integer.parseInt(paramString2[1]))
        {
          int i = Integer.parseInt(paramString1[2]);
          int j = Integer.parseInt(paramString2[2]);
          if (i > j) {
            return false;
          }
        }
      }
    }
    catch (Exception paramString1)
    {
      return false;
    }
    return true;
  }
  
  public static void startAppForPackage(Context paramContext, String paramString)
  {
    paramContext.startActivity(paramContext.getPackageManager().getLaunchIntentForPackage(paramString));
  }
}
