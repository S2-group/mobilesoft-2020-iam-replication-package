package com.appodeal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.appodeal.ads.d.h;
import com.appodeal.ads.f.e;
import com.appodeal.ads.f.f;
import com.appodeal.ads.f.g;
import com.appodeal.ads.utils.k;
import org.json.JSONArray;
import org.json.JSONException;
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
  private final com.appodeal.ads.f.c i;
  private final long j;
  private final String k;
  
  public p(Context paramContext, com.appodeal.ads.f.c paramC, a paramA, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, h paramH)
  {
    this(paramContext, paramC, paramA, paramInt, paramString1, paramString2, paramString3, paramString4, paramH, 0L, null);
  }
  
  p(Context paramContext, com.appodeal.ads.f.c paramC, a paramA, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, h paramH, long paramLong, String paramString5)
  {
    this.a = paramA;
    this.b = paramContext;
    this.c = paramInt;
    this.d = paramString1;
    this.e = paramString2;
    this.f = paramString3;
    this.g = paramString4;
    this.h = paramH;
    this.j = paramLong;
    this.i = paramC;
    this.k = paramString5;
    if ((AppodealSettings.a) && ((this.a == null) || ((this.a instanceof com.appodeal.ads.utils.q))) && ((this.d.equals("stats")) || (this.d.equals("show")) || (this.d.equals("click")) || (this.d.equals("finish")) || (this.d.equals("install")))) {
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
  
  p(Context paramContext, a paramA, int paramInt, String paramString)
  {
    this(paramContext, null, paramA, paramInt, paramString, null, null, null, null);
  }
  
  public static abstract interface a
  {
    public abstract void a(int paramInt);
    
    public abstract void a(JSONObject paramJSONObject, int paramInt, String paramString);
  }
  
  private class b
    extends AsyncTask<Void, Void, JSONObject>
  {
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    
    private b() {}
    
    private JSONObject a()
    {
      JSONObject localJSONObject = new JSONObject();
      int j = c.q;
      int k = j.m;
      int m = ad.l;
      int n = ag.l;
      int i1 = w.l;
      int i2 = q.p;
      int i3 = c.r;
      int i4 = j.n;
      int i5 = ad.n;
      int i6 = ag.n;
      int i7 = w.m;
      int i8 = q.q;
      int i9 = ag.m;
      int i10 = ad.m;
      try
      {
        localJSONObject.put("show", j + k + m + n + i1 + i2);
        localJSONObject.put("click", i3 + i4 + i5 + i6 + i7 + i8);
        if ((this.e) || (this.f) || ((p.g(p.this) != null) && ((p.g(p.this).equals("video")) || (p.g(p.this).equals("rewarded_video"))))) {
          localJSONObject.put("finish", i9 + i10);
        }
        if ((this.b) || ((p.g(p.this) != null) && (p.g(p.this).equals("banner"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "show" }), j.m);
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "click" }), j.n);
        }
        if ((this.e) || ((p.g(p.this) != null) && (p.g(p.this).equals("video"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "video", "show" }), ad.l);
          localJSONObject.put(String.format("%s_%s", new Object[] { "video", "click" }), ad.n);
          localJSONObject.put(String.format("%s_%s", new Object[] { "video", "finish" }), ad.m);
        }
        if ((this.f) || ((p.g(p.this) != null) && (p.g(p.this).equals("rewarded_video"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "show" }), ag.l);
          localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "click" }), ag.n);
          localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "finish" }), ag.m);
        }
        if ((this.c) || ((p.g(p.this) != null) && (p.g(p.this).equals("banner_320"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "show" }), c.q);
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "click" }), c.r);
        }
        if ((this.d) || ((p.g(p.this) != null) && (p.g(p.this).equals("banner_mrec"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "show" }), q.p);
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "click" }), q.q);
        }
        if ((this.g) || ((p.g(p.this) != null) && (p.g(p.this).equals("native"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "native", "show" }), w.l);
          localJSONObject.put(String.format("%s_%s", new Object[] { "native", "click" }), w.m);
        }
        return localJSONObject;
      }
      catch (Exception localException)
      {
        Appodeal.a(localException);
      }
      return localJSONObject;
    }
    
    private void b(JSONObject paramJSONObject)
    {
      JSONArray localJSONArray = paramJSONObject.optJSONArray("segments");
      if ((localJSONArray == null) || (localJSONArray.length() == 0)) {
        return;
      }
      paramJSONObject = new f(p.b(p.this), paramJSONObject).a(localJSONArray);
      if (paramJSONObject != null) {}
      for (;;)
      {
        try
        {
          paramJSONObject.a();
          new g(p.b(p.this)).a(g.a());
          f.a = Long.valueOf(paramJSONObject.b());
          if (Appodeal.d == null) {
            break;
          }
          Appodeal.d.a();
          return;
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
          continue;
        }
        f.a = Long.valueOf(-1L);
      }
    }
    
    /* Error */
    protected JSONObject a(Void... paramVarArgs)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 11
      //   3: aconst_null
      //   4: astore 9
      //   6: aconst_null
      //   7: astore 10
      //   9: aload 10
      //   11: astore 5
      //   13: aload 11
      //   15: astore_1
      //   16: aload_0
      //   17: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   20: invokestatic 150	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   23: ldc -55
      //   25: iconst_0
      //   26: invokevirtual 207	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   29: astore 12
      //   31: aload 10
      //   33: astore 5
      //   35: aload 11
      //   37: astore_1
      //   38: invokestatic 213	java/util/Calendar:getInstance	()Ljava/util/Calendar;
      //   41: astore 4
      //   43: aload 10
      //   45: astore 5
      //   47: aload 11
      //   49: astore_1
      //   50: aload 4
      //   52: aload 12
      //   54: ldc -41
      //   56: lconst_0
      //   57: invokeinterface 221 4 0
      //   62: invokevirtual 225	java/util/Calendar:setTimeInMillis	(J)V
      //   65: aload 10
      //   67: astore 5
      //   69: aload 11
      //   71: astore_1
      //   72: aload 4
      //   74: iconst_5
      //   75: iconst_1
      //   76: invokevirtual 229	java/util/Calendar:add	(II)V
      //   79: aload 10
      //   81: astore 5
      //   83: aload 11
      //   85: astore_1
      //   86: getstatic 234	com/appodeal/ads/UserSettings:sendingInProgress	Z
      //   89: ifne +4615 -> 4704
      //   92: aload 10
      //   94: astore 5
      //   96: aload 11
      //   98: astore_1
      //   99: aload_0
      //   100: getfield 236	com/appodeal/ads/p$b:i	Z
      //   103: ifeq +4601 -> 4704
      //   106: aload 10
      //   108: astore 5
      //   110: aload 11
      //   112: astore_1
      //   113: aload 4
      //   115: invokevirtual 239	java/util/Calendar:getTimeInMillis	()J
      //   118: invokestatic 244	java/lang/System:currentTimeMillis	()J
      //   121: lcmp
      //   122: iflt +23 -> 145
      //   125: aload 10
      //   127: astore 5
      //   129: aload 11
      //   131: astore_1
      //   132: aload 12
      //   134: ldc -10
      //   136: iconst_1
      //   137: invokeinterface 250 3 0
      //   142: ifeq +4562 -> 4704
      //   145: aload 10
      //   147: astore 5
      //   149: aload 11
      //   151: astore_1
      //   152: iconst_1
      //   153: putstatic 234	com/appodeal/ads/UserSettings:sendingInProgress	Z
      //   156: iconst_1
      //   157: istore_2
      //   158: aload 10
      //   160: astore 5
      //   162: aload 11
      //   164: astore_1
      //   165: aload_0
      //   166: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   169: invokestatic 150	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   172: invokevirtual 254	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   175: astore 13
      //   177: aload 10
      //   179: astore 5
      //   181: aload 11
      //   183: astore_1
      //   184: aload 12
      //   186: ldc_w 256
      //   189: aconst_null
      //   190: invokeinterface 260 3 0
      //   195: astore 14
      //   197: aload 14
      //   199: ifnonnull +40 -> 239
      //   202: iconst_0
      //   203: ifeq +17 -> 220
      //   206: aconst_null
      //   207: instanceof 262
      //   210: ifeq +12 -> 222
      //   213: aconst_null
      //   214: checkcast 262	javax/net/ssl/HttpsURLConnection
      //   217: invokevirtual 265	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   220: aconst_null
      //   221: areturn
      //   222: aconst_null
      //   223: instanceof 267
      //   226: ifeq -6 -> 220
      //   229: aconst_null
      //   230: checkcast 267	java/net/HttpURLConnection
      //   233: invokevirtual 268	java/net/HttpURLConnection:disconnect	()V
      //   236: goto -16 -> 220
      //   239: aload 10
      //   241: astore 5
      //   243: aload 11
      //   245: astore_1
      //   246: aload 12
      //   248: ldc_w 270
      //   251: aconst_null
      //   252: invokeinterface 260 3 0
      //   257: astore 8
      //   259: aload 10
      //   261: astore 5
      //   263: aload 11
      //   265: astore_1
      //   266: aload 12
      //   268: ldc_w 272
      //   271: aconst_null
      //   272: invokeinterface 260 3 0
      //   277: astore 4
      //   279: aload 4
      //   281: astore 6
      //   283: aload 8
      //   285: astore 7
      //   287: aload 8
      //   289: ifnonnull +2653 -> 2942
      //   292: aload 10
      //   294: astore 5
      //   296: aload 11
      //   298: astore_1
      //   299: aload_0
      //   300: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   303: invokestatic 274	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   306: ldc_w 276
      //   309: invokevirtual 97	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   312: istore_3
      //   313: aload 4
      //   315: astore 6
      //   317: aload 8
      //   319: astore 7
      //   321: iload_3
      //   322: ifne +2620 -> 2942
      //   325: aload 4
      //   327: astore 6
      //   329: aload 8
      //   331: astore 7
      //   333: aload 11
      //   335: astore_1
      //   336: ldc_w 278
      //   339: invokestatic 284	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
      //   342: pop
      //   343: aload 4
      //   345: astore 6
      //   347: aload 8
      //   349: astore 7
      //   351: aload 11
      //   353: astore_1
      //   354: ldc_w 286
      //   357: ldc_w 288
      //   360: iconst_1
      //   361: anewarray 280	java/lang/Class
      //   364: dup
      //   365: iconst_0
      //   366: ldc -53
      //   368: aastore
      //   369: invokevirtual 292	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      //   372: pop
      //   373: aload 4
      //   375: astore 6
      //   377: aload 8
      //   379: astore 7
      //   381: aload 11
      //   383: astore_1
      //   384: aload_0
      //   385: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   388: invokestatic 150	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   391: invokestatic 295	com/google/android/gms/ads/identifier/AdvertisingIdClient:getAdvertisingIdInfo	(Landroid/content/Context;)Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;
      //   394: astore 15
      //   396: aload 4
      //   398: astore 6
      //   400: aload 8
      //   402: astore 7
      //   404: aload 11
      //   406: astore_1
      //   407: aload 15
      //   409: invokevirtual 301	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:getId	()Ljava/lang/String;
      //   412: astore 5
      //   414: aload 4
      //   416: astore 6
      //   418: aload 5
      //   420: astore 7
      //   422: aload 11
      //   424: astore_1
      //   425: aload 15
      //   427: invokevirtual 305	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:isLimitAdTrackingEnabled	()Z
      //   430: ifeq +2490 -> 2920
      //   433: ldc_w 307
      //   436: astore 4
      //   438: aload 4
      //   440: astore 6
      //   442: aload 5
      //   444: astore 7
      //   446: aload 11
      //   448: astore_1
      //   449: aload 12
      //   451: invokeinterface 311 1 0
      //   456: astore 8
      //   458: aload 4
      //   460: astore 6
      //   462: aload 5
      //   464: astore 7
      //   466: aload 11
      //   468: astore_1
      //   469: aload 8
      //   471: ldc_w 270
      //   474: aload 5
      //   476: invokeinterface 317 3 0
      //   481: pop
      //   482: aload 4
      //   484: astore 6
      //   486: aload 5
      //   488: astore 7
      //   490: aload 11
      //   492: astore_1
      //   493: aload 8
      //   495: ldc_w 272
      //   498: aload 4
      //   500: invokeinterface 317 3 0
      //   505: pop
      //   506: aload 4
      //   508: astore 6
      //   510: aload 5
      //   512: astore 7
      //   514: aload 11
      //   516: astore_1
      //   517: aload 8
      //   519: invokeinterface 320 1 0
      //   524: aload 4
      //   526: astore 6
      //   528: aload 5
      //   530: astore 7
      //   532: aload 11
      //   534: astore_1
      //   535: ldc_w 322
      //   538: iconst_1
      //   539: anewarray 109	java/lang/Object
      //   542: dup
      //   543: iconst_0
      //   544: aload 5
      //   546: aastore
      //   547: invokestatic 113	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   550: invokestatic 325	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   553: aload 5
      //   555: astore_1
      //   556: aload_1
      //   557: ifnonnull +4141 -> 4698
      //   560: aload 10
      //   562: astore 5
      //   564: aload 11
      //   566: astore_1
      //   567: aload_0
      //   568: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   571: invokestatic 150	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   574: invokestatic 331	com/appodeal/ads/aj:j	(Landroid/content/Context;)Ljava/lang/String;
      //   577: astore 6
      //   579: aload 10
      //   581: astore 5
      //   583: aload 11
      //   585: astore_1
      //   586: new 34	org/json/JSONObject
      //   589: dup
      //   590: invokespecial 35	org/json/JSONObject:<init>	()V
      //   593: astore 7
      //   595: aload 10
      //   597: astore 5
      //   599: aload 11
      //   601: astore_1
      //   602: aload 7
      //   604: ldc_w 333
      //   607: aload 14
      //   609: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   612: pop
      //   613: aload 10
      //   615: astore 5
      //   617: aload 11
      //   619: astore_1
      //   620: aload_0
      //   621: getfield 103	com/appodeal/ads/p$b:b	Z
      //   624: ifeq +21 -> 645
      //   627: aload 10
      //   629: astore 5
      //   631: aload 11
      //   633: astore_1
      //   634: aload 7
      //   636: ldc_w 338
      //   639: ldc 105
      //   641: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   644: pop
      //   645: aload 10
      //   647: astore 5
      //   649: aload 11
      //   651: astore_1
      //   652: aload_0
      //   653: getfield 115	com/appodeal/ads/p$b:c	Z
      //   656: ifeq +21 -> 677
      //   659: aload 10
      //   661: astore 5
      //   663: aload 11
      //   665: astore_1
      //   666: aload 7
      //   668: ldc_w 338
      //   671: ldc 117
      //   673: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   676: pop
      //   677: aload 10
      //   679: astore 5
      //   681: aload 11
      //   683: astore_1
      //   684: aload_0
      //   685: getfield 119	com/appodeal/ads/p$b:d	Z
      //   688: ifeq +21 -> 709
      //   691: aload 10
      //   693: astore 5
      //   695: aload 11
      //   697: astore_1
      //   698: aload 7
      //   700: ldc_w 338
      //   703: ldc 121
      //   705: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   708: pop
      //   709: aload 10
      //   711: astore 5
      //   713: aload 11
      //   715: astore_1
      //   716: aload_0
      //   717: getfield 84	com/appodeal/ads/p$b:e	Z
      //   720: ifne +17 -> 737
      //   723: aload 10
      //   725: astore 5
      //   727: aload 11
      //   729: astore_1
      //   730: aload_0
      //   731: getfield 86	com/appodeal/ads/p$b:f	Z
      //   734: ifeq +21 -> 755
      //   737: aload 10
      //   739: astore 5
      //   741: aload 11
      //   743: astore_1
      //   744: aload 7
      //   746: ldc_w 338
      //   749: ldc 91
      //   751: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   754: pop
      //   755: aload 10
      //   757: astore 5
      //   759: aload 11
      //   761: astore_1
      //   762: aload_0
      //   763: getfield 86	com/appodeal/ads/p$b:f	Z
      //   766: ifeq +19 -> 785
      //   769: aload 10
      //   771: astore 5
      //   773: aload 11
      //   775: astore_1
      //   776: aload 7
      //   778: ldc 99
      //   780: iconst_1
      //   781: invokevirtual 341	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   784: pop
      //   785: aload 10
      //   787: astore 5
      //   789: aload 11
      //   791: astore_1
      //   792: aload_0
      //   793: getfield 123	com/appodeal/ads/p$b:g	Z
      //   796: ifeq +21 -> 817
      //   799: aload 10
      //   801: astore 5
      //   803: aload 11
      //   805: astore_1
      //   806: aload 7
      //   808: ldc_w 338
      //   811: ldc 125
      //   813: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   816: pop
      //   817: aload 10
      //   819: astore 5
      //   821: aload 11
      //   823: astore_1
      //   824: aload_0
      //   825: getfield 343	com/appodeal/ads/p$b:h	Z
      //   828: ifeq +20 -> 848
      //   831: aload 10
      //   833: astore 5
      //   835: aload 11
      //   837: astore_1
      //   838: aload 7
      //   840: ldc_w 345
      //   843: iconst_1
      //   844: invokevirtual 341	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   847: pop
      //   848: aload 10
      //   850: astore 5
      //   852: aload 11
      //   854: astore_1
      //   855: getstatic 349	com/appodeal/ads/AppodealSettings:a	Z
      //   858: ifeq +20 -> 878
      //   861: aload 10
      //   863: astore 5
      //   865: aload 11
      //   867: astore_1
      //   868: aload 7
      //   870: ldc_w 351
      //   873: iconst_1
      //   874: invokevirtual 341	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   877: pop
      //   878: aload 10
      //   880: astore 5
      //   882: aload 11
      //   884: astore_1
      //   885: aload 7
      //   887: ldc_w 353
      //   890: getstatic 359	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
      //   893: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   896: pop
      //   897: aload 10
      //   899: astore 5
      //   901: aload 11
      //   903: astore_1
      //   904: aload 7
      //   906: ldc_w 361
      //   909: getstatic 364	android/os/Build$VERSION:SDK_INT	I
      //   912: invokevirtual 80	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
      //   915: pop
      //   916: aload 10
      //   918: astore 5
      //   920: aload 11
      //   922: astore_1
      //   923: aload 7
      //   925: ldc_w 366
      //   928: ldc_w 368
      //   931: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   934: pop
      //   935: aload 10
      //   937: astore 5
      //   939: aload 11
      //   941: astore_1
      //   942: aload_0
      //   943: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   946: invokestatic 150	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   949: invokevirtual 371	android/content/Context:getPackageName	()Ljava/lang/String;
      //   952: astore 8
      //   954: aload 10
      //   956: astore 5
      //   958: aload 11
      //   960: astore_1
      //   961: aload 7
      //   963: ldc_w 373
      //   966: aload 8
      //   968: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   971: pop
      //   972: aload 10
      //   974: astore 5
      //   976: aload 11
      //   978: astore_1
      //   979: aload 13
      //   981: aload 8
      //   983: iconst_0
      //   984: invokevirtual 379	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
      //   987: astore 14
      //   989: aload 10
      //   991: astore 5
      //   993: aload 11
      //   995: astore_1
      //   996: aload 7
      //   998: ldc_w 381
      //   1001: aload 14
      //   1003: getfield 386	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
      //   1006: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1009: pop
      //   1010: aload 10
      //   1012: astore 5
      //   1014: aload 11
      //   1016: astore_1
      //   1017: aload 7
      //   1019: ldc_w 388
      //   1022: aload 14
      //   1024: getfield 391	android/content/pm/PackageInfo:versionCode	I
      //   1027: invokevirtual 80	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
      //   1030: pop
      //   1031: aload 11
      //   1033: astore_1
      //   1034: aload 7
      //   1036: ldc_w 393
      //   1039: aload 13
      //   1041: aload 8
      //   1043: sipush 128
      //   1046: invokevirtual 397	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
      //   1049: getfield 403	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
      //   1052: ldc_w 405
      //   1055: invokevirtual 410	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   1058: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1061: pop
      //   1062: aload 10
      //   1064: astore 5
      //   1066: aload 11
      //   1068: astore_1
      //   1069: aload 7
      //   1071: ldc_w 412
      //   1074: aload 6
      //   1076: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1079: pop
      //   1080: aload 10
      //   1082: astore 5
      //   1084: aload 11
      //   1086: astore_1
      //   1087: aload 7
      //   1089: ldc_w 414
      //   1092: aload 4
      //   1094: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1097: pop
      //   1098: aload 10
      //   1100: astore 5
      //   1102: aload 11
      //   1104: astore_1
      //   1105: aload_0
      //   1106: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   1109: invokestatic 150	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   1112: invokestatic 417	com/appodeal/ads/aj:b	(Landroid/content/Context;)Lcom/appodeal/ads/aj$a;
      //   1115: astore 6
      //   1117: aload 10
      //   1119: astore 5
      //   1121: aload 11
      //   1123: astore_1
      //   1124: aload 7
      //   1126: ldc_w 419
      //   1129: aload 6
      //   1131: getfield 423	com/appodeal/ads/aj$a:a	Ljava/lang/String;
      //   1134: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1137: pop
      //   1138: aload 10
      //   1140: astore 5
      //   1142: aload 11
      //   1144: astore_1
      //   1145: aload 7
      //   1147: ldc_w 425
      //   1150: aload_0
      //   1151: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   1154: invokestatic 150	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   1157: invokestatic 428	com/appodeal/ads/aj:i	(Landroid/content/Context;)F
      //   1160: f2d
      //   1161: invokevirtual 431	org/json/JSONObject:put	(Ljava/lang/String;D)Lorg/json/JSONObject;
      //   1164: pop
      //   1165: aload 10
      //   1167: astore 5
      //   1169: aload 11
      //   1171: astore_1
      //   1172: aload_0
      //   1173: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   1176: invokestatic 150	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   1179: invokestatic 434	com/appodeal/ads/aj:f	(Landroid/content/Context;)Landroid/util/Pair;
      //   1182: astore 4
      //   1184: aload 10
      //   1186: astore 5
      //   1188: aload 11
      //   1190: astore_1
      //   1191: aload 7
      //   1193: ldc_w 436
      //   1196: aload 4
      //   1198: getfield 442	android/util/Pair:first	Ljava/lang/Object;
      //   1201: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1204: pop
      //   1205: aload 10
      //   1207: astore 5
      //   1209: aload 11
      //   1211: astore_1
      //   1212: aload 7
      //   1214: ldc_w 444
      //   1217: aload 4
      //   1219: getfield 447	android/util/Pair:second	Ljava/lang/Object;
      //   1222: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1225: pop
      //   1226: aload 10
      //   1228: astore 5
      //   1230: aload 11
      //   1232: astore_1
      //   1233: aload_0
      //   1234: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   1237: invokestatic 150	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   1240: invokestatic 450	com/appodeal/ads/aj:l	(Landroid/content/Context;)Z
      //   1243: ifeq +1799 -> 3042
      //   1246: aload 10
      //   1248: astore 5
      //   1250: aload 11
      //   1252: astore_1
      //   1253: aload 7
      //   1255: ldc_w 452
      //   1258: ldc_w 454
      //   1261: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1264: pop
      //   1265: aload 10
      //   1267: astore 5
      //   1269: aload 11
      //   1271: astore_1
      //   1272: getstatic 459	android/os/Build:MANUFACTURER	Ljava/lang/String;
      //   1275: ldc_w 461
      //   1278: invokevirtual 97	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1281: ifeq +3428 -> 4709
      //   1284: ldc_w 463
      //   1287: astore 4
      //   1289: aload 10
      //   1291: astore 5
      //   1293: aload 11
      //   1295: astore_1
      //   1296: aload 7
      //   1298: ldc_w 465
      //   1301: aload 4
      //   1303: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1306: pop
      //   1307: aload 11
      //   1309: astore_1
      //   1310: aload 13
      //   1312: aload 8
      //   1314: invokevirtual 468	android/content/pm/PackageManager:getInstallerPackageName	(Ljava/lang/String;)Ljava/lang/String;
      //   1317: astore 5
      //   1319: aload 5
      //   1321: astore 4
      //   1323: aload 5
      //   1325: ifnonnull +8 -> 1333
      //   1328: ldc_w 470
      //   1331: astore 4
      //   1333: aload 11
      //   1335: astore_1
      //   1336: aload 7
      //   1338: ldc_w 472
      //   1341: aload 4
      //   1343: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1346: pop
      //   1347: aload 10
      //   1349: astore 5
      //   1351: aload 11
      //   1353: astore_1
      //   1354: aload 7
      //   1356: ldc_w 474
      //   1359: ldc_w 476
      //   1362: iconst_2
      //   1363: anewarray 109	java/lang/Object
      //   1366: dup
      //   1367: iconst_0
      //   1368: getstatic 459	android/os/Build:MANUFACTURER	Ljava/lang/String;
      //   1371: aastore
      //   1372: dup
      //   1373: iconst_1
      //   1374: getstatic 479	android/os/Build:MODEL	Ljava/lang/String;
      //   1377: aastore
      //   1378: invokestatic 113	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   1381: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1384: pop
      //   1385: aload 10
      //   1387: astore 5
      //   1389: aload 11
      //   1391: astore_1
      //   1392: aload_0
      //   1393: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   1396: invokestatic 150	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   1399: invokestatic 481	com/appodeal/ads/aj:d	(Landroid/content/Context;)Landroid/util/Pair;
      //   1402: astore 4
      //   1404: aload 10
      //   1406: astore 5
      //   1408: aload 11
      //   1410: astore_1
      //   1411: aload 7
      //   1413: ldc_w 483
      //   1416: aload 4
      //   1418: getfield 442	android/util/Pair:first	Ljava/lang/Object;
      //   1421: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1424: pop
      //   1425: aload 10
      //   1427: astore 5
      //   1429: aload 11
      //   1431: astore_1
      //   1432: aload 7
      //   1434: ldc_w 485
      //   1437: aload 4
      //   1439: getfield 447	android/util/Pair:second	Ljava/lang/Object;
      //   1442: checkcast 438	android/util/Pair
      //   1445: getfield 442	android/util/Pair:first	Ljava/lang/Object;
      //   1448: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1451: pop
      //   1452: aload 10
      //   1454: astore 5
      //   1456: aload 11
      //   1458: astore_1
      //   1459: aload 7
      //   1461: ldc_w 487
      //   1464: aload 4
      //   1466: getfield 447	android/util/Pair:second	Ljava/lang/Object;
      //   1469: checkcast 438	android/util/Pair
      //   1472: getfield 447	android/util/Pair:second	Ljava/lang/Object;
      //   1475: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1478: pop
      //   1479: aload 10
      //   1481: astore 5
      //   1483: aload 11
      //   1485: astore_1
      //   1486: aload 7
      //   1488: ldc_w 489
      //   1491: aload 6
      //   1493: getfield 491	com/appodeal/ads/aj$a:b	Ljava/lang/String;
      //   1496: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1499: pop
      //   1500: aload 10
      //   1502: astore 5
      //   1504: aload 11
      //   1506: astore_1
      //   1507: aload 7
      //   1509: ldc_w 493
      //   1512: aload 6
      //   1514: getfield 494	com/appodeal/ads/aj$a:c	Z
      //   1517: invokevirtual 341	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   1520: pop
      //   1521: aload 10
      //   1523: astore 5
      //   1525: aload 11
      //   1527: astore_1
      //   1528: aload 7
      //   1530: ldc_w 496
      //   1533: aload_0
      //   1534: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   1537: invokestatic 150	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   1540: invokestatic 498	com/appodeal/ads/aj:c	(Landroid/content/Context;)Ljava/lang/String;
      //   1543: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1546: pop
      //   1547: aload 10
      //   1549: astore 5
      //   1551: aload 11
      //   1553: astore_1
      //   1554: aload 7
      //   1556: ldc_w 500
      //   1559: invokestatic 506	java/util/Locale:getDefault	()Ljava/util/Locale;
      //   1562: invokevirtual 509	java/util/Locale:toString	()Ljava/lang/String;
      //   1565: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1568: pop
      //   1569: aload 10
      //   1571: astore 5
      //   1573: aload 11
      //   1575: astore_1
      //   1576: ldc_w 511
      //   1579: invokestatic 517	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
      //   1582: getstatic 521	java/util/Locale:ENGLISH	Ljava/util/Locale;
      //   1585: invokestatic 524	java/util/Calendar:getInstance	(Ljava/util/TimeZone;Ljava/util/Locale;)Ljava/util/Calendar;
      //   1588: astore 4
      //   1590: aload 10
      //   1592: astore 5
      //   1594: aload 11
      //   1596: astore_1
      //   1597: aload 7
      //   1599: ldc_w 526
      //   1602: new 528	java/text/SimpleDateFormat
      //   1605: dup
      //   1606: ldc_w 529
      //   1609: getstatic 521	java/util/Locale:ENGLISH	Ljava/util/Locale;
      //   1612: invokespecial 532	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
      //   1615: aload 4
      //   1617: invokevirtual 536	java/util/Calendar:getTime	()Ljava/util/Date;
      //   1620: invokevirtual 539	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
      //   1623: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1626: pop
      //   1627: aload 10
      //   1629: astore 5
      //   1631: aload 11
      //   1633: astore_1
      //   1634: aload 7
      //   1636: ldc_w 541
      //   1639: invokestatic 244	java/lang/System:currentTimeMillis	()J
      //   1642: ldc2_w 542
      //   1645: ldiv
      //   1646: invokevirtual 546	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   1649: pop
      //   1650: aload 10
      //   1652: astore 5
      //   1654: aload 11
      //   1656: astore_1
      //   1657: aload 7
      //   1659: ldc_w 548
      //   1662: ldc_w 550
      //   1665: invokestatic 553	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
      //   1668: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1671: pop
      //   1672: aload 10
      //   1674: astore 5
      //   1676: aload 11
      //   1678: astore_1
      //   1679: aload 7
      //   1681: ldc_w 555
      //   1684: invokestatic 557	com/appodeal/ads/aj:a	()Z
      //   1687: invokevirtual 341	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   1690: pop
      //   1691: aload 10
      //   1693: astore 5
      //   1695: aload 11
      //   1697: astore_1
      //   1698: aload_0
      //   1699: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   1702: invokestatic 150	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   1705: invokestatic 561	com/appodeal/ads/utils/b:c	(Landroid/content/Context;)V
      //   1708: aload 10
      //   1710: astore 5
      //   1712: aload 11
      //   1714: astore_1
      //   1715: aload 7
      //   1717: ldc_w 563
      //   1720: aload 12
      //   1722: invokestatic 566	com/appodeal/ads/utils/b:a	(Landroid/content/SharedPreferences;)J
      //   1725: invokevirtual 546	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   1728: pop
      //   1729: aload 10
      //   1731: astore 5
      //   1733: aload 11
      //   1735: astore_1
      //   1736: aload 7
      //   1738: ldc_w 568
      //   1741: invokestatic 569	com/appodeal/ads/utils/b:b	()J
      //   1744: invokevirtual 546	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   1747: pop
      //   1748: aload 10
      //   1750: astore 5
      //   1752: aload 11
      //   1754: astore_1
      //   1755: aload 7
      //   1757: ldc_w 571
      //   1760: aload 12
      //   1762: invokestatic 573	com/appodeal/ads/utils/b:b	(Landroid/content/SharedPreferences;)J
      //   1765: invokevirtual 546	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   1768: pop
      //   1769: aload 10
      //   1771: astore 5
      //   1773: aload 11
      //   1775: astore_1
      //   1776: aload 12
      //   1778: ldc_w 575
      //   1781: aconst_null
      //   1782: invokeinterface 260 3 0
      //   1787: astore 4
      //   1789: aload 4
      //   1791: ifnull +28 -> 1819
      //   1794: aload 10
      //   1796: astore 5
      //   1798: aload 11
      //   1800: astore_1
      //   1801: aload 7
      //   1803: ldc_w 575
      //   1806: new 34	org/json/JSONObject
      //   1809: dup
      //   1810: aload 4
      //   1812: invokespecial 577	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   1815: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1818: pop
      //   1819: aload 10
      //   1821: astore 5
      //   1823: aload 11
      //   1825: astore_1
      //   1826: aload_0
      //   1827: getfield 236	com/appodeal/ads/p$b:i	Z
      //   1830: ifeq +1809 -> 3639
      //   1833: aload 10
      //   1835: astore 5
      //   1837: aload 11
      //   1839: astore_1
      //   1840: new 141	org/json/JSONArray
      //   1843: dup
      //   1844: invokespecial 578	org/json/JSONArray:<init>	()V
      //   1847: astore 4
      //   1849: aload 10
      //   1851: astore 5
      //   1853: aload 11
      //   1855: astore_1
      //   1856: aload_0
      //   1857: getfield 103	com/appodeal/ads/p$b:b	Z
      //   1860: ifeq +1221 -> 3081
      //   1863: aload 10
      //   1865: astore 5
      //   1867: aload 11
      //   1869: astore_1
      //   1870: aload_0
      //   1871: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   1874: invokestatic 150	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   1877: invokestatic 581	com/appodeal/ads/j:a	(Landroid/content/Context;)Ljava/util/List;
      //   1880: invokeinterface 587 1 0
      //   1885: astore 6
      //   1887: aload 10
      //   1889: astore 5
      //   1891: aload 11
      //   1893: astore_1
      //   1894: aload 6
      //   1896: invokeinterface 592 1 0
      //   1901: ifeq +1180 -> 3081
      //   1904: aload 10
      //   1906: astore 5
      //   1908: aload 11
      //   1910: astore_1
      //   1911: aload 6
      //   1913: invokeinterface 596 1 0
      //   1918: checkcast 598	com/appodeal/ads/k
      //   1921: astore 8
      //   1923: aload 10
      //   1925: astore 5
      //   1927: aload 11
      //   1929: astore_1
      //   1930: aload 8
      //   1932: invokevirtual 601	com/appodeal/ads/k:h	()Lcom/appodeal/ads/n;
      //   1935: ifnull -48 -> 1887
      //   1938: aload 10
      //   1940: astore 5
      //   1942: aload 11
      //   1944: astore_1
      //   1945: aload 4
      //   1947: aload 8
      //   1949: invokevirtual 603	com/appodeal/ads/k:a	()Ljava/lang/String;
      //   1952: invokevirtual 606	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   1955: pop
      //   1956: goto -69 -> 1887
      //   1959: astore 4
      //   1961: aload 10
      //   1963: astore 5
      //   1965: aload 11
      //   1967: astore_1
      //   1968: aload 4
      //   1970: invokestatic 130	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   1973: iload_2
      //   1974: ifeq +286 -> 2260
      //   1977: aload 11
      //   1979: astore_1
      //   1980: new 141	org/json/JSONArray
      //   1983: dup
      //   1984: invokespecial 578	org/json/JSONArray:<init>	()V
      //   1987: astore 4
      //   1989: aload 11
      //   1991: astore_1
      //   1992: aload 13
      //   1994: iconst_0
      //   1995: invokevirtual 610	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
      //   1998: astore 6
      //   2000: aload 11
      //   2002: astore_1
      //   2003: ldc_w 612
      //   2006: invokestatic 618	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
      //   2009: astore 5
      //   2011: aload 11
      //   2013: astore_1
      //   2014: aload 6
      //   2016: invokeinterface 587 1 0
      //   2021: astore 6
      //   2023: aload 11
      //   2025: astore_1
      //   2026: aload 6
      //   2028: invokeinterface 592 1 0
      //   2033: ifeq +1953 -> 3986
      //   2036: aload 11
      //   2038: astore_1
      //   2039: aload 6
      //   2041: invokeinterface 596 1 0
      //   2046: checkcast 399	android/content/pm/ApplicationInfo
      //   2049: getfield 621	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   2052: astore 8
      //   2054: aload 11
      //   2056: astore_1
      //   2057: aload 5
      //   2059: aload 8
      //   2061: invokevirtual 625	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
      //   2064: invokevirtual 630	java/util/regex/Matcher:matches	()Z
      //   2067: ifne -44 -> 2023
      //   2070: aload 11
      //   2072: astore_1
      //   2073: aload 8
      //   2075: ldc_w 353
      //   2078: invokevirtual 97	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2081: ifne -58 -> 2023
      //   2084: aload 11
      //   2086: astore_1
      //   2087: aload 4
      //   2089: aload 8
      //   2091: invokevirtual 606	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   2094: pop
      //   2095: goto -72 -> 2023
      //   2098: astore 4
      //   2100: aload 10
      //   2102: astore 5
      //   2104: aload 11
      //   2106: astore_1
      //   2107: aload 4
      //   2109: invokestatic 130	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2112: aload 11
      //   2114: astore_1
      //   2115: aload 7
      //   2117: ldc_w 632
      //   2120: aload_0
      //   2121: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   2124: invokestatic 150	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   2127: invokestatic 637	com/appodeal/ads/utils/n:a	(Landroid/content/Context;)Lorg/json/JSONArray;
      //   2130: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2133: pop
      //   2134: aload 11
      //   2136: astore_1
      //   2137: aload 7
      //   2139: ldc_w 639
      //   2142: aload_0
      //   2143: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   2146: invokestatic 150	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   2149: invokestatic 643	com/appodeal/ads/utils/o:a	(Landroid/content/Context;)Ljava/lang/String;
      //   2152: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2155: pop
      //   2156: aload 11
      //   2158: astore_1
      //   2159: aload 7
      //   2161: ldc_w 645
      //   2164: aload_0
      //   2165: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   2168: invokestatic 150	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   2171: invokestatic 649	com/appodeal/ads/Appodeal:getUserSettings	(Landroid/content/Context;)Lcom/appodeal/ads/UserSettings;
      //   2174: invokevirtual 651	com/appodeal/ads/UserSettings:a	()Lorg/json/JSONObject;
      //   2177: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2180: pop
      //   2181: aload 10
      //   2183: astore 5
      //   2185: aload 11
      //   2187: astore_1
      //   2188: aload 12
      //   2190: invokeinterface 311 1 0
      //   2195: astore 4
      //   2197: aload 10
      //   2199: astore 5
      //   2201: aload 11
      //   2203: astore_1
      //   2204: aload 4
      //   2206: ldc -41
      //   2208: invokestatic 244	java/lang/System:currentTimeMillis	()J
      //   2211: invokeinterface 655 4 0
      //   2216: pop
      //   2217: aload 10
      //   2219: astore 5
      //   2221: aload 11
      //   2223: astore_1
      //   2224: aload 4
      //   2226: ldc -10
      //   2228: iconst_0
      //   2229: invokeinterface 659 3 0
      //   2234: pop
      //   2235: aload 10
      //   2237: astore 5
      //   2239: aload 11
      //   2241: astore_1
      //   2242: aload 4
      //   2244: invokeinterface 320 1 0
      //   2249: aload 10
      //   2251: astore 5
      //   2253: aload 11
      //   2255: astore_1
      //   2256: iconst_0
      //   2257: putstatic 234	com/appodeal/ads/UserSettings:sendingInProgress	Z
      //   2260: aload 10
      //   2262: astore 5
      //   2264: aload 11
      //   2266: astore_1
      //   2267: aload_0
      //   2268: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   2271: invokestatic 662	com/appodeal/ads/p:i	(Lcom/appodeal/ads/p;)Lcom/appodeal/ads/d/h;
      //   2274: ifnull +55 -> 2329
      //   2277: aload 10
      //   2279: astore 5
      //   2281: aload 11
      //   2283: astore_1
      //   2284: aload_0
      //   2285: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   2288: invokestatic 274	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   2291: ldc_w 664
      //   2294: invokevirtual 97	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2297: ifeq +1740 -> 4037
      //   2300: aload 10
      //   2302: astore 5
      //   2304: aload 11
      //   2306: astore_1
      //   2307: aload 7
      //   2309: ldc_w 666
      //   2312: aload_0
      //   2313: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   2316: invokestatic 662	com/appodeal/ads/p:i	(Lcom/appodeal/ads/p;)Lcom/appodeal/ads/d/h;
      //   2319: invokevirtual 669	com/appodeal/ads/d/h:a	()Lorg/json/JSONObject;
      //   2322: invokevirtual 670	org/json/JSONObject:toString	()Ljava/lang/String;
      //   2325: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2328: pop
      //   2329: aload 10
      //   2331: astore 5
      //   2333: aload 11
      //   2335: astore_1
      //   2336: aload_0
      //   2337: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   2340: invokestatic 274	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   2343: ldc_w 664
      //   2346: invokevirtual 97	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2349: ifne +2368 -> 4717
      //   2352: aload 10
      //   2354: astore 5
      //   2356: aload 11
      //   2358: astore_1
      //   2359: aload_0
      //   2360: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   2363: invokestatic 274	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   2366: ldc 76
      //   2368: invokevirtual 97	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2371: ifne +2346 -> 4717
      //   2374: aload 10
      //   2376: astore 5
      //   2378: aload 11
      //   2380: astore_1
      //   2381: aload_0
      //   2382: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   2385: invokestatic 274	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   2388: ldc 82
      //   2390: invokevirtual 97	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2393: ifne +2324 -> 4717
      //   2396: aload 10
      //   2398: astore 5
      //   2400: aload 11
      //   2402: astore_1
      //   2403: aload_0
      //   2404: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   2407: invokestatic 274	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   2410: ldc 101
      //   2412: invokevirtual 97	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2415: ifne +2302 -> 4717
      //   2418: aload 10
      //   2420: astore 5
      //   2422: aload 11
      //   2424: astore_1
      //   2425: aload_0
      //   2426: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   2429: invokestatic 274	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   2432: ldc_w 276
      //   2435: invokevirtual 97	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2438: ifne +2279 -> 4717
      //   2441: iconst_1
      //   2442: istore_2
      //   2443: iload_2
      //   2444: ifeq +1813 -> 4257
      //   2447: aload 10
      //   2449: astore 5
      //   2451: aload 11
      //   2453: astore_1
      //   2454: new 672	java/net/URL
      //   2457: dup
      //   2458: ldc_w 674
      //   2461: iconst_4
      //   2462: anewarray 109	java/lang/Object
      //   2465: dup
      //   2466: iconst_0
      //   2467: invokestatic 678	com/appodeal/ads/utils/e:c	()I
      //   2470: invokestatic 681	com/appodeal/ads/utils/e:a	(I)Ljava/lang/String;
      //   2473: aastore
      //   2474: dup
      //   2475: iconst_1
      //   2476: invokestatic 683	com/appodeal/ads/utils/e:b	()Ljava/lang/String;
      //   2479: aastore
      //   2480: dup
      //   2481: iconst_2
      //   2482: invokestatic 678	com/appodeal/ads/utils/e:c	()I
      //   2485: invokestatic 688	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   2488: aastore
      //   2489: dup
      //   2490: iconst_3
      //   2491: ldc_w 690
      //   2494: aastore
      //   2495: invokestatic 113	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   2498: invokespecial 691	java/net/URL:<init>	(Ljava/lang/String;)V
      //   2501: astore 6
      //   2503: aload 10
      //   2505: astore 5
      //   2507: aload 11
      //   2509: astore_1
      //   2510: aload 6
      //   2512: invokevirtual 695	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   2515: astore 4
      //   2517: aload 6
      //   2519: invokevirtual 698	java/net/URL:getProtocol	()Ljava/lang/String;
      //   2522: ldc_w 700
      //   2525: invokevirtual 97	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2528: ifeq +79 -> 2607
      //   2531: new 702	java/util/ArrayList
      //   2534: dup
      //   2535: invokespecial 703	java/util/ArrayList:<init>	()V
      //   2538: astore_1
      //   2539: aload_1
      //   2540: ldc_w 705
      //   2543: invokeinterface 707 2 0
      //   2548: pop
      //   2549: aload_1
      //   2550: ldc_w 709
      //   2553: invokeinterface 707 2 0
      //   2558: pop
      //   2559: new 711	com/appodeal/ads/utils/c
      //   2562: dup
      //   2563: aload_1
      //   2564: ldc2_w 712
      //   2567: invokespecial 716	com/appodeal/ads/utils/c:<init>	(Ljava/util/List;J)V
      //   2570: astore_1
      //   2571: ldc_w 718
      //   2574: invokestatic 723	javax/net/ssl/SSLContext:getInstance	(Ljava/lang/String;)Ljavax/net/ssl/SSLContext;
      //   2577: astore 5
      //   2579: aload 5
      //   2581: aconst_null
      //   2582: iconst_1
      //   2583: anewarray 725	javax/net/ssl/TrustManager
      //   2586: dup
      //   2587: iconst_0
      //   2588: aload_1
      //   2589: aastore
      //   2590: aconst_null
      //   2591: invokevirtual 729	javax/net/ssl/SSLContext:init	([Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Ljava/security/SecureRandom;)V
      //   2594: aload 4
      //   2596: checkcast 262	javax/net/ssl/HttpsURLConnection
      //   2599: aload 5
      //   2601: invokevirtual 733	javax/net/ssl/SSLContext:getSocketFactory	()Ljavax/net/ssl/SSLSocketFactory;
      //   2604: invokevirtual 737	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
      //   2607: aload 4
      //   2609: sipush 20000
      //   2612: invokevirtual 743	java/net/URLConnection:setConnectTimeout	(I)V
      //   2615: aload 4
      //   2617: sipush 20000
      //   2620: invokevirtual 746	java/net/URLConnection:setReadTimeout	(I)V
      //   2623: aload 4
      //   2625: ldc_w 748
      //   2628: ldc_w 750
      //   2631: invokevirtual 754	java/net/URLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   2634: aload 4
      //   2636: iconst_1
      //   2637: invokevirtual 758	java/net/URLConnection:setDoOutput	(Z)V
      //   2640: new 760	java/io/ByteArrayOutputStream
      //   2643: dup
      //   2644: invokespecial 761	java/io/ByteArrayOutputStream:<init>	()V
      //   2647: astore 5
      //   2649: new 763	java/util/zip/GZIPOutputStream
      //   2652: dup
      //   2653: aload 5
      //   2655: invokespecial 766	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   2658: astore_1
      //   2659: aload_1
      //   2660: aload 7
      //   2662: invokevirtual 670	org/json/JSONObject:toString	()Ljava/lang/String;
      //   2665: ldc_w 768
      //   2668: invokevirtual 772	java/lang/String:getBytes	(Ljava/lang/String;)[B
      //   2671: invokevirtual 776	java/util/zip/GZIPOutputStream:write	([B)V
      //   2674: aload_1
      //   2675: invokevirtual 779	java/util/zip/GZIPOutputStream:close	()V
      //   2678: aload 5
      //   2680: invokevirtual 783	java/io/ByteArrayOutputStream:toByteArray	()[B
      //   2683: iconst_0
      //   2684: invokestatic 789	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
      //   2687: astore_1
      //   2688: aload 4
      //   2690: invokevirtual 793	java/net/URLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   2693: aload_1
      //   2694: invokestatic 796	com/appodeal/ads/aj:a	(Ljava/io/OutputStream;Ljava/lang/String;)V
      //   2697: aload 4
      //   2699: invokevirtual 800	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
      //   2702: invokestatic 803	com/appodeal/ads/aj:a	(Ljava/io/InputStream;)Ljava/lang/String;
      //   2705: astore_1
      //   2706: aload_1
      //   2707: ifnull +22 -> 2729
      //   2710: aload_1
      //   2711: invokevirtual 806	java/lang/String:isEmpty	()Z
      //   2714: ifne +15 -> 2729
      //   2717: aload_1
      //   2718: ldc_w 808
      //   2721: invokevirtual 97	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2724: istore_3
      //   2725: iload_3
      //   2726: ifeq +5 -> 2731
      //   2729: aconst_null
      //   2730: astore_1
      //   2731: aload_1
      //   2732: astore 6
      //   2734: aload 4
      //   2736: astore 5
      //   2738: aload 4
      //   2740: astore_1
      //   2741: aload_0
      //   2742: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   2745: invokestatic 150	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   2748: ldc_w 810
      //   2751: iconst_0
      //   2752: invokevirtual 207	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   2755: astore 7
      //   2757: aload 6
      //   2759: ifnonnull +1718 -> 4477
      //   2762: iload_2
      //   2763: ifeq +1672 -> 4435
      //   2766: aload 4
      //   2768: astore 5
      //   2770: aload 4
      //   2772: astore_1
      //   2773: aload 7
      //   2775: aload_0
      //   2776: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   2779: invokestatic 274	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   2782: invokeinterface 814 2 0
      //   2787: ifeq +1648 -> 4435
      //   2790: aload 4
      //   2792: astore 5
      //   2794: aload 4
      //   2796: astore_1
      //   2797: ldc_w 816
      //   2800: invokestatic 325	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   2803: aload 4
      //   2805: astore 5
      //   2807: aload 4
      //   2809: astore_1
      //   2810: aload 7
      //   2812: aload_0
      //   2813: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   2816: invokestatic 274	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   2819: ldc_w 818
      //   2822: invokeinterface 260 3 0
      //   2827: astore 6
      //   2829: aload 4
      //   2831: astore 5
      //   2833: aload 4
      //   2835: astore_1
      //   2836: new 34	org/json/JSONObject
      //   2839: dup
      //   2840: aload 6
      //   2842: invokespecial 577	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   2845: astore 6
      //   2847: aload 4
      //   2849: astore 5
      //   2851: aload 4
      //   2853: astore_1
      //   2854: aload 6
      //   2856: invokestatic 820	com/appodeal/ads/Appodeal:a	(Lorg/json/JSONObject;)V
      //   2859: aload 6
      //   2861: ifnull +35 -> 2896
      //   2864: aload 4
      //   2866: astore_1
      //   2867: aload 6
      //   2869: ldc_w 822
      //   2872: invokevirtual 826	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   2875: putstatic 830	com/appodeal/ads/UserSettings:userData	Lorg/json/JSONObject;
      //   2878: aload 4
      //   2880: astore_1
      //   2881: getstatic 183	com/appodeal/ads/f/f:a	Ljava/lang/Long;
      //   2884: ifnonnull +12 -> 2896
      //   2887: aload 4
      //   2889: astore_1
      //   2890: aload_0
      //   2891: aload 6
      //   2893: invokespecial 832	com/appodeal/ads/p$b:b	(Lorg/json/JSONObject;)V
      //   2896: aload 4
      //   2898: ifnull +19 -> 2917
      //   2901: aload 4
      //   2903: instanceof 262
      //   2906: ifeq +1705 -> 4611
      //   2909: aload 4
      //   2911: checkcast 262	javax/net/ssl/HttpsURLConnection
      //   2914: invokevirtual 265	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   2917: aload 6
      //   2919: areturn
      //   2920: ldc_w 834
      //   2923: astore 4
      //   2925: goto -2487 -> 438
      //   2928: astore 4
      //   2930: aload 10
      //   2932: astore 5
      //   2934: aload 11
      //   2936: astore_1
      //   2937: aload 4
      //   2939: invokestatic 130	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2942: aload 7
      //   2944: astore_1
      //   2945: aload 6
      //   2947: astore 4
      //   2949: goto -2393 -> 556
      //   2952: astore 14
      //   2954: aload 10
      //   2956: astore 5
      //   2958: aload 11
      //   2960: astore_1
      //   2961: aload 14
      //   2963: invokestatic 130	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2966: goto -1935 -> 1031
      //   2969: astore 4
      //   2971: aload 5
      //   2973: astore_1
      //   2974: aload 4
      //   2976: invokestatic 130	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2979: aload 5
      //   2981: ifnull +19 -> 3000
      //   2984: aload 5
      //   2986: instanceof 262
      //   2989: ifeq +1641 -> 4630
      //   2992: aload 5
      //   2994: checkcast 262	javax/net/ssl/HttpsURLConnection
      //   2997: invokevirtual 265	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   3000: aconst_null
      //   3001: areturn
      //   3002: astore 14
      //   3004: aload 10
      //   3006: astore 5
      //   3008: aload 11
      //   3010: astore_1
      //   3011: aload 14
      //   3013: invokestatic 130	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   3016: goto -1954 -> 1062
      //   3019: astore 4
      //   3021: aload_1
      //   3022: ifnull +17 -> 3039
      //   3025: aload_1
      //   3026: instanceof 262
      //   3029: ifeq +1620 -> 4649
      //   3032: aload_1
      //   3033: checkcast 262	javax/net/ssl/HttpsURLConnection
      //   3036: invokevirtual 265	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   3039: aload 4
      //   3041: athrow
      //   3042: aload 10
      //   3044: astore 5
      //   3046: aload 11
      //   3048: astore_1
      //   3049: aload 7
      //   3051: ldc_w 452
      //   3054: ldc_w 836
      //   3057: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3060: pop
      //   3061: goto -1796 -> 1265
      //   3064: astore 4
      //   3066: aload 10
      //   3068: astore 5
      //   3070: aload 11
      //   3072: astore_1
      //   3073: aload 4
      //   3075: invokestatic 130	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   3078: goto -1731 -> 1347
      //   3081: aload 10
      //   3083: astore 5
      //   3085: aload 11
      //   3087: astore_1
      //   3088: aload_0
      //   3089: getfield 84	com/appodeal/ads/p$b:e	Z
      //   3092: ifeq +99 -> 3191
      //   3095: aload 10
      //   3097: astore 5
      //   3099: aload 11
      //   3101: astore_1
      //   3102: aload_0
      //   3103: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3106: invokestatic 150	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   3109: invokestatic 837	com/appodeal/ads/ad:a	(Landroid/content/Context;)Ljava/util/List;
      //   3112: invokeinterface 587 1 0
      //   3117: astore 6
      //   3119: aload 10
      //   3121: astore 5
      //   3123: aload 11
      //   3125: astore_1
      //   3126: aload 6
      //   3128: invokeinterface 592 1 0
      //   3133: ifeq +58 -> 3191
      //   3136: aload 10
      //   3138: astore 5
      //   3140: aload 11
      //   3142: astore_1
      //   3143: aload 6
      //   3145: invokeinterface 596 1 0
      //   3150: checkcast 839	com/appodeal/ads/al
      //   3153: astore 8
      //   3155: aload 10
      //   3157: astore 5
      //   3159: aload 11
      //   3161: astore_1
      //   3162: aload 8
      //   3164: invokevirtual 842	com/appodeal/ads/al:h	()Lcom/appodeal/ads/am;
      //   3167: ifnull -48 -> 3119
      //   3170: aload 10
      //   3172: astore 5
      //   3174: aload 11
      //   3176: astore_1
      //   3177: aload 4
      //   3179: aload 8
      //   3181: invokevirtual 843	com/appodeal/ads/al:a	()Ljava/lang/String;
      //   3184: invokevirtual 606	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   3187: pop
      //   3188: goto -69 -> 3119
      //   3191: aload 10
      //   3193: astore 5
      //   3195: aload 11
      //   3197: astore_1
      //   3198: aload_0
      //   3199: getfield 86	com/appodeal/ads/p$b:f	Z
      //   3202: ifeq +99 -> 3301
      //   3205: aload 10
      //   3207: astore 5
      //   3209: aload 11
      //   3211: astore_1
      //   3212: aload_0
      //   3213: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3216: invokestatic 150	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   3219: invokestatic 844	com/appodeal/ads/ag:a	(Landroid/content/Context;)Ljava/util/List;
      //   3222: invokeinterface 587 1 0
      //   3227: astore 6
      //   3229: aload 10
      //   3231: astore 5
      //   3233: aload 11
      //   3235: astore_1
      //   3236: aload 6
      //   3238: invokeinterface 592 1 0
      //   3243: ifeq +58 -> 3301
      //   3246: aload 10
      //   3248: astore 5
      //   3250: aload 11
      //   3252: astore_1
      //   3253: aload 6
      //   3255: invokeinterface 596 1 0
      //   3260: checkcast 839	com/appodeal/ads/al
      //   3263: astore 8
      //   3265: aload 10
      //   3267: astore 5
      //   3269: aload 11
      //   3271: astore_1
      //   3272: aload 8
      //   3274: invokevirtual 842	com/appodeal/ads/al:h	()Lcom/appodeal/ads/am;
      //   3277: ifnull -48 -> 3229
      //   3280: aload 10
      //   3282: astore 5
      //   3284: aload 11
      //   3286: astore_1
      //   3287: aload 4
      //   3289: aload 8
      //   3291: invokevirtual 843	com/appodeal/ads/al:a	()Ljava/lang/String;
      //   3294: invokevirtual 606	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   3297: pop
      //   3298: goto -69 -> 3229
      //   3301: aload 10
      //   3303: astore 5
      //   3305: aload 11
      //   3307: astore_1
      //   3308: aload_0
      //   3309: getfield 115	com/appodeal/ads/p$b:c	Z
      //   3312: ifeq +99 -> 3411
      //   3315: aload 10
      //   3317: astore 5
      //   3319: aload 11
      //   3321: astore_1
      //   3322: aload_0
      //   3323: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3326: invokestatic 150	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   3329: invokestatic 845	com/appodeal/ads/c:a	(Landroid/content/Context;)Ljava/util/List;
      //   3332: invokeinterface 587 1 0
      //   3337: astore 6
      //   3339: aload 10
      //   3341: astore 5
      //   3343: aload 11
      //   3345: astore_1
      //   3346: aload 6
      //   3348: invokeinterface 592 1 0
      //   3353: ifeq +58 -> 3411
      //   3356: aload 10
      //   3358: astore 5
      //   3360: aload 11
      //   3362: astore_1
      //   3363: aload 6
      //   3365: invokeinterface 596 1 0
      //   3370: checkcast 847	com/appodeal/ads/d
      //   3373: astore 8
      //   3375: aload 10
      //   3377: astore 5
      //   3379: aload 11
      //   3381: astore_1
      //   3382: aload 8
      //   3384: invokevirtual 850	com/appodeal/ads/d:f	()Lcom/appodeal/ads/g;
      //   3387: ifnull -48 -> 3339
      //   3390: aload 10
      //   3392: astore 5
      //   3394: aload 11
      //   3396: astore_1
      //   3397: aload 4
      //   3399: aload 8
      //   3401: invokevirtual 851	com/appodeal/ads/d:a	()Ljava/lang/String;
      //   3404: invokevirtual 606	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   3407: pop
      //   3408: goto -69 -> 3339
      //   3411: aload 10
      //   3413: astore 5
      //   3415: aload 11
      //   3417: astore_1
      //   3418: aload_0
      //   3419: getfield 119	com/appodeal/ads/p$b:d	Z
      //   3422: ifeq +99 -> 3521
      //   3425: aload 10
      //   3427: astore 5
      //   3429: aload 11
      //   3431: astore_1
      //   3432: aload_0
      //   3433: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3436: invokestatic 150	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   3439: invokestatic 852	com/appodeal/ads/q:a	(Landroid/content/Context;)Ljava/util/List;
      //   3442: invokeinterface 587 1 0
      //   3447: astore 6
      //   3449: aload 10
      //   3451: astore 5
      //   3453: aload 11
      //   3455: astore_1
      //   3456: aload 6
      //   3458: invokeinterface 592 1 0
      //   3463: ifeq +58 -> 3521
      //   3466: aload 10
      //   3468: astore 5
      //   3470: aload 11
      //   3472: astore_1
      //   3473: aload 6
      //   3475: invokeinterface 596 1 0
      //   3480: checkcast 854	com/appodeal/ads/r
      //   3483: astore 8
      //   3485: aload 10
      //   3487: astore 5
      //   3489: aload 11
      //   3491: astore_1
      //   3492: aload 8
      //   3494: invokevirtual 857	com/appodeal/ads/r:f	()Lcom/appodeal/ads/u;
      //   3497: ifnull -48 -> 3449
      //   3500: aload 10
      //   3502: astore 5
      //   3504: aload 11
      //   3506: astore_1
      //   3507: aload 4
      //   3509: aload 8
      //   3511: invokevirtual 858	com/appodeal/ads/r:a	()Ljava/lang/String;
      //   3514: invokevirtual 606	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   3517: pop
      //   3518: goto -69 -> 3449
      //   3521: aload 10
      //   3523: astore 5
      //   3525: aload 11
      //   3527: astore_1
      //   3528: aload_0
      //   3529: getfield 123	com/appodeal/ads/p$b:g	Z
      //   3532: ifeq +89 -> 3621
      //   3535: aload 10
      //   3537: astore 5
      //   3539: aload 11
      //   3541: astore_1
      //   3542: aload_0
      //   3543: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3546: invokestatic 150	com/appodeal/ads/p:b	(Lcom/appodeal/ads/p;)Landroid/content/Context;
      //   3549: invokestatic 859	com/appodeal/ads/w:a	(Landroid/content/Context;)Ljava/util/List;
      //   3552: invokeinterface 587 1 0
      //   3557: astore 6
      //   3559: aload 10
      //   3561: astore 5
      //   3563: aload 11
      //   3565: astore_1
      //   3566: aload 6
      //   3568: invokeinterface 592 1 0
      //   3573: ifeq +48 -> 3621
      //   3576: aload 10
      //   3578: astore 5
      //   3580: aload 11
      //   3582: astore_1
      //   3583: aload 6
      //   3585: invokeinterface 596 1 0
      //   3590: checkcast 861	com/appodeal/ads/y
      //   3593: astore 8
      //   3595: aload 8
      //   3597: ifnull -38 -> 3559
      //   3600: aload 10
      //   3602: astore 5
      //   3604: aload 11
      //   3606: astore_1
      //   3607: aload 4
      //   3609: aload 8
      //   3611: invokevirtual 862	com/appodeal/ads/y:a	()Ljava/lang/String;
      //   3614: invokevirtual 606	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   3617: pop
      //   3618: goto -59 -> 3559
      //   3621: aload 10
      //   3623: astore 5
      //   3625: aload 11
      //   3627: astore_1
      //   3628: aload 7
      //   3630: ldc_w 864
      //   3633: aload 4
      //   3635: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3638: pop
      //   3639: aload 10
      //   3641: astore 5
      //   3643: aload 11
      //   3645: astore_1
      //   3646: aload_0
      //   3647: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3650: invokestatic 866	com/appodeal/ads/p:c	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   3653: ifnull +26 -> 3679
      //   3656: aload 10
      //   3658: astore 5
      //   3660: aload 11
      //   3662: astore_1
      //   3663: aload 7
      //   3665: ldc_w 868
      //   3668: aload_0
      //   3669: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3672: invokestatic 866	com/appodeal/ads/p:c	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   3675: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3678: pop
      //   3679: aload 10
      //   3681: astore 5
      //   3683: aload 11
      //   3685: astore_1
      //   3686: getstatic 183	com/appodeal/ads/f/f:a	Ljava/lang/Long;
      //   3689: ifnull +22 -> 3711
      //   3692: aload 10
      //   3694: astore 5
      //   3696: aload 11
      //   3698: astore_1
      //   3699: aload 7
      //   3701: ldc_w 870
      //   3704: getstatic 183	com/appodeal/ads/f/f:a	Ljava/lang/Long;
      //   3707: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3710: pop
      //   3711: aload 10
      //   3713: astore 5
      //   3715: aload 11
      //   3717: astore_1
      //   3718: aload_0
      //   3719: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3722: invokestatic 873	com/appodeal/ads/p:d	(Lcom/appodeal/ads/p;)J
      //   3725: lconst_0
      //   3726: lcmp
      //   3727: ifeq +26 -> 3753
      //   3730: aload 10
      //   3732: astore 5
      //   3734: aload 11
      //   3736: astore_1
      //   3737: aload 7
      //   3739: ldc_w 875
      //   3742: aload_0
      //   3743: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3746: invokestatic 873	com/appodeal/ads/p:d	(Lcom/appodeal/ads/p;)J
      //   3749: invokevirtual 546	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   3752: pop
      //   3753: aload 10
      //   3755: astore 5
      //   3757: aload 11
      //   3759: astore_1
      //   3760: aload_0
      //   3761: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3764: invokestatic 274	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   3767: ldc 82
      //   3769: invokevirtual 97	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3772: ifeq +26 -> 3798
      //   3775: aload 10
      //   3777: astore 5
      //   3779: aload 11
      //   3781: astore_1
      //   3782: aload 7
      //   3784: ldc_w 877
      //   3787: invokestatic 244	java/lang/System:currentTimeMillis	()J
      //   3790: ldc2_w 542
      //   3793: ldiv
      //   3794: invokevirtual 546	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   3797: pop
      //   3798: aload 10
      //   3800: astore 5
      //   3802: aload 11
      //   3804: astore_1
      //   3805: aload_0
      //   3806: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3809: invokestatic 274	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   3812: ldc 101
      //   3814: invokevirtual 97	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3817: ifeq +26 -> 3843
      //   3820: aload 10
      //   3822: astore 5
      //   3824: aload 11
      //   3826: astore_1
      //   3827: aload 7
      //   3829: ldc_w 879
      //   3832: invokestatic 244	java/lang/System:currentTimeMillis	()J
      //   3835: ldc2_w 542
      //   3838: ldiv
      //   3839: invokevirtual 546	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   3842: pop
      //   3843: aload 10
      //   3845: astore 5
      //   3847: aload 11
      //   3849: astore_1
      //   3850: aload 7
      //   3852: ldc_w 666
      //   3855: aload_0
      //   3856: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3859: invokestatic 881	com/appodeal/ads/p:e	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   3862: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3865: pop
      //   3866: aload 10
      //   3868: astore 5
      //   3870: aload 11
      //   3872: astore_1
      //   3873: aload 7
      //   3875: ldc_w 883
      //   3878: aload_0
      //   3879: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3882: invokestatic 885	com/appodeal/ads/p:f	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   3885: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3888: pop
      //   3889: aload 10
      //   3891: astore 5
      //   3893: aload 11
      //   3895: astore_1
      //   3896: aload_0
      //   3897: getfield 236	com/appodeal/ads/p$b:i	Z
      //   3900: ifne +20 -> 3920
      //   3903: aload 10
      //   3905: astore 5
      //   3907: aload 11
      //   3909: astore_1
      //   3910: aload_0
      //   3911: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3914: invokestatic 89	com/appodeal/ads/p:g	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   3917: ifnull +23 -> 3940
      //   3920: aload 10
      //   3922: astore 5
      //   3924: aload 11
      //   3926: astore_1
      //   3927: aload 7
      //   3929: ldc_w 887
      //   3932: aload_0
      //   3933: invokespecial 888	com/appodeal/ads/p$b:a	()Lorg/json/JSONObject;
      //   3936: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3939: pop
      //   3940: aload 10
      //   3942: astore 5
      //   3944: aload 11
      //   3946: astore_1
      //   3947: aload_0
      //   3948: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3951: invokestatic 891	com/appodeal/ads/p:h	(Lcom/appodeal/ads/p;)Lcom/appodeal/ads/f/c;
      //   3954: ifnull -1981 -> 1973
      //   3957: aload 10
      //   3959: astore 5
      //   3961: aload 11
      //   3963: astore_1
      //   3964: aload 7
      //   3966: ldc_w 893
      //   3969: aload_0
      //   3970: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   3973: invokestatic 891	com/appodeal/ads/p:h	(Lcom/appodeal/ads/p;)Lcom/appodeal/ads/f/c;
      //   3976: invokevirtual 897	com/appodeal/ads/f/c:a	()I
      //   3979: invokevirtual 80	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
      //   3982: pop
      //   3983: goto -2010 -> 1973
      //   3986: aload 11
      //   3988: astore_1
      //   3989: aload 7
      //   3991: ldc_w 899
      //   3994: aload 4
      //   3996: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3999: pop
      //   4000: goto -1888 -> 2112
      //   4003: astore 4
      //   4005: aload 10
      //   4007: astore 5
      //   4009: aload 11
      //   4011: astore_1
      //   4012: aload 4
      //   4014: invokestatic 130	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   4017: goto -1861 -> 2156
      //   4020: astore 4
      //   4022: aload 10
      //   4024: astore 5
      //   4026: aload 11
      //   4028: astore_1
      //   4029: aload 4
      //   4031: invokestatic 130	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   4034: goto -1853 -> 2181
      //   4037: aload 10
      //   4039: astore 5
      //   4041: aload 11
      //   4043: astore_1
      //   4044: aload_0
      //   4045: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   4048: invokestatic 274	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   4051: ldc 76
      //   4053: invokevirtual 97	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   4056: ifeq +128 -> 4184
      //   4059: aload 10
      //   4061: astore 5
      //   4063: aload 11
      //   4065: astore_1
      //   4066: aload_0
      //   4067: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   4070: invokestatic 662	com/appodeal/ads/p:i	(Lcom/appodeal/ads/p;)Lcom/appodeal/ads/d/h;
      //   4073: invokevirtual 901	com/appodeal/ads/d/h:f	()Z
      //   4076: ifne +53 -> 4129
      //   4079: aload 10
      //   4081: astore 5
      //   4083: aload 11
      //   4085: astore_1
      //   4086: ldc_w 903
      //   4089: invokestatic 325	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   4092: iconst_0
      //   4093: ifeq +17 -> 4110
      //   4096: aconst_null
      //   4097: instanceof 262
      //   4100: ifeq +12 -> 4112
      //   4103: aconst_null
      //   4104: checkcast 262	javax/net/ssl/HttpsURLConnection
      //   4107: invokevirtual 265	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   4110: aconst_null
      //   4111: areturn
      //   4112: aconst_null
      //   4113: instanceof 267
      //   4116: ifeq -6 -> 4110
      //   4119: aconst_null
      //   4120: checkcast 267	java/net/HttpURLConnection
      //   4123: invokevirtual 268	java/net/HttpURLConnection:disconnect	()V
      //   4126: goto -16 -> 4110
      //   4129: aload 10
      //   4131: astore 5
      //   4133: aload 11
      //   4135: astore_1
      //   4136: aload 7
      //   4138: ldc_w 905
      //   4141: aload_0
      //   4142: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   4145: invokestatic 662	com/appodeal/ads/p:i	(Lcom/appodeal/ads/p;)Lcom/appodeal/ads/d/h;
      //   4148: invokevirtual 907	com/appodeal/ads/d/h:e	()Lorg/json/JSONObject;
      //   4151: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   4154: pop
      //   4155: aload 10
      //   4157: astore 5
      //   4159: aload 11
      //   4161: astore_1
      //   4162: aload 7
      //   4164: ldc_w 909
      //   4167: aload_0
      //   4168: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   4171: invokestatic 662	com/appodeal/ads/p:i	(Lcom/appodeal/ads/p;)Lcom/appodeal/ads/d/h;
      //   4174: invokevirtual 911	com/appodeal/ads/d/h:g	()Ljava/lang/String;
      //   4177: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   4180: pop
      //   4181: goto -1852 -> 2329
      //   4184: aload 10
      //   4186: astore 5
      //   4188: aload 11
      //   4190: astore_1
      //   4191: aload_0
      //   4192: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   4195: invokestatic 274	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   4198: ldc 82
      //   4200: invokevirtual 97	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   4203: ifne +25 -> 4228
      //   4206: aload 10
      //   4208: astore 5
      //   4210: aload 11
      //   4212: astore_1
      //   4213: aload_0
      //   4214: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   4217: invokestatic 274	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   4220: ldc 101
      //   4222: invokevirtual 97	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   4225: ifeq -1896 -> 2329
      //   4228: aload 10
      //   4230: astore 5
      //   4232: aload 11
      //   4234: astore_1
      //   4235: aload 7
      //   4237: ldc_w 666
      //   4240: aload_0
      //   4241: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   4244: invokestatic 662	com/appodeal/ads/p:i	(Lcom/appodeal/ads/p;)Lcom/appodeal/ads/d/h;
      //   4247: invokevirtual 911	com/appodeal/ads/d/h:g	()Ljava/lang/String;
      //   4250: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   4253: pop
      //   4254: goto -1925 -> 2329
      //   4257: aload 10
      //   4259: astore 5
      //   4261: aload 11
      //   4263: astore_1
      //   4264: new 672	java/net/URL
      //   4267: dup
      //   4268: ldc_w 674
      //   4271: iconst_4
      //   4272: anewarray 109	java/lang/Object
      //   4275: dup
      //   4276: iconst_0
      //   4277: invokestatic 678	com/appodeal/ads/utils/e:c	()I
      //   4280: invokestatic 681	com/appodeal/ads/utils/e:a	(I)Ljava/lang/String;
      //   4283: aastore
      //   4284: dup
      //   4285: iconst_1
      //   4286: invokestatic 683	com/appodeal/ads/utils/e:b	()Ljava/lang/String;
      //   4289: aastore
      //   4290: dup
      //   4291: iconst_2
      //   4292: invokestatic 678	com/appodeal/ads/utils/e:c	()I
      //   4295: invokestatic 688	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   4298: aastore
      //   4299: dup
      //   4300: iconst_3
      //   4301: aload_0
      //   4302: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   4305: invokestatic 274	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   4308: aastore
      //   4309: invokestatic 113	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   4312: invokespecial 691	java/net/URL:<init>	(Ljava/lang/String;)V
      //   4315: astore 6
      //   4317: goto -1814 -> 2503
      //   4320: astore 5
      //   4322: aload_1
      //   4323: invokevirtual 779	java/util/zip/GZIPOutputStream:close	()V
      //   4326: aload 5
      //   4328: athrow
      //   4329: astore 6
      //   4331: aload 4
      //   4333: astore 5
      //   4335: aload 4
      //   4337: astore_1
      //   4338: aload 6
      //   4340: invokestatic 130	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   4343: aload 4
      //   4345: astore 5
      //   4347: aload 4
      //   4349: astore_1
      //   4350: aload 6
      //   4352: invokevirtual 914	java/io/IOException:getMessage	()Ljava/lang/String;
      //   4355: ldc_w 916
      //   4358: invokevirtual 97	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   4361: ifne +26 -> 4387
      //   4364: aload 4
      //   4366: astore 5
      //   4368: aload 4
      //   4370: astore_1
      //   4371: aload 6
      //   4373: invokevirtual 914	java/io/IOException:getMessage	()Ljava/lang/String;
      //   4376: ldc_w 918
      //   4379: invokevirtual 97	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   4382: istore_3
      //   4383: iload_3
      //   4384: ifeq +45 -> 4429
      //   4387: aload 4
      //   4389: ifnull +19 -> 4408
      //   4392: aload 4
      //   4394: instanceof 262
      //   4397: ifeq +13 -> 4410
      //   4400: aload 4
      //   4402: checkcast 262	javax/net/ssl/HttpsURLConnection
      //   4405: invokevirtual 265	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   4408: aconst_null
      //   4409: areturn
      //   4410: aload 4
      //   4412: instanceof 267
      //   4415: ifeq -7 -> 4408
      //   4418: aload 4
      //   4420: checkcast 267	java/net/HttpURLConnection
      //   4423: invokevirtual 268	java/net/HttpURLConnection:disconnect	()V
      //   4426: goto -18 -> 4408
      //   4429: aconst_null
      //   4430: astore 6
      //   4432: goto -1698 -> 2734
      //   4435: aload 4
      //   4437: ifnull +19 -> 4456
      //   4440: aload 4
      //   4442: instanceof 262
      //   4445: ifeq +13 -> 4458
      //   4448: aload 4
      //   4450: checkcast 262	javax/net/ssl/HttpsURLConnection
      //   4453: invokevirtual 265	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   4456: aconst_null
      //   4457: areturn
      //   4458: aload 4
      //   4460: instanceof 267
      //   4463: ifeq -7 -> 4456
      //   4466: aload 4
      //   4468: checkcast 267	java/net/HttpURLConnection
      //   4471: invokevirtual 268	java/net/HttpURLConnection:disconnect	()V
      //   4474: goto -18 -> 4456
      //   4477: iload_2
      //   4478: ifeq +244 -> 4722
      //   4481: aload 4
      //   4483: astore 5
      //   4485: aload 4
      //   4487: astore_1
      //   4488: aload 7
      //   4490: invokeinterface 311 1 0
      //   4495: astore 7
      //   4497: aload 4
      //   4499: astore 5
      //   4501: aload 4
      //   4503: astore_1
      //   4504: aload 7
      //   4506: aload_0
      //   4507: getfield 22	com/appodeal/ads/p$b:a	Lcom/appodeal/ads/p;
      //   4510: invokestatic 274	com/appodeal/ads/p:a	(Lcom/appodeal/ads/p;)Ljava/lang/String;
      //   4513: aload 6
      //   4515: invokeinterface 317 3 0
      //   4520: pop
      //   4521: aload 4
      //   4523: astore 5
      //   4525: aload 4
      //   4527: astore_1
      //   4528: aload 7
      //   4530: invokeinterface 320 1 0
      //   4535: goto +187 -> 4722
      //   4538: astore 6
      //   4540: aload 4
      //   4542: astore 5
      //   4544: aload 4
      //   4546: astore_1
      //   4547: aload 6
      //   4549: invokestatic 130	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   4552: aload 4
      //   4554: ifnull +19 -> 4573
      //   4557: aload 4
      //   4559: instanceof 262
      //   4562: ifeq +13 -> 4575
      //   4565: aload 4
      //   4567: checkcast 262	javax/net/ssl/HttpsURLConnection
      //   4570: invokevirtual 265	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   4573: aconst_null
      //   4574: areturn
      //   4575: aload 4
      //   4577: instanceof 267
      //   4580: ifeq -7 -> 4573
      //   4583: aload 4
      //   4585: checkcast 267	java/net/HttpURLConnection
      //   4588: invokevirtual 268	java/net/HttpURLConnection:disconnect	()V
      //   4591: goto -18 -> 4573
      //   4594: astore 7
      //   4596: aload 4
      //   4598: astore 5
      //   4600: aload 4
      //   4602: astore_1
      //   4603: aload 7
      //   4605: invokestatic 130	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   4608: goto -1712 -> 2896
      //   4611: aload 4
      //   4613: instanceof 267
      //   4616: ifeq -1699 -> 2917
      //   4619: aload 4
      //   4621: checkcast 267	java/net/HttpURLConnection
      //   4624: invokevirtual 268	java/net/HttpURLConnection:disconnect	()V
      //   4627: goto -1710 -> 2917
      //   4630: aload 5
      //   4632: instanceof 267
      //   4635: ifeq -1635 -> 3000
      //   4638: aload 5
      //   4640: checkcast 267	java/net/HttpURLConnection
      //   4643: invokevirtual 268	java/net/HttpURLConnection:disconnect	()V
      //   4646: goto -1646 -> 3000
      //   4649: aload_1
      //   4650: instanceof 267
      //   4653: ifeq -1614 -> 3039
      //   4656: aload_1
      //   4657: checkcast 267	java/net/HttpURLConnection
      //   4660: invokevirtual 268	java/net/HttpURLConnection:disconnect	()V
      //   4663: goto -1624 -> 3039
      //   4666: astore 5
      //   4668: aload 4
      //   4670: astore_1
      //   4671: aload 5
      //   4673: astore 4
      //   4675: goto -1654 -> 3021
      //   4678: astore_1
      //   4679: aload 4
      //   4681: astore 5
      //   4683: aload_1
      //   4684: astore 4
      //   4686: goto -1715 -> 2971
      //   4689: astore 6
      //   4691: aload 9
      //   4693: astore 4
      //   4695: goto -364 -> 4331
      //   4698: aload_1
      //   4699: astore 6
      //   4701: goto -4122 -> 579
      //   4704: iconst_0
      //   4705: istore_2
      //   4706: goto -4548 -> 158
      //   4709: ldc_w 920
      //   4712: astore 4
      //   4714: goto -3425 -> 1289
      //   4717: iconst_0
      //   4718: istore_2
      //   4719: goto -2276 -> 2443
      //   4722: goto -1893 -> 2829
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	4725	0	this	b
      //   0	4725	1	paramVarArgs	Void[]
      //   157	4562	2	j	int
      //   312	4072	3	bool	boolean
      //   41	1905	4	localObject1	Object
      //   1959	10	4	localJSONException1	JSONException
      //   1987	101	4	localJSONArray	JSONArray
      //   2098	10	4	localException1	Exception
      //   2195	729	4	localObject2	Object
      //   2928	10	4	localException2	Exception
      //   2947	1	4	localObject3	Object
      //   2969	6	4	localException3	Exception
      //   3019	21	4	localObject4	Object
      //   3064	931	4	localException4	Exception
      //   4003	10	4	localException5	Exception
      //   4020	649	4	localException6	Exception
      //   4673	40	4	localObject5	Object
      //   11	4249	5	localObject6	Object
      //   4320	7	5	localObject7	Object
      //   4333	306	5	localObject8	Object
      //   4666	6	5	localObject9	Object
      //   4681	1	5	localObject10	Object
      //   281	4035	6	localObject11	Object
      //   4329	43	6	localIOException1	java.io.IOException
      //   4430	84	6	str	String
      //   4538	10	6	localJSONException2	JSONException
      //   4689	1	6	localIOException2	java.io.IOException
      //   4699	1	6	arrayOfVoid	Void[]
      //   285	4244	7	localObject12	Object
      //   4594	10	7	localException7	Exception
      //   257	3353	8	localObject13	Object
      //   4	4688	9	localObject14	Object
      //   7	4251	10	localObject15	Object
      //   1	4261	11	localObject16	Object
      //   29	2160	12	localSharedPreferences	android.content.SharedPreferences
      //   175	1818	13	localPackageManager	android.content.pm.PackageManager
      //   195	828	14	localObject17	Object
      //   2952	10	14	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
      //   3002	10	14	localException8	Exception
      //   394	32	15	localInfo	com.google.android.gms.ads.identifier.AdvertisingIdClient.Info
      // Exception table:
      //   from	to	target	type
      //   602	613	1959	org/json/JSONException
      //   620	627	1959	org/json/JSONException
      //   634	645	1959	org/json/JSONException
      //   652	659	1959	org/json/JSONException
      //   666	677	1959	org/json/JSONException
      //   684	691	1959	org/json/JSONException
      //   698	709	1959	org/json/JSONException
      //   716	723	1959	org/json/JSONException
      //   730	737	1959	org/json/JSONException
      //   744	755	1959	org/json/JSONException
      //   762	769	1959	org/json/JSONException
      //   776	785	1959	org/json/JSONException
      //   792	799	1959	org/json/JSONException
      //   806	817	1959	org/json/JSONException
      //   824	831	1959	org/json/JSONException
      //   838	848	1959	org/json/JSONException
      //   855	861	1959	org/json/JSONException
      //   868	878	1959	org/json/JSONException
      //   885	897	1959	org/json/JSONException
      //   904	916	1959	org/json/JSONException
      //   923	935	1959	org/json/JSONException
      //   942	954	1959	org/json/JSONException
      //   961	972	1959	org/json/JSONException
      //   979	989	1959	org/json/JSONException
      //   996	1010	1959	org/json/JSONException
      //   1017	1031	1959	org/json/JSONException
      //   1034	1062	1959	org/json/JSONException
      //   1069	1080	1959	org/json/JSONException
      //   1087	1098	1959	org/json/JSONException
      //   1105	1117	1959	org/json/JSONException
      //   1124	1138	1959	org/json/JSONException
      //   1145	1165	1959	org/json/JSONException
      //   1172	1184	1959	org/json/JSONException
      //   1191	1205	1959	org/json/JSONException
      //   1212	1226	1959	org/json/JSONException
      //   1233	1246	1959	org/json/JSONException
      //   1253	1265	1959	org/json/JSONException
      //   1272	1284	1959	org/json/JSONException
      //   1296	1307	1959	org/json/JSONException
      //   1310	1319	1959	org/json/JSONException
      //   1336	1347	1959	org/json/JSONException
      //   1354	1385	1959	org/json/JSONException
      //   1392	1404	1959	org/json/JSONException
      //   1411	1425	1959	org/json/JSONException
      //   1432	1452	1959	org/json/JSONException
      //   1459	1479	1959	org/json/JSONException
      //   1486	1500	1959	org/json/JSONException
      //   1507	1521	1959	org/json/JSONException
      //   1528	1547	1959	org/json/JSONException
      //   1554	1569	1959	org/json/JSONException
      //   1576	1590	1959	org/json/JSONException
      //   1597	1627	1959	org/json/JSONException
      //   1634	1650	1959	org/json/JSONException
      //   1657	1672	1959	org/json/JSONException
      //   1679	1691	1959	org/json/JSONException
      //   1698	1708	1959	org/json/JSONException
      //   1715	1729	1959	org/json/JSONException
      //   1736	1748	1959	org/json/JSONException
      //   1755	1769	1959	org/json/JSONException
      //   1776	1789	1959	org/json/JSONException
      //   1801	1819	1959	org/json/JSONException
      //   1826	1833	1959	org/json/JSONException
      //   1840	1849	1959	org/json/JSONException
      //   1856	1863	1959	org/json/JSONException
      //   1870	1887	1959	org/json/JSONException
      //   1894	1904	1959	org/json/JSONException
      //   1911	1923	1959	org/json/JSONException
      //   1930	1938	1959	org/json/JSONException
      //   1945	1956	1959	org/json/JSONException
      //   2961	2966	1959	org/json/JSONException
      //   3011	3016	1959	org/json/JSONException
      //   3049	3061	1959	org/json/JSONException
      //   3073	3078	1959	org/json/JSONException
      //   3088	3095	1959	org/json/JSONException
      //   3102	3119	1959	org/json/JSONException
      //   3126	3136	1959	org/json/JSONException
      //   3143	3155	1959	org/json/JSONException
      //   3162	3170	1959	org/json/JSONException
      //   3177	3188	1959	org/json/JSONException
      //   3198	3205	1959	org/json/JSONException
      //   3212	3229	1959	org/json/JSONException
      //   3236	3246	1959	org/json/JSONException
      //   3253	3265	1959	org/json/JSONException
      //   3272	3280	1959	org/json/JSONException
      //   3287	3298	1959	org/json/JSONException
      //   3308	3315	1959	org/json/JSONException
      //   3322	3339	1959	org/json/JSONException
      //   3346	3356	1959	org/json/JSONException
      //   3363	3375	1959	org/json/JSONException
      //   3382	3390	1959	org/json/JSONException
      //   3397	3408	1959	org/json/JSONException
      //   3418	3425	1959	org/json/JSONException
      //   3432	3449	1959	org/json/JSONException
      //   3456	3466	1959	org/json/JSONException
      //   3473	3485	1959	org/json/JSONException
      //   3492	3500	1959	org/json/JSONException
      //   3507	3518	1959	org/json/JSONException
      //   3528	3535	1959	org/json/JSONException
      //   3542	3559	1959	org/json/JSONException
      //   3566	3576	1959	org/json/JSONException
      //   3583	3595	1959	org/json/JSONException
      //   3607	3618	1959	org/json/JSONException
      //   3628	3639	1959	org/json/JSONException
      //   3646	3656	1959	org/json/JSONException
      //   3663	3679	1959	org/json/JSONException
      //   3686	3692	1959	org/json/JSONException
      //   3699	3711	1959	org/json/JSONException
      //   3718	3730	1959	org/json/JSONException
      //   3737	3753	1959	org/json/JSONException
      //   3760	3775	1959	org/json/JSONException
      //   3782	3798	1959	org/json/JSONException
      //   3805	3820	1959	org/json/JSONException
      //   3827	3843	1959	org/json/JSONException
      //   3850	3866	1959	org/json/JSONException
      //   3873	3889	1959	org/json/JSONException
      //   3896	3903	1959	org/json/JSONException
      //   3910	3920	1959	org/json/JSONException
      //   3927	3940	1959	org/json/JSONException
      //   3947	3957	1959	org/json/JSONException
      //   3964	3983	1959	org/json/JSONException
      //   1980	1989	2098	java/lang/Exception
      //   1992	2000	2098	java/lang/Exception
      //   2003	2011	2098	java/lang/Exception
      //   2014	2023	2098	java/lang/Exception
      //   2026	2036	2098	java/lang/Exception
      //   2039	2054	2098	java/lang/Exception
      //   2057	2070	2098	java/lang/Exception
      //   2073	2084	2098	java/lang/Exception
      //   2087	2095	2098	java/lang/Exception
      //   3989	4000	2098	java/lang/Exception
      //   336	343	2928	java/lang/Exception
      //   354	373	2928	java/lang/Exception
      //   384	396	2928	java/lang/Exception
      //   407	414	2928	java/lang/Exception
      //   425	433	2928	java/lang/Exception
      //   449	458	2928	java/lang/Exception
      //   469	482	2928	java/lang/Exception
      //   493	506	2928	java/lang/Exception
      //   517	524	2928	java/lang/Exception
      //   535	553	2928	java/lang/Exception
      //   979	989	2952	android/content/pm/PackageManager$NameNotFoundException
      //   996	1010	2952	android/content/pm/PackageManager$NameNotFoundException
      //   1017	1031	2952	android/content/pm/PackageManager$NameNotFoundException
      //   16	31	2969	java/lang/Exception
      //   38	43	2969	java/lang/Exception
      //   50	65	2969	java/lang/Exception
      //   72	79	2969	java/lang/Exception
      //   86	92	2969	java/lang/Exception
      //   99	106	2969	java/lang/Exception
      //   113	125	2969	java/lang/Exception
      //   132	145	2969	java/lang/Exception
      //   152	156	2969	java/lang/Exception
      //   165	177	2969	java/lang/Exception
      //   184	197	2969	java/lang/Exception
      //   246	259	2969	java/lang/Exception
      //   266	279	2969	java/lang/Exception
      //   299	313	2969	java/lang/Exception
      //   567	579	2969	java/lang/Exception
      //   586	595	2969	java/lang/Exception
      //   602	613	2969	java/lang/Exception
      //   620	627	2969	java/lang/Exception
      //   634	645	2969	java/lang/Exception
      //   652	659	2969	java/lang/Exception
      //   666	677	2969	java/lang/Exception
      //   684	691	2969	java/lang/Exception
      //   698	709	2969	java/lang/Exception
      //   716	723	2969	java/lang/Exception
      //   730	737	2969	java/lang/Exception
      //   744	755	2969	java/lang/Exception
      //   762	769	2969	java/lang/Exception
      //   776	785	2969	java/lang/Exception
      //   792	799	2969	java/lang/Exception
      //   806	817	2969	java/lang/Exception
      //   824	831	2969	java/lang/Exception
      //   838	848	2969	java/lang/Exception
      //   855	861	2969	java/lang/Exception
      //   868	878	2969	java/lang/Exception
      //   885	897	2969	java/lang/Exception
      //   904	916	2969	java/lang/Exception
      //   923	935	2969	java/lang/Exception
      //   942	954	2969	java/lang/Exception
      //   961	972	2969	java/lang/Exception
      //   979	989	2969	java/lang/Exception
      //   996	1010	2969	java/lang/Exception
      //   1017	1031	2969	java/lang/Exception
      //   1069	1080	2969	java/lang/Exception
      //   1087	1098	2969	java/lang/Exception
      //   1105	1117	2969	java/lang/Exception
      //   1124	1138	2969	java/lang/Exception
      //   1145	1165	2969	java/lang/Exception
      //   1172	1184	2969	java/lang/Exception
      //   1191	1205	2969	java/lang/Exception
      //   1212	1226	2969	java/lang/Exception
      //   1233	1246	2969	java/lang/Exception
      //   1253	1265	2969	java/lang/Exception
      //   1272	1284	2969	java/lang/Exception
      //   1296	1307	2969	java/lang/Exception
      //   1354	1385	2969	java/lang/Exception
      //   1392	1404	2969	java/lang/Exception
      //   1411	1425	2969	java/lang/Exception
      //   1432	1452	2969	java/lang/Exception
      //   1459	1479	2969	java/lang/Exception
      //   1486	1500	2969	java/lang/Exception
      //   1507	1521	2969	java/lang/Exception
      //   1528	1547	2969	java/lang/Exception
      //   1554	1569	2969	java/lang/Exception
      //   1576	1590	2969	java/lang/Exception
      //   1597	1627	2969	java/lang/Exception
      //   1634	1650	2969	java/lang/Exception
      //   1657	1672	2969	java/lang/Exception
      //   1679	1691	2969	java/lang/Exception
      //   1698	1708	2969	java/lang/Exception
      //   1715	1729	2969	java/lang/Exception
      //   1736	1748	2969	java/lang/Exception
      //   1755	1769	2969	java/lang/Exception
      //   1776	1789	2969	java/lang/Exception
      //   1801	1819	2969	java/lang/Exception
      //   1826	1833	2969	java/lang/Exception
      //   1840	1849	2969	java/lang/Exception
      //   1856	1863	2969	java/lang/Exception
      //   1870	1887	2969	java/lang/Exception
      //   1894	1904	2969	java/lang/Exception
      //   1911	1923	2969	java/lang/Exception
      //   1930	1938	2969	java/lang/Exception
      //   1945	1956	2969	java/lang/Exception
      //   1968	1973	2969	java/lang/Exception
      //   2107	2112	2969	java/lang/Exception
      //   2188	2197	2969	java/lang/Exception
      //   2204	2217	2969	java/lang/Exception
      //   2224	2235	2969	java/lang/Exception
      //   2242	2249	2969	java/lang/Exception
      //   2256	2260	2969	java/lang/Exception
      //   2267	2277	2969	java/lang/Exception
      //   2284	2300	2969	java/lang/Exception
      //   2307	2329	2969	java/lang/Exception
      //   2336	2352	2969	java/lang/Exception
      //   2359	2374	2969	java/lang/Exception
      //   2381	2396	2969	java/lang/Exception
      //   2403	2418	2969	java/lang/Exception
      //   2425	2441	2969	java/lang/Exception
      //   2454	2503	2969	java/lang/Exception
      //   2510	2517	2969	java/lang/Exception
      //   2741	2757	2969	java/lang/Exception
      //   2773	2790	2969	java/lang/Exception
      //   2797	2803	2969	java/lang/Exception
      //   2810	2829	2969	java/lang/Exception
      //   2836	2847	2969	java/lang/Exception
      //   2854	2859	2969	java/lang/Exception
      //   2937	2942	2969	java/lang/Exception
      //   2961	2966	2969	java/lang/Exception
      //   3011	3016	2969	java/lang/Exception
      //   3049	3061	2969	java/lang/Exception
      //   3073	3078	2969	java/lang/Exception
      //   3088	3095	2969	java/lang/Exception
      //   3102	3119	2969	java/lang/Exception
      //   3126	3136	2969	java/lang/Exception
      //   3143	3155	2969	java/lang/Exception
      //   3162	3170	2969	java/lang/Exception
      //   3177	3188	2969	java/lang/Exception
      //   3198	3205	2969	java/lang/Exception
      //   3212	3229	2969	java/lang/Exception
      //   3236	3246	2969	java/lang/Exception
      //   3253	3265	2969	java/lang/Exception
      //   3272	3280	2969	java/lang/Exception
      //   3287	3298	2969	java/lang/Exception
      //   3308	3315	2969	java/lang/Exception
      //   3322	3339	2969	java/lang/Exception
      //   3346	3356	2969	java/lang/Exception
      //   3363	3375	2969	java/lang/Exception
      //   3382	3390	2969	java/lang/Exception
      //   3397	3408	2969	java/lang/Exception
      //   3418	3425	2969	java/lang/Exception
      //   3432	3449	2969	java/lang/Exception
      //   3456	3466	2969	java/lang/Exception
      //   3473	3485	2969	java/lang/Exception
      //   3492	3500	2969	java/lang/Exception
      //   3507	3518	2969	java/lang/Exception
      //   3528	3535	2969	java/lang/Exception
      //   3542	3559	2969	java/lang/Exception
      //   3566	3576	2969	java/lang/Exception
      //   3583	3595	2969	java/lang/Exception
      //   3607	3618	2969	java/lang/Exception
      //   3628	3639	2969	java/lang/Exception
      //   3646	3656	2969	java/lang/Exception
      //   3663	3679	2969	java/lang/Exception
      //   3686	3692	2969	java/lang/Exception
      //   3699	3711	2969	java/lang/Exception
      //   3718	3730	2969	java/lang/Exception
      //   3737	3753	2969	java/lang/Exception
      //   3760	3775	2969	java/lang/Exception
      //   3782	3798	2969	java/lang/Exception
      //   3805	3820	2969	java/lang/Exception
      //   3827	3843	2969	java/lang/Exception
      //   3850	3866	2969	java/lang/Exception
      //   3873	3889	2969	java/lang/Exception
      //   3896	3903	2969	java/lang/Exception
      //   3910	3920	2969	java/lang/Exception
      //   3927	3940	2969	java/lang/Exception
      //   3947	3957	2969	java/lang/Exception
      //   3964	3983	2969	java/lang/Exception
      //   4012	4017	2969	java/lang/Exception
      //   4029	4034	2969	java/lang/Exception
      //   4044	4059	2969	java/lang/Exception
      //   4066	4079	2969	java/lang/Exception
      //   4086	4092	2969	java/lang/Exception
      //   4136	4155	2969	java/lang/Exception
      //   4162	4181	2969	java/lang/Exception
      //   4191	4206	2969	java/lang/Exception
      //   4213	4228	2969	java/lang/Exception
      //   4235	4254	2969	java/lang/Exception
      //   4264	4317	2969	java/lang/Exception
      //   4338	4343	2969	java/lang/Exception
      //   4350	4364	2969	java/lang/Exception
      //   4371	4383	2969	java/lang/Exception
      //   4488	4497	2969	java/lang/Exception
      //   4504	4521	2969	java/lang/Exception
      //   4528	4535	2969	java/lang/Exception
      //   4547	4552	2969	java/lang/Exception
      //   4603	4608	2969	java/lang/Exception
      //   1034	1062	3002	java/lang/Exception
      //   16	31	3019	finally
      //   38	43	3019	finally
      //   50	65	3019	finally
      //   72	79	3019	finally
      //   86	92	3019	finally
      //   99	106	3019	finally
      //   113	125	3019	finally
      //   132	145	3019	finally
      //   152	156	3019	finally
      //   165	177	3019	finally
      //   184	197	3019	finally
      //   246	259	3019	finally
      //   266	279	3019	finally
      //   299	313	3019	finally
      //   336	343	3019	finally
      //   354	373	3019	finally
      //   384	396	3019	finally
      //   407	414	3019	finally
      //   425	433	3019	finally
      //   449	458	3019	finally
      //   469	482	3019	finally
      //   493	506	3019	finally
      //   517	524	3019	finally
      //   535	553	3019	finally
      //   567	579	3019	finally
      //   586	595	3019	finally
      //   602	613	3019	finally
      //   620	627	3019	finally
      //   634	645	3019	finally
      //   652	659	3019	finally
      //   666	677	3019	finally
      //   684	691	3019	finally
      //   698	709	3019	finally
      //   716	723	3019	finally
      //   730	737	3019	finally
      //   744	755	3019	finally
      //   762	769	3019	finally
      //   776	785	3019	finally
      //   792	799	3019	finally
      //   806	817	3019	finally
      //   824	831	3019	finally
      //   838	848	3019	finally
      //   855	861	3019	finally
      //   868	878	3019	finally
      //   885	897	3019	finally
      //   904	916	3019	finally
      //   923	935	3019	finally
      //   942	954	3019	finally
      //   961	972	3019	finally
      //   979	989	3019	finally
      //   996	1010	3019	finally
      //   1017	1031	3019	finally
      //   1034	1062	3019	finally
      //   1069	1080	3019	finally
      //   1087	1098	3019	finally
      //   1105	1117	3019	finally
      //   1124	1138	3019	finally
      //   1145	1165	3019	finally
      //   1172	1184	3019	finally
      //   1191	1205	3019	finally
      //   1212	1226	3019	finally
      //   1233	1246	3019	finally
      //   1253	1265	3019	finally
      //   1272	1284	3019	finally
      //   1296	1307	3019	finally
      //   1310	1319	3019	finally
      //   1336	1347	3019	finally
      //   1354	1385	3019	finally
      //   1392	1404	3019	finally
      //   1411	1425	3019	finally
      //   1432	1452	3019	finally
      //   1459	1479	3019	finally
      //   1486	1500	3019	finally
      //   1507	1521	3019	finally
      //   1528	1547	3019	finally
      //   1554	1569	3019	finally
      //   1576	1590	3019	finally
      //   1597	1627	3019	finally
      //   1634	1650	3019	finally
      //   1657	1672	3019	finally
      //   1679	1691	3019	finally
      //   1698	1708	3019	finally
      //   1715	1729	3019	finally
      //   1736	1748	3019	finally
      //   1755	1769	3019	finally
      //   1776	1789	3019	finally
      //   1801	1819	3019	finally
      //   1826	1833	3019	finally
      //   1840	1849	3019	finally
      //   1856	1863	3019	finally
      //   1870	1887	3019	finally
      //   1894	1904	3019	finally
      //   1911	1923	3019	finally
      //   1930	1938	3019	finally
      //   1945	1956	3019	finally
      //   1968	1973	3019	finally
      //   1980	1989	3019	finally
      //   1992	2000	3019	finally
      //   2003	2011	3019	finally
      //   2014	2023	3019	finally
      //   2026	2036	3019	finally
      //   2039	2054	3019	finally
      //   2057	2070	3019	finally
      //   2073	2084	3019	finally
      //   2087	2095	3019	finally
      //   2107	2112	3019	finally
      //   2115	2134	3019	finally
      //   2137	2156	3019	finally
      //   2159	2181	3019	finally
      //   2188	2197	3019	finally
      //   2204	2217	3019	finally
      //   2224	2235	3019	finally
      //   2242	2249	3019	finally
      //   2256	2260	3019	finally
      //   2267	2277	3019	finally
      //   2284	2300	3019	finally
      //   2307	2329	3019	finally
      //   2336	2352	3019	finally
      //   2359	2374	3019	finally
      //   2381	2396	3019	finally
      //   2403	2418	3019	finally
      //   2425	2441	3019	finally
      //   2454	2503	3019	finally
      //   2510	2517	3019	finally
      //   2741	2757	3019	finally
      //   2773	2790	3019	finally
      //   2797	2803	3019	finally
      //   2810	2829	3019	finally
      //   2836	2847	3019	finally
      //   2854	2859	3019	finally
      //   2867	2878	3019	finally
      //   2881	2887	3019	finally
      //   2890	2896	3019	finally
      //   2937	2942	3019	finally
      //   2961	2966	3019	finally
      //   2974	2979	3019	finally
      //   3011	3016	3019	finally
      //   3049	3061	3019	finally
      //   3073	3078	3019	finally
      //   3088	3095	3019	finally
      //   3102	3119	3019	finally
      //   3126	3136	3019	finally
      //   3143	3155	3019	finally
      //   3162	3170	3019	finally
      //   3177	3188	3019	finally
      //   3198	3205	3019	finally
      //   3212	3229	3019	finally
      //   3236	3246	3019	finally
      //   3253	3265	3019	finally
      //   3272	3280	3019	finally
      //   3287	3298	3019	finally
      //   3308	3315	3019	finally
      //   3322	3339	3019	finally
      //   3346	3356	3019	finally
      //   3363	3375	3019	finally
      //   3382	3390	3019	finally
      //   3397	3408	3019	finally
      //   3418	3425	3019	finally
      //   3432	3449	3019	finally
      //   3456	3466	3019	finally
      //   3473	3485	3019	finally
      //   3492	3500	3019	finally
      //   3507	3518	3019	finally
      //   3528	3535	3019	finally
      //   3542	3559	3019	finally
      //   3566	3576	3019	finally
      //   3583	3595	3019	finally
      //   3607	3618	3019	finally
      //   3628	3639	3019	finally
      //   3646	3656	3019	finally
      //   3663	3679	3019	finally
      //   3686	3692	3019	finally
      //   3699	3711	3019	finally
      //   3718	3730	3019	finally
      //   3737	3753	3019	finally
      //   3760	3775	3019	finally
      //   3782	3798	3019	finally
      //   3805	3820	3019	finally
      //   3827	3843	3019	finally
      //   3850	3866	3019	finally
      //   3873	3889	3019	finally
      //   3896	3903	3019	finally
      //   3910	3920	3019	finally
      //   3927	3940	3019	finally
      //   3947	3957	3019	finally
      //   3964	3983	3019	finally
      //   3989	4000	3019	finally
      //   4012	4017	3019	finally
      //   4029	4034	3019	finally
      //   4044	4059	3019	finally
      //   4066	4079	3019	finally
      //   4086	4092	3019	finally
      //   4136	4155	3019	finally
      //   4162	4181	3019	finally
      //   4191	4206	3019	finally
      //   4213	4228	3019	finally
      //   4235	4254	3019	finally
      //   4264	4317	3019	finally
      //   4338	4343	3019	finally
      //   4350	4364	3019	finally
      //   4371	4383	3019	finally
      //   4488	4497	3019	finally
      //   4504	4521	3019	finally
      //   4528	4535	3019	finally
      //   4547	4552	3019	finally
      //   4603	4608	3019	finally
      //   1310	1319	3064	java/lang/Exception
      //   1336	1347	3064	java/lang/Exception
      //   2115	2134	4003	java/lang/Exception
      //   2137	2156	4003	java/lang/Exception
      //   2159	2181	4020	java/lang/Exception
      //   2659	2674	4320	finally
      //   2517	2607	4329	java/io/IOException
      //   2607	2659	4329	java/io/IOException
      //   2674	2706	4329	java/io/IOException
      //   2710	2725	4329	java/io/IOException
      //   4322	4329	4329	java/io/IOException
      //   2836	2847	4538	org/json/JSONException
      //   2854	2859	4538	org/json/JSONException
      //   2867	2878	4594	java/lang/Exception
      //   2881	2887	4594	java/lang/Exception
      //   2890	2896	4594	java/lang/Exception
      //   2517	2607	4666	finally
      //   2607	2659	4666	finally
      //   2674	2706	4666	finally
      //   2710	2725	4666	finally
      //   4322	4329	4666	finally
      //   2517	2607	4678	java/lang/Exception
      //   2607	2659	4678	java/lang/Exception
      //   2674	2706	4678	java/lang/Exception
      //   2710	2725	4678	java/lang/Exception
      //   4322	4329	4678	java/lang/Exception
      //   2510	2517	4689	java/io/IOException
    }
    
    protected void a(JSONObject paramJSONObject)
    {
      try
      {
        if (p.j(p.this) != null)
        {
          if (paramJSONObject == null)
          {
            p.j(p.this).a(p.k(p.this));
            return;
          }
          p.j(p.this).a(paramJSONObject, p.k(p.this), p.a(p.this));
          return;
        }
      }
      catch (Exception paramJSONObject)
      {
        Appodeal.a(paramJSONObject);
      }
    }
    
    protected void onPreExecute()
    {
      boolean bool2 = false;
      if ((p.a(p.this).equals("banner")) || (p.a(p.this).equals("debug")))
      {
        bool1 = true;
        this.b = bool1;
        if ((!p.a(p.this).equals("banner_320")) && (!p.a(p.this).equals("debug_banner_320"))) {
          break label390;
        }
        bool1 = true;
        label73:
        this.c = bool1;
        if ((!p.a(p.this).equals("banner_mrec")) && (!p.a(p.this).equals("debug_banner_mrec"))) {
          break label395;
        }
        bool1 = true;
        label111:
        this.d = bool1;
        if ((!p.a(p.this).equals("video")) && (!p.a(p.this).equals("debug_video"))) {
          break label400;
        }
        bool1 = true;
        label149:
        this.e = bool1;
        if ((!p.a(p.this).equals("rewarded_video")) && (!p.a(p.this).equals("debug_rewarded_video"))) {
          break label405;
        }
        bool1 = true;
        label187:
        this.f = bool1;
        if ((!p.a(p.this).equals("native")) && (!p.a(p.this).equals("debug_native"))) {
          break label410;
        }
        bool1 = true;
        label225:
        this.g = bool1;
        if ((!p.a(p.this).equals("debug")) && (!p.a(p.this).equals("debug_banner_320")) && (!p.a(p.this).equals("debug_video")) && (!p.a(p.this).equals("debug_rewarded_video")) && (!p.a(p.this).equals("debug_banner_mrec")) && (!p.a(p.this).equals("debug_native"))) {
          break label415;
        }
      }
      label390:
      label395:
      label400:
      label405:
      label410:
      label415:
      for (boolean bool1 = true;; bool1 = false)
      {
        this.h = bool1;
        if ((!this.b) && (!this.c) && (!this.d) && (!this.e) && (!this.f))
        {
          bool1 = bool2;
          if (!this.g) {}
        }
        else
        {
          bool1 = true;
        }
        this.i = bool1;
        return;
        bool1 = false;
        break;
        bool1 = false;
        break label73;
        bool1 = false;
        break label111;
        bool1 = false;
        break label149;
        bool1 = false;
        break label187;
        bool1 = false;
        break label225;
      }
    }
  }
}
