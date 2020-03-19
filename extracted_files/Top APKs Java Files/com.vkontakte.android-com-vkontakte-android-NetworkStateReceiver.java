package com.vkontakte.android;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.vkontakte.android.api.APIController;
import com.vkontakte.android.api.Callback;
import com.vkontakte.android.api.ResultlessCallback;
import com.vkontakte.android.api.VKAPIRequest;
import com.vkontakte.android.api.VKAPIRequest.VKErrorResponse;
import com.vkontakte.android.api.account.C2DMRegisterDevice;
import com.vkontakte.android.api.execute.GetWallInfo;
import com.vkontakte.android.api.execute.GetWallInfo.Result;
import com.vkontakte.android.cache.Cache;
import com.vkontakte.android.data.Analytics;
import com.vkontakte.android.data.Friends;
import com.vkontakte.android.data.Groups;
import com.vkontakte.android.data.Messages;
import com.vkontakte.android.data.PersistentAPIRequest;
import com.vkontakte.android.data.UserNotification;
import com.vkontakte.android.stickers.Stickers;
import com.vkontakte.android.utils.L;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.acra.ErrorReporter;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.mail.android.mytracker.MRMyTracker;
import ru.mail.android.mytracker.MRMyTrackerParams;
import ru.mail.android.mytracker.providers.CustomParamsDataProvider;

public class NetworkStateReceiver
  extends BroadcastReceiver
{
  public static final String ACTION_GROUPS_UPDATED = "com.vkontakte.android.GROUPS_UPDATED";
  private static final List<String> FORCED_SYSTEM_APPS = Arrays.asList(new String[] { "com.vkontakte.android", "com.instagram.android", "com.facebook.katana", "com.facebook.orca" });
  private static final String LAST_GET_NOTIFY = "last_get_notify";
  private static final String LAST_GET_NOTIFY_APP = "last_get_notify_app";
  public static final int TIME_DELAY = 7200;
  private static final ConnectivityManager cm = (ConnectivityManager)VKApplication.context.getSystemService("connectivity");
  private static String currentNetworkType;
  private static final String[] highSpeedTypes;
  public static boolean isConnected = false;
  private static final int[] sl;
  private static int tries;
  public static boolean userInfoUpdated = false;
  
  static
  {
    tries = 5;
    sl = new int[] { 1500, 3000, 6000, 12000, 24000 };
    currentNetworkType = null;
    highSpeedTypes = new String[] { "3g", "lte", "wifi", "ethernet" };
  }
  
  public NetworkStateReceiver() {}
  
  public static void getAppNotifications(Context paramContext)
  {
    int i = 1;
    int j = paramContext.getSharedPreferences(null, 0).getInt("last_get_notify_app", 0);
    if (System.currentTimeMillis() / 1000L - j < 7200L) {}
    while (Global.accessToken == null) {
      return;
    }
    for (;;)
    {
      try
      {
        if ((paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).applicationInfo.flags & 0x1) == 1)
        {
          VKAPIRequest localVKAPIRequest = new VKAPIRequest("internal.getUserNotifications").param("device", Build.MODEL).param("vendor", Build.MANUFACTURER);
          if (i == 0) {
            break label226;
          }
          str = "1";
          localVKAPIRequest.param("system", str).param("os", Build.VERSION.SDK_INT + "," + Build.VERSION.RELEASE).param("app_version", paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode).param("locale", System.getProperty("user.language")).param("ads_device_id", Analytics.getDeviceID()).param("fields", "photo_100,photo_50").param("extended", 1).param("photo_sizes", 1).setCallback(new Callback()
          {
            public void fail(VKAPIRequest.VKErrorResponse paramAnonymousVKErrorResponse) {}
            
            public void success(JSONObject paramAnonymousJSONObject)
            {
              this.val$context.getSharedPreferences(null, 0).edit().putInt("last_get_notify_app", (int)(System.currentTimeMillis() / 1000L)).commit();
              try
              {
                Object localObject = UserNotification.parse(paramAnonymousJSONObject.optJSONObject("response"));
                if (localObject != null)
                {
                  paramAnonymousJSONObject = null;
                  Iterator localIterator = ((List)localObject).iterator();
                  while (localIterator.hasNext())
                  {
                    localObject = (UserNotification)localIterator.next();
                    if ("alert".equals(((UserNotification)localObject).type)) {
                      paramAnonymousJSONObject = (JSONObject)localObject;
                    }
                  }
                  if (paramAnonymousJSONObject != null)
                  {
                    localObject = new Intent(this.val$context, NotificationActivity.class);
                    ((Intent)localObject).putExtra("user_notification", paramAnonymousJSONObject);
                    ((Intent)localObject).addFlags(268435456);
                    this.val$context.startActivity((Intent)localObject);
                  }
                }
                return;
              }
              catch (Exception paramAnonymousJSONObject)
              {
                Log.w("vk", paramAnonymousJSONObject);
              }
            }
          }).exec();
          return;
        }
      }
      catch (Exception paramContext)
      {
        L.e(paramContext);
        return;
      }
      i = 0;
      continue;
      label226:
      String str = "0";
    }
  }
  
  public static String getNetworkType()
  {
    NetworkInfo localNetworkInfo = cm.getActiveNetworkInfo();
    if (localNetworkInfo == null) {
      return "none";
    }
    if (localNetworkInfo.getType() == 0)
    {
      if (("GPRS".equals(localNetworkInfo.getSubtypeName())) || ("EDGE".equals(localNetworkInfo.getSubtypeName()))) {
        return "edge";
      }
      if ("LTE".equals(localNetworkInfo.getSubtypeName())) {
        return "lte";
      }
      return "3g";
    }
    if (localNetworkInfo.getType() == 1) {
      return "wifi";
    }
    if (localNetworkInfo.getType() == 9) {
      return "ethernet";
    }
    return "other";
  }
  
  public static void getNotifications(Context paramContext)
  {
    int i = 1;
    int j = paramContext.getSharedPreferences(null, 0).getInt("last_get_notify", 0);
    if (System.currentTimeMillis() / 1000L - j < 3600L) {}
    while (Global.accessToken == null) {
      return;
    }
    for (;;)
    {
      try
      {
        if ((paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).applicationInfo.flags & 0x1) == 1)
        {
          VKAPIRequest localVKAPIRequest = new VKAPIRequest("internal.getNotifications").param("device", Build.MODEL).param("vendor", Build.MANUFACTURER);
          if (i == 0) {
            break label205;
          }
          str = "1";
          localVKAPIRequest.param("system", str).param("os", Build.VERSION.SDK_INT + "," + Build.VERSION.RELEASE).param("app_version", paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode).param("locale", System.getProperty("user.language")).param("ads_device_id", Analytics.getDeviceID()).setCallback(new Callback()
          {
            public void fail(VKAPIRequest.VKErrorResponse paramAnonymousVKErrorResponse) {}
            
            public void success(JSONObject paramAnonymousJSONObject)
            {
              this.val$context.getSharedPreferences(null, 0).edit().putInt("last_get_notify", (int)(System.currentTimeMillis() / 1000L)).commit();
              Intent localIntent;
              try
              {
                paramAnonymousJSONObject = paramAnonymousJSONObject.getJSONArray("response");
                if (paramAnonymousJSONObject.length() > 0)
                {
                  paramAnonymousJSONObject = paramAnonymousJSONObject.getJSONObject(paramAnonymousJSONObject.length() - 1);
                  localIntent = new Intent(this.val$context, NotificationActivity.class);
                  Iterator localIterator = paramAnonymousJSONObject.keys();
                  while (localIterator.hasNext())
                  {
                    String str = (String)localIterator.next();
                    localIntent.putExtra(str, paramAnonymousJSONObject.getString(str));
                  }
                }
                return;
              }
              catch (Exception paramAnonymousJSONObject)
              {
                Log.w("vk", paramAnonymousJSONObject);
              }
              localIntent.addFlags(268435456);
              this.val$context.startActivity(localIntent);
            }
          }).exec();
          return;
        }
      }
      catch (Exception paramContext)
      {
        L.e(paramContext);
        return;
      }
      i = 0;
      continue;
      label205:
      String str = "0";
    }
  }
  
  public static boolean isConnected()
  {
    NetworkInfo localNetworkInfo = cm.getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnected());
  }
  
  public static boolean isHighSpeed()
  {
    if ((!PreferenceManager.getDefaultSharedPreferences(VKApplication.context).getBoolean("bigImagesMobile", true)) && (isMobile())) {}
    for (;;)
    {
      return false;
      if (currentNetworkType == null) {
        currentNetworkType = getNetworkType();
      }
      String[] arrayOfString = highSpeedTypes;
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        if (arrayOfString[i].equals(currentNetworkType)) {
          return true;
        }
        i += 1;
      }
    }
  }
  
  public static boolean isMobile()
  {
    NetworkInfo localNetworkInfo = cm.getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.getType() == 0);
  }
  
  public static boolean isWifi()
  {
    NetworkInfo localNetworkInfo = cm.getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.getType() == 1);
  }
  
  private static void updateFriendlist(Context paramContext)
  {
    if ((Global.uid > 0) && (Global.accessToken != null))
    {
      Friends.reload(true);
      Groups.reload(true);
    }
  }
  
  public static void updateInfo(Context paramContext)
  {
    if (!userInfoUpdated)
    {
      Log.v("vk", "about to update user info...");
      if (!VKApplication.context.getSharedPreferences(null, 0).contains("uid")) {
        Log.v("vk", "not logged in.");
      }
    }
    else
    {
      return;
    }
    if (!Global.inited)
    {
      Global.displayDensity = paramContext.getResources().getDisplayMetrics().density;
      SharedPreferences localSharedPreferences = paramContext.getApplicationContext().getSharedPreferences(null, 0);
      if (localSharedPreferences.contains("sid"))
      {
        Global.uid = localSharedPreferences.getInt("uid", 0);
        Global.accessToken = localSharedPreferences.getString("sid", null);
        Global.secret = localSharedPreferences.getString("secret", null);
        ErrorReporter.getInstance().putCustomData("vk_uid", Global.uid + "");
      }
      Global.inited = true;
    }
    if (!VKApplication.context.getSharedPreferences(null, 0).contains("new_auth"))
    {
      new Thread(NetworkStateReceiver..Lambda.2.lambdaFactory$(paramContext)).start();
      return;
    }
    updateUserInfo(paramContext);
  }
  
  private static void updateUserInfo(Context paramContext)
  {
    if (Global.uid > 0)
    {
      new C2DMRegisterDevice(paramContext.getSharedPreferences("gcm", 0).getString("reg", null)).exec();
      new GetWallInfo(Global.uid).setCallback(new Callback()
      {
        public void fail(VKAPIRequest.VKErrorResponse paramAnonymousVKErrorResponse)
        {
          try
          {
            Thread.sleep(NetworkStateReceiver.sl[(5 - NetworkStateReceiver.tries)]);
            NetworkStateReceiver.access$310();
            if (NetworkStateReceiver.tries > 0)
            {
              NetworkStateReceiver.updateUserInfo(this.val$context);
              return;
            }
          }
          catch (Exception paramAnonymousVKErrorResponse)
          {
            for (;;)
            {
              L.e(paramAnonymousVKErrorResponse);
            }
            NetworkStateReceiver.access$302(5);
          }
        }
        
        public void success(GetWallInfo.Result paramAnonymousResult)
        {
          NetworkStateReceiver.userInfoUpdated = true;
          Object localObject1 = this.val$context.getSharedPreferences(null, 0);
          Object localObject2 = PreferenceManager.getDefaultSharedPreferences(VKApplication.context);
          TimeUtils.setServerTime(paramAnonymousResult.time);
          ((SharedPreferences)localObject1).edit().putInt("usercountry", paramAnonymousResult.country).putString("username", paramAnonymousResult.name).putString("userphoto", paramAnonymousResult.photo).putString("userstatus", paramAnonymousResult.status).putInt("time_diff", 0).putBoolean("export_twitter_avail", paramAnonymousResult.exportTwi).putBoolean("export_facebook_avail", paramAnonymousResult.exportFb).putBoolean("usersex", paramAnonymousResult.f).putInt("intro", paramAnonymousResult.intro).putString("ads_stoplist", new JSONArray(paramAnonymousResult.adsFilter).toString()).putBoolean("allow_buy_votes", paramAnonymousResult.allowBuyVotes).putString("support_url", paramAnonymousResult.supportUrl).putBoolean("use_vigo", paramAnonymousResult.useVigo).putInt("vigo_connect_timeout", paramAnonymousResult.vigoConnectTimeout).putInt("vigo_read_timeout", paramAnonymousResult.vigoReadTimeout).apply();
          ((SharedPreferences)localObject2).edit().putBoolean("gif_autoplay_available", paramAnonymousResult.gifAutoplayAvailable).apply();
          localObject1 = MRMyTracker.getTrackerParams().getCustomParams();
          ((CustomParamsDataProvider)localObject1).setVKId(Global.uid + "");
          if (!TextUtils.isEmpty(paramAnonymousResult.bdate))
          {
            localObject2 = paramAnonymousResult.bdate.split("\\.");
            if (localObject2.length == 3) {
              ((CustomParamsDataProvider)localObject1).setAge(TimeUtils.getAge(Integer.parseInt(localObject2[0]), Integer.parseInt(localObject2[1]), Integer.parseInt(localObject2[2])));
            }
          }
          if (paramAnonymousResult.f) {}
          for (int i = 2;; i = 1)
          {
            ((CustomParamsDataProvider)localObject1).setGender(i);
            MRMyTracker.getTrackerParams().setTrackingLocationEnabled(PreferenceManager.getDefaultSharedPreferences(this.val$context).getBoolean("mytrackerLocationCrapEnabled", true));
            localObject1 = Stickers.get();
            ((Stickers)localObject1).setNumNewItems(paramAnonymousResult.numNewStickers);
            ((Stickers)localObject1).setNumUpdates(paramAnonymousResult.stickersUpdates);
            NetworkStateReceiver.updateFriendlist(this.val$context);
            if (paramAnonymousResult.needUpdateGoogleNow) {
              GoogleNow.updateTokenAsync();
            }
            if (System.currentTimeMillis() - this.val$context.getSharedPreferences(null, 0).getLong("last_sent_apps", 0L) > 604800000L) {
              try
              {
                localObject1 = this.val$context.getPackageManager().getInstalledPackages(0);
                paramAnonymousResult = new JSONArray();
                localObject1 = ((List)localObject1).iterator();
                while (((Iterator)localObject1).hasNext())
                {
                  localObject2 = (PackageInfo)((Iterator)localObject1).next();
                  if ((((PackageInfo)localObject2).applicationInfo.enabled) && (((((PackageInfo)localObject2).applicationInfo.flags & 0x1) == 0) || (NetworkStateReceiver.FORCED_SYSTEM_APPS.contains(((PackageInfo)localObject2).packageName))))
                  {
                    JSONObject localJSONObject = new JSONObject();
                    localJSONObject.put("package", ((PackageInfo)localObject2).packageName);
                    localJSONObject.put("installed", (int)(((PackageInfo)localObject2).firstInstallTime / 1000L));
                    localJSONObject.put("updated", (int)(((PackageInfo)localObject2).lastUpdateTime / 1000L));
                    if ((((PackageInfo)localObject2).applicationInfo.flags & 0x1) > 0) {
                      localJSONObject.put("system", 1);
                    }
                    localJSONObject.put("version", ((PackageInfo)localObject2).versionName);
                    paramAnonymousResult.put(localJSONObject);
                  }
                }
                return;
              }
              catch (Exception paramAnonymousResult)
              {
                L.e(paramAnonymousResult);
              }
            }
          }
          new VKAPIRequest("stats.trackInstalledApps")
          {
            public Boolean parse(JSONObject paramAnonymous2JSONObject)
              throws Exception
            {
              return Boolean.valueOf(true);
            }
          }.param("apps", paramAnonymousResult.toString()).setCallback(new ResultlessCallback()
          {
            public void success()
            {
              NetworkStateReceiver.3.this.val$context.getSharedPreferences(null, 0).edit().putLong("last_sent_apps", System.currentTimeMillis()).apply();
            }
          }).exec();
        }
      }).exec();
    }
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ("android.intent.action.TIME_SET".equals(paramIntent.getAction())) {
      updateUserInfo(paramContext);
    }
    label138:
    label523:
    do
    {
      return;
      Object localObject3 = PreferenceManager.getDefaultSharedPreferences(paramContext);
      Object localObject1 = PendingIntent.getBroadcast(paramContext, 0, new Intent(paramContext, BirthdayBroadcastReceiver.class), 134217728);
      Object localObject2 = (AlarmManager)paramContext.getSystemService("alarm");
      if (((SharedPreferences)localObject3).getBoolean("notificationsBDays", true))
      {
        localObject3 = Calendar.getInstance();
        ((Calendar)localObject3).set(5, ((Calendar)localObject3).get(5) + 1);
        ((Calendar)localObject3).set(11, 4);
        ((Calendar)localObject3).set(12, 0);
        ((Calendar)localObject3).set(13, 0);
        ((Calendar)localObject3).set(14, 0);
        ((AlarmManager)localObject2).setRepeating(1, ((Calendar)localObject3).getTimeInMillis(), 86400000L, (PendingIntent)localObject1);
        if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(paramIntent.getAction())) {
          continue;
        }
        if ((paramIntent.getBooleanExtra("noConnectivity", false)) || (!isConnected())) {
          break label523;
        }
      }
      for (boolean bool = true;; bool = false)
      {
        currentNetworkType = getNetworkType();
        if (APIController.API_DEBUG) {
          Log.i("vk", "!!!!!!!!!! NETWORK CHANGED to " + currentNetworkType);
        }
        LongPollService.doBroadcastStateChanged();
        if (isConnected == bool) {
          break;
        }
        isConnected = bool;
        if (!isConnected) {
          break;
        }
        if ((Global.longPoll == null) && (AppStateTracker.getCurrentActivity() != null))
        {
          paramIntent = new Intent(VKApplication.context, LongPollService.class);
          VKApplication.context.startService(paramIntent);
        }
        if (paramContext.getSharedPreferences(null, 0).contains("need_update_gcm")) {
          new C2DMRegisterDevice(paramContext.getSharedPreferences("gcm", 0).getString("reg", null)).setCallback(new ResultlessCallback((Context)null)
          {
            public void success()
            {
              VKApplication.context.getSharedPreferences(null, 0).edit().remove("need_update_gcm").commit();
            }
          }).exec();
        }
        Log.i("vk", "Before update info");
        updateInfo(paramContext);
        paramIntent = Cache.getResendableMessages();
        Log.i("vk", "Before resend " + paramIntent);
        paramIntent = paramIntent.iterator();
        while (paramIntent.hasNext())
        {
          localObject2 = (Message)paramIntent.next();
          localObject1 = Messages.send(((Message)localObject2).peer, ((Message)localObject2).text, ((Message)localObject2).attachments, ((Message)localObject2).fwdMessages, ((Message)localObject2).id);
          Messages.add((Message)localObject1, null, null);
          localObject3 = new Intent("com.vkontakte.android.MESSAGE_DELETED");
          ((Intent)localObject3).putExtra("msg_id", ((Message)localObject2).id);
          paramContext.sendBroadcast((Intent)localObject3, "com.vkontakte.android.permission.ACCESS_DATA");
          localObject2 = new Intent("com.vkontakte.android.NEW_MESSAGE");
          ((Intent)localObject2).putExtra("peer_id", ((Message)localObject1).peer);
          ((Intent)localObject2).putExtra("message", (Parcelable)localObject1);
          paramContext.sendBroadcast((Intent)localObject2, "com.vkontakte.android.permission.ACCESS_DATA");
        }
        ((AlarmManager)localObject2).cancel((PendingIntent)localObject1);
        break label138;
      }
      if (paramContext.getSharedPreferences(null, 0).contains("pending_msg_notification")) {
        GCMBroadcastReceiver.updateStateAndShowNotification(paramContext.getSharedPreferences(null, 0).getInt("pending_msg_notification", 0));
      }
      new Thread(NetworkStateReceiver..Lambda.1.lambdaFactory$(this)).start();
      return;
    } while ((!"android.intent.action.TIME_SET".equals(paramIntent.getAction())) && (!"android.intent.action.TIMEZONE_CHANGED".equals(paramIntent.getAction())));
    TimeUtils.resyncOffset();
  }
}
