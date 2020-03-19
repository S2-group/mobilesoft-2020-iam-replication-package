package com.jiubang.commerce.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Process;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class b
{
  private static long a = 0L;
  private static String b = "";
  
  public static String a(Context paramContext)
  {
    try
    {
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1);
      if (paramContext.size() > 0)
      {
        paramContext = ((ActivityManager.RunningTaskInfo)paramContext.get(0)).topActivity.getPackageName();
        return paramContext;
      }
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString))) {
      return false;
    }
    try
    {
      paramContext.getPackageManager().getPackageInfo(paramString, 1024);
      return true;
    }
    catch (Exception paramContext)
    {
      return false;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static int b(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null)) {
      return -1;
    }
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramString, 1024).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      i.e("Ad_SDK", "Error :" + paramString + " is not exist.");
      return -1;
    }
    catch (Exception paramContext) {}
    return -1;
  }
  
  public static List<PackageInfo> b(Context paramContext)
  {
    return paramContext.getPackageManager().getInstalledPackages(0);
  }
  
  public static String c(Context paramContext)
  {
    if (paramContext == null) {
      paramContext = null;
    }
    for (;;)
    {
      return paramContext;
      try
      {
        long l = System.currentTimeMillis();
        if ((!b.equals("")) && (a != 0L) && (l - a <= 14400000L))
        {
          paramContext = b;
          continue;
        }
        String str = "";
        List localList = paramContext.getPackageManager().getInstalledPackages(0);
        Object localObject = str;
        int j;
        if (localList != null)
        {
          j = 0;
          int i = 0;
          paramContext = str;
          localObject = paramContext;
          if (j < localList.size())
          {
            localObject = (PackageInfo)localList.get(j);
            if ((((PackageInfo)localObject).applicationInfo.flags & 0x1) != 0) {
              break;
            }
            if (i.a) {
              i.b("", j + "   :   " + ((PackageInfo)localObject).packageName);
            }
            localObject = paramContext + ((PackageInfo)localObject).packageName + ",";
            i += 1;
            if (i < 50) {
              break label215;
            }
          }
        }
        b = (String)localObject;
        paramContext = b;
        continue;
        label215:
        paramContext = (Context)localObject;
        for (;;)
        {
          j += 1;
          break;
        }
      }
      finally {}
    }
  }
  
  public static String c(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null)) {
      return "";
    }
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 1024).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      i.e("Ad_SDK", "Error :" + paramString + " is not exist.");
      return "";
    }
    catch (Exception paramContext) {}
    return "";
  }
  
  public static String d(Context paramContext)
  {
    if (paramContext != null)
    {
      int i = Process.myPid();
      paramContext = (ActivityManager)paramContext.getSystemService("activity");
      try
      {
        paramContext = paramContext.getRunningAppProcesses().iterator();
        while (paramContext.hasNext())
        {
          ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
          if (localRunningAppProcessInfo.pid == i)
          {
            paramContext = localRunningAppProcessInfo.processName;
            return paramContext;
          }
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return null;
  }
  
  public static String d(Context paramContext, String paramString)
  {
    if (paramString == null) {}
    for (;;)
    {
      return null;
      try
      {
        paramContext = paramContext.getPackageManager();
        paramString = paramContext.getApplicationInfo(paramString, 0);
        if (paramString != null)
        {
          paramContext = paramContext.getApplicationLabel(paramString).toString();
          return paramContext;
        }
      }
      catch (Exception paramContext) {}
    }
    return null;
  }
  
  public static boolean e(Context paramContext, String paramString)
  {
    if (o.e) {
      return i(paramContext, paramString);
    }
    return h(paramContext, paramString);
  }
  
  public static PackageInfo f(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static boolean g(Context paramContext, String paramString)
  {
    if (paramContext != null) {}
    try
    {
      if (!TextUtils.isEmpty(paramString))
      {
        Intent localIntent2 = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
        Intent localIntent1 = localIntent2;
        if (localIntent2 == null)
        {
          PackageInfo localPackageInfo = f(paramContext, paramString);
          localIntent1 = localIntent2;
          if (localPackageInfo != null)
          {
            localIntent1 = localIntent2;
            if (!TextUtils.isEmpty(localPackageInfo.applicationInfo.className))
            {
              localIntent1 = new Intent();
              localIntent1.addFlags(268435456);
              localIntent1.setClassName(paramString, localPackageInfo.applicationInfo.className);
            }
          }
        }
        if (localIntent1 != null)
        {
          paramContext.startActivity(localIntent1);
          return true;
        }
      }
    }
    catch (Exception paramContext)
    {
      return false;
    }
    catch (ActivityNotFoundException paramContext)
    {
      for (;;) {}
    }
  }
  
  private static boolean h(Context paramContext, String paramString)
  {
    try
    {
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1);
      if (paramContext.size() > 0)
      {
        boolean bool = paramString.equals(((ActivityManager.RunningTaskInfo)paramContext.get(0)).topActivity.getPackageName());
        if (bool) {
          return true;
        }
      }
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  private static boolean i(Context paramContext, String paramString)
  {
    try
    {
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
      if (paramContext == null) {
        return false;
      }
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        if (localRunningAppProcessInfo.importance == 100) {
          if (!localRunningAppProcessInfo.processName.equals(paramString))
          {
            boolean bool = Arrays.asList(localRunningAppProcessInfo.pkgList).contains(paramString);
            if (!bool) {
              break;
            }
          }
          else
          {
            return true;
          }
        }
      }
    }
    catch (Exception paramContext) {}
    return false;
  }
}
