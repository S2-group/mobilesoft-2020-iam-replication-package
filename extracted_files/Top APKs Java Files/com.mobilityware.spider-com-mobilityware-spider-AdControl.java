package com.mobilityware.spider;

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
import com.brightroll.androidsdk.AdDelegate;
import com.brightroll.androidsdk.RTB;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.ChartboostDelegate;
import com.flurry.android.FlurryAdListener;
import com.flurry.android.FlurryAdSize;
import com.flurry.android.FlurryAdType;
import com.flurry.android.FlurryAds;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.InterstitialAd;
import com.greystripe.sdk.GSAd;
import com.greystripe.sdk.GSAdErrorCode;
import com.greystripe.sdk.GSAdListener;
import com.greystripe.sdk.GSFullscreenAd;
import com.tremorvideo.sdk.android.videoad.TremorVideo;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

public class AdControl
  implements ParamListener, AdListener, AdDelegate, GSAdListener
{
  private static final boolean ADDEBUG = false;
  private static final String ADMOB = "Admob";
  private static final int ADMOBNET = 0;
  private static final String BRIGHTROLL = "Brightroll";
  private static final int BRIGHTROLLNET = 3;
  private static final String CHARTBOOST = "Chartboost";
  private static final String FLURRY = "Flurry";
  private static final String GREYSTRIPE = "Greystripe";
  private static String GreystripeID;
  public static final String INTERNAL = "Internal";
  private static final int MAXNETS = 5;
  public static final String NOAD = "No ad";
  private static final int RHYTHMNET = 1;
  private static final String TAG = "adControl";
  private static final String TREMOR = "Tremor";
  private static final int TREMORNET = 2;
  private static final int TremorActivityResult = 888;
  private static boolean admob = false;
  private static boolean brightroll = false;
  private static boolean chartboost = false;
  private static boolean flurry = false;
  private static boolean greystripe = false;
  private static boolean rhythm;
  private static boolean tremor;
  private Runnable AdTask = new Runnable()
  {
    public void run()
    {
      try
      {
        AdControl.this.handler.removeCallbacks(AdControl.this.AdTask);
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
        AdControl.this.privateDisplayAd();
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
        if ((AdControl.this.gsAd != null) && (AdControl.greystripe)) {}
        try
        {
          AdControl.this.gsAd.fetch();
          return;
        }
        catch (Throwable localThrowable1) {}
      }
      catch (Throwable localThrowable2)
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
      AdControl.this.initPremium();
      AdControl.this.initAdNets();
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
  private MWActivity activity;
  private AdParams adParams;
  private InterstitialAd admobInterstitial;
  private int admobPercent;
  private boolean admobReady;
  private AdRequest admobRequest;
  private boolean admobRequested;
  private AudioManager audio;
  private Object audioListener;
  public String backupInternalURL;
  private com.brightroll.androidsdk.Ad brightrollAd;
  private int brightrollMaxAPILevel;
  private int brightrollPerDay;
  private int brightrollPercent;
  private boolean brightrollReady;
  private int brightrollToday;
  private Chartboost cb;
  private ChartboostDelegate chartBoostDelegate = new ChartboostDelegate()
  {
    private Runnable ChartboostFetchTask = new Runnable()
    {
      public void run()
      {
        try
        {
          AdControl.this.handler.removeCallbacks(AdControl.6.this.ChartboostFetchTask);
          if ((AdControl.this.cb != null) && (AdControl.chartboost)) {}
          try
          {
            if (!AdControl.this.chartboostRequested)
            {
              AdControl.this.chartboostRequested = true;
              AdControl.this.cb.cacheInterstitial();
            }
            return;
          }
          catch (Throwable localThrowable1)
          {
            AdControl.chartboost = false;
            return;
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
    }
    
    public void didCacheMoreApps() {}
    
    public void didClickInterstitial(String paramAnonymousString)
    {
      try
      {
        AdControl.this.activity.setNoStartStop();
        return;
      }
      catch (Throwable paramAnonymousString) {}
    }
    
    public void didClickMoreApps() {}
    
    public void didCloseInterstitial(String paramAnonymousString)
    {
      try
      {
        AdControl.this.chartboostShowing = false;
        if (!AdControl.this.chartboostRequested)
        {
          AdControl.this.chartboostRequested = true;
          AdControl.this.cb.cacheInterstitial();
        }
        return;
      }
      catch (Throwable paramAnonymousString)
      {
        AdControl.chartboost = false;
      }
    }
    
    public void didCloseMoreApps() {}
    
    public void didDismissInterstitial(String paramAnonymousString)
    {
      try
      {
        AdControl.this.chartboostShowing = false;
        if (!AdControl.this.chartboostRequested)
        {
          AdControl.this.chartboostRequested = true;
          AdControl.this.cb.cacheInterstitial();
        }
        return;
      }
      catch (Throwable paramAnonymousString)
      {
        AdControl.chartboost = false;
      }
    }
    
    public void didDismissMoreApps() {}
    
    public void didFailToLoadInterstitial(String paramAnonymousString)
    {
      AdControl.this.chartboostRequested = false;
      paramAnonymousString = AdControl.this;
      paramAnonymousString.chartboostErrors += 1;
      if (AdControl.this.chartboostErrors < 3) {}
      try
      {
        AdControl.this.handler.postDelayed(this.ChartboostFetchTask, AdControl.this.chartboostErrors * 3000);
        return;
      }
      catch (Throwable paramAnonymousString) {}
    }
    
    public void didFailToLoadMoreApps() {}
    
    public void didShowInterstitial(String paramAnonymousString) {}
    
    public void didShowMoreApps() {}
    
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
    
    public boolean shouldDisplayLoadingViewForMoreApps()
    {
      return true;
    }
    
    public boolean shouldDisplayMoreApps()
    {
      return false;
    }
    
    public boolean shouldRequestInterstitial(String paramAnonymousString)
    {
      return true;
    }
    
    public boolean shouldRequestInterstitialsInFirstSession()
    {
      return true;
    }
    
    public boolean shouldRequestMoreApps()
    {
      return false;
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
  private long lastAdTime;
  private boolean localMusic;
  private long maxTime = 300000L;
  private long minTime;
  private boolean musicPaused;
  private boolean needGSFetch;
  private boolean paused;
  private int[] percent;
  private int premiumErrors;
  private int prevVolume;
  private Random rand;
  private int rhythmPercent;
  private AdControl self = this;
  private int totPercent;
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
  
  public AdControl(MWActivity paramMWActivity, AdParams paramAdParams)
  {
    this.activity = paramMWActivity;
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
        initPremium();
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
  }
  
  private void debugSetup1() {}
  
  private boolean displayChartboost()
  {
    if (this.chartboostPercent < 1) {}
    for (;;)
    {
      return false;
      if ((this.rand != null) && (this.rand.nextInt(100) <= this.chartboostPercent)) {
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
    }
    return false;
  }
  
  private boolean displayFlurry()
  {
    if ((this.rand == null) || (!flurry)) {
      return false;
    }
    if (this.rand.nextInt(100) > this.flurryPercent) {
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
      ((Activity)this.activity).setContentView(2130903045);
      ImageButton localImageButton = (ImageButton)((Activity)this.activity).findViewById(2131361832);
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
  
  private void getAdmob()
  {
    if ((this.paused) || (this.admobReady) || (this.admobRequested) || (this.admobInterstitial == null)) {
      return;
    }
    this.admobRequested = true;
    try
    {
      this.admobRequest = new AdRequest();
      this.admobInterstitial.loadAd(this.admobRequest);
      return;
    }
    catch (Throwable localThrowable)
    {
      admob = false;
      this.admobRequested = false;
      this.admobInterstitial = null;
    }
  }
  
  private void initAdNets()
  {
    this.premiumErrors = 0;
    if ((admob) && (this.admobInterstitial == null)) {}
    try
    {
      this.admobInterstitial = new InterstitialAd((Activity)this.activity, AdInfo.AdMobID);
      this.admobInterstitial.setAdListener(this);
    }
    catch (Throwable localThrowable4)
    {
      try
      {
        if ((greystripe) && (this.gsAd == null))
        {
          this.gsErrors = 0;
          this.gsAd = new GSFullscreenAd((Activity)this.activity, GreystripeID);
          this.gsAd.addListener(this);
          this.gsAd.fetch();
        }
        if ((tremor) && (!this.tremorStarted)) {
          this.tremorStarted = true;
        }
      }
      catch (Throwable localThrowable4)
      {
        try
        {
          TremorVideo.initialize((Activity)this.activity, AdInfo.TremorIDs);
          TremorVideo.start();
          if (!chartboost) {}
        }
        catch (Throwable localThrowable4)
        {
          try
          {
            this.chartboostErrors = 0;
            this.cb = Chartboost.sharedChartboost();
            this.cb.onCreate((Activity)this.activity, AdInfo.ChartboostAppID, AdInfo.ChartboostAppSignature, this.chartBoostDelegate);
            this.cb.startSession();
            Chartboost.sharedChartboost().onStart((Activity)this.activity);
            this.chartboostRequested = true;
            this.cb.cacheInterstitial();
            if (!flurry) {}
          }
          catch (Throwable localThrowable4)
          {
            try
            {
              for (;;)
              {
                this.flurryErrors = 0;
                FlurryAds.initializeAds((Context)this.activity);
                FlurryAds.setAdListener(new MyFlurryAdListener(null));
                this.flurryRequested = true;
                FlurryAds.fetchAd((Context)this.activity, AdInfo.FlurryAdSpace, this.activity.getViewGroup(), FlurryAdSize.FULLSCREEN);
                if ((admob) || (rhythm) || (brightroll)) {
                  this.handler.postDelayed(this.AdTask, 2000L);
                }
                return;
                localThrowable1 = localThrowable1;
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
              }
              localThrowable4 = localThrowable4;
              chartboost = false;
            }
            catch (Throwable localThrowable5)
            {
              for (;;)
              {
                flurry = false;
              }
            }
          }
        }
      }
    }
  }
  
  private void initPremium()
  {
    this.rand = new Random();
    this.totPercent = 0;
    this.percent = new int[5];
    int i = 0;
    if (i >= 5)
    {
      if (admob)
      {
        if (this.admobPercent > 0) {
          break label113;
        }
        admob = false;
      }
      label47:
      if (rhythm)
      {
        if (this.rhythmPercent > 0) {
          break label139;
        }
        rhythm = false;
      }
      label64:
      if (tremor)
      {
        if (this.tremorPercent > 0) {
          break label165;
        }
        tremor = false;
      }
    }
    for (;;)
    {
      if (brightroll)
      {
        if (this.brightrollPercent > 0) {
          break label191;
        }
        brightroll = false;
      }
      return;
      this.percent[i] = -1;
      i += 1;
      break;
      label113:
      this.totPercent += this.admobPercent;
      this.percent[0] = this.totPercent;
      break label47;
      label139:
      this.totPercent += this.rhythmPercent;
      this.percent[1] = this.totPercent;
      break label64;
      label165:
      this.totPercent += this.tremorPercent;
      this.percent[2] = this.totPercent;
    }
    label191:
    this.totPercent += this.brightrollPercent;
    this.percent[3] = this.totPercent;
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
  
  private void privateDisplayAd()
  {
    this.lastAdTime = SystemClock.uptimeMillis();
    if (this.admobReady)
    {
      this.admobReady = false;
      this.dismissed = true;
      this.activity.setNoStartStop();
    }
    label430:
    for (;;)
    {
      try
      {
        this.admobInterstitial.show();
        addLastAdNet("Admob");
        silence();
        return;
      }
      catch (Throwable localThrowable1)
      {
        admob = false;
        this.admobInterstitial = null;
        return;
      }
      if ((this.brightrollReady) && (this.brightrollAd != null))
      {
        brightrollShown();
        this.brightrollReady = false;
        this.dismissed = true;
        this.activity.setNoStartStop();
        this.brightrollAd.show();
        addLastAdNet("Brightroll");
        silence();
        this.brightrollAd = null;
        return;
      }
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
            return;
          }
        }
        catch (Throwable localThrowable2)
        {
          Log.e("Tremor", "Exception:" + localThrowable2.getMessage());
          tremor = false;
          return;
        }
        if ((AdInfo.TremorIDs.length > 1) && (TremorVideo.showAd((Activity)this.activity, AdInfo.TremorIDs[1], 888)))
        {
          addLastAdNet("Tremor");
          silence();
          this.tryTremor = false;
          this.tremorPrev = true;
          this.dismissed = true;
          this.activity.setNoStartStop();
          return;
        }
        if (this.tryTremor)
        {
          this.tryTremor = false;
          this.handler.postDelayed(this.AdTask, 30000L);
        }
      }
      for (;;)
      {
        if ((displayChartboost()) || (displayFlurry())) {
          break label430;
        }
        try
        {
          if ((greystripe) && (this.gsAd != null) && (this.gsAd.isAdReady()))
          {
            this.gsAd.display();
            addLastAdNet("Greystripe " + this.gsAd.getId());
            silence();
            this.dismissed = true;
            this.activity.setNoStartStop();
            return;
          }
        }
        catch (Throwable localThrowable3)
        {
          greystripe = false;
        }
        if (!displayInternalAd()) {
          break;
        }
        return;
        this.tremorPrev = false;
      }
    }
  }
  
  private void requestPremium()
  {
    if (this.totPercent < 1) {
      break label8;
    }
    label8:
    label218:
    for (;;)
    {
      return;
      if ((!this.admobReady) && (!this.brightrollReady))
      {
        int j = this.rand.nextInt(this.totPercent);
        int i = 0;
        for (;;)
        {
          if (i >= 5) {
            break label218;
          }
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
              catch (Throwable localThrowable1)
              {
                return;
              }
            case 2: 
              this.tryTremor = true;
              return;
            }
            if (this.brightrollAd != null) {
              break;
            }
            try
            {
              this.brightrollAd = new com.brightroll.androidsdk.Ad();
              this.brightrollAd.setDelegate(this);
              this.brightrollAd.setSitePlacementId(AdInfo.BrightrollID);
              this.brightrollAd.setHashesUserId(true);
              RTB localRTB = this.brightrollAd.getRTB();
              localRTB.setAppUrl("https://play.google.com/store/apps/details?id=com.mobilityware.spider");
              localRTB.setAppName("Spider");
              localRTB.setHasPrivacyPolicy(true);
              localRTB.setPaid(false);
              localRTB.setAppCategories(new String[] { "Card Games", "Video & Computer Games" });
              this.brightrollAd.fetch();
              return;
            }
            catch (Throwable localThrowable2)
            {
              return;
            }
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
    //   2: getfield 873	com/mobilityware/spider/AdControl:audio	Landroid/media/AudioManager;
    //   5: iconst_3
    //   6: invokevirtual 917	android/media/AudioManager:getStreamVolume	(I)I
    //   9: putfield 910	com/mobilityware/spider/AdControl:prevVolume	I
    //   12: aload_0
    //   13: getfield 873	com/mobilityware/spider/AdControl:audio	Landroid/media/AudioManager;
    //   16: ifnull +147 -> 163
    //   19: aload_0
    //   20: getfield 873	com/mobilityware/spider/AdControl:audio	Landroid/media/AudioManager;
    //   23: invokevirtual 878	android/media/AudioManager:isMusicActive	()Z
    //   26: ifeq +137 -> 163
    //   29: aload_0
    //   30: getfield 248	com/mobilityware/spider/AdControl:lastAdNetOld	Ljava/lang/String;
    //   33: ldc 73
    //   35: if_acmpeq +12 -> 47
    //   38: aload_0
    //   39: getfield 248	com/mobilityware/spider/AdControl:lastAdNetOld	Ljava/lang/String;
    //   42: ldc 45
    //   44: if_acmpne +119 -> 163
    //   47: getstatic 869	android/os/Build$VERSION:SDK_INT	I
    //   50: bipush 8
    //   52: if_icmplt +111 -> 163
    //   55: aload_0
    //   56: iconst_0
    //   57: putfield 871	com/mobilityware/spider/AdControl:localMusic	Z
    //   60: aload_0
    //   61: iconst_0
    //   62: putfield 864	com/mobilityware/spider/AdControl:musicPaused	Z
    //   65: new 880	android/content/Intent
    //   68: dup
    //   69: ldc_w 882
    //   72: invokespecial 883	android/content/Intent:<init>	(Ljava/lang/String;)V
    //   75: astore_2
    //   76: aload_2
    //   77: ldc_w 885
    //   80: ldc_w 919
    //   83: invokevirtual 891	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   86: pop
    //   87: aload_0
    //   88: getfield 228	com/mobilityware/spider/AdControl:activity	Lcom/mobilityware/spider/MWActivity;
    //   91: aload_2
    //   92: iconst_0
    //   93: invokeinterface 895 3 0
    //   98: ldc2_w 920
    //   101: invokestatic 927	java/lang/Thread:sleep	(J)V
    //   104: aload_0
    //   105: getfield 873	com/mobilityware/spider/AdControl:audio	Landroid/media/AudioManager;
    //   108: invokevirtual 878	android/media/AudioManager:isMusicActive	()Z
    //   111: ifne +14 -> 125
    //   114: aload_0
    //   115: iconst_1
    //   116: putfield 871	com/mobilityware/spider/AdControl:localMusic	Z
    //   119: aload_0
    //   120: iconst_1
    //   121: putfield 864	com/mobilityware/spider/AdControl:musicPaused	Z
    //   124: return
    //   125: aload_0
    //   126: new 28	com/mobilityware/spider/AdControl$7
    //   129: dup
    //   130: aload_0
    //   131: invokespecial 928	com/mobilityware/spider/AdControl$7:<init>	(Lcom/mobilityware/spider/AdControl;)V
    //   134: putfield 897	com/mobilityware/spider/AdControl:audioListener	Ljava/lang/Object;
    //   137: aload_0
    //   138: getfield 873	com/mobilityware/spider/AdControl:audio	Landroid/media/AudioManager;
    //   141: aload_0
    //   142: getfield 897	com/mobilityware/spider/AdControl:audioListener	Ljava/lang/Object;
    //   145: checkcast 899	android/media/AudioManager$OnAudioFocusChangeListener
    //   148: iconst_3
    //   149: iconst_3
    //   150: invokevirtual 932	android/media/AudioManager:requestAudioFocus	(Landroid/media/AudioManager$OnAudioFocusChangeListener;II)I
    //   153: iconst_1
    //   154: if_icmpne +57 -> 211
    //   157: aload_0
    //   158: iconst_1
    //   159: putfield 864	com/mobilityware/spider/AdControl:musicPaused	Z
    //   162: return
    //   163: aload_0
    //   164: getfield 228	com/mobilityware/spider/AdControl:activity	Lcom/mobilityware/spider/MWActivity;
    //   167: invokeinterface 908 1 0
    //   172: ifne +39 -> 211
    //   175: aload_0
    //   176: getfield 873	com/mobilityware/spider/AdControl:audio	Landroid/media/AudioManager;
    //   179: invokevirtual 878	android/media/AudioManager:isMusicActive	()Z
    //   182: istore_1
    //   183: iload_1
    //   184: ifne +27 -> 211
    //   187: aload_0
    //   188: getfield 873	com/mobilityware/spider/AdControl:audio	Landroid/media/AudioManager;
    //   191: iconst_3
    //   192: iconst_0
    //   193: iconst_0
    //   194: invokevirtual 914	android/media/AudioManager:setStreamVolume	(III)V
    //   197: aload_0
    //   198: iconst_1
    //   199: putfield 905	com/mobilityware/spider/AdControl:volumeSilenced	Z
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
  
  public void adDismissed(com.brightroll.androidsdk.Ad paramAd)
  {
    restoreVolume();
  }
  
  public void adFetchFailed(com.brightroll.androidsdk.Ad paramAd)
  {
    this.brightrollReady = false;
    this.brightrollAd = null;
    this.premiumErrors += 1;
    if (this.premiumErrors > 15) {}
    while (this.paused) {
      return;
    }
    this.handler.postDelayed(this.AdTask, 60000L);
  }
  
  public void adFetched(com.brightroll.androidsdk.Ad paramAd)
  {
    this.brightrollReady = true;
    this.brightrollAd = paramAd;
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
    long l = SystemClock.uptimeMillis();
    if (l - this.lastAdTime < this.minTime) {
      return false;
    }
    this.lastAdTime = l;
    this.handler.postDelayed(this.DisplayTask, 250L);
    this.eventCount = 0;
    return true;
  }
  
  public Activity getAdActivity()
  {
    return (Activity)this.activity;
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
      this.handler.postDelayed(this.GSFetchTask, 10000L);
      return;
    }
    catch (Throwable paramGSAd) {}
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
  
  public void onDismissScreen(com.google.ads.Ad paramAd)
  {
    if (!this.paused) {
      this.handler.postDelayed(this.AdTask, 60000L);
    }
    for (;;)
    {
      restoreVolume();
      this.lastAdTime = SystemClock.uptimeMillis();
      return;
      this.dismissed = true;
    }
  }
  
  public void onFailedToFetchAd(GSAd paramGSAd, GSAdErrorCode paramGSAdErrorCode)
  {
    this.gsErrors += 1;
    if ((this.gsErrors < 25) && (this.gsAd != null)) {}
    try
    {
      if (!this.paused) {
        this.handler.postDelayed(this.GSFetchTask, this.gsErrors * 3000);
      }
      return;
    }
    catch (Throwable paramGSAd) {}
    greystripe = false;
    this.gsAd = null;
    return;
  }
  
  public void onFailedToReceiveAd(com.google.ads.Ad paramAd, AdRequest.ErrorCode paramErrorCode)
  {
    this.admobRequested = false;
    this.premiumErrors += 1;
    if (this.premiumErrors > 15) {}
    while (this.paused) {
      return;
    }
    this.handler.postDelayed(this.AdTask, 60000L);
  }
  
  public void onFetchedAd(GSAd paramGSAd)
  {
    this.gsErrors = 0;
  }
  
  public void onLeaveApplication(com.google.ads.Ad paramAd) {}
  
  public void onPresentScreen(com.google.ads.Ad paramAd) {}
  
  public void onReceiveAd(com.google.ads.Ad paramAd)
  {
    if (paramAd != this.admobInterstitial) {
      Log.e("adControl", "Not the ad we are looking for");
    }
    this.admobReady = true;
    this.admobRequested = false;
  }
  
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
        GreystripeID = "15cb5d4a-700a-4c8b-bdeb-4ec1cbc7b40a";
        if ((GreystripeID == null) || (GreystripeID.length() == 0)) {
          greystripe = false;
        }
        if (!bool) {
          break label835;
        }
        admob = this.adParams.getBool("AdmobA", false);
        localObject = this.adParams.getString("AdMobID", "");
        if ((localObject != null) && (((String)localObject).length() > 0)) {
          AdInfo.AdMobID = (String)localObject;
        }
        if (AdInfo.AdMobID.length() == 0) {
          admob = false;
        }
        if (!bool) {
          break label851;
        }
        tremor = this.adParams.getBool("Tremor2A", false);
        if ((AdInfo.TremorIDs == null) || (AdInfo.TremorIDs[0].length() == 0)) {
          tremor = false;
        }
        if (!bool) {
          break label867;
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
          break label884;
        }
        brightroll = this.adParams.getBool("BrightrollA2", false);
        if (AdInfo.BrightrollID == 0) {
          brightroll = false;
        }
        this.brightrollMaxAPILevel = this.adParams.getInt("BrightrollMaxAPILevel", 99);
        if ((!bool) && (Build.VERSION.SDK_INT >= 9) && (this.activity.isPlayStorePresent())) {
          break label901;
        }
        this.chartboostPercent = 0;
        if (this.chartboostPercent <= 0) {
          break label919;
        }
        chartboost = true;
        if ((AdInfo.ChartboostAppID.length() == 0) || (AdInfo.ChartboostAppSignature.length() == 0))
        {
          chartboost = false;
          this.chartboostPercent = 0;
        }
        if (!bool) {
          break label926;
        }
        this.flurryPercent = 0;
        this.flurryPercent = 0;
        if (this.flurryPercent <= 0) {
          break label944;
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
        i = this.adParams.getInt("IA", 2);
        if (i != this.IA)
        {
          this.internalAdNbr = 0;
          this.internalAdCount = 0;
          this.IA = i;
        }
        if (!bool) {
          break label951;
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
        }
        if (this.tv)
        {
          brightroll = false;
          admob = false;
          chartboost = false;
          flurry = false;
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
        }
      }
      try
      {
        localObject = ((Activity)this.activity).getSharedPreferences("MWCrash", 0);
        if (!bool) {
          break label972;
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
        boolean bool = false;
        continue;
        greystripe = this.adParams.getBool("Greystripe3", false);
        GreystripeID = "3874ab96-7168-4c74-a39d-54a0665fa5db";
        continue;
        label835:
        admob = this.adParams.getBool("Admob", false);
        continue;
        label851:
        tremor = this.adParams.getBool("Tremor", false);
        continue;
        label867:
        rhythm = this.adParams.getBool("Rhythm", false);
        continue;
        label884:
        brightroll = this.adParams.getBool("Brightroll2", false);
        continue;
        label901:
        this.chartboostPercent = this.adParams.getInt("Chartboost2Percent", 0);
        continue;
        label919:
        chartboost = false;
        continue;
        label926:
        this.flurryPercent = this.adParams.getInt("Flurry2Percent", 0);
        continue;
        label944:
        flurry = false;
        continue;
        label951:
        AdInfo.PrimaryInternalAdURL = AdInfo.Primary1InternalAdURL;
        AdInfo.BackupInternalAdURL = AdInfo.Backup1InternalAdURL;
        AdInfo.InternalAdImageID = AdInfo.Internal1AdImageID;
        continue;
        label972:
        int i = this.adParams.getInt("CV", 0);
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
      if (admob) {
        this.admobInterstitial = null;
      }
      if (greystripe) {
        this.gsAd = null;
      }
      if (tremor) {
        this.tremorStarted = false;
      }
      if (!brightroll) {}
    }
    try
    {
      RTB.init((Activity)this.activity);
      initAdNets();
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        localThrowable.printStackTrace();
        brightroll = false;
      }
    }
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
        LinearLayout localLinearLayout = (LinearLayout)((Activity)this.activity).findViewById(2131361792);
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
          if (AdControl.flurry) {}
          try
          {
            if (!AdControl.this.flurryRequested)
            {
              AdControl.this.flurryRequested = true;
              FlurryAds.fetchAd((Context)AdControl.this.activity, AdInfo.FlurryAdSpace, AdControl.this.activity.getViewGroup(), FlurryAdSize.FULLSCREEN);
            }
            return;
          }
          catch (Throwable localThrowable1)
          {
            AdControl.chartboost = false;
            return;
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
          AdControl.this.handler.postDelayed(this.FlurryFetchTask, 3000L);
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
      if (AdControl.this.flurryErrors < 3) {}
      try
      {
        AdControl.this.handler.postDelayed(this.FlurryFetchTask, AdControl.this.flurryErrors * 3000);
        return;
      }
      catch (Throwable paramString) {}
    }
    
    public void spaceDidReceiveAd(String paramString)
    {
      AdControl.this.flurryRequested = false;
    }
  }
}
