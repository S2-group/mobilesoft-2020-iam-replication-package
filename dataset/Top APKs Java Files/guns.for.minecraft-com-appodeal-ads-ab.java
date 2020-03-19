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
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.util.Pair;
import com.appodeal.ads.f.d;
import com.appodeal.ads.utils.af;
import com.appodeal.ads.utils.f;
import com.appodeal.ads.utils.j;
import com.appodeal.ads.utils.p;
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

public class ab
{
  private static SSLSocketFactory F;
  @VisibleForTesting
  static JSONObject s;
  @VisibleForTesting
  boolean A;
  @VisibleForTesting
  boolean B;
  @VisibleForTesting
  String C;
  @VisibleForTesting
  double D;
  private final JSONObject E;
  private final Handler G;
  private final int H = 0;
  private final int I = 1;
  private boolean J;
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
  String f;
  @VisibleForTesting
  String g;
  @VisibleForTesting
  final com.appodeal.ads.d.h h;
  @VisibleForTesting
  final d i;
  @VisibleForTesting
  long j;
  @VisibleForTesting
  final String k;
  @VisibleForTesting
  Long l;
  @VisibleForTesting
  final int m;
  @VisibleForTesting
  final double n;
  @VisibleForTesting
  final boolean o;
  @VisibleForTesting
  String p;
  @VisibleForTesting
  JSONObject q;
  @VisibleForTesting
  String r;
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
  
  private ab(c paramC)
  {
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
    String str;
    if (c.o(paramC) != null)
    {
      this.q = c.o(paramC).F;
      if ((c.o(paramC).v) && (c.o(paramC).w == null))
      {
        str = c.o(paramC).E;
        this.p = str;
        this.l = c.o(paramC).C;
        this.j = c.o(paramC).i();
        this.g = c.o(paramC).w;
        this.f = c.o(paramC).m;
        this.r = c.o(paramC).l();
      }
    }
    else
    {
      this.G = new Handler(Looper.getMainLooper())
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          if (ab.this.a != null) {}
          switch (paramAnonymousMessage.what)
          {
          default: 
            return;
          case 1: 
            paramAnonymousMessage = (JSONObject)paramAnonymousMessage.obj;
            if (paramAnonymousMessage == null)
            {
              ab.this.a.a(ab.this.c);
              return;
            }
            ab.this.a.a(paramAnonymousMessage, ab.this.c, ab.this.d);
            return;
          }
          ab.this.a.a(ab.this.c);
        }
      };
      if (this.d != null) {
        break label265;
      }
    }
    label265:
    while ((k.a) && ((this.a == null) || ((this.a instanceof af))) && ((this.d.equals("init")) || (this.d.equals("stats")) || (this.d.equals("show")) || (this.d.equals("click")) || (this.d.equals("finish")) || (this.d.equals("install"))))
    {
      return;
      str = null;
      break;
    }
    if ((this.d.equals("banner")) || (this.d.equals("debug")))
    {
      bool1 = true;
      this.t = bool1;
      if ((!this.d.equals("banner_320")) && (!this.d.equals("debug_banner_320"))) {
        break label790;
      }
      bool1 = true;
      label417:
      this.u = bool1;
      if ((!this.d.equals("banner_mrec")) && (!this.d.equals("debug_mrec"))) {
        break label795;
      }
      bool1 = true;
      label448:
      this.v = bool1;
      if ((!this.d.equals("video")) && (!this.d.equals("debug_video"))) {
        break label800;
      }
      bool1 = true;
      label479:
      this.w = bool1;
      if ((!this.d.equals("rewarded_video")) && (!this.d.equals("debug_rewarded_video"))) {
        break label805;
      }
      bool1 = true;
      label510:
      this.x = bool1;
      if ((!this.d.equals("native")) && (!this.d.equals("debug_native"))) {
        break label810;
      }
      bool1 = true;
      label541:
      this.y = bool1;
      if ((!this.d.equals("debug")) && (!this.d.equals("debug_banner_320")) && (!this.d.equals("debug_video")) && (!this.d.equals("debug_rewarded_video")) && (!this.d.equals("debug_mrec")) && (!this.d.equals("debug_native"))) {
        break label815;
      }
      bool1 = true;
      label620:
      this.z = bool1;
      if ((!this.t) && (!this.u) && (!this.v) && (!this.w) && (!this.x) && (!this.y)) {
        break label820;
      }
    }
    label790:
    label795:
    label800:
    label805:
    label810:
    label815:
    label820:
    for (boolean bool1 = true;; bool1 = false)
    {
      this.A = bool1;
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
      this.B = bool1;
      this.J = true;
      return;
      bool1 = false;
      break;
      bool1 = false;
      break label417;
      bool1 = false;
      break label448;
      bool1 = false;
      break label479;
      bool1 = false;
      break label510;
      bool1 = false;
      break label541;
      bool1 = false;
      break label620;
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
      return;
      paramJSONObject = new com.appodeal.ads.f.h(this.b, paramJSONObject.optJSONObject("user_data"));
      paramJSONObject.b(localJSONArray);
      paramJSONObject = paramJSONObject.a(localJSONArray);
      if (paramJSONObject == null)
      {
        com.appodeal.ads.f.h.c();
        return;
      }
    } while (paramJSONObject.c() == com.appodeal.ads.f.h.a().c());
    try
    {
      paramJSONObject.a();
      com.appodeal.ads.f.h.a(paramJSONObject);
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
  private JSONObject c(SharedPreferences paramSharedPreferences)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   6: ifnull +12 -> 18
    //   9: getstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   12: invokevirtual 333	org/json/JSONObject:length	()I
    //   15: ifne +419 -> 434
    //   18: new 272	org/json/JSONObject
    //   21: dup
    //   22: invokespecial 334	org/json/JSONObject:<init>	()V
    //   25: putstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   28: aload_0
    //   29: getfield 78	com/appodeal/ads/ab:b	Landroid/content/Context;
    //   32: invokevirtual 340	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   35: astore_2
    //   36: aload_1
    //   37: ldc_w 342
    //   40: aconst_null
    //   41: invokeinterface 348 3 0
    //   46: astore_1
    //   47: aload_1
    //   48: ifnonnull +8 -> 56
    //   51: ldc 2
    //   53: monitorexit
    //   54: aconst_null
    //   55: areturn
    //   56: getstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   59: ldc_w 350
    //   62: aload_1
    //   63: invokevirtual 354	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   66: pop
    //   67: getstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   70: ldc_w 356
    //   73: getstatic 361	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   76: invokevirtual 354	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   79: pop
    //   80: getstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   83: ldc_w 363
    //   86: getstatic 366	android/os/Build$VERSION:SDK_INT	I
    //   89: invokevirtual 369	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   92: pop
    //   93: getstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   96: ldc_w 371
    //   99: ldc_w 373
    //   102: invokevirtual 354	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   105: pop
    //   106: aload_0
    //   107: getfield 78	com/appodeal/ads/ab:b	Landroid/content/Context;
    //   110: invokevirtual 376	android/content/Context:getPackageName	()Ljava/lang/String;
    //   113: astore_3
    //   114: getstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   117: ldc_w 378
    //   120: aload_3
    //   121: invokevirtual 354	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   124: pop
    //   125: aload_2
    //   126: aload_3
    //   127: iconst_0
    //   128: invokevirtual 384	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   131: astore_1
    //   132: getstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   135: ldc_w 386
    //   138: aload_1
    //   139: getfield 391	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   142: invokevirtual 354	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   145: pop
    //   146: getstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   149: ldc_w 393
    //   152: aload_1
    //   153: getfield 396	android/content/pm/PackageInfo:versionCode	I
    //   156: invokevirtual 369	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   159: pop
    //   160: getstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   163: ldc_w 398
    //   166: aload_1
    //   167: getfield 401	android/content/pm/PackageInfo:firstInstallTime	J
    //   170: ldc2_w 402
    //   173: ldiv
    //   174: invokevirtual 406	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
    //   177: pop
    //   178: aload_2
    //   179: aload_3
    //   180: iconst_0
    //   181: invokevirtual 410	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   184: astore_1
    //   185: getstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   188: ldc_w 412
    //   191: aload_1
    //   192: getfield 417	android/content/pm/ApplicationInfo:targetSdkVersion	I
    //   195: invokevirtual 369	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   198: pop
    //   199: getstatic 419	com/appodeal/ads/Appodeal:l	Ljava/lang/String;
    //   202: ifnull +16 -> 218
    //   205: getstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   208: ldc_w 421
    //   211: getstatic 419	com/appodeal/ads/Appodeal:l	Ljava/lang/String;
    //   214: invokevirtual 354	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   217: pop
    //   218: getstatic 422	com/appodeal/ads/Appodeal:m	Ljava/lang/String;
    //   221: ifnull +16 -> 237
    //   224: getstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   227: ldc_w 424
    //   230: getstatic 422	com/appodeal/ads/Appodeal:m	Ljava/lang/String;
    //   233: invokevirtual 354	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   236: pop
    //   237: aload_0
    //   238: getfield 78	com/appodeal/ads/ab:b	Landroid/content/Context;
    //   241: invokestatic 429	com/appodeal/ads/bf:f	(Landroid/content/Context;)Landroid/util/Pair;
    //   244: astore_1
    //   245: getstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   248: ldc_w 431
    //   251: aload_1
    //   252: getfield 437	android/util/Pair:first	Ljava/lang/Object;
    //   255: invokevirtual 354	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   258: pop
    //   259: getstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   262: ldc_w 439
    //   265: aload_1
    //   266: getfield 442	android/util/Pair:second	Ljava/lang/Object;
    //   269: invokevirtual 354	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   272: pop
    //   273: getstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   276: ldc_w 444
    //   279: aload_0
    //   280: getfield 78	com/appodeal/ads/ab:b	Landroid/content/Context;
    //   283: invokestatic 447	com/appodeal/ads/bf:i	(Landroid/content/Context;)F
    //   286: f2d
    //   287: invokevirtual 450	org/json/JSONObject:put	(Ljava/lang/String;D)Lorg/json/JSONObject;
    //   290: pop
    //   291: aload_0
    //   292: getfield 78	com/appodeal/ads/ab:b	Landroid/content/Context;
    //   295: invokestatic 453	com/appodeal/ads/bf:n	(Landroid/content/Context;)Z
    //   298: ifeq +200 -> 498
    //   301: getstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   304: ldc_w 455
    //   307: ldc_w 457
    //   310: invokevirtual 354	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   313: pop
    //   314: getstatic 462	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   317: ldc_w 464
    //   320: invokevirtual 195	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   323: ifeq +204 -> 527
    //   326: ldc_w 466
    //   329: astore_1
    //   330: getstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   333: ldc_w 468
    //   336: aload_1
    //   337: invokevirtual 354	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   340: pop
    //   341: aload_2
    //   342: aload_3
    //   343: invokevirtual 471	android/content/pm/PackageManager:getInstallerPackageName	(Ljava/lang/String;)Ljava/lang/String;
    //   346: astore_2
    //   347: aload_2
    //   348: astore_1
    //   349: aload_2
    //   350: ifnonnull +7 -> 357
    //   353: ldc_w 473
    //   356: astore_1
    //   357: getstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   360: ldc_w 475
    //   363: aload_1
    //   364: invokevirtual 354	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   367: pop
    //   368: getstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   371: ldc_w 477
    //   374: getstatic 462	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   377: invokevirtual 354	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   380: pop
    //   381: getstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   384: ldc_w 479
    //   387: invokestatic 482	com/appodeal/ads/bf:a	()Z
    //   390: invokevirtual 485	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   393: pop
    //   394: getstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   397: ldc_w 487
    //   400: aload_0
    //   401: getfield 78	com/appodeal/ads/ab:b	Landroid/content/Context;
    //   404: invokestatic 490	com/appodeal/ads/bf:t	(Landroid/content/Context;)Ljava/lang/String;
    //   407: invokevirtual 354	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   410: pop
    //   411: getstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   414: ldc_w 492
    //   417: iconst_1
    //   418: anewarray 191	java/lang/String
    //   421: dup
    //   422: iconst_0
    //   423: ldc_w 494
    //   426: aastore
    //   427: invokestatic 497	com/appodeal/ads/bf:b	([Ljava/lang/String;)Z
    //   430: invokevirtual 485	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
    //   433: pop
    //   434: new 272	org/json/JSONObject
    //   437: dup
    //   438: invokespecial 334	org/json/JSONObject:<init>	()V
    //   441: astore_1
    //   442: getstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   445: invokevirtual 501	org/json/JSONObject:keys	()Ljava/util/Iterator;
    //   448: astore_2
    //   449: aload_2
    //   450: invokeinterface 506 1 0
    //   455: ifeq +67 -> 522
    //   458: aload_2
    //   459: invokeinterface 510 1 0
    //   464: checkcast 191	java/lang/String
    //   467: astore_3
    //   468: aload_1
    //   469: aload_3
    //   470: getstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   473: aload_3
    //   474: invokevirtual 514	org/json/JSONObject:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   477: invokevirtual 354	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   480: pop
    //   481: goto -32 -> 449
    //   484: astore_1
    //   485: ldc 2
    //   487: monitorexit
    //   488: aload_1
    //   489: athrow
    //   490: astore_1
    //   491: aload_1
    //   492: invokestatic 318	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   495: goto -296 -> 199
    //   498: getstatic 332	com/appodeal/ads/ab:s	Lorg/json/JSONObject;
    //   501: ldc_w 455
    //   504: ldc_w 516
    //   507: invokevirtual 354	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   510: pop
    //   511: goto -197 -> 314
    //   514: astore_1
    //   515: aload_1
    //   516: invokestatic 318	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
    //   519: goto -151 -> 368
    //   522: ldc 2
    //   524: monitorexit
    //   525: aload_1
    //   526: areturn
    //   527: ldc_w 518
    //   530: astore_1
    //   531: goto -201 -> 330
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	534	0	this	ab
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
  
  private SSLSocketFactory d()
  {
    try
    {
      if (F == null)
      {
        localObject1 = new ArrayList();
        ((List)localObject1).add("A1ABC1296E644B3A25179FCD3E277C8D36039BEE94478E2F5104FA4244237F54");
        ((List)localObject1).add("E91093227F02CE854C3214749DC7FB3459E0E43E80CAE27F01AA0EA92894C9E1");
        localObject1 = new com.appodeal.ads.utils.g((List)localObject1, 1526342400000L);
        SSLContext localSSLContext = SSLContext.getInstance("TLSv1");
        localSSLContext.init(null, new TrustManager[] { localObject1 }, null);
        F = localSSLContext.getSocketFactory();
      }
      Object localObject1 = F;
      return localObject1;
    }
    finally {}
  }
  
  @Nullable
  JSONObject a(SharedPreferences paramSharedPreferences)
  {
    if (paramSharedPreferences == null) {}
    String str1;
    do
    {
      return null;
      str1 = paramSharedPreferences.getString("appKey", null);
    } while (str1 == null);
    String str2 = az.h();
    JSONObject localJSONObject;
    if (az.g())
    {
      paramSharedPreferences = "0";
      localJSONObject = new JSONObject();
      localJSONObject.put("app_key", str1);
      localJSONObject.put("sdk", "2.4.2");
      localJSONObject.put("package", this.b.getPackageName());
      localJSONObject.put("framework", Appodeal.l);
      localJSONObject.put("android_id", str2);
      localJSONObject.put("advertising_tracking", paramSharedPreferences);
      if (!Build.MANUFACTURER.equals("Amazon")) {
        break label165;
      }
    }
    label165:
    for (paramSharedPreferences = "amazon";; paramSharedPreferences = "google")
    {
      localJSONObject.put("platform", paramSharedPreferences);
      localJSONObject.put("consent", az.f());
      localJSONObject.put("fingerprint", az.i());
      return localJSONObject;
      paramSharedPreferences = "1";
      break;
    }
  }
  
  public void a()
  {
    if (this.J) {
      com.appodeal.ads.utils.t.a.execute(new b(null));
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
    if (this.B)
    {
      if (this.o) {
        return bf.f(this.d);
      }
      return new URL(j.a("get"));
    }
    return new URL(j.a(this.d));
  }
  
  @Nullable
  @VisibleForTesting
  JSONObject b(SharedPreferences paramSharedPreferences)
  {
    JSONObject localJSONObject = c(paramSharedPreferences);
    if (localJSONObject == null) {
      return null;
    }
    Object localObject2 = az.h();
    Object localObject1;
    if (az.g()) {
      localObject1 = "0";
    }
    for (;;)
    {
      localJSONObject.put("android_id", localObject2);
      localJSONObject.put("advertising_tracking", localObject1);
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
        if (k.a) {
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
          localJSONObject.put("ad_stats", c());
        }
        if ((this.A) && (k.o == null)) {
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
        localObject1 = Calendar.getInstance();
        ((Calendar)localObject1).setTimeInMillis(paramSharedPreferences.getLong("lastSettingsTime", 0L));
        ((Calendar)localObject1).add(5, 1);
        if ((!UserSettings.a) && (this.A) && ((((Calendar)localObject1).getTimeInMillis() < System.currentTimeMillis()) || (paramSharedPreferences.getBoolean("should_update_user_settings", true)))) {
          UserSettings.a = true;
        }
      }
      catch (JSONException localException2)
      {
        try
        {
          localJSONObject.put("sa", com.appodeal.ads.utils.ab.a(this.b));
          if (h.h) {}
        }
        catch (Exception localException2)
        {
          try
          {
            for (;;)
            {
              localJSONObject.put("user_settings", bf.u(this.b).k());
              localObject1 = paramSharedPreferences.edit();
              ((SharedPreferences.Editor)localObject1).putLong("lastSettingsTime", System.currentTimeMillis());
              ((SharedPreferences.Editor)localObject1).putBoolean("should_update_user_settings", false);
              ((SharedPreferences.Editor)localObject1).apply();
              UserSettings.a = false;
              localObject1 = Calendar.getInstance();
              ((Calendar)localObject1).setTimeInMillis(paramSharedPreferences.getLong("lastAppTime", 0L));
              ((Calendar)localObject1).add(5, 1);
              if ((!k.l) && (k.k) && (this.A) && (((Calendar)localObject1).getTimeInMillis() < System.currentTimeMillis()))
              {
                k.l = true;
                try
                {
                  localObject1 = new JSONArray();
                  Object localObject3 = this.b.getPackageManager().getInstalledApplications(0);
                  localObject2 = Pattern.compile("^?(?:com\\.android|com\\.google|com\\.sec|com\\.samsung|com\\.sonyericsson|com\\.sonymobile|com\\.motorola|com\\.htc).*$");
                  if (localObject3 == null) {
                    break label1626;
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
                k.l = false;
              }
              if (this.h != null)
              {
                if (!this.d.equals("stats")) {
                  break label1640;
                }
                localJSONObject.put("id", this.h.a().toString());
              }
              az.a(localJSONObject);
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
              label1626:
              localJSONObject.put("apps", localException3);
              continue;
              label1640:
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
    }
  }
  
  @VisibleForTesting
  JSONObject c()
  {
    JSONObject localJSONObject = new JSONObject();
    int i1 = p.a();
    int i2 = p.b();
    int i3 = p.c();
    try
    {
      localJSONObject.put("show", i1);
      localJSONObject.put("click", i2);
      if ((this.w) || (this.x) || ((this.k != null) && ((this.k.equals("video")) || (this.k.equals("rewarded_video"))))) {
        localJSONObject.put("finish", i3);
      }
      if ((this.t) || ((this.k != null) && (this.k.equals("banner"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "show" }), p.a("interstitial"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "click" }), p.b("interstitial"));
      }
      if ((this.w) || ((this.k != null) && (this.k.equals("video"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "show" }), p.a("video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "click" }), p.b("video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "video", "finish" }), p.c("video"));
      }
      if ((this.x) || ((this.k != null) && (this.k.equals("rewarded_video"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "show" }), p.a("rewarded_video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "click" }), p.b("rewarded_video"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "finish" }), p.c("rewarded_video"));
      }
      if ((this.u) || ((this.k != null) && (this.k.equals("banner_320"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "show" }), p.a("banner"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "click" }), p.b("banner"));
      }
      if ((this.v) || ((this.k != null) && (this.k.equals("banner_mrec"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "show" }), p.a("mrec"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "click" }), p.b("mrec"));
      }
      if ((this.y) || ((this.k != null) && (this.k.equals("native"))))
      {
        localJSONObject.put(String.format("%s_%s", new Object[] { "native", "show" }), p.a("native"));
        localJSONObject.put(String.format("%s_%s", new Object[] { "native", "click" }), p.b("native"));
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
      //   4: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   7: getfield 32	com/appodeal/ads/ab:b	Landroid/content/Context;
      //   10: ldc 34
      //   12: iconst_0
      //   13: invokevirtual 40	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   16: astore_2
      //   17: aload_0
      //   18: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   21: getfield 44	com/appodeal/ads/ab:d	Ljava/lang/String;
      //   24: ldc 46
      //   26: invokevirtual 52	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   29: ifeq +29 -> 58
      //   32: aload_0
      //   33: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   36: aload_2
      //   37: invokevirtual 55	com/appodeal/ads/ab:a	(Landroid/content/SharedPreferences;)Lorg/json/JSONObject;
      //   40: astore_2
      //   41: aload_2
      //   42: ifnonnull +28 -> 70
      //   45: aload_0
      //   46: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   49: invokestatic 58	com/appodeal/ads/ab:a	(Lcom/appodeal/ads/ab;)Landroid/os/Handler;
      //   52: iconst_0
      //   53: invokevirtual 64	android/os/Handler:sendEmptyMessage	(I)Z
      //   56: pop
      //   57: return
      //   58: aload_0
      //   59: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   62: aload_2
      //   63: invokevirtual 66	com/appodeal/ads/ab:b	(Landroid/content/SharedPreferences;)Lorg/json/JSONObject;
      //   66: astore_2
      //   67: goto -26 -> 41
      //   70: aload_0
      //   71: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   74: invokevirtual 69	com/appodeal/ads/ab:b	()Ljava/net/URL;
      //   77: astore 5
      //   79: aload_0
      //   80: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   83: getfield 32	com/appodeal/ads/ab:b	Landroid/content/Context;
      //   86: ldc 71
      //   88: iconst_0
      //   89: invokevirtual 40	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   92: astore 6
      //   94: aload 5
      //   96: invokevirtual 77	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   99: astore_3
      //   100: aload 5
      //   102: invokevirtual 81	java/net/URL:getProtocol	()Ljava/lang/String;
      //   105: ldc 83
      //   107: invokevirtual 52	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   110: ifeq +17 -> 127
      //   113: aload_3
      //   114: checkcast 85	javax/net/ssl/HttpsURLConnection
      //   117: aload_0
      //   118: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   121: invokestatic 88	com/appodeal/ads/ab:b	(Lcom/appodeal/ads/ab;)Ljavax/net/ssl/SSLSocketFactory;
      //   124: invokevirtual 92	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
      //   127: aload_0
      //   128: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   131: getfield 96	com/appodeal/ads/ab:B	Z
      //   134: ifeq +459 -> 593
      //   137: aload 6
      //   139: aload_0
      //   140: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   143: getfield 44	com/appodeal/ads/ab:d	Ljava/lang/String;
      //   146: invokeinterface 102 2 0
      //   151: ifeq +442 -> 593
      //   154: aload_3
      //   155: sipush 10000
      //   158: invokevirtual 108	java/net/URLConnection:setConnectTimeout	(I)V
      //   161: aload_3
      //   162: sipush 10000
      //   165: invokevirtual 111	java/net/URLConnection:setReadTimeout	(I)V
      //   168: aload_3
      //   169: sipush 20000
      //   172: invokevirtual 108	java/net/URLConnection:setConnectTimeout	(I)V
      //   175: aload_3
      //   176: sipush 20000
      //   179: invokevirtual 111	java/net/URLConnection:setReadTimeout	(I)V
      //   182: aload_3
      //   183: ldc 113
      //   185: ldc 115
      //   187: invokevirtual 119	java/net/URLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   190: aload_3
      //   191: iconst_1
      //   192: invokevirtual 123	java/net/URLConnection:setDoOutput	(Z)V
      //   195: new 125	java/io/ByteArrayOutputStream
      //   198: dup
      //   199: invokespecial 126	java/io/ByteArrayOutputStream:<init>	()V
      //   202: astore 5
      //   204: new 128	java/util/zip/GZIPOutputStream
      //   207: dup
      //   208: aload 5
      //   210: invokespecial 131	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   213: astore 7
      //   215: aload 7
      //   217: aload_2
      //   218: invokevirtual 136	org/json/JSONObject:toString	()Ljava/lang/String;
      //   221: ldc -118
      //   223: invokevirtual 142	java/lang/String:getBytes	(Ljava/lang/String;)[B
      //   226: invokevirtual 146	java/util/zip/GZIPOutputStream:write	([B)V
      //   229: aload 7
      //   231: invokevirtual 149	java/util/zip/GZIPOutputStream:close	()V
      //   234: aload 5
      //   236: invokevirtual 153	java/io/ByteArrayOutputStream:toByteArray	()[B
      //   239: iconst_0
      //   240: invokestatic 159	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
      //   243: astore_2
      //   244: aload_3
      //   245: invokevirtual 163	java/net/URLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   248: aload_2
      //   249: invokestatic 168	com/appodeal/ads/bf:a	(Ljava/io/OutputStream;Ljava/lang/String;)V
      //   252: aload_3
      //   253: invokevirtual 172	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
      //   256: invokestatic 175	com/appodeal/ads/bf:a	(Ljava/io/InputStream;)Ljava/lang/String;
      //   259: astore 5
      //   261: aload 4
      //   263: astore_2
      //   264: aload 5
      //   266: ifnull +47 -> 313
      //   269: aload 4
      //   271: astore_2
      //   272: aload 5
      //   274: invokevirtual 179	java/lang/String:isEmpty	()Z
      //   277: ifne +36 -> 313
      //   280: aload 4
      //   282: astore_2
      //   283: aload 5
      //   285: ldc -75
      //   287: invokevirtual 52	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   290: ifne +23 -> 313
      //   293: aload_0
      //   294: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   297: getfield 44	com/appodeal/ads/ab:d	Ljava/lang/String;
      //   300: ldc -73
      //   302: invokevirtual 52	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   305: istore_1
      //   306: iload_1
      //   307: ifeq +619 -> 926
      //   310: aload 4
      //   312: astore_2
      //   313: aload_3
      //   314: ifnull +609 -> 923
      //   317: aload_3
      //   318: instanceof 85
      //   321: ifeq +410 -> 731
      //   324: aload_3
      //   325: checkcast 85	javax/net/ssl/HttpsURLConnection
      //   328: invokevirtual 186	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   331: aload_2
      //   332: ifnonnull +503 -> 835
      //   335: aload_0
      //   336: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   339: getfield 96	com/appodeal/ads/ab:B	Z
      //   342: ifeq +480 -> 822
      //   345: aload 6
      //   347: aload_0
      //   348: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   351: getfield 44	com/appodeal/ads/ab:d	Ljava/lang/String;
      //   354: invokeinterface 102 2 0
      //   359: ifeq +463 -> 822
      //   362: aload_0
      //   363: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   366: aload 6
      //   368: aload_0
      //   369: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   372: getfield 44	com/appodeal/ads/ab:d	Ljava/lang/String;
      //   375: invokevirtual 189	com/appodeal/ads/ab:a	(Landroid/content/SharedPreferences;Ljava/lang/String;)Z
      //   378: ifeq +444 -> 822
      //   381: new 191	com/appodeal/ads/utils/c/a
      //   384: dup
      //   385: ldc -63
      //   387: invokespecial 196	com/appodeal/ads/utils/c/a:<init>	(Ljava/lang/String;)V
      //   390: invokestatic 201	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   393: aload 6
      //   395: aload_0
      //   396: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   399: getfield 44	com/appodeal/ads/ab:d	Ljava/lang/String;
      //   402: ldc -53
      //   404: invokeinterface 207 3 0
      //   409: astore_3
      //   410: new 133	org/json/JSONObject
      //   413: dup
      //   414: aload_3
      //   415: invokespecial 208	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   418: astore_2
      //   419: aload_0
      //   420: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   423: getfield 96	com/appodeal/ads/ab:B	Z
      //   426: ifeq +443 -> 869
      //   429: aload_3
      //   430: getstatic 214	com/appodeal/ads/utils/Log$LogLevel:verbose	Lcom/appodeal/ads/utils/Log$LogLevel;
      //   433: invokestatic 217	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;Lcom/appodeal/ads/utils/Log$LogLevel;)V
      //   436: aload_0
      //   437: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   440: getfield 96	com/appodeal/ads/ab:B	Z
      //   443: ifeq +106 -> 549
      //   446: aload_0
      //   447: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   450: getfield 32	com/appodeal/ads/ab:b	Landroid/content/Context;
      //   453: aload_2
      //   454: ldc -37
      //   456: invokevirtual 223	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   459: invokestatic 228	com/appodeal/ads/UserSettings:a	(Landroid/content/Context;Lorg/json/JSONObject;)V
      //   462: aload_0
      //   463: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   466: getfield 32	com/appodeal/ads/ab:b	Landroid/content/Context;
      //   469: aload_2
      //   470: ldc -26
      //   472: invokevirtual 223	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   475: aload_2
      //   476: ldc -24
      //   478: invokevirtual 235	org/json/JSONObject:optBoolean	(Ljava/lang/String;)Z
      //   481: aload_2
      //   482: ldc -19
      //   484: invokevirtual 235	org/json/JSONObject:optBoolean	(Ljava/lang/String;)Z
      //   487: invokestatic 242	com/appodeal/ads/h:a	(Landroid/content/Context;Lorg/json/JSONObject;ZZ)V
      //   490: aload_0
      //   491: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   494: aload 6
      //   496: aload_2
      //   497: ldc -12
      //   499: ldc -11
      //   501: invokevirtual 249	org/json/JSONObject:optInt	(Ljava/lang/String;I)I
      //   504: aload_0
      //   505: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   508: getfield 44	com/appodeal/ads/ab:d	Ljava/lang/String;
      //   511: invokevirtual 252	com/appodeal/ads/ab:a	(Landroid/content/SharedPreferences;ILjava/lang/String;)V
      //   514: aload_0
      //   515: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   518: aload_2
      //   519: invokestatic 255	com/appodeal/ads/ab:a	(Lcom/appodeal/ads/ab;Lorg/json/JSONObject;)V
      //   522: aload_2
      //   523: ldc_w 257
      //   526: invokevirtual 261	org/json/JSONObject:optJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
      //   529: invokestatic 266	com/appodeal/ads/f/e:a	(Lorg/json/JSONArray;)V
      //   532: invokestatic 269	com/appodeal/ads/f/e:c	()V
      //   535: getstatic 273	com/appodeal/ads/Appodeal:i	Lcom/appodeal/ads/utils/v;
      //   538: ifnull +11 -> 549
      //   541: getstatic 273	com/appodeal/ads/Appodeal:i	Lcom/appodeal/ads/utils/v;
      //   544: invokeinterface 277 1 0
      //   549: aload_0
      //   550: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   553: invokestatic 58	com/appodeal/ads/ab:a	(Lcom/appodeal/ads/ab;)Landroid/os/Handler;
      //   556: iconst_1
      //   557: aload_2
      //   558: invokevirtual 281	android/os/Handler:obtainMessage	(ILjava/lang/Object;)Landroid/os/Message;
      //   561: astore_2
      //   562: aload_0
      //   563: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   566: invokestatic 58	com/appodeal/ads/ab:a	(Lcom/appodeal/ads/ab;)Landroid/os/Handler;
      //   569: aload_2
      //   570: invokevirtual 285	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
      //   573: pop
      //   574: return
      //   575: astore_2
      //   576: aload_2
      //   577: invokestatic 201	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   580: aload_0
      //   581: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   584: invokestatic 58	com/appodeal/ads/ab:a	(Lcom/appodeal/ads/ab;)Landroid/os/Handler;
      //   587: iconst_0
      //   588: invokevirtual 64	android/os/Handler:sendEmptyMessage	(I)Z
      //   591: pop
      //   592: return
      //   593: aload_3
      //   594: sipush 20000
      //   597: invokevirtual 108	java/net/URLConnection:setConnectTimeout	(I)V
      //   600: aload_3
      //   601: sipush 20000
      //   604: invokevirtual 111	java/net/URLConnection:setReadTimeout	(I)V
      //   607: goto -439 -> 168
      //   610: astore 4
      //   612: aload_3
      //   613: astore_2
      //   614: aload 4
      //   616: astore_3
      //   617: aload_3
      //   618: invokestatic 201	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   621: aload_3
      //   622: invokevirtual 288	java/io/IOException:getMessage	()Ljava/lang/String;
      //   625: ldc_w 290
      //   628: invokevirtual 52	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   631: ifne +16 -> 647
      //   634: aload_3
      //   635: invokevirtual 288	java/io/IOException:getMessage	()Ljava/lang/String;
      //   638: ldc_w 292
      //   641: invokevirtual 52	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   644: ifeq +119 -> 763
      //   647: aload_0
      //   648: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   651: invokestatic 58	com/appodeal/ads/ab:a	(Lcom/appodeal/ads/ab;)Landroid/os/Handler;
      //   654: iconst_0
      //   655: invokevirtual 64	android/os/Handler:sendEmptyMessage	(I)Z
      //   658: pop
      //   659: aload_2
      //   660: ifnull +272 -> 932
      //   663: aload_2
      //   664: instanceof 85
      //   667: ifeq +81 -> 748
      //   670: aload_2
      //   671: checkcast 85	javax/net/ssl/HttpsURLConnection
      //   674: invokevirtual 186	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   677: return
      //   678: astore_2
      //   679: aload_2
      //   680: invokestatic 201	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   683: goto -449 -> 234
      //   686: astore 4
      //   688: aload_3
      //   689: astore_2
      //   690: aload 4
      //   692: astore_3
      //   693: aload_2
      //   694: ifnull +17 -> 711
      //   697: aload_2
      //   698: instanceof 85
      //   701: ifeq +104 -> 805
      //   704: aload_2
      //   705: checkcast 85	javax/net/ssl/HttpsURLConnection
      //   708: invokevirtual 186	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   711: aload_3
      //   712: athrow
      //   713: astore_2
      //   714: aload 7
      //   716: invokevirtual 149	java/util/zip/GZIPOutputStream:close	()V
      //   719: aload_2
      //   720: athrow
      //   721: astore 4
      //   723: aload 4
      //   725: invokestatic 201	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   728: goto -9 -> 719
      //   731: aload_3
      //   732: instanceof 294
      //   735: ifeq +188 -> 923
      //   738: aload_3
      //   739: checkcast 294	java/net/HttpURLConnection
      //   742: invokevirtual 295	java/net/HttpURLConnection:disconnect	()V
      //   745: goto -414 -> 331
      //   748: aload_2
      //   749: instanceof 294
      //   752: ifeq +180 -> 932
      //   755: aload_2
      //   756: checkcast 294	java/net/HttpURLConnection
      //   759: invokevirtual 295	java/net/HttpURLConnection:disconnect	()V
      //   762: return
      //   763: aload_2
      //   764: ifnull +154 -> 918
      //   767: aload_2
      //   768: instanceof 85
      //   771: ifeq +15 -> 786
      //   774: aload_2
      //   775: checkcast 85	javax/net/ssl/HttpsURLConnection
      //   778: invokevirtual 186	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   781: aconst_null
      //   782: astore_2
      //   783: goto -452 -> 331
      //   786: aload_2
      //   787: instanceof 294
      //   790: ifeq +128 -> 918
      //   793: aload_2
      //   794: checkcast 294	java/net/HttpURLConnection
      //   797: invokevirtual 295	java/net/HttpURLConnection:disconnect	()V
      //   800: aconst_null
      //   801: astore_2
      //   802: goto -471 -> 331
      //   805: aload_2
      //   806: instanceof 294
      //   809: ifeq -98 -> 711
      //   812: aload_2
      //   813: checkcast 294	java/net/HttpURLConnection
      //   816: invokevirtual 295	java/net/HttpURLConnection:disconnect	()V
      //   819: goto -108 -> 711
      //   822: aload_0
      //   823: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   826: invokestatic 58	com/appodeal/ads/ab:a	(Lcom/appodeal/ads/ab;)Landroid/os/Handler;
      //   829: iconst_0
      //   830: invokevirtual 64	android/os/Handler:sendEmptyMessage	(I)Z
      //   833: pop
      //   834: return
      //   835: aload_2
      //   836: astore_3
      //   837: aload_0
      //   838: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   841: getfield 96	com/appodeal/ads/ab:B	Z
      //   844: ifeq -434 -> 410
      //   847: aload_0
      //   848: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   851: aload 6
      //   853: aload_0
      //   854: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   857: getfield 44	com/appodeal/ads/ab:d	Ljava/lang/String;
      //   860: aload_2
      //   861: invokevirtual 298	com/appodeal/ads/ab:a	(Landroid/content/SharedPreferences;Ljava/lang/String;Ljava/lang/String;)V
      //   864: aload_2
      //   865: astore_3
      //   866: goto -456 -> 410
      //   869: aload_3
      //   870: invokestatic 300	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   873: goto -437 -> 436
      //   876: astore_2
      //   877: aload_2
      //   878: invokestatic 201	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   881: aload_0
      //   882: getfield 15	com/appodeal/ads/ab$b:a	Lcom/appodeal/ads/ab;
      //   885: invokestatic 58	com/appodeal/ads/ab:a	(Lcom/appodeal/ads/ab;)Landroid/os/Handler;
      //   888: iconst_0
      //   889: invokevirtual 64	android/os/Handler:sendEmptyMessage	(I)Z
      //   892: pop
      //   893: return
      //   894: astore_3
      //   895: aload_3
      //   896: invokestatic 201	com/appodeal/ads/Appodeal:a	(Ljava/lang/Throwable;)V
      //   899: goto -350 -> 549
      //   902: astore_3
      //   903: aconst_null
      //   904: astore_2
      //   905: goto -212 -> 693
      //   908: astore_3
      //   909: goto -216 -> 693
      //   912: astore_3
      //   913: aconst_null
      //   914: astore_2
      //   915: goto -298 -> 617
      //   918: aconst_null
      //   919: astore_2
      //   920: goto -589 -> 331
      //   923: goto -592 -> 331
      //   926: aload 5
      //   928: astore_2
      //   929: goto -616 -> 313
      //   932: return
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	933	0	this	b
      //   305	2	1	bool	boolean
      //   16	554	2	localObject1	Object
      //   575	2	2	localException1	Exception
      //   613	58	2	localObject2	Object
      //   678	2	2	localException2	Exception
      //   689	16	2	localObject3	Object
      //   713	62	2	localObject4	Object
      //   782	83	2	str	String
      //   876	2	2	localJSONException	JSONException
      //   904	25	2	localObject5	Object
      //   99	771	3	localObject6	Object
      //   894	2	3	localException3	Exception
      //   902	1	3	localObject7	Object
      //   908	1	3	localObject8	Object
      //   912	1	3	localIOException1	java.io.IOException
      //   1	310	4	localObject9	Object
      //   610	5	4	localIOException2	java.io.IOException
      //   686	5	4	localObject10	Object
      //   721	3	4	localException4	Exception
      //   77	850	5	localObject11	Object
      //   92	760	6	localSharedPreferences	SharedPreferences
      //   213	502	7	localGZIPOutputStream	java.util.zip.GZIPOutputStream
      // Exception table:
      //   from	to	target	type
      //   3	41	575	java/lang/Exception
      //   45	57	575	java/lang/Exception
      //   58	67	575	java/lang/Exception
      //   70	94	575	java/lang/Exception
      //   317	331	575	java/lang/Exception
      //   335	410	575	java/lang/Exception
      //   410	436	575	java/lang/Exception
      //   549	574	575	java/lang/Exception
      //   663	677	575	java/lang/Exception
      //   697	711	575	java/lang/Exception
      //   711	713	575	java/lang/Exception
      //   731	745	575	java/lang/Exception
      //   748	762	575	java/lang/Exception
      //   767	781	575	java/lang/Exception
      //   786	800	575	java/lang/Exception
      //   805	819	575	java/lang/Exception
      //   822	834	575	java/lang/Exception
      //   837	864	575	java/lang/Exception
      //   869	873	575	java/lang/Exception
      //   877	893	575	java/lang/Exception
      //   895	899	575	java/lang/Exception
      //   100	127	610	java/io/IOException
      //   127	168	610	java/io/IOException
      //   168	215	610	java/io/IOException
      //   229	234	610	java/io/IOException
      //   234	261	610	java/io/IOException
      //   272	280	610	java/io/IOException
      //   283	306	610	java/io/IOException
      //   593	607	610	java/io/IOException
      //   679	683	610	java/io/IOException
      //   714	719	610	java/io/IOException
      //   719	721	610	java/io/IOException
      //   723	728	610	java/io/IOException
      //   229	234	678	java/lang/Exception
      //   100	127	686	finally
      //   127	168	686	finally
      //   168	215	686	finally
      //   229	234	686	finally
      //   234	261	686	finally
      //   272	280	686	finally
      //   283	306	686	finally
      //   593	607	686	finally
      //   679	683	686	finally
      //   714	719	686	finally
      //   719	721	686	finally
      //   723	728	686	finally
      //   215	229	713	finally
      //   714	719	721	java/lang/Exception
      //   410	436	876	org/json/JSONException
      //   869	873	876	org/json/JSONException
      //   436	549	894	java/lang/Exception
      //   94	100	902	finally
      //   617	647	908	finally
      //   647	659	908	finally
      //   94	100	912	java/io/IOException
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
