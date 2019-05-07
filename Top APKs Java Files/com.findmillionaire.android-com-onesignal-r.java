package com.onesignal;

import android.accounts.Account;
import android.accounts.AccountManager;
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
import android.util.Patterns;
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
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public class r
{
  private static Double A;
  private static Float B;
  private static Integer C;
  private static a D;
  private static b E;
  private static boolean F;
  static String a;
  static String b;
  static Context c;
  static boolean d;
  public static String e = "native";
  static Collection<JSONArray> f = new ArrayList();
  private static boolean g;
  private static d h = d.a;
  private static d i = d.d;
  private static String j = null;
  private static int k = 1;
  private static e l;
  private static boolean m;
  private static c n;
  private static long o = 1L;
  private static long p = -1L;
  private static aa q;
  private static z r;
  private static f s = new e();
  private static int t;
  private static q u;
  private static boolean v;
  private static String w;
  private static boolean x;
  private static boolean y;
  private static Double z;
  
  public r() {}
  
  private static void A()
  {
    if (n == null) {}
    String str1;
    do
    {
      String str2;
      do
      {
        return;
        str1 = u.d();
        if (!u.c()) {
          str1 = null;
        }
        str2 = e();
      } while (str2 == null);
      n.a(str2, str1);
    } while (str1 == null);
    n = null;
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
    m = true;
    o = SystemClock.elapsedRealtime();
    v();
    if (q != null) {
      q.a();
    }
  }
  
  static void a(long paramLong, boolean paramBoolean)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("app_id", a);
      localJSONObject.put("state", "ping");
      localJSONObject.put("active_time", paramLong);
      a(localJSONObject);
      String str = "players/" + e() + "/on_focus";
      t.a local5 = new t.a()
      {
        void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
        {
          r.a("sending on_focus Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
        }
        
        void a(String paramAnonymousString)
        {
          r.a(0L);
        }
      };
      if (paramBoolean)
      {
        t.d(str, localJSONObject, local5);
        return;
      }
      t.b(str, localJSONObject, local5);
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
    //   0: getstatic 262	com/onesignal/r:D	Lcom/onesignal/r$a;
    //   3: ifnonnull +14 -> 17
    //   6: new 24	com/onesignal/r$a
    //   9: dup
    //   10: aconst_null
    //   11: invokespecial 265	com/onesignal/r$a:<init>	(Lcom/onesignal/r$1;)V
    //   14: putstatic 262	com/onesignal/r:D	Lcom/onesignal/r$a;
    //   17: new 267	com/onesignal/q
    //   20: dup
    //   21: invokespecial 268	com/onesignal/q:<init>	()V
    //   24: putstatic 270	com/onesignal/r:u	Lcom/onesignal/q;
    //   27: getstatic 270	com/onesignal/r:u	Lcom/onesignal/q;
    //   30: invokevirtual 273	com/onesignal/q:a	()I
    //   33: putstatic 275	com/onesignal/r:t	I
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
    //   61: getstatic 91	com/onesignal/r$d:d	Lcom/onesignal/r$d;
    //   64: ldc_w 293
    //   67: invokestatic 183	com/onesignal/r:a	(Lcom/onesignal/r$d;Ljava/lang/String;)V
    //   70: getstatic 275	com/onesignal/r:t	I
    //   73: iconst_1
    //   74: if_icmpne +62 -> 136
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
    //   113: getstatic 309	com/onesignal/r$d:b	Lcom/onesignal/r$d;
    //   116: ldc_w 311
    //   119: aload 5
    //   121: invokestatic 188	com/onesignal/r:a	(Lcom/onesignal/r$d;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   124: bipush -6
    //   126: putstatic 97	com/onesignal/r:k	I
    //   129: ldc_w 313
    //   132: invokestatic 319	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   135: pop
    //   136: aload_1
    //   137: putstatic 321	com/onesignal/r:b	Ljava/lang/String;
    //   140: ldc_w 323
    //   143: invokestatic 319	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   146: pop
    //   147: ldc_w 325
    //   150: invokestatic 319	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   153: pop
    //   154: ldc_w 327
    //   157: invokestatic 319	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   160: pop
    //   161: getstatic 329	com/onesignal/r:d	Z
    //   164: ifeq +103 -> 267
    //   167: aload_0
    //   168: ifnull +10 -> 178
    //   171: aload_0
    //   172: invokevirtual 335	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   175: putstatic 337	com/onesignal/r:c	Landroid/content/Context;
    //   178: aload_3
    //   179: ifnull +7 -> 186
    //   182: aload_3
    //   183: putstatic 339	com/onesignal/r:l	Lcom/onesignal/r$e;
    //   186: getstatic 339	com/onesignal/r:l	Lcom/onesignal/r$e;
    //   189: ifnull +6 -> 195
    //   192: invokestatic 341	com/onesignal/r:w	()V
    //   195: return
    //   196: astore_0
    //   197: getstatic 309	com/onesignal/r$d:b	Lcom/onesignal/r$d;
    //   200: ldc_w 343
    //   203: aload_0
    //   204: invokestatic 188	com/onesignal/r:a	(Lcom/onesignal/r$d;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   207: return
    //   208: astore 5
    //   210: getstatic 309	com/onesignal/r$d:b	Lcom/onesignal/r$d;
    //   213: ldc_w 345
    //   216: aload 5
    //   218: invokestatic 188	com/onesignal/r:a	(Lcom/onesignal/r$d;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   221: bipush -4
    //   223: putstatic 97	com/onesignal/r:k	I
    //   226: goto -90 -> 136
    //   229: astore_1
    //   230: getstatic 309	com/onesignal/r$d:b	Lcom/onesignal/r$d;
    //   233: ldc_w 347
    //   236: aload_1
    //   237: invokestatic 188	com/onesignal/r:a	(Lcom/onesignal/r$d;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   240: bipush -5
    //   242: putstatic 97	com/onesignal/r:k	I
    //   245: goto -84 -> 161
    //   248: astore_1
    //   249: getstatic 309	com/onesignal/r$d:b	Lcom/onesignal/r$d;
    //   252: ldc_w 349
    //   255: aload_1
    //   256: invokestatic 188	com/onesignal/r:a	(Lcom/onesignal/r$d;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   259: bipush -3
    //   261: putstatic 97	com/onesignal/r:k	I
    //   264: goto -103 -> 161
    //   267: aload_0
    //   268: instanceof 351
    //   271: istore 4
    //   273: iload 4
    //   275: putstatic 192	com/onesignal/r:m	Z
    //   278: aload_2
    //   279: putstatic 215	com/onesignal/r:a	Ljava/lang/String;
    //   282: aload_0
    //   283: invokevirtual 335	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   286: putstatic 337	com/onesignal/r:c	Landroid/content/Context;
    //   289: iload 4
    //   291: ifeq +177 -> 468
    //   294: aload_0
    //   295: checkcast 351	android/app/Activity
    //   298: putstatic 356	com/onesignal/a:b	Landroid/app/Activity;
    //   301: aload_3
    //   302: putstatic 339	com/onesignal/r:l	Lcom/onesignal/r$e;
    //   305: invokestatic 198	android/os/SystemClock:elapsedRealtime	()J
    //   308: putstatic 99	com/onesignal/r:o	J
    //   311: getstatic 337	com/onesignal/r:c	Landroid/content/Context;
    //   314: invokestatic 359	com/onesignal/u:a	(Landroid/content/Context;)V
    //   317: getstatic 337	com/onesignal/r:c	Landroid/content/Context;
    //   320: new 361	android/content/Intent
    //   323: dup
    //   324: getstatic 337	com/onesignal/r:c	Landroid/content/Context;
    //   327: ldc_w 363
    //   330: invokespecial 366	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   333: invokevirtual 370	android/content/Context:startService	(Landroid/content/Intent;)Landroid/content/ComponentName;
    //   336: pop
    //   337: getstatic 375	android/os/Build$VERSION:SDK_INT	I
    //   340: bipush 13
    //   342: if_icmple +133 -> 475
    //   345: getstatic 337	com/onesignal/r:c	Landroid/content/Context;
    //   348: checkcast 377	android/app/Application
    //   351: new 379	com/onesignal/b
    //   354: dup
    //   355: invokespecial 380	com/onesignal/b:<init>	()V
    //   358: invokevirtual 384	android/app/Application:registerActivityLifecycleCallbacks	(Landroid/app/Application$ActivityLifecycleCallbacks;)V
    //   361: ldc_w 386
    //   364: invokestatic 319	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   367: pop
    //   368: new 388	com/onesignal/z
    //   371: dup
    //   372: getstatic 337	com/onesignal/r:c	Landroid/content/Context;
    //   375: invokespecial 390	com/onesignal/z:<init>	(Landroid/content/Context;)V
    //   378: putstatic 392	com/onesignal/r:r	Lcom/onesignal/z;
    //   381: invokestatic 393	com/onesignal/r:d	()Ljava/lang/String;
    //   384: astore_0
    //   385: aload_0
    //   386: ifnull +95 -> 481
    //   389: aload_0
    //   390: getstatic 215	com/onesignal/r:a	Ljava/lang/String;
    //   393: invokevirtual 289	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   396: ifne +21 -> 417
    //   399: getstatic 178	com/onesignal/r$d:f	Lcom/onesignal/r$d;
    //   402: ldc_w 395
    //   405: invokestatic 183	com/onesignal/r:a	(Lcom/onesignal/r$d;Ljava/lang/String;)V
    //   408: getstatic 215	com/onesignal/r:a	Ljava/lang/String;
    //   411: invokestatic 397	com/onesignal/r:d	(Ljava/lang/String;)V
    //   414: invokestatic 399	com/onesignal/u:e	()V
    //   417: getstatic 192	com/onesignal/r:m	Z
    //   420: ifne +9 -> 429
    //   423: invokestatic 133	com/onesignal/r:e	()Ljava/lang/String;
    //   426: ifnonnull +6 -> 432
    //   429: invokestatic 200	com/onesignal/r:v	()V
    //   432: getstatic 339	com/onesignal/r:l	Lcom/onesignal/r$e;
    //   435: ifnull +6 -> 441
    //   438: invokestatic 341	com/onesignal/r:w	()V
    //   441: getstatic 337	com/onesignal/r:c	Landroid/content/Context;
    //   444: invokestatic 402	com/onesignal/aa:a	(Landroid/content/Context;)Z
    //   447: ifeq +16 -> 463
    //   450: new 204	com/onesignal/aa
    //   453: dup
    //   454: getstatic 337	com/onesignal/r:c	Landroid/content/Context;
    //   457: invokespecial 403	com/onesignal/aa:<init>	(Landroid/content/Context;)V
    //   460: putstatic 202	com/onesignal/r:q	Lcom/onesignal/aa;
    //   463: iconst_1
    //   464: putstatic 329	com/onesignal/r:d	Z
    //   467: return
    //   468: iconst_1
    //   469: putstatic 405	com/onesignal/a:a	Z
    //   472: goto -171 -> 301
    //   475: invokestatic 408	com/onesignal/c:a	()V
    //   478: goto -117 -> 361
    //   481: getstatic 215	com/onesignal/r:a	Ljava/lang/String;
    //   484: invokestatic 397	com/onesignal/r:d	(Ljava/lang/String;)V
    //   487: goto -70 -> 417
    //   490: astore_0
    //   491: goto -110 -> 381
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	494	0	paramContext	Context
    //   0	494	1	paramString1	String
    //   0	494	2	paramString2	String
    //   0	494	3	paramE	e
    //   271	19	4	bool	boolean
    //   111	9	5	localThrowable	Throwable
    //   208	9	5	localClassNotFoundException	ClassNotFoundException
    // Exception table:
    //   from	to	target	type
    //   77	100	111	java/lang/Throwable
    //   100	111	111	java/lang/Throwable
    //   36	41	196	java/lang/Throwable
    //   129	136	208	java/lang/ClassNotFoundException
    //   147	161	229	java/lang/ClassNotFoundException
    //   140	147	248	java/lang/ClassNotFoundException
    //   230	245	248	java/lang/ClassNotFoundException
    //   361	381	490	java/lang/ClassNotFoundException
  }
  
  public static void a(Context paramContext, JSONArray paramJSONArray, boolean paramBoolean)
  {
    b(paramContext, paramJSONArray);
    boolean bool1 = false;
    boolean bool2 = "DISABLE".equals(q.a(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
    if (!bool2) {
      bool1 = a(paramContext, paramJSONArray);
    }
    a(paramJSONArray, false);
    if ((!paramBoolean) && (!bool1)) {
      b(paramContext, paramJSONArray, bool2);
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
        if (!r.s()) {}
        u.a localA;
        for (boolean bool = true;; bool = false)
        {
          localA = u.b(bool);
          if (localA.a) {
            r.e(true);
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
    n = paramC;
    if (e() != null) {
      A();
    }
  }
  
  static void a(d paramD, String paramString)
  {
    a(paramD, paramString, null);
  }
  
  static void a(d paramD, final String paramString, Throwable paramThrowable)
  {
    if (paramD.compareTo(i) < 1)
    {
      if (paramD != d.g) {
        break label148;
      }
      Log.v("OneSignal", paramString, paramThrowable);
    }
    for (;;)
    {
      if ((paramD.compareTo(h) < 1) && (a.b != null)) {}
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
    j = paramString;
    if (c == null) {
      return;
    }
    paramString = e(c).edit();
    paramString.putString("GT_PLAYER_ID", j);
    paramString.commit();
  }
  
  private static void a(String paramString, final JSONObject paramJSONObject, final boolean paramBoolean)
  {
    if (Looper.getMainLooper().getThread() == Thread.currentThread())
    {
      l.a(paramString, paramJSONObject, paramBoolean);
      return;
    }
    a(new Runnable()
    {
      public void run()
      {
        r.u().a(this.a, paramJSONObject, paramBoolean);
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
    if (l == null)
    {
      f.add(paramJSONArray);
      return;
    }
    int i2 = paramJSONArray.length();
    Object localObject4 = null;
    int i1 = 0;
    Object localObject1 = null;
    if (i1 < i2) {
      for (;;)
      {
        try
        {
          localJSONObject = paramJSONArray.getJSONObject(i1);
          localObject2 = null;
          if (localJSONObject.has("custom"))
          {
            localObject3 = new JSONObject(localJSONObject.getString("custom"));
            localObject2 = new JSONObject();
            if (((JSONObject)localObject3).has("a")) {
              localObject2 = ((JSONObject)localObject3).getJSONObject("a");
            }
            if (localJSONObject.has("title")) {
              ((JSONObject)localObject2).put("title", localJSONObject.getString("title"));
            }
            if (((JSONObject)localObject3).has("u")) {
              ((JSONObject)localObject2).put("launchURL", ((JSONObject)localObject3).getString("u"));
            }
            if (localJSONObject.has("sound")) {
              ((JSONObject)localObject2).put("sound", localJSONObject.getString("sound"));
            }
            if (localJSONObject.has("sicon")) {
              ((JSONObject)localObject2).put("smallIcon", localJSONObject.getString("sicon"));
            }
            if (localJSONObject.has("licon")) {
              ((JSONObject)localObject2).put("largeIcon", localJSONObject.getString("licon"));
            }
            if (localJSONObject.has("bicon")) {
              ((JSONObject)localObject2).put("bigPicture", localJSONObject.getString("bicon"));
            }
            boolean bool = localObject2.equals(new JSONObject());
            if (bool) {
              localObject2 = null;
            }
          }
          if (localObject4 == null)
          {
            localObject1 = localObject2;
            localObject3 = localObject1;
          }
        }
        catch (Throwable localThrowable2)
        {
          JSONObject localJSONObject;
          Object localObject2;
          Object localObject3;
          continue;
          continue;
        }
        try
        {
          localObject2 = localJSONObject.getString("alert");
          localObject4 = localObject2;
          i1 += 1;
        }
        catch (Throwable localThrowable1)
        {
          localObject1 = localObject3;
        }
        if (localObject1 != null) {
          continue;
        }
        localObject3 = new JSONObject();
        localObject1 = localObject3;
        localObject3 = localObject1;
        if (!localObject1.has("stacked_notifications"))
        {
          localObject3 = localObject1;
          localObject1.put("stacked_notifications", new JSONArray());
        }
        localObject3 = localObject1;
        ((JSONObject)localObject2).put("message", localJSONObject.getString("alert"));
        localObject3 = localObject1;
        localObject1.getJSONArray("stacked_notifications").put(localObject2);
        continue;
        a(d.c, "Error parsing JSON item " + i1 + "/" + i2 + " for callback.", localThrowable1);
      }
    }
    a(localObject4, localObject1, paramBoolean);
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean, t.a paramA)
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
      t.b("players/" + e() + "/on_purchase", localJSONObject, paramA);
      return;
    }
    catch (Throwable paramJSONArray)
    {
      a(d.c, "Failed to generate JSON for sendPurchases.", paramJSONArray);
    }
  }
  
  private static void a(JSONObject paramJSONObject)
  {
    try
    {
      paramJSONObject.put("net_type", u.b());
      return;
    }
    catch (Throwable paramJSONObject) {}
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
    } while ((l1 < 0L) || (l1 > 604800L));
    if (c == null)
    {
      a(d.c, "Android Context not found, please call OneSignal.init when your app starts.");
      return;
    }
    long l1 = f() + l1;
    if ((paramBoolean) || (l1 < 60L) || (e() == null))
    {
      b(l1);
      return;
    }
    a(l1, true);
  }
  
  static boolean a(Context paramContext)
  {
    return e(paramContext).getBoolean("GT_VIBRATE_ENABLED", true);
  }
  
  static boolean a(Context paramContext, Bundle paramBundle)
  {
    paramBundle = a(paramBundle);
    return (paramBundle == null) || (a(paramBundle, paramContext));
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
            localObject = new JSONObject(((JSONObject)localObject).getString("custom"));
            bool2 = bool1;
            if (((JSONObject)localObject).has("u"))
            {
              String str = ((JSONObject)localObject).getString("u");
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
  
  private static boolean a(d paramD)
  {
    return (paramD.compareTo(h) < 1) || (paramD.compareTo(i) < 1);
  }
  
  static boolean a(String paramString, Context paramContext)
  {
    if ((paramString == null) || ("".equals(paramString))) {
      return false;
    }
    paramContext = new s(paramContext).getReadableDatabase();
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
  
  private static void b(long paramLong)
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
            break label152;
          }
          localObject = new JSONObject(((JSONObject)localObject).getString("custom"));
          if (!((JSONObject)localObject).has("i")) {
            break label152;
          }
          localObject = ((JSONObject)localObject).getString("i");
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("app_id", f(paramContext));
          localJSONObject.put("player_id", g(paramContext));
          localJSONObject.put("opened", true);
          t.a("notifications/" + (String)localObject, localJSONObject, new t.a()
          {
            void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
            {
              r.a("sending Notification Opened Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
            }
          });
        }
        catch (Throwable localThrowable)
        {
          a(d.c, "Failed to generate JSON to send notification opened.", localThrowable);
        }
      }
      return;
      label152:
      i1 += 1;
    }
  }
  
  private static void b(Context paramContext, JSONArray paramJSONArray, boolean paramBoolean)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    int i1 = 0;
    Intent localIntent = new Intent().setAction("com.onesignal.NotificationOpened.RECEIVE").setPackage(paramContext.getPackageName());
    if (localPackageManager.queryBroadcastReceivers(localIntent, 32).size() > 0)
    {
      localIntent.putExtra("onesignal_data", paramJSONArray.toString());
      paramContext.sendBroadcast(localIntent);
      i1 = 1;
    }
    int i2 = i1;
    if (localPackageManager.queryIntentActivities(localIntent, 65536).size() > 0)
    {
      if (i1 == 0) {
        localIntent.putExtra("onesignal_data", paramJSONArray.toString());
      }
      i2 = 1;
      localIntent.setFlags(268566528);
      paramContext.startActivity(localIntent);
    }
    if ((i2 == 0) && (!paramBoolean))
    {
      paramJSONArray = paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
      if (paramJSONArray != null)
      {
        paramJSONArray.setFlags(268566528);
        paramContext.startActivity(paramJSONArray);
      }
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
  
  public static void b(boolean paramBoolean)
  {
    if (c == null) {
      return;
    }
    SharedPreferences.Editor localEditor = e(c).edit();
    localEditor.putBoolean("ONESIGNAL_ALWAYS_SHOW_NOTIF", paramBoolean);
    localEditor.commit();
  }
  
  static boolean b()
  {
    return m;
  }
  
  static boolean b(Context paramContext)
  {
    return e(paramContext).getBoolean("GT_SOUND_ENABLED", true);
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
  
  static boolean c(Context paramContext)
  {
    return e(paramContext).getBoolean("ONESIGNAL_ALWAYS_SHOW_NOTIF", false);
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
    SharedPreferences.Editor localEditor = e(c).edit();
    localEditor.putString("GT_APP_ID", paramString);
    localEditor.commit();
  }
  
  static boolean d(Context paramContext)
  {
    return e(paramContext).getBoolean("ONESIGNAL_INAPP_ALERT", false);
  }
  
  static SharedPreferences e(Context paramContext)
  {
    return paramContext.getSharedPreferences(r.class.getSimpleName(), 0);
  }
  
  static String e()
  {
    if ((j == null) && (c != null)) {
      j = e(c).getString("GT_PLAYER_ID", null);
    }
    return j;
  }
  
  static long f()
  {
    if ((p == -1L) && (c != null)) {
      p = e(c).getLong("GT_UNSENT_ACTIVE_TIME", 0L);
    }
    a(d.e, "GetUnsentActiveTime: " + p);
    return p;
  }
  
  private static String f(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return e(paramContext).getString("GT_APP_ID", null);
  }
  
  private static String g(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return e(paramContext).getString("GT_PLAYER_ID", null);
  }
  
  static boolean g()
  {
    return (d) && (b());
  }
  
  private static void v()
  {
    if (g) {
      return;
    }
    g = true;
    if (t == 2) {}
    for (Object localObject = new w();; localObject = new x())
    {
      ((v)localObject).a(c, b, new v.a()
      {
        public void a(String paramAnonymousString)
        {
          r.c(paramAnonymousString);
          r.c(true);
          r.h();
        }
      });
      l.a(c, D.a, new l.b()
      {
        public void a(Double paramAnonymousDouble1, Double paramAnonymousDouble2, Float paramAnonymousFloat, Integer paramAnonymousInteger)
        {
          r.a(paramAnonymousDouble1);
          r.b(paramAnonymousDouble2);
          r.a(paramAnonymousFloat);
          r.a(paramAnonymousInteger);
          r.d(true);
          r.h();
        }
      });
      return;
    }
  }
  
  private static void w()
  {
    Iterator localIterator = f.iterator();
    while (localIterator.hasNext()) {
      a((JSONArray)localIterator.next(), false);
    }
    f.clear();
  }
  
  private static void x()
  {
    String str = u.d();
    if ((w != null) && (!w.equals(str)))
    {
      u.a(w);
      c();
    }
  }
  
  private static int y()
  {
    TimeZone localTimeZone = Calendar.getInstance().getTimeZone();
    int i2 = localTimeZone.getRawOffset();
    int i1 = i2;
    if (localTimeZone.inDaylightTime(new Date())) {
      i1 = i2 + localTimeZone.getDSTSavings();
    }
    return i1 / 1000;
  }
  
  private static void z()
  {
    a(d.f, "registerUser: registerForPushFired:" + x + ", locationFired: " + y);
    if ((!x) || (!y)) {
      return;
    }
    if (v)
    {
      x();
      return;
    }
    v = true;
    new Thread(new Runnable()
    {
      public void run()
      {
        u.c localC = u.b();
        String str = r.c.getPackageName();
        Object localObject3 = r.c.getPackageManager();
        localC.a("app_id", r.a);
        localC.a("identifier", r.i());
        Object localObject2 = r.j().a(r.c);
        Object localObject1 = localObject2;
        if (localObject2 == null) {
          localObject1 = new d().a(r.c);
        }
        localC.a("ad_id", localObject1);
        localC.a("device_os", Build.VERSION.RELEASE);
        localC.a("timezone", Integer.valueOf(r.k()));
        localC.a("language", Locale.getDefault().getLanguage());
        localC.a("sdk", "020403");
        localC.a("sdk_type", r.e);
        localC.a("android_package", str);
        localC.a("device_model", Build.MODEL);
        localC.a("device_type", Integer.valueOf(r.l()));
        localC.b("subscribableStatus", Integer.valueOf(r.m()));
        try
        {
          localC.a("game_version", Integer.valueOf(((PackageManager)localObject3).getPackageInfo(str, 0).versionCode));
          try
          {
            localObject1 = ((PackageManager)localObject3).getInstalledPackages(0);
            localObject2 = new JSONArray();
            localObject3 = MessageDigest.getInstance("SHA-256");
            i = 0;
            if (i < ((List)localObject1).size())
            {
              if (((((PackageInfo)((List)localObject1).get(i)).applicationInfo.flags & 0x1) != 0) || (str.equals(((PackageInfo)((List)localObject1).get(i)).packageName))) {
                break label529;
              }
              ((MessageDigest)localObject3).update(((PackageInfo)((List)localObject1).get(i)).packageName.getBytes());
              ((JSONArray)localObject2).put(Base64.encodeToString(((MessageDigest)localObject3).digest(), 2));
              break label529;
            }
            localC.a("pkgs", localObject2);
          }
          catch (Throwable localThrowable)
          {
            for (;;) {}
          }
          localObject1 = r.e(r.c).getString("OS_USER_EMAIL", null);
          if (localObject1 != null) {
            localC.a("email", localObject1);
          }
          label519:
          for (;;)
          {
            localC.a("net_type", r.n().b());
            localC.a("carrier", r.n().c());
            localC.a("rooted", Boolean.valueOf(y.a()));
            localC.a("lat", r.o());
            localC.a("long", r.p());
            localC.a("loc_acc", r.q());
            localC.a("loc_type", r.r());
            u.a(localC);
            return;
            if (g.c.a(r.c, "android.permission.GET_ACCOUNTS") == 0)
            {
              localObject1 = AccountManager.get(r.c).getAccounts();
              int j = localObject1.length;
              i = 0;
              for (;;)
              {
                if (i >= j) {
                  break label519;
                }
                localObject2 = localObject1[i];
                if (Patterns.EMAIL_ADDRESS.matcher(((Account)localObject2).name).matches())
                {
                  localC.a("email", ((Account)localObject2).name);
                  break;
                }
                i += 1;
              }
            }
          }
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            int i;
            continue;
            label529:
            i += 1;
          }
        }
      }
    }).start();
  }
  
  public static class a
  {
    boolean a;
    
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
    public abstract void a(String paramString, JSONObject paramJSONObject, boolean paramBoolean);
  }
}
