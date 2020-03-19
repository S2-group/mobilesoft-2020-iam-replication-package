package com.jumpramp.lucktastic.core.application;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.adjust.sdk.Adjust;
import com.adjust.sdk.AdjustConfig;
import com.appsflyer.AppsFlyerLib;
import com.facebook.FacebookSdk;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.flurry.android.FlurryAgent;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.gson.Gson;
import com.jumpramp.lucktastic.core.clientinterface.ClientContent;
import com.jumpramp.lucktastic.core.core.analytics.AmplitudeHelper;
import com.jumpramp.lucktastic.core.core.api.loader.CampaignLoader;
import com.jumpramp.lucktastic.core.core.api.loader.OpportunityLoader;
import com.jumpramp.lucktastic.core.core.data.LucktasticDB;
import com.jumpramp.lucktastic.core.core.models.Alert;
import com.jumpramp.lucktastic.core.core.models.InAppMessage;
import com.jumpramp.lucktastic.core.core.models.TutorialTipResponse;
import com.jumpramp.lucktastic.core.core.models.TutorialTipResponse.TutorialTip;
import com.jumpramp.lucktastic.core.core.models.UserProfile;
import com.jumpramp.lucktastic.core.core.utils.EmptyUtils;
import com.jumpramp.lucktastic.core.core.utils.GooglePlayServicesUtils;
import com.jumpramp.lucktastic.core.core.utils.GooglePlayServicesUtils.GoogleAdvertisingIDListener;
import com.jumpramp.lucktastic.core.core.utils.SharedPreferencesHelper;
import com.jumpramp.lucktastic.core.core.utils.StateMachineManager;
import com.lucktastic.scratch.RegisterLoginActivity;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import me.kiip.sdk.Kiip;
import okhttp3.OkHttpClient;
import org.acra.ACRA;

public abstract class LucktasticCore
  extends Application
{
  protected static final String PROD_API_URL = "https://api.lucktastic.com";
  protected static final String STAG_API_URL = "http://54.84.229.39";
  protected static final String _DEV_API_URL = "http://52.23.210.65";
  private static CampaignLoader campaignLoaderInstance;
  private static LucktasticCore instance = null;
  private static LucktasticDB lucktasticDBInstance = null;
  private static OpportunityLoader opportunityLoaderInstance;
  private static StateMachineManager stateMachineManagerInstance;
  private final long MAX_ACTIVITY_TRANSITION_TIME_MS = 2000L;
  private ArrayList<Alert> alerts;
  private boolean alreadyGotEasterEggs = false;
  private boolean bypassVersionCheck = false;
  private boolean debugStateMachine = false;
  private boolean flipTiles = false;
  private boolean gotAOTD = false;
  final List<InAppMessage> inAppMessages = new ArrayList();
  private boolean isProd = true;
  private boolean isStag = false;
  private boolean is_Dev = false;
  private Timer mActivityTransitionTimer;
  private TimerTask mActivityTransitionTimerTask;
  private ArrayList<Alert> pastAlerts;
  private OkHttpClient refreshOkHttpClient;
  private OkHttpClient sessionOkHttpClient;
  private Tracker tracker = null;
  private boolean wasInBackground;
  
  static
  {
    campaignLoaderInstance = null;
    opportunityLoaderInstance = null;
    stateMachineManagerInstance = null;
  }
  
  public LucktasticCore() {}
  
  public static CampaignLoader getCampaignLoader()
  {
    return campaignLoaderInstance;
  }
  
  public static LucktasticCore getInstance()
  {
    return instance;
  }
  
  public static LucktasticDB getLucktasticDBInstance()
  {
    return lucktasticDBInstance;
  }
  
  public static OpportunityLoader getOpportunityLoader()
  {
    return opportunityLoaderInstance;
  }
  
  public static StateMachineManager getStateMachineManager()
  {
    return stateMachineManagerInstance;
  }
  
  private void initAdjust()
  {
    if (isProd().booleanValue()) {}
    for (String str = "production";; str = "sandbox")
    {
      Adjust.onCreate(new AdjustConfig(this, "tdjrcv4yz9jr", str));
      return;
    }
  }
  
  private void initAppboy()
  {
    if (!isProd().booleanValue()) {
      com.appboy.support.AppboyLogger.LogLevel = 3;
    }
  }
  
  private void initAppsFlyer()
  {
    AppsFlyerLib.getInstance().startTracking(this, "jHRE92ifAUmTuMZNx96obW");
  }
  
  private void initFacebook()
  {
    FacebookSdk.sdkInitialize(getApplicationContext());
  }
  
  private void initFlurry()
  {
    if (!isProd().booleanValue()) {}
    for (boolean bool = true;; bool = false)
    {
      FlurryAgent.setLogEnabled(bool);
      FlurryAgent.init(this, "ZCTZSBZ9ZC39M6WMSNR4");
      return;
    }
  }
  
  private void initFresco()
  {
    Fresco.initialize(getApplicationContext());
  }
  
  private void initKiip()
  {
    Kiip.setInstance(Kiip.init(this, "01fbe7501eaeac41ee6bab988dafe6c1", "8f5e46d495de0fb636b07d7610c71c46"));
  }
  
  public void addInAppMessage(InAppMessage paramInAppMessage)
  {
    Set localSet = SharedPreferencesHelper.getInstalledPackages();
    if ((!this.gotAOTD) && (paramInAppMessage.getType() != null) && (paramInAppMessage.getType().equalsIgnoreCase("aotd")) && (!localSet.contains(paramInAppMessage.getComPackage())))
    {
      this.inAppMessages.add(paramInAppMessage);
      this.gotAOTD = true;
    }
    while ((paramInAppMessage.getType() != null) && (paramInAppMessage.getType().equalsIgnoreCase("aotd"))) {
      return;
    }
    this.inAppMessages.add(paramInAppMessage);
  }
  
  public boolean alreadyGotEasterEggs()
  {
    return this.alreadyGotEasterEggs;
  }
  
  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(paramContext);
    MultiDex.install(this);
  }
  
  public final boolean bypassVersionCheck()
  {
    if (isProd().booleanValue()) {
      return false;
    }
    return this.bypassVersionCheck;
  }
  
  public void clearInAppMessages()
  {
    this.inAppMessages.clear();
  }
  
  public final boolean debugStateMachine()
  {
    return this.debugStateMachine;
  }
  
  public boolean flipTiles()
  {
    return this.flipTiles;
  }
  
  public ArrayList<Alert> getAlerts()
  {
    return this.alerts;
  }
  
  public String getApiUrl()
  {
    if (isProd().booleanValue()) {
      return "https://api.lucktastic.com";
    }
    if (isStag().booleanValue()) {
      return "http://54.84.229.39";
    }
    return "http://52.23.210.65";
  }
  
  public String getAppId()
  {
    return "d532746";
  }
  
  public InAppMessage getInAppMessage()
  {
    if (EmptyUtils.isListEmpty(this.inAppMessages)) {
      return null;
    }
    return (InAppMessage)this.inAppMessages.get(0);
  }
  
  public ArrayList<Alert> getPastAlerts()
  {
    return this.pastAlerts;
  }
  
  public String getPrettyAddress()
  {
    String str2;
    label65:
    String str3;
    label84:
    String str4;
    label104:
    String str5;
    if ((getLucktasticDBInstance().getUserProfile().isRegistered()) && (!TextUtils.isEmpty(getLucktasticDBInstance().getUserProfile().getState()))) {
      if (TextUtils.isEmpty(getLucktasticDBInstance().getUserProfile().getStreetAddress1()))
      {
        str1 = "";
        if (!TextUtils.isEmpty(getLucktasticDBInstance().getUserProfile().getStreetAddress2())) {
          break label185;
        }
        str2 = "";
        if (!TextUtils.isEmpty(getLucktasticDBInstance().getUserProfile().getCity())) {
          break label198;
        }
        str3 = "";
        if (!TextUtils.isEmpty(getLucktasticDBInstance().getUserProfile().getState())) {
          break label211;
        }
        str4 = "";
        if (!TextUtils.isEmpty(getLucktasticDBInstance().getUserProfile().getZip())) {
          break label225;
        }
        str5 = "";
      }
    }
    label124:
    for (String str1 = String.format("%s %s\n %s, %s %s", new Object[] { str1, str2, str3, str4, str5 });; str1 = getLucktasticDBInstance().getUserProfile().getZip())
    {
      str2 = str1;
      if (TextUtils.isEmpty(str1)) {
        str2 = "";
      }
      return str2;
      str1 = getLucktasticDBInstance().getUserProfile().getStreetAddress1();
      break;
      label185:
      str2 = getLucktasticDBInstance().getUserProfile().getStreetAddress2();
      break label65;
      label198:
      str3 = getLucktasticDBInstance().getUserProfile().getCity();
      break label84;
      label211:
      str4 = getLucktasticDBInstance().getUserProfile().getState();
      break label104;
      label225:
      str5 = getLucktasticDBInstance().getUserProfile().getZip();
      break label124;
    }
  }
  
  public String getPublicId()
  {
    String str2 = getLucktasticDBInstance().getPublicId();
    String str1 = str2;
    if (TextUtils.isEmpty(str2)) {
      str1 = "";
    }
    return str1;
  }
  
  public OkHttpClient getRefreshOkHttpClient()
  {
    return this.refreshOkHttpClient;
  }
  
  public String getRefreshToken()
  {
    Object localObject2 = getLucktasticDBInstance().getRefreshToken();
    Object localObject1 = localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject2)) {
      localObject1 = SharedPreferencesHelper.getRefreshToken();
    }
    localObject2 = localObject1;
    if (TextUtils.isEmpty((CharSequence)localObject1)) {
      localObject2 = "";
    }
    return localObject2;
  }
  
  public Integer getServerCode()
  {
    return Integer.valueOf(getVersionCode().intValue() - 90);
  }
  
  public OkHttpClient getSessionOkHttpClient()
  {
    return this.sessionOkHttpClient;
  }
  
  public String getSessionToken()
  {
    Object localObject2 = getLucktasticDBInstance().getSessionToken();
    Object localObject1 = localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject2)) {
      localObject1 = SharedPreferencesHelper.getSessionToken();
    }
    localObject2 = localObject1;
    if (TextUtils.isEmpty((CharSequence)localObject1)) {
      localObject2 = "";
    }
    return localObject2;
  }
  
  public Tracker getTracker()
  {
    try
    {
      if (this.tracker == null)
      {
        localObject1 = GoogleAnalytics.getInstance(this);
        ((GoogleAnalytics)localObject1).getLogger().setLogLevel(0);
        ((GoogleAnalytics)localObject1).setLocalDispatchPeriod(30);
        ((GoogleAnalytics)localObject1).setDryRun(false);
        this.tracker = ((GoogleAnalytics)localObject1).newTracker("UA-27900360-9");
        this.tracker.enableAutoActivityTracking(false);
        this.tracker.enableAdvertisingIdCollection(false);
        this.tracker.enableExceptionReporting(false);
        this.tracker.setSampleRate(50.0D);
        this.tracker.setSessionTimeout(30L);
      }
      Object localObject1 = this.tracker;
      return localObject1;
    }
    finally {}
  }
  
  public TutorialTipResponse.TutorialTip[] getTutorialTips()
  {
    try
    {
      Object localObject = new InputStreamReader(getApplicationContext().getAssets().open("tutorial.json"));
      localObject = ((TutorialTipResponse)new Gson().fromJson((Reader)localObject, TutorialTipResponse.class)).getTutorialTips();
      localObject = (TutorialTipResponse.TutorialTip[])((List)localObject).toArray(new TutorialTipResponse.TutorialTip[((List)localObject).size()]);
      return localObject;
    }
    catch (Exception localException) {}
    return new TutorialTipResponse.TutorialTip[0];
  }
  
  public String getUserAgent()
  {
    return SharedPreferencesHelper.getUserAgent();
  }
  
  public String getUserId()
  {
    Object localObject2 = getLucktasticDBInstance().getUserId();
    Object localObject1 = localObject2;
    if (TextUtils.isEmpty((CharSequence)localObject2)) {
      localObject1 = SharedPreferencesHelper.getUserId();
    }
    localObject2 = localObject1;
    if (TextUtils.isEmpty((CharSequence)localObject1)) {
      localObject2 = "";
    }
    return localObject2;
  }
  
  public Integer getVersionCode()
  {
    try
    {
      int i = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
      return Integer.valueOf(i);
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return Integer.valueOf(420);
  }
  
  public String getVersionName()
  {
    try
    {
      String str = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException) {}
    return "";
  }
  
  public final Boolean isLoggable()
  {
    if (!getPackageName().equals("com.lucktastic.scratch")) {}
    for (boolean bool = true;; bool = false) {
      return Boolean.valueOf(bool);
    }
  }
  
  public final Boolean isProd()
  {
    return Boolean.valueOf(this.isProd);
  }
  
  public final Boolean isStag()
  {
    return Boolean.valueOf(this.isStag);
  }
  
  public boolean isUserRegistered()
  {
    return ClientContent.INSTANCE.isLoggedIn();
  }
  
  public final Boolean is_Dev()
  {
    return Boolean.valueOf(this.is_Dev);
  }
  
  public void logout()
  {
    ClientContent.INSTANCE.logout();
    this.alerts = new ArrayList();
    this.pastAlerts = new ArrayList();
    AmplitudeHelper.setUserId();
  }
  
  public void onCreate()
  {
    boolean bool = false;
    super.onCreate();
    instance = this;
    if ((getPackageName().equals("com.lucktastic.scratch")) || (getPackageName().equals("com.lucktastic.scratch.prod"))) {
      bool = true;
    }
    this.isProd = bool;
    this.isStag = getPackageName().equals("com.lucktastic.scratch.stag");
    this.is_Dev = getPackageName().equals("com.lucktastic.scratch.dev");
    try
    {
      localObject1 = getPackageManager().getApplicationInfo(getPackageName(), 128).metaData;
      this.debugStateMachine = ((Bundle)localObject1).getBoolean("debug_state_machine", false);
      this.bypassVersionCheck = ((Bundle)localObject1).getBoolean("bypass_version_check", false);
    }
    catch (Exception localException1)
    {
      try
      {
        for (;;)
        {
          lucktasticDBInstance = new LucktasticDB(getApplicationContext(), new DatabaseErrorHandler()
          {
            public void onCorruption(SQLiteDatabase paramAnonymousSQLiteDatabase) {}
          });
          lucktasticDBInstance.open();
          initAdjust();
          initAppboy();
          initAppsFlyer();
          initFacebook();
          initFlurry();
          initFresco();
          initKiip();
          campaignLoaderInstance = new CampaignLoader();
          opportunityLoaderInstance = new OpportunityLoader();
          stateMachineManagerInstance = new StateMachineManager();
          this.alerts = new ArrayList();
          this.pastAlerts = new ArrayList();
          ACRA.init(this);
          GooglePlayServicesUtils.getGoogleAdvertisingID(getApplicationContext(), new GooglePlayServicesUtils.GoogleAdvertisingIDListener()
          {
            public void onGoogleAdvertisingId(AdvertisingIdClient.Info paramAnonymousInfo)
            {
              SharedPreferencesHelper.putGoogleAdvertisingId(paramAnonymousInfo.getId());
            }
            
            public void onGooglePlayServicesNotAvailableException(GooglePlayServicesNotAvailableException paramAnonymousGooglePlayServicesNotAvailableException) {}
            
            public void onGooglePlayServicesRepairableException(GooglePlayServicesRepairableException paramAnonymousGooglePlayServicesRepairableException) {}
            
            public void onIOException(IOException paramAnonymousIOException) {}
          });
          Object localObject2 = getPackageManager().getInstalledApplications(128);
          Object localObject1 = new HashSet();
          ((Set)localObject1).clear();
          localObject2 = ((List)localObject2).iterator();
          while (((Iterator)localObject2).hasNext())
          {
            ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject2).next();
            if ((localApplicationInfo.flags & 0x1) != 1) {
              ((Set)localObject1).add(localApplicationInfo.packageName);
            }
          }
          localException1 = localException1;
          localException1.printStackTrace();
        }
      }
      catch (Exception localException2)
      {
        for (;;)
        {
          localException2.printStackTrace();
        }
        SharedPreferencesHelper.putInstalledPackages(localException2);
        getLucktasticDBInstance().clearInAppMessages();
        getLucktasticDBInstance().clearAlerts();
        getLucktasticDBInstance().clearDailyReward();
        getLucktasticDBInstance().clearNotificationSettings();
        SharedPreferencesHelper.removeNetworkString();
        sendBroadcast(new Intent("YouWillNeverKillMe"));
      }
    }
  }
  
  public void onTerminate()
  {
    super.onTerminate();
  }
  
  public void refreshComPackageList()
  {
    Object localObject = getPackageManager().getInstalledApplications(128);
    HashSet localHashSet = new HashSet();
    localHashSet.clear();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      if ((localApplicationInfo.flags & 0x1) != 1) {
        localHashSet.add(localApplicationInfo.packageName);
      }
    }
    SharedPreferencesHelper.putInstalledPackages(localHashSet);
  }
  
  public InAppMessage removeInAppMessage()
  {
    if (EmptyUtils.isListEmpty(this.inAppMessages)) {
      return null;
    }
    return (InAppMessage)this.inAppMessages.remove(0);
  }
  
  public void setAlerts(ArrayList<Alert> paramArrayList)
  {
    this.alerts = paramArrayList;
  }
  
  public void setAlreadyGotEasterEggs(boolean paramBoolean)
  {
    this.alreadyGotEasterEggs = paramBoolean;
  }
  
  public void setFlipTiles(boolean paramBoolean)
  {
    this.flipTiles = paramBoolean;
  }
  
  public void setPastAlerts(ArrayList<Alert> paramArrayList)
  {
    this.pastAlerts = paramArrayList;
  }
  
  public void setRefreshOkHttpClient(OkHttpClient paramOkHttpClient)
  {
    this.refreshOkHttpClient = paramOkHttpClient;
  }
  
  public void setSessionOkHttpClient(OkHttpClient paramOkHttpClient)
  {
    this.sessionOkHttpClient = paramOkHttpClient;
  }
  
  public void startActivityTransitionTimer()
  {
    this.mActivityTransitionTimer = new Timer();
    this.mActivityTransitionTimerTask = new TimerTask()
    {
      public void run()
      {
        LucktasticCore.access$002(LucktasticCore.this, true);
      }
    };
    Timer localTimer = this.mActivityTransitionTimer;
    TimerTask localTimerTask = this.mActivityTransitionTimerTask;
    getClass();
    localTimer.schedule(localTimerTask, 2000L);
  }
  
  public void startRegisterActivity(FragmentActivity paramFragmentActivity, int paramInt)
  {
    Intent localIntent = new Intent(paramFragmentActivity, RegisterLoginActivity.class);
    localIntent.putExtra("EXTRA_RETURN_TO_PREVIOUS", true);
    paramFragmentActivity.startActivityForResult(localIntent, paramInt);
  }
  
  public void stopActivityTransitionTimer()
  {
    if (this.mActivityTransitionTimerTask != null) {
      this.mActivityTransitionTimerTask.cancel();
    }
    if (this.mActivityTransitionTimer != null) {
      this.mActivityTransitionTimer.cancel();
    }
    this.wasInBackground = false;
  }
  
  public boolean wasInBackground()
  {
    return this.wasInBackground;
  }
}
