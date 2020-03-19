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
import android.support.annotation.NonNull;
import android.util.Base64;
import android.util.Log;
import com.onesignal.ActivityLifecycleListenerCompat;
import com.onesignal.SyncService;
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

public class dpd
{
  private static Double A;
  private static Double B;
  private static Float C;
  private static Integer D;
  private static boolean E;
  private static dpd.b F;
  private static boolean G;
  private static boolean H;
  private static boolean I;
  private static JSONObject J;
  private static int K = 0;
  public static String a;
  static String b;
  public static Context c;
  public static boolean d;
  public static String e;
  static dpd.a f;
  static Collection<JSONArray> g;
  public static boolean h;
  private static dpd.d i = dpd.d.a;
  private static dpd.d j = dpd.d.d;
  private static String k = null;
  private static int l;
  private static boolean m;
  private static dpd.c n;
  private static long o = 1L;
  private static long p = -1L;
  private static dpn q;
  private static dpm r;
  private static doo s = new don();
  private static int t;
  private static dpc u;
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
  
  public dpd() {}
  
  private static void D()
  {
    if (H) {
      return;
    }
    boolean bool2 = true;
    H = true;
    w = false;
    if (I) {
      x = false;
    }
    E();
    G();
    boolean bool1 = bool2;
    if (!z) {
      if (f.d) {
        bool1 = bool2;
      } else {
        bool1 = false;
      }
    }
    z = bool1;
  }
  
  private static void E()
  {
    boolean bool2 = E;
    boolean bool1 = true;
    if (bool2)
    {
      Context localContext = c;
      if ((!f.d) || (z)) {
        bool1 = false;
      }
      dot.a(localContext, bool1, new dot.b()
      {
        public void a(Double paramAnonymousDouble1, Double paramAnonymousDouble2, Float paramAnonymousFloat, Integer paramAnonymousInteger)
        {
          dpd.a(paramAnonymousDouble1);
          dpd.b(paramAnonymousDouble2);
          dpd.a(paramAnonymousFloat);
          dpd.a(paramAnonymousInteger);
          dpd.b(true);
          dpd.k();
        }
      });
      return;
    }
    x = true;
    J();
  }
  
  private static void F()
  {
    Object localObject;
    if (t == 2) {
      localObject = new dpi();
    } else {
      localObject = new dpj();
    }
    ((dph)localObject).a(c, b, new dph.a()
    {
      public void a(String paramAnonymousString, int paramAnonymousInt)
      {
        if (paramAnonymousInt < 1)
        {
          if ((dpg.d() == null) && ((dpd.l() == 1) || (dpd.l() < -6))) {
            dpd.a(paramAnonymousInt);
          }
        }
        else if (dpd.l() < -6) {
          dpd.a(paramAnonymousInt);
        }
        dpd.c(paramAnonymousString);
        dpd.c(true);
        dpd.k();
      }
    });
  }
  
  private static void G()
  {
    if (y)
    {
      F();
      return;
    }
    dpf.a local5 = new dpf.a()
    {
      void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        new Thread(new Runnable()
        {
          public void run()
          {
            try
            {
              Thread.sleep(dpd.m() * 10000 + 30000);
              dpd.n();
              dpd.o();
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
            dpd.b = paramAnonymousString.getString("android_sender_id");
          }
          dpd.b(paramAnonymousString.getJSONObject("awl_list"));
        }
        catch (Throwable paramAnonymousString)
        {
          paramAnonymousString.printStackTrace();
        }
        dpd.d(true);
        dpd.p();
      }
    };
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("apps/");
    ((StringBuilder)localObject).append(a);
    ((StringBuilder)localObject).append("/android_params.js");
    String str1 = ((StringBuilder)localObject).toString();
    String str2 = e();
    localObject = str1;
    if (str2 != null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(str1);
      ((StringBuilder)localObject).append("?player_id=");
      ((StringBuilder)localObject).append(str2);
      localObject = ((StringBuilder)localObject).toString();
    }
    dpf.a((String)localObject, local5);
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
    dpd.d localD = dpd.d.f;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("registerUser: registerForPushFired:");
    localStringBuilder.append(w);
    localStringBuilder.append(", locationFired: ");
    localStringBuilder.append(x);
    localStringBuilder.append(", awlFired: ");
    localStringBuilder.append(y);
    a(localD, localStringBuilder.toString());
    if ((w) && (x))
    {
      if (!y) {
        return;
      }
      new Thread(new Runnable()
      {
        public void run()
        {
          dpg.c localC = dpg.b();
          Object localObject2 = dpd.c.getPackageName();
          Object localObject1 = dpd.c.getPackageManager();
          localC.a("app_id", dpd.a);
          localC.a("identifier", dpd.q());
          Object localObject3 = dpd.r().a(dpd.c);
          if (localObject3 != null) {
            localC.a("ad_id", localObject3);
          }
          localC.a("device_os", Build.VERSION.RELEASE);
          localC.a("timezone", Integer.valueOf(dpd.s()));
          localC.a("language", dpc.d());
          localC.a("sdk", "030403");
          localC.a("sdk_type", dpd.e);
          localC.a("android_package", localObject2);
          localC.a("device_model", Build.MODEL);
          localC.a("device_type", Integer.valueOf(dpd.t()));
          localC.b("subscribableStatus", Integer.valueOf(dpd.l()));
          try
          {
            localC.a("game_version", Integer.valueOf(((PackageManager)localObject1).getPackageInfo((String)localObject2, 0).versionCode));
            try
            {
              localObject1 = ((PackageManager)localObject1).getInstalledPackages(0);
              localObject2 = new JSONArray();
              localObject3 = MessageDigest.getInstance("SHA-256");
              i = 0;
            }
            catch (Throwable localThrowable)
            {
              for (;;)
              {
                int i;
                String str;
                continue;
                i += 1;
              }
            }
            if (i < ((List)localObject1).size())
            {
              ((MessageDigest)localObject3).update(((PackageInfo)((List)localObject1).get(i)).packageName.getBytes());
              str = Base64.encodeToString(((MessageDigest)localObject3).digest(), 2);
              if (dpd.u().has(str)) {
                ((JSONArray)localObject2).put(str);
              }
            }
            else
            {
              localC.a("pkgs", localObject2);
              localC.a("net_type", dpd.v().b());
              localC.a("carrier", dpd.v().c());
              localC.a("rooted", Boolean.valueOf(dpl.a()));
              localC.a("lat", dpd.w());
              localC.a("long", dpd.x());
              localC.a("loc_acc", dpd.y());
              localC.a("loc_type", dpd.z());
              dpg.a(localC, dpd.A());
              dpd.e(false);
              return;
            }
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            for (;;) {}
          }
        }
      }, "OS_REG_USER").start();
      return;
    }
  }
  
  private static void K()
  {
    if (n == null) {
      return;
    }
    String str1 = dpg.d();
    if (!dpg.c()) {
      str1 = null;
    }
    String str2 = e();
    if (str2 == null) {
      return;
    }
    n.a(str2, str1);
    if (str1 != null) {
      n = null;
    }
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
    Context localContext = c;
    localContext.startService(new Intent(localContext, SyncService.class));
  }
  
  public static dpd.a a(Context paramContext)
  {
    return new dpd.a(paramContext, null);
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
        a(dpd.d.f, "Not a OneSignal formatted GCM message. No 'i' field in custom.");
        return null;
      }
      a(dpd.d.f, "Not a OneSignal formatted GCM message. No 'custom' field in the bundle.");
      return null;
    }
    catch (Throwable paramBundle)
    {
      a(dpd.d.f, "Could not parse bundle, probably not a OneSignal notification.", paramBundle);
    }
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
    dpn localDpn = q;
    if (localDpn != null) {
      localDpn.a();
    }
    dox.a(c);
  }
  
  static void a(long paramLong)
  {
    SharedPreferences.Editor localEditor = e(c).edit();
    localEditor.putLong("OS_LAST_SESSION_TIME", paramLong);
    localEditor.apply();
  }
  
  public static void a(long paramLong, boolean paramBoolean)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("app_id", a);
      localJSONObject.put("type", 1);
      localJSONObject.put("state", "ping");
      localJSONObject.put("active_time", paramLong);
      c(localJSONObject);
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("players/");
      ((StringBuilder)localObject).append(e());
      ((StringBuilder)localObject).append("/on_focus");
      localObject = ((StringBuilder)localObject).toString();
      dpf.a local7 = new dpf.a()
      {
        void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
        {
          dpd.a("sending on_focus Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
        }
        
        void a(String paramAnonymousString)
        {
          dpd.b(0L);
        }
      };
      if (paramBoolean)
      {
        dpf.d((String)localObject, localJSONObject, local7);
        return;
      }
      dpf.b((String)localObject, localJSONObject, local7);
      return;
    }
    catch (Throwable localThrowable)
    {
      a(dpd.d.c, "Generating on_focus:JSON Failed.", localThrowable);
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, dpd.e paramE, dpd.f paramF)
  {
    if (f == null) {
      f = new dpd.a(null);
    }
    dpd.a localA = f;
    localA.b = paramE;
    localA.c = paramF;
    b = paramString1;
    u = new dpc();
    t = u.a();
    l = u.a(t, paramString2);
    if (l == 64537) {
      return;
    }
    if (d)
    {
      if (paramContext != null) {
        c = paramContext.getApplicationContext();
      }
      if (f.b != null) {
        H();
      }
      return;
    }
    boolean bool = paramContext instanceof Activity;
    m = bool;
    a = paramString2;
    c = paramContext.getApplicationContext();
    if (bool)
    {
      dol.b = (Activity)paramContext;
      dox.a(c);
      M();
    }
    else
    {
      dol.a = true;
    }
    o = SystemClock.elapsedRealtime();
    dpg.a(c);
    if (Build.VERSION.SDK_INT > 13) {
      ((Application)c).registerActivityLifecycleCallbacks(new dom());
    } else {
      ActivityLifecycleListenerCompat.startListener();
    }
    try
    {
      Class.forName("com.amazon.device.iap.PurchasingListener");
      r = new dpm(c);
      paramContext = d();
      if (paramContext != null)
      {
        if (!paramContext.equals(a))
        {
          a(dpd.d.f, "APP ID changed, clearing user id as it is no longer valid.");
          d(a);
          dpg.e();
        }
      }
      else
      {
        doq.a(0, c);
        d(a);
      }
      if ((m) || (e() == null))
      {
        I = L();
        a(System.currentTimeMillis());
        D();
      }
      if (f.b != null) {
        H();
      }
      if (dpn.a(c)) {
        q = new dpn(c);
      }
      d = true;
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
    boolean bool2 = "DISABLE".equals(dpc.a(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
    boolean bool1;
    if (!bool2) {
      bool1 = a(paramContext, paramJSONArray);
    } else {
      bool1 = false;
    }
    b(paramJSONArray, true, paramBoolean);
    if ((!paramBoolean) && (!bool1) && (!bool2)) {
      f(paramContext);
    }
  }
  
  private static void a(dpa paramDpa)
  {
    if (Looper.getMainLooper().getThread() == Thread.currentThread())
    {
      f.b.a(paramDpa);
      return;
    }
    a(new Runnable()
    {
      public void run()
      {
        dpd.f.b.a(this.a);
      }
    });
  }
  
  private static void a(dpd.b paramB)
  {
    if (paramB == null) {
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        dpg.a localA = dpg.b(dpd.B() ^ true);
        if (localA.a) {
          dpd.f(true);
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
  
  public static void a(dpd.c paramC)
  {
    n = paramC;
    if (e() != null) {
      K();
    }
  }
  
  public static void a(dpd.d paramD, String paramString)
  {
    a(paramD, paramString, null);
  }
  
  static void a(dpd.d paramD, final String paramString, Throwable paramThrowable)
  {
    if (paramD.compareTo(j) < 1) {
      if (paramD == dpd.d.g) {
        Log.v("OneSignal", paramString, paramThrowable);
      } else if (paramD == dpd.d.f) {
        Log.d("OneSignal", paramString, paramThrowable);
      } else if (paramD == dpd.d.e) {
        Log.i("OneSignal", paramString, paramThrowable);
      } else if (paramD == dpd.d.d) {
        Log.w("OneSignal", paramString, paramThrowable);
      } else if ((paramD == dpd.d.c) || (paramD == dpd.d.b)) {
        Log.e("OneSignal", paramString, paramThrowable);
      }
    }
    if ((paramD.compareTo(i) < 1) && (dol.b != null)) {
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
        a(new Runnable()
        {
          public void run()
          {
            if (dol.b != null) {
              new AlertDialog.Builder(dol.b).setTitle(this.a.toString()).setMessage(paramString).show();
            }
          }
        });
        return;
      }
      catch (Throwable paramD)
      {
        Log.e("OneSignal", "Error showing logging message.", paramD);
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
    paramString = c;
    if (paramString == null) {
      return;
    }
    paramString = e(paramString).edit();
    paramString.putString("GT_PLAYER_ID", k);
    paramString.commit();
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean, dpf.a paramA)
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
      paramJSONArray = new StringBuilder();
      paramJSONArray.append("players/");
      paramJSONArray.append(e());
      paramJSONArray.append("/on_purchase");
      dpf.b(paramJSONArray.toString(), localJSONObject, paramA);
      return;
    }
    catch (Throwable paramJSONArray)
    {
      a(dpd.d.c, "Failed to generate JSON for sendPurchases.", paramJSONArray);
    }
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    dpd.a localA = f;
    if (localA != null)
    {
      if (localA.c == null) {
        return;
      }
      paramJSONArray = c(paramJSONArray, paramBoolean1, paramBoolean2);
      f.c.a(paramJSONArray.a);
      return;
    }
  }
  
  public static void a(boolean paramBoolean)
  {
    m = false;
    if (!d) {
      return;
    }
    dpm localDpm = r;
    if (localDpm != null) {
      localDpm.a();
    }
    if (o == -1L) {
      return;
    }
    double d1 = SystemClock.elapsedRealtime() - o;
    Double.isNaN(d1);
    long l1 = (d1 / 1000.0D + 0.5D);
    o = SystemClock.elapsedRealtime();
    if (l1 >= 0L)
    {
      if (l1 > 86400L) {
        return;
      }
      if (c == null)
      {
        a(dpd.d.c, "Android Context not found, please call OneSignal.init when your app starts.");
        return;
      }
      a(System.currentTimeMillis());
      l1 = h() + l1;
      if ((!paramBoolean) && (l1 >= 60L) && (e() != null))
      {
        a(l1, true);
        return;
      }
      c(l1);
      return;
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
            localObject1 = new Intent("android.intent.action.VIEW", Uri.parse((String)localObject1));
            ((Intent)localObject1).addFlags(1476919296);
            paramContext.startActivity((Intent)localObject1);
            bool2 = true;
          }
        }
      }
      catch (Throwable localThrowable)
      {
        Object localObject2 = dpd.d.c;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Error parsing JSON item ");
        localStringBuilder.append(i1);
        localStringBuilder.append("/");
        localStringBuilder.append(i2);
        localStringBuilder.append(" for launching a web URL.");
        a((dpd.d)localObject2, localStringBuilder.toString(), localThrowable);
        bool2 = bool1;
      }
      i1 += 1;
    }
    return bool1;
  }
  
  static boolean a(Context paramContext, JSONObject paramJSONObject)
  {
    paramJSONObject = a(paramJSONObject);
    return (paramJSONObject == null) || (a(paramJSONObject, paramContext));
  }
  
  private static boolean a(dpd.d paramD)
  {
    int i1 = paramD.compareTo(i);
    boolean bool = true;
    if (i1 >= 1)
    {
      if (paramD.compareTo(j) < 1) {
        return true;
      }
      bool = false;
    }
    return bool;
  }
  
  static boolean a(String paramString, Context paramContext)
  {
    if (paramString != null)
    {
      if ("".equals(paramString)) {
        return false;
      }
      paramContext = dpe.a(paramContext).getReadableDatabase().query("notification", new String[] { "notification_id" }, "notification_id = ?", new String[] { paramString }, null, null, null);
      boolean bool = paramContext.moveToFirst();
      paramContext.close();
      if (bool)
      {
        paramContext = dpd.d.f;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Duplicate GCM message received, skip processing of ");
        localStringBuilder.append(paramString);
        a(paramContext, localStringBuilder.toString());
        return true;
      }
      return false;
    }
    return false;
  }
  
  private static void b(Context paramContext, JSONArray paramJSONArray)
  {
    int i1 = 0;
    while (i1 < paramJSONArray.length())
    {
      try
      {
        Object localObject = paramJSONArray.getJSONObject(i1);
        if (((JSONObject)localObject).has("custom"))
        {
          localObject = new JSONObject(((JSONObject)localObject).optString("custom", null));
          if (((JSONObject)localObject).has("i"))
          {
            localObject = ((JSONObject)localObject).optString("i", null);
            JSONObject localJSONObject = new JSONObject();
            localJSONObject.put("app_id", g(paramContext));
            localJSONObject.put("player_id", h(paramContext));
            localJSONObject.put("opened", true);
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("notifications/");
            localStringBuilder.append((String)localObject);
            dpf.a(localStringBuilder.toString(), localJSONObject, new dpf.a()
            {
              void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
              {
                dpd.a("sending Notification Opened Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
              }
            });
          }
        }
      }
      catch (Throwable localThrowable)
      {
        a(dpd.d.c, "Failed to generate JSON to send notification opened.", localThrowable);
      }
      i1 += 1;
    }
  }
  
  private static void b(dpd.a paramA)
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
    Object localObject2 = "";
    Object localObject1 = localObject2;
    if (paramString2 != null)
    {
      localObject1 = localObject2;
      if (a(dpd.d.e))
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("\n");
        ((StringBuilder)localObject1).append(paramString2);
        ((StringBuilder)localObject1).append("\n");
        localObject1 = ((StringBuilder)localObject1).toString();
      }
    }
    paramString2 = dpd.d.d;
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
    dpd.a localA = f;
    if ((localA != null) && (localA.b != null))
    {
      a(c(paramJSONArray, paramBoolean1, paramBoolean2));
      return;
    }
    g.add(paramJSONArray);
  }
  
  static boolean b()
  {
    return m;
  }
  
  static boolean b(Context paramContext)
  {
    return e(paramContext).getBoolean("GT_VIBRATE_ENABLED", true);
  }
  
  @NonNull
  private static dpa c(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i3 = paramJSONArray.length();
    dpa localDpa = new dpa();
    doy localDoy = new doy();
    localDoy.a = i();
    localDoy.b = paramBoolean1;
    localDoy.c = paramJSONArray.optJSONObject(0).optInt("notificationId");
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
        localObject3 = localObject1;
        if (!((JSONObject)localObject5).has("custom")) {
          break label384;
        }
        localObject4 = localObject1;
        localDoy.d = dou.a((JSONObject)localObject5);
        localObject3 = localObject1;
        if (localObject1 != null) {
          break label384;
        }
        localObject4 = localObject1;
        localObject3 = localObject1;
        if (!((JSONObject)localObject5).has("actionSelected")) {
          break label384;
        }
        localObject4 = localObject1;
        localObject3 = ((JSONObject)localObject5).optString("actionSelected", null);
      }
      catch (Throwable localThrowable)
      {
        label158:
        localObject3 = dpd.d.c;
        Object localObject5 = new StringBuilder();
        ((StringBuilder)localObject5).append("Error parsing JSON item ");
        ((StringBuilder)localObject5).append(i1);
        ((StringBuilder)localObject5).append("/");
        ((StringBuilder)localObject5).append(i3);
        ((StringBuilder)localObject5).append(" for callback.");
        a((dpd.d)localObject3, ((StringBuilder)localObject5).toString(), localThrowable);
        localObject2 = localObject4;
      }
      localObject4 = localObject3;
      if (localDoy.f == null)
      {
        localObject4 = localObject3;
        localDoy.f = new ArrayList();
      }
      localObject4 = localObject3;
      localDoy.f.add(localDoy.d);
      localObject1 = localObject3;
    }
    for (;;)
    {
      i1 += 1;
      break;
      localDpa.a = localDoy;
      localDpa.b = new doz();
      localDpa.b.b = localObject2;
      localObject3 = localDpa.b;
      if (localObject2 != null) {
        paramJSONArray = doz.a.b;
      } else {
        paramJSONArray = doz.a.a;
      }
      ((doz)localObject3).a = paramJSONArray;
      if (paramBoolean2)
      {
        localDpa.a.e = doy.a.b;
        return localDpa;
      }
      localDpa.a.e = doy.a.a;
      return localDpa;
      label384:
      if (i2 == 0) {
        break label158;
      }
      i2 = 0;
      Object localObject2 = localObject3;
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
    Object localObject = dpd.d.e;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SaveUnsentActiveTime: ");
    localStringBuilder.append(p);
    a((dpd.d)localObject, localStringBuilder.toString());
    localObject = e(c).edit();
    ((SharedPreferences.Editor)localObject).putLong("GT_UNSENT_ACTIVE_TIME", paramLong);
    ((SharedPreferences.Editor)localObject).commit();
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
  
  public static String d()
  {
    return g(c);
  }
  
  private static void d(String paramString)
  {
    Object localObject = c;
    if (localObject == null) {
      return;
    }
    localObject = e((Context)localObject).edit();
    ((SharedPreferences.Editor)localObject).putString("GT_APP_ID", paramString);
    ((SharedPreferences.Editor)localObject).commit();
  }
  
  static SharedPreferences e(Context paramContext)
  {
    return paramContext.getSharedPreferences(dpd.class.getSimpleName(), 0);
  }
  
  public static String e()
  {
    if (k == null)
    {
      Context localContext = c;
      if (localContext != null) {
        k = e(localContext).getString("GT_PLAYER_ID", null);
      }
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
    dpd.a localA = f;
    if (localA == null) {
      return true;
    }
    return localA.f == dpd.g.c;
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
    dpd.a localA = f;
    boolean bool = false;
    if (localA == null) {
      return false;
    }
    if (localA.f == dpd.g.b) {
      bool = true;
    }
    return bool;
  }
  
  public static long h()
  {
    if (p == -1L)
    {
      localObject = c;
      if (localObject != null) {
        p = e((Context)localObject).getLong("GT_UNSENT_ACTIVE_TIME", 0L);
      }
    }
    Object localObject = dpd.d.e;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("GetUnsentActiveTime: ");
    localStringBuilder.append(p);
    a((dpd.d)localObject, localStringBuilder.toString());
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
    dpd.e b;
    dpd.f c;
    boolean d;
    boolean e;
    dpd.g f = dpd.g.b;
    
    private a() {}
    
    private a(Context paramContext)
    {
      this.a = paramContext;
    }
    
    public void a()
    {
      dpd.a(this);
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
    public abstract void a(dpa paramDpa);
  }
  
  public static abstract interface f
  {
    public abstract void a(doy paramDoy);
  }
  
  public static enum g
  {
    private g() {}
  }
}
