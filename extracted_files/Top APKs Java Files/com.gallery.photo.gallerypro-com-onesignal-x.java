package com.onesignal;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
import org.json.JSONArray;
import org.json.JSONObject;

public class x
{
  private static boolean A;
  private static boolean B;
  private static boolean C;
  private static i.e D;
  private static Collection<JSONArray> E = new ArrayList();
  private static HashSet<String> F = new HashSet();
  private static b G;
  private static boolean H;
  private static boolean I;
  private static boolean J;
  private static JSONObject K;
  private static t L;
  private static s<Object, u> M;
  private static OSSubscriptionState N;
  private static s<Object, v> O;
  private static c P;
  private static int Q = 0;
  static String a;
  static Context b;
  static boolean c;
  public static String d;
  static boolean e;
  static a f;
  static boolean g;
  static t h;
  static OSSubscriptionState i;
  private static String j;
  private static boolean k;
  private static e l = e.a;
  private static e m = e.d;
  private static String n = null;
  private static int o;
  private static boolean p;
  private static d q;
  private static long r = 1L;
  private static long s = -1L;
  private static ai t;
  private static ah u;
  private static d v = new c();
  private static int w;
  private static w x;
  private static String y;
  private static boolean z;
  
  static
  {
    d = "native";
    e = true;
  }
  
  public x() {}
  
  private static void E()
  {
    boolean bool = false;
    if (I) {
      return;
    }
    I = true;
    z = false;
    if (J) {
      A = false;
    }
    F();
    H();
    if ((C) || (f.d)) {
      bool = true;
    }
    C = bool;
  }
  
  private static void F()
  {
    Context localContext = b;
    if ((f.d) && (!C)) {}
    for (boolean bool = true;; bool = false)
    {
      i.a(localContext, bool, new i.c()
      {
        public void a(i.e paramAnonymousE)
        {
          x.a(paramAnonymousE);
          x.c(true);
          x.n();
        }
      });
      return;
    }
  }
  
  private static void G()
  {
    if (w == 2) {}
    for (Object localObject = new ad();; localObject = new ae())
    {
      ((ac)localObject).a(b, j, new ac.a()
      {
        public void a(String paramAnonymousString, int paramAnonymousInt)
        {
          if (paramAnonymousInt < 1) {
            if ((ab.e() == null) && ((x.o() == 1) || (x.o() < -6))) {
              x.a(paramAnonymousInt);
            }
          }
          for (;;)
          {
            x.c(paramAnonymousString);
            x.d(true);
            x.f(x.b).b(paramAnonymousString);
            x.n();
            return;
            if (x.o() < -6) {
              x.a(paramAnonymousInt);
            }
          }
        }
      });
      return;
    }
  }
  
  private static void H()
  {
    if (B)
    {
      G();
      return;
    }
    aa.a local5 = new aa.a()
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
              j = x.p() * 10000 + 30000;
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
            x.a(x.e.e, "Failed to get Android parameters, trying again in " + i / 1000 + " seconds.");
            Thread.sleep(i);
            x.q();
            x.r();
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
            x.e(true);
            x.d(paramAnonymousString.getString("android_sender_id"));
          }
          x.g = paramAnonymousString.optBoolean("enterp", false);
          x.a(paramAnonymousString.getJSONObject("awl_list"));
        }
        catch (Throwable paramAnonymousString)
        {
          for (;;)
          {
            paramAnonymousString.printStackTrace();
          }
        }
        x.f(true);
        x.s();
      }
    };
    String str2 = "apps/" + a + "/android_params.js";
    String str3 = g();
    String str1 = str2;
    if (str3 != null) {
      str1 = str2 + "?player_id=" + str3;
    }
    a(e.f, "Starting request to get Android parameters.");
    aa.a(str1, local5);
  }
  
  private static void I()
  {
    Iterator localIterator = E.iterator();
    while (localIterator.hasNext()) {
      b((JSONArray)localIterator.next(), true, false);
    }
    E.clear();
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
    a(e.f, "registerUser: registerForPushFired:" + z + ", locationFired: " + A + ", awlFired: " + B);
    if ((!z) || (!A) || (!B)) {
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        ab.c localC = ab.b();
        Object localObject2 = x.b.getPackageName();
        Object localObject1 = x.b.getPackageManager();
        localC.a("app_id", x.a);
        localC.a("identifier", x.t());
        Object localObject3 = x.u().a(x.b);
        if (localObject3 != null) {
          localC.a("ad_id", localObject3);
        }
        localC.a("device_os", Build.VERSION.RELEASE);
        localC.a("timezone", Integer.valueOf(x.v()));
        localC.a("language", w.d());
        localC.a("sdk", "030508");
        localC.a("sdk_type", x.d);
        localC.a("android_package", localObject2);
        localC.a("device_model", Build.MODEL);
        localC.a("device_type", Integer.valueOf(x.w()));
        localC.b("subscribableStatus", Integer.valueOf(x.o()));
        localC.b("androidPermission", Boolean.valueOf(x.m()));
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
              if (!x.x().has(str)) {
                break label364;
              }
              ((JSONArray)localObject2).put(str);
              break label364;
            }
            localC.a("pkgs", localObject2);
          }
          catch (Throwable localThrowable)
          {
            for (;;) {}
          }
          localC.a("net_type", x.y().b());
          localC.a("carrier", x.y().c());
          localC.a("rooted", Boolean.valueOf(ag.a()));
          if (x.z() != null) {
            localC.a(x.z());
          }
          ab.a(localC, x.A());
          x.g(false);
          y.a(x.b, x.a, x.B(), c.a());
          return;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            int i;
            continue;
            label364:
            i += 1;
          }
        }
      }
    }, "OS_REG_USER").start();
  }
  
  private static void L()
  {
    if (q != null) {
      w.a(new Runnable()
      {
        public void run() {}
      });
    }
  }
  
  private static void M()
  {
    String str = null;
    for (;;)
    {
      try
      {
        Object localObject3 = q;
        if (localObject3 == null) {
          return;
        }
        localObject3 = ab.e();
        if (!ab.d())
        {
          localObject3 = g();
          if (localObject3 != null)
          {
            q.a((String)localObject3, str);
            if (str != null) {
              q = null;
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
  
  private static boolean N()
  {
    return (System.currentTimeMillis() - l(b)) / 1000L >= 30L;
  }
  
  private static void O()
  {
    Intent localIntent = new Intent(b, SyncService.class);
    localIntent.putExtra("task", 0);
    b.startService(localIntent);
  }
  
  static s<Object, u> a()
  {
    if (M == null) {
      M = new s("onOSPermissionChanged", true);
    }
    return M;
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
        a(e.f, "Not a OneSignal formatted GCM message. No 'i' field in custom.");
        return null;
      }
    }
    catch (Throwable paramBundle)
    {
      a(e.f, "Could not parse bundle, probably not a OneSignal notification.", paramBundle);
      return null;
    }
    a(e.f, "Not a OneSignal formatted GCM message. No 'custom' field in the bundle.");
    return null;
  }
  
  static void a(long paramLong)
  {
    SharedPreferences.Editor localEditor = e(b).edit();
    localEditor.putLong("OS_LAST_SESSION_TIME", paramLong);
    localEditor.apply();
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
      aa.a local7 = new aa.a()
      {
        void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
        {
          x.a("sending on_focus Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
        }
        
        void a(String paramAnonymousString)
        {
          x.b(0L);
        }
      };
      if (paramBoolean)
      {
        aa.d(str, localJSONObject, local7);
        return;
      }
      aa.b(str, localJSONObject, local7);
      return;
    }
    catch (Throwable localThrowable)
    {
      a(e.c, "Generating on_focus:JSON Failed.", localThrowable);
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, f paramF, g paramG)
  {
    f = c();
    f.h = false;
    f.b = paramF;
    f.c = paramG;
    if (!k) {
      j = paramString1;
    }
    x = new w();
    w = x.a();
    o = x.a(w, paramString2);
    if (o == 64537) {}
    do
    {
      return;
      if (!c) {
        break;
      }
      if (paramContext != null) {
        b = paramContext.getApplicationContext();
      }
    } while (f.b == null);
    I();
    return;
    boolean bool = paramContext instanceof Activity;
    p = bool;
    a = paramString2;
    b = paramContext.getApplicationContext();
    b(f.g);
    if (bool)
    {
      a.b = (Activity)paramContext;
      m.a(b);
      O();
    }
    for (;;)
    {
      r = SystemClock.elapsedRealtime();
      ab.a(b);
      if (Build.VERSION.SDK_INT > 13) {
        ((Application)b).registerActivityLifecycleCallbacks(new b());
      }
      try
      {
        label198:
        Class.forName("com.amazon.device.iap.PurchasingListener");
        u = new ah(b);
        paramContext = f();
        if (paramContext != null) {
          if (!paramContext.equals(a))
          {
            a(e.f, "APP ID changed, clearing user id as it is no longer valid.");
            e(a);
            ab.f();
          }
        }
        for (;;)
        {
          OSPermissionChangedInternalObserver.a(g(b));
          if ((p) || (g() == null))
          {
            J = N();
            a(System.currentTimeMillis());
            E();
          }
          if (f.b != null) {
            I();
          }
          if (ai.a(b)) {
            t = new ai(b);
          }
          c = true;
          return;
          a.a = true;
          break;
          ActivityLifecycleListenerCompat.startListener();
          break label198;
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
    boolean bool2 = "DISABLE".equals(w.a(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
    if (!bool2) {
      bool1 = a(paramContext, paramJSONArray);
    }
    b(paramJSONArray, true, paramBoolean);
    if ((!paramBoolean) && (!bool1) && (!bool2)) {
      i(paramContext);
    }
  }
  
  private static void a(q paramQ)
  {
    w.a(new Runnable()
    {
      public void run()
      {
        x.f.b.a(this.a);
      }
    });
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
        if (!x.C()) {}
        ab.a localA;
        for (boolean bool = true;; bool = false)
        {
          localA = ab.c(bool);
          if (localA.a) {
            x.h(true);
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
  
  static void a(e paramE, String paramString)
  {
    a(paramE, paramString, null);
  }
  
  static void a(e paramE, final String paramString, Throwable paramThrowable)
  {
    if (paramE.compareTo(m) < 1)
    {
      if (paramE != e.g) {
        break label148;
      }
      Log.v("OneSignal", paramString, paramThrowable);
    }
    for (;;)
    {
      if ((paramE.compareTo(l) < 1) && (a.b != null)) {}
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
        w.a(new Runnable()
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
        label148:
        Log.e("OneSignal", "Error showing logging message.", paramE);
      }
      if (paramE == e.f) {
        Log.d("OneSignal", paramString, paramThrowable);
      } else if (paramE == e.e) {
        Log.i("OneSignal", paramString, paramThrowable);
      } else if (paramE == e.d) {
        Log.w("OneSignal", paramString, paramThrowable);
      } else if ((paramE == e.c) || (paramE == e.b)) {
        Log.e("OneSignal", paramString, paramThrowable);
      }
    }
  }
  
  static void a(String paramString)
  {
    n = paramString;
    if (b == null) {
      return;
    }
    paramString = e(b).edit();
    paramString.putString("GT_PLAYER_ID", n);
    paramString.commit();
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean, aa.a paramA)
  {
    if (g() == null)
    {
      P = new c(paramJSONArray);
      P.b = paramBoolean;
      P.c = paramA;
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
      aa.b("players/" + g() + "/on_purchase", localJSONObject, paramA);
      return;
    }
    catch (Throwable paramJSONArray)
    {
      a(e.c, "Failed to generate JSON for sendPurchases.", paramJSONArray);
    }
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((f == null) || (f.c == null)) {
      return;
    }
    paramJSONArray = c(paramJSONArray, paramBoolean1, paramBoolean2);
    f.c.a(paramJSONArray.a);
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
          a(e.c, "Error parsing JSON item " + i1 + "/" + i2 + " for launching a web URL.", localThrowable);
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
  
  private static boolean a(e paramE)
  {
    return (paramE.compareTo(l) < 1) || (paramE.compareTo(m) < 1);
  }
  
  /* Error */
  private static boolean a(String paramString, Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: ifnull +13 -> 17
    //   7: ldc_w 795
    //   10: aload_0
    //   11: invokevirtual 576	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   14: ifeq +5 -> 19
    //   17: iconst_0
    //   18: ireturn
    //   19: aload_1
    //   20: invokestatic 800	com/onesignal/z:a	(Landroid/content/Context;)Lcom/onesignal/z;
    //   23: astore_1
    //   24: aload_1
    //   25: invokevirtual 803	com/onesignal/z:b	()Landroid/database/sqlite/SQLiteDatabase;
    //   28: ldc_w 805
    //   31: iconst_1
    //   32: anewarray 572	java/lang/String
    //   35: dup
    //   36: iconst_0
    //   37: ldc_w 807
    //   40: aastore
    //   41: ldc_w 809
    //   44: iconst_1
    //   45: anewarray 572	java/lang/String
    //   48: dup
    //   49: iconst_0
    //   50: aload_0
    //   51: aastore
    //   52: aconst_null
    //   53: aconst_null
    //   54: aconst_null
    //   55: invokevirtual 815	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   58: astore_1
    //   59: aload_1
    //   60: astore 4
    //   62: aload 4
    //   64: astore_1
    //   65: aload 4
    //   67: invokeinterface 820 1 0
    //   72: istore_3
    //   73: iload_3
    //   74: istore_2
    //   75: aload 4
    //   77: ifnull +12 -> 89
    //   80: aload 4
    //   82: invokeinterface 823 1 0
    //   87: iload_3
    //   88: istore_2
    //   89: iload_2
    //   90: ifeq +83 -> 173
    //   93: getstatic 228	com/onesignal/x$e:f	Lcom/onesignal/x$e;
    //   96: new 208	java/lang/StringBuilder
    //   99: dup
    //   100: invokespecial 209	java/lang/StringBuilder:<init>	()V
    //   103: ldc_w 825
    //   106: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: aload_0
    //   110: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: invokevirtual 222	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   116: invokestatic 233	com/onesignal/x:a	(Lcom/onesignal/x$e;Ljava/lang/String;)V
    //   119: iconst_1
    //   120: ireturn
    //   121: astore 5
    //   123: aconst_null
    //   124: astore 4
    //   126: aload 4
    //   128: astore_1
    //   129: getstatic 479	com/onesignal/x$e:c	Lcom/onesignal/x$e;
    //   132: ldc_w 827
    //   135: aload 5
    //   137: invokestatic 416	com/onesignal/x:a	(Lcom/onesignal/x$e;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   140: aload 4
    //   142: ifnull +42 -> 184
    //   145: aload 4
    //   147: invokeinterface 823 1 0
    //   152: iconst_0
    //   153: istore_2
    //   154: goto -65 -> 89
    //   157: astore_0
    //   158: aload 4
    //   160: astore_1
    //   161: aload_1
    //   162: ifnull +9 -> 171
    //   165: aload_1
    //   166: invokeinterface 823 1 0
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
    p = false;
    if (!c) {}
    long l1;
    do
    {
      do
      {
        return false;
        if (u != null) {
          u.a();
        }
      } while (r == -1L);
      l1 = ((SystemClock.elapsedRealtime() - r) / 1000.0D + 0.5D);
      r = SystemClock.elapsedRealtime();
    } while ((l1 < 0L) || (l1 > 86400L));
    if (b == null)
    {
      a(e.c, "Android Context not found, please call OneSignal.init when your app starts.");
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
  
  static s<Object, v> b()
  {
    if (O == null) {
      O = new s("onOSSubscriptionChanged", true);
    }
    return O;
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
          if (F.contains(str)) {
            break label153;
          }
          F.add(str);
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("app_id", j(paramContext));
          localJSONObject.put("player_id", k(paramContext));
          localJSONObject.put("opened", true);
          aa.a("notifications/" + str, localJSONObject, new aa.a()
          {
            void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
            {
              x.a("sending Notification Opened Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
            }
          });
        }
        catch (Throwable localThrowable)
        {
          a(e.c, "Failed to generate JSON to send notification opened.", localThrowable);
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
    f = paramA;
    Context localContext = f.a;
    f.a = null;
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
      a(localContext, paramA, localBundle.getString("onesignal_app_id"), f.b, f.c);
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
    L();
    a(G);
    h(b).a(paramString);
    if (P != null)
    {
      a(P.a, P.b, P.c);
      P = null;
    }
    y.a(b, a, paramString, c.a());
  }
  
  private static void b(String paramString1, int paramInt, Throwable paramThrowable, String paramString2)
  {
    String str2 = "";
    String str1 = str2;
    if (paramString2 != null)
    {
      str1 = str2;
      if (a(e.e)) {
        str1 = "\n" + paramString2 + "\n";
      }
    }
    a(e.d, "HTTP code: " + paramInt + " " + paramString1 + str1, paramThrowable);
  }
  
  private static void b(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((f == null) || (f.b == null))
    {
      E.add(paramJSONArray);
      return;
    }
    a(c(paramJSONArray, paramBoolean1, paramBoolean2));
  }
  
  private static void b(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject.put("net_type", x.b());
      return;
    }
    catch (Throwable paramJSONObject) {}
  }
  
  static void b(boolean paramBoolean)
  {
    if (b == null) {
      return;
    }
    SharedPreferences.Editor localEditor = e(b).edit();
    localEditor.putBoolean("OS_FILTER_OTHER_GCM_RECEIVERS", paramBoolean);
    localEditor.commit();
  }
  
  static boolean b(Context paramContext)
  {
    return e(paramContext).getBoolean("OS_FILTER_OTHER_GCM_RECEIVERS", false);
  }
  
  private static q c(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject1 = null;
    int i3 = paramJSONArray.length();
    int i1 = 1;
    q localQ = new q();
    o localO = new o();
    localO.a = k();
    localO.b = paramBoolean1;
    localO.c = paramJSONArray.optJSONObject(0).optInt("notificationId");
    int i2 = 0;
    if (i2 < i3) {}
    try
    {
      Object localObject2 = paramJSONArray.getJSONObject(i2);
      localO.d = j.a((JSONObject)localObject2);
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
        p localP;
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
        if (localO.f == null) {
          localO.f = new ArrayList();
        }
        localO.f.add(localO.d);
      }
      catch (Throwable localThrowable1) {}
      a(e.c, "Error parsing JSON item " + i2 + "/" + i3 + " for callback.", localThrowable1);
    }
    localQ.a = localO;
    localQ.b = new p();
    localQ.b.b = localObject1;
    localP = localQ.b;
    if (localObject1 != null) {}
    for (paramJSONArray = p.a.b;; paramJSONArray = p.a.a)
    {
      localP.a = paramJSONArray;
      if (!paramBoolean2) {
        break;
      }
      localQ.a.e = o.a.b;
      return localQ;
    }
    localQ.a.e = o.a.a;
    return localQ;
  }
  
  public static a c()
  {
    if (f == null) {
      f = new a(null);
    }
    return f;
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
    s = paramLong;
    if (b == null) {
      return;
    }
    a(e.e, "SaveUnsentActiveTime: " + s);
    SharedPreferences.Editor localEditor = e(b).edit();
    localEditor.putLong("GT_UNSENT_ACTIVE_TIME", paramLong);
    localEditor.commit();
  }
  
  static boolean c(Context paramContext)
  {
    return e(paramContext).getBoolean("GT_VIBRATE_ENABLED", true);
  }
  
  static void d()
  {
    O();
    p = true;
    r = SystemClock.elapsedRealtime();
    J = N();
    a(System.currentTimeMillis());
    E();
    if (t != null) {
      t.a();
    }
    m.a(b);
    g(b).a();
  }
  
  static boolean d(Context paramContext)
  {
    return e(paramContext).getBoolean("GT_SOUND_ENABLED", true);
  }
  
  static SharedPreferences e(Context paramContext)
  {
    return paramContext.getSharedPreferences(x.class.getSimpleName(), 0);
  }
  
  private static void e(String paramString)
  {
    if (b == null) {
      return;
    }
    SharedPreferences.Editor localEditor = e(b).edit();
    localEditor.putString("GT_APP_ID", paramString);
    localEditor.commit();
  }
  
  static boolean e()
  {
    return p;
  }
  
  static String f()
  {
    return j(b);
  }
  
  private static t g(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (L == null)
    {
      L = new t(false);
      L.a.b(new OSPermissionChangedInternalObserver());
    }
    return L;
  }
  
  static String g()
  {
    if ((n == null) && (b != null)) {
      n = e(b).getString("GT_PLAYER_ID", null);
    }
    return n;
  }
  
  private static OSSubscriptionState h(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (N == null)
    {
      N = new OSSubscriptionState(false, g(paramContext).b());
      g(paramContext).a.a(N);
      N.a.b(new OSSubscriptionChangedInternalObserver());
    }
    return N;
  }
  
  static boolean h()
  {
    if (f == null) {}
    while (f.i == h.c) {
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
    if (f == null) {}
    while (f.i != h.b) {
      return false;
    }
    return true;
  }
  
  static long j()
  {
    if ((s == -1L) && (b != null)) {
      s = e(b).getLong("GT_UNSENT_ACTIVE_TIME", 0L);
    }
    a(e.e, "GetUnsentActiveTime: " + s);
    return s;
  }
  
  private static String j(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return e(paramContext).getString("GT_APP_ID", null);
  }
  
  private static String k(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return e(paramContext).getString("GT_PLAYER_ID", null);
  }
  
  static boolean k()
  {
    return (c) && (e());
  }
  
  private static long l(Context paramContext)
  {
    return e(paramContext).getLong("OS_LAST_SESSION_TIME", -31000L);
  }
  
  static void l()
  {
    J = false;
    a(System.currentTimeMillis());
  }
  
  static boolean m()
  {
    if (f.f) {
      return w.a(b);
    }
    return true;
  }
  
  public static class a
  {
    Context a;
    x.f b;
    x.g c;
    boolean d;
    boolean e;
    boolean f;
    boolean g;
    boolean h;
    x.h i = x.h.b;
    
    private a() {}
    
    private a(Context paramContext)
    {
      this.a = paramContext;
    }
    
    public a a(x.g paramG)
    {
      this.c = paramG;
      return this;
    }
    
    public a a(x.h paramH)
    {
      x.c().h = false;
      this.i = paramH;
      return this;
    }
    
    public void a()
    {
      x.a(this);
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
    aa.a c;
    
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
    public abstract void a(q paramQ);
  }
  
  public static abstract interface g
  {
    public abstract void a(o paramO);
  }
  
  public static enum h
  {
    private h() {}
  }
}
