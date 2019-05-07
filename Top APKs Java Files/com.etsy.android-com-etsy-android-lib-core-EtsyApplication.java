package com.etsy.android.lib.core;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatDelegate;
import androidx.work.k;
import com.etsy.android.lib.logger.d;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.util.Iterator;
import java.util.List;

public abstract class EtsyApplication
  extends MultiDexApplication
  implements com.etsy.android.lib.logger.elk.uploading.a.a
{
  public static final String ACTION_INSTALL_STATE_DISCOVERED = "com.etsy.android.install.state.discovered";
  public static final String BOE_NAME = "EtsyInc";
  public static final String BOE_PACKAGE_NAME = "com.etsy.android";
  private static final int INSTALLED = 2;
  public static final String PACKAGE_PREFIX = "package:";
  public static final String SOE_NAME = "SellOnEtsy";
  public static final String SOE_PACKAGE_NAME = "com.etsy.android.soe";
  private static final int UNINSTALLED = 1;
  private static final int UNKNOWN = 0;
  private static EtsyApplication sInstance;
  private static final Object sLock = new Object();
  protected com.etsy.android.lib.logger.analytics.i analyticsUploader;
  private com.jenzz.appstate.b appStateMonitor;
  protected Exception googlePlayException = null;
  private int mBOEInstalled = 0;
  private String mBOEInstalledVersion = "";
  private Intent mBOELaunchIntent = null;
  private boolean mLaunchedForUI = false;
  private int mSOEInstalled = 0;
  private String mSOEInstalledVersion = "";
  private Intent mSOELaunchIntent = null;
  @NonNull
  private final com.etsy.android.lib.logger.b mTracker = new com.etsy.android.lib.logger.b("app");
  
  static
  {
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
  }
  
  public EtsyApplication() {}
  
  public static EtsyApplication get()
  {
    synchronized (sLock)
    {
      if (sInstance == null) {
        throw new IllegalStateException("Context was not initialized in onCreate() of the Application base class");
      }
      EtsyApplication localEtsyApplication = sInstance;
      return localEtsyApplication;
    }
  }
  
  public static Intent getBOEDownloadIntent()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(com.etsy.android.lib.config.a.a().d().b(com.etsy.android.lib.config.b.bV)));
    return localIntent;
  }
  
  public static Intent getSOEDownloadIntent()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse(com.etsy.android.lib.config.a.a().d().b(com.etsy.android.lib.config.b.bW)));
    return localIntent;
  }
  
  public static String getSOEName()
  {
    return "SellOnEtsy";
  }
  
  @VisibleForTesting
  public static void initializeContext(EtsyApplication paramEtsyApplication)
  {
    sInstance = paramEtsyApplication;
  }
  
  @NonNull
  public com.etsy.android.lib.logger.b getAnalyticsTracker()
  {
    return this.mTracker;
  }
  
  public Intent getBOELaunchIntent()
  {
    isBOEInstalled();
    return this.mBOELaunchIntent;
  }
  
  public abstract String getConvoAuthority();
  
  public abstract Class<? extends FragmentActivity> getDeepLinkRoutingActivity();
  
  public abstract String getFileProviderAuthority();
  
  public String getSOEInstalledVersion()
  {
    return this.mSOEInstalledVersion;
  }
  
  public Intent getSOELaunchIntent()
  {
    isSOEInstalled();
    return this.mSOELaunchIntent;
  }
  
  public String getVersionName()
  {
    try
    {
      String str = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
    d.c("Package name not found");
    return "";
  }
  
  public void initAppStateListener()
  {
    this.appStateMonitor = com.jenzz.appstate.c.a(this);
    this.appStateMonitor.a(new com.jenzz.appstate.a()
    {
      public void a()
      {
        d.c("App is foregrounded");
        EtsyApplication.this.getAnalyticsTracker().a("became_active", null);
      }
      
      public void b()
      {
        d.c("App is backgrounded");
        EtsyApplication.this.getAnalyticsTracker().a("entered_background", null);
        EtsyApplication.this.analyticsUploader.b();
      }
    });
    this.appStateMonitor.a();
    registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks()
    {
      public void onActivityCreated(Activity paramAnonymousActivity, Bundle paramAnonymousBundle) {}
      
      public void onActivityDestroyed(Activity paramAnonymousActivity) {}
      
      public void onActivityPaused(Activity paramAnonymousActivity) {}
      
      public void onActivityResumed(Activity paramAnonymousActivity) {}
      
      public void onActivitySaveInstanceState(Activity paramAnonymousActivity, Bundle paramAnonymousBundle) {}
      
      public void onActivityStarted(Activity paramAnonymousActivity)
      {
        EtsyApplication.this.analyticsUploader.b();
      }
      
      public void onActivityStopped(Activity paramAnonymousActivity) {}
    });
  }
  
  public boolean isAppInBackground()
  {
    if (this.appStateMonitor == null) {
      return com.etsy.android.lib.logger.i.c();
    }
    return this.appStateMonitor.b();
  }
  
  public boolean isBOEInstalled()
  {
    if (this.mBOEInstalled == 0) {
      new a().execute(new String[] { "com.etsy.android" });
    }
    return this.mBOEInstalled == 2;
  }
  
  public boolean isLaunchedForUI()
  {
    return this.mLaunchedForUI;
  }
  
  protected boolean isPackageInstalled(String paramString)
  {
    Iterator localIterator = getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (localPackageInfo.packageName.startsWith(paramString))
      {
        if (paramString.equals("com.etsy.android.soe"))
        {
          this.mSOEInstalledVersion = localPackageInfo.versionName;
          this.mSOELaunchIntent = getPackageManager().getLaunchIntentForPackage(localPackageInfo.packageName);
        }
        if (paramString.equals("com.etsy.android"))
        {
          this.mBOEInstalledVersion = localPackageInfo.versionName;
          this.mBOELaunchIntent = getPackageManager().getLaunchIntentForPackage(localPackageInfo.packageName);
        }
        return true;
      }
    }
    return false;
  }
  
  public boolean isSOE()
  {
    return getPackageName().startsWith("com.etsy.android.soe");
  }
  
  public boolean isSOEInstalled()
  {
    if (this.mSOEInstalled == 0) {
      new a().execute(new String[] { "com.etsy.android.soe" });
    }
    return this.mSOEInstalled == 2;
  }
  
  public void onCreate()
  {
    super.onCreate();
    try
    {
      com.google.android.gms.c.a.a(this);
    }
    catch (GooglePlayServicesNotAvailableException localGooglePlayServicesNotAvailableException)
    {
      this.googlePlayException = localGooglePlayServicesNotAvailableException;
      localGooglePlayServicesNotAvailableException.printStackTrace();
    }
    catch (GooglePlayServicesRepairableException localGooglePlayServicesRepairableException)
    {
      this.googlePlayException = localGooglePlayServicesRepairableException;
      localGooglePlayServicesRepairableException.printStackTrace();
    }
    k.a(this, new androidx.work.a.a().a());
    synchronized (sLock)
    {
      sInstance = this;
      return;
    }
  }
  
  public void resetInstallStates()
  {
    this.mBOEInstalled = 0;
    this.mSOEInstalled = 0;
    this.mBOELaunchIntent = null;
    this.mSOELaunchIntent = null;
  }
  
  public void setLaunchedForUI(boolean paramBoolean)
  {
    this.mLaunchedForUI = paramBoolean;
  }
  
  public boolean shouldUseNewMonitor()
  {
    return this.appStateMonitor != null;
  }
  
  public void updateInstallStates(String paramString1, String paramString2)
  {
    resetInstallStates();
    String str = paramString2.replace("package:", "");
    if (paramString2.contains("com.etsy.android.soe"))
    {
      if (paramString1.equals("android.intent.action.PACKAGE_ADDED"))
      {
        this.mSOEInstalled = 2;
        this.mSOELaunchIntent = getPackageManager().getLaunchIntentForPackage(str);
      }
      else if (paramString1.equals("android.intent.action.PACKAGE_REMOVED"))
      {
        this.mSOEInstalled = 1;
        this.mSOELaunchIntent = null;
      }
      LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("com.etsy.android.install.state.discovered"));
      return;
    }
    if (paramString2.contains("com.etsy.android"))
    {
      if (paramString1.equals("android.intent.action.PACKAGE_ADDED"))
      {
        this.mBOEInstalled = 2;
        this.mBOELaunchIntent = getPackageManager().getLaunchIntentForPackage(str);
      }
      else if (paramString1.equals("android.intent.action.PACKAGE_REMOVED"))
      {
        this.mBOEInstalled = 1;
        this.mBOELaunchIntent = null;
      }
      LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("com.etsy.android.install.state.discovered"));
    }
  }
  
  class a
    extends AsyncTask<String, Void, Void>
  {
    a() {}
    
    protected Void a(String... paramVarArgs)
    {
      if (paramVarArgs.length > 0)
      {
        paramVarArgs = paramVarArgs[0];
        int i;
        if (EtsyApplication.this.isPackageInstalled(paramVarArgs)) {
          i = 2;
        } else {
          i = 1;
        }
        if (paramVarArgs.equals("com.etsy.android.soe")) {
          EtsyApplication.access$002(EtsyApplication.this, i);
        } else if (paramVarArgs.equals("com.etsy.android")) {
          EtsyApplication.access$102(EtsyApplication.this, i);
        }
      }
      return null;
    }
  }
}
