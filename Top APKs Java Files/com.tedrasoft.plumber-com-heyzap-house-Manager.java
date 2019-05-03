package com.heyzap.house;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import com.heyzap.house.handler.AttributionHandler;
import com.heyzap.house.impl.AbstractActivity;
import com.heyzap.house.model.AdModel;
import com.heyzap.internal.Logger;
import com.heyzap.internal.Utils;
import com.heyzap.sdk.ads.HeyzapAds.OnIncentiveResultListener;
import com.heyzap.sdk.ads.HeyzapAds.OnStatusListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Manager
{
  public static final String ACTION_URL_PLACEHOLDER = "market://details?id=%s&referrer=%s";
  public static final String ACTION_URL_REFERRER = "utm_source%3Dheyzap%26utm_medium%3Dmobile%26utm_campaign%3Dheyzap_ad_network";
  public static String AD_SERVER;
  public static final int AD_UNIT_INCENTIVIZED = 2;
  public static final int AD_UNIT_INTERSTITIAL = 1;
  public static final int AD_UNIT_NATIVE = 4;
  public static final int AD_UNIT_UNKNOWN = 0;
  public static final int AD_UNIT_VIDEO = 3;
  private static HeyzapAds.OnIncentiveResultListener DEFAULT_INCENTIVE_LISTENER = new HeyzapAds.OnIncentiveResultListener()
  {
    public void onComplete(String paramAnonymousString) {}
    
    public void onIncomplete(String paramAnonymousString) {}
  };
  private static HeyzapAds.OnStatusListener DEFAULT_STATUS_LISTENER;
  public static final String FIRST_RUN_KEY = "HeyzapAdsFirstRun";
  public static final String PREFERENCES_KEY = "com.heyzap.sdk.ads";
  public static Boolean SLOW_CLOSE;
  public static Context applicationContext;
  public static final Handler handler;
  public static AbstractActivity lastActivity;
  public static long maxClickDifference = 1000L;
  private static volatile Manager ref;
  public static Boolean started;
  private Context context = null;
  private int flags = 0;
  private HeyzapAds.OnIncentiveResultListener incentiveListener = null;
  public long lastClickedTime = 0L;
  private String publisherId = null;
  private HashMap<Integer, HeyzapAds.OnStatusListener> statusListeners = new HashMap();
  
  static
  {
    SLOW_CLOSE = Boolean.valueOf(false);
    AD_SERVER = "http://ads.heyzap.com/in_game_api/ads";
    handler = new Handler(Looper.getMainLooper());
    started = Boolean.valueOf(false);
    lastActivity = null;
    DEFAULT_STATUS_LISTENER = new HeyzapAds.OnStatusListener()
    {
      public void onAudioFinished() {}
      
      public void onAudioStarted() {}
      
      public void onAvailable(String paramAnonymousString) {}
      
      public void onClick(String paramAnonymousString) {}
      
      public void onFailedToFetch(String paramAnonymousString) {}
      
      public void onFailedToShow(String paramAnonymousString) {}
      
      public void onHide(String paramAnonymousString) {}
      
      public void onShow(String paramAnonymousString) {}
    };
  }
  
  private Manager(Context paramContext, String paramString)
  {
    setPublisherId(paramString);
    setContext(paramContext);
  }
  
  public static String adUnitName(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "interstitial";
    case 2: 
      return "incentivized";
    case 3: 
      return "video";
    }
    return "native";
  }
  
  public static Manager getInstance()
  {
    try
    {
      if (ref == null) {
        throw new RuntimeException("Heyzap has not been started yet! Start Heyzap by calling HeyzapAds.start(<your-publisher-id>) in your launch Activity.");
      }
    }
    finally {}
    Manager localManager = ref;
    return localManager;
  }
  
  public static Boolean isStarted()
  {
    return started;
  }
  
  public static void start(Context paramContext, String paramString)
  {
    int i = 0;
    if (paramContext == null) {
      throw new IllegalArgumentException();
    }
    applicationContext = paramContext.getApplicationContext();
    ref = new Manager(paramContext, paramString);
    paramContext = paramContext.getSharedPreferences("com.heyzap.sdk.ads", 0);
    paramString = paramContext.edit();
    if (!paramContext.getBoolean("ran_once", false)) {
      i = 1;
    }
    if (i != 0)
    {
      Logger.log("Running first run tasks");
      AttributionHandler.getInstance().doSelfInstall(applicationContext);
      paramString.putBoolean("ran_once", true);
      paramString.commit();
    }
    getInstance().clearAndCreateFileCache();
    started = Boolean.valueOf(true);
    Logger.log("Heyzap Ad Manager started.");
  }
  
  public void clearAndCreateFileCache()
  {
    Object localObject = Utils.getCacheDirAbsolutePath(applicationContext);
    try
    {
      if (new File((String)localObject).exists()) {
        Utils.deleteDirectory(new File((String)localObject));
      }
      localObject = new File((String)localObject);
      ((File)localObject).mkdirs();
      ((File)localObject).deleteOnExit();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public Object clone()
  {
    return null;
  }
  
  public Context getContext()
  {
    return this.context;
  }
  
  public List<String> getLocalPackages()
  {
    if (applicationContext == null) {
      return null;
    }
    Object localObject = applicationContext.getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((!localPackageInfo.packageName.startsWith("android.")) && (!localPackageInfo.packageName.startsWith("com.google.android")) && (!localPackageInfo.packageName.startsWith("com.android")) && (!localPackageInfo.packageName.startsWith("com.htc")) && (!localPackageInfo.packageName.startsWith("com.samsung")) && (!localPackageInfo.packageName.startsWith("com.sec")) && (!localPackageInfo.packageName.startsWith("com.monotype")) && (!localPackageInfo.packageName.startsWith("com.verizon")) && (!localPackageInfo.packageName.startsWith("com.qualcomm")) && (!localPackageInfo.packageName.startsWith("com.vzw"))) {
        localArrayList.add(localPackageInfo.packageName);
      }
    }
    return localArrayList;
  }
  
  public HeyzapAds.OnIncentiveResultListener getOnIncentiveListener()
  {
    if (this.incentiveListener != null) {
      return this.incentiveListener;
    }
    return DEFAULT_INCENTIVE_LISTENER;
  }
  
  public HeyzapAds.OnStatusListener getOnStatusListener(Integer paramInteger)
  {
    paramInteger = (HeyzapAds.OnStatusListener)this.statusListeners.get(paramInteger);
    if (paramInteger != null) {
      return paramInteger;
    }
    return DEFAULT_STATUS_LISTENER;
  }
  
  public String getPublisherId()
  {
    return this.publisherId;
  }
  
  public void installHeyzap(AdModel paramAdModel) {}
  
  public Boolean isFlagEnabled(int paramInt)
  {
    if ((this.flags & paramInt) == paramInt) {}
    for (boolean bool = true;; bool = false) {
      return Boolean.valueOf(bool);
    }
  }
  
  public void setContext(Context paramContext)
  {
    this.context = paramContext;
  }
  
  public void setFlags(int paramInt)
  {
    this.flags = paramInt;
  }
  
  public void setOnIncentiveResultListener(HeyzapAds.OnIncentiveResultListener paramOnIncentiveResultListener)
  {
    this.incentiveListener = paramOnIncentiveResultListener;
  }
  
  public void setOnStatusListener(Integer paramInteger, HeyzapAds.OnStatusListener paramOnStatusListener)
  {
    this.statusListeners.put(paramInteger, paramOnStatusListener);
  }
  
  public void setPublisherId(String paramString)
  {
    this.publisherId = paramString;
    Utils.publisherId = this.publisherId;
  }
}
