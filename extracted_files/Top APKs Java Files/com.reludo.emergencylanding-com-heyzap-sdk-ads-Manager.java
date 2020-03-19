package com.heyzap.sdk.ads;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import com.heyzap.internal.Logger;
import com.heyzap.internal.Utils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Manager
{
  public static final String ACTION_URL_PLACEHOLDER = "market://details?id=%s&referrer=%s";
  public static final String ACTION_URL_REFERRER = "utm_source%3Dheyzap%26utm_medium%3Dmobile%26utm_campaign%3Dheyzap_ad_network";
  public static String AD_SERVER;
  public static final int AD_UNIT_INCENTIVIZED = 2;
  public static final int AD_UNIT_INTERSTITIAL = 1;
  public static final int AD_UNIT_UNKNOWN = 0;
  public static final int AD_UNIT_VIDEO = 3;
  public static final String FIRST_RUN_KEY = "HeyzapAdsFirstRun";
  public static final String PREFERENCES_KEY = "com.heyzap.sdk.ads";
  public static Context applicationContext;
  public static HeyzapAds.OnStatusListener displayListener;
  public static final Handler handler;
  public static HeyzapAds.OnIncentiveResultListener incentiveListener;
  public static AbstractActivity lastActivity = null;
  public static long maxClickDifference = 1000L;
  private static volatile Manager ref;
  public static Boolean started;
  private int flags = 0;
  public long lastClickedTime = 0L;
  
  static
  {
    AD_SERVER = "http://ads.heyzap.com/in_game_api/ads";
    handler = new Handler(Looper.getMainLooper());
    started = Boolean.valueOf(false);
  }
  
  private Manager()
  {
    Logger.log("Heyzap Ad Manager started.");
    SharedPreferences localSharedPreferences = applicationContext.getSharedPreferences("com.heyzap.sdk.ads", 0);
    SharedPreferences.Editor localEditor = localSharedPreferences.edit();
    if (!localSharedPreferences.getBoolean("ran_once", false)) {}
    for (int i = 1;; i = 0)
    {
      if (i != 0)
      {
        Logger.log("Running first run tasks");
        AttributionManager.getInstance().doSelfInstall(applicationContext);
        localEditor.putBoolean("ran_once", true);
        localEditor.commit();
      }
      clearAndCreateFileCache();
      started = Boolean.valueOf(true);
      return;
    }
  }
  
  protected static Manager getInstance()
  {
    try
    {
      if (ref == null) {
        ref = new Manager();
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
  
  protected void clearAndCreateFileCache()
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
  
  protected void installHeyzap(AdModel paramAdModel)
  {
    if (paramAdModel.getGamePackage() == null) {}
    for (paramAdModel = "null";; paramAdModel = paramAdModel.getGamePackage())
    {
      installHeyzap(paramAdModel);
      return;
    }
  }
  
  protected void installHeyzap(String paramString)
  {
    if (Utils.heyzapIsInstalled(applicationContext))
    {
      Intent localIntent = new Intent("android.intent.action.MAIN");
      localIntent.setAction("com.heyzap.android");
      localIntent.putExtra("from_ad_for_game_package", paramString);
      localIntent.putExtra("packageName", applicationContext.getPackageName());
      localIntent.addFlags(268435456);
      if (paramString != null)
      {
        localIntent.setComponent(new ComponentName("com.heyzap.android", "com.heyzap.android.activity.GameDetails"));
        localIntent.putExtra("game_package", paramString);
      }
      for (;;)
      {
        applicationContext.startActivity(localIntent);
        return;
        localIntent.setComponent(new ComponentName("com.heyzap.android", "com.heyzap.android.activity.CheckinHub"));
      }
    }
    Utils.installHeyzap(applicationContext, String.format("action=ad_heyzap_logo&game_package=%s", new Object[] { paramString }));
  }
  
  protected Boolean isFlagEnabled(int paramInt)
  {
    if ((this.flags & paramInt) == paramInt) {}
    for (boolean bool = true;; bool = false) {
      return Boolean.valueOf(bool);
    }
  }
  
  protected void setFlags(int paramInt)
  {
    this.flags = paramInt;
  }
}
