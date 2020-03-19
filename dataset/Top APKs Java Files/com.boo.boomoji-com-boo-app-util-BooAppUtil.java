package com.boo.app.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class BooAppUtil
{
  private static long lastClickTime;
  
  private BooAppUtil() {}
  
  public static void exit()
  {
    System.exit(0);
  }
  
  public static int getBoomojiVersionCode(Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      int i = paramContext.getPackageInfo("com.boo.boomoji", 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static String getLocalPackageName(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getApplicationContext().getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).packageName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static int getLocalVersion(Context paramContext)
  {
    try
    {
      int i = paramContext.getApplicationContext().getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static String getLocalVersionName(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getApplicationContext().getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static boolean isAppInstalled(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getPackageManager().getPackageInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean isAppOnForeground(Context paramContext)
  {
    Object localObject = (ActivityManager)paramContext.getApplicationContext().getSystemService("activity");
    paramContext = paramContext.getApplicationContext().getPackageName();
    localObject = ((ActivityManager)localObject).getRunningAppProcesses();
    if (localObject == null) {
      return false;
    }
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject).next();
      if (((localRunningAppProcessInfo.processName.equals("com.boo.chat:website")) || (localRunningAppProcessInfo.processName.equals("com.boo.chat:booc")) || (localRunningAppProcessInfo.processName.equals("com.boo.chat:minisite"))) && (localRunningAppProcessInfo.importance == 100)) {
        return true;
      }
      if ((localRunningAppProcessInfo.processName.contains(paramContext)) && (localRunningAppProcessInfo.importance == 100)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isAvilible(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    int i = 0;
    paramContext = paramContext.getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null) {
      while (i < paramContext.size())
      {
        localArrayList.add(((PackageInfo)paramContext.get(i)).packageName);
        i += 1;
      }
    }
    return localArrayList.contains(paramString);
  }
  
  public static boolean isFastDoubleClick(int paramInt)
  {
    long l = System.currentTimeMillis();
    if (l - lastClickTime < paramInt) {
      return true;
    }
    lastClickTime = l;
    return false;
  }
  
  public static boolean isSupportBoomoji()
  {
    return Build.VERSION.SDK_INT >= 24;
  }
  
  public static void launchApp(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return;
    }
    paramContext.startActivity(paramContext.getPackageManager().getLaunchIntentForPackage(paramString));
  }
  
  public static void launchAppDetail(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      if (TextUtils.isEmpty(paramString1)) {
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("market://details?id=");
      localStringBuilder.append(paramString1);
      paramString1 = new Intent("android.intent.action.VIEW", Uri.parse(localStringBuilder.toString()));
      if (!TextUtils.isEmpty(paramString2)) {
        paramString1.setPackage(paramString2);
      }
      paramString1.addFlags(268435456);
      paramContext.startActivity(paramString1);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void launchAppDetailGooglePlay(Context paramContext, String paramString)
  {
    try
    {
      if (TextUtils.isEmpty(paramString)) {
        return;
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("market://details?id=");
      ((StringBuilder)localObject).append(paramString);
      localObject = new Intent("android.intent.action.VIEW", Uri.parse(((StringBuilder)localObject).toString()));
      ((Intent)localObject).setPackage("com.android.vending");
      ((Intent)localObject).addFlags(268435456);
      paramContext.startActivity((Intent)localObject);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return;
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("http://play.google.com/store/apps/details?id=");
      ((StringBuilder)localObject).append(paramString);
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(((StringBuilder)localObject).toString())));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      for (;;) {}
    }
  }
}
