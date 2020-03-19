package com.adeptmobile.adeptsportslibrary.util.ui;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.widget.Toast;
import com.adeptmobile.adeptsportslibrary.settings.Settings;
import com.adeptmobile.amosports.shared.networking.Urls;
import com.adeptmobile.amosports.util.logging.LogUtil;
import java.util.Iterator;
import java.util.List;

public class UIUtils
{
  private static final String AmazonAppStorePackageName = "com.amazon.venezia";
  private static final String GooglePlayStorePackageNameNew = "com.android.vending";
  private static final String GooglePlayStorePackageNameOld = "com.google.market";
  private static final long HOUR_MILLIS = 3600000L;
  private static final String PREF_APP_VERSION = "pref_app_updated";
  private static final String PREF_INITIAL_LOAD = "pref_initial_load";
  private static final String PREF_SPLASH_LAST_SHOWN_AT = "pref_splash_last_shown";
  private static final long SPLASH_SHOW_INTERVAL = 86400000L;
  
  public UIUtils() {}
  
  public static boolean currentVersionSupportBigNotification()
  {
    return Build.VERSION.SDK_INT >= 16;
  }
  
  public static boolean currentVersionSupportLockScreenControls()
  {
    return Build.VERSION.SDK_INT >= 14;
  }
  
  public static void finishedLeaguePrerollAd(Context paramContext)
  {
    LogUtil.i("adeptsports_UI", "finishedLeaguePreroll");
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    try
    {
      paramContext.edit().putLong("pref_league_preroll_lastshownat", System.currentTimeMillis()).commit();
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static void finishedPrerollAd(Context paramContext)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    try
    {
      paramContext.edit().putLong("pref_preroll_lastshownat", System.currentTimeMillis()).commit();
      return;
    }
    catch (Exception paramContext) {}
  }
  
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
  
  public static boolean isConnectedToNetwork(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext != null)
      {
        boolean bool = paramContext.isConnected();
        if (bool) {
          return true;
        }
      }
      return false;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean isInitialLoadComplete(Context paramContext)
  {
    boolean bool1 = false;
    try
    {
      boolean bool2 = PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("pref_initial_load", false);
      if (bool2) {
        bool1 = true;
      }
      return bool1;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean isLollipop()
  {
    return Build.VERSION.SDK_INT >= 21;
  }
  
  public static void setInitialLoadComplete(Context paramContext)
  {
    try
    {
      PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putBoolean("pref_initial_load", true).commit();
      return;
    }
    catch (Exception paramContext)
    {
      LogUtil.e("BSO_UI", paramContext.getMessage());
      paramContext.printStackTrace();
    }
  }
  
  public static boolean shouldShowLeaguePrerollAd(Context paramContext)
  {
    LogUtil.i("adeptsports_UI", "shouldShowLeaguePrerollAd()");
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    try
    {
      long l = localSharedPreferences.getLong("pref_league_preroll_lastshownat", 0L);
      LogUtil.i("adeptsports_UI", "Last Shown At: " + l);
      if (System.currentTimeMillis() > paramContext.getResources().getInteger(2131492875) * 3600000L + l) {
        return true;
      }
      LogUtil.i("adeptsports_UI", "League video reset timer not met. Checking how many videos watched");
      int i = localSharedPreferences.getInt("pref_league_preroll_count", 0);
      LogUtil.i("adeptsports_UI", "League video reset timer not met. Videos Watched: " + i);
      if (i % 3 == 0)
      {
        LogUtil.i("adeptsports_UI", "League video reset timer not met. Should show preroll");
        return true;
      }
      LogUtil.i("adeptsports_UI", "League video reset timer not met. Skipping Preroll");
      return false;
    }
    catch (Exception paramContext) {}
    return true;
  }
  
  public static boolean shouldShowPrerollAd(Context paramContext)
  {
    if (Urls.getPrerollAdUrl() == null) {}
    for (;;)
    {
      return false;
      if (!Urls.getPrerollAdUrl().isEmpty())
      {
        SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
        try
        {
          long l = localSharedPreferences.getLong("pref_preroll_lastshownat", 0L);
          if (System.currentTimeMillis() > paramContext.getResources().getInteger(2131492875) * 3600000L + l) {
            return true;
          }
          int i = localSharedPreferences.getInt("pref_club_preroll_count", 0);
          if (i % 3 == 0) {
            return true;
          }
        }
        catch (Exception paramContext) {}
      }
    }
    return true;
  }
  
  public static boolean shouldShowSplash(Context paramContext)
  {
    try
    {
      if (wasAppUpdatedNoUpdate(paramContext))
      {
        PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putLong("pref_splash_last_shown", System.currentTimeMillis()).commit();
        return true;
      }
      if (86400000L + PreferenceManager.getDefaultSharedPreferences(paramContext).getLong("pref_splash_last_shown", 0L) > System.currentTimeMillis())
      {
        PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putLong("pref_splash_last_shown", System.currentTimeMillis()).commit();
        return true;
      }
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static void showAudioToast(Context paramContext)
  {
    Toast.makeText(paramContext, "Playing audio. Controls are located in your device's notification panel.", 0).show();
  }
  
  public static void showCantPlayVideoDialog(Context paramContext)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle("Video Player");
    localBuilder.setIcon(17301543).setMessage(paramContext.getResources().getString(2131230891)).setPositiveButton("Store", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        this.val$context.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("market://search?q=Video Player")));
      }
    }).setCancelable(false).setNegativeButton("Ok", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    localBuilder.create().show();
  }
  
  public static void showDialog(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = new AlertDialog.Builder(paramContext);
    DialogInterface.OnClickListener local2 = new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    };
    paramContext.setTitle(paramString1);
    paramContext.setMessage(paramString2);
    paramContext.setCancelable(true);
    paramContext.setPositiveButton("OK", local2);
    paramContext.create().show();
  }
  
  public static void showNoConnectionDialog(Context paramContext)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(paramContext.getResources().getString(2131231567));
    localBuilder.setIcon(17301543).setMessage("Please connect to a network to get the latest data.").setCancelable(false).setNegativeButton("Ok", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    localBuilder.create().show();
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
  
  public static boolean wasAppUpdated(Context paramContext)
  {
    boolean bool = false;
    try
    {
      if (Settings.getVersionCode() > PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("pref_app_updated", 0))
      {
        PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putInt("pref_app_updated", Settings.getVersionCode()).apply();
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean wasAppUpdatedNoUpdate(Context paramContext)
  {
    boolean bool = false;
    try
    {
      int i = Settings.getVersionCode();
      int j = PreferenceManager.getDefaultSharedPreferences(paramContext).getInt("pref_app_updated", 0);
      if (i > j) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static void watchedLeagueVideo(Context paramContext)
  {
    LogUtil.i("adeptsports_UI", "watchedLeagueVideo");
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    try
    {
      int i = paramContext.getInt("pref_league_preroll_count", 0);
      LogUtil.i("adeptsports_UI", "current league video count: " + i);
      paramContext.edit().putInt("pref_league_preroll_count", i + 1).commit();
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static void watchedVideo(Context paramContext)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    try
    {
      int i = paramContext.getInt("pref_club_preroll_count", 0);
      paramContext.edit().putInt("pref_club_preroll_count", i + 1).commit();
      return;
    }
    catch (Exception paramContext) {}
  }
}
