package com.smsrobot.lib.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;

public class AppsUtil
{
  public AppsUtil() {}
  
  public static boolean isAppInstalled(Context paramContext, String paramString)
  {
    paramContext = paramContext.getApplicationContext().getPackageManager().getInstalledPackages(8192).iterator();
    do
    {
      if (!paramContext.hasNext()) {
        return false;
      }
    } while (!((PackageInfo)paramContext.next()).packageName.equals(paramString));
    return true;
  }
}
