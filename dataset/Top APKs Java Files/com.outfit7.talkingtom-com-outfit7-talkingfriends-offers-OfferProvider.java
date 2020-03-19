package com.outfit7.talkingfriends.offers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.net.Uri.Builder;
import android.widget.Toast;
import com.outfit7.funnetworks.FunNetworks;
import com.outfit7.funnetworks.util.NonObfuscatable;
import com.outfit7.funnetworks.util.Util;
import com.outfit7.repackaged.com.google.gson.Gson;
import com.outfit7.repackaged.com.google.gson.reflect.TypeToken;
import com.outfit7.talkingfriends.ad.AdManager;
import com.outfit7.talkingfriends.ad.AdManager.AdManagerCallback;
import com.outfit7.talkingfriends.ad.O7AdInfo;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public abstract class OfferProvider
{
  public static final String BACKEND_SIGNATURE_KEY = "A7INQETRTPFDRG8QWQWA";
  private static final int ICON_CACHE_SIZE = 100;
  private static final long MINIMAL_REWARD_CHECK_INTERVAL = 3000L;
  private static final long OFFER_REQ_TIMEOUT_SECS = 30L;
  private static final String TAG = OfferProvider.class.getName();
  public static final String kEventOfferClicked = "offers_offerClicked";
  public static final String kEventOffersNReceived = "offers_numOffersReceived";
  public static final String kEventOffersProvider = "offers_offersProvider";
  public static final String kEventOffersReceived = "offers_offersReceived";
  public static final String kEventOffersReceivedNone = "offers_offersReceivedNone";
  public static final String kEventOffersReceivedNoneTimeout = "offers_offersReceivedNoneTimeout";
  public static final String kEventOffersRequested = "offers_offersRequested";
  public static final String kEventRewardReceived = "offers_rewardReceived";
  protected long CACHING_TIME = 120000L;
  protected boolean canPreload;
  protected int exchangeRateDenominator = 0;
  protected boolean hasOwnUI;
  long lastRewardCheck;
  protected Activity main = AdManager.getAdManagerCallback().getActivity();
  protected int minPoints;
  protected String providerID;
  private Condition timeoutCond = this.timeoutLock.newCondition();
  private Lock timeoutLock = new ReentrantLock();
  private Toast toast;
  
  public OfferProvider() {}
  
  private List<Offer> checkOffers(String paramString)
  {
    return checkOffers(paramString, null);
  }
  
  /* Error */
  private List<Offer> checkOffers(final String paramString, OfferListener paramOfferListener)
  {
    // Byte code:
    //   0: new 8	com/outfit7/talkingfriends/offers/OfferProvider$1C
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 157	com/outfit7/talkingfriends/offers/OfferProvider$1C:<init>	(Lcom/outfit7/talkingfriends/offers/OfferProvider;)V
    //   8: astore_3
    //   9: aload_0
    //   10: getfield 107	com/outfit7/talkingfriends/offers/OfferProvider:timeoutLock	Ljava/util/concurrent/locks/Lock;
    //   13: invokeinterface 160 1 0
    //   18: aload_2
    //   19: ifnull +9 -> 28
    //   22: aload_2
    //   23: invokeinterface 163 1 0
    //   28: new 11	com/outfit7/talkingfriends/offers/OfferProvider$2
    //   31: dup
    //   32: aload_0
    //   33: aload_1
    //   34: aload_3
    //   35: invokespecial 166	com/outfit7/talkingfriends/offers/OfferProvider$2:<init>	(Lcom/outfit7/talkingfriends/offers/OfferProvider;Ljava/lang/String;Lcom/outfit7/talkingfriends/offers/OfferProvider$1C;)V
    //   38: invokevirtual 169	com/outfit7/talkingfriends/offers/OfferProvider$2:start	()V
    //   41: aload_0
    //   42: getfield 115	com/outfit7/talkingfriends/offers/OfferProvider:timeoutCond	Ljava/util/concurrent/locks/Condition;
    //   45: ldc2_w 42
    //   48: getstatic 175	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   51: invokeinterface 181 4 0
    //   56: ifne +56 -> 112
    //   59: aload_0
    //   60: invokevirtual 184	com/outfit7/talkingfriends/offers/OfferProvider:getCountryCode	()Ljava/lang/String;
    //   63: astore_1
    //   64: invokestatic 123	com/outfit7/talkingfriends/ad/AdManager:getAdManagerCallback	()Lcom/outfit7/talkingfriends/ad/AdManager$AdManagerCallback;
    //   67: ldc 62
    //   69: iconst_2
    //   70: anewarray 4	java/lang/Object
    //   73: dup
    //   74: iconst_0
    //   75: ldc 53
    //   77: aastore
    //   78: dup
    //   79: iconst_1
    //   80: new 186	java/lang/StringBuilder
    //   83: dup
    //   84: invokespecial 187	java/lang/StringBuilder:<init>	()V
    //   87: aload_0
    //   88: getfield 189	com/outfit7/talkingfriends/offers/OfferProvider:providerID	Ljava/lang/String;
    //   91: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: ldc -61
    //   96: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: aload_1
    //   100: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: invokevirtual 198	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   106: aastore
    //   107: invokeinterface 202 3 0
    //   112: aload_2
    //   113: ifnull +13 -> 126
    //   116: aload_2
    //   117: aload_3
    //   118: getfield 206	com/outfit7/talkingfriends/offers/OfferProvider$1C:offers	Ljava/util/List;
    //   121: invokeinterface 210 2 0
    //   126: aload_0
    //   127: getfield 107	com/outfit7/talkingfriends/offers/OfferProvider:timeoutLock	Ljava/util/concurrent/locks/Lock;
    //   130: invokeinterface 213 1 0
    //   135: aload_3
    //   136: getfield 206	com/outfit7/talkingfriends/offers/OfferProvider$1C:offers	Ljava/util/List;
    //   139: areturn
    //   140: astore_1
    //   141: aload_2
    //   142: ifnull +13 -> 155
    //   145: aload_2
    //   146: aload_3
    //   147: getfield 206	com/outfit7/talkingfriends/offers/OfferProvider$1C:offers	Ljava/util/List;
    //   150: invokeinterface 210 2 0
    //   155: aload_0
    //   156: getfield 107	com/outfit7/talkingfriends/offers/OfferProvider:timeoutLock	Ljava/util/concurrent/locks/Lock;
    //   159: invokeinterface 213 1 0
    //   164: aload_1
    //   165: athrow
    //   166: astore_1
    //   167: goto -55 -> 112
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	170	0	this	OfferProvider
    //   0	170	1	paramString	String
    //   0	170	2	paramOfferListener	OfferListener
    //   8	139	3	local1C	1C
    // Exception table:
    //   from	to	target	type
    //   22	28	140	finally
    //   28	41	140	finally
    //   41	112	140	finally
    //   41	112	166	java/lang/InterruptedException
  }
  
  private void checkOffers010(String paramString, List<Offer> paramList)
  {
    Object localObject2;
    for (;;)
    {
      if (this.providerID.equals("nooffers")) {
        return;
      }
      long l1 = System.currentTimeMillis();
      Object localObject1 = this.main.getSharedPreferences("offers", 0);
      long l2 = ((SharedPreferences)localObject1).getLong("lastOfferUpdate", 0L);
      localObject2 = ((SharedPreferences)localObject1).getString("lastProvider", "");
      if ((l1 - l2 > this.CACHING_TIME) || (!((String)localObject2).equals(this.providerID)) || (AdManager.getAdManagerCallback().isInDebugMode()))
      {
        AdManager.getAdManagerCallback().logEvent("offers_offersRequested", new Object[] { "offers_offersProvider", this.providerID });
        localObject2 = getCountryCode();
        getOffers(paramString, paramList);
        if (paramList.size() > 0)
        {
          AdManager.getAdManagerCallback().logEvent("offers_offersReceived", new Object[] { "offers_offersProvider", this.providerID, "offers_numOffersReceived", paramList.size() });
          try
          {
            cleanUpCache();
            paramString = ((SharedPreferences)localObject1).edit();
            paramString.putLong("lastOfferUpdate", l1);
            paramString.putString("lastProvider", this.providerID);
            paramString.putString("lastOffers", serialise(paramList));
            paramString.commit();
            return;
          }
          catch (Exception paramString)
          {
            new StringBuilder().append(paramString);
            return;
          }
        }
        AdManager.getAdManagerCallback().logEvent("offers_offersReceivedNone", new Object[] { "offers_offersProvider", this.providerID + "-" + (String)localObject2 });
        return;
      }
      localObject2 = deserialise(((SharedPreferences)localObject1).getString("lastOffers", ""));
      if (localObject2 != null) {
        break;
      }
      localObject1 = ((SharedPreferences)localObject1).edit();
      ((SharedPreferences.Editor)localObject1).putLong("lastOfferUpdate", 0L);
      ((SharedPreferences.Editor)localObject1).putString("lastProvider", this.providerID);
      ((SharedPreferences.Editor)localObject1).commit();
    }
    paramList.addAll((Collection)localObject2);
  }
  
  private void cleanUpCache()
  {
    Object localObject = new File(this.main.getCacheDir(), ".offersImgCache");
    if (!((File)localObject).exists()) {
      break label25;
    }
    for (;;)
    {
      label25:
      return;
      if (((File)localObject).isDirectory())
      {
        localObject = ((File)localObject).listFiles();
        if (localObject.length == 0) {
          break;
        }
        Arrays.sort((Object[])localObject, new Comparator()
        {
          public int compare(File paramAnonymousFile1, File paramAnonymousFile2)
          {
            return (int)(paramAnonymousFile1.lastModified() - paramAnonymousFile2.lastModified());
          }
          
          public boolean equals(Object paramAnonymousObject)
          {
            return paramAnonymousObject == this;
          }
        });
        int i = 0;
        while (i < localObject.length - 100)
        {
          localObject[i].delete();
          i += 1;
        }
      }
    }
  }
  
  public static int getO7Points(String paramString1, String paramString2)
  {
    return getO7Points(paramString1, paramString2, null);
  }
  
  /* Error */
  public static int getO7Points(String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: invokestatic 123	com/outfit7/talkingfriends/ad/AdManager:getAdManagerCallback	()Lcom/outfit7/talkingfriends/ad/AdManager$AdManagerCallback;
    //   3: invokeinterface 129 1 0
    //   8: astore 9
    //   10: aload 9
    //   12: invokestatic 348	com/outfit7/talkingfriends/ad/AdManager:getAdInfo	(Landroid/content/Context;)Lcom/outfit7/talkingfriends/ad/O7AdInfo;
    //   15: astore 8
    //   17: aload 8
    //   19: getfield 353	com/outfit7/talkingfriends/ad/O7AdInfo:canUse	Z
    //   22: ifne +5 -> 27
    //   25: iconst_0
    //   26: ireturn
    //   27: new 355	org/apache/http/impl/client/DefaultHttpClient
    //   30: dup
    //   31: invokespecial 356	org/apache/http/impl/client/DefaultHttpClient:<init>	()V
    //   34: astore 5
    //   36: aload 9
    //   38: invokestatic 361	com/outfit7/funnetworks/FunNetworks:b	(Landroid/content/Context;)Ljava/lang/String;
    //   41: pop
    //   42: new 363	android/net/Uri$Builder
    //   45: dup
    //   46: invokespecial 364	android/net/Uri$Builder:<init>	()V
    //   49: astore 6
    //   51: aload 6
    //   53: ldc_w 366
    //   56: invokevirtual 370	android/net/Uri$Builder:scheme	(Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   59: pop
    //   60: aload 6
    //   62: aload 9
    //   64: invokestatic 361	com/outfit7/funnetworks/FunNetworks:b	(Landroid/content/Context;)Ljava/lang/String;
    //   67: invokevirtual 373	android/net/Uri$Builder:authority	(Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   70: pop
    //   71: aload 6
    //   73: new 186	java/lang/StringBuilder
    //   76: dup
    //   77: ldc_w 375
    //   80: invokespecial 378	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   83: aload_0
    //   84: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: ldc_w 380
    //   90: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: invokevirtual 198	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   96: invokevirtual 383	android/net/Uri$Builder:path	(Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   99: pop
    //   100: new 385	java/util/ArrayList
    //   103: dup
    //   104: invokespecial 386	java/util/ArrayList:<init>	()V
    //   107: astore 7
    //   109: aload_2
    //   110: astore 4
    //   112: aload_2
    //   113: ifnonnull +10 -> 123
    //   116: aload 9
    //   118: invokevirtual 389	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   121: astore 4
    //   123: aload 7
    //   125: iconst_2
    //   126: anewarray 220	java/lang/String
    //   129: dup
    //   130: iconst_0
    //   131: ldc_w 391
    //   134: aastore
    //   135: dup
    //   136: iconst_1
    //   137: aload 4
    //   139: aastore
    //   140: invokeinterface 394 2 0
    //   145: pop
    //   146: aload 7
    //   148: iconst_2
    //   149: anewarray 220	java/lang/String
    //   152: dup
    //   153: iconst_0
    //   154: ldc_w 396
    //   157: aastore
    //   158: dup
    //   159: iconst_1
    //   160: aload_0
    //   161: aastore
    //   162: invokeinterface 394 2 0
    //   167: pop
    //   168: aload 8
    //   170: getfield 399	com/outfit7/talkingfriends/ad/O7AdInfo:ID	Ljava/lang/String;
    //   173: ifnull +134 -> 307
    //   176: iconst_2
    //   177: anewarray 220	java/lang/String
    //   180: astore_0
    //   181: aload_0
    //   182: iconst_0
    //   183: ldc_w 401
    //   186: aastore
    //   187: aload_0
    //   188: iconst_1
    //   189: aload 8
    //   191: getfield 399	com/outfit7/talkingfriends/ad/O7AdInfo:ID	Ljava/lang/String;
    //   194: aastore
    //   195: aload 7
    //   197: aload_0
    //   198: invokeinterface 394 2 0
    //   203: pop
    //   204: new 186	java/lang/StringBuilder
    //   207: dup
    //   208: invokespecial 187	java/lang/StringBuilder:<init>	()V
    //   211: astore_0
    //   212: aload 7
    //   214: invokeinterface 405 1 0
    //   219: astore_1
    //   220: aload_1
    //   221: invokeinterface 410 1 0
    //   226: ifeq +99 -> 325
    //   229: aload_1
    //   230: invokeinterface 414 1 0
    //   235: checkcast 416	[Ljava/lang/String;
    //   238: astore_2
    //   239: aload_0
    //   240: aload_2
    //   241: iconst_0
    //   242: aaload
    //   243: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   246: ldc_w 418
    //   249: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   252: aload_2
    //   253: iconst_1
    //   254: aaload
    //   255: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   258: ldc_w 420
    //   261: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   264: pop
    //   265: aload_2
    //   266: iconst_0
    //   267: aaload
    //   268: ldc_w 396
    //   271: invokevirtual 224	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   274: ifne -54 -> 220
    //   277: aload 6
    //   279: aload_2
    //   280: iconst_0
    //   281: aaload
    //   282: aload_2
    //   283: iconst_1
    //   284: aaload
    //   285: invokevirtual 424	android/net/Uri$Builder:appendQueryParameter	(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   288: pop
    //   289: goto -69 -> 220
    //   292: astore_0
    //   293: aload 5
    //   295: invokeinterface 430 1 0
    //   300: invokeinterface 435 1 0
    //   305: aload_0
    //   306: athrow
    //   307: iconst_2
    //   308: anewarray 220	java/lang/String
    //   311: astore_0
    //   312: aload_0
    //   313: iconst_0
    //   314: ldc_w 437
    //   317: aastore
    //   318: aload_0
    //   319: iconst_1
    //   320: aload_1
    //   321: aastore
    //   322: goto -127 -> 195
    //   325: aload_0
    //   326: ldc 33
    //   328: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   331: pop
    //   332: new 186	java/lang/StringBuilder
    //   335: dup
    //   336: ldc_w 439
    //   339: invokespecial 378	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   342: aload_0
    //   343: invokevirtual 298	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   346: pop
    //   347: ldc_w 441
    //   350: invokestatic 447	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   353: astore_1
    //   354: aload_1
    //   355: aload_0
    //   356: invokevirtual 198	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   359: invokevirtual 451	java/lang/String:getBytes	()[B
    //   362: invokevirtual 455	java/security/MessageDigest:update	([B)V
    //   365: aload 6
    //   367: ldc_w 457
    //   370: aload_1
    //   371: invokevirtual 460	java/security/MessageDigest:digest	()[B
    //   374: invokestatic 465	com/outfit7/funnetworks/util/Util:a	([B)Ljava/lang/String;
    //   377: invokevirtual 424	android/net/Uri$Builder:appendQueryParameter	(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   380: pop
    //   381: new 467	org/apache/http/client/methods/HttpGet
    //   384: dup
    //   385: aload 6
    //   387: invokevirtual 471	android/net/Uri$Builder:build	()Landroid/net/Uri;
    //   390: invokevirtual 474	android/net/Uri:toString	()Ljava/lang/String;
    //   393: invokespecial 475	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   396: astore_0
    //   397: new 186	java/lang/StringBuilder
    //   400: dup
    //   401: ldc_w 477
    //   404: invokespecial 378	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   407: aload_0
    //   408: invokeinterface 483 1 0
    //   413: invokevirtual 298	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   416: pop
    //   417: aload 5
    //   419: aload_0
    //   420: invokeinterface 487 2 0
    //   425: astore_0
    //   426: aload_0
    //   427: invokeinterface 493 1 0
    //   432: astore_1
    //   433: new 186	java/lang/StringBuilder
    //   436: dup
    //   437: ldc_w 495
    //   440: invokespecial 378	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   443: aload_1
    //   444: invokevirtual 298	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   447: pop
    //   448: aload_1
    //   449: invokeinterface 500 1 0
    //   454: istore_3
    //   455: iload_3
    //   456: sipush 200
    //   459: if_icmpeq +33 -> 492
    //   462: aload 5
    //   464: invokeinterface 430 1 0
    //   469: invokeinterface 435 1 0
    //   474: iconst_0
    //   475: ireturn
    //   476: astore_0
    //   477: new 186	java/lang/StringBuilder
    //   480: dup
    //   481: invokespecial 187	java/lang/StringBuilder:<init>	()V
    //   484: aload_0
    //   485: invokevirtual 298	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   488: pop
    //   489: goto -108 -> 381
    //   492: aload_0
    //   493: invokeinterface 504 1 0
    //   498: astore_0
    //   499: aload_0
    //   500: ifnonnull +17 -> 517
    //   503: aload 5
    //   505: invokeinterface 430 1 0
    //   510: invokeinterface 435 1 0
    //   515: iconst_0
    //   516: ireturn
    //   517: aload_0
    //   518: ldc_w 506
    //   521: invokestatic 511	org/apache/http/util/EntityUtils:toString	(Lorg/apache/http/HttpEntity;Ljava/lang/String;)Ljava/lang/String;
    //   524: astore_1
    //   525: new 186	java/lang/StringBuilder
    //   528: dup
    //   529: ldc_w 513
    //   532: invokespecial 378	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   535: aload_1
    //   536: invokevirtual 193	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   539: pop
    //   540: new 515	com/outfit7/repackaged/com/google/gson/Gson
    //   543: dup
    //   544: invokespecial 516	com/outfit7/repackaged/com/google/gson/Gson:<init>	()V
    //   547: aload_1
    //   548: ldc 22
    //   550: invokevirtual 520	com/outfit7/repackaged/com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   553: checkcast 22	com/outfit7/talkingfriends/offers/OfferProvider$O7JSONResponse
    //   556: getfield 523	com/outfit7/talkingfriends/offers/OfferProvider$O7JSONResponse:points	I
    //   559: istore_3
    //   560: aload_0
    //   561: invokeinterface 528 1 0
    //   566: aload 5
    //   568: invokeinterface 430 1 0
    //   573: invokeinterface 435 1 0
    //   578: iload_3
    //   579: ireturn
    //   580: astore_1
    //   581: new 186	java/lang/StringBuilder
    //   584: dup
    //   585: invokespecial 187	java/lang/StringBuilder:<init>	()V
    //   588: aload_1
    //   589: invokevirtual 298	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   592: pop
    //   593: aload_0
    //   594: invokeinterface 528 1 0
    //   599: iconst_0
    //   600: istore_3
    //   601: goto -35 -> 566
    //   604: astore_1
    //   605: aload_0
    //   606: invokeinterface 528 1 0
    //   611: aload_1
    //   612: athrow
    //   613: astore_0
    //   614: iconst_0
    //   615: istore_3
    //   616: new 186	java/lang/StringBuilder
    //   619: dup
    //   620: invokespecial 187	java/lang/StringBuilder:<init>	()V
    //   623: aload_0
    //   624: invokevirtual 298	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   627: pop
    //   628: goto -62 -> 566
    //   631: astore_0
    //   632: goto -16 -> 616
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	635	0	paramString1	String
    //   0	635	1	paramString2	String
    //   0	635	2	paramString3	String
    //   454	162	3	i	int
    //   110	28	4	str	String
    //   34	533	5	localDefaultHttpClient	DefaultHttpClient
    //   49	337	6	localBuilder	Uri.Builder
    //   107	106	7	localArrayList	ArrayList
    //   15	175	8	localO7AdInfo	O7AdInfo
    //   8	109	9	localActivity	Activity
    // Exception table:
    //   from	to	target	type
    //   36	109	292	finally
    //   116	123	292	finally
    //   123	181	292	finally
    //   187	195	292	finally
    //   195	220	292	finally
    //   220	289	292	finally
    //   307	312	292	finally
    //   325	347	292	finally
    //   347	381	292	finally
    //   381	417	292	finally
    //   417	455	292	finally
    //   477	489	292	finally
    //   492	499	292	finally
    //   560	566	292	finally
    //   593	599	292	finally
    //   605	613	292	finally
    //   616	628	292	finally
    //   347	381	476	java/security/NoSuchAlgorithmException
    //   517	560	580	java/lang/Exception
    //   517	560	604	finally
    //   581	593	604	finally
    //   417	455	613	java/lang/Exception
    //   492	499	613	java/lang/Exception
    //   593	599	613	java/lang/Exception
    //   605	613	613	java/lang/Exception
    //   560	566	631	java/lang/Exception
  }
  
  public static boolean isInTestMode()
  {
    return AdManager.getAdManagerCallback().getAdManager().runAdsInTestMode();
  }
  
  private void showMsg(final String paramString)
  {
    if (!AdManager.getAdManagerCallback().isInDebugMode()) {
      return;
    }
    new StringBuilder("Offers: ").append(paramString);
    AdManager.getAdManagerCallback().getActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (OfferProvider.a(OfferProvider.this) == null)
        {
          OfferProvider.a(OfferProvider.this, Toast.makeText(OfferProvider.this.main.getApplicationContext(), "", 0));
          OfferProvider.a(OfferProvider.this).setGravity(17, 0, 0);
        }
        OfferProvider.a(OfferProvider.this).setText(paramString);
        OfferProvider.a(OfferProvider.this).show();
      }
    });
  }
  
  public static boolean spendO7Points(int paramInt, String paramString1, String paramString2)
  {
    return spendO7Points(paramInt, paramString1, paramString2, null);
  }
  
  public static boolean spendO7Points(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    Activity localActivity = AdManager.getAdManagerCallback().getActivity();
    O7AdInfo localO7AdInfo = AdManager.getAdInfo(localActivity);
    if (!localO7AdInfo.canUse) {
      return false;
    }
    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
    int i;
    if (paramInt == Integer.MAX_VALUE) {
      i = 1;
    }
    Uri.Builder localBuilder;
    for (;;)
    {
      try
      {
        FunNetworks.b(localActivity);
        localBuilder = new Uri.Builder();
        localBuilder.scheme("http");
        localBuilder.authority(FunNetworks.b(localActivity));
        if (i == 0) {
          break label372;
        }
        str = "/clear-points";
        localBuilder.path("rest/talkingFriends/v1/offers/" + paramString1 + str);
        ArrayList localArrayList = new ArrayList();
        str = paramString3;
        if (paramString3 == null) {
          str = localActivity.getPackageName();
        }
        localArrayList.add(new String[] { "app", str });
        if (i == 0) {
          localArrayList.add(new String[] { "points", paramInt });
        }
        localArrayList.add(new String[] { "provider", paramString1 });
        if (localO7AdInfo.ID == null) {
          break label380;
        }
        paramString1 = new String[2];
        paramString1[0] = "aaid";
        paramString1[1] = localO7AdInfo.ID;
        localArrayList.add(paramString1);
        paramString1 = new StringBuilder();
        paramString2 = localArrayList.iterator();
        if (!paramString2.hasNext()) {
          break;
        }
        paramString3 = (String[])paramString2.next();
        paramString1.append(paramString3[0]).append("=").append(paramString3[1]).append("&");
        if (paramString3[0].equals("provider")) {
          continue;
        }
        localBuilder.appendQueryParameter(paramString3[0], paramString3[1]);
        continue;
        i = 0;
      }
      finally
      {
        localDefaultHttpClient.getConnectionManager().shutdown();
      }
      continue;
      label372:
      String str = "/spend-points";
      continue;
      label380:
      paramString1 = new String[2];
      paramString1[0] = "udid";
      paramString1[1] = paramString2;
    }
    paramString1.append("A7INQETRTPFDRG8QWQWA");
    new StringBuilder("sb = ").append(paramString1);
    try
    {
      paramString2 = MessageDigest.getInstance("SHA1");
      paramString2.update(paramString1.toString().getBytes());
      localBuilder.appendQueryParameter("s", Util.a(paramString2.digest()));
      paramString1 = new HttpGet(localBuilder.build().toString());
      new StringBuilder("request = ").append(paramString1.getURI());
    }
    catch (NoSuchAlgorithmException paramString1)
    {
      try
      {
        for (;;)
        {
          paramString1 = localDefaultHttpClient.execute(paramString1);
          paramString2 = paramString1.getStatusLine();
          new StringBuilder("statusLine = ").append(paramString2);
          paramInt = paramString2.getStatusCode();
          if (paramInt == 200) {
            break;
          }
          localDefaultHttpClient.getConnectionManager().shutdown();
          return false;
          paramString1 = paramString1;
          new StringBuilder().append(paramString1);
        }
        paramString1 = paramString1.getEntity();
        if (paramString1 == null)
        {
          localDefaultHttpClient.getConnectionManager().shutdown();
          return true;
        }
        try
        {
          EntityUtils.toString(paramString1, "UTF-8");
          paramString1.consumeContent();
          localDefaultHttpClient.getConnectionManager().shutdown();
          return true;
        }
        finally
        {
          paramString1.consumeContent();
        }
        return false;
      }
      catch (IOException paramString1)
      {
        new StringBuilder().append(paramString1);
        localDefaultHttpClient.getConnectionManager().shutdown();
      }
    }
  }
  
  final void a(int paramInt)
  {
    this.exchangeRateDenominator = paramInt;
  }
  
  public boolean canPreload()
  {
    return this.canPreload;
  }
  
  void checkRewards()
  {
    int i = getPoints();
    AdManager.getAdManagerCallback().gotPoints(this, i);
  }
  
  public void clearCache()
  {
    SharedPreferences.Editor localEditor = this.main.getSharedPreferences("offers", 0).edit();
    localEditor.putLong("lastOfferUpdate", 0L);
    localEditor.commit();
  }
  
  protected List<Offer> deserialise(String paramString)
  {
    (List)new Gson().fromJson(paramString, new TypeToken() {}.getType());
  }
  
  public void finish() {}
  
  protected String getCountryCode()
  {
    try
    {
      JSONResponse localJSONResponse1 = (JSONResponse)Util.a(AdManager.getAdManagerCallback().getActivity(), "jsonResponse", JSONResponse.class);
      JSONResponse localJSONResponse2 = localJSONResponse1;
      if (localJSONResponse1 == null) {
        localJSONResponse2 = new JSONResponse();
      }
      return localJSONResponse2.clientCountryCode;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        Object localObject = null;
      }
    }
  }
  
  public int getMinPoints()
  {
    return this.minPoints;
  }
  
  protected abstract void getOffers(String paramString, List<Offer> paramList);
  
  protected int getPoints()
  {
    return getO7Points(getProviderID(), getUserID());
  }
  
  public String getProviderID()
  {
    return this.providerID;
  }
  
  protected String getUserID()
  {
    return Util.o(this.main);
  }
  
  public boolean hasOwnUI()
  {
    return this.hasOwnUI;
  }
  
  protected boolean isPackageInstalled(String paramString)
  {
    Iterator localIterator = AdManager.getAdManagerCallback().getActivity().getPackageManager().getInstalledPackages(0).iterator();
    while (localIterator.hasNext()) {
      if (((PackageInfo)localIterator.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public List<Offer> loadOffers(String paramString, OfferListener paramOfferListener)
  {
    showMsg("Load offers for " + this.providerID);
    paramString = checkOffers(paramString, paramOfferListener);
    showMsg("Got " + paramString.size() + " offers for " + this.providerID);
    return paramString;
  }
  
  public void onResume() {}
  
  public void release() {}
  
  protected String serialise(List<Offer> paramList)
  {
    new Gson().toJson(paramList, new TypeToken() {}.getType());
  }
  
  public void setMinPoints(int paramInt)
  {
    this.minPoints = paramInt;
  }
  
  public boolean shouldCloseOffers()
  {
    return true;
  }
  
  public void spendPoints(int paramInt)
  {
    clearCache();
    spendO7Points(paramInt, getProviderID(), getUserID());
  }
  
  public void startOffer(Offer paramOffer)
  {
    AdManager.getAdManagerCallback().logEvent("offers_offerClicked", new Object[] { "offers_offersProvider", this.providerID });
  }
  
  public void startSession() {}
  
  public boolean startUI()
  {
    return false;
  }
  
  public String toString()
  {
    return this.providerID;
  }
  
  public boolean usePointsDivisor()
  {
    return true;
  }
  
  public static class JSONResponse
  {
    public String clientCountryCode = "";
    
    public JSONResponse() {}
  }
  
  private static class O7JSONResponse
    implements NonObfuscatable
  {
    int points;
    
    private O7JSONResponse() {}
  }
  
  public static class Offer
    implements NonObfuscatable
  {
    private transient Object downloadMonitor = new Object();
    public String link;
    public int points;
    public String requiredAction;
    public String thumbFile;
    public String thumbLink;
    public String title;
    
    public Offer() {}
    
    /* Error */
    private void cacheImg(byte[] paramArrayOfByte)
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: new 32	java/io/File
      //   5: dup
      //   6: invokestatic 38	com/outfit7/talkingfriends/ad/AdManager:getAdManagerCallback	()Lcom/outfit7/talkingfriends/ad/AdManager$AdManagerCallback;
      //   9: invokeinterface 44 1 0
      //   14: invokevirtual 50	android/app/Activity:getCacheDir	()Ljava/io/File;
      //   17: ldc 52
      //   19: invokespecial 55	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
      //   22: astore_3
      //   23: aload_3
      //   24: invokevirtual 59	java/io/File:exists	()Z
      //   27: ifne +8 -> 35
      //   30: aload_3
      //   31: invokevirtual 62	java/io/File:mkdirs	()Z
      //   34: pop
      //   35: aload_3
      //   36: invokevirtual 65	java/io/File:isDirectory	()Z
      //   39: istore_2
      //   40: iload_2
      //   41: ifne +6 -> 47
      //   44: aload_0
      //   45: monitorexit
      //   46: return
      //   47: new 32	java/io/File
      //   50: dup
      //   51: aload_3
      //   52: aload_0
      //   53: aload_0
      //   54: getfield 67	com/outfit7/talkingfriends/offers/OfferProvider$Offer:thumbLink	Ljava/lang/String;
      //   57: invokespecial 71	com/outfit7/talkingfriends/offers/OfferProvider$Offer:md5	(Ljava/lang/String;)Ljava/lang/String;
      //   60: invokespecial 55	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
      //   63: astore_3
      //   64: new 73	java/io/BufferedOutputStream
      //   67: dup
      //   68: new 75	java/io/FileOutputStream
      //   71: dup
      //   72: aload_3
      //   73: invokespecial 78	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
      //   76: invokespecial 81	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
      //   79: astore_3
      //   80: aload_3
      //   81: aload_1
      //   82: invokevirtual 86	java/io/OutputStream:write	([B)V
      //   85: aload_3
      //   86: invokevirtual 89	java/io/OutputStream:close	()V
      //   89: goto -45 -> 44
      //   92: astore_1
      //   93: invokestatic 93	com/outfit7/talkingfriends/offers/OfferProvider:b	()Ljava/lang/String;
      //   96: pop
      //   97: new 95	java/lang/StringBuilder
      //   100: dup
      //   101: invokespecial 96	java/lang/StringBuilder:<init>	()V
      //   104: aload_1
      //   105: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   108: pop
      //   109: goto -65 -> 44
      //   112: astore_1
      //   113: aload_0
      //   114: monitorexit
      //   115: aload_1
      //   116: athrow
      //   117: astore_1
      //   118: invokestatic 93	com/outfit7/talkingfriends/offers/OfferProvider:b	()Ljava/lang/String;
      //   121: pop
      //   122: new 95	java/lang/StringBuilder
      //   125: dup
      //   126: invokespecial 96	java/lang/StringBuilder:<init>	()V
      //   129: aload_1
      //   130: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   133: pop
      //   134: aload_3
      //   135: invokevirtual 89	java/io/OutputStream:close	()V
      //   138: goto -94 -> 44
      //   141: astore_1
      //   142: aload_3
      //   143: invokevirtual 89	java/io/OutputStream:close	()V
      //   146: aload_1
      //   147: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	148	0	this	Offer
      //   0	148	1	paramArrayOfByte	byte[]
      //   39	2	2	bool	boolean
      //   22	121	3	localObject	Object
      // Exception table:
      //   from	to	target	type
      //   64	80	92	java/lang/Exception
      //   85	89	92	java/lang/Exception
      //   134	138	92	java/lang/Exception
      //   142	148	92	java/lang/Exception
      //   2	35	112	finally
      //   35	40	112	finally
      //   47	64	112	finally
      //   64	80	112	finally
      //   85	89	112	finally
      //   93	109	112	finally
      //   134	138	112	finally
      //   142	148	112	finally
      //   80	85	117	java/lang/Exception
      //   80	85	141	finally
      //   118	134	141	finally
    }
    
    /* Error */
    private byte[] downloadBitmap()
    {
      // Byte code:
      //   0: new 104	org/apache/http/impl/client/DefaultHttpClient
      //   3: dup
      //   4: invokespecial 105	org/apache/http/impl/client/DefaultHttpClient:<init>	()V
      //   7: astore_3
      //   8: new 107	org/apache/http/client/methods/HttpGet
      //   11: dup
      //   12: aload_0
      //   13: getfield 67	com/outfit7/talkingfriends/offers/OfferProvider$Offer:thumbLink	Ljava/lang/String;
      //   16: invokespecial 110	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
      //   19: astore_2
      //   20: aload_3
      //   21: aload_2
      //   22: invokeinterface 116 2 0
      //   27: astore_2
      //   28: aload_2
      //   29: invokeinterface 122 1 0
      //   34: invokeinterface 128 1 0
      //   39: istore_1
      //   40: iload_1
      //   41: sipush 200
      //   44: if_icmpeq +98 -> 142
      //   47: aload_3
      //   48: invokeinterface 132 1 0
      //   53: invokeinterface 137 1 0
      //   58: aconst_null
      //   59: areturn
      //   60: astore_2
      //   61: new 107	org/apache/http/client/methods/HttpGet
      //   64: dup
      //   65: new 95	java/lang/StringBuilder
      //   68: dup
      //   69: invokespecial 96	java/lang/StringBuilder:<init>	()V
      //   72: aload_0
      //   73: getfield 67	com/outfit7/talkingfriends/offers/OfferProvider$Offer:thumbLink	Ljava/lang/String;
      //   76: iconst_0
      //   77: aload_0
      //   78: getfield 67	com/outfit7/talkingfriends/offers/OfferProvider$Offer:thumbLink	Ljava/lang/String;
      //   81: ldc -117
      //   83: invokevirtual 145	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
      //   86: iconst_1
      //   87: iadd
      //   88: invokevirtual 149	java/lang/String:substring	(II)Ljava/lang/String;
      //   91: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   94: aload_0
      //   95: getfield 67	com/outfit7/talkingfriends/offers/OfferProvider$Offer:thumbLink	Ljava/lang/String;
      //   98: aload_0
      //   99: getfield 67	com/outfit7/talkingfriends/offers/OfferProvider$Offer:thumbLink	Ljava/lang/String;
      //   102: ldc -117
      //   104: invokevirtual 145	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
      //   107: iconst_1
      //   108: iadd
      //   109: invokevirtual 155	java/lang/String:substring	(I)Ljava/lang/String;
      //   112: invokestatic 160	android/net/Uri:encode	(Ljava/lang/String;)Ljava/lang/String;
      //   115: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   118: invokevirtual 163	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   121: invokespecial 110	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
      //   124: astore_2
      //   125: goto -105 -> 20
      //   128: astore_2
      //   129: aload_3
      //   130: invokeinterface 132 1 0
      //   135: invokeinterface 137 1 0
      //   140: aload_2
      //   141: athrow
      //   142: aload_2
      //   143: invokeinterface 167 1 0
      //   148: astore_2
      //   149: aload_2
      //   150: ifnonnull +16 -> 166
      //   153: aload_3
      //   154: invokeinterface 132 1 0
      //   159: invokeinterface 137 1 0
      //   164: aconst_null
      //   165: areturn
      //   166: new 169	java/io/ByteArrayOutputStream
      //   169: dup
      //   170: invokespecial 170	java/io/ByteArrayOutputStream:<init>	()V
      //   173: astore 4
      //   175: aload_2
      //   176: aload 4
      //   178: invokeinterface 175 2 0
      //   183: aload_2
      //   184: invokeinterface 178 1 0
      //   189: aload 4
      //   191: invokevirtual 181	java/io/ByteArrayOutputStream:toByteArray	()[B
      //   194: astore_2
      //   195: aload_3
      //   196: invokeinterface 132 1 0
      //   201: invokeinterface 137 1 0
      //   206: aload_2
      //   207: areturn
      //   208: astore_2
      //   209: invokestatic 93	com/outfit7/talkingfriends/offers/OfferProvider:b	()Ljava/lang/String;
      //   212: pop
      //   213: new 95	java/lang/StringBuilder
      //   216: dup
      //   217: invokespecial 96	java/lang/StringBuilder:<init>	()V
      //   220: aload_2
      //   221: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   224: pop
      //   225: aload_3
      //   226: invokeinterface 132 1 0
      //   231: invokeinterface 137 1 0
      //   236: aconst_null
      //   237: areturn
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	238	0	this	Offer
      //   39	6	1	i	int
      //   19	10	2	localObject1	Object
      //   60	1	2	localException1	Exception
      //   124	1	2	localHttpGet	HttpGet
      //   128	15	2	localObject2	Object
      //   148	59	2	localObject3	Object
      //   208	13	2	localException2	Exception
      //   7	219	3	localDefaultHttpClient	DefaultHttpClient
      //   173	17	4	localByteArrayOutputStream	java.io.ByteArrayOutputStream
      // Exception table:
      //   from	to	target	type
      //   8	20	60	java/lang/Exception
      //   8	20	128	finally
      //   20	40	128	finally
      //   61	125	128	finally
      //   142	149	128	finally
      //   166	195	128	finally
      //   209	225	128	finally
      //   20	40	208	java/lang/Exception
      //   142	149	208	java/lang/Exception
      //   166	195	208	java/lang/Exception
    }
    
    /* Error */
    private byte[] getCachedImg()
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 8
      //   3: aconst_null
      //   4: astore 7
      //   6: aconst_null
      //   7: astore 6
      //   9: aload_0
      //   10: monitorenter
      //   11: new 32	java/io/File
      //   14: dup
      //   15: invokestatic 38	com/outfit7/talkingfriends/ad/AdManager:getAdManagerCallback	()Lcom/outfit7/talkingfriends/ad/AdManager$AdManagerCallback;
      //   18: invokeinterface 44 1 0
      //   23: invokevirtual 50	android/app/Activity:getCacheDir	()Ljava/io/File;
      //   26: ldc 52
      //   28: invokespecial 55	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
      //   31: astore 9
      //   33: aload 9
      //   35: invokevirtual 59	java/io/File:exists	()Z
      //   38: istore 4
      //   40: iload 4
      //   42: ifne +12 -> 54
      //   45: aload 6
      //   47: astore 5
      //   49: aload_0
      //   50: monitorexit
      //   51: aload 5
      //   53: areturn
      //   54: aload 6
      //   56: astore 5
      //   58: aload 9
      //   60: invokevirtual 65	java/io/File:isDirectory	()Z
      //   63: ifeq -14 -> 49
      //   66: new 32	java/io/File
      //   69: dup
      //   70: aload 9
      //   72: aload_0
      //   73: aload_0
      //   74: getfield 67	com/outfit7/talkingfriends/offers/OfferProvider$Offer:thumbLink	Ljava/lang/String;
      //   77: invokespecial 71	com/outfit7/talkingfriends/offers/OfferProvider$Offer:md5	(Ljava/lang/String;)Ljava/lang/String;
      //   80: invokespecial 55	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
      //   83: astore 9
      //   85: aload 9
      //   87: invokevirtual 59	java/io/File:exists	()Z
      //   90: istore 4
      //   92: aload 6
      //   94: astore 5
      //   96: iload 4
      //   98: ifeq -49 -> 49
      //   101: aload 8
      //   103: astore 6
      //   105: new 184	java/io/BufferedInputStream
      //   108: dup
      //   109: new 186	java/io/FileInputStream
      //   112: dup
      //   113: aload 9
      //   115: invokespecial 187	java/io/FileInputStream:<init>	(Ljava/io/File;)V
      //   118: invokespecial 190	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
      //   121: astore 8
      //   123: aload 7
      //   125: astore 5
      //   127: aload 9
      //   129: invokevirtual 194	java/io/File:length	()J
      //   132: l2i
      //   133: newarray byte
      //   135: astore 7
      //   137: iconst_0
      //   138: istore_2
      //   139: aload 7
      //   141: astore 5
      //   143: aload 7
      //   145: astore 6
      //   147: aload 7
      //   149: arraylength
      //   150: istore_1
      //   151: iload_1
      //   152: ifeq +37 -> 189
      //   155: aload 7
      //   157: astore 5
      //   159: aload 7
      //   161: astore 6
      //   163: aload 8
      //   165: aload 7
      //   167: iload_2
      //   168: iload_1
      //   169: invokevirtual 200	java/io/InputStream:read	([BII)I
      //   172: istore_3
      //   173: iload_3
      //   174: iconst_m1
      //   175: if_icmpeq +14 -> 189
      //   178: iload_2
      //   179: iload_3
      //   180: iadd
      //   181: istore_2
      //   182: iload_1
      //   183: iload_3
      //   184: isub
      //   185: istore_1
      //   186: goto -35 -> 151
      //   189: aload 7
      //   191: astore 6
      //   193: aload 8
      //   195: invokevirtual 201	java/io/InputStream:close	()V
      //   198: aload 7
      //   200: astore 5
      //   202: goto -153 -> 49
      //   205: astore 7
      //   207: aload 6
      //   209: astore 5
      //   211: invokestatic 93	com/outfit7/talkingfriends/offers/OfferProvider:b	()Ljava/lang/String;
      //   214: pop
      //   215: new 95	java/lang/StringBuilder
      //   218: dup
      //   219: invokespecial 96	java/lang/StringBuilder:<init>	()V
      //   222: aload 7
      //   224: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   227: pop
      //   228: goto -179 -> 49
      //   231: astore 5
      //   233: aload_0
      //   234: monitorexit
      //   235: aload 5
      //   237: athrow
      //   238: astore 7
      //   240: aload 5
      //   242: astore 6
      //   244: invokestatic 93	com/outfit7/talkingfriends/offers/OfferProvider:b	()Ljava/lang/String;
      //   247: pop
      //   248: aload 5
      //   250: astore 6
      //   252: new 95	java/lang/StringBuilder
      //   255: dup
      //   256: invokespecial 96	java/lang/StringBuilder:<init>	()V
      //   259: aload 7
      //   261: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   264: pop
      //   265: aload 5
      //   267: astore 6
      //   269: aload 8
      //   271: invokevirtual 201	java/io/InputStream:close	()V
      //   274: goto -225 -> 49
      //   277: astore 6
      //   279: aconst_null
      //   280: astore 5
      //   282: aload 8
      //   284: invokevirtual 201	java/io/InputStream:close	()V
      //   287: aload 6
      //   289: athrow
      //   290: astore 7
      //   292: goto -81 -> 211
      //   295: astore 7
      //   297: aload 6
      //   299: astore 5
      //   301: aload 7
      //   303: astore 6
      //   305: goto -23 -> 282
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	308	0	this	Offer
      //   150	36	1	i	int
      //   138	44	2	j	int
      //   172	13	3	k	int
      //   38	59	4	bool	boolean
      //   47	163	5	localObject1	Object
      //   231	35	5	localObject2	Object
      //   280	20	5	localObject3	Object
      //   7	261	6	localObject4	Object
      //   277	21	6	localObject5	Object
      //   303	1	6	localObject6	Object
      //   4	195	7	arrayOfByte	byte[]
      //   205	18	7	localException1	Exception
      //   238	22	7	localException2	Exception
      //   290	1	7	localException3	Exception
      //   295	7	7	localObject7	Object
      //   1	282	8	localBufferedInputStream	java.io.BufferedInputStream
      //   31	97	9	localFile	File
      // Exception table:
      //   from	to	target	type
      //   105	123	205	java/lang/Exception
      //   193	198	205	java/lang/Exception
      //   269	274	205	java/lang/Exception
      //   11	40	231	finally
      //   58	92	231	finally
      //   105	123	231	finally
      //   193	198	231	finally
      //   211	228	231	finally
      //   269	274	231	finally
      //   282	290	231	finally
      //   127	137	238	java/lang/Exception
      //   147	151	238	java/lang/Exception
      //   163	173	238	java/lang/Exception
      //   127	137	277	finally
      //   282	290	290	java/lang/Exception
      //   147	151	295	finally
      //   163	173	295	finally
      //   244	248	295	finally
      //   252	265	295	finally
    }
    
    private String md5(String paramString)
    {
      try
      {
        Object localObject = MessageDigest.getInstance("MD5");
        ((MessageDigest)localObject).reset();
        ((MessageDigest)localObject).update(paramString.getBytes("UTF-8"));
        paramString = ((MessageDigest)localObject).digest();
        localObject = new StringBuffer();
        int i = 0;
        while (i < paramString.length)
        {
          String str = Integer.toHexString(paramString[i] & 0xFF);
          if (str.length() == 1) {
            ((StringBuffer)localObject).append('0');
          }
          ((StringBuffer)localObject).append(str);
          i += 1;
        }
        paramString = ((StringBuffer)localObject).toString();
        return paramString;
      }
      catch (Exception paramString)
      {
        throw new RuntimeException();
      }
    }
    
    public Bitmap getThumb()
    {
      return getThumb(false);
    }
    
    public Bitmap getThumb(boolean paramBoolean)
    {
      if (this.thumbLink == null) {}
      Object localObject1;
      do
      {
        return null;
        localObject1 = getCachedImg();
        if (localObject1 != null) {
          return BitmapFactory.decodeByteArray((byte[])localObject1, 0, localObject1.length);
        }
      } while (paramBoolean);
      synchronized (this.downloadMonitor)
      {
        byte[] arrayOfByte2 = getCachedImg();
        localObject1 = arrayOfByte2;
        if (arrayOfByte2 != null) {
          break label75;
        }
        localObject1 = downloadBitmap();
        if (localObject1 == null) {
          return null;
        }
      }
      cacheImg(arrayOfByte1);
      label75:
      return BitmapFactory.decodeByteArray(arrayOfByte1, 0, arrayOfByte1.length);
    }
    
    public Offer setLink(String paramString)
    {
      this.link = paramString;
      return this;
    }
    
    public Offer setPoints(int paramInt)
    {
      this.points = paramInt;
      return this;
    }
    
    public Offer setRequiredAction(String paramString)
    {
      this.requiredAction = paramString;
      return this;
    }
    
    public Offer setThumb(String paramString)
    {
      this.thumbLink = paramString;
      return this;
    }
    
    public Offer setTitle(String paramString)
    {
      this.title = paramString;
      return this;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("title: ").append(this.title).append(", requiredAction: ").append(this.requiredAction).append(", link: ").append(this.link).append(", thumbLink: ").append(this.thumbLink).append(", thumbFile: ").append(this.thumbFile).append(", points: ").append(this.points);
      return localStringBuilder.toString();
    }
  }
  
  public static abstract interface OfferListener
  {
    public abstract void offersLoaded(List<OfferProvider.Offer> paramList);
    
    public abstract void startOffersLoading();
  }
}
