package com.insidesecure.drmagent.v2.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;

public final class a
{
  private static final String[] a = { "com.noshufou.android.su", "superuser", "supersu", "hideroot", "hiderooting", "com.amphoras.hidemyroot", "otarootkeeper", "rootbackup", "com.zachspong.temprootremovejb", "com.ramdroid.appquarantine" };
  private static final String[] b = { "superuser", "androidroot", "root.apk", "com.noshufou.android.su", "hidemyroot" };
  
  public static boolean a(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    if (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      int i = 0;
      for (;;)
      {
        String[] arrayOfString = a;
        if (i >= 10) {
          break;
        }
        if (localPackageInfo.packageName.toLowerCase().contains(a[i]))
        {
          c.a("DRMAgentRootDetection", "Root-check failed. " + a[i]);
          return false;
        }
        i += 1;
      }
    }
    return true;
  }
  
  public static boolean b(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    if (paramContext.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
      int i = 0;
      for (;;)
      {
        String[] arrayOfString = b;
        if (i >= 5) {
          break;
        }
        if (localApplicationInfo.publicSourceDir.toLowerCase().contains(b[i]))
        {
          paramContext = "Root-check failed. " + "Device has unsafe APK " + localApplicationInfo.publicSourceDir;
          c.a("DRMAgentRootDetection", paramContext + b[i]);
          return false;
        }
        i += 1;
      }
    }
    return true;
  }
}
