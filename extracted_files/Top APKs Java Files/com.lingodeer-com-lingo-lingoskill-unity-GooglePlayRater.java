package com.lingo.lingoskill.unity;

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
import com.lingo.lingoskill.base.c.e;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class GooglePlayRater
{
  private static final String GooglePlayStorePackageNameNew = "com.android.vending";
  private static final String GooglePlayStorePackageNameOld = "com.google.market";
  
  public GooglePlayRater() {}
  
  public static void goToMarket(Activity paramActivity)
  {
    openGooglePlayApp(paramActivity, "com.lingodeer");
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
    Object localObject = new StringBuilder("market://details?id=");
    ((StringBuilder)localObject).append(paramString);
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(((StringBuilder)localObject).toString()));
    localObject = paramContext.getPackageManager();
    boolean bool2 = false;
    localObject = ((PackageManager)localObject).queryIntentActivities(paramString, 0).iterator();
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
    boolean bool;
    if (isGooglePlayMarketInstalled(paramActivity)) {
      try
      {
        bool = open(paramActivity, paramString);
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    } else {
      bool = false;
    }
    if (!bool) {}
    try
    {
      localObject = new Intent("android.intent.action.VIEW");
      StringBuilder localStringBuilder = new StringBuilder("https://play.google.com/store/apps/details?id=");
      localStringBuilder.append(paramString);
      ((Intent)localObject).setData(Uri.parse(localStringBuilder.toString()));
      paramActivity.startActivity((Intent)localObject);
      return;
    }
    catch (ActivityNotFoundException paramActivity)
    {
      Object localObject;
      for (;;) {}
    }
    paramActivity = e.a;
    paramActivity = Locale.getDefault();
    localObject = e.a;
    e.a(String.format(paramActivity, e.b(2131755203), new Object[] { paramString }));
  }
}
