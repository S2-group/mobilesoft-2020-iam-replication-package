package com.yy.pushsvc.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Process;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppPackageUtil
{
  private static final String TAG = "AppPackageUtil";
  private static int mAppKey = 0;
  
  public AppPackageUtil() {}
  
  public static int getAppKey(Context paramContext)
  {
    int i = 0;
    if (mAppKey > 0) {
      i = mAppKey;
    }
    while (paramContext == null) {
      return i;
    }
    try
    {
      mAppKey = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.getInt("appKey");
      i = mAppKey;
      return i;
    }
    catch (Exception paramContext)
    {
      PushLog.inst().log("AppPackageUtil.getAppKey error: " + StringUtil.exception2String(paramContext));
    }
    return 0;
  }
  
  public static int getAppRunningStatus(Context paramContext)
  {
    try
    {
      int i = getAppRunningStatus(paramContext, paramContext.getApplicationContext().getPackageName());
      return i;
    }
    catch (Exception paramContext)
    {
      PushLog.inst().log("AppPackageUtil.getAppRunningStatus 2 error: " + StringUtil.exception2String(paramContext));
    }
    return -1;
  }
  
  public static int getAppRunningStatus(Context paramContext, String paramString)
  {
    if ((paramContext != null) && (paramString != null)) {
      try
      {
        if (!paramString.equals(""))
        {
          paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
          if ((paramContext == null) || (paramContext.size() == 0)) {
            break label153;
          }
          paramContext = paramContext.iterator();
          while (paramContext.hasNext())
          {
            ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
            if ((localRunningAppProcessInfo != null) && (localRunningAppProcessInfo.processName.equals(paramString)))
            {
              if (localRunningAppProcessInfo.importance == 100) {
                return 1;
              }
              int i = localRunningAppProcessInfo.importance;
              if (i == 400) {
                return 2;
              }
              return 3;
            }
          }
          return 0;
        }
      }
      catch (Exception paramContext)
      {
        PushLog.inst().log("AppPackageUtil.getAppRunningStatus error: " + StringUtil.exception2String(paramContext));
        return -1;
      }
    }
    return -2;
    label153:
    return -1;
  }
  
  public static String getCurrentProcessName(Context paramContext)
  {
    try
    {
      int i = Process.myPid();
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
      while (paramContext.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        if (localRunningAppProcessInfo.pid == i)
        {
          paramContext = localRunningAppProcessInfo.processName;
          return paramContext;
        }
      }
      return null;
    }
    catch (Exception paramContext)
    {
      PushLog.inst().log("AppPackageUtil.getCurrentProcessName error: " + StringUtil.exception2String(paramContext));
    }
    return null;
  }
  
  public static String getMac(Context paramContext)
  {
    try
    {
      paramContext = (WifiManager)paramContext.getSystemService("wifi");
      if (paramContext != null)
      {
        paramContext = paramContext.getConnectionInfo();
        if (paramContext != null)
        {
          paramContext = paramContext.getMacAddress();
          if (paramContext != null) {
            try
            {
              paramContext = URLEncoder.encode(paramContext, "UTF-8");
              if (paramContext != null) {
                return paramContext;
              }
            }
            catch (Exception paramContext)
            {
              PushLog.inst().log("AppPackageUtil.getMac error: " + StringUtil.exception2String(paramContext));
            }
          }
        }
      }
      return "YY_FAKE_MAC";
    }
    catch (Exception paramContext)
    {
      PushLog.inst().log("AppPackageUtil.getMac error: " + StringUtil.exception2String(paramContext));
    }
    return "YY_FAKE_MAC";
  }
  
  public static int getMyAppKey(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.getInt("appKey");
      return i;
    }
    catch (Exception paramContext)
    {
      PushLog.inst().log("AppPackageUtil.getMyAppKey error: " + StringUtil.exception2String(paramContext));
    }
    return 0;
  }
  
  public static String getMyServiceClassName(Context paramContext)
  {
    Object localObject = null;
    try
    {
      ActivityManager.RunningServiceInfo localRunningServiceInfo = getMyServiceInfo(paramContext);
      paramContext = localObject;
      if (localRunningServiceInfo != null)
      {
        paramContext = localObject;
        if (localRunningServiceInfo.service.getClassName() != null) {
          paramContext = localRunningServiceInfo.service.getClassName();
        }
      }
      return paramContext;
    }
    catch (Exception paramContext)
    {
      PushLog.inst().log("AppPackageUtil.getMyServiceClassName error: " + StringUtil.exception2String(paramContext));
    }
    return null;
  }
  
  public static ActivityManager.RunningServiceInfo getMyServiceInfo(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    for (;;)
    {
      int i;
      try
      {
        paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
        if (paramContext.size() > 0) {
          break label114;
        }
        return null;
      }
      catch (Exception paramContext)
      {
        PushLog.inst().log("AppPackageUtil.getMyServiceInfo error: " + StringUtil.exception2String(paramContext));
        return null;
      }
      if (i < paramContext.size())
      {
        if (Process.myPid() == ((ActivityManager.RunningServiceInfo)paramContext.get(i)).pid)
        {
          paramContext = (ActivityManager.RunningServiceInfo)paramContext.get(i);
          return paramContext;
        }
        i += 1;
      }
      else
      {
        return null;
        label114:
        i = 0;
      }
    }
  }
  
  public static String getMyServicePackageName(Context paramContext)
  {
    Object localObject = null;
    try
    {
      ActivityManager.RunningServiceInfo localRunningServiceInfo = getMyServiceInfo(paramContext);
      paramContext = localObject;
      if (localRunningServiceInfo != null)
      {
        paramContext = localObject;
        if (localRunningServiceInfo.service.getPackageName() != null) {
          paramContext = localRunningServiceInfo.service.getPackageName();
        }
      }
      return paramContext;
    }
    catch (Exception paramContext)
    {
      PushLog.inst().log("AppPackageUtil.getMyServicePackageName error: " + StringUtil.exception2String(paramContext));
    }
    return null;
  }
  
  public static int getPackagePID(Context paramContext, String paramString)
  {
    try
    {
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
      while (paramContext.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        if ((paramString.equals(localRunningAppProcessInfo.processName)) && (localRunningAppProcessInfo.pid != 0))
        {
          int i = localRunningAppProcessInfo.pid;
          return i;
        }
      }
      return -1;
    }
    catch (Exception paramContext)
    {
      PushLog.inst().log("AppPackageUtil.getPackagePID error: " + StringUtil.exception2String(paramContext));
    }
    return -1;
  }
  
  public static int getSDKVersion(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData.getInt("sdkversion");
      return i;
    }
    catch (Exception paramContext)
    {
      PushLog.inst().log("AppPackageUtil.getSDKVersion error: " + StringUtil.exception2String(paramContext));
    }
    return 0;
  }
  
  public static ActivityManager.RunningServiceInfo getServiceInfo(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null)) {}
    try
    {
      PushLog.inst().log("AppPackageUtil.getServiceInfo svc context == null || serviceName == null");
      return null;
    }
    catch (Exception paramContext)
    {
      List localList;
      PushLog.inst().log("AppPackageUtil.getServiceInfo error: " + StringUtil.exception2String(paramContext));
      return null;
    }
    localList = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
    if (localList.size() <= 0)
    {
      PushLog.inst().log("AppPackageUtil.getServiceInfo svc list == null");
      return null;
    }
    for (;;)
    {
      int i;
      if (i < localList.size())
      {
        String str1 = ((ActivityManager.RunningServiceInfo)localList.get(i)).service.getClassName().toString();
        String str2 = ((ActivityManager.RunningServiceInfo)localList.get(i)).service.getPackageName().toString();
        int j = ((ActivityManager.RunningServiceInfo)localList.get(i)).pid;
        if ((str1 != null) && (str2 != null) && (str2.equals(paramContext.getApplicationContext().getPackageName())) && (str1.compareTo(paramString) == 0) && (j != 0))
        {
          paramContext = (ActivityManager.RunningServiceInfo)localList.get(i);
          return paramContext;
        }
        i += 1;
      }
      else
      {
        return null;
        i = 0;
      }
    }
  }
  
  public static ActivityManager.RunningServiceInfo getServiceInfo(Context paramContext, String paramString1, String paramString2)
  {
    if ((paramContext == null) || (paramString1 == null) || (paramString2 == null)) {
      return null;
    }
    for (;;)
    {
      int i;
      try
      {
        paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
        if (paramContext.size() > 0) {
          break label183;
        }
        return null;
      }
      catch (Exception paramContext)
      {
        String str1;
        String str2;
        int j;
        PushLog.inst().log("AppPackageUtil.getServiceInfo error: " + StringUtil.exception2String(paramContext));
        return null;
      }
      if (i < paramContext.size())
      {
        str1 = ((ActivityManager.RunningServiceInfo)paramContext.get(i)).service.getClassName().toString();
        str2 = ((ActivityManager.RunningServiceInfo)paramContext.get(i)).service.getPackageName().toString();
        j = ((ActivityManager.RunningServiceInfo)paramContext.get(i)).pid;
        if ((str1.compareTo(paramString2) == 0) && (paramString1.compareTo(str2) == 0) && (j != 0))
        {
          paramContext = (ActivityManager.RunningServiceInfo)paramContext.get(i);
          return paramContext;
        }
        i += 1;
      }
      else
      {
        return null;
        label183:
        i = 0;
      }
    }
  }
  
  public static ArrayList<String> getServiceList(Context paramContext)
  {
    try
    {
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
      if (paramContext.size() <= 0) {
        return null;
      }
      ArrayList localArrayList = new ArrayList();
      int i = 0;
      while (i < paramContext.size())
      {
        localArrayList.add(((ActivityManager.RunningServiceInfo)paramContext.get(i)).service.getClassName().toString());
        i += 1;
      }
      return localArrayList;
    }
    catch (Exception paramContext)
    {
      PushLog.inst().log("AppPackageUtil.getServiceList error: " + StringUtil.exception2String(paramContext));
    }
    return null;
  }
  
  public static int getServicePID(Context paramContext, String paramString)
  {
    paramContext = getServiceInfo(paramContext, paramString);
    if (paramContext != null) {
      return paramContext.pid;
    }
    return -1;
  }
  
  public static String getServicePackageName(Context paramContext, String paramString)
  {
    paramContext = getServiceInfo(paramContext, paramString);
    if (paramContext != null) {
      return paramContext.service.getPackageName();
    }
    return null;
  }
  
  public static boolean inServiceProcess(Context paramContext, String paramString)
  {
    boolean bool = false;
    try
    {
      int i = Process.myPid();
      int j = getServicePID(paramContext, paramString);
      if (i == j) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext)
    {
      PushLog.inst().log("AppPackageUtil.inServiceProcess error: " + StringUtil.exception2String(paramContext));
    }
    return false;
  }
  
  public static boolean isActivityExist(Context paramContext, String paramString1, String paramString2)
  {
    boolean bool = false;
    try
    {
      Intent localIntent = new Intent();
      localIntent.setComponent(new ComponentName(paramString1, paramString2));
      int i = paramContext.getPackageManager().queryIntentActivities(localIntent, 65536).size();
      if (i > 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext)
    {
      PushLog.inst().log("AppPackageUtil.isActivityExist error: " + StringUtil.exception2String(paramContext));
    }
    return false;
  }
  
  public static boolean isAppInFrontground(Context paramContext, String paramString)
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
    catch (Exception paramContext)
    {
      PushLog.inst().log("AppPackageUtil.isAppInFrontground error: " + StringUtil.exception2String(paramContext));
    }
    return false;
  }
  
  public static boolean isPackageExist(Context paramContext, String paramString)
  {
    if (paramString != null) {
      try
      {
        if (!"".equals(paramString))
        {
          paramContext = paramContext.getPackageManager().getInstalledPackages(0);
          if (paramContext != null)
          {
            paramContext = paramContext.iterator();
            while (paramContext.hasNext())
            {
              boolean bool = ((PackageInfo)paramContext.next()).packageName.equals(paramString);
              if (bool) {
                return true;
              }
            }
          }
          return false;
        }
      }
      catch (Exception paramContext)
      {
        PushLog.inst().log("AppPackageUtil.isPackageExist error: " + StringUtil.exception2String(paramContext));
        return false;
      }
    }
    return false;
  }
  
  public static boolean isPackageRunning(Context paramContext, String paramString)
  {
    try
    {
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
      while (paramContext.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        if (paramString.equals(localRunningAppProcessInfo.processName))
        {
          int i = localRunningAppProcessInfo.pid;
          if (i != 0) {
            return true;
          }
        }
      }
      return false;
    }
    catch (Exception paramContext)
    {
      PushLog.inst().log("AppPackageUtil.isPackageRunning error: " + StringUtil.exception2String(paramContext));
    }
    return false;
  }
  
  public static boolean isServiceRunning(Context paramContext, String paramString)
  {
    return getServiceInfo(paramContext, paramString) != null;
  }
  
  public static boolean isServiceRunning(Context paramContext, String paramString1, String paramString2)
  {
    return getServiceInfo(paramContext, paramString1, paramString2) != null;
  }
  
  public static int isSvcRunning(Context paramContext, String paramString1, String paramString2)
  {
    if ((paramContext == null) || (paramString1 == null) || (paramString2 == null)) {
      return -1;
    }
    for (;;)
    {
      int i;
      try
      {
        paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
        if (paramContext.size() > 0) {
          break label187;
        }
        return -1;
      }
      catch (Exception paramContext)
      {
        String str1;
        String str2;
        int j;
        PushLog.inst().log("AppPackageUtil.isSvcRunning error: " + StringUtil.exception2String(paramContext));
        return -1;
      }
      if (i < paramContext.size())
      {
        str1 = ((ActivityManager.RunningServiceInfo)paramContext.get(i)).service.getClassName().toString();
        str2 = ((ActivityManager.RunningServiceInfo)paramContext.get(i)).service.getPackageName().toString();
        j = ((ActivityManager.RunningServiceInfo)paramContext.get(i)).pid;
        if ((str1.compareTo(paramString2) == 0) && (paramString1.compareTo(str2) == 0) && (j != 0))
        {
          paramContext = paramContext.get(i);
          if (paramContext != null) {
            return 1;
          }
          return 0;
        }
        i += 1;
      }
      else
      {
        return 0;
        label187:
        i = 0;
      }
    }
  }
  
  public static void showAllService(Context paramContext)
  {
    try
    {
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
      while (paramContext.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        PushLog.inst().log("AppPackageUtil.inServiceProcess has Pid=" + localRunningAppProcessInfo.pid + ", processName=" + localRunningAppProcessInfo.processName);
      }
      return;
    }
    catch (Exception paramContext)
    {
      PushLog.inst().log("AppPackageUtil.showAllService error: " + StringUtil.exception2String(paramContext));
    }
  }
  
  public static void showServiceInfo(Context paramContext, String paramString)
  {
    for (;;)
    {
      int i;
      try
      {
        paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE);
        if (paramContext.size() > 0) {
          break label125;
        }
        return;
      }
      catch (Exception paramContext)
      {
        String str;
        int j;
        PushLog.inst().log("AppPackageUtil.showServiceInfo error: " + StringUtil.exception2String(paramContext));
      }
      if (i < paramContext.size())
      {
        str = ((ActivityManager.RunningServiceInfo)paramContext.get(i)).service.getClassName().toString();
        ((ActivityManager.RunningServiceInfo)paramContext.get(i)).service.getPackageName().toString();
        j = str.compareTo(paramString);
        if (j == 0) {}
        i += 1;
      }
      else
      {
        return;
        label125:
        i = 0;
      }
    }
  }
  
  public static boolean startAppByPackageName(Context paramContext, String paramString)
  {
    boolean bool = true;
    if ((paramContext == null) || (paramString == null)) {
      bool = false;
    }
    for (;;)
    {
      return bool;
      try
      {
        if (!isPackageRunning(paramContext, paramString))
        {
          paramString = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
          if (paramString != null)
          {
            paramContext.startActivity(paramString);
            return true;
          }
        }
      }
      catch (Exception paramContext)
      {
        PushLog.inst().log("AppPackageUtil.startAppByPackageName error: " + StringUtil.exception2String(paramContext));
        return false;
      }
    }
    return false;
  }
}
