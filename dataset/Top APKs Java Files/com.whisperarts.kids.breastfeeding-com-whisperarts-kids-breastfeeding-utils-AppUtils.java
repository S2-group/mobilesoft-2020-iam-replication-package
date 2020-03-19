package com.whisperarts.kids.breastfeeding.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Toast;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.Tracker;
import com.whisperarts.kids.breastfeeding.BreastFeedingApplication;
import com.whisperarts.library.common.markets.Market;
import com.whisperarts.library.common.utils.Config;
import java.util.Iterator;
import java.util.List;
import org.acra.ACRA;
import org.acra.ErrorReporter;

public class AppUtils
{
  public AppUtils() {}
  
  public static String getFullVersionPackage()
  {
    if (BreastFeedingApplication.MARKET.usesKey) {
      return "com.whisperarts.kids.breastfeeding.key";
    }
    return "com.whisperarts.kids.breasfeeding.full";
  }
  
  public static boolean isFullVersion(Context paramContext)
  {
    boolean bool = false;
    if (BreastFeedingApplication.MARKET.usesKey) {
      bool = PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean(paramContext.getString(2131099713), false);
    }
    return bool;
  }
  
  public static boolean isKeyInstalled(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(128).iterator();
    ApplicationInfo localApplicationInfo;
    do
    {
      if (!paramContext.hasNext()) {
        return false;
      }
      localApplicationInfo = (ApplicationInfo)paramContext.next();
    } while (!"com.whisperarts.kids.breastfeeding.key".equals(localApplicationInfo.packageName));
    return localApplicationInfo.metaData.getBoolean("isFullVersion", false);
  }
  
  public static void log(Exception paramException, String paramString)
  {
    if (Config.DEBUG) {
      return;
    }
    try
    {
      ErrorReporter localErrorReporter = ACRA.getErrorReporter();
      localErrorReporter.putCustomData("custom", paramString);
      localErrorReporter.handleSilentException(paramException);
      return;
    }
    catch (Exception paramException) {}
  }
  
  public static boolean removeFullVersionFeatures()
  {
    return (BreastFeedingApplication.MARKET == Market.AMAZON) || (BreastFeedingApplication.MARKET == Market.BLACKBERRY);
  }
  
  public static void restart(Activity paramActivity)
  {
    if (Build.VERSION.SDK_INT < 11)
    {
      Intent localIntent = paramActivity.getIntent();
      localIntent.addFlags(65536);
      paramActivity.finish();
      paramActivity.startActivity(localIntent);
      return;
    }
    paramActivity.recreate();
  }
  
  public static void showToast(Context paramContext, String paramString)
  {
    showToast(paramContext, paramString, true);
  }
  
  public static void showToast(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (paramBoolean) {}
    for (int i = 1;; i = 0)
    {
      Toast.makeText(paramContext, paramString, i).show();
      return;
    }
  }
  
  public static void track(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    if (Config.DEBUG) {
      return;
    }
    try
    {
      EasyTracker.getInstance().setContext(paramContext);
      EasyTracker.getTracker().trackEvent(paramString1, paramString2, paramString3, null);
      return;
    }
    catch (Exception paramContext)
    {
      log(paramContext, "error tracking: " + paramString1 + ", " + paramString2 + ", " + paramString3);
    }
  }
}
