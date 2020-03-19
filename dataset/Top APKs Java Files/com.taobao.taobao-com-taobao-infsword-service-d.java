package com.taobao.infsword.service;

import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.taobao.infsword.a.a;
import com.taobao.infsword.b.e;
import com.taobao.infsword.d.c;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class d
{
  public static final int a = 1;
  public static final int b = 2;
  private static d d;
  private Context c;
  
  private d(Context paramContext)
  {
    this.c = paramContext;
  }
  
  private a a(PackageManager paramPackageManager, ApplicationInfo paramApplicationInfo, int paramInt)
  {
    if (!paramApplicationInfo.sourceDir.startsWith("/system"))
    {
      Object localObject = new File(paramApplicationInfo.sourceDir);
      a localA = new a();
      localObject = a((File)localObject);
      if (!"".equals(localObject))
      {
        localA.c((String)localObject);
        if ((paramInt & 0x1) > 0)
        {
          String str = paramApplicationInfo.packageName;
          localObject = str;
          if (str == null) {
            localObject = "";
          }
          localA.e((String)localObject);
        }
        if ((paramInt & 0x2) > 0)
        {
          paramPackageManager = paramPackageManager.getApplicationLabel(paramApplicationInfo);
          if (paramPackageManager != null) {
            break label119;
          }
        }
        label119:
        for (paramPackageManager = "";; paramPackageManager = paramPackageManager.toString())
        {
          localA.d(paramPackageManager);
          return localA;
        }
      }
    }
    return null;
  }
  
  public static d a(Context paramContext)
  {
    if (d == null) {
      d = new d(paramContext);
    }
    return d;
  }
  
  private String a(File paramFile)
  {
    if ((paramFile.exists()) && (paramFile.length() <= 10485760L)) {
      try
      {
        String str = e.a(paramFile);
        if (str == null) {
          break label111;
        }
        return str;
      }
      catch (IOException localIOException)
      {
        c.a("AppScanner", "file@" + paramFile.getAbsolutePath() + " failed md5");
        c.a(localIOException);
        return "";
      }
    } else {
      if (!paramFile.exists()) {
        break label114;
      }
    }
    label111:
    label114:
    for (paramFile = "file#" + paramFile.getName() + "#exceeds max file size : " + paramFile.length();; paramFile = paramFile.getName() + " not exists!")
    {
      c.a("failed md5", paramFile);
      return "";
    }
  }
  
  public a a(String paramString, int paramInt)
  {
    c.a();
    PackageManager localPackageManager = this.c.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledApplications(0).iterator();
    ApplicationInfo localApplicationInfo;
    do
    {
      if (!localIterator.hasNext())
      {
        c.a("scanAddedApps");
        return null;
      }
      localApplicationInfo = (ApplicationInfo)localIterator.next();
    } while (!localApplicationInfo.packageName.equalsIgnoreCase(paramString));
    c.a("scanAddedApps");
    return a(localPackageManager, localApplicationInfo, paramInt);
  }
  
  public HashSet a()
  {
    c.a();
    HashSet localHashSet = new HashSet();
    for (;;)
    {
      try
      {
        localIterator = ((ActivityManager)this.c.getSystemService("activity")).getRecentTasks(1000, 1).iterator();
        boolean bool = localIterator.hasNext();
        if (bool) {
          continue;
        }
      }
      catch (Exception localException)
      {
        Iterator localIterator;
        Object localObject;
        c.a(localException);
        continue;
      }
      c.a("scanRecentUrls");
      return localHashSet;
      localObject = ((ActivityManager.RecentTaskInfo)localIterator.next()).baseIntent;
      if ((((Intent)localObject).getAction() != null) && (((Intent)localObject).getScheme() != null) && (((Intent)localObject).getAction().equalsIgnoreCase("android.intent.action.VIEW")) && ((((Intent)localObject).getScheme().equalsIgnoreCase("http")) || (((Intent)localObject).getScheme().equalsIgnoreCase("https"))))
      {
        localObject = ((Intent)localObject).getDataString();
        if (localObject != null) {
          localHashSet.add(localObject);
        }
      }
    }
  }
  
  public List a(int paramInt)
  {
    c.a();
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = this.c.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledApplications(0).iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        c.a("scanInstalledApps");
        return localArrayList;
      }
      a localA = a(localPackageManager, (ApplicationInfo)localIterator.next(), paramInt);
      if (localA != null) {
        localArrayList.add(localA);
      }
    }
  }
}
