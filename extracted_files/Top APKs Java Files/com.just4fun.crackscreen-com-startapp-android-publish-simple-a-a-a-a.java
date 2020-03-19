package com.startapp.android.publish.simple.a.a.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class a
{
  public static List<String> a(Context paramContext)
  {
    Object localObject = b(paramContext);
    paramContext = new ArrayList();
    localObject = a((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      paramContext.add(((PackageInfo)((Iterator)localObject).next()).packageName);
    }
    return paramContext;
  }
  
  private static List<PackageInfo> a(List<PackageInfo> paramList)
  {
    if (paramList.size() <= 100) {
      return paramList;
    }
    if (Build.VERSION.SDK_INT >= 9) {
      Collections.sort(paramList, new Comparator()
      {
        public final int a(PackageInfo paramAnonymousPackageInfo1, PackageInfo paramAnonymousPackageInfo2)
        {
          long l1 = paramAnonymousPackageInfo1.firstInstallTime;
          long l2 = paramAnonymousPackageInfo2.firstInstallTime;
          if (l1 > l2) {
            return -1;
          }
          if (l1 == l2) {
            return 0;
          }
          return 1;
        }
      });
    }
    return paramList.subList(0, 100);
  }
  
  private static boolean a(PackageInfo paramPackageInfo)
  {
    return ((paramPackageInfo.applicationInfo.flags & 0x1) != 0) || ((paramPackageInfo.applicationInfo.flags & 0x80) != 0);
  }
  
  private static List<PackageInfo> b(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager();
    paramContext = new ArrayList();
    localObject = ((PackageManager)localObject).getInstalledPackages(0).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if (!a(localPackageInfo)) {
        paramContext.add(localPackageInfo);
      }
    }
    return paramContext;
  }
}
