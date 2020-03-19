package com.apalon.am3.h;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.apalon.am3.c.a.c;
import java.util.Iterator;
import java.util.List;

public class m
{
  public static int a = 50;
  
  public static boolean a(Context paramContext, c paramC, e paramE)
  {
    long l;
    if (g.a()) {
      l = System.currentTimeMillis();
    } else {
      l = 0L;
    }
    boolean bool = false;
    if ((paramC != null) && (paramC.c() != 0) && (paramC.b() != null))
    {
      if (paramC.b().size() == 0) {
        return false;
      }
      if ((paramC.a()) && (paramE.d()) && (paramE.c())) {
        return true;
      }
      paramE = paramContext.getPackageManager();
      Object localObject = paramC.b();
      paramContext = null;
      if (((List)localObject).size() > a) {
        paramContext = paramE.getInstalledPackages(128);
      }
      localObject = ((List)localObject).iterator();
      int i = 0;
      int j;
      do
      {
        j = i;
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
        j = i;
        if (a((List)((Iterator)localObject).next(), paramContext, paramE)) {
          j = i + 1;
        }
        i = j;
      } while (j < paramC.c());
      if (g.a()) {
        g.b("Stingy check is done [%dms]", new Object[] { Long.valueOf(System.currentTimeMillis() - l) });
      }
      if (j < paramC.c()) {
        bool = true;
      }
      return bool;
    }
    return false;
  }
  
  private static boolean a(List<String> paramList, List<PackageInfo> paramList1, PackageManager paramPackageManager)
  {
    if (paramList != null)
    {
      if (paramList.isEmpty()) {
        return false;
      }
      if (paramList1 == null) {
        paramList = paramList.iterator();
      }
    }
    for (;;)
    {
      if (paramList.hasNext()) {
        paramList1 = (String)paramList.next();
      }
      try
      {
        paramPackageManager.getPackageInfo(paramList1, 128);
        return true;
      }
      catch (PackageManager.NameNotFoundException paramList1) {}
      return false;
      paramList1 = paramList1.iterator();
      while (paramList1.hasNext()) {
        if (paramList.contains(((PackageInfo)paramList1.next()).packageName)) {
          return true;
        }
      }
      return false;
      return false;
    }
  }
}
