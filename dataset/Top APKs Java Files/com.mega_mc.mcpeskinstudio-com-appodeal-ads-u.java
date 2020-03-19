package com.appodeal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.appodeal.ads.d.h;
import com.appodeal.ads.f.c;
import com.appodeal.ads.f.f;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class u
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
  private final Long l;
  private final int m;
  
  public u(Context paramContext, c paramC, a paramA, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, h paramH)
  {
    this(paramContext, paramC, paramA, paramInt, paramString1, paramString2, paramString3, paramString4, paramH, 0L, null, null, 1);
  }
  
  public u(Context paramContext, c paramC, a paramA, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, h paramH, long paramLong, String paramString5, Long paramLong1)
  {
    this(paramContext, paramC, paramA, paramInt, paramString1, paramString2, paramString3, paramString4, paramH, paramLong, paramString5, paramLong1, 1);
  }
  
  u(Context paramContext, c paramC, a paramA, int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, h paramH, long paramLong, String paramString5, Long paramLong1, int paramInt2)
  {
    this.a = paramA;
    this.b = paramContext;
    this.c = paramInt1;
    this.d = paramString1;
    this.e = paramString2;
    this.f = paramString3;
    this.g = paramString4;
    this.h = paramH;
    this.j = paramLong;
    this.i = paramC;
    this.k = paramString5;
    this.l = paramLong1;
    this.m = paramInt2;
    if ((AppodealSettings.a) && ((this.a == null) || ((this.a instanceof com.appodeal.ads.utils.u))) && ((this.d.equals("stats")) || (this.d.equals("show")) || (this.d.equals("click")) || (this.d.equals("finish")) || (this.d.equals("install")))) {
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
            new u.b(u.this, null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            return;
          }
          new u.b(u.this, null).execute(new Void[0]);
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
  
  public u(Context paramContext, c paramC, a paramA, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, h paramH, Long paramLong)
  {
    this(paramContext, paramC, paramA, paramInt, paramString1, paramString2, paramString3, paramString4, paramH, 0L, null, paramLong, 1);
  }
  
  u(Context paramContext, a paramA, int paramInt, String paramString, Long paramLong)
  {
    this(paramContext, null, paramA, paramInt, paramString, null, null, null, null, paramLong);
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
      int j = g.y;
      int k = o.q;
      int m = ai.n;
      int n = al.n;
      int i1 = Native.p;
      int i2 = w.u;
      int i3 = g.z;
      int i4 = o.r;
      int i5 = ai.p;
      int i6 = al.p;
      int i7 = Native.q;
      int i8 = w.v;
      int i9 = al.o;
      int i10 = ai.o;
      try
      {
        localJSONObject.put("show", j + k + m + n + i1 + i2);
        localJSONObject.put("click", i3 + i4 + i5 + i6 + i7 + i8);
        if ((this.e) || (this.f) || ((u.i(u.this) != null) && ((u.i(u.this).equals("video")) || (u.i(u.this).equals("rewarded_video"))))) {
          localJSONObject.put("finish", i9 + i10);
        }
        if ((this.b) || ((u.i(u.this) != null) && (u.i(u.this).equals("banner"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "show" }), o.q);
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "click" }), o.r);
        }
        if ((this.e) || ((u.i(u.this) != null) && (u.i(u.this).equals("video"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "video", "show" }), ai.n);
          localJSONObject.put(String.format("%s_%s", new Object[] { "video", "click" }), ai.p);
          localJSONObject.put(String.format("%s_%s", new Object[] { "video", "finish" }), ai.o);
        }
        if ((this.f) || ((u.i(u.this) != null) && (u.i(u.this).equals("rewarded_video"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "show" }), al.n);
          localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "click" }), al.p);
          localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "finish" }), al.o);
        }
        if ((this.c) || ((u.i(u.this) != null) && (u.i(u.this).equals("banner_320"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "show" }), g.y);
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "click" }), g.z);
        }
        if ((this.d) || ((u.i(u.this) != null) && (u.i(u.this).equals("banner_mrec"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "show" }), w.u);
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "click" }), w.v);
        }
        if ((this.g) || ((u.i(u.this) != null) && (u.i(u.this).equals("native"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "native", "show" }), Native.p);
          localJSONObject.put(String.format("%s_%s", new Object[] { "native", "click" }), Native.q);
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
      if ((localJSONArray == null) || (localJSONArray.length() == 0)) {}
      for (;;)
      {
        return;
        paramJSONObject = new com.appodeal.ads.f.g(u.b(u.this), paramJSONObject);
        if (!paramJSONObject.b(localJSONArray)) {
          continue;
        }
        paramJSONObject = paramJSONObject.a(localJSONArray);
        if (paramJSONObject != null) {}
        try
        {
          paramJSONObject.a();
          com.appodeal.ads.f.g.a(paramJSONObject);
          if (Appodeal.d == null) {
            continue;
          }
          Appodeal.d.a();
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
    }
    
    /* Error */
    protected JSONObject a(Void... paramVarArgs)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   4: invokestatic 156	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   7: ldc -71
      //   9: iconst_0
      //   10: invokevirtual 191	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   13: astore 9
      //   15: aload_0
      //   16: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   19: invokestatic 156	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   22: invokevirtual 195	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   25: astore 10
      //   27: aload 9
      //   29: ldc -59
      //   31: aconst_null
      //   32: invokeinterface 203 3 0
      //   37: astore 11
      //   39: aload 11
      //   41: ifnonnull +5 -> 46
      //   44: aconst_null
      //   45: areturn
      //   46: aload 9
      //   48: ldc -51
      //   50: aconst_null
      //   51: invokeinterface 203 3 0
      //   56: astore 8
      //   58: aload 9
      //   60: ldc -49
      //   62: aconst_null
      //   63: invokeinterface 203 3 0
      //   68: astore_1
      //   69: aload_1
      //   70: astore 6
      //   72: aload 8
      //   74: astore 7
      //   76: aload 8
      //   78: ifnonnull +2138 -> 2216
      //   81: aload_0
      //   82: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   85: invokestatic 209	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   88: ldc -45
      //   90: invokevirtual 103	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   93: istore 5
      //   95: aload_1
      //   96: astore 6
      //   98: aload 8
      //   100: astore 7
      //   102: iload 5
      //   104: ifne +2112 -> 2216
      //   107: aload_1
      //   108: astore 6
      //   110: aload 8
      //   112: astore 7
      //   114: ldc -43
      //   116: invokestatic 219	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
      //   119: pop
      //   120: aload_1
      //   121: astore 6
      //   123: aload 8
      //   125: astore 7
      //   127: ldc -35
      //   129: ldc -33
      //   131: iconst_1
      //   132: anewarray 215	java/lang/Class
      //   135: dup
      //   136: iconst_0
      //   137: ldc -69
      //   139: aastore
      //   140: invokevirtual 227	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      //   143: pop
      //   144: aload_1
      //   145: astore 6
      //   147: aload 8
      //   149: astore 7
      //   151: aload_0
      //   152: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   155: invokestatic 156	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   158: invokestatic 230	com/google/android/gms/ads/identifier/AdvertisingIdClient:getAdvertisingIdInfo	(Landroid/content/Context;)Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;
      //   161: astore 12
      //   163: aload_1
      //   164: astore 6
      //   166: aload 8
      //   168: astore 7
      //   170: aload 12
      //   172: invokevirtual 236	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:getId	()Ljava/lang/String;
      //   175: astore 8
      //   177: aload_1
      //   178: astore 6
      //   180: aload 8
      //   182: astore 7
      //   184: aload 12
      //   186: invokevirtual 240	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:isLimitAdTrackingEnabled	()Z
      //   189: ifeq +2015 -> 2204
      //   192: ldc -14
      //   194: astore_1
      //   195: aload_1
      //   196: astore 6
      //   198: aload 8
      //   200: astore 7
      //   202: aload 9
      //   204: invokeinterface 246 1 0
      //   209: astore 12
      //   211: aload_1
      //   212: astore 6
      //   214: aload 8
      //   216: astore 7
      //   218: aload 12
      //   220: ldc -51
      //   222: aload 8
      //   224: invokeinterface 252 3 0
      //   229: pop
      //   230: aload_1
      //   231: astore 6
      //   233: aload 8
      //   235: astore 7
      //   237: aload 12
      //   239: ldc -49
      //   241: aload_1
      //   242: invokeinterface 252 3 0
      //   247: pop
      //   248: aload_1
      //   249: astore 6
      //   251: aload 8
      //   253: astore 7
      //   255: aload 12
      //   257: invokeinterface 255 1 0
      //   262: aload_1
      //   263: astore 6
      //   265: aload 8
      //   267: astore 7
      //   269: ldc_w 257
      //   272: iconst_1
      //   273: anewarray 115	java/lang/Object
      //   276: dup
      //   277: iconst_0
      //   278: aload 8
      //   280: aastore
      //   281: invokestatic 119	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   284: invokestatic 260	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   287: aload_1
      //   288: astore 6
      //   290: aload 8
      //   292: astore_1
      //   293: aload_1
      //   294: ifnonnull +3120 -> 3414
      //   297: aload_0
      //   298: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   301: invokestatic 156	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   304: invokestatic 266	com/appodeal/ads/ao:l	(Landroid/content/Context;)Ljava/lang/String;
      //   307: astore_1
      //   308: new 34	org/json/JSONObject
      //   311: dup
      //   312: invokespecial 35	org/json/JSONObject:<init>	()V
      //   315: astore 8
      //   317: aload 8
      //   319: ldc_w 268
      //   322: aload 11
      //   324: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   327: pop
      //   328: aload_0
      //   329: getfield 109	com/appodeal/ads/u$b:b	Z
      //   332: ifeq +14 -> 346
      //   335: aload 8
      //   337: ldc_w 273
      //   340: ldc 111
      //   342: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   345: pop
      //   346: aload_0
      //   347: getfield 121	com/appodeal/ads/u$b:c	Z
      //   350: ifeq +68 -> 418
      //   353: aload 8
      //   355: ldc_w 273
      //   358: ldc 123
      //   360: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   363: pop
      //   364: aload_0
      //   365: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   368: invokestatic 156	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   371: invokestatic 276	com/appodeal/ads/ao:g	(Landroid/content/Context;)F
      //   374: fstore_2
      //   375: aload_0
      //   376: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   379: invokestatic 156	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   382: invokestatic 278	com/appodeal/ads/ao:h	(Landroid/content/Context;)F
      //   385: fstore_3
      //   386: getstatic 281	com/appodeal/ads/g:t	Z
      //   389: ifeq +29 -> 418
      //   392: fload_2
      //   393: ldc_w 282
      //   396: fcmpl
      //   397: iflt +21 -> 418
      //   400: fload_3
      //   401: ldc_w 283
      //   404: fcmpl
      //   405: ifle +13 -> 418
      //   408: aload 8
      //   410: ldc_w 285
      //   413: iconst_1
      //   414: invokevirtual 288	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   417: pop
      //   418: aload_0
      //   419: getfield 125	com/appodeal/ads/u$b:d	Z
      //   422: ifeq +14 -> 436
      //   425: aload 8
      //   427: ldc_w 273
      //   430: ldc 127
      //   432: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   435: pop
      //   436: aload_0
      //   437: getfield 90	com/appodeal/ads/u$b:e	Z
      //   440: ifne +10 -> 450
      //   443: aload_0
      //   444: getfield 92	com/appodeal/ads/u$b:f	Z
      //   447: ifeq +14 -> 461
      //   450: aload 8
      //   452: ldc_w 273
      //   455: ldc 97
      //   457: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   460: pop
      //   461: aload_0
      //   462: getfield 92	com/appodeal/ads/u$b:f	Z
      //   465: ifeq +12 -> 477
      //   468: aload 8
      //   470: ldc 105
      //   472: iconst_1
      //   473: invokevirtual 288	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   476: pop
      //   477: aload_0
      //   478: getfield 129	com/appodeal/ads/u$b:g	Z
      //   481: ifeq +14 -> 495
      //   484: aload 8
      //   486: ldc_w 273
      //   489: ldc -125
      //   491: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   494: pop
      //   495: aload_0
      //   496: getfield 290	com/appodeal/ads/u$b:h	Z
      //   499: ifeq +13 -> 512
      //   502: aload 8
      //   504: ldc_w 292
      //   507: iconst_1
      //   508: invokevirtual 288	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   511: pop
      //   512: getstatic 296	com/appodeal/ads/AppodealSettings:a	Z
      //   515: ifeq +13 -> 528
      //   518: aload 8
      //   520: ldc_w 298
      //   523: iconst_1
      //   524: invokevirtual 288	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   527: pop
      //   528: aload 8
      //   530: ldc_w 300
      //   533: getstatic 306	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
      //   536: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   539: pop
      //   540: aload 8
      //   542: ldc_w 308
      //   545: getstatic 311	android/os/Build$VERSION:SDK_INT	I
      //   548: invokevirtual 86	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
      //   551: pop
      //   552: aload 8
      //   554: ldc_w 313
      //   557: ldc_w 315
      //   560: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   563: pop
      //   564: aload_0
      //   565: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   568: invokestatic 156	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   571: invokevirtual 318	android/content/Context:getPackageName	()Ljava/lang/String;
      //   574: astore 7
      //   576: aload 8
      //   578: ldc_w 320
      //   581: aload 7
      //   583: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   586: pop
      //   587: aload 10
      //   589: aload 7
      //   591: iconst_0
      //   592: invokevirtual 326	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
      //   595: astore 11
      //   597: aload 8
      //   599: ldc_w 328
      //   602: aload 11
      //   604: getfield 333	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
      //   607: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   610: pop
      //   611: aload 8
      //   613: ldc_w 335
      //   616: aload 11
      //   618: getfield 338	android/content/pm/PackageInfo:versionCode	I
      //   621: invokevirtual 86	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
      //   624: pop
      //   625: aload 8
      //   627: ldc_w 340
      //   630: aload 10
      //   632: aload 7
      //   634: sipush 128
      //   637: invokevirtual 344	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
      //   640: getfield 350	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
      //   643: ldc_w 352
      //   646: invokevirtual 357	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   649: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   652: pop
      //   653: aload 8
      //   655: ldc_w 359
      //   658: aload_1
      //   659: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   662: pop
      //   663: aload 8
      //   665: ldc_w 361
      //   668: aload 6
      //   670: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   673: pop
      //   674: aload_0
      //   675: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   678: invokestatic 156	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   681: invokestatic 364	com/appodeal/ads/ao:b	(Landroid/content/Context;)Lcom/appodeal/ads/ao$a;
      //   684: astore 11
      //   686: aload 8
      //   688: ldc_w 366
      //   691: aload 11
      //   693: getfield 370	com/appodeal/ads/ao$a:a	Ljava/lang/String;
      //   696: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   699: pop
      //   700: aload 8
      //   702: ldc_w 372
      //   705: aload_0
      //   706: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   709: invokestatic 156	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   712: invokestatic 375	com/appodeal/ads/ao:k	(Landroid/content/Context;)F
      //   715: f2d
      //   716: invokevirtual 378	org/json/JSONObject:put	(Ljava/lang/String;D)Lorg/json/JSONObject;
      //   719: pop
      //   720: aload_0
      //   721: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   724: invokestatic 156	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   727: invokestatic 381	com/appodeal/ads/ao:f	(Landroid/content/Context;)Landroid/util/Pair;
      //   730: astore_1
      //   731: aload 8
      //   733: ldc_w 383
      //   736: aload_1
      //   737: getfield 389	android/util/Pair:first	Ljava/lang/Object;
      //   740: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   743: pop
      //   744: aload 8
      //   746: ldc_w 391
      //   749: aload_1
      //   750: getfield 394	android/util/Pair:second	Ljava/lang/Object;
      //   753: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   756: pop
      //   757: aload_0
      //   758: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   761: invokestatic 156	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   764: invokestatic 397	com/appodeal/ads/ao:n	(Landroid/content/Context;)Z
      //   767: ifeq +1475 -> 2242
      //   770: aload 8
      //   772: ldc_w 399
      //   775: ldc_w 401
      //   778: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   781: pop
      //   782: getstatic 406	android/os/Build:MANUFACTURER	Ljava/lang/String;
      //   785: ldc_w 408
      //   788: invokevirtual 103	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   791: ifeq +2626 -> 3417
      //   794: ldc_w 410
      //   797: astore_1
      //   798: aload 8
      //   800: ldc_w 412
      //   803: aload_1
      //   804: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   807: pop
      //   808: aload 10
      //   810: aload 7
      //   812: invokevirtual 415	android/content/pm/PackageManager:getInstallerPackageName	(Ljava/lang/String;)Ljava/lang/String;
      //   815: astore 6
      //   817: aload 6
      //   819: astore_1
      //   820: aload 6
      //   822: ifnonnull +7 -> 829
      //   825: ldc_w 417
      //   828: astore_1
      //   829: aload 8
      //   831: ldc_w 419
      //   834: aload_1
      //   835: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   838: pop
      //   839: aload 8
      //   841: ldc_w 421
      //   844: getstatic 406	android/os/Build:MANUFACTURER	Ljava/lang/String;
      //   847: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   850: pop
      //   851: aload 8
      //   853: ldc_w 423
      //   856: ldc_w 425
      //   859: iconst_2
      //   860: anewarray 115	java/lang/Object
      //   863: dup
      //   864: iconst_0
      //   865: getstatic 406	android/os/Build:MANUFACTURER	Ljava/lang/String;
      //   868: aastore
      //   869: dup
      //   870: iconst_1
      //   871: getstatic 428	android/os/Build:MODEL	Ljava/lang/String;
      //   874: aastore
      //   875: invokestatic 119	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   878: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   881: pop
      //   882: aload_0
      //   883: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   886: invokestatic 156	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   889: invokestatic 430	com/appodeal/ads/ao:d	(Landroid/content/Context;)Landroid/util/Pair;
      //   892: astore_1
      //   893: aload 8
      //   895: ldc_w 432
      //   898: aload_1
      //   899: getfield 389	android/util/Pair:first	Ljava/lang/Object;
      //   902: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   905: pop
      //   906: aload 8
      //   908: ldc_w 434
      //   911: aload_1
      //   912: getfield 394	android/util/Pair:second	Ljava/lang/Object;
      //   915: checkcast 385	android/util/Pair
      //   918: getfield 389	android/util/Pair:first	Ljava/lang/Object;
      //   921: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   924: pop
      //   925: aload 8
      //   927: ldc_w 436
      //   930: aload_1
      //   931: getfield 394	android/util/Pair:second	Ljava/lang/Object;
      //   934: checkcast 385	android/util/Pair
      //   937: getfield 394	android/util/Pair:second	Ljava/lang/Object;
      //   940: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   943: pop
      //   944: aload 8
      //   946: ldc_w 438
      //   949: aload 11
      //   951: getfield 440	com/appodeal/ads/ao$a:b	Ljava/lang/String;
      //   954: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   957: pop
      //   958: aload 8
      //   960: ldc_w 442
      //   963: aload 11
      //   965: getfield 443	com/appodeal/ads/ao$a:c	Z
      //   968: invokevirtual 288	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   971: pop
      //   972: aload 8
      //   974: ldc_w 445
      //   977: aload_0
      //   978: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   981: invokestatic 156	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   984: invokestatic 447	com/appodeal/ads/ao:c	(Landroid/content/Context;)Ljava/lang/String;
      //   987: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   990: pop
      //   991: aload 8
      //   993: ldc_w 449
      //   996: invokestatic 455	java/util/Locale:getDefault	()Ljava/util/Locale;
      //   999: invokevirtual 458	java/util/Locale:toString	()Ljava/lang/String;
      //   1002: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1005: pop
      //   1006: ldc_w 460
      //   1009: invokestatic 466	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
      //   1012: getstatic 470	java/util/Locale:ENGLISH	Ljava/util/Locale;
      //   1015: invokestatic 476	java/util/Calendar:getInstance	(Ljava/util/TimeZone;Ljava/util/Locale;)Ljava/util/Calendar;
      //   1018: astore_1
      //   1019: aload 8
      //   1021: ldc_w 478
      //   1024: new 480	java/text/SimpleDateFormat
      //   1027: dup
      //   1028: ldc_w 481
      //   1031: getstatic 470	java/util/Locale:ENGLISH	Ljava/util/Locale;
      //   1034: invokespecial 484	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
      //   1037: aload_1
      //   1038: invokevirtual 488	java/util/Calendar:getTime	()Ljava/util/Date;
      //   1041: invokevirtual 491	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
      //   1044: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1047: pop
      //   1048: aload 8
      //   1050: ldc_w 493
      //   1053: invokestatic 499	java/lang/System:currentTimeMillis	()J
      //   1056: ldc2_w 500
      //   1059: ldiv
      //   1060: invokevirtual 504	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   1063: pop
      //   1064: aload 8
      //   1066: ldc_w 506
      //   1069: ldc_w 508
      //   1072: invokestatic 511	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
      //   1075: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1078: pop
      //   1079: aload 8
      //   1081: ldc_w 513
      //   1084: invokestatic 515	com/appodeal/ads/ao:a	()Z
      //   1087: invokevirtual 288	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   1090: pop
      //   1091: aload_0
      //   1092: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1095: invokestatic 156	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   1098: invokestatic 520	com/appodeal/ads/utils/d:c	(Landroid/content/Context;)V
      //   1101: aload 8
      //   1103: ldc_w 522
      //   1106: aload 9
      //   1108: invokestatic 525	com/appodeal/ads/utils/d:a	(Landroid/content/SharedPreferences;)J
      //   1111: invokevirtual 504	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   1114: pop
      //   1115: aload 8
      //   1117: ldc_w 527
      //   1120: invokestatic 529	com/appodeal/ads/utils/d:b	()J
      //   1123: invokevirtual 504	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   1126: pop
      //   1127: aload 8
      //   1129: ldc_w 531
      //   1132: aload 9
      //   1134: invokestatic 533	com/appodeal/ads/utils/d:b	(Landroid/content/SharedPreferences;)J
      //   1137: invokevirtual 504	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   1140: pop
      //   1141: aload 9
      //   1143: ldc_w 535
      //   1146: aconst_null
      //   1147: invokeinterface 203 3 0
      //   1152: astore_1
      //   1153: aload_1
      //   1154: ifnull +20 -> 1174
      //   1157: aload 8
      //   1159: ldc_w 535
      //   1162: new 34	org/json/JSONObject
      //   1165: dup
      //   1166: aload_1
      //   1167: invokespecial 537	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   1170: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1173: pop
      //   1174: aload_0
      //   1175: getfield 539	com/appodeal/ads/u$b:i	Z
      //   1178: ifeq +1429 -> 2607
      //   1181: new 147	org/json/JSONArray
      //   1184: dup
      //   1185: invokespecial 540	org/json/JSONArray:<init>	()V
      //   1188: astore_1
      //   1189: aload_0
      //   1190: getfield 109	com/appodeal/ads/u$b:b	Z
      //   1193: ifeq +1072 -> 2265
      //   1196: aload_0
      //   1197: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1200: invokestatic 156	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   1203: invokestatic 543	com/appodeal/ads/o:a	(Landroid/content/Context;)Ljava/util/Set;
      //   1206: invokeinterface 549 1 0
      //   1211: astore 6
      //   1213: aload 6
      //   1215: invokeinterface 554 1 0
      //   1220: ifeq +1045 -> 2265
      //   1223: aload 6
      //   1225: invokeinterface 558 1 0
      //   1230: checkcast 560	com/appodeal/ads/p
      //   1233: astore 7
      //   1235: aload 7
      //   1237: invokevirtual 563	com/appodeal/ads/p:h	()Lcom/appodeal/ads/s;
      //   1240: ifnull -27 -> 1213
      //   1243: aload_1
      //   1244: aload 7
      //   1246: invokevirtual 565	com/appodeal/ads/p:a	()Ljava/lang/String;
      //   1249: invokevirtual 568	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   1252: pop
      //   1253: goto -40 -> 1213
      //   1256: astore_1
      //   1257: aload_1
      //   1258: invokestatic 136	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   1261: invokestatic 571	java/util/Calendar:getInstance	()Ljava/util/Calendar;
      //   1264: astore_1
      //   1265: aload_1
      //   1266: aload 9
      //   1268: ldc_w 573
      //   1271: lconst_0
      //   1272: invokeinterface 577 4 0
      //   1277: invokevirtual 581	java/util/Calendar:setTimeInMillis	(J)V
      //   1280: aload_1
      //   1281: iconst_5
      //   1282: iconst_1
      //   1283: invokevirtual 585	java/util/Calendar:add	(II)V
      //   1286: getstatic 590	com/appodeal/ads/UserSettings:sendingInProgress	Z
      //   1289: ifne +122 -> 1411
      //   1292: aload_0
      //   1293: getfield 539	com/appodeal/ads/u$b:i	Z
      //   1296: ifeq +115 -> 1411
      //   1299: aload_1
      //   1300: invokevirtual 593	java/util/Calendar:getTimeInMillis	()J
      //   1303: invokestatic 499	java/lang/System:currentTimeMillis	()J
      //   1306: lcmp
      //   1307: iflt +17 -> 1324
      //   1310: aload 9
      //   1312: ldc_w 595
      //   1315: iconst_1
      //   1316: invokeinterface 599 3 0
      //   1321: ifeq +90 -> 1411
      //   1324: iconst_1
      //   1325: putstatic 590	com/appodeal/ads/UserSettings:sendingInProgress	Z
      //   1328: aload 8
      //   1330: ldc_w 601
      //   1333: aload_0
      //   1334: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1337: invokestatic 156	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   1340: invokestatic 606	com/appodeal/ads/utils/r:a	(Landroid/content/Context;)Lorg/json/JSONArray;
      //   1343: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1346: pop
      //   1347: aload 8
      //   1349: ldc_w 608
      //   1352: aload_0
      //   1353: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1356: invokestatic 156	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   1359: invokestatic 612	com/appodeal/ads/Appodeal:getUserSettings	(Landroid/content/Context;)Lcom/appodeal/ads/UserSettings;
      //   1362: invokevirtual 614	com/appodeal/ads/UserSettings:a	()Lorg/json/JSONObject;
      //   1365: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1368: pop
      //   1369: aload 9
      //   1371: invokeinterface 246 1 0
      //   1376: astore_1
      //   1377: aload_1
      //   1378: ldc_w 573
      //   1381: invokestatic 499	java/lang/System:currentTimeMillis	()J
      //   1384: invokeinterface 618 4 0
      //   1389: pop
      //   1390: aload_1
      //   1391: ldc_w 595
      //   1394: iconst_0
      //   1395: invokeinterface 622 3 0
      //   1400: pop
      //   1401: aload_1
      //   1402: invokeinterface 255 1 0
      //   1407: iconst_0
      //   1408: putstatic 590	com/appodeal/ads/UserSettings:sendingInProgress	Z
      //   1411: invokestatic 571	java/util/Calendar:getInstance	()Ljava/util/Calendar;
      //   1414: astore_1
      //   1415: aload_1
      //   1416: aload 9
      //   1418: ldc_w 624
      //   1421: lconst_0
      //   1422: invokeinterface 577 4 0
      //   1427: invokevirtual 581	java/util/Calendar:setTimeInMillis	(J)V
      //   1430: aload_1
      //   1431: iconst_5
      //   1432: iconst_1
      //   1433: invokevirtual 585	java/util/Calendar:add	(II)V
      //   1436: getstatic 626	com/appodeal/ads/AppodealSettings:n	Z
      //   1439: ifne +159 -> 1598
      //   1442: getstatic 629	com/appodeal/ads/AppodealSettings:m	Z
      //   1445: ifeq +153 -> 1598
      //   1448: aload_0
      //   1449: getfield 539	com/appodeal/ads/u$b:i	Z
      //   1452: ifeq +146 -> 1598
      //   1455: aload_1
      //   1456: invokevirtual 593	java/util/Calendar:getTimeInMillis	()J
      //   1459: invokestatic 499	java/lang/System:currentTimeMillis	()J
      //   1462: lcmp
      //   1463: ifge +135 -> 1598
      //   1466: iconst_1
      //   1467: putstatic 626	com/appodeal/ads/AppodealSettings:n	Z
      //   1470: new 147	org/json/JSONArray
      //   1473: dup
      //   1474: invokespecial 540	org/json/JSONArray:<init>	()V
      //   1477: astore_1
      //   1478: aload 10
      //   1480: iconst_0
      //   1481: invokevirtual 633	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
      //   1484: astore 7
      //   1486: ldc_w 635
      //   1489: invokestatic 641	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
      //   1492: astore 6
      //   1494: aload 7
      //   1496: invokeinterface 644 1 0
      //   1501: astore 7
      //   1503: aload 7
      //   1505: invokeinterface 554 1 0
      //   1510: ifeq +1393 -> 2903
      //   1513: aload 7
      //   1515: invokeinterface 558 1 0
      //   1520: checkcast 346	android/content/pm/ApplicationInfo
      //   1523: getfield 647	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   1526: astore 10
      //   1528: aload 6
      //   1530: aload 10
      //   1532: invokevirtual 651	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
      //   1535: invokevirtual 656	java/util/regex/Matcher:matches	()Z
      //   1538: ifne -35 -> 1503
      //   1541: aload 10
      //   1543: ldc_w 300
      //   1546: invokevirtual 103	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1549: ifne -46 -> 1503
      //   1552: aload_1
      //   1553: aload 10
      //   1555: invokevirtual 568	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   1558: pop
      //   1559: goto -56 -> 1503
      //   1562: astore_1
      //   1563: aload_1
      //   1564: invokestatic 136	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   1567: aload 9
      //   1569: invokeinterface 246 1 0
      //   1574: astore_1
      //   1575: aload_1
      //   1576: ldc_w 624
      //   1579: invokestatic 499	java/lang/System:currentTimeMillis	()J
      //   1582: invokeinterface 618 4 0
      //   1587: pop
      //   1588: aload_1
      //   1589: invokeinterface 255 1 0
      //   1594: iconst_0
      //   1595: putstatic 626	com/appodeal/ads/AppodealSettings:n	Z
      //   1598: aload_0
      //   1599: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1602: invokestatic 659	com/appodeal/ads/u:k	(Lcom/appodeal/ads/u;)Lcom/appodeal/ads/d/h;
      //   1605: ifnull +41 -> 1646
      //   1608: aload_0
      //   1609: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1612: invokestatic 209	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   1615: ldc_w 661
      //   1618: invokevirtual 103	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1621: ifeq +1295 -> 2916
      //   1624: aload 8
      //   1626: ldc_w 663
      //   1629: aload_0
      //   1630: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1633: invokestatic 659	com/appodeal/ads/u:k	(Lcom/appodeal/ads/u;)Lcom/appodeal/ads/d/h;
      //   1636: invokevirtual 666	com/appodeal/ads/d/h:a	()Lorg/json/JSONObject;
      //   1639: invokevirtual 667	org/json/JSONObject:toString	()Ljava/lang/String;
      //   1642: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1645: pop
      //   1646: aload_0
      //   1647: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1650: invokestatic 209	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   1653: ldc_w 661
      //   1656: invokevirtual 103	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1659: ifne +1765 -> 3424
      //   1662: aload_0
      //   1663: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1666: invokestatic 209	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   1669: ldc 82
      //   1671: invokevirtual 103	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1674: ifne +1750 -> 3424
      //   1677: aload_0
      //   1678: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1681: invokestatic 209	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   1684: ldc 88
      //   1686: invokevirtual 103	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1689: ifne +1735 -> 3424
      //   1692: aload_0
      //   1693: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1696: invokestatic 209	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   1699: ldc 107
      //   1701: invokevirtual 103	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1704: ifne +1720 -> 3424
      //   1707: aload_0
      //   1708: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1711: invokestatic 209	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   1714: ldc -45
      //   1716: invokevirtual 103	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1719: ifne +1705 -> 3424
      //   1722: iconst_1
      //   1723: istore 4
      //   1725: iload 4
      //   1727: ifeq +1344 -> 3071
      //   1730: new 669	java/net/URL
      //   1733: dup
      //   1734: ldc_w 671
      //   1737: iconst_4
      //   1738: anewarray 115	java/lang/Object
      //   1741: dup
      //   1742: iconst_0
      //   1743: invokestatic 675	com/appodeal/ads/utils/g:c	()I
      //   1746: invokestatic 678	com/appodeal/ads/utils/g:a	(I)Ljava/lang/String;
      //   1749: aastore
      //   1750: dup
      //   1751: iconst_1
      //   1752: invokestatic 680	com/appodeal/ads/utils/g:b	()Ljava/lang/String;
      //   1755: aastore
      //   1756: dup
      //   1757: iconst_2
      //   1758: invokestatic 675	com/appodeal/ads/utils/g:c	()I
      //   1761: invokestatic 686	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   1764: aastore
      //   1765: dup
      //   1766: iconst_3
      //   1767: ldc_w 688
      //   1770: aastore
      //   1771: invokestatic 119	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   1774: invokespecial 689	java/net/URL:<init>	(Ljava/lang/String;)V
      //   1777: astore_1
      //   1778: aload_0
      //   1779: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1782: invokestatic 156	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   1785: ldc_w 691
      //   1788: iconst_0
      //   1789: invokevirtual 191	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   1792: astore 9
      //   1794: aload_1
      //   1795: invokevirtual 695	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   1798: astore 7
      //   1800: aload_1
      //   1801: invokevirtual 698	java/net/URL:getProtocol	()Ljava/lang/String;
      //   1804: ldc_w 700
      //   1807: invokevirtual 103	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1810: ifeq +79 -> 1889
      //   1813: new 702	java/util/ArrayList
      //   1816: dup
      //   1817: invokespecial 703	java/util/ArrayList:<init>	()V
      //   1820: astore_1
      //   1821: aload_1
      //   1822: ldc_w 705
      //   1825: invokeinterface 707 2 0
      //   1830: pop
      //   1831: aload_1
      //   1832: ldc_w 709
      //   1835: invokeinterface 707 2 0
      //   1840: pop
      //   1841: new 711	com/appodeal/ads/utils/e
      //   1844: dup
      //   1845: aload_1
      //   1846: ldc2_w 712
      //   1849: invokespecial 716	com/appodeal/ads/utils/e:<init>	(Ljava/util/List;J)V
      //   1852: astore_1
      //   1853: ldc_w 718
      //   1856: invokestatic 723	javax/net/ssl/SSLContext:getInstance	(Ljava/lang/String;)Ljavax/net/ssl/SSLContext;
      //   1859: astore 6
      //   1861: aload 6
      //   1863: aconst_null
      //   1864: iconst_1
      //   1865: anewarray 725	javax/net/ssl/TrustManager
      //   1868: dup
      //   1869: iconst_0
      //   1870: aload_1
      //   1871: aastore
      //   1872: aconst_null
      //   1873: invokevirtual 729	javax/net/ssl/SSLContext:init	([Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Ljava/security/SecureRandom;)V
      //   1876: aload 7
      //   1878: checkcast 731	javax/net/ssl/HttpsURLConnection
      //   1881: aload 6
      //   1883: invokevirtual 735	javax/net/ssl/SSLContext:getSocketFactory	()Ljavax/net/ssl/SSLSocketFactory;
      //   1886: invokevirtual 739	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
      //   1889: iload 4
      //   1891: ifeq +1235 -> 3126
      //   1894: aload 9
      //   1896: aload_0
      //   1897: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1900: invokestatic 209	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   1903: invokeinterface 743 2 0
      //   1908: ifeq +1218 -> 3126
      //   1911: aload 7
      //   1913: sipush 10000
      //   1916: invokevirtual 749	java/net/URLConnection:setConnectTimeout	(I)V
      //   1919: aload 7
      //   1921: sipush 10000
      //   1924: invokevirtual 752	java/net/URLConnection:setReadTimeout	(I)V
      //   1927: aload 7
      //   1929: sipush 20000
      //   1932: invokevirtual 749	java/net/URLConnection:setConnectTimeout	(I)V
      //   1935: aload 7
      //   1937: sipush 20000
      //   1940: invokevirtual 752	java/net/URLConnection:setReadTimeout	(I)V
      //   1943: aload 7
      //   1945: ldc_w 754
      //   1948: ldc_w 756
      //   1951: invokevirtual 760	java/net/URLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   1954: aload 7
      //   1956: iconst_1
      //   1957: invokevirtual 764	java/net/URLConnection:setDoOutput	(Z)V
      //   1960: new 766	java/io/ByteArrayOutputStream
      //   1963: dup
      //   1964: invokespecial 767	java/io/ByteArrayOutputStream:<init>	()V
      //   1967: astore 6
      //   1969: new 769	java/util/zip/GZIPOutputStream
      //   1972: dup
      //   1973: aload 6
      //   1975: invokespecial 772	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   1978: astore_1
      //   1979: aload_1
      //   1980: aload 8
      //   1982: invokevirtual 667	org/json/JSONObject:toString	()Ljava/lang/String;
      //   1985: ldc_w 774
      //   1988: invokevirtual 778	java/lang/String:getBytes	(Ljava/lang/String;)[B
      //   1991: invokevirtual 782	java/util/zip/GZIPOutputStream:write	([B)V
      //   1994: aload_1
      //   1995: invokevirtual 785	java/util/zip/GZIPOutputStream:close	()V
      //   1998: aload 6
      //   2000: invokevirtual 789	java/io/ByteArrayOutputStream:toByteArray	()[B
      //   2003: iconst_0
      //   2004: invokestatic 795	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
      //   2007: astore_1
      //   2008: aload 7
      //   2010: invokevirtual 799	java/net/URLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   2013: aload_1
      //   2014: invokestatic 802	com/appodeal/ads/ao:a	(Ljava/io/OutputStream;Ljava/lang/String;)V
      //   2017: aload 7
      //   2019: invokevirtual 806	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
      //   2022: invokestatic 809	com/appodeal/ads/ao:a	(Ljava/io/InputStream;)Ljava/lang/String;
      //   2025: astore 6
      //   2027: aload 6
      //   2029: ifnull +42 -> 2071
      //   2032: aload 6
      //   2034: invokevirtual 812	java/lang/String:isEmpty	()Z
      //   2037: ifne +34 -> 2071
      //   2040: aload 6
      //   2042: ldc_w 814
      //   2045: invokevirtual 103	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2048: ifne +23 -> 2071
      //   2051: aload_0
      //   2052: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2055: invokestatic 209	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   2058: ldc_w 661
      //   2061: invokevirtual 103	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2064: istore 5
      //   2066: iload 5
      //   2068: ifeq +6 -> 2074
      //   2071: aconst_null
      //   2072: astore 6
      //   2074: aload 6
      //   2076: astore_1
      //   2077: aload 7
      //   2079: ifnull +22 -> 2101
      //   2082: aload 7
      //   2084: instanceof 731
      //   2087: ifeq +1155 -> 3242
      //   2090: aload 7
      //   2092: checkcast 731	javax/net/ssl/HttpsURLConnection
      //   2095: invokevirtual 817	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   2098: aload 6
      //   2100: astore_1
      //   2101: aload_1
      //   2102: ifnonnull +1241 -> 3343
      //   2105: iload 4
      //   2107: ifeq +1323 -> 3430
      //   2110: aload 9
      //   2112: aload_0
      //   2113: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2116: invokestatic 209	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   2119: invokeinterface 743 2 0
      //   2124: ifeq +1306 -> 3430
      //   2127: new 819	com/appodeal/ads/utils/b/a
      //   2130: dup
      //   2131: ldc_w 821
      //   2134: invokespecial 822	com/appodeal/ads/utils/b/a:<init>	(Ljava/lang/String;)V
      //   2137: invokestatic 136	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2140: aload 9
      //   2142: aload_0
      //   2143: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2146: invokestatic 209	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   2149: ldc_w 824
      //   2152: invokeinterface 203 3 0
      //   2157: astore_1
      //   2158: new 34	org/json/JSONObject
      //   2161: dup
      //   2162: aload_1
      //   2163: invokespecial 537	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   2166: astore_1
      //   2167: aload_1
      //   2168: invokestatic 826	com/appodeal/ads/Appodeal:a	(Lorg/json/JSONObject;)V
      //   2171: aload_1
      //   2172: ldc_w 828
      //   2175: invokevirtual 832	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   2178: putstatic 836	com/appodeal/ads/UserSettings:userData	Lorg/json/JSONObject;
      //   2181: aload_0
      //   2182: aload_1
      //   2183: invokespecial 838	com/appodeal/ads/u$b:b	(Lorg/json/JSONObject;)V
      //   2186: aload_1
      //   2187: areturn
      //   2188: astore 6
      //   2190: aload 6
      //   2192: invokestatic 136	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2195: aload_1
      //   2196: areturn
      //   2197: astore_1
      //   2198: aload_1
      //   2199: invokestatic 136	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2202: aconst_null
      //   2203: areturn
      //   2204: ldc_w 840
      //   2207: astore_1
      //   2208: goto -2013 -> 195
      //   2211: astore_1
      //   2212: aload_1
      //   2213: invokestatic 136	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2216: aload 7
      //   2218: astore_1
      //   2219: goto -1926 -> 293
      //   2222: astore 11
      //   2224: aload 11
      //   2226: invokestatic 136	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2229: goto -1604 -> 625
      //   2232: astore 11
      //   2234: aload 11
      //   2236: invokestatic 136	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2239: goto -1586 -> 653
      //   2242: aload 8
      //   2244: ldc_w 399
      //   2247: ldc_w 842
      //   2250: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2253: pop
      //   2254: goto -1472 -> 782
      //   2257: astore_1
      //   2258: aload_1
      //   2259: invokestatic 136	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2262: goto -1423 -> 839
      //   2265: aload_0
      //   2266: getfield 90	com/appodeal/ads/u$b:e	Z
      //   2269: ifeq +63 -> 2332
      //   2272: aload_0
      //   2273: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2276: invokestatic 156	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   2279: invokestatic 843	com/appodeal/ads/ai:a	(Landroid/content/Context;)Ljava/util/Set;
      //   2282: invokeinterface 549 1 0
      //   2287: astore 6
      //   2289: aload 6
      //   2291: invokeinterface 554 1 0
      //   2296: ifeq +36 -> 2332
      //   2299: aload 6
      //   2301: invokeinterface 558 1 0
      //   2306: checkcast 845	com/appodeal/ads/aq
      //   2309: astore 7
      //   2311: aload 7
      //   2313: invokevirtual 848	com/appodeal/ads/aq:h	()Lcom/appodeal/ads/ar;
      //   2316: ifnull -27 -> 2289
      //   2319: aload_1
      //   2320: aload 7
      //   2322: invokevirtual 849	com/appodeal/ads/aq:a	()Ljava/lang/String;
      //   2325: invokevirtual 568	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   2328: pop
      //   2329: goto -40 -> 2289
      //   2332: aload_0
      //   2333: getfield 92	com/appodeal/ads/u$b:f	Z
      //   2336: ifeq +63 -> 2399
      //   2339: aload_0
      //   2340: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2343: invokestatic 156	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   2346: invokestatic 850	com/appodeal/ads/al:a	(Landroid/content/Context;)Ljava/util/Set;
      //   2349: invokeinterface 549 1 0
      //   2354: astore 6
      //   2356: aload 6
      //   2358: invokeinterface 554 1 0
      //   2363: ifeq +36 -> 2399
      //   2366: aload 6
      //   2368: invokeinterface 558 1 0
      //   2373: checkcast 845	com/appodeal/ads/aq
      //   2376: astore 7
      //   2378: aload 7
      //   2380: invokevirtual 848	com/appodeal/ads/aq:h	()Lcom/appodeal/ads/ar;
      //   2383: ifnull -27 -> 2356
      //   2386: aload_1
      //   2387: aload 7
      //   2389: invokevirtual 849	com/appodeal/ads/aq:a	()Ljava/lang/String;
      //   2392: invokevirtual 568	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   2395: pop
      //   2396: goto -40 -> 2356
      //   2399: aload_0
      //   2400: getfield 121	com/appodeal/ads/u$b:c	Z
      //   2403: ifeq +63 -> 2466
      //   2406: aload_0
      //   2407: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2410: invokestatic 156	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   2413: invokestatic 851	com/appodeal/ads/g:a	(Landroid/content/Context;)Ljava/util/Set;
      //   2416: invokeinterface 549 1 0
      //   2421: astore 6
      //   2423: aload 6
      //   2425: invokeinterface 554 1 0
      //   2430: ifeq +36 -> 2466
      //   2433: aload 6
      //   2435: invokeinterface 558 1 0
      //   2440: checkcast 853	com/appodeal/ads/h
      //   2443: astore 7
      //   2445: aload 7
      //   2447: invokevirtual 856	com/appodeal/ads/h:f	()Lcom/appodeal/ads/k;
      //   2450: ifnull -27 -> 2423
      //   2453: aload_1
      //   2454: aload 7
      //   2456: invokevirtual 857	com/appodeal/ads/h:a	()Ljava/lang/String;
      //   2459: invokevirtual 568	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   2462: pop
      //   2463: goto -40 -> 2423
      //   2466: aload_0
      //   2467: getfield 125	com/appodeal/ads/u$b:d	Z
      //   2470: ifeq +63 -> 2533
      //   2473: aload_0
      //   2474: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2477: invokestatic 156	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   2480: invokestatic 858	com/appodeal/ads/w:a	(Landroid/content/Context;)Ljava/util/Set;
      //   2483: invokeinterface 549 1 0
      //   2488: astore 6
      //   2490: aload 6
      //   2492: invokeinterface 554 1 0
      //   2497: ifeq +36 -> 2533
      //   2500: aload 6
      //   2502: invokeinterface 558 1 0
      //   2507: checkcast 860	com/appodeal/ads/x
      //   2510: astore 7
      //   2512: aload 7
      //   2514: invokevirtual 863	com/appodeal/ads/x:f	()Lcom/appodeal/ads/aa;
      //   2517: ifnull -27 -> 2490
      //   2520: aload_1
      //   2521: aload 7
      //   2523: invokevirtual 864	com/appodeal/ads/x:a	()Ljava/lang/String;
      //   2526: invokevirtual 568	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   2529: pop
      //   2530: goto -40 -> 2490
      //   2533: aload_0
      //   2534: getfield 129	com/appodeal/ads/u$b:g	Z
      //   2537: ifeq +60 -> 2597
      //   2540: aload_0
      //   2541: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2544: invokestatic 156	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   2547: invokestatic 865	com/appodeal/ads/Native:a	(Landroid/content/Context;)Ljava/util/Set;
      //   2550: invokeinterface 549 1 0
      //   2555: astore 6
      //   2557: aload 6
      //   2559: invokeinterface 554 1 0
      //   2564: ifeq +33 -> 2597
      //   2567: aload 6
      //   2569: invokeinterface 558 1 0
      //   2574: checkcast 867	com/appodeal/ads/ad
      //   2577: astore 7
      //   2579: aload 7
      //   2581: ifnull -24 -> 2557
      //   2584: aload_1
      //   2585: aload 7
      //   2587: invokevirtual 868	com/appodeal/ads/ad:a	()Ljava/lang/String;
      //   2590: invokevirtual 568	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   2593: pop
      //   2594: goto -37 -> 2557
      //   2597: aload 8
      //   2599: ldc_w 870
      //   2602: aload_1
      //   2603: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2606: pop
      //   2607: aload_0
      //   2608: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2611: invokestatic 872	com/appodeal/ads/u:c	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   2614: ifnull +19 -> 2633
      //   2617: aload 8
      //   2619: ldc_w 874
      //   2622: aload_0
      //   2623: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2626: invokestatic 872	com/appodeal/ads/u:c	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   2629: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2632: pop
      //   2633: aload_0
      //   2634: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2637: invokestatic 877	com/appodeal/ads/u:d	(Lcom/appodeal/ads/u;)Ljava/lang/Long;
      //   2640: ifnull +36 -> 2676
      //   2643: aload_0
      //   2644: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2647: invokestatic 877	com/appodeal/ads/u:d	(Lcom/appodeal/ads/u;)Ljava/lang/Long;
      //   2650: invokevirtual 882	java/lang/Long:longValue	()J
      //   2653: ldc2_w 883
      //   2656: lcmp
      //   2657: ifeq +19 -> 2676
      //   2660: aload 8
      //   2662: ldc_w 886
      //   2665: aload_0
      //   2666: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2669: invokestatic 877	com/appodeal/ads/u:d	(Lcom/appodeal/ads/u;)Ljava/lang/Long;
      //   2672: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2675: pop
      //   2676: aload_0
      //   2677: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2680: invokestatic 889	com/appodeal/ads/u:e	(Lcom/appodeal/ads/u;)J
      //   2683: lconst_0
      //   2684: lcmp
      //   2685: ifeq +19 -> 2704
      //   2688: aload 8
      //   2690: ldc_w 891
      //   2693: aload_0
      //   2694: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2697: invokestatic 889	com/appodeal/ads/u:e	(Lcom/appodeal/ads/u;)J
      //   2700: invokevirtual 504	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   2703: pop
      //   2704: aload_0
      //   2705: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2708: invokestatic 209	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   2711: ldc 88
      //   2713: invokevirtual 103	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2716: ifeq +19 -> 2735
      //   2719: aload 8
      //   2721: ldc_w 893
      //   2724: invokestatic 499	java/lang/System:currentTimeMillis	()J
      //   2727: ldc2_w 500
      //   2730: ldiv
      //   2731: invokevirtual 504	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   2734: pop
      //   2735: aload_0
      //   2736: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2739: invokestatic 209	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   2742: ldc 107
      //   2744: invokevirtual 103	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2747: ifeq +19 -> 2766
      //   2750: aload 8
      //   2752: ldc_w 895
      //   2755: invokestatic 499	java/lang/System:currentTimeMillis	()J
      //   2758: ldc2_w 500
      //   2761: ldiv
      //   2762: invokevirtual 504	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   2765: pop
      //   2766: aload_0
      //   2767: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2770: invokestatic 898	com/appodeal/ads/u:f	(Lcom/appodeal/ads/u;)I
      //   2773: iconst_1
      //   2774: if_icmple +19 -> 2793
      //   2777: aload 8
      //   2779: ldc_w 900
      //   2782: aload_0
      //   2783: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2786: invokestatic 898	com/appodeal/ads/u:f	(Lcom/appodeal/ads/u;)I
      //   2789: invokevirtual 86	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
      //   2792: pop
      //   2793: aload 8
      //   2795: ldc_w 663
      //   2798: aload_0
      //   2799: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2802: invokestatic 902	com/appodeal/ads/u:g	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   2805: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2808: pop
      //   2809: aload 8
      //   2811: ldc_w 904
      //   2814: aload_0
      //   2815: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2818: invokestatic 906	com/appodeal/ads/u:h	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   2821: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2824: pop
      //   2825: aload_0
      //   2826: getfield 539	com/appodeal/ads/u$b:i	Z
      //   2829: ifne +13 -> 2842
      //   2832: aload_0
      //   2833: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2836: invokestatic 95	com/appodeal/ads/u:i	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   2839: ifnull +16 -> 2855
      //   2842: aload 8
      //   2844: ldc_w 908
      //   2847: aload_0
      //   2848: invokespecial 909	com/appodeal/ads/u$b:a	()Lorg/json/JSONObject;
      //   2851: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2854: pop
      //   2855: aload_0
      //   2856: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2859: invokestatic 913	com/appodeal/ads/u:j	(Lcom/appodeal/ads/u;)Lcom/appodeal/ads/f/c;
      //   2862: ifnull -1601 -> 1261
      //   2865: aload 8
      //   2867: ldc_w 915
      //   2870: aload_0
      //   2871: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2874: invokestatic 913	com/appodeal/ads/u:j	(Lcom/appodeal/ads/u;)Lcom/appodeal/ads/f/c;
      //   2877: invokevirtual 919	com/appodeal/ads/f/c:a	()I
      //   2880: invokevirtual 86	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
      //   2883: pop
      //   2884: goto -1623 -> 1261
      //   2887: astore_1
      //   2888: aload_1
      //   2889: invokestatic 136	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2892: goto -1545 -> 1347
      //   2895: astore_1
      //   2896: aload_1
      //   2897: invokestatic 136	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2900: goto -1531 -> 1369
      //   2903: aload 8
      //   2905: ldc_w 921
      //   2908: aload_1
      //   2909: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2912: pop
      //   2913: goto -1346 -> 1567
      //   2916: aload_0
      //   2917: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2920: invokestatic 209	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   2923: ldc 82
      //   2925: invokevirtual 103	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2928: ifeq +78 -> 3006
      //   2931: aload_0
      //   2932: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2935: invokestatic 659	com/appodeal/ads/u:k	(Lcom/appodeal/ads/u;)Lcom/appodeal/ads/d/h;
      //   2938: invokevirtual 923	com/appodeal/ads/d/h:f	()Z
      //   2941: ifeq -1295 -> 1646
      //   2944: aload_0
      //   2945: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2948: invokestatic 659	com/appodeal/ads/u:k	(Lcom/appodeal/ads/u;)Lcom/appodeal/ads/d/h;
      //   2951: invokevirtual 925	com/appodeal/ads/d/h:d	()Z
      //   2954: ifne +11 -> 2965
      //   2957: ldc_w 927
      //   2960: invokestatic 260	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   2963: aconst_null
      //   2964: areturn
      //   2965: aload 8
      //   2967: ldc_w 929
      //   2970: aload_0
      //   2971: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2974: invokestatic 659	com/appodeal/ads/u:k	(Lcom/appodeal/ads/u;)Lcom/appodeal/ads/d/h;
      //   2977: invokevirtual 931	com/appodeal/ads/d/h:c	()Lorg/json/JSONObject;
      //   2980: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2983: pop
      //   2984: aload 8
      //   2986: ldc_w 933
      //   2989: aload_0
      //   2990: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2993: invokestatic 659	com/appodeal/ads/u:k	(Lcom/appodeal/ads/u;)Lcom/appodeal/ads/d/h;
      //   2996: invokevirtual 935	com/appodeal/ads/d/h:e	()Ljava/lang/String;
      //   2999: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3002: pop
      //   3003: goto -1357 -> 1646
      //   3006: aload_0
      //   3007: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   3010: invokestatic 209	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   3013: ldc 88
      //   3015: invokevirtual 103	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3018: ifne +18 -> 3036
      //   3021: aload_0
      //   3022: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   3025: invokestatic 209	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   3028: ldc 107
      //   3030: invokevirtual 103	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3033: ifeq -1387 -> 1646
      //   3036: aload_0
      //   3037: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   3040: invokestatic 659	com/appodeal/ads/u:k	(Lcom/appodeal/ads/u;)Lcom/appodeal/ads/d/h;
      //   3043: invokevirtual 923	com/appodeal/ads/d/h:f	()Z
      //   3046: ifeq -1400 -> 1646
      //   3049: aload 8
      //   3051: ldc_w 663
      //   3054: aload_0
      //   3055: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   3058: invokestatic 659	com/appodeal/ads/u:k	(Lcom/appodeal/ads/u;)Lcom/appodeal/ads/d/h;
      //   3061: invokevirtual 935	com/appodeal/ads/d/h:e	()Ljava/lang/String;
      //   3064: invokevirtual 271	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3067: pop
      //   3068: goto -1422 -> 1646
      //   3071: new 669	java/net/URL
      //   3074: dup
      //   3075: ldc_w 671
      //   3078: iconst_4
      //   3079: anewarray 115	java/lang/Object
      //   3082: dup
      //   3083: iconst_0
      //   3084: invokestatic 675	com/appodeal/ads/utils/g:c	()I
      //   3087: invokestatic 678	com/appodeal/ads/utils/g:a	(I)Ljava/lang/String;
      //   3090: aastore
      //   3091: dup
      //   3092: iconst_1
      //   3093: invokestatic 680	com/appodeal/ads/utils/g:b	()Ljava/lang/String;
      //   3096: aastore
      //   3097: dup
      //   3098: iconst_2
      //   3099: invokestatic 675	com/appodeal/ads/utils/g:c	()I
      //   3102: invokestatic 686	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   3105: aastore
      //   3106: dup
      //   3107: iconst_3
      //   3108: aload_0
      //   3109: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   3112: invokestatic 209	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   3115: aastore
      //   3116: invokestatic 119	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   3119: invokespecial 689	java/net/URL:<init>	(Ljava/lang/String;)V
      //   3122: astore_1
      //   3123: goto -1345 -> 1778
      //   3126: aload 7
      //   3128: sipush 20000
      //   3131: invokevirtual 749	java/net/URLConnection:setConnectTimeout	(I)V
      //   3134: aload 7
      //   3136: sipush 20000
      //   3139: invokevirtual 752	java/net/URLConnection:setReadTimeout	(I)V
      //   3142: goto -1215 -> 1927
      //   3145: astore 6
      //   3147: aload 7
      //   3149: astore_1
      //   3150: aload 6
      //   3152: invokestatic 136	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   3155: aload 6
      //   3157: invokevirtual 938	java/io/IOException:getMessage	()Ljava/lang/String;
      //   3160: ldc_w 940
      //   3163: invokevirtual 103	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3166: ifne +21 -> 3187
      //   3169: aload 6
      //   3171: invokevirtual 938	java/io/IOException:getMessage	()Ljava/lang/String;
      //   3174: ldc_w 942
      //   3177: invokevirtual 103	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3180: istore 5
      //   3182: iload 5
      //   3184: ifeq +100 -> 3284
      //   3187: aload_1
      //   3188: ifnull +17 -> 3205
      //   3191: aload_1
      //   3192: instanceof 731
      //   3195: ifeq +72 -> 3267
      //   3198: aload_1
      //   3199: checkcast 731	javax/net/ssl/HttpsURLConnection
      //   3202: invokevirtual 817	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   3205: aconst_null
      //   3206: areturn
      //   3207: astore 6
      //   3209: aload_1
      //   3210: invokevirtual 785	java/util/zip/GZIPOutputStream:close	()V
      //   3213: aload 6
      //   3215: athrow
      //   3216: astore 6
      //   3218: aload 7
      //   3220: astore_1
      //   3221: aload_1
      //   3222: ifnull +17 -> 3239
      //   3225: aload_1
      //   3226: instanceof 731
      //   3229: ifeq +97 -> 3326
      //   3232: aload_1
      //   3233: checkcast 731	javax/net/ssl/HttpsURLConnection
      //   3236: invokevirtual 817	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   3239: aload 6
      //   3241: athrow
      //   3242: aload 6
      //   3244: astore_1
      //   3245: aload 7
      //   3247: instanceof 944
      //   3250: ifeq -1149 -> 2101
      //   3253: aload 7
      //   3255: checkcast 944	java/net/HttpURLConnection
      //   3258: invokevirtual 945	java/net/HttpURLConnection:disconnect	()V
      //   3261: aload 6
      //   3263: astore_1
      //   3264: goto -1163 -> 2101
      //   3267: aload_1
      //   3268: instanceof 944
      //   3271: ifeq -66 -> 3205
      //   3274: aload_1
      //   3275: checkcast 944	java/net/HttpURLConnection
      //   3278: invokevirtual 945	java/net/HttpURLConnection:disconnect	()V
      //   3281: goto -76 -> 3205
      //   3284: aload_1
      //   3285: ifnull +124 -> 3409
      //   3288: aload_1
      //   3289: instanceof 731
      //   3292: ifeq +15 -> 3307
      //   3295: aload_1
      //   3296: checkcast 731	javax/net/ssl/HttpsURLConnection
      //   3299: invokevirtual 817	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   3302: aconst_null
      //   3303: astore_1
      //   3304: goto -1203 -> 2101
      //   3307: aload_1
      //   3308: instanceof 944
      //   3311: ifeq +98 -> 3409
      //   3314: aload_1
      //   3315: checkcast 944	java/net/HttpURLConnection
      //   3318: invokevirtual 945	java/net/HttpURLConnection:disconnect	()V
      //   3321: aconst_null
      //   3322: astore_1
      //   3323: goto -1222 -> 2101
      //   3326: aload_1
      //   3327: instanceof 944
      //   3330: ifeq -91 -> 3239
      //   3333: aload_1
      //   3334: checkcast 944	java/net/HttpURLConnection
      //   3337: invokevirtual 945	java/net/HttpURLConnection:disconnect	()V
      //   3340: goto -101 -> 3239
      //   3343: iload 4
      //   3345: ifeq +87 -> 3432
      //   3348: aload 9
      //   3350: invokeinterface 246 1 0
      //   3355: astore 6
      //   3357: aload 6
      //   3359: aload_0
      //   3360: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   3363: invokestatic 209	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   3366: aload_1
      //   3367: invokeinterface 252 3 0
      //   3372: pop
      //   3373: aload 6
      //   3375: invokeinterface 255 1 0
      //   3380: goto +52 -> 3432
      //   3383: astore_1
      //   3384: aload_1
      //   3385: invokestatic 136	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   3388: aconst_null
      //   3389: areturn
      //   3390: astore 6
      //   3392: aconst_null
      //   3393: astore_1
      //   3394: goto -173 -> 3221
      //   3397: astore 6
      //   3399: goto -178 -> 3221
      //   3402: astore 6
      //   3404: aconst_null
      //   3405: astore_1
      //   3406: goto -256 -> 3150
      //   3409: aconst_null
      //   3410: astore_1
      //   3411: goto -1310 -> 2101
      //   3414: goto -3106 -> 308
      //   3417: ldc_w 947
      //   3420: astore_1
      //   3421: goto -2623 -> 798
      //   3424: iconst_0
      //   3425: istore 4
      //   3427: goto -1702 -> 1725
      //   3430: aconst_null
      //   3431: areturn
      //   3432: goto -1274 -> 2158
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	3435	0	this	b
      //   0	3435	1	paramVarArgs	Void[]
      //   374	19	2	f1	float
      //   385	16	3	f2	float
      //   1723	1703	4	j	int
      //   93	3090	5	bool	boolean
      //   70	2029	6	localObject1	Object
      //   2188	3	6	localException1	Exception
      //   2287	281	6	localIterator	java.util.Iterator
      //   3145	25	6	localIOException1	java.io.IOException
      //   3207	7	6	localObject2	Object
      //   3216	46	6	localObject3	Object
      //   3355	19	6	localEditor	android.content.SharedPreferences.Editor
      //   3390	1	6	localObject4	Object
      //   3397	1	6	localObject5	Object
      //   3402	1	6	localIOException2	java.io.IOException
      //   74	3180	7	localObject6	Object
      //   56	2994	8	localObject7	Object
      //   13	3336	9	localSharedPreferences	android.content.SharedPreferences
      //   25	1529	10	localObject8	Object
      //   37	927	11	localObject9	Object
      //   2222	3	11	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
      //   2232	3	11	localException2	Exception
      //   161	95	12	localObject10	Object
      // Exception table:
      //   from	to	target	type
      //   317	346	1256	org/json/JSONException
      //   346	392	1256	org/json/JSONException
      //   408	418	1256	org/json/JSONException
      //   418	436	1256	org/json/JSONException
      //   436	450	1256	org/json/JSONException
      //   450	461	1256	org/json/JSONException
      //   461	477	1256	org/json/JSONException
      //   477	495	1256	org/json/JSONException
      //   495	512	1256	org/json/JSONException
      //   512	528	1256	org/json/JSONException
      //   528	587	1256	org/json/JSONException
      //   587	625	1256	org/json/JSONException
      //   625	653	1256	org/json/JSONException
      //   653	782	1256	org/json/JSONException
      //   782	794	1256	org/json/JSONException
      //   798	808	1256	org/json/JSONException
      //   808	817	1256	org/json/JSONException
      //   829	839	1256	org/json/JSONException
      //   839	1153	1256	org/json/JSONException
      //   1157	1174	1256	org/json/JSONException
      //   1174	1213	1256	org/json/JSONException
      //   1213	1253	1256	org/json/JSONException
      //   2224	2229	1256	org/json/JSONException
      //   2234	2239	1256	org/json/JSONException
      //   2242	2254	1256	org/json/JSONException
      //   2258	2262	1256	org/json/JSONException
      //   2265	2289	1256	org/json/JSONException
      //   2289	2329	1256	org/json/JSONException
      //   2332	2356	1256	org/json/JSONException
      //   2356	2396	1256	org/json/JSONException
      //   2399	2423	1256	org/json/JSONException
      //   2423	2463	1256	org/json/JSONException
      //   2466	2490	1256	org/json/JSONException
      //   2490	2530	1256	org/json/JSONException
      //   2533	2557	1256	org/json/JSONException
      //   2557	2579	1256	org/json/JSONException
      //   2584	2594	1256	org/json/JSONException
      //   2597	2607	1256	org/json/JSONException
      //   2607	2633	1256	org/json/JSONException
      //   2633	2676	1256	org/json/JSONException
      //   2676	2704	1256	org/json/JSONException
      //   2704	2735	1256	org/json/JSONException
      //   2735	2766	1256	org/json/JSONException
      //   2766	2793	1256	org/json/JSONException
      //   2793	2842	1256	org/json/JSONException
      //   2842	2855	1256	org/json/JSONException
      //   2855	2884	1256	org/json/JSONException
      //   1470	1503	1562	java/lang/Exception
      //   1503	1559	1562	java/lang/Exception
      //   2903	2913	1562	java/lang/Exception
      //   2171	2186	2188	java/lang/Exception
      //   0	39	2197	java/lang/Exception
      //   46	69	2197	java/lang/Exception
      //   81	95	2197	java/lang/Exception
      //   297	308	2197	java/lang/Exception
      //   308	317	2197	java/lang/Exception
      //   317	346	2197	java/lang/Exception
      //   346	392	2197	java/lang/Exception
      //   408	418	2197	java/lang/Exception
      //   418	436	2197	java/lang/Exception
      //   436	450	2197	java/lang/Exception
      //   450	461	2197	java/lang/Exception
      //   461	477	2197	java/lang/Exception
      //   477	495	2197	java/lang/Exception
      //   495	512	2197	java/lang/Exception
      //   512	528	2197	java/lang/Exception
      //   528	587	2197	java/lang/Exception
      //   587	625	2197	java/lang/Exception
      //   653	782	2197	java/lang/Exception
      //   782	794	2197	java/lang/Exception
      //   798	808	2197	java/lang/Exception
      //   839	1153	2197	java/lang/Exception
      //   1157	1174	2197	java/lang/Exception
      //   1174	1213	2197	java/lang/Exception
      //   1213	1253	2197	java/lang/Exception
      //   1257	1261	2197	java/lang/Exception
      //   1261	1324	2197	java/lang/Exception
      //   1324	1328	2197	java/lang/Exception
      //   1369	1411	2197	java/lang/Exception
      //   1411	1470	2197	java/lang/Exception
      //   1563	1567	2197	java/lang/Exception
      //   1567	1598	2197	java/lang/Exception
      //   1598	1646	2197	java/lang/Exception
      //   1646	1722	2197	java/lang/Exception
      //   1730	1778	2197	java/lang/Exception
      //   1778	1794	2197	java/lang/Exception
      //   2082	2098	2197	java/lang/Exception
      //   2110	2158	2197	java/lang/Exception
      //   2158	2171	2197	java/lang/Exception
      //   2190	2195	2197	java/lang/Exception
      //   2212	2216	2197	java/lang/Exception
      //   2224	2229	2197	java/lang/Exception
      //   2234	2239	2197	java/lang/Exception
      //   2242	2254	2197	java/lang/Exception
      //   2258	2262	2197	java/lang/Exception
      //   2265	2289	2197	java/lang/Exception
      //   2289	2329	2197	java/lang/Exception
      //   2332	2356	2197	java/lang/Exception
      //   2356	2396	2197	java/lang/Exception
      //   2399	2423	2197	java/lang/Exception
      //   2423	2463	2197	java/lang/Exception
      //   2466	2490	2197	java/lang/Exception
      //   2490	2530	2197	java/lang/Exception
      //   2533	2557	2197	java/lang/Exception
      //   2557	2579	2197	java/lang/Exception
      //   2584	2594	2197	java/lang/Exception
      //   2597	2607	2197	java/lang/Exception
      //   2607	2633	2197	java/lang/Exception
      //   2633	2676	2197	java/lang/Exception
      //   2676	2704	2197	java/lang/Exception
      //   2704	2735	2197	java/lang/Exception
      //   2735	2766	2197	java/lang/Exception
      //   2766	2793	2197	java/lang/Exception
      //   2793	2842	2197	java/lang/Exception
      //   2842	2855	2197	java/lang/Exception
      //   2855	2884	2197	java/lang/Exception
      //   2888	2892	2197	java/lang/Exception
      //   2896	2900	2197	java/lang/Exception
      //   2916	2963	2197	java/lang/Exception
      //   2965	3003	2197	java/lang/Exception
      //   3006	3036	2197	java/lang/Exception
      //   3036	3068	2197	java/lang/Exception
      //   3071	3123	2197	java/lang/Exception
      //   3191	3205	2197	java/lang/Exception
      //   3225	3239	2197	java/lang/Exception
      //   3239	3242	2197	java/lang/Exception
      //   3245	3261	2197	java/lang/Exception
      //   3267	3281	2197	java/lang/Exception
      //   3288	3302	2197	java/lang/Exception
      //   3307	3321	2197	java/lang/Exception
      //   3326	3340	2197	java/lang/Exception
      //   3348	3380	2197	java/lang/Exception
      //   3384	3388	2197	java/lang/Exception
      //   114	120	2211	java/lang/Exception
      //   127	144	2211	java/lang/Exception
      //   151	163	2211	java/lang/Exception
      //   170	177	2211	java/lang/Exception
      //   184	192	2211	java/lang/Exception
      //   202	211	2211	java/lang/Exception
      //   218	230	2211	java/lang/Exception
      //   237	248	2211	java/lang/Exception
      //   255	262	2211	java/lang/Exception
      //   269	287	2211	java/lang/Exception
      //   587	625	2222	android/content/pm/PackageManager$NameNotFoundException
      //   625	653	2232	java/lang/Exception
      //   808	817	2257	java/lang/Exception
      //   829	839	2257	java/lang/Exception
      //   1328	1347	2887	java/lang/Exception
      //   1347	1369	2895	java/lang/Exception
      //   1800	1889	3145	java/io/IOException
      //   1894	1927	3145	java/io/IOException
      //   1927	1979	3145	java/io/IOException
      //   1994	2027	3145	java/io/IOException
      //   2032	2066	3145	java/io/IOException
      //   3126	3142	3145	java/io/IOException
      //   3209	3216	3145	java/io/IOException
      //   1979	1994	3207	finally
      //   1800	1889	3216	finally
      //   1894	1927	3216	finally
      //   1927	1979	3216	finally
      //   1994	2027	3216	finally
      //   2032	2066	3216	finally
      //   3126	3142	3216	finally
      //   3209	3216	3216	finally
      //   2158	2171	3383	org/json/JSONException
      //   1794	1800	3390	finally
      //   3150	3182	3397	finally
      //   1794	1800	3402	java/io/IOException
    }
    
    protected void a(JSONObject paramJSONObject)
    {
      try
      {
        if (u.l(u.this) != null)
        {
          if (paramJSONObject == null)
          {
            u.l(u.this).a(u.m(u.this));
            return;
          }
          u.l(u.this).a(paramJSONObject, u.m(u.this), u.a(u.this));
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
      if ((u.a(u.this).equals("banner")) || (u.a(u.this).equals("debug")))
      {
        bool1 = true;
        this.b = bool1;
        if ((!u.a(u.this).equals("banner_320")) && (!u.a(u.this).equals("debug_banner_320"))) {
          break label390;
        }
        bool1 = true;
        label73:
        this.c = bool1;
        if ((!u.a(u.this).equals("banner_mrec")) && (!u.a(u.this).equals("debug_mrec"))) {
          break label395;
        }
        bool1 = true;
        label111:
        this.d = bool1;
        if ((!u.a(u.this).equals("video")) && (!u.a(u.this).equals("debug_video"))) {
          break label400;
        }
        bool1 = true;
        label149:
        this.e = bool1;
        if ((!u.a(u.this).equals("rewarded_video")) && (!u.a(u.this).equals("debug_rewarded_video"))) {
          break label405;
        }
        bool1 = true;
        label187:
        this.f = bool1;
        if ((!u.a(u.this).equals("native")) && (!u.a(u.this).equals("debug_native"))) {
          break label410;
        }
        bool1 = true;
        label225:
        this.g = bool1;
        if ((!u.a(u.this).equals("debug")) && (!u.a(u.this).equals("debug_banner_320")) && (!u.a(u.this).equals("debug_video")) && (!u.a(u.this).equals("debug_rewarded_video")) && (!u.a(u.this).equals("debug_mrec")) && (!u.a(u.this).equals("debug_native"))) {
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
