package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.Vector;
import org.w3c.dom.Document;

public class TapjoyConnectCore
{
  private static final String TAG = "TapjoyConnect";
  private static String androidID;
  private static String appID;
  private static String appVersion;
  private static String bridgeVersion;
  private static String carrierCountryCode;
  private static String carrierName;
  private static Hashtable<String, String> connectFlags = null;
  private static TapjoyConnectNotifier connectNotifier;
  private static String connectionSubType;
  private static String connectionType;
  private static Context context = null;
  private static float currencyMultiplier;
  private static String deviceCountryCode;
  private static String deviceID;
  private static String deviceLanguage;
  private static boolean deviceLocation;
  private static String deviceManufacturer;
  private static String deviceModel;
  private static String deviceOSVersion;
  private static String deviceScreenDensity;
  private static String deviceScreenLayoutSize;
  private static String deviceType;
  private static String hostURL;
  private static long lastTimeStamp;
  private static String libraryVersion;
  private static String macAddress;
  private static String matchingPackageNames = "";
  private static String mobileCountryCode;
  private static String mobileNetworkCode;
  private static String paidAppActionID;
  private static String platformName;
  private static String plugin;
  private static String redirectDomain;
  private static String sdkType;
  private static String secretKey;
  private static String sessionID;
  private static String sha2DeviceID;
  private static boolean shareFacebook;
  private static boolean shareGooglePlus;
  private static boolean shareLinkedIn;
  private static boolean shareTwitter;
  private static String storeName;
  private static boolean storeView;
  private static TapjoyConnectCore tapjoyConnectCore = null;
  private static TapjoyURLConnection tapjoyURLConnection = null;
  private static String userID;
  private static boolean videoEnabled;
  private static String videoIDs;
  private static TapjoyViewNotifier viewNotifier;
  private boolean appPaused = false;
  private long elapsed_time = 0L;
  private boolean initialized = false;
  private Timer timer = null;
  
  static
  {
    connectNotifier = null;
    viewNotifier = null;
    androidID = "";
    sessionID = "";
    deviceID = "";
    sha2DeviceID = "";
    macAddress = "";
    deviceModel = "";
    deviceManufacturer = "";
    deviceType = "";
    deviceOSVersion = "";
    deviceCountryCode = "";
    deviceLanguage = "";
    appID = "";
    appVersion = "";
    libraryVersion = "";
    bridgeVersion = "";
    deviceScreenDensity = "";
    deviceScreenLayoutSize = "";
    userID = "";
    deviceLocation = false;
    platformName = "";
    carrierName = "";
    carrierCountryCode = "";
    mobileCountryCode = "";
    mobileNetworkCode = "";
    connectionType = "";
    connectionSubType = "";
    storeName = "";
    secretKey = "";
    plugin = "native";
    sdkType = "";
    hostURL = "https://ws.tapjoyads.com/";
    redirectDomain = "";
    videoEnabled = false;
    videoIDs = "";
    currencyMultiplier = 1.0F;
    shareFacebook = false;
    shareTwitter = false;
    shareGooglePlus = false;
    shareLinkedIn = false;
    storeView = false;
    paidAppActionID = null;
    lastTimeStamp = 0L;
  }
  
  public TapjoyConnectCore(Context paramContext)
  {
    context = paramContext;
    tapjoyURLConnection = new TapjoyURLConnection();
    if (init())
    {
      TapjoyLog.i("TapjoyConnect", "URL parameters: " + getURLParams());
      callConnect();
      sendOfflineLogs();
      if ((getFlagValue("user_id") != null) && (getFlagValue("user_id").length() > 0))
      {
        TapjoyLog.i("TapjoyConnect", "Setting userID to: " + getFlagValue("user_id"));
        setUserID(getFlagValue("user_id"));
      }
      this.initialized = true;
    }
  }
  
  private static String generateSessionID()
  {
    Object localObject = null;
    TapjoyLog.i("TapjoyConnect", "generating sessionID...");
    try
    {
      String str = TapjoyUtil.SHA256(System.currentTimeMillis() / 1000L + appID + deviceID);
      localObject = str;
      lastTimeStamp = System.currentTimeMillis();
      return str;
    }
    catch (Exception localException)
    {
      TapjoyLog.e("TapjoyConnect", "unable to generate session id: " + localException.toString());
    }
    return localObject;
  }
  
  public static String getAndroidID()
  {
    return androidID;
  }
  
  public static String getAppID()
  {
    return appID;
  }
  
  public static String getAwardPointsVerifier(long paramLong, int paramInt, String paramString)
  {
    try
    {
      paramString = TapjoyUtil.SHA256(appID + ":" + getVerifierID() + ":" + paramLong + ":" + secretKey + ":" + paramInt + ":" + paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      TapjoyLog.e("TapjoyConnect", "getAwardPointsVerifier ERROR: " + paramString.toString());
    }
    return "";
  }
  
  public static String getCarrierName()
  {
    return carrierName;
  }
  
  public static String getConnectionSubType()
  {
    String str2 = "";
    String str1 = str2;
    try
    {
      ConnectivityManager localConnectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
      str1 = str2;
      if (localConnectivityManager != null)
      {
        str1 = str2;
        str2 = localConnectivityManager.getActiveNetworkInfo().getSubtypeName();
        str1 = str2;
        TapjoyLog.i("TapjoyConnect", "connection_sub_type: " + str2);
        str1 = str2;
      }
      return str1;
    }
    catch (Exception localException)
    {
      TapjoyLog.e("TapjoyConnect", "getConnectionSubType error: " + localException.toString());
    }
    return str1;
  }
  
  public static String getConnectionType()
  {
    String str1 = "";
    String str3 = str1;
    for (;;)
    {
      try
      {
        ConnectivityManager localConnectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        if (localConnectivityManager == null) {
          break;
        }
        str3 = str1;
        if (localConnectivityManager.getActiveNetworkInfo() == null) {
          break;
        }
        str3 = str1;
        switch (localConnectivityManager.getActiveNetworkInfo().getType())
        {
        case 1: 
          str3 = str1;
          TapjoyLog.i("TapjoyConnect", "connectivity: " + localConnectivityManager.getActiveNetworkInfo().getType());
          str3 = str1;
          TapjoyLog.i("TapjoyConnect", "connection_type: " + str1);
          return str1;
        }
      }
      catch (Exception localException)
      {
        TapjoyLog.e("TapjoyConnect", "getConnectionType error: " + localException.toString());
        return str3;
      }
      str1 = "wifi";
      continue;
      String str2 = "mobile";
    }
    return "";
  }
  
  public static Context getContext()
  {
    return context;
  }
  
  public static String getDeviceID()
  {
    return deviceID;
  }
  
  public static String getFlagValue(String paramString)
  {
    String str = "";
    if (connectFlags != null) {
      str = (String)connectFlags.get(paramString);
    }
    paramString = str;
    if (str == null) {
      paramString = "";
    }
    return paramString;
  }
  
  public static Map<String, String> getGenericURLParams()
  {
    Map localMap = getParamsWithoutAppID();
    TapjoyUtil.safePut(localMap, "app_id", appID, true);
    return localMap;
  }
  
  public static String getHostURL()
  {
    return hostURL;
  }
  
  public static TapjoyConnectCore getInstance()
  {
    return tapjoyConnectCore;
  }
  
  public static String getMacAddress()
  {
    return macAddress;
  }
  
  public static Map<String, ?> getOfflineLogs()
  {
    return context.getSharedPreferences("tapjoyOfflineLog", 0).getAll();
  }
  
  private static String getPackageNamesVerifier(long paramLong, String paramString)
  {
    try
    {
      paramString = TapjoyUtil.SHA256(appID + ":" + deviceID + ":" + paramLong + ":" + secretKey + ":" + paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      TapjoyLog.e("TapjoyConnect", "getVerifier ERROR: " + paramString.toString());
    }
    return "";
  }
  
  private static Map<String, String> getParamsWithoutAppID()
  {
    HashMap localHashMap = new HashMap();
    if ((getFlagValue("sha_2_udid") != null) && (getFlagValue("sha_2_udid").equals("true")))
    {
      TapjoyUtil.safePut(localHashMap, "sha2_udid", sha2DeviceID, true);
      TapjoyUtil.safePut(localHashMap, "publisher_user_id", userID, true);
      TapjoyUtil.safePut(localHashMap, "android_id", androidID, true);
      TapjoyUtil.safePut(localHashMap, "mac_address", macAddress, true);
      TapjoyUtil.safePut(localHashMap, "device_name", deviceModel, true);
      TapjoyUtil.safePut(localHashMap, "device_type", deviceType, true);
      TapjoyUtil.safePut(localHashMap, "os_version", deviceOSVersion, true);
      TapjoyUtil.safePut(localHashMap, "country_code", deviceCountryCode, true);
      TapjoyUtil.safePut(localHashMap, "language_code", deviceLanguage, true);
      TapjoyUtil.safePut(localHashMap, "app_version", appVersion, true);
      TapjoyUtil.safePut(localHashMap, "library_version", libraryVersion, true);
      TapjoyUtil.safePut(localHashMap, "bridge_version", bridgeVersion, true);
      TapjoyUtil.safePut(localHashMap, "platform", platformName, true);
      TapjoyUtil.safePut(localHashMap, "display_multiplier", Float.toString(currencyMultiplier), true);
      TapjoyUtil.safePut(localHashMap, "carrier_name", carrierName, true);
      TapjoyUtil.safePut(localHashMap, "carrier_country_code", carrierCountryCode, true);
      TapjoyUtil.safePut(localHashMap, "mobile_country_code", mobileCountryCode, true);
      TapjoyUtil.safePut(localHashMap, "mobile_network_code", mobileNetworkCode, true);
      TapjoyUtil.safePut(localHashMap, "device_manufacturer", deviceManufacturer, true);
      TapjoyUtil.safePut(localHashMap, "screen_density", deviceScreenDensity, true);
      TapjoyUtil.safePut(localHashMap, "screen_layout_size", deviceScreenLayoutSize, true);
      connectionType = getConnectionType();
      TapjoyUtil.safePut(localHashMap, "connection_type", connectionType, true);
      connectionSubType = getConnectionSubType();
      TapjoyUtil.safePut(localHashMap, "connection_subtype", connectionSubType, true);
      TapjoyUtil.safePut(localHashMap, "plugin", plugin, true);
      TapjoyUtil.safePut(localHashMap, "sdk_type", sdkType, true);
      TapjoyUtil.safePut(localHashMap, "store_name", storeName, true);
      TapjoyUtil.safePut(localHashMap, "device_location", String.valueOf(deviceLocation), true);
      TapjoyUtil.safePut(localHashMap, "share_facebook", String.valueOf(shareFacebook), true);
      TapjoyUtil.safePut(localHashMap, "share_twitter", String.valueOf(shareTwitter), true);
      TapjoyUtil.safePut(localHashMap, "share_googleplus", String.valueOf(shareGooglePlus), true);
      TapjoyUtil.safePut(localHashMap, "share_linkedin", String.valueOf(shareLinkedIn), true);
      TapjoyUtil.safePut(localHashMap, "store_view", String.valueOf(storeView), true);
      if ((sessionID != null) && (sessionID.length() != 0) && (System.currentTimeMillis() - lastTimeStamp <= 1800000L)) {
        break label479;
      }
      sessionID = generateSessionID();
    }
    for (;;)
    {
      TapjoyUtil.safePut(localHashMap, "session_id", sessionID, true);
      return localHashMap;
      TapjoyUtil.safePut(localHashMap, "udid", deviceID, true);
      break;
      label479:
      lastTimeStamp = System.currentTimeMillis();
    }
  }
  
  public static String getRedirectDomain()
  {
    return redirectDomain;
  }
  
  public static String getSecretKey()
  {
    return secretKey;
  }
  
  public static String getSha1MacAddress()
  {
    try
    {
      String str = TapjoyUtil.SHA1(macAddress);
      return str;
    }
    catch (Exception localException)
    {
      TapjoyLog.e("TapjoyConnect", "Error generating sha1 of macAddress: " + localException.toString());
    }
    return null;
  }
  
  public static String getSha2DeviceID()
  {
    return sha2DeviceID;
  }
  
  public static Map<String, String> getTimeStampAndVerifierParams()
  {
    HashMap localHashMap = new HashMap();
    long l = System.currentTimeMillis() / 1000L;
    String str = getVerifier(l);
    TapjoyUtil.safePut(localHashMap, "timestamp", String.valueOf(l), true);
    TapjoyUtil.safePut(localHashMap, "verifier", str, true);
    return localHashMap;
  }
  
  public static Map<String, String> getURLParams()
  {
    Map localMap = getGenericURLParams();
    localMap.putAll(getTimeStampAndVerifierParams());
    return localMap;
  }
  
  public static String getUserID()
  {
    return userID;
  }
  
  private static String getVerifier(long paramLong)
  {
    try
    {
      String str = TapjoyUtil.SHA256(appID + ":" + getVerifierID() + ":" + paramLong + ":" + secretKey);
      return str;
    }
    catch (Exception localException)
    {
      TapjoyLog.e("TapjoyConnect", "getVerifier ERROR: " + localException.toString());
    }
    return "";
  }
  
  private static String getVerifierID()
  {
    if ((deviceID != null) && (deviceID.length() > 0)) {
      return deviceID;
    }
    if ((macAddress != null) && (macAddress.length() > 0)) {
      return macAddress;
    }
    if ((androidID != null) && (androidID.length() > 0)) {
      return androidID;
    }
    Log.e("TapjoyConnect", "Error -- no valid device identifier");
    return null;
  }
  
  public static Map<String, String> getVideoParams()
  {
    HashMap localHashMap = new HashMap();
    if (videoEnabled) {
      if (videoIDs.length() > 0) {
        TapjoyUtil.safePut(localHashMap, "video_offer_ids", videoIDs, true);
      }
    }
    for (;;)
    {
      TapjoyLog.i("TapjoyConnect", "video parameters: " + localHashMap);
      return localHashMap;
      TapjoyUtil.safePut(localHashMap, "hide_videos", String.valueOf(true), true);
    }
  }
  
  private static boolean handleConnectResponse(String paramString)
  {
    paramString = TapjoyUtil.buildDocument(paramString);
    if (paramString != null)
    {
      Object localObject = TapjoyUtil.getNodeTrimValue(paramString.getElementsByTagName("PackageNames"));
      if ((localObject != null) && (((String)localObject).length() > 0))
      {
        Vector localVector = new Vector();
        int j;
        for (int i = 0;; i = j + 1)
        {
          j = ((String)localObject).indexOf(',', i);
          if (j == -1)
          {
            TapjoyLog.i("TapjoyConnect", "parse: " + ((String)localObject).substring(i).trim());
            localVector.add(((String)localObject).substring(i).trim());
            matchingPackageNames = "";
            localObject = context.getPackageManager().getInstalledApplications(0).iterator();
            while (((Iterator)localObject).hasNext())
            {
              ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
              if (((localApplicationInfo.flags & 0x1) != 1) && (localVector.contains(localApplicationInfo.packageName)))
              {
                TapjoyLog.i("TapjoyConnect", "MATCH: installed packageName: " + localApplicationInfo.packageName);
                if (matchingPackageNames.length() > 0) {
                  matchingPackageNames += ",";
                }
                matchingPackageNames += localApplicationInfo.packageName;
              }
            }
          }
          TapjoyLog.i("TapjoyConnect", "parse: " + ((String)localObject).substring(i, j).trim());
          localVector.add(((String)localObject).substring(i, j).trim());
        }
      }
      paramString = TapjoyUtil.getNodeTrimValue(paramString.getElementsByTagName("Success"));
    }
    return (paramString != null) && (paramString.equals("true"));
  }
  
  private boolean handlePayPerActionResponse(String paramString)
  {
    paramString = TapjoyUtil.buildDocument(paramString);
    if (paramString != null)
    {
      paramString = TapjoyUtil.getNodeTrimValue(paramString.getElementsByTagName("Success"));
      if ((paramString != null) && (paramString.equals("true")))
      {
        TapjoyLog.i("TapjoyConnect", "Successfully sent completed Pay-Per-Action to Tapjoy server.");
        return true;
      }
      TapjoyLog.e("TapjoyConnect", "Completed Pay-Per-Action call failed.");
    }
    return false;
  }
  
  /* Error */
  private boolean init()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 628	com/tapjoy/TapjoyConnectCore:validatePermissions	()Z
    //   4: ifne +14 -> 18
    //   7: ldc 14
    //   9: ldc_w 630
    //   12: invokestatic 533	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   15: pop
    //   16: iconst_0
    //   17: ireturn
    //   18: getstatic 82	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   21: invokevirtual 579	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   24: astore 4
    //   26: getstatic 182	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   29: ifnonnull +13 -> 42
    //   32: new 365	java/util/Hashtable
    //   35: dup
    //   36: invokespecial 631	java/util/Hashtable:<init>	()V
    //   39: putstatic 182	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   42: aload 4
    //   44: ifnull +161 -> 205
    //   47: aload 4
    //   49: getstatic 82	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   52: invokevirtual 634	android/content/Context:getPackageName	()Ljava/lang/String;
    //   55: sipush 128
    //   58: invokevirtual 638	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   61: astore 5
    //   63: aload 5
    //   65: ifnull +1070 -> 1135
    //   68: aload 5
    //   70: getfield 642	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   73: ifnull +1062 -> 1135
    //   76: getstatic 648	com/tapjoy/TapjoyConnectFlag:FLAG_ARRAY	[Ljava/lang/String;
    //   79: astore 6
    //   81: aload 6
    //   83: arraylength
    //   84: istore_2
    //   85: iconst_0
    //   86: istore_1
    //   87: iload_1
    //   88: iload_2
    //   89: if_icmpge +108 -> 197
    //   92: aload 6
    //   94: iload_1
    //   95: aaload
    //   96: astore 7
    //   98: aload 5
    //   100: getfield 642	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   103: new 206	java/lang/StringBuilder
    //   106: dup
    //   107: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   110: ldc_w 650
    //   113: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   116: aload 7
    //   118: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   121: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   124: invokevirtual 654	android/os/Bundle:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   127: astore 8
    //   129: aload 8
    //   131: ifnull +1782 -> 1913
    //   134: aload 8
    //   136: invokevirtual 655	java/lang/Object:toString	()Ljava/lang/String;
    //   139: astore 8
    //   141: aload 8
    //   143: ifnull +1770 -> 1913
    //   146: ldc 14
    //   148: new 206	java/lang/StringBuilder
    //   151: dup
    //   152: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   155: ldc_w 657
    //   158: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   161: aload 7
    //   163: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   166: ldc_w 659
    //   169: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: aload 8
    //   174: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   180: invokestatic 230	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   183: getstatic 182	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   186: aload 7
    //   188: aload 8
    //   190: invokevirtual 663	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   193: pop
    //   194: goto +1719 -> 1913
    //   197: ldc 14
    //   199: ldc_w 665
    //   202: invokestatic 230	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   205: ldc_w 667
    //   208: invokestatic 242	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   211: ifnull +22 -> 233
    //   214: ldc_w 667
    //   217: invokestatic 242	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   220: ldc_w 407
    //   223: invokevirtual 411	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   226: ifeq +7 -> 233
    //   229: iconst_1
    //   230: invokestatic 671	com/tapjoy/TapjoyLog:enableLogging	(Z)V
    //   233: getstatic 82	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   236: invokevirtual 675	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   239: ldc_w 417
    //   242: invokestatic 681	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   245: putstatic 94	com/tapjoy/TapjoyConnectCore:androidID	Ljava/lang/String;
    //   248: getstatic 94	com/tapjoy/TapjoyConnectCore:androidID	Ljava/lang/String;
    //   251: ifnull +12 -> 263
    //   254: getstatic 94	com/tapjoy/TapjoyConnectCore:androidID	Ljava/lang/String;
    //   257: invokevirtual 684	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   260: putstatic 94	com/tapjoy/TapjoyConnectCore:androidID	Ljava/lang/String;
    //   263: aload 4
    //   265: getstatic 82	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   268: invokevirtual 634	android/content/Context:getPackageName	()Ljava/lang/String;
    //   271: iconst_0
    //   272: invokevirtual 688	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   275: getfield 693	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   278: putstatic 118	com/tapjoy/TapjoyConnectCore:appVersion	Ljava/lang/String;
    //   281: ldc_w 695
    //   284: putstatic 108	com/tapjoy/TapjoyConnectCore:deviceType	Ljava/lang/String;
    //   287: ldc_w 695
    //   290: putstatic 132	com/tapjoy/TapjoyConnectCore:platformName	Ljava/lang/String;
    //   293: getstatic 700	android/os/Build:MODEL	Ljava/lang/String;
    //   296: putstatic 104	com/tapjoy/TapjoyConnectCore:deviceModel	Ljava/lang/String;
    //   299: getstatic 703	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   302: putstatic 106	com/tapjoy/TapjoyConnectCore:deviceManufacturer	Ljava/lang/String;
    //   305: getstatic 708	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   308: putstatic 110	com/tapjoy/TapjoyConnectCore:deviceOSVersion	Ljava/lang/String;
    //   311: invokestatic 714	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   314: invokevirtual 717	java/util/Locale:getCountry	()Ljava/lang/String;
    //   317: putstatic 112	com/tapjoy/TapjoyConnectCore:deviceCountryCode	Ljava/lang/String;
    //   320: invokestatic 714	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   323: invokevirtual 720	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   326: putstatic 114	com/tapjoy/TapjoyConnectCore:deviceLanguage	Ljava/lang/String;
    //   329: ldc_w 722
    //   332: putstatic 120	com/tapjoy/TapjoyConnectCore:libraryVersion	Ljava/lang/String;
    //   335: ldc_w 724
    //   338: putstatic 122	com/tapjoy/TapjoyConnectCore:bridgeVersion	Ljava/lang/String;
    //   341: ldc -100
    //   343: putstatic 158	com/tapjoy/TapjoyConnectCore:hostURL	Ljava/lang/String;
    //   346: getstatic 82	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   349: ldc_w 726
    //   352: invokevirtual 332	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   355: checkcast 728	android/telephony/TelephonyManager
    //   358: astore 4
    //   360: aload 4
    //   362: ifnull +251 -> 613
    //   365: aload 4
    //   367: invokevirtual 731	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   370: putstatic 134	com/tapjoy/TapjoyConnectCore:carrierName	Ljava/lang/String;
    //   373: aload 4
    //   375: invokevirtual 734	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   378: putstatic 136	com/tapjoy/TapjoyConnectCore:carrierCountryCode	Ljava/lang/String;
    //   381: aload 4
    //   383: invokevirtual 737	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   386: ifnull +53 -> 439
    //   389: aload 4
    //   391: invokevirtual 737	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   394: invokevirtual 248	java/lang/String:length	()I
    //   397: iconst_5
    //   398: if_icmpeq +16 -> 414
    //   401: aload 4
    //   403: invokevirtual 737	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   406: invokevirtual 248	java/lang/String:length	()I
    //   409: bipush 6
    //   411: if_icmpne +28 -> 439
    //   414: aload 4
    //   416: invokevirtual 737	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   419: iconst_0
    //   420: iconst_3
    //   421: invokevirtual 619	java/lang/String:substring	(II)Ljava/lang/String;
    //   424: putstatic 138	com/tapjoy/TapjoyConnectCore:mobileCountryCode	Ljava/lang/String;
    //   427: aload 4
    //   429: invokevirtual 737	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   432: iconst_3
    //   433: invokevirtual 569	java/lang/String:substring	(I)Ljava/lang/String;
    //   436: putstatic 140	com/tapjoy/TapjoyConnectCore:mobileNetworkCode	Ljava/lang/String;
    //   439: aload_0
    //   440: ldc_w 739
    //   443: invokespecial 742	com/tapjoy/TapjoyConnectCore:isPermissionGranted	(Ljava/lang/String;)Z
    //   446: istore_3
    //   447: iload_3
    //   448: ifeq +917 -> 1365
    //   451: ldc_w 744
    //   454: invokestatic 242	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   457: ifnull +723 -> 1180
    //   460: ldc_w 744
    //   463: invokestatic 242	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   466: invokevirtual 248	java/lang/String:length	()I
    //   469: ifle +711 -> 1180
    //   472: ldc_w 744
    //   475: invokestatic 242	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   478: putstatic 98	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   481: ldc 14
    //   483: new 206	java/lang/StringBuilder
    //   486: dup
    //   487: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   490: ldc_w 746
    //   493: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   496: getstatic 98	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   499: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   502: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   505: invokestatic 230	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   508: iconst_0
    //   509: istore_1
    //   510: getstatic 98	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   513: ifnonnull +716 -> 1229
    //   516: ldc 14
    //   518: ldc_w 748
    //   521: invokestatic 308	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   524: ldc 14
    //   526: new 206	java/lang/StringBuilder
    //   529: dup
    //   530: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   533: ldc_w 750
    //   536: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   539: getstatic 753	android/os/Build$VERSION:SDK_INT	I
    //   542: invokevirtual 320	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   545: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   548: invokestatic 230	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   551: iload_1
    //   552: istore_2
    //   553: getstatic 753	android/os/Build$VERSION:SDK_INT	I
    //   556: bipush 9
    //   558: if_icmplt +42 -> 600
    //   561: ldc 14
    //   563: ldc_w 755
    //   566: invokestatic 230	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   569: aload_0
    //   570: invokevirtual 758	com/tapjoy/TapjoyConnectCore:getSerial	()Ljava/lang/String;
    //   573: astore 4
    //   575: iload_1
    //   576: ifne +8 -> 584
    //   579: aload 4
    //   581: putstatic 98	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   584: getstatic 98	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   587: ifnonnull +703 -> 1290
    //   590: ldc 14
    //   592: ldc_w 760
    //   595: invokestatic 308	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   598: iload_1
    //   599: istore_2
    //   600: iload_2
    //   601: ifeq +12 -> 613
    //   604: getstatic 98	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   607: invokestatic 302	com/tapjoy/TapjoyUtil:SHA256	(Ljava/lang/String;)Ljava/lang/String;
    //   610: putstatic 100	com/tapjoy/TapjoyConnectCore:sha2DeviceID	Ljava/lang/String;
    //   613: getstatic 753	android/os/Build$VERSION:SDK_INT	I
    //   616: iconst_3
    //   617: if_icmple +67 -> 684
    //   620: new 762	com/tapjoy/TapjoyDisplayMetricsUtil
    //   623: dup
    //   624: getstatic 82	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   627: invokespecial 764	com/tapjoy/TapjoyDisplayMetricsUtil:<init>	(Landroid/content/Context;)V
    //   630: astore 4
    //   632: new 206	java/lang/StringBuilder
    //   635: dup
    //   636: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   639: ldc 92
    //   641: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   644: aload 4
    //   646: invokevirtual 767	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensity	()I
    //   649: invokevirtual 320	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   652: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   655: putstatic 124	com/tapjoy/TapjoyConnectCore:deviceScreenDensity	Ljava/lang/String;
    //   658: new 206	java/lang/StringBuilder
    //   661: dup
    //   662: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   665: ldc 92
    //   667: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   670: aload 4
    //   672: invokevirtual 770	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenLayoutSize	()I
    //   675: invokevirtual 320	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   678: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   681: putstatic 126	com/tapjoy/TapjoyConnectCore:deviceScreenLayoutSize	Ljava/lang/String;
    //   684: aload_0
    //   685: ldc_w 772
    //   688: invokespecial 742	com/tapjoy/TapjoyConnectCore:isPermissionGranted	(Ljava/lang/String;)Z
    //   691: istore_3
    //   692: iload_3
    //   693: ifeq +751 -> 1444
    //   696: getstatic 82	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   699: ldc_w 357
    //   702: invokevirtual 332	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   705: checkcast 774	android/net/wifi/WifiManager
    //   708: astore 4
    //   710: aload 4
    //   712: ifnull +46 -> 758
    //   715: aload 4
    //   717: invokevirtual 778	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   720: astore 4
    //   722: aload 4
    //   724: ifnull +34 -> 758
    //   727: aload 4
    //   729: invokevirtual 782	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   732: putstatic 102	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   735: getstatic 102	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   738: ifnull +20 -> 758
    //   741: getstatic 102	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   744: ldc_w 314
    //   747: ldc 92
    //   749: invokevirtual 786	java/lang/String:replace	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   752: invokevirtual 684	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   755: putstatic 102	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   758: aload_0
    //   759: ldc_w 788
    //   762: ldc_w 790
    //   765: invokevirtual 794	com/tapjoy/TapjoyConnectCore:detectCapability	(Ljava/lang/String;Ljava/lang/String;)Z
    //   768: putstatic 130	com/tapjoy/TapjoyConnectCore:deviceLocation	Z
    //   771: aload_0
    //   772: ldc_w 796
    //   775: invokevirtual 799	com/tapjoy/TapjoyConnectCore:detectSharingApplication	(Ljava/lang/String;)Z
    //   778: putstatic 168	com/tapjoy/TapjoyConnectCore:shareFacebook	Z
    //   781: aload_0
    //   782: ldc_w 801
    //   785: invokevirtual 799	com/tapjoy/TapjoyConnectCore:detectSharingApplication	(Ljava/lang/String;)Z
    //   788: putstatic 170	com/tapjoy/TapjoyConnectCore:shareTwitter	Z
    //   791: aload_0
    //   792: ldc_w 803
    //   795: invokevirtual 799	com/tapjoy/TapjoyConnectCore:detectSharingApplication	(Ljava/lang/String;)Z
    //   798: putstatic 172	com/tapjoy/TapjoyConnectCore:shareGooglePlus	Z
    //   801: aload_0
    //   802: ldc_w 805
    //   805: invokevirtual 799	com/tapjoy/TapjoyConnectCore:detectSharingApplication	(Ljava/lang/String;)Z
    //   808: putstatic 174	com/tapjoy/TapjoyConnectCore:shareLinkedIn	Z
    //   811: ldc_w 471
    //   814: invokestatic 242	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   817: ifnull +73 -> 890
    //   820: ldc_w 471
    //   823: invokestatic 242	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   826: invokevirtual 248	java/lang/String:length	()I
    //   829: ifle +61 -> 890
    //   832: ldc_w 471
    //   835: invokestatic 242	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   838: putstatic 146	com/tapjoy/TapjoyConnectCore:storeName	Ljava/lang/String;
    //   841: new 807	java/util/ArrayList
    //   844: dup
    //   845: getstatic 810	com/tapjoy/TapjoyConnectFlag:STORE_ARRAY	[Ljava/lang/String;
    //   848: invokestatic 816	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   851: invokespecial 819	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   854: getstatic 146	com/tapjoy/TapjoyConnectCore:storeName	Ljava/lang/String;
    //   857: invokevirtual 820	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   860: ifne +30 -> 890
    //   863: ldc 14
    //   865: new 206	java/lang/StringBuilder
    //   868: dup
    //   869: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   872: ldc_w 822
    //   875: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   878: getstatic 146	com/tapjoy/TapjoyConnectCore:storeName	Ljava/lang/String;
    //   881: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   884: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   887: invokestatic 825	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   890: aload_0
    //   891: getstatic 146	com/tapjoy/TapjoyConnectCore:storeName	Ljava/lang/String;
    //   894: invokevirtual 828	com/tapjoy/TapjoyConnectCore:detectStore	(Ljava/lang/String;)Z
    //   897: putstatic 176	com/tapjoy/TapjoyConnectCore:storeView	Z
    //   900: new 206	java/lang/StringBuilder
    //   903: dup
    //   904: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   907: ldc_w 746
    //   910: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   913: getstatic 98	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   916: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   919: astore 5
    //   921: ldc_w 744
    //   924: invokestatic 242	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   927: ifnull +993 -> 1920
    //   930: ldc_w 744
    //   933: invokestatic 242	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   936: invokevirtual 248	java/lang/String:length	()I
    //   939: ifle +981 -> 1920
    //   942: ldc_w 830
    //   945: astore 4
    //   947: ldc 14
    //   949: aload 5
    //   951: aload 4
    //   953: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   956: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   959: invokestatic 230	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   962: ldc 14
    //   964: new 206	java/lang/StringBuilder
    //   967: dup
    //   968: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   971: ldc_w 832
    //   974: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   977: getstatic 100	com/tapjoy/TapjoyConnectCore:sha2DeviceID	Ljava/lang/String;
    //   980: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   983: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   986: invokestatic 230	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   989: invokestatic 517	com/tapjoy/TapjoyConnectCore:getGenericURLParams	()Ljava/util/Map;
    //   992: invokeinterface 836 1 0
    //   997: invokeinterface 839 1 0
    //   1002: astore 4
    //   1004: aload 4
    //   1006: invokeinterface 596 1 0
    //   1011: ifeq +546 -> 1557
    //   1014: aload 4
    //   1016: invokeinterface 600 1 0
    //   1021: checkcast 841	java/util/Map$Entry
    //   1024: astore 5
    //   1026: ldc 14
    //   1028: new 206	java/lang/StringBuilder
    //   1031: dup
    //   1032: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   1035: aload 5
    //   1037: invokeinterface 844 1 0
    //   1042: checkcast 244	java/lang/String
    //   1045: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1048: ldc_w 846
    //   1051: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1054: aload 5
    //   1056: invokeinterface 849 1 0
    //   1061: checkcast 244	java/lang/String
    //   1064: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1067: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1070: invokestatic 230	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1073: goto -69 -> 1004
    //   1076: astore 4
    //   1078: ldc 14
    //   1080: new 206	java/lang/StringBuilder
    //   1083: dup
    //   1084: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   1087: ldc_w 851
    //   1090: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1093: aload 4
    //   1095: invokevirtual 305	java/lang/Exception:toString	()Ljava/lang/String;
    //   1098: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1101: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1104: invokestatic 308	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1107: aload 4
    //   1109: invokevirtual 854	java/lang/Exception:printStackTrace	()V
    //   1112: getstatic 98	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1115: ifnull +748 -> 1863
    //   1118: getstatic 98	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1121: invokevirtual 248	java/lang/String:length	()I
    //   1124: ifle +739 -> 1863
    //   1127: getstatic 98	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1130: putstatic 128	com/tapjoy/TapjoyConnectCore:userID	Ljava/lang/String;
    //   1133: iconst_1
    //   1134: ireturn
    //   1135: ldc 14
    //   1137: ldc_w 856
    //   1140: invokestatic 230	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1143: goto -938 -> 205
    //   1146: astore 5
    //   1148: ldc 14
    //   1150: new 206	java/lang/StringBuilder
    //   1153: dup
    //   1154: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   1157: ldc_w 858
    //   1160: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1163: aload 5
    //   1165: invokevirtual 305	java/lang/Exception:toString	()Ljava/lang/String;
    //   1168: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1171: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1174: invokestatic 308	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1177: goto -972 -> 205
    //   1180: aload 4
    //   1182: invokevirtual 861	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   1185: putstatic 98	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1188: goto -707 -> 481
    //   1191: astore 4
    //   1193: ldc 14
    //   1195: new 206	java/lang/StringBuilder
    //   1198: dup
    //   1199: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   1202: ldc_w 863
    //   1205: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1208: aload 4
    //   1210: invokevirtual 305	java/lang/Exception:toString	()Ljava/lang/String;
    //   1213: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1216: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1219: invokestatic 308	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1222: aconst_null
    //   1223: putstatic 98	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1226: goto -613 -> 613
    //   1229: getstatic 98	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1232: invokevirtual 248	java/lang/String:length	()I
    //   1235: ifeq +27 -> 1262
    //   1238: getstatic 98	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1241: ldc_w 865
    //   1244: invokevirtual 411	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1247: ifne +15 -> 1262
    //   1250: getstatic 98	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1253: ldc_w 867
    //   1256: invokevirtual 411	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1259: ifeq +14 -> 1273
    //   1262: ldc 14
    //   1264: ldc_w 869
    //   1267: invokestatic 308	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1270: goto -746 -> 524
    //   1273: getstatic 98	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1276: invokestatic 714	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1279: invokevirtual 872	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   1282: putstatic 98	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1285: iconst_1
    //   1286: istore_1
    //   1287: goto -763 -> 524
    //   1290: getstatic 98	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1293: invokevirtual 248	java/lang/String:length	()I
    //   1296: ifeq +39 -> 1335
    //   1299: getstatic 98	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1302: ldc_w 865
    //   1305: invokevirtual 411	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1308: ifne +27 -> 1335
    //   1311: getstatic 98	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1314: ldc_w 867
    //   1317: invokevirtual 411	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1320: ifne +15 -> 1335
    //   1323: getstatic 98	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1326: ldc_w 874
    //   1329: invokevirtual 411	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1332: ifeq +16 -> 1348
    //   1335: ldc 14
    //   1337: ldc_w 876
    //   1340: invokestatic 308	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1343: iload_1
    //   1344: istore_2
    //   1345: goto -745 -> 600
    //   1348: getstatic 98	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1351: invokestatic 714	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   1354: invokevirtual 872	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   1357: putstatic 98	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1360: iconst_1
    //   1361: istore_2
    //   1362: goto -762 -> 600
    //   1365: ldc 14
    //   1367: ldc_w 878
    //   1370: invokestatic 230	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1373: goto -760 -> 613
    //   1376: astore 4
    //   1378: ldc 14
    //   1380: new 206	java/lang/StringBuilder
    //   1383: dup
    //   1384: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   1387: ldc_w 880
    //   1390: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1393: aload 4
    //   1395: invokevirtual 305	java/lang/Exception:toString	()Ljava/lang/String;
    //   1398: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1401: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1404: invokestatic 308	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1407: goto -723 -> 684
    //   1410: astore 4
    //   1412: ldc 14
    //   1414: new 206	java/lang/StringBuilder
    //   1417: dup
    //   1418: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   1421: ldc_w 882
    //   1424: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1427: aload 4
    //   1429: invokevirtual 305	java/lang/Exception:toString	()Ljava/lang/String;
    //   1432: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1435: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1438: invokestatic 308	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1441: goto -683 -> 758
    //   1444: ldc 14
    //   1446: ldc_w 884
    //   1449: invokestatic 230	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1452: goto -694 -> 758
    //   1455: astore 4
    //   1457: ldc 14
    //   1459: new 206	java/lang/StringBuilder
    //   1462: dup
    //   1463: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   1466: ldc_w 886
    //   1469: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1472: aload 4
    //   1474: invokevirtual 305	java/lang/Exception:toString	()Ljava/lang/String;
    //   1477: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1480: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1483: invokestatic 308	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1486: goto -715 -> 771
    //   1489: astore 4
    //   1491: ldc 14
    //   1493: new 206	java/lang/StringBuilder
    //   1496: dup
    //   1497: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   1500: ldc_w 888
    //   1503: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1506: aload 4
    //   1508: invokevirtual 305	java/lang/Exception:toString	()Ljava/lang/String;
    //   1511: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1514: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1517: invokestatic 308	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1520: goto -709 -> 811
    //   1523: astore 4
    //   1525: ldc 14
    //   1527: new 206	java/lang/StringBuilder
    //   1530: dup
    //   1531: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   1534: ldc_w 890
    //   1537: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1540: aload 4
    //   1542: invokevirtual 305	java/lang/Exception:toString	()Ljava/lang/String;
    //   1545: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1548: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1551: invokestatic 308	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1554: goto -654 -> 900
    //   1557: getstatic 182	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   1560: ifnull -448 -> 1112
    //   1563: ldc 14
    //   1565: ldc_w 892
    //   1568: invokestatic 230	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1571: ldc 14
    //   1573: ldc_w 894
    //   1576: invokestatic 230	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1579: getstatic 182	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   1582: invokevirtual 895	java/util/Hashtable:entrySet	()Ljava/util/Set;
    //   1585: invokeinterface 839 1 0
    //   1590: astore 4
    //   1592: aload 4
    //   1594: invokeinterface 596 1 0
    //   1599: ifeq +123 -> 1722
    //   1602: aload 4
    //   1604: invokeinterface 600 1 0
    //   1609: checkcast 841	java/util/Map$Entry
    //   1612: astore 5
    //   1614: ldc 14
    //   1616: new 206	java/lang/StringBuilder
    //   1619: dup
    //   1620: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   1623: ldc_w 897
    //   1626: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1629: aload 5
    //   1631: invokeinterface 844 1 0
    //   1636: checkcast 244	java/lang/String
    //   1639: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1642: ldc_w 899
    //   1645: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1648: aload 5
    //   1650: invokeinterface 849 1 0
    //   1655: checkcast 244	java/lang/String
    //   1658: invokestatic 904	android/net/Uri:encode	(Ljava/lang/String;)Ljava/lang/String;
    //   1661: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1664: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1667: invokestatic 230	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1670: aload 5
    //   1672: invokeinterface 844 1 0
    //   1677: checkcast 244	java/lang/String
    //   1680: ldc_w 405
    //   1683: invokevirtual 411	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1686: ifeq -94 -> 1592
    //   1689: getstatic 154	com/tapjoy/TapjoyConnectCore:sdkType	Ljava/lang/String;
    //   1692: ldc_w 906
    //   1695: invokevirtual 411	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1698: ifne -106 -> 1592
    //   1701: ldc 14
    //   1703: ldc_w 908
    //   1706: invokestatic 825	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   1709: getstatic 182	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   1712: ldc_w 405
    //   1715: invokevirtual 911	java/util/Hashtable:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1718: pop
    //   1719: goto -127 -> 1592
    //   1722: ldc_w 913
    //   1725: invokestatic 242	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1728: ifnull +24 -> 1752
    //   1731: ldc_w 913
    //   1734: invokestatic 242	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1737: invokevirtual 248	java/lang/String:length	()I
    //   1740: ifle +12 -> 1752
    //   1743: ldc_w 913
    //   1746: invokestatic 242	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1749: putstatic 158	com/tapjoy/TapjoyConnectCore:hostURL	Ljava/lang/String;
    //   1752: getstatic 158	com/tapjoy/TapjoyConnectCore:hostURL	Ljava/lang/String;
    //   1755: getstatic 158	com/tapjoy/TapjoyConnectCore:hostURL	Ljava/lang/String;
    //   1758: ldc_w 915
    //   1761: invokevirtual 918	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   1764: ldc_w 915
    //   1767: invokevirtual 248	java/lang/String:length	()I
    //   1770: iadd
    //   1771: getstatic 158	com/tapjoy/TapjoyConnectCore:hostURL	Ljava/lang/String;
    //   1774: ldc_w 920
    //   1777: invokevirtual 923	java/lang/String:lastIndexOf	(Ljava/lang/String;)I
    //   1780: invokevirtual 619	java/lang/String:substring	(II)Ljava/lang/String;
    //   1783: putstatic 160	com/tapjoy/TapjoyConnectCore:redirectDomain	Ljava/lang/String;
    //   1786: ldc 14
    //   1788: new 206	java/lang/StringBuilder
    //   1791: dup
    //   1792: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   1795: ldc_w 925
    //   1798: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1801: getstatic 158	com/tapjoy/TapjoyConnectCore:hostURL	Ljava/lang/String;
    //   1804: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1807: ldc_w 927
    //   1810: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1813: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1816: invokestatic 230	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1819: ldc 14
    //   1821: new 206	java/lang/StringBuilder
    //   1824: dup
    //   1825: invokespecial 207	java/lang/StringBuilder:<init>	()V
    //   1828: ldc_w 929
    //   1831: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1834: getstatic 160	com/tapjoy/TapjoyConnectCore:redirectDomain	Ljava/lang/String;
    //   1837: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1840: ldc_w 927
    //   1843: invokevirtual 213	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1846: invokevirtual 224	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1849: invokestatic 230	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1852: ldc 14
    //   1854: ldc_w 894
    //   1857: invokestatic 230	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1860: goto -748 -> 1112
    //   1863: getstatic 102	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   1866: ifnull +21 -> 1887
    //   1869: getstatic 102	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   1872: invokevirtual 248	java/lang/String:length	()I
    //   1875: ifle +12 -> 1887
    //   1878: getstatic 102	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   1881: putstatic 128	com/tapjoy/TapjoyConnectCore:userID	Ljava/lang/String;
    //   1884: goto -751 -> 1133
    //   1887: getstatic 94	com/tapjoy/TapjoyConnectCore:androidID	Ljava/lang/String;
    //   1890: ifnull +21 -> 1911
    //   1893: getstatic 94	com/tapjoy/TapjoyConnectCore:androidID	Ljava/lang/String;
    //   1896: invokevirtual 248	java/lang/String:length	()I
    //   1899: ifle +12 -> 1911
    //   1902: getstatic 94	com/tapjoy/TapjoyConnectCore:androidID	Ljava/lang/String;
    //   1905: putstatic 128	com/tapjoy/TapjoyConnectCore:userID	Ljava/lang/String;
    //   1908: goto -775 -> 1133
    //   1911: iconst_0
    //   1912: ireturn
    //   1913: iload_1
    //   1914: iconst_1
    //   1915: iadd
    //   1916: istore_1
    //   1917: goto -1830 -> 87
    //   1920: ldc 92
    //   1922: astore 4
    //   1924: goto -977 -> 947
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1927	0	this	TapjoyConnectCore
    //   86	1831	1	i	int
    //   84	1278	2	j	int
    //   446	247	3	bool	boolean
    //   24	991	4	localObject1	Object
    //   1076	105	4	localException1	Exception
    //   1191	18	4	localException2	Exception
    //   1376	18	4	localException3	Exception
    //   1410	18	4	localException4	Exception
    //   1455	18	4	localException5	Exception
    //   1489	18	4	localException6	Exception
    //   1523	18	4	localException7	Exception
    //   1590	333	4	localObject2	Object
    //   61	994	5	localObject3	Object
    //   1146	18	5	localException8	Exception
    //   1612	59	5	localEntry	java.util.Map.Entry
    //   79	14	6	arrayOfString	String[]
    //   96	91	7	str	String
    //   127	62	8	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   233	263	1076	java/lang/Exception
    //   263	360	1076	java/lang/Exception
    //   365	414	1076	java/lang/Exception
    //   414	439	1076	java/lang/Exception
    //   439	447	1076	java/lang/Exception
    //   684	692	1076	java/lang/Exception
    //   811	890	1076	java/lang/Exception
    //   900	942	1076	java/lang/Exception
    //   947	1004	1076	java/lang/Exception
    //   1004	1073	1076	java/lang/Exception
    //   1193	1226	1076	java/lang/Exception
    //   1365	1373	1076	java/lang/Exception
    //   1378	1407	1076	java/lang/Exception
    //   1412	1441	1076	java/lang/Exception
    //   1444	1452	1076	java/lang/Exception
    //   1457	1486	1076	java/lang/Exception
    //   1491	1520	1076	java/lang/Exception
    //   1525	1554	1076	java/lang/Exception
    //   1557	1592	1076	java/lang/Exception
    //   1592	1719	1076	java/lang/Exception
    //   1722	1752	1076	java/lang/Exception
    //   1752	1860	1076	java/lang/Exception
    //   26	42	1146	java/lang/Exception
    //   47	63	1146	java/lang/Exception
    //   68	85	1146	java/lang/Exception
    //   98	129	1146	java/lang/Exception
    //   134	141	1146	java/lang/Exception
    //   146	194	1146	java/lang/Exception
    //   197	205	1146	java/lang/Exception
    //   1135	1143	1146	java/lang/Exception
    //   451	481	1191	java/lang/Exception
    //   481	508	1191	java/lang/Exception
    //   510	524	1191	java/lang/Exception
    //   524	551	1191	java/lang/Exception
    //   553	575	1191	java/lang/Exception
    //   579	584	1191	java/lang/Exception
    //   584	598	1191	java/lang/Exception
    //   604	613	1191	java/lang/Exception
    //   1180	1188	1191	java/lang/Exception
    //   1229	1262	1191	java/lang/Exception
    //   1262	1270	1191	java/lang/Exception
    //   1273	1285	1191	java/lang/Exception
    //   1290	1335	1191	java/lang/Exception
    //   1335	1343	1191	java/lang/Exception
    //   1348	1360	1191	java/lang/Exception
    //   613	684	1376	java/lang/Exception
    //   696	710	1410	java/lang/Exception
    //   715	722	1410	java/lang/Exception
    //   727	758	1410	java/lang/Exception
    //   758	771	1455	java/lang/Exception
    //   771	811	1489	java/lang/Exception
    //   890	900	1523	java/lang/Exception
  }
  
  private boolean isPermissionGranted(String paramString)
  {
    return context.getPackageManager().checkPermission(paramString, context.getPackageName()) == 0;
  }
  
  public static void removeOfflineLog(String paramString)
  {
    SharedPreferences.Editor localEditor = context.getSharedPreferences("tapjoyOfflineLog", 0).edit();
    localEditor.remove(paramString);
    localEditor.commit();
  }
  
  public static void requestTapjoyConnect(Context paramContext, String paramString1, String paramString2)
  {
    requestTapjoyConnect(paramContext, paramString1, paramString2, null);
  }
  
  public static void requestTapjoyConnect(Context paramContext, String paramString1, String paramString2, Hashtable<String, String> paramHashtable)
  {
    requestTapjoyConnect(paramContext, paramString1, paramString2, paramHashtable, null);
  }
  
  public static void requestTapjoyConnect(Context paramContext, String paramString1, String paramString2, Hashtable<String, String> paramHashtable, TapjoyConnectNotifier paramTapjoyConnectNotifier)
  {
    appID = paramString1;
    secretKey = paramString2;
    connectFlags = paramHashtable;
    connectNotifier = paramTapjoyConnectNotifier;
    tapjoyConnectCore = new TapjoyConnectCore(paramContext);
  }
  
  public static void saveOfflineLog(String paramString)
  {
    paramString = paramString + "&original_timestamp=" + System.currentTimeMillis() / 1000L;
    paramString = paramString + "&offline=true";
    SharedPreferences.Editor localEditor = context.getSharedPreferences("tapjoyOfflineLog", 0).edit();
    localEditor.putString(Long.toString(System.currentTimeMillis()), paramString);
    localEditor.commit();
  }
  
  public static void sendOfflineLogs()
  {
    new Thread(new TapjoyConnectCore.2()).start();
  }
  
  public static void setHostURL(String paramString)
  {
    hostURL = paramString;
  }
  
  public static void setPlugin(String paramString)
  {
    plugin = paramString;
  }
  
  public static void setSDKType(String paramString)
  {
    sdkType = paramString;
  }
  
  public static void setUserID(String paramString)
  {
    userID = paramString;
    TapjoyLog.i("TapjoyConnect", "URL parameters: " + getURLParams());
    new Thread(new TapjoyConnectCore.1()).start();
  }
  
  public static void setVideoEnabled(boolean paramBoolean)
  {
    videoEnabled = paramBoolean;
  }
  
  public static void setVideoIDs(String paramString)
  {
    videoIDs = paramString;
  }
  
  private boolean validatePermissions()
  {
    Iterator localIterator = new ArrayList(Arrays.asList(new String[] { "android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE" })).iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (!isPermissionGranted(str))
      {
        Log.e("TapjoyConnect", "Missing required permission: " + str);
        return false;
      }
    }
    return true;
  }
  
  public static void viewDidClose(int paramInt)
  {
    if (viewNotifier != null) {
      viewNotifier.viewDidClose(paramInt);
    }
  }
  
  public static void viewDidOpen(int paramInt)
  {
    if (viewNotifier != null) {
      viewNotifier.viewDidOpen(paramInt);
    }
  }
  
  public static void viewWillClose(int paramInt)
  {
    if (viewNotifier != null) {
      viewNotifier.viewWillClose(paramInt);
    }
  }
  
  public static void viewWillOpen(int paramInt)
  {
    if (viewNotifier != null) {
      viewNotifier.viewWillOpen(paramInt);
    }
  }
  
  public void actionComplete(String paramString)
  {
    TapjoyLog.i("TapjoyConnect", "actionComplete: " + paramString);
    Map localMap = getParamsWithoutAppID();
    TapjoyUtil.safePut(localMap, "app_id", paramString, true);
    localMap.putAll(getTimeStampAndVerifierParams());
    TapjoyLog.i("TapjoyConnect", "PPA URL parameters: " + localMap);
    new Thread(new TapjoyConnectCore.PPAThread(this, localMap)).start();
  }
  
  public void appPause()
  {
    this.appPaused = true;
  }
  
  public void appResume()
  {
    if (this.appPaused)
    {
      generateSessionID();
      this.appPaused = false;
    }
  }
  
  public void callConnect()
  {
    new Thread(new TapjoyConnectCore.ConnectThread(this)).start();
  }
  
  protected boolean detectApplication(String paramString)
  {
    boolean bool2 = false;
    Iterator localIterator = context.getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      bool1 = bool2;
      if (!localIterator.hasNext()) {
        break;
      }
    } while (!((ApplicationInfo)localIterator.next()).packageName.startsWith(paramString));
    boolean bool1 = true;
    return bool1;
  }
  
  protected boolean detectCapability(String paramString1, String paramString2)
  {
    PackageManager localPackageManager = context.getPackageManager();
    FeatureInfo[] arrayOfFeatureInfo = localPackageManager.getSystemAvailableFeatures();
    int j = arrayOfFeatureInfo.length;
    int i = 0;
    while (i < j)
    {
      if (arrayOfFeatureInfo[i].name.matches(paramString1))
      {
        if (paramString2 == null) {}
        while (localPackageManager.checkPermission(paramString2, context.getPackageName()) == 0) {
          return true;
        }
        return false;
      }
      i += 1;
    }
    return false;
  }
  
  protected boolean detectSharingApplication(String paramString)
  {
    boolean bool2 = false;
    Object localObject = context.getPackageManager();
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("text/plain");
    localObject = ((PackageManager)localObject).queryIntentActivities(localIntent, 0).iterator();
    do
    {
      bool1 = bool2;
      if (!((Iterator)localObject).hasNext()) {
        break;
      }
    } while (!((ResolveInfo)((Iterator)localObject).next()).activityInfo.packageName.startsWith(paramString));
    boolean bool1 = true;
    return bool1;
  }
  
  protected boolean detectStore(String paramString)
  {
    boolean bool = false;
    PackageManager localPackageManager = context.getPackageManager();
    Intent localIntent = new Intent("android.intent.action.VIEW");
    if (paramString.length() < 1)
    {
      localIntent.setData(Uri.parse("market://details"));
      if (localPackageManager.queryIntentActivities(localIntent, 0).size() > 0) {
        bool = true;
      }
    }
    for (;;)
    {
      if (bool) {}
      return bool;
      if (paramString.equals("gfan")) {
        bool = detectApplication("com.mappn.gfan");
      } else if (paramString.equals("skt")) {
        bool = detectApplication("com.skt.skaf.TSCINSTALL");
      }
    }
  }
  
  public void enablePaidAppWithActionID(String paramString)
  {
    TapjoyLog.i("TapjoyConnect", "enablePaidAppWithActionID: " + paramString);
    paidAppActionID = paramString;
    this.elapsed_time = context.getSharedPreferences("tjcPrefrences", 0).getLong("tapjoy_elapsed_time", 0L);
    TapjoyLog.i("TapjoyConnect", "paidApp elapsed: " + this.elapsed_time);
    if (this.elapsed_time >= 900000L) {
      if ((paidAppActionID != null) && (paidAppActionID.length() > 0))
      {
        TapjoyLog.i("TapjoyConnect", "Calling PPA actionComplete...");
        actionComplete(paidAppActionID);
      }
    }
    while (this.timer != null) {
      return;
    }
    this.timer = new Timer();
    this.timer.schedule(new TapjoyConnectCore.PaidAppTimerTask(this, null), 10000L, 10000L);
  }
  
  public float getCurrencyMultiplier()
  {
    return currencyMultiplier;
  }
  
  public String getSerial()
  {
    String str2 = null;
    String str1 = str2;
    try
    {
      Field localField = Class.forName("android.os.Build").getDeclaredField("SERIAL");
      str1 = str2;
      if (!localField.isAccessible())
      {
        str1 = str2;
        localField.setAccessible(true);
      }
      str1 = str2;
      str2 = localField.get(Build.class).toString();
      str1 = str2;
      TapjoyLog.i("TapjoyConnect", "serial: " + str2);
      return str2;
    }
    catch (Exception localException)
    {
      TapjoyLog.e("TapjoyConnect", localException.toString());
    }
    return str1;
  }
  
  public boolean isInitialized()
  {
    return this.initialized;
  }
  
  public void release()
  {
    tapjoyConnectCore = null;
    tapjoyURLConnection = null;
    TapjoyLog.i("TapjoyConnect", "Releasing core static instance.");
  }
  
  public void setCurrencyMultiplier(float paramFloat)
  {
    TapjoyLog.i("TapjoyConnect", "setVirtualCurrencyMultiplier: " + paramFloat);
    currencyMultiplier = paramFloat;
  }
  
  public void setTapjoyViewNotifier(TapjoyViewNotifier paramTapjoyViewNotifier)
  {
    viewNotifier = paramTapjoyViewNotifier;
  }
}
