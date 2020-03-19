package com.fancyclean.boost.appmanager.a;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.usage.StorageStats;
import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.IPackageStatsObserver.Stub;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.Build.VERSION;
import android.os.Process;
import android.os.RemoteException;
import android.os.storage.StorageManager;
import com.fancyclean.boost.appmanager.b.a;
import com.fancyclean.boost.common.i;
import com.thinkyeah.common.q;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class c
{
  private static final q a = q.a(c.class);
  @SuppressLint({"StaticFieldLeak"})
  private static c b;
  private Context c;
  private Method d;
  
  private c(Context paramContext)
  {
    this.c = paramContext.getApplicationContext();
    try
    {
      this.d = this.c.getPackageManager().getClass().getMethod("getPackageSizeInfo", new Class[] { String.class, IPackageStatsObserver.class });
      return;
    }
    catch (NoSuchMethodException paramContext)
    {
      a.a("Create getPackageSizeInfoMethod failed", paramContext);
    }
  }
  
  public static c a(Context paramContext)
  {
    if (b == null) {
      try
      {
        if (b == null) {
          b = new c(paramContext);
        }
      }
      finally {}
    }
    return b;
  }
  
  private com.fancyclean.boost.appmanager.b.b a(PackageStats paramPackageStats, boolean paramBoolean)
  {
    if ((paramPackageStats != null) && (paramBoolean))
    {
      long l = paramPackageStats.cacheSize + 0L + paramPackageStats.externalCacheSize + paramPackageStats.codeSize + paramPackageStats.dataSize + paramPackageStats.externalDataSize;
      com.fancyclean.boost.appmanager.b.b localB = new com.fancyclean.boost.appmanager.b.b();
      localB.a = paramPackageStats.packageName;
      localB.b = l;
      paramPackageStats = a;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("package: ");
      localStringBuilder.append(localB.a);
      localStringBuilder.append(", appStorageSize: ");
      localStringBuilder.append(l);
      localStringBuilder.append("");
      paramPackageStats.h(localStringBuilder.toString());
      return localB;
    }
    a.h("failed to PackageStats");
    return null;
  }
  
  @TargetApi(26)
  private com.fancyclean.boost.appmanager.b.b a(String paramString, StorageStats paramStorageStats)
  {
    if (paramStorageStats == null)
    {
      a.h("failed to storageStats");
      return null;
    }
    long l = paramStorageStats.getCacheBytes() + 0L + paramStorageStats.getAppBytes() + paramStorageStats.getDataBytes();
    paramStorageStats = new com.fancyclean.boost.appmanager.b.b();
    paramStorageStats.a = paramString;
    paramStorageStats.b = l;
    paramString = a;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("package: ");
    localStringBuilder.append(paramStorageStats.a);
    localStringBuilder.append(", appStorageSize: ");
    localStringBuilder.append(l);
    localStringBuilder.append("");
    paramString.h(localStringBuilder.toString());
    return paramStorageStats;
  }
  
  private boolean a(PackageInfo paramPackageInfo)
  {
    return (paramPackageInfo.applicationInfo.flags & 0x1) != 0;
  }
  
  private boolean a(List<a> paramList, final Map<String, com.fancyclean.boost.appmanager.b.b> paramMap, final a paramA)
  {
    if (com.fancyclean.boost.common.c.b.a(paramList)) {
      return false;
    }
    try
    {
      final CountDownLatch localCountDownLatch = new CountDownLatch(paramList.size());
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        final String str = ((a)paramList.next()).a();
        if (this.d == null) {
          localCountDownLatch.countDown();
        } else {
          this.d.invoke(this.c.getPackageManager(), new Object[] { str, new IPackageStatsObserver.Stub()
          {
            public void onGetStatsCompleted(PackageStats paramAnonymousPackageStats, boolean paramAnonymousBoolean)
              throws RemoteException
            {
              localCountDownLatch.countDown();
              paramAnonymousPackageStats = c.a(c.this, paramAnonymousPackageStats, paramAnonymousBoolean);
              if (paramAnonymousPackageStats != null)
              {
                paramMap.put(paramAnonymousPackageStats.a, paramAnonymousPackageStats);
                paramA.a(str, paramAnonymousPackageStats);
              }
            }
          } });
        }
      }
      try
      {
        localCountDownLatch.await(5L, TimeUnit.MINUTES);
        return true;
      }
      catch (InterruptedException paramList)
      {
        a.a(paramList);
        return true;
      }
      return false;
    }
    catch (Exception paramList)
    {
      a.a("Get package storage size info failed", paramList);
    }
  }
  
  private boolean b(PackageInfo paramPackageInfo)
  {
    return this.c.getPackageName().equalsIgnoreCase(paramPackageInfo.applicationInfo.packageName);
  }
  
  @TargetApi(26)
  private boolean b(List<a> paramList, Map<String, com.fancyclean.boost.appmanager.b.b> paramMap, a paramA)
  {
    if (i.c(this.c))
    {
      HashSet localHashSet = new HashSet();
      paramList = paramList.iterator();
      while (paramList.hasNext()) {
        localHashSet.add(((a)paramList.next()).a());
      }
      paramList = (StorageStatsManager)this.c.getSystemService("storagestats");
      if (paramList == null)
      {
        a.e("StorageStatsManager is null");
        return false;
      }
      try
      {
        List localList = this.c.getPackageManager().getInstalledPackages(0);
        int i = 0;
        while (i < localList.size())
        {
          PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
          if (localHashSet.contains(localPackageInfo.packageName))
          {
            Object localObject = paramList.queryStatsForPackage(StorageManager.UUID_DEFAULT, localPackageInfo.packageName, Process.myUserHandle());
            q localQ = a;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append(localPackageInfo.packageName);
            localStringBuilder.append(" cache size: ");
            localStringBuilder.append(((StorageStats)localObject).getCacheBytes());
            localQ.h(localStringBuilder.toString());
            localObject = a(localPackageInfo.packageName, (StorageStats)localObject);
            if (localObject != null)
            {
              paramMap.put(((com.fancyclean.boost.appmanager.b.b)localObject).a, localObject);
              paramA.a(localPackageInfo.packageName, (com.fancyclean.boost.appmanager.b.b)localObject);
            }
          }
          i += 1;
        }
        return true;
      }
      catch (Exception paramList)
      {
        a.a("Failed to get system cache", paramList);
        return false;
      }
    }
    a.e("No permission: android.permission.PACKAGE_USAGE_STATS");
    return false;
  }
  
  public String a(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("https://play.google.com/store/apps/details?id=");
    localStringBuilder.append(paramString);
    return localStringBuilder.toString();
  }
  
  public boolean a()
  {
    return (Build.VERSION.SDK_INT < 26) || (i.c(this.c));
  }
  
  public boolean a(List<a> paramList)
  {
    b.a().a(b.c.b);
    HashMap localHashMap = new HashMap();
    Object localObject = new a()
    {
      public void a(String paramAnonymousString, com.fancyclean.boost.appmanager.b.b paramAnonymousB)
      {
        if ((paramAnonymousString != null) && (paramAnonymousB != null)) {
          b.a().a(paramAnonymousString, paramAnonymousB);
        }
      }
    };
    boolean bool;
    if (Build.VERSION.SDK_INT >= 26) {
      bool = b(paramList, localHashMap, (a)localObject);
    } else {
      bool = a(paramList, localHashMap, (a)localObject);
    }
    paramList = a;
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("scan apps storage size result: ");
    ((StringBuilder)localObject).append(bool);
    paramList.h(((StringBuilder)localObject).toString());
    if (localHashMap.size() > 0)
    {
      b.a().a(b.c.c);
      return true;
    }
    b.a().a(b.c.a);
    return false;
  }
  
  public List<a> b()
  {
    PackageManager localPackageManager = this.c.getPackageManager();
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    List localList = localPackageManager.getInstalledPackages(0);
    while (i < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      if ((!a(localPackageInfo)) && (!b(localPackageInfo)))
      {
        a localA = new a(localPackageInfo.packageName);
        localA.a(localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString().trim());
        localA.b(localPackageInfo.versionName);
        localA.a(localPackageInfo.versionCode);
        localA.a(localPackageInfo.firstInstallTime);
        localA.b(localPackageInfo.lastUpdateTime);
        localArrayList.add(localA);
      }
      i += 1;
    }
    return localArrayList;
  }
  
  public static abstract interface a
  {
    public abstract void a(String paramString, com.fancyclean.boost.appmanager.b.b paramB);
  }
}
