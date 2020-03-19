package com.amber.amberutils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

public class SystemUtil
{
  public SystemUtil() {}
  
  public static Drawable getAppIcon(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).applicationInfo.icon;
      paramContext = paramContext.getResources().getDrawable(i);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static String getAppName(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).applicationInfo.labelRes;
      paramContext = paramContext.getResources().getString(i);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static List<PackageInfo> getInstalledAppList(Context paramContext, int paramInt)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(paramInt);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static int getPackageUid(Context paramContext, String paramString)
  {
    int i = -1;
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramString, 0);
      if (paramContext != null) {
        i = paramContext.uid;
      }
      return i;
    }
    catch (Exception paramContext) {}
    return -1;
  }
  
  public static String getWidgetPkg(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    Object localObject = getInstalledAppList(paramContext, 0);
    if ((localObject == null) || (((List)localObject).size() == 0)) {
      return "";
    }
    paramContext = null;
    Iterator localIterator = ((List)localObject).iterator();
    for (;;)
    {
      if (localIterator.hasNext())
      {
        localObject = ((PackageInfo)localIterator.next()).packageName;
        try
        {
          localObject = localPackageManager.getApplicationInfo((String)localObject, 128);
          paramContext = (Context)localObject;
          if (localObject != null)
          {
            paramContext = (Context)localObject;
            if (((ApplicationInfo)localObject).metaData != null)
            {
              paramContext = (Context)localObject;
              if (((ApplicationInfo)localObject).metaData.getBoolean("MUL_WIDGET")) {
                return ((ApplicationInfo)localObject).packageName;
              }
            }
          }
        }
        catch (Exception localException)
        {
          for (;;)
          {
            localException.printStackTrace();
            Context localContext = paramContext;
          }
        }
      }
    }
    return "";
  }
  
  public static boolean isAppRunning(Context paramContext, String paramString)
  {
    Object localObject = (ActivityManager)paramContext.getSystemService("activity");
    paramContext = null;
    try
    {
      localObject = ((ActivityManager)localObject).getRunningTasks(100);
      paramContext = (Context)localObject;
    }
    catch (SecurityException localSecurityException)
    {
      for (;;)
      {
        localSecurityException.printStackTrace();
      }
      paramContext = paramContext.iterator();
      do
      {
        if (!paramContext.hasNext()) {
          break;
        }
      } while (!((ActivityManager.RunningTaskInfo)paramContext.next()).baseActivity.getPackageName().equals(paramString));
    }
    return (paramContext != null) && (paramContext.size() > 0);
  }
  
  public static boolean isProcessRunning(Context paramContext, int paramInt)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(200);
    if (paramContext.size() > 0)
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext()) {
        if (paramInt == ((ActivityManager.RunningServiceInfo)paramContext.next()).uid) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static boolean isServiceRunning(Context paramContext, String paramString)
  {
    if ((paramString == null) || (paramContext == null)) {}
    for (;;)
    {
      return false;
      String str = paramContext.getPackageName();
      if (!TextUtils.isEmpty(str)) {
        try
        {
          paramContext = (ActivityManager)paramContext.getSystemService("activity");
          if (paramContext != null)
          {
            paramContext = paramContext.getRunningServices(Integer.MAX_VALUE);
            if ((paramContext != null) && (paramContext.size() > 0))
            {
              paramContext = paramContext.iterator();
              if (paramContext.hasNext())
              {
                paramContext = (ActivityManager.RunningServiceInfo)paramContext.next();
                if (str.equals(paramContext.service.getPackageName()))
                {
                  boolean bool = paramString.equals(paramContext.service.getClassName());
                  if (bool) {
                    return true;
                  }
                }
              }
            }
          }
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
        }
      }
    }
    return false;
  }
}
