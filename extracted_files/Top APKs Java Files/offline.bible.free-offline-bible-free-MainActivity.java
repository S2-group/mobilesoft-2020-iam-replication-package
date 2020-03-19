package offline.bible.free;

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
import offline.bible.free.gcm.BackendHandler;
import offline.bible.free.gcm.GCMHandler;
import offline.bible.free.gcm.LocalSharedPrefManager;
import offline.bible.free.gcm.TaskListener;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;

public class MainActivity
  extends AppCompatActivity
{
  private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
  private static final int PLUS_ONE_REQUEST_CODE = 0;
  private static final int REQUEST_CODE_EMAIL = 11;
  public static String URL_PLUS;
  public static String accountName = "";
  public static GoogleAnalytics gAnalytics;
  public static String paquetes = "";
  public static Tracker seguimiento;
  Location Buscando;
  WebView Navegator;
  SwipeRefreshLayout Swipie;
  private com.google.android.gms.ads.AdView appAdView;
  double appLat;
  double appLon;
  SharedPreferences appSharedPreferences;
  ImageButton app_stars;
  Integer bValue = Integer.valueOf(1);
  String cargarUrl;
  ProgressDialog comova;
  public int contar = 1;
  private boolean enlineasiono = false;
  LinearLayout error;
  Integer extValue = Integer.valueOf(1);
  com.facebook.ads.InterstitialAd fInterstitialAd;
  public boolean fechandoporta = false;
  LinearLayout footer;
  GoogleApiClient gPlusClient;
  PlusOneButton gPlusOneButton;
  int initCount;
  String loading;
  com.google.android.gms.ads.InterstitialAd mNewInterstitialAd;
  private boolean no_inters = false;
  Button retry;
  
  public MainActivity() {}
  
  private void Llamandoainterfacebook()
  {
    this.fInterstitialAd = new com.facebook.ads.InterstitialAd(this, getResources().getString(2131165299));
    this.fInterstitialAd.loadAd();
    this.fInterstitialAd.setAdListener(new InterstitialAdListener()
    {
      public void onAdClicked(Ad paramAnonymousAd)
      {
        MainActivity.this.Revisandoysiguiendo("Facebook Ads", "Interstitial", "Click");
      }
      
      public void onAdLoaded(Ad paramAnonymousAd)
      {
        MainActivity.this.Revisandoysiguiendo("Facebook Ads", "Interstitial", "Cargado");
        MainActivity.this.fInterstitialAd.show();
      }
      
      public void onError(Ad paramAnonymousAd, AdError paramAnonymousAdError)
      {
        MainActivity.this.Revisandoysiguiendo("Facebook Ads", "Interstitial", "Error: " + paramAnonymousAdError);
        if (MainActivity.this.fechandoporta) {
          MainActivity.this.finish();
        }
      }
      
      public void onInterstitialDismissed(Ad paramAnonymousAd)
      {
        MainActivity.this.Revisandoysiguiendo("Facebook Ads", "Interstitial", "Cerrado");
        if (MainActivity.this.fechandoporta) {
          MainActivity.this.finish();
        }
      }
      
      public void onInterstitialDisplayed(Ad paramAnonymousAd)
      {
        MainActivity.this.Revisandoysiguiendo("Facebook Ads", "Interstitial", "Mostrado");
      }
    });
  }
  
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
  
  public static int randInt(int paramInt1, int paramInt2)
  {
    return new Random().nextInt(paramInt2 - paramInt1 + 1) + paramInt1;
  }
  
  private void requestNewInterstitial()
  {
    AdRequest localAdRequest = new AdRequest.Builder().setLocation(this.Buscando).build();
    this.mNewInterstitialAd.loadAd(localAdRequest);
  }
  
  public void MuestraelInter(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      Llamandoainterfacebook();
    case 1: 
      do
      {
        return;
        if (this.no_inters)
        {
          Llamandoainterfacebook();
          return;
        }
      } while (!this.mNewInterstitialAd.isLoaded());
      this.mNewInterstitialAd.show();
      return;
    }
    Llamandoainterfacebook();
  }
  
  public void Nopinternet()
  {
    LinearLayout localLinearLayout1 = (LinearLayout)findViewById(2131558524);
    LinearLayout localLinearLayout2 = (LinearLayout)findViewById(2131558531);
    localLinearLayout1.setVisibility(0);
    localLinearLayout2.setVisibility(8);
    this.Swipie.setVisibility(8);
    this.Navegator.setVisibility(8);
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
  
  public void Ojoquecuento()
  {
    int i = Integer.valueOf(getString(2131165307)).intValue();
    int j = Integer.valueOf(getString(2131165306)).intValue();
    if (this.contar == j) {
      MuestraelInter(1);
    }
    for (;;)
    {
      this.contar += 1;
      return;
      if ((this.contar - j) % i == 0) {
        MuestraelInter(randInt(1, 2));
      }
    }
  }
  
  public void Revisandoysiguiendo(String paramString1, String paramString2, String paramString3)
  {
    seguimiento.send(new HitBuilders.EventBuilder().setCategory(paramString1).setAction(paramString2).setLabel(paramString3).build());
  }
  
  public void Votenplease()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this).setTitle(getString(2131165271)).setCancelable(false).setIcon(2130903040).setMessage(getString(2131165273) + " " + getString(2131165253) + " " + getString(2131165272)).setPositiveButton(getString(2131165276), null).setNegativeButton(getString(2131165265), null).setNeutralButton(getString(2131165264), null);
    new AppRate(this).setCustomDialog(localBuilder).setShowIfAppHasCrashed(false).setMinDaysUntilPrompt(0L).setMinLaunchesUntilPrompt(2L).setOnClickListener(new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        switch (paramAnonymousInt)
        {
        default: 
          return;
        case -1: 
          MainActivity.this.Revisandoysiguiendo("Votar", "Rate", "Si");
          return;
        case -2: 
          MainActivity.this.Revisandoysiguiendo("Votar", "Rate", "No");
          return;
        }
        MainActivity.this.Revisandoysiguiendo("Votar", "Rate", "Luego");
      }
    }).init();
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
    localBuilder.setTitle(2131165255);
    localBuilder.setIcon(2130837643);
    localBuilder.setMessage(2131165256).setCancelable(true).setNeutralButton(2131165257, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = MainActivity.this.getString(2131165296);
        Intent localIntent = new Intent("android.intent.action.VIEW");
        localIntent.setData(Uri.parse(paramAnonymousDialogInterface));
        MainActivity.this.startActivity(localIntent);
        MainActivity.this.Revisandoysiguiendo("Exit Pop-up", "Button", "More");
      }
    }).setPositiveButton(2131165259, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MainActivity.this.Revisandoysiguiendo("Exit Pop-up", "Button", "Yes");
        if ((MainActivity.this.getString(2131165305).equals("1")) && (MainActivity.this.enlineasiono))
        {
          MainActivity.this.fechandoporta = true;
          MainActivity.this.Llamandoainterfacebook();
          return;
        }
        MainActivity.this.finish();
      }
    }).setNegativeButton(2131165258, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
        MainActivity.this.Revisandoysiguiendo("Exit Pop-up", "Button", "No");
      }
    });
    localBuilder.create().show();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968601);
    this.loading = getPackageName();
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
    this.Swipie = ((SwipeRefreshLayout)findViewById(2131558529));
    this.Swipie.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
    {
      public void onRefresh()
      {
        MainActivity.this.Swipie.setRefreshing(true);
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            MainActivity.this.Swipie.setRefreshing(false);
            MainActivity.this.Navegator.clearCache(true);
            MainActivity.this.Navegator.reload();
          }
        }, 3000L);
      }
    });
    new getBookSrc().execute(new Void[0]);
    this.appSharedPreferences = getPreferences(0);
    this.initCount = this.appSharedPreferences.getInt("numRun", 0);
    this.initCount += 1;
    this.appSharedPreferences.edit().putInt("numRun", this.initCount).apply();
    if (this.Buscando != null)
    {
      this.appLon = this.Buscando.getLongitude();
      this.appLat = this.Buscando.getLatitude();
    }
    paramBundle = (LocationManager)getSystemService("location");
    if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
      this.Buscando = paramBundle.getLastKnownLocation("network");
    }
    paramBundle = (LinearLayout)findViewById(2131558532);
    this.appAdView = new com.google.android.gms.ads.AdView(this);
    this.appAdView.setAdSize(com.google.android.gms.ads.AdSize.SMART_BANNER);
    this.appAdView.setAdUnitId(getResources().getString(2131165278));
    paramBundle.addView(this.appAdView);
    paramBundle = new AdRequest.Builder().setLocation(this.Buscando).build();
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
            MainActivity.this.Revisandoysiguiendo("Facebook Ads", "Banner", "Click");
          }
          
          public void onAdLoaded(Ad paramAnonymous2Ad)
          {
            MainActivity.this.Revisandoysiguiendo("Facebook Ads", "Banner", "Cargado");
          }
          
          public void onError(Ad paramAnonymous2Ad, AdError paramAnonymous2AdError)
          {
            MainActivity.this.Revisandoysiguiendo("Facebook Ads", "Banner", "Error" + paramAnonymous2AdError);
          }
        });
        localAdView.loadAd();
        MainActivity.this.appAdView.setVisibility(8);
        MainActivity.this.Revisandoysiguiendo("Google Admob", "Banner", "Fallo: " + paramAnonymousInt);
      }
      
      public void onAdLoaded()
      {
        MainActivity.this.Revisandoysiguiendo("Google Admob", "Banner", "Cargado");
      }
    });
    if (checkCon(this)) {
      this.enlineasiono = true;
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
          MainActivity.this.Revisandoysiguiendo("Google Admob", "Interstitial", "Cerrado");
        }
        
        public void onAdFailedToLoad(int paramAnonymousInt)
        {
          MainActivity.access$102(MainActivity.this, true);
          MainActivity.this.Revisandoysiguiendo("Google Admob", "Interstitial", "Fallo: " + paramAnonymousInt);
        }
        
        public void onAdLeftApplication() {}
        
        public void onAdLoaded()
        {
          MainActivity.this.Revisandoysiguiendo("Google Admob", "Interstitial", "Cargado");
        }
        
        public void onAdOpened()
        {
          MainActivity.this.Revisandoysiguiendo("Google Admob", "Interstitial", "Mostrado");
        }
      });
      gAnalytics = GoogleAnalytics.getInstance(this);
      gAnalytics.setLocalDispatchPeriod(1800);
      seguimiento = gAnalytics.newTracker(getString(2131165303));
      seguimiento.enableExceptionReporting(true);
      seguimiento.enableAdvertisingIdCollection(true);
      seguimiento.enableAutoActivityTracking(true);
      seguimiento.setScreenName("WebView");
      seguimiento.enableAdvertisingIdCollection(true);
      FacebookSdk.sdkInitialize(getApplicationContext());
      paramBundle = (LikeView)findViewById(2131558535);
      paramBundle.setForegroundColor(65280);
      paramBundle.setLikeViewStyle(LikeView.Style.STANDARD);
      paramBundle.setAuxiliaryViewPosition(LikeView.AuxiliaryViewPosition.INLINE);
      paramBundle.setHorizontalAlignment(LikeView.HorizontalAlignment.CENTER);
      paramBundle.setObjectIdAndType(getString(2131165300), LikeView.ObjectType.PAGE);
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
          if (((this.initCount == 2) || (this.initCount == 5) || (this.initCount == 8)) && (this.enlineasiono)) {
            Votenplease();
          }
          requestNewInterstitial();
          paramBundle = getPackageManager();
          try
          {
            paramBundle = paramBundle.getInstalledApplications(1152).iterator();
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
            Revisandoysiguiendo("Obtener paquetes", "Error", String.valueOf(paramBundle));
          }
          this.enlineasiono = false;
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
  
  public void registerGCM()
  {
    if (checkPlayServices()) {
      GCMHandler.getInstance(this).registerGCM(new TaskListener()
      {
        public void onFail(String paramAnonymousString)
        {
          MainActivity.this.Revisandoysiguiendo("Register Push", "Fail", paramAnonymousString);
        }
        
        public void onSuccess(String paramAnonymousString)
        {
          if ((paramAnonymousString == null) || (paramAnonymousString.isEmpty()))
          {
            MainActivity.this.Revisandoysiguiendo("Register Push", "Fail", "Empty");
            return;
          }
          BackendHandler.getInstance(MainActivity.this.getApplicationContext()).registerBackend(paramAnonymousString, new TaskListener()
          {
            public void onFail(String paramAnonymous2String)
            {
              MainActivity.this.Revisandoysiguiendo("Register Push", "Fail", paramAnonymous2String);
            }
            
            public void onSuccess(String paramAnonymous2String)
            {
              MainActivity.this.Revisandoysiguiendo("Register Push", "Ok", paramAnonymous2String);
            }
          });
        }
      });
    }
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
      MainActivity.this.Navegator = ((WebView)MainActivity.this.findViewById(2131558530));
      if (!MainActivity.this.isFinishing())
      {
        MainActivity.this.comova = new ProgressDialog(MainActivity.this);
        MainActivity.this.comova.setMessage(MainActivity.this.getResources().getString(2131165254));
        MainActivity.this.comova.show();
      }
      MainActivity.this.Navegator.getSettings().setLoadWithOverviewMode(true);
      MainActivity.this.Navegator.getSettings().setJavaScriptEnabled(true);
      MainActivity.this.Navegator.getSettings().setPluginState(WebSettings.PluginState.ON);
      MainActivity.this.Navegator.getSettings().setAppCacheMaxSize(8192L);
      MainActivity.this.Navegator.getSettings().setAppCacheEnabled(true);
      MainActivity.this.Navegator.getSettings().setAllowFileAccess(true);
      MainActivity.this.Navegator.getSettings().setBuiltInZoomControls(false);
      MainActivity.this.Navegator.getSettings().setDomStorageEnabled(true);
      MainActivity.this.Navegator.getSettings().setCacheMode(1);
      MainActivity.this.Navegator.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
      MainActivity.this.Navegator.setWebChromeClient(new WebChromeClient());
      if (MainActivity.this.extValue.intValue() == 0) {}
      for (paramInteger = MainActivity.this.getResources().getString(2131165312) + MainActivity.this.loading + "/index.htm";; paramInteger = "file:///android_asset/" + MainActivity.this.loading + "/index.htm")
      {
        MainActivity.this.Navegator.loadUrl(paramInteger);
        MainActivity.this.Navegator.setWebViewClient(new WebViewClient()
        {
          public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
          {
            super.onPageFinished(MainActivity.this.Navegator, paramAnonymousString);
            try
            {
              MainActivity.this.Revisandoysiguiendo("Webview", "Url", "Cargada");
              if ((MainActivity.this.comova != null) && (MainActivity.this.comova.isShowing())) {
                MainActivity.this.comova.dismiss();
              }
              MainActivity.this.comova = null;
              return;
            }
            catch (IllegalArgumentException paramAnonymousWebView)
            {
              paramAnonymousWebView = paramAnonymousWebView;
              MainActivity.this.comova = null;
              return;
            }
            catch (Exception paramAnonymousWebView)
            {
              paramAnonymousWebView = paramAnonymousWebView;
              MainActivity.this.comova = null;
              return;
            }
            finally
            {
              paramAnonymousWebView = finally;
              MainActivity.this.comova = null;
              throw paramAnonymousWebView;
            }
          }
          
          public void onReceivedError(WebView paramAnonymousWebView, int paramAnonymousInt, String paramAnonymousString1, String paramAnonymousString2)
          {
            MainActivity.this.Nopinternet();
            MainActivity.this.Revisandoysiguiendo("Webview", "Error", paramAnonymousString1);
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
        MainActivity.this.Navegator.setOnTouchListener(new View.OnTouchListener()
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
            MainActivity.this.Ojoquecuento();
            return false;
          }
        });
        return;
      }
    }
  }
}
