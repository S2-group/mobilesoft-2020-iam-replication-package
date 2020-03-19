package com.fanwe.library.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.telephony.TelephonyManager;
import com.fanwe.library.SDLibrary;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class SDPackageUtil
{
  public SDPackageUtil() {}
  
  public static void chmod(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = "chmod " + paramString1 + " " + paramString2;
      Runtime.getRuntime().exec(paramString1);
      return;
    }
    catch (IOException paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static PackageInfo getApkPackageInfo(String paramString)
  {
    return SDLibrary.getInstance().getContext().getPackageManager().getPackageArchiveInfo(paramString, 128);
  }
  
  public static PackageInfo getCurrentPackageInfo()
  {
    return getPackageInfo(getPackageName());
  }
  
  public static String getDeviceId()
  {
    return ((TelephonyManager)SDLibrary.getInstance().getContext().getApplicationContext().getSystemService("phone")).getDeviceId();
  }
  
  public static Bundle getMetaData(Context paramContext)
  {
    try
    {
      paramContext = SDLibrary.getInstance().getContext().getPackageManager().getApplicationInfo(getPackageName(), 128).metaData;
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static PackageInfo getPackageInfo(String paramString)
  {
    try
    {
      paramString = SDLibrary.getInstance().getContext().getPackageManager().getPackageInfo(paramString, 0);
      return paramString;
    }
    catch (Exception paramString) {}
    return null;
  }
  
  public static String getPackageName()
  {
    return SDLibrary.getInstance().getContext().getPackageName();
  }
  
  public static String getProcessName(Context paramContext)
  {
    int i = Process.myPid();
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses();
    if (paramContext == null) {}
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
    do
    {
      while (!paramContext.hasNext())
      {
        return null;
        paramContext = paramContext.iterator();
      }
      localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
    } while (localRunningAppProcessInfo.pid != i);
    return localRunningAppProcessInfo.processName;
  }
  
  public static int getVersionCode()
  {
    return getCurrentPackageInfo().versionCode;
  }
  
  public static String getVersionName()
  {
    return getCurrentPackageInfo().versionName;
  }
  
  public static boolean installApkPackage(String paramString)
  {
    if (paramString == null) {}
    while (!new File(paramString).exists()) {
      return false;
    }
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.addFlags(268435456);
    localIntent.setDataAndType(Uri.parse("file://" + paramString), "application/vnd.android.package-archive");
    SDLibrary.getInstance().getContext().startActivity(localIntent);
    return true;
  }
  
  public static boolean isAppInstalled(Context paramContext, String paramString)
  {
    boolean bool = false;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      if (paramContext != null) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean isBackground()
  {
    boolean bool2 = false;
    Iterator localIterator = ((ActivityManager)SDLibrary.getInstance().getContext().getSystemService("activity")).getRunningAppProcesses().iterator();
    ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo;
    do
    {
      bool1 = bool2;
      if (!localIterator.hasNext()) {
        break;
      }
      localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
    } while (!localRunningAppProcessInfo.processName.equals(getPackageName()));
    boolean bool1 = bool2;
    if (localRunningAppProcessInfo.importance != 100) {
      bool1 = true;
    }
    return bool1;
  }
  
  public static boolean isMainProcess(Context paramContext)
  {
    return paramContext.getPackageName().equals(getProcessName(paramContext));
  }
  
  public static Boolean isPackageExist(String paramString)
  {
    Iterator localIterator = SDLibrary.getInstance().getContext().getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext()) {
      if (((PackageInfo)localIterator.next()).packageName.equalsIgnoreCase(paramString)) {
        return Boolean.valueOf(true);
      }
    }
    return Boolean.valueOf(false);
  }
  
  public static void startAPP(String paramString)
  {
    try
    {
      paramString = SDLibrary.getInstance().getContext().getPackageManager().getLaunchIntentForPackage(paramString);
      SDLibrary.getInstance().getContext().startActivity(paramString);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static void startCurrentApp()
  {
    startAPP(SDLibrary.getInstance().getContext().getPackageName());
  }
}
