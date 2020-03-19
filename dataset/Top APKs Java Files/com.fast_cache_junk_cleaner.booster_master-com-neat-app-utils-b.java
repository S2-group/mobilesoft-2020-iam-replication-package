package com.neat.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class b
{
  public static final HashSet<String> a = new HashSet()
  {
    private static final long serialVersionUID = 1L;
  };
  private static final HashSet<String> b = new HashSet()
  {
    private static final long serialVersionUID = 1L;
  };
  
  public static Drawable a(Context paramContext, String paramString)
  {
    try
    {
      try
      {
        localObject = paramContext.getApplicationContext().getPackageManager();
        ApplicationInfo localApplicationInfo;
        paramString = null;
      }
      finally
      {
        try
        {
          localApplicationInfo = ((PackageManager)localObject).getApplicationInfo(paramString, 0);
          paramString = (String)localObject;
          localObject = localApplicationInfo;
        }
        catch (PackageManager.NameNotFoundException paramString)
        {
          for (;;)
          {
            Object localObject;
            paramString = (String)localObject;
          }
        }
        paramContext = finally;
        break label59;
      }
    }
    catch (PackageManager.NameNotFoundException paramString)
    {
      for (;;) {}
    }
    catch (Exception paramString)
    {
      label59:
      for (;;) {}
    }
    localObject = null;
    paramString = paramString.getApplicationIcon((ApplicationInfo)localObject);
    return paramString;
    paramContext = paramContext.getResources().getDrawable(17301651);
    return paramContext;
    throw paramContext;
  }
  
  public static List<com.neat.batterysaver.b.a> a(Context paramContext)
  {
    try
    {
      ArrayList localArrayList = new ArrayList();
      int i;
      List localList;
      com.neat.batterysaver.b.a localA;
      return localArrayList;
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        try
        {
          paramContext = paramContext.getPackageManager();
          i = 0;
          localList = paramContext.getInstalledPackages(0);
          paramContext = localArrayList;
          if (i < localList.size())
          {
            paramContext = (PackageInfo)localList.get(i);
            if (((paramContext.applicationInfo.flags & 0x1) == 0) && ((paramContext.applicationInfo.flags & 0x80) == 0) && ((paramContext.applicationInfo.flags & 0x200000) == 0))
            {
              localA = new com.neat.batterysaver.b.a();
              localA.a = paramContext.packageName;
              localArrayList.add(localA);
            }
            i += 1;
            continue;
            paramContext = null;
          }
          return paramContext;
        }
        catch (Exception paramContext) {}
        paramContext = paramContext;
      }
    }
  }
  
  public static void a(Activity paramActivity)
  {
    Intent localIntent = new Intent();
    localIntent.addFlags(268435456);
    localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
    localIntent.setData(Uri.fromParts("package", paramActivity.getPackageName(), null));
    paramActivity.startActivity(localIntent);
  }
  
  public static void a(Activity paramActivity, String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setFlags(536936448);
    if (Build.VERSION.SDK_INT >= 9)
    {
      localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
      localIntent.setData(Uri.fromParts("package", paramString, null));
    }
    paramActivity.startActivityForResult(localIntent, 1024);
    paramActivity.overridePendingTransition(0, 0);
  }
  
  public static Set<String> b(Context paramContext)
  {
    paramContext = c(paramContext);
    paramContext.addAll(b);
    return paramContext;
  }
  
  public static Set<String> c(Context paramContext)
  {
    HashSet localHashSet = new HashSet();
    localHashSet.addAll(com.neat.phoneboost.a.a.c());
    localHashSet.addAll(com.neat.phoneboost.a.a.b());
    String str = d(paramContext);
    if (str != null) {
      localHashSet.add(str);
    }
    try
    {
      paramContext = ((InputMethodManager)paramContext.getSystemService("input_method")).getInputMethodList();
      if (paramContext != null)
      {
        int i = 0;
        while (i < paramContext.size())
        {
          localHashSet.add(((InputMethodInfo)paramContext.get(i)).getPackageName());
          i += 1;
        }
      }
      return localHashSet;
    }
    catch (Exception paramContext) {}
    return localHashSet;
  }
  
  public static String d(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 0);
    if (paramContext != null)
    {
      if (paramContext.activityInfo == null) {
        return null;
      }
      if (paramContext.activityInfo.packageName.equals("android")) {
        return null;
      }
      return paramContext.activityInfo.packageName;
    }
    return null;
  }
  
  public static Set<String> e(Context paramContext)
  {
    return new HashSet(com.neat.phoneboost.a.a.a(paramContext).a());
  }
}
