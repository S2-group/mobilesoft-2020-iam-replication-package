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
  private static boolean enableVideoCache;
  private static String libraryVersion;
  private static String macAddress;
  private static String marketName;
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
    marketName = "";
    secretKey = "";
    clientPackage = "";
    referralURL = "";
    plugin = "native";
    sdkType = "";
    videoEnabled = false;
    enableVideoCache = true;
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
  
  public static void enableVideoCache(boolean paramBoolean)
  {
    enableVideoCache = paramBoolean;
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
  
  private static String getFlagValue(String paramString)
  {
    if (connectFlags != null) {
      return (String)connectFlags.get(paramString);
    }
    return null;
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
      if (marketName.length() > 0)
      {
        localObject1 = (String)localObject2 + "&";
        localObject1 = (String)localObject1 + "store_name=" + Uri.encode(marketName);
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
  
  public static String getVideoIDs()
  {
    return videoIDs;
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
    //   0: getstatic 74	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   3: invokevirtual 461	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   6: astore_3
    //   7: getstatic 74	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   10: invokevirtual 512	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   13: ldc_w 514
    //   16: invokestatic 520	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   19: putstatic 82	com/tapjoy/TapjoyConnectCore:androidID	Ljava/lang/String;
    //   22: aload_3
    //   23: getstatic 74	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   26: invokevirtual 523	android/content/Context:getPackageName	()Ljava/lang/String;
    //   29: iconst_0
    //   30: invokevirtual 527	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   33: getfield 532	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   36: putstatic 108	com/tapjoy/TapjoyConnectCore:appVersion	Ljava/lang/String;
    //   39: ldc_w 534
    //   42: putstatic 98	com/tapjoy/TapjoyConnectCore:deviceType	Ljava/lang/String;
    //   45: ldc_w 534
    //   48: putstatic 118	com/tapjoy/TapjoyConnectCore:platformName	Ljava/lang/String;
    //   51: getstatic 539	android/os/Build:MODEL	Ljava/lang/String;
    //   54: putstatic 94	com/tapjoy/TapjoyConnectCore:deviceModel	Ljava/lang/String;
    //   57: getstatic 542	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   60: putstatic 96	com/tapjoy/TapjoyConnectCore:deviceManufacturer	Ljava/lang/String;
    //   63: getstatic 547	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   66: putstatic 100	com/tapjoy/TapjoyConnectCore:deviceOSVersion	Ljava/lang/String;
    //   69: invokestatic 553	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   72: invokevirtual 556	java/util/Locale:getCountry	()Ljava/lang/String;
    //   75: putstatic 102	com/tapjoy/TapjoyConnectCore:deviceCountryCode	Ljava/lang/String;
    //   78: invokestatic 553	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   81: invokevirtual 559	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   84: putstatic 104	com/tapjoy/TapjoyConnectCore:deviceLanguage	Ljava/lang/String;
    //   87: ldc_w 561
    //   90: putstatic 110	com/tapjoy/TapjoyConnectCore:libraryVersion	Ljava/lang/String;
    //   93: getstatic 74	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   96: ldc_w 316
    //   99: iconst_0
    //   100: invokevirtual 320	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   103: astore_3
    //   104: getstatic 74	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   107: ldc_w 563
    //   110: invokevirtual 262	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   113: checkcast 565	android/telephony/TelephonyManager
    //   116: astore 4
    //   118: aload 4
    //   120: ifnull +85 -> 205
    //   123: aload 4
    //   125: invokevirtual 568	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   128: putstatic 84	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   131: aload 4
    //   133: invokevirtual 571	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   136: putstatic 120	com/tapjoy/TapjoyConnectCore:carrierName	Ljava/lang/String;
    //   139: aload 4
    //   141: invokevirtual 574	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   144: putstatic 122	com/tapjoy/TapjoyConnectCore:carrierCountryCode	Ljava/lang/String;
    //   147: aload 4
    //   149: invokevirtual 577	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   152: ifnull +53 -> 205
    //   155: aload 4
    //   157: invokevirtual 577	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   160: invokevirtual 349	java/lang/String:length	()I
    //   163: iconst_5
    //   164: if_icmpeq +16 -> 180
    //   167: aload 4
    //   169: invokevirtual 577	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   172: invokevirtual 349	java/lang/String:length	()I
    //   175: bipush 6
    //   177: if_icmpne +28 -> 205
    //   180: aload 4
    //   182: invokevirtual 577	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   185: iconst_0
    //   186: iconst_3
    //   187: invokevirtual 502	java/lang/String:substring	(II)Ljava/lang/String;
    //   190: putstatic 124	com/tapjoy/TapjoyConnectCore:mobileCountryCode	Ljava/lang/String;
    //   193: aload 4
    //   195: invokevirtual 577	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   198: iconst_3
    //   199: invokevirtual 451	java/lang/String:substring	(I)Ljava/lang/String;
    //   202: putstatic 126	com/tapjoy/TapjoyConnectCore:mobileNetworkCode	Ljava/lang/String;
    //   205: ldc 19
    //   207: new 173	java/lang/StringBuilder
    //   210: dup
    //   211: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   214: ldc_w 579
    //   217: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: getstatic 84	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   223: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   226: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   229: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   232: iconst_0
    //   233: istore_1
    //   234: getstatic 84	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   237: ifnonnull +1395 -> 1632
    //   240: ldc 19
    //   242: ldc_w 581
    //   245: invokestatic 251	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   248: iconst_1
    //   249: istore_1
    //   250: ldc 19
    //   252: new 173	java/lang/StringBuilder
    //   255: dup
    //   256: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   259: ldc_w 583
    //   262: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   265: getstatic 586	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   268: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   274: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   277: iload_1
    //   278: istore_2
    //   279: getstatic 586	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   282: invokestatic 592	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   285: bipush 9
    //   287: if_icmplt +99 -> 386
    //   290: ldc 19
    //   292: ldc_w 594
    //   295: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   298: new 596	com/tapjoy/TapjoyHardwareUtil
    //   301: dup
    //   302: invokespecial 597	com/tapjoy/TapjoyHardwareUtil:<init>	()V
    //   305: invokevirtual 600	com/tapjoy/TapjoyHardwareUtil:getSerial	()Ljava/lang/String;
    //   308: putstatic 92	com/tapjoy/TapjoyConnectCore:serialID	Ljava/lang/String;
    //   311: iload_1
    //   312: ifeq +9 -> 321
    //   315: getstatic 92	com/tapjoy/TapjoyConnectCore:serialID	Ljava/lang/String;
    //   318: putstatic 84	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   321: ldc 19
    //   323: ldc_w 602
    //   326: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   329: ldc 19
    //   331: new 173	java/lang/StringBuilder
    //   334: dup
    //   335: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   338: ldc_w 604
    //   341: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   344: getstatic 84	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   347: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   350: ldc_w 606
    //   353: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   356: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   359: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   362: ldc 19
    //   364: ldc_w 602
    //   367: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   370: getstatic 84	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   373: ifnonnull +1355 -> 1728
    //   376: ldc 19
    //   378: ldc_w 608
    //   381: invokestatic 251	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   384: iconst_1
    //   385: istore_2
    //   386: iload_2
    //   387: ifeq +53 -> 440
    //   390: new 610	java/lang/StringBuffer
    //   393: dup
    //   394: invokespecial 611	java/lang/StringBuffer:<init>	()V
    //   397: astore 4
    //   399: aload 4
    //   401: ldc_w 613
    //   404: invokevirtual 616	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   407: pop
    //   408: aload_3
    //   409: ldc_w 618
    //   412: aconst_null
    //   413: invokeinterface 621 3 0
    //   418: astore 5
    //   420: aload 5
    //   422: ifnull +1526 -> 1948
    //   425: aload 5
    //   427: ldc 80
    //   429: invokevirtual 344	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   432: ifne +1516 -> 1948
    //   435: aload 5
    //   437: putstatic 84	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   440: getstatic 116	com/tapjoy/TapjoyConnectCore:userID	Ljava/lang/String;
    //   443: invokevirtual 349	java/lang/String:length	()I
    //   446: ifne +9 -> 455
    //   449: getstatic 84	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   452: putstatic 116	com/tapjoy/TapjoyConnectCore:userID	Ljava/lang/String;
    //   455: getstatic 84	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   458: invokestatic 245	com/tapjoy/TapjoyUtil:SHA256	(Ljava/lang/String;)Ljava/lang/String;
    //   461: putstatic 86	com/tapjoy/TapjoyConnectCore:sha2DeviceID	Ljava/lang/String;
    //   464: getstatic 586	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   467: invokestatic 592	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   470: iconst_3
    //   471: if_icmple +67 -> 538
    //   474: new 623	com/tapjoy/TapjoyDisplayMetricsUtil
    //   477: dup
    //   478: getstatic 74	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   481: invokespecial 625	com/tapjoy/TapjoyDisplayMetricsUtil:<init>	(Landroid/content/Context;)V
    //   484: astore 4
    //   486: new 173	java/lang/StringBuilder
    //   489: dup
    //   490: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   493: ldc 80
    //   495: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   498: aload 4
    //   500: invokevirtual 628	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensity	()I
    //   503: invokevirtual 239	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   506: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   509: putstatic 112	com/tapjoy/TapjoyConnectCore:deviceScreenDensity	Ljava/lang/String;
    //   512: new 173	java/lang/StringBuilder
    //   515: dup
    //   516: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   519: ldc 80
    //   521: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   524: aload 4
    //   526: invokevirtual 631	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenLayoutSize	()I
    //   529: invokevirtual 239	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   532: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   535: putstatic 114	com/tapjoy/TapjoyConnectCore:deviceScreenLayoutSize	Ljava/lang/String;
    //   538: getstatic 74	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   541: ldc_w 280
    //   544: invokevirtual 262	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   547: checkcast 633	android/net/wifi/WifiManager
    //   550: astore 4
    //   552: aload 4
    //   554: ifnull +56 -> 610
    //   557: aload 4
    //   559: invokevirtual 637	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   562: astore 4
    //   564: aload 4
    //   566: ifnull +44 -> 610
    //   569: aload 4
    //   571: invokevirtual 642	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   574: putstatic 88	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   577: getstatic 88	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   580: ifnull +30 -> 610
    //   583: getstatic 88	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   586: invokevirtual 349	java/lang/String:length	()I
    //   589: ifle +21 -> 610
    //   592: getstatic 88	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   595: invokevirtual 645	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   598: putstatic 88	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   601: getstatic 88	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   604: invokestatic 648	com/tapjoy/TapjoyUtil:SHA1	(Ljava/lang/String;)Ljava/lang/String;
    //   607: putstatic 90	com/tapjoy/TapjoyConnectCore:sha1MacAddress	Ljava/lang/String;
    //   610: ldc_w 650
    //   613: invokestatic 338	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   616: ifnull +69 -> 685
    //   619: ldc_w 650
    //   622: invokestatic 338	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   625: putstatic 130	com/tapjoy/TapjoyConnectCore:marketName	Ljava/lang/String;
    //   628: new 652	java/util/ArrayList
    //   631: dup
    //   632: invokespecial 653	java/util/ArrayList:<init>	()V
    //   635: astore 4
    //   637: aload 4
    //   639: ldc_w 655
    //   642: invokevirtual 656	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   645: pop
    //   646: aload 4
    //   648: getstatic 130	com/tapjoy/TapjoyConnectCore:marketName	Ljava/lang/String;
    //   651: invokevirtual 657	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   654: ifne +31 -> 685
    //   657: ldc 19
    //   659: new 173	java/lang/StringBuilder
    //   662: dup
    //   663: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   666: ldc_w 659
    //   669: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   672: getstatic 130	com/tapjoy/TapjoyConnectCore:marketName	Ljava/lang/String;
    //   675: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   678: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   681: invokestatic 665	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   684: pop
    //   685: aload_3
    //   686: ldc_w 667
    //   689: aconst_null
    //   690: invokeinterface 621 3 0
    //   695: astore_3
    //   696: aload_3
    //   697: ifnull +16 -> 713
    //   700: aload_3
    //   701: ldc 80
    //   703: invokevirtual 344	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   706: ifne +7 -> 713
    //   709: aload_3
    //   710: putstatic 136	com/tapjoy/TapjoyConnectCore:referralURL	Ljava/lang/String;
    //   713: getstatic 74	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   716: invokevirtual 523	android/content/Context:getPackageName	()Ljava/lang/String;
    //   719: putstatic 134	com/tapjoy/TapjoyConnectCore:clientPackage	Ljava/lang/String;
    //   722: ldc 19
    //   724: ldc_w 669
    //   727: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   730: ldc 19
    //   732: new 173	java/lang/StringBuilder
    //   735: dup
    //   736: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   739: ldc_w 671
    //   742: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   745: getstatic 106	com/tapjoy/TapjoyConnectCore:appID	Ljava/lang/String;
    //   748: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   751: ldc_w 606
    //   754: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   757: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   760: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   763: ldc 19
    //   765: new 173	java/lang/StringBuilder
    //   768: dup
    //   769: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   772: ldc_w 673
    //   775: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   778: getstatic 82	com/tapjoy/TapjoyConnectCore:androidID	Ljava/lang/String;
    //   781: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   784: ldc_w 606
    //   787: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   790: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   793: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   796: ldc 19
    //   798: new 173	java/lang/StringBuilder
    //   801: dup
    //   802: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   805: ldc_w 675
    //   808: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   811: getstatic 134	com/tapjoy/TapjoyConnectCore:clientPackage	Ljava/lang/String;
    //   814: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   817: ldc_w 606
    //   820: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   823: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   826: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   829: ldc 19
    //   831: new 173	java/lang/StringBuilder
    //   834: dup
    //   835: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   838: ldc_w 677
    //   841: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   844: getstatic 84	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   847: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   850: ldc_w 606
    //   853: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   856: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   859: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   862: ldc 19
    //   864: new 173	java/lang/StringBuilder
    //   867: dup
    //   868: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   871: ldc_w 679
    //   874: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   877: getstatic 86	com/tapjoy/TapjoyConnectCore:sha2DeviceID	Ljava/lang/String;
    //   880: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   883: ldc_w 606
    //   886: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   889: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   892: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   895: ldc 19
    //   897: new 173	java/lang/StringBuilder
    //   900: dup
    //   901: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   904: ldc_w 681
    //   907: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   910: getstatic 92	com/tapjoy/TapjoyConnectCore:serialID	Ljava/lang/String;
    //   913: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   916: ldc_w 606
    //   919: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   922: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   925: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   928: ldc 19
    //   930: new 173	java/lang/StringBuilder
    //   933: dup
    //   934: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   937: ldc_w 683
    //   940: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   943: getstatic 88	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   946: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   949: ldc_w 606
    //   952: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   955: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   958: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   961: ldc 19
    //   963: new 173	java/lang/StringBuilder
    //   966: dup
    //   967: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   970: ldc_w 685
    //   973: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   976: getstatic 90	com/tapjoy/TapjoyConnectCore:sha1MacAddress	Ljava/lang/String;
    //   979: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   982: ldc_w 606
    //   985: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   988: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   991: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   994: ldc 19
    //   996: new 173	java/lang/StringBuilder
    //   999: dup
    //   1000: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   1003: ldc_w 687
    //   1006: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1009: getstatic 94	com/tapjoy/TapjoyConnectCore:deviceModel	Ljava/lang/String;
    //   1012: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1015: ldc_w 606
    //   1018: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1021: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1024: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1027: ldc 19
    //   1029: new 173	java/lang/StringBuilder
    //   1032: dup
    //   1033: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   1036: ldc_w 689
    //   1039: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1042: getstatic 96	com/tapjoy/TapjoyConnectCore:deviceManufacturer	Ljava/lang/String;
    //   1045: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1048: ldc_w 606
    //   1051: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1054: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1057: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1060: ldc 19
    //   1062: new 173	java/lang/StringBuilder
    //   1065: dup
    //   1066: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   1069: ldc_w 691
    //   1072: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1075: getstatic 98	com/tapjoy/TapjoyConnectCore:deviceType	Ljava/lang/String;
    //   1078: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1081: ldc_w 606
    //   1084: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1087: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1090: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1093: ldc 19
    //   1095: new 173	java/lang/StringBuilder
    //   1098: dup
    //   1099: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   1102: ldc_w 693
    //   1105: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1108: getstatic 110	com/tapjoy/TapjoyConnectCore:libraryVersion	Ljava/lang/String;
    //   1111: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1114: ldc_w 606
    //   1117: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1120: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1123: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1126: ldc 19
    //   1128: new 173	java/lang/StringBuilder
    //   1131: dup
    //   1132: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   1135: ldc_w 695
    //   1138: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1141: getstatic 100	com/tapjoy/TapjoyConnectCore:deviceOSVersion	Ljava/lang/String;
    //   1144: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1147: ldc_w 606
    //   1150: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1153: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1156: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1159: ldc 19
    //   1161: new 173	java/lang/StringBuilder
    //   1164: dup
    //   1165: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   1168: ldc_w 697
    //   1171: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1174: getstatic 102	com/tapjoy/TapjoyConnectCore:deviceCountryCode	Ljava/lang/String;
    //   1177: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1180: ldc_w 606
    //   1183: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1186: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1189: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1192: ldc 19
    //   1194: new 173	java/lang/StringBuilder
    //   1197: dup
    //   1198: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   1201: ldc_w 699
    //   1204: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1207: getstatic 104	com/tapjoy/TapjoyConnectCore:deviceLanguage	Ljava/lang/String;
    //   1210: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1213: ldc_w 606
    //   1216: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1219: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1222: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1225: ldc 19
    //   1227: new 173	java/lang/StringBuilder
    //   1230: dup
    //   1231: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   1234: ldc_w 701
    //   1237: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1240: getstatic 112	com/tapjoy/TapjoyConnectCore:deviceScreenDensity	Ljava/lang/String;
    //   1243: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1246: ldc_w 606
    //   1249: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1252: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1255: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1258: ldc 19
    //   1260: new 173	java/lang/StringBuilder
    //   1263: dup
    //   1264: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   1267: ldc_w 703
    //   1270: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1273: getstatic 114	com/tapjoy/TapjoyConnectCore:deviceScreenLayoutSize	Ljava/lang/String;
    //   1276: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1279: ldc_w 606
    //   1282: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1285: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1288: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1291: ldc 19
    //   1293: new 173	java/lang/StringBuilder
    //   1296: dup
    //   1297: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   1300: ldc_w 705
    //   1303: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1306: getstatic 120	com/tapjoy/TapjoyConnectCore:carrierName	Ljava/lang/String;
    //   1309: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1312: ldc_w 606
    //   1315: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1318: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1321: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1324: ldc 19
    //   1326: new 173	java/lang/StringBuilder
    //   1329: dup
    //   1330: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   1333: ldc_w 707
    //   1336: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1339: getstatic 122	com/tapjoy/TapjoyConnectCore:carrierCountryCode	Ljava/lang/String;
    //   1342: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1345: ldc_w 606
    //   1348: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1351: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1354: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1357: ldc 19
    //   1359: new 173	java/lang/StringBuilder
    //   1362: dup
    //   1363: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   1366: ldc_w 709
    //   1369: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1372: getstatic 124	com/tapjoy/TapjoyConnectCore:mobileCountryCode	Ljava/lang/String;
    //   1375: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1378: ldc_w 606
    //   1381: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1384: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1387: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1390: ldc 19
    //   1392: new 173	java/lang/StringBuilder
    //   1395: dup
    //   1396: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   1399: ldc_w 711
    //   1402: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1405: getstatic 126	com/tapjoy/TapjoyConnectCore:mobileNetworkCode	Ljava/lang/String;
    //   1408: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1411: ldc_w 606
    //   1414: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1417: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1420: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1423: ldc 19
    //   1425: new 173	java/lang/StringBuilder
    //   1428: dup
    //   1429: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   1432: ldc_w 713
    //   1435: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1438: getstatic 130	com/tapjoy/TapjoyConnectCore:marketName	Ljava/lang/String;
    //   1441: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1444: ldc_w 606
    //   1447: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1450: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1453: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1456: ldc 19
    //   1458: new 173	java/lang/StringBuilder
    //   1461: dup
    //   1462: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   1465: ldc_w 715
    //   1468: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1471: getstatic 136	com/tapjoy/TapjoyConnectCore:referralURL	Ljava/lang/String;
    //   1474: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1477: ldc_w 606
    //   1480: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1483: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1486: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1489: getstatic 154	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   1492: ifnull +139 -> 1631
    //   1495: ldc 19
    //   1497: ldc_w 717
    //   1500: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1503: ldc 19
    //   1505: ldc_w 719
    //   1508: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1511: getstatic 154	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   1514: invokevirtual 723	java/util/Hashtable:entrySet	()Ljava/util/Set;
    //   1517: invokeinterface 726 1 0
    //   1522: astore_3
    //   1523: aload_3
    //   1524: invokeinterface 479 1 0
    //   1529: ifeq +102 -> 1631
    //   1532: aload_3
    //   1533: invokeinterface 483 1 0
    //   1538: checkcast 728	java/util/Map$Entry
    //   1541: astore 4
    //   1543: ldc 19
    //   1545: new 173	java/lang/StringBuilder
    //   1548: dup
    //   1549: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   1552: ldc_w 730
    //   1555: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1558: aload 4
    //   1560: invokeinterface 733 1 0
    //   1565: checkcast 298	java/lang/String
    //   1568: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1571: ldc_w 735
    //   1574: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1577: aload 4
    //   1579: invokeinterface 738 1 0
    //   1584: checkcast 298	java/lang/String
    //   1587: invokestatic 306	android/net/Uri:encode	(Ljava/lang/String;)Ljava/lang/String;
    //   1590: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1593: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1596: invokestatic 193	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1599: goto -76 -> 1523
    //   1602: astore_3
    //   1603: ldc 19
    //   1605: new 173	java/lang/StringBuilder
    //   1608: dup
    //   1609: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   1612: ldc_w 740
    //   1615: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1618: aload_3
    //   1619: invokevirtual 248	java/lang/Exception:toString	()Ljava/lang/String;
    //   1622: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1625: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1628: invokestatic 251	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1631: return
    //   1632: getstatic 84	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1635: invokevirtual 349	java/lang/String:length	()I
    //   1638: ifeq +27 -> 1665
    //   1641: getstatic 84	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1644: ldc_w 742
    //   1647: invokevirtual 344	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1650: ifne +15 -> 1665
    //   1653: getstatic 84	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1656: ldc_w 744
    //   1659: invokevirtual 344	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1662: ifeq +16 -> 1678
    //   1665: ldc 19
    //   1667: ldc_w 746
    //   1670: invokestatic 251	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1673: iconst_1
    //   1674: istore_1
    //   1675: goto -1425 -> 250
    //   1678: getstatic 84	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1681: invokevirtual 749	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   1684: putstatic 84	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1687: goto -1437 -> 250
    //   1690: astore 4
    //   1692: ldc 19
    //   1694: new 173	java/lang/StringBuilder
    //   1697: dup
    //   1698: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   1701: ldc_w 751
    //   1704: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1707: aload 4
    //   1709: invokevirtual 248	java/lang/Exception:toString	()Ljava/lang/String;
    //   1712: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1715: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1718: invokestatic 251	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1721: aconst_null
    //   1722: putstatic 84	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1725: goto -1285 -> 440
    //   1728: getstatic 84	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1731: invokevirtual 349	java/lang/String:length	()I
    //   1734: ifeq +39 -> 1773
    //   1737: getstatic 84	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1740: ldc_w 742
    //   1743: invokevirtual 344	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1746: ifne +27 -> 1773
    //   1749: getstatic 84	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1752: ldc_w 744
    //   1755: invokevirtual 344	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1758: ifne +15 -> 1773
    //   1761: getstatic 84	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1764: ldc_w 753
    //   1767: invokevirtual 344	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1770: ifeq +16 -> 1786
    //   1773: ldc 19
    //   1775: ldc_w 755
    //   1778: invokestatic 251	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1781: iconst_1
    //   1782: istore_2
    //   1783: goto -1397 -> 386
    //   1786: getstatic 84	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1789: invokevirtual 749	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   1792: putstatic 84	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1795: iconst_0
    //   1796: istore_2
    //   1797: goto -1411 -> 386
    //   1800: iload_1
    //   1801: bipush 32
    //   1803: if_icmpge +33 -> 1836
    //   1806: aload 4
    //   1808: ldc_w 757
    //   1811: invokestatic 763	java/lang/Math:random	()D
    //   1814: ldc2_w 764
    //   1817: dmul
    //   1818: d2i
    //   1819: bipush 30
    //   1821: irem
    //   1822: invokevirtual 769	java/lang/String:charAt	(I)C
    //   1825: invokevirtual 772	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   1828: pop
    //   1829: iload_1
    //   1830: iconst_1
    //   1831: iadd
    //   1832: istore_1
    //   1833: goto -33 -> 1800
    //   1836: aload 4
    //   1838: invokevirtual 773	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   1841: invokevirtual 749	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   1844: putstatic 84	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1847: aload_3
    //   1848: invokeinterface 777 1 0
    //   1853: astore 4
    //   1855: aload 4
    //   1857: ldc_w 618
    //   1860: getstatic 84	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1863: invokeinterface 783 3 0
    //   1868: pop
    //   1869: aload 4
    //   1871: invokeinterface 786 1 0
    //   1876: pop
    //   1877: goto -1437 -> 440
    //   1880: astore 4
    //   1882: ldc 19
    //   1884: new 173	java/lang/StringBuilder
    //   1887: dup
    //   1888: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   1891: ldc_w 788
    //   1894: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1897: aload 4
    //   1899: invokevirtual 248	java/lang/Exception:toString	()Ljava/lang/String;
    //   1902: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1905: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1908: invokestatic 251	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1911: goto -1373 -> 538
    //   1914: astore 4
    //   1916: ldc 19
    //   1918: new 173	java/lang/StringBuilder
    //   1921: dup
    //   1922: invokespecial 174	java/lang/StringBuilder:<init>	()V
    //   1925: ldc_w 790
    //   1928: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1931: aload 4
    //   1933: invokevirtual 248	java/lang/Exception:toString	()Ljava/lang/String;
    //   1936: invokevirtual 180	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1939: invokevirtual 187	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1942: invokestatic 251	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1945: goto -1335 -> 610
    //   1948: iconst_0
    //   1949: istore_1
    //   1950: goto -150 -> 1800
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1953	0	this	TapjoyConnectCore
    //   233	1717	1	i	int
    //   278	1519	2	j	int
    //   6	1527	3	localObject1	Object
    //   1602	246	3	localException1	Exception
    //   116	1462	4	localObject2	Object
    //   1690	147	4	localException2	Exception
    //   1853	17	4	localEditor	SharedPreferences.Editor
    //   1880	18	4	localException3	Exception
    //   1914	18	4	localException4	Exception
    //   418	18	5	str	String
    // Exception table:
    //   from	to	target	type
    //   7	104	1602	java/lang/Exception
    //   440	455	1602	java/lang/Exception
    //   455	464	1602	java/lang/Exception
    //   610	685	1602	java/lang/Exception
    //   685	696	1602	java/lang/Exception
    //   700	713	1602	java/lang/Exception
    //   713	1523	1602	java/lang/Exception
    //   1523	1599	1602	java/lang/Exception
    //   1692	1725	1602	java/lang/Exception
    //   1882	1911	1602	java/lang/Exception
    //   1916	1945	1602	java/lang/Exception
    //   104	118	1690	java/lang/Exception
    //   123	180	1690	java/lang/Exception
    //   180	205	1690	java/lang/Exception
    //   205	232	1690	java/lang/Exception
    //   234	248	1690	java/lang/Exception
    //   250	277	1690	java/lang/Exception
    //   279	311	1690	java/lang/Exception
    //   315	321	1690	java/lang/Exception
    //   321	384	1690	java/lang/Exception
    //   390	420	1690	java/lang/Exception
    //   425	440	1690	java/lang/Exception
    //   1632	1665	1690	java/lang/Exception
    //   1665	1673	1690	java/lang/Exception
    //   1678	1687	1690	java/lang/Exception
    //   1728	1773	1690	java/lang/Exception
    //   1773	1781	1690	java/lang/Exception
    //   1786	1795	1690	java/lang/Exception
    //   1806	1829	1690	java/lang/Exception
    //   1836	1877	1690	java/lang/Exception
    //   464	538	1880	java/lang/Exception
    //   538	552	1914	java/lang/Exception
    //   557	564	1914	java/lang/Exception
    //   569	610	1914	java/lang/Exception
  }
  
  public static boolean isVideoCacheEnabled()
  {
    return enableVideoCache;
  }
  
  public static boolean isVideoEnabled()
  {
    return videoEnabled;
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
