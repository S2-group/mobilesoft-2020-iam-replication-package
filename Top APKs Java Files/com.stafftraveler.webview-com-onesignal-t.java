package com.onesignal;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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

public class t
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
  private static ad q;
  private static ac r;
  private static d s = new c();
  private static int t;
  private static s u;
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
  
  public t() {}
  
  private static void F()
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
    G();
    I();
    if ((z) || (f.c)) {
      bool = true;
    }
    z = bool;
  }
  
  private static void G()
  {
    boolean bool = true;
    if (E)
    {
      Context localContext = c;
      if ((f.c) && (!z)) {}
      for (;;)
      {
        i.a(localContext, bool, new i.b()
        {
          public void a(Double paramAnonymousDouble1, Double paramAnonymousDouble2, Float paramAnonymousFloat, Integer paramAnonymousInteger)
          {
            t.a(paramAnonymousDouble1);
            t.b(paramAnonymousDouble2);
            t.a(paramAnonymousFloat);
            t.a(paramAnonymousInteger);
            t.b(true);
            t.m();
          }
        });
        return;
        bool = false;
      }
    }
    x = true;
    L();
  }
  
  private static void H()
  {
    if (t == 2) {}
    for (Object localObject = new y();; localObject = new z())
    {
      ((x)localObject).a(c, b, new x.a()
      {
        public void a(String paramAnonymousString, int paramAnonymousInt)
        {
          if (paramAnonymousInt < 1) {
            if ((w.d() == null) && ((t.n() == 1) || (t.n() < -6))) {
              t.a(paramAnonymousInt);
            }
          }
          for (;;)
          {
            t.c(paramAnonymousString);
            t.c(true);
            t.m();
            return;
            if (t.n() < -6) {
              t.a(paramAnonymousInt);
            }
          }
        }
      });
      return;
    }
  }
  
  private static void I()
  {
    if (y)
    {
      H();
      return;
    }
    v.a local6 = new v.a()
    {
      void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        new Thread(new Runnable()
        {
          public void run()
          {
            try
            {
              Thread.sleep(t.o() * 10000 + 30000);
              t.p();
              t.q();
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
            t.b = paramAnonymousString.getString("android_sender_id");
          }
          t.c(paramAnonymousString.getJSONObject("awl_list"));
        }
        catch (Throwable paramAnonymousString)
        {
          for (;;)
          {
            paramAnonymousString.printStackTrace();
          }
        }
        t.d(true);
        t.r();
      }
    };
    String str2 = "apps/" + a + "/android_params.js";
    String str3 = e();
    String str1 = str2;
    if (str3 != null) {
      str1 = str2 + "?player_id=" + str3;
    }
    v.a(str1, local6);
  }
  
  private static void J()
  {
    Iterator localIterator = g.iterator();
    while (localIterator.hasNext()) {
      b((JSONArray)localIterator.next(), true, false);
    }
    g.clear();
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
    a(d.f, "registerUser: registerForPushFired:" + w + ", locationFired: " + x + ", awlFired: " + y);
    if ((!w) || (!x) || (!y)) {
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        w.c localC = w.b();
        Object localObject2 = t.c.getPackageName();
        Object localObject1 = t.c.getPackageManager();
        localC.a("app_id", t.a);
        localC.a("identifier", t.s());
        Object localObject3 = t.t().a(t.c);
        if (localObject3 != null) {
          localC.a("ad_id", localObject3);
        }
        localC.a("device_os", Build.VERSION.RELEASE);
        localC.a("timezone", Integer.valueOf(t.u()));
        localC.a("language", s.d());
        localC.a("sdk", "030403");
        localC.a("sdk_type", t.e);
        localC.a("android_package", localObject2);
        localC.a("device_model", Build.MODEL);
        localC.a("device_type", Integer.valueOf(t.v()));
        localC.b("subscribableStatus", Integer.valueOf(t.n()));
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
              if (!t.w().has(str)) {
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
          localC.a("net_type", t.x().b());
          localC.a("carrier", t.x().c());
          localC.a("rooted", Boolean.valueOf(ab.a()));
          localC.a("lat", t.y());
          localC.a("long", t.z());
          localC.a("loc_acc", t.A());
          localC.a("loc_type", t.B());
          w.a(localC, t.C());
          t.e(false);
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
  
  private static void M()
  {
    if (n == null) {}
    String str1;
    do
    {
      String str2;
      do
      {
        return;
        str1 = w.d();
        if (!w.c()) {
          str1 = null;
        }
        str2 = e();
      } while (str2 == null);
      n.a(str2, str1);
    } while (str1 == null);
    n = null;
  }
  
  private static boolean N()
  {
    return (System.currentTimeMillis() - c(c)) / 1000L >= 30L;
  }
  
  private static void O()
  {
    if (h) {
      return;
    }
    h = true;
    c.startService(new Intent(c, SyncService.class));
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
  
  static void a()
  {
    O();
    m = true;
    o = SystemClock.elapsedRealtime();
    I = N();
    a(System.currentTimeMillis());
    F();
    if (q != null) {
      q.a();
    }
    n.a(c);
  }
  
  static void a(long paramLong)
  {
    SharedPreferences.Editor localEditor = d(c).edit();
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
      d(localJSONObject);
      String str = "players/" + e() + "/on_focus";
      v.a local8 = new v.a()
      {
        void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
        {
          t.a("sending on_focus Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
        }
        
        void a(String paramAnonymousString)
        {
          t.b(0L);
        }
      };
      if (paramBoolean)
      {
        v.d(str, localJSONObject, local8);
        return;
      }
      v.b(str, localJSONObject, local8);
      return;
    }
    catch (Throwable localThrowable)
    {
      a(d.c, "Generating on_focus:JSON Failed.", localThrowable);
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, e paramE)
  {
    a(paramContext, paramString1, paramString2, paramE, null);
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, e paramE, f paramF)
  {
    if (f == null) {
      f = new a(null);
    }
    f.a = paramE;
    f.b = paramF;
    b = paramString1;
    u = new s();
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
    } while (f.a == null);
    J();
    return;
    boolean bool = paramContext instanceof Activity;
    m = bool;
    a = paramString2;
    c = paramContext.getApplicationContext();
    if (bool)
    {
      a.b = (Activity)paramContext;
      n.a(c);
      O();
    }
    for (;;)
    {
      o = SystemClock.elapsedRealtime();
      w.a(c);
      if (Build.VERSION.SDK_INT > 13) {
        ((Application)c).registerActivityLifecycleCallbacks(new b());
      }
      try
      {
        label187:
        Class.forName("com.amazon.device.iap.PurchasingListener");
        r = new ac(c);
        paramContext = d();
        if (paramContext != null) {
          if (!paramContext.equals(a))
          {
            a(d.f, "APP ID changed, clearing user id as it is no longer valid.");
            d(a);
            w.e();
          }
        }
        for (;;)
        {
          if ((m) || (e() == null))
          {
            I = N();
            a(System.currentTimeMillis());
            F();
          }
          if (f.a != null) {
            J();
          }
          if (ad.a(c)) {
            q = new ad(c);
          }
          d = true;
          return;
          a.a = true;
          break;
          ActivityLifecycleListenerCompat.startListener();
          break label187;
          f.a(0, c);
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
    boolean bool2 = "DISABLE".equals(s.a(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
    if (!bool2) {
      bool1 = a(paramContext, paramJSONArray);
    }
    b(paramJSONArray, true, paramBoolean);
    if ((!paramBoolean) && (!bool1) && (!bool2)) {
      e(paramContext);
    }
  }
  
  private static void a(q paramQ)
  {
    if (Looper.getMainLooper().getThread() == Thread.currentThread())
    {
      f.a.a(paramQ);
      return;
    }
    a(new Runnable()
    {
      public void run()
      {
        t.f.a.a(this.a);
      }
    });
  }
  
  public static void a(b paramB)
  {
    if (c == null)
    {
      a(d.c, "You must initialize OneSignal before getting tags! Omitting this tag operation.");
      return;
    }
    if (paramB == null)
    {
      a(d.c, "getTagsHandler is null!");
      return;
    }
    if (e() == null)
    {
      F = paramB;
      return;
    }
    b(paramB);
  }
  
  public static void a(c paramC)
  {
    n = paramC;
    if (e() != null) {
      M();
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
  
  public static void a(g paramG)
  {
    f.e = paramG;
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
    paramString = d(c).edit();
    paramString.putString("GT_PLAYER_ID", k);
    paramString.commit();
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean, v.a paramA)
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
      v.b("players/" + e() + "/on_purchase", localJSONObject, paramA);
      return;
    }
    catch (Throwable paramJSONArray)
    {
      a(d.c, "Failed to generate JSON for sendPurchases.", paramJSONArray);
    }
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((f == null) || (f.b == null)) {
      return;
    }
    paramJSONArray = c(paramJSONArray, paramBoolean1, paramBoolean2);
    f.b.a(paramJSONArray.a);
  }
  
  public static void a(JSONObject paramJSONObject)
  {
    if (c == null) {
      a(d.c, "You must initialize OneSignal before modifying tags! Omitting this tag operation.");
    }
    while (paramJSONObject == null) {
      return;
    }
    JSONObject localJSONObject1 = w.b(false).b;
    JSONObject localJSONObject2 = new JSONObject();
    Iterator localIterator = paramJSONObject.keys();
    for (;;)
    {
      String str;
      if (localIterator.hasNext()) {
        str = (String)localIterator.next();
      }
      try
      {
        Object localObject = paramJSONObject.get(str);
        if (((localObject instanceof JSONArray)) || ((localObject instanceof JSONObject)))
        {
          a(d.c, "Omitting key '" + str + "'! sendTags DO NOT supported nested values!");
          continue;
        }
        if ((paramJSONObject.isNull(str)) || ("".equals(localObject)))
        {
          if (!localJSONObject1.has(str)) {
            continue;
          }
          localJSONObject2.put(str, "");
          continue;
        }
        localJSONObject2.put(str, localObject.toString());
      }
      catch (Throwable localThrowable) {}
      if (localJSONObject2.toString().equals("{}")) {
        break;
      }
      w.a(localJSONObject2);
      return;
    }
  }
  
  static void a(boolean paramBoolean)
  {
    m = false;
    if (!d) {}
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
    long l1 = j() + l1;
    if ((paramBoolean) || (l1 < 60L) || (e() == null))
    {
      c(l1);
      return;
    }
    a(l1, true);
  }
  
  static boolean a(Context paramContext)
  {
    return d(paramContext).getBoolean("GT_VIBRATE_ENABLED", true);
  }
  
  private static boolean a(Context paramContext, JSONArray paramJSONArray)
  {
    int i2 = paramJSONArray.length();
    boolean bool1 = false;
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
    paramJSONObject = b(paramJSONObject);
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
    paramContext = u.a(paramContext).getReadableDatabase().query("notification", new String[] { "notification_id" }, "notification_id = ?", new String[] { paramString }, null, null, null);
    boolean bool = paramContext.moveToFirst();
    paramContext.close();
    if (bool)
    {
      a(d.f, "Duplicate GCM message received, skip processing of " + paramString);
      return true;
    }
    return false;
  }
  
  static String b(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject = new JSONObject(paramJSONObject.optString("custom")).optString("i", null);
      return paramJSONObject;
    }
    catch (Throwable paramJSONObject) {}
    return null;
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
          localJSONObject.put("app_id", f(paramContext));
          localJSONObject.put("player_id", g(paramContext));
          localJSONObject.put("opened", true);
          v.a("notifications/" + (String)localObject, localJSONObject, new v.a()
          {
            void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
            {
              t.a("sending Notification Opened Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
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
  
  private static void b(b paramB)
  {
    if (paramB == null) {
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        if (!t.D()) {}
        w.a localA;
        for (boolean bool = true;; bool = false)
        {
          localA = w.b(bool);
          if (localA.a) {
            t.f(true);
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
  
  static void b(String paramString)
  {
    a(paramString);
    c();
    b(F);
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
    if ((f == null) || (f.a == null))
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
    return d(paramContext).getBoolean("GT_SOUND_ENABLED", true);
  }
  
  static long c(Context paramContext)
  {
    return d(paramContext).getLong("OS_LAST_SESSION_TIME", -31000L);
  }
  
  private static q c(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i3 = paramJSONArray.length();
    int i2 = 1;
    q localQ = new q();
    o localO = new o();
    localO.a = k();
    localO.b = paramBoolean1;
    localO.c = paramJSONArray.optJSONObject(0).optInt("notificationId");
    Object localObject1 = null;
    int i1 = 0;
    for (;;)
    {
      Object localObject4;
      if (i1 < i3) {
        localObject4 = localObject1;
      }
      Object localObject3;
      do
      {
        try
        {
          JSONObject localJSONObject = paramJSONArray.getJSONObject(i1);
          localObject4 = localObject1;
          localObject3 = localObject1;
          if (!localJSONObject.has("custom")) {
            continue;
          }
          localObject4 = localObject1;
          localO.d = j.a(localJSONObject);
          localObject3 = localObject1;
          if (localObject1 != null) {
            continue;
          }
          localObject4 = localObject1;
          localObject3 = localObject1;
          if (!localJSONObject.has("actionSelected")) {
            continue;
          }
          localObject4 = localObject1;
          localObject3 = localJSONObject.optString("actionSelected", null);
        }
        catch (Throwable localThrowable)
        {
          a(d.c, "Error parsing JSON item " + i1 + "/" + i3 + " for callback.", localThrowable);
          localObject2 = localObject4;
          break;
        }
        localObject4 = localObject3;
        if (localO.f == null)
        {
          localObject4 = localObject3;
          localO.f = new ArrayList();
        }
        localObject4 = localObject3;
        localO.f.add(localO.d);
        localObject1 = localObject3;
        break;
        localQ.a = localO;
        localQ.b = new p();
        localQ.b.b = localObject2;
        localObject3 = localQ.b;
        if (localObject2 != null) {}
        for (paramJSONArray = p.a.b;; paramJSONArray = p.a.a)
        {
          ((p)localObject3).a = paramJSONArray;
          if (!paramBoolean2) {
            break;
          }
          localQ.a.e = o.a.b;
          return localQ;
        }
        localQ.a.e = o.a.a;
        return localQ;
      } while (i2 == 0);
      i2 = 0;
      Object localObject2 = localObject3;
      i1 += 1;
    }
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
    SharedPreferences.Editor localEditor = d(c).edit();
    localEditor.putLong("GT_UNSENT_ACTIVE_TIME", paramLong);
    localEditor.commit();
  }
  
  static SharedPreferences d(Context paramContext)
  {
    return paramContext.getSharedPreferences(t.class.getSimpleName(), 0);
  }
  
  static String d()
  {
    return f(c);
  }
  
  private static void d(String paramString)
  {
    if (c == null) {
      return;
    }
    SharedPreferences.Editor localEditor = d(c).edit();
    localEditor.putString("GT_APP_ID", paramString);
    localEditor.commit();
  }
  
  private static void d(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject.put("net_type", u.b());
      return;
    }
    catch (Throwable paramJSONObject) {}
  }
  
  static String e()
  {
    if ((k == null) && (c != null)) {
      k = d(c).getString("GT_PLAYER_ID", null);
    }
    return k;
  }
  
  private static void e(Context paramContext)
  {
    Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
    if (localIntent != null)
    {
      localIntent.setFlags(268566528);
      paramContext.startActivity(localIntent);
    }
  }
  
  private static String f(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return d(paramContext).getString("GT_APP_ID", null);
  }
  
  static boolean f()
  {
    if (f == null) {}
    while (f.e == g.c) {
      return true;
    }
    return false;
  }
  
  private static String g(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return d(paramContext).getString("GT_PLAYER_ID", null);
  }
  
  static boolean g()
  {
    if (f == null) {}
    while (f.e != g.b) {
      return false;
    }
    return true;
  }
  
  public static void h()
  {
    if (!E) {
      return;
    }
    if (c == null)
    {
      a(d.c, "OneSignal.init has not been called. Could not prompt for location.");
      return;
    }
    i.a(c, true, new i.b()
    {
      public void a(Double paramAnonymousDouble1, Double paramAnonymousDouble2, Float paramAnonymousFloat, Integer paramAnonymousInteger)
      {
        if ((paramAnonymousDouble1 != null) && (paramAnonymousDouble2 != null)) {
          w.a(paramAnonymousDouble1, paramAnonymousDouble2, paramAnonymousFloat, paramAnonymousInteger);
        }
      }
    });
    z = true;
  }
  
  public static void i()
  {
    if (c == null)
    {
      a(d.c, "OneSignal.init has not been called. Could not clear notifications.");
      return;
    }
    localObject1 = (NotificationManager)c.getSystemService("notification");
    Object localObject2 = u.a(c);
    Cursor localCursor = ((u)localObject2).getReadableDatabase().query("notification", new String[] { "android_notification_id" }, "dismissed = 0 AND opened = 0", null, null, null, null);
    if (localCursor.moveToFirst()) {
      do
      {
        ((NotificationManager)localObject1).cancel(localCursor.getInt(localCursor.getColumnIndex("android_notification_id")));
      } while (localCursor.moveToNext());
    }
    localCursor.close();
    localObject1 = ((u)localObject2).getWritableDatabase();
    ((SQLiteDatabase)localObject1).beginTransaction();
    try
    {
      localObject2 = new ContentValues();
      ((ContentValues)localObject2).put("dismissed", Integer.valueOf(1));
      ((SQLiteDatabase)localObject1).update("notification", (ContentValues)localObject2, "opened = 0", null);
      ((SQLiteDatabase)localObject1).setTransactionSuccessful();
    }
    catch (Exception localException)
    {
      for (;;)
      {
        a(d.c, "Error marking all notifications as dismissed! ", localException);
        ((SQLiteDatabase)localObject1).endTransaction();
      }
    }
    finally
    {
      ((SQLiteDatabase)localObject1).endTransaction();
    }
    f.a(0, c);
  }
  
  static long j()
  {
    if ((p == -1L) && (c != null)) {
      p = d(c).getLong("GT_UNSENT_ACTIVE_TIME", 0L);
    }
    a(d.e, "GetUnsentActiveTime: " + p);
    return p;
  }
  
  static boolean k()
  {
    return (d) && (b());
  }
  
  static void l()
  {
    I = false;
    a(System.currentTimeMillis());
  }
  
  public static class a
  {
    t.e a;
    t.f b;
    boolean c;
    boolean d;
    t.g e = t.g.b;
    
    private a() {}
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
    public abstract void a(q paramQ);
  }
  
  public static abstract interface f
  {
    public abstract void a(o paramO);
  }
  
  public static enum g
  {
    private g() {}
  }
}
