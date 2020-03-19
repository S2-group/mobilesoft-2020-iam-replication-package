package com.signallab.lib.utils;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.provider.Settings.System;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Window;
import android.widget.ImageButton;
import com.signallab.lib.utils.model.AppInfo;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class AppUtil
{
  public AppUtil() {}
  
  public static boolean checkAppHasPermission(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = obtainAppPermission(paramContext, paramString1);
    if (paramContext == null) {}
    for (;;)
    {
      return false;
      int j = paramContext.length;
      int i = 0;
      while (i < j)
      {
        if (TextUtils.equals(paramContext[i], paramString2)) {
          return true;
        }
        i += 1;
      }
    }
  }
  
  public static void collaspeStatusBar(Context paramContext)
  {
    paramContext.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
  }
  
  public static List<AppInfo> getAllAppInfo(@NonNull Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    if (localPackageManager == null) {
      return localArrayList;
    }
    if (Build.VERSION.SDK_INT >= 24) {}
    Iterator localIterator = localPackageManager.getInstalledApplications(8192).iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (ApplicationInfo)localIterator.next();
      String str1 = ((ApplicationInfo)localObject).packageName;
      if ((checkAppHasPermission(paramContext, str1, "android.permission.INTERNET")) && (!TextUtils.equals(str1, paramContext.getPackageName())))
      {
        String str2 = ((ApplicationInfo)localObject).loadLabel(localPackageManager).toString();
        localObject = ((ApplicationInfo)localObject).loadIcon(localPackageManager);
        AppInfo localAppInfo = new AppInfo();
        localAppInfo.setPackageName(str1);
        localAppInfo.setAppName(str2);
        localAppInfo.setIcon((Drawable)localObject);
        localArrayList.add(localAppInfo);
      }
    }
    return localArrayList;
  }
  
  public static int getIntVersionCode(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static String getLocalCountry()
  {
    return Locale.getDefault().getCountry();
  }
  
  public static String getLocalLanguage()
  {
    return Locale.getDefault().getLanguage();
  }
  
  public static String getManufactuer()
  {
    return Build.MANUFACTURER;
  }
  
  public static ImageButton getNavButtonViewInToolbar(Toolbar paramToolbar)
  {
    if (paramToolbar == null) {
      return null;
    }
    try
    {
      Field localField = Toolbar.class.getDeclaredField("mNavButtonView");
      localField.setAccessible(true);
      paramToolbar = (ImageButton)localField.get(paramToolbar);
      return paramToolbar;
    }
    catch (Exception paramToolbar)
    {
      paramToolbar.printStackTrace();
    }
    return null;
  }
  
  public static String getPhoneModel()
  {
    return Build.MODEL;
  }
  
  public static int getSdkInt()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static String getSdkVersionName()
  {
    return "Android " + Build.VERSION.RELEASE;
  }
  
  public static int getStatusBarColor(Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return paramActivity.getWindow().getStatusBarColor();
    }
    return 0;
  }
  
  public static String getVersionCode(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return String.valueOf(i);
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "0";
  }
  
  public static String getVersionName(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "1.0.0";
  }
  
  public static void gotoAppDetailPage(Context paramContext)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
    try
    {
      localIntent.setData(Uri.fromParts("package", paramContext.getPackageName(), null));
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static boolean isAirplaneMode(Context paramContext)
  {
    boolean bool = true;
    if (paramContext == null) {
      bool = false;
    }
    do
    {
      do
      {
        return bool;
        if (Build.VERSION.SDK_INT < 17) {
          break;
        }
      } while (Settings.System.getInt(paramContext.getContentResolver(), "airplane_mode_on", 0) != 0);
      return false;
    } while (Settings.System.getInt(paramContext.getContentResolver(), "airplane_mode_on", 0) != 0);
    return false;
  }
  
  public static boolean isNotificationEnabled(Context paramContext)
  {
    return NotificationManagerCompat.from(paramContext).areNotificationsEnabled();
  }
  
  @RequiresApi(api=19)
  public static boolean isNotificationEnabled2(Context paramContext)
  {
    AppOpsManager localAppOpsManager = (AppOpsManager)paramContext.getSystemService("appops");
    Object localObject = paramContext.getApplicationInfo();
    paramContext = paramContext.getApplicationContext().getPackageName();
    int i = ((ApplicationInfo)localObject).uid;
    try
    {
      localObject = Class.forName(AppOpsManager.class.getName());
      i = ((Integer)((Class)localObject).getMethod("checkOpNoThrow", new Class[] { Integer.TYPE, Integer.TYPE, String.class }).invoke(localAppOpsManager, new Object[] { Integer.valueOf(((Integer)((Class)localObject).getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class)).intValue()), Integer.valueOf(i), paramContext })).intValue();
      return i == 0;
    }
    catch (ClassNotFoundException paramContext)
    {
      paramContext.printStackTrace();
      return false;
    }
    catch (NoSuchFieldException paramContext)
    {
      for (;;) {}
    }
    catch (IllegalAccessException paramContext)
    {
      for (;;) {}
    }
    catch (NoSuchMethodException paramContext)
    {
      for (;;) {}
    }
    catch (InvocationTargetException paramContext)
    {
      for (;;) {}
    }
  }
  
  public static boolean isRTLDirection()
  {
    return (Build.VERSION.SDK_INT >= 17) && (TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1);
  }
  
  public static boolean isScreenOn(Context paramContext)
  {
    paramContext = (PowerManager)paramContext.getSystemService("power");
    if (Build.VERSION.SDK_INT < 21) {
      return paramContext.isScreenOn();
    }
    return paramContext.isInteractive();
  }
  
  public static String[] obtainAppPermission(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getPackageInfo(paramString, 4096).requestedPermissions;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static void openPlaystore(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setData(Uri.parse(paramString));
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void openSetting(Context paramContext)
  {
    try
    {
      paramContext.startActivity(new Intent("android.settings.SETTINGS"));
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void openSignalPlaystore(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent1 = paramContext.getPackageManager().getLaunchIntentForPackage("com.android.vending");
      localIntent1.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.activities.LaunchUrlHandlerActivity"));
      localIntent1.setData(Uri.parse("market://details?id=" + paramContext.getPackageName()));
      paramContext.startActivity(localIntent1);
      return;
    }
    catch (Exception localException)
    {
      try
      {
        Intent localIntent2 = new Intent("android.intent.action.VIEW");
        localIntent2.setData(Uri.parse(paramString));
        paramContext.startActivity(localIntent2);
        return;
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
    }
  }
  
  public static void setStatusBarColor(Activity paramActivity, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      paramActivity.getWindow().setStatusBarColor(paramInt);
    }
  }
}
