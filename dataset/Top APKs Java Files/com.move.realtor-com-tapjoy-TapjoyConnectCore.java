package com.tapjoy;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import org.w3c.dom.Document;

public class TapjoyConnectCore
{
  public static final String TAPJOY_CONNECT = "TapjoyConnect";
  private static String androidID;
  private static String appID;
  private static String appVersion;
  private static String carrierCountryCode;
  private static String carrierName;
  private static String clientPackage;
  private static Hashtable<String, String> connectFlags = null;
  private static TapjoyConnectNotifier connectNotifier;
  private static String connectionType;
  private static Context context = null;
  private static float currencyMultiplier;
  private static String deviceCountryCode;
  private static String deviceID;
  private static String deviceLanguage;
  private static String deviceManufacturer;
  private static String deviceModel;
  private static String deviceOSVersion;
  private static String deviceScreenDensity;
  private static String deviceScreenLayoutSize;
  private static String deviceType;
  private static String libraryVersion;
  private static String macAddress;
  private static String matchingPackageNames = "";
  private static String mobileCountryCode;
  private static String mobileNetworkCode;
  private static String paidAppActionID;
  private static String platformName;
  private static String plugin;
  private static String referralURL;
  private static String sdkType;
  private static String secretKey;
  private static String serialID;
  private static String sha1MacAddress;
  private static String sha2DeviceID;
  private static String storeName;
  private static TapjoyConnectCore tapjoyConnectCore = null;
  private static TapjoyURLConnection tapjoyURLConnection = null;
  private static String userID;
  private static boolean videoEnabled;
  private static String videoIDs;
  private long elapsed_time = 0L;
  private Timer timer = null;
  
  static
  {
    connectNotifier = null;
    androidID = "";
    deviceID = "";
    sha2DeviceID = "";
    macAddress = "";
    sha1MacAddress = "";
    serialID = "";
    deviceModel = "";
    deviceManufacturer = "";
    deviceType = "";
    deviceOSVersion = "";
    deviceCountryCode = "";
    deviceLanguage = "";
    appID = "";
    appVersion = "";
    libraryVersion = "";
    deviceScreenDensity = "";
    deviceScreenLayoutSize = "";
    userID = "";
    platformName = "";
    carrierName = "";
    carrierCountryCode = "";
    mobileCountryCode = "";
    mobileNetworkCode = "";
    connectionType = "";
    storeName = "";
    secretKey = "";
    clientPackage = "";
    referralURL = "";
    plugin = "native";
    sdkType = "";
    videoEnabled = false;
    videoIDs = "";
    currencyMultiplier = 1.0F;
    paidAppActionID = null;
  }
  
  public TapjoyConnectCore(Context paramContext)
  {
    context = paramContext;
    tapjoyURLConnection = new TapjoyURLConnection();
    init();
    TapjoyLog.i("TapjoyConnect", "URL parameters: " + getURLParams());
    callConnect();
    if ((getFlagValue("user_id") != null) && (getFlagValue("user_id").length() > 0))
    {
      TapjoyLog.i("TapjoyConnect", "Setting userID to: " + getFlagValue("user_id"));
      setUserID(getFlagValue("user_id"));
    }
  }
  
  public static String getAppID()
  {
    return appID;
  }
  
  public static String getAwardPointsVerifier(long paramLong, int paramInt, String paramString)
  {
    try
    {
      paramString = TapjoyUtil.SHA256(appID + ":" + deviceID + ":" + paramLong + ":" + secretKey + ":" + paramInt + ":" + paramString);
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
  
  public static String getClientPackage()
  {
    return clientPackage;
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
  
  public static String getEventVerifier(String paramString)
  {
    try
    {
      paramString = TapjoyUtil.SHA256(appID + ":" + deviceID + ":" + secretKey + ":" + paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      TapjoyLog.e("TapjoyConnect", "getEventVerifier ERROR: " + paramString.toString());
    }
    return "";
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
  
  public static String getGenericURLParams()
  {
    String str = "" + "app_id=" + Uri.encode(appID) + "&";
    return str + getParamsWithoutAppID();
  }
  
  public static TapjoyConnectCore getInstance()
  {
    return tapjoyConnectCore;
  }
  
  public static int getLocalTapPointsTotal()
  {
    return context.getSharedPreferences("tjcPrefrences", 0).getInt("last_tap_points", 55537);
  }
  
  public static String getPackageNamesVerifier(long paramLong, String paramString)
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
  
  private static String getParamsWithoutAppID()
  {
    Object localObject1 = "" + "android_id=" + androidID + "&";
    if ((getFlagValue("sha_2_udid") != null) && (getFlagValue("sha_2_udid").equals("true"))) {}
    for (Object localObject2 = (String)localObject1 + "sha2_udid=" + Uri.encode(sha2DeviceID) + "&";; localObject2 = (String)localObject1 + "udid=" + Uri.encode(deviceID) + "&")
    {
      localObject1 = localObject2;
      if (macAddress != null)
      {
        localObject1 = localObject2;
        if (macAddress.length() > 0) {
          localObject1 = (String)localObject2 + "sha1_mac_address=" + Uri.encode(sha1MacAddress) + "&";
        }
      }
      localObject2 = localObject1;
      if (serialID != null)
      {
        localObject2 = localObject1;
        if (serialID.length() > 0) {
          localObject2 = (String)localObject1 + "serial_id=" + Uri.encode(serialID) + "&";
        }
      }
      localObject1 = (String)localObject2 + "device_name=" + Uri.encode(deviceModel) + "&";
      localObject1 = (String)localObject1 + "device_manufacturer=" + Uri.encode(deviceManufacturer) + "&";
      localObject1 = (String)localObject1 + "device_type=" + Uri.encode(deviceType) + "&";
      localObject1 = (String)localObject1 + "os_version=" + Uri.encode(deviceOSVersion) + "&";
      localObject1 = (String)localObject1 + "country_code=" + Uri.encode(deviceCountryCode) + "&";
      localObject1 = (String)localObject1 + "language_code=" + Uri.encode(deviceLanguage) + "&";
      localObject1 = (String)localObject1 + "app_version=" + Uri.encode(appVersion) + "&";
      localObject1 = (String)localObject1 + "library_version=" + Uri.encode(libraryVersion) + "&";
      localObject1 = (String)localObject1 + "platform=" + Uri.encode(platformName) + "&";
      localObject2 = (String)localObject1 + "display_multiplier=" + Uri.encode(Float.toString(currencyMultiplier));
      localObject1 = localObject2;
      if (carrierName.length() > 0)
      {
        localObject1 = (String)localObject2 + "&";
        localObject1 = (String)localObject1 + "carrier_name=" + Uri.encode(carrierName);
      }
      localObject2 = localObject1;
      if (carrierCountryCode.length() > 0)
      {
        localObject1 = (String)localObject1 + "&";
        localObject2 = (String)localObject1 + "carrier_country_code=" + Uri.encode(carrierCountryCode);
      }
      localObject1 = localObject2;
      if (mobileCountryCode.length() > 0)
      {
        localObject1 = (String)localObject2 + "&";
        localObject1 = (String)localObject1 + "mobile_country_code=" + Uri.encode(mobileCountryCode);
      }
      localObject2 = localObject1;
      if (mobileNetworkCode.length() > 0)
      {
        localObject1 = (String)localObject1 + "&";
        localObject2 = (String)localObject1 + "mobile_network_code=" + Uri.encode(mobileNetworkCode);
      }
      localObject1 = localObject2;
      if (deviceScreenDensity.length() > 0)
      {
        localObject1 = localObject2;
        if (deviceScreenLayoutSize.length() > 0)
        {
          localObject1 = (String)localObject2 + "&";
          localObject1 = (String)localObject1 + "screen_density=" + Uri.encode(deviceScreenDensity) + "&";
          localObject1 = (String)localObject1 + "screen_layout_size=" + Uri.encode(deviceScreenLayoutSize);
        }
      }
      connectionType = getConnectionType();
      localObject2 = localObject1;
      if (connectionType.length() > 0)
      {
        localObject1 = (String)localObject1 + "&";
        localObject2 = (String)localObject1 + "connection_type=" + Uri.encode(connectionType);
      }
      localObject1 = localObject2;
      if (plugin.length() > 0)
      {
        localObject1 = (String)localObject2 + "&";
        localObject1 = (String)localObject1 + "plugin=" + Uri.encode(plugin);
      }
      localObject2 = localObject1;
      if (sdkType.length() > 0)
      {
        localObject1 = (String)localObject1 + "&";
        localObject2 = (String)localObject1 + "sdk_type=" + Uri.encode(sdkType);
      }
      localObject1 = localObject2;
      if (storeName.length() > 0)
      {
        localObject1 = (String)localObject2 + "&";
        localObject1 = (String)localObject1 + "store_name=" + Uri.encode(storeName);
      }
      return localObject1;
    }
  }
  
  public static String getURLParams()
  {
    String str2 = getGenericURLParams() + "&";
    long l = System.currentTimeMillis() / 1000L;
    String str1 = getVerifier(l);
    str2 = str2 + "timestamp=" + l + "&";
    return str2 + "verifier=" + str1;
  }
  
  public static String getUserID()
  {
    return userID;
  }
  
  public static String getVerifier(long paramLong)
  {
    try
    {
      String str = TapjoyUtil.SHA256(appID + ":" + deviceID + ":" + paramLong + ":" + secretKey);
      return str;
    }
    catch (Exception localException)
    {
      TapjoyLog.e("TapjoyConnect", "getVerifier ERROR: " + localException.toString());
    }
    return "";
  }
  
  public static String getVideoParams()
  {
    String str = "";
    if (videoEnabled) {
      if (videoIDs.length() <= 0) {}
    }
    for (str = "video_offer_ids=" + videoIDs;; str = "hide_videos=true")
    {
      TapjoyLog.i("TapjoyConnect", "video parameters: " + str);
      return str;
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
      if ((paramString == null) || (!paramString.equals("true"))) {}
    }
    return true;
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
  private void init()
  {
    // Byte code:
    //   0: getstatic 75	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   3: invokevirtual 469	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   6: astore_3
    //   7: getstatic 155	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   10: ifnonnull +13 -> 23
    //   13: new 301	java/util/Hashtable
    //   16: dup
    //   17: invokespecial 517	java/util/Hashtable:<init>	()V
    //   20: putstatic 155	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   23: aload_3
    //   24: ifnull +160 -> 184
    //   27: aload_3
    //   28: getstatic 75	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   31: invokevirtual 520	android/content/Context:getPackageName	()Ljava/lang/String;
    //   34: sipush 128
    //   37: invokevirtual 524	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   40: astore 4
    //   42: aload 4
    //   44: ifnull +1894 -> 1938
    //   47: aload 4
    //   49: getfield 528	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   52: ifnull +1886 -> 1938
    //   55: getstatic 534	com/tapjoy/TapjoyConnectFlag:FLAG_ARRAY	[Ljava/lang/String;
    //   58: astore 5
    //   60: aload 5
    //   62: arraylength
    //   63: istore_2
    //   64: iconst_0
    //   65: istore_1
    //   66: iload_1
    //   67: iload_2
    //   68: if_icmpge +108 -> 176
    //   71: aload 5
    //   73: iload_1
    //   74: aaload
    //   75: astore 6
    //   77: aload 4
    //   79: getfield 528	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   82: new 174	java/lang/StringBuilder
    //   85: dup
    //   86: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   89: ldc_w 536
    //   92: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: aload 6
    //   97: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   103: invokevirtual 540	android/os/Bundle:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   106: astore 7
    //   108: aload 7
    //   110: ifnull +2206 -> 2316
    //   113: aload 7
    //   115: invokevirtual 541	java/lang/Object:toString	()Ljava/lang/String;
    //   118: astore 7
    //   120: aload 7
    //   122: ifnull +2194 -> 2316
    //   125: ldc 19
    //   127: new 174	java/lang/StringBuilder
    //   130: dup
    //   131: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   134: ldc_w 543
    //   137: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: aload 6
    //   142: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: ldc_w 545
    //   148: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: aload 7
    //   153: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   159: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   162: getstatic 155	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   165: aload 6
    //   167: aload 7
    //   169: invokevirtual 549	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   172: pop
    //   173: goto +2143 -> 2316
    //   176: ldc 19
    //   178: ldc_w 551
    //   181: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   184: ldc_w 553
    //   187: invokestatic 203	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   190: ifnull +22 -> 212
    //   193: ldc_w 553
    //   196: invokestatic 203	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   199: ldc_w 345
    //   202: invokevirtual 349	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   205: ifeq +7 -> 212
    //   208: iconst_1
    //   209: invokestatic 557	com/tapjoy/TapjoyLog:enableLogging	(Z)V
    //   212: getstatic 75	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   215: invokevirtual 561	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   218: ldc_w 563
    //   221: invokestatic 569	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   224: putstatic 85	com/tapjoy/TapjoyConnectCore:androidID	Ljava/lang/String;
    //   227: aload_3
    //   228: getstatic 75	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   231: invokevirtual 520	android/content/Context:getPackageName	()Ljava/lang/String;
    //   234: iconst_0
    //   235: invokevirtual 573	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   238: getfield 578	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   241: putstatic 111	com/tapjoy/TapjoyConnectCore:appVersion	Ljava/lang/String;
    //   244: ldc_w 580
    //   247: putstatic 101	com/tapjoy/TapjoyConnectCore:deviceType	Ljava/lang/String;
    //   250: ldc_w 580
    //   253: putstatic 121	com/tapjoy/TapjoyConnectCore:platformName	Ljava/lang/String;
    //   256: getstatic 585	android/os/Build:MODEL	Ljava/lang/String;
    //   259: putstatic 97	com/tapjoy/TapjoyConnectCore:deviceModel	Ljava/lang/String;
    //   262: getstatic 588	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   265: putstatic 99	com/tapjoy/TapjoyConnectCore:deviceManufacturer	Ljava/lang/String;
    //   268: getstatic 593	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   271: putstatic 103	com/tapjoy/TapjoyConnectCore:deviceOSVersion	Ljava/lang/String;
    //   274: invokestatic 599	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   277: invokevirtual 602	java/util/Locale:getCountry	()Ljava/lang/String;
    //   280: putstatic 105	com/tapjoy/TapjoyConnectCore:deviceCountryCode	Ljava/lang/String;
    //   283: invokestatic 599	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   286: invokevirtual 605	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   289: putstatic 107	com/tapjoy/TapjoyConnectCore:deviceLanguage	Ljava/lang/String;
    //   292: ldc_w 607
    //   295: putstatic 113	com/tapjoy/TapjoyConnectCore:libraryVersion	Ljava/lang/String;
    //   298: getstatic 75	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   301: ldc_w 323
    //   304: iconst_0
    //   305: invokevirtual 327	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   308: astore_3
    //   309: getstatic 75	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   312: ldc_w 609
    //   315: invokevirtual 273	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   318: checkcast 611	android/telephony/TelephonyManager
    //   321: astore 4
    //   323: aload 4
    //   325: ifnull +107 -> 432
    //   328: ldc_w 613
    //   331: invokestatic 203	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   334: ifnull +1649 -> 1983
    //   337: ldc_w 613
    //   340: invokestatic 203	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   343: invokevirtual 209	java/lang/String:length	()I
    //   346: ifle +1637 -> 1983
    //   349: ldc_w 613
    //   352: invokestatic 203	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   355: putstatic 87	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   358: aload 4
    //   360: invokevirtual 616	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   363: putstatic 123	com/tapjoy/TapjoyConnectCore:carrierName	Ljava/lang/String;
    //   366: aload 4
    //   368: invokevirtual 619	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   371: putstatic 125	com/tapjoy/TapjoyConnectCore:carrierCountryCode	Ljava/lang/String;
    //   374: aload 4
    //   376: invokevirtual 622	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   379: ifnull +53 -> 432
    //   382: aload 4
    //   384: invokevirtual 622	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   387: invokevirtual 209	java/lang/String:length	()I
    //   390: iconst_5
    //   391: if_icmpeq +16 -> 407
    //   394: aload 4
    //   396: invokevirtual 622	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   399: invokevirtual 209	java/lang/String:length	()I
    //   402: bipush 6
    //   404: if_icmpne +28 -> 432
    //   407: aload 4
    //   409: invokevirtual 622	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   412: iconst_0
    //   413: iconst_3
    //   414: invokevirtual 510	java/lang/String:substring	(II)Ljava/lang/String;
    //   417: putstatic 127	com/tapjoy/TapjoyConnectCore:mobileCountryCode	Ljava/lang/String;
    //   420: aload 4
    //   422: invokevirtual 622	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   425: iconst_3
    //   426: invokevirtual 459	java/lang/String:substring	(I)Ljava/lang/String;
    //   429: putstatic 129	com/tapjoy/TapjoyConnectCore:mobileNetworkCode	Ljava/lang/String;
    //   432: ldc 19
    //   434: new 174	java/lang/StringBuilder
    //   437: dup
    //   438: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   441: ldc_w 624
    //   444: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   447: getstatic 87	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   450: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   453: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   456: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   459: iconst_0
    //   460: istore_1
    //   461: getstatic 87	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   464: ifnonnull +1568 -> 2032
    //   467: ldc 19
    //   469: ldc_w 626
    //   472: invokestatic 262	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   475: iconst_1
    //   476: istore_1
    //   477: ldc 19
    //   479: new 174	java/lang/StringBuilder
    //   482: dup
    //   483: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   486: ldc_w 628
    //   489: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   492: getstatic 631	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   495: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   498: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   501: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   504: iload_1
    //   505: istore_2
    //   506: getstatic 631	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   509: invokestatic 637	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   512: bipush 9
    //   514: if_icmplt +99 -> 613
    //   517: ldc 19
    //   519: ldc_w 639
    //   522: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   525: new 641	com/tapjoy/TapjoyHardwareUtil
    //   528: dup
    //   529: invokespecial 642	com/tapjoy/TapjoyHardwareUtil:<init>	()V
    //   532: invokevirtual 645	com/tapjoy/TapjoyHardwareUtil:getSerial	()Ljava/lang/String;
    //   535: putstatic 95	com/tapjoy/TapjoyConnectCore:serialID	Ljava/lang/String;
    //   538: iload_1
    //   539: ifeq +9 -> 548
    //   542: getstatic 95	com/tapjoy/TapjoyConnectCore:serialID	Ljava/lang/String;
    //   545: putstatic 87	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   548: ldc 19
    //   550: ldc_w 647
    //   553: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   556: ldc 19
    //   558: new 174	java/lang/StringBuilder
    //   561: dup
    //   562: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   565: ldc_w 649
    //   568: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   571: getstatic 87	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   574: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   577: ldc_w 651
    //   580: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   583: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   586: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   589: ldc 19
    //   591: ldc_w 647
    //   594: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   597: getstatic 87	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   600: ifnonnull +1490 -> 2090
    //   603: ldc 19
    //   605: ldc_w 653
    //   608: invokestatic 262	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   611: iconst_1
    //   612: istore_2
    //   613: iload_2
    //   614: ifeq +53 -> 667
    //   617: new 655	java/lang/StringBuffer
    //   620: dup
    //   621: invokespecial 656	java/lang/StringBuffer:<init>	()V
    //   624: astore 4
    //   626: aload 4
    //   628: ldc_w 658
    //   631: invokevirtual 661	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   634: pop
    //   635: aload_3
    //   636: ldc_w 663
    //   639: aconst_null
    //   640: invokeinterface 666 3 0
    //   645: astore 5
    //   647: aload 5
    //   649: ifnull +1674 -> 2323
    //   652: aload 5
    //   654: ldc 83
    //   656: invokevirtual 349	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   659: ifne +1664 -> 2323
    //   662: aload 5
    //   664: putstatic 87	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   667: getstatic 119	com/tapjoy/TapjoyConnectCore:userID	Ljava/lang/String;
    //   670: invokevirtual 209	java/lang/String:length	()I
    //   673: ifne +9 -> 682
    //   676: getstatic 87	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   679: putstatic 119	com/tapjoy/TapjoyConnectCore:userID	Ljava/lang/String;
    //   682: getstatic 87	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   685: invokestatic 256	com/tapjoy/TapjoyUtil:SHA256	(Ljava/lang/String;)Ljava/lang/String;
    //   688: putstatic 89	com/tapjoy/TapjoyConnectCore:sha2DeviceID	Ljava/lang/String;
    //   691: getstatic 631	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   694: invokestatic 637	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   697: iconst_3
    //   698: if_icmple +67 -> 765
    //   701: new 668	com/tapjoy/TapjoyDisplayMetricsUtil
    //   704: dup
    //   705: getstatic 75	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   708: invokespecial 670	com/tapjoy/TapjoyDisplayMetricsUtil:<init>	(Landroid/content/Context;)V
    //   711: astore 4
    //   713: new 174	java/lang/StringBuilder
    //   716: dup
    //   717: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   720: ldc 83
    //   722: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   725: aload 4
    //   727: invokevirtual 673	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensity	()I
    //   730: invokevirtual 251	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   733: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   736: putstatic 115	com/tapjoy/TapjoyConnectCore:deviceScreenDensity	Ljava/lang/String;
    //   739: new 174	java/lang/StringBuilder
    //   742: dup
    //   743: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   746: ldc 83
    //   748: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   751: aload 4
    //   753: invokevirtual 676	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenLayoutSize	()I
    //   756: invokevirtual 251	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   759: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   762: putstatic 117	com/tapjoy/TapjoyConnectCore:deviceScreenLayoutSize	Ljava/lang/String;
    //   765: getstatic 75	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   768: ldc_w 290
    //   771: invokevirtual 273	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   774: checkcast 678	android/net/wifi/WifiManager
    //   777: astore 4
    //   779: aload 4
    //   781: ifnull +56 -> 837
    //   784: aload 4
    //   786: invokevirtual 682	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   789: astore 4
    //   791: aload 4
    //   793: ifnull +44 -> 837
    //   796: aload 4
    //   798: invokevirtual 687	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   801: putstatic 91	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   804: getstatic 91	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   807: ifnull +30 -> 837
    //   810: getstatic 91	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   813: invokevirtual 209	java/lang/String:length	()I
    //   816: ifle +21 -> 837
    //   819: getstatic 91	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   822: invokevirtual 690	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   825: putstatic 91	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   828: getstatic 91	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   831: invokestatic 693	com/tapjoy/TapjoyUtil:SHA1	(Ljava/lang/String;)Ljava/lang/String;
    //   834: putstatic 93	com/tapjoy/TapjoyConnectCore:sha1MacAddress	Ljava/lang/String;
    //   837: ldc_w 695
    //   840: invokestatic 203	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   843: ifnull +74 -> 917
    //   846: ldc_w 695
    //   849: invokestatic 203	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   852: invokevirtual 209	java/lang/String:length	()I
    //   855: ifle +62 -> 917
    //   858: ldc_w 695
    //   861: invokestatic 203	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   864: putstatic 133	com/tapjoy/TapjoyConnectCore:storeName	Ljava/lang/String;
    //   867: new 697	java/util/ArrayList
    //   870: dup
    //   871: getstatic 700	com/tapjoy/TapjoyConnectFlag:STORE_ARRAY	[Ljava/lang/String;
    //   874: invokestatic 706	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   877: invokespecial 709	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   880: getstatic 133	com/tapjoy/TapjoyConnectCore:storeName	Ljava/lang/String;
    //   883: invokevirtual 710	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   886: ifne +31 -> 917
    //   889: ldc 19
    //   891: new 174	java/lang/StringBuilder
    //   894: dup
    //   895: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   898: ldc_w 712
    //   901: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   904: getstatic 133	com/tapjoy/TapjoyConnectCore:storeName	Ljava/lang/String;
    //   907: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   910: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   913: invokestatic 718	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   916: pop
    //   917: aload_3
    //   918: ldc_w 720
    //   921: aconst_null
    //   922: invokeinterface 666 3 0
    //   927: astore_3
    //   928: aload_3
    //   929: ifnull +16 -> 945
    //   932: aload_3
    //   933: ldc 83
    //   935: invokevirtual 349	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   938: ifne +7 -> 945
    //   941: aload_3
    //   942: putstatic 139	com/tapjoy/TapjoyConnectCore:referralURL	Ljava/lang/String;
    //   945: getstatic 75	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   948: invokevirtual 520	android/content/Context:getPackageName	()Ljava/lang/String;
    //   951: putstatic 137	com/tapjoy/TapjoyConnectCore:clientPackage	Ljava/lang/String;
    //   954: ldc 19
    //   956: new 174	java/lang/StringBuilder
    //   959: dup
    //   960: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   963: ldc_w 722
    //   966: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   969: getstatic 109	com/tapjoy/TapjoyConnectCore:appID	Ljava/lang/String;
    //   972: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   975: ldc_w 651
    //   978: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   981: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   984: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   987: ldc 19
    //   989: new 174	java/lang/StringBuilder
    //   992: dup
    //   993: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   996: ldc_w 724
    //   999: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1002: getstatic 85	com/tapjoy/TapjoyConnectCore:androidID	Ljava/lang/String;
    //   1005: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1008: ldc_w 651
    //   1011: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1014: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1017: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1020: ldc 19
    //   1022: new 174	java/lang/StringBuilder
    //   1025: dup
    //   1026: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   1029: ldc_w 726
    //   1032: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1035: getstatic 137	com/tapjoy/TapjoyConnectCore:clientPackage	Ljava/lang/String;
    //   1038: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1041: ldc_w 651
    //   1044: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1047: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1050: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1053: new 174	java/lang/StringBuilder
    //   1056: dup
    //   1057: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   1060: ldc_w 728
    //   1063: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1066: getstatic 87	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1069: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1072: ldc_w 651
    //   1075: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1078: astore 4
    //   1080: ldc_w 613
    //   1083: invokestatic 203	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1086: ifnull +1224 -> 2310
    //   1089: ldc_w 613
    //   1092: invokestatic 203	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1095: invokevirtual 209	java/lang/String:length	()I
    //   1098: ifle +1212 -> 2310
    //   1101: ldc_w 730
    //   1104: astore_3
    //   1105: ldc 19
    //   1107: aload 4
    //   1109: aload_3
    //   1110: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1113: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1116: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1119: ldc 19
    //   1121: new 174	java/lang/StringBuilder
    //   1124: dup
    //   1125: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   1128: ldc_w 732
    //   1131: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1134: getstatic 89	com/tapjoy/TapjoyConnectCore:sha2DeviceID	Ljava/lang/String;
    //   1137: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1140: ldc_w 651
    //   1143: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1146: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1149: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1152: ldc 19
    //   1154: new 174	java/lang/StringBuilder
    //   1157: dup
    //   1158: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   1161: ldc_w 734
    //   1164: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1167: getstatic 95	com/tapjoy/TapjoyConnectCore:serialID	Ljava/lang/String;
    //   1170: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1173: ldc_w 651
    //   1176: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1179: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1182: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1185: ldc 19
    //   1187: new 174	java/lang/StringBuilder
    //   1190: dup
    //   1191: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   1194: ldc_w 736
    //   1197: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1200: getstatic 91	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   1203: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1206: ldc_w 651
    //   1209: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1212: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1215: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1218: ldc 19
    //   1220: new 174	java/lang/StringBuilder
    //   1223: dup
    //   1224: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   1227: ldc_w 738
    //   1230: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1233: getstatic 93	com/tapjoy/TapjoyConnectCore:sha1MacAddress	Ljava/lang/String;
    //   1236: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1239: ldc_w 651
    //   1242: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1245: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1248: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1251: ldc 19
    //   1253: new 174	java/lang/StringBuilder
    //   1256: dup
    //   1257: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   1260: ldc_w 740
    //   1263: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1266: getstatic 97	com/tapjoy/TapjoyConnectCore:deviceModel	Ljava/lang/String;
    //   1269: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1272: ldc_w 651
    //   1275: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1278: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1281: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1284: ldc 19
    //   1286: new 174	java/lang/StringBuilder
    //   1289: dup
    //   1290: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   1293: ldc_w 742
    //   1296: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1299: getstatic 99	com/tapjoy/TapjoyConnectCore:deviceManufacturer	Ljava/lang/String;
    //   1302: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1305: ldc_w 651
    //   1308: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1311: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1314: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1317: ldc 19
    //   1319: new 174	java/lang/StringBuilder
    //   1322: dup
    //   1323: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   1326: ldc_w 744
    //   1329: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1332: getstatic 101	com/tapjoy/TapjoyConnectCore:deviceType	Ljava/lang/String;
    //   1335: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1338: ldc_w 651
    //   1341: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1344: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1347: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1350: ldc 19
    //   1352: new 174	java/lang/StringBuilder
    //   1355: dup
    //   1356: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   1359: ldc_w 746
    //   1362: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1365: getstatic 113	com/tapjoy/TapjoyConnectCore:libraryVersion	Ljava/lang/String;
    //   1368: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1371: ldc_w 651
    //   1374: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1377: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1380: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1383: ldc 19
    //   1385: new 174	java/lang/StringBuilder
    //   1388: dup
    //   1389: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   1392: ldc_w 748
    //   1395: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1398: getstatic 103	com/tapjoy/TapjoyConnectCore:deviceOSVersion	Ljava/lang/String;
    //   1401: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1404: ldc_w 651
    //   1407: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1410: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1413: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1416: ldc 19
    //   1418: new 174	java/lang/StringBuilder
    //   1421: dup
    //   1422: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   1425: ldc_w 750
    //   1428: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1431: getstatic 105	com/tapjoy/TapjoyConnectCore:deviceCountryCode	Ljava/lang/String;
    //   1434: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1437: ldc_w 651
    //   1440: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1443: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1446: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1449: ldc 19
    //   1451: new 174	java/lang/StringBuilder
    //   1454: dup
    //   1455: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   1458: ldc_w 752
    //   1461: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1464: getstatic 107	com/tapjoy/TapjoyConnectCore:deviceLanguage	Ljava/lang/String;
    //   1467: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1470: ldc_w 651
    //   1473: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1476: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1479: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1482: ldc 19
    //   1484: new 174	java/lang/StringBuilder
    //   1487: dup
    //   1488: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   1491: ldc_w 754
    //   1494: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1497: getstatic 115	com/tapjoy/TapjoyConnectCore:deviceScreenDensity	Ljava/lang/String;
    //   1500: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1503: ldc_w 651
    //   1506: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1509: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1512: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1515: ldc 19
    //   1517: new 174	java/lang/StringBuilder
    //   1520: dup
    //   1521: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   1524: ldc_w 756
    //   1527: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1530: getstatic 117	com/tapjoy/TapjoyConnectCore:deviceScreenLayoutSize	Ljava/lang/String;
    //   1533: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1536: ldc_w 651
    //   1539: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1542: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1545: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1548: ldc 19
    //   1550: new 174	java/lang/StringBuilder
    //   1553: dup
    //   1554: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   1557: ldc_w 758
    //   1560: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1563: getstatic 123	com/tapjoy/TapjoyConnectCore:carrierName	Ljava/lang/String;
    //   1566: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1569: ldc_w 651
    //   1572: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1575: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1578: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1581: ldc 19
    //   1583: new 174	java/lang/StringBuilder
    //   1586: dup
    //   1587: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   1590: ldc_w 760
    //   1593: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1596: getstatic 125	com/tapjoy/TapjoyConnectCore:carrierCountryCode	Ljava/lang/String;
    //   1599: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1602: ldc_w 651
    //   1605: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1608: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1611: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1614: ldc 19
    //   1616: new 174	java/lang/StringBuilder
    //   1619: dup
    //   1620: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   1623: ldc_w 762
    //   1626: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1629: getstatic 127	com/tapjoy/TapjoyConnectCore:mobileCountryCode	Ljava/lang/String;
    //   1632: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1635: ldc_w 651
    //   1638: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1641: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1644: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1647: ldc 19
    //   1649: new 174	java/lang/StringBuilder
    //   1652: dup
    //   1653: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   1656: ldc_w 764
    //   1659: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1662: getstatic 129	com/tapjoy/TapjoyConnectCore:mobileNetworkCode	Ljava/lang/String;
    //   1665: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1668: ldc_w 651
    //   1671: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1674: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1677: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1680: ldc 19
    //   1682: new 174	java/lang/StringBuilder
    //   1685: dup
    //   1686: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   1689: ldc_w 766
    //   1692: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1695: getstatic 133	com/tapjoy/TapjoyConnectCore:storeName	Ljava/lang/String;
    //   1698: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1701: ldc_w 651
    //   1704: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1707: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1710: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1713: ldc 19
    //   1715: new 174	java/lang/StringBuilder
    //   1718: dup
    //   1719: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   1722: ldc_w 768
    //   1725: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1728: getstatic 139	com/tapjoy/TapjoyConnectCore:referralURL	Ljava/lang/String;
    //   1731: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1734: ldc_w 651
    //   1737: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1740: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1743: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1746: getstatic 155	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   1749: ifnull +188 -> 1937
    //   1752: ldc 19
    //   1754: ldc_w 770
    //   1757: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1760: ldc 19
    //   1762: ldc_w 772
    //   1765: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1768: getstatic 155	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   1771: invokevirtual 776	java/util/Hashtable:entrySet	()Ljava/util/Set;
    //   1774: invokeinterface 779 1 0
    //   1779: astore_3
    //   1780: aload_3
    //   1781: invokeinterface 487 1 0
    //   1786: ifeq +151 -> 1937
    //   1789: aload_3
    //   1790: invokeinterface 491 1 0
    //   1795: checkcast 781	java/util/Map$Entry
    //   1798: astore 4
    //   1800: ldc 19
    //   1802: new 174	java/lang/StringBuilder
    //   1805: dup
    //   1806: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   1809: ldc_w 783
    //   1812: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1815: aload 4
    //   1817: invokeinterface 786 1 0
    //   1822: checkcast 205	java/lang/String
    //   1825: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1828: ldc_w 788
    //   1831: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1834: aload 4
    //   1836: invokeinterface 791 1 0
    //   1841: checkcast 205	java/lang/String
    //   1844: invokestatic 313	android/net/Uri:encode	(Ljava/lang/String;)Ljava/lang/String;
    //   1847: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1850: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1853: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1856: aload 4
    //   1858: invokeinterface 786 1 0
    //   1863: checkcast 205	java/lang/String
    //   1866: ldc_w 343
    //   1869: invokevirtual 349	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1872: ifeq -92 -> 1780
    //   1875: getstatic 145	com/tapjoy/TapjoyConnectCore:sdkType	Ljava/lang/String;
    //   1878: ldc_w 793
    //   1881: invokevirtual 349	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1884: ifne -104 -> 1780
    //   1887: ldc 19
    //   1889: ldc_w 795
    //   1892: invokestatic 797	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   1895: getstatic 155	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   1898: ldc_w 343
    //   1901: invokevirtual 800	java/util/Hashtable:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1904: pop
    //   1905: goto -125 -> 1780
    //   1908: astore_3
    //   1909: ldc 19
    //   1911: new 174	java/lang/StringBuilder
    //   1914: dup
    //   1915: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   1918: ldc_w 802
    //   1921: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1924: aload_3
    //   1925: invokevirtual 259	java/lang/Exception:toString	()Ljava/lang/String;
    //   1928: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1931: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1934: invokestatic 262	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1937: return
    //   1938: ldc 19
    //   1940: ldc_w 804
    //   1943: invokestatic 194	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1946: goto -1762 -> 184
    //   1949: astore 4
    //   1951: ldc 19
    //   1953: new 174	java/lang/StringBuilder
    //   1956: dup
    //   1957: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   1960: ldc_w 806
    //   1963: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1966: aload 4
    //   1968: invokevirtual 259	java/lang/Exception:toString	()Ljava/lang/String;
    //   1971: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1974: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1977: invokestatic 262	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1980: goto -1796 -> 184
    //   1983: aload 4
    //   1985: invokevirtual 809	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   1988: putstatic 87	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1991: goto -1633 -> 358
    //   1994: astore 4
    //   1996: ldc 19
    //   1998: new 174	java/lang/StringBuilder
    //   2001: dup
    //   2002: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   2005: ldc_w 811
    //   2008: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2011: aload 4
    //   2013: invokevirtual 259	java/lang/Exception:toString	()Ljava/lang/String;
    //   2016: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2019: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2022: invokestatic 262	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   2025: aconst_null
    //   2026: putstatic 87	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2029: goto -1362 -> 667
    //   2032: getstatic 87	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2035: invokevirtual 209	java/lang/String:length	()I
    //   2038: ifeq +27 -> 2065
    //   2041: getstatic 87	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2044: ldc_w 813
    //   2047: invokevirtual 349	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2050: ifne +15 -> 2065
    //   2053: getstatic 87	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2056: ldc_w 815
    //   2059: invokevirtual 349	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2062: ifeq +16 -> 2078
    //   2065: ldc 19
    //   2067: ldc_w 817
    //   2070: invokestatic 262	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   2073: iconst_1
    //   2074: istore_1
    //   2075: goto -1598 -> 477
    //   2078: getstatic 87	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2081: invokevirtual 820	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   2084: putstatic 87	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2087: goto -1610 -> 477
    //   2090: getstatic 87	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2093: invokevirtual 209	java/lang/String:length	()I
    //   2096: ifeq +39 -> 2135
    //   2099: getstatic 87	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2102: ldc_w 813
    //   2105: invokevirtual 349	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2108: ifne +27 -> 2135
    //   2111: getstatic 87	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2114: ldc_w 815
    //   2117: invokevirtual 349	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2120: ifne +15 -> 2135
    //   2123: getstatic 87	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2126: ldc_w 822
    //   2129: invokevirtual 349	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2132: ifeq +16 -> 2148
    //   2135: ldc 19
    //   2137: ldc_w 824
    //   2140: invokestatic 262	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   2143: iconst_1
    //   2144: istore_2
    //   2145: goto -1532 -> 613
    //   2148: getstatic 87	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2151: invokevirtual 820	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   2154: putstatic 87	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2157: iconst_0
    //   2158: istore_2
    //   2159: goto -1546 -> 613
    //   2162: iload_1
    //   2163: bipush 32
    //   2165: if_icmpge +33 -> 2198
    //   2168: aload 4
    //   2170: ldc_w 826
    //   2173: invokestatic 832	java/lang/Math:random	()D
    //   2176: ldc2_w 833
    //   2179: dmul
    //   2180: d2i
    //   2181: bipush 30
    //   2183: irem
    //   2184: invokevirtual 838	java/lang/String:charAt	(I)C
    //   2187: invokevirtual 841	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   2190: pop
    //   2191: iload_1
    //   2192: iconst_1
    //   2193: iadd
    //   2194: istore_1
    //   2195: goto -33 -> 2162
    //   2198: aload 4
    //   2200: invokevirtual 842	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   2203: invokevirtual 820	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   2206: putstatic 87	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2209: aload_3
    //   2210: invokeinterface 846 1 0
    //   2215: astore 4
    //   2217: aload 4
    //   2219: ldc_w 663
    //   2222: getstatic 87	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2225: invokeinterface 852 3 0
    //   2230: pop
    //   2231: aload 4
    //   2233: invokeinterface 855 1 0
    //   2238: pop
    //   2239: goto -1572 -> 667
    //   2242: astore 4
    //   2244: ldc 19
    //   2246: new 174	java/lang/StringBuilder
    //   2249: dup
    //   2250: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   2253: ldc_w 857
    //   2256: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2259: aload 4
    //   2261: invokevirtual 259	java/lang/Exception:toString	()Ljava/lang/String;
    //   2264: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2267: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2270: invokestatic 262	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   2273: goto -1508 -> 765
    //   2276: astore 4
    //   2278: ldc 19
    //   2280: new 174	java/lang/StringBuilder
    //   2283: dup
    //   2284: invokespecial 175	java/lang/StringBuilder:<init>	()V
    //   2287: ldc_w 859
    //   2290: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2293: aload 4
    //   2295: invokevirtual 259	java/lang/Exception:toString	()Ljava/lang/String;
    //   2298: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2301: invokevirtual 188	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2304: invokestatic 262	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   2307: goto -1470 -> 837
    //   2310: ldc 83
    //   2312: astore_3
    //   2313: goto -1208 -> 1105
    //   2316: iload_1
    //   2317: iconst_1
    //   2318: iadd
    //   2319: istore_1
    //   2320: goto -2254 -> 66
    //   2323: iconst_0
    //   2324: istore_1
    //   2325: goto -163 -> 2162
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2328	0	this	TapjoyConnectCore
    //   65	2260	1	i	int
    //   63	2096	2	j	int
    //   6	1784	3	localObject1	Object
    //   1908	302	3	localException1	Exception
    //   2312	1	3	str1	String
    //   40	1817	4	localObject2	Object
    //   1949	35	4	localException2	Exception
    //   1994	205	4	localException3	Exception
    //   2215	17	4	localEditor	SharedPreferences.Editor
    //   2242	18	4	localException4	Exception
    //   2276	18	4	localException5	Exception
    //   58	605	5	localObject3	Object
    //   75	91	6	str2	String
    //   106	62	7	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   212	309	1908	java/lang/Exception
    //   667	682	1908	java/lang/Exception
    //   682	691	1908	java/lang/Exception
    //   837	917	1908	java/lang/Exception
    //   917	928	1908	java/lang/Exception
    //   932	945	1908	java/lang/Exception
    //   945	1101	1908	java/lang/Exception
    //   1105	1780	1908	java/lang/Exception
    //   1780	1905	1908	java/lang/Exception
    //   1996	2029	1908	java/lang/Exception
    //   2244	2273	1908	java/lang/Exception
    //   2278	2307	1908	java/lang/Exception
    //   7	23	1949	java/lang/Exception
    //   27	42	1949	java/lang/Exception
    //   47	64	1949	java/lang/Exception
    //   77	108	1949	java/lang/Exception
    //   113	120	1949	java/lang/Exception
    //   125	173	1949	java/lang/Exception
    //   176	184	1949	java/lang/Exception
    //   1938	1946	1949	java/lang/Exception
    //   309	323	1994	java/lang/Exception
    //   328	358	1994	java/lang/Exception
    //   358	407	1994	java/lang/Exception
    //   407	432	1994	java/lang/Exception
    //   432	459	1994	java/lang/Exception
    //   461	475	1994	java/lang/Exception
    //   477	504	1994	java/lang/Exception
    //   506	538	1994	java/lang/Exception
    //   542	548	1994	java/lang/Exception
    //   548	611	1994	java/lang/Exception
    //   617	647	1994	java/lang/Exception
    //   652	667	1994	java/lang/Exception
    //   1983	1991	1994	java/lang/Exception
    //   2032	2065	1994	java/lang/Exception
    //   2065	2073	1994	java/lang/Exception
    //   2078	2087	1994	java/lang/Exception
    //   2090	2135	1994	java/lang/Exception
    //   2135	2143	1994	java/lang/Exception
    //   2148	2157	1994	java/lang/Exception
    //   2168	2191	1994	java/lang/Exception
    //   2198	2239	1994	java/lang/Exception
    //   691	765	2242	java/lang/Exception
    //   765	779	2276	java/lang/Exception
    //   784	791	2276	java/lang/Exception
    //   796	837	2276	java/lang/Exception
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
  
  public static void saveTapPointsTotal(int paramInt)
  {
    SharedPreferences.Editor localEditor = context.getSharedPreferences("tjcPrefrences", 0).edit();
    localEditor.putInt("last_tap_points", paramInt);
    localEditor.commit();
  }
  
  public static void setDebugDeviceID(String paramString)
  {
    deviceID = paramString;
    paramString = context.getSharedPreferences("tjcPrefrences", 0).edit();
    paramString.putString("emulatorDeviceId", deviceID);
    paramString.commit();
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
    new Thread(new Runnable()
    {
      public void run()
      {
        TapjoyLog.i("TapjoyConnect", "setUserID...");
        Object localObject = TapjoyConnectCore.getURLParams();
        String str = (String)localObject + "&publisher_user_id=" + TapjoyConnectCore.getUserID();
        localObject = str;
        if (!TapjoyConnectCore.referralURL.equals("")) {
          localObject = str + "&" + TapjoyConnectCore.referralURL;
        }
        localObject = TapjoyConnectCore.tapjoyURLConnection.connectToURL("https://ws.tapjoyads.com/set_publisher_user_id?", (String)localObject);
        if (localObject != null)
        {
          if (TapjoyConnectCore.handleConnectResponse((String)localObject)) {}
          TapjoyLog.i("TapjoyConnect", "setUserID successful...");
        }
      }
    }).start();
  }
  
  public static void setVideoEnabled(boolean paramBoolean)
  {
    videoEnabled = paramBoolean;
  }
  
  public static void setVideoIDs(String paramString)
  {
    videoIDs = paramString;
  }
  
  public void actionComplete(String paramString)
  {
    TapjoyLog.i("TapjoyConnect", "actionComplete: " + paramString);
    paramString = "app_id=" + paramString + "&";
    String str = paramString + getParamsWithoutAppID();
    if (getFlagValue("sha_2_udid") != null)
    {
      paramString = str;
      if (getFlagValue("sha_2_udid").equals("true")) {}
    }
    else
    {
      paramString = str + "&publisher_user_id=" + getUserID();
    }
    paramString = paramString + "&";
    long l = System.currentTimeMillis() / 1000L;
    paramString = paramString + "timestamp=" + l + "&";
    paramString = paramString + "verifier=" + getVerifier(l);
    TapjoyLog.i("TapjoyConnect", "PPA URL parameters: " + paramString);
    new Thread(new PPAThread(paramString)).start();
  }
  
  public void callConnect()
  {
    new Thread(new ConnectThread()).start();
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
    this.timer.schedule(new PaidAppTimerTask(null), 10000L, 10000L);
  }
  
  public float getCurrencyMultiplier()
  {
    return currencyMultiplier;
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
  
  public class ConnectThread
    implements Runnable
  {
    public ConnectThread() {}
    
    public void run()
    {
      TapjoyLog.i("TapjoyConnect", "starting connect call...");
      Object localObject = TapjoyConnectCore.getURLParams();
      localObject = TapjoyConnectCore.tapjoyURLConnection.getResponseFromURL("https://ws.tapjoyads.com/connect?", (String)localObject);
      if ((localObject != null) && (((TapjoyHttpURLResponse)localObject).statusCode == 200)) {
        if (TapjoyConnectCore.handleConnectResponse(((TapjoyHttpURLResponse)localObject).response))
        {
          TapjoyLog.i("TapjoyConnect", "Successfully connected to tapjoy site.");
          if (TapjoyConnectCore.connectNotifier != null) {
            TapjoyConnectCore.connectNotifier.connectSuccess();
          }
        }
      }
      while (TapjoyConnectCore.connectNotifier == null) {
        for (;;)
        {
          if (TapjoyConnectCore.matchingPackageNames.length() > 0)
          {
            String str = TapjoyConnectCore.getGenericURLParams() + "&" + "package_names" + "=" + TapjoyConnectCore.matchingPackageNames + "&";
            long l = System.currentTimeMillis() / 1000L;
            localObject = TapjoyConnectCore.getPackageNamesVerifier(l, TapjoyConnectCore.matchingPackageNames);
            str = str + "timestamp=" + l + "&";
            localObject = str + "verifier=" + (String)localObject;
            localObject = TapjoyConnectCore.tapjoyURLConnection.getResponseFromURL("https://ws.tapjoyads.com/apps_installed?", (String)localObject);
            if ((localObject != null) && (((TapjoyHttpURLResponse)localObject).statusCode == 200)) {
              TapjoyLog.i("TapjoyConnect", "Successfully pinged sdkless api.");
            }
          }
          return;
          if (TapjoyConnectCore.connectNotifier != null) {
            TapjoyConnectCore.connectNotifier.connectFail();
          }
        }
      }
      TapjoyConnectCore.connectNotifier.connectFail();
    }
  }
  
  public class PPAThread
    implements Runnable
  {
    private String params;
    
    public PPAThread(String paramString)
    {
      this.params = paramString;
    }
    
    public void run()
    {
      String str = TapjoyConnectCore.tapjoyURLConnection.connectToURL("https://ws.tapjoyads.com/connect?", this.params);
      if (str != null) {
        TapjoyConnectCore.this.handlePayPerActionResponse(str);
      }
    }
  }
  
  private class PaidAppTimerTask
    extends TimerTask
  {
    private PaidAppTimerTask() {}
    
    public void run()
    {
      TapjoyConnectCore.access$014(TapjoyConnectCore.this, 10000L);
      TapjoyLog.i("TapjoyConnect", "elapsed_time: " + TapjoyConnectCore.this.elapsed_time + " (" + TapjoyConnectCore.this.elapsed_time / 1000L / 60L + "m " + TapjoyConnectCore.this.elapsed_time / 1000L % 60L + "s)");
      SharedPreferences.Editor localEditor = TapjoyConnectCore.context.getSharedPreferences("tjcPrefrences", 0).edit();
      localEditor.putLong("tapjoy_elapsed_time", TapjoyConnectCore.this.elapsed_time);
      localEditor.commit();
      if (TapjoyConnectCore.this.elapsed_time >= 900000L)
      {
        TapjoyLog.i("TapjoyConnect", "timer done...");
        if ((TapjoyConnectCore.paidAppActionID != null) && (TapjoyConnectCore.paidAppActionID.length() > 0))
        {
          TapjoyLog.i("TapjoyConnect", "Calling PPA actionComplete...");
          TapjoyConnectCore.this.actionComplete(TapjoyConnectCore.paidAppActionID);
        }
        cancel();
      }
    }
  }
}
