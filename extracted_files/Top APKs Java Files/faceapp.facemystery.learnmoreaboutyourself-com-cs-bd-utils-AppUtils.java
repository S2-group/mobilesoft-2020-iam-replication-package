package com.cs.bd.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.cs.bd.commerce.util.f;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class AppUtils
{
  public static final int MAX_INSTALL_APP = 50;
  public static final long OBTAIN_TIME = 14400000L;
  private static String sInstallInfoStr = "";
  private static long sObtainTime;
  
  public AppUtils() {}
  
  public static List<PackageInfo> getAllInstalledApps(Context paramContext)
  {
    return paramContext.getPackageManager().getInstalledPackages(0);
  }
  
  public static Drawable getApkIcon(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    Object localObject = paramContext.getPackageArchiveInfo(paramString, 1);
    if (localObject != null)
    {
      localObject = ((PackageInfo)localObject).applicationInfo;
      ((ApplicationInfo)localObject).sourceDir = paramString;
      ((ApplicationInfo)localObject).publicSourceDir = paramString;
    }
    try
    {
      paramContext = ((ApplicationInfo)localObject).loadIcon(paramContext);
      return paramContext;
    }
    catch (OutOfMemoryError paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static String getAppLabel(Context paramContext, String paramString)
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
  
  public static PackageInfo getAppPackageInfo(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static int getAppVersionCode(Context paramContext, String paramString)
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
  
  public static String getAppVersionName(Context paramContext, String paramString)
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
  
  public static String getCurrProcessName(Context paramContext)
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
  
  public static String getDefaultLauncher(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 65536);
    if (paramContext.activityInfo == null) {
      return null;
    }
    if (paramContext.activityInfo.packageName.equals("android")) {
      return null;
    }
    return paramContext.activityInfo.packageName;
  }
  
  public static String getInstallAppInfoWithoutSys(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    for (;;)
    {
      int j;
      Object localObject1;
      int i;
      try
      {
        long l = System.currentTimeMillis();
        if ((!sInstallInfoStr.equals("")) && (sObtainTime != 0L) && (l - sObtainTime <= 14400000L))
        {
          paramContext = sInstallInfoStr;
          return paramContext;
        }
        Object localObject2 = "";
        try
        {
          paramContext = paramContext.getPackageManager();
          j = 0;
          List localList = paramContext.getInstalledPackages(0);
          localObject1 = localObject2;
          if (localList != null)
          {
            k = 0;
            paramContext = (Context)localObject2;
            localObject1 = paramContext;
            if (j < localList.size())
            {
              localObject2 = (PackageInfo)localList.get(j);
              localObject1 = paramContext;
              i = k;
              if ((((PackageInfo)localObject2).applicationInfo.flags & 0x1) != 0) {
                break label280;
              }
              if (f.b())
              {
                localObject1 = new StringBuilder();
                ((StringBuilder)localObject1).append(j);
                ((StringBuilder)localObject1).append("   :   ");
                ((StringBuilder)localObject1).append(((PackageInfo)localObject2).packageName);
                f.a("", ((StringBuilder)localObject1).toString());
              }
              localObject1 = new StringBuilder();
              ((StringBuilder)localObject1).append(paramContext);
              ((StringBuilder)localObject1).append(((PackageInfo)localObject2).packageName);
              ((StringBuilder)localObject1).append(",");
              paramContext = ((StringBuilder)localObject1).toString();
              k += 1;
              localObject1 = paramContext;
              i = k;
              if (k < 50) {
                break label280;
              }
              localObject1 = paramContext;
            }
          }
          sInstallInfoStr = (String)localObject1;
        }
        catch (Throwable paramContext)
        {
          f.a("Ad_SDK", "AppUtils.getInstallAppInfoWithoutSys error", paramContext);
        }
        paramContext = sInstallInfoStr;
        return paramContext;
      }
      finally {}
      label280:
      j += 1;
      paramContext = (Context)localObject1;
      int k = i;
    }
  }
  
  public static List<PackageInfo> getInstalledApps(Context paramContext)
  {
    Object localObject1 = paramContext.getPackageManager();
    paramContext = paramContext.getPackageName();
    Object localObject2 = ((PackageManager)localObject1).getInstalledPackages(0);
    localObject1 = new ArrayList();
    localObject2 = ((List)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject2).next();
      if (((localPackageInfo.applicationInfo.flags & 0x1) == 0) && (!paramContext.equals(localPackageInfo.packageName))) {
        ((List)localObject1).add(localPackageInfo);
      }
    }
    return localObject1;
  }
  
  public static List<ResolveInfo> getLauncherApps(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.LAUNCHER");
    return paramContext.queryIntentActivities(localIntent, 0);
  }
  
  public static List<PackageInfo> getLauncherableApps(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    paramContext = paramContext.getPackageName();
    Object localObject = localPackageManager.getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((localPackageManager.getLaunchIntentForPackage(localPackageInfo.applicationInfo.packageName) != null) && (!paramContext.equals(localPackageInfo.packageName))) {
        localArrayList.add(localPackageInfo);
      }
    }
    return localArrayList;
  }
  
  public static String getPackageName(Intent paramIntent)
  {
    if (paramIntent != null)
    {
      paramIntent = paramIntent.getComponent();
      if (paramIntent != null) {
        return paramIntent.getPackageName();
      }
    }
    return null;
  }
  
  public static int getPidByProcessName(Context paramContext, String paramString)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    while (paramContext.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
      if (localRunningAppProcessInfo.processName.equals(paramString)) {
        return localRunningAppProcessInfo.pid;
      }
    }
    return 0;
  }
  
  public static long[] getPkgTimeInfo(Context paramContext, String paramString)
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
  
  public static String getSelfTopActivityName(Context paramContext)
  {
    try
    {
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1);
      if (paramContext.size() > 0)
      {
        paramContext = ((ActivityManager.RunningTaskInfo)paramContext.get(0)).topActivity.getClassName();
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public static String getTopAppPackageName(Context paramContext)
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
  
  public static void installApk(Context paramContext, File paramFile)
  {
    if (paramFile.exists())
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setFlags(268435456);
      localIntent.setDataAndType(Uri.fromFile(paramFile), "application/vnd.android.package-archive");
      ((ContextWrapper)paramContext).startActivity(localIntent);
      return;
    }
    ToastUtils.makeEventToast(paramContext, "Can not find APK!", false);
  }
  
  public static boolean isAppExist(Context paramContext, String paramString)
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
  
  public static boolean isAppRunningInForground(Context paramContext, String paramString)
  {
    if (SystemUtils.IS_SDK_ABOVE_L) {
      return isForgroundApp(paramContext, paramString);
    }
    return isTopActivity(paramContext, paramString);
  }
  
  private static boolean isForgroundApp(Context paramContext, String paramString)
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
  
  private static boolean isTopActivity(Context paramContext, String paramString)
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
  
  public static void killProcess()
  {
    killProcess(Process.myPid());
  }
  
  public static void killProcess(int paramInt)
  {
    new Exception().printStackTrace();
    Process.killProcess(paramInt);
  }
  
  public static boolean safeStartActivity(Context paramContext, String paramString)
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
          PackageInfo localPackageInfo = getAppPackageInfo(paramContext, paramString);
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
    catch (ActivityNotFoundException|Exception paramContext)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static void uninstallApk(Context paramContext, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("package:");
    localStringBuilder.append(paramString);
    paramString = new Intent("android.intent.action.DELETE", Uri.parse(localStringBuilder.toString()));
    if (!"Xiaomi".equals(Build.BRAND)) {
      paramString.setFlags(1073741824);
    }
    paramContext.startActivity(paramString);
  }
}
