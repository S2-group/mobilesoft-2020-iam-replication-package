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
  private static n.f K;
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
  
  private static void E()
  {
    if (!e.isEmpty())
    {
      d = Executors.newSingleThreadExecutor(new ThreadFactory()
      {
        public Thread newThread(Runnable paramAnonymousRunnable)
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
  
  private static void F()
  {
    boolean bool = false;
    if (P) {
      return;
    }
    P = true;
    G = false;
    if (Q) {
      H = false;
    }
    G();
    I();
    if ((J) || (i.d)) {
      bool = true;
    }
    J = bool;
  }
  
  private static void G()
  {
    n.d local6 = new n.d()
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
    if ((i.d) && (!J)) {}
    for (boolean bool = true;; bool = false)
    {
      n.a(b, bool, local6);
      return;
    }
  }
  
  private static void H()
  {
    if (D == 2) {}
    for (Object localObject = new aq();; localObject = new ar())
    {
      ((ap)localObject).a(b, o, new ap.a()
      {
        public void a(String paramAnonymousString, int paramAnonymousInt)
        {
          if (paramAnonymousInt < 1) {
            if ((an.g() == null) && ((ah.v() == 1) || (ah.v() < -6))) {
              ah.a(paramAnonymousInt);
            }
          }
          for (;;)
          {
            ah.e(paramAnonymousString);
            ah.c(true);
            ah.f(ah.b).b(paramAnonymousString);
            ah.u();
            return;
            if (ah.v() < -6) {
              ah.a(paramAnonymousInt);
            }
          }
        }
      });
      return;
    }
  }
  
  private static void I()
  {
    if (I)
    {
      H();
      return;
    }
    am.a local8 = new am.a()
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
              j = ah.w() * 10000 + 30000;
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
            ah.a(ah.h.e, "Failed to get Android parameters, trying again in " + i / 1000 + " seconds.");
            Thread.sleep(i);
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
            ah.f(paramAnonymousString.getString("android_sender_id"));
          }
          ah.j = paramAnonymousString.optBoolean("enterp", false);
          ah.e(paramAnonymousString.optBoolean("use_email_auth", false));
          ah.a(paramAnonymousString.getJSONObject("awl_list"));
          boolean bool = paramAnonymousString.optBoolean("fba", false);
          al.a(al.a, "GT_FIREBASE_TRACKING_ENABLED", bool);
          p.a(ah.b, paramAnonymousString);
        }
        catch (Throwable paramAnonymousString)
        {
          for (;;)
          {
            paramAnonymousString.printStackTrace();
          }
        }
        ah.f(true);
        ah.z();
      }
    };
    String str2 = "apps/" + a + "/android_params.js";
    String str3 = i();
    String str1 = str2;
    if (str3 != null) {
      str1 = str2 + "?player_id=" + str3;
    }
    a(h.f, "Starting request to get Android parameters.");
    am.a(str1, local8);
  }
  
  private static void J()
  {
    Iterator localIterator = L.iterator();
    while (localIterator.hasNext()) {
      b((JSONArray)localIterator.next(), true, false);
    }
    L.clear();
  }
  
  private static int K()
  {
    TimeZone localTimeZone = Calendar.getInstance().getTimeZone();
    int i2 = localTimeZone.getRawOffset();
    int i1 = i2;
    if (localTimeZone.inDaylightTime(new Date())) {
      i1 = i2 + localTimeZone.getDSTSavings();
    }
    return i1 / 1000;
  }
  
  private static void L()
  {
    a(h.f, "registerUser: registerForPushFired:" + G + ", locationFired: " + H + ", awlFired: " + I);
    if ((!G) || (!H) || (!I)) {
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
  }
  
  private static void M()
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
    localJSONObject.put("timezone", K());
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
        if (i1 < ((List)localObject1).size())
        {
          ((MessageDigest)localObject3).update(((PackageInfo)((List)localObject1).get(i1)).packageName.getBytes());
          String str = Base64.encodeToString(((MessageDigest)localObject3).digest(), 2);
          if (!R.has(str)) {
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
      localJSONObject.put("net_type", E.b());
      localJSONObject.put("carrier", E.c());
      localJSONObject.put("rooted", at.a());
      an.a(localJSONObject);
      localJSONObject = new JSONObject();
      localJSONObject.put("identifier", F);
      localJSONObject.put("subscribableStatus", u);
      localJSONObject.put("androidPermission", p());
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
  
  private static void N()
  {
    if (w != null) {
      ag.a(new Runnable()
      {
        public void run() {}
      });
    }
  }
  
  private static void O()
  {
    String str = null;
    for (;;)
    {
      try
      {
        Object localObject3 = w;
        if (localObject3 == null) {
          return;
        }
        localObject3 = an.g();
        if (!an.f())
        {
          localObject3 = i();
          if (localObject3 != null)
          {
            w.a((String)localObject3, str);
            if (str != null) {
              w = null;
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
  
  private static boolean P()
  {
    if (Q) {}
    while ((System.currentTimeMillis() - m(b)) / 1000L >= 30L) {
      return true;
    }
    return false;
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
    }
    catch (Throwable paramBundle)
    {
      a(h.f, "Could not parse bundle, probably not a OneSignal notification.", paramBundle);
      return null;
    }
    a(h.f, "Not a OneSignal formatted GCM message. No 'custom' field in the bundle.");
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
      b(localJSONObject);
      a(i(), localJSONObject, paramBoolean);
      String str = j();
      if (str != null) {
        a(str, localJSONObject, paramBoolean);
      }
      return;
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
    if (u == 64537) {}
    do
    {
      return;
      if (!c) {
        break;
      }
      if (paramContext != null) {
        b = paramContext.getApplicationContext();
      }
    } while (i.b == null);
    J();
    return;
    boolean bool = paramContext instanceof Activity;
    v = bool;
    a = paramString2;
    b = paramContext.getApplicationContext();
    a(i.g);
    if (bool)
    {
      a.b = (Activity)paramContext;
      u.a(b);
    }
    for (;;)
    {
      x = SystemClock.elapsedRealtime();
      an.d();
      ((Application)b).registerActivityLifecycleCallbacks(new b());
      try
      {
        Class.forName("com.amazon.device.iap.PurchasingListener");
        A = new au(b);
        paramContext = h();
        if (paramContext != null) {
          if (!paramContext.equals(a))
          {
            a(h.f, "APP ID changed, clearing user id as it is no longer valid.");
            g(a);
            an.h();
          }
        }
        for (;;)
        {
          OSPermissionChangedInternalObserver.a(g(b));
          if ((v) || (i() == null))
          {
            Q = P();
            a(System.currentTimeMillis());
            F();
          }
          if (i.b != null) {
            J();
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
          a.a = true;
          break;
          f.a(0, b);
          g(a);
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
        if (!ah.C()) {}
        bc.a localA;
        for (boolean bool = true;; bool = false)
        {
          localA = an.c(bool);
          if (localA.a) {
            ah.g(true);
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
  
  static void a(h paramH, String paramString)
  {
    a(paramH, paramString, null);
  }
  
  static void a(h paramH, final String paramString, Throwable paramThrowable)
  {
    if (paramH.compareTo(r) < 1)
    {
      if (paramH != h.g) {
        break label148;
      }
      Log.v("OneSignal", paramString, paramThrowable);
    }
    for (;;)
    {
      if ((paramH.compareTo(q) < 1) && (a.b != null)) {}
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
        label148:
        Log.e("OneSignal", "Error showing logging message.", paramH);
      }
      if (paramH == h.f) {
        Log.d("OneSignal", paramString, paramThrowable);
      } else if (paramH == h.e) {
        Log.i("OneSignal", paramString, paramThrowable);
      } else if (paramH == h.d) {
        Log.w("OneSignal", paramString, paramThrowable);
      } else if ((paramH == h.c) || (paramH == h.b)) {
        Log.e("OneSignal", paramString, paramThrowable);
      }
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
    paramString = "players/" + paramString + "/on_focus";
    am.a local10 = new am.a()
    {
      void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        ah.a("sending on_focus Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
      }
      
      void a(String paramAnonymousString)
      {
        ah.b(0L);
      }
    };
    if (paramBoolean)
    {
      am.d(paramString, paramJSONObject, local10);
      return;
    }
    am.b(paramString, paramJSONObject, local10);
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean, am.a paramA)
  {
    if (i() == null)
    {
      Y = new f(paramJSONArray);
      Y.b = paramBoolean;
      Y.c = paramA;
    }
    for (;;)
    {
      return;
      try
      {
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("app_id", a);
        if (paramBoolean) {
          localJSONObject.put("existing", true);
        }
        localJSONObject.put("purchases", paramJSONArray);
        am.b("players/" + i() + "/on_purchase", localJSONObject, paramA);
        if (j() != null)
        {
          am.b("players/" + j() + "/on_purchase", localJSONObject, null);
          return;
        }
      }
      catch (Throwable paramJSONArray)
      {
        a(h.c, "Failed to generate JSON for sendPurchases.", paramJSONArray);
      }
    }
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramJSONArray = c(paramJSONArray, paramBoolean1, paramBoolean2);
    if ((B != null) && (c(b))) {
      B.b(paramJSONArray);
    }
    if ((i == null) || (i.c == null)) {
      return;
    }
    i.c.a(paramJSONArray.a);
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
    boolean bool1 = false;
    int i2 = paramJSONArray.length();
    int i1 = 0;
    for (;;)
    {
      boolean bool2;
      if (i1 < i2) {
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
          a(h.c, "Error parsing JSON item " + i1 + "/" + i2 + " for launching a web URL.", localThrowable);
          bool2 = bool1;
        }
      }
      return bool1;
      i1 += 1;
      bool1 = bool2;
    }
  }
  
  static boolean a(Context paramContext, JSONObject paramJSONObject)
  {
    paramJSONObject = c(paramJSONObject);
    return (paramJSONObject == null) || (a(paramJSONObject, paramContext));
  }
  
  private static boolean a(h paramH)
  {
    return (paramH.compareTo(q) < 1) || (paramH.compareTo(r) < 1);
  }
  
  /* Error */
  private static boolean a(String paramString, Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: ifnull +13 -> 17
    //   7: ldc_w 974
    //   10: aload_0
    //   11: invokevirtual 736	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   14: ifeq +5 -> 19
    //   17: iconst_0
    //   18: ireturn
    //   19: aload_1
    //   20: invokestatic 979	com/onesignal/aj:a	(Landroid/content/Context;)Lcom/onesignal/aj;
    //   23: astore_1
    //   24: aload_1
    //   25: invokevirtual 982	com/onesignal/aj:b	()Landroid/database/sqlite/SQLiteDatabase;
    //   28: ldc_w 984
    //   31: iconst_1
    //   32: anewarray 470	java/lang/String
    //   35: dup
    //   36: iconst_0
    //   37: ldc_w 986
    //   40: aastore
    //   41: ldc_w 988
    //   44: iconst_1
    //   45: anewarray 470	java/lang/String
    //   48: dup
    //   49: iconst_0
    //   50: aload_0
    //   51: aastore
    //   52: aconst_null
    //   53: aconst_null
    //   54: aconst_null
    //   55: invokevirtual 994	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   58: astore_1
    //   59: aload_1
    //   60: astore 4
    //   62: aload 4
    //   64: astore_1
    //   65: aload 4
    //   67: invokeinterface 999 1 0
    //   72: istore_3
    //   73: iload_3
    //   74: istore_2
    //   75: aload 4
    //   77: ifnull +12 -> 89
    //   80: aload 4
    //   82: invokeinterface 1002 1 0
    //   87: iload_3
    //   88: istore_2
    //   89: iload_2
    //   90: ifeq +83 -> 173
    //   93: getstatic 289	com/onesignal/ah$h:f	Lcom/onesignal/ah$h;
    //   96: new 269	java/lang/StringBuilder
    //   99: dup
    //   100: invokespecial 270	java/lang/StringBuilder:<init>	()V
    //   103: ldc_w 1004
    //   106: invokevirtual 276	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: aload_0
    //   110: invokevirtual 276	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: invokevirtual 283	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   116: invokestatic 294	com/onesignal/ah:a	(Lcom/onesignal/ah$h;Ljava/lang/String;)V
    //   119: iconst_1
    //   120: ireturn
    //   121: astore 5
    //   123: aconst_null
    //   124: astore 4
    //   126: aload 4
    //   128: astore_1
    //   129: getstatic 650	com/onesignal/ah$h:c	Lcom/onesignal/ah$h;
    //   132: ldc_w 1006
    //   135: aload 5
    //   137: invokestatic 618	com/onesignal/ah:a	(Lcom/onesignal/ah$h;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   140: aload 4
    //   142: ifnull +42 -> 184
    //   145: aload 4
    //   147: invokeinterface 1002 1 0
    //   152: iconst_0
    //   153: istore_2
    //   154: goto -65 -> 89
    //   157: astore_0
    //   158: aload 4
    //   160: astore_1
    //   161: aload_1
    //   162: ifnull +9 -> 171
    //   165: aload_1
    //   166: invokeinterface 1002 1 0
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
    for (;;)
    {
      if (i1 < paramJSONArray.length()) {
        try
        {
          String str = new JSONObject(paramJSONArray.getJSONObject(i1).optString("custom", null)).optString("i", null);
          if (M.contains(str)) {
            break label153;
          }
          M.add(str);
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("app_id", k(paramContext));
          localJSONObject.put("player_id", l(paramContext));
          localJSONObject.put("opened", true);
          am.a("notifications/" + str, localJSONObject, new am.a()
          {
            void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
            {
              ah.a("sending Notification Opened Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
            }
          });
        }
        catch (Throwable localThrowable)
        {
          a(h.c, "Failed to generate JSON to send notification opened.", localThrowable);
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
    if ("".equals(t)) {}
    for (paramString = null;; paramString = t)
    {
      al.a(str, "OS_EMAIL_ID", paramString);
      return;
    }
  }
  
  private static void b(String paramString1, int paramInt, Throwable paramThrowable, String paramString2)
  {
    String str2 = "";
    String str1 = str2;
    if (paramString2 != null)
    {
      str1 = str2;
      if (a(h.e)) {
        str1 = "\n" + paramString2 + "\n";
      }
    }
    a(h.d, "HTTP code: " + paramInt + " " + paramString1 + str1, paramThrowable);
  }
  
  private static void b(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((i == null) || (i.b == null))
    {
      L.add(paramJSONArray);
      return;
    }
    a(c(paramJSONArray, paramBoolean1, paramBoolean2));
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
  
  static boolean b(Context paramContext)
  {
    return al.b(al.a, "OS_FILTER_OTHER_GCM_RECEIVERS", false);
  }
  
  private static aa c(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject1 = null;
    int i3 = paramJSONArray.length();
    int i1 = 1;
    aa localAa = new aa();
    y localY = new y();
    localY.a = n();
    localY.b = paramBoolean1;
    localY.c = paramJSONArray.optJSONObject(0).optInt("notificationId");
    int i2 = 0;
    if (i2 < i3) {}
    try
    {
      Object localObject2 = paramJSONArray.getJSONObject(i2);
      localY.d = o.a((JSONObject)localObject2);
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
        z localZ;
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
        if (localY.f == null) {
          localY.f = new ArrayList();
        }
        localY.f.add(localY.d);
      }
      catch (Throwable localThrowable1) {}
      a(h.c, "Error parsing JSON item " + i2 + "/" + i3 + " for callback.", localThrowable1);
    }
    localAa.a = localY;
    localAa.b = new z();
    localAa.b.b = localObject1;
    localZ = localAa.b;
    if (localObject1 != null) {}
    for (paramJSONArray = z.a.b;; paramJSONArray = z.a.a)
    {
      localZ.a = paramJSONArray;
      if (!paramBoolean2) {
        break;
      }
      localAa.a.e = y.a.b;
      return localAa;
    }
    localAa.a.e = y.a.a;
    return localAa;
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
    y = paramLong;
    if (b == null) {
      return;
    }
    a(h.e, "SaveUnsentActiveTime: " + y);
    al.a(al.a, "GT_UNSENT_ACTIVE_TIME", paramLong);
  }
  
  static void c(String paramString)
  {
    a(paramString);
    N();
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
  
  static boolean d()
  {
    v = false;
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
          if (A != null) {
            A.a();
          }
        } while (x == -1L);
        l1 = ((SystemClock.elapsedRealtime() - x) / 1000.0D + 0.5D);
        x = SystemClock.elapsedRealtime();
      } while ((l1 < 0L) || (l1 > 86400L));
      if (b == null)
      {
        a(h.c, "Android Context not found, please call OneSignal.init when your app starts.");
        return false;
      }
      bool = e();
      a(System.currentTimeMillis());
      l1 += m();
      c(l1);
      if ((l1 >= 60L) && (i() != null)) {
        break;
      }
    } while (l1 < 60L);
    return true;
    if (!bool) {
      ao.a(b);
    }
    ao.a();
    return false;
  }
  
  static boolean d(Context paramContext)
  {
    return al.b(al.a, "GT_VIBRATE_ENABLED", true);
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
    Q = P();
    a(System.currentTimeMillis());
    F();
    if (z != null) {
      z.a();
    }
    u.a(b);
    g(b).a();
    if ((B != null) && (c(b))) {
      B.b();
    }
    ao.b(b);
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
  
  private static void g(String paramString)
  {
    if (b == null) {
      return;
    }
    al.a(al.a, "GT_APP_ID", paramString);
  }
  
  static boolean g()
  {
    return v;
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
    return k(b);
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
  
  static String i()
  {
    if ((s == null) && (b != null)) {
      s = al.b(al.a, "GT_PLAYER_ID", null);
    }
    return s;
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
  
  private static void j(Context paramContext)
  {
    Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
    if (localIntent != null)
    {
      localIntent.setFlags(268566528);
      paramContext.startActivity(localIntent);
    }
  }
  
  private static String k(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return al.b(al.a, "GT_APP_ID", null);
  }
  
  static boolean k()
  {
    if (i == null) {}
    while (i.i == k.c) {
      return true;
    }
    return false;
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
    if (i == null) {}
    while (i.i != k.b) {
      return false;
    }
    return true;
  }
  
  static long m()
  {
    if ((y == -1L) && (b != null)) {
      y = al.b(al.a, "GT_UNSENT_ACTIVE_TIME", 0L);
    }
    a(h.e, "GetUnsentActiveTime: " + y);
    return y;
  }
  
  private static long m(Context paramContext)
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
}
