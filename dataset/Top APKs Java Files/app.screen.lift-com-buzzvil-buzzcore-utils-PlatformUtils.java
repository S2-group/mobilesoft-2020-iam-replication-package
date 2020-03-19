package com.buzzvil.buzzcore.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.util.Base64;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class PlatformUtils
{
  public PlatformUtils() {}
  
  public static List<String> getAllBrowserPackageNames(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.buzzvil.com"));
    int i;
    if (Build.VERSION.SDK_INT >= 23) {
      i = 196608;
    } else {
      i = 65536;
    }
    paramContext = paramContext.getPackageManager().queryIntentActivities(localIntent, i).iterator();
    while (paramContext.hasNext()) {
      localArrayList.add(((ResolveInfo)paramContext.next()).activityInfo.packageName);
    }
    return localArrayList;
  }
  
  public static String getDefaultBrowserPackageName(Context paramContext)
  {
    paramContext = new Intent("android.intent.action.VIEW", Uri.parse("https://www.buzzvil.com")).resolveActivity(paramContext.getPackageManager());
    if (paramContext == null) {
      return "";
    }
    return paramContext.getPackageName();
  }
  
  public static Set<String> getRunningServicePackageSet(Context paramContext, String paramString)
  {
    HashSet localHashSet = new HashSet();
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE).iterator();
    while (paramContext.hasNext())
    {
      ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)paramContext.next();
      if (localRunningServiceInfo.service.getClassName().equals(paramString)) {
        localHashSet.add(localRunningServiceInfo.service.getPackageName());
      }
    }
    return localHashSet;
  }
  
  public static boolean isServiceRunning(Context paramContext, String paramString)
  {
    Iterator localIterator = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE).iterator();
    while (localIterator.hasNext())
    {
      ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)localIterator.next();
      if ((localRunningServiceInfo.service.getPackageName().equals(paramContext.getPackageName())) && (localRunningServiceInfo.service.getClassName().equals(paramString))) {
        return true;
      }
    }
    return false;
  }
  
  public static String makeInstalledAppsString(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
      while (paramContext.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
        if ((localApplicationInfo.flags & 0x1) == 0)
        {
          if (localStringBuilder.length() > 0) {
            localStringBuilder.append(",");
          }
          localStringBuilder.append(localApplicationInfo.packageName);
        }
      }
      paramContext = StringUtils.a(localStringBuilder.toString());
      if (paramContext == null) {
        return null;
      }
      paramContext = Base64.encodeToString(paramContext, 0);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      ThrowableExtension.printStackTrace(paramContext);
    }
    return null;
  }
}
