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
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.annotation.av;
import android.util.Pair;
import com.appodeal.ads.f.d;
import com.appodeal.ads.utils.Log.LogLevel;
import com.appodeal.ads.utils.aa;
import com.appodeal.ads.utils.c.a;
import com.appodeal.ads.utils.f;
import com.appodeal.ads.utils.i;
import com.appodeal.ads.utils.o;
import com.appodeal.ads.utils.s;
import com.appodeal.ads.utils.u;
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

public class ab
{
  @av
  static JSONObject s;
  @av
  boolean A;
  @av
  boolean B;
  @av
  String C;
  @av
  double D;
  private final JSONObject E;
  private final Handler F;
  private final int G;
  private final int H;
  private boolean I;
  @av
  final a a;
  @av
  final Context b;
  @av
  final int c;
  @av
  final String d;
  @av
  final String e;
  @av
  String f;
  @av
  String g;
  @av
  final com.appodeal.ads.d.h h;
  @av
  final d i;
  @av
  long j;
  @av
  final String k;
  @av
  Long l;
  @av
  final int m;
  @av
  final double n;
  @av
  final boolean o;
  @av
  String p;
  @av
  JSONObject q;
  @av
  String r;
  @av
  boolean t;
  @av
  boolean u;
  @av
  boolean v;
  @av
  boolean w;
  @av
  boolean x;
  @av
  boolean y;
  @av
  boolean z;
  
  private ab(c paramC)
  {
    boolean bool2 = false;
    this.G = 0;
    this.H = 1;
    this.a = c.a(paramC);
    this.b = c.b(paramC);
    this.c = c.c(paramC);
    this.d = c.d(paramC);
    this.e = c.e(paramC);
    this.h = c.f(paramC);
    this.i = c.g(paramC);
    this.k = c.h(paramC);
    this.m = c.i(paramC);
    this.n = c.j(paramC);
    this.o = c.k(paramC);
    this.C = c.l(paramC);
    this.D = c.m(paramC);
    this.E = c.n(paramC);
    if (c.o(paramC) != null)
    {
      this.q = c.o(paramC).F;
      String str;
      if ((c.o(paramC).v) && (c.o(paramC).w == null)) {
        str = c.o(paramC).E;
      } else {
        str = null;
      }
      this.p = str;
      this.l = c.o(paramC).C;
      this.j = c.o(paramC).i();
      this.g = c.o(paramC).w;
      this.f = c.o(paramC).m;
      this.r = c.o(paramC).l();
    }
    this.F = new Handler(Looper.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (ab.this.a != null)
        {
          switch (paramAnonymousMessage.what)
          {
          default: 
            return;
          case 1: 
            paramAnonymousMessage = (JSONObject)paramAnonymousMessage.obj;
            if (paramAnonymousMessage != null)
            {
              ab.this.a.a(paramAnonymousMessage, ab.this.c, ab.this.d);
              return;
            }
            break;
          }
          ab.this.a.a(ab.this.c);
        }
      }
    };
    if (this.d == null) {
      return;
    }
    if ((k.b) && ((this.a == null) || ((this.a instanceof com.appodeal.ads.utils.ae))) && ((this.d.equals("init")) || (this.d.equals("stats")) || (this.d.equals("show")) || (this.d.equals("click")) || (this.d.equals("finish")) || (this.d.equals("install")))) {
      return;
    }
    if ((!this.d.equals("banner")) && (!this.d.equals("debug"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.t = bool1;
    if ((!this.d.equals("banner_320")) && (!this.d.equals("debug_banner_320"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.u = bool1;
    if ((!this.d.equals("banner_mrec")) && (!this.d.equals("debug_mrec"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.v = bool1;
    if ((!this.d.equals("video")) && (!this.d.equals("debug_video"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.w = bool1;
    if ((!this.d.equals("rewarded_video")) && (!this.d.equals("debug_rewarded_video"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.x = bool1;
    if ((!this.d.equals("native")) && (!this.d.equals("debug_native"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.y = bool1;
    if ((!this.d.equals("debug")) && (!this.d.equals("debug_banner_320")) && (!this.d.equals("debug_video")) && (!this.d.equals("debug_rewarded_video")) && (!this.d.equals("debug_mrec")) && (!this.d.equals("debug_native"))) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.z = bool1;
    if ((!this.t) && (!this.u) && (!this.v) && (!this.w) && (!this.x) && (!this.y)) {
      bool1 = false;
    } else {
      bool1 = true;
    }
    this.A = bool1;
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
    this.B = bool1;
    this.I = true;
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
    //   13: invokevirtual 272	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   16: astore 7
    //   18: aload 4
    //   20: ifnull +37 -> 57
    //   23: aload 7
    //   25: astore 8
    //   27: aload_1
    //   28: invokevirtual 275	java/net/URL:getProtocol	()Ljava/lang/String;
    //   31: ldc_w 277
    //   34: invokevirtual 194	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   37: ifeq +20 -> 57
    //   40: aload 7
    //   42: astore 8
    //   44: aload 7
    //   46: checkcast 279	javax/net/ssl/HttpsURLConnection
    //   49: aload 4
    //   51: invokevirtual 283	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
    //   54: goto +3 -> 57
    //   57: aload 7
    //   59: astore 8
    //   61: aload_0
    //   62: getfield 247	com/appodeal/ads/ab:B	Z
    //   65: ifeq +51 -> 116
    //   68: aload 7
    //   70: astore 8
    //   72: aload_3
    //   73: aload_0
    //   74: getfield 87	com/appodeal/ads/ab:d	Ljava/lang/String;
    //   77: invokeinterface 289 2 0
    //   82: ifeq +34 -> 116
    //   85: sipush 10000
    //   88: istore 5
    //   90: aload 7
    //   92: astore 8
    //   94: aload 7
    //   96: sipush 10000
    //   99: invokevirtual 295	java/net/URLConnection:setConnectTimeout	(I)V
    //   102: aload 7
    //   104: astore 8
    //   106: aload 7
    //   108: iload 5
    //   110: invokevirtual 298	java/net/URLConnection:setReadTimeout	(I)V
    //   113: goto +23 -> 136
    //   116: sipush 20000
    //   119: istore 5
    //   121: aload 7
    //   123: astore 8
    //   125: aload 7
    //   127: sipush 20000
    //   130: invokevirtual 295	java/net/URLConnection:setConnectTimeout	(I)V
    //   133: goto -31 -> 102
    //   136: aload 7
    //   138: astore 8
    //   140: aload 7
    //   142: ldc_w 300
    //   145: ldc_w 302
    //   148: invokevirtual 306	java/net/URLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   151: aload 7
    //   153: astore 8
    //   155: aload 7
    //   157: iconst_1
    //   158: invokevirtual 310	java/net/URLConnection:setDoOutput	(Z)V
    //   161: aload 7
    //   163: astore 8
    //   165: new 312	java/io/ByteArrayOutputStream
    //   168: dup
    //   169: invokespecial 313	java/io/ByteArrayOutputStream:<init>	()V
    //   172: astore_1
    //   173: aload 7
    //   175: astore 8
    //   177: new 315	java/util/zip/GZIPOutputStream
    //   180: dup
    //   181: aload_1
    //   182: invokespecial 318	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   185: astore_3
    //   186: aload_3
    //   187: aload_2
    //   188: invokevirtual 323	org/json/JSONObject:toString	()Ljava/lang/String;
    //   191: ldc_w 325
    //   194: invokevirtual 329	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   197: invokevirtual 333	java/util/zip/GZIPOutputStream:write	([B)V
    //   200: aload 7
    //   202: astore 8
    //   204: aload_3
    //   205: invokevirtual 336	java/util/zip/GZIPOutputStream:close	()V
    //   208: goto +12 -> 220
    //   211: astore_2
    //   212: aload 7
    //   214: astore 8
    //   216: aload_2
    //   217: invokestatic 341	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   220: aload 7
    //   222: astore 8
    //   224: aload_1
    //   225: invokevirtual 345	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   228: iconst_0
    //   229: invokestatic 351	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
    //   232: astore_1
    //   233: aload 7
    //   235: astore 8
    //   237: aload 7
    //   239: invokevirtual 355	java/net/URLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   242: aload_1
    //   243: invokestatic 360	com/appodeal/ads/bf:a	(Ljava/io/OutputStream;Ljava/lang/String;)V
    //   246: aload 7
    //   248: astore 8
    //   250: aload 7
    //   252: invokevirtual 364	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   255: invokestatic 367	com/appodeal/ads/bf:a	(Ljava/io/InputStream;)Ljava/lang/String;
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
    //   274: invokevirtual 371	java/lang/String:isEmpty	()Z
    //   277: ifne +29 -> 306
    //   280: aload 7
    //   282: astore 8
    //   284: aload_2
    //   285: ldc_w 373
    //   288: invokevirtual 194	java/lang/String:equals	(Ljava/lang/Object;)Z
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
    //   315: instanceof 279
    //   318: ifeq +15 -> 333
    //   321: aload_1
    //   322: astore_2
    //   323: aload 7
    //   325: checkcast 279	javax/net/ssl/HttpsURLConnection
    //   328: invokevirtual 376	javax/net/ssl/HttpsURLConnection:disconnect	()V
    //   331: aload_2
    //   332: areturn
    //   333: aload_1
    //   334: astore_2
    //   335: aload 7
    //   337: instanceof 378
    //   340: ifeq +101 -> 441
    //   343: aload_1
    //   344: astore_2
    //   345: aload 7
    //   347: checkcast 378	java/net/HttpURLConnection
    //   350: invokevirtual 379	java/net/HttpURLConnection:disconnect	()V
    //   353: aload_2
    //   354: areturn
    //   355: astore_1
    //   356: aload 7
    //   358: astore 8
    //   360: aload_3
    //   361: invokevirtual 336	java/util/zip/GZIPOutputStream:close	()V
    //   364: goto +12 -> 376
    //   367: astore_2
    //   368: aload 7
    //   370: astore 8
    //   372: aload_2
    //   373: invokestatic 341	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
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
    //   396: invokestatic 341	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   399: aload 11
    //   401: astore_2
    //   402: aload_1
    //   403: ifnull +38 -> 441
    //   406: aload_1
    //   407: instanceof 279
    //   410: ifeq +12 -> 422
    //   413: aload 9
    //   415: astore_2
    //   416: aload_1
    //   417: astore 7
    //   419: goto -96 -> 323
    //   422: aload 11
    //   424: astore_2
    //   425: aload_1
    //   426: instanceof 378
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
    //   451: instanceof 279
    //   454: ifeq +14 -> 468
    //   457: aload 8
    //   459: checkcast 279	javax/net/ssl/HttpsURLConnection
    //   462: invokevirtual 376	javax/net/ssl/HttpsURLConnection:disconnect	()V
    //   465: goto +19 -> 484
    //   468: aload 8
    //   470: instanceof 378
    //   473: ifeq +11 -> 484
    //   476: aload 8
    //   478: checkcast 378	java/net/HttpURLConnection
    //   481: invokevirtual 379	java/net/HttpURLConnection:disconnect	()V
    //   484: aload_1
    //   485: athrow
    //   486: astore_2
    //   487: aload 7
    //   489: astore_1
    //   490: goto -98 -> 392
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	493	0	this	ab
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
    if (paramQueue.isEmpty()) {
      return null;
    }
    String str1 = (String)paramQueue.poll();
    String str2 = a(a(str1), paramJSONObject, paramSharedPreferences, null);
    if (str2 != null)
    {
      k.a = str1;
      return str2;
    }
    return a(paramQueue, paramJSONObject, paramSharedPreferences);
  }
  
  private void a(JSONObject paramJSONObject)
  {
    JSONArray localJSONArray = paramJSONObject.optJSONArray("segments");
    if (localJSONArray != null)
    {
      if (localJSONArray.length() == 0) {
        return;
      }
      paramJSONObject = new com.appodeal.ads.f.h(this.b, paramJSONObject.optJSONObject("user_data"));
      paramJSONObject.b(localJSONArray);
      paramJSONObject = paramJSONObject.a(localJSONArray);
      if (paramJSONObject == null)
      {
        com.appodeal.ads.f.h.c();
        return;
      }
      if (paramJSONObject.c() != com.appodeal.ads.f.h.a().c())
      {
        try
        {
          paramJSONObject.a();
        }
        catch (JSONException localJSONException)
        {
          Appodeal.a(localJSONException);
        }
        com.appodeal.ads.f.h.a(paramJSONObject);
      }
    }
  }
  
  private String c(String paramString)
  {
    return String.format("%s_timestamp", new Object[] { paramString });
  }
  
  @ag
  private JSONObject c(SharedPreferences paramSharedPreferences)
  {
    for (;;)
    {
      try
      {
        Object localObject2;
        Object localObject1;
        if ((s == null) || (s.length() == 0))
        {
          s = new JSONObject();
          localObject2 = this.b.getPackageManager();
          paramSharedPreferences = paramSharedPreferences.getString("appKey", null);
          if (paramSharedPreferences == null) {
            return null;
          }
          s.put("app_key", paramSharedPreferences);
          s.put("android", Build.VERSION.RELEASE);
          s.put("android_level", Build.VERSION.SDK_INT);
          s.put("sdk", "2.4.3");
          String str = this.b.getPackageName();
          s.put("package", str);
          try
          {
            paramSharedPreferences = ((PackageManager)localObject2).getPackageInfo(str, 0);
            s.put("package_version", paramSharedPreferences.versionName);
            s.put("package_code", paramSharedPreferences.versionCode);
            s.put("install_time", paramSharedPreferences.firstInstallTime / 1000L);
            paramSharedPreferences = ((PackageManager)localObject2).getApplicationInfo(str, 0);
            s.put("target_sdk_version", paramSharedPreferences.targetSdkVersion);
          }
          catch (PackageManager.NameNotFoundException paramSharedPreferences)
          {
            Appodeal.a(paramSharedPreferences);
          }
          if (Appodeal.k != null) {
            s.put("framework", Appodeal.k);
          }
          if (Appodeal.l != null) {
            s.put("plugin_version", Appodeal.l);
          }
          paramSharedPreferences = bf.f(this.b);
          s.put("width", paramSharedPreferences.first);
          s.put("height", paramSharedPreferences.second);
          s.put("pxratio", bf.i(this.b));
          if (bf.m(this.b))
          {
            paramSharedPreferences = s;
            localObject1 = "tablet";
            paramSharedPreferences.put("device_type", localObject1);
          }
          else
          {
            paramSharedPreferences = s;
            localObject1 = "phone";
            continue;
          }
          if (Build.MANUFACTURER.equals("Amazon"))
          {
            paramSharedPreferences = "amazon";
            s.put("platform", paramSharedPreferences);
            try
            {
              localObject1 = ((PackageManager)localObject2).getInstallerPackageName(str);
              paramSharedPreferences = (SharedPreferences)localObject1;
              if (localObject1 == null) {
                paramSharedPreferences = "unknown";
              }
              s.put("installer", paramSharedPreferences);
            }
            catch (Exception paramSharedPreferences)
            {
              Appodeal.a(paramSharedPreferences);
            }
            s.put("manufacturer", Build.MANUFACTURER);
            s.put("rooted", bf.a());
            s.put("webview_version", bf.s(this.b));
            s.put("multidex", bf.b(new String[] { "android.support.multidex.MultiDex" }));
          }
        }
        else
        {
          paramSharedPreferences = new JSONObject();
          localObject1 = s.keys();
          if (((Iterator)localObject1).hasNext())
          {
            localObject2 = (String)((Iterator)localObject1).next();
            paramSharedPreferences.put((String)localObject2, s.get((String)localObject2));
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
  
  URL a(@af String paramString)
  {
    if (this.B)
    {
      if (this.o) {
        return bf.f(this.d);
      }
      return new URL(String.format("%s/%s", new Object[] { paramString, "get" }));
    }
    return new URL(String.format("%s/%s", new Object[] { paramString, this.d }));
  }
  
  @av
  Queue<String> a(Date paramDate)
  {
    LinkedList localLinkedList = new LinkedList();
    localLinkedList.add(i.g());
    Object localObject2 = new SimpleDateFormat("yyyy", Locale.ENGLISH).format(paramDate);
    Object localObject1 = new SimpleDateFormat("yyyy-MM", Locale.ENGLISH).format(paramDate);
    paramDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(paramDate);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("https://a.");
    localStringBuilder.append(b((String)localObject2));
    localStringBuilder.append(".com");
    localLinkedList.add(localStringBuilder.toString());
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("https://a.");
    ((StringBuilder)localObject2).append(b((String)localObject1));
    ((StringBuilder)localObject2).append(".com");
    localLinkedList.add(((StringBuilder)localObject2).toString());
    localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("https://a.");
    ((StringBuilder)localObject1).append(b(paramDate));
    ((StringBuilder)localObject1).append(".com");
    localLinkedList.add(((StringBuilder)localObject1).toString());
    return localLinkedList;
  }
  
  @ag
  JSONObject a(SharedPreferences paramSharedPreferences)
  {
    if (paramSharedPreferences == null) {
      return null;
    }
    String str1 = paramSharedPreferences.getString("appKey", null);
    if (str1 == null) {
      return null;
    }
    String str2 = az.h();
    if (az.g()) {
      paramSharedPreferences = "0";
    } else {
      paramSharedPreferences = "1";
    }
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("app_key", str1);
    localJSONObject.put("sdk", "2.4.3");
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
    localJSONObject.put("consent", az.f());
    localJSONObject.put("fingerprint", az.i());
    localJSONObject.put("adidg", az.j());
    return localJSONObject;
  }
  
  public void a()
  {
    if (this.I) {
      s.a.execute(new b(null));
    }
  }
  
  @av
  void a(SharedPreferences paramSharedPreferences, int paramInt, String paramString)
  {
    paramSharedPreferences = paramSharedPreferences.edit();
    paramSharedPreferences.putInt(d(paramString), paramInt);
    paramSharedPreferences.apply();
  }
  
  @av
  void a(SharedPreferences paramSharedPreferences, String paramString1, String paramString2)
  {
    paramSharedPreferences = paramSharedPreferences.edit();
    paramSharedPreferences.putString(paramString1, paramString2);
    paramSharedPreferences.putLong(c(paramString1), System.currentTimeMillis());
    paramSharedPreferences.apply();
  }
  
  @av
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
  
  @av
  int b(SharedPreferences paramSharedPreferences, String paramString)
  {
    return paramSharedPreferences.getInt(d(paramString), 86400000);
  }
  
  String b(String paramString)
  {
    String str = new String(i.b);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(str);
    return new BigInteger(1, bf.a(localStringBuilder.toString().getBytes())).toString(16);
  }
  
  @av
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
      if ((this.w) || (this.x) || ((this.k != null) && ((this.k.equals("video")) || (this.k.equals("rewarded_video"))))) {
        localJSONObject.put("finish", i3);
      }
      if ((this.t) || ((this.k != null) && (this.k.equals("banner"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "show" }), o.a("interstitial"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "click" }), o.b("interstitial"));
      }
      if ((this.w) || ((this.k != null) && (this.k.equals("video"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "show" }), o.a("video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "click" }), o.b("video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "finish" }), o.c("video"));
      }
      if ((this.x) || ((this.k != null) && (this.k.equals("rewarded_video"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "show" }), o.a("rewarded_video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "click" }), o.b("rewarded_video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "finish" }), o.c("rewarded_video"));
      }
      if ((this.u) || ((this.k != null) && (this.k.equals("banner_320"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "show" }), o.a("banner"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "click" }), o.b("banner"));
      }
      if ((this.v) || ((this.k != null) && (this.k.equals("banner_mrec"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "show" }), o.a("mrec"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "click" }), o.b("mrec"));
      }
      if ((this.y) || ((this.k != null) && (this.k.equals("native"))))
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
  
  @ag
  @av
  JSONObject b(SharedPreferences paramSharedPreferences)
  {
    JSONObject localJSONObject = c(paramSharedPreferences);
    if (localJSONObject == null) {
      return null;
    }
    Object localObject3 = az.h();
    Object localObject1;
    if (az.g()) {
      localObject1 = "0";
    } else {
      localObject1 = "1";
    }
    localJSONObject.put("android_id", localObject3);
    localJSONObject.put("advertising_tracking", localObject1);
    localJSONObject.put("adidg", az.j());
    try
    {
      if (this.t) {
        localJSONObject.put("type", "banner");
      }
      if (this.u)
      {
        localJSONObject.put("type", "banner_320");
        float f1 = bf.g(this.b);
        float f2 = bf.h(this.b);
        if ((l.q) && (f1 >= 728.0F) && (f2 > 720.0F)) {
          localJSONObject.put("large_banners", true);
        }
      }
      if (this.v) {
        localJSONObject.put("type", "banner_mrec");
      }
      if ((this.w) || (this.x)) {
        localJSONObject.put("type", "video");
      }
      if (this.x) {
        localJSONObject.put("rewarded_video", true);
      }
      if (this.y) {
        localJSONObject.put("type", "native");
      }
      if (this.z) {
        localJSONObject.put("debug", true);
      }
      if (k.b) {
        localJSONObject.put("test", true);
      }
      localJSONObject.put("battery", bf.k(this.b));
      localJSONObject.put("crr", bf.c(this.b));
      localJSONObject.put("locale", Locale.getDefault().toString());
      localObject1 = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.ENGLISH);
      localJSONObject.put("timezone", new SimpleDateFormat("Z", Locale.ENGLISH).format(((Calendar)localObject1).getTime()));
      localJSONObject.put("local_time", System.currentTimeMillis() / 1000L);
      localJSONObject.put("user_agent", System.getProperty("http.agent"));
      f.c(this.b);
      localJSONObject.put("session_id", f.a(paramSharedPreferences));
      localJSONObject.put("session_uptime", f.b());
      localJSONObject.put("app_uptime", f.b(paramSharedPreferences));
      localJSONObject.put("consent", az.f());
      localJSONObject.put("fingerprint", az.b());
      if (!h.h)
      {
        localObject1 = bf.b(this.b);
        localJSONObject.put("connection", ((bf.a)localObject1).a);
        localJSONObject.put("connection_subtype", ((bf.a)localObject1).b);
        localJSONObject.put("connection_fast", ((bf.a)localObject1).c);
        localObject1 = bf.d(this.b);
        localJSONObject.put("lt", ((Pair)localObject1).first);
        localJSONObject.put("lat", ((Pair)((Pair)localObject1).second).first);
        localJSONObject.put("lon", ((Pair)((Pair)localObject1).second).second);
        localJSONObject.put("model", String.format("%s %s", new Object[] { Build.MANUFACTURER, Build.MODEL }));
      }
      localJSONObject.put("coppa", h.h);
      if (this.d.equals("iap"))
      {
        localJSONObject.put("currency", this.C);
        localJSONObject.put("amount", this.D);
      }
      if (this.A)
      {
        localObject1 = new JSONArray();
        if (this.t) {
          localObject1 = new JSONArray(t.b().b());
        }
        if (this.w) {
          localObject1 = new JSONArray(au.b().b());
        }
        if (this.x) {
          localObject1 = new JSONArray(ba.b().b());
        }
        if (this.u) {
          localObject1 = new JSONArray(l.b().b());
        }
        if (this.v) {
          localObject1 = new JSONArray(ae.b().b());
        }
        if (this.y) {
          localObject1 = new JSONArray(Native.b().b());
        }
        localJSONObject.put("show_array", localObject1);
      }
      if (this.g != null) {
        localJSONObject.put("loaded_offer", this.g);
      }
      if (this.p != null) {
        localJSONObject.put("last_offer", this.p);
      }
      if ((this.l != null) && (this.l.longValue() != -1L)) {
        localJSONObject.put("segment_id", this.l);
      }
      if (this.j != 0L) {
        localJSONObject.put("show_timestamp", this.j);
      }
      if (this.d.equals("click")) {
        localJSONObject.put("click_timestamp", System.currentTimeMillis() / 1000L);
      }
      if (this.d.equals("finish")) {
        localJSONObject.put("finish_timestamp", System.currentTimeMillis() / 1000L);
      }
      if (this.m > 1) {
        localJSONObject.put("capacity", this.m);
      }
      if (this.n > 0.0D) {
        localJSONObject.put("price_floor", this.n);
      }
      if (this.q != null) {
        localJSONObject.put("ad_properties", this.q);
      }
      if (this.r != null) {
        localJSONObject.put("impid", this.r);
      }
      localJSONObject.put("id", this.e);
      localJSONObject.put("main_id", this.f);
      if ((this.A) || (this.k != null)) {
        localJSONObject.put("ad_stats", b());
      }
      if ((this.A) && (k.p == null)) {
        localJSONObject.put("check_sdk_version", true);
      }
      if (this.i != null) {
        localJSONObject.put("placement_id", this.i.b());
      }
      if ((this.E != null) && (this.d.equals("stats"))) {
        localJSONObject.put("ad_unit_stat", this.E);
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
    if ((!UserSettings.a) && (this.A) && ((localCalendar.getTimeInMillis() < System.currentTimeMillis()) || (paramSharedPreferences.getBoolean("should_update_user_settings", true))))
    {
      UserSettings.a = true;
      try
      {
        localJSONObject.put("sa", aa.a(this.b));
      }
      catch (Exception localException1)
      {
        Appodeal.a(localException1);
      }
      if (!h.h) {
        try
        {
          localJSONObject.put("user_settings", bf.t(this.b).k());
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
    if ((!k.m) && (k.l) && (this.A) && (((Calendar)localObject2).getTimeInMillis() < System.currentTimeMillis()))
    {
      k.m = true;
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
            String str2 = ((ApplicationInfo)((Iterator)localObject4).next()).packageName;
            if ((!((Pattern)localObject3).matcher(str2).matches()) && (!str2.equals("android"))) {
              ((JSONArray)localObject2).put(str2);
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
      k.m = false;
    }
    if (this.h != null)
    {
      String str1;
      if (this.d.equals("stats"))
      {
        paramSharedPreferences = "id";
        str1 = this.h.a().toString();
        localJSONObject.put(paramSharedPreferences, str1);
      }
      else if (this.d.equals("show"))
      {
        if (this.h.e())
        {
          if (!this.h.c())
          {
            Appodeal.a("/get error, rtb invalid check");
            return null;
          }
          localJSONObject.put("rtb_check", this.h.b());
        }
      }
      else
      {
        for (paramSharedPreferences = "bidder_id";; paramSharedPreferences = "id")
        {
          str1 = this.h.d();
          break;
          if (((!this.d.equals("click")) && (!this.d.equals("finish"))) || (!this.h.e())) {
            break label1766;
          }
        }
      }
    }
    label1766:
    az.a(localJSONObject);
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
        Object localObject1 = ab.this.b.getSharedPreferences("appodeal", 0);
        JSONObject localJSONObject;
        if (ab.this.d.equals("init")) {
          localJSONObject = ab.this.a((SharedPreferences)localObject1);
        } else {
          localJSONObject = ab.this.b((SharedPreferences)localObject1);
        }
        if (localJSONObject == null)
        {
          ab.a(ab.this).sendEmptyMessage(0);
          return;
        }
        SharedPreferences localSharedPreferences = ab.this.b.getSharedPreferences("Appodeal", 0);
        if (k.a == null) {
          localObject1 = ab.this;
        }
        for (Object localObject2 = i.g();; localObject2 = k.a)
        {
          localObject1 = ((ab)localObject1).a((String)localObject2);
          break;
          localObject1 = ab.this;
        }
        String str = ab.a(ab.this, (URL)localObject1, localJSONObject, localSharedPreferences, null);
        localObject1 = str;
        if (ab.this.B)
        {
          if (str == null)
          {
            localObject2 = str;
            if (localSharedPreferences.contains(ab.this.d))
            {
              localObject2 = str;
              if (ab.this.a(localSharedPreferences, ab.this.d))
              {
                Appodeal.a(new a("/get error, using saved waterfall"));
                localObject2 = localSharedPreferences.getString(ab.this.d, "");
              }
            }
          }
          else
          {
            ab.this.a(localSharedPreferences, ab.this.d, str);
            localObject2 = str;
          }
          localObject1 = localObject2;
          if (localObject2 == null)
          {
            localObject1 = ab.this.a(new Date());
            localObject2 = ab.a(ab.this, (Queue)localObject1, localJSONObject, localSharedPreferences);
            localObject1 = localObject2;
            if (localObject2 != null)
            {
              ab.this.a(localSharedPreferences, ab.this.d, (String)localObject2);
              localObject1 = localObject2;
            }
          }
        }
        if (localObject1 == null)
        {
          ab.a(ab.this).sendEmptyMessage(0);
          return;
        }
        try
        {
          localObject2 = new JSONObject((String)localObject1);
          if (ab.this.B) {
            Appodeal.a((String)localObject1, Log.LogLevel.verbose);
          } else {
            Appodeal.a((String)localObject1);
          }
          try
          {
            if (ab.this.B)
            {
              UserSettings.a(ab.this.b, ((JSONObject)localObject2).optJSONObject("user_data"));
              h.a(ab.this.b, ((JSONObject)localObject2).optJSONObject("app_data"), ((JSONObject)localObject2).optBoolean("for_kids"), ((JSONObject)localObject2).optBoolean("corona"));
              ab.this.a(localSharedPreferences, ((JSONObject)localObject2).optInt("wst", 86400000), ab.this.d);
              ab.a(ab.this, (JSONObject)localObject2);
              com.appodeal.ads.f.e.a(((JSONObject)localObject2).optJSONArray("placements"));
              com.appodeal.ads.f.e.c();
              if (Appodeal.h != null) {
                Appodeal.h.a();
              }
            }
          }
          catch (Exception localException1)
          {
            Appodeal.a(localException1);
          }
          Message localMessage = ab.a(ab.this).obtainMessage(1, localObject2);
          ab.a(ab.this).sendMessage(localMessage);
          return;
        }
        catch (JSONException localJSONException)
        {
          if (!ab.this.d.equals("stats")) {
            Appodeal.a(localJSONException);
          }
          ab.a(ab.this).sendEmptyMessage(0);
          return;
        }
        return;
      }
      catch (Exception localException2)
      {
        Appodeal.a(localException2);
        ab.a(ab.this).sendEmptyMessage(0);
      }
    }
  }
  
  public static class c
  {
    private final Context a;
    private final int b;
    private final String c;
    private ab.a d;
    private String e;
    private com.appodeal.ads.d.h f;
    private d g;
    private String h;
    private int i = 1;
    private double j = -1.0D;
    private boolean k;
    private String l;
    private double m;
    private JSONObject n;
    private g o;
    
    public c(Context paramContext, int paramInt, String paramString)
    {
      this.a = paramContext;
      this.b = paramInt;
      this.c = paramString;
    }
    
    public c a(double paramDouble)
    {
      this.j = paramDouble;
      return this;
    }
    
    public c a(double paramDouble, String paramString)
    {
      this.m = paramDouble;
      this.l = paramString;
      return this;
    }
    
    public c a(int paramInt)
    {
      this.i = paramInt;
      return this;
    }
    
    public c a(ab.a paramA)
    {
      this.d = paramA;
      return this;
    }
    
    public c a(com.appodeal.ads.d.h paramH)
    {
      this.f = paramH;
      return this;
    }
    
    public c a(d paramD)
    {
      this.g = paramD;
      return this;
    }
    
    public c a(g paramG)
    {
      this.o = paramG;
      return this;
    }
    
    public c a(String paramString)
    {
      this.e = paramString;
      return this;
    }
    
    public c a(JSONObject paramJSONObject)
    {
      this.n = paramJSONObject;
      return this;
    }
    
    public c a(boolean paramBoolean)
    {
      this.k = paramBoolean;
      return this;
    }
    
    public ab a()
    {
      return new ab(this, null);
    }
    
    public c b(String paramString)
    {
      this.h = paramString;
      return this;
    }
  }
}
