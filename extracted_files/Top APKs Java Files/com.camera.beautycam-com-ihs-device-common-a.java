package com.ihs.device.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.ihs.commons.e.f;
import com.ihs.device.common.utils.b;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class a
{
  private final List<a> a = new ArrayList();
  private ReentrantReadWriteLock b = new ReentrantReadWriteLock();
  
  private a()
  {
    this.b.writeLock().lock();
    try
    {
      Object localObject1 = new IntentFilter();
      ((IntentFilter)localObject1).addAction("android.intent.action.PACKAGE_ADDED");
      ((IntentFilter)localObject1).addAction("android.intent.action.PACKAGE_REMOVED");
      ((IntentFilter)localObject1).addDataScheme("package");
      com.ihs.app.framework.a.a().registerReceiver(new BroadcastReceiver()
      {
        public void onReceive(final Context paramAnonymousContext, final Intent paramAnonymousIntent)
        {
          f.c("HSPackageManager-LWJlog", paramAnonymousIntent.getAction());
          if (paramAnonymousIntent.getData() == null) {}
          do
          {
            return;
            paramAnonymousContext = paramAnonymousIntent.getAction();
            paramAnonymousIntent = paramAnonymousIntent.getData().getSchemeSpecificPart();
            f.c("HSPackageManager-LWJlog", "packageName = " + paramAnonymousIntent);
          } while (TextUtils.isEmpty(paramAnonymousIntent));
          new Thread(new Runnable()
          {
            public void run()
            {
              a.a(a.this).writeLock().lock();
              Object localObject1;
              int i;
              int j;
              for (;;)
              {
                label79:
                Object localObject3;
                try
                {
                  localObject1 = paramAnonymousContext;
                  i = -1;
                  j = ((String)localObject1).hashCode();
                  switch (j)
                  {
                  default: 
                    switch (i)
                    {
                    default: 
                      return;
                    }
                  case 1544582882: 
                    if (!((String)localObject1).equals("android.intent.action.PACKAGE_ADDED")) {
                      break;
                    }
                    i = 0;
                    break;
                  }
                  boolean bool = ((String)localObject1).equals("android.intent.action.PACKAGE_REMOVED");
                  if (!bool) {
                    break;
                  }
                  i = 1;
                  break;
                  try
                  {
                    localObject1 = com.ihs.app.framework.a.a().getPackageManager();
                    localObject3 = ((PackageManager)localObject1).getApplicationInfo(paramAnonymousIntent, 128);
                    if (!a.a(a.this, (ApplicationInfo)localObject3, (PackageManager)localObject1)) {
                      continue;
                    }
                    Iterator localIterator2 = a.b(a.this).iterator();
                    if (localIterator2.hasNext())
                    {
                      a.a localA = (a.a)localIterator2.next();
                      if (!TextUtils.equals(((ApplicationInfo)localObject3).packageName, a.a.a(localA))) {
                        continue;
                      }
                      a.b(a.this).remove(localA);
                    }
                    a.b(a.this).add(new a.a((ApplicationInfo)localObject3, (PackageManager)localObject1));
                  }
                  catch (Exception localException)
                  {
                    localException.printStackTrace();
                  }
                  if (!f.a()) {
                    continue;
                  }
                  throw new RuntimeException("HSPackageManager debug exception: installed an app but not found in PackageManager");
                }
                finally
                {
                  a.a(a.this).writeLock().unlock();
                }
                Iterator localIterator1 = a.b(a.this).iterator();
                if (localIterator1.hasNext())
                {
                  localObject3 = (a.a)localIterator1.next();
                  if (!TextUtils.equals(paramAnonymousIntent, a.a.a((a.a)localObject3))) {
                    break label79;
                  }
                  a.b(a.this).remove(localObject3);
                }
              }
            }
          }).start();
        }
      }, (IntentFilter)localObject1);
      localObject1 = com.ihs.app.framework.a.a().getPackageManager();
      Iterator localIterator = ((PackageManager)localObject1).getInstalledApplications(128).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (a(localApplicationInfo, (PackageManager)localObject1)) {
          this.a.add(new a(localApplicationInfo, (PackageManager)localObject1));
        }
      }
      f.c("HSPackageManager-LWJlog", "HSPackageManager init finished, appInfoCacheList.size() = " + this.a.size());
    }
    catch (Exception localException)
    {
      if (!f.a()) {
        break label213;
      }
      throw localException;
    }
    finally
    {
      this.b.writeLock().unlock();
    }
    this.b.writeLock().unlock();
    return;
    label213:
    localObject2.printStackTrace();
    this.b.writeLock().unlock();
  }
  
  public static a a()
  {
    return b.a();
  }
  
  private boolean a(ApplicationInfo paramApplicationInfo, PackageManager paramPackageManager)
  {
    if ((paramApplicationInfo == null) || (paramPackageManager == null)) {}
    while ((TextUtils.isEmpty(paramApplicationInfo.packageName)) || (paramApplicationInfo.packageName.startsWith(com.ihs.app.framework.a.a().getPackageName())) || (paramApplicationInfo.packageName.equalsIgnoreCase("android")) || ((b.a(paramApplicationInfo)) && (paramApplicationInfo.packageName.startsWith("com.android")))) {
      return false;
    }
    return true;
  }
  
  private List<a> c()
  {
    this.b.readLock().lock();
    try
    {
      ArrayList localArrayList = new ArrayList(this.a);
      return localArrayList;
    }
    finally
    {
      this.b.readLock().unlock();
    }
  }
  
  public <T extends HSAppInfo> List<T> a(Class<T> paramClass, AppFilter paramAppFilter)
  {
    return a(paramClass, paramAppFilter, 0);
  }
  
  public <T extends HSAppInfo> List<T> a(Class<T> paramClass, AppFilter paramAppFilter, int paramInt)
  {
    long l = System.nanoTime();
    List localList = c();
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = localList.iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        break label298;
      }
      a localA = (a)localIterator.next();
      if (((paramInt & 0x1) == 0) || (a.b(localA))) {
        if (paramAppFilter != null)
        {
          if ((!b.a(a.a(localA), paramAppFilter.b())) && (((paramInt & 0x2) == 0) || (!a.c(localA)) || (b.a(a.a(localA), paramAppFilter.a()))) && (((paramInt & 0x4) == 0) || (!a.d(localA))) && (((paramInt & 0x8) == 0) || (!a.e(localA)))) {
            try
            {
              HSAppInfo localHSAppInfo = (HSAppInfo)paramClass.getConstructor(new Class[] { String.class }).newInstance(new Object[] { a.a(localA) });
              localHSAppInfo.b(a.f(localA));
              localHSAppInfo.a(a.g(localA));
              localHSAppInfo.a(a.h(localA));
              localHSAppInfo.d(a.b(localA));
              localHSAppInfo.a(a.c(localA));
              localHSAppInfo.b(a.d(localA));
              localHSAppInfo.e(a.e(localA));
              localHSAppInfo.b(a.i(localA));
              localArrayList.add(localHSAppInfo);
            }
            catch (Exception localException)
            {
              f.e("HSPackageManager-LWJlog", "error getConstructor");
            }
          }
        }
        else {
          if (((paramInt & 0x2) == 0) || (!a.c(localException))) {
            break;
          }
        }
      }
    }
    label298:
    f.c("HSPackageManager-LWJlog", "getInstalledAppList3 appInfoCache.size() = " + localList.size() + " filter with: " + (System.nanoTime() - l) / 1000L + "us");
    return localArrayList;
  }
  
  public List<HSAppInfo> b()
  {
    return a(HSAppInfo.class, null, 0);
  }
  
  private static class a
  {
    private final String a;
    private final String b;
    private final int c;
    private final String d;
    private final boolean e;
    private final boolean f;
    private final boolean g;
    private final boolean h;
    private final int i;
    
    a(ApplicationInfo paramApplicationInfo, PackageManager paramPackageManager)
    {
      this.a = paramApplicationInfo.packageName;
      paramPackageManager = paramPackageManager.getApplicationLabel(paramApplicationInfo);
      if (paramPackageManager == null) {}
      for (paramPackageManager = "";; paramPackageManager = paramPackageManager.toString().trim().replace(" ", ""))
      {
        this.b = paramPackageManager;
        this.c = paramApplicationInfo.flags;
        this.d = paramApplicationInfo.publicSourceDir;
        this.e = b.c(paramApplicationInfo.packageName);
        this.f = b.a(paramApplicationInfo);
        this.g = b.d(paramApplicationInfo.packageName);
        this.h = b.e(paramApplicationInfo.packageName);
        this.i = paramApplicationInfo.uid;
        return;
      }
    }
  }
  
  private static class b
  {
    private static final a a = new a(null);
  }
}
