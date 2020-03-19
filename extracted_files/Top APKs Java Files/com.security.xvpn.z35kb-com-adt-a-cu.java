package com.adt.a;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.aiming.mdt.sdk.ad.IWebActivityEvent;
import com.aiming.mdt.sdk.ad.appwallad.AppwallAdListener;
import com.aiming.mdt.sdk.ad.appwallad.IAppwallActivityEvent;
import com.aiming.mdt.sdk.ad.appwallad.IAppwallEvent;
import com.aiming.mdt.sdk.ad.bannerad.BannerAdListener;
import com.aiming.mdt.sdk.ad.bannerad.IBannerEvent;
import com.aiming.mdt.sdk.ad.interstitialAd.IInterstitialActivityEvent;
import com.aiming.mdt.sdk.ad.interstitialAd.IInterstitialEvent;
import com.aiming.mdt.sdk.ad.interstitialAd.InterstitialAdListener;
import com.aiming.mdt.sdk.ad.nativead.INativeEvent;
import com.aiming.mdt.sdk.ad.nativead.NativeAdListener;
import com.aiming.mdt.sdk.ad.videoad.VideoAdListener;
import com.aiming.mdt.sdk.ad.videoad.event.IVideoActivityEvent;
import com.aiming.mdt.sdk.ad.videoad.event.IVideoEvent;
import com.aiming.mdt.sdk.shell.AdConfigHelper;
import com.aiming.mdt.sdk.shell.LoadExecutor;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPOutputStream;

public class cu
{
  public cu() {}
  
  public static boolean b(String paramString)
  {
    i = -1;
    try
    {
      j = paramString.hashCode();
      if (j != 3165045) {}
      switch (j)
      {
      case 52: 
        if (!paramString.equals("8")) {
          break;
        }
        i = 7;
        break;
        if (!paramString.equals("7")) {
          break;
        }
        i = 6;
        break;
        if (!paramString.equals("6")) {
          break;
        }
        i = 5;
        break;
        if (!paramString.equals("4")) {
          break;
        }
        i = 3;
        break;
      case 51: 
        if (!paramString.equals("3")) {
          break;
        }
        i = 4;
        break;
      case 50: 
        if (!paramString.equals("2")) {
          break;
        }
        i = 1;
        break;
      case 49: 
        if (!paramString.equals("1")) {
          break;
        }
        i = 0;
        break;
        if (!paramString.equals("gaid")) {
          break;
        }
        i = 2;
      }
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      for (;;)
      {
        int j;
        continue;
        switch (j)
        {
        }
        switch (i)
        {
        }
      }
    }
    Class.forName("com.mopub.common.MoPub");
    return true;
    Class.forName("com.applovin.sdk.AppLovinSdk");
    return true;
    Class.forName("com.adcolony.sdk.AdColony");
    return true;
    Class.forName("com.unity3d.ads.UnityAds");
    return true;
    Class.forName("com.vungle.publisher.VunglePub");
    return true;
    Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
    return true;
    Class.forName("com.facebook.ads.AdView");
    return true;
    Class.forName("com.google.android.gms.ads.AdView");
    return true;
    di.e(String.format("name : %s not found", new Object[] { paramString }));
    return false;
    di.e(String.format("name : %s not found", new Object[] { paramString }));
    return false;
  }
  
  private static byte[] c(byte[] paramArrayOfByte)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      GZIPOutputStream localGZIPOutputStream = new GZIPOutputStream(localByteArrayOutputStream);
      localGZIPOutputStream.write(paramArrayOfByte);
      localGZIPOutputStream.close();
    }
    catch (IOException paramArrayOfByte)
    {
      di.e("gzip compress error.", paramArrayOfByte);
    }
    return localByteArrayOutputStream.toByteArray();
  }
  
  private static boolean e(bs paramBs, String paramString)
  {
    return TextUtils.isEmpty((String)paramBs.a().get(paramString)) ^ true;
  }
  
  /* Error */
  private static void j(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc -122
    //   3: iconst_0
    //   4: invokevirtual 140	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   7: astore 5
    //   9: new 142	java/text/SimpleDateFormat
    //   12: dup
    //   13: ldc -112
    //   15: getstatic 150	java/util/Locale:US	Ljava/util/Locale;
    //   18: invokespecial 153	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   21: new 155	java/util/Date
    //   24: dup
    //   25: invokespecial 156	java/util/Date:<init>	()V
    //   28: invokevirtual 161	java/text/DateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   31: astore_3
    //   32: aload 5
    //   34: aload_3
    //   35: iconst_0
    //   36: invokeinterface 167 3 0
    //   41: ifeq +9 -> 50
    //   44: ldc -87
    //   46: invokestatic 78	com/adt/a/di:e	(Ljava/lang/String;)V
    //   49: return
    //   50: aload_0
    //   51: invokestatic 175	com/aiming/mdt/sdk/AdtAds:getAppKey	(Landroid/content/Context;)Ljava/lang/String;
    //   54: astore_2
    //   55: new 177	com/adt/a/do
    //   58: dup
    //   59: invokespecial 178	com/adt/a/do:<init>	()V
    //   62: ldc -76
    //   64: aload_0
    //   65: invokestatic 186	com/adt/a/dp:d	(Landroid/content/Context;)Lcom/adt/a/dp;
    //   68: ldc 44
    //   70: invokevirtual 189	com/adt/a/dp:d	(Ljava/lang/String;)Ljava/lang/String;
    //   73: invokevirtual 192	com/adt/a/do:a	(Ljava/lang/String;Ljava/lang/Object;)Lcom/adt/a/do;
    //   76: ldc -62
    //   78: aload_2
    //   79: invokevirtual 192	com/adt/a/do:a	(Ljava/lang/String;Ljava/lang/Object;)Lcom/adt/a/do;
    //   82: ldc -60
    //   84: ldc 42
    //   86: invokevirtual 192	com/adt/a/do:a	(Ljava/lang/String;Ljava/lang/Object;)Lcom/adt/a/do;
    //   89: ldc -58
    //   91: ldc -56
    //   93: invokevirtual 192	com/adt/a/do:a	(Ljava/lang/String;Ljava/lang/Object;)Lcom/adt/a/do;
    //   96: astore_2
    //   97: aload_0
    //   98: getstatic 205	com/adt/a/bw:d	Lcom/adt/a/bw;
    //   101: invokestatic 211	com/aiming/mdt/sdk/shell/AdConfigHelper:getHost	(Landroid/content/Context;Lcom/adt/a/bw;)Ljava/lang/String;
    //   104: astore 4
    //   106: new 213	java/lang/StringBuilder
    //   109: dup
    //   110: invokespecial 214	java/lang/StringBuilder:<init>	()V
    //   113: astore 6
    //   115: aload 6
    //   117: aload 4
    //   119: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   122: pop
    //   123: aload 6
    //   125: ldc -36
    //   127: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: pop
    //   131: aload 6
    //   133: aload_2
    //   134: invokevirtual 223	com/adt/a/do:e	()Ljava/lang/String;
    //   137: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: pop
    //   141: aload 6
    //   143: invokevirtual 226	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   146: astore_2
    //   147: new 213	java/lang/StringBuilder
    //   150: dup
    //   151: invokespecial 214	java/lang/StringBuilder:<init>	()V
    //   154: astore 4
    //   156: aload 4
    //   158: ldc -28
    //   160: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   163: pop
    //   164: aload 4
    //   166: aload_2
    //   167: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   170: pop
    //   171: aload 4
    //   173: invokevirtual 226	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   176: invokestatic 78	com/adt/a/di:e	(Ljava/lang/String;)V
    //   179: new 213	java/lang/StringBuilder
    //   182: dup
    //   183: invokespecial 214	java/lang/StringBuilder:<init>	()V
    //   186: astore 4
    //   188: aload_0
    //   189: invokevirtual 232	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   192: iconst_0
    //   193: invokevirtual 238	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   196: invokeinterface 244 1 0
    //   201: astore_0
    //   202: aload_0
    //   203: invokeinterface 250 1 0
    //   208: ifeq +67 -> 275
    //   211: aload_0
    //   212: invokeinterface 254 1 0
    //   217: checkcast 256	android/content/pm/PackageInfo
    //   220: astore 6
    //   222: iconst_1
    //   223: aload 6
    //   225: getfield 260	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   228: getfield 266	android/content/pm/ApplicationInfo:flags	I
    //   231: iand
    //   232: ifgt -30 -> 202
    //   235: aload 6
    //   237: getfield 270	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   240: ldc_w 272
    //   243: invokevirtual 275	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   246: ifeq +6 -> 252
    //   249: goto -47 -> 202
    //   252: aload 4
    //   254: aload 6
    //   256: getfield 270	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   259: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   262: pop
    //   263: aload 4
    //   265: ldc_w 277
    //   268: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: pop
    //   272: goto -70 -> 202
    //   275: aload 4
    //   277: iconst_0
    //   278: aload 4
    //   280: invokevirtual 280	java/lang/StringBuilder:length	()I
    //   283: iconst_1
    //   284: isub
    //   285: invokevirtual 284	java/lang/StringBuilder:substring	(II)Ljava/lang/String;
    //   288: astore_0
    //   289: aload_0
    //   290: invokestatic 78	com/adt/a/di:e	(Ljava/lang/String;)V
    //   293: aload_0
    //   294: ldc_w 286
    //   297: invokevirtual 290	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   300: invokestatic 292	com/adt/a/cu:c	([B)[B
    //   303: invokestatic 296	com/adt/a/de:e	([B)[B
    //   306: astore 6
    //   308: aload 6
    //   310: ifnonnull +4 -> 314
    //   313: return
    //   314: aconst_null
    //   315: astore 4
    //   317: aconst_null
    //   318: astore_0
    //   319: new 298	java/net/URL
    //   322: dup
    //   323: aload_2
    //   324: invokevirtual 301	java/lang/String:trim	()Ljava/lang/String;
    //   327: invokespecial 303	java/net/URL:<init>	(Ljava/lang/String;)V
    //   330: invokevirtual 307	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   333: checkcast 309	java/net/HttpURLConnection
    //   336: astore_2
    //   337: aload_2
    //   338: iconst_1
    //   339: invokevirtual 313	java/net/HttpURLConnection:setDoInput	(Z)V
    //   342: aload_2
    //   343: iconst_1
    //   344: invokevirtual 316	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   347: aload_2
    //   348: iconst_0
    //   349: invokevirtual 319	java/net/HttpURLConnection:setUseCaches	(Z)V
    //   352: aload_2
    //   353: ldc_w 321
    //   356: ldc_w 322
    //   359: invokevirtual 326	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   362: aload_2
    //   363: ldc_w 328
    //   366: ldc_w 330
    //   369: invokevirtual 326	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   372: aload_2
    //   373: sipush 30000
    //   376: invokevirtual 334	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   379: aload_2
    //   380: ldc_w 335
    //   383: invokevirtual 338	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   386: aload_2
    //   387: invokevirtual 341	java/net/HttpURLConnection:connect	()V
    //   390: aload_2
    //   391: invokevirtual 345	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   394: astore_0
    //   395: aload_0
    //   396: aload 6
    //   398: invokevirtual 348	java/io/OutputStream:write	([B)V
    //   401: aload_0
    //   402: invokevirtual 351	java/io/OutputStream:flush	()V
    //   405: aload_0
    //   406: invokevirtual 352	java/io/OutputStream:close	()V
    //   409: aload_2
    //   410: invokevirtual 355	java/net/HttpURLConnection:getResponseCode	()I
    //   413: istore_1
    //   414: new 213	java/lang/StringBuilder
    //   417: dup
    //   418: invokespecial 214	java/lang/StringBuilder:<init>	()V
    //   421: astore_0
    //   422: aload_0
    //   423: ldc_w 357
    //   426: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   429: pop
    //   430: aload_0
    //   431: iload_1
    //   432: invokevirtual 360	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   435: pop
    //   436: aload_0
    //   437: ldc_w 362
    //   440: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   443: pop
    //   444: aload_0
    //   445: aload_2
    //   446: invokevirtual 365	java/net/HttpURLConnection:getResponseMessage	()Ljava/lang/String;
    //   449: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   452: pop
    //   453: aload_0
    //   454: invokevirtual 226	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   457: invokestatic 78	com/adt/a/di:e	(Ljava/lang/String;)V
    //   460: iload_1
    //   461: sipush 200
    //   464: if_icmpne +26 -> 490
    //   467: aload 5
    //   469: invokeinterface 369 1 0
    //   474: astore_0
    //   475: aload_0
    //   476: aload_3
    //   477: iconst_1
    //   478: invokeinterface 375 3 0
    //   483: pop
    //   484: aload_0
    //   485: invokeinterface 378 1 0
    //   490: aload_2
    //   491: ifnull +18 -> 509
    //   494: aload_2
    //   495: invokevirtual 382	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   498: invokevirtual 385	java/io/InputStream:close	()V
    //   501: goto +8 -> 509
    //   504: astore_0
    //   505: aload_0
    //   506: invokevirtual 388	java/io/IOException:printStackTrace	()V
    //   509: aload_2
    //   510: invokestatic 393	com/aiming/mdt/sdk/util/IOUtil:close	(Ljava/net/HttpURLConnection;)V
    //   513: return
    //   514: astore_0
    //   515: goto +52 -> 567
    //   518: astore_3
    //   519: goto +15 -> 534
    //   522: astore_3
    //   523: aload_0
    //   524: astore_2
    //   525: aload_3
    //   526: astore_0
    //   527: goto +40 -> 567
    //   530: astore_3
    //   531: aload 4
    //   533: astore_2
    //   534: aload_2
    //   535: astore_0
    //   536: ldc_w 395
    //   539: aload_3
    //   540: invokestatic 102	com/adt/a/di:e	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   543: aload_2
    //   544: ifnull +18 -> 562
    //   547: aload_2
    //   548: invokevirtual 382	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   551: invokevirtual 385	java/io/InputStream:close	()V
    //   554: goto +8 -> 562
    //   557: astore_0
    //   558: aload_0
    //   559: invokevirtual 388	java/io/IOException:printStackTrace	()V
    //   562: aload_2
    //   563: invokestatic 393	com/aiming/mdt/sdk/util/IOUtil:close	(Ljava/net/HttpURLConnection;)V
    //   566: return
    //   567: aload_2
    //   568: ifnull +18 -> 586
    //   571: aload_2
    //   572: invokevirtual 382	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   575: invokevirtual 385	java/io/InputStream:close	()V
    //   578: goto +8 -> 586
    //   581: astore_3
    //   582: aload_3
    //   583: invokevirtual 388	java/io/IOException:printStackTrace	()V
    //   586: aload_2
    //   587: invokestatic 393	com/aiming/mdt/sdk/util/IOUtil:close	(Ljava/net/HttpURLConnection;)V
    //   590: aload_0
    //   591: athrow
    //   592: astore_0
    //   593: ldc_w 397
    //   596: aload_0
    //   597: invokestatic 102	com/adt/a/di:e	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   600: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	601	0	paramContext	Context
    //   413	52	1	i	int
    //   54	533	2	localObject1	Object
    //   31	446	3	str	String
    //   518	1	3	localThrowable1	Throwable
    //   522	4	3	localObject2	Object
    //   530	10	3	localThrowable2	Throwable
    //   581	2	3	localIOException	IOException
    //   104	428	4	localObject3	Object
    //   7	461	5	localSharedPreferences	android.content.SharedPreferences
    //   113	284	6	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   494	501	504	java/io/IOException
    //   337	460	514	finally
    //   467	490	514	finally
    //   337	460	518	java/lang/Throwable
    //   467	490	518	java/lang/Throwable
    //   319	337	522	finally
    //   536	543	522	finally
    //   319	337	530	java/lang/Throwable
    //   547	554	557	java/io/IOException
    //   571	578	581	java/io/IOException
    //   0	49	592	java/lang/Throwable
    //   50	202	592	java/lang/Throwable
    //   202	249	592	java/lang/Throwable
    //   252	272	592	java/lang/Throwable
    //   275	308	592	java/lang/Throwable
    //   494	501	592	java/lang/Throwable
    //   505	509	592	java/lang/Throwable
    //   509	513	592	java/lang/Throwable
    //   547	554	592	java/lang/Throwable
    //   558	562	592	java/lang/Throwable
    //   562	566	592	java/lang/Throwable
    //   571	578	592	java/lang/Throwable
    //   582	586	592	java/lang/Throwable
    //   586	592	592	java/lang/Throwable
  }
  
  public int a()
  {
    return 127;
  }
  
  public IBannerEvent a(ViewGroup paramViewGroup, String paramString, BannerAdListener paramBannerAdListener)
  {
    di.e("invoke getBannerEvent");
    return bb.b().e(paramViewGroup, paramString, paramBannerAdListener);
  }
  
  public IInterstitialActivityEvent a(Context paramContext)
  {
    di.e("invoke getInterstitialActivityEvent");
    return new ar();
  }
  
  public INativeEvent a(Context paramContext, String paramString, NativeAdListener paramNativeAdListener)
  {
    di.e("invoke getNativeEvent");
    return bb.b().b(paramContext, paramString, paramNativeAdListener);
  }
  
  public IInterstitialEvent b(Context paramContext, String paramString, InterstitialAdListener paramInterstitialAdListener)
  {
    di.e("invoke getInterstitialEvent");
    return bb.b().c(paramContext, paramString, paramInterstitialAdListener);
  }
  
  public IVideoActivityEvent b(Context paramContext)
  {
    di.e("invoke getVideoActivityEvent");
    return new bn();
  }
  
  public IAppwallActivityEvent c(Context paramContext)
  {
    di.e("invoke getAppwallActivityEvent");
    return new ab();
  }
  
  public IAppwallEvent c(Context paramContext, String paramString, AppwallAdListener paramAppwallAdListener)
  {
    di.e("invoke getAppwallEvent");
    return bb.b().e(paramContext, paramString, paramAppwallAdListener);
  }
  
  public IWebActivityEvent d(Context paramContext)
  {
    di.e("invoke getWebActivityEvent");
    return new bf();
  }
  
  public IVideoEvent e(Activity paramActivity, VideoAdListener paramVideoAdListener)
  {
    di.e("invoke getVideoEvent");
    return bb.b().c(paramActivity, paramVideoAdListener);
  }
  
  public boolean e(final Context paramContext)
  {
    di.e("invoke Core init");
    bs localBs = AdConfigHelper.getShellConfig(paramContext);
    if (localBs == null) {
      return false;
    }
    LoadExecutor.scheduleWithFixedDelay(new Runnable()
    {
      public void run()
      {
        cu.h(paramContext);
      }
    }, 0L, 1L, TimeUnit.DAYS);
    if (localBs.e() != 0) {
      be.b = localBs.e();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("LOADDELAY : ");
    localStringBuilder.append(be.b);
    di.e(localStringBuilder.toString());
    if (localBs.a().containsKey("1"))
    {
      if (!e(localBs, "1"))
      {
        di.e("empty admob appId");
        return false;
      }
      if (b("1")) {
        cz.d(paramContext, localBs);
      }
    }
    if (localBs.a().containsKey("3"))
    {
      if (!e(localBs, "3"))
      {
        di.e("empty unity appId");
        return false;
      }
      if ((b("3")) && ((paramContext instanceof Activity))) {
        df.a((Activity)paramContext, localBs);
      }
    }
    if (localBs.a().containsKey("4"))
    {
      if (!e(localBs, "4"))
      {
        di.e("empty vungle appId");
        return false;
      }
      if (b("4")) {
        dd.c(paramContext, localBs);
      }
    }
    if (localBs.a().containsKey("6"))
    {
      if (!e(localBs, "6"))
      {
        di.e("empty adcolony appId");
        return false;
      }
      if ((b("6")) && ((paramContext instanceof Activity))) {
        da.e((Activity)paramContext, localBs);
      }
    }
    if (localBs.a().containsKey("7"))
    {
      if (!e(localBs, "7"))
      {
        di.e("empty applovin appId");
        return false;
      }
      if (b("7")) {
        cw.b(paramContext, localBs);
      }
    }
    LoadExecutor.scheduleWithFixedDelay(new Runnable()
    {
      public void run()
      {
        try
        {
          bz.e().a(this.e);
          return;
        }
        catch (Throwable localThrowable)
        {
          di.e("preloadAd error :", localThrowable);
        }
      }
    }, 1L, 120L, TimeUnit.SECONDS);
    return true;
  }
}
