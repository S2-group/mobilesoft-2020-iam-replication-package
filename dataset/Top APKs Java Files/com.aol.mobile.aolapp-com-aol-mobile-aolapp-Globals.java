package com.aol.mobile.aolapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.p;
import com.aol.mobile.aolapp.eventmanagement.EventManager;
import com.aol.mobile.aolapp.mail.DataModel;
import com.aol.mobile.aolapp.mail.MailGlobals;
import com.aol.mobile.aolapp.manager.MyFeedManager;
import com.aol.mobile.aolapp.ui.activity.ArticleDetailsActivityCis;
import com.aol.mobile.aolapp.util.Utils;
import com.aol.mobile.aolapp.util.VolleyConnector;
import com.aol.mobile.mailcore.Logging.Logger;
import com.mobileapptracker.MobileAppTracker;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import tv.freewheel.ad.interfaces.IAdManager;

public final class Globals
{
  private static final String TAG = Globals.class.getSimpleName();
  private static String debugAsFutureDate = "";
  private static String debugUserSelectedBannerId = "";
  private static String deviceMd5hash;
  public static IAdManager freewheelAdManager;
  public static boolean isLargeTablet;
  public static boolean isSmallTablet;
  public static boolean isTablet;
  private static Typeface mBoldFont;
  private static EventManager mEventManager;
  private static boolean mInitializedFonts;
  private static Typeface mMediumFont;
  private static MobileAppTracker mMobileAppTracker = null;
  private static Typeface mRegularFont;
  private static Resources mRes;
  private static Typeface mWeatherFont;
  private static boolean mailSelectAllMode;
  public static boolean useProdVideoAds;
  
  static
  {
    mInitializedFonts = false;
    mEventManager = new EventManager();
    freewheelAdManager = null;
    useProdVideoAds = true;
    isTablet = false;
    isSmallTablet = false;
    isLargeTablet = false;
  }
  
  protected Globals() {}
  
  public static boolean didRunOnboardMainScreen(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean("com.aol.mobile.aolapp.util.Constants.ONBOARD_MAIN_2.0.5", false);
  }
  
  public static Resources getApplicationResources()
  {
    return mRes;
  }
  
  public static boolean getAudioMuted(Activity paramActivity)
  {
    boolean bool = false;
    SharedPreferences localSharedPreferences = Utils.getSharedPreferences();
    if (localSharedPreferences.contains("SHARED_PREF_AUDIO_MUTED")) {
      bool = localSharedPreferences.getBoolean("SHARED_PREF_AUDIO_MUTED", false);
    }
    while (!(paramActivity instanceof ArticleDetailsActivityCis)) {
      return bool;
    }
    return Utils.isAutoPlayVideoEnabled(paramActivity);
  }
  
  public static String getDebugAsFutureDate()
  {
    return debugAsFutureDate;
  }
  
  public static String getDebugUserSelectedBannerId()
  {
    return debugUserSelectedBannerId;
  }
  
  public static String getDeviceMd5Hash()
  {
    return deviceMd5hash;
  }
  
  public static MyFeedManager getEditionManager()
  {
    return MyFeedManager.getInstance();
  }
  
  public static EventManager getEventManager()
  {
    return mEventManager;
  }
  
  public static String getLastVisitedActivity()
  {
    String str = "LAST_VISITED_CONTENT_VIEW";
    SharedPreferences localSharedPreferences = Utils.getSharedPreferences();
    if (localSharedPreferences.contains("LAST_VISITED_ACTIVITY")) {
      str = localSharedPreferences.getString("LAST_VISITED_ACTIVITY", "LAST_VISITED_CONTENT_VIEW");
    }
    return str;
  }
  
  public static DataModel getMailDataModel()
  {
    return MailGlobals.getDataModel();
  }
  
  public static MobileAppTracker getMobileAppTracker()
  {
    return mMobileAppTracker;
  }
  
  public static p getRequestQueue()
  {
    return VolleyConnector.getRequestQueue();
  }
  
  public static Typeface getWeatherFontTypeFace()
  {
    if (mWeatherFont == null) {
      initFonts(AolclientApplication.getAppContext());
    }
    return mWeatherFont;
  }
  
  public static void initFonts()
  {
    Logger.d(TAG, "initFonts()");
    if (!mInitializedFonts)
    {
      mInitializedFonts = true;
      initFonts(AolclientApplication.getAppContext());
    }
  }
  
  private static void initFonts(Context paramContext)
  {
    paramContext = paramContext.getAssets();
    mWeatherFont = Typeface.createFromAsset(paramContext, "font/Wif/wif.ttf");
    mRegularFont = Typeface.createFromAsset(paramContext, String.format(Locale.US, "font/Brown/Brown_%s.otf", new Object[] { "Regular" }));
    mMediumFont = Typeface.createFromAsset(paramContext, String.format(Locale.US, "font/Brown/Brown_%s.otf", new Object[] { "Regular" }));
    mBoldFont = Typeface.createFromAsset(paramContext, String.format(Locale.US, "font/Brown/Brown_%s.otf", new Object[] { "Bold" }));
  }
  
  public static void initMobileAppTracker(Context paramContext)
  {
    mMobileAppTracker = MobileAppTracker.init(paramContext, "188488", "f47b627c6ba05de02712a9be843e6237");
  }
  
  public static boolean isInitializedFonts()
  {
    return mInitializedFonts;
  }
  
  public static boolean isNetworkAvailable()
  {
    try
    {
      arrayOfNetworkInfo = ((ConnectivityManager)AolclientApplication.getAppContext().getSystemService("connectivity")).getAllNetworkInfo();
      i1 = arrayOfNetworkInfo.length;
      n = 0;
      i = 0;
      j = 0;
    }
    catch (Exception localException1)
    {
      try
      {
        NetworkInfo[] arrayOfNetworkInfo;
        int i1;
        int n;
        NetworkInfo localNetworkInfo;
        if ("WIFI".equalsIgnoreCase(localNetworkInfo.getTypeName()))
        {
          k = j;
          m = j;
          if (localNetworkInfo.isConnected()) {
            k = 1;
          }
        }
        int j = i;
        m = k;
        if ("MOBILE".equalsIgnoreCase(localNetworkInfo.getTypeName()))
        {
          m = k;
          boolean bool = localNetworkInfo.isConnected();
          j = i;
          if (bool) {
            j = 1;
          }
        }
        n += 1;
        i = j;
        j = k;
      }
      catch (Exception localException2)
      {
        int i;
        int k;
        int m;
        for (;;) {}
      }
      localException1 = localException1;
      i = 0;
      m = 0;
    }
    k = i;
    m = j;
    if (n < i1)
    {
      localNetworkInfo = arrayOfNetworkInfo[n];
      k = j;
      m = j;
      Logger.e(TAG, "#isNetworkAvailable() exception", localException1);
      k = i;
    }
    return (m != 0) || (k != 0);
  }
  
  public static boolean isPackageExists(String paramString)
  {
    Iterator localIterator = AolclientApplication.getAppContext().getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isSelectAllMode()
  {
    return mailSelectAllMode;
  }
  
  public static void removeAudioMuted()
  {
    Utils.getSharedPreferences().edit().remove("SHARED_PREF_AUDIO_MUTED").apply();
  }
  
  public static void setApplicationResources(Resources paramResources)
  {
    mRes = paramResources;
  }
  
  public static void setAudioMuted(boolean paramBoolean)
  {
    Utils.getSharedPreferences().edit().putBoolean("SHARED_PREF_AUDIO_MUTED", paramBoolean).apply();
  }
  
  public static void setBoldTypeFace(TextView paramTextView)
  {
    setTypeface(paramTextView, mBoldFont);
  }
  
  public static void setDebugAsFutureDate(String paramString)
  {
    debugAsFutureDate = paramString;
  }
  
  public static void setDebugUserSelectedBannerId(String paramString)
  {
    debugUserSelectedBannerId = paramString;
  }
  
  public static void setDeviceMd5Hash(String paramString)
  {
    deviceMd5hash = paramString;
  }
  
  public static void setLastVisitedActivity(String paramString)
  {
    Utils.getSharedPreferences().edit().putString("LAST_VISITED_ACTIVITY", paramString).apply();
  }
  
  public static void setMediumTypeFace(TextView paramTextView)
  {
    setTypeface(paramTextView, mMediumFont);
  }
  
  public static void setOnboardingMainScreenDone(Context paramContext, boolean paramBoolean)
  {
    PreferenceManager.getDefaultSharedPreferences(paramContext).edit().putBoolean("com.aol.mobile.aolapp.util.Constants.ONBOARD_MAIN_2.0.5", paramBoolean).apply();
  }
  
  public static void setRegularTypeFace(EditText paramEditText)
  {
    setTypeface(paramEditText, mRegularFont);
  }
  
  public static void setRegularTypeFace(TextView paramTextView)
  {
    setTypeface(paramTextView, mRegularFont);
  }
  
  public static void setSelectAllMode(boolean paramBoolean)
  {
    mailSelectAllMode = paramBoolean;
  }
  
  public static void setThinTypeFace(TextView paramTextView)
  {
    setTypeface(paramTextView, mRegularFont);
  }
  
  private static void setTypeface(EditText paramEditText, Typeface paramTypeface)
  {
    if (paramTypeface == null) {
      initFonts(AolclientApplication.getAppContext());
    }
    if ((paramTypeface != null) && (paramEditText != null)) {
      paramEditText.setTypeface(paramTypeface);
    }
  }
  
  private static void setTypeface(TextView paramTextView, Typeface paramTypeface)
  {
    if (paramTypeface == null) {
      initFonts(AolclientApplication.getAppContext());
    }
    if ((paramTypeface != null) && (paramTextView != null)) {
      paramTextView.setTypeface(paramTypeface);
    }
  }
  
  public static void setWeatherFontTypeFace(TextView paramTextView)
  {
    setTypeface(paramTextView, mWeatherFont);
  }
}
