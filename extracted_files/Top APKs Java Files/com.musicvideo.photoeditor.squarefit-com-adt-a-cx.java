package com.adt.a;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.aiming.mdt.sdk.ad.bannerad.BannerAdListener;
import com.aiming.mdt.sdk.ad.bannerad.IBannerEvent;
import com.aiming.mdt.sdk.ad.interactivead.IInteractiveEvent;
import com.aiming.mdt.sdk.ad.interactivead.InteractiveAdListener;
import com.aiming.mdt.sdk.ad.interstitialAd.IInterstitialEvent;
import com.aiming.mdt.sdk.ad.interstitialAd.InterstitialAdListener;
import com.aiming.mdt.sdk.ad.nativead.INativeEvent;
import com.aiming.mdt.sdk.ad.nativead.NativeAdListener;
import com.aiming.mdt.sdk.ad.videoad.VideoAdListener;
import com.aiming.mdt.sdk.ad.videoad.event.IVideoEvent;
import com.aiming.mdt.sdk.shell.AdConfigHelper;
import com.aiming.mdt.sdk.shell.LoadExecutor;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPOutputStream;

public class cx
{
  public cx() {}
  
  private static byte[] b(byte[] paramArrayOfByte)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      GZIPOutputStream localGZIPOutputStream = new GZIPOutputStream(localByteArrayOutputStream);
      localGZIPOutputStream.write(paramArrayOfByte);
      localGZIPOutputStream.close();
      return localByteArrayOutputStream.toByteArray();
    }
    catch (IOException paramArrayOfByte)
    {
      for (;;)
      {
        di.b("gzip compress error.", paramArrayOfByte);
      }
    }
  }
  
  /* Error */
  private static void d(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 52
    //   3: iconst_0
    //   4: invokevirtual 58	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   7: astore 5
    //   9: new 60	java/text/SimpleDateFormat
    //   12: dup
    //   13: ldc 62
    //   15: getstatic 68	java/util/Locale:US	Ljava/util/Locale;
    //   18: invokespecial 71	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   21: new 73	java/util/Date
    //   24: dup
    //   25: invokespecial 74	java/util/Date:<init>	()V
    //   28: invokevirtual 80	java/text/DateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   31: astore 4
    //   33: aload 5
    //   35: aload 4
    //   37: iconst_0
    //   38: invokeinterface 86 3 0
    //   43: ifeq +9 -> 52
    //   46: ldc 88
    //   48: invokestatic 91	com/adt/a/di:b	(Ljava/lang/String;)V
    //   51: return
    //   52: aload_0
    //   53: invokestatic 97	com/aiming/mdt/sdk/AdtAds:getAppKey	(Landroid/content/Context;)Ljava/lang/String;
    //   56: astore_2
    //   57: new 99	com/adt/a/dn
    //   60: dup
    //   61: invokespecial 100	com/adt/a/dn:<init>	()V
    //   64: ldc 102
    //   66: aload_0
    //   67: invokestatic 107	com/adt/a/dp:c	(Landroid/content/Context;)Lcom/adt/a/dp;
    //   70: ldc 109
    //   72: invokevirtual 112	com/adt/a/dp:c	(Ljava/lang/String;)Ljava/lang/String;
    //   75: invokevirtual 115	com/adt/a/dn:d	(Ljava/lang/String;Ljava/lang/Object;)Lcom/adt/a/dn;
    //   78: ldc 117
    //   80: aload_2
    //   81: invokevirtual 115	com/adt/a/dn:d	(Ljava/lang/String;Ljava/lang/Object;)Lcom/adt/a/dn;
    //   84: ldc 119
    //   86: ldc 121
    //   88: invokevirtual 115	com/adt/a/dn:d	(Ljava/lang/String;Ljava/lang/Object;)Lcom/adt/a/dn;
    //   91: ldc 123
    //   93: ldc 125
    //   95: invokevirtual 115	com/adt/a/dn:d	(Ljava/lang/String;Ljava/lang/Object;)Lcom/adt/a/dn;
    //   98: astore_2
    //   99: aload_0
    //   100: getstatic 131	com/adt/a/bv:a	Lcom/adt/a/bv;
    //   103: invokestatic 137	com/aiming/mdt/sdk/shell/AdConfigHelper:getHost	(Landroid/content/Context;Lcom/adt/a/bv;)Ljava/lang/String;
    //   106: astore_3
    //   107: new 139	java/lang/StringBuilder
    //   110: dup
    //   111: invokespecial 140	java/lang/StringBuilder:<init>	()V
    //   114: aload_3
    //   115: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: ldc -110
    //   120: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   123: aload_2
    //   124: invokevirtual 149	com/adt/a/dn:d	()Ljava/lang/String;
    //   127: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: invokevirtual 152	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   133: astore_2
    //   134: new 139	java/lang/StringBuilder
    //   137: dup
    //   138: invokespecial 140	java/lang/StringBuilder:<init>	()V
    //   141: ldc -102
    //   143: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: aload_2
    //   147: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: invokevirtual 152	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   153: invokestatic 91	com/adt/a/di:b	(Ljava/lang/String;)V
    //   156: new 139	java/lang/StringBuilder
    //   159: dup
    //   160: invokespecial 140	java/lang/StringBuilder:<init>	()V
    //   163: astore_3
    //   164: aload_0
    //   165: invokevirtual 158	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   168: iconst_0
    //   169: invokevirtual 164	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   172: invokeinterface 170 1 0
    //   177: astore_0
    //   178: aload_0
    //   179: invokeinterface 176 1 0
    //   184: ifeq +66 -> 250
    //   187: aload_0
    //   188: invokeinterface 180 1 0
    //   193: checkcast 182	android/content/pm/PackageInfo
    //   196: astore 6
    //   198: aload 6
    //   200: getfield 186	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   203: getfield 192	android/content/pm/ApplicationInfo:flags	I
    //   206: iconst_1
    //   207: iand
    //   208: ifgt -30 -> 178
    //   211: aload 6
    //   213: getfield 196	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   216: ldc -58
    //   218: invokevirtual 204	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   221: ifne -43 -> 178
    //   224: aload_3
    //   225: aload 6
    //   227: getfield 196	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   230: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   233: ldc -50
    //   235: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: pop
    //   239: goto -61 -> 178
    //   242: astore_0
    //   243: ldc -48
    //   245: aload_0
    //   246: invokestatic 43	com/adt/a/di:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   249: return
    //   250: aload_3
    //   251: iconst_0
    //   252: aload_3
    //   253: invokevirtual 212	java/lang/StringBuilder:length	()I
    //   256: iconst_1
    //   257: isub
    //   258: invokevirtual 216	java/lang/StringBuilder:substring	(II)Ljava/lang/String;
    //   261: astore_0
    //   262: aload_0
    //   263: invokestatic 91	com/adt/a/di:b	(Ljava/lang/String;)V
    //   266: aload_0
    //   267: ldc -38
    //   269: invokevirtual 222	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   272: invokestatic 224	com/adt/a/cx:b	([B)[B
    //   275: invokestatic 227	com/adt/a/dj:b	([B)[B
    //   278: astore 6
    //   280: aload 6
    //   282: ifnull +267 -> 549
    //   285: aconst_null
    //   286: astore_0
    //   287: aconst_null
    //   288: astore_3
    //   289: new 229	java/net/URL
    //   292: dup
    //   293: aload_2
    //   294: invokevirtual 232	java/lang/String:trim	()Ljava/lang/String;
    //   297: invokespecial 234	java/net/URL:<init>	(Ljava/lang/String;)V
    //   300: invokevirtual 238	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   303: checkcast 240	java/net/HttpURLConnection
    //   306: astore_2
    //   307: aload_2
    //   308: iconst_1
    //   309: invokevirtual 244	java/net/HttpURLConnection:setDoInput	(Z)V
    //   312: aload_2
    //   313: iconst_1
    //   314: invokevirtual 247	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   317: aload_2
    //   318: iconst_0
    //   319: invokevirtual 250	java/net/HttpURLConnection:setUseCaches	(Z)V
    //   322: aload_2
    //   323: ldc -4
    //   325: ldc -3
    //   327: invokevirtual 257	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   330: aload_2
    //   331: ldc_w 259
    //   334: ldc_w 261
    //   337: invokevirtual 257	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   340: aload_2
    //   341: sipush 30000
    //   344: invokevirtual 265	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   347: aload_2
    //   348: ldc_w 266
    //   351: invokevirtual 269	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   354: aload_2
    //   355: invokevirtual 272	java/net/HttpURLConnection:connect	()V
    //   358: aload_2
    //   359: invokevirtual 276	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   362: astore_0
    //   363: aload_0
    //   364: aload 6
    //   366: invokevirtual 279	java/io/OutputStream:write	([B)V
    //   369: aload_0
    //   370: invokevirtual 282	java/io/OutputStream:flush	()V
    //   373: aload_0
    //   374: invokevirtual 283	java/io/OutputStream:close	()V
    //   377: aload_2
    //   378: invokevirtual 286	java/net/HttpURLConnection:getResponseCode	()I
    //   381: istore_1
    //   382: new 139	java/lang/StringBuilder
    //   385: dup
    //   386: invokespecial 140	java/lang/StringBuilder:<init>	()V
    //   389: ldc_w 288
    //   392: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   395: iload_1
    //   396: invokevirtual 291	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   399: ldc_w 293
    //   402: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   405: aload_2
    //   406: invokevirtual 296	java/net/HttpURLConnection:getResponseMessage	()Ljava/lang/String;
    //   409: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   412: invokevirtual 152	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   415: invokestatic 91	com/adt/a/di:b	(Ljava/lang/String;)V
    //   418: iload_1
    //   419: sipush 200
    //   422: if_icmpne +27 -> 449
    //   425: aload 5
    //   427: invokeinterface 300 1 0
    //   432: astore_0
    //   433: aload_0
    //   434: aload 4
    //   436: iconst_1
    //   437: invokeinterface 306 3 0
    //   442: pop
    //   443: aload_0
    //   444: invokeinterface 309 1 0
    //   449: aload_2
    //   450: ifnull +10 -> 460
    //   453: aload_2
    //   454: invokevirtual 313	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   457: invokevirtual 316	java/io/InputStream:close	()V
    //   460: aload_2
    //   461: invokestatic 321	com/aiming/mdt/sdk/util/IOUtil:close	(Ljava/net/HttpURLConnection;)V
    //   464: return
    //   465: astore_0
    //   466: aload_0
    //   467: invokevirtual 324	java/io/IOException:printStackTrace	()V
    //   470: goto -10 -> 460
    //   473: astore_0
    //   474: aload_3
    //   475: astore_2
    //   476: aload_0
    //   477: astore_3
    //   478: aload_2
    //   479: astore_0
    //   480: ldc_w 326
    //   483: aload_3
    //   484: invokestatic 43	com/adt/a/di:b	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   487: aload_2
    //   488: ifnull +10 -> 498
    //   491: aload_2
    //   492: invokevirtual 313	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   495: invokevirtual 316	java/io/InputStream:close	()V
    //   498: aload_2
    //   499: invokestatic 321	com/aiming/mdt/sdk/util/IOUtil:close	(Ljava/net/HttpURLConnection;)V
    //   502: return
    //   503: astore_0
    //   504: aload_0
    //   505: invokevirtual 324	java/io/IOException:printStackTrace	()V
    //   508: goto -10 -> 498
    //   511: astore_3
    //   512: aload_0
    //   513: astore_2
    //   514: aload_3
    //   515: astore_0
    //   516: aload_2
    //   517: ifnull +10 -> 527
    //   520: aload_2
    //   521: invokevirtual 313	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   524: invokevirtual 316	java/io/InputStream:close	()V
    //   527: aload_2
    //   528: invokestatic 321	com/aiming/mdt/sdk/util/IOUtil:close	(Ljava/net/HttpURLConnection;)V
    //   531: aload_0
    //   532: athrow
    //   533: astore_3
    //   534: aload_3
    //   535: invokevirtual 324	java/io/IOException:printStackTrace	()V
    //   538: goto -11 -> 527
    //   541: astore_0
    //   542: goto -26 -> 516
    //   545: astore_3
    //   546: goto -68 -> 478
    //   549: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	550	0	paramContext	Context
    //   381	42	1	i	int
    //   56	472	2	localObject1	Object
    //   106	378	3	localObject2	Object
    //   511	4	3	localObject3	Object
    //   533	2	3	localIOException	IOException
    //   545	1	3	localThrowable	Throwable
    //   31	404	4	str	String
    //   7	419	5	localSharedPreferences	android.content.SharedPreferences
    //   196	169	6	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   0	51	242	java/lang/Throwable
    //   52	178	242	java/lang/Throwable
    //   178	239	242	java/lang/Throwable
    //   250	280	242	java/lang/Throwable
    //   453	460	242	java/lang/Throwable
    //   460	464	242	java/lang/Throwable
    //   466	470	242	java/lang/Throwable
    //   491	498	242	java/lang/Throwable
    //   498	502	242	java/lang/Throwable
    //   504	508	242	java/lang/Throwable
    //   520	527	242	java/lang/Throwable
    //   527	533	242	java/lang/Throwable
    //   534	538	242	java/lang/Throwable
    //   453	460	465	java/io/IOException
    //   289	307	473	java/lang/Throwable
    //   491	498	503	java/io/IOException
    //   289	307	511	finally
    //   480	487	511	finally
    //   520	527	533	java/io/IOException
    //   307	418	541	finally
    //   425	449	541	finally
    //   307	418	545	java/lang/Throwable
    //   425	449	545	java/lang/Throwable
  }
  
  private static boolean d(bw paramBw, String paramString)
  {
    return !TextUtils.isEmpty((String)paramBw.d().get(paramString));
  }
  
  public static boolean d(String paramString)
  {
    int i = -1;
    for (;;)
    {
      try
      {
        switch (paramString.hashCode())
        {
        case 49: 
          di.b(String.format("name : %s not found", new Object[] { paramString }));
          return false;
        }
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        di.b(String.format("name : %s not found", new Object[] { paramString }));
        return false;
      }
      if (paramString.equals("1"))
      {
        i = 0;
        break label311;
        if (paramString.equals("2"))
        {
          i = 1;
          break label311;
          if (paramString.equals("gaid"))
          {
            i = 2;
            break label311;
            if (paramString.equals("4"))
            {
              i = 3;
              break label311;
              if (paramString.equals("3"))
              {
                i = 4;
                break label311;
                if (paramString.equals("6"))
                {
                  i = 5;
                  break label311;
                  if (paramString.equals("7"))
                  {
                    i = 6;
                    break label311;
                    if (paramString.equals("8"))
                    {
                      i = 7;
                      break label311;
                      Class.forName("com.google.android.gms.ads.AdView");
                      return true;
                      Class.forName("com.facebook.ads.AdView");
                      return true;
                      Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
                      return true;
                      Class.forName("com.vungle.publisher.VunglePub");
                      return true;
                      Class.forName("com.unity3d.ads.UnityAds");
                      return true;
                      Class.forName("com.adcolony.sdk.AdColony");
                      return true;
                      Class.forName("com.applovin.sdk.AppLovinSdk");
                      return true;
                      Class.forName("com.mopub.common.MoPub");
                      return true;
                    }
                  }
                }
              }
            }
          }
        }
      }
      label311:
      switch (i)
      {
      }
    }
  }
  
  public IBannerEvent a(ViewGroup paramViewGroup, String paramString, BannerAdListener paramBannerAdListener)
  {
    di.b("invoke getBannerEvent");
    return ba.b().c(paramViewGroup, paramString, paramBannerAdListener);
  }
  
  public IVideoEvent a(Activity paramActivity, String paramString, VideoAdListener paramVideoAdListener)
  {
    di.b("invoke getVideoEvent");
    return ba.b().d(paramActivity, paramString, paramVideoAdListener);
  }
  
  public INativeEvent b(Context paramContext, String paramString, NativeAdListener paramNativeAdListener)
  {
    di.b("invoke getNativeEvent");
    return ba.b().b(paramContext, paramString, paramNativeAdListener);
  }
  
  public boolean b(final Context paramContext)
  {
    di.b("invoke Core init");
    bw localBw = AdConfigHelper.getShellConfig(paramContext);
    if (localBw == null) {
      return false;
    }
    LoadExecutor.scheduleWithFixedDelay(new Runnable()
    {
      public void run()
      {
        cx.c(paramContext);
      }
    }, 0L, 1L, TimeUnit.DAYS);
    if (localBw.a() != 0) {
      bd.b = localBw.a();
    }
    di.b("LOADDELAY : " + bd.b);
    if (localBw.d().containsKey("1"))
    {
      if (!d(localBw, "1"))
      {
        di.b("empty admob appId");
        return false;
      }
      if (d("1")) {
        da.e(paramContext, localBw);
      }
    }
    if (localBw.d().containsKey("3"))
    {
      if (!d(localBw, "3"))
      {
        di.b("empty unity appId");
        return false;
      }
      if ((d("3")) && ((paramContext instanceof Activity))) {
        dk.c((Activity)paramContext, localBw);
      }
    }
    if (localBw.d().containsKey("4"))
    {
      if (!d(localBw, "4"))
      {
        di.b("empty vungle appId");
        return false;
      }
      if (d("4")) {
        dg.e(paramContext, localBw);
      }
    }
    if (localBw.d().containsKey("6"))
    {
      if (!d(localBw, "6"))
      {
        di.b("empty adcolony appId");
        return false;
      }
      if ((d("6")) && ((paramContext instanceof Activity))) {
        cw.b((Activity)paramContext, localBw);
      }
    }
    if (localBw.d().containsKey("7"))
    {
      if (!d(localBw, "7"))
      {
        di.b("empty applovin appId");
        return false;
      }
      if (d("7")) {
        db.e(paramContext, localBw);
      }
    }
    LoadExecutor.scheduleWithFixedDelay(new Runnable()
    {
      public void run()
      {
        try
        {
          cd.e().b(this.c);
          cb.c().c(this.c);
          return;
        }
        catch (Throwable localThrowable)
        {
          di.b("preloadAd error :", localThrowable);
        }
      }
    }, 1L, 120L, TimeUnit.SECONDS);
    return true;
  }
  
  public IInteractiveEvent c(Context paramContext, String paramString, InteractiveAdListener paramInteractiveAdListener)
  {
    di.b("invoke getInteractiveEvent");
    return ba.b().e(paramContext, paramString, paramInteractiveAdListener);
  }
  
  public IInterstitialEvent d(Context paramContext, String paramString, InterstitialAdListener paramInterstitialAdListener)
  {
    di.b("invoke getInterstitialEvent");
    return ba.b().e(paramContext, paramString, paramInterstitialAdListener);
  }
  
  public int e()
  {
    return 127;
  }
}
