package com.cmcm.newsdetailssdk.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.cmcm.newssdk.onews.model.ONews;
import java.util.Iterator;
import java.util.List;

public class e
{
  private static final int[] a = { 6, 5, 4 };
  private static String b;
  private static String c = "com.mobilesrepublic.appy";
  
  private static String a(ONews paramONews)
  {
    return "newsrepublic://news?contentid=" + paramONews.contentid() + "&action=0x02";
  }
  
  public static void a(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setFlags(268435456);
    localIntent.setClassName("com.android.vending", "com.google.android.finsky.activities.MainActivity");
    if (!TextUtils.isEmpty(b)) {
      localIntent.setData(Uri.parse(b));
    }
    while (b(paramContext, localIntent))
    {
      return;
      localIntent.setData(Uri.parse("market://details?id=" + c));
    }
    localIntent = new Intent();
    if (!TextUtils.isEmpty(b)) {
      localIntent.setData(Uri.parse(b));
    }
    for (;;)
    {
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.setFlags(268435456);
      a(paramContext, localIntent);
      return;
      localIntent.setData(Uri.parse("market://details?id=" + c));
    }
  }
  
  public static void a(Context paramContext, Intent paramIntent)
  {
    Object localObject1 = b(paramContext, paramIntent.getDataString());
    if ((localObject1 == null) || (((List)localObject1).size() <= 0))
    {
      b(paramContext, paramIntent);
      return;
    }
    Object localObject2 = ((List)localObject1).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject2).next();
      if ("com.android.chrome".equals(localResolveInfo.activityInfo.packageName))
      {
        paramIntent.setPackage(localResolveInfo.activityInfo.packageName);
        paramIntent.setClassName(localResolveInfo.activityInfo.packageName, localResolveInfo.activityInfo.name);
        b(paramContext, paramIntent);
        return;
      }
    }
    localObject1 = ((List)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (ResolveInfo)((Iterator)localObject1).next();
      if (a(((ResolveInfo)localObject2).activityInfo.applicationInfo))
      {
        paramIntent.setPackage(((ResolveInfo)localObject2).activityInfo.packageName);
        paramIntent.setClassName(((ResolveInfo)localObject2).activityInfo.packageName, ((ResolveInfo)localObject2).activityInfo.name);
        b(paramContext, paramIntent);
        return;
      }
    }
    b(paramContext, paramIntent);
  }
  
  public static void a(Context paramContext, ONews paramONews)
  {
    if (a(paramContext, "com.mobilesrepublic.appy"))
    {
      paramONews = a(paramONews);
      if (!TextUtils.isEmpty(paramONews))
      {
        paramONews = new Intent("android.intent.action.VIEW", Uri.parse(paramONews));
        paramONews.addCategory("android.intent.category.BROWSABLE");
        paramONews.addCategory("android.intent.category.DEFAULT");
        paramONews.setFlags(268435456);
        if (b(paramContext, paramONews)) {
          return;
        }
      }
    }
    c = "com.mobilesrepublic.appy";
    a(paramContext);
  }
  
  public static void a(String paramString)
  {
    b = paramString;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < paramContext.size())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
        if ((localPackageInfo == null) || (!localPackageInfo.packageName.equalsIgnoreCase(paramString))) {
          break label79;
        }
        bool1 = bool2;
        if (a((PackageInfo)paramContext.get(i))) {
          bool1 = true;
        }
      }
      return bool1;
      label79:
      i += 1;
    }
  }
  
  public static boolean a(ApplicationInfo paramApplicationInfo)
  {
    if (paramApplicationInfo == null) {}
    while (((paramApplicationInfo.flags & 0x1) == 0) && ((paramApplicationInfo.flags & 0x80) == 0)) {
      return false;
    }
    return true;
  }
  
  private static boolean a(PackageInfo paramPackageInfo)
  {
    boolean bool2 = false;
    String str = paramPackageInfo.versionName;
    boolean bool1;
    if ((TextUtils.isEmpty(str)) || (paramPackageInfo == null))
    {
      bool1 = true;
      return bool1;
    }
    paramPackageInfo = str.split("\\.");
    if (paramPackageInfo != null)
    {
      int i = 0;
      for (;;)
      {
        if ((i >= paramPackageInfo.length) || (i >= a.length)) {
          break label94;
        }
        str = paramPackageInfo[i];
        int j = a[i];
        if (Integer.parseInt(str) > j) {
          return true;
        }
        bool1 = bool2;
        if (Integer.parseInt(str) < j) {
          break;
        }
        i += 1;
      }
    }
    label94:
    return true;
  }
  
  public static List<ResolveInfo> b(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    Object localObject = new Intent("android.intent.action.VIEW");
    ((Intent)localObject).addCategory("android.intent.category.BROWSABLE");
    ((Intent)localObject).setData(Uri.parse(paramString));
    paramContext = paramContext.queryIntentActivities((Intent)localObject, 65536);
    if ((paramContext == null) || (paramContext.size() <= 0)) {
      return paramContext;
    }
    paramString = (ResolveInfo)paramContext.get(0);
    localObject = paramContext.iterator();
    while (((Iterator)localObject).hasNext())
    {
      ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
      if ((paramString.priority != localResolveInfo.priority) || (paramString.isDefault != localResolveInfo.isDefault)) {
        paramContext.remove(localResolveInfo);
      }
    }
    return paramContext;
  }
  
  public static void b(String paramString)
  {
    c = paramString;
  }
  
  public static boolean b(Context paramContext, Intent paramIntent)
  {
    try
    {
      if (!(paramContext instanceof Activity)) {
        paramIntent.addFlags(268435456);
      }
      paramContext.startActivity(paramIntent);
      return true;
    }
    catch (Exception paramContext) {}
    return false;
  }
}
