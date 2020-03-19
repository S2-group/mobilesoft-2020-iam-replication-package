package com.appodeal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import com.appodeal.ads.e.d;
import com.appodeal.ads.e.g;
import com.appodeal.ads.utils.aa;
import com.appodeal.ads.utils.ag;
import com.appodeal.ads.utils.o;
import com.appodeal.ads.utils.t;
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
  private final int F = 0;
  private final int G = 1;
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
    String str;
    if (c.n(paramC) != null)
    {
      this.p = c.n(paramC).C;
      if ((c.n(paramC).v) && (c.n(paramC).w == null))
      {
        str = c.n(paramC).B;
        this.o = str;
        this.k = c.n(paramC).z;
        this.i = c.n(paramC).k();
        this.g = c.n(paramC).w;
        this.f = c.n(paramC).n;
        this.q = c.n(paramC).p();
      }
    }
    else
    {
      this.E = new Handler(Looper.getMainLooper())
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          if (ac.this.a != null) {}
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
      };
      if (this.d != null) {
        break label257;
      }
    }
    label257:
    while ((l.b) && ((this.a == null) || ((this.a instanceof ag))) && ((this.d.equals("init")) || (this.d.equals("stats")) || (this.d.equals("show")) || (this.d.equals("click")) || (this.d.equals("finish")) || (this.d.equals("install"))))
    {
      return;
      str = null;
      break;
    }
    if ((this.d.equals("banner")) || (this.d.equals("debug")))
    {
      bool1 = true;
      this.s = bool1;
      if ((!this.d.equals("banner_320")) && (!this.d.equals("debug_banner_320"))) {
        break label782;
      }
      bool1 = true;
      label409:
      this.t = bool1;
      if ((!this.d.equals("banner_mrec")) && (!this.d.equals("debug_mrec"))) {
        break label787;
      }
      bool1 = true;
      label440:
      this.u = bool1;
      if ((!this.d.equals("video")) && (!this.d.equals("debug_video"))) {
        break label792;
      }
      bool1 = true;
      label471:
      this.v = bool1;
      if ((!this.d.equals("rewarded_video")) && (!this.d.equals("debug_rewarded_video"))) {
        break label797;
      }
      bool1 = true;
      label502:
      this.w = bool1;
      if ((!this.d.equals("native")) && (!this.d.equals("debug_native"))) {
        break label802;
      }
      bool1 = true;
      label533:
      this.x = bool1;
      if ((!this.d.equals("debug")) && (!this.d.equals("debug_banner_320")) && (!this.d.equals("debug_video")) && (!this.d.equals("debug_rewarded_video")) && (!this.d.equals("debug_mrec")) && (!this.d.equals("debug_native"))) {
        break label807;
      }
      bool1 = true;
      label612:
      this.y = bool1;
      if ((!this.s) && (!this.t) && (!this.u) && (!this.v) && (!this.w) && (!this.x)) {
        break label812;
      }
    }
    label782:
    label787:
    label792:
    label797:
    label802:
    label807:
    label812:
    for (boolean bool1 = true;; bool1 = false)
    {
      this.z = bool1;
      bool1 = bool2;
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
      return;
      bool1 = false;
      break;
      bool1 = false;
      break label409;
      bool1 = false;
      break label440;
      bool1 = false;
      break label471;
      bool1 = false;
      break label502;
      bool1 = false;
      break label533;
      bool1 = false;
      break label612;
    }
  }
  
  /* Error */
  private String a(URL paramURL, JSONObject paramJSONObject, SharedPreferences paramSharedPreferences, javax.net.ssl.SSLSocketFactory paramSSLSocketFactory)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 264	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   4: astore 6
    //   6: aload 4
    //   8: ifnull +26 -> 34
    //   11: aload_1
    //   12: invokevirtual 267	java/net/URL:getProtocol	()Ljava/lang/String;
    //   15: ldc_w 269
    //   18: invokevirtual 186	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   21: ifeq +13 -> 34
    //   24: aload 6
    //   26: checkcast 271	javax/net/ssl/HttpsURLConnection
    //   29: aload 4
    //   31: invokevirtual 275	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
    //   34: aload_0
    //   35: getfield 239	com/appodeal/ads/ac:A	Z
    //   38: ifeq +161 -> 199
    //   41: aload_3
    //   42: aload_0
    //   43: getfield 85	com/appodeal/ads/ac:d	Ljava/lang/String;
    //   46: invokeinterface 281 2 0
    //   51: ifeq +148 -> 199
    //   54: aload 6
    //   56: sipush 10000
    //   59: invokevirtual 287	java/net/URLConnection:setConnectTimeout	(I)V
    //   62: aload 6
    //   64: sipush 10000
    //   67: invokevirtual 290	java/net/URLConnection:setReadTimeout	(I)V
    //   70: aload 6
    //   72: ldc_w 292
    //   75: ldc_w 294
    //   78: invokevirtual 298	java/net/URLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   81: aload 6
    //   83: iconst_1
    //   84: invokevirtual 302	java/net/URLConnection:setDoOutput	(Z)V
    //   87: new 304	java/io/ByteArrayOutputStream
    //   90: dup
    //   91: invokespecial 305	java/io/ByteArrayOutputStream:<init>	()V
    //   94: astore_1
    //   95: new 307	java/util/zip/GZIPOutputStream
    //   98: dup
    //   99: aload_1
    //   100: invokespecial 310	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   103: astore_3
    //   104: aload_3
    //   105: aload_2
    //   106: invokevirtual 315	org/json/JSONObject:toString	()Ljava/lang/String;
    //   109: ldc_w 317
    //   112: invokevirtual 321	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   115: invokevirtual 325	java/util/zip/GZIPOutputStream:write	([B)V
    //   118: aload_3
    //   119: invokevirtual 328	java/util/zip/GZIPOutputStream:close	()V
    //   122: aload_1
    //   123: invokevirtual 332	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   126: iconst_0
    //   127: invokestatic 338	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
    //   130: astore_1
    //   131: aload 6
    //   133: invokevirtual 342	java/net/URLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   136: aload_1
    //   137: invokestatic 347	com/appodeal/ads/bg:a	(Ljava/io/OutputStream;Ljava/lang/String;)V
    //   140: aload 6
    //   142: invokevirtual 351	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
    //   145: invokestatic 354	com/appodeal/ads/bg:a	(Ljava/io/InputStream;)Ljava/lang/String;
    //   148: astore_1
    //   149: aload_1
    //   150: ifnull +24 -> 174
    //   153: aload_1
    //   154: invokevirtual 358	java/lang/String:isEmpty	()Z
    //   157: ifne +17 -> 174
    //   160: aload_1
    //   161: ldc_w 360
    //   164: invokevirtual 186	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   167: istore 5
    //   169: iload 5
    //   171: ifeq +5 -> 176
    //   174: aconst_null
    //   175: astore_1
    //   176: aload 6
    //   178: ifnull +19 -> 197
    //   181: aload 6
    //   183: instanceof 271
    //   186: ifeq +107 -> 293
    //   189: aload 6
    //   191: checkcast 271	javax/net/ssl/HttpsURLConnection
    //   194: invokevirtual 363	javax/net/ssl/HttpsURLConnection:disconnect	()V
    //   197: aload_1
    //   198: areturn
    //   199: aload 6
    //   201: sipush 20000
    //   204: invokevirtual 287	java/net/URLConnection:setConnectTimeout	(I)V
    //   207: aload 6
    //   209: sipush 20000
    //   212: invokevirtual 290	java/net/URLConnection:setReadTimeout	(I)V
    //   215: goto -145 -> 70
    //   218: astore_2
    //   219: aload 6
    //   221: astore_1
    //   222: aload_2
    //   223: invokestatic 368	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   226: aload_1
    //   227: ifnull +133 -> 360
    //   230: aload_1
    //   231: instanceof 271
    //   234: ifeq +77 -> 311
    //   237: aload_1
    //   238: checkcast 271	javax/net/ssl/HttpsURLConnection
    //   241: invokevirtual 363	javax/net/ssl/HttpsURLConnection:disconnect	()V
    //   244: aconst_null
    //   245: areturn
    //   246: astore_2
    //   247: aload_2
    //   248: invokestatic 368	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   251: goto -129 -> 122
    //   254: astore_2
    //   255: aload 6
    //   257: astore_1
    //   258: aload_1
    //   259: ifnull +17 -> 276
    //   262: aload_1
    //   263: instanceof 271
    //   266: ifeq +61 -> 327
    //   269: aload_1
    //   270: checkcast 271	javax/net/ssl/HttpsURLConnection
    //   273: invokevirtual 363	javax/net/ssl/HttpsURLConnection:disconnect	()V
    //   276: aload_2
    //   277: athrow
    //   278: astore_1
    //   279: aload_3
    //   280: invokevirtual 328	java/util/zip/GZIPOutputStream:close	()V
    //   283: aload_1
    //   284: athrow
    //   285: astore_2
    //   286: aload_2
    //   287: invokestatic 368	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   290: goto -7 -> 283
    //   293: aload 6
    //   295: instanceof 370
    //   298: ifeq -101 -> 197
    //   301: aload 6
    //   303: checkcast 370	java/net/HttpURLConnection
    //   306: invokevirtual 371	java/net/HttpURLConnection:disconnect	()V
    //   309: aload_1
    //   310: areturn
    //   311: aload_1
    //   312: instanceof 370
    //   315: ifeq +45 -> 360
    //   318: aload_1
    //   319: checkcast 370	java/net/HttpURLConnection
    //   322: invokevirtual 371	java/net/HttpURLConnection:disconnect	()V
    //   325: aconst_null
    //   326: areturn
    //   327: aload_1
    //   328: instanceof 370
    //   331: ifeq -55 -> 276
    //   334: aload_1
    //   335: checkcast 370	java/net/HttpURLConnection
    //   338: invokevirtual 371	java/net/HttpURLConnection:disconnect	()V
    //   341: goto -65 -> 276
    //   344: astore_2
    //   345: aconst_null
    //   346: astore_1
    //   347: goto -89 -> 258
    //   350: astore_2
    //   351: goto -93 -> 258
    //   354: astore_2
    //   355: aconst_null
    //   356: astore_1
    //   357: goto -135 -> 222
    //   360: aconst_null
    //   361: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	362	0	this	ac
    //   0	362	1	paramURL	URL
    //   0	362	2	paramJSONObject	JSONObject
    //   0	362	3	paramSharedPreferences	SharedPreferences
    //   0	362	4	paramSSLSocketFactory	javax.net.ssl.SSLSocketFactory
    //   167	3	5	bool	boolean
    //   4	298	6	localURLConnection	java.net.URLConnection
    // Exception table:
    //   from	to	target	type
    //   11	34	218	java/io/IOException
    //   34	70	218	java/io/IOException
    //   70	104	218	java/io/IOException
    //   118	122	218	java/io/IOException
    //   122	149	218	java/io/IOException
    //   153	169	218	java/io/IOException
    //   199	215	218	java/io/IOException
    //   247	251	218	java/io/IOException
    //   279	283	218	java/io/IOException
    //   283	285	218	java/io/IOException
    //   286	290	218	java/io/IOException
    //   118	122	246	java/lang/Exception
    //   11	34	254	finally
    //   34	70	254	finally
    //   70	104	254	finally
    //   118	122	254	finally
    //   122	149	254	finally
    //   153	169	254	finally
    //   199	215	254	finally
    //   247	251	254	finally
    //   279	283	254	finally
    //   283	285	254	finally
    //   286	290	254	finally
    //   104	118	278	finally
    //   279	283	285	java/lang/Exception
    //   0	6	344	finally
    //   222	226	350	finally
    //   0	6	354	java/io/IOException
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
    if ((localJSONArray == null) || (localJSONArray.length() == 0)) {}
    do
    {
      return;
      paramJSONObject = new com.appodeal.ads.e.h(this.b, paramJSONObject.optJSONObject("user_data"));
      paramJSONObject.b(localJSONArray);
      paramJSONObject = paramJSONObject.a(localJSONArray);
      if (paramJSONObject == null)
      {
        com.appodeal.ads.e.h.c();
        return;
      }
    } while (paramJSONObject.c() == com.appodeal.ads.e.h.a().c());
    try
    {
      paramJSONObject.a();
      com.appodeal.ads.e.h.a(paramJSONObject);
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;)
      {
        Appodeal.a(localJSONException);
      }
    }
  }
  
  private String c(String paramString)
  {
    return String.format("%s_timestamp", new Object[] { paramString });
  }
  
  /* Error */
  private JSONObject c(SharedPreferences paramSharedPreferences)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   6: ifnull +12 -> 18
    //   9: getstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   12: invokevirtual 447	org/json/JSONObject:length	()I
    //   15: ifne +419 -> 434
    //   18: new 312	org/json/JSONObject
    //   21: dup
    //   22: invokespecial 448	org/json/JSONObject:<init>	()V
    //   25: putstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   28: aload_0
    //   29: getfield 75	com/appodeal/ads/ac:b	Landroid/content/Context;
    //   32: invokevirtual 454	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   35: astore_2
    //   36: aload_1
    //   37: ldc_w 456
    //   40: aconst_null
    //   41: invokeinterface 460 3 0
    //   46: astore_1
    //   47: aload_1
    //   48: ifnonnull +8 -> 56
    //   51: ldc 2
    //   53: monitorexit
    //   54: aconst_null
    //   55: areturn
    //   56: getstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   59: ldc_w 462
    //   62: aload_1
    //   63: invokevirtual 466	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   66: pop
    //   67: getstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   70: ldc_w 468
    //   73: getstatic 473	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   76: invokevirtual 466	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   79: pop
    //   80: getstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   83: ldc_w 475
    //   86: getstatic 478	android/os/Build$VERSION:SDK_INT	I
    //   89: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   92: pop
    //   93: getstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   96: ldc_w 483
    //   99: ldc_w 485
    //   102: invokevirtual 466	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   105: pop
    //   106: aload_0
    //   107: getfield 75	com/appodeal/ads/ac:b	Landroid/content/Context;
    //   110: invokevirtual 488	android/content/Context:getPackageName	()Ljava/lang/String;
    //   113: astore_3
    //   114: getstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   117: ldc_w 490
    //   120: aload_3
    //   121: invokevirtual 466	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   124: pop
    //   125: aload_2
    //   126: aload_3
    //   127: iconst_0
    //   128: invokevirtual 496	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   131: astore_1
    //   132: getstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   135: ldc_w 498
    //   138: aload_1
    //   139: getfield 503	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   142: invokevirtual 466	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   145: pop
    //   146: getstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   149: ldc_w 505
    //   152: aload_1
    //   153: getfield 508	android/content/pm/PackageInfo:versionCode	I
    //   156: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   159: pop
    //   160: getstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   163: ldc_w 510
    //   166: aload_1
    //   167: getfield 513	android/content/pm/PackageInfo:firstInstallTime	J
    //   170: ldc2_w 514
    //   173: ldiv
    //   174: invokevirtual 518	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   177: pop
    //   178: aload_2
    //   179: aload_3
    //   180: iconst_0
    //   181: invokevirtual 522	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   184: astore_1
    //   185: getstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   188: ldc_w 524
    //   191: aload_1
    //   192: getfield 529	android/content/pm/ApplicationInfo:targetSdkVersion	I
    //   195: invokevirtual 481	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   198: pop
    //   199: getstatic 531	com/appodeal/ads/Appodeal:k	Ljava/lang/String;
    //   202: ifnull +16 -> 218
    //   205: getstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   208: ldc_w 533
    //   211: getstatic 531	com/appodeal/ads/Appodeal:k	Ljava/lang/String;
    //   214: invokevirtual 466	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   217: pop
    //   218: getstatic 535	com/appodeal/ads/Appodeal:l	Ljava/lang/String;
    //   221: ifnull +16 -> 237
    //   224: getstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   227: ldc_w 537
    //   230: getstatic 535	com/appodeal/ads/Appodeal:l	Ljava/lang/String;
    //   233: invokevirtual 466	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   236: pop
    //   237: aload_0
    //   238: getfield 75	com/appodeal/ads/ac:b	Landroid/content/Context;
    //   241: invokestatic 540	com/appodeal/ads/bg:f	(Landroid/content/Context;)Landroid/util/Pair;
    //   244: astore_1
    //   245: getstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   248: ldc_w 542
    //   251: aload_1
    //   252: getfield 548	android/util/Pair:first	Ljava/lang/Object;
    //   255: invokevirtual 466	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   258: pop
    //   259: getstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   262: ldc_w 550
    //   265: aload_1
    //   266: getfield 553	android/util/Pair:second	Ljava/lang/Object;
    //   269: invokevirtual 466	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   272: pop
    //   273: getstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   276: ldc_w 555
    //   279: aload_0
    //   280: getfield 75	com/appodeal/ads/ac:b	Landroid/content/Context;
    //   283: invokestatic 558	com/appodeal/ads/bg:i	(Landroid/content/Context;)F
    //   286: f2d
    //   287: invokevirtual 561	org/json/JSONObject:put	(Ljava/lang/String;D)Lorg/json/JSONObject;
    //   290: pop
    //   291: aload_0
    //   292: getfield 75	com/appodeal/ads/ac:b	Landroid/content/Context;
    //   295: invokestatic 564	com/appodeal/ads/bg:m	(Landroid/content/Context;)Z
    //   298: ifeq +200 -> 498
    //   301: getstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   304: ldc_w 566
    //   307: ldc_w 568
    //   310: invokevirtual 466	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   313: pop
    //   314: getstatic 573	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   317: ldc_w 575
    //   320: invokevirtual 186	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   323: ifeq +204 -> 527
    //   326: ldc_w 577
    //   329: astore_1
    //   330: getstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   333: ldc_w 579
    //   336: aload_1
    //   337: invokevirtual 466	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   340: pop
    //   341: aload_2
    //   342: aload_3
    //   343: invokevirtual 582	android/content/pm/PackageManager:getInstallerPackageName	(Ljava/lang/String;)Ljava/lang/String;
    //   346: astore_2
    //   347: aload_2
    //   348: astore_1
    //   349: aload_2
    //   350: ifnonnull +7 -> 357
    //   353: ldc_w 584
    //   356: astore_1
    //   357: getstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   360: ldc_w 586
    //   363: aload_1
    //   364: invokevirtual 466	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   367: pop
    //   368: getstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   371: ldc_w 588
    //   374: getstatic 573	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   377: invokevirtual 466	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   380: pop
    //   381: getstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   384: ldc_w 590
    //   387: invokestatic 592	com/appodeal/ads/bg:a	()Z
    //   390: invokevirtual 595	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   393: pop
    //   394: getstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   397: ldc_w 597
    //   400: aload_0
    //   401: getfield 75	com/appodeal/ads/ac:b	Landroid/content/Context;
    //   404: invokestatic 600	com/appodeal/ads/bg:s	(Landroid/content/Context;)Ljava/lang/String;
    //   407: invokevirtual 466	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   410: pop
    //   411: getstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   414: ldc_w 602
    //   417: iconst_1
    //   418: anewarray 182	java/lang/String
    //   421: dup
    //   422: iconst_0
    //   423: ldc_w 604
    //   426: aastore
    //   427: invokestatic 607	com/appodeal/ads/bg:a	([Ljava/lang/String;)Z
    //   430: invokevirtual 595	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   433: pop
    //   434: new 312	org/json/JSONObject
    //   437: dup
    //   438: invokespecial 448	org/json/JSONObject:<init>	()V
    //   441: astore_1
    //   442: getstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   445: invokevirtual 611	org/json/JSONObject:keys	()Ljava/util/Iterator;
    //   448: astore_2
    //   449: aload_2
    //   450: invokeinterface 616 1 0
    //   455: ifeq +67 -> 522
    //   458: aload_2
    //   459: invokeinterface 619 1 0
    //   464: checkcast 182	java/lang/String
    //   467: astore_3
    //   468: aload_1
    //   469: aload_3
    //   470: getstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   473: aload_3
    //   474: invokevirtual 623	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   477: invokevirtual 466	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   480: pop
    //   481: goto -32 -> 449
    //   484: astore_1
    //   485: ldc 2
    //   487: monitorexit
    //   488: aload_1
    //   489: athrow
    //   490: astore_1
    //   491: aload_1
    //   492: invokestatic 368	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   495: goto -296 -> 199
    //   498: getstatic 446	com/appodeal/ads/ac:r	Lorg/json/JSONObject;
    //   501: ldc_w 566
    //   504: ldc_w 625
    //   507: invokevirtual 466	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   510: pop
    //   511: goto -197 -> 314
    //   514: astore_1
    //   515: aload_1
    //   516: invokestatic 368	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   519: goto -151 -> 368
    //   522: ldc 2
    //   524: monitorexit
    //   525: aload_1
    //   526: areturn
    //   527: ldc_w 627
    //   530: astore_1
    //   531: goto -201 -> 330
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	534	0	this	ac
    //   0	534	1	paramSharedPreferences	SharedPreferences
    //   35	424	2	localObject	Object
    //   113	361	3	str	String
    // Exception table:
    //   from	to	target	type
    //   3	18	484	finally
    //   18	47	484	finally
    //   51	54	484	finally
    //   56	125	484	finally
    //   125	199	484	finally
    //   199	218	484	finally
    //   218	237	484	finally
    //   237	314	484	finally
    //   314	326	484	finally
    //   330	341	484	finally
    //   341	347	484	finally
    //   357	368	484	finally
    //   368	434	484	finally
    //   434	449	484	finally
    //   449	481	484	finally
    //   485	488	484	finally
    //   491	495	484	finally
    //   498	511	484	finally
    //   515	519	484	finally
    //   522	525	484	finally
    //   125	199	490	android/content/pm/PackageManager$NameNotFoundException
    //   341	347	514	java/lang/Exception
    //   357	368	514	java/lang/Exception
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
    String str1 = new SimpleDateFormat("yyyy", Locale.ENGLISH).format(paramDate);
    String str2 = new SimpleDateFormat("yyyy-MM", Locale.ENGLISH).format(paramDate);
    paramDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(paramDate);
    localLinkedList.add("https://a." + b(str1) + ".com");
    localLinkedList.add("https://a." + b(str2) + ".com");
    localLinkedList.add("https://a." + b(paramDate) + ".com");
    return localLinkedList;
  }
  
  JSONObject a(SharedPreferences paramSharedPreferences)
  {
    if (paramSharedPreferences == null) {}
    String str1;
    do
    {
      return null;
      str1 = paramSharedPreferences.getString("appKey", null);
    } while (str1 == null);
    String str2 = ba.h();
    JSONObject localJSONObject;
    if (ba.g())
    {
      paramSharedPreferences = "0";
      localJSONObject = new JSONObject();
      localJSONObject.put("app_key", str1);
      localJSONObject.put("sdk", "2.4.6");
      localJSONObject.put("package", this.b.getPackageName());
      localJSONObject.put("framework", Appodeal.k);
      localJSONObject.put("android_id", str2);
      localJSONObject.put("advertising_tracking", paramSharedPreferences);
      if (!Build.MANUFACTURER.equals("Amazon")) {
        break label176;
      }
    }
    label176:
    for (paramSharedPreferences = "amazon";; paramSharedPreferences = "google")
    {
      localJSONObject.put("platform", paramSharedPreferences);
      localJSONObject.put("consent", ba.f());
      localJSONObject.put("fingerprint", ba.i());
      localJSONObject.put("adidg", ba.j());
      return localJSONObject;
      paramSharedPreferences = "1";
      break;
    }
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
    return new BigInteger(1, bg.a((paramString + str).getBytes())).toString(16);
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
      }
      return localJSONObject;
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
    Object localObject2 = ba.h();
    Object localObject1;
    if (ba.g()) {
      localObject1 = "0";
    }
    for (;;)
    {
      localJSONObject.put("android_id", localObject2);
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
        localObject1 = Calendar.getInstance();
        ((Calendar)localObject1).setTimeInMillis(paramSharedPreferences.getLong("lastSettingsTime", 0L));
        ((Calendar)localObject1).add(5, 1);
        if ((!UserSettings.a) && (this.z) && ((((Calendar)localObject1).getTimeInMillis() < System.currentTimeMillis()) || (paramSharedPreferences.getBoolean("should_update_user_settings", true)))) {
          UserSettings.a = true;
        }
      }
      catch (JSONException localException2)
      {
        try
        {
          localJSONObject.put("sa", com.appodeal.ads.utils.ac.a(this.b));
          if (i.h) {}
        }
        catch (Exception localException2)
        {
          try
          {
            for (;;)
            {
              localJSONObject.put("user_settings", bg.t(this.b).h());
              localObject1 = paramSharedPreferences.edit();
              ((SharedPreferences.Editor)localObject1).putLong("lastSettingsTime", System.currentTimeMillis());
              ((SharedPreferences.Editor)localObject1).putBoolean("should_update_user_settings", false);
              ((SharedPreferences.Editor)localObject1).apply();
              UserSettings.a = false;
              localObject1 = Calendar.getInstance();
              ((Calendar)localObject1).setTimeInMillis(paramSharedPreferences.getLong("lastAppTime", 0L));
              ((Calendar)localObject1).add(5, 1);
              if ((!l.i) && (l.h) && (this.z) && (((Calendar)localObject1).getTimeInMillis() < System.currentTimeMillis()))
              {
                l.i = true;
                try
                {
                  localObject1 = new JSONArray();
                  Object localObject3 = this.b.getPackageManager().getInstalledApplications(0);
                  localObject2 = Pattern.compile("^?(?:com\\.android|com\\.google|com\\.sec|com\\.samsung|com\\.sonyericsson|com\\.sonymobile|com\\.motorola|com\\.htc).*$");
                  if (localObject3 == null) {
                    break label1482;
                  }
                  localObject3 = ((List)localObject3).iterator();
                  while (((Iterator)localObject3).hasNext())
                  {
                    String str2 = ((ApplicationInfo)((Iterator)localObject3).next()).packageName;
                    if ((!((Pattern)localObject2).matcher(str2).matches()) && (!str2.equals("android"))) {
                      ((JSONArray)localObject1).put(str2);
                    }
                  }
                  paramSharedPreferences = paramSharedPreferences.edit();
                }
                catch (Exception localException1)
                {
                  Appodeal.a(localException1);
                }
                paramSharedPreferences.putLong("lastAppTime", System.currentTimeMillis());
                paramSharedPreferences.apply();
                l.i = false;
              }
              ba.a(localJSONObject);
              return localJSONObject;
              String str1 = "1";
              break;
              localJSONException = localJSONException;
              Appodeal.a(localJSONException);
            }
            localException2 = localException2;
            Appodeal.a(localException2);
          }
          catch (Exception localException3)
          {
            for (;;)
            {
              Appodeal.a(localException3);
              continue;
              label1482:
              localJSONObject.put("apps", localException3);
            }
          }
        }
      }
    }
  }
  
  public static abstract interface a
  {
    public abstract void a(int paramInt);
    
    public abstract void a(JSONObject paramJSONObject, int paramInt, String paramString);
  }
  
  class b
    implements Runnable
  {
    private b() {}
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   4: getfield 30	com/appodeal/ads/ac:b	Landroid/content/Context;
      //   7: ldc 32
      //   9: iconst_0
      //   10: invokevirtual 38	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   13: astore_1
      //   14: aload_0
      //   15: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   18: getfield 42	com/appodeal/ads/ac:d	Ljava/lang/String;
      //   21: ldc 44
      //   23: invokevirtual 50	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   26: ifeq +29 -> 55
      //   29: aload_0
      //   30: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   33: aload_1
      //   34: invokevirtual 53	com/appodeal/ads/ac:a	(Landroid/content/SharedPreferences;)Lorg/json/JSONObject;
      //   37: astore_3
      //   38: aload_3
      //   39: ifnonnull +28 -> 67
      //   42: aload_0
      //   43: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   46: invokestatic 56	com/appodeal/ads/ac:a	(Lcom/appodeal/ads/ac;)Landroid/os/Handler;
      //   49: iconst_0
      //   50: invokevirtual 62	android/os/Handler:sendEmptyMessage	(I)Z
      //   53: pop
      //   54: return
      //   55: aload_0
      //   56: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   59: aload_1
      //   60: invokevirtual 64	com/appodeal/ads/ac:b	(Landroid/content/SharedPreferences;)Lorg/json/JSONObject;
      //   63: astore_3
      //   64: goto -26 -> 38
      //   67: aload_0
      //   68: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   71: getfield 30	com/appodeal/ads/ac:b	Landroid/content/Context;
      //   74: ldc 66
      //   76: iconst_0
      //   77: invokevirtual 38	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   80: astore 5
      //   82: getstatic 70	com/appodeal/ads/l:a	Ljava/lang/String;
      //   85: ifnonnull +210 -> 295
      //   88: aload_0
      //   89: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   92: invokestatic 76	com/appodeal/ads/utils/i:g	()Ljava/lang/String;
      //   95: invokevirtual 79	com/appodeal/ads/ac:a	(Ljava/lang/String;)Ljava/net/URL;
      //   98: astore_1
      //   99: aload_0
      //   100: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   103: aload_1
      //   104: aload_3
      //   105: aload 5
      //   107: aconst_null
      //   108: invokestatic 82	com/appodeal/ads/ac:a	(Lcom/appodeal/ads/ac;Ljava/net/URL;Lorg/json/JSONObject;Landroid/content/SharedPreferences;Ljavax/net/ssl/SSLSocketFactory;)Ljava/lang/String;
      //   111: astore 4
      //   113: aload 4
      //   115: astore_1
      //   116: aload_0
      //   117: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   120: getfield 86	com/appodeal/ads/ac:A	Z
      //   123: ifeq +137 -> 260
      //   126: aload 4
      //   128: ifnonnull +181 -> 309
      //   131: aload 4
      //   133: astore_2
      //   134: aload 5
      //   136: aload_0
      //   137: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   140: getfield 42	com/appodeal/ads/ac:d	Ljava/lang/String;
      //   143: invokeinterface 92 2 0
      //   148: ifeq +54 -> 202
      //   151: aload 4
      //   153: astore_2
      //   154: aload_0
      //   155: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   158: aload 5
      //   160: aload_0
      //   161: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   164: getfield 42	com/appodeal/ads/ac:d	Ljava/lang/String;
      //   167: invokevirtual 95	com/appodeal/ads/ac:a	(Landroid/content/SharedPreferences;Ljava/lang/String;)Z
      //   170: ifeq +32 -> 202
      //   173: new 97	com/appodeal/ads/utils/c/a
      //   176: dup
      //   177: ldc 99
      //   179: invokespecial 102	com/appodeal/ads/utils/c/a:<init>	(Ljava/lang/String;)V
      //   182: invokestatic 107	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   185: aload 5
      //   187: aload_0
      //   188: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   191: getfield 42	com/appodeal/ads/ac:d	Ljava/lang/String;
      //   194: ldc 109
      //   196: invokeinterface 113 3 0
      //   201: astore_2
      //   202: aload_2
      //   203: astore_1
      //   204: aload_2
      //   205: ifnonnull +55 -> 260
      //   208: aload_0
      //   209: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   212: new 115	java/util/Date
      //   215: dup
      //   216: invokespecial 116	java/util/Date:<init>	()V
      //   219: invokevirtual 119	com/appodeal/ads/ac:a	(Ljava/util/Date;)Ljava/util/Queue;
      //   222: astore_1
      //   223: aload_0
      //   224: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   227: aload_1
      //   228: aload_3
      //   229: aload 5
      //   231: invokestatic 122	com/appodeal/ads/ac:a	(Lcom/appodeal/ads/ac;Ljava/util/Queue;Lorg/json/JSONObject;Landroid/content/SharedPreferences;)Ljava/lang/String;
      //   234: astore_2
      //   235: aload_2
      //   236: astore_1
      //   237: aload_2
      //   238: ifnull +22 -> 260
      //   241: aload_0
      //   242: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   245: aload 5
      //   247: aload_0
      //   248: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   251: getfield 42	com/appodeal/ads/ac:d	Ljava/lang/String;
      //   254: aload_2
      //   255: invokevirtual 125	com/appodeal/ads/ac:a	(Landroid/content/SharedPreferences;Ljava/lang/String;Ljava/lang/String;)V
      //   258: aload_2
      //   259: astore_1
      //   260: aload_1
      //   261: ifnonnull +72 -> 333
      //   264: aload_0
      //   265: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   268: invokestatic 56	com/appodeal/ads/ac:a	(Lcom/appodeal/ads/ac;)Landroid/os/Handler;
      //   271: iconst_0
      //   272: invokevirtual 62	android/os/Handler:sendEmptyMessage	(I)Z
      //   275: pop
      //   276: return
      //   277: astore_1
      //   278: aload_1
      //   279: invokestatic 107	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   282: aload_0
      //   283: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   286: invokestatic 56	com/appodeal/ads/ac:a	(Lcom/appodeal/ads/ac;)Landroid/os/Handler;
      //   289: iconst_0
      //   290: invokevirtual 62	android/os/Handler:sendEmptyMessage	(I)Z
      //   293: pop
      //   294: return
      //   295: aload_0
      //   296: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   299: getstatic 70	com/appodeal/ads/l:a	Ljava/lang/String;
      //   302: invokevirtual 79	com/appodeal/ads/ac:a	(Ljava/lang/String;)Ljava/net/URL;
      //   305: astore_1
      //   306: goto -207 -> 99
      //   309: aload_0
      //   310: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   313: aload 5
      //   315: aload_0
      //   316: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   319: getfield 42	com/appodeal/ads/ac:d	Ljava/lang/String;
      //   322: aload 4
      //   324: invokevirtual 125	com/appodeal/ads/ac:a	(Landroid/content/SharedPreferences;Ljava/lang/String;Ljava/lang/String;)V
      //   327: aload 4
      //   329: astore_2
      //   330: goto -128 -> 202
      //   333: new 127	org/json/JSONObject
      //   336: dup
      //   337: aload_1
      //   338: invokespecial 128	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   341: astore_2
      //   342: aload_0
      //   343: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   346: getfield 86	com/appodeal/ads/ac:A	Z
      //   349: ifeq +148 -> 497
      //   352: aload_1
      //   353: getstatic 134	com/appodeal/ads/utils/Log$LogLevel:verbose	Lcom/appodeal/ads/utils/Log$LogLevel;
      //   356: invokestatic 137	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;Lcom/appodeal/ads/utils/Log$LogLevel;)V
      //   359: aload_0
      //   360: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   363: getfield 86	com/appodeal/ads/ac:A	Z
      //   366: ifeq +105 -> 471
      //   369: aload_0
      //   370: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   373: getfield 30	com/appodeal/ads/ac:b	Landroid/content/Context;
      //   376: aload_2
      //   377: ldc -117
      //   379: invokevirtual 143	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   382: invokestatic 148	com/appodeal/ads/UserSettings:a	(Landroid/content/Context;Lorg/json/JSONObject;)V
      //   385: aload_0
      //   386: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   389: getfield 30	com/appodeal/ads/ac:b	Landroid/content/Context;
      //   392: aload_2
      //   393: ldc -106
      //   395: invokevirtual 143	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   398: aload_2
      //   399: ldc -104
      //   401: invokevirtual 155	org/json/JSONObject:optBoolean	(Ljava/lang/String;)Z
      //   404: aload_2
      //   405: ldc -99
      //   407: invokevirtual 155	org/json/JSONObject:optBoolean	(Ljava/lang/String;)Z
      //   410: invokestatic 162	com/appodeal/ads/i:a	(Landroid/content/Context;Lorg/json/JSONObject;ZZ)V
      //   413: aload_0
      //   414: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   417: aload 5
      //   419: aload_2
      //   420: ldc -92
      //   422: ldc -91
      //   424: invokevirtual 169	org/json/JSONObject:optInt	(Ljava/lang/String;I)I
      //   427: aload_0
      //   428: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   431: getfield 42	com/appodeal/ads/ac:d	Ljava/lang/String;
      //   434: invokevirtual 172	com/appodeal/ads/ac:a	(Landroid/content/SharedPreferences;ILjava/lang/String;)V
      //   437: aload_0
      //   438: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   441: aload_2
      //   442: invokestatic 175	com/appodeal/ads/ac:a	(Lcom/appodeal/ads/ac;Lorg/json/JSONObject;)V
      //   445: aload_2
      //   446: ldc -79
      //   448: invokevirtual 181	org/json/JSONObject:optJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   451: invokestatic 186	com/appodeal/ads/e/e:a	(Lorg/json/JSONArray;)V
      //   454: invokestatic 189	com/appodeal/ads/e/e:c	()V
      //   457: getstatic 193	com/appodeal/ads/Appodeal:h	Lcom/appodeal/ads/utils/v;
      //   460: ifnull +11 -> 471
      //   463: getstatic 193	com/appodeal/ads/Appodeal:h	Lcom/appodeal/ads/utils/v;
      //   466: invokeinterface 197 1 0
      //   471: aload_0
      //   472: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   475: invokestatic 56	com/appodeal/ads/ac:a	(Lcom/appodeal/ads/ac;)Landroid/os/Handler;
      //   478: iconst_1
      //   479: aload_2
      //   480: invokevirtual 201	android/os/Handler:obtainMessage	(ILjava/lang/Object;)Landroid/os/Message;
      //   483: astore_1
      //   484: aload_0
      //   485: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   488: invokestatic 56	com/appodeal/ads/ac:a	(Lcom/appodeal/ads/ac;)Landroid/os/Handler;
      //   491: aload_1
      //   492: invokevirtual 205	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   495: pop
      //   496: return
      //   497: aload_1
      //   498: invokestatic 207	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   501: goto -142 -> 359
      //   504: astore_1
      //   505: aload_0
      //   506: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   509: getfield 42	com/appodeal/ads/ac:d	Ljava/lang/String;
      //   512: ldc -47
      //   514: invokevirtual 50	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   517: ifne +7 -> 524
      //   520: aload_1
      //   521: invokestatic 107	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   524: aload_0
      //   525: getfield 15	com/appodeal/ads/ac$b:a	Lcom/appodeal/ads/ac;
      //   528: invokestatic 56	com/appodeal/ads/ac:a	(Lcom/appodeal/ads/ac;)Landroid/os/Handler;
      //   531: iconst_0
      //   532: invokevirtual 62	android/os/Handler:sendEmptyMessage	(I)Z
      //   535: pop
      //   536: return
      //   537: astore_1
      //   538: aload_1
      //   539: invokestatic 107	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   542: goto -71 -> 471
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	545	0	this	b
      //   13	248	1	localObject1	Object
      //   277	2	1	localException1	Exception
      //   305	193	1	localObject2	Object
      //   504	17	1	localJSONException	JSONException
      //   537	2	1	localException2	Exception
      //   133	347	2	localObject3	Object
      //   37	192	3	localJSONObject	JSONObject
      //   111	217	4	str	String
      //   80	338	5	localSharedPreferences	SharedPreferences
      // Exception table:
      //   from	to	target	type
      //   0	38	277	java/lang/Exception
      //   42	54	277	java/lang/Exception
      //   55	64	277	java/lang/Exception
      //   67	99	277	java/lang/Exception
      //   99	113	277	java/lang/Exception
      //   116	126	277	java/lang/Exception
      //   134	151	277	java/lang/Exception
      //   154	202	277	java/lang/Exception
      //   208	235	277	java/lang/Exception
      //   241	258	277	java/lang/Exception
      //   264	276	277	java/lang/Exception
      //   295	306	277	java/lang/Exception
      //   309	327	277	java/lang/Exception
      //   333	359	277	java/lang/Exception
      //   471	496	277	java/lang/Exception
      //   497	501	277	java/lang/Exception
      //   505	524	277	java/lang/Exception
      //   524	536	277	java/lang/Exception
      //   538	542	277	java/lang/Exception
      //   333	359	504	org/json/JSONException
      //   497	501	504	org/json/JSONException
      //   359	471	537	java/lang/Exception
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
