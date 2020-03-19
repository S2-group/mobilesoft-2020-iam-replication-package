package com.onesignal;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
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
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ah
{
  private static int A;
  private static ag B;
  private static String C;
  private static boolean D;
  private static boolean E;
  private static boolean F;
  private static boolean G;
  private static m.e H;
  private static Collection<JSONArray> I = new ArrayList();
  private static HashSet<String> J = new HashSet();
  private static b K;
  private static boolean L;
  private static boolean M;
  private static boolean N;
  private static JSONObject O;
  private static ab P;
  private static z<aa, ac> Q;
  private static OSSubscriptionState R;
  private static z<ae, af> S;
  private static c T;
  private static int U = 0;
  static String a;
  static Context b;
  static boolean c;
  static ExecutorService d;
  public static ConcurrentLinkedQueue<Runnable> e;
  static AtomicLong f;
  public static String g;
  static boolean h;
  static a i;
  static boolean j;
  static ab k;
  static OSSubscriptionState l;
  private static String m;
  private static boolean n;
  private static e o = e.a;
  private static e p = e.d;
  private static String q = null;
  private static int r;
  private static boolean s;
  private static d t;
  private static long u;
  private static long v;
  private static aw w;
  private static au x;
  private static av y;
  private static d z;
  
  static
  {
    e = new ConcurrentLinkedQueue();
    f = new AtomicLong();
    u = 1L;
    v = -1L;
    z = new c();
    g = "native";
    h = true;
  }
  
  public ah() {}
  
  private static void I()
  {
    if (!e.isEmpty())
    {
      d = Executors.newSingleThreadExecutor(new ThreadFactory()
      {
        public Thread newThread(Runnable paramAnonymousRunnable)
        {
          paramAnonymousRunnable = new Thread(paramAnonymousRunnable);
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("OS_PENDING_EXECUTOR_");
          localStringBuilder.append(paramAnonymousRunnable.getId());
          paramAnonymousRunnable.setName(localStringBuilder.toString());
          return paramAnonymousRunnable;
        }
      });
      while (!e.isEmpty()) {
        d.submit((Runnable)e.poll());
      }
    }
  }
  
  private static boolean J()
  {
    if ((c) && (d == null)) {
      return false;
    }
    if ((!c) && (d == null)) {
      return true;
    }
    return (d != null) && (!d.isShutdown());
  }
  
  private static void K()
  {
    if (M) {
      return;
    }
    boolean bool2 = true;
    M = true;
    D = false;
    if (N) {
      E = false;
    }
    L();
    N();
    boolean bool1 = bool2;
    if (!G) {
      if (i.c) {
        bool1 = bool2;
      } else {
        bool1 = false;
      }
    }
    G = bool1;
  }
  
  private static void L()
  {
    Context localContext = b;
    boolean bool;
    if ((i.c) && (!G)) {
      bool = true;
    } else {
      bool = false;
    }
    m.a(localContext, bool, new m.c()
    {
      public void a(m.e paramAnonymousE)
      {
        ah.a(paramAnonymousE);
        ah.f(true);
        ah.q();
      }
    });
  }
  
  private static void M()
  {
    Object localObject;
    if (A == 2) {
      localObject = new aq();
    } else {
      localObject = new ar();
    }
    ((ap)localObject).a(b, m, new ap.a()
    {
      public void a(String paramAnonymousString, int paramAnonymousInt)
      {
        if (paramAnonymousInt < 1)
        {
          if ((an.e() == null) && ((ah.r() == 1) || (ah.r() < -6))) {
            ah.c(paramAnonymousInt);
          }
        }
        else if (ah.r() < -6) {
          ah.c(paramAnonymousInt);
        }
        ah.h(paramAnonymousString);
        ah.g(true);
        ah.e(ah.b).b(paramAnonymousString);
        ah.q();
      }
    });
  }
  
  private static void N()
  {
    if (F)
    {
      M();
      return;
    }
    am.a local16 = new am.a()
    {
      void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        new Thread(new Runnable()
        {
          public void run()
          {
            try
            {
              int j = ah.s() * 10000 + 30000;
              int i = j;
              if (j > 90000) {
                i = 90000;
              }
              ah.e localE = ah.e.e;
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("Failed to get Android parameters, trying again in ");
              localStringBuilder.append(i / 1000);
              localStringBuilder.append(" seconds.");
              ah.a(localE, localStringBuilder.toString());
              Thread.sleep(i);
            }
            catch (Throwable localThrowable)
            {
              for (;;) {}
            }
            ah.t();
            ah.u();
          }
        }, "OS_PARAMS_REQUEST").start();
      }
      
      void a(String paramAnonymousString)
      {
        try
        {
          paramAnonymousString = new JSONObject(paramAnonymousString);
          if (paramAnonymousString.has("android_sender_id"))
          {
            ah.h(true);
            ah.i(paramAnonymousString.getString("android_sender_id"));
          }
          ah.j = paramAnonymousString.optBoolean("enterp", false);
          ah.b(paramAnonymousString.getJSONObject("awl_list"));
          boolean bool = paramAnonymousString.optBoolean("fba", false);
          al.a(al.a, "GT_FIREBASE_TRACKING_ENABLED", bool);
          o.a(ah.b, paramAnonymousString);
        }
        catch (Throwable paramAnonymousString)
        {
          paramAnonymousString.printStackTrace();
        }
        ah.i(true);
        ah.v();
      }
    };
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("apps/");
    ((StringBuilder)localObject).append(a);
    ((StringBuilder)localObject).append("/android_params.js");
    String str1 = ((StringBuilder)localObject).toString();
    String str2 = g();
    localObject = str1;
    if (str2 != null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(str1);
      ((StringBuilder)localObject).append("?player_id=");
      ((StringBuilder)localObject).append(str2);
      localObject = ((StringBuilder)localObject).toString();
    }
    a(e.f, "Starting request to get Android parameters.");
    am.a((String)localObject, local16);
  }
  
  private static void O()
  {
    Iterator localIterator = I.iterator();
    while (localIterator.hasNext()) {
      b((JSONArray)localIterator.next(), true, false);
    }
    I.clear();
  }
  
  private static int P()
  {
    TimeZone localTimeZone = Calendar.getInstance().getTimeZone();
    int i2 = localTimeZone.getRawOffset();
    int i1 = i2;
    if (localTimeZone.inDaylightTime(new Date())) {
      i1 = i2 + localTimeZone.getDSTSavings();
    }
    return i1 / 1000;
  }
  
  private static void Q()
  {
    e localE = e.f;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("registerUser: registerForPushFired:");
    localStringBuilder.append(D);
    localStringBuilder.append(", locationFired: ");
    localStringBuilder.append(E);
    localStringBuilder.append(", awlFired: ");
    localStringBuilder.append(F);
    a(localE, localStringBuilder.toString());
    if ((D) && (E))
    {
      if (!F) {
        return;
      }
      new Thread(new Runnable()
      {
        public void run()
        {
          an.c localC = an.b();
          Object localObject2 = ah.b.getPackageName();
          Object localObject1 = ah.b.getPackageManager();
          localC.a("app_id", ah.a);
          localC.a("identifier", ah.w());
          Object localObject3 = ah.x().a(ah.b);
          if (localObject3 != null) {
            localC.a("ad_id", localObject3);
          }
          localC.a("device_os", Build.VERSION.RELEASE);
          localC.a("timezone", Integer.valueOf(ah.y()));
          localC.a("language", ag.d());
          localC.a("sdk", "030701");
          localC.a("sdk_type", ah.g);
          localC.a("android_package", localObject2);
          localC.a("device_model", Build.MODEL);
          localC.a("device_type", Integer.valueOf(ah.z()));
          localC.b("subscribableStatus", Integer.valueOf(ah.r()));
          localC.b("androidPermission", Boolean.valueOf(ah.p()));
          try
          {
            localC.a("game_version", Integer.valueOf(((PackageManager)localObject1).getPackageInfo((String)localObject2, 0).versionCode));
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
              if (ah.A().has(str)) {
                ((JSONArray)localObject2).put(str);
              }
            }
            else
            {
              localC.a("pkgs", localObject2);
              localC.a("net_type", ah.B().b());
              localC.a("carrier", ah.B().c());
              localC.a("rooted", Boolean.valueOf(at.a()));
              if ((ah.h) && (ah.C() != null)) {
                localC.a(ah.C());
              }
              an.a(localC, ah.D());
              ah.j(false);
              ai.a(ah.b, ah.a, ah.E(), c.a());
              return;
            }
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            for (;;) {}
          }
        }
      }, "OS_REG_USER").start();
      return;
    }
  }
  
  private static void R()
  {
    if (t != null) {
      ag.a(new Runnable()
      {
        public void run() {}
      });
    }
  }
  
  private static void S()
  {
    try
    {
      Object localObject1 = t;
      if (localObject1 == null) {
        return;
      }
      localObject1 = an.e();
      if (!an.d()) {
        localObject1 = null;
      }
      String str = g();
      if (str == null) {
        return;
      }
      t.a(str, (String)localObject1);
      if (localObject1 != null) {
        t = null;
      }
      return;
    }
    finally {}
  }
  
  private static boolean T()
  {
    return (System.currentTimeMillis() - m(b)) / 1000L >= 30L;
  }
  
  private static void U()
  {
    Intent localIntent = new Intent(b, SyncService.class);
    localIntent.putExtra("task", 0);
    b.startService(localIntent);
  }
  
  static z<aa, ac> a()
  {
    if (Q == null) {
      Q = new z("onOSPermissionChanged", true);
    }
    return Q;
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
        a(e.f, "Not a OneSignal formatted GCM message. No 'i' field in custom.");
        return null;
      }
      a(e.f, "Not a OneSignal formatted GCM message. No 'custom' field in the bundle.");
      return null;
    }
    catch (Throwable paramBundle)
    {
      a(e.f, "Could not parse bundle, probably not a OneSignal notification.", paramBundle);
    }
    return null;
  }
  
  public static void a(int paramInt)
  {
    a(e(paramInt));
  }
  
  public static void a(int paramInt1, int paramInt2)
  {
    a(d(paramInt1), d(paramInt2));
  }
  
  static void a(long paramLong)
  {
    al.a(al.a, "OS_LAST_SESSION_TIME", paramLong);
  }
  
  static void a(long paramLong, boolean paramBoolean)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("app_id", a);
      localJSONObject.put("type", 1);
      localJSONObject.put("state", "ping");
      localJSONObject.put("active_time", paramLong);
      c(localJSONObject);
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("players/");
      ((StringBuilder)localObject).append(g());
      ((StringBuilder)localObject).append("/on_focus");
      localObject = ((StringBuilder)localObject).toString();
      am.a local18 = new am.a()
      {
        void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
        {
          ah.a("sending on_focus Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
        }
        
        void a(String paramAnonymousString)
        {
          ah.c(0L);
        }
      };
      if (paramBoolean)
      {
        am.d((String)localObject, localJSONObject, local18);
        return;
      }
      am.b((String)localObject, localJSONObject, local18);
      return;
    }
    catch (Throwable localThrowable)
    {
      a(e.c, "Generating on_focus:JSON Failed.", localThrowable);
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, f paramF, g paramG)
  {
    i = c();
    i.g = false;
    i.a = paramF;
    i.b = paramG;
    if (!n) {
      m = paramString1;
    }
    B = new ag();
    A = B.a();
    r = B.a(paramContext, A, paramString2);
    if (r == 64537) {
      return;
    }
    if (c)
    {
      if (paramContext != null) {
        b = paramContext.getApplicationContext();
      }
      if (i.a != null) {
        O();
      }
      return;
    }
    boolean bool = paramContext instanceof Activity;
    s = bool;
    a = paramString2;
    b = paramContext.getApplicationContext();
    b(i.f);
    if (bool)
    {
      a.b = (Activity)paramContext;
      t.a(b);
      U();
    }
    else
    {
      a.a = true;
    }
    u = SystemClock.elapsedRealtime();
    an.a(b);
    ((Application)b).registerActivityLifecycleCallbacks(new b());
    try
    {
      Class.forName("com.amazon.device.iap.PurchasingListener");
      x = new au(b);
      paramContext = f();
      if (paramContext != null)
      {
        if (!paramContext.equals(a))
        {
          a(e.f, "APP ID changed, clearing user id as it is no longer valid.");
          j(a);
          an.f();
        }
      }
      else
      {
        f.a(0, b);
        j(a);
      }
      OSPermissionChangedInternalObserver.a(f(b));
      if ((s) || (g() == null))
      {
        N = T();
        a(System.currentTimeMillis());
        K();
      }
      if (i.a != null) {
        O();
      }
      if (aw.a(b)) {
        w = new aw(b);
      }
      if (av.a()) {
        y = new av(b);
      }
      c = true;
      I();
      return;
    }
    catch (ClassNotFoundException paramContext)
    {
      for (;;) {}
    }
  }
  
  public static void a(Context paramContext, JSONArray paramJSONArray, boolean paramBoolean)
  {
    b(paramContext, paramJSONArray);
    boolean bool2 = "DISABLE".equals(ag.a(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
    boolean bool1;
    if (!bool2) {
      bool1 = a(paramContext, paramJSONArray);
    } else {
      bool1 = false;
    }
    b(paramJSONArray, true, paramBoolean);
    if ((!paramBoolean) && (!bool1) && (!bool2)) {
      j(paramContext);
    }
  }
  
  public static void a(aa paramAa)
  {
    if (b == null)
    {
      a(e.c, "OneSignal.init has not been called. Could not add permission observer");
      return;
    }
    a().a(paramAa);
    if (f(b).a(g(b))) {
      OSPermissionChangedInternalObserver.b(f(b));
    }
  }
  
  public static void a(ae paramAe)
  {
    if (b == null)
    {
      a(e.c, "OneSignal.init has not been called. Could not add subscription observer");
      return;
    }
    b().a(paramAe);
    if (h(b).a(i(b))) {
      OSSubscriptionChangedInternalObserver.a(h(b));
    }
  }
  
  public static void a(b paramB)
  {
    K = paramB;
    paramB = new Runnable()
    {
      public void run()
      {
        if (this.a == null)
        {
          ah.a(ah.e.c, "getTagsHandler is null!");
          return;
        }
        if (ah.g() == null) {
          return;
        }
        ah.b(ah.F());
      }
    };
    if (b == null)
    {
      a(e.c, "You must initialize OneSignal before getting tags! Moving this tag operation to a pending queue.");
      e.add(paramB);
      return;
    }
    paramB.run();
  }
  
  public static void a(d paramD)
  {
    t = paramD;
    paramD = new Runnable()
    {
      public void run()
      {
        if (ah.g() != null) {
          ag.a(new Runnable()
          {
            public void run() {}
          });
        }
      }
    };
    if ((b != null) && (!J()))
    {
      paramD.run();
      return;
    }
    a(e.c, "You must initialize OneSignal before getting tags! Moving this tag operation to a pending queue.");
    a(new i(paramD));
  }
  
  public static void a(e paramE1, e paramE2)
  {
    p = paramE1;
    o = paramE2;
  }
  
  static void a(e paramE, String paramString)
  {
    a(paramE, paramString, null);
  }
  
  static void a(e paramE, final String paramString, Throwable paramThrowable)
  {
    if (paramE.compareTo(p) < 1) {
      if (paramE == e.g) {
        Log.v("OneSignal", paramString, paramThrowable);
      } else if (paramE == e.f) {
        Log.d("OneSignal", paramString, paramThrowable);
      } else if (paramE == e.e) {
        Log.i("OneSignal", paramString, paramThrowable);
      } else if (paramE == e.d) {
        Log.w("OneSignal", paramString, paramThrowable);
      } else if ((paramE == e.c) || (paramE == e.b)) {
        Log.e("OneSignal", paramString, paramThrowable);
      }
    }
    if ((paramE.compareTo(o) < 1) && (a.b != null)) {
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
        ag.a(new Runnable()
        {
          public void run()
          {
            if (a.b != null) {
              new AlertDialog.Builder(a.b).setTitle(this.a.toString()).setMessage(paramString).show();
            }
          }
        });
        return;
      }
      catch (Throwable paramE)
      {
        Log.e("OneSignal", "Error showing logging message.", paramE);
      }
    }
  }
  
  public static void a(h paramH)
  {
    c().g = true;
    c().h = paramH;
  }
  
  private static void a(i paramI)
  {
    i.a(paramI, f.incrementAndGet());
    e localE;
    StringBuilder localStringBuilder;
    if (d == null)
    {
      localE = e.e;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Adding a task to the pending queue with ID: ");
      localStringBuilder.append(i.a(paramI));
      a(localE, localStringBuilder.toString());
      e.add(paramI);
      return;
    }
    if (!d.isShutdown())
    {
      localE = e.e;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Executor is still running, add to the executor with ID: ");
      localStringBuilder.append(i.a(paramI));
      a(localE, localStringBuilder.toString());
      d.submit(paramI);
    }
  }
  
  private static void a(x paramX)
  {
    ag.a(new Runnable()
    {
      public void run()
      {
        ah.i.a.notificationOpened(this.a);
      }
    });
    if ((y != null) && (b(b))) {
      y.a(paramX);
    }
  }
  
  public static void a(String paramString)
  {
    paramString = new Runnable()
    {
      public void run()
      {
        if (ag.a(this.a)) {
          an.a(this.a.trim().toLowerCase());
        }
      }
    };
    if ((b != null) && (!J()))
    {
      paramString.run();
      return;
    }
    a(e.c, "You should initialize OneSignal before calling syncHashedEmail! Moving this operation to a pending task queue.");
    a(new i(paramString));
  }
  
  public static void a(String paramString, j paramJ)
  {
    try
    {
      a(new JSONObject(paramString), paramJ);
      return;
    }
    catch (JSONException paramJ)
    {
      StringBuilder localStringBuilder;
      for (;;) {}
    }
    paramJ = e.c;
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("Invalid postNotification JSON format: ");
    localStringBuilder.append(paramString);
    a(paramJ, localStringBuilder.toString());
  }
  
  public static void a(String paramString1, String paramString2)
  {
    try
    {
      a(new JSONObject().put(paramString1, paramString2));
      return;
    }
    catch (JSONException paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static void a(Collection<String> paramCollection)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext()) {
        localJSONObject.put((String)paramCollection.next(), "");
      }
      a(localJSONObject);
      return;
    }
    catch (Throwable paramCollection)
    {
      a(e.c, "Failed to generate JSON for deleteTags.", paramCollection);
    }
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean, am.a paramA)
  {
    if (g() == null)
    {
      T = new c(paramJSONArray);
      T.b = paramBoolean;
      T.c = paramA;
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
      paramJSONArray.append(g());
      paramJSONArray.append("/on_purchase");
      am.b(paramJSONArray.toString(), localJSONObject, paramA);
      return;
    }
    catch (Throwable paramJSONArray)
    {
      a(e.c, "Failed to generate JSON for sendPurchases.", paramJSONArray);
    }
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (i != null)
    {
      if (i.b == null) {
        return;
      }
      paramJSONArray = c(paramJSONArray, paramBoolean1, paramBoolean2);
      i.b.notificationReceived(paramJSONArray.a);
      if ((y != null) && (b(b))) {
        y.b(paramJSONArray);
      }
      return;
    }
  }
  
  public static void a(JSONObject paramJSONObject)
  {
    paramJSONObject = new Runnable()
    {
      public void run()
      {
        if (this.a == null) {
          return;
        }
        JSONObject localJSONObject1 = an.d(false).b;
        JSONObject localJSONObject2 = new JSONObject();
        Iterator localIterator = this.a.keys();
        for (;;)
        {
          String str;
          if (localIterator.hasNext()) {
            str = (String)localIterator.next();
          }
          try
          {
            Object localObject = this.a.opt(str);
            if ((!(localObject instanceof JSONArray)) && (!(localObject instanceof JSONObject)))
            {
              if ((!this.a.isNull(str)) && (!"".equals(localObject)))
              {
                localJSONObject2.put(str, localObject.toString());
                continue;
              }
              if ((localJSONObject1 == null) || (!localJSONObject1.has(str))) {
                continue;
              }
              localJSONObject2.put(str, "");
              continue;
            }
            localObject = ah.e.c;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("Omitting key '");
            localStringBuilder.append(str);
            localStringBuilder.append("'! sendTags DO NOT supported nested values!");
            ah.a((ah.e)localObject, localStringBuilder.toString());
          }
          catch (Throwable localThrowable) {}
          if (!localJSONObject2.toString().equals("{}")) {
            an.a(localJSONObject2);
          }
          return;
        }
      }
    };
    if ((b != null) && (!J()))
    {
      paramJSONObject.run();
      return;
    }
    a(e.c, "You must initialize OneSignal before modifying tags!Moving this operation to a pending task queue.");
    a(new i(paramJSONObject));
  }
  
  public static void a(JSONObject paramJSONObject, j paramJ)
  {
    try
    {
      if (!paramJSONObject.has("app_id")) {
        paramJSONObject.put("app_id", f());
      }
      am.b("notifications/", paramJSONObject, new am.a()
      {
        void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
        {
          ah.a("create notification failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
          if (this.a != null)
          {
            if (paramAnonymousInt == 0) {
              paramAnonymousString = "{\"error\": \"HTTP no response error\"}";
            }
            for (;;)
            {
              try
              {
                this.a.b(new JSONObject(paramAnonymousString));
                return;
              }
              catch (Throwable paramAnonymousString)
              {
                continue;
              }
              try
              {
                this.a.b(new JSONObject("{\"error\": \"Unknown response!\"}"));
                return;
              }
              catch (JSONException paramAnonymousString)
              {
                paramAnonymousString.printStackTrace();
              }
            }
          }
        }
        
        public void a(String paramAnonymousString)
        {
          ah.e localE = ah.e.f;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("HTTP create notification success: ");
          Object localObject;
          if (paramAnonymousString != null) {
            localObject = paramAnonymousString;
          } else {
            localObject = "null";
          }
          localStringBuilder.append((String)localObject);
          ah.a(localE, localStringBuilder.toString());
          if (this.a != null) {
            try
            {
              localObject = new JSONObject(paramAnonymousString);
              if (((JSONObject)localObject).has("errors"))
              {
                this.a.b((JSONObject)localObject);
                return;
              }
              this.a.a(new JSONObject(paramAnonymousString));
              return;
            }
            catch (Throwable paramAnonymousString)
            {
              paramAnonymousString.printStackTrace();
            }
          }
        }
      });
      return;
    }
    catch (JSONException paramJSONObject)
    {
      a(e.c, "HTTP create notification json exception!", paramJSONObject);
      if (paramJ != null) {
        try
        {
          paramJ.b(new JSONObject("{'error': 'HTTP create notification json exception!'}"));
          return;
        }
        catch (JSONException paramJSONObject)
        {
          paramJSONObject.printStackTrace();
        }
      }
    }
  }
  
  static boolean a(Context paramContext)
  {
    return al.b(al.a, "OS_FILTER_OTHER_GCM_RECEIVERS", false);
  }
  
  private static boolean a(Context paramContext, JSONArray paramJSONArray)
  {
    int i2 = paramJSONArray.length();
    int i1 = 0;
    boolean bool2;
    for (boolean bool1 = false; i1 < i2; bool1 = bool2)
    {
      try
      {
        Object localObject1 = paramJSONArray.getJSONObject(i1);
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
        Object localObject2 = e.c;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Error parsing JSON item ");
        localStringBuilder.append(i1);
        localStringBuilder.append("/");
        localStringBuilder.append(i2);
        localStringBuilder.append(" for launching a web URL.");
        a((e)localObject2, localStringBuilder.toString(), localThrowable);
        bool2 = bool1;
      }
      i1 += 1;
    }
    return bool1;
  }
  
  static boolean a(Context paramContext, JSONObject paramJSONObject)
  {
    paramJSONObject = d(paramJSONObject);
    return (paramJSONObject == null) || (a(paramJSONObject, paramContext));
  }
  
  private static boolean a(e paramE)
  {
    int i1 = paramE.compareTo(o);
    boolean bool = true;
    if (i1 >= 1)
    {
      if (paramE.compareTo(p) < 1) {
        return true;
      }
      bool = false;
    }
    return bool;
  }
  
  /* Error */
  private static boolean a(String paramString, Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +198 -> 199
    //   4: ldc_w 856
    //   7: aload_0
    //   8: invokevirtual 647	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   11: ifeq +5 -> 16
    //   14: iconst_0
    //   15: ireturn
    //   16: aload_1
    //   17: invokestatic 978	com/onesignal/aj:a	(Landroid/content/Context;)Lcom/onesignal/aj;
    //   20: astore 4
    //   22: aconst_null
    //   23: astore 6
    //   25: aconst_null
    //   26: astore_1
    //   27: aload 4
    //   29: invokevirtual 981	com/onesignal/aj:b	()Landroid/database/sqlite/SQLiteDatabase;
    //   32: ldc_w 983
    //   35: iconst_1
    //   36: anewarray 643	java/lang/String
    //   39: dup
    //   40: iconst_0
    //   41: ldc_w 985
    //   44: aastore
    //   45: ldc_w 987
    //   48: iconst_1
    //   49: anewarray 643	java/lang/String
    //   52: dup
    //   53: iconst_0
    //   54: aload_0
    //   55: aastore
    //   56: aconst_null
    //   57: aconst_null
    //   58: aconst_null
    //   59: invokevirtual 993	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   62: astore 4
    //   64: aload 4
    //   66: invokeinterface 998 1 0
    //   71: istore_3
    //   72: iload_3
    //   73: istore_2
    //   74: aload 4
    //   76: ifnull +65 -> 141
    //   79: aload 4
    //   81: invokeinterface 1001 1 0
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
    //   116: getstatic 555	com/onesignal/ah$e:c	Lcom/onesignal/ah$e;
    //   119: ldc_w 1003
    //   122: aload 5
    //   124: invokestatic 495	com/onesignal/ah:a	(Lcom/onesignal/ah$e;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   127: aload 4
    //   129: ifnull +10 -> 139
    //   132: aload 4
    //   134: invokeinterface 1001 1 0
    //   139: iconst_0
    //   140: istore_2
    //   141: iload_2
    //   142: ifeq +43 -> 185
    //   145: getstatic 317	com/onesignal/ah$e:f	Lcom/onesignal/ah$e;
    //   148: astore_1
    //   149: new 297	java/lang/StringBuilder
    //   152: dup
    //   153: invokespecial 298	java/lang/StringBuilder:<init>	()V
    //   156: astore 4
    //   158: aload 4
    //   160: ldc_w 1005
    //   163: invokevirtual 304	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: pop
    //   167: aload 4
    //   169: aload_0
    //   170: invokevirtual 304	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: pop
    //   174: aload_1
    //   175: aload 4
    //   177: invokevirtual 311	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   180: invokestatic 322	com/onesignal/ah:a	(Lcom/onesignal/ah$e;Ljava/lang/String;)V
    //   183: iconst_1
    //   184: ireturn
    //   185: iconst_0
    //   186: ireturn
    //   187: aload_1
    //   188: ifnull +9 -> 197
    //   191: aload_1
    //   192: invokeinterface 1001 1 0
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
  
  static boolean a(boolean paramBoolean)
  {
    boolean bool = false;
    s = false;
    if (!c) {
      return false;
    }
    if (x != null) {
      x.a();
    }
    if (u == -1L) {
      return false;
    }
    double d1 = SystemClock.elapsedRealtime() - u;
    Double.isNaN(d1);
    long l1 = (d1 / 1000.0D + 0.5D);
    u = SystemClock.elapsedRealtime();
    if (l1 >= 0L)
    {
      if (l1 > 86400L) {
        return false;
      }
      if (b == null)
      {
        a(e.c, "Android Context not found, please call OneSignal.init when your app starts.");
        return false;
      }
      a(System.currentTimeMillis());
      l1 = m() + l1;
      if ((!paramBoolean) && (l1 >= 60L) && (g() != null))
      {
        a(l1, true);
        return false;
      }
      e(l1);
      paramBoolean = bool;
      if (l1 >= 60L) {
        paramBoolean = true;
      }
      return paramBoolean;
    }
    return false;
  }
  
  static z<ae, af> b()
  {
    if (S == null) {
      S = new z("onOSSubscriptionChanged", true);
    }
    return S;
  }
  
  public static void b(int paramInt)
  {
    Runnable local13 = new Runnable()
    {
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: getstatic 26	com/onesignal/ah:b	Landroid/content/Context;
        //   3: invokestatic 31	com/onesignal/aj:a	(Landroid/content/Context;)Lcom/onesignal/aj;
        //   6: astore_1
        //   7: aload_1
        //   8: invokevirtual 34	com/onesignal/aj:a	()Landroid/database/sqlite/SQLiteDatabase;
        //   11: astore_2
        //   12: aload_2
        //   13: astore_1
        //   14: aload_2
        //   15: invokevirtual 39	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
        //   18: aload_2
        //   19: astore_1
        //   20: new 41	java/lang/StringBuilder
        //   23: dup
        //   24: invokespecial 42	java/lang/StringBuilder:<init>	()V
        //   27: astore_3
        //   28: aload_2
        //   29: astore_1
        //   30: aload_3
        //   31: ldc 44
        //   33: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   36: pop
        //   37: aload_2
        //   38: astore_1
        //   39: aload_3
        //   40: aload_0
        //   41: getfield 16	com/onesignal/ah$13:a	I
        //   44: invokevirtual 51	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
        //   47: pop
        //   48: aload_2
        //   49: astore_1
        //   50: aload_3
        //   51: ldc 53
        //   53: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   56: pop
        //   57: aload_2
        //   58: astore_1
        //   59: aload_3
        //   60: ldc 55
        //   62: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   65: pop
        //   66: aload_2
        //   67: astore_1
        //   68: aload_3
        //   69: ldc 57
        //   71: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   74: pop
        //   75: aload_2
        //   76: astore_1
        //   77: aload_3
        //   78: ldc 59
        //   80: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   83: pop
        //   84: aload_2
        //   85: astore_1
        //   86: aload_3
        //   87: ldc 61
        //   89: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   92: pop
        //   93: aload_2
        //   94: astore_1
        //   95: aload_3
        //   96: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   99: astore_3
        //   100: aload_2
        //   101: astore_1
        //   102: new 67	android/content/ContentValues
        //   105: dup
        //   106: invokespecial 68	android/content/ContentValues:<init>	()V
        //   109: astore 4
        //   111: aload_2
        //   112: astore_1
        //   113: aload 4
        //   115: ldc 59
        //   117: iconst_1
        //   118: invokestatic 74	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   121: invokevirtual 78	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
        //   124: aload_2
        //   125: astore_1
        //   126: aload_2
        //   127: ldc 80
        //   129: aload 4
        //   131: aload_3
        //   132: aconst_null
        //   133: invokevirtual 84	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
        //   136: ifle +16 -> 152
        //   139: aload_2
        //   140: astore_1
        //   141: getstatic 26	com/onesignal/ah:b	Landroid/content/Context;
        //   144: aload_2
        //   145: aload_0
        //   146: getfield 16	com/onesignal/ah$13:a	I
        //   149: invokestatic 89	com/onesignal/u:a	(Landroid/content/Context;Landroid/database/sqlite/SQLiteDatabase;I)V
        //   152: aload_2
        //   153: astore_1
        //   154: aload_2
        //   155: getstatic 26	com/onesignal/ah:b	Landroid/content/Context;
        //   158: invokestatic 94	com/onesignal/f:a	(Landroid/database/sqlite/SQLiteDatabase;Landroid/content/Context;)V
        //   161: aload_2
        //   162: astore_1
        //   163: aload_2
        //   164: invokevirtual 97	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
        //   167: aload_2
        //   168: ifnull +107 -> 275
        //   171: aload_2
        //   172: invokevirtual 100	android/database/sqlite/SQLiteDatabase:endTransaction	()V
        //   175: return
        //   176: astore_2
        //   177: goto +99 -> 276
        //   180: astore_3
        //   181: goto +12 -> 193
        //   184: astore_2
        //   185: aconst_null
        //   186: astore_1
        //   187: goto +89 -> 276
        //   190: astore_3
        //   191: aconst_null
        //   192: astore_2
        //   193: aload_2
        //   194: astore_1
        //   195: getstatic 106	com/onesignal/ah$e:c	Lcom/onesignal/ah$e;
        //   198: astore 4
        //   200: aload_2
        //   201: astore_1
        //   202: new 41	java/lang/StringBuilder
        //   205: dup
        //   206: invokespecial 42	java/lang/StringBuilder:<init>	()V
        //   209: astore 5
        //   211: aload_2
        //   212: astore_1
        //   213: aload 5
        //   215: ldc 108
        //   217: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   220: pop
        //   221: aload_2
        //   222: astore_1
        //   223: aload 5
        //   225: aload_0
        //   226: getfield 16	com/onesignal/ah$13:a	I
        //   229: invokevirtual 51	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
        //   232: pop
        //   233: aload_2
        //   234: astore_1
        //   235: aload 5
        //   237: ldc 110
        //   239: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   242: pop
        //   243: aload_2
        //   244: astore_1
        //   245: aload 4
        //   247: aload 5
        //   249: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   252: aload_3
        //   253: invokestatic 113	com/onesignal/ah:a	(Lcom/onesignal/ah$e;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   256: aload_2
        //   257: ifnull +18 -> 275
        //   260: aload_2
        //   261: invokevirtual 100	android/database/sqlite/SQLiteDatabase:endTransaction	()V
        //   264: return
        //   265: astore_1
        //   266: getstatic 106	com/onesignal/ah$e:c	Lcom/onesignal/ah$e;
        //   269: ldc 115
        //   271: aload_1
        //   272: invokestatic 113	com/onesignal/ah:a	(Lcom/onesignal/ah$e;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   275: return
        //   276: aload_1
        //   277: ifnull +20 -> 297
        //   280: aload_1
        //   281: invokevirtual 100	android/database/sqlite/SQLiteDatabase:endTransaction	()V
        //   284: goto +13 -> 297
        //   287: astore_1
        //   288: getstatic 106	com/onesignal/ah$e:c	Lcom/onesignal/ah$e;
        //   291: ldc 115
        //   293: aload_1
        //   294: invokestatic 113	com/onesignal/ah:a	(Lcom/onesignal/ah$e;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   297: aload_2
        //   298: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	299	0	this	13
        //   6	239	1	localObject1	Object
        //   265	16	1	localThrowable1	Throwable
        //   287	7	1	localThrowable2	Throwable
        //   11	161	2	localSQLiteDatabase	android.database.sqlite.SQLiteDatabase
        //   176	1	2	localObject2	Object
        //   184	1	2	localObject3	Object
        //   192	106	2	localObject4	Object
        //   27	105	3	localObject5	Object
        //   180	1	3	localThrowable3	Throwable
        //   190	63	3	localThrowable4	Throwable
        //   109	137	4	localObject6	Object
        //   209	39	5	localStringBuilder	StringBuilder
        // Exception table:
        //   from	to	target	type
        //   14	18	176	finally
        //   20	28	176	finally
        //   30	37	176	finally
        //   39	48	176	finally
        //   50	57	176	finally
        //   59	66	176	finally
        //   68	75	176	finally
        //   77	84	176	finally
        //   86	93	176	finally
        //   95	100	176	finally
        //   102	111	176	finally
        //   113	124	176	finally
        //   126	139	176	finally
        //   141	152	176	finally
        //   154	161	176	finally
        //   163	167	176	finally
        //   195	200	176	finally
        //   202	211	176	finally
        //   213	221	176	finally
        //   223	233	176	finally
        //   235	243	176	finally
        //   245	256	176	finally
        //   14	18	180	java/lang/Throwable
        //   20	28	180	java/lang/Throwable
        //   30	37	180	java/lang/Throwable
        //   39	48	180	java/lang/Throwable
        //   50	57	180	java/lang/Throwable
        //   59	66	180	java/lang/Throwable
        //   68	75	180	java/lang/Throwable
        //   77	84	180	java/lang/Throwable
        //   86	93	180	java/lang/Throwable
        //   95	100	180	java/lang/Throwable
        //   102	111	180	java/lang/Throwable
        //   113	124	180	java/lang/Throwable
        //   126	139	180	java/lang/Throwable
        //   141	152	180	java/lang/Throwable
        //   154	161	180	java/lang/Throwable
        //   163	167	180	java/lang/Throwable
        //   7	12	184	finally
        //   7	12	190	java/lang/Throwable
        //   171	175	265	java/lang/Throwable
        //   260	264	265	java/lang/Throwable
        //   280	284	287	java/lang/Throwable
      }
    };
    if ((b != null) && (!J()))
    {
      local13.run();
      ((NotificationManager)b.getSystemService("notification")).cancel(paramInt);
      return;
    }
    e localE = e.c;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("OneSignal.init has not been called. Could not clear notification id: ");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" at this time - movingthis operation to a waiting task queue. The notification will still be canceledfrom NotificationManager at this time.");
    a(localE, localStringBuilder.toString());
    e.add(local13);
  }
  
  private static void b(Context paramContext, JSONArray paramJSONArray)
  {
    int i1 = 0;
    while (i1 < paramJSONArray.length())
    {
      try
      {
        String str = new JSONObject(paramJSONArray.getJSONObject(i1).optString("custom", null)).optString("i", null);
        if (!J.contains(str))
        {
          J.add(str);
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("app_id", k(paramContext));
          localJSONObject.put("player_id", l(paramContext));
          localJSONObject.put("opened", true);
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("notifications/");
          localStringBuilder.append(str);
          am.a(localStringBuilder.toString(), localJSONObject, new am.a()
          {
            void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
            {
              ah.a("sending Notification Opened Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
            }
          });
        }
      }
      catch (Throwable localThrowable)
      {
        a(e.c, "Failed to generate JSON to send notification opened.", localThrowable);
      }
      i1 += 1;
    }
  }
  
  public static void b(aa paramAa)
  {
    if (b == null)
    {
      a(e.c, "OneSignal.init has not been called. Could not modify permission observer");
      return;
    }
    a().c(paramAa);
  }
  
  public static void b(ae paramAe)
  {
    if (b == null)
    {
      a(e.c, "OneSignal.init has not been called. Could not modify subscription observer");
      return;
    }
    b().c(paramAe);
  }
  
  public static void b(String paramString)
  {
    try
    {
      a(new JSONObject(paramString));
      return;
    }
    catch (JSONException paramString)
    {
      a(e.c, "Generating JSONObject for sendTags failed!", paramString);
    }
  }
  
  private static void b(String paramString1, int paramInt, Throwable paramThrowable, String paramString2)
  {
    Object localObject2 = "";
    Object localObject1 = localObject2;
    if (paramString2 != null)
    {
      localObject1 = localObject2;
      if (a(e.e))
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("\n");
        ((StringBuilder)localObject1).append(paramString2);
        ((StringBuilder)localObject1).append("\n");
        localObject1 = ((StringBuilder)localObject1).toString();
      }
    }
    paramString2 = e.d;
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("HTTP code: ");
    ((StringBuilder)localObject2).append(paramInt);
    ((StringBuilder)localObject2).append(" ");
    ((StringBuilder)localObject2).append(paramString1);
    ((StringBuilder)localObject2).append((String)localObject1);
    a(paramString2, ((StringBuilder)localObject2).toString(), paramThrowable);
  }
  
  private static void b(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((i != null) && (i.a != null))
    {
      a(c(paramJSONArray, paramBoolean1, paramBoolean2));
      return;
    }
    I.add(paramJSONArray);
  }
  
  static void b(boolean paramBoolean)
  {
    if (b == null) {
      return;
    }
    al.a(al.a, "OS_FILTER_OTHER_GCM_RECEIVERS", paramBoolean);
  }
  
  static boolean b(Context paramContext)
  {
    return al.b(al.a, "GT_FIREBASE_TRACKING_ENABLED", false);
  }
  
  public static a c()
  {
    if (i == null) {
      i = new a(null);
    }
    return i;
  }
  
  private static x c(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i3 = paramJSONArray.length();
    x localX = new x();
    v localV = new v();
    localV.a = n();
    localV.b = paramBoolean1;
    localV.c = paramJSONArray.optJSONObject(0).optInt("notificationId");
    Object localObject1 = null;
    int i1 = 0;
    int i2 = 1;
    Object localObject4;
    Object localObject3;
    if (i1 < i3)
    {
      localObject4 = localObject1;
      try
      {
        localObject5 = paramJSONArray.getJSONObject(i1);
        localObject4 = localObject1;
        localV.d = n.a((JSONObject)localObject5);
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
        localObject3 = e.c;
        Object localObject5 = new StringBuilder();
        ((StringBuilder)localObject5).append("Error parsing JSON item ");
        ((StringBuilder)localObject5).append(i1);
        ((StringBuilder)localObject5).append("/");
        ((StringBuilder)localObject5).append(i3);
        ((StringBuilder)localObject5).append(" for callback.");
        a((e)localObject3, ((StringBuilder)localObject5).toString(), localThrowable);
        localObject2 = localObject4;
      }
      localObject4 = localObject3;
      if (localV.f == null)
      {
        localObject4 = localObject3;
        localV.f = new ArrayList();
      }
      localObject4 = localObject3;
      localV.f.add(localV.d);
      localObject1 = localObject3;
    }
    for (;;)
    {
      i1 += 1;
      break;
      localX.a = localV;
      localX.b = new w();
      localX.b.b = localObject2;
      localObject3 = localX.b;
      if (localObject2 != null) {
        paramJSONArray = w.a.b;
      } else {
        paramJSONArray = w.a.a;
      }
      ((w)localObject3).a = paramJSONArray;
      if (paramBoolean2)
      {
        localX.a.e = v.a.b;
        return localX;
      }
      localX.a.e = v.a.a;
      return localX;
      label365:
      if (i2 == 0) {
        break label139;
      }
      i2 = 0;
      Object localObject2 = localObject3;
    }
  }
  
  private static void c(b paramB)
  {
    if (paramB == null) {
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        an.a localA = an.d(ah.G() ^ true);
        if (localA.a) {
          ah.k(true);
        }
        if ((localA.b != null) && (!localA.toString().equals("{}")))
        {
          this.a.a(localA.b);
          return;
        }
        this.a.a(null);
      }
    }, "OS_GETTAGS_CALLBACK").start();
  }
  
  public static void c(String paramString)
  {
    ArrayList localArrayList = new ArrayList(1);
    localArrayList.add(paramString);
    a(localArrayList);
  }
  
  private static void c(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject.put("net_type", B.b());
      return;
    }
    catch (Throwable paramJSONObject) {}
  }
  
  public static void c(boolean paramBoolean)
  {
    if (b == null) {
      return;
    }
    al.a(al.a, "GT_VIBRATE_ENABLED", paramBoolean);
  }
  
  static boolean c(Context paramContext)
  {
    return al.b(al.a, "GT_VIBRATE_ENABLED", true);
  }
  
  private static e d(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      if (paramInt < 0) {
        return e.a;
      }
      break;
    case 6: 
      return e.g;
    case 5: 
      return e.f;
    case 4: 
      return e.e;
    case 3: 
      return e.d;
    case 2: 
      return e.c;
    case 1: 
      return e.b;
    case 0: 
      return e.a;
    }
    return e.g;
  }
  
  private static String d(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject = new JSONObject(paramJSONObject.optString("custom")).optString("i", null);
      return paramJSONObject;
    }
    catch (Throwable paramJSONObject) {}
    return null;
  }
  
  static void d()
  {
    U();
    s = true;
    u = SystemClock.elapsedRealtime();
    N = T();
    a(System.currentTimeMillis());
    K();
    if (w != null) {
      w.a();
    }
    t.a(b);
    f(b).a();
    if ((y != null) && (b(b))) {
      y.b();
    }
  }
  
  private static void d(long paramLong)
  {
    if (f.get() == paramLong)
    {
      a(e.e, "Last Pending Task has ran, shutting down");
      d.shutdown();
    }
  }
  
  public static void d(String paramString)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      paramString = new JSONArray(paramString);
      int i1 = 0;
      while (i1 < paramString.length())
      {
        localJSONObject.put(paramString.getString(i1), "");
        i1 += 1;
      }
      a(localJSONObject);
      return;
    }
    catch (Throwable paramString)
    {
      a(e.c, "Failed to generate JSON for deleteTags.", paramString);
    }
  }
  
  public static void d(boolean paramBoolean)
  {
    if (b == null) {
      return;
    }
    al.a(al.a, "GT_SOUND_ENABLED", paramBoolean);
  }
  
  static boolean d(Context paramContext)
  {
    return al.b(al.a, "GT_SOUND_ENABLED", true);
  }
  
  private static h e(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      if (paramInt < 0) {
        return h.a;
      }
      break;
    case 2: 
      return h.c;
    case 1: 
      return h.b;
    case 0: 
      return h.a;
    }
    return h.c;
  }
  
  private static void e(long paramLong)
  {
    v = paramLong;
    if (b == null) {
      return;
    }
    e localE = e.e;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SaveUnsentActiveTime: ");
    localStringBuilder.append(v);
    a(localE, localStringBuilder.toString());
    al.a(al.a, "GT_UNSENT_ACTIVE_TIME", paramLong);
  }
  
  static void e(String paramString)
  {
    q = paramString;
    if (b == null) {
      return;
    }
    al.a(al.a, "GT_PLAYER_ID", q);
  }
  
  public static void e(boolean paramBoolean)
  {
    Runnable local9 = new Runnable()
    {
      public void run()
      {
        ah.e(ah.b).a(this.a);
        an.b(this.a);
      }
    };
    if ((b != null) && (!J()))
    {
      local9.run();
      return;
    }
    a(e.c, "OneSignal.init has not been called. Moving subscription action to a waiting task queue.");
    a(new i(local9));
  }
  
  static boolean e()
  {
    return s;
  }
  
  private static ab f(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (P == null)
    {
      P = new ab(false);
      P.a.b(new OSPermissionChangedInternalObserver());
    }
    return P;
  }
  
  static String f()
  {
    return k(b);
  }
  
  static void f(String paramString)
  {
    e(paramString);
    R();
    c(K);
    h(b).a(paramString);
    if (T != null)
    {
      a(T.a, T.b, T.c);
      T = null;
    }
    ai.a(b, a, paramString, c.a());
  }
  
  private static ab g(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (k == null) {
      k = new ab(true);
    }
    return k;
  }
  
  static String g()
  {
    if ((q == null) && (b != null)) {
      q = al.b(al.a, "GT_PLAYER_ID", null);
    }
    return q;
  }
  
  public static void g(String paramString)
  {
    Runnable local14 = new Runnable()
    {
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: getstatic 27	com/onesignal/ah:b	Landroid/content/Context;
        //   3: ldc 29
        //   5: invokevirtual 35	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
        //   8: checkcast 37	android/app/NotificationManager
        //   11: astore 4
        //   13: getstatic 27	com/onesignal/ah:b	Landroid/content/Context;
        //   16: invokestatic 42	com/onesignal/aj:a	(Landroid/content/Context;)Lcom/onesignal/aj;
        //   19: astore 7
        //   21: aconst_null
        //   22: astore 5
        //   24: aconst_null
        //   25: astore 6
        //   27: aload 7
        //   29: invokevirtual 45	com/onesignal/aj:b	()Landroid/database/sqlite/SQLiteDatabase;
        //   32: astore_2
        //   33: aload_0
        //   34: getfield 16	com/onesignal/ah$14:a	Ljava/lang/String;
        //   37: astore_3
        //   38: aload_2
        //   39: ldc 29
        //   41: iconst_1
        //   42: anewarray 47	java/lang/String
        //   45: dup
        //   46: iconst_0
        //   47: ldc 49
        //   49: aastore
        //   50: ldc 51
        //   52: iconst_1
        //   53: anewarray 47	java/lang/String
        //   56: dup
        //   57: iconst_0
        //   58: aload_3
        //   59: aastore
        //   60: aconst_null
        //   61: aconst_null
        //   62: aconst_null
        //   63: invokevirtual 57	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   66: astore_2
        //   67: aload_2
        //   68: astore_3
        //   69: aload_2
        //   70: invokeinterface 63 1 0
        //   75: ifeq +36 -> 111
        //   78: aload_2
        //   79: astore_3
        //   80: aload_2
        //   81: aload_2
        //   82: ldc 49
        //   84: invokeinterface 67 2 0
        //   89: invokeinterface 71 2 0
        //   94: istore_1
        //   95: iload_1
        //   96: iconst_m1
        //   97: if_icmpeq -30 -> 67
        //   100: aload_2
        //   101: astore_3
        //   102: aload 4
        //   104: iload_1
        //   105: invokevirtual 75	android/app/NotificationManager:cancel	(I)V
        //   108: goto -41 -> 67
        //   111: aload_2
        //   112: ifnull +103 -> 215
        //   115: aload_2
        //   116: invokeinterface 78 1 0
        //   121: ifne +94 -> 215
        //   124: goto +85 -> 209
        //   127: astore 4
        //   129: goto +13 -> 142
        //   132: astore_2
        //   133: aconst_null
        //   134: astore_3
        //   135: goto +289 -> 424
        //   138: astore 4
        //   140: aconst_null
        //   141: astore_2
        //   142: aload_2
        //   143: astore_3
        //   144: getstatic 84	com/onesignal/ah$e:c	Lcom/onesignal/ah$e;
        //   147: astore 8
        //   149: aload_2
        //   150: astore_3
        //   151: new 86	java/lang/StringBuilder
        //   154: dup
        //   155: invokespecial 87	java/lang/StringBuilder:<init>	()V
        //   158: astore 9
        //   160: aload_2
        //   161: astore_3
        //   162: aload 9
        //   164: ldc 89
        //   166: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   169: pop
        //   170: aload_2
        //   171: astore_3
        //   172: aload 9
        //   174: aload_0
        //   175: getfield 16	com/onesignal/ah$14:a	Ljava/lang/String;
        //   178: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   181: pop
        //   182: aload_2
        //   183: astore_3
        //   184: aload 8
        //   186: aload 9
        //   188: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   191: aload 4
        //   193: invokestatic 100	com/onesignal/ah:a	(Lcom/onesignal/ah$e;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   196: aload_2
        //   197: ifnull +18 -> 215
        //   200: aload_2
        //   201: invokeinterface 78 1 0
        //   206: ifne +9 -> 215
        //   209: aload_2
        //   210: invokeinterface 103 1 0
        //   215: aload 6
        //   217: astore_2
        //   218: aload 7
        //   220: invokevirtual 105	com/onesignal/aj:a	()Landroid/database/sqlite/SQLiteDatabase;
        //   223: astore_3
        //   224: aload_3
        //   225: invokevirtual 108	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
        //   228: aload_0
        //   229: getfield 16	com/onesignal/ah$14:a	Ljava/lang/String;
        //   232: astore_2
        //   233: new 110	android/content/ContentValues
        //   236: dup
        //   237: invokespecial 111	android/content/ContentValues:<init>	()V
        //   240: astore 4
        //   242: aload 4
        //   244: ldc 113
        //   246: iconst_1
        //   247: invokestatic 119	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   250: invokevirtual 123	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
        //   253: aload_3
        //   254: ldc 29
        //   256: aload 4
        //   258: ldc 125
        //   260: iconst_1
        //   261: anewarray 47	java/lang/String
        //   264: dup
        //   265: iconst_0
        //   266: aload_2
        //   267: aastore
        //   268: invokevirtual 129	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
        //   271: pop
        //   272: aload_3
        //   273: getstatic 27	com/onesignal/ah:b	Landroid/content/Context;
        //   276: invokestatic 134	com/onesignal/f:a	(Landroid/database/sqlite/SQLiteDatabase;Landroid/content/Context;)V
        //   279: aload_3
        //   280: invokevirtual 137	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
        //   283: aload_3
        //   284: ifnull +115 -> 399
        //   287: aload_3
        //   288: invokevirtual 140	android/database/sqlite/SQLiteDatabase:endTransaction	()V
        //   291: return
        //   292: astore_2
        //   293: goto +107 -> 400
        //   296: astore 4
        //   298: goto +18 -> 316
        //   301: astore 4
        //   303: aload_2
        //   304: astore_3
        //   305: aload 4
        //   307: astore_2
        //   308: goto +92 -> 400
        //   311: astore 4
        //   313: aload 5
        //   315: astore_3
        //   316: aload_3
        //   317: astore_2
        //   318: getstatic 84	com/onesignal/ah$e:c	Lcom/onesignal/ah$e;
        //   321: astore 5
        //   323: aload_3
        //   324: astore_2
        //   325: new 86	java/lang/StringBuilder
        //   328: dup
        //   329: invokespecial 87	java/lang/StringBuilder:<init>	()V
        //   332: astore 6
        //   334: aload_3
        //   335: astore_2
        //   336: aload 6
        //   338: ldc -114
        //   340: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   343: pop
        //   344: aload_3
        //   345: astore_2
        //   346: aload 6
        //   348: aload_0
        //   349: getfield 16	com/onesignal/ah$14:a	Ljava/lang/String;
        //   352: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   355: pop
        //   356: aload_3
        //   357: astore_2
        //   358: aload 6
        //   360: ldc -112
        //   362: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   365: pop
        //   366: aload_3
        //   367: astore_2
        //   368: aload 5
        //   370: aload 6
        //   372: invokevirtual 97	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   375: aload 4
        //   377: invokestatic 100	com/onesignal/ah:a	(Lcom/onesignal/ah$e;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   380: aload_3
        //   381: ifnull +18 -> 399
        //   384: aload_3
        //   385: invokevirtual 140	android/database/sqlite/SQLiteDatabase:endTransaction	()V
        //   388: return
        //   389: astore_2
        //   390: getstatic 84	com/onesignal/ah$e:c	Lcom/onesignal/ah$e;
        //   393: ldc -110
        //   395: aload_2
        //   396: invokestatic 100	com/onesignal/ah:a	(Lcom/onesignal/ah$e;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   399: return
        //   400: aload_3
        //   401: ifnull +20 -> 421
        //   404: aload_3
        //   405: invokevirtual 140	android/database/sqlite/SQLiteDatabase:endTransaction	()V
        //   408: goto +13 -> 421
        //   411: astore_3
        //   412: getstatic 84	com/onesignal/ah$e:c	Lcom/onesignal/ah$e;
        //   415: ldc -110
        //   417: aload_3
        //   418: invokestatic 100	com/onesignal/ah:a	(Lcom/onesignal/ah$e;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   421: aload_2
        //   422: athrow
        //   423: astore_2
        //   424: aload_3
        //   425: ifnull +18 -> 443
        //   428: aload_3
        //   429: invokeinterface 78 1 0
        //   434: ifne +9 -> 443
        //   437: aload_3
        //   438: invokeinterface 103 1 0
        //   443: aload_2
        //   444: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	445	0	this	14
        //   94	11	1	i	int
        //   32	84	2	localObject1	Object
        //   132	1	2	localObject2	Object
        //   141	126	2	localObject3	Object
        //   292	12	2	localObject4	Object
        //   307	61	2	localObject5	Object
        //   389	33	2	localThrowable1	Throwable
        //   423	21	2	localObject6	Object
        //   37	368	3	localObject7	Object
        //   411	27	3	localThrowable2	Throwable
        //   11	92	4	localNotificationManager	NotificationManager
        //   127	1	4	localThrowable3	Throwable
        //   138	54	4	localThrowable4	Throwable
        //   240	17	4	localContentValues	android.content.ContentValues
        //   296	1	4	localThrowable5	Throwable
        //   301	5	4	localObject8	Object
        //   311	65	4	localThrowable6	Throwable
        //   22	347	5	localE1	ah.e
        //   25	346	6	localStringBuilder1	StringBuilder
        //   19	200	7	localAj	aj
        //   147	38	8	localE2	ah.e
        //   158	29	9	localStringBuilder2	StringBuilder
        // Exception table:
        //   from	to	target	type
        //   69	78	127	java/lang/Throwable
        //   80	95	127	java/lang/Throwable
        //   102	108	127	java/lang/Throwable
        //   27	67	132	finally
        //   27	67	138	java/lang/Throwable
        //   224	283	292	finally
        //   224	283	296	java/lang/Throwable
        //   218	224	301	finally
        //   318	323	301	finally
        //   325	334	301	finally
        //   336	344	301	finally
        //   346	356	301	finally
        //   358	366	301	finally
        //   368	380	301	finally
        //   218	224	311	java/lang/Throwable
        //   287	291	389	java/lang/Throwable
        //   384	388	389	java/lang/Throwable
        //   404	408	411	java/lang/Throwable
        //   69	78	423	finally
        //   80	95	423	finally
        //   102	108	423	finally
        //   144	149	423	finally
        //   151	160	423	finally
        //   162	170	423	finally
        //   172	182	423	finally
        //   184	196	423	finally
      }
    };
    if ((b != null) && (!J()))
    {
      local14.run();
      return;
    }
    e localE = e.c;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("OneSignal.init has not been called. Could not clear notifications part of group ");
    localStringBuilder.append(paramString);
    localStringBuilder.append(" - movingthis operation to a waiting task queue.");
    a(localE, localStringBuilder.toString());
    a(new i(local14));
  }
  
  private static OSSubscriptionState h(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (R == null)
    {
      R = new OSSubscriptionState(false, f(paramContext).b());
      f(paramContext).a.a(R);
      R.a.b(new OSSubscriptionChangedInternalObserver());
    }
    return R;
  }
  
  static boolean h()
  {
    if (i == null) {
      return true;
    }
    return i.h == h.c;
  }
  
  private static OSSubscriptionState i(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (l == null) {
      l = new OSSubscriptionState(true, false);
    }
    return l;
  }
  
  static boolean i()
  {
    a localA = i;
    boolean bool = false;
    if (localA == null) {
      return false;
    }
    if (i.h == h.b) {
      bool = true;
    }
    return bool;
  }
  
  public static void j()
  {
    Runnable local10 = new Runnable()
    {
      public void run()
      {
        m.a(ah.b, true, new m.c()
        {
          public void a(m.e paramAnonymous2E)
          {
            if (paramAnonymous2E != null) {
              an.a(paramAnonymous2E);
            }
          }
        });
        ah.l(true);
      }
    };
    if ((b != null) && (!J()))
    {
      local10.run();
      return;
    }
    a(e.c, "OneSignal.init has not been called. Could not prompt for location at this time - moving this operation to awaiting queue.");
    a(new i(local10));
  }
  
  private static void j(Context paramContext)
  {
    Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
    if (localIntent != null)
    {
      localIntent.setFlags(268566528);
      paramContext.startActivity(localIntent);
    }
  }
  
  private static void j(String paramString)
  {
    if (b == null) {
      return;
    }
    al.a(al.a, "GT_APP_ID", paramString);
  }
  
  private static String k(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return al.b(al.a, "GT_APP_ID", null);
  }
  
  public static void k()
  {
    Runnable local11 = new Runnable()
    {
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: getstatic 22	com/onesignal/ah:b	Landroid/content/Context;
        //   3: ldc 24
        //   5: invokevirtual 30	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
        //   8: checkcast 32	android/app/NotificationManager
        //   11: astore 8
        //   13: getstatic 22	com/onesignal/ah:b	Landroid/content/Context;
        //   16: invokestatic 38	com/onesignal/aj:a	(Landroid/content/Context;)Lcom/onesignal/aj;
        //   19: astore 7
        //   21: aconst_null
        //   22: astore 5
        //   24: aconst_null
        //   25: astore_2
        //   26: aconst_null
        //   27: astore_3
        //   28: aconst_null
        //   29: astore 6
        //   31: aload 7
        //   33: invokevirtual 41	com/onesignal/aj:b	()Landroid/database/sqlite/SQLiteDatabase;
        //   36: ldc 24
        //   38: iconst_1
        //   39: anewarray 43	java/lang/String
        //   42: dup
        //   43: iconst_0
        //   44: ldc 45
        //   46: aastore
        //   47: ldc 47
        //   49: aconst_null
        //   50: aconst_null
        //   51: aconst_null
        //   52: aconst_null
        //   53: invokevirtual 53	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
        //   56: astore 4
        //   58: aload 4
        //   60: invokeinterface 59 1 0
        //   65: ifeq +36 -> 101
        //   68: aload 8
        //   70: aload 4
        //   72: aload 4
        //   74: ldc 45
        //   76: invokeinterface 63 2 0
        //   81: invokeinterface 67 2 0
        //   86: invokevirtual 71	android/app/NotificationManager:cancel	(I)V
        //   89: aload 4
        //   91: invokeinterface 74 1 0
        //   96: istore_1
        //   97: iload_1
        //   98: ifne -30 -> 68
        //   101: aload 6
        //   103: astore_2
        //   104: aload 7
        //   106: invokevirtual 76	com/onesignal/aj:a	()Landroid/database/sqlite/SQLiteDatabase;
        //   109: astore_3
        //   110: aload_3
        //   111: invokevirtual 79	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
        //   114: new 81	android/content/ContentValues
        //   117: dup
        //   118: invokespecial 82	android/content/ContentValues:<init>	()V
        //   121: astore_2
        //   122: aload_2
        //   123: ldc 84
        //   125: iconst_1
        //   126: invokestatic 90	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   129: invokevirtual 94	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
        //   132: aload_3
        //   133: ldc 24
        //   135: aload_2
        //   136: ldc 96
        //   138: aconst_null
        //   139: invokevirtual 100	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
        //   142: pop
        //   143: aload_3
        //   144: invokevirtual 103	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
        //   147: aload_3
        //   148: ifnull +82 -> 230
        //   151: aload_3
        //   152: invokevirtual 106	android/database/sqlite/SQLiteDatabase:endTransaction	()V
        //   155: goto +75 -> 230
        //   158: astore_2
        //   159: getstatic 112	com/onesignal/ah$e:c	Lcom/onesignal/ah$e;
        //   162: astore_3
        //   163: aload_3
        //   164: ldc 114
        //   166: aload_2
        //   167: invokestatic 117	com/onesignal/ah:a	(Lcom/onesignal/ah$e;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   170: goto +60 -> 230
        //   173: astore_2
        //   174: goto +76 -> 250
        //   177: astore 5
        //   179: goto +20 -> 199
        //   182: astore 5
        //   184: aload_2
        //   185: astore_3
        //   186: aload 5
        //   188: astore_2
        //   189: goto +61 -> 250
        //   192: astore_2
        //   193: aload 5
        //   195: astore_3
        //   196: aload_2
        //   197: astore 5
        //   199: aload_3
        //   200: astore_2
        //   201: getstatic 112	com/onesignal/ah$e:c	Lcom/onesignal/ah$e;
        //   204: ldc 119
        //   206: aload 5
        //   208: invokestatic 117	com/onesignal/ah:a	(Lcom/onesignal/ah$e;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   211: aload_3
        //   212: ifnull +18 -> 230
        //   215: aload_3
        //   216: invokevirtual 106	android/database/sqlite/SQLiteDatabase:endTransaction	()V
        //   219: goto +11 -> 230
        //   222: astore_2
        //   223: getstatic 112	com/onesignal/ah$e:c	Lcom/onesignal/ah$e;
        //   226: astore_3
        //   227: goto -64 -> 163
        //   230: iconst_0
        //   231: getstatic 22	com/onesignal/ah:b	Landroid/content/Context;
        //   234: invokestatic 124	com/onesignal/f:a	(ILandroid/content/Context;)V
        //   237: aload 4
        //   239: ifnull +85 -> 324
        //   242: aload 4
        //   244: invokeinterface 127 1 0
        //   249: return
        //   250: aload_3
        //   251: ifnull +20 -> 271
        //   254: aload_3
        //   255: invokevirtual 106	android/database/sqlite/SQLiteDatabase:endTransaction	()V
        //   258: goto +13 -> 271
        //   261: astore_3
        //   262: getstatic 112	com/onesignal/ah$e:c	Lcom/onesignal/ah$e;
        //   265: ldc 114
        //   267: aload_3
        //   268: invokestatic 117	com/onesignal/ah:a	(Lcom/onesignal/ah$e;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   271: aload_2
        //   272: athrow
        //   273: astore_2
        //   274: aload 4
        //   276: astore_3
        //   277: goto +48 -> 325
        //   280: astore_2
        //   281: aload 4
        //   283: astore_3
        //   284: aload_2
        //   285: astore 4
        //   287: goto +15 -> 302
        //   290: astore 4
        //   292: aload_2
        //   293: astore_3
        //   294: aload 4
        //   296: astore_2
        //   297: goto +28 -> 325
        //   300: astore 4
        //   302: aload_3
        //   303: astore_2
        //   304: getstatic 112	com/onesignal/ah$e:c	Lcom/onesignal/ah$e;
        //   307: ldc -127
        //   309: aload 4
        //   311: invokestatic 117	com/onesignal/ah:a	(Lcom/onesignal/ah$e;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   314: aload_3
        //   315: ifnull +9 -> 324
        //   318: aload_3
        //   319: invokeinterface 127 1 0
        //   324: return
        //   325: aload_3
        //   326: ifnull +9 -> 335
        //   329: aload_3
        //   330: invokeinterface 127 1 0
        //   335: aload_2
        //   336: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	337	0	this	11
        //   96	2	1	bool	boolean
        //   25	111	2	localObject1	Object
        //   158	9	2	localThrowable1	Throwable
        //   173	12	2	localObject2	Object
        //   188	1	2	localObject3	Object
        //   192	5	2	localThrowable2	Throwable
        //   200	1	2	localObject4	Object
        //   222	50	2	localThrowable3	Throwable
        //   273	1	2	localObject5	Object
        //   280	13	2	localThrowable4	Throwable
        //   296	40	2	localObject6	Object
        //   27	228	3	localObject7	Object
        //   261	7	3	localThrowable5	Throwable
        //   276	54	3	localObject8	Object
        //   56	230	4	localObject9	Object
        //   290	5	4	localObject10	Object
        //   300	10	4	localThrowable6	Throwable
        //   22	1	5	localObject11	Object
        //   177	1	5	localThrowable7	Throwable
        //   182	12	5	localObject12	Object
        //   197	10	5	localObject13	Object
        //   29	73	6	localObject14	Object
        //   19	86	7	localAj	aj
        //   11	58	8	localNotificationManager	NotificationManager
        // Exception table:
        //   from	to	target	type
        //   151	155	158	java/lang/Throwable
        //   110	147	173	finally
        //   110	147	177	java/lang/Throwable
        //   104	110	182	finally
        //   201	211	182	finally
        //   104	110	192	java/lang/Throwable
        //   215	219	222	java/lang/Throwable
        //   254	258	261	java/lang/Throwable
        //   58	68	273	finally
        //   68	97	273	finally
        //   151	155	273	finally
        //   159	163	273	finally
        //   163	170	273	finally
        //   215	219	273	finally
        //   223	227	273	finally
        //   230	237	273	finally
        //   254	258	273	finally
        //   262	271	273	finally
        //   271	273	273	finally
        //   58	68	280	java/lang/Throwable
        //   68	97	280	java/lang/Throwable
        //   159	163	280	java/lang/Throwable
        //   163	170	280	java/lang/Throwable
        //   223	227	280	java/lang/Throwable
        //   230	237	280	java/lang/Throwable
        //   262	271	280	java/lang/Throwable
        //   271	273	280	java/lang/Throwable
        //   31	58	290	finally
        //   304	314	290	finally
        //   31	58	300	java/lang/Throwable
      }
    };
    if ((b != null) && (!J()))
    {
      local11.run();
      return;
    }
    a(e.c, "OneSignal.init has not been called. Could not clear notifications at this time - moving this operation toa waiting task queue.");
    a(new i(local11));
  }
  
  public static ad l()
  {
    if (b == null)
    {
      a(e.c, "OneSignal.init has not been called. Could not get OSPermissionSubscriptionState");
      return null;
    }
    ad localAd = new ad();
    localAd.a = h(b);
    localAd.b = f(b);
    return localAd;
  }
  
  private static String l(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return al.b(al.a, "GT_PLAYER_ID", null);
  }
  
  static long m()
  {
    if ((v == -1L) && (b != null)) {
      v = al.b(al.a, "GT_UNSENT_ACTIVE_TIME", 0L);
    }
    e localE = e.e;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("GetUnsentActiveTime: ");
    localStringBuilder.append(v);
    a(localE, localStringBuilder.toString());
    return v;
  }
  
  private static long m(Context paramContext)
  {
    return al.b(al.a, "OS_LAST_SESSION_TIME", -31000L);
  }
  
  static boolean n()
  {
    return (c) && (e());
  }
  
  static void o()
  {
    N = false;
    a(System.currentTimeMillis());
  }
  
  static boolean p()
  {
    if (i.e) {
      return ag.a(b);
    }
    return true;
  }
  
  public static class a
  {
    ah.f a;
    ah.g b;
    boolean c;
    boolean d;
    boolean e;
    boolean f;
    boolean g;
    ah.h h = ah.h.b;
    
    private a() {}
    
    public a a(boolean paramBoolean)
    {
      this.e = paramBoolean;
      return this;
    }
    
    public a b(boolean paramBoolean)
    {
      this.f = paramBoolean;
      return this;
    }
  }
  
  public static abstract interface b
  {
    public abstract void a(JSONObject paramJSONObject);
  }
  
  private static class c
  {
    JSONArray a;
    boolean b;
    am.a c;
    
    c(JSONArray paramJSONArray)
    {
      this.a = paramJSONArray;
    }
  }
  
  public static abstract interface d
  {
    public abstract void a(String paramString1, String paramString2);
  }
  
  public static enum e
  {
    private e() {}
  }
  
  public static abstract interface f
  {
    public abstract void notificationOpened(x paramX);
  }
  
  public static abstract interface g
  {
    public abstract void notificationReceived(v paramV);
  }
  
  public static enum h
  {
    private h() {}
  }
  
  private static class i
    implements Runnable
  {
    private Runnable a;
    private long b;
    
    i(Runnable paramRunnable)
    {
      this.a = paramRunnable;
    }
    
    public void run()
    {
      this.a.run();
      ah.b(this.b);
    }
  }
  
  public static abstract interface j
  {
    public abstract void a(JSONObject paramJSONObject);
    
    public abstract void b(JSONObject paramJSONObject);
  }
}
