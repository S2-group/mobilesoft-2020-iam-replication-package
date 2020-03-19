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
import com.onesignal.OSPermissionChangedInternalObserver;
import com.onesignal.OSSubscriptionChangedInternalObserver;
import com.onesignal.OSSubscriptionState;
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

public class dsi
{
  private static long A = -1L;
  private static dsy B;
  private static dsw C;
  private static dsx D;
  private static dre E = new drd();
  private static int F = 0;
  private static dsh G;
  private static String H;
  private static boolean I = false;
  private static boolean J = false;
  private static boolean K = false;
  private static boolean L = false;
  private static drq.f M;
  private static Collection<JSONArray> N = new ArrayList();
  private static HashSet<String> O = new HashSet();
  private static dsi.f P;
  private static boolean Q = false;
  private static boolean R = false;
  private static boolean S = false;
  private static JSONObject T;
  private static boolean U = false;
  private static dse V;
  private static dsd<Object, dsf> W;
  private static OSSubscriptionState X;
  private static dsd<Object, dsg> Y;
  private static dry Z;
  static String a;
  private static dsi.g aa;
  private static dsq ab;
  private static int ac = 0;
  public static Context b;
  public static boolean c = false;
  static ExecutorService d;
  public static ConcurrentLinkedQueue<Runnable> e;
  static AtomicLong f;
  public static String g = "native";
  static boolean h = true;
  static dsi.a i;
  static boolean j = false;
  static boolean k = false;
  static drl l;
  public static dse m;
  public static OSSubscriptionState n;
  private static dsi.e o;
  private static dsi.e p;
  private static String q;
  private static boolean r = false;
  private static dsi.i s = dsi.i.a;
  private static dsi.i t = dsi.i.d;
  private static String u;
  private static String v;
  private static int w = 0;
  private static boolean x = false;
  private static dsi.h y;
  private static long z = 1L;
  
  static
  {
    e = new ConcurrentLinkedQueue();
    f = new AtomicLong();
  }
  
  public dsi() {}
  
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
      if (i.d) {
        bool1 = bool2;
      } else {
        bool1 = false;
      }
    }
    L = bool1;
  }
  
  private static void K()
  {
    drq.d local8 = new drq.d()
    {
      public drq.a a()
      {
        return drq.a.a;
      }
      
      public void a(drq.f paramAnonymousF)
      {
        dsi.a(paramAnonymousF);
        dsi.c(true);
        dsi.x();
      }
    };
    boolean bool;
    if ((i.d) && (!L)) {
      bool = true;
    } else {
      bool = false;
    }
    drq.a(b, bool, local8);
  }
  
  private static dsq L()
  {
    if (ab != null) {
      return ab;
    }
    if (F == 2) {
      ab = new dsr();
    } else if (dsh.a()) {
      ab = new dst();
    } else {
      ab = new dsu();
    }
    return ab;
  }
  
  private static void M()
  {
    L().a(b, q, new dsq.a()
    {
      public void a(String paramAnonymousString, int paramAnonymousInt)
      {
        if (paramAnonymousInt < 1)
        {
          if ((dso.g() == null) && ((dsi.y() == 1) || (dsi.a(dsi.y())))) {
            dsi.b(paramAnonymousInt);
          }
        }
        else if (dsi.a(dsi.y())) {
          dsi.b(paramAnonymousInt);
        }
        dsi.f(paramAnonymousString);
        dsi.d(true);
        dsi.g(dsi.b).b(paramAnonymousString);
        dsi.x();
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
    dsn.a local10 = new dsn.a()
    {
      void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        new Thread(new Runnable()
        {
          public void run()
          {
            try
            {
              int j = 30000 + dsi.z() * 10000;
              int i = j;
              if (j > 90000) {
                i = 90000;
              }
              dsi.i localI = dsi.i.e;
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("Failed to get Android parameters, trying again in ");
              localStringBuilder.append(i / 1000);
              localStringBuilder.append(" seconds.");
              dsi.a(localI, localStringBuilder.toString());
              Thread.sleep(i);
            }
            catch (Throwable localThrowable)
            {
              for (;;) {}
            }
            dsi.A();
            dsi.B();
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
            dsi.e(true);
            dsi.g(paramAnonymousString.getString("android_sender_id"));
          }
          dsi.j = paramAnonymousString.optBoolean("enterp", false);
          dsi.f(paramAnonymousString.optBoolean("use_email_auth", false));
          dsi.b(paramAnonymousString.getJSONObject("awl_list"));
          boolean bool = paramAnonymousString.optBoolean("fba", false);
          dsm.a(dsm.a, "GT_FIREBASE_TRACKING_ENABLED", bool);
          drs.a(dsi.b, paramAnonymousString);
        }
        catch (Throwable paramAnonymousString)
        {
          paramAnonymousString.printStackTrace();
        }
        dsi.g(true);
        dsi.C();
      }
    };
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("apps/");
    ((StringBuilder)localObject).append(a);
    ((StringBuilder)localObject).append("/android_params.js");
    String str1 = ((StringBuilder)localObject).toString();
    String str2 = l();
    localObject = str1;
    if (str2 != null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(str1);
      ((StringBuilder)localObject).append("?player_id=");
      ((StringBuilder)localObject).append(str2);
      localObject = ((StringBuilder)localObject).toString();
    }
    a(dsi.i.f, "Starting request to get Android parameters.");
    dsn.a((String)localObject, local10);
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
    dsi.i localI = dsi.i.f;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("registerUser: registerForPushFired:");
    localStringBuilder.append(I);
    localStringBuilder.append(", locationFired: ");
    localStringBuilder.append(J);
    localStringBuilder.append(", awlFired: ");
    localStringBuilder.append(K);
    a(localI, localStringBuilder.toString());
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
            dsi.D();
            dsj.a(dsi.b, dsi.a, dsi.E(), drd.a());
            return;
          }
          catch (JSONException localJSONException)
          {
            dsi.a(dsi.i.b, "FATAL Error registering device!", localJSONException);
          }
        }
      }, "OS_REG_USER").start();
      return;
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
    localJSONObject.put("language", dsh.f());
    localJSONObject.put("sdk", "031005");
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
        localJSONObject.put("rooted", dsv.a());
        dso.a(localJSONObject);
        localJSONObject = new JSONObject();
        localJSONObject.put("identifier", H);
        localJSONObject.put("subscribableStatus", w);
        localJSONObject.put("androidPermission", s());
        localJSONObject.put("device_type", F);
        dso.b(localJSONObject);
        if ((h) && (M != null)) {
          dso.a(M);
        }
        if (S) {
          dso.j();
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
    try
    {
      Object localObject1 = y;
      if (localObject1 == null) {
        return;
      }
      localObject1 = dso.g();
      if (!dso.f()) {
        localObject1 = null;
      }
      String str = l();
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
  
  private static boolean T()
  {
    if (S) {
      return true;
    }
    return (System.currentTimeMillis() - n(b)) / 1000L >= 30L;
  }
  
  public static dsd<Object, dsf> a()
  {
    if (W == null) {
      W = new dsd("onOSPermissionChanged", true);
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
        a(dsi.i.f, "Not a OneSignal formatted GCM message. No 'i' field in custom.");
        return null;
      }
      a(dsi.i.f, "Not a OneSignal formatted GCM message. No 'custom' field in the bundle.");
      return null;
    }
    catch (Throwable paramBundle)
    {
      a(dsi.i.f, "Could not parse bundle, probably not a OneSignal notification.", paramBundle);
    }
    return null;
  }
  
  static void a(long paramLong)
  {
    dsm.a(dsm.a, "OS_LAST_SESSION_TIME", paramLong);
  }
  
  static void a(long paramLong, boolean paramBoolean)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject().put("app_id", a).put("type", 1).put("state", "ping").put("active_time", paramLong);
      c(localJSONObject);
      a(l(), localJSONObject, paramBoolean);
      String str = m();
      if (str != null)
      {
        a(str, localJSONObject, paramBoolean);
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      a(dsi.i.c, "Generating on_focus:JSON Failed.", localThrowable);
    }
  }
  
  public static void a(Context paramContext)
  {
    int i1;
    if (b == null) {
      i1 = 1;
    } else {
      i1 = 0;
    }
    b = paramContext.getApplicationContext();
    if (i1 != 0) {
      dsm.b();
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, dsi.j paramJ, dsi.k paramK)
  {
    a(paramContext);
    if ((k) && (!d()))
    {
      a(dsi.i.g, "OneSignal SDK initialization delayed, user privacy consent is set to required for this application.");
      l = new drl(paramContext, paramString1, paramString2, paramJ, paramK);
      return;
    }
    i = c();
    i.h = false;
    i.b = paramJ;
    i.c = paramK;
    if (!r) {
      q = paramString1;
    }
    G = new dsh();
    F = G.c();
    w = G.a(paramContext, F, paramString2);
    if (w == 64537) {
      return;
    }
    if (c)
    {
      if (i.b != null) {
        O();
      }
      return;
    }
    boolean bool = paramContext instanceof Activity;
    x = bool;
    a = paramString2;
    b(i.g);
    if (bool)
    {
      drb.b = (Activity)paramContext;
      drv.a(b);
    }
    else
    {
      drb.a = true;
    }
    z = SystemClock.elapsedRealtime();
    dso.d();
    ((Application)b).registerActivityLifecycleCallbacks(new drc());
    try
    {
      Class.forName("com.amazon.device.iap.PurchasingListener");
      C = new dsw(b);
      paramContext = j();
      if (paramContext != null)
      {
        if (!paramContext.equals(a))
        {
          a(dsi.i.f, "APP ID changed, clearing user id as it is no longer valid.");
          h(a);
          dso.h();
        }
      }
      else
      {
        drg.a(0, b);
        h(a);
      }
      OSPermissionChangedInternalObserver.a(h(b));
      if ((x) || (l() == null))
      {
        S = T();
        a(System.currentTimeMillis());
        J();
      }
      if (i.b != null) {
        O();
      }
      if (dsy.a(b)) {
        B = new dsy(b);
      }
      if (dsx.a()) {
        D = new dsx(b);
      }
      dst.a(b);
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
    if ((D != null) && (d(b))) {
      D.a(c(paramJSONArray, true, paramBoolean));
    }
    boolean bool1 = false;
    boolean bool2 = "DISABLE".equals(dsh.a(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
    if (!bool2) {
      bool1 = a(paramContext, paramJSONArray);
    }
    b(paramJSONArray, true, paramBoolean);
    if ((!paramBoolean) && (!bool1) && (!bool2)) {
      k(paramContext);
    }
  }
  
  private static void a(dsb paramDsb)
  {
    dsh.a(new Runnable()
    {
      public void run()
      {
        dsi.i.b.a(this.a);
      }
    });
  }
  
  private static void a(dsi.f paramF)
  {
    if (paramF == null) {
      return;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        dte.a localA = dso.c(dsi.F() ^ true);
        if (localA.a) {
          dsi.h(true);
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
  
  public static void a(dsi.h paramH)
  {
    if (a("idsAvailable()")) {
      return;
    }
    y = paramH;
    paramH = new Runnable()
    {
      public void run()
      {
        if (dsi.l() != null) {
          dsh.a(new Runnable()
          {
            public void run() {}
          });
        }
      }
    };
    if ((b != null) && (!I()))
    {
      paramH.run();
      return;
    }
    a(dsi.i.c, "You must initialize OneSignal before getting tags! Moving this tag operation to a pending queue.");
    a(new dsi.m(paramH));
  }
  
  public static void a(dsi.i paramI, String paramString)
  {
    a(paramI, paramString, null);
  }
  
  static void a(dsi.i paramI, final String paramString, Throwable paramThrowable)
  {
    if (paramI.compareTo(t) < 1) {
      if (paramI == dsi.i.g) {
        Log.v("OneSignal", paramString, paramThrowable);
      } else if (paramI == dsi.i.f) {
        Log.d("OneSignal", paramString, paramThrowable);
      } else if (paramI == dsi.i.e) {
        Log.i("OneSignal", paramString, paramThrowable);
      } else if (paramI == dsi.i.d) {
        Log.w("OneSignal", paramString, paramThrowable);
      } else if ((paramI == dsi.i.c) || (paramI == dsi.i.b)) {
        Log.e("OneSignal", paramString, paramThrowable);
      }
    }
    if ((paramI.compareTo(s) < 1) && (drb.b != null)) {
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
        dsh.a(new Runnable()
        {
          public void run()
          {
            if (drb.b != null) {
              new AlertDialog.Builder(drb.b).setTitle(this.a.toString()).setMessage(paramString).show();
            }
          }
        });
        return;
      }
      catch (Throwable paramI)
      {
        Log.e("OneSignal", "Error showing logging message.", paramI);
      }
    }
  }
  
  private static void a(dsi.m paramM)
  {
    dsi.m.a(paramM, f.incrementAndGet());
    dsi.i localI;
    StringBuilder localStringBuilder;
    if (d == null)
    {
      localI = dsi.i.e;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Adding a task to the pending queue with ID: ");
      localStringBuilder.append(dsi.m.a(paramM));
      a(localI, localStringBuilder.toString());
      e.add(paramM);
      return;
    }
    if (!d.isShutdown())
    {
      localI = dsi.i.e;
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Executor is still running, add to the executor with ID: ");
      localStringBuilder.append(dsi.m.a(paramM));
      a(localI, localStringBuilder.toString());
      d.submit(paramM);
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
    localObject = new dsn.a()
    {
      void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        dsi.a("sending on_focus Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
      }
      
      void a(String paramAnonymousString)
      {
        dsi.c(0L);
      }
    };
    if (paramBoolean)
    {
      dsn.d(paramString, paramJSONObject, (dsn.a)localObject);
      return;
    }
    dsn.b(paramString, paramJSONObject, (dsn.a)localObject);
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean, dsn.a paramA)
  {
    if (a("sendPurchases()")) {
      return;
    }
    if (l() == null)
    {
      aa = new dsi.g(paramJSONArray);
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
      paramJSONArray.append(l());
      paramJSONArray.append("/on_purchase");
      dsn.b(paramJSONArray.toString(), localJSONObject, paramA);
      if (m() != null)
      {
        paramJSONArray = new StringBuilder();
        paramJSONArray.append("players/");
        paramJSONArray.append(m());
        paramJSONArray.append("/on_purchase");
        dsn.b(paramJSONArray.toString(), localJSONObject, null);
        return;
      }
    }
    catch (Throwable paramJSONArray)
    {
      a(dsi.i.c, "Failed to generate JSON for sendPurchases.", paramJSONArray);
    }
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramJSONArray = c(paramJSONArray, paramBoolean1, paramBoolean2);
    if ((D != null) && (d(b))) {
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
  
  public static void a(JSONObject paramJSONObject)
  {
    a(paramJSONObject, null);
  }
  
  public static void a(JSONObject paramJSONObject, final dsi.b paramB)
  {
    if (a("sendTags()")) {
      return;
    }
    paramJSONObject = new Runnable()
    {
      public void run()
      {
        if (this.a == null)
        {
          if (paramB != null) {
            paramB.a(new dsi.n(-1, "Attempted to send null tags"));
          }
          return;
        }
        JSONObject localJSONObject1 = dso.c(false).b;
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
              if ((!this.a.isNull(str)) && (!"".equals(localObject)))
              {
                localJSONObject2.put(str, localObject.toString());
                continue;
              }
              if ((localJSONObject1 == null) || (!localJSONObject1.has(str))) {
                continue;
              }
              localJSONObject2.put(str, "");
              continue;
            }
            localObject = dsi.i.c;
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("Omitting key '");
            localStringBuilder.append(str);
            localStringBuilder.append("'! sendTags DO NOT supported nested values!");
            dsi.a((dsi.i)localObject, localStringBuilder.toString());
          }
          catch (Throwable localThrowable) {}
          if (!localJSONObject2.toString().equals("{}"))
          {
            dso.a(localJSONObject2, paramB);
            return;
          }
          if (paramB != null) {
            paramB.a(localJSONObject1);
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
    a(dsi.i.c, "You must initialize OneSignal before modifying tags!Moving this operation to a pending task queue.");
    if (paramB != null) {
      paramB.a(new dsi.n(-1, "You must initialize OneSignal before modifying tags!Moving this operation to a pending task queue."));
    }
    a(new dsi.m(paramJSONObject));
  }
  
  public static void a(boolean paramBoolean)
  {
    if ((k) && (!paramBoolean))
    {
      a(dsi.i.c, "Cannot change requiresUserPrivacyConsent() from TRUE to FALSE");
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
        Object localObject2 = dsi.i.c;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Error parsing JSON item ");
        localStringBuilder.append(i1);
        localStringBuilder.append("/");
        localStringBuilder.append(i2);
        localStringBuilder.append(" for launching a web URL.");
        a((dsi.i)localObject2, localStringBuilder.toString(), localThrowable);
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
  
  private static boolean a(dsi.i paramI)
  {
    int i1 = paramI.compareTo(s);
    boolean bool = true;
    if (i1 >= 1)
    {
      if (paramI.compareTo(t) < 1) {
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
        dsi.i localI = dsi.i.d;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Method ");
        localStringBuilder.append(paramString);
        localStringBuilder.append(" was called before the user provided privacy consent. Your application is set to require the user's privacy consent before the OneSignal SDK can be initialized. Please ensure the user has provided consent before calling this method. You can check the latest OneSignal consent status by calling OneSignal.userProvidedPrivacyConsent()");
        a(localI, localStringBuilder.toString());
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
    //   4: ldc_w 1074
    //   7: aload_0
    //   8: invokevirtual 778	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   11: ifeq +5 -> 16
    //   14: iconst_0
    //   15: ireturn
    //   16: aload_1
    //   17: invokestatic 1079	dsk:a	(Landroid/content/Context;)Ldsk;
    //   20: astore 4
    //   22: aconst_null
    //   23: astore 6
    //   25: aconst_null
    //   26: astore_1
    //   27: aload 4
    //   29: invokevirtual 1082	dsk:b	()Landroid/database/sqlite/SQLiteDatabase;
    //   32: ldc_w 1084
    //   35: iconst_1
    //   36: anewarray 501	java/lang/String
    //   39: dup
    //   40: iconst_0
    //   41: ldc_w 1086
    //   44: aastore
    //   45: ldc_w 1088
    //   48: iconst_1
    //   49: anewarray 501	java/lang/String
    //   52: dup
    //   53: iconst_0
    //   54: aload_0
    //   55: aastore
    //   56: aconst_null
    //   57: aconst_null
    //   58: aconst_null
    //   59: invokevirtual 1094	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   62: astore 4
    //   64: aload 4
    //   66: invokeinterface 1099 1 0
    //   71: istore_3
    //   72: iload_3
    //   73: istore_2
    //   74: aload 4
    //   76: ifnull +65 -> 141
    //   79: aload 4
    //   81: invokeinterface 1102 1 0
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
    //   116: getstatic 673	dsi$i:c	Ldsi$i;
    //   119: ldc_w 1104
    //   122: aload 5
    //   124: invokestatic 643	dsi:a	(Ldsi$i;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   127: aload 4
    //   129: ifnull +10 -> 139
    //   132: aload 4
    //   134: invokeinterface 1102 1 0
    //   139: iconst_0
    //   140: istore_2
    //   141: iload_2
    //   142: ifeq +43 -> 185
    //   145: getstatic 321	dsi$i:f	Ldsi$i;
    //   148: astore_1
    //   149: new 301	java/lang/StringBuilder
    //   152: dup
    //   153: invokespecial 302	java/lang/StringBuilder:<init>	()V
    //   156: astore 4
    //   158: aload 4
    //   160: ldc_w 1106
    //   163: invokevirtual 308	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: pop
    //   167: aload 4
    //   169: aload_0
    //   170: invokevirtual 308	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: pop
    //   174: aload_1
    //   175: aload 4
    //   177: invokevirtual 315	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   180: invokestatic 326	dsi:a	(Ldsi$i;Ljava/lang/String;)V
    //   183: iconst_1
    //   184: ireturn
    //   185: iconst_0
    //   186: ireturn
    //   187: aload_1
    //   188: ifnull +9 -> 197
    //   191: aload_1
    //   192: invokeinterface 1102 1 0
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
  
  public static dsd<Object, dsg> b()
  {
    if (Y == null) {
      Y = new dsd("onOSSubscriptionChanged", true);
    }
    return Y;
  }
  
  public static dsi.a b(Context paramContext)
  {
    return new dsi.a(paramContext, null);
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
          localJSONObject.put("app_id", l(paramContext));
          localJSONObject.put("player_id", m(paramContext));
          localJSONObject.put("opened", true);
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("notifications/");
          localStringBuilder.append(str);
          dsn.a(localStringBuilder.toString(), localJSONObject, new dsn.a()
          {
            void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
            {
              dsi.a("sending Notification Opened Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
            }
          });
        }
      }
      catch (Throwable localThrowable)
      {
        a(dsi.i.c, "Failed to generate JSON to send notification opened.", localThrowable);
      }
      i1 += 1;
    }
  }
  
  private static void b(dsi.a paramA)
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
    dsm.a(dsm.a, "GT_PLAYER_ID", u);
  }
  
  private static void b(String paramString1, int paramInt, Throwable paramThrowable, String paramString2)
  {
    Object localObject2 = "";
    Object localObject1 = localObject2;
    if (paramString2 != null)
    {
      localObject1 = localObject2;
      if (a(dsi.i.e))
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("\n");
        ((StringBuilder)localObject1).append(paramString2);
        ((StringBuilder)localObject1).append("\n");
        localObject1 = ((StringBuilder)localObject1).toString();
      }
    }
    paramString2 = dsi.i.d;
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
  
  static void b(boolean paramBoolean)
  {
    if (b == null) {
      return;
    }
    dsm.a(dsm.a, "OS_FILTER_OTHER_GCM_RECEIVERS", paramBoolean);
  }
  
  private static dsb c(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i3 = paramJSONArray.length();
    dsb localDsb = new dsb();
    drz localDrz = new drz();
    localDrz.a = q();
    localDrz.b = paramBoolean1;
    localDrz.c = paramJSONArray.optJSONObject(0).optInt("notificationId");
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
        localDrz.d = drr.a((JSONObject)localObject5);
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
        localObject3 = dsi.i.c;
        Object localObject5 = new StringBuilder();
        ((StringBuilder)localObject5).append("Error parsing JSON item ");
        ((StringBuilder)localObject5).append(i1);
        ((StringBuilder)localObject5).append("/");
        ((StringBuilder)localObject5).append(i3);
        ((StringBuilder)localObject5).append(" for callback.");
        a((dsi.i)localObject3, ((StringBuilder)localObject5).toString(), localThrowable);
        localObject2 = localObject4;
      }
      localObject4 = localObject3;
      if (localDrz.f == null)
      {
        localObject4 = localObject3;
        localDrz.f = new ArrayList();
      }
      localObject4 = localObject3;
      localDrz.f.add(localDrz.d);
      localObject1 = localObject3;
    }
    for (;;)
    {
      i1 += 1;
      break;
      localDsb.a = localDrz;
      localDsb.b = new dsa();
      localDsb.b.b = localObject2;
      localObject3 = localDsb.b;
      if (localObject2 != null) {
        paramJSONArray = dsa.a.b;
      } else {
        paramJSONArray = dsa.a.a;
      }
      ((dsa)localObject3).a = paramJSONArray;
      if (paramBoolean2)
      {
        localDsb.a.e = drz.a.b;
        return localDsb;
      }
      localDsb.a.e = drz.a.a;
      return localDsb;
      label365:
      if (i2 == 0) {
        break label139;
      }
      i2 = 0;
      Object localObject2 = localObject3;
    }
  }
  
  public static dsi.a c()
  {
    if (i == null) {
      i = new dsi.a(null);
    }
    return i;
  }
  
  static void c(String paramString)
  {
    v = paramString;
    if (b == null) {
      return;
    }
    String str = dsm.a;
    if ("".equals(v)) {
      paramString = null;
    } else {
      paramString = v;
    }
    dsm.a(str, "OS_EMAIL_ID", paramString);
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
  
  private static boolean c(int paramInt)
  {
    return paramInt < -6;
  }
  
  public static boolean c(Context paramContext)
  {
    return dsm.b(dsm.a, "OS_FILTER_OTHER_GCM_RECEIVERS", false);
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
      a(dsi.i.e, "Last Pending Task has ran, shutting down");
      d.shutdown();
    }
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
    dso.i();
    dsj.a(b, a, paramString, drd.a());
  }
  
  public static boolean d()
  {
    return k();
  }
  
  static boolean d(Context paramContext)
  {
    return dsm.b(dsm.a, "GT_FIREBASE_TRACKING_ENABLED", false);
  }
  
  private static void e(long paramLong)
  {
    A = paramLong;
    if (b == null) {
      return;
    }
    dsi.i localI = dsi.i.e;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SaveUnsentActiveTime: ");
    localStringBuilder.append(A);
    a(localI, localStringBuilder.toString());
    dsm.a(dsm.a, "GT_UNSENT_ACTIVE_TIME", paramLong);
  }
  
  static void e(String paramString)
  {
    c(paramString);
    j(b).a(paramString);
    try
    {
      dso.b(new JSONObject().put("parent_player_id", paramString));
      return;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  static boolean e()
  {
    boolean bool1 = false;
    x = false;
    drq.c();
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
        a(dsi.i.c, "Android Context not found, please call OneSignal.init when your app starts.");
        return false;
      }
      boolean bool2 = f();
      a(System.currentTimeMillis());
      l1 = p() + l1;
      e(l1);
      if ((l1 >= 60L) && (l() != null))
      {
        if (!bool2) {
          dsp.a(b);
        }
        dsp.a();
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
    return dsm.b(dsm.a, "GT_VIBRATE_ENABLED", true);
  }
  
  static boolean f()
  {
    boolean bool = dso.c();
    if (bool) {
      dsp.a(b);
    }
    return (drq.a(b)) || (bool);
  }
  
  static boolean f(Context paramContext)
  {
    return dsm.b(dsm.a, "GT_SOUND_ENABLED", true);
  }
  
  static void g()
  {
    x = true;
    drq.c();
    z = SystemClock.elapsedRealtime();
    S = T();
    a(System.currentTimeMillis());
    J();
    if (B != null) {
      B.a();
    }
    drv.a(b);
    h(b).a();
    if ((D != null) && (d(b))) {
      D.b();
    }
    dsp.b(b);
  }
  
  private static dse h(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (V == null)
    {
      V = new dse(false);
      V.a.b(new OSPermissionChangedInternalObserver());
    }
    return V;
  }
  
  private static void h(String paramString)
  {
    if (b == null) {
      return;
    }
    dsm.a(dsm.a, "GT_APP_ID", paramString);
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
      dsh.a(new Runnable()
      {
        public void run() {}
      });
    }
  }
  
  private static dry j(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (Z == null)
    {
      Z = new dry(false);
      Z.a.b(new drx());
    }
    return Z;
  }
  
  static String j()
  {
    return l(b);
  }
  
  private static void k(Context paramContext)
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
  
  static boolean k()
  {
    return dsm.b(dsm.a, "ONESIGNAL_USER_PROVIDED_CONSENT", false);
  }
  
  public static String l()
  {
    if ((u == null) && (b != null)) {
      u = dsm.b(dsm.a, "GT_PLAYER_ID", null);
    }
    return u;
  }
  
  private static String l(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return dsm.b(dsm.a, "GT_APP_ID", null);
  }
  
  static String m()
  {
    if ("".equals(v)) {
      return null;
    }
    if ((v == null) && (b != null)) {
      v = dsm.b(dsm.a, "OS_EMAIL_ID", null);
    }
    return v;
  }
  
  private static String m(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return dsm.b(dsm.a, "GT_PLAYER_ID", null);
  }
  
  private static long n(Context paramContext)
  {
    return dsm.b(dsm.a, "OS_LAST_SESSION_TIME", -31000L);
  }
  
  static boolean n()
  {
    if (i == null) {
      return true;
    }
    return i.i == dsi.l.c;
  }
  
  static boolean o()
  {
    dsi.a localA = i;
    boolean bool = false;
    if (localA == null) {
      return false;
    }
    if (i.i == dsi.l.b) {
      bool = true;
    }
    return bool;
  }
  
  static long p()
  {
    if ((A == -1L) && (b != null)) {
      A = dsm.b(dsm.a, "GT_UNSENT_ACTIVE_TIME", 0L);
    }
    dsi.i localI = dsi.i.e;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("GetUnsentActiveTime: ");
    localStringBuilder.append(A);
    a(localI, localStringBuilder.toString());
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
  
  public static boolean s()
  {
    if (i.f) {
      return dsh.a(b);
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
      p.a(new dsi.d(dsi.c.d, "Failed due to network failure. Will retry on next sync."));
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
      o.a(new dsi.d(dsi.c.d, "Failed due to network failure. Will retry on next sync."));
      o = null;
    }
  }
  
  public static class a
  {
    Context a;
    dsi.j b;
    dsi.k c;
    boolean d;
    boolean e;
    boolean f;
    boolean g;
    boolean h;
    dsi.l i = dsi.l.b;
    
    private a() {}
    
    private a(Context paramContext)
    {
      this.a = paramContext;
    }
    
    public a a(dsi.l paramL)
    {
      dsi.c().h = false;
      this.i = paramL;
      return this;
    }
    
    public a a(boolean paramBoolean)
    {
      this.f = paramBoolean;
      return this;
    }
    
    public void a()
    {
      dsi.a(this);
    }
  }
  
  public static abstract interface b
  {
    public abstract void a(dsi.n paramN);
    
    public abstract void a(JSONObject paramJSONObject);
  }
  
  public static enum c
  {
    private c() {}
  }
  
  public static class d
  {
    private dsi.c a;
    private String b;
    
    d(dsi.c paramC, String paramString)
    {
      this.a = paramC;
      this.b = paramString;
    }
  }
  
  public static abstract interface e
  {
    public abstract void a();
    
    public abstract void a(dsi.d paramD);
  }
  
  public static abstract interface f
  {
    public abstract void a(JSONObject paramJSONObject);
  }
  
  static class g
  {
    JSONArray a;
    boolean b;
    dsn.a c;
    
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
    public abstract void a(dsb paramDsb);
  }
  
  public static abstract interface k
  {
    public abstract void a(drz paramDrz);
  }
  
  public static enum l
  {
    private l() {}
  }
  
  static class m
    implements Runnable
  {
    private Runnable a;
    private long b;
    
    m(Runnable paramRunnable)
    {
      this.a = paramRunnable;
    }
    
    public void run()
    {
      this.a.run();
      dsi.b(this.b);
    }
  }
  
  public static class n
  {
    private String a;
    private int b;
    
    n(int paramInt, String paramString)
    {
      this.a = paramString;
      this.b = paramInt;
    }
  }
}
