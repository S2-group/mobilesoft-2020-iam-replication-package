package com.onesignal;

import android.app.AlertDialog.Builder;
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
import org.json.JSONException;
import org.json.JSONObject;

public class s
{
  private static Double A;
  private static Double B;
  private static Float C;
  private static Integer D;
  private static b E;
  private static boolean F;
  static String a;
  static String b;
  static Context c;
  static int d;
  static boolean e;
  public static String f = "native";
  static a g;
  static Collection<JSONArray> h = new ArrayList();
  private static boolean i;
  private static d j = d.a;
  private static d k = d.d;
  private static String l = null;
  private static e m;
  private static boolean n;
  private static c o;
  private static long p;
  private static long q;
  private static ac r;
  private static ab s;
  private static f t;
  private static int u;
  private static r v;
  private static boolean w;
  private static String x;
  private static boolean y;
  private static boolean z;
  
  static
  {
    d = 1;
    p = 1L;
    q = -1L;
    t = new e();
  }
  
  public s() {}
  
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
  
  static void a()
  {
    n = true;
    p = SystemClock.elapsedRealtime();
    u();
    if (r != null) {
      r.a();
    }
    q.a(c);
  }
  
  static void a(long paramLong, boolean paramBoolean)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("app_id", a);
      localJSONObject.put("state", "ping");
      localJSONObject.put("active_time", paramLong);
      c(localJSONObject);
      String str = "players/" + e() + "/on_focus";
      u.a local5 = new u.a()
      {
        void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
        {
          s.a("sending on_focus Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
        }
        
        void a(String paramAnonymousString)
        {
          s.a(0L);
        }
      };
      if (paramBoolean)
      {
        u.d(str, localJSONObject, local5);
        return;
      }
      u.b(str, localJSONObject, local5);
      return;
    }
    catch (Throwable localThrowable)
    {
      a(d.c, "Generating on_focus:JSON Failed.", localThrowable);
    }
  }
  
  /* Error */
  public static void a(Context paramContext, String paramString1, String paramString2, e paramE)
  {
    // Byte code:
    //   0: getstatic 262	com/onesignal/s:g	Lcom/onesignal/s$a;
    //   3: ifnonnull +14 -> 17
    //   6: new 24	com/onesignal/s$a
    //   9: dup
    //   10: aconst_null
    //   11: invokespecial 265	com/onesignal/s$a:<init>	(Lcom/onesignal/s$1;)V
    //   14: putstatic 262	com/onesignal/s:g	Lcom/onesignal/s$a;
    //   17: new 267	com/onesignal/r
    //   20: dup
    //   21: invokespecial 268	com/onesignal/r:<init>	()V
    //   24: putstatic 270	com/onesignal/s:v	Lcom/onesignal/r;
    //   27: getstatic 270	com/onesignal/s:v	Lcom/onesignal/r;
    //   30: invokevirtual 273	com/onesignal/r:a	()I
    //   33: putstatic 275	com/onesignal/s:u	I
    //   36: aload_2
    //   37: invokestatic 281	java/util/UUID:fromString	(Ljava/lang/String;)Ljava/util/UUID;
    //   40: pop
    //   41: ldc_w 283
    //   44: aload_2
    //   45: invokevirtual 289	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   48: ifne +13 -> 61
    //   51: ldc_w 291
    //   54: aload_2
    //   55: invokevirtual 289	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   58: ifeq +12 -> 70
    //   61: getstatic 91	com/onesignal/s$d:d	Lcom/onesignal/s$d;
    //   64: ldc_w 293
    //   67: invokestatic 173	com/onesignal/s:a	(Lcom/onesignal/s$d;Ljava/lang/String;)V
    //   70: getstatic 275	com/onesignal/s:u	I
    //   73: iconst_1
    //   74: if_icmpne +69 -> 143
    //   77: aload_1
    //   78: invokestatic 299	java/lang/Double:parseDouble	(Ljava/lang/String;)D
    //   81: pop2
    //   82: aload_1
    //   83: invokevirtual 302	java/lang/String:length	()I
    //   86: bipush 8
    //   88: if_icmplt +12 -> 100
    //   91: aload_1
    //   92: invokevirtual 302	java/lang/String:length	()I
    //   95: bipush 16
    //   97: if_icmple +32 -> 129
    //   100: new 304	java/lang/IllegalArgumentException
    //   103: dup
    //   104: ldc_w 306
    //   107: invokespecial 307	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   110: athrow
    //   111: astore 5
    //   113: getstatic 309	com/onesignal/s$d:b	Lcom/onesignal/s$d;
    //   116: ldc_w 311
    //   119: aload 5
    //   121: invokestatic 178	com/onesignal/s:a	(Lcom/onesignal/s$d;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   124: bipush -6
    //   126: putstatic 97	com/onesignal/s:d	I
    //   129: ldc_w 313
    //   132: invokestatic 319	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   135: pop
    //   136: ldc_w 321
    //   139: invokestatic 319	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   142: pop
    //   143: aload_1
    //   144: putstatic 323	com/onesignal/s:b	Ljava/lang/String;
    //   147: ldc_w 325
    //   150: invokestatic 319	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   153: pop
    //   154: ldc_w 327
    //   157: invokestatic 319	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   160: pop
    //   161: ldc_w 329
    //   164: invokestatic 319	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   167: pop
    //   168: getstatic 331	com/onesignal/s:e	Z
    //   171: ifeq +124 -> 295
    //   174: aload_0
    //   175: ifnull +10 -> 185
    //   178: aload_0
    //   179: invokevirtual 337	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   182: putstatic 198	com/onesignal/s:c	Landroid/content/Context;
    //   185: aload_3
    //   186: ifnull +7 -> 193
    //   189: aload_3
    //   190: putstatic 339	com/onesignal/s:m	Lcom/onesignal/s$e;
    //   193: getstatic 339	com/onesignal/s:m	Lcom/onesignal/s$e;
    //   196: ifnull +6 -> 202
    //   199: invokestatic 341	com/onesignal/s:v	()V
    //   202: return
    //   203: astore_0
    //   204: getstatic 309	com/onesignal/s$d:b	Lcom/onesignal/s$d;
    //   207: ldc_w 343
    //   210: aload_0
    //   211: invokestatic 178	com/onesignal/s:a	(Lcom/onesignal/s$d;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   214: return
    //   215: astore 5
    //   217: getstatic 309	com/onesignal/s$d:b	Lcom/onesignal/s$d;
    //   220: ldc_w 345
    //   223: aload 5
    //   225: invokestatic 178	com/onesignal/s:a	(Lcom/onesignal/s$d;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   228: bipush -4
    //   230: putstatic 97	com/onesignal/s:d	I
    //   233: goto -97 -> 136
    //   236: astore 5
    //   238: getstatic 309	com/onesignal/s$d:b	Lcom/onesignal/s$d;
    //   241: ldc_w 347
    //   244: aload 5
    //   246: invokestatic 178	com/onesignal/s:a	(Lcom/onesignal/s$d;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   249: bipush -4
    //   251: putstatic 97	com/onesignal/s:d	I
    //   254: goto -111 -> 143
    //   257: astore_1
    //   258: getstatic 309	com/onesignal/s$d:b	Lcom/onesignal/s$d;
    //   261: ldc_w 349
    //   264: aload_1
    //   265: invokestatic 178	com/onesignal/s:a	(Lcom/onesignal/s$d;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   268: bipush -5
    //   270: putstatic 97	com/onesignal/s:d	I
    //   273: goto -105 -> 168
    //   276: astore_1
    //   277: getstatic 309	com/onesignal/s$d:b	Lcom/onesignal/s$d;
    //   280: ldc_w 351
    //   283: aload_1
    //   284: invokestatic 178	com/onesignal/s:a	(Lcom/onesignal/s$d;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   287: bipush -3
    //   289: putstatic 97	com/onesignal/s:d	I
    //   292: goto -124 -> 168
    //   295: aload_0
    //   296: instanceof 353
    //   299: istore 4
    //   301: iload 4
    //   303: putstatic 182	com/onesignal/s:n	Z
    //   306: aload_2
    //   307: putstatic 212	com/onesignal/s:a	Ljava/lang/String;
    //   310: aload_0
    //   311: invokevirtual 337	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   314: putstatic 198	com/onesignal/s:c	Landroid/content/Context;
    //   317: iload 4
    //   319: ifeq +183 -> 502
    //   322: aload_0
    //   323: checkcast 353	android/app/Activity
    //   326: putstatic 358	com/onesignal/a:b	Landroid/app/Activity;
    //   329: getstatic 198	com/onesignal/s:c	Landroid/content/Context;
    //   332: invokestatic 203	com/onesignal/q:a	(Landroid/content/Context;)V
    //   335: aload_3
    //   336: putstatic 339	com/onesignal/s:m	Lcom/onesignal/s$e;
    //   339: invokestatic 188	android/os/SystemClock:elapsedRealtime	()J
    //   342: putstatic 99	com/onesignal/s:p	J
    //   345: getstatic 198	com/onesignal/s:c	Landroid/content/Context;
    //   348: invokestatic 361	com/onesignal/v:a	(Landroid/content/Context;)V
    //   351: getstatic 198	com/onesignal/s:c	Landroid/content/Context;
    //   354: new 363	android/content/Intent
    //   357: dup
    //   358: getstatic 198	com/onesignal/s:c	Landroid/content/Context;
    //   361: ldc_w 365
    //   364: invokespecial 368	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   367: invokevirtual 372	android/content/Context:startService	(Landroid/content/Intent;)Landroid/content/ComponentName;
    //   370: pop
    //   371: getstatic 377	android/os/Build$VERSION:SDK_INT	I
    //   374: bipush 13
    //   376: if_icmple +133 -> 509
    //   379: getstatic 198	com/onesignal/s:c	Landroid/content/Context;
    //   382: checkcast 379	android/app/Application
    //   385: new 381	com/onesignal/b
    //   388: dup
    //   389: invokespecial 382	com/onesignal/b:<init>	()V
    //   392: invokevirtual 386	android/app/Application:registerActivityLifecycleCallbacks	(Landroid/app/Application$ActivityLifecycleCallbacks;)V
    //   395: ldc_w 388
    //   398: invokestatic 319	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   401: pop
    //   402: new 390	com/onesignal/ab
    //   405: dup
    //   406: getstatic 198	com/onesignal/s:c	Landroid/content/Context;
    //   409: invokespecial 392	com/onesignal/ab:<init>	(Landroid/content/Context;)V
    //   412: putstatic 394	com/onesignal/s:s	Lcom/onesignal/ab;
    //   415: invokestatic 396	com/onesignal/s:d	()Ljava/lang/String;
    //   418: astore_0
    //   419: aload_0
    //   420: ifnull +95 -> 515
    //   423: aload_0
    //   424: getstatic 212	com/onesignal/s:a	Ljava/lang/String;
    //   427: invokevirtual 289	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   430: ifne +21 -> 451
    //   433: getstatic 168	com/onesignal/s$d:f	Lcom/onesignal/s$d;
    //   436: ldc_w 398
    //   439: invokestatic 173	com/onesignal/s:a	(Lcom/onesignal/s$d;Ljava/lang/String;)V
    //   442: getstatic 212	com/onesignal/s:a	Ljava/lang/String;
    //   445: invokestatic 400	com/onesignal/s:d	(Ljava/lang/String;)V
    //   448: invokestatic 402	com/onesignal/v:e	()V
    //   451: getstatic 182	com/onesignal/s:n	Z
    //   454: ifne +9 -> 463
    //   457: invokestatic 240	com/onesignal/s:e	()Ljava/lang/String;
    //   460: ifnonnull +6 -> 466
    //   463: invokestatic 190	com/onesignal/s:u	()V
    //   466: getstatic 339	com/onesignal/s:m	Lcom/onesignal/s$e;
    //   469: ifnull +6 -> 475
    //   472: invokestatic 341	com/onesignal/s:v	()V
    //   475: getstatic 198	com/onesignal/s:c	Landroid/content/Context;
    //   478: invokestatic 405	com/onesignal/ac:a	(Landroid/content/Context;)Z
    //   481: ifeq +16 -> 497
    //   484: new 194	com/onesignal/ac
    //   487: dup
    //   488: getstatic 198	com/onesignal/s:c	Landroid/content/Context;
    //   491: invokespecial 406	com/onesignal/ac:<init>	(Landroid/content/Context;)V
    //   494: putstatic 192	com/onesignal/s:r	Lcom/onesignal/ac;
    //   497: iconst_1
    //   498: putstatic 331	com/onesignal/s:e	Z
    //   501: return
    //   502: iconst_1
    //   503: putstatic 408	com/onesignal/a:a	Z
    //   506: goto -171 -> 335
    //   509: invokestatic 411	com/onesignal/c:a	()V
    //   512: goto -117 -> 395
    //   515: iconst_0
    //   516: getstatic 198	com/onesignal/s:c	Landroid/content/Context;
    //   519: invokestatic 416	com/onesignal/i:a	(ILandroid/content/Context;)V
    //   522: getstatic 212	com/onesignal/s:a	Ljava/lang/String;
    //   525: invokestatic 400	com/onesignal/s:d	(Ljava/lang/String;)V
    //   528: goto -77 -> 451
    //   531: astore_0
    //   532: goto -117 -> 415
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	535	0	paramContext	Context
    //   0	535	1	paramString1	String
    //   0	535	2	paramString2	String
    //   0	535	3	paramE	e
    //   299	19	4	bool	boolean
    //   111	9	5	localThrowable	Throwable
    //   215	9	5	localClassNotFoundException1	ClassNotFoundException
    //   236	9	5	localClassNotFoundException2	ClassNotFoundException
    // Exception table:
    //   from	to	target	type
    //   77	100	111	java/lang/Throwable
    //   100	111	111	java/lang/Throwable
    //   36	41	203	java/lang/Throwable
    //   129	136	215	java/lang/ClassNotFoundException
    //   136	143	236	java/lang/ClassNotFoundException
    //   154	168	257	java/lang/ClassNotFoundException
    //   147	154	276	java/lang/ClassNotFoundException
    //   258	273	276	java/lang/ClassNotFoundException
    //   395	415	531	java/lang/ClassNotFoundException
  }
  
  public static void a(Context paramContext, JSONArray paramJSONArray, boolean paramBoolean)
  {
    b(paramContext, paramJSONArray);
    boolean bool2 = "DISABLE".equals(r.a(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
    if (!bool2) {}
    for (boolean bool1 = a(paramContext, paramJSONArray);; bool1 = false)
    {
      a(paramJSONArray, false);
      if ((!paramBoolean) && (!bool1)) {
        b(paramContext, paramJSONArray, bool2);
      }
      return;
    }
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
        if (!s.r()) {}
        v.a localA;
        for (boolean bool = true;; bool = false)
        {
          localA = v.b(bool);
          if (localA.a) {
            s.d(true);
          }
          if ((localA.b != null) && (!localA.toString().equals("{}"))) {
            break;
          }
          this.a.a(null);
          return;
        }
        this.a.a(localA.b);
      }
    }).start();
  }
  
  public static void a(c paramC)
  {
    o = paramC;
    if (e() != null) {
      z();
    }
  }
  
  static void a(d paramD, String paramString)
  {
    a(paramD, paramString, null);
  }
  
  static void a(d paramD, final String paramString, Throwable paramThrowable)
  {
    if (paramD.compareTo(k) < 1)
    {
      if (paramD != d.g) {
        break label148;
      }
      Log.v("OneSignal", paramString, paramThrowable);
    }
    for (;;)
    {
      if ((paramD.compareTo(j) < 1) && (a.b != null)) {}
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
    l = paramString;
    if (c == null) {
      return;
    }
    paramString = f(c).edit();
    paramString.putString("GT_PLAYER_ID", l);
    paramString.commit();
  }
  
  public static void a(String paramString1, String paramString2)
  {
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
  
  private static void a(String paramString, final JSONObject paramJSONObject, final boolean paramBoolean)
  {
    if (Looper.getMainLooper().getThread() == Thread.currentThread())
    {
      m.a(paramString, paramJSONObject, paramBoolean);
      return;
    }
    a(new Runnable()
    {
      public void run()
      {
        s.t().a(this.a, paramJSONObject, paramBoolean);
      }
    });
  }
  
  static void a(JSONArray paramJSONArray)
  {
    b(c, paramJSONArray);
    a(paramJSONArray, true);
  }
  
  private static void a(JSONArray paramJSONArray, boolean paramBoolean)
  {
    if (m == null)
    {
      h.add(paramJSONArray);
      return;
    }
    int i2 = paramJSONArray.length();
    int i1 = 0;
    Object localObject2 = null;
    Object localObject5 = null;
    Object localObject4;
    if (i1 < i2) {
      localObject4 = localObject2;
    }
    for (;;)
    {
      try
      {
        localJSONObject = paramJSONArray.getJSONObject(i1);
        localObject4 = localObject2;
        if (!localJSONObject.has("custom")) {
          break label566;
        }
        localObject4 = localObject2;
        localObject3 = new JSONObject(localJSONObject.optString("custom"));
        localObject4 = localObject2;
        localObject1 = new JSONObject();
        localObject4 = localObject2;
        if (((JSONObject)localObject3).has("a"))
        {
          localObject4 = localObject2;
          localObject1 = ((JSONObject)localObject3).optJSONObject("a");
        }
        localObject4 = localObject2;
        if (localJSONObject.has("title"))
        {
          localObject4 = localObject2;
          ((JSONObject)localObject1).put("title", localJSONObject.optString("title"));
        }
        localObject4 = localObject2;
        if (((JSONObject)localObject3).has("u"))
        {
          localObject4 = localObject2;
          ((JSONObject)localObject1).put("launchURL", ((JSONObject)localObject3).optString("u"));
        }
        localObject4 = localObject2;
        if (localJSONObject.has("sound"))
        {
          localObject4 = localObject2;
          ((JSONObject)localObject1).put("sound", localJSONObject.optString("sound"));
        }
        localObject4 = localObject2;
        if (localJSONObject.has("sicon"))
        {
          localObject4 = localObject2;
          ((JSONObject)localObject1).put("smallIcon", localJSONObject.optString("sicon"));
        }
        localObject4 = localObject2;
        if (localJSONObject.has("licon"))
        {
          localObject4 = localObject2;
          ((JSONObject)localObject1).put("largeIcon", localJSONObject.optString("licon"));
        }
        localObject4 = localObject2;
        if (localJSONObject.has("bicon"))
        {
          localObject4 = localObject2;
          ((JSONObject)localObject1).put("bigPicture", localJSONObject.optString("bicon"));
        }
        localObject4 = localObject2;
        boolean bool = localObject1.equals(new JSONObject());
        if (bool) {
          localObject1 = null;
        }
        if (localObject5 != null) {}
      }
      catch (Throwable localThrowable1)
      {
        JSONObject localJSONObject;
        Object localObject3;
        localObject1 = localObject4;
      }
      try
      {
        localObject2 = localJSONObject.optString("alert", null);
        localObject5 = localObject2;
        i1 += 1;
        localObject2 = localObject1;
      }
      catch (Throwable localThrowable2)
      {
        for (;;) {}
      }
      localObject3 = localObject2;
      if (localObject2 == null)
      {
        localObject4 = localObject2;
        localObject3 = new JSONObject();
      }
      localObject4 = localObject3;
      if (!((JSONObject)localObject3).has("stacked_notifications"))
      {
        localObject4 = localObject3;
        ((JSONObject)localObject3).put("stacked_notifications", new JSONArray());
      }
      localObject4 = localObject3;
      ((JSONObject)localObject1).put("message", localJSONObject.optString("alert", null));
      localObject4 = localObject3;
      ((JSONObject)localObject3).getJSONArray("stacked_notifications").put(localObject1);
      Object localObject1 = localObject3;
      continue;
      a(d.c, "Error parsing JSON item " + i1 + "/" + i2 + " for callback.", localThrowable1);
      continue;
      a(localObject5, localThrowable1, paramBoolean);
      return;
      label566:
      localObject1 = null;
    }
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean, u.a paramA)
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
      u.b("players/" + e() + "/on_purchase", localJSONObject, paramA);
      return;
    }
    catch (Throwable paramJSONArray)
    {
      a(d.c, "Failed to generate JSON for sendPurchases.", paramJSONArray);
    }
  }
  
  public static void a(JSONObject paramJSONObject)
  {
    if (c == null) {
      a(d.c, "You must initialize OneSignal before modifying tags! Omitting this tag operation.");
    }
    while (paramJSONObject == null) {
      return;
    }
    JSONObject localJSONObject1 = v.b(false).b;
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
      v.a(localJSONObject2);
      return;
    }
  }
  
  static void a(boolean paramBoolean)
  {
    n = false;
    if (!e) {}
    long l1;
    do
    {
      do
      {
        return;
        if (s != null) {
          s.a();
        }
      } while (p == -1L);
      l1 = ((SystemClock.elapsedRealtime() - p) / 1000.0D + 0.5D);
      p = SystemClock.elapsedRealtime();
    } while ((l1 < 0L) || (l1 > 604800L));
    if (c == null)
    {
      a(d.c, "Android Context not found, please call OneSignal.init when your app starts.");
      return;
    }
    l1 += f();
    if ((paramBoolean) || (l1 < 60L) || (e() == null))
    {
      b(l1);
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
    paramJSONObject = b(paramJSONObject);
    return (paramJSONObject == null) || (a(paramJSONObject, paramContext));
  }
  
  private static boolean a(d paramD)
  {
    return (paramD.compareTo(j) < 1) || (paramD.compareTo(k) < 1);
  }
  
  static boolean a(String paramString, Context paramContext)
  {
    if ((paramString == null) || ("".equals(paramString))) {
      return false;
    }
    paramContext = new t(paramContext).getReadableDatabase();
    Cursor localCursor = paramContext.query("notification", new String[] { "notification_id" }, "notification_id = ?", new String[] { paramString }, null, null, null);
    boolean bool = localCursor.moveToFirst();
    localCursor.close();
    paramContext.close();
    if (bool)
    {
      a(d.f, "Duplicate GCM message received, skipping processing. " + paramString);
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
  
  private static void b(long paramLong)
  {
    q = paramLong;
    if (c == null) {
      return;
    }
    a(d.e, "SaveUnsentActiveTime: " + q);
    SharedPreferences.Editor localEditor = f(c).edit();
    localEditor.putLong("GT_UNSENT_ACTIVE_TIME", paramLong);
    localEditor.commit();
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
            break label154;
          }
          localObject = new JSONObject(((JSONObject)localObject).optString("custom", null));
          if (!((JSONObject)localObject).has("i")) {
            break label154;
          }
          localObject = ((JSONObject)localObject).optString("i", null);
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("app_id", g(paramContext));
          localJSONObject.put("player_id", h(paramContext));
          localJSONObject.put("opened", true);
          u.a("notifications/" + (String)localObject, localJSONObject, new u.a()
          {
            void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
            {
              s.a("sending Notification Opened Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
            }
          });
        }
        catch (Throwable localThrowable)
        {
          a(d.c, "Failed to generate JSON to send notification opened.", localThrowable);
        }
      }
      return;
      label154:
      i1 += 1;
    }
  }
  
  private static void b(Context paramContext, JSONArray paramJSONArray, boolean paramBoolean)
  {
    int i2 = 1;
    PackageManager localPackageManager = paramContext.getPackageManager();
    int i1 = 0;
    Intent localIntent = new Intent().setAction("com.onesignal.NotificationOpened.RECEIVE").setPackage(paramContext.getPackageName());
    if (localPackageManager.queryBroadcastReceivers(localIntent, 32).size() > 0)
    {
      localIntent.putExtra("onesignal_data", paramJSONArray.toString());
      paramContext.sendBroadcast(localIntent);
      i1 = 1;
    }
    if (localPackageManager.queryIntentActivities(localIntent, 65536).size() > 0)
    {
      if (i1 == 0) {
        localIntent.putExtra("onesignal_data", paramJSONArray.toString());
      }
      localIntent.setFlags(268566528);
      paramContext.startActivity(localIntent);
      i1 = i2;
    }
    for (;;)
    {
      if ((i1 == 0) && (!paramBoolean))
      {
        paramJSONArray = paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
        if (paramJSONArray != null)
        {
          paramJSONArray.setFlags(268566528);
          paramContext.startActivity(paramJSONArray);
        }
      }
      return;
    }
  }
  
  private static void b(a paramA)
  {
    g = paramA;
    paramA = g.a;
    g.a = null;
    try
    {
      Bundle localBundle = paramA.getPackageManager().getApplicationInfo(paramA.getPackageName(), 128).metaData;
      a(paramA, localBundle.getString("onesignal_google_project_number").substring(4), localBundle.getString("onesignal_app_id"), g.b);
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
    a(E);
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
  
  static boolean b()
  {
    return n;
  }
  
  static boolean b(Context paramContext)
  {
    return f(paramContext).getBoolean("GT_VIBRATE_ENABLED", true);
  }
  
  static void c()
  {
    if (o != null) {
      a(new Runnable()
      {
        public void run() {}
      });
    }
  }
  
  private static void c(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject.put("net_type", v.b());
      return;
    }
    catch (Throwable paramJSONObject) {}
  }
  
  static boolean c(Context paramContext)
  {
    return f(paramContext).getBoolean("GT_SOUND_ENABLED", true);
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
    SharedPreferences.Editor localEditor = f(c).edit();
    localEditor.putString("GT_APP_ID", paramString);
    localEditor.commit();
  }
  
  static boolean d(Context paramContext)
  {
    return f(paramContext).getBoolean("ONESIGNAL_ALWAYS_SHOW_NOTIF", false);
  }
  
  static String e()
  {
    if ((l == null) && (c != null)) {
      l = f(c).getString("GT_PLAYER_ID", null);
    }
    return l;
  }
  
  static boolean e(Context paramContext)
  {
    return f(paramContext).getBoolean("ONESIGNAL_INAPP_ALERT", false);
  }
  
  static long f()
  {
    if ((q == -1L) && (c != null)) {
      q = f(c).getLong("GT_UNSENT_ACTIVE_TIME", 0L);
    }
    a(d.e, "GetUnsentActiveTime: " + q);
    return q;
  }
  
  static SharedPreferences f(Context paramContext)
  {
    return paramContext.getSharedPreferences(s.class.getSimpleName(), 0);
  }
  
  private static String g(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return f(paramContext).getString("GT_APP_ID", null);
  }
  
  static boolean g()
  {
    return (e) && (b());
  }
  
  private static String h(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return f(paramContext).getString("GT_PLAYER_ID", null);
  }
  
  private static void u()
  {
    if (i) {
      return;
    }
    i = true;
    if (u == 2) {}
    for (Object localObject = new x();; localObject = new y())
    {
      ((w)localObject).a(c, b, new w.a()
      {
        public void a(String paramAnonymousString)
        {
          s.c(paramAnonymousString);
          s.b(true);
          s.h();
        }
      });
      l.a(c, g.c, new l.b()
      {
        public void a(Double paramAnonymousDouble1, Double paramAnonymousDouble2, Float paramAnonymousFloat, Integer paramAnonymousInteger)
        {
          s.a(paramAnonymousDouble1);
          s.b(paramAnonymousDouble2);
          s.a(paramAnonymousFloat);
          s.a(paramAnonymousInteger);
          s.c(true);
          s.h();
        }
      });
      return;
    }
  }
  
  private static void v()
  {
    Iterator localIterator = h.iterator();
    while (localIterator.hasNext()) {
      a((JSONArray)localIterator.next(), false);
    }
    h.clear();
  }
  
  private static void w()
  {
    String str = v.d();
    if ((x != null) && (!x.equals(str)))
    {
      v.a(x);
      c();
    }
  }
  
  private static int x()
  {
    TimeZone localTimeZone = Calendar.getInstance().getTimeZone();
    int i2 = localTimeZone.getRawOffset();
    int i1 = i2;
    if (localTimeZone.inDaylightTime(new Date())) {
      i1 = i2 + localTimeZone.getDSTSavings();
    }
    return i1 / 1000;
  }
  
  private static void y()
  {
    a(d.f, "registerUser: registerForPushFired:" + y + ", locationFired: " + z);
    if ((!y) || (!z)) {
      return;
    }
    if (w)
    {
      w();
      return;
    }
    w = true;
    new Thread(new Runnable()
    {
      public void run()
      {
        int i = 0;
        v.c localC = v.b();
        String str = s.c.getPackageName();
        Object localObject3 = s.c.getPackageManager();
        localC.a("app_id", s.a);
        localC.a("identifier", s.i());
        Object localObject2 = s.j().a(s.c);
        Object localObject1 = localObject2;
        if (localObject2 == null) {
          localObject1 = new d().a(s.c);
        }
        localC.a("ad_id", localObject1);
        localC.a("device_os", Build.VERSION.RELEASE);
        localC.a("timezone", Integer.valueOf(s.k()));
        localC.a("language", r.d());
        localC.a("sdk", "020602");
        localC.a("sdk_type", s.f);
        localC.a("android_package", str);
        localC.a("device_model", Build.MODEL);
        localC.a("device_type", Integer.valueOf(s.l()));
        localC.b("subscribableStatus", Integer.valueOf(s.d));
        try
        {
          localC.a("game_version", Integer.valueOf(((PackageManager)localObject3).getPackageInfo(str, 0).versionCode));
          try
          {
            localObject1 = ((PackageManager)localObject3).getInstalledPackages(0);
            localObject2 = new JSONArray();
            localObject3 = MessageDigest.getInstance("SHA-256");
            if (i < ((List)localObject1).size())
            {
              if (((((PackageInfo)((List)localObject1).get(i)).applicationInfo.flags & 0x1) != 0) || (str.equals(((PackageInfo)((List)localObject1).get(i)).packageName))) {
                break label443;
              }
              ((MessageDigest)localObject3).update(((PackageInfo)((List)localObject1).get(i)).packageName.getBytes());
              ((JSONArray)localObject2).put(Base64.encodeToString(((MessageDigest)localObject3).digest(), 2));
              break label443;
            }
            localC.a("pkgs", localObject2);
          }
          catch (Throwable localThrowable)
          {
            for (;;) {}
          }
          localObject1 = s.f(s.c).getString("OS_USER_EMAIL", null);
          if (localObject1 != null) {
            localC.a("email", localObject1);
          }
          localC.a("net_type", s.m().b());
          localC.a("carrier", s.m().c());
          localC.a("rooted", Boolean.valueOf(aa.a()));
          localC.a("lat", s.n());
          localC.a("long", s.o());
          localC.a("loc_acc", s.p());
          localC.a("loc_type", s.q());
          v.a(localC);
          return;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            continue;
            label443:
            i += 1;
          }
        }
      }
    }).start();
  }
  
  private static void z()
  {
    if (o == null) {}
    String str1;
    do
    {
      String str2;
      do
      {
        return;
        str1 = v.d();
        if (!v.c()) {
          str1 = null;
        }
        str2 = e();
      } while (str2 == null);
      o.a(str2, str1);
    } while (str1 == null);
    o = null;
  }
  
  public static class a
  {
    Context a;
    s.e b;
    boolean c;
    boolean d;
    
    private a() {}
    
    private a(Context paramContext)
    {
      this.a = paramContext;
    }
    
    public a a(s.e paramE)
    {
      this.b = paramE;
      return this;
    }
    
    public void a()
    {
      s.a(this);
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
    public abstract void a(String paramString, JSONObject paramJSONObject, boolean paramBoolean);
  }
}
