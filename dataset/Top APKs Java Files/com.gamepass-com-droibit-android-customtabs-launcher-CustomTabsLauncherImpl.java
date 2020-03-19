package com.droibit.android.customtabs.launcher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class CustomTabsLauncherImpl
{
  private static final List<String> CHROME_PACKAGES = Arrays.asList(new String[] { "com.android.chrome", "com.chrome.beta", "com.chrome.dev", "com.google.android.apps.chrome" });
  
  CustomTabsLauncherImpl() {}
  
  String decidePackage(PackageManager paramPackageManager, List<String> paramList)
  {
    Iterator localIterator = CHROME_PACKAGES.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if ((paramList.contains(str)) && (supportedCustomTabs(paramPackageManager, str))) {
        return str;
      }
    }
    return null;
  }
  
  String defaultViewHandlerPackage(PackageManager paramPackageManager, Uri paramUri)
  {
    paramPackageManager = paramPackageManager.resolveActivity(new Intent("android.intent.action.VIEW", paramUri), 0);
    if (paramPackageManager != null) {
      return paramPackageManager.activityInfo.packageName;
    }
    return null;
  }
  
  List<String> installedPackages(PackageManager paramPackageManager)
  {
    Object localObject = paramPackageManager.getInstalledApplications(128);
    paramPackageManager = new ArrayList(CHROME_PACKAGES.size());
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      if (CHROME_PACKAGES.contains(localApplicationInfo.packageName)) {
        paramPackageManager.add(localApplicationInfo.packageName);
      }
    }
    return paramPackageManager;
  }
  
  void launch(Context paramContext, CustomTabsIntent paramCustomTabsIntent, Uri paramUri, CustomTabsFallback paramCustomTabsFallback)
  {
    String str = packageNameToUse(paramContext.getPackageManager(), paramUri);
    if ((str == null) && (paramCustomTabsFallback != null))
    {
      paramCustomTabsFallback.openUrl(paramContext, paramUri);
      return;
    }
    paramCustomTabsIntent.intent.setPackage(str);
    paramCustomTabsIntent.launchUrl(paramContext, paramUri);
  }
  
  String packageNameToUse(PackageManager paramPackageManager, Uri paramUri)
  {
    paramUri = defaultViewHandlerPackage(paramPackageManager, paramUri);
    if ((paramUri != null) && (CHROME_PACKAGES.contains(paramUri)) && (supportedCustomTabs(paramPackageManager, paramUri))) {
      return paramUri;
    }
    paramUri = installedPackages(paramPackageManager);
    if (paramUri.isEmpty()) {
      return null;
    }
    return decidePackage(paramPackageManager, paramUri);
  }
  
  boolean supportedCustomTabs(PackageManager paramPackageManager, String paramString)
  {
    paramString = new Intent("android.support.customtabs.action.CustomTabsService").setPackage(paramString);
    boolean bool = false;
    if (paramPackageManager.resolveService(paramString, 0) != null) {
      bool = true;
    }
    return bool;
  }
}
