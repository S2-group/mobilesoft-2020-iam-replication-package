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

public class ai
{
  private static av A;
  private static aw B;
  private static d C = new c();
  private static int D = 0;
  private static ah E;
  private static String F;
  private static boolean G = false;
  private static boolean H = false;
  private static boolean I = false;
  private static boolean J = false;
  private static n.f K;
  private static Collection<JSONArray> L = new ArrayList();
  private static HashSet<String> M = new HashSet();
  private static e N;
  private static boolean O = false;
  private static boolean P = false;
  private static boolean Q = false;
  private static JSONObject R;
  private static boolean S = false;
  private static ad T;
  private static ac<Object, ae> U;
  private static OSSubscriptionState V;
  private static ac<Object, ag> W;
  private static x X;
  private static f Y;
  private static int Z = 0;
  static String a;
  static Context b;
  static boolean c = false;
  static ExecutorService d;
  public static ConcurrentLinkedQueue<Runnable> e;
  static AtomicLong f;
  public static String g = "native";
  static boolean h = true;
  static a i;
  static boolean j = false;
  static ad k;
  static OSSubscriptionState l;
  private static d m;
  private static d n;
  private static String o;
  private static boolean p = false;
  private static h q = h.a;
  private static h r = h.d;
  private static String s;
  private static String t;
  private static int u = 0;
  private static boolean v = false;
  private static g w;
  private static long x = 1L;
  private static long y = -1L;
  private static ax z;
  
  static
  {
    e = new ConcurrentLinkedQueue();
    f = new AtomicLong();
  }
  
  public ai() {}
  
  private static void N()
  {
    if (!e.isEmpty())
    {
      d = Executors.newSingleThreadExecutor(new ThreadFactory()
      {
        public final Thread newThread(Runnable paramAnonymousRunnable)
        {
          paramAnonymousRunnable = new Thread(paramAnonymousRunnable);
          StringBuilder localStringBuilder = new StringBuilder("OS_PENDING_EXECUTOR_");
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
  
  private static boolean O()
  {
    if ((c) && (d == null)) {
      return false;
    }
    if ((!c) && (d == null)) {
      return true;
    }
    return (d != null) && (!d.isShutdown());
  }
  
  private static void P()
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
    Q();
    S();
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
  
  private static void Q()
  {
    n.d local7 = new n.d()
    {
      public final n.a a()
      {
        return n.a.a;
      }
      
      public final void a(n.f paramAnonymousF)
      {
        ai.a(paramAnonymousF);
        ai.x();
        ai.y();
      }
    };
    boolean bool;
    if ((i.d) && (!J)) {
      bool = true;
    } else {
      bool = false;
    }
    n.a(b, bool, local7);
  }
  
  private static void R()
  {
    Object localObject;
    if (D == 2) {
      localObject = new ar();
    } else {
      localObject = new as();
    }
    ((aq)localObject).a(b, o, new aq.a()
    {
      public final void a(String paramAnonymousString, int paramAnonymousInt)
      {
        if (paramAnonymousInt <= 0)
        {
          if ((ao.g() == null) && ((ai.z() == 1) || (ai.z() < -6))) {
            ai.a(paramAnonymousInt);
          }
        }
        else if (ai.z() < -6) {
          ai.a(paramAnonymousInt);
        }
        ai.e(paramAnonymousString);
        ai.A();
        ai.b(ai.b).b(paramAnonymousString);
        ai.y();
      }
    });
  }
  
  private static void S()
  {
    if (I)
    {
      R();
      return;
    }
    an.a local9 = new an.a()
    {
      final void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        new Thread(new Runnable()
        {
          public final void run()
          {
            try
            {
              int j = ai.B() * 10000 + 30000;
              int i = j;
              if (j > 90000) {
                i = 90000;
              }
              ai.h localH = ai.h.e;
              StringBuilder localStringBuilder = new StringBuilder("Failed to get Android parameters, trying again in ");
              localStringBuilder.append(i / 1000);
              localStringBuilder.append(" seconds.");
              ai.a(localH, localStringBuilder.toString());
              Thread.sleep(i);
            }
            catch (Throwable localThrowable)
            {
              for (;;) {}
            }
            ai.C();
            ai.D();
          }
        }, "OS_PARAMS_REQUEST").start();
      }
      
      final void a(String paramAnonymousString)
      {
        try
        {
          paramAnonymousString = new JSONObject(paramAnonymousString);
          if (paramAnonymousString.has("android_sender_id"))
          {
            ai.E();
            ai.f(paramAnonymousString.getString("android_sender_id"));
          }
          ai.j = paramAnonymousString.optBoolean("enterp", false);
          ai.a(paramAnonymousString.optBoolean("use_email_auth", false));
          ai.b(paramAnonymousString.getJSONObject("awl_list"));
          boolean bool = paramAnonymousString.optBoolean("fba", false);
          am.a(am.a, "GT_FIREBASE_TRACKING_ENABLED", bool);
          p.a(ai.b, paramAnonymousString);
        }
        catch (Throwable paramAnonymousString)
        {
          paramAnonymousString.printStackTrace();
        }
        ai.F();
        ai.G();
      }
    };
    Object localObject = new StringBuilder("apps/");
    ((StringBuilder)localObject).append(a);
    ((StringBuilder)localObject).append("/android_params.js");
    String str1 = ((StringBuilder)localObject).toString();
    String str2 = h();
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
    an.a((String)localObject, local9);
  }
  
  private static void T()
  {
    Iterator localIterator = L.iterator();
    while (localIterator.hasNext()) {
      a((JSONArray)localIterator.next(), false);
    }
    L.clear();
  }
  
  private static boolean U()
  {
    boolean bool = ao.b();
    if (bool) {
      ap.a(b);
    }
    return (n.a(b)) || (bool);
  }
  
  private static int V()
  {
    TimeZone localTimeZone = Calendar.getInstance().getTimeZone();
    int i2 = localTimeZone.getRawOffset();
    int i1 = i2;
    if (localTimeZone.inDaylightTime(new Date())) {
      i1 = i2 + localTimeZone.getDSTSavings();
    }
    return i1 / 1000;
  }
  
  private static void W()
  {
    h localH = h.f;
    StringBuilder localStringBuilder = new StringBuilder("registerUser: registerForPushFired:");
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
        public final void run()
        {
          try
          {
            ai.I();
            aj.a(ai.b, ai.a, ai.J(), c.a());
            return;
          }
          catch (JSONException localJSONException)
          {
            ai.a(ai.h.b, "FATAL Error registering device!", localJSONException);
          }
        }
      }, "OS_REG_USER").start();
      return;
    }
  }
  
  private static void X()
    throws JSONException
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
    localJSONObject.put("timezone", V());
    localJSONObject.put("language", ah.d());
    localJSONObject.put("sdk", "030804");
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
        localJSONObject.put("net_type", ah.b());
        localJSONObject.put("carrier", ah.c());
        localJSONObject.put("rooted", au.a());
        ao.b(localJSONObject);
        localJSONObject = new JSONObject();
        localJSONObject.put("identifier", F);
        localJSONObject.put("subscribableStatus", u);
        localJSONObject.put("androidPermission", s());
        localJSONObject.put("device_type", D);
        ao.c(localJSONObject);
        if ((h) && (K != null)) {
          ao.a(K);
        }
        if (Q) {
          ao.j();
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
  
  private static void Y()
  {
    if (w != null) {
      ah.a(new Runnable()
      {
        public final void run() {}
      });
    }
  }
  
  private static void Z()
  {
    try
    {
      Object localObject1 = w;
      if (localObject1 == null) {
        return;
      }
      localObject1 = ao.g();
      if (!ao.f()) {
        localObject1 = null;
      }
      String str = h();
      if (str == null) {
        return;
      }
      if (localObject1 != null) {
        w = null;
      }
      return;
    }
    finally {}
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
    return new a(paramContext, (byte)0);
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
    try
    {
      JSONObject localJSONObject = new JSONObject().put("app_id", a).put("type", 1).put("state", "ping").put("active_time", paramLong);
      c(localJSONObject);
      a(h(), localJSONObject);
      String str = i();
      if (str != null) {
        a(str, localJSONObject);
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      a(h.c, "Generating on_focus:JSON Failed.", localThrowable);
    }
  }
  
  private static void a(Context paramContext, String paramString1, String paramString2, i paramI, j paramJ)
  {
    a localA = c();
    i = localA;
    localA.h = false;
    i.b = paramI;
    i.c = paramJ;
    if (!p) {
      o = paramString1;
    }
    E = new ah();
    D = ah.a();
    int i1 = ah.a(paramContext, D, paramString2);
    u = i1;
    if (i1 == 64537) {
      return;
    }
    if (c)
    {
      if (paramContext != null) {
        b = paramContext.getApplicationContext();
      }
      if (i.b != null) {
        T();
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
    }
    else
    {
      a.a = true;
    }
    x = SystemClock.elapsedRealtime();
    ao.c();
    ((Application)b).registerActivityLifecycleCallbacks(new b());
    try
    {
      Class.forName("com.amazon.device.iap.PurchasingListener");
      A = new av(b);
      paramContext = g();
      if (paramContext != null)
      {
        if (!paramContext.equals(a))
        {
          a(h.f, "APP ID changed, clearing user id as it is no longer valid.");
          g(a);
          ao.h();
        }
      }
      else
      {
        f.a(0, b);
        g(a);
      }
      OSPermissionChangedInternalObserver.a(c(b));
      if ((v) || (h() == null))
      {
        Q = ac();
        b(System.currentTimeMillis());
        P();
      }
      if (i.b != null) {
        T();
      }
      if (ax.a(b)) {
        z = new ax(b);
      }
      if (aw.a()) {
        B = new aw(b);
      }
      c = true;
      N();
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
    if ((B != null) && (aa())) {
      B.a(b(paramJSONArray, true, paramBoolean));
    }
    boolean bool1 = false;
    boolean bool2 = "DISABLE".equals(ah.a(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
    if (!bool2) {
      bool1 = a(paramContext, paramJSONArray);
    }
    a(paramJSONArray, paramBoolean);
    if ((!paramBoolean) && (!bool1) && (!bool2)) {
      f(paramContext);
    }
  }
  
  private static void a(aa paramAa)
  {
    ah.a(new Runnable()
    {
      public final void run()
      {
        ai.i.b.a(this.a);
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
      public final void run()
      {
        bd.a localA = ao.b(ai.K() ^ true);
        if (localA.a) {
          ai.L();
        }
        if (localA.b != null) {
          localA.toString().equals("{}");
        }
      }
    }, "OS_GETTAGS_CALLBACK").start();
  }
  
  static void a(h paramH, String paramString)
  {
    a(paramH, paramString, null);
  }
  
  static void a(h paramH, final String paramString, Throwable paramThrowable)
  {
    if (paramH.compareTo(r) <= 0) {
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
    if ((paramH.compareTo(q) <= 0) && (a.b != null)) {
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
        ah.a(new Runnable()
        {
          public final void run()
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
      localStringBuilder = new StringBuilder("Adding a task to the pending queue with ID: ");
      localStringBuilder.append(l.a(paramL));
      a(localH, localStringBuilder.toString());
      e.add(paramL);
      return;
    }
    if (!d.isShutdown())
    {
      localH = h.e;
      localStringBuilder = new StringBuilder("Executor is still running, add to the executor with ID: ");
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
    am.a(am.a, "GT_PLAYER_ID", s);
  }
  
  private static void a(String paramString, JSONObject paramJSONObject)
  {
    StringBuilder localStringBuilder = new StringBuilder("players/");
    localStringBuilder.append(paramString);
    localStringBuilder.append("/on_focus");
    an.d(localStringBuilder.toString(), paramJSONObject, new an.a()
    {
      final void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        ai.a("sending on_focus Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
      }
      
      final void a(String paramAnonymousString) {}
    });
  }
  
  public static void a(Collection<String> paramCollection)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject();
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext()) {
        localJSONObject.put((String)paramCollection.next(), "");
      }
      a(localJSONObject);
      return;
    }
    catch (Throwable paramCollection)
    {
      a(h.c, "Failed to generate JSON for deleteTags.", paramCollection);
    }
  }
  
  private static void a(JSONArray paramJSONArray, boolean paramBoolean)
  {
    if ((i != null) && (i.b != null))
    {
      a(b(paramJSONArray, true, paramBoolean));
      return;
    }
    L.add(paramJSONArray);
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean, an.a paramA)
  {
    if (h() == null)
    {
      paramJSONArray = new f(paramJSONArray);
      Y = paramJSONArray;
      paramJSONArray.b = paramBoolean;
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
      paramJSONArray = new StringBuilder("players/");
      paramJSONArray.append(h());
      paramJSONArray.append("/on_purchase");
      an.b(paramJSONArray.toString(), localJSONObject, paramA);
      if (i() != null)
      {
        paramJSONArray = new StringBuilder("players/");
        paramJSONArray.append(i());
        paramJSONArray.append("/on_purchase");
        an.b(paramJSONArray.toString(), localJSONObject, null);
      }
      return;
    }
    catch (Throwable paramJSONArray)
    {
      a(h.c, "Failed to generate JSON for sendPurchases.", paramJSONArray);
    }
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramJSONArray = b(paramJSONArray, paramBoolean1, paramBoolean2);
    if ((B != null) && (aa())) {
      B.b(paramJSONArray);
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
    paramJSONObject = new Runnable()
    {
      public final void run()
      {
        if (this.a == null) {
          return;
        }
        JSONObject localJSONObject1 = ao.b(false).b;
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
            localObject = ai.h.c;
            StringBuilder localStringBuilder = new StringBuilder("Omitting key '");
            localStringBuilder.append(str);
            localStringBuilder.append("'! sendTags DO NOT supported nested values!");
            ai.a((ai.h)localObject, localStringBuilder.toString());
          }
          catch (Throwable localThrowable) {}
          if (!localJSONObject2.toString().equals("{}")) {
            ao.a(localJSONObject2);
          }
          return;
        }
      }
    };
    if ((b != null) && (!O()))
    {
      paramJSONObject.run();
      return;
    }
    a(h.c, "You must initialize OneSignal before modifying tags!Moving this operation to a pending task queue.");
    a(new l(paramJSONObject));
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
              localObject1 = new StringBuilder("http://");
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
        StringBuilder localStringBuilder = new StringBuilder("Error parsing JSON item ");
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
    paramJSONObject = d(paramJSONObject);
    return (paramJSONObject == null) || (a(paramJSONObject, paramContext));
  }
  
  private static boolean a(h paramH)
  {
    return (paramH.compareTo(q) <= 0) || (paramH.compareTo(r) <= 0);
  }
  
  /* Error */
  private static boolean a(String paramString, Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +192 -> 193
    //   4: ldc_w 902
    //   7: aload_0
    //   8: invokevirtual 733	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   11: ifeq +5 -> 16
    //   14: iconst_0
    //   15: ireturn
    //   16: aload_1
    //   17: invokestatic 1019	com/onesignal/ak:a	(Landroid/content/Context;)Lcom/onesignal/ak;
    //   20: astore 4
    //   22: aconst_null
    //   23: astore 6
    //   25: aconst_null
    //   26: astore_1
    //   27: aload 4
    //   29: invokevirtual 1022	com/onesignal/ak:b	()Landroid/database/sqlite/SQLiteDatabase;
    //   32: ldc_w 1024
    //   35: iconst_1
    //   36: anewarray 498	java/lang/String
    //   39: dup
    //   40: iconst_0
    //   41: ldc_w 1026
    //   44: aastore
    //   45: ldc_w 1028
    //   48: iconst_1
    //   49: anewarray 498	java/lang/String
    //   52: dup
    //   53: iconst_0
    //   54: aload_0
    //   55: aastore
    //   56: aconst_null
    //   57: aconst_null
    //   58: aconst_null
    //   59: invokevirtual 1034	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   62: astore 4
    //   64: aload 4
    //   66: invokeinterface 1039 1 0
    //   71: istore_3
    //   72: iload_3
    //   73: istore_2
    //   74: aload 4
    //   76: ifnull +65 -> 141
    //   79: aload 4
    //   81: invokeinterface 1042 1 0
    //   86: iload_3
    //   87: istore_2
    //   88: goto +53 -> 141
    //   91: astore_0
    //   92: aload 4
    //   94: astore_1
    //   95: goto +86 -> 181
    //   98: astore 5
    //   100: goto +13 -> 113
    //   103: astore_0
    //   104: goto +77 -> 181
    //   107: astore 5
    //   109: aload 6
    //   111: astore 4
    //   113: aload 4
    //   115: astore_1
    //   116: getstatic 647	com/onesignal/ai$h:c	Lcom/onesignal/ai$h;
    //   119: ldc_w 1044
    //   122: aload 5
    //   124: invokestatic 628	com/onesignal/ai:a	(Lcom/onesignal/ai$h;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   127: aload 4
    //   129: ifnull +10 -> 139
    //   132: aload 4
    //   134: invokeinterface 1042 1 0
    //   139: iconst_0
    //   140: istore_2
    //   141: iload_2
    //   142: ifeq +37 -> 179
    //   145: getstatic 304	com/onesignal/ai$h:f	Lcom/onesignal/ai$h;
    //   148: astore_1
    //   149: new 281	java/lang/StringBuilder
    //   152: dup
    //   153: ldc_w 1046
    //   156: invokespecial 286	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   159: astore 4
    //   161: aload 4
    //   163: aload_0
    //   164: invokevirtual 292	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   167: pop
    //   168: aload_1
    //   169: aload 4
    //   171: invokevirtual 297	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   174: invokestatic 309	com/onesignal/ai:a	(Lcom/onesignal/ai$h;Ljava/lang/String;)V
    //   177: iconst_1
    //   178: ireturn
    //   179: iconst_0
    //   180: ireturn
    //   181: aload_1
    //   182: ifnull +9 -> 191
    //   185: aload_1
    //   186: invokeinterface 1042 1 0
    //   191: aload_0
    //   192: athrow
    //   193: iconst_0
    //   194: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	195	0	paramString	String
    //   0	195	1	paramContext	Context
    //   73	69	2	bool1	boolean
    //   71	16	3	bool2	boolean
    //   20	150	4	localObject1	Object
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
  
  private static boolean aa()
  {
    return am.b(am.a, "GT_FIREBASE_TRACKING_ENABLED", false);
  }
  
  private static long ab()
  {
    return am.b(am.a, "OS_LAST_SESSION_TIME", -31000L);
  }
  
  private static boolean ac()
  {
    if (Q) {
      return true;
    }
    return (System.currentTimeMillis() - ab()) / 1000L >= 30L;
  }
  
  private static aa b(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i3 = paramJSONArray.length();
    aa localAa = new aa();
    y localY = new y();
    localY.a = q();
    localY.b = paramBoolean1;
    localY.c = paramJSONArray.optJSONObject(0).optInt("notificationId");
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
        localY.d = o.a((JSONObject)localObject5);
        localObject3 = localObject1;
        if (localObject1 != null) {
          break label357;
        }
        localObject4 = localObject1;
        localObject3 = localObject1;
        if (!((JSONObject)localObject5).has("actionSelected")) {
          break label357;
        }
        localObject4 = localObject1;
        localObject3 = ((JSONObject)localObject5).optString("actionSelected", null);
      }
      catch (Throwable localThrowable)
      {
        label139:
        localObject3 = h.c;
        Object localObject5 = new StringBuilder("Error parsing JSON item ");
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
      localAa.b.b = localObject2;
      paramJSONArray = localAa.b;
      if (localObject2 != null) {
        i1 = z.a.b;
      } else {
        i1 = z.a.a;
      }
      paramJSONArray.a = i1;
      if (paramBoolean2)
      {
        localAa.a.e = y.a.b;
        return localAa;
      }
      localAa.a.e = y.a.a;
      return localAa;
      label357:
      if (i2 == 0) {
        break label139;
      }
      i2 = 0;
      Object localObject2 = localObject3;
    }
  }
  
  static ac<Object, ag> b()
  {
    if (W == null) {
      W = new ac("onOSSubscriptionChanged", true);
    }
    return W;
  }
  
  static void b(long paramLong)
  {
    am.a(am.a, "OS_LAST_SESSION_TIME", paramLong);
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
          localJSONObject.put("app_id", g(paramContext));
          localJSONObject.put("player_id", h(paramContext));
          localJSONObject.put("opened", true);
          StringBuilder localStringBuilder = new StringBuilder("notifications/");
          localStringBuilder.append(str);
          an.a(localStringBuilder.toString(), localJSONObject, new an.a()
          {
            final void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
            {
              ai.a("sending Notification Opened Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
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
    Context localContext = paramA.a;
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
    String str = am.a;
    if ("".equals(t)) {
      paramString = null;
    } else {
      paramString = t;
    }
    am.a(str, "OS_EMAIL_ID", paramString);
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
        localObject1 = new StringBuilder("\n");
        ((StringBuilder)localObject1).append(paramString2);
        ((StringBuilder)localObject1).append("\n");
        localObject1 = ((StringBuilder)localObject1).toString();
      }
    }
    paramString2 = h.d;
    localObject2 = new StringBuilder("HTTP code: ");
    ((StringBuilder)localObject2).append(paramInt);
    ((StringBuilder)localObject2).append(" ");
    ((StringBuilder)localObject2).append(paramString1);
    ((StringBuilder)localObject2).append((String)localObject1);
    a(paramString2, ((StringBuilder)localObject2).toString(), paramThrowable);
  }
  
  private static void b(boolean paramBoolean)
  {
    if (b == null) {
      return;
    }
    am.a(am.a, "OS_FILTER_OTHER_GCM_RECEIVERS", paramBoolean);
  }
  
  private static ad c(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (T == null)
    {
      paramContext = new ad();
      T = paramContext;
      paramContext.a.b(new OSPermissionChangedInternalObserver());
    }
    return T;
  }
  
  public static a c()
  {
    if (i == null) {
      i = new a((byte)0);
    }
    return i;
  }
  
  static void c(String paramString)
  {
    a(paramString);
    Y();
    a(N);
    d(b).a(paramString);
    if (Y != null)
    {
      a(Y.a, Y.b, Y.c);
      Y = null;
    }
    ao.i();
    aj.a(b, a, paramString, c.a());
  }
  
  private static void c(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject.put("net_type", ah.b());
      return;
    }
    catch (Throwable paramJSONObject) {}
  }
  
  private static OSSubscriptionState d(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (V == null)
    {
      V = new OSSubscriptionState(c(paramContext).b());
      c(paramContext).a.a(V);
      V.a.b(new OSSubscriptionChangedInternalObserver());
    }
    return V;
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
      a(h.e, "Last Pending Task has ran, shutting down");
      d.shutdown();
    }
  }
  
  static void d(String paramString)
  {
    b(paramString);
    e(b).a(paramString);
    try
    {
      ao.c(new JSONObject().put("parent_player_id", paramString));
      return;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  static boolean d()
  {
    boolean bool2 = false;
    v = false;
    n.c();
    if (!c) {
      return false;
    }
    if (A != null) {
      A.a();
    }
    if (x == -1L) {
      return false;
    }
    long l1 = ((SystemClock.elapsedRealtime() - x) / 1000.0D + 0.5D);
    x = SystemClock.elapsedRealtime();
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
      boolean bool3 = U();
      b(System.currentTimeMillis());
      l1 = p() + l1;
      e(l1);
      boolean bool1 = l1 < 60L;
      if ((!bool1) && (h() != null))
      {
        if (!bool3) {
          ap.a(b);
        }
        ap.a();
        return false;
      }
      if (!bool1) {
        bool2 = true;
      }
      return bool2;
    }
    return false;
  }
  
  private static x e(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (X == null)
    {
      paramContext = new x();
      X = paramContext;
      paramContext.a.b(new w());
    }
    return X;
  }
  
  static void e()
  {
    v = true;
    n.c();
    x = SystemClock.elapsedRealtime();
    Q = ac();
    b(System.currentTimeMillis());
    P();
    if (z != null) {
      z.a();
    }
    u.a(b);
    c(b).a();
    if ((B != null) && (aa())) {
      B.b();
    }
    ap.b(b);
  }
  
  private static void e(long paramLong)
  {
    y = paramLong;
    if (b == null) {
      return;
    }
    h localH = h.e;
    StringBuilder localStringBuilder = new StringBuilder("SaveUnsentActiveTime: ");
    localStringBuilder.append(y);
    a(localH, localStringBuilder.toString());
    am.a(am.a, "GT_UNSENT_ACTIVE_TIME", paramLong);
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
    return v;
  }
  
  static String g()
  {
    return g(b);
  }
  
  private static String g(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return am.b(am.a, "GT_APP_ID", null);
  }
  
  private static void g(String paramString)
  {
    if (b == null) {
      return;
    }
    am.a(am.a, "GT_APP_ID", paramString);
  }
  
  static String h()
  {
    if ((s == null) && (b != null)) {
      s = am.b(am.a, "GT_PLAYER_ID", null);
    }
    return s;
  }
  
  private static String h(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return am.b(am.a, "GT_PLAYER_ID", null);
  }
  
  static String i()
  {
    if ("".equals(t)) {
      return null;
    }
    if ((t == null) && (b != null)) {
      t = am.b(am.a, "OS_EMAIL_ID", null);
    }
    return t;
  }
  
  static boolean j()
  {
    return am.b(am.a, "OS_FILTER_OTHER_GCM_RECEIVERS", false);
  }
  
  static boolean k()
  {
    return am.b(am.a, "GT_VIBRATE_ENABLED", true);
  }
  
  static boolean l()
  {
    return am.b(am.a, "GT_SOUND_ENABLED", true);
  }
  
  static boolean m()
  {
    if (i == null) {
      return true;
    }
    return i.i == k.c;
  }
  
  static boolean n()
  {
    if (i == null) {
      return false;
    }
    return i.i == k.b;
  }
  
  public static af o()
  {
    if (b == null)
    {
      a(h.c, "OneSignal.init has not been called. Could not get OSPermissionSubscriptionState");
      return null;
    }
    af localAf = new af();
    localAf.a = d(b);
    localAf.b = c(b);
    localAf.c = e(b);
    return localAf;
  }
  
  static long p()
  {
    if ((y == -1L) && (b != null)) {
      y = am.b(am.a, "GT_UNSENT_ACTIVE_TIME", 0L);
    }
    h localH = h.e;
    StringBuilder localStringBuilder = new StringBuilder("GetUnsentActiveTime: ");
    localStringBuilder.append(y);
    a(localH, localStringBuilder.toString());
    return y;
  }
  
  static boolean q()
  {
    return (c) && (f());
  }
  
  static void r()
  {
    Q = false;
    b(System.currentTimeMillis());
  }
  
  static boolean s()
  {
    if (i.f) {
      return ah.e();
    }
    return true;
  }
  
  static void t()
  {
    if (n != null) {
      n = null;
    }
  }
  
  static void u()
  {
    if (n != null)
    {
      new c(b.d, "Failed due to network failure. Will retry on next sync.");
      n = null;
    }
  }
  
  static void v()
  {
    if (m != null) {
      m = null;
    }
  }
  
  static void w()
  {
    if (m != null)
    {
      new c(b.d, "Failed due to network failure. Will retry on next sync.");
      m = null;
    }
  }
  
  public static final class a
  {
    Context a;
    ai.i b;
    ai.j c;
    boolean d;
    boolean e;
    boolean f;
    boolean g;
    boolean h;
    int i = ai.k.b;
    
    private a() {}
    
    private a(Context paramContext)
    {
      this.a = paramContext;
    }
    
    public final a a()
    {
      this.f = true;
      return this;
    }
    
    public final a a(int paramInt)
    {
      ai.c().h = false;
      this.i = paramInt;
      return this;
    }
    
    public final a a(ai.i paramI)
    {
      this.b = paramI;
      return this;
    }
    
    public final a a(ai.j paramJ)
    {
      this.c = paramJ;
      return this;
    }
    
    public final void b()
    {
      ai.a(this);
    }
  }
  
  public static enum b
  {
    public static final int a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
  }
  
  public static final class c
  {
    private int a;
    private String b;
    
    c(int paramInt, String paramString)
    {
      this.a = paramInt;
      this.b = paramString;
    }
  }
  
  public static abstract interface d {}
  
  public static abstract interface e {}
  
  private static final class f
  {
    JSONArray a;
    boolean b;
    an.a c;
    
    f(JSONArray paramJSONArray)
    {
      this.a = paramJSONArray;
    }
  }
  
  public static abstract interface g {}
  
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
    public static final int a = 1;
    public static final int b = 2;
    public static final int c = 3;
  }
  
  private static final class l
    implements Runnable
  {
    private Runnable a;
    private long b;
    
    l(Runnable paramRunnable)
    {
      this.a = paramRunnable;
    }
    
    public final void run()
    {
      this.a.run();
      ai.c(this.b);
    }
  }
}
