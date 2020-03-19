package com.qisi.plugin.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.bluelinelabs.logansquare.LoganSquare;
import com.google.gson.JsonSyntaxException;
import com.qisi.plugin.BuildConfig;
import com.qisi.plugin.manager.App;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.afpro.lockerbase.utils.SharedPreferencesUtils;

public class ThemePackagesUtil
{
  public static String getActivatedThemeStorePkg(Context paramContext)
  {
    if (!BuildConfig.SHOW_THEME_STORE.booleanValue()) {
      return "";
    }
    return SharedPreferencesUtils.getString(paramContext, "pref_key_theme_store_active_pkg", "");
  }
  
  public static PackagesInstalled getInstalledThemePackages(Context paramContext)
  {
    paramContext = SharedPreferencesUtils.getString(paramContext, "pref_key_installed_theme_packages", "");
    if (TextUtils.isEmpty(paramContext)) {
      return null;
    }
    try
    {
      paramContext = (PackagesInstalled)LoganSquare.parse(paramContext, PackagesInstalled.class);
      return paramContext;
    }
    catch (JsonSyntaxException paramContext)
    {
      return null;
    }
    catch (IOException paramContext)
    {
      for (;;) {}
    }
  }
  
  public static PackagesInstalled getNotificationToolEnabledPackages(Context paramContext)
  {
    if (!BuildConfig.SHOW_NOTIFICATION.booleanValue()) {
      return null;
    }
    paramContext = SharedPreferencesUtils.getString(paramContext, "pref_key_notification_enabled_pkgs", "");
    if (TextUtils.isEmpty(paramContext)) {
      return null;
    }
    try
    {
      paramContext = (PackagesInstalled)LoganSquare.parse(paramContext, PackagesInstalled.class);
      return paramContext;
    }
    catch (JsonSyntaxException paramContext)
    {
      return null;
    }
    catch (IOException paramContext)
    {
      for (;;) {}
    }
  }
  
  public static List<ApplicationInfo> getThemeAppsFromPM()
  {
    localArrayList = new ArrayList();
    try
    {
      Iterator localIterator = App.getContext().getPackageManager().getInstalledApplications(128).iterator();
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
  
  public static PackagesInstalled getThemeStoreEnabledPackages(Context paramContext)
  {
    if (!BuildConfig.SHOW_THEME_STORE.booleanValue()) {
      return null;
    }
    paramContext = SharedPreferencesUtils.getString(paramContext, "pref_key_theme_store_enabled_pkgs", "");
    if (TextUtils.isEmpty(paramContext)) {
      return null;
    }
    try
    {
      paramContext = (PackagesInstalled)LoganSquare.parse(paramContext, PackagesInstalled.class);
      return paramContext;
    }
    catch (JsonSyntaxException paramContext)
    {
      return null;
    }
    catch (IOException paramContext)
    {
      for (;;) {}
    }
  }
  
  public static boolean hasActivatedThemeStorePkg(Context paramContext)
  {
    if (!BuildConfig.SHOW_THEME_STORE.booleanValue()) {}
    while (TextUtils.isEmpty(SharedPreferencesUtils.getString(paramContext, "pref_key_theme_store_active_pkg", ""))) {
      return false;
    }
    return true;
  }
  
  public static boolean isActivated(Context paramContext, String paramString)
  {
    paramContext = getInstalledThemePackages(paramContext);
    return (paramContext != null) && (paramContext.packages != null) && (paramContext.packages.size() > 0) && (((String)paramContext.packages.get(0)).equals(paramString));
  }
  
  public static boolean isActivatedNotificationToolEnabledTheme(Context paramContext, String paramString)
  {
    if (!BuildConfig.SHOW_NOTIFICATION.booleanValue()) {
      return false;
    }
    paramContext = getInstalledThemePackages(paramContext);
    if ((paramContext != null) && (paramContext.packages != null) && (paramContext.packages.size() > 0) && (((String)paramContext.packages.get(0)).equals(paramString))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean isActivatedThemeStorePkg(Context paramContext, String paramString)
  {
    if (!BuildConfig.SHOW_THEME_STORE.booleanValue()) {
      return false;
    }
    return TextUtils.equals(SharedPreferencesUtils.getString(paramContext, "pref_key_theme_store_active_pkg", ""), paramString);
  }
  
  public static void saveInstalledTheme(Context paramContext, PackagesInstalled paramPackagesInstalled)
  {
    String str = "";
    try
    {
      paramPackagesInstalled = LoganSquare.serialize(paramPackagesInstalled);
      if (!TextUtils.isEmpty(paramPackagesInstalled)) {
        SharedPreferencesUtils.setString(paramContext, "pref_key_installed_theme_packages", paramPackagesInstalled);
      }
      return;
    }
    catch (IOException paramPackagesInstalled)
    {
      for (;;)
      {
        paramPackagesInstalled = str;
      }
    }
  }
  
  public static void saveNotificationToolEnabledTheme(Context paramContext, PackagesInstalled paramPackagesInstalled)
  {
    if (!BuildConfig.SHOW_NOTIFICATION.booleanValue()) {}
    for (;;)
    {
      return;
      String str = "";
      try
      {
        paramPackagesInstalled = LoganSquare.serialize(paramPackagesInstalled);
        if (TextUtils.isEmpty(paramPackagesInstalled)) {
          continue;
        }
        SharedPreferencesUtils.setString(paramContext, "pref_key_notification_enabled_pkgs", paramPackagesInstalled);
        return;
      }
      catch (IOException paramPackagesInstalled)
      {
        for (;;)
        {
          paramPackagesInstalled = str;
        }
      }
    }
  }
  
  public static void saveThemeStoreEnabledPackages(Context paramContext, PackagesInstalled paramPackagesInstalled)
  {
    if (!BuildConfig.SHOW_THEME_STORE.booleanValue()) {}
    for (;;)
    {
      return;
      String str = "";
      try
      {
        paramPackagesInstalled = LoganSquare.serialize(paramPackagesInstalled);
        if (TextUtils.isEmpty(paramPackagesInstalled)) {
          continue;
        }
        SharedPreferencesUtils.setString(paramContext, "pref_key_theme_store_enabled_pkgs", paramPackagesInstalled);
        return;
      }
      catch (IOException paramPackagesInstalled)
      {
        for (;;)
        {
          paramPackagesInstalled = str;
        }
      }
    }
  }
  
  public static void saveThemeStoreEnabledTheme(Context paramContext, PackagesInstalled paramPackagesInstalled)
  {
    if (!BuildConfig.SHOW_THEME_STORE.booleanValue()) {
      return;
    }
    try
    {
      paramPackagesInstalled = LoganSquare.serialize(paramPackagesInstalled);
      saveThemeStoreEnabledTheme(paramContext, paramPackagesInstalled);
      return;
    }
    catch (Exception paramPackagesInstalled)
    {
      for (;;)
      {
        paramPackagesInstalled = null;
      }
    }
  }
  
  public static void saveThemeStoreEnabledTheme(Context paramContext, String paramString)
  {
    if (!BuildConfig.SHOW_THEME_STORE.booleanValue()) {}
    while (TextUtils.isEmpty(paramString)) {
      return;
    }
    SharedPreferencesUtils.setString(paramContext, "pref_key_theme_store_enabled_pkgs", paramString);
  }
  
  public static void setActiveThemeStoreTheme(Context paramContext, String paramString)
  {
    String str = paramString;
    if (paramString == null) {
      str = "";
    }
    SharedPreferencesUtils.setString(paramContext, "pref_key_theme_store_active_pkg", str);
  }
}
