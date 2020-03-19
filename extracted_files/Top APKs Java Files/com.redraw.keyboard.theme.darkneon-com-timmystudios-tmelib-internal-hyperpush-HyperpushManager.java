package com.timmystudios.tmelib.internal.hyperpush;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobRequest;
import com.evernote.android.job.JobRequest.BackoffPolicy;
import com.evernote.android.job.JobRequest.Builder;
import com.evernote.android.job.JobRequest.NetworkType;
import com.timmystudios.tmelib.TmeHyperpushEventsListener;
import com.timmystudios.tmelib.TmeHyperpushInterceptor;
import com.timmystudios.tmelib.TmeLib;
import com.timmystudios.tmelib.TmeLib.Config;
import com.timmystudios.tmelib.TmeResultCallback;
import com.timmystudios.tmelib.internal.hyperpush.receivers.TMEHyperpushClickReceiver;
import com.timmystudios.tmelib.internal.hyperpush.receivers.TMEHyperpushDismissReceiver;
import com.timmystudios.tmelib.internal.hyperpush.response.HyperpushConditions;
import com.timmystudios.tmelib.internal.hyperpush.response.HyperpushConfig;
import com.timmystudios.tmelib.internal.hyperpush.response.HyperpushEventConditions;
import com.timmystudios.tmelib.internal.hyperpush.response.HyperpushItem;
import com.timmystudios.tmelib.internal.hyperpush.response.HyperpushNotification;
import com.timmystudios.tmelib.internal.hyperpush.response.HyperpushPackageConditions;
import com.timmystudios.tmelib.internal.hyperpush.services.TMERemoteBigPictureNotificationService;
import com.timmystudios.tmelib.internal.hyperpush.services.TMERemoteBigPictureNotificationService.IntentBuilder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class HyperpushManager
{
  private static final String PREF_EVENTS = "events";
  private static final String PREF_SEEN_ITEMS = "seen-items";
  private static final String SHARED_PREFS_FILE = "tme-hyperpush";
  @SuppressLint({"StaticFieldLeak"})
  private static HyperpushManager sInstance;
  private final Context mContext;
  private String[] mKinesisKeywords;
  private final MultiplexingHyperpushEventsListener mMultiplexingListener = new MultiplexingHyperpushEventsListener();
  
  private HyperpushManager(Context paramContext)
  {
    this.mContext = paramContext.getApplicationContext();
    HyperpushConfigManager.initialize(paramContext);
    scheduleFirstNotification();
  }
  
  private boolean areConditionsMet(HyperpushConditions paramHyperpushConditions, Set<String> paramSet1, Set<String> paramSet2)
  {
    if (paramHyperpushConditions == null) {
      return true;
    }
    if ((paramHyperpushConditions.expiration != null) && (DateUtils.hasExpired(paramHyperpushConditions.expiration))) {
      return false;
    }
    if (paramHyperpushConditions.packages != null)
    {
      Iterator localIterator;
      if (paramHyperpushConditions.packages.installed != null)
      {
        localIterator = paramHyperpushConditions.packages.installed.iterator();
        while (localIterator.hasNext()) {
          if (!paramSet1.contains((String)localIterator.next())) {
            return false;
          }
        }
      }
      if (paramHyperpushConditions.packages.notInstalled != null)
      {
        localIterator = paramHyperpushConditions.packages.notInstalled.iterator();
        while (localIterator.hasNext()) {
          if (paramSet1.contains((String)localIterator.next())) {
            return false;
          }
        }
      }
    }
    if (paramHyperpushConditions.events != null)
    {
      if (paramHyperpushConditions.events.performed != null)
      {
        paramSet1 = paramHyperpushConditions.events.performed.iterator();
        while (paramSet1.hasNext()) {
          if (!paramSet2.contains((String)paramSet1.next())) {
            return false;
          }
        }
      }
      if (paramHyperpushConditions.events.notPerformed != null)
      {
        paramHyperpushConditions = paramHyperpushConditions.events.notPerformed.iterator();
        while (paramHyperpushConditions.hasNext()) {
          if (paramSet2.contains((String)paramHyperpushConditions.next())) {
            return false;
          }
        }
      }
    }
    return true;
  }
  
  private Set<String> getInstalledPackageNames()
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this.mContext.getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext()) {
      localHashSet.add(((PackageInfo)localIterator.next()).packageName);
    }
    return localHashSet;
  }
  
  @NonNull
  public static HyperpushManager getInstance()
  {
    if (sInstance == null) {
      throw new IllegalStateException();
    }
    return sInstance;
  }
  
  private int getNotificationDefaults(HyperpushNotification paramHyperpushNotification)
  {
    int i;
    if (paramHyperpushNotification.sound) {
      i = 1;
    } else {
      i = 0;
    }
    int j = i;
    if (paramHyperpushNotification.vibration) {
      j = i | 0x2;
    }
    return j;
  }
  
  private int getNotificationId()
  {
    Integer localInteger = TmeLib.getConfig().hyperpushNotificationId;
    if (localInteger != null) {
      return localInteger.intValue();
    }
    return 0;
  }
  
  private Set<String> getSeenItems()
  {
    return this.mContext.getSharedPreferences("tme-hyperpush", 0).getStringSet("seen-items", new HashSet());
  }
  
  private Set<String> getTaggedEvents()
  {
    return this.mContext.getSharedPreferences("tme-hyperpush", 0).getStringSet("events", new HashSet());
  }
  
  public static void initialize(Context paramContext)
  {
    if (sInstance != null) {
      throw new IllegalStateException();
    }
    sInstance = new HyperpushManager(paramContext);
  }
  
  private void rememberSeenItem(int paramInt)
  {
    SharedPreferences localSharedPreferences = this.mContext.getSharedPreferences("tme-hyperpush", 0);
    HashSet localHashSet = new HashSet(localSharedPreferences.getStringSet("seen-items", new HashSet()));
    localHashSet.add(Integer.toString(paramInt));
    localSharedPreferences.edit().putStringSet("seen-items", localHashSet).apply();
  }
  
  private void scheduleFirstNotification()
  {
    HyperpushConfigManager.getInstance().getConfig(new TmeResultCallback()
    {
      public void onResult(HyperpushConfig paramAnonymousHyperpushConfig)
      {
        HyperpushManager localHyperpushManager = HyperpushManager.this;
        String[] arrayOfString;
        if (paramAnonymousHyperpushConfig == null) {
          arrayOfString = null;
        } else {
          arrayOfString = paramAnonymousHyperpushConfig.kinesisKeywords;
        }
        HyperpushManager.access$002(localHyperpushManager, arrayOfString);
        long l;
        if (paramAnonymousHyperpushConfig != null) {
          l = paramAnonymousHyperpushConfig.secondsUntilFirstNotification;
        } else {
          l = TimeUnit.DAYS.toSeconds(1L);
        }
        HyperpushManager.this.scheduleNextNotification(l);
      }
    });
  }
  
  private void scheduleNextNotification(long paramLong)
  {
    paramLong = TimeUnit.SECONDS.toMillis(paramLong);
    long l = TimeUnit.MINUTES.toMillis(30L);
    JobManager.instance().cancelAllForTag("tme-hyperpush-next-notification");
    new JobRequest.Builder("tme-hyperpush-next-notification").setExecutionWindow(paramLong, paramLong + l).setRequiredNetworkType(JobRequest.NetworkType.CONNECTED).setBackoffCriteria(TimeUnit.MINUTES.toMillis(30L), JobRequest.BackoffPolicy.LINEAR).build().schedule();
  }
  
  @Nullable
  private HyperpushItem selectBestItem(HyperpushConfig paramHyperpushConfig)
  {
    if (paramHyperpushConfig == null) {
      return null;
    }
    Set localSet1 = getSeenItems();
    Set localSet2 = getInstalledPackageNames();
    Set localSet3 = getTaggedEvents();
    paramHyperpushConfig = paramHyperpushConfig.items.iterator();
    while (paramHyperpushConfig.hasNext())
    {
      HyperpushItem localHyperpushItem = (HyperpushItem)paramHyperpushConfig.next();
      if ((!localSet1.contains(Integer.toString(localHyperpushItem.id))) && (areConditionsMet(localHyperpushItem.conditions, localSet2, localSet3)))
      {
        TmeHyperpushInterceptor localTmeHyperpushInterceptor = TmeLib.getHyperpushInterceptor();
        if ((localTmeHyperpushInterceptor == null) || (localTmeHyperpushInterceptor.shouldShowNotification(localHyperpushItem))) {
          return localHyperpushItem;
        }
      }
    }
    return null;
  }
  
  private void showItemNotification(HyperpushItem paramHyperpushItem)
  {
    Object localObject = paramHyperpushItem.notification;
    Intent localIntent1 = new Intent(this.mContext, TMEHyperpushClickReceiver.class);
    localIntent1.putExtra("item-id", paramHyperpushItem.id);
    localIntent1.putExtra("user-intent", paramHyperpushItem.intent);
    Intent localIntent2 = new Intent(this.mContext, TMEHyperpushDismissReceiver.class);
    localIntent2.putExtra("item-id", paramHyperpushItem.id);
    localIntent2.putExtra("user-intent", paramHyperpushItem.intent);
    String str = ((HyperpushNotification)localObject).type;
    int i;
    if ((str.hashCode() == -111559375) && (str.equals("big-picture"))) {
      i = 0;
    } else {
      i = -1;
    }
    if (i != 0)
    {
      localObject = new Intent("TYPE_HYPERPUSH_CUSTOM");
      ((Intent)localObject).putExtra("TYPE_HYPERPUSH_CUSTOM", paramHyperpushItem.intent);
      this.mContext.sendBroadcast((Intent)localObject);
      return;
    }
    paramHyperpushItem = new TMERemoteBigPictureNotificationService.IntentBuilder(this.mContext).setNotificationId(getNotificationId()).setBigPictureUrl(((HyperpushNotification)localObject).bigPictureUri).setBigLargeIconUrl(((HyperpushNotification)localObject).bigLargeIconUri).setTitle(((HyperpushNotification)localObject).titleText).setBody(((HyperpushNotification)localObject).bodyText).setDefaults(getNotificationDefaults((HyperpushNotification)localObject)).setContentIntent(PendingIntent.getBroadcast(this.mContext, 0, localIntent1, 268435456)).setDeleteIntent(PendingIntent.getBroadcast(this.mContext, 0, localIntent2, 268435456));
    if (TmeLib.getConfig().hyperpushNotificationSmallIconRes != 0) {
      paramHyperpushItem.setSmallIconRes(TmeLib.getConfig().hyperpushNotificationSmallIconRes);
    }
    localObject = TmeLib.getConfig().hyperpushNotificationPriority;
    if (localObject != null) {
      paramHyperpushItem.setPriority(((Integer)localObject).intValue());
    }
    localObject = TmeLib.getConfig().hyperpushNotificationColor;
    if (localObject != null) {
      paramHyperpushItem.setColor(((Integer)localObject).intValue());
    }
    TMERemoteBigPictureNotificationService.enqueueWork(this.mContext, paramHyperpushItem.build());
  }
  
  public void addListener(TmeHyperpushEventsListener paramTmeHyperpushEventsListener)
  {
    this.mMultiplexingListener.addHyperpushEventsListener(paramTmeHyperpushEventsListener);
  }
  
  public Map<String, String> getKinesisParams(Intent paramIntent)
  {
    paramIntent = paramIntent.getExtras();
    if (paramIntent == null) {
      return null;
    }
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramIntent.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if ((!TextUtils.isEmpty(str)) && (isKinesisKeyWord(str)))
      {
        Object localObject = paramIntent.get(str);
        if (localObject != null) {
          localHashMap.put(str, kinesisObjectToString(localObject));
        }
      }
    }
    return localHashMap;
  }
  
  public Bundle getKinesisParamsAsBundle(Intent paramIntent)
  {
    paramIntent = paramIntent.getExtras();
    if (paramIntent == null) {
      return null;
    }
    Bundle localBundle = new Bundle();
    Iterator localIterator = paramIntent.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if ((!TextUtils.isEmpty(str)) && (isKinesisKeyWord(str)))
      {
        Object localObject = paramIntent.get(str);
        if (localObject != null) {
          localBundle.putString(str, kinesisObjectToString(localObject));
        }
      }
    }
    return localBundle;
  }
  
  protected boolean isKinesisKeyWord(String paramString)
  {
    if ((paramString != null) && (this.mKinesisKeywords != null))
    {
      if (this.mKinesisKeywords.length == 0) {
        return false;
      }
      String[] arrayOfString = this.mKinesisKeywords;
      int j = arrayOfString.length;
      int i = 0;
      while (i < j)
      {
        String str = arrayOfString[i];
        if ((!TextUtils.isEmpty(str)) && (paramString.equals(str))) {
          return true;
        }
        i += 1;
      }
      return false;
    }
    return false;
  }
  
  protected String kinesisObjectToString(Object paramObject)
  {
    double d;
    if ((paramObject instanceof Float))
    {
      float f = ((Float)paramObject).floatValue();
      d = f;
      if (Math.floor(d) == d) {
        return Long.toString(f);
      }
    }
    if ((paramObject instanceof Double))
    {
      d = ((Double)paramObject).doubleValue();
      if (Math.floor(d) == d) {
        return Long.toString(d);
      }
    }
    return paramObject.toString();
  }
  
  public void onNotificationClicked(int paramInt, Intent paramIntent)
  {
    this.mMultiplexingListener.onNotificationClicked(paramInt, paramIntent);
  }
  
  public void onNotificationDismissed(int paramInt, Intent paramIntent)
  {
    this.mMultiplexingListener.onNotificationDismissed(paramInt, paramIntent);
  }
  
  public void removeListener(TmeHyperpushEventsListener paramTmeHyperpushEventsListener)
  {
    this.mMultiplexingListener.removeHyperpushEventsListener(paramTmeHyperpushEventsListener);
  }
  
  public void showNextNotification()
  {
    HyperpushConfigManager.getInstance().getConfig(new TmeResultCallback()
    {
      public void onResult(HyperpushConfig paramAnonymousHyperpushConfig)
      {
        HyperpushManager localHyperpushManager = HyperpushManager.this;
        String[] arrayOfString;
        if (paramAnonymousHyperpushConfig == null) {
          arrayOfString = null;
        } else {
          arrayOfString = paramAnonymousHyperpushConfig.kinesisKeywords;
        }
        HyperpushManager.access$002(localHyperpushManager, arrayOfString);
        long l = TimeUnit.DAYS.toSeconds(1L);
        paramAnonymousHyperpushConfig = HyperpushManager.this.selectBestItem(paramAnonymousHyperpushConfig);
        if (paramAnonymousHyperpushConfig != null)
        {
          HyperpushManager.this.showItemNotification(paramAnonymousHyperpushConfig);
          HyperpushManager.this.rememberSeenItem(paramAnonymousHyperpushConfig.id);
          HyperpushManager.this.mMultiplexingListener.onNotificationShown(paramAnonymousHyperpushConfig.id, paramAnonymousHyperpushConfig.intent);
          l = paramAnonymousHyperpushConfig.secondsUntilNextNotification;
        }
        HyperpushManager.this.scheduleNextNotification(l);
      }
    });
  }
  
  public void tagEvent(String paramString)
  {
    SharedPreferences localSharedPreferences = this.mContext.getSharedPreferences("tme-hyperpush", 0);
    HashSet localHashSet = new HashSet(localSharedPreferences.getStringSet("events", new HashSet()));
    localHashSet.add(paramString);
    localSharedPreferences.edit().putStringSet("events", localHashSet).apply();
  }
}
