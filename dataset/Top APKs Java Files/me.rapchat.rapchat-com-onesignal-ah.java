package com.onesignal;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Application;
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
  private static au A;
  private static av B;
  private static d C = new c();
  private static int D = 0;
  private static ag E;
  private static String F;
  private static boolean G = false;
  private static boolean H = false;
  private static boolean I = false;
  private static boolean J = false;
  private static n.f K;
  private static Collection<JSONArray> L = new ArrayList();
  private static HashSet<String> M = new HashSet();
  private static e N;
  private static boolean O = false;
  private static boolean P = false;
  private static boolean Q = false;
  private static JSONObject R;
  private static boolean S = false;
  private static ad T;
  private static ac<Object, ae> U;
  private static OSSubscriptionState V;
  private static ac<Object, af> W;
  private static v X;
  private static f Y;
  private static int Z = 0;
  static String a;
  static Context b;
  static boolean c = false;
  static ExecutorService d;
  public static ConcurrentLinkedQueue<Runnable> e;
  static AtomicLong f;
  public static String g = "native";
  static boolean h = true;
  static a i;
  static boolean j = false;
  static ad k;
  static OSSubscriptionState l;
  private static d m;
  private static d n;
  private static String o;
  private static boolean p = false;
  private static h q = h.a;
  private static h r = h.d;
  private static String s;
  private static String t;
  private static int u = 0;
  private static boolean v = false;
  private static g w;
  private static long x = 1L;
  private static long y = -1L;
  private static aw z;
  
  static
  {
    e = new ConcurrentLinkedQueue();
    f = new AtomicLong();
  }
  
  public ah() {}
  
  private static void E()
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
  
  private static boolean F()
  {
    if ((c) && (d == null)) {
      return false;
    }
    if ((!c) && (d == null)) {
      return true;
    }
    return (d != null) && (!d.isShutdown());
  }
  
  private static void G()
  {
    if (P) {
      return;
    }
    boolean bool2 = true;
    P = true;
    G = false;
    if (Q) {
      H = false;
    }
    H();
    J();
    boolean bool1 = bool2;
    if (!J) {
      if (i.d) {
        bool1 = bool2;
      } else {
        bool1 = false;
      }
    }
    J = bool1;
  }
  
  private static void H()
  {
    n.d local8 = new n.d()
    {
      public n.a a()
      {
        return n.a.a;
      }
      
      public void a(n.f paramAnonymousF)
      {
        ah.a(paramAnonymousF);
        ah.b(true);
        ah.u();
      }
    };
    boolean bool;
    if ((i.d) && (!J)) {
      bool = true;
    } else {
      bool = false;
    }
    n.a(b, bool, local8);
  }
  
  private static void I()
  {
    Object localObject;
    if (D == 2) {
      localObject = new aq();
    } else {
      localObject = new ar();
    }
    ((ap)localObject).a(b, o, new ap.a()
    {
      public void a(String paramAnonymousString, int paramAnonymousInt)
      {
        if (paramAnonymousInt < 1)
        {
          if ((an.g() == null) && ((ah.v() == 1) || (ah.v() < -6))) {
            ah.a(paramAnonymousInt);
          }
        }
        else if (ah.v() < -6) {
          ah.a(paramAnonymousInt);
        }
        ah.f(paramAnonymousString);
        ah.c(true);
        ah.f(ah.b).b(paramAnonymousString);
        ah.u();
      }
    });
  }
  
  private static void J()
  {
    if (I)
    {
      I();
      return;
    }
    am.a local10 = new am.a()
    {
      void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        new Thread(new Runnable()
        {
          public void run()
          {
            try
            {
              int j = ah.w() * 10000 + 30000;
              int i = j;
              if (j > 90000) {
                i = 90000;
              }
              ah.h localH = ah.h.e;
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("Failed to get Android parameters, trying again in ");
              localStringBuilder.append(i / 1000);
              localStringBuilder.append(" seconds.");
              ah.a(localH, localStringBuilder.toString());
              Thread.sleep(i);
            }
            catch (Throwable localThrowable)
            {
              for (;;) {}
            }
            ah.x();
            ah.y();
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
            ah.d(true);
            ah.g(paramAnonymousString.getString("android_sender_id"));
          }
          ah.j = paramAnonymousString.optBoolean("enterp", false);
          ah.e(paramAnonymousString.optBoolean("use_email_auth", false));
          ah.b(paramAnonymousString.getJSONObject("awl_list"));
          boolean bool = paramAnonymousString.optBoolean("fba", false);
          al.a(al.a, "GT_FIREBASE_TRACKING_ENABLED", bool);
          p.a(ah.b, paramAnonymousString);
        }
        catch (Throwable paramAnonymousString)
        {
          paramAnonymousString.printStackTrace();
        }
        ah.f(true);
        ah.z();
      }
    };
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("apps/");
    ((StringBuilder)localObject).append(a);
    ((StringBuilder)localObject).append("/android_params.js");
    String str1 = ((StringBuilder)localObject).toString();
    String str2 = i();
    localObject = str1;
    if (str2 != null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(str1);
      ((StringBuilder)localObject).append("?player_id=");
      ((StringBuilder)localObject).append(str2);
      localObject = ((StringBuilder)localObject).toString();
    }
    a(h.f, "Starting request to get Android parameters.");
    am.a((String)localObject, local10);
  }
  
  private static void K()
  {
    Iterator localIterator = L.iterator();
    while (localIterator.hasNext()) {
      b((JSONArray)localIterator.next(), true, false);
    }
    L.clear();
  }
  
  private static int L()
  {
    TimeZone localTimeZone = Calendar.getInstance().getTimeZone();
    int i2 = localTimeZone.getRawOffset();
    int i1 = i2;
    if (localTimeZone.inDaylightTime(new Date())) {
      i1 = i2 + localTimeZone.getDSTSavings();
    }
    return i1 / 1000;
  }
  
  private static void M()
  {
    h localH = h.f;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("registerUser: registerForPushFired:");
    localStringBuilder.append(G);
    localStringBuilder.append(", locationFired: ");
    localStringBuilder.append(H);
    localStringBuilder.append(", awlFired: ");
    localStringBuilder.append(I);
    a(localH, localStringBuilder.toString());
    if ((G) && (H))
    {
      if (!I) {
        return;
      }
      new Thread(new Runnable()
      {
        public void run()
        {
          try
          {
            ah.A();
            ai.a(ah.b, ah.a, ah.B(), c.a());
            return;
          }
          catch (JSONException localJSONException)
          {
            ah.a(ah.h.b, "FATAL Error registering device!", localJSONException);
          }
        }
      }, "OS_REG_USER").start();
      return;
    }
  }
  
  private static void N()
    throws JSONException
  {
    Object localObject2 = b.getPackageName();
    Object localObject1 = b.getPackageManager();
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("app_id", a);
    Object localObject3 = C.a(b);
    if (localObject3 != null) {
      localJSONObject.put("ad_id", localObject3);
    }
    localJSONObject.put("device_os", Build.VERSION.RELEASE);
    localJSONObject.put("timezone", L());
    localJSONObject.put("language", ag.d());
    localJSONObject.put("sdk", "030803");
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
        if (R.has(str)) {
          ((JSONArray)localObject2).put(str);
        }
      }
      else
      {
        localJSONObject.put("pkgs", localObject2);
        localJSONObject.put("net_type", E.b());
        localJSONObject.put("carrier", E.c());
        localJSONObject.put("rooted", at.a());
        an.b(localJSONObject);
        localJSONObject = new JSONObject();
        localJSONObject.put("identifier", F);
        localJSONObject.put("subscribableStatus", u);
        localJSONObject.put("androidPermission", p());
        localJSONObject.put("device_type", D);
        an.c(localJSONObject);
        if ((h) && (K != null)) {
          an.a(K);
        }
        if (Q) {
          an.j();
        }
        P = false;
        return;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
  }
  
  private static void O()
  {
    if (w != null) {
      ag.a(new Runnable()
      {
        public void run() {}
      });
    }
  }
  
  private static void P()
  {
    try
    {
      Object localObject1 = w;
      if (localObject1 == null) {
        return;
      }
      localObject1 = an.g();
      if (!an.f()) {
        localObject1 = null;
      }
      String str = i();
      if (str == null) {
        return;
      }
      w.a(str, (String)localObject1);
      if (localObject1 != null) {
        w = null;
      }
      return;
    }
    finally {}
  }
  
  private static boolean Q()
  {
    if (Q) {
      return true;
    }
    return (System.currentTimeMillis() - n(b)) / 1000L >= 30L;
  }
  
  static ac<Object, ae> a()
  {
    if (U == null) {
      U = new ac("onOSPermissionChanged", true);
    }
    return U;
  }
  
  public static a a(Context paramContext)
  {
    return new a(paramContext, null);
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
        a(h.f, "Not a OneSignal formatted GCM message. No 'i' field in custom.");
        return null;
      }
      a(h.f, "Not a OneSignal formatted GCM message. No 'custom' field in the bundle.");
      return null;
    }
    catch (Throwable paramBundle)
    {
      a(h.f, "Could not parse bundle, probably not a OneSignal notification.", paramBundle);
    }
    return null;
  }
  
  static void a(long paramLong)
  {
    al.a(al.a, "OS_LAST_SESSION_TIME", paramLong);
  }
  
  static void a(long paramLong, boolean paramBoolean)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject().put("app_id", a).put("type", 1).put("state", "ping").put("active_time", paramLong);
      c(localJSONObject);
      a(i(), localJSONObject, paramBoolean);
      String str = j();
      if (str != null)
      {
        a(str, localJSONObject, paramBoolean);
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      a(h.c, "Generating on_focus:JSON Failed.", localThrowable);
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, i paramI, j paramJ)
  {
    i = c();
    i.h = false;
    i.b = paramI;
    i.c = paramJ;
    if (!p) {
      o = paramString1;
    }
    E = new ag();
    D = E.a();
    u = E.a(paramContext, D, paramString2);
    if (u == 64537) {
      return;
    }
    if (c)
    {
      if (paramContext != null) {
        b = paramContext.getApplicationContext();
      }
      if (i.b != null) {
        K();
      }
      return;
    }
    boolean bool = paramContext instanceof Activity;
    v = bool;
    a = paramString2;
    b = paramContext.getApplicationContext();
    a(i.g);
    if (bool)
    {
      a.b = (Activity)paramContext;
      s.a(b);
    }
    else
    {
      a.a = true;
    }
    x = SystemClock.elapsedRealtime();
    an.d();
    ((Application)b).registerActivityLifecycleCallbacks(new b());
    try
    {
      Class.forName("com.amazon.device.iap.PurchasingListener");
      A = new au(b);
      paramContext = h();
      if (paramContext != null)
      {
        if (!paramContext.equals(a))
        {
          a(h.f, "APP ID changed, clearing user id as it is no longer valid.");
          h(a);
          an.h();
        }
      }
      else
      {
        f.a(0, b);
        h(a);
      }
      OSPermissionChangedInternalObserver.a(h(b));
      if ((v) || (i() == null))
      {
        Q = Q();
        a(System.currentTimeMillis());
        G();
      }
      if (i.b != null) {
        K();
      }
      if (aw.a(b)) {
        z = new aw(b);
      }
      if (av.a()) {
        B = new av(b);
      }
      c = true;
      E();
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
    if ((B != null) && (c(b))) {
      B.a(c(paramJSONArray, true, paramBoolean));
    }
    boolean bool1 = false;
    boolean bool2 = "DISABLE".equals(ag.a(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
    if (!bool2) {
      bool1 = a(paramContext, paramJSONArray);
    }
    b(paramJSONArray, true, paramBoolean);
    if ((!paramBoolean) && (!bool1) && (!bool2)) {
      k(paramContext);
    }
  }
  
  private static void a(e paramE)
  {
    if (paramE == null) {
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        bc.a localA = an.c(ah.C() ^ true);
        if (localA.a) {
          ah.g(true);
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
  
  public static void a(g paramG)
  {
    w = paramG;
    paramG = new Runnable()
    {
      public void run()
      {
        if (ah.i() != null) {
          ag.a(new Runnable()
          {
            public void run() {}
          });
        }
      }
    };
    if ((b != null) && (!F()))
    {
      paramG.run();
      return;
    }
    a(h.c, "You must initialize OneSignal before getting tags! Moving this tag operation to a pending queue.");
    a(new l(paramG));
  }
  
  static void a(h paramH, String paramString)
  {
    a(paramH, paramString, null);
  }
  
  static void a(h paramH, final String paramString, Throwable paramThrowable)
  {
    if (paramH.compareTo(r) < 1) {
      if (paramH == h.g) {
        Log.v("OneSignal", paramString, paramThrowable);
      } else if (paramH == h.f) {
        Log.d("OneSignal", paramString, paramThrowable);
      } else if (paramH == h.e) {
        Log.i("OneSignal", paramString, paramThrowable);
      } else if (paramH == h.d) {
        Log.w("OneSignal", paramString, paramThrowable);
      } else if ((paramH == h.c) || (paramH == h.b)) {
        Log.e("OneSignal", paramString, paramThrowable);
      }
    }
    if ((paramH.compareTo(q) < 1) && (a.b != null)) {
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
      catch (Throwable paramH)
      {
        Log.e("OneSignal", "Error showing logging message.", paramH);
      }
    }
  }
  
  private static void a(l paramL)
  {
    l.a(paramL, f.incrementAndGet());
    h localH;
    StringBuilder localStringBuilder;
    if (d == null)
    {
      localH = h.e;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Adding a task to the pending queue with ID: ");
      localStringBuilder.append(l.a(paramL));
      a(localH, localStringBuilder.toString());
      e.add(paramL);
      return;
    }
    if (!d.isShutdown())
    {
      localH = h.e;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Executor is still running, add to the executor with ID: ");
      localStringBuilder.append(l.a(paramL));
      a(localH, localStringBuilder.toString());
      d.submit(paramL);
    }
  }
  
  private static void a(z paramZ)
  {
    ag.a(new Runnable()
    {
      public void run()
      {
        ah.i.b.a(this.a);
      }
    });
  }
  
  public static void a(String paramString)
  {
    a(paramString, null, null);
  }
  
  public static void a(String paramString1, final String paramString2, d paramD)
  {
    if (!ag.a(paramString1))
    {
      if (paramD != null) {
        paramD.a(new c(b.a, "Email is invalid"));
      }
      a(h.c, "Email is invalid");
      return;
    }
    if ((S) && (paramString2 == null))
    {
      if (paramD != null) {
        paramD.a(new c(b.b, "Email authentication (auth token) is set to REQUIRED for this application. Please provide an auth token from your backend server or change the setting in the OneSignal dashboard."));
      }
      a(h.c, "Email authentication (auth token) is set to REQUIRED for this application. Please provide an auth token from your backend server or change the setting in the OneSignal dashboard.");
      return;
    }
    m = paramD;
    paramString1 = new Runnable()
    {
      public void run()
      {
        String str1 = this.a.trim();
        String str2 = paramString2;
        if (str2 != null) {
          str2.toLowerCase();
        }
        ah.g(ah.b).b(str1);
        an.a(str1.toLowerCase(), str2);
      }
    };
    if ((b != null) && (!F()))
    {
      paramString1.run();
      return;
    }
    a(h.c, "You should initialize OneSignal before calling setEmail! Moving this operation to a pending task queue.");
    a(new l(paramString1));
  }
  
  private static void a(String paramString, JSONObject paramJSONObject, boolean paramBoolean)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("players/");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append("/on_focus");
    paramString = ((StringBuilder)localObject).toString();
    localObject = new am.a()
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
      am.d(paramString, paramJSONObject, (am.a)localObject);
      return;
    }
    am.b(paramString, paramJSONObject, (am.a)localObject);
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean, am.a paramA)
  {
    if (i() == null)
    {
      Y = new f(paramJSONArray);
      Y.b = paramBoolean;
      Y.c = paramA;
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
      paramJSONArray.append(i());
      paramJSONArray.append("/on_purchase");
      am.b(paramJSONArray.toString(), localJSONObject, paramA);
      if (j() != null)
      {
        paramJSONArray = new StringBuilder();
        paramJSONArray.append("players/");
        paramJSONArray.append(j());
        paramJSONArray.append("/on_purchase");
        am.b(paramJSONArray.toString(), localJSONObject, null);
        return;
      }
    }
    catch (Throwable paramJSONArray)
    {
      a(h.c, "Failed to generate JSON for sendPurchases.", paramJSONArray);
    }
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramJSONArray = c(paramJSONArray, paramBoolean1, paramBoolean2);
    if ((B != null) && (c(b))) {
      B.b(paramJSONArray);
    }
    if (i != null)
    {
      if (i.c == null) {
        return;
      }
      i.c.a(paramJSONArray.a);
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
        JSONObject localJSONObject1 = an.c(false).b;
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
            localObject = ah.h.c;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("Omitting key '");
            localStringBuilder.append(str);
            localStringBuilder.append("'! sendTags DO NOT supported nested values!");
            ah.a((ah.h)localObject, localStringBuilder.toString());
          }
          catch (Throwable localThrowable) {}
          if (!localJSONObject2.toString().equals("{}")) {
            an.a(localJSONObject2);
          }
          return;
        }
      }
    };
    if ((b != null) && (!F()))
    {
      paramJSONObject.run();
      return;
    }
    a(h.c, "You must initialize OneSignal before modifying tags!Moving this operation to a pending task queue.");
    a(new l(paramJSONObject));
  }
  
  static void a(boolean paramBoolean)
  {
    if (b == null) {
      return;
    }
    al.a(al.a, "OS_FILTER_OTHER_GCM_RECEIVERS", paramBoolean);
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
        Object localObject2 = h.c;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Error parsing JSON item ");
        localStringBuilder.append(i1);
        localStringBuilder.append("/");
        localStringBuilder.append(i2);
        localStringBuilder.append(" for launching a web URL.");
        a((h)localObject2, localStringBuilder.toString(), localThrowable);
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
  
  private static boolean a(h paramH)
  {
    int i1 = paramH.compareTo(q);
    boolean bool = true;
    if (i1 >= 1)
    {
      if (paramH.compareTo(r) < 1) {
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
    //   4: ldc_w 1047
    //   7: aload_0
    //   8: invokevirtual 751	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   11: ifeq +5 -> 16
    //   14: iconst_0
    //   15: ireturn
    //   16: aload_1
    //   17: invokestatic 1052	com/onesignal/aj:a	(Landroid/content/Context;)Lcom/onesignal/aj;
    //   20: astore 4
    //   22: aconst_null
    //   23: astore 6
    //   25: aconst_null
    //   26: astore_1
    //   27: aload 4
    //   29: invokevirtual 1055	com/onesignal/aj:b	()Landroid/database/sqlite/SQLiteDatabase;
    //   32: ldc_w 1057
    //   35: iconst_1
    //   36: anewarray 482	java/lang/String
    //   39: dup
    //   40: iconst_0
    //   41: ldc_w 1059
    //   44: aastore
    //   45: ldc_w 1061
    //   48: iconst_1
    //   49: anewarray 482	java/lang/String
    //   52: dup
    //   53: iconst_0
    //   54: aload_0
    //   55: aastore
    //   56: aconst_null
    //   57: aconst_null
    //   58: aconst_null
    //   59: invokevirtual 1067	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   62: astore 4
    //   64: aload 4
    //   66: invokeinterface 1072 1 0
    //   71: istore_3
    //   72: iload_3
    //   73: istore_2
    //   74: aload 4
    //   76: ifnull +65 -> 141
    //   79: aload 4
    //   81: invokeinterface 1075 1 0
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
    //   116: getstatic 663	com/onesignal/ah$h:c	Lcom/onesignal/ah$h;
    //   119: ldc_w 1077
    //   122: aload 5
    //   124: invokestatic 634	com/onesignal/ah:a	(Lcom/onesignal/ah$h;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   127: aload 4
    //   129: ifnull +10 -> 139
    //   132: aload 4
    //   134: invokeinterface 1075 1 0
    //   139: iconst_0
    //   140: istore_2
    //   141: iload_2
    //   142: ifeq +43 -> 185
    //   145: getstatic 299	com/onesignal/ah$h:f	Lcom/onesignal/ah$h;
    //   148: astore_1
    //   149: new 279	java/lang/StringBuilder
    //   152: dup
    //   153: invokespecial 280	java/lang/StringBuilder:<init>	()V
    //   156: astore 4
    //   158: aload 4
    //   160: ldc_w 1079
    //   163: invokevirtual 286	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: pop
    //   167: aload 4
    //   169: aload_0
    //   170: invokevirtual 286	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: pop
    //   174: aload_1
    //   175: aload 4
    //   177: invokevirtual 293	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   180: invokestatic 304	com/onesignal/ah:a	(Lcom/onesignal/ah$h;Ljava/lang/String;)V
    //   183: iconst_1
    //   184: ireturn
    //   185: iconst_0
    //   186: ireturn
    //   187: aload_1
    //   188: ifnull +9 -> 197
    //   191: aload_1
    //   192: invokeinterface 1075 1 0
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
  
  static ac<Object, af> b()
  {
    if (W == null) {
      W = new ac("onOSSubscriptionChanged", true);
    }
    return W;
  }
  
  private static void b(Context paramContext, JSONArray paramJSONArray)
  {
    int i1 = 0;
    while (i1 < paramJSONArray.length())
    {
      try
      {
        String str = new JSONObject(paramJSONArray.getJSONObject(i1).optString("custom", null)).optString("i", null);
        if (!M.contains(str))
        {
          M.add(str);
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("app_id", l(paramContext));
          localJSONObject.put("player_id", m(paramContext));
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
        a(h.c, "Failed to generate JSON to send notification opened.", localThrowable);
      }
      i1 += 1;
    }
  }
  
  private static void b(a paramA)
  {
    if (c().h) {
      paramA.i = c().i;
    }
    i = paramA;
    Context localContext = i.a;
    i.a = null;
    try
    {
      Bundle localBundle = localContext.getPackageManager().getApplicationInfo(localContext.getPackageName(), 128).metaData;
      String str = localBundle.getString("onesignal_google_project_number");
      paramA = str;
      if (str != null)
      {
        paramA = str;
        if (str.length() > 4) {
          paramA = str.substring(4);
        }
      }
      a(localContext, paramA, localBundle.getString("onesignal_app_id"), i.b, i.c);
      return;
    }
    catch (Throwable paramA)
    {
      paramA.printStackTrace();
    }
  }
  
  static void b(String paramString)
  {
    s = paramString;
    if (b == null) {
      return;
    }
    al.a(al.a, "GT_PLAYER_ID", s);
  }
  
  private static void b(String paramString1, int paramInt, Throwable paramThrowable, String paramString2)
  {
    Object localObject2 = "";
    Object localObject1 = localObject2;
    if (paramString2 != null)
    {
      localObject1 = localObject2;
      if (a(h.e))
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("\n");
        ((StringBuilder)localObject1).append(paramString2);
        ((StringBuilder)localObject1).append("\n");
        localObject1 = ((StringBuilder)localObject1).toString();
      }
    }
    paramString2 = h.d;
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
    if ((i != null) && (i.b != null))
    {
      a(c(paramJSONArray, paramBoolean1, paramBoolean2));
      return;
    }
    L.add(paramJSONArray);
  }
  
  static boolean b(Context paramContext)
  {
    return al.b(al.a, "OS_FILTER_OTHER_GCM_RECEIVERS", false);
  }
  
  public static a c()
  {
    if (i == null) {
      i = new a(null);
    }
    return i;
  }
  
  private static z c(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i3 = paramJSONArray.length();
    z localZ = new z();
    w localW = new w();
    localW.a = n();
    localW.b = paramBoolean1;
    localW.c = paramJSONArray.optJSONObject(0).optInt("notificationId");
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
        localW.d = o.a((JSONObject)localObject5);
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
        localObject3 = h.c;
        Object localObject5 = new StringBuilder();
        ((StringBuilder)localObject5).append("Error parsing JSON item ");
        ((StringBuilder)localObject5).append(i1);
        ((StringBuilder)localObject5).append("/");
        ((StringBuilder)localObject5).append(i3);
        ((StringBuilder)localObject5).append(" for callback.");
        a((h)localObject3, ((StringBuilder)localObject5).toString(), localThrowable);
        localObject2 = localObject4;
      }
      localObject4 = localObject3;
      if (localW.f == null)
      {
        localObject4 = localObject3;
        localW.f = new ArrayList();
      }
      localObject4 = localObject3;
      localW.f.add(localW.d);
      localObject1 = localObject3;
    }
    for (;;)
    {
      i1 += 1;
      break;
      localZ.a = localW;
      localZ.b = new x();
      localZ.b.b = localObject2;
      localObject3 = localZ.b;
      if (localObject2 != null) {
        paramJSONArray = x.a.b;
      } else {
        paramJSONArray = x.a.a;
      }
      ((x)localObject3).a = paramJSONArray;
      if (paramBoolean2)
      {
        localZ.a.e = w.a.b;
        return localZ;
      }
      localZ.a.e = w.a.a;
      return localZ;
      label365:
      if (i2 == 0) {
        break label139;
      }
      i2 = 0;
      Object localObject2 = localObject3;
    }
  }
  
  static void c(String paramString)
  {
    t = paramString;
    if (b == null) {
      return;
    }
    String str = al.a;
    if ("".equals(t)) {
      paramString = null;
    } else {
      paramString = t;
    }
    al.a(str, "OS_EMAIL_ID", paramString);
  }
  
  private static void c(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject.put("net_type", E.b());
      return;
    }
    catch (Throwable paramJSONObject) {}
  }
  
  static boolean c(Context paramContext)
  {
    return al.b(al.a, "GT_FIREBASE_TRACKING_ENABLED", false);
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
      a(h.e, "Last Pending Task has ran, shutting down");
      d.shutdown();
    }
  }
  
  static void d(String paramString)
  {
    b(paramString);
    O();
    a(N);
    i(b).a(paramString);
    if (Y != null)
    {
      a(Y.a, Y.b, Y.c);
      Y = null;
    }
    an.i();
    ai.a(b, a, paramString, c.a());
  }
  
  static boolean d()
  {
    boolean bool1 = false;
    v = false;
    if (!c) {
      return false;
    }
    if (A != null) {
      A.a();
    }
    if (x == -1L) {
      return false;
    }
    long l1 = ((SystemClock.elapsedRealtime() - x) / 1000.0D + 0.5D);
    x = SystemClock.elapsedRealtime();
    if (l1 >= 0L)
    {
      if (l1 > 86400L) {
        return false;
      }
      if (b == null)
      {
        a(h.c, "Android Context not found, please call OneSignal.init when your app starts.");
        return false;
      }
      boolean bool2 = e();
      a(System.currentTimeMillis());
      l1 = m() + l1;
      e(l1);
      if ((l1 >= 60L) && (i() != null))
      {
        if (!bool2) {
          ao.a(b);
        }
        ao.a();
        return false;
      }
      if (l1 >= 60L) {
        bool1 = true;
      }
      return bool1;
    }
    return false;
  }
  
  static boolean d(Context paramContext)
  {
    return al.b(al.a, "GT_VIBRATE_ENABLED", true);
  }
  
  private static void e(long paramLong)
  {
    y = paramLong;
    if (b == null) {
      return;
    }
    h localH = h.e;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SaveUnsentActiveTime: ");
    localStringBuilder.append(y);
    a(localH, localStringBuilder.toString());
    al.a(al.a, "GT_UNSENT_ACTIVE_TIME", paramLong);
  }
  
  static void e(String paramString)
  {
    c(paramString);
    j(b).a(paramString);
    try
    {
      an.c(new JSONObject().put("parent_player_id", paramString));
      return;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  static boolean e()
  {
    boolean bool = an.c();
    if (bool) {
      ao.a(b);
    }
    return (n.a(b)) || (bool);
  }
  
  static boolean e(Context paramContext)
  {
    return al.b(al.a, "GT_SOUND_ENABLED", true);
  }
  
  static void f()
  {
    v = true;
    x = SystemClock.elapsedRealtime();
    Q = Q();
    a(System.currentTimeMillis());
    G();
    if (z != null) {
      z.a();
    }
    s.a(b);
    h(b).a();
    if ((B != null) && (c(b))) {
      B.b();
    }
    ao.b(b);
  }
  
  static boolean g()
  {
    return v;
  }
  
  private static ad h(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (T == null)
    {
      T = new ad(false);
      T.a.b(new OSPermissionChangedInternalObserver());
    }
    return T;
  }
  
  static String h()
  {
    return l(b);
  }
  
  private static void h(String paramString)
  {
    if (b == null) {
      return;
    }
    al.a(al.a, "GT_APP_ID", paramString);
  }
  
  private static OSSubscriptionState i(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (V == null)
    {
      V = new OSSubscriptionState(false, h(paramContext).b());
      h(paramContext).a.a(V);
      V.a.b(new OSSubscriptionChangedInternalObserver());
    }
    return V;
  }
  
  static String i()
  {
    if ((s == null) && (b != null)) {
      s = al.b(al.a, "GT_PLAYER_ID", null);
    }
    return s;
  }
  
  private static v j(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (X == null)
    {
      X = new v(false);
      X.a.b(new u());
    }
    return X;
  }
  
  static String j()
  {
    if ("".equals(t)) {
      return null;
    }
    if ((t == null) && (b != null)) {
      t = al.b(al.a, "OS_EMAIL_ID", null);
    }
    return t;
  }
  
  private static void k(Context paramContext)
  {
    Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
    if (localIntent != null)
    {
      localIntent.setFlags(268566528);
      paramContext.startActivity(localIntent);
    }
  }
  
  static boolean k()
  {
    if (i == null) {
      return true;
    }
    return i.i == k.c;
  }
  
  private static String l(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return al.b(al.a, "GT_APP_ID", null);
  }
  
  static boolean l()
  {
    a localA = i;
    boolean bool = false;
    if (localA == null) {
      return false;
    }
    if (i.i == k.b) {
      bool = true;
    }
    return bool;
  }
  
  static long m()
  {
    if ((y == -1L) && (b != null)) {
      y = al.b(al.a, "GT_UNSENT_ACTIVE_TIME", 0L);
    }
    h localH = h.e;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("GetUnsentActiveTime: ");
    localStringBuilder.append(y);
    a(localH, localStringBuilder.toString());
    return y;
  }
  
  private static String m(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return al.b(al.a, "GT_PLAYER_ID", null);
  }
  
  private static long n(Context paramContext)
  {
    return al.b(al.a, "OS_LAST_SESSION_TIME", -31000L);
  }
  
  static boolean n()
  {
    return (c) && (g());
  }
  
  static void o()
  {
    Q = false;
    a(System.currentTimeMillis());
  }
  
  static boolean p()
  {
    if (i.f) {
      return ag.a(b);
    }
    return true;
  }
  
  static void q()
  {
    if (n != null)
    {
      n.a();
      n = null;
    }
  }
  
  static void r()
  {
    if (n != null)
    {
      n.a(new c(b.d, "Failed due to network failure. Will retry on next sync."));
      n = null;
    }
  }
  
  static void s()
  {
    if (m != null)
    {
      m.a();
      m = null;
    }
  }
  
  static void t()
  {
    if (m != null)
    {
      m.a(new c(b.d, "Failed due to network failure. Will retry on next sync."));
      m = null;
    }
  }
  
  public static class a
  {
    Context a;
    ah.i b;
    ah.j c;
    boolean d;
    boolean e;
    boolean f;
    boolean g;
    boolean h;
    ah.k i = ah.k.b;
    
    private a() {}
    
    private a(Context paramContext)
    {
      this.a = paramContext;
    }
    
    public a a(ah.i paramI)
    {
      this.b = paramI;
      return this;
    }
    
    public a a(ah.j paramJ)
    {
      this.c = paramJ;
      return this;
    }
    
    public a a(ah.k paramK)
    {
      ah.c().h = false;
      this.i = paramK;
      return this;
    }
    
    public void a()
    {
      ah.a(this);
    }
  }
  
  public static enum b
  {
    private b() {}
  }
  
  public static class c
  {
    private ah.b a;
    private String b;
    
    c(ah.b paramB, String paramString)
    {
      this.a = paramB;
      this.b = paramString;
    }
  }
  
  public static abstract interface d
  {
    public abstract void a();
    
    public abstract void a(ah.c paramC);
  }
  
  public static abstract interface e
  {
    public abstract void a(JSONObject paramJSONObject);
  }
  
  private static class f
  {
    JSONArray a;
    boolean b;
    am.a c;
    
    f(JSONArray paramJSONArray)
    {
      this.a = paramJSONArray;
    }
  }
  
  public static abstract interface g
  {
    public abstract void a(String paramString1, String paramString2);
  }
  
  public static enum h
  {
    private h() {}
  }
  
  public static abstract interface i
  {
    public abstract void a(z paramZ);
  }
  
  public static abstract interface j
  {
    public abstract void a(w paramW);
  }
  
  public static enum k
  {
    private k() {}
  }
  
  private static class l
    implements Runnable
  {
    private Runnable a;
    private long b;
    
    l(Runnable paramRunnable)
    {
      this.a = paramRunnable;
    }
    
    public void run()
    {
      this.a.run();
      ah.b(this.b);
    }
  }
}
