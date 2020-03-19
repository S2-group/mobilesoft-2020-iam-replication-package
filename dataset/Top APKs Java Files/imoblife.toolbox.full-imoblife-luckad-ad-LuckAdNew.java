package imoblife.luckad.ad;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import base.util.PackageUtil;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import imoblife.luckad.R.drawable;
import imoblife.luckad.R.id;
import imoblife.luckad.R.layout;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LuckAdNew
{
  public static final String GOOGLE_PLAY_PACKAGENAME = "com.android.vending";
  public static final String MARKET = "market";
  public static final String MARKET_ANDROID_COM = "market.android.com";
  private static final int ONE_MINUTE = 120000;
  private static final int ONE_SECOND = 2000;
  public static final String PLAY_GOOGLE_COM = "play.google.com";
  public static final String TAG = LuckAdNew.class.getSimpleName();
  private static Activity activity;
  private static List<AdInfo> adPreparedList;
  private static int adsCount;
  private static int adsDownloadNum = 15;
  private static View.OnClickListener adsListner;
  private static String adsPlan;
  private static String adsStrOfJson = "";
  private static int icon_rs;
  private static int indexOfAdList = 0;
  private static int indexOfAdPrepared;
  private static LuckAdNew instance;
  private static boolean isRunningDownloadAds;
  private static int sizeOfAdPrepared;
  private static LinearLayout titlebar_for_ad;
  private static int updateTimeLmt = 4;
  private final String AD_JSON_URL = "https://s3.amazonaws.com/AllInOneToolbox/recommend/adsjson.html";
  private final String AD_TYPE_URL = "https://s3.amazonaws.com/AllInOneToolbox/recommend/";
  private final String PARSE_ALL = "all";
  private final String PARSE_HOUR = "hour";
  private List<AdInfo> ad_list;
  private List<AdInfo> ad_list_native;
  private List<AdInfo> ad_list_wall;
  private Context context;
  private ExternalListener externalListener;
  private int fbTimeLMT = 1;
  private boolean isGooglePlaySchema = true;
  private boolean isLoadAdsFromPrepareList = false;
  private boolean isMinAdPrice = false;
  private boolean isNextPreload = false;
  private boolean isPubPreload = true;
  private boolean isStillPostImpession = false;
  private boolean isTrackLuckAd = false;
  public boolean isYMPreload = false;
  private IAdLoadListener listener;
  private double minAdPrice = 0.2D;
  private String openWindowType = "P";
  private String parseOrder = "M";
  private int preloadAdsNum = 2;
  private int prepareAdNum = 9;
  private final String spliteTokenString = "###";
  private boolean urlCheckCache = true;
  
  static
  {
    adsCount = 5;
    isRunningDownloadAds = false;
    sizeOfAdPrepared = 3;
    indexOfAdPrepared = 0;
    adsPlan = "P";
  }
  
  private LuckAdNew(Context paramContext)
  {
    this.context = paramContext;
  }
  
  public static int BKDRHash(String paramString)
  {
    int j = 0;
    int i = 0;
    while (i < paramString.length())
    {
      j = j * 131 + paramString.charAt(i);
      i += 1;
    }
    return 0x7FFFFFFF & j;
  }
  
  public static LuckAdNew get(Context paramContext)
  {
    if (instance == null) {
      instance = new LuckAdNew(paramContext);
    }
    return instance;
  }
  
  public static LuckAdNew get(Context paramContext, Activity paramActivity)
  {
    activity = paramActivity;
    if (instance == null) {
      instance = new LuckAdNew(paramContext);
    }
    return instance;
  }
  
  public static List<AdInfo> getAdPreparedList()
  {
    return adPreparedList;
  }
  
  public static String getAdsPlan()
  {
    return adsPlan;
  }
  
  public static String getAdsStrOfJson()
  {
    return adsStrOfJson;
  }
  
  public static int getIndexOfAdPrepare()
  {
    int i = indexOfAdPrepared;
    indexOfAdPrepared += 1;
    if (indexOfAdPrepared >= sizeOfAdPrepared) {
      indexOfAdPrepared = 0;
    }
    return i;
  }
  
  public static Bundle getMetaData(Context paramContext)
  {
    Log.d("count", "getMetaData");
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128).metaData;
      return paramContext;
    }
    catch (Exception paramContext)
    {
      isRunningDownloadAds = false;
      throw new RuntimeException("Could not read meta data in the manifest.", paramContext);
    }
  }
  
  public static List<String> getUserAppList(Context paramContext)
  {
    Object localObject = paramContext.getPackageManager().getInstalledPackages(0);
    paramContext = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((localPackageInfo.applicationInfo.flags & 0x1) != 1) {
        paramContext.add(localPackageInfo.packageName);
      }
    }
    return paramContext;
  }
  
  public static String getUserSecurityAppList(Context paramContext)
  {
    paramContext = getUserAppList(paramContext);
    StringBuilder localStringBuilder = new StringBuilder();
    if ((paramContext != null) && (paramContext.size() > 0))
    {
      int i = 0;
      while (i < paramContext.size())
      {
        localStringBuilder.append(hashResultTo64(BKDRHash((String)paramContext.get(i)))).append(",");
        i += 1;
      }
    }
    localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
    return localStringBuilder.toString();
  }
  
  static char get_64_element(int paramInt)
  {
    char c = '\000';
    if ((paramInt >= 0) && (paramInt <= 9)) {
      c = (char)(paramInt + 48);
    }
    do
    {
      return c;
      if ((paramInt >= 10) && (paramInt <= 35)) {
        return (char)(paramInt + 87);
      }
      if ((paramInt >= 36) && (paramInt <= 61)) {
        return (char)(paramInt + 29);
      }
      if (paramInt == 62) {
        return '+';
      }
    } while (paramInt != 63);
    return '-';
  }
  
  public static String hashResultTo64(int paramInt)
  {
    System.out.println("hash1: " + paramInt);
    StringBuffer localStringBuffer = new StringBuffer("");
    while (paramInt != 0)
    {
      int i = paramInt / 64;
      localStringBuffer.append(get_64_element(paramInt % 64));
      paramInt = i;
    }
    return localStringBuffer.reverse().toString();
  }
  
  public static void inflateAdOther(AdInfo paramAdInfo, View paramView, Context paramContext)
  {
    Log.i(TAG, "Native Ad::inflateAd ");
    try
    {
      String str = paramAdInfo.getImageUrl();
      ImageView localImageView;
      if ((str != null) && (!str.trim().equals("")))
      {
        localImageView = (ImageView)paramView.findViewById(R.id.pop_screen_ad_bg);
        Picasso.with(paramContext).load(str).into(localImageView);
      }
      str = paramAdInfo.getIconUrl();
      if ((str != null) && (!str.trim().equals("")))
      {
        localImageView = (ImageView)paramView.findViewById(R.id.adIcon);
        Picasso.with(paramContext).load(str).into(localImageView);
      }
      ((TextView)paramView.findViewById(R.id.adTitle)).setText(paramAdInfo.getTilte());
      ((TextView)paramView.findViewById(R.id.adBody)).setText(paramAdInfo.getContent());
      paramAdInfo = paramAdInfo.getAdFinalLink();
      ((LinearLayout)paramView.findViewById(R.id.ad_layout)).setOnClickListener(new LuckAdNew.13(paramContext, paramAdInfo));
      return;
    }
    catch (Exception paramAdInfo)
    {
      paramAdInfo.printStackTrace();
    }
  }
  
  public static boolean isLuckAdSupport(Context paramContext)
  {
    return (PackageUtil.isPackageInstalled(paramContext, "com.android.vending")) || (PackageUtil.isPackageInstalled(paramContext, "market.android.com"));
  }
  
  public static void setAdPreparedList(List<AdInfo> paramList)
  {
    adPreparedList = paramList;
  }
  
  public static void setAdsPlan(String paramString)
  {
    adsPlan = paramString;
  }
  
  public static void setAdsStrOfJson(String paramString)
  {
    adsStrOfJson = paramString;
  }
  
  public static void setIsRunningDownloadAds(boolean paramBoolean)
  {
    isRunningDownloadAds = paramBoolean;
  }
  
  public void checkAd()
  {
    try
    {
      initAdPrparedList();
      if (!isRunningDownloadAds)
      {
        logPrint(TAG, "LuckAdNew checkAd执行!");
        isRunningDownloadAds = true;
        initListner();
        if (getAdsStrOfJson().equals("")) {
          new LuckAdNew.AdLoadTask(this, this.context).execute(new Void[0]);
        }
        for (;;)
        {
          new LuckAdNew.AdTypeLoadTask(this, this.context).execute(new Void[0]);
          new LuckAdNew.AdLoadYMWallTask(this, this.context, 10).execute(new Void[0]);
          isRunningDownloadAds = false;
          return;
          if (CheckTime.isTimeUp(this.context, 24))
          {
            logPrint(TAG, "Updating the AdsStrOfJson!!!!!!!!!!!!!!!!");
            new LuckAdNew.AdLoadTask(this, this.context).execute(new Void[0]);
          }
        }
      }
      return;
    }
    catch (Exception localException)
    {
      isRunningDownloadAds = false;
      localException.printStackTrace();
    }
  }
  
  public void checkAdForNetMobile()
  {
    logPrint("NETChangeForAdsMobile", "isRunningDownloadAds-->" + isRunningDownloadAds);
    if (!isRunningDownloadAds)
    {
      logPrint("NETChangeForAdsMobile", "update ad list-mobile->");
      isRunningDownloadAds = true;
    }
  }
  
  public void checkAdForNetWifi()
  {
    logPrint("NETChangeForAdsWifi", "isRunningDownloadAds-->" + isRunningDownloadAds);
    if (!isRunningDownloadAds)
    {
      logPrint("NETChangeForAdsWifi", "update ad list-wifi->");
      isRunningDownloadAds = true;
    }
  }
  
  /* Error */
  public void clickNoneFacebookAds(Activity paramActivity, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 600	imoblife/luckad/ad/LuckAdNew:getAdList_Native	()Ljava/util/List;
    //   4: ifnull +461 -> 465
    //   7: aload_0
    //   8: ldc_w 602
    //   11: new 378	java/lang/StringBuilder
    //   14: dup
    //   15: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   18: ldc_w 604
    //   21: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   24: aload_0
    //   25: invokevirtual 600	imoblife/luckad/ad/LuckAdNew:getAdList_Native	()Ljava/util/List;
    //   28: invokeinterface 382 1 0
    //   33: invokevirtual 416	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   36: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   39: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   42: aload_0
    //   43: ldc_w 606
    //   46: new 378	java/lang/StringBuilder
    //   49: dup
    //   50: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   53: ldc_w 604
    //   56: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: getstatic 121	imoblife/luckad/ad/LuckAdNew:indexOfAdList	I
    //   62: invokevirtual 416	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   65: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   68: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   71: aload_0
    //   72: getstatic 121	imoblife/luckad/ad/LuckAdNew:indexOfAdList	I
    //   75: invokevirtual 609	imoblife/luckad/ad/LuckAdNew:getAdUninstalledIndexGlispaByStepNew	(I)I
    //   78: putstatic 121	imoblife/luckad/ad/LuckAdNew:indexOfAdList	I
    //   81: aconst_null
    //   82: astore 12
    //   84: aload_0
    //   85: invokevirtual 600	imoblife/luckad/ad/LuckAdNew:getAdList_Native	()Ljava/util/List;
    //   88: getstatic 121	imoblife/luckad/ad/LuckAdNew:indexOfAdList	I
    //   91: invokeinterface 385 2 0
    //   96: checkcast 445	imoblife/luckad/ad/AdInfo
    //   99: astore 13
    //   101: aload 13
    //   103: astore 12
    //   105: aload 12
    //   107: ifnull +178 -> 285
    //   110: aload 12
    //   112: invokevirtual 612	imoblife/luckad/ad/AdInfo:getLoadStatus	()Ljava/lang/String;
    //   115: ldc_w 614
    //   118: invokevirtual 454	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   121: ifeq +164 -> 285
    //   124: getstatic 121	imoblife/luckad/ad/LuckAdNew:indexOfAdList	I
    //   127: iconst_1
    //   128: iadd
    //   129: putstatic 121	imoblife/luckad/ad/LuckAdNew:indexOfAdList	I
    //   132: getstatic 121	imoblife/luckad/ad/LuckAdNew:indexOfAdList	I
    //   135: aload_0
    //   136: invokevirtual 600	imoblife/luckad/ad/LuckAdNew:getAdList_Native	()Ljava/util/List;
    //   139: invokeinterface 382 1 0
    //   144: if_icmplt +7 -> 151
    //   147: iconst_0
    //   148: putstatic 121	imoblife/luckad/ad/LuckAdNew:indexOfAdList	I
    //   151: aload_0
    //   152: aload_1
    //   153: iload_2
    //   154: iload_3
    //   155: iload 4
    //   157: iload 5
    //   159: iload 6
    //   161: iload 7
    //   163: iload 8
    //   165: iload 9
    //   167: aload 12
    //   169: invokevirtual 618	imoblife/luckad/ad/LuckAdNew:showNewAds	(Landroid/app/Activity;IIIIIIIILimoblife/luckad/ad/AdInfo;)V
    //   172: aload_0
    //   173: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   176: ldc_w 620
    //   179: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   182: aload_0
    //   183: getfield 182	imoblife/luckad/ad/LuckAdNew:isStillPostImpession	Z
    //   186: istore 11
    //   188: iload 11
    //   190: ifeq +338 -> 528
    //   193: new 622	imoblife/luckad/ad/LuckAdNew$11
    //   196: dup
    //   197: aload_0
    //   198: aload 12
    //   200: invokevirtual 625	imoblife/luckad/ad/AdInfo:getRecordUrl	()Ljava/lang/String;
    //   203: invokespecial 628	imoblife/luckad/ad/LuckAdNew$11:<init>	(Limoblife/luckad/ad/LuckAdNew;Ljava/lang/String;)V
    //   206: invokevirtual 631	imoblife/luckad/ad/LuckAdNew$11:start	()V
    //   209: aload_0
    //   210: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   213: ldc_w 633
    //   216: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   219: return
    //   220: astore 13
    //   222: aload_0
    //   223: ldc_w 635
    //   226: ldc_w 637
    //   229: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   232: goto -127 -> 105
    //   235: astore_1
    //   236: aload_1
    //   237: invokevirtual 527	java/lang/Exception:printStackTrace	()V
    //   240: return
    //   241: astore_1
    //   242: aload_0
    //   243: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   246: ldc_w 639
    //   249: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   252: iconst_0
    //   253: putstatic 125	imoblife/luckad/ad/LuckAdNew:isRunningDownloadAds	Z
    //   256: aload_1
    //   257: invokevirtual 527	java/lang/Exception:printStackTrace	()V
    //   260: goto -88 -> 172
    //   263: astore_1
    //   264: aload_0
    //   265: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   268: ldc_w 641
    //   271: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   274: iconst_0
    //   275: putstatic 125	imoblife/luckad/ad/LuckAdNew:isRunningDownloadAds	Z
    //   278: aload_1
    //   279: invokevirtual 527	java/lang/Exception:printStackTrace	()V
    //   282: goto -73 -> 209
    //   285: aload 12
    //   287: ifnull +114 -> 401
    //   290: aload 12
    //   292: invokevirtual 612	imoblife/luckad/ad/AdInfo:getLoadStatus	()Ljava/lang/String;
    //   295: ldc_w 643
    //   298: invokevirtual 454	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   301: istore 11
    //   303: iload 11
    //   305: ifeq +96 -> 401
    //   308: aload_0
    //   309: getstatic 129	imoblife/luckad/ad/LuckAdNew:indexOfAdPrepared	I
    //   312: invokevirtual 646	imoblife/luckad/ad/LuckAdNew:getAdPreparedUninstalledIndex	(I)I
    //   315: istore 10
    //   317: getstatic 248	imoblife/luckad/ad/LuckAdNew:adPreparedList	Ljava/util/List;
    //   320: iload 10
    //   322: invokeinterface 385 2 0
    //   327: checkcast 445	imoblife/luckad/ad/AdInfo
    //   330: astore 12
    //   332: iload 10
    //   334: iconst_1
    //   335: iadd
    //   336: putstatic 129	imoblife/luckad/ad/LuckAdNew:indexOfAdPrepared	I
    //   339: aload 12
    //   341: ifnull +24 -> 365
    //   344: aload_0
    //   345: aload_1
    //   346: iload_2
    //   347: iload_3
    //   348: iload 4
    //   350: iload 5
    //   352: iload 6
    //   354: iload 7
    //   356: iload 8
    //   358: iload 9
    //   360: aload 12
    //   362: invokevirtual 618	imoblife/luckad/ad/LuckAdNew:showNewAds	(Landroid/app/Activity;IIIIIIIILimoblife/luckad/ad/AdInfo;)V
    //   365: getstatic 121	imoblife/luckad/ad/LuckAdNew:indexOfAdList	I
    //   368: iconst_1
    //   369: iadd
    //   370: putstatic 121	imoblife/luckad/ad/LuckAdNew:indexOfAdList	I
    //   373: getstatic 121	imoblife/luckad/ad/LuckAdNew:indexOfAdList	I
    //   376: aload_0
    //   377: invokevirtual 600	imoblife/luckad/ad/LuckAdNew:getAdList_Native	()Ljava/util/List;
    //   380: invokeinterface 382 1 0
    //   385: if_icmplt +143 -> 528
    //   388: iconst_0
    //   389: putstatic 121	imoblife/luckad/ad/LuckAdNew:indexOfAdList	I
    //   392: return
    //   393: astore_1
    //   394: aload_1
    //   395: invokevirtual 527	java/lang/Exception:printStackTrace	()V
    //   398: goto -33 -> 365
    //   401: aload_0
    //   402: getstatic 129	imoblife/luckad/ad/LuckAdNew:indexOfAdPrepared	I
    //   405: invokevirtual 646	imoblife/luckad/ad/LuckAdNew:getAdPreparedUninstalledIndex	(I)I
    //   408: istore 10
    //   410: getstatic 248	imoblife/luckad/ad/LuckAdNew:adPreparedList	Ljava/util/List;
    //   413: iload 10
    //   415: invokeinterface 385 2 0
    //   420: checkcast 445	imoblife/luckad/ad/AdInfo
    //   423: astore 12
    //   425: iload 10
    //   427: iconst_1
    //   428: iadd
    //   429: putstatic 129	imoblife/luckad/ad/LuckAdNew:indexOfAdPrepared	I
    //   432: aload 12
    //   434: ifnull +94 -> 528
    //   437: aload_0
    //   438: aload_1
    //   439: iload_2
    //   440: iload_3
    //   441: iload 4
    //   443: iload 5
    //   445: iload 6
    //   447: iload 7
    //   449: iload 8
    //   451: iload 9
    //   453: aload 12
    //   455: invokevirtual 618	imoblife/luckad/ad/LuckAdNew:showNewAds	(Landroid/app/Activity;IIIIIIIILimoblife/luckad/ad/AdInfo;)V
    //   458: return
    //   459: astore_1
    //   460: aload_1
    //   461: invokevirtual 527	java/lang/Exception:printStackTrace	()V
    //   464: return
    //   465: aload_0
    //   466: getstatic 129	imoblife/luckad/ad/LuckAdNew:indexOfAdPrepared	I
    //   469: invokevirtual 646	imoblife/luckad/ad/LuckAdNew:getAdPreparedUninstalledIndex	(I)I
    //   472: istore 10
    //   474: getstatic 248	imoblife/luckad/ad/LuckAdNew:adPreparedList	Ljava/util/List;
    //   477: iload 10
    //   479: invokeinterface 385 2 0
    //   484: checkcast 445	imoblife/luckad/ad/AdInfo
    //   487: astore 12
    //   489: iload 10
    //   491: iconst_1
    //   492: iadd
    //   493: putstatic 129	imoblife/luckad/ad/LuckAdNew:indexOfAdPrepared	I
    //   496: aload 12
    //   498: ifnull +30 -> 528
    //   501: aload_0
    //   502: aload_1
    //   503: iload_2
    //   504: iload_3
    //   505: iload 4
    //   507: iload 5
    //   509: iload 6
    //   511: iload 7
    //   513: iload 8
    //   515: iload 9
    //   517: aload 12
    //   519: invokevirtual 618	imoblife/luckad/ad/LuckAdNew:showNewAds	(Landroid/app/Activity;IIIIIIIILimoblife/luckad/ad/AdInfo;)V
    //   522: return
    //   523: astore_1
    //   524: aload_1
    //   525: invokevirtual 527	java/lang/Exception:printStackTrace	()V
    //   528: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	529	0	this	LuckAdNew
    //   0	529	1	paramActivity	Activity
    //   0	529	2	paramInt1	int
    //   0	529	3	paramInt2	int
    //   0	529	4	paramInt3	int
    //   0	529	5	paramInt4	int
    //   0	529	6	paramInt5	int
    //   0	529	7	paramInt6	int
    //   0	529	8	paramInt7	int
    //   0	529	9	paramInt8	int
    //   315	178	10	i	int
    //   186	118	11	bool	boolean
    //   82	436	12	localObject	Object
    //   99	3	13	localAdInfo	AdInfo
    //   220	1	13	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   84	101	220	java/lang/Exception
    //   0	81	235	java/lang/Exception
    //   110	151	235	java/lang/Exception
    //   172	188	235	java/lang/Exception
    //   209	219	235	java/lang/Exception
    //   222	232	235	java/lang/Exception
    //   242	260	235	java/lang/Exception
    //   264	282	235	java/lang/Exception
    //   290	303	235	java/lang/Exception
    //   365	392	235	java/lang/Exception
    //   394	398	235	java/lang/Exception
    //   460	464	235	java/lang/Exception
    //   524	528	235	java/lang/Exception
    //   151	172	241	java/lang/Exception
    //   193	209	263	java/lang/Exception
    //   308	339	393	java/lang/Exception
    //   344	365	393	java/lang/Exception
    //   401	432	459	java/lang/Exception
    //   437	458	459	java/lang/Exception
    //   465	496	523	java/lang/Exception
    //   501	522	523	java/lang/Exception
  }
  
  public void clickOtherNativeAds(int paramInt)
  {
    clickNoneFacebookAds(activity, R.layout.ad_indirect, R.drawable.ad_icon1, R.id.pop_screen_ad_bg, R.id.adTitle, R.id.adBody, paramInt, R.id.adIcon, R.id.closeIcon);
  }
  
  public String dealString(String paramString)
  {
    if (paramString == null) {
      return "";
    }
    return paramString.trim();
  }
  
  public void firstCheckLoadAds()
  {
    int j = getAdList_Native().size();
    int i = 0;
    if (i < j)
    {
      AdInfo localAdInfo = (AdInfo)getAdList_Native().get(i);
      if (!localAdInfo.getAdsType().toLowerCase().equals("cpa")) {}
      while (!localAdInfo.getLoadStatus().equals("init"))
      {
        i += 1;
        break;
      }
      ((AdInfo)getAdList_Native().get(i)).setLoadStatus("loading");
      new LoadLinkTaskByStepNew(this.context, getListener(), i).execute(new Integer[0]);
    }
  }
  
  public List<AdInfo> getAdList()
  {
    return this.ad_list;
  }
  
  public List<AdInfo> getAdList_Native()
  {
    return this.ad_list_native;
  }
  
  public int getAdPreparedUninstalledIndex(int paramInt)
  {
    for (;;)
    {
      int i;
      try
      {
        logPrint("getAdPreparedUninstalledIndex", "index-->" + paramInt + "-sizeOfAdPrepared->" + sizeOfAdPrepared);
        int j = paramInt;
        int m = 1;
        if (paramInt < sizeOfAdPrepared)
        {
          i = paramInt;
          int k = m;
          paramInt = j;
          if (i < sizeOfAdPrepared)
          {
            AdInfo localAdInfo = (AdInfo)adPreparedList.get(i);
            if (localAdInfo != null)
            {
              logPrint("getAdPreparedUninstalledIndex11111", "unistalled-->" + i + "--name-->" + localAdInfo.getTilte());
              if (!PackageUtil.isPackageInstalled(this.context, localAdInfo.getPkgName()))
              {
                logPrint("getAdPreparedUninstalledIndex22222", "unistalled-->" + i + "--name-->" + localAdInfo.getTilte());
                paramInt = i;
                k = 0;
              }
            }
          }
          else
          {
            if (k != 0) {
              paramInt = 0;
            }
            return paramInt;
          }
        }
        else
        {
          return 0;
        }
      }
      catch (Exception localException)
      {
        return 0;
      }
      i += 1;
    }
  }
  
  public int getAdUninstalledIndexGlispaByStepNew(int paramInt)
  {
    int j = paramInt;
    int i = paramInt;
    try
    {
      if (paramInt >= this.ad_list_native.size()) {
        i = 0;
      }
      for (;;)
      {
        i = j;
        AdInfo localAdInfo;
        if (paramInt < this.ad_list_native.size())
        {
          localAdInfo = (AdInfo)this.ad_list_native.get(paramInt);
          if (!PackageUtil.isPackageInstalled(this.context, localAdInfo.getPkgName())) {
            i = paramInt;
          }
        }
        else
        {
          localAdInfo = (AdInfo)this.ad_list_native.get(i);
          if ((!localAdInfo.getLoadStatus().equals("succeed")) && (!localAdInfo.getLoadStatus().equals("fail"))) {
            break label438;
          }
          logPrint(TAG, "g-status->" + localAdInfo.getLoadStatus());
          int k = i + 1;
          paramInt = 0;
          j = k;
          if (k >= this.ad_list_native.size()) {
            j = 0;
          }
          localAdInfo = (AdInfo)this.ad_list_native.get(j);
          logPrint(TAG, "g-next-status->" + localAdInfo.getLoadStatus());
          if (localAdInfo.getLoadStatus().equals("init"))
          {
            localAdInfo.setLoadStatus("loading");
            new LoadLinkTaskByStepNew(this.context, getListener(), j).execute(new Integer[0]);
          }
          if (this.isNextPreload)
          {
            if (j != 0)
            {
              j += 1;
              k = this.ad_list_native.size();
              paramInt = j;
              if (j >= k) {
                paramInt = 0;
              }
            }
            if (paramInt <= 0) {}
          }
        }
        label438:
        do
        {
          try
          {
            localAdInfo = (AdInfo)this.ad_list_native.get(paramInt);
            logPrint(TAG, "g-next-next-status->" + localAdInfo.getLoadStatus());
            if (localAdInfo.getLoadStatus().equals("init"))
            {
              localAdInfo.setLoadStatus("loading");
              new LoadLinkTaskByStepNew(this.context, getListener(), paramInt).execute(new Integer[0]);
            }
            return i;
          }
          catch (Exception localException1)
          {
            localException1.printStackTrace();
            return i;
          }
          logPrint(TAG, "g-already installed->" + paramInt);
          paramInt += 1;
          break;
          if (localException1.getLoadStatus().equals("loading"))
          {
            logPrint(TAG, "g-status->" + localException1.getLoadStatus());
            return i;
          }
        } while (!localException1.getLoadStatus().equals("init"));
        logPrint(TAG, "g-status->" + localException1.getLoadStatus());
        localException1.setLoadStatus("loading");
        new LoadLinkTaskByStepNew(this.context, getListener(), i).execute(new Integer[0]);
        return i;
        paramInt = i;
      }
      return 0;
    }
    catch (Exception localException2) {}
  }
  
  public List<AdInfo> getAd_list_wall()
  {
    return this.ad_list_wall;
  }
  
  public View.OnClickListener getAdsListner()
  {
    return adsListner;
  }
  
  public int getAdscount()
  {
    return adsCount;
  }
  
  public String getAdtypeName()
  {
    String str = "adtype_v4.html";
    Bundle localBundle = getMetaData(this.context);
    if (localBundle != null)
    {
      str = localBundle.getString("adtype_name");
      logPrint(TAG, "getAdtypeName---> " + str);
    }
    return str;
  }
  
  public long getAid()
  {
    long l = getMetaData(this.context).getInt("luckad_aid");
    logPrint(getClass().getSimpleName(), "getKey(): " + l);
    return l;
  }
  
  public ArrayList<AdInfo> getAppiaAdsArray(String paramString)
  {
    localArrayList = new ArrayList();
    if ((paramString == null) || (paramString.trim().equals(""))) {}
    for (;;)
    {
      return localArrayList;
      Object localObject = DocumentBuilderFactory.newInstance();
      try
      {
        paramString = ((DocumentBuilderFactory)localObject).newDocumentBuilder().parse(new ByteArrayInputStream(paramString.getBytes("UTF-8")));
        localObject = paramString.getDocumentElement().getElementsByTagName("ad");
        if ((localObject == null) || (((NodeList)localObject).getLength() <= 0)) {
          continue;
        }
        int i = 0;
        if (i >= ((NodeList)localObject).getLength()) {
          continue;
        }
        String str1 = paramString.getElementsByTagName("appId").item(i).getFirstChild().getNodeValue();
        if (str1 != null)
        {
          boolean bool = PackageUtil.isPackageInstalled(this.context, str1);
          if (!bool) {
            break label160;
          }
        }
        for (;;)
        {
          i += 1;
          break;
          try
          {
            label160:
            AdInfo localAdInfo = new AdInfo();
            localAdInfo.setPkgName(str1);
            String str2 = paramString.getElementsByTagName("bidRate").item(i).getFirstChild().getNodeValue();
            try
            {
              double d = Double.parseDouble(str2);
              if ((this.isMinAdPrice) && (d < this.minAdPrice)) {
                logPrint(TAG, "the appia price is low->" + d + "<" + this.minAdPrice);
              }
            }
            catch (Exception localException2)
            {
              localException2.printStackTrace();
              localAdInfo.setAdsPrice(str2);
              localAdInfo.setAdsOrigin("Appia");
              String str3 = paramString.getElementsByTagName("clickProxyURL").item(i).getFirstChild().getNodeValue();
              localAdInfo.setTrackUrl(str3);
              String str4 = paramString.getElementsByTagName("impressionTrackingURL").item(i).getFirstChild().getNodeValue();
              localAdInfo.setRecordUrl(str4);
              String str5 = paramString.getElementsByTagName("productDescription").item(i).getFirstChild().getNodeValue();
              localAdInfo.setContent(str5);
              String str6 = paramString.getElementsByTagName("productImage").item(i).getFirstChild().getNodeValue();
              localAdInfo.setImageUrl(str6);
              String str7 = paramString.getElementsByTagName("productName").item(i).getFirstChild().getNodeValue();
              localAdInfo.setTitle(str7);
              String str8 = paramString.getElementsByTagName("productThumbnail").item(i).getFirstChild().getNodeValue();
              localAdInfo.setIconUrl(str8);
              logPrint(TAG, "APPIA::pkg-->" + str1);
              logPrint(TAG, "APPIA::price-->" + str2);
              logPrint(TAG, "APPIA::clickUrl-->" + str3);
              logPrint(TAG, "APPIA::impUrl-->" + str4);
              logPrint(TAG, "APPIA::descrption-->" + str5);
              logPrint(TAG, "APPIA::imageUrl-->" + str6);
              logPrint(TAG, "APPIA::title-->" + str7);
              logPrint(TAG, "APPIA::iconUrl-->" + str8);
              logPrint(TAG, "APPIA::origin-->" + localAdInfo.getAdsOrigin());
              localAdInfo.setConverted(false);
              localArrayList.add(localAdInfo);
            }
          }
          catch (Exception localException1)
          {
            localException1.printStackTrace();
          }
        }
        return localArrayList;
      }
      catch (ParserConfigurationException paramString)
      {
        paramString.printStackTrace();
        return localArrayList;
      }
      catch (SAXException paramString)
      {
        paramString.printStackTrace();
        return localArrayList;
      }
      catch (IOException paramString)
      {
        paramString.printStackTrace();
        return localArrayList;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
  }
  
  public ExternalListener getExternalListener()
  {
    return this.externalListener;
  }
  
  public int getFbTimeLMT()
  {
    return this.fbTimeLMT;
  }
  
  /* Error */
  public ArrayList<AdInfo> getGlispaAdsArray(String paramString)
  {
    // Byte code:
    //   0: new 338	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 339	java/util/ArrayList:<init>	()V
    //   7: astore_3
    //   8: aload_1
    //   9: ifnull +15 -> 24
    //   12: aload_1
    //   13: invokevirtual 451	java/lang/String:trim	()Ljava/lang/String;
    //   16: ldc 117
    //   18: invokevirtual 454	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   21: ifeq +5 -> 26
    //   24: aload_3
    //   25: areturn
    //   26: new 923	org/json/JSONObject
    //   29: dup
    //   30: aload_1
    //   31: invokevirtual 451	java/lang/String:trim	()Ljava/lang/String;
    //   34: invokespecial 924	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   37: ldc_w 926
    //   40: invokevirtual 930	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   43: astore_1
    //   44: iconst_0
    //   45: istore_2
    //   46: iload_2
    //   47: aload_1
    //   48: invokevirtual 933	org/json/JSONArray:length	()I
    //   51: if_icmpge -27 -> 24
    //   54: aload_1
    //   55: iload_2
    //   56: invokevirtual 934	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   59: checkcast 923	org/json/JSONObject
    //   62: astore 5
    //   64: aload_0
    //   65: getfield 198	imoblife/luckad/ad/LuckAdNew:context	Landroid/content/Context;
    //   68: aload 5
    //   70: ldc_w 936
    //   73: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   76: invokestatic 535	base/util/PackageUtil:isPackageInstalled	(Landroid/content/Context;Ljava/lang/String;)Z
    //   79: ifeq +6 -> 85
    //   82: goto +467 -> 549
    //   85: new 445	imoblife/luckad/ad/AdInfo
    //   88: dup
    //   89: invokespecial 830	imoblife/luckad/ad/AdInfo:<init>	()V
    //   92: astore 4
    //   94: aload 4
    //   96: aload 5
    //   98: ldc_w 936
    //   101: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   104: invokevirtual 833	imoblife/luckad/ad/AdInfo:setPkgName	(Ljava/lang/String;)V
    //   107: aload 4
    //   109: aload 5
    //   111: ldc_w 939
    //   114: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   117: invokevirtual 861	imoblife/luckad/ad/AdInfo:setTrackUrl	(Ljava/lang/String;)V
    //   120: aload_0
    //   121: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   124: new 378	java/lang/StringBuilder
    //   127: dup
    //   128: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   131: ldc_w 941
    //   134: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   137: aload 4
    //   139: invokevirtual 944	imoblife/luckad/ad/AdInfo:getTrackUrl	()Ljava/lang/String;
    //   142: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   148: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   151: new 923	org/json/JSONObject
    //   154: dup
    //   155: aload 5
    //   157: ldc_w 946
    //   160: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   163: invokespecial 924	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   166: ldc_w 948
    //   169: invokevirtual 930	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   172: iconst_0
    //   173: invokevirtual 934	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   176: checkcast 202	java/lang/String
    //   179: astore 6
    //   181: aload_0
    //   182: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   185: new 378	java/lang/StringBuilder
    //   188: dup
    //   189: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   192: ldc_w 950
    //   195: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   198: aload 6
    //   200: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   203: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   206: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   209: aload 6
    //   211: ifnull +20 -> 231
    //   214: aload 6
    //   216: ldc 117
    //   218: invokevirtual 454	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   221: ifne +10 -> 231
    //   224: aload 4
    //   226: aload 6
    //   228: invokevirtual 866	imoblife/luckad/ad/AdInfo:setRecordUrl	(Ljava/lang/String;)V
    //   231: new 923	org/json/JSONObject
    //   234: dup
    //   235: aload 5
    //   237: ldc_w 952
    //   240: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   243: invokespecial 924	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   246: astore 6
    //   248: aload 4
    //   250: aload 6
    //   252: ldc_w 954
    //   255: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   258: invokevirtual 881	imoblife/luckad/ad/AdInfo:setTitle	(Ljava/lang/String;)V
    //   261: aload 4
    //   263: aload 6
    //   265: ldc_w 956
    //   268: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   271: invokevirtual 871	imoblife/luckad/ad/AdInfo:setContent	(Ljava/lang/String;)V
    //   274: aload 4
    //   276: aload 6
    //   278: ldc_w 958
    //   281: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   284: invokevirtual 886	imoblife/luckad/ad/AdInfo:setIconUrl	(Ljava/lang/String;)V
    //   287: new 923	org/json/JSONObject
    //   290: dup
    //   291: aload 6
    //   293: ldc_w 960
    //   296: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   299: invokespecial 924	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   302: ldc_w 962
    //   305: invokevirtual 930	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   308: astore 6
    //   310: aload 6
    //   312: iconst_0
    //   313: invokevirtual 934	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   316: ifnull +49 -> 365
    //   319: aload 6
    //   321: iconst_0
    //   322: invokevirtual 934	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   325: checkcast 202	java/lang/String
    //   328: astore 6
    //   330: aload_0
    //   331: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   334: new 378	java/lang/StringBuilder
    //   337: dup
    //   338: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   341: ldc_w 964
    //   344: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   347: aload 6
    //   349: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   352: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   355: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   358: aload 4
    //   360: aload 6
    //   362: invokevirtual 876	imoblife/luckad/ad/AdInfo:setImageUrl	(Ljava/lang/String;)V
    //   365: aload 4
    //   367: iconst_0
    //   368: invokevirtual 910	imoblife/luckad/ad/AdInfo:setConverted	(Z)V
    //   371: new 923	org/json/JSONObject
    //   374: dup
    //   375: aload 5
    //   377: ldc_w 966
    //   380: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   383: invokespecial 924	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   386: astore 5
    //   388: aload 5
    //   390: ldc_w 968
    //   393: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   396: astore 6
    //   398: aload 4
    //   400: aload 6
    //   402: invokevirtual 971	imoblife/luckad/ad/AdInfo:setAdFinalLink	(Ljava/lang/String;)V
    //   405: aload 4
    //   407: new 973	java/net/URI
    //   410: dup
    //   411: aload 6
    //   413: invokespecial 974	java/net/URI:<init>	(Ljava/lang/String;)V
    //   416: invokevirtual 978	imoblife/luckad/ad/AdInfo:setConversionUrl	(Ljava/net/URI;)V
    //   419: aload 4
    //   421: aload 5
    //   423: ldc_w 980
    //   426: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   429: invokevirtual 866	imoblife/luckad/ad/AdInfo:setRecordUrl	(Ljava/lang/String;)V
    //   432: aload 4
    //   434: iconst_1
    //   435: invokevirtual 910	imoblife/luckad/ad/AdInfo:setConverted	(Z)V
    //   438: aload 4
    //   440: ldc_w 614
    //   443: invokevirtual 680	imoblife/luckad/ad/AdInfo:setLoadStatus	(Ljava/lang/String;)V
    //   446: aload_0
    //   447: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   450: new 378	java/lang/StringBuilder
    //   453: dup
    //   454: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   457: ldc_w 982
    //   460: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   463: aload 6
    //   465: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   468: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   471: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   474: aload_0
    //   475: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   478: new 378	java/lang/StringBuilder
    //   481: dup
    //   482: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   485: ldc_w 984
    //   488: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   491: aload 5
    //   493: ldc_w 980
    //   496: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   499: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   502: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   505: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   508: aload_3
    //   509: aload 4
    //   511: invokevirtual 911	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   514: pop
    //   515: goto +34 -> 549
    //   518: astore_1
    //   519: iconst_0
    //   520: putstatic 125	imoblife/luckad/ad/LuckAdNew:isRunningDownloadAds	Z
    //   523: aload_1
    //   524: invokevirtual 527	java/lang/Exception:printStackTrace	()V
    //   527: aload_3
    //   528: areturn
    //   529: astore 6
    //   531: aload 6
    //   533: invokevirtual 527	java/lang/Exception:printStackTrace	()V
    //   536: goto -171 -> 365
    //   539: astore 5
    //   541: aload 5
    //   543: invokevirtual 527	java/lang/Exception:printStackTrace	()V
    //   546: goto -38 -> 508
    //   549: iload_2
    //   550: iconst_1
    //   551: iadd
    //   552: istore_2
    //   553: goto -507 -> 46
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	556	0	this	LuckAdNew
    //   0	556	1	paramString	String
    //   45	508	2	i	int
    //   7	521	3	localArrayList	ArrayList
    //   92	418	4	localAdInfo	AdInfo
    //   62	430	5	localJSONObject	JSONObject
    //   539	3	5	localException1	Exception
    //   179	285	6	localObject	Object
    //   529	3	6	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   26	44	518	java/lang/Exception
    //   46	82	518	java/lang/Exception
    //   85	209	518	java/lang/Exception
    //   214	231	518	java/lang/Exception
    //   231	287	518	java/lang/Exception
    //   365	371	518	java/lang/Exception
    //   508	515	518	java/lang/Exception
    //   531	536	518	java/lang/Exception
    //   541	546	518	java/lang/Exception
    //   287	365	529	java/lang/Exception
    //   371	508	539	java/lang/Exception
  }
  
  public String getGlispaId()
  {
    String str = "b8438224-dfd8-46c4-952f-cf6a596474a9";
    Bundle localBundle = getMetaData(this.context);
    if (localBundle != null)
    {
      str = localBundle.getString("glispa_id");
      logPrint(TAG, "getGlispaId---> " + str);
    }
    return str;
  }
  
  public ArrayList<AdInfo> getGlispaPubNativeAdsArray(String paramString1, String paramString2)
  {
    ArrayList localArrayList2 = new ArrayList();
    Object localObject = getGlispaAdsArray(paramString1);
    ArrayList localArrayList1 = getPubNativeAdsArray(paramString2);
    paramString1 = (String)localObject;
    if (localObject == null) {
      paramString1 = new ArrayList();
    }
    paramString2 = localArrayList1;
    if (localArrayList1 == null) {
      paramString2 = new ArrayList();
    }
    int i;
    int j;
    int k;
    if ((paramString2.size() > 0) && (paramString1.size() > 0))
    {
      if (this.parseOrder.equals("P"))
      {
        logPrint(TAG, "Pubnative first!!!");
        localArrayList2.addAll(paramString2);
        localArrayList1 = new ArrayList();
        i = 0;
        while (i < paramString2.size())
        {
          localArrayList1.add(((AdInfo)paramString2.get(i)).getPkgName());
          i += 1;
        }
        i = 0;
        if (i < paramString1.size())
        {
          paramString2 = (AdInfo)paramString1.get(i);
          if (localArrayList1.contains(paramString2.getPkgName())) {}
          for (;;)
          {
            i += 1;
            break;
            localArrayList2.add(paramString2);
          }
        }
      }
      else if (this.parseOrder.equals("G"))
      {
        logPrint(TAG, "Glispa first!!!");
        localArrayList2.addAll(paramString1);
        localArrayList1 = new ArrayList();
        i = 0;
        while (i < paramString1.size())
        {
          localArrayList1.add(((AdInfo)paramString1.get(i)).getPkgName());
          i += 1;
        }
        i = 0;
        if (i < paramString2.size())
        {
          paramString1 = (AdInfo)paramString2.get(i);
          if (localArrayList1.contains(paramString1.getPkgName())) {}
          for (;;)
          {
            i += 1;
            break;
            localArrayList2.add(paramString1);
          }
        }
      }
      else
      {
        logPrint(TAG, "Alternative mode!!!");
        j = paramString1.size();
        k = paramString2.size();
        localArrayList1 = new ArrayList();
        i = 0;
      }
    }
    else {
      while (i < adsDownloadNum)
      {
        if (i < k)
        {
          localObject = (AdInfo)paramString2.get(i);
          if (!localArrayList1.contains(((AdInfo)localObject).getPkgName()))
          {
            localArrayList2.add(localObject);
            localArrayList1.add(((AdInfo)localObject).getPkgName());
          }
        }
        if (i < j)
        {
          localObject = (AdInfo)paramString1.get(i);
          if (!localArrayList1.contains(((AdInfo)localObject).getPkgName()))
          {
            localArrayList2.add(localObject);
            localArrayList1.add(((AdInfo)localObject).getPkgName());
          }
        }
        i += 1;
        continue;
        if ((paramString2.size() != 0) || (paramString1.size() <= 0)) {
          break label477;
        }
        localArrayList2.addAll(paramString1);
      }
    }
    label477:
    while ((paramString2.size() <= 0) || (paramString1.size() != 0)) {
      return localArrayList2;
    }
    localArrayList2.addAll(paramString2);
    return localArrayList2;
  }
  
  public IAdLoadListener getListener()
  {
    return this.listener;
  }
  
  public ArrayList<AdInfo> getMobCoreAdsArray(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if ((paramString == null) || (paramString.trim().equals(""))) {}
    for (;;)
    {
      return localArrayList;
      try
      {
        paramString = new JSONObject(paramString.trim()).getJSONArray("ads");
        if ((paramString == null) || (paramString.length() == 0))
        {
          logPrint(TAG, "MobCore ads is ZERO!");
          return localArrayList;
        }
      }
      catch (Exception paramString)
      {
        isRunningDownloadAds = false;
        paramString.printStackTrace();
        return localArrayList;
      }
      int i = 0;
      while (i < paramString.length())
      {
        Object localObject1 = (JSONObject)paramString.get(i);
        Object localObject2 = ((JSONObject)localObject1).getString("packageName");
        Log.i(TAG, "MobCore ads appid--" + i + "->" + (String)localObject2);
        if (!PackageUtil.isPackageInstalled(this.context, (String)localObject2))
        {
          AdInfo localAdInfo = new AdInfo();
          double d;
          try
          {
            d = Double.parseDouble(((JSONObject)localObject1).getString("bid"));
            if ((this.isMinAdPrice) && (d < this.minAdPrice)) {
              logPrint(TAG, "MobCore ads price-is-low->" + d + "<" + this.minAdPrice);
            }
          }
          catch (Exception localException2)
          {
            localException2.printStackTrace();
          }
          for (;;)
          {
            localAdInfo.setPkgName((String)localObject2);
            localAdInfo.setTrackUrl(((JSONObject)localObject1).getString("clickURL"));
            logPrint(TAG, "MobCore ads getTrackUrl--->" + localAdInfo.getTrackUrl());
            localAdInfo.setRecordUrl(((JSONObject)localObject1).getString("impressionURL"));
            logPrint(TAG, "MobCore ads impressionUrl--->" + localAdInfo.getRecordUrl());
            localAdInfo.setAdsOrigin("MobileCore");
            logPrint(TAG, "MobCore ads origin-->" + localAdInfo.getAdsOrigin());
            localAdInfo.setTitle(((JSONObject)localObject1).getString("title"));
            localAdInfo.setContent(((JSONObject)localObject1).getString("description"));
            localObject2 = new JSONObject(((JSONObject)localObject1).getString("creatives"));
            localAdInfo.setIconUrl(((JSONObject)localObject2).getString("img"));
            try
            {
              localAdInfo.setImageUrl(((JSONObject)localObject2).getString("banner480x320"));
              localAdInfo.setConverted(false);
              Log.i(TAG, "MobileCore ads title-->" + localAdInfo.getTilte());
              logPrint(TAG, "MobileCore ads description-->" + localAdInfo.getContent());
              logPrint(TAG, "MobileCore ads icon_url-->" + localAdInfo.getIconUrl());
              logPrint(TAG, "MobileCore ads banner_url-->" + localAdInfo.getImageUrl());
              localObject1 = ((JSONObject)localObject1).getString("pricingModel");
              logPrint(TAG, "MobileCore ads revenue_model-->" + localAdInfo.getAdsType());
              if (!((String)localObject1).trim().toLowerCase().equals("cpi"))
              {
                localAdInfo.setLoadStatus("succeed");
                localAdInfo.setConverted(true);
                localAdInfo.setConversionUrl(new URI(localAdInfo.getTrackUrl()));
                localAdInfo.setAdFinalLink(localAdInfo.getTrackUrl());
                if (!this.isPubPreload) {
                  break label776;
                }
                localAdInfo.setConverted(false);
                localArrayList.add(localAdInfo);
                break label820;
                localAdInfo.setAdsPrice(String.valueOf(d));
                logPrint(TAG, "MobCore ads price--->" + localAdInfo.getAdsPrice());
              }
            }
            catch (Exception localException1)
            {
              for (;;)
              {
                localException1.printStackTrace();
                continue;
                localAdInfo.setAdsType("cpa");
                continue;
                label776:
                localAdInfo.setLoadStatus("succeed");
                localAdInfo.setAdFinalLink(localAdInfo.getTrackUrl());
                localAdInfo.setConversionUrl(new URI(localAdInfo.getTrackUrl()));
                localAdInfo.setConverted(true);
              }
            }
          }
        }
        label820:
        i += 1;
      }
    }
  }
  
  /* Error */
  public AdInfo getNativeAdNoFB()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 6
    //   6: aconst_null
    //   7: astore 4
    //   9: aload 5
    //   11: astore_3
    //   12: aload_0
    //   13: invokevirtual 600	imoblife/luckad/ad/LuckAdNew:getAdList_Native	()Ljava/util/List;
    //   16: ifnull +452 -> 468
    //   19: aload 5
    //   21: astore_3
    //   22: aload_0
    //   23: ldc_w 602
    //   26: new 378	java/lang/StringBuilder
    //   29: dup
    //   30: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   33: ldc_w 604
    //   36: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: aload_0
    //   40: invokevirtual 600	imoblife/luckad/ad/LuckAdNew:getAdList_Native	()Ljava/util/List;
    //   43: invokeinterface 382 1 0
    //   48: invokevirtual 416	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   51: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   54: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   57: aload 5
    //   59: astore_3
    //   60: aload_0
    //   61: ldc_w 606
    //   64: new 378	java/lang/StringBuilder
    //   67: dup
    //   68: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   71: ldc_w 604
    //   74: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   77: getstatic 121	imoblife/luckad/ad/LuckAdNew:indexOfAdList	I
    //   80: invokevirtual 416	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   83: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   86: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   89: aload 5
    //   91: astore_3
    //   92: aload_0
    //   93: getstatic 121	imoblife/luckad/ad/LuckAdNew:indexOfAdList	I
    //   96: invokevirtual 609	imoblife/luckad/ad/LuckAdNew:getAdUninstalledIndexGlispaByStepNew	(I)I
    //   99: putstatic 121	imoblife/luckad/ad/LuckAdNew:indexOfAdList	I
    //   102: aload_0
    //   103: invokevirtual 600	imoblife/luckad/ad/LuckAdNew:getAdList_Native	()Ljava/util/List;
    //   106: getstatic 121	imoblife/luckad/ad/LuckAdNew:indexOfAdList	I
    //   109: invokeinterface 385 2 0
    //   114: checkcast 445	imoblife/luckad/ad/AdInfo
    //   117: astore_3
    //   118: aload_3
    //   119: astore 4
    //   121: aload 4
    //   123: ifnull +172 -> 295
    //   126: aload 4
    //   128: astore_3
    //   129: aload 4
    //   131: invokevirtual 612	imoblife/luckad/ad/AdInfo:getLoadStatus	()Ljava/lang/String;
    //   134: ldc_w 614
    //   137: invokevirtual 454	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   140: ifeq +155 -> 295
    //   143: aload 4
    //   145: astore_3
    //   146: getstatic 121	imoblife/luckad/ad/LuckAdNew:indexOfAdList	I
    //   149: iconst_1
    //   150: iadd
    //   151: putstatic 121	imoblife/luckad/ad/LuckAdNew:indexOfAdList	I
    //   154: aload 4
    //   156: astore_3
    //   157: getstatic 121	imoblife/luckad/ad/LuckAdNew:indexOfAdList	I
    //   160: aload_0
    //   161: invokevirtual 600	imoblife/luckad/ad/LuckAdNew:getAdList_Native	()Ljava/util/List;
    //   164: invokeinterface 382 1 0
    //   169: if_icmplt +10 -> 179
    //   172: aload 4
    //   174: astore_3
    //   175: iconst_0
    //   176: putstatic 121	imoblife/luckad/ad/LuckAdNew:indexOfAdList	I
    //   179: aload 4
    //   181: astore_3
    //   182: aload_0
    //   183: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   186: ldc_w 620
    //   189: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   192: aload 4
    //   194: astore_3
    //   195: aload_0
    //   196: getfield 182	imoblife/luckad/ad/LuckAdNew:isStillPostImpession	Z
    //   199: istore_2
    //   200: iload_2
    //   201: ifeq +323 -> 524
    //   204: new 1080	imoblife/luckad/ad/LuckAdNew$12
    //   207: dup
    //   208: aload_0
    //   209: aload 4
    //   211: invokevirtual 625	imoblife/luckad/ad/AdInfo:getRecordUrl	()Ljava/lang/String;
    //   214: invokespecial 1081	imoblife/luckad/ad/LuckAdNew$12:<init>	(Limoblife/luckad/ad/LuckAdNew;Ljava/lang/String;)V
    //   217: invokevirtual 1082	imoblife/luckad/ad/LuckAdNew$12:start	()V
    //   220: aload 4
    //   222: astore_3
    //   223: aload_0
    //   224: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   227: ldc_w 633
    //   230: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   233: goto +291 -> 524
    //   236: astore_3
    //   237: aload 5
    //   239: astore_3
    //   240: aload_0
    //   241: ldc_w 635
    //   244: ldc_w 637
    //   247: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   250: goto -129 -> 121
    //   253: astore 4
    //   255: aload 4
    //   257: invokevirtual 527	java/lang/Exception:printStackTrace	()V
    //   260: aload_3
    //   261: areturn
    //   262: astore 5
    //   264: aload 4
    //   266: astore_3
    //   267: aload_0
    //   268: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   271: ldc_w 641
    //   274: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   277: aload 4
    //   279: astore_3
    //   280: iconst_0
    //   281: putstatic 125	imoblife/luckad/ad/LuckAdNew:isRunningDownloadAds	Z
    //   284: aload 4
    //   286: astore_3
    //   287: aload 5
    //   289: invokevirtual 527	java/lang/Exception:printStackTrace	()V
    //   292: goto -72 -> 220
    //   295: aload 4
    //   297: ifnull +115 -> 412
    //   300: aload 4
    //   302: astore_3
    //   303: aload 4
    //   305: invokevirtual 612	imoblife/luckad/ad/AdInfo:getLoadStatus	()Ljava/lang/String;
    //   308: ldc_w 643
    //   311: invokevirtual 454	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   314: istore_2
    //   315: iload_2
    //   316: ifeq +96 -> 412
    //   319: aload 4
    //   321: astore 5
    //   323: aload_0
    //   324: getstatic 129	imoblife/luckad/ad/LuckAdNew:indexOfAdPrepared	I
    //   327: invokevirtual 646	imoblife/luckad/ad/LuckAdNew:getAdPreparedUninstalledIndex	(I)I
    //   330: istore_1
    //   331: aload 4
    //   333: astore 5
    //   335: getstatic 248	imoblife/luckad/ad/LuckAdNew:adPreparedList	Ljava/util/List;
    //   338: iload_1
    //   339: invokeinterface 385 2 0
    //   344: checkcast 445	imoblife/luckad/ad/AdInfo
    //   347: astore_3
    //   348: aload_3
    //   349: astore 5
    //   351: iload_1
    //   352: iconst_1
    //   353: iadd
    //   354: putstatic 129	imoblife/luckad/ad/LuckAdNew:indexOfAdPrepared	I
    //   357: aload_3
    //   358: astore 5
    //   360: aload 5
    //   362: astore_3
    //   363: getstatic 121	imoblife/luckad/ad/LuckAdNew:indexOfAdList	I
    //   366: iconst_1
    //   367: iadd
    //   368: putstatic 121	imoblife/luckad/ad/LuckAdNew:indexOfAdList	I
    //   371: aload 5
    //   373: astore_3
    //   374: getstatic 121	imoblife/luckad/ad/LuckAdNew:indexOfAdList	I
    //   377: aload_0
    //   378: invokevirtual 600	imoblife/luckad/ad/LuckAdNew:getAdList_Native	()Ljava/util/List;
    //   381: invokeinterface 382 1 0
    //   386: if_icmplt +141 -> 527
    //   389: aload 5
    //   391: astore_3
    //   392: iconst_0
    //   393: putstatic 121	imoblife/luckad/ad/LuckAdNew:indexOfAdList	I
    //   396: goto +131 -> 527
    //   399: astore 4
    //   401: aload 5
    //   403: astore_3
    //   404: aload 4
    //   406: invokevirtual 527	java/lang/Exception:printStackTrace	()V
    //   409: goto -49 -> 360
    //   412: aload 4
    //   414: astore 5
    //   416: aload_0
    //   417: getstatic 129	imoblife/luckad/ad/LuckAdNew:indexOfAdPrepared	I
    //   420: invokevirtual 646	imoblife/luckad/ad/LuckAdNew:getAdPreparedUninstalledIndex	(I)I
    //   423: istore_1
    //   424: aload 4
    //   426: astore 5
    //   428: getstatic 248	imoblife/luckad/ad/LuckAdNew:adPreparedList	Ljava/util/List;
    //   431: iload_1
    //   432: invokeinterface 385 2 0
    //   437: checkcast 445	imoblife/luckad/ad/AdInfo
    //   440: astore_3
    //   441: aload_3
    //   442: astore 5
    //   444: iload_1
    //   445: iconst_1
    //   446: iadd
    //   447: putstatic 129	imoblife/luckad/ad/LuckAdNew:indexOfAdPrepared	I
    //   450: aload_3
    //   451: areturn
    //   452: astore 4
    //   454: aload 5
    //   456: astore_3
    //   457: aload 4
    //   459: invokevirtual 527	java/lang/Exception:printStackTrace	()V
    //   462: aload 5
    //   464: astore_3
    //   465: goto -15 -> 450
    //   468: aload 6
    //   470: astore 4
    //   472: aload_0
    //   473: getstatic 129	imoblife/luckad/ad/LuckAdNew:indexOfAdPrepared	I
    //   476: invokevirtual 646	imoblife/luckad/ad/LuckAdNew:getAdPreparedUninstalledIndex	(I)I
    //   479: istore_1
    //   480: aload 6
    //   482: astore 4
    //   484: getstatic 248	imoblife/luckad/ad/LuckAdNew:adPreparedList	Ljava/util/List;
    //   487: iload_1
    //   488: invokeinterface 385 2 0
    //   493: checkcast 445	imoblife/luckad/ad/AdInfo
    //   496: astore_3
    //   497: aload_3
    //   498: astore 4
    //   500: iload_1
    //   501: iconst_1
    //   502: iadd
    //   503: putstatic 129	imoblife/luckad/ad/LuckAdNew:indexOfAdPrepared	I
    //   506: aload_3
    //   507: areturn
    //   508: astore 5
    //   510: aload 4
    //   512: astore_3
    //   513: aload 5
    //   515: invokevirtual 527	java/lang/Exception:printStackTrace	()V
    //   518: aload 4
    //   520: astore_3
    //   521: goto -15 -> 506
    //   524: aload 4
    //   526: areturn
    //   527: aload 5
    //   529: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	530	0	this	LuckAdNew
    //   330	173	1	i	int
    //   199	117	2	bool	boolean
    //   11	212	3	localObject1	Object
    //   236	1	3	localException1	Exception
    //   239	282	3	localObject2	Object
    //   7	214	4	localObject3	Object
    //   253	79	4	localException2	Exception
    //   399	26	4	localException3	Exception
    //   452	6	4	localException4	Exception
    //   470	55	4	localObject4	Object
    //   1	237	5	localObject5	Object
    //   262	26	5	localException5	Exception
    //   321	142	5	localObject6	Object
    //   508	20	5	localException6	Exception
    //   4	477	6	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   102	118	236	java/lang/Exception
    //   12	19	253	java/lang/Exception
    //   22	57	253	java/lang/Exception
    //   60	89	253	java/lang/Exception
    //   92	102	253	java/lang/Exception
    //   129	143	253	java/lang/Exception
    //   146	154	253	java/lang/Exception
    //   157	172	253	java/lang/Exception
    //   175	179	253	java/lang/Exception
    //   182	192	253	java/lang/Exception
    //   195	200	253	java/lang/Exception
    //   223	233	253	java/lang/Exception
    //   240	250	253	java/lang/Exception
    //   267	277	253	java/lang/Exception
    //   280	284	253	java/lang/Exception
    //   287	292	253	java/lang/Exception
    //   303	315	253	java/lang/Exception
    //   363	371	253	java/lang/Exception
    //   374	389	253	java/lang/Exception
    //   392	396	253	java/lang/Exception
    //   404	409	253	java/lang/Exception
    //   457	462	253	java/lang/Exception
    //   513	518	253	java/lang/Exception
    //   204	220	262	java/lang/Exception
    //   323	331	399	java/lang/Exception
    //   335	348	399	java/lang/Exception
    //   351	357	399	java/lang/Exception
    //   416	424	452	java/lang/Exception
    //   428	441	452	java/lang/Exception
    //   444	450	452	java/lang/Exception
    //   472	480	508	java/lang/Exception
    //   484	497	508	java/lang/Exception
    //   500	506	508	java/lang/Exception
  }
  
  public ArrayList<AdInfo> getPubAppiaAdsArray(String paramString1, String paramString2)
  {
    ArrayList localArrayList2 = new ArrayList();
    Object localObject = getAppiaAdsArray(paramString1);
    ArrayList localArrayList1 = getPubNativeAdsArray(paramString2);
    paramString1 = (String)localObject;
    if (localObject == null) {
      paramString1 = new ArrayList();
    }
    paramString2 = localArrayList1;
    if (localArrayList1 == null) {
      paramString2 = new ArrayList();
    }
    int i;
    int j;
    int k;
    if ((paramString2.size() > 0) && (paramString1.size() > 0))
    {
      if (this.parseOrder.equals("P"))
      {
        logPrint(TAG, "Pubnative first!!!");
        localArrayList2.addAll(paramString2);
        localArrayList1 = new ArrayList();
        i = 0;
        while (i < paramString2.size())
        {
          localArrayList1.add(((AdInfo)paramString2.get(i)).getPkgName());
          i += 1;
        }
        i = 0;
        if (i < paramString1.size())
        {
          paramString2 = (AdInfo)paramString1.get(i);
          if (localArrayList1.contains(paramString2.getPkgName())) {}
          for (;;)
          {
            i += 1;
            break;
            localArrayList2.add(paramString2);
          }
        }
      }
      else if (this.parseOrder.equals("I"))
      {
        logPrint(TAG, "Appia first!!!");
        localArrayList2.addAll(paramString1);
        localArrayList1 = new ArrayList();
        i = 0;
        while (i < paramString1.size())
        {
          localArrayList1.add(((AdInfo)paramString1.get(i)).getPkgName());
          i += 1;
        }
        i = 0;
        if (i < paramString2.size())
        {
          paramString1 = (AdInfo)paramString2.get(i);
          if (localArrayList1.contains(paramString1.getPkgName())) {}
          for (;;)
          {
            i += 1;
            break;
            localArrayList2.add(paramString1);
          }
        }
      }
      else
      {
        logPrint(TAG, "Alternative mode!!!");
        j = paramString1.size();
        k = paramString2.size();
        localArrayList1 = new ArrayList();
        i = 0;
      }
    }
    else {
      while (i < adsDownloadNum)
      {
        if (i < k)
        {
          localObject = (AdInfo)paramString2.get(i);
          if (!localArrayList1.contains(((AdInfo)localObject).getPkgName()))
          {
            localArrayList2.add(localObject);
            localArrayList1.add(((AdInfo)localObject).getPkgName());
          }
        }
        if (i < j)
        {
          localObject = (AdInfo)paramString1.get(i);
          if (!localArrayList1.contains(((AdInfo)localObject).getPkgName()))
          {
            localArrayList2.add(localObject);
            localArrayList1.add(((AdInfo)localObject).getPkgName());
          }
        }
        i += 1;
        continue;
        if ((paramString2.size() != 0) || (paramString1.size() <= 0)) {
          break label477;
        }
        localArrayList2.addAll(paramString1);
      }
    }
    label477:
    while ((paramString2.size() <= 0) || (paramString1.size() != 0)) {
      return localArrayList2;
    }
    localArrayList2.addAll(paramString2);
    return localArrayList2;
  }
  
  public ArrayList<AdInfo> getPubMobCoreAdsArray(String paramString1, String paramString2)
  {
    ArrayList localArrayList2 = new ArrayList();
    Object localObject = getMobCoreAdsArray(paramString1);
    ArrayList localArrayList1 = getPubNativeAdsArray(paramString2);
    paramString1 = (String)localObject;
    if (localObject == null) {
      paramString1 = new ArrayList();
    }
    paramString2 = localArrayList1;
    if (localArrayList1 == null) {
      paramString2 = new ArrayList();
    }
    int i;
    int j;
    int k;
    if ((paramString2.size() > 0) && (paramString1.size() > 0))
    {
      if (this.parseOrder.equals("P"))
      {
        logPrint(TAG, "Pubnative first!!!");
        localArrayList2.addAll(paramString2);
        localArrayList1 = new ArrayList();
        i = 0;
        while (i < paramString2.size())
        {
          localArrayList1.add(((AdInfo)paramString2.get(i)).getPkgName());
          i += 1;
        }
        i = 0;
        if (i < paramString1.size())
        {
          paramString2 = (AdInfo)paramString1.get(i);
          if (localArrayList1.contains(paramString2.getPkgName())) {}
          for (;;)
          {
            i += 1;
            break;
            localArrayList2.add(paramString2);
          }
        }
      }
      else if (this.parseOrder.equals("Mob"))
      {
        logPrint(TAG, "MobCore first!!!");
        localArrayList2.addAll(paramString1);
        localArrayList1 = new ArrayList();
        i = 0;
        while (i < paramString1.size())
        {
          localArrayList1.add(((AdInfo)paramString1.get(i)).getPkgName());
          i += 1;
        }
        i = 0;
        if (i < paramString2.size())
        {
          paramString1 = (AdInfo)paramString2.get(i);
          if (localArrayList1.contains(paramString1.getPkgName())) {}
          for (;;)
          {
            i += 1;
            break;
            localArrayList2.add(paramString1);
          }
        }
      }
      else
      {
        logPrint(TAG, "Alternative mode!!!");
        j = paramString1.size();
        k = paramString2.size();
        localArrayList1 = new ArrayList();
        i = 0;
      }
    }
    else {
      while (i < adsDownloadNum)
      {
        if (i < k)
        {
          localObject = (AdInfo)paramString2.get(i);
          if (!localArrayList1.contains(((AdInfo)localObject).getPkgName()))
          {
            localArrayList2.add(localObject);
            localArrayList1.add(((AdInfo)localObject).getPkgName());
          }
        }
        if (i < j)
        {
          localObject = (AdInfo)paramString1.get(i);
          if (!localArrayList1.contains(((AdInfo)localObject).getPkgName()))
          {
            localArrayList2.add(localObject);
            localArrayList1.add(((AdInfo)localObject).getPkgName());
          }
        }
        i += 1;
        continue;
        if ((paramString2.size() != 0) || (paramString1.size() <= 0)) {
          break label477;
        }
        localArrayList2.addAll(paramString1);
      }
    }
    label477:
    while ((paramString2.size() <= 0) || (paramString1.size() != 0)) {
      return localArrayList2;
    }
    localArrayList2.addAll(paramString2);
    return localArrayList2;
  }
  
  public ArrayList<AdInfo> getPubNativeAdsArray(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if ((paramString == null) || (paramString.trim().equals(""))) {}
    int i;
    do
    {
      return localArrayList;
      try
      {
        paramString = new JSONObject(paramString.trim()).getJSONArray("ads");
        if ((paramString == null) || (paramString.length() == 0))
        {
          logPrint(TAG, "pubnative ads is ZERO!");
          return localArrayList;
        }
      }
      catch (Exception paramString)
      {
        isRunningDownloadAds = false;
        paramString.printStackTrace();
        return localArrayList;
      }
      i = 0;
    } while (i >= paramString.length());
    Object localObject1 = (JSONObject)paramString.get(i);
    Object localObject3 = new JSONObject(((JSONObject)localObject1).getString("app_details"));
    String str2 = ((JSONObject)localObject3).getString("store_id");
    Log.i(TAG, "pubnative ads appid--" + i + "->" + str2);
    Object localObject2 = ((JSONObject)localObject1).getJSONArray("beacons");
    logPrint(TAG, "pubnative ads impress -->" + ((JSONArray)localObject2).get(0).toString());
    localObject2 = new JSONObject(((JSONArray)localObject2).get(0).toString()).getString("url");
    logPrint(TAG, "pubnative ads impress url-->" + (String)localObject2);
    if ((localObject3 == null) || (PackageUtil.isPackageInstalled(this.context, str2))) {
      if (localObject2 != null)
      {
        boolean bool = ((String)localObject2).equals("");
        if (bool) {}
      }
    }
    double d;
    for (;;)
    {
      try
      {
        localObject1 = ((String)localObject2).trim() + "&installed=1";
        Log.i(TAG, "installed app-->" + (String)localObject1);
        new LuckAdNew.3(this, (String)localObject1).start();
        i += 1;
      }
      catch (Exception localException1)
      {
        logPrint(TAG, "POP-new-failed record!");
        isRunningDownloadAds = false;
        localException1.printStackTrace();
        continue;
      }
      localObject3 = new AdInfo();
      try
      {
        d = Double.parseDouble(localException1.getString("points")) / 1000.0D;
        if ((!this.isMinAdPrice) || (d >= this.minAdPrice)) {
          break label919;
        }
        logPrint(TAG, "pubnative ads price-is-low->" + d + "<" + this.minAdPrice);
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
      }
    }
    label495:
    ((AdInfo)localObject3).setPkgName(str2);
    if ((localObject2 != null) && (!((String)localObject2).equals(""))) {
      ((AdInfo)localObject3).setRecordUrl((String)localObject2);
    }
    ((AdInfo)localObject3).setTrackUrl(localException1.getString("click_url"));
    logPrint(TAG, "pubnative ads getTrackUrl--->" + ((AdInfo)localObject3).getTrackUrl());
    ((AdInfo)localObject3).setAdsOrigin("PubNative");
    logPrint(TAG, "pubnative ads origin-->" + ((AdInfo)localObject3).getAdsOrigin());
    ((AdInfo)localObject3).setTitle(localException1.getString("title"));
    ((AdInfo)localObject3).setContent(localException1.getString("description"));
    ((AdInfo)localObject3).setIconUrl(localException1.getString("icon_url"));
    ((AdInfo)localObject3).setImageUrl(localException1.getString("banner_url"));
    ((AdInfo)localObject3).setConverted(false);
    Log.i(TAG, "pubnative ads title-->" + ((AdInfo)localObject3).getTilte());
    logPrint(TAG, "pubnative ads description-->" + ((AdInfo)localObject3).getContent());
    logPrint(TAG, "pubnative ads icon_url-->" + ((AdInfo)localObject3).getIconUrl());
    logPrint(TAG, "pubnative ads banner_url-->" + ((AdInfo)localObject3).getImageUrl());
    String str1 = localException1.getString("revenue_model");
    logPrint(TAG, "pubnative ads revenue_model-->" + ((AdInfo)localObject3).getAdsType());
    ((AdInfo)localObject3).setAdsType(str1);
    if (!str1.trim().toLowerCase().equals("cpa"))
    {
      ((AdInfo)localObject3).setLoadStatus("succeed");
      ((AdInfo)localObject3).setConverted(true);
      ((AdInfo)localObject3).setConversionUrl(new URI(((AdInfo)localObject3).getTrackUrl()));
      ((AdInfo)localObject3).setAdFinalLink(((AdInfo)localObject3).getTrackUrl());
    }
    if (this.isPubPreload) {
      ((AdInfo)localObject3).setConverted(false);
    }
    for (;;)
    {
      localArrayList.add(localObject3);
      break;
      label919:
      ((AdInfo)localObject3).setAdsPrice(String.valueOf(d));
      logPrint(TAG, "pubnative ads price--->" + ((AdInfo)localObject3).getAdsPrice());
      break label495;
      ((AdInfo)localObject3).setLoadStatus("succeed");
      ((AdInfo)localObject3).setAdFinalLink(((AdInfo)localObject3).getTrackUrl());
      ((AdInfo)localObject3).setConversionUrl(new URI(((AdInfo)localObject3).getTrackUrl()));
      ((AdInfo)localObject3).setConverted(true);
    }
  }
  
  public String getPubnativeAppToken()
  {
    String str = "bcfb638264f8d21d671e7f144d62072384323356f4f5a86fbde5b2aa3fd8e772";
    Bundle localBundle = getMetaData(this.context);
    if (localBundle != null)
    {
      str = localBundle.getString("pubnative_app_token");
      logPrint(TAG, "getPubnativeAppToken---> " + str);
    }
    return str;
  }
  
  public String getPubnativeBundleId()
  {
    String str = "imoblife.toolbox.full";
    Bundle localBundle = getMetaData(this.context);
    if (localBundle != null)
    {
      str = localBundle.getString("pubnative_bundle_id");
      logPrint(TAG, "getPubnativeBundleId---> " + str);
    }
    return str;
  }
  
  public String getRequestByURL(String paramString)
  {
    str = "";
    Log.i(TAG, "Amazon URL-->" + paramString);
    for (;;)
    {
      try
      {
        localHttpURLConnection = (HttpURLConnection)new URL(paramString).openConnection();
        localHttpURLConnection.setConnectTimeout(10000);
        localHttpURLConnection.setRequestMethod("GET");
        localHttpURLConnection.setDoInput(true);
        i = 0;
      }
      catch (Exception paramString)
      {
        HttpURLConnection localHttpURLConnection;
        int i;
        int j;
        byte[] arrayOfByte;
        isRunningDownloadAds = false;
        paramString.printStackTrace();
        paramString = str;
        continue;
        paramString = new String(paramString.toByteArray());
        continue;
      }
      try
      {
        j = localHttpURLConnection.getResponseCode();
        i = j;
      }
      catch (Exception paramString)
      {
        isRunningDownloadAds = false;
        paramString.printStackTrace();
      }
    }
    paramString = str;
    if (i == 200)
    {
      paramString = new ByteArrayOutputStream();
      arrayOfByte = new byte['Ѐ'];
      try
      {
        for (;;)
        {
          i = localHttpURLConnection.getInputStream().read(arrayOfByte);
          if (i == -1) {
            break;
          }
          paramString.write(arrayOfByte, 0, i);
        }
        Log.i(TAG, "the page content from Aamzon-->" + paramString);
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        paramString = str;
      }
    }
    return paramString;
  }
  
  public long getSid()
  {
    long l = getMetaData(this.context).getInt("luckad_sid");
    logPrint(getClass().getSimpleName(), "getKey(): " + l);
    return l;
  }
  
  protected String getUserAgent(Context paramContext)
  {
    return TextUtils.join(" ", new String[] { String.valueOf(AdUitls.getOsv()), AdUitls.getDmf(), AdUitls.getDml(), AdUitls.getDpd(), String.valueOf(AdUitls.getDs(paramContext)), AdUitls.getMcc(paramContext), AdUitls.getMnc(paramContext), AdUitls.getIcc(paramContext), AdUitls.getCn(paramContext), String.valueOf(AdUitls.getNt(paramContext)), AdUitls.getTz(), AdUitls.getIp() });
  }
  
  public ArrayList<AdInfo> getYeahmobiAdsArray(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if ((paramString == null) || (paramString.trim().equals(""))) {
      return localArrayList;
    }
    try
    {
      paramString = new JSONObject(paramString.trim()).getJSONArray("ads");
      if ((paramString == null) || (paramString.length() == 0))
      {
        logPrint(TAG, "yeahmobi ads is ZERO!");
        return localArrayList;
      }
    }
    catch (Exception paramString)
    {
      isRunningDownloadAds = false;
      paramString.printStackTrace();
      return localArrayList;
    }
    int i = 0;
    AdInfo localAdInfo;
    while (i < paramString.length())
    {
      Object localObject = (JSONObject)paramString.get(i);
      logPrint(TAG, "yeahmobi ads appid--->" + localObject);
      localAdInfo = new AdInfo();
      localAdInfo.setTrackUrl(((JSONObject)localObject).getString("click_track_url"));
      localAdInfo.setRecordUrl(((JSONObject)localObject).getString("impression_record_url"));
      localAdInfo.setClick_record_url(((JSONObject)localObject).getString("click_record_url"));
      localAdInfo.setTitle(((JSONObject)localObject).getString("title"));
      localAdInfo.setContent(((JSONObject)localObject).getString("main_content"));
      localAdInfo.setIconUrl(((JSONObject)localObject).getString("icon_image_url").replace("=w300", "=w90"));
      localAdInfo.setImageUrl(((JSONObject)localObject).getString("main_image_url"));
      localAdInfo.setPkgName(((JSONObject)localObject).getString("app_package_name"));
      localObject = ((JSONObject)localObject).getString("bid");
      try
      {
        double d = Double.parseDouble((String)localObject);
        if ((this.isMinAdPrice) && (d < this.minAdPrice))
        {
          logPrint(TAG, "the YM price is low->" + d + "<" + this.minAdPrice);
          i += 1;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        localAdInfo.setAdsPrice((String)localObject);
        localAdInfo.setAdsOrigin("YeahMobi");
        if (!this.isYMPreload) {
          break label382;
        }
      }
    }
    localAdInfo.setConverted(false);
    for (;;)
    {
      localArrayList.add(localAdInfo);
      break;
      label382:
      localAdInfo.setLoadStatus("succeed");
      localAdInfo.setAdFinalLink(localAdInfo.getTrackUrl());
      localAdInfo.setConversionUrl(new URI(localAdInfo.getTrackUrl()));
      localAdInfo.setConverted(true);
    }
  }
  
  public ArrayList<AdInfo> getYeahmobiPubNativeAdsArray(String paramString1, String paramString2)
  {
    ArrayList localArrayList2 = new ArrayList();
    Object localObject = getYeahmobiAdsArray(paramString1);
    ArrayList localArrayList1 = getPubNativeAdsArray(paramString2);
    paramString1 = (String)localObject;
    if (localObject == null) {
      paramString1 = new ArrayList();
    }
    paramString2 = localArrayList1;
    if (localArrayList1 == null) {
      paramString2 = new ArrayList();
    }
    int i;
    int j;
    int k;
    if ((paramString2.size() > 0) && (paramString1.size() > 0))
    {
      if (this.parseOrder.equals("P"))
      {
        logPrint(TAG, "Pubnative first!!!");
        localArrayList2.addAll(paramString2);
        localArrayList1 = new ArrayList();
        i = 0;
        while (i < paramString2.size())
        {
          localArrayList1.add(((AdInfo)paramString2.get(i)).getPkgName());
          i += 1;
        }
        i = 0;
        if (i < paramString1.size())
        {
          paramString2 = (AdInfo)paramString1.get(i);
          if (localArrayList1.contains(paramString2.getPkgName())) {}
          for (;;)
          {
            i += 1;
            break;
            localArrayList2.add(paramString2);
          }
        }
      }
      else if (this.parseOrder.equals("Y"))
      {
        logPrint(TAG, "Glispa first!!!");
        localArrayList2.addAll(paramString1);
        localArrayList1 = new ArrayList();
        i = 0;
        while (i < paramString1.size())
        {
          localArrayList1.add(((AdInfo)paramString1.get(i)).getPkgName());
          i += 1;
        }
        i = 0;
        if (i < paramString2.size())
        {
          paramString1 = (AdInfo)paramString2.get(i);
          if (localArrayList1.contains(paramString1.getPkgName())) {}
          for (;;)
          {
            i += 1;
            break;
            localArrayList2.add(paramString1);
          }
        }
      }
      else
      {
        logPrint(TAG, "Alternative mode!!!");
        j = paramString1.size();
        k = paramString2.size();
        localArrayList1 = new ArrayList();
        i = 0;
      }
    }
    else {
      while (i < adsDownloadNum)
      {
        if (i < k)
        {
          localObject = (AdInfo)paramString2.get(i);
          if (!localArrayList1.contains(((AdInfo)localObject).getPkgName()))
          {
            localArrayList2.add(localObject);
            localArrayList1.add(((AdInfo)localObject).getPkgName());
          }
        }
        if (i < j)
        {
          localObject = (AdInfo)paramString1.get(i);
          if (!localArrayList1.contains(((AdInfo)localObject).getPkgName()))
          {
            localArrayList2.add(localObject);
            localArrayList1.add(((AdInfo)localObject).getPkgName());
          }
        }
        i += 1;
        continue;
        if ((paramString2.size() != 0) || (paramString1.size() <= 0)) {
          break label477;
        }
        localArrayList2.addAll(paramString1);
      }
    }
    label477:
    while ((paramString2.size() <= 0) || (paramString1.size() != 0)) {
      return localArrayList2;
    }
    localArrayList2.addAll(paramString2);
    return localArrayList2;
  }
  
  public void initAdPrparedList()
  {
    try
    {
      readAdsFromLocal();
      if ((adPreparedList == null) || (adPreparedList.size() == 0))
      {
        adPreparedList = new ArrayList();
        adPreparedList.add(new AdInfo("Hola Launcher", "Hola Launcher-Smaller yet bigger!", "https://s3.amazonaws.com/AllInOneToolbox/recommend/holalauncher.png", "", "https://play.google.com/store/apps/details?id=com.hola.launcher&referrer=aq_tranid%3d0EqvMe960Op6EZSpoiNEieJmUKjamQKGk%26pid%3dha_yeahmobi_gad_int%26c%3daio"));
        adPreparedList.add(new AdInfo("Mobo Launcher", "Simple and efficient business launcher.", "https://s3.amazonaws.com/AllInOneToolbox/recommend/MoboLauncher.png", "https://s3.amazonaws.com/AllInOneToolbox/recommend/ad_snap_url2.png", "https://play.google.com/store/apps/details?id=com.bd.android.mobolauncher&referrer=utm_source%3DAIO%2520toolbox%26anid%3Dadmob"));
        adPreparedList.add(new AdInfo("Asteroids 3D", "Everybody loves space journeys. To come off the Earth!", "https://s3.amazonaws.com/AllInOneToolbox/recommend/Asteroids3D.png", "", "https://play.google.com/store/apps/details?id=com.SkyDivers.asteroids3d&referrer=utm_source%3DAll_In_One_Toolbox%26utm_medium%3DSkydiverslab"));
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public boolean initAds()
  {
    try
    {
      if (CheckTime.isTimeUpAPI(this.context, 12, "sp_name_luck_ad_recmd_prelist", "sp_key_last_time_recmd_prelist"))
      {
        logPrint(TAG, "time is up and need to update!");
        return true;
      }
      if (PreferenceManager.getDefaultSharedPreferences(this.context).getInt("preparelist_size", 0) <= 0)
      {
        logPrint(TAG, "count is ZERO and need to update!");
        return true;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  public void initListner()
  {
    adsListner = new LuckAdNew.1(this);
  }
  
  public boolean isAdsObsoleted(SharedPreferences paramSharedPreferences, int paramInt, String paramString)
  {
    boolean bool;
    try
    {
      long l1 = System.currentTimeMillis();
      long l2 = paramSharedPreferences.getLong(paramString, l1);
      long l3 = l1 - l2;
      logPrint(TAG, "DATA-recmd:: " + l1 + " - " + l2 + " = " + l3);
      if (l3 > paramInt * 3600000L)
      {
        logPrint(TAG, "Luckads:: prepared ad is loaded over 24 hours!!!");
        bool = true;
      }
      else
      {
        logPrint(TAG, "Luckads:: prepared ad is sitll in 24 hours and can be visited!!!");
        bool = false;
      }
    }
    catch (Exception paramSharedPreferences)
    {
      paramSharedPreferences.printStackTrace();
      return false;
    }
    return bool;
  }
  
  public boolean isRightURL(String paramString)
  {
    return this.urlCheckCache;
  }
  
  public void loadAdsByType(String paramString)
  {
    int i = -1;
    for (;;)
    {
      try
      {
        switch (paramString.hashCode())
        {
        case 71: 
          logPrint(TAG, "enter case default->!!!!!!!!!!!!!!!!");
          resetAdListPubnative(4, "sp_name_luck_ad_recmd_0", "sp_key_last_time_recmd_0", initAds());
          return;
        }
      }
      catch (Exception paramString)
      {
        isRunningDownloadAds = false;
        paramString.printStackTrace();
        return;
      }
      if (paramString.equals("G"))
      {
        i = 0;
        break label1828;
        if (paramString.equals("FG"))
        {
          i = 1;
          break label1828;
          if (paramString.equals("P"))
          {
            i = 2;
            break label1828;
            if (paramString.equals("FP"))
            {
              i = 3;
              break label1828;
              if (paramString.equals("I"))
              {
                i = 4;
                break label1828;
                if (paramString.equals("PI"))
                {
                  i = 5;
                  break label1828;
                  if (paramString.equals("M"))
                  {
                    i = 6;
                    break label1828;
                    if (paramString.equals("FM"))
                    {
                      i = 7;
                      break label1828;
                      if (paramString.equals("PM"))
                      {
                        i = 8;
                        break label1828;
                        if (paramString.equals("FPM"))
                        {
                          i = 9;
                          break label1828;
                          if (paramString.equals("F"))
                          {
                            i = 10;
                            break label1828;
                            if (paramString.equals("GP"))
                            {
                              i = 11;
                              break label1828;
                              if (paramString.equals("FGP"))
                              {
                                i = 12;
                                break label1828;
                                if (paramString.equals("Y"))
                                {
                                  i = 13;
                                  break label1828;
                                  if (paramString.equals("FY"))
                                  {
                                    i = 14;
                                    break label1828;
                                    if (paramString.equals("PY"))
                                    {
                                      i = 15;
                                      break label1828;
                                      if (paramString.equals("FPY"))
                                      {
                                        i = 16;
                                        break label1828;
                                        logPrint(TAG, "enter case " + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt + "!!!!!!!!!!!!!!!!");
                                        boolean bool = initAds();
                                        resetAdList(updateTimeLmt, "sp_name_luck_ad_recmd_" + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt, "sp_key_last_time_recmd_" + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt, bool);
                                        return;
                                        logPrint(TAG, "enter case " + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt + "!!!!!!!!!!!!!!!!");
                                        bool = initAds();
                                        resetAdListPubnative(updateTimeLmt, "sp_name_luck_ad_recmd_" + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt, "sp_key_last_time_recmd_" + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt, bool);
                                        return;
                                        logPrint(TAG, "enter case " + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt + "!!!!!!!!!!!!!!!!");
                                        bool = initAds();
                                        resetAdListAppia(updateTimeLmt, "sp_name_luck_ad_recmd_" + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt, "sp_key_last_time_recmd_" + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt, bool);
                                        return;
                                        logPrint(TAG, "enter case " + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt + "!!!!!!!!!!!!!!!!");
                                        bool = initAds();
                                        resetAdListPubAppia(updateTimeLmt, "sp_name_luck_ad_recmd_" + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt, "sp_key_last_time_recmd_" + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt, bool);
                                        return;
                                        logPrint(TAG, "enter case " + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt + "!!!!!!!!!!!!!!!!");
                                        bool = initAds();
                                        resetAdListMobCore(updateTimeLmt, "sp_name_luck_ad_recmd_" + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt, "sp_key_last_time_recmd_" + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt, bool);
                                        return;
                                        logPrint(TAG, "enter case " + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt + "!!!!!!!!!!!!!!!!");
                                        bool = initAds();
                                        resetAdListPubMobCore(updateTimeLmt, "sp_name_luck_ad_recmd_" + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt, "sp_key_last_time_recmd_" + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt, bool);
                                        return;
                                        logPrint(TAG, "enter case " + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt + "!!!!!!!!!!!!!!!!");
                                        bool = initAds();
                                        resetAdListPubnative(updateTimeLmt, "sp_name_luck_ad_recmd_" + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt, "sp_key_last_time_recmd_" + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt, bool);
                                        return;
                                        logPrint(TAG, "enter case " + paramString + this.preloadAdsNum + updateTimeLmt + "!!!!!!!!!!!!!!!!");
                                        bool = initAds();
                                        resetAdListGliPub(updateTimeLmt, "sp_name_luck_ad_recmd_" + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt, "sp_key_last_time_recmd_" + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt, bool);
                                        return;
                                        logPrint(TAG, "enter case " + paramString + this.preloadAdsNum + updateTimeLmt + "!!!!!!!!!!!!!!!!");
                                        bool = initAds();
                                        resetAdListYeahmobi(updateTimeLmt, "sp_name_luck_ad_recmd_" + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt, "sp_key_last_time_recmd_" + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt, bool);
                                        return;
                                        bool = initAds();
                                        resetAdListYeaPub(updateTimeLmt, "sp_name_luck_ad_recmd_" + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt, "sp_key_last_time_recmd_" + paramString + adsDownloadNum + this.preloadAdsNum + updateTimeLmt, bool);
                                        return;
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
                  }
                }
              }
            }
          }
        }
      }
      label1828:
      switch (i)
      {
      }
    }
  }
  
  /* Error */
  public URI loadGPLink(String paramString)
  {
    // Byte code:
    //   0: new 1481	org/apache/http/client/methods/HttpGet
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 1482	org/apache/http/client/methods/HttpGet:<init>	(Ljava/lang/String;)V
    //   8: astore_2
    //   9: invokestatic 1488	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   12: invokevirtual 1491	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   15: astore_1
    //   16: aload_0
    //   17: getfield 184	imoblife/luckad/ad/LuckAdNew:isGooglePlaySchema	Z
    //   20: ifeq +159 -> 179
    //   23: aload_2
    //   24: ldc_w 1493
    //   27: aload_0
    //   28: aload_0
    //   29: getfield 198	imoblife/luckad/ad/LuckAdNew:context	Landroid/content/Context;
    //   32: invokevirtual 1495	imoblife/luckad/ad/LuckAdNew:getUserAgent	(Landroid/content/Context;)Ljava/lang/String;
    //   35: invokevirtual 1498	org/apache/http/client/methods/HttpGet:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   38: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   41: new 378	java/lang/StringBuilder
    //   44: dup
    //   45: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   48: ldc_w 1500
    //   51: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: aload_0
    //   55: aload_0
    //   56: getfield 198	imoblife/luckad/ad/LuckAdNew:context	Landroid/content/Context;
    //   59: invokevirtual 1495	imoblife/luckad/ad/LuckAdNew:getUserAgent	(Landroid/content/Context;)Ljava/lang/String;
    //   62: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   68: invokestatic 443	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   71: pop
    //   72: aload_2
    //   73: ldc_w 1502
    //   76: aload_1
    //   77: invokevirtual 1498	org/apache/http/client/methods/HttpGet:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   80: new 1504	org/apache/http/params/BasicHttpParams
    //   83: dup
    //   84: invokespecial 1505	org/apache/http/params/BasicHttpParams:<init>	()V
    //   87: astore_1
    //   88: aload_1
    //   89: ldc_w 1506
    //   92: invokestatic 1512	org/apache/http/params/HttpConnectionParams:setConnectionTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   95: aload_1
    //   96: ldc_w 1506
    //   99: invokestatic 1515	org/apache/http/params/HttpConnectionParams:setSoTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   102: new 1517	org/apache/http/impl/client/DefaultHttpClient
    //   105: dup
    //   106: aload_1
    //   107: invokespecial 1520	org/apache/http/impl/client/DefaultHttpClient:<init>	(Lorg/apache/http/params/HttpParams;)V
    //   110: astore_3
    //   111: aload_3
    //   112: invokeinterface 1526 1 0
    //   117: ldc_w 1528
    //   120: iconst_1
    //   121: invokestatic 1533	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   124: invokeinterface 1539 3 0
    //   129: pop
    //   130: aload_3
    //   131: invokeinterface 1526 1 0
    //   136: ldc_w 1541
    //   139: bipush 100
    //   141: invokestatic 1544	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   144: invokeinterface 1539 3 0
    //   149: pop
    //   150: new 1546	imoblife/luckad/ad/MarketRedirectHandler
    //   153: dup
    //   154: invokespecial 1547	imoblife/luckad/ad/MarketRedirectHandler:<init>	()V
    //   157: astore_1
    //   158: aload_3
    //   159: checkcast 1549	org/apache/http/impl/client/AbstractHttpClient
    //   162: aload_1
    //   163: invokevirtual 1553	org/apache/http/impl/client/AbstractHttpClient:setRedirectHandler	(Lorg/apache/http/client/RedirectHandler;)V
    //   166: aload_3
    //   167: aload_2
    //   168: invokeinterface 1556 2 0
    //   173: pop
    //   174: aload_1
    //   175: invokevirtual 1560	imoblife/luckad/ad/MarketRedirectHandler:getGplayUrl	()Ljava/net/URI;
    //   178: areturn
    //   179: aload_2
    //   180: ldc_w 1493
    //   183: ldc_w 1562
    //   186: invokestatic 1565	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   189: invokevirtual 1498	org/apache/http/client/methods/HttpGet:addHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   192: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   195: new 378	java/lang/StringBuilder
    //   198: dup
    //   199: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   202: ldc_w 1567
    //   205: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   208: ldc_w 1562
    //   211: invokestatic 1565	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   214: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   217: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   220: invokestatic 443	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   223: pop
    //   224: goto -152 -> 72
    //   227: astore_1
    //   228: aload_0
    //   229: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   232: new 378	java/lang/StringBuilder
    //   235: dup
    //   236: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   239: ldc_w 1569
    //   242: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   245: aload_1
    //   246: invokevirtual 1570	java/lang/Exception:toString	()Ljava/lang/String;
    //   249: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   252: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   255: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   258: iconst_0
    //   259: putstatic 125	imoblife/luckad/ad/LuckAdNew:isRunningDownloadAds	Z
    //   262: aload_1
    //   263: invokevirtual 527	java/lang/Exception:printStackTrace	()V
    //   266: aconst_null
    //   267: areturn
    //   268: astore_2
    //   269: aload_0
    //   270: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   273: new 378	java/lang/StringBuilder
    //   276: dup
    //   277: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   280: ldc_w 1572
    //   283: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   286: aload_2
    //   287: invokevirtual 1573	javax/net/ssl/SSLPeerUnverifiedException:toString	()Ljava/lang/String;
    //   290: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   293: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   296: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   299: aload_2
    //   300: invokevirtual 1574	javax/net/ssl/SSLPeerUnverifiedException:printStackTrace	()V
    //   303: goto -129 -> 174
    //   306: astore_2
    //   307: aload_0
    //   308: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   311: new 378	java/lang/StringBuilder
    //   314: dup
    //   315: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   318: ldc_w 1576
    //   321: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   324: aload_2
    //   325: invokevirtual 1577	org/apache/http/client/ClientProtocolException:toString	()Ljava/lang/String;
    //   328: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   331: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   334: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   337: aload_2
    //   338: invokevirtual 1578	org/apache/http/client/ClientProtocolException:printStackTrace	()V
    //   341: goto -167 -> 174
    //   344: astore_2
    //   345: aload_0
    //   346: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   349: new 378	java/lang/StringBuilder
    //   352: dup
    //   353: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   356: ldc_w 1580
    //   359: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   362: aload_2
    //   363: invokevirtual 1570	java/lang/Exception:toString	()Ljava/lang/String;
    //   366: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   369: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   372: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   375: aload_2
    //   376: invokevirtual 527	java/lang/Exception:printStackTrace	()V
    //   379: goto -205 -> 174
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	382	0	this	LuckAdNew
    //   0	382	1	paramString	String
    //   8	172	2	localHttpGet	org.apache.http.client.methods.HttpGet
    //   268	32	2	localSSLPeerUnverifiedException	javax.net.ssl.SSLPeerUnverifiedException
    //   306	32	2	localClientProtocolException	org.apache.http.client.ClientProtocolException
    //   344	32	2	localException	Exception
    //   110	57	3	localDefaultHttpClient	org.apache.http.impl.client.DefaultHttpClient
    // Exception table:
    //   from	to	target	type
    //   0	72	227	java/lang/Exception
    //   72	166	227	java/lang/Exception
    //   174	179	227	java/lang/Exception
    //   179	224	227	java/lang/Exception
    //   269	303	227	java/lang/Exception
    //   307	341	227	java/lang/Exception
    //   345	379	227	java/lang/Exception
    //   166	174	268	javax/net/ssl/SSLPeerUnverifiedException
    //   166	174	306	org/apache/http/client/ClientProtocolException
    //   166	174	344	java/lang/Exception
  }
  
  public String loadGPLinkNew(String paramString)
  {
    Object localObject = null;
    try
    {
      URI localURI = loadGPLink(paramString);
      paramString = localObject;
      if (localURI != null)
      {
        paramString = localObject;
        if (!localURI.toString().trim().equals("")) {
          paramString = localURI.toString();
        }
      }
      return paramString;
    }
    catch (Exception paramString)
    {
      logPrint(TAG, "final Exception output--" + paramString.toString());
      isRunningDownloadAds = false;
      paramString.printStackTrace();
    }
    return null;
  }
  
  public void logPrint(String paramString1, String paramString2)
  {
    Log.i(paramString1, paramString2);
  }
  
  public void openGplay(String paramString)
  {
    try
    {
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.VIEW");
      localIntent.setFlags(268435456);
      localIntent.setData(Uri.parse(paramString));
      localIntent.setClassName("com.android.vending", "com.android.vending.AssetBrowserActivity");
      this.context.startActivity(localIntent);
      return;
    }
    catch (Exception paramString)
    {
      isRunningDownloadAds = false;
      paramString.printStackTrace();
    }
  }
  
  public void openOtherAdsForGlispa()
  {
    try
    {
      int i = getAdPreparedUninstalledIndex(indexOfAdPrepared);
      AdInfo localAdInfo = (AdInfo)adPreparedList.get(i);
      indexOfAdPrepared = i + 1;
      openGplay(localAdInfo.getConversionUrl().toString());
      logPrint(TAG, "prepare List link ->" + localAdInfo.getConversionUrl().toString());
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void parseAdInfo(String paramString)
  {
    if (paramString != null) {}
    for (;;)
    {
      int i;
      try
      {
        if (paramString.trim().equals("")) {
          return;
        }
        paramString = new JSONObject(paramString.trim()).getJSONArray("ads");
        setAdList(new ArrayList());
        getAdList().clear();
        if (paramString.length() <= adsCount) {
          break label236;
        }
        adsCount = paramString.length();
      }
      catch (Exception paramString)
      {
        JSONObject localJSONObject;
        AdInfo localAdInfo;
        isRunningDownloadAds = false;
        paramString.printStackTrace();
      }
      if (i < paramString.length())
      {
        localJSONObject = (JSONObject)paramString.get(i);
        localAdInfo = new AdInfo();
        localAdInfo.setTrackUrl(localJSONObject.getString("url"));
        localAdInfo.setTitle(localJSONObject.getString("title"));
        localAdInfo.setContent(localJSONObject.getString("content"));
        localAdInfo.setIconUrl(localJSONObject.getString("icon"));
        localAdInfo.setPkgName(localJSONObject.getString("package"));
        localAdInfo.setType(localJSONObject.getString("type"));
        if ((getAdList() == null) || (getAdList().size() - 1 < i)) {
          getAdList().add(localAdInfo);
        } else {
          getAdList().set(i, localAdInfo);
        }
      }
      else
      {
        return;
        label236:
        i = 0;
        continue;
      }
      i += 1;
    }
  }
  
  public void parseAppiaAdInfo(String paramString1, String paramString2)
  {
    if (paramString2.equals("all"))
    {
      parseAppiaAdInfoAll(paramString1);
      if ((getAdList_Native() != null) && (getAdList_Native().size() > 0))
      {
        logPrint(TAG, "Appia List Size->" + getAdList_Native().size());
        if (this.preloadAdsNum <= 0) {
          break label143;
        }
        logPrint(TAG, "Appia native load " + this.preloadAdsNum + " feed!!!");
        new LoadLinkTask(this.context, getListener(), this.preloadAdsNum).execute(new Integer[0]);
        indexOfAdList = 0;
      }
    }
    label143:
    do
    {
      do
      {
        do
        {
          return;
        } while (this.listener == null);
        this.listener.onLoadSuccess();
        return;
        parseAppiaAdInfoHour(paramString1);
      } while ((getAdList_Native() == null) || (getAdList_Native().size() <= 0));
      if (this.preloadAdsNum > 0)
      {
        logPrint(TAG, "Pubnative native load 1 feed!!!");
        firstCheckLoadAds();
        indexOfAdList = 0;
        return;
      }
    } while (this.listener == null);
    this.listener.onLoadSuccess();
  }
  
  public void parseAppiaAdInfoAll(String paramString)
  {
    try
    {
      paramString = getAppiaAdsArray(paramString);
      if (paramString != null)
      {
        if (paramString.size() == 0) {
          return;
        }
        if ((getAdList_Native() == null) || (getAdList_Native().size() == 0))
        {
          logPrint(TAG, "parseAppiaAdInfo--update all-->new--" + indexOfAdList);
          setAdList_Native(new ArrayList());
          getAdList_Native().addAll(paramString);
          return;
        }
      }
    }
    catch (Exception paramString)
    {
      isRunningDownloadAds = false;
      paramString.printStackTrace();
    }
  }
  
  public void parseAppiaAdInfoHour(String paramString)
  {
    AdInfo localAdInfo1;
    int m;
    int j;
    label134:
    int k;
    AdInfo localAdInfo2;
    try
    {
      paramString = getAppiaAdsArray(paramString);
      if (paramString == null) {
        break label743;
      }
      if (paramString.size() == 0) {
        return;
      }
      if ((getAdList_Native() != null) && (getAdList_Native().size() != 0)) {
        break label396;
      }
      logPrint(TAG, "parseAppiaAdInfo---->new--" + indexOfAdList);
      if ((adPreparedList != null) && (adPreparedList.size() > 0) && (this.isLoadAdsFromPrepareList))
      {
        logPrint(TAG, "LuckAdNew::Load ads from prepare list");
        localArrayList = new ArrayList();
        i = 0;
        if (i < paramString.size())
        {
          localAdInfo1 = (AdInfo)paramString.get(i);
          m = 0;
          j = 0;
          k = m;
          if (j < adPreparedList.size())
          {
            localAdInfo2 = (AdInfo)adPreparedList.get(j);
            if ((!localAdInfo1.getPkgName().trim().equals(localAdInfo2.getPkgName().trim())) || (!isRightURL(localAdInfo2.getAdFinalLink()))) {
              break label751;
            }
            logPrint(TAG, "ad list-title-" + j + localAdInfo1.getTilte() + "-->exist!!!");
            logPrint(TAG, "ad list-url-" + j + localAdInfo2.getAdFinalLink());
            k = 1;
            localAdInfo1.setAdFinalLink(localAdInfo2.getAdFinalLink());
            localAdInfo1.setConversionUrl(localAdInfo2.getConversionUrl());
            localAdInfo1.setLoadStatus("succeed");
            localArrayList.add(localAdInfo1);
          }
          if (k != 0) {
            break label744;
          }
          localArrayList.add(localAdInfo1);
          break label744;
        }
        setAdList_Native(new ArrayList());
        getAdList_Native().addAll(localArrayList);
        return;
      }
    }
    catch (Exception paramString)
    {
      isRunningDownloadAds = false;
      paramString.printStackTrace();
      return;
    }
    logPrint(TAG, "LuckAdNew::Do not load ads from prepare list");
    setAdList_Native(new ArrayList());
    getAdList_Native().addAll(paramString);
    return;
    label396:
    logPrint(TAG, "parseAppiaAdInfo---->list already exist!-new ads size->" + paramString.size());
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    label437:
    if (i < paramString.size())
    {
      localAdInfo1 = (AdInfo)paramString.get(i);
      m = 0;
      j = 0;
    }
    for (;;)
    {
      k = m;
      if (j < getAdList_Native().size())
      {
        localAdInfo2 = (AdInfo)getAdList_Native().get(j);
        if (!localAdInfo1.getPkgName().trim().equals(localAdInfo2.getPkgName().trim())) {
          break label765;
        }
        logPrint(TAG, "ad list--" + j + localAdInfo1.getPkgName() + "-->exist!!!");
        k = 1;
        if ((!localAdInfo2.getLoadStatus().equals("succeed")) || (!isRightURL(localAdInfo2.getAdFinalLink()))) {
          break label676;
        }
        logPrint(TAG, "ad list--" + j + "-without preload->exist!!!");
        logPrint(TAG, "ad list-url-" + j + localAdInfo2.getAdFinalLink());
        localArrayList.add(localAdInfo2);
      }
      for (;;)
      {
        if (k == 0)
        {
          localArrayList.add(localAdInfo1);
          break label758;
          label676:
          logPrint(TAG, "ad list--" + j + "-need preload->exist!!!");
          localArrayList.add(localAdInfo1);
          continue;
          setAdList_Native(new ArrayList());
          getAdList_Native().addAll(localArrayList);
          label743:
          return;
          label744:
          i += 1;
          break;
          label751:
          j += 1;
          break label134;
        }
      }
      label758:
      i += 1;
      break label437;
      label765:
      j += 1;
    }
  }
  
  public void parseAppiaAdWallInfo(String paramString)
  {
    Log.i(TAG, "APPIA::" + paramString);
    if ((paramString != null) && (!paramString.trim().equals("")))
    {
      paramString = getAppiaAdsArray(paramString);
      if ((paramString != null) && (paramString.size() != 0))
      {
        this.ad_list_wall = new ArrayList();
        setAd_list_wall(paramString);
      }
    }
  }
  
  public void parseGlispaAdInfoByStepNew(String paramString)
  {
    parseGlispaAdInfoFromJson(paramString);
    logPrint(TAG, "parseGlispaAdInfoByStepNew native List Size-new->" + getAdList_Native().size());
    if (this.preloadAdsNum > 0)
    {
      logPrint(TAG, "Glispa native load 1 feed!!!");
      if ((getAdList_Native() != null) && (getAdList_Native().size() > 0)) {
        firstCheckLoadAds();
      }
    }
    while (this.listener == null) {
      return;
    }
    this.listener.onLoadSuccess();
  }
  
  /* Error */
  public void parseGlispaAdInfoFromJson(String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +1264 -> 1265
    //   4: aload_1
    //   5: invokevirtual 451	java/lang/String:trim	()Ljava/lang/String;
    //   8: ldc 117
    //   10: invokevirtual 454	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   13: ifeq +4 -> 17
    //   16: return
    //   17: new 923	org/json/JSONObject
    //   20: dup
    //   21: aload_1
    //   22: invokevirtual 451	java/lang/String:trim	()Ljava/lang/String;
    //   25: invokespecial 924	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   28: ldc_w 926
    //   31: invokevirtual 930	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   34: astore_1
    //   35: aload_0
    //   36: invokevirtual 600	imoblife/luckad/ad/LuckAdNew:getAdList_Native	()Ljava/util/List;
    //   39: ifnull +15 -> 54
    //   42: aload_0
    //   43: invokevirtual 600	imoblife/luckad/ad/LuckAdNew:getAdList_Native	()Ljava/util/List;
    //   46: invokeinterface 382 1 0
    //   51: ifne +542 -> 593
    //   54: aload_0
    //   55: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   58: ldc_w 1720
    //   61: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   64: aload_0
    //   65: new 338	java/util/ArrayList
    //   68: dup
    //   69: invokespecial 339	java/util/ArrayList:<init>	()V
    //   72: invokevirtual 1681	imoblife/luckad/ad/LuckAdNew:setAdList_Native	(Ljava/util/List;)V
    //   75: aload_0
    //   76: invokevirtual 600	imoblife/luckad/ad/LuckAdNew:getAdList_Native	()Ljava/util/List;
    //   79: invokeinterface 1635 1 0
    //   84: iconst_0
    //   85: istore_2
    //   86: iload_2
    //   87: aload_1
    //   88: invokevirtual 933	org/json/JSONArray:length	()I
    //   91: if_icmpge +1174 -> 1265
    //   94: aload_1
    //   95: iload_2
    //   96: invokevirtual 934	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   99: checkcast 923	org/json/JSONObject
    //   102: astore 7
    //   104: aload_0
    //   105: getfield 198	imoblife/luckad/ad/LuckAdNew:context	Landroid/content/Context;
    //   108: aload 7
    //   110: ldc_w 936
    //   113: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   116: invokestatic 535	base/util/PackageUtil:isPackageInstalled	(Landroid/content/Context;Ljava/lang/String;)Z
    //   119: ifeq +6 -> 125
    //   122: goto +1144 -> 1266
    //   125: new 445	imoblife/luckad/ad/AdInfo
    //   128: dup
    //   129: invokespecial 830	imoblife/luckad/ad/AdInfo:<init>	()V
    //   132: astore 6
    //   134: aload 6
    //   136: aload 7
    //   138: ldc_w 936
    //   141: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   144: invokevirtual 833	imoblife/luckad/ad/AdInfo:setPkgName	(Ljava/lang/String;)V
    //   147: aload 6
    //   149: aload 7
    //   151: ldc_w 939
    //   154: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   157: invokevirtual 861	imoblife/luckad/ad/AdInfo:setTrackUrl	(Ljava/lang/String;)V
    //   160: aload_0
    //   161: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   164: new 378	java/lang/StringBuilder
    //   167: dup
    //   168: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   171: ldc_w 941
    //   174: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: aload 6
    //   179: invokevirtual 944	imoblife/luckad/ad/AdInfo:getTrackUrl	()Ljava/lang/String;
    //   182: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   185: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   188: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   191: new 923	org/json/JSONObject
    //   194: dup
    //   195: aload 7
    //   197: ldc_w 946
    //   200: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   203: invokespecial 924	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   206: ldc_w 948
    //   209: invokevirtual 930	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   212: iconst_0
    //   213: invokevirtual 934	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   216: checkcast 202	java/lang/String
    //   219: astore 8
    //   221: aload_0
    //   222: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   225: new 378	java/lang/StringBuilder
    //   228: dup
    //   229: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   232: ldc_w 950
    //   235: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: aload 8
    //   240: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   243: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   246: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   249: aload 8
    //   251: ifnull +20 -> 271
    //   254: aload 8
    //   256: ldc 117
    //   258: invokevirtual 454	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   261: ifne +10 -> 271
    //   264: aload 6
    //   266: aload 8
    //   268: invokevirtual 866	imoblife/luckad/ad/AdInfo:setRecordUrl	(Ljava/lang/String;)V
    //   271: new 923	org/json/JSONObject
    //   274: dup
    //   275: aload 7
    //   277: ldc_w 952
    //   280: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   283: invokespecial 924	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   286: astore 8
    //   288: aload 6
    //   290: aload 8
    //   292: ldc_w 954
    //   295: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   298: invokevirtual 881	imoblife/luckad/ad/AdInfo:setTitle	(Ljava/lang/String;)V
    //   301: aload 6
    //   303: aload 8
    //   305: ldc_w 956
    //   308: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   311: invokevirtual 871	imoblife/luckad/ad/AdInfo:setContent	(Ljava/lang/String;)V
    //   314: aload 6
    //   316: aload 8
    //   318: ldc_w 958
    //   321: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   324: invokevirtual 886	imoblife/luckad/ad/AdInfo:setIconUrl	(Ljava/lang/String;)V
    //   327: new 923	org/json/JSONObject
    //   330: dup
    //   331: aload 8
    //   333: ldc_w 960
    //   336: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   339: invokespecial 924	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   342: ldc_w 962
    //   345: invokevirtual 930	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   348: astore 8
    //   350: aload 8
    //   352: iconst_0
    //   353: invokevirtual 934	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   356: ifnull +49 -> 405
    //   359: aload 8
    //   361: iconst_0
    //   362: invokevirtual 934	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   365: checkcast 202	java/lang/String
    //   368: astore 8
    //   370: aload_0
    //   371: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   374: new 378	java/lang/StringBuilder
    //   377: dup
    //   378: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   381: ldc_w 964
    //   384: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   387: aload 8
    //   389: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   392: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   395: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   398: aload 6
    //   400: aload 8
    //   402: invokevirtual 876	imoblife/luckad/ad/AdInfo:setImageUrl	(Ljava/lang/String;)V
    //   405: aload 6
    //   407: iconst_0
    //   408: invokevirtual 910	imoblife/luckad/ad/AdInfo:setConverted	(Z)V
    //   411: new 923	org/json/JSONObject
    //   414: dup
    //   415: aload 7
    //   417: ldc_w 966
    //   420: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   423: invokespecial 924	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   426: astore 7
    //   428: aload 7
    //   430: ldc_w 968
    //   433: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   436: astore 8
    //   438: aload 6
    //   440: aload 8
    //   442: invokevirtual 971	imoblife/luckad/ad/AdInfo:setAdFinalLink	(Ljava/lang/String;)V
    //   445: aload 6
    //   447: new 973	java/net/URI
    //   450: dup
    //   451: aload 8
    //   453: invokespecial 974	java/net/URI:<init>	(Ljava/lang/String;)V
    //   456: invokevirtual 978	imoblife/luckad/ad/AdInfo:setConversionUrl	(Ljava/net/URI;)V
    //   459: aload 6
    //   461: aload 7
    //   463: ldc_w 980
    //   466: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   469: invokevirtual 866	imoblife/luckad/ad/AdInfo:setRecordUrl	(Ljava/lang/String;)V
    //   472: aload 6
    //   474: iconst_1
    //   475: invokevirtual 910	imoblife/luckad/ad/AdInfo:setConverted	(Z)V
    //   478: aload 6
    //   480: ldc_w 614
    //   483: invokevirtual 680	imoblife/luckad/ad/AdInfo:setLoadStatus	(Ljava/lang/String;)V
    //   486: aload_0
    //   487: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   490: new 378	java/lang/StringBuilder
    //   493: dup
    //   494: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   497: ldc_w 982
    //   500: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   503: aload 8
    //   505: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   508: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   511: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   514: aload_0
    //   515: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   518: new 378	java/lang/StringBuilder
    //   521: dup
    //   522: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   525: ldc_w 984
    //   528: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   531: aload 7
    //   533: ldc_w 980
    //   536: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   539: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   542: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   545: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   548: aload_0
    //   549: invokevirtual 600	imoblife/luckad/ad/LuckAdNew:getAdList_Native	()Ljava/util/List;
    //   552: aload 6
    //   554: invokeinterface 371 2 0
    //   559: pop
    //   560: goto +706 -> 1266
    //   563: astore_1
    //   564: iconst_0
    //   565: putstatic 125	imoblife/luckad/ad/LuckAdNew:isRunningDownloadAds	Z
    //   568: aload_1
    //   569: invokevirtual 527	java/lang/Exception:printStackTrace	()V
    //   572: return
    //   573: astore 8
    //   575: aload 8
    //   577: invokevirtual 527	java/lang/Exception:printStackTrace	()V
    //   580: goto -175 -> 405
    //   583: astore 7
    //   585: aload 7
    //   587: invokevirtual 527	java/lang/Exception:printStackTrace	()V
    //   590: goto -42 -> 548
    //   593: aload_0
    //   594: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   597: new 378	java/lang/StringBuilder
    //   600: dup
    //   601: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   604: ldc_w 1722
    //   607: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   610: aload_1
    //   611: invokevirtual 933	org/json/JSONArray:length	()I
    //   614: invokevirtual 416	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   617: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   620: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   623: new 338	java/util/ArrayList
    //   626: dup
    //   627: invokespecial 339	java/util/ArrayList:<init>	()V
    //   630: astore 6
    //   632: iconst_0
    //   633: istore_2
    //   634: iload_2
    //   635: aload_1
    //   636: invokevirtual 933	org/json/JSONArray:length	()I
    //   639: if_icmpge +594 -> 1233
    //   642: aload_1
    //   643: iload_2
    //   644: invokevirtual 934	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   647: checkcast 923	org/json/JSONObject
    //   650: astore 8
    //   652: aload_0
    //   653: getfield 198	imoblife/luckad/ad/LuckAdNew:context	Landroid/content/Context;
    //   656: aload 8
    //   658: ldc_w 936
    //   661: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   664: invokestatic 535	base/util/PackageUtil:isPackageInstalled	(Landroid/content/Context;Ljava/lang/String;)Z
    //   667: ifeq +613 -> 1280
    //   670: goto +603 -> 1273
    //   673: iload 5
    //   675: istore 4
    //   677: iload_3
    //   678: aload_0
    //   679: invokevirtual 600	imoblife/luckad/ad/LuckAdNew:getAdList_Native	()Ljava/util/List;
    //   682: invokeinterface 382 1 0
    //   687: if_icmpge +87 -> 774
    //   690: aload_0
    //   691: invokevirtual 600	imoblife/luckad/ad/LuckAdNew:getAdList_Native	()Ljava/util/List;
    //   694: iload_3
    //   695: invokeinterface 385 2 0
    //   700: checkcast 445	imoblife/luckad/ad/AdInfo
    //   703: astore 7
    //   705: aload 8
    //   707: ldc_w 939
    //   710: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   713: invokevirtual 451	java/lang/String:trim	()Ljava/lang/String;
    //   716: aload 7
    //   718: invokevirtual 944	imoblife/luckad/ad/AdInfo:getTrackUrl	()Ljava/lang/String;
    //   721: invokevirtual 451	java/lang/String:trim	()Ljava/lang/String;
    //   724: invokevirtual 454	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   727: ifeq +561 -> 1288
    //   730: aload_0
    //   731: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   734: new 378	java/lang/StringBuilder
    //   737: dup
    //   738: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   741: ldc_w 1700
    //   744: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   747: iload_3
    //   748: invokevirtual 416	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   751: ldc_w 1692
    //   754: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   757: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   760: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   763: iconst_1
    //   764: istore 4
    //   766: aload 6
    //   768: aload 7
    //   770: invokevirtual 911	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   773: pop
    //   774: iload 4
    //   776: ifne +497 -> 1273
    //   779: new 445	imoblife/luckad/ad/AdInfo
    //   782: dup
    //   783: invokespecial 830	imoblife/luckad/ad/AdInfo:<init>	()V
    //   786: astore 7
    //   788: aload 7
    //   790: aload 8
    //   792: ldc_w 936
    //   795: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   798: invokevirtual 833	imoblife/luckad/ad/AdInfo:setPkgName	(Ljava/lang/String;)V
    //   801: aload 7
    //   803: aload 8
    //   805: ldc_w 939
    //   808: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   811: invokevirtual 861	imoblife/luckad/ad/AdInfo:setTrackUrl	(Ljava/lang/String;)V
    //   814: aload_0
    //   815: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   818: new 378	java/lang/StringBuilder
    //   821: dup
    //   822: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   825: ldc_w 941
    //   828: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   831: aload 7
    //   833: invokevirtual 944	imoblife/luckad/ad/AdInfo:getTrackUrl	()Ljava/lang/String;
    //   836: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   839: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   842: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   845: new 923	org/json/JSONObject
    //   848: dup
    //   849: aload 8
    //   851: ldc_w 946
    //   854: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   857: invokespecial 924	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   860: ldc_w 948
    //   863: invokevirtual 930	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   866: iconst_0
    //   867: invokevirtual 934	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   870: checkcast 202	java/lang/String
    //   873: astore 9
    //   875: aload_0
    //   876: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   879: new 378	java/lang/StringBuilder
    //   882: dup
    //   883: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   886: ldc_w 950
    //   889: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   892: aload 9
    //   894: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   897: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   900: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   903: aload 9
    //   905: ifnull +20 -> 925
    //   908: aload 9
    //   910: ldc 117
    //   912: invokevirtual 454	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   915: ifne +10 -> 925
    //   918: aload 7
    //   920: aload 9
    //   922: invokevirtual 866	imoblife/luckad/ad/AdInfo:setRecordUrl	(Ljava/lang/String;)V
    //   925: new 923	org/json/JSONObject
    //   928: dup
    //   929: aload 8
    //   931: ldc_w 952
    //   934: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   937: invokespecial 924	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   940: astore 9
    //   942: aload 7
    //   944: aload 9
    //   946: ldc_w 954
    //   949: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   952: invokevirtual 881	imoblife/luckad/ad/AdInfo:setTitle	(Ljava/lang/String;)V
    //   955: aload 7
    //   957: aload 9
    //   959: ldc_w 956
    //   962: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   965: invokevirtual 871	imoblife/luckad/ad/AdInfo:setContent	(Ljava/lang/String;)V
    //   968: aload 7
    //   970: aload 9
    //   972: ldc_w 958
    //   975: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   978: invokevirtual 886	imoblife/luckad/ad/AdInfo:setIconUrl	(Ljava/lang/String;)V
    //   981: new 923	org/json/JSONObject
    //   984: dup
    //   985: aload 9
    //   987: ldc_w 960
    //   990: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   993: invokespecial 924	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   996: ldc_w 962
    //   999: invokevirtual 930	org/json/JSONObject:getJSONArray	(Ljava/lang/String;)Lorg/json/JSONArray;
    //   1002: astore 9
    //   1004: aload 9
    //   1006: iconst_0
    //   1007: invokevirtual 934	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   1010: ifnull +49 -> 1059
    //   1013: aload 9
    //   1015: iconst_0
    //   1016: invokevirtual 934	org/json/JSONArray:get	(I)Ljava/lang/Object;
    //   1019: checkcast 202	java/lang/String
    //   1022: astore 9
    //   1024: aload_0
    //   1025: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   1028: new 378	java/lang/StringBuilder
    //   1031: dup
    //   1032: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   1035: ldc_w 964
    //   1038: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1041: aload 9
    //   1043: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1046: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1049: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   1052: aload 7
    //   1054: aload 9
    //   1056: invokevirtual 876	imoblife/luckad/ad/AdInfo:setImageUrl	(Ljava/lang/String;)V
    //   1059: aload 7
    //   1061: iconst_0
    //   1062: invokevirtual 910	imoblife/luckad/ad/AdInfo:setConverted	(Z)V
    //   1065: new 923	org/json/JSONObject
    //   1068: dup
    //   1069: aload 8
    //   1071: ldc_w 966
    //   1074: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1077: invokespecial 924	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   1080: astore 8
    //   1082: aload 8
    //   1084: ldc_w 968
    //   1087: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1090: astore 9
    //   1092: aload 7
    //   1094: aload 9
    //   1096: invokevirtual 971	imoblife/luckad/ad/AdInfo:setAdFinalLink	(Ljava/lang/String;)V
    //   1099: aload 7
    //   1101: new 973	java/net/URI
    //   1104: dup
    //   1105: aload 9
    //   1107: invokespecial 974	java/net/URI:<init>	(Ljava/lang/String;)V
    //   1110: invokevirtual 978	imoblife/luckad/ad/AdInfo:setConversionUrl	(Ljava/net/URI;)V
    //   1113: aload 7
    //   1115: aload 8
    //   1117: ldc_w 980
    //   1120: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1123: invokevirtual 866	imoblife/luckad/ad/AdInfo:setRecordUrl	(Ljava/lang/String;)V
    //   1126: aload 7
    //   1128: iconst_1
    //   1129: invokevirtual 910	imoblife/luckad/ad/AdInfo:setConverted	(Z)V
    //   1132: aload 7
    //   1134: ldc_w 614
    //   1137: invokevirtual 680	imoblife/luckad/ad/AdInfo:setLoadStatus	(Ljava/lang/String;)V
    //   1140: aload_0
    //   1141: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   1144: new 378	java/lang/StringBuilder
    //   1147: dup
    //   1148: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   1151: ldc_w 982
    //   1154: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1157: aload 9
    //   1159: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1162: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1165: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   1168: aload_0
    //   1169: getstatic 115	imoblife/luckad/ad/LuckAdNew:TAG	Ljava/lang/String;
    //   1172: new 378	java/lang/StringBuilder
    //   1175: dup
    //   1176: invokespecial 379	java/lang/StringBuilder:<init>	()V
    //   1179: ldc_w 984
    //   1182: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1185: aload 8
    //   1187: ldc_w 980
    //   1190: invokevirtual 937	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   1193: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1196: invokevirtual 405	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1199: invokevirtual 552	imoblife/luckad/ad/LuckAdNew:logPrint	(Ljava/lang/String;Ljava/lang/String;)V
    //   1202: aload 6
    //   1204: aload 7
    //   1206: invokevirtual 911	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   1209: pop
    //   1210: goto +63 -> 1273
    //   1213: astore 9
    //   1215: aload 9
    //   1217: invokevirtual 527	java/lang/Exception:printStackTrace	()V
    //   1220: goto -161 -> 1059
    //   1223: astore 8
    //   1225: aload 8
    //   1227: invokevirtual 527	java/lang/Exception:printStackTrace	()V
    //   1230: goto -28 -> 1202
    //   1233: aload_0
    //   1234: new 338	java/util/ArrayList
    //   1237: dup
    //   1238: invokespecial 339	java/util/ArrayList:<init>	()V
    //   1241: invokevirtual 1681	imoblife/luckad/ad/LuckAdNew:setAdList_Native	(Ljava/util/List;)V
    //   1244: aload_0
    //   1245: invokevirtual 600	imoblife/luckad/ad/LuckAdNew:getAdList_Native	()Ljava/util/List;
    //   1248: invokeinterface 1635 1 0
    //   1253: aload_0
    //   1254: invokevirtual 600	imoblife/luckad/ad/LuckAdNew:getAdList_Native	()Ljava/util/List;
    //   1257: aload 6
    //   1259: invokeinterface 1682 2 0
    //   1264: pop
    //   1265: return
    //   1266: iload_2
    //   1267: iconst_1
    //   1268: iadd
    //   1269: istore_2
    //   1270: goto -1184 -> 86
    //   1273: iload_2
    //   1274: iconst_1
    //   1275: iadd
    //   1276: istore_2
    //   1277: goto -643 -> 634
    //   1280: iconst_0
    //   1281: istore 5
    //   1283: iconst_0
    //   1284: istore_3
    //   1285: goto -612 -> 673
    //   1288: iload_3
    //   1289: iconst_1
    //   1290: iadd
    //   1291: istore_3
    //   1292: goto -619 -> 673
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1295	0	this	LuckAdNew
    //   0	1295	1	paramString	String
    //   85	1192	2	i	int
    //   677	615	3	j	int
    //   675	100	4	k	int
    //   673	609	5	m	int
    //   132	1126	6	localObject1	Object
    //   102	430	7	localJSONObject1	JSONObject
    //   583	3	7	localException1	Exception
    //   703	502	7	localAdInfo	AdInfo
    //   219	285	8	localObject2	Object
    //   573	3	8	localException2	Exception
    //   650	536	8	localJSONObject2	JSONObject
    //   1223	3	8	localException3	Exception
    //   873	285	9	localObject3	Object
    //   1213	3	9	localException4	Exception
    // Exception table:
    //   from	to	target	type
    //   4	16	563	java/lang/Exception
    //   17	54	563	java/lang/Exception
    //   54	84	563	java/lang/Exception
    //   86	122	563	java/lang/Exception
    //   125	249	563	java/lang/Exception
    //   254	271	563	java/lang/Exception
    //   271	327	563	java/lang/Exception
    //   405	411	563	java/lang/Exception
    //   548	560	563	java/lang/Exception
    //   575	580	563	java/lang/Exception
    //   585	590	563	java/lang/Exception
    //   593	632	563	java/lang/Exception
    //   634	670	563	java/lang/Exception
    //   677	763	563	java/lang/Exception
    //   766	774	563	java/lang/Exception
    //   779	903	563	java/lang/Exception
    //   908	925	563	java/lang/Exception
    //   925	981	563	java/lang/Exception
    //   1059	1065	563	java/lang/Exception
    //   1202	1210	563	java/lang/Exception
    //   1215	1220	563	java/lang/Exception
    //   1225	1230	563	java/lang/Exception
    //   1233	1265	563	java/lang/Exception
    //   327	405	573	java/lang/Exception
    //   411	548	583	java/lang/Exception
    //   981	1059	1213	java/lang/Exception
    //   1065	1202	1223	java/lang/Exception
  }
  
  public void parseGlispaAdInfoUpdAll(String paramString)
  {
    parseGlispaAdInfoFromJson(paramString);
    logPrint(TAG, "parseGlispaAdInfoUpdAll native List Size-new->" + getAdList_Native().size());
    if ((getAdList_Native() != null) && (getAdList_Native().size() > 0))
    {
      if (this.preloadAdsNum <= 0) {
        break label130;
      }
      logPrint(TAG, "Glispa native load " + this.preloadAdsNum + " feed!!!");
      new LoadLinkTask(this.context, getListener(), this.preloadAdsNum).execute(new Integer[0]);
    }
    label130:
    while (this.listener == null) {
      return;
    }
    this.listener.onLoadSuccess();
  }
  
  public void parseGlispaPubnativeAdInfo(String paramString1, String paramString2, String paramString3)
  {
    label168:
    int i;
    try
    {
      paramString1 = getGlispaPubNativeAdsArray(paramString1, paramString2);
      if (paramString1 == null) {
        break label550;
      }
      if (paramString1.size() == 0) {
        return;
      }
      if ((getAdList_Native() != null) && (getAdList_Native().size() != 0)) {
        break label168;
      }
      setAdList_Native(new ArrayList());
      getAdList_Native().clear();
      getAdList_Native().addAll(paramString1);
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        label405:
        isRunningDownloadAds = false;
        paramString1.printStackTrace();
        continue;
        label461:
        j += 1;
        continue;
        setAdList_Native(new ArrayList());
        getAdList_Native().addAll(paramString2);
      }
      if (this.listener == null) {
        break label550;
      }
      this.listener.onLoadSuccess();
      return;
      if (this.preloadAdsNum <= 0) {
        break label534;
      }
      logPrint(TAG, "glispa Pubnative native load 1 feed!!!");
      firstCheckLoadAds();
      return;
      label534:
      if (this.listener == null) {
        break label550;
      }
      this.listener.onLoadSuccess();
    }
    if ((getAdList_Native() != null) && (getAdList_Native().size() > 0)) {
      if (paramString3.equals("all")) {
        if (this.preloadAdsNum > 0)
        {
          logPrint(TAG, "Glispa pubnative load " + this.preloadAdsNum + " feed!!!");
          new LoadLinkTask(this.context, getListener(), this.preloadAdsNum).execute(new Integer[0]);
          return;
          logPrint(TAG, "parseGlispaPubnativeAdInfo---->list already exist!-new ads size->" + paramString1.size());
          paramString2 = new ArrayList();
          i = 0;
        }
      }
    }
    for (;;)
    {
      int j;
      if (i < paramString1.size())
      {
        AdInfo localAdInfo1 = (AdInfo)paramString1.get(i);
        int m = 0;
        j = 0;
        int k = m;
        if (j < getAdList_Native().size())
        {
          AdInfo localAdInfo2 = (AdInfo)getAdList_Native().get(j);
          if (!localAdInfo1.getPkgName().trim().equals(localAdInfo2.getPkgName().trim())) {
            break label461;
          }
          logPrint(TAG, "ad list--" + j + "-->exist!!!");
          k = 1;
          if ((!localAdInfo2.getLoadStatus().equals("succeed")) || (!this.urlCheckCache)) {
            break label405;
          }
          logPrint(TAG, "ad list--" + j + "-without preload->exist!!!");
          paramString2.add(localAdInfo2);
        }
        while (k == 0)
        {
          paramString2.add(localAdInfo1);
          break;
          logPrint(TAG, "ad list--" + j + "-need preload->exist!!!");
          paramString2.add(localAdInfo1);
        }
      }
      label550:
      return;
      i += 1;
    }
  }
  
  public void parseMobCoreAdInfo(String paramString1, String paramString2)
  {
    if (paramString2.equals("all"))
    {
      parseMobCoreAdInfoAll(paramString1);
      if ((getAdList_Native() != null) && (getAdList_Native().size() > 0))
      {
        logPrint(TAG, "MobCore List Size->" + getAdList_Native().size());
        if (this.preloadAdsNum <= 0) {
          break label143;
        }
        logPrint(TAG, "MobCore native load " + this.preloadAdsNum + " feed!!!");
        new LoadLinkTask(this.context, getListener(), this.preloadAdsNum).execute(new Integer[0]);
        indexOfAdList = 0;
      }
    }
    label143:
    do
    {
      do
      {
        do
        {
          return;
        } while (this.listener == null);
        this.listener.onLoadSuccess();
        return;
        parseMobCoreAdInfoHour(paramString1);
      } while ((getAdList_Native() == null) || (getAdList_Native().size() <= 0));
      if (this.preloadAdsNum > 0)
      {
        logPrint(TAG, "Pubnative native load 1 feed!!!");
        firstCheckLoadAds();
        indexOfAdList = 0;
        return;
      }
    } while (this.listener == null);
    this.listener.onLoadSuccess();
  }
  
  public void parseMobCoreAdInfoAll(String paramString)
  {
    try
    {
      paramString = getMobCoreAdsArray(paramString);
      if (paramString != null)
      {
        if (paramString.size() == 0) {
          return;
        }
        if ((getAdList_Native() == null) || (getAdList_Native().size() == 0))
        {
          logPrint(TAG, "parseMobCoreAdInfo--update all-->new--" + indexOfAdList);
          setAdList_Native(new ArrayList());
          getAdList_Native().addAll(paramString);
          return;
        }
      }
    }
    catch (Exception paramString)
    {
      isRunningDownloadAds = false;
      paramString.printStackTrace();
    }
  }
  
  public void parseMobCoreAdInfoHour(String paramString)
  {
    AdInfo localAdInfo1;
    int m;
    int j;
    label134:
    int k;
    AdInfo localAdInfo2;
    try
    {
      paramString = getMobCoreAdsArray(paramString);
      if (paramString == null) {
        break label743;
      }
      if (paramString.size() == 0) {
        return;
      }
      if ((getAdList_Native() != null) && (getAdList_Native().size() != 0)) {
        break label396;
      }
      logPrint(TAG, "parseMobCoreAdInfo---->new--" + indexOfAdList);
      if ((adPreparedList != null) && (adPreparedList.size() > 0) && (this.isLoadAdsFromPrepareList))
      {
        logPrint(TAG, "LuckAdNew::Load ads from prepare list");
        localArrayList = new ArrayList();
        i = 0;
        if (i < paramString.size())
        {
          localAdInfo1 = (AdInfo)paramString.get(i);
          m = 0;
          j = 0;
          k = m;
          if (j < adPreparedList.size())
          {
            localAdInfo2 = (AdInfo)adPreparedList.get(j);
            if ((!localAdInfo1.getPkgName().trim().equals(localAdInfo2.getPkgName().trim())) || (!isRightURL(localAdInfo2.getAdFinalLink()))) {
              break label751;
            }
            logPrint(TAG, "ad list-title-" + j + localAdInfo1.getTilte() + "-->exist!!!");
            logPrint(TAG, "ad list-url-" + j + localAdInfo2.getAdFinalLink());
            k = 1;
            localAdInfo1.setAdFinalLink(localAdInfo2.getAdFinalLink());
            localAdInfo1.setConversionUrl(localAdInfo2.getConversionUrl());
            localAdInfo1.setLoadStatus("succeed");
            localArrayList.add(localAdInfo1);
          }
          if (k != 0) {
            break label744;
          }
          localArrayList.add(localAdInfo1);
          break label744;
        }
        setAdList_Native(new ArrayList());
        getAdList_Native().addAll(localArrayList);
        return;
      }
    }
    catch (Exception paramString)
    {
      isRunningDownloadAds = false;
      paramString.printStackTrace();
      return;
    }
    logPrint(TAG, "LuckAdNew::Do not load ads from prepare list");
    setAdList_Native(new ArrayList());
    getAdList_Native().addAll(paramString);
    return;
    label396:
    logPrint(TAG, "parseMobCoreAdInfo---->list already exist!-new ads size->" + paramString.size());
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    label437:
    if (i < paramString.size())
    {
      localAdInfo1 = (AdInfo)paramString.get(i);
      m = 0;
      j = 0;
    }
    for (;;)
    {
      k = m;
      if (j < getAdList_Native().size())
      {
        localAdInfo2 = (AdInfo)getAdList_Native().get(j);
        if (!localAdInfo1.getPkgName().trim().equals(localAdInfo2.getPkgName().trim())) {
          break label765;
        }
        logPrint(TAG, "ad list--" + j + localAdInfo1.getPkgName() + "-->exist!!!");
        k = 1;
        if ((!localAdInfo2.getLoadStatus().equals("succeed")) || (!isRightURL(localAdInfo2.getAdFinalLink()))) {
          break label676;
        }
        logPrint(TAG, "ad list--" + j + "-without preload->exist!!!");
        logPrint(TAG, "ad list-url-" + j + localAdInfo2.getAdFinalLink());
        localArrayList.add(localAdInfo2);
      }
      for (;;)
      {
        if (k == 0)
        {
          localArrayList.add(localAdInfo1);
          break label758;
          label676:
          logPrint(TAG, "ad list--" + j + "-need preload->exist!!!");
          localArrayList.add(localAdInfo1);
          continue;
          setAdList_Native(new ArrayList());
          getAdList_Native().addAll(localArrayList);
          label743:
          return;
          label744:
          i += 1;
          break;
          label751:
          j += 1;
          break label134;
        }
      }
      label758:
      i += 1;
      break label437;
      label765:
      j += 1;
    }
  }
  
  public void parsePubAdInfoByHours(String paramString)
  {
    parsePubAdInfoFromJson(paramString);
    if ((getAdList_Native() != null) && (getAdList_Native().size() > 0))
    {
      if (this.preloadAdsNum <= 0) {
        break label50;
      }
      logPrint(TAG, "Pubnative native load 1 feed!!!");
      firstCheckLoadAds();
      indexOfAdList = 0;
    }
    label50:
    while (this.listener == null) {
      return;
    }
    this.listener.onLoadSuccess();
  }
  
  public void parsePubAdInfoFromJson(String paramString)
  {
    AdInfo localAdInfo1;
    int m;
    int j;
    label134:
    int k;
    AdInfo localAdInfo2;
    try
    {
      paramString = getPubNativeAdsArray(paramString);
      if (paramString == null) {
        break label743;
      }
      if (paramString.size() == 0) {
        return;
      }
      if ((getAdList_Native() != null) && (getAdList_Native().size() != 0)) {
        break label396;
      }
      logPrint(TAG, "parsePubnativeAdInfo---->new--" + indexOfAdList);
      if ((adPreparedList != null) && (adPreparedList.size() > 0) && (this.isLoadAdsFromPrepareList))
      {
        logPrint(TAG, "LuckAdNew::Load ads from prepare list");
        localArrayList = new ArrayList();
        i = 0;
        if (i < paramString.size())
        {
          localAdInfo1 = (AdInfo)paramString.get(i);
          m = 0;
          j = 0;
          k = m;
          if (j < adPreparedList.size())
          {
            localAdInfo2 = (AdInfo)adPreparedList.get(j);
            if ((!localAdInfo1.getPkgName().trim().equals(localAdInfo2.getPkgName().trim())) || (!isRightURL(localAdInfo2.getAdFinalLink()))) {
              break label751;
            }
            logPrint(TAG, "ad list-title-" + j + localAdInfo1.getTilte() + "-->exist!!!");
            logPrint(TAG, "ad list-url-" + j + localAdInfo2.getAdFinalLink());
            k = 1;
            localAdInfo1.setAdFinalLink(localAdInfo2.getAdFinalLink());
            localAdInfo1.setConversionUrl(localAdInfo2.getConversionUrl());
            localAdInfo1.setLoadStatus("succeed");
            localArrayList.add(localAdInfo1);
          }
          if (k != 0) {
            break label744;
          }
          localArrayList.add(localAdInfo1);
          break label744;
        }
        setAdList_Native(new ArrayList());
        getAdList_Native().addAll(localArrayList);
        return;
      }
    }
    catch (Exception paramString)
    {
      isRunningDownloadAds = false;
      paramString.printStackTrace();
      return;
    }
    logPrint(TAG, "LuckAdNew::Do not load ads from prepare list");
    setAdList_Native(new ArrayList());
    getAdList_Native().addAll(paramString);
    return;
    label396:
    logPrint(TAG, "parsePubnativeAdInfo---->list already exist!-new ads size->" + paramString.size());
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    label437:
    if (i < paramString.size())
    {
      localAdInfo1 = (AdInfo)paramString.get(i);
      m = 0;
      j = 0;
    }
    for (;;)
    {
      k = m;
      if (j < getAdList_Native().size())
      {
        localAdInfo2 = (AdInfo)getAdList_Native().get(j);
        if (!localAdInfo1.getPkgName().trim().equals(localAdInfo2.getPkgName().trim())) {
          break label765;
        }
        logPrint(TAG, "ad list--" + j + localAdInfo1.getPkgName() + "-->exist!!!");
        k = 1;
        if ((!localAdInfo2.getLoadStatus().equals("succeed")) || (!isRightURL(localAdInfo2.getAdFinalLink()))) {
          break label676;
        }
        logPrint(TAG, "ad list--" + j + "-without preload->exist!!!");
        logPrint(TAG, "ad list-url-" + j + localAdInfo2.getAdFinalLink());
        localArrayList.add(localAdInfo2);
      }
      for (;;)
      {
        if (k == 0)
        {
          localArrayList.add(localAdInfo1);
          break label758;
          label676:
          logPrint(TAG, "ad list--" + j + "-need preload->exist!!!");
          localArrayList.add(localAdInfo1);
          continue;
          setAdList_Native(new ArrayList());
          getAdList_Native().addAll(localArrayList);
          label743:
          return;
          label744:
          i += 1;
          break;
          label751:
          j += 1;
          break label134;
        }
      }
      label758:
      i += 1;
      break label437;
      label765:
      j += 1;
    }
  }
  
  public void parsePubAdInfoFromJsonAll(String paramString)
  {
    try
    {
      paramString = getPubNativeAdsArray(paramString);
      if (paramString != null)
      {
        if (paramString.size() == 0) {
          return;
        }
        if ((getAdList_Native() == null) || (getAdList_Native().size() == 0))
        {
          logPrint(TAG, "parsePubnativeAdInfo--update all-->new--" + indexOfAdList);
          setAdList_Native(new ArrayList());
          getAdList_Native().addAll(paramString);
          return;
        }
      }
    }
    catch (Exception paramString)
    {
      isRunningDownloadAds = false;
      paramString.printStackTrace();
    }
  }
  
  public void parsePubAdInfoUpdAll(String paramString)
  {
    parsePubAdInfoFromJsonAll(paramString);
    if ((getAdList_Native() != null) && (getAdList_Native().size() > 0))
    {
      logPrint(TAG, "PubNative List Size->" + getAdList_Native().size());
      if (this.preloadAdsNum <= 0) {
        break label134;
      }
      logPrint(TAG, "PubNative native load " + this.preloadAdsNum + " feed!!!");
      new LoadLinkTask(this.context, getListener(), this.preloadAdsNum).execute(new Integer[0]);
      indexOfAdList = 0;
    }
    label134:
    while (this.listener == null) {
      return;
    }
    this.listener.onLoadSuccess();
  }
  
  public void parsePubAppiaAdInfo(String paramString1, String paramString2, String paramString3)
  {
    label168:
    int i;
    try
    {
      paramString1 = getPubAppiaAdsArray(paramString1, paramString2);
      if (paramString1 == null) {
        break label555;
      }
      if (paramString1.size() == 0) {
        return;
      }
      if ((getAdList_Native() != null) && (getAdList_Native().size() != 0)) {
        break label168;
      }
      setAdList_Native(new ArrayList());
      getAdList_Native().clear();
      getAdList_Native().addAll(paramString1);
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        label410:
        isRunningDownloadAds = false;
        paramString1.printStackTrace();
        continue;
        label466:
        j += 1;
        continue;
        setAdList_Native(new ArrayList());
        getAdList_Native().addAll(paramString2);
      }
      if (this.listener == null) {
        break label555;
      }
      this.listener.onLoadSuccess();
      return;
      if (this.preloadAdsNum <= 0) {
        break label539;
      }
      logPrint(TAG, "glispa Pubnative native load 1 feed!!!");
      firstCheckLoadAds();
      return;
      label539:
      if (this.listener == null) {
        break label555;
      }
      this.listener.onLoadSuccess();
    }
    if ((getAdList_Native() != null) && (getAdList_Native().size() > 0)) {
      if (paramString3.equals("all")) {
        if (this.preloadAdsNum > 0)
        {
          logPrint(TAG, "Glispa pubnative load " + this.preloadAdsNum + " feed!!!");
          new LoadLinkTask(this.context, getListener(), this.preloadAdsNum).execute(new Integer[0]);
          return;
          logPrint(TAG, "parsePubAppiaAdInfo---->list already exist!-new ads size->" + paramString1.size());
          paramString2 = new ArrayList();
          i = 0;
        }
      }
    }
    for (;;)
    {
      int j;
      if (i < paramString1.size())
      {
        AdInfo localAdInfo1 = (AdInfo)paramString1.get(i);
        int m = 0;
        j = 0;
        int k = m;
        if (j < getAdList_Native().size())
        {
          AdInfo localAdInfo2 = (AdInfo)getAdList_Native().get(j);
          if (!localAdInfo1.getPkgName().trim().equals(localAdInfo2.getPkgName().trim())) {
            break label466;
          }
          logPrint(TAG, "ad list--" + j + "-->exist!!!");
          k = 1;
          if ((!localAdInfo2.getLoadStatus().equals("succeed")) || (!isRightURL(localAdInfo2.getAdFinalLink()))) {
            break label410;
          }
          logPrint(TAG, "ad list--" + j + "-without preload->exist!!!");
          paramString2.add(localAdInfo2);
        }
        while (k == 0)
        {
          paramString2.add(localAdInfo1);
          break;
          logPrint(TAG, "ad list--" + j + "-need preload->exist!!!");
          paramString2.add(localAdInfo1);
        }
      }
      label555:
      return;
      i += 1;
    }
  }
  
  public void parsePubMobCoreAdInfo(String paramString1, String paramString2, String paramString3)
  {
    label168:
    int i;
    try
    {
      paramString1 = getPubMobCoreAdsArray(paramString1, paramString2);
      if (paramString1 == null) {
        break label555;
      }
      if (paramString1.size() == 0) {
        return;
      }
      if ((getAdList_Native() != null) && (getAdList_Native().size() != 0)) {
        break label168;
      }
      setAdList_Native(new ArrayList());
      getAdList_Native().clear();
      getAdList_Native().addAll(paramString1);
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        label410:
        isRunningDownloadAds = false;
        paramString1.printStackTrace();
        continue;
        label466:
        j += 1;
        continue;
        setAdList_Native(new ArrayList());
        getAdList_Native().addAll(paramString2);
      }
      if (this.listener == null) {
        break label555;
      }
      this.listener.onLoadSuccess();
      return;
      if (this.preloadAdsNum <= 0) {
        break label539;
      }
      logPrint(TAG, "glispa Pubnative native load 1 feed!!!");
      firstCheckLoadAds();
      return;
      label539:
      if (this.listener == null) {
        break label555;
      }
      this.listener.onLoadSuccess();
    }
    if ((getAdList_Native() != null) && (getAdList_Native().size() > 0)) {
      if (paramString3.equals("all")) {
        if (this.preloadAdsNum > 0)
        {
          logPrint(TAG, "Glispa pubnative load " + this.preloadAdsNum + " feed!!!");
          new LoadLinkTask(this.context, getListener(), this.preloadAdsNum).execute(new Integer[0]);
          return;
          logPrint(TAG, "parsePubMobCoreAdInfo---->list already exist!-new ads size->" + paramString1.size());
          paramString2 = new ArrayList();
          i = 0;
        }
      }
    }
    for (;;)
    {
      int j;
      if (i < paramString1.size())
      {
        AdInfo localAdInfo1 = (AdInfo)paramString1.get(i);
        int m = 0;
        j = 0;
        int k = m;
        if (j < getAdList_Native().size())
        {
          AdInfo localAdInfo2 = (AdInfo)getAdList_Native().get(j);
          if (!localAdInfo1.getPkgName().trim().equals(localAdInfo2.getPkgName().trim())) {
            break label466;
          }
          logPrint(TAG, "ad list--" + j + "-->exist!!!");
          k = 1;
          if ((!localAdInfo2.getLoadStatus().equals("succeed")) || (!isRightURL(localAdInfo2.getAdFinalLink()))) {
            break label410;
          }
          logPrint(TAG, "ad list--" + j + "-without preload->exist!!!");
          paramString2.add(localAdInfo2);
        }
        while (k == 0)
        {
          paramString2.add(localAdInfo1);
          break;
          logPrint(TAG, "ad list--" + j + "-need preload->exist!!!");
          paramString2.add(localAdInfo1);
        }
      }
      label555:
      return;
      i += 1;
    }
  }
  
  public void parseYMAdWallInfo(String paramString)
  {
    Log.i(TAG, "YM::" + paramString);
    if ((paramString != null) && (!paramString.trim().equals("")))
    {
      paramString = getYeahmobiAdsArray(paramString);
      if ((paramString != null) && (paramString.size() != 0))
      {
        this.ad_list_wall = new ArrayList();
        setAd_list_wall(paramString);
      }
    }
  }
  
  public void parseYeahmobiAdInfo(String paramString1, String paramString2)
  {
    if (paramString2.equals("all"))
    {
      parseYeahmobiAdInfoAll(paramString1);
      if ((getAdList_Native() != null) && (getAdList_Native().size() > 0))
      {
        logPrint(TAG, "Yeahmobi List Size->" + getAdList_Native().size());
        if (this.preloadAdsNum <= 0) {
          break label143;
        }
        logPrint(TAG, "Yeahmobi native load " + this.preloadAdsNum + " feed!!!");
        new LoadLinkTask(this.context, getListener(), this.preloadAdsNum).execute(new Integer[0]);
        indexOfAdList = 0;
      }
    }
    label143:
    do
    {
      do
      {
        do
        {
          return;
        } while (this.listener == null);
        this.listener.onLoadSuccess();
        return;
        parseYeahmobiAdInfoHour(paramString1);
      } while ((getAdList_Native() == null) || (getAdList_Native().size() <= 0));
      if (this.preloadAdsNum > 0)
      {
        logPrint(TAG, "Yeahmobi native load 1 feed!!!");
        firstCheckLoadAds();
        indexOfAdList = 0;
        return;
      }
    } while (this.listener == null);
    this.listener.onLoadSuccess();
  }
  
  public void parseYeahmobiAdInfoAll(String paramString)
  {
    try
    {
      paramString = getYeahmobiAdsArray(paramString);
      if (paramString != null)
      {
        if (paramString.size() == 0) {
          return;
        }
        if ((getAdList_Native() == null) || (getAdList_Native().size() == 0))
        {
          logPrint(TAG, "parseYeahmobiAdInfo--update all-->new--" + indexOfAdList);
          setAdList_Native(new ArrayList());
          getAdList_Native().addAll(paramString);
          return;
        }
      }
    }
    catch (Exception paramString)
    {
      isRunningDownloadAds = false;
      paramString.printStackTrace();
    }
  }
  
  public void parseYeahmobiAdInfoHour(String paramString)
  {
    AdInfo localAdInfo1;
    int m;
    int j;
    label134:
    int k;
    AdInfo localAdInfo2;
    try
    {
      paramString = getYeahmobiAdsArray(paramString);
      if (paramString == null) {
        break label743;
      }
      if (paramString.size() == 0) {
        return;
      }
      if ((getAdList_Native() != null) && (getAdList_Native().size() != 0)) {
        break label396;
      }
      logPrint(TAG, "parseYeahmobiAdInfo---->new--" + indexOfAdList);
      if ((adPreparedList != null) && (adPreparedList.size() > 0) && (this.isLoadAdsFromPrepareList))
      {
        logPrint(TAG, "LuckAdNew::Load ads from prepare list");
        localArrayList = new ArrayList();
        i = 0;
        if (i < paramString.size())
        {
          localAdInfo1 = (AdInfo)paramString.get(i);
          m = 0;
          j = 0;
          k = m;
          if (j < adPreparedList.size())
          {
            localAdInfo2 = (AdInfo)adPreparedList.get(j);
            if ((!localAdInfo1.getPkgName().trim().equals(localAdInfo2.getPkgName().trim())) || (!isRightURL(localAdInfo2.getAdFinalLink()))) {
              break label751;
            }
            logPrint(TAG, "ad list-title-" + j + localAdInfo1.getTilte() + "-->exist!!!");
            logPrint(TAG, "ad list-url-" + j + localAdInfo2.getAdFinalLink());
            k = 1;
            localAdInfo1.setAdFinalLink(localAdInfo2.getAdFinalLink());
            localAdInfo1.setConversionUrl(localAdInfo2.getConversionUrl());
            localAdInfo1.setLoadStatus("succeed");
            localArrayList.add(localAdInfo1);
          }
          if (k != 0) {
            break label744;
          }
          localArrayList.add(localAdInfo1);
          break label744;
        }
        setAdList_Native(new ArrayList());
        getAdList_Native().addAll(localArrayList);
        return;
      }
    }
    catch (Exception paramString)
    {
      isRunningDownloadAds = false;
      paramString.printStackTrace();
      return;
    }
    logPrint(TAG, "LuckAdNew::Do not load ads from prepare list");
    setAdList_Native(new ArrayList());
    getAdList_Native().addAll(paramString);
    return;
    label396:
    logPrint(TAG, "parseYeahmobiAdInfo---->list already exist!-new ads size->" + paramString.size());
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    label437:
    if (i < paramString.size())
    {
      localAdInfo1 = (AdInfo)paramString.get(i);
      m = 0;
      j = 0;
    }
    for (;;)
    {
      k = m;
      if (j < getAdList_Native().size())
      {
        localAdInfo2 = (AdInfo)getAdList_Native().get(j);
        if (!localAdInfo1.getPkgName().trim().equals(localAdInfo2.getPkgName().trim())) {
          break label765;
        }
        logPrint(TAG, "ad list--" + j + localAdInfo1.getPkgName() + "-->exist!!!");
        k = 1;
        if ((!localAdInfo2.getLoadStatus().equals("succeed")) || (!isRightURL(localAdInfo2.getAdFinalLink()))) {
          break label676;
        }
        logPrint(TAG, "ad list--" + j + "-without preload->exist!!!");
        logPrint(TAG, "ad list-url-" + j + localAdInfo2.getAdFinalLink());
        localArrayList.add(localAdInfo2);
      }
      for (;;)
      {
        if (k == 0)
        {
          localArrayList.add(localAdInfo1);
          break label758;
          label676:
          logPrint(TAG, "ad list--" + j + "-need preload->exist!!!");
          localArrayList.add(localAdInfo1);
          continue;
          setAdList_Native(new ArrayList());
          getAdList_Native().addAll(localArrayList);
          label743:
          return;
          label744:
          i += 1;
          break;
          label751:
          j += 1;
          break label134;
        }
      }
      label758:
      i += 1;
      break label437;
      label765:
      j += 1;
    }
  }
  
  public void parseYeahmobiPubnativeAdInfo(String paramString1, String paramString2, String paramString3)
  {
    label168:
    int i;
    try
    {
      paramString1 = getYeahmobiPubNativeAdsArray(paramString1, paramString2);
      if (paramString1 == null) {
        break label555;
      }
      if (paramString1.size() == 0) {
        return;
      }
      if ((getAdList_Native() != null) && (getAdList_Native().size() != 0)) {
        break label168;
      }
      setAdList_Native(new ArrayList());
      getAdList_Native().clear();
      getAdList_Native().addAll(paramString1);
    }
    catch (Exception paramString1)
    {
      for (;;)
      {
        label410:
        isRunningDownloadAds = false;
        paramString1.printStackTrace();
        continue;
        label466:
        j += 1;
        continue;
        setAdList_Native(new ArrayList());
        getAdList_Native().addAll(paramString2);
      }
      if (this.listener == null) {
        break label555;
      }
      this.listener.onLoadSuccess();
      return;
      if (this.preloadAdsNum <= 0) {
        break label539;
      }
      logPrint(TAG, "Yeahmobi Pubnative native load 1 feed!!!");
      firstCheckLoadAds();
      return;
      label539:
      if (this.listener == null) {
        break label555;
      }
      this.listener.onLoadSuccess();
    }
    if ((getAdList_Native() != null) && (getAdList_Native().size() > 0)) {
      if (paramString3.equals("all")) {
        if (this.preloadAdsNum > 0)
        {
          logPrint(TAG, "Yeahmobi pubnative load " + this.preloadAdsNum + " feed!!!");
          new LoadLinkTask(this.context, getListener(), this.preloadAdsNum).execute(new Integer[0]);
          return;
          logPrint(TAG, "parseYeahmobiPubnativeAdInfo---->list already exist!-new ads size->" + paramString1.size());
          paramString2 = new ArrayList();
          i = 0;
        }
      }
    }
    for (;;)
    {
      int j;
      if (i < paramString1.size())
      {
        AdInfo localAdInfo1 = (AdInfo)paramString1.get(i);
        int m = 0;
        j = 0;
        int k = m;
        if (j < getAdList_Native().size())
        {
          AdInfo localAdInfo2 = (AdInfo)getAdList_Native().get(j);
          if (!localAdInfo1.getPkgName().trim().equals(localAdInfo2.getPkgName().trim())) {
            break label466;
          }
          logPrint(TAG, "ad list--" + j + "-->exist!!!");
          k = 1;
          if ((!localAdInfo2.getLoadStatus().equals("succeed")) || (!isRightURL(localAdInfo2.getAdFinalLink()))) {
            break label410;
          }
          logPrint(TAG, "ad list--" + j + "-without preload->exist!!!");
          paramString2.add(localAdInfo2);
        }
        while (k == 0)
        {
          paramString2.add(localAdInfo1);
          break;
          logPrint(TAG, "ad list--" + j + "-need preload->exist!!!");
          paramString2.add(localAdInfo1);
        }
      }
      label555:
      return;
      i += 1;
    }
  }
  
  public void readAdsFromLocal()
  {
    for (;;)
    {
      int i;
      try
      {
        SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        int j = localSharedPreferences.getInt("preparelist_size", 0);
        logPrint(TAG, "Load the Ads from location --number is -->" + j);
        if (j <= 0) {
          break label569;
        }
        adPreparedList = new ArrayList();
        i = 0;
        if (i < j)
        {
          String str = localSharedPreferences.getString("preparelist_" + i + "_link", "");
          Object localObject = localSharedPreferences.getString("preparelist_" + i + "_pkg", "");
          Log.i(TAG, "LuckAd::download url is installed-" + PackageUtil.isPackageInstalled(this.context, (String)localObject) + "->" + (String)localObject);
          if ((str.equals("")) || (isAdsObsoleted(localSharedPreferences, 24, "preparelist_" + i + "_date")) || (PackageUtil.isPackageInstalled(this.context, (String)localObject))) {
            break label599;
          }
          localObject = new AdInfo("", new URI(str));
          ((AdInfo)localObject).setAdFinalLink(str);
          ((AdInfo)localObject).setConversionUrl(new URI(str));
          ((AdInfo)localObject).setLoadStatus("succeed");
          logPrint("Glispa Ads", "from local title is " + i + "-->" + localSharedPreferences.getString(new StringBuilder().append("preparelist_").append(i).append("_title").toString(), ""));
          ((AdInfo)localObject).setTitle(localSharedPreferences.getString("preparelist_" + i + "_title", ""));
          ((AdInfo)localObject).setContent(localSharedPreferences.getString("preparelist_" + i + "_content", ""));
          ((AdInfo)localObject).setIconUrl(localSharedPreferences.getString("preparelist_" + i + "_icon", ""));
          ((AdInfo)localObject).setImageUrl(localSharedPreferences.getString("preparelist_" + i + "_image", ""));
          adPreparedList.add(localObject);
        }
      }
      catch (Exception localException)
      {
        isRunningDownloadAds = false;
        localException.printStackTrace();
        return;
      }
      sizeOfAdPrepared = adPreparedList.size();
      if (sizeOfAdPrepared > 10) {
        sizeOfAdPrepared = 10;
      }
      label569:
      logPrint(TAG, "Load the Ads from location -final-number is -->" + sizeOfAdPrepared);
      return;
      label599:
      i += 1;
    }
  }
  
  public void resetAdList(int paramInt, String paramString1, String paramString2, boolean paramBoolean)
  {
    logPrint(TAG, "isUpdateAll--->" + paramBoolean);
    if (paramBoolean)
    {
      logPrint(TAG, "LuckAds::Load the Ads from remote server!!!");
      new LuckAdNew.AdLoadGlispaParseTaskUpdAll(this, this.context).execute(new Void[0]);
    }
    for (;;)
    {
      if (this.listener != null) {
        this.listener.onLoadSuccess();
      }
      return;
      if (CheckTime.isTimeUpAPI(this.context, paramInt, paramString1, paramString2))
      {
        logPrint(TAG, "LuckAds::Just reenter the program and update time is up!!!!");
        new LuckAdNew.AdLoadGlispaParseTaskByHours(this, this.context).execute(new Void[0]);
      }
      else
      {
        if ((getAdList_Native() == null) || (getAdList_Native().size() == 0))
        {
          if (CheckTime.isTimeUpAPI(this.context, 1, paramString1 + "_1", paramString2 + "_1"))
          {
            logPrint(TAG, "LuckAds::Load the Ads from location!!!");
            new LuckAdNew.AdLoadGlispaParseTaskByHours(this, this.context).execute(new Void[0]);
          }
          for (;;)
          {
            readAdsFromLocal();
            break;
            logPrint(TAG, "LuckAds::Load the Ads from location!!!---too frequentlly!!!");
            setAdList_Native(new ArrayList());
          }
        }
        logPrint(TAG, "LuckAds::Just reenter the program!!!!");
      }
    }
  }
  
  public void resetAdListAppia(int paramInt, String paramString1, String paramString2, boolean paramBoolean)
  {
    logPrint(TAG, "isUpdateAll--->" + paramBoolean);
    if (paramBoolean)
    {
      logPrint(TAG, "LuckAds::Load the Ads from remote server!!!");
      new LuckAdNew.AdLoadAppiaTask(this, this.context, "all").execute(new Void[0]);
    }
    for (;;)
    {
      if (this.listener != null) {
        this.listener.onLoadSuccess();
      }
      return;
      if (CheckTime.isTimeUpAPI(this.context, paramInt, paramString1, paramString2))
      {
        logPrint(TAG, "LuckAds::Just reenter the program and update time is up!!!!");
        new LuckAdNew.AdLoadAppiaTask(this, this.context, "hour").execute(new Void[0]);
      }
      else
      {
        if ((getAdList_Native() == null) || (getAdList_Native().size() == 0))
        {
          if (CheckTime.isTimeUpAPI(this.context, 1, paramString1 + "_1", paramString2 + "_1"))
          {
            logPrint(TAG, "LuckAds::Load the Ads from location!!!");
            new LuckAdNew.AdLoadAppiaTask(this, this.context, "hour").execute(new Void[0]);
          }
          for (;;)
          {
            readAdsFromLocal();
            break;
            logPrint(TAG, "LuckAds::Load the Ads from location!!!---too frequentlly!!!");
            setAdList_Native(new ArrayList());
          }
        }
        logPrint(TAG, "LuckAds::Just reenter the program!!!!");
      }
    }
  }
  
  public void resetAdListGliPub(int paramInt, String paramString1, String paramString2, boolean paramBoolean)
  {
    logPrint(TAG, "isUpdateAll--->" + paramBoolean);
    if (paramBoolean)
    {
      logPrint(TAG, "LuckAds::GP Load the Ads from remote server!!!");
      new LuckAdNew.AdLoadGlispaPubnativeTask(this, this.context, "all").execute(new Void[0]);
    }
    for (;;)
    {
      if (this.listener != null) {
        this.listener.onLoadSuccess();
      }
      return;
      if (CheckTime.isTimeUpAPI(this.context, paramInt, paramString1, paramString2))
      {
        logPrint(TAG, "LuckAds::GP Just reenter the program and update time is up!!!!");
        new LuckAdNew.AdLoadGlispaPubnativeTask(this, this.context, "hour").execute(new Void[0]);
      }
      else
      {
        if ((getAdList_Native() == null) || (getAdList_Native().size() == 0))
        {
          if (CheckTime.isTimeUpAPI(this.context, 1, paramString1 + "_1", paramString2 + "_1"))
          {
            logPrint(TAG, "LuckAds::GP Load the Ads from location!!!");
            new LuckAdNew.AdLoadGlispaPubnativeTask(this, this.context, "hour").execute(new Void[0]);
          }
          for (;;)
          {
            readAdsFromLocal();
            break;
            logPrint(TAG, "LuckAds::GP Load the Ads from location!!!---too frequentlly!!!");
            setAdList_Native(new ArrayList());
          }
        }
        logPrint(TAG, "LuckAds::GP Just reenter the program!!!!");
      }
    }
  }
  
  public void resetAdListMobCore(int paramInt, String paramString1, String paramString2, boolean paramBoolean)
  {
    logPrint(TAG, "isUpdateAll--->" + paramBoolean);
    if (paramBoolean)
    {
      logPrint(TAG, "LuckAds::Load the Ads from remote server!!!");
      new LuckAdNew.AdLoadMobCoreTask(this, this.context, "all").execute(new Void[0]);
    }
    for (;;)
    {
      if (this.listener != null) {
        this.listener.onLoadSuccess();
      }
      return;
      if (CheckTime.isTimeUpAPI(this.context, paramInt, paramString1, paramString2))
      {
        logPrint(TAG, "LuckAds::Just reenter the program and update time is up!!!!");
        new LuckAdNew.AdLoadMobCoreTask(this, this.context, "hour").execute(new Void[0]);
      }
      else
      {
        if ((getAdList_Native() == null) || (getAdList_Native().size() == 0))
        {
          if (CheckTime.isTimeUpAPI(this.context, 1, paramString1 + "_1", paramString2 + "_1"))
          {
            logPrint(TAG, "LuckAds::Load the Ads from location!!!");
            new LuckAdNew.AdLoadMobCoreTask(this, this.context, "hour").execute(new Void[0]);
          }
          for (;;)
          {
            readAdsFromLocal();
            break;
            logPrint(TAG, "LuckAds::Load the Ads from location!!!---too frequentlly!!!");
            setAdList_Native(new ArrayList());
          }
        }
        logPrint(TAG, "LuckAds::Just reenter the program!!!!");
      }
    }
  }
  
  public void resetAdListPubAppia(int paramInt, String paramString1, String paramString2, boolean paramBoolean)
  {
    logPrint(TAG, "isUpdateAll--->" + paramBoolean);
    if (paramBoolean)
    {
      logPrint(TAG, "LuckAds::GP Load the Ads from remote server!!!");
      new LuckAdNew.AdLoadPubAppiaTask(this, this.context, "all").execute(new Void[0]);
    }
    for (;;)
    {
      if (this.listener != null) {
        this.listener.onLoadSuccess();
      }
      return;
      if (CheckTime.isTimeUpAPI(this.context, paramInt, paramString1, paramString2))
      {
        logPrint(TAG, "LuckAds::GP Just reenter the program and update time is up!!!!");
        new LuckAdNew.AdLoadPubAppiaTask(this, this.context, "hour").execute(new Void[0]);
      }
      else
      {
        if ((getAdList_Native() == null) || (getAdList_Native().size() == 0))
        {
          if (CheckTime.isTimeUpAPI(this.context, 1, paramString1 + "_1", paramString2 + "_1"))
          {
            logPrint(TAG, "LuckAds::GP Load the Ads from location!!!");
            new LuckAdNew.AdLoadPubAppiaTask(this, this.context, "hour").execute(new Void[0]);
          }
          for (;;)
          {
            readAdsFromLocal();
            break;
            logPrint(TAG, "LuckAds::GP Load the Ads from location!!!---too frequentlly!!!");
            setAdList_Native(new ArrayList());
          }
        }
        logPrint(TAG, "LuckAds::GP Just reenter the program!!!!");
      }
    }
  }
  
  public void resetAdListPubMobCore(int paramInt, String paramString1, String paramString2, boolean paramBoolean)
  {
    logPrint(TAG, "isUpdateAll--->" + paramBoolean);
    if (paramBoolean)
    {
      logPrint(TAG, "LuckAds::GP Load the Ads from remote server!!!");
      new LuckAdNew.AdLoadPubMobCoreTask(this, this.context, "all").execute(new Void[0]);
    }
    for (;;)
    {
      if (this.listener != null) {
        this.listener.onLoadSuccess();
      }
      return;
      if (CheckTime.isTimeUpAPI(this.context, paramInt, paramString1, paramString2))
      {
        logPrint(TAG, "LuckAds::GP Just reenter the program and update time is up!!!!");
        new LuckAdNew.AdLoadPubMobCoreTask(this, this.context, "hour").execute(new Void[0]);
      }
      else
      {
        if ((getAdList_Native() == null) || (getAdList_Native().size() == 0))
        {
          if (CheckTime.isTimeUpAPI(this.context, 1, paramString1 + "_1", paramString2 + "_1"))
          {
            logPrint(TAG, "LuckAds::GP Load the Ads from location!!!");
            new LuckAdNew.AdLoadPubMobCoreTask(this, this.context, "hour").execute(new Void[0]);
          }
          for (;;)
          {
            readAdsFromLocal();
            break;
            logPrint(TAG, "LuckAds::GP Load the Ads from location!!!---too frequentlly!!!");
            setAdList_Native(new ArrayList());
          }
        }
        logPrint(TAG, "LuckAds::GP Just reenter the program!!!!");
      }
    }
  }
  
  public void resetAdListPubnative(int paramInt, String paramString1, String paramString2, boolean paramBoolean)
  {
    logPrint(TAG, "isUpdateAll--->" + paramBoolean);
    if (paramBoolean)
    {
      logPrint(TAG, "LuckAds::Load the Ads from remote server!!!");
      new LuckAdNew.AdLoadPubTaskUpdAll(this, this.context).execute(new Void[0]);
    }
    for (;;)
    {
      if (this.listener != null) {
        this.listener.onLoadSuccess();
      }
      return;
      if (CheckTime.isTimeUpAPI(this.context, paramInt, paramString1, paramString2))
      {
        logPrint(TAG, "LuckAds::Just reenter the program and update time is up!!!!");
        new LuckAdNew.AdLoadPubParseTaskByHours(this, this.context).execute(new Void[0]);
      }
      else
      {
        if ((getAdList_Native() == null) || (getAdList_Native().size() == 0))
        {
          if (CheckTime.isTimeUpAPI(this.context, 1, paramString1 + "_1", paramString2 + "_1"))
          {
            logPrint(TAG, "LuckAds::Load the Ads from location!!!");
            new LuckAdNew.AdLoadPubParseTaskByHours(this, this.context).execute(new Void[0]);
          }
          for (;;)
          {
            readAdsFromLocal();
            break;
            logPrint(TAG, "LuckAds::Load the Ads from location!!!---too frequentlly!!!");
            setAdList_Native(new ArrayList());
          }
        }
        logPrint(TAG, "LuckAds::Just reenter the program!!!!");
      }
    }
  }
  
  public void resetAdListYeaPub(int paramInt, String paramString1, String paramString2, boolean paramBoolean)
  {
    logPrint(TAG, "isUpdateAll--->" + paramBoolean);
    if (paramBoolean)
    {
      logPrint(TAG, "LuckAds::GP Load the Ads from remote server!!!");
      new LuckAdNew.AdLoadYeahmobiPubnativeTask(this, this.context, "all").execute(new Void[0]);
    }
    for (;;)
    {
      if (this.listener != null) {
        this.listener.onLoadSuccess();
      }
      return;
      if (CheckTime.isTimeUpAPI(this.context, paramInt, paramString1, paramString2))
      {
        logPrint(TAG, "LuckAds::GP Just reenter the program and update time is up!!!!");
        new LuckAdNew.AdLoadYeahmobiPubnativeTask(this, this.context, "hour").execute(new Void[0]);
      }
      else
      {
        if ((getAdList_Native() == null) || (getAdList_Native().size() == 0))
        {
          if (CheckTime.isTimeUpAPI(this.context, 1, paramString1 + "_1", paramString2 + "_1"))
          {
            logPrint(TAG, "LuckAds::GP Load the Ads from location!!!");
            new LuckAdNew.AdLoadYeahmobiPubnativeTask(this, this.context, "hour").execute(new Void[0]);
          }
          for (;;)
          {
            readAdsFromLocal();
            break;
            logPrint(TAG, "LuckAds::GP Load the Ads from location!!!---too frequentlly!!!");
            setAdList_Native(new ArrayList());
          }
        }
        logPrint(TAG, "LuckAds::GP Just reenter the program!!!!");
      }
    }
  }
  
  public void resetAdListYeahmobi(int paramInt, String paramString1, String paramString2, boolean paramBoolean)
  {
    logPrint(TAG, "isUpdateAll--->" + paramBoolean);
    if (paramBoolean)
    {
      logPrint(TAG, "LuckAds::Load the Ads from remote server!!!");
      new LuckAdNew.AdLoadYeahmobiTask(this, this.context, "all").execute(new Void[0]);
    }
    for (;;)
    {
      if (this.listener != null) {
        this.listener.onLoadSuccess();
      }
      return;
      if (CheckTime.isTimeUpAPI(this.context, paramInt, paramString1, paramString2))
      {
        logPrint(TAG, "LuckAds::Just reenter the program and update time is up!!!!");
        new LuckAdNew.AdLoadYeahmobiTask(this, this.context, "hour").execute(new Void[0]);
      }
      else
      {
        if ((getAdList_Native() == null) || (getAdList_Native().size() == 0))
        {
          if (CheckTime.isTimeUpAPI(this.context, 1, paramString1 + "_1", paramString2 + "_1"))
          {
            logPrint(TAG, "LuckAds::Load the Ads from location!!!");
            new LuckAdNew.AdLoadYeahmobiTask(this, this.context, "hour").execute(new Void[0]);
          }
          for (;;)
          {
            readAdsFromLocal();
            break;
            logPrint(TAG, "LuckAds::Load the Ads from location!!!---too frequentlly!!!");
            setAdList_Native(new ArrayList());
          }
        }
        logPrint(TAG, "LuckAds::Just reenter the program!!!!");
      }
    }
  }
  
  public void setAdList(List<AdInfo> paramList)
  {
    this.ad_list = paramList;
  }
  
  public void setAdList_Native(List<AdInfo> paramList)
  {
    this.ad_list_native = paramList;
  }
  
  public void setAd_list_wall(List<AdInfo> paramList)
  {
    this.ad_list_wall = paramList;
  }
  
  public void setExternalListener(ExternalListener paramExternalListener)
  {
    this.externalListener = paramExternalListener;
  }
  
  public void setIndexOfAdList(int paramInt)
  {
    indexOfAdList = paramInt;
  }
  
  public void setListener(IAdLoadListener paramIAdLoadListener)
  {
    this.listener = paramIAdLoadListener;
  }
  
  public void setOnListener(View.OnClickListener paramOnClickListener)
  {
    adsListner = paramOnClickListener;
  }
  
  public void setTitlebar_for_ad(LinearLayout paramLinearLayout)
  {
    titlebar_for_ad = paramLinearLayout;
  }
  
  public void setTitlebar_for_ad(LinearLayout paramLinearLayout, int paramInt)
  {
    titlebar_for_ad = paramLinearLayout;
    icon_rs = paramInt;
  }
  
  public void showNewAds(Activity paramActivity, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, AdInfo paramAdInfo)
  {
    try
    {
      new AlertDialog.Builder(activity).create();
      try
      {
        paramActivity = new AlertDialog.Builder(activity, 16973839).create();
        View localView = LayoutInflater.from(activity).inflate(paramInt1, null);
        String str1 = paramAdInfo.getImageUrl();
        if ((str1 != null) && (!str1.trim().equals("")))
        {
          localObject = (ImageView)localView.findViewById(paramInt3);
          Picasso.with(this.context).load(str1).placeholder(paramInt2).into((ImageView)localObject);
        }
        str1 = paramAdInfo.getIconUrl();
        if ((str1 != null) && (!str1.trim().equals("")))
        {
          localObject = (ImageView)localView.findViewById(paramInt7);
          Picasso.with(this.context).load(str1).placeholder(paramInt6).into((ImageView)localObject);
        }
        ((TextView)localView.findViewById(paramInt4)).setText(paramAdInfo.getTilte());
        ((TextView)localView.findViewById(paramInt5)).setText(paramAdInfo.getContent());
        ((ImageView)localView.findViewById(paramInt8)).setOnClickListener(new LuckAdNew.5(this, paramActivity));
        str1 = paramAdInfo.adFinalLink;
        Object localObject = paramAdInfo.getAdsOrigin();
        String str2 = paramAdInfo.getClick_record_url();
        boolean bool = paramAdInfo.isClicked();
        ((LinearLayout)localView.findViewById(R.id.ad_layout)).setOnClickListener(new LuckAdNew.6(this, str1, (String)localObject, bool, str2, paramActivity));
        localView.setOnClickListener(new LuckAdNew.7(this, paramActivity));
        paramActivity.show();
        paramActivity.getWindow().setContentView(localView);
        return;
      }
      catch (NoSuchMethodError paramActivity)
      {
        for (;;)
        {
          Log.e(TAG, "Older SDK, using old Builder");
          paramActivity = new AlertDialog.Builder(activity).create();
        }
      }
      return;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
      isRunningDownloadAds = false;
    }
  }
  
  public void showNewAds(Activity paramActivity, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, AdInfo paramAdInfo, int paramInt9)
  {
    try
    {
      new AlertDialog.Builder(activity).create();
      try
      {
        paramActivity = new AlertDialog.Builder(activity, 16973839).create();
        View localView = LayoutInflater.from(activity).inflate(paramInt1, null);
        String str1 = paramAdInfo.getImageUrl();
        if ((str1 != null) && (!str1.trim().equals("")))
        {
          localObject = (ImageView)localView.findViewById(paramInt3);
          Picasso.with(this.context).load(str1).placeholder(paramInt2).into((ImageView)localObject);
        }
        str1 = paramAdInfo.getIconUrl();
        if ((str1 != null) && (!str1.trim().equals("")))
        {
          localObject = (ImageView)localView.findViewById(paramInt7);
          Picasso.with(this.context).load(str1).placeholder(paramInt6).into((ImageView)localObject);
        }
        ((TextView)localView.findViewById(paramInt4)).setText(paramAdInfo.getTilte());
        ((TextView)localView.findViewById(paramInt5)).setText(paramAdInfo.getContent());
        ((ImageView)localView.findViewById(paramInt8)).setOnClickListener(new LuckAdNew.8(this, paramActivity));
        str1 = paramAdInfo.adFinalLink;
        Object localObject = paramAdInfo.getAdsOrigin();
        String str2 = paramAdInfo.getClick_record_url();
        boolean bool = paramAdInfo.isClicked();
        ((LinearLayout)localView.findViewById(R.id.ad_layout)).setOnClickListener(new LuckAdNew.9(this, str1, (String)localObject, bool, str2, paramInt9, paramActivity));
        localView.setOnClickListener(new LuckAdNew.10(this, paramActivity));
        paramActivity.show();
        paramActivity.getWindow().setContentView(localView);
        return;
      }
      catch (NoSuchMethodError paramActivity)
      {
        for (;;)
        {
          Log.e(TAG, "Older SDK, using old Builder");
          paramActivity = new AlertDialog.Builder(activity).create();
        }
      }
      return;
    }
    catch (Exception paramActivity)
    {
      paramActivity.printStackTrace();
      isRunningDownloadAds = false;
    }
  }
  
  public void updateGlispaListnerByStepNew()
  {
    adsListner = new LuckAdNew.2(this);
  }
  
  public void updateLuckAd(LinearLayout paramLinearLayout, int paramInt)
  {
    Object localObject = "";
    try
    {
      str1 = Locale.getDefault().getCountry();
      localObject = str1;
      logPrint(TAG, "code::" + str1);
      localObject = str1;
    }
    catch (Exception localException1)
    {
      do
      {
        for (;;)
        {
          String str1;
          int i;
          localException1.printStackTrace();
          continue;
          if (localException1.equals("G"))
          {
            i = 0;
            continue;
            if (localException1.equals("P"))
            {
              i = 1;
              continue;
              if (localException1.equals("I"))
              {
                i = 2;
                continue;
                if (localException1.equals("Y"))
                {
                  i = 3;
                  continue;
                  if (localException1.equals("M"))
                  {
                    i = 4;
                    continue;
                    if (localException1.equals("F"))
                    {
                      i = 5;
                      continue;
                      if (localException1.equals("FG"))
                      {
                        i = 6;
                        continue;
                        if (localException1.equals("FP"))
                        {
                          i = 7;
                          continue;
                          if (localException1.equals("FY"))
                          {
                            i = 8;
                            continue;
                            if (localException1.equals("FPY"))
                            {
                              i = 9;
                              continue;
                              if (localException1.equals("FPM"))
                              {
                                i = 10;
                                continue;
                                if (localException1.equals("GP"))
                                {
                                  i = 11;
                                  continue;
                                  if (localException1.equals("PI"))
                                  {
                                    i = 12;
                                    continue;
                                    if (localException1.equals("PY"))
                                    {
                                      i = 13;
                                      continue;
                                      if (localException1.equals("PM")) {
                                        i = 14;
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
                  }
                }
              }
            }
          }
        }
        try
        {
          String str2 = Locale.getDefault().getCountry();
          localObject = str2;
        }
        catch (Exception localException2)
        {
          for (;;)
          {
            localException2.printStackTrace();
          }
          if (!this.openWindowType.equals("N")) {
            continue;
          }
          get(activity, activity).updateGlispaListnerByStepNew();
          paramLinearLayout.setOnClickListener(get(activity, activity).getAdsListner());
          return;
          get(activity, activity).updateNewAdListner(activity, R.layout.ad_indirect, R.drawable.ad_icon1, R.id.pop_screen_ad_bg, R.id.adTitle, R.id.adBody, paramInt, R.id.adIcon, R.id.closeIcon);
          paramLinearLayout.setOnClickListener(get(activity, activity).getAdsListner());
          return;
        }
        if (((String)localObject).toUpperCase().equals("CN"))
        {
          get(activity, activity).updateNewAdListner(activity, R.layout.ad_indirect, R.drawable.ad_icon1, R.id.pop_screen_ad_bg, R.id.adTitle, R.id.adBody, paramInt, R.id.adIcon, R.id.closeIcon);
          paramLinearLayout.setOnClickListener(get(activity, activity).getAdsListner());
          return;
        }
      } while (PackageUtil.isPackageInstalled(this.context, "com.facebook.katana"));
      get(activity, activity).updateNewAdListner(activity, R.layout.ad_indirect, R.drawable.ad_icon1, R.id.pop_screen_ad_bg, R.id.adTitle, R.id.adBody, paramInt, R.id.adIcon, R.id.closeIcon);
      paramLinearLayout.setOnClickListener(get(activity, activity).getAdsListner());
      return;
    }
    str1 = getAdsPlan();
    i = -1;
    switch (str1.hashCode())
    {
    default: 
      switch (i)
      {
      default: 
        get(activity, activity).updateNewAdListner(activity, R.layout.ad_indirect, R.drawable.ad_icon1, R.id.pop_screen_ad_bg, R.id.adTitle, R.id.adBody, paramInt, R.id.adIcon, R.id.closeIcon);
        paramLinearLayout.setOnClickListener(get(activity, activity).getAdsListner());
        return;
      }
      break;
    }
    if (((String)localObject).toUpperCase().equals("CN"))
    {
      get(activity, activity).updateNewAdListner(activity, R.layout.ad_indirect, R.drawable.ad_icon1, R.id.pop_screen_ad_bg, R.id.adTitle, R.id.adBody, paramInt, R.id.adIcon, R.id.closeIcon);
      paramLinearLayout.setOnClickListener(get(activity, activity).getAdsListner());
      return;
    }
    if (this.openWindowType.equals("N"))
    {
      get(activity, activity).updateGlispaListnerByStepNew();
      paramLinearLayout.setOnClickListener(get(activity, activity).getAdsListner());
      return;
    }
    get(activity, activity).updateNewAdListner(activity, R.layout.ad_indirect, R.drawable.ad_icon1, R.id.pop_screen_ad_bg, R.id.adTitle, R.id.adBody, paramInt, R.id.adIcon, R.id.closeIcon);
    paramLinearLayout.setOnClickListener(get(activity, activity).getAdsListner());
  }
  
  public void updateNewAdListner(Activity paramActivity, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8)
  {
    adsListner = new LuckAdNew.4(this, paramActivity, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8);
  }
}
