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
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
  static final long MIN_ON_FOCUS_TIME = 60L;
  private static final long MIN_ON_SESSION_TIME = 30L;
  public static final String VERSION = "031003";
  private static int androidParamsReties = 0;
  static Context appContext;
  static String appId;
  private static JSONObject awl;
  private static boolean awlFired;
  private static OSEmailSubscriptionState currentEmailSubscriptionState;
  private static OSPermissionState currentPermissionState;
  private static OSSubscriptionState currentSubscriptionState;
  static DelayedConsentInitializationParameters delayedInitParams;
  private static int deviceType;
  private static String emailId;
  private static OneSignal.EmailUpdateHandler emailLogoutHandler;
  private static OSObservable<OSEmailSubscriptionObserver, OSEmailSubscriptionStateChanges> emailSubscriptionStateChangesObserver;
  private static OneSignal.EmailUpdateHandler emailUpdateHandler;
  private static boolean foreground;
  private static boolean getTagsCall;
  private static OneSignal.IAPUpdateJob iapUpdateJob;
  private static OneSignal.IdsAvailableHandler idsAvailableHandler;
  static boolean initDone;
  static OSEmailSubscriptionState lastEmailSubscriptionState;
  private static LocationGMS.LocationPoint lastLocationPoint;
  static OSPermissionState lastPermissionState;
  private static String lastRegistrationId;
  static OSSubscriptionState lastSubscriptionState;
  static AtomicLong lastTaskId;
  private static long lastTrackedFocusTime;
  private static boolean locationFired;
  private static OneSignal.LOG_LEVEL logCatLevel;
  static boolean mEnterp;
  private static String mGoogleProjectNumber;
  private static boolean mGoogleProjectNumberIsRemote;
  static OneSignal.Builder mInitBuilder;
  private static PushRegistrator mPushRegistrator;
  private static AdvertisingIdentifierProvider mainAdIdProvider;
  private static OSUtils osUtils;
  private static OneSignal.GetTagsHandler pendingGetTagsHandler;
  static ExecutorService pendingTaskExecutor;
  private static OSObservable<OSPermissionObserver, OSPermissionStateChanges> permissionStateChangesObserver;
  private static HashSet<String> postedOpenedNotifIds;
  private static boolean promptedLocation;
  private static boolean registerForPushFired;
  static boolean requiresUserPrivacyConsent;
  public static String sdkType;
  private static boolean sendAsSession;
  static boolean shareLocation;
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
    lastTaskId = new AtomicLong();
    lastTrackedFocusTime = 1L;
    unSentActiveTime = -1L;
    mainAdIdProvider = new AdvertisingIdProviderGPS();
    sdkType = "native";
    shareLocation = true;
    unprocessedOpenedNotifis = new ArrayList();
    postedOpenedNotifIds = new HashSet();
    requiresUserPrivacyConsent = false;
  }
  
  public OneSignal() {}
  
  static long GetUnsentActiveTime()
  {
    if ((unSentActiveTime == -1L) && (appContext != null)) {
      unSentActiveTime = OneSignalPrefs.getLong(OneSignalPrefs.PREFS_ONESIGNAL, "GT_UNSENT_ACTIVE_TIME", 0L);
    }
    Log(OneSignal.LOG_LEVEL.INFO, "GetUnsentActiveTime: " + unSentActiveTime);
    return unSentActiveTime;
  }
  
  static void Log(OneSignal.LOG_LEVEL paramLOG_LEVEL, String paramString)
  {
    Log(paramLOG_LEVEL, paramString, null);
  }
  
  static void Log(OneSignal.LOG_LEVEL paramLOG_LEVEL, String paramString, Throwable paramThrowable)
  {
    if (paramLOG_LEVEL.compareTo(logCatLevel) < 1)
    {
      if (paramLOG_LEVEL != OneSignal.LOG_LEVEL.VERBOSE) {
        break label147;
      }
      Log.v("OneSignal", paramString, paramThrowable);
    }
    for (;;)
    {
      if ((paramLOG_LEVEL.compareTo(visualLogLevel) < 1) && (ActivityLifecycleHandler.curActivity != null)) {}
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
        OSUtils.runOnMainUIThread(new OneSignal.5(paramLOG_LEVEL, paramString));
        return;
      }
      catch (Throwable paramLOG_LEVEL)
      {
        label147:
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
  
  private static void SaveAppId(String paramString)
  {
    if (appContext == null) {
      return;
    }
    OneSignalPrefs.saveString(OneSignalPrefs.PREFS_ONESIGNAL, "GT_APP_ID", paramString);
  }
  
  private static void SaveUnsentActiveTime(long paramLong)
  {
    unSentActiveTime = paramLong;
    if (appContext == null) {
      return;
    }
    Log(OneSignal.LOG_LEVEL.INFO, "SaveUnsentActiveTime: " + unSentActiveTime);
    OneSignalPrefs.saveLong(OneSignalPrefs.PREFS_ONESIGNAL, "GT_UNSENT_ACTIVE_TIME", paramLong);
  }
  
  public static void addEmailSubscriptionObserver(@NonNull OSEmailSubscriptionObserver paramOSEmailSubscriptionObserver)
  {
    if (appContext == null) {
      Log(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not add email subscription observer");
    }
    do
    {
      return;
      getEmailSubscriptionStateChangesObserver().addObserver(paramOSEmailSubscriptionObserver);
    } while (!getCurrentEmailSubscriptionState(appContext).compare(getLastEmailSubscriptionState(appContext)));
    OSEmailSubscriptionChangedInternalObserver.fireChangesToPublicObserver(getCurrentEmailSubscriptionState(appContext));
  }
  
  private static void addNetType(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject.put("net_type", osUtils.getNetType());
      return;
    }
    catch (Throwable paramJSONObject) {}
  }
  
  public static void addPermissionObserver(OSPermissionObserver paramOSPermissionObserver)
  {
    if (appContext == null) {
      Log(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not add permission observer");
    }
    do
    {
      return;
      getPermissionStateChangesObserver().addObserver(paramOSPermissionObserver);
    } while (!getCurrentPermissionState(appContext).compare(getLastPermissionState(appContext)));
    OSPermissionChangedInternalObserver.fireChangesToPublicObserver(getCurrentPermissionState(appContext));
  }
  
  public static void addSubscriptionObserver(OSSubscriptionObserver paramOSSubscriptionObserver)
  {
    if (appContext == null) {
      Log(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not add subscription observer");
    }
    do
    {
      return;
      getSubscriptionStateChangesObserver().addObserver(paramOSSubscriptionObserver);
    } while (!getCurrentSubscriptionState(appContext).compare(getLastSubscriptionState(appContext)));
    OSSubscriptionChangedInternalObserver.fireChangesToPublicObserver(getCurrentSubscriptionState(appContext));
  }
  
  private static void addTaskToQueue(OneSignal.PendingTaskRunnable paramPendingTaskRunnable)
  {
    OneSignal.PendingTaskRunnable.access$402(paramPendingTaskRunnable, lastTaskId.incrementAndGet());
    if (pendingTaskExecutor == null)
    {
      Log(OneSignal.LOG_LEVEL.INFO, "Adding a task to the pending queue with ID: " + OneSignal.PendingTaskRunnable.access$400(paramPendingTaskRunnable));
      taskQueueWaitingForInit.add(paramPendingTaskRunnable);
    }
    while (pendingTaskExecutor.isShutdown()) {
      return;
    }
    Log(OneSignal.LOG_LEVEL.INFO, "Executor is still running, add to the executor with ID: " + OneSignal.PendingTaskRunnable.access$400(paramPendingTaskRunnable));
    pendingTaskExecutor.submit(paramPendingTaskRunnable);
  }
  
  static boolean areNotificationsEnabledForSubscribedState()
  {
    if (mInitBuilder.mUnsubscribeWhenNotificationsAreDisabled) {
      return OSUtils.areNotificationsEnabled(appContext);
    }
    return true;
  }
  
  private static boolean atLogLevel(OneSignal.LOG_LEVEL paramLOG_LEVEL)
  {
    return (paramLOG_LEVEL.compareTo(visualLogLevel) < 1) || (paramLOG_LEVEL.compareTo(logCatLevel) < 1);
  }
  
  public static void cancelGroupedNotifications(String paramString)
  {
    if (shouldLogUserPrivacyConsentErrorMessageForMethodName("cancelGroupedNotifications()")) {
      return;
    }
    OneSignal.23 local23 = new OneSignal.23(paramString);
    if ((appContext == null) || (shouldRunTaskThroughQueue()))
    {
      Log(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not clear notifications part of group " + paramString + " - movingthis operation to a waiting task queue.");
      addTaskToQueue(new OneSignal.PendingTaskRunnable(local23));
      return;
    }
    local23.run();
  }
  
  public static void cancelNotification(int paramInt)
  {
    if (shouldLogUserPrivacyConsentErrorMessageForMethodName("cancelNotification()")) {
      return;
    }
    OneSignal.22 local22 = new OneSignal.22(paramInt);
    if ((appContext == null) || (shouldRunTaskThroughQueue()))
    {
      Log(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not clear notification id: " + paramInt + " at this time - movingthis operation to a waiting task queue. The notification will still be canceledfrom NotificationManager at this time.");
      taskQueueWaitingForInit.add(local22);
      return;
    }
    local22.run();
    ((NotificationManager)appContext.getSystemService("notification")).cancel(paramInt);
  }
  
  public static void clearOneSignalNotifications()
  {
    if (shouldLogUserPrivacyConsentErrorMessageForMethodName("clearOneSignalNotifications()")) {
      return;
    }
    OneSignal.21 local21 = new OneSignal.21();
    if ((appContext == null) || (shouldRunTaskThroughQueue()))
    {
      Log(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not clear notifications at this time - moving this operation toa waiting task queue.");
      addTaskToQueue(new OneSignal.PendingTaskRunnable(local21));
      return;
    }
    local21.run();
  }
  
  public static OneSignal.OSInFocusDisplayOption currentInFocusDisplayOption()
  {
    return getCurrentOrNewInitBuilder().mDisplayOption;
  }
  
  public static void deleteTag(String paramString)
  {
    deleteTag(paramString, null);
  }
  
  public static void deleteTag(String paramString, OneSignal.ChangeTagsUpdateHandler paramChangeTagsUpdateHandler)
  {
    if (shouldLogUserPrivacyConsentErrorMessageForMethodName("deleteTag()")) {
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
    if (shouldLogUserPrivacyConsentErrorMessageForMethodName("deleteTags()")) {
      return;
    }
    try
    {
      JSONObject localJSONObject = new JSONObject();
      paramString = new JSONArray(paramString);
      int i = 0;
      while (i < paramString.length())
      {
        localJSONObject.put(paramString.getString(i), "");
        i += 1;
      }
      sendTags(localJSONObject, paramChangeTagsUpdateHandler);
      return;
    }
    catch (Throwable paramString)
    {
      Log(OneSignal.LOG_LEVEL.ERROR, "Failed to generate JSON for deleteTags.", paramString);
    }
  }
  
  public static void deleteTags(Collection<String> paramCollection)
  {
    deleteTags(paramCollection, null);
  }
  
  public static void deleteTags(Collection<String> paramCollection, OneSignal.ChangeTagsUpdateHandler paramChangeTagsUpdateHandler)
  {
    if (shouldLogUserPrivacyConsentErrorMessageForMethodName("deleteTags()")) {
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
    }
    catch (Throwable paramCollection)
    {
      Log(OneSignal.LOG_LEVEL.ERROR, "Failed to generate JSON for deleteTags.", paramCollection);
      return;
    }
  }
  
  public static void enableSound(boolean paramBoolean)
  {
    if (appContext == null) {
      return;
    }
    OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "GT_SOUND_ENABLED", paramBoolean);
  }
  
  public static void enableVibrate(boolean paramBoolean)
  {
    if (appContext == null) {
      return;
    }
    OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "GT_VIBRATE_ENABLED", paramBoolean);
  }
  
  private static void fireCallbackForOpenedNotifications()
  {
    Iterator localIterator = unprocessedOpenedNotifis.iterator();
    while (localIterator.hasNext()) {
      runNotificationOpenedCallback((JSONArray)localIterator.next(), true, false);
    }
    unprocessedOpenedNotifis.clear();
  }
  
  static void fireEmailUpdateFailure()
  {
    if (emailUpdateHandler != null)
    {
      emailUpdateHandler.onFailure(new OneSignal.EmailUpdateError(OneSignal.EmailErrorType.NETWORK, "Failed due to network failure. Will retry on next sync."));
      emailUpdateHandler = null;
    }
  }
  
  static void fireEmailUpdateSuccess()
  {
    if (emailUpdateHandler != null)
    {
      emailUpdateHandler.onSuccess();
      emailUpdateHandler = null;
    }
  }
  
  static void fireIdsAvailableCallback()
  {
    if (idsAvailableHandler != null) {
      OSUtils.runOnMainUIThread(new OneSignal.16());
    }
  }
  
  private static void fireIntentFromNotificationOpen(Context paramContext)
  {
    if (shouldLogUserPrivacyConsentErrorMessageForMethodName(null)) {}
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
    OSUtils.runOnMainUIThread(new OneSignal.17(paramOSNotificationOpenResult));
  }
  
  @NonNull
  private static OSNotificationOpenResult generateOsNotificationOpenResult(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    int k = paramJSONArray.length();
    int j = 1;
    OSNotificationOpenResult localOSNotificationOpenResult = new OSNotificationOpenResult();
    OSNotification localOSNotification = new OSNotification();
    localOSNotification.isAppInFocus = isAppActive();
    localOSNotification.shown = paramBoolean1;
    localOSNotification.androidNotificationId = paramJSONArray.optJSONObject(0).optInt("notificationId");
    Object localObject1 = null;
    int i = 0;
    for (;;)
    {
      Object localObject4;
      if (i < k) {
        localObject4 = localObject1;
      }
      Object localObject3;
      do
      {
        try
        {
          JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
          localObject4 = localObject1;
          localOSNotification.payload = NotificationBundleProcessor.OSNotificationPayloadFrom(localJSONObject);
          localObject3 = localObject1;
          if (localObject1 != null) {
            continue;
          }
          localObject4 = localObject1;
          localObject3 = localObject1;
          if (!localJSONObject.has("actionSelected")) {
            continue;
          }
          localObject4 = localObject1;
          localObject3 = localJSONObject.optString("actionSelected", null);
        }
        catch (Throwable localThrowable)
        {
          Log(OneSignal.LOG_LEVEL.ERROR, "Error parsing JSON item " + i + "/" + k + " for callback.", localThrowable);
          localObject2 = localObject4;
          break;
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
        break;
        localOSNotificationOpenResult.notification = localOSNotification;
        localOSNotificationOpenResult.action = new OSNotificationAction();
        localOSNotificationOpenResult.action.actionID = localObject2;
        localObject3 = localOSNotificationOpenResult.action;
        if (localObject2 != null) {}
        for (paramJSONArray = OSNotificationAction.ActionType.ActionTaken;; paramJSONArray = OSNotificationAction.ActionType.Opened)
        {
          ((OSNotificationAction)localObject3).type = paramJSONArray;
          if (!paramBoolean2) {
            break;
          }
          localOSNotificationOpenResult.notification.displayType = OSNotification.DisplayType.InAppAlert;
          return localOSNotificationOpenResult;
        }
        localOSNotificationOpenResult.notification.displayType = OSNotification.DisplayType.Notification;
        return localOSNotificationOpenResult;
      } while (j == 0);
      j = 0;
      Object localObject2 = localObject3;
      i += 1;
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
      currentEmailSubscriptionState.observable.addObserverStrong(new OSEmailSubscriptionChangedInternalObserver());
    }
    return currentEmailSubscriptionState;
  }
  
  public static OneSignal.Builder getCurrentOrNewInitBuilder()
  {
    if (mInitBuilder == null) {
      mInitBuilder = new OneSignal.Builder(null);
    }
    return mInitBuilder;
  }
  
  private static OSPermissionState getCurrentPermissionState(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (currentPermissionState == null)
    {
      currentPermissionState = new OSPermissionState(false);
      currentPermissionState.observable.addObserverStrong(new OSPermissionChangedInternalObserver());
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
      getCurrentPermissionState(paramContext).observable.addObserver(currentSubscriptionState);
      currentSubscriptionState.observable.addObserverStrong(new OSSubscriptionChangedInternalObserver());
    }
    return currentSubscriptionState;
  }
  
  static String getEmailId()
  {
    if ("".equals(emailId)) {
      return null;
    }
    if ((emailId == null) && (appContext != null)) {
      emailId = OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "OS_EMAIL_ID", null);
    }
    return emailId;
  }
  
  static OSObservable<OSEmailSubscriptionObserver, OSEmailSubscriptionStateChanges> getEmailSubscriptionStateChangesObserver()
  {
    if (emailSubscriptionStateChangesObserver == null) {
      emailSubscriptionStateChangesObserver = new OSObservable("onOSEmailSubscriptionChanged", true);
    }
    return emailSubscriptionStateChangesObserver;
  }
  
  static boolean getFilterOtherGCMReceivers(Context paramContext)
  {
    return OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, "OS_FILTER_OTHER_GCM_RECEIVERS", false);
  }
  
  static boolean getFirebaseAnalyticsEnabled(Context paramContext)
  {
    return OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, "GT_FIREBASE_TRACKING_ENABLED", false);
  }
  
  static boolean getInAppAlertNotificationEnabled()
  {
    if (mInitBuilder == null) {}
    while (mInitBuilder.mDisplayOption != OneSignal.OSInFocusDisplayOption.InAppAlert) {
      return false;
    }
    return true;
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
    if (lastEmailSubscriptionState == null) {
      lastEmailSubscriptionState = new OSEmailSubscriptionState(true);
    }
    return lastEmailSubscriptionState;
  }
  
  private static OSPermissionState getLastPermissionState(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (lastPermissionState == null) {
      lastPermissionState = new OSPermissionState(true);
    }
    return lastPermissionState;
  }
  
  private static long getLastSessionTime(Context paramContext)
  {
    return OneSignalPrefs.getLong(OneSignalPrefs.PREFS_ONESIGNAL, "OS_LAST_SESSION_TIME", -31000L);
  }
  
  private static OSSubscriptionState getLastSubscriptionState(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (lastSubscriptionState == null) {
      lastSubscriptionState = new OSSubscriptionState(true, false);
    }
    return lastSubscriptionState;
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
  
  static String getNotificationIdFromGCMBundle(Bundle paramBundle)
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
        Log(OneSignal.LOG_LEVEL.DEBUG, "Not a OneSignal formatted GCM message. No 'i' field in custom.");
        return null;
      }
    }
    catch (Throwable paramBundle)
    {
      Log(OneSignal.LOG_LEVEL.DEBUG, "Could not parse bundle, probably not a OneSignal notification.", paramBundle);
      return null;
    }
    Log(OneSignal.LOG_LEVEL.DEBUG, "Not a OneSignal formatted GCM message. No 'custom' field in the bundle.");
    return null;
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
  
  static boolean getNotificationsWhenActiveEnabled()
  {
    if (mInitBuilder == null) {}
    while (mInitBuilder.mDisplayOption == OneSignal.OSInFocusDisplayOption.Notification) {
      return true;
    }
    return false;
  }
  
  static OSObservable<OSPermissionObserver, OSPermissionStateChanges> getPermissionStateChangesObserver()
  {
    if (permissionStateChangesObserver == null) {
      permissionStateChangesObserver = new OSObservable("onOSPermissionChanged", true);
    }
    return permissionStateChangesObserver;
  }
  
  public static OSPermissionSubscriptionState getPermissionSubscriptionState()
  {
    if (shouldLogUserPrivacyConsentErrorMessageForMethodName("getPermissionSubscriptionState()")) {
      return null;
    }
    if (appContext == null)
    {
      Log(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not get OSPermissionSubscriptionState");
      return null;
    }
    OSPermissionSubscriptionState localOSPermissionSubscriptionState = new OSPermissionSubscriptionState();
    localOSPermissionSubscriptionState.subscriptionStatus = getCurrentSubscriptionState(appContext);
    localOSPermissionSubscriptionState.permissionStatus = getCurrentPermissionState(appContext);
    localOSPermissionSubscriptionState.emailSubscriptionStatus = getCurrentEmailSubscriptionState(appContext);
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
      if (OSUtils.hasFCMLibrary()) {
        mPushRegistrator = new PushRegistratorFCM();
      } else {
        mPushRegistrator = new PushRegistratorGCM();
      }
    }
  }
  
  static String getSavedAppId()
  {
    return getSavedAppId(appContext);
  }
  
  private static String getSavedAppId(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "GT_APP_ID", null);
  }
  
  static boolean getSavedUserConsentStatus()
  {
    return OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_USER_PROVIDED_CONSENT", false);
  }
  
  private static String getSavedUserId(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "GT_PLAYER_ID", null);
  }
  
  static boolean getSoundEnabled(Context paramContext)
  {
    return OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, "GT_SOUND_ENABLED", true);
  }
  
  static OSObservable<OSSubscriptionObserver, OSSubscriptionStateChanges> getSubscriptionStateChangesObserver()
  {
    if (subscriptionStateChangesObserver == null) {
      subscriptionStateChangesObserver = new OSObservable("onOSSubscriptionChanged", true);
    }
    return subscriptionStateChangesObserver;
  }
  
  public static void getTags(OneSignal.GetTagsHandler paramGetTagsHandler)
  {
    if (shouldLogUserPrivacyConsentErrorMessageForMethodName("getTags()")) {
      return;
    }
    pendingGetTagsHandler = paramGetTagsHandler;
    paramGetTagsHandler = new OneSignal.13(paramGetTagsHandler);
    if (appContext == null)
    {
      Log(OneSignal.LOG_LEVEL.ERROR, "You must initialize OneSignal before getting tags! Moving this tag operation to a pending queue.");
      taskQueueWaitingForInit.add(paramGetTagsHandler);
      return;
    }
    paramGetTagsHandler.run();
  }
  
  private static int getTimeZoneOffset()
  {
    TimeZone localTimeZone = Calendar.getInstance().getTimeZone();
    int j = localTimeZone.getRawOffset();
    int i = j;
    if (localTimeZone.inDaylightTime(new Date())) {
      i = j + localTimeZone.getDSTSavings();
    }
    return i / 1000;
  }
  
  static String getUserId()
  {
    if ((userId == null) && (appContext != null)) {
      userId = OneSignalPrefs.getString(OneSignalPrefs.PREFS_ONESIGNAL, "GT_PLAYER_ID", null);
    }
    return userId;
  }
  
  static boolean getVibrate(Context paramContext)
  {
    return OneSignalPrefs.getBool(OneSignalPrefs.PREFS_ONESIGNAL, "GT_VIBRATE_ENABLED", true);
  }
  
  static void handleFailedEmailLogout()
  {
    if (emailLogoutHandler != null)
    {
      emailLogoutHandler.onFailure(new OneSignal.EmailUpdateError(OneSignal.EmailErrorType.NETWORK, "Failed due to network failure. Will retry on next sync."));
      emailLogoutHandler = null;
    }
  }
  
  public static void handleNotificationOpen(Context paramContext, JSONArray paramJSONArray, boolean paramBoolean)
  {
    if (shouldLogUserPrivacyConsentErrorMessageForMethodName(null)) {}
    boolean bool1;
    boolean bool2;
    do
    {
      return;
      notificationOpenedRESTCall(paramContext, paramJSONArray);
      if ((trackFirebaseAnalytics != null) && (getFirebaseAnalyticsEnabled(appContext))) {
        trackFirebaseAnalytics.trackOpenedEvent(generateOsNotificationOpenResult(paramJSONArray, true, paramBoolean));
      }
      bool1 = false;
      bool2 = "DISABLE".equals(OSUtils.getManifestMeta(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
      if (!bool2) {
        bool1 = openURLFromNotification(paramContext, paramJSONArray);
      }
      runNotificationOpenedCallback(paramJSONArray, true, paramBoolean);
    } while ((paramBoolean) || (bool1) || (bool2));
    fireIntentFromNotificationOpen(paramContext);
  }
  
  static void handleNotificationReceived(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramJSONArray = generateOsNotificationOpenResult(paramJSONArray, paramBoolean1, paramBoolean2);
    if ((trackFirebaseAnalytics != null) && (getFirebaseAnalyticsEnabled(appContext))) {
      trackFirebaseAnalytics.trackReceivedEvent(paramJSONArray);
    }
    if ((mInitBuilder == null) || (mInitBuilder.mNotificationReceivedHandler == null)) {
      return;
    }
    mInitBuilder.mNotificationReceivedHandler.notificationReceived(paramJSONArray.notification);
  }
  
  static void handleSuccessfulEmailLogout()
  {
    if (emailLogoutHandler != null)
    {
      emailLogoutHandler.onSuccess();
      emailLogoutHandler = null;
    }
  }
  
  public static void idsAvailable(OneSignal.IdsAvailableHandler paramIdsAvailableHandler)
  {
    if (shouldLogUserPrivacyConsentErrorMessageForMethodName("idsAvailable()")) {
      return;
    }
    idsAvailableHandler = paramIdsAvailableHandler;
    paramIdsAvailableHandler = new OneSignal.15();
    if ((appContext == null) || (shouldRunTaskThroughQueue()))
    {
      Log(OneSignal.LOG_LEVEL.ERROR, "You must initialize OneSignal before getting tags! Moving this tag operation to a pending queue.");
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
    setAppContext(paramContext);
    if ((requiresUserPrivacyConsent) && (!userProvidedPrivacyConsent()))
    {
      Log(OneSignal.LOG_LEVEL.VERBOSE, "OneSignal SDK initialization delayed, user privacy consent is set to required for this application.");
      delayedInitParams = new DelayedConsentInitializationParameters(paramContext, paramString1, paramString2, paramNotificationOpenedHandler, paramNotificationReceivedHandler);
    }
    do
    {
      do
      {
        return;
        mInitBuilder = getCurrentOrNewInitBuilder();
        mInitBuilder.mDisplayOptionCarryOver = false;
        mInitBuilder.mNotificationOpenedHandler = paramNotificationOpenedHandler;
        mInitBuilder.mNotificationReceivedHandler = paramNotificationReceivedHandler;
        if (!mGoogleProjectNumberIsRemote) {
          mGoogleProjectNumber = paramString1;
        }
        osUtils = new OSUtils();
        deviceType = osUtils.getDeviceType();
        subscribableStatus = osUtils.initializationChecker(paramContext, deviceType, paramString2);
      } while (subscribableStatus == 64537);
      if (!initDone) {
        break;
      }
    } while (mInitBuilder.mNotificationOpenedHandler == null);
    fireCallbackForOpenedNotifications();
    return;
    boolean bool = paramContext instanceof Activity;
    foreground = bool;
    appId = paramString2;
    saveFilterOtherGCMReceivers(mInitBuilder.mFilterOtherGCMReceivers);
    if (bool)
    {
      ActivityLifecycleHandler.curActivity = (Activity)paramContext;
      NotificationRestorer.asyncRestore(appContext);
    }
    for (;;)
    {
      lastTrackedFocusTime = SystemClock.elapsedRealtime();
      OneSignalStateSynchronizer.initUserState();
      ((Application)appContext).registerActivityLifecycleCallbacks(new ActivityLifecycleListener());
      try
      {
        Class.forName("com.amazon.device.iap.PurchasingListener");
        trackAmazonPurchase = new TrackAmazonPurchase(appContext);
        paramContext = getSavedAppId();
        if (paramContext != null) {
          if (!paramContext.equals(appId))
          {
            Log(OneSignal.LOG_LEVEL.DEBUG, "APP ID changed, clearing user id as it is no longer valid.");
            SaveAppId(appId);
            OneSignalStateSynchronizer.resetCurrentState();
          }
        }
        for (;;)
        {
          OSPermissionChangedInternalObserver.handleInternalChanges(getCurrentPermissionState(appContext));
          if ((foreground) || (getUserId() == null))
          {
            sendAsSession = isPastOnSessionTime();
            setLastSessionTime(System.currentTimeMillis());
            startRegistrationOrOnSession();
          }
          if (mInitBuilder.mNotificationOpenedHandler != null) {
            fireCallbackForOpenedNotifications();
          }
          if (TrackGooglePurchase.CanTrack(appContext)) {
            trackGooglePurchase = new TrackGooglePurchase(appContext);
          }
          if (TrackFirebaseAnalytics.CanTrack()) {
            trackFirebaseAnalytics = new TrackFirebaseAnalytics(appContext);
          }
          PushRegistratorFCM.disableFirebaseInstanceIdService(appContext);
          initDone = true;
          startPendingTasks();
          return;
          ActivityLifecycleHandler.nextResumeIsFirstActivity = true;
          break;
          BadgeCountUpdater.updateCount(0, appContext);
          SaveAppId(appId);
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
    if (getCurrentOrNewInitBuilder().mDisplayOptionCarryOver) {
      paramBuilder.mDisplayOption = getCurrentOrNewInitBuilder().mDisplayOption;
    }
    mInitBuilder = paramBuilder;
    Context localContext = mInitBuilder.mContext;
    mInitBuilder.mContext = null;
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
      init(localContext, paramBuilder, localBundle.getString("onesignal_app_id"), mInitBuilder.mNotificationOpenedHandler, mInitBuilder.mNotificationReceivedHandler);
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
  
  /* Error */
  private static void internalFireIdsAvailableCallback()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 745	com/onesignal/OneSignal:idsAvailableHandler	Lcom/onesignal/OneSignal$IdsAvailableHandler;
    //   6: astore_0
    //   7: aload_0
    //   8: ifnonnull +7 -> 15
    //   11: ldc 2
    //   13: monitorexit
    //   14: return
    //   15: invokestatic 1333	com/onesignal/OneSignalStateSynchronizer:getRegistrationId	()Ljava/lang/String;
    //   18: astore_0
    //   19: invokestatic 1336	com/onesignal/OneSignalStateSynchronizer:getSubscribed	()Z
    //   22: ifne +5 -> 27
    //   25: aconst_null
    //   26: astore_0
    //   27: invokestatic 1242	com/onesignal/OneSignal:getUserId	()Ljava/lang/String;
    //   30: astore_1
    //   31: aload_1
    //   32: ifnull -21 -> 11
    //   35: getstatic 745	com/onesignal/OneSignal:idsAvailableHandler	Lcom/onesignal/OneSignal$IdsAvailableHandler;
    //   38: aload_1
    //   39: aload_0
    //   40: invokeinterface 1339 3 0
    //   45: aload_0
    //   46: ifnull -35 -> 11
    //   49: aconst_null
    //   50: putstatic 745	com/onesignal/OneSignal:idsAvailableHandler	Lcom/onesignal/OneSignal$IdsAvailableHandler;
    //   53: goto -42 -> 11
    //   56: astore_0
    //   57: ldc 2
    //   59: monitorexit
    //   60: aload_0
    //   61: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   6	40	0	localObject1	Object
    //   56	5	0	localObject2	Object
    //   30	9	1	str	String
    // Exception table:
    //   from	to	target	type
    //   3	7	56	finally
    //   15	19	56	finally
    //   19	25	56	finally
    //   27	31	56	finally
    //   35	45	56	finally
    //   49	53	56	finally
  }
  
  static boolean isAppActive()
  {
    return (initDone) && (isForeground());
  }
  
  private static boolean isDuplicateNotification(String paramString, Context paramContext)
  {
    if ((paramString == null) || ("".equals(paramString))) {
      return false;
    }
    boolean bool2 = false;
    Object localObject2 = OneSignalDbHelper.getInstance(paramContext);
    Object localObject1 = null;
    paramContext = null;
    label177:
    try
    {
      localObject2 = ((OneSignalDbHelper)localObject2).getReadableDbWithRetries().query("notification", new String[] { "notification_id" }, "notification_id = ?", new String[] { paramString }, null, null, null);
      paramContext = (Context)localObject2;
      localObject1 = localObject2;
      bool1 = ((Cursor)localObject2).moveToFirst();
      bool2 = bool1;
      bool1 = bool2;
      if (localObject2 != null)
      {
        ((Cursor)localObject2).close();
        bool1 = bool2;
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        localObject1 = paramContext;
        Log(OneSignal.LOG_LEVEL.ERROR, "Could not check for duplicate, assuming unique.", localThrowable);
        boolean bool1 = bool2;
        if (paramContext != null)
        {
          paramContext.close();
          bool1 = bool2;
        }
      }
    }
    finally
    {
      if (localObject1 == null) {
        break label177;
      }
      ((Cursor)localObject1).close();
    }
    if (bool1)
    {
      Log(OneSignal.LOG_LEVEL.DEBUG, "Duplicate GCM message received, skip processing of " + paramString);
      return true;
    }
    return false;
  }
  
  static boolean isForeground()
  {
    return foreground;
  }
  
  private static boolean isPastOnSessionTime()
  {
    if (sendAsSession) {}
    while ((System.currentTimeMillis() - getLastSessionTime(appContext)) / 1000L >= 30L) {
      return true;
    }
    return false;
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
    Log(OneSignal.LOG_LEVEL.WARN, "HTTP code: " + paramInt + " " + paramString1 + str1, paramThrowable);
  }
  
  public static void logoutEmail()
  {
    logoutEmail(null);
  }
  
  public static void logoutEmail(@Nullable OneSignal.EmailUpdateHandler paramEmailUpdateHandler)
  {
    if (shouldLogUserPrivacyConsentErrorMessageForMethodName("logoutEmail()")) {
      return;
    }
    if (getEmailId() == null)
    {
      if (paramEmailUpdateHandler != null) {
        paramEmailUpdateHandler.onFailure(new OneSignal.EmailUpdateError(OneSignal.EmailErrorType.INVALID_OPERATION, "logoutEmail not valid as email was not set or already logged out!"));
      }
      Log(OneSignal.LOG_LEVEL.ERROR, "logoutEmail not valid as email was not set or already logged out!");
      return;
    }
    emailLogoutHandler = paramEmailUpdateHandler;
    paramEmailUpdateHandler = new OneSignal.10();
    if ((appContext == null) || (shouldRunTaskThroughQueue()))
    {
      Log(OneSignal.LOG_LEVEL.ERROR, "You should initialize OneSignal before calling logoutEmail! Moving this operation to a pending task queue.");
      addTaskToQueue(new OneSignal.PendingTaskRunnable(paramEmailUpdateHandler));
      return;
    }
    paramEmailUpdateHandler.run();
  }
  
  private static void makeAndroidParamsRequest()
  {
    if (awlFired)
    {
      registerForPushToken();
      return;
    }
    OneSignal.4 local4 = new OneSignal.4();
    String str2 = "apps/" + appId + "/android_params.js";
    String str3 = getUserId();
    String str1 = str2;
    if (str3 != null) {
      str1 = str2 + "?player_id=" + str3;
    }
    Log(OneSignal.LOG_LEVEL.DEBUG, "Starting request to get Android parameters.");
    OneSignalRestClient.get(str1, local4);
  }
  
  static boolean notValidOrDuplicated(Context paramContext, JSONObject paramJSONObject)
  {
    paramJSONObject = getNotificationIdFromGCMJsonPayload(paramJSONObject);
    return (paramJSONObject == null) || (isDuplicateNotification(paramJSONObject, paramContext));
  }
  
  private static void notificationOpenedRESTCall(Context paramContext, JSONArray paramJSONArray)
  {
    int i = 0;
    for (;;)
    {
      if (i < paramJSONArray.length()) {
        try
        {
          String str = new JSONObject(paramJSONArray.getJSONObject(i).optString("custom", null)).optString("i", null);
          if (postedOpenedNotifIds.contains(str)) {
            break label153;
          }
          postedOpenedNotifIds.add(str);
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("app_id", getSavedAppId(paramContext));
          localJSONObject.put("player_id", getSavedUserId(paramContext));
          localJSONObject.put("opened", true);
          OneSignalRestClient.put("notifications/" + str, localJSONObject, new OneSignal.18());
        }
        catch (Throwable localThrowable)
        {
          Log(OneSignal.LOG_LEVEL.ERROR, "Failed to generate JSON to send notification opened.", localThrowable);
        }
      }
      return;
      label153:
      i += 1;
    }
  }
  
  static void onAppFocus()
  {
    foreground = true;
    LocationGMS.onFocusChange();
    lastTrackedFocusTime = SystemClock.elapsedRealtime();
    sendAsSession = isPastOnSessionTime();
    setLastSessionTime(System.currentTimeMillis());
    startRegistrationOrOnSession();
    if (trackGooglePurchase != null) {
      trackGooglePurchase.trackIAP();
    }
    NotificationRestorer.asyncRestore(appContext);
    getCurrentPermissionState(appContext).refreshAsTo();
    if ((trackFirebaseAnalytics != null) && (getFirebaseAnalyticsEnabled(appContext))) {
      trackFirebaseAnalytics.trackInfluenceOpenEvent();
    }
    OneSignalSyncServiceUtils.cancelSyncTask(appContext);
  }
  
  @WorkerThread
  static boolean onAppLostFocus()
  {
    foreground = false;
    LocationGMS.onFocusChange();
    if (!initDone) {}
    long l;
    boolean bool;
    do
    {
      do
      {
        do
        {
          return false;
          if (trackAmazonPurchase != null) {
            trackAmazonPurchase.checkListener();
          }
        } while (lastTrackedFocusTime == -1L);
        l = ((SystemClock.elapsedRealtime() - lastTrackedFocusTime) / 1000.0D + 0.5D);
        lastTrackedFocusTime = SystemClock.elapsedRealtime();
      } while ((l < 0L) || (l > 86400L));
      if (appContext == null)
      {
        Log(OneSignal.LOG_LEVEL.ERROR, "Android Context not found, please call OneSignal.init when your app starts.");
        return false;
      }
      bool = scheduleSyncService();
      setLastSessionTime(System.currentTimeMillis());
      l = GetUnsentActiveTime() + l;
      SaveUnsentActiveTime(l);
      if ((l >= 60L) && (getUserId() != null)) {
        break;
      }
    } while (l < 60L);
    return true;
    if (!bool) {
      OneSignalSyncServiceUtils.scheduleSyncTask(appContext);
    }
    OneSignalSyncServiceUtils.syncOnFocusTime();
    return false;
  }
  
  private static void onTaskRan(long paramLong)
  {
    if (lastTaskId.get() == paramLong)
    {
      Log(OneSignal.LOG_LEVEL.INFO, "Last Pending Task has ran, shutting down");
      pendingTaskExecutor.shutdown();
    }
  }
  
  public static void onesignalLog(OneSignal.LOG_LEVEL paramLOG_LEVEL, String paramString)
  {
    Log(paramLOG_LEVEL, paramString);
  }
  
  private static boolean openURLFromNotification(Context paramContext, JSONArray paramJSONArray)
  {
    boolean bool2;
    if (shouldLogUserPrivacyConsentErrorMessageForMethodName(null))
    {
      bool2 = false;
      return bool2;
    }
    int j = paramJSONArray.length();
    boolean bool1 = false;
    int i = 0;
    for (;;)
    {
      bool2 = bool1;
      if (i >= j) {
        break;
      }
      try
      {
        Object localObject = paramJSONArray.getJSONObject(i);
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
        Log(OneSignal.LOG_LEVEL.ERROR, "Error parsing JSON item " + i + "/" + j + " for launching a web URL.", localThrowable);
        bool2 = bool1;
      }
      i += 1;
      bool1 = bool2;
    }
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
      Log(OneSignal.LOG_LEVEL.ERROR, "Invalid postNotification JSON format: " + paramString);
    }
  }
  
  public static void postNotification(JSONObject paramJSONObject, OneSignal.PostNotificationResponseHandler paramPostNotificationResponseHandler)
  {
    if (shouldLogUserPrivacyConsentErrorMessageForMethodName("postNotification()")) {}
    do
    {
      return;
      try
      {
        if (!paramJSONObject.has("app_id")) {
          paramJSONObject.put("app_id", getSavedAppId());
        }
        OneSignalRestClient.post("notifications/", paramJSONObject, new OneSignal.12(paramPostNotificationResponseHandler));
        return;
      }
      catch (JSONException paramJSONObject)
      {
        Log(OneSignal.LOG_LEVEL.ERROR, "HTTP create notification json exception!", paramJSONObject);
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
    if (shouldLogUserPrivacyConsentErrorMessageForMethodName("promptLocation()")) {
      return;
    }
    OneSignal.20 local20 = new OneSignal.20();
    if ((appContext == null) || (shouldRunTaskThroughQueue()))
    {
      Log(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not prompt for location at this time - moving this operation to awaiting queue.");
      addTaskToQueue(new OneSignal.PendingTaskRunnable(local20));
      return;
    }
    local20.run();
  }
  
  public static void provideUserConsent(boolean paramBoolean)
  {
    boolean bool = userProvidedPrivacyConsent();
    saveUserConsentStatus(paramBoolean);
    if ((!bool) && (paramBoolean) && (delayedInitParams != null))
    {
      init(delayedInitParams.context, delayedInitParams.googleProjectNumber, delayedInitParams.appId, delayedInitParams.openedHandler, delayedInitParams.receivedHandler);
      delayedInitParams = null;
    }
  }
  
  private static boolean pushStatusRuntimeError(int paramInt)
  {
    return paramInt < -6;
  }
  
  private static void registerForPushToken()
  {
    getPushRegistrator().registerForPush(appContext, mGoogleProjectNumber, new OneSignal.3());
  }
  
  private static void registerUser()
  {
    Log(OneSignal.LOG_LEVEL.DEBUG, "registerUser: registerForPushFired:" + registerForPushFired + ", locationFired: " + locationFired + ", awlFired: " + awlFired);
    if ((!registerForPushFired) || (!locationFired) || (!awlFired)) {
      return;
    }
    new Thread(new OneSignal.7(), "OS_REG_USER").start();
  }
  
  private static void registerUserTask()
    throws JSONException
  {
    Object localObject2 = appContext.getPackageName();
    Object localObject1 = appContext.getPackageManager();
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("app_id", appId);
    Object localObject3 = mainAdIdProvider.getIdentifier(appContext);
    if (localObject3 != null) {
      localJSONObject.put("ad_id", localObject3);
    }
    localJSONObject.put("device_os", Build.VERSION.RELEASE);
    localJSONObject.put("timezone", getTimeZoneOffset());
    localJSONObject.put("language", OSUtils.getCorrectedLanguage());
    localJSONObject.put("sdk", "031003");
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
        i = 0;
        if (i < ((List)localObject1).size())
        {
          ((MessageDigest)localObject3).update(((PackageInfo)((List)localObject1).get(i)).packageName.getBytes());
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
      localJSONObject.put("net_type", osUtils.getNetType());
      localJSONObject.put("carrier", osUtils.getCarrierName());
      localJSONObject.put("rooted", RootToolsInternalMethods.isRooted());
      OneSignalStateSynchronizer.updateDeviceInfo(localJSONObject);
      localJSONObject = new JSONObject();
      localJSONObject.put("identifier", lastRegistrationId);
      localJSONObject.put("subscribableStatus", subscribableStatus);
      localJSONObject.put("androidPermission", areNotificationsEnabledForSubscribedState());
      localJSONObject.put("device_type", deviceType);
      OneSignalStateSynchronizer.updatePushState(localJSONObject);
      if ((shareLocation) && (lastLocationPoint != null)) {
        OneSignalStateSynchronizer.updateLocation(lastLocationPoint);
      }
      if (sendAsSession) {
        OneSignalStateSynchronizer.setSyncAsNewSession();
      }
      waitingToPostStateSync = false;
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        int i;
        continue;
        label387:
        i += 1;
      }
    }
  }
  
  public static void removeEmailSubscriptionObserver(@NonNull OSEmailSubscriptionObserver paramOSEmailSubscriptionObserver)
  {
    if (appContext == null)
    {
      Log(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not modify email subscription observer");
      return;
    }
    getEmailSubscriptionStateChangesObserver().removeObserver(paramOSEmailSubscriptionObserver);
  }
  
  public static void removeNotificationOpenedHandler()
  {
    getCurrentOrNewInitBuilder().mNotificationOpenedHandler = null;
  }
  
  public static void removeNotificationReceivedHandler()
  {
    getCurrentOrNewInitBuilder().mNotificationReceivedHandler = null;
  }
  
  public static void removePermissionObserver(OSPermissionObserver paramOSPermissionObserver)
  {
    if (appContext == null)
    {
      Log(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not modify permission observer");
      return;
    }
    getPermissionStateChangesObserver().removeObserver(paramOSPermissionObserver);
  }
  
  public static void removeSubscriptionObserver(OSSubscriptionObserver paramOSSubscriptionObserver)
  {
    if (appContext == null)
    {
      Log(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not modify subscription observer");
      return;
    }
    getSubscriptionStateChangesObserver().removeObserver(paramOSSubscriptionObserver);
  }
  
  public static boolean requiresUserPrivacyConsent()
  {
    return (requiresUserPrivacyConsent) && (!userProvidedPrivacyConsent());
  }
  
  private static void runNotificationOpenedCallback(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((mInitBuilder == null) || (mInitBuilder.mNotificationOpenedHandler == null))
    {
      unprocessedOpenedNotifis.add(paramJSONArray);
      return;
    }
    fireNotificationOpenedHandler(generateOsNotificationOpenResult(paramJSONArray, paramBoolean1, paramBoolean2));
  }
  
  static void saveEmailId(String paramString)
  {
    emailId = paramString;
    if (appContext == null) {
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
  
  static void saveFilterOtherGCMReceivers(boolean paramBoolean)
  {
    if (appContext == null) {
      return;
    }
    OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "OS_FILTER_OTHER_GCM_RECEIVERS", paramBoolean);
  }
  
  static void saveUserConsentStatus(boolean paramBoolean)
  {
    OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "ONESIGNAL_USER_PROVIDED_CONSENT", paramBoolean);
  }
  
  static void saveUserId(String paramString)
  {
    userId = paramString;
    if (appContext == null) {
      return;
    }
    OneSignalPrefs.saveString(OneSignalPrefs.PREFS_ONESIGNAL, "GT_PLAYER_ID", userId);
  }
  
  static boolean scheduleSyncService()
  {
    boolean bool = OneSignalStateSynchronizer.persist();
    if (bool) {
      OneSignalSyncServiceUtils.scheduleSyncTask(appContext);
    }
    return (LocationGMS.scheduleUpdate(appContext)) || (bool);
  }
  
  static void sendOnFocus(long paramLong, boolean paramBoolean)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject().put("app_id", appId).put("type", 1).put("state", "ping").put("active_time", paramLong);
      addNetType(localJSONObject);
      sendOnFocusToPlayer(getUserId(), localJSONObject, paramBoolean);
      String str = getEmailId();
      if (str != null) {
        sendOnFocusToPlayer(str, localJSONObject, paramBoolean);
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      Log(OneSignal.LOG_LEVEL.ERROR, "Generating on_focus:JSON Failed.", localThrowable);
    }
  }
  
  private static void sendOnFocusToPlayer(String paramString, JSONObject paramJSONObject, boolean paramBoolean)
  {
    paramString = "players/" + paramString + "/on_focus";
    OneSignal.6 local6 = new OneSignal.6();
    if (paramBoolean)
    {
      OneSignalRestClient.postSync(paramString, paramJSONObject, local6);
      return;
    }
    OneSignalRestClient.post(paramString, paramJSONObject, local6);
  }
  
  static void sendPurchases(JSONArray paramJSONArray, boolean paramBoolean, OneSignalRestClient.ResponseHandler paramResponseHandler)
  {
    if (shouldLogUserPrivacyConsentErrorMessageForMethodName("sendPurchases()")) {}
    for (;;)
    {
      return;
      if (getUserId() == null)
      {
        iapUpdateJob = new OneSignal.IAPUpdateJob(paramJSONArray);
        iapUpdateJob.newAsExisting = paramBoolean;
        iapUpdateJob.restResponseHandler = paramResponseHandler;
        return;
      }
      try
      {
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("app_id", appId);
        if (paramBoolean) {
          localJSONObject.put("existing", true);
        }
        localJSONObject.put("purchases", paramJSONArray);
        OneSignalRestClient.post("players/" + getUserId() + "/on_purchase", localJSONObject, paramResponseHandler);
        if (getEmailId() != null)
        {
          OneSignalRestClient.post("players/" + getEmailId() + "/on_purchase", localJSONObject, null);
          return;
        }
      }
      catch (Throwable paramJSONArray)
      {
        Log(OneSignal.LOG_LEVEL.ERROR, "Failed to generate JSON for sendPurchases.", paramJSONArray);
      }
    }
  }
  
  public static void sendTag(String paramString1, String paramString2)
  {
    if (shouldLogUserPrivacyConsentErrorMessageForMethodName("sendTag()")) {
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
      Log(OneSignal.LOG_LEVEL.ERROR, "Generating JSONObject for sendTags failed!", paramString);
    }
  }
  
  public static void sendTags(JSONObject paramJSONObject)
  {
    sendTags(paramJSONObject, null);
  }
  
  public static void sendTags(JSONObject paramJSONObject, OneSignal.ChangeTagsUpdateHandler paramChangeTagsUpdateHandler)
  {
    if (shouldLogUserPrivacyConsentErrorMessageForMethodName("sendTags()")) {
      return;
    }
    paramJSONObject = new OneSignal.11(paramJSONObject, paramChangeTagsUpdateHandler);
    if ((appContext == null) || (shouldRunTaskThroughQueue()))
    {
      Log(OneSignal.LOG_LEVEL.ERROR, "You must initialize OneSignal before modifying tags!Moving this operation to a pending task queue.");
      if (paramChangeTagsUpdateHandler != null) {
        paramChangeTagsUpdateHandler.onFailure(new OneSignal.SendTagsError(-1, "You must initialize OneSignal before modifying tags!Moving this operation to a pending task queue."));
      }
      addTaskToQueue(new OneSignal.PendingTaskRunnable(paramJSONObject));
      return;
    }
    paramJSONObject.run();
  }
  
  static void setAppContext(Context paramContext)
  {
    if (appContext == null) {}
    for (int i = 1;; i = 0)
    {
      appContext = paramContext.getApplicationContext();
      if (i != 0) {
        OneSignalPrefs.startDelayedWrite();
      }
      return;
    }
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
    if (shouldLogUserPrivacyConsentErrorMessageForMethodName("setEmail()")) {
      return;
    }
    if (!OSUtils.isValidEmail(paramString1))
    {
      if (paramEmailUpdateHandler != null) {
        paramEmailUpdateHandler.onFailure(new OneSignal.EmailUpdateError(OneSignal.EmailErrorType.VALIDATION, "Email is invalid"));
      }
      Log(OneSignal.LOG_LEVEL.ERROR, "Email is invalid");
      return;
    }
    if ((useEmailAuth) && (paramString2 == null))
    {
      if (paramEmailUpdateHandler != null) {
        paramEmailUpdateHandler.onFailure(new OneSignal.EmailUpdateError(OneSignal.EmailErrorType.REQUIRES_EMAIL_AUTH, "Email authentication (auth token) is set to REQUIRED for this application. Please provide an auth token from your backend server or change the setting in the OneSignal dashboard."));
      }
      Log(OneSignal.LOG_LEVEL.ERROR, "Email authentication (auth token) is set to REQUIRED for this application. Please provide an auth token from your backend server or change the setting in the OneSignal dashboard.");
      return;
    }
    emailUpdateHandler = paramEmailUpdateHandler;
    paramString1 = new OneSignal.9(paramString1, paramString2);
    if ((appContext == null) || (shouldRunTaskThroughQueue()))
    {
      Log(OneSignal.LOG_LEVEL.ERROR, "You should initialize OneSignal before calling setEmail! Moving this operation to a pending task queue.");
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
    getCurrentOrNewInitBuilder().mDisplayOptionCarryOver = true;
    getCurrentOrNewInitBuilder().mDisplayOption = paramOSInFocusDisplayOption;
  }
  
  static void setLastSessionTime(long paramLong)
  {
    OneSignalPrefs.saveLong(OneSignalPrefs.PREFS_ONESIGNAL, "OS_LAST_SESSION_TIME", paramLong);
  }
  
  public static void setLocationShared(boolean paramBoolean)
  {
    if (shouldLogUserPrivacyConsentErrorMessageForMethodName("setLocationShared()")) {
      return;
    }
    shareLocation = paramBoolean;
    if (!paramBoolean) {
      OneSignalStateSynchronizer.clearLocation();
    }
    Log(OneSignal.LOG_LEVEL.DEBUG, "shareLocation:" + shareLocation);
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
    if ((requiresUserPrivacyConsent) && (!paramBoolean))
    {
      Log(OneSignal.LOG_LEVEL.ERROR, "Cannot change requiresUserPrivacyConsent() from TRUE to FALSE");
      return;
    }
    requiresUserPrivacyConsent = paramBoolean;
  }
  
  public static void setSubscription(boolean paramBoolean)
  {
    if (shouldLogUserPrivacyConsentErrorMessageForMethodName("setSubscription()")) {
      return;
    }
    OneSignal.19 local19 = new OneSignal.19(paramBoolean);
    if ((appContext == null) || (shouldRunTaskThroughQueue()))
    {
      Log(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Moving subscription action to a waiting task queue.");
      addTaskToQueue(new OneSignal.PendingTaskRunnable(local19));
      return;
    }
    local19.run();
  }
  
  static boolean shouldLogUserPrivacyConsentErrorMessageForMethodName(String paramString)
  {
    if ((requiresUserPrivacyConsent) && (!userProvidedPrivacyConsent()))
    {
      if (paramString != null) {
        Log(OneSignal.LOG_LEVEL.WARN, "Method " + paramString + " was called before the user provided privacy consent. Your application is set to require the user's privacy consent before the OneSignal SDK can be initialized. Please ensure the user has provided consent before calling this method. You can check the latest OneSignal consent status by calling OneSignal.userProvidedPrivacyConsent()");
      }
      return true;
    }
    return false;
  }
  
  private static boolean shouldRunTaskThroughQueue()
  {
    if ((initDone) && (pendingTaskExecutor == null)) {}
    do
    {
      return false;
      if ((!initDone) && (pendingTaskExecutor == null)) {
        return true;
      }
    } while ((pendingTaskExecutor == null) || (pendingTaskExecutor.isShutdown()));
    return true;
  }
  
  public static OneSignal.Builder startInit(Context paramContext)
  {
    return new OneSignal.Builder(paramContext, null);
  }
  
  private static void startLocationUpdate()
  {
    OneSignal.2 local2 = new OneSignal.2();
    if ((mInitBuilder.mPromptLocation) && (!promptedLocation)) {}
    for (boolean bool = true;; bool = false)
    {
      LocationGMS.getLocation(appContext, bool, local2);
      return;
    }
  }
  
  private static void startPendingTasks()
  {
    if (!taskQueueWaitingForInit.isEmpty())
    {
      pendingTaskExecutor = Executors.newSingleThreadExecutor(new OneSignal.1());
      while (!taskQueueWaitingForInit.isEmpty()) {
        pendingTaskExecutor.submit((Runnable)taskQueueWaitingForInit.poll());
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
    if ((promptedLocation) || (mInitBuilder.mPromptLocation)) {
      bool = true;
    }
    promptedLocation = bool;
  }
  
  @Deprecated
  public static void syncHashedEmail(String paramString)
  {
    if (shouldLogUserPrivacyConsentErrorMessageForMethodName("SyncHashedEmail()")) {}
    while (!OSUtils.isValidEmail(paramString)) {
      return;
    }
    paramString = new OneSignal.8(paramString);
    if ((appContext == null) || (shouldRunTaskThroughQueue()))
    {
      Log(OneSignal.LOG_LEVEL.ERROR, "You should initialize OneSignal before calling syncHashedEmail! Moving this operation to a pending task queue.");
      addTaskToQueue(new OneSignal.PendingTaskRunnable(paramString));
      return;
    }
    paramString.run();
  }
  
  static void updateEmailIdDependents(String paramString)
  {
    saveEmailId(paramString);
    getCurrentEmailSubscriptionState(appContext).setEmailUserId(paramString);
    try
    {
      OneSignalStateSynchronizer.updatePushState(new JSONObject().put("parent_player_id", paramString));
      return;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  static void updateOnSessionDependents()
  {
    sendAsSession = false;
    setLastSessionTime(System.currentTimeMillis());
  }
  
  static void updateUserIdDependents(String paramString)
  {
    saveUserId(paramString);
    fireIdsAvailableCallback();
    internalFireGetTagsCallback(pendingGetTagsHandler);
    getCurrentSubscriptionState(appContext).setUserId(paramString);
    if (iapUpdateJob != null)
    {
      sendPurchases(iapUpdateJob.toReport, iapUpdateJob.newAsExisting, iapUpdateJob.restResponseHandler);
      iapUpdateJob = null;
    }
    OneSignalStateSynchronizer.refreshEmailState();
    OneSignalChromeTab.setup(appContext, appId, paramString, AdvertisingIdProviderGPS.getLastValue());
  }
  
  public static boolean userProvidedPrivacyConsent()
  {
    return getSavedUserConsentStatus();
  }
}
