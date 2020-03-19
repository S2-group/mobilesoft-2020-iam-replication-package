package com.adeptmobile.fep.base.util.sys;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import timber.log.Timber;

public class PackageUtils
{
  private static final String AmazonAppStorePackageName = "com.amazon.venezia";
  private static final String GooglePlayStorePackageNameNew = "com.android.vending";
  private static final String GooglePlayStorePackageNameOld = "com.google.market";
  
  public PackageUtils() {}
  
  public static boolean isAppInstalled(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
      if (paramContext != null) {
        return true;
      }
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return false;
  }
  
  public static boolean isCallable(Context paramContext, Intent paramIntent)
  {
    return paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536).size() > 0;
  }
  
  public static void saveCurrentAppVersion(Context paramContext)
  {
    try
    {
      if (!wasAppUpdated(paramContext)) {
        break label53;
      }
      PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putInt("last_seen_app_version_code", paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode).apply();
      return;
    }
    catch (Exception paramContext)
    {
      label53:
      for (;;) {}
    }
    Timber.w("Failed to save last seen version code", new Object[0]);
  }
  
  public static boolean shouldForceUpdate(String[] paramArrayOfString, String paramString)
  {
    if ((paramArrayOfString != null) && (paramArrayOfString.length > 0) && (paramString != null))
    {
      int j = paramArrayOfString.length;
      int i = 0;
      while (i < j)
      {
        if (paramArrayOfString[i].equalsIgnoreCase(paramString)) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public static void startOutsideActivityOrShop(Context paramContext, String paramString)
  {
    if (isAppInstalled(paramContext, paramString))
    {
      paramContext.startActivity(paramContext.getPackageManager().getLaunchIntentForPackage(paramString));
      return;
    }
    Object localObject2 = paramContext.getPackageManager();
    Object localObject1 = new ArrayList();
    if (Build.VERSION.SDK_INT >= 24) {
      localObject1 = ((PackageManager)localObject2).getInstalledPackages(8192);
    } else {
      ((PackageManager)localObject2).getInstalledPackages(8192);
    }
    localObject1 = ((List)localObject1).iterator();
    int j = 0;
    int i = 0;
    int k;
    int m;
    do
    {
      k = j;
      m = i;
      if (!((Iterator)localObject1).hasNext()) {
        break;
      }
      localObject2 = (PackageInfo)((Iterator)localObject1).next();
      if (!((PackageInfo)localObject2).packageName.equalsIgnoreCase("com.android.vending"))
      {
        k = j;
        if (!((PackageInfo)localObject2).packageName.equalsIgnoreCase("com.google.market")) {}
      }
      else
      {
        k = 1;
      }
      m = i;
      if (((PackageInfo)localObject2).packageName.equalsIgnoreCase("com.amazon.venezia")) {
        m = 1;
      }
      j = k;
      i = m;
    } while (k == 0);
    if (k != 0) {}
    try
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("market://details?id=");
      ((StringBuilder)localObject1).append(paramString);
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(((StringBuilder)localObject1).toString())));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException1)
    {
      for (;;) {}
    }
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("http://play.google.com/store/apps/details?id=");
    ((StringBuilder)localObject1).append(paramString);
    paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(((StringBuilder)localObject1).toString())));
    return;
    if (m != 0) {}
    try
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("amzn://apps/android?p=");
      ((StringBuilder)localObject1).append(paramString);
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(((StringBuilder)localObject1).toString())));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException2)
    {
      for (;;) {}
    }
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("http://www.amazon.com/gp/mas/dl/android?p=");
    ((StringBuilder)localObject1).append(paramString);
    paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(((StringBuilder)localObject1).toString())));
    return;
    Toast.makeText(paramContext.getApplicationContext(), "Unable to load app store.", 0).show();
  }
  
  public static void startOutsideApplicationOrShop(Context paramContext, String paramString)
  {
    if (isAppInstalled(paramContext, paramString))
    {
      paramContext.startActivity(paramContext.getPackageManager().getLaunchIntentForPackage(paramString));
      return;
    }
    startShopForPackage(paramContext, paramString);
  }
  
  public static void startShopForPackage(Context paramContext, String paramString)
  {
    Object localObject = paramContext.getPackageManager().getInstalledPackages(8192).iterator();
    int i = 0;
    int j;
    do
    {
      j = i;
      if (!((Iterator)localObject).hasNext()) {
        break;
      }
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if (!localPackageInfo.packageName.equalsIgnoreCase("com.android.vending"))
      {
        j = i;
        if (!localPackageInfo.packageName.equalsIgnoreCase("com.google.market")) {}
      }
      else
      {
        j = 1;
      }
      i = j;
    } while (j == 0);
    if (j != 0) {}
    try
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("market://details?id=");
      ((StringBuilder)localObject).append(paramString);
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(((StringBuilder)localObject).toString())));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      for (;;) {}
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("http://play.google.com/store/apps/details?id=");
    ((StringBuilder)localObject).append(paramString);
    paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(((StringBuilder)localObject).toString())));
  }
  
  public static boolean wasAppUpdated(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      int j = PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("last_seen_app_version_code", 0);
      return i > j;
    }
    catch (Exception paramContext) {}
    return false;
  }
}
