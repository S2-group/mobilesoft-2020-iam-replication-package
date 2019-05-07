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
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
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
  private static long A;
  private static ba B;
  private static ay C;
  private static az D;
  private static d E;
  private static int F;
  private static ai G;
  private static String H;
  private static boolean I;
  private static boolean J;
  private static boolean K;
  private static boolean L;
  private static p.f M;
  private static Collection<JSONArray> N;
  private static HashSet<String> O;
  private static f P;
  private static boolean Q;
  private static boolean R;
  private static boolean S;
  private static JSONObject T;
  private static boolean U;
  private static af V;
  private static ae<Object, ag> W;
  private static OSSubscriptionState X;
  private static ae<Object, ah> Y;
  private static z Z;
  static String a;
  private static g aa;
  private static ar ab;
  private static int ac = 0;
  static Context b;
  static boolean c;
  static ExecutorService d;
  public static ConcurrentLinkedQueue<Runnable> e;
  static AtomicLong f;
  public static String g;
  static boolean h;
  static a i;
  static boolean j;
  static boolean k;
  static k l;
  static af m;
  static OSSubscriptionState n;
  private static e o;
  private static e p;
  private static String q;
  private static boolean r;
  private static i s = i.a;
  private static i t = i.d;
  private static String u = null;
  private static String v = null;
  private static int w;
  private static boolean x;
  private static h y;
  private static long z;
  
  static
  {
    e = new ConcurrentLinkedQueue();
    f = new AtomicLong();
    z = 1L;
    A = -1L;
    E = new c();
    g = "native";
    h = true;
    N = new ArrayList();
    O = new HashSet();
    k = false;
  }
  
  public aj() {}
  
  private static void H()
  {
    if (!e.isEmpty())
    {
      d = Executors.newSingleThreadExecutor(new ThreadFactory()
      {
        public Thread newThread(@NonNull Runnable paramAnonymousRunnable)
        {
          paramAnonymousRunnable = new Thread(paramAnonymousRunnable);
          paramAnonymousRunnable.setName("OS_PENDING_EXECUTOR_" + paramAnonymousRunnable.getId());
          return paramAnonymousRunnable;
        }
      });
      while (!e.isEmpty()) {
        d.submit((Runnable)e.poll());
      }
    }
  }
  
  private static void I()
  {
    boolean bool = false;
    if (R) {
      return;
    }
    R = true;
    I = false;
    if (S) {
      J = false;
    }
    J();
    M();
    if ((L) || (i.d)) {
      bool = true;
    }
    L = bool;
  }
  
  private static void J()
  {
    p.d local6 = new p.d()
    {
      public p.a a()
      {
        return p.a.a;
      }
      
      public void a(p.f paramAnonymousF)
      {
        aj.a(paramAnonymousF);
        aj.c(true);
        aj.x();
      }
    };
    if ((i.d) && (!L)) {}
    for (boolean bool = true;; bool = false)
    {
      p.a(b, bool, local6);
      return;
    }
  }
  
  private static ar K()
  {
    if (ab != null) {
      return ab;
    }
    if (F == 2) {
      ab = new as();
    }
    for (;;)
    {
      return ab;
      if (ai.a()) {
        ab = new au();
      } else {
        ab = new av();
      }
    }
  }
  
  private static void L()
  {
    K().a(b, q, new ar.a()
    {
      public void a(String paramAnonymousString, int paramAnonymousInt)
      {
        if (paramAnonymousInt < 1) {
          if ((ap.g() == null) && ((aj.y() == 1) || (aj.a(aj.y())))) {
            aj.b(paramAnonymousInt);
          }
        }
        for (;;)
        {
          aj.f(paramAnonymousString);
          aj.d(true);
          aj.g(aj.b).b(paramAnonymousString);
          aj.x();
          return;
          if (aj.a(aj.y())) {
            aj.b(paramAnonymousInt);
          }
        }
      }
    });
  }
  
  private static void M()
  {
    if (K)
    {
      L();
      return;
    }
    ao.a local8 = new ao.a()
    {
      void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        new Thread(new Runnable()
        {
          public void run()
          {
            i = 90000;
            try
            {
              j = aj.z() * 10000 + 30000;
              if (j <= 90000) {
                break label72;
              }
            }
            catch (Throwable localThrowable)
            {
              for (;;)
              {
                int j;
                continue;
                i = j;
              }
            }
            aj.a(aj.i.e, "Failed to get Android parameters, trying again in " + i / 1000 + " seconds.");
            Thread.sleep(i);
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
            aj.e(true);
            aj.g(paramAnonymousString.getString("android_sender_id"));
          }
          aj.j = paramAnonymousString.optBoolean("enterp", false);
          aj.f(paramAnonymousString.optBoolean("use_email_auth", false));
          aj.a(paramAnonymousString.getJSONObject("awl_list"));
          boolean bool = paramAnonymousString.optBoolean("fba", false);
          an.a(an.a, "GT_FIREBASE_TRACKING_ENABLED", bool);
          r.a(aj.b, paramAnonymousString);
        }
        catch (Throwable paramAnonymousString)
        {
          for (;;)
          {
            paramAnonymousString.printStackTrace();
          }
        }
        aj.g(true);
        aj.C();
      }
    };
    String str2 = "apps/" + a + "/android_params.js";
    String str3 = l();
    String str1 = str2;
    if (str3 != null) {
      str1 = str2 + "?player_id=" + str3;
    }
    a(i.f, "Starting request to get Android parameters.");
    ao.a(str1, local8);
  }
  
  private static void N()
  {
    Iterator localIterator = N.iterator();
    while (localIterator.hasNext()) {
      b((JSONArray)localIterator.next(), true, false);
    }
    N.clear();
  }
  
  private static int O()
  {
    TimeZone localTimeZone = Calendar.getInstance().getTimeZone();
    int i2 = localTimeZone.getRawOffset();
    int i1 = i2;
    if (localTimeZone.inDaylightTime(new Date())) {
      i1 = i2 + localTimeZone.getDSTSavings();
    }
    return i1 / 1000;
  }
  
  private static void P()
  {
    a(i.f, "registerUser: registerForPushFired:" + I + ", locationFired: " + J + ", awlFired: " + K);
    if ((!I) || (!J) || (!K)) {
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
          aj.a(aj.i.b, "FATAL Error registering device!", localJSONException);
        }
      }
    }, "OS_REG_USER").start();
  }
  
  private static void Q()
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
    localJSONObject.put("timezone", O());
    localJSONObject.put("language", ai.f());
    localJSONObject.put("sdk", "031001");
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
        if (i1 < ((List)localObject1).size())
        {
          ((MessageDigest)localObject3).update(((PackageInfo)((List)localObject1).get(i1)).packageName.getBytes());
          String str = Base64.encodeToString(((MessageDigest)localObject3).digest(), 2);
          if (!T.has(str)) {
            break label388;
          }
          ((JSONArray)localObject2).put(str);
          break label388;
        }
        localJSONObject.put("pkgs", localObject2);
      }
      catch (Throwable localThrowable)
      {
        for (;;) {}
      }
      localJSONObject.put("net_type", G.d());
      localJSONObject.put("carrier", G.e());
      localJSONObject.put("rooted", ax.a());
      ap.a(localJSONObject);
      localJSONObject = new JSONObject();
      localJSONObject.put("identifier", H);
      localJSONObject.put("subscribableStatus", w);
      localJSONObject.put("androidPermission", s());
      localJSONObject.put("device_type", F);
      ap.b(localJSONObject);
      if ((h) && (M != null)) {
        ap.a(M);
      }
      if (S) {
        ap.j();
      }
      R = false;
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;)
      {
        int i1;
        continue;
        label388:
        i1 += 1;
      }
    }
  }
  
  private static void R()
  {
    String str = null;
    for (;;)
    {
      try
      {
        Object localObject3 = y;
        if (localObject3 == null) {
          return;
        }
        localObject3 = ap.g();
        if (!ap.f())
        {
          localObject3 = l();
          if (localObject3 != null)
          {
            y.a((String)localObject3, str);
            if (str != null) {
              y = null;
            }
          }
        }
        else
        {
          Object localObject2 = localObject3;
        }
      }
      finally {}
    }
  }
  
  private static boolean S()
  {
    if (S) {}
    while ((System.currentTimeMillis() - o(b)) / 1000L >= 30L) {
      return true;
    }
    return false;
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
      if (paramBundle.containsKey("custom"))
      {
        paramBundle = new JSONObject(paramBundle.getString("custom"));
        if (paramBundle.has("i")) {
          return paramBundle.optString("i", null);
        }
        a(i.f, "Not a OneSignal formatted GCM message. No 'i' field in custom.");
        return null;
      }
    }
    catch (Throwable paramBundle)
    {
      a(i.f, "Could not parse bundle, probably not a OneSignal notification.", paramBundle);
      return null;
    }
    a(i.f, "Not a OneSignal formatted GCM message. No 'custom' field in the bundle.");
    return null;
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
      b(localJSONObject);
      a(l(), localJSONObject, paramBoolean);
      String str = m();
      if (str != null) {
        a(str, localJSONObject, paramBoolean);
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      a(i.c, "Generating on_focus:JSON Failed.", localThrowable);
    }
  }
  
  static void a(Context paramContext)
  {
    b = paramContext.getApplicationContext();
    an.b();
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, j paramJ, k paramK)
  {
    a(paramContext);
    if ((k) && (!d()))
    {
      a(i.g, "OneSignal SDK initialization delayed, user privacy consent is set to required for this application.");
      l = new k(paramContext, paramString1, paramString2, paramJ, paramK);
    }
    do
    {
      do
      {
        return;
        i = c();
        i.h = false;
        i.b = paramJ;
        i.c = paramK;
        if (!r) {
          q = paramString1;
        }
        G = new ai();
        F = G.c();
        w = G.a(paramContext, F, paramString2);
      } while (w == 64537);
      if (!c) {
        break;
      }
    } while (i.b == null);
    N();
    return;
    boolean bool = paramContext instanceof Activity;
    x = bool;
    a = paramString2;
    b(i.g);
    if (bool)
    {
      a.b = (Activity)paramContext;
      w.a(b);
    }
    for (;;)
    {
      z = SystemClock.elapsedRealtime();
      ap.d();
      ((Application)b).registerActivityLifecycleCallbacks(new b());
      try
      {
        Class.forName("com.amazon.device.iap.PurchasingListener");
        C = new ay(b);
        paramContext = j();
        if (paramContext != null) {
          if (!paramContext.equals(a))
          {
            a(i.f, "APP ID changed, clearing user id as it is no longer valid.");
            h(a);
            ap.h();
          }
        }
        for (;;)
        {
          OSPermissionChangedInternalObserver.a(h(b));
          if ((x) || (l() == null))
          {
            S = S();
            a(System.currentTimeMillis());
            I();
          }
          if (i.b != null) {
            N();
          }
          if (ba.a(b)) {
            B = new ba(b);
          }
          if (az.a()) {
            D = new az(b);
          }
          au.a(b);
          c = true;
          H();
          return;
          a.a = true;
          break;
          f.a(0, b);
          h(a);
        }
      }
      catch (ClassNotFoundException paramContext)
      {
        for (;;) {}
      }
    }
  }
  
  public static void a(Context paramContext, JSONArray paramJSONArray, boolean paramBoolean)
  {
    if (a(null)) {}
    boolean bool1;
    boolean bool2;
    do
    {
      return;
      b(paramContext, paramJSONArray);
      if ((D != null) && (d(b))) {
        D.a(c(paramJSONArray, true, paramBoolean));
      }
      bool1 = false;
      bool2 = "DISABLE".equals(ai.a(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
      if (!bool2) {
        bool1 = a(paramContext, paramJSONArray);
      }
      b(paramJSONArray, true, paramBoolean);
    } while ((paramBoolean) || (bool1) || (bool2));
    k(paramContext);
  }
  
  private static void a(ac paramAc)
  {
    ai.a(new Runnable()
    {
      public void run()
      {
        aj.i.b.a(this.a);
      }
    });
  }
  
  private static void a(f paramF)
  {
    if (paramF == null) {
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        if (!aj.F()) {}
        bg.a localA;
        for (boolean bool = true;; bool = false)
        {
          localA = ap.c(bool);
          if (localA.a) {
            aj.h(true);
          }
          if ((localA.b != null) && (!localA.toString().equals("{}"))) {
            break;
          }
          this.a.a(null);
          return;
        }
        this.a.a(localA.b);
      }
    }, "OS_GETTAGS_CALLBACK").start();
  }
  
  static void a(i paramI, String paramString)
  {
    a(paramI, paramString, null);
  }
  
  static void a(i paramI, final String paramString, Throwable paramThrowable)
  {
    if (paramI.compareTo(t) < 1)
    {
      if (paramI != i.g) {
        break label148;
      }
      Log.v("OneSignal", paramString, paramThrowable);
    }
    for (;;)
    {
      if ((paramI.compareTo(s) < 1) && (a.b != null)) {}
      try
      {
        Object localObject = paramString + "\n";
        paramString = (String)localObject;
        if (paramThrowable != null)
        {
          paramString = (String)localObject + paramThrowable.getMessage();
          localObject = new StringWriter();
          paramThrowable.printStackTrace(new PrintWriter((Writer)localObject));
          paramString = paramString + ((StringWriter)localObject).toString();
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
      catch (Throwable paramI)
      {
        label148:
        Log.e("OneSignal", "Error showing logging message.", paramI);
      }
      if (paramI == i.f) {
        Log.d("OneSignal", paramString, paramThrowable);
      } else if (paramI == i.e) {
        Log.i("OneSignal", paramString, paramThrowable);
      } else if (paramI == i.d) {
        Log.w("OneSignal", paramString, paramThrowable);
      } else if ((paramI == i.c) || (paramI == i.b)) {
        Log.e("OneSignal", paramString, paramThrowable);
      }
    }
  }
  
  private static void a(String paramString, JSONObject paramJSONObject, boolean paramBoolean)
  {
    paramString = "players/" + paramString + "/on_focus";
    ao.a local10 = new ao.a()
    {
      void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        aj.a("sending on_focus Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
      }
      
      void a(String paramAnonymousString)
      {
        aj.b(0L);
      }
    };
    if (paramBoolean)
    {
      ao.d(paramString, paramJSONObject, local10);
      return;
    }
    ao.b(paramString, paramJSONObject, local10);
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean, ao.a paramA)
  {
    if (a("sendPurchases()")) {}
    for (;;)
    {
      return;
      if (l() == null)
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
        ao.b("players/" + l() + "/on_purchase", localJSONObject, paramA);
        if (m() != null)
        {
          ao.b("players/" + m() + "/on_purchase", localJSONObject, null);
          return;
        }
      }
      catch (Throwable paramJSONArray)
      {
        a(i.c, "Failed to generate JSON for sendPurchases.", paramJSONArray);
      }
    }
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramJSONArray = c(paramJSONArray, paramBoolean1, paramBoolean2);
    if ((D != null) && (d(b))) {
      D.b(paramJSONArray);
    }
    if ((i == null) || (i.c == null)) {
      return;
    }
    i.c.a(paramJSONArray.a);
  }
  
  public static void a(boolean paramBoolean)
  {
    if ((k) && (!paramBoolean))
    {
      a(i.c, "Cannot change requiresUserPrivacyConsent() from TRUE to FALSE");
      return;
    }
    k = paramBoolean;
  }
  
  private static boolean a(Context paramContext, JSONArray paramJSONArray)
  {
    boolean bool1 = false;
    boolean bool2 = false;
    if (a(null)) {
      return bool2;
    }
    int i2 = paramJSONArray.length();
    int i1 = 0;
    for (;;)
    {
      bool2 = bool1;
      if (i1 >= i2) {
        break;
      }
      try
      {
        Object localObject = paramJSONArray.getJSONObject(i1);
        if (!((JSONObject)localObject).has("custom"))
        {
          bool2 = bool1;
        }
        else
        {
          localObject = new JSONObject(((JSONObject)localObject).optString("custom"));
          bool2 = bool1;
          if (((JSONObject)localObject).has("u"))
          {
            String str = ((JSONObject)localObject).optString("u", null);
            localObject = str;
            if (!str.contains("://")) {
              localObject = "http://" + str;
            }
            localObject = new Intent("android.intent.action.VIEW", Uri.parse(((String)localObject).trim()));
            ((Intent)localObject).addFlags(1476919296);
            paramContext.startActivity((Intent)localObject);
            bool2 = true;
          }
        }
      }
      catch (Throwable localThrowable)
      {
        a(i.c, "Error parsing JSON item " + i1 + "/" + i2 + " for launching a web URL.", localThrowable);
        bool2 = bool1;
      }
      i1 += 1;
      bool1 = bool2;
    }
  }
  
  static boolean a(Context paramContext, JSONObject paramJSONObject)
  {
    paramJSONObject = c(paramJSONObject);
    return (paramJSONObject == null) || (a(paramJSONObject, paramContext));
  }
  
  private static boolean a(i paramI)
  {
    return (paramI.compareTo(s) < 1) || (paramI.compareTo(t) < 1);
  }
  
  static boolean a(String paramString)
  {
    if ((k) && (!d()))
    {
      if (paramString != null) {
        a(i.d, "Method " + paramString + " was called before the user provided privacy consent. Your application is set to require the user's privacy consent before the OneSignal SDK can be initialized. Please ensure the user has provided consent before calling this method. You can check the latest OneSignal consent status by calling OneSignal.userProvidedPrivacyConsent()");
      }
      return true;
    }
    return false;
  }
  
  /* Error */
  private static boolean a(String paramString, Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: ifnull +13 -> 17
    //   7: ldc_w 1008
    //   10: aload_0
    //   11: invokevirtual 765	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   14: ifeq +5 -> 19
    //   17: iconst_0
    //   18: ireturn
    //   19: aload_1
    //   20: invokestatic 1013	com/onesignal/al:a	(Landroid/content/Context;)Lcom/onesignal/al;
    //   23: astore_1
    //   24: aload_1
    //   25: invokevirtual 1016	com/onesignal/al:b	()Landroid/database/sqlite/SQLiteDatabase;
    //   28: ldc_w 1018
    //   31: iconst_1
    //   32: anewarray 494	java/lang/String
    //   35: dup
    //   36: iconst_0
    //   37: ldc_w 1020
    //   40: aastore
    //   41: ldc_w 1022
    //   44: iconst_1
    //   45: anewarray 494	java/lang/String
    //   48: dup
    //   49: iconst_0
    //   50: aload_0
    //   51: aastore
    //   52: aconst_null
    //   53: aconst_null
    //   54: aconst_null
    //   55: invokevirtual 1028	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   58: astore_1
    //   59: aload_1
    //   60: astore 4
    //   62: aload 4
    //   64: astore_1
    //   65: aload 4
    //   67: invokeinterface 1033 1 0
    //   72: istore_3
    //   73: iload_3
    //   74: istore_2
    //   75: aload 4
    //   77: ifnull +12 -> 89
    //   80: aload 4
    //   82: invokeinterface 1036 1 0
    //   87: iload_3
    //   88: istore_2
    //   89: iload_2
    //   90: ifeq +83 -> 173
    //   93: getstatic 316	com/onesignal/aj$i:f	Lcom/onesignal/aj$i;
    //   96: new 296	java/lang/StringBuilder
    //   99: dup
    //   100: invokespecial 297	java/lang/StringBuilder:<init>	()V
    //   103: ldc_w 1038
    //   106: invokevirtual 303	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: aload_0
    //   110: invokevirtual 303	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: invokevirtual 310	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   116: invokestatic 321	com/onesignal/aj:a	(Lcom/onesignal/aj$i;Ljava/lang/String;)V
    //   119: iconst_1
    //   120: ireturn
    //   121: astore 5
    //   123: aconst_null
    //   124: astore 4
    //   126: aload 4
    //   128: astore_1
    //   129: getstatic 664	com/onesignal/aj$i:c	Lcom/onesignal/aj$i;
    //   132: ldc_w 1040
    //   135: aload 5
    //   137: invokestatic 632	com/onesignal/aj:a	(Lcom/onesignal/aj$i;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   140: aload 4
    //   142: ifnull +42 -> 184
    //   145: aload 4
    //   147: invokeinterface 1036 1 0
    //   152: iconst_0
    //   153: istore_2
    //   154: goto -65 -> 89
    //   157: astore_0
    //   158: aload 4
    //   160: astore_1
    //   161: aload_1
    //   162: ifnull +9 -> 171
    //   165: aload_1
    //   166: invokeinterface 1036 1 0
    //   171: aload_0
    //   172: athrow
    //   173: iconst_0
    //   174: ireturn
    //   175: astore_0
    //   176: goto -15 -> 161
    //   179: astore 5
    //   181: goto -55 -> 126
    //   184: iconst_0
    //   185: istore_2
    //   186: goto -97 -> 89
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	189	0	paramString	String
    //   0	189	1	paramContext	Context
    //   74	112	2	bool1	boolean
    //   72	16	3	bool2	boolean
    //   1	158	4	localContext	Context
    //   121	15	5	localThrowable1	Throwable
    //   179	1	5	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   24	59	121	java/lang/Throwable
    //   24	59	157	finally
    //   65	73	175	finally
    //   129	140	175	finally
    //   65	73	179	java/lang/Throwable
  }
  
  static ae<Object, ah> b()
  {
    if (Y == null) {
      Y = new ae("onOSSubscriptionChanged", true);
    }
    return Y;
  }
  
  public static a b(Context paramContext)
  {
    return new a(paramContext, null);
  }
  
  private static void b(Context paramContext, JSONArray paramJSONArray)
  {
    int i1 = 0;
    for (;;)
    {
      if (i1 < paramJSONArray.length()) {
        try
        {
          String str = new JSONObject(paramJSONArray.getJSONObject(i1).optString("custom", null)).optString("i", null);
          if (O.contains(str)) {
            break label153;
          }
          O.add(str);
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("app_id", l(paramContext));
          localJSONObject.put("player_id", n(paramContext));
          localJSONObject.put("opened", true);
          ao.a("notifications/" + str, localJSONObject, new ao.a()
          {
            void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
            {
              aj.a("sending Notification Opened Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
            }
          });
        }
        catch (Throwable localThrowable)
        {
          a(i.c, "Failed to generate JSON to send notification opened.", localThrowable);
        }
      }
      return;
      label153:
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
      paramA.printStackTrace();
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
    String str2 = "";
    String str1 = str2;
    if (paramString2 != null)
    {
      str1 = str2;
      if (a(i.e)) {
        str1 = "\n" + paramString2 + "\n";
      }
    }
    a(i.d, "HTTP code: " + paramInt + " " + paramString1 + str1, paramThrowable);
  }
  
  private static void b(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((i == null) || (i.b == null))
    {
      N.add(paramJSONArray);
      return;
    }
    a(c(paramJSONArray, paramBoolean1, paramBoolean2));
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
    an.a(an.a, "OS_FILTER_OTHER_GCM_RECEIVERS", paramBoolean);
  }
  
  @NonNull
  private static ac c(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject1 = null;
    int i3 = paramJSONArray.length();
    int i1 = 1;
    ac localAc = new ac();
    aa localAa = new aa();
    localAa.a = q();
    localAa.b = paramBoolean1;
    localAa.c = paramJSONArray.optJSONObject(0).optInt("notificationId");
    int i2 = 0;
    if (i2 < i3) {}
    try
    {
      Object localObject2 = paramJSONArray.getJSONObject(i2);
      localAa.d = q.a((JSONObject)localObject2);
      if ((localObject1 != null) || (!((JSONObject)localObject2).has("actionSelected"))) {
        break label318;
      }
      localObject2 = ((JSONObject)localObject2).optString("actionSelected", null);
      localObject1 = localObject2;
    }
    catch (Throwable localThrowable2)
    {
      for (;;)
      {
        ab localAb;
      }
    }
    if (i1 != 0) {
      i1 = 0;
    }
    for (;;)
    {
      i2 += 1;
      break;
      try
      {
        if (localAa.f == null) {
          localAa.f = new ArrayList();
        }
        localAa.f.add(localAa.d);
      }
      catch (Throwable localThrowable1) {}
      a(i.c, "Error parsing JSON item " + i2 + "/" + i3 + " for callback.", localThrowable1);
    }
    localAc.a = localAa;
    localAc.b = new ab();
    localAc.b.b = localObject1;
    localAb = localAc.b;
    if (localObject1 != null) {}
    for (paramJSONArray = ab.a.b;; paramJSONArray = ab.a.a)
    {
      localAb.a = paramJSONArray;
      if (!paramBoolean2) {
        break;
      }
      localAc.a.e = aa.a.b;
      return localAc;
    }
    localAc.a.e = aa.a.a;
    return localAc;
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
    a(i.e, "SaveUnsentActiveTime: " + A);
    an.a(an.a, "GT_UNSENT_ACTIVE_TIME", paramLong);
  }
  
  static void c(String paramString)
  {
    v = paramString;
    if (b == null) {
      return;
    }
    String str = an.a;
    if ("".equals(v)) {}
    for (paramString = null;; paramString = v)
    {
      an.a(str, "OS_EMAIL_ID", paramString);
      return;
    }
  }
  
  private static boolean c(int paramInt)
  {
    return paramInt < -6;
  }
  
  static boolean c(Context paramContext)
  {
    return an.b(an.a, "OS_FILTER_OTHER_GCM_RECEIVERS", false);
  }
  
  static void d(String paramString)
  {
    b(paramString);
    i();
    a(P);
    i(b).a(paramString);
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
    return k();
  }
  
  static boolean d(Context paramContext)
  {
    return an.b(an.a, "GT_FIREBASE_TRACKING_ENABLED", false);
  }
  
  static void e(String paramString)
  {
    c(paramString);
    j(b).a(paramString);
    try
    {
      ap.b(new JSONObject().put("parent_player_id", paramString));
      return;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  @WorkerThread
  static boolean e()
  {
    x = false;
    p.c();
    if (!c) {}
    long l1;
    boolean bool;
    do
    {
      do
      {
        do
        {
          return false;
          if (C != null) {
            C.a();
          }
        } while (z == -1L);
        l1 = ((SystemClock.elapsedRealtime() - z) / 1000.0D + 0.5D);
        z = SystemClock.elapsedRealtime();
      } while ((l1 < 0L) || (l1 > 86400L));
      if (b == null)
      {
        a(i.c, "Android Context not found, please call OneSignal.init when your app starts.");
        return false;
      }
      bool = f();
      a(System.currentTimeMillis());
      l1 += p();
      c(l1);
      if ((l1 >= 60L) && (l() != null)) {
        break;
      }
    } while (l1 < 60L);
    return true;
    if (!bool) {
      aq.a(b);
    }
    aq.a();
    return false;
  }
  
  static boolean e(Context paramContext)
  {
    return an.b(an.a, "GT_VIBRATE_ENABLED", true);
  }
  
  static boolean f()
  {
    boolean bool = ap.c();
    if (bool) {
      aq.a(b);
    }
    return (p.a(b)) || (bool);
  }
  
  static boolean f(Context paramContext)
  {
    return an.b(an.a, "GT_SOUND_ENABLED", true);
  }
  
  static void g()
  {
    x = true;
    p.c();
    z = SystemClock.elapsedRealtime();
    S = S();
    a(System.currentTimeMillis());
    I();
    if (B != null) {
      B.a();
    }
    w.a(b);
    h(b).a();
    if ((D != null) && (d(b))) {
      D.b();
    }
    aq.b(b);
  }
  
  private static af h(Context paramContext)
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
  
  static void i()
  {
    if (y != null) {
      ai.a(new Runnable()
      {
        public void run() {}
      });
    }
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
  
  static String j()
  {
    return l(b);
  }
  
  private static void k(Context paramContext)
  {
    if (a(null)) {}
    Intent localIntent;
    do
    {
      return;
      localIntent = paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
    } while (localIntent == null);
    localIntent.setFlags(268566528);
    paramContext.startActivity(localIntent);
  }
  
  static boolean k()
  {
    return m(b);
  }
  
  static String l()
  {
    if ((u == null) && (b != null)) {
      u = an.b(an.a, "GT_PLAYER_ID", null);
    }
    return u;
  }
  
  private static String l(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return an.b(an.a, "GT_APP_ID", null);
  }
  
  static String m()
  {
    if ("".equals(v)) {
      return null;
    }
    if ((v == null) && (b != null)) {
      v = an.b(an.a, "OS_EMAIL_ID", null);
    }
    return v;
  }
  
  private static boolean m(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    return an.b(an.a, "ONESIGNAL_USER_PROVIDED_CONSENT", false);
  }
  
  private static String n(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return an.b(an.a, "GT_PLAYER_ID", null);
  }
  
  static boolean n()
  {
    if (i == null) {}
    while (i.i == l.c) {
      return true;
    }
    return false;
  }
  
  private static long o(Context paramContext)
  {
    return an.b(an.a, "OS_LAST_SESSION_TIME", -31000L);
  }
  
  static boolean o()
  {
    if (i == null) {}
    while (i.i != l.b) {
      return false;
    }
    return true;
  }
  
  static long p()
  {
    if ((A == -1L) && (b != null)) {
      A = an.b(an.a, "GT_UNSENT_ACTIVE_TIME", 0L);
    }
    a(i.e, "GetUnsentActiveTime: " + A);
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
    if (i.f) {
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
      p.a(new d(c.d, "Failed due to network failure. Will retry on next sync."));
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
      o.a(new d(c.d, "Failed due to network failure. Will retry on next sync."));
      o = null;
    }
  }
  
  public static class a
  {
    Context a;
    aj.j b;
    aj.k c;
    boolean d;
    boolean e;
    boolean f;
    boolean g;
    boolean h;
    aj.l i = aj.l.b;
    
    private a() {}
    
    private a(Context paramContext)
    {
      this.a = paramContext;
    }
    
    public void a()
    {
      aj.a(this);
    }
  }
  
  public static abstract interface b
  {
    public abstract void a(aj.m paramM);
    
    public abstract void a(JSONObject paramJSONObject);
  }
  
  public static enum c
  {
    private c() {}
  }
  
  public static class d
  {
    private aj.c a;
    private String b;
    
    d(aj.c paramC, String paramString)
    {
      this.a = paramC;
      this.b = paramString;
    }
  }
  
  public static abstract interface e
  {
    public abstract void a();
    
    public abstract void a(aj.d paramD);
  }
  
  public static abstract interface f
  {
    public abstract void a(JSONObject paramJSONObject);
  }
  
  private static class g
  {
    JSONArray a;
    boolean b;
    ao.a c;
    
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
    public abstract void a(ac paramAc);
  }
  
  public static abstract interface k
  {
    public abstract void a(aa paramAa);
  }
  
  public static enum l
  {
    private l() {}
  }
  
  public static class m
  {
    private String a;
    private int b;
    
    m(int paramInt, String paramString)
    {
      this.a = paramString;
      this.b = paramInt;
    }
  }
}
