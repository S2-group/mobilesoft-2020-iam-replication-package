package com.qisi.plugin.m;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.qisi.plugin.b;
import com.qisi.plugin.manager.App;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.afpro.c.a.e;

public class d
{
  public static c a(Context paramContext)
  {
    paramContext = e.b(paramContext, "pref_key_installed_theme_packages", "");
    if (TextUtils.isEmpty(paramContext)) {
      return null;
    }
    try
    {
      paramContext = c.a(paramContext);
      return paramContext;
    }
    catch (JsonSyntaxException paramContext) {}
    return null;
  }
  
  public static List<ApplicationInfo> a()
  {
    localArrayList = new ArrayList();
    try
    {
      Iterator localIterator = App.a().getPackageManager().getInstalledApplications(128).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (localApplicationInfo != null)
        {
          String str = localApplicationInfo.packageName;
          if ((!TextUtils.isEmpty(str)) && ((TextUtils.indexOf(str, "com.ikeyboard.theme.") == 0) || (TextUtils.indexOf(str, "com.ikeyboard.emoji") == 0))) {
            localArrayList.add(localApplicationInfo);
          }
        }
      }
      return localArrayList;
    }
    catch (Exception localException) {}
  }
  
  public static void a(Context paramContext, c paramC)
  {
    paramC = paramC.a();
    if (!TextUtils.isEmpty(paramC)) {
      e.a(paramContext, "pref_key_installed_theme_packages", paramC);
    }
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    paramContext = a(paramContext);
    return (paramContext != null) && (paramContext.a != null) && (paramContext.a.size() > 0) && (((String)paramContext.a.get(0)).equals(paramString));
  }
  
  public static c b(Context paramContext)
  {
    if (!b.j.booleanValue()) {}
    do
    {
      return null;
      paramContext = e.b(paramContext, "pref_key_notification_enabled_pkgs", "");
    } while (TextUtils.isEmpty(paramContext));
    try
    {
      paramContext = c.a(paramContext);
      return paramContext;
    }
    catch (JsonSyntaxException paramContext) {}
    return null;
  }
  
  public static void b(Context paramContext, c paramC)
  {
    if (!b.j.booleanValue()) {}
    do
    {
      return;
      paramC = paramC.a();
    } while (TextUtils.isEmpty(paramC));
    e.a(paramContext, "pref_key_notification_enabled_pkgs", paramC);
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    if (!b.j.booleanValue()) {
      return false;
    }
    paramContext = a(paramContext);
    if ((paramContext != null) && (paramContext.a != null) && (paramContext.a.size() > 0) && (((String)paramContext.a.get(0)).equals(paramString))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static c c(Context paramContext)
  {
    if (!b.l.booleanValue()) {}
    do
    {
      return null;
      paramContext = e.b(paramContext, "pref_key_theme_store_enabled_pkgs", "");
    } while (TextUtils.isEmpty(paramContext));
    try
    {
      paramContext = c.a(paramContext);
      return paramContext;
    }
    catch (JsonSyntaxException paramContext) {}
    return null;
  }
  
  public static void c(Context paramContext, c paramC)
  {
    if (!b.l.booleanValue()) {}
    do
    {
      return;
      paramC = paramC.a();
    } while (TextUtils.isEmpty(paramC));
    e.a(paramContext, "pref_key_theme_store_enabled_pkgs", paramC);
  }
  
  public static void c(Context paramContext, String paramString)
  {
    if (!b.l.booleanValue()) {}
    while (TextUtils.isEmpty(paramString)) {
      return;
    }
    e.a(paramContext, "pref_key_theme_store_enabled_pkgs", paramString);
  }
  
  public static String d(Context paramContext)
  {
    if (!b.l.booleanValue()) {
      return "";
    }
    return e.b(paramContext, "pref_key_theme_store_active_pkg", "");
  }
  
  public static void d(Context paramContext, c paramC)
  {
    if (!b.l.booleanValue()) {
      return;
    }
    c(paramContext, paramC.a());
  }
  
  public static void d(Context paramContext, String paramString)
  {
    String str = paramString;
    if (paramString == null) {
      str = "";
    }
    e.a(paramContext, "pref_key_theme_store_active_pkg", str);
  }
  
  public static boolean e(Context paramContext)
  {
    if (!b.l.booleanValue()) {}
    while (TextUtils.isEmpty(e.b(paramContext, "pref_key_theme_store_active_pkg", ""))) {
      return false;
    }
    return true;
  }
  
  public static boolean e(Context paramContext, String paramString)
  {
    if (!b.l.booleanValue()) {
      return false;
    }
    return TextUtils.equals(e.b(paramContext, "pref_key_theme_store_active_pkg", ""), paramString);
  }
}
