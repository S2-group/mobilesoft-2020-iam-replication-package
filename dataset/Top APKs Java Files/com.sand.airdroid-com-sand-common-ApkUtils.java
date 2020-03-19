package com.sand.common;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build.VERSION;
import com.sand.media.image.ImageUtils;
import java.io.File;
import java.util.List;

public class ApkUtils
{
  public ApkUtils() {}
  
  public static byte[] getApkPackageIcon(Context paramContext, String paramString)
  {
    PackageInfo localPackageInfo;
    if (FileHelper.isExists(paramString))
    {
      paramContext = paramContext.getPackageManager();
      localPackageInfo = paramContext.getPackageArchiveInfo(paramString, 0);
      if (localPackageInfo != null) {}
    }
    else
    {
      return null;
    }
    if (Build.VERSION.SDK_INT >= 8)
    {
      localPackageInfo.applicationInfo.sourceDir = paramString;
      localPackageInfo.applicationInfo.publicSourceDir = paramString;
    }
    return ImageUtils.a(paramContext.getApplicationIcon(localPackageInfo.applicationInfo));
  }
  
  public static String getApkPackageName(Context paramContext, String paramString)
  {
    try
    {
      if (FileHelper.isExists(paramString))
      {
        paramContext = paramContext.getPackageManager().getPackageArchiveInfo(paramString, 0).packageName;
        return paramContext;
      }
    }
    catch (Exception paramContext) {}
    return "";
  }
  
  public static int getAppCount(Context paramContext)
  {
    return paramContext.getPackageManager().getInstalledPackages(0).size();
  }
  
  @Deprecated
  public static byte[] getAppIcon(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getApplicationIcon(paramString);
      if (paramContext == null) {
        return null;
      }
      paramContext = ImageUtils.a(paramContext);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      new StringBuilder("App: ").append(paramString).append("is not exit.");
    }
    return null;
  }
  
  public static String getAppVersionName(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramString, 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "unknown";
  }
  
  public static boolean install(Context paramContext, String paramString)
  {
    if (!FileHelper.isExists(paramString)) {
      return false;
    }
    try
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setDataAndType(Uri.fromFile(new File(paramString)), "application/vnd.android.package-archive");
      localIntent.addFlags(268435456);
      paramContext.startActivity(localIntent);
      return true;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean isPackageInstalled(Context paramContext, String paramString, int paramInt)
  {
    boolean bool = false;
    paramContext = paramContext.getPackageManager();
    try
    {
      int i = paramContext.getPackageInfo(paramString, 0).versionCode;
      if ((i == paramInt) || (paramInt == -1)) {
        bool = true;
      }
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean uninstall(Context paramContext, String paramString)
  {
    if (!isPackageInstalled(paramContext, paramString, -1)) {
      return false;
    }
    paramString = new Intent("android.intent.action.DELETE", Uri.parse("package:" + paramString));
    paramString.addFlags(268435456);
    paramContext.startActivity(paramString);
    return true;
  }
}
