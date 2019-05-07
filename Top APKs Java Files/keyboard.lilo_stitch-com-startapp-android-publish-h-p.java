package com.startapp.android.publish.h;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import com.startapp.android.publish.b;
import com.startapp.android.publish.f.a.b.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class p
{
  private static boolean a;
  
  public static String a(Context paramContext)
  {
    j.a("SimpleToken", 3, "createSimpleToken entered");
    paramContext = new a().a(b(paramContext));
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("simpleToken : [");
    localStringBuilder.append(paramContext);
    localStringBuilder.append("]");
    j.a("SimpleToken", 3, localStringBuilder.toString());
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
        public int a(PackageInfo paramAnonymousPackageInfo1, PackageInfo paramAnonymousPackageInfo2)
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
    int i = paramPackageInfo.applicationInfo.flags;
    boolean bool = true;
    if ((i & 0x1) == 0)
    {
      if ((paramPackageInfo.applicationInfo.flags & 0x80) != 0) {
        return true;
      }
      bool = false;
    }
    return bool;
  }
  
  public static List<String> b(Context paramContext)
  {
    j.a("SimpleToken", 3, "getPackageList entered");
    Object localObject = c(paramContext);
    paramContext = new ArrayList();
    localObject = a((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      paramContext.add(((PackageInfo)((Iterator)localObject).next()).packageName);
    }
    if (a) {
      paramContext.add(0, b.c);
    }
    return paramContext;
  }
  
  private static List<PackageInfo> c(Context paramContext)
  {
    j.a("SimpleToken", 3, "getPackages entered");
    Object localObject = paramContext.getPackageManager();
    paramContext = new ArrayList();
    new ArrayList();
    try
    {
      localObject = ((PackageManager)localObject).getInstalledPackages(8192);
      a = false;
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if (!a(localPackageInfo)) {
          paramContext.add(localPackageInfo);
        } else if (localPackageInfo.packageName.equals(b.c)) {
          a = true;
        }
      }
      return paramContext;
    }
    catch (RuntimeException localRuntimeException)
    {
      j.a("SimpleToken", 6, "Could not complete getInstalledPackages", localRuntimeException);
    }
    return paramContext;
  }
}
