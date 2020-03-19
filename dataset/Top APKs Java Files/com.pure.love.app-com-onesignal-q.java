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

public class q
{
  private static Double A;
  private static Float B;
  private static Integer C;
  private static a D;
  static String a;
  static String b;
  static Context c;
  static boolean d;
  public static String e = "native";
  static Collection<JSONArray> f = new ArrayList();
  private static boolean g;
  private static c h = c.a;
  private static c i = c.d;
  private static String j = null;
  private static int k = 1;
  private static d l;
  private static boolean m;
  private static b n;
  private static long o = 1L;
  private static long p = -1L;
  private static z q;
  private static y r;
  private static f s = new e();
  private static int t;
  private static p u;
  private static boolean v;
  private static String w;
  private static boolean x;
  private static boolean y;
  private static Double z;
  
  public q() {}
  
  static void a()
  {
    m = true;
    o = SystemClock.elapsedRealtime();
    t();
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
      s.a local4 = new s.a()
      {
        void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
        {
          q.a("sending on_focus Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
        }
        
        void a(String paramAnonymousString)
        {
          q.a(0L);
        }
      };
      if (paramBoolean)
      {
        s.d(str, localJSONObject, local4);
        return;
      }
      s.b(str, localJSONObject, local4);
      return;
    }
    catch (Throwable localThrowable)
    {
      a(c.c, "Generating on_focus:JSON Failed.", localThrowable);
    }
  }
  
  /* Error */
  public static void a(Context paramContext, String paramString1, String paramString2, d paramD)
  {
    // Byte code:
    //   0: getstatic 205	com/onesignal/q:D	Lcom/onesignal/q$a;
    //   3: ifnonnull +14 -> 17
    //   6: new 22	com/onesignal/q$a
    //   9: dup
    //   10: aconst_null
    //   11: invokespecial 208	com/onesignal/q$a:<init>	(Lcom/onesignal/q$1;)V
    //   14: putstatic 205	com/onesignal/q:D	Lcom/onesignal/q$a;
    //   17: new 210	com/onesignal/p
    //   20: dup
    //   21: invokespecial 211	com/onesignal/p:<init>	()V
    //   24: putstatic 213	com/onesignal/q:u	Lcom/onesignal/p;
    //   27: getstatic 213	com/onesignal/q:u	Lcom/onesignal/p;
    //   30: invokevirtual 216	com/onesignal/p:a	()I
    //   33: putstatic 218	com/onesignal/q:t	I
    //   36: aload_2
    //   37: invokestatic 224	java/util/UUID:fromString	(Ljava/lang/String;)Ljava/util/UUID;
    //   40: pop
    //   41: ldc -30
    //   43: aload_2
    //   44: invokevirtual 232	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   47: ifne +12 -> 59
    //   50: ldc -22
    //   52: aload_2
    //   53: invokevirtual 232	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   56: ifeq +11 -> 67
    //   59: getstatic 84	com/onesignal/q$c:d	Lcom/onesignal/q$c;
    //   62: ldc -20
    //   64: invokestatic 239	com/onesignal/q:a	(Lcom/onesignal/q$c;Ljava/lang/String;)V
    //   67: getstatic 218	com/onesignal/q:t	I
    //   70: iconst_1
    //   71: if_icmpne +61 -> 132
    //   74: aload_1
    //   75: invokestatic 245	java/lang/Double:parseDouble	(Ljava/lang/String;)D
    //   78: pop2
    //   79: aload_1
    //   80: invokevirtual 248	java/lang/String:length	()I
    //   83: bipush 8
    //   85: if_icmplt +12 -> 97
    //   88: aload_1
    //   89: invokevirtual 248	java/lang/String:length	()I
    //   92: bipush 16
    //   94: if_icmple +31 -> 125
    //   97: new 250	java/lang/IllegalArgumentException
    //   100: dup
    //   101: ldc -4
    //   103: invokespecial 255	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   106: athrow
    //   107: astore 5
    //   109: getstatic 257	com/onesignal/q$c:b	Lcom/onesignal/q$c;
    //   112: ldc_w 259
    //   115: aload 5
    //   117: invokestatic 200	com/onesignal/q:a	(Lcom/onesignal/q$c;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   120: bipush -6
    //   122: putstatic 90	com/onesignal/q:k	I
    //   125: ldc_w 261
    //   128: invokestatic 267	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   131: pop
    //   132: aload_1
    //   133: putstatic 269	com/onesignal/q:b	Ljava/lang/String;
    //   136: ldc_w 271
    //   139: invokestatic 267	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   142: pop
    //   143: ldc_w 273
    //   146: invokestatic 267	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   149: pop
    //   150: ldc_w 275
    //   153: invokestatic 267	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   156: pop
    //   157: getstatic 277	com/onesignal/q:d	Z
    //   160: ifeq +103 -> 263
    //   163: aload_0
    //   164: ifnull +10 -> 174
    //   167: aload_0
    //   168: invokevirtual 283	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   171: putstatic 285	com/onesignal/q:c	Landroid/content/Context;
    //   174: aload_3
    //   175: ifnull +7 -> 182
    //   178: aload_3
    //   179: putstatic 287	com/onesignal/q:l	Lcom/onesignal/q$d;
    //   182: getstatic 287	com/onesignal/q:l	Lcom/onesignal/q$d;
    //   185: ifnull +6 -> 191
    //   188: invokestatic 289	com/onesignal/q:u	()V
    //   191: return
    //   192: astore_0
    //   193: getstatic 257	com/onesignal/q$c:b	Lcom/onesignal/q$c;
    //   196: ldc_w 291
    //   199: aload_0
    //   200: invokestatic 200	com/onesignal/q:a	(Lcom/onesignal/q$c;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   203: return
    //   204: astore 5
    //   206: getstatic 257	com/onesignal/q$c:b	Lcom/onesignal/q$c;
    //   209: ldc_w 293
    //   212: aload 5
    //   214: invokestatic 200	com/onesignal/q:a	(Lcom/onesignal/q$c;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   217: bipush -4
    //   219: putstatic 90	com/onesignal/q:k	I
    //   222: goto -90 -> 132
    //   225: astore_1
    //   226: getstatic 257	com/onesignal/q$c:b	Lcom/onesignal/q$c;
    //   229: ldc_w 295
    //   232: aload_1
    //   233: invokestatic 200	com/onesignal/q:a	(Lcom/onesignal/q$c;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   236: bipush -5
    //   238: putstatic 90	com/onesignal/q:k	I
    //   241: goto -84 -> 157
    //   244: astore_1
    //   245: getstatic 257	com/onesignal/q$c:b	Lcom/onesignal/q$c;
    //   248: ldc_w 297
    //   251: aload_1
    //   252: invokestatic 200	com/onesignal/q:a	(Lcom/onesignal/q$c;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   255: bipush -3
    //   257: putstatic 90	com/onesignal/q:k	I
    //   260: goto -103 -> 157
    //   263: aload_0
    //   264: instanceof 299
    //   267: istore 4
    //   269: iload 4
    //   271: putstatic 125	com/onesignal/q:m	Z
    //   274: aload_2
    //   275: putstatic 152	com/onesignal/q:a	Ljava/lang/String;
    //   278: aload_0
    //   279: invokevirtual 283	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   282: putstatic 285	com/onesignal/q:c	Landroid/content/Context;
    //   285: iload 4
    //   287: ifeq +177 -> 464
    //   290: aload_0
    //   291: checkcast 299	android/app/Activity
    //   294: putstatic 304	com/onesignal/a:b	Landroid/app/Activity;
    //   297: aload_3
    //   298: putstatic 287	com/onesignal/q:l	Lcom/onesignal/q$d;
    //   301: invokestatic 131	android/os/SystemClock:elapsedRealtime	()J
    //   304: putstatic 92	com/onesignal/q:o	J
    //   307: getstatic 285	com/onesignal/q:c	Landroid/content/Context;
    //   310: invokestatic 309	com/onesignal/t:a	(Landroid/content/Context;)V
    //   313: getstatic 285	com/onesignal/q:c	Landroid/content/Context;
    //   316: new 311	android/content/Intent
    //   319: dup
    //   320: getstatic 285	com/onesignal/q:c	Landroid/content/Context;
    //   323: ldc_w 313
    //   326: invokespecial 316	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   329: invokevirtual 320	android/content/Context:startService	(Landroid/content/Intent;)Landroid/content/ComponentName;
    //   332: pop
    //   333: getstatic 325	android/os/Build$VERSION:SDK_INT	I
    //   336: bipush 13
    //   338: if_icmple +133 -> 471
    //   341: getstatic 285	com/onesignal/q:c	Landroid/content/Context;
    //   344: checkcast 327	android/app/Application
    //   347: new 329	com/onesignal/b
    //   350: dup
    //   351: invokespecial 330	com/onesignal/b:<init>	()V
    //   354: invokevirtual 334	android/app/Application:registerActivityLifecycleCallbacks	(Landroid/app/Application$ActivityLifecycleCallbacks;)V
    //   357: ldc_w 336
    //   360: invokestatic 267	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   363: pop
    //   364: new 338	com/onesignal/y
    //   367: dup
    //   368: getstatic 285	com/onesignal/q:c	Landroid/content/Context;
    //   371: invokespecial 340	com/onesignal/y:<init>	(Landroid/content/Context;)V
    //   374: putstatic 342	com/onesignal/q:r	Lcom/onesignal/y;
    //   377: invokestatic 344	com/onesignal/q:d	()Ljava/lang/String;
    //   380: astore_0
    //   381: aload_0
    //   382: ifnull +95 -> 477
    //   385: aload_0
    //   386: getstatic 152	com/onesignal/q:a	Ljava/lang/String;
    //   389: invokevirtual 232	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   392: ifne +21 -> 413
    //   395: getstatic 346	com/onesignal/q$c:f	Lcom/onesignal/q$c;
    //   398: ldc_w 348
    //   401: invokestatic 239	com/onesignal/q:a	(Lcom/onesignal/q$c;Ljava/lang/String;)V
    //   404: getstatic 152	com/onesignal/q:a	Ljava/lang/String;
    //   407: invokestatic 350	com/onesignal/q:c	(Ljava/lang/String;)V
    //   410: invokestatic 352	com/onesignal/t:e	()V
    //   413: getstatic 125	com/onesignal/q:m	Z
    //   416: ifne +9 -> 425
    //   419: invokestatic 180	com/onesignal/q:e	()Ljava/lang/String;
    //   422: ifnonnull +6 -> 428
    //   425: invokestatic 133	com/onesignal/q:t	()V
    //   428: getstatic 287	com/onesignal/q:l	Lcom/onesignal/q$d;
    //   431: ifnull +6 -> 437
    //   434: invokestatic 289	com/onesignal/q:u	()V
    //   437: getstatic 285	com/onesignal/q:c	Landroid/content/Context;
    //   440: invokestatic 355	com/onesignal/z:a	(Landroid/content/Context;)Z
    //   443: ifeq +16 -> 459
    //   446: new 137	com/onesignal/z
    //   449: dup
    //   450: getstatic 285	com/onesignal/q:c	Landroid/content/Context;
    //   453: invokespecial 356	com/onesignal/z:<init>	(Landroid/content/Context;)V
    //   456: putstatic 135	com/onesignal/q:q	Lcom/onesignal/z;
    //   459: iconst_1
    //   460: putstatic 277	com/onesignal/q:d	Z
    //   463: return
    //   464: iconst_1
    //   465: putstatic 358	com/onesignal/a:a	Z
    //   468: goto -171 -> 297
    //   471: invokestatic 361	com/onesignal/c:a	()V
    //   474: goto -117 -> 357
    //   477: getstatic 152	com/onesignal/q:a	Ljava/lang/String;
    //   480: invokestatic 350	com/onesignal/q:c	(Ljava/lang/String;)V
    //   483: goto -70 -> 413
    //   486: astore_0
    //   487: goto -110 -> 377
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	490	0	paramContext	Context
    //   0	490	1	paramString1	String
    //   0	490	2	paramString2	String
    //   0	490	3	paramD	d
    //   267	19	4	bool	boolean
    //   107	9	5	localThrowable	Throwable
    //   204	9	5	localClassNotFoundException	ClassNotFoundException
    // Exception table:
    //   from	to	target	type
    //   74	97	107	java/lang/Throwable
    //   97	107	107	java/lang/Throwable
    //   36	41	192	java/lang/Throwable
    //   125	132	204	java/lang/ClassNotFoundException
    //   143	157	225	java/lang/ClassNotFoundException
    //   136	143	244	java/lang/ClassNotFoundException
    //   226	241	244	java/lang/ClassNotFoundException
    //   357	377	486	java/lang/ClassNotFoundException
  }
  
  public static void a(Context paramContext, JSONArray paramJSONArray, boolean paramBoolean)
  {
    b(paramContext, paramJSONArray);
    boolean bool1 = false;
    boolean bool2 = "DISABLE".equals(p.a(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
    if (!bool2) {
      bool1 = a(paramContext, paramJSONArray);
    }
    a(paramJSONArray, false);
    if ((!paramBoolean) && (!bool1)) {
      b(paramContext, paramJSONArray, bool2);
    }
  }
  
  public static void a(b paramB)
  {
    n = paramB;
    if (e() != null) {
      y();
    }
  }
  
  static void a(c paramC, String paramString)
  {
    a(paramC, paramString, null);
  }
  
  static void a(c paramC, final String paramString, Throwable paramThrowable)
  {
    if (paramC.compareTo(i) < 1)
    {
      if (paramC != c.g) {
        break label148;
      }
      Log.v("OneSignal", paramString, paramThrowable);
    }
    for (;;)
    {
      if ((paramC.compareTo(h) < 1) && (a.b != null)) {}
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
      catch (Throwable paramC)
      {
        label148:
        Log.e("OneSignal", "Error showing logging message.", paramC);
      }
      if (paramC == c.f) {
        Log.d("OneSignal", paramString, paramThrowable);
      } else if (paramC == c.e) {
        Log.i("OneSignal", paramString, paramThrowable);
      } else if (paramC == c.d) {
        Log.w("OneSignal", paramString, paramThrowable);
      } else if ((paramC == c.c) || (paramC == c.b)) {
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
        q.s().a(this.a, paramJSONObject, paramBoolean);
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
        a(c.c, "Error parsing JSON item " + i1 + "/" + i2 + " for callback.", localThrowable1);
      }
    }
    a(localObject4, localObject1, paramBoolean);
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean, s.a paramA)
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
      s.b("players/" + e() + "/on_purchase", localJSONObject, paramA);
      return;
    }
    catch (Throwable paramJSONArray)
    {
      a(c.c, "Failed to generate JSON for sendPurchases.", paramJSONArray);
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
      a(c.c, "Android Context not found, please call OneSignal.init when your app starts.");
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
    if (paramBundle.isEmpty()) {
      return true;
    }
    try
    {
      if (paramBundle.containsKey("custom"))
      {
        paramBundle = new JSONObject(paramBundle.getString("custom"));
        if (paramBundle.has("i")) {
          return a(paramBundle.getString("i"), paramContext);
        }
        a(c.f, "Not a OneSignal formatted GCM message. No 'i' field in custom.");
        return true;
      }
    }
    catch (Throwable paramContext)
    {
      a(c.f, "Could not parse bundle for duplicate, probably not a OneSignal notification.", paramContext);
      return true;
    }
    a(c.f, "Not a OneSignal formatted GCM message. No 'custom' field in the bundle.");
    return true;
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
          a(c.c, "Error parsing JSON item " + i1 + "/" + i2 + " for launching a web URL.", localThrowable);
          bool2 = bool1;
        }
      }
      return bool1;
      i1 += 1;
      bool1 = bool2;
    }
  }
  
  private static boolean a(c paramC)
  {
    return (paramC.compareTo(h) < 1) || (paramC.compareTo(i) < 1);
  }
  
  static boolean a(String paramString, Context paramContext)
  {
    if ((paramString == null) || ("".equals(paramString))) {
      return false;
    }
    paramContext = new r(paramContext).getReadableDatabase();
    Cursor localCursor = paramContext.query("notification", new String[] { "notification_id" }, "notification_id = ?", new String[] { paramString }, null, null, null);
    boolean bool = localCursor.moveToFirst();
    localCursor.close();
    paramContext.close();
    if (bool)
    {
      a(c.f, "Duplicate GCM message received, skipping processing. " + paramString);
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
    a(c.e, "SaveUnsentActiveTime: " + p);
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
            break label156;
          }
          localObject = new JSONObject(((JSONObject)localObject).getString("custom"));
          if (!((JSONObject)localObject).has("i")) {
            break label156;
          }
          localObject = ((JSONObject)localObject).getString("i");
          JSONObject localJSONObject = new JSONObject();
          localJSONObject.put("app_id", f(paramContext));
          localJSONObject.put("player_id", g(paramContext));
          localJSONObject.put("opened", true);
          s.a("notifications/" + (String)localObject, localJSONObject, new s.a()
          {
            void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
            {
              q.a("sending Notification Opened Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
            }
          });
        }
        catch (Throwable localThrowable)
        {
          a(c.c, "Failed to generate JSON to send notification opened.", localThrowable);
        }
      }
      return;
      label156:
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
  
  private static void b(String paramString1, int paramInt, Throwable paramThrowable, String paramString2)
  {
    String str2 = "";
    String str1 = str2;
    if (paramString2 != null)
    {
      str1 = str2;
      if (a(c.e)) {
        str1 = "\n" + paramString2 + "\n";
      }
    }
    a(c.d, "HTTP code: " + paramInt + " " + paramString1 + str1, paramThrowable);
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
  
  private static void c(String paramString)
  {
    if (c == null) {
      return;
    }
    SharedPreferences.Editor localEditor = e(c).edit();
    localEditor.putString("GT_APP_ID", paramString);
    localEditor.commit();
  }
  
  static boolean c(Context paramContext)
  {
    return e(paramContext).getBoolean("ONESIGNAL_ALWAYS_SHOW_NOTIF", false);
  }
  
  static String d()
  {
    return f(c);
  }
  
  static boolean d(Context paramContext)
  {
    return e(paramContext).getBoolean("ONESIGNAL_INAPP_ALERT", false);
  }
  
  static SharedPreferences e(Context paramContext)
  {
    return paramContext.getSharedPreferences(q.class.getSimpleName(), 0);
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
    a(c.e, "GetUnsentActiveTime: " + p);
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
  
  private static void t()
  {
    if (g) {
      return;
    }
    g = true;
    if (t == 2) {}
    for (Object localObject = new v();; localObject = new w())
    {
      ((u)localObject).a(c, b, new u.a()
      {
        public void a(String paramAnonymousString)
        {
          q.b(paramAnonymousString);
          q.c(true);
          q.g();
        }
      });
      l.a(c, D.a, new l.b()
      {
        public void a(Double paramAnonymousDouble1, Double paramAnonymousDouble2, Float paramAnonymousFloat, Integer paramAnonymousInteger)
        {
          q.a(paramAnonymousDouble1);
          q.b(paramAnonymousDouble2);
          q.a(paramAnonymousFloat);
          q.a(paramAnonymousInteger);
          q.d(true);
          q.g();
        }
      });
      return;
    }
  }
  
  private static void u()
  {
    Iterator localIterator = f.iterator();
    while (localIterator.hasNext()) {
      a((JSONArray)localIterator.next(), false);
    }
    f.clear();
  }
  
  private static void v()
  {
    String str = t.d();
    if ((w != null) && (!w.equals(str)))
    {
      t.a(w);
      c();
    }
  }
  
  private static int w()
  {
    TimeZone localTimeZone = Calendar.getInstance().getTimeZone();
    int i2 = localTimeZone.getRawOffset();
    int i1 = i2;
    if (localTimeZone.inDaylightTime(new Date())) {
      i1 = i2 + localTimeZone.getDSTSavings();
    }
    return i1 / 1000;
  }
  
  private static void x()
  {
    a(c.f, "registerUser: registerForPushFired:" + x + ", locationFired: " + y);
    if ((!x) || (!y)) {
      return;
    }
    if (v)
    {
      v();
      return;
    }
    v = true;
    new Thread(new Runnable()
    {
      public void run()
      {
        t.b localB = t.b();
        String str = q.c.getPackageName();
        Object localObject3 = q.c.getPackageManager();
        localB.a("app_id", q.a);
        localB.a("identifier", q.h());
        Object localObject2 = q.i().a(q.c);
        Object localObject1 = localObject2;
        if (localObject2 == null) {
          localObject1 = new d().a(q.c);
        }
        localB.a("ad_id", localObject1);
        localB.a("device_os", Build.VERSION.RELEASE);
        localB.a("timezone", Integer.valueOf(q.j()));
        localB.a("language", Locale.getDefault().getLanguage());
        localB.a("sdk", "020300");
        localB.a("sdk_type", q.e);
        localB.a("android_package", str);
        localB.a("device_model", Build.MODEL);
        localB.a("device_type", Integer.valueOf(q.k()));
        localB.b("subscribableStatus", Integer.valueOf(q.l()));
        try
        {
          localB.a("game_version", Integer.valueOf(((PackageManager)localObject3).getPackageInfo(str, 0).versionCode));
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
            localB.a("pkgs", localObject2);
          }
          catch (Throwable localThrowable)
          {
            for (;;) {}
          }
          localObject1 = q.e(q.c).getString("OS_USER_EMAIL", null);
          if (localObject1 != null) {
            localB.a("email", localObject1);
          }
          label519:
          for (;;)
          {
            localB.a("net_type", q.m().b());
            localB.a("carrier", q.m().c());
            localB.a("rooted", Boolean.valueOf(x.a()));
            localB.a("lat", q.n());
            localB.a("long", q.o());
            localB.a("loc_acc", q.p());
            localB.a("loc_type", q.q());
            t.a(localB);
            return;
            if (g.c.a(q.c, "android.permission.GET_ACCOUNTS") == 0)
            {
              localObject1 = AccountManager.get(q.c).getAccounts();
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
                  localB.a("email", ((Account)localObject2).name);
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
  
  private static void y()
  {
    if (n == null) {}
    String str1;
    do
    {
      String str2;
      do
      {
        return;
        str1 = t.d();
        if (!t.c()) {
          str1 = null;
        }
        str2 = e();
      } while (str2 == null);
      n.a(str2, str1);
    } while (str1 == null);
    n = null;
  }
  
  public static class a
  {
    boolean a;
    
    private a() {}
  }
  
  public static abstract interface b
  {
    public abstract void a(String paramString1, String paramString2);
  }
  
  public static enum c
  {
    private c() {}
  }
  
  public static abstract interface d
  {
    public abstract void a(String paramString, JSONObject paramJSONObject, boolean paramBoolean);
  }
}
