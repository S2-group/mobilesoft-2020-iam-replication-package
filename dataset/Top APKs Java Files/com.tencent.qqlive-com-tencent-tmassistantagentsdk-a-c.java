package com.tencent.tmassistantagentsdk.a;

import android.content.ActivityNotFoundException;
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
import android.text.TextUtils;
import android.widget.Toast;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class c
{
  private static final String a = c.class.getName();
  private static Map b = new HashMap();
  
  public c() {}
  
  public static Intent a(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN", null);
    localIntent.addCategory("android.intent.category.LAUNCHER");
    localIntent.addFlags(1073741824);
    if (!b.containsKey(paramString))
    {
      paramContext = paramContext.getPackageManager().queryIntentActivities(localIntent, 0).iterator();
      while (paramContext.hasNext())
      {
        ResolveInfo localResolveInfo = (ResolveInfo)paramContext.next();
        if (!b.containsKey(localResolveInfo.activityInfo.applicationInfo.packageName)) {
          b.put(localResolveInfo.activityInfo.applicationInfo.packageName, localResolveInfo.activityInfo.name);
        }
      }
    }
    paramContext = (String)b.get(paramString);
    if (paramContext != null)
    {
      localIntent.setComponent(new ComponentName(paramString, paramContext));
      return localIntent;
    }
    return null;
  }
  
  public static List a(Context paramContext)
  {
    if (paramContext != null)
    {
      paramContext = paramContext.getPackageManager();
      if (paramContext != null)
      {
        e.b(a, "getInstalledPackages switch is open, will scan local packages");
        return paramContext.getInstalledPackages(0);
      }
    }
    e.b(a, "getInstalledPackages switch is closed, will not scan local packages");
    return null;
  }
  
  public static boolean a(String paramString, Context paramContext)
  {
    boolean bool = false;
    if (paramContext != null) {}
    try
    {
      paramContext.getPackageManager().getPackageInfo(paramString, 0);
      bool = true;
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  public static String b(String paramString, Context paramContext)
  {
    if (paramContext == null) {}
    for (;;)
    {
      return null;
      try
      {
        paramString = paramContext.getPackageManager().getPackageArchiveInfo(paramString, 1);
        if (paramString != null)
        {
          paramString = paramString.applicationInfo.packageName;
          return paramString;
        }
      }
      catch (Exception paramString)
      {
        e.e(a, "getApkName>>>" + paramString.getMessage());
      }
    }
    return null;
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    try
    {
      if (TextUtils.isEmpty(paramString)) {
        return false;
      }
      if (new File(paramString).exists())
      {
        Intent localIntent = new Intent("android.intent.action.VIEW");
        localIntent.setFlags(268435456);
        localIntent.setDataAndType(Uri.fromFile(new File(paramString)), "application/vnd.android.package-archive");
        paramContext.startActivity(localIntent);
        return true;
      }
    }
    catch (Exception paramContext)
    {
      e.e(a, "installApp>>>failed");
    }
    return false;
  }
  
  public static void c(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
      localIntent.putExtra("platformId", "qzone_m");
      e.c("add", ">>has add platformid=qzone_m");
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      new Intent();
      paramString = a(paramContext, paramString);
      if (paramString != null)
      {
        paramString.setAction("android.intent.action.MAIN");
        paramString.putExtra("platformId", "qzone_m");
        e.c("add", ">>has add platformid=qzone_m");
        try
        {
          paramContext.startActivity(paramString);
          return;
        }
        catch (ActivityNotFoundException paramString)
        {
          paramString.printStackTrace();
          Toast.makeText(paramContext, "无法启动该应用", 0).show();
          return;
        }
      }
      Toast.makeText(paramContext, "无法启动该应用", 0).show();
    }
  }
}
