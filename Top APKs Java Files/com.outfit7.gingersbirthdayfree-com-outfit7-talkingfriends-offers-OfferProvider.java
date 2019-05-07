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
import com.outfit7.funnetworks.util.Log;
import com.outfit7.funnetworks.util.Logger;
import com.outfit7.funnetworks.util.NonObfuscatable;
import com.outfit7.funnetworks.util.Util;
import com.outfit7.repackaged.com.google.gson.Gson;
import com.outfit7.repackaged.com.google.gson.reflect.TypeToken;
import com.outfit7.talkingfriends.ad.AdManager;
import com.outfit7.talkingfriends.ad.BaseAdManager.AdManagerCallback;
import com.outfit7.talkingfriends.ad.O7AdInfo;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
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
  private static final String KEY_LAST_OFFER_CHECK = "lastOfferCheck";
  private static final String KEY_LAST_OFFER_CLICK = "lastOfferClick";
  private static final String LAST_INTERVAL_PASSED = "lastIntervalPassed";
  private static final long MINIMAL_REWARD_CHECK_INTERVAL = 5000L;
  private static final long OFFER_REQ_TIMEOUT_SECS = 30L;
  private static final int[] REWARD_CHECK_INTERVALS = { 0, 1, 3, 5, 24, 48 };
  private static final String TAG = "OfferProvider";
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
  private long lastRewardCheck;
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
    //   5: invokespecial 166	com/outfit7/talkingfriends/offers/OfferProvider$1C:<init>	(Lcom/outfit7/talkingfriends/offers/OfferProvider;)V
    //   8: astore_3
    //   9: aload_0
    //   10: getfield 114	com/outfit7/talkingfriends/offers/OfferProvider:timeoutLock	Ljava/util/concurrent/locks/Lock;
    //   13: invokeinterface 169 1 0
    //   18: aload_2
    //   19: ifnull +9 -> 28
    //   22: aload_2
    //   23: invokeinterface 172 1 0
    //   28: new 11	com/outfit7/talkingfriends/offers/OfferProvider$2
    //   31: dup
    //   32: aload_0
    //   33: aload_1
    //   34: aload_3
    //   35: invokespecial 175	com/outfit7/talkingfriends/offers/OfferProvider$2:<init>	(Lcom/outfit7/talkingfriends/offers/OfferProvider;Ljava/lang/String;Lcom/outfit7/talkingfriends/offers/OfferProvider$1C;)V
    //   38: invokevirtual 178	com/outfit7/talkingfriends/offers/OfferProvider$2:start	()V
    //   41: aload_0
    //   42: getfield 122	com/outfit7/talkingfriends/offers/OfferProvider:timeoutCond	Ljava/util/concurrent/locks/Condition;
    //   45: ldc2_w 51
    //   48: getstatic 184	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   51: invokeinterface 190 4 0
    //   56: ifne +56 -> 112
    //   59: aload_0
    //   60: invokevirtual 194	com/outfit7/talkingfriends/offers/OfferProvider:getCountryCode	()Ljava/lang/String;
    //   63: astore_1
    //   64: invokestatic 130	com/outfit7/talkingfriends/ad/AdManager:getAdManagerCallback	()Lcom/outfit7/talkingfriends/ad/BaseAdManager$AdManagerCallback;
    //   67: ldc 75
    //   69: iconst_2
    //   70: anewarray 4	java/lang/Object
    //   73: dup
    //   74: iconst_0
    //   75: ldc 66
    //   77: aastore
    //   78: dup
    //   79: iconst_1
    //   80: new 196	java/lang/StringBuilder
    //   83: dup
    //   84: invokespecial 197	java/lang/StringBuilder:<init>	()V
    //   87: aload_0
    //   88: getfield 199	com/outfit7/talkingfriends/offers/OfferProvider:providerID	Ljava/lang/String;
    //   91: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: ldc -51
    //   96: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: aload_1
    //   100: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   106: aastore
    //   107: invokeinterface 212 3 0
    //   112: aload_2
    //   113: ifnull +13 -> 126
    //   116: aload_2
    //   117: aload_3
    //   118: getfield 216	com/outfit7/talkingfriends/offers/OfferProvider$1C:offers	Ljava/util/List;
    //   121: invokeinterface 220 2 0
    //   126: aload_0
    //   127: getfield 114	com/outfit7/talkingfriends/offers/OfferProvider:timeoutLock	Ljava/util/concurrent/locks/Lock;
    //   130: invokeinterface 223 1 0
    //   135: aload_3
    //   136: getfield 216	com/outfit7/talkingfriends/offers/OfferProvider$1C:offers	Ljava/util/List;
    //   139: areturn
    //   140: astore_1
    //   141: aload_2
    //   142: ifnull +13 -> 155
    //   145: aload_2
    //   146: aload_3
    //   147: getfield 216	com/outfit7/talkingfriends/offers/OfferProvider$1C:offers	Ljava/util/List;
    //   150: invokeinterface 220 2 0
    //   155: aload_0
    //   156: getfield 114	com/outfit7/talkingfriends/offers/OfferProvider:timeoutLock	Ljava/util/concurrent/locks/Lock;
    //   159: invokeinterface 223 1 0
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
    if (this.providerID.equals("nooffers")) {
      return;
    }
    long l1 = System.currentTimeMillis();
    Object localObject1 = getSharedPreferences();
    long l2 = ((SharedPreferences)localObject1).getLong("lastOfferUpdate", 0L);
    Object localObject2 = ((SharedPreferences)localObject1).getString("lastProvider", "");
    if ((l1 - l2 > this.CACHING_TIME) || (!((String)localObject2).equals(this.providerID)) || (AdManager.getAdManagerCallback().isInDebugMode()))
    {
      AdManager.getAdManagerCallback().logEvent("offers_offersRequested", new Object[] { "offers_offersProvider", this.providerID });
      localObject2 = getCountryCode();
      getOffers(paramString, paramList);
      if (paramList.size() > 0)
      {
        AdManager.getAdManagerCallback().logEvent("offers_offersReceived", new Object[] { "offers_offersProvider", this.providerID, "offers_numOffersReceived", "" + paramList.size() });
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
          Log.e("OfferProvider", "" + paramString, paramString);
          return;
        }
      }
      AdManager.getAdManagerCallback().logEvent("offers_offersReceivedNone", new Object[] { "offers_offersProvider", this.providerID + "-" + (String)localObject2 });
      return;
    }
    localObject2 = deserialise(((SharedPreferences)localObject1).getString("lastOffers", ""));
    if (localObject2 == null)
    {
      localObject1 = ((SharedPreferences)localObject1).edit();
      ((SharedPreferences.Editor)localObject1).putLong("lastOfferUpdate", 0L);
      ((SharedPreferences.Editor)localObject1).putString("lastProvider", this.providerID);
      ((SharedPreferences.Editor)localObject1).commit();
      checkOffers010(paramString, paramList);
      return;
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
    //   0: invokestatic 130	com/outfit7/talkingfriends/ad/AdManager:getAdManagerCallback	()Lcom/outfit7/talkingfriends/ad/BaseAdManager$AdManagerCallback;
    //   3: invokeinterface 136 1 0
    //   8: astore 12
    //   10: aload 12
    //   12: invokestatic 363	com/outfit7/talkingfriends/ad/AdManager:getAdInfo	(Landroid/content/Context;)Lcom/outfit7/talkingfriends/ad/O7AdInfo;
    //   15: astore 11
    //   17: aload 11
    //   19: getfield 368	com/outfit7/talkingfriends/ad/O7AdInfo:canUse	Z
    //   22: ifne +5 -> 27
    //   25: iconst_0
    //   26: ireturn
    //   27: new 370	org/apache/http/impl/client/DefaultHttpClient
    //   30: dup
    //   31: invokespecial 371	org/apache/http/impl/client/DefaultHttpClient:<init>	()V
    //   34: astore 8
    //   36: aload 12
    //   38: invokestatic 377	com/outfit7/funnetworks/FunNetworks:getProperAuthorityURI	(Landroid/content/Context;)Ljava/lang/String;
    //   41: pop
    //   42: new 379	android/net/Uri$Builder
    //   45: dup
    //   46: invokespecial 380	android/net/Uri$Builder:<init>	()V
    //   49: astore 9
    //   51: aload 9
    //   53: ldc_w 382
    //   56: invokevirtual 386	android/net/Uri$Builder:scheme	(Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   59: pop
    //   60: aload 9
    //   62: aload 12
    //   64: invokestatic 377	com/outfit7/funnetworks/FunNetworks:getProperAuthorityURI	(Landroid/content/Context;)Ljava/lang/String;
    //   67: invokevirtual 389	android/net/Uri$Builder:authority	(Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   70: pop
    //   71: aload 9
    //   73: new 196	java/lang/StringBuilder
    //   76: dup
    //   77: invokespecial 197	java/lang/StringBuilder:<init>	()V
    //   80: ldc_w 391
    //   83: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   86: aload_0
    //   87: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   90: ldc_w 393
    //   93: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   96: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   99: invokevirtual 396	android/net/Uri$Builder:path	(Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   102: pop
    //   103: new 398	java/util/ArrayList
    //   106: dup
    //   107: invokespecial 399	java/util/ArrayList:<init>	()V
    //   110: astore 10
    //   112: aload_2
    //   113: astore 7
    //   115: aload_2
    //   116: ifnonnull +10 -> 126
    //   119: aload 12
    //   121: invokevirtual 402	android/app/Activity:getPackageName	()Ljava/lang/String;
    //   124: astore 7
    //   126: aload 10
    //   128: iconst_2
    //   129: anewarray 230	java/lang/String
    //   132: dup
    //   133: iconst_0
    //   134: ldc_w 404
    //   137: aastore
    //   138: dup
    //   139: iconst_1
    //   140: aload 7
    //   142: aastore
    //   143: invokeinterface 407 2 0
    //   148: pop
    //   149: aload 10
    //   151: iconst_2
    //   152: anewarray 230	java/lang/String
    //   155: dup
    //   156: iconst_0
    //   157: ldc_w 409
    //   160: aastore
    //   161: dup
    //   162: iconst_1
    //   163: aload_0
    //   164: aastore
    //   165: invokeinterface 407 2 0
    //   170: pop
    //   171: aload 11
    //   173: getfield 412	com/outfit7/talkingfriends/ad/O7AdInfo:ID	Ljava/lang/String;
    //   176: ifnull +134 -> 310
    //   179: iconst_2
    //   180: anewarray 230	java/lang/String
    //   183: astore_0
    //   184: aload_0
    //   185: iconst_0
    //   186: ldc_w 414
    //   189: aastore
    //   190: aload_0
    //   191: iconst_1
    //   192: aload 11
    //   194: getfield 412	com/outfit7/talkingfriends/ad/O7AdInfo:ID	Ljava/lang/String;
    //   197: aastore
    //   198: aload 10
    //   200: aload_0
    //   201: invokeinterface 407 2 0
    //   206: pop
    //   207: new 196	java/lang/StringBuilder
    //   210: dup
    //   211: invokespecial 197	java/lang/StringBuilder:<init>	()V
    //   214: astore_0
    //   215: aload 10
    //   217: invokeinterface 418 1 0
    //   222: astore_1
    //   223: aload_1
    //   224: invokeinterface 423 1 0
    //   229: ifeq +99 -> 328
    //   232: aload_1
    //   233: invokeinterface 427 1 0
    //   238: checkcast 429	[Ljava/lang/String;
    //   241: astore_2
    //   242: aload_0
    //   243: aload_2
    //   244: iconst_0
    //   245: aaload
    //   246: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   249: ldc_w 431
    //   252: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   255: aload_2
    //   256: iconst_1
    //   257: aaload
    //   258: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   261: ldc_w 433
    //   264: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   267: pop
    //   268: aload_2
    //   269: iconst_0
    //   270: aaload
    //   271: ldc_w 409
    //   274: invokevirtual 234	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   277: ifne -54 -> 223
    //   280: aload 9
    //   282: aload_2
    //   283: iconst_0
    //   284: aaload
    //   285: aload_2
    //   286: iconst_1
    //   287: aaload
    //   288: invokevirtual 437	android/net/Uri$Builder:appendQueryParameter	(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   291: pop
    //   292: goto -69 -> 223
    //   295: astore_0
    //   296: aload 8
    //   298: invokeinterface 443 1 0
    //   303: invokeinterface 448 1 0
    //   308: aload_0
    //   309: athrow
    //   310: iconst_2
    //   311: anewarray 230	java/lang/String
    //   314: astore_0
    //   315: aload_0
    //   316: iconst_0
    //   317: ldc_w 450
    //   320: aastore
    //   321: aload_0
    //   322: iconst_1
    //   323: aload_1
    //   324: aastore
    //   325: goto -127 -> 198
    //   328: aload_0
    //   329: ldc 33
    //   331: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   334: pop
    //   335: ldc_w 452
    //   338: new 196	java/lang/StringBuilder
    //   341: dup
    //   342: invokespecial 197	java/lang/StringBuilder:<init>	()V
    //   345: ldc_w 454
    //   348: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   351: aload_0
    //   352: invokevirtual 305	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   355: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   358: invokestatic 460	com/outfit7/funnetworks/util/Logger:debug	(Ljava/lang/String;Ljava/lang/String;)V
    //   361: ldc_w 462
    //   364: invokestatic 468	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
    //   367: astore_1
    //   368: aload_1
    //   369: aload_0
    //   370: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   373: invokevirtual 472	java/lang/String:getBytes	()[B
    //   376: invokevirtual 476	java/security/MessageDigest:update	([B)V
    //   379: aload 9
    //   381: ldc_w 478
    //   384: aload_1
    //   385: invokevirtual 481	java/security/MessageDigest:digest	()[B
    //   388: invokestatic 487	com/outfit7/funnetworks/util/Util:convToHex	([B)Ljava/lang/String;
    //   391: invokevirtual 437	android/net/Uri$Builder:appendQueryParameter	(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   394: pop
    //   395: iconst_0
    //   396: istore 6
    //   398: iconst_0
    //   399: istore 5
    //   401: new 489	org/apache/http/client/methods/HttpGet
    //   404: dup
    //   405: aload 9
    //   407: invokevirtual 493	android/net/Uri$Builder:build	()Landroid/net/Uri;
    //   410: invokevirtual 496	android/net/Uri:toString	()Ljava/lang/String;
    //   413: invokespecial 499	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   416: astore_0
    //   417: ldc_w 452
    //   420: new 196	java/lang/StringBuilder
    //   423: dup
    //   424: invokespecial 197	java/lang/StringBuilder:<init>	()V
    //   427: ldc_w 501
    //   430: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   433: aload_0
    //   434: invokeinterface 507 1 0
    //   439: invokevirtual 305	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   442: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   445: invokestatic 460	com/outfit7/funnetworks/util/Logger:debug	(Ljava/lang/String;Ljava/lang/String;)V
    //   448: iload 6
    //   450: istore_3
    //   451: aload 8
    //   453: aload_0
    //   454: invokeinterface 511 2 0
    //   459: astore_0
    //   460: iload 6
    //   462: istore_3
    //   463: aload_0
    //   464: invokeinterface 517 1 0
    //   469: astore_1
    //   470: iload 6
    //   472: istore_3
    //   473: ldc_w 452
    //   476: new 196	java/lang/StringBuilder
    //   479: dup
    //   480: invokespecial 197	java/lang/StringBuilder:<init>	()V
    //   483: ldc_w 519
    //   486: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   489: aload_1
    //   490: invokevirtual 305	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   493: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   496: invokestatic 460	com/outfit7/funnetworks/util/Logger:debug	(Ljava/lang/String;Ljava/lang/String;)V
    //   499: iload 6
    //   501: istore_3
    //   502: aload_1
    //   503: invokeinterface 524 1 0
    //   508: istore 4
    //   510: iload 4
    //   512: sipush 200
    //   515: if_icmpeq +47 -> 562
    //   518: aload 8
    //   520: invokeinterface 443 1 0
    //   525: invokeinterface 448 1 0
    //   530: iconst_0
    //   531: ireturn
    //   532: astore_0
    //   533: ldc 57
    //   535: new 196	java/lang/StringBuilder
    //   538: dup
    //   539: invokespecial 197	java/lang/StringBuilder:<init>	()V
    //   542: ldc_w 256
    //   545: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   548: aload_0
    //   549: invokevirtual 305	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   552: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   555: aload_0
    //   556: invokestatic 311	com/outfit7/funnetworks/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   559: goto -164 -> 395
    //   562: iload 6
    //   564: istore_3
    //   565: aload_0
    //   566: invokeinterface 528 1 0
    //   571: astore_0
    //   572: aload_0
    //   573: ifnonnull +17 -> 590
    //   576: aload 8
    //   578: invokeinterface 443 1 0
    //   583: invokeinterface 448 1 0
    //   588: iconst_0
    //   589: ireturn
    //   590: aload_0
    //   591: ldc_w 530
    //   594: invokestatic 535	org/apache/http/util/EntityUtils:toString	(Lorg/apache/http/HttpEntity;Ljava/lang/String;)Ljava/lang/String;
    //   597: astore_1
    //   598: ldc_w 452
    //   601: new 196	java/lang/StringBuilder
    //   604: dup
    //   605: invokespecial 197	java/lang/StringBuilder:<init>	()V
    //   608: ldc_w 537
    //   611: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   614: aload_1
    //   615: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   618: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   621: invokestatic 460	com/outfit7/funnetworks/util/Logger:debug	(Ljava/lang/String;Ljava/lang/String;)V
    //   624: new 539	com/outfit7/repackaged/com/google/gson/Gson
    //   627: dup
    //   628: invokespecial 540	com/outfit7/repackaged/com/google/gson/Gson:<init>	()V
    //   631: aload_1
    //   632: ldc 22
    //   634: invokevirtual 544	com/outfit7/repackaged/com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   637: checkcast 22	com/outfit7/talkingfriends/offers/OfferProvider$O7JSONResponse
    //   640: getfield 547	com/outfit7/talkingfriends/offers/OfferProvider$O7JSONResponse:points	I
    //   643: istore 4
    //   645: iload 4
    //   647: istore_3
    //   648: aload_0
    //   649: invokeinterface 552 1 0
    //   654: iload 4
    //   656: istore_3
    //   657: aload 8
    //   659: invokeinterface 443 1 0
    //   664: invokeinterface 448 1 0
    //   669: iload_3
    //   670: ireturn
    //   671: astore_1
    //   672: ldc 57
    //   674: new 196	java/lang/StringBuilder
    //   677: dup
    //   678: invokespecial 197	java/lang/StringBuilder:<init>	()V
    //   681: ldc_w 256
    //   684: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   687: aload_1
    //   688: invokevirtual 305	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   691: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   694: aload_1
    //   695: invokestatic 311	com/outfit7/funnetworks/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   698: iload 6
    //   700: istore_3
    //   701: aload_0
    //   702: invokeinterface 552 1 0
    //   707: iload 5
    //   709: istore_3
    //   710: goto -53 -> 657
    //   713: astore_0
    //   714: ldc 57
    //   716: new 196	java/lang/StringBuilder
    //   719: dup
    //   720: invokespecial 197	java/lang/StringBuilder:<init>	()V
    //   723: ldc_w 256
    //   726: invokevirtual 203	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   729: aload_0
    //   730: invokevirtual 305	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   733: invokevirtual 208	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   736: aload_0
    //   737: invokestatic 311	com/outfit7/funnetworks/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   740: goto -83 -> 657
    //   743: astore_1
    //   744: iload 6
    //   746: istore_3
    //   747: aload_0
    //   748: invokeinterface 552 1 0
    //   753: iload 6
    //   755: istore_3
    //   756: aload_1
    //   757: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	758	0	paramString1	String
    //   0	758	1	paramString2	String
    //   0	758	2	paramString3	String
    //   450	306	3	i	int
    //   508	147	4	j	int
    //   399	309	5	k	int
    //   396	358	6	m	int
    //   113	28	7	str	String
    //   34	624	8	localDefaultHttpClient	DefaultHttpClient
    //   49	357	9	localBuilder	Uri.Builder
    //   110	106	10	localArrayList	ArrayList
    //   15	178	11	localO7AdInfo	O7AdInfo
    //   8	112	12	localActivity	Activity
    // Exception table:
    //   from	to	target	type
    //   36	112	295	finally
    //   119	126	295	finally
    //   126	184	295	finally
    //   190	198	295	finally
    //   198	223	295	finally
    //   223	292	295	finally
    //   310	315	295	finally
    //   328	361	295	finally
    //   361	395	295	finally
    //   401	448	295	finally
    //   451	460	295	finally
    //   463	470	295	finally
    //   473	499	295	finally
    //   502	510	295	finally
    //   533	559	295	finally
    //   565	572	295	finally
    //   648	654	295	finally
    //   701	707	295	finally
    //   714	740	295	finally
    //   747	753	295	finally
    //   756	758	295	finally
    //   361	395	532	java/security/NoSuchAlgorithmException
    //   590	645	671	java/lang/Exception
    //   451	460	713	java/lang/Exception
    //   463	470	713	java/lang/Exception
    //   473	499	713	java/lang/Exception
    //   502	510	713	java/lang/Exception
    //   565	572	713	java/lang/Exception
    //   648	654	713	java/lang/Exception
    //   701	707	713	java/lang/Exception
    //   747	753	713	java/lang/Exception
    //   756	758	713	java/lang/Exception
    //   590	645	743	finally
    //   672	698	743	finally
  }
  
  private SharedPreferences getSharedPreferences()
  {
    return this.main.getSharedPreferences("offers", 0);
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
    new StringBuilder().append("Offers: ").append(paramString).toString();
    AdManager.getAdManagerCallback().getActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (OfferProvider.this.toast == null)
        {
          OfferProvider.access$002(OfferProvider.this, Toast.makeText(OfferProvider.this.main.getApplicationContext(), "", 0));
          OfferProvider.this.toast.setGravity(17, 0, 0);
        }
        OfferProvider.this.toast.setText(paramString);
        OfferProvider.this.toast.show();
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
        FunNetworks.getProperAuthorityURI(localActivity);
        localBuilder = new Uri.Builder();
        localBuilder.scheme("http");
        localBuilder.authority(FunNetworks.getProperAuthorityURI(localActivity));
        if (i == 0) {
          break label381;
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
          localArrayList.add(new String[] { "points", "" + paramInt });
        }
        localArrayList.add(new String[] { "provider", paramString1 });
        if (localO7AdInfo.ID == null) {
          break label389;
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
      label381:
      String str = "/spend-points";
      continue;
      label389:
      paramString1 = new String[2];
      paramString1[0] = "udid";
      paramString1[1] = paramString2;
    }
    paramString1.append("A7INQETRTPFDRG8QWQWA");
    Logger.debug("==500==", "sb = " + paramString1);
    try
    {
      paramString2 = MessageDigest.getInstance("SHA1");
      paramString2.update(paramString1.toString().getBytes());
      localBuilder.appendQueryParameter("s", Util.convToHex(paramString2.digest()));
      paramString1 = new HttpGet(localBuilder.build().toString());
      Logger.debug("==500==", "request = " + paramString1.getURI());
    }
    catch (NoSuchAlgorithmException paramString1)
    {
      try
      {
        for (;;)
        {
          paramString1 = localDefaultHttpClient.execute(paramString1);
          paramString2 = paramString1.getStatusLine();
          Logger.debug("==500==", "statusLine = " + paramString2);
          if (paramString2.getStatusCode() == 200) {
            break;
          }
          Logger.error("OfferProvider", "Status code is not 200, returning");
          localDefaultHttpClient.getConnectionManager().shutdown();
          return false;
          paramString1 = paramString1;
          Logger.error("OfferProvider", "" + paramString1, paramString1);
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
        Log.e("OfferProvider", "" + paramString1, paramString1);
        localDefaultHttpClient.getConnectionManager().shutdown();
      }
    }
  }
  
  boolean canCheckReward()
  {
    Logger.debug("OfferProvider", "canCheckReward()");
    long l2 = System.currentTimeMillis();
    if (l2 - this.lastRewardCheck < 5000L) {
      return false;
    }
    SharedPreferences localSharedPreferences = getSharedPreferences();
    long l1 = localSharedPreferences.getLong("lastOfferClick", -1L);
    int i = localSharedPreferences.getInt("lastIntervalPassed", 0);
    StringBuilder localStringBuilder = new StringBuilder().append("lastOfferClickTime: ");
    if (l1 == -1L) {}
    for (Object localObject = " never";; localObject = new Date(l1))
    {
      Logger.debug("OfferProvider", localObject);
      if (l1 != -1L) {
        break;
      }
      return false;
    }
    Logger.debug("OfferProvider", "rewardCheckIntervalIndex: " + i);
    Logger.debug("OfferProvider", "       CurrentTime: " + new Date());
    if (i >= REWARD_CHECK_INTERVALS.length)
    {
      Logger.debug("OfferProvider", "The reward was not received after " + REWARD_CHECK_INTERVALS[(REWARD_CHECK_INTERVALS.length - 1)] + " hours");
      localSharedPreferences.edit().remove("lastOfferClick").commit();
      return false;
    }
    Logger.debug("OfferProvider", "seconds: " + REWARD_CHECK_INTERVALS[i]);
    if (l2 - l1 < 3600000 * REWARD_CHECK_INTERVALS[i])
    {
      Logger.debug("OfferProvider", "less than " + REWARD_CHECK_INTERVALS[i] + "hours have passed since the offer click, checking will take place again at " + new Date(3600000 * REWARD_CHECK_INTERVALS[i] + l1));
      return false;
    }
    long l3 = localSharedPreferences.getLong("lastOfferCheck", l2);
    if (i == 0) {}
    for (l1 = 3600000L;; l1 = 900000L)
    {
      Logger.debug("OfferProvider", "     lastCheckTime: " + new Date(l3) + "   rewardCheckTime: " + l1 / 60000L);
      if (l2 - l3 <= l1) {
        break;
      }
      Logger.debug("OfferProvider", "more than " + l1 / 60000L + " minutes have passed, go to next waiting interval");
      localSharedPreferences.edit().putInt("lastIntervalPassed", i + 1).commit();
      localSharedPreferences.edit().remove("lastOfferCheck").commit();
      return false;
    }
    Logger.debug("OfferProvider", "CHECK = TRUE");
    if (!localSharedPreferences.contains("lastOfferCheck")) {
      localSharedPreferences.edit().putLong("lastOfferCheck", l2).commit();
    }
    this.lastRewardCheck = l2;
    return true;
  }
  
  public boolean canPreload()
  {
    return this.canPreload;
  }
  
  void checkRewards()
  {
    int i = getPoints();
    AdManager.getAdManagerCallback().gotPoints(this, i);
    if (i > 0)
    {
      Logger.debug("OfferProvider", "got reward, no longer checking BE for reward");
      getSharedPreferences().edit().remove("lastOfferClick").commit();
    }
  }
  
  public void clearCache()
  {
    SharedPreferences.Editor localEditor = getSharedPreferences().edit();
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
    Object localObject1 = null;
    try
    {
      localObject2 = (JSONResponse)Util.JSONToObj(AdManager.getAdManagerCallback().getActivity(), "jsonResponse", JSONResponse.class);
      localObject1 = localObject2;
    }
    catch (IOException localIOException)
    {
      Object localObject2;
      for (;;) {}
    }
    localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = new JSONResponse();
    }
    return ((JSONResponse)localObject2).clientCountryCode;
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
    return Util.getUniqueUserID(this.main);
  }
  
  public boolean hasOwnUI()
  {
    return this.hasOwnUI;
  }
  
  protected boolean isPackageInstalled(String paramString)
  {
    boolean bool2 = false;
    Iterator localIterator = AdManager.getAdManagerCallback().getActivity().getPackageManager().getInstalledPackages(0).iterator();
    do
    {
      bool1 = bool2;
      if (!localIterator.hasNext()) {
        break;
      }
    } while (!((PackageInfo)localIterator.next()).packageName.equals(paramString));
    boolean bool1 = true;
    return bool1;
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
  
  void setExchangeRateDenominator(int paramInt)
  {
    this.exchangeRateDenominator = paramInt;
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
    paramOffer = getSharedPreferences();
    if (paramOffer.contains("lastOfferClick")) {
      paramOffer.edit().remove("lastOfferClick").commit();
    }
    if (paramOffer.contains("lastIntervalPassed")) {
      paramOffer.edit().remove("lastIntervalPassed").commit();
    }
    if (paramOffer.contains("lastOfferCheck")) {
      paramOffer.edit().remove("lastOfferCheck").commit();
    }
    paramOffer.edit().putLong("lastOfferClick", System.currentTimeMillis()).commit();
    this.lastRewardCheck = 0L;
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
      //   6: invokestatic 38	com/outfit7/talkingfriends/ad/AdManager:getAdManagerCallback	()Lcom/outfit7/talkingfriends/ad/BaseAdManager$AdManagerCallback;
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
      //   93: ldc 91
      //   95: new 93	java/lang/StringBuilder
      //   98: dup
      //   99: invokespecial 94	java/lang/StringBuilder:<init>	()V
      //   102: ldc 96
      //   104: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   107: aload_1
      //   108: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   111: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   114: aload_1
      //   115: invokestatic 113	com/outfit7/funnetworks/util/Logger:error	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   118: goto -74 -> 44
      //   121: astore_1
      //   122: aload_0
      //   123: monitorexit
      //   124: aload_1
      //   125: athrow
      //   126: astore_1
      //   127: ldc 91
      //   129: new 93	java/lang/StringBuilder
      //   132: dup
      //   133: invokespecial 94	java/lang/StringBuilder:<init>	()V
      //   136: ldc 96
      //   138: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   141: aload_1
      //   142: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   145: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   148: aload_1
      //   149: invokestatic 113	com/outfit7/funnetworks/util/Logger:error	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   152: aload_3
      //   153: invokevirtual 89	java/io/OutputStream:close	()V
      //   156: goto -112 -> 44
      //   159: astore_1
      //   160: aload_3
      //   161: invokevirtual 89	java/io/OutputStream:close	()V
      //   164: aload_1
      //   165: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	166	0	this	Offer
      //   0	166	1	paramArrayOfByte	byte[]
      //   39	2	2	bool	boolean
      //   22	139	3	localObject	Object
      // Exception table:
      //   from	to	target	type
      //   64	80	92	java/lang/Exception
      //   85	89	92	java/lang/Exception
      //   152	156	92	java/lang/Exception
      //   160	166	92	java/lang/Exception
      //   2	35	121	finally
      //   35	40	121	finally
      //   47	64	121	finally
      //   64	80	121	finally
      //   85	89	121	finally
      //   93	118	121	finally
      //   152	156	121	finally
      //   160	166	121	finally
      //   80	85	126	java/lang/Exception
      //   80	85	159	finally
      //   127	152	159	finally
    }
    
    private byte[] downloadBitmap()
    {
      DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
      try
      {
        localObject1 = new HttpGet(this.thumbLink);
      }
      catch (Exception localException1) {}finally
      {
        Object localObject1;
        int i;
        Object localObject2;
        ByteArrayOutputStream localByteArrayOutputStream;
        localDefaultHttpClient.getConnectionManager().shutdown();
      }
    }
    
    /* Error */
    private byte[] getCachedImg()
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 6
      //   3: aload_0
      //   4: monitorenter
      //   5: new 32	java/io/File
      //   8: dup
      //   9: invokestatic 38	com/outfit7/talkingfriends/ad/AdManager:getAdManagerCallback	()Lcom/outfit7/talkingfriends/ad/BaseAdManager$AdManagerCallback;
      //   12: invokeinterface 44 1 0
      //   17: invokevirtual 50	android/app/Activity:getCacheDir	()Ljava/io/File;
      //   20: ldc 52
      //   22: invokespecial 55	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
      //   25: astore 7
      //   27: aload 7
      //   29: invokevirtual 59	java/io/File:exists	()Z
      //   32: istore 4
      //   34: iload 4
      //   36: ifne +12 -> 48
      //   39: aload 6
      //   41: astore 5
      //   43: aload_0
      //   44: monitorexit
      //   45: aload 5
      //   47: areturn
      //   48: aload 6
      //   50: astore 5
      //   52: aload 7
      //   54: invokevirtual 65	java/io/File:isDirectory	()Z
      //   57: ifeq -14 -> 43
      //   60: new 32	java/io/File
      //   63: dup
      //   64: aload 7
      //   66: aload_0
      //   67: aload_0
      //   68: getfield 67	com/outfit7/talkingfriends/offers/OfferProvider$Offer:thumbLink	Ljava/lang/String;
      //   71: invokespecial 71	com/outfit7/talkingfriends/offers/OfferProvider$Offer:md5	(Ljava/lang/String;)Ljava/lang/String;
      //   74: invokespecial 55	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
      //   77: astore 8
      //   79: aload 8
      //   81: invokevirtual 59	java/io/File:exists	()Z
      //   84: istore 4
      //   86: aload 6
      //   88: astore 5
      //   90: iload 4
      //   92: ifeq -49 -> 43
      //   95: aconst_null
      //   96: astore 6
      //   98: aconst_null
      //   99: astore 7
      //   101: aconst_null
      //   102: astore 5
      //   104: new 196	java/io/BufferedInputStream
      //   107: dup
      //   108: new 198	java/io/FileInputStream
      //   111: dup
      //   112: aload 8
      //   114: invokespecial 199	java/io/FileInputStream:<init>	(Ljava/io/File;)V
      //   117: invokespecial 202	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
      //   120: astore 9
      //   122: aload 8
      //   124: invokevirtual 206	java/io/File:length	()J
      //   127: l2i
      //   128: newarray byte
      //   130: astore 8
      //   132: iconst_0
      //   133: istore_2
      //   134: aload 8
      //   136: astore 6
      //   138: aload 8
      //   140: astore 7
      //   142: aload 8
      //   144: arraylength
      //   145: istore_1
      //   146: iload_1
      //   147: ifne +58 -> 205
      //   150: aload 8
      //   152: astore 5
      //   154: aload 9
      //   156: invokevirtual 209	java/io/InputStream:close	()V
      //   159: aload 8
      //   161: astore 5
      //   163: goto -120 -> 43
      //   166: astore 6
      //   168: ldc 91
      //   170: new 93	java/lang/StringBuilder
      //   173: dup
      //   174: invokespecial 94	java/lang/StringBuilder:<init>	()V
      //   177: ldc 96
      //   179: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   182: aload 6
      //   184: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   187: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   190: aload 6
      //   192: invokestatic 113	com/outfit7/funnetworks/util/Logger:error	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   195: goto -152 -> 43
      //   198: astore 5
      //   200: aload_0
      //   201: monitorexit
      //   202: aload 5
      //   204: athrow
      //   205: aload 8
      //   207: astore 6
      //   209: aload 8
      //   211: astore 7
      //   213: aload 9
      //   215: aload 8
      //   217: iload_2
      //   218: iload_1
      //   219: invokevirtual 213	java/io/InputStream:read	([BII)I
      //   222: istore_3
      //   223: iload_3
      //   224: iconst_m1
      //   225: if_icmpeq -75 -> 150
      //   228: iload_2
      //   229: iload_3
      //   230: iadd
      //   231: istore_2
      //   232: iload_1
      //   233: iload_3
      //   234: isub
      //   235: istore_1
      //   236: goto -90 -> 146
      //   239: astore 5
      //   241: aload 6
      //   243: astore 7
      //   245: ldc 91
      //   247: new 93	java/lang/StringBuilder
      //   250: dup
      //   251: invokespecial 94	java/lang/StringBuilder:<init>	()V
      //   254: ldc 96
      //   256: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   259: aload 5
      //   261: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   264: invokevirtual 107	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   267: aload 5
      //   269: invokestatic 113	com/outfit7/funnetworks/util/Logger:error	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
      //   272: aload 6
      //   274: astore 5
      //   276: aload 9
      //   278: invokevirtual 209	java/io/InputStream:close	()V
      //   281: aload 6
      //   283: astore 5
      //   285: goto -242 -> 43
      //   288: astore 6
      //   290: aload 7
      //   292: astore 5
      //   294: aload 9
      //   296: invokevirtual 209	java/io/InputStream:close	()V
      //   299: aload 7
      //   301: astore 5
      //   303: aload 6
      //   305: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	306	0	this	Offer
      //   145	91	1	i	int
      //   133	99	2	j	int
      //   222	13	3	k	int
      //   32	59	4	bool	boolean
      //   41	121	5	localObject1	Object
      //   198	5	5	localObject2	Object
      //   239	29	5	localException1	Exception
      //   274	28	5	localObject3	Object
      //   1	136	6	localObject4	Object
      //   166	25	6	localException2	Exception
      //   207	75	6	localObject5	Object
      //   288	16	6	localObject6	Object
      //   25	275	7	localObject7	Object
      //   77	139	8	localObject8	Object
      //   120	175	9	localBufferedInputStream	java.io.BufferedInputStream
      // Exception table:
      //   from	to	target	type
      //   104	122	166	java/lang/Exception
      //   154	159	166	java/lang/Exception
      //   276	281	166	java/lang/Exception
      //   294	299	166	java/lang/Exception
      //   303	306	166	java/lang/Exception
      //   5	34	198	finally
      //   52	86	198	finally
      //   104	122	198	finally
      //   154	159	198	finally
      //   168	195	198	finally
      //   276	281	198	finally
      //   294	299	198	finally
      //   303	306	198	finally
      //   122	132	239	java/lang/Exception
      //   142	146	239	java/lang/Exception
      //   213	223	239	java/lang/Exception
      //   122	132	288	finally
      //   142	146	288	finally
      //   213	223	288	finally
      //   245	272	288	finally
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
      localStringBuilder.append("title: ").append(this.title).append(", ").append("requiredAction: ").append(this.requiredAction).append(", ").append("link: ").append(this.link).append(", ").append("thumbLink: ").append(this.thumbLink).append(", ").append("thumbFile: ").append(this.thumbFile).append(", ").append("points: ").append(this.points);
      return localStringBuilder.toString();
    }
  }
  
  public static abstract interface OfferListener
  {
    public abstract void offersLoaded(List<OfferProvider.Offer> paramList);
    
    public abstract void startOffersLoading();
  }
}
