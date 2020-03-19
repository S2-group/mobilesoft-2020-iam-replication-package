package com.kochava.android.tracker;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.kochava.android.a.b;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class c
{
  private static String D;
  private static String E;
  private static String F;
  private static int G = 7;
  private static int H = 60;
  private static boolean J = false;
  private static boolean K = false;
  private static boolean L = false;
  private static boolean M = true;
  private static boolean N = false;
  private static long O = 0L;
  private static long P = 0L;
  private static String Q = "";
  private static Map<String, String> R;
  private static List<w> S = new ArrayList();
  private static a T;
  private static Timer V;
  private static boolean Y = true;
  private static boolean Z = false;
  protected static String a;
  private static boolean aa = false;
  private static long ac = 0L;
  private static boolean ad = false;
  private static boolean ae = true;
  private static boolean af = false;
  private static boolean ag = false;
  private static SharedPreferences aj;
  private static SharedPreferences ak;
  private static final ExecutorService al = Executors.newFixedThreadPool(1);
  private static Handler am;
  private static final ScheduledExecutorService an = Executors.newSingleThreadScheduledExecutor();
  private static final Uri ao = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
  private static HashMap<String, Boolean> ap = new d();
  private static JSONArray aq = new JSONArray();
  private static Runnable as = new p();
  protected static boolean b;
  protected static Context c;
  protected static Handler g = new l();
  private static Map<String, String> h;
  private static JSONObject i;
  private static String j = "";
  private static int k;
  private static boolean l;
  private static boolean m;
  private static String w;
  private static String y;
  private static String z;
  private String A;
  private int B;
  private int C;
  private boolean I = false;
  private boolean U = true;
  private Timer W;
  private Timer X;
  private Timer ab;
  private JSONObject ah;
  private JSONObject ai;
  private Runnable ar = new o(this);
  private Handler at = null;
  private final String au = "location_accuracy";
  private final String av = "location_timeout";
  private final String aw = "location_staleness";
  protected JSONObject d;
  protected JSONObject e;
  protected JSONObject f;
  private Context n;
  private String o;
  private String p;
  private String q;
  private String r;
  private String s;
  private String t;
  private String u;
  private String v;
  private String x;
  
  static
  {
    a = "";
    b = false;
    k = 60000;
    l = false;
    m = false;
  }
  
  public c(Context paramContext, HashMap<String, Object> paramHashMap)
  {
    a(paramContext, true, paramHashMap);
  }
  
  private String H()
  {
    String str = "" + Q() + ":::";
    str = str + L() + ":::";
    str = str + K() + ":::";
    str = str + O() + ":::";
    return str + M();
  }
  
  /* Error */
  private static void I()
  {
    // Byte code:
    //   0: getstatic 274	android/os/Build$VERSION:SDK_INT	I
    //   3: bipush 14
    //   5: if_icmplt +261 -> 266
    //   8: getstatic 107	com/kochava/android/tracker/c:b	Z
    //   11: ifne +255 -> 266
    //   14: getstatic 151	com/kochava/android/tracker/c:ad	Z
    //   17: ifeq +10 -> 27
    //   20: ldc_w 276
    //   23: invokestatic 281	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   26: return
    //   27: ldc_w 283
    //   30: invokestatic 285	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   33: getstatic 143	com/kochava/android/tracker/c:Y	Z
    //   36: ifne +46 -> 82
    //   39: getstatic 287	com/kochava/android/tracker/c:V	Ljava/util/Timer;
    //   42: ifnonnull +189 -> 231
    //   45: ldc_w 289
    //   48: invokestatic 285	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   51: new 291	java/util/Timer
    //   54: dup
    //   55: invokespecial 292	java/util/Timer:<init>	()V
    //   58: putstatic 287	com/kochava/android/tracker/c:V	Ljava/util/Timer;
    //   61: getstatic 287	com/kochava/android/tracker/c:V	Ljava/util/Timer;
    //   64: new 294	com/kochava/android/tracker/r
    //   67: dup
    //   68: invokespecial 295	com/kochava/android/tracker/r:<init>	()V
    //   71: getstatic 110	com/kochava/android/tracker/c:k	I
    //   74: i2l
    //   75: getstatic 110	com/kochava/android/tracker/c:k	I
    //   78: i2l
    //   79: invokevirtual 299	java/util/Timer:schedule	(Ljava/util/TimerTask;JJ)V
    //   82: invokestatic 305	java/lang/System:currentTimeMillis	()J
    //   85: ldc2_w 306
    //   88: ldiv
    //   89: putstatic 132	com/kochava/android/tracker/c:P	J
    //   92: getstatic 128	com/kochava/android/tracker/c:N	Z
    //   95: ifeq +18 -> 113
    //   98: invokestatic 309	com/kochava/android/tracker/c:Y	()Z
    //   101: ifeq +12 -> 113
    //   104: getstatic 311	com/kochava/android/tracker/c:c	Landroid/content/Context;
    //   107: invokestatic 316	com/kochava/android/tracker/ab:a	(Landroid/content/Context;)Lcom/kochava/android/tracker/ab;
    //   110: invokevirtual 318	com/kochava/android/tracker/ab:a	()V
    //   113: getstatic 320	com/kochava/android/tracker/c:aj	Landroid/content/SharedPreferences;
    //   116: ifnull +49 -> 165
    //   119: getstatic 320	com/kochava/android/tracker/c:aj	Landroid/content/SharedPreferences;
    //   122: ldc_w 322
    //   125: ldc 101
    //   127: invokeinterface 328 3 0
    //   132: ldc 101
    //   134: invokevirtual 334	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   137: istore_0
    //   138: iload_0
    //   139: ifne +26 -> 165
    //   142: new 336	org/json/JSONObject
    //   145: dup
    //   146: getstatic 320	com/kochava/android/tracker/c:aj	Landroid/content/SharedPreferences;
    //   149: ldc_w 322
    //   152: ldc 101
    //   154: invokeinterface 328 3 0
    //   159: invokespecial 338	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   162: invokestatic 341	com/kochava/android/tracker/c:c	(Lorg/json/JSONObject;)V
    //   165: getstatic 320	com/kochava/android/tracker/c:aj	Landroid/content/SharedPreferences;
    //   168: ifnull +98 -> 266
    //   171: getstatic 320	com/kochava/android/tracker/c:aj	Landroid/content/SharedPreferences;
    //   174: ldc_w 343
    //   177: ldc 101
    //   179: invokeinterface 328 3 0
    //   184: ldc_w 345
    //   187: invokevirtual 334	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   190: ifeq +76 -> 266
    //   193: getstatic 153	com/kochava/android/tracker/c:ae	Z
    //   196: ifeq +64 -> 260
    //   199: ldc_w 347
    //   202: invokestatic 349	com/kochava/android/tracker/c:f	(Ljava/lang/String;)V
    //   205: return
    //   206: astore_1
    //   207: new 249	java/lang/StringBuilder
    //   210: dup
    //   211: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   214: ldc_w 351
    //   217: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: aload_1
    //   221: invokevirtual 354	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   224: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   227: invokestatic 281	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   230: return
    //   231: ldc_w 356
    //   234: invokestatic 285	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   237: goto -155 -> 82
    //   240: astore_1
    //   241: ldc_w 358
    //   244: invokestatic 281	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   247: getstatic 361	com/kochava/android/tracker/aa:b	Z
    //   250: ifeq -85 -> 165
    //   253: aload_1
    //   254: invokevirtual 364	org/json/JSONException:printStackTrace	()V
    //   257: goto -92 -> 165
    //   260: ldc_w 366
    //   263: invokestatic 285	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   266: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   137	2	0	bool	boolean
    //   206	15	1	localException	Exception
    //   240	14	1	localJSONException	JSONException
    // Exception table:
    //   from	to	target	type
    //   0	26	206	java/lang/Exception
    //   27	82	206	java/lang/Exception
    //   82	113	206	java/lang/Exception
    //   113	138	206	java/lang/Exception
    //   142	165	206	java/lang/Exception
    //   165	205	206	java/lang/Exception
    //   231	237	206	java/lang/Exception
    //   241	257	206	java/lang/Exception
    //   260	266	206	java/lang/Exception
    //   142	165	240	org/json/JSONException
  }
  
  private static void J()
  {
    for (;;)
    {
      try
      {
        if ((Build.VERSION.SDK_INT < 14) || (b)) {
          return;
        }
        if (ad)
        {
          b.b("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
          return;
        }
        b.a("Automatic Session End");
        if (!Y)
        {
          if (V != null)
          {
            b.a("Session end, flush timer was on, canceling timer and flushing current events.");
            a();
            V.cancel();
            V = null;
          }
        }
        else
        {
          if (!ae) {
            break;
          }
          f("exit");
          return;
        }
      }
      catch (Exception localException)
      {
        b.b("(Automatic Session End) Exception occured during session tracking.\n" + localException);
        return;
      }
      b.a("Session end, flush timer was already off.");
    }
    b.a("Session events disabled by server.");
  }
  
  private static String K()
  {
    return Build.BRAND;
  }
  
  private static String L()
  {
    return Build.MODEL;
  }
  
  private static String M()
  {
    return "Android " + Build.VERSION.RELEASE;
  }
  
  private static boolean N()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (ak != null)
    {
      bool1 = bool2;
      if (!ak.getAll().isEmpty()) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private String O()
  {
    return this.q + " " + this.r;
  }
  
  private String P()
  {
    if (this.t != null) {
      return this.t;
    }
    return "Unknown";
  }
  
  private static String Q()
  {
    if ((aj.contains("kochava_app_id_generated")) && (!aj.getString("kochava_app_id_generated", "").equals(""))) {
      return aj.getString("kochava_app_id_generated", "");
    }
    String str = UUID.randomUUID().toString().replaceAll("-", "");
    str = "KA" + str;
    aj.edit().putString("kochava_app_id_generated", str).commit();
    return str;
  }
  
  private void R()
  {
    if (this.at == null) {
      this.at = new e(this);
    }
  }
  
  private void S()
  {
    int i2 = 0;
    aj = this.n.getSharedPreferences("initPrefs", 0);
    if (aj.getString("initBool", "").equals("")) {
      aj.edit().putString("initBool", "false").commit();
    }
    String str1;
    String str2;
    if (aj.getString("kochavaappdata", null) != null)
    {
      str1 = T.a(aj.getString("kochavaappdata", null));
      str2 = H();
      b.a("Stored Data: " + str1);
      b.a("Created Data: " + str2);
      if (str1 == null) {
        T.b(aj.getString("kochavaappdata", null), str2);
      }
    }
    for (;;)
    {
      if ((this.U) || ((!aj.getString("initBool", "").equals("")) && (aj.getString("initBool", "").equals("false"))))
      {
        b.a("Initial event has not yet been qued in the database, making initial call");
        b("initial", null);
      }
      int i1;
      if (!Y)
      {
        i1 = i2;
        if (!Y)
        {
          i1 = i2;
          if (aa) {}
        }
      }
      else
      {
        i1 = 1;
      }
      if ((i1 != 0) && (V == null))
      {
        V = new Timer();
        V.schedule(new i(this), 0L, k);
      }
      this.X = new Timer();
      return;
      if (!str1.equals(str2))
      {
        T.a(aj.getString("kochavaappdata", null), str2);
      }
      else
      {
        b.a("Set start of life to false");
        this.U = false;
        continue;
        this.U = false;
      }
    }
  }
  
  private static String T()
  {
    int i1 = 1;
    if (!aj.getString("initBool", "").equals("true"))
    {
      b.a("PREF_INIT not true, waiting for initial to be queued");
      localObject1 = "";
      return localObject1;
    }
    Object localObject1 = T.a();
    if (localObject1 == null) {
      return "";
    }
    Object localObject3 = new String[2];
    localObject1 = ((String)localObject1).split("=", 2);
    long l1 = Long.parseLong(localObject1[0]);
    localObject3 = localObject1[1];
    localObject1 = localObject3;
    if (((String)localObject3).startsWith("[")) {
      localObject1 = ((String)localObject3).substring(1);
    }
    if (((String)localObject1).endsWith("]")) {
      localObject1 = ((String)localObject1).substring(0, ((String)localObject1).length() - 1);
    }
    for (;;)
    {
      b.a("Post The Data 3>>>>>>" + (String)localObject1);
      if (((String)localObject1).contains("\"action\":\"initial\"")) {
        b.b("Post Data: Event is initial, look at response");
      }
      for (;;)
      {
        if ((j == null) || (j.trim().isEmpty()))
        {
          b.a("postEvent - hostControl was empty, using default");
          j = "control.kochava.com";
        }
        try
        {
          b.a("postEvent - posting to " + "https://" + j + "/track/kvTracker.php");
          localObject3 = (HttpsURLConnection)new URL("https://" + j + "/track/kvTracker.php").openConnection();
          ((HttpsURLConnection)localObject3).setRequestProperty("User-Agent", z);
          ((HttpsURLConnection)localObject3).setRequestProperty("Content-Type", "application/json; charset=UTF-8");
          ((HttpsURLConnection)localObject3).setRequestMethod("POST");
          ((HttpsURLConnection)localObject3).setConnectTimeout(30000);
          ((HttpsURLConnection)localObject3).setReadTimeout(30000);
          ((HttpsURLConnection)localObject3).setDoInput(true);
          ((HttpsURLConnection)localObject3).setDoOutput(true);
          ((HttpsURLConnection)localObject3).connect();
          localObject4 = new OutputStreamWriter(((HttpsURLConnection)localObject3).getOutputStream());
          ((OutputStreamWriter)localObject4).write((String)localObject1);
          ((OutputStreamWriter)localObject4).close();
        }
        catch (Exception localException)
        {
          for (;;)
          {
            Object localObject4;
            b.b("TrackTask " + localException);
            return "";
            b.b("TrackTask " + localException);
          }
        }
        catch (IOException localIOException2)
        {
          label393:
          if (!localIOException2.getClass().equals(SSLException.class)) {
            break label709;
          }
        }
        try
        {
          b.a("Grabbing Result...");
          localObject1 = new StringBuffer("");
          localObject3 = new BufferedReader(new InputStreamReader(((HttpsURLConnection)localObject3).getInputStream()));
          localObject4 = ((BufferedReader)localObject3).readLine();
          if (localObject4 != null)
          {
            ((StringBuffer)localObject1).append((String)localObject4);
            break label393;
          }
        }
        catch (IOException localIOException1)
        {
          if (!localIOException1.getClass().equals(SSLException.class)) {
            break label639;
          }
          b.b("SSLException! Shutting down SDK and sending report." + localIOException1);
          b(localIOException1);
          return "";
          localObject3 = localIOException1.toString();
          b.a("Result: " + (String)localObject3);
          if (i1 != 0)
          {
            if (((String)localObject3).contains("\"success\":\"1\""))
            {
              b.a("Got success response, cleaning database.");
              T.a(l1);
            }
            Object localObject2 = localObject3;
            if (N()) {
              break;
            }
            localObject2 = localObject3;
            if (!ag) {
              break;
            }
            b.a("Requesting attribution data in " + G + " seconds...");
            a(G);
            return localObject3;
          }
        }
        catch (OutOfMemoryError localOutOfMemoryError)
        {
          b.b("TrackTask " + localOutOfMemoryError);
          return "";
        }
        T.a(l1);
        return localObject3;
        label639:
        b.b("SSLException! Shutting down SDK and sending report." + localIOException2);
        b(localIOException2);
        for (;;)
        {
          return "";
          label709:
          b.b("TrackTask " + localIOException2);
        }
        i1 = 0;
      }
    }
  }
  
  /* Error */
  private String U()
  {
    // Byte code:
    //   0: ldc 101
    //   2: astore 4
    //   4: ldc 101
    //   6: astore_3
    //   7: new 249	java/lang/StringBuilder
    //   10: dup
    //   11: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   14: ldc 101
    //   16: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: ldc_w 678
    //   22: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   25: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   28: astore 5
    //   30: ldc_w 680
    //   33: invokestatic 683	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   36: astore_3
    //   37: aload_3
    //   38: invokevirtual 548	java/lang/String:trim	()Ljava/lang/String;
    //   41: invokevirtual 549	java/lang/String:isEmpty	()Z
    //   44: ifeq +662 -> 706
    //   47: iconst_0
    //   48: istore_1
    //   49: aload 5
    //   51: astore 4
    //   53: aload_3
    //   54: astore_2
    //   55: iload_1
    //   56: ifne +77 -> 133
    //   59: new 249	java/lang/StringBuilder
    //   62: dup
    //   63: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   66: aload 5
    //   68: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: ldc_w 685
    //   74: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   77: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   80: astore 4
    //   82: aload_3
    //   83: astore_2
    //   84: new 687	android/webkit/WebView
    //   87: dup
    //   88: aload_0
    //   89: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   92: invokespecial 690	android/webkit/WebView:<init>	(Landroid/content/Context;)V
    //   95: invokevirtual 694	android/webkit/WebView:getSettings	()Landroid/webkit/WebSettings;
    //   98: invokevirtual 699	android/webkit/WebSettings:getUserAgentString	()Ljava/lang/String;
    //   101: astore_3
    //   102: aload_3
    //   103: astore_2
    //   104: new 249	java/lang/StringBuilder
    //   107: dup
    //   108: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   111: aload 4
    //   113: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: ldc_w 701
    //   119: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   122: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   125: astore 5
    //   127: aload 5
    //   129: astore 4
    //   131: aload_3
    //   132: astore_2
    //   133: aload_2
    //   134: invokevirtual 548	java/lang/String:trim	()Ljava/lang/String;
    //   137: invokevirtual 549	java/lang/String:isEmpty	()Z
    //   140: ifeq +561 -> 701
    //   143: iconst_0
    //   144: istore_1
    //   145: aload 4
    //   147: astore 5
    //   149: aload_2
    //   150: astore 6
    //   152: iload_1
    //   153: ifne +148 -> 301
    //   156: new 249	java/lang/StringBuilder
    //   159: dup
    //   160: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   163: aload 4
    //   165: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   168: ldc_w 703
    //   171: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   177: astore 5
    //   179: ldc_w 696
    //   182: iconst_2
    //   183: anewarray 705	java/lang/Class
    //   186: dup
    //   187: iconst_0
    //   188: ldc_w 456
    //   191: aastore
    //   192: dup
    //   193: iconst_1
    //   194: ldc_w 687
    //   197: aastore
    //   198: invokevirtual 709	java/lang/Class:getDeclaredConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   201: astore_3
    //   202: aload_3
    //   203: astore 4
    //   205: aload_2
    //   206: astore 6
    //   208: aload_3
    //   209: iconst_1
    //   210: invokevirtual 714	java/lang/reflect/Constructor:setAccessible	(Z)V
    //   213: aload_3
    //   214: astore 4
    //   216: aload_2
    //   217: astore 6
    //   219: aload_3
    //   220: iconst_2
    //   221: anewarray 4	java/lang/Object
    //   224: dup
    //   225: iconst_0
    //   226: aload_0
    //   227: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   230: aastore
    //   231: dup
    //   232: iconst_1
    //   233: aconst_null
    //   234: aastore
    //   235: invokevirtual 718	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   238: checkcast 696	android/webkit/WebSettings
    //   241: invokevirtual 699	android/webkit/WebSettings:getUserAgentString	()Ljava/lang/String;
    //   244: astore_2
    //   245: aload_3
    //   246: astore 4
    //   248: aload_2
    //   249: astore 6
    //   251: new 249	java/lang/StringBuilder
    //   254: dup
    //   255: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   258: aload 5
    //   260: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   263: ldc_w 720
    //   266: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   269: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   272: astore 7
    //   274: aload 7
    //   276: astore 4
    //   278: aload 4
    //   280: astore 5
    //   282: aload_2
    //   283: astore 6
    //   285: aload_3
    //   286: ifnull +15 -> 301
    //   289: aload_3
    //   290: iconst_0
    //   291: invokevirtual 714	java/lang/reflect/Constructor:setAccessible	(Z)V
    //   294: aload_2
    //   295: astore 6
    //   297: aload 4
    //   299: astore 5
    //   301: new 249	java/lang/StringBuilder
    //   304: dup
    //   305: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   308: ldc_w 722
    //   311: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   314: aload 5
    //   316: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   319: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   322: invokestatic 285	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   325: aload 6
    //   327: areturn
    //   328: astore_2
    //   329: new 724	java/io/StringWriter
    //   332: dup
    //   333: invokespecial 725	java/io/StringWriter:<init>	()V
    //   336: astore 5
    //   338: aload_2
    //   339: new 727	java/io/PrintWriter
    //   342: dup
    //   343: aload 5
    //   345: invokespecial 730	java/io/PrintWriter:<init>	(Ljava/io/Writer;)V
    //   348: invokevirtual 733	java/lang/Exception:printStackTrace	(Ljava/io/PrintWriter;)V
    //   351: aload 5
    //   353: invokevirtual 734	java/io/StringWriter:toString	()Ljava/lang/String;
    //   356: invokestatic 281	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   359: new 249	java/lang/StringBuilder
    //   362: dup
    //   363: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   366: aload_3
    //   367: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   370: ldc_w 736
    //   373: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   376: aload_2
    //   377: invokevirtual 737	java/lang/Exception:toString	()Ljava/lang/String;
    //   380: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   383: ldc_w 739
    //   386: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   389: aload 5
    //   391: invokevirtual 734	java/io/StringWriter:toString	()Ljava/lang/String;
    //   394: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   397: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   400: astore 5
    //   402: aload 4
    //   404: astore_3
    //   405: goto -368 -> 37
    //   408: astore_2
    //   409: aload 5
    //   411: astore 4
    //   413: aload_2
    //   414: astore 5
    //   416: aload_3
    //   417: astore_2
    //   418: aload 5
    //   420: astore_3
    //   421: new 724	java/io/StringWriter
    //   424: dup
    //   425: invokespecial 725	java/io/StringWriter:<init>	()V
    //   428: astore 5
    //   430: aload_3
    //   431: new 727	java/io/PrintWriter
    //   434: dup
    //   435: aload 5
    //   437: invokespecial 730	java/io/PrintWriter:<init>	(Ljava/io/Writer;)V
    //   440: invokevirtual 733	java/lang/Exception:printStackTrace	(Ljava/io/PrintWriter;)V
    //   443: aload 5
    //   445: invokevirtual 734	java/io/StringWriter:toString	()Ljava/lang/String;
    //   448: invokestatic 281	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   451: new 249	java/lang/StringBuilder
    //   454: dup
    //   455: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   458: aload 4
    //   460: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   463: ldc_w 741
    //   466: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   469: aload_3
    //   470: invokevirtual 737	java/lang/Exception:toString	()Ljava/lang/String;
    //   473: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   476: ldc_w 739
    //   479: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   482: aload 5
    //   484: invokevirtual 734	java/io/StringWriter:toString	()Ljava/lang/String;
    //   487: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   490: ldc_w 743
    //   493: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   496: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   499: astore 4
    //   501: goto -368 -> 133
    //   504: astore 5
    //   506: aconst_null
    //   507: astore_3
    //   508: aload 4
    //   510: astore 6
    //   512: aload_3
    //   513: astore 4
    //   515: new 724	java/io/StringWriter
    //   518: dup
    //   519: invokespecial 725	java/io/StringWriter:<init>	()V
    //   522: astore 7
    //   524: aload_3
    //   525: astore 4
    //   527: aload 5
    //   529: new 727	java/io/PrintWriter
    //   532: dup
    //   533: aload 7
    //   535: invokespecial 730	java/io/PrintWriter:<init>	(Ljava/io/Writer;)V
    //   538: invokevirtual 733	java/lang/Exception:printStackTrace	(Ljava/io/PrintWriter;)V
    //   541: aload_3
    //   542: astore 4
    //   544: aload 7
    //   546: invokevirtual 734	java/io/StringWriter:toString	()Ljava/lang/String;
    //   549: invokestatic 281	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   552: aload_3
    //   553: astore 4
    //   555: new 249	java/lang/StringBuilder
    //   558: dup
    //   559: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   562: aload 6
    //   564: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   567: ldc_w 745
    //   570: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   573: aload 5
    //   575: invokevirtual 737	java/lang/Exception:toString	()Ljava/lang/String;
    //   578: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   581: ldc_w 739
    //   584: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   587: aload 7
    //   589: invokevirtual 734	java/io/StringWriter:toString	()Ljava/lang/String;
    //   592: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   595: ldc_w 743
    //   598: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   601: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   604: astore 5
    //   606: aload 5
    //   608: astore 4
    //   610: aload 4
    //   612: astore 5
    //   614: aload_2
    //   615: astore 6
    //   617: aload_3
    //   618: ifnull -317 -> 301
    //   621: aload_3
    //   622: iconst_0
    //   623: invokevirtual 714	java/lang/reflect/Constructor:setAccessible	(Z)V
    //   626: aload 4
    //   628: astore 5
    //   630: aload_2
    //   631: astore 6
    //   633: goto -332 -> 301
    //   636: astore_2
    //   637: aconst_null
    //   638: astore 4
    //   640: aload 4
    //   642: ifnull +9 -> 651
    //   645: aload 4
    //   647: iconst_0
    //   648: invokevirtual 714	java/lang/reflect/Constructor:setAccessible	(Z)V
    //   651: aload_2
    //   652: athrow
    //   653: astore_2
    //   654: goto -14 -> 640
    //   657: astore 4
    //   659: aconst_null
    //   660: astore_3
    //   661: aload 5
    //   663: astore 6
    //   665: aload 4
    //   667: astore 5
    //   669: goto -157 -> 512
    //   672: astore_2
    //   673: aload 5
    //   675: astore 4
    //   677: aload_2
    //   678: astore 5
    //   680: aload 6
    //   682: astore_2
    //   683: aload 4
    //   685: astore 6
    //   687: goto -175 -> 512
    //   690: astore_3
    //   691: goto -270 -> 421
    //   694: astore_2
    //   695: aload 5
    //   697: astore_3
    //   698: goto -369 -> 329
    //   701: iconst_1
    //   702: istore_1
    //   703: goto -558 -> 145
    //   706: iconst_1
    //   707: istore_1
    //   708: goto -659 -> 49
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	711	0	this	c
    //   48	660	1	i1	int
    //   54	241	2	localObject1	Object
    //   328	49	2	localException1	Exception
    //   408	6	2	localException2	Exception
    //   417	214	2	localObject2	Object
    //   636	16	2	localObject3	Object
    //   653	1	2	localObject4	Object
    //   672	6	2	localException3	Exception
    //   682	1	2	localObject5	Object
    //   694	1	2	localException4	Exception
    //   6	655	3	localObject6	Object
    //   690	1	3	localException5	Exception
    //   697	1	3	localObject7	Object
    //   2	644	4	localObject8	Object
    //   657	9	4	localException6	Exception
    //   675	9	4	localObject9	Object
    //   28	455	5	localObject10	Object
    //   504	70	5	localException7	Exception
    //   604	92	5	localObject11	Object
    //   150	536	6	localObject12	Object
    //   272	316	7	localObject13	Object
    // Exception table:
    //   from	to	target	type
    //   7	30	328	java/lang/Exception
    //   59	82	408	java/lang/Exception
    //   156	179	504	java/lang/Exception
    //   156	179	636	finally
    //   179	202	636	finally
    //   208	213	653	finally
    //   219	245	653	finally
    //   251	274	653	finally
    //   515	524	653	finally
    //   527	541	653	finally
    //   544	552	653	finally
    //   555	606	653	finally
    //   179	202	657	java/lang/Exception
    //   208	213	672	java/lang/Exception
    //   219	245	672	java/lang/Exception
    //   251	274	672	java/lang/Exception
    //   84	102	690	java/lang/Exception
    //   104	127	690	java/lang/Exception
    //   30	37	694	java/lang/Exception
  }
  
  private static String V()
  {
    try
    {
      Object localObject = (AudioManager)c.getSystemService("audio");
      localObject = ((AudioManager)localObject).getStreamVolume(3) + "";
      return localObject;
    }
    catch (Exception localException) {}
    return "";
  }
  
  private String W()
  {
    for (;;)
    {
      int i1;
      try
      {
        i1 = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.n);
        if (i1 != 0) {}
        switch (i1)
        {
        case 4: 
        case 5: 
        case 6: 
        case 7: 
        case 8: 
          b.a("Google Play Services check returned unknown error code (" + i1 + ").");
          b.b("Problem getting Advertising ID " + GooglePlayServicesUtil.getErrorString(i1));
          AdvertisingIdClient.Info localInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.n);
          String str = localInfo.getId();
          J = localInfo.isLimitAdTrackingEnabled();
          return str;
        }
      }
      catch (Exception localException)
      {
        b.b("Problem getting Advertising ID (catch): " + localException.toString());
        return "";
      }
      b.a("Google Play Services check returned ConnectionResult.SUCCESS (" + i1 + ").");
      continue;
      b.a("Google Play Services check returned ConnectionResult.SERVICE_MISSING (" + i1 + ").");
      continue;
      b.a("Google Play Services check returned ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED (" + i1 + ").");
      continue;
      b.a("Google Play Services check returned ConnectionResult.SERVICE_DISABLED (" + i1 + ").");
      continue;
      b.a("Google Play Services check returned ConnectionResult.SERVICE_INVALID (" + i1 + ").");
    }
  }
  
  private String X()
  {
    Object localObject1 = "";
    if (this.n.checkCallingOrSelfPermission("android.permission.GET_ACCOUNTS") == 0)
    {
      Account[] arrayOfAccount = AccountManager.get(this.n).getAccounts();
      int i2 = arrayOfAccount.length;
      int i1 = 0;
      while (i1 < i2)
      {
        Account localAccount = arrayOfAccount[i1];
        Object localObject2 = localObject1;
        if (g(localAccount.name))
        {
          localObject2 = localAccount.name.toLowerCase();
          localObject2 = (String)localObject1 + (String)localObject2 + ",";
        }
        i1 += 1;
        localObject1 = localObject2;
      }
      if (((String)localObject1).length() > 0) {
        localObject1 = ((String)localObject1).substring(0, ((String)localObject1).length() - 1);
      }
    }
    for (;;)
    {
      return "[" + (String)localObject1 + "]";
      localObject1 = "";
      continue;
      localObject1 = "";
      b.a("****NOTICE**** Gathering of emails was whitelisted, but android.permission.GET_ACCOUNTS declaration was missing from manifest.");
    }
  }
  
  private static boolean Y()
  {
    long l2 = ab.b * 60 * 1000;
    if ((aj != null) && (aj.getLong("kochava_old_loc_timestamp", 0L) != 0L)) {}
    for (long l1 = aj.getLong("kochava_old_loc_timestamp", 0L);; l1 = 0L)
    {
      if (l1 == 0L) {}
      while (System.currentTimeMillis() - l1 >= l2) {
        return true;
      }
      return false;
    }
  }
  
  /* Error */
  protected static String a(android.content.ContentResolver paramContentResolver)
  {
    // Byte code:
    //   0: aload_0
    //   1: getstatic 181	com/kochava/android/tracker/c:ao	Landroid/net/Uri;
    //   4: iconst_1
    //   5: anewarray 330	java/lang/String
    //   8: dup
    //   9: iconst_0
    //   10: ldc_w 841
    //   13: aastore
    //   14: aconst_null
    //   15: aconst_null
    //   16: aconst_null
    //   17: invokevirtual 847	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   20: astore_0
    //   21: aload_0
    //   22: ifnull +16 -> 38
    //   25: aload_0
    //   26: astore_2
    //   27: aload_0
    //   28: invokeinterface 852 1 0
    //   33: istore_1
    //   34: iload_1
    //   35: ifne +24 -> 59
    //   38: aload_0
    //   39: ifnull +18 -> 57
    //   42: aload_0
    //   43: invokeinterface 855 1 0
    //   48: ifne +9 -> 57
    //   51: aload_0
    //   52: invokeinterface 856 1 0
    //   57: aconst_null
    //   58: areturn
    //   59: aload_0
    //   60: astore_2
    //   61: aload_0
    //   62: aload_0
    //   63: ldc_w 841
    //   66: invokeinterface 859 2 0
    //   71: invokeinterface 861 2 0
    //   76: astore_3
    //   77: aload_3
    //   78: astore_2
    //   79: aload_0
    //   80: ifnull +22 -> 102
    //   83: aload_3
    //   84: astore_2
    //   85: aload_0
    //   86: invokeinterface 855 1 0
    //   91: ifne +11 -> 102
    //   94: aload_0
    //   95: invokeinterface 856 1 0
    //   100: aload_3
    //   101: astore_2
    //   102: aload_2
    //   103: areturn
    //   104: astore_3
    //   105: aconst_null
    //   106: astore_0
    //   107: aload_0
    //   108: astore_2
    //   109: new 249	java/lang/StringBuilder
    //   112: dup
    //   113: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   116: ldc_w 863
    //   119: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   122: aload_3
    //   123: invokevirtual 737	java/lang/Exception:toString	()Ljava/lang/String;
    //   126: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   129: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   132: invokestatic 285	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   135: aload_0
    //   136: ifnull +18 -> 154
    //   139: aload_0
    //   140: invokeinterface 855 1 0
    //   145: ifne +9 -> 154
    //   148: aload_0
    //   149: invokeinterface 856 1 0
    //   154: aconst_null
    //   155: astore_2
    //   156: goto -54 -> 102
    //   159: astore_0
    //   160: aconst_null
    //   161: astore_2
    //   162: goto -60 -> 102
    //   165: astore_0
    //   166: aconst_null
    //   167: astore_2
    //   168: aload_2
    //   169: ifnull +18 -> 187
    //   172: aload_2
    //   173: invokeinterface 855 1 0
    //   178: ifne +9 -> 187
    //   181: aload_2
    //   182: invokeinterface 856 1 0
    //   187: aload_0
    //   188: athrow
    //   189: astore_2
    //   190: goto -3 -> 187
    //   193: astore_0
    //   194: goto -26 -> 168
    //   197: astore_3
    //   198: goto -91 -> 107
    //   201: astore_0
    //   202: aload_3
    //   203: astore_2
    //   204: goto -102 -> 102
    //   207: astore_0
    //   208: aconst_null
    //   209: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	210	0	paramContentResolver	android.content.ContentResolver
    //   33	2	1	bool	boolean
    //   26	156	2	localObject1	Object
    //   189	1	2	localException1	Exception
    //   203	1	2	localObject2	Object
    //   76	25	3	str	String
    //   104	19	3	localException2	Exception
    //   197	6	3	localException3	Exception
    // Exception table:
    //   from	to	target	type
    //   0	21	104	java/lang/Exception
    //   139	154	159	java/lang/Exception
    //   0	21	165	finally
    //   172	187	189	java/lang/Exception
    //   27	34	193	finally
    //   61	77	193	finally
    //   109	135	193	finally
    //   27	34	197	java/lang/Exception
    //   61	77	197	java/lang/Exception
    //   85	100	201	java/lang/Exception
    //   42	57	207	java/lang/Exception
  }
  
  public static void a()
  {
    if (ad)
    {
      b.b("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      return;
    }
    b.a("flush");
    al.submit(new z(null));
  }
  
  protected static void a(int paramInt)
  {
    an.schedule(as, paramInt, TimeUnit.SECONDS);
  }
  
  /* Error */
  @android.annotation.TargetApi(14)
  private void a(Context paramContext, boolean paramBoolean, HashMap<String, Object> paramHashMap)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore 5
    //   3: aload_1
    //   4: ifnull +2331 -> 2335
    //   7: aload_0
    //   8: aload_1
    //   9: putfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   12: iload_2
    //   13: putstatic 126	com/kochava/android/tracker/c:M	Z
    //   16: ldc_w 904
    //   19: new 249	java/lang/StringBuilder
    //   22: dup
    //   23: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   26: ldc_w 906
    //   29: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: getstatic 105	com/kochava/android/tracker/c:a	Ljava/lang/String;
    //   35: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   41: invokestatic 911	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   44: pop
    //   45: getstatic 311	com/kochava/android/tracker/c:c	Landroid/content/Context;
    //   48: ifnonnull +10 -> 58
    //   51: aload_1
    //   52: invokevirtual 915	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   55: putstatic 311	com/kochava/android/tracker/c:c	Landroid/content/Context;
    //   58: aload_0
    //   59: new 291	java/util/Timer
    //   62: dup
    //   63: invokespecial 292	java/util/Timer:<init>	()V
    //   66: putfield 917	com/kochava/android/tracker/c:ab	Ljava/util/Timer;
    //   69: aload_0
    //   70: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   73: ldc_w 454
    //   76: iconst_0
    //   77: invokevirtual 460	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   80: putstatic 320	com/kochava/android/tracker/c:aj	Landroid/content/SharedPreferences;
    //   83: new 468	com/kochava/android/tracker/a
    //   86: dup
    //   87: aload_0
    //   88: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   91: invokespecial 918	com/kochava/android/tracker/a:<init>	(Landroid/content/Context;)V
    //   94: putstatic 466	com/kochava/android/tracker/c:T	Lcom/kochava/android/tracker/a;
    //   97: aload_3
    //   98: ifnull +231 -> 329
    //   101: aload_3
    //   102: ldc_w 920
    //   105: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   108: ifnull +44 -> 152
    //   111: aload_3
    //   112: ldc_w 920
    //   115: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   118: invokevirtual 645	java/lang/Object:getClass	()Ljava/lang/Class;
    //   121: ldc_w 927
    //   124: invokevirtual 648	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   127: ifeq +25 -> 152
    //   130: aload_3
    //   131: ldc_w 920
    //   134: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   137: checkcast 927	java/lang/Boolean
    //   140: invokevirtual 930	java/lang/Boolean:booleanValue	()Z
    //   143: istore_2
    //   144: iload_2
    //   145: invokestatic 932	com/kochava/android/tracker/c:a	(Z)V
    //   148: iload_2
    //   149: invokestatic 934	com/kochava/android/tracker/c:b	(Z)V
    //   152: aload_3
    //   153: ldc_w 936
    //   156: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   159: ifnull +35 -> 194
    //   162: aload_3
    //   163: ldc_w 936
    //   166: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   169: invokevirtual 645	java/lang/Object:getClass	()Ljava/lang/Class;
    //   172: ldc_w 330
    //   175: invokevirtual 648	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   178: ifeq +16 -> 194
    //   181: aload_3
    //   182: ldc_w 936
    //   185: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   188: checkcast 330	java/lang/String
    //   191: putstatic 105	com/kochava/android/tracker/c:a	Ljava/lang/String;
    //   194: aload_3
    //   195: ldc_w 938
    //   198: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   201: ifnull +38 -> 239
    //   204: aload_3
    //   205: ldc_w 938
    //   208: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   211: invokevirtual 645	java/lang/Object:getClass	()Ljava/lang/Class;
    //   214: ldc_w 927
    //   217: invokevirtual 648	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   220: ifeq +19 -> 239
    //   223: aload_3
    //   224: ldc_w 938
    //   227: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   230: checkcast 927	java/lang/Boolean
    //   233: invokevirtual 930	java/lang/Boolean:booleanValue	()Z
    //   236: putstatic 107	com/kochava/android/tracker/c:b	Z
    //   239: aload_3
    //   240: ldc_w 940
    //   243: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   246: ifnull +38 -> 284
    //   249: aload_3
    //   250: ldc_w 940
    //   253: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   256: invokevirtual 645	java/lang/Object:getClass	()Ljava/lang/Class;
    //   259: ldc_w 927
    //   262: invokevirtual 648	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   265: ifeq +19 -> 284
    //   268: aload_3
    //   269: ldc_w 940
    //   272: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   275: checkcast 927	java/lang/Boolean
    //   278: invokevirtual 930	java/lang/Boolean:booleanValue	()Z
    //   281: putstatic 124	com/kochava/android/tracker/c:L	Z
    //   284: aload_3
    //   285: ldc_w 942
    //   288: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   291: ifnull +38 -> 329
    //   294: aload_3
    //   295: ldc_w 942
    //   298: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   301: invokevirtual 645	java/lang/Object:getClass	()Ljava/lang/Class;
    //   304: ldc_w 927
    //   307: invokevirtual 648	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   310: ifeq +19 -> 329
    //   313: aload_3
    //   314: ldc_w 942
    //   317: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   320: checkcast 927	java/lang/Boolean
    //   323: invokevirtual 930	java/lang/Boolean:booleanValue	()Z
    //   326: putstatic 143	com/kochava/android/tracker/c:Y	Z
    //   329: aload_0
    //   330: invokespecial 944	com/kochava/android/tracker/c:R	()V
    //   333: new 249	java/lang/StringBuilder
    //   336: dup
    //   337: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   340: ldc_w 946
    //   343: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   346: getstatic 320	com/kochava/android/tracker/c:aj	Landroid/content/SharedPreferences;
    //   349: ldc_w 948
    //   352: ldc 101
    //   354: invokeinterface 328 3 0
    //   359: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   362: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   365: invokestatic 285	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   368: getstatic 320	com/kochava/android/tracker/c:aj	Landroid/content/SharedPreferences;
    //   371: ldc_w 948
    //   374: ldc 101
    //   376: invokeinterface 328 3 0
    //   381: ldc 101
    //   383: invokevirtual 334	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   386: ifne +26 -> 412
    //   389: new 188	org/json/JSONArray
    //   392: dup
    //   393: getstatic 320	com/kochava/android/tracker/c:aj	Landroid/content/SharedPreferences;
    //   396: ldc_w 948
    //   399: ldc 101
    //   401: invokeinterface 328 3 0
    //   406: invokespecial 949	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   409: putstatic 191	com/kochava/android/tracker/c:aq	Lorg/json/JSONArray;
    //   412: getstatic 274	android/os/Build$VERSION:SDK_INT	I
    //   415: bipush 14
    //   417: if_icmplt +1959 -> 2376
    //   420: getstatic 107	com/kochava/android/tracker/c:b	Z
    //   423: ifne +1953 -> 2376
    //   426: new 249	java/lang/StringBuilder
    //   429: dup
    //   430: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   433: ldc_w 951
    //   436: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   439: getstatic 107	com/kochava/android/tracker/c:b	Z
    //   442: invokevirtual 954	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   445: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   448: invokestatic 285	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   451: getstatic 311	com/kochava/android/tracker/c:c	Landroid/content/Context;
    //   454: checkcast 956	android/app/Application
    //   457: new 958	com/kochava/android/tracker/x
    //   460: dup
    //   461: aload_0
    //   462: invokespecial 959	com/kochava/android/tracker/x:<init>	(Lcom/kochava/android/tracker/c;)V
    //   465: invokevirtual 963	android/app/Application:registerActivityLifecycleCallbacks	(Landroid/app/Application$ActivityLifecycleCallbacks;)V
    //   468: getstatic 311	com/kochava/android/tracker/c:c	Landroid/content/Context;
    //   471: new 965	com/kochava/android/tracker/y
    //   474: dup
    //   475: aload_0
    //   476: invokespecial 966	com/kochava/android/tracker/y:<init>	(Lcom/kochava/android/tracker/c;)V
    //   479: invokevirtual 970	android/content/Context:registerComponentCallbacks	(Landroid/content/ComponentCallbacks;)V
    //   482: iconst_1
    //   483: putstatic 974	com/kochava/android/tracker/u:a	Z
    //   486: iconst_1
    //   487: putstatic 975	com/kochava/android/tracker/u:b	Z
    //   490: aload_0
    //   491: aload_0
    //   492: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   495: invokevirtual 979	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   498: invokestatic 981	com/kochava/android/tracker/c:a	(Landroid/content/ContentResolver;)Ljava/lang/String;
    //   501: putfield 983	com/kochava/android/tracker/c:A	Ljava/lang/String;
    //   504: getstatic 124	com/kochava/android/tracker/c:L	Z
    //   507: ifne +19 -> 526
    //   510: new 985	com/kochava/android/tracker/n
    //   513: dup
    //   514: aload_0
    //   515: invokespecial 986	com/kochava/android/tracker/n:<init>	(Lcom/kochava/android/tracker/c;)V
    //   518: iconst_0
    //   519: anewarray 988	java/lang/Void
    //   522: invokevirtual 994	android/os/AsyncTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   525: pop
    //   526: aload_0
    //   527: aload_0
    //   528: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   531: invokevirtual 998	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   534: aload_0
    //   535: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   538: invokevirtual 1001	android/content/Context:getPackageName	()Ljava/lang/String;
    //   541: iconst_0
    //   542: invokevirtual 1007	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   545: getfield 1012	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   548: putfield 412	com/kochava/android/tracker/c:t	Ljava/lang/String;
    //   551: aload_0
    //   552: new 336	org/json/JSONObject
    //   555: dup
    //   556: invokespecial 1013	org/json/JSONObject:<init>	()V
    //   559: putfield 1015	com/kochava/android/tracker/c:d	Lorg/json/JSONObject;
    //   562: aload_0
    //   563: new 336	org/json/JSONObject
    //   566: dup
    //   567: invokespecial 1013	org/json/JSONObject:<init>	()V
    //   570: putfield 1017	com/kochava/android/tracker/c:e	Lorg/json/JSONObject;
    //   573: aload_0
    //   574: new 336	org/json/JSONObject
    //   577: dup
    //   578: invokespecial 1013	org/json/JSONObject:<init>	()V
    //   581: putfield 1019	com/kochava/android/tracker/c:f	Lorg/json/JSONObject;
    //   584: aload_3
    //   585: ifnull +2495 -> 3080
    //   588: aload_3
    //   589: ldc_w 1021
    //   592: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   595: ifnull +2479 -> 3074
    //   598: aload_3
    //   599: ldc_w 1021
    //   602: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   605: invokevirtual 645	java/lang/Object:getClass	()Ljava/lang/Class;
    //   608: ldc_w 330
    //   611: invokevirtual 648	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   614: ifeq +2460 -> 3074
    //   617: aload_3
    //   618: ldc_w 1021
    //   621: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   624: checkcast 330	java/lang/String
    //   627: astore 6
    //   629: aload_3
    //   630: ldc_w 1023
    //   633: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   636: ifnull +2432 -> 3068
    //   639: aload_3
    //   640: ldc_w 1023
    //   643: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   646: invokevirtual 645	java/lang/Object:getClass	()Ljava/lang/Class;
    //   649: ldc_w 330
    //   652: invokevirtual 648	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   655: ifeq +2413 -> 3068
    //   658: aload_3
    //   659: ldc_w 1023
    //   662: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   665: checkcast 330	java/lang/String
    //   668: astore 7
    //   670: aload_3
    //   671: ldc_w 1025
    //   674: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   677: ifnull +2383 -> 3060
    //   680: aload_3
    //   681: ldc_w 1025
    //   684: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   687: invokevirtual 645	java/lang/Object:getClass	()Ljava/lang/Class;
    //   690: ldc_w 330
    //   693: invokevirtual 648	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   696: ifeq +2364 -> 3060
    //   699: aload_3
    //   700: ldc_w 1025
    //   703: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   706: checkcast 330	java/lang/String
    //   709: astore 8
    //   711: aload_3
    //   712: ldc_w 1027
    //   715: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   718: ifnull +2337 -> 3055
    //   721: aload_3
    //   722: ldc_w 1027
    //   725: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   728: invokevirtual 645	java/lang/Object:getClass	()Ljava/lang/Class;
    //   731: ldc_w 330
    //   734: invokevirtual 648	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   737: ifeq +1667 -> 2404
    //   740: aload_3
    //   741: ldc_w 1027
    //   744: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   747: checkcast 330	java/lang/String
    //   750: astore_1
    //   751: aload_3
    //   752: ldc_w 1029
    //   755: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   758: ifnull +34 -> 792
    //   761: aload_3
    //   762: ldc_w 1029
    //   765: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   768: invokevirtual 645	java/lang/Object:getClass	()Ljava/lang/Class;
    //   771: ldc_w 330
    //   774: invokevirtual 648	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   777: ifeq +15 -> 792
    //   780: aload_3
    //   781: ldc_w 1029
    //   784: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   787: checkcast 330	java/lang/String
    //   790: astore 9
    //   792: aload_3
    //   793: ldc_w 1031
    //   796: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   799: ifnull +2250 -> 3049
    //   802: aload_3
    //   803: ldc_w 1031
    //   806: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   809: invokevirtual 645	java/lang/Object:getClass	()Ljava/lang/Class;
    //   812: ldc_w 330
    //   815: invokevirtual 648	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   818: ifeq +2231 -> 3049
    //   821: aload_3
    //   822: ldc_w 1031
    //   825: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   828: checkcast 330	java/lang/String
    //   831: astore 9
    //   833: aload_3
    //   834: ldc_w 1033
    //   837: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   840: ifnull +39 -> 879
    //   843: aload_3
    //   844: ldc_w 1033
    //   847: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   850: invokevirtual 645	java/lang/Object:getClass	()Ljava/lang/Class;
    //   853: ldc_w 927
    //   856: invokevirtual 648	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   859: ifeq +20 -> 879
    //   862: aload_0
    //   863: aload_3
    //   864: ldc_w 1033
    //   867: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   870: checkcast 927	java/lang/Boolean
    //   873: invokevirtual 930	java/lang/Boolean:booleanValue	()Z
    //   876: putfield 206	com/kochava/android/tracker/c:I	Z
    //   879: aload_3
    //   880: ldc_w 1035
    //   883: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   886: ifnull +115 -> 1001
    //   889: aload_3
    //   890: ldc_w 1035
    //   893: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   896: instanceof 922
    //   899: ifeq +102 -> 1001
    //   902: aload_3
    //   903: ldc_w 1035
    //   906: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   909: checkcast 922	java/util/HashMap
    //   912: putstatic 1037	com/kochava/android/tracker/c:h	Ljava/util/Map;
    //   915: new 336	org/json/JSONObject
    //   918: dup
    //   919: invokespecial 1013	org/json/JSONObject:<init>	()V
    //   922: putstatic 1039	com/kochava/android/tracker/c:i	Lorg/json/JSONObject;
    //   925: getstatic 1037	com/kochava/android/tracker/c:h	Ljava/util/Map;
    //   928: invokeinterface 1043 1 0
    //   933: invokeinterface 1049 1 0
    //   938: astore 10
    //   940: aload 10
    //   942: invokeinterface 1054 1 0
    //   947: ifeq +54 -> 1001
    //   950: aload 10
    //   952: invokeinterface 1058 1 0
    //   957: checkcast 1060	java/util/Map$Entry
    //   960: astore 11
    //   962: getstatic 1039	com/kochava/android/tracker/c:i	Lorg/json/JSONObject;
    //   965: aload 11
    //   967: invokeinterface 1063 1 0
    //   972: checkcast 330	java/lang/String
    //   975: aload 11
    //   977: invokeinterface 1066 1 0
    //   982: checkcast 330	java/lang/String
    //   985: invokevirtual 1070	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   988: pop
    //   989: aload 10
    //   991: invokeinterface 1073 1 0
    //   996: goto -56 -> 940
    //   999: astore 10
    //   1001: aload_3
    //   1002: ldc_w 1075
    //   1005: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1008: ifnull +36 -> 1044
    //   1011: aload_3
    //   1012: ldc_w 1075
    //   1015: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1018: invokevirtual 645	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1021: ldc_w 330
    //   1024: invokevirtual 648	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1027: ifeq +17 -> 1044
    //   1030: aload_0
    //   1031: aload_3
    //   1032: ldc_w 1075
    //   1035: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1038: checkcast 330	java/lang/String
    //   1041: putfield 1077	com/kochava/android/tracker/c:v	Ljava/lang/String;
    //   1044: aload 6
    //   1046: astore 10
    //   1048: aload 7
    //   1050: astore 11
    //   1052: aload 8
    //   1054: astore 12
    //   1056: aload_1
    //   1057: astore 13
    //   1059: aload 9
    //   1061: astore 14
    //   1063: aload_3
    //   1064: ldc_w 1079
    //   1067: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1070: ifnull +115 -> 1185
    //   1073: aload 6
    //   1075: astore 10
    //   1077: aload 7
    //   1079: astore 11
    //   1081: aload 8
    //   1083: astore 12
    //   1085: aload_1
    //   1086: astore 13
    //   1088: aload 9
    //   1090: astore 14
    //   1092: aload_3
    //   1093: ldc_w 1079
    //   1096: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1099: invokevirtual 645	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1102: ldc_w 1081
    //   1105: invokevirtual 648	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   1108: ifeq +77 -> 1185
    //   1111: aload_3
    //   1112: ldc_w 1079
    //   1115: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1118: checkcast 1081	java/lang/Integer
    //   1121: invokevirtual 1084	java/lang/Integer:intValue	()I
    //   1124: istore 4
    //   1126: iload 4
    //   1128: iconst_1
    //   1129: if_icmpge +1317 -> 2446
    //   1132: new 249	java/lang/StringBuilder
    //   1135: dup
    //   1136: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   1139: ldc_w 1086
    //   1142: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1145: iload 4
    //   1147: invokevirtual 670	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1150: ldc_w 1088
    //   1153: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1156: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1159: invokestatic 285	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1162: iconst_1
    //   1163: putstatic 112	com/kochava/android/tracker/c:l	Z
    //   1166: aload 9
    //   1168: astore 14
    //   1170: aload_1
    //   1171: astore 13
    //   1173: aload 8
    //   1175: astore 12
    //   1177: aload 7
    //   1179: astore 11
    //   1181: aload 6
    //   1183: astore 10
    //   1185: aload 14
    //   1187: ifnull +19 -> 1206
    //   1190: aload 14
    //   1192: invokevirtual 548	java/lang/String:trim	()Ljava/lang/String;
    //   1195: invokevirtual 533	java/lang/String:length	()I
    //   1198: ifeq +8 -> 1206
    //   1201: aload 14
    //   1203: putstatic 103	com/kochava/android/tracker/c:j	Ljava/lang/String;
    //   1206: aload 13
    //   1208: ifnull +18 -> 1226
    //   1211: aload 13
    //   1213: ldc_w 345
    //   1216: invokevirtual 1091	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   1219: ifeq +7 -> 1226
    //   1222: iconst_1
    //   1223: putstatic 157	com/kochava/android/tracker/c:ag	Z
    //   1226: aload_0
    //   1227: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   1230: ldc_w 1093
    //   1233: iconst_0
    //   1234: invokevirtual 460	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   1237: putstatic 395	com/kochava/android/tracker/c:ak	Landroid/content/SharedPreferences;
    //   1240: aload 11
    //   1242: ifnull +1296 -> 2538
    //   1245: aload 11
    //   1247: invokevirtual 548	java/lang/String:trim	()Ljava/lang/String;
    //   1250: invokevirtual 533	java/lang/String:length	()I
    //   1253: ifeq +1285 -> 2538
    //   1256: aload 11
    //   1258: putstatic 1095	com/kochava/android/tracker/c:w	Ljava/lang/String;
    //   1261: aload_0
    //   1262: getfield 1017	com/kochava/android/tracker/c:e	Lorg/json/JSONObject;
    //   1265: ldc_w 1023
    //   1268: aload 11
    //   1270: invokevirtual 1070	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1273: pop
    //   1274: aload_0
    //   1275: getfield 1019	com/kochava/android/tracker/c:f	Lorg/json/JSONObject;
    //   1278: ldc_w 1023
    //   1281: aload 11
    //   1283: invokevirtual 1070	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1286: pop
    //   1287: getstatic 320	com/kochava/android/tracker/c:aj	Landroid/content/SharedPreferences;
    //   1290: ldc_w 464
    //   1293: ldc 101
    //   1295: invokeinterface 328 3 0
    //   1300: ldc 101
    //   1302: invokevirtual 334	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1305: ifeq +27 -> 1332
    //   1308: getstatic 320	com/kochava/android/tracker/c:aj	Landroid/content/SharedPreferences;
    //   1311: invokeinterface 438 1 0
    //   1316: ldc_w 464
    //   1319: aload 11
    //   1321: invokeinterface 444 3 0
    //   1326: invokeinterface 447 1 0
    //   1331: pop
    //   1332: aload_0
    //   1333: aload 12
    //   1335: invokespecial 1097	com/kochava/android/tracker/c:e	(Ljava/lang/String;)V
    //   1338: aload_0
    //   1339: getfield 1015	com/kochava/android/tracker/c:d	Lorg/json/JSONObject;
    //   1342: ldc_w 1099
    //   1345: aload_0
    //   1346: invokespecial 1101	com/kochava/android/tracker/c:P	()Ljava/lang/String;
    //   1349: invokevirtual 1070	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1352: pop
    //   1353: aload_0
    //   1354: getfield 1015	com/kochava/android/tracker/c:d	Lorg/json/JSONObject;
    //   1357: ldc_w 1103
    //   1360: ldc_w 1105
    //   1363: invokevirtual 1070	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1366: pop
    //   1367: aload_0
    //   1368: getfield 1015	com/kochava/android/tracker/c:d	Lorg/json/JSONObject;
    //   1371: ldc_w 1107
    //   1374: ldc_w 1109
    //   1377: invokevirtual 1070	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1380: pop
    //   1381: aload_0
    //   1382: getfield 1015	com/kochava/android/tracker/c:d	Lorg/json/JSONObject;
    //   1385: ldc_w 1025
    //   1388: getstatic 320	com/kochava/android/tracker/c:aj	Landroid/content/SharedPreferences;
    //   1391: ldc_w 1025
    //   1394: ldc_w 1111
    //   1397: invokeinterface 328 3 0
    //   1402: invokevirtual 1070	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1405: pop
    //   1406: aload_0
    //   1407: getfield 1019	com/kochava/android/tracker/c:f	Lorg/json/JSONObject;
    //   1410: ldc_w 1025
    //   1413: getstatic 320	com/kochava/android/tracker/c:aj	Landroid/content/SharedPreferences;
    //   1416: ldc_w 1025
    //   1419: ldc_w 1111
    //   1422: invokeinterface 328 3 0
    //   1427: invokevirtual 1070	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1430: pop
    //   1431: aload_0
    //   1432: getfield 1019	com/kochava/android/tracker/c:f	Lorg/json/JSONObject;
    //   1435: ldc_w 1107
    //   1438: ldc_w 1109
    //   1441: invokevirtual 1070	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1444: pop
    //   1445: aload_0
    //   1446: getfield 1019	com/kochava/android/tracker/c:f	Lorg/json/JSONObject;
    //   1449: ldc_w 1025
    //   1452: getstatic 320	com/kochava/android/tracker/c:aj	Landroid/content/SharedPreferences;
    //   1455: ldc_w 1025
    //   1458: ldc_w 1111
    //   1461: invokeinterface 328 3 0
    //   1466: invokevirtual 1070	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1469: pop
    //   1470: aload_0
    //   1471: getfield 1017	com/kochava/android/tracker/c:e	Lorg/json/JSONObject;
    //   1474: ldc_w 1113
    //   1477: new 249	java/lang/StringBuilder
    //   1480: dup
    //   1481: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   1484: ldc_w 1115
    //   1487: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1490: getstatic 105	com/kochava/android/tracker/c:a	Ljava/lang/String;
    //   1493: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1496: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1499: invokevirtual 1070	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1502: pop
    //   1503: aload_0
    //   1504: getfield 1017	com/kochava/android/tracker/c:e	Lorg/json/JSONObject;
    //   1507: ldc_w 1117
    //   1510: ldc_w 1119
    //   1513: invokevirtual 1070	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1516: pop
    //   1517: aload_0
    //   1518: getfield 1017	com/kochava/android/tracker/c:e	Lorg/json/JSONObject;
    //   1521: ldc_w 1121
    //   1524: aload_0
    //   1525: getfield 1015	com/kochava/android/tracker/c:d	Lorg/json/JSONObject;
    //   1528: invokevirtual 1070	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1531: pop
    //   1532: aload_0
    //   1533: getfield 1017	com/kochava/android/tracker/c:e	Lorg/json/JSONObject;
    //   1536: ldc_w 1123
    //   1539: aload_0
    //   1540: getfield 1019	com/kochava/android/tracker/c:f	Lorg/json/JSONObject;
    //   1543: invokevirtual 1070	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   1546: pop
    //   1547: invokestatic 305	java/lang/System:currentTimeMillis	()J
    //   1550: ldc2_w 306
    //   1553: ldiv
    //   1554: putstatic 132	com/kochava/android/tracker/c:P	J
    //   1557: ldc 101
    //   1559: astore_3
    //   1560: new 1125	android/content/ComponentName
    //   1563: dup
    //   1564: aload_0
    //   1565: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   1568: ldc_w 1127
    //   1571: invokespecial 1130	android/content/ComponentName:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   1574: astore_1
    //   1575: aload_0
    //   1576: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   1579: invokevirtual 998	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1582: aload_1
    //   1583: iconst_0
    //   1584: invokevirtual 1134	android/content/pm/PackageManager:getReceiverInfo	(Landroid/content/ComponentName;I)Landroid/content/pm/ActivityInfo;
    //   1587: pop
    //   1588: ldc_w 1136
    //   1591: invokestatic 285	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1594: iconst_0
    //   1595: istore 4
    //   1597: aload_3
    //   1598: astore_1
    //   1599: aload_0
    //   1600: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   1603: invokevirtual 998	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1606: ldc_w 1138
    //   1609: aload_0
    //   1610: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   1613: invokevirtual 1001	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1616: invokevirtual 1141	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1619: ifge +27 -> 1646
    //   1622: new 249	java/lang/StringBuilder
    //   1625: dup
    //   1626: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   1629: aload_3
    //   1630: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1633: ldc_w 1143
    //   1636: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1639: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1642: astore_1
    //   1643: iconst_1
    //   1644: istore 4
    //   1646: aload_1
    //   1647: astore_3
    //   1648: aload_0
    //   1649: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   1652: invokevirtual 998	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1655: ldc_w 1145
    //   1658: aload_0
    //   1659: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   1662: invokevirtual 1001	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1665: invokevirtual 1141	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1668: ifge +27 -> 1695
    //   1671: new 249	java/lang/StringBuilder
    //   1674: dup
    //   1675: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   1678: aload_1
    //   1679: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1682: ldc_w 1147
    //   1685: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1688: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1691: astore_3
    //   1692: iconst_1
    //   1693: istore 4
    //   1695: aload_0
    //   1696: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   1699: invokevirtual 998	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1702: ldc_w 1149
    //   1705: aload_0
    //   1706: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   1709: invokevirtual 1001	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1712: invokevirtual 1141	android/content/pm/PackageManager:checkPermission	(Ljava/lang/String;Ljava/lang/String;)I
    //   1715: ifge +1331 -> 3046
    //   1718: new 249	java/lang/StringBuilder
    //   1721: dup
    //   1722: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   1725: aload_3
    //   1726: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1729: ldc_w 1151
    //   1732: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1735: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1738: astore_3
    //   1739: iload 5
    //   1741: istore 4
    //   1743: iload 4
    //   1745: ifeq +13 -> 1758
    //   1748: ldc_w 1153
    //   1751: invokestatic 1155	com/kochava/android/a/b:c	(Ljava/lang/String;)V
    //   1754: aload_3
    //   1755: invokestatic 1155	com/kochava/android/a/b:c	(Ljava/lang/String;)V
    //   1758: aload_0
    //   1759: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   1762: ldc_w 1157
    //   1765: invokevirtual 751	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   1768: checkcast 1159	android/telephony/TelephonyManager
    //   1771: invokevirtual 1162	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   1774: putstatic 1164	com/kochava/android/tracker/c:F	Ljava/lang/String;
    //   1777: aload_0
    //   1778: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   1781: ldc_w 1166
    //   1784: invokevirtual 751	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   1787: checkcast 1168	android/net/wifi/WifiManager
    //   1790: invokevirtual 1172	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   1793: invokevirtual 1177	android/net/wifi/WifiInfo:getBSSID	()Ljava/lang/String;
    //   1796: putstatic 1179	com/kochava/android/tracker/c:E	Ljava/lang/String;
    //   1799: aload_0
    //   1800: invokespecial 1181	com/kochava/android/tracker/c:U	()Ljava/lang/String;
    //   1803: putstatic 572	com/kochava/android/tracker/c:z	Ljava/lang/String;
    //   1806: aload_0
    //   1807: invokestatic 246	com/kochava/android/tracker/c:K	()Ljava/lang/String;
    //   1810: putfield 1183	com/kochava/android/tracker/c:o	Ljava/lang/String;
    //   1813: aload_0
    //   1814: invokestatic 244	com/kochava/android/tracker/c:L	()Ljava/lang/String;
    //   1817: putfield 1185	com/kochava/android/tracker/c:p	Ljava/lang/String;
    //   1820: aload_0
    //   1821: ldc_w 1187
    //   1824: putfield 406	com/kochava/android/tracker/c:q	Ljava/lang/String;
    //   1827: aload_0
    //   1828: ldc_w 1189
    //   1831: putfield 410	com/kochava/android/tracker/c:r	Ljava/lang/String;
    //   1834: aload_0
    //   1835: ldc 101
    //   1837: putfield 1191	com/kochava/android/tracker/c:s	Ljava/lang/String;
    //   1840: aload_0
    //   1841: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   1844: invokevirtual 915	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   1847: invokevirtual 998	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1850: astore_3
    //   1851: aload_3
    //   1852: aload_0
    //   1853: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   1856: invokevirtual 1001	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1859: iconst_0
    //   1860: invokevirtual 1195	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   1863: astore_1
    //   1864: aload_1
    //   1865: ifnull +986 -> 2851
    //   1868: aload_3
    //   1869: aload_1
    //   1870: invokevirtual 1199	android/content/pm/PackageManager:getApplicationLabel	(Landroid/content/pm/ApplicationInfo;)Ljava/lang/CharSequence;
    //   1873: astore_1
    //   1874: aload_0
    //   1875: aload_1
    //   1876: checkcast 330	java/lang/String
    //   1879: checkcast 330	java/lang/String
    //   1882: putfield 406	com/kochava/android/tracker/c:q	Ljava/lang/String;
    //   1885: new 249	java/lang/StringBuilder
    //   1888: dup
    //   1889: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   1892: ldc_w 1201
    //   1895: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1898: aload_0
    //   1899: getfield 406	com/kochava/android/tracker/c:q	Ljava/lang/String;
    //   1902: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1905: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1908: invokestatic 285	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1911: aload_0
    //   1912: new 249	java/lang/StringBuilder
    //   1915: dup
    //   1916: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   1919: aload_0
    //   1920: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   1923: invokevirtual 998	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1926: aload_0
    //   1927: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   1930: invokevirtual 1001	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1933: iconst_0
    //   1934: invokevirtual 1007	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   1937: getfield 1204	android/content/pm/PackageInfo:versionCode	I
    //   1940: invokevirtual 670	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1943: ldc 101
    //   1945: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1948: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1951: putfield 410	com/kochava/android/tracker/c:r	Ljava/lang/String;
    //   1954: new 249	java/lang/StringBuilder
    //   1957: dup
    //   1958: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   1961: ldc_w 1206
    //   1964: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1967: aload_0
    //   1968: getfield 410	com/kochava/android/tracker/c:r	Ljava/lang/String;
    //   1971: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1974: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1977: invokestatic 285	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   1980: aload_0
    //   1981: aload_0
    //   1982: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   1985: invokevirtual 998	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1988: aload_0
    //   1989: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   1992: invokevirtual 1001	android/content/Context:getPackageName	()Ljava/lang/String;
    //   1995: iconst_0
    //   1996: invokevirtual 1007	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   1999: getfield 1209	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   2002: putfield 1191	com/kochava/android/tracker/c:s	Ljava/lang/String;
    //   2005: new 249	java/lang/StringBuilder
    //   2008: dup
    //   2009: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   2012: ldc_w 1211
    //   2015: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2018: aload_0
    //   2019: getfield 1191	com/kochava/android/tracker/c:s	Ljava/lang/String;
    //   2022: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2025: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2028: invokestatic 285	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2031: aload_0
    //   2032: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   2035: ldc_w 1213
    //   2038: invokevirtual 751	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   2041: checkcast 1215	android/view/WindowManager
    //   2044: astore_1
    //   2045: aload_0
    //   2046: aload_1
    //   2047: invokeinterface 1219 1 0
    //   2052: invokevirtual 1224	android/view/Display:getHeight	()I
    //   2055: putfield 1226	com/kochava/android/tracker/c:B	I
    //   2058: aload_0
    //   2059: aload_1
    //   2060: invokeinterface 1219 1 0
    //   2065: invokevirtual 1229	android/view/Display:getWidth	()I
    //   2068: putfield 1231	com/kochava/android/tracker/c:C	I
    //   2071: new 249	java/lang/StringBuilder
    //   2074: dup
    //   2075: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   2078: ldc_w 1233
    //   2081: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2084: aload_0
    //   2085: getfield 1226	com/kochava/android/tracker/c:B	I
    //   2088: invokevirtual 670	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2091: ldc_w 1235
    //   2094: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2097: aload_0
    //   2098: getfield 1231	com/kochava/android/tracker/c:C	I
    //   2101: invokevirtual 670	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2104: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2107: invokestatic 285	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2110: aload_0
    //   2111: aload_0
    //   2112: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   2115: invokevirtual 979	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   2118: ldc_w 1237
    //   2121: invokestatic 1242	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   2124: putfield 1244	com/kochava/android/tracker/c:x	Ljava/lang/String;
    //   2127: aload_0
    //   2128: invokestatic 256	com/kochava/android/tracker/c:Q	()Ljava/lang/String;
    //   2131: putfield 1246	com/kochava/android/tracker/c:u	Ljava/lang/String;
    //   2134: aload_0
    //   2135: getfield 1017	com/kochava/android/tracker/c:e	Lorg/json/JSONObject;
    //   2138: ldc_w 1248
    //   2141: invokestatic 256	com/kochava/android/tracker/c:Q	()Ljava/lang/String;
    //   2144: invokevirtual 1070	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2147: pop
    //   2148: getstatic 320	com/kochava/android/tracker/c:aj	Landroid/content/SharedPreferences;
    //   2151: ldc_w 343
    //   2154: ldc 101
    //   2156: invokeinterface 328 3 0
    //   2161: ldc_w 345
    //   2164: invokevirtual 334	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2167: ifne +141 -> 2308
    //   2170: aload_0
    //   2171: getfield 452	com/kochava/android/tracker/c:n	Landroid/content/Context;
    //   2174: invokevirtual 998	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   2177: astore_3
    //   2178: aload_3
    //   2179: sipush 128
    //   2182: invokevirtual 1252	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   2185: invokeinterface 1255 1 0
    //   2190: astore 6
    //   2192: aload 6
    //   2194: invokeinterface 1054 1 0
    //   2199: ifeq +109 -> 2308
    //   2202: aload 6
    //   2204: invokeinterface 1058 1 0
    //   2209: checkcast 1257	android/content/pm/ApplicationInfo
    //   2212: astore 7
    //   2214: aload 7
    //   2216: invokestatic 1260	com/kochava/android/tracker/c:a	(Landroid/content/pm/ApplicationInfo;)Z
    //   2219: istore_2
    //   2220: iload_2
    //   2221: ifne -29 -> 2192
    //   2224: aload_3
    //   2225: aload 7
    //   2227: getfield 1261	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   2230: iconst_0
    //   2231: invokevirtual 1007	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   2234: astore_1
    //   2235: aload_1
    //   2236: ifnull -44 -> 2192
    //   2239: getstatic 141	com/kochava/android/tracker/c:S	Ljava/util/List;
    //   2242: new 1263	com/kochava/android/tracker/w
    //   2245: dup
    //   2246: aload_0
    //   2247: aload_1
    //   2248: getfield 1012	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   2251: new 249	java/lang/StringBuilder
    //   2254: dup
    //   2255: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   2258: aload_1
    //   2259: getfield 1266	android/content/pm/PackageInfo:firstInstallTime	J
    //   2262: invokevirtual 1269	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2265: ldc 101
    //   2267: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2270: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2273: new 249	java/lang/StringBuilder
    //   2276: dup
    //   2277: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   2280: aload_1
    //   2281: getfield 1272	android/content/pm/PackageInfo:lastUpdateTime	J
    //   2284: invokevirtual 1269	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2287: ldc 101
    //   2289: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2292: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2295: invokespecial 1275	com/kochava/android/tracker/w:<init>	(Lcom/kochava/android/tracker/c;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   2298: invokeinterface 1278 2 0
    //   2303: pop
    //   2304: goto -112 -> 2192
    //   2307: astore_1
    //   2308: getstatic 171	com/kochava/android/tracker/c:an	Ljava/util/concurrent/ScheduledExecutorService;
    //   2311: aload_0
    //   2312: getfield 215	com/kochava/android/tracker/c:ar	Ljava/lang/Runnable;
    //   2315: ldc2_w 1279
    //   2318: getstatic 892	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   2321: invokeinterface 897 5 0
    //   2326: pop
    //   2327: aload_0
    //   2328: ldc_w 1282
    //   2331: invokevirtual 1283	com/kochava/android/tracker/c:a	(Ljava/lang/String;)V
    //   2334: return
    //   2335: ldc_w 1285
    //   2338: invokestatic 281	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   2341: iconst_1
    //   2342: putstatic 151	com/kochava/android/tracker/c:ad	Z
    //   2345: return
    //   2346: astore_1
    //   2347: new 249	java/lang/StringBuilder
    //   2350: dup
    //   2351: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   2354: ldc_w 1287
    //   2357: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2360: aload_1
    //   2361: invokevirtual 737	java/lang/Exception:toString	()Ljava/lang/String;
    //   2364: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2367: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2370: invokestatic 285	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2373: goto -1961 -> 412
    //   2376: new 249	java/lang/StringBuilder
    //   2379: dup
    //   2380: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   2383: ldc_w 1289
    //   2386: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2389: getstatic 107	com/kochava/android/tracker/c:b	Z
    //   2392: invokevirtual 954	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   2395: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2398: invokestatic 285	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2401: goto -1911 -> 490
    //   2404: aload_3
    //   2405: ldc_w 1027
    //   2408: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2411: invokevirtual 645	java/lang/Object:getClass	()Ljava/lang/Class;
    //   2414: ldc_w 927
    //   2417: invokevirtual 648	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   2420: ifeq +635 -> 3055
    //   2423: aload_3
    //   2424: ldc_w 1027
    //   2427: invokevirtual 925	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   2430: checkcast 927	java/lang/Boolean
    //   2433: invokevirtual 930	java/lang/Boolean:booleanValue	()Z
    //   2436: ifeq +619 -> 3055
    //   2439: ldc_w 345
    //   2442: astore_1
    //   2443: goto -1692 -> 751
    //   2446: iload 4
    //   2448: sipush 360
    //   2451: if_icmple +42 -> 2493
    //   2454: new 249	java/lang/StringBuilder
    //   2457: dup
    //   2458: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   2461: ldc_w 1086
    //   2464: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2467: iload 4
    //   2469: invokevirtual 670	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2472: ldc_w 1291
    //   2475: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2478: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2481: invokestatic 285	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2484: ldc_w 1292
    //   2487: putstatic 110	com/kochava/android/tracker/c:k	I
    //   2490: goto -1328 -> 1162
    //   2493: iload 4
    //   2495: bipush 60
    //   2497: imul
    //   2498: sipush 1000
    //   2501: imul
    //   2502: putstatic 110	com/kochava/android/tracker/c:k	I
    //   2505: new 249	java/lang/StringBuilder
    //   2508: dup
    //   2509: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   2512: ldc_w 1294
    //   2515: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2518: iload 4
    //   2520: invokevirtual 670	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2523: ldc_w 1296
    //   2526: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2529: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2532: invokestatic 285	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2535: goto -1373 -> 1162
    //   2538: aload 10
    //   2540: ifnull +179 -> 2719
    //   2543: aload 10
    //   2545: invokevirtual 548	java/lang/String:trim	()Ljava/lang/String;
    //   2548: invokevirtual 533	java/lang/String:length	()I
    //   2551: ifeq +168 -> 2719
    //   2554: aload_0
    //   2555: getfield 1015	com/kochava/android/tracker/c:d	Lorg/json/JSONObject;
    //   2558: ldc_w 1021
    //   2561: aload 10
    //   2563: invokevirtual 1070	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2566: pop
    //   2567: getstatic 320	com/kochava/android/tracker/c:aj	Landroid/content/SharedPreferences;
    //   2570: ldc_w 464
    //   2573: ldc 101
    //   2575: invokeinterface 328 3 0
    //   2580: ldc 101
    //   2582: invokevirtual 334	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2585: ifeq +27 -> 2612
    //   2588: getstatic 320	com/kochava/android/tracker/c:aj	Landroid/content/SharedPreferences;
    //   2591: invokeinterface 438 1 0
    //   2596: ldc_w 464
    //   2599: aload 10
    //   2601: invokeinterface 444 3 0
    //   2606: invokeinterface 447 1 0
    //   2611: pop
    //   2612: aload 11
    //   2614: ifnull +78 -> 2692
    //   2617: aload 11
    //   2619: invokevirtual 548	java/lang/String:trim	()Ljava/lang/String;
    //   2622: invokevirtual 533	java/lang/String:length	()I
    //   2625: ifeq +67 -> 2692
    //   2628: aload 11
    //   2630: putstatic 1095	com/kochava/android/tracker/c:w	Ljava/lang/String;
    //   2633: aload_0
    //   2634: getfield 1017	com/kochava/android/tracker/c:e	Lorg/json/JSONObject;
    //   2637: ldc_w 1023
    //   2640: aload 11
    //   2642: invokevirtual 1070	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2645: pop
    //   2646: aload_0
    //   2647: getfield 1019	com/kochava/android/tracker/c:f	Lorg/json/JSONObject;
    //   2650: ldc_w 1023
    //   2653: aload 11
    //   2655: invokevirtual 1070	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   2658: pop
    //   2659: goto -1327 -> 1332
    //   2662: astore_1
    //   2663: new 249	java/lang/StringBuilder
    //   2666: dup
    //   2667: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   2670: ldc_w 1298
    //   2673: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2676: aload_1
    //   2677: invokevirtual 1299	org/json/JSONException:toString	()Ljava/lang/String;
    //   2680: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2683: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2686: invokestatic 281	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   2689: goto -1142 -> 1547
    //   2692: new 249	java/lang/StringBuilder
    //   2695: dup
    //   2696: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   2699: ldc_w 1301
    //   2702: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2705: aload 10
    //   2707: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2710: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2713: putstatic 1095	com/kochava/android/tracker/c:w	Ljava/lang/String;
    //   2716: goto -1384 -> 1332
    //   2719: ldc_w 1303
    //   2722: invokestatic 281	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   2725: iconst_1
    //   2726: putstatic 151	com/kochava/android/tracker/c:ad	Z
    //   2729: return
    //   2730: astore_1
    //   2731: new 249	java/lang/StringBuilder
    //   2734: dup
    //   2735: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   2738: ldc 101
    //   2740: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2743: ldc_w 1305
    //   2746: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2749: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2752: astore_3
    //   2753: iconst_1
    //   2754: istore 4
    //   2756: goto -1159 -> 1597
    //   2759: astore_1
    //   2760: new 249	java/lang/StringBuilder
    //   2763: dup
    //   2764: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   2767: ldc_w 1307
    //   2770: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2773: aload_1
    //   2774: invokevirtual 737	java/lang/Exception:toString	()Ljava/lang/String;
    //   2777: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2780: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2783: invokestatic 281	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   2786: goto -1009 -> 1777
    //   2789: astore_1
    //   2790: new 249	java/lang/StringBuilder
    //   2793: dup
    //   2794: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   2797: ldc_w 1309
    //   2800: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2803: aload_1
    //   2804: invokevirtual 737	java/lang/Exception:toString	()Ljava/lang/String;
    //   2807: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2810: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2813: invokestatic 285	com/kochava/android/a/b:a	(Ljava/lang/String;)V
    //   2816: goto -1017 -> 1799
    //   2819: astore_1
    //   2820: new 249	java/lang/StringBuilder
    //   2823: dup
    //   2824: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   2827: ldc_w 1311
    //   2830: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2833: aload_1
    //   2834: invokevirtual 1312	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   2837: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2840: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2843: invokestatic 281	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   2846: aconst_null
    //   2847: astore_1
    //   2848: goto -984 -> 1864
    //   2851: ldc_w 1314
    //   2854: astore_1
    //   2855: goto -981 -> 1874
    //   2858: astore_1
    //   2859: new 249	java/lang/StringBuilder
    //   2862: dup
    //   2863: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   2866: ldc_w 1311
    //   2869: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2872: aload_1
    //   2873: invokevirtual 737	java/lang/Exception:toString	()Ljava/lang/String;
    //   2876: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2879: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2882: invokestatic 281	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   2885: goto -974 -> 1911
    //   2888: astore_1
    //   2889: new 249	java/lang/StringBuilder
    //   2892: dup
    //   2893: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   2896: ldc_w 1316
    //   2899: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2902: aload_1
    //   2903: invokevirtual 737	java/lang/Exception:toString	()Ljava/lang/String;
    //   2906: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2909: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2912: invokestatic 281	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   2915: goto -935 -> 1980
    //   2918: astore_1
    //   2919: new 249	java/lang/StringBuilder
    //   2922: dup
    //   2923: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   2926: ldc_w 1318
    //   2929: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2932: aload_1
    //   2933: invokevirtual 737	java/lang/Exception:toString	()Ljava/lang/String;
    //   2936: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2939: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2942: invokestatic 281	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   2945: goto -914 -> 2031
    //   2948: astore_1
    //   2949: new 249	java/lang/StringBuilder
    //   2952: dup
    //   2953: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   2956: ldc_w 1320
    //   2959: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2962: aload_1
    //   2963: invokevirtual 737	java/lang/Exception:toString	()Ljava/lang/String;
    //   2966: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2969: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2972: invokestatic 281	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   2975: goto -865 -> 2110
    //   2978: astore_1
    //   2979: getstatic 361	com/kochava/android/tracker/aa:b	Z
    //   2982: ifeq -834 -> 2148
    //   2985: aload_1
    //   2986: invokevirtual 364	org/json/JSONException:printStackTrace	()V
    //   2989: goto -841 -> 2148
    //   2992: astore_1
    //   2993: new 249	java/lang/StringBuilder
    //   2996: dup
    //   2997: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   3000: ldc_w 1322
    //   3003: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3006: aload 7
    //   3008: getfield 1261	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   3011: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3014: ldc_w 1324
    //   3017: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3020: aload_1
    //   3021: invokevirtual 1312	android/content/pm/PackageManager$NameNotFoundException:toString	()Ljava/lang/String;
    //   3024: invokevirtual 254	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3027: invokevirtual 261	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   3030: invokestatic 281	com/kochava/android/a/b:b	(Ljava/lang/String;)V
    //   3033: aconst_null
    //   3034: astore_1
    //   3035: goto -800 -> 2235
    //   3038: astore_1
    //   3039: goto -2488 -> 551
    //   3042: astore_1
    //   3043: goto -2539 -> 504
    //   3046: goto -1303 -> 1743
    //   3049: aconst_null
    //   3050: astore 9
    //   3052: goto -2219 -> 833
    //   3055: aconst_null
    //   3056: astore_1
    //   3057: goto -2306 -> 751
    //   3060: ldc_w 1111
    //   3063: astore 8
    //   3065: goto -2354 -> 711
    //   3068: aconst_null
    //   3069: astore 7
    //   3071: goto -2401 -> 670
    //   3074: aconst_null
    //   3075: astore 6
    //   3077: goto -2448 -> 629
    //   3080: aconst_null
    //   3081: astore 14
    //   3083: aconst_null
    //   3084: astore 13
    //   3086: ldc_w 1111
    //   3089: astore 12
    //   3091: aconst_null
    //   3092: astore 11
    //   3094: aconst_null
    //   3095: astore 10
    //   3097: goto -1912 -> 1185
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	3100	0	this	c
    //   0	3100	1	paramContext	Context
    //   0	3100	2	paramBoolean	boolean
    //   0	3100	3	paramHashMap	HashMap<String, Object>
    //   1124	1631	4	i1	int
    //   1	1739	5	i2	int
    //   627	2449	6	localObject1	Object
    //   668	2402	7	localObject2	Object
    //   709	2355	8	str1	String
    //   790	2261	9	str2	String
    //   938	52	10	localIterator	Iterator
    //   999	1	10	localException	Exception
    //   1046	2050	10	localObject3	Object
    //   960	2133	11	localObject4	Object
    //   1054	2036	12	str3	String
    //   1057	2028	13	localContext	Context
    //   1061	2021	14	str4	String
    // Exception table:
    //   from	to	target	type
    //   902	940	999	java/lang/Exception
    //   940	996	999	java/lang/Exception
    //   2148	2192	2307	java/lang/Exception
    //   2192	2220	2307	java/lang/Exception
    //   2224	2235	2307	java/lang/Exception
    //   2239	2304	2307	java/lang/Exception
    //   2993	3033	2307	java/lang/Exception
    //   389	412	2346	java/lang/Exception
    //   1245	1332	2662	org/json/JSONException
    //   1332	1547	2662	org/json/JSONException
    //   2543	2612	2662	org/json/JSONException
    //   2617	2659	2662	org/json/JSONException
    //   2692	2716	2662	org/json/JSONException
    //   2719	2729	2662	org/json/JSONException
    //   1575	1594	2730	android/content/pm/PackageManager$NameNotFoundException
    //   1758	1777	2759	java/lang/Exception
    //   1777	1799	2789	java/lang/Exception
    //   1851	1864	2819	android/content/pm/PackageManager$NameNotFoundException
    //   1840	1851	2858	java/lang/Exception
    //   1851	1864	2858	java/lang/Exception
    //   1868	1874	2858	java/lang/Exception
    //   1874	1911	2858	java/lang/Exception
    //   2820	2846	2858	java/lang/Exception
    //   1911	1980	2888	java/lang/Exception
    //   1980	2031	2918	java/lang/Exception
    //   2031	2110	2948	java/lang/Exception
    //   2134	2148	2978	org/json/JSONException
    //   2224	2235	2992	android/content/pm/PackageManager$NameNotFoundException
    //   526	551	3038	java/lang/Exception
    //   490	504	3042	java/lang/Exception
  }
  
  private void a(String paramString, Map<String, String> paramMap)
  {
    int i3 = 0;
    String str = (String)paramMap.get("event_name");
    if (str != null)
    {
      int i1 = 0;
      for (;;)
      {
        int i2 = i3;
        if (i1 < aq.length()) {}
        try
        {
          boolean bool = aq.get(i1).equals(paramMap.get("event_name"));
          if (bool)
          {
            i2 = 1;
            if (i2 == 0) {
              break;
            }
            b.a("fireEvent - Events under the key \"" + str + "\" are blocked!");
            return;
          }
        }
        catch (JSONException localJSONException)
        {
          i1 += 1;
        }
      }
      b(paramString, paramMap);
      return;
    }
    b(paramString, paramMap);
  }
  
  public static void a(boolean paramBoolean)
  {
    aa.a = paramBoolean;
    b.a("enableDebug to " + paramBoolean);
  }
  
  private static boolean a(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo.flags & 0x1) != 0;
  }
  
  private static JSONObject b(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null) {
      try
      {
        paramJSONObject.put("usertime", System.currentTimeMillis() / 1000L + "");
        paramJSONObject.put("uptime", System.currentTimeMillis() / 1000L - P + "");
        if (O != 0L) {
          paramJSONObject.put("updelta", System.currentTimeMillis() / 1000L - O + "");
        }
        for (;;)
        {
          O = System.currentTimeMillis() / 1000L;
          aj = c.getSharedPreferences("initPrefs", 0);
          if (aj.getString("mylat", "").equals("")) {
            break;
          }
          paramJSONObject.put("geo_lat", aj.getString("mylat", ""));
          paramJSONObject.put("geo_lon", aj.getString("mylong", ""));
          return paramJSONObject;
          paramJSONObject.put("updelta", "0");
        }
        return paramJSONObject;
      }
      catch (Exception localException)
      {
        b.b("Error adding time properties to a JSON object " + localException);
      }
    }
  }
  
  private static void b(Exception paramException)
  {
    new k(paramException).start();
  }
  
  private void b(String paramString, Map<String, String> paramMap)
  {
    if ((!paramString.equals("initial")) && (!Y) && (aa) && (!Z))
    {
      Z = true;
      this.ab.schedule(new t(this), 60000L);
    }
    b.a("FIRE EVENT*** action:" + paramString);
    b.a("FIRE EVENT*** properties:" + paramMap);
    JSONObject localJSONObject1 = new JSONObject();
    label1086:
    int i1;
    do
    {
      JSONObject localJSONObject2;
      for (;;)
      {
        try
        {
          localJSONObject1.put("kochava_app_id", w);
          localJSONObject1.put("kochava_device_id", Q());
          localJSONObject1.put("action", paramString);
          localJSONObject1.put("dev_id_strategy", y);
          localJSONObject1.put("last_post_time", 0);
          aj = this.n.getSharedPreferences("initPrefs", 0);
          localJSONObject1.put("currency", aj.getString("currency", "USD"));
          localJSONObject2 = b(new JSONObject());
          if (paramString.equals("initial"))
          {
            b.a("Event is initial, or initial did not get que'd on first load");
            try
            {
              if (((Boolean)ap.get("affinity_group")).booleanValue())
              {
                paramString = new JSONArray();
                localObject = S.iterator();
                if (((Iterator)localObject).hasNext())
                {
                  w localW = (w)((Iterator)localObject).next();
                  JSONObject localJSONObject3 = new JSONObject();
                  localJSONObject3.put("app_name", localW.a);
                  localJSONObject3.put("mtime", localW.b);
                  localJSONObject3.put("utime", localW.c);
                  paramString.put(localJSONObject3);
                  continue;
                }
              }
              if (R == null) {
                break;
              }
            }
            catch (JSONException paramString)
            {
              if (aa.b) {
                paramString.printStackTrace();
              }
              b.b("event " + paramString);
              return;
              localJSONObject2.put("affinity_group", paramString);
              localJSONObject1.put("sdk_version", "Android20160105" + a);
              if (((Boolean)ap.get("volume")).booleanValue()) {
                localJSONObject1.put("volume", V());
              }
              if (((Boolean)ap.get("bssid")).booleanValue()) {
                localJSONObject2.put("bssid", E);
              }
              if (((Boolean)ap.get("carrier_name")).booleanValue()) {
                localJSONObject2.put("carrier_name", F);
              }
              if (((Boolean)ap.get("adid")).booleanValue()) {
                localJSONObject2.put("adid", D);
              }
              localJSONObject2.put("device", L() + "-" + K());
              if (((Boolean)ap.get("screen_size")).booleanValue()) {
                localJSONObject2.put("disp_h", this.B);
              }
              if (((Boolean)ap.get("screen_size")).booleanValue()) {
                localJSONObject2.put("disp_w", this.C);
              }
              localJSONObject2.put("package_name", P());
              localJSONObject2.put("app_version", O());
              if (!this.s.equals("")) {
                localJSONObject2.put("app_short_string", this.s);
              }
              if (((Boolean)ap.get("android_id")).booleanValue()) {
                localJSONObject2.put("android_id", this.x);
              }
              localJSONObject2.put("os_version", M());
              localJSONObject2.put("app_limit_tracking", this.I);
              localJSONObject2.put("device_limit_tracking", J);
              paramString = new JSONObject();
              if (af)
              {
                localObject = X();
                if (!((String)localObject).equals("[]")) {
                  paramString.put("email", localObject);
                }
              }
              if (paramString.length() > 0) {
                localJSONObject2.put("ids", paramString);
              }
              if (i != null) {
                localJSONObject2.put("identity_link", i);
              }
              if ((this.v != null) && (!this.v.isEmpty())) {
                localJSONObject2.put("clickData", this.v);
              }
              if (((Boolean)ap.get("fb_attribution_id")).booleanValue())
              {
                this.A = a(this.n.getContentResolver());
                if (this.A != null) {
                  break label1086;
                }
                localJSONObject2.put("fb_attribution_id", "");
              }
              paramString = (WindowManager)this.n.getSystemService("window");
              localObject = new DisplayMetrics();
              paramString.getDefaultDisplay().getMetrics((DisplayMetrics)localObject);
              this.ai = localJSONObject2;
              this.ah = localJSONObject1;
              b.a("Initial Event, saving until next event. ");
              return;
            }
            catch (Exception paramString)
            {
              b.b("Error during fireEvent - Please review stack trace");
              if (aa.b) {
                paramString.printStackTrace();
              }
            }
          }
          paramString = R.entrySet().iterator();
          if (!paramString.hasNext()) {
            break;
          }
          Object localObject = (Map.Entry)paramString.next();
          localJSONObject2.put((String)((Map.Entry)localObject).getKey(), ((Map.Entry)localObject).getValue());
          continue;
          localJSONObject2.put("fb_attribution_id", this.A);
        }
        catch (JSONException paramString)
        {
          if (aa.b) {
            paramString.printStackTrace();
          }
          b.b("event " + paramString);
          return;
        }
      }
      if (paramMap != null)
      {
        paramString = paramMap.entrySet().iterator();
        while (paramString.hasNext())
        {
          paramMap = (Map.Entry)paramString.next();
          localJSONObject2.put((String)paramMap.getKey(), paramMap.getValue());
        }
      }
      localJSONObject1.put("data", localJSONObject2);
      b.a("fireEvent with properties: " + localJSONObject1);
      k(true);
      i1 = T.a(localJSONObject1, false, false);
      ac = System.currentTimeMillis();
    } while (i1 < 50);
    a();
  }
  
  private static void b(JSONArray paramJSONArray)
  {
    new m(paramJSONArray).start();
  }
  
  public static void b(boolean paramBoolean)
  {
    b.a("setErrorDebug to " + paramBoolean);
    aa.b = paramBoolean;
  }
  
  private static void c(JSONObject paramJSONObject)
  {
    new j(paramJSONObject).start();
  }
  
  private void d(JSONObject paramJSONObject)
  {
    JSONArray localJSONArray1 = new JSONArray();
    try
    {
      Iterator localIterator = paramJSONObject.keys();
      if (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        JSONArray localJSONArray2 = paramJSONObject.getJSONArray(str);
        int i1 = 0;
        for (;;)
        {
          int i2 = localJSONArray2.length();
          if (i1 >= i2) {
            break;
          }
          try
          {
            new ComponentName(this.n, Class.forName(localJSONArray2.getString(i1)));
            localJSONArray1.put(str);
          }
          catch (Exception localException)
          {
            i1 += 1;
          }
        }
      }
      return;
    }
    catch (Exception paramJSONObject)
    {
      if (localJSONArray1.length() > 0) {
        b(localJSONArray1);
      }
    }
  }
  
  private void e(String paramString)
  {
    if ((paramString != null) && (paramString.length() != 0))
    {
      aj = this.n.getSharedPreferences("initPrefs", 0);
      aj.edit().putString("currency", paramString).commit();
    }
  }
  
  private static void f(String paramString)
  {
    new s(paramString).start();
  }
  
  private static boolean g(String paramString)
  {
    boolean bool = false;
    if (Pattern.compile("^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$", 2).matcher(paramString).matches()) {
      bool = true;
    }
    return bool;
  }
  
  private void k(boolean paramBoolean)
  {
    if ((aj.getString("initBool", "").equals("false")) && (this.ai != null) && (this.ah != null)) {
      try
      {
        b.a("Initial properties: " + this.ai);
        b.a("Initital Oject: " + this.ah);
        if (!aj.getString("initData", "noData").equals("noData"))
        {
          this.ai.put("conversion_type", "gplay");
          this.ai.put("conversion_data", aj.getString("initData", ""));
          b.a("Got referral, attaching: " + aj.getString("initData", ""));
        }
        for (;;)
        {
          this.ah.put("data", this.ai);
          T.a(this.ah, true, false);
          b.a("Sending Initial");
          aj.edit().putString("initBool", "true").commit();
          if (!paramBoolean) {
            break;
          }
          this.X.cancel();
          return;
          b.b("Did not get referral data.");
        }
        return;
      }
      catch (JSONException localJSONException)
      {
        b.b("An error occured during que initial. " + localJSONException);
        if (aa.b) {
          localJSONException.printStackTrace();
        }
      }
    }
  }
  
  protected void a(String paramString)
  {
    paramString = new q(this, paramString);
    an.schedule(paramString, 10L, TimeUnit.SECONDS);
  }
  
  public void a(String paramString1, String paramString2)
  {
    if (ad)
    {
      b.b("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      return;
    }
    try
    {
      b.a("Got event " + paramString1);
      HashMap localHashMap = new HashMap();
      localHashMap.put("event_name", paramString1);
      localHashMap.put("event_data", paramString2);
      a("event", localHashMap);
      return;
    }
    catch (Exception paramString1)
    {
      b.b("Error in event call: " + paramString1);
    }
  }
  
  public void a(Map<String, String> paramMap)
  {
    if (ad)
    {
      b.b("The library was not initialized properly or we cannot connect to our servers. Until this is fixed, this method cannot be used.");
      return;
    }
    b.a("Mapping identity");
    b("identityLink", paramMap);
  }
}
