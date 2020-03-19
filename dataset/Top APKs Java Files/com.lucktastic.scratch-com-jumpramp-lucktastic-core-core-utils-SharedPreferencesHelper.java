package com.jumpramp.lucktastic.core.core.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import com.jumpramp.lucktastic.core.application.LucktasticCore;
import java.io.File;
import java.io.PrintStream;
import java.util.Set;

public class SharedPreferencesHelper
{
  private static final String INSTALLED_PACKAGES = "INSTALLED_PACKAGES";
  private static final String KEY_APP_GOOGLE_ADVERTISING_ID = "GOOGLE_ADVERTISING_ID";
  private static final String KEY_APP_SALT = "AppSaltValue";
  private static final String KEY_CASH_FOR_GRAB = "KEY_CASH_FOR_GRAB";
  private static final String KEY_CURRENT_ADVERTISING_ID = "CURRENT_ADVERTISING_ID";
  private static final String KEY_CURRENT_APP_VERSION_CODE = "CURRENT_APP_VERSION_CODE";
  private static final String KEY_CURRENT_APP_VERSION_NAME = "CURRENT_APP_VERSION_NAME";
  private static final String KEY_CURRENT_SERVER_CODE = "CURRENT_SERVER_CODE";
  private static final String KEY_DWOLLA_ACCOUNT_ID = "DwollaAccountId";
  private static final String KEY_DWOLLA_CODE = "DwollaCode";
  private static final String KEY_DWOLLA_NAME = "DwollaName";
  private static final String KEY_DWOLLA_TOKEN = "DwollaToken";
  public static final String KEY_EVENT_ID = "event_id";
  private static final String KEY_GATED_OPP_ID_OPEN = "GATED_OPP_ID_OPEN";
  public static final String KEY_IS_REF_COMPLETE = "is_ref_complete";
  public static final String KEY_LUCK_REF_ID = "luck_ref_id";
  public static final String KEY_LUCK_SRC = "luck_src";
  private static final String KEY_NETWORK_STRING = "key_network_string";
  private static final String KEY_ON_LOGIN_SUCCESS = "OnLoginSuccess";
  private static final String KEY_ON_REGISTER_SUCCESS = "OnRegisterSuccess";
  public static final String KEY_OPP_ID = "opp_id";
  private static final String KEY_OPP_REFRESH_TIME = "key_opp_refresh_time";
  private static final String KEY_PASSWORD_UPDATE = "PasswordUpdate";
  private static final String KEY_PLAYER_PREFS_REFRESH_TOKEN = "refreshToken";
  private static final String KEY_PLAYER_PREFS_SESSION_TOKEN = "sessionToken";
  private static final String KEY_PLAYER_PREFS_USER_ID = "UserId";
  private static final String KEY_PRIZE_WHEEL_SOUND = "PRIZE_WHEEL_SOUND";
  private static final String KEY_PRIZE_WHEEL_SPEED = "PRIZE_WHEEL_SPEED";
  private static final String KEY_PRIZE_WHEEL_TUTORIAL = "PRIZE_WHEEL_TUTORIAL";
  public static final String KEY_REFERRER = "referrer";
  public static final String KEY_REF_CHANNEL = "ref_channel";
  public static final String KEY_REF_REWARD_COUNT = "ref_reward_count";
  public static final String KEY_REF_SIG = "ref_sig";
  private static final String KEY_REVENUE_PER_AD_VIEWED = "revenue_per_ad_viewed";
  private static final String KEY_REVENUE_PER_CARD_PLAY = "revenue_per_card_play";
  private static final String KEY_SHOW_GAME_DIALOG = "SHOW_GAME_DIALOG";
  private static final String KEY_TUTORIAL_SHOWN = "tutorial_shown";
  private static final String KEY_TWITTER_AUTH_TOKEN = "twitter_auth_token";
  private static final String KEY_TWITTER_AUTH_TOKEN_SECRET = "twitter_auth_token_secret";
  private static final String KEY_TWO_FACTOR_TOKEN_B = "TwoFactorTokenB";
  private static final String KEY_TWO_FACTOR_TOKEN_C = "TwoFactorTokenC";
  private static final String KEY_UPDATE_ALLOW_SKIP = "AllowSkip";
  private static final String KEY_UPDATE_MESSAGE_BODY = "UpdateMessageBody";
  private static final String KEY_UPDATE_MESSAGE_HEADER = "UpdateMessageHeader";
  private static final String KEY_USER_AGENT = "UserAgent";
  private static final String KEY_USER_ID_ENCRYPTION_KEY = "UserIdEncryptionValue";
  private static final String KEY_WINNER_COUNT = "key_winner_count";
  private static final String KEY_ZIP_CODE = "zip";
  private static final String KEY_Z_TOKEN = "ZToken";
  public static final String LUCK150 = "LUCK150";
  private static final String LUCKTASTIC_SHARED_PREFS = "lucktastic_shared_prefs";
  private static final String LUCKTASTIC_SHARED_PREFS_PERM = "lucktastic_shared_prefs_perm";
  private static final int MODE = 0;
  public static final String SOURCE = "SOURCE";
  
  public SharedPreferencesHelper() {}
  
  public static Boolean clearLucktasticPermanentPreferences()
  {
    return Boolean.valueOf(getLucktasticPermanentPreferences().edit().clear().commit());
  }
  
  public static Boolean clearLucktasticPreferences()
  {
    return Boolean.valueOf(getLucktasticPreferences().edit().clear().commit());
  }
  
  public static void clearReferrerSourceFromInstall()
  {
    getLucktasticPermanentPreferences().edit().remove("luck_ref_id").remove("event_id").remove("opp_id").remove("ref_channel").remove("ref_sig").putBoolean("is_ref_complete", true).putInt("ref_reward_count", 3).apply();
  }
  
  public static Boolean containsGatedOppIdOpen()
  {
    return Boolean.valueOf(getLucktasticPermanentPreferences().contains("GATED_OPP_ID_OPEN"));
  }
  
  public static Boolean containsTwitterAuthToken()
  {
    return Boolean.valueOf(getLucktasticPermanentPreferences().contains("twitter_auth_token"));
  }
  
  public static Boolean containsTwitterAuthTokenSecret()
  {
    return Boolean.valueOf(getLucktasticPermanentPreferences().contains("twitter_auth_token_secret"));
  }
  
  public static String getCashForGrab()
  {
    return getLucktasticPermanentPreferences().getString("KEY_CASH_FOR_GRAB", "34,325");
  }
  
  public static String getCurrentAdvertisingId()
  {
    return getLucktasticPermanentPreferences().getString("CURRENT_ADVERTISING_ID", "");
  }
  
  public static String getCurrentAppVersionCode()
  {
    return getLucktasticPermanentPreferences().getString("CURRENT_APP_VERSION_CODE", "");
  }
  
  public static String getCurrentAppVersionName()
  {
    return getLucktasticPermanentPreferences().getString("CURRENT_APP_VERSION_NAME", "");
  }
  
  public static String getCurrentServerCode()
  {
    return getLucktasticPermanentPreferences().getString("CURRENT_SERVER_CODE", "");
  }
  
  public static String getDwollaAccountId()
  {
    return getLucktasticPreferences().getString("DwollaAccountId", "");
  }
  
  public static String getDwollaCode()
  {
    return getLucktasticPreferences().getString("DwollaCode", "");
  }
  
  public static String getDwollaName()
  {
    return getLucktasticPreferences().getString("DwollaName", "");
  }
  
  public static String getDwollaToken()
  {
    return getLucktasticPreferences().getString("DwollaToken", "");
  }
  
  public static String getGatedOppIdOpen()
  {
    return getLucktasticPermanentPreferences().getString("GATED_OPP_ID_OPEN", null);
  }
  
  public static String getGoogleAdvertisingId()
  {
    return getLucktasticPermanentPreferences().getString("GOOGLE_ADVERTISING_ID", "");
  }
  
  public static Set<String> getInstalledPackages()
  {
    return getLucktasticPermanentPreferences().getStringSet("INSTALLED_PACKAGES", null);
  }
  
  public static Boolean getIsReferralComplete()
  {
    return Boolean.valueOf(getLucktasticPermanentPreferences().getBoolean("is_ref_complete", true));
  }
  
  private static SharedPreferences getLucktasticPermanentPreferences()
  {
    return LucktasticCore.getInstance().getApplicationContext().getSharedPreferences("lucktastic_shared_prefs_perm", 0);
  }
  
  private static SharedPreferences getLucktasticPreferences()
  {
    return LucktasticCore.getInstance().getApplicationContext().getSharedPreferences("lucktastic_shared_prefs", 0);
  }
  
  private static SharedPreferences getLucktasticUnityPlayerPreferences()
  {
    return LucktasticCore.getInstance().getApplicationContext().getSharedPreferences(LucktasticCore.getInstance().getPackageName() + ".v2.playerprefs", 0);
  }
  
  public static String getNetworkString()
  {
    return getLucktasticPreferences().getString("key_network_string", "");
  }
  
  public static Boolean getOnLoginSuccess()
  {
    return Boolean.valueOf(getLucktasticPreferences().getBoolean("OnLoginSuccess", false));
  }
  
  public static Boolean getOnRegisterSuccess()
  {
    return Boolean.valueOf(getLucktasticPreferences().getBoolean("OnRegisterSuccess", false));
  }
  
  public static long getOppRefreshTime()
  {
    return getLucktasticPreferences().getLong("key_opp_refresh_time", 0L);
  }
  
  public static Boolean getPasswordUpdate()
  {
    return Boolean.valueOf(getLucktasticPreferences().getBoolean("PasswordUpdate", false));
  }
  
  public static Boolean getPasswordUpdateAllowSkip()
  {
    return Boolean.valueOf(getLucktasticPreferences().getBoolean("AllowSkip", true));
  }
  
  public static String getPasswordUpdateMessageBody()
  {
    return getLucktasticPreferences().getString("UpdateMessageBody", null);
  }
  
  public static String getPasswordUpdateMessageHeader()
  {
    return getLucktasticPreferences().getString("UpdateMessageHeader", null);
  }
  
  public static String getReferrerChannel()
  {
    return getLucktasticPermanentPreferences().getString("ref_channel", "");
  }
  
  public static String getReferrerEventId()
  {
    return getLucktasticPermanentPreferences().getString("event_id", "");
  }
  
  public static String getReferrerId()
  {
    return getLucktasticPermanentPreferences().getString("luck_ref_id", "");
  }
  
  public static String getReferrerOppId()
  {
    return getLucktasticPermanentPreferences().getString("opp_id", "");
  }
  
  public static Integer getReferrerRewardCount()
  {
    return Integer.valueOf(getLucktasticPermanentPreferences().getInt("ref_reward_count", 0));
  }
  
  public static String getReferrerSignal()
  {
    return getLucktasticPermanentPreferences().getString("ref_sig", "");
  }
  
  public static String getRefreshToken()
  {
    return getLucktasticUnityPlayerPreferences().getString("refreshToken", "");
  }
  
  public static Double getRevenuePerAdViewed()
  {
    return Double.valueOf(Double.longBitsToDouble(getLucktasticPreferences().getLong("revenue_per_ad_viewed", Double.doubleToLongBits(0.02D))));
  }
  
  public static Double getRevenuePerCardPlay()
  {
    return Double.valueOf(Double.longBitsToDouble(getLucktasticPreferences().getLong("revenue_per_card_play", Double.doubleToLongBits(0.02D))));
  }
  
  public static String getSalt()
  {
    return getLucktasticPermanentPreferences().getString("AppSaltValue", "ThisLITTLEsecret_of_MIN3_is_W3LLseasoneD");
  }
  
  public static String getSessionToken()
  {
    return getLucktasticUnityPlayerPreferences().getString("sessionToken", "");
  }
  
  public static void getSharedPreferencesDirectory()
  {
    Object localObject = new File(LucktasticCore.getInstance().getApplicationInfo().dataDir, "shared_prefs");
    if ((((File)localObject).exists()) && (((File)localObject).isDirectory()))
    {
      localObject = ((File)localObject).list();
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        String str = localObject[i];
        System.out.println("Shared Preferences File: " + str);
        i += 1;
      }
    }
  }
  
  public static Boolean getShowGameCardDialog()
  {
    return Boolean.valueOf(getLucktasticPreferences().getBoolean("SHOW_GAME_DIALOG", true));
  }
  
  public static String getSource()
  {
    return getLucktasticPermanentPreferences().getString("SOURCE", "LUCK150");
  }
  
  public static Boolean getSpinWheelSound()
  {
    return Boolean.valueOf(getLucktasticPreferences().getBoolean("PRIZE_WHEEL_SOUND", true));
  }
  
  public static Boolean getSpinWheelSpeed()
  {
    return Boolean.valueOf(getLucktasticPreferences().getBoolean("PRIZE_WHEEL_SPEED", false));
  }
  
  public static Boolean getSpinWheelTutorialShown()
  {
    return Boolean.valueOf(getLucktasticPreferences().getBoolean("PRIZE_WHEEL_TUTORIAL", false));
  }
  
  public static String getTwitterAuthToken()
  {
    return getLucktasticPermanentPreferences().getString("twitter_auth_token", null);
  }
  
  public static String getTwitterAuthTokenSecret()
  {
    return getLucktasticPermanentPreferences().getString("twitter_auth_token_secret", null);
  }
  
  public static String getTwoFactorTokenB()
  {
    return getLucktasticPreferences().getString("TwoFactorTokenB", "");
  }
  
  public static String getTwoFactorTokenC()
  {
    return getLucktasticPreferences().getString("TwoFactorTokenC", "");
  }
  
  public static String getUserAgent()
  {
    return getLucktasticPermanentPreferences().getString("UserAgent", "Native_Android");
  }
  
  public static String getUserId()
  {
    return getLucktasticUnityPlayerPreferences().getString("UserId", "");
  }
  
  public static String getUserIdEncryptionKey()
  {
    return getLucktasticPermanentPreferences().getString("UserIdEncryptionValue", "D*hasdf%F,dsw-0&");
  }
  
  public static String getUserZipCode()
  {
    return getLucktasticPreferences().getString("zip", null);
  }
  
  public static String getWinnerCount()
  {
    return getLucktasticPermanentPreferences().getString("key_winner_count", "313805");
  }
  
  public static String getZToken()
  {
    return getLucktasticPreferences().getString("ZToken", null);
  }
  
  public static void incrementReferrerRewardCount()
  {
    SharedPreferences localSharedPreferences = getLucktasticPermanentPreferences();
    int i = localSharedPreferences.getInt("ref_reward_count", 0);
    localSharedPreferences.edit().putInt("ref_reward_count", Integer.valueOf(i + 1).intValue()).apply();
  }
  
  public static void putCashForGrab(String paramString)
  {
    getLucktasticPermanentPreferences().edit().putString("KEY_CASH_FOR_GRAB", paramString).apply();
  }
  
  public static void putCurrentAdvertisingId(String paramString)
  {
    getLucktasticPermanentPreferences().edit().putString("CURRENT_ADVERTISING_ID", paramString).apply();
  }
  
  public static void putCurrentAppVersionCode(String paramString)
  {
    getLucktasticPermanentPreferences().edit().putString("CURRENT_APP_VERSION_CODE", paramString).apply();
  }
  
  public static void putCurrentAppVersionName(String paramString)
  {
    getLucktasticPermanentPreferences().edit().putString("CURRENT_APP_VERSION_NAME", paramString).apply();
  }
  
  public static void putCurrentServerCode(String paramString)
  {
    getLucktasticPermanentPreferences().edit().putString("CURRENT_SERVER_CODE", paramString).apply();
  }
  
  public static void putDwollaAccountId(String paramString)
  {
    getLucktasticPreferences().edit().putString("DwollaAccountId", paramString).apply();
  }
  
  public static void putDwollaCode(String paramString)
  {
    getLucktasticPreferences().edit().putString("DwollaCode", paramString).apply();
  }
  
  public static void putDwollaName(String paramString)
  {
    getLucktasticPreferences().edit().putString("DwollaName", paramString).apply();
  }
  
  public static void putDwollaToken(String paramString)
  {
    getLucktasticPreferences().edit().putString("DwollaToken", paramString).apply();
  }
  
  public static void putGatedOppIdOpen(String paramString)
  {
    getLucktasticPermanentPreferences().edit().putString("GATED_OPP_ID_OPEN", paramString).apply();
  }
  
  public static void putGoogleAdvertisingId(String paramString)
  {
    getLucktasticPermanentPreferences().edit().putString("GOOGLE_ADVERTISING_ID", paramString).apply();
  }
  
  public static void putInstalledPackages(Set<String> paramSet)
  {
    getLucktasticPermanentPreferences().edit().putStringSet("INSTALLED_PACKAGES", paramSet).apply();
  }
  
  public static void putNetworkString(String paramString)
  {
    getLucktasticPreferences().edit().putString("key_network_string", paramString).apply();
  }
  
  public static void putOnLoginSuccess()
  {
    getLucktasticPreferences().edit().putBoolean("OnLoginSuccess", true).apply();
  }
  
  public static void putOnRegisterSuccess()
  {
    getLucktasticPreferences().edit().putBoolean("OnRegisterSuccess", true).apply();
  }
  
  public static void putOppRefreshTime(long paramLong)
  {
    getLucktasticPreferences().edit().putLong("key_opp_refresh_time", paramLong).apply();
  }
  
  public static void putPasswordUpdate(Boolean paramBoolean)
  {
    getLucktasticPreferences().edit().putBoolean("PasswordUpdate", paramBoolean.booleanValue()).apply();
  }
  
  public static void putPasswordUpdateAllowSkip(Boolean paramBoolean)
  {
    getLucktasticPreferences().edit().putBoolean("AllowSkip", paramBoolean.booleanValue()).apply();
  }
  
  public static void putPasswordUpdateMessageBody(String paramString)
  {
    getLucktasticPreferences().edit().putString("UpdateMessageBody", paramString).apply();
  }
  
  public static void putPasswordUpdateMessageHeader(String paramString)
  {
    getLucktasticPreferences().edit().putString("UpdateMessageHeader", paramString).apply();
  }
  
  public static void putRevenuePerAdViewed(Double paramDouble)
  {
    getLucktasticPreferences().edit().putLong("revenue_per_ad_viewed", Double.doubleToLongBits(paramDouble.doubleValue())).apply();
  }
  
  public static void putRevenuePerCardPlay(Double paramDouble)
  {
    getLucktasticPreferences().edit().putLong("revenue_per_card_play", Double.doubleToLongBits(paramDouble.doubleValue())).apply();
  }
  
  public static void putSalt(String paramString)
  {
    getLucktasticPermanentPreferences().edit().putString("AppSaltValue", paramString).apply();
  }
  
  public static void putShowGameCardDialog(Boolean paramBoolean)
  {
    getLucktasticPreferences().edit().putBoolean("SHOW_GAME_DIALOG", paramBoolean.booleanValue()).apply();
  }
  
  public static void putSpinWheelSound(Boolean paramBoolean)
  {
    getLucktasticPreferences().edit().putBoolean("PRIZE_WHEEL_SOUND", paramBoolean.booleanValue()).apply();
  }
  
  public static void putSpinWheelSpeed(Boolean paramBoolean)
  {
    getLucktasticPreferences().edit().putBoolean("PRIZE_WHEEL_SPEED", paramBoolean.booleanValue()).apply();
  }
  
  public static void putSpinWheelTutorialShown(Boolean paramBoolean)
  {
    getLucktasticPreferences().edit().putBoolean("PRIZE_WHEEL_TUTORIAL", paramBoolean.booleanValue()).apply();
  }
  
  public static void putTwitterAuthToken(String paramString)
  {
    getLucktasticPermanentPreferences().edit().putString("twitter_auth_token", paramString).apply();
  }
  
  public static void putTwitterAuthTokenSecret(String paramString)
  {
    getLucktasticPermanentPreferences().edit().putString("twitter_auth_token_secret", paramString).apply();
  }
  
  public static void putTwoFactorTokenB(String paramString)
  {
    getLucktasticPreferences().edit().putString("TwoFactorTokenB", paramString).apply();
  }
  
  public static void putTwoFactorTokenC(String paramString)
  {
    getLucktasticPreferences().edit().putString("TwoFactorTokenC", paramString).apply();
  }
  
  public static void putUserAgent(String paramString)
  {
    getLucktasticPermanentPreferences().edit().putString("UserAgent", paramString).apply();
  }
  
  public static void putUserIdEncryptionKey(String paramString)
  {
    getLucktasticPermanentPreferences().edit().putString("UserIdEncryptionValue", paramString).apply();
  }
  
  public static void putUserZipCode(String paramString)
  {
    getLucktasticPreferences().edit().putString("zip", paramString).apply();
  }
  
  public static void putWinnerCount(String paramString)
  {
    getLucktasticPermanentPreferences().edit().putString("key_winner_count", paramString).apply();
  }
  
  public static void putZToken(String paramString)
  {
    getLucktasticPreferences().edit().putString("ZToken", paramString).apply();
  }
  
  public static void removeGatedOppIdOpen()
  {
    getLucktasticPermanentPreferences().edit().remove("GATED_OPP_ID_OPEN").apply();
  }
  
  public static void removeInstalledPackages()
  {
    getLucktasticPermanentPreferences().edit().remove("INSTALLED_PACKAGES").apply();
  }
  
  public static void removeNetworkString()
  {
    getLucktasticPreferences().edit().remove("key_network_string").apply();
  }
  
  public static void removeOnLoginSuccess()
  {
    getLucktasticPreferences().edit().remove("OnLoginSuccess").apply();
  }
  
  public static void removeOnRegisterSuccess()
  {
    getLucktasticPreferences().edit().remove("OnRegisterSuccess").apply();
  }
  
  public static void removeOppRefreshTime()
  {
    getLucktasticPreferences().edit().remove("key_opp_refresh_time").apply();
  }
  
  public static void removeRefreshToken()
  {
    getLucktasticUnityPlayerPreferences().edit().remove("refreshToken").apply();
  }
  
  public static void removeSessionToken()
  {
    getLucktasticUnityPlayerPreferences().edit().remove("sessionToken").apply();
  }
  
  public static void removeTwitterAuthToken()
  {
    getLucktasticPermanentPreferences().edit().remove("twitter_auth_token").apply();
  }
  
  public static void removeTwitterAuthTokenSecret()
  {
    getLucktasticPermanentPreferences().edit().remove("twitter_auth_token_secret").apply();
  }
  
  public static void removeUserID()
  {
    getLucktasticUnityPlayerPreferences().edit().remove("UserId").apply();
  }
  
  public static void setTutorialShown(boolean paramBoolean)
  {
    getLucktasticPreferences().edit().putBoolean("tutorial_shown", paramBoolean).apply();
  }
  
  public static boolean tutorialShown()
  {
    return getLucktasticPreferences().getBoolean("tutorial_shown", false);
  }
  
  public static void writeReferrerSourceFromInstall(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    getLucktasticPermanentPreferences().edit().putString("SOURCE", paramString1).putString("luck_ref_id", paramString2).putString("event_id", paramString3).putString("opp_id", paramString4).putString("ref_channel", paramString5).putString("ref_sig", paramString6).putBoolean("is_ref_complete", false).putInt("ref_reward_count", 0).apply();
  }
}
