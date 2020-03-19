package com.onesignal;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Application;
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

public class aj
{
  private static long A = -1L;
  private static ba B;
  private static ay C;
  private static az D;
  private static d E = new c();
  private static int F = 0;
  private static ai G;
  private static String H;
  private static boolean I = false;
  private static boolean J = false;
  private static boolean K = false;
  private static boolean L = false;
  private static p.f M;
  private static Collection<JSONArray> N = new ArrayList();
  private static HashSet<String> O = new HashSet();
  private static e P;
  private static boolean Q = false;
  private static boolean R = false;
  private static boolean S = false;
  private static JSONObject T;
  private static boolean U = false;
  private static af V;
  private static ae<Object, ag> W;
  private static OSSubscriptionState X;
  private static ae<Object, ah> Y;
  private static z Z;
  static String a;
  private static f aa;
  private static ar ab;
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
  static af m;
  static OSSubscriptionState n;
  private static d o;
  private static d p;
  private static String q;
  private static boolean r = false;
  private static h s = h.a;
  private static h t = h.d;
  private static String u;
  private static String v;
  private static int w = 0;
  private static boolean x = false;
  private static g y;
  private static long z = 1L;
  
  static
  {
    e = new ConcurrentLinkedQueue();
    f = new AtomicLong();
  }
  
  public aj() {}
  
  private static void H()
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
  
  private static boolean I()
  {
    if ((c) && (d == null)) {
      return false;
    }
    if ((!c) && (d == null)) {
      return true;
    }
    return (d != null) && (!d.isShutdown());
  }
  
  private static void J()
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
    K();
    N();
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
  
  private static void K()
  {
    p.d local7 = new p.d()
    {
      public p.a a()
      {
        return p.a.a;
      }
      
      public void a(p.f paramAnonymousF)
      {
        aj.a(paramAnonymousF);
        aj.d(true);
        aj.x();
      }
    };
    boolean bool;
    if ((i.c) && (!L)) {
      bool = true;
    } else {
      bool = false;
    }
    p.a(b, bool, local7);
  }
  
  private static ar L()
  {
    if (ab != null) {
      return ab;
    }
    Object localObject;
    if (F == 2) {
      localObject = new as();
    }
    for (;;)
    {
      ab = (ar)localObject;
      break;
      if (ai.a()) {
        localObject = new au();
      } else {
        localObject = new av();
      }
    }
    return ab;
  }
  
  private static void M()
  {
    L().a(b, q, new ar.a()
    {
      public void a(String paramAnonymousString, int paramAnonymousInt)
      {
        if (paramAnonymousInt < 1 ? (ap.g() == null) && ((aj.y() == 1) || (aj.a(aj.y()))) : aj.a(aj.y())) {
          aj.b(paramAnonymousInt);
        }
        aj.f(paramAnonymousString);
        aj.e(true);
        aj.e(aj.b).b(paramAnonymousString);
        aj.x();
      }
    });
  }
  
  private static void N()
  {
    if (K)
    {
      M();
      return;
    }
    ao.a local10 = new ao.a()
    {
      void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        new Thread(new Runnable()
        {
          public void run()
          {
            try
            {
              int j = 30000 + aj.z() * 10000;
              int i = j;
              if (j > 90000) {
                i = 90000;
              }
              aj.h localH = aj.h.e;
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("Failed to get Android parameters, trying again in ");
              localStringBuilder.append(i / 1000);
              localStringBuilder.append(" seconds.");
              aj.a(localH, localStringBuilder.toString());
              Thread.sleep(i);
            }
            catch (Throwable localThrowable)
            {
              for (;;) {}
            }
            aj.A();
            aj.B();
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
            aj.f(true);
            aj.g(paramAnonymousString.getString("android_sender_id"));
          }
          aj.j = paramAnonymousString.optBoolean("enterp", false);
          aj.g(paramAnonymousString.optBoolean("use_email_auth", false));
          aj.b(paramAnonymousString.getJSONObject("awl_list"));
          boolean bool = paramAnonymousString.optBoolean("fba", false);
          an.a(an.a, "GT_FIREBASE_TRACKING_ENABLED", bool);
          r.a(aj.b, paramAnonymousString);
        }
        catch (Throwable paramAnonymousString)
        {
          paramAnonymousString.printStackTrace();
        }
        aj.h(true);
        aj.C();
      }
    };
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
    a(h.f, "Starting request to get Android parameters.");
    ao.a((String)localObject, local10);
  }
  
  private static void O()
  {
    Iterator localIterator = N.iterator();
    while (localIterator.hasNext()) {
      b((JSONArray)localIterator.next(), true, false);
    }
    N.clear();
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
    h localH = h.f;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("registerUser: registerForPushFired:");
    localStringBuilder.append(I);
    localStringBuilder.append(", locationFired: ");
    localStringBuilder.append(J);
    localStringBuilder.append(", awlFired: ");
    localStringBuilder.append(K);
    a(localH, localStringBuilder.toString());
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
            aj.D();
            ak.a(aj.b, aj.a, aj.E(), c.a());
            return;
          }
          catch (JSONException localJSONException)
          {
            aj.a(aj.h.b, "FATAL Error registering device!", localJSONException);
          }
        }
      }, "OS_REG_USER").start();
    }
  }
  
  private static void R()
    throws JSONException
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
    localJSONObject.put("timezone", P());
    localJSONObject.put("language", ai.f());
    localJSONObject.put("sdk", "030901");
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
        localJSONObject.put("rooted", ax.a());
        ap.b(localJSONObject);
        localJSONObject = new JSONObject();
        localJSONObject.put("identifier", H);
        localJSONObject.put("subscribableStatus", w);
        localJSONObject.put("androidPermission", s());
        localJSONObject.put("device_type", F);
        ap.c(localJSONObject);
        if ((h) && (M != null)) {
          ap.a(M);
        }
        if (S) {
          ap.j();
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
  
  private static void S()
  {
    if (y != null) {
      ai.a(new Runnable()
      {
        public void run() {}
      });
    }
  }
  
  private static void T()
  {
    try
    {
      Object localObject1 = y;
      if (localObject1 == null) {
        return;
      }
      localObject1 = ap.g();
      if (!ap.f()) {
        localObject1 = null;
      }
      String str = k();
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
  
  private static boolean U()
  {
    if (S) {
      return true;
    }
    return (System.currentTimeMillis() - m(b)) / 1000L >= 30L;
  }
  
  static ae<Object, ag> a()
  {
    if (W == null) {
      W = new ae("onOSPermissionChanged", true);
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
      h localH;
      if (paramBundle.containsKey("custom"))
      {
        paramBundle = new JSONObject(paramBundle.getString("custom"));
        if (paramBundle.has("i")) {
          return paramBundle.optString("i", null);
        }
        localH = h.f;
      }
      for (paramBundle = "Not a OneSignal formatted GCM message. No 'i' field in custom.";; paramBundle = "Not a OneSignal formatted GCM message. No 'custom' field in the bundle.")
      {
        a(localH, paramBundle);
        return null;
        localH = h.f;
      }
      return null;
    }
    catch (Throwable paramBundle)
    {
      a(h.f, "Could not parse bundle, probably not a OneSignal notification.", paramBundle);
    }
  }
  
  static void a(long paramLong)
  {
    an.a(an.a, "OS_LAST_SESSION_TIME", paramLong);
  }
  
  static void a(long paramLong, boolean paramBoolean)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject().put("app_id", a).put("type", 1).put("state", "ping").put("active_time", paramLong);
      c(localJSONObject);
      a(k(), localJSONObject, paramBoolean);
      String str = l();
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
    b = paramContext.getApplicationContext();
    if ((k) && (!d()))
    {
      a(h.g, "OneSignal SDK initialization delayed, user privacy consent is set to required for this application.");
      l = new k(paramContext, paramString1, paramString2, paramI, paramJ);
      return;
    }
    i = c();
    i.g = false;
    i.a = paramI;
    i.b = paramJ;
    if (!r) {
      q = paramString1;
    }
    G = new ai();
    F = G.c();
    w = G.a(paramContext, F, paramString2);
    if (w == 64537) {
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
    x = bool;
    a = paramString2;
    b = paramContext.getApplicationContext();
    a(i.f);
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
    ap.d();
    ((Application)b).registerActivityLifecycleCallbacks(new b());
    try
    {
      Class.forName("com.amazon.device.iap.PurchasingListener");
      C = new ay(b);
      paramContext = i();
      if (paramContext != null)
      {
        if (!paramContext.equals(a))
        {
          a(h.f, "APP ID changed, clearing user id as it is no longer valid.");
          h(a);
          ap.h();
        }
      }
      else
      {
        f.a(0, b);
        h(a);
      }
      OSPermissionChangedInternalObserver.a(f(b));
      if ((x) || (k() == null))
      {
        S = U();
        a(System.currentTimeMillis());
        J();
      }
      if (i.a != null) {
        O();
      }
      if (ba.a(b)) {
        B = new ba(b);
      }
      if (az.a()) {
        D = new az(b);
      }
      c = true;
      H();
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
    if ((D != null) && (b(b))) {
      D.a(c(paramJSONArray, true, paramBoolean));
    }
    boolean bool1 = false;
    boolean bool2 = "DISABLE".equals(ai.a(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
    if (!bool2) {
      bool1 = a(paramContext, paramJSONArray);
    }
    b(paramJSONArray, true, paramBoolean);
    if ((!paramBoolean) && (!bool1) && (!bool2)) {
      i(paramContext);
    }
  }
  
  private static void a(ac paramAc)
  {
    ai.a(new Runnable()
    {
      public void run()
      {
        aj.i.a.a(this.a);
      }
    });
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
        bg.a localA = ap.c(aj.F() ^ true);
        if (localA.a) {
          aj.i(true);
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
  
  static void a(h paramH, String paramString)
  {
    a(paramH, paramString, null);
  }
  
  static void a(h paramH, final String paramString, Throwable paramThrowable)
  {
    if (paramH.compareTo(t) < 1) {
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
    if ((paramH.compareTo(s) < 1) && (a.b != null)) {
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
        ai.a(new Runnable()
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
  
  public static void a(k paramK)
  {
    c().g = true;
    c().h = paramK;
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
  
  private static void a(String paramString, JSONObject paramJSONObject, boolean paramBoolean)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("players/");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append("/on_focus");
    paramString = ((StringBuilder)localObject).toString();
    localObject = new ao.a()
    {
      void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        aj.a("sending on_focus Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
      }
      
      void a(String paramAnonymousString)
      {
        aj.c(0L);
      }
    };
    if (paramBoolean)
    {
      ao.d(paramString, paramJSONObject, (ao.a)localObject);
      return;
    }
    ao.b(paramString, paramJSONObject, (ao.a)localObject);
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean, ao.a paramA)
  {
    if (a("sendPurchases()")) {
      return;
    }
    if (k() == null)
    {
      aa = new f(paramJSONArray);
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
      paramJSONArray.append(k());
      paramJSONArray.append("/on_purchase");
      ao.b(paramJSONArray.toString(), localJSONObject, paramA);
      if (l() != null)
      {
        paramJSONArray = new StringBuilder();
        paramJSONArray.append("players/");
        paramJSONArray.append(l());
        paramJSONArray.append("/on_purchase");
        ao.b(paramJSONArray.toString(), localJSONObject, null);
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
    if ((D != null) && (b(b))) {
      D.b(paramJSONArray);
    }
    if (i != null)
    {
      if (i.b == null) {
        return;
      }
      i.b.a(paramJSONArray.a);
    }
  }
  
  public static void a(JSONObject paramJSONObject)
  {
    if (a("sendTags()")) {
      return;
    }
    paramJSONObject = new Runnable()
    {
      public void run()
      {
        if (this.a == null) {
          return;
        }
        JSONObject localJSONObject1 = ap.c(false).b;
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
            localObject = aj.h.c;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("Omitting key '");
            localStringBuilder.append(str);
            localStringBuilder.append("'! sendTags DO NOT supported nested values!");
            aj.a((aj.h)localObject, localStringBuilder.toString());
          }
          catch (Throwable localThrowable) {}
          if (!localJSONObject2.toString().equals("{}")) {
            ap.a(localJSONObject2);
          }
          return;
        }
      }
    };
    if ((b != null) && (!I()))
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
    an.a(an.a, "OS_FILTER_OTHER_GCM_RECEIVERS", paramBoolean);
  }
  
  static boolean a(Context paramContext)
  {
    return an.b(an.a, "OS_FILTER_OTHER_GCM_RECEIVERS", false);
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
    int i1 = paramH.compareTo(s);
    boolean bool = true;
    if (i1 >= 1)
    {
      if (paramH.compareTo(t) < 1) {
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
        h localH = h.d;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Method ");
        localStringBuilder.append(paramString);
        localStringBuilder.append(" was called before the user provided privacy consent. Your application is set to require the user's privacy consent before the OneSignal SDK can be initialized. Please ensure the user has provided consent before calling this method. You can check the latest OneSignal consent status by calling OneSignal.userProvidedPrivacyConsent()");
        a(localH, localStringBuilder.toString());
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
    //   4: ldc_w 1057
    //   7: aload_0
    //   8: invokevirtual 772	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   11: ifeq +5 -> 16
    //   14: iconst_0
    //   15: ireturn
    //   16: aload_1
    //   17: invokestatic 1062	com/onesignal/al:a	(Landroid/content/Context;)Lcom/onesignal/al;
    //   20: astore 4
    //   22: aconst_null
    //   23: astore 6
    //   25: aconst_null
    //   26: astore_1
    //   27: aload 4
    //   29: invokevirtual 1065	com/onesignal/al:b	()Landroid/database/sqlite/SQLiteDatabase;
    //   32: ldc_w 1067
    //   35: iconst_1
    //   36: anewarray 494	java/lang/String
    //   39: dup
    //   40: iconst_0
    //   41: ldc_w 1069
    //   44: aastore
    //   45: ldc_w 1071
    //   48: iconst_1
    //   49: anewarray 494	java/lang/String
    //   52: dup
    //   53: iconst_0
    //   54: aload_0
    //   55: aastore
    //   56: aconst_null
    //   57: aconst_null
    //   58: aconst_null
    //   59: invokevirtual 1077	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   62: astore 4
    //   64: aload 4
    //   66: invokeinterface 1082 1 0
    //   71: istore_3
    //   72: iload_3
    //   73: istore_2
    //   74: aload 4
    //   76: ifnull +65 -> 141
    //   79: aload 4
    //   81: invokeinterface 1085 1 0
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
    //   116: getstatic 669	com/onesignal/aj$h:c	Lcom/onesignal/aj$h;
    //   119: ldc_w 1087
    //   122: aload 5
    //   124: invokestatic 640	com/onesignal/aj:a	(Lcom/onesignal/aj$h;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   127: aload 4
    //   129: ifnull +10 -> 139
    //   132: aload 4
    //   134: invokeinterface 1085 1 0
    //   139: iconst_0
    //   140: istore_2
    //   141: iload_2
    //   142: ifeq +43 -> 185
    //   145: getstatic 314	com/onesignal/aj$h:f	Lcom/onesignal/aj$h;
    //   148: astore_1
    //   149: new 294	java/lang/StringBuilder
    //   152: dup
    //   153: invokespecial 295	java/lang/StringBuilder:<init>	()V
    //   156: astore 4
    //   158: aload 4
    //   160: ldc_w 1089
    //   163: invokevirtual 301	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: pop
    //   167: aload 4
    //   169: aload_0
    //   170: invokevirtual 301	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: pop
    //   174: aload_1
    //   175: aload 4
    //   177: invokevirtual 308	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   180: invokestatic 319	com/onesignal/aj:a	(Lcom/onesignal/aj$h;Ljava/lang/String;)V
    //   183: iconst_1
    //   184: ireturn
    //   185: iconst_0
    //   186: ireturn
    //   187: aload_1
    //   188: ifnull +9 -> 197
    //   191: aload_1
    //   192: invokeinterface 1085 1 0
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
  
  static ae<Object, ah> b()
  {
    if (Y == null) {
      Y = new ae("onOSSubscriptionChanged", true);
    }
    return Y;
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
          localJSONObject.put("app_id", j(paramContext));
          localJSONObject.put("player_id", l(paramContext));
          localJSONObject.put("opened", true);
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("notifications/");
          localStringBuilder.append(str);
          ao.a(localStringBuilder.toString(), localJSONObject, new ao.a()
          {
            void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
            {
              aj.a("sending Notification Opened Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
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
  
  static void b(String paramString)
  {
    u = paramString;
    if (b == null) {
      return;
    }
    an.a(an.a, "GT_PLAYER_ID", u);
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
    if ((i != null) && (i.a != null))
    {
      a(c(paramJSONArray, paramBoolean1, paramBoolean2));
      return;
    }
    N.add(paramJSONArray);
  }
  
  public static void b(boolean paramBoolean)
  {
    if (b == null) {
      return;
    }
    an.a(an.a, "GT_VIBRATE_ENABLED", paramBoolean);
  }
  
  static boolean b(Context paramContext)
  {
    return an.b(an.a, "GT_FIREBASE_TRACKING_ENABLED", false);
  }
  
  private static ac c(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i3 = paramJSONArray.length();
    ac localAc = new ac();
    aa localAa = new aa();
    localAa.a = q();
    localAa.b = paramBoolean1;
    localAa.c = paramJSONArray.optJSONObject(0).optInt("notificationId");
    Object localObject1 = null;
    int i2 = 1;
    int i1 = 0;
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
      localAc.a = localAa;
      localAc.b = new ab();
      localAc.b.b = ((String)localObject2);
      localObject3 = localAc.b;
      if (localObject2 != null) {
        paramJSONArray = ab.a.b;
      } else {
        paramJSONArray = ab.a.a;
      }
      ((ab)localObject3).a = paramJSONArray;
      if (paramBoolean2) {
        paramJSONArray = localAc.a;
      }
      for (Object localObject2 = aa.a.b;; localObject2 = aa.a.a)
      {
        paramJSONArray.e = ((aa.a)localObject2);
        return localAc;
        paramJSONArray = localAc.a;
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
  
  static void c(String paramString)
  {
    v = paramString;
    if (b == null) {
      return;
    }
    String str = an.a;
    if ("".equals(v)) {
      paramString = null;
    } else {
      paramString = v;
    }
    an.a(str, "OS_EMAIL_ID", paramString);
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
  
  public static void c(boolean paramBoolean)
  {
    if (b == null) {
      return;
    }
    an.a(an.a, "GT_SOUND_ENABLED", paramBoolean);
  }
  
  private static boolean c(int paramInt)
  {
    return paramInt < -6;
  }
  
  static boolean c(Context paramContext)
  {
    return an.b(an.a, "GT_VIBRATE_ENABLED", true);
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
    S();
    a(P);
    g(b).a(paramString);
    if (aa != null)
    {
      a(aa.a, aa.b, aa.c);
      aa = null;
    }
    ap.i();
    ak.a(b, a, paramString, c.a());
  }
  
  public static boolean d()
  {
    return j();
  }
  
  static boolean d(Context paramContext)
  {
    return an.b(an.a, "GT_SOUND_ENABLED", true);
  }
  
  private static void e(long paramLong)
  {
    A = paramLong;
    if (b == null) {
      return;
    }
    h localH = h.e;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SaveUnsentActiveTime: ");
    localStringBuilder.append(A);
    a(localH, localStringBuilder.toString());
    an.a(an.a, "GT_UNSENT_ACTIVE_TIME", paramLong);
  }
  
  static void e(String paramString)
  {
    c(paramString);
    h(b).a(paramString);
    try
    {
      ap.c(new JSONObject().put("parent_player_id", paramString));
      return;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  static boolean e()
  {
    boolean bool3 = false;
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
    boolean bool2 = bool3;
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
      bool2 = f();
      a(System.currentTimeMillis());
      l1 = p() + l1;
      e(l1);
      boolean bool1 = l1 < 60L;
      if ((!bool1) && (k() != null))
      {
        if (!bool2) {
          aq.a(b);
        }
        aq.a();
        return false;
      }
      bool2 = bool3;
      if (!bool1) {
        bool2 = true;
      }
    }
    return bool2;
  }
  
  private static af f(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (V == null)
    {
      V = new af(false);
      V.a.b(new OSPermissionChangedInternalObserver());
    }
    return V;
  }
  
  static boolean f()
  {
    boolean bool = ap.c();
    if (bool) {
      aq.a(b);
    }
    return (p.a(b)) || (bool);
  }
  
  private static OSSubscriptionState g(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (X == null)
    {
      X = new OSSubscriptionState(false, f(paramContext).b());
      f(paramContext).a.a(X);
      X.a.b(new OSSubscriptionChangedInternalObserver());
    }
    return X;
  }
  
  static void g()
  {
    x = true;
    p.c();
    z = SystemClock.elapsedRealtime();
    S = U();
    a(System.currentTimeMillis());
    J();
    if (B != null) {
      B.a();
    }
    w.a(b);
    f(b).a();
    if ((D != null) && (b(b))) {
      D.b();
    }
    aq.b(b);
  }
  
  private static z h(Context paramContext)
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
  
  private static void h(String paramString)
  {
    if (b == null) {
      return;
    }
    an.a(an.a, "GT_APP_ID", paramString);
  }
  
  static boolean h()
  {
    return x;
  }
  
  static String i()
  {
    return j(b);
  }
  
  private static void i(Context paramContext)
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
  
  private static String j(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return an.b(an.a, "GT_APP_ID", null);
  }
  
  static boolean j()
  {
    return k(b);
  }
  
  static String k()
  {
    if ((u == null) && (b != null)) {
      u = an.b(an.a, "GT_PLAYER_ID", null);
    }
    return u;
  }
  
  private static boolean k(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    return an.b(an.a, "ONESIGNAL_USER_PROVIDED_CONSENT", false);
  }
  
  static String l()
  {
    if ("".equals(v)) {
      return null;
    }
    if ((v == null) && (b != null)) {
      v = an.b(an.a, "OS_EMAIL_ID", null);
    }
    return v;
  }
  
  private static String l(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return an.b(an.a, "GT_PLAYER_ID", null);
  }
  
  private static long m(Context paramContext)
  {
    return an.b(an.a, "OS_LAST_SESSION_TIME", -31000L);
  }
  
  static boolean m()
  {
    if (i == null) {
      return true;
    }
    return i.h == k.c;
  }
  
  static boolean n()
  {
    a localA = i;
    boolean bool = false;
    if (localA == null) {
      return false;
    }
    if (i.h == k.b) {
      bool = true;
    }
    return bool;
  }
  
  public static void o()
  {
    if (a("clearOneSignalNotifications()")) {
      return;
    }
    Runnable local8 = new Runnable()
    {
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: getstatic 22	com/onesignal/aj:b	Landroid/content/Context;
        //   3: ldc 24
        //   5: invokevirtual 30	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
        //   8: checkcast 32	android/app/NotificationManager
        //   11: astore 8
        //   13: getstatic 22	com/onesignal/aj:b	Landroid/content/Context;
        //   16: invokestatic 38	com/onesignal/al:a	(Landroid/content/Context;)Lcom/onesignal/al;
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
        //   33: invokevirtual 41	com/onesignal/al:b	()Landroid/database/sqlite/SQLiteDatabase;
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
        //   106: invokevirtual 76	com/onesignal/al:a	()Landroid/database/sqlite/SQLiteDatabase;
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
        //   159: getstatic 112	com/onesignal/aj$h:c	Lcom/onesignal/aj$h;
        //   162: astore_3
        //   163: aload_3
        //   164: ldc 114
        //   166: aload_2
        //   167: invokestatic 117	com/onesignal/aj:a	(Lcom/onesignal/aj$h;Ljava/lang/String;Ljava/lang/Throwable;)V
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
        //   201: getstatic 112	com/onesignal/aj$h:c	Lcom/onesignal/aj$h;
        //   204: ldc 119
        //   206: aload 5
        //   208: invokestatic 117	com/onesignal/aj:a	(Lcom/onesignal/aj$h;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   211: aload_3
        //   212: ifnull +18 -> 230
        //   215: aload_3
        //   216: invokevirtual 106	android/database/sqlite/SQLiteDatabase:endTransaction	()V
        //   219: goto +11 -> 230
        //   222: astore_2
        //   223: getstatic 112	com/onesignal/aj$h:c	Lcom/onesignal/aj$h;
        //   226: astore_3
        //   227: goto -64 -> 163
        //   230: iconst_0
        //   231: getstatic 22	com/onesignal/aj:b	Landroid/content/Context;
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
        //   262: getstatic 112	com/onesignal/aj$h:c	Lcom/onesignal/aj$h;
        //   265: ldc 114
        //   267: aload_3
        //   268: invokestatic 117	com/onesignal/aj:a	(Lcom/onesignal/aj$h;Ljava/lang/String;Ljava/lang/Throwable;)V
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
        //   304: getstatic 112	com/onesignal/aj$h:c	Lcom/onesignal/aj$h;
        //   307: ldc -127
        //   309: aload 4
        //   311: invokestatic 117	com/onesignal/aj:a	(Lcom/onesignal/aj$h;Ljava/lang/String;Ljava/lang/Throwable;)V
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
        //   0	337	0	this	8
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
        //   19	86	7	localAl	al
        //   11	58	8	localNotificationManager	android.app.NotificationManager
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
    if ((b != null) && (!I()))
    {
      local8.run();
      return;
    }
    a(h.c, "OneSignal.init has not been called. Could not clear notifications at this time - moving this operation toa waiting task queue.");
    a(new l(local8));
  }
  
  static long p()
  {
    if ((A == -1L) && (b != null)) {
      A = an.b(an.a, "GT_UNSENT_ACTIVE_TIME", 0L);
    }
    h localH = h.e;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("GetUnsentActiveTime: ");
    localStringBuilder.append(A);
    a(localH, localStringBuilder.toString());
    return A;
  }
  
  static boolean q()
  {
    return (c) && (h());
  }
  
  static void r()
  {
    S = false;
    a(System.currentTimeMillis());
  }
  
  static boolean s()
  {
    if (i.e) {
      return ai.a(b);
    }
    return true;
  }
  
  static void t()
  {
    if (p != null)
    {
      p.a();
      p = null;
    }
  }
  
  static void u()
  {
    if (p != null)
    {
      p.a(new c(b.d, "Failed due to network failure. Will retry on next sync."));
      p = null;
    }
  }
  
  static void v()
  {
    if (o != null)
    {
      o.a();
      o = null;
    }
  }
  
  static void w()
  {
    if (o != null)
    {
      o.a(new c(b.d, "Failed due to network failure. Will retry on next sync."));
      o = null;
    }
  }
  
  public static class a
  {
    aj.i a;
    aj.j b;
    boolean c;
    boolean d;
    boolean e;
    boolean f;
    boolean g;
    aj.k h = aj.k.b;
    
    private a() {}
  }
  
  public static enum b
  {
    private b() {}
  }
  
  public static class c
  {
    private aj.b a;
    private String b;
    
    c(aj.b paramB, String paramString)
    {
      this.a = paramB;
      this.b = paramString;
    }
  }
  
  public static abstract interface d
  {
    public abstract void a();
    
    public abstract void a(aj.c paramC);
  }
  
  public static abstract interface e
  {
    public abstract void a(JSONObject paramJSONObject);
  }
  
  private static class f
  {
    JSONArray a;
    boolean b;
    ao.a c;
    
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
    public abstract void a(ac paramAc);
  }
  
  public static abstract interface j
  {
    public abstract void a(aa paramAa);
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
      aj.b(this.b);
    }
  }
}
