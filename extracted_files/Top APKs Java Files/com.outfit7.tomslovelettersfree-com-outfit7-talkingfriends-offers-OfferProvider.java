package com.outfit7.talkingfriends.offers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.outfit7.funnetworks.util.j;
import com.outfit7.repackaged.com.google.gson.Gson;
import com.outfit7.talkingfriends.ad.AdManager;
import com.outfit7.talkingfriends.ad.AdManager.AdManagerCallback;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class OfferProvider
{
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
  private long lastRewardCheck;
  protected Activity main = AdManager.getAdManagerCallback().getActivity();
  protected int minPoints;
  protected List<OfferProvider.Offer> offers;
  protected String providerID;
  private Condition timeoutCond = this.timeoutLock.newCondition();
  private Lock timeoutLock = new ReentrantLock();
  
  public OfferProvider() {}
  
  private List<OfferProvider.Offer> checkOffers(String paramString)
  {
    return checkOffers(paramString, null);
  }
  
  /* Error */
  private List<OfferProvider.Offer> checkOffers(String paramString, OfferProvider.OfferListener paramOfferListener)
  {
    // Byte code:
    //   0: new 119	com/outfit7/talkingfriends/offers/OfferProvider$1C
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 122	com/outfit7/talkingfriends/offers/OfferProvider$1C:<init>	(Lcom/outfit7/talkingfriends/offers/OfferProvider;)V
    //   8: astore_3
    //   9: aload_0
    //   10: getfield 78	com/outfit7/talkingfriends/offers/OfferProvider:timeoutLock	Ljava/util/concurrent/locks/Lock;
    //   13: invokeinterface 125 1 0
    //   18: aload_2
    //   19: ifnull +9 -> 28
    //   22: aload_2
    //   23: invokeinterface 130 1 0
    //   28: new 132	com/outfit7/talkingfriends/offers/OfferProvider$1
    //   31: dup
    //   32: aload_0
    //   33: aload_3
    //   34: aload_1
    //   35: invokespecial 135	com/outfit7/talkingfriends/offers/OfferProvider$1:<init>	(Lcom/outfit7/talkingfriends/offers/OfferProvider;Lcom/outfit7/talkingfriends/offers/OfferProvider$1C;Ljava/lang/String;)V
    //   38: invokevirtual 138	com/outfit7/talkingfriends/offers/OfferProvider$1:start	()V
    //   41: aload_0
    //   42: getfield 86	com/outfit7/talkingfriends/offers/OfferProvider:timeoutCond	Ljava/util/concurrent/locks/Condition;
    //   45: ldc2_w 13
    //   48: getstatic 144	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   51: invokeinterface 150 4 0
    //   56: ifne +56 -> 112
    //   59: aload_0
    //   60: invokevirtual 153	com/outfit7/talkingfriends/offers/OfferProvider:getCountryCode	()Ljava/lang/String;
    //   63: astore_1
    //   64: invokestatic 92	com/outfit7/talkingfriends/ad/AdManager:getAdManagerCallback	()Lcom/outfit7/talkingfriends/ad/AdManager$AdManagerCallback;
    //   67: ldc 34
    //   69: iconst_2
    //   70: anewarray 4	java/lang/Object
    //   73: dup
    //   74: iconst_0
    //   75: ldc 25
    //   77: aastore
    //   78: dup
    //   79: iconst_1
    //   80: new 155	java/lang/StringBuilder
    //   83: dup
    //   84: invokespecial 156	java/lang/StringBuilder:<init>	()V
    //   87: aload_0
    //   88: getfield 158	com/outfit7/talkingfriends/offers/OfferProvider:providerID	Ljava/lang/String;
    //   91: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: ldc -92
    //   96: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: aload_1
    //   100: invokevirtual 162	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   106: aastore
    //   107: invokeinterface 171 3 0
    //   112: aload_2
    //   113: ifnull +13 -> 126
    //   116: aload_2
    //   117: aload_3
    //   118: getfield 173	com/outfit7/talkingfriends/offers/OfferProvider$1C:offers	Ljava/util/List;
    //   121: invokeinterface 177 2 0
    //   126: aload_0
    //   127: getfield 78	com/outfit7/talkingfriends/offers/OfferProvider:timeoutLock	Ljava/util/concurrent/locks/Lock;
    //   130: invokeinterface 180 1 0
    //   135: aload_3
    //   136: getfield 173	com/outfit7/talkingfriends/offers/OfferProvider$1C:offers	Ljava/util/List;
    //   139: areturn
    //   140: astore_1
    //   141: aload_2
    //   142: ifnull +13 -> 155
    //   145: aload_2
    //   146: aload_3
    //   147: getfield 173	com/outfit7/talkingfriends/offers/OfferProvider$1C:offers	Ljava/util/List;
    //   150: invokeinterface 177 2 0
    //   155: aload_0
    //   156: getfield 78	com/outfit7/talkingfriends/offers/OfferProvider:timeoutLock	Ljava/util/concurrent/locks/Lock;
    //   159: invokeinterface 180 1 0
    //   164: aload_1
    //   165: athrow
    //   166: astore_1
    //   167: goto -55 -> 112
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	170	0	this	OfferProvider
    //   0	170	1	paramString	String
    //   0	170	2	paramOfferListener	OfferProvider.OfferListener
    //   8	139	3	local1C	OfferProvider.1C
    // Exception table:
    //   from	to	target	type
    //   22	28	140	finally
    //   28	41	140	finally
    //   41	112	140	finally
    //   41	112	166	java/lang/InterruptedException
  }
  
  private List<OfferProvider.Offer> checkOffers010(String paramString)
  {
    for (;;)
    {
      if (this.providerID.equals("nooffers")) {
        return new LinkedList();
      }
      long l1 = System.currentTimeMillis();
      Object localObject = this.main.getSharedPreferences("offers", 0);
      long l2 = ((SharedPreferences)localObject).getLong("lastOfferUpdate", 0L);
      String str = ((SharedPreferences)localObject).getString("lastProvider", "");
      if ((l1 - l2 > this.CACHING_TIME) || (!str.equals(this.providerID)))
      {
        AdManager.getAdManagerCallback().logEvent("offers_offersRequested", new Object[] { "offers_offersProvider", this.providerID });
        str = getCountryCode();
        this.offers = new LinkedList();
        getOffers(paramString);
        if (this.offers.size() > 0) {
          AdManager.getAdManagerCallback().logEvent("offers_offersReceived", new Object[] { "offers_offersProvider", this.providerID, "offers_numOffersReceived", this.offers.size() });
        }
      }
      do
      {
        for (;;)
        {
          try
          {
            cleanUpCache();
            paramString = ((SharedPreferences)localObject).edit();
            paramString.putLong("lastOfferUpdate", l1);
            paramString.putString("lastProvider", this.providerID);
            paramString.putString("lastOffers", serialise());
            paramString.commit();
            return this.offers;
          }
          catch (Exception paramString)
          {
            localObject = TAG;
            new StringBuilder().append(paramString);
            continue;
          }
          AdManager.getAdManagerCallback().logEvent("offers_offersReceivedNone", new Object[] { "offers_offersProvider", this.providerID + "-" + str });
        }
        this.offers = deserialise(((SharedPreferences)localObject).getString("lastOffers", ""));
      } while (this.offers != null);
      localObject = ((SharedPreferences)localObject).edit();
      ((SharedPreferences.Editor)localObject).putLong("lastOfferUpdate", 0L);
      ((SharedPreferences.Editor)localObject).putString("lastProvider", this.providerID);
      ((SharedPreferences.Editor)localObject).commit();
    }
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
        Arrays.sort((Object[])localObject, new OfferProvider.2(this));
        int i = 0;
        while (i < localObject.length - 100)
        {
          localObject[i].delete();
          i += 1;
        }
      }
    }
  }
  
  final boolean a()
  {
    long l = System.currentTimeMillis();
    if (l - this.lastRewardCheck < 3000L) {
      return false;
    }
    this.lastRewardCheck = l;
    return true;
  }
  
  public boolean canPreload()
  {
    return this.canPreload;
  }
  
  public void checkRewards() {}
  
  public void clearCache()
  {
    SharedPreferences.Editor localEditor = this.main.getSharedPreferences("offers", 0).edit();
    localEditor.putLong("lastOfferUpdate", 0L);
    localEditor.commit();
  }
  
  protected List<OfferProvider.Offer> deserialise(String paramString)
  {
    return (List)new Gson().a(paramString, new OfferProvider.4(this).getType());
  }
  
  public void finish() {}
  
  protected String getCountryCode()
  {
    try
    {
      OfferProvider.JSONResponse localJSONResponse1 = (OfferProvider.JSONResponse)j.a(AdManager.getAdManagerCallback().getActivity(), "jsonResponse", OfferProvider.JSONResponse.class);
      OfferProvider.JSONResponse localJSONResponse2 = localJSONResponse1;
      if (localJSONResponse1 == null) {
        localJSONResponse2 = new OfferProvider.JSONResponse();
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
  
  protected abstract void getOffers(String paramString);
  
  public int getPoints()
  {
    return 0;
  }
  
  public String getProviderID()
  {
    return this.providerID;
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
  
  public List<OfferProvider.Offer> loadOffers(String paramString, OfferProvider.OfferListener paramOfferListener)
  {
    return checkOffers(paramString, paramOfferListener);
  }
  
  public void onResume() {}
  
  public void release() {}
  
  protected String serialise()
  {
    return new Gson().a(this.offers, new OfferProvider.3(this).getType());
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
  }
  
  public void startOffer(OfferProvider.Offer paramOffer)
  {
    AdManager.getAdManagerCallback().logEvent("offers_offerClicked", new Object[] { "offers_offersProvider", this.providerID });
  }
  
  public void startSession() {}
  
  public String toString()
  {
    return this.providerID;
  }
}
