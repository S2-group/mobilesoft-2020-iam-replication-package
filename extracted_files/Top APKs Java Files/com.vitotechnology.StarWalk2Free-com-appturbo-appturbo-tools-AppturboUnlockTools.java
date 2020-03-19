package com.appturbo.appturbo.tools;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import java.util.Iterator;
import java.util.List;

public class AppturboUnlockTools
{
  public AppturboUnlockTools() {}
  
  public static boolean isAppturboUnlockable(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    PackageInfo localPackageInfo;
    do
    {
      if (!((Iterator)localObject).hasNext())
      {
        localObject = new Intent("android.intent.action.VIEW");
        ((Intent)localObject).setFlags(268435456);
        ((Intent)localObject).setData(Uri.parse("appturbo://check"));
        if (paramContext.getPackageManager().queryIntentActivities((Intent)localObject, 65536).size() <= 0) {
          break;
        }
      }
      do
      {
        return true;
        localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      } while ((localPackageInfo.packageName.equalsIgnoreCase("com.appturbo.appturboCA2015")) || (localPackageInfo.packageName.equalsIgnoreCase("com.appturbo.appoftheday2015")));
    } while (!localPackageInfo.packageName.equalsIgnoreCase("com.appturbo.appofthenight"));
    return true;
    return false;
  }
}
