package com.appodeal.ads;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.appodeal.ads.d.h;
import com.appodeal.ads.f.c;
import com.appodeal.ads.f.f;
import com.appodeal.ads.utils.q;
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
  private final double n;
  
  public u(Context paramContext, c paramC, a paramA, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, h paramH)
  {
    this(paramContext, paramC, paramA, paramInt, paramString1, paramString2, paramString3, paramString4, paramH, 0L, null, null, 1, -1.0D);
  }
  
  public u(Context paramContext, c paramC, a paramA, int paramInt, String paramString1, String paramString2, String paramString3, String paramString4, h paramH, long paramLong, String paramString5, Long paramLong1)
  {
    this(paramContext, paramC, paramA, paramInt, paramString1, paramString2, paramString3, paramString4, paramH, paramLong, paramString5, paramLong1, 1, -1.0D);
  }
  
  public u(Context paramContext, c paramC, a paramA, int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, h paramH, long paramLong, String paramString5, Long paramLong1, int paramInt2)
  {
    this(paramContext, paramC, paramA, paramInt1, paramString1, paramString2, paramString3, paramString4, paramH, paramLong, paramString5, paramLong1, paramInt2, -1.0D);
  }
  
  u(Context paramContext, c paramC, a paramA, int paramInt1, String paramString1, String paramString2, String paramString3, String paramString4, h paramH, long paramLong, String paramString5, Long paramLong1, int paramInt2, double paramDouble)
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
    this.n = paramDouble;
    if ((AppodealSettings.a) && ((this.a == null) || ((this.a instanceof com.appodeal.ads.utils.w))) && ((this.d.equals("stats")) || (this.d.equals("show")) || (this.d.equals("click")) || (this.d.equals("finish")) || (this.d.equals("install")))) {
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
    this(paramContext, paramC, paramA, paramInt, paramString1, paramString2, paramString3, paramString4, paramH, 0L, null, paramLong, 1, -1.0D);
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
        if ((this.e) || (this.f) || ((u.j(u.this) != null) && ((u.j(u.this).equals("video")) || (u.j(u.this).equals("rewarded_video"))))) {
          localJSONObject.put("finish", i9 + i10);
        }
        if ((this.b) || ((u.j(u.this) != null) && (u.j(u.this).equals("banner"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "show" }), o.q);
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner", "click" }), o.r);
        }
        if ((this.e) || ((u.j(u.this) != null) && (u.j(u.this).equals("video"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "video", "show" }), ai.n);
          localJSONObject.put(String.format("%s_%s", new Object[] { "video", "click" }), ai.p);
          localJSONObject.put(String.format("%s_%s", new Object[] { "video", "finish" }), ai.o);
        }
        if ((this.f) || ((u.j(u.this) != null) && (u.j(u.this).equals("rewarded_video"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "show" }), al.n);
          localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "click" }), al.p);
          localJSONObject.put(String.format("%s_%s", new Object[] { "rewarded_video", "finish" }), al.o);
        }
        if ((this.c) || ((u.j(u.this) != null) && (u.j(u.this).equals("banner_320"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "show" }), g.y);
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner_320", "click" }), g.z);
        }
        if ((this.d) || ((u.j(u.this) != null) && (u.j(u.this).equals("banner_mrec"))))
        {
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "show" }), w.u);
          localJSONObject.put(String.format("%s_%s", new Object[] { "banner_mrec", "click" }), w.v);
        }
        if ((this.g) || ((u.j(u.this) != null) && (u.j(u.this).equals("native"))))
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
      //   4: invokestatic 157	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   7: ldc -70
      //   9: iconst_0
      //   10: invokevirtual 192	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   13: astore 9
      //   15: aload_0
      //   16: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   19: invokestatic 157	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   22: invokevirtual 196	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
      //   25: astore 10
      //   27: aload 9
      //   29: ldc -58
      //   31: aconst_null
      //   32: invokeinterface 204 3 0
      //   37: astore 11
      //   39: aload 11
      //   41: ifnonnull +5 -> 46
      //   44: aconst_null
      //   45: areturn
      //   46: aload 9
      //   48: ldc -50
      //   50: aconst_null
      //   51: invokeinterface 204 3 0
      //   56: astore 8
      //   58: aload 9
      //   60: ldc -48
      //   62: aconst_null
      //   63: invokeinterface 204 3 0
      //   68: astore_1
      //   69: aload_1
      //   70: astore 6
      //   72: aload 8
      //   74: astore 7
      //   76: aload 8
      //   78: ifnonnull +2138 -> 2216
      //   81: aload_0
      //   82: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   85: invokestatic 210	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   88: ldc -44
      //   90: invokevirtual 104	java/lang/String:equals	(Ljava/lang/Object;)Z
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
      //   114: ldc -42
      //   116: invokestatic 220	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
      //   119: pop
      //   120: aload_1
      //   121: astore 6
      //   123: aload 8
      //   125: astore 7
      //   127: ldc -34
      //   129: ldc -32
      //   131: iconst_1
      //   132: anewarray 216	java/lang/Class
      //   135: dup
      //   136: iconst_0
      //   137: ldc -68
      //   139: aastore
      //   140: invokevirtual 228	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      //   143: pop
      //   144: aload_1
      //   145: astore 6
      //   147: aload 8
      //   149: astore 7
      //   151: aload_0
      //   152: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   155: invokestatic 157	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   158: invokestatic 231	com/google/android/gms/ads/identifier/AdvertisingIdClient:getAdvertisingIdInfo	(Landroid/content/Context;)Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;
      //   161: astore 12
      //   163: aload_1
      //   164: astore 6
      //   166: aload 8
      //   168: astore 7
      //   170: aload 12
      //   172: invokevirtual 237	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:getId	()Ljava/lang/String;
      //   175: astore 8
      //   177: aload_1
      //   178: astore 6
      //   180: aload 8
      //   182: astore 7
      //   184: aload 12
      //   186: invokevirtual 241	com/google/android/gms/ads/identifier/AdvertisingIdClient$Info:isLimitAdTrackingEnabled	()Z
      //   189: ifeq +2015 -> 2204
      //   192: ldc -13
      //   194: astore_1
      //   195: aload_1
      //   196: astore 6
      //   198: aload 8
      //   200: astore 7
      //   202: aload 9
      //   204: invokeinterface 247 1 0
      //   209: astore 12
      //   211: aload_1
      //   212: astore 6
      //   214: aload 8
      //   216: astore 7
      //   218: aload 12
      //   220: ldc -50
      //   222: aload 8
      //   224: invokeinterface 253 3 0
      //   229: pop
      //   230: aload_1
      //   231: astore 6
      //   233: aload 8
      //   235: astore 7
      //   237: aload 12
      //   239: ldc -48
      //   241: aload_1
      //   242: invokeinterface 253 3 0
      //   247: pop
      //   248: aload_1
      //   249: astore 6
      //   251: aload 8
      //   253: astore 7
      //   255: aload 12
      //   257: invokeinterface 256 1 0
      //   262: aload_1
      //   263: astore 6
      //   265: aload 8
      //   267: astore 7
      //   269: ldc_w 258
      //   272: iconst_1
      //   273: anewarray 116	java/lang/Object
      //   276: dup
      //   277: iconst_0
      //   278: aload 8
      //   280: aastore
      //   281: invokestatic 120	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   284: invokestatic 261	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   287: aload_1
      //   288: astore 6
      //   290: aload 8
      //   292: astore_1
      //   293: aload_1
      //   294: ifnonnull +3148 -> 3442
      //   297: aload_0
      //   298: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   301: invokestatic 157	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   304: invokestatic 267	com/appodeal/ads/ao:l	(Landroid/content/Context;)Ljava/lang/String;
      //   307: astore_1
      //   308: new 34	org/json/JSONObject
      //   311: dup
      //   312: invokespecial 35	org/json/JSONObject:<init>	()V
      //   315: astore 8
      //   317: aload 8
      //   319: ldc_w 269
      //   322: aload 11
      //   324: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   327: pop
      //   328: aload_0
      //   329: getfield 110	com/appodeal/ads/u$b:b	Z
      //   332: ifeq +14 -> 346
      //   335: aload 8
      //   337: ldc_w 274
      //   340: ldc 112
      //   342: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   345: pop
      //   346: aload_0
      //   347: getfield 122	com/appodeal/ads/u$b:c	Z
      //   350: ifeq +68 -> 418
      //   353: aload 8
      //   355: ldc_w 274
      //   358: ldc 124
      //   360: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   363: pop
      //   364: aload_0
      //   365: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   368: invokestatic 157	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   371: invokestatic 277	com/appodeal/ads/ao:g	(Landroid/content/Context;)F
      //   374: fstore_2
      //   375: aload_0
      //   376: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   379: invokestatic 157	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   382: invokestatic 279	com/appodeal/ads/ao:h	(Landroid/content/Context;)F
      //   385: fstore_3
      //   386: getstatic 282	com/appodeal/ads/g:t	Z
      //   389: ifeq +29 -> 418
      //   392: fload_2
      //   393: ldc_w 283
      //   396: fcmpl
      //   397: iflt +21 -> 418
      //   400: fload_3
      //   401: ldc_w 284
      //   404: fcmpl
      //   405: ifle +13 -> 418
      //   408: aload 8
      //   410: ldc_w 286
      //   413: iconst_1
      //   414: invokevirtual 289	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   417: pop
      //   418: aload_0
      //   419: getfield 126	com/appodeal/ads/u$b:d	Z
      //   422: ifeq +14 -> 436
      //   425: aload 8
      //   427: ldc_w 274
      //   430: ldc -128
      //   432: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   435: pop
      //   436: aload_0
      //   437: getfield 90	com/appodeal/ads/u$b:e	Z
      //   440: ifne +10 -> 450
      //   443: aload_0
      //   444: getfield 92	com/appodeal/ads/u$b:f	Z
      //   447: ifeq +14 -> 461
      //   450: aload 8
      //   452: ldc_w 274
      //   455: ldc 98
      //   457: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   460: pop
      //   461: aload_0
      //   462: getfield 92	com/appodeal/ads/u$b:f	Z
      //   465: ifeq +12 -> 477
      //   468: aload 8
      //   470: ldc 106
      //   472: iconst_1
      //   473: invokevirtual 289	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   476: pop
      //   477: aload_0
      //   478: getfield 130	com/appodeal/ads/u$b:g	Z
      //   481: ifeq +14 -> 495
      //   484: aload 8
      //   486: ldc_w 274
      //   489: ldc -124
      //   491: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   494: pop
      //   495: aload_0
      //   496: getfield 291	com/appodeal/ads/u$b:h	Z
      //   499: ifeq +13 -> 512
      //   502: aload 8
      //   504: ldc_w 293
      //   507: iconst_1
      //   508: invokevirtual 289	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   511: pop
      //   512: getstatic 297	com/appodeal/ads/AppodealSettings:a	Z
      //   515: ifeq +13 -> 528
      //   518: aload 8
      //   520: ldc_w 299
      //   523: iconst_1
      //   524: invokevirtual 289	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   527: pop
      //   528: aload 8
      //   530: ldc_w 301
      //   533: getstatic 307	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
      //   536: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   539: pop
      //   540: aload 8
      //   542: ldc_w 309
      //   545: getstatic 312	android/os/Build$VERSION:SDK_INT	I
      //   548: invokevirtual 86	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
      //   551: pop
      //   552: aload 8
      //   554: ldc_w 314
      //   557: ldc_w 316
      //   560: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   563: pop
      //   564: aload_0
      //   565: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   568: invokestatic 157	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   571: invokevirtual 319	android/content/Context:getPackageName	()Ljava/lang/String;
      //   574: astore 7
      //   576: aload 8
      //   578: ldc_w 321
      //   581: aload 7
      //   583: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   586: pop
      //   587: aload 10
      //   589: aload 7
      //   591: iconst_0
      //   592: invokevirtual 327	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
      //   595: astore 11
      //   597: aload 8
      //   599: ldc_w 329
      //   602: aload 11
      //   604: getfield 334	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
      //   607: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   610: pop
      //   611: aload 8
      //   613: ldc_w 336
      //   616: aload 11
      //   618: getfield 339	android/content/pm/PackageInfo:versionCode	I
      //   621: invokevirtual 86	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
      //   624: pop
      //   625: aload 8
      //   627: ldc_w 341
      //   630: aload 10
      //   632: aload 7
      //   634: sipush 128
      //   637: invokevirtual 345	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
      //   640: getfield 351	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
      //   643: ldc_w 353
      //   646: invokevirtual 358	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
      //   649: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   652: pop
      //   653: aload 8
      //   655: ldc_w 360
      //   658: aload_1
      //   659: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   662: pop
      //   663: aload 8
      //   665: ldc_w 362
      //   668: aload 6
      //   670: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   673: pop
      //   674: aload_0
      //   675: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   678: invokestatic 157	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   681: invokestatic 365	com/appodeal/ads/ao:b	(Landroid/content/Context;)Lcom/appodeal/ads/ao$a;
      //   684: astore 11
      //   686: aload 8
      //   688: ldc_w 367
      //   691: aload 11
      //   693: getfield 371	com/appodeal/ads/ao$a:a	Ljava/lang/String;
      //   696: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   699: pop
      //   700: aload 8
      //   702: ldc_w 373
      //   705: aload_0
      //   706: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   709: invokestatic 157	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   712: invokestatic 376	com/appodeal/ads/ao:k	(Landroid/content/Context;)F
      //   715: f2d
      //   716: invokevirtual 379	org/json/JSONObject:put	(Ljava/lang/String;D)Lorg/json/JSONObject;
      //   719: pop
      //   720: aload_0
      //   721: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   724: invokestatic 157	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   727: invokestatic 382	com/appodeal/ads/ao:f	(Landroid/content/Context;)Landroid/util/Pair;
      //   730: astore_1
      //   731: aload 8
      //   733: ldc_w 384
      //   736: aload_1
      //   737: getfield 390	android/util/Pair:first	Ljava/lang/Object;
      //   740: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   743: pop
      //   744: aload 8
      //   746: ldc_w 392
      //   749: aload_1
      //   750: getfield 395	android/util/Pair:second	Ljava/lang/Object;
      //   753: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   756: pop
      //   757: aload_0
      //   758: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   761: invokestatic 157	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   764: invokestatic 398	com/appodeal/ads/ao:n	(Landroid/content/Context;)Z
      //   767: ifeq +1475 -> 2242
      //   770: aload 8
      //   772: ldc_w 400
      //   775: ldc_w 402
      //   778: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   781: pop
      //   782: getstatic 407	android/os/Build:MANUFACTURER	Ljava/lang/String;
      //   785: ldc_w 409
      //   788: invokevirtual 104	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   791: ifeq +2654 -> 3445
      //   794: ldc_w 411
      //   797: astore_1
      //   798: aload 8
      //   800: ldc_w 413
      //   803: aload_1
      //   804: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   807: pop
      //   808: aload 10
      //   810: aload 7
      //   812: invokevirtual 416	android/content/pm/PackageManager:getInstallerPackageName	(Ljava/lang/String;)Ljava/lang/String;
      //   815: astore 6
      //   817: aload 6
      //   819: astore_1
      //   820: aload 6
      //   822: ifnonnull +7 -> 829
      //   825: ldc_w 418
      //   828: astore_1
      //   829: aload 8
      //   831: ldc_w 420
      //   834: aload_1
      //   835: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   838: pop
      //   839: aload 8
      //   841: ldc_w 422
      //   844: getstatic 407	android/os/Build:MANUFACTURER	Ljava/lang/String;
      //   847: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   850: pop
      //   851: aload 8
      //   853: ldc_w 424
      //   856: ldc_w 426
      //   859: iconst_2
      //   860: anewarray 116	java/lang/Object
      //   863: dup
      //   864: iconst_0
      //   865: getstatic 407	android/os/Build:MANUFACTURER	Ljava/lang/String;
      //   868: aastore
      //   869: dup
      //   870: iconst_1
      //   871: getstatic 429	android/os/Build:MODEL	Ljava/lang/String;
      //   874: aastore
      //   875: invokestatic 120	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   878: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   881: pop
      //   882: aload_0
      //   883: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   886: invokestatic 157	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   889: invokestatic 431	com/appodeal/ads/ao:d	(Landroid/content/Context;)Landroid/util/Pair;
      //   892: astore_1
      //   893: aload 8
      //   895: ldc_w 433
      //   898: aload_1
      //   899: getfield 390	android/util/Pair:first	Ljava/lang/Object;
      //   902: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   905: pop
      //   906: aload 8
      //   908: ldc_w 435
      //   911: aload_1
      //   912: getfield 395	android/util/Pair:second	Ljava/lang/Object;
      //   915: checkcast 386	android/util/Pair
      //   918: getfield 390	android/util/Pair:first	Ljava/lang/Object;
      //   921: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   924: pop
      //   925: aload 8
      //   927: ldc_w 437
      //   930: aload_1
      //   931: getfield 395	android/util/Pair:second	Ljava/lang/Object;
      //   934: checkcast 386	android/util/Pair
      //   937: getfield 395	android/util/Pair:second	Ljava/lang/Object;
      //   940: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   943: pop
      //   944: aload 8
      //   946: ldc_w 439
      //   949: aload 11
      //   951: getfield 441	com/appodeal/ads/ao$a:b	Ljava/lang/String;
      //   954: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   957: pop
      //   958: aload 8
      //   960: ldc_w 443
      //   963: aload 11
      //   965: getfield 444	com/appodeal/ads/ao$a:c	Z
      //   968: invokevirtual 289	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   971: pop
      //   972: aload 8
      //   974: ldc_w 446
      //   977: aload_0
      //   978: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   981: invokestatic 157	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   984: invokestatic 448	com/appodeal/ads/ao:c	(Landroid/content/Context;)Ljava/lang/String;
      //   987: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   990: pop
      //   991: aload 8
      //   993: ldc_w 450
      //   996: invokestatic 456	java/util/Locale:getDefault	()Ljava/util/Locale;
      //   999: invokevirtual 459	java/util/Locale:toString	()Ljava/lang/String;
      //   1002: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1005: pop
      //   1006: ldc_w 461
      //   1009: invokestatic 467	java/util/TimeZone:getTimeZone	(Ljava/lang/String;)Ljava/util/TimeZone;
      //   1012: getstatic 471	java/util/Locale:ENGLISH	Ljava/util/Locale;
      //   1015: invokestatic 477	java/util/Calendar:getInstance	(Ljava/util/TimeZone;Ljava/util/Locale;)Ljava/util/Calendar;
      //   1018: astore_1
      //   1019: aload 8
      //   1021: ldc_w 479
      //   1024: new 481	java/text/SimpleDateFormat
      //   1027: dup
      //   1028: ldc_w 482
      //   1031: getstatic 471	java/util/Locale:ENGLISH	Ljava/util/Locale;
      //   1034: invokespecial 485	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
      //   1037: aload_1
      //   1038: invokevirtual 489	java/util/Calendar:getTime	()Ljava/util/Date;
      //   1041: invokevirtual 492	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
      //   1044: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1047: pop
      //   1048: aload 8
      //   1050: ldc_w 494
      //   1053: invokestatic 500	java/lang/System:currentTimeMillis	()J
      //   1056: ldc2_w 501
      //   1059: ldiv
      //   1060: invokevirtual 505	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   1063: pop
      //   1064: aload 8
      //   1066: ldc_w 507
      //   1069: ldc_w 509
      //   1072: invokestatic 512	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
      //   1075: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1078: pop
      //   1079: aload 8
      //   1081: ldc_w 514
      //   1084: invokestatic 516	com/appodeal/ads/ao:a	()Z
      //   1087: invokevirtual 289	org/json/JSONObject:put	(Ljava/lang/String;Z)Lorg/json/JSONObject;
      //   1090: pop
      //   1091: aload_0
      //   1092: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1095: invokestatic 157	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   1098: invokestatic 521	com/appodeal/ads/utils/d:c	(Landroid/content/Context;)V
      //   1101: aload 8
      //   1103: ldc_w 523
      //   1106: aload 9
      //   1108: invokestatic 526	com/appodeal/ads/utils/d:a	(Landroid/content/SharedPreferences;)J
      //   1111: invokevirtual 505	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   1114: pop
      //   1115: aload 8
      //   1117: ldc_w 528
      //   1120: invokestatic 530	com/appodeal/ads/utils/d:b	()J
      //   1123: invokevirtual 505	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   1126: pop
      //   1127: aload 8
      //   1129: ldc_w 532
      //   1132: aload 9
      //   1134: invokestatic 534	com/appodeal/ads/utils/d:b	(Landroid/content/SharedPreferences;)J
      //   1137: invokevirtual 505	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   1140: pop
      //   1141: aload 9
      //   1143: ldc_w 536
      //   1146: aconst_null
      //   1147: invokeinterface 204 3 0
      //   1152: astore_1
      //   1153: aload_1
      //   1154: ifnull +20 -> 1174
      //   1157: aload 8
      //   1159: ldc_w 536
      //   1162: new 34	org/json/JSONObject
      //   1165: dup
      //   1166: aload_1
      //   1167: invokespecial 538	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   1170: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1173: pop
      //   1174: aload_0
      //   1175: getfield 540	com/appodeal/ads/u$b:i	Z
      //   1178: ifeq +1429 -> 2607
      //   1181: new 148	org/json/JSONArray
      //   1184: dup
      //   1185: invokespecial 541	org/json/JSONArray:<init>	()V
      //   1188: astore_1
      //   1189: aload_0
      //   1190: getfield 110	com/appodeal/ads/u$b:b	Z
      //   1193: ifeq +1072 -> 2265
      //   1196: aload_0
      //   1197: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1200: invokestatic 157	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   1203: invokestatic 544	com/appodeal/ads/o:a	(Landroid/content/Context;)Ljava/util/Set;
      //   1206: invokeinterface 550 1 0
      //   1211: astore 6
      //   1213: aload 6
      //   1215: invokeinterface 555 1 0
      //   1220: ifeq +1045 -> 2265
      //   1223: aload 6
      //   1225: invokeinterface 559 1 0
      //   1230: checkcast 561	com/appodeal/ads/p
      //   1233: astore 7
      //   1235: aload 7
      //   1237: invokevirtual 564	com/appodeal/ads/p:g	()Lcom/appodeal/ads/s;
      //   1240: ifnull -27 -> 1213
      //   1243: aload_1
      //   1244: aload 7
      //   1246: invokevirtual 566	com/appodeal/ads/p:a	()Ljava/lang/String;
      //   1249: invokevirtual 569	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   1252: pop
      //   1253: goto -40 -> 1213
      //   1256: astore_1
      //   1257: aload_1
      //   1258: invokestatic 137	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   1261: invokestatic 572	java/util/Calendar:getInstance	()Ljava/util/Calendar;
      //   1264: astore_1
      //   1265: aload_1
      //   1266: aload 9
      //   1268: ldc_w 574
      //   1271: lconst_0
      //   1272: invokeinterface 578 4 0
      //   1277: invokevirtual 582	java/util/Calendar:setTimeInMillis	(J)V
      //   1280: aload_1
      //   1281: iconst_5
      //   1282: iconst_1
      //   1283: invokevirtual 586	java/util/Calendar:add	(II)V
      //   1286: getstatic 591	com/appodeal/ads/UserSettings:sendingInProgress	Z
      //   1289: ifne +122 -> 1411
      //   1292: aload_0
      //   1293: getfield 540	com/appodeal/ads/u$b:i	Z
      //   1296: ifeq +115 -> 1411
      //   1299: aload_1
      //   1300: invokevirtual 594	java/util/Calendar:getTimeInMillis	()J
      //   1303: invokestatic 500	java/lang/System:currentTimeMillis	()J
      //   1306: lcmp
      //   1307: iflt +17 -> 1324
      //   1310: aload 9
      //   1312: ldc_w 596
      //   1315: iconst_1
      //   1316: invokeinterface 600 3 0
      //   1321: ifeq +90 -> 1411
      //   1324: iconst_1
      //   1325: putstatic 591	com/appodeal/ads/UserSettings:sendingInProgress	Z
      //   1328: aload 8
      //   1330: ldc_w 602
      //   1333: aload_0
      //   1334: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1337: invokestatic 157	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   1340: invokestatic 607	com/appodeal/ads/utils/t:a	(Landroid/content/Context;)Lorg/json/JSONArray;
      //   1343: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1346: pop
      //   1347: aload 8
      //   1349: ldc_w 609
      //   1352: aload_0
      //   1353: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1356: invokestatic 157	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   1359: invokestatic 613	com/appodeal/ads/Appodeal:getUserSettings	(Landroid/content/Context;)Lcom/appodeal/ads/UserSettings;
      //   1362: invokevirtual 615	com/appodeal/ads/UserSettings:a	()Lorg/json/JSONObject;
      //   1365: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1368: pop
      //   1369: aload 9
      //   1371: invokeinterface 247 1 0
      //   1376: astore_1
      //   1377: aload_1
      //   1378: ldc_w 574
      //   1381: invokestatic 500	java/lang/System:currentTimeMillis	()J
      //   1384: invokeinterface 619 4 0
      //   1389: pop
      //   1390: aload_1
      //   1391: ldc_w 596
      //   1394: iconst_0
      //   1395: invokeinterface 623 3 0
      //   1400: pop
      //   1401: aload_1
      //   1402: invokeinterface 256 1 0
      //   1407: iconst_0
      //   1408: putstatic 591	com/appodeal/ads/UserSettings:sendingInProgress	Z
      //   1411: invokestatic 572	java/util/Calendar:getInstance	()Ljava/util/Calendar;
      //   1414: astore_1
      //   1415: aload_1
      //   1416: aload 9
      //   1418: ldc_w 625
      //   1421: lconst_0
      //   1422: invokeinterface 578 4 0
      //   1427: invokevirtual 582	java/util/Calendar:setTimeInMillis	(J)V
      //   1430: aload_1
      //   1431: iconst_5
      //   1432: iconst_1
      //   1433: invokevirtual 586	java/util/Calendar:add	(II)V
      //   1436: getstatic 627	com/appodeal/ads/AppodealSettings:n	Z
      //   1439: ifne +159 -> 1598
      //   1442: getstatic 630	com/appodeal/ads/AppodealSettings:m	Z
      //   1445: ifeq +153 -> 1598
      //   1448: aload_0
      //   1449: getfield 540	com/appodeal/ads/u$b:i	Z
      //   1452: ifeq +146 -> 1598
      //   1455: aload_1
      //   1456: invokevirtual 594	java/util/Calendar:getTimeInMillis	()J
      //   1459: invokestatic 500	java/lang/System:currentTimeMillis	()J
      //   1462: lcmp
      //   1463: ifge +135 -> 1598
      //   1466: iconst_1
      //   1467: putstatic 627	com/appodeal/ads/AppodealSettings:n	Z
      //   1470: new 148	org/json/JSONArray
      //   1473: dup
      //   1474: invokespecial 541	org/json/JSONArray:<init>	()V
      //   1477: astore_1
      //   1478: aload 10
      //   1480: iconst_0
      //   1481: invokevirtual 634	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
      //   1484: astore 7
      //   1486: ldc_w 636
      //   1489: invokestatic 642	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
      //   1492: astore 6
      //   1494: aload 7
      //   1496: invokeinterface 645 1 0
      //   1501: astore 7
      //   1503: aload 7
      //   1505: invokeinterface 555 1 0
      //   1510: ifeq +1421 -> 2931
      //   1513: aload 7
      //   1515: invokeinterface 559 1 0
      //   1520: checkcast 347	android/content/pm/ApplicationInfo
      //   1523: getfield 648	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
      //   1526: astore 10
      //   1528: aload 6
      //   1530: aload 10
      //   1532: invokevirtual 652	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
      //   1535: invokevirtual 657	java/util/regex/Matcher:matches	()Z
      //   1538: ifne -35 -> 1503
      //   1541: aload 10
      //   1543: ldc_w 301
      //   1546: invokevirtual 104	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1549: ifne -46 -> 1503
      //   1552: aload_1
      //   1553: aload 10
      //   1555: invokevirtual 569	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   1558: pop
      //   1559: goto -56 -> 1503
      //   1562: astore_1
      //   1563: aload_1
      //   1564: invokestatic 137	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   1567: aload 9
      //   1569: invokeinterface 247 1 0
      //   1574: astore_1
      //   1575: aload_1
      //   1576: ldc_w 625
      //   1579: invokestatic 500	java/lang/System:currentTimeMillis	()J
      //   1582: invokeinterface 619 4 0
      //   1587: pop
      //   1588: aload_1
      //   1589: invokeinterface 256 1 0
      //   1594: iconst_0
      //   1595: putstatic 627	com/appodeal/ads/AppodealSettings:n	Z
      //   1598: aload_0
      //   1599: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1602: invokestatic 660	com/appodeal/ads/u:l	(Lcom/appodeal/ads/u;)Lcom/appodeal/ads/d/h;
      //   1605: ifnull +41 -> 1646
      //   1608: aload_0
      //   1609: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1612: invokestatic 210	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   1615: ldc_w 662
      //   1618: invokevirtual 104	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1621: ifeq +1323 -> 2944
      //   1624: aload 8
      //   1626: ldc_w 664
      //   1629: aload_0
      //   1630: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1633: invokestatic 660	com/appodeal/ads/u:l	(Lcom/appodeal/ads/u;)Lcom/appodeal/ads/d/h;
      //   1636: invokevirtual 667	com/appodeal/ads/d/h:a	()Lorg/json/JSONObject;
      //   1639: invokevirtual 668	org/json/JSONObject:toString	()Ljava/lang/String;
      //   1642: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   1645: pop
      //   1646: aload_0
      //   1647: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1650: invokestatic 210	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   1653: ldc_w 662
      //   1656: invokevirtual 104	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1659: ifne +1793 -> 3452
      //   1662: aload_0
      //   1663: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1666: invokestatic 210	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   1669: ldc 82
      //   1671: invokevirtual 104	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1674: ifne +1778 -> 3452
      //   1677: aload_0
      //   1678: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1681: invokestatic 210	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   1684: ldc 88
      //   1686: invokevirtual 104	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1689: ifne +1763 -> 3452
      //   1692: aload_0
      //   1693: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1696: invokestatic 210	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   1699: ldc 108
      //   1701: invokevirtual 104	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1704: ifne +1748 -> 3452
      //   1707: aload_0
      //   1708: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1711: invokestatic 210	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   1714: ldc -44
      //   1716: invokevirtual 104	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1719: ifne +1733 -> 3452
      //   1722: iconst_1
      //   1723: istore 4
      //   1725: iload 4
      //   1727: ifeq +1372 -> 3099
      //   1730: new 670	java/net/URL
      //   1733: dup
      //   1734: ldc_w 672
      //   1737: iconst_4
      //   1738: anewarray 116	java/lang/Object
      //   1741: dup
      //   1742: iconst_0
      //   1743: invokestatic 676	com/appodeal/ads/utils/g:c	()I
      //   1746: invokestatic 679	com/appodeal/ads/utils/g:a	(I)Ljava/lang/String;
      //   1749: aastore
      //   1750: dup
      //   1751: iconst_1
      //   1752: invokestatic 681	com/appodeal/ads/utils/g:b	()Ljava/lang/String;
      //   1755: aastore
      //   1756: dup
      //   1757: iconst_2
      //   1758: invokestatic 676	com/appodeal/ads/utils/g:c	()I
      //   1761: invokestatic 687	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   1764: aastore
      //   1765: dup
      //   1766: iconst_3
      //   1767: ldc_w 689
      //   1770: aastore
      //   1771: invokestatic 120	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   1774: invokespecial 690	java/net/URL:<init>	(Ljava/lang/String;)V
      //   1777: astore_1
      //   1778: aload_0
      //   1779: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1782: invokestatic 157	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   1785: ldc_w 692
      //   1788: iconst_0
      //   1789: invokevirtual 192	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
      //   1792: astore 9
      //   1794: aload_1
      //   1795: invokevirtual 696	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   1798: astore 7
      //   1800: aload_1
      //   1801: invokevirtual 699	java/net/URL:getProtocol	()Ljava/lang/String;
      //   1804: ldc_w 701
      //   1807: invokevirtual 104	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   1810: ifeq +79 -> 1889
      //   1813: new 703	java/util/ArrayList
      //   1816: dup
      //   1817: invokespecial 704	java/util/ArrayList:<init>	()V
      //   1820: astore_1
      //   1821: aload_1
      //   1822: ldc_w 706
      //   1825: invokeinterface 708 2 0
      //   1830: pop
      //   1831: aload_1
      //   1832: ldc_w 710
      //   1835: invokeinterface 708 2 0
      //   1840: pop
      //   1841: new 712	com/appodeal/ads/utils/e
      //   1844: dup
      //   1845: aload_1
      //   1846: ldc2_w 713
      //   1849: invokespecial 717	com/appodeal/ads/utils/e:<init>	(Ljava/util/List;J)V
      //   1852: astore_1
      //   1853: ldc_w 719
      //   1856: invokestatic 724	javax/net/ssl/SSLContext:getInstance	(Ljava/lang/String;)Ljavax/net/ssl/SSLContext;
      //   1859: astore 6
      //   1861: aload 6
      //   1863: aconst_null
      //   1864: iconst_1
      //   1865: anewarray 726	javax/net/ssl/TrustManager
      //   1868: dup
      //   1869: iconst_0
      //   1870: aload_1
      //   1871: aastore
      //   1872: aconst_null
      //   1873: invokevirtual 730	javax/net/ssl/SSLContext:init	([Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Ljava/security/SecureRandom;)V
      //   1876: aload 7
      //   1878: checkcast 732	javax/net/ssl/HttpsURLConnection
      //   1881: aload 6
      //   1883: invokevirtual 736	javax/net/ssl/SSLContext:getSocketFactory	()Ljavax/net/ssl/SSLSocketFactory;
      //   1886: invokevirtual 740	javax/net/ssl/HttpsURLConnection:setSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
      //   1889: iload 4
      //   1891: ifeq +1263 -> 3154
      //   1894: aload 9
      //   1896: aload_0
      //   1897: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   1900: invokestatic 210	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   1903: invokeinterface 744 2 0
      //   1908: ifeq +1246 -> 3154
      //   1911: aload 7
      //   1913: sipush 10000
      //   1916: invokevirtual 750	java/net/URLConnection:setConnectTimeout	(I)V
      //   1919: aload 7
      //   1921: sipush 10000
      //   1924: invokevirtual 753	java/net/URLConnection:setReadTimeout	(I)V
      //   1927: aload 7
      //   1929: sipush 20000
      //   1932: invokevirtual 750	java/net/URLConnection:setConnectTimeout	(I)V
      //   1935: aload 7
      //   1937: sipush 20000
      //   1940: invokevirtual 753	java/net/URLConnection:setReadTimeout	(I)V
      //   1943: aload 7
      //   1945: ldc_w 755
      //   1948: ldc_w 757
      //   1951: invokevirtual 761	java/net/URLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   1954: aload 7
      //   1956: iconst_1
      //   1957: invokevirtual 765	java/net/URLConnection:setDoOutput	(Z)V
      //   1960: new 767	java/io/ByteArrayOutputStream
      //   1963: dup
      //   1964: invokespecial 768	java/io/ByteArrayOutputStream:<init>	()V
      //   1967: astore 6
      //   1969: new 770	java/util/zip/GZIPOutputStream
      //   1972: dup
      //   1973: aload 6
      //   1975: invokespecial 773	java/util/zip/GZIPOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   1978: astore_1
      //   1979: aload_1
      //   1980: aload 8
      //   1982: invokevirtual 668	org/json/JSONObject:toString	()Ljava/lang/String;
      //   1985: ldc_w 775
      //   1988: invokevirtual 779	java/lang/String:getBytes	(Ljava/lang/String;)[B
      //   1991: invokevirtual 783	java/util/zip/GZIPOutputStream:write	([B)V
      //   1994: aload_1
      //   1995: invokevirtual 786	java/util/zip/GZIPOutputStream:close	()V
      //   1998: aload 6
      //   2000: invokevirtual 790	java/io/ByteArrayOutputStream:toByteArray	()[B
      //   2003: iconst_0
      //   2004: invokestatic 796	android/util/Base64:encodeToString	([BI)Ljava/lang/String;
      //   2007: astore_1
      //   2008: aload 7
      //   2010: invokevirtual 800	java/net/URLConnection:getOutputStream	()Ljava/io/OutputStream;
      //   2013: aload_1
      //   2014: invokestatic 803	com/appodeal/ads/ao:a	(Ljava/io/OutputStream;Ljava/lang/String;)V
      //   2017: aload 7
      //   2019: invokevirtual 807	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
      //   2022: invokestatic 810	com/appodeal/ads/ao:a	(Ljava/io/InputStream;)Ljava/lang/String;
      //   2025: astore 6
      //   2027: aload 6
      //   2029: ifnull +42 -> 2071
      //   2032: aload 6
      //   2034: invokevirtual 813	java/lang/String:isEmpty	()Z
      //   2037: ifne +34 -> 2071
      //   2040: aload 6
      //   2042: ldc_w 815
      //   2045: invokevirtual 104	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2048: ifne +23 -> 2071
      //   2051: aload_0
      //   2052: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2055: invokestatic 210	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   2058: ldc_w 662
      //   2061: invokevirtual 104	java/lang/String:equals	(Ljava/lang/Object;)Z
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
      //   2084: instanceof 732
      //   2087: ifeq +1183 -> 3270
      //   2090: aload 7
      //   2092: checkcast 732	javax/net/ssl/HttpsURLConnection
      //   2095: invokevirtual 818	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   2098: aload 6
      //   2100: astore_1
      //   2101: aload_1
      //   2102: ifnonnull +1269 -> 3371
      //   2105: iload 4
      //   2107: ifeq +1351 -> 3458
      //   2110: aload 9
      //   2112: aload_0
      //   2113: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2116: invokestatic 210	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   2119: invokeinterface 744 2 0
      //   2124: ifeq +1334 -> 3458
      //   2127: new 820	com/appodeal/ads/utils/b/a
      //   2130: dup
      //   2131: ldc_w 822
      //   2134: invokespecial 823	com/appodeal/ads/utils/b/a:<init>	(Ljava/lang/String;)V
      //   2137: invokestatic 137	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2140: aload 9
      //   2142: aload_0
      //   2143: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2146: invokestatic 210	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   2149: ldc_w 825
      //   2152: invokeinterface 204 3 0
      //   2157: astore_1
      //   2158: new 34	org/json/JSONObject
      //   2161: dup
      //   2162: aload_1
      //   2163: invokespecial 538	org/json/JSONObject:<init>	(Ljava/lang/String;)V
      //   2166: astore_1
      //   2167: aload_1
      //   2168: invokestatic 827	com/appodeal/ads/Appodeal:a	(Lorg/json/JSONObject;)V
      //   2171: aload_1
      //   2172: ldc_w 829
      //   2175: invokevirtual 833	org/json/JSONObject:optJSONObject	(Ljava/lang/String;)Lorg/json/JSONObject;
      //   2178: putstatic 837	com/appodeal/ads/UserSettings:userData	Lorg/json/JSONObject;
      //   2181: aload_0
      //   2182: aload_1
      //   2183: invokespecial 839	com/appodeal/ads/u$b:b	(Lorg/json/JSONObject;)V
      //   2186: aload_1
      //   2187: areturn
      //   2188: astore 6
      //   2190: aload 6
      //   2192: invokestatic 137	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2195: aload_1
      //   2196: areturn
      //   2197: astore_1
      //   2198: aload_1
      //   2199: invokestatic 137	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2202: aconst_null
      //   2203: areturn
      //   2204: ldc_w 841
      //   2207: astore_1
      //   2208: goto -2013 -> 195
      //   2211: astore_1
      //   2212: aload_1
      //   2213: invokestatic 137	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2216: aload 7
      //   2218: astore_1
      //   2219: goto -1926 -> 293
      //   2222: astore 11
      //   2224: aload 11
      //   2226: invokestatic 137	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2229: goto -1604 -> 625
      //   2232: astore 11
      //   2234: aload 11
      //   2236: invokestatic 137	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2239: goto -1586 -> 653
      //   2242: aload 8
      //   2244: ldc_w 400
      //   2247: ldc_w 843
      //   2250: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2253: pop
      //   2254: goto -1472 -> 782
      //   2257: astore_1
      //   2258: aload_1
      //   2259: invokestatic 137	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2262: goto -1423 -> 839
      //   2265: aload_0
      //   2266: getfield 90	com/appodeal/ads/u$b:e	Z
      //   2269: ifeq +63 -> 2332
      //   2272: aload_0
      //   2273: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2276: invokestatic 157	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   2279: invokestatic 844	com/appodeal/ads/ai:a	(Landroid/content/Context;)Ljava/util/Set;
      //   2282: invokeinterface 550 1 0
      //   2287: astore 6
      //   2289: aload 6
      //   2291: invokeinterface 555 1 0
      //   2296: ifeq +36 -> 2332
      //   2299: aload 6
      //   2301: invokeinterface 559 1 0
      //   2306: checkcast 846	com/appodeal/ads/aq
      //   2309: astore 7
      //   2311: aload 7
      //   2313: invokevirtual 849	com/appodeal/ads/aq:g	()Lcom/appodeal/ads/ar;
      //   2316: ifnull -27 -> 2289
      //   2319: aload_1
      //   2320: aload 7
      //   2322: invokevirtual 850	com/appodeal/ads/aq:a	()Ljava/lang/String;
      //   2325: invokevirtual 569	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   2328: pop
      //   2329: goto -40 -> 2289
      //   2332: aload_0
      //   2333: getfield 92	com/appodeal/ads/u$b:f	Z
      //   2336: ifeq +63 -> 2399
      //   2339: aload_0
      //   2340: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2343: invokestatic 157	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   2346: invokestatic 851	com/appodeal/ads/al:a	(Landroid/content/Context;)Ljava/util/Set;
      //   2349: invokeinterface 550 1 0
      //   2354: astore 6
      //   2356: aload 6
      //   2358: invokeinterface 555 1 0
      //   2363: ifeq +36 -> 2399
      //   2366: aload 6
      //   2368: invokeinterface 559 1 0
      //   2373: checkcast 846	com/appodeal/ads/aq
      //   2376: astore 7
      //   2378: aload 7
      //   2380: invokevirtual 849	com/appodeal/ads/aq:g	()Lcom/appodeal/ads/ar;
      //   2383: ifnull -27 -> 2356
      //   2386: aload_1
      //   2387: aload 7
      //   2389: invokevirtual 850	com/appodeal/ads/aq:a	()Ljava/lang/String;
      //   2392: invokevirtual 569	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   2395: pop
      //   2396: goto -40 -> 2356
      //   2399: aload_0
      //   2400: getfield 122	com/appodeal/ads/u$b:c	Z
      //   2403: ifeq +63 -> 2466
      //   2406: aload_0
      //   2407: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2410: invokestatic 157	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   2413: invokestatic 852	com/appodeal/ads/g:a	(Landroid/content/Context;)Ljava/util/Set;
      //   2416: invokeinterface 550 1 0
      //   2421: astore 6
      //   2423: aload 6
      //   2425: invokeinterface 555 1 0
      //   2430: ifeq +36 -> 2466
      //   2433: aload 6
      //   2435: invokeinterface 559 1 0
      //   2440: checkcast 854	com/appodeal/ads/h
      //   2443: astore 7
      //   2445: aload 7
      //   2447: invokevirtual 857	com/appodeal/ads/h:f	()Lcom/appodeal/ads/k;
      //   2450: ifnull -27 -> 2423
      //   2453: aload_1
      //   2454: aload 7
      //   2456: invokevirtual 858	com/appodeal/ads/h:a	()Ljava/lang/String;
      //   2459: invokevirtual 569	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   2462: pop
      //   2463: goto -40 -> 2423
      //   2466: aload_0
      //   2467: getfield 126	com/appodeal/ads/u$b:d	Z
      //   2470: ifeq +63 -> 2533
      //   2473: aload_0
      //   2474: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2477: invokestatic 157	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   2480: invokestatic 859	com/appodeal/ads/w:a	(Landroid/content/Context;)Ljava/util/Set;
      //   2483: invokeinterface 550 1 0
      //   2488: astore 6
      //   2490: aload 6
      //   2492: invokeinterface 555 1 0
      //   2497: ifeq +36 -> 2533
      //   2500: aload 6
      //   2502: invokeinterface 559 1 0
      //   2507: checkcast 861	com/appodeal/ads/x
      //   2510: astore 7
      //   2512: aload 7
      //   2514: invokevirtual 864	com/appodeal/ads/x:f	()Lcom/appodeal/ads/aa;
      //   2517: ifnull -27 -> 2490
      //   2520: aload_1
      //   2521: aload 7
      //   2523: invokevirtual 865	com/appodeal/ads/x:a	()Ljava/lang/String;
      //   2526: invokevirtual 569	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   2529: pop
      //   2530: goto -40 -> 2490
      //   2533: aload_0
      //   2534: getfield 130	com/appodeal/ads/u$b:g	Z
      //   2537: ifeq +60 -> 2597
      //   2540: aload_0
      //   2541: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2544: invokestatic 157	com/appodeal/ads/u:b	(Lcom/appodeal/ads/u;)Landroid/content/Context;
      //   2547: invokestatic 866	com/appodeal/ads/Native:a	(Landroid/content/Context;)Ljava/util/Set;
      //   2550: invokeinterface 550 1 0
      //   2555: astore 6
      //   2557: aload 6
      //   2559: invokeinterface 555 1 0
      //   2564: ifeq +33 -> 2597
      //   2567: aload 6
      //   2569: invokeinterface 559 1 0
      //   2574: checkcast 868	com/appodeal/ads/ad
      //   2577: astore 7
      //   2579: aload 7
      //   2581: ifnull -24 -> 2557
      //   2584: aload_1
      //   2585: aload 7
      //   2587: invokevirtual 869	com/appodeal/ads/ad:a	()Ljava/lang/String;
      //   2590: invokevirtual 569	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
      //   2593: pop
      //   2594: goto -37 -> 2557
      //   2597: aload 8
      //   2599: ldc_w 871
      //   2602: aload_1
      //   2603: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2606: pop
      //   2607: aload_0
      //   2608: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2611: invokestatic 873	com/appodeal/ads/u:c	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   2614: ifnull +19 -> 2633
      //   2617: aload 8
      //   2619: ldc_w 875
      //   2622: aload_0
      //   2623: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2626: invokestatic 873	com/appodeal/ads/u:c	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   2629: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2632: pop
      //   2633: aload_0
      //   2634: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2637: invokestatic 878	com/appodeal/ads/u:d	(Lcom/appodeal/ads/u;)Ljava/lang/Long;
      //   2640: ifnull +36 -> 2676
      //   2643: aload_0
      //   2644: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2647: invokestatic 878	com/appodeal/ads/u:d	(Lcom/appodeal/ads/u;)Ljava/lang/Long;
      //   2650: invokevirtual 883	java/lang/Long:longValue	()J
      //   2653: ldc2_w 884
      //   2656: lcmp
      //   2657: ifeq +19 -> 2676
      //   2660: aload 8
      //   2662: ldc_w 887
      //   2665: aload_0
      //   2666: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2669: invokestatic 878	com/appodeal/ads/u:d	(Lcom/appodeal/ads/u;)Ljava/lang/Long;
      //   2672: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2675: pop
      //   2676: aload_0
      //   2677: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2680: invokestatic 890	com/appodeal/ads/u:e	(Lcom/appodeal/ads/u;)J
      //   2683: lconst_0
      //   2684: lcmp
      //   2685: ifeq +19 -> 2704
      //   2688: aload 8
      //   2690: ldc_w 892
      //   2693: aload_0
      //   2694: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2697: invokestatic 890	com/appodeal/ads/u:e	(Lcom/appodeal/ads/u;)J
      //   2700: invokevirtual 505	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   2703: pop
      //   2704: aload_0
      //   2705: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2708: invokestatic 210	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   2711: ldc 88
      //   2713: invokevirtual 104	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2716: ifeq +19 -> 2735
      //   2719: aload 8
      //   2721: ldc_w 894
      //   2724: invokestatic 500	java/lang/System:currentTimeMillis	()J
      //   2727: ldc2_w 501
      //   2730: ldiv
      //   2731: invokevirtual 505	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   2734: pop
      //   2735: aload_0
      //   2736: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2739: invokestatic 210	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   2742: ldc 108
      //   2744: invokevirtual 104	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2747: ifeq +19 -> 2766
      //   2750: aload 8
      //   2752: ldc_w 896
      //   2755: invokestatic 500	java/lang/System:currentTimeMillis	()J
      //   2758: ldc2_w 501
      //   2761: ldiv
      //   2762: invokevirtual 505	org/json/JSONObject:put	(Ljava/lang/String;J)Lorg/json/JSONObject;
      //   2765: pop
      //   2766: aload_0
      //   2767: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2770: invokestatic 899	com/appodeal/ads/u:f	(Lcom/appodeal/ads/u;)I
      //   2773: iconst_1
      //   2774: if_icmple +19 -> 2793
      //   2777: aload 8
      //   2779: ldc_w 901
      //   2782: aload_0
      //   2783: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2786: invokestatic 899	com/appodeal/ads/u:f	(Lcom/appodeal/ads/u;)I
      //   2789: invokevirtual 86	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
      //   2792: pop
      //   2793: aload_0
      //   2794: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2797: invokestatic 904	com/appodeal/ads/u:g	(Lcom/appodeal/ads/u;)D
      //   2800: dconst_0
      //   2801: dcmpl
      //   2802: ifle +19 -> 2821
      //   2805: aload 8
      //   2807: ldc_w 906
      //   2810: aload_0
      //   2811: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2814: invokestatic 904	com/appodeal/ads/u:g	(Lcom/appodeal/ads/u;)D
      //   2817: invokevirtual 379	org/json/JSONObject:put	(Ljava/lang/String;D)Lorg/json/JSONObject;
      //   2820: pop
      //   2821: aload 8
      //   2823: ldc_w 664
      //   2826: aload_0
      //   2827: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2830: invokestatic 908	com/appodeal/ads/u:h	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   2833: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2836: pop
      //   2837: aload 8
      //   2839: ldc_w 910
      //   2842: aload_0
      //   2843: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2846: invokestatic 912	com/appodeal/ads/u:i	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   2849: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2852: pop
      //   2853: aload_0
      //   2854: getfield 540	com/appodeal/ads/u$b:i	Z
      //   2857: ifne +13 -> 2870
      //   2860: aload_0
      //   2861: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2864: invokestatic 96	com/appodeal/ads/u:j	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   2867: ifnull +16 -> 2883
      //   2870: aload 8
      //   2872: ldc_w 914
      //   2875: aload_0
      //   2876: invokespecial 915	com/appodeal/ads/u$b:a	()Lorg/json/JSONObject;
      //   2879: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2882: pop
      //   2883: aload_0
      //   2884: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2887: invokestatic 918	com/appodeal/ads/u:k	(Lcom/appodeal/ads/u;)Lcom/appodeal/ads/f/c;
      //   2890: ifnull -1629 -> 1261
      //   2893: aload 8
      //   2895: ldc_w 920
      //   2898: aload_0
      //   2899: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2902: invokestatic 918	com/appodeal/ads/u:k	(Lcom/appodeal/ads/u;)Lcom/appodeal/ads/f/c;
      //   2905: invokevirtual 924	com/appodeal/ads/f/c:a	()I
      //   2908: invokevirtual 86	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
      //   2911: pop
      //   2912: goto -1651 -> 1261
      //   2915: astore_1
      //   2916: aload_1
      //   2917: invokestatic 137	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2920: goto -1573 -> 1347
      //   2923: astore_1
      //   2924: aload_1
      //   2925: invokestatic 137	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   2928: goto -1559 -> 1369
      //   2931: aload 8
      //   2933: ldc_w 926
      //   2936: aload_1
      //   2937: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   2940: pop
      //   2941: goto -1374 -> 1567
      //   2944: aload_0
      //   2945: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2948: invokestatic 210	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   2951: ldc 82
      //   2953: invokevirtual 104	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   2956: ifeq +78 -> 3034
      //   2959: aload_0
      //   2960: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2963: invokestatic 660	com/appodeal/ads/u:l	(Lcom/appodeal/ads/u;)Lcom/appodeal/ads/d/h;
      //   2966: invokevirtual 928	com/appodeal/ads/d/h:f	()Z
      //   2969: ifeq -1323 -> 1646
      //   2972: aload_0
      //   2973: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   2976: invokestatic 660	com/appodeal/ads/u:l	(Lcom/appodeal/ads/u;)Lcom/appodeal/ads/d/h;
      //   2979: invokevirtual 930	com/appodeal/ads/d/h:d	()Z
      //   2982: ifne +11 -> 2993
      //   2985: ldc_w 932
      //   2988: invokestatic 261	com/appodeal/ads/Appodeal:a	(Ljava/lang/String;)V
      //   2991: aconst_null
      //   2992: areturn
      //   2993: aload 8
      //   2995: ldc_w 934
      //   2998: aload_0
      //   2999: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   3002: invokestatic 660	com/appodeal/ads/u:l	(Lcom/appodeal/ads/u;)Lcom/appodeal/ads/d/h;
      //   3005: invokevirtual 936	com/appodeal/ads/d/h:c	()Lorg/json/JSONObject;
      //   3008: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3011: pop
      //   3012: aload 8
      //   3014: ldc_w 938
      //   3017: aload_0
      //   3018: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   3021: invokestatic 660	com/appodeal/ads/u:l	(Lcom/appodeal/ads/u;)Lcom/appodeal/ads/d/h;
      //   3024: invokevirtual 940	com/appodeal/ads/d/h:e	()Ljava/lang/String;
      //   3027: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3030: pop
      //   3031: goto -1385 -> 1646
      //   3034: aload_0
      //   3035: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   3038: invokestatic 210	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   3041: ldc 88
      //   3043: invokevirtual 104	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3046: ifne +18 -> 3064
      //   3049: aload_0
      //   3050: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   3053: invokestatic 210	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   3056: ldc 108
      //   3058: invokevirtual 104	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3061: ifeq -1415 -> 1646
      //   3064: aload_0
      //   3065: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   3068: invokestatic 660	com/appodeal/ads/u:l	(Lcom/appodeal/ads/u;)Lcom/appodeal/ads/d/h;
      //   3071: invokevirtual 928	com/appodeal/ads/d/h:f	()Z
      //   3074: ifeq -1428 -> 1646
      //   3077: aload 8
      //   3079: ldc_w 664
      //   3082: aload_0
      //   3083: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   3086: invokestatic 660	com/appodeal/ads/u:l	(Lcom/appodeal/ads/u;)Lcom/appodeal/ads/d/h;
      //   3089: invokevirtual 940	com/appodeal/ads/d/h:e	()Ljava/lang/String;
      //   3092: invokevirtual 272	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
      //   3095: pop
      //   3096: goto -1450 -> 1646
      //   3099: new 670	java/net/URL
      //   3102: dup
      //   3103: ldc_w 672
      //   3106: iconst_4
      //   3107: anewarray 116	java/lang/Object
      //   3110: dup
      //   3111: iconst_0
      //   3112: invokestatic 676	com/appodeal/ads/utils/g:c	()I
      //   3115: invokestatic 679	com/appodeal/ads/utils/g:a	(I)Ljava/lang/String;
      //   3118: aastore
      //   3119: dup
      //   3120: iconst_1
      //   3121: invokestatic 681	com/appodeal/ads/utils/g:b	()Ljava/lang/String;
      //   3124: aastore
      //   3125: dup
      //   3126: iconst_2
      //   3127: invokestatic 676	com/appodeal/ads/utils/g:c	()I
      //   3130: invokestatic 687	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   3133: aastore
      //   3134: dup
      //   3135: iconst_3
      //   3136: aload_0
      //   3137: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   3140: invokestatic 210	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   3143: aastore
      //   3144: invokestatic 120	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   3147: invokespecial 690	java/net/URL:<init>	(Ljava/lang/String;)V
      //   3150: astore_1
      //   3151: goto -1373 -> 1778
      //   3154: aload 7
      //   3156: sipush 20000
      //   3159: invokevirtual 750	java/net/URLConnection:setConnectTimeout	(I)V
      //   3162: aload 7
      //   3164: sipush 20000
      //   3167: invokevirtual 753	java/net/URLConnection:setReadTimeout	(I)V
      //   3170: goto -1243 -> 1927
      //   3173: astore 6
      //   3175: aload 7
      //   3177: astore_1
      //   3178: aload 6
      //   3180: invokestatic 137	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   3183: aload 6
      //   3185: invokevirtual 943	java/io/IOException:getMessage	()Ljava/lang/String;
      //   3188: ldc_w 945
      //   3191: invokevirtual 104	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3194: ifne +21 -> 3215
      //   3197: aload 6
      //   3199: invokevirtual 943	java/io/IOException:getMessage	()Ljava/lang/String;
      //   3202: ldc_w 947
      //   3205: invokevirtual 104	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   3208: istore 5
      //   3210: iload 5
      //   3212: ifeq +100 -> 3312
      //   3215: aload_1
      //   3216: ifnull +17 -> 3233
      //   3219: aload_1
      //   3220: instanceof 732
      //   3223: ifeq +72 -> 3295
      //   3226: aload_1
      //   3227: checkcast 732	javax/net/ssl/HttpsURLConnection
      //   3230: invokevirtual 818	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   3233: aconst_null
      //   3234: areturn
      //   3235: astore 6
      //   3237: aload_1
      //   3238: invokevirtual 786	java/util/zip/GZIPOutputStream:close	()V
      //   3241: aload 6
      //   3243: athrow
      //   3244: astore 6
      //   3246: aload 7
      //   3248: astore_1
      //   3249: aload_1
      //   3250: ifnull +17 -> 3267
      //   3253: aload_1
      //   3254: instanceof 732
      //   3257: ifeq +97 -> 3354
      //   3260: aload_1
      //   3261: checkcast 732	javax/net/ssl/HttpsURLConnection
      //   3264: invokevirtual 818	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   3267: aload 6
      //   3269: athrow
      //   3270: aload 6
      //   3272: astore_1
      //   3273: aload 7
      //   3275: instanceof 949
      //   3278: ifeq -1177 -> 2101
      //   3281: aload 7
      //   3283: checkcast 949	java/net/HttpURLConnection
      //   3286: invokevirtual 950	java/net/HttpURLConnection:disconnect	()V
      //   3289: aload 6
      //   3291: astore_1
      //   3292: goto -1191 -> 2101
      //   3295: aload_1
      //   3296: instanceof 949
      //   3299: ifeq -66 -> 3233
      //   3302: aload_1
      //   3303: checkcast 949	java/net/HttpURLConnection
      //   3306: invokevirtual 950	java/net/HttpURLConnection:disconnect	()V
      //   3309: goto -76 -> 3233
      //   3312: aload_1
      //   3313: ifnull +124 -> 3437
      //   3316: aload_1
      //   3317: instanceof 732
      //   3320: ifeq +15 -> 3335
      //   3323: aload_1
      //   3324: checkcast 732	javax/net/ssl/HttpsURLConnection
      //   3327: invokevirtual 818	javax/net/ssl/HttpsURLConnection:disconnect	()V
      //   3330: aconst_null
      //   3331: astore_1
      //   3332: goto -1231 -> 2101
      //   3335: aload_1
      //   3336: instanceof 949
      //   3339: ifeq +98 -> 3437
      //   3342: aload_1
      //   3343: checkcast 949	java/net/HttpURLConnection
      //   3346: invokevirtual 950	java/net/HttpURLConnection:disconnect	()V
      //   3349: aconst_null
      //   3350: astore_1
      //   3351: goto -1250 -> 2101
      //   3354: aload_1
      //   3355: instanceof 949
      //   3358: ifeq -91 -> 3267
      //   3361: aload_1
      //   3362: checkcast 949	java/net/HttpURLConnection
      //   3365: invokevirtual 950	java/net/HttpURLConnection:disconnect	()V
      //   3368: goto -101 -> 3267
      //   3371: iload 4
      //   3373: ifeq +87 -> 3460
      //   3376: aload 9
      //   3378: invokeinterface 247 1 0
      //   3383: astore 6
      //   3385: aload 6
      //   3387: aload_0
      //   3388: getfield 22	com/appodeal/ads/u$b:a	Lcom/appodeal/ads/u;
      //   3391: invokestatic 210	com/appodeal/ads/u:a	(Lcom/appodeal/ads/u;)Ljava/lang/String;
      //   3394: aload_1
      //   3395: invokeinterface 253 3 0
      //   3400: pop
      //   3401: aload 6
      //   3403: invokeinterface 256 1 0
      //   3408: goto +52 -> 3460
      //   3411: astore_1
      //   3412: aload_1
      //   3413: invokestatic 137	com/appodeal/ads/Appodeal:a	(Ljava/lang/Exception;)V
      //   3416: aconst_null
      //   3417: areturn
      //   3418: astore 6
      //   3420: aconst_null
      //   3421: astore_1
      //   3422: goto -173 -> 3249
      //   3425: astore 6
      //   3427: goto -178 -> 3249
      //   3430: astore 6
      //   3432: aconst_null
      //   3433: astore_1
      //   3434: goto -256 -> 3178
      //   3437: aconst_null
      //   3438: astore_1
      //   3439: goto -1338 -> 2101
      //   3442: goto -3134 -> 308
      //   3445: ldc_w 952
      //   3448: astore_1
      //   3449: goto -2651 -> 798
      //   3452: iconst_0
      //   3453: istore 4
      //   3455: goto -1730 -> 1725
      //   3458: aconst_null
      //   3459: areturn
      //   3460: goto -1302 -> 2158
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	3463	0	this	b
      //   0	3463	1	paramVarArgs	Void[]
      //   374	19	2	f1	float
      //   385	16	3	f2	float
      //   1723	1731	4	j	int
      //   93	3118	5	bool	boolean
      //   70	2029	6	localObject1	Object
      //   2188	3	6	localException1	Exception
      //   2287	281	6	localIterator	java.util.Iterator
      //   3173	25	6	localIOException1	java.io.IOException
      //   3235	7	6	localObject2	Object
      //   3244	46	6	localObject3	Object
      //   3383	19	6	localEditor	android.content.SharedPreferences.Editor
      //   3418	1	6	localObject4	Object
      //   3425	1	6	localObject5	Object
      //   3430	1	6	localIOException2	java.io.IOException
      //   74	3208	7	localObject6	Object
      //   56	3022	8	localObject7	Object
      //   13	3364	9	localSharedPreferences	android.content.SharedPreferences
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
      //   2793	2821	1256	org/json/JSONException
      //   2821	2870	1256	org/json/JSONException
      //   2870	2883	1256	org/json/JSONException
      //   2883	2912	1256	org/json/JSONException
      //   1470	1503	1562	java/lang/Exception
      //   1503	1559	1562	java/lang/Exception
      //   2931	2941	1562	java/lang/Exception
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
      //   2793	2821	2197	java/lang/Exception
      //   2821	2870	2197	java/lang/Exception
      //   2870	2883	2197	java/lang/Exception
      //   2883	2912	2197	java/lang/Exception
      //   2916	2920	2197	java/lang/Exception
      //   2924	2928	2197	java/lang/Exception
      //   2944	2991	2197	java/lang/Exception
      //   2993	3031	2197	java/lang/Exception
      //   3034	3064	2197	java/lang/Exception
      //   3064	3096	2197	java/lang/Exception
      //   3099	3151	2197	java/lang/Exception
      //   3219	3233	2197	java/lang/Exception
      //   3253	3267	2197	java/lang/Exception
      //   3267	3270	2197	java/lang/Exception
      //   3273	3289	2197	java/lang/Exception
      //   3295	3309	2197	java/lang/Exception
      //   3316	3330	2197	java/lang/Exception
      //   3335	3349	2197	java/lang/Exception
      //   3354	3368	2197	java/lang/Exception
      //   3376	3408	2197	java/lang/Exception
      //   3412	3416	2197	java/lang/Exception
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
      //   1328	1347	2915	java/lang/Exception
      //   1347	1369	2923	java/lang/Exception
      //   1800	1889	3173	java/io/IOException
      //   1894	1927	3173	java/io/IOException
      //   1927	1979	3173	java/io/IOException
      //   1994	2027	3173	java/io/IOException
      //   2032	2066	3173	java/io/IOException
      //   3154	3170	3173	java/io/IOException
      //   3237	3244	3173	java/io/IOException
      //   1979	1994	3235	finally
      //   1800	1889	3244	finally
      //   1894	1927	3244	finally
      //   1927	1979	3244	finally
      //   1994	2027	3244	finally
      //   2032	2066	3244	finally
      //   3154	3170	3244	finally
      //   3237	3244	3244	finally
      //   2158	2171	3411	org/json/JSONException
      //   1794	1800	3418	finally
      //   3178	3210	3425	finally
      //   1794	1800	3430	java/io/IOException
    }
    
    protected void a(JSONObject paramJSONObject)
    {
      try
      {
        if (u.m(u.this) != null)
        {
          if (paramJSONObject == null)
          {
            u.m(u.this).a(u.n(u.this));
            return;
          }
          u.m(u.this).a(paramJSONObject, u.n(u.this), u.a(u.this));
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
