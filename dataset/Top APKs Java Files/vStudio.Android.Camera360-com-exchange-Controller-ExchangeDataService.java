package com.exchange.Controller;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.exchange.Model.AdvertiserConfig;
import com.exchange.Public.DeviceManager;
import com.exchange.Public.ExchangeConstants;
import com.exchange.Public.LocationAgent;
import com.exchange.Public.Network;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExchangeDataService
{
  public static Context context;
  public static int curIndex = 0;
  public static List<AdvertiserConfig> mAdvertisers = null;
  static int minimal_ad_count = 6;
  
  public ExchangeDataService() {}
  
  private static String[] NetworkAccessMode(Context paramContext)
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "Unknown";
    arrayOfString[1] = "Unknown";
    if (paramContext.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", paramContext.getPackageName()) != 0) {
      arrayOfString[0] = "Unknown";
    }
    do
    {
      return arrayOfString;
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (paramContext == null)
      {
        arrayOfString[0] = "Unknown";
        return arrayOfString;
      }
      if (paramContext.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED)
      {
        arrayOfString[0] = "Wi-Fi";
        return arrayOfString;
      }
      paramContext = paramContext.getNetworkInfo(0);
    } while (paramContext.getState() != NetworkInfo.State.CONNECTED);
    arrayOfString[0] = "2G/3G";
    arrayOfString[1] = paramContext.getSubtypeName();
    return arrayOfString;
  }
  
  private static ArrayList<AdvertiserConfig> convertToAdvertisers(JSONObject paramJSONObject)
  {
    boolean bool = true;
    ArrayList localArrayList = new ArrayList();
    if (paramJSONObject == null)
    {
      Log.e(ExchangeConstants.LOG_TAG, "failed requesting");
      return localArrayList;
    }
    for (;;)
    {
      try
      {
        String str1 = paramJSONObject.getString("status");
        if (paramJSONObject.has("filter"))
        {
          if (!paramJSONObject.getString("filter").equals("1")) {
            break label171;
          }
          ExchangeConstants.filterInstalledApp = bool;
        }
        if (paramJSONObject.has("show_size"))
        {
          String str2 = paramJSONObject.getString("show_size");
          if (str2 != null)
          {
            if (!"1".equals(str2)) {
              continue;
            }
            ExchangeConstants.show_size = true;
          }
        }
        if ("1".equals(str1))
        {
          paramJSONObject = paramJSONObject.getJSONArray("promoters");
          int i = 0;
          if (i >= paramJSONObject.length()) {
            break;
          }
          localArrayList.add(getAdvertiserFromJson((JSONObject)paramJSONObject.get(i)));
          i += 1;
          continue;
          ExchangeConstants.show_size = false;
          continue;
        }
        Log.e(ExchangeConstants.LOG_TAG, "failed requesting");
      }
      catch (JSONException paramJSONObject)
      {
        paramJSONObject.printStackTrace();
        return localArrayList;
      }
      return localArrayList;
      label171:
      bool = false;
    }
  }
  
  public static void filter()
  {
    if ((ExchangeConstants.REQUEST_NUMBER <= minimal_ad_count) || (mAdvertisers == null) || (mAdvertisers.size() <= minimal_ad_count)) {
      return;
    }
    Set localSet = DeviceManager.getInstalledPackages(context);
    StringBuilder localStringBuilder = new StringBuilder("");
    int j = 0;
    int i = mAdvertisers.size() - 1;
    for (;;)
    {
      if (i < 0) {}
      while (mAdvertisers.size() <= minimal_ad_count)
      {
        Log.i(ExchangeConstants.LOG_TAG, j + " requested apps are filtered, including:" + localStringBuilder.toString());
        return;
      }
      AdvertiserConfig localAdvertiserConfig = (AdvertiserConfig)mAdvertisers.get(i);
      int k = j;
      if (localSet.contains(localAdvertiserConfig.packageName))
      {
        localStringBuilder.append(localAdvertiserConfig.packageName + ",");
        k = j + 1;
        mAdvertisers.remove(i);
      }
      i -= 1;
      j = k;
    }
  }
  
  public static AdvertiserConfig getAd(int paramInt)
  {
    if (hasData()) {
      return (AdvertiserConfig)mAdvertisers.get(paramInt);
    }
    return null;
  }
  
  static AdvertiserConfig getAdvertiserFromJson(JSONObject paramJSONObject)
  {
    localAdvertiserConfig = new AdvertiserConfig();
    try
    {
      if (paramJSONObject.has("title")) {
        localAdvertiserConfig.adName = paramJSONObject.getString("title");
      }
      if (paramJSONObject.has("ad_words")) {
        localAdvertiserConfig.adDescription = paramJSONObject.getString("ad_words");
      }
      if (paramJSONObject.has("description")) {
        localAdvertiserConfig.adDetail = paramJSONObject.getString("description");
      }
      if (paramJSONObject.has("promoter")) {
        localAdvertiserConfig.appkey = paramJSONObject.getString("promoter");
      }
      if (paramJSONObject.has("category")) {
        localAdvertiserConfig.category = paramJSONObject.getString("category");
      }
      if (paramJSONObject.has("size")) {
        localAdvertiserConfig.fileSize = paramJSONObject.getLong("size");
      }
      String str;
      if (paramJSONObject.has("url"))
      {
        str = paramJSONObject.getString("url");
        if (!str.toLowerCase().contains("http")) {
          break label261;
        }
      }
      for (;;)
      {
        localAdvertiserConfig.apk = str;
        if (paramJSONObject.has("provider")) {
          localAdvertiserConfig.provider = paramJSONObject.getString("provider");
        }
        if (paramJSONObject.has("app_package_name")) {
          localAdvertiserConfig.packageName = paramJSONObject.getString("app_package_name");
        }
        if (paramJSONObject.has("landing_type")) {
          localAdvertiserConfig.landingType = paramJSONObject.getInt("landing_type");
        }
        if (!paramJSONObject.has("icon")) {
          break;
        }
        localAdvertiserConfig.adIconUrl = (ExchangeConstants.BASE_URL + paramJSONObject.getString("icon"));
        return localAdvertiserConfig;
        label261:
        str = ExchangeConstants.BASE_URL + str;
      }
      return localAdvertiserConfig;
    }
    catch (JSONException paramJSONObject)
    {
      paramJSONObject.printStackTrace();
    }
  }
  
  public static AdvertiserConfig getCurAd()
  {
    if (hasData()) {
      return (AdvertiserConfig)mAdvertisers.get(curIndex);
    }
    return null;
  }
  
  private static String getDateTime()
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
  }
  
  public static List<AdvertiserConfig> getExampleAds(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    switch (paramInt)
    {
    }
    for (;;)
    {
      mAdvertisers = localArrayList;
      return localArrayList;
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[0], ExchangeConstants.des[0], ExchangeConstants.detailDes[0], 160000L, "1.1", ExchangeConstants.appIcon[0]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[1], ExchangeConstants.des[1], ExchangeConstants.detailDes[1], 160000L, "1.1", ExchangeConstants.appIcon[1]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[2], ExchangeConstants.des[2], ExchangeConstants.detailDes[2], 160000L, "1.1", ExchangeConstants.appIcon[2]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[3], ExchangeConstants.des[3], ExchangeConstants.detailDes[3], 160000L, "1.1", ExchangeConstants.appIcon[3]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[4], ExchangeConstants.des[4], ExchangeConstants.detailDes[4], 160000L, "1.1", ExchangeConstants.appIcon[4]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[5], ExchangeConstants.des[5], ExchangeConstants.detailDes[5], 160000L, "1.1", ExchangeConstants.appIcon[5]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[6], ExchangeConstants.des[6], ExchangeConstants.detailDes[6], 160000L, "1.1", ExchangeConstants.appIcon[6]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[7], ExchangeConstants.des[7], ExchangeConstants.detailDes[7], 160000L, "1.1", ExchangeConstants.appIcon[7]));
      continue;
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[1], ExchangeConstants.des[1], ExchangeConstants.detailDes[1], 160000L, "1.1", ExchangeConstants.appIcon[1]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[2], ExchangeConstants.des[2], ExchangeConstants.detailDes[2], 160000L, "1.1", ExchangeConstants.appIcon[2]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[0], ExchangeConstants.des[0], ExchangeConstants.detailDes[0], 160000L, "1.1", ExchangeConstants.appIcon[0]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[7], ExchangeConstants.des[7], ExchangeConstants.detailDes[7], 160000L, "1.1", ExchangeConstants.appIcon[7]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[6], ExchangeConstants.des[6], ExchangeConstants.detailDes[6], 160000L, "1.1", ExchangeConstants.appIcon[6]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[5], ExchangeConstants.des[5], ExchangeConstants.detailDes[5], 160000L, "1.1", ExchangeConstants.appIcon[5]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[4], ExchangeConstants.des[4], ExchangeConstants.detailDes[4], 160000L, "1.1", ExchangeConstants.appIcon[4]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[3], ExchangeConstants.des[3], ExchangeConstants.detailDes[3], 160000L, "1.1", ExchangeConstants.appIcon[3]));
      continue;
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[2], ExchangeConstants.des[2], ExchangeConstants.detailDes[2], 160000L, "1.1", ExchangeConstants.appIcon[2]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[4], ExchangeConstants.des[4], ExchangeConstants.detailDes[4], 160000L, "1.1", ExchangeConstants.appIcon[4]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[1], ExchangeConstants.des[1], ExchangeConstants.detailDes[1], 160000L, "1.1", ExchangeConstants.appIcon[1]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[3], ExchangeConstants.des[3], ExchangeConstants.detailDes[3], 160000L, "1.1", ExchangeConstants.appIcon[3]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[6], ExchangeConstants.des[6], ExchangeConstants.detailDes[6], 160000L, "1.1", ExchangeConstants.appIcon[6]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[5], ExchangeConstants.des[5], ExchangeConstants.detailDes[5], 160000L, "1.1", ExchangeConstants.appIcon[5]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[7], ExchangeConstants.des[7], ExchangeConstants.detailDes[7], 160000L, "1.1", ExchangeConstants.appIcon[7]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[0], ExchangeConstants.des[0], ExchangeConstants.detailDes[0], 160000L, "1.1", ExchangeConstants.appIcon[0]));
      continue;
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[3], ExchangeConstants.des[3], ExchangeConstants.detailDes[3], 160000L, "1.1", ExchangeConstants.appIcon[3]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[1], ExchangeConstants.des[1], ExchangeConstants.detailDes[1], 160000L, "1.1", ExchangeConstants.appIcon[1]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[0], ExchangeConstants.des[0], ExchangeConstants.detailDes[0], 160000L, "1.1", ExchangeConstants.appIcon[0]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[5], ExchangeConstants.des[5], ExchangeConstants.detailDes[5], 160000L, "1.1", ExchangeConstants.appIcon[5]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[4], ExchangeConstants.des[4], ExchangeConstants.detailDes[4], 160000L, "1.1", ExchangeConstants.appIcon[4]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[2], ExchangeConstants.des[2], ExchangeConstants.detailDes[2], 160000L, "1.1", ExchangeConstants.appIcon[2]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[6], ExchangeConstants.des[6], ExchangeConstants.detailDes[6], 160000L, "1.1", ExchangeConstants.appIcon[6]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[7], ExchangeConstants.des[7], ExchangeConstants.detailDes[7], 160000L, "1.1", ExchangeConstants.appIcon[7]));
      continue;
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[4], ExchangeConstants.des[4], ExchangeConstants.detailDes[4], 160000L, "1.1", ExchangeConstants.appIcon[4]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[3], ExchangeConstants.des[3], ExchangeConstants.detailDes[3], 160000L, "1.1", ExchangeConstants.appIcon[3]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[2], ExchangeConstants.des[2], ExchangeConstants.detailDes[2], 160000L, "1.1", ExchangeConstants.appIcon[2]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[1], ExchangeConstants.des[1], ExchangeConstants.detailDes[1], 160000L, "1.1", ExchangeConstants.appIcon[1]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[5], ExchangeConstants.des[5], ExchangeConstants.detailDes[5], 160000L, "1.1", ExchangeConstants.appIcon[5]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[0], ExchangeConstants.des[0], ExchangeConstants.detailDes[0], 160000L, "1.1", ExchangeConstants.appIcon[0]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[7], ExchangeConstants.des[7], ExchangeConstants.detailDes[7], 160000L, "1.1", ExchangeConstants.appIcon[7]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[6], ExchangeConstants.des[6], ExchangeConstants.detailDes[6], 160000L, "1.1", ExchangeConstants.appIcon[6]));
      continue;
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[5], ExchangeConstants.des[5], ExchangeConstants.detailDes[5], 160000L, "1.1", ExchangeConstants.appIcon[5]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[3], ExchangeConstants.des[3], ExchangeConstants.detailDes[3], 160000L, "1.1", ExchangeConstants.appIcon[3]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[2], ExchangeConstants.des[2], ExchangeConstants.detailDes[2], 160000L, "1.1", ExchangeConstants.appIcon[2]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[1], ExchangeConstants.des[1], ExchangeConstants.detailDes[1], 160000L, "1.1", ExchangeConstants.appIcon[1]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[0], ExchangeConstants.des[0], ExchangeConstants.detailDes[0], 160000L, "1.1", ExchangeConstants.appIcon[0]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[6], ExchangeConstants.des[6], ExchangeConstants.detailDes[6], 160000L, "1.1", ExchangeConstants.appIcon[6]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[7], ExchangeConstants.des[7], ExchangeConstants.detailDes[7], 160000L, "1.1", ExchangeConstants.appIcon[7]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[4], ExchangeConstants.des[4], ExchangeConstants.detailDes[4], 160000L, "1.1", ExchangeConstants.appIcon[4]));
      continue;
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[6], ExchangeConstants.des[6], ExchangeConstants.detailDes[6], 160000L, "1.1", ExchangeConstants.appIcon[6]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[3], ExchangeConstants.des[3], ExchangeConstants.detailDes[3], 160000L, "1.1", ExchangeConstants.appIcon[3]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[2], ExchangeConstants.des[2], ExchangeConstants.detailDes[2], 160000L, "1.1", ExchangeConstants.appIcon[2]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[1], ExchangeConstants.des[1], ExchangeConstants.detailDes[1], 160000L, "1.1", ExchangeConstants.appIcon[1]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[0], ExchangeConstants.des[0], ExchangeConstants.detailDes[0], 160000L, "1.1", ExchangeConstants.appIcon[0]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[7], ExchangeConstants.des[7], ExchangeConstants.detailDes[7], 160000L, "1.1", ExchangeConstants.appIcon[7]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[4], ExchangeConstants.des[4], ExchangeConstants.detailDes[4], 160000L, "1.1", ExchangeConstants.appIcon[4]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[5], ExchangeConstants.des[5], ExchangeConstants.detailDes[5], 160000L, "1.1", ExchangeConstants.appIcon[5]));
      continue;
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[7], ExchangeConstants.des[7], ExchangeConstants.detailDes[7], 160000L, "1.1", ExchangeConstants.appIcon[7]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[3], ExchangeConstants.des[3], ExchangeConstants.detailDes[3], 160000L, "1.1", ExchangeConstants.appIcon[3]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[4], ExchangeConstants.des[4], ExchangeConstants.detailDes[4], 160000L, "1.1", ExchangeConstants.appIcon[4]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[2], ExchangeConstants.des[2], ExchangeConstants.detailDes[2], 160000L, "1.1", ExchangeConstants.appIcon[2]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[1], ExchangeConstants.des[1], ExchangeConstants.detailDes[1], 160000L, "1.1", ExchangeConstants.appIcon[1]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[0], ExchangeConstants.des[0], ExchangeConstants.detailDes[0], 160000L, "1.1", ExchangeConstants.appIcon[0]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[5], ExchangeConstants.des[5], ExchangeConstants.detailDes[5], 160000L, "1.1", ExchangeConstants.appIcon[5]));
      localArrayList.add(new AdvertiserConfig(ExchangeConstants.title[6], ExchangeConstants.des[6], ExchangeConstants.detailDes[6], 160000L, "1.1", ExchangeConstants.appIcon[6]));
    }
  }
  
  private static Location getLocationOnce(Context paramContext)
  {
    return new LocationAgent(paramContext).getLocation();
  }
  
  private static JSONObject getReportInput(int paramInt1, int paramInt2, int paramInt3, List<AdvertiserConfig> paramList)
  {
    JSONObject localJSONObject1 = new JSONObject();
    Object localObject1;
    for (;;)
    {
      try
      {
        localJSONObject1.put("page_level", paramInt2);
        localJSONObject1.put("promotion_type", paramInt3);
        localObject1 = (TelephonyManager)context.getSystemService("phone");
        if (localObject1 == null)
        {
          Log.w(ExchangeConstants.LOG_TAG, "No IMEI.");
          return null;
        }
        localJSONObject1.put("device_id", ((TelephonyManager)localObject1).getDeviceId());
        localJSONObject1.put("publisher", ExchangeConstants.APPKEY);
        localObject2 = getDateTime();
        localObject1 = localObject2.split(" ")[0];
        localObject2 = localObject2.split(" ")[1];
        localJSONObject1.put("date", localObject1);
        localJSONObject1.put("time", localObject2);
        localObject1 = Calendar.getInstance(new Configuration().locale);
        if (localObject1 == null) {
          break label245;
        }
        localObject1 = ((Calendar)localObject1).getTimeZone();
        if (localObject1 != null)
        {
          localJSONObject1.put("timezone", ((TimeZone)localObject1).getRawOffset() / 3600000);
          localObject1 = new JSONArray();
          paramInt3 = paramList.size();
          paramInt2 = 0;
          if (paramInt2 < paramInt3) {
            break;
          }
          localJSONObject1.put("promoters", localObject1);
          return localJSONObject1;
        }
      }
      catch (Exception paramList)
      {
        paramList.printStackTrace();
        return localJSONObject1;
      }
      localJSONObject1.put("timezone", 8);
      continue;
      label245:
      localJSONObject1.put("timezone", 8);
    }
    Object localObject2 = (AdvertiserConfig)paramList.get(paramInt2);
    JSONObject localJSONObject2 = new JSONObject();
    localJSONObject2.put("promoter", ((AdvertiserConfig)localObject2).appkey);
    localJSONObject2.put("category", ((AdvertiserConfig)localObject2).category);
    localJSONObject2.put("action", ((AdvertiserConfig)localObject2).action);
    if (paramInt3 > 1) {
      localJSONObject2.put("action_index", paramInt2);
    }
    for (;;)
    {
      ((JSONArray)localObject1).put(localJSONObject2);
      paramInt2 += 1;
      break;
      localJSONObject2.put("action_index", paramInt1);
    }
  }
  
  /* Error */
  private static JSONObject getRequestInput()
  {
    // Byte code:
    //   0: new 103	org/json/JSONObject
    //   3: dup
    //   4: invokespecial 346	org/json/JSONObject:<init>	()V
    //   7: astore_1
    //   8: aload_1
    //   9: ldc_w 431
    //   12: getstatic 433	com/exchange/Public/ExchangeConstants:keywords	Ljava/lang/String;
    //   15: invokevirtual 371	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   18: pop
    //   19: aload_1
    //   20: ldc_w 435
    //   23: getstatic 437	com/exchange/Public/ExchangeConstants:sdk_version	I
    //   26: invokevirtual 352	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   29: pop
    //   30: getstatic 165	com/exchange/Controller/ExchangeDataService:context	Landroid/content/Context;
    //   33: ldc_w 356
    //   36: invokevirtual 54	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   39: checkcast 358	android/telephony/TelephonyManager
    //   42: astore_2
    //   43: aload_2
    //   44: ifnonnull +15 -> 59
    //   47: getstatic 92	com/exchange/Public/ExchangeConstants:LOG_TAG	Ljava/lang/String;
    //   50: ldc_w 360
    //   53: invokestatic 363	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   56: pop
    //   57: aconst_null
    //   58: areturn
    //   59: aload_1
    //   60: ldc_w 365
    //   63: aload_2
    //   64: invokevirtual 368	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   67: invokevirtual 371	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   70: pop
    //   71: aload_1
    //   72: ldc_w 439
    //   75: getstatic 444	android/os/Build:MODEL	Ljava/lang/String;
    //   78: invokevirtual 371	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   81: pop
    //   82: getstatic 165	com/exchange/Controller/ExchangeDataService:context	Landroid/content/Context;
    //   85: invokestatic 448	com/exchange/Public/DeviceManager:getAppkey	(Landroid/content/Context;)Ljava/lang/String;
    //   88: putstatic 376	com/exchange/Public/ExchangeConstants:APPKEY	Ljava/lang/String;
    //   91: aload_1
    //   92: ldc_w 450
    //   95: getstatic 376	com/exchange/Public/ExchangeConstants:APPKEY	Ljava/lang/String;
    //   98: invokevirtual 371	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   101: pop
    //   102: aload_1
    //   103: ldc_w 452
    //   106: getstatic 165	com/exchange/Controller/ExchangeDataService:context	Landroid/content/Context;
    //   109: invokevirtual 36	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   112: getstatic 165	com/exchange/Controller/ExchangeDataService:context	Landroid/content/Context;
    //   115: invokevirtual 42	android/content/Context:getPackageName	()Ljava/lang/String;
    //   118: iconst_0
    //   119: invokevirtual 456	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   122: getfield 461	android/content/pm/PackageInfo:versionCode	I
    //   125: invokevirtual 352	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   128: pop
    //   129: aload_1
    //   130: ldc_w 463
    //   133: ldc_w 465
    //   136: invokevirtual 371	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   139: pop
    //   140: new 390	android/content/res/Configuration
    //   143: dup
    //   144: invokespecial 391	android/content/res/Configuration:<init>	()V
    //   147: astore_3
    //   148: getstatic 165	com/exchange/Controller/ExchangeDataService:context	Landroid/content/Context;
    //   151: invokevirtual 469	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   154: aload_3
    //   155: invokestatic 475	android/provider/Settings$System:getConfiguration	(Landroid/content/ContentResolver;Landroid/content/res/Configuration;)V
    //   158: aload_3
    //   159: ifnull +311 -> 470
    //   162: aload_3
    //   163: getfield 395	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   166: ifnull +304 -> 470
    //   169: aload_1
    //   170: ldc_w 476
    //   173: aload_3
    //   174: getfield 395	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   177: invokevirtual 481	java/util/Locale:getDisplayName	()Ljava/lang/String;
    //   180: invokevirtual 371	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   183: pop
    //   184: aload_3
    //   185: getfield 395	android/content/res/Configuration:locale	Ljava/util/Locale;
    //   188: invokestatic 401	java/util/Calendar:getInstance	(Ljava/util/Locale;)Ljava/util/Calendar;
    //   191: astore_3
    //   192: aload_3
    //   193: ifnull +304 -> 497
    //   196: aload_3
    //   197: invokevirtual 405	java/util/Calendar:getTimeZone	()Ljava/util/TimeZone;
    //   200: astore_3
    //   201: aload_3
    //   202: ifnull +282 -> 484
    //   205: aload_1
    //   206: ldc_w 407
    //   209: aload_3
    //   210: invokevirtual 412	java/util/TimeZone:getRawOffset	()I
    //   213: ldc_w 413
    //   216: idiv
    //   217: invokevirtual 352	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   220: pop
    //   221: new 483	android/util/DisplayMetrics
    //   224: dup
    //   225: invokespecial 484	android/util/DisplayMetrics:<init>	()V
    //   228: astore_3
    //   229: getstatic 165	com/exchange/Controller/ExchangeDataService:context	Landroid/content/Context;
    //   232: ldc_w 486
    //   235: invokevirtual 54	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   238: checkcast 488	android/view/WindowManager
    //   241: invokeinterface 492 1 0
    //   246: aload_3
    //   247: invokevirtual 498	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   250: aload_3
    //   251: getfield 501	android/util/DisplayMetrics:widthPixels	I
    //   254: istore_0
    //   255: aload_1
    //   256: ldc_w 503
    //   259: new 173	java/lang/StringBuilder
    //   262: dup
    //   263: aload_3
    //   264: getfield 506	android/util/DisplayMetrics:heightPixels	I
    //   267: invokestatic 182	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   270: invokestatic 208	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   273: invokespecial 178	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   276: ldc_w 508
    //   279: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   282: iload_0
    //   283: invokestatic 182	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   286: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   289: invokevirtual 191	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   292: invokevirtual 371	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   295: pop
    //   296: getstatic 165	com/exchange/Controller/ExchangeDataService:context	Landroid/content/Context;
    //   299: invokestatic 510	com/exchange/Controller/ExchangeDataService:NetworkAccessMode	(Landroid/content/Context;)[Ljava/lang/String;
    //   302: astore_3
    //   303: aload_1
    //   304: ldc_w 512
    //   307: aload_3
    //   308: iconst_0
    //   309: aaload
    //   310: invokevirtual 371	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   313: pop
    //   314: aload_3
    //   315: iconst_0
    //   316: aaload
    //   317: ldc 76
    //   319: invokevirtual 119	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   322: ifeq +14 -> 336
    //   325: aload_1
    //   326: ldc_w 514
    //   329: aload_3
    //   330: iconst_1
    //   331: aaload
    //   332: invokevirtual 371	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   335: pop
    //   336: aload_1
    //   337: ldc_w 516
    //   340: aload_2
    //   341: invokevirtual 519	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   344: invokevirtual 371	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   347: pop
    //   348: getstatic 165	com/exchange/Controller/ExchangeDataService:context	Landroid/content/Context;
    //   351: invokestatic 521	com/exchange/Controller/ExchangeDataService:getLocationOnce	(Landroid/content/Context;)Landroid/location/Location;
    //   354: astore_2
    //   355: aload_2
    //   356: ifnull +196 -> 552
    //   359: aload_1
    //   360: ldc_w 523
    //   363: aload_2
    //   364: invokevirtual 529	android/location/Location:getLatitude	()D
    //   367: invokestatic 532	java/lang/String:valueOf	(D)Ljava/lang/String;
    //   370: invokevirtual 371	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   373: pop
    //   374: aload_1
    //   375: ldc_w 534
    //   378: aload_2
    //   379: invokevirtual 537	android/location/Location:getLongitude	()D
    //   382: invokestatic 532	java/lang/String:valueOf	(D)Ljava/lang/String;
    //   385: invokevirtual 371	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   388: pop
    //   389: aload_1
    //   390: ldc_w 539
    //   393: invokestatic 544	com/exchange/Public/UmengHelper:getCPU	()Ljava/lang/String;
    //   396: invokevirtual 371	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   399: pop
    //   400: aload_1
    //   401: ldc_w 388
    //   404: new 299	java/util/Date
    //   407: dup
    //   408: invokespecial 300	java/util/Date:<init>	()V
    //   411: invokevirtual 545	java/util/Date:toString	()Ljava/lang/String;
    //   414: invokevirtual 371	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   417: pop
    //   418: aload_1
    //   419: ldc_w 547
    //   422: ldc_w 549
    //   425: invokevirtual 371	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   428: pop
    //   429: aload_1
    //   430: ldc_w 551
    //   433: getstatic 158	com/exchange/Public/ExchangeConstants:REQUEST_NUMBER	I
    //   436: invokevirtual 352	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   439: pop
    //   440: aload_1
    //   441: areturn
    //   442: astore_2
    //   443: getstatic 92	com/exchange/Public/ExchangeConstants:LOG_TAG	Ljava/lang/String;
    //   446: ldc_w 553
    //   449: invokestatic 363	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   452: pop
    //   453: aload_1
    //   454: areturn
    //   455: astore_3
    //   456: aload_1
    //   457: ldc_w 452
    //   460: ldc_w 555
    //   463: invokevirtual 371	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   466: pop
    //   467: goto -338 -> 129
    //   470: aload_1
    //   471: ldc_w 476
    //   474: ldc_w 557
    //   477: invokevirtual 371	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   480: pop
    //   481: goto -297 -> 184
    //   484: aload_1
    //   485: ldc_w 407
    //   488: bipush 8
    //   490: invokevirtual 352	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   493: pop
    //   494: goto -273 -> 221
    //   497: aload_1
    //   498: ldc_w 407
    //   501: bipush 8
    //   503: invokevirtual 352	org/json/JSONObject:put	(Ljava/lang/String;I)Lorg/json/JSONObject;
    //   506: pop
    //   507: goto -286 -> 221
    //   510: astore_3
    //   511: aload_1
    //   512: ldc_w 503
    //   515: ldc 30
    //   517: invokevirtual 371	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   520: pop
    //   521: goto -225 -> 296
    //   524: astore_3
    //   525: aload_1
    //   526: ldc_w 512
    //   529: ldc 30
    //   531: invokevirtual 371	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   534: pop
    //   535: goto -199 -> 336
    //   538: astore_2
    //   539: aload_1
    //   540: ldc_w 516
    //   543: ldc 30
    //   545: invokevirtual 371	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   548: pop
    //   549: goto -201 -> 348
    //   552: aload_1
    //   553: ldc_w 523
    //   556: dconst_0
    //   557: invokevirtual 560	org/json/JSONObject:put	(Ljava/lang/String;D)Lorg/json/JSONObject;
    //   560: pop
    //   561: aload_1
    //   562: ldc_w 534
    //   565: dconst_0
    //   566: invokevirtual 560	org/json/JSONObject:put	(Ljava/lang/String;D)Lorg/json/JSONObject;
    //   569: pop
    //   570: goto -181 -> 389
    // Local variable table:
    //   start	length	slot	name	signature
    //   254	29	0	i	int
    //   7	555	1	localJSONObject	JSONObject
    //   42	337	2	localObject1	Object
    //   442	1	2	localException1	Exception
    //   538	1	2	localException2	Exception
    //   147	183	3	localObject2	Object
    //   455	1	3	localNameNotFoundException	android.content.pm.PackageManager.NameNotFoundException
    //   510	1	3	localException3	Exception
    //   524	1	3	localException4	Exception
    // Exception table:
    //   from	to	target	type
    //   8	43	442	java/lang/Exception
    //   47	57	442	java/lang/Exception
    //   59	102	442	java/lang/Exception
    //   102	129	442	java/lang/Exception
    //   129	158	442	java/lang/Exception
    //   162	184	442	java/lang/Exception
    //   184	192	442	java/lang/Exception
    //   196	201	442	java/lang/Exception
    //   205	221	442	java/lang/Exception
    //   348	355	442	java/lang/Exception
    //   359	389	442	java/lang/Exception
    //   389	440	442	java/lang/Exception
    //   456	467	442	java/lang/Exception
    //   470	481	442	java/lang/Exception
    //   484	494	442	java/lang/Exception
    //   497	507	442	java/lang/Exception
    //   511	521	442	java/lang/Exception
    //   525	535	442	java/lang/Exception
    //   539	549	442	java/lang/Exception
    //   552	570	442	java/lang/Exception
    //   102	129	455	android/content/pm/PackageManager$NameNotFoundException
    //   221	296	510	java/lang/Exception
    //   296	336	524	java/lang/Exception
    //   336	348	538	java/lang/Exception
  }
  
  public static boolean hasData()
  {
    return (mAdvertisers != null) && (mAdvertisers.size() != 0);
  }
  
  static int localLenth(String paramString)
  {
    int i = 0;
    int j = paramString.length() - 1;
    if (j < 0) {
      return i / 2;
    }
    char c = paramString.charAt(j);
    if (((c >= '0') && (c <= '9')) || ((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z'))) {
      i += 1;
    }
    for (;;)
    {
      j -= 1;
      break;
      if (Character.isLetter(c)) {
        i += 2;
      } else {
        i += 1;
      }
    }
  }
  
  public static int report(int paramInt1, int paramInt2, int paramInt3, List<AdvertiserConfig> paramList)
  {
    paramList = Network.sendMessage(getReportInput(paramInt1, paramInt2, paramInt3, paramList), ExchangeConstants.REPORT_URL);
    Log.i("exchange", "report result:" + paramList);
    if (paramList == null) {
      return -1;
    }
    try
    {
      boolean bool = "ok".equals(new JSONObject(paramList).getString("success"));
      if (bool) {
        return 1;
      }
      return 0;
    }
    catch (JSONException paramList)
    {
      paramList.printStackTrace();
    }
    return -2;
  }
  
  public static void request()
  {
    Object localObject2 = Network.sendMessage(getRequestInput(), ExchangeConstants.REQUEST_URL);
    Log.i("exchange", "request result:" + (String)localObject2);
    Object localObject1 = null;
    try
    {
      localObject2 = new JSONObject((String)localObject2);
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
      }
    }
    mAdvertisers = convertToAdvertisers(localObject1);
    if (ExchangeConstants.filterInstalledApp) {
      filter();
    }
  }
  
  public static void requestDataAsyn(Context paramContext, ExchangeDataRequestListener paramExchangeDataRequestListener)
  {
    if ((ExchangeConstants.ONLY_CHINESE) && (!DeviceManager.isChinese(paramContext)))
    {
      Log.e(ExchangeConstants.LOG_TAG, "English os can not show ads");
      paramExchangeDataRequestListener.dataReceived(0);
      return;
    }
    new GetDataThread(paramContext, paramExchangeDataRequestListener).start();
  }
  
  public static boolean rotate()
  {
    if (hasData()) {}
    for (int i = (curIndex + 1) % mAdvertisers.size();; i = (curIndex + 1) % mAdvertisers.size()) {
      if ((i == curIndex) || (getAd(i).landingType == 0))
      {
        if (i != curIndex) {
          break;
        }
        return false;
      }
    }
    curIndex = i;
    return true;
  }
  
  public static void setKeywords(String paramString)
  {
    ExchangeConstants.keywords = paramString;
  }
  
  public static void trimDescription(List<AdvertiserConfig> paramList, int paramInt1, int paramInt2)
  {
    paramList = paramList.iterator();
    for (;;)
    {
      if (!paramList.hasNext()) {
        return;
      }
      AdvertiserConfig localAdvertiserConfig = (AdvertiserConfig)paramList.next();
      String str = localAdvertiserConfig.adDescription;
      if (str.length() > paramInt2) {
        localAdvertiserConfig.adDescription = str.substring(0, paramInt2);
      }
      str = localAdvertiserConfig.adName;
      if (str.length() > paramInt1) {
        localAdvertiserConfig.adName = str.substring(0, paramInt1);
      }
    }
  }
}
