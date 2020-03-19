package com.appodeal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import com.appodeal.ads.e.d;
import com.appodeal.ads.e.e;
import com.appodeal.ads.e.g;
import com.appodeal.ads.utils.Log.LogLevel;
import com.appodeal.ads.utils.aa;
import com.appodeal.ads.utils.ag;
import com.appodeal.ads.utils.c.a;
import com.appodeal.ads.utils.o;
import com.appodeal.ads.utils.t;
import com.appodeal.ads.utils.v;
import java.math.BigInteger;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Queue;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ac
{
  static JSONObject r;
  boolean A;
  String B;
  double C;
  private final JSONObject D;
  private final Handler E;
  private final int F;
  private final int G;
  private boolean H;
  final a a;
  final Context b;
  final int c;
  final String d;
  final String e;
  String f;
  String g;
  final d h;
  long i;
  final String j;
  Long k;
  final int l;
  final double m;
  final boolean n;
  String o;
  JSONObject p;
  String q;
  boolean s;
  boolean t;
  boolean u;
  boolean v;
  boolean w;
  boolean x;
  boolean y;
  boolean z;
  
  private ac(c paramC)
  {
    boolean bool2 = false;
    this.F = 0;
    this.G = 1;
    this.a = c.a(paramC);
    this.b = c.b(paramC);
    this.c = c.c(paramC);
    this.d = c.d(paramC);
    this.e = c.e(paramC);
    this.h = c.f(paramC);
    this.j = c.g(paramC);
    this.l = c.h(paramC);
    this.m = c.i(paramC);
    this.n = c.j(paramC);
    this.B = c.k(paramC);
    this.C = c.l(paramC);
    this.D = c.m(paramC);
    if (c.n(paramC) != null)
    {
      this.p = c.n(paramC).C;
      String str;
      if ((c.n(paramC).v) && (c.n(paramC).w == null)) {
        str = c.n(paramC).B;
      } else {
        str = null;
      }
      this.o = str;
      this.k = c.n(paramC).z;
      this.i = c.n(paramC).k();
      this.g = c.n(paramC).w;
      this.f = c.n(paramC).n;
      this.q = c.n(paramC).p();
    }
    this.E = new Handler(Looper.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (ac.this.a != null)
        {
          switch (paramAnonymousMessage.what)
          {
          default: 
            return;
          case 1: 
            paramAnonymousMessage = (JSONObject)paramAnonymousMessage.obj;
            if (paramAnonymousMessage != null)
            {
              ac.this.a.a(paramAnonymousMessage, ac.this.c, ac.this.d);
              return;
            }
            break;
          }
          ac.this.a.a(ac.this.c);
        }
      }
    };
    if (this.d == null) {
      return;
    }
    if ((l.b) && ((this.a == null) || ((this.a instanceof ag))) && ((this.d.equals("init")) || (this.d.equals("stats")) || (this.d.equals("show")) || (this.d.equals("click")) || (this.d.equals("finish")) || (this.d.equals("install")))) {
      return;
    }
    if ((!this.d.equals("banner")) && (!this.d.equals("debug"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.s = bool1;
    if ((!this.d.equals("banner_320")) && (!this.d.equals("debug_banner_320"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.t = bool1;
    if ((!this.d.equals("banner_mrec")) && (!this.d.equals("debug_mrec"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.u = bool1;
    if ((!this.d.equals("video")) && (!this.d.equals("debug_video"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.v = bool1;
    if ((!this.d.equals("rewarded_video")) && (!this.d.equals("debug_rewarded_video"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.w = bool1;
    if ((!this.d.equals("native")) && (!this.d.equals("debug_native"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.x = bool1;
    if ((!this.d.equals("debug")) && (!this.d.equals("debug_banner_320")) && (!this.d.equals("debug_video")) && (!this.d.equals("debug_rewarded_video")) && (!this.d.equals("debug_mrec")) && (!this.d.equals("debug_native"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.y = bool1;
    if ((!this.s) && (!this.t) && (!this.u) && (!this.v) && (!this.w) && (!this.x)) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.z = bool1;
    boolean bool1 = bool2;
    if (!this.d.equals("init"))
    {
      bool1 = bool2;
      if (!this.d.equals("stats"))
      {
        bool1 = bool2;
        if (!this.d.equals("show"))
        {
          bool1 = bool2;
          if (!this.d.equals("click"))
          {
            bool1 = bool2;
            if (!this.d.equals("finish"))
            {
              bool1 = bool2;
              if (!this.d.equals("install"))
              {
                bool1 = bool2;
                if (!this.d.equals("iap")) {
                  bool1 = true;
                }
              }
            }
          }
        }
      }
    }
    this.A = bool1;
    this.H = true;
  }
  
  /* Error */
  private String a(URL paramURL, JSONObject paramJSONObject, SharedPreferences paramSharedPreferences, javax.net.ssl.SSLSocketFactory paramSSLSocketFactory)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 9
    //   3: aconst_null
    //   4: astore 10
    //   6: aconst_null
    //   7: astore 11
    //   9: aconst_null
    //   10: astore 12
    //   12: aload_1
    //   13: invokevirtual 264	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   16: astore 7
    //   18: aload 4
    //   20: ifnull +37 -> 57
    //   23: aload 7
    //   25: astore 8
    //   27: aload_1
    //   28: invokevirtual 267	java/net/URL:getProtocol	()Ljava/lang/String;
    //   31: ldc_w 269
    //   34: invokevirtual 186	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   37: ifeq +20 -> 57
    //   40: aload 7
    //   42: astore 8
    //   44: aload 7
    //   46: checkcast 271	javax/net/ssl/HttpsURLConnection
    //   49: aload 4
    //   51: invokevirtual 275	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
    //   54: goto +3 -> 57
    //   57: aload 7
    //   59: astore 8
    //   61: aload_0
    //   62: getfield 239	com/appodeal/ads/ac:A	Z
    //   65: ifeq +51 -> 116
    //   68: aload 7
    //   70: astore 8
    //   72: aload_3
    //   73: aload_0
    //   74: getfield 85	com/appodeal/ads/ac:d	Ljava/lang/String;
    //   77: invokeinterface 281 2 0
    //   82: ifeq +34 -> 116
    //   85: sipush 10000
    //   88: istore 5
    //   90: aload 7
    //   92: astore 8
    //   94: aload 7
    //   96: sipush 10000
    //   99: invokevirtual 287	java/net/URLConnection:setConnectTimeout	(I)V
    //   102: aload 7
    //   104: astore 8
    //   106: aload 7
    //   108: iload 5
    //   110: invokevirtual 290	java/net/URLConnection:setReadTimeout	(I)V
    //   113: goto +23 -> 136
    //   116: sipush 20000
    //   119: istore 5
    //   121: aload 7
    //   123: astore 8
    //   125: aload 7
    //   127: sipush 20000
    //   130: invokevirtual 287	java/net/URLConnection:setConnectTimeout	(I)V
    //   133: goto -31 -> 102
    //   136: aload 7
    //   138: astore 8
    //   140: aload 7
    //   142: ldc_w 292
    //   145: ldc_w 294
    //   148: invokevirtual 298	java/net/URLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   151: aload 7
    //   153: astore 8
    //   155: aload 7
    //   157: iconst_1
    //   158: invokevirtual 302	java/net/URLConnection:setDoOutput	(Z)V
    //   161: aload 7
    //   163: astore 8
    //   165: new 304	java/io/ByteArrayOutputStream
    //   168: dup
    //   169: invokespecial 305	java/io/ByteArrayOutputStream:<init>	()V
    //   172: astore_1
    //   173: aload 7
    //   175: astore 8
    //   177: new 307	java/util/zip/GZIPOutputStream
    //   180: dup
    //   181: aload_1
    //   182: invokespecial 310	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   185: astore_3
    //   186: aload_3
    //   187: aload_2
    //   188: invokevirtual 315	org/json/JSONObject:toString	()Ljava/lang/String;
    //   191: ldc_w 317
    //   194: invokevirtual 321	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   197: invokevirtual 325	java/util/zip/GZIPOutputStream:write	([B)V
    //   200: aload 7
    //   202: astore 8
    //   204: aload_3
    //   205: invokevirtual 328	java/util/zip/GZIPOutputStream:close	()V
    //   208: goto +12 -> 220
    //   211: astore_2
    //   212: aload 7
    //   214: astore 8
    //   216: aload_2
    //   217: invokestatic 333	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   220: aload 7
    //   222: astore 8
    //   224: aload_1
    //   225: invokevirtual 337	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   228: iconst_0
    //   229: invokestatic 343	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
    //   232: astore_1
    //   233: aload 7
    //   235: astore 8
    //   237: aload 7
    //   239: invokevirtual 347	java/net/URLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   242: aload_1
    //   243: invokestatic 352	com/appodeal/ads/bg:a	(Ljava/io/OutputStream;Ljava/lang/String;)V
    //   246: aload 7
    //   248: astore 8
    //   250: aload 7
    //   252: invokevirtual 356	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   255: invokestatic 359	com/appodeal/ads/bg:a	(Ljava/io/InputStream;)Ljava/lang/String;
    //   258: astore_2
    //   259: aload 12
    //   261: astore_1
    //   262: aload_2
    //   263: ifnull +43 -> 306
    //   266: aload 12
    //   268: astore_1
    //   269: aload 7
    //   271: astore 8
    //   273: aload_2
    //   274: invokevirtual 363	java/lang/String:isEmpty	()Z
    //   277: ifne +29 -> 306
    //   280: aload 7
    //   282: astore 8
    //   284: aload_2
    //   285: ldc_w 365
    //   288: invokevirtual 186	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   291: istore 6
    //   293: iload 6
    //   295: ifeq +9 -> 304
    //   298: aload 12
    //   300: astore_1
    //   301: goto +5 -> 306
    //   304: aload_2
    //   305: astore_1
    //   306: aload_1
    //   307: astore_2
    //   308: aload 7
    //   310: ifnull +131 -> 441
    //   313: aload 7
    //   315: instanceof 271
    //   318: ifeq +15 -> 333
    //   321: aload_1
    //   322: astore_2
    //   323: aload 7
    //   325: checkcast 271	javax/net/ssl/HttpsURLConnection
    //   328: invokevirtual 368	javax/net/ssl/HttpsURLConnection:disconnect	()V
    //   331: aload_2
    //   332: areturn
    //   333: aload_1
    //   334: astore_2
    //   335: aload 7
    //   337: instanceof 370
    //   340: ifeq +101 -> 441
    //   343: aload_1
    //   344: astore_2
    //   345: aload 7
    //   347: checkcast 370	java/net/HttpURLConnection
    //   350: invokevirtual 371	java/net/HttpURLConnection:disconnect	()V
    //   353: aload_2
    //   354: areturn
    //   355: astore_1
    //   356: aload 7
    //   358: astore 8
    //   360: aload_3
    //   361: invokevirtual 328	java/util/zip/GZIPOutputStream:close	()V
    //   364: goto +12 -> 376
    //   367: astore_2
    //   368: aload 7
    //   370: astore 8
    //   372: aload_2
    //   373: invokestatic 333	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   376: aload 7
    //   378: astore 8
    //   380: aload_1
    //   381: athrow
    //   382: astore_1
    //   383: aconst_null
    //   384: astore 8
    //   386: goto +58 -> 444
    //   389: astore_2
    //   390: aconst_null
    //   391: astore_1
    //   392: aload_1
    //   393: astore 8
    //   395: aload_2
    //   396: invokestatic 333	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   399: aload 11
    //   401: astore_2
    //   402: aload_1
    //   403: ifnull +38 -> 441
    //   406: aload_1
    //   407: instanceof 271
    //   410: ifeq +12 -> 422
    //   413: aload 9
    //   415: astore_2
    //   416: aload_1
    //   417: astore 7
    //   419: goto -96 -> 323
    //   422: aload 11
    //   424: astore_2
    //   425: aload_1
    //   426: instanceof 370
    //   429: ifeq +12 -> 441
    //   432: aload 10
    //   434: astore_2
    //   435: aload_1
    //   436: astore 7
    //   438: goto -93 -> 345
    //   441: aload_2
    //   442: areturn
    //   443: astore_1
    //   444: aload 8
    //   446: ifnull +38 -> 484
    //   449: aload 8
    //   451: instanceof 271
    //   454: ifeq +14 -> 468
    //   457: aload 8
    //   459: checkcast 271	javax/net/ssl/HttpsURLConnection
    //   462: invokevirtual 368	javax/net/ssl/HttpsURLConnection:disconnect	()V
    //   465: goto +19 -> 484
    //   468: aload 8
    //   470: instanceof 370
    //   473: ifeq +11 -> 484
    //   476: aload 8
    //   478: checkcast 370	java/net/HttpURLConnection
    //   481: invokevirtual 371	java/net/HttpURLConnection:disconnect	()V
    //   484: aload_1
    //   485: athrow
    //   486: astore_2
    //   487: aload 7
    //   489: astore_1
    //   490: goto -98 -> 392
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	493	0	this	ac
    //   0	493	1	paramURL	URL
    //   0	493	2	paramJSONObject	JSONObject
    //   0	493	3	paramSharedPreferences	SharedPreferences
    //   0	493	4	paramSSLSocketFactory	javax.net.ssl.SSLSocketFactory
    //   88	32	5	i1	int
    //   291	3	6	bool	boolean
    //   16	472	7	localObject1	Object
    //   25	452	8	localObject2	Object
    //   1	413	9	localObject3	Object
    //   4	429	10	localObject4	Object
    //   7	416	11	localObject5	Object
    //   10	289	12	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   204	208	211	java/lang/Exception
    //   186	200	355	finally
    //   360	364	367	java/lang/Exception
    //   12	18	382	finally
    //   12	18	389	java/io/IOException
    //   27	40	443	finally
    //   44	54	443	finally
    //   61	68	443	finally
    //   72	85	443	finally
    //   94	102	443	finally
    //   106	113	443	finally
    //   125	133	443	finally
    //   140	151	443	finally
    //   155	161	443	finally
    //   165	173	443	finally
    //   177	186	443	finally
    //   204	208	443	finally
    //   216	220	443	finally
    //   224	233	443	finally
    //   237	246	443	finally
    //   250	259	443	finally
    //   273	280	443	finally
    //   284	293	443	finally
    //   360	364	443	finally
    //   372	376	443	finally
    //   380	382	443	finally
    //   395	399	443	finally
    //   27	40	486	java/io/IOException
    //   44	54	486	java/io/IOException
    //   61	68	486	java/io/IOException
    //   72	85	486	java/io/IOException
    //   94	102	486	java/io/IOException
    //   106	113	486	java/io/IOException
    //   125	133	486	java/io/IOException
    //   140	151	486	java/io/IOException
    //   155	161	486	java/io/IOException
    //   165	173	486	java/io/IOException
    //   177	186	486	java/io/IOException
    //   204	208	486	java/io/IOException
    //   216	220	486	java/io/IOException
    //   224	233	486	java/io/IOException
    //   237	246	486	java/io/IOException
    //   250	259	486	java/io/IOException
    //   273	280	486	java/io/IOException
    //   284	293	486	java/io/IOException
    //   360	364	486	java/io/IOException
    //   372	376	486	java/io/IOException
    //   380	382	486	java/io/IOException
  }
  
  private String a(Queue<String> paramQueue, JSONObject paramJSONObject, SharedPreferences paramSharedPreferences)
  {
    String str1;
    String str2;
    do
    {
      if (paramQueue.isEmpty()) {
        return null;
      }
      str1 = (String)paramQueue.poll();
      str2 = a(a(str1), paramJSONObject, paramSharedPreferences, null);
    } while (str2 == null);
    l.a = str1;
    return str2;
  }
  
  private void a(JSONObject paramJSONObject)
  {
    JSONArray localJSONArray = paramJSONObject.optJSONArray("segments");
    if (localJSONArray != null)
    {
      if (localJSONArray.length() == 0) {
        return;
      }
      paramJSONObject = new com.appodeal.ads.e.h(this.b, paramJSONObject.optJSONObject("user_data"));
      paramJSONObject.b(localJSONArray);
      paramJSONObject = paramJSONObject.a(localJSONArray);
      if (paramJSONObject == null)
      {
        com.appodeal.ads.e.h.c();
        return;
      }
      if (paramJSONObject.c() != com.appodeal.ads.e.h.a().c())
      {
        try
        {
          paramJSONObject.a();
        }
        catch (JSONException localJSONException)
        {
          Appodeal.a(localJSONException);
        }
        com.appodeal.ads.e.h.a(paramJSONObject);
      }
    }
  }
  
  private String c(String paramString)
  {
    return String.format("%s_timestamp", new Object[] { paramString });
  }
  
  private JSONObject c(SharedPreferences paramSharedPreferences)
  {
    for (;;)
    {
      try
      {
        Object localObject2;
        Object localObject1;
        if ((r == null) || (r.length() == 0))
        {
          r = new JSONObject();
          localObject2 = this.b.getPackageManager();
          paramSharedPreferences = paramSharedPreferences.getString("appKey", null);
          if (paramSharedPreferences == null) {
            return null;
          }
          r.put("app_key", paramSharedPreferences);
          r.put("android", Build.VERSION.RELEASE);
          r.put("android_level", Build.VERSION.SDK_INT);
          r.put("sdk", "2.4.5");
          String str = this.b.getPackageName();
          r.put("package", str);
          try
          {
            paramSharedPreferences = ((PackageManager)localObject2).getPackageInfo(str, 0);
            r.put("package_version", paramSharedPreferences.versionName);
            r.put("package_code", paramSharedPreferences.versionCode);
            r.put("install_time", paramSharedPreferences.firstInstallTime / 1000L);
            paramSharedPreferences = ((PackageManager)localObject2).getApplicationInfo(str, 0);
            r.put("target_sdk_version", paramSharedPreferences.targetSdkVersion);
          }
          catch (PackageManager.NameNotFoundException paramSharedPreferences)
          {
            Appodeal.a(paramSharedPreferences);
          }
          if (Appodeal.k != null) {
            r.put("framework", Appodeal.k);
          }
          if (Appodeal.l != null) {
            r.put("plugin_version", Appodeal.l);
          }
          paramSharedPreferences = bg.f(this.b);
          r.put("width", paramSharedPreferences.first);
          r.put("height", paramSharedPreferences.second);
          r.put("pxratio", bg.i(this.b));
          if (bg.m(this.b))
          {
            paramSharedPreferences = r;
            localObject1 = "tablet";
            paramSharedPreferences.put("device_type", localObject1);
          }
          else
          {
            paramSharedPreferences = r;
            localObject1 = "phone";
            continue;
          }
          if (Build.MANUFACTURER.equals("Amazon"))
          {
            paramSharedPreferences = "amazon";
            r.put("platform", paramSharedPreferences);
            try
            {
              localObject1 = ((PackageManager)localObject2).getInstallerPackageName(str);
              paramSharedPreferences = (SharedPreferences)localObject1;
              if (localObject1 == null) {
                paramSharedPreferences = "unknown";
              }
              r.put("installer", paramSharedPreferences);
            }
            catch (Exception paramSharedPreferences)
            {
              Appodeal.a(paramSharedPreferences);
            }
            r.put("manufacturer", Build.MANUFACTURER);
            r.put("rooted", bg.a());
            r.put("webview_version", bg.s(this.b));
            r.put("multidex", bg.b(new String[] { "android.support.multidex.MultiDex" }));
          }
        }
        else
        {
          paramSharedPreferences = new JSONObject();
          localObject1 = r.keys();
          if (((Iterator)localObject1).hasNext())
          {
            localObject2 = (String)((Iterator)localObject1).next();
            paramSharedPreferences.put((String)localObject2, r.get((String)localObject2));
            continue;
          }
          return paramSharedPreferences;
        }
      }
      finally {}
      paramSharedPreferences = "google";
    }
  }
  
  private String d(String paramString)
  {
    return String.format("%s_wst", new Object[] { paramString });
  }
  
  URL a(String paramString)
  {
    if (this.A)
    {
      if (this.n) {
        return bg.f(this.d);
      }
      return new URL(String.format("%s/%s", new Object[] { paramString, "get" }));
    }
    return new URL(String.format("%s/%s", new Object[] { paramString, this.d }));
  }
  
  Queue<String> a(Date paramDate)
  {
    LinkedList localLinkedList = new LinkedList();
    localLinkedList.add(com.appodeal.ads.utils.i.g());
    Object localObject2 = new SimpleDateFormat("yyyy", Locale.ENGLISH).format(paramDate);
    Object localObject1 = new SimpleDateFormat("yyyy-MM", Locale.ENGLISH).format(paramDate);
    paramDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(paramDate);
    StringBuilder localStringBuilder = new StringBuilder("https://a.");
    localStringBuilder.append(b((String)localObject2));
    localStringBuilder.append(".com");
    localLinkedList.add(localStringBuilder.toString());
    localObject2 = new StringBuilder("https://a.");
    ((StringBuilder)localObject2).append(b((String)localObject1));
    ((StringBuilder)localObject2).append(".com");
    localLinkedList.add(((StringBuilder)localObject2).toString());
    localObject1 = new StringBuilder("https://a.");
    ((StringBuilder)localObject1).append(b(paramDate));
    ((StringBuilder)localObject1).append(".com");
    localLinkedList.add(((StringBuilder)localObject1).toString());
    return localLinkedList;
  }
  
  JSONObject a(SharedPreferences paramSharedPreferences)
  {
    if (paramSharedPreferences == null) {
      return null;
    }
    String str1 = paramSharedPreferences.getString("appKey", null);
    if (str1 == null) {
      return null;
    }
    String str2 = ba.h();
    if (ba.g()) {
      paramSharedPreferences = "0";
    } else {
      paramSharedPreferences = "1";
    }
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("app_key", str1);
    localJSONObject.put("sdk", "2.4.5");
    localJSONObject.put("package", this.b.getPackageName());
    localJSONObject.put("framework", Appodeal.k);
    localJSONObject.put("android_id", str2);
    localJSONObject.put("advertising_tracking", paramSharedPreferences);
    if (Build.MANUFACTURER.equals("Amazon")) {
      paramSharedPreferences = "amazon";
    } else {
      paramSharedPreferences = "google";
    }
    localJSONObject.put("platform", paramSharedPreferences);
    localJSONObject.put("consent", ba.f());
    localJSONObject.put("fingerprint", ba.i());
    localJSONObject.put("adidg", ba.j());
    return localJSONObject;
  }
  
  public void a()
  {
    if (this.H) {
      t.a.execute(new b(null));
    }
  }
  
  void a(SharedPreferences paramSharedPreferences, int paramInt, String paramString)
  {
    paramSharedPreferences = paramSharedPreferences.edit();
    paramSharedPreferences.putInt(d(paramString), paramInt);
    paramSharedPreferences.apply();
  }
  
  void a(SharedPreferences paramSharedPreferences, String paramString1, String paramString2)
  {
    paramSharedPreferences = paramSharedPreferences.edit();
    paramSharedPreferences.putString(paramString1, paramString2);
    paramSharedPreferences.putLong(c(paramString1), System.currentTimeMillis());
    paramSharedPreferences.apply();
  }
  
  boolean a(SharedPreferences paramSharedPreferences, String paramString)
  {
    long l1 = paramSharedPreferences.getLong(c(paramString), 0L);
    if (System.currentTimeMillis() - l1 > b(paramSharedPreferences, paramString))
    {
      paramSharedPreferences = paramSharedPreferences.edit();
      paramSharedPreferences.remove(paramString);
      paramSharedPreferences.remove(c(paramString));
      paramSharedPreferences.remove(d(paramString));
      paramSharedPreferences.apply();
      return false;
    }
    return true;
  }
  
  int b(SharedPreferences paramSharedPreferences, String paramString)
  {
    return paramSharedPreferences.getInt(d(paramString), 86400000);
  }
  
  String b(String paramString)
  {
    String str = new String(com.appodeal.ads.utils.i.b);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(str);
    return new BigInteger(1, bg.a(localStringBuilder.toString().getBytes())).toString(16);
  }
  
  JSONObject b()
  {
    JSONObject localJSONObject = new JSONObject();
    int i1 = o.a();
    int i2 = o.b();
    int i3 = o.c();
    try
    {
      localJSONObject.put("show", i1);
      localJSONObject.put("click", i2);
      if ((this.v) || (this.w) || ((this.j != null) && ((this.j.equals("video")) || (this.j.equals("rewarded_video"))))) {
        localJSONObject.put("finish", i3);
      }
      if ((this.s) || ((this.j != null) && (this.j.equals("banner"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "show" }), o.a("interstitial"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "click" }), o.b("interstitial"));
      }
      if ((this.v) || ((this.j != null) && (this.j.equals("video"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "show" }), o.a("video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "click" }), o.b("video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "finish" }), o.c("video"));
      }
      if ((this.w) || ((this.j != null) && (this.j.equals("rewarded_video"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "show" }), o.a("rewarded_video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "click" }), o.b("rewarded_video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "finish" }), o.c("rewarded_video"));
      }
      if ((this.t) || ((this.j != null) && (this.j.equals("banner_320"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "show" }), o.a("banner"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "click" }), o.b("banner"));
      }
      if ((this.u) || ((this.j != null) && (this.j.equals("banner_mrec"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "show" }), o.a("mrec"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "click" }), o.b("mrec"));
      }
      if ((this.x) || ((this.j != null) && (this.j.equals("native"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "native", "show" }), o.a("native"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "native", "click" }), o.b("native"));
        return localJSONObject;
      }
    }
    catch (Exception localException)
    {
      Appodeal.a(localException);
    }
    return localJSONObject;
  }
  
  JSONObject b(SharedPreferences paramSharedPreferences)
  {
    JSONObject localJSONObject = c(paramSharedPreferences);
    if (localJSONObject == null) {
      return null;
    }
    Object localObject3 = ba.h();
    Object localObject1;
    if (ba.g()) {
      localObject1 = "0";
    } else {
      localObject1 = "1";
    }
    localJSONObject.put("android_id", localObject3);
    localJSONObject.put("advertising_tracking", localObject1);
    localJSONObject.put("adidg", ba.j());
    try
    {
      if (this.s) {
        localJSONObject.put("type", "banner");
      }
      if (this.t)
      {
        localJSONObject.put("type", "banner_320");
        if (m.h()) {
          localJSONObject.put("large_banners", true);
        }
      }
      if (this.u) {
        localJSONObject.put("type", "banner_mrec");
      }
      if ((this.v) || (this.w)) {
        localJSONObject.put("type", "video");
      }
      if (this.w) {
        localJSONObject.put("rewarded_video", true);
      }
      if (this.x) {
        localJSONObject.put("type", "native");
      }
      if (this.y) {
        localJSONObject.put("debug", true);
      }
      if (l.b) {
        localJSONObject.put("test", true);
      }
      localJSONObject.put("battery", bg.k(this.b));
      localJSONObject.put("crr", bg.c(this.b));
      localJSONObject.put("locale", Locale.getDefault().toString());
      localObject1 = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.ENGLISH);
      localJSONObject.put("timezone", new SimpleDateFormat("Z", Locale.ENGLISH).format(((Calendar)localObject1).getTime()));
      localJSONObject.put("local_time", System.currentTimeMillis() / 1000L);
      localJSONObject.put("user_agent", System.getProperty("http.agent"));
      com.appodeal.ads.utils.f.c(this.b);
      localJSONObject.put("session_id", com.appodeal.ads.utils.f.a(paramSharedPreferences));
      localJSONObject.put("session_uptime", com.appodeal.ads.utils.f.b());
      localJSONObject.put("app_uptime", com.appodeal.ads.utils.f.b(paramSharedPreferences));
      localJSONObject.put("consent", ba.f());
      localJSONObject.put("fingerprint", ba.b());
      if (!i.h)
      {
        localObject1 = bg.b(this.b);
        localJSONObject.put("connection", ((bg.a)localObject1).a);
        localJSONObject.put("connection_subtype", ((bg.a)localObject1).b);
        localJSONObject.put("connection_fast", ((bg.a)localObject1).c);
        localObject1 = bg.d(this.b);
        localJSONObject.put("lt", ((Pair)localObject1).first);
        localJSONObject.put("lat", ((Pair)((Pair)localObject1).second).first);
        localJSONObject.put("lon", ((Pair)((Pair)localObject1).second).second);
        localJSONObject.put("model", String.format("%s %s", new Object[] { Build.MANUFACTURER, Build.MODEL }));
      }
      localJSONObject.put("coppa", i.h);
      localJSONObject.put("session_uuid", Appodeal.d().a());
      if (this.d.equals("iap"))
      {
        localJSONObject.put("currency", this.B);
        localJSONObject.put("amount", this.C);
      }
      if (this.z)
      {
        localObject1 = new JSONArray();
        if (this.s) {
          localObject1 = new JSONArray(u.b().b());
        }
        if (this.v) {
          localObject1 = new JSONArray(av.b().b());
        }
        if (this.w) {
          localObject1 = new JSONArray(bb.b().b());
        }
        if (this.t) {
          localObject1 = new JSONArray(m.b().b());
        }
        if (this.u) {
          localObject1 = new JSONArray(af.b().b());
        }
        if (this.x) {
          localObject1 = new JSONArray(Native.b().b());
        }
        localJSONObject.put("show_array", localObject1);
      }
      if (this.g != null) {
        localJSONObject.put("loaded_offer", this.g);
      }
      if (this.o != null) {
        localJSONObject.put("last_offer", this.o);
      }
      if ((this.k != null) && (this.k.longValue() != -1L)) {
        localJSONObject.put("segment_id", this.k);
      }
      if (this.i != 0L) {
        localJSONObject.put("show_timestamp", this.i);
      }
      if (this.d.equals("click")) {
        localJSONObject.put("click_timestamp", System.currentTimeMillis() / 1000L);
      }
      if (this.d.equals("finish")) {
        localJSONObject.put("finish_timestamp", System.currentTimeMillis() / 1000L);
      }
      if (this.l > 1) {
        localJSONObject.put("capacity", this.l);
      }
      if (this.m > 0.0D) {
        localJSONObject.put("price_floor", this.m);
      }
      if (this.p != null) {
        localJSONObject.put("ad_properties", this.p);
      }
      if (this.q != null) {
        localJSONObject.put("impid", this.q);
      }
      localJSONObject.put("id", this.e);
      localJSONObject.put("main_id", this.f);
      if ((this.z) || (this.j != null)) {
        localJSONObject.put("ad_stats", b());
      }
      if ((this.z) && (l.l == null)) {
        localJSONObject.put("check_sdk_version", true);
      }
      if (this.h != null) {
        localJSONObject.put("placement_id", this.h.b());
      }
      if ((this.D != null) && (this.d.equals("stats"))) {
        localJSONObject.put("ad_unit_stat", this.D);
      }
      if (ExtraData.a().length() > 0) {
        localJSONObject.put("ext", ExtraData.a());
      }
    }
    catch (JSONException localJSONException)
    {
      Appodeal.a(localJSONException);
    }
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramSharedPreferences.getLong("lastSettingsTime", 0L));
    localCalendar.add(5, 1);
    if ((!UserSettings.a) && (this.z) && ((localCalendar.getTimeInMillis() < System.currentTimeMillis()) || (paramSharedPreferences.getBoolean("should_update_user_settings", true))))
    {
      UserSettings.a = true;
      try
      {
        localJSONObject.put("sa", com.appodeal.ads.utils.ac.a(this.b));
      }
      catch (Exception localException1)
      {
        Appodeal.a(localException1);
      }
      if (!i.h) {
        try
        {
          localJSONObject.put("user_settings", bg.t(this.b).h());
        }
        catch (Exception localException2)
        {
          Appodeal.a(localException2);
        }
      }
      localObject2 = paramSharedPreferences.edit();
      ((SharedPreferences.Editor)localObject2).putLong("lastSettingsTime", System.currentTimeMillis());
      ((SharedPreferences.Editor)localObject2).putBoolean("should_update_user_settings", false);
      ((SharedPreferences.Editor)localObject2).apply();
      UserSettings.a = false;
    }
    Object localObject2 = Calendar.getInstance();
    ((Calendar)localObject2).setTimeInMillis(paramSharedPreferences.getLong("lastAppTime", 0L));
    ((Calendar)localObject2).add(5, 1);
    if ((!l.i) && (l.h) && (this.z) && (((Calendar)localObject2).getTimeInMillis() < System.currentTimeMillis()))
    {
      l.i = true;
      try
      {
        localObject2 = new JSONArray();
        Object localObject4 = this.b.getPackageManager().getInstalledApplications(0);
        localObject3 = Pattern.compile("^?(?:com\\.android|com\\.google|com\\.sec|com\\.samsung|com\\.sonyericsson|com\\.sonymobile|com\\.motorola|com\\.htc).*$");
        if (localObject4 != null)
        {
          localObject4 = ((List)localObject4).iterator();
          while (((Iterator)localObject4).hasNext())
          {
            String str = ((ApplicationInfo)((Iterator)localObject4).next()).packageName;
            if ((!((Pattern)localObject3).matcher(str).matches()) && (!str.equals("android"))) {
              ((JSONArray)localObject2).put(str);
            }
          }
        }
        localJSONObject.put("apps", localObject2);
      }
      catch (Exception localException3)
      {
        Appodeal.a(localException3);
      }
      paramSharedPreferences = paramSharedPreferences.edit();
      paramSharedPreferences.putLong("lastAppTime", System.currentTimeMillis());
      paramSharedPreferences.apply();
      l.i = false;
    }
    ba.a(localJSONObject);
    return localJSONObject;
  }
  
  public static abstract interface a
  {
    public abstract void a(int paramInt);
    
    public abstract void a(JSONObject paramJSONObject, int paramInt, String paramString);
  }
  
  private class b
    implements Runnable
  {
    private b() {}
    
    public void run()
    {
      try
      {
        Object localObject1 = ac.this.b.getSharedPreferences("appodeal", 0);
        JSONObject localJSONObject;
        if (ac.this.d.equals("init")) {
          localJSONObject = ac.this.a((SharedPreferences)localObject1);
        } else {
          localJSONObject = ac.this.b((SharedPreferences)localObject1);
        }
        if (localJSONObject == null)
        {
          ac.a(ac.this).sendEmptyMessage(0);
          return;
        }
        SharedPreferences localSharedPreferences = ac.this.b.getSharedPreferences("Appodeal", 0);
        if (l.a == null) {
          localObject1 = ac.this;
        }
        for (Object localObject2 = com.appodeal.ads.utils.i.g();; localObject2 = l.a)
        {
          localObject1 = ((ac)localObject1).a((String)localObject2);
          break;
          localObject1 = ac.this;
        }
        String str = ac.a(ac.this, (URL)localObject1, localJSONObject, localSharedPreferences, null);
        localObject1 = str;
        if (ac.this.A)
        {
          if (str == null)
          {
            localObject2 = str;
            if (localSharedPreferences.contains(ac.this.d))
            {
              localObject2 = str;
              if (ac.this.a(localSharedPreferences, ac.this.d))
              {
                Appodeal.a(new a("/get error, using saved waterfall"));
                localObject2 = localSharedPreferences.getString(ac.this.d, "");
              }
            }
          }
          else
          {
            ac.this.a(localSharedPreferences, ac.this.d, str);
            localObject2 = str;
          }
          localObject1 = localObject2;
          if (localObject2 == null)
          {
            localObject1 = ac.this.a(new Date());
            localObject2 = ac.a(ac.this, (Queue)localObject1, localJSONObject, localSharedPreferences);
            localObject1 = localObject2;
            if (localObject2 != null)
            {
              ac.this.a(localSharedPreferences, ac.this.d, (String)localObject2);
              localObject1 = localObject2;
            }
          }
        }
        if (localObject1 == null)
        {
          ac.a(ac.this).sendEmptyMessage(0);
          return;
        }
        try
        {
          localObject2 = new JSONObject((String)localObject1);
          if (ac.this.A) {
            Appodeal.a((String)localObject1, Log.LogLevel.verbose);
          } else {
            Appodeal.a((String)localObject1);
          }
          try
          {
            if (ac.this.A)
            {
              UserSettings.a(ac.this.b, ((JSONObject)localObject2).optJSONObject("user_data"));
              i.a(ac.this.b, ((JSONObject)localObject2).optJSONObject("app_data"), ((JSONObject)localObject2).optBoolean("for_kids"), ((JSONObject)localObject2).optBoolean("corona"));
              ac.this.a(localSharedPreferences, ((JSONObject)localObject2).optInt("wst", 86400000), ac.this.d);
              ac.a(ac.this, (JSONObject)localObject2);
              e.a(((JSONObject)localObject2).optJSONArray("placements"));
              e.c();
              if (Appodeal.h != null) {
                Appodeal.h.a();
              }
            }
          }
          catch (Exception localException1)
          {
            Appodeal.a(localException1);
          }
          Message localMessage = ac.a(ac.this).obtainMessage(1, localObject2);
          ac.a(ac.this).sendMessage(localMessage);
          return;
        }
        catch (JSONException localJSONException)
        {
          if (!ac.this.d.equals("stats")) {
            Appodeal.a(localJSONException);
          }
          ac.a(ac.this).sendEmptyMessage(0);
          return;
        }
        return;
      }
      catch (Exception localException2)
      {
        Appodeal.a(localException2);
        ac.a(ac.this).sendEmptyMessage(0);
      }
    }
  }
  
  public static class c
  {
    private final Context a;
    private final int b;
    private final String c;
    private ac.a d;
    private String e;
    private d f;
    private String g;
    private int h = 1;
    private double i = -1.0D;
    private boolean j;
    private String k;
    private double l;
    private JSONObject m;
    private h n;
    
    public c(Context paramContext, int paramInt, String paramString)
    {
      this.a = paramContext;
      this.b = paramInt;
      this.c = paramString;
    }
    
    public c a(double paramDouble)
    {
      this.i = paramDouble;
      return this;
    }
    
    public c a(double paramDouble, String paramString)
    {
      this.l = paramDouble;
      this.k = paramString;
      return this;
    }
    
    public c a(int paramInt)
    {
      this.h = paramInt;
      return this;
    }
    
    public c a(ac.a paramA)
    {
      this.d = paramA;
      return this;
    }
    
    public c a(d paramD)
    {
      this.f = paramD;
      return this;
    }
    
    public c a(h paramH)
    {
      this.n = paramH;
      return this;
    }
    
    public c a(String paramString)
    {
      this.e = paramString;
      return this;
    }
    
    public c a(JSONObject paramJSONObject)
    {
      this.m = paramJSONObject;
      return this;
    }
    
    public c a(boolean paramBoolean)
    {
      this.j = paramBoolean;
      return this;
    }
    
    public ac a()
    {
      return new ac(this, null);
    }
    
    public c b(String paramString)
    {
      this.g = paramString;
      return this;
    }
  }
}
