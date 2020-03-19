package com.appodeal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.appodeal.ads.d.h;
import com.appodeal.ads.f.c;
import com.appodeal.ads.f.f;
import com.appodeal.ads.f.f.a;
import com.appodeal.ads.f.g;
import com.appodeal.ads.utils.k;
import com.appodeal.ads.utils.q;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class r
{
  private final a a;
  private final Context b;
  private final int c;
  private final String d;
  private final String e;
  private final String f;
  private final String g;
  private final h h;
  private final c i;
  private final long j;
  private final String k;
  
  public r(Context paramContext, c paramC, a paramA, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, h paramH)
  {
    this(paramContext, paramC, paramA, paramInt, paramString1, paramString2, paramString3, paramString4, paramH, 0L, null);
  }
  
  r(Context paramContext, c paramC, a paramA, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, h paramH, long paramLong, String paramString5)
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
    if ((AppodealSettings.a) && ((this.a == null) || ((this.a instanceof q))) && ((this.d.equals("stats")) || (this.d.equals("show")) || (this.d.equals("click")) || (this.d.equals("finish")) || (this.d.equals("install")))) {
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
            new r.b(r.this, null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
          }
          new r.b(r.this, null).execute(new Void[0]);
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
  
  r(Context paramContext, a paramA, int paramInt, String paramString)
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
      int j = d.w;
      int k = l.o;
      int m = af.m;
      int n = ai.m;
      int i1 = y.n;
      int i2 = s.s;
      int i3 = d.x;
      int i4 = l.p;
      int i5 = af.o;
      int i6 = ai.o;
      int i7 = y.o;
      int i8 = s.t;
      int i9 = ai.n;
      int i10 = af.n;
      try
      {
        localJSONObject.put("show", j + k + m + n + i1 + i2);
        localJSONObject.put("click", i3 + i4 + i5 + i6 + i7 + i8);
        if ((this.e) || (this.f) || ((r.g(r.this) != null) && ((r.g(r.this).equals("video")) || (r.g(r.this).equals("rewarded_video"))))) {
          localJSONObject.put("finish", i9 + i10);
        }
        if ((this.b) || ((r.g(r.this) != null) && (r.g(r.this).equals("banner"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "show" }), l.o);
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "click" }), l.p);
        }
        if ((this.e) || ((r.g(r.this) != null) && (r.g(r.this).equals("video"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "video", "show" }), af.m);
          localJSONObject.put(String.format("%s_%s", new Object[] { "video", "click" }), af.o);
          localJSONObject.put(String.format("%s_%s", new Object[] { "video", "finish" }), af.n);
        }
        if ((this.f) || ((r.g(r.this) != null) && (r.g(r.this).equals("rewarded_video"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "show" }), ai.m);
          localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "click" }), ai.o);
          localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "finish" }), ai.n);
        }
        if ((this.c) || ((r.g(r.this) != null) && (r.g(r.this).equals("banner_320"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "show" }), d.w);
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "click" }), d.x);
        }
        if ((this.d) || ((r.g(r.this) != null) && (r.g(r.this).equals("banner_mrec"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "show" }), s.s);
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "click" }), s.t);
        }
        if ((this.g) || ((r.g(r.this) != null) && (r.g(r.this).equals("native"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "native", "show" }), y.n);
          localJSONObject.put(String.format("%s_%s", new Object[] { "native", "click" }), y.o);
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
      paramJSONObject = new g(r.b(r.this), paramJSONObject).a(localJSONArray);
      if (paramJSONObject != null) {}
      for (;;)
      {
        try
        {
          paramJSONObject.a();
          paramJSONObject.b().n();
          g.a = Long.valueOf(paramJSONObject.c());
          g.a(paramJSONObject);
          if (Appodeal.d == null) {
            break;
          }
          Appodeal.d.a();
          return;
        }
        catch (JSONException localJSONException)
        {
          Appodeal.a(localJSONException);
          continue;
        }
        g.a = Long.valueOf(-1L);
      }
    }
    
    /* Error */
    protected JSONObject a(Void... paramVarArgs)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 13
      //   3: aconst_null
      //   4: astore 11
      //   6: aconst_null
      //   7: astore 12
      //   9: aload 12
      //   11: astore 7
      //   13: aload 13
      //   15: astore_1
      //   16: aload_0
      //   17: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   20: invokestatic 154	com/appodeal/ads/r:b	(Lcom/appodeal/ads/r;)Landroid/content/Context;
      //   23: ldc -55
      //   25: iconst_0
      //   26: invokevirtual 207	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   29: astore 14
      //   31: aload 12
      //   33: astore 7
      //   35: aload 13
      //   37: astore_1
      //   38: invokestatic 213	java/util/Calendar:getInstance	()Ljava/util/Calendar;
      //   41: astore 6
      //   43: aload 12
      //   45: astore 7
      //   47: aload 13
      //   49: astore_1
      //   50: aload 6
      //   52: aload 14
      //   54: ldc -41
      //   56: lconst_0
      //   57: invokeinterface 221 4 0
      //   62: invokevirtual 225	java/util/Calendar:setTimeInMillis	(J)V
      //   65: aload 12
      //   67: astore 7
      //   69: aload 13
      //   71: astore_1
      //   72: aload 6
      //   74: iconst_5
      //   75: iconst_1
      //   76: invokevirtual 229	java/util/Calendar:add	(II)V
      //   79: aload 12
      //   81: astore 7
      //   83: aload 13
      //   85: astore_1
      //   86: getstatic 234	com/appodeal/ads/UserSettings:sendingInProgress	Z
      //   89: ifne +4746 -> 4835
      //   92: aload 12
      //   94: astore 7
      //   96: aload 13
      //   98: astore_1
      //   99: aload_0
      //   100: getfield 236	com/appodeal/ads/r$b:i	Z
      //   103: ifeq +4732 -> 4835
      //   106: aload 12
      //   108: astore 7
      //   110: aload 13
      //   112: astore_1
      //   113: aload 6
      //   115: invokevirtual 239	java/util/Calendar:getTimeInMillis	()J
      //   118: invokestatic 244	java/lang/System:currentTimeMillis	()J
      //   121: lcmp
      //   122: iflt +23 -> 145
      //   125: aload 12
      //   127: astore 7
      //   129: aload 13
      //   131: astore_1
      //   132: aload 14
      //   134: ldc -10
      //   136: iconst_1
      //   137: invokeinterface 250 3 0
      //   142: ifeq +4693 -> 4835
      //   145: aload 12
      //   147: astore 7
      //   149: aload 13
      //   151: astore_1
      //   152: iconst_1
      //   153: putstatic 234	com/appodeal/ads/UserSettings:sendingInProgress	Z
      //   156: iconst_1
      //   157: istore 4
      //   159: aload 12
      //   161: astore 7
      //   163: aload 13
      //   165: astore_1
      //   166: aload_0
      //   167: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   170: invokestatic 154	com/appodeal/ads/r:b	(Lcom/appodeal/ads/r;)Landroid/content/Context;
      //   173: invokevirtual 254	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   176: astore 15
      //   178: aload 12
      //   180: astore 7
      //   182: aload 13
      //   184: astore_1
      //   185: aload 14
      //   187: ldc_w 256
      //   190: aconst_null
      //   191: invokeinterface 260 3 0
      //   196: astore 16
      //   198: aload 16
      //   200: ifnonnull +40 -> 240
      //   203: iconst_0
      //   204: ifeq +17 -> 221
      //   207: aconst_null
      //   208: instanceof 262
      //   211: ifeq +12 -> 223
      //   214: aconst_null
      //   215: checkcast 262	javax/net/ssl/HttpsURLConnection
      //   218: invokevirtual 265	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   221: aconst_null
      //   222: areturn
      //   223: aconst_null
      //   224: instanceof 267
      //   227: ifeq -6 -> 221
      //   230: aconst_null
      //   231: checkcast 267	java/net/HttpURLConnection
      //   234: invokevirtual 268	java/net/HttpURLConnection:disconnect	()V
      //   237: goto -16 -> 221
      //   240: aload 12
      //   242: astore 7
      //   244: aload 13
      //   246: astore_1
      //   247: aload 14
      //   249: ldc_w 270
      //   252: aconst_null
      //   253: invokeinterface 260 3 0
      //   258: astore 10
      //   260: aload 12
      //   262: astore 7
      //   264: aload 13
      //   266: astore_1
      //   267: aload 14
      //   269: ldc_w 272
      //   272: aconst_null
      //   273: invokeinterface 260 3 0
      //   278: astore 6
      //   280: aload 6
      //   282: astore 8
      //   284: aload 10
      //   286: astore 9
      //   288: aload 10
      //   290: ifnonnull +2780 -> 3070
      //   293: aload 12
      //   295: astore 7
      //   297: aload 13
      //   299: astore_1
      //   300: aload_0
      //   301: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   304: invokestatic 274	com/appodeal/ads/r:a	(Lcom/appodeal/ads/r;)Ljava/lang/String;
      //   307: ldc_w 276
      //   310: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   313: istore 5
      //   315: aload 6
      //   317: astore 8
      //   319: aload 10
      //   321: astore 9
      //   323: iload 5
      //   325: ifne +2745 -> 3070
      //   328: aload 6
      //   330: astore 8
      //   332: aload 10
      //   334: astore 9
      //   336: aload 13
      //   338: astore_1
      //   339: ldc_w 278
      //   342: invokestatic 284	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
      //   345: pop
      //   346: aload 6
      //   348: astore 8
      //   350: aload 10
      //   352: astore 9
      //   354: aload 13
      //   356: astore_1
      //   357: ldc_w 286
      //   360: ldc_w 288
      //   363: iconst_1
      //   364: anewarray 280	java/lang/Class
      //   367: dup
      //   368: iconst_0
      //   369: ldc -53
      //   371: aastore
      //   372: invokevirtual 292	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      //   375: pop
      //   376: aload 6
      //   378: astore 8
      //   380: aload 10
      //   382: astore 9
      //   384: aload 13
      //   386: astore_1
      //   387: aload_0
      //   388: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   391: invokestatic 154	com/appodeal/ads/r:b	(Lcom/appodeal/ads/r;)Landroid/content/Context;
      //   394: invokestatic 295	com/google/android/gms/ads/identifier/AdvertisingIdClient:getAdvertisingIdInfo	(Landroid/content/Context;)Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;
      //   397: astore 17
      //   399: aload 6
      //   401: astore 8
      //   403: aload 10
      //   405: astore 9
      //   407: aload 13
      //   409: astore_1
      //   410: aload 17
      //   412: invokevirtual 301	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:getId	()Ljava/lang/String;
      //   415: astore 7
      //   417: aload 6
      //   419: astore 8
      //   421: aload 7
      //   423: astore 9
      //   425: aload 13
      //   427: astore_1
      //   428: aload 17
      //   430: invokevirtual 305	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:isLimitAdTrackingEnabled	()Z
      //   433: ifeq +2615 -> 3048
      //   436: ldc_w 307
      //   439: astore 6
      //   441: aload 6
      //   443: astore 8
      //   445: aload 7
      //   447: astore 9
      //   449: aload 13
      //   451: astore_1
      //   452: aload 14
      //   454: invokeinterface 311 1 0
      //   459: astore 10
      //   461: aload 6
      //   463: astore 8
      //   465: aload 7
      //   467: astore 9
      //   469: aload 13
      //   471: astore_1
      //   472: aload 10
      //   474: ldc_w 270
      //   477: aload 7
      //   479: invokeinterface 317 3 0
      //   484: pop
      //   485: aload 6
      //   487: astore 8
      //   489: aload 7
      //   491: astore 9
      //   493: aload 13
      //   495: astore_1
      //   496: aload 10
      //   498: ldc_w 272
      //   501: aload 6
      //   503: invokeinterface 317 3 0
      //   508: pop
      //   509: aload 6
      //   511: astore 8
      //   513: aload 7
      //   515: astore 9
      //   517: aload 13
      //   519: astore_1
      //   520: aload 10
      //   522: invokeinterface 320 1 0
      //   527: aload 6
      //   529: astore 8
      //   531: aload 7
      //   533: astore 9
      //   535: aload 13
      //   537: astore_1
      //   538: ldc_w 322
      //   541: iconst_1
      //   542: anewarray 113	java/lang/Object
      //   545: dup
      //   546: iconst_0
      //   547: aload 7
      //   549: aastore
      //   550: invokestatic 117	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   553: invokestatic 325	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   556: aload 7
      //   558: astore_1
      //   559: aload_1
      //   560: ifnonnull +4269 -> 4829
      //   563: aload 12
      //   565: astore 7
      //   567: aload 13
      //   569: astore_1
      //   570: aload_0
      //   571: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   574: invokestatic 154	com/appodeal/ads/r:b	(Lcom/appodeal/ads/r;)Landroid/content/Context;
      //   577: invokestatic 331	com/appodeal/ads/al:l	(Landroid/content/Context;)Ljava/lang/String;
      //   580: astore 8
      //   582: aload 12
      //   584: astore 7
      //   586: aload 13
      //   588: astore_1
      //   589: new 34	org/json/JSONObject
      //   592: dup
      //   593: invokespecial 35	org/json/JSONObject:<init>	()V
      //   596: astore 9
      //   598: aload 12
      //   600: astore 7
      //   602: aload 13
      //   604: astore_1
      //   605: aload 9
      //   607: ldc_w 333
      //   610: aload 16
      //   612: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   615: pop
      //   616: aload 12
      //   618: astore 7
      //   620: aload 13
      //   622: astore_1
      //   623: aload_0
      //   624: getfield 107	com/appodeal/ads/r$b:b	Z
      //   627: ifeq +21 -> 648
      //   630: aload 12
      //   632: astore 7
      //   634: aload 13
      //   636: astore_1
      //   637: aload 9
      //   639: ldc_w 338
      //   642: ldc 109
      //   644: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   647: pop
      //   648: aload 12
      //   650: astore 7
      //   652: aload 13
      //   654: astore_1
      //   655: aload_0
      //   656: getfield 119	com/appodeal/ads/r$b:c	Z
      //   659: ifeq +103 -> 762
      //   662: aload 12
      //   664: astore 7
      //   666: aload 13
      //   668: astore_1
      //   669: aload 9
      //   671: ldc_w 338
      //   674: ldc 121
      //   676: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   679: pop
      //   680: aload 12
      //   682: astore 7
      //   684: aload 13
      //   686: astore_1
      //   687: aload_0
      //   688: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   691: invokestatic 154	com/appodeal/ads/r:b	(Lcom/appodeal/ads/r;)Landroid/content/Context;
      //   694: invokestatic 341	com/appodeal/ads/al:g	(Landroid/content/Context;)F
      //   697: fstore_2
      //   698: aload 12
      //   700: astore 7
      //   702: aload 13
      //   704: astore_1
      //   705: aload_0
      //   706: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   709: invokestatic 154	com/appodeal/ads/r:b	(Lcom/appodeal/ads/r;)Landroid/content/Context;
      //   712: invokestatic 343	com/appodeal/ads/al:h	(Landroid/content/Context;)F
      //   715: fstore_3
      //   716: aload 12
      //   718: astore 7
      //   720: aload 13
      //   722: astore_1
      //   723: getstatic 346	com/appodeal/ads/d:q	Z
      //   726: ifeq +36 -> 762
      //   729: fload_2
      //   730: ldc_w 347
      //   733: fcmpl
      //   734: iflt +28 -> 762
      //   737: fload_3
      //   738: ldc_w 348
      //   741: fcmpl
      //   742: ifle +20 -> 762
      //   745: aload 12
      //   747: astore 7
      //   749: aload 13
      //   751: astore_1
      //   752: aload 9
      //   754: ldc_w 350
      //   757: iconst_1
      //   758: invokevirtual 353	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   761: pop
      //   762: aload 12
      //   764: astore 7
      //   766: aload 13
      //   768: astore_1
      //   769: aload_0
      //   770: getfield 123	com/appodeal/ads/r$b:d	Z
      //   773: ifeq +21 -> 794
      //   776: aload 12
      //   778: astore 7
      //   780: aload 13
      //   782: astore_1
      //   783: aload 9
      //   785: ldc_w 338
      //   788: ldc 125
      //   790: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   793: pop
      //   794: aload 12
      //   796: astore 7
      //   798: aload 13
      //   800: astore_1
      //   801: aload_0
      //   802: getfield 88	com/appodeal/ads/r$b:e	Z
      //   805: ifne +17 -> 822
      //   808: aload 12
      //   810: astore 7
      //   812: aload 13
      //   814: astore_1
      //   815: aload_0
      //   816: getfield 90	com/appodeal/ads/r$b:f	Z
      //   819: ifeq +21 -> 840
      //   822: aload 12
      //   824: astore 7
      //   826: aload 13
      //   828: astore_1
      //   829: aload 9
      //   831: ldc_w 338
      //   834: ldc 95
      //   836: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   839: pop
      //   840: aload 12
      //   842: astore 7
      //   844: aload 13
      //   846: astore_1
      //   847: aload_0
      //   848: getfield 90	com/appodeal/ads/r$b:f	Z
      //   851: ifeq +19 -> 870
      //   854: aload 12
      //   856: astore 7
      //   858: aload 13
      //   860: astore_1
      //   861: aload 9
      //   863: ldc 103
      //   865: iconst_1
      //   866: invokevirtual 353	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   869: pop
      //   870: aload 12
      //   872: astore 7
      //   874: aload 13
      //   876: astore_1
      //   877: aload_0
      //   878: getfield 127	com/appodeal/ads/r$b:g	Z
      //   881: ifeq +21 -> 902
      //   884: aload 12
      //   886: astore 7
      //   888: aload 13
      //   890: astore_1
      //   891: aload 9
      //   893: ldc_w 338
      //   896: ldc -127
      //   898: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   901: pop
      //   902: aload 12
      //   904: astore 7
      //   906: aload 13
      //   908: astore_1
      //   909: aload_0
      //   910: getfield 355	com/appodeal/ads/r$b:h	Z
      //   913: ifeq +20 -> 933
      //   916: aload 12
      //   918: astore 7
      //   920: aload 13
      //   922: astore_1
      //   923: aload 9
      //   925: ldc_w 357
      //   928: iconst_1
      //   929: invokevirtual 353	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   932: pop
      //   933: aload 12
      //   935: astore 7
      //   937: aload 13
      //   939: astore_1
      //   940: getstatic 361	com/appodeal/ads/AppodealSettings:a	Z
      //   943: ifeq +20 -> 963
      //   946: aload 12
      //   948: astore 7
      //   950: aload 13
      //   952: astore_1
      //   953: aload 9
      //   955: ldc_w 363
      //   958: iconst_1
      //   959: invokevirtual 353	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   962: pop
      //   963: aload 12
      //   965: astore 7
      //   967: aload 13
      //   969: astore_1
      //   970: aload 9
      //   972: ldc_w 365
      //   975: getstatic 371	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
      //   978: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   981: pop
      //   982: aload 12
      //   984: astore 7
      //   986: aload 13
      //   988: astore_1
      //   989: aload 9
      //   991: ldc_w 373
      //   994: getstatic 376	android/os/Build$VERSION:SDK_INT	I
      //   997: invokevirtual 84	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
      //   1000: pop
      //   1001: aload 12
      //   1003: astore 7
      //   1005: aload 13
      //   1007: astore_1
      //   1008: aload 9
      //   1010: ldc_w 378
      //   1013: ldc_w 380
      //   1016: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1019: pop
      //   1020: aload 12
      //   1022: astore 7
      //   1024: aload 13
      //   1026: astore_1
      //   1027: aload_0
      //   1028: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   1031: invokestatic 154	com/appodeal/ads/r:b	(Lcom/appodeal/ads/r;)Landroid/content/Context;
      //   1034: invokevirtual 383	android/content/Context:getPackageName	()Ljava/lang/String;
      //   1037: astore 10
      //   1039: aload 12
      //   1041: astore 7
      //   1043: aload 13
      //   1045: astore_1
      //   1046: aload 9
      //   1048: ldc_w 385
      //   1051: aload 10
      //   1053: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1056: pop
      //   1057: aload 12
      //   1059: astore 7
      //   1061: aload 13
      //   1063: astore_1
      //   1064: aload 15
      //   1066: aload 10
      //   1068: iconst_0
      //   1069: invokevirtual 391	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
      //   1072: astore 16
      //   1074: aload 12
      //   1076: astore 7
      //   1078: aload 13
      //   1080: astore_1
      //   1081: aload 9
      //   1083: ldc_w 393
      //   1086: aload 16
      //   1088: getfield 398	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
      //   1091: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1094: pop
      //   1095: aload 12
      //   1097: astore 7
      //   1099: aload 13
      //   1101: astore_1
      //   1102: aload 9
      //   1104: ldc_w 400
      //   1107: aload 16
      //   1109: getfield 403	android/content/pm/PackageInfo:versionCode	I
      //   1112: invokevirtual 84	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
      //   1115: pop
      //   1116: aload 13
      //   1118: astore_1
      //   1119: aload 9
      //   1121: ldc_w 405
      //   1124: aload 15
      //   1126: aload 10
      //   1128: sipush 128
      //   1131: invokevirtual 409	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
      //   1134: getfield 415	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
      //   1137: ldc_w 417
      //   1140: invokevirtual 422	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   1143: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1146: pop
      //   1147: aload 12
      //   1149: astore 7
      //   1151: aload 13
      //   1153: astore_1
      //   1154: aload 9
      //   1156: ldc_w 424
      //   1159: aload 8
      //   1161: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1164: pop
      //   1165: aload 12
      //   1167: astore 7
      //   1169: aload 13
      //   1171: astore_1
      //   1172: aload 9
      //   1174: ldc_w 426
      //   1177: aload 6
      //   1179: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1182: pop
      //   1183: aload 12
      //   1185: astore 7
      //   1187: aload 13
      //   1189: astore_1
      //   1190: aload_0
      //   1191: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   1194: invokestatic 154	com/appodeal/ads/r:b	(Lcom/appodeal/ads/r;)Landroid/content/Context;
      //   1197: invokestatic 429	com/appodeal/ads/al:b	(Landroid/content/Context;)Lcom/appodeal/ads/al$a;
      //   1200: astore 8
      //   1202: aload 12
      //   1204: astore 7
      //   1206: aload 13
      //   1208: astore_1
      //   1209: aload 9
      //   1211: ldc_w 431
      //   1214: aload 8
      //   1216: getfield 435	com/appodeal/ads/al$a:a	Ljava/lang/String;
      //   1219: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1222: pop
      //   1223: aload 12
      //   1225: astore 7
      //   1227: aload 13
      //   1229: astore_1
      //   1230: aload 9
      //   1232: ldc_w 437
      //   1235: aload_0
      //   1236: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   1239: invokestatic 154	com/appodeal/ads/r:b	(Lcom/appodeal/ads/r;)Landroid/content/Context;
      //   1242: invokestatic 440	com/appodeal/ads/al:k	(Landroid/content/Context;)F
      //   1245: f2d
      //   1246: invokevirtual 443	org/json/JSONObject:put	(Ljava/lang/String;D)Lorg/json/JSONObject;
      //   1249: pop
      //   1250: aload 12
      //   1252: astore 7
      //   1254: aload 13
      //   1256: astore_1
      //   1257: aload_0
      //   1258: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   1261: invokestatic 154	com/appodeal/ads/r:b	(Lcom/appodeal/ads/r;)Landroid/content/Context;
      //   1264: invokestatic 446	com/appodeal/ads/al:f	(Landroid/content/Context;)Landroid/util/Pair;
      //   1267: astore 6
      //   1269: aload 12
      //   1271: astore 7
      //   1273: aload 13
      //   1275: astore_1
      //   1276: aload 9
      //   1278: ldc_w 448
      //   1281: aload 6
      //   1283: getfield 454	android/util/Pair:first	Ljava/lang/Object;
      //   1286: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1289: pop
      //   1290: aload 12
      //   1292: astore 7
      //   1294: aload 13
      //   1296: astore_1
      //   1297: aload 9
      //   1299: ldc_w 456
      //   1302: aload 6
      //   1304: getfield 459	android/util/Pair:second	Ljava/lang/Object;
      //   1307: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1310: pop
      //   1311: aload 12
      //   1313: astore 7
      //   1315: aload 13
      //   1317: astore_1
      //   1318: aload_0
      //   1319: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   1322: invokestatic 154	com/appodeal/ads/r:b	(Lcom/appodeal/ads/r;)Landroid/content/Context;
      //   1325: invokestatic 462	com/appodeal/ads/al:n	(Landroid/content/Context;)Z
      //   1328: ifeq +1842 -> 3170
      //   1331: aload 12
      //   1333: astore 7
      //   1335: aload 13
      //   1337: astore_1
      //   1338: aload 9
      //   1340: ldc_w 464
      //   1343: ldc_w 466
      //   1346: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1349: pop
      //   1350: aload 12
      //   1352: astore 7
      //   1354: aload 13
      //   1356: astore_1
      //   1357: getstatic 471	android/os/Build:MANUFACTURER	Ljava/lang/String;
      //   1360: ldc_w 473
      //   1363: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1366: ifeq +3475 -> 4841
      //   1369: ldc_w 475
      //   1372: astore 6
      //   1374: aload 12
      //   1376: astore 7
      //   1378: aload 13
      //   1380: astore_1
      //   1381: aload 9
      //   1383: ldc_w 477
      //   1386: aload 6
      //   1388: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1391: pop
      //   1392: aload 13
      //   1394: astore_1
      //   1395: aload 15
      //   1397: aload 10
      //   1399: invokevirtual 480	android/content/pm/PackageManager:getInstallerPackageName	(Ljava/lang/String;)Ljava/lang/String;
      //   1402: astore 7
      //   1404: aload 7
      //   1406: astore 6
      //   1408: aload 7
      //   1410: ifnonnull +8 -> 1418
      //   1413: ldc_w 482
      //   1416: astore 6
      //   1418: aload 13
      //   1420: astore_1
      //   1421: aload 9
      //   1423: ldc_w 484
      //   1426: aload 6
      //   1428: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1431: pop
      //   1432: aload 12
      //   1434: astore 7
      //   1436: aload 13
      //   1438: astore_1
      //   1439: aload 9
      //   1441: ldc_w 486
      //   1444: getstatic 471	android/os/Build:MANUFACTURER	Ljava/lang/String;
      //   1447: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1450: pop
      //   1451: aload 12
      //   1453: astore 7
      //   1455: aload 13
      //   1457: astore_1
      //   1458: aload 9
      //   1460: ldc_w 488
      //   1463: ldc_w 490
      //   1466: iconst_2
      //   1467: anewarray 113	java/lang/Object
      //   1470: dup
      //   1471: iconst_0
      //   1472: getstatic 471	android/os/Build:MANUFACTURER	Ljava/lang/String;
      //   1475: aastore
      //   1476: dup
      //   1477: iconst_1
      //   1478: getstatic 493	android/os/Build:MODEL	Ljava/lang/String;
      //   1481: aastore
      //   1482: invokestatic 117	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   1485: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1488: pop
      //   1489: aload 12
      //   1491: astore 7
      //   1493: aload 13
      //   1495: astore_1
      //   1496: aload_0
      //   1497: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   1500: invokestatic 154	com/appodeal/ads/r:b	(Lcom/appodeal/ads/r;)Landroid/content/Context;
      //   1503: invokestatic 495	com/appodeal/ads/al:d	(Landroid/content/Context;)Landroid/util/Pair;
      //   1506: astore 6
      //   1508: aload 12
      //   1510: astore 7
      //   1512: aload 13
      //   1514: astore_1
      //   1515: aload 9
      //   1517: ldc_w 497
      //   1520: aload 6
      //   1522: getfield 454	android/util/Pair:first	Ljava/lang/Object;
      //   1525: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1528: pop
      //   1529: aload 12
      //   1531: astore 7
      //   1533: aload 13
      //   1535: astore_1
      //   1536: aload 9
      //   1538: ldc_w 499
      //   1541: aload 6
      //   1543: getfield 459	android/util/Pair:second	Ljava/lang/Object;
      //   1546: checkcast 450	android/util/Pair
      //   1549: getfield 454	android/util/Pair:first	Ljava/lang/Object;
      //   1552: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1555: pop
      //   1556: aload 12
      //   1558: astore 7
      //   1560: aload 13
      //   1562: astore_1
      //   1563: aload 9
      //   1565: ldc_w 501
      //   1568: aload 6
      //   1570: getfield 459	android/util/Pair:second	Ljava/lang/Object;
      //   1573: checkcast 450	android/util/Pair
      //   1576: getfield 459	android/util/Pair:second	Ljava/lang/Object;
      //   1579: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1582: pop
      //   1583: aload 12
      //   1585: astore 7
      //   1587: aload 13
      //   1589: astore_1
      //   1590: aload 9
      //   1592: ldc_w 503
      //   1595: aload 8
      //   1597: getfield 505	com/appodeal/ads/al$a:b	Ljava/lang/String;
      //   1600: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1603: pop
      //   1604: aload 12
      //   1606: astore 7
      //   1608: aload 13
      //   1610: astore_1
      //   1611: aload 9
      //   1613: ldc_w 507
      //   1616: aload 8
      //   1618: getfield 508	com/appodeal/ads/al$a:c	Z
      //   1621: invokevirtual 353	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   1624: pop
      //   1625: aload 12
      //   1627: astore 7
      //   1629: aload 13
      //   1631: astore_1
      //   1632: aload 9
      //   1634: ldc_w 510
      //   1637: aload_0
      //   1638: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   1641: invokestatic 154	com/appodeal/ads/r:b	(Lcom/appodeal/ads/r;)Landroid/content/Context;
      //   1644: invokestatic 512	com/appodeal/ads/al:c	(Landroid/content/Context;)Ljava/lang/String;
      //   1647: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1650: pop
      //   1651: aload 12
      //   1653: astore 7
      //   1655: aload 13
      //   1657: astore_1
      //   1658: aload 9
      //   1660: ldc_w 514
      //   1663: invokestatic 520	java/util/Locale:getDefault	()Ljava/util/Locale;
      //   1666: invokevirtual 523	java/util/Locale:toString	()Ljava/lang/String;
      //   1669: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1672: pop
      //   1673: aload 12
      //   1675: astore 7
      //   1677: aload 13
      //   1679: astore_1
      //   1680: ldc_w 525
      //   1683: invokestatic 531	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
      //   1686: getstatic 535	java/util/Locale:ENGLISH	Ljava/util/Locale;
      //   1689: invokestatic 538	java/util/Calendar:getInstance	(Ljava/util/TimeZone;Ljava/util/Locale;)Ljava/util/Calendar;
      //   1692: astore 6
      //   1694: aload 12
      //   1696: astore 7
      //   1698: aload 13
      //   1700: astore_1
      //   1701: aload 9
      //   1703: ldc_w 540
      //   1706: new 542	java/text/SimpleDateFormat
      //   1709: dup
      //   1710: ldc_w 543
      //   1713: getstatic 535	java/util/Locale:ENGLISH	Ljava/util/Locale;
      //   1716: invokespecial 546	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
      //   1719: aload 6
      //   1721: invokevirtual 550	java/util/Calendar:getTime	()Ljava/util/Date;
      //   1724: invokevirtual 553	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
      //   1727: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1730: pop
      //   1731: aload 12
      //   1733: astore 7
      //   1735: aload 13
      //   1737: astore_1
      //   1738: aload 9
      //   1740: ldc_w 555
      //   1743: invokestatic 244	java/lang/System:currentTimeMillis	()J
      //   1746: ldc2_w 556
      //   1749: ldiv
      //   1750: invokevirtual 560	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   1753: pop
      //   1754: aload 12
      //   1756: astore 7
      //   1758: aload 13
      //   1760: astore_1
      //   1761: aload 9
      //   1763: ldc_w 562
      //   1766: ldc_w 564
      //   1769: invokestatic 567	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
      //   1772: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1775: pop
      //   1776: aload 12
      //   1778: astore 7
      //   1780: aload 13
      //   1782: astore_1
      //   1783: aload 9
      //   1785: ldc_w 569
      //   1788: invokestatic 571	com/appodeal/ads/al:a	()Z
      //   1791: invokevirtual 353	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   1794: pop
      //   1795: aload 12
      //   1797: astore 7
      //   1799: aload 13
      //   1801: astore_1
      //   1802: aload_0
      //   1803: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   1806: invokestatic 154	com/appodeal/ads/r:b	(Lcom/appodeal/ads/r;)Landroid/content/Context;
      //   1809: invokestatic 576	com/appodeal/ads/utils/b:c	(Landroid/content/Context;)V
      //   1812: aload 12
      //   1814: astore 7
      //   1816: aload 13
      //   1818: astore_1
      //   1819: aload 9
      //   1821: ldc_w 578
      //   1824: aload 14
      //   1826: invokestatic 581	com/appodeal/ads/utils/b:a	(Landroid/content/SharedPreferences;)J
      //   1829: invokevirtual 560	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   1832: pop
      //   1833: aload 12
      //   1835: astore 7
      //   1837: aload 13
      //   1839: astore_1
      //   1840: aload 9
      //   1842: ldc_w 583
      //   1845: invokestatic 585	com/appodeal/ads/utils/b:b	()J
      //   1848: invokevirtual 560	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   1851: pop
      //   1852: aload 12
      //   1854: astore 7
      //   1856: aload 13
      //   1858: astore_1
      //   1859: aload 9
      //   1861: ldc_w 587
      //   1864: aload 14
      //   1866: invokestatic 589	com/appodeal/ads/utils/b:b	(Landroid/content/SharedPreferences;)J
      //   1869: invokevirtual 560	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   1872: pop
      //   1873: aload 12
      //   1875: astore 7
      //   1877: aload 13
      //   1879: astore_1
      //   1880: aload 14
      //   1882: ldc_w 591
      //   1885: aconst_null
      //   1886: invokeinterface 260 3 0
      //   1891: astore 6
      //   1893: aload 6
      //   1895: ifnull +28 -> 1923
      //   1898: aload 12
      //   1900: astore 7
      //   1902: aload 13
      //   1904: astore_1
      //   1905: aload 9
      //   1907: ldc_w 591
      //   1910: new 34	org/json/JSONObject
      //   1913: dup
      //   1914: aload 6
      //   1916: invokespecial 593	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   1919: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1922: pop
      //   1923: aload 12
      //   1925: astore 7
      //   1927: aload 13
      //   1929: astore_1
      //   1930: aload_0
      //   1931: getfield 236	com/appodeal/ads/r$b:i	Z
      //   1934: ifeq +1833 -> 3767
      //   1937: aload 12
      //   1939: astore 7
      //   1941: aload 13
      //   1943: astore_1
      //   1944: new 145	org/json/JSONArray
      //   1947: dup
      //   1948: invokespecial 594	org/json/JSONArray:<init>	()V
      //   1951: astore 6
      //   1953: aload 12
      //   1955: astore 7
      //   1957: aload 13
      //   1959: astore_1
      //   1960: aload_0
      //   1961: getfield 107	com/appodeal/ads/r$b:b	Z
      //   1964: ifeq +1245 -> 3209
      //   1967: aload 12
      //   1969: astore 7
      //   1971: aload 13
      //   1973: astore_1
      //   1974: aload_0
      //   1975: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   1978: invokestatic 154	com/appodeal/ads/r:b	(Lcom/appodeal/ads/r;)Landroid/content/Context;
      //   1981: invokestatic 597	com/appodeal/ads/l:a	(Landroid/content/Context;)Ljava/util/Set;
      //   1984: invokeinterface 603 1 0
      //   1989: astore 8
      //   1991: aload 12
      //   1993: astore 7
      //   1995: aload 13
      //   1997: astore_1
      //   1998: aload 8
      //   2000: invokeinterface 608 1 0
      //   2005: ifeq +1204 -> 3209
      //   2008: aload 12
      //   2010: astore 7
      //   2012: aload 13
      //   2014: astore_1
      //   2015: aload 8
      //   2017: invokeinterface 612 1 0
      //   2022: checkcast 614	com/appodeal/ads/m
      //   2025: astore 10
      //   2027: aload 12
      //   2029: astore 7
      //   2031: aload 13
      //   2033: astore_1
      //   2034: aload 10
      //   2036: invokevirtual 617	com/appodeal/ads/m:h	()Lcom/appodeal/ads/p;
      //   2039: ifnull -48 -> 1991
      //   2042: aload 12
      //   2044: astore 7
      //   2046: aload 13
      //   2048: astore_1
      //   2049: aload 6
      //   2051: aload 10
      //   2053: invokevirtual 619	com/appodeal/ads/m:a	()Ljava/lang/String;
      //   2056: invokevirtual 622	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   2059: pop
      //   2060: goto -69 -> 1991
      //   2063: astore 6
      //   2065: aload 12
      //   2067: astore 7
      //   2069: aload 13
      //   2071: astore_1
      //   2072: aload 6
      //   2074: invokestatic 134	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2077: iload 4
      //   2079: ifeq +286 -> 2365
      //   2082: aload 13
      //   2084: astore_1
      //   2085: new 145	org/json/JSONArray
      //   2088: dup
      //   2089: invokespecial 594	org/json/JSONArray:<init>	()V
      //   2092: astore 6
      //   2094: aload 13
      //   2096: astore_1
      //   2097: aload 15
      //   2099: iconst_0
      //   2100: invokevirtual 626	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
      //   2103: astore 8
      //   2105: aload 13
      //   2107: astore_1
      //   2108: ldc_w 628
      //   2111: invokestatic 634	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
      //   2114: astore 7
      //   2116: aload 13
      //   2118: astore_1
      //   2119: aload 8
      //   2121: invokeinterface 637 1 0
      //   2126: astore 8
      //   2128: aload 13
      //   2130: astore_1
      //   2131: aload 8
      //   2133: invokeinterface 608 1 0
      //   2138: ifeq +1976 -> 4114
      //   2141: aload 13
      //   2143: astore_1
      //   2144: aload 8
      //   2146: invokeinterface 612 1 0
      //   2151: checkcast 411	android/content/pm/ApplicationInfo
      //   2154: getfield 640	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   2157: astore 10
      //   2159: aload 13
      //   2161: astore_1
      //   2162: aload 7
      //   2164: aload 10
      //   2166: invokevirtual 644	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
      //   2169: invokevirtual 649	java/util/regex/Matcher:matches	()Z
      //   2172: ifne -44 -> 2128
      //   2175: aload 13
      //   2177: astore_1
      //   2178: aload 10
      //   2180: ldc_w 365
      //   2183: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2186: ifne -58 -> 2128
      //   2189: aload 13
      //   2191: astore_1
      //   2192: aload 6
      //   2194: aload 10
      //   2196: invokevirtual 622	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   2199: pop
      //   2200: goto -72 -> 2128
      //   2203: astore 6
      //   2205: aload 12
      //   2207: astore 7
      //   2209: aload 13
      //   2211: astore_1
      //   2212: aload 6
      //   2214: invokestatic 134	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2217: aload 13
      //   2219: astore_1
      //   2220: aload 9
      //   2222: ldc_w 651
      //   2225: aload_0
      //   2226: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   2229: invokestatic 154	com/appodeal/ads/r:b	(Lcom/appodeal/ads/r;)Landroid/content/Context;
      //   2232: invokestatic 656	com/appodeal/ads/utils/n:a	(Landroid/content/Context;)Lorg/json/JSONArray;
      //   2235: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2238: pop
      //   2239: aload 13
      //   2241: astore_1
      //   2242: aload 9
      //   2244: ldc_w 658
      //   2247: aload_0
      //   2248: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   2251: invokestatic 154	com/appodeal/ads/r:b	(Lcom/appodeal/ads/r;)Landroid/content/Context;
      //   2254: invokestatic 662	com/appodeal/ads/utils/o:a	(Landroid/content/Context;)Ljava/lang/String;
      //   2257: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2260: pop
      //   2261: aload 13
      //   2263: astore_1
      //   2264: aload 9
      //   2266: ldc_w 664
      //   2269: aload_0
      //   2270: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   2273: invokestatic 154	com/appodeal/ads/r:b	(Lcom/appodeal/ads/r;)Landroid/content/Context;
      //   2276: invokestatic 668	com/appodeal/ads/Appodeal:getUserSettings	(Landroid/content/Context;)Lcom/appodeal/ads/UserSettings;
      //   2279: invokevirtual 670	com/appodeal/ads/UserSettings:a	()Lorg/json/JSONObject;
      //   2282: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2285: pop
      //   2286: aload 12
      //   2288: astore 7
      //   2290: aload 13
      //   2292: astore_1
      //   2293: aload 14
      //   2295: invokeinterface 311 1 0
      //   2300: astore 6
      //   2302: aload 12
      //   2304: astore 7
      //   2306: aload 13
      //   2308: astore_1
      //   2309: aload 6
      //   2311: ldc -41
      //   2313: invokestatic 244	java/lang/System:currentTimeMillis	()J
      //   2316: invokeinterface 674 4 0
      //   2321: pop
      //   2322: aload 12
      //   2324: astore 7
      //   2326: aload 13
      //   2328: astore_1
      //   2329: aload 6
      //   2331: ldc -10
      //   2333: iconst_0
      //   2334: invokeinterface 678 3 0
      //   2339: pop
      //   2340: aload 12
      //   2342: astore 7
      //   2344: aload 13
      //   2346: astore_1
      //   2347: aload 6
      //   2349: invokeinterface 320 1 0
      //   2354: aload 12
      //   2356: astore 7
      //   2358: aload 13
      //   2360: astore_1
      //   2361: iconst_0
      //   2362: putstatic 234	com/appodeal/ads/UserSettings:sendingInProgress	Z
      //   2365: aload 12
      //   2367: astore 7
      //   2369: aload 13
      //   2371: astore_1
      //   2372: aload_0
      //   2373: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   2376: invokestatic 681	com/appodeal/ads/r:i	(Lcom/appodeal/ads/r;)Lcom/appodeal/ads/d/h;
      //   2379: ifnull +55 -> 2434
      //   2382: aload 12
      //   2384: astore 7
      //   2386: aload 13
      //   2388: astore_1
      //   2389: aload_0
      //   2390: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   2393: invokestatic 274	com/appodeal/ads/r:a	(Lcom/appodeal/ads/r;)Ljava/lang/String;
      //   2396: ldc_w 683
      //   2399: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2402: ifeq +1763 -> 4165
      //   2405: aload 12
      //   2407: astore 7
      //   2409: aload 13
      //   2411: astore_1
      //   2412: aload 9
      //   2414: ldc_w 685
      //   2417: aload_0
      //   2418: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   2421: invokestatic 681	com/appodeal/ads/r:i	(Lcom/appodeal/ads/r;)Lcom/appodeal/ads/d/h;
      //   2424: invokevirtual 688	com/appodeal/ads/d/h:a	()Lorg/json/JSONObject;
      //   2427: invokevirtual 689	org/json/JSONObject:toString	()Ljava/lang/String;
      //   2430: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2433: pop
      //   2434: aload 12
      //   2436: astore 7
      //   2438: aload 13
      //   2440: astore_1
      //   2441: aload_0
      //   2442: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   2445: invokestatic 274	com/appodeal/ads/r:a	(Lcom/appodeal/ads/r;)Ljava/lang/String;
      //   2448: ldc_w 683
      //   2451: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2454: ifne +2395 -> 4849
      //   2457: aload 12
      //   2459: astore 7
      //   2461: aload 13
      //   2463: astore_1
      //   2464: aload_0
      //   2465: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   2468: invokestatic 274	com/appodeal/ads/r:a	(Lcom/appodeal/ads/r;)Ljava/lang/String;
      //   2471: ldc 80
      //   2473: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2476: ifne +2373 -> 4849
      //   2479: aload 12
      //   2481: astore 7
      //   2483: aload 13
      //   2485: astore_1
      //   2486: aload_0
      //   2487: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   2490: invokestatic 274	com/appodeal/ads/r:a	(Lcom/appodeal/ads/r;)Ljava/lang/String;
      //   2493: ldc 86
      //   2495: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2498: ifne +2351 -> 4849
      //   2501: aload 12
      //   2503: astore 7
      //   2505: aload 13
      //   2507: astore_1
      //   2508: aload_0
      //   2509: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   2512: invokestatic 274	com/appodeal/ads/r:a	(Lcom/appodeal/ads/r;)Ljava/lang/String;
      //   2515: ldc 105
      //   2517: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2520: ifne +2329 -> 4849
      //   2523: aload 12
      //   2525: astore 7
      //   2527: aload 13
      //   2529: astore_1
      //   2530: aload_0
      //   2531: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   2534: invokestatic 274	com/appodeal/ads/r:a	(Lcom/appodeal/ads/r;)Ljava/lang/String;
      //   2537: ldc_w 276
      //   2540: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2543: ifne +2306 -> 4849
      //   2546: iconst_1
      //   2547: istore 4
      //   2549: iload 4
      //   2551: ifeq +1834 -> 4385
      //   2554: aload 12
      //   2556: astore 7
      //   2558: aload 13
      //   2560: astore_1
      //   2561: new 691	java/net/URL
      //   2564: dup
      //   2565: ldc_w 693
      //   2568: iconst_4
      //   2569: anewarray 113	java/lang/Object
      //   2572: dup
      //   2573: iconst_0
      //   2574: invokestatic 697	com/appodeal/ads/utils/e:c	()I
      //   2577: invokestatic 700	com/appodeal/ads/utils/e:a	(I)Ljava/lang/String;
      //   2580: aastore
      //   2581: dup
      //   2582: iconst_1
      //   2583: invokestatic 702	com/appodeal/ads/utils/e:b	()Ljava/lang/String;
      //   2586: aastore
      //   2587: dup
      //   2588: iconst_2
      //   2589: invokestatic 697	com/appodeal/ads/utils/e:c	()I
      //   2592: invokestatic 707	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   2595: aastore
      //   2596: dup
      //   2597: iconst_3
      //   2598: ldc_w 709
      //   2601: aastore
      //   2602: invokestatic 117	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   2605: invokespecial 710	java/net/URL:<init>	(Ljava/lang/String;)V
      //   2608: astore 8
      //   2610: aload 12
      //   2612: astore 7
      //   2614: aload 13
      //   2616: astore_1
      //   2617: aload 8
      //   2619: invokevirtual 714	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   2622: astore 6
      //   2624: aload 8
      //   2626: invokevirtual 717	java/net/URL:getProtocol	()Ljava/lang/String;
      //   2629: ldc_w 719
      //   2632: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2635: ifeq +79 -> 2714
      //   2638: new 721	java/util/ArrayList
      //   2641: dup
      //   2642: invokespecial 722	java/util/ArrayList:<init>	()V
      //   2645: astore_1
      //   2646: aload_1
      //   2647: ldc_w 724
      //   2650: invokeinterface 726 2 0
      //   2655: pop
      //   2656: aload_1
      //   2657: ldc_w 728
      //   2660: invokeinterface 726 2 0
      //   2665: pop
      //   2666: new 730	com/appodeal/ads/utils/c
      //   2669: dup
      //   2670: aload_1
      //   2671: ldc2_w 731
      //   2674: invokespecial 735	com/appodeal/ads/utils/c:<init>	(Ljava/util/List;J)V
      //   2677: astore_1
      //   2678: ldc_w 737
      //   2681: invokestatic 742	javax/net/ssl/SSLContext:getInstance	(Ljava/lang/String;)Ljavax/net/ssl/SSLContext;
      //   2684: astore 7
      //   2686: aload 7
      //   2688: aconst_null
      //   2689: iconst_1
      //   2690: anewarray 744	javax/net/ssl/TrustManager
      //   2693: dup
      //   2694: iconst_0
      //   2695: aload_1
      //   2696: aastore
      //   2697: aconst_null
      //   2698: invokevirtual 748	javax/net/ssl/SSLContext:init	([Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Ljava/security/SecureRandom;)V
      //   2701: aload 6
      //   2703: checkcast 262	javax/net/ssl/HttpsURLConnection
      //   2706: aload 7
      //   2708: invokevirtual 752	javax/net/ssl/SSLContext:getSocketFactory	()Ljavax/net/ssl/SSLSocketFactory;
      //   2711: invokevirtual 756	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
      //   2714: aload 6
      //   2716: sipush 20000
      //   2719: invokevirtual 762	java/net/URLConnection:setConnectTimeout	(I)V
      //   2722: aload 6
      //   2724: sipush 20000
      //   2727: invokevirtual 765	java/net/URLConnection:setReadTimeout	(I)V
      //   2730: aload 6
      //   2732: ldc_w 767
      //   2735: ldc_w 769
      //   2738: invokevirtual 773	java/net/URLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   2741: aload 6
      //   2743: iconst_1
      //   2744: invokevirtual 777	java/net/URLConnection:setDoOutput	(Z)V
      //   2747: new 779	java/io/ByteArrayOutputStream
      //   2750: dup
      //   2751: invokespecial 780	java/io/ByteArrayOutputStream:<init>	()V
      //   2754: astore 7
      //   2756: new 782	java/util/zip/GZIPOutputStream
      //   2759: dup
      //   2760: aload 7
      //   2762: invokespecial 785	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   2765: astore_1
      //   2766: aload_1
      //   2767: aload 9
      //   2769: invokevirtual 689	org/json/JSONObject:toString	()Ljava/lang/String;
      //   2772: ldc_w 787
      //   2775: invokevirtual 791	java/lang/String:getBytes	(Ljava/lang/String;)[B
      //   2778: invokevirtual 795	java/util/zip/GZIPOutputStream:write	([B)V
      //   2781: aload_1
      //   2782: invokevirtual 798	java/util/zip/GZIPOutputStream:close	()V
      //   2785: aload 7
      //   2787: invokevirtual 802	java/io/ByteArrayOutputStream:toByteArray	()[B
      //   2790: iconst_0
      //   2791: invokestatic 808	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
      //   2794: astore_1
      //   2795: aload 6
      //   2797: invokevirtual 812	java/net/URLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   2800: aload_1
      //   2801: invokestatic 815	com/appodeal/ads/al:a	(Ljava/io/OutputStream;Ljava/lang/String;)V
      //   2804: aload 6
      //   2806: invokevirtual 819	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
      //   2809: invokestatic 822	com/appodeal/ads/al:a	(Ljava/io/InputStream;)Ljava/lang/String;
      //   2812: astore_1
      //   2813: aload_1
      //   2814: ifnull +40 -> 2854
      //   2817: aload_1
      //   2818: invokevirtual 825	java/lang/String:isEmpty	()Z
      //   2821: ifne +33 -> 2854
      //   2824: aload_1
      //   2825: ldc_w 827
      //   2828: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2831: ifne +23 -> 2854
      //   2834: aload_0
      //   2835: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   2838: invokestatic 274	com/appodeal/ads/r:a	(Lcom/appodeal/ads/r;)Ljava/lang/String;
      //   2841: ldc_w 683
      //   2844: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2847: istore 5
      //   2849: iload 5
      //   2851: ifeq +5 -> 2856
      //   2854: aconst_null
      //   2855: astore_1
      //   2856: aload_1
      //   2857: astore 8
      //   2859: aload 6
      //   2861: astore 7
      //   2863: aload 6
      //   2865: astore_1
      //   2866: aload_0
      //   2867: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   2870: invokestatic 154	com/appodeal/ads/r:b	(Lcom/appodeal/ads/r;)Landroid/content/Context;
      //   2873: ldc_w 829
      //   2876: iconst_0
      //   2877: invokevirtual 207	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   2880: astore 9
      //   2882: aload 8
      //   2884: ifnonnull +1723 -> 4607
      //   2887: iload 4
      //   2889: ifeq +1676 -> 4565
      //   2892: aload 6
      //   2894: astore 7
      //   2896: aload 6
      //   2898: astore_1
      //   2899: aload 9
      //   2901: aload_0
      //   2902: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   2905: invokestatic 274	com/appodeal/ads/r:a	(Lcom/appodeal/ads/r;)Ljava/lang/String;
      //   2908: invokeinterface 833 2 0
      //   2913: ifeq +1652 -> 4565
      //   2916: aload 6
      //   2918: astore 7
      //   2920: aload 6
      //   2922: astore_1
      //   2923: new 835	com/appodeal/ads/utils/b/a
      //   2926: dup
      //   2927: ldc_w 837
      //   2930: invokespecial 838	com/appodeal/ads/utils/b/a:<init>	(Ljava/lang/String;)V
      //   2933: invokestatic 134	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2936: aload 6
      //   2938: astore 7
      //   2940: aload 6
      //   2942: astore_1
      //   2943: aload 9
      //   2945: aload_0
      //   2946: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   2949: invokestatic 274	com/appodeal/ads/r:a	(Lcom/appodeal/ads/r;)Ljava/lang/String;
      //   2952: ldc_w 840
      //   2955: invokeinterface 260 3 0
      //   2960: astore 8
      //   2962: aload 6
      //   2964: astore 7
      //   2966: aload 6
      //   2968: astore_1
      //   2969: new 34	org/json/JSONObject
      //   2972: dup
      //   2973: aload 8
      //   2975: invokespecial 593	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   2978: astore 8
      //   2980: aload 6
      //   2982: astore 7
      //   2984: aload 6
      //   2986: astore_1
      //   2987: aload 8
      //   2989: invokestatic 842	com/appodeal/ads/Appodeal:a	(Lorg/json/JSONObject;)V
      //   2992: aload 6
      //   2994: astore_1
      //   2995: aload 8
      //   2997: ldc_w 844
      //   3000: invokevirtual 848	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   3003: putstatic 852	com/appodeal/ads/UserSettings:userData	Lorg/json/JSONObject;
      //   3006: aload 6
      //   3008: astore_1
      //   3009: getstatic 183	com/appodeal/ads/f/g:a	Ljava/lang/Long;
      //   3012: ifnonnull +12 -> 3024
      //   3015: aload 6
      //   3017: astore_1
      //   3018: aload_0
      //   3019: aload 8
      //   3021: invokespecial 854	com/appodeal/ads/r$b:b	(Lorg/json/JSONObject;)V
      //   3024: aload 6
      //   3026: ifnull +19 -> 3045
      //   3029: aload 6
      //   3031: instanceof 262
      //   3034: ifeq +1708 -> 4742
      //   3037: aload 6
      //   3039: checkcast 262	javax/net/ssl/HttpsURLConnection
      //   3042: invokevirtual 265	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   3045: aload 8
      //   3047: areturn
      //   3048: ldc_w 856
      //   3051: astore 6
      //   3053: goto -2612 -> 441
      //   3056: astore 6
      //   3058: aload 12
      //   3060: astore 7
      //   3062: aload 13
      //   3064: astore_1
      //   3065: aload 6
      //   3067: invokestatic 134	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   3070: aload 9
      //   3072: astore_1
      //   3073: aload 8
      //   3075: astore 6
      //   3077: goto -2518 -> 559
      //   3080: astore 16
      //   3082: aload 12
      //   3084: astore 7
      //   3086: aload 13
      //   3088: astore_1
      //   3089: aload 16
      //   3091: invokestatic 134	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   3094: goto -1978 -> 1116
      //   3097: astore 6
      //   3099: aload 7
      //   3101: astore_1
      //   3102: aload 6
      //   3104: invokestatic 134	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   3107: aload 7
      //   3109: ifnull +19 -> 3128
      //   3112: aload 7
      //   3114: instanceof 262
      //   3117: ifeq +1644 -> 4761
      //   3120: aload 7
      //   3122: checkcast 262	javax/net/ssl/HttpsURLConnection
      //   3125: invokevirtual 265	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   3128: aconst_null
      //   3129: areturn
      //   3130: astore 16
      //   3132: aload 12
      //   3134: astore 7
      //   3136: aload 13
      //   3138: astore_1
      //   3139: aload 16
      //   3141: invokestatic 134	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   3144: goto -1997 -> 1147
      //   3147: astore 6
      //   3149: aload_1
      //   3150: ifnull +17 -> 3167
      //   3153: aload_1
      //   3154: instanceof 262
      //   3157: ifeq +1623 -> 4780
      //   3160: aload_1
      //   3161: checkcast 262	javax/net/ssl/HttpsURLConnection
      //   3164: invokevirtual 265	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   3167: aload 6
      //   3169: athrow
      //   3170: aload 12
      //   3172: astore 7
      //   3174: aload 13
      //   3176: astore_1
      //   3177: aload 9
      //   3179: ldc_w 464
      //   3182: ldc_w 858
      //   3185: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3188: pop
      //   3189: goto -1839 -> 1350
      //   3192: astore 6
      //   3194: aload 12
      //   3196: astore 7
      //   3198: aload 13
      //   3200: astore_1
      //   3201: aload 6
      //   3203: invokestatic 134	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   3206: goto -1774 -> 1432
      //   3209: aload 12
      //   3211: astore 7
      //   3213: aload 13
      //   3215: astore_1
      //   3216: aload_0
      //   3217: getfield 88	com/appodeal/ads/r$b:e	Z
      //   3220: ifeq +99 -> 3319
      //   3223: aload 12
      //   3225: astore 7
      //   3227: aload 13
      //   3229: astore_1
      //   3230: aload_0
      //   3231: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   3234: invokestatic 154	com/appodeal/ads/r:b	(Lcom/appodeal/ads/r;)Landroid/content/Context;
      //   3237: invokestatic 859	com/appodeal/ads/af:a	(Landroid/content/Context;)Ljava/util/Set;
      //   3240: invokeinterface 603 1 0
      //   3245: astore 8
      //   3247: aload 12
      //   3249: astore 7
      //   3251: aload 13
      //   3253: astore_1
      //   3254: aload 8
      //   3256: invokeinterface 608 1 0
      //   3261: ifeq +58 -> 3319
      //   3264: aload 12
      //   3266: astore 7
      //   3268: aload 13
      //   3270: astore_1
      //   3271: aload 8
      //   3273: invokeinterface 612 1 0
      //   3278: checkcast 861	com/appodeal/ads/an
      //   3281: astore 10
      //   3283: aload 12
      //   3285: astore 7
      //   3287: aload 13
      //   3289: astore_1
      //   3290: aload 10
      //   3292: invokevirtual 864	com/appodeal/ads/an:h	()Lcom/appodeal/ads/ao;
      //   3295: ifnull -48 -> 3247
      //   3298: aload 12
      //   3300: astore 7
      //   3302: aload 13
      //   3304: astore_1
      //   3305: aload 6
      //   3307: aload 10
      //   3309: invokevirtual 865	com/appodeal/ads/an:a	()Ljava/lang/String;
      //   3312: invokevirtual 622	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   3315: pop
      //   3316: goto -69 -> 3247
      //   3319: aload 12
      //   3321: astore 7
      //   3323: aload 13
      //   3325: astore_1
      //   3326: aload_0
      //   3327: getfield 90	com/appodeal/ads/r$b:f	Z
      //   3330: ifeq +99 -> 3429
      //   3333: aload 12
      //   3335: astore 7
      //   3337: aload 13
      //   3339: astore_1
      //   3340: aload_0
      //   3341: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   3344: invokestatic 154	com/appodeal/ads/r:b	(Lcom/appodeal/ads/r;)Landroid/content/Context;
      //   3347: invokestatic 866	com/appodeal/ads/ai:a	(Landroid/content/Context;)Ljava/util/Set;
      //   3350: invokeinterface 603 1 0
      //   3355: astore 8
      //   3357: aload 12
      //   3359: astore 7
      //   3361: aload 13
      //   3363: astore_1
      //   3364: aload 8
      //   3366: invokeinterface 608 1 0
      //   3371: ifeq +58 -> 3429
      //   3374: aload 12
      //   3376: astore 7
      //   3378: aload 13
      //   3380: astore_1
      //   3381: aload 8
      //   3383: invokeinterface 612 1 0
      //   3388: checkcast 861	com/appodeal/ads/an
      //   3391: astore 10
      //   3393: aload 12
      //   3395: astore 7
      //   3397: aload 13
      //   3399: astore_1
      //   3400: aload 10
      //   3402: invokevirtual 864	com/appodeal/ads/an:h	()Lcom/appodeal/ads/ao;
      //   3405: ifnull -48 -> 3357
      //   3408: aload 12
      //   3410: astore 7
      //   3412: aload 13
      //   3414: astore_1
      //   3415: aload 6
      //   3417: aload 10
      //   3419: invokevirtual 865	com/appodeal/ads/an:a	()Ljava/lang/String;
      //   3422: invokevirtual 622	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   3425: pop
      //   3426: goto -69 -> 3357
      //   3429: aload 12
      //   3431: astore 7
      //   3433: aload 13
      //   3435: astore_1
      //   3436: aload_0
      //   3437: getfield 119	com/appodeal/ads/r$b:c	Z
      //   3440: ifeq +99 -> 3539
      //   3443: aload 12
      //   3445: astore 7
      //   3447: aload 13
      //   3449: astore_1
      //   3450: aload_0
      //   3451: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   3454: invokestatic 154	com/appodeal/ads/r:b	(Lcom/appodeal/ads/r;)Landroid/content/Context;
      //   3457: invokestatic 867	com/appodeal/ads/d:a	(Landroid/content/Context;)Ljava/util/Set;
      //   3460: invokeinterface 603 1 0
      //   3465: astore 8
      //   3467: aload 12
      //   3469: astore 7
      //   3471: aload 13
      //   3473: astore_1
      //   3474: aload 8
      //   3476: invokeinterface 608 1 0
      //   3481: ifeq +58 -> 3539
      //   3484: aload 12
      //   3486: astore 7
      //   3488: aload 13
      //   3490: astore_1
      //   3491: aload 8
      //   3493: invokeinterface 612 1 0
      //   3498: checkcast 869	com/appodeal/ads/e
      //   3501: astore 10
      //   3503: aload 12
      //   3505: astore 7
      //   3507: aload 13
      //   3509: astore_1
      //   3510: aload 10
      //   3512: invokevirtual 872	com/appodeal/ads/e:f	()Lcom/appodeal/ads/h;
      //   3515: ifnull -48 -> 3467
      //   3518: aload 12
      //   3520: astore 7
      //   3522: aload 13
      //   3524: astore_1
      //   3525: aload 6
      //   3527: aload 10
      //   3529: invokevirtual 873	com/appodeal/ads/e:a	()Ljava/lang/String;
      //   3532: invokevirtual 622	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   3535: pop
      //   3536: goto -69 -> 3467
      //   3539: aload 12
      //   3541: astore 7
      //   3543: aload 13
      //   3545: astore_1
      //   3546: aload_0
      //   3547: getfield 123	com/appodeal/ads/r$b:d	Z
      //   3550: ifeq +99 -> 3649
      //   3553: aload 12
      //   3555: astore 7
      //   3557: aload 13
      //   3559: astore_1
      //   3560: aload_0
      //   3561: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   3564: invokestatic 154	com/appodeal/ads/r:b	(Lcom/appodeal/ads/r;)Landroid/content/Context;
      //   3567: invokestatic 874	com/appodeal/ads/s:a	(Landroid/content/Context;)Ljava/util/Set;
      //   3570: invokeinterface 603 1 0
      //   3575: astore 8
      //   3577: aload 12
      //   3579: astore 7
      //   3581: aload 13
      //   3583: astore_1
      //   3584: aload 8
      //   3586: invokeinterface 608 1 0
      //   3591: ifeq +58 -> 3649
      //   3594: aload 12
      //   3596: astore 7
      //   3598: aload 13
      //   3600: astore_1
      //   3601: aload 8
      //   3603: invokeinterface 612 1 0
      //   3608: checkcast 876	com/appodeal/ads/t
      //   3611: astore 10
      //   3613: aload 12
      //   3615: astore 7
      //   3617: aload 13
      //   3619: astore_1
      //   3620: aload 10
      //   3622: invokevirtual 879	com/appodeal/ads/t:f	()Lcom/appodeal/ads/w;
      //   3625: ifnull -48 -> 3577
      //   3628: aload 12
      //   3630: astore 7
      //   3632: aload 13
      //   3634: astore_1
      //   3635: aload 6
      //   3637: aload 10
      //   3639: invokevirtual 880	com/appodeal/ads/t:a	()Ljava/lang/String;
      //   3642: invokevirtual 622	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   3645: pop
      //   3646: goto -69 -> 3577
      //   3649: aload 12
      //   3651: astore 7
      //   3653: aload 13
      //   3655: astore_1
      //   3656: aload_0
      //   3657: getfield 127	com/appodeal/ads/r$b:g	Z
      //   3660: ifeq +89 -> 3749
      //   3663: aload 12
      //   3665: astore 7
      //   3667: aload 13
      //   3669: astore_1
      //   3670: aload_0
      //   3671: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   3674: invokestatic 154	com/appodeal/ads/r:b	(Lcom/appodeal/ads/r;)Landroid/content/Context;
      //   3677: invokestatic 881	com/appodeal/ads/y:a	(Landroid/content/Context;)Ljava/util/Set;
      //   3680: invokeinterface 603 1 0
      //   3685: astore 8
      //   3687: aload 12
      //   3689: astore 7
      //   3691: aload 13
      //   3693: astore_1
      //   3694: aload 8
      //   3696: invokeinterface 608 1 0
      //   3701: ifeq +48 -> 3749
      //   3704: aload 12
      //   3706: astore 7
      //   3708: aload 13
      //   3710: astore_1
      //   3711: aload 8
      //   3713: invokeinterface 612 1 0
      //   3718: checkcast 883	com/appodeal/ads/aa
      //   3721: astore 10
      //   3723: aload 10
      //   3725: ifnull -38 -> 3687
      //   3728: aload 12
      //   3730: astore 7
      //   3732: aload 13
      //   3734: astore_1
      //   3735: aload 6
      //   3737: aload 10
      //   3739: invokevirtual 884	com/appodeal/ads/aa:a	()Ljava/lang/String;
      //   3742: invokevirtual 622	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   3745: pop
      //   3746: goto -59 -> 3687
      //   3749: aload 12
      //   3751: astore 7
      //   3753: aload 13
      //   3755: astore_1
      //   3756: aload 9
      //   3758: ldc_w 886
      //   3761: aload 6
      //   3763: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3766: pop
      //   3767: aload 12
      //   3769: astore 7
      //   3771: aload 13
      //   3773: astore_1
      //   3774: aload_0
      //   3775: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   3778: invokestatic 888	com/appodeal/ads/r:c	(Lcom/appodeal/ads/r;)Ljava/lang/String;
      //   3781: ifnull +26 -> 3807
      //   3784: aload 12
      //   3786: astore 7
      //   3788: aload 13
      //   3790: astore_1
      //   3791: aload 9
      //   3793: ldc_w 890
      //   3796: aload_0
      //   3797: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   3800: invokestatic 888	com/appodeal/ads/r:c	(Lcom/appodeal/ads/r;)Ljava/lang/String;
      //   3803: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3806: pop
      //   3807: aload 12
      //   3809: astore 7
      //   3811: aload 13
      //   3813: astore_1
      //   3814: getstatic 183	com/appodeal/ads/f/g:a	Ljava/lang/Long;
      //   3817: ifnull +22 -> 3839
      //   3820: aload 12
      //   3822: astore 7
      //   3824: aload 13
      //   3826: astore_1
      //   3827: aload 9
      //   3829: ldc_w 892
      //   3832: getstatic 183	com/appodeal/ads/f/g:a	Ljava/lang/Long;
      //   3835: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3838: pop
      //   3839: aload 12
      //   3841: astore 7
      //   3843: aload 13
      //   3845: astore_1
      //   3846: aload_0
      //   3847: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   3850: invokestatic 895	com/appodeal/ads/r:d	(Lcom/appodeal/ads/r;)J
      //   3853: lconst_0
      //   3854: lcmp
      //   3855: ifeq +26 -> 3881
      //   3858: aload 12
      //   3860: astore 7
      //   3862: aload 13
      //   3864: astore_1
      //   3865: aload 9
      //   3867: ldc_w 897
      //   3870: aload_0
      //   3871: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   3874: invokestatic 895	com/appodeal/ads/r:d	(Lcom/appodeal/ads/r;)J
      //   3877: invokevirtual 560	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   3880: pop
      //   3881: aload 12
      //   3883: astore 7
      //   3885: aload 13
      //   3887: astore_1
      //   3888: aload_0
      //   3889: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   3892: invokestatic 274	com/appodeal/ads/r:a	(Lcom/appodeal/ads/r;)Ljava/lang/String;
      //   3895: ldc 86
      //   3897: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3900: ifeq +26 -> 3926
      //   3903: aload 12
      //   3905: astore 7
      //   3907: aload 13
      //   3909: astore_1
      //   3910: aload 9
      //   3912: ldc_w 899
      //   3915: invokestatic 244	java/lang/System:currentTimeMillis	()J
      //   3918: ldc2_w 556
      //   3921: ldiv
      //   3922: invokevirtual 560	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   3925: pop
      //   3926: aload 12
      //   3928: astore 7
      //   3930: aload 13
      //   3932: astore_1
      //   3933: aload_0
      //   3934: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   3937: invokestatic 274	com/appodeal/ads/r:a	(Lcom/appodeal/ads/r;)Ljava/lang/String;
      //   3940: ldc 105
      //   3942: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3945: ifeq +26 -> 3971
      //   3948: aload 12
      //   3950: astore 7
      //   3952: aload 13
      //   3954: astore_1
      //   3955: aload 9
      //   3957: ldc_w 901
      //   3960: invokestatic 244	java/lang/System:currentTimeMillis	()J
      //   3963: ldc2_w 556
      //   3966: ldiv
      //   3967: invokevirtual 560	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   3970: pop
      //   3971: aload 12
      //   3973: astore 7
      //   3975: aload 13
      //   3977: astore_1
      //   3978: aload 9
      //   3980: ldc_w 685
      //   3983: aload_0
      //   3984: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   3987: invokestatic 903	com/appodeal/ads/r:e	(Lcom/appodeal/ads/r;)Ljava/lang/String;
      //   3990: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3993: pop
      //   3994: aload 12
      //   3996: astore 7
      //   3998: aload 13
      //   4000: astore_1
      //   4001: aload 9
      //   4003: ldc_w 905
      //   4006: aload_0
      //   4007: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   4010: invokestatic 907	com/appodeal/ads/r:f	(Lcom/appodeal/ads/r;)Ljava/lang/String;
      //   4013: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   4016: pop
      //   4017: aload 12
      //   4019: astore 7
      //   4021: aload 13
      //   4023: astore_1
      //   4024: aload_0
      //   4025: getfield 236	com/appodeal/ads/r$b:i	Z
      //   4028: ifne +20 -> 4048
      //   4031: aload 12
      //   4033: astore 7
      //   4035: aload 13
      //   4037: astore_1
      //   4038: aload_0
      //   4039: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   4042: invokestatic 93	com/appodeal/ads/r:g	(Lcom/appodeal/ads/r;)Ljava/lang/String;
      //   4045: ifnull +23 -> 4068
      //   4048: aload 12
      //   4050: astore 7
      //   4052: aload 13
      //   4054: astore_1
      //   4055: aload 9
      //   4057: ldc_w 909
      //   4060: aload_0
      //   4061: invokespecial 910	com/appodeal/ads/r$b:a	()Lorg/json/JSONObject;
      //   4064: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   4067: pop
      //   4068: aload 12
      //   4070: astore 7
      //   4072: aload 13
      //   4074: astore_1
      //   4075: aload_0
      //   4076: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   4079: invokestatic 913	com/appodeal/ads/r:h	(Lcom/appodeal/ads/r;)Lcom/appodeal/ads/f/c;
      //   4082: ifnull -2005 -> 2077
      //   4085: aload 12
      //   4087: astore 7
      //   4089: aload 13
      //   4091: astore_1
      //   4092: aload 9
      //   4094: ldc_w 915
      //   4097: aload_0
      //   4098: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   4101: invokestatic 913	com/appodeal/ads/r:h	(Lcom/appodeal/ads/r;)Lcom/appodeal/ads/f/c;
      //   4104: invokevirtual 919	com/appodeal/ads/f/c:a	()I
      //   4107: invokevirtual 84	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
      //   4110: pop
      //   4111: goto -2034 -> 2077
      //   4114: aload 13
      //   4116: astore_1
      //   4117: aload 9
      //   4119: ldc_w 921
      //   4122: aload 6
      //   4124: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   4127: pop
      //   4128: goto -1911 -> 2217
      //   4131: astore 6
      //   4133: aload 12
      //   4135: astore 7
      //   4137: aload 13
      //   4139: astore_1
      //   4140: aload 6
      //   4142: invokestatic 134	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   4145: goto -1884 -> 2261
      //   4148: astore 6
      //   4150: aload 12
      //   4152: astore 7
      //   4154: aload 13
      //   4156: astore_1
      //   4157: aload 6
      //   4159: invokestatic 134	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   4162: goto -1876 -> 2286
      //   4165: aload 12
      //   4167: astore 7
      //   4169: aload 13
      //   4171: astore_1
      //   4172: aload_0
      //   4173: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   4176: invokestatic 274	com/appodeal/ads/r:a	(Lcom/appodeal/ads/r;)Ljava/lang/String;
      //   4179: ldc 80
      //   4181: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   4184: ifeq +128 -> 4312
      //   4187: aload 12
      //   4189: astore 7
      //   4191: aload 13
      //   4193: astore_1
      //   4194: aload_0
      //   4195: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   4198: invokestatic 681	com/appodeal/ads/r:i	(Lcom/appodeal/ads/r;)Lcom/appodeal/ads/d/h;
      //   4201: invokevirtual 923	com/appodeal/ads/d/h:f	()Z
      //   4204: ifne +53 -> 4257
      //   4207: aload 12
      //   4209: astore 7
      //   4211: aload 13
      //   4213: astore_1
      //   4214: ldc_w 925
      //   4217: invokestatic 325	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   4220: iconst_0
      //   4221: ifeq +17 -> 4238
      //   4224: aconst_null
      //   4225: instanceof 262
      //   4228: ifeq +12 -> 4240
      //   4231: aconst_null
      //   4232: checkcast 262	javax/net/ssl/HttpsURLConnection
      //   4235: invokevirtual 265	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   4238: aconst_null
      //   4239: areturn
      //   4240: aconst_null
      //   4241: instanceof 267
      //   4244: ifeq -6 -> 4238
      //   4247: aconst_null
      //   4248: checkcast 267	java/net/HttpURLConnection
      //   4251: invokevirtual 268	java/net/HttpURLConnection:disconnect	()V
      //   4254: goto -16 -> 4238
      //   4257: aload 12
      //   4259: astore 7
      //   4261: aload 13
      //   4263: astore_1
      //   4264: aload 9
      //   4266: ldc_w 927
      //   4269: aload_0
      //   4270: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   4273: invokestatic 681	com/appodeal/ads/r:i	(Lcom/appodeal/ads/r;)Lcom/appodeal/ads/d/h;
      //   4276: invokevirtual 929	com/appodeal/ads/d/h:e	()Lorg/json/JSONObject;
      //   4279: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   4282: pop
      //   4283: aload 12
      //   4285: astore 7
      //   4287: aload 13
      //   4289: astore_1
      //   4290: aload 9
      //   4292: ldc_w 931
      //   4295: aload_0
      //   4296: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   4299: invokestatic 681	com/appodeal/ads/r:i	(Lcom/appodeal/ads/r;)Lcom/appodeal/ads/d/h;
      //   4302: invokevirtual 933	com/appodeal/ads/d/h:g	()Ljava/lang/String;
      //   4305: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   4308: pop
      //   4309: goto -1875 -> 2434
      //   4312: aload 12
      //   4314: astore 7
      //   4316: aload 13
      //   4318: astore_1
      //   4319: aload_0
      //   4320: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   4323: invokestatic 274	com/appodeal/ads/r:a	(Lcom/appodeal/ads/r;)Ljava/lang/String;
      //   4326: ldc 86
      //   4328: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   4331: ifne +25 -> 4356
      //   4334: aload 12
      //   4336: astore 7
      //   4338: aload 13
      //   4340: astore_1
      //   4341: aload_0
      //   4342: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   4345: invokestatic 274	com/appodeal/ads/r:a	(Lcom/appodeal/ads/r;)Ljava/lang/String;
      //   4348: ldc 105
      //   4350: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   4353: ifeq -1919 -> 2434
      //   4356: aload 12
      //   4358: astore 7
      //   4360: aload 13
      //   4362: astore_1
      //   4363: aload 9
      //   4365: ldc_w 685
      //   4368: aload_0
      //   4369: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   4372: invokestatic 681	com/appodeal/ads/r:i	(Lcom/appodeal/ads/r;)Lcom/appodeal/ads/d/h;
      //   4375: invokevirtual 933	com/appodeal/ads/d/h:g	()Ljava/lang/String;
      //   4378: invokevirtual 336	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   4381: pop
      //   4382: goto -1948 -> 2434
      //   4385: aload 12
      //   4387: astore 7
      //   4389: aload 13
      //   4391: astore_1
      //   4392: new 691	java/net/URL
      //   4395: dup
      //   4396: ldc_w 693
      //   4399: iconst_4
      //   4400: anewarray 113	java/lang/Object
      //   4403: dup
      //   4404: iconst_0
      //   4405: invokestatic 697	com/appodeal/ads/utils/e:c	()I
      //   4408: invokestatic 700	com/appodeal/ads/utils/e:a	(I)Ljava/lang/String;
      //   4411: aastore
      //   4412: dup
      //   4413: iconst_1
      //   4414: invokestatic 702	com/appodeal/ads/utils/e:b	()Ljava/lang/String;
      //   4417: aastore
      //   4418: dup
      //   4419: iconst_2
      //   4420: invokestatic 697	com/appodeal/ads/utils/e:c	()I
      //   4423: invokestatic 707	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   4426: aastore
      //   4427: dup
      //   4428: iconst_3
      //   4429: aload_0
      //   4430: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   4433: invokestatic 274	com/appodeal/ads/r:a	(Lcom/appodeal/ads/r;)Ljava/lang/String;
      //   4436: aastore
      //   4437: invokestatic 117	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   4440: invokespecial 710	java/net/URL:<init>	(Ljava/lang/String;)V
      //   4443: astore 8
      //   4445: goto -1835 -> 2610
      //   4448: astore 7
      //   4450: aload_1
      //   4451: invokevirtual 798	java/util/zip/GZIPOutputStream:close	()V
      //   4454: aload 7
      //   4456: athrow
      //   4457: astore 8
      //   4459: aload 6
      //   4461: astore 7
      //   4463: aload 6
      //   4465: astore_1
      //   4466: aload 8
      //   4468: invokestatic 134	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   4471: aload 6
      //   4473: astore 7
      //   4475: aload 6
      //   4477: astore_1
      //   4478: aload 8
      //   4480: invokevirtual 936	java/io/IOException:getMessage	()Ljava/lang/String;
      //   4483: ldc_w 938
      //   4486: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   4489: ifne +28 -> 4517
      //   4492: aload 6
      //   4494: astore 7
      //   4496: aload 6
      //   4498: astore_1
      //   4499: aload 8
      //   4501: invokevirtual 936	java/io/IOException:getMessage	()Ljava/lang/String;
      //   4504: ldc_w 940
      //   4507: invokevirtual 101	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   4510: istore 5
      //   4512: iload 5
      //   4514: ifeq +45 -> 4559
      //   4517: aload 6
      //   4519: ifnull +19 -> 4538
      //   4522: aload 6
      //   4524: instanceof 262
      //   4527: ifeq +13 -> 4540
      //   4530: aload 6
      //   4532: checkcast 262	javax/net/ssl/HttpsURLConnection
      //   4535: invokevirtual 265	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   4538: aconst_null
      //   4539: areturn
      //   4540: aload 6
      //   4542: instanceof 267
      //   4545: ifeq -7 -> 4538
      //   4548: aload 6
      //   4550: checkcast 267	java/net/HttpURLConnection
      //   4553: invokevirtual 268	java/net/HttpURLConnection:disconnect	()V
      //   4556: goto -18 -> 4538
      //   4559: aconst_null
      //   4560: astore 8
      //   4562: goto -1703 -> 2859
      //   4565: aload 6
      //   4567: ifnull +19 -> 4586
      //   4570: aload 6
      //   4572: instanceof 262
      //   4575: ifeq +13 -> 4588
      //   4578: aload 6
      //   4580: checkcast 262	javax/net/ssl/HttpsURLConnection
      //   4583: invokevirtual 265	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   4586: aconst_null
      //   4587: areturn
      //   4588: aload 6
      //   4590: instanceof 267
      //   4593: ifeq -7 -> 4586
      //   4596: aload 6
      //   4598: checkcast 267	java/net/HttpURLConnection
      //   4601: invokevirtual 268	java/net/HttpURLConnection:disconnect	()V
      //   4604: goto -18 -> 4586
      //   4607: iload 4
      //   4609: ifeq +246 -> 4855
      //   4612: aload 6
      //   4614: astore 7
      //   4616: aload 6
      //   4618: astore_1
      //   4619: aload 9
      //   4621: invokeinterface 311 1 0
      //   4626: astore 9
      //   4628: aload 6
      //   4630: astore 7
      //   4632: aload 6
      //   4634: astore_1
      //   4635: aload 9
      //   4637: aload_0
      //   4638: getfield 22	com/appodeal/ads/r$b:a	Lcom/appodeal/ads/r;
      //   4641: invokestatic 274	com/appodeal/ads/r:a	(Lcom/appodeal/ads/r;)Ljava/lang/String;
      //   4644: aload 8
      //   4646: invokeinterface 317 3 0
      //   4651: pop
      //   4652: aload 6
      //   4654: astore 7
      //   4656: aload 6
      //   4658: astore_1
      //   4659: aload 9
      //   4661: invokeinterface 320 1 0
      //   4666: goto +189 -> 4855
      //   4669: astore 8
      //   4671: aload 6
      //   4673: astore 7
      //   4675: aload 6
      //   4677: astore_1
      //   4678: aload 8
      //   4680: invokestatic 134	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   4683: aload 6
      //   4685: ifnull +19 -> 4704
      //   4688: aload 6
      //   4690: instanceof 262
      //   4693: ifeq +13 -> 4706
      //   4696: aload 6
      //   4698: checkcast 262	javax/net/ssl/HttpsURLConnection
      //   4701: invokevirtual 265	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   4704: aconst_null
      //   4705: areturn
      //   4706: aload 6
      //   4708: instanceof 267
      //   4711: ifeq -7 -> 4704
      //   4714: aload 6
      //   4716: checkcast 267	java/net/HttpURLConnection
      //   4719: invokevirtual 268	java/net/HttpURLConnection:disconnect	()V
      //   4722: goto -18 -> 4704
      //   4725: astore 9
      //   4727: aload 6
      //   4729: astore 7
      //   4731: aload 6
      //   4733: astore_1
      //   4734: aload 9
      //   4736: invokestatic 134	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   4739: goto -1715 -> 3024
      //   4742: aload 6
      //   4744: instanceof 267
      //   4747: ifeq -1702 -> 3045
      //   4750: aload 6
      //   4752: checkcast 267	java/net/HttpURLConnection
      //   4755: invokevirtual 268	java/net/HttpURLConnection:disconnect	()V
      //   4758: goto -1713 -> 3045
      //   4761: aload 7
      //   4763: instanceof 267
      //   4766: ifeq -1638 -> 3128
      //   4769: aload 7
      //   4771: checkcast 267	java/net/HttpURLConnection
      //   4774: invokevirtual 268	java/net/HttpURLConnection:disconnect	()V
      //   4777: goto -1649 -> 3128
      //   4780: aload_1
      //   4781: instanceof 267
      //   4784: ifeq -1617 -> 3167
      //   4787: aload_1
      //   4788: checkcast 267	java/net/HttpURLConnection
      //   4791: invokevirtual 268	java/net/HttpURLConnection:disconnect	()V
      //   4794: goto -1627 -> 3167
      //   4797: astore 7
      //   4799: aload 6
      //   4801: astore_1
      //   4802: aload 7
      //   4804: astore 6
      //   4806: goto -1657 -> 3149
      //   4809: astore_1
      //   4810: aload 6
      //   4812: astore 7
      //   4814: aload_1
      //   4815: astore 6
      //   4817: goto -1718 -> 3099
      //   4820: astore 8
      //   4822: aload 11
      //   4824: astore 6
      //   4826: goto -367 -> 4459
      //   4829: aload_1
      //   4830: astore 8
      //   4832: goto -4250 -> 582
      //   4835: iconst_0
      //   4836: istore 4
      //   4838: goto -4679 -> 159
      //   4841: ldc_w 942
      //   4844: astore 6
      //   4846: goto -3472 -> 1374
      //   4849: iconst_0
      //   4850: istore 4
      //   4852: goto -2303 -> 2549
      //   4855: goto -1893 -> 2962
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	4858	0	this	b
      //   0	4858	1	paramVarArgs	Void[]
      //   697	33	2	f1	float
      //   715	23	3	f2	float
      //   157	4694	4	j	int
      //   313	4200	5	bool	boolean
      //   41	2009	6	localObject1	Object
      //   2063	10	6	localJSONException1	JSONException
      //   2092	101	6	localJSONArray	JSONArray
      //   2203	10	6	localException1	Exception
      //   2300	752	6	localObject2	Object
      //   3056	10	6	localException2	Exception
      //   3075	1	6	localObject3	Object
      //   3097	6	6	localException3	Exception
      //   3147	21	6	localObject4	Object
      //   3192	931	6	localException4	Exception
      //   4131	10	6	localException5	Exception
      //   4148	652	6	localException6	Exception
      //   4804	41	6	localObject5	Object
      //   11	4377	7	localObject6	Object
      //   4448	7	7	localObject7	Object
      //   4461	309	7	localObject8	Object
      //   4797	6	7	localObject9	Object
      //   4812	1	7	localObject10	Object
      //   282	4162	8	localObject11	Object
      //   4457	43	8	localIOException1	java.io.IOException
      //   4560	85	8	str	String
      //   4669	10	8	localJSONException2	JSONException
      //   4820	1	8	localIOException2	java.io.IOException
      //   4830	1	8	arrayOfVoid	Void[]
      //   286	4374	9	localObject12	Object
      //   4725	10	9	localException7	Exception
      //   258	3480	10	localObject13	Object
      //   4	4819	11	localObject14	Object
      //   7	4379	12	localObject15	Object
      //   1	4389	13	localObject16	Object
      //   29	2265	14	localSharedPreferences	android.content.SharedPreferences
      //   176	1922	15	localPackageManager	android.content.pm.PackageManager
      //   196	912	16	localObject17	Object
      //   3080	10	16	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
      //   3130	10	16	localException8	Exception
      //   397	32	17	localInfo	com.google.android.gms.ads.identifier.AdvertisingIdClient.Info
      // Exception table:
      //   from	to	target	type
      //   605	616	2063	org/json/JSONException
      //   623	630	2063	org/json/JSONException
      //   637	648	2063	org/json/JSONException
      //   655	662	2063	org/json/JSONException
      //   669	680	2063	org/json/JSONException
      //   687	698	2063	org/json/JSONException
      //   705	716	2063	org/json/JSONException
      //   723	729	2063	org/json/JSONException
      //   752	762	2063	org/json/JSONException
      //   769	776	2063	org/json/JSONException
      //   783	794	2063	org/json/JSONException
      //   801	808	2063	org/json/JSONException
      //   815	822	2063	org/json/JSONException
      //   829	840	2063	org/json/JSONException
      //   847	854	2063	org/json/JSONException
      //   861	870	2063	org/json/JSONException
      //   877	884	2063	org/json/JSONException
      //   891	902	2063	org/json/JSONException
      //   909	916	2063	org/json/JSONException
      //   923	933	2063	org/json/JSONException
      //   940	946	2063	org/json/JSONException
      //   953	963	2063	org/json/JSONException
      //   970	982	2063	org/json/JSONException
      //   989	1001	2063	org/json/JSONException
      //   1008	1020	2063	org/json/JSONException
      //   1027	1039	2063	org/json/JSONException
      //   1046	1057	2063	org/json/JSONException
      //   1064	1074	2063	org/json/JSONException
      //   1081	1095	2063	org/json/JSONException
      //   1102	1116	2063	org/json/JSONException
      //   1119	1147	2063	org/json/JSONException
      //   1154	1165	2063	org/json/JSONException
      //   1172	1183	2063	org/json/JSONException
      //   1190	1202	2063	org/json/JSONException
      //   1209	1223	2063	org/json/JSONException
      //   1230	1250	2063	org/json/JSONException
      //   1257	1269	2063	org/json/JSONException
      //   1276	1290	2063	org/json/JSONException
      //   1297	1311	2063	org/json/JSONException
      //   1318	1331	2063	org/json/JSONException
      //   1338	1350	2063	org/json/JSONException
      //   1357	1369	2063	org/json/JSONException
      //   1381	1392	2063	org/json/JSONException
      //   1395	1404	2063	org/json/JSONException
      //   1421	1432	2063	org/json/JSONException
      //   1439	1451	2063	org/json/JSONException
      //   1458	1489	2063	org/json/JSONException
      //   1496	1508	2063	org/json/JSONException
      //   1515	1529	2063	org/json/JSONException
      //   1536	1556	2063	org/json/JSONException
      //   1563	1583	2063	org/json/JSONException
      //   1590	1604	2063	org/json/JSONException
      //   1611	1625	2063	org/json/JSONException
      //   1632	1651	2063	org/json/JSONException
      //   1658	1673	2063	org/json/JSONException
      //   1680	1694	2063	org/json/JSONException
      //   1701	1731	2063	org/json/JSONException
      //   1738	1754	2063	org/json/JSONException
      //   1761	1776	2063	org/json/JSONException
      //   1783	1795	2063	org/json/JSONException
      //   1802	1812	2063	org/json/JSONException
      //   1819	1833	2063	org/json/JSONException
      //   1840	1852	2063	org/json/JSONException
      //   1859	1873	2063	org/json/JSONException
      //   1880	1893	2063	org/json/JSONException
      //   1905	1923	2063	org/json/JSONException
      //   1930	1937	2063	org/json/JSONException
      //   1944	1953	2063	org/json/JSONException
      //   1960	1967	2063	org/json/JSONException
      //   1974	1991	2063	org/json/JSONException
      //   1998	2008	2063	org/json/JSONException
      //   2015	2027	2063	org/json/JSONException
      //   2034	2042	2063	org/json/JSONException
      //   2049	2060	2063	org/json/JSONException
      //   3089	3094	2063	org/json/JSONException
      //   3139	3144	2063	org/json/JSONException
      //   3177	3189	2063	org/json/JSONException
      //   3201	3206	2063	org/json/JSONException
      //   3216	3223	2063	org/json/JSONException
      //   3230	3247	2063	org/json/JSONException
      //   3254	3264	2063	org/json/JSONException
      //   3271	3283	2063	org/json/JSONException
      //   3290	3298	2063	org/json/JSONException
      //   3305	3316	2063	org/json/JSONException
      //   3326	3333	2063	org/json/JSONException
      //   3340	3357	2063	org/json/JSONException
      //   3364	3374	2063	org/json/JSONException
      //   3381	3393	2063	org/json/JSONException
      //   3400	3408	2063	org/json/JSONException
      //   3415	3426	2063	org/json/JSONException
      //   3436	3443	2063	org/json/JSONException
      //   3450	3467	2063	org/json/JSONException
      //   3474	3484	2063	org/json/JSONException
      //   3491	3503	2063	org/json/JSONException
      //   3510	3518	2063	org/json/JSONException
      //   3525	3536	2063	org/json/JSONException
      //   3546	3553	2063	org/json/JSONException
      //   3560	3577	2063	org/json/JSONException
      //   3584	3594	2063	org/json/JSONException
      //   3601	3613	2063	org/json/JSONException
      //   3620	3628	2063	org/json/JSONException
      //   3635	3646	2063	org/json/JSONException
      //   3656	3663	2063	org/json/JSONException
      //   3670	3687	2063	org/json/JSONException
      //   3694	3704	2063	org/json/JSONException
      //   3711	3723	2063	org/json/JSONException
      //   3735	3746	2063	org/json/JSONException
      //   3756	3767	2063	org/json/JSONException
      //   3774	3784	2063	org/json/JSONException
      //   3791	3807	2063	org/json/JSONException
      //   3814	3820	2063	org/json/JSONException
      //   3827	3839	2063	org/json/JSONException
      //   3846	3858	2063	org/json/JSONException
      //   3865	3881	2063	org/json/JSONException
      //   3888	3903	2063	org/json/JSONException
      //   3910	3926	2063	org/json/JSONException
      //   3933	3948	2063	org/json/JSONException
      //   3955	3971	2063	org/json/JSONException
      //   3978	3994	2063	org/json/JSONException
      //   4001	4017	2063	org/json/JSONException
      //   4024	4031	2063	org/json/JSONException
      //   4038	4048	2063	org/json/JSONException
      //   4055	4068	2063	org/json/JSONException
      //   4075	4085	2063	org/json/JSONException
      //   4092	4111	2063	org/json/JSONException
      //   2085	2094	2203	java/lang/Exception
      //   2097	2105	2203	java/lang/Exception
      //   2108	2116	2203	java/lang/Exception
      //   2119	2128	2203	java/lang/Exception
      //   2131	2141	2203	java/lang/Exception
      //   2144	2159	2203	java/lang/Exception
      //   2162	2175	2203	java/lang/Exception
      //   2178	2189	2203	java/lang/Exception
      //   2192	2200	2203	java/lang/Exception
      //   4117	4128	2203	java/lang/Exception
      //   339	346	3056	java/lang/Exception
      //   357	376	3056	java/lang/Exception
      //   387	399	3056	java/lang/Exception
      //   410	417	3056	java/lang/Exception
      //   428	436	3056	java/lang/Exception
      //   452	461	3056	java/lang/Exception
      //   472	485	3056	java/lang/Exception
      //   496	509	3056	java/lang/Exception
      //   520	527	3056	java/lang/Exception
      //   538	556	3056	java/lang/Exception
      //   1064	1074	3080	android/content/pm/PackageManager$NameNotFoundException
      //   1081	1095	3080	android/content/pm/PackageManager$NameNotFoundException
      //   1102	1116	3080	android/content/pm/PackageManager$NameNotFoundException
      //   16	31	3097	java/lang/Exception
      //   38	43	3097	java/lang/Exception
      //   50	65	3097	java/lang/Exception
      //   72	79	3097	java/lang/Exception
      //   86	92	3097	java/lang/Exception
      //   99	106	3097	java/lang/Exception
      //   113	125	3097	java/lang/Exception
      //   132	145	3097	java/lang/Exception
      //   152	156	3097	java/lang/Exception
      //   166	178	3097	java/lang/Exception
      //   185	198	3097	java/lang/Exception
      //   247	260	3097	java/lang/Exception
      //   267	280	3097	java/lang/Exception
      //   300	315	3097	java/lang/Exception
      //   570	582	3097	java/lang/Exception
      //   589	598	3097	java/lang/Exception
      //   605	616	3097	java/lang/Exception
      //   623	630	3097	java/lang/Exception
      //   637	648	3097	java/lang/Exception
      //   655	662	3097	java/lang/Exception
      //   669	680	3097	java/lang/Exception
      //   687	698	3097	java/lang/Exception
      //   705	716	3097	java/lang/Exception
      //   723	729	3097	java/lang/Exception
      //   752	762	3097	java/lang/Exception
      //   769	776	3097	java/lang/Exception
      //   783	794	3097	java/lang/Exception
      //   801	808	3097	java/lang/Exception
      //   815	822	3097	java/lang/Exception
      //   829	840	3097	java/lang/Exception
      //   847	854	3097	java/lang/Exception
      //   861	870	3097	java/lang/Exception
      //   877	884	3097	java/lang/Exception
      //   891	902	3097	java/lang/Exception
      //   909	916	3097	java/lang/Exception
      //   923	933	3097	java/lang/Exception
      //   940	946	3097	java/lang/Exception
      //   953	963	3097	java/lang/Exception
      //   970	982	3097	java/lang/Exception
      //   989	1001	3097	java/lang/Exception
      //   1008	1020	3097	java/lang/Exception
      //   1027	1039	3097	java/lang/Exception
      //   1046	1057	3097	java/lang/Exception
      //   1064	1074	3097	java/lang/Exception
      //   1081	1095	3097	java/lang/Exception
      //   1102	1116	3097	java/lang/Exception
      //   1154	1165	3097	java/lang/Exception
      //   1172	1183	3097	java/lang/Exception
      //   1190	1202	3097	java/lang/Exception
      //   1209	1223	3097	java/lang/Exception
      //   1230	1250	3097	java/lang/Exception
      //   1257	1269	3097	java/lang/Exception
      //   1276	1290	3097	java/lang/Exception
      //   1297	1311	3097	java/lang/Exception
      //   1318	1331	3097	java/lang/Exception
      //   1338	1350	3097	java/lang/Exception
      //   1357	1369	3097	java/lang/Exception
      //   1381	1392	3097	java/lang/Exception
      //   1439	1451	3097	java/lang/Exception
      //   1458	1489	3097	java/lang/Exception
      //   1496	1508	3097	java/lang/Exception
      //   1515	1529	3097	java/lang/Exception
      //   1536	1556	3097	java/lang/Exception
      //   1563	1583	3097	java/lang/Exception
      //   1590	1604	3097	java/lang/Exception
      //   1611	1625	3097	java/lang/Exception
      //   1632	1651	3097	java/lang/Exception
      //   1658	1673	3097	java/lang/Exception
      //   1680	1694	3097	java/lang/Exception
      //   1701	1731	3097	java/lang/Exception
      //   1738	1754	3097	java/lang/Exception
      //   1761	1776	3097	java/lang/Exception
      //   1783	1795	3097	java/lang/Exception
      //   1802	1812	3097	java/lang/Exception
      //   1819	1833	3097	java/lang/Exception
      //   1840	1852	3097	java/lang/Exception
      //   1859	1873	3097	java/lang/Exception
      //   1880	1893	3097	java/lang/Exception
      //   1905	1923	3097	java/lang/Exception
      //   1930	1937	3097	java/lang/Exception
      //   1944	1953	3097	java/lang/Exception
      //   1960	1967	3097	java/lang/Exception
      //   1974	1991	3097	java/lang/Exception
      //   1998	2008	3097	java/lang/Exception
      //   2015	2027	3097	java/lang/Exception
      //   2034	2042	3097	java/lang/Exception
      //   2049	2060	3097	java/lang/Exception
      //   2072	2077	3097	java/lang/Exception
      //   2212	2217	3097	java/lang/Exception
      //   2293	2302	3097	java/lang/Exception
      //   2309	2322	3097	java/lang/Exception
      //   2329	2340	3097	java/lang/Exception
      //   2347	2354	3097	java/lang/Exception
      //   2361	2365	3097	java/lang/Exception
      //   2372	2382	3097	java/lang/Exception
      //   2389	2405	3097	java/lang/Exception
      //   2412	2434	3097	java/lang/Exception
      //   2441	2457	3097	java/lang/Exception
      //   2464	2479	3097	java/lang/Exception
      //   2486	2501	3097	java/lang/Exception
      //   2508	2523	3097	java/lang/Exception
      //   2530	2546	3097	java/lang/Exception
      //   2561	2610	3097	java/lang/Exception
      //   2617	2624	3097	java/lang/Exception
      //   2866	2882	3097	java/lang/Exception
      //   2899	2916	3097	java/lang/Exception
      //   2923	2936	3097	java/lang/Exception
      //   2943	2962	3097	java/lang/Exception
      //   2969	2980	3097	java/lang/Exception
      //   2987	2992	3097	java/lang/Exception
      //   3065	3070	3097	java/lang/Exception
      //   3089	3094	3097	java/lang/Exception
      //   3139	3144	3097	java/lang/Exception
      //   3177	3189	3097	java/lang/Exception
      //   3201	3206	3097	java/lang/Exception
      //   3216	3223	3097	java/lang/Exception
      //   3230	3247	3097	java/lang/Exception
      //   3254	3264	3097	java/lang/Exception
      //   3271	3283	3097	java/lang/Exception
      //   3290	3298	3097	java/lang/Exception
      //   3305	3316	3097	java/lang/Exception
      //   3326	3333	3097	java/lang/Exception
      //   3340	3357	3097	java/lang/Exception
      //   3364	3374	3097	java/lang/Exception
      //   3381	3393	3097	java/lang/Exception
      //   3400	3408	3097	java/lang/Exception
      //   3415	3426	3097	java/lang/Exception
      //   3436	3443	3097	java/lang/Exception
      //   3450	3467	3097	java/lang/Exception
      //   3474	3484	3097	java/lang/Exception
      //   3491	3503	3097	java/lang/Exception
      //   3510	3518	3097	java/lang/Exception
      //   3525	3536	3097	java/lang/Exception
      //   3546	3553	3097	java/lang/Exception
      //   3560	3577	3097	java/lang/Exception
      //   3584	3594	3097	java/lang/Exception
      //   3601	3613	3097	java/lang/Exception
      //   3620	3628	3097	java/lang/Exception
      //   3635	3646	3097	java/lang/Exception
      //   3656	3663	3097	java/lang/Exception
      //   3670	3687	3097	java/lang/Exception
      //   3694	3704	3097	java/lang/Exception
      //   3711	3723	3097	java/lang/Exception
      //   3735	3746	3097	java/lang/Exception
      //   3756	3767	3097	java/lang/Exception
      //   3774	3784	3097	java/lang/Exception
      //   3791	3807	3097	java/lang/Exception
      //   3814	3820	3097	java/lang/Exception
      //   3827	3839	3097	java/lang/Exception
      //   3846	3858	3097	java/lang/Exception
      //   3865	3881	3097	java/lang/Exception
      //   3888	3903	3097	java/lang/Exception
      //   3910	3926	3097	java/lang/Exception
      //   3933	3948	3097	java/lang/Exception
      //   3955	3971	3097	java/lang/Exception
      //   3978	3994	3097	java/lang/Exception
      //   4001	4017	3097	java/lang/Exception
      //   4024	4031	3097	java/lang/Exception
      //   4038	4048	3097	java/lang/Exception
      //   4055	4068	3097	java/lang/Exception
      //   4075	4085	3097	java/lang/Exception
      //   4092	4111	3097	java/lang/Exception
      //   4140	4145	3097	java/lang/Exception
      //   4157	4162	3097	java/lang/Exception
      //   4172	4187	3097	java/lang/Exception
      //   4194	4207	3097	java/lang/Exception
      //   4214	4220	3097	java/lang/Exception
      //   4264	4283	3097	java/lang/Exception
      //   4290	4309	3097	java/lang/Exception
      //   4319	4334	3097	java/lang/Exception
      //   4341	4356	3097	java/lang/Exception
      //   4363	4382	3097	java/lang/Exception
      //   4392	4445	3097	java/lang/Exception
      //   4466	4471	3097	java/lang/Exception
      //   4478	4492	3097	java/lang/Exception
      //   4499	4512	3097	java/lang/Exception
      //   4619	4628	3097	java/lang/Exception
      //   4635	4652	3097	java/lang/Exception
      //   4659	4666	3097	java/lang/Exception
      //   4678	4683	3097	java/lang/Exception
      //   4734	4739	3097	java/lang/Exception
      //   1119	1147	3130	java/lang/Exception
      //   16	31	3147	finally
      //   38	43	3147	finally
      //   50	65	3147	finally
      //   72	79	3147	finally
      //   86	92	3147	finally
      //   99	106	3147	finally
      //   113	125	3147	finally
      //   132	145	3147	finally
      //   152	156	3147	finally
      //   166	178	3147	finally
      //   185	198	3147	finally
      //   247	260	3147	finally
      //   267	280	3147	finally
      //   300	315	3147	finally
      //   339	346	3147	finally
      //   357	376	3147	finally
      //   387	399	3147	finally
      //   410	417	3147	finally
      //   428	436	3147	finally
      //   452	461	3147	finally
      //   472	485	3147	finally
      //   496	509	3147	finally
      //   520	527	3147	finally
      //   538	556	3147	finally
      //   570	582	3147	finally
      //   589	598	3147	finally
      //   605	616	3147	finally
      //   623	630	3147	finally
      //   637	648	3147	finally
      //   655	662	3147	finally
      //   669	680	3147	finally
      //   687	698	3147	finally
      //   705	716	3147	finally
      //   723	729	3147	finally
      //   752	762	3147	finally
      //   769	776	3147	finally
      //   783	794	3147	finally
      //   801	808	3147	finally
      //   815	822	3147	finally
      //   829	840	3147	finally
      //   847	854	3147	finally
      //   861	870	3147	finally
      //   877	884	3147	finally
      //   891	902	3147	finally
      //   909	916	3147	finally
      //   923	933	3147	finally
      //   940	946	3147	finally
      //   953	963	3147	finally
      //   970	982	3147	finally
      //   989	1001	3147	finally
      //   1008	1020	3147	finally
      //   1027	1039	3147	finally
      //   1046	1057	3147	finally
      //   1064	1074	3147	finally
      //   1081	1095	3147	finally
      //   1102	1116	3147	finally
      //   1119	1147	3147	finally
      //   1154	1165	3147	finally
      //   1172	1183	3147	finally
      //   1190	1202	3147	finally
      //   1209	1223	3147	finally
      //   1230	1250	3147	finally
      //   1257	1269	3147	finally
      //   1276	1290	3147	finally
      //   1297	1311	3147	finally
      //   1318	1331	3147	finally
      //   1338	1350	3147	finally
      //   1357	1369	3147	finally
      //   1381	1392	3147	finally
      //   1395	1404	3147	finally
      //   1421	1432	3147	finally
      //   1439	1451	3147	finally
      //   1458	1489	3147	finally
      //   1496	1508	3147	finally
      //   1515	1529	3147	finally
      //   1536	1556	3147	finally
      //   1563	1583	3147	finally
      //   1590	1604	3147	finally
      //   1611	1625	3147	finally
      //   1632	1651	3147	finally
      //   1658	1673	3147	finally
      //   1680	1694	3147	finally
      //   1701	1731	3147	finally
      //   1738	1754	3147	finally
      //   1761	1776	3147	finally
      //   1783	1795	3147	finally
      //   1802	1812	3147	finally
      //   1819	1833	3147	finally
      //   1840	1852	3147	finally
      //   1859	1873	3147	finally
      //   1880	1893	3147	finally
      //   1905	1923	3147	finally
      //   1930	1937	3147	finally
      //   1944	1953	3147	finally
      //   1960	1967	3147	finally
      //   1974	1991	3147	finally
      //   1998	2008	3147	finally
      //   2015	2027	3147	finally
      //   2034	2042	3147	finally
      //   2049	2060	3147	finally
      //   2072	2077	3147	finally
      //   2085	2094	3147	finally
      //   2097	2105	3147	finally
      //   2108	2116	3147	finally
      //   2119	2128	3147	finally
      //   2131	2141	3147	finally
      //   2144	2159	3147	finally
      //   2162	2175	3147	finally
      //   2178	2189	3147	finally
      //   2192	2200	3147	finally
      //   2212	2217	3147	finally
      //   2220	2239	3147	finally
      //   2242	2261	3147	finally
      //   2264	2286	3147	finally
      //   2293	2302	3147	finally
      //   2309	2322	3147	finally
      //   2329	2340	3147	finally
      //   2347	2354	3147	finally
      //   2361	2365	3147	finally
      //   2372	2382	3147	finally
      //   2389	2405	3147	finally
      //   2412	2434	3147	finally
      //   2441	2457	3147	finally
      //   2464	2479	3147	finally
      //   2486	2501	3147	finally
      //   2508	2523	3147	finally
      //   2530	2546	3147	finally
      //   2561	2610	3147	finally
      //   2617	2624	3147	finally
      //   2866	2882	3147	finally
      //   2899	2916	3147	finally
      //   2923	2936	3147	finally
      //   2943	2962	3147	finally
      //   2969	2980	3147	finally
      //   2987	2992	3147	finally
      //   2995	3006	3147	finally
      //   3009	3015	3147	finally
      //   3018	3024	3147	finally
      //   3065	3070	3147	finally
      //   3089	3094	3147	finally
      //   3102	3107	3147	finally
      //   3139	3144	3147	finally
      //   3177	3189	3147	finally
      //   3201	3206	3147	finally
      //   3216	3223	3147	finally
      //   3230	3247	3147	finally
      //   3254	3264	3147	finally
      //   3271	3283	3147	finally
      //   3290	3298	3147	finally
      //   3305	3316	3147	finally
      //   3326	3333	3147	finally
      //   3340	3357	3147	finally
      //   3364	3374	3147	finally
      //   3381	3393	3147	finally
      //   3400	3408	3147	finally
      //   3415	3426	3147	finally
      //   3436	3443	3147	finally
      //   3450	3467	3147	finally
      //   3474	3484	3147	finally
      //   3491	3503	3147	finally
      //   3510	3518	3147	finally
      //   3525	3536	3147	finally
      //   3546	3553	3147	finally
      //   3560	3577	3147	finally
      //   3584	3594	3147	finally
      //   3601	3613	3147	finally
      //   3620	3628	3147	finally
      //   3635	3646	3147	finally
      //   3656	3663	3147	finally
      //   3670	3687	3147	finally
      //   3694	3704	3147	finally
      //   3711	3723	3147	finally
      //   3735	3746	3147	finally
      //   3756	3767	3147	finally
      //   3774	3784	3147	finally
      //   3791	3807	3147	finally
      //   3814	3820	3147	finally
      //   3827	3839	3147	finally
      //   3846	3858	3147	finally
      //   3865	3881	3147	finally
      //   3888	3903	3147	finally
      //   3910	3926	3147	finally
      //   3933	3948	3147	finally
      //   3955	3971	3147	finally
      //   3978	3994	3147	finally
      //   4001	4017	3147	finally
      //   4024	4031	3147	finally
      //   4038	4048	3147	finally
      //   4055	4068	3147	finally
      //   4075	4085	3147	finally
      //   4092	4111	3147	finally
      //   4117	4128	3147	finally
      //   4140	4145	3147	finally
      //   4157	4162	3147	finally
      //   4172	4187	3147	finally
      //   4194	4207	3147	finally
      //   4214	4220	3147	finally
      //   4264	4283	3147	finally
      //   4290	4309	3147	finally
      //   4319	4334	3147	finally
      //   4341	4356	3147	finally
      //   4363	4382	3147	finally
      //   4392	4445	3147	finally
      //   4466	4471	3147	finally
      //   4478	4492	3147	finally
      //   4499	4512	3147	finally
      //   4619	4628	3147	finally
      //   4635	4652	3147	finally
      //   4659	4666	3147	finally
      //   4678	4683	3147	finally
      //   4734	4739	3147	finally
      //   1395	1404	3192	java/lang/Exception
      //   1421	1432	3192	java/lang/Exception
      //   2220	2239	4131	java/lang/Exception
      //   2242	2261	4131	java/lang/Exception
      //   2264	2286	4148	java/lang/Exception
      //   2766	2781	4448	finally
      //   2624	2714	4457	java/io/IOException
      //   2714	2766	4457	java/io/IOException
      //   2781	2813	4457	java/io/IOException
      //   2817	2849	4457	java/io/IOException
      //   4450	4457	4457	java/io/IOException
      //   2969	2980	4669	org/json/JSONException
      //   2987	2992	4669	org/json/JSONException
      //   2995	3006	4725	java/lang/Exception
      //   3009	3015	4725	java/lang/Exception
      //   3018	3024	4725	java/lang/Exception
      //   2624	2714	4797	finally
      //   2714	2766	4797	finally
      //   2781	2813	4797	finally
      //   2817	2849	4797	finally
      //   4450	4457	4797	finally
      //   2624	2714	4809	java/lang/Exception
      //   2714	2766	4809	java/lang/Exception
      //   2781	2813	4809	java/lang/Exception
      //   2817	2849	4809	java/lang/Exception
      //   4450	4457	4809	java/lang/Exception
      //   2617	2624	4820	java/io/IOException
    }
    
    protected void a(JSONObject paramJSONObject)
    {
      try
      {
        if (r.j(r.this) != null)
        {
          if (paramJSONObject == null)
          {
            r.j(r.this).a(r.k(r.this));
            return;
          }
          r.j(r.this).a(paramJSONObject, r.k(r.this), r.a(r.this));
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
      if ((r.a(r.this).equals("banner")) || (r.a(r.this).equals("debug")))
      {
        bool1 = true;
        this.b = bool1;
        if ((!r.a(r.this).equals("banner_320")) && (!r.a(r.this).equals("debug_banner_320"))) {
          break label390;
        }
        bool1 = true;
        label73:
        this.c = bool1;
        if ((!r.a(r.this).equals("banner_mrec")) && (!r.a(r.this).equals("debug_mrec"))) {
          break label395;
        }
        bool1 = true;
        label111:
        this.d = bool1;
        if ((!r.a(r.this).equals("video")) && (!r.a(r.this).equals("debug_video"))) {
          break label400;
        }
        bool1 = true;
        label149:
        this.e = bool1;
        if ((!r.a(r.this).equals("rewarded_video")) && (!r.a(r.this).equals("debug_rewarded_video"))) {
          break label405;
        }
        bool1 = true;
        label187:
        this.f = bool1;
        if ((!r.a(r.this).equals("native")) && (!r.a(r.this).equals("debug_native"))) {
          break label410;
        }
        bool1 = true;
        label225:
        this.g = bool1;
        if ((!r.a(r.this).equals("debug")) && (!r.a(r.this).equals("debug_banner_320")) && (!r.a(r.this).equals("debug_video")) && (!r.a(r.this).equals("debug_rewarded_video")) && (!r.a(r.this).equals("debug_mrec")) && (!r.a(r.this).equals("debug_native"))) {
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
