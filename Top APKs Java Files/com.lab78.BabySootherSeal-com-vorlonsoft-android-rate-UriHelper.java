package com.vorlonsoft.android.rate;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class UriHelper
{
  private static final String AMAZON_APPSTORE = "amzn://apps/android?p=";
  private static final String AMAZON_APPSTORE_WEB = "https://www.amazon.com/gp/mas/dl/android?p=";
  private static final String BLACKBERRY_WORLD = "appworld://content/";
  private static final String BLACKBERRY_WORLD_WEB = "https://appworld.blackberry.com/webstore/content/";
  private static final String CAFE_BAZAAR = "bazaar://details?id=";
  private static final String CAFE_BAZAAR_WEB = "https://cafebazaar.ir/app/";
  private static final String CHINESE_STORES = "market://details?id=";
  private static final String GOOGLE_PLAY = "market://details?id=";
  private static final String GOOGLE_PLAY_WEB = "https://play.google.com/store/apps/details?id=";
  private static final String MI_APPSTORE = "http://app.mi.com/details?id=";
  private static final String SAMSUNG_GALAXY_APPS = "samsungapps://ProductDetail/";
  private static final String SAMSUNG_GALAXY_APPS_WEB = "https://apps.samsung.com/appquery/appDetail.as?appId=";
  private static final String SLIDEME = "sam://details?id=";
  private static final String SLIDEME_WEB = "http://slideme.org/app/";
  private static final String TENCENT_APP_STORE = "market://details?id=";
  private static final String TENCENT_APP_STORE_WEB = "http://a.app.qq.com/o/simple.jsp?pkgname=";
  private static final String YANDEX_STORE = "yastore://details?id=";
  private static final String YANDEX_STORE_WEB = "https://store.yandex.com/apps/details?id=";
  
  private UriHelper() {}
  
  static Uri getAmazonAppstore(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Uri.parse("amzn://apps/android?p=".concat(String.valueOf(paramString)));
  }
  
  static Uri getAmazonAppstoreWeb(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Uri.parse("https://www.amazon.com/gp/mas/dl/android?p=".concat(String.valueOf(paramString)));
  }
  
  static Uri getBlackBerryWorld(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Uri.parse("appworld://content/".concat(String.valueOf(paramString)));
  }
  
  static Uri getBlackBerryWorldWeb(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Uri.parse("https://appworld.blackberry.com/webstore/content/".concat(String.valueOf(paramString)));
  }
  
  static Uri getCafeBazaar(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Uri.parse("bazaar://details?id=".concat(String.valueOf(paramString)));
  }
  
  static Uri getCafeBazaarWeb(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Uri.parse("https://cafebazaar.ir/app/".concat(String.valueOf(paramString)));
  }
  
  static Uri getChineseStores(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Uri.parse("market://details?id=".concat(String.valueOf(paramString)));
  }
  
  static Uri getGooglePlay(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Uri.parse("market://details?id=".concat(String.valueOf(paramString)));
  }
  
  static Uri getGooglePlayWeb(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Uri.parse("https://play.google.com/store/apps/details?id=".concat(String.valueOf(paramString)));
  }
  
  static Uri getMiAppstore(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Uri.parse("http://app.mi.com/details?id=".concat(String.valueOf(paramString)));
  }
  
  static Uri getSamsungGalaxyApps(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Uri.parse("samsungapps://ProductDetail/".concat(String.valueOf(paramString)));
  }
  
  static Uri getSamsungGalaxyAppsWeb(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Uri.parse("https://apps.samsung.com/appquery/appDetail.as?appId=".concat(String.valueOf(paramString)));
  }
  
  static Uri getSlideME(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Uri.parse("sam://details?id=".concat(String.valueOf(paramString)));
  }
  
  static Uri getSlideMEWeb(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Uri.parse("http://slideme.org/app/".concat(String.valueOf(paramString)));
  }
  
  static Uri getTencentAppStore(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Uri.parse("market://details?id=".concat(String.valueOf(paramString)));
  }
  
  static Uri getTencentAppStoreWeb(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Uri.parse("http://a.app.qq.com/o/simple.jsp?pkgname=".concat(String.valueOf(paramString)));
  }
  
  static Uri getYandexStore(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Uri.parse("yastore://details?id=".concat(String.valueOf(paramString)));
  }
  
  static Uri getYandexStoreWeb(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Uri.parse("https://store.yandex.com/apps/details?id=".concat(String.valueOf(paramString)));
  }
  
  static boolean isPackageExists(Context paramContext, String paramString)
  {
    return isPackagesExists(paramContext, new String[] { paramString }).size() > 0;
  }
  
  static ArrayList<String> isPackagesExists(Context paramContext, String[] paramArrayOfString)
  {
    int i = 0;
    Object localObject = paramContext.getPackageManager();
    paramContext = new ArrayList();
    localObject = ((PackageManager)localObject).getInstalledApplications(0);
    int j = paramArrayOfString.length;
    while (i < j)
    {
      String str = paramArrayOfString[i];
      Iterator localIterator = ((List)localObject).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (localApplicationInfo.packageName.equals(str)) {
          paramContext.add(localApplicationInfo.packageName);
        }
      }
      i += 1;
    }
    return paramContext;
  }
}
