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
    if (g.a()) {}
    for (long l = System.currentTimeMillis();; l = 0L)
    {
      if ((paramC == null) || (paramC.c() == 0) || (paramC.b() == null) || (paramC.b().size() == 0)) {
        return false;
      }
      if ((paramC.a()) && (paramE.d()) && (paramE.c())) {
        return true;
      }
      paramE = paramContext.getPackageManager();
      Object localObject = paramC.b();
      if (((List)localObject).size() > a) {}
      for (paramContext = paramE.getInstalledPackages(128);; paramContext = null)
      {
        localObject = ((List)localObject).iterator();
        int i = 0;
        label109:
        if (((Iterator)localObject).hasNext()) {
          if (a((List)((Iterator)localObject).next(), paramContext, paramE))
          {
            i += 1;
            label141:
            if (i < paramC.c()) {}
          }
        }
        for (;;)
        {
          if (g.a()) {
            g.b("Stingy check is done [%dms]", new Object[] { Long.valueOf(System.currentTimeMillis() - l) });
          }
          if (i >= paramC.c()) {
            break;
          }
          return true;
          break label109;
          break label141;
        }
      }
    }
  }
  
  private static boolean a(List<String> paramList, List<PackageInfo> paramList1, PackageManager paramPackageManager)
  {
    if ((paramList == null) || (paramList.isEmpty())) {
      return false;
    }
    if (paramList1 == null) {
      paramList = paramList.iterator();
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
    }
  }
}
