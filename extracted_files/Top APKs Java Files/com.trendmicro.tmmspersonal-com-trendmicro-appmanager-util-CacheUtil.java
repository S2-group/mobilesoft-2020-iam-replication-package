package com.trendmicro.appmanager.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.usage.StorageStats;
import android.app.usage.StorageStatsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.IPackageStatsObserver.Stub;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageStats;
import android.os.Build.VERSION;
import android.os.Process;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.text.format.Formatter;
import com.trendmicro.tmmssuite.core.sys.c;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class CacheUtil
{
  private static Method e;
  private static CountDownLatch f;
  private static long g = 0L;
  private static Object h = new Object();
  private final PackageManager a;
  private Semaphore b = new Semaphore(0);
  private PackageStats c = null;
  private Context d;
  
  public CacheUtil(Context paramContext)
  {
    this.d = paramContext.getApplicationContext();
    this.a = paramContext.getApplicationContext().getPackageManager();
  }
  
  @TargetApi(26)
  private static long a(Context paramContext, String paramString)
  {
    long l1 = 0L;
    long l2;
    if (Build.VERSION.SDK_INT < 26) {
      l2 = l1;
    }
    Object localObject;
    do
    {
      do
      {
        return l2;
        localObject = ((StorageManager)paramContext.getSystemService("storage")).getStorageVolumes();
        l2 = l1;
      } while (localObject == null);
      l2 = l1;
    } while (((List)localObject).size() == 0);
    StorageStatsManager localStorageStatsManager = (StorageStatsManager)paramContext.getSystemService("storagestats");
    Iterator localIterator = ((List)localObject).iterator();
    for (;;)
    {
      l2 = l1;
      if (!localIterator.hasNext()) {
        break;
      }
      localObject = ((StorageVolume)localIterator.next()).getUuid();
      label109:
      long l3;
      long l4;
      if (localObject == null)
      {
        localObject = StorageManager.UUID_DEFAULT;
        l2 = l1;
        l3 = l1;
        l4 = l1;
      }
      try
      {
        StorageStats localStorageStats = localStorageStatsManager.queryStatsForPackage((UUID)localObject, paramString, Process.myUserHandle());
        l2 = l1;
        l3 = l1;
        l4 = l1;
        l1 += localStorageStats.getAppBytes() + localStorageStats.getCacheBytes() + localStorageStats.getDataBytes();
        l2 = l1;
        l3 = l1;
        l4 = l1;
        c.c("CacheUtil", paramString + "on storage: " + localObject + ", appBytes:" + Formatter.formatShortFileSize(paramContext, localStorageStats.getAppBytes()) + ", cacheBytes:" + Formatter.formatShortFileSize(paramContext, localStorageStats.getCacheBytes()) + ", dataBytes:" + Formatter.formatShortFileSize(paramContext, localStorageStats.getDataBytes()));
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localNameNotFoundException.printStackTrace();
        l1 = l2;
        continue;
        UUID localUUID = UUID.fromString(localNameNotFoundException);
        break label109;
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        l1 = l3;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        l1 = l4;
      }
    }
  }
  
  private PackageStats b(String paramString)
  {
    Method localMethod = f();
    PackageStatsObserver localPackageStatsObserver;
    if (localMethod != null) {
      localPackageStatsObserver = new PackageStatsObserver(null);
    }
    try
    {
      localMethod.invoke(this.a, new Object[] { paramString, localPackageStatsObserver });
      d();
      return this.c;
    }
    catch (IllegalArgumentException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
    catch (IllegalAccessException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
    catch (InvocationTargetException paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
  }
  
  private void d()
  {
    try
    {
      this.b.acquire();
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException.printStackTrace();
    }
  }
  
  private void e()
  {
    this.b.release();
  }
  
  private static Method f()
  {
    if (e == null) {}
    try
    {
      e = PackageManager.class.getMethod("getPackageSizeInfo", new Class[] { String.class, IPackageStatsObserver.class });
      return e;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      for (;;)
      {
        localNoSuchMethodException.printStackTrace();
      }
    }
  }
  
  @SuppressLint({"NewApi"})
  public long a(String paramString)
  {
    long l2;
    if (Build.VERSION.SDK_INT >= 26) {
      l2 = a(this.d, paramString);
    }
    long l1;
    do
    {
      do
      {
        return l2;
        l2 = 0L;
        this.c = b(paramString);
      } while (this.c == null);
      l2 = this.c.cacheSize + this.c.codeSize + this.c.dataSize;
      l1 = l2;
      if (Build.VERSION.SDK_INT >= 11) {
        l1 = l2 + this.c.externalCacheSize + this.c.externalDataSize + this.c.externalMediaSize + this.c.externalObbSize;
      }
      l2 = l1;
    } while (Build.VERSION.SDK_INT < 14);
    return l1 + this.c.externalCodeSize;
  }
  
  private static class GetTotalCacheTread
    extends Thread
  {
    private Context a;
    
    public void a(String paramString)
    {
      try
      {
        PackageManager localPackageManager = this.a.getPackageManager();
        localPackageManager.getClass().getMethod("getPackageSizeInfo", new Class[] { String.class, IPackageStatsObserver.class }).invoke(localPackageManager, new Object[] { paramString, new TotalCachePackageStatsObserver() });
        return;
      }
      catch (IllegalArgumentException paramString)
      {
        paramString.printStackTrace();
        return;
      }
      catch (IllegalAccessException paramString)
      {
        paramString.printStackTrace();
        return;
      }
      catch (InvocationTargetException paramString)
      {
        paramString.printStackTrace();
        return;
      }
      catch (SecurityException paramString)
      {
        paramString.printStackTrace();
        return;
      }
      catch (NoSuchMethodException paramString)
      {
        paramString.printStackTrace();
      }
    }
    
    public void run()
    {
      List localList = this.a.getPackageManager().getInstalledApplications(0);
      int j = localList.size();
      int i = 0;
      while (i < j)
      {
        a(((ApplicationInfo)localList.get(i)).packageName);
        i += 1;
      }
    }
    
    class TotalCachePackageStatsObserver
      extends IPackageStatsObserver.Stub
    {
      TotalCachePackageStatsObserver() {}
      
      public void onGetStatsCompleted(PackageStats paramPackageStats, boolean paramBoolean)
      {
        if ((paramBoolean) && (paramPackageStats != null)) {}
        synchronized (CacheUtil.a())
        {
          CacheUtil.a(CacheUtil.b() + paramPackageStats.cacheSize);
          CacheUtil.c().countDown();
          return;
        }
      }
    }
  }
  
  private class PackageStatsObserver
    extends IPackageStatsObserver.Stub
  {
    private PackageStatsObserver() {}
    
    public void onGetStatsCompleted(PackageStats paramPackageStats, boolean paramBoolean)
    {
      if ((paramBoolean) && (paramPackageStats != null)) {
        CacheUtil.a(CacheUtil.this, paramPackageStats);
      }
      CacheUtil.a(CacheUtil.this);
    }
  }
}
