package com.livio.android.alhu;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import com.livio.android.util.LivioConnectUtl;
import com.livio.android.util.LivioLog;
import com.livio.android.util.a.a.a;
import com.livio.android.util.artwork.ArtworkReadyEvent;
import com.livio.android.util.artwork.LivioArtwork;
import com.livio.cir.LivioConnect;
import com.livio.cir.LivioConnectUtilities;
import com.livio.cir.LivioPacket;
import com.livio.cir.LivioPacketFactory;
import com.livio.cir.SystemAttributes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.http.client.methods.HttpGet;

public class Alhu
  implements a.a, ArtworkReadyEvent
{
  private static final String ANDROID_PACKAGE_NAME = "androidPackageName";
  private static final String APP_KEY = "key";
  private static final String APP_NAME = "name";
  private static final String IMAGE_URL = "imgUrl";
  private static final String LIVIO_ALHU = "livioalhu";
  private static final String LIVIO_ALHU_JSON = "livioalhujson";
  private static final String LIVIO_ALHU_REQUEST = "livioalhurequest";
  private static final String LIVIO_ALHU_TIME_STAMP = "livioalhutimestamp";
  private static final String PLAYSTORE_LINK = "android";
  private static final long REFRESH_APP_LIST_TIME = 3600000L;
  private static final String TAG = "ALHU";
  LivioPacket alhu_packet;
  int appCategory;
  private ArrayList<AppListItem> appList = new ArrayList();
  int appLocation;
  private Context context;
  String hardwareUUID;
  int maxListSize;
  int offset = 0;
  private com.livio.cir.a sender;
  private boolean wantsArtwork = true;
  
  public Alhu(LivioPacket paramLivioPacket, Context paramContext, com.livio.cir.a paramA)
  {
    this.hardwareUUID = LivioConnectUtl.stringToHex(new String(paramLivioPacket.getPayloadForSubtag(1)));
    this.maxListSize = LivioPacketFactory.decodeBaseTen(new String(paramLivioPacket.getPayloadForSubtag(2)));
    this.offset = LivioPacketFactory.decodeBaseTen(new String(paramLivioPacket.getPayloadForSubtag(3)));
    this.wantsArtwork = LivioConnectUtilities.charArrayToBool(paramLivioPacket.getPayloadForSubtag(4));
    this.appCategory = paramLivioPacket.getPayloadForSubtag(5)[0];
    this.appLocation = paramLivioPacket.getPayloadForSubtag(6)[0];
    LivioConnect.getInstance().getSystemAttributes().setSystemAttribute(3, paramLivioPacket.getPayloadForSubtag(7)[0]);
    LivioConnect.getInstance().setMaxPacketLength(LivioPacketFactory.decodeBaseTen(new String(paramLivioPacket.getPayloadForSubtag(8))));
    this.alhu_packet = paramLivioPacket;
    this.context = paramContext;
    this.sender = paramA;
  }
  
  private boolean doesPackageExists(PackageManager paramPackageManager, String paramString)
  {
    if ((paramString == null) || (paramString.equals(""))) {
      return false;
    }
    paramPackageManager = paramPackageManager.getInstalledApplications(0).iterator();
    ApplicationInfo localApplicationInfo;
    do
    {
      if (!paramPackageManager.hasNext()) {
        return false;
      }
      localApplicationInfo = (ApplicationInfo)paramPackageManager.next();
      LivioLog.d("ALHU", "Looking at " + localApplicationInfo.packageName);
    } while (!localApplicationInfo.packageName.equalsIgnoreCase(paramString));
    LivioLog.d("ALHU", "Package " + paramString + " is available");
    return true;
  }
  
  private String getAlhuList(Context paramContext)
  {
    return paramContext.getSharedPreferences("livioalhu", 0).getString("livioalhujson", null);
  }
  
  private Long getAlhuListTimeStamp(Context paramContext)
  {
    return Long.valueOf(paramContext.getSharedPreferences("livioalhu", 0).getLong("livioalhutimestamp", 0L));
  }
  
  private String getAlhuRequest(Context paramContext)
  {
    return paramContext.getSharedPreferences("livioalhu", 0).getString("livioalhurequest", "");
  }
  
  public static Intent getLaunchIntent(PackageManager paramPackageManager, AppListItem paramAppListItem)
  {
    if (paramAppListItem.getLaunchIntent() != null) {
      return paramAppListItem.getLaunchIntent();
    }
    Object localObject = paramAppListItem.getPackageName();
    Intent localIntent = null;
    if (localObject != null)
    {
      LivioLog.d("ALHU", "Attempting to find launch intent by package name");
      localIntent = getLaunchIntent(paramPackageManager, paramAppListItem.getPackageName());
    }
    String str;
    List localList;
    int i;
    if (localIntent == null)
    {
      str = paramAppListItem.getAppName();
      LivioLog.d("ALHU", "Attempting to find launch intent by app name: " + paramAppListItem.getAppName());
      localList = paramPackageManager.getInstalledPackages(0);
      i = 0;
    }
    for (;;)
    {
      if (i >= localList.size()) {}
      for (;;)
      {
        paramAppListItem.setLaunchIntent(localIntent);
        return localIntent;
        localObject = (PackageInfo)localList.get(i);
        if ((((PackageInfo)localObject).versionName == null) || (!((PackageInfo)localObject).applicationInfo.loadLabel(paramPackageManager).toString().toLowerCase(Locale.US).contains(str.toLowerCase(Locale.US)))) {
          break;
        }
        localIntent = getLaunchIntent(paramPackageManager, ((PackageInfo)localObject).packageName);
        paramAppListItem.setPackageName(((PackageInfo)localObject).packageName);
      }
      i += 1;
    }
  }
  
  public static Intent getLaunchIntent(PackageManager paramPackageManager, String paramString)
  {
    if ((paramString == null) || (paramPackageManager == null)) {
      paramPackageManager = null;
    }
    do
    {
      return paramPackageManager;
      paramString = paramPackageManager.getLaunchIntentForPackage(paramString);
      paramPackageManager = paramString;
    } while (paramString == null);
    paramString.addFlags(268435456);
    paramString.putExtra("com.livio.launch", "LIVIO_INTENT_STRING");
    paramString.putExtra("com.livio.launch.timestamp", System.currentTimeMillis());
    return paramString;
  }
  
  /* Error */
  private void parseJSONAppList(String paramString)
  {
    // Byte code:
    //   0: new 335	org/json/JSONArray
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 336	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   8: astore 5
    //   10: iconst_0
    //   11: istore_2
    //   12: iload_2
    //   13: aload 5
    //   15: invokevirtual 339	org/json/JSONArray:length	()I
    //   18: if_icmpge +11 -> 29
    //   21: iload_2
    //   22: aload_0
    //   23: getfield 101	com/livio/android/alhu/Alhu:maxListSize	I
    //   26: if_icmplt +13 -> 39
    //   29: aload_0
    //   30: aload_0
    //   31: getfield 135	com/livio/android/alhu/Alhu:context	Landroid/content/Context;
    //   34: aload_1
    //   35: invokespecial 343	com/livio/android/alhu/Alhu:setAlhuList	(Landroid/content/Context;Ljava/lang/String;)V
    //   38: return
    //   39: aload 5
    //   41: iload_2
    //   42: invokevirtual 347	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   45: astore 6
    //   47: ldc 43
    //   49: aload 5
    //   51: iload_2
    //   52: invokevirtual 347	org/json/JSONArray:getJSONObject	(I)Lorg/json/JSONObject;
    //   55: invokevirtual 350	org/json/JSONObject:toString	()Ljava/lang/String;
    //   58: invokestatic 353	com/livio/android/util/LivioLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   61: aload 6
    //   63: ldc 21
    //   65: invokevirtual 356	org/json/JSONObject:has	(Ljava/lang/String;)Z
    //   68: ifeq +123 -> 191
    //   71: aload 6
    //   73: ldc 21
    //   75: invokevirtual 358	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   78: invokevirtual 359	java/lang/String:length	()I
    //   81: ifle +110 -> 191
    //   84: new 361	com/livio/android/util/artwork/LivioArtwork
    //   87: dup
    //   88: aload 6
    //   90: ldc 21
    //   92: invokevirtual 358	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   95: invokespecial 362	com/livio/android/util/artwork/LivioArtwork:<init>	(Ljava/lang/String;)V
    //   98: astore 4
    //   100: aload 6
    //   102: ldc 15
    //   104: invokevirtual 358	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   107: astore 7
    //   109: aload 7
    //   111: aload 7
    //   113: invokevirtual 359	java/lang/String:length	()I
    //   116: iconst_4
    //   117: isub
    //   118: invokevirtual 366	java/lang/String:substring	(I)Ljava/lang/String;
    //   121: bipush 16
    //   123: invokestatic 372	java/lang/Integer:parseInt	(Ljava/lang/String;I)I
    //   126: istore_3
    //   127: aload 4
    //   129: iconst_4
    //   130: iload_3
    //   131: invokevirtual 375	com/livio/android/util/artwork/LivioArtwork:setImageId	(II)V
    //   134: aload_0
    //   135: aload 6
    //   137: ldc 12
    //   139: invokevirtual 378	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   142: invokespecial 381	com/livio/android/alhu/Alhu:shouldAddToList	(Ljava/lang/String;)Z
    //   145: ifeq +121 -> 266
    //   148: aload_0
    //   149: getfield 74	com/livio/android/alhu/Alhu:appList	Ljava/util/ArrayList;
    //   152: new 233	com/livio/android/alhu/AppListItem
    //   155: dup
    //   156: aload 6
    //   158: ldc 18
    //   160: invokevirtual 358	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   163: iload_3
    //   164: aload 6
    //   166: ldc 12
    //   168: invokevirtual 378	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   171: aload 4
    //   173: iconst_1
    //   174: aload 6
    //   176: ldc 36
    //   178: invokevirtual 378	org/json/JSONObject:optString	(Ljava/lang/String;)Ljava/lang/String;
    //   181: invokespecial 384	com/livio/android/alhu/AppListItem:<init>	(Ljava/lang/String;ILjava/lang/String;Lcom/livio/android/util/artwork/LivioArtwork;ILjava/lang/String;)V
    //   184: invokevirtual 387	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   187: pop
    //   188: goto +78 -> 266
    //   191: ldc 43
    //   193: ldc_w 389
    //   196: invokestatic 353	com/livio/android/util/LivioLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   199: new 361	com/livio/android/util/artwork/LivioArtwork
    //   202: dup
    //   203: ldc_w 391
    //   206: invokespecial 362	com/livio/android/util/artwork/LivioArtwork:<init>	(Ljava/lang/String;)V
    //   209: astore 4
    //   211: goto -111 -> 100
    //   214: astore 4
    //   216: ldc 43
    //   218: aload 4
    //   220: invokevirtual 394	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   223: invokestatic 397	com/livio/android/util/LivioLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   226: goto +40 -> 266
    //   229: astore_1
    //   230: ldc 43
    //   232: aload_1
    //   233: invokevirtual 398	org/json/JSONException:toString	()Ljava/lang/String;
    //   236: invokestatic 397	com/livio/android/util/LivioLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   239: aload_0
    //   240: getfield 137	com/livio/android/alhu/Alhu:sender	Lcom/livio/cir/a;
    //   243: new 400	com/livio/cir/PrimaryTag
    //   246: dup
    //   247: bipush 64
    //   249: iconst_1
    //   250: iconst_5
    //   251: invokespecial 403	com/livio/cir/PrimaryTag:<init>	(IZI)V
    //   254: bipush 13
    //   256: invokestatic 407	com/livio/cir/LivioPacketFactory:makeNak	(Lcom/livio/cir/PrimaryTag;I)[C
    //   259: invokeinterface 412 2 0
    //   264: pop
    //   265: return
    //   266: iload_2
    //   267: iconst_1
    //   268: iadd
    //   269: istore_2
    //   270: goto -258 -> 12
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	273	0	this	Alhu
    //   0	273	1	paramString	String
    //   11	259	2	i	int
    //   126	38	3	j	int
    //   98	112	4	localLivioArtwork	LivioArtwork
    //   214	5	4	localException	Exception
    //   8	42	5	localJSONArray	org.json.JSONArray
    //   45	130	6	localJSONObject	org.json.JSONObject
    //   107	5	7	str	String
    // Exception table:
    //   from	to	target	type
    //   39	100	214	java/lang/Exception
    //   100	188	214	java/lang/Exception
    //   191	211	214	java/lang/Exception
    //   0	10	229	org/json/JSONException
    //   12	29	229	org/json/JSONException
    //   29	38	229	org/json/JSONException
    //   39	100	229	org/json/JSONException
    //   100	188	229	org/json/JSONException
    //   191	211	229	org/json/JSONException
    //   216	226	229	org/json/JSONException
  }
  
  private void setAlhuList(Context paramContext, String paramString)
  {
    if ((paramString != null) && (paramContext != null))
    {
      paramContext = paramContext.getSharedPreferences("livioalhu", 0).edit();
      paramContext.putString("livioalhujson", paramString);
      paramContext.putLong("livioalhutimestamp", System.currentTimeMillis());
      paramContext.commit();
    }
  }
  
  private void setAlhuRequest(Context paramContext, String paramString)
  {
    if ((paramString != null) && (paramContext != null))
    {
      paramContext = paramContext.getSharedPreferences("livioalhu", 0).edit();
      paramContext.putString("livioalhurequest", paramString);
      paramContext.commit();
    }
  }
  
  private boolean shouldAddToList(String paramString)
  {
    boolean bool = true;
    LivioLog.d("ALHU", "Looking for: " + paramString);
    switch (this.appLocation)
    {
    default: 
      LivioLog.w("ALHU", "Did not hit a known case for query apps! Received: " + this.appLocation);
    case 3: 
    case 2: 
      do
      {
        bool = false;
        return bool;
      } while (doesPackageExists(this.context.getPackageManager(), paramString));
      return true;
    }
    return doesPackageExists(this.context.getPackageManager(), paramString);
  }
  
  public void artworkReady(int paramInt1, int paramInt2)
  {
    LivioLog.i("ALHU", "Artwork ready for: " + paramInt1 + " - " + paramInt2);
    this.sender.a(getAppItem(paramInt2).getAppListItemPacket());
  }
  
  public void artworkReady(String paramString, int paramInt)
  {
    LivioLog.e("ALHU", "Artwork ready for wrong event");
  }
  
  public void downloadAppList()
  {
    Object localObject = getAlhuList(this.context);
    String str = "https://wopr.livioconnect.com/api/v1/filter/applications.json?platform=android&hardwareUID=" + this.hardwareUUID + "&category=" + this.appCategory + "&access_token=b569120ed753d3a8c28d02551f6273b8e8cb3720ce37a19a";
    if ((localObject == null) || (System.currentTimeMillis() - getAlhuListTimeStamp(this.context).longValue() > 3600000L) || (!getAlhuRequest(this.context).equalsIgnoreCase(str)))
    {
      setAlhuRequest(this.context, str);
      LivioLog.w("ALHU", "Request: " + str);
      localObject = new HttpGet(str);
      new com.livio.android.util.a.a(this).execute(new Object[] { localObject });
      return;
    }
    httpCallComplete((String)localObject);
  }
  
  public AppListItem getAppItem(int paramInt)
  {
    Iterator localIterator = this.appList.iterator();
    AppListItem localAppListItem;
    do
    {
      if (!localIterator.hasNext()) {
        return null;
      }
      localAppListItem = (AppListItem)localIterator.next();
      LivioLog.e("ALHU", "Looking for: " + paramInt + " Found: " + localAppListItem.getAppId());
    } while (localAppListItem.getAppId() != paramInt);
    LivioLog.d("ALHU", "Returning app item: " + paramInt);
    return localAppListItem;
  }
  
  public int getAppLocationToBeQuerried()
  {
    return this.appLocation;
  }
  
  public Bitmap getImageForAppID(int paramInt)
  {
    AppListItem localAppListItem = getAppItem(paramInt);
    if (localAppListItem != null) {
      return localAppListItem.getImage().getImage(this);
    }
    return null;
  }
  
  public void httpCallComplete(String paramString)
  {
    int i;
    if (paramString != null)
    {
      parseJSONAppList(paramString);
      if ((this.appList == null) || (this.appList.size() <= 0)) {
        LivioLog.e("ALHU", "There was an error creating the app list for requested query");
      }
      this.sender.a(LivioPacketFactory.queryAppsACK(this.appList.size(), 0));
      i = this.offset;
    }
    for (;;)
    {
      if ((i >= this.appList.size()) || (i >= this.maxListSize)) {
        return;
      }
      this.sender.a(((AppListItem)this.appList.get(i)).getAppListItemPacket());
      i += 1;
    }
  }
  
  public void httpFailure(int paramInt)
  {
    LivioLog.e("ALHU", "There was an error in the HTTP request. Trying to load old list");
    String str = getAlhuList(this.context);
    if (str != null)
    {
      httpCallComplete(str);
      return;
    }
    LivioLog.w("ALHU", "Stored list was also null, nothing more can be done. Please try ALHU request again");
    this.sender.a(LivioPacketFactory.makeNak(this.alhu_packet.getPrimaryTag(), 7));
  }
  
  public boolean wantsArtwork()
  {
    return this.wantsArtwork;
  }
}
