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
import com.crashlytics.android.Crashlytics;
import com.facebook.device.yearclass.YearClass;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.core.ImagePipelineConfig.Builder;
import com.facebook.network.connectionclass.ConnectionClassManager;
import com.facebook.network.connectionclass.ConnectionClassManager.ConnectionClassStateChangeListener;
import com.facebook.network.connectionclass.ConnectionQuality;
import com.jaumo.boost.BoostApi;
import com.jaumo.classes.s;
import com.jaumo.fcm.FcmHelper;
import com.jaumo.location.b;
import com.jaumo.me.MeCache;
import com.jaumo.mqtt.MQTTLifecycle;
import com.jaumo.mqtt.d;
import com.jaumo.util.o;
import com.jaumo.v2.V2Cache;
import com.jaumo.v2.V2Loader;
import com.jaumo.zapping.ZappingCache;
import com.vk.sdk.VKSdk;
import io.fabric.sdk.android.Fabric;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import kotlin.TypeCastException;
import kotlin.jvm.internal.r;
import timber.log.Timber;
import timber.log.Timber.Tree;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig.Builder;

@kotlin.h(a={1, 1, 13}, b={"\000£\001\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\020\b\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\b\003\n\002\030\002\n\002\b\006\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\005\n\002\020\002\n\002\b\005*\001.\b\026\030\000 r2\0020\001:\001rB\005¢\006\002\020\002J\b\020m\032\0020nH\004J\b\020o\032\0020nH\026J\b\020p\032\0020nH\026J\006\020q\032\0020nR\036\020\003\032\0020\0048\000@\000X.¢\006\016\n\000\032\004\b\005\020\006\"\004\b\007\020\bR\036\020\t\032\0020\n8\000@\000X.¢\006\016\n\000\032\004\b\013\020\f\"\004\b\r\020\016R\036\020\017\032\0020\0208\000@\000X.¢\006\016\n\000\032\004\b\021\020\022\"\004\b\023\020\024R\032\020\025\032\0020\026X\016¢\006\016\n\000\032\004\b\027\020\030\"\004\b\031\020\032R\036\020\033\032\0020\0348\000@\000X.¢\006\016\n\000\032\004\b\035\020\036\"\004\b\037\020 R\036\020!\032\0020\"8\000@\000X.¢\006\016\n\000\032\004\b#\020$\"\004\b%\020&R\032\020'\032\0020(X.¢\006\016\n\000\032\004\b)\020*\"\004\b+\020,R\020\020-\032\0020.X\004¢\006\004\n\002\020/R\036\0200\032\002018\000@\000X.¢\006\016\n\000\032\004\b2\0203\"\004\b4\0205R\016\0206\032\0020\026X\016¢\006\002\n\000R\036\0207\032\002088\000@\000X.¢\006\016\n\000\032\004\b9\020:\"\004\b;\020<R\036\020=\032\0020>8\000@\000X.¢\006\016\n\000\032\004\b?\020@\"\004\bA\020BR\036\020C\032\0020D8\000@\000X.¢\006\016\n\000\032\004\bE\020F\"\004\bG\020HR\036\020I\032\0020J8\000@\000X.¢\006\016\n\000\032\004\bK\020L\"\004\bM\020NR\036\020O\032\0020P8\000@\000X.¢\006\016\n\000\032\004\bQ\020R\"\004\bS\020TR\036\020U\032\0020V8\000@\000X.¢\006\016\n\000\032\004\bW\020X\"\004\bY\020ZR\036\020[\032\0020\\8\000@\000X.¢\006\016\n\000\032\004\b]\020^\"\004\b_\020`R\036\020a\032\0020b8\000@\000X.¢\006\016\n\000\032\004\bc\020d\"\004\be\020fR\036\020g\032\0020h8\000@\000X.¢\006\016\n\000\032\004\bi\020j\"\004\bk\020l¨\006s"}, c={"Lcom/jaumo/App;", "Landroid/support/multidex/MultiDexApplication;", "()V", "authManager", "Lcom/jaumo/auth/AuthManager;", "getAuthManager$android_primeRelease", "()Lcom/jaumo/auth/AuthManager;", "setAuthManager$android_primeRelease", "(Lcom/jaumo/auth/AuthManager;)V", "boostApi", "Lcom/jaumo/boost/BoostApi;", "getBoostApi$android_primeRelease", "()Lcom/jaumo/boost/BoostApi;", "setBoostApi$android_primeRelease", "(Lcom/jaumo/boost/BoostApi;)V", "cache", "Lhelper/Cache;", "getCache$android_primeRelease", "()Lhelper/Cache;", "setCache$android_primeRelease", "(Lhelper/Cache;)V", "deviceYear", "", "getDeviceYear", "()I", "setDeviceYear", "(I)V", "fcmHelper", "Lcom/jaumo/fcm/FcmHelper;", "getFcmHelper$android_primeRelease", "()Lcom/jaumo/fcm/FcmHelper;", "setFcmHelper$android_primeRelease", "(Lcom/jaumo/fcm/FcmHelper;)V", "filterApi", "Lcom/jaumo/filter/FilterApi;", "getFilterApi$android_primeRelease", "()Lcom/jaumo/filter/FilterApi;", "setFilterApi$android_primeRelease", "(Lcom/jaumo/filter/FilterApi;)V", "jaumoComponent", "Lcom/jaumo/JaumoComponent;", "getJaumoComponent", "()Lcom/jaumo/JaumoComponent;", "setJaumoComponent", "(Lcom/jaumo/JaumoComponent;)V", "lifecycleListener", "com/jaumo/App$lifecycleListener$1", "Lcom/jaumo/App$lifecycleListener$1;", "meCache", "Lcom/jaumo/me/MeCache;", "getMeCache$android_primeRelease", "()Lcom/jaumo/me/MeCache;", "setMeCache$android_primeRelease", "(Lcom/jaumo/me/MeCache;)V", "memoryClass", "mqtt", "Lcom/jaumo/mqtt/MQTTConnection;", "getMqtt$android_primeRelease", "()Lcom/jaumo/mqtt/MQTTConnection;", "setMqtt$android_primeRelease", "(Lcom/jaumo/mqtt/MQTTConnection;)V", "mqttLifecycle", "Lcom/jaumo/mqtt/MQTTLifecycle;", "getMqttLifecycle$android_primeRelease", "()Lcom/jaumo/mqtt/MQTTLifecycle;", "setMqttLifecycle$android_primeRelease", "(Lcom/jaumo/mqtt/MQTTLifecycle;)V", "publisher", "Lcom/jaumo/classes/Publisher;", "getPublisher$android_primeRelease", "()Lcom/jaumo/classes/Publisher;", "setPublisher$android_primeRelease", "(Lcom/jaumo/classes/Publisher;)V", "pushinator", "Lcom/jaumo/pushinator/Pushinator;", "getPushinator$android_primeRelease", "()Lcom/jaumo/pushinator/Pushinator;", "setPushinator$android_primeRelease", "(Lcom/jaumo/pushinator/Pushinator;)V", "sessionManager", "Lcom/jaumo/sessionstate/SessionManager;", "getSessionManager$android_primeRelease", "()Lcom/jaumo/sessionstate/SessionManager;", "setSessionManager$android_primeRelease", "(Lcom/jaumo/sessionstate/SessionManager;)V", "tracker", "Lcom/jaumo/util/Tracker;", "getTracker$android_primeRelease", "()Lcom/jaumo/util/Tracker;", "setTracker$android_primeRelease", "(Lcom/jaumo/util/Tracker;)V", "v2", "Lcom/jaumo/v2/V2Loader;", "getV2$android_primeRelease", "()Lcom/jaumo/v2/V2Loader;", "setV2$android_primeRelease", "(Lcom/jaumo/v2/V2Loader;)V", "v2Cache", "Lcom/jaumo/v2/V2Cache;", "getV2Cache$android_primeRelease", "()Lcom/jaumo/v2/V2Cache;", "setV2Cache$android_primeRelease", "(Lcom/jaumo/v2/V2Cache;)V", "zappingCache", "Lcom/jaumo/zapping/ZappingCache;", "getZappingCache$android_primeRelease", "()Lcom/jaumo/zapping/ZappingCache;", "setZappingCache$android_primeRelease", "(Lcom/jaumo/zapping/ZappingCache;)V", "initLogging", "", "onCreate", "onLowMemory", "onOutOfMemory", "Companion", "android_primeRelease"})
public class App
  extends android.support.multidex.a
{
  public static final Companion q = new Companion(null);
  private static App u;
  public f a;
  @Inject
  public V2Loader b;
  @Inject
  public o c;
  @Inject
  public com.jaumo.c.e d;
  @Inject
  public s e;
  @Inject
  public MQTTLifecycle f;
  @Inject
  public com.jaumo.auth.a g;
  @Inject
  public com.jaumo.mqtt.a h;
  @Inject
  public com.jaumo.b.a i;
  @Inject
  public FcmHelper j;
  @Inject
  public BoostApi k;
  @Inject
  public com.jaumo.filter.a l;
  @Inject
  public helper.a m;
  @Inject
  public ZappingCache n;
  @Inject
  public MeCache o;
  @Inject
  public V2Cache p;
  private int r;
  private int s;
  private final lifecycleListener.1 t;
  
  public App()
  {
    u = (App)this;
    this.t = new AppLifecycleManager.AppLifecycleListener()
    {
      public void onAppPaused()
      {
        Timber.b("onAppPaused()", new Object[0]);
        this.this$0.c().c();
      }
      
      public void onAppResumed()
      {
        Timber.b("onAppResumed()", new Object[0]);
        if (this.this$0.f().d()) {
          this.this$0.i().a(this.this$0.getApplicationContext());
        }
        this.this$0.c().d();
      }
      
      public void onAppStarted()
      {
        Timber.b("onAppStarted()", new Object[0]);
        b.a(this.this$0.getApplicationContext());
        this.this$0.c().a((com.jaumo.c.f)new com.jaumo.c.a(this.this$0.getApplicationContext())).a((com.jaumo.c.f)this.this$0.d()).a((com.jaumo.c.f)new com.jaumo.classes.c(this.this$0.getApplicationContext())).a((com.jaumo.c.f)new com.jaumo.classes.a(this.this$0.getApplicationContext())).a((com.jaumo.c.f)this.this$0.e()).a((com.jaumo.c.f)b.a()).a((com.jaumo.c.f)this.this$0.f()).a((com.jaumo.c.f)new com.jaumo.c.c()).a((com.jaumo.c.f)new com.jaumo.c.g()).a((com.jaumo.c.f)this.this$0.j()).a((com.jaumo.c.f)this.this$0.k()).a((com.jaumo.c.f)this.this$0.h()).a((com.jaumo.c.f)this.this$0.l()).a((com.jaumo.c.f)this.this$0.m()).a((com.jaumo.c.f)this.this$0.n()).a((Application)App.q());
        this.this$0.g().a("pushinator", (d)this.this$0.h());
      }
      
      public void onAppStopped()
      {
        Timber.b("onAppStopped()", new Object[0]);
        this.this$0.c().b();
      }
    };
  }
  
  public static final App r()
  {
    return q.get();
  }
  
  public static final Context s()
  {
    return q.getContext();
  }
  
  public final int a()
  {
    return this.r;
  }
  
  public final f b()
  {
    f localF = this.a;
    if (localF == null) {
      r.b("jaumoComponent");
    }
    return localF;
  }
  
  public final com.jaumo.c.e c()
  {
    com.jaumo.c.e localE = this.d;
    if (localE == null) {
      r.b("sessionManager");
    }
    return localE;
  }
  
  public final s d()
  {
    s localS = this.e;
    if (localS == null) {
      r.b("publisher");
    }
    return localS;
  }
  
  public final MQTTLifecycle e()
  {
    MQTTLifecycle localMQTTLifecycle = this.f;
    if (localMQTTLifecycle == null) {
      r.b("mqttLifecycle");
    }
    return localMQTTLifecycle;
  }
  
  public final com.jaumo.auth.a f()
  {
    com.jaumo.auth.a localA = this.g;
    if (localA == null) {
      r.b("authManager");
    }
    return localA;
  }
  
  public final com.jaumo.mqtt.a g()
  {
    com.jaumo.mqtt.a localA = this.h;
    if (localA == null) {
      r.b("mqtt");
    }
    return localA;
  }
  
  public final com.jaumo.b.a h()
  {
    com.jaumo.b.a localA = this.i;
    if (localA == null) {
      r.b("pushinator");
    }
    return localA;
  }
  
  public final FcmHelper i()
  {
    FcmHelper localFcmHelper = this.j;
    if (localFcmHelper == null) {
      r.b("fcmHelper");
    }
    return localFcmHelper;
  }
  
  public final BoostApi j()
  {
    BoostApi localBoostApi = this.k;
    if (localBoostApi == null) {
      r.b("boostApi");
    }
    return localBoostApi;
  }
  
  public final com.jaumo.filter.a k()
  {
    com.jaumo.filter.a localA = this.l;
    if (localA == null) {
      r.b("filterApi");
    }
    return localA;
  }
  
  public final ZappingCache l()
  {
    ZappingCache localZappingCache = this.n;
    if (localZappingCache == null) {
      r.b("zappingCache");
    }
    return localZappingCache;
  }
  
  public final MeCache m()
  {
    MeCache localMeCache = this.o;
    if (localMeCache == null) {
      r.b("meCache");
    }
    return localMeCache;
  }
  
  public final V2Cache n()
  {
    V2Cache localV2Cache = this.p;
    if (localV2Cache == null) {
      r.b("v2Cache");
    }
    return localV2Cache;
  }
  
  protected final void o()
  {
    Fabric.a(getApplicationContext(), new io.fabric.sdk.android.e[] { (io.fabric.sdk.android.e)new Crashlytics() });
    Timber.a((Timber.Tree)new Timber.Tree()
    {
      protected void log(int paramAnonymousInt, String paramAnonymousString1, String paramAnonymousString2, Throwable paramAnonymousThrowable)
      {
        r.b(paramAnonymousString2, "message");
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
    o();
    Object localObject = DaggerJaumoComponent.a().jaumoModule(new g(this)).build();
    r.a(localObject, "DaggerJaumoComponent.bui…\n                .build()");
    this.a = ((f)localObject);
    localObject = this.a;
    if (localObject == null) {
      r.b("jaumoComponent");
    }
    ((f)localObject).a(this);
    registerActivityLifecycleCallbacks((Application.ActivityLifecycleCallbacks)new AppLifecycleManager(2000L, (AppLifecycleManager.AppLifecycleListener)this.t));
    com.jaumo.network.h.a(getApplicationContext());
    net.danlew.android.joda.a.a(getApplicationContext());
    localObject = getSystemService("activity");
    if (localObject != null)
    {
      localObject = (ActivityManager)localObject;
      ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
      ((ActivityManager)localObject).getMemoryInfo(localMemoryInfo);
      this.s = ((ActivityManager)localObject).getMemoryClass();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Heap size ");
      localStringBuilder.append(((ActivityManager)localObject).getMemoryClass());
      localStringBuilder.append(", large ");
      localStringBuilder.append(((ActivityManager)localObject).getLargeMemoryClass());
      localStringBuilder.append(" Avail ");
      localStringBuilder.append(localMemoryInfo.availMem / 1048576L);
      Timber.b(localStringBuilder.toString(), new Object[0]);
      this.r = YearClass.get(getApplicationContext());
      ConnectionClassManager.getInstance().register((ConnectionClassManager.ConnectionClassStateChangeListener)onCreate.1.INSTANCE);
      localObject = this.c;
      if (localObject == null) {
        r.b("tracker");
      }
      ((o)localObject).a(getApplicationContext());
    }
    try
    {
      if (getResources().getInteger(2131361801) <= 0) {
        break label291;
      }
      VKSdk.a(getApplicationContext());
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      for (;;) {}
    }
    Timber.e("VK SDK not initialized, app id not defined", new Object[0]);
    label291:
    Fresco.initialize(getApplicationContext(), ImagePipelineConfig.newBuilder(getApplicationContext()).setDownsampleEnabled(true).build());
    CalligraphyConfig.initDefault(new CalligraphyConfig.Builder().setDefaultFontPath(getString(2131820862)).setFontAttrId(2130968856).build());
    new com.jaumo.ads.a().a();
    com.jaumo.debug.c.a((Context)this);
    return;
    throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
  }
  
  public void onLowMemory()
  {
    super.onLowMemory();
    p();
  }
  
  public final void p()
  {
    Timber.e("-------- CLEAR CACHE --------", new Object[0]);
    helper.a localA = this.m;
    if (localA == null) {
      r.b("cache");
    }
    localA.a();
  }
  
  @kotlin.h(a={1, 1, 13}, b={"\000\"\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\013\n\002\b\002\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\b\020\005\032\0020\004H\007J\b\020\006\032\0020\007H\007J\b\020\b\032\0020\tH\007J\b\020\n\032\0020\tH\007R\016\020\003\032\0020\004X.¢\006\002\n\000¨\006\013"}, c={"Lcom/jaumo/App$Companion;", "", "()V", "application", "Lcom/jaumo/App;", "get", "getContext", "Landroid/content/Context;", "isLowMemDevice", "", "isPlayStoreAvailable", "android_primeRelease"})
  public static final class Companion
  {
    private Companion() {}
    
    public final App get()
    {
      return App.q();
    }
    
    public final Context getContext()
    {
      Context localContext = App.q().getApplicationContext();
      r.a(localContext, "application.applicationContext");
      return localContext;
    }
    
    public final boolean isLowMemDevice()
    {
      return App.a(App.q()) < 48;
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
          if (!r.a(localPackageInfo.packageName, "com.google.vending"))
          {
            boolean bool = r.a(localPackageInfo.packageName, "com.android.vending");
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
