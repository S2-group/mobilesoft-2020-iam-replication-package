package com.adt.a;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.aiming.mdt.sdk.ad.bannerad.BannerAdListener;
import com.aiming.mdt.sdk.ad.bannerad.IBannerEvent;
import com.aiming.mdt.sdk.ad.interactivead.IInteractiveEvent;
import com.aiming.mdt.sdk.ad.interactivead.InteractiveAdListener;
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

public class cr
{
  public cr() {}
  
  /* Error */
  private static void c(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 21
    //   3: iconst_0
    //   4: invokevirtual 27	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   7: astore 5
    //   9: new 29	java/text/SimpleDateFormat
    //   12: dup
    //   13: ldc 31
    //   15: getstatic 37	java/util/Locale:US	Ljava/util/Locale;
    //   18: invokespecial 40	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   21: new 42	java/util/Date
    //   24: dup
    //   25: invokespecial 43	java/util/Date:<init>	()V
    //   28: invokevirtual 49	java/text/DateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   31: astore 4
    //   33: aload 5
    //   35: aload 4
    //   37: iconst_0
    //   38: invokeinterface 55 3 0
    //   43: ifeq +9 -> 52
    //   46: ldc 57
    //   48: invokestatic 63	com/adt/a/df:b	(Ljava/lang/String;)V
    //   51: return
    //   52: aload_0
    //   53: invokestatic 69	com/aiming/mdt/sdk/AdtAds:getAppKey	(Landroid/content/Context;)Ljava/lang/String;
    //   56: astore_2
    //   57: new 71	com/adt/a/dk
    //   60: dup
    //   61: invokespecial 72	com/adt/a/dk:<init>	()V
    //   64: ldc 74
    //   66: aload_0
    //   67: invokestatic 80	com/adt/a/dj:e	(Landroid/content/Context;)Lcom/adt/a/dj;
    //   70: ldc 82
    //   72: invokevirtual 85	com/adt/a/dj:c	(Ljava/lang/String;)Ljava/lang/String;
    //   75: invokevirtual 89	com/adt/a/dk:d	(Ljava/lang/String;Ljava/lang/Object;)Lcom/adt/a/dk;
    //   78: ldc 91
    //   80: aload_2
    //   81: invokevirtual 89	com/adt/a/dk:d	(Ljava/lang/String;Ljava/lang/Object;)Lcom/adt/a/dk;
    //   84: ldc 93
    //   86: ldc 95
    //   88: invokevirtual 89	com/adt/a/dk:d	(Ljava/lang/String;Ljava/lang/Object;)Lcom/adt/a/dk;
    //   91: ldc 97
    //   93: ldc 99
    //   95: invokevirtual 89	com/adt/a/dk:d	(Ljava/lang/String;Ljava/lang/Object;)Lcom/adt/a/dk;
    //   98: astore_2
    //   99: aload_0
    //   100: getstatic 105	com/adt/a/br:a	Lcom/adt/a/br;
    //   103: invokestatic 111	com/aiming/mdt/sdk/shell/AdConfigHelper:getHost	(Landroid/content/Context;Lcom/adt/a/br;)Ljava/lang/String;
    //   106: astore_3
    //   107: new 113	java/lang/StringBuilder
    //   110: dup
    //   111: invokespecial 114	java/lang/StringBuilder:<init>	()V
    //   114: aload_3
    //   115: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: ldc 120
    //   120: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   123: aload_2
    //   124: invokevirtual 123	com/adt/a/dk:d	()Ljava/lang/String;
    //   127: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   133: astore_2
    //   134: new 113	java/lang/StringBuilder
    //   137: dup
    //   138: invokespecial 114	java/lang/StringBuilder:<init>	()V
    //   141: ldc -128
    //   143: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: aload_2
    //   147: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   153: invokestatic 63	com/adt/a/df:b	(Ljava/lang/String;)V
    //   156: new 113	java/lang/StringBuilder
    //   159: dup
    //   160: invokespecial 114	java/lang/StringBuilder:<init>	()V
    //   163: astore_3
    //   164: aload_0
    //   165: invokevirtual 132	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   168: iconst_0
    //   169: invokevirtual 138	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   172: invokeinterface 144 1 0
    //   177: astore_0
    //   178: aload_0
    //   179: invokeinterface 150 1 0
    //   184: ifeq +66 -> 250
    //   187: aload_0
    //   188: invokeinterface 154 1 0
    //   193: checkcast 156	android/content/pm/PackageInfo
    //   196: astore 6
    //   198: aload 6
    //   200: getfield 160	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   203: getfield 166	android/content/pm/ApplicationInfo:flags	I
    //   206: iconst_1
    //   207: iand
    //   208: ifgt -30 -> 178
    //   211: aload 6
    //   213: getfield 170	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   216: ldc -84
    //   218: invokevirtual 178	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   221: ifne -43 -> 178
    //   224: aload_3
    //   225: aload 6
    //   227: getfield 170	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   230: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   233: ldc -76
    //   235: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: pop
    //   239: goto -61 -> 178
    //   242: astore_0
    //   243: ldc -74
    //   245: aload_0
    //   246: invokestatic 185	com/adt/a/df:c	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   249: return
    //   250: aload_3
    //   251: iconst_0
    //   252: aload_3
    //   253: invokevirtual 189	java/lang/StringBuilder:length	()I
    //   256: iconst_1
    //   257: isub
    //   258: invokevirtual 193	java/lang/StringBuilder:substring	(II)Ljava/lang/String;
    //   261: astore_0
    //   262: aload_0
    //   263: invokestatic 63	com/adt/a/df:b	(Ljava/lang/String;)V
    //   266: aload_0
    //   267: ldc -61
    //   269: invokevirtual 199	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   272: invokestatic 202	com/adt/a/cr:e	([B)[B
    //   275: invokestatic 206	com/adt/a/dc:b	([B)[B
    //   278: astore 6
    //   280: aload 6
    //   282: ifnull +264 -> 546
    //   285: aconst_null
    //   286: astore_0
    //   287: aconst_null
    //   288: astore_3
    //   289: new 208	java/net/URL
    //   292: dup
    //   293: aload_2
    //   294: invokevirtual 211	java/lang/String:trim	()Ljava/lang/String;
    //   297: invokespecial 213	java/net/URL:<init>	(Ljava/lang/String;)V
    //   300: invokevirtual 217	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   303: checkcast 219	java/net/HttpURLConnection
    //   306: astore_2
    //   307: aload_2
    //   308: iconst_1
    //   309: invokevirtual 223	java/net/HttpURLConnection:setDoInput	(Z)V
    //   312: aload_2
    //   313: iconst_1
    //   314: invokevirtual 226	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   317: aload_2
    //   318: iconst_0
    //   319: invokevirtual 229	java/net/HttpURLConnection:setUseCaches	(Z)V
    //   322: aload_2
    //   323: ldc -25
    //   325: ldc -23
    //   327: invokevirtual 237	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   330: aload_2
    //   331: ldc -17
    //   333: ldc -15
    //   335: invokevirtual 237	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   338: aload_2
    //   339: sipush 30000
    //   342: invokevirtual 245	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   345: aload_2
    //   346: ldc -10
    //   348: invokevirtual 249	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   351: aload_2
    //   352: invokevirtual 252	java/net/HttpURLConnection:connect	()V
    //   355: aload_2
    //   356: invokevirtual 256	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   359: astore_0
    //   360: aload_0
    //   361: aload 6
    //   363: invokevirtual 262	java/io/OutputStream:write	([B)V
    //   366: aload_0
    //   367: invokevirtual 265	java/io/OutputStream:flush	()V
    //   370: aload_0
    //   371: invokevirtual 267	java/io/OutputStream:close	()V
    //   374: aload_2
    //   375: invokevirtual 270	java/net/HttpURLConnection:getResponseCode	()I
    //   378: istore_1
    //   379: new 113	java/lang/StringBuilder
    //   382: dup
    //   383: invokespecial 114	java/lang/StringBuilder:<init>	()V
    //   386: ldc_w 272
    //   389: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   392: iload_1
    //   393: invokevirtual 275	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   396: ldc_w 277
    //   399: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   402: aload_2
    //   403: invokevirtual 280	java/net/HttpURLConnection:getResponseMessage	()Ljava/lang/String;
    //   406: invokevirtual 118	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   409: invokevirtual 126	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   412: invokestatic 63	com/adt/a/df:b	(Ljava/lang/String;)V
    //   415: iload_1
    //   416: sipush 200
    //   419: if_icmpne +27 -> 446
    //   422: aload 5
    //   424: invokeinterface 284 1 0
    //   429: astore_0
    //   430: aload_0
    //   431: aload 4
    //   433: iconst_1
    //   434: invokeinterface 290 3 0
    //   439: pop
    //   440: aload_0
    //   441: invokeinterface 293 1 0
    //   446: aload_2
    //   447: ifnull +10 -> 457
    //   450: aload_2
    //   451: invokevirtual 297	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   454: invokevirtual 300	java/io/InputStream:close	()V
    //   457: aload_2
    //   458: invokestatic 305	com/aiming/mdt/sdk/util/IOUtil:close	(Ljava/net/HttpURLConnection;)V
    //   461: return
    //   462: astore_0
    //   463: aload_0
    //   464: invokevirtual 308	java/io/IOException:printStackTrace	()V
    //   467: goto -10 -> 457
    //   470: astore_0
    //   471: aload_3
    //   472: astore_2
    //   473: aload_0
    //   474: astore_3
    //   475: aload_2
    //   476: astore_0
    //   477: ldc_w 310
    //   480: aload_3
    //   481: invokestatic 185	com/adt/a/df:c	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   484: aload_2
    //   485: ifnull +10 -> 495
    //   488: aload_2
    //   489: invokevirtual 297	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   492: invokevirtual 300	java/io/InputStream:close	()V
    //   495: aload_2
    //   496: invokestatic 305	com/aiming/mdt/sdk/util/IOUtil:close	(Ljava/net/HttpURLConnection;)V
    //   499: return
    //   500: astore_0
    //   501: aload_0
    //   502: invokevirtual 308	java/io/IOException:printStackTrace	()V
    //   505: goto -10 -> 495
    //   508: astore_3
    //   509: aload_0
    //   510: astore_2
    //   511: aload_3
    //   512: astore_0
    //   513: aload_2
    //   514: ifnull +10 -> 524
    //   517: aload_2
    //   518: invokevirtual 297	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   521: invokevirtual 300	java/io/InputStream:close	()V
    //   524: aload_2
    //   525: invokestatic 305	com/aiming/mdt/sdk/util/IOUtil:close	(Ljava/net/HttpURLConnection;)V
    //   528: aload_0
    //   529: athrow
    //   530: astore_3
    //   531: aload_3
    //   532: invokevirtual 308	java/io/IOException:printStackTrace	()V
    //   535: goto -11 -> 524
    //   538: astore_0
    //   539: goto -26 -> 513
    //   542: astore_3
    //   543: goto -68 -> 475
    //   546: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	547	0	paramContext	Context
    //   378	42	1	i	int
    //   56	469	2	localObject1	Object
    //   106	375	3	localObject2	Object
    //   508	4	3	localObject3	Object
    //   530	2	3	localIOException	IOException
    //   542	1	3	localThrowable	Throwable
    //   31	401	4	str	String
    //   7	416	5	localSharedPreferences	android.content.SharedPreferences
    //   196	166	6	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   0	51	242	java/lang/Throwable
    //   52	178	242	java/lang/Throwable
    //   178	239	242	java/lang/Throwable
    //   250	280	242	java/lang/Throwable
    //   450	457	242	java/lang/Throwable
    //   457	461	242	java/lang/Throwable
    //   463	467	242	java/lang/Throwable
    //   488	495	242	java/lang/Throwable
    //   495	499	242	java/lang/Throwable
    //   501	505	242	java/lang/Throwable
    //   517	524	242	java/lang/Throwable
    //   524	530	242	java/lang/Throwable
    //   531	535	242	java/lang/Throwable
    //   450	457	462	java/io/IOException
    //   289	307	470	java/lang/Throwable
    //   488	495	500	java/io/IOException
    //   289	307	508	finally
    //   477	484	508	finally
    //   517	524	530	java/io/IOException
    //   307	415	538	finally
    //   422	446	538	finally
    //   307	415	542	java/lang/Throwable
    //   422	446	542	java/lang/Throwable
  }
  
  private static boolean d(bu paramBu, String paramString)
  {
    return !TextUtils.isEmpty((String)paramBu.c().get(paramString));
  }
  
  public static boolean e(String paramString)
  {
    int i = -1;
    for (;;)
    {
      try
      {
        switch (paramString.hashCode())
        {
        case 49: 
          df.b(String.format("name : %s not found", new Object[] { paramString }));
          return false;
        }
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        df.b(String.format("name : %s not found", new Object[] { paramString }));
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
  
  private static byte[] e(byte[] paramArrayOfByte)
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
        df.c("gzip compress error.", paramArrayOfByte);
      }
    }
  }
  
  public boolean a(final Context paramContext)
  {
    df.b("invoke Core init");
    bu localBu = AdConfigHelper.getShellConfig(paramContext);
    if (localBu == null) {
      return false;
    }
    LoadExecutor.scheduleWithFixedDelay(new Runnable()
    {
      public void run()
      {
        cr.e(paramContext);
      }
    }, 0L, 1L, TimeUnit.DAYS);
    if (localBu.a() != 0) {
      bc.d = localBu.a();
    }
    df.b("LOADDELAY : " + bc.d);
    if (localBu.c().containsKey("1"))
    {
      if (!d(localBu, "1"))
      {
        df.b("empty admob appId");
        return false;
      }
      if (e("1")) {
        ct.a(paramContext, localBu);
      }
    }
    if (localBu.c().containsKey("3"))
    {
      if (!d(localBu, "3"))
      {
        df.b("empty unity appId");
        return false;
      }
      if ((e("3")) && ((paramContext instanceof Activity))) {
        de.a((Activity)paramContext, localBu);
      }
    }
    if (localBu.c().containsKey("4"))
    {
      if (!d(localBu, "4"))
      {
        df.b("empty vungle appId");
        return false;
      }
      if (e("4")) {
        dd.d(paramContext, localBu);
      }
    }
    if (localBu.c().containsKey("6"))
    {
      if (!d(localBu, "6"))
      {
        df.b("empty adcolony appId");
        return false;
      }
      if ((e("6")) && ((paramContext instanceof Activity))) {
        cs.b((Activity)paramContext, localBu);
      }
    }
    if (localBu.c().containsKey("7"))
    {
      if (!d(localBu, "7"))
      {
        df.b("empty applovin appId");
        return false;
      }
      if (e("7")) {
        cy.a(paramContext, localBu);
      }
    }
    LoadExecutor.scheduleWithFixedDelay(new Runnable()
    {
      public void run()
      {
        try
        {
          cb.a().b(this.b);
          ca.a().d(this.b);
          return;
        }
        catch (Throwable localThrowable)
        {
          df.c("preloadAd error :", localThrowable);
        }
      }
    }, 1L, 120L, TimeUnit.SECONDS);
    return true;
  }
  
  public IBannerEvent b(ViewGroup paramViewGroup, String paramString, BannerAdListener paramBannerAdListener)
  {
    df.b("invoke getBannerEvent");
    return ba.d().d(paramViewGroup, paramString, paramBannerAdListener);
  }
  
  public IInterstitialEvent b(Context paramContext, String paramString, InterstitialAdListener paramInterstitialAdListener)
  {
    df.b("invoke getInterstitialEvent");
    return ba.d().b(paramContext, paramString, paramInterstitialAdListener);
  }
  
  public IVideoActivityEvent b(Context paramContext)
  {
    df.b("invoke getVideoActivityEvent");
    return new bq();
  }
  
  public int d()
  {
    return 127;
  }
  
  public IInterstitialActivityEvent d(Context paramContext)
  {
    df.b("invoke getInterstitialActivityEvent");
    return new am();
  }
  
  public IInteractiveEvent e(Context paramContext, String paramString, InteractiveAdListener paramInteractiveAdListener)
  {
    df.b("invoke getInteractiveEvent");
    return ba.d().a(paramContext, paramString, paramInteractiveAdListener);
  }
  
  public INativeEvent e(Context paramContext, String paramString, NativeAdListener paramNativeAdListener)
  {
    df.b("invoke getNativeEvent");
    return ba.d().a(paramContext, paramString, paramNativeAdListener);
  }
  
  public IVideoEvent e(Activity paramActivity, VideoAdListener paramVideoAdListener)
  {
    df.b("invoke getVideoEvent");
    return ba.d().a(paramActivity, paramVideoAdListener);
  }
}
