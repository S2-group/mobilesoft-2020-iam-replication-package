package com.outfit7.talkingfriends.offers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;
import com.outfit7.funnetworks.util.NonObfuscatable;
import com.outfit7.funnetworks.util.e;
import com.outfit7.repackaged.com.google.gson.Gson;
import com.outfit7.repackaged.com.google.gson.reflect.TypeToken;
import com.outfit7.talkingfriends.ad.AdManager;
import com.outfit7.talkingfriends.ad.AdManager.AdManagerCallback;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
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
  protected int exchangeRateDenominator = 0;
  protected boolean hasOwnUI;
  private long lastRewardCheck;
  protected Activity main = AdManager.getAdManagerCallback().getActivity();
  protected int minPoints;
  protected String providerID;
  private Condition timeoutCond = this.timeoutLock.newCondition();
  private Lock timeoutLock = new ReentrantLock();
  
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
    //   5: invokespecial 144	com/outfit7/talkingfriends/offers/OfferProvider$1C:<init>	(Lcom/outfit7/talkingfriends/offers/OfferProvider;)V
    //   8: astore_3
    //   9: aload_0
    //   10: getfield 99	com/outfit7/talkingfriends/offers/OfferProvider:timeoutLock	Ljava/util/concurrent/locks/Lock;
    //   13: invokeinterface 147 1 0
    //   18: aload_2
    //   19: ifnull +9 -> 28
    //   22: aload_2
    //   23: invokeinterface 150 1 0
    //   28: new 11	com/outfit7/talkingfriends/offers/OfferProvider$2
    //   31: dup
    //   32: aload_0
    //   33: aload_1
    //   34: aload_3
    //   35: invokespecial 153	com/outfit7/talkingfriends/offers/OfferProvider$2:<init>	(Lcom/outfit7/talkingfriends/offers/OfferProvider;Ljava/lang/String;Lcom/outfit7/talkingfriends/offers/OfferProvider$1C;)V
    //   38: invokevirtual 156	com/outfit7/talkingfriends/offers/OfferProvider$2:start	()V
    //   41: aload_0
    //   42: getfield 107	com/outfit7/talkingfriends/offers/OfferProvider:timeoutCond	Ljava/util/concurrent/locks/Condition;
    //   45: ldc2_w 35
    //   48: getstatic 162	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   51: invokeinterface 168 4 0
    //   56: ifne +56 -> 112
    //   59: aload_0
    //   60: invokevirtual 171	com/outfit7/talkingfriends/offers/OfferProvider:getCountryCode	()Ljava/lang/String;
    //   63: astore_1
    //   64: invokestatic 115	com/outfit7/talkingfriends/ad/AdManager:getAdManagerCallback	()Lcom/outfit7/talkingfriends/ad/AdManager$AdManagerCallback;
    //   67: ldc 56
    //   69: iconst_2
    //   70: anewarray 4	java/lang/Object
    //   73: dup
    //   74: iconst_0
    //   75: ldc 47
    //   77: aastore
    //   78: dup
    //   79: iconst_1
    //   80: new 173	java/lang/StringBuilder
    //   83: dup
    //   84: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   87: aload_0
    //   88: getfield 176	com/outfit7/talkingfriends/offers/OfferProvider:providerID	Ljava/lang/String;
    //   91: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   94: ldc -74
    //   96: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: aload_1
    //   100: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: invokevirtual 185	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   106: aastore
    //   107: invokeinterface 189 3 0
    //   112: aload_2
    //   113: ifnull +13 -> 126
    //   116: aload_2
    //   117: aload_3
    //   118: getfield 193	com/outfit7/talkingfriends/offers/OfferProvider$1C:offers	Ljava/util/List;
    //   121: invokeinterface 197 2 0
    //   126: aload_0
    //   127: getfield 99	com/outfit7/talkingfriends/offers/OfferProvider:timeoutLock	Ljava/util/concurrent/locks/Lock;
    //   130: invokeinterface 200 1 0
    //   135: aload_3
    //   136: getfield 193	com/outfit7/talkingfriends/offers/OfferProvider$1C:offers	Ljava/util/List;
    //   139: areturn
    //   140: astore_1
    //   141: aload_2
    //   142: ifnull +13 -> 155
    //   145: aload_2
    //   146: aload_3
    //   147: getfield 193	com/outfit7/talkingfriends/offers/OfferProvider$1C:offers	Ljava/util/List;
    //   150: invokeinterface 197 2 0
    //   155: aload_0
    //   156: getfield 99	com/outfit7/talkingfriends/offers/OfferProvider:timeoutLock	Ljava/util/concurrent/locks/Lock;
    //   159: invokeinterface 200 1 0
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
      if ((l1 - l2 > this.CACHING_TIME) || (!((String)localObject2).equals(this.providerID)))
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
            paramList = TAG;
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
  
  private void showMsg(final String paramString)
  {
    if (!AdManager.getAdManagerCallback().isInDebugMode()) {
      return;
    }
    paramString = "Offers: " + paramString;
    AdManager.getAdManagerCallback().getActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast localToast = Toast.makeText(AdManager.getAdManagerCallback().getActivity().getApplicationContext(), paramString, 0);
        localToast.setGravity(17, 0, 0);
        localToast.show();
      }
    });
  }
  
  final void a(int paramInt)
  {
    this.exchangeRateDenominator = paramInt;
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
  
  protected List<Offer> deserialise(String paramString)
  {
    (List)new Gson().fromJson(paramString, new TypeToken() {}.getType());
  }
  
  public void finish() {}
  
  protected String getCountryCode()
  {
    try
    {
      JSONResponse localJSONResponse1 = (JSONResponse)e.a(AdManager.getAdManagerCallback().getActivity(), "jsonResponse", JSONResponse.class);
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
  
  public int getPoints()
  {
    return 0;
  }
  
  public String getProviderID()
  {
    return this.providerID;
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
      //   124	1	2	localHttpGet	org.apache.http.client.methods.HttpGet
      //   128	15	2	localObject2	Object
      //   148	59	2	localObject3	Object
      //   208	13	2	localException2	Exception
      //   7	219	3	localDefaultHttpClient	org.apache.http.impl.client.DefaultHttpClient
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
