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

public class OneSignal
{
  private static long A = -1L;
  private static aw B;
  private static au C;
  private static av D;
  private static d E = new c();
  private static int F = 0;
  private static af G;
  private static String H;
  private static boolean I = false;
  private static boolean J = false;
  private static boolean K = false;
  private static boolean L = false;
  private static LocationGMS.e M;
  private static Collection<JSONArray> N = new ArrayList();
  private static HashSet<String> O = new HashSet();
  private static e P;
  private static boolean Q = false;
  private static boolean R = false;
  private static boolean S = false;
  private static JSONObject T;
  private static boolean U = false;
  private static ac V;
  private static ab<Object, ad> W;
  private static OSSubscriptionState X;
  private static ab<Object, ae> Y;
  private static y Z;
  static String a;
  private static f aa;
  private static an ab;
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
  static ac m;
  static OSSubscriptionState n;
  private static d o;
  private static d p;
  private static String q;
  private static boolean r = false;
  private static LOG_LEVEL s = LOG_LEVEL.a;
  private static LOG_LEVEL t = LOG_LEVEL.d;
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
  
  public OneSignal() {}
  
  private static void H()
  {
    if (!e.isEmpty())
    {
      d = Executors.newSingleThreadExecutor(new ThreadFactory()
      {
        public Thread newThread(@NonNull Runnable paramAnonymousRunnable)
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
  
  private static void I()
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
    J();
    M();
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
  
  private static void J()
  {
    LocationGMS.c local6 = new LocationGMS.c()
    {
      public LocationGMS.CALLBACK_TYPE a()
      {
        return LocationGMS.CALLBACK_TYPE.a;
      }
      
      public void a(LocationGMS.e paramAnonymousE)
      {
        OneSignal.a(paramAnonymousE);
        OneSignal.c(true);
        OneSignal.x();
      }
    };
    boolean bool;
    if ((i.d) && (!L)) {
      bool = true;
    } else {
      bool = false;
    }
    LocationGMS.a(b, bool, local6);
  }
  
  private static an K()
  {
    if (ab != null) {
      return ab;
    }
    Object localObject;
    if (F == 2) {
      localObject = new ao();
    }
    for (;;)
    {
      ab = (an)localObject;
      break;
      if (af.a()) {
        localObject = new aq();
      } else {
        localObject = new ar();
      }
    }
    return ab;
  }
  
  private static void L()
  {
    K().a(b, q, new an.a()
    {
      public void a(String paramAnonymousString, int paramAnonymousInt)
      {
        if (paramAnonymousInt < 1 ? (al.g() == null) && ((OneSignal.y() == 1) || (OneSignal.a(OneSignal.y()))) : OneSignal.a(OneSignal.y())) {
          OneSignal.b(paramAnonymousInt);
        }
        OneSignal.f(paramAnonymousString);
        OneSignal.d(true);
        OneSignal.g(OneSignal.b).b(paramAnonymousString);
        OneSignal.x();
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
    ak.a local8 = new ak.a()
    {
      void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        new Thread(new Runnable()
        {
          public void run()
          {
            try
            {
              int j = OneSignal.z() * 10000 + 30000;
              int i = j;
              if (j > 90000) {
                i = 90000;
              }
              OneSignal.LOG_LEVEL localLOG_LEVEL = OneSignal.LOG_LEVEL.e;
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("Failed to get Android parameters, trying again in ");
              localStringBuilder.append(i / 1000);
              localStringBuilder.append(" seconds.");
              OneSignal.a(localLOG_LEVEL, localStringBuilder.toString());
              Thread.sleep(i);
            }
            catch (Throwable localThrowable)
            {
              for (;;) {}
            }
            OneSignal.A();
            OneSignal.B();
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
            OneSignal.g(paramAnonymousString.getString("android_sender_id"));
          }
          OneSignal.j = paramAnonymousString.optBoolean("enterp", false);
          OneSignal.f(paramAnonymousString.optBoolean("use_email_auth", false));
          OneSignal.a(paramAnonymousString.getJSONObject("awl_list"));
          boolean bool = paramAnonymousString.optBoolean("fba", false);
          aj.a(aj.a, "GT_FIREBASE_TRACKING_ENABLED", bool);
          q.a(OneSignal.b, paramAnonymousString);
        }
        catch (Throwable paramAnonymousString)
        {
          paramAnonymousString.printStackTrace();
        }
        OneSignal.g(true);
        OneSignal.C();
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
    a(LOG_LEVEL.f, "Starting request to get Android parameters.");
    ak.a((String)localObject, local8);
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
    LOG_LEVEL localLOG_LEVEL = LOG_LEVEL.f;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("registerUser: registerForPushFired:");
    localStringBuilder.append(I);
    localStringBuilder.append(", locationFired: ");
    localStringBuilder.append(J);
    localStringBuilder.append(", awlFired: ");
    localStringBuilder.append(K);
    a(localLOG_LEVEL, localStringBuilder.toString());
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
            OneSignal.D();
            ag.a(OneSignal.b, OneSignal.a, OneSignal.E(), c.a());
            return;
          }
          catch (JSONException localJSONException)
          {
            OneSignal.a(OneSignal.LOG_LEVEL.b, "FATAL Error registering device!", localJSONException);
          }
        }
      }, "OS_REG_USER").start();
    }
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
    localJSONObject.put("language", af.f());
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
        localJSONObject.put("rooted", at.a());
        al.a(localJSONObject);
        localJSONObject = new JSONObject();
        localJSONObject.put("identifier", H);
        localJSONObject.put("subscribableStatus", w);
        localJSONObject.put("androidPermission", s());
        localJSONObject.put("device_type", F);
        al.b(localJSONObject);
        if ((h) && (M != null)) {
          al.a(M);
        }
        if (S) {
          al.j();
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
  
  private static void R()
  {
    try
    {
      Object localObject1 = y;
      if (localObject1 == null) {
        return;
      }
      localObject1 = al.g();
      if (!al.f()) {
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
  
  private static boolean S()
  {
    if (S) {
      return true;
    }
    return (System.currentTimeMillis() - o(b)) / 1000L >= 30L;
  }
  
  static ab<Object, ad> a()
  {
    if (W == null) {
      W = new ab("onOSPermissionChanged", true);
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
      LOG_LEVEL localLOG_LEVEL;
      if (paramBundle.containsKey("custom"))
      {
        paramBundle = new JSONObject(paramBundle.getString("custom"));
        if (paramBundle.has("i")) {
          return paramBundle.optString("i", null);
        }
        localLOG_LEVEL = LOG_LEVEL.f;
      }
      for (paramBundle = "Not a OneSignal formatted GCM message. No 'i' field in custom.";; paramBundle = "Not a OneSignal formatted GCM message. No 'custom' field in the bundle.")
      {
        a(localLOG_LEVEL, paramBundle);
        return null;
        localLOG_LEVEL = LOG_LEVEL.f;
      }
      return null;
    }
    catch (Throwable paramBundle)
    {
      a(LOG_LEVEL.f, "Could not parse bundle, probably not a OneSignal notification.", paramBundle);
    }
  }
  
  static void a(long paramLong)
  {
    aj.a(aj.a, "OS_LAST_SESSION_TIME", paramLong);
  }
  
  static void a(long paramLong, boolean paramBoolean)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject().put("app_id", a).put("type", 1).put("state", "ping").put("active_time", paramLong);
      b(localJSONObject);
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
      a(LOG_LEVEL.c, "Generating on_focus:JSON Failed.", localThrowable);
    }
  }
  
  static void a(Context paramContext)
  {
    b = paramContext.getApplicationContext();
    aj.b();
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, h paramH, i paramI)
  {
    a(paramContext);
    if ((k) && (!d()))
    {
      a(LOG_LEVEL.g, "OneSignal SDK initialization delayed, user privacy consent is set to required for this application.");
      l = new k(paramContext, paramString1, paramString2, paramH, paramI);
      return;
    }
    i = c();
    i.h = false;
    i.b = paramH;
    i.c = paramI;
    if (!r) {
      q = paramString1;
    }
    G = new af();
    F = G.c();
    w = G.a(paramContext, F, paramString2);
    if (w == 64537) {
      return;
    }
    if (c)
    {
      if (i.b != null) {
        N();
      }
      return;
    }
    boolean bool = paramContext instanceof Activity;
    x = bool;
    a = paramString2;
    b(i.g);
    if (bool)
    {
      a.b = (Activity)paramContext;
      v.a(b);
    }
    else
    {
      a.a = true;
    }
    z = SystemClock.elapsedRealtime();
    al.d();
    ((Application)b).registerActivityLifecycleCallbacks(new b());
    try
    {
      Class.forName("com.amazon.device.iap.PurchasingListener");
      C = new au(b);
      paramContext = j();
      if (paramContext != null)
      {
        if (!paramContext.equals(a))
        {
          a(LOG_LEVEL.f, "APP ID changed, clearing user id as it is no longer valid.");
          h(a);
          al.h();
        }
      }
      else
      {
        f.a(0, b);
        h(a);
      }
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
      if (aw.a(b)) {
        B = new aw(b);
      }
      if (av.a()) {
        D = new av(b);
      }
      aq.a(b);
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
    boolean bool2 = "DISABLE".equals(af.a(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
    if (!bool2) {
      bool1 = a(paramContext, paramJSONArray);
    }
    b(paramJSONArray, true, paramBoolean);
    if ((!paramBoolean) && (!bool1) && (!bool2)) {
      k(paramContext);
    }
  }
  
  static void a(LOG_LEVEL paramLOG_LEVEL, String paramString)
  {
    a(paramLOG_LEVEL, paramString, null);
  }
  
  static void a(LOG_LEVEL paramLOG_LEVEL, final String paramString, Throwable paramThrowable)
  {
    if (paramLOG_LEVEL.compareTo(t) < 1) {
      if (paramLOG_LEVEL == LOG_LEVEL.g) {
        Log.v("OneSignal", paramString, paramThrowable);
      } else if (paramLOG_LEVEL == LOG_LEVEL.f) {
        Log.d("OneSignal", paramString, paramThrowable);
      } else if (paramLOG_LEVEL == LOG_LEVEL.e) {
        Log.i("OneSignal", paramString, paramThrowable);
      } else if (paramLOG_LEVEL == LOG_LEVEL.d) {
        Log.w("OneSignal", paramString, paramThrowable);
      } else if ((paramLOG_LEVEL == LOG_LEVEL.c) || (paramLOG_LEVEL == LOG_LEVEL.b)) {
        Log.e("OneSignal", paramString, paramThrowable);
      }
    }
    if ((paramLOG_LEVEL.compareTo(s) < 1) && (a.b != null)) {
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
        af.a(new Runnable()
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
        Log.e("OneSignal", "Error showing logging message.", paramLOG_LEVEL);
      }
    }
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
        bc.a localA = al.c(OneSignal.F() ^ true);
        if (localA.a) {
          OneSignal.h(true);
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
  
  private static void a(z paramZ)
  {
    af.a(new Runnable()
    {
      public void run()
      {
        OneSignal.i.b.a(this.a);
      }
    });
  }
  
  private static void a(String paramString, JSONObject paramJSONObject, boolean paramBoolean)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("players/");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append("/on_focus");
    paramString = ((StringBuilder)localObject).toString();
    localObject = new ak.a()
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
      ak.d(paramString, paramJSONObject, (ak.a)localObject);
      return;
    }
    ak.b(paramString, paramJSONObject, (ak.a)localObject);
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean, ak.a paramA)
  {
    if (a("sendPurchases()")) {
      return;
    }
    if (l() == null)
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
      paramJSONArray.append(l());
      paramJSONArray.append("/on_purchase");
      ak.b(paramJSONArray.toString(), localJSONObject, paramA);
      if (m() != null)
      {
        paramJSONArray = new StringBuilder();
        paramJSONArray.append("players/");
        paramJSONArray.append(m());
        paramJSONArray.append("/on_purchase");
        ak.b(paramJSONArray.toString(), localJSONObject, null);
        return;
      }
    }
    catch (Throwable paramJSONArray)
    {
      a(LOG_LEVEL.c, "Failed to generate JSON for sendPurchases.", paramJSONArray);
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
    }
  }
  
  public static void a(boolean paramBoolean)
  {
    if ((k) && (!paramBoolean))
    {
      a(LOG_LEVEL.c, "Cannot change requiresUserPrivacyConsent() from TRUE to FALSE");
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
        Object localObject2 = LOG_LEVEL.c;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Error parsing JSON item ");
        localStringBuilder.append(i1);
        localStringBuilder.append("/");
        localStringBuilder.append(i2);
        localStringBuilder.append(" for launching a web URL.");
        a((LOG_LEVEL)localObject2, localStringBuilder.toString(), localThrowable);
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
  
  private static boolean a(LOG_LEVEL paramLOG_LEVEL)
  {
    int i1 = paramLOG_LEVEL.compareTo(s);
    boolean bool = true;
    if (i1 >= 1)
    {
      if (paramLOG_LEVEL.compareTo(t) < 1) {
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
        LOG_LEVEL localLOG_LEVEL = LOG_LEVEL.d;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Method ");
        localStringBuilder.append(paramString);
        localStringBuilder.append(" was called before the user provided privacy consent. Your application is set to require the user's privacy consent before the OneSignal SDK can be initialized. Please ensure the user has provided consent before calling this method. You can check the latest OneSignal consent status by calling OneSignal.userProvidedPrivacyConsent()");
        a(localLOG_LEVEL, localStringBuilder.toString());
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
    //   4: ldc_w 1011
    //   7: aload_0
    //   8: invokevirtual 770	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   11: ifeq +5 -> 16
    //   14: iconst_0
    //   15: ireturn
    //   16: aload_1
    //   17: invokestatic 1016	com/onesignal/ah:a	(Landroid/content/Context;)Lcom/onesignal/ah;
    //   20: astore 4
    //   22: aconst_null
    //   23: astore 6
    //   25: aconst_null
    //   26: astore_1
    //   27: aload 4
    //   29: invokevirtual 1019	com/onesignal/ah:b	()Landroid/database/sqlite/SQLiteDatabase;
    //   32: ldc_w 1021
    //   35: iconst_1
    //   36: anewarray 491	java/lang/String
    //   39: dup
    //   40: iconst_0
    //   41: ldc_w 1023
    //   44: aastore
    //   45: ldc_w 1025
    //   48: iconst_1
    //   49: anewarray 491	java/lang/String
    //   52: dup
    //   53: iconst_0
    //   54: aload_0
    //   55: aastore
    //   56: aconst_null
    //   57: aconst_null
    //   58: aconst_null
    //   59: invokevirtual 1031	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   62: astore 4
    //   64: aload 4
    //   66: invokeinterface 1036 1 0
    //   71: istore_3
    //   72: iload_3
    //   73: istore_2
    //   74: aload 4
    //   76: ifnull +65 -> 141
    //   79: aload 4
    //   81: invokeinterface 1039 1 0
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
    //   116: getstatic 663	com/onesignal/OneSignal$LOG_LEVEL:c	Lcom/onesignal/OneSignal$LOG_LEVEL;
    //   119: ldc_w 1041
    //   122: aload 5
    //   124: invokestatic 633	com/onesignal/OneSignal:a	(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   127: aload 4
    //   129: ifnull +10 -> 139
    //   132: aload 4
    //   134: invokeinterface 1039 1 0
    //   139: iconst_0
    //   140: istore_2
    //   141: iload_2
    //   142: ifeq +43 -> 185
    //   145: getstatic 311	com/onesignal/OneSignal$LOG_LEVEL:f	Lcom/onesignal/OneSignal$LOG_LEVEL;
    //   148: astore_1
    //   149: new 291	java/lang/StringBuilder
    //   152: dup
    //   153: invokespecial 292	java/lang/StringBuilder:<init>	()V
    //   156: astore 4
    //   158: aload 4
    //   160: ldc_w 1043
    //   163: invokevirtual 298	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: pop
    //   167: aload 4
    //   169: aload_0
    //   170: invokevirtual 298	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: pop
    //   174: aload_1
    //   175: aload 4
    //   177: invokevirtual 305	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   180: invokestatic 316	com/onesignal/OneSignal:a	(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;)V
    //   183: iconst_1
    //   184: ireturn
    //   185: iconst_0
    //   186: ireturn
    //   187: aload_1
    //   188: ifnull +9 -> 197
    //   191: aload_1
    //   192: invokeinterface 1039 1 0
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
  
  public static a b(Context paramContext)
  {
    return new a(paramContext, null);
  }
  
  static ab<Object, ae> b()
  {
    if (Y == null) {
      Y = new ab("onOSSubscriptionChanged", true);
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
          localJSONObject.put("app_id", l(paramContext));
          localJSONObject.put("player_id", n(paramContext));
          localJSONObject.put("opened", true);
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("notifications/");
          localStringBuilder.append(str);
          ak.a(localStringBuilder.toString(), localJSONObject, new ak.a()
          {
            void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
            {
              OneSignal.a("sending Notification Opened Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
            }
          });
        }
      }
      catch (Throwable localThrowable)
      {
        a(LOG_LEVEL.c, "Failed to generate JSON to send notification opened.", localThrowable);
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
      paramA.printStackTrace();
    }
  }
  
  static void b(String paramString)
  {
    u = paramString;
    if (b == null) {
      return;
    }
    aj.a(aj.a, "GT_PLAYER_ID", u);
  }
  
  private static void b(String paramString1, int paramInt, Throwable paramThrowable, String paramString2)
  {
    Object localObject2 = "";
    Object localObject1 = localObject2;
    if (paramString2 != null)
    {
      localObject1 = localObject2;
      if (a(LOG_LEVEL.e))
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("\n");
        ((StringBuilder)localObject1).append(paramString2);
        ((StringBuilder)localObject1).append("\n");
        localObject1 = ((StringBuilder)localObject1).toString();
      }
    }
    paramString2 = LOG_LEVEL.d;
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
    aj.a(aj.a, "OS_FILTER_OTHER_GCM_RECEIVERS", paramBoolean);
  }
  
  public static a c()
  {
    if (i == null) {
      i = new a(null);
    }
    return i;
  }
  
  @NonNull
  private static z c(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i3 = paramJSONArray.length();
    z localZ = new z();
    OSNotification localOSNotification = new OSNotification();
    localOSNotification.a = q();
    localOSNotification.b = paramBoolean1;
    localOSNotification.c = paramJSONArray.optJSONObject(0).optInt("notificationId");
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
        localOSNotification.d = p.a((JSONObject)localObject5);
        localObject3 = localObject1;
        if (localObject1 != null) {
          break label371;
        }
        localObject4 = localObject1;
        localObject3 = localObject1;
        if (!((JSONObject)localObject5).has("actionSelected")) {
          break label371;
        }
        localObject4 = localObject1;
        localObject3 = ((JSONObject)localObject5).optString("actionSelected", null);
      }
      catch (Throwable localThrowable)
      {
        label139:
        localObject3 = LOG_LEVEL.c;
        Object localObject5 = new StringBuilder();
        ((StringBuilder)localObject5).append("Error parsing JSON item ");
        ((StringBuilder)localObject5).append(i1);
        ((StringBuilder)localObject5).append("/");
        ((StringBuilder)localObject5).append(i3);
        ((StringBuilder)localObject5).append(" for callback.");
        a((LOG_LEVEL)localObject3, ((StringBuilder)localObject5).toString(), localThrowable);
        localObject2 = localObject4;
      }
      localObject4 = localObject3;
      if (localOSNotification.f == null)
      {
        localObject4 = localObject3;
        localOSNotification.f = new ArrayList();
      }
      localObject4 = localObject3;
      localOSNotification.f.add(localOSNotification.d);
      localObject1 = localObject3;
    }
    for (;;)
    {
      i1 += 1;
      break;
      localZ.a = localOSNotification;
      localZ.b = new OSNotificationAction();
      localZ.b.b = ((String)localObject2);
      localObject3 = localZ.b;
      if (localObject2 != null) {
        paramJSONArray = OSNotificationAction.ActionType.b;
      } else {
        paramJSONArray = OSNotificationAction.ActionType.a;
      }
      ((OSNotificationAction)localObject3).a = paramJSONArray;
      if (paramBoolean2) {
        paramJSONArray = localZ.a;
      }
      for (Object localObject2 = OSNotification.DisplayType.b;; localObject2 = OSNotification.DisplayType.a)
      {
        paramJSONArray.e = ((OSNotification.DisplayType)localObject2);
        return localZ;
        paramJSONArray = localZ.a;
      }
      label371:
      if (i2 == 0) {
        break label139;
      }
      i2 = 0;
      localObject2 = localObject3;
    }
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
    LOG_LEVEL localLOG_LEVEL = LOG_LEVEL.e;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SaveUnsentActiveTime: ");
    localStringBuilder.append(A);
    a(localLOG_LEVEL, localStringBuilder.toString());
    aj.a(aj.a, "GT_UNSENT_ACTIVE_TIME", paramLong);
  }
  
  static void c(String paramString)
  {
    v = paramString;
    if (b == null) {
      return;
    }
    String str = aj.a;
    if ("".equals(v)) {
      paramString = null;
    } else {
      paramString = v;
    }
    aj.a(str, "OS_EMAIL_ID", paramString);
  }
  
  private static boolean c(int paramInt)
  {
    return paramInt < -6;
  }
  
  static boolean c(Context paramContext)
  {
    return aj.b(aj.a, "OS_FILTER_OTHER_GCM_RECEIVERS", false);
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
    al.i();
    ag.a(b, a, paramString, c.a());
  }
  
  public static boolean d()
  {
    return k();
  }
  
  static boolean d(Context paramContext)
  {
    return aj.b(aj.a, "GT_FIREBASE_TRACKING_ENABLED", false);
  }
  
  static void e(String paramString)
  {
    c(paramString);
    j(b).a(paramString);
    try
    {
      al.b(new JSONObject().put("parent_player_id", paramString));
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
    boolean bool2 = false;
    x = false;
    LocationGMS.c();
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
    boolean bool1 = bool2;
    if (l1 >= 0L)
    {
      if (l1 > 86400L) {
        return false;
      }
      if (b == null)
      {
        a(LOG_LEVEL.c, "Android Context not found, please call OneSignal.init when your app starts.");
        return false;
      }
      bool1 = f();
      a(System.currentTimeMillis());
      l1 = p() + l1;
      c(l1);
      if ((l1 >= 60L) && (l() != null))
      {
        if (!bool1) {
          am.a(b);
        }
        am.a();
        return false;
      }
      bool1 = bool2;
      if (l1 >= 60L) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  static boolean e(Context paramContext)
  {
    return aj.b(aj.a, "GT_VIBRATE_ENABLED", true);
  }
  
  static boolean f()
  {
    boolean bool = al.c();
    if (bool) {
      am.a(b);
    }
    return (LocationGMS.a(b)) || (bool);
  }
  
  static boolean f(Context paramContext)
  {
    return aj.b(aj.a, "GT_SOUND_ENABLED", true);
  }
  
  static void g()
  {
    x = true;
    LocationGMS.c();
    z = SystemClock.elapsedRealtime();
    S = S();
    a(System.currentTimeMillis());
    I();
    if (B != null) {
      B.a();
    }
    v.a(b);
    h(b).a();
    if ((D != null) && (d(b))) {
      D.b();
    }
    am.b(b);
  }
  
  private static ac h(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (V == null)
    {
      V = new ac(false);
      V.a.b(new OSPermissionChangedInternalObserver());
    }
    return V;
  }
  
  private static void h(String paramString)
  {
    if (b == null) {
      return;
    }
    aj.a(aj.a, "GT_APP_ID", paramString);
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
      af.a(new Runnable()
      {
        public void run() {}
      });
    }
  }
  
  private static y j(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (Z == null)
    {
      Z = new y(false);
      Z.a.b(new x());
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
    return m(b);
  }
  
  static String l()
  {
    if ((u == null) && (b != null)) {
      u = aj.b(aj.a, "GT_PLAYER_ID", null);
    }
    return u;
  }
  
  private static String l(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return aj.b(aj.a, "GT_APP_ID", null);
  }
  
  static String m()
  {
    if ("".equals(v)) {
      return null;
    }
    if ((v == null) && (b != null)) {
      v = aj.b(aj.a, "OS_EMAIL_ID", null);
    }
    return v;
  }
  
  private static boolean m(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    return aj.b(aj.a, "ONESIGNAL_USER_PROVIDED_CONSENT", false);
  }
  
  private static String n(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return aj.b(aj.a, "GT_PLAYER_ID", null);
  }
  
  static boolean n()
  {
    if (i == null) {
      return true;
    }
    return i.i == OSInFocusDisplayOption.c;
  }
  
  private static long o(Context paramContext)
  {
    return aj.b(aj.a, "OS_LAST_SESSION_TIME", -31000L);
  }
  
  static boolean o()
  {
    a localA = i;
    boolean bool = false;
    if (localA == null) {
      return false;
    }
    if (i.i == OSInFocusDisplayOption.b) {
      bool = true;
    }
    return bool;
  }
  
  static long p()
  {
    if ((A == -1L) && (b != null)) {
      A = aj.b(aj.a, "GT_UNSENT_ACTIVE_TIME", 0L);
    }
    LOG_LEVEL localLOG_LEVEL = LOG_LEVEL.e;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("GetUnsentActiveTime: ");
    localStringBuilder.append(A);
    a(localLOG_LEVEL, localStringBuilder.toString());
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
      return af.a(b);
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
      p.a(new c(EmailErrorType.d, "Failed due to network failure. Will retry on next sync."));
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
      o.a(new c(EmailErrorType.d, "Failed due to network failure. Will retry on next sync."));
      o = null;
    }
  }
  
  public static enum EmailErrorType
  {
    private EmailErrorType() {}
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
    OneSignal.h b;
    OneSignal.i c;
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
    public abstract void a(OneSignal.j paramJ);
    
    public abstract void a(JSONObject paramJSONObject);
  }
  
  public static class c
  {
    private OneSignal.EmailErrorType a;
    private String b;
    
    c(OneSignal.EmailErrorType paramEmailErrorType, String paramString)
    {
      this.a = paramEmailErrorType;
      this.b = paramString;
    }
  }
  
  public static abstract interface d
  {
    public abstract void a();
    
    public abstract void a(OneSignal.c paramC);
  }
  
  public static abstract interface e
  {
    public abstract void a(JSONObject paramJSONObject);
  }
  
  private static class f
  {
    JSONArray a;
    boolean b;
    ak.a c;
    
    f(JSONArray paramJSONArray)
    {
      this.a = paramJSONArray;
    }
  }
  
  public static abstract interface g
  {
    public abstract void a(String paramString1, String paramString2);
  }
  
  public static abstract interface h
  {
    public abstract void a(z paramZ);
  }
  
  public static abstract interface i
  {
    public abstract void a(OSNotification paramOSNotification);
  }
  
  public static class j
  {
    private String a;
    private int b;
    
    j(int paramInt, String paramString)
    {
      this.a = paramString;
      this.b = paramInt;
    }
  }
}
