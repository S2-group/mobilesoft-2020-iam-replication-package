package com.ridecharge.android.taximagic;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.flurry.android.FlurryAgent;
import com.ridecharge.android.rclibrary.AppProperties;
import com.ridecharge.android.rclibrary.service.GetDriverRatingQuestionsCommand;
import com.ridecharge.android.rclibrary.service.ServerCommand;
import com.ridecharge.android.rclibrary.state.AppState;
import com.ridecharge.android.rclibrary.util.MessageListener;
import com.ridecharge.android.rclibrary.util.OAuthHelper;
import com.ridecharge.android.taximagic.util.TaxiMagicReceiver;
import com.ridecharge.android.taximagic.view.BusyScreen;
import com.ridecharge.android.taximagic.view.RateDriverScreen;
import com.urbanairship.UAirship;
import com.urbanairship.push.PushManager;
import com.urbanairship.push.PushPreferences;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class TaxiMagicApplication
  extends Application
{
  private static final String TAG = TaxiMagicApplication.class.getSimpleName();
  private static TaxiMagicApplication instance;
  private RequestToken currentRequestToken;
  private final boolean doExitApplicationMessage = false;
  private final boolean doVisibilityTimer = false;
  private final long exitOnHiddenTime = 1200000L;
  private boolean exiting = false;
  private final Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      int i = paramAnonymousMessage.what;
      for (;;)
      {
        int j;
        boolean bool;
        synchronized (TaxiMagicApplication.this.messageListeners)
        {
          j = TaxiMagicApplication.this.messageListeners.size();
          bool = false;
          i = 0;
          break label169;
          if (!bool) {}
          switch (paramAnonymousMessage.what)
          {
          case 63: 
            return;
            bool = ((MessageListener)TaxiMagicApplication.this.messageListeners.get(i)).handleAppMessage(paramAnonymousMessage);
            i += 1;
            break label169;
            if (TaxiMagicApplication.this.exiting) {
              continue;
            }
            TaxiMagicApplication.this.promptUserToRateDriver();
          }
        }
        paramAnonymousMessage = new Intent(TaxiMagicApplication.this.getApplicationContext(), RateDriverScreen.class);
        paramAnonymousMessage.setFlags(268435456);
        TaxiMagicApplication.this.getApplicationContext().startActivity(paramAnonymousMessage);
        continue;
        label169:
        if (!bool) {
          if (i >= j) {}
        }
      }
    }
  };
  private long invisibileTime = 0L;
  private ArrayList<MessageListener> messageListeners = new ArrayList();
  private OAuthHelper oAuthHelper;
  private SharedPreferences prefsPrefs;
  private TaxiMagicReceiver taxiMagicReceiver;
  private Twitter twitter;
  private Timer visibilityTimer = null;
  
  public TaxiMagicApplication()
  {
    FlurryAgent.setLogEnabled(false);
    instance = this;
    AppProperties localAppProperties = AppProperties.getInstance();
    localAppProperties.setAppProperty("nonce", "");
    localAppProperties.setAppProperty("latitude", new Double(0.0D));
    localAppProperties.setAppProperty("longitude", new Double(0.0D));
    localAppProperties.setAppProperty("accuracy", new Double(0.0D));
    localAppProperties.setAppProperty("AppNameKey", new Boolean(false));
  }
  
  public static String btrim(String paramString)
  {
    return paramString.replaceAll("\\b\\s{1,}\\b", "");
  }
  
  private String getDeviceID()
  {
    String str2 = ((TelephonyManager)getSystemService("phone")).getDeviceId();
    String str1;
    if (str2 != null)
    {
      str1 = str2;
      if (!str2.equalsIgnoreCase("")) {}
    }
    else
    {
      str1 = Settings.System.getString(getContentResolver(), "android_id");
    }
    return str1;
  }
  
  public static TaxiMagicApplication getInstance()
  {
    return instance;
  }
  
  public static String itrim(String paramString)
  {
    return paramString.replaceAll("\\b\\s{2,}\\b", " ");
  }
  
  public static String lrtrim(String paramString)
  {
    return ltrim(rtrim(paramString));
  }
  
  public static String ltrim(String paramString)
  {
    return paramString.replaceAll("^\\s+", "");
  }
  
  public static String rtrim(String paramString)
  {
    return paramString.replaceAll("\\s+$", "");
  }
  
  private void startVisibiltyTimer()
  {
    try
    {
      cancelVisibiltyTimer();
      TimerTask local2 = new TimerTask()
      {
        public final void run()
        {
          TaxiMagicApplication.this.handler.sendEmptyMessage(32);
        }
      };
      this.visibilityTimer = new Timer();
      this.visibilityTimer.schedule(local2, 1200000L);
      return;
    }
    finally {}
  }
  
  public static String trim(String paramString)
  {
    return btrim(paramString).trim();
  }
  
  public void addMessageListener(MessageListener paramMessageListener)
  {
    synchronized (this.messageListeners)
    {
      this.messageListeners.add(paramMessageListener);
      return;
    }
  }
  
  public String beginTwitterAuthorization()
  {
    try
    {
      if (this.currentRequestToken == null) {
        this.currentRequestToken = this.twitter.getOAuthRequestToken();
      }
      String str = this.currentRequestToken.getAuthorizationURL();
      return str;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }
  
  public void broadCastSendMessageIntent(int paramInt)
  {
    Intent localIntent = new Intent("com.ridecharge.android.taximagic.send_message");
    localIntent.putExtra("messageInt", paramInt);
    sendBroadcast(localIntent);
  }
  
  public void cancelVisibiltyTimer()
  {
    try
    {
      if (this.visibilityTimer != null)
      {
        this.visibilityTimer.cancel();
        this.visibilityTimer = null;
      }
      return;
    }
    finally {}
  }
  
  public void dumpInstalledApps()
  {
    PackageManager localPackageManager = getPackageManager();
    List localList = localPackageManager.getInstalledApplications(0);
    int i = 0;
    for (;;)
    {
      if (i >= localList.size()) {
        return;
      }
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localList.get(i);
      Log.d(TAG, "-------------------------------");
      Log.d(TAG, "'" + localApplicationInfo.loadLabel(localPackageManager) + "'");
      Log.d(TAG, "'" + localApplicationInfo.loadDescription(localPackageManager) + "'");
      Log.d(TAG, "'" + localApplicationInfo.name + "' - '" + localApplicationInfo.packageName + "' - '" + localApplicationInfo.className + "'");
      i += 1;
    }
  }
  
  public void exitApplication()
  {
    this.exiting = true;
    this.handler.sendEmptyMessage(32);
  }
  
  public String getMDN()
  {
    String str2 = ((TelephonyManager)getSystemService("phone")).getLine1Number();
    String str1 = str2;
    if (str2 == null) {
      str1 = "";
    }
    return str1;
  }
  
  public SharedPreferences getSharedPreferences()
  {
    if (this.prefsPrefs == null) {
      this.prefsPrefs = getSharedPreferences("private_prefs", 0);
    }
    return this.prefsPrefs;
  }
  
  public Twitter getTwitter()
  {
    return this.twitter;
  }
  
  public boolean isTwitterAuthorized()
  {
    return this.oAuthHelper.hasAccessToken();
  }
  
  public void onCreate()
  {
    super.onCreate();
    AppProperties localAppProperties = AppProperties.getInstance();
    localAppProperties.setFileDir(getFilesDir());
    localAppProperties.setDeviceID(getDeviceID());
    localAppProperties.setSharedPreferences(getSharedPreferences());
    localAppProperties.setAppProperty("BreadCrumbMarkerKey", getResources().getString(2131099923));
    localAppProperties.setAppProperty("mobileRideAlertPrimaryTextRebookKey", getResources().getString(2131099839));
    localAppProperties.setAppProperty("mobileRideAlertPrimaryTextCallProviderKey", getResources().getString(2131099840));
    localAppProperties.setAppProperty("bookedKey", getResources().getString(2131099867));
    localAppProperties.setAppProperty("rideSubtitleFormat", getResources().getString(2131099794));
    localAppProperties.setAppProperty("rideSubtitleFormat2", getResources().getString(2131099795));
    localAppProperties.setAppProperty("errorNoPaymentMethodsKey", getResources().getString(2131099769));
    localAppProperties.setAppProperty("errorKey", getResources().getString(2131099684));
    localAppProperties.setAppProperty("unknownKey", getResources().getString(2131099790));
    Object localObject = getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getPackageInfo(getPackageName(), 0).versionName;
      localAppProperties.setAppVersion((String)localObject);
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      try
      {
        localObject = getApplicationContext().getAssets().open("airshipconfig.properties");
        Properties localProperties = new Properties();
        localProperties.load((InputStream)localObject);
        localAppProperties.setUrbanAirshipProperties(localProperties);
        UAirship.takeOff(this);
        PushManager.enablePush();
        localObject = PushManager.shared().getPreferences();
        PushManager.shared().setIntentReceiver(TaxiMagicReceiver.class);
        ((PushPreferences)localObject).setSoundEnabled(true);
        ((PushPreferences)localObject).setVibrateEnabled(true);
        this.taxiMagicReceiver = new TaxiMagicReceiver();
        localObject = new IntentFilter("com.ridecharge.android.taximagic.send_message");
        registerReceiver(this.taxiMagicReceiver, (IntentFilter)localObject);
        this.oAuthHelper = new OAuthHelper(this, this.handler);
        this.twitter = new TwitterFactory().getInstance();
        this.oAuthHelper.configureOAuth(this.twitter);
        return;
        localNameNotFoundException = localNameNotFoundException;
        String str = "0.0";
      }
      catch (IOException localIOException)
      {
        for (;;)
        {
          localIOException.printStackTrace();
        }
      }
    }
  }
  
  public void onTerminate()
  {
    unregisterReceiver(this.taxiMagicReceiver);
    super.onTerminate();
  }
  
  public void onVisibilityChanged(boolean paramBoolean)
  {
    if (!this.exiting)
    {
      if (!paramBoolean) {
        this.invisibileTime = System.currentTimeMillis();
      }
    }
    else {
      return;
    }
    if (wasHiddenTooLong()) {
      reStart(this);
    }
    this.invisibileTime = 0L;
  }
  
  public void promptUserToRateDriver()
  {
    ServerCommand.sendCommand(new GetDriverRatingQuestionsCommand(this.handler, (String)AppProperties.getInstance().getAppProperty("rateRideNumberKey")));
  }
  
  public void reStart(Context paramContext)
  {
    reset();
    Intent localIntent = new Intent(paramContext, BusyScreen.class);
    localIntent.addFlags(268435456);
    localIntent.putExtra("messageInt", 3);
    paramContext.startActivity(localIntent);
  }
  
  public void removeMessageListener(MessageListener paramMessageListener)
  {
    synchronized (this.messageListeners)
    {
      this.messageListeners.remove(paramMessageListener);
      if (this.messageListeners.size() == 0) {}
      return;
    }
  }
  
  public void reset()
  {
    AppState.getInstance().clear();
    this.invisibileTime = 0L;
    this.handler.sendEmptyMessage(33);
  }
  
  public void resetTwitter()
  {
    this.currentRequestToken = null;
  }
  
  public void sendEmptyMessage(int paramInt)
  {
    this.handler.sendEmptyMessage(paramInt);
  }
  
  public boolean twitterAuthorize(String paramString)
  {
    try
    {
      paramString = this.twitter.getOAuthAccessToken(this.currentRequestToken, paramString);
      this.oAuthHelper.storeAccessToken(paramString);
      return true;
    }
    catch (Throwable paramString)
    {
      Message localMessage = this.handler.obtainMessage(42);
      Bundle localBundle = new Bundle();
      localBundle.putString("title", getString(2131099684));
      localBundle.putString("message", "Unable to authorize user " + paramString.getMessage());
      localMessage.setData(localBundle);
      this.handler.sendMessage(localMessage);
    }
    return false;
  }
  
  public void twitterAuthorized()
  {
    try
    {
      AccessToken localAccessToken = this.twitter.getOAuthAccessToken();
      this.oAuthHelper.storeAccessToken(localAccessToken);
      return;
    }
    catch (Throwable localThrowable)
    {
      Message localMessage = this.handler.obtainMessage(42);
      Bundle localBundle = new Bundle();
      localBundle.putString("title", getString(2131099684));
      localBundle.putString("message", "Unable to authorize user " + localThrowable.getMessage());
      localMessage.setData(localBundle);
      this.handler.sendMessage(localMessage);
    }
  }
  
  public void twitterUnAuthorize()
  {
    this.oAuthHelper.deleteAccessToken();
    CookieSyncManager.createInstance(this);
    CookieManager localCookieManager = CookieManager.getInstance();
    localCookieManager.removeAllCookie();
    localCookieManager.removeSessionCookie();
    localCookieManager.removeExpiredCookie();
    new WebView(this).getSettings().setSavePassword(false);
  }
  
  public boolean wasHiddenTooLong()
  {
    if (this.invisibileTime > 0L) {
      return System.currentTimeMillis() - this.invisibileTime > 1200000L;
    }
    return false;
  }
}
