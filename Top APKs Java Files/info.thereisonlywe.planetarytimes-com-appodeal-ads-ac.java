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
import com.appodeal.ads.utils.ae;
import com.appodeal.ads.utils.aj;
import com.appodeal.ads.utils.c.a;
import com.appodeal.ads.utils.p;
import com.appodeal.ads.utils.v;
import com.appodeal.ads.utils.x;
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
    if ((l.b) && ((this.a == null) || ((this.a instanceof aj))) && ((this.d.equals("init")) || (this.d.equals("stats")) || (this.d.equals("show")) || (this.d.equals("click")) || (this.d.equals("finish")) || (this.d.equals("install")))) {
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
    //   16: invokestatic 270	com/google/firebase/perf/network/FirebasePerfUrlConnection:instrument	(Ljava/lang/Object;)Ljava/lang/Object;
    //   19: checkcast 272	java/net/URLConnection
    //   22: astore 7
    //   24: aload 4
    //   26: ifnull +37 -> 63
    //   29: aload 7
    //   31: astore 8
    //   33: aload_1
    //   34: invokevirtual 275	java/net/URL:getProtocol	()Ljava/lang/String;
    //   37: ldc_w 277
    //   40: invokevirtual 186	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   43: ifeq +20 -> 63
    //   46: aload 7
    //   48: astore 8
    //   50: aload 7
    //   52: checkcast 279	javax/net/ssl/HttpsURLConnection
    //   55: aload 4
    //   57: invokevirtual 283	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
    //   60: goto +3 -> 63
    //   63: aload 7
    //   65: astore 8
    //   67: aload_0
    //   68: getfield 239	com/appodeal/ads/ac:A	Z
    //   71: ifeq +51 -> 122
    //   74: aload 7
    //   76: astore 8
    //   78: aload_3
    //   79: aload_0
    //   80: getfield 85	com/appodeal/ads/ac:d	Ljava/lang/String;
    //   83: invokeinterface 289 2 0
    //   88: ifeq +34 -> 122
    //   91: sipush 10000
    //   94: istore 5
    //   96: aload 7
    //   98: astore 8
    //   100: aload 7
    //   102: sipush 10000
    //   105: invokevirtual 293	java/net/URLConnection:setConnectTimeout	(I)V
    //   108: aload 7
    //   110: astore 8
    //   112: aload 7
    //   114: iload 5
    //   116: invokevirtual 296	java/net/URLConnection:setReadTimeout	(I)V
    //   119: goto +23 -> 142
    //   122: sipush 20000
    //   125: istore 5
    //   127: aload 7
    //   129: astore 8
    //   131: aload 7
    //   133: sipush 20000
    //   136: invokevirtual 293	java/net/URLConnection:setConnectTimeout	(I)V
    //   139: goto -31 -> 108
    //   142: aload 7
    //   144: astore 8
    //   146: aload 7
    //   148: ldc_w 298
    //   151: ldc_w 300
    //   154: invokevirtual 304	java/net/URLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   157: aload 7
    //   159: astore 8
    //   161: aload 7
    //   163: iconst_1
    //   164: invokevirtual 308	java/net/URLConnection:setDoOutput	(Z)V
    //   167: aload 7
    //   169: astore 8
    //   171: new 310	java/io/ByteArrayOutputStream
    //   174: dup
    //   175: invokespecial 311	java/io/ByteArrayOutputStream:<init>	()V
    //   178: astore_1
    //   179: aload 7
    //   181: astore 8
    //   183: new 313	java/util/zip/GZIPOutputStream
    //   186: dup
    //   187: aload_1
    //   188: invokespecial 316	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   191: astore_3
    //   192: aload_3
    //   193: aload_2
    //   194: invokevirtual 321	org/json/JSONObject:toString	()Ljava/lang/String;
    //   197: ldc_w 323
    //   200: invokevirtual 327	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   203: invokevirtual 331	java/util/zip/GZIPOutputStream:write	([B)V
    //   206: aload 7
    //   208: astore 8
    //   210: aload_3
    //   211: invokevirtual 334	java/util/zip/GZIPOutputStream:close	()V
    //   214: goto +12 -> 226
    //   217: astore_2
    //   218: aload 7
    //   220: astore 8
    //   222: aload_2
    //   223: invokestatic 339	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   226: aload 7
    //   228: astore 8
    //   230: aload_1
    //   231: invokevirtual 343	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   234: iconst_0
    //   235: invokestatic 349	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
    //   238: astore_1
    //   239: aload 7
    //   241: astore 8
    //   243: aload 7
    //   245: invokevirtual 353	java/net/URLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   248: aload_1
    //   249: invokestatic 358	com/appodeal/ads/bg:a	(Ljava/io/OutputStream;Ljava/lang/String;)V
    //   252: aload 7
    //   254: astore 8
    //   256: aload 7
    //   258: invokevirtual 362	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   261: invokestatic 365	com/appodeal/ads/bg:a	(Ljava/io/InputStream;)Ljava/lang/String;
    //   264: astore_2
    //   265: aload 12
    //   267: astore_1
    //   268: aload_2
    //   269: ifnull +43 -> 312
    //   272: aload 12
    //   274: astore_1
    //   275: aload 7
    //   277: astore 8
    //   279: aload_2
    //   280: invokevirtual 369	java/lang/String:isEmpty	()Z
    //   283: ifne +29 -> 312
    //   286: aload 7
    //   288: astore 8
    //   290: aload_2
    //   291: ldc_w 371
    //   294: invokevirtual 186	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   297: istore 6
    //   299: iload 6
    //   301: ifeq +9 -> 310
    //   304: aload 12
    //   306: astore_1
    //   307: goto +5 -> 312
    //   310: aload_2
    //   311: astore_1
    //   312: aload_1
    //   313: astore_2
    //   314: aload 7
    //   316: ifnull +131 -> 447
    //   319: aload 7
    //   321: instanceof 279
    //   324: ifeq +15 -> 339
    //   327: aload_1
    //   328: astore_2
    //   329: aload 7
    //   331: checkcast 279	javax/net/ssl/HttpsURLConnection
    //   334: invokevirtual 374	javax/net/ssl/HttpsURLConnection:disconnect	()V
    //   337: aload_2
    //   338: areturn
    //   339: aload_1
    //   340: astore_2
    //   341: aload 7
    //   343: instanceof 376
    //   346: ifeq +101 -> 447
    //   349: aload_1
    //   350: astore_2
    //   351: aload 7
    //   353: checkcast 376	java/net/HttpURLConnection
    //   356: invokevirtual 377	java/net/HttpURLConnection:disconnect	()V
    //   359: aload_2
    //   360: areturn
    //   361: astore_1
    //   362: aload 7
    //   364: astore 8
    //   366: aload_3
    //   367: invokevirtual 334	java/util/zip/GZIPOutputStream:close	()V
    //   370: goto +12 -> 382
    //   373: astore_2
    //   374: aload 7
    //   376: astore 8
    //   378: aload_2
    //   379: invokestatic 339	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   382: aload 7
    //   384: astore 8
    //   386: aload_1
    //   387: athrow
    //   388: astore_1
    //   389: aconst_null
    //   390: astore 8
    //   392: goto +58 -> 450
    //   395: astore_2
    //   396: aconst_null
    //   397: astore_1
    //   398: aload_1
    //   399: astore 8
    //   401: aload_2
    //   402: invokestatic 339	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   405: aload 11
    //   407: astore_2
    //   408: aload_1
    //   409: ifnull +38 -> 447
    //   412: aload_1
    //   413: instanceof 279
    //   416: ifeq +12 -> 428
    //   419: aload 9
    //   421: astore_2
    //   422: aload_1
    //   423: astore 7
    //   425: goto -96 -> 329
    //   428: aload 11
    //   430: astore_2
    //   431: aload_1
    //   432: instanceof 376
    //   435: ifeq +12 -> 447
    //   438: aload 10
    //   440: astore_2
    //   441: aload_1
    //   442: astore 7
    //   444: goto -93 -> 351
    //   447: aload_2
    //   448: areturn
    //   449: astore_1
    //   450: aload 8
    //   452: ifnull +38 -> 490
    //   455: aload 8
    //   457: instanceof 279
    //   460: ifeq +14 -> 474
    //   463: aload 8
    //   465: checkcast 279	javax/net/ssl/HttpsURLConnection
    //   468: invokevirtual 374	javax/net/ssl/HttpsURLConnection:disconnect	()V
    //   471: goto +19 -> 490
    //   474: aload 8
    //   476: instanceof 376
    //   479: ifeq +11 -> 490
    //   482: aload 8
    //   484: checkcast 376	java/net/HttpURLConnection
    //   487: invokevirtual 377	java/net/HttpURLConnection:disconnect	()V
    //   490: aload_1
    //   491: athrow
    //   492: astore_2
    //   493: aload 7
    //   495: astore_1
    //   496: goto -98 -> 398
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	499	0	this	ac
    //   0	499	1	paramURL	URL
    //   0	499	2	paramJSONObject	JSONObject
    //   0	499	3	paramSharedPreferences	SharedPreferences
    //   0	499	4	paramSSLSocketFactory	javax.net.ssl.SSLSocketFactory
    //   94	32	5	i1	int
    //   297	3	6	bool	boolean
    //   22	472	7	localObject1	Object
    //   31	452	8	localObject2	Object
    //   1	419	9	localObject3	Object
    //   4	435	10	localObject4	Object
    //   7	422	11	localObject5	Object
    //   10	295	12	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   210	214	217	java/lang/Exception
    //   192	206	361	finally
    //   366	370	373	java/lang/Exception
    //   12	24	388	finally
    //   12	24	395	java/io/IOException
    //   33	46	449	finally
    //   50	60	449	finally
    //   67	74	449	finally
    //   78	91	449	finally
    //   100	108	449	finally
    //   112	119	449	finally
    //   131	139	449	finally
    //   146	157	449	finally
    //   161	167	449	finally
    //   171	179	449	finally
    //   183	192	449	finally
    //   210	214	449	finally
    //   222	226	449	finally
    //   230	239	449	finally
    //   243	252	449	finally
    //   256	265	449	finally
    //   279	286	449	finally
    //   290	299	449	finally
    //   366	370	449	finally
    //   378	382	449	finally
    //   386	388	449	finally
    //   401	405	449	finally
    //   33	46	492	java/io/IOException
    //   50	60	492	java/io/IOException
    //   67	74	492	java/io/IOException
    //   78	91	492	java/io/IOException
    //   100	108	492	java/io/IOException
    //   112	119	492	java/io/IOException
    //   131	139	492	java/io/IOException
    //   146	157	492	java/io/IOException
    //   161	167	492	java/io/IOException
    //   171	179	492	java/io/IOException
    //   183	192	492	java/io/IOException
    //   210	214	492	java/io/IOException
    //   222	226	492	java/io/IOException
    //   230	239	492	java/io/IOException
    //   243	252	492	java/io/IOException
    //   256	265	492	java/io/IOException
    //   279	286	492	java/io/IOException
    //   290	299	492	java/io/IOException
    //   366	370	492	java/io/IOException
    //   378	382	492	java/io/IOException
    //   386	388	492	java/io/IOException
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
          r.put("sdk", "2.4.9");
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
            r.put("multidex", bg.a(new String[] { "android.support.multidex.MultiDex" }));
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
    localJSONObject.put("sdk", "2.4.9");
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
      v.a.execute(new b(null));
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
    int i1 = p.a();
    int i2 = p.b();
    int i3 = p.c();
    try
    {
      localJSONObject.put("show", i1);
      localJSONObject.put("click", i2);
      if ((this.v) || (this.w) || ((this.j != null) && ((this.j.equals("video")) || (this.j.equals("rewarded_video"))))) {
        localJSONObject.put("finish", i3);
      }
      if ((this.s) || ((this.j != null) && (this.j.equals("banner"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "show" }), p.a("interstitial"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "click" }), p.b("interstitial"));
      }
      if ((this.v) || ((this.j != null) && (this.j.equals("video"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "show" }), p.a("video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "click" }), p.b("video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "finish" }), p.c("video"));
      }
      if ((this.w) || ((this.j != null) && (this.j.equals("rewarded_video"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "show" }), p.a("rewarded_video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "click" }), p.b("rewarded_video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "finish" }), p.c("rewarded_video"));
      }
      if ((this.t) || ((this.j != null) && (this.j.equals("banner_320"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "show" }), p.a("banner"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "click" }), p.b("banner"));
      }
      if ((this.u) || ((this.j != null) && (this.j.equals("banner_mrec"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "show" }), p.a("mrec"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "click" }), p.b("mrec"));
      }
      if ((this.x) || ((this.j != null) && (this.j.equals("native"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "native", "show" }), p.a("native"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "native", "click" }), p.b("native"));
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
        localJSONObject.put("sa", ae.a(this.b));
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
