package com.diskusage;

import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.IPackageStatsObserver.Stub;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.StatFs;
import android.util.Log;
import com.diskusage.a.e;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class a
{
  String a = "";
  boolean b = true;
  private DiskUsage c;
  private int d;
  private int e;
  
  public a(DiskUsage paramDiskUsage)
  {
    this.c = paramDiskUsage;
  }
  
  private Map<String, Long> a()
  {
    TreeMap localTreeMap = new TreeMap();
    for (;;)
    {
      Object localObject1;
      try
      {
        BufferedReader localBufferedReader = new BufferedReader(new FileReader("/proc/mounts"));
        localObject1 = localBufferedReader.readLine();
        if (localObject1 != null)
        {
          Object localObject2 = ((String)localObject1).split(" +");
          if (localObject2.length < 3) {
            continue;
          }
          localObject1 = localObject2[1];
          if ((localObject2[2].equals("tmpfs")) || (!((String)localObject1).startsWith("/mnt/asec/"))) {
            continue;
          }
          localObject2 = ((String)localObject1).substring(((String)localObject1).lastIndexOf('/') + 1);
          localObject2 = ((String)localObject2).substring(0, ((String)localObject2).indexOf('-'));
          localObject1 = new StatFs((String)localObject1);
          if (Build.VERSION.SDK_INT >= 18)
          {
            l1 = ((StatFs)localObject1).getBlockCountLong();
            long l2 = ((StatFs)localObject1).getAvailableBlocksLong();
            l1 = ((StatFs)localObject1).getBlockSizeLong() * (l1 - l2);
            localTreeMap.put(localObject2, Long.valueOf(l1));
            Log.d("diskusage", "external size (" + (String)localObject2 + ") = " + l1 / 1024L + " kb");
          }
        }
        else
        {
          return localTreeMap;
        }
      }
      catch (Throwable localThrowable)
      {
        Log.e("disksusage", "failed to parse /proc/mounts", localThrowable);
      }
      int i = ((StatFs)localObject1).getBlockCount();
      int j = ((StatFs)localObject1).getAvailableBlocks();
      int k = ((StatFs)localObject1).getBlockSize();
      long l1 = k * (i - j);
    }
  }
  
  private void a(int paramInt)
  {
    try
    {
      this.d += paramInt;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public com.diskusage.a.a[] a(boolean paramBoolean, final AppFilter paramAppFilter, final int paramInt)
  {
    final Map localMap = a();
    final ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = this.c.getPackageManager();
    Method localMethod = localPackageManager.getClass().getMethod("getPackageSizeInfo", new Class[] { String.class, IPackageStatsObserver.class });
    Object localObject = localPackageManager.getInstalledPackages(8320);
    Handler localHandler = this.c.f;
    Runnable local1 = new Runnable()
    {
      public void run()
      {
        MyProgressDialog localMyProgressDialog = a.a(a.this).i().c;
        if (localMyProgressDialog != null)
        {
          if (a.this.b)
          {
            localMyProgressDialog.b();
            a.this.b = false;
          }
          localMyProgressDialog.a(this.a.size());
          localMyProgressDialog.a(a.b(a.this), a.this.a);
        }
        a.a(a.this).f.postDelayed(this, 50L);
      }
    };
    localHandler.post(local1);
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      final PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if (localPackageInfo.applicationInfo == null)
      {
        Log.d("diskusage", "No applicationInfo");
      }
      else
      {
        if ((0x40000 & localPackageInfo.applicationInfo.flags) != 0) {}
        for (int i = 1;; i = 0)
        {
          if ((i == 0) && (paramBoolean)) {
            break label249;
          }
          a(1);
          final String str1 = localPackageInfo.packageName;
          final String str2 = localPackageManager.getApplicationLabel(localPackageInfo.applicationInfo).toString();
          this.a = str2;
          localMethod.invoke(localPackageManager, new Object[] { str1, new IPackageStatsObserver.Stub()
          {
            public void onGetStatsCompleted(PackageStats paramAnonymousPackageStats, boolean paramAnonymousBoolean)
            {
              synchronized (a.this)
              {
                a.c(a.this);
                a.a(a.this, -1);
                if (paramAnonymousBoolean)
                {
                  paramAnonymousPackageStats = new e(str2, str1, paramAnonymousPackageStats, localPackageInfo.applicationInfo.flags, (Long)localMap.get(str1), paramInt);
                  paramAnonymousPackageStats.a(paramAppFilter, paramInt);
                  localArrayList.add(paramAnonymousPackageStats);
                }
                a.this.notify();
                return;
              }
            }
          } });
          break;
        }
        try
        {
          label249:
          this.a = localPackageManager.getApplicationLabel(localPackageInfo.applicationInfo).toString();
          this.e += 1;
        }
        finally {}
      }
    }
    for (;;)
    {
      try
      {
        if (this.d != 0) {
          wait();
        }
      }
      finally {}
    }
    if (localArrayList.size() == 0) {
      return null;
    }
    paramAppFilter = (com.diskusage.a.a[])localArrayList.toArray(new com.diskusage.a.a[0]);
    Arrays.sort(paramAppFilter, com.diskusage.a.a.l);
    localHandler.removeCallbacks(local1);
    return paramAppFilter;
  }
}
