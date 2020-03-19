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
    new Thread(new ConnectThread()).start();
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
    if (connectFlags != null) {
      return (String)connectFlags.get(paramString);
    }
    return "";
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
    //   0: getstatic 73	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   3: invokevirtual 463	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   6: astore_3
    //   7: getstatic 151	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   10: ifnonnull +13 -> 23
    //   13: new 288	java/util/Hashtable
    //   16: dup
    //   17: invokespecial 511	java/util/Hashtable:<init>	()V
    //   20: putstatic 151	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   23: aload_3
    //   24: ifnull +160 -> 184
    //   27: aload_3
    //   28: getstatic 73	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   31: invokevirtual 514	android/content/Context:getPackageName	()Ljava/lang/String;
    //   34: sipush 128
    //   37: invokevirtual 518	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   40: astore 4
    //   42: aload 4
    //   44: ifnull +1811 -> 1855
    //   47: aload 4
    //   49: getfield 522	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   52: ifnull +1803 -> 1855
    //   55: getstatic 528	com/tapjoy/TapjoyConnectFlag:FLAG_ARRAY	[Ljava/lang/String;
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
    //   79: getfield 522	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   82: new 170	java/lang/StringBuilder
    //   85: dup
    //   86: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   89: ldc_w 530
    //   92: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: aload 6
    //   97: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   103: invokevirtual 534	android/os/Bundle:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   106: astore 7
    //   108: aload 7
    //   110: ifnull +2106 -> 2216
    //   113: aload 7
    //   115: invokevirtual 535	java/lang/Object:toString	()Ljava/lang/String;
    //   118: astore 7
    //   120: aload 7
    //   122: ifnull +2094 -> 2216
    //   125: ldc 19
    //   127: new 170	java/lang/StringBuilder
    //   130: dup
    //   131: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   134: ldc_w 537
    //   137: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: aload 6
    //   142: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: ldc_w 539
    //   148: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: aload 7
    //   153: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   159: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   162: getstatic 151	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   165: aload 6
    //   167: aload 7
    //   169: invokevirtual 543	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   172: pop
    //   173: goto +2043 -> 2216
    //   176: ldc 19
    //   178: ldc_w 545
    //   181: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   184: getstatic 73	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   187: invokevirtual 549	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   190: ldc_w 551
    //   193: invokestatic 557	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   196: putstatic 81	com/tapjoy/TapjoyConnectCore:androidID	Ljava/lang/String;
    //   199: aload_3
    //   200: getstatic 73	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   203: invokevirtual 514	android/content/Context:getPackageName	()Ljava/lang/String;
    //   206: iconst_0
    //   207: invokevirtual 561	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   210: getfield 566	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   213: putstatic 107	com/tapjoy/TapjoyConnectCore:appVersion	Ljava/lang/String;
    //   216: ldc_w 568
    //   219: putstatic 97	com/tapjoy/TapjoyConnectCore:deviceType	Ljava/lang/String;
    //   222: ldc_w 568
    //   225: putstatic 117	com/tapjoy/TapjoyConnectCore:platformName	Ljava/lang/String;
    //   228: getstatic 573	android/os/Build:MODEL	Ljava/lang/String;
    //   231: putstatic 93	com/tapjoy/TapjoyConnectCore:deviceModel	Ljava/lang/String;
    //   234: getstatic 576	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   237: putstatic 95	com/tapjoy/TapjoyConnectCore:deviceManufacturer	Ljava/lang/String;
    //   240: getstatic 581	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   243: putstatic 99	com/tapjoy/TapjoyConnectCore:deviceOSVersion	Ljava/lang/String;
    //   246: invokestatic 587	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   249: invokevirtual 590	java/util/Locale:getCountry	()Ljava/lang/String;
    //   252: putstatic 101	com/tapjoy/TapjoyConnectCore:deviceCountryCode	Ljava/lang/String;
    //   255: invokestatic 587	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   258: invokevirtual 593	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   261: putstatic 103	com/tapjoy/TapjoyConnectCore:deviceLanguage	Ljava/lang/String;
    //   264: ldc_w 595
    //   267: putstatic 109	com/tapjoy/TapjoyConnectCore:libraryVersion	Ljava/lang/String;
    //   270: getstatic 73	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   273: ldc_w 312
    //   276: iconst_0
    //   277: invokevirtual 316	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   280: astore_3
    //   281: getstatic 73	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   284: ldc_w 597
    //   287: invokevirtual 258	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   290: checkcast 599	android/telephony/TelephonyManager
    //   293: astore 4
    //   295: aload 4
    //   297: ifnull +85 -> 382
    //   300: aload 4
    //   302: invokevirtual 602	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   305: putstatic 83	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   308: aload 4
    //   310: invokevirtual 605	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   313: putstatic 119	com/tapjoy/TapjoyConnectCore:carrierName	Ljava/lang/String;
    //   316: aload 4
    //   318: invokevirtual 608	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   321: putstatic 121	com/tapjoy/TapjoyConnectCore:carrierCountryCode	Ljava/lang/String;
    //   324: aload 4
    //   326: invokevirtual 611	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   329: ifnull +53 -> 382
    //   332: aload 4
    //   334: invokevirtual 611	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   337: invokevirtual 345	java/lang/String:length	()I
    //   340: iconst_5
    //   341: if_icmpeq +16 -> 357
    //   344: aload 4
    //   346: invokevirtual 611	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   349: invokevirtual 345	java/lang/String:length	()I
    //   352: bipush 6
    //   354: if_icmpne +28 -> 382
    //   357: aload 4
    //   359: invokevirtual 611	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   362: iconst_0
    //   363: iconst_3
    //   364: invokevirtual 504	java/lang/String:substring	(II)Ljava/lang/String;
    //   367: putstatic 123	com/tapjoy/TapjoyConnectCore:mobileCountryCode	Ljava/lang/String;
    //   370: aload 4
    //   372: invokevirtual 611	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   375: iconst_3
    //   376: invokevirtual 453	java/lang/String:substring	(I)Ljava/lang/String;
    //   379: putstatic 125	com/tapjoy/TapjoyConnectCore:mobileNetworkCode	Ljava/lang/String;
    //   382: ldc 19
    //   384: new 170	java/lang/StringBuilder
    //   387: dup
    //   388: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   391: ldc_w 613
    //   394: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   397: getstatic 83	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   400: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   403: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   406: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   409: iconst_0
    //   410: istore_1
    //   411: getstatic 83	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   414: ifnonnull +1486 -> 1900
    //   417: ldc 19
    //   419: ldc_w 615
    //   422: invokestatic 247	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   425: iconst_1
    //   426: istore_1
    //   427: ldc 19
    //   429: new 170	java/lang/StringBuilder
    //   432: dup
    //   433: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   436: ldc_w 617
    //   439: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   442: getstatic 620	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   445: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   448: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   451: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   454: iload_1
    //   455: istore_2
    //   456: getstatic 620	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   459: invokestatic 626	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   462: bipush 9
    //   464: if_icmplt +99 -> 563
    //   467: ldc 19
    //   469: ldc_w 628
    //   472: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   475: new 630	com/tapjoy/TapjoyHardwareUtil
    //   478: dup
    //   479: invokespecial 631	com/tapjoy/TapjoyHardwareUtil:<init>	()V
    //   482: invokevirtual 634	com/tapjoy/TapjoyHardwareUtil:getSerial	()Ljava/lang/String;
    //   485: putstatic 91	com/tapjoy/TapjoyConnectCore:serialID	Ljava/lang/String;
    //   488: iload_1
    //   489: ifeq +9 -> 498
    //   492: getstatic 91	com/tapjoy/TapjoyConnectCore:serialID	Ljava/lang/String;
    //   495: putstatic 83	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   498: ldc 19
    //   500: ldc_w 636
    //   503: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   506: ldc 19
    //   508: new 170	java/lang/StringBuilder
    //   511: dup
    //   512: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   515: ldc_w 638
    //   518: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   521: getstatic 83	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   524: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   527: ldc_w 640
    //   530: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   533: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   536: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   539: ldc 19
    //   541: ldc_w 636
    //   544: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   547: getstatic 83	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   550: ifnonnull +1446 -> 1996
    //   553: ldc 19
    //   555: ldc_w 642
    //   558: invokestatic 247	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   561: iconst_1
    //   562: istore_2
    //   563: iload_2
    //   564: ifeq +53 -> 617
    //   567: new 644	java/lang/StringBuffer
    //   570: dup
    //   571: invokespecial 645	java/lang/StringBuffer:<init>	()V
    //   574: astore 4
    //   576: aload 4
    //   578: ldc_w 647
    //   581: invokevirtual 650	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   584: pop
    //   585: aload_3
    //   586: ldc_w 652
    //   589: aconst_null
    //   590: invokeinterface 655 3 0
    //   595: astore 5
    //   597: aload 5
    //   599: ifnull +1624 -> 2223
    //   602: aload 5
    //   604: ldc 79
    //   606: invokevirtual 340	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   609: ifne +1614 -> 2223
    //   612: aload 5
    //   614: putstatic 83	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   617: getstatic 115	com/tapjoy/TapjoyConnectCore:userID	Ljava/lang/String;
    //   620: invokevirtual 345	java/lang/String:length	()I
    //   623: ifne +9 -> 632
    //   626: getstatic 83	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   629: putstatic 115	com/tapjoy/TapjoyConnectCore:userID	Ljava/lang/String;
    //   632: getstatic 83	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   635: invokestatic 241	com/tapjoy/TapjoyUtil:SHA256	(Ljava/lang/String;)Ljava/lang/String;
    //   638: putstatic 85	com/tapjoy/TapjoyConnectCore:sha2DeviceID	Ljava/lang/String;
    //   641: getstatic 620	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   644: invokestatic 626	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   647: iconst_3
    //   648: if_icmple +67 -> 715
    //   651: new 657	com/tapjoy/TapjoyDisplayMetricsUtil
    //   654: dup
    //   655: getstatic 73	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   658: invokespecial 659	com/tapjoy/TapjoyDisplayMetricsUtil:<init>	(Landroid/content/Context;)V
    //   661: astore 4
    //   663: new 170	java/lang/StringBuilder
    //   666: dup
    //   667: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   670: ldc 79
    //   672: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   675: aload 4
    //   677: invokevirtual 662	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensity	()I
    //   680: invokevirtual 235	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   683: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   686: putstatic 111	com/tapjoy/TapjoyConnectCore:deviceScreenDensity	Ljava/lang/String;
    //   689: new 170	java/lang/StringBuilder
    //   692: dup
    //   693: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   696: ldc 79
    //   698: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   701: aload 4
    //   703: invokevirtual 665	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenLayoutSize	()I
    //   706: invokevirtual 235	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   709: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   712: putstatic 113	com/tapjoy/TapjoyConnectCore:deviceScreenLayoutSize	Ljava/lang/String;
    //   715: getstatic 73	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   718: ldc_w 276
    //   721: invokevirtual 258	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   724: checkcast 667	android/net/wifi/WifiManager
    //   727: astore 4
    //   729: aload 4
    //   731: ifnull +56 -> 787
    //   734: aload 4
    //   736: invokevirtual 671	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   739: astore 4
    //   741: aload 4
    //   743: ifnull +44 -> 787
    //   746: aload 4
    //   748: invokevirtual 676	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   751: putstatic 87	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   754: getstatic 87	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   757: ifnull +30 -> 787
    //   760: getstatic 87	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   763: invokevirtual 345	java/lang/String:length	()I
    //   766: ifle +21 -> 787
    //   769: getstatic 87	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   772: invokevirtual 679	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   775: putstatic 87	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   778: getstatic 87	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   781: invokestatic 682	com/tapjoy/TapjoyUtil:SHA1	(Ljava/lang/String;)Ljava/lang/String;
    //   784: putstatic 89	com/tapjoy/TapjoyConnectCore:sha1MacAddress	Ljava/lang/String;
    //   787: ldc_w 684
    //   790: invokestatic 334	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   793: ifnull +74 -> 867
    //   796: ldc_w 684
    //   799: invokestatic 334	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   802: invokevirtual 345	java/lang/String:length	()I
    //   805: ifle +62 -> 867
    //   808: ldc_w 684
    //   811: invokestatic 334	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   814: putstatic 129	com/tapjoy/TapjoyConnectCore:storeName	Ljava/lang/String;
    //   817: new 686	java/util/ArrayList
    //   820: dup
    //   821: getstatic 689	com/tapjoy/TapjoyConnectFlag:STORE_ARRAY	[Ljava/lang/String;
    //   824: invokestatic 695	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   827: invokespecial 698	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   830: getstatic 129	com/tapjoy/TapjoyConnectCore:storeName	Ljava/lang/String;
    //   833: invokevirtual 699	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   836: ifne +31 -> 867
    //   839: ldc 19
    //   841: new 170	java/lang/StringBuilder
    //   844: dup
    //   845: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   848: ldc_w 701
    //   851: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   854: getstatic 129	com/tapjoy/TapjoyConnectCore:storeName	Ljava/lang/String;
    //   857: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   860: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   863: invokestatic 707	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   866: pop
    //   867: aload_3
    //   868: ldc_w 709
    //   871: aconst_null
    //   872: invokeinterface 655 3 0
    //   877: astore_3
    //   878: aload_3
    //   879: ifnull +16 -> 895
    //   882: aload_3
    //   883: ldc 79
    //   885: invokevirtual 340	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   888: ifne +7 -> 895
    //   891: aload_3
    //   892: putstatic 135	com/tapjoy/TapjoyConnectCore:referralURL	Ljava/lang/String;
    //   895: getstatic 73	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   898: invokevirtual 514	android/content/Context:getPackageName	()Ljava/lang/String;
    //   901: putstatic 133	com/tapjoy/TapjoyConnectCore:clientPackage	Ljava/lang/String;
    //   904: ldc 19
    //   906: new 170	java/lang/StringBuilder
    //   909: dup
    //   910: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   913: ldc_w 711
    //   916: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   919: getstatic 105	com/tapjoy/TapjoyConnectCore:appID	Ljava/lang/String;
    //   922: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   925: ldc_w 640
    //   928: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   931: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   934: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   937: ldc 19
    //   939: new 170	java/lang/StringBuilder
    //   942: dup
    //   943: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   946: ldc_w 713
    //   949: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   952: getstatic 81	com/tapjoy/TapjoyConnectCore:androidID	Ljava/lang/String;
    //   955: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   958: ldc_w 640
    //   961: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   964: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   967: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   970: ldc 19
    //   972: new 170	java/lang/StringBuilder
    //   975: dup
    //   976: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   979: ldc_w 715
    //   982: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   985: getstatic 133	com/tapjoy/TapjoyConnectCore:clientPackage	Ljava/lang/String;
    //   988: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   991: ldc_w 640
    //   994: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   997: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1000: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1003: ldc 19
    //   1005: new 170	java/lang/StringBuilder
    //   1008: dup
    //   1009: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   1012: ldc_w 717
    //   1015: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1018: getstatic 83	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1021: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1024: ldc_w 640
    //   1027: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1030: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1033: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1036: ldc 19
    //   1038: new 170	java/lang/StringBuilder
    //   1041: dup
    //   1042: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   1045: ldc_w 719
    //   1048: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1051: getstatic 85	com/tapjoy/TapjoyConnectCore:sha2DeviceID	Ljava/lang/String;
    //   1054: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1057: ldc_w 640
    //   1060: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1063: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1066: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1069: ldc 19
    //   1071: new 170	java/lang/StringBuilder
    //   1074: dup
    //   1075: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   1078: ldc_w 721
    //   1081: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1084: getstatic 91	com/tapjoy/TapjoyConnectCore:serialID	Ljava/lang/String;
    //   1087: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1090: ldc_w 640
    //   1093: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1096: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1099: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1102: ldc 19
    //   1104: new 170	java/lang/StringBuilder
    //   1107: dup
    //   1108: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   1111: ldc_w 723
    //   1114: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1117: getstatic 87	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   1120: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1123: ldc_w 640
    //   1126: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1129: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1132: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1135: ldc 19
    //   1137: new 170	java/lang/StringBuilder
    //   1140: dup
    //   1141: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   1144: ldc_w 725
    //   1147: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1150: getstatic 89	com/tapjoy/TapjoyConnectCore:sha1MacAddress	Ljava/lang/String;
    //   1153: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1156: ldc_w 640
    //   1159: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1162: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1165: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1168: ldc 19
    //   1170: new 170	java/lang/StringBuilder
    //   1173: dup
    //   1174: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   1177: ldc_w 727
    //   1180: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1183: getstatic 93	com/tapjoy/TapjoyConnectCore:deviceModel	Ljava/lang/String;
    //   1186: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1189: ldc_w 640
    //   1192: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1195: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1198: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1201: ldc 19
    //   1203: new 170	java/lang/StringBuilder
    //   1206: dup
    //   1207: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   1210: ldc_w 729
    //   1213: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1216: getstatic 95	com/tapjoy/TapjoyConnectCore:deviceManufacturer	Ljava/lang/String;
    //   1219: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1222: ldc_w 640
    //   1225: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1228: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1231: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1234: ldc 19
    //   1236: new 170	java/lang/StringBuilder
    //   1239: dup
    //   1240: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   1243: ldc_w 731
    //   1246: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1249: getstatic 97	com/tapjoy/TapjoyConnectCore:deviceType	Ljava/lang/String;
    //   1252: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1255: ldc_w 640
    //   1258: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1261: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1264: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1267: ldc 19
    //   1269: new 170	java/lang/StringBuilder
    //   1272: dup
    //   1273: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   1276: ldc_w 733
    //   1279: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1282: getstatic 109	com/tapjoy/TapjoyConnectCore:libraryVersion	Ljava/lang/String;
    //   1285: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1288: ldc_w 640
    //   1291: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1294: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1297: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1300: ldc 19
    //   1302: new 170	java/lang/StringBuilder
    //   1305: dup
    //   1306: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   1309: ldc_w 735
    //   1312: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1315: getstatic 99	com/tapjoy/TapjoyConnectCore:deviceOSVersion	Ljava/lang/String;
    //   1318: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1321: ldc_w 640
    //   1324: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1327: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1330: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1333: ldc 19
    //   1335: new 170	java/lang/StringBuilder
    //   1338: dup
    //   1339: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   1342: ldc_w 737
    //   1345: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1348: getstatic 101	com/tapjoy/TapjoyConnectCore:deviceCountryCode	Ljava/lang/String;
    //   1351: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1354: ldc_w 640
    //   1357: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1360: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1363: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1366: ldc 19
    //   1368: new 170	java/lang/StringBuilder
    //   1371: dup
    //   1372: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   1375: ldc_w 739
    //   1378: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1381: getstatic 103	com/tapjoy/TapjoyConnectCore:deviceLanguage	Ljava/lang/String;
    //   1384: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1387: ldc_w 640
    //   1390: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1393: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1396: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1399: ldc 19
    //   1401: new 170	java/lang/StringBuilder
    //   1404: dup
    //   1405: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   1408: ldc_w 741
    //   1411: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1414: getstatic 111	com/tapjoy/TapjoyConnectCore:deviceScreenDensity	Ljava/lang/String;
    //   1417: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1420: ldc_w 640
    //   1423: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1426: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1429: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1432: ldc 19
    //   1434: new 170	java/lang/StringBuilder
    //   1437: dup
    //   1438: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   1441: ldc_w 743
    //   1444: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1447: getstatic 113	com/tapjoy/TapjoyConnectCore:deviceScreenLayoutSize	Ljava/lang/String;
    //   1450: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1453: ldc_w 640
    //   1456: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1459: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1462: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1465: ldc 19
    //   1467: new 170	java/lang/StringBuilder
    //   1470: dup
    //   1471: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   1474: ldc_w 745
    //   1477: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1480: getstatic 119	com/tapjoy/TapjoyConnectCore:carrierName	Ljava/lang/String;
    //   1483: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1486: ldc_w 640
    //   1489: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1492: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1495: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1498: ldc 19
    //   1500: new 170	java/lang/StringBuilder
    //   1503: dup
    //   1504: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   1507: ldc_w 747
    //   1510: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1513: getstatic 121	com/tapjoy/TapjoyConnectCore:carrierCountryCode	Ljava/lang/String;
    //   1516: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1519: ldc_w 640
    //   1522: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1525: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1528: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1531: ldc 19
    //   1533: new 170	java/lang/StringBuilder
    //   1536: dup
    //   1537: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   1540: ldc_w 749
    //   1543: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1546: getstatic 123	com/tapjoy/TapjoyConnectCore:mobileCountryCode	Ljava/lang/String;
    //   1549: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1552: ldc_w 640
    //   1555: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1558: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1561: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1564: ldc 19
    //   1566: new 170	java/lang/StringBuilder
    //   1569: dup
    //   1570: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   1573: ldc_w 751
    //   1576: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1579: getstatic 125	com/tapjoy/TapjoyConnectCore:mobileNetworkCode	Ljava/lang/String;
    //   1582: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1585: ldc_w 640
    //   1588: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1591: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1594: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1597: ldc 19
    //   1599: new 170	java/lang/StringBuilder
    //   1602: dup
    //   1603: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   1606: ldc_w 753
    //   1609: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1612: getstatic 129	com/tapjoy/TapjoyConnectCore:storeName	Ljava/lang/String;
    //   1615: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1618: ldc_w 640
    //   1621: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1624: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1627: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1630: ldc 19
    //   1632: new 170	java/lang/StringBuilder
    //   1635: dup
    //   1636: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   1639: ldc_w 755
    //   1642: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1645: getstatic 135	com/tapjoy/TapjoyConnectCore:referralURL	Ljava/lang/String;
    //   1648: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1651: ldc_w 640
    //   1654: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1657: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1660: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1663: getstatic 151	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   1666: ifnull +188 -> 1854
    //   1669: ldc 19
    //   1671: ldc_w 757
    //   1674: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1677: ldc 19
    //   1679: ldc_w 759
    //   1682: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1685: getstatic 151	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   1688: invokevirtual 763	java/util/Hashtable:entrySet	()Ljava/util/Set;
    //   1691: invokeinterface 766 1 0
    //   1696: astore_3
    //   1697: aload_3
    //   1698: invokeinterface 481 1 0
    //   1703: ifeq +151 -> 1854
    //   1706: aload_3
    //   1707: invokeinterface 485 1 0
    //   1712: checkcast 768	java/util/Map$Entry
    //   1715: astore 4
    //   1717: ldc 19
    //   1719: new 170	java/lang/StringBuilder
    //   1722: dup
    //   1723: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   1726: ldc_w 770
    //   1729: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1732: aload 4
    //   1734: invokeinterface 773 1 0
    //   1739: checkcast 294	java/lang/String
    //   1742: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1745: ldc_w 775
    //   1748: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1751: aload 4
    //   1753: invokeinterface 778 1 0
    //   1758: checkcast 294	java/lang/String
    //   1761: invokestatic 302	android/net/Uri:encode	(Ljava/lang/String;)Ljava/lang/String;
    //   1764: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1767: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1770: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1773: aload 4
    //   1775: invokeinterface 773 1 0
    //   1780: checkcast 294	java/lang/String
    //   1783: ldc_w 332
    //   1786: invokevirtual 340	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1789: ifeq -92 -> 1697
    //   1792: getstatic 141	com/tapjoy/TapjoyConnectCore:sdkType	Ljava/lang/String;
    //   1795: ldc_w 780
    //   1798: invokevirtual 340	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1801: ifne -104 -> 1697
    //   1804: ldc 19
    //   1806: ldc_w 782
    //   1809: invokestatic 784	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   1812: getstatic 151	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   1815: ldc_w 332
    //   1818: invokevirtual 787	java/util/Hashtable:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1821: pop
    //   1822: goto -125 -> 1697
    //   1825: astore_3
    //   1826: ldc 19
    //   1828: new 170	java/lang/StringBuilder
    //   1831: dup
    //   1832: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   1835: ldc_w 789
    //   1838: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1841: aload_3
    //   1842: invokevirtual 244	java/lang/Exception:toString	()Ljava/lang/String;
    //   1845: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1848: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1851: invokestatic 247	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1854: return
    //   1855: ldc 19
    //   1857: ldc_w 791
    //   1860: invokestatic 190	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1863: goto -1679 -> 184
    //   1866: astore 4
    //   1868: ldc 19
    //   1870: new 170	java/lang/StringBuilder
    //   1873: dup
    //   1874: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   1877: ldc_w 793
    //   1880: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1883: aload 4
    //   1885: invokevirtual 244	java/lang/Exception:toString	()Ljava/lang/String;
    //   1888: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1891: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1894: invokestatic 247	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1897: goto -1713 -> 184
    //   1900: getstatic 83	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1903: invokevirtual 345	java/lang/String:length	()I
    //   1906: ifeq +27 -> 1933
    //   1909: getstatic 83	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1912: ldc_w 795
    //   1915: invokevirtual 340	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1918: ifne +15 -> 1933
    //   1921: getstatic 83	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1924: ldc_w 797
    //   1927: invokevirtual 340	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1930: ifeq +16 -> 1946
    //   1933: ldc 19
    //   1935: ldc_w 799
    //   1938: invokestatic 247	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1941: iconst_1
    //   1942: istore_1
    //   1943: goto -1516 -> 427
    //   1946: getstatic 83	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1949: invokevirtual 802	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   1952: putstatic 83	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1955: goto -1528 -> 427
    //   1958: astore 4
    //   1960: ldc 19
    //   1962: new 170	java/lang/StringBuilder
    //   1965: dup
    //   1966: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   1969: ldc_w 804
    //   1972: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1975: aload 4
    //   1977: invokevirtual 244	java/lang/Exception:toString	()Ljava/lang/String;
    //   1980: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1983: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1986: invokestatic 247	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1989: aconst_null
    //   1990: putstatic 83	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1993: goto -1376 -> 617
    //   1996: getstatic 83	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1999: invokevirtual 345	java/lang/String:length	()I
    //   2002: ifeq +39 -> 2041
    //   2005: getstatic 83	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2008: ldc_w 795
    //   2011: invokevirtual 340	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2014: ifne +27 -> 2041
    //   2017: getstatic 83	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2020: ldc_w 797
    //   2023: invokevirtual 340	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2026: ifne +15 -> 2041
    //   2029: getstatic 83	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2032: ldc_w 806
    //   2035: invokevirtual 340	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2038: ifeq +16 -> 2054
    //   2041: ldc 19
    //   2043: ldc_w 808
    //   2046: invokestatic 247	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   2049: iconst_1
    //   2050: istore_2
    //   2051: goto -1488 -> 563
    //   2054: getstatic 83	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2057: invokevirtual 802	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   2060: putstatic 83	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2063: iconst_0
    //   2064: istore_2
    //   2065: goto -1502 -> 563
    //   2068: iload_1
    //   2069: bipush 32
    //   2071: if_icmpge +33 -> 2104
    //   2074: aload 4
    //   2076: ldc_w 810
    //   2079: invokestatic 816	java/lang/Math:random	()D
    //   2082: ldc2_w 817
    //   2085: dmul
    //   2086: d2i
    //   2087: bipush 30
    //   2089: irem
    //   2090: invokevirtual 822	java/lang/String:charAt	(I)C
    //   2093: invokevirtual 825	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   2096: pop
    //   2097: iload_1
    //   2098: iconst_1
    //   2099: iadd
    //   2100: istore_1
    //   2101: goto -33 -> 2068
    //   2104: aload 4
    //   2106: invokevirtual 826	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   2109: invokevirtual 802	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   2112: putstatic 83	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2115: aload_3
    //   2116: invokeinterface 830 1 0
    //   2121: astore 4
    //   2123: aload 4
    //   2125: ldc_w 652
    //   2128: getstatic 83	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2131: invokeinterface 836 3 0
    //   2136: pop
    //   2137: aload 4
    //   2139: invokeinterface 839 1 0
    //   2144: pop
    //   2145: goto -1528 -> 617
    //   2148: astore 4
    //   2150: ldc 19
    //   2152: new 170	java/lang/StringBuilder
    //   2155: dup
    //   2156: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   2159: ldc_w 841
    //   2162: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2165: aload 4
    //   2167: invokevirtual 244	java/lang/Exception:toString	()Ljava/lang/String;
    //   2170: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2173: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2176: invokestatic 247	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   2179: goto -1464 -> 715
    //   2182: astore 4
    //   2184: ldc 19
    //   2186: new 170	java/lang/StringBuilder
    //   2189: dup
    //   2190: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   2193: ldc_w 843
    //   2196: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2199: aload 4
    //   2201: invokevirtual 244	java/lang/Exception:toString	()Ljava/lang/String;
    //   2204: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2207: invokevirtual 184	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2210: invokestatic 247	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   2213: goto -1426 -> 787
    //   2216: iload_1
    //   2217: iconst_1
    //   2218: iadd
    //   2219: istore_1
    //   2220: goto -2154 -> 66
    //   2223: iconst_0
    //   2224: istore_1
    //   2225: goto -157 -> 2068
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2228	0	this	TapjoyConnectCore
    //   65	2160	1	i	int
    //   63	2002	2	j	int
    //   6	1701	3	localObject1	Object
    //   1825	291	3	localException1	Exception
    //   40	1734	4	localObject2	Object
    //   1866	18	4	localException2	Exception
    //   1958	147	4	localException3	Exception
    //   2121	17	4	localEditor	SharedPreferences.Editor
    //   2148	18	4	localException4	Exception
    //   2182	18	4	localException5	Exception
    //   58	555	5	localObject3	Object
    //   75	91	6	str	String
    //   106	62	7	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   184	281	1825	java/lang/Exception
    //   617	632	1825	java/lang/Exception
    //   632	641	1825	java/lang/Exception
    //   787	867	1825	java/lang/Exception
    //   867	878	1825	java/lang/Exception
    //   882	895	1825	java/lang/Exception
    //   895	1697	1825	java/lang/Exception
    //   1697	1822	1825	java/lang/Exception
    //   1960	1993	1825	java/lang/Exception
    //   2150	2179	1825	java/lang/Exception
    //   2184	2213	1825	java/lang/Exception
    //   7	23	1866	java/lang/Exception
    //   27	42	1866	java/lang/Exception
    //   47	64	1866	java/lang/Exception
    //   77	108	1866	java/lang/Exception
    //   113	120	1866	java/lang/Exception
    //   125	173	1866	java/lang/Exception
    //   176	184	1866	java/lang/Exception
    //   1855	1863	1866	java/lang/Exception
    //   281	295	1958	java/lang/Exception
    //   300	357	1958	java/lang/Exception
    //   357	382	1958	java/lang/Exception
    //   382	409	1958	java/lang/Exception
    //   411	425	1958	java/lang/Exception
    //   427	454	1958	java/lang/Exception
    //   456	488	1958	java/lang/Exception
    //   492	498	1958	java/lang/Exception
    //   498	561	1958	java/lang/Exception
    //   567	597	1958	java/lang/Exception
    //   602	617	1958	java/lang/Exception
    //   1900	1933	1958	java/lang/Exception
    //   1933	1941	1958	java/lang/Exception
    //   1946	1955	1958	java/lang/Exception
    //   1996	2041	1958	java/lang/Exception
    //   2041	2049	1958	java/lang/Exception
    //   2054	2063	1958	java/lang/Exception
    //   2074	2097	1958	java/lang/Exception
    //   2104	2145	1958	java/lang/Exception
    //   641	715	2148	java/lang/Exception
    //   715	729	2182	java/lang/Exception
    //   734	741	2182	java/lang/Exception
    //   746	787	2182	java/lang/Exception
  }
  
  public static void requestTapjoyConnect(Context paramContext, String paramString1, String paramString2)
  {
    requestTapjoyConnect(paramContext, paramString1, paramString2, null);
  }
  
  public static void requestTapjoyConnect(Context paramContext, String paramString1, String paramString2, Hashtable<String, String> paramHashtable)
  {
    appID = paramString1;
    secretKey = paramString2;
    connectFlags = paramHashtable;
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
      if ((localObject != null) && (((TapjoyHttpURLResponse)localObject).statusCode == 200))
      {
        if (TapjoyConnectCore.handleConnectResponse(((TapjoyHttpURLResponse)localObject).response)) {
          TapjoyLog.i("TapjoyConnect", "Successfully connected to tapjoy site.");
        }
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
      }
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
      TapjoyConnectCore.access$002(TapjoyConnectCore.this, TapjoyConnectCore.this.elapsed_time + 10000L);
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
