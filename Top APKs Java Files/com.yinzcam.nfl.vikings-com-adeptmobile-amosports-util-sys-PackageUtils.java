package com.adeptmobile.amosports.util.sys;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.Toast;
import java.util.Iterator;
import java.util.List;

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
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean isCallable(Context paramContext, Intent paramIntent)
  {
    return paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536).size() > 0;
  }
  
  public static void startOutsideActivityOrShop(Context paramContext, String paramString)
  {
    if (isAppInstalled(paramContext, paramString))
    {
      paramContext.startActivity(paramContext.getPackageManager().getLaunchIntentForPackage(paramString));
      return;
    }
    Object localObject = paramContext.getPackageManager().getInstalledPackages(8192);
    int j = 0;
    int i = 0;
    localObject = ((List)localObject).iterator();
    int m;
    int k;
    do
    {
      m = i;
      k = j;
      if (!((Iterator)localObject).hasNext()) {
        break;
      }
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if (!localPackageInfo.packageName.equalsIgnoreCase("com.android.vending"))
      {
        k = j;
        if (!localPackageInfo.packageName.equalsIgnoreCase("com.google.market")) {}
      }
      else
      {
        k = 1;
      }
      m = i;
      if (localPackageInfo.packageName.equalsIgnoreCase("com.amazon.venezia")) {
        m = 1;
      }
      i = m;
      j = k;
    } while (k == 0);
    if (k != 0) {
      try
      {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString)));
        return;
      }
      catch (ActivityNotFoundException localActivityNotFoundException1)
      {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + paramString)));
        return;
      }
    }
    if (m != 0) {
      try
      {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("amzn://apps/android?p=" + paramString)));
        return;
      }
      catch (ActivityNotFoundException localActivityNotFoundException2)
      {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.amazon.com/gp/mas/dl/android?p=" + paramString)));
        return;
      }
    }
    Toast.makeText(paramContext.getApplicationContext(), "Unable to load app store.", 0).show();
  }
  
  public static void startShopForPackage(Context paramContext, String paramString)
  {
    Object localObject = paramContext.getPackageManager().getInstalledPackages(8192);
    int j = 0;
    int i = 0;
    localObject = ((List)localObject).iterator();
    int m;
    int k;
    do
    {
      m = i;
      k = j;
      if (!((Iterator)localObject).hasNext()) {
        break;
      }
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if (!localPackageInfo.packageName.equalsIgnoreCase("com.android.vending"))
      {
        k = j;
        if (!localPackageInfo.packageName.equalsIgnoreCase("com.google.market")) {}
      }
      else
      {
        k = 1;
      }
      m = i;
      if (localPackageInfo.packageName.equalsIgnoreCase("com.amazon.venezia")) {
        m = 1;
      }
      i = m;
      j = k;
    } while (k == 0);
    if (k != 0) {
      try
      {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString)));
        return;
      }
      catch (ActivityNotFoundException localActivityNotFoundException1)
      {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + paramString)));
        return;
      }
    }
    if (m != 0) {
      try
      {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("amzn://apps/android?p=" + paramString)));
        return;
      }
      catch (ActivityNotFoundException localActivityNotFoundException2)
      {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.amazon.com/gp/mas/dl/android?p=" + paramString)));
        return;
      }
    }
    Toast.makeText(paramContext.getApplicationContext(), "Unable to load app store.", 0).show();
  }
}
