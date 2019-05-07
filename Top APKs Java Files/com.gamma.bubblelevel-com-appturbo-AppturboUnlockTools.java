package com.appturbo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;

public class AppturboUnlockTools
{
  public AppturboUnlockTools() {}
  
  public static boolean isAppturboUnlockable(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    PackageInfo localPackageInfo;
    do
    {
      if (!paramContext.hasNext()) {
        return false;
      }
      localPackageInfo = (PackageInfo)paramContext.next();
    } while ((!localPackageInfo.packageName.equalsIgnoreCase("com.appturbo.appturboCA2015")) && (!localPackageInfo.packageName.equalsIgnoreCase("com.appturbo.appoftheday2015")));
    return true;
  }
}
