package com.vorlonsoft.android.rate;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import java.util.Iterator;
import java.util.List;

final class UriHelper
{
  private static final String AMAZON_APPSTORE = "amzn://apps/android?p=";
  private static final String AMAZON_APPSTORE_WEB = "https://www.amazon.com/gp/mas/dl/android?p=";
  private static final String GOOGLE_PLAY = "market://details?id=";
  private static final String GOOGLE_PLAY_WEB = "https://play.google.com/store/apps/details?id=";
  private static final String MI_APPSTORE = "http://app.mi.com/details?id=";
  private static final String SAMSUNG_GALAXY_APPS = "samsungapps://ProductDetail/";
  private static final String SAMSUNG_GALAXY_APPS_WEB = "https://www.samsungapps.com/appquery/appDetail.as?appId=";
  private static final String TENCENT_APP_STORE = "http://android.myapp.com/myapp/detail.htm?apkName=";
  
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
    return Uri.parse("https://www.samsungapps.com/appquery/appDetail.as?appId=".concat(String.valueOf(paramString)));
  }
  
  static Uri getTencentAppStore(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return Uri.parse("http://android.myapp.com/myapp/detail.htm?apkName=".concat(String.valueOf(paramString)));
  }
  
  static boolean isPackageExists(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
}
