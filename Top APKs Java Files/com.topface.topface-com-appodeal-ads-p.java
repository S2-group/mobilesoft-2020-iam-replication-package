package com.appodeal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.appodeal.ads.e.h;
import com.appodeal.ads.g.d;
import com.appodeal.ads.g.e;
import com.appodeal.ads.g.f;
import com.appodeal.ads.h.l;
import org.json.JSONArray;
import org.json.JSONObject;

public class p
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
  
  p(Context paramContext, a paramA, int paramInt, String paramString)
  {
    this(paramContext, paramA, paramInt, paramString, null, null, null, null);
  }
  
  public p(Context paramContext, a paramA, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, h paramH)
  {
    this(paramContext, paramA, paramInt, paramString1, paramString2, paramString3, paramString4, paramH, 0L);
  }
  
  p(Context paramContext, a paramA, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, h paramH, long paramLong)
  {
    this.a = paramA;
    this.b = paramContext;
    this.c = paramInt;
    this.d = paramString1;
    this.e = paramString2;
    this.f = paramString3;
    this.g = paramString4;
    this.h = paramH;
    this.i = paramLong;
    if ((AppodealSettings.a) && ((this.a == null) || ((this.a instanceof l))) && ((this.d.equals("stats")) || (this.d.equals("show")) || (this.d.equals("click")) || (this.d.equals("finish")) || (this.d.equals("install")))) {
      return;
    }
    if ((paramContext instanceof Activity))
    {
      ((Activity)paramContext).runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (Build.VERSION.SDK_INT >= 11)
          {
            new p.b(p.this, null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
          }
          new p.b(p.this, null).execute(new Void[0]);
        }
      });
      return;
    }
    if (Build.VERSION.SDK_INT >= 11)
    {
      new b(null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
      return;
    }
    new b(null).execute(new Void[0]);
  }
  
  public static abstract interface a
  {
    public abstract void a(int paramInt);
    
    public abstract void a(JSONObject paramJSONObject, int paramInt, String paramString);
  }
  
  private class b
    extends AsyncTask<Void, Void, JSONObject>
  {
    private b() {}
    
    private void b(JSONObject paramJSONObject)
    {
      JSONArray localJSONArray = paramJSONObject.optJSONArray("segments");
      if ((localJSONArray == null) || (localJSONArray.length() == 0)) {
        return;
      }
      paramJSONObject = new e(p.a(p.this), paramJSONObject).a(localJSONArray);
      if (paramJSONObject != null)
      {
        new f(p.a(p.this)).a(paramJSONObject.b());
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
      //   1: astore 19
      //   3: aconst_null
      //   4: astore 20
      //   6: aload 20
      //   8: astore 15
      //   10: aload 19
      //   12: astore_1
      //   13: aload_0
      //   14: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   17: invokestatic 41	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   20: ldc 84
      //   22: iconst_0
      //   23: invokevirtual 90	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   26: astore 21
      //   28: aload 20
      //   30: astore 15
      //   32: aload 19
      //   34: astore_1
      //   35: aload_0
      //   36: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   39: invokestatic 41	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   42: invokevirtual 94	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   45: astore 22
      //   47: aload 20
      //   49: astore 15
      //   51: aload 19
      //   53: astore_1
      //   54: aload 21
      //   56: ldc 96
      //   58: aconst_null
      //   59: invokeinterface 102 3 0
      //   64: astore 23
      //   66: aload 23
      //   68: ifnonnull +19 -> 87
      //   71: aconst_null
      //   72: astore_1
      //   73: iconst_0
      //   74: ifeq +11 -> 85
      //   77: new 104	java/lang/NullPointerException
      //   80: dup
      //   81: invokespecial 105	java/lang/NullPointerException:<init>	()V
      //   84: athrow
      //   85: aload_1
      //   86: areturn
      //   87: aload 20
      //   89: astore 15
      //   91: aload 19
      //   93: astore_1
      //   94: aload 21
      //   96: ldc 107
      //   98: aconst_null
      //   99: invokeinterface 102 3 0
      //   104: astore 18
      //   106: aload 20
      //   108: astore 15
      //   110: aload 19
      //   112: astore_1
      //   113: aload 21
      //   115: ldc 109
      //   117: aconst_null
      //   118: invokeinterface 102 3 0
      //   123: astore 14
      //   125: aload 14
      //   127: astore 16
      //   129: aload 18
      //   131: astore 17
      //   133: aload 18
      //   135: ifnonnull +2744 -> 2879
      //   138: aload 14
      //   140: astore 16
      //   142: aload 18
      //   144: astore 17
      //   146: aload 19
      //   148: astore_1
      //   149: ldc 111
      //   151: iconst_0
      //   152: aload_0
      //   153: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   156: invokestatic 41	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   159: invokevirtual 117	java/lang/Object:getClass	()Ljava/lang/Class;
      //   162: invokevirtual 123	java/lang/Class:getClassLoader	()Ljava/lang/ClassLoader;
      //   165: invokestatic 127	java/lang/Class:forName	(Ljava/lang/String;ZLjava/lang/ClassLoader;)Ljava/lang/Class;
      //   168: pop
      //   169: aload 14
      //   171: astore 16
      //   173: aload 18
      //   175: astore 17
      //   177: aload 19
      //   179: astore_1
      //   180: ldc -127
      //   182: ldc -125
      //   184: iconst_1
      //   185: anewarray 119	java/lang/Class
      //   188: dup
      //   189: iconst_0
      //   190: ldc 86
      //   192: aastore
      //   193: invokevirtual 135	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      //   196: pop
      //   197: aload 14
      //   199: astore 16
      //   201: aload 18
      //   203: astore 17
      //   205: aload 19
      //   207: astore_1
      //   208: aload_0
      //   209: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   212: invokestatic 41	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   215: invokestatic 138	com/google/android/gms/ads/identifier/AdvertisingIdClient:getAdvertisingIdInfo	(Landroid/content/Context;)Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;
      //   218: astore 24
      //   220: aload 14
      //   222: astore 16
      //   224: aload 18
      //   226: astore 17
      //   228: aload 19
      //   230: astore_1
      //   231: aload 24
      //   233: invokevirtual 144	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:getId	()Ljava/lang/String;
      //   236: astore 15
      //   238: aload 14
      //   240: astore 16
      //   242: aload 15
      //   244: astore 17
      //   246: aload 19
      //   248: astore_1
      //   249: aload 24
      //   251: invokevirtual 148	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:isLimitAdTrackingEnabled	()Z
      //   254: ifeq +2603 -> 2857
      //   257: ldc -106
      //   259: astore 14
      //   261: aload 14
      //   263: astore 16
      //   265: aload 15
      //   267: astore 17
      //   269: aload 19
      //   271: astore_1
      //   272: aload 21
      //   274: invokeinterface 154 1 0
      //   279: astore 18
      //   281: aload 14
      //   283: astore 16
      //   285: aload 15
      //   287: astore 17
      //   289: aload 19
      //   291: astore_1
      //   292: aload 18
      //   294: ldc 107
      //   296: aload 15
      //   298: invokeinterface 160 3 0
      //   303: pop
      //   304: aload 14
      //   306: astore 16
      //   308: aload 15
      //   310: astore 17
      //   312: aload 19
      //   314: astore_1
      //   315: aload 18
      //   317: ldc 109
      //   319: aload 14
      //   321: invokeinterface 160 3 0
      //   326: pop
      //   327: aload 14
      //   329: astore 16
      //   331: aload 15
      //   333: astore 17
      //   335: aload 19
      //   337: astore_1
      //   338: aload 18
      //   340: invokeinterface 163 1 0
      //   345: aload 14
      //   347: astore 16
      //   349: aload 15
      //   351: astore 17
      //   353: aload 19
      //   355: astore_1
      //   356: ldc -91
      //   358: iconst_1
      //   359: anewarray 113	java/lang/Object
      //   362: dup
      //   363: iconst_0
      //   364: aload 15
      //   366: aastore
      //   367: invokestatic 171	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   370: invokestatic 176	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   373: aload 15
      //   375: astore_1
      //   376: aload_1
      //   377: ifnonnull +3929 -> 4306
      //   380: aload 20
      //   382: astore 15
      //   384: aload 19
      //   386: astore_1
      //   387: aload_0
      //   388: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   391: invokestatic 41	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   394: invokestatic 182	com/appodeal/ads/aj:j	(Landroid/content/Context;)Ljava/lang/String;
      //   397: astore 16
      //   399: aload 20
      //   401: astore 15
      //   403: aload 19
      //   405: astore_1
      //   406: new 26	org/json/JSONObject
      //   409: dup
      //   410: invokespecial 183	org/json/JSONObject:<init>	()V
      //   413: astore 17
      //   415: aload 20
      //   417: astore 15
      //   419: aload 19
      //   421: astore_1
      //   422: aload_0
      //   423: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   426: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   429: ldc -68
      //   431: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   434: ifne +3878 -> 4312
      //   437: aload 20
      //   439: astore 15
      //   441: aload 19
      //   443: astore_1
      //   444: aload_0
      //   445: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   448: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   451: ldc -62
      //   453: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   456: ifeq +2433 -> 2889
      //   459: goto +3853 -> 4312
      //   462: aload 20
      //   464: astore 15
      //   466: aload 19
      //   468: astore_1
      //   469: aload_0
      //   470: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   473: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   476: ldc -60
      //   478: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   481: ifne +3836 -> 4317
      //   484: aload 20
      //   486: astore 15
      //   488: aload 19
      //   490: astore_1
      //   491: aload_0
      //   492: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   495: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   498: ldc -58
      //   500: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   503: ifeq +2391 -> 2894
      //   506: goto +3811 -> 4317
      //   509: aload 20
      //   511: astore 15
      //   513: aload 19
      //   515: astore_1
      //   516: aload_0
      //   517: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   520: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   523: ldc -56
      //   525: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   528: ifne +3794 -> 4322
      //   531: aload 20
      //   533: astore 15
      //   535: aload 19
      //   537: astore_1
      //   538: aload_0
      //   539: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   542: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   545: ldc -54
      //   547: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   550: ifeq +2349 -> 2899
      //   553: goto +3769 -> 4322
      //   556: aload 20
      //   558: astore 15
      //   560: aload 19
      //   562: astore_1
      //   563: aload_0
      //   564: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   567: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   570: ldc -52
      //   572: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   575: ifne +3753 -> 4328
      //   578: aload 20
      //   580: astore 15
      //   582: aload 19
      //   584: astore_1
      //   585: aload_0
      //   586: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   589: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   592: ldc -50
      //   594: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   597: ifeq +2308 -> 2905
      //   600: goto +3728 -> 4328
      //   603: aload 20
      //   605: astore 15
      //   607: aload 19
      //   609: astore_1
      //   610: aload_0
      //   611: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   614: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   617: ldc -48
      //   619: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   622: ifne +3712 -> 4334
      //   625: aload 20
      //   627: astore 15
      //   629: aload 19
      //   631: astore_1
      //   632: aload_0
      //   633: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   636: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   639: ldc -46
      //   641: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   644: ifeq +2267 -> 2911
      //   647: goto +3687 -> 4334
      //   650: aload 20
      //   652: astore 15
      //   654: aload 19
      //   656: astore_1
      //   657: aload_0
      //   658: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   661: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   664: ldc -44
      //   666: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   669: ifne +3671 -> 4340
      //   672: aload 20
      //   674: astore 15
      //   676: aload 19
      //   678: astore_1
      //   679: aload_0
      //   680: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   683: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   686: ldc -42
      //   688: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   691: ifeq +2226 -> 2917
      //   694: goto +3646 -> 4340
      //   697: aload 20
      //   699: astore 15
      //   701: aload 19
      //   703: astore_1
      //   704: aload_0
      //   705: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   708: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   711: ldc -62
      //   713: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   716: ifne +117 -> 833
      //   719: aload 20
      //   721: astore 15
      //   723: aload 19
      //   725: astore_1
      //   726: aload_0
      //   727: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   730: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   733: ldc -58
      //   735: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   738: ifne +95 -> 833
      //   741: aload 20
      //   743: astore 15
      //   745: aload 19
      //   747: astore_1
      //   748: aload_0
      //   749: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   752: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   755: ldc -50
      //   757: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   760: ifne +73 -> 833
      //   763: aload 20
      //   765: astore 15
      //   767: aload 19
      //   769: astore_1
      //   770: aload_0
      //   771: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   774: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   777: ldc -46
      //   779: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   782: ifne +51 -> 833
      //   785: aload 20
      //   787: astore 15
      //   789: aload 19
      //   791: astore_1
      //   792: aload_0
      //   793: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   796: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   799: ldc -54
      //   801: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   804: ifne +29 -> 833
      //   807: aload 20
      //   809: astore 15
      //   811: aload 19
      //   813: astore_1
      //   814: aload_0
      //   815: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   818: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   821: ldc -42
      //   823: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   826: istore 9
      //   828: iload 9
      //   830: ifeq +2093 -> 2923
      //   833: iconst_1
      //   834: istore 8
      //   836: aload 20
      //   838: astore 15
      //   840: aload 19
      //   842: astore_1
      //   843: aload 17
      //   845: ldc -40
      //   847: aload 23
      //   849: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   852: pop
      //   853: iload_2
      //   854: ifeq +20 -> 874
      //   857: aload 20
      //   859: astore 15
      //   861: aload 19
      //   863: astore_1
      //   864: aload 17
      //   866: ldc -34
      //   868: ldc -68
      //   870: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   873: pop
      //   874: iload_3
      //   875: ifeq +20 -> 895
      //   878: aload 20
      //   880: astore 15
      //   882: aload 19
      //   884: astore_1
      //   885: aload 17
      //   887: ldc -34
      //   889: ldc -60
      //   891: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   894: pop
      //   895: iload 4
      //   897: ifeq +3449 -> 4346
      //   900: aload 20
      //   902: astore 15
      //   904: aload 19
      //   906: astore_1
      //   907: aload 17
      //   909: ldc -34
      //   911: ldc -56
      //   913: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   916: pop
      //   917: goto +3429 -> 4346
      //   920: aload 20
      //   922: astore 15
      //   924: aload 19
      //   926: astore_1
      //   927: aload 17
      //   929: ldc -34
      //   931: ldc -52
      //   933: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   936: pop
      //   937: iload 6
      //   939: ifeq +19 -> 958
      //   942: aload 20
      //   944: astore 15
      //   946: aload 19
      //   948: astore_1
      //   949: aload 17
      //   951: ldc -48
      //   953: iconst_1
      //   954: invokevirtual 225	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   957: pop
      //   958: iload 7
      //   960: ifeq +20 -> 980
      //   963: aload 20
      //   965: astore 15
      //   967: aload 19
      //   969: astore_1
      //   970: aload 17
      //   972: ldc -34
      //   974: ldc -44
      //   976: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   979: pop
      //   980: iload 8
      //   982: ifeq +19 -> 1001
      //   985: aload 20
      //   987: astore 15
      //   989: aload 19
      //   991: astore_1
      //   992: aload 17
      //   994: ldc -62
      //   996: iconst_1
      //   997: invokevirtual 225	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   1000: pop
      //   1001: aload 20
      //   1003: astore 15
      //   1005: aload 19
      //   1007: astore_1
      //   1008: getstatic 230	com/appodeal/ads/AppodealSettings:a	Z
      //   1011: ifeq +19 -> 1030
      //   1014: aload 20
      //   1016: astore 15
      //   1018: aload 19
      //   1020: astore_1
      //   1021: aload 17
      //   1023: ldc -24
      //   1025: iconst_1
      //   1026: invokevirtual 225	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   1029: pop
      //   1030: aload 20
      //   1032: astore 15
      //   1034: aload 19
      //   1036: astore_1
      //   1037: aload 17
      //   1039: ldc -22
      //   1041: getstatic 240	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
      //   1044: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1047: pop
      //   1048: aload 20
      //   1050: astore 15
      //   1052: aload 19
      //   1054: astore_1
      //   1055: aload 17
      //   1057: ldc -14
      //   1059: getstatic 246	android/os/Build$VERSION:SDK_INT	I
      //   1062: invokevirtual 249	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
      //   1065: pop
      //   1066: aload 20
      //   1068: astore 15
      //   1070: aload 19
      //   1072: astore_1
      //   1073: aload 17
      //   1075: ldc -5
      //   1077: ldc -3
      //   1079: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1082: pop
      //   1083: aload 20
      //   1085: astore 15
      //   1087: aload 19
      //   1089: astore_1
      //   1090: aload_0
      //   1091: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   1094: invokestatic 41	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   1097: invokevirtual 256	android/content/Context:getPackageName	()Ljava/lang/String;
      //   1100: astore 18
      //   1102: aload 20
      //   1104: astore 15
      //   1106: aload 19
      //   1108: astore_1
      //   1109: aload 17
      //   1111: ldc_w 258
      //   1114: aload 18
      //   1116: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1119: pop
      //   1120: aload 20
      //   1122: astore 15
      //   1124: aload 19
      //   1126: astore_1
      //   1127: aload 17
      //   1129: ldc_w 260
      //   1132: aload 22
      //   1134: aload 18
      //   1136: iconst_0
      //   1137: invokevirtual 266	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
      //   1140: getfield 271	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
      //   1143: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1146: pop
      //   1147: aload 19
      //   1149: astore_1
      //   1150: aload 17
      //   1152: ldc_w 273
      //   1155: aload 22
      //   1157: aload 18
      //   1159: sipush 128
      //   1162: invokevirtual 277	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
      //   1165: getfield 283	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
      //   1168: ldc_w 285
      //   1171: invokevirtual 290	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   1174: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1177: pop
      //   1178: aload 20
      //   1180: astore 15
      //   1182: aload 19
      //   1184: astore_1
      //   1185: aload 17
      //   1187: ldc_w 292
      //   1190: aload 16
      //   1192: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1195: pop
      //   1196: aload 20
      //   1198: astore 15
      //   1200: aload 19
      //   1202: astore_1
      //   1203: aload 17
      //   1205: ldc_w 294
      //   1208: aload 14
      //   1210: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1213: pop
      //   1214: aload 20
      //   1216: astore 15
      //   1218: aload 19
      //   1220: astore_1
      //   1221: aload_0
      //   1222: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   1225: invokestatic 41	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   1228: invokestatic 297	com/appodeal/ads/aj:b	(Landroid/content/Context;)Lcom/appodeal/ads/aj$a;
      //   1231: astore 16
      //   1233: aload 20
      //   1235: astore 15
      //   1237: aload 19
      //   1239: astore_1
      //   1240: aload 17
      //   1242: ldc_w 299
      //   1245: aload 16
      //   1247: getfield 303	com/appodeal/ads/aj$a:a	Ljava/lang/String;
      //   1250: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1253: pop
      //   1254: aload 20
      //   1256: astore 15
      //   1258: aload 19
      //   1260: astore_1
      //   1261: aload 17
      //   1263: ldc_w 305
      //   1266: aload_0
      //   1267: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   1270: invokestatic 41	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   1273: invokestatic 309	com/appodeal/ads/aj:i	(Landroid/content/Context;)F
      //   1276: f2d
      //   1277: invokevirtual 312	org/json/JSONObject:put	(Ljava/lang/String;D)Lorg/json/JSONObject;
      //   1280: pop
      //   1281: aload 20
      //   1283: astore 15
      //   1285: aload 19
      //   1287: astore_1
      //   1288: aload_0
      //   1289: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   1292: invokestatic 41	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   1295: invokestatic 316	com/appodeal/ads/aj:f	(Landroid/content/Context;)Landroid/util/Pair;
      //   1298: astore 14
      //   1300: aload 20
      //   1302: astore 15
      //   1304: aload 19
      //   1306: astore_1
      //   1307: aload 17
      //   1309: ldc_w 318
      //   1312: aload 14
      //   1314: getfield 324	android/util/Pair:first	Ljava/lang/Object;
      //   1317: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1320: pop
      //   1321: aload 20
      //   1323: astore 15
      //   1325: aload 19
      //   1327: astore_1
      //   1328: aload 17
      //   1330: ldc_w 326
      //   1333: aload 14
      //   1335: getfield 329	android/util/Pair:second	Ljava/lang/Object;
      //   1338: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1341: pop
      //   1342: aload 20
      //   1344: astore 15
      //   1346: aload 19
      //   1348: astore_1
      //   1349: aload_0
      //   1350: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   1353: invokestatic 41	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   1356: invokestatic 333	com/appodeal/ads/aj:l	(Landroid/content/Context;)Z
      //   1359: ifeq +1654 -> 3013
      //   1362: aload 20
      //   1364: astore 15
      //   1366: aload 19
      //   1368: astore_1
      //   1369: aload 17
      //   1371: ldc_w 335
      //   1374: ldc_w 337
      //   1377: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1380: pop
      //   1381: aload 20
      //   1383: astore 15
      //   1385: aload 19
      //   1387: astore_1
      //   1388: getstatic 342	android/os/Build:MANUFACTURER	Ljava/lang/String;
      //   1391: ldc_w 344
      //   1394: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1397: ifeq +2962 -> 4359
      //   1400: ldc_w 346
      //   1403: astore 14
      //   1405: aload 20
      //   1407: astore 15
      //   1409: aload 19
      //   1411: astore_1
      //   1412: aload 17
      //   1414: ldc_w 348
      //   1417: aload 14
      //   1419: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1422: pop
      //   1423: aload 19
      //   1425: astore_1
      //   1426: aload 22
      //   1428: aload 18
      //   1430: invokevirtual 351	android/content/pm/PackageManager:getInstallerPackageName	(Ljava/lang/String;)Ljava/lang/String;
      //   1433: astore 15
      //   1435: aload 15
      //   1437: astore 14
      //   1439: aload 15
      //   1441: ifnonnull +8 -> 1449
      //   1444: ldc_w 353
      //   1447: astore 14
      //   1449: aload 19
      //   1451: astore_1
      //   1452: aload 17
      //   1454: ldc_w 355
      //   1457: aload 14
      //   1459: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1462: pop
      //   1463: iload_2
      //   1464: ifne +27 -> 1491
      //   1467: iload 5
      //   1469: ifne +22 -> 1491
      //   1472: iload 6
      //   1474: ifne +17 -> 1491
      //   1477: iload_3
      //   1478: ifne +13 -> 1491
      //   1481: iload 4
      //   1483: ifne +8 -> 1491
      //   1486: iload 7
      //   1488: ifeq +2076 -> 3564
      //   1491: aload 20
      //   1493: astore 15
      //   1495: aload 19
      //   1497: astore_1
      //   1498: aload 17
      //   1500: ldc_w 357
      //   1503: ldc_w 359
      //   1506: iconst_2
      //   1507: anewarray 113	java/lang/Object
      //   1510: dup
      //   1511: iconst_0
      //   1512: getstatic 342	android/os/Build:MANUFACTURER	Ljava/lang/String;
      //   1515: aastore
      //   1516: dup
      //   1517: iconst_1
      //   1518: getstatic 362	android/os/Build:MODEL	Ljava/lang/String;
      //   1521: aastore
      //   1522: invokestatic 171	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   1525: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1528: pop
      //   1529: aload 20
      //   1531: astore 15
      //   1533: aload 19
      //   1535: astore_1
      //   1536: aload_0
      //   1537: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   1540: invokestatic 41	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   1543: invokestatic 365	com/appodeal/ads/aj:d	(Landroid/content/Context;)Landroid/util/Pair;
      //   1546: astore 14
      //   1548: aload 20
      //   1550: astore 15
      //   1552: aload 19
      //   1554: astore_1
      //   1555: aload 17
      //   1557: ldc_w 367
      //   1560: aload 14
      //   1562: getfield 324	android/util/Pair:first	Ljava/lang/Object;
      //   1565: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1568: pop
      //   1569: aload 20
      //   1571: astore 15
      //   1573: aload 19
      //   1575: astore_1
      //   1576: aload 17
      //   1578: ldc_w 369
      //   1581: aload 14
      //   1583: getfield 329	android/util/Pair:second	Ljava/lang/Object;
      //   1586: checkcast 320	android/util/Pair
      //   1589: getfield 324	android/util/Pair:first	Ljava/lang/Object;
      //   1592: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1595: pop
      //   1596: aload 20
      //   1598: astore 15
      //   1600: aload 19
      //   1602: astore_1
      //   1603: aload 17
      //   1605: ldc_w 371
      //   1608: aload 14
      //   1610: getfield 329	android/util/Pair:second	Ljava/lang/Object;
      //   1613: checkcast 320	android/util/Pair
      //   1616: getfield 329	android/util/Pair:second	Ljava/lang/Object;
      //   1619: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1622: pop
      //   1623: aload 20
      //   1625: astore 15
      //   1627: aload 19
      //   1629: astore_1
      //   1630: aload 17
      //   1632: ldc_w 373
      //   1635: aload 16
      //   1637: getfield 375	com/appodeal/ads/aj$a:b	Ljava/lang/String;
      //   1640: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1643: pop
      //   1644: aload 20
      //   1646: astore 15
      //   1648: aload 19
      //   1650: astore_1
      //   1651: aload 17
      //   1653: ldc_w 377
      //   1656: aload 16
      //   1658: getfield 380	com/appodeal/ads/aj$a:c	Z
      //   1661: invokevirtual 225	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   1664: pop
      //   1665: aload 20
      //   1667: astore 15
      //   1669: aload 19
      //   1671: astore_1
      //   1672: aload 17
      //   1674: ldc_w 382
      //   1677: aload_0
      //   1678: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   1681: invokestatic 41	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   1684: invokestatic 384	com/appodeal/ads/aj:c	(Landroid/content/Context;)Ljava/lang/String;
      //   1687: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1690: pop
      //   1691: aload 20
      //   1693: astore 15
      //   1695: aload 19
      //   1697: astore_1
      //   1698: aload 17
      //   1700: ldc_w 386
      //   1703: invokestatic 392	java/util/Locale:getDefault	()Ljava/util/Locale;
      //   1706: invokevirtual 395	java/util/Locale:toString	()Ljava/lang/String;
      //   1709: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1712: pop
      //   1713: aload 20
      //   1715: astore 15
      //   1717: aload 19
      //   1719: astore_1
      //   1720: ldc_w 397
      //   1723: invokestatic 403	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
      //   1726: getstatic 407	java/util/Locale:ENGLISH	Ljava/util/Locale;
      //   1729: invokestatic 413	java/util/Calendar:getInstance	(Ljava/util/TimeZone;Ljava/util/Locale;)Ljava/util/Calendar;
      //   1732: astore 14
      //   1734: aload 20
      //   1736: astore 15
      //   1738: aload 19
      //   1740: astore_1
      //   1741: aload 17
      //   1743: ldc_w 415
      //   1746: new 417	java/text/SimpleDateFormat
      //   1749: dup
      //   1750: ldc_w 418
      //   1753: getstatic 407	java/util/Locale:ENGLISH	Ljava/util/Locale;
      //   1756: invokespecial 421	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
      //   1759: aload 14
      //   1761: invokevirtual 425	java/util/Calendar:getTime	()Ljava/util/Date;
      //   1764: invokevirtual 428	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
      //   1767: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1770: pop
      //   1771: aload 20
      //   1773: astore 15
      //   1775: aload 19
      //   1777: astore_1
      //   1778: aload 17
      //   1780: ldc_w 430
      //   1783: ldc_w 432
      //   1786: invokestatic 437	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
      //   1789: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1792: pop
      //   1793: aload 20
      //   1795: astore 15
      //   1797: aload 19
      //   1799: astore_1
      //   1800: aload_0
      //   1801: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   1804: invokestatic 41	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   1807: invokestatic 441	com/appodeal/ads/h/b:c	(Landroid/content/Context;)V
      //   1810: aload 20
      //   1812: astore 15
      //   1814: aload 19
      //   1816: astore_1
      //   1817: aload 17
      //   1819: ldc_w 443
      //   1822: aload 21
      //   1824: invokestatic 446	com/appodeal/ads/h/b:a	(Landroid/content/SharedPreferences;)J
      //   1827: invokevirtual 449	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   1830: pop
      //   1831: aload 20
      //   1833: astore 15
      //   1835: aload 19
      //   1837: astore_1
      //   1838: aload 17
      //   1840: ldc_w 451
      //   1843: invokestatic 453	com/appodeal/ads/h/b:b	()J
      //   1846: invokevirtual 449	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   1849: pop
      //   1850: aload 20
      //   1852: astore 15
      //   1854: aload 19
      //   1856: astore_1
      //   1857: aload 17
      //   1859: ldc_w 455
      //   1862: aload 21
      //   1864: invokestatic 457	com/appodeal/ads/h/b:b	(Landroid/content/SharedPreferences;)J
      //   1867: invokevirtual 449	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   1870: pop
      //   1871: aload 20
      //   1873: astore 15
      //   1875: aload 19
      //   1877: astore_1
      //   1878: aload 21
      //   1880: ldc_w 459
      //   1883: aconst_null
      //   1884: invokeinterface 102 3 0
      //   1889: astore 14
      //   1891: aload 14
      //   1893: ifnull +28 -> 1921
      //   1896: aload 20
      //   1898: astore 15
      //   1900: aload 19
      //   1902: astore_1
      //   1903: aload 17
      //   1905: ldc_w 459
      //   1908: new 26	org/json/JSONObject
      //   1911: dup
      //   1912: aload 14
      //   1914: invokespecial 461	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   1917: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1920: pop
      //   1921: aload 20
      //   1923: astore 15
      //   1925: aload 19
      //   1927: astore_1
      //   1928: new 32	org/json/JSONArray
      //   1931: dup
      //   1932: invokespecial 462	org/json/JSONArray:<init>	()V
      //   1935: astore 14
      //   1937: iload_2
      //   1938: ifeq +1114 -> 3052
      //   1941: aload 20
      //   1943: astore 15
      //   1945: aload 19
      //   1947: astore_1
      //   1948: aload_0
      //   1949: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   1952: invokestatic 41	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   1955: invokestatic 467	com/appodeal/ads/j:a	(Landroid/content/Context;)Ljava/util/List;
      //   1958: invokeinterface 473 1 0
      //   1963: astore 18
      //   1965: aload 20
      //   1967: astore 15
      //   1969: aload 19
      //   1971: astore_1
      //   1972: aload 18
      //   1974: invokeinterface 478 1 0
      //   1979: ifeq +1073 -> 3052
      //   1982: aload 20
      //   1984: astore 15
      //   1986: aload 19
      //   1988: astore_1
      //   1989: aload 18
      //   1991: invokeinterface 482 1 0
      //   1996: checkcast 484	com/appodeal/ads/k
      //   1999: astore 16
      //   2001: aload 20
      //   2003: astore 15
      //   2005: aload 19
      //   2007: astore_1
      //   2008: aload 16
      //   2010: invokevirtual 487	com/appodeal/ads/k:i	()Lcom/appodeal/ads/n;
      //   2013: ifnull -48 -> 1965
      //   2016: aload 20
      //   2018: astore 15
      //   2020: aload 19
      //   2022: astore_1
      //   2023: aload 14
      //   2025: aload 16
      //   2027: invokevirtual 489	com/appodeal/ads/k:a	()Ljava/lang/String;
      //   2030: invokevirtual 492	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   2033: pop
      //   2034: goto -69 -> 1965
      //   2037: astore 14
      //   2039: aload 20
      //   2041: astore 15
      //   2043: aload 19
      //   2045: astore_1
      //   2046: aload 14
      //   2048: invokestatic 495	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2051: aload 20
      //   2053: astore 15
      //   2055: aload 19
      //   2057: astore_1
      //   2058: invokestatic 498	java/util/Calendar:getInstance	()Ljava/util/Calendar;
      //   2061: astore 14
      //   2063: aload 20
      //   2065: astore 15
      //   2067: aload 19
      //   2069: astore_1
      //   2070: aload 14
      //   2072: aload 21
      //   2074: ldc_w 500
      //   2077: lconst_0
      //   2078: invokeinterface 504 4 0
      //   2083: invokevirtual 508	java/util/Calendar:setTimeInMillis	(J)V
      //   2086: aload 20
      //   2088: astore 15
      //   2090: aload 19
      //   2092: astore_1
      //   2093: aload 14
      //   2095: iconst_5
      //   2096: iconst_1
      //   2097: invokevirtual 512	java/util/Calendar:add	(II)V
      //   2100: iload_2
      //   2101: ifne +27 -> 2128
      //   2104: iload 5
      //   2106: ifne +22 -> 2128
      //   2109: iload 6
      //   2111: ifne +17 -> 2128
      //   2114: iload_3
      //   2115: ifne +13 -> 2128
      //   2118: iload 4
      //   2120: ifne +8 -> 2128
      //   2123: iload 7
      //   2125: ifeq +171 -> 2296
      //   2128: aload 20
      //   2130: astore 15
      //   2132: aload 19
      //   2134: astore_1
      //   2135: aload 14
      //   2137: invokevirtual 515	java/util/Calendar:getTimeInMillis	()J
      //   2140: lstore 10
      //   2142: aload 20
      //   2144: astore 15
      //   2146: aload 19
      //   2148: astore_1
      //   2149: invokestatic 518	java/lang/System:currentTimeMillis	()J
      //   2152: lstore 12
      //   2154: lload 10
      //   2156: lload 12
      //   2158: lcmp
      //   2159: ifge +137 -> 2296
      //   2162: aload 19
      //   2164: astore_1
      //   2165: new 32	org/json/JSONArray
      //   2168: dup
      //   2169: invokespecial 462	org/json/JSONArray:<init>	()V
      //   2172: astore 14
      //   2174: aload 19
      //   2176: astore_1
      //   2177: aload 22
      //   2179: iconst_0
      //   2180: invokevirtual 522	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
      //   2183: astore 16
      //   2185: aload 19
      //   2187: astore_1
      //   2188: ldc_w 524
      //   2191: invokestatic 530	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
      //   2194: astore 15
      //   2196: aload 19
      //   2198: astore_1
      //   2199: aload 16
      //   2201: invokeinterface 473 1 0
      //   2206: astore 16
      //   2208: aload 19
      //   2210: astore_1
      //   2211: aload 16
      //   2213: invokeinterface 478 1 0
      //   2218: ifeq +1509 -> 3727
      //   2221: aload 19
      //   2223: astore_1
      //   2224: aload 16
      //   2226: invokeinterface 482 1 0
      //   2231: checkcast 279	android/content/pm/ApplicationInfo
      //   2234: getfield 533	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   2237: astore 18
      //   2239: aload 19
      //   2241: astore_1
      //   2242: aload 15
      //   2244: aload 18
      //   2246: invokevirtual 537	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
      //   2249: invokevirtual 542	java/util/regex/Matcher:matches	()Z
      //   2252: ifne -44 -> 2208
      //   2255: aload 19
      //   2257: astore_1
      //   2258: aload 18
      //   2260: ldc -22
      //   2262: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2265: ifne -57 -> 2208
      //   2268: aload 19
      //   2270: astore_1
      //   2271: aload 14
      //   2273: aload 18
      //   2275: invokevirtual 492	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   2278: pop
      //   2279: goto -71 -> 2208
      //   2282: astore 14
      //   2284: aload 20
      //   2286: astore 15
      //   2288: aload 19
      //   2290: astore_1
      //   2291: aload 14
      //   2293: invokestatic 495	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2296: aload 20
      //   2298: astore 15
      //   2300: aload 19
      //   2302: astore_1
      //   2303: aload_0
      //   2304: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   2307: invokestatic 546	com/appodeal/ads/p:g	(Lcom/appodeal/ads/p;)Lcom/appodeal/ads/e/h;
      //   2310: ifnull +55 -> 2365
      //   2313: aload 20
      //   2315: astore 15
      //   2317: aload 19
      //   2319: astore_1
      //   2320: aload_0
      //   2321: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   2324: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   2327: ldc_w 548
      //   2330: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2333: ifeq +1532 -> 3865
      //   2336: aload 20
      //   2338: astore 15
      //   2340: aload 19
      //   2342: astore_1
      //   2343: aload 17
      //   2345: ldc_w 550
      //   2348: aload_0
      //   2349: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   2352: invokestatic 546	com/appodeal/ads/p:g	(Lcom/appodeal/ads/p;)Lcom/appodeal/ads/e/h;
      //   2355: invokevirtual 554	com/appodeal/ads/e/h:a	()Lorg/json/JSONObject;
      //   2358: invokevirtual 555	org/json/JSONObject:toString	()Ljava/lang/String;
      //   2361: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2364: pop
      //   2365: aload 20
      //   2367: astore 15
      //   2369: aload 19
      //   2371: astore_1
      //   2372: aload_0
      //   2373: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   2376: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   2379: ldc_w 548
      //   2382: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2385: ifne +1982 -> 4367
      //   2388: aload 20
      //   2390: astore 15
      //   2392: aload 19
      //   2394: astore_1
      //   2395: aload_0
      //   2396: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   2399: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   2402: ldc_w 557
      //   2405: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2408: ifne +1959 -> 4367
      //   2411: aload 20
      //   2413: astore 15
      //   2415: aload 19
      //   2417: astore_1
      //   2418: aload_0
      //   2419: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   2422: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   2425: ldc_w 559
      //   2428: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2431: ifne +1936 -> 4367
      //   2434: aload 20
      //   2436: astore 15
      //   2438: aload 19
      //   2440: astore_1
      //   2441: aload_0
      //   2442: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   2445: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   2448: ldc_w 561
      //   2451: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2454: ifne +1913 -> 4367
      //   2457: aload 20
      //   2459: astore 15
      //   2461: aload 19
      //   2463: astore_1
      //   2464: aload_0
      //   2465: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   2468: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   2471: ldc_w 563
      //   2474: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2477: ifne +1890 -> 4367
      //   2480: iconst_1
      //   2481: istore_2
      //   2482: iload_2
      //   2483: ifeq +1582 -> 4065
      //   2486: aload 20
      //   2488: astore 15
      //   2490: aload 19
      //   2492: astore_1
      //   2493: new 565	java/net/URL
      //   2496: dup
      //   2497: ldc_w 567
      //   2500: iconst_3
      //   2501: anewarray 113	java/lang/Object
      //   2504: dup
      //   2505: iconst_0
      //   2506: invokestatic 571	com/appodeal/ads/h/c:b	()Ljava/lang/String;
      //   2509: aastore
      //   2510: dup
      //   2511: iconst_1
      //   2512: invokestatic 573	com/appodeal/ads/h/c:c	()I
      //   2515: invokestatic 578	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   2518: aastore
      //   2519: dup
      //   2520: iconst_2
      //   2521: ldc_w 580
      //   2524: aastore
      //   2525: invokestatic 171	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   2528: invokespecial 581	java/net/URL:<init>	(Ljava/lang/String;)V
      //   2531: astore 14
      //   2533: aload 20
      //   2535: astore 15
      //   2537: aload 19
      //   2539: astore_1
      //   2540: aload 14
      //   2542: invokevirtual 585	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   2545: checkcast 587	java/net/HttpURLConnection
      //   2548: astore 14
      //   2550: aload 14
      //   2552: sipush 20000
      //   2555: invokevirtual 591	java/net/HttpURLConnection:setConnectTimeout	(I)V
      //   2558: aload 14
      //   2560: sipush 20000
      //   2563: invokevirtual 594	java/net/HttpURLConnection:setReadTimeout	(I)V
      //   2566: aload 14
      //   2568: ldc_w 596
      //   2571: ldc_w 598
      //   2574: invokevirtual 602	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   2577: aload 14
      //   2579: iconst_1
      //   2580: invokevirtual 606	java/net/HttpURLConnection:setDoOutput	(Z)V
      //   2583: new 608	java/io/ByteArrayOutputStream
      //   2586: dup
      //   2587: invokespecial 609	java/io/ByteArrayOutputStream:<init>	()V
      //   2590: astore 15
      //   2592: new 611	java/util/zip/GZIPOutputStream
      //   2595: dup
      //   2596: aload 15
      //   2598: invokespecial 614	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   2601: astore_1
      //   2602: aload_1
      //   2603: aload 17
      //   2605: invokevirtual 555	org/json/JSONObject:toString	()Ljava/lang/String;
      //   2608: ldc_w 616
      //   2611: invokevirtual 620	java/lang/String:getBytes	(Ljava/lang/String;)[B
      //   2614: invokevirtual 624	java/util/zip/GZIPOutputStream:write	([B)V
      //   2617: aload_1
      //   2618: invokevirtual 627	java/util/zip/GZIPOutputStream:close	()V
      //   2621: aload 15
      //   2623: invokevirtual 631	java/io/ByteArrayOutputStream:toByteArray	()[B
      //   2626: iconst_0
      //   2627: invokestatic 637	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
      //   2630: astore_1
      //   2631: aload 14
      //   2633: invokevirtual 641	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   2636: aload_1
      //   2637: invokestatic 644	com/appodeal/ads/aj:a	(Ljava/io/OutputStream;Ljava/lang/String;)V
      //   2640: aload 14
      //   2642: invokevirtual 648	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   2645: invokestatic 651	com/appodeal/ads/aj:a	(Ljava/io/InputStream;)Ljava/lang/String;
      //   2648: astore_1
      //   2649: aload_1
      //   2650: ifnull +24 -> 2674
      //   2653: aload_1
      //   2654: invokevirtual 654	java/lang/String:isEmpty	()Z
      //   2657: ifne +17 -> 2674
      //   2660: aload_1
      //   2661: ldc_w 656
      //   2664: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2667: istore 9
      //   2669: iload 9
      //   2671: ifeq +5 -> 2676
      //   2674: aconst_null
      //   2675: astore_1
      //   2676: aload_1
      //   2677: astore 16
      //   2679: aload 14
      //   2681: astore 15
      //   2683: aload 14
      //   2685: astore_1
      //   2686: aload_0
      //   2687: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   2690: invokestatic 41	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   2693: ldc_w 658
      //   2696: iconst_0
      //   2697: invokevirtual 90	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   2700: astore 17
      //   2702: aload 16
      //   2704: ifnonnull +1461 -> 4165
      //   2707: iload_2
      //   2708: ifeq +1443 -> 4151
      //   2711: aload 14
      //   2713: astore 15
      //   2715: aload 14
      //   2717: astore_1
      //   2718: aload 17
      //   2720: aload_0
      //   2721: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   2724: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   2727: invokeinterface 662 2 0
      //   2732: ifeq +1419 -> 4151
      //   2735: aload 14
      //   2737: astore 15
      //   2739: aload 14
      //   2741: astore_1
      //   2742: ldc_w 664
      //   2745: invokestatic 176	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   2748: aload 14
      //   2750: astore 15
      //   2752: aload 14
      //   2754: astore_1
      //   2755: aload 17
      //   2757: aload_0
      //   2758: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   2761: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   2764: ldc_w 666
      //   2767: invokeinterface 102 3 0
      //   2772: astore 16
      //   2774: aload 14
      //   2776: astore 15
      //   2778: aload 14
      //   2780: astore_1
      //   2781: new 26	org/json/JSONObject
      //   2784: dup
      //   2785: aload 16
      //   2787: invokespecial 461	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   2790: astore 16
      //   2792: aload 14
      //   2794: astore 15
      //   2796: aload 14
      //   2798: astore_1
      //   2799: aload 16
      //   2801: invokestatic 667	com/appodeal/ads/Appodeal:a	(Lorg/json/JSONObject;)V
      //   2804: aload 16
      //   2806: ifnull +35 -> 2841
      //   2809: aload 14
      //   2811: astore_1
      //   2812: aload 16
      //   2814: ldc_w 669
      //   2817: invokevirtual 673	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   2820: putstatic 679	com/appodeal/ads/UserSettings:userData	Lorg/json/JSONObject;
      //   2823: aload 14
      //   2825: astore_1
      //   2826: getstatic 71	com/appodeal/ads/g/e:a	Ljava/lang/Long;
      //   2829: ifnonnull +12 -> 2841
      //   2832: aload 14
      //   2834: astore_1
      //   2835: aload_0
      //   2836: aload 16
      //   2838: invokespecial 681	com/appodeal/ads/p$b:b	(Lorg/json/JSONObject;)V
      //   2841: aload 16
      //   2843: astore_1
      //   2844: aload 14
      //   2846: ifnull -2761 -> 85
      //   2849: aload 14
      //   2851: invokevirtual 684	java/net/HttpURLConnection:disconnect	()V
      //   2854: aload 16
      //   2856: areturn
      //   2857: ldc_w 686
      //   2860: astore 14
      //   2862: goto -2601 -> 261
      //   2865: astore 14
      //   2867: aload 20
      //   2869: astore 15
      //   2871: aload 19
      //   2873: astore_1
      //   2874: aload 14
      //   2876: invokestatic 495	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2879: aload 16
      //   2881: astore 14
      //   2883: aload 17
      //   2885: astore_1
      //   2886: goto -2510 -> 376
      //   2889: iconst_0
      //   2890: istore_2
      //   2891: goto -2429 -> 462
      //   2894: iconst_0
      //   2895: istore_3
      //   2896: goto -2387 -> 509
      //   2899: iconst_0
      //   2900: istore 4
      //   2902: goto -2346 -> 556
      //   2905: iconst_0
      //   2906: istore 5
      //   2908: goto -2305 -> 603
      //   2911: iconst_0
      //   2912: istore 6
      //   2914: goto -2264 -> 650
      //   2917: iconst_0
      //   2918: istore 7
      //   2920: goto -2223 -> 697
      //   2923: iconst_0
      //   2924: istore 8
      //   2926: goto -2090 -> 836
      //   2929: astore 23
      //   2931: aload 20
      //   2933: astore 15
      //   2935: aload 19
      //   2937: astore_1
      //   2938: aload 23
      //   2940: invokestatic 495	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2943: goto -1796 -> 1147
      //   2946: astore_1
      //   2947: aload 15
      //   2949: astore 14
      //   2951: aload_1
      //   2952: astore 15
      //   2954: aload 14
      //   2956: astore_1
      //   2957: aload 15
      //   2959: invokestatic 495	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2962: aconst_null
      //   2963: astore_1
      //   2964: aload 14
      //   2966: ifnull -2881 -> 85
      //   2969: aload 14
      //   2971: invokevirtual 684	java/net/HttpURLConnection:disconnect	()V
      //   2974: aconst_null
      //   2975: areturn
      //   2976: astore 23
      //   2978: aload 20
      //   2980: astore 15
      //   2982: aload 19
      //   2984: astore_1
      //   2985: aload 23
      //   2987: invokestatic 495	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2990: goto -1812 -> 1178
      //   2993: astore 15
      //   2995: aload_1
      //   2996: astore 14
      //   2998: aload 15
      //   3000: astore_1
      //   3001: aload 14
      //   3003: ifnull +8 -> 3011
      //   3006: aload 14
      //   3008: invokevirtual 684	java/net/HttpURLConnection:disconnect	()V
      //   3011: aload_1
      //   3012: athrow
      //   3013: aload 20
      //   3015: astore 15
      //   3017: aload 19
      //   3019: astore_1
      //   3020: aload 17
      //   3022: ldc_w 335
      //   3025: ldc_w 688
      //   3028: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3031: pop
      //   3032: goto -1651 -> 1381
      //   3035: astore 14
      //   3037: aload 20
      //   3039: astore 15
      //   3041: aload 19
      //   3043: astore_1
      //   3044: aload 14
      //   3046: invokestatic 495	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   3049: goto -1586 -> 1463
      //   3052: iload 5
      //   3054: ifeq +99 -> 3153
      //   3057: aload 20
      //   3059: astore 15
      //   3061: aload 19
      //   3063: astore_1
      //   3064: aload_0
      //   3065: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3068: invokestatic 41	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   3071: invokestatic 691	com/appodeal/ads/ad:a	(Landroid/content/Context;)Ljava/util/List;
      //   3074: invokeinterface 473 1 0
      //   3079: astore 18
      //   3081: aload 20
      //   3083: astore 15
      //   3085: aload 19
      //   3087: astore_1
      //   3088: aload 18
      //   3090: invokeinterface 478 1 0
      //   3095: ifeq +58 -> 3153
      //   3098: aload 20
      //   3100: astore 15
      //   3102: aload 19
      //   3104: astore_1
      //   3105: aload 18
      //   3107: invokeinterface 482 1 0
      //   3112: checkcast 693	com/appodeal/ads/al
      //   3115: astore 16
      //   3117: aload 20
      //   3119: astore 15
      //   3121: aload 19
      //   3123: astore_1
      //   3124: aload 16
      //   3126: invokevirtual 696	com/appodeal/ads/al:i	()Lcom/appodeal/ads/am;
      //   3129: ifnull -48 -> 3081
      //   3132: aload 20
      //   3134: astore 15
      //   3136: aload 19
      //   3138: astore_1
      //   3139: aload 14
      //   3141: aload 16
      //   3143: invokevirtual 697	com/appodeal/ads/al:a	()Ljava/lang/String;
      //   3146: invokevirtual 492	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   3149: pop
      //   3150: goto -69 -> 3081
      //   3153: iload 6
      //   3155: ifeq +99 -> 3254
      //   3158: aload 20
      //   3160: astore 15
      //   3162: aload 19
      //   3164: astore_1
      //   3165: aload_0
      //   3166: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3169: invokestatic 41	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   3172: invokestatic 700	com/appodeal/ads/ag:a	(Landroid/content/Context;)Ljava/util/List;
      //   3175: invokeinterface 473 1 0
      //   3180: astore 16
      //   3182: aload 20
      //   3184: astore 15
      //   3186: aload 19
      //   3188: astore_1
      //   3189: aload 16
      //   3191: invokeinterface 478 1 0
      //   3196: ifeq +58 -> 3254
      //   3199: aload 20
      //   3201: astore 15
      //   3203: aload 19
      //   3205: astore_1
      //   3206: aload 16
      //   3208: invokeinterface 482 1 0
      //   3213: checkcast 693	com/appodeal/ads/al
      //   3216: astore 18
      //   3218: aload 20
      //   3220: astore 15
      //   3222: aload 19
      //   3224: astore_1
      //   3225: aload 18
      //   3227: invokevirtual 696	com/appodeal/ads/al:i	()Lcom/appodeal/ads/am;
      //   3230: ifnull -48 -> 3182
      //   3233: aload 20
      //   3235: astore 15
      //   3237: aload 19
      //   3239: astore_1
      //   3240: aload 14
      //   3242: aload 18
      //   3244: invokevirtual 697	com/appodeal/ads/al:a	()Ljava/lang/String;
      //   3247: invokevirtual 492	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   3250: pop
      //   3251: goto -69 -> 3182
      //   3254: iload_3
      //   3255: ifeq +99 -> 3354
      //   3258: aload 20
      //   3260: astore 15
      //   3262: aload 19
      //   3264: astore_1
      //   3265: aload_0
      //   3266: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3269: invokestatic 41	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   3272: invokestatic 703	com/appodeal/ads/b:a	(Landroid/content/Context;)Ljava/util/List;
      //   3275: invokeinterface 473 1 0
      //   3280: astore 18
      //   3282: aload 20
      //   3284: astore 15
      //   3286: aload 19
      //   3288: astore_1
      //   3289: aload 18
      //   3291: invokeinterface 478 1 0
      //   3296: ifeq +58 -> 3354
      //   3299: aload 20
      //   3301: astore 15
      //   3303: aload 19
      //   3305: astore_1
      //   3306: aload 18
      //   3308: invokeinterface 482 1 0
      //   3313: checkcast 705	com/appodeal/ads/c
      //   3316: astore 16
      //   3318: aload 20
      //   3320: astore 15
      //   3322: aload 19
      //   3324: astore_1
      //   3325: aload 16
      //   3327: invokevirtual 708	com/appodeal/ads/c:g	()Lcom/appodeal/ads/f;
      //   3330: ifnull -48 -> 3282
      //   3333: aload 20
      //   3335: astore 15
      //   3337: aload 19
      //   3339: astore_1
      //   3340: aload 14
      //   3342: aload 16
      //   3344: invokevirtual 709	com/appodeal/ads/c:a	()Ljava/lang/String;
      //   3347: invokevirtual 492	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   3350: pop
      //   3351: goto -69 -> 3282
      //   3354: iload 4
      //   3356: ifeq +99 -> 3455
      //   3359: aload 20
      //   3361: astore 15
      //   3363: aload 19
      //   3365: astore_1
      //   3366: aload_0
      //   3367: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3370: invokestatic 41	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   3373: invokestatic 712	com/appodeal/ads/q:a	(Landroid/content/Context;)Ljava/util/List;
      //   3376: invokeinterface 473 1 0
      //   3381: astore 16
      //   3383: aload 20
      //   3385: astore 15
      //   3387: aload 19
      //   3389: astore_1
      //   3390: aload 16
      //   3392: invokeinterface 478 1 0
      //   3397: ifeq +58 -> 3455
      //   3400: aload 20
      //   3402: astore 15
      //   3404: aload 19
      //   3406: astore_1
      //   3407: aload 16
      //   3409: invokeinterface 482 1 0
      //   3414: checkcast 714	com/appodeal/ads/r
      //   3417: astore 18
      //   3419: aload 20
      //   3421: astore 15
      //   3423: aload 19
      //   3425: astore_1
      //   3426: aload 18
      //   3428: invokevirtual 717	com/appodeal/ads/r:g	()Lcom/appodeal/ads/u;
      //   3431: ifnull -48 -> 3383
      //   3434: aload 20
      //   3436: astore 15
      //   3438: aload 19
      //   3440: astore_1
      //   3441: aload 14
      //   3443: aload 18
      //   3445: invokevirtual 718	com/appodeal/ads/r:a	()Ljava/lang/String;
      //   3448: invokevirtual 492	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   3451: pop
      //   3452: goto -69 -> 3383
      //   3455: iload 7
      //   3457: ifeq +89 -> 3546
      //   3460: aload 20
      //   3462: astore 15
      //   3464: aload 19
      //   3466: astore_1
      //   3467: aload_0
      //   3468: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3471: invokestatic 41	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   3474: invokestatic 721	com/appodeal/ads/w:a	(Landroid/content/Context;)Ljava/util/List;
      //   3477: invokeinterface 473 1 0
      //   3482: astore 16
      //   3484: aload 20
      //   3486: astore 15
      //   3488: aload 19
      //   3490: astore_1
      //   3491: aload 16
      //   3493: invokeinterface 478 1 0
      //   3498: ifeq +48 -> 3546
      //   3501: aload 20
      //   3503: astore 15
      //   3505: aload 19
      //   3507: astore_1
      //   3508: aload 16
      //   3510: invokeinterface 482 1 0
      //   3515: checkcast 723	com/appodeal/ads/y
      //   3518: astore 18
      //   3520: aload 18
      //   3522: ifnull -38 -> 3484
      //   3525: aload 20
      //   3527: astore 15
      //   3529: aload 19
      //   3531: astore_1
      //   3532: aload 14
      //   3534: aload 18
      //   3536: invokevirtual 724	com/appodeal/ads/y:a	()Ljava/lang/String;
      //   3539: invokevirtual 492	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   3542: pop
      //   3543: goto -59 -> 3484
      //   3546: aload 20
      //   3548: astore 15
      //   3550: aload 19
      //   3552: astore_1
      //   3553: aload 17
      //   3555: ldc_w 726
      //   3558: aload 14
      //   3560: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3563: pop
      //   3564: aload 20
      //   3566: astore 15
      //   3568: aload 19
      //   3570: astore_1
      //   3571: aload_0
      //   3572: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3575: invokestatic 728	com/appodeal/ads/p:c	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   3578: ifnull +26 -> 3604
      //   3581: aload 20
      //   3583: astore 15
      //   3585: aload 19
      //   3587: astore_1
      //   3588: aload 17
      //   3590: ldc_w 730
      //   3593: aload_0
      //   3594: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3597: invokestatic 728	com/appodeal/ads/p:c	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   3600: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3603: pop
      //   3604: aload 20
      //   3606: astore 15
      //   3608: aload 19
      //   3610: astore_1
      //   3611: getstatic 71	com/appodeal/ads/g/e:a	Ljava/lang/Long;
      //   3614: ifnull +22 -> 3636
      //   3617: aload 20
      //   3619: astore 15
      //   3621: aload 19
      //   3623: astore_1
      //   3624: aload 17
      //   3626: ldc_w 732
      //   3629: getstatic 71	com/appodeal/ads/g/e:a	Ljava/lang/Long;
      //   3632: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3635: pop
      //   3636: aload 20
      //   3638: astore 15
      //   3640: aload 19
      //   3642: astore_1
      //   3643: aload_0
      //   3644: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3647: invokestatic 735	com/appodeal/ads/p:d	(Lcom/appodeal/ads/p;)J
      //   3650: lconst_0
      //   3651: lcmp
      //   3652: ifeq +26 -> 3678
      //   3655: aload 20
      //   3657: astore 15
      //   3659: aload 19
      //   3661: astore_1
      //   3662: aload 17
      //   3664: ldc_w 737
      //   3667: aload_0
      //   3668: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3671: invokestatic 735	com/appodeal/ads/p:d	(Lcom/appodeal/ads/p;)J
      //   3674: invokevirtual 449	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   3677: pop
      //   3678: aload 20
      //   3680: astore 15
      //   3682: aload 19
      //   3684: astore_1
      //   3685: aload 17
      //   3687: ldc_w 550
      //   3690: aload_0
      //   3691: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3694: invokestatic 740	com/appodeal/ads/p:e	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   3697: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3700: pop
      //   3701: aload 20
      //   3703: astore 15
      //   3705: aload 19
      //   3707: astore_1
      //   3708: aload 17
      //   3710: ldc_w 742
      //   3713: aload_0
      //   3714: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3717: invokestatic 744	com/appodeal/ads/p:f	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   3720: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3723: pop
      //   3724: goto -1673 -> 2051
      //   3727: aload 19
      //   3729: astore_1
      //   3730: aload 17
      //   3732: ldc_w 746
      //   3735: aload 14
      //   3737: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3740: pop
      //   3741: aload 19
      //   3743: astore_1
      //   3744: aload 17
      //   3746: ldc_w 748
      //   3749: aload_0
      //   3750: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3753: invokestatic 41	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   3756: invokestatic 753	com/appodeal/ads/h/i:a	(Landroid/content/Context;)Lorg/json/JSONArray;
      //   3759: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3762: pop
      //   3763: aload 19
      //   3765: astore_1
      //   3766: aload 17
      //   3768: ldc_w 755
      //   3771: aload_0
      //   3772: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3775: invokestatic 41	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   3778: invokestatic 759	com/appodeal/ads/h/j:a	(Landroid/content/Context;)Ljava/lang/String;
      //   3781: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3784: pop
      //   3785: aload 19
      //   3787: astore_1
      //   3788: aload 17
      //   3790: ldc_w 761
      //   3793: aload_0
      //   3794: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3797: invokestatic 41	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   3800: invokestatic 765	com/appodeal/ads/Appodeal:getUserSettings	(Landroid/content/Context;)Lcom/appodeal/ads/UserSettings;
      //   3803: invokevirtual 766	com/appodeal/ads/UserSettings:a	()Lorg/json/JSONObject;
      //   3806: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3809: pop
      //   3810: aload 19
      //   3812: astore_1
      //   3813: aload 21
      //   3815: invokeinterface 154 1 0
      //   3820: astore 14
      //   3822: aload 19
      //   3824: astore_1
      //   3825: aload 14
      //   3827: ldc_w 500
      //   3830: invokestatic 518	java/lang/System:currentTimeMillis	()J
      //   3833: invokeinterface 770 4 0
      //   3838: pop
      //   3839: aload 19
      //   3841: astore_1
      //   3842: aload 14
      //   3844: invokeinterface 163 1 0
      //   3849: goto -1553 -> 2296
      //   3852: astore 14
      //   3854: aload 19
      //   3856: astore_1
      //   3857: aload 14
      //   3859: invokestatic 495	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   3862: goto -52 -> 3810
      //   3865: aload 20
      //   3867: astore 15
      //   3869: aload 19
      //   3871: astore_1
      //   3872: aload_0
      //   3873: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3876: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   3879: ldc_w 557
      //   3882: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3885: ifeq +105 -> 3990
      //   3888: aload 20
      //   3890: astore 15
      //   3892: aload 19
      //   3894: astore_1
      //   3895: aload_0
      //   3896: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3899: invokestatic 546	com/appodeal/ads/p:g	(Lcom/appodeal/ads/p;)Lcom/appodeal/ads/e/h;
      //   3902: invokevirtual 772	com/appodeal/ads/e/h:f	()Z
      //   3905: ifne +30 -> 3935
      //   3908: aload 20
      //   3910: astore 15
      //   3912: aload 19
      //   3914: astore_1
      //   3915: ldc_w 774
      //   3918: invokestatic 176	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   3921: aconst_null
      //   3922: astore_1
      //   3923: iconst_0
      //   3924: ifeq -3839 -> 85
      //   3927: new 104	java/lang/NullPointerException
      //   3930: dup
      //   3931: invokespecial 105	java/lang/NullPointerException:<init>	()V
      //   3934: athrow
      //   3935: aload 20
      //   3937: astore 15
      //   3939: aload 19
      //   3941: astore_1
      //   3942: aload 17
      //   3944: ldc_w 776
      //   3947: aload_0
      //   3948: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3951: invokestatic 546	com/appodeal/ads/p:g	(Lcom/appodeal/ads/p;)Lcom/appodeal/ads/e/h;
      //   3954: invokevirtual 778	com/appodeal/ads/e/h:e	()Lorg/json/JSONObject;
      //   3957: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3960: pop
      //   3961: aload 20
      //   3963: astore 15
      //   3965: aload 19
      //   3967: astore_1
      //   3968: aload 17
      //   3970: ldc_w 780
      //   3973: aload_0
      //   3974: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3977: invokestatic 546	com/appodeal/ads/p:g	(Lcom/appodeal/ads/p;)Lcom/appodeal/ads/e/h;
      //   3980: invokevirtual 782	com/appodeal/ads/e/h:g	()Ljava/lang/String;
      //   3983: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3986: pop
      //   3987: goto -1622 -> 2365
      //   3990: aload 20
      //   3992: astore 15
      //   3994: aload 19
      //   3996: astore_1
      //   3997: aload_0
      //   3998: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   4001: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   4004: ldc_w 559
      //   4007: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   4010: ifne +26 -> 4036
      //   4013: aload 20
      //   4015: astore 15
      //   4017: aload 19
      //   4019: astore_1
      //   4020: aload_0
      //   4021: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   4024: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   4027: ldc_w 561
      //   4030: invokevirtual 192	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   4033: ifeq -1668 -> 2365
      //   4036: aload 20
      //   4038: astore 15
      //   4040: aload 19
      //   4042: astore_1
      //   4043: aload 17
      //   4045: ldc_w 550
      //   4048: aload_0
      //   4049: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   4052: invokestatic 546	com/appodeal/ads/p:g	(Lcom/appodeal/ads/p;)Lcom/appodeal/ads/e/h;
      //   4055: invokevirtual 782	com/appodeal/ads/e/h:g	()Ljava/lang/String;
      //   4058: invokevirtual 220	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   4061: pop
      //   4062: goto -1697 -> 2365
      //   4065: aload 20
      //   4067: astore 15
      //   4069: aload 19
      //   4071: astore_1
      //   4072: new 565	java/net/URL
      //   4075: dup
      //   4076: ldc_w 567
      //   4079: iconst_3
      //   4080: anewarray 113	java/lang/Object
      //   4083: dup
      //   4084: iconst_0
      //   4085: invokestatic 571	com/appodeal/ads/h/c:b	()Ljava/lang/String;
      //   4088: aastore
      //   4089: dup
      //   4090: iconst_1
      //   4091: invokestatic 573	com/appodeal/ads/h/c:c	()I
      //   4094: invokestatic 578	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   4097: aastore
      //   4098: dup
      //   4099: iconst_2
      //   4100: aload_0
      //   4101: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   4104: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   4107: aastore
      //   4108: invokestatic 171	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   4111: invokespecial 581	java/net/URL:<init>	(Ljava/lang/String;)V
      //   4114: astore 14
      //   4116: goto -1583 -> 2533
      //   4119: astore 15
      //   4121: aload_1
      //   4122: invokevirtual 627	java/util/zip/GZIPOutputStream:close	()V
      //   4125: aload 15
      //   4127: athrow
      //   4128: astore 15
      //   4130: aload 14
      //   4132: astore_1
      //   4133: aload 15
      //   4135: astore 14
      //   4137: aload 14
      //   4139: invokestatic 495	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   4142: aconst_null
      //   4143: astore 16
      //   4145: aload_1
      //   4146: astore 14
      //   4148: goto -1469 -> 2679
      //   4151: aconst_null
      //   4152: astore_1
      //   4153: aload 14
      //   4155: ifnull -4070 -> 85
      //   4158: aload 14
      //   4160: invokevirtual 684	java/net/HttpURLConnection:disconnect	()V
      //   4163: aconst_null
      //   4164: areturn
      //   4165: iload_2
      //   4166: ifeq +206 -> 4372
      //   4169: aload 14
      //   4171: astore 15
      //   4173: aload 14
      //   4175: astore_1
      //   4176: aload 17
      //   4178: invokeinterface 154 1 0
      //   4183: astore 17
      //   4185: aload 14
      //   4187: astore 15
      //   4189: aload 14
      //   4191: astore_1
      //   4192: aload 17
      //   4194: aload_0
      //   4195: getfield 14	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   4198: invokestatic 186	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   4201: aload 16
      //   4203: invokeinterface 160 3 0
      //   4208: pop
      //   4209: aload 14
      //   4211: astore 15
      //   4213: aload 14
      //   4215: astore_1
      //   4216: aload 17
      //   4218: invokeinterface 163 1 0
      //   4223: goto +149 -> 4372
      //   4226: astore 16
      //   4228: aload 14
      //   4230: astore 15
      //   4232: aload 14
      //   4234: astore_1
      //   4235: aload 16
      //   4237: invokestatic 495	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   4240: aconst_null
      //   4241: astore_1
      //   4242: aload 14
      //   4244: ifnull -4159 -> 85
      //   4247: aload 14
      //   4249: invokevirtual 684	java/net/HttpURLConnection:disconnect	()V
      //   4252: aconst_null
      //   4253: areturn
      //   4254: astore 17
      //   4256: aload 14
      //   4258: astore 15
      //   4260: aload 14
      //   4262: astore_1
      //   4263: aload 17
      //   4265: invokestatic 495	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   4268: goto -1427 -> 2841
      //   4271: astore_1
      //   4272: goto -1271 -> 3001
      //   4275: astore 15
      //   4277: aload_1
      //   4278: astore 14
      //   4280: aload 15
      //   4282: astore_1
      //   4283: goto -1282 -> 3001
      //   4286: astore 15
      //   4288: goto -1334 -> 2954
      //   4291: astore 15
      //   4293: aload_1
      //   4294: astore 14
      //   4296: goto -1342 -> 2954
      //   4299: astore 14
      //   4301: aconst_null
      //   4302: astore_1
      //   4303: goto -166 -> 4137
      //   4306: aload_1
      //   4307: astore 16
      //   4309: goto -3910 -> 399
      //   4312: iconst_1
      //   4313: istore_2
      //   4314: goto -3852 -> 462
      //   4317: iconst_1
      //   4318: istore_3
      //   4319: goto -3810 -> 509
      //   4322: iconst_1
      //   4323: istore 4
      //   4325: goto -3769 -> 556
      //   4328: iconst_1
      //   4329: istore 5
      //   4331: goto -3728 -> 603
      //   4334: iconst_1
      //   4335: istore 6
      //   4337: goto -3687 -> 650
      //   4340: iconst_1
      //   4341: istore 7
      //   4343: goto -3646 -> 697
      //   4346: iload 5
      //   4348: ifne -3428 -> 920
      //   4351: iload 6
      //   4353: ifeq -3416 -> 937
      //   4356: goto -3436 -> 920
      //   4359: ldc_w 784
      //   4362: astore 14
      //   4364: goto -2959 -> 1405
      //   4367: iconst_0
      //   4368: istore_2
      //   4369: goto -1887 -> 2482
      //   4372: goto -1598 -> 2774
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	4375	0	this	b
      //   0	4375	1	paramVarArgs	Void[]
      //   853	3516	2	i	int
      //   874	3445	3	j	int
      //   895	3429	4	k	int
      //   1467	2880	5	m	int
      //   937	3415	6	n	int
      //   958	3384	7	i1	int
      //   834	2091	8	i2	int
      //   826	1844	9	bool	boolean
      //   2140	15	10	l1	long
      //   2152	5	12	l2	long
      //   123	1901	14	localObject1	Object
      //   2037	10	14	localJSONException1	org.json.JSONException
      //   2061	211	14	localObject2	Object
      //   2282	10	14	localException1	Exception
      //   2531	330	14	localObject3	Object
      //   2865	10	14	localException2	Exception
      //   2881	126	14	localObject4	Object
      //   3035	701	14	localException3	Exception
      //   3820	23	14	localEditor	android.content.SharedPreferences.Editor
      //   3852	6	14	localException4	Exception
      //   4114	181	14	localObject5	Object
      //   4299	1	14	localIOException1	java.io.IOException
      //   4362	1	14	str1	String
      //   8	2973	15	localObject6	Object
      //   2993	6	15	localObject7	Object
      //   3015	1053	15	localObject8	Object
      //   4119	7	15	localObject9	Object
      //   4128	6	15	localIOException2	java.io.IOException
      //   4171	88	15	localObject10	Object
      //   4275	6	15	localObject11	Object
      //   4286	1	15	localException5	Exception
      //   4291	1	15	localException6	Exception
      //   127	4075	16	localObject12	Object
      //   4226	10	16	localJSONException2	org.json.JSONException
      //   4307	1	16	arrayOfVoid	Void[]
      //   131	4086	17	localObject13	Object
      //   4254	10	17	localException7	Exception
      //   104	3431	18	localObject14	Object
      //   1	4069	19	localObject15	Object
      //   4	4062	20	localObject16	Object
      //   26	3788	21	localSharedPreferences	android.content.SharedPreferences
      //   45	2133	22	localPackageManager	android.content.pm.PackageManager
      //   64	784	23	str2	String
      //   2929	10	23	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
      //   2976	10	23	localException8	Exception
      //   218	32	24	localInfo	com.google.android.gms.ads.identifier.AdvertisingIdClient.Info
      // Exception table:
      //   from	to	target	type
      //   843	853	2037	org/json/JSONException
      //   864	874	2037	org/json/JSONException
      //   885	895	2037	org/json/JSONException
      //   907	917	2037	org/json/JSONException
      //   927	937	2037	org/json/JSONException
      //   949	958	2037	org/json/JSONException
      //   970	980	2037	org/json/JSONException
      //   992	1001	2037	org/json/JSONException
      //   1008	1014	2037	org/json/JSONException
      //   1021	1030	2037	org/json/JSONException
      //   1037	1048	2037	org/json/JSONException
      //   1055	1066	2037	org/json/JSONException
      //   1073	1083	2037	org/json/JSONException
      //   1090	1102	2037	org/json/JSONException
      //   1109	1120	2037	org/json/JSONException
      //   1127	1147	2037	org/json/JSONException
      //   1150	1178	2037	org/json/JSONException
      //   1185	1196	2037	org/json/JSONException
      //   1203	1214	2037	org/json/JSONException
      //   1221	1233	2037	org/json/JSONException
      //   1240	1254	2037	org/json/JSONException
      //   1261	1281	2037	org/json/JSONException
      //   1288	1300	2037	org/json/JSONException
      //   1307	1321	2037	org/json/JSONException
      //   1328	1342	2037	org/json/JSONException
      //   1349	1362	2037	org/json/JSONException
      //   1369	1381	2037	org/json/JSONException
      //   1388	1400	2037	org/json/JSONException
      //   1412	1423	2037	org/json/JSONException
      //   1426	1435	2037	org/json/JSONException
      //   1452	1463	2037	org/json/JSONException
      //   1498	1529	2037	org/json/JSONException
      //   1536	1548	2037	org/json/JSONException
      //   1555	1569	2037	org/json/JSONException
      //   1576	1596	2037	org/json/JSONException
      //   1603	1623	2037	org/json/JSONException
      //   1630	1644	2037	org/json/JSONException
      //   1651	1665	2037	org/json/JSONException
      //   1672	1691	2037	org/json/JSONException
      //   1698	1713	2037	org/json/JSONException
      //   1720	1734	2037	org/json/JSONException
      //   1741	1771	2037	org/json/JSONException
      //   1778	1793	2037	org/json/JSONException
      //   1800	1810	2037	org/json/JSONException
      //   1817	1831	2037	org/json/JSONException
      //   1838	1850	2037	org/json/JSONException
      //   1857	1871	2037	org/json/JSONException
      //   1878	1891	2037	org/json/JSONException
      //   1903	1921	2037	org/json/JSONException
      //   1928	1937	2037	org/json/JSONException
      //   1948	1965	2037	org/json/JSONException
      //   1972	1982	2037	org/json/JSONException
      //   1989	2001	2037	org/json/JSONException
      //   2008	2016	2037	org/json/JSONException
      //   2023	2034	2037	org/json/JSONException
      //   2938	2943	2037	org/json/JSONException
      //   2985	2990	2037	org/json/JSONException
      //   3020	3032	2037	org/json/JSONException
      //   3044	3049	2037	org/json/JSONException
      //   3064	3081	2037	org/json/JSONException
      //   3088	3098	2037	org/json/JSONException
      //   3105	3117	2037	org/json/JSONException
      //   3124	3132	2037	org/json/JSONException
      //   3139	3150	2037	org/json/JSONException
      //   3165	3182	2037	org/json/JSONException
      //   3189	3199	2037	org/json/JSONException
      //   3206	3218	2037	org/json/JSONException
      //   3225	3233	2037	org/json/JSONException
      //   3240	3251	2037	org/json/JSONException
      //   3265	3282	2037	org/json/JSONException
      //   3289	3299	2037	org/json/JSONException
      //   3306	3318	2037	org/json/JSONException
      //   3325	3333	2037	org/json/JSONException
      //   3340	3351	2037	org/json/JSONException
      //   3366	3383	2037	org/json/JSONException
      //   3390	3400	2037	org/json/JSONException
      //   3407	3419	2037	org/json/JSONException
      //   3426	3434	2037	org/json/JSONException
      //   3441	3452	2037	org/json/JSONException
      //   3467	3484	2037	org/json/JSONException
      //   3491	3501	2037	org/json/JSONException
      //   3508	3520	2037	org/json/JSONException
      //   3532	3543	2037	org/json/JSONException
      //   3553	3564	2037	org/json/JSONException
      //   3571	3581	2037	org/json/JSONException
      //   3588	3604	2037	org/json/JSONException
      //   3611	3617	2037	org/json/JSONException
      //   3624	3636	2037	org/json/JSONException
      //   3643	3655	2037	org/json/JSONException
      //   3662	3678	2037	org/json/JSONException
      //   3685	3701	2037	org/json/JSONException
      //   3708	3724	2037	org/json/JSONException
      //   2165	2174	2282	java/lang/Exception
      //   2177	2185	2282	java/lang/Exception
      //   2188	2196	2282	java/lang/Exception
      //   2199	2208	2282	java/lang/Exception
      //   2211	2221	2282	java/lang/Exception
      //   2224	2239	2282	java/lang/Exception
      //   2242	2255	2282	java/lang/Exception
      //   2258	2268	2282	java/lang/Exception
      //   2271	2279	2282	java/lang/Exception
      //   3730	3741	2282	java/lang/Exception
      //   3813	3822	2282	java/lang/Exception
      //   3825	3839	2282	java/lang/Exception
      //   3842	3849	2282	java/lang/Exception
      //   3857	3862	2282	java/lang/Exception
      //   149	169	2865	java/lang/Exception
      //   180	197	2865	java/lang/Exception
      //   208	220	2865	java/lang/Exception
      //   231	238	2865	java/lang/Exception
      //   249	257	2865	java/lang/Exception
      //   272	281	2865	java/lang/Exception
      //   292	304	2865	java/lang/Exception
      //   315	327	2865	java/lang/Exception
      //   338	345	2865	java/lang/Exception
      //   356	373	2865	java/lang/Exception
      //   1127	1147	2929	android/content/pm/PackageManager$NameNotFoundException
      //   13	28	2946	java/lang/Exception
      //   35	47	2946	java/lang/Exception
      //   54	66	2946	java/lang/Exception
      //   94	106	2946	java/lang/Exception
      //   113	125	2946	java/lang/Exception
      //   387	399	2946	java/lang/Exception
      //   406	415	2946	java/lang/Exception
      //   422	437	2946	java/lang/Exception
      //   444	459	2946	java/lang/Exception
      //   469	484	2946	java/lang/Exception
      //   491	506	2946	java/lang/Exception
      //   516	531	2946	java/lang/Exception
      //   538	553	2946	java/lang/Exception
      //   563	578	2946	java/lang/Exception
      //   585	600	2946	java/lang/Exception
      //   610	625	2946	java/lang/Exception
      //   632	647	2946	java/lang/Exception
      //   657	672	2946	java/lang/Exception
      //   679	694	2946	java/lang/Exception
      //   704	719	2946	java/lang/Exception
      //   726	741	2946	java/lang/Exception
      //   748	763	2946	java/lang/Exception
      //   770	785	2946	java/lang/Exception
      //   792	807	2946	java/lang/Exception
      //   814	828	2946	java/lang/Exception
      //   843	853	2946	java/lang/Exception
      //   864	874	2946	java/lang/Exception
      //   885	895	2946	java/lang/Exception
      //   907	917	2946	java/lang/Exception
      //   927	937	2946	java/lang/Exception
      //   949	958	2946	java/lang/Exception
      //   970	980	2946	java/lang/Exception
      //   992	1001	2946	java/lang/Exception
      //   1008	1014	2946	java/lang/Exception
      //   1021	1030	2946	java/lang/Exception
      //   1037	1048	2946	java/lang/Exception
      //   1055	1066	2946	java/lang/Exception
      //   1073	1083	2946	java/lang/Exception
      //   1090	1102	2946	java/lang/Exception
      //   1109	1120	2946	java/lang/Exception
      //   1127	1147	2946	java/lang/Exception
      //   1185	1196	2946	java/lang/Exception
      //   1203	1214	2946	java/lang/Exception
      //   1221	1233	2946	java/lang/Exception
      //   1240	1254	2946	java/lang/Exception
      //   1261	1281	2946	java/lang/Exception
      //   1288	1300	2946	java/lang/Exception
      //   1307	1321	2946	java/lang/Exception
      //   1328	1342	2946	java/lang/Exception
      //   1349	1362	2946	java/lang/Exception
      //   1369	1381	2946	java/lang/Exception
      //   1388	1400	2946	java/lang/Exception
      //   1412	1423	2946	java/lang/Exception
      //   1498	1529	2946	java/lang/Exception
      //   1536	1548	2946	java/lang/Exception
      //   1555	1569	2946	java/lang/Exception
      //   1576	1596	2946	java/lang/Exception
      //   1603	1623	2946	java/lang/Exception
      //   1630	1644	2946	java/lang/Exception
      //   1651	1665	2946	java/lang/Exception
      //   1672	1691	2946	java/lang/Exception
      //   1698	1713	2946	java/lang/Exception
      //   1720	1734	2946	java/lang/Exception
      //   1741	1771	2946	java/lang/Exception
      //   1778	1793	2946	java/lang/Exception
      //   1800	1810	2946	java/lang/Exception
      //   1817	1831	2946	java/lang/Exception
      //   1838	1850	2946	java/lang/Exception
      //   1857	1871	2946	java/lang/Exception
      //   1878	1891	2946	java/lang/Exception
      //   1903	1921	2946	java/lang/Exception
      //   1928	1937	2946	java/lang/Exception
      //   1948	1965	2946	java/lang/Exception
      //   1972	1982	2946	java/lang/Exception
      //   1989	2001	2946	java/lang/Exception
      //   2008	2016	2946	java/lang/Exception
      //   2023	2034	2946	java/lang/Exception
      //   2046	2051	2946	java/lang/Exception
      //   2058	2063	2946	java/lang/Exception
      //   2070	2086	2946	java/lang/Exception
      //   2093	2100	2946	java/lang/Exception
      //   2135	2142	2946	java/lang/Exception
      //   2149	2154	2946	java/lang/Exception
      //   2291	2296	2946	java/lang/Exception
      //   2303	2313	2946	java/lang/Exception
      //   2320	2336	2946	java/lang/Exception
      //   2343	2365	2946	java/lang/Exception
      //   2372	2388	2946	java/lang/Exception
      //   2395	2411	2946	java/lang/Exception
      //   2418	2434	2946	java/lang/Exception
      //   2441	2457	2946	java/lang/Exception
      //   2464	2480	2946	java/lang/Exception
      //   2493	2533	2946	java/lang/Exception
      //   2540	2550	2946	java/lang/Exception
      //   2686	2702	2946	java/lang/Exception
      //   2718	2735	2946	java/lang/Exception
      //   2742	2748	2946	java/lang/Exception
      //   2755	2774	2946	java/lang/Exception
      //   2781	2792	2946	java/lang/Exception
      //   2799	2804	2946	java/lang/Exception
      //   2874	2879	2946	java/lang/Exception
      //   2938	2943	2946	java/lang/Exception
      //   2985	2990	2946	java/lang/Exception
      //   3020	3032	2946	java/lang/Exception
      //   3044	3049	2946	java/lang/Exception
      //   3064	3081	2946	java/lang/Exception
      //   3088	3098	2946	java/lang/Exception
      //   3105	3117	2946	java/lang/Exception
      //   3124	3132	2946	java/lang/Exception
      //   3139	3150	2946	java/lang/Exception
      //   3165	3182	2946	java/lang/Exception
      //   3189	3199	2946	java/lang/Exception
      //   3206	3218	2946	java/lang/Exception
      //   3225	3233	2946	java/lang/Exception
      //   3240	3251	2946	java/lang/Exception
      //   3265	3282	2946	java/lang/Exception
      //   3289	3299	2946	java/lang/Exception
      //   3306	3318	2946	java/lang/Exception
      //   3325	3333	2946	java/lang/Exception
      //   3340	3351	2946	java/lang/Exception
      //   3366	3383	2946	java/lang/Exception
      //   3390	3400	2946	java/lang/Exception
      //   3407	3419	2946	java/lang/Exception
      //   3426	3434	2946	java/lang/Exception
      //   3441	3452	2946	java/lang/Exception
      //   3467	3484	2946	java/lang/Exception
      //   3491	3501	2946	java/lang/Exception
      //   3508	3520	2946	java/lang/Exception
      //   3532	3543	2946	java/lang/Exception
      //   3553	3564	2946	java/lang/Exception
      //   3571	3581	2946	java/lang/Exception
      //   3588	3604	2946	java/lang/Exception
      //   3611	3617	2946	java/lang/Exception
      //   3624	3636	2946	java/lang/Exception
      //   3643	3655	2946	java/lang/Exception
      //   3662	3678	2946	java/lang/Exception
      //   3685	3701	2946	java/lang/Exception
      //   3708	3724	2946	java/lang/Exception
      //   3872	3888	2946	java/lang/Exception
      //   3895	3908	2946	java/lang/Exception
      //   3915	3921	2946	java/lang/Exception
      //   3942	3961	2946	java/lang/Exception
      //   3968	3987	2946	java/lang/Exception
      //   3997	4013	2946	java/lang/Exception
      //   4020	4036	2946	java/lang/Exception
      //   4043	4062	2946	java/lang/Exception
      //   4072	4116	2946	java/lang/Exception
      //   4176	4185	2946	java/lang/Exception
      //   4192	4209	2946	java/lang/Exception
      //   4216	4223	2946	java/lang/Exception
      //   4235	4240	2946	java/lang/Exception
      //   4263	4268	2946	java/lang/Exception
      //   1150	1178	2976	java/lang/Exception
      //   13	28	2993	finally
      //   35	47	2993	finally
      //   54	66	2993	finally
      //   94	106	2993	finally
      //   113	125	2993	finally
      //   149	169	2993	finally
      //   180	197	2993	finally
      //   208	220	2993	finally
      //   231	238	2993	finally
      //   249	257	2993	finally
      //   272	281	2993	finally
      //   292	304	2993	finally
      //   315	327	2993	finally
      //   338	345	2993	finally
      //   356	373	2993	finally
      //   387	399	2993	finally
      //   406	415	2993	finally
      //   422	437	2993	finally
      //   444	459	2993	finally
      //   469	484	2993	finally
      //   491	506	2993	finally
      //   516	531	2993	finally
      //   538	553	2993	finally
      //   563	578	2993	finally
      //   585	600	2993	finally
      //   610	625	2993	finally
      //   632	647	2993	finally
      //   657	672	2993	finally
      //   679	694	2993	finally
      //   704	719	2993	finally
      //   726	741	2993	finally
      //   748	763	2993	finally
      //   770	785	2993	finally
      //   792	807	2993	finally
      //   814	828	2993	finally
      //   843	853	2993	finally
      //   864	874	2993	finally
      //   885	895	2993	finally
      //   907	917	2993	finally
      //   927	937	2993	finally
      //   949	958	2993	finally
      //   970	980	2993	finally
      //   992	1001	2993	finally
      //   1008	1014	2993	finally
      //   1021	1030	2993	finally
      //   1037	1048	2993	finally
      //   1055	1066	2993	finally
      //   1073	1083	2993	finally
      //   1090	1102	2993	finally
      //   1109	1120	2993	finally
      //   1127	1147	2993	finally
      //   1150	1178	2993	finally
      //   1185	1196	2993	finally
      //   1203	1214	2993	finally
      //   1221	1233	2993	finally
      //   1240	1254	2993	finally
      //   1261	1281	2993	finally
      //   1288	1300	2993	finally
      //   1307	1321	2993	finally
      //   1328	1342	2993	finally
      //   1349	1362	2993	finally
      //   1369	1381	2993	finally
      //   1388	1400	2993	finally
      //   1412	1423	2993	finally
      //   1426	1435	2993	finally
      //   1452	1463	2993	finally
      //   1498	1529	2993	finally
      //   1536	1548	2993	finally
      //   1555	1569	2993	finally
      //   1576	1596	2993	finally
      //   1603	1623	2993	finally
      //   1630	1644	2993	finally
      //   1651	1665	2993	finally
      //   1672	1691	2993	finally
      //   1698	1713	2993	finally
      //   1720	1734	2993	finally
      //   1741	1771	2993	finally
      //   1778	1793	2993	finally
      //   1800	1810	2993	finally
      //   1817	1831	2993	finally
      //   1838	1850	2993	finally
      //   1857	1871	2993	finally
      //   1878	1891	2993	finally
      //   1903	1921	2993	finally
      //   1928	1937	2993	finally
      //   1948	1965	2993	finally
      //   1972	1982	2993	finally
      //   1989	2001	2993	finally
      //   2008	2016	2993	finally
      //   2023	2034	2993	finally
      //   2046	2051	2993	finally
      //   2058	2063	2993	finally
      //   2070	2086	2993	finally
      //   2093	2100	2993	finally
      //   2135	2142	2993	finally
      //   2149	2154	2993	finally
      //   2165	2174	2993	finally
      //   2177	2185	2993	finally
      //   2188	2196	2993	finally
      //   2199	2208	2993	finally
      //   2211	2221	2993	finally
      //   2224	2239	2993	finally
      //   2242	2255	2993	finally
      //   2258	2268	2993	finally
      //   2271	2279	2993	finally
      //   2291	2296	2993	finally
      //   2303	2313	2993	finally
      //   2320	2336	2993	finally
      //   2343	2365	2993	finally
      //   2372	2388	2993	finally
      //   2395	2411	2993	finally
      //   2418	2434	2993	finally
      //   2441	2457	2993	finally
      //   2464	2480	2993	finally
      //   2493	2533	2993	finally
      //   2540	2550	2993	finally
      //   2686	2702	2993	finally
      //   2718	2735	2993	finally
      //   2742	2748	2993	finally
      //   2755	2774	2993	finally
      //   2781	2792	2993	finally
      //   2799	2804	2993	finally
      //   2812	2823	2993	finally
      //   2826	2832	2993	finally
      //   2835	2841	2993	finally
      //   2874	2879	2993	finally
      //   2938	2943	2993	finally
      //   2957	2962	2993	finally
      //   2985	2990	2993	finally
      //   3020	3032	2993	finally
      //   3044	3049	2993	finally
      //   3064	3081	2993	finally
      //   3088	3098	2993	finally
      //   3105	3117	2993	finally
      //   3124	3132	2993	finally
      //   3139	3150	2993	finally
      //   3165	3182	2993	finally
      //   3189	3199	2993	finally
      //   3206	3218	2993	finally
      //   3225	3233	2993	finally
      //   3240	3251	2993	finally
      //   3265	3282	2993	finally
      //   3289	3299	2993	finally
      //   3306	3318	2993	finally
      //   3325	3333	2993	finally
      //   3340	3351	2993	finally
      //   3366	3383	2993	finally
      //   3390	3400	2993	finally
      //   3407	3419	2993	finally
      //   3426	3434	2993	finally
      //   3441	3452	2993	finally
      //   3467	3484	2993	finally
      //   3491	3501	2993	finally
      //   3508	3520	2993	finally
      //   3532	3543	2993	finally
      //   3553	3564	2993	finally
      //   3571	3581	2993	finally
      //   3588	3604	2993	finally
      //   3611	3617	2993	finally
      //   3624	3636	2993	finally
      //   3643	3655	2993	finally
      //   3662	3678	2993	finally
      //   3685	3701	2993	finally
      //   3708	3724	2993	finally
      //   3730	3741	2993	finally
      //   3744	3763	2993	finally
      //   3766	3785	2993	finally
      //   3788	3810	2993	finally
      //   3813	3822	2993	finally
      //   3825	3839	2993	finally
      //   3842	3849	2993	finally
      //   3857	3862	2993	finally
      //   3872	3888	2993	finally
      //   3895	3908	2993	finally
      //   3915	3921	2993	finally
      //   3942	3961	2993	finally
      //   3968	3987	2993	finally
      //   3997	4013	2993	finally
      //   4020	4036	2993	finally
      //   4043	4062	2993	finally
      //   4072	4116	2993	finally
      //   4176	4185	2993	finally
      //   4192	4209	2993	finally
      //   4216	4223	2993	finally
      //   4235	4240	2993	finally
      //   4263	4268	2993	finally
      //   1426	1435	3035	java/lang/Exception
      //   1452	1463	3035	java/lang/Exception
      //   3744	3763	3852	java/lang/Exception
      //   3766	3785	3852	java/lang/Exception
      //   3788	3810	3852	java/lang/Exception
      //   2602	2617	4119	finally
      //   2550	2602	4128	java/io/IOException
      //   2617	2649	4128	java/io/IOException
      //   2653	2669	4128	java/io/IOException
      //   4121	4128	4128	java/io/IOException
      //   2781	2792	4226	org/json/JSONException
      //   2799	2804	4226	org/json/JSONException
      //   2812	2823	4254	java/lang/Exception
      //   2826	2832	4254	java/lang/Exception
      //   2835	2841	4254	java/lang/Exception
      //   2550	2602	4271	finally
      //   2617	2649	4271	finally
      //   2653	2669	4271	finally
      //   4121	4128	4271	finally
      //   4137	4142	4275	finally
      //   2550	2602	4286	java/lang/Exception
      //   2617	2649	4286	java/lang/Exception
      //   2653	2669	4286	java/lang/Exception
      //   4121	4128	4286	java/lang/Exception
      //   4137	4142	4291	java/lang/Exception
      //   2540	2550	4299	java/io/IOException
    }
    
    protected void a(JSONObject paramJSONObject)
    {
      super.onPostExecute(paramJSONObject);
      try
      {
        if (p.h(p.this) != null)
        {
          if (paramJSONObject == null)
          {
            p.h(p.this).a(p.i(p.this));
            return;
          }
          p.h(p.this).a(paramJSONObject, p.i(p.this), p.b(p.this));
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
