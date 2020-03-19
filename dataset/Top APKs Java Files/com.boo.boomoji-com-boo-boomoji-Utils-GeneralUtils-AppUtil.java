package com.boo.boomoji.Utils.GeneralUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AppUtil
{
  private AppUtil() {}
  
  public static Drawable getApplicationIcon(Context paramContext)
  {
    PackageManager localPackageManager = getPackageManager(paramContext);
    try
    {
      paramContext = localPackageManager.getPackageInfo(getPackageName(paramContext), 0).applicationInfo.loadIcon(localPackageManager);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static List<PackageInfo> getInstalledPackages(Context paramContext)
  {
    paramContext = getPackageManager(paramContext);
    int i = 0;
    paramContext = paramContext.getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    while (i < paramContext.size())
    {
      if ((((PackageInfo)paramContext.get(i)).applicationInfo.flags & 0x1) == 0) {
        localArrayList.add(paramContext.get(i));
      }
      i += 1;
    }
    return localArrayList;
  }
  
  public static String getMobileBrand(Context paramContext)
  {
    try
    {
      paramContext = Build.BRAND;
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return "未知";
  }
  
  private static PackageManager getPackageManager(Context paramContext)
  {
    return paramContext.getPackageManager();
  }
  
  public static String getPackageName(Context paramContext)
  {
    return paramContext.getPackageName();
  }
  
  public static int getVersionCode(Context paramContext)
  {
    PackageManager localPackageManager = getPackageManager(paramContext);
    try
    {
      int i = localPackageManager.getPackageInfo(getPackageName(paramContext), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return -1;
  }
  
  public static String getVersionName(Context paramContext)
  {
    PackageManager localPackageManager = getPackageManager(paramContext);
    try
    {
      paramContext = localPackageManager.getPackageInfo(getPackageName(paramContext), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
  
  public static void installApk(Activity paramActivity, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setDataAndType(Uri.fromFile(new File(paramString)), "application/vnd.android.package-archive");
    paramActivity.startActivity(localIntent);
  }
}
