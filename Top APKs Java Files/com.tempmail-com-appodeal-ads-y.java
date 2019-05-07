package com.appodeal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import com.appodeal.ads.f.c;
import com.appodeal.ads.f.g;
import com.appodeal.ads.utils.e;
import com.appodeal.ads.utils.p;
import com.appodeal.ads.utils.v;
import com.appodeal.ads.utils.z;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class y
{
  private static SSLSocketFactory B;
  static JSONObject r;
  boolean A;
  private final Handler C;
  private final int D = 0;
  private final int E = 1;
  private boolean F;
  final a a;
  final Context b;
  final int c;
  final String d;
  final String e;
  final String f;
  final String g;
  final com.appodeal.ads.d.h h;
  final c i;
  final long j;
  final String k;
  final Long l;
  final int m;
  final double n;
  final boolean o;
  final String p;
  final JSONObject q;
  boolean s;
  boolean t;
  boolean u;
  boolean v;
  boolean w;
  boolean x;
  boolean y;
  boolean z;
  
  private y(c paramC)
  {
    this.a = c.a(paramC);
    this.b = c.b(paramC);
    this.c = c.c(paramC);
    this.d = c.d(paramC);
    this.e = c.e(paramC);
    this.f = c.f(paramC);
    this.g = c.g(paramC);
    this.h = c.h(paramC);
    this.j = c.i(paramC);
    this.i = c.j(paramC);
    this.k = c.k(paramC);
    this.l = c.l(paramC);
    this.m = c.m(paramC);
    this.n = c.n(paramC);
    this.o = c.o(paramC);
    this.p = c.p(paramC);
    this.q = c.q(paramC);
    this.C = new Handler(Looper.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (y.this.a != null) {}
        switch (paramAnonymousMessage.what)
        {
        default: 
          return;
        case 1: 
          paramAnonymousMessage = (JSONObject)paramAnonymousMessage.obj;
          if (paramAnonymousMessage == null)
          {
            y.this.a.a(y.this.c);
            return;
          }
          y.this.a.a(paramAnonymousMessage, y.this.c, y.this.d);
          return;
        }
        y.this.a.a(y.this.c);
      }
    };
    if (this.d == null) {}
    while ((h.a) && ((this.a == null) || ((this.a instanceof z))) && ((this.d.equals("stats")) || (this.d.equals("show")) || (this.d.equals("click")) || (this.d.equals("finish")) || (this.d.equals("install")))) {
      return;
    }
    if ((this.d.equals("banner")) || (this.d.equals("debug")))
    {
      bool1 = true;
      this.s = bool1;
      if ((!this.d.equals("banner_320")) && (!this.d.equals("debug_banner_320"))) {
        break label660;
      }
      bool1 = true;
      label315:
      this.t = bool1;
      if ((!this.d.equals("banner_mrec")) && (!this.d.equals("debug_mrec"))) {
        break label665;
      }
      bool1 = true;
      label346:
      this.u = bool1;
      if ((!this.d.equals("video")) && (!this.d.equals("debug_video"))) {
        break label670;
      }
      bool1 = true;
      label377:
      this.v = bool1;
      if ((!this.d.equals("rewarded_video")) && (!this.d.equals("debug_rewarded_video"))) {
        break label675;
      }
      bool1 = true;
      label408:
      this.w = bool1;
      if ((!this.d.equals("native")) && (!this.d.equals("debug_native"))) {
        break label680;
      }
      bool1 = true;
      label439:
      this.x = bool1;
      if ((!this.d.equals("debug")) && (!this.d.equals("debug_banner_320")) && (!this.d.equals("debug_video")) && (!this.d.equals("debug_rewarded_video")) && (!this.d.equals("debug_mrec")) && (!this.d.equals("debug_native"))) {
        break label685;
      }
      bool1 = true;
      label518:
      this.y = bool1;
      if ((!this.s) && (!this.t) && (!this.u) && (!this.v) && (!this.w) && (!this.x)) {
        break label690;
      }
    }
    label660:
    label665:
    label670:
    label675:
    label680:
    label685:
    label690:
    for (boolean bool1 = true;; bool1 = false)
    {
      this.z = bool1;
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
              if (!this.d.equals("install")) {
                bool1 = true;
              }
            }
          }
        }
      }
      this.A = bool1;
      this.F = true;
      return;
      bool1 = false;
      break;
      bool1 = false;
      break label315;
      bool1 = false;
      break label346;
      bool1 = false;
      break label377;
      bool1 = false;
      break label408;
      bool1 = false;
      break label439;
      bool1 = false;
      break label518;
    }
  }
  
  private String a(String paramString)
  {
    return String.format("%s_timestamp", new Object[] { paramString });
  }
  
  private void a(JSONObject paramJSONObject)
  {
    JSONArray localJSONArray = paramJSONObject.optJSONArray("segments");
    if ((localJSONArray == null) || (localJSONArray.length() == 0)) {}
    do
    {
      do
      {
        return;
        paramJSONObject = new g(this.b, paramJSONObject);
      } while (!paramJSONObject.b(localJSONArray));
      paramJSONObject = paramJSONObject.a(localJSONArray);
    } while (paramJSONObject == null);
    try
    {
      paramJSONObject.a();
      g.a(paramJSONObject);
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
  
  private String b(String paramString)
  {
    return String.format("%s_wst", new Object[] { paramString });
  }
  
  /* Error */
  private JSONObject b(SharedPreferences paramSharedPreferences)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   6: ifnull +12 -> 18
    //   9: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   12: invokevirtual 290	org/json/JSONObject:length	()I
    //   15: ifne +741 -> 756
    //   18: new 243	org/json/JSONObject
    //   21: dup
    //   22: invokespecial 291	org/json/JSONObject:<init>	()V
    //   25: putstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   28: aload_0
    //   29: getfield 75	com/appodeal/ads/y:b	Landroid/content/Context;
    //   32: invokevirtual 297	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   35: astore 7
    //   37: aload_1
    //   38: ldc_w 299
    //   41: aconst_null
    //   42: invokeinterface 305 3 0
    //   47: astore 8
    //   49: aload 8
    //   51: ifnonnull +8 -> 59
    //   54: ldc 2
    //   56: monitorexit
    //   57: aconst_null
    //   58: areturn
    //   59: aload_1
    //   60: ldc_w 307
    //   63: aconst_null
    //   64: invokeinterface 305 3 0
    //   69: astore 6
    //   71: aload_1
    //   72: ldc_w 309
    //   75: aconst_null
    //   76: invokeinterface 305 3 0
    //   81: astore_3
    //   82: aload_3
    //   83: astore 5
    //   85: aload 6
    //   87: astore 4
    //   89: aload 6
    //   91: ifnonnull +781 -> 872
    //   94: aload_0
    //   95: getfield 85	com/appodeal/ads/y:d	Ljava/lang/String;
    //   98: ldc -79
    //   100: invokevirtual 169	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   103: istore_2
    //   104: aload_3
    //   105: astore 5
    //   107: aload 6
    //   109: astore 4
    //   111: iload_2
    //   112: ifne +760 -> 872
    //   115: aload_3
    //   116: astore 5
    //   118: aload 6
    //   120: astore 4
    //   122: ldc_w 311
    //   125: invokestatic 317	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   128: pop
    //   129: aload_3
    //   130: astore 5
    //   132: aload 6
    //   134: astore 4
    //   136: ldc_w 319
    //   139: ldc_w 321
    //   142: iconst_1
    //   143: anewarray 313	java/lang/Class
    //   146: dup
    //   147: iconst_0
    //   148: ldc_w 293
    //   151: aastore
    //   152: invokevirtual 325	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   155: pop
    //   156: aload_3
    //   157: astore 5
    //   159: aload 6
    //   161: astore 4
    //   163: aload_0
    //   164: getfield 75	com/appodeal/ads/y:b	Landroid/content/Context;
    //   167: invokestatic 328	com/google/android/gms/ads/identifier/AdvertisingIdClient:getAdvertisingIdInfo	(Landroid/content/Context;)Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;
    //   170: astore 9
    //   172: aload_3
    //   173: astore 5
    //   175: aload 6
    //   177: astore 4
    //   179: aload 9
    //   181: invokevirtual 334	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:getId	()Ljava/lang/String;
    //   184: astore 6
    //   186: aload_3
    //   187: astore 5
    //   189: aload 6
    //   191: astore 4
    //   193: aload 9
    //   195: invokevirtual 338	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:isLimitAdTrackingEnabled	()Z
    //   198: ifeq +617 -> 815
    //   201: ldc_w 340
    //   204: astore_3
    //   205: aload_3
    //   206: astore 5
    //   208: aload 6
    //   210: astore 4
    //   212: aload_1
    //   213: invokeinterface 344 1 0
    //   218: astore_1
    //   219: aload_3
    //   220: astore 5
    //   222: aload 6
    //   224: astore 4
    //   226: aload_1
    //   227: ldc_w 307
    //   230: aload 6
    //   232: invokeinterface 350 3 0
    //   237: pop
    //   238: aload_3
    //   239: astore 5
    //   241: aload 6
    //   243: astore 4
    //   245: aload_1
    //   246: ldc_w 309
    //   249: aload_3
    //   250: invokeinterface 350 3 0
    //   255: pop
    //   256: aload_3
    //   257: astore 5
    //   259: aload 6
    //   261: astore 4
    //   263: aload_1
    //   264: invokeinterface 353 1 0
    //   269: aload_3
    //   270: astore 5
    //   272: aload 6
    //   274: astore 4
    //   276: ldc_w 355
    //   279: iconst_1
    //   280: anewarray 4	java/lang/Object
    //   283: dup
    //   284: iconst_0
    //   285: aload 6
    //   287: aastore
    //   288: invokestatic 233	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   291: invokestatic 358	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
    //   294: aload 6
    //   296: astore_1
    //   297: aload_1
    //   298: ifnonnull +571 -> 869
    //   301: aload_0
    //   302: getfield 75	com/appodeal/ads/y:b	Landroid/content/Context;
    //   305: invokestatic 363	com/appodeal/ads/ay:l	(Landroid/content/Context;)Ljava/lang/String;
    //   308: astore_1
    //   309: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   312: ldc_w 365
    //   315: aload 8
    //   317: invokevirtual 369	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   320: pop
    //   321: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   324: ldc_w 371
    //   327: getstatic 376	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   330: invokevirtual 369	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   333: pop
    //   334: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   337: ldc_w 378
    //   340: getstatic 381	android/os/Build$VERSION:SDK_INT	I
    //   343: invokevirtual 384	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   346: pop
    //   347: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   350: ldc_w 386
    //   353: ldc_w 388
    //   356: invokevirtual 369	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   359: pop
    //   360: aload_0
    //   361: getfield 75	com/appodeal/ads/y:b	Landroid/content/Context;
    //   364: invokevirtual 391	android/content/Context:getPackageName	()Ljava/lang/String;
    //   367: astore 4
    //   369: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   372: ldc_w 393
    //   375: aload 4
    //   377: invokevirtual 369	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   380: pop
    //   381: aload 7
    //   383: aload 4
    //   385: iconst_0
    //   386: invokevirtual 399	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   389: astore 5
    //   391: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   394: ldc_w 401
    //   397: aload 5
    //   399: getfield 406	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   402: invokevirtual 369	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   405: pop
    //   406: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   409: ldc_w 408
    //   412: aload 5
    //   414: getfield 411	android/content/pm/PackageInfo:versionCode	I
    //   417: invokevirtual 384	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   420: pop
    //   421: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   424: ldc_w 413
    //   427: aload 5
    //   429: getfield 416	android/content/pm/PackageInfo:firstInstallTime	J
    //   432: ldc2_w 417
    //   435: ldiv
    //   436: invokevirtual 421	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   439: pop
    //   440: aload 7
    //   442: aload 4
    //   444: iconst_0
    //   445: invokevirtual 425	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   448: astore 5
    //   450: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   453: ldc_w 427
    //   456: aload 5
    //   458: getfield 432	android/content/pm/ApplicationInfo:targetSdkVersion	I
    //   461: invokevirtual 384	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   464: pop
    //   465: getstatic 434	com/appodeal/ads/Appodeal:j	Ljava/lang/String;
    //   468: ifnull +16 -> 484
    //   471: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   474: ldc_w 436
    //   477: getstatic 434	com/appodeal/ads/Appodeal:j	Ljava/lang/String;
    //   480: invokevirtual 369	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   483: pop
    //   484: getstatic 437	com/appodeal/ads/Appodeal:k	Ljava/lang/String;
    //   487: ifnull +16 -> 503
    //   490: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   493: ldc_w 439
    //   496: getstatic 437	com/appodeal/ads/Appodeal:k	Ljava/lang/String;
    //   499: invokevirtual 369	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   502: pop
    //   503: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   506: ldc_w 441
    //   509: aload_1
    //   510: invokevirtual 369	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   513: pop
    //   514: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   517: ldc_w 443
    //   520: aload_3
    //   521: invokevirtual 369	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   524: pop
    //   525: aload_0
    //   526: getfield 75	com/appodeal/ads/y:b	Landroid/content/Context;
    //   529: invokestatic 446	com/appodeal/ads/ay:f	(Landroid/content/Context;)Landroid/util/Pair;
    //   532: astore_1
    //   533: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   536: ldc_w 448
    //   539: aload_1
    //   540: getfield 454	android/util/Pair:first	Ljava/lang/Object;
    //   543: invokevirtual 369	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   546: pop
    //   547: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   550: ldc_w 456
    //   553: aload_1
    //   554: getfield 459	android/util/Pair:second	Ljava/lang/Object;
    //   557: invokevirtual 369	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   560: pop
    //   561: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   564: ldc_w 461
    //   567: aload_0
    //   568: getfield 75	com/appodeal/ads/y:b	Landroid/content/Context;
    //   571: invokestatic 464	com/appodeal/ads/ay:i	(Landroid/content/Context;)F
    //   574: f2d
    //   575: invokevirtual 467	org/json/JSONObject:put	(Ljava/lang/String;D)Lorg/json/JSONObject;
    //   578: pop
    //   579: aload_0
    //   580: getfield 75	com/appodeal/ads/y:b	Landroid/content/Context;
    //   583: invokestatic 470	com/appodeal/ads/ay:n	(Landroid/content/Context;)Z
    //   586: ifeq +254 -> 840
    //   589: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   592: ldc_w 472
    //   595: ldc_w 474
    //   598: invokevirtual 369	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   601: pop
    //   602: getstatic 479	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   605: ldc_w 481
    //   608: invokevirtual 169	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   611: ifeq +270 -> 881
    //   614: ldc_w 483
    //   617: astore_1
    //   618: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   621: ldc_w 485
    //   624: aload_1
    //   625: invokevirtual 369	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   628: pop
    //   629: aload 7
    //   631: aload 4
    //   633: invokevirtual 488	android/content/pm/PackageManager:getInstallerPackageName	(Ljava/lang/String;)Ljava/lang/String;
    //   636: astore_3
    //   637: aload_3
    //   638: astore_1
    //   639: aload_3
    //   640: ifnonnull +7 -> 647
    //   643: ldc_w 490
    //   646: astore_1
    //   647: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   650: ldc_w 492
    //   653: aload_1
    //   654: invokevirtual 369	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   657: pop
    //   658: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   661: ldc_w 494
    //   664: getstatic 479	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   667: invokevirtual 369	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   670: pop
    //   671: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   674: ldc_w 496
    //   677: ldc_w 498
    //   680: iconst_2
    //   681: anewarray 4	java/lang/Object
    //   684: dup
    //   685: iconst_0
    //   686: getstatic 479	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   689: aastore
    //   690: dup
    //   691: iconst_1
    //   692: getstatic 501	android/os/Build:MODEL	Ljava/lang/String;
    //   695: aastore
    //   696: invokestatic 233	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   699: invokevirtual 369	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   702: pop
    //   703: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   706: ldc_w 503
    //   709: invokestatic 505	com/appodeal/ads/ay:a	()Z
    //   712: invokevirtual 508	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   715: pop
    //   716: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   719: ldc_w 510
    //   722: aload_0
    //   723: getfield 75	com/appodeal/ads/y:b	Landroid/content/Context;
    //   726: invokestatic 512	com/appodeal/ads/ay:t	(Landroid/content/Context;)Ljava/lang/String;
    //   729: invokevirtual 369	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   732: pop
    //   733: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   736: ldc_w 514
    //   739: iconst_1
    //   740: anewarray 165	java/lang/String
    //   743: dup
    //   744: iconst_0
    //   745: ldc_w 516
    //   748: aastore
    //   749: invokestatic 519	com/appodeal/ads/ay:a	([Ljava/lang/String;)Z
    //   752: invokevirtual 508	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   755: pop
    //   756: new 243	org/json/JSONObject
    //   759: dup
    //   760: invokespecial 291	org/json/JSONObject:<init>	()V
    //   763: astore_1
    //   764: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   767: invokevirtual 523	org/json/JSONObject:keys	()Ljava/util/Iterator;
    //   770: astore_3
    //   771: aload_3
    //   772: invokeinterface 528 1 0
    //   777: ifeq +87 -> 864
    //   780: aload_3
    //   781: invokeinterface 532 1 0
    //   786: checkcast 165	java/lang/String
    //   789: astore 4
    //   791: aload_1
    //   792: aload 4
    //   794: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   797: aload 4
    //   799: invokevirtual 536	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   802: invokevirtual 369	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   805: pop
    //   806: goto -35 -> 771
    //   809: astore_1
    //   810: ldc 2
    //   812: monitorexit
    //   813: aload_1
    //   814: athrow
    //   815: ldc_w 538
    //   818: astore_3
    //   819: goto -614 -> 205
    //   822: astore_1
    //   823: aload_1
    //   824: invokestatic 276	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   827: goto +45 -> 872
    //   830: astore 5
    //   832: aload 5
    //   834: invokestatic 276	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   837: goto -372 -> 465
    //   840: getstatic 289	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   843: ldc_w 472
    //   846: ldc_w 540
    //   849: invokevirtual 369	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   852: pop
    //   853: goto -251 -> 602
    //   856: astore_1
    //   857: aload_1
    //   858: invokestatic 276	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   861: goto -203 -> 658
    //   864: ldc 2
    //   866: monitorexit
    //   867: aload_1
    //   868: areturn
    //   869: goto -560 -> 309
    //   872: aload 4
    //   874: astore_1
    //   875: aload 5
    //   877: astore_3
    //   878: goto -581 -> 297
    //   881: ldc_w 542
    //   884: astore_1
    //   885: goto -267 -> 618
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	888	0	this	y
    //   0	888	1	paramSharedPreferences	SharedPreferences
    //   103	9	2	bool	boolean
    //   81	797	3	localObject1	Object
    //   87	786	4	str1	String
    //   83	374	5	localObject2	Object
    //   830	46	5	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   69	226	6	str2	String
    //   35	595	7	localPackageManager	PackageManager
    //   47	269	8	str3	String
    //   170	24	9	localInfo	com.google.android.gms.ads.identifier.AdvertisingIdClient.Info
    // Exception table:
    //   from	to	target	type
    //   3	18	809	finally
    //   18	49	809	finally
    //   54	57	809	finally
    //   59	82	809	finally
    //   94	104	809	finally
    //   122	129	809	finally
    //   136	156	809	finally
    //   163	172	809	finally
    //   179	186	809	finally
    //   193	201	809	finally
    //   212	219	809	finally
    //   226	238	809	finally
    //   245	256	809	finally
    //   263	269	809	finally
    //   276	294	809	finally
    //   301	309	809	finally
    //   309	381	809	finally
    //   381	465	809	finally
    //   465	484	809	finally
    //   484	503	809	finally
    //   503	602	809	finally
    //   602	614	809	finally
    //   618	629	809	finally
    //   629	637	809	finally
    //   647	658	809	finally
    //   658	756	809	finally
    //   756	771	809	finally
    //   771	806	809	finally
    //   810	813	809	finally
    //   823	827	809	finally
    //   832	837	809	finally
    //   840	853	809	finally
    //   857	861	809	finally
    //   864	867	809	finally
    //   122	129	822	java/lang/Exception
    //   136	156	822	java/lang/Exception
    //   163	172	822	java/lang/Exception
    //   179	186	822	java/lang/Exception
    //   193	201	822	java/lang/Exception
    //   212	219	822	java/lang/Exception
    //   226	238	822	java/lang/Exception
    //   245	256	822	java/lang/Exception
    //   263	269	822	java/lang/Exception
    //   276	294	822	java/lang/Exception
    //   381	465	830	android/content/pm/PackageManager$NameNotFoundException
    //   629	637	856	java/lang/Exception
    //   647	658	856	java/lang/Exception
  }
  
  private SSLSocketFactory d()
  {
    try
    {
      if (B == null)
      {
        localObject1 = new ArrayList();
        ((List)localObject1).add("A1ABC1296E644B3A25179FCD3E277C8D36039BEE94478E2F5104FA4244237F54");
        ((List)localObject1).add("E91093227F02CE854C3214749DC7FB3459E0E43E80CAE27F01AA0EA92894C9E1");
        localObject1 = new com.appodeal.ads.utils.f((List)localObject1, 1526342400000L);
        SSLContext localSSLContext = SSLContext.getInstance("TLSv1");
        localSSLContext.init(null, new TrustManager[] { localObject1 }, null);
        B = localSSLContext.getSocketFactory();
      }
      Object localObject1 = B;
      return localObject1;
    }
    finally {}
  }
  
  JSONObject a(SharedPreferences paramSharedPreferences)
  {
    localJSONObject = b(paramSharedPreferences);
    if (localJSONObject == null) {
      return null;
    }
    try
    {
      if (this.s) {
        localJSONObject.put("type", "banner");
      }
      if (this.t)
      {
        localJSONObject.put("type", "banner_320");
        float f1 = ay.g(this.b);
        float f2 = ay.h(this.b);
        if ((i.t) && (f1 >= 728.0F) && (f2 > 720.0F)) {
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
      if (h.a) {
        localJSONObject.put("test", true);
      }
      localObject1 = ay.d(this.b);
      localJSONObject.put("lt", ((Pair)localObject1).first);
      localJSONObject.put("lat", ((Pair)((Pair)localObject1).second).first);
      localJSONObject.put("lon", ((Pair)((Pair)localObject1).second).second);
      localObject1 = ay.b(this.b);
      localJSONObject.put("connection", ((ay.a)localObject1).a);
      localJSONObject.put("battery", ay.k(this.b));
      localJSONObject.put("connection_subtype", ((ay.a)localObject1).b);
      localJSONObject.put("connection_fast", ((ay.a)localObject1).c);
      localJSONObject.put("crr", ay.c(this.b));
      localJSONObject.put("locale", Locale.getDefault().toString());
      localObject1 = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.ENGLISH);
      localJSONObject.put("timezone", new SimpleDateFormat("Z", Locale.ENGLISH).format(((Calendar)localObject1).getTime()));
      localJSONObject.put("local_time", System.currentTimeMillis() / 1000L);
      localJSONObject.put("user_agent", System.getProperty("http.agent"));
      e.c(this.b);
      localJSONObject.put("session_id", e.a(paramSharedPreferences));
      localJSONObject.put("session_uptime", e.b());
      localJSONObject.put("app_uptime", e.b(paramSharedPreferences));
      localObject1 = paramSharedPreferences.getString("inapps", null);
      if (localObject1 != null) {
        localJSONObject.put("inapps", new JSONObject((String)localObject1));
      }
      if (this.z)
      {
        localObject1 = new JSONArray();
        if (this.s) {
          localObject1 = new JSONArray(q.c);
        }
        if (this.v) {
          localObject1 = new JSONArray(aq.s);
        }
        if (this.w) {
          localObject1 = new JSONArray(au.x);
        }
        if (this.t) {
          localObject1 = new JSONArray(i.F);
        }
        if (this.u) {
          localObject1 = new JSONArray(aa.C);
        }
        if (this.x) {
          localObject1 = new JSONArray(Native.w);
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
      localJSONObject.put("id", this.e);
      localJSONObject.put("main_id", this.f);
      if ((this.z) || (this.k != null)) {
        localJSONObject.put("ad_stats", c());
      }
      if (this.i != null) {
        localJSONObject.put("placement_id", this.i.b());
      }
    }
    catch (JSONException localException2)
    {
      try
      {
        localJSONObject.put("sa", v.a(this.b));
      }
      catch (Exception localException2)
      {
        try
        {
          for (;;)
          {
            localJSONObject.put("user_settings", ay.u(this.b).k());
            Object localObject1 = paramSharedPreferences.edit();
            ((SharedPreferences.Editor)localObject1).putLong("lastSettingsTime", System.currentTimeMillis());
            ((SharedPreferences.Editor)localObject1).putBoolean("should_update_user_settings", false);
            ((SharedPreferences.Editor)localObject1).apply();
            UserSettings.a = false;
            localObject1 = Calendar.getInstance();
            ((Calendar)localObject1).setTimeInMillis(paramSharedPreferences.getLong("lastAppTime", 0L));
            ((Calendar)localObject1).add(5, 1);
            if ((!h.m) && (h.l) && (this.z) && (((Calendar)localObject1).getTimeInMillis() < System.currentTimeMillis()))
            {
              h.m = true;
              try
              {
                localObject1 = new JSONArray();
                Object localObject2 = this.b.getPackageManager().getInstalledApplications(0);
                Pattern localPattern = Pattern.compile("^?(?:com\\.android|com\\.google|com\\.sec|com\\.samsung|com\\.sonyericsson|com\\.sonymobile|com\\.motorola|com\\.htc).*$");
                if (localObject2 == null) {
                  break;
                }
                localObject2 = ((List)localObject2).iterator();
                while (((Iterator)localObject2).hasNext())
                {
                  String str = ((ApplicationInfo)((Iterator)localObject2).next()).packageName;
                  if ((!localPattern.matcher(str).matches()) && (!str.equals("android"))) {
                    ((JSONArray)localObject1).put(str);
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
              h.m = false;
            }
            if (this.h != null)
            {
              if (!this.d.equals("stats")) {
                break label1393;
              }
              localJSONObject.put("id", this.h.a().toString());
            }
            return localJSONObject;
            localJSONException = localJSONException;
            Appodeal.a(localJSONException);
            continue;
            localException2 = localException2;
            Appodeal.a(localException2);
          }
        }
        catch (Exception localException3)
        {
          for (;;)
          {
            Appodeal.a(localException3);
            continue;
            localJSONObject.put("apps", localException3);
            continue;
            if (this.d.equals("show"))
            {
              if (this.h.e())
              {
                if (!this.h.c())
                {
                  Appodeal.a("/get error, rtb invalid check");
                  return null;
                }
                localJSONObject.put("rtb_check", this.h.b());
                localJSONObject.put("bidder_id", this.h.d());
              }
            }
            else if (((this.d.equals("click")) || (this.d.equals("finish"))) && (this.h.e())) {
              localJSONObject.put("id", this.h.d());
            }
          }
        }
      }
    }
    localObject1 = Calendar.getInstance();
    ((Calendar)localObject1).setTimeInMillis(paramSharedPreferences.getLong("lastSettingsTime", 0L));
    ((Calendar)localObject1).add(5, 1);
    if ((!UserSettings.a) && (this.z) && ((((Calendar)localObject1).getTimeInMillis() < System.currentTimeMillis()) || (paramSharedPreferences.getBoolean("should_update_user_settings", true)))) {
      UserSettings.a = true;
    }
  }
  
  public void a()
  {
    if (this.F) {
      p.a.execute(new b(null));
    }
  }
  
  void a(SharedPreferences paramSharedPreferences, int paramInt, String paramString)
  {
    paramSharedPreferences = paramSharedPreferences.edit();
    paramSharedPreferences.putInt(b(paramString), paramInt);
    paramSharedPreferences.apply();
  }
  
  void a(SharedPreferences paramSharedPreferences, String paramString1, String paramString2)
  {
    paramSharedPreferences = paramSharedPreferences.edit();
    paramSharedPreferences.putString(paramString1, paramString2);
    paramSharedPreferences.putLong(a(paramString1), System.currentTimeMillis());
    paramSharedPreferences.apply();
  }
  
  boolean a(SharedPreferences paramSharedPreferences, String paramString)
  {
    long l1 = paramSharedPreferences.getLong(a(paramString), 0L);
    if (System.currentTimeMillis() - l1 > b(paramSharedPreferences, paramString))
    {
      paramSharedPreferences = paramSharedPreferences.edit();
      paramSharedPreferences.remove(paramString);
      paramSharedPreferences.remove(a(paramString));
      paramSharedPreferences.remove(b(paramString));
      paramSharedPreferences.apply();
      return false;
    }
    return true;
  }
  
  int b(SharedPreferences paramSharedPreferences, String paramString)
  {
    return paramSharedPreferences.getInt(b(paramString), 86400000);
  }
  
  URL b()
  {
    if (this.A)
    {
      if (this.o) {
        return ay.d(this.d);
      }
      return new URL(com.appodeal.ads.utils.h.a("get"));
    }
    return new URL(com.appodeal.ads.utils.h.a(this.d));
  }
  
  JSONObject c()
  {
    JSONObject localJSONObject = new JSONObject();
    int i1 = i.x;
    int i2 = q.o;
    int i3 = aq.j;
    int i4 = au.n;
    int i5 = Native.q;
    int i6 = aa.u;
    int i7 = i.y;
    int i8 = q.p;
    int i9 = aq.l;
    int i10 = au.p;
    int i11 = Native.r;
    int i12 = aa.v;
    int i13 = au.o;
    int i14 = aq.k;
    try
    {
      localJSONObject.put("show", i1 + i2 + i3 + i4 + i5 + i6);
      localJSONObject.put("click", i7 + i8 + i9 + i10 + i11 + i12);
      if ((this.v) || (this.w) || ((this.k != null) && ((this.k.equals("video")) || (this.k.equals("rewarded_video"))))) {
        localJSONObject.put("finish", i13 + i14);
      }
      if ((this.s) || ((this.k != null) && (this.k.equals("banner"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "show" }), q.o);
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "click" }), q.p);
      }
      if ((this.v) || ((this.k != null) && (this.k.equals("video"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "show" }), aq.j);
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "click" }), aq.l);
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "finish" }), aq.k);
      }
      if ((this.w) || ((this.k != null) && (this.k.equals("rewarded_video"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "show" }), au.n);
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "click" }), au.p);
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "finish" }), au.o);
      }
      if ((this.t) || ((this.k != null) && (this.k.equals("banner_320"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "show" }), i.x);
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "click" }), i.y);
      }
      if ((this.u) || ((this.k != null) && (this.k.equals("banner_mrec"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "show" }), aa.u);
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "click" }), aa.v);
      }
      if ((this.x) || ((this.k != null) && (this.k.equals("native"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "native", "show" }), Native.q);
        localJSONObject.put(String.format("%s_%s", new Object[] { "native", "click" }), Native.r);
      }
      return localJSONObject;
    }
    catch (Exception localException)
    {
      Appodeal.a(localException);
    }
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
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 4
      //   3: aload_0
      //   4: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   7: getfield 32	com/appodeal/ads/y:b	Landroid/content/Context;
      //   10: ldc 34
      //   12: iconst_0
      //   13: invokevirtual 40	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   16: astore_2
      //   17: aload_0
      //   18: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   21: aload_2
      //   22: invokevirtual 43	com/appodeal/ads/y:a	(Landroid/content/SharedPreferences;)Lorg/json/JSONObject;
      //   25: astore_2
      //   26: aload_2
      //   27: ifnonnull +16 -> 43
      //   30: aload_0
      //   31: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   34: invokestatic 46	com/appodeal/ads/y:a	(Lcom/appodeal/ads/y;)Landroid/os/Handler;
      //   37: iconst_0
      //   38: invokevirtual 52	android/os/Handler:sendEmptyMessage	(I)Z
      //   41: pop
      //   42: return
      //   43: aload_0
      //   44: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   47: invokevirtual 55	com/appodeal/ads/y:b	()Ljava/net/URL;
      //   50: astore 5
      //   52: aload_0
      //   53: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   56: getfield 32	com/appodeal/ads/y:b	Landroid/content/Context;
      //   59: ldc 57
      //   61: iconst_0
      //   62: invokevirtual 40	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   65: astore 6
      //   67: aload 5
      //   69: invokevirtual 63	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   72: astore_3
      //   73: aload 5
      //   75: invokevirtual 67	java/net/URL:getProtocol	()Ljava/lang/String;
      //   78: ldc 69
      //   80: invokevirtual 75	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   83: ifeq +17 -> 100
      //   86: aload_3
      //   87: checkcast 77	javax/net/ssl/HttpsURLConnection
      //   90: aload_0
      //   91: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   94: invokestatic 80	com/appodeal/ads/y:b	(Lcom/appodeal/ads/y;)Ljavax/net/ssl/SSLSocketFactory;
      //   97: invokevirtual 84	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
      //   100: aload_0
      //   101: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   104: getfield 88	com/appodeal/ads/y:A	Z
      //   107: ifeq +452 -> 559
      //   110: aload 6
      //   112: aload_0
      //   113: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   116: getfield 92	com/appodeal/ads/y:d	Ljava/lang/String;
      //   119: invokeinterface 98 2 0
      //   124: ifeq +435 -> 559
      //   127: aload_3
      //   128: sipush 10000
      //   131: invokevirtual 104	java/net/URLConnection:setConnectTimeout	(I)V
      //   134: aload_3
      //   135: sipush 10000
      //   138: invokevirtual 107	java/net/URLConnection:setReadTimeout	(I)V
      //   141: aload_3
      //   142: sipush 20000
      //   145: invokevirtual 104	java/net/URLConnection:setConnectTimeout	(I)V
      //   148: aload_3
      //   149: sipush 20000
      //   152: invokevirtual 107	java/net/URLConnection:setReadTimeout	(I)V
      //   155: aload_3
      //   156: ldc 109
      //   158: ldc 111
      //   160: invokevirtual 115	java/net/URLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   163: aload_3
      //   164: iconst_1
      //   165: invokevirtual 119	java/net/URLConnection:setDoOutput	(Z)V
      //   168: new 121	java/io/ByteArrayOutputStream
      //   171: dup
      //   172: invokespecial 122	java/io/ByteArrayOutputStream:<init>	()V
      //   175: astore 5
      //   177: new 124	java/util/zip/GZIPOutputStream
      //   180: dup
      //   181: aload 5
      //   183: invokespecial 127	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   186: astore 7
      //   188: aload 7
      //   190: aload_2
      //   191: invokevirtual 132	org/json/JSONObject:toString	()Ljava/lang/String;
      //   194: ldc -122
      //   196: invokevirtual 138	java/lang/String:getBytes	(Ljava/lang/String;)[B
      //   199: invokevirtual 142	java/util/zip/GZIPOutputStream:write	([B)V
      //   202: aload 7
      //   204: invokevirtual 145	java/util/zip/GZIPOutputStream:close	()V
      //   207: aload 5
      //   209: invokevirtual 149	java/io/ByteArrayOutputStream:toByteArray	()[B
      //   212: iconst_0
      //   213: invokestatic 155	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
      //   216: astore_2
      //   217: aload_3
      //   218: invokevirtual 159	java/net/URLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   221: aload_2
      //   222: invokestatic 164	com/appodeal/ads/ay:a	(Ljava/io/OutputStream;Ljava/lang/String;)V
      //   225: aload_3
      //   226: invokevirtual 168	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
      //   229: invokestatic 171	com/appodeal/ads/ay:a	(Ljava/io/InputStream;)Ljava/lang/String;
      //   232: astore 5
      //   234: aload 4
      //   236: astore_2
      //   237: aload 5
      //   239: ifnull +47 -> 286
      //   242: aload 4
      //   244: astore_2
      //   245: aload 5
      //   247: invokevirtual 175	java/lang/String:isEmpty	()Z
      //   250: ifne +36 -> 286
      //   253: aload 4
      //   255: astore_2
      //   256: aload 5
      //   258: ldc -79
      //   260: invokevirtual 75	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   263: ifne +23 -> 286
      //   266: aload_0
      //   267: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   270: getfield 92	com/appodeal/ads/y:d	Ljava/lang/String;
      //   273: ldc -77
      //   275: invokevirtual 75	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   278: istore_1
      //   279: iload_1
      //   280: ifeq +612 -> 892
      //   283: aload 4
      //   285: astore_2
      //   286: aload_3
      //   287: ifnull +602 -> 889
      //   290: aload_3
      //   291: instanceof 77
      //   294: ifeq +403 -> 697
      //   297: aload_3
      //   298: checkcast 77	javax/net/ssl/HttpsURLConnection
      //   301: invokevirtual 182	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   304: aload_2
      //   305: ifnonnull +496 -> 801
      //   308: aload_0
      //   309: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   312: getfield 88	com/appodeal/ads/y:A	Z
      //   315: ifeq +473 -> 788
      //   318: aload 6
      //   320: aload_0
      //   321: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   324: getfield 92	com/appodeal/ads/y:d	Ljava/lang/String;
      //   327: invokeinterface 98 2 0
      //   332: ifeq +456 -> 788
      //   335: aload_0
      //   336: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   339: aload 6
      //   341: aload_0
      //   342: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   345: getfield 92	com/appodeal/ads/y:d	Ljava/lang/String;
      //   348: invokevirtual 185	com/appodeal/ads/y:a	(Landroid/content/SharedPreferences;Ljava/lang/String;)Z
      //   351: ifeq +437 -> 788
      //   354: new 187	com/appodeal/ads/utils/c/a
      //   357: dup
      //   358: ldc -67
      //   360: invokespecial 192	com/appodeal/ads/utils/c/a:<init>	(Ljava/lang/String;)V
      //   363: invokestatic 197	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   366: aload 6
      //   368: aload_0
      //   369: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   372: getfield 92	com/appodeal/ads/y:d	Ljava/lang/String;
      //   375: ldc -57
      //   377: invokeinterface 203 3 0
      //   382: astore_3
      //   383: new 129	org/json/JSONObject
      //   386: dup
      //   387: aload_3
      //   388: invokespecial 204	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   391: astore_2
      //   392: aload_0
      //   393: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   396: getfield 88	com/appodeal/ads/y:A	Z
      //   399: ifeq +436 -> 835
      //   402: aload_3
      //   403: getstatic 210	com/appodeal/ads/utils/Log$LogLevel:verbose	Lcom/appodeal/ads/utils/Log$LogLevel;
      //   406: invokestatic 213	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;Lcom/appodeal/ads/utils/Log$LogLevel;)V
      //   409: aload_0
      //   410: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   413: getfield 88	com/appodeal/ads/y:A	Z
      //   416: ifeq +99 -> 515
      //   419: aload_0
      //   420: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   423: getfield 32	com/appodeal/ads/y:b	Landroid/content/Context;
      //   426: aload_2
      //   427: ldc -41
      //   429: invokevirtual 219	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   432: invokestatic 224	com/appodeal/ads/UserSettings:a	(Landroid/content/Context;Lorg/json/JSONObject;)V
      //   435: aload_0
      //   436: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   439: getfield 32	com/appodeal/ads/y:b	Landroid/content/Context;
      //   442: aload_2
      //   443: ldc -30
      //   445: invokevirtual 219	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   448: aload_2
      //   449: ldc -28
      //   451: invokevirtual 231	org/json/JSONObject:optBoolean	(Ljava/lang/String;)Z
      //   454: invokestatic 236	com/appodeal/ads/f:a	(Landroid/content/Context;Lorg/json/JSONObject;Z)V
      //   457: aload_0
      //   458: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   461: aload 6
      //   463: aload_2
      //   464: ldc -18
      //   466: ldc -17
      //   468: invokevirtual 243	org/json/JSONObject:optInt	(Ljava/lang/String;I)I
      //   471: aload_0
      //   472: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   475: getfield 92	com/appodeal/ads/y:d	Ljava/lang/String;
      //   478: invokevirtual 246	com/appodeal/ads/y:a	(Landroid/content/SharedPreferences;ILjava/lang/String;)V
      //   481: aload_0
      //   482: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   485: aload_2
      //   486: invokestatic 249	com/appodeal/ads/y:a	(Lcom/appodeal/ads/y;Lorg/json/JSONObject;)V
      //   489: aload_2
      //   490: ldc -5
      //   492: invokevirtual 255	org/json/JSONObject:optJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   495: invokestatic 260	com/appodeal/ads/f/d:a	(Lorg/json/JSONArray;)V
      //   498: invokestatic 263	com/appodeal/ads/f/d:c	()V
      //   501: getstatic 267	com/appodeal/ads/Appodeal:g	Lcom/appodeal/ads/utils/r;
      //   504: ifnull +11 -> 515
      //   507: getstatic 267	com/appodeal/ads/Appodeal:g	Lcom/appodeal/ads/utils/r;
      //   510: invokeinterface 271 1 0
      //   515: aload_0
      //   516: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   519: invokestatic 46	com/appodeal/ads/y:a	(Lcom/appodeal/ads/y;)Landroid/os/Handler;
      //   522: iconst_1
      //   523: aload_2
      //   524: invokevirtual 275	android/os/Handler:obtainMessage	(ILjava/lang/Object;)Landroid/os/Message;
      //   527: astore_2
      //   528: aload_0
      //   529: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   532: invokestatic 46	com/appodeal/ads/y:a	(Lcom/appodeal/ads/y;)Landroid/os/Handler;
      //   535: aload_2
      //   536: invokevirtual 279	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   539: pop
      //   540: return
      //   541: astore_2
      //   542: aload_2
      //   543: invokestatic 197	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   546: aload_0
      //   547: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   550: invokestatic 46	com/appodeal/ads/y:a	(Lcom/appodeal/ads/y;)Landroid/os/Handler;
      //   553: iconst_0
      //   554: invokevirtual 52	android/os/Handler:sendEmptyMessage	(I)Z
      //   557: pop
      //   558: return
      //   559: aload_3
      //   560: sipush 20000
      //   563: invokevirtual 104	java/net/URLConnection:setConnectTimeout	(I)V
      //   566: aload_3
      //   567: sipush 20000
      //   570: invokevirtual 107	java/net/URLConnection:setReadTimeout	(I)V
      //   573: goto -432 -> 141
      //   576: astore 4
      //   578: aload_3
      //   579: astore_2
      //   580: aload 4
      //   582: astore_3
      //   583: aload_3
      //   584: invokestatic 197	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   587: aload_3
      //   588: invokevirtual 282	java/io/IOException:getMessage	()Ljava/lang/String;
      //   591: ldc_w 284
      //   594: invokevirtual 75	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   597: ifne +16 -> 613
      //   600: aload_3
      //   601: invokevirtual 282	java/io/IOException:getMessage	()Ljava/lang/String;
      //   604: ldc_w 286
      //   607: invokevirtual 75	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   610: ifeq +119 -> 729
      //   613: aload_0
      //   614: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   617: invokestatic 46	com/appodeal/ads/y:a	(Lcom/appodeal/ads/y;)Landroid/os/Handler;
      //   620: iconst_0
      //   621: invokevirtual 52	android/os/Handler:sendEmptyMessage	(I)Z
      //   624: pop
      //   625: aload_2
      //   626: ifnull +272 -> 898
      //   629: aload_2
      //   630: instanceof 77
      //   633: ifeq +81 -> 714
      //   636: aload_2
      //   637: checkcast 77	javax/net/ssl/HttpsURLConnection
      //   640: invokevirtual 182	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   643: return
      //   644: astore_2
      //   645: aload_2
      //   646: invokestatic 197	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   649: goto -442 -> 207
      //   652: astore 4
      //   654: aload_3
      //   655: astore_2
      //   656: aload 4
      //   658: astore_3
      //   659: aload_2
      //   660: ifnull +17 -> 677
      //   663: aload_2
      //   664: instanceof 77
      //   667: ifeq +104 -> 771
      //   670: aload_2
      //   671: checkcast 77	javax/net/ssl/HttpsURLConnection
      //   674: invokevirtual 182	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   677: aload_3
      //   678: athrow
      //   679: astore_2
      //   680: aload 7
      //   682: invokevirtual 145	java/util/zip/GZIPOutputStream:close	()V
      //   685: aload_2
      //   686: athrow
      //   687: astore 4
      //   689: aload 4
      //   691: invokestatic 197	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   694: goto -9 -> 685
      //   697: aload_3
      //   698: instanceof 288
      //   701: ifeq +188 -> 889
      //   704: aload_3
      //   705: checkcast 288	java/net/HttpURLConnection
      //   708: invokevirtual 289	java/net/HttpURLConnection:disconnect	()V
      //   711: goto -407 -> 304
      //   714: aload_2
      //   715: instanceof 288
      //   718: ifeq +180 -> 898
      //   721: aload_2
      //   722: checkcast 288	java/net/HttpURLConnection
      //   725: invokevirtual 289	java/net/HttpURLConnection:disconnect	()V
      //   728: return
      //   729: aload_2
      //   730: ifnull +154 -> 884
      //   733: aload_2
      //   734: instanceof 77
      //   737: ifeq +15 -> 752
      //   740: aload_2
      //   741: checkcast 77	javax/net/ssl/HttpsURLConnection
      //   744: invokevirtual 182	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   747: aconst_null
      //   748: astore_2
      //   749: goto -445 -> 304
      //   752: aload_2
      //   753: instanceof 288
      //   756: ifeq +128 -> 884
      //   759: aload_2
      //   760: checkcast 288	java/net/HttpURLConnection
      //   763: invokevirtual 289	java/net/HttpURLConnection:disconnect	()V
      //   766: aconst_null
      //   767: astore_2
      //   768: goto -464 -> 304
      //   771: aload_2
      //   772: instanceof 288
      //   775: ifeq -98 -> 677
      //   778: aload_2
      //   779: checkcast 288	java/net/HttpURLConnection
      //   782: invokevirtual 289	java/net/HttpURLConnection:disconnect	()V
      //   785: goto -108 -> 677
      //   788: aload_0
      //   789: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   792: invokestatic 46	com/appodeal/ads/y:a	(Lcom/appodeal/ads/y;)Landroid/os/Handler;
      //   795: iconst_0
      //   796: invokevirtual 52	android/os/Handler:sendEmptyMessage	(I)Z
      //   799: pop
      //   800: return
      //   801: aload_2
      //   802: astore_3
      //   803: aload_0
      //   804: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   807: getfield 88	com/appodeal/ads/y:A	Z
      //   810: ifeq -427 -> 383
      //   813: aload_0
      //   814: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   817: aload 6
      //   819: aload_0
      //   820: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   823: getfield 92	com/appodeal/ads/y:d	Ljava/lang/String;
      //   826: aload_2
      //   827: invokevirtual 292	com/appodeal/ads/y:a	(Landroid/content/SharedPreferences;Ljava/lang/String;Ljava/lang/String;)V
      //   830: aload_2
      //   831: astore_3
      //   832: goto -449 -> 383
      //   835: aload_3
      //   836: invokestatic 294	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   839: goto -430 -> 409
      //   842: astore_2
      //   843: aload_2
      //   844: invokestatic 197	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   847: aload_0
      //   848: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   851: invokestatic 46	com/appodeal/ads/y:a	(Lcom/appodeal/ads/y;)Landroid/os/Handler;
      //   854: iconst_0
      //   855: invokevirtual 52	android/os/Handler:sendEmptyMessage	(I)Z
      //   858: pop
      //   859: return
      //   860: astore_3
      //   861: aload_3
      //   862: invokestatic 197	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   865: goto -350 -> 515
      //   868: astore_3
      //   869: aconst_null
      //   870: astore_2
      //   871: goto -212 -> 659
      //   874: astore_3
      //   875: goto -216 -> 659
      //   878: astore_3
      //   879: aconst_null
      //   880: astore_2
      //   881: goto -298 -> 583
      //   884: aconst_null
      //   885: astore_2
      //   886: goto -582 -> 304
      //   889: goto -585 -> 304
      //   892: aload 5
      //   894: astore_2
      //   895: goto -609 -> 286
      //   898: return
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	899	0	this	b
      //   278	2	1	bool	boolean
      //   16	520	2	localObject1	Object
      //   541	2	2	localException1	Exception
      //   579	58	2	localObject2	Object
      //   644	2	2	localException2	Exception
      //   655	16	2	localObject3	Object
      //   679	62	2	localObject4	Object
      //   748	83	2	str	String
      //   842	2	2	localJSONException	JSONException
      //   870	25	2	localObject5	Object
      //   72	764	3	localObject6	Object
      //   860	2	3	localException3	Exception
      //   868	1	3	localObject7	Object
      //   874	1	3	localObject8	Object
      //   878	1	3	localIOException1	java.io.IOException
      //   1	283	4	localObject9	Object
      //   576	5	4	localIOException2	java.io.IOException
      //   652	5	4	localObject10	Object
      //   687	3	4	localException4	Exception
      //   50	843	5	localObject11	Object
      //   65	753	6	localSharedPreferences	SharedPreferences
      //   186	495	7	localGZIPOutputStream	java.util.zip.GZIPOutputStream
      // Exception table:
      //   from	to	target	type
      //   3	26	541	java/lang/Exception
      //   30	42	541	java/lang/Exception
      //   43	67	541	java/lang/Exception
      //   290	304	541	java/lang/Exception
      //   308	383	541	java/lang/Exception
      //   383	409	541	java/lang/Exception
      //   515	540	541	java/lang/Exception
      //   629	643	541	java/lang/Exception
      //   663	677	541	java/lang/Exception
      //   677	679	541	java/lang/Exception
      //   697	711	541	java/lang/Exception
      //   714	728	541	java/lang/Exception
      //   733	747	541	java/lang/Exception
      //   752	766	541	java/lang/Exception
      //   771	785	541	java/lang/Exception
      //   788	800	541	java/lang/Exception
      //   803	830	541	java/lang/Exception
      //   835	839	541	java/lang/Exception
      //   843	859	541	java/lang/Exception
      //   861	865	541	java/lang/Exception
      //   73	100	576	java/io/IOException
      //   100	141	576	java/io/IOException
      //   141	188	576	java/io/IOException
      //   202	207	576	java/io/IOException
      //   207	234	576	java/io/IOException
      //   245	253	576	java/io/IOException
      //   256	279	576	java/io/IOException
      //   559	573	576	java/io/IOException
      //   645	649	576	java/io/IOException
      //   680	685	576	java/io/IOException
      //   685	687	576	java/io/IOException
      //   689	694	576	java/io/IOException
      //   202	207	644	java/lang/Exception
      //   73	100	652	finally
      //   100	141	652	finally
      //   141	188	652	finally
      //   202	207	652	finally
      //   207	234	652	finally
      //   245	253	652	finally
      //   256	279	652	finally
      //   559	573	652	finally
      //   645	649	652	finally
      //   680	685	652	finally
      //   685	687	652	finally
      //   689	694	652	finally
      //   188	202	679	finally
      //   680	685	687	java/lang/Exception
      //   383	409	842	org/json/JSONException
      //   835	839	842	org/json/JSONException
      //   409	515	860	java/lang/Exception
      //   67	73	868	finally
      //   583	613	874	finally
      //   613	625	874	finally
      //   67	73	878	java/io/IOException
    }
  }
  
  public static class c
  {
    private final Context a;
    private final int b;
    private final String c;
    private y.a d;
    private String e;
    private String f;
    private String g;
    private com.appodeal.ads.d.h h;
    private c i;
    private long j;
    private String k;
    private Long l;
    private int m = 1;
    private double n = -1.0D;
    private boolean o;
    private String p;
    private JSONObject q;
    
    public c(Context paramContext, int paramInt, String paramString)
    {
      this.a = paramContext;
      this.b = paramInt;
      this.c = paramString;
    }
    
    public c a(double paramDouble)
    {
      this.n = paramDouble;
      return this;
    }
    
    public c a(int paramInt)
    {
      this.m = paramInt;
      return this;
    }
    
    public c a(long paramLong)
    {
      this.j = paramLong;
      return this;
    }
    
    public c a(com.appodeal.ads.d.h paramH)
    {
      this.h = paramH;
      return this;
    }
    
    public c a(c paramC)
    {
      this.i = paramC;
      return this;
    }
    
    public c a(y.a paramA)
    {
      this.d = paramA;
      return this;
    }
    
    public c a(Long paramLong)
    {
      this.l = paramLong;
      return this;
    }
    
    public c a(String paramString)
    {
      this.e = paramString;
      return this;
    }
    
    public c a(JSONObject paramJSONObject)
    {
      this.q = paramJSONObject;
      return this;
    }
    
    public c a(boolean paramBoolean)
    {
      this.o = paramBoolean;
      return this;
    }
    
    public y a()
    {
      return new y(this, null);
    }
    
    public c b(String paramString)
    {
      this.f = paramString;
      return this;
    }
    
    public c c(String paramString)
    {
      this.g = paramString;
      return this;
    }
    
    public c d(String paramString)
    {
      this.k = paramString;
      return this;
    }
    
    public c e(String paramString)
    {
      this.p = paramString;
      return this;
    }
  }
}
