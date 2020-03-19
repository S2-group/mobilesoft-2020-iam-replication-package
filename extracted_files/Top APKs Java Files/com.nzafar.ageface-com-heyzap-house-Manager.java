package com.heyzap.house;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.heyzap.analytics.Event;
import com.heyzap.analytics.MetricsTracker;
import com.heyzap.house.handler.AttributionHandler;
import com.heyzap.house.impl.AbstractActivity;
import com.heyzap.house.model.AdCache;
import com.heyzap.house.model.AdModel;
import com.heyzap.house.model.AdModel.HtmlAssetFetcher;
import com.heyzap.house.model.AdModel.ModelPostFetchCompleteListener;
import com.heyzap.house.model.VideoModel;
import com.heyzap.house.model.VideoModel.Cacher;
import com.heyzap.internal.ExecutorPool;
import com.heyzap.internal.GenericCallback;
import com.heyzap.internal.Logger;
import com.heyzap.internal.Utils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class Manager
{
  public static final String ACTION_URL_PLACEHOLDER = "market://details?id=%s&referrer=%s";
  public static final String ACTION_URL_REFERRER = "utm_source%3Dheyzap%26utm_medium%3Dmobile%26utm_campaign%3Dheyzap_ad_network";
  public static String AD_SERVER;
  public static final String FIRST_RUN_KEY = "HeyzapAdsFirstRun";
  public static final long MAX_CACHE_SIZE = 30000000L;
  public static final String PREFERENCES_KEY = "com.heyzap.sdk.ads";
  public static Boolean SLOW_CLOSE;
  public static Context applicationContext;
  public static final Handler handler;
  public static AbstractActivity lastActivity = null;
  public static long maxClickDifference = 1000L;
  private static volatile Manager ref;
  public static Boolean started;
  private Context context = null;
  private int flags = 0;
  public long lastClickedTime = 0L;
  private String publisherId = null;
  public long startTime = System.currentTimeMillis();
  public HashMap<String, CacheEntry> videoCache;
  
  static
  {
    SLOW_CLOSE = Boolean.valueOf(false);
    AD_SERVER = "http://ads.heyzap.com/in_game_api/ads";
    handler = new Handler(Looper.getMainLooper());
    started = Boolean.valueOf(false);
  }
  
  private Manager(Context paramContext, String paramString)
  {
    setPublisherId(paramString);
    setContext(paramContext);
    clearAndCreateImageFileCache();
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
  
  private void initializeCache(Context paramContext)
  {
    paramContext = new File(Utils.getCachePath(paramContext, "cacheObject"));
    if (paramContext.exists())
    {
      for (;;)
      {
        try
        {
          this.videoCache = ((HashMap)new ObjectInputStream(new FileInputStream(paramContext)).readObject());
          Iterator localIterator = this.videoCache.values().iterator();
          if (!localIterator.hasNext()) {
            break;
          }
          Object localObject = (CacheEntry)localIterator.next();
          if (((CacheEntry)localObject).getLastUsedTime() == 0L)
          {
            localObject = ((CacheEntry)localObject).getModel();
            ((VideoModel)localObject).setFileCached(Boolean.valueOf(false));
            ((VideoModel)localObject).setIsReady(false);
            ((VideoModel)localObject).unCacheHtml();
            AdModel.HtmlAssetFetcher.fetch((AdModel)localObject, new GenericCallback()
            {
              public void onCallback(Object paramAnonymousObject, Throwable paramAnonymousThrowable) {}
            });
            AdCache.getInstance().put((AdModel)localObject);
            MetricsTracker.putEvent((AdModel)localObject, false).impressionCached(true);
            VideoModel.Cacher.start(applicationContext, (VideoModel)localObject, new AdModel.ModelPostFetchCompleteListener()
            {
              public void onComplete(AdModel paramAnonymousAdModel, Throwable paramAnonymousThrowable) {}
            });
          }
          else
          {
            ((CacheEntry)localObject).getModel().event = null;
          }
        }
        catch (Exception localException)
        {
          Logger.trace(localException);
          paramContext.delete();
          this.videoCache = new HashMap();
          return;
        }
      }
      cleanFileCache();
      return;
    }
    this.videoCache = new HashMap();
  }
  
  public static Boolean isStarted()
  {
    return started;
  }
  
  public static void runOnUiThread(Runnable paramRunnable)
  {
    new Handler(Looper.getMainLooper()).post(paramRunnable);
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
    MetricsTracker.sendMetrics();
    started = Boolean.valueOf(true);
    ref.initializeCache(applicationContext);
    Logger.log("Heyzap Ad Manager started.");
  }
  
  public void cleanFileCache()
  {
    final String str = Utils.getCacheDirAbsolutePath(applicationContext);
    new File(str).mkdirs();
    if (this.videoCache == null) {
      return;
    }
    ExecutorPool.getInstance().execute(new Runnable()
    {
      public void run()
      {
        for (;;)
        {
          long l;
          CacheEntry localCacheEntry;
          synchronized (Manager.this.videoCache)
          {
            Object localObject1 = Manager.this.videoCache.values().iterator();
            l = 0L;
            if (((Iterator)localObject1).hasNext())
            {
              l += new File(((CacheEntry)((Iterator)localObject1).next()).getModel().getCachedPath()).length();
              continue;
            }
            if (l <= 30000000L) {
              break label340;
            }
            Object localObject3 = new ArrayList(Manager.this.videoCache.values());
            localObject1 = new ArrayList();
            ((ArrayList)localObject1).add("cacheObject");
            Collections.sort((List)localObject3);
            localObject3 = ((ArrayList)localObject3).iterator();
            if (!((Iterator)localObject3).hasNext()) {
              break;
            }
            localCacheEntry = (CacheEntry)((Iterator)localObject3).next();
            if (localCacheEntry.getLastUsedTime() == 0L) {
              ((ArrayList)localObject1).add(new File(localCacheEntry.getModel().getCachedPath()).getName());
            }
          }
          if (l < 30000000L)
          {
            localArrayList.add(new File(localCacheEntry.getModel().getCachedPath()).getName());
          }
          else
          {
            Manager.this.videoCache.remove(localCacheEntry.getModel().getStaticUri().getPath());
            l -= new File(localCacheEntry.getModel().getCachedPath()).length();
          }
        }
        Object localObject2 = new FilenameFilter()
        {
          public boolean accept(File paramAnonymous2File, String paramAnonymous2String)
          {
            return !localArrayList.contains(paramAnonymous2String);
          }
        };
        localObject2 = new File(str).listFiles((FilenameFilter)localObject2);
        int j = localObject2.length;
        int i = 0;
        while (i < j)
        {
          localObject2[i].delete();
          i += 1;
        }
        Manager.this.saveCache();
        label340:
      }
    });
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
  
  public void saveCache()
  {
    ExecutorPool.getInstance().execute(new Runnable()
    {
      public void run()
      {
        try
        {
          synchronized (Manager.this.videoCache)
          {
            try
            {
              new ObjectOutputStream(new FileOutputStream(Utils.getCachePath(Manager.applicationContext, "cacheObject"))).writeObject(Manager.this.videoCache);
              return;
            }
            catch (FileNotFoundException localFileNotFoundException)
            {
              for (;;)
              {
                Logger.trace(localFileNotFoundException);
              }
            }
          }
        }
        catch (IOException localIOException)
        {
          for (;;)
          {
            Logger.trace(localIOException);
          }
        }
      }
    });
  }
  
  public void setContext(Context paramContext)
  {
    this.context = paramContext;
  }
  
  public void setFlags(int paramInt)
  {
    this.flags = paramInt;
  }
  
  public void setPublisherId(String paramString)
  {
    this.publisherId = paramString;
    Utils.publisherId = this.publisherId;
  }
}
