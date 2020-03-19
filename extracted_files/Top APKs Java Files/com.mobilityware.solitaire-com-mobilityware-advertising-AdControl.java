package com.mobilityware.advertising;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;
import com.brightroll.androidsdk.Ad;
import com.brightroll.androidsdk.AdDelegate;
import com.brightroll.androidsdk.RTB;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.ChartboostDefaultDelegate;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.flurry.android.FlurryAdListener;
import com.flurry.android.FlurryAdSize;
import com.flurry.android.FlurryAdType;
import com.flurry.android.FlurryAds;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.InterstitialAd;
import com.greystripe.sdk.GSAd;
import com.greystripe.sdk.GSAdErrorCode;
import com.greystripe.sdk.GSAdListener;
import com.greystripe.sdk.GSFullscreenAd;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubInterstitial.InterstitialAdListener;
import com.tapsense.android.publisher.TapSenseAds;
import com.tapsense.android.publisher.TapSenseAdsCallback;
import com.tapsense.android.publisher.TapSenseAdsErrorCode;
import com.tremorvideo.sdk.android.videoad.TremorVideo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

public class AdControl
  implements ParamListener, AdDelegate, GSAdListener, MoPubInterstitial.InterstitialAdListener, TapSenseAdsCallback
{
  private static final boolean ADDEBUG = false;
  private static final String ADMOB = "Admob";
  private static final int ADMOBNET = 0;
  private static final int ADMOBTESTNET = 0;
  private static final String APPLOVIN = "AppLovin";
  private static final int APPLOVINNET = 1;
  private static final int APPLOVINTESTNET = 8;
  private static final String BRIGHTROLL = "Brightroll";
  private static final int BRIGHTROLLNET = 3;
  private static final int BRIGHTROLLTESTNET = 3;
  private static final String CHARTBOOST = "Chartboost";
  private static final int CHARTBOOSTNET = 3;
  private static final int CHARTBOOSTTESTNET = 5;
  private static final int DISPLAYTASKDELAYTIME = 250;
  private static final String FLURRY = "Flurry";
  private static final int FLURRYNET = 4;
  private static final int FLURRYTESTNET = 7;
  private static final String GREYSTRIPE = "Greystripe";
  private static final int GREYSTRIPENET = 0;
  private static final int GREYSTRIPETESTNET = 4;
  private static String GreystripeID;
  public static final String INTERNAL = "Internal";
  private static final int INTERNALTESTNET = 9;
  private static final int MAXNONPREMIUMNETS = 7;
  private static final int MAXPREMIUMNETS = 5;
  private static final String MOPUB = "MoPub";
  private static final int MOPUBNET = 2;
  private static final int MOPUBTESTNET = 6;
  public static final String NOAD = "No ad";
  private static final int PREMIUMFAILTIME = 30000;
  private static final int RHYTHMNET = 1;
  private static final int RHYTHMTESTNET = 1;
  private static final String TAG = "adControl";
  private static final String TAPSENSE = "Tapsense";
  private static final int TAPSENSENET = 5;
  private static final int TAPSENSETESTNET = 10;
  private static final String TREMOR = "Tremor";
  private static final int TREMORNET = 2;
  private static final int TREMORTESTNET = 2;
  private static final int TremorActivityResult = 888;
  private static boolean admob = false;
  private static boolean brightroll = false;
  private static boolean chartboost = false;
  private static boolean flurry = false;
  private static boolean greystripe = false;
  private static int greystripeDelayRetires;
  private static int greystripePercent;
  private static boolean mopub = false;
  private static boolean rhythm;
  private static boolean tremor;
  private Runnable AdTask = new Runnable()
  {
    public void run()
    {
      try
      {
        AdControl.this.handler.removeCallbacks(AdControl.this.AdTask);
        if ((AdControl.this.paused) && (!AdControl.this.noStartStop)) {
          return;
        }
        AdControl.this.requestPremium();
        return;
      }
      catch (Throwable localThrowable)
      {
        for (;;) {}
      }
    }
  };
  private Runnable DisplayTask = new Runnable()
  {
    public void run()
    {
      try
      {
        AdControl.this.handler.removeCallbacks(AdControl.this.DisplayTask);
        AdControl.this.adWasDisplayed = AdControl.this.privateDisplayAd();
        return;
      }
      catch (Throwable localThrowable)
      {
        for (;;) {}
      }
    }
  };
  private Runnable GSFetchTask = new Runnable()
  {
    public void run()
    {
      try
      {
        AdControl.this.handler.removeCallbacks(AdControl.this.GSFetchTask);
        if ((AdControl.this.paused) && (!AdControl.this.noStartStop)) {
          try
          {
            AdControl.greystripeDelayRetires += 1;
            if (AdControl.greystripeDelayRetires >= 5)
            {
              AdControl.greystripeDelayRetires = 99;
              return;
            }
            AdControl.this.handler.postDelayed(AdControl.this.GSFetchTask, 20000L);
            return;
          }
          catch (Throwable localThrowable1)
          {
            return;
          }
        }
        if ((AdControl.this.gsAd != null) && (AdControl.greystripe)) {
          try
          {
            AdControl.this.gsAd.fetch();
            return;
          }
          catch (Throwable localThrowable2) {}
        }
      }
      catch (Throwable localThrowable3)
      {
        for (;;) {}
      }
    }
  };
  private int IA;
  private Runnable InitTask = new Runnable()
  {
    public void run()
    {
      AdControl.this.initPercent();
      AdControl.this.initAdNets();
    }
  };
  private Runnable MopubFetchTask = new Runnable()
  {
    public void run()
    {
      try
      {
        AdControl.this.handler.removeCallbacks(AdControl.this.MopubFetchTask);
        if ((AdControl.this.paused) && (!AdControl.this.noStartStop)) {
          return;
        }
        AdControl.this.getMopub();
        return;
      }
      catch (Throwable localThrowable)
      {
        for (;;) {}
      }
    }
  };
  private Runnable ParamTask = new Runnable()
  {
    public void run()
    {
      try
      {
        AdControl.this.handler.removeCallbacks(AdControl.this.ParamTask);
        if (!AdControl.this.gotParams) {
          AdControl.this.paramsChanged();
        }
        return;
      }
      catch (Throwable localThrowable)
      {
        for (;;) {}
      }
    }
  };
  public MWActivity activity;
  private AdParams adParams;
  private boolean adWasDisplayed;
  private AdListener admobDelegate = new AdListener()
  {
    public void onAdClosed()
    {
      if (!AdControl.this.paused) {
        AdControl.this.handler.postDelayed(AdControl.this.AdTask, 60000L);
      }
      for (;;)
      {
        AdControl.this.restoreVolume();
        AdControl.this.lastAdTime = SystemClock.uptimeMillis();
        return;
        AdControl.this.dismissed = true;
      }
    }
    
    public void onAdFailedToLoad(int paramAnonymousInt)
    {
      AdControl.this.admobRequested = false;
      AdControl localAdControl = AdControl.this;
      localAdControl.premiumErrors += 1;
      localAdControl = AdControl.this;
      localAdControl.admobErrors += 1;
      if (AdControl.this.testListener != null) {
        AdControl.this.testListener.requestFailed(0);
      }
      if (AdControl.this.premiumErrors > 15) {}
      do
      {
        return;
        if (AdControl.this.admobErrors >= 3)
        {
          AdControl.admob = false;
          AdControl.this.initPercent();
        }
      } while (AdControl.this.paused);
      AdControl.this.handler.postDelayed(AdControl.this.AdTask, 30000L);
    }
    
    public void onAdLoaded()
    {
      AdControl.this.admobReady = true;
      AdControl.this.admobRequested = false;
      if (AdControl.this.testListener != null) {
        AdControl.this.testListener.requestSuccessfull(0);
      }
    }
  };
  private int admobErrors;
  private int admobInARow;
  private InterstitialAd admobInterstitial;
  private int admobPercent;
  private boolean admobReady;
  private AdRequest admobRequest;
  private boolean admobRequested;
  private AppLovinDelegate alDelegate;
  private boolean appLovin = false;
  private AppLovinAd appLovinAd;
  private AppLovinInterstitialAdDialog appLovinAdDialog;
  private int appLovinDeliver;
  private int appLovinPerDay;
  private int appLovinPercent;
  private boolean appLovinReady;
  private AppLovinSdk appLovinSdk;
  private int appLovinToday;
  private AudioManager audio;
  private Object audioListener;
  public String backupInternalURL;
  private Ad brightrollAd;
  private int brightrollErrors;
  private int brightrollInARow;
  private int brightrollMaxAPILevel;
  private int brightrollPerDay;
  private int brightrollPercent;
  private boolean brightrollReady;
  private int brightrollToday;
  private Chartboost cb;
  private ChartboostDefaultDelegate chartBoostDelegate = new ChartboostDefaultDelegate()
  {
    private Runnable ChartboostFetchTask = new Runnable()
    {
      public void run()
      {
        try
        {
          AdControl.this.handler.removeCallbacks(AdControl.8.this.ChartboostFetchTask);
          if ((AdControl.this.paused) && (!AdControl.this.noStartStop)) {}
          for (;;)
          {
            return;
            if ((AdControl.this.cb == null) || (!AdControl.chartboost)) {
              continue;
            }
            try
            {
              if (AdControl.this.chartboostRequested) {
                continue;
              }
              AdControl.this.chartboostRequested = true;
              AdControl.this.cb.cacheInterstitial();
              return;
            }
            catch (Throwable localThrowable1)
            {
              AdControl.chartboost = false;
              return;
            }
          }
        }
        catch (Throwable localThrowable2)
        {
          for (;;) {}
        }
      }
    };
    
    public void didCacheInterstitial(String paramAnonymousString)
    {
      AdControl.this.chartboostRequested = false;
      if (AdControl.this.testListener != null) {
        AdControl.this.testListener.requestSuccessfull(5);
      }
    }
    
    public void didClickInterstitial(String paramAnonymousString)
    {
      try
      {
        AdControl.this.activity.setNoStartStop();
        return;
      }
      catch (Throwable paramAnonymousString) {}
    }
    
    public void didCloseInterstitial(String paramAnonymousString)
    {
      try
      {
        AdControl.this.chartboostShowing = false;
        if ((AdControl.this.testListener == null) && (!AdControl.this.chartboostRequested))
        {
          AdControl.this.chartboostRequested = true;
          AdControl.this.cb.cacheInterstitial();
        }
        AdControl.this.lastAdTime = SystemClock.uptimeMillis();
        return;
      }
      catch (Throwable paramAnonymousString)
      {
        AdControl.chartboost = false;
      }
    }
    
    public void didDismissInterstitial(String paramAnonymousString)
    {
      try
      {
        AdControl.this.chartboostShowing = false;
        if ((AdControl.this.testListener == null) && (!AdControl.this.chartboostRequested))
        {
          AdControl.this.chartboostRequested = true;
          AdControl.this.cb.cacheInterstitial();
        }
        AdControl.this.lastAdTime = SystemClock.uptimeMillis();
        return;
      }
      catch (Throwable paramAnonymousString)
      {
        AdControl.chartboost = false;
      }
    }
    
    public void didFailToLoadInterstitial(String paramAnonymousString, CBError.CBImpressionError paramAnonymousCBImpressionError)
    {
      AdControl.this.chartboostRequested = false;
      paramAnonymousString = AdControl.this;
      paramAnonymousString.chartboostErrors += 1;
      if (AdControl.this.chartboostErrors < 2) {}
      try
      {
        if (AdControl.this.testListener == null) {
          AdControl.this.handler.postDelayed(this.ChartboostFetchTask, AdControl.this.chartboostErrors * 3000);
        }
        if (AdControl.this.testListener != null) {
          AdControl.this.testListener.requestFailed(5);
        }
        return;
      }
      catch (Throwable paramAnonymousString)
      {
        for (;;) {}
      }
    }
    
    public void didShowInterstitial(String paramAnonymousString) {}
    
    public boolean shouldDisplayInterstitial(String paramAnonymousString)
    {
      boolean bool = false;
      if (AdControl.this.chartboostShouldShow)
      {
        AdControl.this.chartboostShouldShow = false;
        bool = true;
      }
      return bool;
    }
    
    public boolean shouldRequestInterstitial(String paramAnonymousString)
    {
      return true;
    }
  };
  private int chartboostErrors;
  private int chartboostPercent;
  private boolean chartboostRequested;
  private boolean chartboostShouldShow;
  private boolean chartboostShowing;
  public boolean crashed;
  public String currentInternalURL;
  private boolean dismissed;
  private int eventCount = 0;
  private int eventInterval = 2;
  private boolean flurryDisplayed;
  private int flurryErrors;
  private int flurryPercent;
  private boolean flurryRequested;
  private boolean gotParams;
  private GSFullscreenAd gsAd;
  private int gsErrors;
  private Handler handler;
  private int internalAdCount;
  private int internalAdNbr;
  public boolean internalAdShowing;
  private ArrayList<String> lastAdNet;
  public String lastAdNetOld;
  public long lastAdTime;
  private boolean localMusic;
  private long maxTime = 300000L;
  private long minTime;
  private int mopubErrors;
  private MoPubInterstitial mopubInterstitial;
  private int mopubPercent;
  private boolean mopubReady;
  private boolean musicPaused;
  private boolean needGSFetch;
  private boolean needTapsenseAd;
  public boolean noStartStop;
  private int[] nonPremPercent;
  private int nonPremTotPercent;
  private boolean paused;
  private int[] percent;
  private int premiumErrors;
  private int prevVolume;
  private Random rand;
  private int rhythmPercent;
  private AdControl self = this;
  private boolean tapsense;
  private boolean tapsenseInit;
  private int tapsensePercent;
  private AdTestListener testListener;
  private int totPercent;
  private int tremorInARow;
  private int tremorPercent;
  private boolean tremorPrev;
  private boolean tremorStarted;
  private boolean tryTremor;
  private boolean tv;
  private boolean volumeSilenced;
  
  static
  {
    GreystripeID = "";
    tremor = false;
    rhythm = false;
  }
  
  public AdControl(MWActivity paramMWActivity, AdParams paramAdParams, AdTestListener paramAdTestListener)
  {
    this.activity = paramMWActivity;
    this.testListener = paramAdTestListener;
    try
    {
      this.tv = this.activity.isTV();
      this.handler = new Handler();
      this.lastAdNet = new ArrayList();
      this.lastAdNetOld = "No ad";
      loadLastAdNets();
      this.adParams = paramAdParams;
      this.minTime = 15000L;
      this.gotParams = false;
      this.crashed = false;
      this.adParams.addListener(this.self);
      this.handler.postDelayed(this.ParamTask, 3000L);
      return;
    }
    catch (Throwable paramMWActivity)
    {
      for (;;)
      {
        this.tv = false;
      }
    }
  }
  
  private void addLastAdNet(String paramString)
  {
    this.lastAdNetOld = paramString;
    try
    {
      if (this.lastAdNet.size() > 3) {
        this.lastAdNet.remove(this.lastAdNet.size() - 1);
      }
      SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("MMddyy HHmm");
      localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
      Date localDate = new Date();
      this.lastAdNet.add(0, paramString + " " + localSimpleDateFormat.format(localDate));
      return;
    }
    catch (Throwable paramString) {}
  }
  
  private void brightrollShown()
  {
    try
    {
      this.brightrollToday += 1;
      int i = Calendar.getInstance().get(6);
      SharedPreferences.Editor localEditor = ((Activity)this.activity).getSharedPreferences("MWAds", 0).edit();
      localEditor.putInt("BRDay", i);
      localEditor.putInt("BRToday", this.brightrollToday);
      localEditor.commit();
      if (this.brightrollToday >= this.brightrollPerDay)
      {
        brightroll = false;
        initPercent();
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
  }
  
  private void debugSetup1() {}
  
  private boolean displayAdmob()
  {
    if ((this.admobReady) && (this.admobInterstitial != null))
    {
      this.admobReady = false;
      this.dismissed = true;
      this.activity.setNoStartStop();
      try
      {
        if (this.admobInterstitial.isLoaded())
        {
          this.admobInterstitial.show();
          addLastAdNet("Admob");
          silence();
          return true;
        }
        return false;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        admob = false;
        this.admobInterstitial = null;
        return false;
      }
    }
    return false;
  }
  
  private boolean displayApplovin()
  {
    if (!this.appLovin) {
      return false;
    }
    if (!this.appLovinReady) {
      return false;
    }
    if (this.appLovinAd == null) {
      return false;
    }
    this.appLovinReady = false;
    try
    {
      this.appLovinAdDialog = AppLovinInterstitialAd.create(this.appLovinSdk, (Activity)this.activity);
      this.appLovinAdDialog.setAdClickListener(this.alDelegate);
      this.appLovinAdDialog.setAdDisplayListener(this.alDelegate);
      this.appLovinAdDialog.setAdVideoPlaybackListener(this.alDelegate);
      this.appLovinAdDialog.showAndRender(this.appLovinAd);
      addLastAdNet("AppLovin");
      this.appLovinToday += 1;
      this.appLovinDeliver -= 1;
      int i = Calendar.getInstance().get(6);
      SharedPreferences.Editor localEditor = ((Activity)this.activity).getSharedPreferences("MWAds", 0).edit();
      localEditor.putInt("ALDay", i);
      localEditor.putInt("ALToday", this.appLovinToday);
      localEditor.commit();
      return true;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      this.appLovin = false;
      this.appLovinAd = null;
    }
    return false;
  }
  
  private boolean displayBrightroll()
  {
    if ((this.brightrollReady) && (this.brightrollAd != null))
    {
      brightrollShown();
      this.brightrollReady = false;
      this.dismissed = true;
      this.activity.setNoStartStop();
      addLastAdNet("Brightroll " + this.brightrollAd.getAdId());
      try
      {
        this.brightrollAd.show();
        silence();
        this.brightrollAd = null;
        return true;
      }
      catch (Throwable localThrowable)
      {
        this.brightrollAd = null;
        return false;
      }
    }
    return false;
  }
  
  private boolean displayChartboost()
  {
    if (!chartboost) {}
    for (;;)
    {
      return false;
      try
      {
        if ((chartboost) && (this.cb.hasCachedInterstitial()))
        {
          this.chartboostShouldShow = true;
          this.chartboostShowing = true;
          this.cb.showInterstitial();
          addLastAdNet("Chartboost");
          return true;
        }
      }
      catch (Throwable localThrowable)
      {
        chartboost = false;
      }
    }
    return false;
  }
  
  private boolean displayFlurry()
  {
    if (!flurry) {
      return false;
    }
    try
    {
      if ((flurry) && (FlurryAds.isAdAvailable((Context)this.activity, AdInfo.FlurryAdSpace, FlurryAdSize.FULLSCREEN, 0L)))
      {
        addLastAdNet("Flurry");
        this.activity.setNoStartStop();
        this.flurryDisplayed = true;
        FlurryAds.displayAd((Context)this.activity, AdInfo.FlurryAdSpace, this.activity.getViewGroup());
        return true;
      }
    }
    catch (Throwable localThrowable)
    {
      flurry = false;
    }
    return false;
  }
  
  private boolean displayGreystripe()
  {
    try
    {
      if ((greystripe) && (this.gsAd != null) && (this.gsAd.isAdReady()))
      {
        this.gsAd.display();
        addLastAdNet("Greystripe " + this.gsAd.getId());
        silence();
        this.dismissed = true;
        this.activity.setNoStartStop();
        return true;
      }
    }
    catch (Throwable localThrowable)
    {
      greystripe = false;
    }
    return false;
  }
  
  private boolean displayInternalAd()
  {
    if (AdInfo.PrimaryInternalAdURL == null) {
      return false;
    }
    if (this.internalAdCount >= AdInfo.PrimaryInternalAdURL.length) {
      return false;
    }
    if (this.internalAdNbr >= AdInfo.PrimaryInternalAdURL.length) {
      this.internalAdNbr = 0;
    }
    try
    {
      if ((AdInfo.PrimaryInternalAdURL[this.internalAdNbr].startsWith("market://details?id=")) && (isInstalled(AdInfo.PrimaryInternalAdURL[this.internalAdNbr].substring(AdInfo.PrimaryInternalAdURL[this.internalAdNbr].indexOf("=") + 1))))
      {
        this.internalAdNbr += 1;
        if (this.internalAdNbr >= AdInfo.PrimaryInternalAdURL.length) {
          this.internalAdNbr = 0;
        }
        this.lastAdTime = 0L;
        return false;
      }
      this.internalAdShowing = true;
      ((Activity)this.activity).setContentView(this.activity.getInternalAdLayout());
      ImageButton localImageButton = (ImageButton)((Activity)this.activity).findViewById(this.activity.getInternalAdButton());
      this.currentInternalURL = AdInfo.PrimaryInternalAdURL[this.internalAdNbr];
      this.backupInternalURL = AdInfo.BackupInternalAdURL[this.internalAdNbr];
      localImageButton.setImageResource(AdInfo.InternalAdImageID[this.internalAdNbr]);
      switchOrientation(((Activity)this.activity).getResources().getConfiguration().orientation);
      this.internalAdNbr += 1;
      if (this.internalAdNbr >= AdInfo.PrimaryInternalAdURL.length) {
        this.internalAdNbr = 0;
      }
      this.lastAdTime = SystemClock.uptimeMillis();
      this.internalAdCount += 1;
      addLastAdNet("Internal");
      return true;
    }
    catch (Throwable localThrowable)
    {
      this.internalAdNbr += 1;
      if (this.internalAdNbr >= AdInfo.PrimaryInternalAdURL.length) {
        this.internalAdNbr = 0;
      }
      this.activity.internalAdSkipAction(null);
    }
    return false;
  }
  
  private boolean displayMoPub()
  {
    if (!mopub) {}
    for (;;)
    {
      return false;
      try
      {
        if ((this.mopubReady) && (this.mopubInterstitial != null) && (this.mopubInterstitial.isReady()))
        {
          this.mopubReady = false;
          silence();
          this.dismissed = true;
          this.activity.setNoStartStop();
          this.mopubInterstitial.show();
          addLastAdNet("MoPub");
          return true;
        }
      }
      catch (Throwable localThrowable)
      {
        mopub = false;
        this.mopubInterstitial = null;
      }
    }
    return false;
  }
  
  private boolean displayNonPremium()
  {
    if (this.nonPremTotPercent < 1) {}
    for (;;)
    {
      return false;
      int j = this.rand.nextInt(this.nonPremTotPercent);
      int i = 0;
      while (i < 7)
      {
        if (j < this.nonPremPercent[i])
        {
          switch (i)
          {
          default: 
            Log.e("adControl", "displayNonPremium invalid ad net");
            return false;
          case 0: 
            return displayGreystripe();
          case 1: 
            return displayApplovin();
          case 2: 
            return displayMoPub();
          case 3: 
            return displayChartboost();
          case 4: 
            return displayFlurry();
          }
          return displayTapsense();
        }
        i += 1;
      }
    }
  }
  
  private boolean displayTapsense()
  {
    if (!this.tapsense) {}
    for (;;)
    {
      return false;
      try
      {
        if (TapSenseAds.getInstance().isReady())
        {
          silence();
          this.dismissed = true;
          this.activity.setNoStartStop();
          addLastAdNet("Tapsense");
          TapSenseAds.getInstance().showAd();
          return true;
        }
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        this.tapsense = false;
      }
    }
    return false;
  }
  
  private boolean displayTremor()
  {
    if ((tremor) && ((!this.tremorPrev) || (isWifiAvail())))
    {
      try
      {
        if (TremorVideo.showAd((Activity)this.activity, AdInfo.TremorIDs[0], 888))
        {
          addLastAdNet("Tremor");
          silence();
          this.tryTremor = false;
          this.tremorPrev = true;
          this.dismissed = true;
          this.activity.setNoStartStop();
          return true;
        }
        if ((AdInfo.TremorIDs.length > 1) && (TremorVideo.showAd((Activity)this.activity, AdInfo.TremorIDs[1], 888)))
        {
          addLastAdNet("Tremor");
          silence();
          this.tryTremor = false;
          this.tremorPrev = true;
          this.dismissed = true;
          this.activity.setNoStartStop();
          return true;
        }
      }
      catch (Throwable localThrowable)
      {
        Log.e("Tremor", "Exception:" + localThrowable.getMessage());
        tremor = false;
        return false;
      }
      if (this.tryTremor)
      {
        this.tryTremor = false;
        this.handler.postDelayed(this.AdTask, 30000L);
      }
    }
    for (;;)
    {
      return false;
      this.tremorPrev = false;
    }
  }
  
  private boolean getAdmob()
  {
    if ((this.paused) || (this.admobReady) || (this.admobRequested) || (this.admobInterstitial == null)) {
      return false;
    }
    this.admobRequested = true;
    try
    {
      this.admobRequest = new AdRequest.Builder().build();
      this.admobInterstitial.loadAd(this.admobRequest);
      return true;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      admob = false;
      this.admobRequested = false;
      this.admobInterstitial = null;
    }
    return false;
  }
  
  private boolean getBrightroll()
  {
    if (this.brightrollAd == null) {}
    try
    {
      this.brightrollAd = new Ad();
      this.brightrollAd.setDelegate(this);
      this.brightrollAd.setSitePlacementId(AdInfo.BrightrollID);
      this.brightrollAd.setHashesUserId(true);
      RTB localRTB = this.brightrollAd.getRTB();
      localRTB.setAppUrl("https://play.google.com/store/apps/details?id=com.mobilityware.solitaire");
      localRTB.setAppName("Solitaire");
      localRTB.setHasPrivacyPolicy(true);
      localRTB.setPaid(false);
      localRTB.setAppCategories(new String[] { "Card Games", "Video & Computer Games" });
      this.brightrollAd.fetch();
      return true;
    }
    catch (Throwable localThrowable) {}
    return false;
  }
  
  private void initAdNets()
  {
    this.premiumErrors = 0;
    this.admobErrors = 0;
    this.admobInARow = 0;
    if ((admob) && (this.admobInterstitial == null)) {}
    try
    {
      this.admobReady = false;
      this.admobRequested = false;
      this.admobInterstitial = new InterstitialAd((Activity)this.activity);
      this.admobInterstitial.setAdUnitId(AdInfo.AdMobID);
      this.admobInterstitial.setAdListener(this.admobDelegate);
      this.gsErrors = 0;
      greystripeDelayRetires = 0;
    }
    catch (Throwable localThrowable8)
    {
      try
      {
        if ((greystripe) && (this.gsAd == null))
        {
          this.gsAd = new GSFullscreenAd((Activity)this.activity, GreystripeID);
          this.gsAd.addListener(this);
          if (this.testListener == null) {
            this.gsAd.fetch();
          }
        }
        this.tremorInARow = 0;
        if ((tremor) && (!this.tremorStarted)) {
          this.tremorStarted = true;
        }
      }
      catch (Throwable localThrowable8)
      {
        try
        {
          TremorVideo.initialize((Activity)this.activity, AdInfo.TremorIDs);
          TremorVideo.start();
          this.chartboostErrors = 0;
          if (!chartboost) {}
        }
        catch (Throwable localThrowable8)
        {
          try
          {
            this.cb = Chartboost.sharedChartboost();
            if (this.activity.isAmazon())
            {
              this.cb.onCreate((Activity)this.activity, AdInfo.ChartboostAppIDA, AdInfo.ChartboostAppSignatureA, this.chartBoostDelegate);
              this.cb.startSession();
              Chartboost.sharedChartboost().onStart((Activity)this.activity);
              if (this.testListener == null)
              {
                this.chartboostRequested = true;
                this.cb.cacheInterstitial();
              }
              this.flurryErrors = 0;
              if (!flurry) {}
            }
          }
          catch (Throwable localThrowable8)
          {
            try
            {
              FlurryAds.initializeAds((Context)this.activity);
              FlurryAds.setAdListener(new MyFlurryAdListener(null));
              this.flurryRequested = true;
              if (this.testListener == null) {
                FlurryAds.fetchAd((Context)this.activity, AdInfo.FlurryAdSpace, this.activity.getViewGroup(), FlurryAdSize.FULLSCREEN);
              }
              this.mopubErrors = 0;
              if ((!mopub) || (this.mopubInterstitial != null)) {}
            }
            catch (Throwable localThrowable8)
            {
              try
              {
                this.mopubReady = false;
                this.mopubInterstitial = new MoPubInterstitial((Activity)this.activity, AdInfo.MoPubID);
                this.mopubInterstitial.setFacebookSupported(false);
                this.mopubInterstitial.setInterstitialAdListener(this);
                if (this.testListener == null) {
                  this.mopubInterstitial.load();
                }
                if (!this.appLovin) {}
              }
              catch (Throwable localThrowable8)
              {
                try
                {
                  AppLovinSdk.initializeSdk((Activity)this.activity);
                  this.alDelegate = new AppLovinDelegate(null);
                  this.appLovinSdk = AppLovinSdk.getInstance((Activity)this.activity);
                  if (this.testListener == null) {
                    requestAppLovin();
                  }
                  if (!this.tapsense) {}
                }
                catch (Throwable localThrowable8)
                {
                  try
                  {
                    if (!this.tapsenseInit)
                    {
                      this.tapsenseInit = true;
                      TapSenseAds.start((Activity)this.activity, "mobilityware", AdInfo.TapsenseAppID, "42b8dfb005");
                      TapSenseAds.getInstance().callback = this;
                      this.brightrollInARow = 0;
                      this.brightrollErrors = 0;
                      if (!brightroll) {}
                    }
                  }
                  catch (Throwable localThrowable8)
                  {
                    try
                    {
                      for (;;)
                      {
                        RTB.init((Activity)this.activity);
                        if (((admob) || (rhythm) || (brightroll)) && (this.testListener == null)) {
                          this.handler.postDelayed(this.AdTask, 2000L);
                        }
                        return;
                        localThrowable1 = localThrowable1;
                        localThrowable1.printStackTrace();
                        admob = false;
                        this.admobInterstitial = null;
                        continue;
                        localThrowable2 = localThrowable2;
                        greystripe = false;
                        continue;
                        localThrowable3 = localThrowable3;
                        localThrowable3.printStackTrace();
                        tremor = false;
                        this.tremorStarted = false;
                        continue;
                        this.cb.onCreate((Activity)this.activity, AdInfo.ChartboostAppID, AdInfo.ChartboostAppSignature, this.chartBoostDelegate);
                        continue;
                        localThrowable4 = localThrowable4;
                        chartboost = false;
                        continue;
                        localThrowable5 = localThrowable5;
                        flurry = false;
                        continue;
                        localThrowable6 = localThrowable6;
                        mopub = false;
                        this.mopubInterstitial = null;
                        continue;
                        localThrowable7 = localThrowable7;
                        Log.i("adControl", "initAdNets EXCEPTION");
                        localThrowable7.printStackTrace();
                        this.appLovin = false;
                        continue;
                        if (this.needTapsenseAd)
                        {
                          this.needTapsenseAd = false;
                          if (!TapSenseAds.getInstance().isReady()) {
                            TapSenseAds.getInstance().requestAd();
                          }
                        }
                      }
                      localThrowable8 = localThrowable8;
                      Log.i("adControl", "initAdNets EXCEPTION");
                      localThrowable8.printStackTrace();
                      this.tapsense = false;
                    }
                    catch (Throwable localThrowable9)
                    {
                      for (;;)
                      {
                        localThrowable9.printStackTrace();
                        brightroll = false;
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  private void initPercent()
  {
    if (this.rand == null) {
      this.rand = new Random();
    }
    this.totPercent = 0;
    this.percent = new int[5];
    int i = 0;
    if (i >= 5)
    {
      if (admob)
      {
        if (this.admobPercent > 0) {
          break label357;
        }
        admob = false;
      }
      label54:
      if (rhythm)
      {
        if (this.rhythmPercent > 0) {
          break label383;
        }
        rhythm = false;
      }
      label71:
      if (tremor)
      {
        if (this.tremorPercent > 0) {
          break label409;
        }
        tremor = false;
      }
      label88:
      if (brightroll)
      {
        if (this.brightrollPercent > 0) {
          break label435;
        }
        brightroll = false;
      }
      label105:
      this.nonPremTotPercent = 0;
      this.nonPremPercent = new int[7];
      i = 0;
    }
    for (;;)
    {
      if (i >= 7)
      {
        if ((greystripe) && (greystripePercent > 0))
        {
          this.nonPremTotPercent += greystripePercent;
          this.nonPremPercent[0] = this.nonPremTotPercent;
        }
        if ((this.appLovin) && (this.appLovinPercent > 0))
        {
          this.nonPremTotPercent += this.appLovinPercent;
          this.nonPremPercent[1] = this.nonPremTotPercent;
        }
        if ((mopub) && (this.mopubPercent > 0))
        {
          this.nonPremTotPercent += this.mopubPercent;
          this.nonPremPercent[2] = this.nonPremTotPercent;
        }
        if ((chartboost) && (this.chartboostPercent > 0))
        {
          this.nonPremTotPercent += this.chartboostPercent;
          this.nonPremPercent[3] = this.nonPremTotPercent;
        }
        if ((flurry) && (this.flurryPercent > 0))
        {
          this.nonPremTotPercent += this.flurryPercent;
          this.nonPremPercent[4] = this.nonPremTotPercent;
        }
        if ((this.tapsense) && (this.tapsensePercent > 0))
        {
          this.nonPremTotPercent += this.tapsensePercent;
          this.nonPremPercent[5] = this.nonPremTotPercent;
        }
        return;
        this.percent[i] = -1;
        i += 1;
        break;
        label357:
        this.totPercent += this.admobPercent;
        this.percent[0] = this.totPercent;
        break label54;
        label383:
        this.totPercent += this.rhythmPercent;
        this.percent[1] = this.totPercent;
        break label71;
        label409:
        this.totPercent += this.tremorPercent;
        this.percent[2] = this.totPercent;
        break label88;
        label435:
        this.totPercent += this.brightrollPercent;
        this.percent[3] = this.totPercent;
        break label105;
      }
      this.nonPremPercent[i] = -1;
      i += 1;
    }
  }
  
  private boolean isAppLovinAtLimit()
  {
    try
    {
      int i = Calendar.getInstance().get(6);
      SharedPreferences localSharedPreferences = ((Activity)this.activity).getSharedPreferences("MWAds", 0);
      if (localSharedPreferences.getInt("ALDay", 0) == i)
      {
        this.appLovinToday = localSharedPreferences.getInt("ALToday", 0);
        if (this.appLovinToday < this.appLovinPerDay) {
          break label76;
        }
        return true;
      }
      this.appLovinToday = 0;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return false;
    label76:
    return false;
  }
  
  private boolean isBrightrollAtLimit()
  {
    try
    {
      int i = Calendar.getInstance().get(6);
      SharedPreferences localSharedPreferences = ((Activity)this.activity).getSharedPreferences("MWAds", 0);
      if (localSharedPreferences.getInt("BRDay", 0) == i)
      {
        this.brightrollToday = localSharedPreferences.getInt("BRToday", 0);
        if (this.brightrollToday < this.brightrollPerDay) {
          break label76;
        }
        return true;
      }
      this.brightrollToday = 0;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return false;
    label76:
    return false;
  }
  
  private boolean isInstalled(String paramString)
  {
    try
    {
      List localList = ((Activity)this.activity).getPackageManager().getInstalledPackages(0);
      int i = 0;
      for (;;)
      {
        if (i >= localList.size()) {
          return false;
        }
        PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
        if (localPackageInfo.packageName != null)
        {
          boolean bool = localPackageInfo.packageName.equals(paramString);
          if (bool) {
            return true;
          }
        }
        i += 1;
      }
      return false;
    }
    catch (Throwable paramString) {}
  }
  
  private boolean isWifiAvail()
  {
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)((Activity)this.activity).getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo != null)
      {
        int i = localNetworkInfo.getType();
        if (i == 0) {
          return false;
        }
      }
      return true;
    }
    catch (Throwable localThrowable) {}
    return false;
  }
  
  private void loadLastAdNets()
  {
    int i;
    do
    {
      try
      {
        localSharedPreferences = ((Activity)this.activity).getSharedPreferences("MWAds", 0);
        i = 0;
      }
      catch (Throwable localThrowable)
      {
        SharedPreferences localSharedPreferences;
        String str;
        localThrowable.printStackTrace();
        return;
      }
      str = localSharedPreferences.getString("LastNet" + i, null);
      if (str != null) {
        this.lastAdNet.add(str);
      }
      i += 1;
    } while (i < 3);
  }
  
  private boolean privateDisplayAd()
  {
    this.lastAdTime = SystemClock.uptimeMillis();
    if ((this.admobInARow < 2) && (displayAdmob())) {
      this.admobInARow += 1;
    }
    do
    {
      return true;
      if (this.admobInARow > 0) {
        this.admobInARow -= 1;
      }
      if ((this.brightrollInARow < 2) && (displayBrightroll()))
      {
        this.brightrollInARow += 1;
        return true;
      }
      if (this.brightrollInARow > 0) {
        this.brightrollInARow -= 1;
      }
      if ((this.tremorInARow < 2) && (displayTremor()))
      {
        this.tremorInARow += 1;
        return true;
      }
      if (this.tremorInARow > 0) {
        this.tremorInARow -= 1;
      }
    } while (((this.appLovinDeliver > 0) && (displayApplovin())) || (displayNonPremium()) || (displayGreystripe()) || (displayInternalAd()) || (displayMoPub()) || (displayChartboost()) || (displayTapsense()));
    return false;
  }
  
  private boolean requestAppLovin()
  {
    if (!this.appLovin) {}
    while ((this.paused) && (!this.noStartStop)) {
      return false;
    }
    if (this.appLovinToday >= this.appLovinPerDay)
    {
      this.appLovin = false;
      initPercent();
      return false;
    }
    try
    {
      this.appLovinReady = false;
      this.appLovinSdk.getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, this.alDelegate);
      return true;
    }
    catch (Throwable localThrowable)
    {
      Log.i("adControl", "requestAppLovin EXCEPTION");
      localThrowable.printStackTrace();
      this.appLovin = false;
    }
    return false;
  }
  
  private void requestPremium()
  {
    if (this.totPercent < 1) {}
    for (;;)
    {
      return;
      if ((!this.admobReady) && (!this.brightrollReady) && (this.testListener == null) && (this.premiumErrors <= 15))
      {
        int j = this.rand.nextInt(this.totPercent);
        int i = 0;
        while (i < 5)
        {
          if (j < this.percent[i])
          {
            switch (i)
            {
            case 1: 
            default: 
              Log.e("adControl", "invalid ad net");
              return;
            case 0: 
              try
              {
                getAdmob();
                return;
              }
              catch (Throwable localThrowable)
              {
                return;
              }
            case 2: 
              this.tryTremor = true;
              return;
            }
            getBrightroll();
            return;
          }
          i += 1;
        }
      }
    }
  }
  
  private void restoreVolume()
  {
    if ((this.musicPaused) && ((this.lastAdNetOld == "Tremor") || (this.lastAdNetOld == "Brightroll")) && (Build.VERSION.SDK_INT >= 8)) {
      try
      {
        if ((this.localMusic) && (!this.audio.isMusicActive()))
        {
          Intent localIntent = new Intent("com.android.music.musicservicecommand");
          localIntent.putExtra("command", "play");
          this.activity.sendOrderedAudioBroadcast(localIntent, true);
        }
        for (;;)
        {
          this.musicPaused = false;
          return;
          if (this.audioListener != null) {
            this.audio.abandonAudioFocus((AudioManager.OnAudioFocusChangeListener)this.audioListener);
          }
        }
        if (!this.volumeSilenced) {
          return;
        }
      }
      catch (Throwable localThrowable1)
      {
        return;
      }
    }
    if (!this.activity.isSoundOn()) {
      try
      {
        this.audio.setStreamVolume(3, this.prevVolume, 0);
        this.volumeSilenced = false;
        return;
      }
      catch (Throwable localThrowable2)
      {
        for (;;) {}
      }
    }
  }
  
  /* Error */
  private void silence()
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_0
    //   2: getfield 1224	com/mobilityware/advertising/AdControl:audio	Landroid/media/AudioManager;
    //   5: iconst_3
    //   6: invokevirtual 1268	android/media/AudioManager:getStreamVolume	(I)I
    //   9: putfield 1261	com/mobilityware/advertising/AdControl:prevVolume	I
    //   12: aload_0
    //   13: getfield 1224	com/mobilityware/advertising/AdControl:audio	Landroid/media/AudioManager;
    //   16: ifnull +147 -> 163
    //   19: aload_0
    //   20: getfield 1224	com/mobilityware/advertising/AdControl:audio	Landroid/media/AudioManager;
    //   23: invokevirtual 1229	android/media/AudioManager:isMusicActive	()Z
    //   26: ifeq +137 -> 163
    //   29: aload_0
    //   30: getfield 346	com/mobilityware/advertising/AdControl:lastAdNetOld	Ljava/lang/String;
    //   33: ldc 119
    //   35: if_acmpeq +12 -> 47
    //   38: aload_0
    //   39: getfield 346	com/mobilityware/advertising/AdControl:lastAdNetOld	Ljava/lang/String;
    //   42: ldc 62
    //   44: if_acmpne +119 -> 163
    //   47: getstatic 1220	android/os/Build$VERSION:SDK_INT	I
    //   50: bipush 8
    //   52: if_icmplt +111 -> 163
    //   55: aload_0
    //   56: iconst_0
    //   57: putfield 1222	com/mobilityware/advertising/AdControl:localMusic	Z
    //   60: aload_0
    //   61: iconst_0
    //   62: putfield 1215	com/mobilityware/advertising/AdControl:musicPaused	Z
    //   65: new 1231	android/content/Intent
    //   68: dup
    //   69: ldc_w 1233
    //   72: invokespecial 1234	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   75: astore_2
    //   76: aload_2
    //   77: ldc_w 1236
    //   80: ldc_w 1270
    //   83: invokevirtual 1242	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   86: pop
    //   87: aload_0
    //   88: getfield 324	com/mobilityware/advertising/AdControl:activity	Lcom/mobilityware/advertising/MWActivity;
    //   91: aload_2
    //   92: iconst_0
    //   93: invokeinterface 1246 3 0
    //   98: ldc2_w 1271
    //   101: invokestatic 1278	java/lang/Thread:sleep	(J)V
    //   104: aload_0
    //   105: getfield 1224	com/mobilityware/advertising/AdControl:audio	Landroid/media/AudioManager;
    //   108: invokevirtual 1229	android/media/AudioManager:isMusicActive	()Z
    //   111: ifne +14 -> 125
    //   114: aload_0
    //   115: iconst_1
    //   116: putfield 1222	com/mobilityware/advertising/AdControl:localMusic	Z
    //   119: aload_0
    //   120: iconst_1
    //   121: putfield 1215	com/mobilityware/advertising/AdControl:musicPaused	Z
    //   124: return
    //   125: aload_0
    //   126: new 34	com/mobilityware/advertising/AdControl$9
    //   129: dup
    //   130: aload_0
    //   131: invokespecial 1279	com/mobilityware/advertising/AdControl$9:<init>	(Lcom/mobilityware/advertising/AdControl;)V
    //   134: putfield 1248	com/mobilityware/advertising/AdControl:audioListener	Ljava/lang/Object;
    //   137: aload_0
    //   138: getfield 1224	com/mobilityware/advertising/AdControl:audio	Landroid/media/AudioManager;
    //   141: aload_0
    //   142: getfield 1248	com/mobilityware/advertising/AdControl:audioListener	Ljava/lang/Object;
    //   145: checkcast 1250	android/media/AudioManager$OnAudioFocusChangeListener
    //   148: iconst_3
    //   149: iconst_3
    //   150: invokevirtual 1283	android/media/AudioManager:requestAudioFocus	(Landroid/media/AudioManager$OnAudioFocusChangeListener;II)I
    //   153: iconst_1
    //   154: if_icmpne +57 -> 211
    //   157: aload_0
    //   158: iconst_1
    //   159: putfield 1215	com/mobilityware/advertising/AdControl:musicPaused	Z
    //   162: return
    //   163: aload_0
    //   164: getfield 324	com/mobilityware/advertising/AdControl:activity	Lcom/mobilityware/advertising/MWActivity;
    //   167: invokeinterface 1259 1 0
    //   172: ifne +39 -> 211
    //   175: aload_0
    //   176: getfield 1224	com/mobilityware/advertising/AdControl:audio	Landroid/media/AudioManager;
    //   179: invokevirtual 1229	android/media/AudioManager:isMusicActive	()Z
    //   182: istore_1
    //   183: iload_1
    //   184: ifne +27 -> 211
    //   187: aload_0
    //   188: getfield 1224	com/mobilityware/advertising/AdControl:audio	Landroid/media/AudioManager;
    //   191: iconst_3
    //   192: iconst_0
    //   193: iconst_0
    //   194: invokevirtual 1265	android/media/AudioManager:setStreamVolume	(III)V
    //   197: aload_0
    //   198: iconst_1
    //   199: putfield 1256	com/mobilityware/advertising/AdControl:volumeSilenced	Z
    //   202: return
    //   203: astore_2
    //   204: goto -7 -> 197
    //   207: astore_2
    //   208: goto -196 -> 12
    //   211: return
    //   212: astore_2
    //   213: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	214	0	this	AdControl
    //   182	2	1	bool	boolean
    //   75	17	2	localIntent	Intent
    //   203	1	2	localThrowable1	Throwable
    //   207	1	2	localThrowable2	Throwable
    //   212	1	2	localThrowable3	Throwable
    // Exception table:
    //   from	to	target	type
    //   187	197	203	java/lang/Throwable
    //   0	12	207	java/lang/Throwable
    //   12	47	212	java/lang/Throwable
    //   47	124	212	java/lang/Throwable
    //   125	162	212	java/lang/Throwable
    //   163	183	212	java/lang/Throwable
    //   197	202	212	java/lang/Throwable
  }
  
  private boolean smallScreen()
  {
    try
    {
      int i = ((Activity)this.activity).getResources().getConfiguration().screenLayout;
      if ((i & 0xF) == 1) {
        return true;
      }
    }
    catch (Throwable localThrowable) {}
    return false;
  }
  
  public void adDidClick() {}
  
  public void adDidCompletion() {}
  
  public void adDidFirstQuartile() {}
  
  public void adDidImpression() {}
  
  public void adDidMidpoint() {}
  
  public void adDidThirdQuartile() {}
  
  public void adDismissed(Ad paramAd)
  {
    this.dismissed = true;
    restoreVolume();
    this.lastAdTime = SystemClock.uptimeMillis();
    if (!this.paused) {
      this.handler.postDelayed(this.AdTask, 60000L);
    }
  }
  
  public void adFetchFailed(Ad paramAd)
  {
    this.brightrollReady = false;
    this.brightrollAd = null;
    if (this.testListener != null) {
      this.testListener.requestFailed(3);
    }
    this.premiumErrors += 1;
    this.brightrollErrors += 1;
    if (this.premiumErrors > 15) {}
    do
    {
      return;
      if (this.brightrollErrors >= 3)
      {
        brightroll = false;
        initPercent();
      }
    } while ((this.paused) || (this.testListener != null));
    this.handler.postDelayed(this.AdTask, 30000L);
  }
  
  public void adFetched(Ad paramAd)
  {
    this.brightrollReady = true;
    this.brightrollAd = paramAd;
    if (this.testListener != null) {
      this.testListener.requestSuccessfull(3);
    }
  }
  
  public void adcDestroy(Activity paramActivity)
  {
    if (tremor) {}
    try
    {
      tremor = false;
      TremorVideo.destroy();
      if ((chartboost) && (this.cb != null)) {
        Chartboost.sharedChartboost().onDestroy(paramActivity);
      }
      if (this.mopubInterstitial != null)
      {
        this.mopubInterstitial.destroy();
        this.mopubInterstitial = null;
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
  }
  
  public void adcPause()
  {
    this.paused = true;
    try
    {
      this.handler.removeCallbacks(this.AdTask);
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  public void adcResume()
  {
    this.paused = false;
    if ((!this.admobReady) && (!this.brightrollReady) && (!this.tryTremor)) {}
    try
    {
      this.handler.removeCallbacks(this.AdTask);
      if (this.dismissed) {
        this.handler.postDelayed(this.AdTask, 30000L);
      }
      for (;;)
      {
        this.dismissed = false;
        if ((greystripe) && (this.gsAd != null) && (this.needGSFetch)) {
          this.needGSFetch = false;
        }
        try
        {
          this.gsAd.fetch();
          return;
        }
        catch (Throwable localThrowable1)
        {
          return;
        }
        this.handler.postDelayed(this.AdTask, 500L);
      }
    }
    catch (Throwable localThrowable2)
    {
      for (;;) {}
    }
  }
  
  public boolean displayAd()
  {
    this.adWasDisplayed = false;
    long l = SystemClock.uptimeMillis();
    if (l - this.lastAdTime < this.minTime) {
      return false;
    }
    this.lastAdTime = l;
    this.handler.postDelayed(this.DisplayTask, 250L);
    this.eventCount = 0;
    return true;
  }
  
  public boolean displayTestAd(int paramInt)
  {
    this.adWasDisplayed = false;
    switch (paramInt)
    {
    }
    for (;;)
    {
      return this.adWasDisplayed;
      this.adWasDisplayed = displayAdmob();
      continue;
      this.adWasDisplayed = false;
      continue;
      this.adWasDisplayed = displayTremor();
      continue;
      this.adWasDisplayed = displayBrightroll();
      continue;
      this.adWasDisplayed = displayGreystripe();
      continue;
      this.adWasDisplayed = displayChartboost();
      continue;
      this.adWasDisplayed = displayMoPub();
      continue;
      this.adWasDisplayed = false;
      continue;
      this.adWasDisplayed = displayApplovin();
      continue;
      this.adWasDisplayed = displayInternalAd();
      continue;
      this.adWasDisplayed = displayTapsense();
    }
  }
  
  public Activity getAdActivity()
  {
    return (Activity)this.activity;
  }
  
  public boolean getAdWasDisplayed()
  {
    return this.adWasDisplayed;
  }
  
  public int getDisplayTaskDelayTime()
  {
    return 250;
  }
  
  public String getLastAdNet()
  {
    if ((this.lastAdNet == null) || (this.lastAdNet.size() == 0)) {
      return "No ad";
    }
    return (String)this.lastAdNet.get(0);
  }
  
  public String getLastAdNets()
  {
    if ((this.lastAdNet == null) || (this.lastAdNet.size() == 0)) {
      return "No ad";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int j = this.lastAdNet.size();
    int i = j;
    if (j > 3) {
      i = 3;
    }
    j = 0;
    for (;;)
    {
      if (j >= i) {
        return localStringBuilder.toString();
      }
      if (j > 0) {
        localStringBuilder.append(", ");
      }
      localStringBuilder.append((String)this.lastAdNet.get(j));
      j += 1;
    }
  }
  
  public long getMinTime()
  {
    return this.minTime;
  }
  
  public boolean getMopub()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.mopubInterstitial != null)
    {
      bool1 = bool2;
      if (!mopub) {}
    }
    try
    {
      this.mopubInterstitial.load();
      bool1 = true;
      return bool1;
    }
    catch (Throwable localThrowable)
    {
      mopub = false;
      this.mopubInterstitial = null;
    }
    return false;
  }
  
  public void internalAdDismiss()
  {
    this.internalAdShowing = false;
    this.lastAdTime = SystemClock.uptimeMillis();
  }
  
  public boolean newSignificantEvent()
  {
    int i = this.eventCount + 1;
    this.eventCount = i;
    this.eventCount = (i % this.eventInterval);
    if (SystemClock.uptimeMillis() - this.lastAdTime >= this.maxTime) {
      return displayAd();
    }
    if (this.eventCount == 0)
    {
      if (displayAd()) {
        return true;
      }
      this.eventCount = (this.eventInterval - 1);
    }
    return false;
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 888)
    {
      this.dismissed = true;
      restoreVolume();
      this.lastAdTime = SystemClock.uptimeMillis();
      if (!this.paused) {
        this.handler.postDelayed(this.AdTask, 60000L);
      }
    }
  }
  
  public void onAdClickthrough(GSAd paramGSAd)
  {
    this.needGSFetch = true;
  }
  
  public void onAdCollapse(GSAd paramGSAd) {}
  
  public void onAdDismissal(GSAd paramGSAd)
  {
    if (this.gsAd != null) {
      restoreVolume();
    }
    try
    {
      if (this.testListener == null) {
        this.handler.postDelayed(this.GSFetchTask, 10000L);
      }
      this.lastAdTime = SystemClock.uptimeMillis();
      return;
    }
    catch (Throwable paramGSAd)
    {
      for (;;) {}
    }
  }
  
  public void onAdExpansion(GSAd paramGSAd) {}
  
  public boolean onBackPressed()
  {
    if ((chartboost) && (this.cb != null) && (this.chartboostShowing)) {
      try
      {
        boolean bool = Chartboost.sharedChartboost().onBackPressed();
        return bool;
      }
      catch (Throwable localThrowable) {}
    }
    return false;
  }
  
  public void onFailedToFetchAd(GSAd paramGSAd, GSAdErrorCode paramGSAdErrorCode)
  {
    this.gsErrors += 1;
    if ((this.gsErrors < 15) && (this.gsAd != null)) {}
    try
    {
      if ((this.testListener == null) && (!this.paused)) {
        this.handler.postDelayed(this.GSFetchTask, this.gsErrors * 3000);
      }
      for (;;)
      {
        if (this.testListener != null) {
          this.testListener.requestFailed(4);
        }
        return;
        greystripe = false;
        this.gsAd = null;
      }
    }
    catch (Throwable paramGSAd)
    {
      for (;;) {}
    }
  }
  
  public void onFetchedAd(GSAd paramGSAd)
  {
    this.gsErrors = 0;
    if (this.testListener != null) {
      this.testListener.requestSuccessfull(4);
    }
  }
  
  public void onInterstitialClicked(MoPubInterstitial paramMoPubInterstitial) {}
  
  public void onInterstitialDismissed(MoPubInterstitial paramMoPubInterstitial)
  {
    restoreVolume();
    try
    {
      if (this.testListener == null) {
        this.handler.postDelayed(this.MopubFetchTask, 10000L);
      }
      this.lastAdTime = SystemClock.uptimeMillis();
      return;
    }
    catch (Throwable paramMoPubInterstitial)
    {
      mopub = false;
      this.mopubInterstitial = null;
    }
  }
  
  public void onInterstitialFailed(MoPubInterstitial paramMoPubInterstitial, MoPubErrorCode paramMoPubErrorCode)
  {
    this.mopubErrors += 1;
    if (this.testListener != null) {
      this.testListener.requestFailed(6);
    }
    if (this.mopubErrors >= 2)
    {
      mopub = false;
      initPercent();
    }
    while (this.testListener != null) {
      return;
    }
    this.handler.postDelayed(this.MopubFetchTask, 20000L);
  }
  
  public void onInterstitialLoaded(MoPubInterstitial paramMoPubInterstitial)
  {
    this.mopubReady = true;
    if (this.testListener != null) {
      this.testListener.requestSuccessfull(6);
    }
  }
  
  public void onInterstitialShown(MoPubInterstitial paramMoPubInterstitial) {}
  
  public void onStart()
  {
    if ((chartboost) && (this.cb != null)) {
      Chartboost.sharedChartboost().onStart((Activity)this.activity);
    }
  }
  
  public void onStop()
  {
    if ((chartboost) && (this.cb != null)) {
      Chartboost.sharedChartboost().onStop((Activity)this.activity);
    }
  }
  
  public void paramsChanged()
  {
    this.gotParams = true;
    try
    {
      bool = this.activity.isAmazon();
      Object localObject;
      if (bool)
      {
        greystripe = this.adParams.getBool("GreystripeA3", false);
        GreystripeID = "13360bcc-f43f-454c-a3df-cd4ebdf2d8c7";
        if ((GreystripeID == null) || (GreystripeID.length() == 0)) {
          greystripe = false;
        }
        greystripePercent = this.adParams.getInt("GreystripePercent", 0);
        if (!bool) {
          break label1276;
        }
        admob = this.adParams.getBool("AdmobA", false);
        localObject = this.adParams.getString("AdMobID", "");
        if ((localObject != null) && (((String)localObject).length() > 0)) {
          AdInfo.AdMobID = (String)localObject;
        }
        if (AdInfo.AdMobID.length() == 0) {
          admob = false;
        }
        if (Build.VERSION.SDK_INT < 9) {
          admob = false;
        }
        if (!bool) {
          break label1292;
        }
        tremor = this.adParams.getBool("Tremor2A", false);
        if ((AdInfo.TremorIDs == null) || (AdInfo.TremorIDs.length == 0) || (AdInfo.TremorIDs[0].length() == 0)) {
          tremor = false;
        }
        if (!bool) {
          break label1308;
        }
        rhythm = this.adParams.getBool("RhythmA", false);
        localObject = this.adParams.getString("RhythmID", "");
        if ((localObject != null) && (((String)localObject).length() > 0)) {
          AdInfo.RhythmID = (String)localObject;
        }
        if (AdInfo.RhythmID.length() == 0) {
          rhythm = false;
        }
        if (!bool) {
          break label1325;
        }
        brightroll = this.adParams.getBool("BrightrollA2", false);
        if (!brightroll)
        {
          if (!bool) {
            break label1342;
          }
          brightroll = this.adParams.getBool("BrightrollA3", false);
        }
        if (AdInfo.BrightrollID == 0) {
          brightroll = false;
        }
        this.brightrollMaxAPILevel = this.adParams.getInt("BrightrollMaxAPILevel", 99);
        this.mopubPercent = this.adParams.getInt("MopubPercent", -1);
        if ((this.mopubPercent <= -1) || (bool)) {
          break label1359;
        }
        mopub = true;
        if ((AdInfo.MoPubID == null) || (AdInfo.MoPubID.length() == 0)) {
          mopub = false;
        }
        if (smallScreen()) {
          mopub = false;
        }
        if (Build.VERSION.SDK_INT >= 9) {
          break label1366;
        }
        this.chartboostPercent = 0;
        if (this.chartboostPercent <= 0) {
          break label1406;
        }
        chartboost = true;
        if (!bool) {
          break label1413;
        }
        if ((AdInfo.ChartboostAppIDA.length() == 0) || (AdInfo.ChartboostAppSignatureA.length() == 0))
        {
          chartboost = false;
          this.chartboostPercent = 0;
        }
        if (!bool) {
          break label1443;
        }
        this.flurryPercent = 0;
        this.flurryPercent = 0;
        if (this.flurryPercent <= 0) {
          break label1461;
        }
        flurry = true;
        if (AdInfo.FlurryAdSpace.length() == 0)
        {
          flurry = false;
          this.flurryPercent = 0;
        }
        if ((Build.VERSION.SDK_INT > 15) || (Build.VERSION.SDK_INT < 9))
        {
          flurry = false;
          this.flurryPercent = 0;
        }
        if (this.activity.getViewGroup() == null)
        {
          flurry = false;
          this.flurryPercent = 0;
        }
        this.admobPercent = this.adParams.getInt("AdmobPercent", 0);
        this.tremorPercent = this.adParams.getInt("TremorPercent", 0);
        this.rhythmPercent = this.adParams.getInt("RhythmPercent", 0);
        this.brightrollPercent = this.adParams.getInt("BrightrollPercent", 0);
        if ((Build.VERSION.SDK_INT < 8) || (Build.VERSION.SDK_INT > this.brightrollMaxAPILevel))
        {
          brightroll = false;
          this.brightrollPercent = 0;
        }
        this.brightrollPerDay = this.adParams.getInt("BrightrollPerDay", 1);
        this.appLovinDeliver = this.adParams.getInt("AppLovinDeliver", 0);
        this.appLovinPercent = this.adParams.getInt("AppLovinPercent", -2);
        if (this.appLovinPercent == -2) {
          this.appLovinPercent = this.adParams.getInt("AppLovin2Percent", -1);
        }
        if ((this.appLovinPercent <= -1) || (bool)) {
          break label1468;
        }
        this.appLovin = true;
        if ((AdInfo.AppLovinID == null) || (AdInfo.AppLovinID.length() == 0)) {
          this.appLovin = false;
        }
        this.appLovinPerDay = this.adParams.getInt("AppLovinPerDay", 2);
        if ((this.appLovin) && (isAppLovinAtLimit())) {
          this.appLovin = false;
        }
        if (Build.VERSION.SDK_INT < 14) {
          this.appLovin = false;
        }
        this.tapsensePercent = this.adParams.getInt("TapsensePercent", -1);
        if ((this.tapsensePercent <= -1) || (bool)) {
          break label1476;
        }
        this.tapsense = true;
        if ((AdInfo.TapsenseAppID == null) || (AdInfo.TapsenseAppID.length() == 0)) {
          this.tapsense = false;
        }
        i = this.adParams.getInt("IA", 2);
        if (i != this.IA)
        {
          this.internalAdNbr = 0;
          this.internalAdCount = 0;
          this.IA = i;
        }
        if (!bool) {
          break label1484;
        }
        AdInfo.PrimaryInternalAdURL = AdInfo.AmazonInternalAdURL;
        AdInfo.BackupInternalAdURL = AdInfo.AmazonBackup1InternalAdURL;
        AdInfo.InternalAdImageID = AdInfo.AmazonInternal1AdImageID;
        if ((brightroll) && (isBrightrollAtLimit())) {
          brightroll = false;
        }
        if (this.crashed)
        {
          Log.i("adControl", "crashed=true");
          greystripe = false;
          admob = false;
          tremor = false;
          rhythm = false;
          brightroll = false;
          chartboost = false;
          flurry = false;
          mopub = false;
          this.appLovin = false;
          this.tapsense = false;
        }
        if (this.tv)
        {
          brightroll = false;
          admob = false;
          chartboost = false;
          flurry = false;
          mopub = false;
          this.tapsense = false;
        }
        long l = this.activity.availFSSpace();
        if (l < 2000000L)
        {
          Log.i("adControl", "free space=" + l);
          greystripe = false;
          admob = false;
          tremor = false;
          rhythm = false;
          brightroll = false;
          chartboost = false;
          flurry = false;
          mopub = false;
          this.tapsense = false;
        }
        if (this.testListener != null)
        {
          Log.i("adControl", "* * *  Test Mode - all networks turned on");
          admob = true;
          greystripe = true;
          tremor = true;
          chartboost = true;
          brightroll = true;
          this.tapsense = true;
          mopub = true;
          this.appLovin = true;
          this.admobPercent = 1;
          greystripePercent = 1;
          this.tremorPercent = 1;
          this.chartboostPercent = 1;
          this.brightrollPercent = 1;
          this.tapsensePercent = 1;
          this.mopubPercent = 1;
          this.appLovinPercent = 1;
        }
      }
      try
      {
        localObject = ((Activity)this.activity).getSharedPreferences("MWCrash", 0);
        if (!bool) {
          break label1505;
        }
        i = this.adParams.getInt("CVA", 0);
        if (((SharedPreferences)localObject).getInt("VER", 0) != i)
        {
          localObject = ((SharedPreferences)localObject).edit();
          ((SharedPreferences.Editor)localObject).putInt("VER", i);
          ((SharedPreferences.Editor)localObject).commit();
        }
      }
      catch (Throwable localThrowable2)
      {
        for (;;) {}
      }
      ((Activity)this.activity).runOnUiThread(this.InitTask);
      return;
    }
    catch (Throwable localThrowable1)
    {
      for (;;)
      {
        int i;
        boolean bool = false;
        continue;
        greystripe = this.adParams.getBool("Greystripe3", false);
        GreystripeID = "28c67532-a45d-4129-a14f-96cf44bc81e3";
        continue;
        label1276:
        admob = this.adParams.getBool("Admob", false);
        continue;
        label1292:
        tremor = this.adParams.getBool("Tremor", false);
        continue;
        label1308:
        rhythm = this.adParams.getBool("Rhythm", false);
        continue;
        label1325:
        brightroll = this.adParams.getBool("Brightroll2", false);
        continue;
        label1342:
        brightroll = this.adParams.getBool("Brightroll3", false);
        continue;
        label1359:
        mopub = false;
        continue;
        label1366:
        if (bool)
        {
          this.chartboostPercent = this.adParams.getInt("Chartboost2PercentA", 0);
        }
        else
        {
          this.chartboostPercent = this.adParams.getInt("Chartboost2Percent", 0);
          continue;
          label1406:
          chartboost = false;
          continue;
          label1413:
          if ((AdInfo.ChartboostAppID.length() == 0) || (AdInfo.ChartboostAppSignature.length() == 0))
          {
            chartboost = false;
            this.chartboostPercent = 0;
            continue;
            label1443:
            this.flurryPercent = this.adParams.getInt("Flurry2Percent", 0);
            continue;
            label1461:
            flurry = false;
            continue;
            label1468:
            this.appLovin = false;
            continue;
            label1476:
            this.tapsense = false;
            continue;
            label1484:
            AdInfo.PrimaryInternalAdURL = AdInfo.Primary1InternalAdURL;
            AdInfo.BackupInternalAdURL = AdInfo.Backup1InternalAdURL;
            AdInfo.InternalAdImageID = AdInfo.Internal1AdImageID;
            continue;
            label1505:
            i = this.adParams.getInt("CV", 0);
          }
        }
      }
    }
  }
  
  public void reInitAdNets()
  {
    boolean bool = false;
    if (this.adParams != null) {
      bool = this.adParams.getBool("RI", true);
    }
    if (bool)
    {
      this.admobInterstitial = null;
      if (greystripe) {
        this.gsAd = null;
      }
      if (tremor) {
        this.tremorStarted = false;
      }
      if ((mopub) && (this.mopubInterstitial == null)) {}
    }
    try
    {
      this.mopubInterstitial.setInterstitialAdListener(null);
      this.mopubInterstitial.destroy();
      this.mopubInterstitial = null;
      initAdNets();
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
  }
  
  public boolean requestTestAd(int paramInt)
  {
    switch (paramInt)
    {
    case 1: 
    case 7: 
    default: 
      return false;
    case 0: 
      return getAdmob();
    case 2: 
      return true;
    case 3: 
      return getBrightroll();
    case 4: 
      this.gsAd.fetch();
      return true;
    case 5: 
      if (!this.chartboostRequested)
      {
        this.chartboostRequested = true;
        this.cb.cacheInterstitial();
      }
      return true;
    case 6: 
      return getMopub();
    case 8: 
      return requestAppLovin();
    case 10: 
      return true;
    }
    return true;
  }
  
  public void resetEventCount()
  {
    this.eventCount = 0;
  }
  
  public void saveLastAdNets()
  {
    try
    {
      if (this.lastAdNet != null)
      {
        int j = this.lastAdNet.size();
        int i = j;
        if (j > 3) {
          i = 3;
        }
        SharedPreferences.Editor localEditor = ((Activity)this.activity).getSharedPreferences("MWAds", 0).edit();
        j = 0;
        for (;;)
        {
          if (j >= i)
          {
            localEditor.commit();
            return;
          }
          localEditor.putString("LastNet" + j, (String)this.lastAdNet.get(j));
          j += 1;
        }
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
  
  public void setAudioManager(AudioManager paramAudioManager)
  {
    this.audio = paramAudioManager;
  }
  
  public void setEventInterval(int paramInt)
  {
    this.eventInterval = paramInt;
    resetEventCount();
  }
  
  public void setMaxTime(long paramLong)
  {
    this.maxTime = paramLong;
  }
  
  public void switchOrientation(int paramInt)
  {
    try
    {
      if (this.internalAdShowing)
      {
        LinearLayout localLinearLayout = (LinearLayout)((Activity)this.activity).findViewById(this.activity.getInternalAdLayoutId());
        if (localLinearLayout != null)
        {
          if (paramInt == 2)
          {
            localLinearLayout.setOrientation(0);
            return;
          }
          localLinearLayout.setOrientation(1);
          return;
        }
      }
    }
    catch (Throwable localThrowable) {}
  }
  
  public void tapSenseAdDidDisappear()
  {
    restoreVolume();
    this.lastAdTime = SystemClock.uptimeMillis();
  }
  
  public void tapSenseAdWillAppear() {}
  
  public void tapSenseDidFailToLoadAd(TapSenseAdsErrorCode paramTapSenseAdsErrorCode)
  {
    this.needTapsenseAd = true;
  }
  
  public void tapSenseDidLoadAd()
  {
    this.needTapsenseAd = false;
  }
  
  private class AppLovinDelegate
    implements AppLovinAdDisplayListener, AppLovinAdLoadListener, AppLovinAdClickListener, AppLovinAdVideoPlaybackListener
  {
    private AppLovinDelegate() {}
    
    public void adClicked(AppLovinAd paramAppLovinAd)
    {
      try
      {
        AdControl.this.activity.setNoStartStop();
        AdControl.this.appLovinAdDialog.dismiss();
        return;
      }
      catch (Throwable paramAppLovinAd) {}
    }
    
    public void adDisplayed(AppLovinAd paramAppLovinAd) {}
    
    public void adHidden(AppLovinAd paramAppLovinAd)
    {
      AdControl.this.appLovinAd = null;
      AdControl.this.appLovinAdDialog = null;
      if (AdControl.this.testListener == null) {
        AdControl.this.requestAppLovin();
      }
      AdControl.this.lastAdTime = SystemClock.uptimeMillis();
    }
    
    public void adReceived(AppLovinAd paramAppLovinAd)
    {
      if (paramAppLovinAd != null)
      {
        AdControl.this.appLovinAd = paramAppLovinAd;
        AdControl.this.appLovinReady = true;
      }
      if (AdControl.this.testListener != null) {
        AdControl.this.testListener.requestSuccessfull(8);
      }
    }
    
    public void failedToReceiveAd(int paramInt)
    {
      if (AdControl.this.testListener != null) {
        AdControl.this.testListener.requestFailed(8);
      }
    }
    
    public void videoPlaybackBegan(AppLovinAd paramAppLovinAd) {}
    
    public void videoPlaybackEnded(AppLovinAd paramAppLovinAd, double paramDouble, boolean paramBoolean) {}
  }
  
  private class MyFlurryAdListener
    implements FlurryAdListener
  {
    private Runnable FlurryFetchTask = new Runnable()
    {
      public void run()
      {
        try
        {
          AdControl.this.handler.removeCallbacks(AdControl.MyFlurryAdListener.this.FlurryFetchTask);
          if ((AdControl.this.paused) && (!AdControl.this.noStartStop)) {}
          for (;;)
          {
            return;
            if (!AdControl.flurry) {
              continue;
            }
            try
            {
              if (AdControl.this.flurryRequested) {
                continue;
              }
              AdControl.this.flurryRequested = true;
              FlurryAds.fetchAd((Context)AdControl.this.activity, AdInfo.FlurryAdSpace, AdControl.this.activity.getViewGroup(), FlurryAdSize.FULLSCREEN);
              return;
            }
            catch (Throwable localThrowable1)
            {
              AdControl.chartboost = false;
              return;
            }
          }
        }
        catch (Throwable localThrowable2)
        {
          for (;;) {}
        }
      }
    };
    
    private MyFlurryAdListener() {}
    
    public void onAdClicked(String paramString) {}
    
    public void onAdClosed(String paramString)
    {
      try
      {
        if (AdControl.this.flurryDisplayed) {
          AdControl.this.activity.setNoStartStop();
        }
        AdControl.this.flurryDisplayed = false;
        try
        {
          if (AdControl.this.testListener == null) {
            AdControl.this.handler.postDelayed(this.FlurryFetchTask, 10000L);
          }
          AdControl.this.lastAdTime = SystemClock.uptimeMillis();
          return;
        }
        catch (Throwable paramString) {}
      }
      catch (Throwable paramString)
      {
        for (;;) {}
      }
    }
    
    public void onAdOpened(String paramString) {}
    
    public void onApplicationExit(String paramString)
    {
      try
      {
        AdControl.this.activity.setNoStartStop();
        return;
      }
      catch (Throwable paramString) {}
    }
    
    public void onRenderFailed(String paramString) {}
    
    public void onVideoCompleted(String paramString) {}
    
    public boolean shouldDisplayAd(String paramString, FlurryAdType paramFlurryAdType)
    {
      return paramFlurryAdType.equals(FlurryAdType.WEB_TAKEOVER);
    }
    
    public void spaceDidFailToReceiveAd(String paramString)
    {
      AdControl.this.flurryRequested = false;
      paramString = AdControl.this;
      paramString.flurryErrors += 1;
      if (AdControl.this.flurryErrors < 2) {}
      try
      {
        if (AdControl.this.testListener == null) {
          AdControl.this.handler.postDelayed(this.FlurryFetchTask, AdControl.this.flurryErrors * 3000);
        }
        if (AdControl.this.testListener != null) {
          AdControl.this.testListener.requestFailed(7);
        }
        return;
      }
      catch (Throwable paramString)
      {
        for (;;) {}
      }
    }
    
    public void spaceDidReceiveAd(String paramString)
    {
      AdControl.this.flurryRequested = false;
      if (AdControl.this.testListener != null) {
        AdControl.this.testListener.requestSuccessfull(7);
      }
    }
  }
}
