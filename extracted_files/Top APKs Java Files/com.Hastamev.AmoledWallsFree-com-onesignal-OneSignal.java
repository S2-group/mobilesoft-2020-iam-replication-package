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
  static final long MIN_ON_FOCUS_TIME = 60L;
  private static final long MIN_ON_SESSION_TIME = 30L;
  public static final String VERSION = "030803";
  private static int androidParamsReties = 0;
  static Context appContext;
  static String appId;
  private static JSONObject awl;
  private static boolean awlFired = false;
  private static OSEmailSubscriptionState currentEmailSubscriptionState;
  private static OSPermissionState currentPermissionState;
  private static OSSubscriptionState currentSubscriptionState;
  private static int deviceType = 0;
  private static String emailId;
  private static OneSignal.EmailUpdateHandler emailLogoutHandler;
  private static OSObservable emailSubscriptionStateChangesObserver;
  private static OneSignal.EmailUpdateHandler emailUpdateHandler;
  private static boolean foreground = false;
  private static boolean getTagsCall = false;
  private static OneSignal.IAPUpdateJob iapUpdateJob;
  private static OneSignal.IdsAvailableHandler idsAvailableHandler;
  static boolean initDone = false;
  static OSEmailSubscriptionState lastEmailSubscriptionState;
  private static LocationGMS.LocationPoint lastLocationPoint;
  static OSPermissionState lastPermissionState;
  private static String lastRegistrationId;
  static OSSubscriptionState lastSubscriptionState;
  static AtomicLong lastTaskId;
  private static long lastTrackedFocusTime = 1L;
  private static boolean locationFired = false;
  private static OneSignal.LOG_LEVEL logCatLevel;
  static boolean mEnterp = false;
  private static String mGoogleProjectNumber;
  private static boolean mGoogleProjectNumberIsRemote = false;
  static OneSignal.Builder mInitBuilder;
  private static AdvertisingIdentifierProvider mainAdIdProvider;
  private static OSUtils osUtils;
  private static OneSignal.GetTagsHandler pendingGetTagsHandler;
  static ExecutorService pendingTaskExecutor;
  private static OSObservable permissionStateChangesObserver;
  private static HashSet postedOpenedNotifIds = new HashSet();
  private static boolean promptedLocation = false;
  private static boolean registerForPushFired = false;
  public static String sdkType = "native";
  private static boolean sendAsSession = false;
  static boolean shareLocation = true;
  private static int subscribableStatus = 0;
  private static OSObservable subscriptionStateChangesObserver;
  public static ConcurrentLinkedQueue taskQueueWaitingForInit;
  private static TrackAmazonPurchase trackAmazonPurchase;
  private static TrackFirebaseAnalytics trackFirebaseAnalytics;
  private static TrackGooglePurchase trackGooglePurchase;
  private static long unSentActiveTime = -1L;
  private static Collection unprocessedOpenedNotifis;
  private static boolean useEmailAuth;
  private static String userId;
  private static OneSignal.LOG_LEVEL visualLogLevel = OneSignal.LOG_LEVEL.NONE;
  private static boolean waitingToPostStateSync;
  
  static
  {
    logCatLevel = OneSignal.LOG_LEVEL.WARN;
    taskQueueWaitingForInit = new ConcurrentLinkedQueue();
    lastTaskId = new AtomicLong();
    mainAdIdProvider = new AdvertisingIdProviderGPS();
    unprocessedOpenedNotifis = new ArrayList();
  }
  
  public OneSignal() {}
  
  static long GetUnsentActiveTime()
  {
    if ((unSentActiveTime == -1L) && (appContext != null)) {
      unSentActiveTime = OneSignalPrefs.getLong(OneSignalPrefs.PREFS_ONESIGNAL, "GT_UNSENT_ACTIVE_TIME", 0L);
    }
    OneSignal.LOG_LEVEL localLOG_LEVEL = OneSignal.LOG_LEVEL.INFO;
    StringBuilder localStringBuilder = new StringBuilder("GetUnsentActiveTime: ");
    localStringBuilder.append(unSentActiveTime);
    Log(localLOG_LEVEL, localStringBuilder.toString());
    return unSentActiveTime;
  }
  
  static void Log(OneSignal.LOG_LEVEL paramLOG_LEVEL, String paramString)
  {
    Log(paramLOG_LEVEL, paramString, null);
  }
  
  static void Log(OneSignal.LOG_LEVEL paramLOG_LEVEL, String paramString, Throwable paramThrowable)
  {
    if (paramLOG_LEVEL.compareTo(logCatLevel) <= 0) {
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
    if ((paramLOG_LEVEL.compareTo(visualLogLevel) <= 0) && (ActivityLifecycleHandler.curActivity != null)) {
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
        OSUtils.runOnMainUIThread(new OneSignal.5(paramLOG_LEVEL, paramString));
        return;
      }
      catch (Throwable paramLOG_LEVEL)
      {
        Log.e("OneSignal", "Error showing logging message.", paramLOG_LEVEL);
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
    OneSignal.LOG_LEVEL localLOG_LEVEL = OneSignal.LOG_LEVEL.INFO;
    StringBuilder localStringBuilder = new StringBuilder("SaveUnsentActiveTime: ");
    localStringBuilder.append(unSentActiveTime);
    Log(localLOG_LEVEL, localStringBuilder.toString());
    OneSignalPrefs.saveLong(OneSignalPrefs.PREFS_ONESIGNAL, "GT_UNSENT_ACTIVE_TIME", paramLong);
  }
  
  public static void addEmailSubscriptionObserver(@NonNull OSEmailSubscriptionObserver paramOSEmailSubscriptionObserver)
  {
    if (appContext == null)
    {
      Log(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not add email subscription observer");
      return;
    }
    getEmailSubscriptionStateChangesObserver().addObserver(paramOSEmailSubscriptionObserver);
    if (getCurrentEmailSubscriptionState(appContext).compare(getLastEmailSubscriptionState(appContext))) {
      OSEmailSubscriptionChangedInternalObserver.fireChangesToPublicObserver(getCurrentEmailSubscriptionState(appContext));
    }
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
    if (appContext == null)
    {
      Log(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not add permission observer");
      return;
    }
    getPermissionStateChangesObserver().addObserver(paramOSPermissionObserver);
    if (getCurrentPermissionState(appContext).compare(getLastPermissionState(appContext))) {
      OSPermissionChangedInternalObserver.fireChangesToPublicObserver(getCurrentPermissionState(appContext));
    }
  }
  
  public static void addSubscriptionObserver(OSSubscriptionObserver paramOSSubscriptionObserver)
  {
    if (appContext == null)
    {
      Log(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not add subscription observer");
      return;
    }
    getSubscriptionStateChangesObserver().addObserver(paramOSSubscriptionObserver);
    if (getCurrentSubscriptionState(appContext).compare(getLastSubscriptionState(appContext))) {
      OSSubscriptionChangedInternalObserver.fireChangesToPublicObserver(getCurrentSubscriptionState(appContext));
    }
  }
  
  private static void addTaskToQueue(OneSignal.PendingTaskRunnable paramPendingTaskRunnable)
  {
    OneSignal.PendingTaskRunnable.access$402(paramPendingTaskRunnable, lastTaskId.incrementAndGet());
    OneSignal.LOG_LEVEL localLOG_LEVEL;
    StringBuilder localStringBuilder;
    if (pendingTaskExecutor == null)
    {
      localLOG_LEVEL = OneSignal.LOG_LEVEL.INFO;
      localStringBuilder = new StringBuilder("Adding a task to the pending queue with ID: ");
      localStringBuilder.append(OneSignal.PendingTaskRunnable.access$400(paramPendingTaskRunnable));
      Log(localLOG_LEVEL, localStringBuilder.toString());
      taskQueueWaitingForInit.add(paramPendingTaskRunnable);
      return;
    }
    if (!pendingTaskExecutor.isShutdown())
    {
      localLOG_LEVEL = OneSignal.LOG_LEVEL.INFO;
      localStringBuilder = new StringBuilder("Executor is still running, add to the executor with ID: ");
      localStringBuilder.append(OneSignal.PendingTaskRunnable.access$400(paramPendingTaskRunnable));
      Log(localLOG_LEVEL, localStringBuilder.toString());
      pendingTaskExecutor.submit(paramPendingTaskRunnable);
    }
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
    return (paramLOG_LEVEL.compareTo(visualLogLevel) <= 0) || (paramLOG_LEVEL.compareTo(logCatLevel) <= 0);
  }
  
  public static void cancelGroupedNotifications(String paramString)
  {
    OneSignal.23 local23 = new OneSignal.23(paramString);
    if ((appContext != null) && (!shouldRunTaskThroughQueue()))
    {
      local23.run();
      return;
    }
    OneSignal.LOG_LEVEL localLOG_LEVEL = OneSignal.LOG_LEVEL.ERROR;
    StringBuilder localStringBuilder = new StringBuilder("OneSignal.init has not been called. Could not clear notifications part of group ");
    localStringBuilder.append(paramString);
    localStringBuilder.append(" - movingthis operation to a waiting task queue.");
    Log(localLOG_LEVEL, localStringBuilder.toString());
    addTaskToQueue(new OneSignal.PendingTaskRunnable(local23));
  }
  
  public static void cancelNotification(int paramInt)
  {
    OneSignal.22 local22 = new OneSignal.22(paramInt);
    if ((appContext != null) && (!shouldRunTaskThroughQueue()))
    {
      local22.run();
      ((NotificationManager)appContext.getSystemService("notification")).cancel(paramInt);
      return;
    }
    OneSignal.LOG_LEVEL localLOG_LEVEL = OneSignal.LOG_LEVEL.ERROR;
    StringBuilder localStringBuilder = new StringBuilder("OneSignal.init has not been called. Could not clear notification id: ");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" at this time - movingthis operation to a waiting task queue. The notification will still be canceledfrom NotificationManager at this time.");
    Log(localLOG_LEVEL, localStringBuilder.toString());
    taskQueueWaitingForInit.add(local22);
  }
  
  public static void clearOneSignalNotifications()
  {
    OneSignal.21 local21 = new OneSignal.21();
    if ((appContext != null) && (!shouldRunTaskThroughQueue()))
    {
      local21.run();
      return;
    }
    Log(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not clear notifications at this time - moving this operation toa waiting task queue.");
    addTaskToQueue(new OneSignal.PendingTaskRunnable(local21));
  }
  
  public static void deleteTag(String paramString)
  {
    ArrayList localArrayList = new ArrayList(1);
    localArrayList.add(paramString);
    deleteTags(localArrayList);
  }
  
  public static void deleteTags(String paramString)
  {
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
      sendTags(localJSONObject);
      return;
    }
    catch (Throwable paramString)
    {
      Log(OneSignal.LOG_LEVEL.ERROR, "Failed to generate JSON for deleteTags.", paramString);
    }
  }
  
  public static void deleteTags(Collection paramCollection)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext()) {
        localJSONObject.put((String)paramCollection.next(), "");
      }
      sendTags(localJSONObject);
      return;
    }
    catch (Throwable paramCollection)
    {
      Log(OneSignal.LOG_LEVEL.ERROR, "Failed to generate JSON for deleteTags.", paramCollection);
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
  
  private static void fireIdsAvailableCallback()
  {
    if (idsAvailableHandler != null) {
      OSUtils.runOnMainUIThread(new OneSignal.16());
    }
  }
  
  private static void fireIntentFromNotificationOpen(Context paramContext)
  {
    Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
    if (localIntent != null)
    {
      localIntent.setFlags(268566528);
      paramContext.startActivity(localIntent);
    }
  }
  
  private static void fireNotificationOpenedHandler(OSNotificationOpenResult paramOSNotificationOpenResult)
  {
    OSUtils.runOnMainUIThread(new OneSignal.17(paramOSNotificationOpenResult));
  }
  
  @NonNull
  private static OSNotificationOpenResult generateOsNotificationOpenResult(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    int k = paramJSONArray.length();
    OSNotificationOpenResult localOSNotificationOpenResult = new OSNotificationOpenResult();
    OSNotification localOSNotification = new OSNotification();
    localOSNotification.isAppInFocus = isAppActive();
    localOSNotification.shown = paramBoolean1;
    localOSNotification.androidNotificationId = paramJSONArray.optJSONObject(0).optInt("notificationId");
    Object localObject1 = null;
    int j = 1;
    int i = 0;
    Object localObject4;
    Object localObject3;
    if (i < k)
    {
      localObject4 = localObject1;
      try
      {
        localObject5 = paramJSONArray.getJSONObject(i);
        localObject4 = localObject1;
        localOSNotification.payload = NotificationBundleProcessor.OSNotificationPayloadFrom((JSONObject)localObject5);
        localObject3 = localObject1;
        if (localObject1 != null) {
          break label365;
        }
        localObject4 = localObject1;
        localObject3 = localObject1;
        if (!((JSONObject)localObject5).has("actionSelected")) {
          break label365;
        }
        localObject4 = localObject1;
        localObject3 = ((JSONObject)localObject5).optString("actionSelected", null);
      }
      catch (Throwable localThrowable)
      {
        label139:
        localObject3 = OneSignal.LOG_LEVEL.ERROR;
        Object localObject5 = new StringBuilder("Error parsing JSON item ");
        ((StringBuilder)localObject5).append(i);
        ((StringBuilder)localObject5).append("/");
        ((StringBuilder)localObject5).append(k);
        ((StringBuilder)localObject5).append(" for callback.");
        Log((OneSignal.LOG_LEVEL)localObject3, ((StringBuilder)localObject5).toString(), localThrowable);
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
      i += 1;
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
      label365:
      if (j == 0) {
        break label139;
      }
      j = 0;
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
      paramContext = new OSEmailSubscriptionState(false);
      currentEmailSubscriptionState = paramContext;
      paramContext.observable.addObserverStrong(new OSEmailSubscriptionChangedInternalObserver());
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
      paramContext = new OSPermissionState(false);
      currentPermissionState = paramContext;
      paramContext.observable.addObserverStrong(new OSPermissionChangedInternalObserver());
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
  
  static OSObservable getEmailSubscriptionStateChangesObserver()
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
    if (mInitBuilder == null) {
      return false;
    }
    return mInitBuilder.mDisplayOption == OneSignal.OSInFocusDisplayOption.InAppAlert;
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
  
  static String getNotificationIdFromGCMBundle(Bundle paramBundle)
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
        Log(localLOG_LEVEL, paramBundle);
        return null;
        localLOG_LEVEL = OneSignal.LOG_LEVEL.DEBUG;
      }
      return null;
    }
    catch (Throwable paramBundle)
    {
      Log(OneSignal.LOG_LEVEL.DEBUG, "Could not parse bundle, probably not a OneSignal notification.", paramBundle);
    }
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
    if (mInitBuilder == null) {
      return true;
    }
    return mInitBuilder.mDisplayOption == OneSignal.OSInFocusDisplayOption.Notification;
  }
  
  static OSObservable getPermissionStateChangesObserver()
  {
    if (permissionStateChangesObserver == null) {
      permissionStateChangesObserver = new OSObservable("onOSPermissionChanged", true);
    }
    return permissionStateChangesObserver;
  }
  
  public static OSPermissionSubscriptionState getPermissionSubscriptionState()
  {
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
  
  static OSObservable getSubscriptionStateChangesObserver()
  {
    if (subscriptionStateChangesObserver == null) {
      subscriptionStateChangesObserver = new OSObservable("onOSSubscriptionChanged", true);
    }
    return subscriptionStateChangesObserver;
  }
  
  public static void getTags(OneSignal.GetTagsHandler paramGetTagsHandler)
  {
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
    notificationOpenedRESTCall(paramContext, paramJSONArray);
    if ((trackFirebaseAnalytics != null) && (getFirebaseAnalyticsEnabled(appContext))) {
      trackFirebaseAnalytics.trackOpenedEvent(generateOsNotificationOpenResult(paramJSONArray, true, paramBoolean));
    }
    boolean bool1 = false;
    boolean bool2 = "DISABLE".equals(OSUtils.getManifestMeta(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
    if (!bool2) {
      bool1 = openURLFromNotification(paramContext, paramJSONArray);
    }
    runNotificationOpenedCallback(paramJSONArray, true, paramBoolean);
    if ((!paramBoolean) && (!bool1) && (!bool2)) {
      fireIntentFromNotificationOpen(paramContext);
    }
  }
  
  static void handleNotificationReceived(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramJSONArray = generateOsNotificationOpenResult(paramJSONArray, paramBoolean1, paramBoolean2);
    if ((trackFirebaseAnalytics != null) && (getFirebaseAnalyticsEnabled(appContext))) {
      trackFirebaseAnalytics.trackReceivedEvent(paramJSONArray);
    }
    if (mInitBuilder != null)
    {
      if (mInitBuilder.mNotificationReceivedHandler == null) {
        return;
      }
      mInitBuilder.mNotificationReceivedHandler.notificationReceived(paramJSONArray.notification);
    }
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
    idsAvailableHandler = paramIdsAvailableHandler;
    paramIdsAvailableHandler = new OneSignal.15();
    if ((appContext != null) && (!shouldRunTaskThroughQueue()))
    {
      paramIdsAvailableHandler.run();
      return;
    }
    Log(OneSignal.LOG_LEVEL.ERROR, "You must initialize OneSignal before getting tags! Moving this tag operation to a pending queue.");
    addTaskToQueue(new OneSignal.PendingTaskRunnable(paramIdsAvailableHandler));
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
    OneSignal.Builder localBuilder = getCurrentOrNewInitBuilder();
    mInitBuilder = localBuilder;
    localBuilder.mDisplayOptionCarryOver = false;
    mInitBuilder.mNotificationOpenedHandler = paramNotificationOpenedHandler;
    mInitBuilder.mNotificationReceivedHandler = paramNotificationReceivedHandler;
    if (!mGoogleProjectNumberIsRemote) {
      mGoogleProjectNumber = paramString1;
    }
    paramString1 = new OSUtils();
    osUtils = paramString1;
    deviceType = paramString1.getDeviceType();
    int i = osUtils.initializationChecker(paramContext, deviceType, paramString2);
    subscribableStatus = i;
    if (i == 64537) {
      return;
    }
    if (initDone)
    {
      if (paramContext != null) {
        appContext = paramContext.getApplicationContext();
      }
      if (mInitBuilder.mNotificationOpenedHandler != null) {
        fireCallbackForOpenedNotifications();
      }
      return;
    }
    boolean bool = paramContext instanceof Activity;
    foreground = bool;
    appId = paramString2;
    appContext = paramContext.getApplicationContext();
    saveFilterOtherGCMReceivers(mInitBuilder.mFilterOtherGCMReceivers);
    if (bool)
    {
      ActivityLifecycleHandler.curActivity = (Activity)paramContext;
      NotificationRestorer.asyncRestore(appContext);
    }
    else
    {
      ActivityLifecycleHandler.nextResumeIsFirstActivity = true;
    }
    lastTrackedFocusTime = SystemClock.elapsedRealtime();
    OneSignalStateSynchronizer.initUserState();
    ((Application)appContext).registerActivityLifecycleCallbacks(new ActivityLifecycleListener());
    try
    {
      Class.forName("com.amazon.device.iap.PurchasingListener");
      trackAmazonPurchase = new TrackAmazonPurchase(appContext);
      paramContext = getSavedAppId();
      if (paramContext != null)
      {
        if (!paramContext.equals(appId))
        {
          Log(OneSignal.LOG_LEVEL.DEBUG, "APP ID changed, clearing user id as it is no longer valid.");
          SaveAppId(appId);
          OneSignalStateSynchronizer.resetCurrentState();
        }
      }
      else
      {
        BadgeCountUpdater.updateCount(0, appContext);
        SaveAppId(appId);
      }
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
      initDone = true;
      startPendingTasks();
      return;
    }
    catch (ClassNotFoundException paramContext)
    {
      for (;;) {}
    }
  }
  
  private static void init(OneSignal.Builder paramBuilder)
  {
    if (getCurrentOrNewInitBuilder().mDisplayOptionCarryOver) {
      paramBuilder.mDisplayOption = getCurrentOrNewInitBuilder().mDisplayOption;
    }
    mInitBuilder = paramBuilder;
    Context localContext = paramBuilder.mContext;
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
  
  private static void internalFireIdsAvailableCallback()
  {
    try
    {
      Object localObject1 = idsAvailableHandler;
      if (localObject1 == null) {
        return;
      }
      localObject1 = OneSignalStateSynchronizer.getRegistrationId();
      if (!OneSignalStateSynchronizer.getSubscribed()) {
        localObject1 = null;
      }
      String str = getUserId();
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
  
  static boolean isAppActive()
  {
    return (initDone) && (isForeground());
  }
  
  /* Error */
  private static boolean isDuplicateNotification(String paramString, Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +192 -> 193
    //   4: ldc_w 601
    //   7: aload_0
    //   8: invokevirtual 845	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   11: ifeq +5 -> 16
    //   14: iconst_0
    //   15: ireturn
    //   16: aload_1
    //   17: invokestatic 1241	com/onesignal/OneSignalDbHelper:getInstance	(Landroid/content/Context;)Lcom/onesignal/OneSignalDbHelper;
    //   20: astore 4
    //   22: aconst_null
    //   23: astore 6
    //   25: aconst_null
    //   26: astore_1
    //   27: aload 4
    //   29: invokevirtual 1245	com/onesignal/OneSignalDbHelper:getReadableDbWithRetries	()Landroid/database/sqlite/SQLiteDatabase;
    //   32: ldc_w 555
    //   35: iconst_1
    //   36: anewarray 621	java/lang/String
    //   39: dup
    //   40: iconst_0
    //   41: ldc_w 1247
    //   44: aastore
    //   45: ldc_w 1249
    //   48: iconst_1
    //   49: anewarray 621	java/lang/String
    //   52: dup
    //   53: iconst_0
    //   54: aload_0
    //   55: aastore
    //   56: aconst_null
    //   57: aconst_null
    //   58: aconst_null
    //   59: invokevirtual 1255	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   62: astore 4
    //   64: aload 4
    //   66: invokeinterface 1260 1 0
    //   71: istore_3
    //   72: iload_3
    //   73: istore_2
    //   74: aload 4
    //   76: ifnull +65 -> 141
    //   79: aload 4
    //   81: invokeinterface 1263 1 0
    //   86: iload_3
    //   87: istore_2
    //   88: goto +53 -> 141
    //   91: astore_0
    //   92: aload 4
    //   94: astore_1
    //   95: goto +86 -> 181
    //   98: astore 5
    //   100: goto +13 -> 113
    //   103: astore_0
    //   104: goto +77 -> 181
    //   107: astore 5
    //   109: aload 6
    //   111: astore 4
    //   113: aload 4
    //   115: astore_1
    //   116: getstatic 219	com/onesignal/OneSignal$LOG_LEVEL:ERROR	Lcom/onesignal/OneSignal$LOG_LEVEL;
    //   119: ldc_w 1265
    //   122: aload 5
    //   124: invokestatic 187	com/onesignal/OneSignal:Log	(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   127: aload 4
    //   129: ifnull +10 -> 139
    //   132: aload 4
    //   134: invokeinterface 1263 1 0
    //   139: iconst_0
    //   140: istore_2
    //   141: iload_2
    //   142: ifeq +37 -> 179
    //   145: getstatic 207	com/onesignal/OneSignal$LOG_LEVEL:DEBUG	Lcom/onesignal/OneSignal$LOG_LEVEL;
    //   148: astore_1
    //   149: new 167	java/lang/StringBuilder
    //   152: dup
    //   153: ldc_w 1267
    //   156: invokespecial 172	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   159: astore 4
    //   161: aload 4
    //   163: aload_0
    //   164: invokevirtual 235	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   167: pop
    //   168: aload_1
    //   169: aload 4
    //   171: invokevirtual 180	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   174: invokestatic 184	com/onesignal/OneSignal:Log	(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;)V
    //   177: iconst_1
    //   178: ireturn
    //   179: iconst_0
    //   180: ireturn
    //   181: aload_1
    //   182: ifnull +9 -> 191
    //   185: aload_1
    //   186: invokeinterface 1263 1 0
    //   191: aload_0
    //   192: athrow
    //   193: iconst_0
    //   194: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	195	0	paramString	String
    //   0	195	1	paramContext	Context
    //   73	69	2	bool1	boolean
    //   71	16	3	bool2	boolean
    //   20	150	4	localObject1	Object
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
  
  static boolean isForeground()
  {
    return foreground;
  }
  
  private static boolean isPastOnSessionTime()
  {
    if (sendAsSession) {
      return true;
    }
    return (System.currentTimeMillis() - getLastSessionTime(appContext)) / 1000L >= 30L;
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
        localObject1 = new StringBuilder("\n");
        ((StringBuilder)localObject1).append(paramString2);
        ((StringBuilder)localObject1).append("\n");
        localObject1 = ((StringBuilder)localObject1).toString();
      }
    }
    paramString2 = OneSignal.LOG_LEVEL.WARN;
    localObject2 = new StringBuilder("HTTP code: ");
    ((StringBuilder)localObject2).append(paramInt);
    ((StringBuilder)localObject2).append(" ");
    ((StringBuilder)localObject2).append(paramString1);
    ((StringBuilder)localObject2).append((String)localObject1);
    Log(paramString2, ((StringBuilder)localObject2).toString(), paramThrowable);
  }
  
  public static void logoutEmail()
  {
    logoutEmail(null);
  }
  
  public static void logoutEmail(@Nullable OneSignal.EmailUpdateHandler paramEmailUpdateHandler)
  {
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
    if ((appContext != null) && (!shouldRunTaskThroughQueue()))
    {
      paramEmailUpdateHandler.run();
      return;
    }
    Log(OneSignal.LOG_LEVEL.ERROR, "You should initialize OneSignal before calling logoutEmail! Moving this operation to a pending task queue.");
    addTaskToQueue(new OneSignal.PendingTaskRunnable(paramEmailUpdateHandler));
  }
  
  private static void makeAndroidParamsRequest()
  {
    if (awlFired)
    {
      registerForPushToken();
      return;
    }
    OneSignal.4 local4 = new OneSignal.4();
    Object localObject = new StringBuilder("apps/");
    ((StringBuilder)localObject).append(appId);
    ((StringBuilder)localObject).append("/android_params.js");
    String str1 = ((StringBuilder)localObject).toString();
    String str2 = getUserId();
    localObject = str1;
    if (str2 != null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(str1);
      ((StringBuilder)localObject).append("?player_id=");
      ((StringBuilder)localObject).append(str2);
      localObject = ((StringBuilder)localObject).toString();
    }
    Log(OneSignal.LOG_LEVEL.DEBUG, "Starting request to get Android parameters.");
    OneSignalRestClient.get((String)localObject, local4);
  }
  
  static boolean notValidOrDuplicated(Context paramContext, JSONObject paramJSONObject)
  {
    paramJSONObject = getNotificationIdFromGCMJsonPayload(paramJSONObject);
    return (paramJSONObject == null) || (isDuplicateNotification(paramJSONObject, paramContext));
  }
  
  private static void notificationOpenedRESTCall(Context paramContext, JSONArray paramJSONArray)
  {
    int i = 0;
    while (i < paramJSONArray.length())
    {
      try
      {
        String str = new JSONObject(paramJSONArray.getJSONObject(i).optString("custom", null)).optString("i", null);
        if (!postedOpenedNotifIds.contains(str))
        {
          postedOpenedNotifIds.add(str);
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("app_id", getSavedAppId(paramContext));
          localJSONObject.put("player_id", getSavedUserId(paramContext));
          localJSONObject.put("opened", true);
          StringBuilder localStringBuilder = new StringBuilder("notifications/");
          localStringBuilder.append(str);
          OneSignalRestClient.put(localStringBuilder.toString(), localJSONObject, new OneSignal.18());
        }
      }
      catch (Throwable localThrowable)
      {
        Log(OneSignal.LOG_LEVEL.ERROR, "Failed to generate JSON to send notification opened.", localThrowable);
      }
      i += 1;
    }
  }
  
  static void onAppFocus()
  {
    foreground = true;
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
    boolean bool2 = false;
    foreground = false;
    if (!initDone) {
      return false;
    }
    if (trackAmazonPurchase != null) {
      trackAmazonPurchase.checkListener();
    }
    if (lastTrackedFocusTime == -1L) {
      return false;
    }
    long l = ((SystemClock.elapsedRealtime() - lastTrackedFocusTime) / 1000.0D + 0.5D);
    lastTrackedFocusTime = SystemClock.elapsedRealtime();
    boolean bool1 = bool2;
    if (l >= 0L)
    {
      if (l > 86400L) {
        return false;
      }
      if (appContext == null)
      {
        Log(OneSignal.LOG_LEVEL.ERROR, "Android Context not found, please call OneSignal.init when your app starts.");
        return false;
      }
      bool1 = scheduleSyncService();
      setLastSessionTime(System.currentTimeMillis());
      l = GetUnsentActiveTime() + l;
      SaveUnsentActiveTime(l);
      if ((l >= 60L) && (getUserId() != null))
      {
        if (!bool1) {
          OneSignalSyncServiceUtils.scheduleSyncTask(appContext);
        }
        OneSignalSyncServiceUtils.syncOnFocusTime();
        return false;
      }
      bool1 = bool2;
      if (l >= 60L) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private static void onTaskRan(long paramLong)
  {
    if (lastTaskId.get() == paramLong)
    {
      Log(OneSignal.LOG_LEVEL.INFO, "Last Pending Task has ran, shutting down");
      pendingTaskExecutor.shutdown();
    }
  }
  
  private static boolean openURLFromNotification(Context paramContext, JSONArray paramJSONArray)
  {
    int j = paramJSONArray.length();
    int i = 0;
    boolean bool2;
    for (boolean bool1 = false; i < j; bool1 = bool2)
    {
      try
      {
        Object localObject1 = paramJSONArray.getJSONObject(i);
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
              localObject1 = new StringBuilder("http://");
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
        StringBuilder localStringBuilder = new StringBuilder("Error parsing JSON item ");
        localStringBuilder.append(i);
        localStringBuilder.append("/");
        localStringBuilder.append(j);
        localStringBuilder.append(" for launching a web URL.");
        Log((OneSignal.LOG_LEVEL)localObject2, localStringBuilder.toString(), localThrowable);
        bool2 = bool1;
      }
      i += 1;
    }
    return bool1;
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
    localStringBuilder = new StringBuilder("Invalid postNotification JSON format: ");
    localStringBuilder.append(paramString);
    Log(paramPostNotificationResponseHandler, localStringBuilder.toString());
  }
  
  public static void postNotification(JSONObject paramJSONObject, OneSignal.PostNotificationResponseHandler paramPostNotificationResponseHandler)
  {
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
    OneSignal.20 local20 = new OneSignal.20();
    if ((appContext != null) && (!shouldRunTaskThroughQueue()))
    {
      local20.run();
      return;
    }
    Log(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Could not prompt for location at this time - moving this operation to awaiting queue.");
    addTaskToQueue(new OneSignal.PendingTaskRunnable(local20));
  }
  
  private static void registerForPushToken()
  {
    Object localObject;
    if (deviceType == 2) {
      localObject = new PushRegistratorADM();
    } else {
      localObject = new PushRegistratorGPS();
    }
    ((PushRegistrator)localObject).registerForPush(appContext, mGoogleProjectNumber, new OneSignal.3());
  }
  
  private static void registerUser()
  {
    OneSignal.LOG_LEVEL localLOG_LEVEL = OneSignal.LOG_LEVEL.DEBUG;
    StringBuilder localStringBuilder = new StringBuilder("registerUser: registerForPushFired:");
    localStringBuilder.append(registerForPushFired);
    localStringBuilder.append(", locationFired: ");
    localStringBuilder.append(locationFired);
    localStringBuilder.append(", awlFired: ");
    localStringBuilder.append(awlFired);
    Log(localLOG_LEVEL, localStringBuilder.toString());
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
    localJSONObject.put("sdk", "030803");
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
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          int i;
          String str;
          continue;
          i += 1;
        }
      }
      if (i < ((List)localObject1).size())
      {
        ((MessageDigest)localObject3).update(((PackageInfo)((List)localObject1).get(i)).packageName.getBytes());
        str = Base64.encodeToString(((MessageDigest)localObject3).digest(), 2);
        if (awl.has(str)) {
          ((JSONArray)localObject2).put(str);
        }
      }
      else
      {
        localJSONObject.put("pkgs", localObject2);
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
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
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
  
  private static void runNotificationOpenedCallback(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((mInitBuilder != null) && (mInitBuilder.mNotificationOpenedHandler != null))
    {
      fireNotificationOpenedHandler(generateOsNotificationOpenResult(paramJSONArray, paramBoolean1, paramBoolean2));
      return;
    }
    unprocessedOpenedNotifis.add(paramJSONArray);
  }
  
  static void saveEmailId(String paramString)
  {
    emailId = paramString;
    if (appContext == null) {
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
  
  static void saveFilterOtherGCMReceivers(boolean paramBoolean)
  {
    if (appContext == null) {
      return;
    }
    OneSignalPrefs.saveBool(OneSignalPrefs.PREFS_ONESIGNAL, "OS_FILTER_OTHER_GCM_RECEIVERS", paramBoolean);
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
    Object localObject = new StringBuilder("players/");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append("/on_focus");
    paramString = ((StringBuilder)localObject).toString();
    localObject = new OneSignal.6();
    if (paramBoolean)
    {
      OneSignalRestClient.postSync(paramString, paramJSONObject, (OneSignalRestClient.ResponseHandler)localObject);
      return;
    }
    OneSignalRestClient.post(paramString, paramJSONObject, (OneSignalRestClient.ResponseHandler)localObject);
  }
  
  static void sendPurchases(JSONArray paramJSONArray, boolean paramBoolean, OneSignalRestClient.ResponseHandler paramResponseHandler)
  {
    if (getUserId() == null)
    {
      paramJSONArray = new OneSignal.IAPUpdateJob(paramJSONArray);
      iapUpdateJob = paramJSONArray;
      paramJSONArray.newAsExisting = paramBoolean;
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
      paramJSONArray = new StringBuilder("players/");
      paramJSONArray.append(getUserId());
      paramJSONArray.append("/on_purchase");
      OneSignalRestClient.post(paramJSONArray.toString(), localJSONObject, paramResponseHandler);
      if (getEmailId() != null)
      {
        paramJSONArray = new StringBuilder("players/");
        paramJSONArray.append(getEmailId());
        paramJSONArray.append("/on_purchase");
        OneSignalRestClient.post(paramJSONArray.toString(), localJSONObject, null);
      }
      return;
    }
    catch (Throwable paramJSONArray)
    {
      Log(OneSignal.LOG_LEVEL.ERROR, "Failed to generate JSON for sendPurchases.", paramJSONArray);
    }
  }
  
  public static void sendTag(String paramString1, String paramString2)
  {
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
    paramJSONObject = new OneSignal.11(paramJSONObject);
    if ((appContext != null) && (!shouldRunTaskThroughQueue()))
    {
      paramJSONObject.run();
      return;
    }
    Log(OneSignal.LOG_LEVEL.ERROR, "You must initialize OneSignal before modifying tags!Moving this operation to a pending task queue.");
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
    if ((appContext != null) && (!shouldRunTaskThroughQueue()))
    {
      paramString1.run();
      return;
    }
    Log(OneSignal.LOG_LEVEL.ERROR, "You should initialize OneSignal before calling setEmail! Moving this operation to a pending task queue.");
    addTaskToQueue(new OneSignal.PendingTaskRunnable(paramString1));
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
    shareLocation = paramBoolean;
    if (!paramBoolean) {
      OneSignalStateSynchronizer.clearLocation();
    }
    OneSignal.LOG_LEVEL localLOG_LEVEL = OneSignal.LOG_LEVEL.DEBUG;
    StringBuilder localStringBuilder = new StringBuilder("shareLocation:");
    localStringBuilder.append(shareLocation);
    Log(localLOG_LEVEL, localStringBuilder.toString());
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
  
  public static void setSubscription(boolean paramBoolean)
  {
    OneSignal.19 local19 = new OneSignal.19(paramBoolean);
    if ((appContext != null) && (!shouldRunTaskThroughQueue()))
    {
      local19.run();
      return;
    }
    Log(OneSignal.LOG_LEVEL.ERROR, "OneSignal.init has not been called. Moving subscription action to a waiting task queue.");
    addTaskToQueue(new OneSignal.PendingTaskRunnable(local19));
  }
  
  private static boolean shouldRunTaskThroughQueue()
  {
    if ((initDone) && (pendingTaskExecutor == null)) {
      return false;
    }
    if ((!initDone) && (pendingTaskExecutor == null)) {
      return true;
    }
    return (pendingTaskExecutor != null) && (!pendingTaskExecutor.isShutdown());
  }
  
  public static OneSignal.Builder startInit(Context paramContext)
  {
    return new OneSignal.Builder(paramContext, null);
  }
  
  private static void startLocationUpdate()
  {
    OneSignal.2 local2 = new OneSignal.2();
    boolean bool;
    if ((mInitBuilder.mPromptLocation) && (!promptedLocation)) {
      bool = true;
    } else {
      bool = false;
    }
    LocationGMS.getLocation(appContext, bool, local2);
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
      if (mInitBuilder.mPromptLocation) {
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
    if (!OSUtils.isValidEmail(paramString)) {
      return;
    }
    paramString = new OneSignal.8(paramString);
    if ((appContext != null) && (!shouldRunTaskThroughQueue()))
    {
      paramString.run();
      return;
    }
    Log(OneSignal.LOG_LEVEL.ERROR, "You should initialize OneSignal before calling syncHashedEmail! Moving this operation to a pending task queue.");
    addTaskToQueue(new OneSignal.PendingTaskRunnable(paramString));
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
}
