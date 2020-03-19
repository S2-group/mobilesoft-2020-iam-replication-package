package com.buzzvil.buzzscreen.sdk;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import android.util.Log;
import com.buzzvil.buzzcore.utils.AppUtils;
import com.buzzvil.buzzcore.utils.LogHelper;
import com.buzzvil.buzzcore.utils.PlatformUtils;
import com.buzzvil.buzzcore.utils.TimeUtils;
import com.buzzvil.buzzscreen.sdk.tutorial.InteractiveGuideConfig;
import com.buzzvil.buzzscreen.sdk.tutorial.LockerInteractiveGuidePreferenceHelper;
import com.buzzvil.buzzscreen.sdk.tutorial.LockerTutorialPreferenceHelper;
import com.buzzvil.locker.BuzzLocker;
import com.buzzvil.locker.BuzzLocker.AutoplayType;
import com.buzzvil.locker.BuzzLocker.ConfigInterface;
import com.buzzvil.locker.BuzzLocker.EventListener;
import com.buzzvil.locker.BuzzLocker.LifeCycleListener;
import com.buzzvil.locker.BuzzLocker.ServiceNotificationInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class BuzzScreen
{
  public static final String VERSION = "1.9.5.7";
  public static final int VERSION_CODE = 1957;
  private static BuzzScreen b;
  private static Notification n;
  LockerServiceNotificationConfig a;
  private Context c;
  private String d;
  private UserProfile e;
  private boolean f = false;
  private OnPointListener g;
  @Deprecated
  private ActivateListener h;
  private List<OnActivationListener> i = new ArrayList();
  private boolean j = false;
  private final PreferenceStore k;
  private final LockerTutorialPreferenceHelper l;
  private final LockerInteractiveGuidePreferenceHelper m;
  private BuzzLocker.LifeCycleListener o = new BuzzLocker.LifeCycleListener()
  {
    public void onScreenOff()
    {
      super.onScreenOff();
      Analytics.trackEvent(Analytics.Type.SCREEN, "off");
    }
    
    public void onScreenOn()
    {
      super.onScreenOn();
      Analytics.trackEvent(Analytics.Type.SCREEN, "on");
    }
    
    public void onStart()
    {
      super.onStart();
      BuzzScreen.a(BuzzScreen.this, true);
    }
    
    public void onStop()
    {
      super.onStop();
      LogHelper.d("BuzzScreen", "BuzzLocker lifeCycle onStop");
      BuzzScreen.a(BuzzScreen.this, false);
    }
  };
  
  private BuzzScreen(String paramString, Context paramContext, Class<?> paramClass, boolean paramBoolean, PrivacyPolicy paramPrivacyPolicy)
  {
    this.c = paramContext;
    this.d = paramString;
    this.f = paramBoolean;
    this.k = new PreferenceStore(paramContext, paramBoolean);
    this.l = new LockerTutorialPreferenceHelper(this.k);
    this.e = new UserProfile(paramContext, paramBoolean, this.k);
    this.a = new LockerServiceNotificationConfig(paramContext);
    this.m = new LockerInteractiveGuidePreferenceHelper(this.k);
    if (paramPrivacyPolicy == PrivacyPolicy.NONE) {
      this.k.putBooleanAndSync("use_privacy_policy", false);
    }
    BuzzLocker.getInstance().addLifeCycleListener(this.o);
    try
    {
      a(paramClass.getName());
      return;
    }
    catch (Exception paramString)
    {
      LogHelper.e("BuzzScreen", paramString.toString());
    }
  }
  
  private void a(int paramInt)
  {
    this.k.putIntAndSync("snooze_to", paramInt);
  }
  
  private static void a(Context paramContext)
  {
    if (getInstance().getPreferenceStore().getInt("file_version", 0) != 1)
    {
      paramContext = paramContext.getSharedPreferences("Feed", 0);
      getInstance().getPreferenceStore().putString("session_key", paramContext.getString("SESSION_KEY", ""));
      getInstance().getPreferenceStore().putInt("session_key_last_request_time", paramContext.getInt("SESSION_KEY_LAST_REQUEST_TIME", 0));
      getInstance().getPreferenceStore().putBoolean("tutorial_for_you_shown", paramContext.getBoolean("TUTORIAL_FOR_YOU_SHOWN", false));
      getInstance().getPreferenceStore().putBoolean("tutorial_videos_shown", paramContext.getBoolean("TUTORIAL_VIDEOS_SHOWN", false));
      getInstance().getPreferenceStore().putBoolean("tutorial_explore_shown", paramContext.getBoolean("TUTORIAL_EXPLORE_SHOWN", false));
      getInstance().getPreferenceStore().putInt("file_version", 1);
    }
  }
  
  private static void a(Context paramContext, String paramString)
  {
    if (paramString.equals(SimpleLockerActivity.class.getName()))
    {
      LogHelper.d("BuzzScreen", "no need validateManifest");
      return;
    }
    String str = paramContext.getPackageName();
    try
    {
      ActivityInfo localActivityInfo = paramContext.getPackageManager().getActivityInfo(new ComponentName(str, paramString), 128);
      if (localActivityInfo == null) {
        return;
      }
      if (localActivityInfo.screenOrientation != 1) {
        Log.e("BuzzScreen", String.format("[PLEASE FIX]%s screenOrientation is not portrait. Read guide carefully!!!", new Object[] { paramString }));
      }
      str = String.format("%s.Locker", new Object[] { str });
      if ((localActivityInfo.taskAffinity == null) || (!localActivityInfo.taskAffinity.equals(str))) {
        Log.e("BuzzScreen", String.format("[PLEASE FIX]%s taskAffinity is not %s. Read guide carefully!!!", new Object[] { paramString, str }));
      }
      if (localActivityInfo.launchMode != 2) {
        Log.e("BuzzScreen", String.format("[PLEASE FIX]%s launchMode is not singleTask. Read guide carefully!!!", new Object[] { paramString }));
      }
      if ((localActivityInfo.flags & 0x20) != 32) {
        Log.e("BuzzScreen", String.format("[PLEASE FIX]%s excludeFromRecents is not true. Read guide carefully!!!", new Object[] { paramString }));
      }
      return;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      try
      {
        paramContext = paramContext.getPackageManager().getServiceInfo(new ComponentName(paramContext, LockerService.class), 128);
        if ((paramContext != null) && (!localActivityInfo.processName.equals(paramContext.processName))) {
          Log.e("BuzzScreen", String.format("[PLEASE FIX]%s processName is invalid. Read guide carefully!!!", new Object[] { paramString }));
        }
        return;
      }
      catch (PackageManager.NameNotFoundException paramContext) {}
      paramContext = paramContext;
      return;
    }
  }
  
  private void a(ActivateListener paramActivateListener)
  {
    this.k.putBooleanAndSync("enabled", true);
    setLockScreenUsageAgreed(true);
    a(0);
    b(false);
    if (paramActivateListener != null)
    {
      this.h = paramActivateListener;
      this.j = false;
    }
    new AdvertisingIdManager(this.c).loadInfo(new AdvertisingIdManager.a()
    {
      public void a(String paramAnonymousString, boolean paramAnonymousBoolean)
      {
        if ((BuzzScreen.getInstance().getUserProfile().getUserDeviceId() != 0) && (!BuzzScreen.getInstance().needInitForUpdate()))
        {
          BuzzLocker.getInstance().start();
          Log.i("BuzzScreen", "activated");
          return;
        }
        BuzzScreen.this.callInit(new BuzzScreenApi.ResponseListener()
        {
          public void onFail()
          {
            BuzzLocker.getInstance().start();
            Log.i("BuzzScreen", "activated");
          }
          
          public void onSuccess()
          {
            BuzzLocker.getInstance().start();
            Log.i("BuzzScreen", "activated");
          }
        });
      }
    });
  }
  
  private void a(final String paramString)
  {
    ((Application)this.c.getApplicationContext()).registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks()
    {
      public void onActivityCreated(Activity paramAnonymousActivity, Bundle paramAnonymousBundle) {}
      
      public void onActivityDestroyed(Activity paramAnonymousActivity) {}
      
      public void onActivityPaused(Activity paramAnonymousActivity) {}
      
      public void onActivityResumed(Activity paramAnonymousActivity)
      {
        try
        {
          String str = paramAnonymousActivity.getClass().getName();
          if (str.equals(paramString)) {
            paramAnonymousActivity = Analytics.Type.ACTIVITY_LOCKER;
          } else if (AppUtils.isLauncherActivity(paramAnonymousActivity)) {
            paramAnonymousActivity = Analytics.Type.ACTIVITY_LAUNCHER;
          } else {
            paramAnonymousActivity = Analytics.Type.ACTIVITY_OTHERS;
          }
          Analytics.trackEvent(paramAnonymousActivity, str);
          return;
        }
        catch (Exception paramAnonymousActivity)
        {
          LogHelper.e("BuzzScreen", paramAnonymousActivity.toString());
        }
      }
      
      public void onActivitySaveInstanceState(Activity paramAnonymousActivity, Bundle paramAnonymousBundle) {}
      
      public void onActivityStarted(Activity paramAnonymousActivity) {}
      
      public void onActivityStopped(Activity paramAnonymousActivity) {}
    });
  }
  
  private void a(boolean paramBoolean)
  {
    Iterator localIterator = this.i.iterator();
    while (localIterator.hasNext())
    {
      OnActivationListener localOnActivationListener = (OnActivationListener)localIterator.next();
      if (paramBoolean) {
        localOnActivationListener.onActivated();
      } else {
        localOnActivationListener.onDeactivated();
      }
    }
  }
  
  /* Error */
  private static boolean a(Context paramContext, PackageInfo paramPackageInfo, String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: getfield 423	android/content/pm/PackageInfo:providers	[Landroid/content/pm/ProviderInfo;
    //   4: astore 9
    //   6: aload 9
    //   8: ifnonnull +5 -> 13
    //   11: iconst_0
    //   12: ireturn
    //   13: aload 9
    //   15: arraylength
    //   16: istore 4
    //   18: iconst_0
    //   19: istore_3
    //   20: iload_3
    //   21: iload 4
    //   23: if_icmpge +309 -> 332
    //   26: aload 9
    //   28: iload_3
    //   29: aaload
    //   30: astore 10
    //   32: aconst_null
    //   33: astore 8
    //   35: aconst_null
    //   36: astore 7
    //   38: aload 7
    //   40: astore_1
    //   41: aload 10
    //   43: getfield 428	android/content/pm/ProviderInfo:name	Ljava/lang/String;
    //   46: ldc_w 430
    //   49: invokevirtual 177	java/lang/Class:getName	()Ljava/lang/String;
    //   52: invokevirtual 281	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   55: ifne +6 -> 61
    //   58: goto +255 -> 313
    //   61: aload 7
    //   63: astore_1
    //   64: ldc -74
    //   66: ldc_w 432
    //   69: iconst_2
    //   70: anewarray 4	java/lang/Object
    //   73: dup
    //   74: iconst_0
    //   75: aload 10
    //   77: getfield 435	android/content/pm/ProviderInfo:packageName	Ljava/lang/String;
    //   80: aastore
    //   81: dup
    //   82: iconst_1
    //   83: aload 10
    //   85: getfield 428	android/content/pm/ProviderInfo:name	Ljava/lang/String;
    //   88: aastore
    //   89: invokestatic 313	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   92: invokestatic 285	com/buzzvil/buzzcore/utils/LogHelper:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   95: aload 7
    //   97: astore_1
    //   98: ldc_w 437
    //   101: iconst_2
    //   102: anewarray 4	java/lang/Object
    //   105: dup
    //   106: iconst_0
    //   107: aload 10
    //   109: getfield 440	android/content/pm/ProviderInfo:authority	Ljava/lang/String;
    //   112: aastore
    //   113: dup
    //   114: iconst_1
    //   115: aload_2
    //   116: aastore
    //   117: invokestatic 313	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   120: astore 10
    //   122: aload 7
    //   124: astore_1
    //   125: aload_0
    //   126: invokevirtual 444	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   129: aload 10
    //   131: invokestatic 450	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   134: aconst_null
    //   135: aconst_null
    //   136: aconst_null
    //   137: aconst_null
    //   138: invokevirtual 456	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   141: astore 7
    //   143: aload 7
    //   145: invokeinterface 462 1 0
    //   150: istore 5
    //   152: iload 5
    //   154: iconst_1
    //   155: if_icmpeq +18 -> 173
    //   158: aload 7
    //   160: ifnull +153 -> 313
    //   163: aload 7
    //   165: invokeinterface 465 1 0
    //   170: goto +143 -> 313
    //   173: aload 7
    //   175: invokeinterface 468 1 0
    //   180: pop
    //   181: aload 7
    //   183: iconst_1
    //   184: invokeinterface 471 2 0
    //   189: astore_1
    //   190: aload 7
    //   192: iconst_2
    //   193: invokeinterface 471 2 0
    //   198: astore 8
    //   200: ldc -74
    //   202: ldc_w 473
    //   205: iconst_2
    //   206: anewarray 4	java/lang/Object
    //   209: dup
    //   210: iconst_0
    //   211: aload_2
    //   212: aastore
    //   213: dup
    //   214: iconst_1
    //   215: aload 8
    //   217: aastore
    //   218: invokestatic 313	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   221: invokestatic 285	com/buzzvil/buzzcore/utils/LogHelper:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   224: aload_1
    //   225: aload_2
    //   226: invokevirtual 281	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   229: ifeq +32 -> 261
    //   232: ldc_w 475
    //   235: aload 8
    //   237: invokevirtual 281	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   240: istore 6
    //   242: iload 6
    //   244: ifeq +17 -> 261
    //   247: aload 7
    //   249: ifnull +10 -> 259
    //   252: aload 7
    //   254: invokeinterface 465 1 0
    //   259: iconst_1
    //   260: ireturn
    //   261: aload 7
    //   263: ifnull +50 -> 313
    //   266: goto -103 -> 163
    //   269: astore_0
    //   270: aload 7
    //   272: astore_1
    //   273: goto +47 -> 320
    //   276: astore 8
    //   278: goto +15 -> 293
    //   281: astore_0
    //   282: goto +38 -> 320
    //   285: astore_1
    //   286: aload 8
    //   288: astore 7
    //   290: aload_1
    //   291: astore 8
    //   293: aload 7
    //   295: astore_1
    //   296: aload 8
    //   298: invokestatic 481	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:printStackTrace	(Ljava/lang/Throwable;)V
    //   301: aload 7
    //   303: ifnull +10 -> 313
    //   306: aload 7
    //   308: invokeinterface 465 1 0
    //   313: iload_3
    //   314: iconst_1
    //   315: iadd
    //   316: istore_3
    //   317: goto -297 -> 20
    //   320: aload_1
    //   321: ifnull +9 -> 330
    //   324: aload_1
    //   325: invokeinterface 465 1 0
    //   330: aload_0
    //   331: athrow
    //   332: iconst_0
    //   333: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	334	0	paramContext	Context
    //   0	334	1	paramPackageInfo	PackageInfo
    //   0	334	2	paramString	String
    //   19	298	3	i1	int
    //   16	8	4	i2	int
    //   150	6	5	i3	int
    //   240	3	6	bool	boolean
    //   36	271	7	localObject1	Object
    //   33	203	8	str	String
    //   276	11	8	localNullPointerException	NullPointerException
    //   291	6	8	localPackageInfo	PackageInfo
    //   4	23	9	arrayOfProviderInfo	android.content.pm.ProviderInfo[]
    //   30	100	10	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   143	152	269	finally
    //   173	242	269	finally
    //   143	152	276	java/lang/NullPointerException
    //   173	242	276	java/lang/NullPointerException
    //   41	58	281	finally
    //   64	95	281	finally
    //   98	122	281	finally
    //   125	143	281	finally
    //   296	301	281	finally
    //   41	58	285	java/lang/NullPointerException
    //   64	95	285	java/lang/NullPointerException
    //   98	122	285	java/lang/NullPointerException
    //   125	143	285	java/lang/NullPointerException
  }
  
  private static boolean a(String paramString, Context paramContext, Class<?> paramClass, int paramInt, boolean paramBoolean, PrivacyPolicy paramPrivacyPolicy)
  {
    LogHelper.setPackageName(paramContext.getPackageName());
    boolean bool = false;
    LogHelper.setEnable(false);
    if (TextUtils.isEmpty(paramString))
    {
      paramString = "empty_app_key";
      Log.e("BuzzScreen", "[PLEASE FIX] appKey can not be empty.");
    }
    else
    {
      bool = true;
    }
    a(paramContext, paramClass.getName());
    BuzzLocker.init(paramContext, paramClass, paramInt);
    BuzzLocker.getInstance().setImageResourceOnEmptyCover(R.drawable.bs_locker_default_ad_cover);
    BuzzLocker.getInstance().setImageResourceOnAdchoice(R.drawable.bs_locker_icon_adchoices);
    BuzzLocker.getInstance().setImageResourceOnOutbrainSponsoredIcon(R.drawable.bs_outbrain_sponsored_logo);
    b = new BuzzScreen(paramString, paramContext, paramClass, paramBoolean, paramPrivacyPolicy);
    if (paramBoolean) {
      Log.i("BuzzScreen", "init with multi process");
    } else {
      Log.i("BuzzScreen", "init");
    }
    if (paramPrivacyPolicy == PrivacyPolicy.GDPR) {
      Log.i("BuzzScreen", "init with gdpr");
    }
    a(paramContext);
    RequestManager.a(paramContext);
    PrivacyPolicyHelper.a(paramContext, null);
    BuzzLocker.getInstance().setConfig(new BuzzLocker.ConfigInterface()
    {
      public int finishInSec()
      {
        return BuzzScreen.getInstance().getPreferenceStore().getInt(BuzzScreen.Settings.FINISH_IN_SEC.toString(), 60);
      }
      
      public BuzzLocker.AutoplayType getAutoplayType()
      {
        return BuzzLocker.AutoplayType.valueOf(BuzzScreen.AutoplayType.a(BuzzScreen.getInstance().getAutoplayType()));
      }
      
      public boolean isEnabled()
      {
        return BuzzScreen.getInstance().getPreferenceStore().getBoolean("enabled", false);
      }
      
      public boolean isHidden()
      {
        return BuzzScreen.getInstance().isSnoozed();
      }
    });
    BuzzLocker.getInstance().setServiceNotification(new BuzzLocker.ServiceNotificationInterface()
    {
      public Notification build()
      {
        if (BuzzScreen.c() == null) {
          BuzzScreen.a(BuzzScreen.a(BuzzScreen.getInstance()));
        }
        return BuzzScreen.c();
      }
      
      public boolean isShowAlways()
      {
        return BuzzScreen.getInstance().getLockerServiceNotificationConfig().isShowAlways();
      }
    });
    BuzzLocker.getInstance().setLockerServiceClass(LockerService.class);
    BuzzLocker.getInstance().setEventListener(new BuzzLocker.EventListener()
    {
      public void onCampaignSettingsUpdated(JSONObject paramAnonymousJSONObject)
        throws JSONException
      {
        Analytics.getInstance().setReportRatio(Analytics.Type.EARLY_RETURN, paramAnonymousJSONObject.optDouble("early_return_report_ratio", 0.5D));
        Analytics.getInstance().setReportRatio(Analytics.Type.DCP_SESSION, paramAnonymousJSONObject.optDouble("dcp_session_report_ratio", 0.5D));
      }
    });
    BuzzLocker.getInstance().setCustomCampaignClass(Campaign.class);
    BuzzLocker.getInstance().setLockerLandingHelperActivityClass(LandingHelperActivity.class);
    BuzzLocker.getInstance().setLandingOverlayActivityClass(LandingOverlayActivity.class);
    BuzzLocker.getInstance().setVideoOverlayActivityClass(VideoOverlayActivity.class);
    Analytics.init(paramContext);
    return bool;
  }
  
  private void b(boolean paramBoolean)
  {
    this.k.putBooleanAndSync("deactivate_until_reboot", paramBoolean);
  }
  
  private void d()
  {
    LogHelper.d("BuzzScreen", "loadAdidAndCallInit");
    if ((getInstance().getUserProfile().getUserDeviceId() != 0) && (this.k.getBoolean("enabled", false))) {
      new AdvertisingIdManager(this.c).loadInfo(new AdvertisingIdManager.a()
      {
        public void a(String paramAnonymousString, boolean paramAnonymousBoolean)
        {
          Log.d("BuzzScreen", String.format("adid:%s // isLimitAdTrackingEnabled:%s", new Object[] { paramAnonymousString, Boolean.toString(paramAnonymousBoolean) }));
          if (BuzzScreen.this.needInitForUpdate()) {
            BuzzScreen.this.callInit(null);
          }
        }
      });
    }
  }
  
  private Notification e()
  {
    Object localObject;
    if (Build.VERSION.SDK_INT >= 26)
    {
      localObject = new NotificationChannel("BuzzScreenLockerService", "Locker Service", 1);
      ((NotificationChannel)localObject).setDescription("Locker Service");
      ((NotificationChannel)localObject).setShowBadge(false);
      ((NotificationManager)this.c.getSystemService("notification")).createNotificationChannel((NotificationChannel)localObject);
      localObject = new NotificationCompat.Builder(this.c, "BuzzScreenLockerService");
    }
    else
    {
      localObject = new NotificationCompat.Builder(this.c);
      ((NotificationCompat.Builder)localObject).setPriority(-2);
    }
    ((NotificationCompat.Builder)localObject).setWhen(0L);
    Intent localIntent;
    if (this.a.getIntent() != null)
    {
      localIntent = this.a.getIntent();
    }
    else
    {
      localIntent = AppUtils.getLaunchIntent(this.c);
      localIntent.setFlags(603979776);
    }
    ((NotificationCompat.Builder)localObject).setContentIntent(PendingIntent.getActivity(this.c, 0, localIntent, 0));
    if (this.a.getTitle().length() > 0) {
      ((NotificationCompat.Builder)localObject).setContentTitle(this.a.getTitle());
    }
    if (this.a.getText().length() > 0) {
      ((NotificationCompat.Builder)localObject).setContentText(this.a.getText());
    }
    if (this.a.getSmallIconResourceId() != 0) {
      ((NotificationCompat.Builder)localObject).setSmallIcon(this.a.getSmallIconResourceId());
    }
    if (this.a.getLargeIconResourceId() != 0) {
      ((NotificationCompat.Builder)localObject).setLargeIcon(BitmapFactory.decodeResource(this.c.getResources(), this.a.getLargeIconResourceId()));
    }
    return ((NotificationCompat.Builder)localObject).build();
  }
  
  public static List<String> getActivatedPackageNames(Context paramContext)
  {
    Object localObject1 = PlatformUtils.getRunningServicePackageSet(paramContext, LockerService.class.getName());
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("[getActivatedPackageNames] getRunningServicePackageSet :");
    ((StringBuilder)localObject2).append(localObject1.toString());
    LogHelper.d("BuzzScreen", ((StringBuilder)localObject2).toString());
    Object localObject3 = new PreferenceStore(paramContext, false);
    localObject2 = paramContext.getPackageName();
    if (((PreferenceStore)localObject3).getBoolean("enabled", false)) {
      ((Set)localObject1).add(localObject2);
    }
    localObject3 = paramContext.getPackageManager().getInstalledPackages(8).iterator();
    while (((Iterator)localObject3).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject3).next();
      if ((!((Set)localObject1).contains(localPackageInfo.packageName)) && (!((String)localObject2).equals(localPackageInfo.packageName)) && (a(paramContext, localPackageInfo, "enabled")))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("[getActivatedPackageNames] checkEnabledWithProviders :");
        localStringBuilder.append(localPackageInfo.packageName);
        LogHelper.d("BuzzScreen", localStringBuilder.toString());
        ((Set)localObject1).add(localPackageInfo.packageName);
      }
    }
    paramContext = new ArrayList((Collection)localObject1);
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("[getActivatedPackageNames] packages :");
    ((StringBuilder)localObject1).append(paramContext.toString());
    LogHelper.d("BuzzScreen", ((StringBuilder)localObject1).toString());
    return paramContext;
  }
  
  public static BuzzScreen getInstance()
  {
    if (b != null) {
      return b;
    }
    throw new RuntimeException("BuzzScreen class not correctly initialized. Please call BuzzScreen.init in the Application class onCreate.");
  }
  
  public static List<String> getSnoozedPackageNames(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramContext.getPackageManager().getInstalledPackages(8).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (a(paramContext, localPackageInfo, "snoozed")) {
        localArrayList.add(localPackageInfo.packageName);
      }
    }
    paramContext = new StringBuilder();
    paramContext.append("[getSnoozedPackageNames] packages :");
    paramContext.append(localArrayList.toString());
    LogHelper.d("BuzzScreen", paramContext.toString());
    return localArrayList;
  }
  
  public static boolean init(Context paramContext, Class<?> paramClass, int paramInt)
  {
    return init(AppUtils.getMetaDataStringFromManifest(paramContext, "com.buzzvil.buzzscreen.sdk.app_key"), paramContext, paramClass, paramInt);
  }
  
  public static boolean init(String paramString, Context paramContext, Class<?> paramClass, int paramInt)
  {
    return a(paramString, paramContext, paramClass, paramInt, AppUtils.getMetaDataBooleanFromManifest(paramContext, "com.buzzvil.buzzscreen.sdk.use_multi_process"), PrivacyPolicy.NONE);
  }
  
  public static boolean init(String paramString, Context paramContext, Class<?> paramClass, int paramInt, PrivacyPolicy paramPrivacyPolicy)
  {
    return a(paramString, paramContext, paramClass, paramInt, AppUtils.getMetaDataBooleanFromManifest(paramContext, "com.buzzvil.buzzscreen.sdk.use_multi_process"), paramPrivacyPolicy);
  }
  
  public static void runInBackground(boolean paramBoolean)
  {
    LockerService.a = paramBoolean;
  }
  
  public static void setLockerNotification(Notification paramNotification)
  {
    if (!getInstance().getLockerServiceNotificationConfig().isShowAlways())
    {
      Log.e("BuzzScreen", "You must set 'isShowAlways' true to use notifyOnServiceNotification");
      return;
    }
    n = paramNotification;
  }
  
  public static void showLog(boolean paramBoolean)
  {
    LogHelper.setEnable(paramBoolean);
  }
  
  OnPointListener a()
  {
    return this.g;
  }
  
  public void activate()
  {
    activate(null);
  }
  
  public void activate(ActivateListener paramActivateListener)
  {
    if (TextUtils.isEmpty(this.e.getUserId())) {
      throw new RuntimeException("You must set user id. Please call setUserId() before calling BuzzScreen.getInstance().activate().");
    }
    a(paramActivateListener);
  }
  
  public void activateIfConsent(Activity paramActivity, final OnActivateListener paramOnActivateListener)
  {
    if (TextUtils.isEmpty(this.e.getUserId())) {
      throw new RuntimeException("You must set user id. Please call setUserId() before calling BuzzScreen.getInstance().activateIfConsent(...).");
    }
    if (paramOnActivateListener == null) {
      throw new RuntimeException("You must set OnActivateListener. listener can not be null");
    }
    if (!this.k.getBoolean("use_privacy_policy", true))
    {
      Log.i("BuzzScreen", "Consent not required");
      a(null);
      paramOnActivateListener.onActivated();
      return;
    }
    if (this.k.getBoolean("consent_privacy_policy", false))
    {
      Log.i("BuzzScreen", "Consent already has");
      a(null);
      paramOnActivateListener.onActivated();
      return;
    }
    PrivacyPolicyHelper.a(paramActivity, this.f, new PrivacyPolicyHelper.a()
    {
      public void a(PrivacyPolicyHelper.ObtainState paramAnonymousObtainState)
      {
        switch (BuzzScreen.3.a[paramAnonymousObtainState.ordinal()])
        {
        default: 
          return;
        case 4: 
          paramOnActivateListener.onNetworkError();
          return;
        case 3: 
          paramOnActivateListener.onCancelledByUser();
          return;
        }
        BuzzScreen.a(BuzzScreen.this, null);
        paramOnActivateListener.onActivated();
      }
    });
  }
  
  @Deprecated
  public void applyMigrationData(Bundle paramBundle)
  {
    Iterator localIterator = paramBundle.keySet().iterator();
    for (;;)
    {
      boolean bool = localIterator.hasNext();
      int i1 = 1;
      if (!bool) {
        break;
      }
      String str = (String)localIterator.next();
      Object localObject = paramBundle.get(str);
      if (localObject != null)
      {
        switch (str.hashCode())
        {
        default: 
          break;
        case -147132913: 
          if (str.equals("user_id")) {
            i1 = 0;
          }
          break;
        case -192844312: 
          if (str.equals("user_region")) {
            i1 = 2;
          }
          break;
        case -507561547: 
          if (!str.equals("user_gender")) {
            break;
          }
          break;
        case -1790469967: 
          if (str.equals("user_birth_year")) {
            i1 = 3;
          }
          break;
        case -2015126113: 
          if (str.equals("user_custom_target_3")) {
            i1 = 6;
          }
          break;
        case -2015126114: 
          if (str.equals("user_custom_target_2")) {
            i1 = 5;
          }
          break;
        case -2015126115: 
          if (str.equals("user_custom_target_1")) {
            i1 = 4;
          }
          break;
        }
        i1 = -1;
        switch (i1)
        {
        default: 
          break;
        case 6: 
          this.e.setCustomTarget3((String)localObject);
          break;
        case 5: 
          this.e.setCustomTarget2((String)localObject);
          break;
        case 4: 
          this.e.setCustomTarget1((String)localObject);
          break;
        case 3: 
          this.e.setBirthYear(((Integer)localObject).intValue());
          break;
        case 2: 
          this.e.setRegion((String)localObject);
          break;
        case 1: 
          this.e.setGender((String)localObject);
          break;
        case 0: 
          this.e.setUserId((String)localObject);
        }
      }
    }
    this.k.putBooleanAndSync("need_device_init_for_update", true);
  }
  
  boolean b()
  {
    return this.f;
  }
  
  public void callInit(BuzzScreenApi.ResponseListener paramResponseListener)
  {
    BuzzScreenApi.a(this.c, paramResponseListener);
  }
  
  public void deactivate()
  {
    if (isPreventDeactivating())
    {
      Log.i("BuzzScreen", "prevent deactivating");
      return;
    }
    this.k.putBooleanAndSync("enabled", false);
    a(0);
    b(false);
    BuzzLocker.getInstance().stop();
    Log.i("BuzzScreen", "deactivated");
  }
  
  public void deactivateUntilReboot()
  {
    Log.i("BuzzScreen", "deactivateUntilReboot");
    deactivate();
    b(true);
  }
  
  @Deprecated
  public void deleteUser(ApiCallListener paramApiCallListener)
  {
    Log.d("BuzzScreen", "deleteUser");
    BuzzScreenApi.a(this.c, paramApiCallListener);
  }
  
  public String getAppKey()
  {
    return this.d;
  }
  
  public AutoplayType getAutoplayType()
  {
    AutoplayType localAutoplayType2 = AutoplayType.valueOf(this.k.getInt("autoplay_type", 0));
    AutoplayType localAutoplayType1 = localAutoplayType2;
    if (localAutoplayType2 == null)
    {
      localAutoplayType1 = AutoplayType.ON_WIFI;
      setAutoplayType(localAutoplayType1);
    }
    return localAutoplayType1;
  }
  
  public LockerServiceNotificationConfig getLockerServiceNotificationConfig()
  {
    return this.a;
  }
  
  @Deprecated
  public Bundle getMigrationData()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("user_id", this.e.getUserId());
    localBundle.putString("user_gender", this.e.getGender());
    localBundle.putString("user_region", this.e.getRegion());
    localBundle.putInt("user_birth_year", this.e.getBirthYear());
    localBundle.putString("user_custom_target_1", this.e.getCustomTarget(1));
    localBundle.putString("user_custom_target_2", this.e.getCustomTarget(2));
    localBundle.putString("user_custom_target_3", this.e.getCustomTarget(3));
    return localBundle;
  }
  
  public PreferenceStore getPreferenceStore()
  {
    return this.k;
  }
  
  public String getSettings(Settings paramSettings)
  {
    return this.k.getString(paramSettings.toString(), null);
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public String getSettingsPageDisplayRatio()
  {
    return this.k.getString(Settings.PAGE_DISPLAY_RATIO.toString(), null);
  }
  
  public int getSnoozeTo()
  {
    return this.k.getInt("snooze_to", 0);
  }
  
  public UserProfile getUserProfile()
  {
    return this.e;
  }
  
  public boolean isActivated()
  {
    return this.k.getBoolean("enabled", false);
  }
  
  public boolean isCardViewCleanModeEnabled()
  {
    return this.k.getBoolean("card_view_clean_mode_enabled", false);
  }
  
  public boolean isCardViewEnabled()
  {
    return this.k.getBoolean("card_view_enabled", false);
  }
  
  public boolean isConsentGranted()
  {
    return PrivacyPolicyHelper.isConsentGranted();
  }
  
  public boolean isDeactivatedUntilReboot()
  {
    return this.k.getBoolean("deactivate_until_reboot", false);
  }
  
  public boolean isFeedEnabled()
  {
    PreferenceStore localPreferenceStore = this.k;
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (localPreferenceStore.getBoolean("use_feed", false))
    {
      bool1 = bool2;
      if (!PrivacyPolicyHelper.isConsentRequired()) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  @Deprecated
  public boolean isLockScreenUsageAgreed()
  {
    return this.k.getBoolean("lockscreen_usage_agreed", false);
  }
  
  public boolean isPreventDeactivating()
  {
    return AppUtils.getMetaDataBooleanFromManifest(this.c, "com.buzzvil.buzzscreen.sdk.prevent_deactivating");
  }
  
  public boolean isSnoozed()
  {
    PreferenceStore localPreferenceStore = this.k;
    boolean bool = false;
    int i1 = localPreferenceStore.getInt("snooze_to", 0);
    if (TimeUtils.getCurrentTimestamp() < i1) {
      bool = true;
    }
    return bool;
  }
  
  public boolean isV3Enabled()
  {
    return this.k.getBoolean("use_v3", false);
  }
  
  public void launch()
  {
    d();
    BuzzLocker.getInstance().launch();
  }
  
  public void logout()
  {
    getInstance().deactivate();
    BuzzLocker.getInstance().clearCampaignPoolCache();
    Iterator localIterator = PrefKey.a().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      this.k.remove(str);
    }
  }
  
  public boolean needInitForUpdate()
  {
    return this.k.getBoolean("need_device_init_for_update", false);
  }
  
  @Deprecated
  public void notifyOnServiceNotification(Notification paramNotification)
  {
    LogHelper.d("BuzzScreen", "notifyOnServiceNotification");
    if (!this.a.isShowAlways())
    {
      Log.e("BuzzScreen", "You must set 'isShowAlways' true to use notifyOnServiceNotification");
      return;
    }
    NotificationManager localNotificationManager = (NotificationManager)this.c.getSystemService("notification");
    if (paramNotification == null)
    {
      localNotificationManager.notify(LockerService.a(), e());
      return;
    }
    localNotificationManager.notify(LockerService.a(), paramNotification);
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void onAllocationCompleted()
  {
    if ((this.h != null) && (!this.j))
    {
      this.h.onReady();
      this.j = true;
    }
    BuzzScreenApi.a(this.c);
  }
  
  public void onObtainConsent()
  {
    BuzzLocker.getInstance().refreshStaleAds();
  }
  
  public void registerOnActivationListener(OnActivationListener paramOnActivationListener)
  {
    unregisterOnActivationListener(paramOnActivationListener);
    this.i.add(paramOnActivationListener);
  }
  
  public void resetInteractiveGuide()
  {
    this.m.reset();
  }
  
  public void setAutoplayType(@NonNull AutoplayType paramAutoplayType)
  {
    AutoplayType localAutoplayType = AutoplayType.valueOf(this.k.getInt("autoplay_type", 0));
    this.k.putIntAndSync("autoplay_type", AutoplayType.a(paramAutoplayType));
    if ((localAutoplayType != paramAutoplayType) && (getUserProfile().getUserDeviceId() > 0)) {
      BuzzScreenApi.putUserConfig(getUserProfile().getUserDeviceId(), AutoplayType.a(paramAutoplayType));
    }
  }
  
  public void setCardViewCleanModeEnable(boolean paramBoolean)
  {
    this.k.putBooleanAndSync("card_view_clean_mode_enabled", paramBoolean);
  }
  
  public void setCardViewEnable(boolean paramBoolean)
  {
    if (paramBoolean != isCardViewEnabled())
    {
      String str;
      if (paramBoolean) {
        str = "enable";
      } else {
        str = "disable";
      }
      Analytics.trackEvent("cardview", str);
    }
    this.k.putBooleanAndSync("card_view_enabled", paramBoolean);
  }
  
  public void setInteractiveGuide(InteractiveGuideConfig paramInteractiveGuideConfig)
  {
    this.m.enable(paramInteractiveGuideConfig.isEnabled());
    this.m.setConfig(paramInteractiveGuideConfig.toJsonString());
  }
  
  @Deprecated
  public void setLockScreenUsageAgreed(boolean paramBoolean)
  {
    this.k.putBooleanAndSync("lockscreen_usage_agreed", paramBoolean);
  }
  
  public void setOnPointListener(OnPointListener paramOnPointListener)
  {
    this.g = paramOnPointListener;
  }
  
  public void setSettings(Settings paramSettings, int paramInt)
  {
    this.k.putIntAndSync(paramSettings.toString(), paramInt);
  }
  
  public void setSettings(Settings paramSettings, String paramString)
  {
    this.k.putStringAndSync(paramSettings.toString(), paramString);
  }
  
  public void setUseFeed(boolean paramBoolean)
  {
    setUseFeed(paramBoolean, null);
  }
  
  public void setUseFeed(boolean paramBoolean, String paramString)
  {
    this.k.putBooleanAndSync("use_feed", paramBoolean);
    if (!TextUtils.isEmpty(paramString)) {
      this.k.putStringAndSync("feed_unit_id", paramString);
    }
  }
  
  public void setUseV3(boolean paramBoolean)
  {
    this.k.putBooleanAndSync("use_v3", paramBoolean);
  }
  
  public void showLockScreen()
  {
    BuzzLocker.getInstance().showLocker();
  }
  
  public void showRevokeConsentDialog(Activity paramActivity, final OnConsentRevokeListener paramOnConsentRevokeListener)
  {
    PrivacyPolicyHelper.a(paramActivity, this.f, new OnConsentRevokeListener()
    {
      public void onCancelledByUser()
      {
        paramOnConsentRevokeListener.onCancelledByUser();
      }
      
      public void onConsentRevoked()
      {
        Log.i("BuzzScreen", "Consent revoked");
        paramOnConsentRevokeListener.onConsentRevoked();
        BuzzScreen.this.deactivate();
      }
    });
  }
  
  public void simpleEvent(String paramString)
  {
    BuzzScreenApi.a(this.c, paramString);
  }
  
  public void snooze(int paramInt)
  {
    paramInt = TimeUtils.getCurrentTimestamp() + paramInt;
    a(paramInt);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("wake up after ");
    localStringBuilder.append(Integer.toString(paramInt));
    Log.i("BuzzScreen", localStringBuilder.toString());
  }
  
  public void unregisterOnActivationListener(OnActivationListener paramOnActivationListener)
  {
    Iterator localIterator = this.i.iterator();
    while (localIterator.hasNext()) {
      if (((OnActivationListener)localIterator.next()).equals(paramOnActivationListener))
      {
        localIterator.remove();
        return;
      }
    }
  }
  
  public void useTutorial(@NonNull List<Integer> paramList, int paramInt)
  {
    this.l.setUseTutorial(true);
    this.l.setTutorialImageResIds(paramList);
    this.l.setTutorialButtonResId(paramInt);
  }
  
  @Deprecated
  public static abstract interface ActivateListener
  {
    public abstract void onReady();
  }
  
  @Deprecated
  public static abstract interface ApiCallListener
  {
    public abstract void onFail(int paramInt);
    
    public abstract void onSuccess();
  }
  
  public static enum AutoplayType
  {
    private final int a;
    
    private AutoplayType(int paramInt)
    {
      this.a = paramInt;
    }
    
    private int a()
    {
      return this.a;
    }
    
    @Nullable
    public static AutoplayType valueOf(int paramInt)
    {
      AutoplayType[] arrayOfAutoplayType = values();
      int i = 0;
      int j = arrayOfAutoplayType.length;
      while (i < j)
      {
        AutoplayType localAutoplayType = arrayOfAutoplayType[i];
        if (localAutoplayType.a == paramInt) {
          return localAutoplayType;
        }
        i += 1;
      }
      return null;
    }
  }
  
  public static abstract interface OnActivateListener
  {
    public abstract void onActivated();
    
    public abstract void onCancelledByUser();
    
    public abstract void onNetworkError();
  }
  
  public static abstract interface OnActivationListener
  {
    public abstract void onActivated();
    
    public abstract void onDeactivated();
  }
  
  public static abstract interface OnConsentRevokeListener
  {
    public abstract void onCancelledByUser();
    
    public abstract void onConsentRevoked();
  }
  
  public static abstract interface OnPointListener
  {
    public abstract void onFail(BuzzScreen.PointType paramPointType);
    
    public abstract void onSuccess(BuzzScreen.PointType paramPointType, int paramInt);
  }
  
  public static enum PointType
  {
    private PointType() {}
  }
  
  public static enum PrivacyPolicy
  {
    private PrivacyPolicy() {}
  }
  
  public static enum Settings
  {
    private String a;
    
    private Settings(String paramString)
    {
      this.a = paramString;
    }
    
    public String toString()
    {
      return this.a;
    }
  }
}
