package com.startapp.android.publish.j;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.startapp.android.publish.e;
import com.startapp.android.publish.h.a.b.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class t
{
  private static boolean a = false;
  
  public static String a(Context paramContext)
  {
    n.a("SimpleToken", 3, "createSimpleToken entered");
    paramContext = new a().a(b(paramContext));
    n.a("SimpleToken", 3, "simpleToken : [" + paramContext + "]");
    return paramContext;
  }
  
  private static List<PackageInfo> a(List<PackageInfo> paramList)
  {
    if (paramList.size() <= 100) {
      return paramList;
    }
    b.a(paramList);
    return paramList.subList(0, 100);
  }
  
  private static boolean a(PackageInfo paramPackageInfo)
  {
    return ((paramPackageInfo.applicationInfo.flags & 0x1) != 0) || ((paramPackageInfo.applicationInfo.flags & 0x80) != 0);
  }
  
  public static List<String> b(Context paramContext)
  {
    n.a("SimpleToken", 3, "getPackageList entered");
    Object localObject = c(paramContext);
    paramContext = new ArrayList();
    localObject = a((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      paramContext.add(((PackageInfo)((Iterator)localObject).next()).packageName);
    }
    if (a) {
      paramContext.add(0, e.i);
    }
    return paramContext;
  }
  
  private static List<PackageInfo> c(Context paramContext)
  {
    n.a("SimpleToken", 3, "getPackages entered");
    Object localObject = paramContext.getPackageManager();
    paramContext = new ArrayList();
    new ArrayList();
    for (;;)
    {
      try
      {
        localObject = ((PackageManager)localObject).getInstalledPackages(8192);
        a = false;
        localObject = ((List)localObject).iterator();
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if (!a(localPackageInfo))
        {
          paramContext.add(localPackageInfo);
          continue;
        }
        if (!localPackageInfo.packageName.equals(e.i)) {
          continue;
        }
      }
      catch (RuntimeException localRuntimeException)
      {
        n.a("SimpleToken", 6, "Could not complete getInstalledPackages", localRuntimeException);
        return paramContext;
      }
      a = true;
    }
    return paramContext;
  }
}
