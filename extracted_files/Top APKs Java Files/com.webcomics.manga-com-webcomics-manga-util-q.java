package com.webcomics.manga.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import com.webcomics.manga.App;
import com.webcomics.manga.activities.SplashActivity;
import java.util.Iterator;
import java.util.List;

public class q
{
  private static String a;
  
  public static int a()
  {
    try
    {
      int i = Settings.System.getInt(App.a().getContentResolver(), "screen_brightness");
      return i;
    }
    catch (Settings.SettingNotFoundException localSettingNotFoundException)
    {
      localSettingNotFoundException.printStackTrace();
    }
    return 200;
  }
  
  public static int a(Context paramContext)
  {
    paramContext = (WindowManager)paramContext.getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramContext.getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels;
  }
  
  public static String a(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return "";
    }
    for (;;)
    {
      int i;
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledPackages(8);
        if (paramContext == null) {
          return "";
        }
        paramContext = paramContext.iterator();
        if (paramContext.hasNext())
        {
          ProviderInfo[] arrayOfProviderInfo = ((PackageInfo)paramContext.next()).providers;
          if (arrayOfProviderInfo == null) {
            continue;
          }
          int j = arrayOfProviderInfo.length;
          i = 0;
          if (i >= j) {
            continue;
          }
          ProviderInfo localProviderInfo = arrayOfProviderInfo[i];
          if (!paramString.equals(localProviderInfo.readPermission)) {
            if (!paramString.equals(localProviderInfo.writePermission)) {
              break label122;
            }
          }
          paramContext = localProviderInfo.authority;
          return paramContext;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
      }
      return "";
      label122:
      i += 1;
    }
  }
  
  public static void a(float paramFloat, Window paramWindow)
  {
    WindowManager.LayoutParams localLayoutParams = paramWindow.getAttributes();
    localLayoutParams.alpha = paramFloat;
    paramWindow.setAttributes(localLayoutParams);
  }
  
  public static void a(Activity paramActivity)
  {
    if (d(paramActivity)) {
      return;
    }
    l.d("CommUtil", "addShortcut");
    Intent localIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("shortcut = ");
    ((StringBuilder)localObject).append(localIntent);
    l.d("CommUtil", ((StringBuilder)localObject).toString());
    localIntent.putExtra("android.intent.extra.shortcut.NAME", paramActivity.getResources().getString(2131689586));
    localIntent.putExtra("duplicate", false);
    localObject = new ComponentName(paramActivity.getPackageName(), SplashActivity.class.getName());
    localIntent.putExtra("android.intent.extra.shortcut.INTENT", new Intent("android.intent.action.MAIN").setComponent((ComponentName)localObject).addCategory("android.intent.category.LAUNCHER"));
    localIntent.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", Intent.ShortcutIconResource.fromContext(paramActivity, 2131492864));
    paramActivity.sendBroadcast(localIntent);
  }
  
  public static int b(Context paramContext)
  {
    paramContext = (WindowManager)paramContext.getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramContext.getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.heightPixels;
  }
  
  public static int c(Context paramContext)
  {
    int i = f.a(paramContext, 24.0F);
    int j = paramContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (j > 0) {
      i = paramContext.getResources().getDimensionPixelSize(j);
    }
    return i;
  }
  
  public static boolean d(Context paramContext)
  {
    boolean bool2 = false;
    for (;;)
    {
      Object localObject1;
      int i;
      try
      {
        localContentResolver = paramContext.getContentResolver();
        localObject1 = e(paramContext);
        if (localObject1 != null)
        {
          localObject2 = localObject1;
          if (!((String)localObject1).trim().equals("")) {}
        }
        else
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append(f(paramContext));
          ((StringBuilder)localObject1).append(".permission.READ_SETTINGS");
          localObject2 = a(paramContext, ((StringBuilder)localObject1).toString());
        }
        localObject1 = localObject2;
        if (TextUtils.isEmpty((CharSequence)localObject2))
        {
          i = Build.VERSION.SDK_INT;
          if (i >= 8) {
            break label295;
          }
          localObject1 = "com.android.launcher.settings";
        }
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("content://");
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append("/favorites?notify=true");
        localObject2 = Uri.parse(((StringBuilder)localObject2).toString());
        localObject1 = null;
      }
      catch (Exception paramContext)
      {
        ContentResolver localContentResolver;
        Object localObject2;
        paramContext.printStackTrace();
        bool1 = bool2;
      }
      for (;;)
      {
        try
        {
          paramContext = paramContext.getResources().getString(2131689586).trim();
          paramContext = localContentResolver.query((Uri)localObject2, new String[] { "title", "intent" }, "title=?", new String[] { paramContext }, null);
          bool1 = bool2;
          if (paramContext == null) {}
        }
        catch (Exception paramContext)
        {
          paramContext = (Context)localObject1;
          break;
        }
        try
        {
          i = paramContext.getCount();
          bool1 = bool2;
          if (i <= 0) {
            continue;
          }
          bool1 = true;
        }
        catch (Exception localException)
        {
          break;
        }
      }
      boolean bool1 = bool2;
      if (paramContext != null)
      {
        paramContext.close();
        bool1 = bool2;
      }
      paramContext = new StringBuilder();
      paramContext.append("hasShortcut = ");
      paramContext.append(bool1);
      l.d("CommUtil", paramContext.toString());
      return bool1;
      label295:
      String str;
      if (i < 19) {
        str = "com.android.launcher2.settings";
      } else {
        str = "com.android.launcher3.settings";
      }
    }
  }
  
  public static String e(Context paramContext)
  {
    if (TextUtils.isEmpty(a)) {
      a = a(paramContext, "com.android.launcher.permission.READ_SETTINGS");
    }
    return a;
  }
  
  public static String f(Context paramContext)
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.HOME");
    paramContext = paramContext.getPackageManager().resolveActivity(localIntent, 0);
    if ((paramContext != null) && (paramContext.activityInfo != null))
    {
      if (paramContext.activityInfo.packageName.equals("android")) {
        return "";
      }
      if (paramContext.activityInfo.packageName.equals("com.google.android.googlequicksearchbox")) {
        return "com.google.android.launcher";
      }
      return paramContext.activityInfo.packageName;
    }
    return "";
  }
}
