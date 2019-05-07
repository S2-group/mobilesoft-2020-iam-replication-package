package com.jaumo;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.support.multidex.MultiDexApplication;
import com.crashlytics.android.Crashlytics;
import com.facebook.device.yearclass.YearClass;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineConfig.Builder;
import com.facebook.network.connectionclass.ConnectionClassManager;
import com.facebook.network.connectionclass.ConnectionClassManager.ConnectionClassStateChangeListener;
import com.facebook.network.connectionclass.ConnectionQuality;
import com.jaumo.ads.GoogleAdConsent;
import com.jaumo.auth.AuthManager;
import com.jaumo.boost.BoostApi;
import com.jaumo.classes.AdvertiserId;
import com.jaumo.classes.AppActive;
import com.jaumo.classes.Publisher;
import com.jaumo.data.V2Loader;
import com.jaumo.fcm.FcmHelper;
import com.jaumo.filter.FilterApi;
import com.jaumo.location.LocationUpdater;
import com.jaumo.mqtt.MQTTConnection;
import com.jaumo.mqtt.MQTTLifecycle;
import com.jaumo.mqtt.MQTTMessageReceivedListener;
import com.jaumo.network.RestConfig;
import com.jaumo.pushinator.Pushinator;
import com.jaumo.sessionstate.CacheDirManager;
import com.jaumo.sessionstate.PreferencesSessionManager;
import com.jaumo.sessionstate.SessionManager;
import com.jaumo.sessionstate.SessionStateListener;
import com.jaumo.sessionstate.TranslationsLoader;
import com.jaumo.util.Tracker;
import com.vk.sdk.VKSdk;
import helper.Cache;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import net.danlew.android.joda.JodaTimeAndroid;
import timber.log.Timber;
import timber.log.Timber.Tree;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig.Builder;

@Metadata(d1={"\000\001\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\020\b\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\b\004\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\020\002\n\002\b\005*\001.\b\026\030\000 `2\0020\001:\001`B\005¢\006\002\020\002J\b\020[\032\0020\\H\004J\b\020]\032\0020\\H\026J\b\020^\032\0020\\H\026J\006\020_\032\0020\\R\036\020\003\032\0020\0048\000@\000X.¢\006\016\n\000\032\004\b\005\020\006\"\004\b\007\020\bR\036\020\t\032\0020\n8\000@\000X.¢\006\016\n\000\032\004\b\013\020\f\"\004\b\r\020\016R\036\020\017\032\0020\0208\000@\000X.¢\006\016\n\000\032\004\b\021\020\022\"\004\b\023\020\024R\032\020\025\032\0020\026X\016¢\006\016\n\000\032\004\b\027\020\030\"\004\b\031\020\032R\036\020\033\032\0020\0348\000@\000X.¢\006\016\n\000\032\004\b\035\020\036\"\004\b\037\020 R\036\020!\032\0020\"8\000@\000X.¢\006\016\n\000\032\004\b#\020$\"\004\b%\020&R\032\020'\032\0020(X.¢\006\016\n\000\032\004\b)\020*\"\004\b+\020,R\020\020-\032\0020.X\004¢\006\004\n\002\020/R\016\0200\032\0020\026X\016¢\006\002\n\000R\036\0201\032\002028\000@\000X.¢\006\016\n\000\032\004\b3\0204\"\004\b5\0206R\036\0207\032\002088\000@\000X.¢\006\016\n\000\032\004\b9\020:\"\004\b;\020<R\036\020=\032\0020>8\000@\000X.¢\006\016\n\000\032\004\b?\020@\"\004\bA\020BR\036\020C\032\0020D8\000@\000X.¢\006\016\n\000\032\004\bE\020F\"\004\bG\020HR\036\020I\032\0020J8\000@\000X.¢\006\016\n\000\032\004\bK\020L\"\004\bM\020NR\036\020O\032\0020P8\000@\000X.¢\006\016\n\000\032\004\bQ\020R\"\004\bS\020TR\036\020U\032\0020V8\000@\000X.¢\006\016\n\000\032\004\bW\020X\"\004\bY\020Z¨\006a"}, d2={"Lcom/jaumo/App;", "Landroid/support/multidex/MultiDexApplication;", "()V", "authManager", "Lcom/jaumo/auth/AuthManager;", "getAuthManager$jaumo_android_pinkRelease", "()Lcom/jaumo/auth/AuthManager;", "setAuthManager$jaumo_android_pinkRelease", "(Lcom/jaumo/auth/AuthManager;)V", "boostApi", "Lcom/jaumo/boost/BoostApi;", "getBoostApi$jaumo_android_pinkRelease", "()Lcom/jaumo/boost/BoostApi;", "setBoostApi$jaumo_android_pinkRelease", "(Lcom/jaumo/boost/BoostApi;)V", "cache", "Lhelper/Cache;", "getCache$jaumo_android_pinkRelease", "()Lhelper/Cache;", "setCache$jaumo_android_pinkRelease", "(Lhelper/Cache;)V", "deviceYear", "", "getDeviceYear", "()I", "setDeviceYear", "(I)V", "fcmHelper", "Lcom/jaumo/fcm/FcmHelper;", "getFcmHelper$jaumo_android_pinkRelease", "()Lcom/jaumo/fcm/FcmHelper;", "setFcmHelper$jaumo_android_pinkRelease", "(Lcom/jaumo/fcm/FcmHelper;)V", "filterApi", "Lcom/jaumo/filter/FilterApi;", "getFilterApi$jaumo_android_pinkRelease", "()Lcom/jaumo/filter/FilterApi;", "setFilterApi$jaumo_android_pinkRelease", "(Lcom/jaumo/filter/FilterApi;)V", "jaumoComponent", "Lcom/jaumo/JaumoComponent;", "getJaumoComponent", "()Lcom/jaumo/JaumoComponent;", "setJaumoComponent", "(Lcom/jaumo/JaumoComponent;)V", "lifecycleListener", "com/jaumo/App$lifecycleListener$1", "Lcom/jaumo/App$lifecycleListener$1;", "memoryClass", "mqtt", "Lcom/jaumo/mqtt/MQTTConnection;", "getMqtt$jaumo_android_pinkRelease", "()Lcom/jaumo/mqtt/MQTTConnection;", "setMqtt$jaumo_android_pinkRelease", "(Lcom/jaumo/mqtt/MQTTConnection;)V", "mqttLifecycle", "Lcom/jaumo/mqtt/MQTTLifecycle;", "getMqttLifecycle$jaumo_android_pinkRelease", "()Lcom/jaumo/mqtt/MQTTLifecycle;", "setMqttLifecycle$jaumo_android_pinkRelease", "(Lcom/jaumo/mqtt/MQTTLifecycle;)V", "publisher", "Lcom/jaumo/classes/Publisher;", "getPublisher$jaumo_android_pinkRelease", "()Lcom/jaumo/classes/Publisher;", "setPublisher$jaumo_android_pinkRelease", "(Lcom/jaumo/classes/Publisher;)V", "pushinator", "Lcom/jaumo/pushinator/Pushinator;", "getPushinator$jaumo_android_pinkRelease", "()Lcom/jaumo/pushinator/Pushinator;", "setPushinator$jaumo_android_pinkRelease", "(Lcom/jaumo/pushinator/Pushinator;)V", "sessionManager", "Lcom/jaumo/sessionstate/SessionManager;", "getSessionManager$jaumo_android_pinkRelease", "()Lcom/jaumo/sessionstate/SessionManager;", "setSessionManager$jaumo_android_pinkRelease", "(Lcom/jaumo/sessionstate/SessionManager;)V", "tracker", "Lcom/jaumo/util/Tracker;", "getTracker$jaumo_android_pinkRelease", "()Lcom/jaumo/util/Tracker;", "setTracker$jaumo_android_pinkRelease", "(Lcom/jaumo/util/Tracker;)V", "v2", "Lcom/jaumo/data/V2Loader;", "getV2$jaumo_android_pinkRelease", "()Lcom/jaumo/data/V2Loader;", "setV2$jaumo_android_pinkRelease", "(Lcom/jaumo/data/V2Loader;)V", "initLogging", "", "onCreate", "onLowMemory", "onOutOfMemory", "Companion", "jaumo-android_pinkRelease"})
public class App
  extends MultiDexApplication
{
  public static final Companion Companion = new Companion(null);
  private static App application;
  @Inject
  public AuthManager authManager;
  @Inject
  public BoostApi boostApi;
  @Inject
  public Cache cache;
  private int deviceYear;
  @Inject
  public FcmHelper fcmHelper;
  @Inject
  public FilterApi filterApi;
  public JaumoComponent jaumoComponent;
  private final lifecycleListener.1 lifecycleListener;
  private int memoryClass;
  @Inject
  public MQTTConnection mqtt;
  @Inject
  public MQTTLifecycle mqttLifecycle;
  @Inject
  public Publisher publisher;
  @Inject
  public Pushinator pushinator;
  @Inject
  public SessionManager sessionManager;
  @Inject
  public Tracker tracker;
  @Inject
  public V2Loader v2;
  
  public App()
  {
    application = (App)this;
    this.lifecycleListener = new AppLifecycleManager.AppLifecycleListener()
    {
      public void onAppPaused()
      {
        Timber.d("onAppPaused()", new Object[0]);
        this.this$0.getSessionManager$jaumo_android_pinkRelease().pauseApplication();
      }
      
      public void onAppResumed()
      {
        Timber.d("onAppResumed()", new Object[0]);
        if (this.this$0.getAuthManager$jaumo_android_pinkRelease().isAuthenticated()) {
          this.this$0.getFcmHelper$jaumo_android_pinkRelease().register(this.this$0.getApplicationContext());
        }
        this.this$0.getSessionManager$jaumo_android_pinkRelease().resumeApplication();
      }
      
      public void onAppStarted()
      {
        Timber.d("onAppStarted()", new Object[0]);
        LocationUpdater.init(this.this$0.getApplicationContext());
        this.this$0.getSessionManager$jaumo_android_pinkRelease().addListener((SessionStateListener)new CacheDirManager(this.this$0.getApplicationContext())).addListener((SessionStateListener)this.this$0.getPublisher$jaumo_android_pinkRelease()).addListener((SessionStateListener)new AppActive(this.this$0.getApplicationContext())).addListener((SessionStateListener)new AdvertiserId(this.this$0.getApplicationContext())).addListener((SessionStateListener)this.this$0.getMqttLifecycle$jaumo_android_pinkRelease()).addListener((SessionStateListener)LocationUpdater.getInstance()).addListener((SessionStateListener)this.this$0.getAuthManager$jaumo_android_pinkRelease()).addListener((SessionStateListener)new PreferencesSessionManager()).addListener((SessionStateListener)new TranslationsLoader()).addListener((SessionStateListener)this.this$0.getBoostApi$jaumo_android_pinkRelease()).addListener((SessionStateListener)this.this$0.getFilterApi$jaumo_android_pinkRelease()).addListener((SessionStateListener)this.this$0.getPushinator$jaumo_android_pinkRelease()).startApplication((Application)App.access$getApplication$cp());
        this.this$0.getMqtt$jaumo_android_pinkRelease().addMessageReceivedListener("pushinator", (MQTTMessageReceivedListener)this.this$0.getPushinator$jaumo_android_pinkRelease());
      }
      
      public void onAppStopped()
      {
        Timber.d("onAppStopped()", new Object[0]);
        this.this$0.getSessionManager$jaumo_android_pinkRelease().stopApplication();
      }
    };
  }
  
  public static final App get()
  {
    return Companion.get();
  }
  
  public static final Context getContext()
  {
    return Companion.getContext();
  }
  
  public final AuthManager getAuthManager$jaumo_android_pinkRelease()
  {
    AuthManager localAuthManager = this.authManager;
    if (localAuthManager == null) {
      Intrinsics.throwUninitializedPropertyAccessException("authManager");
    }
    return localAuthManager;
  }
  
  public final BoostApi getBoostApi$jaumo_android_pinkRelease()
  {
    BoostApi localBoostApi = this.boostApi;
    if (localBoostApi == null) {
      Intrinsics.throwUninitializedPropertyAccessException("boostApi");
    }
    return localBoostApi;
  }
  
  public final int getDeviceYear()
  {
    return this.deviceYear;
  }
  
  public final FcmHelper getFcmHelper$jaumo_android_pinkRelease()
  {
    FcmHelper localFcmHelper = this.fcmHelper;
    if (localFcmHelper == null) {
      Intrinsics.throwUninitializedPropertyAccessException("fcmHelper");
    }
    return localFcmHelper;
  }
  
  public final FilterApi getFilterApi$jaumo_android_pinkRelease()
  {
    FilterApi localFilterApi = this.filterApi;
    if (localFilterApi == null) {
      Intrinsics.throwUninitializedPropertyAccessException("filterApi");
    }
    return localFilterApi;
  }
  
  public final JaumoComponent getJaumoComponent()
  {
    JaumoComponent localJaumoComponent = this.jaumoComponent;
    if (localJaumoComponent == null) {
      Intrinsics.throwUninitializedPropertyAccessException("jaumoComponent");
    }
    return localJaumoComponent;
  }
  
  public final MQTTConnection getMqtt$jaumo_android_pinkRelease()
  {
    MQTTConnection localMQTTConnection = this.mqtt;
    if (localMQTTConnection == null) {
      Intrinsics.throwUninitializedPropertyAccessException("mqtt");
    }
    return localMQTTConnection;
  }
  
  public final MQTTLifecycle getMqttLifecycle$jaumo_android_pinkRelease()
  {
    MQTTLifecycle localMQTTLifecycle = this.mqttLifecycle;
    if (localMQTTLifecycle == null) {
      Intrinsics.throwUninitializedPropertyAccessException("mqttLifecycle");
    }
    return localMQTTLifecycle;
  }
  
  public final Publisher getPublisher$jaumo_android_pinkRelease()
  {
    Publisher localPublisher = this.publisher;
    if (localPublisher == null) {
      Intrinsics.throwUninitializedPropertyAccessException("publisher");
    }
    return localPublisher;
  }
  
  public final Pushinator getPushinator$jaumo_android_pinkRelease()
  {
    Pushinator localPushinator = this.pushinator;
    if (localPushinator == null) {
      Intrinsics.throwUninitializedPropertyAccessException("pushinator");
    }
    return localPushinator;
  }
  
  public final SessionManager getSessionManager$jaumo_android_pinkRelease()
  {
    SessionManager localSessionManager = this.sessionManager;
    if (localSessionManager == null) {
      Intrinsics.throwUninitializedPropertyAccessException("sessionManager");
    }
    return localSessionManager;
  }
  
  protected final void initLogging()
  {
    Fabric.with(getApplicationContext(), new Kit[] { (Kit)new Crashlytics() });
    Timber.plant((Timber.Tree)new Timber.Tree()
    {
      protected void log(int paramAnonymousInt, String paramAnonymousString1, String paramAnonymousString2, Throwable paramAnonymousThrowable)
      {
        Intrinsics.checkParameterIsNotNull(paramAnonymousString2, "message");
        if (paramAnonymousInt != 2)
        {
          if (paramAnonymousInt == 3) {
            return;
          }
          Crashlytics.log(paramAnonymousInt, paramAnonymousString1, paramAnonymousString2);
          if (paramAnonymousThrowable != null) {
            Crashlytics.logException(paramAnonymousThrowable);
          }
          return;
        }
      }
    });
  }
  
  public void onCreate()
  {
    super.onCreate();
    initLogging();
    Object localObject = DaggerJaumoComponent.builder().jaumoModule(new JaumoModule(this)).build();
    Intrinsics.checkExpressionValueIsNotNull(localObject, "DaggerJaumoComponent.bui…\n                .build()");
    this.jaumoComponent = ((JaumoComponent)localObject);
    localObject = this.jaumoComponent;
    if (localObject == null) {
      Intrinsics.throwUninitializedPropertyAccessException("jaumoComponent");
    }
    ((JaumoComponent)localObject).inject(this);
    registerActivityLifecycleCallbacks((Application.ActivityLifecycleCallbacks)new AppLifecycleManager(2000L, (AppLifecycleManager.AppLifecycleListener)this.lifecycleListener));
    RestConfig.initialize(getApplicationContext());
    JodaTimeAndroid.init(getApplicationContext());
    localObject = getSystemService("activity");
    if (localObject != null)
    {
      localObject = (ActivityManager)localObject;
      ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
      ((ActivityManager)localObject).getMemoryInfo(localMemoryInfo);
      this.memoryClass = ((ActivityManager)localObject).getMemoryClass();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Heap size ");
      localStringBuilder.append(((ActivityManager)localObject).getMemoryClass());
      localStringBuilder.append(", large ");
      localStringBuilder.append(((ActivityManager)localObject).getLargeMemoryClass());
      localStringBuilder.append(" Avail ");
      localStringBuilder.append(localMemoryInfo.availMem / 1048576L);
      Timber.d(localStringBuilder.toString(), new Object[0]);
      this.deviceYear = YearClass.get(getApplicationContext());
      ConnectionClassManager.getInstance().register((ConnectionClassManager.ConnectionClassStateChangeListener)onCreate.1.INSTANCE);
      localObject = this.tracker;
      if (localObject == null) {
        Intrinsics.throwUninitializedPropertyAccessException("tracker");
      }
      ((Tracker)localObject).init(getApplicationContext());
    }
    try
    {
      if (getResources().getInteger(2131361801) <= 0) {
        break label290;
      }
      VKSdk.initialize(getApplicationContext());
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      for (;;) {}
    }
    Timber.e("VK SDK not initialized, app id not defined", new Object[0]);
    label290:
    Fresco.initialize(getApplicationContext(), ImagePipelineConfig.newBuilder(getApplicationContext()).setDownsampleEnabled(true).build());
    CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath(getString(2131820891)).setFontAttrId(2130968857).build());
    new GoogleAdConsent().subscribeForConsentChanges();
    return;
    throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
  }
  
  public void onLowMemory()
  {
    super.onLowMemory();
    onOutOfMemory();
  }
  
  public final void onOutOfMemory()
  {
    Timber.e("-------- CLEAR CACHE --------", new Object[0]);
    Cache localCache = this.cache;
    if (localCache == null) {
      Intrinsics.throwUninitializedPropertyAccessException("cache");
    }
    localCache.clear();
  }
  
  @Metadata(d1={"\000\"\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\013\n\002\b\002\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\b\020\005\032\0020\004H\007J\b\020\006\032\0020\007H\007J\b\020\b\032\0020\tH\007J\b\020\n\032\0020\tH\007R\016\020\003\032\0020\004X.¢\006\002\n\000¨\006\013"}, d2={"Lcom/jaumo/App$Companion;", "", "()V", "application", "Lcom/jaumo/App;", "get", "getContext", "Landroid/content/Context;", "isLowMemDevice", "", "isPlayStoreAvailable", "jaumo-android_pinkRelease"})
  public static final class Companion
  {
    private Companion() {}
    
    public final App get()
    {
      return App.access$getApplication$cp();
    }
    
    public final Context getContext()
    {
      Context localContext = App.access$getApplication$cp().getApplicationContext();
      Intrinsics.checkExpressionValueIsNotNull(localContext, "application.applicationContext");
      return localContext;
    }
    
    public final boolean isLowMemDevice()
    {
      return App.access$getMemoryClass$p(App.access$getApplication$cp()) < 48;
    }
    
    public final boolean isPlayStoreAvailable()
    {
      Object localObject = ((Companion)this).getContext().getPackageManager();
      try
      {
        localObject = ((PackageManager)localObject).getInstalledPackages(0).iterator();
        while (((Iterator)localObject).hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
          if (!Intrinsics.areEqual(localPackageInfo.packageName, "com.google.vending"))
          {
            boolean bool = Intrinsics.areEqual(localPackageInfo.packageName, "com.android.vending");
            if (!bool) {
              break;
            }
          }
          else
          {
            return true;
          }
        }
        return false;
      }
      catch (RuntimeException localRuntimeException) {}
      return false;
    }
  }
}
