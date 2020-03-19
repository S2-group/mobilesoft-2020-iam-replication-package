package com.b.a.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

final class f
{
  private static final String[] a = { "Superuser.apk", "myhappy.apk" };
  private static final String[] b = { "/system/bin/", "/system/xbin/", "/sbin/", "/system/", "/system/bin/.ext/", "/system/usr/we-need-root/" };
  private static Set<String> c;
  
  static
  {
    HashSet localHashSet = new HashSet();
    c = localHashSet;
    localHashSet.add("com.noshufou.android.su");
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
    paramContext = paramContext.getPackageManager().getInstalledApplications(128);
    boolean bool1;
    boolean bool2;
    label134:
    int j;
    int i;
    if (paramContext != null)
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        Object localObject = (ApplicationInfo)paramContext.next();
        if (c.contains(((ApplicationInfo)localObject).packageName))
        {
          bool1 = true;
          boolean bool3 = a();
          paramContext = Arrays.asList(b).iterator();
          while (paramContext.hasNext())
          {
            localObject = (String)paramContext.next();
            if (new File((String)localObject + "su").exists())
            {
              bool2 = true;
              j = 0;
              i = 0;
              label138:
              if (j >= 3) {
                return i;
              }
              if (!new Boolean[] { Boolean.valueOf(bool1), Boolean.valueOf(bool3), Boolean.valueOf(bool2) }[j].booleanValue()) {
                break label206;
              }
            }
          }
        }
      }
    }
    label206:
    for (int k = 1;; k = 0)
    {
      j += 1;
      i = (i << 1) + k;
      break label138;
      bool1 = false;
      break;
      bool2 = false;
      break label134;
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
}
