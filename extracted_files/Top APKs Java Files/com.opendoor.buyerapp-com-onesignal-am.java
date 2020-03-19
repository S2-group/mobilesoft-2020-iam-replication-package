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

public class am
{
  private static long A = -1L;
  private static bd B;
  private static bb C;
  private static bc D;
  private static d E = new c();
  private static int F = 0;
  private static al G;
  private static String H;
  private static boolean I = false;
  private static boolean J = false;
  private static boolean K = false;
  private static boolean L = false;
  private static p.f M;
  private static Collection<JSONArray> N = new ArrayList();
  private static HashSet<String> O = new HashSet();
  private static f P;
  private static boolean Q = false;
  private static boolean R = false;
  private static boolean S = false;
  private static JSONObject T;
  private static boolean U = false;
  private static ah V;
  private static ag<Object, ai> W;
  private static OSSubscriptionState X;
  private static ag<Object, ak> Y;
  private static z Z;
  static String a;
  private static g aa;
  private static au ab;
  private static int ac = 0;
  static Context b;
  static boolean c = false;
  static ExecutorService d;
  public static ConcurrentLinkedQueue<Runnable> e;
  static AtomicLong f;
  public static String g = "native";
  static boolean h = true;
  static a i;
  static boolean j = false;
  static boolean k = false;
  static k l;
  static ah m;
  static OSSubscriptionState n;
  private static e o;
  private static e p;
  private static String q;
  private static boolean r = false;
  private static i s = i.a;
  private static i t = i.d;
  private static String u;
  private static String v;
  private static int w = 0;
  private static boolean x = false;
  private static h y;
  private static long z = 1L;
  
  static
  {
    e = new ConcurrentLinkedQueue();
    f = new AtomicLong();
  }
  
  public am() {}
  
  static void A()
  {
    if (o != null)
    {
      o.a(new d(c.d, "Failed due to network failure. Will retry on next sync."));
      o = null;
    }
  }
  
  private static void M()
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
  
  private static boolean N()
  {
    if ((c) && (d == null)) {
      return false;
    }
    if ((!c) && (d == null)) {
      return true;
    }
    return (d != null) && (!d.isShutdown());
  }
  
  private static void O()
  {
    if (R) {
      return;
    }
    boolean bool2 = true;
    R = true;
    I = false;
    if (S) {
      J = false;
    }
    P();
    S();
    boolean bool1 = bool2;
    if (!L) {
      if (i.c) {
        bool1 = bool2;
      } else {
        bool1 = false;
      }
    }
    L = bool1;
  }
  
  private static void P()
  {
    p.d local12 = new p.d()
    {
      public p.a a()
      {
        return p.a.a;
      }
      
      public void a(p.f paramAnonymousF)
      {
        am.a(paramAnonymousF);
        am.i(true);
        am.B();
      }
    };
    boolean bool;
    if ((i.c) && (!L)) {
      bool = true;
    } else {
      bool = false;
    }
    p.a(b, bool, local12);
  }
  
  private static au Q()
  {
    if (ab != null) {
      return ab;
    }
    Object localObject;
    if (F == 2) {
      localObject = new av();
    }
    for (;;)
    {
      ab = (au)localObject;
      break;
      if (al.a()) {
        localObject = new ax();
      } else {
        localObject = new ay();
      }
    }
    return ab;
  }
  
  private static void R()
  {
    Q().a(b, q, new au.a()
    {
      public void a(String paramAnonymousString, int paramAnonymousInt)
      {
        if (paramAnonymousInt < 1 ? (as.h() == null) && ((am.C() == 1) || (am.c(am.C()))) : am.c(am.C())) {
          am.d(paramAnonymousInt);
        }
        am.i(paramAnonymousString);
        am.j(true);
        am.f(am.b).b(paramAnonymousString);
        am.B();
      }
    });
  }
  
  private static void S()
  {
    if (K)
    {
      R();
      return;
    }
    ar.a local18 = new ar.a()
    {
      void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        new Thread(new Runnable()
        {
          public void run()
          {
            try
            {
              int j = am.D() * 10000 + 30000;
              int i = j;
              if (j > 90000) {
                i = 90000;
              }
              am.i localI = am.i.e;
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("Failed to get Android parameters, trying again in ");
              localStringBuilder.append(i / 1000);
              localStringBuilder.append(" seconds.");
              am.b(localI, localStringBuilder.toString());
              Thread.sleep(i);
            }
            catch (Throwable localThrowable)
            {
              for (;;) {}
            }
            am.E();
            am.F();
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
            am.k(true);
            am.j(paramAnonymousString.getString("android_sender_id"));
          }
          am.j = paramAnonymousString.optBoolean("enterp", false);
          am.l(paramAnonymousString.optBoolean("use_email_auth", false));
          am.b(paramAnonymousString.getJSONObject("awl_list"));
          boolean bool = paramAnonymousString.optBoolean("fba", false);
          aq.a(aq.a, "GT_FIREBASE_TRACKING_ENABLED", bool);
          r.a(am.b, paramAnonymousString);
        }
        catch (Throwable paramAnonymousString)
        {
          paramAnonymousString.printStackTrace();
        }
        am.m(true);
        am.G();
      }
    };
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("apps/");
    ((StringBuilder)localObject).append(a);
    ((StringBuilder)localObject).append("/android_params.js");
    String str1 = ((StringBuilder)localObject).toString();
    String str2 = m();
    localObject = str1;
    if (str2 != null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(str1);
      ((StringBuilder)localObject).append("?player_id=");
      ((StringBuilder)localObject).append(str2);
      localObject = ((StringBuilder)localObject).toString();
    }
    b(i.f, "Starting request to get Android parameters.");
    ar.a((String)localObject, local18);
  }
  
  private static void T()
  {
    Iterator localIterator = N.iterator();
    while (localIterator.hasNext()) {
      b((JSONArray)localIterator.next(), true, false);
    }
    N.clear();
  }
  
  private static int U()
  {
    TimeZone localTimeZone = Calendar.getInstance().getTimeZone();
    int i2 = localTimeZone.getRawOffset();
    int i1 = i2;
    if (localTimeZone.inDaylightTime(new Date())) {
      i1 = i2 + localTimeZone.getDSTSavings();
    }
    return i1 / 1000;
  }
  
  private static void V()
  {
    i localI = i.f;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("registerUser: registerForPushFired:");
    localStringBuilder.append(I);
    localStringBuilder.append(", locationFired: ");
    localStringBuilder.append(J);
    localStringBuilder.append(", awlFired: ");
    localStringBuilder.append(K);
    b(localI, localStringBuilder.toString());
    if ((I) && (J))
    {
      if (!K) {
        return;
      }
      new Thread(new Runnable()
      {
        public void run()
        {
          try
          {
            am.H();
            an.a(am.b, am.a, am.I(), c.a());
            return;
          }
          catch (JSONException localJSONException)
          {
            am.a(am.i.b, "FATAL Error registering device!", localJSONException);
          }
        }
      }, "OS_REG_USER").start();
    }
  }
  
  private static void W()
  {
    Object localObject2 = b.getPackageName();
    Object localObject1 = b.getPackageManager();
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("app_id", a);
    Object localObject3 = E.a(b);
    if (localObject3 != null) {
      localJSONObject.put("ad_id", localObject3);
    }
    localJSONObject.put("device_os", Build.VERSION.RELEASE);
    localJSONObject.put("timezone", U());
    localJSONObject.put("language", al.f());
    localJSONObject.put("sdk", "031005");
    localJSONObject.put("sdk_type", g);
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
        i1 = 0;
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          int i1;
          String str;
          continue;
          i1 += 1;
        }
      }
      if (i1 < ((List)localObject1).size())
      {
        ((MessageDigest)localObject3).update(((PackageInfo)((List)localObject1).get(i1)).packageName.getBytes());
        str = Base64.encodeToString(((MessageDigest)localObject3).digest(), 2);
        if (T.has(str)) {
          ((JSONArray)localObject2).put(str);
        }
      }
      else
      {
        localJSONObject.put("pkgs", localObject2);
        localJSONObject.put("net_type", G.d());
        localJSONObject.put("carrier", G.e());
        localJSONObject.put("rooted", ba.a());
        as.a(localJSONObject);
        localJSONObject = new JSONObject();
        localJSONObject.put("identifier", H);
        localJSONObject.put("subscribableStatus", w);
        localJSONObject.put("androidPermission", w());
        localJSONObject.put("device_type", F);
        as.b(localJSONObject);
        if ((h) && (M != null)) {
          as.a(M);
        }
        if (S) {
          as.k();
        }
        R = false;
        return;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
  }
  
  private static void X()
  {
    try
    {
      Object localObject1 = y;
      if (localObject1 == null) {
        return;
      }
      localObject1 = as.h();
      if (!as.g()) {
        localObject1 = null;
      }
      String str = m();
      if (str == null) {
        return;
      }
      y.a(str, (String)localObject1);
      if (localObject1 != null) {
        y = null;
      }
      return;
    }
    finally {}
  }
  
  private static boolean Y()
  {
    if (S) {
      return true;
    }
    return (System.currentTimeMillis() - n(b)) / 1000L >= 30L;
  }
  
  static ag<Object, ai> a()
  {
    if (W == null) {
      W = new ag("onOSPermissionChanged", true);
    }
    return W;
  }
  
  static String a(Bundle paramBundle)
  {
    if (paramBundle.isEmpty()) {
      return null;
    }
    try
    {
      i localI;
      if (paramBundle.containsKey("custom"))
      {
        paramBundle = new JSONObject(paramBundle.getString("custom"));
        if (paramBundle.has("i")) {
          return paramBundle.optString("i", null);
        }
        localI = i.f;
      }
      for (paramBundle = "Not a OneSignal formatted GCM message. No 'i' field in custom.";; paramBundle = "Not a OneSignal formatted GCM message. No 'custom' field in the bundle.")
      {
        b(localI, paramBundle);
        return null;
        localI = i.f;
      }
      return null;
    }
    catch (Throwable paramBundle)
    {
      a(i.f, "Could not parse bundle, probably not a OneSignal notification.", paramBundle);
    }
  }
  
  public static void a(int paramInt)
  {
    a(g(paramInt));
  }
  
  public static void a(int paramInt1, int paramInt2)
  {
    a(f(paramInt1), f(paramInt2));
  }
  
  static void a(long paramLong)
  {
    aq.a(aq.a, "OS_LAST_SESSION_TIME", paramLong);
  }
  
  static void a(long paramLong, boolean paramBoolean)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject().put("app_id", a).put("type", 1).put("state", "ping").put("active_time", paramLong);
      c(localJSONObject);
      a(m(), localJSONObject, paramBoolean);
      String str = n();
      if (str != null)
      {
        a(str, localJSONObject, paramBoolean);
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      a(i.c, "Generating on_focus:JSON Failed.", localThrowable);
    }
  }
  
  static void a(Context paramContext)
  {
    int i1;
    if (b == null) {
      i1 = 1;
    } else {
      i1 = 0;
    }
    b = paramContext.getApplicationContext();
    if (i1 != 0) {
      aq.b();
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, j paramJ, k paramK)
  {
    a(paramContext);
    if ((k) && (!d()))
    {
      b(i.g, "OneSignal SDK initialization delayed, user privacy consent is set to required for this application.");
      l = new k(paramContext, paramString1, paramString2, paramJ, paramK);
      return;
    }
    i = c();
    i.g = false;
    i.a = paramJ;
    i.b = paramK;
    if (!r) {
      q = paramString1;
    }
    G = new al();
    F = G.c();
    w = G.a(paramContext, F, paramString2);
    if (w == 64537) {
      return;
    }
    if (c)
    {
      if (i.a != null) {
        T();
      }
      return;
    }
    boolean bool = paramContext instanceof Activity;
    x = bool;
    a = paramString2;
    d(i.f);
    if (bool)
    {
      a.b = (Activity)paramContext;
      w.a(b);
    }
    else
    {
      a.a = true;
    }
    z = SystemClock.elapsedRealtime();
    as.e();
    ((Application)b).registerActivityLifecycleCallbacks(new b());
    try
    {
      Class.forName("com.amazon.device.iap.PurchasingListener");
      C = new bb(b);
      paramContext = k();
      if (paramContext != null)
      {
        if (!paramContext.equals(a))
        {
          b(i.f, "APP ID changed, clearing user id as it is no longer valid.");
          k(a);
          as.i();
        }
      }
      else
      {
        f.a(0, b);
        k(a);
      }
      OSPermissionChangedInternalObserver.a(h(b));
      if ((x) || (m() == null))
      {
        S = Y();
        a(System.currentTimeMillis());
        O();
      }
      if (i.a != null) {
        T();
      }
      if (bd.a(b)) {
        B = new bd(b);
      }
      if (bc.a()) {
        D = new bc(b);
      }
      ax.a(b);
      c = true;
      M();
      return;
    }
    catch (ClassNotFoundException paramContext)
    {
      for (;;) {}
    }
  }
  
  public static void a(Context paramContext, JSONArray paramJSONArray, boolean paramBoolean)
  {
    if (a(null)) {
      return;
    }
    b(paramContext, paramJSONArray);
    if ((D != null) && (c(b))) {
      D.a(c(paramJSONArray, true, paramBoolean));
    }
    boolean bool1 = false;
    boolean bool2 = "DISABLE".equals(al.a(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
    if (!bool2) {
      bool1 = a(paramContext, paramJSONArray);
    }
    b(paramJSONArray, true, paramBoolean);
    if ((!paramBoolean) && (!bool1) && (!bool2)) {
      k(paramContext);
    }
  }
  
  private static void a(ad paramAd)
  {
    al.a(new Runnable()
    {
      public void run()
      {
        am.i.a.notificationOpened(this.a);
      }
    });
  }
  
  public static void a(e paramE)
  {
    if (a("logoutEmail()")) {
      return;
    }
    if (n() == null)
    {
      if (paramE != null) {
        paramE.a(new d(c.c, "logoutEmail not valid as email was not set or already logged out!"));
      }
      b(i.c, "logoutEmail not valid as email was not set or already logged out!");
      return;
    }
    p = paramE;
    paramE = new Runnable()
    {
      public void run() {}
    };
    if ((b != null) && (!N()))
    {
      paramE.run();
      return;
    }
    b(i.c, "You should initialize OneSignal before calling logoutEmail! Moving this operation to a pending task queue.");
    a(new m(paramE));
  }
  
  public static void a(f paramF)
  {
    if (a("getTags()")) {
      return;
    }
    P = paramF;
    paramF = new Runnable()
    {
      public void run()
      {
        if (this.a == null)
        {
          am.b(am.i.c, "getTagsHandler is null!");
          return;
        }
        if (am.m() == null) {
          return;
        }
        am.b(am.J());
      }
    };
    if (b == null)
    {
      b(i.c, "You must initialize OneSignal before getting tags! Moving this tag operation to a pending queue.");
      e.add(paramF);
      return;
    }
    paramF.run();
  }
  
  public static void a(h paramH)
  {
    if (a("idsAvailable()")) {
      return;
    }
    y = paramH;
    paramH = new Runnable()
    {
      public void run()
      {
        if (am.m() != null) {
          al.a(new Runnable()
          {
            public void run() {}
          });
        }
      }
    };
    if ((b != null) && (!N()))
    {
      paramH.run();
      return;
    }
    b(i.c, "You must initialize OneSignal before getting tags! Moving this tag operation to a pending queue.");
    a(new m(paramH));
  }
  
  public static void a(i paramI1, i paramI2)
  {
    t = paramI1;
    s = paramI2;
  }
  
  public static void a(i paramI, String paramString)
  {
    b(paramI, paramString);
  }
  
  static void a(i paramI, final String paramString, Throwable paramThrowable)
  {
    if (paramI.compareTo(t) < 1) {
      if (paramI == i.g) {
        Log.v("OneSignal", paramString, paramThrowable);
      } else if (paramI == i.f) {
        Log.d("OneSignal", paramString, paramThrowable);
      } else if (paramI == i.e) {
        Log.i("OneSignal", paramString, paramThrowable);
      } else if (paramI == i.d) {
        Log.w("OneSignal", paramString, paramThrowable);
      } else if ((paramI == i.c) || (paramI == i.b)) {
        Log.e("OneSignal", paramString, paramThrowable);
      }
    }
    if ((paramI.compareTo(s) < 1) && (a.b != null)) {
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
        al.a(new Runnable()
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
      catch (Throwable paramI)
      {
        Log.e("OneSignal", "Error showing logging message.", paramI);
      }
    }
  }
  
  public static void a(l paramL)
  {
    c().g = true;
    c().h = paramL;
  }
  
  private static void a(m paramM)
  {
    m.a(paramM, f.incrementAndGet());
    i localI;
    StringBuilder localStringBuilder;
    if (d == null)
    {
      localI = i.e;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Adding a task to the pending queue with ID: ");
      localStringBuilder.append(m.a(paramM));
      b(localI, localStringBuilder.toString());
      e.add(paramM);
      return;
    }
    if (!d.isShutdown())
    {
      localI = i.e;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Executor is still running, add to the executor with ID: ");
      localStringBuilder.append(m.a(paramM));
      b(localI, localStringBuilder.toString());
      d.submit(paramM);
    }
  }
  
  public static void a(String paramString, b paramB)
  {
    if (a("deleteTag()")) {
      return;
    }
    ArrayList localArrayList = new ArrayList(1);
    localArrayList.add(paramString);
    a(localArrayList, paramB);
  }
  
  public static void a(String paramString1, String paramString2)
  {
    if (a("sendTag()")) {
      return;
    }
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
  
  public static void a(String paramString1, final String paramString2, e paramE)
  {
    if (a("setEmail()")) {
      return;
    }
    if (!al.a(paramString1))
    {
      if (paramE != null) {
        paramE.a(new d(c.a, "Email is invalid"));
      }
      b(i.c, "Email is invalid");
      return;
    }
    if ((U) && (paramString2 == null))
    {
      if (paramE != null) {
        paramE.a(new d(c.b, "Email authentication (auth token) is set to REQUIRED for this application. Please provide an auth token from your backend server or change the setting in the OneSignal dashboard."));
      }
      b(i.c, "Email authentication (auth token) is set to REQUIRED for this application. Please provide an auth token from your backend server or change the setting in the OneSignal dashboard.");
      return;
    }
    o = paramE;
    paramString1 = new Runnable()
    {
      public void run()
      {
        String str1 = this.a.trim();
        String str2 = paramString2;
        if (str2 != null) {
          str2.toLowerCase();
        }
        am.g(am.b).b(str1);
        as.a(str1.toLowerCase(), str2);
      }
    };
    if ((b != null) && (!N()))
    {
      paramString1.run();
      return;
    }
    b(i.c, "You should initialize OneSignal before calling setEmail! Moving this operation to a pending task queue.");
    a(new m(paramString1));
  }
  
  private static void a(String paramString, JSONObject paramJSONObject, boolean paramBoolean)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("players/");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append("/on_focus");
    paramString = ((StringBuilder)localObject).toString();
    localObject = new ar.a()
    {
      void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        am.a("sending on_focus Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
      }
      
      void a(String paramAnonymousString)
      {
        am.c(0L);
      }
    };
    if (paramBoolean)
    {
      ar.d(paramString, paramJSONObject, (ar.a)localObject);
      return;
    }
    ar.b(paramString, paramJSONObject, (ar.a)localObject);
  }
  
  public static void a(Collection<String> paramCollection, b paramB)
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
      a(localJSONObject, paramB);
      return;
    }
    catch (Throwable paramCollection)
    {
      a(i.c, "Failed to generate JSON for deleteTags.", paramCollection);
    }
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean, ar.a paramA)
  {
    if (a("sendPurchases()")) {
      return;
    }
    if (m() == null)
    {
      aa = new g(paramJSONArray);
      aa.b = paramBoolean;
      aa.c = paramA;
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
      paramJSONArray.append(m());
      paramJSONArray.append("/on_purchase");
      ar.b(paramJSONArray.toString(), localJSONObject, paramA);
      if (n() != null)
      {
        paramJSONArray = new StringBuilder();
        paramJSONArray.append("players/");
        paramJSONArray.append(n());
        paramJSONArray.append("/on_purchase");
        ar.b(paramJSONArray.toString(), localJSONObject, null);
        return;
      }
    }
    catch (Throwable paramJSONArray)
    {
      a(i.c, "Failed to generate JSON for sendPurchases.", paramJSONArray);
    }
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramJSONArray = c(paramJSONArray, paramBoolean1, paramBoolean2);
    if ((D != null) && (c(b))) {
      D.b(paramJSONArray);
    }
    if (i != null)
    {
      if (i.b == null) {
        return;
      }
      i.b.notificationReceived(paramJSONArray.a);
    }
  }
  
  public static void a(JSONObject paramJSONObject)
  {
    a(paramJSONObject, null);
  }
  
  public static void a(JSONObject paramJSONObject, final b paramB)
  {
    if (a("sendTags()")) {
      return;
    }
    paramJSONObject = new Runnable()
    {
      public void run()
      {
        if (this.a == null)
        {
          if (paramB != null) {
            paramB.a(new am.o(-1, "Attempted to send null tags"));
          }
          return;
        }
        JSONObject localJSONObject1 = as.d(false).b;
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
              if ((!this.a.isNull(str)) && (!"".equals(localObject))) {}
              for (localObject = localObject.toString();; localObject = "")
              {
                localJSONObject2.put(str, localObject);
                break;
                if ((localJSONObject1 == null) || (!localJSONObject1.has(str))) {
                  break;
                }
              }
            }
            localObject = am.i.c;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("Omitting key '");
            localStringBuilder.append(str);
            localStringBuilder.append("'! sendTags DO NOT supported nested values!");
            am.b((am.i)localObject, localStringBuilder.toString());
          }
          catch (Throwable localThrowable) {}
          if (!localJSONObject2.toString().equals("{}"))
          {
            as.a(localJSONObject2, paramB);
            return;
          }
          if (paramB != null) {
            paramB.a(localJSONObject1);
          }
          return;
        }
      }
    };
    if ((b != null) && (!N()))
    {
      paramJSONObject.run();
      return;
    }
    b(i.c, "You must initialize OneSignal before modifying tags!Moving this operation to a pending task queue.");
    if (paramB != null) {
      paramB.a(new o(-1, "You must initialize OneSignal before modifying tags!Moving this operation to a pending task queue."));
    }
    a(new m(paramJSONObject));
  }
  
  public static void a(JSONObject paramJSONObject, n paramN)
  {
    if (a("postNotification()")) {
      return;
    }
    try
    {
      if (!paramJSONObject.has("app_id")) {
        paramJSONObject.put("app_id", k());
      }
      ar.b("notifications/", paramJSONObject, new ar.a()
      {
        void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
        {
          am.a("create notification failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
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
          am.i localI = am.i.f;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("HTTP create notification success: ");
          Object localObject;
          if (paramAnonymousString != null) {
            localObject = paramAnonymousString;
          } else {
            localObject = "null";
          }
          localStringBuilder.append((String)localObject);
          am.b(localI, localStringBuilder.toString());
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
      a(i.c, "HTTP create notification json exception!", paramJSONObject);
      if (paramN != null) {
        try
        {
          paramN.b(new JSONObject("{'error': 'HTTP create notification json exception!'}"));
          return;
        }
        catch (JSONException paramJSONObject)
        {
          paramJSONObject.printStackTrace();
        }
      }
    }
  }
  
  public static void a(boolean paramBoolean)
  {
    boolean bool = d();
    c(paramBoolean);
    if ((!bool) && (paramBoolean) && (l != null))
    {
      a(l.a, l.b, l.c, l.d, l.e);
      l = null;
    }
  }
  
  private static boolean a(Context paramContext, JSONArray paramJSONArray)
  {
    boolean bool1 = a(null);
    int i1 = 0;
    if (bool1) {
      return false;
    }
    int i2 = paramJSONArray.length();
    boolean bool2;
    for (bool1 = false; i1 < i2; bool1 = bool2)
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
        Object localObject2 = i.c;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Error parsing JSON item ");
        localStringBuilder.append(i1);
        localStringBuilder.append("/");
        localStringBuilder.append(i2);
        localStringBuilder.append(" for launching a web URL.");
        a((i)localObject2, localStringBuilder.toString(), localThrowable);
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
  
  private static boolean a(i paramI)
  {
    int i1 = paramI.compareTo(s);
    boolean bool = true;
    if (i1 >= 1)
    {
      if (paramI.compareTo(t) < 1) {
        return true;
      }
      bool = false;
    }
    return bool;
  }
  
  static boolean a(String paramString)
  {
    if ((k) && (!d()))
    {
      if (paramString != null)
      {
        i localI = i.d;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Method ");
        localStringBuilder.append(paramString);
        localStringBuilder.append(" was called before the user provided privacy consent. Your application is set to require the user's privacy consent before the OneSignal SDK can be initialized. Please ensure the user has provided consent before calling this method. You can check the latest OneSignal consent status by calling OneSignal.userProvidedPrivacyConsent()");
        b(localI, localStringBuilder.toString());
      }
      return true;
    }
    return false;
  }
  
  /* Error */
  private static boolean a(String paramString, Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +198 -> 199
    //   4: ldc_w 1055
    //   7: aload_0
    //   8: invokevirtual 831	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   11: ifeq +5 -> 16
    //   14: iconst_0
    //   15: ireturn
    //   16: aload_1
    //   17: invokestatic 1201	com/onesignal/ao:a	(Landroid/content/Context;)Lcom/onesignal/ao;
    //   20: astore 4
    //   22: aconst_null
    //   23: astore 6
    //   25: aconst_null
    //   26: astore_1
    //   27: aload 4
    //   29: invokevirtual 1204	com/onesignal/ao:b	()Landroid/database/sqlite/SQLiteDatabase;
    //   32: ldc_w 1206
    //   35: iconst_1
    //   36: anewarray 541	java/lang/String
    //   39: dup
    //   40: iconst_0
    //   41: ldc_w 1208
    //   44: aastore
    //   45: ldc_w 1210
    //   48: iconst_1
    //   49: anewarray 541	java/lang/String
    //   52: dup
    //   53: iconst_0
    //   54: aload_0
    //   55: aastore
    //   56: aconst_null
    //   57: aconst_null
    //   58: aconst_null
    //   59: invokevirtual 1216	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   62: astore 4
    //   64: aload 4
    //   66: invokeinterface 1221 1 0
    //   71: istore_3
    //   72: iload_3
    //   73: istore_2
    //   74: aload 4
    //   76: ifnull +65 -> 141
    //   79: aload 4
    //   81: invokeinterface 1224 1 0
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
    //   116: getstatic 725	com/onesignal/am$i:c	Lcom/onesignal/am$i;
    //   119: ldc_w 1226
    //   122: aload 5
    //   124: invokestatic 681	com/onesignal/am:a	(Lcom/onesignal/am$i;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   127: aload 4
    //   129: ifnull +10 -> 139
    //   132: aload 4
    //   134: invokeinterface 1224 1 0
    //   139: iconst_0
    //   140: istore_2
    //   141: iload_2
    //   142: ifeq +43 -> 185
    //   145: getstatic 361	com/onesignal/am$i:f	Lcom/onesignal/am$i;
    //   148: astore_1
    //   149: new 341	java/lang/StringBuilder
    //   152: dup
    //   153: invokespecial 342	java/lang/StringBuilder:<init>	()V
    //   156: astore 4
    //   158: aload 4
    //   160: ldc_w 1228
    //   163: invokevirtual 348	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: pop
    //   167: aload 4
    //   169: aload_0
    //   170: invokevirtual 348	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: pop
    //   174: aload_1
    //   175: aload 4
    //   177: invokevirtual 355	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   180: invokestatic 366	com/onesignal/am:b	(Lcom/onesignal/am$i;Ljava/lang/String;)V
    //   183: iconst_1
    //   184: ireturn
    //   185: iconst_0
    //   186: ireturn
    //   187: aload_1
    //   188: ifnull +9 -> 197
    //   191: aload_1
    //   192: invokeinterface 1224 1 0
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
  
  static ag<Object, ak> b()
  {
    if (Y == null) {
      Y = new ag("onOSSubscriptionChanged", true);
    }
    return Y;
  }
  
  public static void b(int paramInt)
  {
    if (a("cancelNotification()")) {
      return;
    }
    Runnable local16 = new Runnable()
    {
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: getstatic 26	com/onesignal/am:b	Landroid/content/Context;
        //   3: invokestatic 31	com/onesignal/ao:a	(Landroid/content/Context;)Lcom/onesignal/ao;
        //   6: astore_1
        //   7: aload_1
        //   8: invokevirtual 34	com/onesignal/ao:a	()Landroid/database/sqlite/SQLiteDatabase;
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
        //   41: getfield 16	com/onesignal/am$16:a	I
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
        //   141: getstatic 26	com/onesignal/am:b	Landroid/content/Context;
        //   144: aload_2
        //   145: aload_0
        //   146: getfield 16	com/onesignal/am$16:a	I
        //   149: invokestatic 89	com/onesignal/x:a	(Landroid/content/Context;Landroid/database/sqlite/SQLiteDatabase;I)V
        //   152: aload_2
        //   153: astore_1
        //   154: aload_2
        //   155: getstatic 26	com/onesignal/am:b	Landroid/content/Context;
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
        //   195: getstatic 106	com/onesignal/am$i:c	Lcom/onesignal/am$i;
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
        //   226: getfield 16	com/onesignal/am$16:a	I
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
        //   253: invokestatic 113	com/onesignal/am:a	(Lcom/onesignal/am$i;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   256: aload_2
        //   257: ifnull +18 -> 275
        //   260: aload_2
        //   261: invokevirtual 100	android/database/sqlite/SQLiteDatabase:endTransaction	()V
        //   264: return
        //   265: astore_1
        //   266: getstatic 106	com/onesignal/am$i:c	Lcom/onesignal/am$i;
        //   269: ldc 115
        //   271: aload_1
        //   272: invokestatic 113	com/onesignal/am:a	(Lcom/onesignal/am$i;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   275: return
        //   276: aload_1
        //   277: ifnull +20 -> 297
        //   280: aload_1
        //   281: invokevirtual 100	android/database/sqlite/SQLiteDatabase:endTransaction	()V
        //   284: goto +13 -> 297
        //   287: astore_1
        //   288: getstatic 106	com/onesignal/am$i:c	Lcom/onesignal/am$i;
        //   291: ldc 115
        //   293: aload_1
        //   294: invokestatic 113	com/onesignal/am:a	(Lcom/onesignal/am$i;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   297: aload_2
        //   298: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	299	0	this	16
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
    if ((b != null) && (!N()))
    {
      local16.run();
      ((NotificationManager)b.getSystemService("notification")).cancel(paramInt);
      return;
    }
    i localI = i.c;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("OneSignal.init has not been called. Could not clear notification id: ");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" at this time - movingthis operation to a waiting task queue. The notification will still be canceledfrom NotificationManager at this time.");
    b(localI, localStringBuilder.toString());
    e.add(local16);
  }
  
  private static void b(Context paramContext, JSONArray paramJSONArray)
  {
    int i1 = 0;
    while (i1 < paramJSONArray.length())
    {
      try
      {
        String str = new JSONObject(paramJSONArray.getJSONObject(i1).optString("custom", null)).optString("i", null);
        if (!O.contains(str))
        {
          O.add(str);
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("app_id", l(paramContext));
          localJSONObject.put("player_id", m(paramContext));
          localJSONObject.put("opened", true);
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("notifications/");
          localStringBuilder.append(str);
          ar.a(localStringBuilder.toString(), localJSONObject, new ar.a()
          {
            void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
            {
              am.a("sending Notification Opened Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
            }
          });
        }
      }
      catch (Throwable localThrowable)
      {
        a(i.c, "Failed to generate JSON to send notification opened.", localThrowable);
      }
      i1 += 1;
    }
  }
  
  static void b(i paramI, String paramString)
  {
    a(paramI, paramString, null);
  }
  
  @Deprecated
  public static void b(String paramString)
  {
    if (a("SyncHashedEmail()")) {
      return;
    }
    if (!al.a(paramString)) {
      return;
    }
    paramString = new Runnable()
    {
      public void run()
      {
        as.a(this.a.trim().toLowerCase());
      }
    };
    if ((b != null) && (!N()))
    {
      paramString.run();
      return;
    }
    b(i.c, "You should initialize OneSignal before calling syncHashedEmail! Moving this operation to a pending task queue.");
    a(new m(paramString));
  }
  
  private static void b(String paramString1, int paramInt, Throwable paramThrowable, String paramString2)
  {
    Object localObject2 = "";
    Object localObject1 = localObject2;
    if (paramString2 != null)
    {
      localObject1 = localObject2;
      if (a(i.e))
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("\n");
        ((StringBuilder)localObject1).append(paramString2);
        ((StringBuilder)localObject1).append("\n");
        localObject1 = ((StringBuilder)localObject1).toString();
      }
    }
    paramString2 = i.d;
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
    N.add(paramJSONArray);
  }
  
  public static void b(boolean paramBoolean)
  {
    if ((k) && (!paramBoolean))
    {
      b(i.c, "Cannot change requiresUserPrivacyConsent() from TRUE to FALSE");
      return;
    }
    k = paramBoolean;
  }
  
  static boolean b(Context paramContext)
  {
    return aq.b(aq.a, "OS_FILTER_OTHER_GCM_RECEIVERS", false);
  }
  
  private static ad c(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i3 = paramJSONArray.length();
    ad localAd = new ad();
    aa localAa = new aa();
    localAa.a = u();
    localAa.b = paramBoolean1;
    localAa.c = paramJSONArray.optJSONObject(0).optInt("notificationId");
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
        localAa.d = q.a((JSONObject)localObject5);
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
        localObject3 = i.c;
        Object localObject5 = new StringBuilder();
        ((StringBuilder)localObject5).append("Error parsing JSON item ");
        ((StringBuilder)localObject5).append(i1);
        ((StringBuilder)localObject5).append("/");
        ((StringBuilder)localObject5).append(i3);
        ((StringBuilder)localObject5).append(" for callback.");
        a((i)localObject3, ((StringBuilder)localObject5).toString(), localThrowable);
        localObject2 = localObject4;
      }
      localObject4 = localObject3;
      if (localAa.f == null)
      {
        localObject4 = localObject3;
        localAa.f = new ArrayList();
      }
      localObject4 = localObject3;
      localAa.f.add(localAa.d);
      localObject1 = localObject3;
    }
    for (;;)
    {
      i1 += 1;
      break;
      localAd.a = localAa;
      localAd.b = new ab();
      localAd.b.b = ((String)localObject2);
      localObject3 = localAd.b;
      if (localObject2 != null) {
        paramJSONArray = ab.a.b;
      } else {
        paramJSONArray = ab.a.a;
      }
      ((ab)localObject3).a = paramJSONArray;
      if (paramBoolean2) {
        paramJSONArray = localAd.a;
      }
      for (Object localObject2 = aa.a.b;; localObject2 = aa.a.a)
      {
        paramJSONArray.e = ((aa.a)localObject2);
        return localAd;
        paramJSONArray = localAd.a;
      }
      label371:
      if (i2 == 0) {
        break label139;
      }
      i2 = 0;
      localObject2 = localObject3;
    }
  }
  
  public static a c()
  {
    if (i == null) {
      i = new a(null);
    }
    return i;
  }
  
  private static void c(f paramF)
  {
    if (paramF == null) {
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        bj.a localA = as.d(am.K() ^ true);
        if (localA.a) {
          am.n(true);
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
    if (a("setExternalId()")) {
      return;
    }
    paramString = new Runnable()
    {
      public void run()
      {
        try
        {
          as.b(this.a);
          return;
        }
        catch (JSONException localJSONException)
        {
          String str;
          if (this.a == "") {
            str = "remove";
          } else {
            str = "set";
          }
          am.i localI = am.i.c;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Attempted to ");
          localStringBuilder.append(str);
          localStringBuilder.append(" external ID but encountered a JSON exception");
          am.a(localI, localStringBuilder.toString());
          localJSONException.printStackTrace();
        }
      }
    };
    if ((b != null) && (!N()))
    {
      paramString.run();
      return;
    }
    a(new m(paramString));
  }
  
  private static void c(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject.put("net_type", G.d());
      return;
    }
    catch (Throwable paramJSONObject) {}
  }
  
  static void c(boolean paramBoolean)
  {
    aq.a(aq.a, "ONESIGNAL_USER_PROVIDED_CONSENT", paramBoolean);
  }
  
  static boolean c(Context paramContext)
  {
    return aq.b(aq.a, "GT_FIREBASE_TRACKING_ENABLED", false);
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
  
  private static void d(long paramLong)
  {
    if (f.get() == paramLong)
    {
      b(i.e, "Last Pending Task has ran, shutting down");
      d.shutdown();
    }
  }
  
  public static void d(String paramString)
  {
    a(paramString, null);
  }
  
  static void d(boolean paramBoolean)
  {
    if (b == null) {
      return;
    }
    aq.a(aq.a, "OS_FILTER_OTHER_GCM_RECEIVERS", paramBoolean);
  }
  
  public static boolean d()
  {
    return l();
  }
  
  static boolean d(Context paramContext)
  {
    return aq.b(aq.a, "GT_VIBRATE_ENABLED", true);
  }
  
  private static void e(long paramLong)
  {
    A = paramLong;
    if (b == null) {
      return;
    }
    i localI = i.e;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SaveUnsentActiveTime: ");
    localStringBuilder.append(A);
    b(localI, localStringBuilder.toString());
    aq.a(aq.a, "GT_UNSENT_ACTIVE_TIME", paramLong);
  }
  
  static void e(String paramString)
  {
    u = paramString;
    if (b == null) {
      return;
    }
    aq.a(aq.a, "GT_PLAYER_ID", u);
  }
  
  public static void e(boolean paramBoolean)
  {
    if (b == null) {
      return;
    }
    aq.a(aq.a, "GT_VIBRATE_ENABLED", paramBoolean);
  }
  
  static boolean e()
  {
    boolean bool2 = false;
    x = false;
    p.c();
    if (!c) {
      return false;
    }
    if (C != null) {
      C.a();
    }
    if (z == -1L) {
      return false;
    }
    long l1 = ((SystemClock.elapsedRealtime() - z) / 1000.0D + 0.5D);
    z = SystemClock.elapsedRealtime();
    boolean bool1 = bool2;
    if (l1 >= 0L)
    {
      if (l1 > 86400L) {
        return false;
      }
      if (b == null)
      {
        b(i.c, "Android Context not found, please call OneSignal.init when your app starts.");
        return false;
      }
      bool1 = f();
      a(System.currentTimeMillis());
      l1 = t() + l1;
      e(l1);
      if ((l1 >= 60L) && (m() != null))
      {
        if (!bool1) {
          at.a(b);
        }
        at.a();
        return false;
      }
      bool1 = bool2;
      if (l1 >= 60L) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private static boolean e(int paramInt)
  {
    return paramInt < -6;
  }
  
  static boolean e(Context paramContext)
  {
    return aq.b(aq.a, "GT_SOUND_ENABLED", true);
  }
  
  private static i f(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      if (paramInt < 0) {
        return i.a;
      }
      break;
    case 6: 
      return i.g;
    case 5: 
      return i.f;
    case 4: 
      return i.e;
    case 3: 
      return i.d;
    case 2: 
      return i.c;
    case 1: 
      return i.b;
    case 0: 
      return i.a;
    }
    return i.g;
  }
  
  static void f(String paramString)
  {
    v = paramString;
    if (b == null) {
      return;
    }
    String str = aq.a;
    if ("".equals(v)) {
      paramString = null;
    } else {
      paramString = v;
    }
    aq.a(str, "OS_EMAIL_ID", paramString);
  }
  
  public static void f(boolean paramBoolean)
  {
    if (b == null) {
      return;
    }
    aq.a(aq.a, "GT_SOUND_ENABLED", paramBoolean);
  }
  
  static boolean f()
  {
    boolean bool = as.c();
    if (bool) {
      at.a(b);
    }
    return (p.a(b)) || (bool);
  }
  
  private static l g(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      if (paramInt < 0) {
        return l.a;
      }
      break;
    case 2: 
      return l.c;
    case 1: 
      return l.b;
    case 0: 
      return l.a;
    }
    return l.c;
  }
  
  static void g()
  {
    x = true;
    p.c();
    z = SystemClock.elapsedRealtime();
    S = Y();
    a(System.currentTimeMillis());
    O();
    if (B != null) {
      B.a();
    }
    w.a(b);
    h(b).a();
    if ((D != null) && (c(b))) {
      D.b();
    }
    at.b(b);
  }
  
  static void g(String paramString)
  {
    e(paramString);
    j();
    c(P);
    i(b).a(paramString);
    if (aa != null)
    {
      a(aa.a, aa.b, aa.c);
      aa = null;
    }
    as.j();
    an.a(b, a, paramString, c.a());
  }
  
  public static void g(boolean paramBoolean)
  {
    if (a("setSubscription()")) {
      return;
    }
    Runnable local13 = new Runnable()
    {
      public void run()
      {
        am.f(am.b).a(this.a);
        as.b(this.a);
      }
    };
    if ((b != null) && (!N()))
    {
      local13.run();
      return;
    }
    b(i.c, "OneSignal.init has not been called. Moving subscription action to a waiting task queue.");
    a(new m(local13));
  }
  
  private static ah h(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (V == null)
    {
      V = new ah(false);
      V.a.b(new OSPermissionChangedInternalObserver());
    }
    return V;
  }
  
  static void h(String paramString)
  {
    f(paramString);
    j(b).a(paramString);
    try
    {
      as.b(new JSONObject().put("parent_player_id", paramString));
      return;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public static void h(boolean paramBoolean)
  {
    if (a("setLocationShared()")) {
      return;
    }
    h = paramBoolean;
    if (!paramBoolean) {
      as.d();
    }
    i localI = i.f;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("shareLocation:");
    localStringBuilder.append(h);
    b(localI, localStringBuilder.toString());
  }
  
  static boolean h()
  {
    return x;
  }
  
  private static OSSubscriptionState i(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (X == null)
    {
      X = new OSSubscriptionState(false, h(paramContext).b());
      h(paramContext).a.a(X);
      X.a.b(new OSSubscriptionChangedInternalObserver());
    }
    return X;
  }
  
  public static void i()
  {
    if (a("removeExternalUserId()")) {
      return;
    }
    c("");
  }
  
  private static z j(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (Z == null)
    {
      Z = new z(false);
      Z.a.b(new y());
    }
    return Z;
  }
  
  static void j()
  {
    if (y != null) {
      al.a(new Runnable()
      {
        public void run() {}
      });
    }
  }
  
  static String k()
  {
    return l(b);
  }
  
  private static void k(Context paramContext)
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
  
  private static void k(String paramString)
  {
    if (b == null) {
      return;
    }
    aq.a(aq.a, "GT_APP_ID", paramString);
  }
  
  private static String l(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return aq.b(aq.a, "GT_APP_ID", null);
  }
  
  static boolean l()
  {
    return aq.b(aq.a, "ONESIGNAL_USER_PROVIDED_CONSENT", false);
  }
  
  static String m()
  {
    if ((u == null) && (b != null)) {
      u = aq.b(aq.a, "GT_PLAYER_ID", null);
    }
    return u;
  }
  
  private static String m(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return aq.b(aq.a, "GT_PLAYER_ID", null);
  }
  
  private static long n(Context paramContext)
  {
    return aq.b(aq.a, "OS_LAST_SESSION_TIME", -31000L);
  }
  
  static String n()
  {
    if ("".equals(v)) {
      return null;
    }
    if ((v == null) && (b != null)) {
      v = aq.b(aq.a, "OS_EMAIL_ID", null);
    }
    return v;
  }
  
  static boolean o()
  {
    if (i == null) {
      return true;
    }
    return i.h == l.c;
  }
  
  static boolean p()
  {
    a localA = i;
    boolean bool = false;
    if (localA == null) {
      return false;
    }
    if (i.h == l.b) {
      bool = true;
    }
    return bool;
  }
  
  public static void q()
  {
    if (a("promptLocation()")) {
      return;
    }
    Runnable local14 = new Runnable()
    {
      public void run()
      {
        p.d local1 = new p.d()
        {
          public p.a a()
          {
            return p.a.b;
          }
          
          public void a(p.f paramAnonymous2F)
          {
            if (am.a("promptLocation()")) {
              return;
            }
            if (paramAnonymous2F != null) {
              as.a(paramAnonymous2F);
            }
          }
        };
        p.a(am.b, true, local1);
        am.o(true);
      }
    };
    if ((b != null) && (!N()))
    {
      local14.run();
      return;
    }
    b(i.c, "OneSignal.init has not been called. Could not prompt for location at this time - moving this operation to awaiting queue.");
    a(new m(local14));
  }
  
  public static void r()
  {
    if (a("clearOneSignalNotifications()")) {
      return;
    }
    Runnable local15 = new Runnable()
    {
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: getstatic 22	com/onesignal/am:b	Landroid/content/Context;
        //   3: ldc 24
        //   5: invokevirtual 30	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
        //   8: checkcast 32	android/app/NotificationManager
        //   11: astore 8
        //   13: getstatic 22	com/onesignal/am:b	Landroid/content/Context;
        //   16: invokestatic 38	com/onesignal/ao:a	(Landroid/content/Context;)Lcom/onesignal/ao;
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
        //   33: invokevirtual 41	com/onesignal/ao:b	()Landroid/database/sqlite/SQLiteDatabase;
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
        //   106: invokevirtual 76	com/onesignal/ao:a	()Landroid/database/sqlite/SQLiteDatabase;
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
        //   159: getstatic 112	com/onesignal/am$i:c	Lcom/onesignal/am$i;
        //   162: astore_3
        //   163: aload_3
        //   164: ldc 114
        //   166: aload_2
        //   167: invokestatic 117	com/onesignal/am:a	(Lcom/onesignal/am$i;Ljava/lang/String;Ljava/lang/Throwable;)V
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
        //   201: getstatic 112	com/onesignal/am$i:c	Lcom/onesignal/am$i;
        //   204: ldc 119
        //   206: aload 5
        //   208: invokestatic 117	com/onesignal/am:a	(Lcom/onesignal/am$i;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   211: aload_3
        //   212: ifnull +18 -> 230
        //   215: aload_3
        //   216: invokevirtual 106	android/database/sqlite/SQLiteDatabase:endTransaction	()V
        //   219: goto +11 -> 230
        //   222: astore_2
        //   223: getstatic 112	com/onesignal/am$i:c	Lcom/onesignal/am$i;
        //   226: astore_3
        //   227: goto -64 -> 163
        //   230: iconst_0
        //   231: getstatic 22	com/onesignal/am:b	Landroid/content/Context;
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
        //   262: getstatic 112	com/onesignal/am$i:c	Lcom/onesignal/am$i;
        //   265: ldc 114
        //   267: aload_3
        //   268: invokestatic 117	com/onesignal/am:a	(Lcom/onesignal/am$i;Ljava/lang/String;Ljava/lang/Throwable;)V
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
        //   304: getstatic 112	com/onesignal/am$i:c	Lcom/onesignal/am$i;
        //   307: ldc -127
        //   309: aload 4
        //   311: invokestatic 117	com/onesignal/am:a	(Lcom/onesignal/am$i;Ljava/lang/String;Ljava/lang/Throwable;)V
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
        //   0	337	0	this	15
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
        //   19	86	7	localAo	ao
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
    if ((b != null) && (!N()))
    {
      local15.run();
      return;
    }
    b(i.c, "OneSignal.init has not been called. Could not clear notifications at this time - moving this operation toa waiting task queue.");
    a(new m(local15));
  }
  
  public static aj s()
  {
    if (a("getPermissionSubscriptionState()")) {
      return null;
    }
    if (b == null)
    {
      b(i.c, "OneSignal.init has not been called. Could not get OSPermissionSubscriptionState");
      return null;
    }
    aj localAj = new aj();
    localAj.a = i(b);
    localAj.b = h(b);
    localAj.c = j(b);
    return localAj;
  }
  
  static long t()
  {
    if ((A == -1L) && (b != null)) {
      A = aq.b(aq.a, "GT_UNSENT_ACTIVE_TIME", 0L);
    }
    i localI = i.e;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("GetUnsentActiveTime: ");
    localStringBuilder.append(A);
    b(localI, localStringBuilder.toString());
    return A;
  }
  
  static boolean u()
  {
    return (c) && (h());
  }
  
  static void v()
  {
    S = false;
    a(System.currentTimeMillis());
  }
  
  static boolean w()
  {
    if (i.e) {
      return al.a(b);
    }
    return true;
  }
  
  static void x()
  {
    if (p != null)
    {
      p.a();
      p = null;
    }
  }
  
  static void y()
  {
    if (p != null)
    {
      p.a(new d(c.d, "Failed due to network failure. Will retry on next sync."));
      p = null;
    }
  }
  
  static void z()
  {
    if (o != null)
    {
      o.a();
      o = null;
    }
  }
  
  public static class a
  {
    am.j a;
    am.k b;
    boolean c;
    boolean d;
    boolean e;
    boolean f;
    boolean g;
    am.l h = am.l.b;
    
    private a() {}
  }
  
  public static abstract interface b
  {
    public abstract void a(am.o paramO);
    
    public abstract void a(JSONObject paramJSONObject);
  }
  
  public static enum c
  {
    private c() {}
  }
  
  public static class d
  {
    private am.c a;
    private String b;
    
    d(am.c paramC, String paramString)
    {
      this.a = paramC;
      this.b = paramString;
    }
    
    public String a()
    {
      return this.b;
    }
  }
  
  public static abstract interface e
  {
    public abstract void a();
    
    public abstract void a(am.d paramD);
  }
  
  public static abstract interface f
  {
    public abstract void a(JSONObject paramJSONObject);
  }
  
  private static class g
  {
    JSONArray a;
    boolean b;
    ar.a c;
    
    g(JSONArray paramJSONArray)
    {
      this.a = paramJSONArray;
    }
  }
  
  public static abstract interface h
  {
    public abstract void a(String paramString1, String paramString2);
  }
  
  public static enum i
  {
    private i() {}
  }
  
  public static abstract interface j
  {
    public abstract void notificationOpened(ad paramAd);
  }
  
  public static abstract interface k
  {
    public abstract void notificationReceived(aa paramAa);
  }
  
  public static enum l
  {
    private l() {}
  }
  
  private static class m
    implements Runnable
  {
    private Runnable a;
    private long b;
    
    m(Runnable paramRunnable)
    {
      this.a = paramRunnable;
    }
    
    public void run()
    {
      this.a.run();
      am.b(this.b);
    }
  }
  
  public static abstract interface n
  {
    public abstract void a(JSONObject paramJSONObject);
    
    public abstract void b(JSONObject paramJSONObject);
  }
  
  public static class o
  {
    private String a;
    private int b;
    
    o(int paramInt, String paramString)
    {
      this.a = paramString;
      this.b = paramInt;
    }
  }
}
