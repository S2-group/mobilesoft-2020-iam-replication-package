package com.tutotoons.ane.AirTutoToons;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import com.tutotoons.ane.AirTutoToons.ads.TutoAds;
import com.tutotoons.ane.AirTutoToons.receivers.NotificationReceiver;
import com.tutotoons.ane.AirTutoToons.utils.Data;
import com.tutotoons.ane.AirTutoToons.utils.InstallTracker;
import com.tutotoons.ane.AirTutoToons.utils.Logger;
import com.tutotoons.ane.AirTutoToons.utils.Notifications;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NativeCalls
{
  public NativeCalls() {}
  
  public static boolean AppInstalled(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 1);
      return true;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return false;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;) {}
    }
  }
  
  public static boolean AutoCacheDelayFailed(Context paramContext, int paramInt)
  {
    TutoAds.setAutoCacheDelayFailed(paramInt);
    return true;
  }
  
  public static boolean AutoCacheDelayIdle(Context paramContext, int paramInt)
  {
    TutoAds.setAutoCacheDelayIdle(paramInt);
    return true;
  }
  
  public static boolean AutoCacheDelaySuccess(Context paramContext, int paramInt)
  {
    TutoAds.setAutoCacheDelaySuccess(paramInt);
    return true;
  }
  
  public static boolean AutoCacheInterstitial(Context paramContext, boolean paramBoolean)
  {
    TutoAds.autoPreLoadInterstitials(paramContext, paramBoolean);
    return true;
  }
  
  public static boolean AutoCacheInterstitialVideo(Context paramContext, boolean paramBoolean)
  {
    TutoAds.autoPreLoadInterstitialVideo(paramContext, paramBoolean);
    return true;
  }
  
  public static boolean AutoCachePanel(Context paramContext, boolean paramBoolean)
  {
    TutoAds.autoPreLoadPanel(paramContext, paramBoolean);
    return true;
  }
  
  public static boolean AutoCachePlayable(Context paramContext, boolean paramBoolean)
  {
    TutoAds.autoPreLoadPlayable(paramContext, paramBoolean);
    return true;
  }
  
  public static boolean AutoCacheRewardedVideo(Context paramContext, boolean paramBoolean)
  {
    TutoAds.autoPreLoadRewardedVideo(paramContext, paramBoolean);
    return true;
  }
  
  public static boolean CancelAllNotifications(Context paramContext)
  {
    Iterator localIterator = Notifications.keywords.entrySet().iterator();
    while (localIterator.hasNext()) {
      CancelNotificationByID(paramContext, Integer.parseInt((String)((Map.Entry)localIterator.next()).getKey()));
    }
    return true;
  }
  
  public static boolean CancelNotificationByID(Context paramContext, int paramInt)
  {
    AlarmManager localAlarmManager = (AlarmManager)paramContext.getSystemService("alarm");
    Intent localIntent = new Intent("com.tutotoons.ane.AirTutoToons.PUSH_NOTIFICATION");
    localIntent.setClass(paramContext, NotificationReceiver.class);
    paramContext = PendingIntent.getBroadcast(paramContext, paramInt, localIntent, 536870912);
    if (paramContext != null)
    {
      localAlarmManager.cancel(paramContext);
      paramContext.cancel();
      return true;
    }
    return false;
  }
  
  public static boolean ConnectionSpeedLimit(Context paramContext, int paramInt)
  {
    TutoAds.setConnectionLimit(paramInt);
    return true;
  }
  
  public static int GetActiveNotificationCount(Context paramContext)
  {
    int i = 0;
    Intent localIntent = new Intent("com.tutotoons.ane.AirTutoToons.PUSH_NOTIFICATION");
    localIntent.setClass(paramContext, NotificationReceiver.class);
    Iterator localIterator = Notifications.keywords.entrySet().iterator();
    while (localIterator.hasNext()) {
      if (PendingIntent.getBroadcast(paramContext, Integer.parseInt((String)((Map.Entry)localIterator.next()).getKey()), localIntent, 536870912) != null) {
        i += 1;
      }
    }
    return i;
  }
  
  public static String GetActiveNotificationList(Context paramContext)
  {
    String str1 = "";
    int i = 1;
    Intent localIntent = new Intent("com.tutotoons.ane.AirTutoToons.PUSH_NOTIFICATION");
    localIntent.setClass(paramContext, NotificationReceiver.class);
    Iterator localIterator = Notifications.keywords.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (PendingIntent.getBroadcast(paramContext, Integer.parseInt((String)localEntry.getKey()), localIntent, 536870912) != null)
      {
        String str2 = str1;
        if (i == 0) {
          str2 = str1 + ",";
        }
        str1 = str2 + (String)localEntry.getKey();
        i = 0;
      }
    }
    return str1;
  }
  
  public static String GetAppReferrer(Context paramContext, String paramString)
  {
    Object localObject = "failed_to_get_referrer_from_shared_preferences";
    try
    {
      paramContext = PreferenceManager.getDefaultSharedPreferences(paramContext).getString(paramString, "did_not_receive_referrer_from_GPS");
      localObject = paramContext;
      Logger.d("AirTutoToons", "NativeCalls:" + paramContext + " " + paramString);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      Logger.d("AirTutoToons", "NativeCalls:" + (String)localObject);
      paramContext.printStackTrace();
    }
    return localObject;
  }
  
  public static int GetNotificationID(Context paramContext)
  {
    return Data.notification_id;
  }
  
  public static JSONArray GetTutoApps(Context paramContext)
  {
    JSONArray localJSONArray = new JSONArray();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    if (i < paramContext.size())
    {
      Object localObject = (PackageInfo)paramContext.get(i);
      if (((PackageInfo)localObject).versionName == null) {}
      for (;;)
      {
        i += 1;
        break;
        String str1 = ((PackageInfo)localObject).packageName;
        String str2 = ((PackageInfo)localObject).versionName;
        if ((str1.indexOf("com.tutotoons.app") != -1) || (str1.indexOf("com.tutoplay.app") != -1) || (str1.indexOf("com.cuteandtinybabygames") != -1))
        {
          localObject = new JSONObject();
          try
          {
            ((JSONObject)localObject).put("packageName", str1);
            ((JSONObject)localObject).put("version", str2);
            Logger.d("AirTutoToons", "packageName " + str1 + " version " + str2);
            localJSONArray.put(localObject);
          }
          catch (JSONException localJSONException)
          {
            for (;;)
            {
              localJSONException.printStackTrace();
            }
          }
        }
      }
    }
    return localJSONArray;
  }
  
  public static boolean HidePanel(Activity paramActivity, Context paramContext, boolean paramBoolean)
  {
    TutoAds.HidePanel(paramActivity, paramContext, paramBoolean);
    return true;
  }
  
  public static boolean InitAdolf(Context paramContext)
  {
    TutoAds.Init(paramContext);
    return true;
  }
  
  public static boolean InterstitialCacheLimit(Context paramContext, int paramInt)
  {
    TutoAds.setInterstitialCacheLimit(paramInt);
    return true;
  }
  
  public static boolean InterstitialVideoCacheLimit(Context paramContext, int paramInt)
  {
    TutoAds.setInterstitialVideoCacheLimit(paramInt);
    return true;
  }
  
  public static boolean LoadInterstitial(Context paramContext)
  {
    TutoAds.LoadInterstitial(paramContext);
    return true;
  }
  
  public static boolean LoadInterstitialVideo(Context paramContext)
  {
    TutoAds.LoadInterstitialVideo(paramContext);
    return true;
  }
  
  public static boolean LoadPanel(Activity paramActivity, Context paramContext)
  {
    TutoAds.LoadPanel(paramActivity, paramContext);
    return true;
  }
  
  public static boolean LoadPlayable(Context paramContext)
  {
    TutoAds.LoadPlayable(paramContext);
    return true;
  }
  
  public static boolean LoadRewardedVideo(Context paramContext)
  {
    TutoAds.LoadRewardedVideo(paramContext);
    return true;
  }
  
  public static boolean PanelCacheLimit(Context paramContext, int paramInt)
  {
    TutoAds.setPanelCacheLimit(paramInt);
    return true;
  }
  
  public static boolean PlayableCacheLimit(Context paramContext, int paramInt)
  {
    TutoAds.setPlayableCacheLimit(paramInt);
    return true;
  }
  
  public static boolean PlayableClicked(Context paramContext, String paramString)
  {
    try
    {
      TutoAds.PlayableClicked(new JSONObject(paramString));
      return true;
    }
    catch (JSONException paramContext)
    {
      for (;;)
      {
        Logger.d("AirTutoToons", "NativeCalls:TutoAds:PlayableClicked:JSONException");
      }
    }
  }
  
  public static boolean PlayableCompleted(Context paramContext, String paramString)
  {
    try
    {
      TutoAds.PlayableCompleted(new JSONObject(paramString));
      return true;
    }
    catch (JSONException paramContext)
    {
      for (;;)
      {
        Logger.d("AirTutoToons", "NativeCalls:TutoAds:PlayableCompleted:JSONException");
      }
    }
  }
  
  public static boolean PlayableRelease(Context paramContext, String paramString)
  {
    try
    {
      TutoAds.PlayableRelease(new JSONObject(paramString));
      return true;
    }
    catch (JSONException paramContext)
    {
      for (;;)
      {
        Logger.d("AirTutoToons", "NativeCalls:TutoAds:PlayableRelease:JSONException");
      }
    }
  }
  
  public static boolean PlayableReportError(Context paramContext, String paramString)
  {
    try
    {
      TutoAds.PlayableReportError(new JSONObject(paramString));
      return true;
    }
    catch (JSONException paramContext)
    {
      for (;;)
      {
        Logger.d("AirTutoToons", "NativeCalls:TutoAds:PlayableReportError:JSONException");
      }
    }
  }
  
  public static boolean RewardedVideoCacheLimit(Context paramContext, int paramInt)
  {
    TutoAds.setRewardedVideoCacheLimit(paramInt);
    return true;
  }
  
  public static boolean ScheduleNotification(Context paramContext, Activity paramActivity, int paramInt, String paramString1, String paramString2, long paramLong, String paramString3, String paramString4, String paramString5, String paramString6)
  {
    paramLong *= 1000L;
    if ((paramString1 != null) && (paramString2 != null) && (paramLong > 0L))
    {
      paramActivity = new Intent("com.tutotoons.ane.AirTutoToons.PUSH_NOTIFICATION");
      paramActivity.setClass(paramContext, NotificationReceiver.class);
      paramActivity.putExtra("id", paramInt);
      paramActivity.putExtra("title", paramString1);
      paramActivity.putExtra("message", paramString2);
      paramActivity.putExtra("ga_id", paramString3);
      paramActivity.putExtra("cid", paramString4);
      paramActivity.putExtra("tracker_path", paramString5);
      paramActivity.putExtra("tracker_prefix", paramString6);
      paramActivity.putExtra("version_number", Data.getAppVersion(paramContext));
      paramActivity.putExtra("config_url", Data.config_url);
      paramActivity = PendingIntent.getBroadcast(paramContext, paramInt, paramActivity, 134217728);
      Logger.d("AirTutoToons", "NativeCalls:ScheduleNotification " + SystemClock.elapsedRealtime() + paramLong);
      ((AlarmManager)paramContext.getSystemService("alarm")).set(2, SystemClock.elapsedRealtime() + paramLong, paramActivity);
      return true;
    }
    return false;
  }
  
  public static boolean SetCampaignCappingState(boolean paramBoolean)
  {
    com.tutotoons.ane.AirTutoToons.ads.capping.Capping.campaign_capping_enabled = Boolean.valueOf(paramBoolean);
    return true;
  }
  
  public static boolean SetCampaignInRowCappingState(boolean paramBoolean)
  {
    com.tutotoons.ane.AirTutoToons.ads.capping.Capping.campaigns_in_a_row_capping_enabled = Boolean.valueOf(paramBoolean);
    return true;
  }
  
  public static boolean SetCampaignMaxLoadsCappingState(boolean paramBoolean)
  {
    com.tutotoons.ane.AirTutoToons.ads.capping.Capping.max_campaigns_per_session_capping_enabled = Boolean.valueOf(paramBoolean);
    return true;
  }
  
  public static boolean SetCampaignsCappedByAdType(boolean paramBoolean)
  {
    com.tutotoons.ane.AirTutoToons.ads.capping.Capping.campaigns_capped_by_ad_type = Boolean.valueOf(paramBoolean);
    return true;
  }
  
  public static boolean SetCampaignsInRowLimit(int paramInt)
  {
    if (paramInt <= 1)
    {
      com.tutotoons.ane.AirTutoToons.ads.capping.Capping.campaign_in_a_row = 1;
      return true;
    }
    com.tutotoons.ane.AirTutoToons.ads.capping.Capping.campaign_in_a_row = paramInt;
    return true;
  }
  
  public static boolean SetCappingPerCampaign(int paramInt)
  {
    if (paramInt <= 1)
    {
      com.tutotoons.ane.AirTutoToons.ads.capping.Capping.max_campaign_loads = 1;
      return true;
    }
    com.tutotoons.ane.AirTutoToons.ads.capping.Capping.max_campaign_loads = paramInt;
    return true;
  }
  
  public static boolean SetCappingPerCreative(int paramInt)
  {
    if (paramInt <= 1)
    {
      com.tutotoons.ane.AirTutoToons.ads.capping.Capping.max_creative_loads = 1;
      return true;
    }
    com.tutotoons.ane.AirTutoToons.ads.capping.Capping.max_creative_loads = paramInt;
    return true;
  }
  
  public static boolean SetCappingPerGame(int paramInt)
  {
    if (paramInt <= 1)
    {
      com.tutotoons.ane.AirTutoToons.ads.capping.Capping.max_game_loads = 1;
      return true;
    }
    com.tutotoons.ane.AirTutoToons.ads.capping.Capping.max_game_loads = paramInt;
    return true;
  }
  
  public static boolean SetClientId(Context paramContext, String paramString)
  {
    Data.client_id = paramString;
    return true;
  }
  
  public static boolean SetCloseableVideo(Context paramContext, boolean paramBoolean)
  {
    TutoAds.setCloseableVideo(Boolean.valueOf(paramBoolean));
    return true;
  }
  
  public static Boolean SetConfigUrl(Context paramContext, String paramString)
  {
    Data.config_url = paramString;
    return Boolean.valueOf(true);
  }
  
  public static boolean SetCreativeCappedByAdType(boolean paramBoolean)
  {
    com.tutotoons.ane.AirTutoToons.ads.capping.Capping.creatives_capped_by_ad_type = Boolean.valueOf(paramBoolean);
    return true;
  }
  
  public static boolean SetCreativeCappingState(boolean paramBoolean)
  {
    com.tutotoons.ane.AirTutoToons.ads.capping.Capping.creative_capping_enabled = Boolean.valueOf(paramBoolean);
    return true;
  }
  
  public static boolean SetCreativeInRowCappingState(boolean paramBoolean)
  {
    com.tutotoons.ane.AirTutoToons.ads.capping.Capping.creative_in_a_row_capping_enabled = Boolean.valueOf(paramBoolean);
    return true;
  }
  
  public static boolean SetCreativeInRowLimit(int paramInt)
  {
    if (paramInt <= 1)
    {
      com.tutotoons.ane.AirTutoToons.ads.capping.Capping.creative_in_a_row = 1;
      return true;
    }
    com.tutotoons.ane.AirTutoToons.ads.capping.Capping.creative_in_a_row = paramInt;
    return true;
  }
  
  public static boolean SetCreativeMaxLoadsCappingState(boolean paramBoolean)
  {
    com.tutotoons.ane.AirTutoToons.ads.capping.Capping.max_creative_per_session_capping_enabled = Boolean.valueOf(paramBoolean);
    return true;
  }
  
  public static boolean SetGameCappingState(boolean paramBoolean)
  {
    com.tutotoons.ane.AirTutoToons.ads.capping.Capping.game_capping_enabled = Boolean.valueOf(paramBoolean);
    return true;
  }
  
  public static boolean SetGameInRowCappingState(boolean paramBoolean)
  {
    com.tutotoons.ane.AirTutoToons.ads.capping.Capping.game_in_a_row_capping_enabled = Boolean.valueOf(paramBoolean);
    return true;
  }
  
  public static boolean SetGameInRowLimit(int paramInt)
  {
    if (paramInt <= 1)
    {
      com.tutotoons.ane.AirTutoToons.ads.capping.Capping.games_in_a_row = 1;
      return true;
    }
    com.tutotoons.ane.AirTutoToons.ads.capping.Capping.games_in_a_row = paramInt;
    return true;
  }
  
  public static boolean SetGameMaxLoadsCappingState(boolean paramBoolean)
  {
    com.tutotoons.ane.AirTutoToons.ads.capping.Capping.max_game_per_session_capping_enabled = Boolean.valueOf(paramBoolean);
    return true;
  }
  
  public static boolean SetGamesCappedByAdType(boolean paramBoolean)
  {
    com.tutotoons.ane.AirTutoToons.ads.capping.Capping.games_capped_by_ad_type = Boolean.valueOf(paramBoolean);
    return true;
  }
  
  public static boolean SetGoogleAnalyticsId(Context paramContext, String paramString)
  {
    Data.google_analytics_id = paramString;
    return true;
  }
  
  public static boolean SetInterstitialVideoDuration(int paramInt)
  {
    TutoAds.setInterstitialVideoDuration(paramInt);
    return true;
  }
  
  public static boolean ShareImage(Activity paramActivity, String paramString1, String paramString2, String paramString3)
  {
    if (paramString1 == null) {}
    while (!new File(paramString1).exists()) {
      return false;
    }
    try
    {
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.setType("image/*");
      localIntent.putExtra("android.intent.extra.STREAM", Uri.parse("file://" + paramString1));
      if (paramString3.length() > 0) {
        localIntent.putExtra("android.intent.extra.TEXT", paramString3);
      }
      paramActivity.startActivity(Intent.createChooser(localIntent, paramString2));
      return true;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
    }
    return false;
  }
  
  public static boolean ShowInterstitial(Context paramContext)
  {
    TutoAds.ShowInterstitial(paramContext);
    return true;
  }
  
  public static boolean ShowInterstitialVideo(Context paramContext)
  {
    TutoAds.ShowInterstitialVideo(paramContext);
    return true;
  }
  
  public static boolean ShowPanel(Activity paramActivity, Context paramContext, boolean paramBoolean)
  {
    TutoAds.ShowPanel(paramActivity, paramContext, paramBoolean);
    return true;
  }
  
  public static boolean ShowRewardedVideo(Context paramContext)
  {
    TutoAds.ShowRewardedVideo(paramContext);
    return true;
  }
  
  public static boolean TrackInstall(Context paramContext)
  {
    InstallTracker.trackInstall(paramContext);
    return true;
  }
}
