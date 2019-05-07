package com.video.master.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Process;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class c
{
  public static long a(Context paramContext, PackageInfo paramPackageInfo)
  {
    if (paramPackageInfo != null) {
      return paramPackageInfo.firstInstallTime;
    }
    return 0L;
  }
  
  public static PackageInfo a(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static List<PackageInfo> a(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getInstalledPackages(0);
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
      paramContext = null;
    }
    Object localObject = paramContext;
    if (paramContext == null) {
      localObject = new ArrayList();
    }
    return localObject;
  }
  
  public static boolean a(Context paramContext, Intent paramIntent)
  {
    return paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536).size() > 0;
  }
  
  public static long b(Context paramContext, String paramString)
  {
    return a(paramContext, a(paramContext, paramString));
  }
  
  public static String b(Context paramContext)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    if (paramContext == null) {
      return null;
    }
    paramContext = paramContext.getRunningAppProcesses();
    if (paramContext == null) {
      return null;
    }
    int i = Process.myPid();
    paramContext = paramContext.iterator();
    while (paramContext.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      if ((localRunningAppProcessInfo != null) && (localRunningAppProcessInfo.pid == i)) {
        return localRunningAppProcessInfo.processName;
      }
    }
    return null;
  }
  
  public static String c(Context paramContext)
  {
    return f(paramContext, paramContext.getPackageName());
  }
  
  public static boolean c(Context paramContext, String paramString)
  {
    try
    {
      boolean bool = paramContext.getPackageManager().getApplicationInfo(paramString, 0).enabled;
      return true ^ bool;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return true;
  }
  
  public static int d(Context paramContext)
  {
    return g(paramContext, paramContext.getPackageName());
  }
  
  public static boolean d(Context paramContext, String paramString)
  {
    if (paramContext != null) {
      if (paramString == null) {
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
  
  public static void e(Context paramContext, String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    if (!(paramContext instanceof Activity)) {
      paramString.addFlags(268435456);
    }
    if (a(paramContext, paramString)) {
      paramContext.startActivity(paramString);
    }
  }
  
  private static String f(Context paramContext, String paramString)
  {
    if (paramString == null) {
      return null;
    }
    try
    {
      paramContext = paramContext.getApplicationContext().getPackageManager().getPackageInfo(paramString, 0);
      if (paramContext == null) {
        return null;
      }
      paramContext = paramContext.versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  private static int g(Context paramContext, String paramString)
  {
    if (paramString == null) {
      return -1;
    }
    try
    {
      paramContext = paramContext.getApplicationContext().getPackageManager().getPackageInfo(paramString, 0);
      if (paramContext == null) {
        return -1;
      }
      int i = paramContext.versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return -1;
  }
}
