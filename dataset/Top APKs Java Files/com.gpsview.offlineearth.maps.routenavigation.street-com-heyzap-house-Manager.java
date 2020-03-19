package com.heyzap.house;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Handler;
import android.os.Looper;
import com.heyzap.common.cache.FileCache;
import com.heyzap.common.concurrency.ExecutorPool;
import com.heyzap.common.concurrency.FutureUtils;
import com.heyzap.common.concurrency.SettableFuture;
import com.heyzap.house.abstr.AbstractActivity;
import com.heyzap.house.model.AdModel;
import com.heyzap.house.request.DisplayCache;
import com.heyzap.house.request.FetchRequest;
import com.heyzap.internal.ContextReference;
import com.heyzap.internal.Logger;
import com.heyzap.internal.Utils;
import com.heyzap.sdk.ads.HeyzapAds;
import com.heyzap.sdk.ads.HeyzapAds.AdsConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Manager
{
  public static final String ACTION_URL_PLACEHOLDER = "market://details?id=%s&referrer=%s";
  public static final String ACTION_URL_REFERRER = "utm_source%3Dheyzap%26utm_medium%3Dmobile%26utm_campaign%3Dheyzap_ad_network";
  public static String AD_SERVER = "https://ads.heyzap.com/in_game_api/ads";
  public static final String FIRST_RUN_KEY = "HeyzapAdsFirstRun";
  public static final long MAX_CACHE_SIZE = 30000000L;
  public static Boolean SLOW_CLOSE = Boolean.valueOf(false);
  public static Context applicationContext;
  private static DisplayCache displayCache;
  private static FileCache fileCache;
  public static final Handler handler = new Handler(Looper.getMainLooper());
  private static AtomicReference<SettableFuture> initializationFutureRef = new AtomicReference();
  public static AbstractActivity lastActivity;
  public static long maxClickDifference = 1000L;
  private static volatile Manager ref;
  public static Boolean started = Boolean.valueOf(false);
  private ContextReference contextRef = null;
  public long lastClickedTime = 0L;
  
  private Manager(ContextReference paramContextReference, String paramString)
  {
    setPublisherId(paramString);
    setContextRef(paramContextReference);
    clearAndCreateImageFileCache();
    displayCache = new DisplayCache();
    fileCache = new FileCache(ExecutorPool.getInstance(), new File(Utils.getCacheDirAbsolutePath(paramContextReference.getApp())), Long.valueOf(30000000L));
  }
  
  public static Manager getInstance()
  {
    try
    {
      if (ref == null) {
        throw new RuntimeException("Heyzap has not been started yet! Start Heyzap by calling HeyzapAds.start(<your-publisher-id>) in your launch Activity.");
      }
      Manager localManager = ref;
      return localManager;
    }
    finally {}
  }
  
  public static Boolean isStarted()
  {
    return started;
  }
  
  public static void runOnUiThread(Runnable paramRunnable)
  {
    new Handler(Looper.getMainLooper()).post(paramRunnable);
  }
  
  public static void setAdsHost(String paramString)
  {
    FetchRequest.setDefaultHost(paramString);
    com.heyzap.common.net.APIClient.DOMAIN = paramString;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("http://");
    localStringBuilder.append(paramString);
    localStringBuilder.append("/in_game_api/ads");
    AD_SERVER = localStringBuilder.toString();
  }
  
  public static SettableFuture start(ContextReference paramContextReference, String paramString)
  {
    if (initializationFutureRef.compareAndSet(null, SettableFuture.create()))
    {
      if (paramContextReference.getApp() == null) {
        throw new IllegalArgumentException();
      }
      applicationContext = paramContextReference.getApp();
      ref = new Manager(paramContextReference, paramString);
      com.heyzap.internal.PackageManager.checkInstalledPackages(paramContextReference.getApp());
      paramContextReference = ref.getFileCache().open();
      paramContextReference.addListener(new Runnable()
      {
        public void run()
        {
          Manager.ref.getFileCache().flush();
        }
      }, ExecutorPool.getInstance());
      FutureUtils.bind(paramContextReference, (SettableFuture)initializationFutureRef.get(), ExecutorPool.getInstance());
      ((SettableFuture)initializationFutureRef.get()).addListener(new Runnable()
      {
        public void run()
        {
          Manager.started = Boolean.valueOf(true);
          Logger.log("Heyzap Ad Manager started.");
        }
      }, ExecutorPool.getInstance());
    }
    return (SettableFuture)initializationFutureRef.get();
  }
  
  public void clearAndCreateFileCache()
  {
    String str = Utils.getCacheDirAbsolutePath(applicationContext);
    try
    {
      if (new File(str).exists()) {
        Utils.deleteDirectory(new File(str));
      }
      new File(str).mkdirs();
      return;
    }
    catch (Exception localException)
    {
      Logger.trace(localException);
    }
  }
  
  public void clearAndCreateImageFileCache()
  {
    String str = Utils.getImageCacheDirAbsolutePath(applicationContext);
    try
    {
      if (new File(str).exists()) {
        Utils.deleteDirectory(new File(str));
      }
      new File(str).mkdirs();
      return;
    }
    catch (Exception localException)
    {
      Logger.trace(localException);
    }
  }
  
  public Object clone()
  {
    return null;
  }
  
  public DisplayCache getDisplayCache()
  {
    return displayCache;
  }
  
  public FileCache getFileCache()
  {
    return fileCache;
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
  
  public String getPublisherId()
  {
    return HeyzapAds.config.publisherId;
  }
  
  public void installHeyzap(AdModel paramAdModel) {}
  
  public void setContextRef(ContextReference paramContextReference)
  {
    this.contextRef = paramContextReference;
  }
  
  public void setPublisherId(String paramString)
  {
    HeyzapAds.config.publisherId = paramString;
  }
}