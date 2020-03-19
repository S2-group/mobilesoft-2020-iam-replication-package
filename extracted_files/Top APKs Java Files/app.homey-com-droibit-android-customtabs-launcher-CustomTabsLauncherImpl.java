package com.droibit.android.customtabs.launcher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.customtabs.CustomTabsIntent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
class CustomTabsLauncherImpl
{
  private static final String ACTION_CUSTOM_TABS_CONNECTION = "android.support.customtabs.action.CustomTabsService";
  private static final List<String> CHROME_PACKAGES = Arrays.asList(new String[] { "com.android.chrome", "com.chrome.beta", "com.chrome.dev", "com.google.android.apps.chrome" });
  @VisibleForTesting
  static final String PACKAGE_BETA = "com.chrome.beta";
  @VisibleForTesting
  static final String PACKAGE_DEV = "com.chrome.dev";
  @VisibleForTesting
  static final String PACKAGE_LOCAL = "com.google.android.apps.chrome";
  @VisibleForTesting
  static final String PACKAGE_STABLE = "com.android.chrome";
  
  CustomTabsLauncherImpl() {}
  
  @VisibleForTesting
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
  
  @Nullable
  @VisibleForTesting
  String defaultViewHandlerPackage(PackageManager paramPackageManager, Uri paramUri)
  {
    paramPackageManager = paramPackageManager.resolveActivity(new Intent("android.intent.action.VIEW", paramUri), 0);
    if (paramPackageManager != null) {
      return paramPackageManager.activityInfo.packageName;
    }
    return null;
  }
  
  @NonNull
  @VisibleForTesting
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
  
  @VisibleForTesting(otherwise=3)
  void launch(@NonNull Context paramContext, @NonNull CustomTabsIntent paramCustomTabsIntent, @NonNull Uri paramUri, @Nullable CustomTabsFallback paramCustomTabsFallback)
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
  
  @Nullable
  @VisibleForTesting
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
  
  @VisibleForTesting
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
