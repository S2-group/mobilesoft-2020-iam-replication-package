package com.jiubang.commerce.chargelocker.util.machine;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import com.jiubang.commerce.chargelocker.util.common.utils.log.LogUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class AppUtils
{
  public static final String ACTION_SETTINGS = "android.settings.APPLICATION_DETAILS_SETTINGS";
  
  public AppUtils() {}
  
  private static boolean a(Context paramContext, String paramString)
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
  
  private static boolean b(Context paramContext, String paramString)
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
  
  public static boolean canReadGmailLabels(Context paramContext)
  {
    boolean bool4 = false;
    boolean bool5 = false;
    boolean bool1 = false;
    boolean bool2 = bool4;
    for (;;)
    {
      try
      {
        paramContext = paramContext.getPackageManager().getPackageInfo("com.google.android.gm", 4104);
        bool2 = bool4;
        if (paramContext.permissions != null)
        {
          bool2 = bool4;
          int j = paramContext.permissions.length;
          i = 0;
          if (i < j)
          {
            bool2 = bool4;
            Object localObject = paramContext.permissions[i];
            bool2 = bool4;
            boolean bool3;
            if ("com.google.android.gm.permission.READ_CONTENT_PROVIDER".equals(((PermissionInfo)localObject).name))
            {
              bool2 = bool4;
              if (((PermissionInfo)localObject).protectionLevel < 2)
              {
                i = 1;
                bool3 = bool5;
                if (i == 0) {
                  break label218;
                }
                bool2 = bool4;
                bool3 = bool5;
                if (paramContext.providers == null) {
                  break label218;
                }
                bool2 = bool4;
                j = paramContext.providers.length;
                i = 0;
                bool3 = bool1;
                if (i >= j) {
                  break label218;
                }
                bool2 = bool1;
                localObject = paramContext.providers[i];
                bool3 = bool1;
                bool2 = bool1;
                if ("com.google.android.gm".equals(((ProviderInfo)localObject).authority))
                {
                  bool2 = bool1;
                  bool4 = TextUtils.equals("com.google.android.gm.permission.READ_CONTENT_PROVIDER", ((ProviderInfo)localObject).readPermission);
                  bool3 = bool1;
                  if (bool4) {
                    bool3 = true;
                  }
                }
                i += 1;
                bool1 = bool3;
                continue;
              }
            }
            i += 1;
            continue;
            return bool3;
          }
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        bool3 = bool2;
      }
      label218:
      int i = 0;
    }
  }
  
  public static float changeVersionNameToFloat(String paramString)
  {
    if ((paramString != null) && (!paramString.equals(""))) {
      try
      {
        String str = paramString.trim().toLowerCase();
        paramString = str;
        if (str.contains("_")) {
          paramString = str.substring(0, str.indexOf("_"));
        }
        str = paramString;
        if (paramString.contains("beta")) {
          str = paramString.replace("beta", "");
        }
        int i = str.indexOf(".", str.indexOf(".") + 1);
        if (i != -1) {
          return Float.parseFloat(str.substring(0, i) + str.substring(i + 1, str.length()));
        }
        float f = Float.parseFloat(str);
        return f;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
    return 0.0F;
  }
  
  public static List<ResolveInfo> filterPkgs(List<ResolveInfo> paramList, String[] paramArrayOfString)
  {
    if ((paramList == null) || (paramList.size() == 0) || (paramArrayOfString == null) || (paramArrayOfString.length == 0)) {
      return paramList;
    }
    ArrayList localArrayList = new ArrayList(paramList);
    paramList = paramList.iterator();
    label132:
    while (paramList.hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)paramList.next();
      if ((localResolveInfo != null) && (localResolveInfo.activityInfo != null) && (localResolveInfo.activityInfo.packageName != null))
      {
        int j = paramArrayOfString.length;
        int i = 0;
        for (;;)
        {
          if (i >= j) {
            break label132;
          }
          String str = paramArrayOfString[i];
          if (localResolveInfo.activityInfo.packageName.equals(str))
          {
            localArrayList.remove(localResolveInfo);
            break;
          }
          i += 1;
        }
      }
    }
    return localArrayList;
  }
  
  public static long getApkSize(PackageManager paramPackageManager, String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramPackageManager == null)) {}
    for (;;)
    {
      return 0L;
      try
      {
        paramPackageManager = paramPackageManager.getApplicationInfo(paramString, 0);
        if ((paramPackageManager != null) && (paramPackageManager.sourceDir != null))
        {
          long l = new File(paramPackageManager.sourceDir).length();
          return l;
        }
      }
      catch (PackageManager.NameNotFoundException paramPackageManager)
      {
        paramPackageManager.printStackTrace();
      }
    }
    return 0L;
  }
  
  public static Context getAppContext(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.createPackageContext(paramString, 2);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static Drawable getAppIcon(Context paramContext, String paramString)
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
          paramContext = paramString.loadIcon(paramContext);
          return paramContext;
        }
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        paramContext.printStackTrace();
      }
    }
    return null;
  }
  
  public static String getAppLable(Context paramContext, String paramString)
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
      catch (PackageManager.NameNotFoundException paramContext)
      {
        paramContext.printStackTrace();
      }
    }
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
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static int getAppVersionCode(Context paramContext, String paramString)
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
      LogUtils.e("ChargeLocker", "Error :" + paramString + " is not exist.");
      return -1;
    }
    catch (Exception paramContext) {}
    return -1;
  }
  
  public static String getAppVersionName(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static String getAppVersionName(Context paramContext, String paramString)
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
      LogUtils.e("ChargeLocker", "Error :" + paramString + " is not exist.");
      return "";
    }
    catch (Exception paramContext) {}
    return "";
  }
  
  public static ApplicationInfo getApplicationInfo(Context paramContext, String paramString)
  {
    if (paramString == null) {
      return null;
    }
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
  
  public static String getCurProcessName(Context paramContext)
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
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String getDefaultLauncher(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 65536);
    if (paramContext.activityInfo == null) {}
    while (paramContext.activityInfo.packageName.equals("android")) {
      return null;
    }
    return paramContext.activityInfo.packageName;
  }
  
  public static List<ApplicationInfo> getInstallAppInfo(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    return paramContext.getPackageManager().getInstalledApplications(8192);
  }
  
  public static List<ResolveInfo> getLauncherApps(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.LAUNCHER");
    try
    {
      List localList1 = paramContext.queryIntentActivities(localIntent, 0);
      return localList1;
    }
    catch (Exception localException)
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = paramContext.getInstalledApplications(0).iterator();
      while (localIterator.hasNext())
      {
        localIntent.setPackage(((ApplicationInfo)localIterator.next()).packageName);
        List localList2 = paramContext.queryIntentActivities(localIntent, 0);
        if ((localList2 != null) && (!localList2.isEmpty())) {
          localArrayList.addAll(localList2);
        }
      }
      return localArrayList;
    }
  }
  
  public static List<ResolveInfo> getLauncherApps(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    paramString = new Intent(paramString);
    paramString.addCategory("android.intent.category.LAUNCHER");
    try
    {
      paramContext = paramContext.queryIntentActivities(paramString, 0);
      return paramContext;
    }
    catch (OutOfMemoryError paramContext)
    {
      paramContext.printStackTrace();
      return null;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static String getPackage(Intent paramIntent)
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
  
  public static ServiceInfo getServiceInfo(Context paramContext, ComponentName paramComponentName)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getServiceInfo(paramComponentName, 4);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static String getTopAppPkgName(Context paramContext)
  {
    if (Machine.IS_SDK_ABOVE_L) {
      return null;
    }
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
  
  public static int getUidByPkgName(Context paramContext, String paramString)
  {
    try
    {
      int i = paramContext.getPackageManager().getApplicationInfo(paramString, 1).uid;
      return i;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static int getVersionCodeByPkgName(Context paramContext, String paramString)
  {
    int i = 0;
    if (paramString != null) {
      paramContext = paramContext.getPackageManager();
    }
    try
    {
      i = paramContext.getPackageInfo(paramString, 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      LogUtils.i("AppUtils", "getVersionCodeByPkgName=" + paramString + " has " + paramContext.getMessage());
    }
    return 0;
  }
  
  public static String getVersionNameByPkgName(Context paramContext, String paramString)
  {
    String str = "0.0";
    if (paramString != null) {
      paramContext = paramContext.getPackageManager();
    }
    try
    {
      str = paramContext.getPackageInfo(paramString, 0).versionName;
      return str;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "0.0";
  }
  
  public static boolean gotoBrowser(Context paramContext, String paramString)
  {
    if (paramString == null) {}
    do
    {
      return false;
      localObject = Uri.parse(paramString);
    } while (localObject == null);
    Object localObject = new Intent("android.intent.action.VIEW", (Uri)localObject);
    ((Intent)localObject).setFlags(268435456);
    try
    {
      paramContext.startActivity((Intent)localObject);
      return true;
    }
    catch (ActivityNotFoundException paramContext)
    {
      LogUtils.i("matt", "gotoBrowser error, uri = " + paramString);
      return false;
    }
    catch (Exception paramContext)
    {
      LogUtils.i("matt", "gotoBrowser error, uri = " + paramString);
    }
    return false;
  }
  
  public static void gotoBrowserIfFailtoMarket(Context paramContext, String paramString1, String paramString2)
  {
    if (!gotoMarket(paramContext, paramString1)) {
      gotoBrowser(paramContext, paramString2);
    }
  }
  
  public static boolean gotoMarket(Context paramContext, String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    paramString.setPackage("com.android.vending");
    if ((paramContext instanceof Activity)) {
      paramString.setFlags(1073741824);
    }
    for (;;)
    {
      try
      {
        paramContext.startActivity(paramString);
        return true;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      paramString.setFlags(268435456);
    }
    return false;
  }
  
  public static boolean isAppExist(Context paramContext, Intent paramIntent)
  {
    boolean bool2 = false;
    try
    {
      paramContext = paramContext.getPackageManager().queryIntentActivities(paramIntent, 0);
      boolean bool1 = bool2;
      if (paramContext != null)
      {
        bool1 = bool2;
        if (paramContext.size() > 0) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = null;
      }
    }
  }
  
  public static boolean isAppExist(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null)) {
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
  
  public static boolean isAppRunningInForground(Context paramContext, String paramString)
  {
    if (Machine.IS_SDK_ABOVE_L) {
      return b(paramContext, paramString);
    }
    return a(paramContext, paramString);
  }
  
  public static boolean isBrowser(Context paramContext, String paramString)
  {
    if (paramString == null) {}
    do
    {
      return false;
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.addCategory("android.intent.category.DEFAULT");
      localIntent.addCategory("android.intent.category.BROWSABLE");
      localIntent.setData(Uri.parse("http://3g.cn"));
      localIntent.setPackage(paramString);
      paramContext = paramContext.getPackageManager().queryIntentActivities(localIntent, 0);
    } while ((paramContext == null) || (paramContext.size() <= 0));
    return true;
  }
  
  public static boolean isDefaultLauncher(Context paramContext)
  {
    return paramContext.getPackageName().equals(getDefaultLauncher(paramContext));
  }
  
  public static boolean isGOLauncherRunningInForground(Context paramContext)
  {
    return isAppRunningInForground(paramContext, paramContext.getPackageName());
  }
  
  public static boolean isInstallOnSDCard(PackageManager paramPackageManager, String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramPackageManager == null)) {}
    for (;;)
    {
      return false;
      try
      {
        int i = paramPackageManager.getApplicationInfo(paramString, 0).flags;
        if ((i & 0x40000) != 0) {
          return true;
        }
      }
      catch (PackageManager.NameNotFoundException paramPackageManager)
      {
        paramPackageManager.printStackTrace();
      }
    }
    return false;
  }
  
  public static boolean isInternalApp(Context paramContext, Intent paramIntent)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    PackageManager localPackageManager;
    if (paramContext != null) {
      localPackageManager = paramContext.getPackageManager();
    }
    try
    {
      paramContext = Environment.getDataDirectory().getAbsolutePath();
      paramIntent = localPackageManager.getActivityInfo(paramIntent.getComponent(), 0).applicationInfo.publicSourceDir;
      bool1 = bool2;
      if (paramIntent != null)
      {
        bool1 = bool2;
        if (paramIntent.length() > 0) {
          bool1 = paramIntent.startsWith(paramContext);
        }
      }
      return bool1;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean isServiceRunning(ActivityManager paramActivityManager, String paramString1, String paramString2)
  {
    boolean bool2 = false;
    paramActivityManager = paramActivityManager.getRunningServices(Integer.MAX_VALUE);
    int i;
    int j;
    if (paramActivityManager == null)
    {
      i = 0;
      j = 0;
    }
    for (;;)
    {
      boolean bool1 = bool2;
      if (j < i)
      {
        ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)paramActivityManager.get(j);
        if ((localRunningServiceInfo != null) && (localRunningServiceInfo.service != null))
        {
          String str1 = localRunningServiceInfo.service.getPackageName();
          String str2 = localRunningServiceInfo.service.getClassName();
          if ((str1 != null) && (str1.contains(paramString1)) && (str2 != null) && (str2.contains(paramString2)))
          {
            LogUtils.i("Notification", "package = " + localRunningServiceInfo.service.getPackageName() + " class = " + localRunningServiceInfo.service.getClassName());
            bool1 = true;
          }
        }
      }
      else
      {
        return bool1;
        i = paramActivityManager.size();
        break;
      }
      j += 1;
    }
  }
  
  public static boolean isServiceRunning(Context paramContext, String paramString1, String paramString2)
  {
    return isServiceRunning((ActivityManager)paramContext.getSystemService("activity"), paramString1, paramString2);
  }
  
  public static boolean isSystemApp(Context paramContext, Intent paramIntent)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramContext != null) {
      paramContext = paramContext.getPackageManager();
    }
    try
    {
      paramContext = paramContext.getActivityInfo(paramIntent.getComponent(), 0).applicationInfo;
      bool1 = bool2;
      if (paramContext != null) {
        if ((paramContext.flags & 0x1) == 0)
        {
          int i = paramContext.flags;
          bool1 = bool2;
          if ((i & 0x80) == 0) {}
        }
        else
        {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean isSystemApp(Context paramContext, String paramString)
  {
    paramContext = getApplicationInfo(paramContext, paramString);
    if (paramContext == null) {}
    while (((paramContext.flags & 0x1) == 0) && ((paramContext.flags & 0x80) == 0)) {
      return false;
    }
    return true;
  }
  
  public static void killProcess()
  {
    Process.killProcess(Process.myPid());
  }
  
  public static boolean safeStartActivity(Context paramContext, Intent paramIntent)
  {
    if (paramContext != null) {}
    try
    {
      paramContext.startActivity(paramIntent);
      return true;
    }
    catch (ActivityNotFoundException paramContext)
    {
      LogUtils.e("matt", "saveStartActivity err " + paramContext.getMessage());
      return false;
    }
    catch (SecurityException paramContext)
    {
      for (;;)
      {
        LogUtils.e("matt", "saveStartActivity err " + paramContext.getMessage());
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        LogUtils.e("matt", "saveStartActivity err " + paramContext.getMessage());
      }
    }
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
    catch (ActivityNotFoundException paramContext)
    {
      LogUtils.e("matt", "saveStartActivity err " + paramContext.getMessage());
      return false;
    }
    catch (SecurityException paramContext)
    {
      for (;;)
      {
        LogUtils.e("matt", "saveStartActivity err " + paramContext.getMessage());
      }
    }
  }
  
  public static void safeStartActivityForResult(Activity paramActivity, Intent paramIntent, int paramInt)
  {
    if (paramActivity != null) {}
    try
    {
      paramActivity.startActivityForResult(paramIntent, paramInt);
      return;
    }
    catch (ActivityNotFoundException paramActivity)
    {
      LogUtils.e("matt", "saveStartActivityForResult err " + paramActivity.getMessage());
      return;
    }
    catch (SecurityException paramActivity)
    {
      LogUtils.e("matt", "saveStartActivityForResult err " + paramActivity.getMessage());
    }
  }
  
  public static void showAppDetails(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent();
    int i = Build.VERSION.SDK_INT;
    if (i >= 9)
    {
      localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
      localIntent.setData(Uri.fromParts("package", paramString, null));
    }
    try
    {
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      String str;
      paramContext.printStackTrace();
    }
    if (i == 8) {}
    for (str = "pkg";; str = "com.android.settings.ApplicationPkgName")
    {
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
      localIntent.putExtra(str, paramString);
      break;
    }
  }
  
  public static void uninstallApp(Activity paramActivity, Uri paramUri)
  {
    paramActivity.startActivity(new Intent("android.intent.action.DELETE", paramUri));
  }
  
  public static void uninstallPackage(Activity paramActivity, String paramString)
  {
    uninstallApp(paramActivity, Uri.parse("package:" + paramString));
  }
}
