package com.link.messages.sms.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import java.util.Iterator;
import java.util.List;

public class b
{
  public static boolean a(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((localPackageInfo.packageName.equalsIgnoreCase("com.appturbo.appturboCA2015")) || (localPackageInfo.packageName.equalsIgnoreCase("com.appturbo.appoftheday2015")) || (localPackageInfo.packageName.equalsIgnoreCase("com.appturbo.appofthenight"))) {
        return true;
      }
    }
    localObject = new Intent("android.intent.action.VIEW");
    ((Intent)localObject).setFlags(268435456);
    ((Intent)localObject).setData(Uri.parse("appturbo://check"));
    if (paramContext.getPackageManager().queryIntentActivities((Intent)localObject, 65536).size() > 0) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}
