package com.heyzap.house;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import com.heyzap.a.b.b;
import com.heyzap.a.b.b.a;
import com.heyzap.a.c.c;
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
  public static String AD_SERVER = "https://ads.heyzap.com/in_game_api/ads";
  public static final String FIRST_RUN_KEY = "HeyzapAdsFirstRun";
  public static final long MAX_CACHE_SIZE = 30000000L;
  public static Boolean SLOW_CLOSE = Boolean.valueOf(false);
  public static Context applicationContext;
  private static DisplayCache b;
  private static b c;
  private static AtomicReference<com.heyzap.a.c.j> d = new AtomicReference();
  private static volatile Manager e;
  public static final Handler handler = new Handler(Looper.getMainLooper());
  public static AbstractActivity lastActivity;
  public static long maxClickDifference = 1000L;
  public static Boolean started = Boolean.valueOf(false);
  private com.heyzap.internal.d a = null;
  public long lastClickedTime = 0L;
  
  private Manager(com.heyzap.internal.d paramD, String paramString)
  {
    setPublisherId(paramString);
    setContextRef(paramD);
    clearAndCreateImageFileCache();
    b = new DisplayCache();
    c = new b(c.a(), new File(Utils.h(paramD.a)), Long.valueOf(30000000L));
  }
  
  public static Manager getInstance()
  {
    try
    {
      if (e == null) {
        throw new RuntimeException("Heyzap has not been started yet! Start Heyzap by calling HeyzapAds.start(<your-publisher-id>) in your launch Activity.");
      }
      Manager localManager = e;
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
    com.heyzap.a.e.a.a = paramString;
    StringBuilder localStringBuilder = new StringBuilder("https://");
    localStringBuilder.append(paramString);
    localStringBuilder.append("/in_game_api/ads");
    AD_SERVER = localStringBuilder.toString();
  }
  
  public static com.heyzap.a.c.j start(com.heyzap.internal.d paramD, String paramString)
  {
    if (d.compareAndSet(null, com.heyzap.a.c.j.a()))
    {
      if (paramD.a == null) {
        throw new IllegalArgumentException();
      }
      applicationContext = paramD.a;
      e = new Manager(paramD, paramString);
      com.heyzap.internal.j.a(paramD.a);
      paramD = e.getFileCache();
      Logger.log("(CACHE) Open");
      paramString = com.heyzap.a.c.j.a();
      if (!paramD.g.get())
      {
        paramD.h = new ConcurrentHashMap();
        paramD.i.execute(new b.a(paramD, paramD, paramString));
      }
      else
      {
        paramString.a(Boolean.valueOf(true));
      }
      paramString.a(new Runnable()
      {
        public final void run()
        {
          Manager.a().getFileCache().a();
        }
      }, c.a());
      com.heyzap.a.c.d.a(paramString, (com.heyzap.a.c.j)d.get(), c.a());
      ((com.heyzap.a.c.j)d.get()).a(new Runnable()
      {
        public final void run()
        {
          Manager.started = Boolean.valueOf(true);
          Logger.log("Heyzap Ad Manager started.");
        }
      }, c.a());
    }
    return (com.heyzap.a.c.j)d.get();
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
