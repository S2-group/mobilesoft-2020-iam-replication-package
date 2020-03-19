package com.heyzap.house;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import com.heyzap.common.b.b;
import com.heyzap.common.b.b.a;
import com.heyzap.common.c.e;
import com.heyzap.house.abstr.AbstractActivity;
import com.heyzap.house.model.AdModel;
import com.heyzap.house.request.DisplayCache;
import com.heyzap.house.request.FetchRequest;
import com.heyzap.internal.Logger;
import com.heyzap.internal.Utils;
import com.heyzap.sdk.ads.HeyzapAds;
import com.heyzap.sdk.ads.HeyzapAds.AdsConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class Manager
{
  public static final String ACTION_URL_PLACEHOLDER = "market://details?id=%s&referrer=%s";
  public static final String ACTION_URL_REFERRER = "utm_source%3Dheyzap%26utm_medium%3Dmobile%26utm_campaign%3Dheyzap_ad_network";
  public static String AD_SERVER;
  public static final String FIRST_RUN_KEY = "HeyzapAdsFirstRun";
  public static final long MAX_CACHE_SIZE = 30000000L;
  public static Boolean SLOW_CLOSE;
  public static Context applicationContext;
  private static DisplayCache b;
  private static b c;
  private static AtomicReference<com.heyzap.common.c.k> d = new AtomicReference();
  private static volatile Manager e;
  public static final Handler handler;
  public static AbstractActivity lastActivity;
  public static long maxClickDifference = 1000L;
  public static Boolean started;
  private com.heyzap.internal.d a = null;
  public long lastClickedTime = 0L;
  
  static
  {
    SLOW_CLOSE = Boolean.valueOf(false);
    AD_SERVER = "https://ads.heyzap.com/in_game_api/ads";
    handler = new Handler(Looper.getMainLooper());
    started = Boolean.valueOf(false);
    lastActivity = null;
  }
  
  private Manager(com.heyzap.internal.d paramD, String paramString)
  {
    setPublisherId(paramString);
    setContextRef(paramD);
    clearAndCreateImageFileCache();
    b = new DisplayCache();
    c = new b(com.heyzap.common.c.d.a(), new File(Utils.h(paramD.a)), Long.valueOf(30000000L));
  }
  
  public static Manager getInstance()
  {
    try
    {
      if (e == null) {
        throw new RuntimeException("Heyzap has not been started yet! Start Heyzap by calling HeyzapAds.start(<your-publisher-id>) in your launch Activity.");
      }
    }
    finally {}
    Manager localManager = e;
    return localManager;
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
    com.heyzap.common.d.a.a = paramString;
    AD_SERVER = "https://" + paramString + "/in_game_api/ads";
  }
  
  public static com.heyzap.common.c.k start(com.heyzap.internal.d paramD, String paramString)
  {
    if (d.compareAndSet(null, com.heyzap.common.c.k.a()))
    {
      if (paramD.a == null) {
        throw new IllegalArgumentException();
      }
      applicationContext = paramD.a;
      e = new Manager(paramD, paramString);
      com.heyzap.internal.k.a(paramD.a);
      paramD = e.getFileCache();
      Logger.log("(CACHE) Open");
      paramString = com.heyzap.common.c.k.a();
      if (paramD.g.get()) {
        break label172;
      }
      paramD.h = new ConcurrentHashMap();
      paramD.i.execute(new b.a(paramD, paramD, paramString));
    }
    for (;;)
    {
      paramString.a(new Runnable()
      {
        public final void run()
        {
          Manager.a().getFileCache().a();
        }
      }, com.heyzap.common.c.d.a());
      e.a(paramString, (com.heyzap.common.c.k)d.get(), com.heyzap.common.c.d.a());
      ((com.heyzap.common.c.k)d.get()).a(new Runnable()
      {
        public final void run()
        {
          Manager.started = Boolean.valueOf(true);
          Logger.log("Heyzap Ad Manager started.");
        }
      }, com.heyzap.common.c.d.a());
      return (com.heyzap.common.c.k)d.get();
      label172:
      paramString.a(Boolean.valueOf(true));
    }
  }
  
  public void clearAndCreateFileCache()
  {
    String str = Utils.h(applicationContext);
    try
    {
      if (new File(str).exists()) {
        Utils.a(new File(str));
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
    String str = Utils.i(applicationContext);
    try
    {
      if (new File(str).exists()) {
        Utils.a(new File(str));
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
    return b;
  }
  
  public b getFileCache()
  {
    return c;
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
  
  public void setContextRef(com.heyzap.internal.d paramD)
  {
    this.a = paramD;
  }
  
  public void setPublisherId(String paramString)
  {
    HeyzapAds.config.publisherId = paramString;
  }
}
