package com.sail.advanced.booster.appmanager;

import a.a.b.b;
import a.a.d.e;
import a.a.h;
import a.a.i;
import a.a.j;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.IPackageStatsObserver.Stub;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.sail.advanced.booster.i.v;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;

public class c
{
  private final b.a a;
  private final a.a.b.a b;
  private Method c;
  private boolean d = false;
  
  public c(b.a paramA)
  {
    this.a = paramA;
    this.b = new a.a.b.a();
  }
  
  private com.sail.advanced.booster.b.a a(PackageInfo paramPackageInfo, PackageManager paramPackageManager)
  {
    com.sail.advanced.booster.b.a localA = new com.sail.advanced.booster.b.a();
    Drawable localDrawable = paramPackageInfo.applicationInfo.loadIcon(paramPackageManager);
    localA.setCacheDir(paramPackageInfo.applicationInfo.dataDir);
    localA.setAppIcon(localDrawable);
    int i = paramPackageInfo.applicationInfo.flags;
    localA.setUid(paramPackageInfo.applicationInfo.uid);
    if ((i & 0x1) != 0) {
      localA.setUserApp(false);
    } else {
      localA.setUserApp(true);
    }
    if ((i & 0x40000) != 0) {
      localA.setInRom(false);
    } else {
      localA.setInRom(true);
    }
    localA.setAppName(paramPackageInfo.applicationInfo.loadLabel(paramPackageManager).toString());
    localA.setPackname(paramPackageInfo.packageName);
    localA.setVersion(paramPackageInfo.versionName);
    localA.setLastUpdateTime(paramPackageInfo.lastUpdateTime);
    return localA;
  }
  
  private void a(final List<com.sail.advanced.booster.b.a> paramList)
  {
    paramList = h.a(new j()
    {
      public void a(i<com.sail.advanced.booster.b.a> paramAnonymousI)
      {
        Object localObject1;
        if (Build.VERSION.SDK_INT >= 26)
        {
          localIterator = paramList.iterator();
          while (localIterator.hasNext())
          {
            localObject1 = (com.sail.advanced.booster.b.a)localIterator.next();
            long l = 0L;
            if (c.b(c.this)) {
              l = com.sail.advanced.booster.i.a.a(v.a(), new File(((com.sail.advanced.booster.b.a)localObject1).getCacheDir()), ((com.sail.advanced.booster.b.a)localObject1).getPackname());
            }
            ((com.sail.advanced.booster.b.a)localObject1).setPkgSize(l);
            paramAnonymousI.onNext(localObject1);
          }
        }
        Iterator localIterator = paramList.iterator();
        while (localIterator.hasNext())
        {
          Object localObject2 = (com.sail.advanced.booster.b.a)localIterator.next();
          if (c.c(c.this) != null)
          {
            com.a.a.a.a(new Object[] { "query size" });
            localObject1 = ((com.sail.advanced.booster.b.a)localObject2).getPackname();
            localObject2 = new IPackageStatsObserver.Stub()
            {
              public void onGetStatsCompleted(PackageStats paramAnonymous2PackageStats, boolean paramAnonymous2Boolean)
              {
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append("onGetStatsCompleted:");
                localStringBuilder.append(paramAnonymous2PackageStats.cacheSize);
                localStringBuilder.append(paramAnonymous2PackageStats.codeSize);
                localStringBuilder.append(paramAnonymous2PackageStats.dataSize);
                localStringBuilder.append(", currThread:");
                localStringBuilder.append(Thread.currentThread().getName());
                com.a.a.a.a(new Object[] { localStringBuilder.toString() });
                this.a.setPkgSize(paramAnonymous2PackageStats.cacheSize + paramAnonymous2PackageStats.codeSize + paramAnonymous2PackageStats.dataSize);
                c.d(c.this).a(h.a(this.a).a(a.a.a.b.a.a()).a(new e()new e
                {
                  public void a(com.sail.advanced.booster.b.a paramAnonymous3A)
                  {
                    c.a(c.this).a(paramAnonymous3A);
                  }
                }, new e()
                {
                  public void a(Throwable paramAnonymous3Throwable)
                  {
                    com.a.a.a.b(new Object[] { paramAnonymous3Throwable });
                  }
                }));
              }
            };
            c.c(c.this).invoke(v.a().getPackageManager(), new Object[] { localObject1, localObject2 });
          }
          else
          {
            com.a.a.a.a(new Object[] { "mGetPackageSizeInfoMethod is null" });
          }
        }
        paramAnonymousI.onComplete();
      }
    }).a(a.a.a.b.a.a()).b(a.a.h.a.b()).c().a(new e()
    {
      public void a(com.sail.advanced.booster.b.a paramAnonymousA)
      {
        c.a(c.this).a(paramAnonymousA);
      }
    });
    this.b.a(paramList);
  }
  
  public void a()
  {
    if (Build.VERSION.SDK_INT < 26) {
      try
      {
        this.c = v.a().getPackageManager().getClass().getMethod("getPackageSizeInfo", new Class[] { String.class, IPackageStatsObserver.class });
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        localNoSuchMethodException.printStackTrace();
      }
    }
    b();
  }
  
  public void a(boolean paramBoolean)
  {
    this.d = paramBoolean;
  }
  
  public void b()
  {
    this.a.x();
    b localB = h.a(new Callable()
    {
      public List<com.sail.advanced.booster.b.a> a()
      {
        ArrayList localArrayList = new ArrayList();
        PackageManager localPackageManager = v.a().getPackageManager();
        Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
        while (localIterator.hasNext())
        {
          Object localObject = (PackageInfo)localIterator.next();
          if ((((PackageInfo)localObject).applicationInfo.flags & 0x1) == 0)
          {
            localObject = c.a(c.this, (PackageInfo)localObject, localPackageManager);
            if (!TextUtils.equals(v.a().getPackageName(), ((com.sail.advanced.booster.b.a)localObject).getPackname())) {
              localArrayList.add(localObject);
            }
          }
        }
        return localArrayList;
      }
    }).b(a.a.h.a.b()).a(a.a.a.b.a.a()).a(new e()
    {
      public void a(List<com.sail.advanced.booster.b.a> paramAnonymousList)
      {
        c.a(c.this).y();
        c.a(c.this).a(paramAnonymousList);
        c.a(c.this, paramAnonymousList);
      }
    });
    this.b.a(localB);
  }
  
  public void c()
  {
    this.b.a();
  }
}
