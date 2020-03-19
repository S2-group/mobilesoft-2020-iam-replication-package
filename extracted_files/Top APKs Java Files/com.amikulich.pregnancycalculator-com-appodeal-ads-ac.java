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
    if (l.b)
    {
      paramC = this.a;
      if (((paramC == null) || ((paramC instanceof ag))) && ((this.d.equals("init")) || (this.d.equals("stats")) || (this.d.equals("show")) || (this.d.equals("click")) || (this.d.equals("finish")) || (this.d.equals("install")))) {
        return;
      }
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
    //   20: ifnull +34 -> 54
    //   23: aload 7
    //   25: astore 8
    //   27: aload_1
    //   28: invokevirtual 267	java/net/URL:getProtocol	()Ljava/lang/String;
    //   31: ldc_w 269
    //   34: invokevirtual 186	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   37: ifeq +17 -> 54
    //   40: aload 7
    //   42: astore 8
    //   44: aload 7
    //   46: checkcast 271	javax/net/ssl/HttpsURLConnection
    //   49: aload 4
    //   51: invokevirtual 275	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
    //   54: aload 7
    //   56: astore 8
    //   58: aload_0
    //   59: getfield 239	com/appodeal/ads/ac:A	Z
    //   62: ifeq +51 -> 113
    //   65: aload 7
    //   67: astore 8
    //   69: aload_3
    //   70: aload_0
    //   71: getfield 85	com/appodeal/ads/ac:d	Ljava/lang/String;
    //   74: invokeinterface 281 2 0
    //   79: ifeq +34 -> 113
    //   82: sipush 10000
    //   85: istore 5
    //   87: aload 7
    //   89: astore 8
    //   91: aload 7
    //   93: sipush 10000
    //   96: invokevirtual 287	java/net/URLConnection:setConnectTimeout	(I)V
    //   99: aload 7
    //   101: astore 8
    //   103: aload 7
    //   105: iload 5
    //   107: invokevirtual 290	java/net/URLConnection:setReadTimeout	(I)V
    //   110: goto +23 -> 133
    //   113: sipush 20000
    //   116: istore 5
    //   118: aload 7
    //   120: astore 8
    //   122: aload 7
    //   124: sipush 20000
    //   127: invokevirtual 287	java/net/URLConnection:setConnectTimeout	(I)V
    //   130: goto -31 -> 99
    //   133: aload 7
    //   135: astore 8
    //   137: aload 7
    //   139: ldc_w 292
    //   142: ldc_w 294
    //   145: invokevirtual 298	java/net/URLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   148: aload 7
    //   150: astore 8
    //   152: aload 7
    //   154: iconst_1
    //   155: invokevirtual 302	java/net/URLConnection:setDoOutput	(Z)V
    //   158: aload 7
    //   160: astore 8
    //   162: new 304	java/io/ByteArrayOutputStream
    //   165: dup
    //   166: invokespecial 305	java/io/ByteArrayOutputStream:<init>	()V
    //   169: astore_1
    //   170: aload 7
    //   172: astore 8
    //   174: new 307	java/util/zip/GZIPOutputStream
    //   177: dup
    //   178: aload_1
    //   179: invokespecial 310	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   182: astore_3
    //   183: aload_3
    //   184: aload_2
    //   185: invokevirtual 315	org/json/JSONObject:toString	()Ljava/lang/String;
    //   188: ldc_w 317
    //   191: invokevirtual 321	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   194: invokevirtual 325	java/util/zip/GZIPOutputStream:write	([B)V
    //   197: aload 7
    //   199: astore 8
    //   201: aload_3
    //   202: invokevirtual 328	java/util/zip/GZIPOutputStream:close	()V
    //   205: goto +12 -> 217
    //   208: astore_2
    //   209: aload 7
    //   211: astore 8
    //   213: aload_2
    //   214: invokestatic 333	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   217: aload 7
    //   219: astore 8
    //   221: aload_1
    //   222: invokevirtual 337	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   225: iconst_0
    //   226: invokestatic 343	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
    //   229: astore_1
    //   230: aload 7
    //   232: astore 8
    //   234: aload 7
    //   236: invokevirtual 347	java/net/URLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   239: aload_1
    //   240: invokestatic 352	com/appodeal/ads/bg:a	(Ljava/io/OutputStream;Ljava/lang/String;)V
    //   243: aload 7
    //   245: astore 8
    //   247: aload 7
    //   249: invokevirtual 356	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   252: invokestatic 359	com/appodeal/ads/bg:a	(Ljava/io/InputStream;)Ljava/lang/String;
    //   255: astore_2
    //   256: aload 12
    //   258: astore_1
    //   259: aload_2
    //   260: ifnull +43 -> 303
    //   263: aload 12
    //   265: astore_1
    //   266: aload 7
    //   268: astore 8
    //   270: aload_2
    //   271: invokevirtual 363	java/lang/String:isEmpty	()Z
    //   274: ifne +29 -> 303
    //   277: aload 7
    //   279: astore 8
    //   281: aload_2
    //   282: ldc_w 365
    //   285: invokevirtual 186	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   288: istore 6
    //   290: iload 6
    //   292: ifeq +9 -> 301
    //   295: aload 12
    //   297: astore_1
    //   298: goto +5 -> 303
    //   301: aload_2
    //   302: astore_1
    //   303: aload_1
    //   304: astore_2
    //   305: aload 7
    //   307: ifnull +138 -> 445
    //   310: aload 7
    //   312: instanceof 271
    //   315: ifeq +15 -> 330
    //   318: aload_1
    //   319: astore_2
    //   320: aload 7
    //   322: checkcast 271	javax/net/ssl/HttpsURLConnection
    //   325: invokevirtual 368	javax/net/ssl/HttpsURLConnection:disconnect	()V
    //   328: aload_2
    //   329: areturn
    //   330: aload_1
    //   331: astore_2
    //   332: aload 7
    //   334: instanceof 370
    //   337: ifeq +108 -> 445
    //   340: aload_1
    //   341: astore_2
    //   342: aload 7
    //   344: checkcast 370	java/net/HttpURLConnection
    //   347: invokevirtual 371	java/net/HttpURLConnection:disconnect	()V
    //   350: aload_2
    //   351: areturn
    //   352: astore_1
    //   353: aload 7
    //   355: astore 8
    //   357: aload_3
    //   358: invokevirtual 328	java/util/zip/GZIPOutputStream:close	()V
    //   361: goto +12 -> 373
    //   364: astore_2
    //   365: aload 7
    //   367: astore 8
    //   369: aload_2
    //   370: invokestatic 333	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   373: aload 7
    //   375: astore 8
    //   377: aload_1
    //   378: athrow
    //   379: astore_2
    //   380: aload 7
    //   382: astore_1
    //   383: goto +13 -> 396
    //   386: astore_1
    //   387: aconst_null
    //   388: astore 8
    //   390: goto +58 -> 448
    //   393: astore_2
    //   394: aconst_null
    //   395: astore_1
    //   396: aload_1
    //   397: astore 8
    //   399: aload_2
    //   400: invokestatic 333	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   403: aload 11
    //   405: astore_2
    //   406: aload_1
    //   407: ifnull +38 -> 445
    //   410: aload_1
    //   411: instanceof 271
    //   414: ifeq +12 -> 426
    //   417: aload 9
    //   419: astore_2
    //   420: aload_1
    //   421: astore 7
    //   423: goto -103 -> 320
    //   426: aload 11
    //   428: astore_2
    //   429: aload_1
    //   430: instanceof 370
    //   433: ifeq +12 -> 445
    //   436: aload 10
    //   438: astore_2
    //   439: aload_1
    //   440: astore 7
    //   442: goto -100 -> 342
    //   445: aload_2
    //   446: areturn
    //   447: astore_1
    //   448: aload 8
    //   450: ifnull +38 -> 488
    //   453: aload 8
    //   455: instanceof 271
    //   458: ifne +22 -> 480
    //   461: aload 8
    //   463: instanceof 370
    //   466: ifeq +22 -> 488
    //   469: aload 8
    //   471: checkcast 370	java/net/HttpURLConnection
    //   474: invokevirtual 371	java/net/HttpURLConnection:disconnect	()V
    //   477: goto +11 -> 488
    //   480: aload 8
    //   482: checkcast 271	javax/net/ssl/HttpsURLConnection
    //   485: invokevirtual 368	javax/net/ssl/HttpsURLConnection:disconnect	()V
    //   488: aload_1
    //   489: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	490	0	this	ac
    //   0	490	1	paramURL	URL
    //   0	490	2	paramJSONObject	JSONObject
    //   0	490	3	paramSharedPreferences	SharedPreferences
    //   0	490	4	paramSSLSocketFactory	javax.net.ssl.SSLSocketFactory
    //   85	32	5	i1	int
    //   288	3	6	bool	boolean
    //   16	425	7	localObject1	Object
    //   25	456	8	localObject2	Object
    //   1	417	9	localObject3	Object
    //   4	433	10	localObject4	Object
    //   7	420	11	localObject5	Object
    //   10	286	12	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   201	205	208	java/lang/Exception
    //   183	197	352	finally
    //   357	361	364	java/lang/Exception
    //   27	40	379	java/io/IOException
    //   44	54	379	java/io/IOException
    //   58	65	379	java/io/IOException
    //   69	82	379	java/io/IOException
    //   91	99	379	java/io/IOException
    //   103	110	379	java/io/IOException
    //   122	130	379	java/io/IOException
    //   137	148	379	java/io/IOException
    //   152	158	379	java/io/IOException
    //   162	170	379	java/io/IOException
    //   174	183	379	java/io/IOException
    //   201	205	379	java/io/IOException
    //   213	217	379	java/io/IOException
    //   221	230	379	java/io/IOException
    //   234	243	379	java/io/IOException
    //   247	256	379	java/io/IOException
    //   270	277	379	java/io/IOException
    //   281	290	379	java/io/IOException
    //   357	361	379	java/io/IOException
    //   369	373	379	java/io/IOException
    //   377	379	379	java/io/IOException
    //   12	18	386	finally
    //   12	18	393	java/io/IOException
    //   27	40	447	finally
    //   44	54	447	finally
    //   58	65	447	finally
    //   69	82	447	finally
    //   91	99	447	finally
    //   103	110	447	finally
    //   122	130	447	finally
    //   137	148	447	finally
    //   152	158	447	finally
    //   162	170	447	finally
    //   174	183	447	finally
    //   201	205	447	finally
    //   213	217	447	finally
    //   221	230	447	finally
    //   234	243	447	finally
    //   247	256	447	finally
    //   270	277	447	finally
    //   281	290	447	finally
    //   357	361	447	finally
    //   369	373	447	finally
    //   377	379	447	finally
    //   399	403	447	finally
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
      l.a = str1;
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
