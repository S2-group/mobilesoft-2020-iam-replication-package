package com.asanayoga.asanarebel.ui.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;

public class ContextUtil
{
  public ContextUtil() {}
  
  public static boolean isPackageInstalled(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
      if ((localApplicationInfo != null) && (localApplicationInfo.packageName != null) && (localApplicationInfo.packageName.equals(paramString))) {
        return true;
      }
    }
    return false;
  }
}
