package com.funlabmedia.funlabapp.Util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build.VERSION;
import com.funlabmedia.funlabapp.MainActivity;
import com.funlabmedia.funlabapp.MoreApps.MoreAppsActivity.MoreAppsEntry;
import java.util.Iterator;
import java.util.List;

public class StoreUtil
{
  public static boolean AmazonAppStoreInstalled;
  public static boolean AptoideStoreInstalled = false;
  public static boolean GooglePlayStoreInstalled = false;
  public static boolean SamsungAppStoreInstalled;
  
  static
  {
    AmazonAppStoreInstalled = false;
    SamsungAppStoreInstalled = false;
  }
  
  public StoreUtil() {}
  
  private static void CheckAmazonAppStore(Context paramContext)
  {
    paramContext = paramContext.getApplicationContext().getPackageManager();
    if (Build.VERSION.SDK_INT < 24) {}
    for (;;)
    {
      paramContext = paramContext.getInstalledPackages(8192).iterator();
      while (paramContext.hasNext()) {
        if (((PackageInfo)paramContext.next()).packageName.equals("com.amazon.venezia")) {
          AmazonAppStoreInstalled = true;
        }
      }
      return;
    }
  }
  
  private static void CheckAptoide(Context paramContext)
  {
    paramContext = paramContext.getApplicationContext().getPackageManager();
    if (Build.VERSION.SDK_INT < 24) {}
    for (;;)
    {
      paramContext = paramContext.getInstalledPackages(8192).iterator();
      while (paramContext.hasNext()) {
        if (((PackageInfo)paramContext.next()).packageName.equals("cm.aptoide.pt")) {
          AptoideStoreInstalled = true;
        }
      }
      return;
    }
  }
  
  public static void CheckAvailableStores(Context paramContext)
  {
    CheckGooglePlayStore(paramContext);
    CheckAmazonAppStore(paramContext);
    CheckSamsungAppStore(paramContext);
  }
  
  private static void CheckGooglePlayStore(Context paramContext)
  {
    paramContext = paramContext.getApplicationContext().getPackageManager();
    if (Build.VERSION.SDK_INT < 24) {}
    for (;;)
    {
      paramContext = paramContext.getInstalledPackages(8192).iterator();
      while (paramContext.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
        if ((localPackageInfo.packageName.equals("com.google.market")) || (localPackageInfo.packageName.equals("com.android.vending"))) {
          GooglePlayStoreInstalled = true;
        }
      }
      return;
    }
  }
  
  private static void CheckSamsungAppStore(Context paramContext)
  {
    paramContext = paramContext.getApplicationContext().getPackageManager();
    if (Build.VERSION.SDK_INT < 24) {}
    for (;;)
    {
      paramContext = paramContext.getInstalledPackages(8192).iterator();
      while (paramContext.hasNext()) {
        if (((PackageInfo)paramContext.next()).packageName.equals("com.sec.android.app.samsungapps")) {
          SamsungAppStoreInstalled = true;
        }
      }
      return;
    }
  }
  
  public static void MoreAppsOpen(String paramString, MoreAppsActivity.MoreAppsEntry paramMoreAppsEntry, Context paramContext)
  {
    boolean bool1 = false;
    if ((MainActivity.StoreId.equals("Amazon")) && (paramMoreAppsEntry.inAmazonStore)) {
      bool1 = TryAmazon(paramString, paramContext);
    }
    for (;;)
    {
      boolean bool2 = bool1;
      if (!bool1)
      {
        bool2 = bool1;
        if (paramMoreAppsEntry.inAmazonStore) {
          bool2 = TryAmazon(paramString, paramContext);
        }
      }
      bool1 = bool2;
      if (!bool2)
      {
        bool1 = bool2;
        if (paramMoreAppsEntry.inSamsungStore) {
          bool1 = TrySamsung(paramString, paramContext);
        }
      }
      bool2 = bool1;
      if (!bool1)
      {
        bool2 = bool1;
        if (paramMoreAppsEntry.inAptoideStore) {
          bool2 = TryAptoide(paramString, paramContext);
        }
      }
      if (!bool2) {}
      try
      {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString)));
        return;
      }
      catch (Exception paramMoreAppsEntry)
      {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + paramString)));
      }
      if ((MainActivity.StoreId.equals("Samsung")) && (paramMoreAppsEntry.inSamsungStore)) {
        bool1 = TrySamsung(paramString, paramContext);
      } else if ((MainActivity.StoreId.equals("Aptoide")) && (paramMoreAppsEntry.inAptoideStore)) {
        bool1 = TryAptoide(paramString, paramContext);
      } else if (MainActivity.StoreId.equals("GooglePlay")) {
        bool1 = TryGooglePlay(paramString, paramContext);
      }
    }
  }
  
  public static void RateAppOpen(String paramString, Context paramContext)
  {
    if (MainActivity.StoreId.equals("GooglePlay")) {}
    try
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString)));
      if (!MainActivity.StoreId.equals("Amazon")) {}
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      try
      {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("amzn://apps/android?p=" + paramString)));
        if (MainActivity.StoreId.equals("Samsung")) {}
        try
        {
          paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("samsungapps://ProductDetail/" + paramString)));
          return;
        }
        catch (Exception localException2)
        {
          paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString)));
        }
        localActivityNotFoundException = localActivityNotFoundException;
        paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + paramString)));
      }
      catch (Exception localException1)
      {
        for (;;)
        {
          paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString)));
        }
      }
    }
  }
  
  private static boolean TryAmazon(String paramString, Context paramContext)
  {
    if (AmazonAppStoreInstalled) {
      try
      {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("amzn://apps/android?p=" + paramString)));
        return true;
      }
      catch (Exception paramString) {}
    }
    return false;
  }
  
  private static boolean TryAptoide(String paramString, Context paramContext)
  {
    return false;
  }
  
  private static boolean TryGooglePlay(String paramString, Context paramContext)
  {
    if (GooglePlayStoreInstalled) {
      try
      {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString)));
        return true;
      }
      catch (Exception paramString) {}
    }
    return false;
  }
  
  private static boolean TrySamsung(String paramString, Context paramContext)
  {
    if (SamsungAppStoreInstalled) {
      try
      {
        paramContext.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("samsungapps://ProductDetail/" + paramString)));
        return true;
      }
      catch (Exception paramString) {}
    }
    return false;
  }
}
