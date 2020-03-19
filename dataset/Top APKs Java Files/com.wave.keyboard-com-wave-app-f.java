package com.wave.app;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.wave.data.AppAttrib;
import com.wave.data.theme.ThemeResourceDict;
import com.wave.keyboard.b.b;
import com.wave.o.s;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class f
  extends e
{
  private static f c;
  private final Object b = new Object();
  private List<com.wave.keyboard.c.a> d = new ArrayList();
  private List<com.wave.keyboard.c.a> e = new ArrayList();
  
  public f() {}
  
  public static f a()
  {
    if (c == null) {
      c = new f();
    }
    return c;
  }
  
  private void a(List<com.wave.keyboard.c.a> paramList, com.wave.keyboard.c.a paramA)
  {
    Log.d("ThemesRepo", "addInstalledTheme " + paramA.packageName);
    paramList.add(paramA);
  }
  
  private void b(List<com.wave.keyboard.c.a> paramList)
  {
    paramList.add(c());
    paramList.add(d());
  }
  
  public List<AppAttrib> a(List<AppAttrib> paramList)
  {
    Object localObject = b();
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      com.wave.keyboard.c.a localA = (com.wave.keyboard.c.a)((Iterator)localObject).next();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        AppAttrib localAppAttrib = (AppAttrib)localIterator.next();
        if (localA.shortName.equals(localAppAttrib.shortname)) {
          localArrayList.add(localAppAttrib);
        }
      }
    }
    return localArrayList;
  }
  
  public List<com.wave.keyboard.c.a> a(boolean paramBoolean)
  {
    this.e = new ArrayList();
    if (!paramBoolean) {
      return this.d;
    }
    Iterator localIterator = this.d.iterator();
    while (localIterator.hasNext())
    {
      com.wave.keyboard.c.a localA = (com.wave.keyboard.c.a)localIterator.next();
      if (!localA.e()) {
        this.e.add(localA);
      }
    }
    return this.e;
  }
  
  public void a(String paramString)
  {
    Log.d("ThemesRepo", "fetchInstalledApps from " + paramString);
    long l1 = System.currentTimeMillis();
    synchronized (this.b)
    {
      this.d.clear();
      b(this.d);
      long l2 = System.currentTimeMillis();
      if ((com.wave.feature.a.an.a()) || (com.wave.feature.a.ao.a())) {
        this.d.addAll(a.a().c(this.a));
      }
      if (com.wave.feature.a.ap.a()) {
        this.d.addAll(a.a().d(this.a));
      }
      Log.d("ThemesRepo", "fetchInstalledApps diskthemes finished in " + (System.currentTimeMillis() - l2));
      System.currentTimeMillis();
      Iterator localIterator = this.a.getPackageManager().getInstalledApplications(128).iterator();
      for (;;)
      {
        if (localIterator.hasNext())
        {
          ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
          if (!localApplicationInfo.packageName.contains(b.c))
          {
            boolean bool = localApplicationInfo.packageName.contains(b.d);
            if (!bool) {
              continue;
            }
          }
          try
          {
            l2 = System.currentTimeMillis();
            com.wave.keyboard.c.a localA = new com.wave.keyboard.c.a();
            localA.packageName = localApplicationInfo.packageName;
            localA.shortName = localA.a();
            a(this.d, localA);
            Log.d("ThemesRepo", "fetchInstalledApps theme " + localA.packageName + " finished in " + (System.currentTimeMillis() - l2));
            System.currentTimeMillis();
          }
          catch (Exception localException)
          {
            com.wave.n.a.a(localException);
          }
        }
      }
    }
    Log.d("ThemesRepo", "fetchInstalledApps from " + paramString + " finished in " + (System.currentTimeMillis() - l1));
  }
  
  public boolean a(com.wave.keyboard.c.a paramA, String paramString)
  {
    if (this.d == null) {
      return false;
    }
    for (;;)
    {
      synchronized (this.b)
      {
        Log.d("ThemesRepo", "removeTheme from " + paramString);
        Iterator localIterator = this.d.iterator();
        if (localIterator.hasNext())
        {
          paramString = (com.wave.keyboard.c.a)localIterator.next();
          if (!paramString.isEqual(paramA)) {
            continue;
          }
          paramA = paramString;
          if (paramA == null) {
            break label124;
          }
          this.d.remove(paramA);
          break label124;
          label103:
          return bool;
        }
      }
      label124:
      do
      {
        bool = false;
        break label103;
        paramA = null;
        break;
      } while (paramA == null);
      boolean bool = true;
    }
  }
  
  public List<com.wave.keyboard.c.a> b()
  {
    return a(false);
  }
  
  public boolean b(String paramString)
  {
    if (s.g(paramString)) {
      return false;
    }
    Iterator localIterator = a(false).iterator();
    while (localIterator.hasNext()) {
      if (((com.wave.keyboard.c.a)localIterator.next()).shortName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public com.wave.keyboard.c.a c()
  {
    com.wave.keyboard.c.a localA = new com.wave.keyboard.c.a();
    localA.shortName = "wave.keyboard.green";
    localA.packageName = this.a.getPackageName();
    localA.a(ThemeResourceDict.getGreenResources());
    return localA;
  }
  
  public com.wave.keyboard.c.a d()
  {
    com.wave.keyboard.c.a localA = new com.wave.keyboard.c.a();
    localA.shortName = "wave.keyboard.white";
    localA.packageName = this.a.getPackageName();
    localA.a(ThemeResourceDict.getWhiteResources());
    return localA;
  }
}
