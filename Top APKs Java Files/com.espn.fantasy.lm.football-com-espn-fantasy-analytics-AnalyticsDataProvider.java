package com.espn.fantasy.analytics;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.espn.analytics.AnalyticsInitializationDataProvider;
import com.espn.fantasy.ESPNFantasyApplication;
import com.espn.onboarding.espnonboarding.EspnUserManager;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public enum AnalyticsDataProvider
  implements AnalyticsInitializationDataProvider
{
  INSTANCE;
  
  private static final String ESPN_SIGN_IN = "ESPN";
  private static final String TAG = "AnalyticsDataProvider";
  private static final String TRIAL = "Trial";
  private String currentSport;
  private String[] customDimens;
  private String leagueManager;
  private String navMethod;
  
  private AnalyticsDataProvider() {}
  
  public static AnalyticsDataProvider getInstance()
  {
    return INSTANCE;
  }
  
  public boolean doesUserPlayFantasy()
  {
    return false;
  }
  
  public String getAuthenticationStatus()
  {
    return null;
  }
  
  public String getAutoplaySetting()
  {
    return null;
  }
  
  public String getBlueKaiSiteId()
  {
    return ESPNFantasyApplication.BLUEKAI_SITE_ID;
  }
  
  public String getCurrentEdition()
  {
    return "en-us";
  }
  
  public String getCurrentLanguage()
  {
    return Locale.getDefault().getDisplayLanguage();
  }
  
  public String getCurrentSport()
  {
    return this.currentSport;
  }
  
  public String[] getCustomDimensions()
  {
    return this.customDimens;
  }
  
  public String getEPlusEntitlementStatus()
  {
    return null;
  }
  
  public List<String> getExtraCustomDimensions()
  {
    return new CustomDimensionProviderFactory().newProvider().provideCustomDimensions();
  }
  
  public int getFavoriteLeagueTotal()
  {
    return 0;
  }
  
  public int getFavoritePodcastTotal()
  {
    return 0;
  }
  
  public int getFavoriteTeamTotal()
  {
    return 0;
  }
  
  public String getFreeTrialStatus()
  {
    return null;
  }
  
  public String getGoogleAdvertisingID()
  {
    return ESPNFantasyApplication.getSingleton().getGoogleAdvertisingId();
  }
  
  public String getLaunchMechanism(Context paramContext)
  {
    return null;
  }
  
  public String getLeagueManager()
  {
    return this.leagueManager;
  }
  
  public String getLoginType()
  {
    if (EspnUserManager.getInstance().isLoggedIn()) {
      return "ESPN";
    }
    return "Trial";
  }
  
  public String getNetworkBucket()
  {
    return null;
  }
  
  public String getNielsenAudioAppId()
  {
    return null;
  }
  
  public String getNielsenAudioSFCode()
  {
    return null;
  }
  
  public String getNielsenStaticAppId()
  {
    return ESPNFantasyApplication.NIELSEN_APP_ID;
  }
  
  public String getNielsenStaticSFCode()
  {
    return ESPNFantasyApplication.NIELSEN_SFCODE;
  }
  
  public String getNielsenStaticVcId()
  {
    return ESPNFantasyApplication.NIELSEN_VCID;
  }
  
  public String getOOMEntitlementStatus()
  {
    return null;
  }
  
  public String getOmnitureAppName()
  {
    return ESPNFantasyApplication.OMNITURE_APP_NAME;
  }
  
  public String getOmnitureChannel()
  {
    return ESPNFantasyApplication.OMNITURE_CHANNEL;
  }
  
  public String getPerformanceAnimationScore()
  {
    return null;
  }
  
  public int getPerformanceYear()
  {
    return 0;
  }
  
  public String getSignInNavMethod()
  {
    return this.navMethod;
  }
  
  public String getSite()
  {
    return null;
  }
  
  public String getSwid()
  {
    return EspnUserManager.getInstance().getEspnCredentialSwid();
  }
  
  public String getTVEAuthenticationStatus()
  {
    return null;
  }
  
  public String getTVEEntitlementStatus()
  {
    return null;
  }
  
  public String getTestingKey()
  {
    return null;
  }
  
  public String getTimeRemainingPercentage()
  {
    return null;
  }
  
  public int getTotalFavorites()
  {
    return 0;
  }
  
  public String getVisitorId()
  {
    return ESPNFantasyApplication.VISITOR_ID;
  }
  
  public boolean hasAlertPrefs()
  {
    return false;
  }
  
  public boolean hasFavorites()
  {
    Iterator localIterator = ESPNFantasyApplication.getSingleton().getPackageManager().getInstalledApplications(128).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.equals("com.espn.score_center")) {
        return true;
      }
    }
    return false;
  }
  
  public boolean isChromecasting()
  {
    return false;
  }
  
  public boolean isFantasyAppUser()
  {
    return true;
  }
  
  public boolean isHttpsEnabled()
  {
    return false;
  }
  
  public boolean isInSplitScreen()
  {
    return false;
  }
  
  public boolean isLocationServiceEnabled()
  {
    return false;
  }
  
  public boolean isLoggedIn()
  {
    return EspnUserManager.getInstance().isLoggedIn();
  }
  
  public boolean isLoggingEnabled()
  {
    return false;
  }
  
  public boolean isPremiumUser()
  {
    return EspnUserManager.getInstance().isPremiumUser();
  }
  
  public void setCurrentSport(String paramString)
  {
    this.currentSport = paramString;
  }
  
  public void setCustomDimensions(String[] paramArrayOfString)
  {
    this.customDimens = paramArrayOfString;
  }
  
  public void setLeagueManager(String paramString)
  {
    this.leagueManager = paramString;
  }
  
  public void setSignInNavMethod(String paramString)
  {
    this.navMethod = paramString;
  }
}
