package com.antivirus.core.scanners;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.avg.toolkit.g.a;
import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class q
  implements w
{
  private List a;
  private ListIterator b;
  private int c;
  private HashSet d;
  private int e = 0;
  
  public q(int paramInt, String paramString1, String paramString2, Context paramContext)
  {
    this.c = paramInt;
    paramContext = paramContext.getPackageManager();
    if (paramString2 == null) {
      this.a = paramContext.getInstalledPackages(0);
    }
    for (;;)
    {
      if (this.a != null) {
        this.b = this.a.listIterator();
      }
      paramString1 = paramString1.split(",");
      this.d = new HashSet(paramString1.length);
      int i = paramString1.length;
      paramInt = 0;
      while (paramInt < i)
      {
        paramString2 = paramString1[paramInt];
        this.d.add(paramString2);
        paramInt += 1;
      }
      try
      {
        paramString2 = paramContext.getPackageInfo(paramString2, 0);
        this.a = new LinkedList();
        this.a.add(paramString2);
      }
      catch (Exception paramString2)
      {
        a.a(paramString2);
      }
    }
  }
  
  public ae a()
  {
    return ae.a;
  }
  
  public v b()
  {
    Object localObject1 = null;
    Object localObject2 = localObject1;
    PackageInfo localPackageInfo;
    if (this.b != null)
    {
      localObject2 = localObject1;
      if (this.b.hasNext())
      {
        localPackageInfo = (PackageInfo)this.b.next();
        this.e += 1;
        if (this.d.contains(localPackageInfo.packageName)) {
          break label214;
        }
        localObject2 = new File(localPackageInfo.applicationInfo.sourceDir);
        if ((!((File)localObject2).exists()) || (!((File)localObject2).canRead())) {
          break label214;
        }
      }
    }
    try
    {
      if (localPackageInfo.applicationInfo.sourceDir.startsWith("/data")) {
        localObject2 = i.c;
      }
      for (;;)
      {
        localObject2 = new j(localPackageInfo.packageName, localPackageInfo.applicationInfo.sourceDir, k.a, this.c, (i)localObject2);
        try
        {
          ((j)localObject2).d(localPackageInfo.packageName);
          return localObject2;
        }
        catch (Exception localException2)
        {
          localObject1 = localObject2;
        }
        if (localPackageInfo.applicationInfo.sourceDir.startsWith("/system")) {
          localObject2 = i.d;
        } else if (localPackageInfo.applicationInfo.sourceDir.startsWith("/mnt")) {
          localObject2 = i.b;
        } else {
          localObject2 = i.e;
        }
      }
    }
    catch (Exception localException1)
    {
      for (;;)
      {
        Exception localException3 = localException1;
      }
    }
    a.a(localException2);
    label214:
    for (;;)
    {
      break;
    }
  }
  
  public boolean c()
  {
    return (this.b != null) && (this.b.hasNext());
  }
  
  public int d()
  {
    return this.e;
  }
}
