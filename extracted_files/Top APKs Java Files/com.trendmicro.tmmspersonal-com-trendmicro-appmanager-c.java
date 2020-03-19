package com.trendmicro.appmanager;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import com.trendmicro.appmanager.a.d;
import com.trendmicro.appmanager.a.d.a;
import com.trendmicro.appmanager.util.CacheUtil;
import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class c
{
  private static final String a = com.trendmicro.tmmssuite.util.i.a(c.class);
  private HashSet<String> b;
  private SoftReference<Context> c;
  private PackageManager d;
  private boolean e = false;
  private List<d> f;
  private a g = null;
  private CacheUtil h;
  
  public c(Context paramContext)
  {
    this.c = new SoftReference(paramContext);
    this.d = com.trendmicro.tmmssuite.core.util.i.a();
    this.f = new ArrayList();
    this.h = new CacheUtil((Context)this.c.get());
  }
  
  private long a(ApplicationInfo paramApplicationInfo)
  {
    try
    {
      long l = this.h.a(paramApplicationInfo.packageName);
      return l;
    }
    catch (Exception paramApplicationInfo) {}
    return 0L;
  }
  
  private String a(PackageInfo paramPackageInfo, d.a paramA)
  {
    int k = 0;
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramA == d.a.c) {}
    for (;;)
    {
      try
      {
        paramPackageInfo.requestedPermissions = this.d.getPackageInfo(paramPackageInfo.packageName, 4096).requestedPermissions;
        paramA = paramPackageInfo.requestedPermissions;
        if ((paramA != null) && (paramA.length > 0))
        {
          i = 0;
          if (i < paramA.length) {
            if (paramA[i].equals("android.permission.RECEIVE_BOOT_COMPLETED"))
            {
              i = 1;
              j = k;
              if (this.b != null)
              {
                j = k;
                if (this.b.contains(paramPackageInfo.packageName)) {
                  j = 1;
                }
              }
              if ((j == 0) || (i == 0)) {
                continue;
              }
              localStringBuilder.append(((Context)this.c.get()).getString(2131296920));
              return localStringBuilder.toString();
            }
          }
        }
      }
      catch (Exception paramA)
      {
        int j;
        paramA.printStackTrace();
        continue;
        i += 1;
        continue;
        if (j != 0)
        {
          localStringBuilder.append(((Context)this.c.get()).getString(2131296919));
          continue;
        }
        if (i == 0) {
          continue;
        }
        localStringBuilder.append(((Context)this.c.get()).getString(2131296918));
        continue;
      }
      int i = 0;
    }
  }
  
  private boolean b(PackageInfo paramPackageInfo)
  {
    boolean bool1 = true;
    try
    {
      if (Build.VERSION.SDK_INT > 7)
      {
        if ((paramPackageInfo.applicationInfo.flags & 0x40000) != 262144) {
          break label62;
        }
        return true;
      }
      paramPackageInfo = paramPackageInfo.applicationInfo.sourceDir;
      if (!paramPackageInfo.startsWith("/data/app"))
      {
        boolean bool2 = paramPackageInfo.startsWith("/system/app");
        if (!bool2) {}
      }
      else
      {
        return false;
      }
    }
    catch (Exception paramPackageInfo)
    {
      bool1 = false;
    }
    return bool1;
    label62:
    return false;
  }
  
  private d.a c(PackageInfo paramPackageInfo)
  {
    if (Build.VERSION.SDK_INT < 8) {
      return d.a.a;
    }
    for (;;)
    {
      try
      {
        if ((paramPackageInfo.applicationInfo.flags & 0x1) > 0)
        {
          paramPackageInfo = d.a.b;
          return paramPackageInfo;
        }
        int i;
        try
        {
          Field localField1 = PackageInfo.class.getField("installLocation");
          if (localField1 != null)
          {
            i = 0;
            try
            {
              j = localField1.getInt(paramPackageInfo);
              i = j;
            }
            catch (Exception localException4)
            {
              try
              {
                Field localField2;
                Object localObject;
                int j = localObject.getInt(paramPackageInfo.applicationInfo);
                i = j;
              }
              catch (Exception localException3) {}
              paramPackageInfo = d.a.c;
              return paramPackageInfo;
            }
            com.trendmicro.tmmssuite.core.sys.c.a(a, paramPackageInfo.packageName + " installLocation field: " + i);
            if ((i != 1) && (i != -1)) {
              if (b(paramPackageInfo))
              {
                paramPackageInfo = d.a.d;
                return paramPackageInfo;
              }
            }
          }
        }
        catch (Exception localException1)
        {
          try
          {
            localField2 = ApplicationInfo.class.getField("installLocation");
          }
          catch (Exception localException2)
          {
            localObject = null;
          }
          continue;
        }
        return d.a.a;
      }
      catch (Exception paramPackageInfo) {}
    }
  }
  
  private HashSet<String> d()
  {
    HashSet localHashSet = new HashSet();
    List localList = AppWidgetManager.getInstance((Context)this.c.get()).getInstalledProviders();
    int j = localList.size();
    int i = 0;
    while (i < j)
    {
      localHashSet.add(((AppWidgetProviderInfo)localList.get(i)).provider.getPackageName());
      i += 1;
    }
    return localHashSet;
  }
  
  public d.a a(String paramString)
  {
    localObject = d.a.a;
    try
    {
      d.a localA = c(this.d.getPackageInfo(paramString, 0));
      localObject = localA;
      com.trendmicro.tmmssuite.core.sys.c.b(a, ((Exception)localObject).getMessage());
    }
    catch (Exception localException1)
    {
      try
      {
        com.trendmicro.tmmssuite.core.sys.c.a(a, paramString + " is installed on " + localObject);
        return localObject;
      }
      catch (Exception localException2)
      {
        for (;;)
        {
          paramString = (String)localObject;
          localObject = localException2;
        }
      }
      localException1 = localException1;
      paramString = (String)localObject;
      localObject = localException1;
    }
    return paramString;
  }
  
  public d a(PackageInfo paramPackageInfo)
  {
    if (com.trendmicro.tmmssuite.core.util.i.a(paramPackageInfo.applicationInfo)) {}
    d.a localA;
    do
    {
      return null;
      localA = a(paramPackageInfo.packageName);
      com.trendmicro.tmmssuite.core.sys.c.c(a, paramPackageInfo.packageName + ", installed in " + localA);
    } while ((localA != d.a.c) && (localA != d.a.d));
    d localD = new d();
    ApplicationInfo localApplicationInfo = paramPackageInfo.applicationInfo;
    localD.b(localApplicationInfo.packageName);
    localD.a((String)localApplicationInfo.loadLabel(this.d));
    localD.a(localApplicationInfo.loadIcon(this.d));
    localD.b(com.trendmicro.tmmssuite.core.util.i.a(paramPackageInfo));
    localD.a(a(localApplicationInfo));
    localD.a(localA);
    localD.d(a(paramPackageInfo, localA));
    localD.c(paramPackageInfo.versionName);
    com.trendmicro.tmmssuite.core.sys.c.a(a, localD.toString());
    return localD;
  }
  
  public void a()
  {
    this.e = true;
    this.f.clear();
    new Thread(new Runnable()
    {
      public void run()
      {
        Object localObject1 = com.trendmicro.tmmssuite.core.util.i.a(false);
        c.a(c.this, c.a(c.this));
        if ((localObject1 != null) && (((List)localObject1).size() > 0))
        {
          localObject1 = ((List)localObject1).iterator();
          if (!((Iterator)localObject1).hasNext()) {
            break label153;
          }
          localObject2 = (PackageInfo)((Iterator)localObject1).next();
          if (!c.b(c.this)) {
            if (c.c(c.this) != null) {
              c.c(c.this).a();
            }
          }
        }
        label153:
        while (c.c(c.this) == null)
        {
          return;
          Object localObject2 = c.this.a((PackageInfo)localObject2);
          if (localObject2 == null) {
            break;
          }
          c.d(c.this).add(localObject2);
          if (c.c(c.this) == null) {
            break;
          }
          c.c(c.this).a((d)localObject2);
          break;
          com.trendmicro.tmmssuite.core.sys.c.c(c.c(), "getInstalledPackages is empty");
        }
        c.c(c.this).a();
      }
    }).start();
  }
  
  public void a(a paramA)
  {
    this.g = paramA;
  }
  
  public void b()
  {
    this.e = false;
  }
  
  public static abstract interface a
  {
    public abstract void a();
    
    public abstract void a(d paramD);
  }
}
