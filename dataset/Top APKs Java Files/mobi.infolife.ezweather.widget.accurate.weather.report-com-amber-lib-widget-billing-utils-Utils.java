package com.amber.lib.widget.billing.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.util.List;

public class Utils
{
  public static final String AMBER_WEATHER_PACKAGE_NAME = "mobi.infolife.ezweather";
  public static final String APPLY_WIDGET_INTENT = "store.apply.widget";
  public static final String CAMPAIGN_STORE = "store";
  public static final String EZWEATHER_PLUGIN = "EZWEATHER_PLUGIN";
  public static final String LOCKER_SKIN = "LOCKER_SKIN";
  public static final String META_DATA_VALUE = "mobi.infolife.ezweather.plugin.widgetskin";
  public static final String PLUGIN_WIDGET = "amber_widget";
  public static final String PLUGIN_WIDGET_THEME = "amber_widget_theme";
  public static final String PLUGIN_WIDGET_USING_WIDGET = "amber_widget_using_widget";
  public static final String THEME_PACKAGE_NAME_EXTRA = "theme_package_name";
  public static final String TYPE_ARRAY = "array";
  public static final String TYPE_BOOL = "bool";
  public static final String TYPE_COLOR = "color";
  public static final String TYPE_DRAWABLE = "drawable";
  public static final String TYPE_ID = "id";
  public static final String TYPE_INTEGER = "integer";
  public static final String TYPE_LAYOUT = "layout";
  public static final String TYPE_STRING = "string";
  public static final String WHATS_APP_PACKAGE_NAME = "com.whatsapp";
  
  public Utils() {}
  
  public static boolean checkAppInstalled(Context paramContext, String paramString)
  {
    try
    {
      paramContext.getPackageManager().getApplicationInfo(paramString, 0);
      return true;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static int dip2px(Context paramContext, int paramInt)
  {
    float f = paramContext.getResources().getDisplayMetrics().density;
    return (int)(paramInt * f + 0.5F);
  }
  
  public static List<PackageInfo> getInstalledAppList(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext = paramContext.getInstalledPackages(paramInt);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return null;
    }
    catch (OutOfMemoryError paramContext)
    {
      for (;;) {}
    }
  }
  
  public static Context getOtherAppContext(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getApplicationContext().createPackageContext(paramString, 3);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static int getResourceId(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      int i = paramContext.getResourcesForApplication(paramString3).getIdentifier(paramString1, paramString2, paramString3);
      return i;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static int getWidthDimen(Context paramContext)
  {
    try
    {
      int i = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getWidth();
      if (i > 720) {
        return 4;
      }
      if (i > 480) {
        return 3;
      }
      if (i > 320) {
        return 2;
      }
      if (i > 240) {
        return 1;
      }
      if (i <= 240) {
        return 1;
      }
      return 5;
    }
    catch (RuntimeException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 5;
  }
  
  public static boolean isAppExist(Context paramContext, String paramString)
  {
    if ((paramString == null) || ("".equals(paramString))) {
      return false;
    }
    try
    {
      paramContext.getPackageManager().getApplicationInfo(paramString, 8192);
      return true;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean isMulWidget(Context paramContext, String paramString)
  {
    try
    {
      boolean bool = paramContext.getPackageManager().getApplicationInfo(paramString, 128).metaData.getBoolean("MUL_WIDGET");
      return bool;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean isNewUserInStore(Context paramContext)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramContext.getSharedPreferences("mul_widget", 0).getInt("weather_activity_open_count", 0) <= 2)
    {
      bool1 = bool2;
      if (System.currentTimeMillis() - paramContext.getSharedPreferences("mul_world", 0).getLong("first_open_time", -1L) < 43200000L) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static Object loadMetaData(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramString1, 128).metaData.get(paramString2);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static void startAPP(Context paramContext, String paramString)
  {
    try
    {
      paramContext.startActivity(paramContext.getPackageManager().getLaunchIntentForPackage(paramString));
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
}
