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

public class OneSignal
{
  private static long A;
  private static aw B;
  private static au C;
  private static av D;
  private static d E;
  private static int F;
  private static ae G;
  private static String H;
  private static boolean I;
  private static boolean J;
  private static boolean K;
  private static boolean L;
  private static LocationGMS.e M;
  private static Collection<JSONArray> N;
  private static HashSet<String> O;
  private static ArrayList<e> P;
  private static boolean Q;
  private static boolean R;
  private static boolean S;
  private static JSONObject T;
  private static boolean U;
  private static ab V;
  private static aa<Object, ac> W;
  private static OSSubscriptionState X;
  private static aa<Object, ad> Y;
  private static x Z;
  static String a;
  private static f aa;
  private static an ab;
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
  static ab m;
  static OSSubscriptionState n;
  private static d o;
  private static d p;
  private static String q;
  private static boolean r;
  private static LOG_LEVEL s = LOG_LEVEL.a;
  private static LOG_LEVEL t = LOG_LEVEL.d;
  private static String u = null;
  private static String v = null;
  private static int w;
  private static boolean x;
  private static g y;
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
  
  public OneSignal() {}
  
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
    ExecutorService localExecutorService = d;
    return (localExecutorService != null) && (!localExecutorService.isShutdown());
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
  
  private static an M()
  {
    an localAn = ab;
    if (localAn != null) {
      return localAn;
    }
    if (F == 2) {
      ab = new ao();
    } else if (ae.a()) {
      ab = new aq();
    } else {
      ab = new ar();
    }
    return ab;
  }
  
  private static void N()
  {
    M().a(b, q, new an.a()
    {
      public void a(String paramAnonymousString, int paramAnonymousInt)
      {
        if (paramAnonymousInt < 1)
        {
          if ((al.g() == null) && ((OneSignal.y() == 1) || (OneSignal.b(OneSignal.y())))) {
            OneSignal.c(paramAnonymousInt);
          }
        }
        else if (OneSignal.b(OneSignal.y())) {
          OneSignal.c(paramAnonymousInt);
        }
        OneSignal.f(paramAnonymousString);
        OneSignal.d(true);
        OneSignal.g(OneSignal.b).b(paramAnonymousString);
        OneSignal.x();
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
    ak.a local9 = new ak.a()
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
    ak.a((String)localObject, local9);
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
            af.a(OneSignal.b, OneSignal.a, OneSignal.E(), c.a());
            return;
          }
          catch (JSONException localJSONException)
          {
            OneSignal.a(OneSignal.LOG_LEVEL.b, "FATAL Error registering device!", localJSONException);
          }
        }
      }, "OS_REG_USER").start();
      return;
    }
  }
  
  private static void S()
  {
    Object localObject3 = b.getPackageName();
    Object localObject2 = b.getPackageManager();
    Object localObject1 = new JSONObject();
    ((JSONObject)localObject1).put("app_id", a);
    Object localObject4 = E.a(b);
    if (localObject4 != null) {
      ((JSONObject)localObject1).put("ad_id", localObject4);
    }
    ((JSONObject)localObject1).put("device_os", Build.VERSION.RELEASE);
    ((JSONObject)localObject1).put("timezone", Q());
    ((JSONObject)localObject1).put("language", ae.f());
    ((JSONObject)localObject1).put("sdk", "031006");
    ((JSONObject)localObject1).put("sdk_type", g);
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
        if (T.has(str)) {
          ((JSONArray)localObject3).put(str);
        }
      }
      else
      {
        ((JSONObject)localObject1).put("pkgs", localObject3);
        ((JSONObject)localObject1).put("net_type", G.d());
        ((JSONObject)localObject1).put("carrier", G.e());
        ((JSONObject)localObject1).put("rooted", at.a());
        al.a((JSONObject)localObject1);
        localObject1 = new JSONObject();
        ((JSONObject)localObject1).put("identifier", H);
        ((JSONObject)localObject1).put("subscribableStatus", w);
        ((JSONObject)localObject1).put("androidPermission", s());
        ((JSONObject)localObject1).put("device_type", F);
        al.b((JSONObject)localObject1);
        if (h)
        {
          localObject1 = M;
          if (localObject1 != null) {
            al.a((LocationGMS.e)localObject1);
          }
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
          bc.a localA = al.c(OneSignal.F() ^ true);
          if (localA.a) {
            OneSignal.h(true);
          }
          synchronized (OneSignal.G())
          {
            Iterator localIterator = OneSignal.G().iterator();
            while (localIterator.hasNext())
            {
              OneSignal.e localE = (OneSignal.e)localIterator.next();
              if ((localA.b != null) && (!localA.toString().equals("{}"))) {
                localE.a(localA.b);
              } else {
                localE.a(null);
              }
            }
            OneSignal.G().clear();
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
  
  private static boolean V()
  {
    if (S) {
      return true;
    }
    return (System.currentTimeMillis() - n(b)) / 1000L >= 30L;
  }
  
  static aa<Object, ac> a()
  {
    if (W == null) {
      W = new aa("onOSPermissionChanged", true);
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
        a(LOG_LEVEL.f, "Not a OneSignal formatted GCM message. No 'i' field in custom.");
        return null;
      }
      a(LOG_LEVEL.f, "Not a OneSignal formatted GCM message. No 'custom' field in the bundle.");
      return null;
    }
    catch (Throwable paramBundle)
    {
      a(LOG_LEVEL.f, "Could not parse bundle, probably not a OneSignal notification.", paramBundle);
    }
    return null;
  }
  
  public static void a(int paramInt)
  {
    Runnable local7 = new Runnable()
    {
      /* Error */
      public void run()
      {
        // Byte code:
        //   0: getstatic 26	com/onesignal/OneSignal:b	Landroid/content/Context;
        //   3: invokestatic 31	com/onesignal/ah:a	(Landroid/content/Context;)Lcom/onesignal/ah;
        //   6: astore_1
        //   7: aload_1
        //   8: invokevirtual 34	com/onesignal/ah:a	()Landroid/database/sqlite/SQLiteDatabase;
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
        //   41: getfield 15	com/onesignal/OneSignal$7:a	I
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
        //   141: getstatic 26	com/onesignal/OneSignal:b	Landroid/content/Context;
        //   144: aload_2
        //   145: aload_0
        //   146: getfield 15	com/onesignal/OneSignal$7:a	I
        //   149: invokestatic 89	com/onesignal/v:a	(Landroid/content/Context;Landroid/database/sqlite/SQLiteDatabase;I)V
        //   152: aload_2
        //   153: astore_1
        //   154: aload_2
        //   155: getstatic 26	com/onesignal/OneSignal:b	Landroid/content/Context;
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
        //   197: getstatic 106	com/onesignal/OneSignal$LOG_LEVEL:c	Lcom/onesignal/OneSignal$LOG_LEVEL;
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
        //   228: getfield 15	com/onesignal/OneSignal$7:a	I
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
        //   255: invokestatic 113	com/onesignal/OneSignal:a	(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   258: aload_2
        //   259: ifnull +20 -> 279
        //   262: aload_2
        //   263: invokevirtual 100	android/database/sqlite/SQLiteDatabase:endTransaction	()V
        //   266: goto +13 -> 279
        //   269: astore_1
        //   270: getstatic 106	com/onesignal/OneSignal$LOG_LEVEL:c	Lcom/onesignal/OneSignal$LOG_LEVEL;
        //   273: ldc 115
        //   275: aload_1
        //   276: invokestatic 113	com/onesignal/OneSignal:a	(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   279: getstatic 26	com/onesignal/OneSignal:b	Landroid/content/Context;
        //   282: ldc 80
        //   284: invokevirtual 121	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
        //   287: checkcast 123	android/app/NotificationManager
        //   290: aload_0
        //   291: getfield 15	com/onesignal/OneSignal$7:a	I
        //   294: invokevirtual 126	android/app/NotificationManager:cancel	(I)V
        //   297: return
        //   298: aload_1
        //   299: ifnull +20 -> 319
        //   302: aload_1
        //   303: invokevirtual 100	android/database/sqlite/SQLiteDatabase:endTransaction	()V
        //   306: goto +13 -> 319
        //   309: astore_1
        //   310: getstatic 106	com/onesignal/OneSignal$LOG_LEVEL:c	Lcom/onesignal/OneSignal$LOG_LEVEL;
        //   313: ldc 115
        //   315: aload_1
        //   316: invokestatic 113	com/onesignal/OneSignal:a	(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
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
    LOG_LEVEL localLOG_LEVEL = LOG_LEVEL.c;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("OneSignal.init has not been called. Could not clear notification id: ");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" at this time - movingthis operation to a waiting task queue. The notification will still be canceledfrom NotificationManager at this time.");
    a(localLOG_LEVEL, localStringBuilder.toString());
    e.add(local7);
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
  
  public static void a(Context paramContext)
  {
    if (paramContext == null)
    {
      a(LOG_LEVEL.d, "setAppContext(null) is not valid, ignoring!");
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
      aj.b();
    }
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
    a localA = i;
    localA.h = false;
    localA.b = paramH;
    localA.c = paramI;
    if (!r) {
      q = paramString1;
    }
    G = new ae();
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
      u.a(b);
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
        S = V();
        a(System.currentTimeMillis());
        K();
      }
      if (i.b != null) {
        P();
      }
      if (aw.a(b)) {
        B = new aw(b);
      }
      if (av.a()) {
        D = new av(b);
      }
      aq.a(b);
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
    boolean bool2 = "DISABLE".equals(ae.a(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
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
        ae.a(new Runnable()
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
  
  private static void a(y paramY)
  {
    ae.a(new Runnable()
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
      paramJSONArray = aa;
      paramJSONArray.b = paramBoolean;
      paramJSONArray.c = paramA;
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
    a localA = i;
    if (localA != null)
    {
      if (localA.c == null) {
        return;
      }
      i.c.a(paramJSONArray.a);
      return;
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
    //   1: ifnull +189 -> 190
    //   4: ldc_w 1030
    //   7: aload_0
    //   8: invokevirtual 800	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   11: ifeq +5 -> 16
    //   14: iconst_0
    //   15: ireturn
    //   16: aload_1
    //   17: invokestatic 1035	com/onesignal/ah:a	(Landroid/content/Context;)Lcom/onesignal/ah;
    //   20: astore 5
    //   22: aconst_null
    //   23: astore 4
    //   25: aconst_null
    //   26: astore_1
    //   27: aload 5
    //   29: invokevirtual 1038	com/onesignal/ah:b	()Landroid/database/sqlite/SQLiteDatabase;
    //   32: ldc_w 1040
    //   35: iconst_1
    //   36: anewarray 505	java/lang/String
    //   39: dup
    //   40: iconst_0
    //   41: ldc_w 1042
    //   44: aastore
    //   45: ldc_w 1044
    //   48: iconst_1
    //   49: anewarray 505	java/lang/String
    //   52: dup
    //   53: iconst_0
    //   54: aload_0
    //   55: aastore
    //   56: aconst_null
    //   57: aconst_null
    //   58: aconst_null
    //   59: invokevirtual 1050	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   62: astore 5
    //   64: aload 5
    //   66: astore_1
    //   67: aload 5
    //   69: astore 4
    //   71: aload 5
    //   73: invokeinterface 1055 1 0
    //   78: istore_3
    //   79: iload_3
    //   80: istore_2
    //   81: aload 5
    //   83: ifnull +49 -> 132
    //   86: aload 5
    //   88: invokeinterface 1058 1 0
    //   93: iload_3
    //   94: istore_2
    //   95: goto +37 -> 132
    //   98: astore_0
    //   99: goto +79 -> 178
    //   102: astore 5
    //   104: aload 4
    //   106: astore_1
    //   107: getstatic 660	com/onesignal/OneSignal$LOG_LEVEL:c	Lcom/onesignal/OneSignal$LOG_LEVEL;
    //   110: ldc_w 1060
    //   113: aload 5
    //   115: invokestatic 649	com/onesignal/OneSignal:a	(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   118: aload 4
    //   120: ifnull +10 -> 130
    //   123: aload 4
    //   125: invokeinterface 1058 1 0
    //   130: iconst_0
    //   131: istore_2
    //   132: iload_2
    //   133: ifeq +43 -> 176
    //   136: getstatic 327	com/onesignal/OneSignal$LOG_LEVEL:f	Lcom/onesignal/OneSignal$LOG_LEVEL;
    //   139: astore_1
    //   140: new 307	java/lang/StringBuilder
    //   143: dup
    //   144: invokespecial 308	java/lang/StringBuilder:<init>	()V
    //   147: astore 4
    //   149: aload 4
    //   151: ldc_w 1062
    //   154: invokevirtual 314	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   157: pop
    //   158: aload 4
    //   160: aload_0
    //   161: invokevirtual 314	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: pop
    //   165: aload_1
    //   166: aload 4
    //   168: invokevirtual 321	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   171: invokestatic 332	com/onesignal/OneSignal:a	(Lcom/onesignal/OneSignal$LOG_LEVEL;Ljava/lang/String;)V
    //   174: iconst_1
    //   175: ireturn
    //   176: iconst_0
    //   177: ireturn
    //   178: aload_1
    //   179: ifnull +9 -> 188
    //   182: aload_1
    //   183: invokeinterface 1058 1 0
    //   188: aload_0
    //   189: athrow
    //   190: iconst_0
    //   191: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	192	0	paramString	String
    //   0	192	1	paramContext	Context
    //   80	53	2	bool1	boolean
    //   78	16	3	bool2	boolean
    //   23	144	4	localObject1	Object
    //   20	67	5	localObject2	Object
    //   102	12	5	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   27	64	98	finally
    //   71	79	98	finally
    //   107	118	98	finally
    //   27	64	102	java/lang/Throwable
    //   71	79	102	java/lang/Throwable
  }
  
  public static a b(Context paramContext)
  {
    return new a(paramContext, null);
  }
  
  static aa<Object, ad> b()
  {
    if (Y == null) {
      Y = new aa("onOSSubscriptionChanged", true);
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
          localJSONObject.put("player_id", m(paramContext));
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
    a localA = i;
    if ((localA != null) && (localA.b != null))
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
  
  private static y c(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    int i3 = paramJSONArray.length();
    y localY = new y();
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
      localY.a = localOSNotification;
      localY.b = new OSNotificationAction();
      localY.b.b = localObject2;
      localObject3 = localY.b;
      if (localObject2 != null) {
        paramJSONArray = OSNotificationAction.ActionType.b;
      } else {
        paramJSONArray = OSNotificationAction.ActionType.a;
      }
      ((OSNotificationAction)localObject3).a = paramJSONArray;
      if (paramBoolean2)
      {
        localY.a.e = OSNotification.DisplayType.b;
        return localY;
      }
      localY.a.e = OSNotification.DisplayType.a;
      return localY;
      label365:
      if (i2 == 0) {
        break label139;
      }
      i2 = 0;
      Object localObject2 = localObject3;
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
  
  static boolean c(Context paramContext)
  {
    return aj.b(aj.a, "OS_FILTER_OTHER_GCM_RECEIVERS", false);
  }
  
  static void d(String paramString)
  {
    b(paramString);
    i();
    T();
    i(b).a(paramString);
    f localF = aa;
    if (localF != null)
    {
      a(localF.a, aa.b, aa.c);
      aa = null;
    }
    al.i();
    af.a(b, a, paramString, c.a());
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
  
  static boolean e()
  {
    boolean bool1 = false;
    x = false;
    LocationGMS.c();
    if (!c) {
      return false;
    }
    au localAu = C;
    if (localAu != null) {
      localAu.a();
    }
    if (z == -1L) {
      return false;
    }
    double d1 = SystemClock.elapsedRealtime() - z;
    Double.isNaN(d1);
    long l1 = (d1 / 1000.0D + 0.5D);
    z = SystemClock.elapsedRealtime();
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
      boolean bool2 = f();
      a(System.currentTimeMillis());
      l1 = p() + l1;
      c(l1);
      if ((l1 >= 60L) && (l() != null))
      {
        if (!bool2) {
          am.a(b);
        }
        am.a();
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
    S = V();
    a(System.currentTimeMillis());
    K();
    aw localAw = B;
    if (localAw != null) {
      localAw.a();
    }
    u.a(b);
    h(b).a();
    if ((D != null) && (d(b))) {
      D.b();
    }
    am.b(b);
  }
  
  private static ab h(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (V == null)
    {
      V = new ab(false);
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
      ae.a(new Runnable()
      {
        public void run() {}
      });
    }
  }
  
  private static x j(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    if (Z == null)
    {
      Z = new x(false);
      Z.a.b(new w());
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
    return aj.b(aj.a, "ONESIGNAL_USER_PROVIDED_CONSENT", false);
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
  
  private static String m(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return aj.b(aj.a, "GT_PLAYER_ID", null);
  }
  
  private static long n(Context paramContext)
  {
    return aj.b(aj.a, "OS_LAST_SESSION_TIME", -31000L);
  }
  
  static boolean n()
  {
    a localA = i;
    if (localA == null) {
      return true;
    }
    return localA.i == OSInFocusDisplayOption.c;
  }
  
  static boolean o()
  {
    a localA = i;
    boolean bool = false;
    if (localA == null) {
      return false;
    }
    if (localA.i == OSInFocusDisplayOption.b) {
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
      return ae.a(b);
    }
    return true;
  }
  
  static void t()
  {
    d localD = p;
    if (localD != null)
    {
      localD.a();
      p = null;
    }
  }
  
  static void u()
  {
    d localD = p;
    if (localD != null)
    {
      localD.a(new c(EmailErrorType.d, "Failed due to network failure. Will retry on next sync."));
      p = null;
    }
  }
  
  static void v()
  {
    d localD = o;
    if (localD != null)
    {
      localD.a();
      o = null;
    }
  }
  
  static void w()
  {
    d localD = o;
    if (localD != null)
    {
      localD.a(new c(EmailErrorType.d, "Failed due to network failure. Will retry on next sync."));
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
    public abstract void a(y paramY);
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
