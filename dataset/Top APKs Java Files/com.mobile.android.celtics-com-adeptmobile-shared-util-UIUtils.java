package com.adeptmobile.shared.util;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.StyleSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import com.adeptmobile.adeptsports.Settings;
import com.adeptmobile.adeptsports.ui.BaseActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.lang3.StringUtils;

public class UIUtils
{
  private static final String AmazonAppStorePackageName = "com.amazon.venezia";
  private static final int BRIGHTNESS_THRESHOLD = 130;
  private static final int DAY_MILLIS = 86400000;
  private static final String GooglePlayStorePackageNameNew = "com.android.vending";
  private static final String GooglePlayStorePackageNameOld = "com.google.market";
  private static final int HOUR_MILLIS = 3600000;
  private static final int MINUTE_MILLIS = 60000;
  private static final int SECOND_MILLIS = 1000;
  private static final int TIME_FLAGS = 32771;
  public static final TimeZone TIME_ZONE = ;
  private static final long sAppLoadTime = System.currentTimeMillis();
  private static StyleSpan sBoldSpan = new StyleSpan(1);
  
  public UIUtils() {}
  
  public static Spannable buildStyledSnippet(String paramString)
  {
    SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(paramString);
    int j = -1;
    int i = 0;
    for (;;)
    {
      int k = paramString.indexOf('{', j);
      if (k == -1) {
        break;
      }
      j = paramString.indexOf('}', k);
      localSpannableStringBuilder.delete(k - i, k - i + 1);
      localSpannableStringBuilder.delete(j - i - 1, j - i);
      localSpannableStringBuilder.setSpan(sBoldSpan, k - i, j - i - 1, 33);
      i += 2;
    }
    return localSpannableStringBuilder;
  }
  
  public static boolean canResolveActivity(Context paramContext, Intent paramIntent)
  {
    boolean bool = false;
    try
    {
      paramContext = paramContext.getPackageManager().resolveActivity(paramIntent, 0);
      if (paramContext != null) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean currentVersionSupportBigNotification()
  {
    return Build.VERSION.SDK_INT >= 16;
  }
  
  public static boolean currentVersionSupportLockScreenControls()
  {
    return Build.VERSION.SDK_INT >= 14;
  }
  
  public static int dipToPixel(float paramFloat, Context paramContext)
  {
    return Math.round(paramFloat * paramContext.getResources().getDisplayMetrics().density);
  }
  
  public static void finishedLeaguePrerollAd(Context paramContext)
  {
    LogUtils.LOGI("adeptsports_UI", "finishedLeaguePreroll");
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
  
  public static int getColumnWidth(Context paramContext, int paramInt)
  {
    return paramContext.getResources().getDisplayMetrics().widthPixels / paramInt;
  }
  
  public static long getCurrentTime(Context paramContext)
  {
    return System.currentTimeMillis();
  }
  
  public static String getDeviceId()
  {
    if (("android_id" != null) && ("android_id".length() > 0)) {
      return "android_id";
    }
    return "";
  }
  
  public static String getFormattedDate(long paramLong)
  {
    return getFormattedDate(paramLong, "MMM dd, yyyy hh:mm a z");
  }
  
  public static String getFormattedDate(long paramLong, String paramString)
  {
    long l = paramLong;
    if (paramLong < 1000000000000L) {
      l = paramLong * 1000L;
    }
    try
    {
      Date localDate = new Date(l);
      paramString = new SimpleDateFormat(paramString).format(localDate);
      return paramString;
    }
    catch (Exception paramString)
    {
      Log.e("UIUtils", "getFormattedDate FAILURE", paramString);
    }
    return "";
  }
  
  public static String getFormattedDate(String paramString1, String paramString2)
  {
    try
    {
      paramString1 = DateUtility.ParseRFC3339DateFormat(paramString1);
      paramString1 = new SimpleDateFormat(paramString2).format(paramString1);
      return paramString1;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return "";
  }
  
  public static String getImagePath(String paramString)
  {
    if (paramString.contains("http")) {}
    return paramString;
  }
  
  public static Point getScreenDimensions(Context paramContext)
  {
    paramContext = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    Point localPoint = new Point();
    if (Build.VERSION.SDK_INT >= 13)
    {
      paramContext.getSize(localPoint);
      return localPoint;
    }
    localPoint.x = paramContext.getWidth();
    localPoint.y = paramContext.getHeight();
    return localPoint;
  }
  
  public static String getTimeAgo(long paramLong, Context paramContext)
  {
    long l = paramLong;
    if (paramLong < 1000000000000L) {
      l = paramLong * 1000L;
    }
    paramLong = getCurrentTime(paramContext);
    if ((l > paramLong) || (l <= 0L))
    {
      paramLong = l - paramLong;
      if (paramLong > 259200000L) {
        return "in " + paramLong / 86400000L + " days";
      }
      if (paramLong > 172800000L) {
        return "tomorrow";
      }
      if (paramLong > 86400000L) {
        return "in " + paramLong / 3600000L + " hours";
      }
      if (paramLong > 5400000L) {
        return "in an hour";
      }
      if (paramLong > 3000000L) {
        return "in " + paramLong / 60000L + " minutes";
      }
      if (paramLong > 120000L) {
        return "in a minute";
      }
      return "just now";
    }
    paramLong -= l;
    if (paramLong < 60000L) {
      return "just now";
    }
    if (paramLong < 120000L) {
      return "a minute ago";
    }
    if (paramLong < 3000000L) {
      return paramLong / 60000L + " minutes ago";
    }
    if (paramLong < 5400000L) {
      return "an hour ago";
    }
    if (paramLong < 86400000L) {
      return paramLong / 3600000L + " hours ago";
    }
    if (paramLong < 172800000L) {
      return "yesterday";
    }
    return paramLong / 86400000L + " days ago";
  }
  
  public static String getTimeAgo(String paramString, Context paramContext)
  {
    return getTimeAgo(DateUtility.getRFP3339DateAsLong(paramString), paramContext);
  }
  
  public static boolean hasFroyo()
  {
    return Build.VERSION.SDK_INT >= 8;
  }
  
  public static boolean hasGingerbread()
  {
    return Build.VERSION.SDK_INT >= 9;
  }
  
  public static boolean hasHoneycomb()
  {
    return Build.VERSION.SDK_INT >= 11;
  }
  
  public static boolean hasHoneycombMR1()
  {
    return Build.VERSION.SDK_INT >= 12;
  }
  
  public static boolean hasICS()
  {
    return Build.VERSION.SDK_INT >= 14;
  }
  
  public static boolean hasICSMR1()
  {
    return Build.VERSION.SDK_INT >= 15;
  }
  
  public static boolean hasJellyBean()
  {
    return Build.VERSION.SDK_INT >= 16;
  }
  
  public static boolean hasKitKat()
  {
    return Build.VERSION.SDK_INT >= 19;
  }
  
  public static boolean isAppInstalled(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean isCallable(Context paramContext, Intent paramIntent)
  {
    return paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536).size() > 0;
  }
  
  public static boolean isColorDark(int paramInt)
  {
    return (Color.red(paramInt) * 30 + Color.green(paramInt) * 59 + Color.blue(paramInt) * 11) / 100 <= 130;
  }
  
  public static boolean isGoogleTV(Context paramContext)
  {
    return paramContext.getPackageManager().hasSystemFeature("com.google.android.tv");
  }
  
  public static boolean isHoneycombTablet(Context paramContext)
  {
    return (hasHoneycomb()) && (isTablet(paramContext));
  }
  
  public static boolean isNotificationFiredForAlert(Context paramContext, String paramString)
  {
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    paramString = String.format("notification_fired_%s", new Object[] { paramString });
    boolean bool = paramContext.getBoolean(paramString, false);
    paramContext.edit().putBoolean(paramString, true).commit();
    return bool;
  }
  
  public static boolean isSameDay(long paramLong1, long paramLong2)
  {
    TimeZone.setDefault(TIME_ZONE);
    Calendar localCalendar1 = Calendar.getInstance();
    Calendar localCalendar2 = Calendar.getInstance();
    localCalendar1.setTimeInMillis(paramLong1);
    localCalendar2.setTimeInMillis(paramLong2);
    return (localCalendar1.get(1) == localCalendar2.get(1)) && (localCalendar1.get(6) == localCalendar2.get(6));
  }
  
  public static boolean isServiceRunning(String paramString, Context paramContext)
  {
    paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningServices(Integer.MAX_VALUE).iterator();
    while (paramContext.hasNext()) {
      if (paramString.equals(((ActivityManager.RunningServiceInfo)paramContext.next()).service.getClassName())) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isTablet(Context paramContext)
  {
    return (paramContext.getResources().getConfiguration().screenLayout & 0xF) >= 3;
  }
  
  public static boolean isUsingUSLocale()
  {
    return (Locale.getDefault() != null) && (Locale.getDefault().getCountry().equalsIgnoreCase("us"));
  }
  
  public static boolean loadProfileForExternalApp(Context paramContext, String paramString1, String paramString2)
  {
    boolean bool = false;
    try
    {
      paramString1 = Uri.parse(paramString1 + paramString2);
      LogUtils.LOGI("adeptsports_UI", paramString1.toString());
      paramString1 = new Intent("android.intent.action.VIEW", paramString1);
      if (canResolveActivity(paramContext, paramString1))
      {
        paramString1.addFlags(268435456);
        paramContext.startActivity(paramString1);
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static void loadTuneInRadioIfAbleFromUrl(Context paramContext, String paramString)
  {
    if ((isAppInstalled(paramContext, "radiotime.player")) || (isAppInstalled(paramContext, "tunein.player"))) {
      try
      {
        String[] arrayOfString = StringUtils.split(paramString, "-");
        boolean bool = loadProfileForExternalApp(paramContext, "tunein://profile/", arrayOfString[(arrayOfString.length - 1)]);
        if (bool) {
          return;
        }
      }
      catch (Exception localException) {}
    }
    loadUrlInBrowser(paramContext, paramString);
  }
  
  public static void loadTweet(BaseActivity paramBaseActivity, String paramString1, String paramString2)
  {
    LogUtils.LOGI("adeptsports_UI", "Loading tweet with status: " + paramString1);
    try
    {
      if (loadProfileForExternalApp(paramBaseActivity, "twitter://status?status_id=", paramString1))
      {
        LogUtils.LOGI("adeptsports_UI", "Loaded in twitter");
        return;
      }
    }
    catch (Exception localException)
    {
      do
      {
        localException.printStackTrace();
      } while ((paramBaseActivity == null) || (paramString2 == null) || (paramString2.length() <= 0));
      LogUtils.LOGI("adeptsports_UI", "Loading tweet in popover: " + paramString1);
      if ((paramString2.contains("m.twitter")) || (paramString2.contains("mobile.twitter")))
      {
        paramBaseActivity.showPopoverWebview(paramString2);
        return;
      }
      paramBaseActivity.showPopoverWebview(paramString2.replace("twitter.com", "mobile.twitter.com"));
    }
  }
  
  public static void loadUrlInBrowser(Context paramContext, String paramString)
  {
    if ((paramString != null) && (paramString.length() > 0)) {}
    try
    {
      paramContext.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse(paramString)));
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static void preferPackageForIntent(Context paramContext, Intent paramIntent, String paramString)
  {
    paramContext = paramContext.getPackageManager().queryIntentActivities(paramIntent, 0).iterator();
    while (paramContext.hasNext()) {
      if (((ResolveInfo)paramContext.next()).activityInfo.packageName.equals(paramString)) {
        paramIntent.setPackage(paramString);
      }
    }
  }
  
  public static void resetVideoCount(Context paramContext, String paramString)
  {
    LogUtils.LOGI("adeptsports_UI", "reset video count for: " + paramString);
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    try
    {
      paramContext.edit().putInt(paramString, 0).commit();
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static void safeOpenLink(Context paramContext, Intent paramIntent)
  {
    try
    {
      paramContext.startActivity(paramIntent);
      return;
    }
    catch (ActivityNotFoundException paramIntent)
    {
      Toast.makeText(paramContext, "Couldn't open link", 0).show();
    }
  }
  
  @TargetApi(11)
  public static void setActivatedCompat(View paramView, boolean paramBoolean)
  {
    if (hasHoneycomb()) {
      paramView.setActivated(paramBoolean);
    }
  }
  
  public static void setTextMaybeHtml(TextView paramTextView, String paramString)
  {
    if (TextUtils.isEmpty(paramString))
    {
      paramTextView.setText("");
      return;
    }
    if ((paramString.contains("<")) && (paramString.contains(">")))
    {
      paramTextView.setText(Html.fromHtml(paramString));
      paramTextView.setMovementMethod(LinkMovementMethod.getInstance());
      paramTextView.setFocusable(false);
      return;
    }
    paramTextView.setText(paramString);
  }
  
  public static boolean shouldShowLeaguePrerollAd(Context paramContext)
  {
    if (!Settings.hasLeaguePrerollAd()) {
      return false;
    }
    LogUtils.LOGI("adeptsports_UI", "shouldShowLeaguePrerollAd()");
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    try
    {
      long l = localSharedPreferences.getLong("pref_league_preroll_lastshownat", 0L);
      LogUtils.LOGI("adeptsports_UI", "Last Shown At: " + l);
      if (System.currentTimeMillis() > paramContext.getResources().getInteger(2131427339) * 3600000 + l) {
        return true;
      }
      LogUtils.LOGI("adeptsports_UI", "League video reset timer not met. Checking how many videos watched");
      int i = localSharedPreferences.getInt("pref_league_preroll_count", 0);
      LogUtils.LOGI("adeptsports_UI", "League video reset timer not met. Videos Watched: " + i);
      if (i % 3 == 0)
      {
        LogUtils.LOGI("adeptsports_UI", "League video reset timer not met. Should show preroll");
        return true;
      }
      LogUtils.LOGI("adeptsports_UI", "League video reset timer not met. Skipping Preroll");
      return false;
    }
    catch (Exception paramContext) {}
    return true;
  }
  
  public static boolean shouldShowPrerollAd(Context paramContext)
  {
    if (!Settings.hasPrerollAd()) {
      return false;
    }
    LogUtils.LOGI("adeptsports_UI", "shouldShowPrerollAd()");
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    try
    {
      long l = localSharedPreferences.getLong("pref_preroll_lastshownat", 0L);
      LogUtils.LOGI("adeptsports_UI", "Club Last Shown At: " + l);
      if (System.currentTimeMillis() > paramContext.getResources().getInteger(2131427339) * 3600000 + l) {
        return true;
      }
      LogUtils.LOGI("adeptsports_UI", "video reset timer not met. Checking how many videos watched");
      int i = localSharedPreferences.getInt("pref_club_preroll_count", 0);
      LogUtils.LOGI("adeptsports_UI", "video reset timer not met. Videos Watched: " + i);
      if (i % 3 == 0)
      {
        LogUtils.LOGI("adeptsports_UI", "video reset timer not met. Should show preroll");
        return true;
      }
      LogUtils.LOGI("adeptsports_UI", "video reset timer not met. Skipping Preroll");
      return false;
    }
    catch (Exception paramContext) {}
    return true;
  }
  
  public static boolean shouldShowSplashScreen(Context paramContext)
  {
    if (paramContext == null) {}
    long l;
    do
    {
      return true;
      l = PreferenceManager.getDefaultSharedPreferences(paramContext).getLong(Settings.getSplashPrefKey(), 0L);
    } while ((l == 0L) || (l < getCurrentTime(paramContext) - Settings.splashTimeout()) || (wasAppUpdated(paramContext)));
    return false;
  }
  
  public static void showCantPlayVideoDialog(Context paramContext)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(paramContext.getResources().getString(2131166109));
    localBuilder.setIcon(17301543).setMessage(paramContext.getResources().getString(2131165304)).setPositiveButton(paramContext.getResources().getString(2131165564), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        this.val$context.startActivity(new Intent("android.intent.action.VIEW").setData(Uri.parse("market://search?q=Video Player")));
      }
    }).setCancelable(false).setNegativeButton(paramContext.getResources().getString(2131165378), new DialogInterface.OnClickListener()
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
    localBuilder.setTitle(paramContext.getResources().getString(2131165371));
    localBuilder.setIcon(17301543).setMessage(paramContext.getResources().getString(2131165374)).setCancelable(false).setNegativeButton(paramContext.getResources().getString(2131165378), new DialogInterface.OnClickListener()
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
  
  public static boolean wasAppUpdated(Context paramContext)
  {
    boolean bool = false;
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      if (localSharedPreferences.getInt(Settings.getVersionPrefKey(), 0) < paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode)
      {
        localSharedPreferences.edit().putInt(Settings.getVersionPrefKey(), i).commit();
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static void watchedLeagueVideo(Context paramContext)
  {
    LogUtils.LOGI("adeptsports_UI", "watchedLeagueVideo");
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    try
    {
      int i = paramContext.getInt("pref_league_preroll_count", 0);
      LogUtils.LOGI("adeptsports_UI", "current league video count: " + i);
      paramContext.edit().putInt("pref_league_preroll_count", i + 1).commit();
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static void watchedVideo(Context paramContext)
  {
    LogUtils.LOGI("adeptsports_UI", "watchedVideo");
    paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext);
    try
    {
      int i = paramContext.getInt("pref_club_preroll_count", 0);
      LogUtils.LOGI("adeptsports_UI", "current video count: " + i);
      paramContext.edit().putInt("pref_club_preroll_count", i + 1).commit();
      return;
    }
    catch (Exception paramContext) {}
  }
}
