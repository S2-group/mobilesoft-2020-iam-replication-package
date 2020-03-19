package com.cs.bd.e;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.cs.bd.commerce.util.f;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class c
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
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    if (paramContext != null) {
      if (TextUtils.isEmpty(paramString)) {
        return false;
      }
    }
    try
    {
      paramContext.getPackageManager().getPackageInfo(paramString, 1024);
      return true;
    }
    catch (PackageManager.NameNotFoundException|Exception paramContext) {}
    return false;
    return false;
  }
  
  public static int b(Context paramContext, String paramString)
  {
    if (paramContext != null) {
      if (paramString == null) {
        return -1;
      }
    }
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramString, 1024).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    paramContext = new StringBuilder();
    paramContext.append("Error :");
    paramContext.append(paramString);
    paramContext.append(" is not exist.");
    f.d("Ad_SDK", paramContext.toString());
    return -1;
    return -1;
  }
  
  public static List<PackageInfo> b(Context paramContext)
  {
    return paramContext.getPackageManager().getInstalledPackages(0);
  }
  
  public static long[] c(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getPackageInfo(paramString, 0);
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      paramContext = null;
    }
    if (paramContext == null) {
      return null;
    }
    return new long[] { paramContext.firstInstallTime, paramContext.lastUpdateTime };
  }
  
  public static String d(Context paramContext, String paramString)
  {
    if (paramContext != null) {
      if (paramString == null) {
        return "";
      }
    }
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 1024).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    paramContext = new StringBuilder();
    paramContext.append("Error :");
    paramContext.append(paramString);
    paramContext.append(" is not exist.");
    f.d("Ad_SDK", paramContext.toString());
    return "";
    return "";
  }
  
  public static String e(Context paramContext, String paramString)
  {
    Object localObject = null;
    if (paramString == null) {
      return null;
    }
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      paramString = localPackageManager.getApplicationInfo(paramString, 0);
      paramContext = localObject;
      if (paramString != null) {
        paramContext = localPackageManager.getApplicationLabel(paramString).toString();
      }
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static boolean f(Context paramContext, String paramString)
  {
    if (t.e) {
      return h(paramContext, paramString);
    }
    return g(paramContext, paramString);
  }
  
  private static boolean g(Context paramContext, String paramString)
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
      return false;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  private static boolean h(Context paramContext, String paramString)
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
      return false;
    }
    catch (Exception paramContext) {}
    return false;
  }
}
