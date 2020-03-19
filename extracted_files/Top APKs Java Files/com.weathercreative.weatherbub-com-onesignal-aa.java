package com.onesignal;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class aa
{
  private static Double A;
  private static Float B;
  private static Integer C;
  private static boolean D = true;
  private static Collection<JSONArray> E = new ArrayList();
  private static ac F;
  private static boolean G;
  private static boolean H;
  private static boolean I;
  private static JSONObject J;
  private static int K = 0;
  static String a;
  static String b;
  static Context c;
  static boolean d;
  public static String e;
  static ab f;
  static boolean g;
  private static ae h = ae.a;
  private static ae i = ae.d;
  private static String j = null;
  private static int k;
  private static boolean l;
  private static ad m;
  private static long n = 1L;
  private static long o = -1L;
  private static av p;
  private static at q;
  private static f r = new f();
  private static int s;
  private static z t;
  private static String u;
  private static boolean v;
  private static boolean w;
  private static boolean x;
  private static boolean y;
  private static Double z;
  
  static
  {
    e = "native";
  }
  
  public aa() {}
  
  private static void B()
  {
    boolean bool = true;
    if (H) {
      return;
    }
    H = true;
    v = false;
    if (I) {
      w = false;
    }
    if (D)
    {
      a.1.a(c, false, new l());
      D();
      if (!y) {
        break label71;
      }
    }
    for (;;)
    {
      y = bool;
      return;
      w = true;
      F();
      break;
      label71:
      bool = false;
    }
  }
  
  private static void C()
  {
    if (s == 2) {}
    for (Object localObject = new ar();; localObject = new as())
    {
      ((ap)localObject).a(c, b, new aq());
      return;
    }
  }
  
  private static void D()
  {
    if (x)
    {
      C();
      return;
    }
    ak local3 = new ak()
    {
      final void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        new Thread(new Runnable()
        {
          public final void run()
          {
            try
            {
              Thread.sleep(aa.k() * 10000 + 30000);
              aa.l();
              aa.m();
              return;
            }
            catch (Throwable localThrowable)
            {
              for (;;) {}
            }
          }
        }, "OS_PARAMS_REQUEST");
      }
      
      final void a(String paramAnonymousString)
      {
        try
        {
          paramAnonymousString = new JSONObject(paramAnonymousString);
          if (paramAnonymousString.has("android_sender_id")) {
            aa.b = paramAnonymousString.getString("android_sender_id");
          }
          aa.b(paramAnonymousString.getJSONObject("awl_list"));
        }
        catch (Throwable paramAnonymousString)
        {
          for (;;)
          {
            paramAnonymousString.printStackTrace();
          }
        }
        aa.d(true);
        aa.n();
      }
    };
    String str2 = "apps/" + a + "/android_params.js";
    String str3 = c();
    String str1 = str2;
    if (str3 != null) {
      str1 = str2 + "?player_id=" + str3;
    }
    new Thread(new aj(str1, local3)).start();
  }
  
  private static void E()
  {
    Iterator localIterator = E.iterator();
    while (localIterator.hasNext()) {
      b((JSONArray)localIterator.next(), true, false);
    }
    E.clear();
  }
  
  private static void F()
  {
    a(ae.f, "registerUser: registerForPushFired:" + v + ", locationFired: " + w + ", awlFired: " + x, null);
    if ((!v) || (!w) || (!x)) {
      return;
    }
    new Thread(new Runnable()
    {
      /* Error */
      public final void run()
      {
        // Byte code:
        //   0: invokestatic 28	com/onesignal/al:b	()Lcom/onesignal/ao;
        //   3: astore 4
        //   5: getstatic 32	com/onesignal/aa:c	Landroid/content/Context;
        //   8: invokevirtual 38	android/content/Context:getPackageName	()Ljava/lang/String;
        //   11: astore 6
        //   13: getstatic 32	com/onesignal/aa:c	Landroid/content/Context;
        //   16: invokevirtual 42	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
        //   19: astore 5
        //   21: aload 4
        //   23: ldc 44
        //   25: getstatic 48	com/onesignal/aa:a	Ljava/lang/String;
        //   28: invokevirtual 53	com/onesignal/ao:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   31: aload 4
        //   33: ldc 55
        //   35: invokestatic 58	com/onesignal/aa:o	()Ljava/lang/String;
        //   38: invokevirtual 53	com/onesignal/ao:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   41: invokestatic 62	com/onesignal/aa:p	()Lcom/onesignal/f;
        //   44: getstatic 32	com/onesignal/aa:c	Landroid/content/Context;
        //   47: invokevirtual 67	com/onesignal/f:a	(Landroid/content/Context;)Ljava/lang/String;
        //   50: astore_2
        //   51: aload_2
        //   52: ifnull +11 -> 63
        //   55: aload 4
        //   57: ldc 69
        //   59: aload_2
        //   60: invokevirtual 53	com/onesignal/ao:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   63: aload 4
        //   65: ldc 71
        //   67: getstatic 76	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
        //   70: invokevirtual 53	com/onesignal/ao:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   73: aload 4
        //   75: ldc 78
        //   77: invokestatic 82	com/onesignal/aa:q	()I
        //   80: invokestatic 88	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   83: invokevirtual 53	com/onesignal/ao:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   86: invokestatic 94	java/util/Locale:getDefault	()Ljava/util/Locale;
        //   89: invokevirtual 97	java/util/Locale:getLanguage	()Ljava/lang/String;
        //   92: astore_3
        //   93: aload_3
        //   94: ldc 99
        //   96: invokevirtual 105	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   99: ifeq +196 -> 295
        //   102: ldc 107
        //   104: astore_2
        //   105: aload 4
        //   107: ldc 109
        //   109: aload_2
        //   110: invokevirtual 53	com/onesignal/ao:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   113: aload 4
        //   115: ldc 111
        //   117: ldc 113
        //   119: invokevirtual 53	com/onesignal/ao:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   122: aload 4
        //   124: ldc 115
        //   126: getstatic 118	com/onesignal/aa:e	Ljava/lang/String;
        //   129: invokevirtual 53	com/onesignal/ao:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   132: aload 4
        //   134: ldc 120
        //   136: aload 6
        //   138: invokevirtual 53	com/onesignal/ao:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   141: aload 4
        //   143: ldc 122
        //   145: getstatic 127	android/os/Build:MODEL	Ljava/lang/String;
        //   148: invokevirtual 53	com/onesignal/ao:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   151: aload 4
        //   153: ldc -127
        //   155: invokestatic 132	com/onesignal/aa:r	()I
        //   158: invokestatic 88	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   161: invokevirtual 53	com/onesignal/ao:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   164: invokestatic 135	com/onesignal/aa:j	()I
        //   167: istore_1
        //   168: aload 4
        //   170: getfield 138	com/onesignal/ao:a	Lorg/json/JSONObject;
        //   173: ldc -116
        //   175: iload_1
        //   176: invokestatic 88	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   179: invokevirtual 146	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   182: pop
        //   183: aload 4
        //   185: ldc -108
        //   187: aload 5
        //   189: aload 6
        //   191: iconst_0
        //   192: invokevirtual 154	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //   195: getfield 160	android/content/pm/PackageInfo:versionCode	I
        //   198: invokestatic 88	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   201: invokevirtual 53	com/onesignal/ao:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   204: aload 5
        //   206: iconst_0
        //   207: invokevirtual 164	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
        //   210: astore_2
        //   211: new 166	org/json/JSONArray
        //   214: dup
        //   215: invokespecial 167	org/json/JSONArray:<init>	()V
        //   218: astore_3
        //   219: ldc -87
        //   221: invokestatic 175	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
        //   224: astore 5
        //   226: iconst_0
        //   227: istore_1
        //   228: iload_1
        //   229: aload_2
        //   230: invokeinterface 180 1 0
        //   235: if_icmpge +141 -> 376
        //   238: aload 5
        //   240: aload_2
        //   241: iload_1
        //   242: invokeinterface 184 2 0
        //   247: checkcast 156	android/content/pm/PackageInfo
        //   250: getfield 187	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
        //   253: invokevirtual 191	java/lang/String:getBytes	()[B
        //   256: invokevirtual 195	java/security/MessageDigest:update	([B)V
        //   259: aload 5
        //   261: invokevirtual 198	java/security/MessageDigest:digest	()[B
        //   264: iconst_2
        //   265: invokestatic 204	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
        //   268: astore 6
        //   270: invokestatic 208	com/onesignal/aa:s	()Lorg/json/JSONObject;
        //   273: aload 6
        //   275: invokevirtual 212	org/json/JSONObject:has	(Ljava/lang/String;)Z
        //   278: ifeq +10 -> 288
        //   281: aload_3
        //   282: aload 6
        //   284: invokevirtual 215	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   287: pop
        //   288: iload_1
        //   289: iconst_1
        //   290: iadd
        //   291: istore_1
        //   292: goto -64 -> 228
        //   295: aload_3
        //   296: ldc -39
        //   298: invokevirtual 105	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   301: ifeq +9 -> 310
        //   304: ldc -37
        //   306: astore_2
        //   307: goto -202 -> 105
        //   310: aload_3
        //   311: ldc -35
        //   313: invokevirtual 105	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   316: ifeq +9 -> 325
        //   319: ldc -33
        //   321: astore_2
        //   322: goto -217 -> 105
        //   325: aload_3
        //   326: astore_2
        //   327: aload_3
        //   328: ldc -31
        //   330: invokevirtual 105	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   333: ifeq -228 -> 105
        //   336: new 227	java/lang/StringBuilder
        //   339: dup
        //   340: invokespecial 228	java/lang/StringBuilder:<init>	()V
        //   343: aload_3
        //   344: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   347: ldc -22
        //   349: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   352: invokestatic 94	java/util/Locale:getDefault	()Ljava/util/Locale;
        //   355: invokevirtual 237	java/util/Locale:getCountry	()Ljava/lang/String;
        //   358: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   361: invokevirtual 240	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   364: astore_2
        //   365: goto -260 -> 105
        //   368: astore_2
        //   369: aload_2
        //   370: invokevirtual 243	org/json/JSONException:printStackTrace	()V
        //   373: goto -190 -> 183
        //   376: aload 4
        //   378: ldc -11
        //   380: aload_3
        //   381: invokevirtual 53	com/onesignal/ao:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   384: invokestatic 249	com/onesignal/aa:t	()Lcom/onesignal/z;
        //   387: pop
        //   388: aload 4
        //   390: ldc -5
        //   392: invokestatic 256	com/onesignal/z:b	()Ljava/lang/Integer;
        //   395: invokevirtual 53	com/onesignal/ao:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   398: invokestatic 249	com/onesignal/aa:t	()Lcom/onesignal/z;
        //   401: pop
        //   402: getstatic 32	com/onesignal/aa:c	Landroid/content/Context;
        //   405: ldc_w 258
        //   408: invokevirtual 262	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
        //   411: checkcast 264	android/telephony/TelephonyManager
        //   414: invokevirtual 267	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
        //   417: astore_3
        //   418: aload_3
        //   419: astore_2
        //   420: ldc_w 269
        //   423: aload_3
        //   424: invokevirtual 105	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   427: ifeq +5 -> 432
        //   430: aconst_null
        //   431: astore_2
        //   432: aload 4
        //   434: ldc_w 271
        //   437: aload_2
        //   438: invokevirtual 53	com/onesignal/ao:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   441: aload 4
        //   443: ldc_w 273
        //   446: invokestatic 279	com/onesignal/a$1:f	()Z
        //   449: invokestatic 284	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
        //   452: invokevirtual 53	com/onesignal/ao:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   455: aload 4
        //   457: ldc_w 286
        //   460: invokestatic 290	com/onesignal/aa:u	()Ljava/lang/Double;
        //   463: invokevirtual 53	com/onesignal/ao:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   466: aload 4
        //   468: ldc_w 292
        //   471: invokestatic 295	com/onesignal/aa:v	()Ljava/lang/Double;
        //   474: invokevirtual 53	com/onesignal/ao:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   477: aload 4
        //   479: ldc_w 297
        //   482: invokestatic 301	com/onesignal/aa:w	()Ljava/lang/Float;
        //   485: invokevirtual 53	com/onesignal/ao:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   488: aload 4
        //   490: ldc_w 303
        //   493: invokestatic 306	com/onesignal/aa:x	()Ljava/lang/Integer;
        //   496: invokevirtual 53	com/onesignal/ao:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   499: aload 4
        //   501: invokestatic 309	com/onesignal/aa:y	()Z
        //   504: invokestatic 312	com/onesignal/al:a	(Lcom/onesignal/ao;Z)V
        //   507: iconst_0
        //   508: invokestatic 315	com/onesignal/aa:e	(Z)Z
        //   511: pop
        //   512: return
        //   513: astore_2
        //   514: goto -130 -> 384
        //   517: astore_2
        //   518: goto -314 -> 204
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	521	0	this	6
        //   167	125	1	i	int
        //   50	315	2	localObject1	Object
        //   368	2	2	localJSONException	org.json.JSONException
        //   419	19	2	localObject2	Object
        //   513	1	2	localThrowable	Throwable
        //   517	1	2	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
        //   92	332	3	localObject3	Object
        //   3	497	4	localAo	ao
        //   19	241	5	localObject4	Object
        //   11	272	6	str	String
        // Exception table:
        //   from	to	target	type
        //   168	183	368	org/json/JSONException
        //   204	226	513	java/lang/Throwable
        //   228	288	513	java/lang/Throwable
        //   376	384	513	java/lang/Throwable
        //   183	204	517	android/content/pm/PackageManager$NameNotFoundException
      }
    }, "OS_REG_USER").start();
  }
  
  private static void G()
  {
    if (m == null) {}
    String str1;
    do
    {
      String str2;
      do
      {
        return;
        str1 = al.d();
        if (!al.c()) {
          str1 = null;
        }
        str2 = c();
      } while (str2 == null);
      m.a(str2, str1);
    } while (str1 == null);
    m = null;
  }
  
  private static boolean H()
  {
    return (System.currentTimeMillis() - d(c).getLong("OS_LAST_SESSION_TIME", -31000L)) / 1000L >= 30L;
  }
  
  private static void I()
  {
    if (g) {
      return;
    }
    g = true;
    c.startService(new Intent(c, SyncService.class));
  }
  
  public static ab a(Context paramContext)
  {
    return new ab(paramContext, (byte)0);
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
        a(ae.f, "Not a OneSignal formatted GCM message. No 'i' field in custom.", null);
        return null;
      }
    }
    catch (Throwable paramBundle)
    {
      a(ae.f, "Could not parse bundle, probably not a OneSignal notification.", paramBundle);
      return null;
    }
    a(ae.f, "Not a OneSignal formatted GCM message. No 'custom' field in the bundle.", null);
    return null;
  }
  
  static void a()
  {
    I();
    l = true;
    n = SystemClock.elapsedRealtime();
    I = H();
    a(System.currentTimeMillis());
    B();
    if (p != null) {
      p.a();
    }
    a.1.a(c);
  }
  
  static void a(long paramLong)
  {
    SharedPreferences.Editor localEditor = d(c).edit();
    localEditor.putLong("OS_LAST_SESSION_TIME", paramLong);
    localEditor.apply();
  }
  
  /* Error */
  static void a(long paramLong, boolean paramBoolean)
  {
    // Byte code:
    //   0: new 333	org/json/JSONObject
    //   3: dup
    //   4: invokespecial 392	org/json/JSONObject:<init>	()V
    //   7: astore_3
    //   8: aload_3
    //   9: ldc_w 394
    //   12: getstatic 176	com/onesignal/aa:a	Ljava/lang/String;
    //   15: invokevirtual 398	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   18: pop
    //   19: aload_3
    //   20: ldc_w 400
    //   23: iconst_1
    //   24: invokevirtual 403	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   27: pop
    //   28: aload_3
    //   29: ldc_w 405
    //   32: ldc_w 407
    //   35: invokevirtual 398	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   38: pop
    //   39: aload_3
    //   40: ldc_w 409
    //   43: lload_0
    //   44: invokevirtual 412	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   47: pop
    //   48: aload_3
    //   49: ldc_w 414
    //   52: invokestatic 419	com/onesignal/z:b	()Ljava/lang/Integer;
    //   55: invokevirtual 398	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   58: pop
    //   59: new 169	java/lang/StringBuilder
    //   62: dup
    //   63: ldc_w 421
    //   66: invokespecial 174	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   69: invokestatic 188	com/onesignal/aa:c	()Ljava/lang/String;
    //   72: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: ldc_w 423
    //   78: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: invokevirtual 186	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   84: aload_3
    //   85: new 16	com/onesignal/aa$5
    //   88: dup
    //   89: invokespecial 424	com/onesignal/aa$5:<init>	()V
    //   92: invokestatic 427	com/onesignal/a$1:b	(Ljava/lang/String;Lorg/json/JSONObject;Lcom/onesignal/ak;)V
    //   95: return
    //   96: astore_3
    //   97: getstatic 429	com/onesignal/ae:c	Lcom/onesignal/ae;
    //   100: ldc_w 431
    //   103: aload_3
    //   104: invokestatic 242	com/onesignal/aa:a	(Lcom/onesignal/ae;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   107: return
    //   108: astore 4
    //   110: goto -51 -> 59
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	113	0	paramLong	long
    //   0	113	2	paramBoolean	boolean
    //   7	78	3	localJSONObject	JSONObject
    //   96	8	3	localThrowable1	Throwable
    //   108	1	4	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   8	48	96	java/lang/Throwable
    //   59	95	96	java/lang/Throwable
    //   48	59	108	java/lang/Throwable
  }
  
  public static void a(Context paramContext, JSONArray paramJSONArray, boolean paramBoolean)
  {
    boolean bool1 = false;
    int i1 = 0;
    for (;;)
    {
      if (i1 < paramJSONArray.length()) {
        for (;;)
        {
          try
          {
            Object localObject = paramJSONArray.getJSONObject(i1);
            if (!((JSONObject)localObject).has("custom")) {
              break;
            }
            localObject = new JSONObject(((JSONObject)localObject).optString("custom", null));
            if (!((JSONObject)localObject).has("i")) {
              break;
            }
            String str2 = ((JSONObject)localObject).optString("i", null);
            JSONObject localJSONObject = new JSONObject();
            localJSONObject.put("app_id", e(paramContext));
            if (paramContext == null)
            {
              localObject = "";
              localJSONObject.put("player_id", localObject);
              localJSONObject.put("opened", true);
              new Thread(new ah("notifications/" + str2, localJSONObject, new ak()
              {
                final void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
                {
                  aa.a("sending Notification Opened Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
                }
              })).start();
            }
          }
          catch (Throwable localThrowable)
          {
            a(ae.c, "Failed to generate JSON to send notification opened.", localThrowable);
          }
          String str1 = d(paramContext).getString("GT_PLAYER_ID", null);
        }
      }
      boolean bool2 = "DISABLE".equals(z.a(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
      if (!bool2) {
        bool1 = a(paramContext, paramJSONArray);
      }
      b(paramJSONArray, true, paramBoolean);
      if ((!paramBoolean) && (!bool1) && (!bool2))
      {
        paramJSONArray = paramContext.getPackageManager().getLaunchIntentForPackage(paramContext.getPackageName());
        if (paramJSONArray != null)
        {
          paramJSONArray.setFlags(268566528);
          paramContext.startActivity(paramJSONArray);
        }
      }
      return;
      i1 += 1;
    }
  }
  
  public static void a(ac paramAc)
  {
    if (c == null)
    {
      a(ae.c, "You must initialize OneSignal before getting tags! Omitting this tag operation.", null);
      return;
    }
    if (c() == null)
    {
      F = paramAc;
      return;
    }
    b(paramAc);
  }
  
  public static void a(ad paramAd)
  {
    m = paramAd;
    if (c() != null) {
      G();
    }
  }
  
  static void a(ae paramAe, String paramString)
  {
    a(paramAe, paramString, null);
  }
  
  static void a(ae paramAe, final String paramString, Throwable paramThrowable)
  {
    if (paramAe.compareTo(i) <= 0)
    {
      if (paramAe != ae.g) {
        break label146;
      }
      Log.v("OneSignal", paramString, paramThrowable);
    }
    for (;;)
    {
      if ((paramAe.compareTo(h) <= 0) && (a.b != null)) {}
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
          public final void run()
          {
            if (a.b != null) {
              new AlertDialog.Builder(a.b).setTitle(aa.this.toString()).setMessage(paramString).show();
            }
          }
        });
        return;
      }
      catch (Throwable paramAe)
      {
        label146:
        Log.e("OneSignal", "Error showing logging message.", paramAe);
      }
      if (paramAe == ae.f) {
        Log.d("OneSignal", paramString, paramThrowable);
      } else if (paramAe == ae.e) {
        Log.i("OneSignal", paramString, paramThrowable);
      } else if (paramAe == ae.d) {
        Log.w("OneSignal", paramString, paramThrowable);
      } else if ((paramAe == ae.c) || (paramAe == ae.b)) {
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
    paramString = d(c).edit();
    paramString.putString("GT_PLAYER_ID", j);
    paramString.commit();
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean, ak paramAk)
  {
    if (c() == null) {
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
      new Thread(new ai("players/" + c() + "/on_purchase", localJSONObject, paramAk)).start();
      return;
    }
    catch (Throwable paramJSONArray)
    {
      a(ae.c, "Failed to generate JSON for sendPurchases.", paramJSONArray);
    }
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((f == null) || (f.c == null)) {
      return;
    }
    c(paramJSONArray, paramBoolean1, paramBoolean2);
  }
  
  public static void a(JSONObject paramJSONObject)
  {
    if (c == null)
    {
      a(ae.c, "You must initialize OneSignal before modifying tags! Omitting this tag operation.", null);
      return;
    }
    JSONObject localJSONObject1 = al.b(false).b;
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
          a(ae.c, "Omitting key '" + str + "'! sendTags DO NOT supported nested values!", null);
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
      al.a(localJSONObject2);
      return;
    }
  }
  
  static void a(boolean paramBoolean)
  {
    l = false;
    if (!d) {}
    long l1;
    do
    {
      do
      {
        return;
        if (q != null) {
          q.a();
        }
      } while (n == -1L);
      l1 = ((SystemClock.elapsedRealtime() - n) / 1000.0D + 0.5D);
      n = SystemClock.elapsedRealtime();
    } while ((l1 < 0L) || (l1 > 86400L));
    if (c == null)
    {
      a(ae.c, "Android Context not found, please call OneSignal.init when your app starts.", null);
      return;
    }
    a(System.currentTimeMillis());
    l1 += f();
    if ((paramBoolean) || (l1 < 60L) || (c() == null))
    {
      c(l1);
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
          a(ae.c, "Error parsing JSON item " + i1 + "/" + i2 + " for launching a web URL.", localThrowable);
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
    paramJSONObject = c(paramJSONObject);
    return (paramJSONObject == null) || (a(paramJSONObject, paramContext));
  }
  
  private static boolean a(String paramString, Context paramContext)
  {
    if ((paramString == null) || ("".equals(paramString))) {
      return false;
    }
    paramContext = ag.a(paramContext).getReadableDatabase().query("notification", new String[] { "notification_id" }, "notification_id = ?", new String[] { paramString }, null, null, null);
    boolean bool = paramContext.moveToFirst();
    paramContext.close();
    if (bool)
    {
      a(ae.f, "Duplicate GCM message received, skip processing of " + paramString, null);
      return true;
    }
    return false;
  }
  
  static String b()
  {
    return e(c);
  }
  
  private static void b(ac paramAc)
  {
    if (paramAc == null) {
      return;
    }
    new Thread(new Runnable()
    {
      public final void run()
      {
        if (!aa.z()) {}
        am localAm;
        for (boolean bool = true;; bool = false)
        {
          localAm = al.b(bool);
          if (localAm.a) {
            aa.f(true);
          }
          if ((localAm.b != null) && (!localAm.toString().equals("{}"))) {
            break;
          }
          aa.this.a(null);
          return;
        }
        aa.this.a(localAm.b);
      }
    }, "OS_GETTAGS_CALLBACK").start();
  }
  
  static void b(String paramString)
  {
    a(paramString);
    if (m != null) {
      a(new Runnable()
      {
        public final void run() {}
      });
    }
    b(F);
  }
  
  private static void b(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((f == null) || (f.b == null)) {
      E.add(paramJSONArray);
    }
    do
    {
      return;
      paramJSONArray = c(paramJSONArray, true, paramBoolean2);
    } while (Looper.getMainLooper().getThread() == Thread.currentThread());
    a(new Runnable()
    {
      public final void run()
      {
        ab localAb = aa.f;
      }
    });
  }
  
  static boolean b(Context paramContext)
  {
    return d(paramContext).getBoolean("GT_VIBRATE_ENABLED", true);
  }
  
  private static v c(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject1 = null;
    int i3 = paramJSONArray.length();
    int i2 = 1;
    v localV = new v();
    r localR = new r();
    paramJSONArray.optJSONObject(0).optInt("notificationId");
    int i1 = 0;
    if (i1 < i3) {}
    try
    {
      Object localObject2 = paramJSONArray.getJSONObject(i1);
      if (!((JSONObject)localObject2).has("custom")) {
        break label268;
      }
      localR.a = a.1.a((JSONObject)localObject2);
      if ((localObject1 != null) || (!((JSONObject)localObject2).has("actionSelected"))) {
        break label268;
      }
      localObject2 = ((JSONObject)localObject2).optString("actionSelected", null);
      localObject1 = localObject2;
    }
    catch (Throwable localThrowable2)
    {
      for (;;) {}
    }
    if (i2 != 0) {
      i2 = 0;
    }
    for (;;)
    {
      i1 += 1;
      break;
      try
      {
        if (localR.b == null) {
          localR.b = new ArrayList();
        }
        localR.b.add(localR.a);
      }
      catch (Throwable localThrowable1) {}
      a(ae.c, "Error parsing JSON item " + i1 + "/" + i3 + " for callback.", localThrowable1);
    }
    localV.a = localR;
    localV.b = new t();
    if (localObject1 != null) {}
    for (i1 = u.b; paramBoolean2; i1 = u.a)
    {
      i1 = s.b;
      return localV;
    }
    i1 = s.a;
    return localV;
  }
  
  static String c()
  {
    if ((j == null) && (c != null)) {
      j = d(c).getString("GT_PLAYER_ID", null);
    }
    return j;
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
    o = paramLong;
    if (c == null) {
      return;
    }
    a(ae.e, "SaveUnsentActiveTime: " + o, null);
    SharedPreferences.Editor localEditor = d(c).edit();
    localEditor.putLong("GT_UNSENT_ACTIVE_TIME", paramLong);
    localEditor.commit();
  }
  
  static boolean c(Context paramContext)
  {
    return d(paramContext).getBoolean("GT_SOUND_ENABLED", true);
  }
  
  static SharedPreferences d(Context paramContext)
  {
    return paramContext.getSharedPreferences(aa.class.getSimpleName(), 0);
  }
  
  private static void d(String paramString)
  {
    if (c == null) {
      return;
    }
    SharedPreferences.Editor localEditor = d(c).edit();
    localEditor.putString("GT_APP_ID", paramString);
    localEditor.commit();
  }
  
  static boolean d()
  {
    if (f == null) {}
    while (f.d == af.b) {
      return true;
    }
    return false;
  }
  
  private static String e(Context paramContext)
  {
    if (paramContext == null) {
      return "";
    }
    return d(paramContext).getString("GT_APP_ID", null);
  }
  
  static boolean e()
  {
    if (f == null) {}
    while (f.d != af.a) {
      return false;
    }
    return true;
  }
  
  static long f()
  {
    if ((o == -1L) && (c != null)) {
      o = d(c).getLong("GT_UNSENT_ACTIVE_TIME", 0L);
    }
    a(ae.e, "GetUnsentActiveTime: " + o, null);
    return o;
  }
  
  static boolean g()
  {
    return (d) && (l);
  }
  
  static void h()
  {
    I = false;
    a(System.currentTimeMillis());
  }
}
