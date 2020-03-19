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

public class ak
{
  private static long A;
  private static bc B;
  private static ba C;
  private static bb D;
  private static d E;
  private static int F;
  private static aj G;
  private static String H;
  private static boolean I;
  private static boolean J;
  private static boolean K;
  private static boolean L;
  private static p.f M;
  private static Collection<JSONArray> N;
  private static HashSet<String> O;
  private static ArrayList<f> P;
  private static boolean Q;
  private static boolean R;
  private static boolean S;
  private static JSONObject T;
  private static boolean U;
  private static ag V;
  private static af<Object, ah> W;
  private static OSSubscriptionState X;
  private static af<Object, ai> Y;
  private static aa Z;
  static String a;
  private static g aa;
  private static at ab;
  private static int ac = 0;
  static Context b;
  static boolean c;
  static ExecutorService d;
  public static ConcurrentLinkedQueue<Runnable> e;
  static AtomicLong f;
  public static String g;
  static boolean h;
  static a i;
  static boolean j;
  static boolean k;
  static k l;
  static ag m;
  static OSSubscriptionState n;
  private static e o;
  private static e p;
  private static String q;
  private static boolean r;
  private static i s = i.a;
  private static i t = i.d;
  private static String u = null;
  private static String v = null;
  private static int w;
  private static boolean x;
  private static h y;
  private static long z;
  
  static
  {
    e = new ConcurrentLinkedQueue();
    f = new AtomicLong();
    z = 1L;
    A = -1L;
    E = new c();
    g = "native";
    h = true;
    N = new ArrayList();
    O = new HashSet();
    P = new ArrayList();
    k = false;
  }
  
  public ak() {}
  
  private static void I()
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
  
  private static boolean J()
  {
    if ((c) && (d == null)) {
      return false;
    }
    if ((!c) && (d == null)) {
      return true;
    }
    return (d != null) && (!d.isShutdown());
  }
  
  private static void K()
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
    L();
    O();
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
  
  private static void L()
  {
    p.d local6 = new p.d()
    {
      public p.a a()
      {
        return p.a.a;
      }
      
      public void a(p.f paramAnonymousF)
      {
        ak.a(paramAnonymousF);
        ak.c(true);
        ak.x();
      }
    };
    boolean bool;
    if ((i.d) && (!L)) {
      bool = true;
    } else {
      bool = false;
    }
    p.a(b, bool, local6);
  }
  
  private static at M()
  {
    if (ab != null) {
      return ab;
    }
    Object localObject;
    if (F == 2) {
      localObject = new au();
    }
    for (;;)
    {
      ab = (at)localObject;
      break;
      if (aj.a()) {
        localObject = new aw();
      } else {
        localObject = new ax();
      }
    }
    return ab;
  }
  
  private static void N()
  {
    M().a(b, q, new at.a()
    {
      public void a(String paramAnonymousString, int paramAnonymousInt)
      {
        if (paramAnonymousInt < 1 ? (ar.g() == null) && ((ak.y() == 1) || (ak.b(ak.y()))) : ak.b(ak.y())) {
          ak.c(paramAnonymousInt);
        }
        ak.f(paramAnonymousString);
        ak.d(true);
        ak.g(ak.b).b(paramAnonymousString);
        ak.x();
      }
    });
  }
  
  private static void O()
  {
    if (K)
    {
      N();
      return;
    }
    aq.a local9 = new aq.a()
    {
      void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        new Thread(new Runnable()
        {
          public void run()
          {
            try
            {
              int j = ak.z() * 10000 + 30000;
              int i = j;
              if (j > 90000) {
                i = 90000;
              }
              ak.i localI = ak.i.e;
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("Failed to get Android parameters, trying again in ");
              localStringBuilder.append(i / 1000);
              localStringBuilder.append(" seconds.");
              ak.a(localI, localStringBuilder.toString());
              Thread.sleep(i);
            }
            catch (Throwable localThrowable)
            {
              for (;;) {}
            }
            ak.A();
            ak.B();
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
            ak.e(true);
            ak.g(paramAnonymousString.getString("android_sender_id"));
          }
          ak.j = paramAnonymousString.optBoolean("enterp", false);
          ak.f(paramAnonymousString.optBoolean("use_email_auth", false));
          ak.a(paramAnonymousString.getJSONObject("awl_list"));
          boolean bool = paramAnonymousString.optBoolean("fba", false);
          ap.a(ap.a, "GT_FIREBASE_TRACKING_ENABLED", bool);
          r.a(ak.b, paramAnonymousString);
        }
        catch (Throwable paramAnonymousString)
        {
          paramAnonymousString.printStackTrace();
        }
        ak.g(true);
        ak.C();
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
    a(i.f, "Starting request to get Android parameters.");
    aq.a((String)localObject, local9);
  }
  
  private static void P()
  {
    Iterator localIterator = N.iterator();
    while (localIterator.hasNext()) {
      b((JSONArray)localIterator.next(), true, false);
    }
    N.clear();
  }
  
  private static int Q()
  {
    TimeZone localTimeZone = Calendar.getInstance().getTimeZone();
    int i2 = localTimeZone.getRawOffset();
    int i1 = i2;
    if (localTimeZone.inDaylightTime(new Date())) {
      i1 = i2 + localTimeZone.getDSTSavings();
    }
    return i1 / 1000;
  }
  
  private static void R()
  {
    i localI = i.f;
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
            ak.D();
            al.a(ak.b, ak.a, ak.E(), c.a());
            return;
          }
          catch (JSONException localJSONException)
          {
            ak.a(ak.i.b, "FATAL Error registering device!", localJSONException);
          }
        }
      }, "OS_REG_USER").start();
    }
  }
  
  private static void S()
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
    localJSONObject.put("timezone", Q());
    localJSONObject.put("language", aj.f());
    localJSONObject.put("sdk", "031006");
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
        localJSONObject.put("rooted", az.a());
        ar.a(localJSONObject);
        localJSONObject = new JSONObject();
        localJSONObject.put("identifier", H);
        localJSONObject.put("subscribableStatus", w);
        localJSONObject.put("androidPermission", s());
        localJSONObject.put("device_type", F);
        ar.b(localJSONObject);
        if ((h) && (M != null)) {
          ar.a(M);
        }
        if (S) {
          ar.j();
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
  
  private static void T()
  {
    synchronized (P)
    {
      if (P.size() == 0) {
        return;
      }
      new Thread(new Runnable()
      {
        public void run()
        {
          bi.a localA = ar.c(ak.F() ^ true);
          if (localA.a) {
            ak.h(true);
          }
          synchronized (ak.G())
          {
            Iterator localIterator = ak.G().iterator();
            while (localIterator.hasNext())
            {
              ak.f localF = (ak.f)localIterator.next();
              if ((localA.b != null) && (!localA.toString().equals("{}"))) {
                localF.a(localA.b);
              } else {
                localF.a(null);
              }
            }
            ak.G().clear();
            return;
          }
        }
      }, "OS_GETTAGS_CALLBACK").start();
      return;
    }
  }
  
  private static void U()
  {
    try
    {
      Object localObject1 = y;
      if (localObject1 == null) {
        return;
      }
      localObject1 = ar.g();
      if (!ar.f()) {
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
  
  private static boolean V()
  {
    if (S) {
      return true;
    }
    return (System.currentTimeMillis() - n(b)) / 1000L >= 30L;
  }
  
  static af<Object, ah> a()
  {
    if (W == null) {
      W = new af("onOSPermissionChanged", true);
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
      i localI;
      if (paramBundle.containsKey("custom"))
      {
        paramBundle = new JSONObject(paramBundle.getString("custom"));
        if (paramBundle.has("i")) {
          return paramBundle.optString("i", null);
        }
        localI = i.f;
      }
      for (paramBundle = "Not a OneSignal formatted GCM message. No 'i' field in custom.";; paramBundle = "Not a OneSignal formatted GCM message. No 'custom' field in the bundle.")
      {
        a(localI, paramBundle);
        return null;
        localI = i.f;
      }
      return null;
    }
    catch (Throwable paramBundle)
    {
      a(i.f, "Could not parse bundle, probably not a OneSignal notification.", paramBundle);
    }
  }
  
  public static void a(int paramInt)
  {
    Runnable local7 = new Runnable()
    {
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: getstatic 26	com/onesignal/ak:b	Landroid/content/Context;
        //   3: invokestatic 31	com/onesignal/an:a	(Landroid/content/Context;)Lcom/onesignal/an;
        //   6: astore_1
        //   7: aload_1
        //   8: invokevirtual 34	com/onesignal/an:a	()Landroid/database/sqlite/SQLiteDatabase;
        //   11: astore_2
        //   12: aload_2
        //   13: astore_1
        //   14: aload_2
        //   15: invokevirtual 39	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
        //   18: aload_2
        //   19: astore_1
        //   20: new 41	java/lang/StringBuilder
        //   23: dup
        //   24: invokespecial 42	java/lang/StringBuilder:<init>	()V
        //   27: astore_3
        //   28: aload_2
        //   29: astore_1
        //   30: aload_3
        //   31: ldc 44
        //   33: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   36: pop
        //   37: aload_2
        //   38: astore_1
        //   39: aload_3
        //   40: aload_0
        //   41: getfield 15	com/onesignal/ak$7:a	I
        //   44: invokevirtual 51	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
        //   47: pop
        //   48: aload_2
        //   49: astore_1
        //   50: aload_3
        //   51: ldc 53
        //   53: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   56: pop
        //   57: aload_2
        //   58: astore_1
        //   59: aload_3
        //   60: ldc 55
        //   62: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   65: pop
        //   66: aload_2
        //   67: astore_1
        //   68: aload_3
        //   69: ldc 57
        //   71: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   74: pop
        //   75: aload_2
        //   76: astore_1
        //   77: aload_3
        //   78: ldc 59
        //   80: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   83: pop
        //   84: aload_2
        //   85: astore_1
        //   86: aload_3
        //   87: ldc 61
        //   89: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   92: pop
        //   93: aload_2
        //   94: astore_1
        //   95: aload_3
        //   96: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   99: astore_3
        //   100: aload_2
        //   101: astore_1
        //   102: new 67	android/content/ContentValues
        //   105: dup
        //   106: invokespecial 68	android/content/ContentValues:<init>	()V
        //   109: astore 4
        //   111: aload_2
        //   112: astore_1
        //   113: aload 4
        //   115: ldc 59
        //   117: iconst_1
        //   118: invokestatic 74	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   121: invokevirtual 78	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
        //   124: aload_2
        //   125: astore_1
        //   126: aload_2
        //   127: ldc 80
        //   129: aload 4
        //   131: aload_3
        //   132: aconst_null
        //   133: invokevirtual 84	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
        //   136: ifle +16 -> 152
        //   139: aload_2
        //   140: astore_1
        //   141: getstatic 26	com/onesignal/ak:b	Landroid/content/Context;
        //   144: aload_2
        //   145: aload_0
        //   146: getfield 15	com/onesignal/ak$7:a	I
        //   149: invokestatic 89	com/onesignal/y:a	(Landroid/content/Context;Landroid/database/sqlite/SQLiteDatabase;I)V
        //   152: aload_2
        //   153: astore_1
        //   154: aload_2
        //   155: getstatic 26	com/onesignal/ak:b	Landroid/content/Context;
        //   158: invokestatic 94	com/onesignal/f:a	(Landroid/database/sqlite/SQLiteDatabase;Landroid/content/Context;)V
        //   161: aload_2
        //   162: astore_1
        //   163: aload_2
        //   164: invokevirtual 97	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
        //   167: aload_2
        //   168: ifnull +111 -> 279
        //   171: aload_2
        //   172: invokevirtual 100	android/database/sqlite/SQLiteDatabase:endTransaction	()V
        //   175: goto +104 -> 279
        //   178: astore_2
        //   179: goto +119 -> 298
        //   182: astore_3
        //   183: goto +12 -> 195
        //   186: astore_2
        //   187: aconst_null
        //   188: astore_1
        //   189: goto +109 -> 298
        //   192: astore_3
        //   193: aconst_null
        //   194: astore_2
        //   195: aload_2
        //   196: astore_1
        //   197: getstatic 106	com/onesignal/ak$i:c	Lcom/onesignal/ak$i;
        //   200: astore 4
        //   202: aload_2
        //   203: astore_1
        //   204: new 41	java/lang/StringBuilder
        //   207: dup
        //   208: invokespecial 42	java/lang/StringBuilder:<init>	()V
        //   211: astore 5
        //   213: aload_2
        //   214: astore_1
        //   215: aload 5
        //   217: ldc 108
        //   219: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   222: pop
        //   223: aload_2
        //   224: astore_1
        //   225: aload 5
        //   227: aload_0
        //   228: getfield 15	com/onesignal/ak$7:a	I
        //   231: invokevirtual 51	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
        //   234: pop
        //   235: aload_2
        //   236: astore_1
        //   237: aload 5
        //   239: ldc 110
        //   241: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   244: pop
        //   245: aload_2
        //   246: astore_1
        //   247: aload 4
        //   249: aload 5
        //   251: invokevirtual 65	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   254: aload_3
        //   255: invokestatic 113	com/onesignal/ak:a	(Lcom/onesignal/ak$i;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   258: aload_2
        //   259: ifnull +20 -> 279
        //   262: aload_2
        //   263: invokevirtual 100	android/database/sqlite/SQLiteDatabase:endTransaction	()V
        //   266: goto +13 -> 279
        //   269: astore_1
        //   270: getstatic 106	com/onesignal/ak$i:c	Lcom/onesignal/ak$i;
        //   273: ldc 115
        //   275: aload_1
        //   276: invokestatic 113	com/onesignal/ak:a	(Lcom/onesignal/ak$i;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   279: getstatic 26	com/onesignal/ak:b	Landroid/content/Context;
        //   282: ldc 80
        //   284: invokevirtual 121	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
        //   287: checkcast 123	android/app/NotificationManager
        //   290: aload_0
        //   291: getfield 15	com/onesignal/ak$7:a	I
        //   294: invokevirtual 126	android/app/NotificationManager:cancel	(I)V
        //   297: return
        //   298: aload_1
        //   299: ifnull +20 -> 319
        //   302: aload_1
        //   303: invokevirtual 100	android/database/sqlite/SQLiteDatabase:endTransaction	()V
        //   306: goto +13 -> 319
        //   309: astore_1
        //   310: getstatic 106	com/onesignal/ak$i:c	Lcom/onesignal/ak$i;
        //   313: ldc 115
        //   315: aload_1
        //   316: invokestatic 113	com/onesignal/ak:a	(Lcom/onesignal/ak$i;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   319: aload_2
        //   320: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	321	0	this	7
        //   6	241	1	localObject1	Object
        //   269	34	1	localThrowable1	Throwable
        //   309	7	1	localThrowable2	Throwable
        //   11	161	2	localSQLiteDatabase	android.database.sqlite.SQLiteDatabase
        //   178	1	2	localObject2	Object
        //   186	1	2	localObject3	Object
        //   194	126	2	localObject4	Object
        //   27	105	3	localObject5	Object
        //   182	1	3	localThrowable3	Throwable
        //   192	63	3	localThrowable4	Throwable
        //   109	139	4	localObject6	Object
        //   211	39	5	localStringBuilder	StringBuilder
        // Exception table:
        //   from	to	target	type
        //   14	18	178	finally
        //   20	28	178	finally
        //   30	37	178	finally
        //   39	48	178	finally
        //   50	57	178	finally
        //   59	66	178	finally
        //   68	75	178	finally
        //   77	84	178	finally
        //   86	93	178	finally
        //   95	100	178	finally
        //   102	111	178	finally
        //   113	124	178	finally
        //   126	139	178	finally
        //   141	152	178	finally
        //   154	161	178	finally
        //   163	167	178	finally
        //   197	202	178	finally
        //   204	213	178	finally
        //   215	223	178	finally
        //   225	235	178	finally
        //   237	245	178	finally
        //   247	258	178	finally
        //   14	18	182	java/lang/Throwable
        //   20	28	182	java/lang/Throwable
        //   30	37	182	java/lang/Throwable
        //   39	48	182	java/lang/Throwable
        //   50	57	182	java/lang/Throwable
        //   59	66	182	java/lang/Throwable
        //   68	75	182	java/lang/Throwable
        //   77	84	182	java/lang/Throwable
        //   86	93	182	java/lang/Throwable
        //   95	100	182	java/lang/Throwable
        //   102	111	182	java/lang/Throwable
        //   113	124	182	java/lang/Throwable
        //   126	139	182	java/lang/Throwable
        //   141	152	182	java/lang/Throwable
        //   154	161	182	java/lang/Throwable
        //   163	167	182	java/lang/Throwable
        //   7	12	186	finally
        //   7	12	192	java/lang/Throwable
        //   171	175	269	java/lang/Throwable
        //   262	266	269	java/lang/Throwable
        //   302	306	309	java/lang/Throwable
      }
    };
    if ((b != null) && (!J()))
    {
      local7.run();
      return;
    }
    i localI = i.c;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("OneSignal.init has not been called. Could not clear notification id: ");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" at this time - movingthis operation to a waiting task queue. The notification will still be canceledfrom NotificationManager at this time.");
    a(localI, localStringBuilder.toString());
    e.add(local7);
  }
  
  static void a(long paramLong)
  {
    ap.a(ap.a, "OS_LAST_SESSION_TIME", paramLong);
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
      a(i.c, "Generating on_focus:JSON Failed.", localThrowable);
    }
  }
  
  public static void a(Context paramContext)
  {
    if (paramContext == null)
    {
      a(i.d, "setAppContext(null) is not valid, ignoring!");
      return;
    }
    int i1;
    if (b == null) {
      i1 = 1;
    } else {
      i1 = 0;
    }
    b = paramContext.getApplicationContext();
    if (i1 != 0) {
      ap.b();
    }
  }
  
  public static void a(Context paramContext, String paramString1, String paramString2, j paramJ, k paramK)
  {
    a(paramContext);
    if ((k) && (!d()))
    {
      a(i.g, "OneSignal SDK initialization delayed, user privacy consent is set to required for this application.");
      l = new k(paramContext, paramString1, paramString2, paramJ, paramK);
      return;
    }
    i = c();
    i.h = false;
    i.b = paramJ;
    i.c = paramK;
    if (!r) {
      q = paramString1;
    }
    G = new aj();
    F = G.c();
    w = G.a(paramContext, F, paramString2);
    if (w == 64537) {
      return;
    }
    if (c)
    {
      if (i.b != null) {
        P();
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
      x.a(b);
    }
    else
    {
      a.a = true;
    }
    z = SystemClock.elapsedRealtime();
    ar.d();
    ((Application)b).registerActivityLifecycleCallbacks(new b());
    try
    {
      Class.forName("com.amazon.device.iap.PurchasingListener");
      C = new ba(b);
      paramContext = j();
      if (paramContext != null)
      {
        if (!paramContext.equals(a))
        {
          a(i.f, "APP ID changed, clearing user id as it is no longer valid.");
          h(a);
          ar.h();
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
        S = V();
        a(System.currentTimeMillis());
        K();
      }
      if (i.b != null) {
        P();
      }
      if (bc.a(b)) {
        B = new bc(b);
      }
      if (bb.a()) {
        D = new bb(b);
      }
      aw.a(b);
      c = true;
      I();
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
    boolean bool2 = "DISABLE".equals(aj.a(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
    if (!bool2) {
      bool1 = a(paramContext, paramJSONArray);
    }
    b(paramJSONArray, true, paramBoolean);
    if ((!paramBoolean) && (!bool1) && (!bool2)) {
      k(paramContext);
    }
  }
  
  private static void a(ad paramAd)
  {
    aj.a(new Runnable()
    {
      public void run()
      {
        ak.i.b.a(this.a);
      }
    });
  }
  
  static void a(i paramI, String paramString)
  {
    a(paramI, paramString, null);
  }
  
  static void a(i paramI, final String paramString, Throwable paramThrowable)
  {
    if (paramI.compareTo(t) < 1) {
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
    if ((paramI.compareTo(s) < 1) && (a.b != null)) {
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
        aj.a(new Runnable()
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
    localObject = new aq.a()
    {
      void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        ak.a("sending on_focus Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
      }
      
      void a(String paramAnonymousString)
      {
        ak.b(0L);
      }
    };
    if (paramBoolean)
    {
      aq.d(paramString, paramJSONObject, (aq.a)localObject);
      return;
    }
    aq.b(paramString, paramJSONObject, (aq.a)localObject);
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean, aq.a paramA)
  {
    if (a("sendPurchases()")) {
      return;
    }
    if (l() == null)
    {
      aa = new g(paramJSONArray);
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
      aq.b(paramJSONArray.toString(), localJSONObject, paramA);
      if (m() != null)
      {
        paramJSONArray = new StringBuilder();
        paramJSONArray.append("players/");
        paramJSONArray.append(m());
        paramJSONArray.append("/on_purchase");
        aq.b(paramJSONArray.toString(), localJSONObject, null);
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
      a(i.c, "Cannot change requiresUserPrivacyConsent() from TRUE to FALSE");
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
    //   4: ldc_w 1027
    //   7: aload_0
    //   8: invokevirtual 797	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   11: ifeq +5 -> 16
    //   14: iconst_0
    //   15: ireturn
    //   16: aload_1
    //   17: invokestatic 1032	com/onesignal/an:a	(Landroid/content/Context;)Lcom/onesignal/an;
    //   20: astore 4
    //   22: aconst_null
    //   23: astore 6
    //   25: aconst_null
    //   26: astore_1
    //   27: aload 4
    //   29: invokevirtual 1035	com/onesignal/an:b	()Landroid/database/sqlite/SQLiteDatabase;
    //   32: ldc_w 1037
    //   35: iconst_1
    //   36: anewarray 502	java/lang/String
    //   39: dup
    //   40: iconst_0
    //   41: ldc_w 1039
    //   44: aastore
    //   45: ldc_w 1041
    //   48: iconst_1
    //   49: anewarray 502	java/lang/String
    //   52: dup
    //   53: iconst_0
    //   54: aload_0
    //   55: aastore
    //   56: aconst_null
    //   57: aconst_null
    //   58: aconst_null
    //   59: invokevirtual 1047	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   62: astore 4
    //   64: aload 4
    //   66: invokeinterface 1052 1 0
    //   71: istore_3
    //   72: iload_3
    //   73: istore_2
    //   74: aload 4
    //   76: ifnull +65 -> 141
    //   79: aload 4
    //   81: invokeinterface 1055 1 0
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
    //   116: getstatic 657	com/onesignal/ak$i:c	Lcom/onesignal/ak$i;
    //   119: ldc_w 1057
    //   122: aload 5
    //   124: invokestatic 646	com/onesignal/ak:a	(Lcom/onesignal/ak$i;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   127: aload 4
    //   129: ifnull +10 -> 139
    //   132: aload 4
    //   134: invokeinterface 1055 1 0
    //   139: iconst_0
    //   140: istore_2
    //   141: iload_2
    //   142: ifeq +43 -> 185
    //   145: getstatic 324	com/onesignal/ak$i:f	Lcom/onesignal/ak$i;
    //   148: astore_1
    //   149: new 304	java/lang/StringBuilder
    //   152: dup
    //   153: invokespecial 305	java/lang/StringBuilder:<init>	()V
    //   156: astore 4
    //   158: aload 4
    //   160: ldc_w 1059
    //   163: invokevirtual 311	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: pop
    //   167: aload 4
    //   169: aload_0
    //   170: invokevirtual 311	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: pop
    //   174: aload_1
    //   175: aload 4
    //   177: invokevirtual 318	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   180: invokestatic 329	com/onesignal/ak:a	(Lcom/onesignal/ak$i;Ljava/lang/String;)V
    //   183: iconst_1
    //   184: ireturn
    //   185: iconst_0
    //   186: ireturn
    //   187: aload_1
    //   188: ifnull +9 -> 197
    //   191: aload_1
    //   192: invokeinterface 1055 1 0
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
  
  static af<Object, ai> b()
  {
    if (Y == null) {
      Y = new af("onOSSubscriptionChanged", true);
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
          aq.a(localStringBuilder.toString(), localJSONObject, new aq.a()
          {
            void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
            {
              ak.a("sending Notification Opened Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
            }
          });
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
    ap.a(ap.a, "GT_PLAYER_ID", u);
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
    ap.a(ap.a, "OS_FILTER_OTHER_GCM_RECEIVERS", paramBoolean);
  }
  
  private static ad c(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i3 = paramJSONArray.length();
    ad localAd = new ad();
    ab localAb = new ab();
    localAb.a = q();
    localAb.b = paramBoolean1;
    localAb.c = paramJSONArray.optJSONObject(0).optInt("notificationId");
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
        localAb.d = q.a((JSONObject)localObject5);
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
      if (localAb.f == null)
      {
        localObject4 = localObject3;
        localAb.f = new ArrayList();
      }
      localObject4 = localObject3;
      localAb.f.add(localAb.d);
      localObject1 = localObject3;
    }
    for (;;)
    {
      i1 += 1;
      break;
      localAd.a = localAb;
      localAd.b = new ac();
      localAd.b.b = ((String)localObject2);
      localObject3 = localAd.b;
      if (localObject2 != null) {
        paramJSONArray = ac.a.b;
      } else {
        paramJSONArray = ac.a.a;
      }
      ((ac)localObject3).a = paramJSONArray;
      if (paramBoolean2) {
        paramJSONArray = localAd.a;
      }
      for (Object localObject2 = ab.a.b;; localObject2 = ab.a.a)
      {
        paramJSONArray.e = ((ab.a)localObject2);
        return localAd;
        paramJSONArray = localAd.a;
      }
      label371:
      if (i2 == 0) {
        break label139;
      }
      i2 = 0;
      localObject2 = localObject3;
    }
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
    A = paramLong;
    if (b == null) {
      return;
    }
    i localI = i.e;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SaveUnsentActiveTime: ");
    localStringBuilder.append(A);
    a(localI, localStringBuilder.toString());
    ap.a(ap.a, "GT_UNSENT_ACTIVE_TIME", paramLong);
  }
  
  static void c(String paramString)
  {
    v = paramString;
    if (b == null) {
      return;
    }
    String str = ap.a;
    if ("".equals(v)) {
      paramString = null;
    } else {
      paramString = v;
    }
    ap.a(str, "OS_EMAIL_ID", paramString);
  }
  
  static boolean c(Context paramContext)
  {
    return ap.b(ap.a, "OS_FILTER_OTHER_GCM_RECEIVERS", false);
  }
  
  static void d(String paramString)
  {
    b(paramString);
    i();
    T();
    i(b).a(paramString);
    if (aa != null)
    {
      a(aa.a, aa.b, aa.c);
      aa = null;
    }
    ar.i();
    al.a(b, a, paramString, c.a());
  }
  
  public static boolean d()
  {
    return k();
  }
  
  private static boolean d(int paramInt)
  {
    return paramInt < -6;
  }
  
  static boolean d(Context paramContext)
  {
    return ap.b(ap.a, "GT_FIREBASE_TRACKING_ENABLED", false);
  }
  
  static void e(String paramString)
  {
    c(paramString);
    j(b).a(paramString);
    try
    {
      ar.b(new JSONObject().put("parent_player_id", paramString));
      return;
    }
    catch (JSONException paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  static boolean e()
  {
    boolean bool2 = false;
    x = false;
    p.c();
    if (!c) {
      return false;
    }
    if (C != null) {
      C.a();
    }
    if (z == -1L) {
      return false;
    }
    double d1 = SystemClock.elapsedRealtime() - z;
    Double.isNaN(d1);
    long l1 = (d1 / 1000.0D + 0.5D);
    z = SystemClock.elapsedRealtime();
    boolean bool1 = bool2;
    if (l1 >= 0L)
    {
      if (l1 > 86400L) {
        return false;
      }
      if (b == null)
      {
        a(i.c, "Android Context not found, please call OneSignal.init when your app starts.");
        return false;
      }
      bool1 = f();
      a(System.currentTimeMillis());
      l1 = p() + l1;
      c(l1);
      if ((l1 >= 60L) && (l() != null))
      {
        if (!bool1) {
          as.a(b);
        }
        as.a();
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
    return ap.b(ap.a, "GT_VIBRATE_ENABLED", true);
  }
  
  static boolean f()
  {
    boolean bool = ar.c();
    if (bool) {
      as.a(b);
    }
    return (p.a(b)) || (bool);
  }
  
  static boolean f(Context paramContext)
  {
    return ap.b(ap.a, "GT_SOUND_ENABLED", true);
  }
  
  static void g()
  {
    x = true;
    p.c();
    z = SystemClock.elapsedRealtime();
    S = V();
    a(System.currentTimeMillis());
    K();
    if (B != null) {
      B.a();
    }
    x.a(b);
    h(b).a();
    if ((D != null) && (d(b))) {
      D.b();
    }
    as.b(b);
  }
  
  private static ag h(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (V == null)
    {
      V = new ag(false);
      V.a.b(new OSPermissionChangedInternalObserver());
    }
    return V;
  }
  
  private static void h(String paramString)
  {
    if (b == null) {
      return;
    }
    ap.a(ap.a, "GT_APP_ID", paramString);
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
      aj.a(new Runnable()
      {
        public void run() {}
      });
    }
  }
  
  private static aa j(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (Z == null)
    {
      Z = new aa(false);
      Z.a.b(new z());
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
    return ap.b(ap.a, "ONESIGNAL_USER_PROVIDED_CONSENT", false);
  }
  
  static String l()
  {
    if ((u == null) && (b != null)) {
      u = ap.b(ap.a, "GT_PLAYER_ID", null);
    }
    return u;
  }
  
  private static String l(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return ap.b(ap.a, "GT_APP_ID", null);
  }
  
  static String m()
  {
    if ("".equals(v)) {
      return null;
    }
    if ((v == null) && (b != null)) {
      v = ap.b(ap.a, "OS_EMAIL_ID", null);
    }
    return v;
  }
  
  private static String m(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return ap.b(ap.a, "GT_PLAYER_ID", null);
  }
  
  private static long n(Context paramContext)
  {
    return ap.b(ap.a, "OS_LAST_SESSION_TIME", -31000L);
  }
  
  static boolean n()
  {
    if (i == null) {
      return true;
    }
    return i.i == l.c;
  }
  
  static boolean o()
  {
    a localA = i;
    boolean bool = false;
    if (localA == null) {
      return false;
    }
    if (i.i == l.b) {
      bool = true;
    }
    return bool;
  }
  
  static long p()
  {
    if ((A == -1L) && (b != null)) {
      A = ap.b(ap.a, "GT_UNSENT_ACTIVE_TIME", 0L);
    }
    i localI = i.e;
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
  
  static boolean s()
  {
    if (i.f) {
      return aj.a(b);
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
      p.a(new d(c.d, "Failed due to network failure. Will retry on next sync."));
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
      o.a(new d(c.d, "Failed due to network failure. Will retry on next sync."));
      o = null;
    }
  }
  
  public static class a
  {
    Context a;
    ak.j b;
    ak.k c;
    boolean d;
    boolean e;
    boolean f;
    boolean g;
    boolean h;
    ak.l i = ak.l.b;
    
    private a() {}
    
    private a(Context paramContext)
    {
      this.a = paramContext;
    }
    
    public a a(ak.j paramJ)
    {
      this.b = paramJ;
      return this;
    }
    
    public a a(ak.l paramL)
    {
      ak.c().h = false;
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
      ak.a(this);
    }
  }
  
  public static abstract interface b
  {
    public abstract void a(ak.m paramM);
    
    public abstract void a(JSONObject paramJSONObject);
  }
  
  public static enum c
  {
    private c() {}
  }
  
  public static class d
  {
    private ak.c a;
    private String b;
    
    d(ak.c paramC, String paramString)
    {
      this.a = paramC;
      this.b = paramString;
    }
  }
  
  public static abstract interface e
  {
    public abstract void a();
    
    public abstract void a(ak.d paramD);
  }
  
  public static abstract interface f
  {
    public abstract void a(JSONObject paramJSONObject);
  }
  
  private static class g
  {
    JSONArray a;
    boolean b;
    aq.a c;
    
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
    public abstract void a(ad paramAd);
  }
  
  public static abstract interface k
  {
    public abstract void a(ab paramAb);
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
