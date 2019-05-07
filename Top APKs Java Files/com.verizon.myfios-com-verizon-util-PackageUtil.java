package com.verizon.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import java.util.List;

public class PackageUtil
{
  public PackageUtil() {}
  
  public static Intent getLaunchIntent(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.setPackage(paramString);
    localIntent.addCategory("android.intent.category.LAUNCHER");
    paramContext = paramContext.queryIntentActivities(localIntent, 0);
    if ((paramContext == null) || (paramContext.size() == 0)) {
      return null;
    }
    localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.LAUNCHER");
    localIntent.setPackage(paramString);
    localIntent.setClassName(paramString, ((ResolveInfo)paramContext.get(0)).activityInfo.name);
    localIntent.addFlags(268435456);
    localIntent.addFlags(67108864);
    return localIntent;
  }
  
  public static Intent getMarketIntent(Uri paramUri)
  {
    paramUri = new Intent("android.intent.action.VIEW", paramUri);
    paramUri.addFlags(67108864);
    return paramUri;
  }
  
  public static Intent getMarketIntent(String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(String.format("market://details?id=%s", new Object[] { paramString })));
    paramString.addFlags(67108864);
    return paramString;
  }
  
  public static Intent getMarketSearchIntent(String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(String.format("market://search?q=%s", new Object[] { paramString })));
    paramString.addFlags(67108864);
    return paramString;
  }
  
  public static PackageInfo getPackageInfo(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return null;
  }
  
  public static String getPackageName(Context paramContext)
  {
    return paramContext.getPackageName();
  }
  
  static String installedSource(Context paramContext, String paramString)
  {
    return paramContext.getPackageManager().getInstallerPackageName(paramString);
  }
  
  public static int installedVersionCode(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      if (i >= paramContext.size()) {
        return -1;
      }
      PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
      if (localPackageInfo.packageName.equals(paramString)) {
        return localPackageInfo.versionCode;
      }
      i += 1;
    }
  }
  
  public static String installedVersionName(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      if (i >= paramContext.size()) {
        return "unknown";
      }
      PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
      if (localPackageInfo.packageName.equals(paramString)) {
        return localPackageInfo.versionName;
      }
      i += 1;
    }
  }
  
  public static boolean isInstalled(Context paramContext, String paramString)
  {
    return paramContext.getPackageManager().getLaunchIntentForPackage(paramString) != null;
  }
  
  public static boolean isIntentAvailable(Context paramContext, Intent paramIntent)
  {
    return paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536).size() > 0;
  }
  
  public static boolean isIntentAvailable(Context paramContext, Uri paramUri)
  {
    return isIntentAvailable(paramContext, new Intent("android.intent.action.VIEW", paramUri));
  }
}
