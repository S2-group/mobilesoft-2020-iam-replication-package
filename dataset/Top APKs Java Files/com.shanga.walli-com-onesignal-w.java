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
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

public class w
{
  private static Double A;
  private static Double B;
  private static Float C;
  private static Integer D;
  private static boolean E;
  private static b F;
  private static boolean G;
  private static boolean H;
  private static boolean I;
  private static JSONObject J;
  private static int K = 0;
  static String a;
  static String b;
  static Context c;
  static boolean d;
  public static String e;
  static a f;
  static Collection<JSONArray> g;
  static boolean h;
  private static d i = d.a;
  private static d j = d.d;
  private static String k = null;
  private static int l;
  private static boolean m;
  private static c n;
  private static long o = 1L;
  private static long p = -1L;
  private static ag q;
  private static af r;
  private static e s = new d();
  private static int t;
  private static v u;
  private static String v;
  private static boolean w;
  private static boolean x;
  private static boolean y;
  private static boolean z;
  
  static
  {
    e = "native";
    E = true;
    g = new ArrayList();
  }
  
  public w() {}
  
  private static void D()
  {
    boolean bool = false;
    if (H) {
      return;
    }
    H = true;
    w = false;
    if (I) {
      x = false;
    }
    E();
    G();
    if ((z) || (f.d)) {
      bool = true;
    }
    z = bool;
  }
  
  private static void E()
  {
    boolean bool = true;
    if (E)
    {
      Context localContext = c;
      if ((f.d) && (!z)) {}
      for (;;)
      {
        j.a(localContext, bool, new j.b()
        {
          public void a(Double paramAnonymousDouble1, Double paramAnonymousDouble2, Float paramAnonymousFloat, Integer paramAnonymousInteger)
          {
            w.a(paramAnonymousDouble1);
            w.b(paramAnonymousDouble2);
            w.a(paramAnonymousFloat);
            w.a(paramAnonymousInteger);
            w.b(true);
            w.k();
          }
        });
        return;
        bool = false;
      }
    }
    x = true;
    J();
  }
  
  private static void F()
  {
    if (t == 2) {}
    for (Object localObject = new ab();; localObject = new ac())
    {
      ((aa)localObject).a(c, b, new aa.a()
      {
        public void a(String paramAnonymousString, int paramAnonymousInt)
        {
          if (paramAnonymousInt < 1) {
            if ((z.d() == null) && ((w.l() == 1) || (w.l() < -6))) {
              w.a(paramAnonymousInt);
            }
          }
          for (;;)
          {
            w.c(paramAnonymousString);
            w.c(true);
            w.k();
            return;
            if (w.l() < -6) {
              w.a(paramAnonymousInt);
            }
          }
        }
      });
      return;
    }
  }
  
  private static void G()
  {
    if (y)
    {
      F();
      return;
    }
    y.a local5 = new y.a()
    {
      void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        new Thread(new Runnable()
        {
          public void run()
          {
            try
            {
              Thread.sleep(w.m() * 10000 + 30000);
              w.n();
              w.o();
              return;
            }
            catch (Throwable localThrowable)
            {
              for (;;) {}
            }
          }
        }, "OS_PARAMS_REQUEST");
      }
      
      void a(String paramAnonymousString)
      {
        try
        {
          paramAnonymousString = new JSONObject(paramAnonymousString);
          if (paramAnonymousString.has("android_sender_id")) {
            w.b = paramAnonymousString.getString("android_sender_id");
          }
          w.b(paramAnonymousString.getJSONObject("awl_list"));
        }
        catch (Throwable paramAnonymousString)
        {
          for (;;)
          {
            paramAnonymousString.printStackTrace();
          }
        }
        w.d(true);
        w.p();
      }
    };
    String str2 = "apps/" + a + "/android_params.js";
    String str3 = e();
    String str1 = str2;
    if (str3 != null) {
      str1 = str2 + "?player_id=" + str3;
    }
    y.a(str1, local5);
  }
  
  private static void H()
  {
    Iterator localIterator = g.iterator();
    while (localIterator.hasNext()) {
      b((JSONArray)localIterator.next(), true, false);
    }
    g.clear();
  }
  
  private static int I()
  {
    TimeZone localTimeZone = Calendar.getInstance().getTimeZone();
    int i2 = localTimeZone.getRawOffset();
    int i1 = i2;
    if (localTimeZone.inDaylightTime(new Date())) {
      i1 = i2 + localTimeZone.getDSTSavings();
    }
    return i1 / 1000;
  }
  
  private static void J()
  {
    a(d.f, "registerUser: registerForPushFired:" + w + ", locationFired: " + x + ", awlFired: " + y);
    if ((!w) || (!x) || (!y)) {
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        z.c localC = z.b();
        Object localObject2 = w.c.getPackageName();
        Object localObject1 = w.c.getPackageManager();
        localC.a("app_id", w.a);
        localC.a("identifier", w.q());
        Object localObject3 = w.r().a(w.c);
        if (localObject3 != null) {
          localC.a("ad_id", localObject3);
        }
        localC.a("device_os", Build.VERSION.RELEASE);
        localC.a("timezone", Integer.valueOf(w.s()));
        localC.a("language", v.d());
        localC.a("sdk", "030402");
        localC.a("sdk_type", w.e);
        localC.a("android_package", localObject2);
        localC.a("device_model", Build.MODEL);
        localC.a("device_type", Integer.valueOf(w.t()));
        localC.b("subscribableStatus", Integer.valueOf(w.l()));
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
              if (!w.u().has(str)) {
                break label360;
              }
              ((JSONArray)localObject2).put(str);
              break label360;
            }
            localC.a("pkgs", localObject2);
          }
          catch (Throwable localThrowable)
          {
            for (;;) {}
          }
          localC.a("net_type", w.v().b());
          localC.a("carrier", w.v().c());
          localC.a("rooted", Boolean.valueOf(ae.a()));
          localC.a("lat", w.w());
          localC.a("long", w.x());
          localC.a("loc_acc", w.y());
          localC.a("loc_type", w.z());
          z.a(localC, w.A());
          w.e(false);
          return;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            int i;
            continue;
            label360:
            i += 1;
          }
        }
      }
    }, "OS_REG_USER").start();
  }
  
  private static void K()
  {
    if (n == null) {}
    String str1;
    do
    {
      String str2;
      do
      {
        return;
        str1 = z.d();
        if (!z.c()) {
          str1 = null;
        }
        str2 = e();
      } while (str2 == null);
      n.a(str2, str1);
    } while (str1 == null);
    n = null;
  }
  
  private static boolean L()
  {
    return (System.currentTimeMillis() - d(c)) / 1000L >= 30L;
  }
  
  private static void M()
  {
    if (h) {
      return;
    }
    h = true;
    c.startService(new Intent(c, SyncService.class));
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
        a(d.f, "Not a OneSignal formatted GCM message. No 'i' field in custom.");
        return null;
      }
    }
    catch (Throwable paramBundle)
    {
      a(d.f, "Could not parse bundle, probably not a OneSignal notification.", paramBundle);
      return null;
    }
    a(d.f, "Not a OneSignal formatted GCM message. No 'custom' field in the bundle.");
    return null;
  }
  
  static String a(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject = new JSONObject(paramJSONObject.optString("custom")).optString("i", null);
      return paramJSONObject;
    }
    catch (Throwable paramJSONObject) {}
    return null;
  }
  
  static void a()
  {
    M();
    m = true;
    o = SystemClock.elapsedRealtime();
    I = L();
    a(System.currentTimeMillis());
    D();
    if (q != null) {
      q.a();
    }
    o.a(c);
  }
  
  static void a(long paramLong)
  {
    SharedPreferences.Editor localEditor = e(c).edit();
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
      c(localJSONObject);
      String str = "players/" + e() + "/on_focus";
      y.a local7 = new y.a()
      {
        void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
        {
          w.a("sending on_focus Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
        }
        
        void a(String paramAnonymousString)
        {
          w.b(0L);
        }
      };
      if (paramBoolean)
      {
        y.d(str, localJSONObject, local7);
        return;
      }
      y.b(str, localJSONObject, local7);
      return;
    }
    catch (Throwable localThrowable)
    {
      a(d.c, "Generating on_focus:JSON Failed.", localThrowable);
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, e paramE, f paramF)
  {
    if (f == null) {
      f = new a(null);
    }
    f.b = paramE;
    f.c = paramF;
    b = paramString1;
    u = new v();
    t = u.a();
    l = u.a(t, paramString2);
    if (l == 64537) {}
    do
    {
      return;
      if (!d) {
        break;
      }
      if (paramContext != null) {
        c = paramContext.getApplicationContext();
      }
    } while (f.b == null);
    H();
    return;
    boolean bool = paramContext instanceof Activity;
    m = bool;
    a = paramString2;
    c = paramContext.getApplicationContext();
    if (bool)
    {
      a.b = (Activity)paramContext;
      o.a(c);
      M();
    }
    for (;;)
    {
      o = SystemClock.elapsedRealtime();
      z.a(c);
      if (Build.VERSION.SDK_INT > 13) {
        ((Application)c).registerActivityLifecycleCallbacks(new b());
      }
      try
      {
        label187:
        Class.forName("com.amazon.device.iap.PurchasingListener");
        r = new af(c);
        paramContext = d();
        if (paramContext != null) {
          if (!paramContext.equals(a))
          {
            a(d.f, "APP ID changed, clearing user id as it is no longer valid.");
            d(a);
            z.e();
          }
        }
        for (;;)
        {
          if ((m) || (e() == null))
          {
            I = L();
            a(System.currentTimeMillis());
            D();
          }
          if (f.b != null) {
            H();
          }
          if (ag.a(c)) {
            q = new ag(c);
          }
          d = true;
          return;
          a.a = true;
          break;
          c.a();
          break label187;
          g.a(0, c);
          d(a);
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
    boolean bool2 = "DISABLE".equals(v.a(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
    if (!bool2) {
      bool1 = a(paramContext, paramJSONArray);
    }
    b(paramJSONArray, true, paramBoolean);
    if ((!paramBoolean) && (!bool1) && (!bool2)) {
      f(paramContext);
    }
  }
  
  private static void a(s paramS)
  {
    if (Looper.getMainLooper().getThread() == Thread.currentThread())
    {
      f.b.a(paramS);
      return;
    }
    a(new Runnable()
    {
      public void run()
      {
        w.f.b.a(this.a);
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
        if (!w.B()) {}
        z.a localA;
        for (boolean bool = true;; bool = false)
        {
          localA = z.b(bool);
          if (localA.a) {
            w.f(true);
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
  
  public static void a(c paramC)
  {
    n = paramC;
    if (e() != null) {
      K();
    }
  }
  
  static void a(d paramD, String paramString)
  {
    a(paramD, paramString, null);
  }
  
  static void a(d paramD, final String paramString, Throwable paramThrowable)
  {
    if (paramD.compareTo(j) < 1)
    {
      if (paramD != d.g) {
        break label148;
      }
      Log.v("OneSignal", paramString, paramThrowable);
    }
    for (;;)
    {
      if ((paramD.compareTo(i) < 1) && (a.b != null)) {}
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
        a(new Runnable()
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
      catch (Throwable paramD)
      {
        label148:
        Log.e("OneSignal", "Error showing logging message.", paramD);
      }
      if (paramD == d.f) {
        Log.d("OneSignal", paramString, paramThrowable);
      } else if (paramD == d.e) {
        Log.i("OneSignal", paramString, paramThrowable);
      } else if (paramD == d.d) {
        Log.w("OneSignal", paramString, paramThrowable);
      } else if ((paramD == d.c) || (paramD == d.b)) {
        Log.e("OneSignal", paramString, paramThrowable);
      }
    }
  }
  
  static void a(Runnable paramRunnable)
  {
    new Handler(Looper.getMainLooper()).post(paramRunnable);
  }
  
  static void a(String paramString)
  {
    k = paramString;
    if (c == null) {
      return;
    }
    paramString = e(c).edit();
    paramString.putString("GT_PLAYER_ID", k);
    paramString.commit();
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean, y.a paramA)
  {
    if (e() == null) {
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
      y.b("players/" + e() + "/on_purchase", localJSONObject, paramA);
      return;
    }
    catch (Throwable paramJSONArray)
    {
      a(d.c, "Failed to generate JSON for sendPurchases.", paramJSONArray);
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
  
  static void a(boolean paramBoolean)
  {
    m = false;
    if (!d) {}
    long l1;
    do
    {
      do
      {
        return;
        if (r != null) {
          r.a();
        }
      } while (o == -1L);
      l1 = ((SystemClock.elapsedRealtime() - o) / 1000.0D + 0.5D);
      o = SystemClock.elapsedRealtime();
    } while ((l1 < 0L) || (l1 > 86400L));
    if (c == null)
    {
      a(d.c, "Android Context not found, please call OneSignal.init when your app starts.");
      return;
    }
    a(System.currentTimeMillis());
    l1 += h();
    if ((paramBoolean) || (l1 < 60L) || (e() == null))
    {
      c(l1);
      return;
    }
    a(l1, true);
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
              localObject = new Intent("android.intent.action.VIEW", Uri.parse((String)localObject));
              ((Intent)localObject).addFlags(1476919296);
              paramContext.startActivity((Intent)localObject);
              bool2 = true;
            }
          }
        }
        catch (Throwable localThrowable)
        {
          a(d.c, "Error parsing JSON item " + i1 + "/" + i2 + " for launching a web URL.", localThrowable);
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
    paramJSONObject = a(paramJSONObject);
    return (paramJSONObject == null) || (a(paramJSONObject, paramContext));
  }
  
  private static boolean a(d paramD)
  {
    return (paramD.compareTo(i) < 1) || (paramD.compareTo(j) < 1);
  }
  
  static boolean a(String paramString, Context paramContext)
  {
    if ((paramString == null) || ("".equals(paramString))) {
      return false;
    }
    paramContext = x.a(paramContext).getReadableDatabase().query("notification", new String[] { "notification_id" }, "notification_id = ?", new String[] { paramString }, null, null, null);
    boolean bool = paramContext.moveToFirst();
    paramContext.close();
    if (bool)
    {
      a(d.f, "Duplicate GCM message received, skip processing of " + paramString);
      return true;
    }
    return false;
  }
  
  private static void b(Context paramContext, JSONArray paramJSONArray)
  {
    int i1 = 0;
    for (;;)
    {
      if (i1 < paramJSONArray.length()) {
        try
        {
          Object localObject = paramJSONArray.getJSONObject(i1);
          if (!((JSONObject)localObject).has("custom")) {
            break label159;
          }
          localObject = new JSONObject(((JSONObject)localObject).optString("custom", null));
          if (!((JSONObject)localObject).has("i")) {
            break label159;
          }
          localObject = ((JSONObject)localObject).optString("i", null);
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("app_id", g(paramContext));
          localJSONObject.put("player_id", h(paramContext));
          localJSONObject.put("opened", true);
          y.a("notifications/" + (String)localObject, localJSONObject, new y.a()
          {
            void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
            {
              w.a("sending Notification Opened Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
            }
          });
        }
        catch (Throwable localThrowable)
        {
          a(d.c, "Failed to generate JSON to send notification opened.", localThrowable);
        }
      }
      return;
      label159:
      i1 += 1;
    }
  }
  
  private static void b(a paramA)
  {
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
    c();
    a(F);
  }
  
  private static void b(String paramString1, int paramInt, Throwable paramThrowable, String paramString2)
  {
    String str2 = "";
    String str1 = str2;
    if (paramString2 != null)
    {
      str1 = str2;
      if (a(d.e)) {
        str1 = "\n" + paramString2 + "\n";
      }
    }
    a(d.d, "HTTP code: " + paramInt + " " + paramString1 + str1, paramThrowable);
  }
  
  private static void b(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((f == null) || (f.b == null))
    {
      g.add(paramJSONArray);
      return;
    }
    a(c(paramJSONArray, paramBoolean1, paramBoolean2));
  }
  
  static boolean b()
  {
    return m;
  }
  
  static boolean b(Context paramContext)
  {
    return e(paramContext).getBoolean("GT_VIBRATE_ENABLED", true);
  }
  
  private static s c(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject1 = null;
    int i3 = paramJSONArray.length();
    int i1 = 1;
    s localS = new s();
    p localP = new p();
    localP.a = i();
    localP.b = paramBoolean1;
    localP.c = paramJSONArray.optJSONObject(0).optInt("notificationId");
    int i2 = 0;
    if (i2 < i3) {}
    try
    {
      Object localObject2 = paramJSONArray.getJSONObject(i2);
      if (!((JSONObject)localObject2).has("custom")) {
        break label329;
      }
      localP.d = k.a((JSONObject)localObject2);
      if ((localObject1 != null) || (!((JSONObject)localObject2).has("actionSelected"))) {
        break label329;
      }
      localObject2 = ((JSONObject)localObject2).optString("actionSelected", null);
      localObject1 = localObject2;
    }
    catch (Throwable localThrowable2)
    {
      for (;;)
      {
        q localQ;
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
        if (localP.f == null) {
          localP.f = new ArrayList();
        }
        localP.f.add(localP.d);
      }
      catch (Throwable localThrowable1) {}
      a(d.c, "Error parsing JSON item " + i2 + "/" + i3 + " for callback.", localThrowable1);
    }
    localS.a = localP;
    localS.b = new q();
    localS.b.b = localObject1;
    localQ = localS.b;
    if (localObject1 != null) {}
    for (paramJSONArray = q.a.b;; paramJSONArray = q.a.a)
    {
      localQ.a = paramJSONArray;
      if (!paramBoolean2) {
        break;
      }
      localS.a.e = p.a.b;
      return localS;
    }
    localS.a.e = p.a.a;
    return localS;
  }
  
  static void c()
  {
    if (n != null) {
      a(new Runnable()
      {
        public void run() {}
      });
    }
  }
  
  private static void c(long paramLong)
  {
    p = paramLong;
    if (c == null) {
      return;
    }
    a(d.e, "SaveUnsentActiveTime: " + p);
    SharedPreferences.Editor localEditor = e(c).edit();
    localEditor.putLong("GT_UNSENT_ACTIVE_TIME", paramLong);
    localEditor.commit();
  }
  
  private static void c(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject.put("net_type", u.b());
      return;
    }
    catch (Throwable paramJSONObject) {}
  }
  
  static boolean c(Context paramContext)
  {
    return e(paramContext).getBoolean("GT_SOUND_ENABLED", true);
  }
  
  static long d(Context paramContext)
  {
    return e(paramContext).getLong("OS_LAST_SESSION_TIME", -31000L);
  }
  
  static String d()
  {
    return g(c);
  }
  
  private static void d(String paramString)
  {
    if (c == null) {
      return;
    }
    SharedPreferences.Editor localEditor = e(c).edit();
    localEditor.putString("GT_APP_ID", paramString);
    localEditor.commit();
  }
  
  static SharedPreferences e(Context paramContext)
  {
    return paramContext.getSharedPreferences(w.class.getSimpleName(), 0);
  }
  
  static String e()
  {
    if ((k == null) && (c != null)) {
      k = e(c).getString("GT_PLAYER_ID", null);
    }
    return k;
  }
  
  private static void f(Context paramContext)
  {
    Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
    if (localIntent != null)
    {
      localIntent.setFlags(268566528);
      paramContext.startActivity(localIntent);
    }
  }
  
  static boolean f()
  {
    if (f == null) {}
    while (f.f == g.c) {
      return true;
    }
    return false;
  }
  
  private static String g(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return e(paramContext).getString("GT_APP_ID", null);
  }
  
  static boolean g()
  {
    if (f == null) {}
    while (f.f != g.b) {
      return false;
    }
    return true;
  }
  
  static long h()
  {
    if ((p == -1L) && (c != null)) {
      p = e(c).getLong("GT_UNSENT_ACTIVE_TIME", 0L);
    }
    a(d.e, "GetUnsentActiveTime: " + p);
    return p;
  }
  
  private static String h(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return e(paramContext).getString("GT_PLAYER_ID", null);
  }
  
  static boolean i()
  {
    return (d) && (b());
  }
  
  static void j()
  {
    I = false;
    a(System.currentTimeMillis());
  }
  
  public static class a
  {
    Context a;
    w.e b;
    w.f c;
    boolean d;
    boolean e;
    w.g f = w.g.b;
    
    private a() {}
    
    private a(Context paramContext)
    {
      this.a = paramContext;
    }
    
    public a a(w.e paramE)
    {
      this.b = paramE;
      return this;
    }
    
    public a a(w.g paramG)
    {
      this.f = paramG;
      return this;
    }
    
    public void a()
    {
      w.a(this);
    }
  }
  
  public static abstract interface b
  {
    public abstract void a(JSONObject paramJSONObject);
  }
  
  public static abstract interface c
  {
    public abstract void a(String paramString1, String paramString2);
  }
  
  public static enum d
  {
    private d() {}
  }
  
  public static abstract interface e
  {
    public abstract void a(s paramS);
  }
  
  public static abstract interface f
  {
    public abstract void a(p paramP);
  }
  
  public static enum g
  {
    private g() {}
  }
}
