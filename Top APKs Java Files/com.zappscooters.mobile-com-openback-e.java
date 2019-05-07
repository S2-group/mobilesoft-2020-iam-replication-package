package com.openback;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class e
{
  private static final Map<Integer, String> a = new AppUtils.1();
  
  static int a(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      int i = paramContext.getResources().getIdentifier(paramString1, paramString2, paramContext.getPackageName());
      return i;
    }
    catch (Exception paramContext)
    {
      z.a(paramContext);
    }
    return 0;
  }
  
  static String a(int paramInt)
  {
    if (a.containsKey(Integer.valueOf(paramInt))) {
      return (String)a.get(Integer.valueOf(paramInt));
    }
    return "Unknown android level";
  }
  
  @Nullable
  static String a(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationLabel(paramContext.getApplicationInfo()).toString();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      z.a(paramContext);
    }
    return null;
  }
  
  static void a(Context paramContext, String paramString)
  {
    try
    {
      if (StringUtils.a(paramString))
      {
        paramString = e(paramContext);
        if (paramString != null)
        {
          paramString.setFlags(805306368);
          paramString.putExtra("OPENBACK_TO_FRONT", true);
          paramContext.startActivity(paramString);
          return;
        }
        z.g("Unable to get launch intent");
        return;
      }
      Intent localIntent = new Intent();
      String str = b(paramContext);
      if (str != null)
      {
        localIntent.setComponent(new ComponentName(str, paramString));
        localIntent.setFlags(536870912);
        localIntent.putExtra("OPENBACK_TO_FRONT", true);
        paramContext.startActivity(localIntent);
        return;
      }
      z.g("Unable to get package name");
      return;
    }
    catch (Exception paramContext)
    {
      z.a(paramContext);
    }
  }
  
  static boolean a(Context paramContext, Class<?> paramClass)
  {
    try
    {
      paramClass = new ComponentName(paramContext, paramClass);
      paramContext.getPackageManager().getServiceInfo(paramClass, 128);
      return true;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return false;
  }
  
  @Nullable
  static String b(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getApplicationContext().getPackageName();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      z.a(paramContext);
    }
    return null;
  }
  
  static boolean b(Context paramContext, Class<?> paramClass)
  {
    try
    {
      paramClass = new ComponentName(paramContext, paramClass);
      paramContext.getPackageManager().getReceiverInfo(paramClass, 128);
      return true;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return false;
  }
  
  static String c(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(b(paramContext), 0);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramContext.versionName);
      localStringBuilder.append("-");
      localStringBuilder.append(paramContext.versionCode);
      paramContext = localStringBuilder.toString();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      z.a(paramContext);
    }
    return "1";
  }
  
  static int d(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(b(paramContext), 0).versionCode;
      return i;
    }
    catch (Exception paramContext)
    {
      z.a(paramContext);
    }
    return 0;
  }
  
  static Intent e(Context paramContext)
  {
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      paramContext = b(paramContext);
      if ((localPackageManager != null) && (paramContext != null))
      {
        paramContext = localPackageManager.getLaunchIntentForPackage(paramContext);
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      z.a(paramContext);
    }
    return null;
  }
  
  static String f(Context paramContext)
  {
    localStringBuilder = new StringBuilder();
    try
    {
      paramContext = paramContext.getPackageManager();
      Iterator localIterator = paramContext.getInstalledApplications(128).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        Object localObject = paramContext.getLaunchIntentForPackage(localApplicationInfo.packageName);
        if (localObject != null) {
          try
          {
            localObject = paramContext.getPackageInfo(localApplicationInfo.packageName, 0);
            localStringBuilder.append(localApplicationInfo.packageName);
            localStringBuilder.append(":");
            localStringBuilder.append(paramContext.getApplicationLabel(localApplicationInfo));
            localStringBuilder.append(":");
            localStringBuilder.append(((PackageInfo)localObject).versionName);
            localStringBuilder.append(":");
            localStringBuilder.append(((PackageInfo)localObject).versionCode);
            if ((localApplicationInfo.flags & 0x80) != 0) {
              localStringBuilder.append(":SYSTEM-UPDATED|");
            } else if ((localApplicationInfo.flags & 0x1) != 0) {
              localStringBuilder.append(":SYSTEM|");
            } else {
              localStringBuilder.append(":USER|");
            }
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            z.a(localNameNotFoundException);
          }
        }
      }
      return localStringBuilder.toString();
    }
    catch (Exception paramContext)
    {
      z.a(paramContext);
    }
  }
  
  static int g(Context paramContext)
  {
    try
    {
      paramContext = (ActivityManager)paramContext.getSystemService("activity");
      if (paramContext != null)
      {
        paramContext = paramContext.getRunningAppProcesses();
        if (paramContext != null)
        {
          int i = paramContext.size();
          return i;
        }
      }
    }
    catch (Exception paramContext)
    {
      z.a(paramContext);
    }
    return 0;
  }
  
  static int h(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getInstalledApplications(128).size();
      return i;
    }
    catch (Exception paramContext)
    {
      z.a(paramContext);
    }
    return 0;
  }
  
  static String i(Context paramContext)
  {
    localStringBuilder = new StringBuilder();
    try
    {
      paramContext = (ActivityManager)paramContext.getSystemService("activity");
      if (paramContext != null)
      {
        paramContext = paramContext.getRunningAppProcesses();
        if (paramContext != null)
        {
          paramContext = paramContext.iterator();
          while (paramContext.hasNext())
          {
            localStringBuilder.append(((ActivityManager.RunningAppProcessInfo)paramContext.next()).processName);
            localStringBuilder.append("|");
          }
        }
      }
      return localStringBuilder.toString();
    }
    catch (Exception paramContext)
    {
      z.a(paramContext);
    }
  }
}
