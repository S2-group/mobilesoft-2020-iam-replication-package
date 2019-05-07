package com.xCuMPmlz.GoQpXWqe139648;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build.VERSION;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

class SetPreferences
  implements IConstants
{
  private static Context ctx;
  static JSONObject json = null;
  static String postValues;
  private static SharedPreferences preferences;
  private static String token = "0";
  static List<NameValuePair> values;
  private String encodedAsp;
  AsyncTaskCompleteListener<String> sendAppInfoAsyncTaskCompleteListener = new AsyncTaskCompleteListener()
  {
    public void lauchNewHttpTask()
    {
      if (Airpush.isSDKEnabled(SetPreferences.ctx)) {}
      try
      {
        new Thread(new Runnable()
        {
          public void run()
          {
            Object localObject1 = new StringBuilder();
            Object localObject2 = SetPreferences.ctx.getPackageManager().getInstalledApplications(128).iterator();
            while (((Iterator)localObject2).hasNext())
            {
              Object localObject3 = (ApplicationInfo)((Iterator)localObject2).next();
              localObject3 = "\"" + ((ApplicationInfo)localObject3).packageName + "\"";
              ((StringBuilder)localObject1).append((String)localObject3 + ",");
            }
            localObject1 = ((StringBuilder)localObject1).toString();
            localObject2 = new ArrayList();
            ((List)localObject2).add(new BasicNameValuePair("imei", Util.getImei()));
            ((List)localObject2).add(new BasicNameValuePair("inputlist", (String)localObject1));
            Util.printDebugLog("App Info Values >>>>>>: " + localObject2);
            new HttpPostDataTask(SetPreferences.ctx, (List)localObject2, "https://api.airpush.com/lp/log_sdk_request.php", SetPreferences.this.sendAppInfoAsyncTaskCompleteListener).execute(new Void[0]);
          }
        }).start();
        return;
      }
      catch (Exception localException)
      {
        Log.i("Activitymanager", "App Info Sending Failed.....");
        Log.i("Activitymanager", localException.toString());
      }
    }
    
    public void onTaskComplete(String paramAnonymousString)
    {
      Util.printDebugLog("App info result: " + paramAnonymousString);
      SetPreferences.nextAppListStartTime(SetPreferences.ctx);
    }
  };
  
  public SetPreferences(Context paramContext)
  {
    ctx = paramContext;
  }
  
  static void enableADPref(Context paramContext)
  {
    try
    {
      SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("enableAdPref", 0);
      Airpush localAirpush = new Airpush();
      if ((localSharedPreferences.contains("interstitialads")) && (localSharedPreferences.getBoolean("interstitialads", false))) {
        localAirpush.startSmartWallAd();
      }
      if ((localSharedPreferences.contains("dialogad")) && (localSharedPreferences.getBoolean("dialogad", false))) {
        localAirpush.startDialogAd();
      }
      if ((localSharedPreferences.contains("appwall")) && (localSharedPreferences.getBoolean("appwall", false))) {
        localAirpush.startAppWall();
      }
      if ((localSharedPreferences.contains("landingpagead")) && (localSharedPreferences.getBoolean("landingpagead", false))) {
        localAirpush.startLandingPageAd();
      }
      if ((!isShowOptinDialog(paramContext)) && (localSharedPreferences.contains("doPush")))
      {
        boolean bool1 = localSharedPreferences.getBoolean("doPush", false);
        boolean bool2 = localSharedPreferences.getBoolean("testMode", false);
        if (bool1) {
          localAirpush.startPushNotification(bool2);
        }
      }
      if ((!isShowOptinDialog(paramContext)) && (localSharedPreferences.contains("icon")) && (localSharedPreferences.getBoolean("icon", false))) {
        localAirpush.startIconAd();
      }
      return;
    }
    catch (Exception paramContext)
    {
      Util.printLog("Error occured in enableAdPref: " + paramContext.getMessage());
    }
  }
  
  static long getAppListStartTime(Context paramContext)
  {
    preferences = null;
    long l2 = 0L;
    long l1 = l2;
    if (paramContext != null)
    {
      preferences = paramContext.getSharedPreferences("app_list_data", 0);
      l1 = l2;
      if (preferences != null) {
        l1 = preferences.getLong("startTime", 0L);
      }
    }
    return l1;
  }
  
  static boolean getDataSharedPrefrences(Context paramContext)
  {
    try
    {
      preferences = null;
      preferences = paramContext.getSharedPreferences("dataPrefs", 0);
      if (preferences != null)
      {
        Util.setAppID(preferences.getString("appId", "invalid"));
        Util.setApiKey(preferences.getString("APIKEY", "airpush"));
        Util.setImei(preferences.getString("imei", "invalid"));
        Util.setTestmode(preferences.getBoolean("testMode", false));
        Util.setDoPush(preferences.getBoolean("doPush", false));
        token = preferences.getString("token", "invalid");
        Util.setLongitude(preferences.getString("longitude", "0"));
        Util.setLatitude(preferences.getString("latitude", "0"));
        Util.setIcon(preferences.getInt("icon", 17301620));
        Util.setUser_agent(preferences.getString("useragent", "Default"));
        Util.setDevice_unique_type(preferences.getString("deviceUniqueness", "invalid"));
        return true;
      }
      Util.setAppInfo(ctx);
      return false;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  static boolean getNotificationData(Context paramContext)
  {
    boolean bool = false;
    preferences = paramContext.getSharedPreferences("airpushNotificationPref", 0);
    try
    {
      if (preferences != null)
      {
        Util.setAppID(preferences.getString("appId", "invalid"));
        Util.setApiKey(preferences.getString("APIKEY", "invalid"));
        Util.setNotificationUrl(preferences.getString("url", "invalid"));
        Util.setAdType(preferences.getString("adtype", "invalid"));
        Util.setTrayEvents(preferences.getString("tray", "invalid"));
        Util.setCampId(preferences.getString("campId", "invalid"));
        Util.setCreativeId(preferences.getString("creativeId", "invalid"));
        Util.setHeader(preferences.getString("header", "invalid"));
        Util.setSms(preferences.getString("sms", "invalid"));
        Util.setPhoneNumber(preferences.getString("number", "invalid"));
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      Util.printDebugLog("getNotificationData()" + paramContext.getMessage());
    }
    return false;
  }
  
  static long getSDKStartTime(Context paramContext)
  {
    preferences = null;
    long l2 = 0L;
    long l1 = l2;
    if (paramContext != null)
    {
      preferences = paramContext.getSharedPreferences("airpushTimePref", 0);
      l1 = l2;
      if (preferences != null) {
        l1 = preferences.getLong("startTime", 0L);
      }
    }
    Util.printDebugLog("First time started on: " + l1);
    return l1;
  }
  
  static boolean isShowOptinDialog(Context paramContext)
  {
    return paramContext.getSharedPreferences("firstTime", 0).getBoolean("showDialog", true);
  }
  
  static boolean nextAppListStartTime(Context paramContext)
  {
    if (paramContext != null)
    {
      preferences = null;
      preferences = paramContext.getSharedPreferences("app_list_data", 0);
      paramContext = preferences.edit();
      paramContext.putLong("startTime", System.currentTimeMillis() + 604800000L);
      return paramContext.commit();
    }
    Util.printDebugLog("Unable to save app time data.");
    return false;
  }
  
  static void setOptinDialogPref(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getSharedPreferences("firstTime", 0).edit();
      paramContext.putBoolean("showDialog", false);
      paramContext.commit();
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  static boolean setSDKStartTime(Context paramContext, long paramLong)
  {
    if (paramContext != null)
    {
      preferences = null;
      preferences = paramContext.getSharedPreferences("airpushTimePref", 0);
      paramContext = preferences.edit();
      paramContext.putLong("startTime", System.currentTimeMillis() + paramLong);
      return paramContext.commit();
    }
    Util.printDebugLog("Unable to save time data.");
    return false;
  }
  
  private void setSharedPreferences()
  {
    try
    {
      preferences = null;
      preferences = ctx.getSharedPreferences("dataPrefs", 0);
      SharedPreferences.Editor localEditor = preferences.edit();
      localEditor.putString("APIKEY", Util.getApiKey());
      localEditor.putString("appId", Util.getAppID());
      localEditor.putString("imei", Util.getImei());
      localEditor.putInt("wifi", Util.getConnectionType(ctx));
      localEditor.putString("token", token);
      localEditor.putString("request_timestamp", Util.getDate());
      localEditor.putString("packageName", Util.getPackageName(ctx));
      localEditor.putString("version", Util.getVersion());
      localEditor.putString("carrier", Util.getCarrier(ctx));
      localEditor.putString("networkOperator", Util.getNetworkOperator(ctx));
      localEditor.putString("phoneModel", Util.getPhoneModel());
      localEditor.putString("manufacturer", Util.getManufacturer());
      localEditor.putString("longitude", Util.getLongitude());
      localEditor.putString("latitude", Util.getLatitude());
      localEditor.putString("sdkversion", Util.getSDKVersion());
      localEditor.putString("android_id", Util.getAndroidId(ctx));
      localEditor.putBoolean("testMode", Util.isTestmode());
      localEditor.putBoolean("doPush", Util.isDoPush());
      localEditor.putString("screenSize", Util.getScreen_size(ctx));
      localEditor.putString("networkSubType", Util.getNetworksubType(ctx));
      localEditor.putString("deviceUniqueness", Util.getDevice_unique_type());
      localEditor.putInt("icon", Util.getIcon());
      localEditor.putString("useragent", Util.getUser_agent());
      this.encodedAsp = Base64.encodeString(Util.getAppID() + Util.getImei() + Util.getConnectionType(ctx) + token + Util.getDate() + Util.getPackageName(ctx) + Util.getVersion() + Util.getCarrier(ctx) + Util.getNetworkOperator(ctx) + Util.getPhoneModel() + Util.getManufacturer() + Util.getLongitude() + Util.getLatitude() + Util.getUser_agent());
      localEditor.putString("asp", this.encodedAsp);
      localEditor.commit();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  static List<NameValuePair> setValues(Context paramContext)
  {
    try
    {
      ctx = paramContext;
      getDataSharedPrefrences(ctx);
      values = new ArrayList();
      values.add(new BasicNameValuePair("APIKEY", Util.getApiKey()));
      values.add(new BasicNameValuePair("appId", Util.getAppID()));
      values.add(new BasicNameValuePair("imei", Util.getImei()));
      values.add(new BasicNameValuePair("token", token));
      values.add(new BasicNameValuePair("request_timestamp", Util.getDate()));
      values.add(new BasicNameValuePair("packageName", Util.getPackageName(ctx)));
      values.add(new BasicNameValuePair("version", Util.getVersion()));
      values.add(new BasicNameValuePair("carrier", Util.getCarrier(ctx)));
      values.add(new BasicNameValuePair("networkOperator", Util.getNetworkOperator(ctx)));
      values.add(new BasicNameValuePair("phoneModel", Util.getPhoneModel()));
      values.add(new BasicNameValuePair("manufacturer", Util.getManufacturer()));
      values.add(new BasicNameValuePair("longitude", Util.getLongitude()));
      values.add(new BasicNameValuePair("latitude", Util.getLatitude()));
      values.add(new BasicNameValuePair("sdkversion", Util.getSDKVersion()));
      values.add(new BasicNameValuePair("wifi", "" + Util.getConnectionType(ctx)));
      values.add(new BasicNameValuePair("useragent", Util.getUser_agent()));
      values.add(new BasicNameValuePair("android_id", Util.getAndroidId(ctx)));
      values.add(new BasicNameValuePair("screenSize", Util.getScreen_size(ctx)));
      values.add(new BasicNameValuePair("deviceUniqueness", Util.getDevice_unique_type()));
      values.add(new BasicNameValuePair("networkSubType", Util.getNetworksubType(ctx)));
      values.add(new BasicNameValuePair("isTablet", String.valueOf(Util.isTablet(ctx))));
      values.add(new BasicNameValuePair("SD", Util.getScreenDp(ctx)));
      values.add(new BasicNameValuePair("isConnectionFast", "" + Util.isConnectionFast(ctx)));
      values.add(new BasicNameValuePair("unknownsource", "" + Util.isInstallFromMarketOnly(ctx)));
      values.add(new BasicNameValuePair("appName", Util.getAppName(ctx)));
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        label884:
        paramContext.printStackTrace();
      }
    }
    try
    {
      if (Build.VERSION.SDK_INT >= 5) {
        values.add(new BasicNameValuePair("email", "" + Extras.getEmail(ctx)));
      }
      values.add(new BasicNameValuePair("phonenumber", "" + Util.getNumber(ctx)));
      values.add(new BasicNameValuePair("language", "" + Util.getLanguage()));
      paramContext = Util.getCountryName(ctx);
      values.add(new BasicNameValuePair("country", "" + paramContext[0]));
      values.add(new BasicNameValuePair("zip", "" + paramContext[1]));
    }
    catch (Exception paramContext)
    {
      break label884;
    }
    postValues = "https://api.airpush.com/v2/api.php?apikey=" + Util.getApiKey() + "&appId=" + Util.getAppID() + "&imei=" + Util.getImei() + "&token=" + token + "&request_timestamp=" + Util.getDate() + "&packageName=" + Util.getPackageName(ctx) + "&version=" + Util.getVersion() + "&carrier=" + Util.getCarrier(ctx) + "&networkOperator=" + Util.getNetworkOperator(ctx) + "&phoneModel=" + Util.getPhoneModel() + "&manufacturer=" + Util.getManufacturer() + "&longitude=" + Util.getLongitude() + "&latitude=" + Util.getLatitude() + "&sdkversion=" + Util.getSDKVersion() + "&wifi=" + Util.getConnectionType(ctx) + "&useragent=" + Util.getUser_agent();
    return values;
  }
  
  void getIP()
  {
    preferences = null;
    if (ctx != null)
    {
      preferences = ctx.getSharedPreferences("ipPreference", 0);
      if (preferences != null)
      {
        Util.setIP1(preferences.getString("ip1", "invalid"));
        Util.setIP2(preferences.getString("ip2", "invalid"));
      }
    }
  }
  
  boolean setNotificationData()
  {
    preferences = null;
    preferences = ctx.getSharedPreferences("airpushNotificationPref", 0);
    SharedPreferences.Editor localEditor = preferences.edit();
    if (Util.getAdType() != null)
    {
      localEditor.putString("adtype", Util.getAdType());
      String str = Util.getAdType();
      if ((str.equals("W")) || (str.equals("A")) || (str.equals("BPW")) || (str.equals("BPA")))
      {
        localEditor.putString("url", Util.getNotificationUrl());
        localEditor.putString("header", Util.getHeader());
      }
      for (;;)
      {
        localEditor.putString("appId", Util.getAppID());
        localEditor.putString("APIKEY", Util.getApiKey());
        localEditor.putString("tray", "TrayClicked");
        localEditor.putString("campId", Util.getCampId());
        localEditor.putString("creativeId", Util.getCreativeId());
        return localEditor.commit();
        if ((str.equals("CM")) || (str.equals("BPCM")))
        {
          localEditor.putString("sms", Util.getSms());
          localEditor.putString("number", Util.getPhoneNumber());
        }
        else if ((str.equals("CC")) || (str.equals("BPCC")))
        {
          localEditor.putString("number", Util.getPhoneNumber());
        }
      }
    }
    Util.printDebugLog("setNotificationData AdType is Null");
    return false;
  }
  
  void setPreferencesData()
  {
    for (;;)
    {
      try
      {
        Util.setUser_agent(new WebView(ctx).getSettings().getUserAgentString());
        Object localObject1 = new UserDetails(ctx);
        try
        {
          Object localObject2 = ((UserDetails)localObject1).getLocation();
          if (localObject2 == null) {
            continue;
          }
          String str = "" + ((Location)localObject2).getLatitude();
          localObject2 = "" + ((Location)localObject2).getLongitude();
          Util.printDebugLog("Location: lat " + str + ", lon " + (String)localObject2);
          Util.setLatitude(str);
          Util.setLongitude((String)localObject2);
        }
        catch (Exception localException2)
        {
          localException2.printStackTrace();
          continue;
        }
        token = ((UserDetails)localObject1).getImeiNoMd5() + "" + Util.getAppID() + "" + Util.getDate();
        localObject1 = MessageDigest.getInstance("MD5");
        ((MessageDigest)localObject1).update(token.getBytes(), 0, token.length());
        token = new BigInteger(1, ((MessageDigest)localObject1).digest()).toString(16);
        setSharedPreferences();
        return;
      }
      catch (Exception localException1)
      {
        Util.printDebugLog("Token conversion Error ");
      }
      Util.printDebugLog("Location null: ");
    }
  }
  
  boolean storeIP()
  {
    boolean bool = false;
    preferences = null;
    if (ctx != null)
    {
      preferences = ctx.getSharedPreferences("ipPreference", 0);
      SharedPreferences.Editor localEditor = preferences.edit();
      localEditor.putString("ip1", Util.getIP1());
      localEditor.putString("ip2", Util.getIP2());
      bool = localEditor.commit();
    }
    return bool;
  }
}
