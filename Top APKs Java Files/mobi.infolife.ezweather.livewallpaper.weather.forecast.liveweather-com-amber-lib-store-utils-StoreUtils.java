package com.amber.lib.store.utils;

import android.app.WallpaperInfo;
import android.app.WallpaperManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;
import com.amber.lib.store.R.string;
import com.amber.lib.store.data.DownloadListener;
import com.amber.lib.store.data.ItemData;
import com.amber.lib.store.data.StoreDataRequest;
import com.amber.lib.tools.ToolUtils;
import com.amber.lib.widget.ReflectUtil;
import com.amber.lib.widget.status.WidgetStatusManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class StoreUtils
{
  public static final String APPLY_WIDGET_INTENT = "store.apply.widget";
  public static final String CAMPAIGN_STORE = "store";
  public static final String COMMON_LWP_SERVICE_NAME = "mobi.infolife.ezweather.lwplib.wallpaper.BaseWallpaper";
  public static final String ENGINE_NAME_KEY = "ENGINE_SERVICE_NAME";
  public static final String EZWEATHER_PLUGIN = "EZWEATHER_PLUGIN";
  public static final String LOCKER_SKIN = "LOCKER_SKIN";
  public static final String META_DATA_VALUE = "mobi.infolife.ezweather.plugin.widgetskin";
  public static final String THEME_PACKAGE_NAME_EXTRA = "theme_package_name";
  public static final String TYPE_BOOL = "bool";
  public static final String TYPE_COLOR = "color";
  public static final String TYPE_DRAWABLE = "drawable";
  public static final String TYPE_ID = "id";
  public static final String TYPE_INTEGER = "integer";
  public static final String TYPE_LAYOUT = "layout";
  public static final String TYPE_STRING = "string";
  public static final int WIDGETSIZE_4X2_CLOCK = 5;
  public static final int WIDGETSIZE_4X2_FORECAST = 6;
  public static List<PackageInfo> installedAppList;
  
  public StoreUtils() {}
  
  public static void applyThemeForAmberWeather(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      Context localContext = ReflectUtil.createContextByPkgName(paramContext, paramString2);
      Intent localIntent = new Intent();
      localIntent.setComponent(new ComponentName(paramString1, "mobi.infolife.store.activity.WidgetHelperActivity"));
      localIntent.putExtra("packagename", paramString2);
      if (localContext.getResources().getBoolean(ReflectUtil.getResourceId(paramContext, "isContainForecast", "bool", paramString2)))
      {
        paramString1 = "widget_fw_type1";
        localIntent.putExtra("layoutname", paramString1);
        if (!localContext.getResources().getBoolean(ReflectUtil.getResourceId(paramContext, "isContain4_2", "bool", paramString2))) {
          break label175;
        }
      }
      label175:
      for (int i = 5;; i = 6)
      {
        localIntent.putExtra("widgetsize", i);
        localIntent.putExtra("isforecast", localContext.getResources().getBoolean(ReflectUtil.getResourceId(paramContext, "isContainForecast", "bool", paramString2)));
        localIntent.putExtra("productid", localContext.getResources().getString(ReflectUtil.getResourceId(paramContext, "productId", "string", paramString2)));
        localIntent.addFlags(268435456);
        paramContext.startActivity(localIntent);
        return;
        paramString1 = "widget_cw_type1";
        break;
      }
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void applyWallpaper(Context paramContext, String paramString)
  {
    Object localObject2 = getMetaDataByKey(paramContext, paramString, "ENGINE_SERVICE_NAME");
    Object localObject1 = localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject2)) {
      localObject1 = "mobi.infolife.ezweather.lwplib.wallpaper.BaseWallpaper";
    }
    try
    {
      paramString = new ComponentName(paramString, (String)localObject1);
      localObject1 = new Intent("android.service.wallpaper.CHANGE_LIVE_WALLPAPER");
      ((Intent)localObject1).putExtra("android.service.wallpaper.extra.LIVE_WALLPAPER_COMPONENT", paramString);
      ((Intent)localObject1).setFlags(67108864);
      paramContext.startActivity((Intent)localObject1);
      return;
    }
    catch (ActivityNotFoundException paramString) {}
    try
    {
      localObject1 = Toast.makeText(paramContext, "Choose Parallax\n in the list to start the Live Wallpaper.", 1);
      localObject2 = new Intent("android.service.wallpaper.LIVE_WALLPAPER_CHOOSER");
      ((Intent)localObject2).setFlags(67108864);
      paramContext.startActivity((Intent)localObject2);
      ((Toast)localObject1).show();
      paramString.printStackTrace();
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      for (;;)
      {
        localActivityNotFoundException.printStackTrace();
        Toast.makeText(paramContext, "Please go to your system settings or long press on your homescreen to set Live Wallpaper", 1).show();
      }
    }
  }
  
  public static void applyWidgetByPkgName(Context paramContext, String paramString)
  {
    String str2 = WidgetStatusManager.getInstance().getMainWidget();
    String str1 = str2;
    if (!ToolUtils.checkAppInstalled(paramContext, str2)) {
      str1 = paramContext.getPackageName();
    }
    startActivityApplyWidget(paramContext, str1, paramString, false);
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
    int i = 0;
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    if (installedAppList == null) {
      installedAppList = getInstalledAppList(paramContext, 8192);
    }
    installedAppList = getInstalledAppList(paramContext, 8192);
    if (installedAppList == null) {}
    for (;;)
    {
      return localArrayList;
      Object localObject1 = null;
      Iterator localIterator = installedAppList.iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        Object localObject2 = localPackageInfo.packageName;
        Object localObject3;
        try
        {
          localObject2 = localPackageManager.getApplicationInfo((String)localObject2, 128);
          localObject1 = localObject2;
          if (localObject2 == null) {
            continue;
          }
          localObject1 = localObject2;
          if (((ApplicationInfo)localObject2).metaData == null) {
            continue;
          }
          if (((ApplicationInfo)localObject2).metaData.getBoolean("LOCKER_SKIN"))
          {
            localObject1 = new ItemData();
            ((ItemData)localObject1).setPackageName(localPackageInfo.packageName);
            ((ItemData)localObject1).setName(localPackageManager.getApplicationLabel((ApplicationInfo)localObject2).toString());
            ((ItemData)localObject1).setPrice(0.0F);
            localArrayList.add(localObject1);
            ((ItemData)localObject1).setDefault(false);
            localObject1 = localObject2;
          }
        }
        catch (Exception localException)
        {
          for (;;)
          {
            localException.printStackTrace();
            localObject3 = localObject1;
          }
          localObject1 = localObject3;
        }
        if (i == 0)
        {
          String str = localObject3.metaData.getString("EZWEATHER_PLUGIN");
          localObject1 = localObject3;
          if (str != null)
          {
            localObject1 = localObject3;
            if ("mobi.infolife.ezweather.plugin.widgetskin".equals(str))
            {
              i = 1;
              localObject1 = new ItemData();
              ((ItemData)localObject1).setPackageName(localPackageInfo.packageName);
              ((ItemData)localObject1).setName(paramContext.getResources().getString(R.string.default_locker_name));
              ((ItemData)localObject1).setPrice(0.0F);
              ((ItemData)localObject1).setDefault(true);
              ((ItemData)localObject1).setWidget(false);
              localArrayList.add(localObject1);
              localObject1 = localObject3;
            }
          }
        }
      }
    }
  }
  
  public static ArrayList<ItemData> getInstalledPluginsForStore(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = paramContext.getPackageManager();
    installedAppList = getInstalledAppList(paramContext, 8192);
    localObject2 = null;
    if (installedAppList == null) {
      return localArrayList;
    }
    Iterator localIterator = installedAppList.iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      String str = localPackageInfo.packageName;
      try
      {
        ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo(str, 128);
        localObject2 = localApplicationInfo;
        if (localApplicationInfo == null) {
          continue;
        }
        localObject2 = localApplicationInfo;
        if (localApplicationInfo.metaData == null) {
          continue;
        }
        Object localObject3 = localApplicationInfo.metaData.getString("EZWEATHER_PLUGIN");
        localObject2 = localApplicationInfo;
        if (localObject3 == null) {
          continue;
        }
        localObject2 = localApplicationInfo;
        if (!"mobi.infolife.ezweather.plugin.widgetskin".equals(localObject3)) {
          continue;
        }
        localObject3 = ReflectUtil.createContextByPkgName(paramContext, str);
        int i = 0;
        localObject2 = new ItemData();
        ((ItemData)localObject2).setPackageName(localPackageInfo.packageName);
        ((ItemData)localObject2).setName(localPackageManager.getApplicationLabel(localApplicationInfo).toString());
        try
        {
          boolean bool = ((Context)localObject3).getResources().getBoolean(ReflectUtil.getResourceId(paramContext, "isFree", "bool", str));
          i = bool;
        }
        catch (Exception localException2)
        {
          for (;;)
          {
            Object localObject1;
            localException2.printStackTrace();
            continue;
            ((ItemData)localObject2).setPrice(2.0F);
          }
        }
        if (i != 0)
        {
          ((ItemData)localObject2).setPrice(0.0F);
          localArrayList.add(localObject2);
          localObject2 = localApplicationInfo;
        }
      }
      catch (Exception localException1)
      {
        for (;;)
        {
          localException1.printStackTrace();
          localObject1 = localObject2;
        }
      }
    }
  }
  
  public static String getMetaDataByKey(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramString1, 128);
      if ((paramContext != null) && (paramContext.metaData != null))
      {
        paramContext = paramContext.metaData.getString(paramString2);
        return paramContext;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
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
      return 5;
    }
    catch (RuntimeException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 5;
  }
  
  public static boolean isAmberLiveWallpaper(Context paramContext, String paramString)
  {
    try
    {
      boolean bool = paramContext.getPackageManager().getApplicationInfo(paramString, 128).metaData.getBoolean("IS_AMBER_LWP");
      return bool;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean isAmberLocker(Context paramContext, String paramString)
  {
    try
    {
      boolean bool = paramContext.getPackageManager().getApplicationInfo(paramString, 128).metaData.getBoolean("LOCKER_SKIN");
      return bool;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
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
  
  public static boolean isRedesignWidget(Context paramContext, String paramString)
  {
    try
    {
      boolean bool = paramContext.getPackageManager().getApplicationInfo(paramString, 128).metaData.getBoolean("IS_REDESIGN_WIDGET", false);
      return bool;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean isWallpaperUsed(Context paramContext, String paramString)
  {
    paramContext = WallpaperManager.getInstance(paramContext).getWallpaperInfo();
    if (paramContext != null)
    {
      paramContext = paramContext.getPackageName();
      if ((!TextUtils.isEmpty(paramContext)) && (!TextUtils.isEmpty(paramString))) {
        return paramContext.equals(paramString);
      }
    }
    return false;
  }
  
  private static void senBroadcastApplyWidget(Context paramContext, String paramString1, String paramString2, boolean paramBoolean)
  {
    if (TextUtils.equals(paramContext.getPackageName(), paramString1)) {
      WidgetStatusManager.getInstance().setUsingTheme(paramString2);
    }
    Intent localIntent = new Intent("store.apply.widget");
    localIntent.putExtra("theme_package_name", paramString2);
    if (paramBoolean) {}
    for (;;)
    {
      localIntent.setPackage(paramString1);
      localIntent.setFlags(32);
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
    if (TextUtils.equals(paramContext.getPackageName(), paramString1)) {
      WidgetStatusManager.getInstance().setUsingTheme(paramString2);
    }
    Intent localIntent = new Intent();
    if (paramBoolean) {}
    for (String str = paramString1;; str = paramContext.getPackageName())
    {
      localIntent.setComponent(new ComponentName(str, "com.amber.lib.store.activity.ApplyStoreWidgetActivity"));
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
  
  public static void updateStoreVersionCode(Context paramContext)
  {
    StoreDataRequest.downloadData(StoreDataRequest.getVersionUrl(paramContext), new DownloadListener()
    {
      public void onComplete(String paramAnonymousString)
      {
        try
        {
          int i = new JSONObject(paramAnonymousString).optInt("version");
          StorePreference.setUpdateVersionTime(this.val$context, System.currentTimeMillis());
          if (StorePreference.getStoreVersionCode(this.val$context) < i)
          {
            StorePreference.addThisVersionShowFeatureFragmentCount(this.val$context, true);
            StorePreference.setStoreNeedUpdate(this.val$context, true);
            StorePreference.setStoreIsNew(this.val$context, true);
            if (StorePreference.getShowNewVersionStoreCount(this.val$context) != 2) {
              StorePreference.setShowNewVersionStoreCount(this.val$context, 1);
            }
            StorePreference.setStoreNewItemNum(this.val$context);
            StorePreference.setStoreVersionCode(this.val$context, i);
          }
          return;
        }
        catch (JSONException paramAnonymousString)
        {
          paramAnonymousString.printStackTrace();
        }
      }
      
      public void onError() {}
    });
  }
}
