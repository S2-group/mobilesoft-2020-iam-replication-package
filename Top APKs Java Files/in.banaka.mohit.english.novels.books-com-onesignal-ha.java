package com.onesignal;

import android.app.Activity;
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
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ha
{
  private static String A;
  private static boolean B = false;
  private static boolean C = false;
  private static boolean D = false;
  private static boolean E = false;
  private static y.f F;
  static boolean G = true;
  static a H;
  private static Collection<JSONArray> I = new ArrayList();
  private static HashSet<String> J = new HashSet();
  private static f K;
  private static boolean L = false;
  private static boolean M = false;
  private static boolean N = false;
  private static JSONObject O;
  static boolean P = false;
  private static boolean Q = false;
  static boolean R = false;
  static l S;
  private static R T;
  static R U;
  private static Q<OSPermissionObserver, S> V;
  private static OSSubscriptionState W;
  static OSSubscriptionState X;
  private static Q<OSSubscriptionObserver, T> Y;
  private static K Z;
  private static e a;
  private static g aa;
  private static e b;
  private static Aa ba;
  static String c;
  private static int ca = 0;
  private static String d;
  private static boolean e = false;
  static Context f;
  private static i g = i.a;
  private static i h = i.d;
  private static String i;
  private static String j;
  private static int k = 0;
  static boolean l = false;
  private static boolean m = false;
  static ExecutorService n;
  public static ConcurrentLinkedQueue<Runnable> o = new ConcurrentLinkedQueue();
  static AtomicLong p = new AtomicLong();
  private static h q;
  private static long r = 1L;
  private static long s = -1L;
  private static Pa t;
  private static Ka u;
  private static La v;
  private static e w = new d();
  private static int x = 0;
  public static String y = "native";
  private static U z;
  
  public ha() {}
  
  private static void H()
  {
    if (!o.isEmpty())
    {
      n = Executors.newSingleThreadExecutor(new Z());
      while (!o.isEmpty()) {
        n.submit((Runnable)o.poll());
      }
    }
  }
  
  private static void I()
  {
    if (M) {
      return;
    }
    boolean bool2 = true;
    M = true;
    B = false;
    if (N) {
      C = false;
    }
    J();
    M();
    boolean bool1 = bool2;
    if (!E) {
      if (H.d) {
        bool1 = bool2;
      } else {
        bool1 = false;
      }
    }
    E = bool1;
  }
  
  private static void J()
  {
    aa localAa = new aa();
    boolean bool;
    if ((H.d) && (!E)) {
      bool = true;
    } else {
      bool = false;
    }
    y.a(f, bool, localAa);
  }
  
  private static Aa K()
  {
    Aa localAa = ba;
    if (localAa != null) {
      return localAa;
    }
    if (x == 2) {
      ba = new Ca();
    } else if (U.a()) {
      ba = new Fa();
    } else {
      ba = new Ga();
    }
    return ba;
  }
  
  private static void L()
  {
    K().a(f, d, new ba());
  }
  
  private static void M()
  {
    if (D)
    {
      L();
      return;
    }
    da localDa = new da();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("apps/");
    ((StringBuilder)localObject).append(c);
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
    a(i.f, "Starting request to get Android parameters.");
    va.a((String)localObject, localDa);
  }
  
  private static void N()
  {
    Iterator localIterator = I.iterator();
    while (localIterator.hasNext()) {
      b((JSONArray)localIterator.next(), true, false);
    }
    I.clear();
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
    i localI = i.f;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("registerUser: registerForPushFired:");
    localStringBuilder.append(B);
    localStringBuilder.append(", locationFired: ");
    localStringBuilder.append(C);
    localStringBuilder.append(", awlFired: ");
    localStringBuilder.append(D);
    a(localI, localStringBuilder.toString());
    if ((B) && (C))
    {
      if (!D) {
        return;
      }
      new Thread(new ga(), "OS_REG_USER").start();
      return;
    }
  }
  
  private static void Q()
  {
    Object localObject3 = f.getPackageName();
    Object localObject2 = f.getPackageManager();
    Object localObject1 = new JSONObject();
    ((JSONObject)localObject1).put("app_id", c);
    Object localObject4 = w.a(f);
    if (localObject4 != null) {
      ((JSONObject)localObject1).put("ad_id", localObject4);
    }
    ((JSONObject)localObject1).put("device_os", Build.VERSION.RELEASE);
    ((JSONObject)localObject1).put("timezone", O());
    ((JSONObject)localObject1).put("language", U.b());
    ((JSONObject)localObject1).put("sdk", "031002");
    ((JSONObject)localObject1).put("sdk_type", y);
    ((JSONObject)localObject1).put("android_package", localObject3);
    ((JSONObject)localObject1).put("device_model", Build.MODEL);
    try
    {
      ((JSONObject)localObject1).put("game_version", ((PackageManager)localObject2).getPackageInfo((String)localObject3, 0).versionCode);
      try
      {
        localObject2 = ((PackageManager)localObject2).getInstalledPackages(0);
        localObject3 = new JSONArray();
        localObject4 = MessageDigest.getInstance("SHA-256");
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
      if (i1 < ((List)localObject2).size())
      {
        ((MessageDigest)localObject4).update(((PackageInfo)((List)localObject2).get(i1)).packageName.getBytes());
        str = Base64.encodeToString(((MessageDigest)localObject4).digest(), 2);
        if (O.has(str)) {
          ((JSONArray)localObject3).put(str);
        }
      }
      else
      {
        ((JSONObject)localObject1).put("pkgs", localObject3);
        ((JSONObject)localObject1).put("net_type", z.e());
        ((JSONObject)localObject1).put("carrier", z.f());
        ((JSONObject)localObject1).put("rooted", Ia.a());
        wa.a((JSONObject)localObject1);
        localObject1 = new JSONObject();
        ((JSONObject)localObject1).put("identifier", A);
        ((JSONObject)localObject1).put("subscribableStatus", k);
        ((JSONObject)localObject1).put("androidPermission", s());
        ((JSONObject)localObject1).put("device_type", x);
        wa.b((JSONObject)localObject1);
        if (G)
        {
          localObject1 = F;
          if (localObject1 != null) {
            wa.a((y.f)localObject1);
          }
        }
        if (N) {
          wa.j();
        }
        M = false;
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
      Object localObject1 = q;
      if (localObject1 == null) {
        return;
      }
      localObject1 = wa.g();
      if (!wa.f()) {
        localObject1 = null;
      }
      String str = l();
      if (str == null) {
        return;
      }
      q.a(str, (String)localObject1);
      if (localObject1 != null) {
        q = null;
      }
      return;
    }
    finally {}
  }
  
  private static boolean S()
  {
    if (N) {
      return true;
    }
    return (System.currentTimeMillis() - o(f)) / 1000L >= 30L;
  }
  
  static Q<OSPermissionObserver, S> a()
  {
    if (V == null) {
      V = new Q("onOSPermissionChanged", true);
    }
    return V;
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
      a(i.f, "Not a OneSignal formatted GCM message. No 'custom' field in the bundle.");
      return null;
    }
    catch (Throwable paramBundle)
    {
      a(i.f, "Could not parse bundle, probably not a OneSignal notification.", paramBundle);
    }
    return null;
  }
  
  static void a(long paramLong)
  {
    oa.a(oa.a, "OS_LAST_SESSION_TIME", paramLong);
  }
  
  static void a(long paramLong, boolean paramBoolean)
  {
    try
    {
      JSONObject localJSONObject = new JSONObject().put("app_id", c).put("type", 1).put("state", "ping").put("active_time", paramLong);
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
      a(i.c, "Generating on_focus:JSON Failed.", localThrowable);
    }
  }
  
  static void a(Context paramContext)
  {
    int i1;
    if (f == null) {
      i1 = 1;
    } else {
      i1 = 0;
    }
    f = paramContext.getApplicationContext();
    if (i1 != 0) {
      oa.b();
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, j paramJ, k paramK)
  {
    a(paramContext);
    if ((R) && (!d()))
    {
      a(i.g, "OneSignal SDK initialization delayed, user privacy consent is set to required for this application.");
      S = new l(paramContext, paramString1, paramString2, paramJ, paramK);
      return;
    }
    H = c();
    a localA = H;
    localA.h = false;
    localA.b = paramJ;
    localA.c = paramK;
    if (!e) {
      d = paramString1;
    }
    z = new U();
    x = z.d();
    k = z.a(paramContext, x, paramString2);
    if (k == 64537) {
      return;
    }
    if (l)
    {
      if (H.b != null) {
        N();
      }
      return;
    }
    boolean bool = paramContext instanceof Activity;
    m = bool;
    c = paramString2;
    b(H.g);
    if (bool)
    {
      b.b = (Activity)paramContext;
      H.a(f);
    }
    else
    {
      b.a = true;
    }
    r = SystemClock.elapsedRealtime();
    wa.d();
    ((Application)f).registerActivityLifecycleCallbacks(new c());
    try
    {
      Class.forName("com.amazon.device.iap.PurchasingListener");
      u = new Ka(f);
      paramContext = j();
      if (paramContext != null)
      {
        if (!paramContext.equals(c))
        {
          a(i.f, "APP ID changed, clearing user id as it is no longer valid.");
          h(c);
          wa.h();
        }
      }
      else
      {
        g.a(0, f);
        h(c);
      }
      OSPermissionChangedInternalObserver.a(h(f));
      if ((m) || (l() == null))
      {
        N = S();
        a(System.currentTimeMillis());
        I();
      }
      if (H.b != null) {
        N();
      }
      if (Pa.a(f)) {
        t = new Pa(f);
      }
      if (La.a()) {
        v = new La(f);
      }
      Fa.a(f);
      l = true;
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
    if ((v != null) && (d(f))) {
      v.a(c(paramJSONArray, true, paramBoolean));
    }
    boolean bool1 = false;
    boolean bool2 = "DISABLE".equals(U.a(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
    if (!bool2) {
      bool1 = a(paramContext, paramJSONArray);
    }
    b(paramJSONArray, true, paramBoolean);
    if ((!paramBoolean) && (!bool1) && (!bool2)) {
      k(paramContext);
    }
  }
  
  private static void a(N paramN)
  {
    U.a(new X(paramN));
  }
  
  private static void a(f paramF)
  {
    if (paramF == null) {
      return;
    }
    new Thread(new V(paramF), "OS_GETTAGS_CALLBACK").start();
  }
  
  static void a(i paramI, String paramString)
  {
    a(paramI, paramString, null);
  }
  
  static void a(i paramI, String paramString, Throwable paramThrowable)
  {
    if (paramI.compareTo(h) < 1) {
      if (paramI == i.g) {
        Log.v("OneSignal", paramString, paramThrowable);
      } else if (paramI == i.f) {
        Log.d("OneSignal", paramString, paramThrowable);
      } else if (paramI == i.e) {
        Log.i("OneSignal", paramString, paramThrowable);
      } else if (paramI == i.d) {
        Log.w("OneSignal", paramString, paramThrowable);
      } else if ((paramI == i.c) || (paramI == i.b)) {
        Log.e("OneSignal", paramString, paramThrowable);
      }
    }
    if ((paramI.compareTo(g) < 1) && (b.b != null)) {
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
        U.a(new ea(paramI, paramString));
        return;
      }
      catch (Throwable paramI)
      {
        Log.e("OneSignal", "Error showing logging message.", paramI);
      }
    }
  }
  
  private static void a(String paramString, JSONObject paramJSONObject, boolean paramBoolean)
  {
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("players/");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append("/on_focus");
    paramString = ((StringBuilder)localObject).toString();
    localObject = new fa();
    if (paramBoolean)
    {
      va.d(paramString, paramJSONObject, (va.a)localObject);
      return;
    }
    va.b(paramString, paramJSONObject, (va.a)localObject);
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean, va.a paramA)
  {
    if (a("sendPurchases()")) {
      return;
    }
    if (l() == null)
    {
      aa = new g(paramJSONArray);
      paramJSONArray = aa;
      paramJSONArray.b = paramBoolean;
      paramJSONArray.c = paramA;
      return;
    }
    try
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("app_id", c);
      if (paramBoolean) {
        localJSONObject.put("existing", true);
      }
      localJSONObject.put("purchases", paramJSONArray);
      paramJSONArray = new StringBuilder();
      paramJSONArray.append("players/");
      paramJSONArray.append(l());
      paramJSONArray.append("/on_purchase");
      va.b(paramJSONArray.toString(), localJSONObject, paramA);
      if (m() != null)
      {
        paramJSONArray = new StringBuilder();
        paramJSONArray.append("players/");
        paramJSONArray.append(m());
        paramJSONArray.append("/on_purchase");
        va.b(paramJSONArray.toString(), localJSONObject, null);
        return;
      }
    }
    catch (Throwable paramJSONArray)
    {
      a(i.c, "Failed to generate JSON for sendPurchases.", paramJSONArray);
    }
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramJSONArray = c(paramJSONArray, paramBoolean1, paramBoolean2);
    if ((v != null) && (d(f))) {
      v.b(paramJSONArray);
    }
    Object localObject = H;
    if (localObject != null)
    {
      localObject = ((a)localObject).c;
      if (localObject == null) {
        return;
      }
      ((k)localObject).a(paramJSONArray.a);
      return;
    }
  }
  
  public static void a(boolean paramBoolean)
  {
    if ((R) && (!paramBoolean))
    {
      a(i.c, "Cannot change requiresUserPrivacyConsent() from TRUE to FALSE");
      return;
    }
    R = paramBoolean;
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
        Object localObject2 = i.c;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Error parsing JSON item ");
        localStringBuilder.append(i1);
        localStringBuilder.append("/");
        localStringBuilder.append(i2);
        localStringBuilder.append(" for launching a web URL.");
        a((i)localObject2, localStringBuilder.toString(), localThrowable);
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
  
  private static boolean a(i paramI)
  {
    int i1 = paramI.compareTo(g);
    boolean bool = true;
    if (i1 >= 1)
    {
      if (paramI.compareTo(h) < 1) {
        return true;
      }
      bool = false;
    }
    return bool;
  }
  
  static boolean a(String paramString)
  {
    if ((R) && (!d()))
    {
      if (paramString != null)
      {
        i localI = i.d;
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
    //   4: ldc_w 1025
    //   7: aload_0
    //   8: invokevirtual 782	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   11: ifeq +5 -> 16
    //   14: iconst_0
    //   15: ireturn
    //   16: aload_1
    //   17: invokestatic 1030	com/onesignal/ka:a	(Landroid/content/Context;)Lcom/onesignal/ka;
    //   20: astore 4
    //   22: aconst_null
    //   23: astore 6
    //   25: aconst_null
    //   26: astore_1
    //   27: aload 4
    //   29: invokevirtual 1033	com/onesignal/ka:b	()Landroid/database/sqlite/SQLiteDatabase;
    //   32: ldc_w 1035
    //   35: iconst_1
    //   36: anewarray 502	java/lang/String
    //   39: dup
    //   40: iconst_0
    //   41: ldc_w 1037
    //   44: aastore
    //   45: ldc_w 1039
    //   48: iconst_1
    //   49: anewarray 502	java/lang/String
    //   52: dup
    //   53: iconst_0
    //   54: aload_0
    //   55: aastore
    //   56: aconst_null
    //   57: aconst_null
    //   58: aconst_null
    //   59: invokevirtual 1045	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   62: astore 4
    //   64: aload 4
    //   66: invokeinterface 1050 1 0
    //   71: istore_3
    //   72: iload_3
    //   73: istore_2
    //   74: aload 4
    //   76: ifnull +65 -> 141
    //   79: aload 4
    //   81: invokeinterface 1053 1 0
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
    //   116: getstatic 675	com/onesignal/ha$i:c	Lcom/onesignal/ha$i;
    //   119: ldc_w 1055
    //   122: aload 5
    //   124: invokestatic 644	com/onesignal/ha:a	(Lcom/onesignal/ha$i;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   127: aload 4
    //   129: ifnull +10 -> 139
    //   132: aload 4
    //   134: invokeinterface 1053 1 0
    //   139: iconst_0
    //   140: istore_2
    //   141: iload_2
    //   142: ifeq +43 -> 185
    //   145: getstatic 322	com/onesignal/ha$i:f	Lcom/onesignal/ha$i;
    //   148: astore_1
    //   149: new 302	java/lang/StringBuilder
    //   152: dup
    //   153: invokespecial 303	java/lang/StringBuilder:<init>	()V
    //   156: astore 4
    //   158: aload 4
    //   160: ldc_w 1057
    //   163: invokevirtual 309	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: pop
    //   167: aload 4
    //   169: aload_0
    //   170: invokevirtual 309	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: pop
    //   174: aload_1
    //   175: aload 4
    //   177: invokevirtual 316	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   180: invokestatic 327	com/onesignal/ha:a	(Lcom/onesignal/ha$i;Ljava/lang/String;)V
    //   183: iconst_1
    //   184: ireturn
    //   185: iconst_0
    //   186: ireturn
    //   187: aload_1
    //   188: ifnull +9 -> 197
    //   191: aload_1
    //   192: invokeinterface 1053 1 0
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
  
  static Q<OSSubscriptionObserver, T> b()
  {
    if (Y == null) {
      Y = new Q("onOSSubscriptionChanged", true);
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
    while (i1 < paramJSONArray.length())
    {
      try
      {
        String str = new JSONObject(paramJSONArray.getJSONObject(i1).optString("custom", null)).optString("i", null);
        if (!J.contains(str))
        {
          J.add(str);
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("app_id", l(paramContext));
          localJSONObject.put("player_id", n(paramContext));
          localJSONObject.put("opened", true);
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("notifications/");
          localStringBuilder.append(str);
          va.a(localStringBuilder.toString(), localJSONObject, new Y());
        }
      }
      catch (Throwable localThrowable)
      {
        a(i.c, "Failed to generate JSON to send notification opened.", localThrowable);
      }
      i1 += 1;
    }
  }
  
  private static void b(a paramA)
  {
    if (c().h) {
      paramA.i = c().i;
    }
    H = paramA;
    paramA = H;
    Context localContext = paramA.a;
    paramA.a = null;
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
      a(localContext, paramA, localBundle.getString("onesignal_app_id"), H.b, H.c);
      return;
    }
    catch (Throwable paramA)
    {
      paramA.printStackTrace();
    }
  }
  
  static void b(String paramString)
  {
    i = paramString;
    if (f == null) {
      return;
    }
    oa.a(oa.a, "GT_PLAYER_ID", i);
  }
  
  private static void b(String paramString1, int paramInt, Throwable paramThrowable, String paramString2)
  {
    Object localObject2 = "";
    Object localObject1 = localObject2;
    if (paramString2 != null)
    {
      localObject1 = localObject2;
      if (a(i.e))
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("\n");
        ((StringBuilder)localObject1).append(paramString2);
        ((StringBuilder)localObject1).append("\n");
        localObject1 = ((StringBuilder)localObject1).toString();
      }
    }
    paramString2 = i.d;
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
    a localA = H;
    if ((localA != null) && (localA.b != null))
    {
      a(c(paramJSONArray, paramBoolean1, paramBoolean2));
      return;
    }
    I.add(paramJSONArray);
  }
  
  private static void b(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject.put("net_type", z.e());
      return;
    }
    catch (Throwable paramJSONObject) {}
  }
  
  static void b(boolean paramBoolean)
  {
    if (f == null) {
      return;
    }
    oa.a(oa.a, "OS_FILTER_OTHER_GCM_RECEIVERS", paramBoolean);
  }
  
  private static N c(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i3 = paramJSONArray.length();
    N localN = new N();
    L localL = new L();
    localL.a = q();
    localL.b = paramBoolean1;
    localL.c = paramJSONArray.optJSONObject(0).optInt("notificationId");
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
        localL.d = A.a((JSONObject)localObject5);
        localObject3 = localObject1;
        if (localObject1 != null) {
          break label362;
        }
        localObject4 = localObject1;
        localObject3 = localObject1;
        if (!((JSONObject)localObject5).has("actionSelected")) {
          break label362;
        }
        localObject4 = localObject1;
        localObject3 = ((JSONObject)localObject5).optString("actionSelected", null);
      }
      catch (Throwable localThrowable)
      {
        label139:
        localObject3 = i.c;
        Object localObject5 = new StringBuilder();
        ((StringBuilder)localObject5).append("Error parsing JSON item ");
        ((StringBuilder)localObject5).append(i1);
        ((StringBuilder)localObject5).append("/");
        ((StringBuilder)localObject5).append(i3);
        ((StringBuilder)localObject5).append(" for callback.");
        a((i)localObject3, ((StringBuilder)localObject5).toString(), localThrowable);
        localObject2 = localObject4;
      }
      localObject4 = localObject3;
      if (localL.f == null)
      {
        localObject4 = localObject3;
        localL.f = new ArrayList();
      }
      localObject4 = localObject3;
      localL.f.add(localL.d);
      localObject1 = localObject3;
    }
    for (;;)
    {
      i1 += 1;
      break;
      localN.a = localL;
      localN.b = new M();
      localObject3 = localN.b;
      ((M)localObject3).b = localObject2;
      if (localObject2 != null) {
        paramJSONArray = M.a.b;
      } else {
        paramJSONArray = M.a.a;
      }
      ((M)localObject3).a = paramJSONArray;
      if (paramBoolean2)
      {
        localN.a.e = L.a.b;
        return localN;
      }
      localN.a.e = L.a.a;
      return localN;
      label362:
      if (i2 == 0) {
        break label139;
      }
      i2 = 0;
      Object localObject2 = localObject3;
    }
  }
  
  public static a c()
  {
    if (H == null) {
      H = new a(null);
    }
    return H;
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
    if (f == null) {
      return;
    }
    i localI = i.e;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SaveUnsentActiveTime: ");
    localStringBuilder.append(s);
    a(localI, localStringBuilder.toString());
    oa.a(oa.a, "GT_UNSENT_ACTIVE_TIME", paramLong);
  }
  
  static void c(String paramString)
  {
    j = paramString;
    if (f == null) {
      return;
    }
    String str = oa.a;
    if ("".equals(j)) {
      paramString = null;
    } else {
      paramString = j;
    }
    oa.a(str, "OS_EMAIL_ID", paramString);
  }
  
  private static boolean c(int paramInt)
  {
    return paramInt < -6;
  }
  
  static boolean c(Context paramContext)
  {
    return oa.b(oa.a, "OS_FILTER_OTHER_GCM_RECEIVERS", false);
  }
  
  static void d(String paramString)
  {
    b(paramString);
    i();
    a(K);
    i(f).a(paramString);
    g localG = aa;
    if (localG != null)
    {
      a(localG.a, localG.b, localG.c);
      aa = null;
    }
    wa.i();
    ja.a(f, c, paramString, d.a());
  }
  
  public static boolean d()
  {
    return k();
  }
  
  static boolean d(Context paramContext)
  {
    return oa.b(oa.a, "GT_FIREBASE_TRACKING_ENABLED", false);
  }
  
  static void e(String paramString)
  {
    c(paramString);
    j(f).a(paramString);
    try
    {
      wa.b(new JSONObject().put("parent_player_id", paramString));
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
    m = false;
    y.c();
    if (!l) {
      return false;
    }
    Ka localKa = u;
    if (localKa != null) {
      localKa.a();
    }
    if (r == -1L) {
      return false;
    }
    double d1 = SystemClock.elapsedRealtime() - r;
    Double.isNaN(d1);
    long l1 = (d1 / 1000.0D + 0.5D);
    r = SystemClock.elapsedRealtime();
    if (l1 >= 0L)
    {
      if (l1 > 86400L) {
        return false;
      }
      if (f == null)
      {
        a(i.c, "Android Context not found, please call OneSignal.init when your app starts.");
        return false;
      }
      boolean bool2 = f();
      a(System.currentTimeMillis());
      l1 = p() + l1;
      c(l1);
      if ((l1 >= 60L) && (l() != null))
      {
        if (!bool2) {
          xa.a(f);
        }
        xa.a();
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
    return oa.b(oa.a, "GT_VIBRATE_ENABLED", true);
  }
  
  static boolean f()
  {
    boolean bool = wa.c();
    if (bool) {
      xa.a(f);
    }
    return (y.a(f)) || (bool);
  }
  
  static boolean f(Context paramContext)
  {
    return oa.b(oa.a, "GT_SOUND_ENABLED", true);
  }
  
  static void g()
  {
    m = true;
    y.c();
    r = SystemClock.elapsedRealtime();
    N = S();
    a(System.currentTimeMillis());
    I();
    Pa localPa = t;
    if (localPa != null) {
      localPa.b();
    }
    H.a(f);
    h(f).g();
    if ((v != null) && (d(f))) {
      v.b();
    }
    xa.b(f);
  }
  
  private static R h(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (T == null)
    {
      T = new R(false);
      T.a.b(new OSPermissionChangedInternalObserver());
    }
    return T;
  }
  
  private static void h(String paramString)
  {
    if (f == null) {
      return;
    }
    oa.a(oa.a, "GT_APP_ID", paramString);
  }
  
  static boolean h()
  {
    return m;
  }
  
  private static OSSubscriptionState i(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (W == null)
    {
      W = new OSSubscriptionState(false, h(paramContext).h());
      h(paramContext).a.a(W);
      W.a.b(new OSSubscriptionChangedInternalObserver());
    }
    return W;
  }
  
  static void i()
  {
    if (q != null) {
      U.a(new W());
    }
  }
  
  private static K j(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (Z == null)
    {
      Z = new K(false);
      Z.a.b(new J());
    }
    return Z;
  }
  
  static String j()
  {
    return l(f);
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
    return m(f);
  }
  
  static String l()
  {
    if ((i == null) && (f != null)) {
      i = oa.b(oa.a, "GT_PLAYER_ID", null);
    }
    return i;
  }
  
  private static String l(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return oa.b(oa.a, "GT_APP_ID", null);
  }
  
  static String m()
  {
    if ("".equals(j)) {
      return null;
    }
    if ((j == null) && (f != null)) {
      j = oa.b(oa.a, "OS_EMAIL_ID", null);
    }
    return j;
  }
  
  private static boolean m(Context paramContext)
  {
    if (paramContext == null) {
      return false;
    }
    return oa.b(oa.a, "ONESIGNAL_USER_PROVIDED_CONSENT", false);
  }
  
  private static String n(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return oa.b(oa.a, "GT_PLAYER_ID", null);
  }
  
  static boolean n()
  {
    a localA = H;
    if (localA == null) {
      return true;
    }
    return localA.i == l.c;
  }
  
  private static long o(Context paramContext)
  {
    return oa.b(oa.a, "OS_LAST_SESSION_TIME", -31000L);
  }
  
  static boolean o()
  {
    a localA = H;
    boolean bool = false;
    if (localA == null) {
      return false;
    }
    if (localA.i == l.b) {
      bool = true;
    }
    return bool;
  }
  
  static long p()
  {
    if ((s == -1L) && (f != null)) {
      s = oa.b(oa.a, "GT_UNSENT_ACTIVE_TIME", 0L);
    }
    i localI = i.e;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("GetUnsentActiveTime: ");
    localStringBuilder.append(s);
    a(localI, localStringBuilder.toString());
    return s;
  }
  
  static boolean q()
  {
    return (l) && (h());
  }
  
  static void r()
  {
    N = false;
    a(System.currentTimeMillis());
  }
  
  static boolean s()
  {
    if (H.f) {
      return U.a(f);
    }
    return true;
  }
  
  static void t()
  {
    e localE = b;
    if (localE != null)
    {
      localE.onSuccess();
      b = null;
    }
  }
  
  static void u()
  {
    e localE = b;
    if (localE != null)
    {
      localE.a(new d(c.d, "Failed due to network failure. Will retry on next sync."));
      b = null;
    }
  }
  
  static void v()
  {
    e localE = a;
    if (localE != null)
    {
      localE.onSuccess();
      a = null;
    }
  }
  
  static void w()
  {
    e localE = a;
    if (localE != null)
    {
      localE.a(new d(c.d, "Failed due to network failure. Will retry on next sync."));
      a = null;
    }
  }
  
  public static class a
  {
    Context a;
    ha.j b;
    ha.k c;
    boolean d;
    boolean e;
    boolean f;
    boolean g;
    boolean h;
    ha.l i = ha.l.b;
    
    private a() {}
    
    private a(Context paramContext)
    {
      this.a = paramContext;
    }
    
    public a a(ha.l paramL)
    {
      ha.c().h = false;
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
      ha.a(this);
    }
  }
  
  public static abstract interface b
  {
    public abstract void a(ha.m paramM);
    
    public abstract void a(JSONObject paramJSONObject);
  }
  
  public static enum c
  {
    private c() {}
  }
  
  public static class d
  {
    private ha.c a;
    private String b;
    
    d(ha.c paramC, String paramString)
    {
      this.a = paramC;
      this.b = paramString;
    }
  }
  
  public static abstract interface e
  {
    public abstract void a(ha.d paramD);
    
    public abstract void onSuccess();
  }
  
  public static abstract interface f
  {
    public abstract void a(JSONObject paramJSONObject);
  }
  
  private static class g
  {
    JSONArray a;
    boolean b;
    va.a c;
    
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
    public abstract void a(N paramN);
  }
  
  public static abstract interface k
  {
    public abstract void a(L paramL);
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
