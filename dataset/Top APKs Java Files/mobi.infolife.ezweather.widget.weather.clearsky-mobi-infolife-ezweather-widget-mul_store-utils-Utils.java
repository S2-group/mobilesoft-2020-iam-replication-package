package mobi.infolife.ezweather.widget.mul_store.utils;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import mobi.info.ezweather.baselibrary.BaseCommonUtils;
import mobi.infolife.ezweather.widget.mul_store.data.ItemData;
import mobi.infolife.ezweather.widget.mul_store.data.StoreDataRequest;
import org.json.JSONException;
import org.json.JSONObject;

public class Utils
{
  public static final String AMBER_WEATHER_PACKAGE_NAME = "mobi.infolife.ezweather";
  public static final String APPLY_WIDGET_INTENT = "store.apply.widget";
  public static final String CAMPAIGN_STORE = "store";
  public static final String EZWEATHER_PLUGIN = "EZWEATHER_PLUGIN";
  public static final String LOCKER_SKIN = "LOCKER_SKIN";
  public static final String META_DATA_VALUE = "mobi.infolife.ezweather.plugin.widgetskin";
  public static final String NEW_USER = "new_user";
  public static final String OLD_USER = "old_user";
  public static final String PLUGIN_WIDGET = "mul_widget";
  public static final String PLUGIN_WIDGET_THEME = "mul_widget_theme";
  public static final String PLUGIN_WIDGET_USING_WIDGET = "mul_widget_using_widget";
  public static final String THEME_PACKAGE_NAME_EXTRA = "theme_package_name";
  public static final String TYPE_BOOL = "bool";
  public static final String TYPE_COLOR = "color";
  public static final String TYPE_DRAWABLE = "drawable";
  public static final String TYPE_ID = "id";
  public static final String TYPE_INTEGER = "integer";
  public static final String TYPE_LAYOUT = "layout";
  public static final String TYPE_STRING = "string";
  
  public Utils() {}
  
  public static void UpdateStoreVersionCode(Context paramContext)
  {
    StoreDataRequest.downloadData(StoreDataRequest.getVersionUrl(paramContext), new Utils.1(paramContext));
  }
  
  public static void applyWidgetByPkgName(Context paramContext, String paramString)
  {
    String str2 = getMainWidgetPkgName(paramContext);
    String str1 = str2;
    if (!isAppExist(paramContext, str2)) {
      str1 = paramContext.getPackageName();
    }
    startActivityApplyWidget(paramContext, str1, paramString, false);
  }
  
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
  
  public static Context createContextByPkgName(Context paramContext, String paramString)
  {
    if (paramContext.getPackageName().equals(paramString)) {
      return paramContext;
    }
    try
    {
      paramString = paramContext.createPackageContext(paramString, 3);
      paramContext = paramString;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
    return paramContext;
  }
  
  public static int dip2px(Context paramContext, int paramInt)
  {
    float f = paramContext.getResources().getDisplayMetrics().density;
    return (int)(paramInt * f + 0.5F);
  }
  
  public static void downloadAppAndClearUiHistory(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    downloadAppByPackageName(paramContext, paramString1, paramString2, paramString3);
  }
  
  public static void downloadAppByPackageName(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    String str1;
    if (StorePreference.getUseAsMainAppStatus(paramContext) == 0)
    {
      str1 = "mul_widget_theme";
      if (!isNewUserInStore(paramContext)) {
        break label59;
      }
    }
    label59:
    for (String str2 = "new_user";; str2 = "old_user")
    {
      BaseCommonUtils.downloadApp(paramContext, paramString1, str1, paramString2, str2, paramString3, paramContext.getPackageName());
      return;
      if (getWidgetCount(paramContext) > 0)
      {
        str1 = "mul_widget_using_widget";
        break;
      }
      str1 = "mul_widget";
      break;
    }
  }
  
  public static Intent downloadAppByPackageNameIntent(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    String str1;
    if (StorePreference.getUseAsMainAppStatus(paramContext) == 0)
    {
      str1 = "mul_widget_theme";
      if (!isNewUserInStore(paramContext)) {
        break label59;
      }
    }
    label59:
    for (String str2 = "new_user";; str2 = "old_user")
    {
      return BaseCommonUtils.downloadAppIntent(paramContext, paramString1, str1, paramString2, str2, paramString3, paramContext.getPackageName());
      if (getWidgetCount(paramContext) > 0)
      {
        str1 = "mul_widget_using_widget";
        break;
      }
      str1 = "mul_widget";
      break;
    }
  }
  
  public static ArrayList<ItemData> getInstalledAmberPlugins(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    Object localObject = getInstalledAppList(paramContext, 8192);
    paramContext = null;
    if (localObject == null) {}
    for (;;)
    {
      return localArrayList;
      Iterator localIterator = ((List)localObject).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        localObject = localPackageInfo.packageName;
        Context localContext;
        try
        {
          localObject = localPackageManager.getApplicationInfo((String)localObject, 128);
          paramContext = (Context)localObject;
          if (localObject == null) {
            continue;
          }
          paramContext = (Context)localObject;
          if (((ApplicationInfo)localObject).metaData == null) {
            continue;
          }
          paramContext = ((ApplicationInfo)localObject).metaData.getString("EZWEATHER_PLUGIN");
          if ((paramContext != null) && ("mobi.infolife.ezweather.plugin.widgetskin".equals(paramContext)))
          {
            paramContext = new ItemData();
            paramContext.setPackageName(localPackageInfo.packageName);
            paramContext.setWidget(true);
            localArrayList.add(paramContext);
            paramContext = (Context)localObject;
          }
        }
        catch (Exception localException)
        {
          for (;;)
          {
            localException.printStackTrace();
            localContext = paramContext;
          }
          paramContext = localContext;
        }
        if (localContext.metaData.getBoolean("LOCKER_SKIN"))
        {
          paramContext = new ItemData();
          paramContext.setPackageName(localPackageInfo.packageName);
          paramContext.setWidget(false);
          localArrayList.add(paramContext);
          paramContext = localContext;
        }
      }
    }
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
    }
    return null;
  }
  
  public static ArrayList<ItemData> getInstalledLockersForStore(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    Object localObject = getInstalledAppList(paramContext, 8192);
    if (localObject == null) {
      return localArrayList;
    }
    paramContext = null;
    Iterator localIterator = ((List)localObject).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      localObject = localPackageInfo.packageName;
      try
      {
        localObject = localPackageManager.getApplicationInfo((String)localObject, 128);
        paramContext = (Context)localObject;
        if (localObject == null) {
          continue;
        }
        paramContext = (Context)localObject;
        if (((ApplicationInfo)localObject).metaData == null) {
          continue;
        }
        paramContext = (Context)localObject;
        if (!((ApplicationInfo)localObject).metaData.getBoolean("LOCKER_SKIN")) {
          continue;
        }
        paramContext = new ItemData();
        paramContext.setPackageName(localPackageInfo.packageName);
        paramContext.setName(localPackageManager.getApplicationLabel((ApplicationInfo)localObject).toString());
        paramContext.setPrice(0.0F);
        localArrayList.add(paramContext);
        paramContext.setDefault(false);
        paramContext = (Context)localObject;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
          Context localContext = paramContext;
        }
      }
    }
  }
  
  public static ArrayList<ItemData> getInstalledPluginsForStore(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    Object localObject1 = getInstalledAppList(paramContext, 8192);
    localObject3 = null;
    if (localObject1 == null) {
      return localArrayList;
    }
    Iterator localIterator = ((List)localObject1).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      String str = localPackageInfo.packageName;
      try
      {
        localObject1 = localPackageManager.getApplicationInfo(str, 128);
        localObject3 = localObject1;
        if (localObject1 == null) {
          continue;
        }
        localObject3 = localObject1;
        if (((ApplicationInfo)localObject1).metaData == null) {
          continue;
        }
        Object localObject4 = ((ApplicationInfo)localObject1).metaData.getString("EZWEATHER_PLUGIN");
        localObject3 = localObject1;
        if (localObject4 == null) {
          continue;
        }
        localObject3 = localObject1;
        if (!"mobi.infolife.ezweather.plugin.widgetskin".equals(localObject4)) {
          continue;
        }
        localObject4 = getOtherAppContext(paramContext, str);
        int i = 0;
        localObject3 = new ItemData();
        ((ItemData)localObject3).setPackageName(localPackageInfo.packageName);
        ((ItemData)localObject3).setName(localPackageManager.getApplicationLabel((ApplicationInfo)localObject1).toString());
        try
        {
          boolean bool = ((Context)localObject4).getResources().getBoolean(getResourceId(paramContext, "isFree", "bool", str));
          i = bool;
        }
        catch (Exception localException2)
        {
          for (;;)
          {
            Object localObject2;
            localException2.printStackTrace();
            continue;
            ((ItemData)localObject3).setPrice(2.0F);
          }
        }
        if (i != 0)
        {
          ((ItemData)localObject3).setPrice(0.0F);
          localArrayList.add(localObject3);
          localObject3 = localObject1;
        }
      }
      catch (Exception localException1)
      {
        for (;;)
        {
          localException1.printStackTrace();
          localObject2 = localObject3;
        }
      }
    }
  }
  
  public static String getMainWidgetPkgName(Context paramContext)
  {
    Object localObject1 = SdConfig.getConfigFromSd(paramContext);
    Object localObject2 = paramContext.getPackageName();
    if (TextUtils.isEmpty((CharSequence)localObject1)) {
      localObject1 = paramContext.getPackageName();
    }
    for (;;)
    {
      SdConfig.saveConfigToSd(paramContext, (String)localObject1, (String)localObject2);
      return localObject1;
      try
      {
        localObject1 = new JSONObject((String)localObject1);
        String str2 = ((JSONObject)localObject1).optString(SdConfig.MAIN_WIDGET_PACKAGE_NAME);
        localObject2 = ((JSONObject)localObject1).optString(SdConfig.CONFIG_USING_THEME_PKG_NAME);
        localObject1 = str2;
        if (!isAppExist(paramContext, str2))
        {
          localObject1 = paramContext.getPackageName();
          localObject2 = localObject1;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
        String str1 = paramContext.getPackageName();
        localObject2 = str1;
      }
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
  
  public static Class<?> getRClass(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getClassLoader().loadClass(paramString + ".R");
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
  
  public static String getUsingThemeByConfig(Context paramContext)
  {
    String str1 = SdConfig.getConfigFromSd(paramContext);
    try
    {
      str1 = (String)new JSONObject(str1).get(SdConfig.CONFIG_USING_THEME_PKG_NAME);
      String str3 = str1;
      if (!checkAppInstalled(paramContext, str1)) {
        str3 = paramContext.getPackageName();
      }
      return str3;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        localJSONException.printStackTrace();
        String str2 = paramContext.getPackageName();
      }
    }
  }
  
  public static int getWidgetCount(Context paramContext)
  {
    int[] arrayOfInt = new int[0];
    try
    {
      ComponentName localComponentName = new ComponentName(paramContext, "mobi.infolife.ezweather.widget.common.mulWidget.WidgetProvider");
      paramContext = AppWidgetManager.getInstance(paramContext).getAppWidgetIds(localComponentName);
      return paramContext.length;
    }
    catch (RuntimeException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = arrayOfInt;
      }
    }
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
  
  private static void senBroadcastApplyWidget(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    SdConfig.saveConfigToSd(paramContext, paramString1, paramString2);
    Intent localIntent = new Intent("store.apply.widget");
    localIntent.putExtra("theme_package_name", paramString2);
    if (paramBoolean) {}
    for (;;)
    {
      localIntent.setPackage(paramString1);
      if (Build.VERSION.SDK_INT >= 12) {
        localIntent.setFlags(32);
      }
      paramContext.sendBroadcast(localIntent);
      return;
      paramString1 = paramContext.getPackageName();
    }
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
  
  public static void startActivityApplyWidget(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    SdConfig.saveConfigToSd(paramContext, paramString1, paramString2);
    Intent localIntent = new Intent();
    if (paramBoolean) {}
    for (String str = paramString1;; str = paramContext.getPackageName())
    {
      localIntent.setComponent(new ComponentName(str, "mobi.infolife.ezweather.widget.common.mulWidget.widgetRender.ApplyStoreWidgetActivity"));
      localIntent.setAction("store.apply.widget");
      localIntent.putExtra("theme_package_name", paramString2);
      localIntent.setFlags(268435456);
      try
      {
        paramContext.startActivity(localIntent);
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        senBroadcastApplyWidget(paramContext, paramString1, paramString2, paramBoolean);
      }
    }
  }
}
