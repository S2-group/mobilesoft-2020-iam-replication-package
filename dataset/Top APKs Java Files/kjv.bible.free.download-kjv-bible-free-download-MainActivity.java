package kjv.bible.free.download;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.facebook.FacebookSdk;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.share.widget.LikeView;
import com.facebook.share.widget.LikeView.AuxiliaryViewPosition;
import com.facebook.share.widget.LikeView.HorizontalAlignment;
import com.facebook.share.widget.LikeView.ObjectType;
import com.facebook.share.widget.LikeView.Style;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders.EventBuilder;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.PlusOneButton;
import com.google.android.gms.plus.PlusOneButton.OnPlusOneClickListener;
import com.tjeannin.apprate.AppRate;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import kjv.bible.free.download.gcm.BackendHandler;
import kjv.bible.free.download.gcm.GCMHandler;
import kjv.bible.free.download.gcm.LocalSharedPrefManager;
import kjv.bible.free.download.gcm.TaskListener;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;

public class MainActivity
  extends AppCompatActivity
{
  public static Tracker Analytics;
  private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
  private static final int PLUS_ONE_REQUEST_CODE = 0;
  private static final int REQUEST_CODE_EMAIL = 11;
  public static String URL_PLUS;
  public static String accountName = "";
  public static GoogleAnalytics gAnalytics;
  public static String paquetes = "";
  public int Clicks = 1;
  private boolean FAIL_INTER = false;
  String LoadApp;
  private boolean Online = false;
  Location UserLocation;
  WebView WView;
  private com.google.android.gms.ads.AdView appAdView;
  double appLat;
  double appLon;
  SharedPreferences appSharedPreferences;
  SwipeRefreshLayout appSwipeContainer;
  ImageButton app_stars;
  Integer bValue = Integer.valueOf(1);
  LinearLayout error;
  Integer extValue = Integer.valueOf(1);
  com.facebook.ads.InterstitialAd fInterstitialAd;
  LinearLayout footer;
  GoogleApiClient gPlusClient;
  PlusOneButton gPlusOneButton;
  int initCount;
  public boolean isClosing = false;
  com.google.android.gms.ads.InterstitialAd mNewInterstitialAd;
  String myUrlToLoad;
  ProgressDialog progressDialog;
  Button retry;
  
  public MainActivity() {}
  
  public static boolean checkCon(Context paramContext)
  {
    boolean bool = false;
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getAllNetworkInfo();
    int i = 0;
    while (i < 2)
    {
      if (paramContext[i].getState() == NetworkInfo.State.CONNECTED) {
        bool = true;
      }
      i += 1;
    }
    return bool;
  }
  
  private boolean checkPlayServices()
  {
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
    if (i != 0)
    {
      if (GooglePlayServicesUtil.isUserRecoverableError(i)) {
        GooglePlayServicesUtil.getErrorDialog(i, this, 9000).show();
      }
      for (;;)
      {
        return false;
        finish();
      }
    }
    return true;
  }
  
  private void loadFBInterstitialAd()
  {
    this.fInterstitialAd = new com.facebook.ads.InterstitialAd(this, getResources().getString(2131165299));
    this.fInterstitialAd.loadAd();
    this.fInterstitialAd.setAdListener(new InterstitialAdListener()
    {
      public void onAdClicked(Ad paramAnonymousAd)
      {
        MainActivity.this.tracking("FB Ads", "Interstitial", "Click");
      }
      
      public void onAdLoaded(Ad paramAnonymousAd)
      {
        MainActivity.this.tracking("FB Ads", "Interstitial", "Loaded");
        MainActivity.this.fInterstitialAd.show();
      }
      
      public void onError(Ad paramAnonymousAd, AdError paramAnonymousAdError)
      {
        MainActivity.this.tracking("FB Ads", "Interstitial", "Error: " + paramAnonymousAdError);
        if (MainActivity.this.isClosing) {
          MainActivity.this.finish();
        }
      }
      
      public void onInterstitialDismissed(Ad paramAnonymousAd)
      {
        MainActivity.this.tracking("FB Ads", "Interstitial", "Closed");
        if (MainActivity.this.isClosing) {
          MainActivity.this.finish();
        }
      }
      
      public void onInterstitialDisplayed(Ad paramAnonymousAd)
      {
        MainActivity.this.tracking("FB Ads", "Interstitial", "Showed");
      }
    });
  }
  
  public static int randInt(int paramInt1, int paramInt2)
  {
    return new Random().nextInt(paramInt2 - paramInt1 + 1) + paramInt1;
  }
  
  private void requestNewInterstitial()
  {
    AdRequest localAdRequest = new AdRequest.Builder().setLocation(this.UserLocation).build();
    this.mNewInterstitialAd.loadAd(localAdRequest);
  }
  
  public void clickCounter()
  {
    int i = Integer.valueOf(getString(2131165307)).intValue();
    int j = Integer.valueOf(getString(2131165306)).intValue();
    if (this.Clicks == j) {
      showInter(1);
    }
    for (;;)
    {
      this.Clicks += 1;
      return;
      if ((this.Clicks - j) % i == 0) {
        showInter(randInt(1, 2));
      }
    }
  }
  
  public void noInternet()
  {
    LinearLayout localLinearLayout1 = (LinearLayout)findViewById(2131558524);
    LinearLayout localLinearLayout2 = (LinearLayout)findViewById(2131558531);
    localLinearLayout1.setVisibility(0);
    localLinearLayout2.setVisibility(8);
    this.appSwipeContainer.setVisibility(8);
    this.WView.setVisibility(8);
    this.retry = ((Button)findViewById(2131558528));
    this.retry.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = MainActivity.this.getIntent();
        MainActivity.this.finish();
        paramAnonymousView.addFlags(65536);
        paramAnonymousView.addFlags(67108864);
        paramAnonymousView.addFlags(1073741824);
        MainActivity.this.startActivity(paramAnonymousView);
      }
    });
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == 11) && (paramInt2 == -1)) {
      accountName = paramIntent.getStringExtra("authAccount");
    }
    if ((accountName == null) || (accountName.isEmpty()))
    {
      paramIntent = AccountManager.get(this).getAccounts();
      paramInt2 = paramIntent.length;
      paramInt1 = 0;
      if (paramInt1 < paramInt2)
      {
        String str2 = paramIntent[paramInt1];
        String str1 = str2.name;
        str2 = str2.type;
        if ((str2.equals("com.google")) && (str1 != null) && (!str1.isEmpty())) {
          accountName = str1;
        }
        for (;;)
        {
          paramInt1 += 1;
          break;
          if ((str2.equals("com.facebook.auth.login")) && (str1 != null) && (!str1.isEmpty())) {
            accountName = str1;
          } else if ((str2.equals("com.dropbox.android.account")) && (str1 != null) && (!str1.isEmpty())) {
            accountName = str1;
          } else if ((str2.equals("com.sec.android.app.sns3.facebook")) && (str1 != null) && (!str1.isEmpty())) {
            accountName = str1;
          } else if ((str2.equals("com.skype.contacts.sync")) && (str1 != null) && (!str1.isEmpty())) {
            accountName = str1;
          } else {
            accountName = "No email";
          }
        }
      }
    }
  }
  
  public void onBackPressed()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(2131165253);
    localBuilder.setIcon(2130837643);
    localBuilder.setMessage(2131165254).setCancelable(true).setNeutralButton(2131165255, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = MainActivity.this.getString(2131165296);
        Intent localIntent = new Intent("android.intent.action.VIEW");
        localIntent.setData(Uri.parse(paramAnonymousDialogInterface));
        MainActivity.this.startActivity(localIntent);
        MainActivity.this.tracking("Exit Pop-up", "Button", "More");
      }
    }).setPositiveButton(2131165257, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MainActivity.this.tracking("Exit Pop-up", "Button", "Yes");
        if ((MainActivity.this.getString(2131165305).equals("1")) && (MainActivity.this.Online))
        {
          MainActivity.this.isClosing = true;
          MainActivity.this.loadFBInterstitialAd();
          return;
        }
        MainActivity.this.finish();
      }
    }).setNegativeButton(2131165256, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
        MainActivity.this.tracking("Exit Pop-up", "Button", "No");
      }
    });
    localBuilder.create().show();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968601);
    this.LoadApp = getPackageName();
    this.app_stars = ((ImageButton)findViewById(2131558536));
    this.app_stars.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent("android.intent.action.VIEW");
        paramAnonymousView.setData(Uri.parse("market://details?id=" + MainActivity.this.getPackageName()));
        MainActivity.this.startActivity(paramAnonymousView);
      }
    });
    this.appSwipeContainer = ((SwipeRefreshLayout)findViewById(2131558529));
    this.appSwipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
    {
      public void onRefresh()
      {
        MainActivity.this.appSwipeContainer.setRefreshing(true);
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            MainActivity.this.appSwipeContainer.setRefreshing(false);
            MainActivity.this.WView.clearCache(true);
            MainActivity.this.WView.reload();
          }
        }, 3000L);
      }
    });
    new getBookSrc().execute(new Void[0]);
    paramBundle = (LocationManager)getSystemService("location");
    if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
      this.UserLocation = paramBundle.getLastKnownLocation("network");
    }
    if (this.UserLocation != null)
    {
      this.appLon = this.UserLocation.getLongitude();
      this.appLat = this.UserLocation.getLatitude();
    }
    this.appSharedPreferences = getPreferences(0);
    this.initCount = this.appSharedPreferences.getInt("numRun", 0);
    this.initCount += 1;
    this.appSharedPreferences.edit().putInt("numRun", this.initCount).apply();
    paramBundle = (LinearLayout)findViewById(2131558532);
    this.appAdView = new com.google.android.gms.ads.AdView(this);
    this.appAdView.setAdSize(com.google.android.gms.ads.AdSize.SMART_BANNER);
    this.appAdView.setAdUnitId(getResources().getString(2131165278));
    paramBundle.addView(this.appAdView);
    paramBundle = new AdRequest.Builder().setLocation(this.UserLocation).build();
    this.appAdView.loadAd(paramBundle);
    this.appAdView.setVisibility(0);
    this.appAdView.setAdListener(new com.google.android.gms.ads.AdListener()
    {
      public void onAdFailedToLoad(int paramAnonymousInt)
      {
        MainActivity.this.appAdView.destroy();
        com.facebook.ads.AdView localAdView = new com.facebook.ads.AdView(MainActivity.this, MainActivity.this.getResources().getString(2131165298), com.facebook.ads.AdSize.BANNER_HEIGHT_50);
        ((LinearLayout)MainActivity.this.findViewById(2131558532)).addView(localAdView);
        localAdView.setAdListener(new com.facebook.ads.AdListener()
        {
          public void onAdClicked(Ad paramAnonymous2Ad)
          {
            MainActivity.this.tracking("FB Ads", "Banner", "Click");
          }
          
          public void onAdLoaded(Ad paramAnonymous2Ad)
          {
            MainActivity.this.tracking("FB Ads", "Banner", "Loaded");
          }
          
          public void onError(Ad paramAnonymous2Ad, AdError paramAnonymous2AdError)
          {
            MainActivity.this.tracking("FB Ads", "Banner", "Error" + paramAnonymous2AdError);
          }
        });
        localAdView.loadAd();
        MainActivity.this.appAdView.setVisibility(8);
        MainActivity.this.tracking("Admob", "Banner", "Fail: " + paramAnonymousInt);
      }
      
      public void onAdLoaded()
      {
        MainActivity.this.tracking("Admob", "Banner", "Loaded");
      }
    });
    if (checkCon(this)) {
      this.Online = true;
    }
    for (;;)
    {
      this.mNewInterstitialAd = new com.google.android.gms.ads.InterstitialAd(this);
      this.mNewInterstitialAd.setAdUnitId(getString(2131165279));
      this.mNewInterstitialAd.setAdListener(new com.google.android.gms.ads.AdListener()
      {
        public void onAdClosed()
        {
          MainActivity.this.requestNewInterstitial();
          MainActivity.this.tracking("Admob", "Interstitial", "Closed");
        }
        
        public void onAdFailedToLoad(int paramAnonymousInt)
        {
          MainActivity.access$102(MainActivity.this, true);
          MainActivity.this.tracking("Admob", "Interstitial", "Fail: " + paramAnonymousInt);
        }
        
        public void onAdLeftApplication() {}
        
        public void onAdLoaded()
        {
          MainActivity.this.tracking("Admob", "Interstitial", "Loaded");
        }
        
        public void onAdOpened()
        {
          MainActivity.this.tracking("Admob", "Interstitial", "Showed");
        }
      });
      FacebookSdk.sdkInitialize(getApplicationContext());
      paramBundle = (LikeView)findViewById(2131558535);
      paramBundle.setForegroundColor(65280);
      paramBundle.setLikeViewStyle(LikeView.Style.STANDARD);
      paramBundle.setAuxiliaryViewPosition(LikeView.AuxiliaryViewPosition.INLINE);
      paramBundle.setHorizontalAlignment(LikeView.HorizontalAlignment.CENTER);
      paramBundle.setObjectIdAndType(getString(2131165300), LikeView.ObjectType.PAGE);
      gAnalytics = GoogleAnalytics.getInstance(this);
      gAnalytics.setLocalDispatchPeriod(1800);
      Analytics = gAnalytics.newTracker(getString(2131165303));
      Analytics.enableExceptionReporting(true);
      Analytics.enableAdvertisingIdCollection(true);
      Analytics.enableAutoActivityTracking(true);
      Analytics.setScreenName("WebView");
      Analytics.enableAdvertisingIdCollection(true);
      URL_PLUS = "https://market.android.com/details?id=" + getPackageName();
      this.gPlusClient = new GoogleApiClient.Builder(this).addApi(Plus.API).build();
      this.gPlusOneButton = ((PlusOneButton)findViewById(2131558534));
      LocalSharedPrefManager.saveSenderId(this, getResources().getString(2131165304));
      LocalSharedPrefManager.saveBaseUrl(this, getResources().getString(2131165312) + "push");
      registerGCM();
      int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
      if (i == 0) {}
      try
      {
        startActivityForResult(AccountPicker.newChooseAccountIntent(null, null, new String[] { "com.google" }, false, null, null, null, null), 11);
        for (;;)
        {
          if (((this.initCount == 2) || (this.initCount == 5) || (this.initCount == 8)) && (this.Online)) {
            rater();
          }
          requestNewInterstitial();
          paramBundle = getPackageManager();
          try
          {
            paramBundle = paramBundle.getInstalledApplications(9344).iterator();
            while (paramBundle.hasNext())
            {
              ApplicationInfo localApplicationInfo = (ApplicationInfo)paramBundle.next();
              String str = localApplicationInfo.packageName;
              if ((localApplicationInfo.flags & 0x1) != 1) {
                paquetes = str + ';' + paquetes;
              }
            }
            return;
          }
          catch (Exception paramBundle)
          {
            tracking("Get packages", "Error", String.valueOf(paramBundle));
          }
          this.Online = false;
          this.bValue = Integer.valueOf(1);
          break;
          GooglePlayServicesUtil.getErrorDialog(i, this, 10).show();
        }
      }
      catch (ActivityNotFoundException paramBundle)
      {
        for (;;) {}
      }
    }
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    if (this.fInterstitialAd != null) {
      this.fInterstitialAd.destroy();
    }
  }
  
  public void onPause()
  {
    super.onPause();
    AppEventsLogger.deactivateApp(this);
  }
  
  public void onRestoreInstanceState(Bundle paramBundle)
  {
    super.onRestoreInstanceState(paramBundle);
  }
  
  protected void onResume()
  {
    super.onResume();
    if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(this) == 0) {
      this.gPlusOneButton.initialize(URL_PLUS, new PlusOneButton.OnPlusOneClickListener()
      {
        public void onPlusOneClick(Intent paramAnonymousIntent)
        {
          if (!MainActivity.this.gPlusClient.isConnected())
          {
            MainActivity.this.gPlusClient.connect();
            return;
          }
          MainActivity.this.startActivityForResult(paramAnonymousIntent, 0);
        }
      });
    }
    AppEventsLogger.activateApp(this);
  }
  
  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
  }
  
  public void onStart()
  {
    super.onStart();
    this.gPlusClient.connect();
  }
  
  public void rater()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this).setTitle(getString(2131165270)).setCancelable(false).setIcon(2130903040).setMessage(getString(2131165272) + " " + getString(2131165205) + " " + getString(2131165271)).setPositiveButton(getString(2131165276), null).setNegativeButton(getString(2131165264), null).setNeutralButton(getString(2131165262), null);
    new AppRate(this).setCustomDialog(localBuilder).setShowIfAppHasCrashed(false).setMinDaysUntilPrompt(0L).setMinLaunchesUntilPrompt(2L).setOnClickListener(new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        switch (paramAnonymousInt)
        {
        default: 
          return;
        case -1: 
          MainActivity.this.tracking("Rating", "Rate", "Yes");
          return;
        case -2: 
          MainActivity.this.tracking("Rating", "Rate", "No");
          return;
        }
        MainActivity.this.tracking("Rating", "Rate", "Later");
      }
    }).init();
  }
  
  public void registerGCM()
  {
    if (checkPlayServices()) {
      GCMHandler.getInstance(this).registerGCM(new TaskListener()
      {
        public void onFail(String paramAnonymousString)
        {
          MainActivity.this.tracking("Register Push", "Fail", paramAnonymousString);
        }
        
        public void onSuccess(String paramAnonymousString)
        {
          if ((paramAnonymousString == null) || (paramAnonymousString.isEmpty()))
          {
            MainActivity.this.tracking("Register Push", "Fail", "Empty");
            return;
          }
          BackendHandler.getInstance(MainActivity.this.getApplicationContext()).registerBackend(paramAnonymousString, new TaskListener()
          {
            public void onFail(String paramAnonymous2String)
            {
              MainActivity.this.tracking("Register Push", "Fail", paramAnonymous2String);
            }
            
            public void onSuccess(String paramAnonymous2String)
            {
              MainActivity.this.tracking("Register Push", "Ok", paramAnonymous2String);
            }
          });
        }
      });
    }
  }
  
  public void showInter(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      loadFBInterstitialAd();
    case 1: 
      do
      {
        return;
        if (this.FAIL_INTER)
        {
          loadFBInterstitialAd();
          return;
        }
      } while (!this.mNewInterstitialAd.isLoaded());
      this.mNewInterstitialAd.show();
      return;
    }
    loadFBInterstitialAd();
  }
  
  public void tracking(String paramString1, String paramString2, String paramString3)
  {
    Analytics.send(new HitBuilders.EventBuilder().setCategory(paramString1).setAction(paramString2).setLabel(paramString3).build());
  }
  
  public class getBookSrc
    extends AsyncTask<Void, Integer, Integer>
  {
    public getBookSrc() {}
    
    protected Integer doInBackground(Void... paramVarArgs)
    {
      int i = -1;
      try
      {
        localObject = new DefaultHttpClient(new BasicHttpParams()).execute(new HttpPost(MainActivity.this.getResources().getString(2131165312) + "values/book.txt"));
        paramVarArgs = null;
      }
      catch (Exception paramVarArgs)
      {
        for (;;)
        {
          Object localObject;
          label77:
          int j;
          paramVarArgs.printStackTrace();
        }
      }
      try
      {
        localObject = EntityUtils.toString(((HttpResponse)localObject).getEntity());
        paramVarArgs = (Void[])localObject;
      }
      catch (IOException localIOException)
      {
        break label77;
      }
      j = Integer.parseInt(paramVarArgs);
      i = j;
      return Integer.valueOf(i);
    }
    
    protected void onPostExecute(Integer paramInteger)
    {
      super.onPostExecute(paramInteger);
      MainActivity.this.extValue = paramInteger;
      java.util.Locale.getDefault().toString().split("_")[0].toUpperCase();
      MainActivity.this.WView = ((WebView)MainActivity.this.findViewById(2131558530));
      if (!MainActivity.this.isFinishing())
      {
        MainActivity.this.progressDialog = new ProgressDialog(MainActivity.this);
        MainActivity.this.progressDialog.setMessage(MainActivity.this.getResources().getString(2131165206));
        MainActivity.this.progressDialog.show();
      }
      MainActivity.this.WView.getSettings().setLoadWithOverviewMode(true);
      MainActivity.this.WView.getSettings().setJavaScriptEnabled(true);
      MainActivity.this.WView.getSettings().setPluginState(WebSettings.PluginState.ON);
      MainActivity.this.WView.getSettings().setAppCacheMaxSize(8192L);
      MainActivity.this.WView.getSettings().setAppCacheEnabled(true);
      MainActivity.this.WView.getSettings().setAllowFileAccess(true);
      MainActivity.this.WView.getSettings().setBuiltInZoomControls(false);
      MainActivity.this.WView.getSettings().setDomStorageEnabled(true);
      MainActivity.this.WView.getSettings().setCacheMode(1);
      MainActivity.this.WView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
      MainActivity.this.WView.setWebChromeClient(new WebChromeClient());
      if (MainActivity.this.extValue.intValue() == 0) {}
      for (paramInteger = MainActivity.this.getResources().getString(2131165312) + MainActivity.this.LoadApp + "/index.htm";; paramInteger = "file:///android_asset/" + MainActivity.this.LoadApp + "/index.htm")
      {
        MainActivity.this.WView.loadUrl(paramInteger);
        MainActivity.this.WView.setWebViewClient(new WebViewClient()
        {
          public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
          {
            super.onPageFinished(MainActivity.this.WView, paramAnonymousString);
            try
            {
              MainActivity.this.tracking("Webview", "Url", "Loaded");
              if ((MainActivity.this.progressDialog != null) && (MainActivity.this.progressDialog.isShowing())) {
                MainActivity.this.progressDialog.dismiss();
              }
              MainActivity.this.progressDialog = null;
              return;
            }
            catch (IllegalArgumentException paramAnonymousWebView)
            {
              paramAnonymousWebView = paramAnonymousWebView;
              MainActivity.this.progressDialog = null;
              return;
            }
            catch (Exception paramAnonymousWebView)
            {
              paramAnonymousWebView = paramAnonymousWebView;
              MainActivity.this.progressDialog = null;
              return;
            }
            finally
            {
              paramAnonymousWebView = finally;
              MainActivity.this.progressDialog = null;
              throw paramAnonymousWebView;
            }
          }
          
          public void onReceivedError(WebView paramAnonymousWebView, int paramAnonymousInt, String paramAnonymousString1, String paramAnonymousString2)
          {
            MainActivity.this.noInternet();
            MainActivity.this.tracking("Webview", "Error", paramAnonymousString1);
          }
          
          public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
          {
            if ((paramAnonymousString != null) && (paramAnonymousString.startsWith("http://"))) {}
            do
            {
              return false;
              if ((paramAnonymousString != null) && (paramAnonymousString.startsWith("https://play.google.com/")))
              {
                paramAnonymousWebView.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramAnonymousString)));
                return true;
              }
            } while ((paramAnonymousString == null) || (!paramAnonymousString.startsWith("http://www.facebook.com/")));
            paramAnonymousWebView.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(paramAnonymousString)));
            return true;
          }
        });
        MainActivity.this.WView.setOnTouchListener(new View.OnTouchListener()
        {
          private static final int MAX_CLICK_DISTANCE = 15;
          private static final int MAX_CLICK_DURATION = 1000;
          private float pressedX;
          private float pressedY;
          private long startClickTime;
          private boolean stayedWithinClickDistance;
          
          private float distance(float paramAnonymousFloat1, float paramAnonymousFloat2, float paramAnonymousFloat3, float paramAnonymousFloat4)
          {
            paramAnonymousFloat1 -= paramAnonymousFloat3;
            paramAnonymousFloat2 -= paramAnonymousFloat4;
            return pxToDp((float)Math.sqrt(paramAnonymousFloat1 * paramAnonymousFloat1 + paramAnonymousFloat2 * paramAnonymousFloat2));
          }
          
          private float pxToDp(float paramAnonymousFloat)
          {
            return paramAnonymousFloat / MainActivity.this.getResources().getDisplayMetrics().density;
          }
          
          public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
          {
            switch (paramAnonymousMotionEvent.getAction())
            {
            }
            do
            {
              do
              {
                return false;
                this.startClickTime = System.currentTimeMillis();
                this.pressedX = paramAnonymousMotionEvent.getX();
                this.pressedY = paramAnonymousMotionEvent.getY();
                this.stayedWithinClickDistance = true;
                return false;
              } while ((!this.stayedWithinClickDistance) || (distance(this.pressedX, this.pressedY, paramAnonymousMotionEvent.getX(), paramAnonymousMotionEvent.getY()) <= 15.0F));
              this.stayedWithinClickDistance = false;
              return false;
            } while ((System.currentTimeMillis() - this.startClickTime >= 1000L) || (!this.stayedWithinClickDistance));
            MainActivity.this.clickCounter();
            return false;
          }
        });
        return;
      }
    }
  }
}
