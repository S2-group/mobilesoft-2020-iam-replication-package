package com.a.a.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class o
{
  private static final String[] a = { "Superuser.apk", "myhappy.apk" };
  private static final String[] b = { "/system/bin/", "/system/xbin/", "/sbin/", "/system/", "/system/bin/.ext/", "/system/usr/we-need-root/" };
  private static Set<String> c = new HashSet();
  
  static
  {
    c.add("com.noshufou.android.su");
    c.add("eu.chainfire.supersu");
    c.add("eu.chainfire.supersu.pro");
    c.add("com.koushikdutta.superuser");
    c.add("com.noshufou.android.su.elite");
    c.add("david.lahuta.superuser.free.pro");
    c.add("com.bitcubate.android.su.installer");
    c.add("com.bitcubate.superuser.pro");
    c.add("david.lahuta.superuser");
  }
  
  static int a(Context paramContext)
  {
    Boolean[] arrayOfBoolean = new Boolean[3];
    arrayOfBoolean[0] = Boolean.valueOf(b(paramContext));
    arrayOfBoolean[1] = Boolean.valueOf(a());
    arrayOfBoolean[2] = Boolean.valueOf(b());
    int m = arrayOfBoolean.length;
    int j = 0;
    int i = 0;
    if (j < m)
    {
      if (arrayOfBoolean[j].booleanValue()) {}
      for (int k = 1;; k = 0)
      {
        j += 1;
        i = (i << 1) + k;
        break;
      }
    }
    return i;
  }
  
  private static boolean a()
  {
    try
    {
      Iterator localIterator = Arrays.asList(a).iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        boolean bool = new File("/system/app/" + str).exists();
        if (bool) {
          return true;
        }
      }
    }
    catch (Exception localException) {}
    return false;
  }
  
  private static boolean b()
  {
    Iterator localIterator = Arrays.asList(b).iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (new File(str + "su").exists()) {
        return true;
      }
    }
    return false;
  }
  
  private static boolean b(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(128);
    if (paramContext != null)
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
        if (c.contains(localApplicationInfo.packageName)) {
          return true;
        }
      }
    }
    return false;
  }
}
