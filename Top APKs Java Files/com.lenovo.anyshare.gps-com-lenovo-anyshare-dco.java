package com.lenovo.anyshare;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

public final class dco
{
  private static List<String> a = new ArrayList();
  private static cyn b = new cyn(new ArrayList(), true, 1000L);
  private static StringBuilder c = new StringBuilder();
  private static boolean d = false;
  private static long e = 0L;
  
  public static int a(Context paramContext, String paramString)
  {
    a.add(paramString);
    int i = ddb.a(paramContext, paramString);
    a.remove(paramString);
    return i;
  }
  
  public static int a(Context paramContext, String paramString, int paramInt)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramString, 0).versionCode;
      if (i >= paramInt) {
        return 1;
      }
      return 2;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return 0;
  }
  
  public static List<String> a(Context paramContext)
  {
    Object localObject = AppWidgetManager.getInstance(paramContext).getInstalledProviders();
    paramContext = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      paramContext.add(((AppWidgetProviderInfo)((Iterator)localObject).next()).provider.getPackageName());
    }
    return paramContext;
  }
  
  public static List<PackageInfo> a(Context paramContext, int paramInt, String paramString)
  {
    long l1 = 0L;
    for (;;)
    {
      try
      {
        l2 = e;
        e = System.currentTimeMillis();
        if (l2 != 0L) {
          continue;
        }
        c.append(paramString + "-" + l1 + " ");
        if ((paramInt == 0) && (!b.c())) {
          continue;
        }
        paramString = paramContext.getPackageManager().getInstalledPackages(paramInt);
        paramContext = paramString;
        if (paramInt == 0)
        {
          paramContext = paramString;
          if (paramString != null)
          {
            b.a(paramString);
            paramContext = paramString;
          }
        }
      }
      catch (Throwable paramContext)
      {
        long l2;
        b(paramContext.getMessage());
        c = new StringBuilder();
        if (paramInt != 0) {
          continue;
        }
        paramContext = (List)b.g();
        continue;
        paramContext = new ArrayList();
        continue;
      }
      finally {}
      return paramContext;
      l1 = (e - l2) / 1000L;
      continue;
      paramContext = (List)b.g();
    }
  }
  
  public static boolean a()
  {
    return ddb.a() > 0;
  }
  
  public static boolean a(String paramString)
  {
    Object localObject = cye.a(paramString.replace(".apk", ".odex"));
    if ((localObject != null) && (((cye)localObject).c())) {
      return true;
    }
    localObject = new String[4];
    localObject[0] = "/arm/";
    localObject[1] = "/arm64/";
    localObject[2] = "/x86/";
    localObject[3] = "/x86_64/";
    cye localCye1 = cye.a(paramString);
    cye localCye2 = localCye1.g();
    if (!localCye2.h().contains(cxz.c(paramString))) {
      return false;
    }
    if ((localCye2 != null) && (localCye2.c()))
    {
      int j = localObject.length;
      int i = 0;
      for (;;)
      {
        if (i >= j) {
          break label193;
        }
        paramString = localObject[i];
        paramString = cye.a(localCye2.h() + paramString + localCye1.i().replace(".apk", ".odex"));
        cxk.b("PackageUtils", "odex path = " + paramString.h());
        if ((paramString != null) && (paramString.c())) {
          break;
        }
        i += 1;
      }
    }
    label193:
    return false;
  }
  
  public static int b(Context paramContext)
  {
    int j = 1;
    int k = 0;
    try
    {
      cxi.a(paramContext);
      String str = paramContext.getPackageName();
      Object localObject1 = (ActivityManager)paramContext.getSystemService("activity");
      int i;
      if (Build.VERSION.SDK_INT <= 19)
      {
        paramContext = ((ActivityManager)localObject1).getRunningTasks(1);
        if ((paramContext != null) && (!paramContext.isEmpty()))
        {
          bool = str.equalsIgnoreCase(((ActivityManager.RunningTaskInfo)paramContext.get(0)).topActivity.getPackageName());
          if (bool) {
            i = 1;
          }
        }
      }
      do
      {
        do
        {
          for (;;)
          {
            return i;
            i = 0;
          }
          try
          {
            paramContext = ActivityManager.RunningAppProcessInfo.class.getDeclaredField("processState");
            if (paramContext == null) {
              return -1;
            }
          }
          catch (Exception paramContext)
          {
            for (;;)
            {
              cxk.c("PackageUtils", "getField processState exception", paramContext);
              paramContext = null;
            }
            localObject1 = ((ActivityManager)localObject1).getRunningAppProcesses();
            i = k;
          }
        } while (localObject1 == null);
        i = k;
      } while (((List)localObject1).isEmpty());
      Iterator localIterator = ((List)localObject1).iterator();
      do
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
        do
        {
          i = k;
          if (!localIterator.hasNext()) {
            break;
          }
          localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
          i = localRunningAppProcessInfo.importance;
        } while (i != 100);
        try
        {
          i = paramContext.getInt(localRunningAppProcessInfo);
          localObject1 = Integer.valueOf(i);
        }
        catch (Exception localException)
        {
          for (;;)
          {
            Object localObject2 = null;
            continue;
            i = 0;
          }
        }
      } while ((localObject1 == null) || (((Integer)localObject1).intValue() != 2));
      boolean bool = TextUtils.equals(localRunningAppProcessInfo.processName, str);
      if (bool)
      {
        i = j;
        return i;
      }
      return -1;
    }
    catch (Exception paramContext)
    {
      cxk.c("PackageUtils", "getAppRunningStatus failed!", paramContext);
    }
  }
  
  public static void b(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.addFlags(268435456);
    localIntent.setDataAndType(Uri.parse("file://" + paramString), "application/vnd.android.package-archive");
    paramContext.startActivity(localIntent);
  }
  
  private static void b(String paramString)
  {
    try
    {
      LinkedHashMap localLinkedHashMap = new LinkedHashMap();
      localLinkedHashMap.put("error", paramString);
      if (!d)
      {
        localLinkedHashMap.put("history", c.toString().trim());
        d = true;
      }
      for (;;)
      {
        cjz.b(cys.a(), "ERR_AboutPackageManager", localLinkedHashMap);
        return;
        localLinkedHashMap.put("history", null);
      }
      return;
    }
    catch (Throwable paramString) {}
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    if (!d(paramContext, paramString)) {}
    while (!ddb.b(paramContext, paramString)) {
      return false;
    }
    return d(paramContext, paramString);
  }
  
  public static boolean d(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getPackageManager().getApplicationInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
}
