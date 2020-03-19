package com.adxmi.android;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import java.util.List;

public class cm
{
  private static boolean ew = false;
  private static boolean ex = false;
  
  public static boolean I(Context paramContext)
  {
    return J(paramContext) != 0;
  }
  
  public static int J(Context paramContext)
  {
    int j = 1;
    int i;
    try
    {
      if (("unknown".equals(Build.BOARD)) && ("generic".equals(Build.DEVICE)) && ("generic".equals(Build.BRAND))) {
        return 1;
      }
      if (!be.I(Build.MODEL))
      {
        String str = Build.MODEL.trim().toLowerCase();
        i = j;
        if (!"sdk".equals(str))
        {
          i = j;
          if ("google_sdk".equals(str)) {}
        }
      }
      else
      {
        if (K(paramContext)) {
          return 2;
        }
        if (L(paramContext)) {
          return 3;
        }
        if (M(paramContext)) {
          return 4;
        }
        boolean bool = N(paramContext);
        if (bool) {
          return 6;
        }
      }
    }
    catch (Throwable paramContext)
    {
      i = 0;
    }
    return i;
  }
  
  private static boolean K(Context paramContext)
  {
    try
    {
      if (("unknown".equals(Build.BOARD)) && ("vbox86p".equals(Build.DEVICE)) && ("generic".equals(Build.BRAND)))
      {
        boolean bool = "test-keys".equals(Build.TAGS);
        if (bool) {
          return true;
        }
      }
    }
    catch (Throwable paramContext) {}
    return false;
  }
  
  private static boolean L(Context paramContext)
  {
    try
    {
      if (!be.I(Build.MODEL))
      {
        boolean bool = Build.MODEL.trim().toLowerCase().contains("tiantian");
        if (bool) {
          return true;
        }
      }
    }
    catch (Throwable paramContext) {}
    return false;
  }
  
  private static boolean M(Context paramContext)
  {
    try
    {
      if ((be.I(Build.MODEL)) && ("Droid4X".equals(Build.DEVICE)) && ("test-keys".equals(Build.TAGS)))
      {
        boolean bool = Build.MODEL.contains("Droid4X");
        if (bool) {
          return true;
        }
      }
    }
    catch (Throwable paramContext) {}
    return false;
  }
  
  private static boolean N(Context paramContext)
  {
    if (ew) {
      return ex;
    }
    for (;;)
    {
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledPackages(0);
        m = 0;
        i = 0;
        j = paramContext.size();
        if (m >= j) {
          continue;
        }
        k = i;
      }
      catch (Exception paramContext)
      {
        int m;
        int i;
        int k;
        PackageInfo localPackageInfo;
        String str;
        boolean bool;
        continue;
        int j = i;
        continue;
        continue;
        j = i;
        continue;
      }
      try
      {
        localPackageInfo = (PackageInfo)paramContext.get(m);
        k = i;
        str = localPackageInfo.packageName;
        k = i;
        if ((localPackageInfo.applicationInfo.flags & 0x1) != 1) {
          continue;
        }
        j = i;
        k = i;
        if (str.trim().toLowerCase().equals("com.blue".trim() + "stacks.appsettings")) {
          j = i + 1;
        }
        i = j;
        k = j;
        if (str.trim().toLowerCase().equals("com.blues".trim() + "tacks.settings")) {
          i = j + 1;
        }
        k = i;
        bool = str.trim().toLowerCase().equals("com.blues".trim() + "tacks.blue".trim() + "stackslocationprovider");
        if (!bool) {
          continue;
        }
        j = i + 1;
        i = j;
        if (j > 2)
        {
          ew = true;
          if (j >= 2) {
            ex = true;
          }
          return ex;
        }
      }
      catch (Throwable localThrowable)
      {
        i = k;
      }
      m += 1;
    }
  }
}
