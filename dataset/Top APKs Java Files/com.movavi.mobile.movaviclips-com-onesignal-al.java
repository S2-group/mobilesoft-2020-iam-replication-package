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

public class al
{
  private static long A = -1L;
  private static bc B;
  private static ba C;
  private static bb D;
  private static d E = new c();
  private static int F = 0;
  private static ak G;
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
  private static ah V;
  private static ag<Object, ai> W;
  private static OSSubscriptionState X;
  private static ag<Object, aj> Y;
  private static z Z;
  static String a;
  private static f aa;
  private static at ab;
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
  
  public al() {}
  
  private static void G()
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
  
  private static void H()
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
    I();
    L();
    boolean bool1 = bool2;
    if (!L) {
      if (i.d) {
        bool1 = bool2;
      } else {
        bool1 = false;
      }
    }
    L = bool1;
  }
  
  private static void I()
  {
    p.d local6 = new p.d()
    {
      public p.a a()
      {
        return p.a.a;
      }
      
      public void a(p.f paramAnonymousF)
      {
        al.a(paramAnonymousF);
        al.c(true);
        al.w();
      }
    };
    boolean bool;
    if ((i.d) && (!L)) {
      bool = true;
    } else {
      bool = false;
    }
    p.a(b, bool, local6);
  }
  
  private static at J()
  {
    if (ab != null) {
      return ab;
    }
    if (F == 2) {
      ab = new au();
    } else if (ak.a()) {
      ab = new aw();
    } else {
      ab = new ax();
    }
    return ab;
  }
  
  private static void K()
  {
    J().a(b, q, new at.a()
    {
      public void a(String paramAnonymousString, int paramAnonymousInt)
      {
        if (paramAnonymousInt < 1)
        {
          if ((ar.g() == null) && ((al.x() == 1) || (al.a(al.x())))) {
            al.b(paramAnonymousInt);
          }
        }
        else if (al.a(al.x())) {
          al.b(paramAnonymousInt);
        }
        al.f(paramAnonymousString);
        al.d(true);
        al.f(al.b).b(paramAnonymousString);
        al.w();
      }
    });
  }
  
  private static void L()
  {
    if (K)
    {
      K();
      return;
    }
    aq.a local8 = new aq.a()
    {
      void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        new Thread(new Runnable()
        {
          public void run()
          {
            try
            {
              int j = 30000 + al.y() * 10000;
              int i = j;
              if (j > 90000) {
                i = 90000;
              }
              al.h localH = al.h.e;
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("Failed to get Android parameters, trying again in ");
              localStringBuilder.append(i / 1000);
              localStringBuilder.append(" seconds.");
              al.a(localH, localStringBuilder.toString());
              Thread.sleep(i);
            }
            catch (Throwable localThrowable)
            {
              for (;;) {}
            }
            al.z();
            al.A();
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
            al.e(true);
            al.g(paramAnonymousString.getString("android_sender_id"));
          }
          al.j = paramAnonymousString.optBoolean("enterp", false);
          al.f(paramAnonymousString.optBoolean("use_email_auth", false));
          al.a(paramAnonymousString.getJSONObject("awl_list"));
          boolean bool = paramAnonymousString.optBoolean("fba", false);
          ap.a(ap.a, "GT_FIREBASE_TRACKING_ENABLED", bool);
          r.a(al.b, paramAnonymousString);
        }
        catch (Throwable paramAnonymousString)
        {
          com.google.a.a.a.a.a.a.a(paramAnonymousString);
        }
        al.g(true);
        al.B();
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
    aq.a((String)localObject, local8);
  }
  
  private static void M()
  {
    Iterator localIterator = N.iterator();
    while (localIterator.hasNext()) {
      b((JSONArray)localIterator.next(), true, false);
    }
    N.clear();
  }
  
  private static int N()
  {
    TimeZone localTimeZone = Calendar.getInstance().getTimeZone();
    int i2 = localTimeZone.getRawOffset();
    int i1 = i2;
    if (localTimeZone.inDaylightTime(new Date())) {
      i1 = i2 + localTimeZone.getDSTSavings();
    }
    return i1 / 1000;
  }
  
  private static void O()
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
            al.C();
            am.a(al.b, al.a, al.D(), c.a());
            return;
          }
          catch (JSONException localJSONException)
          {
            al.a(al.h.b, "FATAL Error registering device!", localJSONException);
          }
        }
      }, "OS_REG_USER").start();
      return;
    }
  }
  
  private static void P()
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
    localJSONObject.put("timezone", N());
    localJSONObject.put("language", ak.f());
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
        localJSONObject.put("rooted", az.a());
        ar.a(localJSONObject);
        localJSONObject = new JSONObject();
        localJSONObject.put("identifier", H);
        localJSONObject.put("subscribableStatus", w);
        localJSONObject.put("androidPermission", r());
        localJSONObject.put("device_type", F);
        ar.b(localJSONObject);
        if ((h) && (M != null)) {
          ar.a(M);
        }
        if (S) {
          ar.j();
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
  
  private static void Q()
  {
    if (y != null) {
      ak.a(new Runnable()
      {
        public void run() {}
      });
    }
  }
  
  private static void R()
  {
    try
    {
      Object localObject1 = y;
      if (localObject1 == null) {
        return;
      }
      localObject1 = ar.g();
      if (!ar.f()) {
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
  
  private static boolean S()
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
    ap.a(ap.a, "OS_LAST_SESSION_TIME", paramLong);
  }
  
  static void a(long paramLong, boolean paramBoolean)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject().put("app_id", a).put("type", 1).put("state", "ping").put("active_time", paramLong);
      b(localJSONObject);
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
    i.h = false;
    i.b = paramI;
    i.c = paramJ;
    if (!r) {
      q = paramString1;
    }
    G = new ak();
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
      if (i.b != null) {
        M();
      }
      return;
    }
    boolean bool = paramContext instanceof Activity;
    x = bool;
    a = paramString2;
    b = paramContext.getApplicationContext();
    b(i.g);
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
    ar.d();
    ((Application)b).registerActivityLifecycleCallbacks(new b());
    try
    {
      Class.forName("com.amazon.device.iap.PurchasingListener");
      C = new ba(b);
      paramContext = i();
      if (paramContext != null)
      {
        if (!paramContext.equals(a))
        {
          a(h.f, "APP ID changed, clearing user id as it is no longer valid.");
          h(a);
          ar.h();
        }
      }
      else
      {
        f.a(0, b);
        h(a);
      }
      OSPermissionChangedInternalObserver.a(g(b));
      if ((x) || (k() == null))
      {
        S = S();
        a(System.currentTimeMillis());
        H();
      }
      if (i.b != null) {
        M();
      }
      if (bc.a(b)) {
        B = new bc(b);
      }
      if (bb.a()) {
        D = new bb(b);
      }
      c = true;
      G();
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
    boolean bool2 = "DISABLE".equals(ak.a(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
    if (!bool2) {
      bool1 = a(paramContext, paramJSONArray);
    }
    b(paramJSONArray, true, paramBoolean);
    if ((!paramBoolean) && (!bool1) && (!bool2)) {
      j(paramContext);
    }
  }
  
  private static void a(ad paramAd)
  {
    ak.a(new Runnable()
    {
      public void run()
      {
        al.i.b.a(this.a);
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
        bi.a localA = ar.c(al.E() ^ true);
        if (localA.a) {
          al.h(true);
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
          com.google.a.a.a.a.a.a.a(paramThrowable, new PrintWriter((Writer)localObject));
          paramThrowable = new StringBuilder();
          paramThrowable.append(paramString);
          paramThrowable.append(((StringWriter)localObject).toString());
          paramString = paramThrowable.toString();
        }
        ak.a(new Runnable()
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
  
  private static void a(String paramString, JSONObject paramJSONObject, boolean paramBoolean)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("players/");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append("/on_focus");
    paramString = ((StringBuilder)localObject).toString();
    localObject = new aq.a()
    {
      void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        al.a("sending on_focus Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
      }
      
      void a(String paramAnonymousString)
      {
        al.b(0L);
      }
    };
    if (paramBoolean)
    {
      aq.d(paramString, paramJSONObject, (aq.a)localObject);
      return;
    }
    aq.b(paramString, paramJSONObject, (aq.a)localObject);
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean, aq.a paramA)
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
      aq.b(paramJSONArray.toString(), localJSONObject, paramA);
      if (l() != null)
      {
        paramJSONArray = new StringBuilder();
        paramJSONArray.append("players/");
        paramJSONArray.append(l());
        paramJSONArray.append("/on_purchase");
        aq.b(paramJSONArray.toString(), localJSONObject, null);
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
    if ((D != null) && (c(b))) {
      D.b(paramJSONArray);
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
  
  public static void a(boolean paramBoolean)
  {
    if ((k) && (!paramBoolean))
    {
      a(h.c, "Cannot change requiresUserPrivacyConsent() from TRUE to FALSE");
      return;
    }
    k = paramBoolean;
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
    paramJSONObject = c(paramJSONObject);
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
    //   4: ldc_w 1001
    //   7: aload_0
    //   8: invokevirtual 763	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   11: ifeq +5 -> 16
    //   14: iconst_0
    //   15: ireturn
    //   16: aload_1
    //   17: invokestatic 1006	com/onesignal/an:a	(Landroid/content/Context;)Lcom/onesignal/an;
    //   20: astore 4
    //   22: aconst_null
    //   23: astore 6
    //   25: aconst_null
    //   26: astore_1
    //   27: aload 4
    //   29: invokevirtual 1009	com/onesignal/an:b	()Landroid/database/sqlite/SQLiteDatabase;
    //   32: ldc_w 1011
    //   35: iconst_1
    //   36: anewarray 479	java/lang/String
    //   39: dup
    //   40: iconst_0
    //   41: ldc_w 1013
    //   44: aastore
    //   45: ldc_w 1015
    //   48: iconst_1
    //   49: anewarray 479	java/lang/String
    //   52: dup
    //   53: iconst_0
    //   54: aload_0
    //   55: aastore
    //   56: aconst_null
    //   57: aconst_null
    //   58: aconst_null
    //   59: invokevirtual 1021	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   62: astore 4
    //   64: aload 4
    //   66: invokeinterface 1026 1 0
    //   71: istore_3
    //   72: iload_3
    //   73: istore_2
    //   74: aload 4
    //   76: ifnull +65 -> 141
    //   79: aload 4
    //   81: invokeinterface 1029 1 0
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
    //   116: getstatic 659	com/onesignal/al$h:c	Lcom/onesignal/al$h;
    //   119: ldc_w 1031
    //   122: aload 5
    //   124: invokestatic 629	com/onesignal/al:a	(Lcom/onesignal/al$h;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   127: aload 4
    //   129: ifnull +10 -> 139
    //   132: aload 4
    //   134: invokeinterface 1029 1 0
    //   139: iconst_0
    //   140: istore_2
    //   141: iload_2
    //   142: ifeq +43 -> 185
    //   145: getstatic 298	com/onesignal/al$h:f	Lcom/onesignal/al$h;
    //   148: astore_1
    //   149: new 278	java/lang/StringBuilder
    //   152: dup
    //   153: invokespecial 279	java/lang/StringBuilder:<init>	()V
    //   156: astore 4
    //   158: aload 4
    //   160: ldc_w 1033
    //   163: invokevirtual 285	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: pop
    //   167: aload 4
    //   169: aload_0
    //   170: invokevirtual 285	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: pop
    //   174: aload_1
    //   175: aload 4
    //   177: invokevirtual 292	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   180: invokestatic 303	com/onesignal/al:a	(Lcom/onesignal/al$h;Ljava/lang/String;)V
    //   183: iconst_1
    //   184: ireturn
    //   185: iconst_0
    //   186: ireturn
    //   187: aload_1
    //   188: ifnull +9 -> 197
    //   191: aload_1
    //   192: invokeinterface 1029 1 0
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
  
  static ag<Object, aj> b()
  {
    if (Y == null) {
      Y = new ag("onOSSubscriptionChanged", true);
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
          localJSONObject.put("app_id", k(paramContext));
          localJSONObject.put("player_id", m(paramContext));
          localJSONObject.put("opened", true);
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("notifications/");
          localStringBuilder.append(str);
          aq.a(localStringBuilder.toString(), localJSONObject, new aq.a()
          {
            void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
            {
              al.a("sending Notification Opened Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
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
      a("ENABLE".equalsIgnoreCase(localBundle.getString("com.onesignal.PrivacyConsent")));
      a(localContext, paramA, localBundle.getString("onesignal_app_id"), i.b, i.c);
      return;
    }
    catch (Throwable paramA)
    {
      com.google.a.a.a.a.a.a.a(paramA);
    }
  }
  
  static void b(String paramString)
  {
    u = paramString;
    if (b == null) {
      return;
    }
    ap.a(ap.a, "GT_PLAYER_ID", u);
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
    N.add(paramJSONArray);
  }
  
  private static void b(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject.put("net_type", G.d());
      return;
    }
    catch (Throwable paramJSONObject) {}
  }
  
  static void b(boolean paramBoolean)
  {
    if (b == null) {
      return;
    }
    ap.a(ap.a, "OS_FILTER_OTHER_GCM_RECEIVERS", paramBoolean);
  }
  
  static boolean b(Context paramContext)
  {
    return ap.b(ap.a, "OS_FILTER_OTHER_GCM_RECEIVERS", false);
  }
  
  private static ad c(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i3 = paramJSONArray.length();
    ad localAd = new ad();
    aa localAa = new aa();
    localAa.a = p();
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
      localAd.b.b = localObject2;
      localObject3 = localAd.b;
      if (localObject2 != null) {
        paramJSONArray = ab.a.b;
      } else {
        paramJSONArray = ab.a.a;
      }
      ((ab)localObject3).a = paramJSONArray;
      if (paramBoolean2)
      {
        localAd.a.e = aa.a.b;
        return localAd;
      }
      localAd.a.e = aa.a.a;
      return localAd;
      label365:
      if (i2 == 0) {
        break label139;
      }
      i2 = 0;
      Object localObject2 = localObject3;
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
  
  private static void c(long paramLong)
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
    ap.a(ap.a, "GT_UNSENT_ACTIVE_TIME", paramLong);
  }
  
  static void c(String paramString)
  {
    v = paramString;
    if (b == null) {
      return;
    }
    String str = ap.a;
    if ("".equals(v)) {
      paramString = null;
    } else {
      paramString = v;
    }
    ap.a(str, "OS_EMAIL_ID", paramString);
  }
  
  private static boolean c(int paramInt)
  {
    return paramInt < -6;
  }
  
  static boolean c(Context paramContext)
  {
    return ap.b(ap.a, "GT_FIREBASE_TRACKING_ENABLED", false);
  }
  
  static void d(String paramString)
  {
    b(paramString);
    Q();
    a(P);
    h(b).a(paramString);
    if (aa != null)
    {
      a(aa.a, aa.b, aa.c);
      aa = null;
    }
    ar.i();
    am.a(b, a, paramString, c.a());
  }
  
  public static boolean d()
  {
    return j();
  }
  
  static boolean d(Context paramContext)
  {
    return ap.b(ap.a, "GT_VIBRATE_ENABLED", true);
  }
  
  static void e(String paramString)
  {
    c(paramString);
    i(b).a(paramString);
    try
    {
      ar.b(new JSONObject().put("parent_player_id", paramString));
      return;
    }
    catch (JSONException paramString)
    {
      com.google.a.a.a.a.a.a.a(paramString);
    }
  }
  
  static boolean e()
  {
    boolean bool1 = false;
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
      boolean bool2 = f();
      a(System.currentTimeMillis());
      l1 = o() + l1;
      c(l1);
      if ((l1 >= 60L) && (k() != null))
      {
        if (!bool2) {
          as.a(b);
        }
        as.a();
        return false;
      }
      if (l1 >= 60L) {
        bool1 = true;
      }
      return bool1;
    }
    return false;
  }
  
  static boolean e(Context paramContext)
  {
    return ap.b(ap.a, "GT_SOUND_ENABLED", true);
  }
  
  static boolean f()
  {
    boolean bool = ar.c();
    if (bool) {
      as.a(b);
    }
    return (p.a(b)) || (bool);
  }
  
  private static ah g(Context paramContext)
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
  
  static void g()
  {
    x = true;
    p.c();
    z = SystemClock.elapsedRealtime();
    S = S();
    a(System.currentTimeMillis());
    H();
    if (B != null) {
      B.a();
    }
    w.a(b);
    g(b).a();
    if ((D != null) && (c(b))) {
      D.b();
    }
    as.b(b);
  }
  
  private static OSSubscriptionState h(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (X == null)
    {
      X = new OSSubscriptionState(false, g(paramContext).b());
      g(paramContext).a.a(X);
      X.a.b(new OSSubscriptionChangedInternalObserver());
    }
    return X;
  }
  
  private static void h(String paramString)
  {
    if (b == null) {
      return;
    }
    ap.a(ap.a, "GT_APP_ID", paramString);
  }
  
  static boolean h()
  {
    return x;
  }
  
  private static z i(Context paramContext)
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
  
  static String i()
  {
    return k(b);
  }
  
  private static void j(Context paramContext)
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
  
  static boolean j()
  {
    return l(b);
  }
  
  static String k()
  {
    if ((u == null) && (b != null)) {
      u = ap.b(ap.a, "GT_PLAYER_ID", null);
    }
    return u;
  }
  
  private static String k(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return ap.b(ap.a, "GT_APP_ID", null);
  }
  
  static String l()
  {
    if ("".equals(v)) {
      return null;
    }
    if ((v == null) && (b != null)) {
      v = ap.b(ap.a, "OS_EMAIL_ID", null);
    }
    return v;
  }
  
  private static boolean l(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    return ap.b(ap.a, "ONESIGNAL_USER_PROVIDED_CONSENT", false);
  }
  
  private static String m(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return ap.b(ap.a, "GT_PLAYER_ID", null);
  }
  
  static boolean m()
  {
    if (i == null) {
      return true;
    }
    return i.i == k.c;
  }
  
  private static long n(Context paramContext)
  {
    return ap.b(ap.a, "OS_LAST_SESSION_TIME", -31000L);
  }
  
  static boolean n()
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
  
  static long o()
  {
    if ((A == -1L) && (b != null)) {
      A = ap.b(ap.a, "GT_UNSENT_ACTIVE_TIME", 0L);
    }
    h localH = h.e;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("GetUnsentActiveTime: ");
    localStringBuilder.append(A);
    a(localH, localStringBuilder.toString());
    return A;
  }
  
  static boolean p()
  {
    return (c) && (h());
  }
  
  static void q()
  {
    S = false;
    a(System.currentTimeMillis());
  }
  
  static boolean r()
  {
    if (i.f) {
      return ak.a(b);
    }
    return true;
  }
  
  static void s()
  {
    if (p != null)
    {
      p.a();
      p = null;
    }
  }
  
  static void t()
  {
    if (p != null)
    {
      p.a(new c(b.d, "Failed due to network failure. Will retry on next sync."));
      p = null;
    }
  }
  
  static void u()
  {
    if (o != null)
    {
      o.a();
      o = null;
    }
  }
  
  static void v()
  {
    if (o != null)
    {
      o.a(new c(b.d, "Failed due to network failure. Will retry on next sync."));
      o = null;
    }
  }
  
  public static class a
  {
    Context a;
    al.i b;
    al.j c;
    boolean d;
    boolean e;
    boolean f;
    boolean g;
    boolean h;
    al.k i = al.k.b;
    
    private a() {}
    
    private a(Context paramContext)
    {
      this.a = paramContext;
    }
    
    public a a(al.i paramI)
    {
      this.b = paramI;
      return this;
    }
    
    public void a()
    {
      al.a(this);
    }
  }
  
  public static enum b
  {
    private b() {}
  }
  
  public static class c
  {
    private al.b a;
    private String b;
    
    c(al.b paramB, String paramString)
    {
      this.a = paramB;
      this.b = paramString;
    }
  }
  
  public static abstract interface d
  {
    public abstract void a();
    
    public abstract void a(al.c paramC);
  }
  
  public static abstract interface e
  {
    public abstract void a(JSONObject paramJSONObject);
  }
  
  private static class f
  {
    JSONArray a;
    boolean b;
    aq.a c;
    
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
    public abstract void a(ad paramAd);
  }
  
  public static abstract interface j
  {
    public abstract void a(aa paramAa);
  }
  
  public static enum k
  {
    private k() {}
  }
}
