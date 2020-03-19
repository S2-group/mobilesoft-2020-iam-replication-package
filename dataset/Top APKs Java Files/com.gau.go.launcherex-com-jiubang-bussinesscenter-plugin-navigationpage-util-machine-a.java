package com.jiubang.bussinesscenter.plugin.navigationpage.util.machine;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import com.jiubang.bussinesscenter.plugin.navigationpage.common.utils.a.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a
{
  public static List<ResolveInfo> a(Context paramContext)
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
  
  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    if (!i(paramContext, paramString1)) {
      f(paramContext, paramString2);
    }
  }
  
  public static boolean a(Context paramContext, Intent paramIntent)
  {
    if (paramContext != null) {}
    try
    {
      paramContext.startActivity(paramIntent);
      return true;
    }
    catch (ActivityNotFoundException paramContext)
    {
      c.d("matt", "saveStartActivity err " + paramContext.getMessage());
      return false;
    }
    catch (SecurityException paramContext)
    {
      for (;;)
      {
        c.d("matt", "saveStartActivity err " + paramContext.getMessage());
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        c.d("matt", "saveStartActivity err " + paramContext.getMessage());
      }
    }
  }
  
  public static boolean a(Context paramContext, String paramString)
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
  
  public static int b(Context paramContext, String paramString)
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
      c.d("NP", "Error :" + paramString + " is not exist.");
      return -1;
    }
    catch (Exception paramContext) {}
    return -1;
  }
  
  public static String c(Context paramContext, String paramString)
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
      c.d("NP", "Error :" + paramString + " is not exist.");
      return "";
    }
    catch (Exception paramContext) {}
    return "";
  }
  
  public static boolean d(Context paramContext, String paramString)
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
          PackageInfo localPackageInfo = e(paramContext, paramString);
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
      c.d("matt", "saveStartActivity err " + paramContext.getMessage());
      return false;
    }
    catch (SecurityException paramContext)
    {
      for (;;)
      {
        c.d("matt", "saveStartActivity err " + paramContext.getMessage());
      }
    }
  }
  
  public static PackageInfo e(Context paramContext, String paramString)
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
  
  public static boolean f(Context paramContext, String paramString)
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
      c.b("matt", "gotoBrowser error, uri = " + paramString);
      return false;
    }
    catch (Exception paramContext)
    {
      c.b("matt", "gotoBrowser error, uri = " + paramString);
    }
    return false;
  }
  
  public static Drawable g(Context paramContext, String paramString)
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
  
  public static String h(Context paramContext, String paramString)
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
  
  public static boolean i(Context paramContext, String paramString)
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
}
