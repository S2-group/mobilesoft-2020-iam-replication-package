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
import org.json.JSONObject;

public class OneSignal
{
  private static int A;
  private static ac B;
  private static String C;
  private static boolean D;
  private static boolean E;
  private static boolean F;
  private static boolean G;
  private static n.e H;
  private static Collection<JSONArray> I = new ArrayList();
  private static HashSet<String> J = new HashSet();
  private static b K;
  private static boolean L;
  private static boolean M;
  private static boolean N;
  private static JSONObject O;
  private static z P;
  private static y<Object, aa> Q;
  private static OSSubscriptionState R;
  private static y<Object, ab> S;
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
  static z k;
  static OSSubscriptionState l;
  private static String m;
  private static boolean n;
  private static LOG_LEVEL o = LOG_LEVEL.a;
  private static LOG_LEVEL p = LOG_LEVEL.d;
  private static String q = null;
  private static int r;
  private static boolean s;
  private static d t;
  private static long u;
  private static long v;
  private static ar w;
  private static ap x;
  private static aq y;
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
  
  public OneSignal() {}
  
  private static void E()
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
  
  private static void F()
  {
    boolean bool = false;
    if (M) {
      return;
    }
    M = true;
    D = false;
    if (N) {
      E = false;
    }
    G();
    I();
    if ((G) || (i.d)) {
      bool = true;
    }
    G = bool;
  }
  
  private static void G()
  {
    Context localContext = b;
    if ((i.d) && (!G)) {}
    for (boolean bool = true;; bool = false)
    {
      n.a(localContext, bool, new n.c()
      {
        public void a(n.e paramAnonymousE)
        {
          OneSignal.a(paramAnonymousE);
          OneSignal.c(true);
          OneSignal.n();
        }
      });
      return;
    }
  }
  
  private static void H()
  {
    if (A == 2) {}
    for (Object localObject = new al();; localObject = new am())
    {
      ((ak)localObject).a(b, m, new ak.a()
      {
        public void a(String paramAnonymousString, int paramAnonymousInt)
        {
          if (paramAnonymousInt < 1) {
            if ((ai.e() == null) && ((OneSignal.o() == 1) || (OneSignal.o() < -6))) {
              OneSignal.a(paramAnonymousInt);
            }
          }
          for (;;)
          {
            OneSignal.c(paramAnonymousString);
            OneSignal.d(true);
            OneSignal.f(OneSignal.b).b(paramAnonymousString);
            OneSignal.n();
            return;
            if (OneSignal.o() < -6) {
              OneSignal.a(paramAnonymousInt);
            }
          }
        }
      });
      return;
    }
  }
  
  private static void I()
  {
    if (F)
    {
      H();
      return;
    }
    ah.a local8 = new ah.a()
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
              j = OneSignal.p() * 10000 + 30000;
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
            OneSignal.a(OneSignal.LOG_LEVEL.e, "Failed to get Android parameters, trying again in " + i / 1000 + " seconds.");
            Thread.sleep(i);
            OneSignal.q();
            OneSignal.r();
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
            OneSignal.e(true);
            OneSignal.d(paramAnonymousString.getString("android_sender_id"));
          }
          OneSignal.j = paramAnonymousString.optBoolean("enterp", false);
          OneSignal.a(paramAnonymousString.getJSONObject("awl_list"));
          boolean bool = paramAnonymousString.optBoolean("fba", false);
          ag.a(ag.a, "GT_FIREBASE_TRACKING_ENABLED", bool);
          p.a(OneSignal.b, paramAnonymousString);
        }
        catch (Throwable paramAnonymousString)
        {
          for (;;)
          {
            paramAnonymousString.printStackTrace();
          }
        }
        OneSignal.f(true);
        OneSignal.s();
      }
    };
    String str2 = "apps/" + a + "/android_params.js";
    String str3 = g();
    String str1 = str2;
    if (str3 != null) {
      str1 = str2 + "?player_id=" + str3;
    }
    a(LOG_LEVEL.f, "Starting request to get Android parameters.");
    ah.a(str1, local8);
  }
  
  private static void J()
  {
    Iterator localIterator = I.iterator();
    while (localIterator.hasNext()) {
      b((JSONArray)localIterator.next(), true, false);
    }
    I.clear();
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
    a(LOG_LEVEL.f, "registerUser: registerForPushFired:" + D + ", locationFired: " + E + ", awlFired: " + F);
    if ((!D) || (!E) || (!F)) {
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        ai.c localC = ai.b();
        Object localObject2 = OneSignal.b.getPackageName();
        Object localObject1 = OneSignal.b.getPackageManager();
        localC.a("app_id", OneSignal.a);
        localC.a("identifier", OneSignal.t());
        Object localObject3 = OneSignal.u().a(OneSignal.b);
        if (localObject3 != null) {
          localC.a("ad_id", localObject3);
        }
        localC.a("device_os", Build.VERSION.RELEASE);
        localC.a("timezone", Integer.valueOf(OneSignal.v()));
        localC.a("language", ac.d());
        localC.a("sdk", "030701");
        localC.a("sdk_type", OneSignal.g);
        localC.a("android_package", localObject2);
        localC.a("device_model", Build.MODEL);
        localC.a("device_type", Integer.valueOf(OneSignal.w()));
        localC.b("subscribableStatus", Integer.valueOf(OneSignal.o()));
        localC.b("androidPermission", Boolean.valueOf(OneSignal.m()));
        try
        {
          localC.a("game_version", Integer.valueOf(((PackageManager)localObject1).getPackageInfo((String)localObject2, 0).versionCode));
          try
          {
            localObject1 = ((PackageManager)localObject1).getInstalledPackages(0);
            localObject2 = new JSONArray();
            localObject3 = MessageDigest.getInstance("SHA-256");
            i = 0;
            if (i < ((List)localObject1).size())
            {
              ((MessageDigest)localObject3).update(((PackageInfo)((List)localObject1).get(i)).packageName.getBytes());
              String str = Base64.encodeToString(((MessageDigest)localObject3).digest(), 2);
              if (!OneSignal.x().has(str)) {
                break label370;
              }
              ((JSONArray)localObject2).put(str);
              break label370;
            }
            localC.a("pkgs", localObject2);
          }
          catch (Throwable localThrowable)
          {
            for (;;) {}
          }
          localC.a("net_type", OneSignal.y().b());
          localC.a("carrier", OneSignal.y().c());
          localC.a("rooted", Boolean.valueOf(ao.a()));
          if ((OneSignal.h) && (OneSignal.z() != null)) {
            localC.a(OneSignal.z());
          }
          ai.a(localC, OneSignal.A());
          OneSignal.g(false);
          ad.a(OneSignal.b, OneSignal.a, OneSignal.B(), c.a());
          return;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            int i;
            continue;
            label370:
            i += 1;
          }
        }
      }
    }, "OS_REG_USER").start();
  }
  
  private static void M()
  {
    if (t != null) {
      ac.a(new Runnable()
      {
        public void run() {}
      });
    }
  }
  
  private static void N()
  {
    String str = null;
    for (;;)
    {
      try
      {
        Object localObject3 = t;
        if (localObject3 == null) {
          return;
        }
        localObject3 = ai.e();
        if (!ai.d())
        {
          localObject3 = g();
          if (localObject3 != null)
          {
            t.a((String)localObject3, str);
            if (str != null) {
              t = null;
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
  
  private static boolean O()
  {
    return (System.currentTimeMillis() - l(b)) / 1000L >= 30L;
  }
  
  private static void P()
  {
    Intent localIntent = new Intent(b, SyncService.class);
    localIntent.putExtra("task", 0);
    b.startService(localIntent);
  }
  
  public static a a(Context paramContext)
  {
    return new a(paramContext, null);
  }
  
  static y<Object, aa> a()
  {
    if (Q == null) {
      Q = new y("onOSPermissionChanged", true);
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
        a(LOG_LEVEL.f, "Not a OneSignal formatted GCM message. No 'i' field in custom.");
        return null;
      }
    }
    catch (Throwable paramBundle)
    {
      a(LOG_LEVEL.f, "Could not parse bundle, probably not a OneSignal notification.", paramBundle);
      return null;
    }
    a(LOG_LEVEL.f, "Not a OneSignal formatted GCM message. No 'custom' field in the bundle.");
    return null;
  }
  
  static void a(long paramLong)
  {
    ag.a(ag.a, "OS_LAST_SESSION_TIME", paramLong);
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
      String str = "players/" + g() + "/on_focus";
      ah.a local10 = new ah.a()
      {
        void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
        {
          OneSignal.a("sending on_focus Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
        }
        
        void a(String paramAnonymousString)
        {
          OneSignal.b(0L);
        }
      };
      if (paramBoolean)
      {
        ah.d(str, localJSONObject, local10);
        return;
      }
      ah.b(str, localJSONObject, local10);
      return;
    }
    catch (Throwable localThrowable)
    {
      a(LOG_LEVEL.c, "Generating on_focus:JSON Failed.", localThrowable);
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, e paramE, f paramF)
  {
    i = c();
    i.h = false;
    i.b = paramE;
    i.c = paramF;
    if (!n) {
      m = paramString1;
    }
    B = new ac();
    A = B.a();
    r = B.a(paramContext, A, paramString2);
    if (r == 64537) {}
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
    s = bool;
    a = paramString2;
    b = paramContext.getApplicationContext();
    b(i.g);
    if (bool)
    {
      a.b = (Activity)paramContext;
      u.a(b);
      P();
    }
    for (;;)
    {
      u = SystemClock.elapsedRealtime();
      ai.a(b);
      ((Application)b).registerActivityLifecycleCallbacks(new b());
      try
      {
        Class.forName("com.amazon.device.iap.PurchasingListener");
        x = new ap(b);
        paramContext = f();
        if (paramContext != null) {
          if (!paramContext.equals(a))
          {
            a(LOG_LEVEL.f, "APP ID changed, clearing user id as it is no longer valid.");
            e(a);
            ai.f();
          }
        }
        for (;;)
        {
          OSPermissionChangedInternalObserver.a(g(b));
          if ((s) || (g() == null))
          {
            N = O();
            a(System.currentTimeMillis());
            F();
          }
          if (i.b != null) {
            J();
          }
          if (ar.a(b)) {
            w = new ar(b);
          }
          if (aq.a()) {
            y = new aq(b);
          }
          c = true;
          E();
          return;
          a.a = true;
          break;
          f.a(0, b);
          e(a);
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
    boolean bool1 = false;
    boolean bool2 = "DISABLE".equals(ac.a(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
    if (!bool2) {
      bool1 = a(paramContext, paramJSONArray);
    }
    b(paramJSONArray, true, paramBoolean);
    if ((!paramBoolean) && (!bool1) && (!bool2)) {
      i(paramContext);
    }
  }
  
  static void a(LOG_LEVEL paramLOG_LEVEL, String paramString)
  {
    a(paramLOG_LEVEL, paramString, null);
  }
  
  static void a(LOG_LEVEL paramLOG_LEVEL, final String paramString, Throwable paramThrowable)
  {
    if (paramLOG_LEVEL.compareTo(p) < 1)
    {
      if (paramLOG_LEVEL != LOG_LEVEL.g) {
        break label148;
      }
      Log.v("OneSignal", paramString, paramThrowable);
    }
    for (;;)
    {
      if ((paramLOG_LEVEL.compareTo(o) < 1) && (a.b != null)) {}
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
        ac.a(new Runnable()
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
      catch (Throwable paramLOG_LEVEL)
      {
        label148:
        Log.e("OneSignal", "Error showing logging message.", paramLOG_LEVEL);
      }
      if (paramLOG_LEVEL == LOG_LEVEL.f) {
        Log.d("OneSignal", paramString, paramThrowable);
      } else if (paramLOG_LEVEL == LOG_LEVEL.e) {
        Log.i("OneSignal", paramString, paramThrowable);
      } else if (paramLOG_LEVEL == LOG_LEVEL.d) {
        Log.w("OneSignal", paramString, paramThrowable);
      } else if ((paramLOG_LEVEL == LOG_LEVEL.c) || (paramLOG_LEVEL == LOG_LEVEL.b)) {
        Log.e("OneSignal", paramString, paramThrowable);
      }
    }
  }
  
  private static void a(b paramB)
  {
    if (paramB == null) {
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        if (!OneSignal.C()) {}
        ai.a localA;
        for (boolean bool = true;; bool = false)
        {
          localA = ai.c(bool);
          if (localA.a) {
            OneSignal.h(true);
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
  
  private static void a(w paramW)
  {
    ac.a(new Runnable()
    {
      public void run()
      {
        OneSignal.i.b.a(this.a);
      }
    });
    if ((y != null) && (c(b))) {
      y.a(paramW);
    }
  }
  
  static void a(String paramString)
  {
    q = paramString;
    if (b == null) {
      return;
    }
    ag.a(ag.a, "GT_PLAYER_ID", q);
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean, ah.a paramA)
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
      ah.b("players/" + g() + "/on_purchase", localJSONObject, paramA);
      return;
    }
    catch (Throwable paramJSONArray)
    {
      a(LOG_LEVEL.c, "Failed to generate JSON for sendPurchases.", paramJSONArray);
    }
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((i == null) || (i.c == null)) {}
    do
    {
      return;
      paramJSONArray = c(paramJSONArray, paramBoolean1, paramBoolean2);
      i.c.a(paramJSONArray.a);
    } while ((y == null) || (!c(b)));
    y.b(paramJSONArray);
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
          a(LOG_LEVEL.c, "Error parsing JSON item " + i1 + "/" + i2 + " for launching a web URL.", localThrowable);
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
  
  private static boolean a(LOG_LEVEL paramLOG_LEVEL)
  {
    return (paramLOG_LEVEL.compareTo(o) < 1) || (paramLOG_LEVEL.compareTo(p) < 1);
  }
  
  /* Error */
  private static boolean a(String paramString, Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: ifnull +13 -> 17
    //   7: ldc_w 827
    //   10: aload_0
    //   11: invokevirtual 602	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   14: ifeq +5 -> 19
    //   17: iconst_0
    //   18: ireturn
    //   19: aload_1
    //   20: invokestatic 832	com/onesignal/ae:a	(Landroid/content/Context;)Lcom/onesignal/ae;
    //   23: astore_1
    //   24: aload_1
    //   25: invokevirtual 835	com/onesignal/ae:b	()Landroid/database/sqlite/SQLiteDatabase;
    //   28: ldc_w 837
    //   31: iconst_1
    //   32: anewarray 598	java/lang/String
    //   35: dup
    //   36: iconst_0
    //   37: ldc_w 839
    //   40: aastore
    //   41: ldc_w 841
    //   44: iconst_1
    //   45: anewarray 598	java/lang/String
    //   48: dup
    //   49: iconst_0
    //   50: aload_0
    //   51: aastore
    //   52: aconst_null
    //   53: aconst_null
    //   54: aconst_null
    //   55: invokevirtual 847	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   58: astore_1
    //   59: aload_1
    //   60: astore 4
    //   62: aload 4
    //   64: astore_1
    //   65: aload 4
    //   67: invokeinterface 852 1 0
    //   72: istore_3
    //   73: iload_3
    //   74: istore_2
    //   75: aload 4
    //   77: ifnull +12 -> 89
    //   80: aload 4
    //   82: invokeinterface 855 1 0
    //   87: iload_3
    //   88: istore_2
    //   89: iload_2
    //   90: ifeq +83 -> 173
    //   93: getstatic 275	com/onesignal/OneSignal$LOG_LEVEL:f	Lcom/onesignal/OneSignal$LOG_LEVEL;
    //   96: new 255	java/lang/StringBuilder
    //   99: dup
    //   100: invokespecial 256	java/lang/StringBuilder:<init>	()V
    //   103: ldc_w 857
    //   106: invokevirtual 262	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: aload_0
    //   110: invokevirtual 262	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: invokevirtual 269	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   116: invokestatic 280	com/onesignal/OneSignal:a	(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;)V
    //   119: iconst_1
    //   120: ireturn
    //   121: astore 5
    //   123: aconst_null
    //   124: astore 4
    //   126: aload 4
    //   128: astore_1
    //   129: getstatic 511	com/onesignal/OneSignal$LOG_LEVEL:c	Lcom/onesignal/OneSignal$LOG_LEVEL;
    //   132: ldc_w 859
    //   135: aload 5
    //   137: invokestatic 460	com/onesignal/OneSignal:a	(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   140: aload 4
    //   142: ifnull +42 -> 184
    //   145: aload 4
    //   147: invokeinterface 855 1 0
    //   152: iconst_0
    //   153: istore_2
    //   154: goto -65 -> 89
    //   157: astore_0
    //   158: aload 4
    //   160: astore_1
    //   161: aload_1
    //   162: ifnull +9 -> 171
    //   165: aload_1
    //   166: invokeinterface 855 1 0
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
  
  static boolean a(boolean paramBoolean)
  {
    boolean bool = true;
    s = false;
    if (!c) {}
    long l1;
    do
    {
      do
      {
        return false;
        if (x != null) {
          x.a();
        }
      } while (u == -1L);
      l1 = ((SystemClock.elapsedRealtime() - u) / 1000.0D + 0.5D);
      u = SystemClock.elapsedRealtime();
    } while ((l1 < 0L) || (l1 > 86400L));
    if (b == null)
    {
      a(LOG_LEVEL.c, "Android Context not found, please call OneSignal.init when your app starts.");
      return false;
    }
    a(System.currentTimeMillis());
    l1 += j();
    if ((paramBoolean) || (l1 < 60L) || (g() == null))
    {
      c(l1);
      if (l1 >= 60L) {}
      for (paramBoolean = bool;; paramBoolean = false) {
        return paramBoolean;
      }
    }
    a(l1, true);
    return false;
  }
  
  static y<Object, ab> b()
  {
    if (S == null) {
      S = new y("onOSSubscriptionChanged", true);
    }
    return S;
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
          if (J.contains(str)) {
            break label153;
          }
          J.add(str);
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("app_id", j(paramContext));
          localJSONObject.put("player_id", k(paramContext));
          localJSONObject.put("opened", true);
          ah.a("notifications/" + str, localJSONObject, new ah.a()
          {
            void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
            {
              OneSignal.a("sending Notification Opened Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
            }
          });
        }
        catch (Throwable localThrowable)
        {
          a(LOG_LEVEL.c, "Failed to generate JSON to send notification opened.", localThrowable);
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
    a(paramString);
    M();
    a(K);
    h(b).a(paramString);
    if (T != null)
    {
      a(T.a, T.b, T.c);
      T = null;
    }
    ad.a(b, a, paramString, c.a());
  }
  
  private static void b(String paramString1, int paramInt, Throwable paramThrowable, String paramString2)
  {
    String str2 = "";
    String str1 = str2;
    if (paramString2 != null)
    {
      str1 = str2;
      if (a(LOG_LEVEL.e)) {
        str1 = "\n" + paramString2 + "\n";
      }
    }
    a(LOG_LEVEL.d, "HTTP code: " + paramInt + " " + paramString1 + str1, paramThrowable);
  }
  
  private static void b(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((i == null) || (i.b == null))
    {
      I.add(paramJSONArray);
      return;
    }
    a(c(paramJSONArray, paramBoolean1, paramBoolean2));
  }
  
  private static void b(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject.put("net_type", B.b());
      return;
    }
    catch (Throwable paramJSONObject) {}
  }
  
  static void b(boolean paramBoolean)
  {
    if (b == null) {
      return;
    }
    ag.a(ag.a, "OS_FILTER_OTHER_GCM_RECEIVERS", paramBoolean);
  }
  
  static boolean b(Context paramContext)
  {
    return ag.b(ag.a, "OS_FILTER_OTHER_GCM_RECEIVERS", false);
  }
  
  public static a c()
  {
    if (i == null) {
      i = new a(null);
    }
    return i;
  }
  
  @NonNull
  private static w c(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject1 = null;
    int i3 = paramJSONArray.length();
    int i1 = 1;
    w localW = new w();
    OSNotification localOSNotification = new OSNotification();
    localOSNotification.a = k();
    localOSNotification.b = paramBoolean1;
    localOSNotification.c = paramJSONArray.optJSONObject(0).optInt("notificationId");
    int i2 = 0;
    if (i2 < i3) {}
    try
    {
      Object localObject2 = paramJSONArray.getJSONObject(i2);
      localOSNotification.d = o.a((JSONObject)localObject2);
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
        OSNotificationAction localOSNotificationAction;
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
        if (localOSNotification.f == null) {
          localOSNotification.f = new ArrayList();
        }
        localOSNotification.f.add(localOSNotification.d);
      }
      catch (Throwable localThrowable1) {}
      a(LOG_LEVEL.c, "Error parsing JSON item " + i2 + "/" + i3 + " for callback.", localThrowable1);
    }
    localW.a = localOSNotification;
    localW.b = new OSNotificationAction();
    localW.b.b = localObject1;
    localOSNotificationAction = localW.b;
    if (localObject1 != null) {}
    for (paramJSONArray = OSNotificationAction.ActionType.b;; paramJSONArray = OSNotificationAction.ActionType.a)
    {
      localOSNotificationAction.a = paramJSONArray;
      if (!paramBoolean2) {
        break;
      }
      localW.a.e = OSNotification.DisplayType.b;
      return localW;
    }
    localW.a.e = OSNotification.DisplayType.a;
    return localW;
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
    v = paramLong;
    if (b == null) {
      return;
    }
    a(LOG_LEVEL.e, "SaveUnsentActiveTime: " + v);
    ag.a(ag.a, "GT_UNSENT_ACTIVE_TIME", paramLong);
  }
  
  static boolean c(Context paramContext)
  {
    return ag.b(ag.a, "GT_FIREBASE_TRACKING_ENABLED", false);
  }
  
  static void d()
  {
    P();
    s = true;
    u = SystemClock.elapsedRealtime();
    N = O();
    a(System.currentTimeMillis());
    F();
    if (w != null) {
      w.a();
    }
    u.a(b);
    g(b).a();
    if ((y != null) && (c(b))) {
      y.b();
    }
  }
  
  static boolean d(Context paramContext)
  {
    return ag.b(ag.a, "GT_VIBRATE_ENABLED", true);
  }
  
  private static void e(String paramString)
  {
    if (b == null) {
      return;
    }
    ag.a(ag.a, "GT_APP_ID", paramString);
  }
  
  static boolean e()
  {
    return s;
  }
  
  static boolean e(Context paramContext)
  {
    return ag.b(ag.a, "GT_SOUND_ENABLED", true);
  }
  
  static String f()
  {
    return j(b);
  }
  
  private static z g(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (P == null)
    {
      P = new z(false);
      P.a.b(new OSPermissionChangedInternalObserver());
    }
    return P;
  }
  
  static String g()
  {
    if ((q == null) && (b != null)) {
      q = ag.b(ag.a, "GT_PLAYER_ID", null);
    }
    return q;
  }
  
  private static OSSubscriptionState h(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (R == null)
    {
      R = new OSSubscriptionState(false, g(paramContext).b());
      g(paramContext).a.a(R);
      R.a.b(new OSSubscriptionChangedInternalObserver());
    }
    return R;
  }
  
  static boolean h()
  {
    if (i == null) {}
    while (i.i == OSInFocusDisplayOption.c) {
      return true;
    }
    return false;
  }
  
  private static void i(Context paramContext)
  {
    Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
    if (localIntent != null)
    {
      localIntent.setFlags(268566528);
      paramContext.startActivity(localIntent);
    }
  }
  
  static boolean i()
  {
    if (i == null) {}
    while (i.i != OSInFocusDisplayOption.b) {
      return false;
    }
    return true;
  }
  
  static long j()
  {
    if ((v == -1L) && (b != null)) {
      v = ag.b(ag.a, "GT_UNSENT_ACTIVE_TIME", 0L);
    }
    a(LOG_LEVEL.e, "GetUnsentActiveTime: " + v);
    return v;
  }
  
  private static String j(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return ag.b(ag.a, "GT_APP_ID", null);
  }
  
  private static String k(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return ag.b(ag.a, "GT_PLAYER_ID", null);
  }
  
  static boolean k()
  {
    return (c) && (e());
  }
  
  private static long l(Context paramContext)
  {
    return ag.b(ag.a, "OS_LAST_SESSION_TIME", -31000L);
  }
  
  static void l()
  {
    N = false;
    a(System.currentTimeMillis());
  }
  
  static boolean m()
  {
    if (i.f) {
      return ac.a(b);
    }
    return true;
  }
  
  public static enum LOG_LEVEL
  {
    private LOG_LEVEL() {}
  }
  
  public static enum OSInFocusDisplayOption
  {
    private OSInFocusDisplayOption() {}
  }
  
  public static class a
  {
    Context a;
    OneSignal.e b;
    OneSignal.f c;
    boolean d;
    boolean e;
    boolean f;
    boolean g;
    boolean h;
    OneSignal.OSInFocusDisplayOption i = OneSignal.OSInFocusDisplayOption.b;
    
    private a() {}
    
    private a(Context paramContext)
    {
      this.a = paramContext;
    }
    
    public a a(OneSignal.OSInFocusDisplayOption paramOSInFocusDisplayOption)
    {
      OneSignal.c().h = false;
      this.i = paramOSInFocusDisplayOption;
      return this;
    }
    
    public a a(boolean paramBoolean)
    {
      this.f = paramBoolean;
      return this;
    }
    
    public void a()
    {
      OneSignal.a(this);
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
    ah.a c;
    
    c(JSONArray paramJSONArray)
    {
      this.a = paramJSONArray;
    }
  }
  
  public static abstract interface d
  {
    public abstract void a(String paramString1, String paramString2);
  }
  
  public static abstract interface e
  {
    public abstract void a(w paramW);
  }
  
  public static abstract interface f
  {
    public abstract void a(OSNotification paramOSNotification);
  }
}
