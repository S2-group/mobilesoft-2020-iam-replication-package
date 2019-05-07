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
  private static d C;
  private static int D;
  private static ag E;
  private static String F;
  private static boolean G;
  private static boolean H;
  private static boolean I;
  private static boolean J;
  private static n.e K;
  private static Collection<JSONArray> L = new ArrayList();
  private static HashSet<String> M = new HashSet();
  private static e N;
  private static boolean O;
  private static boolean P;
  private static boolean Q;
  private static JSONObject R;
  private static boolean S;
  private static ad T;
  private static ac<Object, ae> U;
  private static OSSubscriptionState V;
  private static ac<Object, af> W;
  private static x X;
  private static f Y;
  private static int Z = 0;
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
  static ad k;
  static OSSubscriptionState l;
  private static d m;
  private static d n;
  private static String o;
  private static boolean p;
  private static h q = h.a;
  private static h r = h.d;
  private static String s = null;
  private static String t = null;
  private static int u;
  private static boolean v;
  private static g w;
  private static long x;
  private static long y;
  private static aw z;
  
  static
  {
    e = new ConcurrentLinkedQueue();
    f = new AtomicLong();
    x = 1L;
    y = -1L;
    C = new c();
    g = "native";
    h = true;
  }
  
  public ah() {}
  
  private static void C()
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
  
  private static boolean D()
  {
    if ((c) && (d == null)) {
      return false;
    }
    if ((!c) && (d == null)) {
      return true;
    }
    return (d != null) && (!d.isShutdown());
  }
  
  private static void E()
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
    F();
    H();
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
  
  private static void F()
  {
    Context localContext = b;
    boolean bool;
    if ((i.d) && (!J)) {
      bool = true;
    } else {
      bool = false;
    }
    n.a(localContext, bool, new n.c()
    {
      public void a(n.e paramAnonymousE)
      {
        ah.a(paramAnonymousE);
        ah.c(true);
        ah.s();
      }
    });
  }
  
  private static void G()
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
        if (paramAnonymousInt < 1 ? (an.g() == null) && ((ah.t() == 1) || (ah.t() < -6)) : ah.t() < -6) {
          ah.a(paramAnonymousInt);
        }
        ah.e(paramAnonymousString);
        ah.d(true);
        ah.f(ah.b).b(paramAnonymousString);
        ah.s();
      }
    });
  }
  
  private static void H()
  {
    if (I)
    {
      G();
      return;
    }
    am.a local9 = new am.a()
    {
      void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        new Thread(new Runnable()
        {
          public void run()
          {
            try
            {
              int j = ah.u() * 10000 + 30000;
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
            ah.v();
            ah.w();
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
            ah.e(true);
            ah.f(paramAnonymousString.getString("android_sender_id"));
          }
          ah.j = paramAnonymousString.optBoolean("enterp", false);
          ah.f(paramAnonymousString.optBoolean("use_email_auth", false));
          ah.a(paramAnonymousString.getJSONObject("awl_list"));
          boolean bool = paramAnonymousString.optBoolean("fba", false);
          al.a(al.a, "GT_FIREBASE_TRACKING_ENABLED", bool);
          p.a(ah.b, paramAnonymousString);
        }
        catch (Throwable paramAnonymousString)
        {
          paramAnonymousString.printStackTrace();
        }
        ah.g(true);
        ah.x();
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
    a(h.f, "Starting request to get Android parameters.");
    am.a((String)localObject, local9);
  }
  
  private static void I()
  {
    Iterator localIterator = L.iterator();
    while (localIterator.hasNext()) {
      b((JSONArray)localIterator.next(), true, false);
    }
    L.clear();
  }
  
  private static int J()
  {
    TimeZone localTimeZone = Calendar.getInstance().getTimeZone();
    int i2 = localTimeZone.getRawOffset();
    int i1 = i2;
    if (localTimeZone.inDaylightTime(new Date())) {
      i1 = i2 + localTimeZone.getDSTSavings();
    }
    return i1 / 1000;
  }
  
  private static void K()
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
            ah.y();
            ai.a(ah.b, ah.a, ah.z(), c.a());
            return;
          }
          catch (JSONException localJSONException)
          {
            ah.a(ah.h.b, "FATAL Error registering device!", localJSONException);
          }
        }
      }, "OS_REG_USER").start();
    }
  }
  
  private static void L()
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
    localJSONObject.put("timezone", J());
    localJSONObject.put("language", ag.d());
    localJSONObject.put("sdk", "030801");
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
        an.a(localJSONObject);
        localJSONObject = new JSONObject();
        localJSONObject.put("identifier", F);
        localJSONObject.put("subscribableStatus", u);
        localJSONObject.put("androidPermission", n());
        localJSONObject.put("device_type", D);
        an.b(localJSONObject);
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
  
  private static void M()
  {
    if (w != null) {
      ag.a(new Runnable()
      {
        public void run() {}
      });
    }
  }
  
  private static void N()
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
      String str = g();
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
  
  private static boolean O()
  {
    if (Q) {
      return true;
    }
    return (System.currentTimeMillis() - m(b)) / 1000L >= 30L;
  }
  
  private static void P()
  {
    Intent localIntent = new Intent(b, SyncService.class);
    localIntent.putExtra("task", 0);
    b.startService(localIntent);
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
      b(localJSONObject);
      a(g(), localJSONObject, paramBoolean);
      String str = h();
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
        I();
      }
      return;
    }
    boolean bool = paramContext instanceof Activity;
    v = bool;
    a = paramString2;
    b = paramContext.getApplicationContext();
    b(i.g);
    if (bool)
    {
      a.b = (Activity)paramContext;
      u.a(b);
      P();
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
      paramContext = f();
      if (paramContext != null)
      {
        if (!paramContext.equals(a))
        {
          a(h.f, "APP ID changed, clearing user id as it is no longer valid.");
          g(a);
          an.h();
        }
      }
      else
      {
        f.a(0, b);
        g(a);
      }
      OSPermissionChangedInternalObserver.a(g(b));
      if ((v) || (g() == null))
      {
        Q = O();
        a(System.currentTimeMillis());
        E();
      }
      if (i.b != null) {
        I();
      }
      if (aw.a(b)) {
        z = new aw(b);
      }
      if (av.a()) {
        B = new av(b);
      }
      c = true;
      C();
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
  
  private static void a(aa paramAa)
  {
    ag.a(new Runnable()
    {
      public void run()
      {
        ah.i.b.a(this.a);
      }
    });
    if ((B != null) && (c(b))) {
      B.a(paramAa);
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
        bc.a localA = an.c(ah.A() ^ true);
        if (localA.a) {
          ah.h(true);
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
        if (ah.g() != null) {
          ag.a(new Runnable()
          {
            public void run() {}
          });
        }
      }
    };
    if ((b != null) && (!D()))
    {
      paramG.run();
      return;
    }
    a(h.c, "You must initialize OneSignal before getting tags! Moving this tag operation to a pending queue.");
    a(new l(paramG));
  }
  
  public static void a(h paramH1, h paramH2)
  {
    r = paramH1;
    q = paramH2;
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
  
  static void a(String paramString)
  {
    s = paramString;
    if (b == null) {
      return;
    }
    al.a(al.a, "GT_PLAYER_ID", s);
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
    if (g() == null)
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
      paramJSONArray.append(g());
      paramJSONArray.append("/on_purchase");
      am.b(paramJSONArray.toString(), localJSONObject, paramA);
      if (h() != null)
      {
        paramJSONArray = new StringBuilder();
        paramJSONArray.append("players/");
        paramJSONArray.append(h());
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
    if (i != null)
    {
      if (i.c == null) {
        return;
      }
      paramJSONArray = c(paramJSONArray, paramBoolean1, paramBoolean2);
      i.c.a(paramJSONArray.a);
      if ((B != null) && (c(b))) {
        B.b(paramJSONArray);
      }
    }
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
    paramJSONObject = c(paramJSONObject);
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
    //   4: ldc_w 1026
    //   7: aload_0
    //   8: invokevirtual 763	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   11: ifeq +5 -> 16
    //   14: iconst_0
    //   15: ireturn
    //   16: aload_1
    //   17: invokestatic 1031	com/onesignal/aj:a	(Landroid/content/Context;)Lcom/onesignal/aj;
    //   20: astore 4
    //   22: aconst_null
    //   23: astore 6
    //   25: aconst_null
    //   26: astore_1
    //   27: aload 4
    //   29: invokevirtual 1034	com/onesignal/aj:b	()Landroid/database/sqlite/SQLiteDatabase;
    //   32: ldc_w 1036
    //   35: iconst_1
    //   36: anewarray 479	java/lang/String
    //   39: dup
    //   40: iconst_0
    //   41: ldc_w 1038
    //   44: aastore
    //   45: ldc_w 1040
    //   48: iconst_1
    //   49: anewarray 479	java/lang/String
    //   52: dup
    //   53: iconst_0
    //   54: aload_0
    //   55: aastore
    //   56: aconst_null
    //   57: aconst_null
    //   58: aconst_null
    //   59: invokevirtual 1046	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   62: astore 4
    //   64: aload 4
    //   66: invokeinterface 1051 1 0
    //   71: istore_3
    //   72: iload_3
    //   73: istore_2
    //   74: aload 4
    //   76: ifnull +65 -> 141
    //   79: aload 4
    //   81: invokeinterface 1054 1 0
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
    //   116: getstatic 675	com/onesignal/ah$h:c	Lcom/onesignal/ah$h;
    //   119: ldc_w 1056
    //   122: aload 5
    //   124: invokestatic 645	com/onesignal/ah:a	(Lcom/onesignal/ah$h;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   127: aload 4
    //   129: ifnull +10 -> 139
    //   132: aload 4
    //   134: invokeinterface 1054 1 0
    //   139: iconst_0
    //   140: istore_2
    //   141: iload_2
    //   142: ifeq +43 -> 185
    //   145: getstatic 298	com/onesignal/ah$h:f	Lcom/onesignal/ah$h;
    //   148: astore_1
    //   149: new 277	java/lang/StringBuilder
    //   152: dup
    //   153: invokespecial 278	java/lang/StringBuilder:<init>	()V
    //   156: astore 4
    //   158: aload 4
    //   160: ldc_w 1058
    //   163: invokevirtual 284	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: pop
    //   167: aload 4
    //   169: aload_0
    //   170: invokevirtual 284	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: pop
    //   174: aload_1
    //   175: aload 4
    //   177: invokevirtual 292	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   180: invokestatic 303	com/onesignal/ah:a	(Lcom/onesignal/ah$h;Ljava/lang/String;)V
    //   183: iconst_1
    //   184: ireturn
    //   185: iconst_0
    //   186: ireturn
    //   187: aload_1
    //   188: ifnull +9 -> 197
    //   191: aload_1
    //   192: invokeinterface 1054 1 0
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
    boolean bool2 = false;
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
    double d1 = SystemClock.elapsedRealtime() - x;
    Double.isNaN(d1);
    long l1 = (d1 / 1000.0D + 0.5D);
    x = SystemClock.elapsedRealtime();
    boolean bool1 = bool2;
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
      a(System.currentTimeMillis());
      l1 = k() + l1;
      if ((!paramBoolean) && (l1 >= 60L) && (g() != null))
      {
        a(l1, true);
        return false;
      }
      e(l1);
      bool1 = bool2;
      if (l1 >= 60L) {
        bool1 = true;
      }
    }
    return bool1;
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
  
  private static void b(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject.put("net_type", E.b());
      return;
    }
    catch (Throwable paramJSONObject) {}
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
    return al.b(al.a, "OS_FILTER_OTHER_GCM_RECEIVERS", false);
  }
  
  private static aa c(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i3 = paramJSONArray.length();
    aa localAa = new aa();
    y localY = new y();
    localY.a = l();
    localY.b = paramBoolean1;
    localY.c = paramJSONArray.optJSONObject(0).optInt("notificationId");
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
        localY.d = o.a((JSONObject)localObject5);
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
      if (localY.f == null)
      {
        localObject4 = localObject3;
        localY.f = new ArrayList();
      }
      localObject4 = localObject3;
      localY.f.add(localY.d);
      localObject1 = localObject3;
    }
    for (;;)
    {
      i1 += 1;
      break;
      localAa.a = localY;
      localAa.b = new z();
      localAa.b.b = ((String)localObject2);
      localObject3 = localAa.b;
      if (localObject2 != null) {
        paramJSONArray = z.a.b;
      } else {
        paramJSONArray = z.a.a;
      }
      ((z)localObject3).a = paramJSONArray;
      if (paramBoolean2) {
        paramJSONArray = localAa.a;
      }
      for (Object localObject2 = y.a.b;; localObject2 = y.a.a)
      {
        paramJSONArray.e = ((y.a)localObject2);
        return localAa;
        paramJSONArray = localAa.a;
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
  
  private static String c(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject = new JSONObject(paramJSONObject.optString("custom")).optString("i", null);
      return paramJSONObject;
    }
    catch (Throwable paramJSONObject) {}
    return null;
  }
  
  static void c(String paramString)
  {
    a(paramString);
    M();
    a(N);
    h(b).a(paramString);
    if (Y != null)
    {
      a(Y.a, Y.b, Y.c);
      Y = null;
    }
    an.i();
    ai.a(b, a, paramString, c.a());
  }
  
  static boolean c(Context paramContext)
  {
    return al.b(al.a, "GT_FIREBASE_TRACKING_ENABLED", false);
  }
  
  static void d()
  {
    P();
    v = true;
    x = SystemClock.elapsedRealtime();
    Q = O();
    a(System.currentTimeMillis());
    E();
    if (z != null) {
      z.a();
    }
    u.a(b);
    g(b).a();
    if ((B != null) && (c(b))) {
      B.b();
    }
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
    i(b).a(paramString);
    try
    {
      an.b(new JSONObject().put("parent_player_id", paramString));
      return;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
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
  
  static boolean e()
  {
    return v;
  }
  
  static boolean e(Context paramContext)
  {
    return al.b(al.a, "GT_SOUND_ENABLED", true);
  }
  
  static String f()
  {
    return k(b);
  }
  
  private static ad g(Context paramContext)
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
  
  static String g()
  {
    if ((s == null) && (b != null)) {
      s = al.b(al.a, "GT_PLAYER_ID", null);
    }
    return s;
  }
  
  private static void g(String paramString)
  {
    if (b == null) {
      return;
    }
    al.a(al.a, "GT_APP_ID", paramString);
  }
  
  private static OSSubscriptionState h(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (V == null)
    {
      V = new OSSubscriptionState(false, g(paramContext).b());
      g(paramContext).a.a(V);
      V.a.b(new OSSubscriptionChangedInternalObserver());
    }
    return V;
  }
  
  static String h()
  {
    if ("".equals(t)) {
      return null;
    }
    if ((t == null) && (b != null)) {
      t = al.b(al.a, "OS_EMAIL_ID", null);
    }
    return t;
  }
  
  private static x i(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (X == null)
    {
      X = new x(false);
      X.a.b(new w());
    }
    return X;
  }
  
  static boolean i()
  {
    if (i == null) {
      return true;
    }
    return i.i == k.c;
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
  
  static boolean j()
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
  
  static long k()
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
  
  private static String k(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return al.b(al.a, "GT_APP_ID", null);
  }
  
  private static String l(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return al.b(al.a, "GT_PLAYER_ID", null);
  }
  
  static boolean l()
  {
    return (c) && (e());
  }
  
  private static long m(Context paramContext)
  {
    return al.b(al.a, "OS_LAST_SESSION_TIME", -31000L);
  }
  
  static void m()
  {
    Q = false;
    a(System.currentTimeMillis());
  }
  
  static boolean n()
  {
    if (i.f) {
      return ag.a(b);
    }
    return true;
  }
  
  static void o()
  {
    if (n != null)
    {
      n.a();
      n = null;
    }
  }
  
  static void p()
  {
    if (n != null)
    {
      n.a(new c(b.d, "Failed due to network failure. Will retry on next sync."));
      n = null;
    }
  }
  
  static void q()
  {
    if (m != null)
    {
      m.a();
      m = null;
    }
  }
  
  static void r()
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
    
    public a a(boolean paramBoolean)
    {
      this.d = paramBoolean;
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
    public abstract void a(aa paramAa);
  }
  
  public static abstract interface j
  {
    public abstract void a(y paramY);
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
