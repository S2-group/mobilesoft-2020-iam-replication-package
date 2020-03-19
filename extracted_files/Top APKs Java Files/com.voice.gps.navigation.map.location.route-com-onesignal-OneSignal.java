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
  public static final String VERSION = "031002";
  static String a;
  private static int androidParamsReties = 0;
  private static JSONObject awl;
  private static boolean awlFired = false;
  static Context b;
  static boolean c = false;
  private static OSEmailSubscriptionState currentEmailSubscriptionState;
  private static OSPermissionState currentPermissionState;
  private static OSSubscriptionState currentSubscriptionState;
  static ExecutorService d;
  private static int deviceType = 0;
  static AtomicLong e;
  private static String emailId;
  private static OneSignal.EmailUpdateHandler emailLogoutHandler;
  private static OSObservable<OSEmailSubscriptionObserver, OSEmailSubscriptionStateChanges> emailSubscriptionStateChangesObserver;
  private static OneSignal.EmailUpdateHandler emailUpdateHandler;
  static boolean f = true;
  private static boolean foreground = false;
  static Builder g;
  private static boolean getTagsCall = false;
  static boolean h = false;
  static boolean i = false;
  private static OneSignal.IAPUpdateJob iapUpdateJob;
  private static OneSignal.IdsAvailableHandler idsAvailableHandler;
  static DelayedConsentInitializationParameters j;
  static OSPermissionState k;
  static OSSubscriptionState l;
  private static LocationGMS.LocationPoint lastLocationPoint;
  private static String lastRegistrationId;
  private static long lastTrackedFocusTime = 1L;
  private static boolean locationFired = false;
  private static OneSignal.LOG_LEVEL logCatLevel;
  static OSEmailSubscriptionState m;
  private static String mGoogleProjectNumber;
  private static boolean mGoogleProjectNumberIsRemote = false;
  private static PushRegistrator mPushRegistrator;
  private static AdvertisingIdentifierProvider mainAdIdProvider;
  private static OSUtils osUtils;
  private static OneSignal.GetTagsHandler pendingGetTagsHandler;
  private static OSObservable<OSPermissionObserver, OSPermissionStateChanges> permissionStateChangesObserver;
  private static HashSet<String> postedOpenedNotifIds = new HashSet();
  private static boolean promptedLocation = false;
  private static boolean registerForPushFired = false;
  public static String sdkType = "native";
  private static boolean sendAsSession = false;
  private static int subscribableStatus = 0;
  private static OSObservable<OSSubscriptionObserver, OSSubscriptionStateChanges> subscriptionStateChangesObserver;
  public static ConcurrentLinkedQueue<Runnable> taskQueueWaitingForInit;
  private static TrackAmazonPurchase trackAmazonPurchase;
  private static TrackFirebaseAnalytics trackFirebaseAnalytics;
  private static TrackGooglePurchase trackGooglePurchase;
  private static long unSentActiveTime = -1L;
  private static Collection<JSONArray> unprocessedOpenedNotifis;
  private static boolean useEmailAuth;
  private static String userId;
  private static OneSignal.LOG_LEVEL visualLogLevel = OneSignal.LOG_LEVEL.NONE;
  private static boolean waitingToPostStateSync;
  
  static
  {
    logCatLevel = OneSignal.LOG_LEVEL.WARN;
    taskQueueWaitingForInit = new ConcurrentLinkedQueue();
    e = new AtomicLong();
    mainAdIdProvider = new AdvertisingIdProviderGPS();
    unprocessedOpenedNotifis = new ArrayList();
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
    OneSignal.LOG_LEVEL localLOG_LEVEL = OneSignal.LOG_LEVEL.INFO;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SaveUnsentActiveTime: ");
    localStringBuilder.append(unSentActiveTime);
    a(localLOG_LEVEL, localStringBuilder.toString());
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
      OneSignal.LOG_LEVEL localLOG_LEVEL;
      if (paramBundle.containsKey("custom"))
      {
        paramBundle = new JSONObject(paramBundle.getString("custom"));
        if (paramBundle.has("i")) {
          return paramBundle.optString("i", null);
        }
        localLOG_LEVEL = OneSignal.LOG_LEVEL.DEBUG;
      }
      for (paramBundle = "Not a OneSignal formatted GCM message. No 'i' field in custom.";; paramBundle = "Not a OneSignal formatted GCM message. No 'custom' field in the bundle.")
      {
        a(localLOG_LEVEL, paramBundle);
        return null;
        localLOG_LEVEL = OneSignal.LOG_LEVEL.DEBUG;
      }
      return null;
    }
    catch (Throwable paramBundle)
    {
      a(OneSignal.LOG_LEVEL.DEBUG, "Could not parse bundle, probably not a OneSignal notification.", paramBundle);
    }
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
      if (str != null)
      {
        sendOnFocusToPlayer(str, localJSONObject, paramBoolean);
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "Generating on_focus:JSON Failed.", localThrowable);
    }
  }
  
  static void a(Context paramContext)
  {
    int n;
    if (b == null) {
      n = 1;
    } else {
      n = 0;
    }
    b = paramContext.getApplicationContext();
    if (n != 0) {
      OneSignalPrefs.startDelayedWrite();
    }
  }
  
  static void a(OneSignal.LOG_LEVEL paramLOG_LEVEL, String paramString)
  {
    a(paramLOG_LEVEL, paramString, null);
  }
  
  static void a(OneSignal.LOG_LEVEL paramLOG_LEVEL, String paramString, Throwable paramThrowable)
  {
    if (paramLOG_LEVEL.compareTo(logCatLevel) < 1) {
      if (paramLOG_LEVEL == OneSignal.LOG_LEVEL.VERBOSE) {
        Log.v("OneSignal", paramString, paramThrowable);
      } else if (paramLOG_LEVEL == OneSignal.LOG_LEVEL.DEBUG) {
        Log.d("OneSignal", paramString, paramThrowable);
      } else if (paramLOG_LEVEL == OneSignal.LOG_LEVEL.INFO) {
        Log.i("OneSignal", paramString, paramThrowable);
      } else if (paramLOG_LEVEL == OneSignal.LOG_LEVEL.WARN) {
        Log.w("OneSignal", paramString, paramThrowable);
      } else if ((paramLOG_LEVEL == OneSignal.LOG_LEVEL.ERROR) || (paramLOG_LEVEL == OneSignal.LOG_LEVEL.FATAL)) {
        Log.e("OneSignal", paramString, paramThrowable);
      }
    }
    if ((paramLOG_LEVEL.compareTo(visualLogLevel) < 1) && (ActivityLifecycleHandler.b != null)) {
      try
      {
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append(paramString);
        ((StringBuilder)localObject).append("\n");
        localObject = ((StringBuilder)localObject).toString();
        paramString = (String)localObject;
        if (paramThrowable != null)
        {
          paramString = new StringBuilder();
          paramString.append((String)localObject);
          paramString.append(paramThrowable.getMessage());
          paramString = paramString.toString();
          localObject = new StringWriter();
          paramThrowable.printStackTrace(new PrintWriter((Writer)localObject));
          paramThrowable = new StringBuilder();
          paramThrowable.append(paramString);
          paramThrowable.append(((StringWriter)localObject).toString());
          paramString = paramThrowable.toString();
        }
        OSUtils.a(new OneSignal.5(paramLOG_LEVEL, paramString));
        return;
      }
      catch (Throwable paramLOG_LEVEL)
      {
        Log.e("OneSignal", "Error showing logging message.", paramLOG_LEVEL);
      }
    }
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean, OneSignalRestClient.ResponseHandler paramResponseHandler)
  {
    if (a("sendPurchases()")) {
      return;
    }
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
      paramJSONArray = new StringBuilder();
      paramJSONArray.append("players/");
      paramJSONArray.append(k());
      paramJSONArray.append("/on_purchase");
      OneSignalRestClient.b(paramJSONArray.toString(), localJSONObject, paramResponseHandler);
      if (l() != null)
      {
        paramJSONArray = new StringBuilder();
        paramJSONArray.append("players/");
        paramJSONArray.append(l());
        paramJSONArray.append("/on_purchase");
        OneSignalRestClient.b(paramJSONArray.toString(), localJSONObject, null);
        return;
      }
    }
    catch (Throwable paramJSONArray)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "Failed to generate JSON for sendPurchases.", paramJSONArray);
    }
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramJSONArray = generateOsNotificationOpenResult(paramJSONArray, paramBoolean1, paramBoolean2);
    if ((trackFirebaseAnalytics != null) && (c(b))) {
      trackFirebaseAnalytics.b(paramJSONArray);
    }
    if (g != null)
    {
      if (g.c == null) {
        return;
      }
      g.c.notificationReceived(paramJSONArray.notification);
    }
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
      if (paramString != null)
      {
        OneSignal.LOG_LEVEL localLOG_LEVEL = OneSignal.LOG_LEVEL.WARN;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Method ");
        localStringBuilder.append(paramString);
        localStringBuilder.append(" was called before the user provided privacy consent. Your application is set to require the user's privacy consent before the OneSignal SDK can be initialized. Please ensure the user has provided consent before calling this method. You can check the latest OneSignal consent status by calling OneSignal.userProvidedPrivacyConsent()");
        a(localLOG_LEVEL, localStringBuilder.toString());
      }
      return true;
    }
    return false;
  }
  
  public static void addEmailSubscriptionObserver(@NonNull OSEmailSubscriptionObserver paramOSEmailSubscriptionObserver)
  {
    if (a("addEmailSubscriptionObserver()")) {
      return;
    }
    if (b == null)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not add email subscription observer");
      return;
    }
    c().a(paramOSEmailSubscriptionObserver);
    if (getCurrentEmailSubscriptionState(b).a(getLastEmailSubscriptionState(b))) {
      OSEmailSubscriptionChangedInternalObserver.a(getCurrentEmailSubscriptionState(b));
    }
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
    if (a("addPermissionObserver()")) {
      return;
    }
    if (b == null)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not add permission observer");
      return;
    }
    a().a(paramOSPermissionObserver);
    if (getCurrentPermissionState(b).a(getLastPermissionState(b))) {
      OSPermissionChangedInternalObserver.b(getCurrentPermissionState(b));
    }
  }
  
  public static void addSubscriptionObserver(OSSubscriptionObserver paramOSSubscriptionObserver)
  {
    if (a("addSubscriptionObserver()")) {
      return;
    }
    if (b == null)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not add subscription observer");
      return;
    }
    b().a(paramOSSubscriptionObserver);
    if (getCurrentSubscriptionState(b).a(getLastSubscriptionState(b))) {
      OSSubscriptionChangedInternalObserver.a(getCurrentSubscriptionState(b));
    }
  }
  
  private static void addTaskToQueue(OneSignal.PendingTaskRunnable paramPendingTaskRunnable)
  {
    OneSignal.PendingTaskRunnable.a(paramPendingTaskRunnable, e.incrementAndGet());
    OneSignal.LOG_LEVEL localLOG_LEVEL;
    StringBuilder localStringBuilder;
    if (d == null)
    {
      localLOG_LEVEL = OneSignal.LOG_LEVEL.INFO;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Adding a task to the pending queue with ID: ");
      localStringBuilder.append(OneSignal.PendingTaskRunnable.a(paramPendingTaskRunnable));
      a(localLOG_LEVEL, localStringBuilder.toString());
      taskQueueWaitingForInit.add(paramPendingTaskRunnable);
      return;
    }
    if (!d.isShutdown())
    {
      localLOG_LEVEL = OneSignal.LOG_LEVEL.INFO;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Executor is still running, add to the executor with ID: ");
      localStringBuilder.append(OneSignal.PendingTaskRunnable.a(paramPendingTaskRunnable));
      a(localLOG_LEVEL, localStringBuilder.toString());
      d.submit(paramPendingTaskRunnable);
    }
  }
  
  private static boolean atLogLevel(OneSignal.LOG_LEVEL paramLOG_LEVEL)
  {
    int n = paramLOG_LEVEL.compareTo(visualLogLevel);
    boolean bool = true;
    if (n >= 1)
    {
      if (paramLOG_LEVEL.compareTo(logCatLevel) < 1) {
        return true;
      }
      bool = false;
    }
    return bool;
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
    if ("".equals(emailId)) {
      paramString = null;
    } else {
      paramString = emailId;
    }
    OneSignalPrefs.saveString(str, "OS_EMAIL_ID", paramString);
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
    if ((b != null) && (!shouldRunTaskThroughQueue()))
    {
      local23.run();
      return;
    }
    OneSignal.LOG_LEVEL localLOG_LEVEL = OneSignal.LOG_LEVEL.ERROR;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("OneSignal.init has not been called. Could not clear notifications part of group ");
    localStringBuilder.append(paramString);
    localStringBuilder.append(" - movingthis operation to a waiting task queue.");
    a(localLOG_LEVEL, localStringBuilder.toString());
    addTaskToQueue(new OneSignal.PendingTaskRunnable(local23));
  }
  
  public static void cancelNotification(int paramInt)
  {
    if (a("cancelNotification()")) {
      return;
    }
    OneSignal.22 local22 = new OneSignal.22(paramInt);
    if ((b != null) && (!shouldRunTaskThroughQueue()))
    {
      local22.run();
      ((NotificationManager)b.getSystemService("notification")).cancel(paramInt);
      return;
    }
    OneSignal.LOG_LEVEL localLOG_LEVEL = OneSignal.LOG_LEVEL.ERROR;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("OneSignal.init has not been called. Could not clear notification id: ");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" at this time - movingthis operation to a waiting task queue. The notification will still be canceledfrom NotificationManager at this time.");
    a(localLOG_LEVEL, localStringBuilder.toString());
    taskQueueWaitingForInit.add(local22);
  }
  
  public static void clearOneSignalNotifications()
  {
    if (a("clearOneSignalNotifications()")) {
      return;
    }
    OneSignal.21 local21 = new OneSignal.21();
    if ((b != null) && (!shouldRunTaskThroughQueue()))
    {
      local21.run();
      return;
    }
    a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not clear notifications at this time - moving this operation toa waiting task queue.");
    addTaskToQueue(new OneSignal.PendingTaskRunnable(local21));
  }
  
  public static OneSignal.OSInFocusDisplayOption currentInFocusDisplayOption()
  {
    return getCurrentOrNewInitBuilder().i;
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
    boolean bool2 = false;
    foreground = false;
    LocationGMS.c();
    if (!c) {
      return false;
    }
    if (trackAmazonPurchase != null) {
      trackAmazonPurchase.a();
    }
    if (lastTrackedFocusTime == -1L) {
      return false;
    }
    long l1 = ((SystemClock.elapsedRealtime() - lastTrackedFocusTime) / 1000.0D + 0.5D);
    lastTrackedFocusTime = SystemClock.elapsedRealtime();
    boolean bool1 = bool2;
    if (l1 >= 0L)
    {
      if (l1 > 86400L) {
        return false;
      }
      if (b == null)
      {
        a(OneSignal.LOG_LEVEL.ERROR, "Android Context not found, please call OneSignal.init when your app starts.");
        return false;
      }
      bool1 = e();
      a(System.currentTimeMillis());
      l1 = o() + l1;
      SaveUnsentActiveTime(l1);
      if ((l1 >= 60L) && (k() != null))
      {
        if (!bool1) {
          OneSignalSyncServiceUtils.a(b);
        }
        OneSignalSyncServiceUtils.a();
        return false;
      }
      bool1 = bool2;
      if (l1 >= 60L) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  static boolean d(Context paramContext)
  {
    return OneSignalPrefs.a(OneSignalPrefs.PREFS_ONESIGNAL, "GT_VIBRATE_ENABLED", true);
  }
  
  public static void deleteTag(String paramString)
  {
    deleteTag(paramString, null);
  }
  
  public static void deleteTag(String paramString, OneSignal.ChangeTagsUpdateHandler paramChangeTagsUpdateHandler)
  {
    if (a("deleteTag()")) {
      return;
    }
    ArrayList localArrayList = new ArrayList(1);
    localArrayList.add(paramString);
    deleteTags(localArrayList, paramChangeTagsUpdateHandler);
  }
  
  public static void deleteTags(String paramString)
  {
    deleteTags(paramString, null);
  }
  
  public static void deleteTags(String paramString, OneSignal.ChangeTagsUpdateHandler paramChangeTagsUpdateHandler)
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
      sendTags(localJSONObject, paramChangeTagsUpdateHandler);
      return;
    }
    catch (Throwable paramString)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "Failed to generate JSON for deleteTags.", paramString);
    }
  }
  
  public static void deleteTags(Collection<String> paramCollection)
  {
    deleteTags(paramCollection, null);
  }
  
  public static void deleteTags(Collection<String> paramCollection, OneSignal.ChangeTagsUpdateHandler paramChangeTagsUpdateHandler)
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
      sendTags(localJSONObject, paramChangeTagsUpdateHandler);
      return;
    }
    catch (Throwable paramCollection)
    {
      a(OneSignal.LOG_LEVEL.ERROR, "Failed to generate JSON for deleteTags.", paramCollection);
    }
  }
  
  static void e(String paramString)
  {
    c(paramString);
    getCurrentEmailSubscriptionState(b).a(paramString);
    try
    {
      OneSignalStateSynchronizer.b(new JSONObject().put("parent_player_id", paramString));
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
    if (a(null)) {
      return;
    }
    Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
    if (localIntent != null)
    {
      localIntent.setFlags(268566528);
      paramContext.startActivity(localIntent);
    }
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
    int i2 = paramJSONArray.length();
    OSNotificationOpenResult localOSNotificationOpenResult = new OSNotificationOpenResult();
    OSNotification localOSNotification = new OSNotification();
    localOSNotification.isAppInFocus = p();
    localOSNotification.shown = paramBoolean1;
    localOSNotification.androidNotificationId = paramJSONArray.optJSONObject(0).optInt("notificationId");
    Object localObject1 = null;
    int i1 = 1;
    int n = 0;
    Object localObject4;
    Object localObject3;
    if (n < i2)
    {
      localObject4 = localObject1;
      try
      {
        localObject5 = paramJSONArray.getJSONObject(n);
        localObject4 = localObject1;
        localOSNotification.payload = NotificationBundleProcessor.a((JSONObject)localObject5);
        localObject3 = localObject1;
        if (localObject1 != null) {
          break label371;
        }
        localObject4 = localObject1;
        localObject3 = localObject1;
        if (!((JSONObject)localObject5).has("actionSelected")) {
          break label371;
        }
        localObject4 = localObject1;
        localObject3 = ((JSONObject)localObject5).optString("actionSelected", null);
      }
      catch (Throwable localThrowable)
      {
        label139:
        localObject3 = OneSignal.LOG_LEVEL.ERROR;
        Object localObject5 = new StringBuilder();
        ((StringBuilder)localObject5).append("Error parsing JSON item ");
        ((StringBuilder)localObject5).append(n);
        ((StringBuilder)localObject5).append("/");
        ((StringBuilder)localObject5).append(i2);
        ((StringBuilder)localObject5).append(" for callback.");
        a((OneSignal.LOG_LEVEL)localObject3, ((StringBuilder)localObject5).toString(), localThrowable);
        localObject2 = localObject4;
      }
      localObject4 = localObject3;
      if (localOSNotification.groupedNotifications == null)
      {
        localObject4 = localObject3;
        localOSNotification.groupedNotifications = new ArrayList();
      }
      localObject4 = localObject3;
      localOSNotification.groupedNotifications.add(localOSNotification.payload);
      localObject1 = localObject3;
    }
    for (;;)
    {
      n += 1;
      break;
      localOSNotificationOpenResult.notification = localOSNotification;
      localOSNotificationOpenResult.action = new OSNotificationAction();
      localOSNotificationOpenResult.action.actionID = ((String)localObject2);
      localObject3 = localOSNotificationOpenResult.action;
      if (localObject2 != null) {
        paramJSONArray = OSNotificationAction.ActionType.ActionTaken;
      } else {
        paramJSONArray = OSNotificationAction.ActionType.Opened;
      }
      ((OSNotificationAction)localObject3).type = paramJSONArray;
      if (paramBoolean2) {
        paramJSONArray = localOSNotificationOpenResult.notification;
      }
      for (Object localObject2 = OSNotification.DisplayType.InAppAlert;; localObject2 = OSNotification.DisplayType.Notification)
      {
        paramJSONArray.displayType = ((OSNotification.DisplayType)localObject2);
        return localOSNotificationOpenResult;
        paramJSONArray = localOSNotificationOpenResult.notification;
      }
      label371:
      if (i1 == 0) {
        break label139;
      }
      i1 = 0;
      localObject2 = localObject3;
    }
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
  
  public static Builder getCurrentOrNewInitBuilder()
  {
    if (g == null) {
      g = new Builder(null);
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
    case 2: 
      return OneSignal.OSInFocusDisplayOption.Notification;
    case 1: 
      return OneSignal.OSInFocusDisplayOption.InAppAlert;
    case 0: 
      return OneSignal.OSInFocusDisplayOption.None;
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
    case 6: 
      return OneSignal.LOG_LEVEL.VERBOSE;
    case 5: 
      return OneSignal.LOG_LEVEL.DEBUG;
    case 4: 
      return OneSignal.LOG_LEVEL.INFO;
    case 3: 
      return OneSignal.LOG_LEVEL.WARN;
    case 2: 
      return OneSignal.LOG_LEVEL.ERROR;
    case 1: 
      return OneSignal.LOG_LEVEL.FATAL;
    case 0: 
      return OneSignal.LOG_LEVEL.NONE;
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
    Object localObject;
    if (deviceType == 2) {
      localObject = new PushRegistratorADM();
    }
    for (;;)
    {
      mPushRegistrator = (PushRegistrator)localObject;
      break;
      if (OSUtils.a()) {
        localObject = new PushRegistratorFCM();
      } else {
        localObject = new PushRegistratorGCM();
      }
    }
    return mPushRegistrator;
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
    if (a(null)) {
      return;
    }
    notificationOpenedRESTCall(paramContext, paramJSONArray);
    if ((trackFirebaseAnalytics != null) && (c(b))) {
      trackFirebaseAnalytics.a(generateOsNotificationOpenResult(paramJSONArray, true, paramBoolean));
    }
    boolean bool1 = false;
    boolean bool2 = "DISABLE".equals(OSUtils.a(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
    if (!bool2) {
      bool1 = openURLFromNotification(paramContext, paramJSONArray);
    }
    runNotificationOpenedCallback(paramJSONArray, true, paramBoolean);
    if ((!paramBoolean) && (!bool1) && (!bool2)) {
      fireIntentFromNotificationOpen(paramContext);
    }
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
    if ((b != null) && (!shouldRunTaskThroughQueue()))
    {
      paramIdsAvailableHandler.run();
      return;
    }
    a(OneSignal.LOG_LEVEL.ERROR, "You must initialize OneSignal before getting tags! Moving this tag operation to a pending queue.");
    addTaskToQueue(new OneSignal.PendingTaskRunnable(paramIdsAvailableHandler));
  }
  
  public static void init(Context paramContext, String paramString1, String paramString2)
  {
    init(paramContext, paramString1, paramString2, null, null);
  }
  
  public static void init(Context paramContext, String paramString1, String paramString2, NotificationOpenedHandler paramNotificationOpenedHandler)
  {
    init(paramContext, paramString1, paramString2, paramNotificationOpenedHandler, null);
  }
  
  public static void init(Context paramContext, String paramString1, String paramString2, NotificationOpenedHandler paramNotificationOpenedHandler, OneSignal.NotificationReceivedHandler paramNotificationReceivedHandler)
  {
    a(paramContext);
    if ((i) && (!userProvidedPrivacyConsent()))
    {
      a(OneSignal.LOG_LEVEL.VERBOSE, "OneSignal SDK initialization delayed, user privacy consent is set to required for this application.");
      j = new DelayedConsentInitializationParameters(paramContext, paramString1, paramString2, paramNotificationOpenedHandler, paramNotificationReceivedHandler);
      return;
    }
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
    if (subscribableStatus == 64537) {
      return;
    }
    if (c)
    {
      if (g.b != null) {
        fireCallbackForOpenedNotifications();
      }
      return;
    }
    boolean bool = paramContext instanceof Activity;
    foreground = bool;
    a = paramString2;
    b(g.g);
    if (bool)
    {
      ActivityLifecycleHandler.b = (Activity)paramContext;
      NotificationRestorer.a(b);
    }
    else
    {
      ActivityLifecycleHandler.a = true;
    }
    lastTrackedFocusTime = SystemClock.elapsedRealtime();
    OneSignalStateSynchronizer.e();
    ((Application)b).registerActivityLifecycleCallbacks(new ActivityLifecycleListener());
    try
    {
      Class.forName("com.amazon.device.iap.PurchasingListener");
      trackAmazonPurchase = new TrackAmazonPurchase(b);
      paramContext = i();
      if (paramContext != null)
      {
        if (!paramContext.equals(a))
        {
          a(OneSignal.LOG_LEVEL.DEBUG, "APP ID changed, clearing user id as it is no longer valid.");
          SaveAppId(a);
          OneSignalStateSynchronizer.i();
        }
      }
      else
      {
        BadgeCountUpdater.a(0, b);
        SaveAppId(a);
      }
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
    }
    catch (ClassNotFoundException paramContext)
    {
      for (;;) {}
    }
  }
  
  private static void init(Builder paramBuilder)
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
    try
    {
      Object localObject1 = idsAvailableHandler;
      if (localObject1 == null) {
        return;
      }
      localObject1 = OneSignalStateSynchronizer.h();
      if (!OneSignalStateSynchronizer.g()) {
        localObject1 = null;
      }
      String str = k();
      if (str == null) {
        return;
      }
      idsAvailableHandler.idsAvailable(str, (String)localObject1);
      if (localObject1 != null) {
        idsAvailableHandler = null;
      }
      return;
    }
    finally {}
  }
  
  /* Error */
  private static boolean isDuplicateNotification(String paramString, Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +198 -> 199
    //   4: ldc_w 684
    //   7: aload_0
    //   8: invokevirtual 689	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   11: ifeq +5 -> 16
    //   14: iconst_0
    //   15: ireturn
    //   16: aload_1
    //   17: invokestatic 1360	com/onesignal/OneSignalDbHelper:getInstance	(Landroid/content/Context;)Lcom/onesignal/OneSignalDbHelper;
    //   20: astore 4
    //   22: aconst_null
    //   23: astore 6
    //   25: aconst_null
    //   26: astore_1
    //   27: aload 4
    //   29: invokevirtual 1363	com/onesignal/OneSignalDbHelper:b	()Landroid/database/sqlite/SQLiteDatabase;
    //   32: ldc_w 727
    //   35: iconst_1
    //   36: anewarray 686	java/lang/String
    //   39: dup
    //   40: iconst_0
    //   41: ldc_w 1365
    //   44: aastore
    //   45: ldc_w 1367
    //   48: iconst_1
    //   49: anewarray 686	java/lang/String
    //   52: dup
    //   53: iconst_0
    //   54: aload_0
    //   55: aastore
    //   56: aconst_null
    //   57: aconst_null
    //   58: aconst_null
    //   59: invokevirtual 1373	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   62: astore 4
    //   64: aload 4
    //   66: invokeinterface 1378 1 0
    //   71: istore_3
    //   72: iload_3
    //   73: istore_2
    //   74: aload 4
    //   76: ifnull +65 -> 141
    //   79: aload 4
    //   81: invokeinterface 1381 1 0
    //   86: iload_3
    //   87: istore_2
    //   88: goto +53 -> 141
    //   91: astore_0
    //   92: aload 4
    //   94: astore_1
    //   95: goto +92 -> 187
    //   98: astore 5
    //   100: goto +13 -> 113
    //   103: astore_0
    //   104: goto +83 -> 187
    //   107: astore 5
    //   109: aload 6
    //   111: astore 4
    //   113: aload 4
    //   115: astore_1
    //   116: getstatic 358	com/onesignal/OneSignal$LOG_LEVEL:ERROR	Lcom/onesignal/OneSignal$LOG_LEVEL;
    //   119: ldc_w 1383
    //   122: aload 5
    //   124: invokestatic 314	com/onesignal/OneSignal:a	(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   127: aload 4
    //   129: ifnull +10 -> 139
    //   132: aload 4
    //   134: invokeinterface 1381 1 0
    //   139: iconst_0
    //   140: istore_2
    //   141: iload_2
    //   142: ifeq +43 -> 185
    //   145: getstatic 305	com/onesignal/OneSignal$LOG_LEVEL:DEBUG	Lcom/onesignal/OneSignal$LOG_LEVEL;
    //   148: astore_1
    //   149: new 235	java/lang/StringBuilder
    //   152: dup
    //   153: invokespecial 236	java/lang/StringBuilder:<init>	()V
    //   156: astore 4
    //   158: aload 4
    //   160: ldc_w 1385
    //   163: invokevirtual 242	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: pop
    //   167: aload 4
    //   169: aload_0
    //   170: invokevirtual 242	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: pop
    //   174: aload_1
    //   175: aload 4
    //   177: invokevirtual 248	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   180: invokestatic 251	com/onesignal/OneSignal:a	(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;)V
    //   183: iconst_1
    //   184: ireturn
    //   185: iconst_0
    //   186: ireturn
    //   187: aload_1
    //   188: ifnull +9 -> 197
    //   191: aload_1
    //   192: invokeinterface 1381 1 0
    //   197: aload_0
    //   198: athrow
    //   199: iconst_0
    //   200: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	201	0	paramString	String
    //   0	201	1	paramContext	Context
    //   73	69	2	bool1	boolean
    //   71	16	3	bool2	boolean
    //   20	156	4	localObject1	Object
    //   98	1	5	localThrowable1	Throwable
    //   107	16	5	localThrowable2	Throwable
    //   23	87	6	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   64	72	91	finally
    //   64	72	98	java/lang/Throwable
    //   27	64	103	finally
    //   116	127	103	finally
    //   27	64	107	java/lang/Throwable
  }
  
  private static boolean isPastOnSessionTime()
  {
    if (sendAsSession) {
      return true;
    }
    return (System.currentTimeMillis() - getLastSessionTime(b)) / 1000L >= 30L;
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
    Object localObject2 = "";
    Object localObject1 = localObject2;
    if (paramString2 != null)
    {
      localObject1 = localObject2;
      if (atLogLevel(OneSignal.LOG_LEVEL.INFO))
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("\n");
        ((StringBuilder)localObject1).append(paramString2);
        ((StringBuilder)localObject1).append("\n");
        localObject1 = ((StringBuilder)localObject1).toString();
      }
    }
    paramString2 = OneSignal.LOG_LEVEL.WARN;
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("HTTP code: ");
    ((StringBuilder)localObject2).append(paramInt);
    ((StringBuilder)localObject2).append(" ");
    ((StringBuilder)localObject2).append(paramString1);
    ((StringBuilder)localObject2).append((String)localObject1);
    a(paramString2, ((StringBuilder)localObject2).toString(), paramThrowable);
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
    if ((b != null) && (!shouldRunTaskThroughQueue()))
    {
      paramEmailUpdateHandler.run();
      return;
    }
    a(OneSignal.LOG_LEVEL.ERROR, "You should initialize OneSignal before calling logoutEmail! Moving this operation to a pending task queue.");
    addTaskToQueue(new OneSignal.PendingTaskRunnable(paramEmailUpdateHandler));
  }
  
  static boolean m()
  {
    if (g == null) {
      return true;
    }
    return g.i == OneSignal.OSInFocusDisplayOption.Notification;
  }
  
  private static void makeAndroidParamsRequest()
  {
    if (awlFired)
    {
      registerForPushToken();
      return;
    }
    OneSignal.4 local4 = new OneSignal.4();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("apps/");
    ((StringBuilder)localObject).append(a);
    ((StringBuilder)localObject).append("/android_params.js");
    String str1 = ((StringBuilder)localObject).toString();
    String str2 = k();
    localObject = str1;
    if (str2 != null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(str1);
      ((StringBuilder)localObject).append("?player_id=");
      ((StringBuilder)localObject).append(str2);
      localObject = ((StringBuilder)localObject).toString();
    }
    a(OneSignal.LOG_LEVEL.DEBUG, "Starting request to get Android parameters.");
    OneSignalRestClient.a((String)localObject, local4);
  }
  
  static boolean n()
  {
    Builder localBuilder = g;
    boolean bool = false;
    if (localBuilder == null) {
      return false;
    }
    if (g.i == OneSignal.OSInFocusDisplayOption.InAppAlert) {
      bool = true;
    }
    return bool;
  }
  
  private static void notificationOpenedRESTCall(Context paramContext, JSONArray paramJSONArray)
  {
    int n = 0;
    while (n < paramJSONArray.length())
    {
      try
      {
        String str = new JSONObject(paramJSONArray.getJSONObject(n).optString("custom", null)).optString("i", null);
        if (!postedOpenedNotifIds.contains(str))
        {
          postedOpenedNotifIds.add(str);
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("app_id", getSavedAppId(paramContext));
          localJSONObject.put("player_id", getSavedUserId(paramContext));
          localJSONObject.put("opened", true);
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("notifications/");
          localStringBuilder.append(str);
          OneSignalRestClient.a(localStringBuilder.toString(), localJSONObject, new OneSignal.18());
        }
      }
      catch (Throwable localThrowable)
      {
        a(OneSignal.LOG_LEVEL.ERROR, "Failed to generate JSON to send notification opened.", localThrowable);
      }
      n += 1;
    }
  }
  
  static long o()
  {
    if ((unSentActiveTime == -1L) && (b != null)) {
      unSentActiveTime = OneSignalPrefs.a(OneSignalPrefs.PREFS_ONESIGNAL, "GT_UNSENT_ACTIVE_TIME", 0L);
    }
    OneSignal.LOG_LEVEL localLOG_LEVEL = OneSignal.LOG_LEVEL.INFO;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("GetUnsentActiveTime: ");
    localStringBuilder.append(unSentActiveTime);
    a(localLOG_LEVEL, localStringBuilder.toString());
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
  
  public static void onesignalLog(OneSignal.LOG_LEVEL paramLOG_LEVEL, String paramString)
  {
    a(paramLOG_LEVEL, paramString);
  }
  
  private static boolean openURLFromNotification(Context paramContext, JSONArray paramJSONArray)
  {
    boolean bool1 = a(null);
    int n = 0;
    if (bool1) {
      return false;
    }
    int i1 = paramJSONArray.length();
    boolean bool2;
    for (bool1 = false; n < i1; bool1 = bool2)
    {
      try
      {
        Object localObject1 = paramJSONArray.getJSONObject(n);
        if (!((JSONObject)localObject1).has("custom"))
        {
          bool2 = bool1;
        }
        else
        {
          localObject1 = new JSONObject(((JSONObject)localObject1).optString("custom"));
          bool2 = bool1;
          if (((JSONObject)localObject1).has("u"))
          {
            localObject2 = ((JSONObject)localObject1).optString("u", null);
            localObject1 = localObject2;
            if (!((String)localObject2).contains("://"))
            {
              localObject1 = new StringBuilder();
              ((StringBuilder)localObject1).append("http://");
              ((StringBuilder)localObject1).append((String)localObject2);
              localObject1 = ((StringBuilder)localObject1).toString();
            }
            localObject1 = new Intent("android.intent.action.VIEW", Uri.parse(((String)localObject1).trim()));
            ((Intent)localObject1).addFlags(1476919296);
            paramContext.startActivity((Intent)localObject1);
            bool2 = true;
          }
        }
      }
      catch (Throwable localThrowable)
      {
        Object localObject2 = OneSignal.LOG_LEVEL.ERROR;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Error parsing JSON item ");
        localStringBuilder.append(n);
        localStringBuilder.append("/");
        localStringBuilder.append(i1);
        localStringBuilder.append(" for launching a web URL.");
        a((OneSignal.LOG_LEVEL)localObject2, localStringBuilder.toString(), localThrowable);
        bool2 = bool1;
      }
      n += 1;
    }
    return bool1;
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
      StringBuilder localStringBuilder;
      for (;;) {}
    }
    paramPostNotificationResponseHandler = OneSignal.LOG_LEVEL.ERROR;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("Invalid postNotification JSON format: ");
    localStringBuilder.append(paramString);
    a(paramPostNotificationResponseHandler, localStringBuilder.toString());
  }
  
  public static void postNotification(JSONObject paramJSONObject, OneSignal.PostNotificationResponseHandler paramPostNotificationResponseHandler)
  {
    if (a("postNotification()")) {
      return;
    }
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
      if (paramPostNotificationResponseHandler != null) {
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
    }
  }
  
  public static void promptLocation()
  {
    if (a("promptLocation()")) {
      return;
    }
    OneSignal.20 local20 = new OneSignal.20();
    if ((b != null) && (!shouldRunTaskThroughQueue()))
    {
      local20.run();
      return;
    }
    a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not prompt for location at this time - moving this operation to awaiting queue.");
    addTaskToQueue(new OneSignal.PendingTaskRunnable(local20));
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
    OneSignal.LOG_LEVEL localLOG_LEVEL = OneSignal.LOG_LEVEL.DEBUG;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("registerUser: registerForPushFired:");
    localStringBuilder.append(registerForPushFired);
    localStringBuilder.append(", locationFired: ");
    localStringBuilder.append(locationFired);
    localStringBuilder.append(", awlFired: ");
    localStringBuilder.append(awlFired);
    a(localLOG_LEVEL, localStringBuilder.toString());
    if ((registerForPushFired) && (locationFired))
    {
      if (!awlFired) {
        return;
      }
      new Thread(new OneSignal.7(), "OS_REG_USER").start();
    }
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
    localJSONObject.put("sdk", "031002");
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
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          int n;
          String str;
          continue;
          n += 1;
        }
      }
      if (n < ((List)localObject1).size())
      {
        ((MessageDigest)localObject3).update(((PackageInfo)((List)localObject1).get(n)).packageName.getBytes());
        str = Base64.encodeToString(((MessageDigest)localObject3).digest(), 2);
        if (awl.has(str)) {
          ((JSONArray)localObject2).put(str);
        }
      }
      else
      {
        localJSONObject.put("pkgs", localObject2);
        localJSONObject.put("net_type", osUtils.d());
        localJSONObject.put("carrier", osUtils.e());
        localJSONObject.put("rooted", RootToolsInternalMethods.a());
        OneSignalStateSynchronizer.a(localJSONObject);
        localJSONObject = new JSONObject();
        localJSONObject.put("identifier", lastRegistrationId);
        localJSONObject.put("subscribableStatus", subscribableStatus);
        localJSONObject.put("androidPermission", r());
        localJSONObject.put("device_type", deviceType);
        OneSignalStateSynchronizer.b(localJSONObject);
        if ((f) && (lastLocationPoint != null)) {
          OneSignalStateSynchronizer.a(lastLocationPoint);
        }
        if (sendAsSession) {
          OneSignalStateSynchronizer.k();
        }
        waitingToPostStateSync = false;
        return;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
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
  
  public static boolean requiresUserPrivacyConsent()
  {
    return (i) && (!userProvidedPrivacyConsent());
  }
  
  private static void runNotificationOpenedCallback(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((g != null) && (g.b != null))
    {
      fireNotificationOpenedHandler(generateOsNotificationOpenResult(paramJSONArray, paramBoolean1, paramBoolean2));
      return;
    }
    unprocessedOpenedNotifis.add(paramJSONArray);
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
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("players/");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append("/on_focus");
    paramString = ((StringBuilder)localObject).toString();
    localObject = new OneSignal.6();
    if (paramBoolean)
    {
      OneSignalRestClient.d(paramString, paramJSONObject, (OneSignalRestClient.ResponseHandler)localObject);
      return;
    }
    OneSignalRestClient.b(paramString, paramJSONObject, (OneSignalRestClient.ResponseHandler)localObject);
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
    sendTags(paramJSONObject, null);
  }
  
  public static void sendTags(JSONObject paramJSONObject, OneSignal.ChangeTagsUpdateHandler paramChangeTagsUpdateHandler)
  {
    if (a("sendTags()")) {
      return;
    }
    paramJSONObject = new OneSignal.11(paramJSONObject, paramChangeTagsUpdateHandler);
    if ((b != null) && (!shouldRunTaskThroughQueue()))
    {
      paramJSONObject.run();
      return;
    }
    a(OneSignal.LOG_LEVEL.ERROR, "You must initialize OneSignal before modifying tags!Moving this operation to a pending task queue.");
    if (paramChangeTagsUpdateHandler != null) {
      paramChangeTagsUpdateHandler.onFailure(new OneSignal.SendTagsError(-1, "You must initialize OneSignal before modifying tags!Moving this operation to a pending task queue."));
    }
    addTaskToQueue(new OneSignal.PendingTaskRunnable(paramJSONObject));
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
    if ((b != null) && (!shouldRunTaskThroughQueue()))
    {
      paramString1.run();
      return;
    }
    a(OneSignal.LOG_LEVEL.ERROR, "You should initialize OneSignal before calling setEmail! Moving this operation to a pending task queue.");
    addTaskToQueue(new OneSignal.PendingTaskRunnable(paramString1));
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
    OneSignal.LOG_LEVEL localLOG_LEVEL = OneSignal.LOG_LEVEL.DEBUG;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("shareLocation:");
    localStringBuilder.append(f);
    a(localLOG_LEVEL, localStringBuilder.toString());
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
    if ((b != null) && (!shouldRunTaskThroughQueue()))
    {
      local19.run();
      return;
    }
    a(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Moving subscription action to a waiting task queue.");
    addTaskToQueue(new OneSignal.PendingTaskRunnable(local19));
  }
  
  private static boolean shouldRunTaskThroughQueue()
  {
    if ((c) && (d == null)) {
      return false;
    }
    if ((!c) && (d == null)) {
      return true;
    }
    return (d != null) && (!d.isShutdown());
  }
  
  public static Builder startInit(Context paramContext)
  {
    return new Builder(paramContext, null);
  }
  
  private static void startLocationUpdate()
  {
    OneSignal.2 local2 = new OneSignal.2();
    boolean bool;
    if ((g.d) && (!promptedLocation)) {
      bool = true;
    } else {
      bool = false;
    }
    LocationGMS.a(b, bool, local2);
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
    if (waitingToPostStateSync) {
      return;
    }
    boolean bool2 = true;
    waitingToPostStateSync = true;
    registerForPushFired = false;
    if (sendAsSession) {
      locationFired = false;
    }
    startLocationUpdate();
    makeAndroidParamsRequest();
    boolean bool1 = bool2;
    if (!promptedLocation) {
      if (g.d) {
        bool1 = bool2;
      } else {
        bool1 = false;
      }
    }
    promptedLocation = bool1;
  }
  
  @Deprecated
  public static void syncHashedEmail(String paramString)
  {
    if (a("SyncHashedEmail()")) {
      return;
    }
    if (!OSUtils.a(paramString)) {
      return;
    }
    paramString = new OneSignal.8(paramString);
    if ((b != null) && (!shouldRunTaskThroughQueue()))
    {
      paramString.run();
      return;
    }
    a(OneSignal.LOG_LEVEL.ERROR, "You should initialize OneSignal before calling syncHashedEmail! Moving this operation to a pending task queue.");
    addTaskToQueue(new OneSignal.PendingTaskRunnable(paramString));
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
  
  public static class Builder
  {
    Context a;
    OneSignal.NotificationOpenedHandler b;
    OneSignal.NotificationReceivedHandler c;
    boolean d;
    boolean e;
    boolean f;
    boolean g;
    boolean h;
    OneSignal.OSInFocusDisplayOption i = OneSignal.OSInFocusDisplayOption.InAppAlert;
    
    private Builder() {}
    
    private Builder(Context paramContext)
    {
      this.a = paramContext;
    }
    
    private void setDisplayOptionCarryOver(boolean paramBoolean)
    {
      this.h = paramBoolean;
    }
    
    public Builder autoPromptLocation(boolean paramBoolean)
    {
      this.d = paramBoolean;
      return this;
    }
    
    public Builder disableGmsMissingPrompt(boolean paramBoolean)
    {
      this.e = paramBoolean;
      return this;
    }
    
    public Builder filterOtherGCMReceivers(boolean paramBoolean)
    {
      this.g = paramBoolean;
      return this;
    }
    
    public Builder inFocusDisplaying(OneSignal.OSInFocusDisplayOption paramOSInFocusDisplayOption)
    {
      OneSignal.getCurrentOrNewInitBuilder().h = false;
      this.i = paramOSInFocusDisplayOption;
      return this;
    }
    
    public void init()
    {
      OneSignal.a(this);
    }
    
    public Builder setNotificationOpenedHandler(OneSignal.NotificationOpenedHandler paramNotificationOpenedHandler)
    {
      this.b = paramNotificationOpenedHandler;
      return this;
    }
    
    public Builder setNotificationReceivedHandler(OneSignal.NotificationReceivedHandler paramNotificationReceivedHandler)
    {
      this.c = paramNotificationReceivedHandler;
      return this;
    }
    
    public Builder unsubscribeWhenNotificationsAreDisabled(boolean paramBoolean)
    {
      this.f = paramBoolean;
      return this;
    }
  }
  
  public static abstract interface NotificationOpenedHandler
  {
    public abstract void notificationOpened(OSNotificationOpenResult paramOSNotificationOpenResult);
  }
}
