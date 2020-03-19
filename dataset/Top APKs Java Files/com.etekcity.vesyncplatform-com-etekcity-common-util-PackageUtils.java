package com.etekcity.common.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.widget.Toast;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class PackageUtils
{
  public static final int APP_INSTALL_AUTO = 0;
  public static final int APP_INSTALL_EXTERNAL = 2;
  public static final int APP_INSTALL_INTERNAL = 1;
  
  public PackageUtils() {}
  
  public static PackageInfo getAppPackageInfo(Context paramContext)
  {
    if (paramContext != null)
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      if (localPackageManager != null) {
        try
        {
          paramContext = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0);
          return paramContext;
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
        }
      }
    }
    return null;
  }
  
  public static int getAppVersionCode(Context paramContext)
  {
    if (paramContext != null)
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      if (localPackageManager != null) {
        try
        {
          paramContext = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0);
          if (paramContext != null)
          {
            int i = paramContext.versionCode;
            return i;
          }
        }
        catch (PackageManager.NameNotFoundException paramContext)
        {
          paramContext.printStackTrace();
        }
      }
    }
    return -1;
  }
  
  public static ApplicationInfo getApplicationInfo(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramString, 0);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static List<PackageInfo> getInsatalledPackages(Context paramContext)
  {
    return paramContext.getPackageManager().getInstalledPackages(0);
  }
  
  public static int getInstallLocation()
  {
    ShellUtils.CommandResult localCommandResult = ShellUtils.execCommand("LD_LIBRARY_PATH=/vendor/lib:/system/lib pm get-install-location", false, true);
    if ((localCommandResult.result == 0) && (localCommandResult.responseMsg != null) && (localCommandResult.responseMsg.length() > 0)) {
      try
      {
        int i = Integer.parseInt(localCommandResult.responseMsg.substring(0, 1));
        return i;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        localNumberFormatException.printStackTrace();
      }
    }
    return 0;
  }
  
  public static PackageInfo getPackageInfo(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static ActivityManager.RunningTaskInfo getTopRunningTask(Context paramContext)
  {
    try
    {
      paramContext = (ActivityManager.RunningTaskInfo)((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1).get(0);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static void goToInstalledAppDetails(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent();
    int i = Build.VERSION.SDK_INT;
    if (Build.VERSION.SDK_INT >= 9)
    {
      localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
      localIntent.setData(Uri.fromParts("package", paramString, null));
    }
    else
    {
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
      String str;
      if (i == 8) {
        str = "pkg";
      } else {
        str = "com.android.settings.ApplicationPkgName";
      }
      localIntent.putExtra(str, paramString);
    }
    localIntent.addFlags(268435456);
    paramContext.startActivity(localIntent);
  }
  
  public static boolean install(Context paramContext, File paramFile)
  {
    if ((paramFile != null) && (paramFile.exists()) && (paramFile.isFile()))
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setDataAndType(Uri.fromFile(paramFile), "application/vnd.android.package-archive");
      localIntent.addFlags(268435456);
      paramContext.startActivity(localIntent);
      return true;
    }
    return false;
  }
  
  public static boolean isSystemApplication(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    return isSystemApplication(paramContext, paramContext.getPackageName());
  }
  
  public static boolean isSystemApplication(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    if ((paramContext != null) && (paramString != null))
    {
      if (paramString.length() == 0) {
        return false;
      }
      try
      {
        paramContext = paramContext.getApplicationInfo(paramString, 0);
        if (paramContext != null)
        {
          int i = paramContext.flags;
          if ((i & 0x1) > 0) {
            return true;
          }
        }
        return false;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        return false;
      }
    }
    return false;
  }
  
  public static boolean startAppByPackageName(Context paramContext, String paramString)
  {
    return startAppByPackageName(paramContext, paramString, null);
  }
  
  public static boolean startAppByPackageName(Context paramContext, String paramString, Map<String, String> paramMap)
  {
    try
    {
      paramString = paramContext.getPackageManager().getPackageInfo(paramString, 0);
      Object localObject = new Intent("android.intent.action.MAIN", null);
      ((Intent)localObject).addCategory("android.intent.category.LAUNCHER");
      if (Build.VERSION.SDK_INT >= 4) {
        ((Intent)localObject).setPackage(paramString.packageName);
      }
      paramString = (ResolveInfo)paramContext.getPackageManager().queryIntentActivities((Intent)localObject, 0).iterator().next();
      if (paramString != null)
      {
        localObject = paramString.activityInfo.packageName;
        String str = paramString.activityInfo.name;
        paramString = new Intent("android.intent.action.MAIN");
        paramString.setFlags(268435456);
        paramString.addCategory("android.intent.category.LAUNCHER");
        paramString.setComponent(new ComponentName((String)localObject, str));
        if (paramMap != null)
        {
          paramMap = paramMap.entrySet().iterator();
          while (paramMap.hasNext())
          {
            localObject = (Map.Entry)paramMap.next();
            paramString.putExtra((String)((Map.Entry)localObject).getKey(), (String)((Map.Entry)localObject).getValue());
          }
        }
        paramContext.startActivity(paramString);
        return true;
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      Toast.makeText(paramContext.getApplicationContext(), "启动失败", 1).show();
    }
    return false;
  }
  
  public static void uninstallApk(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.DELETE");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("package:");
    localStringBuilder.append(paramString);
    localIntent.setData(Uri.parse(localStringBuilder.toString()));
    paramContext.startActivity(localIntent);
  }
}
