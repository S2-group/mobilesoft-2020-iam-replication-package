package com.appodeal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Pair;
import com.appodeal.ads.f.c;
import com.appodeal.ads.f.g;
import com.appodeal.ads.utils.ad;
import com.appodeal.ads.utils.e;
import com.appodeal.ads.utils.n;
import com.appodeal.ads.utils.r;
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
  private static SSLSocketFactory C;
  @VisibleForTesting
  static JSONObject r;
  @VisibleForTesting
  boolean A;
  private final JSONObject B;
  private final Handler D;
  private final int E = 0;
  private final int F = 1;
  private boolean G;
  @VisibleForTesting
  final a a;
  @VisibleForTesting
  final Context b;
  @VisibleForTesting
  final int c;
  @VisibleForTesting
  final String d;
  @VisibleForTesting
  final String e;
  @VisibleForTesting
  final String f;
  @VisibleForTesting
  final String g;
  @VisibleForTesting
  final com.appodeal.ads.d.h h;
  @VisibleForTesting
  final c i;
  @VisibleForTesting
  final long j;
  @VisibleForTesting
  final String k;
  @VisibleForTesting
  final Long l;
  @VisibleForTesting
  final int m;
  @VisibleForTesting
  final double n;
  @VisibleForTesting
  final boolean o;
  @VisibleForTesting
  final String p;
  @VisibleForTesting
  final JSONObject q;
  @VisibleForTesting
  boolean s;
  @VisibleForTesting
  boolean t;
  @VisibleForTesting
  boolean u;
  @VisibleForTesting
  boolean v;
  @VisibleForTesting
  boolean w;
  @VisibleForTesting
  boolean x;
  @VisibleForTesting
  boolean y;
  @VisibleForTesting
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
    this.B = c.r(paramC);
    this.D = new Handler(Looper.getMainLooper())
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
    while ((h.a) && ((this.a == null) || ((this.a instanceof ad))) && ((this.d.equals("stats")) || (this.d.equals("show")) || (this.d.equals("click")) || (this.d.equals("finish")) || (this.d.equals("install")))) {
      return;
    }
    if ((this.d.equals("banner")) || (this.d.equals("debug")))
    {
      bool1 = true;
      this.s = bool1;
      if ((!this.d.equals("banner_320")) && (!this.d.equals("debug_banner_320"))) {
        break label668;
      }
      bool1 = true;
      label323:
      this.t = bool1;
      if ((!this.d.equals("banner_mrec")) && (!this.d.equals("debug_mrec"))) {
        break label673;
      }
      bool1 = true;
      label354:
      this.u = bool1;
      if ((!this.d.equals("video")) && (!this.d.equals("debug_video"))) {
        break label678;
      }
      bool1 = true;
      label385:
      this.v = bool1;
      if ((!this.d.equals("rewarded_video")) && (!this.d.equals("debug_rewarded_video"))) {
        break label683;
      }
      bool1 = true;
      label416:
      this.w = bool1;
      if ((!this.d.equals("native")) && (!this.d.equals("debug_native"))) {
        break label688;
      }
      bool1 = true;
      label447:
      this.x = bool1;
      if ((!this.d.equals("debug")) && (!this.d.equals("debug_banner_320")) && (!this.d.equals("debug_video")) && (!this.d.equals("debug_rewarded_video")) && (!this.d.equals("debug_mrec")) && (!this.d.equals("debug_native"))) {
        break label693;
      }
      bool1 = true;
      label526:
      this.y = bool1;
      if ((!this.s) && (!this.t) && (!this.u) && (!this.v) && (!this.w) && (!this.x)) {
        break label698;
      }
    }
    label668:
    label673:
    label678:
    label683:
    label688:
    label693:
    label698:
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
      this.G = true;
      return;
      bool1 = false;
      break;
      bool1 = false;
      break label323;
      bool1 = false;
      break label354;
      bool1 = false;
      break label385;
      bool1 = false;
      break label416;
      bool1 = false;
      break label447;
      bool1 = false;
      break label526;
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
  @Nullable
  private JSONObject b(SharedPreferences paramSharedPreferences)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   6: ifnull +12 -> 18
    //   9: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   12: invokevirtual 297	org/json/JSONObject:length	()I
    //   15: ifne +747 -> 762
    //   18: new 249	org/json/JSONObject
    //   21: dup
    //   22: invokespecial 298	org/json/JSONObject:<init>	()V
    //   25: putstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   28: aload_0
    //   29: getfield 77	com/appodeal/ads/y:b	Landroid/content/Context;
    //   32: invokevirtual 304	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   35: astore 7
    //   37: aload_1
    //   38: ldc_w 306
    //   41: aconst_null
    //   42: invokeinterface 312 3 0
    //   47: astore 8
    //   49: aload 8
    //   51: ifnonnull +8 -> 59
    //   54: ldc 2
    //   56: monitorexit
    //   57: aconst_null
    //   58: areturn
    //   59: aload_1
    //   60: ldc_w 314
    //   63: aconst_null
    //   64: invokeinterface 312 3 0
    //   69: astore 6
    //   71: aload_1
    //   72: ldc_w 316
    //   75: aconst_null
    //   76: invokeinterface 312 3 0
    //   81: astore_3
    //   82: aload_3
    //   83: astore 5
    //   85: aload 6
    //   87: astore 4
    //   89: aload 6
    //   91: ifnonnull +787 -> 878
    //   94: aload_0
    //   95: getfield 87	com/appodeal/ads/y:d	Ljava/lang/String;
    //   98: ldc -73
    //   100: invokevirtual 175	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   103: istore_2
    //   104: aload_3
    //   105: astore 5
    //   107: aload 6
    //   109: astore 4
    //   111: iload_2
    //   112: ifne +766 -> 878
    //   115: aload_3
    //   116: astore 5
    //   118: aload 6
    //   120: astore 4
    //   122: ldc_w 318
    //   125: invokestatic 324	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   128: pop
    //   129: aload_3
    //   130: astore 5
    //   132: aload 6
    //   134: astore 4
    //   136: ldc_w 326
    //   139: ldc_w 328
    //   142: iconst_1
    //   143: anewarray 320	java/lang/Class
    //   146: dup
    //   147: iconst_0
    //   148: ldc_w 300
    //   151: aastore
    //   152: invokevirtual 332	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   155: pop
    //   156: aload_3
    //   157: astore 5
    //   159: aload 6
    //   161: astore 4
    //   163: aload_0
    //   164: getfield 77	com/appodeal/ads/y:b	Landroid/content/Context;
    //   167: invokestatic 335	com/google/android/gms/ads/identifier/AdvertisingIdClient:getAdvertisingIdInfo	(Landroid/content/Context;)Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;
    //   170: astore 9
    //   172: aload_3
    //   173: astore 5
    //   175: aload 6
    //   177: astore 4
    //   179: aload 9
    //   181: invokevirtual 341	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:getId	()Ljava/lang/String;
    //   184: astore 6
    //   186: aload_3
    //   187: astore 5
    //   189: aload 6
    //   191: astore 4
    //   193: aload 9
    //   195: invokevirtual 345	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:isLimitAdTrackingEnabled	()Z
    //   198: ifeq +623 -> 821
    //   201: ldc_w 347
    //   204: astore_3
    //   205: aload_3
    //   206: astore 5
    //   208: aload 6
    //   210: astore 4
    //   212: aload_1
    //   213: invokeinterface 351 1 0
    //   218: astore_1
    //   219: aload_3
    //   220: astore 5
    //   222: aload 6
    //   224: astore 4
    //   226: aload_1
    //   227: ldc_w 314
    //   230: aload 6
    //   232: invokeinterface 357 3 0
    //   237: pop
    //   238: aload_3
    //   239: astore 5
    //   241: aload 6
    //   243: astore 4
    //   245: aload_1
    //   246: ldc_w 316
    //   249: aload_3
    //   250: invokeinterface 357 3 0
    //   255: pop
    //   256: aload_3
    //   257: astore 5
    //   259: aload 6
    //   261: astore 4
    //   263: aload_1
    //   264: invokeinterface 360 1 0
    //   269: aload_3
    //   270: astore 5
    //   272: aload 6
    //   274: astore 4
    //   276: ldc_w 362
    //   279: iconst_1
    //   280: anewarray 4	java/lang/Object
    //   283: dup
    //   284: iconst_0
    //   285: aload 6
    //   287: aastore
    //   288: invokestatic 239	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   291: invokestatic 365	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
    //   294: aload 6
    //   296: astore_1
    //   297: aload_1
    //   298: ifnonnull +577 -> 875
    //   301: aload_0
    //   302: getfield 77	com/appodeal/ads/y:b	Landroid/content/Context;
    //   305: invokestatic 370	com/appodeal/ads/az:l	(Landroid/content/Context;)Ljava/lang/String;
    //   308: astore_1
    //   309: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   312: ldc_w 372
    //   315: aload 8
    //   317: invokevirtual 376	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   320: pop
    //   321: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   324: ldc_w 378
    //   327: getstatic 383	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   330: invokevirtual 376	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   333: pop
    //   334: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   337: ldc_w 385
    //   340: getstatic 388	android/os/Build$VERSION:SDK_INT	I
    //   343: invokevirtual 391	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   346: pop
    //   347: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   350: ldc_w 393
    //   353: ldc_w 395
    //   356: invokevirtual 376	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   359: pop
    //   360: aload_0
    //   361: getfield 77	com/appodeal/ads/y:b	Landroid/content/Context;
    //   364: invokevirtual 398	android/content/Context:getPackageName	()Ljava/lang/String;
    //   367: astore 4
    //   369: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   372: ldc_w 400
    //   375: aload 4
    //   377: invokevirtual 376	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   380: pop
    //   381: aload 7
    //   383: aload 4
    //   385: iconst_0
    //   386: invokevirtual 406	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   389: astore 5
    //   391: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   394: ldc_w 408
    //   397: aload 5
    //   399: getfield 413	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   402: invokevirtual 376	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   405: pop
    //   406: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   409: ldc_w 415
    //   412: aload 5
    //   414: getfield 418	android/content/pm/PackageInfo:versionCode	I
    //   417: invokevirtual 391	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   420: pop
    //   421: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   424: ldc_w 420
    //   427: aload 5
    //   429: getfield 423	android/content/pm/PackageInfo:firstInstallTime	J
    //   432: ldc2_w 424
    //   435: ldiv
    //   436: invokevirtual 428	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   439: pop
    //   440: aload 7
    //   442: aload 4
    //   444: iconst_0
    //   445: invokevirtual 432	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   448: astore 5
    //   450: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   453: ldc_w 434
    //   456: aload 5
    //   458: getfield 439	android/content/pm/ApplicationInfo:targetSdkVersion	I
    //   461: invokevirtual 391	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   464: pop
    //   465: getstatic 441	com/appodeal/ads/Appodeal:j	Ljava/lang/String;
    //   468: ifnull +16 -> 484
    //   471: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   474: ldc_w 443
    //   477: getstatic 441	com/appodeal/ads/Appodeal:j	Ljava/lang/String;
    //   480: invokevirtual 376	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   483: pop
    //   484: getstatic 444	com/appodeal/ads/Appodeal:k	Ljava/lang/String;
    //   487: ifnull +16 -> 503
    //   490: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   493: ldc_w 446
    //   496: getstatic 444	com/appodeal/ads/Appodeal:k	Ljava/lang/String;
    //   499: invokevirtual 376	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   502: pop
    //   503: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   506: ldc_w 448
    //   509: aload_1
    //   510: invokevirtual 376	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   513: pop
    //   514: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   517: ldc_w 450
    //   520: aload_3
    //   521: invokevirtual 376	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   524: pop
    //   525: aload_0
    //   526: getfield 77	com/appodeal/ads/y:b	Landroid/content/Context;
    //   529: invokestatic 453	com/appodeal/ads/az:f	(Landroid/content/Context;)Landroid/util/Pair;
    //   532: astore_1
    //   533: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   536: ldc_w 455
    //   539: aload_1
    //   540: getfield 461	android/util/Pair:first	Ljava/lang/Object;
    //   543: invokevirtual 376	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   546: pop
    //   547: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   550: ldc_w 463
    //   553: aload_1
    //   554: getfield 466	android/util/Pair:second	Ljava/lang/Object;
    //   557: invokevirtual 376	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   560: pop
    //   561: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   564: ldc_w 468
    //   567: aload_0
    //   568: getfield 77	com/appodeal/ads/y:b	Landroid/content/Context;
    //   571: invokestatic 471	com/appodeal/ads/az:i	(Landroid/content/Context;)F
    //   574: f2d
    //   575: invokevirtual 474	org/json/JSONObject:put	(Ljava/lang/String;D)Lorg/json/JSONObject;
    //   578: pop
    //   579: aload_0
    //   580: getfield 77	com/appodeal/ads/y:b	Landroid/content/Context;
    //   583: invokestatic 477	com/appodeal/ads/az:n	(Landroid/content/Context;)Z
    //   586: ifeq +260 -> 846
    //   589: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   592: ldc_w 479
    //   595: ldc_w 481
    //   598: invokevirtual 376	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   601: pop
    //   602: getstatic 486	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   605: ldc_w 488
    //   608: invokevirtual 175	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   611: ifeq +276 -> 887
    //   614: ldc_w 490
    //   617: astore_1
    //   618: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   621: ldc_w 492
    //   624: aload_1
    //   625: invokevirtual 376	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   628: pop
    //   629: aload 7
    //   631: aload 4
    //   633: invokevirtual 495	android/content/pm/PackageManager:getInstallerPackageName	(Ljava/lang/String;)Ljava/lang/String;
    //   636: astore_3
    //   637: aload_3
    //   638: astore_1
    //   639: aload_3
    //   640: ifnonnull +7 -> 647
    //   643: ldc_w 497
    //   646: astore_1
    //   647: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   650: ldc_w 499
    //   653: aload_1
    //   654: invokevirtual 376	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   657: pop
    //   658: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   661: ldc_w 501
    //   664: getstatic 486	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   667: invokevirtual 376	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   670: pop
    //   671: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   674: ldc_w 503
    //   677: invokestatic 505	com/appodeal/ads/az:a	()Z
    //   680: invokevirtual 508	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   683: pop
    //   684: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   687: ldc_w 510
    //   690: aload_0
    //   691: getfield 77	com/appodeal/ads/y:b	Landroid/content/Context;
    //   694: invokestatic 512	com/appodeal/ads/az:t	(Landroid/content/Context;)Ljava/lang/String;
    //   697: invokevirtual 376	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   700: pop
    //   701: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   704: ldc_w 514
    //   707: iconst_1
    //   708: anewarray 171	java/lang/String
    //   711: dup
    //   712: iconst_0
    //   713: ldc_w 516
    //   716: aastore
    //   717: invokestatic 519	com/appodeal/ads/az:b	([Ljava/lang/String;)Z
    //   720: invokevirtual 508	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   723: pop
    //   724: getstatic 523	com/appodeal/ads/f:h	Z
    //   727: ifne +35 -> 762
    //   730: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   733: ldc_w 525
    //   736: ldc_w 527
    //   739: iconst_2
    //   740: anewarray 4	java/lang/Object
    //   743: dup
    //   744: iconst_0
    //   745: getstatic 486	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   748: aastore
    //   749: dup
    //   750: iconst_1
    //   751: getstatic 530	android/os/Build:MODEL	Ljava/lang/String;
    //   754: aastore
    //   755: invokestatic 239	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   758: invokevirtual 376	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   761: pop
    //   762: new 249	org/json/JSONObject
    //   765: dup
    //   766: invokespecial 298	org/json/JSONObject:<init>	()V
    //   769: astore_1
    //   770: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   773: invokevirtual 534	org/json/JSONObject:keys	()Ljava/util/Iterator;
    //   776: astore_3
    //   777: aload_3
    //   778: invokeinterface 539 1 0
    //   783: ifeq +87 -> 870
    //   786: aload_3
    //   787: invokeinterface 543 1 0
    //   792: checkcast 171	java/lang/String
    //   795: astore 4
    //   797: aload_1
    //   798: aload 4
    //   800: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   803: aload 4
    //   805: invokevirtual 547	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   808: invokevirtual 376	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   811: pop
    //   812: goto -35 -> 777
    //   815: astore_1
    //   816: ldc 2
    //   818: monitorexit
    //   819: aload_1
    //   820: athrow
    //   821: ldc_w 549
    //   824: astore_3
    //   825: goto -620 -> 205
    //   828: astore_1
    //   829: aload_1
    //   830: invokestatic 282	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   833: goto +45 -> 878
    //   836: astore 5
    //   838: aload 5
    //   840: invokestatic 282	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   843: goto -378 -> 465
    //   846: getstatic 296	com/appodeal/ads/y:r	Lorg/json/JSONObject;
    //   849: ldc_w 479
    //   852: ldc_w 551
    //   855: invokevirtual 376	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   858: pop
    //   859: goto -257 -> 602
    //   862: astore_1
    //   863: aload_1
    //   864: invokestatic 282	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   867: goto -209 -> 658
    //   870: ldc 2
    //   872: monitorexit
    //   873: aload_1
    //   874: areturn
    //   875: goto -566 -> 309
    //   878: aload 4
    //   880: astore_1
    //   881: aload 5
    //   883: astore_3
    //   884: goto -587 -> 297
    //   887: ldc_w 553
    //   890: astore_1
    //   891: goto -273 -> 618
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	894	0	this	y
    //   0	894	1	paramSharedPreferences	SharedPreferences
    //   103	9	2	bool	boolean
    //   81	803	3	localObject1	Object
    //   87	792	4	str1	String
    //   83	374	5	localObject2	Object
    //   836	46	5	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   69	226	6	str2	String
    //   35	595	7	localPackageManager	PackageManager
    //   47	269	8	str3	String
    //   170	24	9	localInfo	com.google.android.gms.ads.identifier.AdvertisingIdClient.Info
    // Exception table:
    //   from	to	target	type
    //   3	18	815	finally
    //   18	49	815	finally
    //   54	57	815	finally
    //   59	82	815	finally
    //   94	104	815	finally
    //   122	129	815	finally
    //   136	156	815	finally
    //   163	172	815	finally
    //   179	186	815	finally
    //   193	201	815	finally
    //   212	219	815	finally
    //   226	238	815	finally
    //   245	256	815	finally
    //   263	269	815	finally
    //   276	294	815	finally
    //   301	309	815	finally
    //   309	381	815	finally
    //   381	465	815	finally
    //   465	484	815	finally
    //   484	503	815	finally
    //   503	602	815	finally
    //   602	614	815	finally
    //   618	629	815	finally
    //   629	637	815	finally
    //   647	658	815	finally
    //   658	762	815	finally
    //   762	777	815	finally
    //   777	812	815	finally
    //   816	819	815	finally
    //   829	833	815	finally
    //   838	843	815	finally
    //   846	859	815	finally
    //   863	867	815	finally
    //   870	873	815	finally
    //   122	129	828	java/lang/Exception
    //   136	156	828	java/lang/Exception
    //   163	172	828	java/lang/Exception
    //   179	186	828	java/lang/Exception
    //   193	201	828	java/lang/Exception
    //   212	219	828	java/lang/Exception
    //   226	238	828	java/lang/Exception
    //   245	256	828	java/lang/Exception
    //   263	269	828	java/lang/Exception
    //   276	294	828	java/lang/Exception
    //   381	465	836	android/content/pm/PackageManager$NameNotFoundException
    //   629	637	862	java/lang/Exception
    //   647	658	862	java/lang/Exception
  }
  
  private SSLSocketFactory d()
  {
    try
    {
      if (C == null)
      {
        localObject1 = new ArrayList();
        ((List)localObject1).add("A1ABC1296E644B3A25179FCD3E277C8D36039BEE94478E2F5104FA4244237F54");
        ((List)localObject1).add("E91093227F02CE854C3214749DC7FB3459E0E43E80CAE27F01AA0EA92894C9E1");
        localObject1 = new com.appodeal.ads.utils.f((List)localObject1, 1526342400000L);
        SSLContext localSSLContext = SSLContext.getInstance("TLSv1");
        localSSLContext.init(null, new TrustManager[] { localObject1 }, null);
        C = localSSLContext.getSocketFactory();
      }
      Object localObject1 = C;
      return localObject1;
    }
    finally {}
  }
  
  @Nullable
  @VisibleForTesting
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
        float f1 = az.g(this.b);
        float f2 = az.h(this.b);
        if ((i.q) && (f1 >= 728.0F) && (f2 > 720.0F)) {
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
      localObject1 = az.b(this.b);
      localJSONObject.put("battery", az.k(this.b));
      localJSONObject.put("crr", az.c(this.b));
      localJSONObject.put("locale", Locale.getDefault().toString());
      localObject2 = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.ENGLISH);
      localJSONObject.put("timezone", new SimpleDateFormat("Z", Locale.ENGLISH).format(((Calendar)localObject2).getTime()));
      localJSONObject.put("local_time", System.currentTimeMillis() / 1000L);
      localJSONObject.put("user_agent", System.getProperty("http.agent"));
      e.c(this.b);
      localJSONObject.put("session_id", e.a(paramSharedPreferences));
      localJSONObject.put("session_uptime", e.b());
      localJSONObject.put("app_uptime", e.b(paramSharedPreferences));
      if (!f.h)
      {
        localJSONObject.put("connection", ((az.a)localObject1).a);
        localJSONObject.put("connection_subtype", ((az.a)localObject1).b);
        localJSONObject.put("connection_fast", ((az.a)localObject1).c);
        localObject1 = az.d(this.b);
        localJSONObject.put("lt", ((Pair)localObject1).first);
        localJSONObject.put("lat", ((Pair)((Pair)localObject1).second).first);
        localJSONObject.put("lon", ((Pair)((Pair)localObject1).second).second);
      }
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
          localObject1 = new JSONArray(ar.o);
        }
        if (this.w) {
          localObject1 = new JSONArray(av.t);
        }
        if (this.t) {
          localObject1 = new JSONArray(i.A);
        }
        if (this.u) {
          localObject1 = new JSONArray(ab.x);
        }
        if (this.x) {
          localObject1 = new JSONArray(Native.t);
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
      if ((this.z) && (h.p == null)) {
        localJSONObject.put("check_sdk_version", true);
      }
      if (this.i != null) {
        localJSONObject.put("placement_id", this.i.b());
      }
      if ((this.B != null) && (this.d.equals("stats"))) {
        localJSONObject.put("ad_unit_stat", this.B);
      }
    }
    catch (JSONException localException2)
    {
      try
      {
        localJSONObject.put("sa", z.a(this.b));
        if (f.h) {
          break label1127;
        }
      }
      catch (Exception localException2)
      {
        try
        {
          for (;;)
          {
            Object localObject2;
            localJSONObject.put("user_settings", az.u(this.b).k());
            Object localObject1 = paramSharedPreferences.edit();
            ((SharedPreferences.Editor)localObject1).putLong("lastSettingsTime", System.currentTimeMillis());
            ((SharedPreferences.Editor)localObject1).putBoolean("should_update_user_settings", false);
            ((SharedPreferences.Editor)localObject1).apply();
            UserSettings.a = false;
            localObject1 = Calendar.getInstance();
            ((Calendar)localObject1).setTimeInMillis(paramSharedPreferences.getLong("lastAppTime", 0L));
            ((Calendar)localObject1).add(5, 1);
            if ((!h.l) && (h.k) && (this.z) && (((Calendar)localObject1).getTimeInMillis() < System.currentTimeMillis()))
            {
              h.l = true;
              try
              {
                localObject1 = new JSONArray();
                Object localObject3 = this.b.getPackageManager().getInstalledApplications(0);
                localObject2 = Pattern.compile("^?(?:com\\.android|com\\.google|com\\.sec|com\\.samsung|com\\.sonyericsson|com\\.sonymobile|com\\.motorola|com\\.htc).*$");
                if (localObject3 == null) {
                  break;
                }
                localObject3 = ((List)localObject3).iterator();
                while (((Iterator)localObject3).hasNext())
                {
                  String str = ((ApplicationInfo)((Iterator)localObject3).next()).packageName;
                  if ((!((Pattern)localObject2).matcher(str).matches()) && (!str.equals("android"))) {
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
              h.l = false;
            }
            if (this.h != null)
            {
              if (!this.d.equals("stats")) {
                break label1460;
              }
              localJSONObject.put("id", this.h.a().toString());
            }
            return localJSONObject;
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
    if (this.G) {
      r.a.execute(new b(null));
    }
  }
  
  @VisibleForTesting
  void a(SharedPreferences paramSharedPreferences, int paramInt, String paramString)
  {
    paramSharedPreferences = paramSharedPreferences.edit();
    paramSharedPreferences.putInt(b(paramString), paramInt);
    paramSharedPreferences.apply();
  }
  
  @VisibleForTesting
  void a(SharedPreferences paramSharedPreferences, String paramString1, String paramString2)
  {
    paramSharedPreferences = paramSharedPreferences.edit();
    paramSharedPreferences.putString(paramString1, paramString2);
    paramSharedPreferences.putLong(a(paramString1), System.currentTimeMillis());
    paramSharedPreferences.apply();
  }
  
  @VisibleForTesting
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
  
  @VisibleForTesting
  int b(SharedPreferences paramSharedPreferences, String paramString)
  {
    return paramSharedPreferences.getInt(b(paramString), 86400000);
  }
  
  URL b()
  {
    if (this.A)
    {
      if (this.o) {
        return az.f(this.d);
      }
      return new URL(com.appodeal.ads.utils.h.a("get"));
    }
    return new URL(com.appodeal.ads.utils.h.a(this.d));
  }
  
  @VisibleForTesting
  JSONObject c()
  {
    JSONObject localJSONObject = new JSONObject();
    int i1 = n.a();
    int i2 = n.b();
    int i3 = n.c();
    try
    {
      localJSONObject.put("show", i1);
      localJSONObject.put("click", i2);
      if ((this.v) || (this.w) || ((this.k != null) && ((this.k.equals("video")) || (this.k.equals("rewarded_video"))))) {
        localJSONObject.put("finish", i3);
      }
      if ((this.s) || ((this.k != null) && (this.k.equals("banner"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "show" }), n.a("interstitial"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "click" }), n.b("interstitial"));
      }
      if ((this.v) || ((this.k != null) && (this.k.equals("video"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "show" }), n.a("video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "click" }), n.b("video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "finish" }), n.c("video"));
      }
      if ((this.w) || ((this.k != null) && (this.k.equals("rewarded_video"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "show" }), n.a("rewarded_video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "click" }), n.b("rewarded_video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "finish" }), n.c("rewarded_video"));
      }
      if ((this.t) || ((this.k != null) && (this.k.equals("banner_320"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "show" }), n.a("banner"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "click" }), n.b("banner"));
      }
      if ((this.u) || ((this.k != null) && (this.k.equals("banner_mrec"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "show" }), n.a("mrec"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "click" }), n.b("mrec"));
      }
      if ((this.x) || ((this.k != null) && (this.k.equals("native"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "native", "show" }), n.a("native"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "native", "click" }), n.b("native"));
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
      //   107: ifeq +458 -> 565
      //   110: aload 6
      //   112: aload_0
      //   113: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   116: getfield 92	com/appodeal/ads/y:d	Ljava/lang/String;
      //   119: invokeinterface 98 2 0
      //   124: ifeq +441 -> 565
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
      //   222: invokestatic 164	com/appodeal/ads/az:a	(Ljava/io/OutputStream;Ljava/lang/String;)V
      //   225: aload_3
      //   226: invokevirtual 168	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
      //   229: invokestatic 171	com/appodeal/ads/az:a	(Ljava/io/InputStream;)Ljava/lang/String;
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
      //   280: ifeq +618 -> 898
      //   283: aload 4
      //   285: astore_2
      //   286: aload_3
      //   287: ifnull +608 -> 895
      //   290: aload_3
      //   291: instanceof 77
      //   294: ifeq +409 -> 703
      //   297: aload_3
      //   298: checkcast 77	javax/net/ssl/HttpsURLConnection
      //   301: invokevirtual 182	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   304: aload_2
      //   305: ifnonnull +502 -> 807
      //   308: aload_0
      //   309: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   312: getfield 88	com/appodeal/ads/y:A	Z
      //   315: ifeq +479 -> 794
      //   318: aload 6
      //   320: aload_0
      //   321: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   324: getfield 92	com/appodeal/ads/y:d	Ljava/lang/String;
      //   327: invokeinterface 98 2 0
      //   332: ifeq +462 -> 794
      //   335: aload_0
      //   336: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   339: aload 6
      //   341: aload_0
      //   342: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   345: getfield 92	com/appodeal/ads/y:d	Ljava/lang/String;
      //   348: invokevirtual 185	com/appodeal/ads/y:a	(Landroid/content/SharedPreferences;Ljava/lang/String;)Z
      //   351: ifeq +443 -> 794
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
      //   399: ifeq +442 -> 841
      //   402: aload_3
      //   403: getstatic 210	com/appodeal/ads/utils/Log$LogLevel:verbose	Lcom/appodeal/ads/utils/Log$LogLevel;
      //   406: invokestatic 213	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;Lcom/appodeal/ads/utils/Log$LogLevel;)V
      //   409: aload_0
      //   410: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   413: getfield 88	com/appodeal/ads/y:A	Z
      //   416: ifeq +105 -> 521
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
      //   454: aload_2
      //   455: ldc -23
      //   457: invokevirtual 231	org/json/JSONObject:optBoolean	(Ljava/lang/String;)Z
      //   460: invokestatic 238	com/appodeal/ads/f:a	(Landroid/content/Context;Lorg/json/JSONObject;ZZ)V
      //   463: aload_0
      //   464: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   467: aload 6
      //   469: aload_2
      //   470: ldc -16
      //   472: ldc -15
      //   474: invokevirtual 245	org/json/JSONObject:optInt	(Ljava/lang/String;I)I
      //   477: aload_0
      //   478: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   481: getfield 92	com/appodeal/ads/y:d	Ljava/lang/String;
      //   484: invokevirtual 248	com/appodeal/ads/y:a	(Landroid/content/SharedPreferences;ILjava/lang/String;)V
      //   487: aload_0
      //   488: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   491: aload_2
      //   492: invokestatic 251	com/appodeal/ads/y:a	(Lcom/appodeal/ads/y;Lorg/json/JSONObject;)V
      //   495: aload_2
      //   496: ldc -3
      //   498: invokevirtual 257	org/json/JSONObject:optJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   501: invokestatic 262	com/appodeal/ads/f/d:a	(Lorg/json/JSONArray;)V
      //   504: invokestatic 265	com/appodeal/ads/f/d:c	()V
      //   507: getstatic 269	com/appodeal/ads/Appodeal:g	Lcom/appodeal/ads/utils/t;
      //   510: ifnull +11 -> 521
      //   513: getstatic 269	com/appodeal/ads/Appodeal:g	Lcom/appodeal/ads/utils/t;
      //   516: invokeinterface 273 1 0
      //   521: aload_0
      //   522: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   525: invokestatic 46	com/appodeal/ads/y:a	(Lcom/appodeal/ads/y;)Landroid/os/Handler;
      //   528: iconst_1
      //   529: aload_2
      //   530: invokevirtual 277	android/os/Handler:obtainMessage	(ILjava/lang/Object;)Landroid/os/Message;
      //   533: astore_2
      //   534: aload_0
      //   535: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   538: invokestatic 46	com/appodeal/ads/y:a	(Lcom/appodeal/ads/y;)Landroid/os/Handler;
      //   541: aload_2
      //   542: invokevirtual 281	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   545: pop
      //   546: return
      //   547: astore_2
      //   548: aload_2
      //   549: invokestatic 197	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   552: aload_0
      //   553: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   556: invokestatic 46	com/appodeal/ads/y:a	(Lcom/appodeal/ads/y;)Landroid/os/Handler;
      //   559: iconst_0
      //   560: invokevirtual 52	android/os/Handler:sendEmptyMessage	(I)Z
      //   563: pop
      //   564: return
      //   565: aload_3
      //   566: sipush 20000
      //   569: invokevirtual 104	java/net/URLConnection:setConnectTimeout	(I)V
      //   572: aload_3
      //   573: sipush 20000
      //   576: invokevirtual 107	java/net/URLConnection:setReadTimeout	(I)V
      //   579: goto -438 -> 141
      //   582: astore 4
      //   584: aload_3
      //   585: astore_2
      //   586: aload 4
      //   588: astore_3
      //   589: aload_3
      //   590: invokestatic 197	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   593: aload_3
      //   594: invokevirtual 284	java/io/IOException:getMessage	()Ljava/lang/String;
      //   597: ldc_w 286
      //   600: invokevirtual 75	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   603: ifne +16 -> 619
      //   606: aload_3
      //   607: invokevirtual 284	java/io/IOException:getMessage	()Ljava/lang/String;
      //   610: ldc_w 288
      //   613: invokevirtual 75	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   616: ifeq +119 -> 735
      //   619: aload_0
      //   620: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   623: invokestatic 46	com/appodeal/ads/y:a	(Lcom/appodeal/ads/y;)Landroid/os/Handler;
      //   626: iconst_0
      //   627: invokevirtual 52	android/os/Handler:sendEmptyMessage	(I)Z
      //   630: pop
      //   631: aload_2
      //   632: ifnull +272 -> 904
      //   635: aload_2
      //   636: instanceof 77
      //   639: ifeq +81 -> 720
      //   642: aload_2
      //   643: checkcast 77	javax/net/ssl/HttpsURLConnection
      //   646: invokevirtual 182	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   649: return
      //   650: astore_2
      //   651: aload_2
      //   652: invokestatic 197	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   655: goto -448 -> 207
      //   658: astore 4
      //   660: aload_3
      //   661: astore_2
      //   662: aload 4
      //   664: astore_3
      //   665: aload_2
      //   666: ifnull +17 -> 683
      //   669: aload_2
      //   670: instanceof 77
      //   673: ifeq +104 -> 777
      //   676: aload_2
      //   677: checkcast 77	javax/net/ssl/HttpsURLConnection
      //   680: invokevirtual 182	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   683: aload_3
      //   684: athrow
      //   685: astore_2
      //   686: aload 7
      //   688: invokevirtual 145	java/util/zip/GZIPOutputStream:close	()V
      //   691: aload_2
      //   692: athrow
      //   693: astore 4
      //   695: aload 4
      //   697: invokestatic 197	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   700: goto -9 -> 691
      //   703: aload_3
      //   704: instanceof 290
      //   707: ifeq +188 -> 895
      //   710: aload_3
      //   711: checkcast 290	java/net/HttpURLConnection
      //   714: invokevirtual 291	java/net/HttpURLConnection:disconnect	()V
      //   717: goto -413 -> 304
      //   720: aload_2
      //   721: instanceof 290
      //   724: ifeq +180 -> 904
      //   727: aload_2
      //   728: checkcast 290	java/net/HttpURLConnection
      //   731: invokevirtual 291	java/net/HttpURLConnection:disconnect	()V
      //   734: return
      //   735: aload_2
      //   736: ifnull +154 -> 890
      //   739: aload_2
      //   740: instanceof 77
      //   743: ifeq +15 -> 758
      //   746: aload_2
      //   747: checkcast 77	javax/net/ssl/HttpsURLConnection
      //   750: invokevirtual 182	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   753: aconst_null
      //   754: astore_2
      //   755: goto -451 -> 304
      //   758: aload_2
      //   759: instanceof 290
      //   762: ifeq +128 -> 890
      //   765: aload_2
      //   766: checkcast 290	java/net/HttpURLConnection
      //   769: invokevirtual 291	java/net/HttpURLConnection:disconnect	()V
      //   772: aconst_null
      //   773: astore_2
      //   774: goto -470 -> 304
      //   777: aload_2
      //   778: instanceof 290
      //   781: ifeq -98 -> 683
      //   784: aload_2
      //   785: checkcast 290	java/net/HttpURLConnection
      //   788: invokevirtual 291	java/net/HttpURLConnection:disconnect	()V
      //   791: goto -108 -> 683
      //   794: aload_0
      //   795: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   798: invokestatic 46	com/appodeal/ads/y:a	(Lcom/appodeal/ads/y;)Landroid/os/Handler;
      //   801: iconst_0
      //   802: invokevirtual 52	android/os/Handler:sendEmptyMessage	(I)Z
      //   805: pop
      //   806: return
      //   807: aload_2
      //   808: astore_3
      //   809: aload_0
      //   810: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   813: getfield 88	com/appodeal/ads/y:A	Z
      //   816: ifeq -433 -> 383
      //   819: aload_0
      //   820: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   823: aload 6
      //   825: aload_0
      //   826: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   829: getfield 92	com/appodeal/ads/y:d	Ljava/lang/String;
      //   832: aload_2
      //   833: invokevirtual 294	com/appodeal/ads/y:a	(Landroid/content/SharedPreferences;Ljava/lang/String;Ljava/lang/String;)V
      //   836: aload_2
      //   837: astore_3
      //   838: goto -455 -> 383
      //   841: aload_3
      //   842: invokestatic 296	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   845: goto -436 -> 409
      //   848: astore_2
      //   849: aload_2
      //   850: invokestatic 197	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   853: aload_0
      //   854: getfield 15	com/appodeal/ads/y$b:a	Lcom/appodeal/ads/y;
      //   857: invokestatic 46	com/appodeal/ads/y:a	(Lcom/appodeal/ads/y;)Landroid/os/Handler;
      //   860: iconst_0
      //   861: invokevirtual 52	android/os/Handler:sendEmptyMessage	(I)Z
      //   864: pop
      //   865: return
      //   866: astore_3
      //   867: aload_3
      //   868: invokestatic 197	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   871: goto -350 -> 521
      //   874: astore_3
      //   875: aconst_null
      //   876: astore_2
      //   877: goto -212 -> 665
      //   880: astore_3
      //   881: goto -216 -> 665
      //   884: astore_3
      //   885: aconst_null
      //   886: astore_2
      //   887: goto -298 -> 589
      //   890: aconst_null
      //   891: astore_2
      //   892: goto -588 -> 304
      //   895: goto -591 -> 304
      //   898: aload 5
      //   900: astore_2
      //   901: goto -615 -> 286
      //   904: return
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	905	0	this	b
      //   278	2	1	bool	boolean
      //   16	526	2	localObject1	Object
      //   547	2	2	localException1	Exception
      //   585	58	2	localObject2	Object
      //   650	2	2	localException2	Exception
      //   661	16	2	localObject3	Object
      //   685	62	2	localObject4	Object
      //   754	83	2	str	String
      //   848	2	2	localJSONException	JSONException
      //   876	25	2	localObject5	Object
      //   72	770	3	localObject6	Object
      //   866	2	3	localException3	Exception
      //   874	1	3	localObject7	Object
      //   880	1	3	localObject8	Object
      //   884	1	3	localIOException1	java.io.IOException
      //   1	283	4	localObject9	Object
      //   582	5	4	localIOException2	java.io.IOException
      //   658	5	4	localObject10	Object
      //   693	3	4	localException4	Exception
      //   50	849	5	localObject11	Object
      //   65	759	6	localSharedPreferences	SharedPreferences
      //   186	501	7	localGZIPOutputStream	java.util.zip.GZIPOutputStream
      // Exception table:
      //   from	to	target	type
      //   3	26	547	java/lang/Exception
      //   30	42	547	java/lang/Exception
      //   43	67	547	java/lang/Exception
      //   290	304	547	java/lang/Exception
      //   308	383	547	java/lang/Exception
      //   383	409	547	java/lang/Exception
      //   521	546	547	java/lang/Exception
      //   635	649	547	java/lang/Exception
      //   669	683	547	java/lang/Exception
      //   683	685	547	java/lang/Exception
      //   703	717	547	java/lang/Exception
      //   720	734	547	java/lang/Exception
      //   739	753	547	java/lang/Exception
      //   758	772	547	java/lang/Exception
      //   777	791	547	java/lang/Exception
      //   794	806	547	java/lang/Exception
      //   809	836	547	java/lang/Exception
      //   841	845	547	java/lang/Exception
      //   849	865	547	java/lang/Exception
      //   867	871	547	java/lang/Exception
      //   73	100	582	java/io/IOException
      //   100	141	582	java/io/IOException
      //   141	188	582	java/io/IOException
      //   202	207	582	java/io/IOException
      //   207	234	582	java/io/IOException
      //   245	253	582	java/io/IOException
      //   256	279	582	java/io/IOException
      //   565	579	582	java/io/IOException
      //   651	655	582	java/io/IOException
      //   686	691	582	java/io/IOException
      //   691	693	582	java/io/IOException
      //   695	700	582	java/io/IOException
      //   202	207	650	java/lang/Exception
      //   73	100	658	finally
      //   100	141	658	finally
      //   141	188	658	finally
      //   202	207	658	finally
      //   207	234	658	finally
      //   245	253	658	finally
      //   256	279	658	finally
      //   565	579	658	finally
      //   651	655	658	finally
      //   686	691	658	finally
      //   691	693	658	finally
      //   695	700	658	finally
      //   188	202	685	finally
      //   686	691	693	java/lang/Exception
      //   383	409	848	org/json/JSONException
      //   841	845	848	org/json/JSONException
      //   409	521	866	java/lang/Exception
      //   67	73	874	finally
      //   589	619	880	finally
      //   619	631	880	finally
      //   67	73	884	java/io/IOException
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
    private JSONObject r;
    
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
    
    public c b(JSONObject paramJSONObject)
    {
      this.r = paramJSONObject;
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
