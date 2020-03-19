package com.appodeal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.appodeal.ads.open_rtb.h;
import com.appodeal.ads.segments.d;
import com.appodeal.ads.segments.e;
import com.appodeal.ads.segments.f;
import com.appodeal.ads.utils.j;
import org.json.JSONArray;
import org.json.JSONObject;

public class o
{
  private final a a;
  private final Context b;
  private final int c;
  private final String d;
  private final String e;
  private final String f;
  private final String g;
  private final h h;
  private final long i;
  
  o(Activity paramActivity, a paramA, int paramInt, String paramString)
  {
    this(paramActivity, paramA, paramInt, paramString, null, null, null, null);
  }
  
  o(Activity paramActivity, a paramA, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, h paramH)
  {
    this(paramActivity, paramA, paramInt, paramString1, paramString2, paramString3, paramString4, paramH, 0L);
  }
  
  o(Activity paramActivity, a paramA, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, h paramH, long paramLong)
  {
    this.a = paramA;
    this.b = paramActivity;
    this.c = paramInt;
    this.d = paramString1;
    this.e = paramString2;
    this.f = paramString3;
    this.g = paramString4;
    this.h = paramH;
    this.i = paramLong;
    if ((AppodealSettings.a) && ((this.a == null) || ((this.a instanceof j))) && ((this.d.equals("stats")) || (this.d.equals("show")) || (this.d.equals("click")))) {
      return;
    }
    paramActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (Build.VERSION.SDK_INT >= 11)
        {
          new o.b(o.this, null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
          return;
        }
        new o.b(o.this, null).execute(new Void[0]);
      }
    });
  }
  
  public static abstract interface a
  {
    public abstract void a(int paramInt);
    
    public abstract void a(JSONObject paramJSONObject, int paramInt, String paramString);
  }
  
  private class b
    extends AsyncTask
  {
    private b() {}
    
    private void b(JSONObject paramJSONObject)
    {
      JSONArray localJSONArray = paramJSONObject.optJSONArray("segments");
      if ((localJSONArray == null) || (localJSONArray.length() == 0)) {
        return;
      }
      paramJSONObject = new e(o.a(o.this), paramJSONObject).a(localJSONArray);
      if (paramJSONObject != null)
      {
        new f(o.a(o.this)).a(paramJSONObject.b());
        e.a = Long.valueOf(paramJSONObject.a());
        return;
      }
      e.a = Long.valueOf(-1L);
    }
    
    /* Error */
    protected JSONObject a(Void... paramVarArgs)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 18
      //   3: aconst_null
      //   4: astore 17
      //   6: aload 17
      //   8: astore 13
      //   10: aload 18
      //   12: astore_1
      //   13: aload_0
      //   14: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   17: invokestatic 40	com/appodeal/ads/o:a	(Lcom/appodeal/ads/o;)Landroid/content/Context;
      //   20: ldc 83
      //   22: iconst_0
      //   23: invokevirtual 89	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   26: astore 19
      //   28: aload 17
      //   30: astore 13
      //   32: aload 18
      //   34: astore_1
      //   35: aload_0
      //   36: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   39: invokestatic 40	com/appodeal/ads/o:a	(Lcom/appodeal/ads/o;)Landroid/content/Context;
      //   42: invokevirtual 93	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   45: astore 20
      //   47: aload 17
      //   49: astore 13
      //   51: aload 18
      //   53: astore_1
      //   54: aload 19
      //   56: ldc 95
      //   58: aconst_null
      //   59: invokeinterface 101 3 0
      //   64: astore 21
      //   66: aload 21
      //   68: ifnonnull +19 -> 87
      //   71: aconst_null
      //   72: astore_1
      //   73: iconst_0
      //   74: ifeq +11 -> 85
      //   77: new 103	java/lang/NullPointerException
      //   80: dup
      //   81: invokespecial 104	java/lang/NullPointerException:<init>	()V
      //   84: athrow
      //   85: aload_1
      //   86: areturn
      //   87: aload 17
      //   89: astore 13
      //   91: aload 18
      //   93: astore_1
      //   94: aload 19
      //   96: ldc 106
      //   98: aconst_null
      //   99: invokeinterface 101 3 0
      //   104: astore 16
      //   106: aload 17
      //   108: astore 13
      //   110: aload 18
      //   112: astore_1
      //   113: aload 19
      //   115: ldc 108
      //   117: aconst_null
      //   118: invokeinterface 101 3 0
      //   123: astore 12
      //   125: aload 12
      //   127: astore 14
      //   129: aload 16
      //   131: astore 15
      //   133: aload 16
      //   135: ifnonnull +2386 -> 2521
      //   138: aload 12
      //   140: astore 14
      //   142: aload 16
      //   144: astore 15
      //   146: aload 18
      //   148: astore_1
      //   149: ldc 110
      //   151: iconst_0
      //   152: aload_0
      //   153: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   156: invokestatic 40	com/appodeal/ads/o:a	(Lcom/appodeal/ads/o;)Landroid/content/Context;
      //   159: invokevirtual 116	java/lang/Object:getClass	()Ljava/lang/Class;
      //   162: invokevirtual 122	java/lang/Class:getClassLoader	()Ljava/lang/ClassLoader;
      //   165: invokestatic 126	java/lang/Class:forName	(Ljava/lang/String;ZLjava/lang/ClassLoader;)Ljava/lang/Class;
      //   168: pop
      //   169: aload 12
      //   171: astore 14
      //   173: aload 16
      //   175: astore 15
      //   177: aload 18
      //   179: astore_1
      //   180: ldc -128
      //   182: ldc -126
      //   184: iconst_1
      //   185: anewarray 118	java/lang/Class
      //   188: dup
      //   189: iconst_0
      //   190: ldc 85
      //   192: aastore
      //   193: invokevirtual 134	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      //   196: pop
      //   197: aload 12
      //   199: astore 14
      //   201: aload 16
      //   203: astore 15
      //   205: aload 18
      //   207: astore_1
      //   208: aload_0
      //   209: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   212: invokestatic 40	com/appodeal/ads/o:a	(Lcom/appodeal/ads/o;)Landroid/content/Context;
      //   215: invokestatic 137	com/google/android/gms/ads/identifier/AdvertisingIdClient:getAdvertisingIdInfo	(Landroid/content/Context;)Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;
      //   218: astore 22
      //   220: aload 12
      //   222: astore 14
      //   224: aload 16
      //   226: astore 15
      //   228: aload 18
      //   230: astore_1
      //   231: aload 22
      //   233: invokevirtual 143	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:getId	()Ljava/lang/String;
      //   236: astore 13
      //   238: aload 12
      //   240: astore 14
      //   242: aload 13
      //   244: astore 15
      //   246: aload 18
      //   248: astore_1
      //   249: aload 22
      //   251: invokevirtual 147	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:isLimitAdTrackingEnabled	()Z
      //   254: ifeq +2245 -> 2499
      //   257: ldc -107
      //   259: astore 12
      //   261: aload 12
      //   263: astore 14
      //   265: aload 13
      //   267: astore 15
      //   269: aload 18
      //   271: astore_1
      //   272: aload 19
      //   274: invokeinterface 153 1 0
      //   279: astore 16
      //   281: aload 12
      //   283: astore 14
      //   285: aload 13
      //   287: astore 15
      //   289: aload 18
      //   291: astore_1
      //   292: aload 16
      //   294: ldc 106
      //   296: aload 13
      //   298: invokeinterface 159 3 0
      //   303: pop
      //   304: aload 12
      //   306: astore 14
      //   308: aload 13
      //   310: astore 15
      //   312: aload 18
      //   314: astore_1
      //   315: aload 16
      //   317: ldc 108
      //   319: aload 12
      //   321: invokeinterface 159 3 0
      //   326: pop
      //   327: aload 12
      //   329: astore 14
      //   331: aload 13
      //   333: astore 15
      //   335: aload 18
      //   337: astore_1
      //   338: aload 16
      //   340: invokeinterface 162 1 0
      //   345: aload 13
      //   347: astore_1
      //   348: aload_1
      //   349: ifnonnull +3305 -> 3654
      //   352: aload 17
      //   354: astore 13
      //   356: aload 18
      //   358: astore_1
      //   359: aload_0
      //   360: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   363: invokestatic 40	com/appodeal/ads/o:a	(Lcom/appodeal/ads/o;)Landroid/content/Context;
      //   366: invokestatic 168	com/appodeal/ads/v:j	(Landroid/content/Context;)Ljava/lang/String;
      //   369: astore 14
      //   371: aload 17
      //   373: astore 13
      //   375: aload 18
      //   377: astore_1
      //   378: new 25	org/json/JSONObject
      //   381: dup
      //   382: invokespecial 169	org/json/JSONObject:<init>	()V
      //   385: astore 15
      //   387: aload 17
      //   389: astore 13
      //   391: aload 18
      //   393: astore_1
      //   394: aload_0
      //   395: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   398: invokestatic 172	com/appodeal/ads/o:b	(Lcom/appodeal/ads/o;)Ljava/lang/String;
      //   401: ldc -82
      //   403: invokevirtual 180	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   406: ifne +3254 -> 3660
      //   409: aload 17
      //   411: astore 13
      //   413: aload 18
      //   415: astore_1
      //   416: aload_0
      //   417: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   420: invokestatic 172	com/appodeal/ads/o:b	(Lcom/appodeal/ads/o;)Ljava/lang/String;
      //   423: ldc -74
      //   425: invokevirtual 180	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   428: ifeq +2103 -> 2531
      //   431: goto +3229 -> 3660
      //   434: aload 17
      //   436: astore 13
      //   438: aload 18
      //   440: astore_1
      //   441: aload_0
      //   442: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   445: invokestatic 172	com/appodeal/ads/o:b	(Lcom/appodeal/ads/o;)Ljava/lang/String;
      //   448: ldc -72
      //   450: invokevirtual 180	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   453: ifne +3212 -> 3665
      //   456: aload 17
      //   458: astore 13
      //   460: aload 18
      //   462: astore_1
      //   463: aload_0
      //   464: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   467: invokestatic 172	com/appodeal/ads/o:b	(Lcom/appodeal/ads/o;)Ljava/lang/String;
      //   470: ldc -70
      //   472: invokevirtual 180	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   475: ifeq +2061 -> 2536
      //   478: goto +3187 -> 3665
      //   481: aload 17
      //   483: astore 13
      //   485: aload 18
      //   487: astore_1
      //   488: aload_0
      //   489: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   492: invokestatic 172	com/appodeal/ads/o:b	(Lcom/appodeal/ads/o;)Ljava/lang/String;
      //   495: ldc -68
      //   497: invokevirtual 180	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   500: ifne +3170 -> 3670
      //   503: aload 17
      //   505: astore 13
      //   507: aload 18
      //   509: astore_1
      //   510: aload_0
      //   511: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   514: invokestatic 172	com/appodeal/ads/o:b	(Lcom/appodeal/ads/o;)Ljava/lang/String;
      //   517: ldc -66
      //   519: invokevirtual 180	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   522: ifeq +2019 -> 2541
      //   525: goto +3145 -> 3670
      //   528: aload 17
      //   530: astore 13
      //   532: aload 18
      //   534: astore_1
      //   535: aload_0
      //   536: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   539: invokestatic 172	com/appodeal/ads/o:b	(Lcom/appodeal/ads/o;)Ljava/lang/String;
      //   542: ldc -64
      //   544: invokevirtual 180	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   547: ifne +3129 -> 3676
      //   550: aload 17
      //   552: astore 13
      //   554: aload 18
      //   556: astore_1
      //   557: aload_0
      //   558: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   561: invokestatic 172	com/appodeal/ads/o:b	(Lcom/appodeal/ads/o;)Ljava/lang/String;
      //   564: ldc -62
      //   566: invokevirtual 180	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   569: ifeq +1978 -> 2547
      //   572: goto +3104 -> 3676
      //   575: aload 17
      //   577: astore 13
      //   579: aload 18
      //   581: astore_1
      //   582: aload_0
      //   583: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   586: invokestatic 172	com/appodeal/ads/o:b	(Lcom/appodeal/ads/o;)Ljava/lang/String;
      //   589: ldc -74
      //   591: invokevirtual 180	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   594: ifne +73 -> 667
      //   597: aload 17
      //   599: astore 13
      //   601: aload 18
      //   603: astore_1
      //   604: aload_0
      //   605: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   608: invokestatic 172	com/appodeal/ads/o:b	(Lcom/appodeal/ads/o;)Ljava/lang/String;
      //   611: ldc -70
      //   613: invokevirtual 180	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   616: ifne +51 -> 667
      //   619: aload 17
      //   621: astore 13
      //   623: aload 18
      //   625: astore_1
      //   626: aload_0
      //   627: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   630: invokestatic 172	com/appodeal/ads/o:b	(Lcom/appodeal/ads/o;)Ljava/lang/String;
      //   633: ldc -66
      //   635: invokevirtual 180	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   638: ifne +29 -> 667
      //   641: aload 17
      //   643: astore 13
      //   645: aload 18
      //   647: astore_1
      //   648: aload_0
      //   649: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   652: invokestatic 172	com/appodeal/ads/o:b	(Lcom/appodeal/ads/o;)Ljava/lang/String;
      //   655: ldc -62
      //   657: invokevirtual 180	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   660: istore 7
      //   662: iload 7
      //   664: ifeq +1889 -> 2553
      //   667: iconst_1
      //   668: istore 6
      //   670: aload 17
      //   672: astore 13
      //   674: aload 18
      //   676: astore_1
      //   677: aload 15
      //   679: ldc -60
      //   681: aload 21
      //   683: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   686: pop
      //   687: iload_2
      //   688: ifeq +20 -> 708
      //   691: aload 17
      //   693: astore 13
      //   695: aload 18
      //   697: astore_1
      //   698: aload 15
      //   700: ldc -54
      //   702: ldc -82
      //   704: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   707: pop
      //   708: iload_3
      //   709: ifeq +2973 -> 3682
      //   712: aload 17
      //   714: astore 13
      //   716: aload 18
      //   718: astore_1
      //   719: aload 15
      //   721: ldc -54
      //   723: ldc -72
      //   725: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   728: pop
      //   729: goto +2953 -> 3682
      //   732: aload 17
      //   734: astore 13
      //   736: aload 18
      //   738: astore_1
      //   739: aload 15
      //   741: ldc -54
      //   743: ldc -68
      //   745: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   748: pop
      //   749: iload 5
      //   751: ifeq +19 -> 770
      //   754: aload 17
      //   756: astore 13
      //   758: aload 18
      //   760: astore_1
      //   761: aload 15
      //   763: ldc -64
      //   765: iconst_1
      //   766: invokevirtual 205	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   769: pop
      //   770: iload 6
      //   772: ifeq +19 -> 791
      //   775: aload 17
      //   777: astore 13
      //   779: aload 18
      //   781: astore_1
      //   782: aload 15
      //   784: ldc -74
      //   786: iconst_1
      //   787: invokevirtual 205	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   790: pop
      //   791: aload 17
      //   793: astore 13
      //   795: aload 18
      //   797: astore_1
      //   798: getstatic 210	com/appodeal/ads/AppodealSettings:a	Z
      //   801: ifeq +19 -> 820
      //   804: aload 17
      //   806: astore 13
      //   808: aload 18
      //   810: astore_1
      //   811: aload 15
      //   813: ldc -44
      //   815: iconst_1
      //   816: invokevirtual 205	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   819: pop
      //   820: aload 17
      //   822: astore 13
      //   824: aload 18
      //   826: astore_1
      //   827: aload 15
      //   829: ldc -42
      //   831: getstatic 220	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
      //   834: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   837: pop
      //   838: aload 17
      //   840: astore 13
      //   842: aload 18
      //   844: astore_1
      //   845: aload 15
      //   847: ldc -34
      //   849: getstatic 226	android/os/Build$VERSION:SDK_INT	I
      //   852: invokevirtual 229	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
      //   855: pop
      //   856: aload 17
      //   858: astore 13
      //   860: aload 18
      //   862: astore_1
      //   863: aload 15
      //   865: ldc -25
      //   867: ldc -23
      //   869: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   872: pop
      //   873: aload 17
      //   875: astore 13
      //   877: aload 18
      //   879: astore_1
      //   880: aload_0
      //   881: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   884: invokestatic 40	com/appodeal/ads/o:a	(Lcom/appodeal/ads/o;)Landroid/content/Context;
      //   887: invokevirtual 236	android/content/Context:getPackageName	()Ljava/lang/String;
      //   890: astore 16
      //   892: aload 17
      //   894: astore 13
      //   896: aload 18
      //   898: astore_1
      //   899: aload 15
      //   901: ldc -18
      //   903: aload 16
      //   905: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   908: pop
      //   909: aload 17
      //   911: astore 13
      //   913: aload 18
      //   915: astore_1
      //   916: aload 15
      //   918: ldc -16
      //   920: aload 20
      //   922: aload 16
      //   924: iconst_0
      //   925: invokevirtual 246	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
      //   928: getfield 251	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
      //   931: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   934: pop
      //   935: aload 18
      //   937: astore_1
      //   938: aload 15
      //   940: ldc -3
      //   942: aload 20
      //   944: aload 16
      //   946: sipush 128
      //   949: invokevirtual 257	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
      //   952: getfield 263	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
      //   955: ldc_w 265
      //   958: invokevirtual 270	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   961: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   964: pop
      //   965: aload 17
      //   967: astore 13
      //   969: aload 18
      //   971: astore_1
      //   972: aload 15
      //   974: ldc_w 272
      //   977: aload 14
      //   979: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   982: pop
      //   983: aload 17
      //   985: astore 13
      //   987: aload 18
      //   989: astore_1
      //   990: aload 15
      //   992: ldc_w 274
      //   995: aload 12
      //   997: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1000: pop
      //   1001: aload 17
      //   1003: astore 13
      //   1005: aload 18
      //   1007: astore_1
      //   1008: aload_0
      //   1009: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   1012: invokestatic 40	com/appodeal/ads/o:a	(Lcom/appodeal/ads/o;)Landroid/content/Context;
      //   1015: invokestatic 277	com/appodeal/ads/v:b	(Landroid/content/Context;)Lcom/appodeal/ads/v$a;
      //   1018: astore 14
      //   1020: aload 17
      //   1022: astore 13
      //   1024: aload 18
      //   1026: astore_1
      //   1027: aload 15
      //   1029: ldc_w 279
      //   1032: aload 14
      //   1034: getfield 283	com/appodeal/ads/v$a:a	Ljava/lang/String;
      //   1037: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1040: pop
      //   1041: aload 17
      //   1043: astore 13
      //   1045: aload 18
      //   1047: astore_1
      //   1048: aload 15
      //   1050: ldc_w 285
      //   1053: aload_0
      //   1054: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   1057: invokestatic 40	com/appodeal/ads/o:a	(Lcom/appodeal/ads/o;)Landroid/content/Context;
      //   1060: invokestatic 289	com/appodeal/ads/v:i	(Landroid/content/Context;)F
      //   1063: f2d
      //   1064: invokevirtual 292	org/json/JSONObject:put	(Ljava/lang/String;D)Lorg/json/JSONObject;
      //   1067: pop
      //   1068: aload 17
      //   1070: astore 13
      //   1072: aload 18
      //   1074: astore_1
      //   1075: aload_0
      //   1076: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   1079: invokestatic 40	com/appodeal/ads/o:a	(Lcom/appodeal/ads/o;)Landroid/content/Context;
      //   1082: invokestatic 296	com/appodeal/ads/v:f	(Landroid/content/Context;)Landroid/util/Pair;
      //   1085: astore 12
      //   1087: aload 17
      //   1089: astore 13
      //   1091: aload 18
      //   1093: astore_1
      //   1094: aload 15
      //   1096: ldc_w 298
      //   1099: aload 12
      //   1101: getfield 304	android/util/Pair:first	Ljava/lang/Object;
      //   1104: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1107: pop
      //   1108: aload 17
      //   1110: astore 13
      //   1112: aload 18
      //   1114: astore_1
      //   1115: aload 15
      //   1117: ldc_w 306
      //   1120: aload 12
      //   1122: getfield 309	android/util/Pair:second	Ljava/lang/Object;
      //   1125: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1128: pop
      //   1129: iload_2
      //   1130: ifne +17 -> 1147
      //   1133: iload 4
      //   1135: ifne +12 -> 1147
      //   1138: iload 5
      //   1140: ifne +7 -> 1147
      //   1143: iload_3
      //   1144: ifeq +1827 -> 2971
      //   1147: aload 17
      //   1149: astore 13
      //   1151: aload 18
      //   1153: astore_1
      //   1154: getstatic 314	android/os/Build:MANUFACTURER	Ljava/lang/String;
      //   1157: ldc_w 316
      //   1160: invokevirtual 180	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1163: ifeq +1480 -> 2643
      //   1166: ldc_w 318
      //   1169: astore 12
      //   1171: aload 17
      //   1173: astore 13
      //   1175: aload 18
      //   1177: astore_1
      //   1178: aload 15
      //   1180: ldc_w 320
      //   1183: aload 12
      //   1185: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1188: pop
      //   1189: aload 17
      //   1191: astore 13
      //   1193: aload 18
      //   1195: astore_1
      //   1196: aload 15
      //   1198: ldc_w 322
      //   1201: ldc_w 324
      //   1204: iconst_2
      //   1205: anewarray 112	java/lang/Object
      //   1208: dup
      //   1209: iconst_0
      //   1210: getstatic 314	android/os/Build:MANUFACTURER	Ljava/lang/String;
      //   1213: aastore
      //   1214: dup
      //   1215: iconst_1
      //   1216: getstatic 327	android/os/Build:MODEL	Ljava/lang/String;
      //   1219: aastore
      //   1220: invokestatic 331	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   1223: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1226: pop
      //   1227: aload 17
      //   1229: astore 13
      //   1231: aload 18
      //   1233: astore_1
      //   1234: aload_0
      //   1235: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   1238: invokestatic 40	com/appodeal/ads/o:a	(Lcom/appodeal/ads/o;)Landroid/content/Context;
      //   1241: invokestatic 334	com/appodeal/ads/v:d	(Landroid/content/Context;)Landroid/util/Pair;
      //   1244: astore 12
      //   1246: aload 17
      //   1248: astore 13
      //   1250: aload 18
      //   1252: astore_1
      //   1253: aload 15
      //   1255: ldc_w 336
      //   1258: aload 12
      //   1260: getfield 304	android/util/Pair:first	Ljava/lang/Object;
      //   1263: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1266: pop
      //   1267: aload 17
      //   1269: astore 13
      //   1271: aload 18
      //   1273: astore_1
      //   1274: aload 15
      //   1276: ldc_w 338
      //   1279: aload 12
      //   1281: getfield 309	android/util/Pair:second	Ljava/lang/Object;
      //   1284: checkcast 300	android/util/Pair
      //   1287: getfield 304	android/util/Pair:first	Ljava/lang/Object;
      //   1290: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1293: pop
      //   1294: aload 17
      //   1296: astore 13
      //   1298: aload 18
      //   1300: astore_1
      //   1301: aload 15
      //   1303: ldc_w 340
      //   1306: aload 12
      //   1308: getfield 309	android/util/Pair:second	Ljava/lang/Object;
      //   1311: checkcast 300	android/util/Pair
      //   1314: getfield 309	android/util/Pair:second	Ljava/lang/Object;
      //   1317: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1320: pop
      //   1321: aload 17
      //   1323: astore 13
      //   1325: aload 18
      //   1327: astore_1
      //   1328: aload 15
      //   1330: ldc_w 342
      //   1333: aload 14
      //   1335: getfield 344	com/appodeal/ads/v$a:b	Ljava/lang/String;
      //   1338: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1341: pop
      //   1342: aload 17
      //   1344: astore 13
      //   1346: aload 18
      //   1348: astore_1
      //   1349: aload 15
      //   1351: ldc_w 346
      //   1354: aload 14
      //   1356: getfield 349	com/appodeal/ads/v$a:c	Z
      //   1359: invokevirtual 205	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   1362: pop
      //   1363: aload 17
      //   1365: astore 13
      //   1367: aload 18
      //   1369: astore_1
      //   1370: aload 15
      //   1372: ldc_w 351
      //   1375: aload_0
      //   1376: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   1379: invokestatic 40	com/appodeal/ads/o:a	(Lcom/appodeal/ads/o;)Landroid/content/Context;
      //   1382: invokestatic 353	com/appodeal/ads/v:c	(Landroid/content/Context;)Ljava/lang/String;
      //   1385: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1388: pop
      //   1389: aload 17
      //   1391: astore 13
      //   1393: aload 18
      //   1395: astore_1
      //   1396: aload 15
      //   1398: ldc_w 355
      //   1401: invokestatic 361	java/util/Locale:getDefault	()Ljava/util/Locale;
      //   1404: invokevirtual 364	java/util/Locale:toString	()Ljava/lang/String;
      //   1407: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1410: pop
      //   1411: aload 17
      //   1413: astore 13
      //   1415: aload 18
      //   1417: astore_1
      //   1418: ldc_w 366
      //   1421: invokestatic 372	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
      //   1424: getstatic 376	java/util/Locale:ENGLISH	Ljava/util/Locale;
      //   1427: invokestatic 382	java/util/Calendar:getInstance	(Ljava/util/TimeZone;Ljava/util/Locale;)Ljava/util/Calendar;
      //   1430: astore 12
      //   1432: aload 17
      //   1434: astore 13
      //   1436: aload 18
      //   1438: astore_1
      //   1439: aload 15
      //   1441: ldc_w 384
      //   1444: new 386	java/text/SimpleDateFormat
      //   1447: dup
      //   1448: ldc_w 387
      //   1451: getstatic 376	java/util/Locale:ENGLISH	Ljava/util/Locale;
      //   1454: invokespecial 390	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
      //   1457: aload 12
      //   1459: invokevirtual 394	java/util/Calendar:getTime	()Ljava/util/Date;
      //   1462: invokevirtual 397	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
      //   1465: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1468: pop
      //   1469: aload 17
      //   1471: astore 13
      //   1473: aload 18
      //   1475: astore_1
      //   1476: aload 15
      //   1478: ldc_w 399
      //   1481: ldc_w 401
      //   1484: invokestatic 406	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
      //   1487: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1490: pop
      //   1491: aload 17
      //   1493: astore 13
      //   1495: aload 18
      //   1497: astore_1
      //   1498: aload_0
      //   1499: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   1502: invokestatic 40	com/appodeal/ads/o:a	(Lcom/appodeal/ads/o;)Landroid/content/Context;
      //   1505: invokestatic 410	com/appodeal/ads/utils/b:c	(Landroid/content/Context;)V
      //   1508: aload 17
      //   1510: astore 13
      //   1512: aload 18
      //   1514: astore_1
      //   1515: aload 15
      //   1517: ldc_w 412
      //   1520: aload 19
      //   1522: invokestatic 415	com/appodeal/ads/utils/b:a	(Landroid/content/SharedPreferences;)J
      //   1525: invokevirtual 418	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   1528: pop
      //   1529: aload 17
      //   1531: astore 13
      //   1533: aload 18
      //   1535: astore_1
      //   1536: aload 15
      //   1538: ldc_w 420
      //   1541: invokestatic 422	com/appodeal/ads/utils/b:b	()J
      //   1544: invokevirtual 418	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   1547: pop
      //   1548: aload 17
      //   1550: astore 13
      //   1552: aload 18
      //   1554: astore_1
      //   1555: aload 15
      //   1557: ldc_w 424
      //   1560: aload 19
      //   1562: invokestatic 426	com/appodeal/ads/utils/b:b	(Landroid/content/SharedPreferences;)J
      //   1565: invokevirtual 418	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   1568: pop
      //   1569: aload 17
      //   1571: astore 13
      //   1573: aload 18
      //   1575: astore_1
      //   1576: aload 19
      //   1578: ldc_w 428
      //   1581: aconst_null
      //   1582: invokeinterface 101 3 0
      //   1587: astore 12
      //   1589: aload 12
      //   1591: ifnull +28 -> 1619
      //   1594: aload 17
      //   1596: astore 13
      //   1598: aload 18
      //   1600: astore_1
      //   1601: aload 15
      //   1603: ldc_w 428
      //   1606: new 25	org/json/JSONObject
      //   1609: dup
      //   1610: aload 12
      //   1612: invokespecial 431	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   1615: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1618: pop
      //   1619: aload 17
      //   1621: astore 13
      //   1623: aload 18
      //   1625: astore_1
      //   1626: new 31	org/json/JSONArray
      //   1629: dup
      //   1630: invokespecial 432	org/json/JSONArray:<init>	()V
      //   1633: astore 12
      //   1635: iload_2
      //   1636: ifeq +1015 -> 2651
      //   1639: aload 17
      //   1641: astore 13
      //   1643: aload 18
      //   1645: astore_1
      //   1646: aload_0
      //   1647: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   1650: invokestatic 40	com/appodeal/ads/o:a	(Lcom/appodeal/ads/o;)Landroid/content/Context;
      //   1653: invokestatic 437	com/appodeal/ads/i:a	(Landroid/content/Context;)Ljava/util/List;
      //   1656: invokeinterface 443 1 0
      //   1661: astore 14
      //   1663: aload 17
      //   1665: astore 13
      //   1667: aload 18
      //   1669: astore_1
      //   1670: aload 14
      //   1672: invokeinterface 448 1 0
      //   1677: ifeq +974 -> 2651
      //   1680: aload 17
      //   1682: astore 13
      //   1684: aload 18
      //   1686: astore_1
      //   1687: aload 14
      //   1689: invokeinterface 452 1 0
      //   1694: checkcast 454	com/appodeal/ads/j
      //   1697: astore 16
      //   1699: aload 17
      //   1701: astore 13
      //   1703: aload 18
      //   1705: astore_1
      //   1706: aload 16
      //   1708: invokevirtual 457	com/appodeal/ads/j:i	()Lcom/appodeal/ads/m;
      //   1711: ifnull -48 -> 1663
      //   1714: aload 17
      //   1716: astore 13
      //   1718: aload 18
      //   1720: astore_1
      //   1721: aload 12
      //   1723: aload 16
      //   1725: invokevirtual 459	com/appodeal/ads/j:a	()Ljava/lang/String;
      //   1728: invokevirtual 462	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   1731: pop
      //   1732: goto -69 -> 1663
      //   1735: astore 12
      //   1737: aload 17
      //   1739: astore 13
      //   1741: aload 18
      //   1743: astore_1
      //   1744: aload 12
      //   1746: invokestatic 467	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   1749: aload 17
      //   1751: astore 13
      //   1753: aload 18
      //   1755: astore_1
      //   1756: invokestatic 470	java/util/Calendar:getInstance	()Ljava/util/Calendar;
      //   1759: astore 12
      //   1761: aload 17
      //   1763: astore 13
      //   1765: aload 18
      //   1767: astore_1
      //   1768: aload 12
      //   1770: aload 19
      //   1772: ldc_w 472
      //   1775: lconst_0
      //   1776: invokeinterface 476 4 0
      //   1781: invokevirtual 480	java/util/Calendar:setTimeInMillis	(J)V
      //   1784: aload 17
      //   1786: astore 13
      //   1788: aload 18
      //   1790: astore_1
      //   1791: aload 12
      //   1793: iconst_5
      //   1794: iconst_1
      //   1795: invokevirtual 484	java/util/Calendar:add	(II)V
      //   1798: iload_2
      //   1799: ifne +17 -> 1816
      //   1802: iload 4
      //   1804: ifne +12 -> 1816
      //   1807: iload 5
      //   1809: ifne +7 -> 1816
      //   1812: iload_3
      //   1813: ifeq +171 -> 1984
      //   1816: aload 17
      //   1818: astore 13
      //   1820: aload 18
      //   1822: astore_1
      //   1823: aload 12
      //   1825: invokevirtual 487	java/util/Calendar:getTimeInMillis	()J
      //   1828: lstore 10
      //   1830: aload 17
      //   1832: astore 13
      //   1834: aload 18
      //   1836: astore_1
      //   1837: invokestatic 490	java/lang/System:currentTimeMillis	()J
      //   1840: lstore 8
      //   1842: lload 10
      //   1844: lload 8
      //   1846: lcmp
      //   1847: ifge +137 -> 1984
      //   1850: aload 18
      //   1852: astore_1
      //   1853: new 31	org/json/JSONArray
      //   1856: dup
      //   1857: invokespecial 432	org/json/JSONArray:<init>	()V
      //   1860: astore 12
      //   1862: aload 18
      //   1864: astore_1
      //   1865: aload 20
      //   1867: iconst_0
      //   1868: invokevirtual 494	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
      //   1871: astore 14
      //   1873: aload 18
      //   1875: astore_1
      //   1876: ldc_w 496
      //   1879: invokestatic 502	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
      //   1882: astore 13
      //   1884: aload 18
      //   1886: astore_1
      //   1887: aload 14
      //   1889: invokeinterface 443 1 0
      //   1894: astore 16
      //   1896: aload 18
      //   1898: astore_1
      //   1899: aload 16
      //   1901: invokeinterface 448 1 0
      //   1906: ifeq +1196 -> 3102
      //   1909: aload 18
      //   1911: astore_1
      //   1912: aload 16
      //   1914: invokeinterface 452 1 0
      //   1919: checkcast 259	android/content/pm/ApplicationInfo
      //   1922: getfield 505	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   1925: astore 14
      //   1927: aload 18
      //   1929: astore_1
      //   1930: aload 13
      //   1932: aload 14
      //   1934: invokevirtual 509	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
      //   1937: invokevirtual 514	java/util/regex/Matcher:matches	()Z
      //   1940: ifne -44 -> 1896
      //   1943: aload 18
      //   1945: astore_1
      //   1946: aload 14
      //   1948: ldc -42
      //   1950: invokevirtual 180	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1953: ifne -57 -> 1896
      //   1956: aload 18
      //   1958: astore_1
      //   1959: aload 12
      //   1961: aload 14
      //   1963: invokevirtual 462	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   1966: pop
      //   1967: goto -71 -> 1896
      //   1970: astore 12
      //   1972: aload 17
      //   1974: astore 13
      //   1976: aload 18
      //   1978: astore_1
      //   1979: aload 12
      //   1981: invokestatic 467	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   1984: aload 17
      //   1986: astore 13
      //   1988: aload 18
      //   1990: astore_1
      //   1991: aload_0
      //   1992: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   1995: invokestatic 518	com/appodeal/ads/o:g	(Lcom/appodeal/ads/o;)Lcom/appodeal/ads/open_rtb/h;
      //   1998: ifnull +55 -> 2053
      //   2001: aload 17
      //   2003: astore 13
      //   2005: aload 18
      //   2007: astore_1
      //   2008: aload_0
      //   2009: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   2012: invokestatic 172	com/appodeal/ads/o:b	(Lcom/appodeal/ads/o;)Ljava/lang/String;
      //   2015: ldc_w 520
      //   2018: invokevirtual 180	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2021: ifeq +1215 -> 3236
      //   2024: aload 17
      //   2026: astore 13
      //   2028: aload 18
      //   2030: astore_1
      //   2031: aload 15
      //   2033: ldc_w 522
      //   2036: aload_0
      //   2037: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   2040: invokestatic 518	com/appodeal/ads/o:g	(Lcom/appodeal/ads/o;)Lcom/appodeal/ads/open_rtb/h;
      //   2043: invokevirtual 526	com/appodeal/ads/open_rtb/h:a	()Lorg/json/JSONObject;
      //   2046: invokevirtual 527	org/json/JSONObject:toString	()Ljava/lang/String;
      //   2049: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2052: pop
      //   2053: aload 17
      //   2055: astore 13
      //   2057: aload 18
      //   2059: astore_1
      //   2060: aload_0
      //   2061: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   2064: invokestatic 172	com/appodeal/ads/o:b	(Lcom/appodeal/ads/o;)Ljava/lang/String;
      //   2067: ldc_w 520
      //   2070: invokevirtual 180	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2073: ifne +1622 -> 3695
      //   2076: aload 17
      //   2078: astore 13
      //   2080: aload 18
      //   2082: astore_1
      //   2083: aload_0
      //   2084: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   2087: invokestatic 172	com/appodeal/ads/o:b	(Lcom/appodeal/ads/o;)Ljava/lang/String;
      //   2090: ldc_w 529
      //   2093: invokevirtual 180	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2096: ifne +1599 -> 3695
      //   2099: aload 17
      //   2101: astore 13
      //   2103: aload 18
      //   2105: astore_1
      //   2106: aload_0
      //   2107: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   2110: invokestatic 172	com/appodeal/ads/o:b	(Lcom/appodeal/ads/o;)Ljava/lang/String;
      //   2113: ldc_w 531
      //   2116: invokevirtual 180	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2119: ifne +1576 -> 3695
      //   2122: iconst_1
      //   2123: istore_2
      //   2124: iload_2
      //   2125: ifeq +1288 -> 3413
      //   2128: aload 17
      //   2130: astore 13
      //   2132: aload 18
      //   2134: astore_1
      //   2135: new 533	java/net/URL
      //   2138: dup
      //   2139: ldc_w 535
      //   2142: iconst_3
      //   2143: anewarray 112	java/lang/Object
      //   2146: dup
      //   2147: iconst_0
      //   2148: invokestatic 539	com/appodeal/ads/utils/c:b	()Ljava/lang/String;
      //   2151: aastore
      //   2152: dup
      //   2153: iconst_1
      //   2154: invokestatic 541	com/appodeal/ads/utils/c:c	()I
      //   2157: invokestatic 546	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   2160: aastore
      //   2161: dup
      //   2162: iconst_2
      //   2163: ldc_w 548
      //   2166: aastore
      //   2167: invokestatic 331	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   2170: invokespecial 549	java/net/URL:<init>	(Ljava/lang/String;)V
      //   2173: astore 12
      //   2175: aload 17
      //   2177: astore 13
      //   2179: aload 18
      //   2181: astore_1
      //   2182: aload 12
      //   2184: invokevirtual 553	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   2187: checkcast 555	java/net/HttpURLConnection
      //   2190: astore 12
      //   2192: aload 12
      //   2194: sipush 20000
      //   2197: invokevirtual 559	java/net/HttpURLConnection:setConnectTimeout	(I)V
      //   2200: aload 12
      //   2202: sipush 20000
      //   2205: invokevirtual 562	java/net/HttpURLConnection:setReadTimeout	(I)V
      //   2208: aload 12
      //   2210: ldc_w 564
      //   2213: ldc_w 566
      //   2216: invokevirtual 570	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   2219: aload 12
      //   2221: iconst_1
      //   2222: invokevirtual 574	java/net/HttpURLConnection:setDoOutput	(Z)V
      //   2225: new 576	java/io/ByteArrayOutputStream
      //   2228: dup
      //   2229: invokespecial 577	java/io/ByteArrayOutputStream:<init>	()V
      //   2232: astore 13
      //   2234: new 579	java/util/zip/GZIPOutputStream
      //   2237: dup
      //   2238: aload 13
      //   2240: invokespecial 582	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   2243: astore_1
      //   2244: aload_1
      //   2245: aload 15
      //   2247: invokevirtual 527	org/json/JSONObject:toString	()Ljava/lang/String;
      //   2250: ldc_w 584
      //   2253: invokevirtual 588	java/lang/String:getBytes	(Ljava/lang/String;)[B
      //   2256: invokevirtual 592	java/util/zip/GZIPOutputStream:write	([B)V
      //   2259: aload_1
      //   2260: invokevirtual 595	java/util/zip/GZIPOutputStream:close	()V
      //   2263: aload 13
      //   2265: invokevirtual 599	java/io/ByteArrayOutputStream:toByteArray	()[B
      //   2268: iconst_0
      //   2269: invokestatic 605	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
      //   2272: astore_1
      //   2273: aload 12
      //   2275: invokevirtual 609	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   2278: aload_1
      //   2279: invokestatic 612	com/appodeal/ads/v:a	(Ljava/io/OutputStream;Ljava/lang/String;)V
      //   2282: aload 12
      //   2284: invokevirtual 616	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   2287: invokestatic 619	com/appodeal/ads/v:a	(Ljava/io/InputStream;)Ljava/lang/String;
      //   2290: astore_1
      //   2291: aload_1
      //   2292: ifnull +24 -> 2316
      //   2295: aload_1
      //   2296: invokevirtual 622	java/lang/String:isEmpty	()Z
      //   2299: ifne +17 -> 2316
      //   2302: aload_1
      //   2303: ldc_w 624
      //   2306: invokevirtual 180	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2309: istore 7
      //   2311: iload 7
      //   2313: ifeq +5 -> 2318
      //   2316: aconst_null
      //   2317: astore_1
      //   2318: aload_1
      //   2319: astore 14
      //   2321: aload 12
      //   2323: astore 13
      //   2325: aload 12
      //   2327: astore_1
      //   2328: aload_0
      //   2329: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   2332: invokestatic 40	com/appodeal/ads/o:a	(Lcom/appodeal/ads/o;)Landroid/content/Context;
      //   2335: ldc_w 626
      //   2338: iconst_0
      //   2339: invokevirtual 89	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   2342: astore 15
      //   2344: aload 14
      //   2346: ifnonnull +1167 -> 3513
      //   2349: iload_2
      //   2350: ifeq +1149 -> 3499
      //   2353: aload 12
      //   2355: astore 13
      //   2357: aload 12
      //   2359: astore_1
      //   2360: aload 15
      //   2362: aload_0
      //   2363: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   2366: invokestatic 172	com/appodeal/ads/o:b	(Lcom/appodeal/ads/o;)Ljava/lang/String;
      //   2369: invokeinterface 630 2 0
      //   2374: ifeq +1125 -> 3499
      //   2377: aload 12
      //   2379: astore 13
      //   2381: aload 12
      //   2383: astore_1
      //   2384: ldc_w 632
      //   2387: invokestatic 634	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   2390: aload 12
      //   2392: astore 13
      //   2394: aload 12
      //   2396: astore_1
      //   2397: aload 15
      //   2399: aload_0
      //   2400: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   2403: invokestatic 172	com/appodeal/ads/o:b	(Lcom/appodeal/ads/o;)Ljava/lang/String;
      //   2406: ldc_w 636
      //   2409: invokeinterface 101 3 0
      //   2414: astore 14
      //   2416: aload 12
      //   2418: astore 13
      //   2420: aload 12
      //   2422: astore_1
      //   2423: new 25	org/json/JSONObject
      //   2426: dup
      //   2427: aload 14
      //   2429: invokespecial 431	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   2432: astore 14
      //   2434: aload 12
      //   2436: astore 13
      //   2438: aload 12
      //   2440: astore_1
      //   2441: aload 14
      //   2443: invokestatic 637	com/appodeal/ads/Appodeal:a	(Lorg/json/JSONObject;)V
      //   2446: aload 14
      //   2448: ifnull +35 -> 2483
      //   2451: aload 12
      //   2453: astore_1
      //   2454: aload 14
      //   2456: ldc_w 639
      //   2459: invokevirtual 643	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   2462: putstatic 649	com/appodeal/ads/UserSettings:userData	Lorg/json/JSONObject;
      //   2465: aload 12
      //   2467: astore_1
      //   2468: getstatic 70	com/appodeal/ads/segments/e:a	Ljava/lang/Long;
      //   2471: ifnonnull +12 -> 2483
      //   2474: aload 12
      //   2476: astore_1
      //   2477: aload_0
      //   2478: aload 14
      //   2480: invokespecial 651	com/appodeal/ads/o$b:b	(Lorg/json/JSONObject;)V
      //   2483: aload 14
      //   2485: astore_1
      //   2486: aload 12
      //   2488: ifnull -2403 -> 85
      //   2491: aload 12
      //   2493: invokevirtual 654	java/net/HttpURLConnection:disconnect	()V
      //   2496: aload 14
      //   2498: areturn
      //   2499: ldc_w 656
      //   2502: astore 12
      //   2504: goto -2243 -> 261
      //   2507: astore 12
      //   2509: aload 17
      //   2511: astore 13
      //   2513: aload 18
      //   2515: astore_1
      //   2516: aload 12
      //   2518: invokestatic 467	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2521: aload 14
      //   2523: astore 12
      //   2525: aload 15
      //   2527: astore_1
      //   2528: goto -2180 -> 348
      //   2531: iconst_0
      //   2532: istore_2
      //   2533: goto -2099 -> 434
      //   2536: iconst_0
      //   2537: istore_3
      //   2538: goto -2057 -> 481
      //   2541: iconst_0
      //   2542: istore 4
      //   2544: goto -2016 -> 528
      //   2547: iconst_0
      //   2548: istore 5
      //   2550: goto -1975 -> 575
      //   2553: iconst_0
      //   2554: istore 6
      //   2556: goto -1886 -> 670
      //   2559: astore 21
      //   2561: aload 17
      //   2563: astore 13
      //   2565: aload 18
      //   2567: astore_1
      //   2568: aload 21
      //   2570: invokestatic 467	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2573: goto -1638 -> 935
      //   2576: astore_1
      //   2577: aload 13
      //   2579: astore 12
      //   2581: aload_1
      //   2582: astore 13
      //   2584: aload 12
      //   2586: astore_1
      //   2587: aload 13
      //   2589: invokestatic 467	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2592: aconst_null
      //   2593: astore_1
      //   2594: aload 12
      //   2596: ifnull -2511 -> 85
      //   2599: aload 12
      //   2601: invokevirtual 654	java/net/HttpURLConnection:disconnect	()V
      //   2604: aconst_null
      //   2605: areturn
      //   2606: astore 16
      //   2608: aload 17
      //   2610: astore 13
      //   2612: aload 18
      //   2614: astore_1
      //   2615: aload 16
      //   2617: invokestatic 467	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2620: goto -1655 -> 965
      //   2623: astore 13
      //   2625: aload_1
      //   2626: astore 12
      //   2628: aload 13
      //   2630: astore_1
      //   2631: aload 12
      //   2633: ifnull +8 -> 2641
      //   2636: aload 12
      //   2638: invokevirtual 654	java/net/HttpURLConnection:disconnect	()V
      //   2641: aload_1
      //   2642: athrow
      //   2643: ldc_w 658
      //   2646: astore 12
      //   2648: goto -1477 -> 1171
      //   2651: iload 4
      //   2653: ifeq +99 -> 2752
      //   2656: aload 17
      //   2658: astore 13
      //   2660: aload 18
      //   2662: astore_1
      //   2663: aload_0
      //   2664: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   2667: invokestatic 40	com/appodeal/ads/o:a	(Lcom/appodeal/ads/o;)Landroid/content/Context;
      //   2670: invokestatic 661	com/appodeal/ads/p:a	(Landroid/content/Context;)Ljava/util/List;
      //   2673: invokeinterface 443 1 0
      //   2678: astore 14
      //   2680: aload 17
      //   2682: astore 13
      //   2684: aload 18
      //   2686: astore_1
      //   2687: aload 14
      //   2689: invokeinterface 448 1 0
      //   2694: ifeq +58 -> 2752
      //   2697: aload 17
      //   2699: astore 13
      //   2701: aload 18
      //   2703: astore_1
      //   2704: aload 14
      //   2706: invokeinterface 452 1 0
      //   2711: checkcast 663	com/appodeal/ads/x
      //   2714: astore 16
      //   2716: aload 17
      //   2718: astore 13
      //   2720: aload 18
      //   2722: astore_1
      //   2723: aload 16
      //   2725: invokevirtual 666	com/appodeal/ads/x:i	()Lcom/appodeal/ads/y;
      //   2728: ifnull -48 -> 2680
      //   2731: aload 17
      //   2733: astore 13
      //   2735: aload 18
      //   2737: astore_1
      //   2738: aload 12
      //   2740: aload 16
      //   2742: invokevirtual 667	com/appodeal/ads/x:a	()Ljava/lang/String;
      //   2745: invokevirtual 462	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   2748: pop
      //   2749: goto -69 -> 2680
      //   2752: iload 5
      //   2754: ifeq +99 -> 2853
      //   2757: aload 17
      //   2759: astore 13
      //   2761: aload 18
      //   2763: astore_1
      //   2764: aload_0
      //   2765: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   2768: invokestatic 40	com/appodeal/ads/o:a	(Lcom/appodeal/ads/o;)Landroid/content/Context;
      //   2771: invokestatic 670	com/appodeal/ads/s:a	(Landroid/content/Context;)Ljava/util/List;
      //   2774: invokeinterface 443 1 0
      //   2779: astore 14
      //   2781: aload 17
      //   2783: astore 13
      //   2785: aload 18
      //   2787: astore_1
      //   2788: aload 14
      //   2790: invokeinterface 448 1 0
      //   2795: ifeq +58 -> 2853
      //   2798: aload 17
      //   2800: astore 13
      //   2802: aload 18
      //   2804: astore_1
      //   2805: aload 14
      //   2807: invokeinterface 452 1 0
      //   2812: checkcast 663	com/appodeal/ads/x
      //   2815: astore 16
      //   2817: aload 17
      //   2819: astore 13
      //   2821: aload 18
      //   2823: astore_1
      //   2824: aload 16
      //   2826: invokevirtual 666	com/appodeal/ads/x:i	()Lcom/appodeal/ads/y;
      //   2829: ifnull -48 -> 2781
      //   2832: aload 17
      //   2834: astore 13
      //   2836: aload 18
      //   2838: astore_1
      //   2839: aload 12
      //   2841: aload 16
      //   2843: invokevirtual 667	com/appodeal/ads/x:a	()Ljava/lang/String;
      //   2846: invokevirtual 462	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   2849: pop
      //   2850: goto -69 -> 2781
      //   2853: iload_3
      //   2854: ifeq +99 -> 2953
      //   2857: aload 17
      //   2859: astore 13
      //   2861: aload 18
      //   2863: astore_1
      //   2864: aload_0
      //   2865: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   2868: invokestatic 40	com/appodeal/ads/o:a	(Lcom/appodeal/ads/o;)Landroid/content/Context;
      //   2871: invokestatic 673	com/appodeal/ads/b:a	(Landroid/content/Context;)Ljava/util/List;
      //   2874: invokeinterface 443 1 0
      //   2879: astore 14
      //   2881: aload 17
      //   2883: astore 13
      //   2885: aload 18
      //   2887: astore_1
      //   2888: aload 14
      //   2890: invokeinterface 448 1 0
      //   2895: ifeq +58 -> 2953
      //   2898: aload 17
      //   2900: astore 13
      //   2902: aload 18
      //   2904: astore_1
      //   2905: aload 14
      //   2907: invokeinterface 452 1 0
      //   2912: checkcast 675	com/appodeal/ads/c
      //   2915: astore 16
      //   2917: aload 17
      //   2919: astore 13
      //   2921: aload 18
      //   2923: astore_1
      //   2924: aload 16
      //   2926: invokevirtual 678	com/appodeal/ads/c:g	()Lcom/appodeal/ads/f;
      //   2929: ifnull -48 -> 2881
      //   2932: aload 17
      //   2934: astore 13
      //   2936: aload 18
      //   2938: astore_1
      //   2939: aload 12
      //   2941: aload 16
      //   2943: invokevirtual 679	com/appodeal/ads/c:a	()Ljava/lang/String;
      //   2946: invokevirtual 462	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   2949: pop
      //   2950: goto -69 -> 2881
      //   2953: aload 17
      //   2955: astore 13
      //   2957: aload 18
      //   2959: astore_1
      //   2960: aload 15
      //   2962: ldc_w 681
      //   2965: aload 12
      //   2967: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2970: pop
      //   2971: aload 17
      //   2973: astore 13
      //   2975: aload 18
      //   2977: astore_1
      //   2978: aload_0
      //   2979: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   2982: invokestatic 683	com/appodeal/ads/o:c	(Lcom/appodeal/ads/o;)Ljava/lang/String;
      //   2985: ifnull +26 -> 3011
      //   2988: aload 17
      //   2990: astore 13
      //   2992: aload 18
      //   2994: astore_1
      //   2995: aload 15
      //   2997: ldc_w 685
      //   3000: aload_0
      //   3001: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   3004: invokestatic 683	com/appodeal/ads/o:c	(Lcom/appodeal/ads/o;)Ljava/lang/String;
      //   3007: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3010: pop
      //   3011: aload 17
      //   3013: astore 13
      //   3015: aload 18
      //   3017: astore_1
      //   3018: aload_0
      //   3019: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   3022: invokestatic 688	com/appodeal/ads/o:d	(Lcom/appodeal/ads/o;)J
      //   3025: lconst_0
      //   3026: lcmp
      //   3027: ifeq +26 -> 3053
      //   3030: aload 17
      //   3032: astore 13
      //   3034: aload 18
      //   3036: astore_1
      //   3037: aload 15
      //   3039: ldc_w 690
      //   3042: aload_0
      //   3043: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   3046: invokestatic 688	com/appodeal/ads/o:d	(Lcom/appodeal/ads/o;)J
      //   3049: invokevirtual 418	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   3052: pop
      //   3053: aload 17
      //   3055: astore 13
      //   3057: aload 18
      //   3059: astore_1
      //   3060: aload 15
      //   3062: ldc_w 522
      //   3065: aload_0
      //   3066: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   3069: invokestatic 693	com/appodeal/ads/o:e	(Lcom/appodeal/ads/o;)Ljava/lang/String;
      //   3072: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3075: pop
      //   3076: aload 17
      //   3078: astore 13
      //   3080: aload 18
      //   3082: astore_1
      //   3083: aload 15
      //   3085: ldc_w 695
      //   3088: aload_0
      //   3089: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   3092: invokestatic 697	com/appodeal/ads/o:f	(Lcom/appodeal/ads/o;)Ljava/lang/String;
      //   3095: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3098: pop
      //   3099: goto -1350 -> 1749
      //   3102: aload 18
      //   3104: astore_1
      //   3105: aload 15
      //   3107: ldc_w 699
      //   3110: aload 12
      //   3112: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3115: pop
      //   3116: aload 18
      //   3118: astore_1
      //   3119: aload 15
      //   3121: ldc_w 701
      //   3124: getstatic 704	com/appodeal/ads/Appodeal:b	Landroid/app/Activity;
      //   3127: invokestatic 709	com/appodeal/ads/utils/g:a	(Landroid/app/Activity;)Lorg/json/JSONArray;
      //   3130: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3133: pop
      //   3134: aload 18
      //   3136: astore_1
      //   3137: aload 15
      //   3139: ldc_w 711
      //   3142: aload_0
      //   3143: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   3146: invokestatic 40	com/appodeal/ads/o:a	(Lcom/appodeal/ads/o;)Landroid/content/Context;
      //   3149: invokestatic 715	com/appodeal/ads/utils/h:a	(Landroid/content/Context;)Ljava/lang/String;
      //   3152: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3155: pop
      //   3156: aload 18
      //   3158: astore_1
      //   3159: aload 15
      //   3161: ldc_w 717
      //   3164: aload_0
      //   3165: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   3168: invokestatic 40	com/appodeal/ads/o:a	(Lcom/appodeal/ads/o;)Landroid/content/Context;
      //   3171: invokestatic 721	com/appodeal/ads/Appodeal:getUserSettings	(Landroid/content/Context;)Lcom/appodeal/ads/UserSettings;
      //   3174: invokevirtual 722	com/appodeal/ads/UserSettings:a	()Lorg/json/JSONObject;
      //   3177: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3180: pop
      //   3181: aload 18
      //   3183: astore_1
      //   3184: aload 19
      //   3186: invokeinterface 153 1 0
      //   3191: astore 12
      //   3193: aload 18
      //   3195: astore_1
      //   3196: aload 12
      //   3198: ldc_w 472
      //   3201: invokestatic 490	java/lang/System:currentTimeMillis	()J
      //   3204: invokeinterface 726 4 0
      //   3209: pop
      //   3210: aload 18
      //   3212: astore_1
      //   3213: aload 12
      //   3215: invokeinterface 162 1 0
      //   3220: goto -1236 -> 1984
      //   3223: astore 12
      //   3225: aload 18
      //   3227: astore_1
      //   3228: aload 12
      //   3230: invokestatic 467	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   3233: goto -52 -> 3181
      //   3236: aload 17
      //   3238: astore 13
      //   3240: aload 18
      //   3242: astore_1
      //   3243: aload_0
      //   3244: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   3247: invokestatic 172	com/appodeal/ads/o:b	(Lcom/appodeal/ads/o;)Ljava/lang/String;
      //   3250: ldc_w 529
      //   3253: invokevirtual 180	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3256: ifeq +105 -> 3361
      //   3259: aload 17
      //   3261: astore 13
      //   3263: aload 18
      //   3265: astore_1
      //   3266: aload_0
      //   3267: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   3270: invokestatic 518	com/appodeal/ads/o:g	(Lcom/appodeal/ads/o;)Lcom/appodeal/ads/open_rtb/h;
      //   3273: invokevirtual 728	com/appodeal/ads/open_rtb/h:f	()Z
      //   3276: ifne +30 -> 3306
      //   3279: aload 17
      //   3281: astore 13
      //   3283: aload 18
      //   3285: astore_1
      //   3286: ldc_w 730
      //   3289: invokestatic 634	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   3292: aconst_null
      //   3293: astore_1
      //   3294: iconst_0
      //   3295: ifeq -3210 -> 85
      //   3298: new 103	java/lang/NullPointerException
      //   3301: dup
      //   3302: invokespecial 104	java/lang/NullPointerException:<init>	()V
      //   3305: athrow
      //   3306: aload 17
      //   3308: astore 13
      //   3310: aload 18
      //   3312: astore_1
      //   3313: aload 15
      //   3315: ldc_w 732
      //   3318: aload_0
      //   3319: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   3322: invokestatic 518	com/appodeal/ads/o:g	(Lcom/appodeal/ads/o;)Lcom/appodeal/ads/open_rtb/h;
      //   3325: invokevirtual 734	com/appodeal/ads/open_rtb/h:e	()Lorg/json/JSONObject;
      //   3328: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3331: pop
      //   3332: aload 17
      //   3334: astore 13
      //   3336: aload 18
      //   3338: astore_1
      //   3339: aload 15
      //   3341: ldc_w 736
      //   3344: aload_0
      //   3345: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   3348: invokestatic 518	com/appodeal/ads/o:g	(Lcom/appodeal/ads/o;)Lcom/appodeal/ads/open_rtb/h;
      //   3351: invokevirtual 738	com/appodeal/ads/open_rtb/h:g	()Ljava/lang/String;
      //   3354: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3357: pop
      //   3358: goto -1305 -> 2053
      //   3361: aload 17
      //   3363: astore 13
      //   3365: aload 18
      //   3367: astore_1
      //   3368: aload_0
      //   3369: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   3372: invokestatic 172	com/appodeal/ads/o:b	(Lcom/appodeal/ads/o;)Ljava/lang/String;
      //   3375: ldc_w 531
      //   3378: invokevirtual 180	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3381: ifeq -1328 -> 2053
      //   3384: aload 17
      //   3386: astore 13
      //   3388: aload 18
      //   3390: astore_1
      //   3391: aload 15
      //   3393: ldc_w 522
      //   3396: aload_0
      //   3397: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   3400: invokestatic 518	com/appodeal/ads/o:g	(Lcom/appodeal/ads/o;)Lcom/appodeal/ads/open_rtb/h;
      //   3403: invokevirtual 738	com/appodeal/ads/open_rtb/h:g	()Ljava/lang/String;
      //   3406: invokevirtual 200	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3409: pop
      //   3410: goto -1357 -> 2053
      //   3413: aload 17
      //   3415: astore 13
      //   3417: aload 18
      //   3419: astore_1
      //   3420: new 533	java/net/URL
      //   3423: dup
      //   3424: ldc_w 535
      //   3427: iconst_3
      //   3428: anewarray 112	java/lang/Object
      //   3431: dup
      //   3432: iconst_0
      //   3433: invokestatic 539	com/appodeal/ads/utils/c:b	()Ljava/lang/String;
      //   3436: aastore
      //   3437: dup
      //   3438: iconst_1
      //   3439: invokestatic 541	com/appodeal/ads/utils/c:c	()I
      //   3442: invokestatic 546	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   3445: aastore
      //   3446: dup
      //   3447: iconst_2
      //   3448: aload_0
      //   3449: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   3452: invokestatic 172	com/appodeal/ads/o:b	(Lcom/appodeal/ads/o;)Ljava/lang/String;
      //   3455: aastore
      //   3456: invokestatic 331	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   3459: invokespecial 549	java/net/URL:<init>	(Ljava/lang/String;)V
      //   3462: astore 12
      //   3464: goto -1289 -> 2175
      //   3467: astore 13
      //   3469: aload_1
      //   3470: invokevirtual 595	java/util/zip/GZIPOutputStream:close	()V
      //   3473: aload 13
      //   3475: athrow
      //   3476: astore 13
      //   3478: aload 12
      //   3480: astore_1
      //   3481: aload 13
      //   3483: astore 12
      //   3485: aload 12
      //   3487: invokestatic 467	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   3490: aconst_null
      //   3491: astore 14
      //   3493: aload_1
      //   3494: astore 12
      //   3496: goto -1175 -> 2321
      //   3499: aconst_null
      //   3500: astore_1
      //   3501: aload 12
      //   3503: ifnull -3418 -> 85
      //   3506: aload 12
      //   3508: invokevirtual 654	java/net/HttpURLConnection:disconnect	()V
      //   3511: aconst_null
      //   3512: areturn
      //   3513: iload_2
      //   3514: ifeq +186 -> 3700
      //   3517: aload 12
      //   3519: astore 13
      //   3521: aload 12
      //   3523: astore_1
      //   3524: aload 15
      //   3526: invokeinterface 153 1 0
      //   3531: astore 15
      //   3533: aload 12
      //   3535: astore 13
      //   3537: aload 12
      //   3539: astore_1
      //   3540: aload 15
      //   3542: aload_0
      //   3543: getfield 13	com/appodeal/ads/o$b:a	Lcom/appodeal/ads/o;
      //   3546: invokestatic 172	com/appodeal/ads/o:b	(Lcom/appodeal/ads/o;)Ljava/lang/String;
      //   3549: aload 14
      //   3551: invokeinterface 159 3 0
      //   3556: pop
      //   3557: aload 12
      //   3559: astore 13
      //   3561: aload 12
      //   3563: astore_1
      //   3564: aload 15
      //   3566: invokeinterface 162 1 0
      //   3571: goto +129 -> 3700
      //   3574: astore 14
      //   3576: aload 12
      //   3578: astore 13
      //   3580: aload 12
      //   3582: astore_1
      //   3583: aload 14
      //   3585: invokestatic 467	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   3588: aconst_null
      //   3589: astore_1
      //   3590: aload 12
      //   3592: ifnull -3507 -> 85
      //   3595: aload 12
      //   3597: invokevirtual 654	java/net/HttpURLConnection:disconnect	()V
      //   3600: aconst_null
      //   3601: areturn
      //   3602: astore 15
      //   3604: aload 12
      //   3606: astore 13
      //   3608: aload 12
      //   3610: astore_1
      //   3611: aload 15
      //   3613: invokestatic 467	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   3616: goto -1133 -> 2483
      //   3619: astore_1
      //   3620: goto -989 -> 2631
      //   3623: astore 13
      //   3625: aload_1
      //   3626: astore 12
      //   3628: aload 13
      //   3630: astore_1
      //   3631: goto -1000 -> 2631
      //   3634: astore 13
      //   3636: goto -1052 -> 2584
      //   3639: astore 13
      //   3641: aload_1
      //   3642: astore 12
      //   3644: goto -1060 -> 2584
      //   3647: astore 12
      //   3649: aconst_null
      //   3650: astore_1
      //   3651: goto -166 -> 3485
      //   3654: aload_1
      //   3655: astore 14
      //   3657: goto -3286 -> 371
      //   3660: iconst_1
      //   3661: istore_2
      //   3662: goto -3228 -> 434
      //   3665: iconst_1
      //   3666: istore_3
      //   3667: goto -3186 -> 481
      //   3670: iconst_1
      //   3671: istore 4
      //   3673: goto -3145 -> 528
      //   3676: iconst_1
      //   3677: istore 5
      //   3679: goto -3104 -> 575
      //   3682: iload 4
      //   3684: ifne -2952 -> 732
      //   3687: iload 5
      //   3689: ifeq -2940 -> 749
      //   3692: goto -2960 -> 732
      //   3695: iconst_0
      //   3696: istore_2
      //   3697: goto -1573 -> 2124
      //   3700: goto -1284 -> 2416
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	3703	0	this	b
      //   0	3703	1	paramVarArgs	Void[]
      //   687	3010	2	i	int
      //   708	2959	3	j	int
      //   1133	2550	4	k	int
      //   749	2939	5	m	int
      //   668	1887	6	n	int
      //   660	1652	7	bool	boolean
      //   1840	5	8	l1	long
      //   1828	15	10	l2	long
      //   123	1599	12	localObject1	Object
      //   1735	10	12	localJSONException1	org.json.JSONException
      //   1759	201	12	localObject2	Object
      //   1970	10	12	localException1	Exception
      //   2173	330	12	localObject3	Object
      //   2507	10	12	localException2	Exception
      //   2523	691	12	localObject4	Object
      //   3223	6	12	localException3	Exception
      //   3462	181	12	localObject5	Object
      //   3647	1	12	localIOException1	java.io.IOException
      //   8	2603	13	localObject6	Object
      //   2623	6	13	localObject7	Object
      //   2658	758	13	localObject8	Object
      //   3467	7	13	localObject9	Object
      //   3476	6	13	localIOException2	java.io.IOException
      //   3519	88	13	localObject10	Object
      //   3623	6	13	localObject11	Object
      //   3634	1	13	localException4	Exception
      //   3639	1	13	localException5	Exception
      //   127	3423	14	localObject12	Object
      //   3574	10	14	localJSONException2	org.json.JSONException
      //   3655	1	14	arrayOfVoid	Void[]
      //   131	3434	15	localObject13	Object
      //   3602	10	15	localException6	Exception
      //   104	1809	16	localObject14	Object
      //   2606	10	16	localException7	Exception
      //   2714	228	16	localObject15	Object
      //   4	3410	17	localObject16	Object
      //   1	3417	18	localObject17	Object
      //   26	3159	19	localSharedPreferences	android.content.SharedPreferences
      //   45	1821	20	localPackageManager	android.content.pm.PackageManager
      //   64	618	21	str	String
      //   2559	10	21	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
      //   218	32	22	localInfo	com.google.android.gms.ads.identifier.AdvertisingIdClient.Info
      // Exception table:
      //   from	to	target	type
      //   677	687	1735	org/json/JSONException
      //   698	708	1735	org/json/JSONException
      //   719	729	1735	org/json/JSONException
      //   739	749	1735	org/json/JSONException
      //   761	770	1735	org/json/JSONException
      //   782	791	1735	org/json/JSONException
      //   798	804	1735	org/json/JSONException
      //   811	820	1735	org/json/JSONException
      //   827	838	1735	org/json/JSONException
      //   845	856	1735	org/json/JSONException
      //   863	873	1735	org/json/JSONException
      //   880	892	1735	org/json/JSONException
      //   899	909	1735	org/json/JSONException
      //   916	935	1735	org/json/JSONException
      //   938	965	1735	org/json/JSONException
      //   972	983	1735	org/json/JSONException
      //   990	1001	1735	org/json/JSONException
      //   1008	1020	1735	org/json/JSONException
      //   1027	1041	1735	org/json/JSONException
      //   1048	1068	1735	org/json/JSONException
      //   1075	1087	1735	org/json/JSONException
      //   1094	1108	1735	org/json/JSONException
      //   1115	1129	1735	org/json/JSONException
      //   1154	1166	1735	org/json/JSONException
      //   1178	1189	1735	org/json/JSONException
      //   1196	1227	1735	org/json/JSONException
      //   1234	1246	1735	org/json/JSONException
      //   1253	1267	1735	org/json/JSONException
      //   1274	1294	1735	org/json/JSONException
      //   1301	1321	1735	org/json/JSONException
      //   1328	1342	1735	org/json/JSONException
      //   1349	1363	1735	org/json/JSONException
      //   1370	1389	1735	org/json/JSONException
      //   1396	1411	1735	org/json/JSONException
      //   1418	1432	1735	org/json/JSONException
      //   1439	1469	1735	org/json/JSONException
      //   1476	1491	1735	org/json/JSONException
      //   1498	1508	1735	org/json/JSONException
      //   1515	1529	1735	org/json/JSONException
      //   1536	1548	1735	org/json/JSONException
      //   1555	1569	1735	org/json/JSONException
      //   1576	1589	1735	org/json/JSONException
      //   1601	1619	1735	org/json/JSONException
      //   1626	1635	1735	org/json/JSONException
      //   1646	1663	1735	org/json/JSONException
      //   1670	1680	1735	org/json/JSONException
      //   1687	1699	1735	org/json/JSONException
      //   1706	1714	1735	org/json/JSONException
      //   1721	1732	1735	org/json/JSONException
      //   2568	2573	1735	org/json/JSONException
      //   2615	2620	1735	org/json/JSONException
      //   2663	2680	1735	org/json/JSONException
      //   2687	2697	1735	org/json/JSONException
      //   2704	2716	1735	org/json/JSONException
      //   2723	2731	1735	org/json/JSONException
      //   2738	2749	1735	org/json/JSONException
      //   2764	2781	1735	org/json/JSONException
      //   2788	2798	1735	org/json/JSONException
      //   2805	2817	1735	org/json/JSONException
      //   2824	2832	1735	org/json/JSONException
      //   2839	2850	1735	org/json/JSONException
      //   2864	2881	1735	org/json/JSONException
      //   2888	2898	1735	org/json/JSONException
      //   2905	2917	1735	org/json/JSONException
      //   2924	2932	1735	org/json/JSONException
      //   2939	2950	1735	org/json/JSONException
      //   2960	2971	1735	org/json/JSONException
      //   2978	2988	1735	org/json/JSONException
      //   2995	3011	1735	org/json/JSONException
      //   3018	3030	1735	org/json/JSONException
      //   3037	3053	1735	org/json/JSONException
      //   3060	3076	1735	org/json/JSONException
      //   3083	3099	1735	org/json/JSONException
      //   1853	1862	1970	java/lang/Exception
      //   1865	1873	1970	java/lang/Exception
      //   1876	1884	1970	java/lang/Exception
      //   1887	1896	1970	java/lang/Exception
      //   1899	1909	1970	java/lang/Exception
      //   1912	1927	1970	java/lang/Exception
      //   1930	1943	1970	java/lang/Exception
      //   1946	1956	1970	java/lang/Exception
      //   1959	1967	1970	java/lang/Exception
      //   3105	3116	1970	java/lang/Exception
      //   3184	3193	1970	java/lang/Exception
      //   3196	3210	1970	java/lang/Exception
      //   3213	3220	1970	java/lang/Exception
      //   3228	3233	1970	java/lang/Exception
      //   149	169	2507	java/lang/Exception
      //   180	197	2507	java/lang/Exception
      //   208	220	2507	java/lang/Exception
      //   231	238	2507	java/lang/Exception
      //   249	257	2507	java/lang/Exception
      //   272	281	2507	java/lang/Exception
      //   292	304	2507	java/lang/Exception
      //   315	327	2507	java/lang/Exception
      //   338	345	2507	java/lang/Exception
      //   916	935	2559	android/content/pm/PackageManager$NameNotFoundException
      //   13	28	2576	java/lang/Exception
      //   35	47	2576	java/lang/Exception
      //   54	66	2576	java/lang/Exception
      //   94	106	2576	java/lang/Exception
      //   113	125	2576	java/lang/Exception
      //   359	371	2576	java/lang/Exception
      //   378	387	2576	java/lang/Exception
      //   394	409	2576	java/lang/Exception
      //   416	431	2576	java/lang/Exception
      //   441	456	2576	java/lang/Exception
      //   463	478	2576	java/lang/Exception
      //   488	503	2576	java/lang/Exception
      //   510	525	2576	java/lang/Exception
      //   535	550	2576	java/lang/Exception
      //   557	572	2576	java/lang/Exception
      //   582	597	2576	java/lang/Exception
      //   604	619	2576	java/lang/Exception
      //   626	641	2576	java/lang/Exception
      //   648	662	2576	java/lang/Exception
      //   677	687	2576	java/lang/Exception
      //   698	708	2576	java/lang/Exception
      //   719	729	2576	java/lang/Exception
      //   739	749	2576	java/lang/Exception
      //   761	770	2576	java/lang/Exception
      //   782	791	2576	java/lang/Exception
      //   798	804	2576	java/lang/Exception
      //   811	820	2576	java/lang/Exception
      //   827	838	2576	java/lang/Exception
      //   845	856	2576	java/lang/Exception
      //   863	873	2576	java/lang/Exception
      //   880	892	2576	java/lang/Exception
      //   899	909	2576	java/lang/Exception
      //   916	935	2576	java/lang/Exception
      //   972	983	2576	java/lang/Exception
      //   990	1001	2576	java/lang/Exception
      //   1008	1020	2576	java/lang/Exception
      //   1027	1041	2576	java/lang/Exception
      //   1048	1068	2576	java/lang/Exception
      //   1075	1087	2576	java/lang/Exception
      //   1094	1108	2576	java/lang/Exception
      //   1115	1129	2576	java/lang/Exception
      //   1154	1166	2576	java/lang/Exception
      //   1178	1189	2576	java/lang/Exception
      //   1196	1227	2576	java/lang/Exception
      //   1234	1246	2576	java/lang/Exception
      //   1253	1267	2576	java/lang/Exception
      //   1274	1294	2576	java/lang/Exception
      //   1301	1321	2576	java/lang/Exception
      //   1328	1342	2576	java/lang/Exception
      //   1349	1363	2576	java/lang/Exception
      //   1370	1389	2576	java/lang/Exception
      //   1396	1411	2576	java/lang/Exception
      //   1418	1432	2576	java/lang/Exception
      //   1439	1469	2576	java/lang/Exception
      //   1476	1491	2576	java/lang/Exception
      //   1498	1508	2576	java/lang/Exception
      //   1515	1529	2576	java/lang/Exception
      //   1536	1548	2576	java/lang/Exception
      //   1555	1569	2576	java/lang/Exception
      //   1576	1589	2576	java/lang/Exception
      //   1601	1619	2576	java/lang/Exception
      //   1626	1635	2576	java/lang/Exception
      //   1646	1663	2576	java/lang/Exception
      //   1670	1680	2576	java/lang/Exception
      //   1687	1699	2576	java/lang/Exception
      //   1706	1714	2576	java/lang/Exception
      //   1721	1732	2576	java/lang/Exception
      //   1744	1749	2576	java/lang/Exception
      //   1756	1761	2576	java/lang/Exception
      //   1768	1784	2576	java/lang/Exception
      //   1791	1798	2576	java/lang/Exception
      //   1823	1830	2576	java/lang/Exception
      //   1837	1842	2576	java/lang/Exception
      //   1979	1984	2576	java/lang/Exception
      //   1991	2001	2576	java/lang/Exception
      //   2008	2024	2576	java/lang/Exception
      //   2031	2053	2576	java/lang/Exception
      //   2060	2076	2576	java/lang/Exception
      //   2083	2099	2576	java/lang/Exception
      //   2106	2122	2576	java/lang/Exception
      //   2135	2175	2576	java/lang/Exception
      //   2182	2192	2576	java/lang/Exception
      //   2328	2344	2576	java/lang/Exception
      //   2360	2377	2576	java/lang/Exception
      //   2384	2390	2576	java/lang/Exception
      //   2397	2416	2576	java/lang/Exception
      //   2423	2434	2576	java/lang/Exception
      //   2441	2446	2576	java/lang/Exception
      //   2516	2521	2576	java/lang/Exception
      //   2568	2573	2576	java/lang/Exception
      //   2615	2620	2576	java/lang/Exception
      //   2663	2680	2576	java/lang/Exception
      //   2687	2697	2576	java/lang/Exception
      //   2704	2716	2576	java/lang/Exception
      //   2723	2731	2576	java/lang/Exception
      //   2738	2749	2576	java/lang/Exception
      //   2764	2781	2576	java/lang/Exception
      //   2788	2798	2576	java/lang/Exception
      //   2805	2817	2576	java/lang/Exception
      //   2824	2832	2576	java/lang/Exception
      //   2839	2850	2576	java/lang/Exception
      //   2864	2881	2576	java/lang/Exception
      //   2888	2898	2576	java/lang/Exception
      //   2905	2917	2576	java/lang/Exception
      //   2924	2932	2576	java/lang/Exception
      //   2939	2950	2576	java/lang/Exception
      //   2960	2971	2576	java/lang/Exception
      //   2978	2988	2576	java/lang/Exception
      //   2995	3011	2576	java/lang/Exception
      //   3018	3030	2576	java/lang/Exception
      //   3037	3053	2576	java/lang/Exception
      //   3060	3076	2576	java/lang/Exception
      //   3083	3099	2576	java/lang/Exception
      //   3243	3259	2576	java/lang/Exception
      //   3266	3279	2576	java/lang/Exception
      //   3286	3292	2576	java/lang/Exception
      //   3313	3332	2576	java/lang/Exception
      //   3339	3358	2576	java/lang/Exception
      //   3368	3384	2576	java/lang/Exception
      //   3391	3410	2576	java/lang/Exception
      //   3420	3464	2576	java/lang/Exception
      //   3524	3533	2576	java/lang/Exception
      //   3540	3557	2576	java/lang/Exception
      //   3564	3571	2576	java/lang/Exception
      //   3583	3588	2576	java/lang/Exception
      //   3611	3616	2576	java/lang/Exception
      //   938	965	2606	java/lang/Exception
      //   13	28	2623	finally
      //   35	47	2623	finally
      //   54	66	2623	finally
      //   94	106	2623	finally
      //   113	125	2623	finally
      //   149	169	2623	finally
      //   180	197	2623	finally
      //   208	220	2623	finally
      //   231	238	2623	finally
      //   249	257	2623	finally
      //   272	281	2623	finally
      //   292	304	2623	finally
      //   315	327	2623	finally
      //   338	345	2623	finally
      //   359	371	2623	finally
      //   378	387	2623	finally
      //   394	409	2623	finally
      //   416	431	2623	finally
      //   441	456	2623	finally
      //   463	478	2623	finally
      //   488	503	2623	finally
      //   510	525	2623	finally
      //   535	550	2623	finally
      //   557	572	2623	finally
      //   582	597	2623	finally
      //   604	619	2623	finally
      //   626	641	2623	finally
      //   648	662	2623	finally
      //   677	687	2623	finally
      //   698	708	2623	finally
      //   719	729	2623	finally
      //   739	749	2623	finally
      //   761	770	2623	finally
      //   782	791	2623	finally
      //   798	804	2623	finally
      //   811	820	2623	finally
      //   827	838	2623	finally
      //   845	856	2623	finally
      //   863	873	2623	finally
      //   880	892	2623	finally
      //   899	909	2623	finally
      //   916	935	2623	finally
      //   938	965	2623	finally
      //   972	983	2623	finally
      //   990	1001	2623	finally
      //   1008	1020	2623	finally
      //   1027	1041	2623	finally
      //   1048	1068	2623	finally
      //   1075	1087	2623	finally
      //   1094	1108	2623	finally
      //   1115	1129	2623	finally
      //   1154	1166	2623	finally
      //   1178	1189	2623	finally
      //   1196	1227	2623	finally
      //   1234	1246	2623	finally
      //   1253	1267	2623	finally
      //   1274	1294	2623	finally
      //   1301	1321	2623	finally
      //   1328	1342	2623	finally
      //   1349	1363	2623	finally
      //   1370	1389	2623	finally
      //   1396	1411	2623	finally
      //   1418	1432	2623	finally
      //   1439	1469	2623	finally
      //   1476	1491	2623	finally
      //   1498	1508	2623	finally
      //   1515	1529	2623	finally
      //   1536	1548	2623	finally
      //   1555	1569	2623	finally
      //   1576	1589	2623	finally
      //   1601	1619	2623	finally
      //   1626	1635	2623	finally
      //   1646	1663	2623	finally
      //   1670	1680	2623	finally
      //   1687	1699	2623	finally
      //   1706	1714	2623	finally
      //   1721	1732	2623	finally
      //   1744	1749	2623	finally
      //   1756	1761	2623	finally
      //   1768	1784	2623	finally
      //   1791	1798	2623	finally
      //   1823	1830	2623	finally
      //   1837	1842	2623	finally
      //   1853	1862	2623	finally
      //   1865	1873	2623	finally
      //   1876	1884	2623	finally
      //   1887	1896	2623	finally
      //   1899	1909	2623	finally
      //   1912	1927	2623	finally
      //   1930	1943	2623	finally
      //   1946	1956	2623	finally
      //   1959	1967	2623	finally
      //   1979	1984	2623	finally
      //   1991	2001	2623	finally
      //   2008	2024	2623	finally
      //   2031	2053	2623	finally
      //   2060	2076	2623	finally
      //   2083	2099	2623	finally
      //   2106	2122	2623	finally
      //   2135	2175	2623	finally
      //   2182	2192	2623	finally
      //   2328	2344	2623	finally
      //   2360	2377	2623	finally
      //   2384	2390	2623	finally
      //   2397	2416	2623	finally
      //   2423	2434	2623	finally
      //   2441	2446	2623	finally
      //   2454	2465	2623	finally
      //   2468	2474	2623	finally
      //   2477	2483	2623	finally
      //   2516	2521	2623	finally
      //   2568	2573	2623	finally
      //   2587	2592	2623	finally
      //   2615	2620	2623	finally
      //   2663	2680	2623	finally
      //   2687	2697	2623	finally
      //   2704	2716	2623	finally
      //   2723	2731	2623	finally
      //   2738	2749	2623	finally
      //   2764	2781	2623	finally
      //   2788	2798	2623	finally
      //   2805	2817	2623	finally
      //   2824	2832	2623	finally
      //   2839	2850	2623	finally
      //   2864	2881	2623	finally
      //   2888	2898	2623	finally
      //   2905	2917	2623	finally
      //   2924	2932	2623	finally
      //   2939	2950	2623	finally
      //   2960	2971	2623	finally
      //   2978	2988	2623	finally
      //   2995	3011	2623	finally
      //   3018	3030	2623	finally
      //   3037	3053	2623	finally
      //   3060	3076	2623	finally
      //   3083	3099	2623	finally
      //   3105	3116	2623	finally
      //   3119	3134	2623	finally
      //   3137	3156	2623	finally
      //   3159	3181	2623	finally
      //   3184	3193	2623	finally
      //   3196	3210	2623	finally
      //   3213	3220	2623	finally
      //   3228	3233	2623	finally
      //   3243	3259	2623	finally
      //   3266	3279	2623	finally
      //   3286	3292	2623	finally
      //   3313	3332	2623	finally
      //   3339	3358	2623	finally
      //   3368	3384	2623	finally
      //   3391	3410	2623	finally
      //   3420	3464	2623	finally
      //   3524	3533	2623	finally
      //   3540	3557	2623	finally
      //   3564	3571	2623	finally
      //   3583	3588	2623	finally
      //   3611	3616	2623	finally
      //   3119	3134	3223	java/lang/Exception
      //   3137	3156	3223	java/lang/Exception
      //   3159	3181	3223	java/lang/Exception
      //   2244	2259	3467	finally
      //   2192	2244	3476	java/io/IOException
      //   2259	2291	3476	java/io/IOException
      //   2295	2311	3476	java/io/IOException
      //   3469	3476	3476	java/io/IOException
      //   2423	2434	3574	org/json/JSONException
      //   2441	2446	3574	org/json/JSONException
      //   2454	2465	3602	java/lang/Exception
      //   2468	2474	3602	java/lang/Exception
      //   2477	2483	3602	java/lang/Exception
      //   2192	2244	3619	finally
      //   2259	2291	3619	finally
      //   2295	2311	3619	finally
      //   3469	3476	3619	finally
      //   3485	3490	3623	finally
      //   2192	2244	3634	java/lang/Exception
      //   2259	2291	3634	java/lang/Exception
      //   2295	2311	3634	java/lang/Exception
      //   3469	3476	3634	java/lang/Exception
      //   3485	3490	3639	java/lang/Exception
      //   2182	2192	3647	java/io/IOException
    }
    
    protected void a(JSONObject paramJSONObject)
    {
      super.onPostExecute(paramJSONObject);
      try
      {
        if (o.h(o.this) != null)
        {
          if (paramJSONObject == null)
          {
            o.h(o.this).a(o.i(o.this));
            return;
          }
          o.h(o.this).a(paramJSONObject, o.i(o.this), o.b(o.this));
          return;
        }
      }
      catch (Exception paramJSONObject)
      {
        Appodeal.a(paramJSONObject);
      }
    }
  }
}
