package com.onesignal;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import com.amazon.device.iap.PurchasingListener;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class v
{
  private static Double A;
  private static Double B;
  private static Float C;
  private static Integer D;
  private static boolean E;
  private static b F;
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
  static a f;
  static Collection<JSONArray> g;
  static boolean h;
  private static d i = d.a;
  private static d j = d.d;
  private static String k = null;
  private static int l;
  private static boolean m;
  private static c n;
  private static long o = 1L;
  private static long p = -1L;
  private static af q;
  private static ae r;
  private static d s = new c();
  private static int t;
  private static u u;
  private static String v;
  private static boolean w;
  private static boolean x;
  private static boolean y;
  private static boolean z;
  
  static
  {
    e = "native";
    E = true;
    g = new ArrayList();
  }
  
  public v() {}
  
  private static void H()
  {
    int i2 = -1;
    boolean bool = false;
    if (H) {
      return;
    }
    H = true;
    w = false;
    if (I) {
      x = false;
    }
    Object localObject;
    int i1;
    int i3;
    if (E)
    {
      localObject = c;
      if ((f.d) && (!z))
      {
        i1 = 1;
        i.b local1 = new i.b()
        {
          public final void a(Double paramAnonymousDouble1, Double paramAnonymousDouble2, Float paramAnonymousFloat, Integer paramAnonymousInteger)
          {
            v.a(paramAnonymousDouble1);
            v.b(paramAnonymousDouble2);
            v.a(paramAnonymousFloat);
            v.a(paramAnonymousInteger);
            v.i();
            v.j();
          }
        };
        i.c = local1;
        i3 = e.a.a((Context)localObject, "android.permission.ACCESS_FINE_LOCATION");
        if (i3 == -1)
        {
          i2 = e.a.a((Context)localObject, "android.permission.ACCESS_COARSE_LOCATION");
          i.d = true;
        }
        if (Build.VERSION.SDK_INT >= 23) {
          break label159;
        }
        if ((i3 == 0) || (i2 == 0)) {
          break label153;
        }
        local1.a(null, null, null, null);
      }
    }
    for (;;)
    {
      J();
      if ((z) || (f.d)) {
        bool = true;
      }
      z = bool;
      return;
      i1 = 0;
      break;
      label153:
      i.a();
      continue;
      label159:
      if (i3 != 0)
      {
        for (;;)
        {
          try
          {
            localObject = Arrays.asList(((Context)localObject).getPackageManager().getPackageInfo(((Context)localObject).getPackageName(), 4096).requestedPermissions);
            if (!((List)localObject).contains("android.permission.ACCESS_FINE_LOCATION")) {
              break label230;
            }
            i.b = "android.permission.ACCESS_FINE_LOCATION";
            if ((i.b == null) || (i1 == 0)) {
              break label254;
            }
            PermissionsActivity.a();
          }
          catch (Throwable localThrowable)
          {
            localThrowable.printStackTrace();
          }
          break;
          label230:
          if ((localThrowable.contains("android.permission.ACCESS_COARSE_LOCATION")) && (i2 != 0)) {
            i.b = "android.permission.ACCESS_COARSE_LOCATION";
          }
        }
        label254:
        if (i2 == 0) {
          i.a();
        } else {
          i.b();
        }
      }
      else
      {
        i.a();
        continue;
        x = true;
        L();
      }
    }
  }
  
  private static void I()
  {
    if (t == 2) {}
    for (Object localObject = new aa();; localObject = new ab())
    {
      ((z)localObject).a(c, b, new z.a()
      {
        public final void a(String paramAnonymousString, int paramAnonymousInt)
        {
          if (paramAnonymousInt <= 0) {
            if ((y.d() == null) && ((v.k() == 1) || (v.k() < -6))) {
              v.a(paramAnonymousInt);
            }
          }
          for (;;)
          {
            v.c(paramAnonymousString);
            v.l();
            v.j();
            return;
            if (v.k() < -6) {
              v.a(paramAnonymousInt);
            }
          }
        }
      });
      return;
    }
  }
  
  private static void J()
  {
    if (y)
    {
      I();
      return;
    }
    x.a local5 = new x.a()
    {
      final void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
      {
        new Thread(new Runnable()
        {
          public final void run()
          {
            try
            {
              Thread.sleep(v.m() * 10000 + 30000);
              v.n();
              v.o();
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
            v.b = paramAnonymousString.getString("android_sender_id");
          }
          v.a(paramAnonymousString.getJSONObject("awl_list"));
        }
        catch (Throwable paramAnonymousString)
        {
          for (;;)
          {
            paramAnonymousString.printStackTrace();
          }
        }
        v.p();
        v.q();
      }
    };
    String str2 = "apps/" + a + "/android_params.js";
    String str3 = c();
    String str1 = str2;
    if (str3 != null) {
      str1 = str2 + "?player_id=" + str3;
    }
    new Thread(new x.3(str1, local5)).start();
  }
  
  private static void K()
  {
    Iterator localIterator = g.iterator();
    while (localIterator.hasNext()) {
      a((JSONArray)localIterator.next(), false);
    }
    g.clear();
  }
  
  private static void L()
  {
    a(d.f, "registerUser: registerForPushFired:" + w + ", locationFired: " + x + ", awlFired: " + y, null);
    if ((!w) || (!x) || (!y)) {
      return;
    }
    new Thread(new Runnable()
    {
      /* Error */
      public final void run()
      {
        // Byte code:
        //   0: iconst_0
        //   1: istore_1
        //   2: invokestatic 28	com/onesignal/y:b	()Lcom/onesignal/y$c;
        //   5: astore 5
        //   7: getstatic 32	com/onesignal/v:c	Landroid/content/Context;
        //   10: invokevirtual 38	android/content/Context:getPackageName	()Ljava/lang/String;
        //   13: astore 7
        //   15: getstatic 32	com/onesignal/v:c	Landroid/content/Context;
        //   18: invokevirtual 42	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
        //   21: astore 6
        //   23: aload 5
        //   25: ldc 44
        //   27: getstatic 48	com/onesignal/v:a	Ljava/lang/String;
        //   30: invokevirtual 53	com/onesignal/y$c:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   33: aload 5
        //   35: ldc 55
        //   37: invokestatic 58	com/onesignal/v:s	()Ljava/lang/String;
        //   40: invokevirtual 53	com/onesignal/y$c:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   43: invokestatic 62	com/onesignal/v:t	()Lcom/onesignal/d;
        //   46: getstatic 32	com/onesignal/v:c	Landroid/content/Context;
        //   49: invokeinterface 67 2 0
        //   54: astore_3
        //   55: aload_3
        //   56: ifnull +11 -> 67
        //   59: aload 5
        //   61: ldc 69
        //   63: aload_3
        //   64: invokevirtual 53	com/onesignal/y$c:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   67: aload 5
        //   69: ldc 71
        //   71: getstatic 76	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
        //   74: invokevirtual 53	com/onesignal/y$c:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   77: aload 5
        //   79: ldc 78
        //   81: invokestatic 82	com/onesignal/v:u	()I
        //   84: invokestatic 88	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   87: invokevirtual 53	com/onesignal/y$c:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   90: invokestatic 94	java/util/Locale:getDefault	()Ljava/util/Locale;
        //   93: invokevirtual 97	java/util/Locale:getLanguage	()Ljava/lang/String;
        //   96: astore 4
        //   98: aload 4
        //   100: ldc 99
        //   102: invokevirtual 105	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   105: ifeq +196 -> 301
        //   108: ldc 107
        //   110: astore_3
        //   111: aload 5
        //   113: ldc 109
        //   115: aload_3
        //   116: invokevirtual 53	com/onesignal/y$c:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   119: aload 5
        //   121: ldc 111
        //   123: ldc 113
        //   125: invokevirtual 53	com/onesignal/y$c:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   128: aload 5
        //   130: ldc 115
        //   132: getstatic 118	com/onesignal/v:e	Ljava/lang/String;
        //   135: invokevirtual 53	com/onesignal/y$c:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   138: aload 5
        //   140: ldc 120
        //   142: aload 7
        //   144: invokevirtual 53	com/onesignal/y$c:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   147: aload 5
        //   149: ldc 122
        //   151: getstatic 127	android/os/Build:MODEL	Ljava/lang/String;
        //   154: invokevirtual 53	com/onesignal/y$c:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   157: aload 5
        //   159: ldc -127
        //   161: invokestatic 132	com/onesignal/v:v	()I
        //   164: invokestatic 88	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   167: invokevirtual 53	com/onesignal/y$c:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   170: invokestatic 135	com/onesignal/v:k	()I
        //   173: istore_2
        //   174: aload 5
        //   176: getfield 138	com/onesignal/y$c:a	Lorg/json/JSONObject;
        //   179: ldc -116
        //   181: iload_2
        //   182: invokestatic 88	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   185: invokevirtual 146	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   188: pop
        //   189: aload 5
        //   191: ldc -108
        //   193: aload 6
        //   195: aload 7
        //   197: iconst_0
        //   198: invokevirtual 154	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //   201: getfield 160	android/content/pm/PackageInfo:versionCode	I
        //   204: invokestatic 88	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
        //   207: invokevirtual 53	com/onesignal/y$c:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   210: aload 6
        //   212: iconst_0
        //   213: invokevirtual 164	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
        //   216: astore_3
        //   217: new 166	org/json/JSONArray
        //   220: dup
        //   221: invokespecial 167	org/json/JSONArray:<init>	()V
        //   224: astore 4
        //   226: ldc -87
        //   228: invokestatic 175	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
        //   231: astore 6
        //   233: iload_1
        //   234: aload_3
        //   235: invokeinterface 180 1 0
        //   240: if_icmpge +147 -> 387
        //   243: aload 6
        //   245: aload_3
        //   246: iload_1
        //   247: invokeinterface 184 2 0
        //   252: checkcast 156	android/content/pm/PackageInfo
        //   255: getfield 187	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
        //   258: invokevirtual 191	java/lang/String:getBytes	()[B
        //   261: invokevirtual 195	java/security/MessageDigest:update	([B)V
        //   264: aload 6
        //   266: invokevirtual 198	java/security/MessageDigest:digest	()[B
        //   269: iconst_2
        //   270: invokestatic 204	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
        //   273: astore 7
        //   275: invokestatic 208	com/onesignal/v:w	()Lorg/json/JSONObject;
        //   278: aload 7
        //   280: invokevirtual 212	org/json/JSONObject:has	(Ljava/lang/String;)Z
        //   283: ifeq +11 -> 294
        //   286: aload 4
        //   288: aload 7
        //   290: invokevirtual 215	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   293: pop
        //   294: iload_1
        //   295: iconst_1
        //   296: iadd
        //   297: istore_1
        //   298: goto -65 -> 233
        //   301: aload 4
        //   303: ldc -39
        //   305: invokevirtual 105	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   308: ifeq +9 -> 317
        //   311: ldc -37
        //   313: astore_3
        //   314: goto -203 -> 111
        //   317: aload 4
        //   319: ldc -35
        //   321: invokevirtual 105	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   324: ifeq +9 -> 333
        //   327: ldc -33
        //   329: astore_3
        //   330: goto -219 -> 111
        //   333: aload 4
        //   335: astore_3
        //   336: aload 4
        //   338: ldc -31
        //   340: invokevirtual 105	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   343: ifeq -232 -> 111
        //   346: new 227	java/lang/StringBuilder
        //   349: dup
        //   350: invokespecial 228	java/lang/StringBuilder:<init>	()V
        //   353: aload 4
        //   355: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   358: ldc -22
        //   360: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   363: invokestatic 94	java/util/Locale:getDefault	()Ljava/util/Locale;
        //   366: invokevirtual 237	java/util/Locale:getCountry	()Ljava/lang/String;
        //   369: invokevirtual 232	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   372: invokevirtual 240	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   375: astore_3
        //   376: goto -265 -> 111
        //   379: astore_3
        //   380: aload_3
        //   381: invokevirtual 243	org/json/JSONException:printStackTrace	()V
        //   384: goto -195 -> 189
        //   387: aload 5
        //   389: ldc -11
        //   391: aload 4
        //   393: invokevirtual 53	com/onesignal/y$c:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   396: invokestatic 249	com/onesignal/v:x	()Lcom/onesignal/u;
        //   399: pop
        //   400: aload 5
        //   402: ldc -5
        //   404: invokestatic 256	com/onesignal/u:b	()Ljava/lang/Integer;
        //   407: invokevirtual 53	com/onesignal/y$c:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   410: invokestatic 249	com/onesignal/v:x	()Lcom/onesignal/u;
        //   413: pop
        //   414: getstatic 32	com/onesignal/v:c	Landroid/content/Context;
        //   417: ldc_w 258
        //   420: invokevirtual 262	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
        //   423: checkcast 264	android/telephony/TelephonyManager
        //   426: invokevirtual 267	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
        //   429: astore 4
        //   431: aload 4
        //   433: astore_3
        //   434: ldc_w 269
        //   437: aload 4
        //   439: invokevirtual 105	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   442: ifeq +5 -> 447
        //   445: aconst_null
        //   446: astore_3
        //   447: aload 5
        //   449: ldc_w 271
        //   452: aload_3
        //   453: invokevirtual 53	com/onesignal/y$c:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   456: aload 5
        //   458: ldc_w 273
        //   461: invokestatic 278	com/onesignal/ad:a	()Z
        //   464: invokestatic 283	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
        //   467: invokevirtual 53	com/onesignal/y$c:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   470: aload 5
        //   472: ldc_w 285
        //   475: invokestatic 289	com/onesignal/v:y	()Ljava/lang/Double;
        //   478: invokevirtual 53	com/onesignal/y$c:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   481: aload 5
        //   483: ldc_w 291
        //   486: invokestatic 294	com/onesignal/v:z	()Ljava/lang/Double;
        //   489: invokevirtual 53	com/onesignal/y$c:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   492: aload 5
        //   494: ldc_w 296
        //   497: invokestatic 300	com/onesignal/v:A	()Ljava/lang/Float;
        //   500: invokevirtual 53	com/onesignal/y$c:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   503: aload 5
        //   505: ldc_w 302
        //   508: invokestatic 305	com/onesignal/v:B	()Ljava/lang/Integer;
        //   511: invokevirtual 53	com/onesignal/y$c:a	(Ljava/lang/String;Ljava/lang/Object;)V
        //   514: aload 5
        //   516: invokestatic 308	com/onesignal/v:C	()Z
        //   519: invokestatic 311	com/onesignal/y:a	(Lcom/onesignal/y$c;Z)V
        //   522: invokestatic 314	com/onesignal/v:D	()Z
        //   525: pop
        //   526: return
        //   527: astore_3
        //   528: goto -132 -> 396
        //   531: astore_3
        //   532: goto -322 -> 210
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	535	0	this	8
        //   1	297	1	i	int
        //   173	9	2	j	int
        //   54	322	3	localObject1	Object
        //   379	2	3	localJSONException	JSONException
        //   433	20	3	localObject2	Object
        //   527	1	3	localThrowable	Throwable
        //   531	1	3	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
        //   96	342	4	localObject3	Object
        //   5	510	5	localC	y.c
        //   21	244	6	localObject4	Object
        //   13	276	7	str	String
        // Exception table:
        //   from	to	target	type
        //   174	189	379	org/json/JSONException
        //   210	233	527	java/lang/Throwable
        //   233	294	527	java/lang/Throwable
        //   387	396	527	java/lang/Throwable
        //   189	210	531	android/content/pm/PackageManager$NameNotFoundException
      }
    }, "OS_REG_USER").start();
  }
  
  private static void M()
  {
    if (n == null) {}
    String str;
    do
    {
      do
      {
        return;
        str = y.d();
        if (!y.c()) {
          str = null;
        }
      } while (c() == null);
      n.a();
    } while (str == null);
    n = null;
  }
  
  private static boolean N()
  {
    return (System.currentTimeMillis() - d(c).getLong("OS_LAST_SESSION_TIME", -31000L)) / 1000L >= 30L;
  }
  
  private static void O()
  {
    if (h) {
      return;
    }
    h = true;
    c.startService(new Intent(c, SyncService.class));
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
        a(d.f, "Not a OneSignal formatted GCM message. No 'i' field in custom.", null);
        return null;
      }
    }
    catch (Throwable paramBundle)
    {
      a(d.f, "Could not parse bundle, probably not a OneSignal notification.", paramBundle);
      return null;
    }
    a(d.f, "Not a OneSignal formatted GCM message. No 'custom' field in the bundle.", null);
    return null;
  }
  
  static void a()
  {
    O();
    m = true;
    o = SystemClock.elapsedRealtime();
    I = N();
    b(System.currentTimeMillis());
    H();
    if (q != null) {
      q.a();
    }
    n.a(c);
  }
  
  /* Error */
  static void a(long paramLong)
  {
    // Byte code:
    //   0: new 413	org/json/JSONObject
    //   3: dup
    //   4: invokespecial 462	org/json/JSONObject:<init>	()V
    //   7: astore_2
    //   8: aload_2
    //   9: ldc_w 464
    //   12: getstatic 270	com/onesignal/v:a	Ljava/lang/String;
    //   15: invokevirtual 468	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   18: pop
    //   19: aload_2
    //   20: ldc_w 470
    //   23: iconst_1
    //   24: invokevirtual 473	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   27: pop
    //   28: aload_2
    //   29: ldc_w 475
    //   32: ldc_w 477
    //   35: invokevirtual 468	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   38: pop
    //   39: aload_2
    //   40: ldc_w 479
    //   43: lload_0
    //   44: invokevirtual 482	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   47: pop
    //   48: aload_2
    //   49: ldc_w 484
    //   52: invokestatic 488	com/onesignal/u:b	()Ljava/lang/Integer;
    //   55: invokevirtual 468	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   58: pop
    //   59: new 263	java/lang/StringBuilder
    //   62: dup
    //   63: ldc_w 490
    //   66: invokespecial 268	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   69: invokestatic 281	com/onesignal/v:c	()Ljava/lang/String;
    //   72: invokevirtual 274	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: ldc_w 492
    //   78: invokevirtual 274	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: invokevirtual 279	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   84: aload_2
    //   85: new 22	com/onesignal/v$7
    //   88: dup
    //   89: invokespecial 493	com/onesignal/v$7:<init>	()V
    //   92: invokestatic 498	com/onesignal/x:a	(Ljava/lang/String;Lorg/json/JSONObject;Lcom/onesignal/x$a;)V
    //   95: return
    //   96: astore_2
    //   97: getstatic 500	com/onesignal/v$d:c	Lcom/onesignal/v$d;
    //   100: ldc_w 502
    //   103: aload_2
    //   104: invokestatic 334	com/onesignal/v:a	(Lcom/onesignal/v$d;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   107: return
    //   108: astore_3
    //   109: goto -50 -> 59
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	112	0	paramLong	long
    //   7	78	2	localJSONObject	JSONObject
    //   96	8	2	localThrowable1	Throwable
    //   108	1	3	localThrowable2	Throwable
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
              new Thread(new x.1("notifications/" + str2, localJSONObject, new x.a()
              {
                final void a(int paramAnonymousInt, String paramAnonymousString, Throwable paramAnonymousThrowable)
                {
                  v.a("sending Notification Opened Failed", paramAnonymousInt, paramAnonymousThrowable, paramAnonymousString);
                }
              })).start();
            }
          }
          catch (Throwable localThrowable)
          {
            a(d.c, "Failed to generate JSON to send notification opened.", localThrowable);
          }
          String str1 = d(paramContext).getString("GT_PLAYER_ID", null);
        }
      }
      boolean bool2 = "DISABLE".equals(u.a(paramContext, "com.onesignal.NotificationOpened.DEFAULT"));
      if (!bool2) {
        bool1 = a(paramContext, paramJSONArray);
      }
      a(paramJSONArray, paramBoolean);
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
  
  public static void a(c paramC)
  {
    n = paramC;
    if (c() != null) {
      M();
    }
  }
  
  static void a(d paramD, String paramString)
  {
    a(paramD, paramString, null);
  }
  
  static void a(d paramD, final String paramString, Throwable paramThrowable)
  {
    if ((paramD.compareTo(j) <= 0) && (paramD != d.g) && (paramD != d.f) && (paramD != d.e))
    {
      if (paramD != d.d) {
        break label167;
      }
      Log.w("OneSignal", paramString, paramThrowable);
    }
    for (;;)
    {
      if ((paramD.compareTo(i) <= 0) && (a.b != null)) {}
      Object localObject;
      label167:
      try
      {
        localObject = paramString + "\n";
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
              new AlertDialog.Builder(a.b).setTitle(this.a.toString()).setMessage(paramString).show();
            }
          }
        });
        return;
      }
      catch (Throwable paramD) {}
      if (paramD != d.c) {
        localObject = d.b;
      }
    }
  }
  
  static void a(Runnable paramRunnable)
  {
    new Handler(Looper.getMainLooper()).post(paramRunnable);
  }
  
  static void a(String paramString)
  {
    k = paramString;
    if (c == null) {
      return;
    }
    paramString = d(c).edit();
    paramString.putString("GT_PLAYER_ID", k);
    paramString.commit();
  }
  
  public static void a(String paramString1, String paramString2)
  {
    try
    {
      b(new JSONObject().put(paramString1, paramString2));
      return;
    }
    catch (JSONException paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  private static void a(JSONArray paramJSONArray, boolean paramBoolean)
  {
    if ((f == null) || (f.b == null)) {
      g.add(paramJSONArray);
    }
    do
    {
      return;
      paramJSONArray = b(paramJSONArray, true, paramBoolean);
    } while (Looper.getMainLooper().getThread() == Thread.currentThread());
    a(new Runnable()
    {
      public final void run()
      {
        v.a localA = v.f;
      }
    });
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean, x.a paramA)
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
      new Thread(new x.2("players/" + c() + "/on_purchase", localJSONObject, paramA)).start();
      return;
    }
    catch (Throwable paramJSONArray)
    {
      a(d.c, "Failed to generate JSON for sendPurchases.", paramJSONArray);
    }
  }
  
  static void a(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((f == null) || (f.c == null)) {
      return;
    }
    b(paramJSONArray, paramBoolean1, paramBoolean2);
  }
  
  static void a(boolean paramBoolean)
  {
    m = false;
    if (!d) {}
    for (;;)
    {
      return;
      ae localAe;
      if (r != null)
      {
        localAe = r;
        if (!localAe.a) {}
      }
      try
      {
        PurchasingListener localPurchasingListener = (PurchasingListener)localAe.d.get(localAe.c);
        if (localPurchasingListener != localAe.b)
        {
          localAe.b.a = localPurchasingListener;
          localAe.a();
        }
        if (o == -1L) {
          continue;
        }
        long l1 = ((SystemClock.elapsedRealtime() - o) / 1000.0D + 0.5D);
        o = SystemClock.elapsedRealtime();
        if ((l1 < 0L) || (l1 > 86400L)) {
          continue;
        }
        if (c == null)
        {
          a(d.c, "Android Context not found, please call OneSignal.init when your app starts.", null);
          return;
        }
        b(System.currentTimeMillis());
        l1 += f();
        if ((paramBoolean) || (l1 < 60L) || (c() == null))
        {
          c(l1);
          return;
        }
        a(l1);
        return;
      }
      catch (Throwable localThrowable)
      {
        for (;;) {}
      }
    }
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
    paramJSONObject = c(paramJSONObject);
    return (paramJSONObject == null) || (a(paramJSONObject, paramContext));
  }
  
  private static boolean a(String paramString, Context paramContext)
  {
    if ((paramString == null) || ("".equals(paramString))) {
      return false;
    }
    paramContext = w.a(paramContext).getReadableDatabase().query("notification", new String[] { "notification_id" }, "notification_id = ?", new String[] { paramString }, null, null, null);
    boolean bool = paramContext.moveToFirst();
    paramContext.close();
    if (bool)
    {
      a(d.f, "Duplicate GCM message received, skip processing of " + paramString, null);
      return true;
    }
    return false;
  }
  
  private static r b(JSONArray paramJSONArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    Object localObject1 = null;
    int i3 = paramJSONArray.length();
    int i2 = 1;
    r localR = new r();
    o localO = new o();
    localO.a = g();
    localO.b = paramBoolean1;
    localO.c = paramJSONArray.optJSONObject(0).optInt("notificationId");
    int i1 = 0;
    if (i1 < i3) {}
    try
    {
      Object localObject2 = paramJSONArray.getJSONObject(i1);
      if (!((JSONObject)localObject2).has("custom")) {
        break label321;
      }
      localO.d = j.a((JSONObject)localObject2);
      if ((localObject1 != null) || (!((JSONObject)localObject2).has("actionSelected"))) {
        break label321;
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
        if (localO.f == null) {
          localO.f = new ArrayList();
        }
        localO.f.add(localO.d);
      }
      catch (Throwable localThrowable1) {}
      a(d.c, "Error parsing JSON item " + i1 + "/" + i3 + " for callback.", localThrowable1);
    }
    localR.a = localO;
    localR.b = new p();
    localR.b.b = localObject1;
    paramJSONArray = localR.b;
    if (localObject1 != null) {}
    for (i1 = p.a.b;; i1 = p.a.a)
    {
      paramJSONArray.a = i1;
      if (!paramBoolean2) {
        break;
      }
      localR.a.e = o.a.b;
      return localR;
    }
    localR.a.e = o.a.a;
    return localR;
  }
  
  static String b()
  {
    return e(c);
  }
  
  static void b(long paramLong)
  {
    SharedPreferences.Editor localEditor = d(c).edit();
    localEditor.putLong("OS_LAST_SESSION_TIME", paramLong);
    localEditor.apply();
  }
  
  static void b(String paramString)
  {
    a(paramString);
    if (n != null) {
      a(new Runnable()
      {
        public final void run() {}
      });
    }
    paramString = F;
    if (paramString != null) {
      new Thread(new Runnable()
      {
        public final void run()
        {
          if (!v.E()) {}
          for (boolean bool = true;; bool = false)
          {
            y.a localA = y.c(bool);
            if (localA.a) {
              v.F();
            }
            if (localA.b != null) {
              localA.toString().equals("{}");
            }
            return;
          }
        }
      }, "OS_GETTAGS_CALLBACK").start();
    }
  }
  
  private static void b(JSONObject paramJSONObject)
  {
    if (c == null) {
      a(d.c, "You must initialize OneSignal before modifying tags! Omitting this tag operation.", null);
    }
    while (paramJSONObject == null) {
      return;
    }
    JSONObject localJSONObject1 = y.c(false).b;
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
          a(d.c, "Omitting key '" + str + "'! sendTags DO NOT supported nested values!", null);
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
      y.a(localJSONObject2);
      return;
    }
  }
  
  public static void b(boolean paramBoolean)
  {
    if (c == null)
    {
      a(d.c, "OneSignal.init has not been called. Could not set subscription.", null);
      return;
    }
    y.b(paramBoolean);
  }
  
  static boolean b(Context paramContext)
  {
    return d(paramContext).getBoolean("GT_VIBRATE_ENABLED", true);
  }
  
  static String c()
  {
    if ((k == null) && (c != null)) {
      k = d(c).getString("GT_PLAYER_ID", null);
    }
    return k;
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
    p = paramLong;
    if (c == null) {
      return;
    }
    a(d.e, "SaveUnsentActiveTime: " + p, null);
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
    return paramContext.getSharedPreferences(v.class.getSimpleName(), 0);
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
    while (f.f == g.c) {
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
    while (f.f != g.b) {
      return false;
    }
    return true;
  }
  
  static long f()
  {
    if ((p == -1L) && (c != null)) {
      p = d(c).getLong("GT_UNSENT_ACTIVE_TIME", 0L);
    }
    a(d.e, "GetUnsentActiveTime: " + p, null);
    return p;
  }
  
  static boolean g()
  {
    return (d) && (m);
  }
  
  static void h()
  {
    I = false;
    b(System.currentTimeMillis());
  }
  
  public static final class a
  {
    Context a;
    v.e b;
    v.f c;
    boolean d;
    boolean e;
    public int f = v.g.b;
    
    private a() {}
    
    private a(Context paramContext)
    {
      this.a = paramContext;
    }
  }
  
  public static abstract interface b {}
  
  public static abstract interface c
  {
    public abstract void a();
  }
  
  public static enum d
  {
    private d() {}
  }
  
  public static abstract interface e {}
  
  public static abstract interface f {}
  
  public static enum g {}
}
