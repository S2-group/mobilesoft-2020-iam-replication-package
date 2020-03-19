package com.chineseskill.market.googleplay;

import android.app.Activity;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import com.chineseskill.utility.PhoneUtil;
import java.util.Iterator;
import java.util.List;

public class GooglePlayRater
{
  private static final String GooglePlayStorePackageNameNew = "com.android.vending";
  private static final String GooglePlayStorePackageNameOld = "com.google.market";
  
  public GooglePlayRater() {}
  
  public static void goToMarket(Activity paramActivity)
  {
    openGooglePlayApp(paramActivity, "com.chineseskill");
  }
  
  public static boolean isGooglePlayMarketInstalled(Activity paramActivity)
  {
    paramActivity = paramActivity.getApplication().getPackageManager().getInstalledPackages(8192).iterator();
    while (paramActivity.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramActivity.next();
      if ((localPackageInfo.packageName.equals("com.google.market")) || (localPackageInfo.packageName.equals("com.android.vending"))) {
        return true;
      }
    }
    return false;
  }
  
  private static boolean open(Context paramContext, String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + paramString));
    boolean bool2 = false;
    Object localObject = paramContext.getPackageManager().queryIntentActivities(paramString, 0).iterator();
    ResolveInfo localResolveInfo;
    String str;
    do
    {
      bool1 = bool2;
      if (!((Iterator)localObject).hasNext()) {
        break;
      }
      localResolveInfo = (ResolveInfo)((Iterator)localObject).next();
      str = localResolveInfo.activityInfo.applicationInfo.packageName;
    } while ((!str.equals("com.android.vending")) && (!str.equals("com.google.market")));
    localObject = localResolveInfo.activityInfo;
    paramString.setComponent(new ComponentName(((ActivityInfo)localObject).applicationInfo.packageName, ((ActivityInfo)localObject).name));
    paramContext.startActivity(paramString);
    boolean bool1 = true;
    return bool1;
  }
  
  public static void openGooglePlayApp(Activity paramActivity, String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (isGooglePlayMarketInstalled(paramActivity)) {}
    try
    {
      bool1 = open(paramActivity, paramString);
      if (bool1) {}
    }
    catch (Exception localException)
    {
      for (;;)
      {
        try
        {
          Intent localIntent = new Intent("android.intent.action.VIEW");
          localIntent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + paramString));
          paramActivity.startActivity(localIntent);
          return;
        }
        catch (ActivityNotFoundException localActivityNotFoundException)
        {
          PhoneUtil.toast(paramActivity, "Can't open it! Please search " + paramString + " in Google Play Store.");
        }
        localException = localException;
        localException.printStackTrace();
        bool1 = bool2;
      }
    }
  }
}
