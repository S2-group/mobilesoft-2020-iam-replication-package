package com.onesignal;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Base64;
import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OneSignal
{
  private static final long MIN_ON_SESSION_TIME = 30L;
  public static final String VERSION = "030903";
  static String a;
  private static int androidParamsReties = 0;
  private static JSONObject awl;
  private static boolean awlFired;
  static Context b;
  static boolean c;
  private static OSEmailSubscriptionState currentEmailSubscriptionState;
  private static OSPermissionState currentPermissionState;
  private static OSSubscriptionState currentSubscriptionState;
  static ExecutorService d;
  private static int deviceType;
  static AtomicLong e;
  private static String emailId;
  private static OneSignal.EmailUpdateHandler emailLogoutHandler;
  private static OSObservable<OSEmailSubscriptionObserver, OSEmailSubscriptionStateChanges> emailSubscriptionStateChangesObserver;
  private static OneSignal.EmailUpdateHandler emailUpdateHandler;
  static boolean f;
  private static boolean foreground;
  static OneSignal.Builder g;
  private static boolean getTagsCall;
  static boolean h;
  static boolean i;
  private static OneSignal.IAPUpdateJob iapUpdateJob;
  private static OneSignal.IdsAvailableHandler idsAvailableHandler;
  static DelayedConsentInitializationParameters j;
  static OSPermissionState k;
  static OSSubscriptionState l;
  private static LocationGMS.LocationPoint lastLocationPoint;
  private static String lastRegistrationId;
  private static long lastTrackedFocusTime;
  private static boolean locationFired;
  private static OneSignal.LOG_LEVEL logCatLevel;
  static OSEmailSubscriptionState m;
  private static String mGoogleProjectNumber;
  private static boolean mGoogleProjectNumberIsRemote;
  private static PushRegistrator mPushRegistrator;
  private static AdvertisingIdentifierProvider mainAdIdProvider;
  private static OSUtils osUtils;
  private static OneSignal.GetTagsHandler pendingGetTagsHandler;
  private static OSObservable<OSPermissionObserver, OSPermissionStateChanges> permissionStateChangesObserver;
  private static HashSet<String> postedOpenedNotifIds;
  private static boolean promptedLocation;
  private static boolean registerForPushFired;
  public static String sdkType;
  private static boolean sendAsSession;
  private static int subscribableStatus;
  private static OSObservable<OSSubscriptionObserver, OSSubscriptionStateChanges> subscriptionStateChangesObserver;
  public static ConcurrentLinkedQueue<Runnable> taskQueueWaitingForInit;
  private static TrackAmazonPurchase trackAmazonPurchase;
  private static TrackFirebaseAnalytics trackFirebaseAnalytics;
  private static TrackGooglePurchase trackGooglePurchase;
  private static long unSentActiveTime;
  private static Collection<JSONArray> unprocessedOpenedNotifis;
  private static boolean useEmailAuth;
  private static String userId;
  private static OneSignal.LOG_LEVEL visualLogLevel = OneSignal.LOG_LEVEL.NONE;
  private static boolean waitingToPostStateSync;
  
  static
  {
    logCatLevel = OneSignal.LOG_LEVEL.WARN;
    userId = null;
    emailId = null;
    taskQueueWaitingForInit = new ConcurrentLinkedQueue();
    e = new AtomicLong();
    lastTrackedFocusTime = 1L;
    unSentActiveTime = -1L;
    mainAdIdProvider = new AdvertisingIdProviderGPS();
    sdkType = "native";
    f = true;
    unprocessedOpenedNotifis = new ArrayList();
    postedOpenedNotifIds = new HashSet();
    i = false;
  }
  
  public OneSignal() {}
  
  private static void SaveAppId(String paramString)
  {
    if (b == null) {
      return;
    }
    OneSignalPrefs.saveString(OneSignalPrefs.PREFS_ONESIGNAL, "GT_APP_ID", paramString);
  }
  
  private static void SaveUnsentActiveTime(long paramLong)
  {
    unSentActiveTime = paramLong;
    if (b == null) {
      return;
    }
    a(OneSignal.LOG_LEVEL.INFO, "SaveUnsentActiveTime: " + unSentActiveTime);
    OneSignalPrefs.saveLong(OneSignalPrefs.PREFS_ONESIGNAL, "GT_UNSENT_ACTIVE_TIME", paramLong);
  }
  
  static OSObservable<OSPermissionObserver, OSPermissionStateChanges> a()
  {
    if (permissionStateChangesObserver == null) {
      permissionStateChangesObserver = new OSObservable("onOSPermissionChanged", true);
    }
    return permissionStateChangesObserver;
  }
  
  static String a(Bundle paramBundle)
  {
    if (paramBundle.isEmpty()) {
      return null;
    }
    try
    {
      if (paramBundle.containsKey("custom"))
      {
        paramBundle = new JSONObject(paramBundle.getString("custom"));
        if (paramBundle.has("i")) {
          return paramBundle.optString("i", null);
        }
        a(OneSignal.LOG_LEVEL.DEBUG, "Not a OneSignal formatted GCM message. No 'i' field in custom.");
        return null;
      }
    }
    catch (Throwable paramBundle)
    {
      a(OneSignal.LOG_LEVEL.DEBUG, "Could not parse bundle, probably not a OneSignal notification.", paramBundle);
      return null;
    }
    a(OneSignal.LOG_LEVEL.DEBUG, "Not a OneSignal formatted GCM message. No 'custom' field in the bundle.");
    return null;
  }
  
  static void a(long paramLong)
  {
    OneSignalPrefs.saveLong(OneSignalPrefs.PREFS_ONESIGNAL, "OS_LAST_SESSION_TIME", paramLong);
  }
  
  static void a(long paramLong, boolean paramBoolean)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject().put("app_id", a).put("type", 1).put("state", "ping").put("active_time", paramLong);
      addNetType(localJSONObject);
      sendOnFocusToPlayer(k(), localJSONObject, paramBoolean);
      String str = l();
      if (str != null) {
        sendOnFocusToPlayer(str, localJSONObject, paramBoolean);
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "Generating on_focus:JSON Failed.", localThrowable);
    }
  }
  
  static void a(Context paramContext)
  {
    b = paramContext.getApplicationContext();
    OneSignalPrefs.startDelayedWrite();
  }
  
  static void a(OneSignal.LOG_LEVEL paramLOG_LEVEL, String paramString)
  {
    a(paramLOG_LEVEL, paramString, null);
  }
  
  static void a(OneSignal.LOG_LEVEL paramLOG_LEVEL, String paramString, Throwable paramThrowable)
  {
    if (paramLOG_LEVEL.compareTo(logCatLevel) < 1)
    {
      if (paramLOG_LEVEL != OneSignal.LOG_LEVEL.VERBOSE) {
        break label148;
      }
      Log.v("OneSignal", paramString, paramThrowable);
    }
    for (;;)
    {
      if ((paramLOG_LEVEL.compareTo(visualLogLevel) < 1) && (ActivityLifecycleHandler.b != null)) {}
      try
      {
        Object localObject = paramString + "\n";
        paramString = (String)localObject;
        if (paramThrowable != null)
        {
          paramString = (String)localObject + paramThrowable.getMessage();
          localObject = new StringWriter();
          paramThrowable.printStackTrace(new PrintWriter((Writer)localObject));
          paramString = paramString + ((StringWriter)localObject).toString();
        }
        OSUtils.a(new OneSignal.5(paramLOG_LEVEL, paramString));
        return;
      }
      catch (Throwable paramLOG_LEVEL)
      {
        label148:
        Log.e("OneSignal", "Error showing logging message.", paramLOG_LEVEL);
      }
      if (paramLOG_LEVEL == OneSignal.LOG_LEVEL.DEBUG) {
        Log.d("OneSignal", paramString, paramThrowable);
      } else if (paramLOG_LEVEL == OneSignal.LOG_LEVEL.INFO) {
        Log.i("OneSignal", paramString, paramThrowable);
      } else if (paramLOG_LEVEL == OneSignal.LOG_LEVEL.WARN) {
        Log.w("OneSignal", paramString, paramThrowable);
      } else if ((paramLOG_LEVEL == OneSignal.LOG_LEVEL.ERROR) || (paramLOG_LEVEL == OneSignal.LOG_LEVEL.FATAL)) {
        Log.e("OneSignal", paramString, paramThrowable);
      }
    }
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean, OneSignalRestClient.ResponseHandler paramResponseHandler)
  {
    if (a("sendPurchases()")) {}
    for (;;)
    {
      return;
      if (k() == null)
      {
        iapUpdateJob = new OneSignal.IAPUpdateJob(paramJSONArray);
        iapUpdateJob.b = paramBoolean;
        iapUpdateJob.c = paramResponseHandler;
        return;
      }
      try
      {
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("app_id", a);
        if (paramBoolean) {
          localJSONObject.put("existing", true);
        }
        localJSONObject.put("purchases", paramJSONArray);
        OneSignalRestClient.b("players/" + k() + "/on_purchase", localJSONObject, paramResponseHandler);
        if (l() != null)
        {
          OneSignalRestClient.b("players/" + l() + "/on_purchase", localJSONObject, null);
          return;
        }
      }
      catch (Throwable paramJSONArray)
      {
        a(OneSignal.LOG_LEVEL.ERROR, "Failed to generate JSON for sendPurchases.", paramJSONArray);
      }
    }
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramJSONArray = generateOsNotificationOpenResult(paramJSONArray, paramBoolean1, paramBoolean2);
    if ((trackFirebaseAnalytics != null) && (c(b))) {
      trackFirebaseAnalytics.b(paramJSONArray);
    }
    if ((g == null) || (g.c == null)) {
      return;
    }
    g.c.notificationReceived(paramJSONArray.notification);
  }
  
  static void a(boolean paramBoolean)
  {
    if (b == null) {
      return;
    }
    OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_USER_PROVIDED_CONSENT", paramBoolean);
  }
  
  static boolean a(Context paramContext, JSONObject paramJSONObject)
  {
    paramJSONObject = getNotificationIdFromGCMJsonPayload(paramJSONObject);
    return (paramJSONObject == null) || (isDuplicateNotification(paramJSONObject, paramContext));
  }
  
  static boolean a(String paramString)
  {
    if ((i) && (!userProvidedPrivacyConsent()))
    {
      if (paramString != null) {
        a(OneSignal.LOG_LEVEL.WARN, "Method " + paramString + " was called before the user provided privacy consent. Your application is set to require the user's privacy consent before the OneSignal SDK can be initialized. Please ensure the user has provided consent before calling this method. You can check the latest OneSignal consent status by calling OneSignal.userProvidedPrivacyConsent()");
      }
      return true;
    }
    return false;
  }
  
  public static void addEmailSubscriptionObserver(@NonNull OSEmailSubscriptionObserver paramOSEmailSubscriptionObserver)
  {
    if (a("addEmailSubscriptionObserver()")) {}
    do
    {
      return;
      if (b == null)
      {
        a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not add email subscription observer");
        return;
      }
      c().a(paramOSEmailSubscriptionObserver);
    } while (!getCurrentEmailSubscriptionState(b).a(getLastEmailSubscriptionState(b)));
    OSEmailSubscriptionChangedInternalObserver.a(getCurrentEmailSubscriptionState(b));
  }
  
  private static void addNetType(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject.put("net_type", osUtils.d());
      return;
    }
    catch (Throwable paramJSONObject) {}
  }
  
  public static void addPermissionObserver(OSPermissionObserver paramOSPermissionObserver)
  {
    if (a("addPermissionObserver()")) {}
    do
    {
      return;
      if (b == null)
      {
        a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not add permission observer");
        return;
      }
      a().a(paramOSPermissionObserver);
    } while (!getCurrentPermissionState(b).a(getLastPermissionState(b)));
    OSPermissionChangedInternalObserver.b(getCurrentPermissionState(b));
  }
  
  public static void addSubscriptionObserver(OSSubscriptionObserver paramOSSubscriptionObserver)
  {
    if (a("addSubscriptionObserver()")) {}
    do
    {
      return;
      if (b == null)
      {
        a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not add subscription observer");
        return;
      }
      b().a(paramOSSubscriptionObserver);
    } while (!getCurrentSubscriptionState(b).a(getLastSubscriptionState(b)));
    OSSubscriptionChangedInternalObserver.a(getCurrentSubscriptionState(b));
  }
  
  private static void addTaskToQueue(OneSignal.PendingTaskRunnable paramPendingTaskRunnable)
  {
    OneSignal.PendingTaskRunnable.a(paramPendingTaskRunnable, e.incrementAndGet());
    if (d == null)
    {
      a(OneSignal.LOG_LEVEL.INFO, "Adding a task to the pending queue with ID: " + OneSignal.PendingTaskRunnable.a(paramPendingTaskRunnable));
      taskQueueWaitingForInit.add(paramPendingTaskRunnable);
    }
    while (d.isShutdown()) {
      return;
    }
    a(OneSignal.LOG_LEVEL.INFO, "Executor is still running, add to the executor with ID: " + OneSignal.PendingTaskRunnable.a(paramPendingTaskRunnable));
    d.submit(paramPendingTaskRunnable);
  }
  
  private static boolean atLogLevel(OneSignal.LOG_LEVEL paramLOG_LEVEL)
  {
    return (paramLOG_LEVEL.compareTo(visualLogLevel) < 1) || (paramLOG_LEVEL.compareTo(logCatLevel) < 1);
  }
  
  static OSObservable<OSSubscriptionObserver, OSSubscriptionStateChanges> b()
  {
    if (subscriptionStateChangesObserver == null) {
      subscriptionStateChangesObserver = new OSObservable("onOSSubscriptionChanged", true);
    }
    return subscriptionStateChangesObserver;
  }
  
  static void b(String paramString)
  {
    userId = paramString;
    if (b == null) {
      return;
    }
    OneSignalPrefs.saveString(OneSignalPrefs.PREFS_ONESIGNAL, "GT_PLAYER_ID", userId);
  }
  
  static void b(boolean paramBoolean)
  {
    if (b == null) {
      return;
    }
    OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "OS_FILTER_OTHER_GCM_RECEIVERS", paramBoolean);
  }
  
  static boolean b(Context paramContext)
  {
    return OneSignalPrefs.a(OneSignalPrefs.PREFS_ONESIGNAL, "OS_FILTER_OTHER_GCM_RECEIVERS", false);
  }
  
  static OSObservable<OSEmailSubscriptionObserver, OSEmailSubscriptionStateChanges> c()
  {
    if (emailSubscriptionStateChangesObserver == null) {
      emailSubscriptionStateChangesObserver = new OSObservable("onOSEmailSubscriptionChanged", true);
    }
    return emailSubscriptionStateChangesObserver;
  }
  
  static void c(String paramString)
  {
    emailId = paramString;
    if (b == null) {
      return;
    }
    String str = OneSignalPrefs.PREFS_ONESIGNAL;
    if ("".equals(emailId)) {}
    for (paramString = null;; paramString = emailId)
    {
      OneSignalPrefs.saveString(str, "OS_EMAIL_ID", paramString);
      return;
    }
  }
  
  static boolean c(Context paramContext)
  {
    return OneSignalPrefs.a(OneSignalPrefs.PREFS_ONESIGNAL, "GT_FIREBASE_TRACKING_ENABLED", false);
  }
  
  public static void cancelGroupedNotifications(String paramString)
  {
    if (a("cancelGroupedNotifications()")) {
      return;
    }
    OneSignal.23 local23 = new OneSignal.23(paramString);
    if ((b == null) || (shouldRunTaskThroughQueue()))
    {
      a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not clear notifications part of group " + paramString + " - movingthis operation to a waiting task queue.");
      addTaskToQueue(new OneSignal.PendingTaskRunnable(local23));
      return;
    }
    local23.run();
  }
  
  public static void cancelNotification(int paramInt)
  {
    if (a("cancelNotification()")) {
      return;
    }
    OneSignal.22 local22 = new OneSignal.22(paramInt);
    if ((b == null) || (shouldRunTaskThroughQueue()))
    {
      a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not clear notification id: " + paramInt + " at this time - movingthis operation to a waiting task queue. The notification will still be canceledfrom NotificationManager at this time.");
      taskQueueWaitingForInit.add(local22);
      return;
    }
    local22.run();
    ((NotificationManager)b.getSystemService("notification")).cancel(paramInt);
  }
  
  public static void clearOneSignalNotifications()
  {
    if (a("clearOneSignalNotifications()")) {
      return;
    }
    OneSignal.21 local21 = new OneSignal.21();
    if ((b == null) || (shouldRunTaskThroughQueue()))
    {
      a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not clear notifications at this time - moving this operation toa waiting task queue.");
      addTaskToQueue(new OneSignal.PendingTaskRunnable(local21));
      return;
    }
    local21.run();
  }
  
  static void d(String paramString)
  {
    b(paramString);
    h();
    internalFireGetTagsCallback(pendingGetTagsHandler);
    getCurrentSubscriptionState(b).a(paramString);
    if (iapUpdateJob != null)
    {
      a(iapUpdateJob.a, iapUpdateJob.b, iapUpdateJob.c);
      iapUpdateJob = null;
    }
    OneSignalStateSynchronizer.j();
    OneSignalChromeTab.a(b, a, paramString, AdvertisingIdProviderGPS.a());
  }
  
  @WorkerThread
  static boolean d()
  {
    foreground = false;
    LocationGMS.c();
    if (!c) {}
    long l1;
    boolean bool;
    do
    {
      do
      {
        do
        {
          return false;
          if (trackAmazonPurchase != null) {
            trackAmazonPurchase.a();
          }
        } while (lastTrackedFocusTime == -1L);
        l1 = ((SystemClock.elapsedRealtime() - lastTrackedFocusTime) / 1000.0D + 0.5D);
        lastTrackedFocusTime = SystemClock.elapsedRealtime();
      } while ((l1 < 0L) || (l1 > 86400L));
      if (b == null)
      {
        a(OneSignal.LOG_LEVEL.ERROR, "Android Context not found, please call OneSignal.init when your app starts.");
        return false;
      }
      bool = e();
      a(System.currentTimeMillis());
      l1 += o();
      SaveUnsentActiveTime(l1);
      if ((l1 >= 60L) && (k() != null)) {
        break;
      }
    } while (l1 < 60L);
    return true;
    if (!bool) {
      OneSignalSyncServiceUtils.a(b);
    }
    OneSignalSyncServiceUtils.a();
    return false;
  }
  
  static boolean d(Context paramContext)
  {
    return OneSignalPrefs.a(OneSignalPrefs.PREFS_ONESIGNAL, "GT_VIBRATE_ENABLED", true);
  }
  
  public static void deleteTag(String paramString)
  {
    if (a("deleteTag()")) {
      return;
    }
    ArrayList localArrayList = new ArrayList(1);
    localArrayList.add(paramString);
    deleteTags(localArrayList);
  }
  
  public static void deleteTags(String paramString)
  {
    if (a("deleteTags()")) {
      return;
    }
    try
    {
      JSONObject localJSONObject = new JSONObject();
      paramString = new JSONArray(paramString);
      int n = 0;
      while (n < paramString.length())
      {
        localJSONObject.put(paramString.getString(n), "");
        n += 1;
      }
      sendTags(localJSONObject);
      return;
    }
    catch (Throwable paramString)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "Failed to generate JSON for deleteTags.", paramString);
    }
  }
  
  public static void deleteTags(Collection<String> paramCollection)
  {
    if (a("deleteTags()")) {
      return;
    }
    try
    {
      JSONObject localJSONObject = new JSONObject();
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext()) {
        localJSONObject.put((String)paramCollection.next(), "");
      }
      sendTags(localJSONObject);
    }
    catch (Throwable paramCollection)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "Failed to generate JSON for deleteTags.", paramCollection);
      return;
    }
  }
  
  static void e(String paramString)
  {
    c(paramString);
    getCurrentEmailSubscriptionState(b).a(paramString);
    try
    {
      OneSignalStateSynchronizer.c(new JSONObject().put("parent_player_id", paramString));
      return;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  static boolean e()
  {
    boolean bool = OneSignalStateSynchronizer.c();
    if (bool) {
      OneSignalSyncServiceUtils.a(b);
    }
    return (LocationGMS.a(b)) || (bool);
  }
  
  static boolean e(Context paramContext)
  {
    return OneSignalPrefs.a(OneSignalPrefs.PREFS_ONESIGNAL, "GT_SOUND_ENABLED", true);
  }
  
  public static void enableSound(boolean paramBoolean)
  {
    if (b == null) {
      return;
    }
    OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "GT_SOUND_ENABLED", paramBoolean);
  }
  
  public static void enableVibrate(boolean paramBoolean)
  {
    if (b == null) {
      return;
    }
    OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "GT_VIBRATE_ENABLED", paramBoolean);
  }
  
  static void f()
  {
    foreground = true;
    LocationGMS.c();
    lastTrackedFocusTime = SystemClock.elapsedRealtime();
    sendAsSession = isPastOnSessionTime();
    a(System.currentTimeMillis());
    startRegistrationOrOnSession();
    if (trackGooglePurchase != null) {
      trackGooglePurchase.a();
    }
    NotificationRestorer.a(b);
    getCurrentPermissionState(b).a();
    if ((trackFirebaseAnalytics != null) && (c(b))) {
      trackFirebaseAnalytics.b();
    }
    OneSignalSyncServiceUtils.b(b);
  }
  
  private static void fireCallbackForOpenedNotifications()
  {
    Iterator localIterator = unprocessedOpenedNotifis.iterator();
    while (localIterator.hasNext()) {
      runNotificationOpenedCallback((JSONArray)localIterator.next(), true, false);
    }
    unprocessedOpenedNotifis.clear();
  }
  
  private static void fireIntentFromNotificationOpen(Context paramContext)
  {
    if (a(null)) {}
    Intent localIntent;
    do
    {
      return;
      localIntent = paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
    } while (localIntent == null);
    localIntent.setFlags(268566528);
    paramContext.startActivity(localIntent);
  }
  
  private static void fireNotificationOpenedHandler(OSNotificationOpenResult paramOSNotificationOpenResult)
  {
    OSUtils.a(new OneSignal.17(paramOSNotificationOpenResult));
  }
  
  static boolean g()
  {
    return foreground;
  }
  
  @NonNull
  private static OSNotificationOpenResult generateOsNotificationOpenResult(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject1 = null;
    int i2 = paramJSONArray.length();
    int n = 1;
    OSNotificationOpenResult localOSNotificationOpenResult = new OSNotificationOpenResult();
    OSNotification localOSNotification = new OSNotification();
    localOSNotification.isAppInFocus = p();
    localOSNotification.shown = paramBoolean1;
    localOSNotification.androidNotificationId = paramJSONArray.optJSONObject(0).optInt("notificationId");
    int i1 = 0;
    if (i1 < i2) {}
    try
    {
      Object localObject2 = paramJSONArray.getJSONObject(i1);
      localOSNotification.payload = NotificationBundleProcessor.a((JSONObject)localObject2);
      if ((localObject1 != null) || (!((JSONObject)localObject2).has("actionSelected"))) {
        break label318;
      }
      localObject2 = ((JSONObject)localObject2).optString("actionSelected", null);
      localObject1 = localObject2;
    }
    catch (Throwable localThrowable2)
    {
      for (;;)
      {
        OSNotificationAction localOSNotificationAction;
      }
    }
    if (n != 0) {
      n = 0;
    }
    for (;;)
    {
      i1 += 1;
      break;
      try
      {
        if (localOSNotification.groupedNotifications == null) {
          localOSNotification.groupedNotifications = new ArrayList();
        }
        localOSNotification.groupedNotifications.add(localOSNotification.payload);
      }
      catch (Throwable localThrowable1) {}
      a(OneSignal.LOG_LEVEL.ERROR, "Error parsing JSON item " + i1 + "/" + i2 + " for callback.", localThrowable1);
    }
    localOSNotificationOpenResult.notification = localOSNotification;
    localOSNotificationOpenResult.action = new OSNotificationAction();
    localOSNotificationOpenResult.action.actionID = localObject1;
    localOSNotificationAction = localOSNotificationOpenResult.action;
    if (localObject1 != null) {}
    for (paramJSONArray = OSNotificationAction.ActionType.ActionTaken;; paramJSONArray = OSNotificationAction.ActionType.Opened)
    {
      localOSNotificationAction.type = paramJSONArray;
      if (!paramBoolean2) {
        break;
      }
      localOSNotificationOpenResult.notification.displayType = OSNotification.DisplayType.InAppAlert;
      return localOSNotificationOpenResult;
    }
    localOSNotificationOpenResult.notification.displayType = OSNotification.DisplayType.Notification;
    return localOSNotificationOpenResult;
  }
  
  private static OSEmailSubscriptionState getCurrentEmailSubscriptionState(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (currentEmailSubscriptionState == null)
    {
      currentEmailSubscriptionState = new OSEmailSubscriptionState(false);
      currentEmailSubscriptionState.a.b(new OSEmailSubscriptionChangedInternalObserver());
    }
    return currentEmailSubscriptionState;
  }
  
  public static OneSignal.Builder getCurrentOrNewInitBuilder()
  {
    if (g == null) {
      g = new OneSignal.Builder(null);
    }
    return g;
  }
  
  private static OSPermissionState getCurrentPermissionState(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (currentPermissionState == null)
    {
      currentPermissionState = new OSPermissionState(false);
      currentPermissionState.a.b(new OSPermissionChangedInternalObserver());
    }
    return currentPermissionState;
  }
  
  private static OSSubscriptionState getCurrentSubscriptionState(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (currentSubscriptionState == null)
    {
      currentSubscriptionState = new OSSubscriptionState(false, getCurrentPermissionState(paramContext).getEnabled());
      getCurrentPermissionState(paramContext).a.a(currentSubscriptionState);
      currentSubscriptionState.a.b(new OSSubscriptionChangedInternalObserver());
    }
    return currentSubscriptionState;
  }
  
  private static OneSignal.OSInFocusDisplayOption getInFocusDisplaying(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      if (paramInt < 0) {
        return OneSignal.OSInFocusDisplayOption.None;
      }
      break;
    case 0: 
      return OneSignal.OSInFocusDisplayOption.None;
    case 1: 
      return OneSignal.OSInFocusDisplayOption.InAppAlert;
    case 2: 
      return OneSignal.OSInFocusDisplayOption.Notification;
    }
    return OneSignal.OSInFocusDisplayOption.Notification;
  }
  
  private static OSEmailSubscriptionState getLastEmailSubscriptionState(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (m == null) {
      m = new OSEmailSubscriptionState(true);
    }
    return m;
  }
  
  private static OSPermissionState getLastPermissionState(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (k == null) {
      k = new OSPermissionState(true);
    }
    return k;
  }
  
  private static long getLastSessionTime(Context paramContext)
  {
    return OneSignalPrefs.a(OneSignalPrefs.PREFS_ONESIGNAL, "OS_LAST_SESSION_TIME", -31000L);
  }
  
  private static OSSubscriptionState getLastSubscriptionState(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (l == null) {
      l = new OSSubscriptionState(true, false);
    }
    return l;
  }
  
  private static OneSignal.LOG_LEVEL getLogLevel(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      if (paramInt < 0) {
        return OneSignal.LOG_LEVEL.NONE;
      }
      break;
    case 0: 
      return OneSignal.LOG_LEVEL.NONE;
    case 1: 
      return OneSignal.LOG_LEVEL.FATAL;
    case 2: 
      return OneSignal.LOG_LEVEL.ERROR;
    case 3: 
      return OneSignal.LOG_LEVEL.WARN;
    case 4: 
      return OneSignal.LOG_LEVEL.INFO;
    case 5: 
      return OneSignal.LOG_LEVEL.DEBUG;
    case 6: 
      return OneSignal.LOG_LEVEL.VERBOSE;
    }
    return OneSignal.LOG_LEVEL.VERBOSE;
  }
  
  private static String getNotificationIdFromGCMJsonPayload(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject = new JSONObject(paramJSONObject.optString("custom")).optString("i", null);
      return paramJSONObject;
    }
    catch (Throwable paramJSONObject) {}
    return null;
  }
  
  public static OSPermissionSubscriptionState getPermissionSubscriptionState()
  {
    if (a("getPermissionSubscriptionState()")) {
      return null;
    }
    if (b == null)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not get OSPermissionSubscriptionState");
      return null;
    }
    OSPermissionSubscriptionState localOSPermissionSubscriptionState = new OSPermissionSubscriptionState();
    localOSPermissionSubscriptionState.a = getCurrentSubscriptionState(b);
    localOSPermissionSubscriptionState.b = getCurrentPermissionState(b);
    localOSPermissionSubscriptionState.c = getCurrentEmailSubscriptionState(b);
    return localOSPermissionSubscriptionState;
  }
  
  private static PushRegistrator getPushRegistrator()
  {
    if (mPushRegistrator != null) {
      return mPushRegistrator;
    }
    if (deviceType == 2) {
      mPushRegistrator = new PushRegistratorADM();
    }
    for (;;)
    {
      return mPushRegistrator;
      if (OSUtils.a()) {
        mPushRegistrator = new PushRegistratorFCM();
      } else {
        mPushRegistrator = new PushRegistratorGCM();
      }
    }
  }
  
  private static String getSavedAppId(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return OneSignalPrefs.a(OneSignalPrefs.PREFS_ONESIGNAL, "GT_APP_ID", null);
  }
  
  private static boolean getSavedUserConsentStatus(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    return OneSignalPrefs.a(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_USER_PROVIDED_CONSENT", false);
  }
  
  private static String getSavedUserId(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return OneSignalPrefs.a(OneSignalPrefs.PREFS_ONESIGNAL, "GT_PLAYER_ID", null);
  }
  
  public static void getTags(OneSignal.GetTagsHandler paramGetTagsHandler)
  {
    if (a("getTags()")) {
      return;
    }
    pendingGetTagsHandler = paramGetTagsHandler;
    paramGetTagsHandler = new OneSignal.13(paramGetTagsHandler);
    if (b == null)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "You must initialize OneSignal before getting tags! Moving this tag operation to a pending queue.");
      taskQueueWaitingForInit.add(paramGetTagsHandler);
      return;
    }
    paramGetTagsHandler.run();
  }
  
  private static int getTimeZoneOffset()
  {
    TimeZone localTimeZone = Calendar.getInstance().getTimeZone();
    int i1 = localTimeZone.getRawOffset();
    int n = i1;
    if (localTimeZone.inDaylightTime(new Date())) {
      n = i1 + localTimeZone.getDSTSavings();
    }
    return n / 1000;
  }
  
  static void h()
  {
    if (idsAvailableHandler != null) {
      OSUtils.a(new OneSignal.16());
    }
  }
  
  public static void handleNotificationOpen(Context paramContext, JSONArray paramJSONArray, boolean paramBoolean)
  {
    if (a(null)) {}
    boolean bool1;
    boolean bool2;
    do
    {
      return;
      notificationOpenedRESTCall(paramContext, paramJSONArray);
      if ((trackFirebaseAnalytics != null) && (c(b))) {
        trackFirebaseAnalytics.a(generateOsNotificationOpenResult(paramJSONArray, true, paramBoolean));
      }
      bool1 = false;
      bool2 = "DISABLE".equals(OSUtils.a(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
      if (!bool2) {
        bool1 = openURLFromNotification(paramContext, paramJSONArray);
      }
      runNotificationOpenedCallback(paramJSONArray, true, paramBoolean);
    } while ((paramBoolean) || (bool1) || (bool2));
    fireIntentFromNotificationOpen(paramContext);
  }
  
  static String i()
  {
    return getSavedAppId(b);
  }
  
  public static void idsAvailable(OneSignal.IdsAvailableHandler paramIdsAvailableHandler)
  {
    if (a("idsAvailable()")) {
      return;
    }
    idsAvailableHandler = paramIdsAvailableHandler;
    paramIdsAvailableHandler = new OneSignal.15();
    if ((b == null) || (shouldRunTaskThroughQueue()))
    {
      a(OneSignal.LOG_LEVEL.ERROR, "You must initialize OneSignal before getting tags! Moving this tag operation to a pending queue.");
      addTaskToQueue(new OneSignal.PendingTaskRunnable(paramIdsAvailableHandler));
      return;
    }
    paramIdsAvailableHandler.run();
  }
  
  public static void init(Context paramContext, String paramString1, String paramString2)
  {
    init(paramContext, paramString1, paramString2, null, null);
  }
  
  public static void init(Context paramContext, String paramString1, String paramString2, OneSignal.NotificationOpenedHandler paramNotificationOpenedHandler)
  {
    init(paramContext, paramString1, paramString2, paramNotificationOpenedHandler, null);
  }
  
  public static void init(Context paramContext, String paramString1, String paramString2, OneSignal.NotificationOpenedHandler paramNotificationOpenedHandler, OneSignal.NotificationReceivedHandler paramNotificationReceivedHandler)
  {
    a(paramContext);
    if ((i) && (!userProvidedPrivacyConsent()))
    {
      a(OneSignal.LOG_LEVEL.VERBOSE, "OneSignal SDK initialization delayed, user privacy consent is set to required for this application.");
      j = new DelayedConsentInitializationParameters(paramContext, paramString1, paramString2, paramNotificationOpenedHandler, paramNotificationReceivedHandler);
    }
    do
    {
      do
      {
        return;
        g = getCurrentOrNewInitBuilder();
        g.h = false;
        g.b = paramNotificationOpenedHandler;
        g.c = paramNotificationReceivedHandler;
        if (!mGoogleProjectNumberIsRemote) {
          mGoogleProjectNumber = paramString1;
        }
        osUtils = new OSUtils();
        deviceType = osUtils.c();
        subscribableStatus = osUtils.a(paramContext, deviceType, paramString2);
      } while (subscribableStatus == 64537);
      if (!c) {
        break;
      }
    } while (g.b == null);
    fireCallbackForOpenedNotifications();
    return;
    boolean bool = paramContext instanceof Activity;
    foreground = bool;
    a = paramString2;
    b(g.g);
    if (bool)
    {
      ActivityLifecycleHandler.b = (Activity)paramContext;
      NotificationRestorer.a(b);
    }
    for (;;)
    {
      lastTrackedFocusTime = SystemClock.elapsedRealtime();
      OneSignalStateSynchronizer.e();
      ((Application)b).registerActivityLifecycleCallbacks(new ActivityLifecycleListener());
      try
      {
        Class.forName("com.amazon.device.iap.PurchasingListener");
        trackAmazonPurchase = new TrackAmazonPurchase(b);
        paramContext = i();
        if (paramContext != null) {
          if (!paramContext.equals(a))
          {
            a(OneSignal.LOG_LEVEL.DEBUG, "APP ID changed, clearing user id as it is no longer valid.");
            SaveAppId(a);
            OneSignalStateSynchronizer.i();
          }
        }
        for (;;)
        {
          OSPermissionChangedInternalObserver.a(getCurrentPermissionState(b));
          if ((foreground) || (k() == null))
          {
            sendAsSession = isPastOnSessionTime();
            a(System.currentTimeMillis());
            startRegistrationOrOnSession();
          }
          if (g.b != null) {
            fireCallbackForOpenedNotifications();
          }
          if (TrackGooglePurchase.a(b)) {
            trackGooglePurchase = new TrackGooglePurchase(b);
          }
          if (TrackFirebaseAnalytics.a()) {
            trackFirebaseAnalytics = new TrackFirebaseAnalytics(b);
          }
          PushRegistratorFCM.a(b);
          c = true;
          startPendingTasks();
          return;
          ActivityLifecycleHandler.a = true;
          break;
          BadgeCountUpdater.a(0, b);
          SaveAppId(a);
        }
      }
      catch (ClassNotFoundException paramContext)
      {
        for (;;) {}
      }
    }
  }
  
  private static void init(OneSignal.Builder paramBuilder)
  {
    if (getCurrentOrNewInitBuilder().h) {
      paramBuilder.i = getCurrentOrNewInitBuilder().i;
    }
    g = paramBuilder;
    Context localContext = g.a;
    g.a = null;
    try
    {
      Bundle localBundle = localContext.getPackageManager().getApplicationInfo(localContext.getPackageName(), 128).metaData;
      String str = localBundle.getString("onesignal_google_project_number");
      paramBuilder = str;
      if (str != null)
      {
        paramBuilder = str;
        if (str.length() > 4) {
          paramBuilder = str.substring(4);
        }
      }
      setRequiresUserPrivacyConsent("ENABLE".equalsIgnoreCase(localBundle.getString("com.onesignal.PrivacyConsent")));
      init(localContext, paramBuilder, localBundle.getString("onesignal_app_id"), g.b, g.c);
      return;
    }
    catch (Throwable paramBuilder)
    {
      paramBuilder.printStackTrace();
    }
  }
  
  private static void internalFireGetTagsCallback(OneSignal.GetTagsHandler paramGetTagsHandler)
  {
    if (paramGetTagsHandler == null) {
      return;
    }
    new Thread(new OneSignal.14(paramGetTagsHandler), "OS_GETTAGS_CALLBACK").start();
  }
  
  private static void internalFireIdsAvailableCallback()
  {
    String str = null;
    for (;;)
    {
      try
      {
        Object localObject3 = idsAvailableHandler;
        if (localObject3 == null) {
          return;
        }
        localObject3 = OneSignalStateSynchronizer.h();
        if (!OneSignalStateSynchronizer.g())
        {
          localObject3 = k();
          if (localObject3 != null)
          {
            idsAvailableHandler.idsAvailable((String)localObject3, str);
            if (str != null) {
              idsAvailableHandler = null;
            }
          }
        }
        else
        {
          Object localObject2 = localObject3;
        }
      }
      finally {}
    }
  }
  
  /* Error */
  private static boolean isDuplicateNotification(String paramString, Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: ifnull +13 -> 17
    //   7: ldc_w 682
    //   10: aload_0
    //   11: invokevirtual 687	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   14: ifeq +5 -> 19
    //   17: iconst_0
    //   18: ireturn
    //   19: aload_1
    //   20: invokestatic 1346	com/onesignal/OneSignalDbHelper:getInstance	(Landroid/content/Context;)Lcom/onesignal/OneSignalDbHelper;
    //   23: astore_1
    //   24: aload_1
    //   25: invokevirtual 1349	com/onesignal/OneSignalDbHelper:b	()Landroid/database/sqlite/SQLiteDatabase;
    //   28: ldc_w 732
    //   31: iconst_1
    //   32: anewarray 684	java/lang/String
    //   35: dup
    //   36: iconst_0
    //   37: ldc_w 1351
    //   40: aastore
    //   41: ldc_w 1353
    //   44: iconst_1
    //   45: anewarray 684	java/lang/String
    //   48: dup
    //   49: iconst_0
    //   50: aload_0
    //   51: aastore
    //   52: aconst_null
    //   53: aconst_null
    //   54: aconst_null
    //   55: invokevirtual 1359	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   58: astore_1
    //   59: aload_1
    //   60: astore 4
    //   62: aload 4
    //   64: astore_1
    //   65: aload 4
    //   67: invokeinterface 1364 1 0
    //   72: istore_3
    //   73: iload_3
    //   74: istore_2
    //   75: aload 4
    //   77: ifnull +12 -> 89
    //   80: aload 4
    //   82: invokeinterface 1367 1 0
    //   87: iload_3
    //   88: istore_2
    //   89: iload_2
    //   90: ifeq +83 -> 173
    //   93: getstatic 307	com/onesignal/OneSignal$LOG_LEVEL:DEBUG	Lcom/onesignal/OneSignal$LOG_LEVEL;
    //   96: new 237	java/lang/StringBuilder
    //   99: dup
    //   100: invokespecial 238	java/lang/StringBuilder:<init>	()V
    //   103: ldc_w 1369
    //   106: invokevirtual 244	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: aload_0
    //   110: invokevirtual 244	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: invokevirtual 250	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   116: invokestatic 253	com/onesignal/OneSignal:a	(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;)V
    //   119: iconst_1
    //   120: ireturn
    //   121: astore 5
    //   123: aconst_null
    //   124: astore 4
    //   126: aload 4
    //   128: astore_1
    //   129: getstatic 360	com/onesignal/OneSignal$LOG_LEVEL:ERROR	Lcom/onesignal/OneSignal$LOG_LEVEL;
    //   132: ldc_w 1371
    //   135: aload 5
    //   137: invokestatic 314	com/onesignal/OneSignal:a	(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   140: aload 4
    //   142: ifnull +42 -> 184
    //   145: aload 4
    //   147: invokeinterface 1367 1 0
    //   152: iconst_0
    //   153: istore_2
    //   154: goto -65 -> 89
    //   157: astore_0
    //   158: aload 4
    //   160: astore_1
    //   161: aload_1
    //   162: ifnull +9 -> 171
    //   165: aload_1
    //   166: invokeinterface 1367 1 0
    //   171: aload_0
    //   172: athrow
    //   173: iconst_0
    //   174: ireturn
    //   175: astore_0
    //   176: goto -15 -> 161
    //   179: astore 5
    //   181: goto -55 -> 126
    //   184: iconst_0
    //   185: istore_2
    //   186: goto -97 -> 89
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	189	0	paramString	String
    //   0	189	1	paramContext	Context
    //   74	112	2	bool1	boolean
    //   72	16	3	bool2	boolean
    //   1	158	4	localContext	Context
    //   121	15	5	localThrowable1	Throwable
    //   179	1	5	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   24	59	121	java/lang/Throwable
    //   24	59	157	finally
    //   65	73	175	finally
    //   129	140	175	finally
    //   65	73	179	java/lang/Throwable
  }
  
  private static boolean isPastOnSessionTime()
  {
    if (sendAsSession) {}
    while ((System.currentTimeMillis() - getLastSessionTime(b)) / 1000L >= 30L) {
      return true;
    }
    return false;
  }
  
  static boolean j()
  {
    return getSavedUserConsentStatus(b);
  }
  
  static String k()
  {
    if ((userId == null) && (b != null)) {
      userId = OneSignalPrefs.a(OneSignalPrefs.PREFS_ONESIGNAL, "GT_PLAYER_ID", null);
    }
    return userId;
  }
  
  static String l()
  {
    if ("".equals(emailId)) {
      return null;
    }
    if ((emailId == null) && (b != null)) {
      emailId = OneSignalPrefs.a(OneSignalPrefs.PREFS_ONESIGNAL, "OS_EMAIL_ID", null);
    }
    return emailId;
  }
  
  private static void logHttpError(String paramString1, int paramInt, Throwable paramThrowable, String paramString2)
  {
    String str2 = "";
    String str1 = str2;
    if (paramString2 != null)
    {
      str1 = str2;
      if (atLogLevel(OneSignal.LOG_LEVEL.INFO)) {
        str1 = "\n" + paramString2 + "\n";
      }
    }
    a(OneSignal.LOG_LEVEL.WARN, "HTTP code: " + paramInt + " " + paramString1 + str1, paramThrowable);
  }
  
  public static void logoutEmail()
  {
    logoutEmail(null);
  }
  
  public static void logoutEmail(@Nullable OneSignal.EmailUpdateHandler paramEmailUpdateHandler)
  {
    if (a("logoutEmail()")) {
      return;
    }
    if (l() == null)
    {
      if (paramEmailUpdateHandler != null) {
        paramEmailUpdateHandler.onFailure(new OneSignal.EmailUpdateError(OneSignal.EmailErrorType.INVALID_OPERATION, "logoutEmail not valid as email was not set or already logged out!"));
      }
      a(OneSignal.LOG_LEVEL.ERROR, "logoutEmail not valid as email was not set or already logged out!");
      return;
    }
    emailLogoutHandler = paramEmailUpdateHandler;
    paramEmailUpdateHandler = new OneSignal.10();
    if ((b == null) || (shouldRunTaskThroughQueue()))
    {
      a(OneSignal.LOG_LEVEL.ERROR, "You should initialize OneSignal before calling logoutEmail! Moving this operation to a pending task queue.");
      addTaskToQueue(new OneSignal.PendingTaskRunnable(paramEmailUpdateHandler));
      return;
    }
    paramEmailUpdateHandler.run();
  }
  
  static boolean m()
  {
    if (g == null) {}
    while (g.i == OneSignal.OSInFocusDisplayOption.Notification) {
      return true;
    }
    return false;
  }
  
  private static void makeAndroidParamsRequest()
  {
    if (awlFired)
    {
      registerForPushToken();
      return;
    }
    OneSignal.4 local4 = new OneSignal.4();
    String str2 = "apps/" + a + "/android_params.js";
    String str3 = k();
    String str1 = str2;
    if (str3 != null) {
      str1 = str2 + "?player_id=" + str3;
    }
    a(OneSignal.LOG_LEVEL.DEBUG, "Starting request to get Android parameters.");
    OneSignalRestClient.a(str1, local4);
  }
  
  static boolean n()
  {
    if (g == null) {}
    while (g.i != OneSignal.OSInFocusDisplayOption.InAppAlert) {
      return false;
    }
    return true;
  }
  
  private static void notificationOpenedRESTCall(Context paramContext, JSONArray paramJSONArray)
  {
    int n = 0;
    for (;;)
    {
      if (n < paramJSONArray.length()) {
        try
        {
          String str = new JSONObject(paramJSONArray.getJSONObject(n).optString("custom", null)).optString("i", null);
          if (postedOpenedNotifIds.contains(str)) {
            break label153;
          }
          postedOpenedNotifIds.add(str);
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("app_id", getSavedAppId(paramContext));
          localJSONObject.put("player_id", getSavedUserId(paramContext));
          localJSONObject.put("opened", true);
          OneSignalRestClient.a("notifications/" + str, localJSONObject, new OneSignal.18());
        }
        catch (Throwable localThrowable)
        {
          a(OneSignal.LOG_LEVEL.ERROR, "Failed to generate JSON to send notification opened.", localThrowable);
        }
      }
      return;
      label153:
      n += 1;
    }
  }
  
  static long o()
  {
    if ((unSentActiveTime == -1L) && (b != null)) {
      unSentActiveTime = OneSignalPrefs.a(OneSignalPrefs.PREFS_ONESIGNAL, "GT_UNSENT_ACTIVE_TIME", 0L);
    }
    a(OneSignal.LOG_LEVEL.INFO, "GetUnsentActiveTime: " + unSentActiveTime);
    return unSentActiveTime;
  }
  
  private static void onTaskRan(long paramLong)
  {
    if (e.get() == paramLong)
    {
      a(OneSignal.LOG_LEVEL.INFO, "Last Pending Task has ran, shutting down");
      d.shutdown();
    }
  }
  
  private static boolean openURLFromNotification(Context paramContext, JSONArray paramJSONArray)
  {
    boolean bool1 = false;
    boolean bool2 = false;
    if (a(null)) {
      return bool2;
    }
    int i1 = paramJSONArray.length();
    int n = 0;
    for (;;)
    {
      bool2 = bool1;
      if (n >= i1) {
        break;
      }
      try
      {
        Object localObject = paramJSONArray.getJSONObject(n);
        if (!((JSONObject)localObject).has("custom"))
        {
          bool2 = bool1;
        }
        else
        {
          localObject = new JSONObject(((JSONObject)localObject).optString("custom"));
          bool2 = bool1;
          if (((JSONObject)localObject).has("u"))
          {
            String str = ((JSONObject)localObject).optString("u", null);
            localObject = str;
            if (!str.contains("://")) {
              localObject = "http://" + str;
            }
            localObject = new Intent("android.intent.action.VIEW", Uri.parse(((String)localObject).trim()));
            ((Intent)localObject).addFlags(1476919296);
            paramContext.startActivity((Intent)localObject);
            bool2 = true;
          }
        }
      }
      catch (Throwable localThrowable)
      {
        a(OneSignal.LOG_LEVEL.ERROR, "Error parsing JSON item " + n + "/" + i1 + " for launching a web URL.", localThrowable);
        bool2 = bool1;
      }
      n += 1;
      bool1 = bool2;
    }
  }
  
  static boolean p()
  {
    return (c) && (g());
  }
  
  public static void postNotification(String paramString, OneSignal.PostNotificationResponseHandler paramPostNotificationResponseHandler)
  {
    try
    {
      postNotification(new JSONObject(paramString), paramPostNotificationResponseHandler);
      return;
    }
    catch (JSONException paramPostNotificationResponseHandler)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "Invalid postNotification JSON format: " + paramString);
    }
  }
  
  public static void postNotification(JSONObject paramJSONObject, OneSignal.PostNotificationResponseHandler paramPostNotificationResponseHandler)
  {
    if (a("postNotification()")) {}
    do
    {
      return;
      try
      {
        if (!paramJSONObject.has("app_id")) {
          paramJSONObject.put("app_id", i());
        }
        OneSignalRestClient.b("notifications/", paramJSONObject, new OneSignal.12(paramPostNotificationResponseHandler));
        return;
      }
      catch (JSONException paramJSONObject)
      {
        a(OneSignal.LOG_LEVEL.ERROR, "HTTP create notification json exception!", paramJSONObject);
      }
    } while (paramPostNotificationResponseHandler == null);
    try
    {
      paramPostNotificationResponseHandler.onFailure(new JSONObject("{'error': 'HTTP create notification json exception!'}"));
      return;
    }
    catch (JSONException paramJSONObject)
    {
      paramJSONObject.printStackTrace();
    }
  }
  
  public static void promptLocation()
  {
    if (a("promptLocation()")) {
      return;
    }
    OneSignal.20 local20 = new OneSignal.20();
    if ((b == null) || (shouldRunTaskThroughQueue()))
    {
      a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not prompt for location at this time - moving this operation to awaiting queue.");
      addTaskToQueue(new OneSignal.PendingTaskRunnable(local20));
      return;
    }
    local20.run();
  }
  
  public static void provideUserConsent(boolean paramBoolean)
  {
    boolean bool = userProvidedPrivacyConsent();
    a(paramBoolean);
    if ((!bool) && (paramBoolean) && (j != null))
    {
      init(j.context, j.googleProjectNumber, j.appId, j.openedHandler, j.receivedHandler);
      j = null;
    }
  }
  
  private static boolean pushStatusRuntimeError(int paramInt)
  {
    return paramInt < -6;
  }
  
  static void q()
  {
    sendAsSession = false;
    a(System.currentTimeMillis());
  }
  
  static boolean r()
  {
    if (g.f) {
      return OSUtils.a(b);
    }
    return true;
  }
  
  private static void registerForPushToken()
  {
    getPushRegistrator().registerForPush(b, mGoogleProjectNumber, new OneSignal.3());
  }
  
  private static void registerUser()
  {
    a(OneSignal.LOG_LEVEL.DEBUG, "registerUser: registerForPushFired:" + registerForPushFired + ", locationFired: " + locationFired + ", awlFired: " + awlFired);
    if ((!registerForPushFired) || (!locationFired) || (!awlFired)) {
      return;
    }
    new Thread(new OneSignal.7(), "OS_REG_USER").start();
  }
  
  private static void registerUserTask()
  {
    Object localObject2 = b.getPackageName();
    Object localObject1 = b.getPackageManager();
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("app_id", a);
    Object localObject3 = mainAdIdProvider.getIdentifier(b);
    if (localObject3 != null) {
      localJSONObject.put("ad_id", localObject3);
    }
    localJSONObject.put("device_os", Build.VERSION.RELEASE);
    localJSONObject.put("timezone", getTimeZoneOffset());
    localJSONObject.put("language", OSUtils.f());
    localJSONObject.put("sdk", "030903");
    localJSONObject.put("sdk_type", sdkType);
    localJSONObject.put("android_package", localObject2);
    localJSONObject.put("device_model", Build.MODEL);
    try
    {
      localJSONObject.put("game_version", ((PackageManager)localObject1).getPackageInfo((String)localObject2, 0).versionCode);
      try
      {
        localObject1 = ((PackageManager)localObject1).getInstalledPackages(0);
        localObject2 = new JSONArray();
        localObject3 = MessageDigest.getInstance("SHA-256");
        n = 0;
        if (n < ((List)localObject1).size())
        {
          ((MessageDigest)localObject3).update(((PackageInfo)((List)localObject1).get(n)).packageName.getBytes());
          String str = Base64.encodeToString(((MessageDigest)localObject3).digest(), 2);
          if (!awl.has(str)) {
            break label387;
          }
          ((JSONArray)localObject2).put(str);
          break label387;
        }
        localJSONObject.put("pkgs", localObject2);
      }
      catch (Throwable localThrowable)
      {
        for (;;) {}
      }
      localJSONObject.put("net_type", osUtils.d());
      localJSONObject.put("carrier", osUtils.e());
      localJSONObject.put("rooted", RootToolsInternalMethods.a());
      OneSignalStateSynchronizer.b(localJSONObject);
      localJSONObject = new JSONObject();
      localJSONObject.put("identifier", lastRegistrationId);
      localJSONObject.put("subscribableStatus", subscribableStatus);
      localJSONObject.put("androidPermission", r());
      localJSONObject.put("device_type", deviceType);
      OneSignalStateSynchronizer.c(localJSONObject);
      if ((f) && (lastLocationPoint != null)) {
        OneSignalStateSynchronizer.a(lastLocationPoint);
      }
      if (sendAsSession) {
        OneSignalStateSynchronizer.k();
      }
      waitingToPostStateSync = false;
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        int n;
        continue;
        label387:
        n += 1;
      }
    }
  }
  
  public static void removeEmailSubscriptionObserver(@NonNull OSEmailSubscriptionObserver paramOSEmailSubscriptionObserver)
  {
    if (b == null)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not modify email subscription observer");
      return;
    }
    c().c(paramOSEmailSubscriptionObserver);
  }
  
  public static void removeNotificationOpenedHandler()
  {
    getCurrentOrNewInitBuilder().b = null;
  }
  
  public static void removeNotificationReceivedHandler()
  {
    getCurrentOrNewInitBuilder().c = null;
  }
  
  public static void removePermissionObserver(OSPermissionObserver paramOSPermissionObserver)
  {
    if (b == null)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not modify permission observer");
      return;
    }
    a().c(paramOSPermissionObserver);
  }
  
  public static void removeSubscriptionObserver(OSSubscriptionObserver paramOSSubscriptionObserver)
  {
    if (b == null)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not modify subscription observer");
      return;
    }
    b().c(paramOSSubscriptionObserver);
  }
  
  private static void runNotificationOpenedCallback(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((g == null) || (g.b == null))
    {
      unprocessedOpenedNotifis.add(paramJSONArray);
      return;
    }
    fireNotificationOpenedHandler(generateOsNotificationOpenResult(paramJSONArray, paramBoolean1, paramBoolean2));
  }
  
  static void s()
  {
    if (emailLogoutHandler != null)
    {
      emailLogoutHandler.onSuccess();
      emailLogoutHandler = null;
    }
  }
  
  private static void sendOnFocusToPlayer(String paramString, JSONObject paramJSONObject, boolean paramBoolean)
  {
    paramString = "players/" + paramString + "/on_focus";
    OneSignal.6 local6 = new OneSignal.6();
    if (paramBoolean)
    {
      OneSignalRestClient.d(paramString, paramJSONObject, local6);
      return;
    }
    OneSignalRestClient.b(paramString, paramJSONObject, local6);
  }
  
  public static void sendTag(String paramString1, String paramString2)
  {
    if (a("sendTag()")) {
      return;
    }
    try
    {
      sendTags(new JSONObject().put(paramString1, paramString2));
      return;
    }
    catch (JSONException paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static void sendTags(String paramString)
  {
    try
    {
      sendTags(new JSONObject(paramString));
      return;
    }
    catch (JSONException paramString)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "Generating JSONObject for sendTags failed!", paramString);
    }
  }
  
  public static void sendTags(JSONObject paramJSONObject)
  {
    if (a("sendTags()")) {
      return;
    }
    paramJSONObject = new OneSignal.11(paramJSONObject);
    if ((b == null) || (shouldRunTaskThroughQueue()))
    {
      a(OneSignal.LOG_LEVEL.ERROR, "You must initialize OneSignal before modifying tags!Moving this operation to a pending task queue.");
      addTaskToQueue(new OneSignal.PendingTaskRunnable(paramJSONObject));
      return;
    }
    paramJSONObject.run();
  }
  
  public static void setEmail(@NonNull String paramString)
  {
    setEmail(paramString, null, null);
  }
  
  public static void setEmail(@NonNull String paramString, OneSignal.EmailUpdateHandler paramEmailUpdateHandler)
  {
    setEmail(paramString, null, paramEmailUpdateHandler);
  }
  
  public static void setEmail(@NonNull String paramString1, @Nullable String paramString2)
  {
    setEmail(paramString1, paramString2, null);
  }
  
  public static void setEmail(@NonNull String paramString1, @Nullable String paramString2, @Nullable OneSignal.EmailUpdateHandler paramEmailUpdateHandler)
  {
    if (a("setEmail()")) {
      return;
    }
    if (!OSUtils.a(paramString1))
    {
      if (paramEmailUpdateHandler != null) {
        paramEmailUpdateHandler.onFailure(new OneSignal.EmailUpdateError(OneSignal.EmailErrorType.VALIDATION, "Email is invalid"));
      }
      a(OneSignal.LOG_LEVEL.ERROR, "Email is invalid");
      return;
    }
    if ((useEmailAuth) && (paramString2 == null))
    {
      if (paramEmailUpdateHandler != null) {
        paramEmailUpdateHandler.onFailure(new OneSignal.EmailUpdateError(OneSignal.EmailErrorType.REQUIRES_EMAIL_AUTH, "Email authentication (auth token) is set to REQUIRED for this application. Please provide an auth token from your backend server or change the setting in the OneSignal dashboard."));
      }
      a(OneSignal.LOG_LEVEL.ERROR, "Email authentication (auth token) is set to REQUIRED for this application. Please provide an auth token from your backend server or change the setting in the OneSignal dashboard.");
      return;
    }
    emailUpdateHandler = paramEmailUpdateHandler;
    paramString1 = new OneSignal.9(paramString1, paramString2);
    if ((b == null) || (shouldRunTaskThroughQueue()))
    {
      a(OneSignal.LOG_LEVEL.ERROR, "You should initialize OneSignal before calling setEmail! Moving this operation to a pending task queue.");
      addTaskToQueue(new OneSignal.PendingTaskRunnable(paramString1));
      return;
    }
    paramString1.run();
  }
  
  public static void setInFocusDisplaying(int paramInt)
  {
    setInFocusDisplaying(getInFocusDisplaying(paramInt));
  }
  
  public static void setInFocusDisplaying(OneSignal.OSInFocusDisplayOption paramOSInFocusDisplayOption)
  {
    getCurrentOrNewInitBuilder().h = true;
    getCurrentOrNewInitBuilder().i = paramOSInFocusDisplayOption;
  }
  
  public static void setLocationShared(boolean paramBoolean)
  {
    if (a("setLocationShared()")) {
      return;
    }
    f = paramBoolean;
    if (!paramBoolean) {
      OneSignalStateSynchronizer.d();
    }
    a(OneSignal.LOG_LEVEL.DEBUG, "shareLocation:" + f);
  }
  
  public static void setLogLevel(int paramInt1, int paramInt2)
  {
    setLogLevel(getLogLevel(paramInt1), getLogLevel(paramInt2));
  }
  
  public static void setLogLevel(OneSignal.LOG_LEVEL paramLOG_LEVEL1, OneSignal.LOG_LEVEL paramLOG_LEVEL2)
  {
    logCatLevel = paramLOG_LEVEL1;
    visualLogLevel = paramLOG_LEVEL2;
  }
  
  public static void setRequiresUserPrivacyConsent(boolean paramBoolean)
  {
    if ((i) && (!paramBoolean))
    {
      a(OneSignal.LOG_LEVEL.ERROR, "Cannot change requiresUserPrivacyConsent() from TRUE to FALSE");
      return;
    }
    i = paramBoolean;
  }
  
  public static void setSubscription(boolean paramBoolean)
  {
    if (a("setSubscription()")) {
      return;
    }
    OneSignal.19 local19 = new OneSignal.19(paramBoolean);
    if ((b == null) || (shouldRunTaskThroughQueue()))
    {
      a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Moving subscription action to a waiting task queue.");
      addTaskToQueue(new OneSignal.PendingTaskRunnable(local19));
      return;
    }
    local19.run();
  }
  
  private static boolean shouldRunTaskThroughQueue()
  {
    if ((c) && (d == null)) {}
    do
    {
      return false;
      if ((!c) && (d == null)) {
        return true;
      }
    } while ((d == null) || (d.isShutdown()));
    return true;
  }
  
  public static OneSignal.Builder startInit(Context paramContext)
  {
    return new OneSignal.Builder(paramContext, null);
  }
  
  private static void startLocationUpdate()
  {
    OneSignal.2 local2 = new OneSignal.2();
    if ((g.d) && (!promptedLocation)) {}
    for (boolean bool = true;; bool = false)
    {
      LocationGMS.a(b, bool, local2);
      return;
    }
  }
  
  private static void startPendingTasks()
  {
    if (!taskQueueWaitingForInit.isEmpty())
    {
      d = Executors.newSingleThreadExecutor(new OneSignal.1());
      while (!taskQueueWaitingForInit.isEmpty()) {
        d.submit((Runnable)taskQueueWaitingForInit.poll());
      }
    }
  }
  
  private static void startRegistrationOrOnSession()
  {
    boolean bool = false;
    if (waitingToPostStateSync) {
      return;
    }
    waitingToPostStateSync = true;
    registerForPushFired = false;
    if (sendAsSession) {
      locationFired = false;
    }
    startLocationUpdate();
    makeAndroidParamsRequest();
    if ((promptedLocation) || (g.d)) {
      bool = true;
    }
    promptedLocation = bool;
  }
  
  @Deprecated
  public static void syncHashedEmail(String paramString)
  {
    if (a("SyncHashedEmail()")) {}
    while (!OSUtils.a(paramString)) {
      return;
    }
    paramString = new OneSignal.8(paramString);
    if ((b == null) || (shouldRunTaskThroughQueue()))
    {
      a(OneSignal.LOG_LEVEL.ERROR, "You should initialize OneSignal before calling syncHashedEmail! Moving this operation to a pending task queue.");
      addTaskToQueue(new OneSignal.PendingTaskRunnable(paramString));
      return;
    }
    paramString.run();
  }
  
  static void t()
  {
    if (emailLogoutHandler != null)
    {
      emailLogoutHandler.onFailure(new OneSignal.EmailUpdateError(OneSignal.EmailErrorType.NETWORK, "Failed due to network failure. Will retry on next sync."));
      emailLogoutHandler = null;
    }
  }
  
  static void u()
  {
    if (emailUpdateHandler != null)
    {
      emailUpdateHandler.onSuccess();
      emailUpdateHandler = null;
    }
  }
  
  public static boolean userProvidedPrivacyConsent()
  {
    return j();
  }
  
  static void v()
  {
    if (emailUpdateHandler != null)
    {
      emailUpdateHandler.onFailure(new OneSignal.EmailUpdateError(OneSignal.EmailErrorType.NETWORK, "Failed due to network failure. Will retry on next sync."));
      emailUpdateHandler = null;
    }
  }
}
