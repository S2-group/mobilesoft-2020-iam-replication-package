package com.fancyclean.boost.junkclean.a.b;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.IPackageStatsObserver.Stub;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.Build.VERSION;
import android.os.Environment;
import com.crashlytics.android.Crashlytics;
import com.fancyclean.boost.junkclean.model.junkItem.CacheJunkItem;
import com.fancyclean.boost.junkclean.model.junkItem.JunkItem;
import com.thinkyeah.common.h.g;
import com.thinkyeah.common.q;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class d
  extends c
{
  private static final q d = q.a(d.class);
  private Method e;
  
  d(Context paramContext, com.fancyclean.boost.junkclean.model.c paramC, Set<String> paramSet)
  {
    super(paramContext, paramC, paramSet);
    this.b = paramC;
    try
    {
      this.e = this.a.getPackageManager().getClass().getMethod("getPackageSizeInfo", new Class[] { String.class, IPackageStatsObserver.class });
      return;
    }
    catch (NoSuchMethodException paramContext)
    {
      d.a("Create getPackageSizeInfoMethod failed", paramContext);
    }
  }
  
  private void b(f.a paramA)
  {
    if (!b())
    {
      d.h("External storage is unavailable");
      return;
    }
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(Environment.getExternalStorageDirectory().getAbsolutePath());
    ((StringBuilder)localObject1).append("/Android/data");
    Object localObject2 = new File(((StringBuilder)localObject1).toString());
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(((File)localObject2).getAbsolutePath());
    ((StringBuilder)localObject1).append("/%s/cache");
    localObject1 = ((StringBuilder)localObject1).toString();
    if (!((File)localObject2).isDirectory())
    {
      d.h("External data directory is not a directory!");
      return;
    }
    localObject2 = ((File)localObject2).listFiles();
    if ((localObject2 != null) && (localObject2.length > 0))
    {
      int j = localObject2.length;
      int i = 0;
      while (i < j)
      {
        String str = localObject2[i];
        File localFile = new File(String.format((String)localObject1, new Object[] { str.getName() }));
        if (paramA.a())
        {
          d.e("scan, cache junk cancelled");
          return;
        }
        if (localFile.exists())
        {
          Object localObject3 = str.getName();
          if ((!this.c.contains(localObject3)) && (!com.fancyclean.boost.common.c.d.c().equals(localObject3)))
          {
            str = com.thinkyeah.common.h.a.d(this.a, (String)localObject3);
            long l = g.f(localFile);
            if ((!com.fancyclean.boost.common.c.b.a(str)) && (l > 0L))
            {
              localObject3 = new CacheJunkItem(0, (String)localObject3);
              ((CacheJunkItem)localObject3).e = str;
              ((CacheJunkItem)localObject3).g.set(l);
              ((CacheJunkItem)localObject3).d.add(localFile.getAbsolutePath());
              ((CacheJunkItem)localObject3).i = true;
              ((CacheJunkItem)localObject3).f = this.a.getString(2131820737);
              paramA.a(l);
              paramA.a((JunkItem)localObject3);
            }
          }
        }
        i += 1;
      }
      return;
    }
    d.h("No cache files found");
  }
  
  private boolean b()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  private void c(final f.a paramA)
  {
    try
    {
      Object localObject = this.a.getPackageManager().getInstalledApplications(0);
      if (!com.fancyclean.boost.common.c.b.a((Collection)localObject)) {
        try
        {
          final CountDownLatch localCountDownLatch = new CountDownLatch(((List)localObject).size());
          final CacheJunkItem localCacheJunkItem = new CacheJunkItem(0, "com.cachejunk.placeholder");
          localCacheJunkItem.b = true;
          localCacheJunkItem.i = true;
          localCacheJunkItem.e = this.a.getString(2131821078);
          localCacheJunkItem.f = this.a.getString(2131820737);
          localObject = ((List)localObject).iterator();
          while (((Iterator)localObject).hasNext())
          {
            ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
            if ((paramA.a()) || (this.e == null)) {
              break;
            }
            this.e.invoke(this.a.getPackageManager(), new Object[] { localApplicationInfo.packageName, new IPackageStatsObserver.Stub()
            {
              public void onGetStatsCompleted(PackageStats paramAnonymousPackageStats, boolean paramAnonymousBoolean)
              {
                localCountDownLatch.countDown();
                if (paramAnonymousPackageStats == null)
                {
                  d.a().h("PackageStats is null");
                  return;
                }
                long l = paramAnonymousPackageStats.cacheSize + 0L;
                q localQ = d.a();
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append("Get app cache size, packageName: ");
                localStringBuilder.append(paramAnonymousPackageStats.packageName);
                localStringBuilder.append(", cache size: ");
                localStringBuilder.append(paramAnonymousPackageStats.cacheSize);
                localQ.h(localStringBuilder.toString());
                if (paramAnonymousBoolean)
                {
                  if (l <= 0L) {
                    return;
                  }
                  paramA.a(l);
                  localCacheJunkItem.g.addAndGet(l);
                  return;
                }
              }
            } });
          }
          try
          {
            localCountDownLatch.await(5L, TimeUnit.MINUTES);
          }
          catch (InterruptedException localInterruptedException)
          {
            d.a(localInterruptedException);
          }
          if (localCacheJunkItem.g.get() > 0L)
          {
            paramA.a(localCacheJunkItem);
            return;
          }
        }
        catch (Exception paramA)
        {
          d.a("Fail to get package size info", paramA);
        }
      }
      return;
    }
    catch (Exception paramA)
    {
      d.a(paramA);
      Crashlytics.logException(paramA);
    }
  }
  
  public void a(f.a paramA)
  {
    long l1 = System.currentTimeMillis();
    long l2 = com.fancyclean.boost.junkclean.a.a(this.a);
    b(paramA);
    if ((Build.VERSION.SDK_INT < 26) && ((com.fancyclean.boost.common.b.x(this.a)) || (l1 < l2) || (l1 - l2 > 300000L))) {
      c(paramA);
    }
    d.h("Cache scan done");
  }
}
