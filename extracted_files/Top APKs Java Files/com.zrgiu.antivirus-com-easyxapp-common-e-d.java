package com.easyxapp.common.e;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.easyxapp.CommonDefine;
import com.easyxapp.xp.common.util.i;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class d
{
  public static List a = null;
  
  public static long a(PackageManager paramPackageManager, String paramString)
  {
    try
    {
      paramPackageManager = paramPackageManager.getPackageInfo(paramString, 0);
      long l = PackageInfo.class.getField("firstInstallTime").getLong(paramPackageManager);
      return l;
    }
    catch (Exception paramPackageManager)
    {
      i.e(paramPackageManager);
    }
    return 0L;
  }
  
  public static Drawable a(Context paramContext, String paramString)
  {
    Iterator localIterator = e(paramContext).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (localPackageInfo.packageName.equals(paramString)) {
        return localPackageInfo.applicationInfo.loadIcon(paramContext.getPackageManager());
      }
    }
    return null;
  }
  
  public static List a(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = e(paramContext).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if ((localPackageInfo.applicationInfo.flags & 0x1) <= 0)
      {
        i.d("appOnPhone" + localPackageInfo.packageName);
        localArrayList.add(localPackageInfo);
      }
    }
    return localArrayList;
  }
  
  public static boolean a(Context paramContext)
  {
    paramContext = e(paramContext);
    int j = paramContext.size();
    int i = 0;
    while (i < j)
    {
      if ("com.android.vending".equalsIgnoreCase(((PackageInfo)paramContext.get(i)).packageName)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static long b(PackageManager paramPackageManager, String paramString)
  {
    long l = 0L;
    try
    {
      paramPackageManager = new File(paramPackageManager.getApplicationInfo(paramString, 0).sourceDir);
      if (paramPackageManager.exists()) {
        l = paramPackageManager.lastModified();
      }
      return l;
    }
    catch (PackageManager.NameNotFoundException paramPackageManager) {}
    return 0L;
  }
  
  public static void b(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.DELETE");
    localIntent.setFlags(268435456);
    localIntent.setData(Uri.parse("package:" + paramContext.getPackageName()));
    paramContext.startActivity(localIntent);
  }
  
  public static void b(Context paramContext, String paramString)
  {
    try
    {
      if (paramString.startsWith("market:"))
      {
        if (a(paramContext)) {
          e(paramContext, paramString);
        }
      }
      else
      {
        f(paramContext, paramString);
        return;
      }
    }
    catch (Exception paramContext)
    {
      i.e(paramContext);
    }
  }
  
  public static void c(Context paramContext)
  {
    String str = paramContext.getPackageName();
    if (a(paramContext))
    {
      i.b("download " + str + " from GP");
      e(paramContext, "market://details?id=" + str);
      return;
    }
    i.b("download " + str + " by browser");
    f(paramContext, CommonDefine.DOWNLOAD_APK_URL + "?id=" + str);
  }
  
  public static void c(Context paramContext, String paramString)
  {
    if ((paramString == null) || (paramString.length() <= 0)) {
      return;
    }
    paramContext.startActivity(paramContext.getPackageManager().getLaunchIntentForPackage(paramString));
  }
  
  public static String d(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      i.e(paramContext);
    }
    return "1.0";
  }
  
  public static void d(Context paramContext, String paramString)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    Object localObject = null;
    try
    {
      paramString = localPackageManager.getLaunchIntentForPackage(paramString);
      if (paramString != null)
      {
        paramString.addFlags(268435456);
        paramContext.startActivity(paramString);
      }
      return;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        i.e(paramString);
        paramString = localObject;
      }
    }
  }
  
  private static List e(Context paramContext)
  {
    if (a == null) {}
    try
    {
      a = paramContext.getPackageManager().getInstalledPackages(0);
      return a;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        a = new ArrayList();
        i.e(paramContext);
      }
    }
  }
  
  private static void e(Context paramContext, String paramString)
  {
    try
    {
      paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
      Iterator localIterator = paramContext.getPackageManager().queryIntentActivities(paramString, 0).iterator();
      while (localIterator.hasNext())
      {
        ResolveInfo localResolveInfo = (ResolveInfo)localIterator.next();
        if (localResolveInfo.activityInfo.packageName.equals("com.android.vending"))
        {
          paramString.setComponent(new ComponentName("com.android.vending", localResolveInfo.activityInfo.name));
          paramString.addFlags(268435456);
          paramContext.getApplicationContext().startActivity(paramString);
        }
      }
      return;
    }
    catch (Exception paramContext)
    {
      i.e(paramContext);
    }
  }
  
  private static void f(Context paramContext, String paramString)
  {
    Intent localIntent;
    try
    {
      Object localObject2 = Uri.parse(paramString);
      localIntent = new Intent("android.intent.action.VIEW", (Uri)localObject2);
      Object localObject1 = paramContext.getPackageManager();
      localObject2 = ((PackageManager)localObject1).resolveActivity(new Intent("android.intent.action.VIEW", (Uri)localObject2), 65536);
      if ((localObject2 != null) && (!"android".equals(((ResolveInfo)localObject2).activityInfo.packageName)))
      {
        localIntent.setComponent(new ComponentName(((ResolveInfo)localObject2).activityInfo.packageName, ((ResolveInfo)localObject2).activityInfo.name));
        localIntent.addFlags(268435456);
        paramContext.startActivity(localIntent);
        i.b("Open default browser: " + paramString);
        return;
      }
      localObject1 = ((PackageManager)localObject1).queryIntentActivities(localIntent, 0).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ResolveInfo)((Iterator)localObject1).next();
        if (((ResolveInfo)localObject2).activityInfo.packageName.equals("com.android.browser"))
        {
          localIntent.setComponent(new ComponentName("com.android.browser", ((ResolveInfo)localObject2).activityInfo.name));
          localIntent.addFlags(268435456);
          paramContext.startActivity(localIntent);
          i.b("Open system browser: " + paramString);
          return;
        }
      }
    }
    catch (Exception paramContext)
    {
      i.c(paramContext);
      return;
    }
    localIntent.setFlags(268435456);
    paramContext.startActivity(localIntent);
    i.b("Open browser: " + paramString);
  }
}
