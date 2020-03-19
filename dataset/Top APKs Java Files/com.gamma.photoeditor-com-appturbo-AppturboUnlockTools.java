package com.appturbo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import java.util.Iterator;
import java.util.List;

public final class AppturboUnlockTools
{
  public AppturboUnlockTools() {}
  
  public static boolean isAppturboUnlockable(@NonNull Context paramContext)
  {
    boolean bool2 = true;
    boolean bool1;
    try
    {
      Object localObject = paramContext.getPackageManager().getInstalledPackages(0).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        bool1 = bool2;
        if (localPackageInfo.packageName.equalsIgnoreCase("com.appturbo.appturboCA2015")) {
          return bool1;
        }
        bool1 = bool2;
        if (localPackageInfo.packageName.equalsIgnoreCase("com.appturbo.appoftheday2015")) {
          return bool1;
        }
        bool1 = bool2;
        if (localPackageInfo.packageName.equalsIgnoreCase("com.appturbo.appofthenight")) {
          return bool1;
        }
        if (localPackageInfo.packageName.equalsIgnoreCase("com.appturbo.gameoftheday")) {
          return true;
        }
      }
      localObject = new Intent("android.intent.action.VIEW");
      ((Intent)localObject).setFlags(268435456);
      ((Intent)localObject).setData(Uri.parse("appturbo://check"));
      int i = paramContext.getPackageManager().queryIntentActivities((Intent)localObject, 65536).size();
      bool1 = bool2;
      if (i <= 0) {
        return false;
      }
    }
    catch (Exception paramContext)
    {
      bool1 = false;
    }
    return bool1;
  }
}
