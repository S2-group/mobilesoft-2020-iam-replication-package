package com.piriform.ccleaner.core.d;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import com.piriform.ccleaner.core.j;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public final class u
{
  public static PackageInfo a(PackageManager paramPackageManager, String paramString)
  {
    paramPackageManager = paramPackageManager.getPackageArchiveInfo(paramString, 0);
    if (paramPackageManager != null)
    {
      paramPackageManager.applicationInfo.sourceDir = paramString;
      paramPackageManager.applicationInfo.publicSourceDir = paramString;
    }
    return paramPackageManager;
  }
  
  private static com.piriform.ccleaner.core.a.a a(Context paramContext, PackageInfo paramPackageInfo)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramPackageInfo.applicationInfo != null)
    {
      if (paramContext.getPackageManager() != null) {
        break label25;
      }
      localObject1 = localObject2;
    }
    label25:
    int i;
    do
    {
      do
      {
        return localObject1;
        paramContext = paramContext.getPackageManager();
        paramContext = paramPackageInfo.applicationInfo.loadLabel(paramContext);
        localObject1 = localObject2;
      } while (paramContext == null);
      localObject1 = paramContext.toString();
      paramContext = new com.piriform.ccleaner.core.a.a();
      paramContext.a = ((String)localObject1);
      paramContext.b = paramPackageInfo.packageName;
      paramContext.c = paramPackageInfo.versionName;
      paramContext.d = paramPackageInfo.versionCode;
      paramContext.p = paramPackageInfo.applicationInfo;
      if (Build.VERSION.SDK_INT > 8) {
        paramContext.h = paramPackageInfo.firstInstallTime;
      }
      i = paramPackageInfo.applicationInfo.flags & 0x1;
      if (i == 1) {
        break;
      }
      localObject1 = paramContext;
    } while (i != 128);
    paramContext.i = true;
    return paramContext;
  }
  
  public static com.piriform.ccleaner.core.a.a a(Context paramContext, String paramString)
  {
    paramString = c(paramContext, paramString);
    CountDownLatch localCountDownLatch;
    if (paramString != null)
    {
      paramString = a(paramContext, paramString);
      localCountDownLatch = new CountDownLatch(1);
      a(paramContext, paramString, localCountDownLatch, null);
    }
    try
    {
      boolean bool = localCountDownLatch.await(1L, TimeUnit.SECONDS);
      if (!bool) {
        return null;
      }
    }
    catch (InterruptedException paramContext)
    {
      throw new InterruptedException();
    }
    return paramString;
  }
  
  private static void a(Context paramContext, com.piriform.ccleaner.core.a.a paramA, CountDownLatch paramCountDownLatch, j paramJ)
  {
    paramContext = paramContext.getPackageManager();
    if (paramContext != null) {}
    try
    {
      Method localMethod = paramContext.getClass().getMethod("getPackageSizeInfo", new Class[] { String.class, IPackageStatsObserver.class });
      localMethod.setAccessible(true);
      localMethod.invoke(paramContext, new Object[] { paramA.b, new w(paramA, paramCountDownLatch, paramJ) });
      return;
    }
    catch (IllegalAccessException paramContext)
    {
      throw new com.piriform.ccleaner.core.w(paramContext);
    }
    catch (IllegalArgumentException paramContext)
    {
      throw new com.piriform.ccleaner.core.w(paramContext);
    }
    catch (NoSuchMethodException paramContext)
    {
      throw new com.piriform.ccleaner.core.w(paramContext);
    }
    catch (InvocationTargetException paramContext)
    {
      throw new com.piriform.ccleaner.core.w(paramContext);
    }
  }
  
  public static void a(Context paramContext, boolean paramBoolean, j paramJ)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = paramContext.getPackageManager();
    if (localObject1 != null)
    {
      localObject1 = ((PackageManager)localObject1).getInstalledApplications(0).iterator();
      Object localObject3;
      label148:
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ApplicationInfo)((Iterator)localObject1).next();
        localObject3 = b(paramContext, ((ApplicationInfo)localObject2).packageName);
        if (localObject3 != null)
        {
          if ((((ApplicationInfo)localObject2).flags & 0x1) == 1) {}
          for (int i = 1;; i = 0)
          {
            if (((i != 0) && (!paramBoolean)) || ("com.piriform.ccleaner".equals(((ApplicationInfo)localObject2).packageName))) {
              break label148;
            }
            localObject2 = a(paramContext, (PackageInfo)localObject3);
            localArrayList.add(localObject2);
            paramJ.b((com.piriform.ccleaner.core.a.a)localObject2);
            if (!Thread.interrupted()) {
              break;
            }
            throw new InterruptedException();
          }
        }
      }
      localObject1 = new CountDownLatch(localArrayList.size());
      Object localObject2 = localArrayList.iterator();
      while (((Iterator)localObject2).hasNext())
      {
        localObject3 = (com.piriform.ccleaner.core.a.a)((Iterator)localObject2).next();
        if (Thread.interrupted()) {
          throw new InterruptedException();
        }
        a(paramContext, (com.piriform.ccleaner.core.a.a)localObject3, (CountDownLatch)localObject1, paramJ);
      }
    }
    try
    {
      ((CountDownLatch)localObject1).await(localArrayList.size() * 1, TimeUnit.SECONDS);
      return;
    }
    catch (InterruptedException paramContext)
    {
      throw new InterruptedException();
    }
  }
  
  private static PackageInfo b(Context paramContext, String paramString)
  {
    Object localObject = null;
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = localObject;
    if (localPackageManager != null) {}
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramString, 1);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      com.b.a.a.a.a.a("Package info not found for: " + paramString, paramContext);
    }
    return null;
  }
  
  public static PackageInfo b(PackageManager paramPackageManager, String paramString)
  {
    try
    {
      paramPackageManager = paramPackageManager.getPackageInfo(paramString, 1);
      return paramPackageManager;
    }
    catch (PackageManager.NameNotFoundException paramPackageManager) {}
    return null;
  }
  
  private static PackageInfo c(Context paramContext, String paramString)
  {
    Object localObject = null;
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = localObject;
    if (localPackageManager != null) {}
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramString, 128);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
}
