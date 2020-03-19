package com.appodeal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.appodeal.ads.d.h;
import com.appodeal.ads.f.f;
import com.appodeal.ads.f.f.a;
import com.appodeal.ads.f.g;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class q
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
  
  public q(Context paramContext, com.appodeal.ads.f.c paramC, a paramA, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, h paramH)
  {
    this(paramContext, paramC, paramA, paramInt, paramString1, paramString2, paramString3, paramString4, paramH, 0L, null);
  }
  
  q(Context paramContext, com.appodeal.ads.f.c paramC, a paramA, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, h paramH, long paramLong, String paramString5)
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
            new q.b(q.this, null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
          }
          new q.b(q.this, null).execute(new Void[0]);
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
  
  q(Context paramContext, a paramA, int paramInt, String paramString)
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
      int j = c.t;
      int k = k.m;
      int m = ae.l;
      int n = ah.l;
      int i1 = x.l;
      int i2 = r.p;
      int i3 = c.u;
      int i4 = k.n;
      int i5 = ae.n;
      int i6 = ah.n;
      int i7 = x.m;
      int i8 = r.q;
      int i9 = ah.m;
      int i10 = ae.m;
      try
      {
        localJSONObject.put("show", j + k + m + n + i1 + i2);
        localJSONObject.put("click", i3 + i4 + i5 + i6 + i7 + i8);
        if ((this.e) || (this.f) || ((q.g(q.this) != null) && ((q.g(q.this).equals("video")) || (q.g(q.this).equals("rewarded_video"))))) {
          localJSONObject.put("finish", i9 + i10);
        }
        if ((this.b) || ((q.g(q.this) != null) && (q.g(q.this).equals("banner"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "show" }), k.m);
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "click" }), k.n);
        }
        if ((this.e) || ((q.g(q.this) != null) && (q.g(q.this).equals("video"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "video", "show" }), ae.l);
          localJSONObject.put(String.format("%s_%s", new Object[] { "video", "click" }), ae.n);
          localJSONObject.put(String.format("%s_%s", new Object[] { "video", "finish" }), ae.m);
        }
        if ((this.f) || ((q.g(q.this) != null) && (q.g(q.this).equals("rewarded_video"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "show" }), ah.l);
          localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "click" }), ah.n);
          localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "finish" }), ah.m);
        }
        if ((this.c) || ((q.g(q.this) != null) && (q.g(q.this).equals("banner_320"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "show" }), c.t);
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "click" }), c.u);
        }
        if ((this.d) || ((q.g(q.this) != null) && (q.g(q.this).equals("banner_mrec"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "show" }), r.p);
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "click" }), r.q);
        }
        if ((this.g) || ((q.g(q.this) != null) && (q.g(q.this).equals("native"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "native", "show" }), x.l);
          localJSONObject.put(String.format("%s_%s", new Object[] { "native", "click" }), x.m);
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
      paramJSONObject = new g(q.b(q.this), paramJSONObject).a(localJSONArray);
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
      //   17: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   20: invokestatic 152	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   23: ldc -57
      //   25: iconst_0
      //   26: invokevirtual 205	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   29: astore 14
      //   31: aload 12
      //   33: astore 7
      //   35: aload 13
      //   37: astore_1
      //   38: invokestatic 211	java/util/Calendar:getInstance	()Ljava/util/Calendar;
      //   41: astore 6
      //   43: aload 12
      //   45: astore 7
      //   47: aload 13
      //   49: astore_1
      //   50: aload 6
      //   52: aload 14
      //   54: ldc -43
      //   56: lconst_0
      //   57: invokeinterface 219 4 0
      //   62: invokevirtual 223	java/util/Calendar:setTimeInMillis	(J)V
      //   65: aload 12
      //   67: astore 7
      //   69: aload 13
      //   71: astore_1
      //   72: aload 6
      //   74: iconst_5
      //   75: iconst_1
      //   76: invokevirtual 227	java/util/Calendar:add	(II)V
      //   79: aload 12
      //   81: astore 7
      //   83: aload 13
      //   85: astore_1
      //   86: getstatic 232	com/appodeal/ads/UserSettings:sendingInProgress	Z
      //   89: ifne +4743 -> 4832
      //   92: aload 12
      //   94: astore 7
      //   96: aload 13
      //   98: astore_1
      //   99: aload_0
      //   100: getfield 234	com/appodeal/ads/q$b:i	Z
      //   103: ifeq +4729 -> 4832
      //   106: aload 12
      //   108: astore 7
      //   110: aload 13
      //   112: astore_1
      //   113: aload 6
      //   115: invokevirtual 237	java/util/Calendar:getTimeInMillis	()J
      //   118: invokestatic 242	java/lang/System:currentTimeMillis	()J
      //   121: lcmp
      //   122: iflt +23 -> 145
      //   125: aload 12
      //   127: astore 7
      //   129: aload 13
      //   131: astore_1
      //   132: aload 14
      //   134: ldc -12
      //   136: iconst_1
      //   137: invokeinterface 248 3 0
      //   142: ifeq +4690 -> 4832
      //   145: aload 12
      //   147: astore 7
      //   149: aload 13
      //   151: astore_1
      //   152: iconst_1
      //   153: putstatic 232	com/appodeal/ads/UserSettings:sendingInProgress	Z
      //   156: iconst_1
      //   157: istore 4
      //   159: aload 12
      //   161: astore 7
      //   163: aload 13
      //   165: astore_1
      //   166: aload_0
      //   167: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   170: invokestatic 152	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   173: invokevirtual 252	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   176: astore 15
      //   178: aload 12
      //   180: astore 7
      //   182: aload 13
      //   184: astore_1
      //   185: aload 14
      //   187: ldc -2
      //   189: aconst_null
      //   190: invokeinterface 258 3 0
      //   195: astore 16
      //   197: aload 16
      //   199: ifnonnull +40 -> 239
      //   202: iconst_0
      //   203: ifeq +17 -> 220
      //   206: aconst_null
      //   207: instanceof 260
      //   210: ifeq +12 -> 222
      //   213: aconst_null
      //   214: checkcast 260	javax/net/ssl/HttpsURLConnection
      //   217: invokevirtual 263	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   220: aconst_null
      //   221: areturn
      //   222: aconst_null
      //   223: instanceof 265
      //   226: ifeq -6 -> 220
      //   229: aconst_null
      //   230: checkcast 265	java/net/HttpURLConnection
      //   233: invokevirtual 266	java/net/HttpURLConnection:disconnect	()V
      //   236: goto -16 -> 220
      //   239: aload 12
      //   241: astore 7
      //   243: aload 13
      //   245: astore_1
      //   246: aload 14
      //   248: ldc_w 268
      //   251: aconst_null
      //   252: invokeinterface 258 3 0
      //   257: astore 10
      //   259: aload 12
      //   261: astore 7
      //   263: aload 13
      //   265: astore_1
      //   266: aload 14
      //   268: ldc_w 270
      //   271: aconst_null
      //   272: invokeinterface 258 3 0
      //   277: astore 6
      //   279: aload 6
      //   281: astore 8
      //   283: aload 10
      //   285: astore 9
      //   287: aload 10
      //   289: ifnonnull +2778 -> 3067
      //   292: aload 12
      //   294: astore 7
      //   296: aload 13
      //   298: astore_1
      //   299: aload_0
      //   300: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   303: invokestatic 272	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   306: ldc_w 274
      //   309: invokevirtual 99	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   312: istore 5
      //   314: aload 6
      //   316: astore 8
      //   318: aload 10
      //   320: astore 9
      //   322: iload 5
      //   324: ifne +2743 -> 3067
      //   327: aload 6
      //   329: astore 8
      //   331: aload 10
      //   333: astore 9
      //   335: aload 13
      //   337: astore_1
      //   338: ldc_w 276
      //   341: invokestatic 282	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
      //   344: pop
      //   345: aload 6
      //   347: astore 8
      //   349: aload 10
      //   351: astore 9
      //   353: aload 13
      //   355: astore_1
      //   356: ldc_w 284
      //   359: ldc_w 286
      //   362: iconst_1
      //   363: anewarray 278	java/lang/Class
      //   366: dup
      //   367: iconst_0
      //   368: ldc -55
      //   370: aastore
      //   371: invokevirtual 290	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      //   374: pop
      //   375: aload 6
      //   377: astore 8
      //   379: aload 10
      //   381: astore 9
      //   383: aload 13
      //   385: astore_1
      //   386: aload_0
      //   387: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   390: invokestatic 152	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   393: invokestatic 293	com/google/android/gms/ads/identifier/AdvertisingIdClient:getAdvertisingIdInfo	(Landroid/content/Context;)Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;
      //   396: astore 17
      //   398: aload 6
      //   400: astore 8
      //   402: aload 10
      //   404: astore 9
      //   406: aload 13
      //   408: astore_1
      //   409: aload 17
      //   411: invokevirtual 299	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:getId	()Ljava/lang/String;
      //   414: astore 7
      //   416: aload 6
      //   418: astore 8
      //   420: aload 7
      //   422: astore 9
      //   424: aload 13
      //   426: astore_1
      //   427: aload 17
      //   429: invokevirtual 303	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:isLimitAdTrackingEnabled	()Z
      //   432: ifeq +2613 -> 3045
      //   435: ldc_w 305
      //   438: astore 6
      //   440: aload 6
      //   442: astore 8
      //   444: aload 7
      //   446: astore 9
      //   448: aload 13
      //   450: astore_1
      //   451: aload 14
      //   453: invokeinterface 309 1 0
      //   458: astore 10
      //   460: aload 6
      //   462: astore 8
      //   464: aload 7
      //   466: astore 9
      //   468: aload 13
      //   470: astore_1
      //   471: aload 10
      //   473: ldc_w 268
      //   476: aload 7
      //   478: invokeinterface 315 3 0
      //   483: pop
      //   484: aload 6
      //   486: astore 8
      //   488: aload 7
      //   490: astore 9
      //   492: aload 13
      //   494: astore_1
      //   495: aload 10
      //   497: ldc_w 270
      //   500: aload 6
      //   502: invokeinterface 315 3 0
      //   507: pop
      //   508: aload 6
      //   510: astore 8
      //   512: aload 7
      //   514: astore 9
      //   516: aload 13
      //   518: astore_1
      //   519: aload 10
      //   521: invokeinterface 318 1 0
      //   526: aload 6
      //   528: astore 8
      //   530: aload 7
      //   532: astore 9
      //   534: aload 13
      //   536: astore_1
      //   537: ldc_w 320
      //   540: iconst_1
      //   541: anewarray 111	java/lang/Object
      //   544: dup
      //   545: iconst_0
      //   546: aload 7
      //   548: aastore
      //   549: invokestatic 115	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   552: invokestatic 323	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   555: aload 7
      //   557: astore_1
      //   558: aload_1
      //   559: ifnonnull +4267 -> 4826
      //   562: aload 12
      //   564: astore 7
      //   566: aload 13
      //   568: astore_1
      //   569: aload_0
      //   570: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   573: invokestatic 152	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   576: invokestatic 328	com/appodeal/ads/ak:l	(Landroid/content/Context;)Ljava/lang/String;
      //   579: astore 8
      //   581: aload 12
      //   583: astore 7
      //   585: aload 13
      //   587: astore_1
      //   588: new 34	org/json/JSONObject
      //   591: dup
      //   592: invokespecial 35	org/json/JSONObject:<init>	()V
      //   595: astore 9
      //   597: aload 12
      //   599: astore 7
      //   601: aload 13
      //   603: astore_1
      //   604: aload 9
      //   606: ldc_w 330
      //   609: aload 16
      //   611: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   614: pop
      //   615: aload 12
      //   617: astore 7
      //   619: aload 13
      //   621: astore_1
      //   622: aload_0
      //   623: getfield 105	com/appodeal/ads/q$b:b	Z
      //   626: ifeq +21 -> 647
      //   629: aload 12
      //   631: astore 7
      //   633: aload 13
      //   635: astore_1
      //   636: aload 9
      //   638: ldc_w 335
      //   641: ldc 107
      //   643: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   646: pop
      //   647: aload 12
      //   649: astore 7
      //   651: aload 13
      //   653: astore_1
      //   654: aload_0
      //   655: getfield 117	com/appodeal/ads/q$b:c	Z
      //   658: ifeq +103 -> 761
      //   661: aload 12
      //   663: astore 7
      //   665: aload 13
      //   667: astore_1
      //   668: aload 9
      //   670: ldc_w 335
      //   673: ldc 119
      //   675: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   678: pop
      //   679: aload 12
      //   681: astore 7
      //   683: aload 13
      //   685: astore_1
      //   686: aload_0
      //   687: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   690: invokestatic 152	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   693: invokestatic 338	com/appodeal/ads/ak:g	(Landroid/content/Context;)F
      //   696: fstore_2
      //   697: aload 12
      //   699: astore 7
      //   701: aload 13
      //   703: astore_1
      //   704: aload_0
      //   705: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   708: invokestatic 152	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   711: invokestatic 340	com/appodeal/ads/ak:h	(Landroid/content/Context;)F
      //   714: fstore_3
      //   715: aload 12
      //   717: astore 7
      //   719: aload 13
      //   721: astore_1
      //   722: getstatic 342	com/appodeal/ads/c:n	Z
      //   725: ifeq +36 -> 761
      //   728: fload_2
      //   729: ldc_w 343
      //   732: fcmpl
      //   733: iflt +28 -> 761
      //   736: fload_3
      //   737: ldc_w 344
      //   740: fcmpl
      //   741: ifle +20 -> 761
      //   744: aload 12
      //   746: astore 7
      //   748: aload 13
      //   750: astore_1
      //   751: aload 9
      //   753: ldc_w 346
      //   756: iconst_1
      //   757: invokevirtual 349	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   760: pop
      //   761: aload 12
      //   763: astore 7
      //   765: aload 13
      //   767: astore_1
      //   768: aload_0
      //   769: getfield 121	com/appodeal/ads/q$b:d	Z
      //   772: ifeq +21 -> 793
      //   775: aload 12
      //   777: astore 7
      //   779: aload 13
      //   781: astore_1
      //   782: aload 9
      //   784: ldc_w 335
      //   787: ldc 123
      //   789: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   792: pop
      //   793: aload 12
      //   795: astore 7
      //   797: aload 13
      //   799: astore_1
      //   800: aload_0
      //   801: getfield 86	com/appodeal/ads/q$b:e	Z
      //   804: ifne +17 -> 821
      //   807: aload 12
      //   809: astore 7
      //   811: aload 13
      //   813: astore_1
      //   814: aload_0
      //   815: getfield 88	com/appodeal/ads/q$b:f	Z
      //   818: ifeq +21 -> 839
      //   821: aload 12
      //   823: astore 7
      //   825: aload 13
      //   827: astore_1
      //   828: aload 9
      //   830: ldc_w 335
      //   833: ldc 93
      //   835: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   838: pop
      //   839: aload 12
      //   841: astore 7
      //   843: aload 13
      //   845: astore_1
      //   846: aload_0
      //   847: getfield 88	com/appodeal/ads/q$b:f	Z
      //   850: ifeq +19 -> 869
      //   853: aload 12
      //   855: astore 7
      //   857: aload 13
      //   859: astore_1
      //   860: aload 9
      //   862: ldc 101
      //   864: iconst_1
      //   865: invokevirtual 349	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   868: pop
      //   869: aload 12
      //   871: astore 7
      //   873: aload 13
      //   875: astore_1
      //   876: aload_0
      //   877: getfield 125	com/appodeal/ads/q$b:g	Z
      //   880: ifeq +21 -> 901
      //   883: aload 12
      //   885: astore 7
      //   887: aload 13
      //   889: astore_1
      //   890: aload 9
      //   892: ldc_w 335
      //   895: ldc 127
      //   897: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   900: pop
      //   901: aload 12
      //   903: astore 7
      //   905: aload 13
      //   907: astore_1
      //   908: aload_0
      //   909: getfield 351	com/appodeal/ads/q$b:h	Z
      //   912: ifeq +20 -> 932
      //   915: aload 12
      //   917: astore 7
      //   919: aload 13
      //   921: astore_1
      //   922: aload 9
      //   924: ldc_w 353
      //   927: iconst_1
      //   928: invokevirtual 349	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   931: pop
      //   932: aload 12
      //   934: astore 7
      //   936: aload 13
      //   938: astore_1
      //   939: getstatic 357	com/appodeal/ads/AppodealSettings:a	Z
      //   942: ifeq +20 -> 962
      //   945: aload 12
      //   947: astore 7
      //   949: aload 13
      //   951: astore_1
      //   952: aload 9
      //   954: ldc_w 359
      //   957: iconst_1
      //   958: invokevirtual 349	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   961: pop
      //   962: aload 12
      //   964: astore 7
      //   966: aload 13
      //   968: astore_1
      //   969: aload 9
      //   971: ldc_w 361
      //   974: getstatic 367	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
      //   977: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   980: pop
      //   981: aload 12
      //   983: astore 7
      //   985: aload 13
      //   987: astore_1
      //   988: aload 9
      //   990: ldc_w 369
      //   993: getstatic 372	android/os/Build$VERSION:SDK_INT	I
      //   996: invokevirtual 82	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
      //   999: pop
      //   1000: aload 12
      //   1002: astore 7
      //   1004: aload 13
      //   1006: astore_1
      //   1007: aload 9
      //   1009: ldc_w 374
      //   1012: ldc_w 376
      //   1015: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1018: pop
      //   1019: aload 12
      //   1021: astore 7
      //   1023: aload 13
      //   1025: astore_1
      //   1026: aload_0
      //   1027: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   1030: invokestatic 152	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   1033: invokevirtual 379	android/content/Context:getPackageName	()Ljava/lang/String;
      //   1036: astore 10
      //   1038: aload 12
      //   1040: astore 7
      //   1042: aload 13
      //   1044: astore_1
      //   1045: aload 9
      //   1047: ldc_w 381
      //   1050: aload 10
      //   1052: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1055: pop
      //   1056: aload 12
      //   1058: astore 7
      //   1060: aload 13
      //   1062: astore_1
      //   1063: aload 15
      //   1065: aload 10
      //   1067: iconst_0
      //   1068: invokevirtual 387	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
      //   1071: astore 16
      //   1073: aload 12
      //   1075: astore 7
      //   1077: aload 13
      //   1079: astore_1
      //   1080: aload 9
      //   1082: ldc_w 389
      //   1085: aload 16
      //   1087: getfield 394	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
      //   1090: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1093: pop
      //   1094: aload 12
      //   1096: astore 7
      //   1098: aload 13
      //   1100: astore_1
      //   1101: aload 9
      //   1103: ldc_w 396
      //   1106: aload 16
      //   1108: getfield 399	android/content/pm/PackageInfo:versionCode	I
      //   1111: invokevirtual 82	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
      //   1114: pop
      //   1115: aload 13
      //   1117: astore_1
      //   1118: aload 9
      //   1120: ldc_w 401
      //   1123: aload 15
      //   1125: aload 10
      //   1127: sipush 128
      //   1130: invokevirtual 405	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
      //   1133: getfield 411	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
      //   1136: ldc_w 413
      //   1139: invokevirtual 418	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   1142: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1145: pop
      //   1146: aload 12
      //   1148: astore 7
      //   1150: aload 13
      //   1152: astore_1
      //   1153: aload 9
      //   1155: ldc_w 420
      //   1158: aload 8
      //   1160: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1163: pop
      //   1164: aload 12
      //   1166: astore 7
      //   1168: aload 13
      //   1170: astore_1
      //   1171: aload 9
      //   1173: ldc_w 422
      //   1176: aload 6
      //   1178: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1181: pop
      //   1182: aload 12
      //   1184: astore 7
      //   1186: aload 13
      //   1188: astore_1
      //   1189: aload_0
      //   1190: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   1193: invokestatic 152	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   1196: invokestatic 425	com/appodeal/ads/ak:b	(Landroid/content/Context;)Lcom/appodeal/ads/ak$a;
      //   1199: astore 8
      //   1201: aload 12
      //   1203: astore 7
      //   1205: aload 13
      //   1207: astore_1
      //   1208: aload 9
      //   1210: ldc_w 427
      //   1213: aload 8
      //   1215: getfield 431	com/appodeal/ads/ak$a:a	Ljava/lang/String;
      //   1218: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1221: pop
      //   1222: aload 12
      //   1224: astore 7
      //   1226: aload 13
      //   1228: astore_1
      //   1229: aload 9
      //   1231: ldc_w 433
      //   1234: aload_0
      //   1235: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   1238: invokestatic 152	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   1241: invokestatic 436	com/appodeal/ads/ak:k	(Landroid/content/Context;)F
      //   1244: f2d
      //   1245: invokevirtual 439	org/json/JSONObject:put	(Ljava/lang/String;D)Lorg/json/JSONObject;
      //   1248: pop
      //   1249: aload 12
      //   1251: astore 7
      //   1253: aload 13
      //   1255: astore_1
      //   1256: aload_0
      //   1257: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   1260: invokestatic 152	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   1263: invokestatic 442	com/appodeal/ads/ak:f	(Landroid/content/Context;)Landroid/util/Pair;
      //   1266: astore 6
      //   1268: aload 12
      //   1270: astore 7
      //   1272: aload 13
      //   1274: astore_1
      //   1275: aload 9
      //   1277: ldc_w 444
      //   1280: aload 6
      //   1282: getfield 450	android/util/Pair:first	Ljava/lang/Object;
      //   1285: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1288: pop
      //   1289: aload 12
      //   1291: astore 7
      //   1293: aload 13
      //   1295: astore_1
      //   1296: aload 9
      //   1298: ldc_w 452
      //   1301: aload 6
      //   1303: getfield 455	android/util/Pair:second	Ljava/lang/Object;
      //   1306: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1309: pop
      //   1310: aload 12
      //   1312: astore 7
      //   1314: aload 13
      //   1316: astore_1
      //   1317: aload_0
      //   1318: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   1321: invokestatic 152	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   1324: invokestatic 458	com/appodeal/ads/ak:n	(Landroid/content/Context;)Z
      //   1327: ifeq +1840 -> 3167
      //   1330: aload 12
      //   1332: astore 7
      //   1334: aload 13
      //   1336: astore_1
      //   1337: aload 9
      //   1339: ldc_w 460
      //   1342: ldc_w 462
      //   1345: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1348: pop
      //   1349: aload 12
      //   1351: astore 7
      //   1353: aload 13
      //   1355: astore_1
      //   1356: getstatic 467	android/os/Build:MANUFACTURER	Ljava/lang/String;
      //   1359: ldc_w 469
      //   1362: invokevirtual 99	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1365: ifeq +3473 -> 4838
      //   1368: ldc_w 471
      //   1371: astore 6
      //   1373: aload 12
      //   1375: astore 7
      //   1377: aload 13
      //   1379: astore_1
      //   1380: aload 9
      //   1382: ldc_w 473
      //   1385: aload 6
      //   1387: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1390: pop
      //   1391: aload 13
      //   1393: astore_1
      //   1394: aload 15
      //   1396: aload 10
      //   1398: invokevirtual 476	android/content/pm/PackageManager:getInstallerPackageName	(Ljava/lang/String;)Ljava/lang/String;
      //   1401: astore 7
      //   1403: aload 7
      //   1405: astore 6
      //   1407: aload 7
      //   1409: ifnonnull +8 -> 1417
      //   1412: ldc_w 478
      //   1415: astore 6
      //   1417: aload 13
      //   1419: astore_1
      //   1420: aload 9
      //   1422: ldc_w 480
      //   1425: aload 6
      //   1427: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1430: pop
      //   1431: aload 12
      //   1433: astore 7
      //   1435: aload 13
      //   1437: astore_1
      //   1438: aload 9
      //   1440: ldc_w 482
      //   1443: getstatic 467	android/os/Build:MANUFACTURER	Ljava/lang/String;
      //   1446: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1449: pop
      //   1450: aload 12
      //   1452: astore 7
      //   1454: aload 13
      //   1456: astore_1
      //   1457: aload 9
      //   1459: ldc_w 484
      //   1462: ldc_w 486
      //   1465: iconst_2
      //   1466: anewarray 111	java/lang/Object
      //   1469: dup
      //   1470: iconst_0
      //   1471: getstatic 467	android/os/Build:MANUFACTURER	Ljava/lang/String;
      //   1474: aastore
      //   1475: dup
      //   1476: iconst_1
      //   1477: getstatic 489	android/os/Build:MODEL	Ljava/lang/String;
      //   1480: aastore
      //   1481: invokestatic 115	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   1484: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1487: pop
      //   1488: aload 12
      //   1490: astore 7
      //   1492: aload 13
      //   1494: astore_1
      //   1495: aload_0
      //   1496: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   1499: invokestatic 152	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   1502: invokestatic 491	com/appodeal/ads/ak:d	(Landroid/content/Context;)Landroid/util/Pair;
      //   1505: astore 6
      //   1507: aload 12
      //   1509: astore 7
      //   1511: aload 13
      //   1513: astore_1
      //   1514: aload 9
      //   1516: ldc_w 493
      //   1519: aload 6
      //   1521: getfield 450	android/util/Pair:first	Ljava/lang/Object;
      //   1524: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1527: pop
      //   1528: aload 12
      //   1530: astore 7
      //   1532: aload 13
      //   1534: astore_1
      //   1535: aload 9
      //   1537: ldc_w 495
      //   1540: aload 6
      //   1542: getfield 455	android/util/Pair:second	Ljava/lang/Object;
      //   1545: checkcast 446	android/util/Pair
      //   1548: getfield 450	android/util/Pair:first	Ljava/lang/Object;
      //   1551: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1554: pop
      //   1555: aload 12
      //   1557: astore 7
      //   1559: aload 13
      //   1561: astore_1
      //   1562: aload 9
      //   1564: ldc_w 497
      //   1567: aload 6
      //   1569: getfield 455	android/util/Pair:second	Ljava/lang/Object;
      //   1572: checkcast 446	android/util/Pair
      //   1575: getfield 455	android/util/Pair:second	Ljava/lang/Object;
      //   1578: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1581: pop
      //   1582: aload 12
      //   1584: astore 7
      //   1586: aload 13
      //   1588: astore_1
      //   1589: aload 9
      //   1591: ldc_w 499
      //   1594: aload 8
      //   1596: getfield 501	com/appodeal/ads/ak$a:b	Ljava/lang/String;
      //   1599: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1602: pop
      //   1603: aload 12
      //   1605: astore 7
      //   1607: aload 13
      //   1609: astore_1
      //   1610: aload 9
      //   1612: ldc_w 503
      //   1615: aload 8
      //   1617: getfield 504	com/appodeal/ads/ak$a:c	Z
      //   1620: invokevirtual 349	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   1623: pop
      //   1624: aload 12
      //   1626: astore 7
      //   1628: aload 13
      //   1630: astore_1
      //   1631: aload 9
      //   1633: ldc_w 506
      //   1636: aload_0
      //   1637: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   1640: invokestatic 152	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   1643: invokestatic 508	com/appodeal/ads/ak:c	(Landroid/content/Context;)Ljava/lang/String;
      //   1646: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1649: pop
      //   1650: aload 12
      //   1652: astore 7
      //   1654: aload 13
      //   1656: astore_1
      //   1657: aload 9
      //   1659: ldc_w 510
      //   1662: invokestatic 516	java/util/Locale:getDefault	()Ljava/util/Locale;
      //   1665: invokevirtual 519	java/util/Locale:toString	()Ljava/lang/String;
      //   1668: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1671: pop
      //   1672: aload 12
      //   1674: astore 7
      //   1676: aload 13
      //   1678: astore_1
      //   1679: ldc_w 521
      //   1682: invokestatic 527	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
      //   1685: getstatic 531	java/util/Locale:ENGLISH	Ljava/util/Locale;
      //   1688: invokestatic 534	java/util/Calendar:getInstance	(Ljava/util/TimeZone;Ljava/util/Locale;)Ljava/util/Calendar;
      //   1691: astore 6
      //   1693: aload 12
      //   1695: astore 7
      //   1697: aload 13
      //   1699: astore_1
      //   1700: aload 9
      //   1702: ldc_w 536
      //   1705: new 538	java/text/SimpleDateFormat
      //   1708: dup
      //   1709: ldc_w 539
      //   1712: getstatic 531	java/util/Locale:ENGLISH	Ljava/util/Locale;
      //   1715: invokespecial 542	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
      //   1718: aload 6
      //   1720: invokevirtual 546	java/util/Calendar:getTime	()Ljava/util/Date;
      //   1723: invokevirtual 549	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
      //   1726: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1729: pop
      //   1730: aload 12
      //   1732: astore 7
      //   1734: aload 13
      //   1736: astore_1
      //   1737: aload 9
      //   1739: ldc_w 551
      //   1742: invokestatic 242	java/lang/System:currentTimeMillis	()J
      //   1745: ldc2_w 552
      //   1748: ldiv
      //   1749: invokevirtual 556	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   1752: pop
      //   1753: aload 12
      //   1755: astore 7
      //   1757: aload 13
      //   1759: astore_1
      //   1760: aload 9
      //   1762: ldc_w 558
      //   1765: ldc_w 560
      //   1768: invokestatic 563	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
      //   1771: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1774: pop
      //   1775: aload 12
      //   1777: astore 7
      //   1779: aload 13
      //   1781: astore_1
      //   1782: aload 9
      //   1784: ldc_w 565
      //   1787: invokestatic 567	com/appodeal/ads/ak:a	()Z
      //   1790: invokevirtual 349	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   1793: pop
      //   1794: aload 12
      //   1796: astore 7
      //   1798: aload 13
      //   1800: astore_1
      //   1801: aload_0
      //   1802: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   1805: invokestatic 152	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   1808: invokestatic 572	com/appodeal/ads/utils/b:c	(Landroid/content/Context;)V
      //   1811: aload 12
      //   1813: astore 7
      //   1815: aload 13
      //   1817: astore_1
      //   1818: aload 9
      //   1820: ldc_w 574
      //   1823: aload 14
      //   1825: invokestatic 577	com/appodeal/ads/utils/b:a	(Landroid/content/SharedPreferences;)J
      //   1828: invokevirtual 556	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   1831: pop
      //   1832: aload 12
      //   1834: astore 7
      //   1836: aload 13
      //   1838: astore_1
      //   1839: aload 9
      //   1841: ldc_w 579
      //   1844: invokestatic 581	com/appodeal/ads/utils/b:b	()J
      //   1847: invokevirtual 556	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   1850: pop
      //   1851: aload 12
      //   1853: astore 7
      //   1855: aload 13
      //   1857: astore_1
      //   1858: aload 9
      //   1860: ldc_w 583
      //   1863: aload 14
      //   1865: invokestatic 585	com/appodeal/ads/utils/b:b	(Landroid/content/SharedPreferences;)J
      //   1868: invokevirtual 556	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   1871: pop
      //   1872: aload 12
      //   1874: astore 7
      //   1876: aload 13
      //   1878: astore_1
      //   1879: aload 14
      //   1881: ldc_w 587
      //   1884: aconst_null
      //   1885: invokeinterface 258 3 0
      //   1890: astore 6
      //   1892: aload 6
      //   1894: ifnull +28 -> 1922
      //   1897: aload 12
      //   1899: astore 7
      //   1901: aload 13
      //   1903: astore_1
      //   1904: aload 9
      //   1906: ldc_w 587
      //   1909: new 34	org/json/JSONObject
      //   1912: dup
      //   1913: aload 6
      //   1915: invokespecial 589	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   1918: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1921: pop
      //   1922: aload 12
      //   1924: astore 7
      //   1926: aload 13
      //   1928: astore_1
      //   1929: aload_0
      //   1930: getfield 234	com/appodeal/ads/q$b:i	Z
      //   1933: ifeq +1831 -> 3764
      //   1936: aload 12
      //   1938: astore 7
      //   1940: aload 13
      //   1942: astore_1
      //   1943: new 143	org/json/JSONArray
      //   1946: dup
      //   1947: invokespecial 590	org/json/JSONArray:<init>	()V
      //   1950: astore 6
      //   1952: aload 12
      //   1954: astore 7
      //   1956: aload 13
      //   1958: astore_1
      //   1959: aload_0
      //   1960: getfield 105	com/appodeal/ads/q$b:b	Z
      //   1963: ifeq +1243 -> 3206
      //   1966: aload 12
      //   1968: astore 7
      //   1970: aload 13
      //   1972: astore_1
      //   1973: aload_0
      //   1974: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   1977: invokestatic 152	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   1980: invokestatic 593	com/appodeal/ads/k:a	(Landroid/content/Context;)Ljava/util/List;
      //   1983: invokeinterface 599 1 0
      //   1988: astore 8
      //   1990: aload 12
      //   1992: astore 7
      //   1994: aload 13
      //   1996: astore_1
      //   1997: aload 8
      //   1999: invokeinterface 604 1 0
      //   2004: ifeq +1202 -> 3206
      //   2007: aload 12
      //   2009: astore 7
      //   2011: aload 13
      //   2013: astore_1
      //   2014: aload 8
      //   2016: invokeinterface 608 1 0
      //   2021: checkcast 610	com/appodeal/ads/l
      //   2024: astore 10
      //   2026: aload 12
      //   2028: astore 7
      //   2030: aload 13
      //   2032: astore_1
      //   2033: aload 10
      //   2035: invokevirtual 613	com/appodeal/ads/l:h	()Lcom/appodeal/ads/o;
      //   2038: ifnull -48 -> 1990
      //   2041: aload 12
      //   2043: astore 7
      //   2045: aload 13
      //   2047: astore_1
      //   2048: aload 6
      //   2050: aload 10
      //   2052: invokevirtual 615	com/appodeal/ads/l:a	()Ljava/lang/String;
      //   2055: invokevirtual 618	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   2058: pop
      //   2059: goto -69 -> 1990
      //   2062: astore 6
      //   2064: aload 12
      //   2066: astore 7
      //   2068: aload 13
      //   2070: astore_1
      //   2071: aload 6
      //   2073: invokestatic 132	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2076: iload 4
      //   2078: ifeq +286 -> 2364
      //   2081: aload 13
      //   2083: astore_1
      //   2084: new 143	org/json/JSONArray
      //   2087: dup
      //   2088: invokespecial 590	org/json/JSONArray:<init>	()V
      //   2091: astore 6
      //   2093: aload 13
      //   2095: astore_1
      //   2096: aload 15
      //   2098: iconst_0
      //   2099: invokevirtual 622	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
      //   2102: astore 8
      //   2104: aload 13
      //   2106: astore_1
      //   2107: ldc_w 624
      //   2110: invokestatic 630	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
      //   2113: astore 7
      //   2115: aload 13
      //   2117: astore_1
      //   2118: aload 8
      //   2120: invokeinterface 599 1 0
      //   2125: astore 8
      //   2127: aload 13
      //   2129: astore_1
      //   2130: aload 8
      //   2132: invokeinterface 604 1 0
      //   2137: ifeq +1974 -> 4111
      //   2140: aload 13
      //   2142: astore_1
      //   2143: aload 8
      //   2145: invokeinterface 608 1 0
      //   2150: checkcast 407	android/content/pm/ApplicationInfo
      //   2153: getfield 633	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   2156: astore 10
      //   2158: aload 13
      //   2160: astore_1
      //   2161: aload 7
      //   2163: aload 10
      //   2165: invokevirtual 637	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
      //   2168: invokevirtual 642	java/util/regex/Matcher:matches	()Z
      //   2171: ifne -44 -> 2127
      //   2174: aload 13
      //   2176: astore_1
      //   2177: aload 10
      //   2179: ldc_w 361
      //   2182: invokevirtual 99	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2185: ifne -58 -> 2127
      //   2188: aload 13
      //   2190: astore_1
      //   2191: aload 6
      //   2193: aload 10
      //   2195: invokevirtual 618	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   2198: pop
      //   2199: goto -72 -> 2127
      //   2202: astore 6
      //   2204: aload 12
      //   2206: astore 7
      //   2208: aload 13
      //   2210: astore_1
      //   2211: aload 6
      //   2213: invokestatic 132	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2216: aload 13
      //   2218: astore_1
      //   2219: aload 9
      //   2221: ldc_w 644
      //   2224: aload_0
      //   2225: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   2228: invokestatic 152	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   2231: invokestatic 649	com/appodeal/ads/utils/n:a	(Landroid/content/Context;)Lorg/json/JSONArray;
      //   2234: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2237: pop
      //   2238: aload 13
      //   2240: astore_1
      //   2241: aload 9
      //   2243: ldc_w 651
      //   2246: aload_0
      //   2247: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   2250: invokestatic 152	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   2253: invokestatic 655	com/appodeal/ads/utils/o:a	(Landroid/content/Context;)Ljava/lang/String;
      //   2256: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2259: pop
      //   2260: aload 13
      //   2262: astore_1
      //   2263: aload 9
      //   2265: ldc_w 657
      //   2268: aload_0
      //   2269: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   2272: invokestatic 152	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   2275: invokestatic 661	com/appodeal/ads/Appodeal:getUserSettings	(Landroid/content/Context;)Lcom/appodeal/ads/UserSettings;
      //   2278: invokevirtual 663	com/appodeal/ads/UserSettings:a	()Lorg/json/JSONObject;
      //   2281: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2284: pop
      //   2285: aload 12
      //   2287: astore 7
      //   2289: aload 13
      //   2291: astore_1
      //   2292: aload 14
      //   2294: invokeinterface 309 1 0
      //   2299: astore 6
      //   2301: aload 12
      //   2303: astore 7
      //   2305: aload 13
      //   2307: astore_1
      //   2308: aload 6
      //   2310: ldc -43
      //   2312: invokestatic 242	java/lang/System:currentTimeMillis	()J
      //   2315: invokeinterface 667 4 0
      //   2320: pop
      //   2321: aload 12
      //   2323: astore 7
      //   2325: aload 13
      //   2327: astore_1
      //   2328: aload 6
      //   2330: ldc -12
      //   2332: iconst_0
      //   2333: invokeinterface 671 3 0
      //   2338: pop
      //   2339: aload 12
      //   2341: astore 7
      //   2343: aload 13
      //   2345: astore_1
      //   2346: aload 6
      //   2348: invokeinterface 318 1 0
      //   2353: aload 12
      //   2355: astore 7
      //   2357: aload 13
      //   2359: astore_1
      //   2360: iconst_0
      //   2361: putstatic 232	com/appodeal/ads/UserSettings:sendingInProgress	Z
      //   2364: aload 12
      //   2366: astore 7
      //   2368: aload 13
      //   2370: astore_1
      //   2371: aload_0
      //   2372: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   2375: invokestatic 674	com/appodeal/ads/q:i	(Lcom/appodeal/ads/q;)Lcom/appodeal/ads/d/h;
      //   2378: ifnull +55 -> 2433
      //   2381: aload 12
      //   2383: astore 7
      //   2385: aload 13
      //   2387: astore_1
      //   2388: aload_0
      //   2389: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   2392: invokestatic 272	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   2395: ldc_w 676
      //   2398: invokevirtual 99	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2401: ifeq +1761 -> 4162
      //   2404: aload 12
      //   2406: astore 7
      //   2408: aload 13
      //   2410: astore_1
      //   2411: aload 9
      //   2413: ldc_w 678
      //   2416: aload_0
      //   2417: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   2420: invokestatic 674	com/appodeal/ads/q:i	(Lcom/appodeal/ads/q;)Lcom/appodeal/ads/d/h;
      //   2423: invokevirtual 681	com/appodeal/ads/d/h:a	()Lorg/json/JSONObject;
      //   2426: invokevirtual 682	org/json/JSONObject:toString	()Ljava/lang/String;
      //   2429: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2432: pop
      //   2433: aload 12
      //   2435: astore 7
      //   2437: aload 13
      //   2439: astore_1
      //   2440: aload_0
      //   2441: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   2444: invokestatic 272	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   2447: ldc_w 676
      //   2450: invokevirtual 99	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2453: ifne +2393 -> 4846
      //   2456: aload 12
      //   2458: astore 7
      //   2460: aload 13
      //   2462: astore_1
      //   2463: aload_0
      //   2464: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   2467: invokestatic 272	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   2470: ldc 78
      //   2472: invokevirtual 99	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2475: ifne +2371 -> 4846
      //   2478: aload 12
      //   2480: astore 7
      //   2482: aload 13
      //   2484: astore_1
      //   2485: aload_0
      //   2486: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   2489: invokestatic 272	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   2492: ldc 84
      //   2494: invokevirtual 99	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2497: ifne +2349 -> 4846
      //   2500: aload 12
      //   2502: astore 7
      //   2504: aload 13
      //   2506: astore_1
      //   2507: aload_0
      //   2508: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   2511: invokestatic 272	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   2514: ldc 103
      //   2516: invokevirtual 99	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2519: ifne +2327 -> 4846
      //   2522: aload 12
      //   2524: astore 7
      //   2526: aload 13
      //   2528: astore_1
      //   2529: aload_0
      //   2530: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   2533: invokestatic 272	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   2536: ldc_w 274
      //   2539: invokevirtual 99	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2542: ifne +2304 -> 4846
      //   2545: iconst_1
      //   2546: istore 4
      //   2548: iload 4
      //   2550: ifeq +1832 -> 4382
      //   2553: aload 12
      //   2555: astore 7
      //   2557: aload 13
      //   2559: astore_1
      //   2560: new 684	java/net/URL
      //   2563: dup
      //   2564: ldc_w 686
      //   2567: iconst_4
      //   2568: anewarray 111	java/lang/Object
      //   2571: dup
      //   2572: iconst_0
      //   2573: invokestatic 690	com/appodeal/ads/utils/e:c	()I
      //   2576: invokestatic 693	com/appodeal/ads/utils/e:a	(I)Ljava/lang/String;
      //   2579: aastore
      //   2580: dup
      //   2581: iconst_1
      //   2582: invokestatic 695	com/appodeal/ads/utils/e:b	()Ljava/lang/String;
      //   2585: aastore
      //   2586: dup
      //   2587: iconst_2
      //   2588: invokestatic 690	com/appodeal/ads/utils/e:c	()I
      //   2591: invokestatic 700	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   2594: aastore
      //   2595: dup
      //   2596: iconst_3
      //   2597: ldc_w 702
      //   2600: aastore
      //   2601: invokestatic 115	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   2604: invokespecial 703	java/net/URL:<init>	(Ljava/lang/String;)V
      //   2607: astore 8
      //   2609: aload 12
      //   2611: astore 7
      //   2613: aload 13
      //   2615: astore_1
      //   2616: aload 8
      //   2618: invokevirtual 707	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   2621: astore 6
      //   2623: aload 8
      //   2625: invokevirtual 710	java/net/URL:getProtocol	()Ljava/lang/String;
      //   2628: ldc_w 712
      //   2631: invokevirtual 99	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2634: ifeq +79 -> 2713
      //   2637: new 714	java/util/ArrayList
      //   2640: dup
      //   2641: invokespecial 715	java/util/ArrayList:<init>	()V
      //   2644: astore_1
      //   2645: aload_1
      //   2646: ldc_w 717
      //   2649: invokeinterface 719 2 0
      //   2654: pop
      //   2655: aload_1
      //   2656: ldc_w 721
      //   2659: invokeinterface 719 2 0
      //   2664: pop
      //   2665: new 723	com/appodeal/ads/utils/c
      //   2668: dup
      //   2669: aload_1
      //   2670: ldc2_w 724
      //   2673: invokespecial 728	com/appodeal/ads/utils/c:<init>	(Ljava/util/List;J)V
      //   2676: astore_1
      //   2677: ldc_w 730
      //   2680: invokestatic 735	javax/net/ssl/SSLContext:getInstance	(Ljava/lang/String;)Ljavax/net/ssl/SSLContext;
      //   2683: astore 7
      //   2685: aload 7
      //   2687: aconst_null
      //   2688: iconst_1
      //   2689: anewarray 737	javax/net/ssl/TrustManager
      //   2692: dup
      //   2693: iconst_0
      //   2694: aload_1
      //   2695: aastore
      //   2696: aconst_null
      //   2697: invokevirtual 741	javax/net/ssl/SSLContext:init	([Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Ljava/security/SecureRandom;)V
      //   2700: aload 6
      //   2702: checkcast 260	javax/net/ssl/HttpsURLConnection
      //   2705: aload 7
      //   2707: invokevirtual 745	javax/net/ssl/SSLContext:getSocketFactory	()Ljavax/net/ssl/SSLSocketFactory;
      //   2710: invokevirtual 749	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
      //   2713: aload 6
      //   2715: sipush 20000
      //   2718: invokevirtual 755	java/net/URLConnection:setConnectTimeout	(I)V
      //   2721: aload 6
      //   2723: sipush 20000
      //   2726: invokevirtual 758	java/net/URLConnection:setReadTimeout	(I)V
      //   2729: aload 6
      //   2731: ldc_w 760
      //   2734: ldc_w 762
      //   2737: invokevirtual 766	java/net/URLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   2740: aload 6
      //   2742: iconst_1
      //   2743: invokevirtual 770	java/net/URLConnection:setDoOutput	(Z)V
      //   2746: new 772	java/io/ByteArrayOutputStream
      //   2749: dup
      //   2750: invokespecial 773	java/io/ByteArrayOutputStream:<init>	()V
      //   2753: astore 7
      //   2755: new 775	java/util/zip/GZIPOutputStream
      //   2758: dup
      //   2759: aload 7
      //   2761: invokespecial 778	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   2764: astore_1
      //   2765: aload_1
      //   2766: aload 9
      //   2768: invokevirtual 682	org/json/JSONObject:toString	()Ljava/lang/String;
      //   2771: ldc_w 780
      //   2774: invokevirtual 784	java/lang/String:getBytes	(Ljava/lang/String;)[B
      //   2777: invokevirtual 788	java/util/zip/GZIPOutputStream:write	([B)V
      //   2780: aload_1
      //   2781: invokevirtual 791	java/util/zip/GZIPOutputStream:close	()V
      //   2784: aload 7
      //   2786: invokevirtual 795	java/io/ByteArrayOutputStream:toByteArray	()[B
      //   2789: iconst_0
      //   2790: invokestatic 801	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
      //   2793: astore_1
      //   2794: aload 6
      //   2796: invokevirtual 805	java/net/URLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   2799: aload_1
      //   2800: invokestatic 808	com/appodeal/ads/ak:a	(Ljava/io/OutputStream;Ljava/lang/String;)V
      //   2803: aload 6
      //   2805: invokevirtual 812	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
      //   2808: invokestatic 815	com/appodeal/ads/ak:a	(Ljava/io/InputStream;)Ljava/lang/String;
      //   2811: astore_1
      //   2812: aload_1
      //   2813: ifnull +40 -> 2853
      //   2816: aload_1
      //   2817: invokevirtual 818	java/lang/String:isEmpty	()Z
      //   2820: ifne +33 -> 2853
      //   2823: aload_1
      //   2824: ldc_w 820
      //   2827: invokevirtual 99	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2830: ifne +23 -> 2853
      //   2833: aload_0
      //   2834: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   2837: invokestatic 272	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   2840: ldc_w 676
      //   2843: invokevirtual 99	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2846: istore 5
      //   2848: iload 5
      //   2850: ifeq +5 -> 2855
      //   2853: aconst_null
      //   2854: astore_1
      //   2855: aload_1
      //   2856: astore 8
      //   2858: aload 6
      //   2860: astore 7
      //   2862: aload 6
      //   2864: astore_1
      //   2865: aload_0
      //   2866: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   2869: invokestatic 152	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   2872: ldc_w 822
      //   2875: iconst_0
      //   2876: invokevirtual 205	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   2879: astore 9
      //   2881: aload 8
      //   2883: ifnonnull +1721 -> 4604
      //   2886: iload 4
      //   2888: ifeq +1674 -> 4562
      //   2891: aload 6
      //   2893: astore 7
      //   2895: aload 6
      //   2897: astore_1
      //   2898: aload 9
      //   2900: aload_0
      //   2901: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   2904: invokestatic 272	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   2907: invokeinterface 826 2 0
      //   2912: ifeq +1650 -> 4562
      //   2915: aload 6
      //   2917: astore 7
      //   2919: aload 6
      //   2921: astore_1
      //   2922: ldc_w 828
      //   2925: invokestatic 323	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   2928: aload 6
      //   2930: astore 7
      //   2932: aload 6
      //   2934: astore_1
      //   2935: aload 9
      //   2937: aload_0
      //   2938: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   2941: invokestatic 272	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   2944: ldc_w 830
      //   2947: invokeinterface 258 3 0
      //   2952: astore 8
      //   2954: aload 6
      //   2956: astore 7
      //   2958: aload 6
      //   2960: astore_1
      //   2961: new 34	org/json/JSONObject
      //   2964: dup
      //   2965: aload 8
      //   2967: invokespecial 589	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   2970: astore 8
      //   2972: aload 6
      //   2974: astore 7
      //   2976: aload 6
      //   2978: astore_1
      //   2979: aload 8
      //   2981: invokestatic 832	com/appodeal/ads/Appodeal:a	(Lorg/json/JSONObject;)V
      //   2984: aload 8
      //   2986: ifnull +35 -> 3021
      //   2989: aload 6
      //   2991: astore_1
      //   2992: aload 8
      //   2994: ldc_w 834
      //   2997: invokevirtual 838	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   3000: putstatic 842	com/appodeal/ads/UserSettings:userData	Lorg/json/JSONObject;
      //   3003: aload 6
      //   3005: astore_1
      //   3006: getstatic 181	com/appodeal/ads/f/g:a	Ljava/lang/Long;
      //   3009: ifnonnull +12 -> 3021
      //   3012: aload 6
      //   3014: astore_1
      //   3015: aload_0
      //   3016: aload 8
      //   3018: invokespecial 844	com/appodeal/ads/q$b:b	(Lorg/json/JSONObject;)V
      //   3021: aload 6
      //   3023: ifnull +19 -> 3042
      //   3026: aload 6
      //   3028: instanceof 260
      //   3031: ifeq +1708 -> 4739
      //   3034: aload 6
      //   3036: checkcast 260	javax/net/ssl/HttpsURLConnection
      //   3039: invokevirtual 263	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   3042: aload 8
      //   3044: areturn
      //   3045: ldc_w 846
      //   3048: astore 6
      //   3050: goto -2610 -> 440
      //   3053: astore 6
      //   3055: aload 12
      //   3057: astore 7
      //   3059: aload 13
      //   3061: astore_1
      //   3062: aload 6
      //   3064: invokestatic 132	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   3067: aload 9
      //   3069: astore_1
      //   3070: aload 8
      //   3072: astore 6
      //   3074: goto -2516 -> 558
      //   3077: astore 16
      //   3079: aload 12
      //   3081: astore 7
      //   3083: aload 13
      //   3085: astore_1
      //   3086: aload 16
      //   3088: invokestatic 132	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   3091: goto -1976 -> 1115
      //   3094: astore 6
      //   3096: aload 7
      //   3098: astore_1
      //   3099: aload 6
      //   3101: invokestatic 132	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   3104: aload 7
      //   3106: ifnull +19 -> 3125
      //   3109: aload 7
      //   3111: instanceof 260
      //   3114: ifeq +1644 -> 4758
      //   3117: aload 7
      //   3119: checkcast 260	javax/net/ssl/HttpsURLConnection
      //   3122: invokevirtual 263	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   3125: aconst_null
      //   3126: areturn
      //   3127: astore 16
      //   3129: aload 12
      //   3131: astore 7
      //   3133: aload 13
      //   3135: astore_1
      //   3136: aload 16
      //   3138: invokestatic 132	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   3141: goto -1995 -> 1146
      //   3144: astore 6
      //   3146: aload_1
      //   3147: ifnull +17 -> 3164
      //   3150: aload_1
      //   3151: instanceof 260
      //   3154: ifeq +1623 -> 4777
      //   3157: aload_1
      //   3158: checkcast 260	javax/net/ssl/HttpsURLConnection
      //   3161: invokevirtual 263	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   3164: aload 6
      //   3166: athrow
      //   3167: aload 12
      //   3169: astore 7
      //   3171: aload 13
      //   3173: astore_1
      //   3174: aload 9
      //   3176: ldc_w 460
      //   3179: ldc_w 848
      //   3182: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3185: pop
      //   3186: goto -1837 -> 1349
      //   3189: astore 6
      //   3191: aload 12
      //   3193: astore 7
      //   3195: aload 13
      //   3197: astore_1
      //   3198: aload 6
      //   3200: invokestatic 132	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   3203: goto -1772 -> 1431
      //   3206: aload 12
      //   3208: astore 7
      //   3210: aload 13
      //   3212: astore_1
      //   3213: aload_0
      //   3214: getfield 86	com/appodeal/ads/q$b:e	Z
      //   3217: ifeq +99 -> 3316
      //   3220: aload 12
      //   3222: astore 7
      //   3224: aload 13
      //   3226: astore_1
      //   3227: aload_0
      //   3228: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   3231: invokestatic 152	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   3234: invokestatic 849	com/appodeal/ads/ae:a	(Landroid/content/Context;)Ljava/util/List;
      //   3237: invokeinterface 599 1 0
      //   3242: astore 8
      //   3244: aload 12
      //   3246: astore 7
      //   3248: aload 13
      //   3250: astore_1
      //   3251: aload 8
      //   3253: invokeinterface 604 1 0
      //   3258: ifeq +58 -> 3316
      //   3261: aload 12
      //   3263: astore 7
      //   3265: aload 13
      //   3267: astore_1
      //   3268: aload 8
      //   3270: invokeinterface 608 1 0
      //   3275: checkcast 851	com/appodeal/ads/am
      //   3278: astore 10
      //   3280: aload 12
      //   3282: astore 7
      //   3284: aload 13
      //   3286: astore_1
      //   3287: aload 10
      //   3289: invokevirtual 854	com/appodeal/ads/am:h	()Lcom/appodeal/ads/an;
      //   3292: ifnull -48 -> 3244
      //   3295: aload 12
      //   3297: astore 7
      //   3299: aload 13
      //   3301: astore_1
      //   3302: aload 6
      //   3304: aload 10
      //   3306: invokevirtual 855	com/appodeal/ads/am:a	()Ljava/lang/String;
      //   3309: invokevirtual 618	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   3312: pop
      //   3313: goto -69 -> 3244
      //   3316: aload 12
      //   3318: astore 7
      //   3320: aload 13
      //   3322: astore_1
      //   3323: aload_0
      //   3324: getfield 88	com/appodeal/ads/q$b:f	Z
      //   3327: ifeq +99 -> 3426
      //   3330: aload 12
      //   3332: astore 7
      //   3334: aload 13
      //   3336: astore_1
      //   3337: aload_0
      //   3338: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   3341: invokestatic 152	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   3344: invokestatic 856	com/appodeal/ads/ah:a	(Landroid/content/Context;)Ljava/util/List;
      //   3347: invokeinterface 599 1 0
      //   3352: astore 8
      //   3354: aload 12
      //   3356: astore 7
      //   3358: aload 13
      //   3360: astore_1
      //   3361: aload 8
      //   3363: invokeinterface 604 1 0
      //   3368: ifeq +58 -> 3426
      //   3371: aload 12
      //   3373: astore 7
      //   3375: aload 13
      //   3377: astore_1
      //   3378: aload 8
      //   3380: invokeinterface 608 1 0
      //   3385: checkcast 851	com/appodeal/ads/am
      //   3388: astore 10
      //   3390: aload 12
      //   3392: astore 7
      //   3394: aload 13
      //   3396: astore_1
      //   3397: aload 10
      //   3399: invokevirtual 854	com/appodeal/ads/am:h	()Lcom/appodeal/ads/an;
      //   3402: ifnull -48 -> 3354
      //   3405: aload 12
      //   3407: astore 7
      //   3409: aload 13
      //   3411: astore_1
      //   3412: aload 6
      //   3414: aload 10
      //   3416: invokevirtual 855	com/appodeal/ads/am:a	()Ljava/lang/String;
      //   3419: invokevirtual 618	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   3422: pop
      //   3423: goto -69 -> 3354
      //   3426: aload 12
      //   3428: astore 7
      //   3430: aload 13
      //   3432: astore_1
      //   3433: aload_0
      //   3434: getfield 117	com/appodeal/ads/q$b:c	Z
      //   3437: ifeq +99 -> 3536
      //   3440: aload 12
      //   3442: astore 7
      //   3444: aload 13
      //   3446: astore_1
      //   3447: aload_0
      //   3448: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   3451: invokestatic 152	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   3454: invokestatic 857	com/appodeal/ads/c:a	(Landroid/content/Context;)Ljava/util/List;
      //   3457: invokeinterface 599 1 0
      //   3462: astore 8
      //   3464: aload 12
      //   3466: astore 7
      //   3468: aload 13
      //   3470: astore_1
      //   3471: aload 8
      //   3473: invokeinterface 604 1 0
      //   3478: ifeq +58 -> 3536
      //   3481: aload 12
      //   3483: astore 7
      //   3485: aload 13
      //   3487: astore_1
      //   3488: aload 8
      //   3490: invokeinterface 608 1 0
      //   3495: checkcast 859	com/appodeal/ads/d
      //   3498: astore 10
      //   3500: aload 12
      //   3502: astore 7
      //   3504: aload 13
      //   3506: astore_1
      //   3507: aload 10
      //   3509: invokevirtual 862	com/appodeal/ads/d:f	()Lcom/appodeal/ads/g;
      //   3512: ifnull -48 -> 3464
      //   3515: aload 12
      //   3517: astore 7
      //   3519: aload 13
      //   3521: astore_1
      //   3522: aload 6
      //   3524: aload 10
      //   3526: invokevirtual 863	com/appodeal/ads/d:a	()Ljava/lang/String;
      //   3529: invokevirtual 618	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   3532: pop
      //   3533: goto -69 -> 3464
      //   3536: aload 12
      //   3538: astore 7
      //   3540: aload 13
      //   3542: astore_1
      //   3543: aload_0
      //   3544: getfield 121	com/appodeal/ads/q$b:d	Z
      //   3547: ifeq +99 -> 3646
      //   3550: aload 12
      //   3552: astore 7
      //   3554: aload 13
      //   3556: astore_1
      //   3557: aload_0
      //   3558: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   3561: invokestatic 152	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   3564: invokestatic 864	com/appodeal/ads/r:a	(Landroid/content/Context;)Ljava/util/List;
      //   3567: invokeinterface 599 1 0
      //   3572: astore 8
      //   3574: aload 12
      //   3576: astore 7
      //   3578: aload 13
      //   3580: astore_1
      //   3581: aload 8
      //   3583: invokeinterface 604 1 0
      //   3588: ifeq +58 -> 3646
      //   3591: aload 12
      //   3593: astore 7
      //   3595: aload 13
      //   3597: astore_1
      //   3598: aload 8
      //   3600: invokeinterface 608 1 0
      //   3605: checkcast 866	com/appodeal/ads/s
      //   3608: astore 10
      //   3610: aload 12
      //   3612: astore 7
      //   3614: aload 13
      //   3616: astore_1
      //   3617: aload 10
      //   3619: invokevirtual 869	com/appodeal/ads/s:f	()Lcom/appodeal/ads/v;
      //   3622: ifnull -48 -> 3574
      //   3625: aload 12
      //   3627: astore 7
      //   3629: aload 13
      //   3631: astore_1
      //   3632: aload 6
      //   3634: aload 10
      //   3636: invokevirtual 870	com/appodeal/ads/s:a	()Ljava/lang/String;
      //   3639: invokevirtual 618	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   3642: pop
      //   3643: goto -69 -> 3574
      //   3646: aload 12
      //   3648: astore 7
      //   3650: aload 13
      //   3652: astore_1
      //   3653: aload_0
      //   3654: getfield 125	com/appodeal/ads/q$b:g	Z
      //   3657: ifeq +89 -> 3746
      //   3660: aload 12
      //   3662: astore 7
      //   3664: aload 13
      //   3666: astore_1
      //   3667: aload_0
      //   3668: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   3671: invokestatic 152	com/appodeal/ads/q:b	(Lcom/appodeal/ads/q;)Landroid/content/Context;
      //   3674: invokestatic 871	com/appodeal/ads/x:a	(Landroid/content/Context;)Ljava/util/List;
      //   3677: invokeinterface 599 1 0
      //   3682: astore 8
      //   3684: aload 12
      //   3686: astore 7
      //   3688: aload 13
      //   3690: astore_1
      //   3691: aload 8
      //   3693: invokeinterface 604 1 0
      //   3698: ifeq +48 -> 3746
      //   3701: aload 12
      //   3703: astore 7
      //   3705: aload 13
      //   3707: astore_1
      //   3708: aload 8
      //   3710: invokeinterface 608 1 0
      //   3715: checkcast 873	com/appodeal/ads/z
      //   3718: astore 10
      //   3720: aload 10
      //   3722: ifnull -38 -> 3684
      //   3725: aload 12
      //   3727: astore 7
      //   3729: aload 13
      //   3731: astore_1
      //   3732: aload 6
      //   3734: aload 10
      //   3736: invokevirtual 874	com/appodeal/ads/z:a	()Ljava/lang/String;
      //   3739: invokevirtual 618	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   3742: pop
      //   3743: goto -59 -> 3684
      //   3746: aload 12
      //   3748: astore 7
      //   3750: aload 13
      //   3752: astore_1
      //   3753: aload 9
      //   3755: ldc_w 876
      //   3758: aload 6
      //   3760: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3763: pop
      //   3764: aload 12
      //   3766: astore 7
      //   3768: aload 13
      //   3770: astore_1
      //   3771: aload_0
      //   3772: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   3775: invokestatic 878	com/appodeal/ads/q:c	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   3778: ifnull +26 -> 3804
      //   3781: aload 12
      //   3783: astore 7
      //   3785: aload 13
      //   3787: astore_1
      //   3788: aload 9
      //   3790: ldc_w 880
      //   3793: aload_0
      //   3794: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   3797: invokestatic 878	com/appodeal/ads/q:c	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   3800: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3803: pop
      //   3804: aload 12
      //   3806: astore 7
      //   3808: aload 13
      //   3810: astore_1
      //   3811: getstatic 181	com/appodeal/ads/f/g:a	Ljava/lang/Long;
      //   3814: ifnull +22 -> 3836
      //   3817: aload 12
      //   3819: astore 7
      //   3821: aload 13
      //   3823: astore_1
      //   3824: aload 9
      //   3826: ldc_w 882
      //   3829: getstatic 181	com/appodeal/ads/f/g:a	Ljava/lang/Long;
      //   3832: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3835: pop
      //   3836: aload 12
      //   3838: astore 7
      //   3840: aload 13
      //   3842: astore_1
      //   3843: aload_0
      //   3844: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   3847: invokestatic 885	com/appodeal/ads/q:d	(Lcom/appodeal/ads/q;)J
      //   3850: lconst_0
      //   3851: lcmp
      //   3852: ifeq +26 -> 3878
      //   3855: aload 12
      //   3857: astore 7
      //   3859: aload 13
      //   3861: astore_1
      //   3862: aload 9
      //   3864: ldc_w 887
      //   3867: aload_0
      //   3868: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   3871: invokestatic 885	com/appodeal/ads/q:d	(Lcom/appodeal/ads/q;)J
      //   3874: invokevirtual 556	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   3877: pop
      //   3878: aload 12
      //   3880: astore 7
      //   3882: aload 13
      //   3884: astore_1
      //   3885: aload_0
      //   3886: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   3889: invokestatic 272	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   3892: ldc 84
      //   3894: invokevirtual 99	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3897: ifeq +26 -> 3923
      //   3900: aload 12
      //   3902: astore 7
      //   3904: aload 13
      //   3906: astore_1
      //   3907: aload 9
      //   3909: ldc_w 889
      //   3912: invokestatic 242	java/lang/System:currentTimeMillis	()J
      //   3915: ldc2_w 552
      //   3918: ldiv
      //   3919: invokevirtual 556	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   3922: pop
      //   3923: aload 12
      //   3925: astore 7
      //   3927: aload 13
      //   3929: astore_1
      //   3930: aload_0
      //   3931: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   3934: invokestatic 272	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   3937: ldc 103
      //   3939: invokevirtual 99	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3942: ifeq +26 -> 3968
      //   3945: aload 12
      //   3947: astore 7
      //   3949: aload 13
      //   3951: astore_1
      //   3952: aload 9
      //   3954: ldc_w 891
      //   3957: invokestatic 242	java/lang/System:currentTimeMillis	()J
      //   3960: ldc2_w 552
      //   3963: ldiv
      //   3964: invokevirtual 556	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   3967: pop
      //   3968: aload 12
      //   3970: astore 7
      //   3972: aload 13
      //   3974: astore_1
      //   3975: aload 9
      //   3977: ldc_w 678
      //   3980: aload_0
      //   3981: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   3984: invokestatic 893	com/appodeal/ads/q:e	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   3987: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3990: pop
      //   3991: aload 12
      //   3993: astore 7
      //   3995: aload 13
      //   3997: astore_1
      //   3998: aload 9
      //   4000: ldc_w 895
      //   4003: aload_0
      //   4004: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   4007: invokestatic 897	com/appodeal/ads/q:f	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   4010: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   4013: pop
      //   4014: aload 12
      //   4016: astore 7
      //   4018: aload 13
      //   4020: astore_1
      //   4021: aload_0
      //   4022: getfield 234	com/appodeal/ads/q$b:i	Z
      //   4025: ifne +20 -> 4045
      //   4028: aload 12
      //   4030: astore 7
      //   4032: aload 13
      //   4034: astore_1
      //   4035: aload_0
      //   4036: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   4039: invokestatic 91	com/appodeal/ads/q:g	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   4042: ifnull +23 -> 4065
      //   4045: aload 12
      //   4047: astore 7
      //   4049: aload 13
      //   4051: astore_1
      //   4052: aload 9
      //   4054: ldc_w 899
      //   4057: aload_0
      //   4058: invokespecial 900	com/appodeal/ads/q$b:a	()Lorg/json/JSONObject;
      //   4061: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   4064: pop
      //   4065: aload 12
      //   4067: astore 7
      //   4069: aload 13
      //   4071: astore_1
      //   4072: aload_0
      //   4073: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   4076: invokestatic 903	com/appodeal/ads/q:h	(Lcom/appodeal/ads/q;)Lcom/appodeal/ads/f/c;
      //   4079: ifnull -2003 -> 2076
      //   4082: aload 12
      //   4084: astore 7
      //   4086: aload 13
      //   4088: astore_1
      //   4089: aload 9
      //   4091: ldc_w 905
      //   4094: aload_0
      //   4095: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   4098: invokestatic 903	com/appodeal/ads/q:h	(Lcom/appodeal/ads/q;)Lcom/appodeal/ads/f/c;
      //   4101: invokevirtual 909	com/appodeal/ads/f/c:a	()I
      //   4104: invokevirtual 82	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
      //   4107: pop
      //   4108: goto -2032 -> 2076
      //   4111: aload 13
      //   4113: astore_1
      //   4114: aload 9
      //   4116: ldc_w 911
      //   4119: aload 6
      //   4121: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   4124: pop
      //   4125: goto -1909 -> 2216
      //   4128: astore 6
      //   4130: aload 12
      //   4132: astore 7
      //   4134: aload 13
      //   4136: astore_1
      //   4137: aload 6
      //   4139: invokestatic 132	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   4142: goto -1882 -> 2260
      //   4145: astore 6
      //   4147: aload 12
      //   4149: astore 7
      //   4151: aload 13
      //   4153: astore_1
      //   4154: aload 6
      //   4156: invokestatic 132	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   4159: goto -1874 -> 2285
      //   4162: aload 12
      //   4164: astore 7
      //   4166: aload 13
      //   4168: astore_1
      //   4169: aload_0
      //   4170: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   4173: invokestatic 272	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   4176: ldc 78
      //   4178: invokevirtual 99	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   4181: ifeq +128 -> 4309
      //   4184: aload 12
      //   4186: astore 7
      //   4188: aload 13
      //   4190: astore_1
      //   4191: aload_0
      //   4192: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   4195: invokestatic 674	com/appodeal/ads/q:i	(Lcom/appodeal/ads/q;)Lcom/appodeal/ads/d/h;
      //   4198: invokevirtual 913	com/appodeal/ads/d/h:f	()Z
      //   4201: ifne +53 -> 4254
      //   4204: aload 12
      //   4206: astore 7
      //   4208: aload 13
      //   4210: astore_1
      //   4211: ldc_w 915
      //   4214: invokestatic 323	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   4217: iconst_0
      //   4218: ifeq +17 -> 4235
      //   4221: aconst_null
      //   4222: instanceof 260
      //   4225: ifeq +12 -> 4237
      //   4228: aconst_null
      //   4229: checkcast 260	javax/net/ssl/HttpsURLConnection
      //   4232: invokevirtual 263	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   4235: aconst_null
      //   4236: areturn
      //   4237: aconst_null
      //   4238: instanceof 265
      //   4241: ifeq -6 -> 4235
      //   4244: aconst_null
      //   4245: checkcast 265	java/net/HttpURLConnection
      //   4248: invokevirtual 266	java/net/HttpURLConnection:disconnect	()V
      //   4251: goto -16 -> 4235
      //   4254: aload 12
      //   4256: astore 7
      //   4258: aload 13
      //   4260: astore_1
      //   4261: aload 9
      //   4263: ldc_w 917
      //   4266: aload_0
      //   4267: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   4270: invokestatic 674	com/appodeal/ads/q:i	(Lcom/appodeal/ads/q;)Lcom/appodeal/ads/d/h;
      //   4273: invokevirtual 919	com/appodeal/ads/d/h:e	()Lorg/json/JSONObject;
      //   4276: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   4279: pop
      //   4280: aload 12
      //   4282: astore 7
      //   4284: aload 13
      //   4286: astore_1
      //   4287: aload 9
      //   4289: ldc_w 921
      //   4292: aload_0
      //   4293: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   4296: invokestatic 674	com/appodeal/ads/q:i	(Lcom/appodeal/ads/q;)Lcom/appodeal/ads/d/h;
      //   4299: invokevirtual 923	com/appodeal/ads/d/h:g	()Ljava/lang/String;
      //   4302: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   4305: pop
      //   4306: goto -1873 -> 2433
      //   4309: aload 12
      //   4311: astore 7
      //   4313: aload 13
      //   4315: astore_1
      //   4316: aload_0
      //   4317: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   4320: invokestatic 272	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   4323: ldc 84
      //   4325: invokevirtual 99	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   4328: ifne +25 -> 4353
      //   4331: aload 12
      //   4333: astore 7
      //   4335: aload 13
      //   4337: astore_1
      //   4338: aload_0
      //   4339: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   4342: invokestatic 272	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   4345: ldc 103
      //   4347: invokevirtual 99	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   4350: ifeq -1917 -> 2433
      //   4353: aload 12
      //   4355: astore 7
      //   4357: aload 13
      //   4359: astore_1
      //   4360: aload 9
      //   4362: ldc_w 678
      //   4365: aload_0
      //   4366: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   4369: invokestatic 674	com/appodeal/ads/q:i	(Lcom/appodeal/ads/q;)Lcom/appodeal/ads/d/h;
      //   4372: invokevirtual 923	com/appodeal/ads/d/h:g	()Ljava/lang/String;
      //   4375: invokevirtual 333	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   4378: pop
      //   4379: goto -1946 -> 2433
      //   4382: aload 12
      //   4384: astore 7
      //   4386: aload 13
      //   4388: astore_1
      //   4389: new 684	java/net/URL
      //   4392: dup
      //   4393: ldc_w 686
      //   4396: iconst_4
      //   4397: anewarray 111	java/lang/Object
      //   4400: dup
      //   4401: iconst_0
      //   4402: invokestatic 690	com/appodeal/ads/utils/e:c	()I
      //   4405: invokestatic 693	com/appodeal/ads/utils/e:a	(I)Ljava/lang/String;
      //   4408: aastore
      //   4409: dup
      //   4410: iconst_1
      //   4411: invokestatic 695	com/appodeal/ads/utils/e:b	()Ljava/lang/String;
      //   4414: aastore
      //   4415: dup
      //   4416: iconst_2
      //   4417: invokestatic 690	com/appodeal/ads/utils/e:c	()I
      //   4420: invokestatic 700	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   4423: aastore
      //   4424: dup
      //   4425: iconst_3
      //   4426: aload_0
      //   4427: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   4430: invokestatic 272	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   4433: aastore
      //   4434: invokestatic 115	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   4437: invokespecial 703	java/net/URL:<init>	(Ljava/lang/String;)V
      //   4440: astore 8
      //   4442: goto -1833 -> 2609
      //   4445: astore 7
      //   4447: aload_1
      //   4448: invokevirtual 791	java/util/zip/GZIPOutputStream:close	()V
      //   4451: aload 7
      //   4453: athrow
      //   4454: astore 8
      //   4456: aload 6
      //   4458: astore 7
      //   4460: aload 6
      //   4462: astore_1
      //   4463: aload 8
      //   4465: invokestatic 132	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   4468: aload 6
      //   4470: astore 7
      //   4472: aload 6
      //   4474: astore_1
      //   4475: aload 8
      //   4477: invokevirtual 926	java/io/IOException:getMessage	()Ljava/lang/String;
      //   4480: ldc_w 928
      //   4483: invokevirtual 99	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   4486: ifne +28 -> 4514
      //   4489: aload 6
      //   4491: astore 7
      //   4493: aload 6
      //   4495: astore_1
      //   4496: aload 8
      //   4498: invokevirtual 926	java/io/IOException:getMessage	()Ljava/lang/String;
      //   4501: ldc_w 930
      //   4504: invokevirtual 99	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   4507: istore 5
      //   4509: iload 5
      //   4511: ifeq +45 -> 4556
      //   4514: aload 6
      //   4516: ifnull +19 -> 4535
      //   4519: aload 6
      //   4521: instanceof 260
      //   4524: ifeq +13 -> 4537
      //   4527: aload 6
      //   4529: checkcast 260	javax/net/ssl/HttpsURLConnection
      //   4532: invokevirtual 263	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   4535: aconst_null
      //   4536: areturn
      //   4537: aload 6
      //   4539: instanceof 265
      //   4542: ifeq -7 -> 4535
      //   4545: aload 6
      //   4547: checkcast 265	java/net/HttpURLConnection
      //   4550: invokevirtual 266	java/net/HttpURLConnection:disconnect	()V
      //   4553: goto -18 -> 4535
      //   4556: aconst_null
      //   4557: astore 8
      //   4559: goto -1701 -> 2858
      //   4562: aload 6
      //   4564: ifnull +19 -> 4583
      //   4567: aload 6
      //   4569: instanceof 260
      //   4572: ifeq +13 -> 4585
      //   4575: aload 6
      //   4577: checkcast 260	javax/net/ssl/HttpsURLConnection
      //   4580: invokevirtual 263	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   4583: aconst_null
      //   4584: areturn
      //   4585: aload 6
      //   4587: instanceof 265
      //   4590: ifeq -7 -> 4583
      //   4593: aload 6
      //   4595: checkcast 265	java/net/HttpURLConnection
      //   4598: invokevirtual 266	java/net/HttpURLConnection:disconnect	()V
      //   4601: goto -18 -> 4583
      //   4604: iload 4
      //   4606: ifeq +246 -> 4852
      //   4609: aload 6
      //   4611: astore 7
      //   4613: aload 6
      //   4615: astore_1
      //   4616: aload 9
      //   4618: invokeinterface 309 1 0
      //   4623: astore 9
      //   4625: aload 6
      //   4627: astore 7
      //   4629: aload 6
      //   4631: astore_1
      //   4632: aload 9
      //   4634: aload_0
      //   4635: getfield 22	com/appodeal/ads/q$b:a	Lcom/appodeal/ads/q;
      //   4638: invokestatic 272	com/appodeal/ads/q:a	(Lcom/appodeal/ads/q;)Ljava/lang/String;
      //   4641: aload 8
      //   4643: invokeinterface 315 3 0
      //   4648: pop
      //   4649: aload 6
      //   4651: astore 7
      //   4653: aload 6
      //   4655: astore_1
      //   4656: aload 9
      //   4658: invokeinterface 318 1 0
      //   4663: goto +189 -> 4852
      //   4666: astore 8
      //   4668: aload 6
      //   4670: astore 7
      //   4672: aload 6
      //   4674: astore_1
      //   4675: aload 8
      //   4677: invokestatic 132	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   4680: aload 6
      //   4682: ifnull +19 -> 4701
      //   4685: aload 6
      //   4687: instanceof 260
      //   4690: ifeq +13 -> 4703
      //   4693: aload 6
      //   4695: checkcast 260	javax/net/ssl/HttpsURLConnection
      //   4698: invokevirtual 263	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   4701: aconst_null
      //   4702: areturn
      //   4703: aload 6
      //   4705: instanceof 265
      //   4708: ifeq -7 -> 4701
      //   4711: aload 6
      //   4713: checkcast 265	java/net/HttpURLConnection
      //   4716: invokevirtual 266	java/net/HttpURLConnection:disconnect	()V
      //   4719: goto -18 -> 4701
      //   4722: astore 9
      //   4724: aload 6
      //   4726: astore 7
      //   4728: aload 6
      //   4730: astore_1
      //   4731: aload 9
      //   4733: invokestatic 132	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   4736: goto -1715 -> 3021
      //   4739: aload 6
      //   4741: instanceof 265
      //   4744: ifeq -1702 -> 3042
      //   4747: aload 6
      //   4749: checkcast 265	java/net/HttpURLConnection
      //   4752: invokevirtual 266	java/net/HttpURLConnection:disconnect	()V
      //   4755: goto -1713 -> 3042
      //   4758: aload 7
      //   4760: instanceof 265
      //   4763: ifeq -1638 -> 3125
      //   4766: aload 7
      //   4768: checkcast 265	java/net/HttpURLConnection
      //   4771: invokevirtual 266	java/net/HttpURLConnection:disconnect	()V
      //   4774: goto -1649 -> 3125
      //   4777: aload_1
      //   4778: instanceof 265
      //   4781: ifeq -1617 -> 3164
      //   4784: aload_1
      //   4785: checkcast 265	java/net/HttpURLConnection
      //   4788: invokevirtual 266	java/net/HttpURLConnection:disconnect	()V
      //   4791: goto -1627 -> 3164
      //   4794: astore 7
      //   4796: aload 6
      //   4798: astore_1
      //   4799: aload 7
      //   4801: astore 6
      //   4803: goto -1657 -> 3146
      //   4806: astore_1
      //   4807: aload 6
      //   4809: astore 7
      //   4811: aload_1
      //   4812: astore 6
      //   4814: goto -1718 -> 3096
      //   4817: astore 8
      //   4819: aload 11
      //   4821: astore 6
      //   4823: goto -367 -> 4456
      //   4826: aload_1
      //   4827: astore 8
      //   4829: goto -4248 -> 581
      //   4832: iconst_0
      //   4833: istore 4
      //   4835: goto -4676 -> 159
      //   4838: ldc_w 932
      //   4841: astore 6
      //   4843: goto -3470 -> 1373
      //   4846: iconst_0
      //   4847: istore 4
      //   4849: goto -2301 -> 2548
      //   4852: goto -1898 -> 2954
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	4855	0	this	b
      //   0	4855	1	paramVarArgs	Void[]
      //   696	33	2	f1	float
      //   714	23	3	f2	float
      //   157	4691	4	j	int
      //   312	4198	5	bool	boolean
      //   41	2008	6	localObject1	Object
      //   2062	10	6	localJSONException1	JSONException
      //   2091	101	6	localJSONArray	JSONArray
      //   2202	10	6	localException1	Exception
      //   2299	750	6	localObject2	Object
      //   3053	10	6	localException2	Exception
      //   3072	1	6	localObject3	Object
      //   3094	6	6	localException3	Exception
      //   3144	21	6	localObject4	Object
      //   3189	931	6	localException4	Exception
      //   4128	10	6	localException5	Exception
      //   4145	652	6	localException6	Exception
      //   4801	41	6	localObject5	Object
      //   11	4374	7	localObject6	Object
      //   4445	7	7	localObject7	Object
      //   4458	309	7	localObject8	Object
      //   4794	6	7	localObject9	Object
      //   4809	1	7	localObject10	Object
      //   281	4160	8	localObject11	Object
      //   4454	43	8	localIOException1	java.io.IOException
      //   4557	85	8	str	String
      //   4666	10	8	localJSONException2	JSONException
      //   4817	1	8	localIOException2	java.io.IOException
      //   4827	1	8	arrayOfVoid	Void[]
      //   285	4372	9	localObject12	Object
      //   4722	10	9	localException7	Exception
      //   257	3478	10	localObject13	Object
      //   4	4816	11	localObject14	Object
      //   7	4376	12	localObject15	Object
      //   1	4386	13	localObject16	Object
      //   29	2264	14	localSharedPreferences	android.content.SharedPreferences
      //   176	1921	15	localPackageManager	android.content.pm.PackageManager
      //   195	912	16	localObject17	Object
      //   3077	10	16	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
      //   3127	10	16	localException8	Exception
      //   396	32	17	localInfo	com.google.android.gms.ads.identifier.AdvertisingIdClient.Info
      // Exception table:
      //   from	to	target	type
      //   604	615	2062	org/json/JSONException
      //   622	629	2062	org/json/JSONException
      //   636	647	2062	org/json/JSONException
      //   654	661	2062	org/json/JSONException
      //   668	679	2062	org/json/JSONException
      //   686	697	2062	org/json/JSONException
      //   704	715	2062	org/json/JSONException
      //   722	728	2062	org/json/JSONException
      //   751	761	2062	org/json/JSONException
      //   768	775	2062	org/json/JSONException
      //   782	793	2062	org/json/JSONException
      //   800	807	2062	org/json/JSONException
      //   814	821	2062	org/json/JSONException
      //   828	839	2062	org/json/JSONException
      //   846	853	2062	org/json/JSONException
      //   860	869	2062	org/json/JSONException
      //   876	883	2062	org/json/JSONException
      //   890	901	2062	org/json/JSONException
      //   908	915	2062	org/json/JSONException
      //   922	932	2062	org/json/JSONException
      //   939	945	2062	org/json/JSONException
      //   952	962	2062	org/json/JSONException
      //   969	981	2062	org/json/JSONException
      //   988	1000	2062	org/json/JSONException
      //   1007	1019	2062	org/json/JSONException
      //   1026	1038	2062	org/json/JSONException
      //   1045	1056	2062	org/json/JSONException
      //   1063	1073	2062	org/json/JSONException
      //   1080	1094	2062	org/json/JSONException
      //   1101	1115	2062	org/json/JSONException
      //   1118	1146	2062	org/json/JSONException
      //   1153	1164	2062	org/json/JSONException
      //   1171	1182	2062	org/json/JSONException
      //   1189	1201	2062	org/json/JSONException
      //   1208	1222	2062	org/json/JSONException
      //   1229	1249	2062	org/json/JSONException
      //   1256	1268	2062	org/json/JSONException
      //   1275	1289	2062	org/json/JSONException
      //   1296	1310	2062	org/json/JSONException
      //   1317	1330	2062	org/json/JSONException
      //   1337	1349	2062	org/json/JSONException
      //   1356	1368	2062	org/json/JSONException
      //   1380	1391	2062	org/json/JSONException
      //   1394	1403	2062	org/json/JSONException
      //   1420	1431	2062	org/json/JSONException
      //   1438	1450	2062	org/json/JSONException
      //   1457	1488	2062	org/json/JSONException
      //   1495	1507	2062	org/json/JSONException
      //   1514	1528	2062	org/json/JSONException
      //   1535	1555	2062	org/json/JSONException
      //   1562	1582	2062	org/json/JSONException
      //   1589	1603	2062	org/json/JSONException
      //   1610	1624	2062	org/json/JSONException
      //   1631	1650	2062	org/json/JSONException
      //   1657	1672	2062	org/json/JSONException
      //   1679	1693	2062	org/json/JSONException
      //   1700	1730	2062	org/json/JSONException
      //   1737	1753	2062	org/json/JSONException
      //   1760	1775	2062	org/json/JSONException
      //   1782	1794	2062	org/json/JSONException
      //   1801	1811	2062	org/json/JSONException
      //   1818	1832	2062	org/json/JSONException
      //   1839	1851	2062	org/json/JSONException
      //   1858	1872	2062	org/json/JSONException
      //   1879	1892	2062	org/json/JSONException
      //   1904	1922	2062	org/json/JSONException
      //   1929	1936	2062	org/json/JSONException
      //   1943	1952	2062	org/json/JSONException
      //   1959	1966	2062	org/json/JSONException
      //   1973	1990	2062	org/json/JSONException
      //   1997	2007	2062	org/json/JSONException
      //   2014	2026	2062	org/json/JSONException
      //   2033	2041	2062	org/json/JSONException
      //   2048	2059	2062	org/json/JSONException
      //   3086	3091	2062	org/json/JSONException
      //   3136	3141	2062	org/json/JSONException
      //   3174	3186	2062	org/json/JSONException
      //   3198	3203	2062	org/json/JSONException
      //   3213	3220	2062	org/json/JSONException
      //   3227	3244	2062	org/json/JSONException
      //   3251	3261	2062	org/json/JSONException
      //   3268	3280	2062	org/json/JSONException
      //   3287	3295	2062	org/json/JSONException
      //   3302	3313	2062	org/json/JSONException
      //   3323	3330	2062	org/json/JSONException
      //   3337	3354	2062	org/json/JSONException
      //   3361	3371	2062	org/json/JSONException
      //   3378	3390	2062	org/json/JSONException
      //   3397	3405	2062	org/json/JSONException
      //   3412	3423	2062	org/json/JSONException
      //   3433	3440	2062	org/json/JSONException
      //   3447	3464	2062	org/json/JSONException
      //   3471	3481	2062	org/json/JSONException
      //   3488	3500	2062	org/json/JSONException
      //   3507	3515	2062	org/json/JSONException
      //   3522	3533	2062	org/json/JSONException
      //   3543	3550	2062	org/json/JSONException
      //   3557	3574	2062	org/json/JSONException
      //   3581	3591	2062	org/json/JSONException
      //   3598	3610	2062	org/json/JSONException
      //   3617	3625	2062	org/json/JSONException
      //   3632	3643	2062	org/json/JSONException
      //   3653	3660	2062	org/json/JSONException
      //   3667	3684	2062	org/json/JSONException
      //   3691	3701	2062	org/json/JSONException
      //   3708	3720	2062	org/json/JSONException
      //   3732	3743	2062	org/json/JSONException
      //   3753	3764	2062	org/json/JSONException
      //   3771	3781	2062	org/json/JSONException
      //   3788	3804	2062	org/json/JSONException
      //   3811	3817	2062	org/json/JSONException
      //   3824	3836	2062	org/json/JSONException
      //   3843	3855	2062	org/json/JSONException
      //   3862	3878	2062	org/json/JSONException
      //   3885	3900	2062	org/json/JSONException
      //   3907	3923	2062	org/json/JSONException
      //   3930	3945	2062	org/json/JSONException
      //   3952	3968	2062	org/json/JSONException
      //   3975	3991	2062	org/json/JSONException
      //   3998	4014	2062	org/json/JSONException
      //   4021	4028	2062	org/json/JSONException
      //   4035	4045	2062	org/json/JSONException
      //   4052	4065	2062	org/json/JSONException
      //   4072	4082	2062	org/json/JSONException
      //   4089	4108	2062	org/json/JSONException
      //   2084	2093	2202	java/lang/Exception
      //   2096	2104	2202	java/lang/Exception
      //   2107	2115	2202	java/lang/Exception
      //   2118	2127	2202	java/lang/Exception
      //   2130	2140	2202	java/lang/Exception
      //   2143	2158	2202	java/lang/Exception
      //   2161	2174	2202	java/lang/Exception
      //   2177	2188	2202	java/lang/Exception
      //   2191	2199	2202	java/lang/Exception
      //   4114	4125	2202	java/lang/Exception
      //   338	345	3053	java/lang/Exception
      //   356	375	3053	java/lang/Exception
      //   386	398	3053	java/lang/Exception
      //   409	416	3053	java/lang/Exception
      //   427	435	3053	java/lang/Exception
      //   451	460	3053	java/lang/Exception
      //   471	484	3053	java/lang/Exception
      //   495	508	3053	java/lang/Exception
      //   519	526	3053	java/lang/Exception
      //   537	555	3053	java/lang/Exception
      //   1063	1073	3077	android/content/pm/PackageManager$NameNotFoundException
      //   1080	1094	3077	android/content/pm/PackageManager$NameNotFoundException
      //   1101	1115	3077	android/content/pm/PackageManager$NameNotFoundException
      //   16	31	3094	java/lang/Exception
      //   38	43	3094	java/lang/Exception
      //   50	65	3094	java/lang/Exception
      //   72	79	3094	java/lang/Exception
      //   86	92	3094	java/lang/Exception
      //   99	106	3094	java/lang/Exception
      //   113	125	3094	java/lang/Exception
      //   132	145	3094	java/lang/Exception
      //   152	156	3094	java/lang/Exception
      //   166	178	3094	java/lang/Exception
      //   185	197	3094	java/lang/Exception
      //   246	259	3094	java/lang/Exception
      //   266	279	3094	java/lang/Exception
      //   299	314	3094	java/lang/Exception
      //   569	581	3094	java/lang/Exception
      //   588	597	3094	java/lang/Exception
      //   604	615	3094	java/lang/Exception
      //   622	629	3094	java/lang/Exception
      //   636	647	3094	java/lang/Exception
      //   654	661	3094	java/lang/Exception
      //   668	679	3094	java/lang/Exception
      //   686	697	3094	java/lang/Exception
      //   704	715	3094	java/lang/Exception
      //   722	728	3094	java/lang/Exception
      //   751	761	3094	java/lang/Exception
      //   768	775	3094	java/lang/Exception
      //   782	793	3094	java/lang/Exception
      //   800	807	3094	java/lang/Exception
      //   814	821	3094	java/lang/Exception
      //   828	839	3094	java/lang/Exception
      //   846	853	3094	java/lang/Exception
      //   860	869	3094	java/lang/Exception
      //   876	883	3094	java/lang/Exception
      //   890	901	3094	java/lang/Exception
      //   908	915	3094	java/lang/Exception
      //   922	932	3094	java/lang/Exception
      //   939	945	3094	java/lang/Exception
      //   952	962	3094	java/lang/Exception
      //   969	981	3094	java/lang/Exception
      //   988	1000	3094	java/lang/Exception
      //   1007	1019	3094	java/lang/Exception
      //   1026	1038	3094	java/lang/Exception
      //   1045	1056	3094	java/lang/Exception
      //   1063	1073	3094	java/lang/Exception
      //   1080	1094	3094	java/lang/Exception
      //   1101	1115	3094	java/lang/Exception
      //   1153	1164	3094	java/lang/Exception
      //   1171	1182	3094	java/lang/Exception
      //   1189	1201	3094	java/lang/Exception
      //   1208	1222	3094	java/lang/Exception
      //   1229	1249	3094	java/lang/Exception
      //   1256	1268	3094	java/lang/Exception
      //   1275	1289	3094	java/lang/Exception
      //   1296	1310	3094	java/lang/Exception
      //   1317	1330	3094	java/lang/Exception
      //   1337	1349	3094	java/lang/Exception
      //   1356	1368	3094	java/lang/Exception
      //   1380	1391	3094	java/lang/Exception
      //   1438	1450	3094	java/lang/Exception
      //   1457	1488	3094	java/lang/Exception
      //   1495	1507	3094	java/lang/Exception
      //   1514	1528	3094	java/lang/Exception
      //   1535	1555	3094	java/lang/Exception
      //   1562	1582	3094	java/lang/Exception
      //   1589	1603	3094	java/lang/Exception
      //   1610	1624	3094	java/lang/Exception
      //   1631	1650	3094	java/lang/Exception
      //   1657	1672	3094	java/lang/Exception
      //   1679	1693	3094	java/lang/Exception
      //   1700	1730	3094	java/lang/Exception
      //   1737	1753	3094	java/lang/Exception
      //   1760	1775	3094	java/lang/Exception
      //   1782	1794	3094	java/lang/Exception
      //   1801	1811	3094	java/lang/Exception
      //   1818	1832	3094	java/lang/Exception
      //   1839	1851	3094	java/lang/Exception
      //   1858	1872	3094	java/lang/Exception
      //   1879	1892	3094	java/lang/Exception
      //   1904	1922	3094	java/lang/Exception
      //   1929	1936	3094	java/lang/Exception
      //   1943	1952	3094	java/lang/Exception
      //   1959	1966	3094	java/lang/Exception
      //   1973	1990	3094	java/lang/Exception
      //   1997	2007	3094	java/lang/Exception
      //   2014	2026	3094	java/lang/Exception
      //   2033	2041	3094	java/lang/Exception
      //   2048	2059	3094	java/lang/Exception
      //   2071	2076	3094	java/lang/Exception
      //   2211	2216	3094	java/lang/Exception
      //   2292	2301	3094	java/lang/Exception
      //   2308	2321	3094	java/lang/Exception
      //   2328	2339	3094	java/lang/Exception
      //   2346	2353	3094	java/lang/Exception
      //   2360	2364	3094	java/lang/Exception
      //   2371	2381	3094	java/lang/Exception
      //   2388	2404	3094	java/lang/Exception
      //   2411	2433	3094	java/lang/Exception
      //   2440	2456	3094	java/lang/Exception
      //   2463	2478	3094	java/lang/Exception
      //   2485	2500	3094	java/lang/Exception
      //   2507	2522	3094	java/lang/Exception
      //   2529	2545	3094	java/lang/Exception
      //   2560	2609	3094	java/lang/Exception
      //   2616	2623	3094	java/lang/Exception
      //   2865	2881	3094	java/lang/Exception
      //   2898	2915	3094	java/lang/Exception
      //   2922	2928	3094	java/lang/Exception
      //   2935	2954	3094	java/lang/Exception
      //   2961	2972	3094	java/lang/Exception
      //   2979	2984	3094	java/lang/Exception
      //   3062	3067	3094	java/lang/Exception
      //   3086	3091	3094	java/lang/Exception
      //   3136	3141	3094	java/lang/Exception
      //   3174	3186	3094	java/lang/Exception
      //   3198	3203	3094	java/lang/Exception
      //   3213	3220	3094	java/lang/Exception
      //   3227	3244	3094	java/lang/Exception
      //   3251	3261	3094	java/lang/Exception
      //   3268	3280	3094	java/lang/Exception
      //   3287	3295	3094	java/lang/Exception
      //   3302	3313	3094	java/lang/Exception
      //   3323	3330	3094	java/lang/Exception
      //   3337	3354	3094	java/lang/Exception
      //   3361	3371	3094	java/lang/Exception
      //   3378	3390	3094	java/lang/Exception
      //   3397	3405	3094	java/lang/Exception
      //   3412	3423	3094	java/lang/Exception
      //   3433	3440	3094	java/lang/Exception
      //   3447	3464	3094	java/lang/Exception
      //   3471	3481	3094	java/lang/Exception
      //   3488	3500	3094	java/lang/Exception
      //   3507	3515	3094	java/lang/Exception
      //   3522	3533	3094	java/lang/Exception
      //   3543	3550	3094	java/lang/Exception
      //   3557	3574	3094	java/lang/Exception
      //   3581	3591	3094	java/lang/Exception
      //   3598	3610	3094	java/lang/Exception
      //   3617	3625	3094	java/lang/Exception
      //   3632	3643	3094	java/lang/Exception
      //   3653	3660	3094	java/lang/Exception
      //   3667	3684	3094	java/lang/Exception
      //   3691	3701	3094	java/lang/Exception
      //   3708	3720	3094	java/lang/Exception
      //   3732	3743	3094	java/lang/Exception
      //   3753	3764	3094	java/lang/Exception
      //   3771	3781	3094	java/lang/Exception
      //   3788	3804	3094	java/lang/Exception
      //   3811	3817	3094	java/lang/Exception
      //   3824	3836	3094	java/lang/Exception
      //   3843	3855	3094	java/lang/Exception
      //   3862	3878	3094	java/lang/Exception
      //   3885	3900	3094	java/lang/Exception
      //   3907	3923	3094	java/lang/Exception
      //   3930	3945	3094	java/lang/Exception
      //   3952	3968	3094	java/lang/Exception
      //   3975	3991	3094	java/lang/Exception
      //   3998	4014	3094	java/lang/Exception
      //   4021	4028	3094	java/lang/Exception
      //   4035	4045	3094	java/lang/Exception
      //   4052	4065	3094	java/lang/Exception
      //   4072	4082	3094	java/lang/Exception
      //   4089	4108	3094	java/lang/Exception
      //   4137	4142	3094	java/lang/Exception
      //   4154	4159	3094	java/lang/Exception
      //   4169	4184	3094	java/lang/Exception
      //   4191	4204	3094	java/lang/Exception
      //   4211	4217	3094	java/lang/Exception
      //   4261	4280	3094	java/lang/Exception
      //   4287	4306	3094	java/lang/Exception
      //   4316	4331	3094	java/lang/Exception
      //   4338	4353	3094	java/lang/Exception
      //   4360	4379	3094	java/lang/Exception
      //   4389	4442	3094	java/lang/Exception
      //   4463	4468	3094	java/lang/Exception
      //   4475	4489	3094	java/lang/Exception
      //   4496	4509	3094	java/lang/Exception
      //   4616	4625	3094	java/lang/Exception
      //   4632	4649	3094	java/lang/Exception
      //   4656	4663	3094	java/lang/Exception
      //   4675	4680	3094	java/lang/Exception
      //   4731	4736	3094	java/lang/Exception
      //   1118	1146	3127	java/lang/Exception
      //   16	31	3144	finally
      //   38	43	3144	finally
      //   50	65	3144	finally
      //   72	79	3144	finally
      //   86	92	3144	finally
      //   99	106	3144	finally
      //   113	125	3144	finally
      //   132	145	3144	finally
      //   152	156	3144	finally
      //   166	178	3144	finally
      //   185	197	3144	finally
      //   246	259	3144	finally
      //   266	279	3144	finally
      //   299	314	3144	finally
      //   338	345	3144	finally
      //   356	375	3144	finally
      //   386	398	3144	finally
      //   409	416	3144	finally
      //   427	435	3144	finally
      //   451	460	3144	finally
      //   471	484	3144	finally
      //   495	508	3144	finally
      //   519	526	3144	finally
      //   537	555	3144	finally
      //   569	581	3144	finally
      //   588	597	3144	finally
      //   604	615	3144	finally
      //   622	629	3144	finally
      //   636	647	3144	finally
      //   654	661	3144	finally
      //   668	679	3144	finally
      //   686	697	3144	finally
      //   704	715	3144	finally
      //   722	728	3144	finally
      //   751	761	3144	finally
      //   768	775	3144	finally
      //   782	793	3144	finally
      //   800	807	3144	finally
      //   814	821	3144	finally
      //   828	839	3144	finally
      //   846	853	3144	finally
      //   860	869	3144	finally
      //   876	883	3144	finally
      //   890	901	3144	finally
      //   908	915	3144	finally
      //   922	932	3144	finally
      //   939	945	3144	finally
      //   952	962	3144	finally
      //   969	981	3144	finally
      //   988	1000	3144	finally
      //   1007	1019	3144	finally
      //   1026	1038	3144	finally
      //   1045	1056	3144	finally
      //   1063	1073	3144	finally
      //   1080	1094	3144	finally
      //   1101	1115	3144	finally
      //   1118	1146	3144	finally
      //   1153	1164	3144	finally
      //   1171	1182	3144	finally
      //   1189	1201	3144	finally
      //   1208	1222	3144	finally
      //   1229	1249	3144	finally
      //   1256	1268	3144	finally
      //   1275	1289	3144	finally
      //   1296	1310	3144	finally
      //   1317	1330	3144	finally
      //   1337	1349	3144	finally
      //   1356	1368	3144	finally
      //   1380	1391	3144	finally
      //   1394	1403	3144	finally
      //   1420	1431	3144	finally
      //   1438	1450	3144	finally
      //   1457	1488	3144	finally
      //   1495	1507	3144	finally
      //   1514	1528	3144	finally
      //   1535	1555	3144	finally
      //   1562	1582	3144	finally
      //   1589	1603	3144	finally
      //   1610	1624	3144	finally
      //   1631	1650	3144	finally
      //   1657	1672	3144	finally
      //   1679	1693	3144	finally
      //   1700	1730	3144	finally
      //   1737	1753	3144	finally
      //   1760	1775	3144	finally
      //   1782	1794	3144	finally
      //   1801	1811	3144	finally
      //   1818	1832	3144	finally
      //   1839	1851	3144	finally
      //   1858	1872	3144	finally
      //   1879	1892	3144	finally
      //   1904	1922	3144	finally
      //   1929	1936	3144	finally
      //   1943	1952	3144	finally
      //   1959	1966	3144	finally
      //   1973	1990	3144	finally
      //   1997	2007	3144	finally
      //   2014	2026	3144	finally
      //   2033	2041	3144	finally
      //   2048	2059	3144	finally
      //   2071	2076	3144	finally
      //   2084	2093	3144	finally
      //   2096	2104	3144	finally
      //   2107	2115	3144	finally
      //   2118	2127	3144	finally
      //   2130	2140	3144	finally
      //   2143	2158	3144	finally
      //   2161	2174	3144	finally
      //   2177	2188	3144	finally
      //   2191	2199	3144	finally
      //   2211	2216	3144	finally
      //   2219	2238	3144	finally
      //   2241	2260	3144	finally
      //   2263	2285	3144	finally
      //   2292	2301	3144	finally
      //   2308	2321	3144	finally
      //   2328	2339	3144	finally
      //   2346	2353	3144	finally
      //   2360	2364	3144	finally
      //   2371	2381	3144	finally
      //   2388	2404	3144	finally
      //   2411	2433	3144	finally
      //   2440	2456	3144	finally
      //   2463	2478	3144	finally
      //   2485	2500	3144	finally
      //   2507	2522	3144	finally
      //   2529	2545	3144	finally
      //   2560	2609	3144	finally
      //   2616	2623	3144	finally
      //   2865	2881	3144	finally
      //   2898	2915	3144	finally
      //   2922	2928	3144	finally
      //   2935	2954	3144	finally
      //   2961	2972	3144	finally
      //   2979	2984	3144	finally
      //   2992	3003	3144	finally
      //   3006	3012	3144	finally
      //   3015	3021	3144	finally
      //   3062	3067	3144	finally
      //   3086	3091	3144	finally
      //   3099	3104	3144	finally
      //   3136	3141	3144	finally
      //   3174	3186	3144	finally
      //   3198	3203	3144	finally
      //   3213	3220	3144	finally
      //   3227	3244	3144	finally
      //   3251	3261	3144	finally
      //   3268	3280	3144	finally
      //   3287	3295	3144	finally
      //   3302	3313	3144	finally
      //   3323	3330	3144	finally
      //   3337	3354	3144	finally
      //   3361	3371	3144	finally
      //   3378	3390	3144	finally
      //   3397	3405	3144	finally
      //   3412	3423	3144	finally
      //   3433	3440	3144	finally
      //   3447	3464	3144	finally
      //   3471	3481	3144	finally
      //   3488	3500	3144	finally
      //   3507	3515	3144	finally
      //   3522	3533	3144	finally
      //   3543	3550	3144	finally
      //   3557	3574	3144	finally
      //   3581	3591	3144	finally
      //   3598	3610	3144	finally
      //   3617	3625	3144	finally
      //   3632	3643	3144	finally
      //   3653	3660	3144	finally
      //   3667	3684	3144	finally
      //   3691	3701	3144	finally
      //   3708	3720	3144	finally
      //   3732	3743	3144	finally
      //   3753	3764	3144	finally
      //   3771	3781	3144	finally
      //   3788	3804	3144	finally
      //   3811	3817	3144	finally
      //   3824	3836	3144	finally
      //   3843	3855	3144	finally
      //   3862	3878	3144	finally
      //   3885	3900	3144	finally
      //   3907	3923	3144	finally
      //   3930	3945	3144	finally
      //   3952	3968	3144	finally
      //   3975	3991	3144	finally
      //   3998	4014	3144	finally
      //   4021	4028	3144	finally
      //   4035	4045	3144	finally
      //   4052	4065	3144	finally
      //   4072	4082	3144	finally
      //   4089	4108	3144	finally
      //   4114	4125	3144	finally
      //   4137	4142	3144	finally
      //   4154	4159	3144	finally
      //   4169	4184	3144	finally
      //   4191	4204	3144	finally
      //   4211	4217	3144	finally
      //   4261	4280	3144	finally
      //   4287	4306	3144	finally
      //   4316	4331	3144	finally
      //   4338	4353	3144	finally
      //   4360	4379	3144	finally
      //   4389	4442	3144	finally
      //   4463	4468	3144	finally
      //   4475	4489	3144	finally
      //   4496	4509	3144	finally
      //   4616	4625	3144	finally
      //   4632	4649	3144	finally
      //   4656	4663	3144	finally
      //   4675	4680	3144	finally
      //   4731	4736	3144	finally
      //   1394	1403	3189	java/lang/Exception
      //   1420	1431	3189	java/lang/Exception
      //   2219	2238	4128	java/lang/Exception
      //   2241	2260	4128	java/lang/Exception
      //   2263	2285	4145	java/lang/Exception
      //   2765	2780	4445	finally
      //   2623	2713	4454	java/io/IOException
      //   2713	2765	4454	java/io/IOException
      //   2780	2812	4454	java/io/IOException
      //   2816	2848	4454	java/io/IOException
      //   4447	4454	4454	java/io/IOException
      //   2961	2972	4666	org/json/JSONException
      //   2979	2984	4666	org/json/JSONException
      //   2992	3003	4722	java/lang/Exception
      //   3006	3012	4722	java/lang/Exception
      //   3015	3021	4722	java/lang/Exception
      //   2623	2713	4794	finally
      //   2713	2765	4794	finally
      //   2780	2812	4794	finally
      //   2816	2848	4794	finally
      //   4447	4454	4794	finally
      //   2623	2713	4806	java/lang/Exception
      //   2713	2765	4806	java/lang/Exception
      //   2780	2812	4806	java/lang/Exception
      //   2816	2848	4806	java/lang/Exception
      //   4447	4454	4806	java/lang/Exception
      //   2616	2623	4817	java/io/IOException
    }
    
    protected void a(JSONObject paramJSONObject)
    {
      try
      {
        if (q.j(q.this) != null)
        {
          if (paramJSONObject == null)
          {
            q.j(q.this).a(q.k(q.this));
            return;
          }
          q.j(q.this).a(paramJSONObject, q.k(q.this), q.a(q.this));
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
      if ((q.a(q.this).equals("banner")) || (q.a(q.this).equals("debug")))
      {
        bool1 = true;
        this.b = bool1;
        if ((!q.a(q.this).equals("banner_320")) && (!q.a(q.this).equals("debug_banner_320"))) {
          break label390;
        }
        bool1 = true;
        label73:
        this.c = bool1;
        if ((!q.a(q.this).equals("banner_mrec")) && (!q.a(q.this).equals("debug_banner_mrec"))) {
          break label395;
        }
        bool1 = true;
        label111:
        this.d = bool1;
        if ((!q.a(q.this).equals("video")) && (!q.a(q.this).equals("debug_video"))) {
          break label400;
        }
        bool1 = true;
        label149:
        this.e = bool1;
        if ((!q.a(q.this).equals("rewarded_video")) && (!q.a(q.this).equals("debug_rewarded_video"))) {
          break label405;
        }
        bool1 = true;
        label187:
        this.f = bool1;
        if ((!q.a(q.this).equals("native")) && (!q.a(q.this).equals("debug_native"))) {
          break label410;
        }
        bool1 = true;
        label225:
        this.g = bool1;
        if ((!q.a(q.this).equals("debug")) && (!q.a(q.this).equals("debug_banner_320")) && (!q.a(q.this).equals("debug_video")) && (!q.a(q.this).equals("debug_rewarded_video")) && (!q.a(q.this).equals("debug_banner_mrec")) && (!q.a(q.this).equals("debug_native"))) {
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
