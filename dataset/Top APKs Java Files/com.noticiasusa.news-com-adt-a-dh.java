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
import com.aiming.mdt.sdk.ad.videoad.event.IVideoEvent;
import com.aiming.mdt.sdk.shell.AdConfigHelper;
import com.aiming.mdt.sdk.shell.LoadExecutor;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPOutputStream;

public class dh
{
  public dh() {}
  
  private static boolean b(cf paramCf, String paramString)
  {
    return !TextUtils.isEmpty((String)paramCf.e().get(paramString));
  }
  
  private static byte[] c(byte[] paramArrayOfByte)
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
        dz.a("gzip compress error.", paramArrayOfByte);
      }
    }
  }
  
  /* Error */
  private static void d(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 74
    //   3: iconst_0
    //   4: invokevirtual 80	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   7: astore 5
    //   9: new 82	java/text/SimpleDateFormat
    //   12: dup
    //   13: ldc 84
    //   15: getstatic 90	java/util/Locale:US	Ljava/util/Locale;
    //   18: invokespecial 93	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;Ljava/util/Locale;)V
    //   21: new 95	java/util/Date
    //   24: dup
    //   25: invokespecial 96	java/util/Date:<init>	()V
    //   28: invokevirtual 102	java/text/DateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   31: astore 4
    //   33: aload 5
    //   35: aload 4
    //   37: iconst_0
    //   38: invokeinterface 108 3 0
    //   43: ifeq +9 -> 52
    //   46: ldc 110
    //   48: invokestatic 113	com/adt/a/dz:c	(Ljava/lang/String;)V
    //   51: return
    //   52: aload_0
    //   53: invokestatic 119	com/aiming/mdt/sdk/AdtAds:getAppKey	(Landroid/content/Context;)Ljava/lang/String;
    //   56: astore_2
    //   57: new 121	com/adt/a/ee
    //   60: dup
    //   61: invokespecial 122	com/adt/a/ee:<init>	()V
    //   64: ldc 124
    //   66: aload_0
    //   67: invokestatic 129	com/adt/a/ec:e	(Landroid/content/Context;)Lcom/adt/a/ec;
    //   70: ldc -125
    //   72: invokevirtual 134	com/adt/a/ec:c	(Ljava/lang/String;)Ljava/lang/String;
    //   75: invokevirtual 137	com/adt/a/ee:a	(Ljava/lang/String;Ljava/lang/Object;)Lcom/adt/a/ee;
    //   78: ldc -117
    //   80: aload_2
    //   81: invokevirtual 137	com/adt/a/ee:a	(Ljava/lang/String;Ljava/lang/Object;)Lcom/adt/a/ee;
    //   84: ldc -115
    //   86: ldc -113
    //   88: invokevirtual 137	com/adt/a/ee:a	(Ljava/lang/String;Ljava/lang/Object;)Lcom/adt/a/ee;
    //   91: ldc -111
    //   93: ldc -109
    //   95: invokevirtual 137	com/adt/a/ee:a	(Ljava/lang/String;Ljava/lang/Object;)Lcom/adt/a/ee;
    //   98: astore_2
    //   99: aload_0
    //   100: getstatic 152	com/adt/a/ca:b	Lcom/adt/a/ca;
    //   103: invokestatic 158	com/aiming/mdt/sdk/shell/AdConfigHelper:getHost	(Landroid/content/Context;Lcom/adt/a/ca;)Ljava/lang/String;
    //   106: astore_3
    //   107: new 160	java/lang/StringBuilder
    //   110: dup
    //   111: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   114: aload_3
    //   115: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: ldc -89
    //   120: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   123: aload_2
    //   124: invokevirtual 170	com/adt/a/ee:a	()Ljava/lang/String;
    //   127: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   133: astore_2
    //   134: new 160	java/lang/StringBuilder
    //   137: dup
    //   138: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   141: ldc -81
    //   143: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: aload_2
    //   147: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   153: invokestatic 113	com/adt/a/dz:c	(Ljava/lang/String;)V
    //   156: new 160	java/lang/StringBuilder
    //   159: dup
    //   160: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   163: astore_3
    //   164: aload_0
    //   165: invokevirtual 179	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   168: iconst_0
    //   169: invokevirtual 185	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   172: invokeinterface 191 1 0
    //   177: astore_0
    //   178: aload_0
    //   179: invokeinterface 197 1 0
    //   184: ifeq +66 -> 250
    //   187: aload_0
    //   188: invokeinterface 201 1 0
    //   193: checkcast 203	android/content/pm/PackageInfo
    //   196: astore 6
    //   198: aload 6
    //   200: getfield 207	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   203: getfield 213	android/content/pm/ApplicationInfo:flags	I
    //   206: iconst_1
    //   207: iand
    //   208: ifgt -30 -> 178
    //   211: aload 6
    //   213: getfield 217	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   216: ldc -37
    //   218: invokevirtual 223	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   221: ifne -43 -> 178
    //   224: aload_3
    //   225: aload 6
    //   227: getfield 217	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   230: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   233: ldc -31
    //   235: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: pop
    //   239: goto -61 -> 178
    //   242: astore_0
    //   243: ldc -29
    //   245: aload_0
    //   246: invokestatic 68	com/adt/a/dz:a	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   249: return
    //   250: aload_3
    //   251: iconst_0
    //   252: aload_3
    //   253: invokevirtual 231	java/lang/StringBuilder:length	()I
    //   256: iconst_1
    //   257: isub
    //   258: invokevirtual 235	java/lang/StringBuilder:substring	(II)Ljava/lang/String;
    //   261: astore_0
    //   262: aload_0
    //   263: invokestatic 113	com/adt/a/dz:c	(Ljava/lang/String;)V
    //   266: aload_0
    //   267: ldc -19
    //   269: invokevirtual 241	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   272: invokestatic 243	com/adt/a/dh:c	([B)[B
    //   275: invokestatic 247	com/adt/a/ds:a	([B)[B
    //   278: astore 6
    //   280: aload 6
    //   282: ifnull +269 -> 551
    //   285: aconst_null
    //   286: astore_0
    //   287: aconst_null
    //   288: astore_3
    //   289: new 249	java/net/URL
    //   292: dup
    //   293: aload_2
    //   294: invokevirtual 252	java/lang/String:trim	()Ljava/lang/String;
    //   297: invokespecial 254	java/net/URL:<init>	(Ljava/lang/String;)V
    //   300: invokevirtual 258	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   303: checkcast 260	java/net/HttpURLConnection
    //   306: astore_2
    //   307: aload_2
    //   308: iconst_1
    //   309: invokevirtual 264	java/net/HttpURLConnection:setDoInput	(Z)V
    //   312: aload_2
    //   313: iconst_1
    //   314: invokevirtual 267	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   317: aload_2
    //   318: iconst_0
    //   319: invokevirtual 270	java/net/HttpURLConnection:setUseCaches	(Z)V
    //   322: aload_2
    //   323: ldc_w 272
    //   326: ldc_w 273
    //   329: invokevirtual 277	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   332: aload_2
    //   333: ldc_w 279
    //   336: ldc_w 281
    //   339: invokevirtual 277	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   342: aload_2
    //   343: sipush 30000
    //   346: invokevirtual 285	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   349: aload_2
    //   350: ldc_w 286
    //   353: invokevirtual 289	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   356: aload_2
    //   357: invokevirtual 292	java/net/HttpURLConnection:connect	()V
    //   360: aload_2
    //   361: invokevirtual 296	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   364: astore_0
    //   365: aload_0
    //   366: aload 6
    //   368: invokevirtual 299	java/io/OutputStream:write	([B)V
    //   371: aload_0
    //   372: invokevirtual 302	java/io/OutputStream:flush	()V
    //   375: aload_0
    //   376: invokevirtual 303	java/io/OutputStream:close	()V
    //   379: aload_2
    //   380: invokevirtual 306	java/net/HttpURLConnection:getResponseCode	()I
    //   383: istore_1
    //   384: new 160	java/lang/StringBuilder
    //   387: dup
    //   388: invokespecial 161	java/lang/StringBuilder:<init>	()V
    //   391: ldc_w 308
    //   394: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   397: iload_1
    //   398: invokevirtual 311	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   401: ldc_w 313
    //   404: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   407: aload_2
    //   408: invokevirtual 316	java/net/HttpURLConnection:getResponseMessage	()Ljava/lang/String;
    //   411: invokevirtual 165	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   414: invokevirtual 173	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   417: invokestatic 113	com/adt/a/dz:c	(Ljava/lang/String;)V
    //   420: iload_1
    //   421: sipush 200
    //   424: if_icmpne +27 -> 451
    //   427: aload 5
    //   429: invokeinterface 320 1 0
    //   434: astore_0
    //   435: aload_0
    //   436: aload 4
    //   438: iconst_1
    //   439: invokeinterface 326 3 0
    //   444: pop
    //   445: aload_0
    //   446: invokeinterface 329 1 0
    //   451: aload_2
    //   452: ifnull +10 -> 462
    //   455: aload_2
    //   456: invokevirtual 333	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   459: invokevirtual 336	java/io/InputStream:close	()V
    //   462: aload_2
    //   463: invokestatic 341	com/aiming/mdt/sdk/util/IOUtil:close	(Ljava/net/HttpURLConnection;)V
    //   466: return
    //   467: astore_0
    //   468: aload_0
    //   469: invokestatic 346	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:a	(Ljava/lang/Throwable;)V
    //   472: goto -10 -> 462
    //   475: astore_0
    //   476: aload_3
    //   477: astore_2
    //   478: aload_0
    //   479: astore_3
    //   480: aload_2
    //   481: astore_0
    //   482: ldc_w 348
    //   485: aload_3
    //   486: invokestatic 68	com/adt/a/dz:a	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   489: aload_2
    //   490: ifnull +10 -> 500
    //   493: aload_2
    //   494: invokevirtual 333	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   497: invokevirtual 336	java/io/InputStream:close	()V
    //   500: aload_2
    //   501: invokestatic 341	com/aiming/mdt/sdk/util/IOUtil:close	(Ljava/net/HttpURLConnection;)V
    //   504: return
    //   505: astore_0
    //   506: aload_0
    //   507: invokestatic 346	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:a	(Ljava/lang/Throwable;)V
    //   510: goto -10 -> 500
    //   513: astore_3
    //   514: aload_0
    //   515: astore_2
    //   516: aload_3
    //   517: astore_0
    //   518: aload_2
    //   519: ifnull +10 -> 529
    //   522: aload_2
    //   523: invokevirtual 333	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   526: invokevirtual 336	java/io/InputStream:close	()V
    //   529: aload_2
    //   530: invokestatic 341	com/aiming/mdt/sdk/util/IOUtil:close	(Ljava/net/HttpURLConnection;)V
    //   533: aload_0
    //   534: athrow
    //   535: astore_3
    //   536: aload_3
    //   537: invokestatic 346	com/google/devtools/build/android/desugar/runtime/ThrowableExtension:a	(Ljava/lang/Throwable;)V
    //   540: goto -11 -> 529
    //   543: astore_0
    //   544: goto -26 -> 518
    //   547: astore_3
    //   548: goto -68 -> 480
    //   551: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	552	0	paramContext	Context
    //   383	42	1	i	int
    //   56	474	2	localObject1	Object
    //   106	380	3	localObject2	Object
    //   513	4	3	localObject3	Object
    //   535	2	3	localIOException	IOException
    //   547	1	3	localThrowable	Throwable
    //   31	406	4	str	String
    //   7	421	5	localSharedPreferences	android.content.SharedPreferences
    //   196	171	6	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   0	51	242	java/lang/Throwable
    //   52	178	242	java/lang/Throwable
    //   178	239	242	java/lang/Throwable
    //   250	280	242	java/lang/Throwable
    //   455	462	242	java/lang/Throwable
    //   462	466	242	java/lang/Throwable
    //   468	472	242	java/lang/Throwable
    //   493	500	242	java/lang/Throwable
    //   500	504	242	java/lang/Throwable
    //   506	510	242	java/lang/Throwable
    //   522	529	242	java/lang/Throwable
    //   529	535	242	java/lang/Throwable
    //   536	540	242	java/lang/Throwable
    //   455	462	467	java/io/IOException
    //   289	307	475	java/lang/Throwable
    //   493	500	505	java/io/IOException
    //   289	307	513	finally
    //   482	489	513	finally
    //   522	529	535	java/io/IOException
    //   307	420	543	finally
    //   427	451	543	finally
    //   307	420	547	java/lang/Throwable
    //   427	451	547	java/lang/Throwable
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
          dz.c(String.format("name : %s not found", new Object[] { paramString }));
          return false;
        }
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        dz.c(String.format("name : %s not found", new Object[] { paramString }));
        return false;
      }
      if (paramString.equals("1"))
      {
        i = 0;
        break label377;
        if (paramString.equals("2"))
        {
          i = 1;
          break label377;
          if (paramString.equals("gaid"))
          {
            i = 2;
            break label377;
            if (paramString.equals("4"))
            {
              i = 3;
              break label377;
              if (paramString.equals("3"))
              {
                i = 4;
                break label377;
                if (paramString.equals("6"))
                {
                  i = 5;
                  break label377;
                  if (paramString.equals("7"))
                  {
                    i = 6;
                    break label377;
                    if (paramString.equals("8"))
                    {
                      i = 7;
                      break label377;
                      if (paramString.equals("10"))
                      {
                        i = 8;
                        break label377;
                        if (paramString.equals("11"))
                        {
                          i = 9;
                          break label377;
                          Class.forName("com.google.android.gms.ads.AdView");
                          return true;
                          Class.forName("com.facebook.ads.AdView");
                          return true;
                          Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
                          return true;
                          Class.forName("com.vungle.warren.Vungle");
                          return true;
                          Class.forName("com.unity3d.ads.UnityAds");
                          return true;
                          Class.forName("com.adcolony.sdk.AdColony");
                          return true;
                          Class.forName("com.applovin.sdk.AppLovinSdk");
                          return true;
                          Class.forName("com.mopub.common.MoPub");
                          return true;
                          Class.forName("com.tapjoy.Tapjoy");
                          return true;
                          Class.forName("com.chartboost.sdk.Chartboost");
                          return true;
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
      label377:
      switch (i)
      {
      }
    }
  }
  
  public IBannerEvent a(ViewGroup paramViewGroup, String paramString, BannerAdListener paramBannerAdListener)
  {
    dz.c("invoke getBannerEvent");
    return bg.d().c(paramViewGroup, paramString, paramBannerAdListener);
  }
  
  public IInterstitialEvent b(Context paramContext, String paramString, InterstitialAdListener paramInterstitialAdListener)
  {
    dz.c("invoke getInterstitialEvent");
    return bg.d().c(paramContext, paramString, paramInterstitialAdListener);
  }
  
  public boolean b(final Context paramContext)
  {
    dz.c("invoke Core init");
    cf localCf = AdConfigHelper.getShellConfig(paramContext);
    if (localCf == null) {
      return false;
    }
    LoadExecutor.scheduleWithFixedDelay(new Runnable()
    {
      public void run()
      {
        dh.e(paramContext);
      }
    }, 0L, 1L, TimeUnit.DAYS);
    if (localCf.a() != 0) {
      be.e = localCf.a();
    }
    dz.c("LOADDELAY : " + be.e);
    if (localCf.e().containsKey("1"))
    {
      if (!b(localCf, "1"))
      {
        dz.c("empty admob appId");
        return false;
      }
      if (e("1")) {
        di.b(paramContext, localCf);
      }
    }
    if (localCf.e().containsKey("3"))
    {
      if (!b(localCf, "3"))
      {
        dz.c("empty unity appId");
        return false;
      }
      if ((e("3")) && ((paramContext instanceof Activity))) {
        dr.e((Activity)paramContext, localCf);
      }
    }
    if (localCf.e().containsKey("4"))
    {
      if (!b(localCf, "4"))
      {
        dz.c("empty vungle appId");
        return false;
      }
      if (e("4")) {
        du.a(paramContext, localCf);
      }
    }
    if (localCf.e().containsKey("6"))
    {
      if (!b(localCf, "6"))
      {
        dz.c("empty adcolony appId");
        return false;
      }
      if ((e("6")) && ((paramContext instanceof Activity))) {
        dg.e((Activity)paramContext, localCf);
      }
    }
    if (localCf.e().containsKey("7"))
    {
      if (!b(localCf, "7"))
      {
        dz.c("empty applovin appId");
        return false;
      }
      if (e("7")) {
        dj.e(paramContext, localCf);
      }
    }
    if (localCf.e().containsKey("10"))
    {
      if (!b(localCf, "10"))
      {
        dz.c("empty tapjoy appId");
        return false;
      }
      if ((e("10")) && ((paramContext instanceof Activity))) {
        dq.b((Activity)paramContext, localCf);
      }
    }
    if (localCf.e().containsKey("11"))
    {
      if (!b(localCf, "11"))
      {
        dz.c("empty chartboost appId");
        return false;
      }
      if ((e("11")) && ((paramContext instanceof Activity))) {
        dp.b((Activity)paramContext, localCf);
      }
    }
    LoadExecutor.scheduleWithFixedDelay(new Runnable()
    {
      public void run()
      {
        try
        {
          ck.b().c(this.b);
          cc.d().a(this.b);
          return;
        }
        catch (Throwable localThrowable)
        {
          dz.a("preloadAd error :", localThrowable);
        }
      }
    }, 1L, 120L, TimeUnit.SECONDS);
    LoadExecutor.scheduleWithFixedDelay(new Runnable()
    {
      public void run()
      {
        cp.d().c();
      }
    }, 1L, 1L, TimeUnit.SECONDS);
    return true;
  }
  
  public int c()
  {
    return 127;
  }
  
  public IInteractiveEvent c(Context paramContext, String paramString, InteractiveAdListener paramInteractiveAdListener)
  {
    dz.c("invoke getInteractiveEvent");
    return bg.d().a(paramContext, paramString, paramInteractiveAdListener);
  }
  
  public INativeEvent c(Context paramContext, String paramString, NativeAdListener paramNativeAdListener)
  {
    dz.c("invoke getNativeEvent");
    return bg.d().b(paramContext, paramString, paramNativeAdListener);
  }
  
  public IVideoEvent e()
  {
    dz.c("invoke getVideoEvent");
    return bg.d().c();
  }
}
