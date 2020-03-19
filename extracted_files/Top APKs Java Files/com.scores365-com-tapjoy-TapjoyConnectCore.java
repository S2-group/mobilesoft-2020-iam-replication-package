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
  private static String sha2DeviceID;
  private static String storeName;
  private static TapjoyConnectCore tapjoyConnectCore = null;
  private static TapjoyURLConnection tapjoyURLConnection = null;
  private static String userID;
  private static boolean videoEnabled;
  private static String videoIDs;
  private static TapjoyViewNotifier viewNotifier;
  private long elapsed_time = 0L;
  private Timer timer = null;
  
  static
  {
    connectNotifier = null;
    viewNotifier = null;
    androidID = "";
    deviceID = "";
    sha2DeviceID = "";
    macAddress = "";
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
          localObject1 = (String)localObject2 + "mac_address=" + Uri.encode(macAddress) + "&";
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
    //   0: getstatic 76	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   3: invokevirtual 470	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   6: astore_3
    //   7: getstatic 156	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   10: ifnonnull +13 -> 23
    //   13: new 302	java/util/Hashtable
    //   16: dup
    //   17: invokespecial 518	java/util/Hashtable:<init>	()V
    //   20: putstatic 156	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   23: aload_3
    //   24: ifnull +160 -> 184
    //   27: aload_3
    //   28: getstatic 76	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   31: invokevirtual 521	android/content/Context:getPackageName	()Ljava/lang/String;
    //   34: sipush 128
    //   37: invokevirtual 525	android/content/pm/PackageManager:getApplicationInfo	(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
    //   40: astore 4
    //   42: aload 4
    //   44: ifnull +1828 -> 1872
    //   47: aload 4
    //   49: getfield 529	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   52: ifnull +1820 -> 1872
    //   55: getstatic 535	com/tapjoy/TapjoyConnectFlag:FLAG_ARRAY	[Ljava/lang/String;
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
    //   79: getfield 529	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   82: new 175	java/lang/StringBuilder
    //   85: dup
    //   86: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   89: ldc_w 537
    //   92: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: aload 6
    //   97: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   103: invokevirtual 541	android/os/Bundle:get	(Ljava/lang/String;)Ljava/lang/Object;
    //   106: astore 7
    //   108: aload 7
    //   110: ifnull +2143 -> 2253
    //   113: aload 7
    //   115: invokevirtual 542	java/lang/Object:toString	()Ljava/lang/String;
    //   118: astore 7
    //   120: aload 7
    //   122: ifnull +2131 -> 2253
    //   125: ldc 19
    //   127: new 175	java/lang/StringBuilder
    //   130: dup
    //   131: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   134: ldc_w 544
    //   137: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: aload 6
    //   142: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: ldc_w 546
    //   148: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: aload 7
    //   153: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   156: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   159: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   162: getstatic 156	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   165: aload 6
    //   167: aload 7
    //   169: invokevirtual 550	java/util/Hashtable:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   172: pop
    //   173: goto +2080 -> 2253
    //   176: ldc 19
    //   178: ldc_w 552
    //   181: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   184: ldc_w 554
    //   187: invokestatic 204	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   190: ifnull +22 -> 212
    //   193: ldc_w 554
    //   196: invokestatic 204	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   199: ldc_w 346
    //   202: invokevirtual 350	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   205: ifeq +7 -> 212
    //   208: iconst_1
    //   209: invokestatic 558	com/tapjoy/TapjoyLog:enableLogging	(Z)V
    //   212: getstatic 76	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   215: invokevirtual 562	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   218: ldc_w 564
    //   221: invokestatic 570	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   224: putstatic 88	com/tapjoy/TapjoyConnectCore:androidID	Ljava/lang/String;
    //   227: aload_3
    //   228: getstatic 76	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   231: invokevirtual 521	android/content/Context:getPackageName	()Ljava/lang/String;
    //   234: iconst_0
    //   235: invokevirtual 574	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   238: getfield 579	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   241: putstatic 112	com/tapjoy/TapjoyConnectCore:appVersion	Ljava/lang/String;
    //   244: ldc_w 581
    //   247: putstatic 102	com/tapjoy/TapjoyConnectCore:deviceType	Ljava/lang/String;
    //   250: ldc_w 581
    //   253: putstatic 122	com/tapjoy/TapjoyConnectCore:platformName	Ljava/lang/String;
    //   256: getstatic 586	android/os/Build:MODEL	Ljava/lang/String;
    //   259: putstatic 98	com/tapjoy/TapjoyConnectCore:deviceModel	Ljava/lang/String;
    //   262: getstatic 589	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   265: putstatic 100	com/tapjoy/TapjoyConnectCore:deviceManufacturer	Ljava/lang/String;
    //   268: getstatic 594	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   271: putstatic 104	com/tapjoy/TapjoyConnectCore:deviceOSVersion	Ljava/lang/String;
    //   274: invokestatic 600	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   277: invokevirtual 603	java/util/Locale:getCountry	()Ljava/lang/String;
    //   280: putstatic 106	com/tapjoy/TapjoyConnectCore:deviceCountryCode	Ljava/lang/String;
    //   283: invokestatic 600	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   286: invokevirtual 606	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   289: putstatic 108	com/tapjoy/TapjoyConnectCore:deviceLanguage	Ljava/lang/String;
    //   292: ldc_w 608
    //   295: putstatic 114	com/tapjoy/TapjoyConnectCore:libraryVersion	Ljava/lang/String;
    //   298: getstatic 76	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   301: ldc_w 324
    //   304: iconst_0
    //   305: invokevirtual 328	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   308: astore_3
    //   309: getstatic 76	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   312: ldc_w 610
    //   315: invokevirtual 274	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   318: checkcast 612	android/telephony/TelephonyManager
    //   321: astore 4
    //   323: aload 4
    //   325: ifnull +107 -> 432
    //   328: ldc_w 614
    //   331: invokestatic 204	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   334: ifnull +1583 -> 1917
    //   337: ldc_w 614
    //   340: invokestatic 204	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   343: invokevirtual 210	java/lang/String:length	()I
    //   346: ifle +1571 -> 1917
    //   349: ldc_w 614
    //   352: invokestatic 204	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   355: putstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   358: aload 4
    //   360: invokevirtual 617	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   363: putstatic 124	com/tapjoy/TapjoyConnectCore:carrierName	Ljava/lang/String;
    //   366: aload 4
    //   368: invokevirtual 620	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   371: putstatic 126	com/tapjoy/TapjoyConnectCore:carrierCountryCode	Ljava/lang/String;
    //   374: aload 4
    //   376: invokevirtual 623	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   379: ifnull +53 -> 432
    //   382: aload 4
    //   384: invokevirtual 623	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   387: invokevirtual 210	java/lang/String:length	()I
    //   390: iconst_5
    //   391: if_icmpeq +16 -> 407
    //   394: aload 4
    //   396: invokevirtual 623	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   399: invokevirtual 210	java/lang/String:length	()I
    //   402: bipush 6
    //   404: if_icmpne +28 -> 432
    //   407: aload 4
    //   409: invokevirtual 623	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   412: iconst_0
    //   413: iconst_3
    //   414: invokevirtual 511	java/lang/String:substring	(II)Ljava/lang/String;
    //   417: putstatic 128	com/tapjoy/TapjoyConnectCore:mobileCountryCode	Ljava/lang/String;
    //   420: aload 4
    //   422: invokevirtual 623	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   425: iconst_3
    //   426: invokevirtual 460	java/lang/String:substring	(I)Ljava/lang/String;
    //   429: putstatic 130	com/tapjoy/TapjoyConnectCore:mobileNetworkCode	Ljava/lang/String;
    //   432: ldc 19
    //   434: new 175	java/lang/StringBuilder
    //   437: dup
    //   438: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   441: ldc_w 625
    //   444: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   447: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   450: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   453: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   456: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   459: iconst_0
    //   460: istore_1
    //   461: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   464: ifnonnull +1502 -> 1966
    //   467: ldc 19
    //   469: ldc_w 627
    //   472: invokestatic 263	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   475: iconst_1
    //   476: istore_1
    //   477: ldc 19
    //   479: new 175	java/lang/StringBuilder
    //   482: dup
    //   483: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   486: ldc_w 629
    //   489: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   492: getstatic 632	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   495: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   498: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   501: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   504: iload_1
    //   505: istore_2
    //   506: getstatic 632	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   509: invokestatic 638	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   512: bipush 9
    //   514: if_icmplt +99 -> 613
    //   517: ldc 19
    //   519: ldc_w 640
    //   522: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   525: new 642	com/tapjoy/TapjoyHardwareUtil
    //   528: dup
    //   529: invokespecial 643	com/tapjoy/TapjoyHardwareUtil:<init>	()V
    //   532: invokevirtual 646	com/tapjoy/TapjoyHardwareUtil:getSerial	()Ljava/lang/String;
    //   535: putstatic 96	com/tapjoy/TapjoyConnectCore:serialID	Ljava/lang/String;
    //   538: iload_1
    //   539: ifeq +9 -> 548
    //   542: getstatic 96	com/tapjoy/TapjoyConnectCore:serialID	Ljava/lang/String;
    //   545: putstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   548: ldc 19
    //   550: ldc_w 648
    //   553: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   556: ldc 19
    //   558: new 175	java/lang/StringBuilder
    //   561: dup
    //   562: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   565: ldc_w 650
    //   568: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   571: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   574: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   577: ldc_w 652
    //   580: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   583: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   586: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   589: ldc 19
    //   591: ldc_w 648
    //   594: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   597: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   600: ifnonnull +1427 -> 2027
    //   603: ldc 19
    //   605: ldc_w 654
    //   608: invokestatic 263	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   611: iconst_1
    //   612: istore_2
    //   613: iload_2
    //   614: ifeq +53 -> 667
    //   617: new 656	java/lang/StringBuffer
    //   620: dup
    //   621: invokespecial 657	java/lang/StringBuffer:<init>	()V
    //   624: astore 4
    //   626: aload 4
    //   628: ldc_w 659
    //   631: invokevirtual 662	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   634: pop
    //   635: aload_3
    //   636: ldc_w 664
    //   639: aconst_null
    //   640: invokeinterface 667 3 0
    //   645: astore 5
    //   647: aload 5
    //   649: ifnull +1611 -> 2260
    //   652: aload 5
    //   654: ldc 86
    //   656: invokevirtual 350	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   659: ifne +1601 -> 2260
    //   662: aload 5
    //   664: putstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   667: getstatic 120	com/tapjoy/TapjoyConnectCore:userID	Ljava/lang/String;
    //   670: invokevirtual 210	java/lang/String:length	()I
    //   673: ifne +9 -> 682
    //   676: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   679: putstatic 120	com/tapjoy/TapjoyConnectCore:userID	Ljava/lang/String;
    //   682: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   685: invokestatic 257	com/tapjoy/TapjoyUtil:SHA256	(Ljava/lang/String;)Ljava/lang/String;
    //   688: putstatic 92	com/tapjoy/TapjoyConnectCore:sha2DeviceID	Ljava/lang/String;
    //   691: getstatic 632	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   694: invokestatic 638	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   697: iconst_3
    //   698: if_icmple +67 -> 765
    //   701: new 669	com/tapjoy/TapjoyDisplayMetricsUtil
    //   704: dup
    //   705: getstatic 76	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   708: invokespecial 671	com/tapjoy/TapjoyDisplayMetricsUtil:<init>	(Landroid/content/Context;)V
    //   711: astore 4
    //   713: new 175	java/lang/StringBuilder
    //   716: dup
    //   717: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   720: ldc 86
    //   722: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   725: aload 4
    //   727: invokevirtual 674	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensity	()I
    //   730: invokevirtual 252	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   733: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   736: putstatic 116	com/tapjoy/TapjoyConnectCore:deviceScreenDensity	Ljava/lang/String;
    //   739: new 175	java/lang/StringBuilder
    //   742: dup
    //   743: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   746: ldc 86
    //   748: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   751: aload 4
    //   753: invokevirtual 677	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenLayoutSize	()I
    //   756: invokevirtual 252	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   759: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   762: putstatic 118	com/tapjoy/TapjoyConnectCore:deviceScreenLayoutSize	Ljava/lang/String;
    //   765: getstatic 76	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   768: ldc_w 291
    //   771: invokevirtual 274	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   774: checkcast 679	android/net/wifi/WifiManager
    //   777: astore 4
    //   779: aload 4
    //   781: ifnull +23 -> 804
    //   784: aload 4
    //   786: invokevirtual 683	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   789: astore 4
    //   791: aload 4
    //   793: ifnull +11 -> 804
    //   796: aload 4
    //   798: invokevirtual 688	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   801: putstatic 94	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   804: ldc_w 690
    //   807: invokestatic 204	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   810: ifnull +74 -> 884
    //   813: ldc_w 690
    //   816: invokestatic 204	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   819: invokevirtual 210	java/lang/String:length	()I
    //   822: ifle +62 -> 884
    //   825: ldc_w 690
    //   828: invokestatic 204	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   831: putstatic 134	com/tapjoy/TapjoyConnectCore:storeName	Ljava/lang/String;
    //   834: new 692	java/util/ArrayList
    //   837: dup
    //   838: getstatic 695	com/tapjoy/TapjoyConnectFlag:STORE_ARRAY	[Ljava/lang/String;
    //   841: invokestatic 701	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   844: invokespecial 704	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   847: getstatic 134	com/tapjoy/TapjoyConnectCore:storeName	Ljava/lang/String;
    //   850: invokevirtual 705	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   853: ifne +31 -> 884
    //   856: ldc 19
    //   858: new 175	java/lang/StringBuilder
    //   861: dup
    //   862: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   865: ldc_w 707
    //   868: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   871: getstatic 134	com/tapjoy/TapjoyConnectCore:storeName	Ljava/lang/String;
    //   874: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   877: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   880: invokestatic 713	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   883: pop
    //   884: aload_3
    //   885: ldc_w 715
    //   888: aconst_null
    //   889: invokeinterface 667 3 0
    //   894: astore_3
    //   895: aload_3
    //   896: ifnull +16 -> 912
    //   899: aload_3
    //   900: ldc 86
    //   902: invokevirtual 350	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   905: ifne +7 -> 912
    //   908: aload_3
    //   909: putstatic 140	com/tapjoy/TapjoyConnectCore:referralURL	Ljava/lang/String;
    //   912: getstatic 76	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   915: invokevirtual 521	android/content/Context:getPackageName	()Ljava/lang/String;
    //   918: putstatic 138	com/tapjoy/TapjoyConnectCore:clientPackage	Ljava/lang/String;
    //   921: ldc 19
    //   923: new 175	java/lang/StringBuilder
    //   926: dup
    //   927: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   930: ldc_w 717
    //   933: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   936: getstatic 110	com/tapjoy/TapjoyConnectCore:appID	Ljava/lang/String;
    //   939: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   942: ldc_w 652
    //   945: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   948: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   951: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   954: ldc 19
    //   956: new 175	java/lang/StringBuilder
    //   959: dup
    //   960: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   963: ldc_w 719
    //   966: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   969: getstatic 88	com/tapjoy/TapjoyConnectCore:androidID	Ljava/lang/String;
    //   972: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   975: ldc_w 652
    //   978: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   981: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   984: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   987: ldc 19
    //   989: new 175	java/lang/StringBuilder
    //   992: dup
    //   993: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   996: ldc_w 721
    //   999: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1002: getstatic 138	com/tapjoy/TapjoyConnectCore:clientPackage	Ljava/lang/String;
    //   1005: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1008: ldc_w 652
    //   1011: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1014: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1017: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1020: new 175	java/lang/StringBuilder
    //   1023: dup
    //   1024: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1027: ldc_w 723
    //   1030: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1033: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1036: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1039: ldc_w 652
    //   1042: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1045: astore 4
    //   1047: ldc_w 614
    //   1050: invokestatic 204	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1053: ifnull +1194 -> 2247
    //   1056: ldc_w 614
    //   1059: invokestatic 204	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1062: invokevirtual 210	java/lang/String:length	()I
    //   1065: ifle +1182 -> 2247
    //   1068: ldc_w 725
    //   1071: astore_3
    //   1072: ldc 19
    //   1074: aload 4
    //   1076: aload_3
    //   1077: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1080: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1083: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1086: ldc 19
    //   1088: new 175	java/lang/StringBuilder
    //   1091: dup
    //   1092: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1095: ldc_w 727
    //   1098: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1101: getstatic 92	com/tapjoy/TapjoyConnectCore:sha2DeviceID	Ljava/lang/String;
    //   1104: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1107: ldc_w 652
    //   1110: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1113: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1116: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1119: ldc 19
    //   1121: new 175	java/lang/StringBuilder
    //   1124: dup
    //   1125: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1128: ldc_w 729
    //   1131: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1134: getstatic 96	com/tapjoy/TapjoyConnectCore:serialID	Ljava/lang/String;
    //   1137: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1140: ldc_w 652
    //   1143: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1146: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1149: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1152: ldc 19
    //   1154: new 175	java/lang/StringBuilder
    //   1157: dup
    //   1158: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1161: ldc_w 731
    //   1164: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1167: getstatic 94	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   1170: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1173: ldc_w 652
    //   1176: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1179: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1182: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1185: ldc 19
    //   1187: new 175	java/lang/StringBuilder
    //   1190: dup
    //   1191: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1194: ldc_w 733
    //   1197: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1200: getstatic 98	com/tapjoy/TapjoyConnectCore:deviceModel	Ljava/lang/String;
    //   1203: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1206: ldc_w 652
    //   1209: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1212: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1215: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1218: ldc 19
    //   1220: new 175	java/lang/StringBuilder
    //   1223: dup
    //   1224: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1227: ldc_w 735
    //   1230: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1233: getstatic 100	com/tapjoy/TapjoyConnectCore:deviceManufacturer	Ljava/lang/String;
    //   1236: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1239: ldc_w 652
    //   1242: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1245: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1248: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1251: ldc 19
    //   1253: new 175	java/lang/StringBuilder
    //   1256: dup
    //   1257: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1260: ldc_w 737
    //   1263: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1266: getstatic 102	com/tapjoy/TapjoyConnectCore:deviceType	Ljava/lang/String;
    //   1269: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1272: ldc_w 652
    //   1275: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1278: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1281: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1284: ldc 19
    //   1286: new 175	java/lang/StringBuilder
    //   1289: dup
    //   1290: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1293: ldc_w 739
    //   1296: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1299: getstatic 114	com/tapjoy/TapjoyConnectCore:libraryVersion	Ljava/lang/String;
    //   1302: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1305: ldc_w 652
    //   1308: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1311: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1314: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1317: ldc 19
    //   1319: new 175	java/lang/StringBuilder
    //   1322: dup
    //   1323: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1326: ldc_w 741
    //   1329: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1332: getstatic 104	com/tapjoy/TapjoyConnectCore:deviceOSVersion	Ljava/lang/String;
    //   1335: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1338: ldc_w 652
    //   1341: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1344: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1347: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1350: ldc 19
    //   1352: new 175	java/lang/StringBuilder
    //   1355: dup
    //   1356: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1359: ldc_w 743
    //   1362: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1365: getstatic 106	com/tapjoy/TapjoyConnectCore:deviceCountryCode	Ljava/lang/String;
    //   1368: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1371: ldc_w 652
    //   1374: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1377: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1380: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1383: ldc 19
    //   1385: new 175	java/lang/StringBuilder
    //   1388: dup
    //   1389: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1392: ldc_w 745
    //   1395: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1398: getstatic 108	com/tapjoy/TapjoyConnectCore:deviceLanguage	Ljava/lang/String;
    //   1401: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1404: ldc_w 652
    //   1407: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1410: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1413: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1416: ldc 19
    //   1418: new 175	java/lang/StringBuilder
    //   1421: dup
    //   1422: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1425: ldc_w 747
    //   1428: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1431: getstatic 116	com/tapjoy/TapjoyConnectCore:deviceScreenDensity	Ljava/lang/String;
    //   1434: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1437: ldc_w 652
    //   1440: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1443: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1446: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1449: ldc 19
    //   1451: new 175	java/lang/StringBuilder
    //   1454: dup
    //   1455: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1458: ldc_w 749
    //   1461: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1464: getstatic 118	com/tapjoy/TapjoyConnectCore:deviceScreenLayoutSize	Ljava/lang/String;
    //   1467: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1470: ldc_w 652
    //   1473: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1476: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1479: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1482: ldc 19
    //   1484: new 175	java/lang/StringBuilder
    //   1487: dup
    //   1488: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1491: ldc_w 751
    //   1494: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1497: getstatic 124	com/tapjoy/TapjoyConnectCore:carrierName	Ljava/lang/String;
    //   1500: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1503: ldc_w 652
    //   1506: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1509: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1512: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1515: ldc 19
    //   1517: new 175	java/lang/StringBuilder
    //   1520: dup
    //   1521: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1524: ldc_w 753
    //   1527: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1530: getstatic 126	com/tapjoy/TapjoyConnectCore:carrierCountryCode	Ljava/lang/String;
    //   1533: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1536: ldc_w 652
    //   1539: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1542: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1545: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1548: ldc 19
    //   1550: new 175	java/lang/StringBuilder
    //   1553: dup
    //   1554: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1557: ldc_w 755
    //   1560: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1563: getstatic 128	com/tapjoy/TapjoyConnectCore:mobileCountryCode	Ljava/lang/String;
    //   1566: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1569: ldc_w 652
    //   1572: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1575: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1578: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1581: ldc 19
    //   1583: new 175	java/lang/StringBuilder
    //   1586: dup
    //   1587: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1590: ldc_w 757
    //   1593: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1596: getstatic 130	com/tapjoy/TapjoyConnectCore:mobileNetworkCode	Ljava/lang/String;
    //   1599: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1602: ldc_w 652
    //   1605: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1608: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1611: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1614: ldc 19
    //   1616: new 175	java/lang/StringBuilder
    //   1619: dup
    //   1620: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1623: ldc_w 759
    //   1626: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1629: getstatic 134	com/tapjoy/TapjoyConnectCore:storeName	Ljava/lang/String;
    //   1632: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1635: ldc_w 652
    //   1638: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1641: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1644: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1647: ldc 19
    //   1649: new 175	java/lang/StringBuilder
    //   1652: dup
    //   1653: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1656: ldc_w 761
    //   1659: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1662: getstatic 140	com/tapjoy/TapjoyConnectCore:referralURL	Ljava/lang/String;
    //   1665: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1668: ldc_w 652
    //   1671: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1674: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1677: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1680: getstatic 156	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   1683: ifnull +188 -> 1871
    //   1686: ldc 19
    //   1688: ldc_w 763
    //   1691: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1694: ldc 19
    //   1696: ldc_w 765
    //   1699: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1702: getstatic 156	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   1705: invokevirtual 769	java/util/Hashtable:entrySet	()Ljava/util/Set;
    //   1708: invokeinterface 772 1 0
    //   1713: astore_3
    //   1714: aload_3
    //   1715: invokeinterface 488 1 0
    //   1720: ifeq +151 -> 1871
    //   1723: aload_3
    //   1724: invokeinterface 492 1 0
    //   1729: checkcast 774	java/util/Map$Entry
    //   1732: astore 4
    //   1734: ldc 19
    //   1736: new 175	java/lang/StringBuilder
    //   1739: dup
    //   1740: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1743: ldc_w 776
    //   1746: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1749: aload 4
    //   1751: invokeinterface 779 1 0
    //   1756: checkcast 206	java/lang/String
    //   1759: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1762: ldc_w 781
    //   1765: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1768: aload 4
    //   1770: invokeinterface 784 1 0
    //   1775: checkcast 206	java/lang/String
    //   1778: invokestatic 314	android/net/Uri:encode	(Ljava/lang/String;)Ljava/lang/String;
    //   1781: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1784: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1787: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1790: aload 4
    //   1792: invokeinterface 779 1 0
    //   1797: checkcast 206	java/lang/String
    //   1800: ldc_w 344
    //   1803: invokevirtual 350	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1806: ifeq -92 -> 1714
    //   1809: getstatic 146	com/tapjoy/TapjoyConnectCore:sdkType	Ljava/lang/String;
    //   1812: ldc_w 786
    //   1815: invokevirtual 350	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1818: ifne -104 -> 1714
    //   1821: ldc 19
    //   1823: ldc_w 788
    //   1826: invokestatic 790	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   1829: getstatic 156	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   1832: ldc_w 344
    //   1835: invokevirtual 793	java/util/Hashtable:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1838: pop
    //   1839: goto -125 -> 1714
    //   1842: astore_3
    //   1843: ldc 19
    //   1845: new 175	java/lang/StringBuilder
    //   1848: dup
    //   1849: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1852: ldc_w 795
    //   1855: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1858: aload_3
    //   1859: invokevirtual 260	java/lang/Exception:toString	()Ljava/lang/String;
    //   1862: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1865: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1868: invokestatic 263	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1871: return
    //   1872: ldc 19
    //   1874: ldc_w 797
    //   1877: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1880: goto -1696 -> 184
    //   1883: astore 4
    //   1885: ldc 19
    //   1887: new 175	java/lang/StringBuilder
    //   1890: dup
    //   1891: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1894: ldc_w 799
    //   1897: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1900: aload 4
    //   1902: invokevirtual 260	java/lang/Exception:toString	()Ljava/lang/String;
    //   1905: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1908: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1911: invokestatic 263	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1914: goto -1730 -> 184
    //   1917: aload 4
    //   1919: invokevirtual 802	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   1922: putstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1925: goto -1567 -> 358
    //   1928: astore 4
    //   1930: ldc 19
    //   1932: new 175	java/lang/StringBuilder
    //   1935: dup
    //   1936: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1939: ldc_w 804
    //   1942: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1945: aload 4
    //   1947: invokevirtual 260	java/lang/Exception:toString	()Ljava/lang/String;
    //   1950: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1953: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1956: invokestatic 263	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1959: aconst_null
    //   1960: putstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1963: goto -1296 -> 667
    //   1966: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1969: invokevirtual 210	java/lang/String:length	()I
    //   1972: ifeq +27 -> 1999
    //   1975: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1978: ldc_w 806
    //   1981: invokevirtual 350	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1984: ifne +15 -> 1999
    //   1987: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1990: ldc_w 808
    //   1993: invokevirtual 350	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1996: ifeq +16 -> 2012
    //   1999: ldc 19
    //   2001: ldc_w 810
    //   2004: invokestatic 263	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   2007: iconst_1
    //   2008: istore_1
    //   2009: goto -1532 -> 477
    //   2012: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2015: invokestatic 600	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   2018: invokevirtual 814	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   2021: putstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2024: goto -1547 -> 477
    //   2027: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2030: invokevirtual 210	java/lang/String:length	()I
    //   2033: ifeq +39 -> 2072
    //   2036: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2039: ldc_w 806
    //   2042: invokevirtual 350	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2045: ifne +27 -> 2072
    //   2048: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2051: ldc_w 808
    //   2054: invokevirtual 350	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2057: ifne +15 -> 2072
    //   2060: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2063: ldc_w 816
    //   2066: invokevirtual 350	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   2069: ifeq +16 -> 2085
    //   2072: ldc 19
    //   2074: ldc_w 818
    //   2077: invokestatic 263	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   2080: iconst_1
    //   2081: istore_2
    //   2082: goto -1469 -> 613
    //   2085: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2088: invokevirtual 820	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   2091: putstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2094: iconst_0
    //   2095: istore_2
    //   2096: goto -1483 -> 613
    //   2099: iload_1
    //   2100: bipush 32
    //   2102: if_icmpge +33 -> 2135
    //   2105: aload 4
    //   2107: ldc_w 822
    //   2110: invokestatic 828	java/lang/Math:random	()D
    //   2113: ldc2_w 829
    //   2116: dmul
    //   2117: d2i
    //   2118: bipush 30
    //   2120: irem
    //   2121: invokevirtual 834	java/lang/String:charAt	(I)C
    //   2124: invokevirtual 837	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   2127: pop
    //   2128: iload_1
    //   2129: iconst_1
    //   2130: iadd
    //   2131: istore_1
    //   2132: goto -33 -> 2099
    //   2135: aload 4
    //   2137: invokevirtual 838	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   2140: invokevirtual 820	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   2143: putstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2146: aload_3
    //   2147: invokeinterface 842 1 0
    //   2152: astore 4
    //   2154: aload 4
    //   2156: ldc_w 664
    //   2159: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2162: invokeinterface 848 3 0
    //   2167: pop
    //   2168: aload 4
    //   2170: invokeinterface 851 1 0
    //   2175: pop
    //   2176: goto -1509 -> 667
    //   2179: astore 4
    //   2181: ldc 19
    //   2183: new 175	java/lang/StringBuilder
    //   2186: dup
    //   2187: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   2190: ldc_w 853
    //   2193: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2196: aload 4
    //   2198: invokevirtual 260	java/lang/Exception:toString	()Ljava/lang/String;
    //   2201: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2204: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2207: invokestatic 263	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   2210: goto -1445 -> 765
    //   2213: astore 4
    //   2215: ldc 19
    //   2217: new 175	java/lang/StringBuilder
    //   2220: dup
    //   2221: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   2224: ldc_w 855
    //   2227: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2230: aload 4
    //   2232: invokevirtual 260	java/lang/Exception:toString	()Ljava/lang/String;
    //   2235: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2238: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2241: invokestatic 263	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   2244: goto -1440 -> 804
    //   2247: ldc 86
    //   2249: astore_3
    //   2250: goto -1178 -> 1072
    //   2253: iload_1
    //   2254: iconst_1
    //   2255: iadd
    //   2256: istore_1
    //   2257: goto -2191 -> 66
    //   2260: iconst_0
    //   2261: istore_1
    //   2262: goto -163 -> 2099
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2265	0	this	TapjoyConnectCore
    //   65	2197	1	i	int
    //   63	2033	2	j	int
    //   6	1718	3	localObject1	Object
    //   1842	305	3	localException1	Exception
    //   2249	1	3	str1	String
    //   40	1751	4	localObject2	Object
    //   1883	35	4	localException2	Exception
    //   1928	208	4	localException3	Exception
    //   2152	17	4	localEditor	SharedPreferences.Editor
    //   2179	18	4	localException4	Exception
    //   2213	18	4	localException5	Exception
    //   58	605	5	localObject3	Object
    //   75	91	6	str2	String
    //   106	62	7	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   212	309	1842	java/lang/Exception
    //   667	682	1842	java/lang/Exception
    //   682	691	1842	java/lang/Exception
    //   804	884	1842	java/lang/Exception
    //   884	895	1842	java/lang/Exception
    //   899	912	1842	java/lang/Exception
    //   912	1068	1842	java/lang/Exception
    //   1072	1714	1842	java/lang/Exception
    //   1714	1839	1842	java/lang/Exception
    //   1930	1963	1842	java/lang/Exception
    //   2181	2210	1842	java/lang/Exception
    //   2215	2244	1842	java/lang/Exception
    //   7	23	1883	java/lang/Exception
    //   27	42	1883	java/lang/Exception
    //   47	64	1883	java/lang/Exception
    //   77	108	1883	java/lang/Exception
    //   113	120	1883	java/lang/Exception
    //   125	173	1883	java/lang/Exception
    //   176	184	1883	java/lang/Exception
    //   1872	1880	1883	java/lang/Exception
    //   309	323	1928	java/lang/Exception
    //   328	358	1928	java/lang/Exception
    //   358	407	1928	java/lang/Exception
    //   407	432	1928	java/lang/Exception
    //   432	459	1928	java/lang/Exception
    //   461	475	1928	java/lang/Exception
    //   477	504	1928	java/lang/Exception
    //   506	538	1928	java/lang/Exception
    //   542	548	1928	java/lang/Exception
    //   548	611	1928	java/lang/Exception
    //   617	647	1928	java/lang/Exception
    //   652	667	1928	java/lang/Exception
    //   1917	1925	1928	java/lang/Exception
    //   1966	1999	1928	java/lang/Exception
    //   1999	2007	1928	java/lang/Exception
    //   2012	2024	1928	java/lang/Exception
    //   2027	2072	1928	java/lang/Exception
    //   2072	2080	1928	java/lang/Exception
    //   2085	2094	1928	java/lang/Exception
    //   2105	2128	1928	java/lang/Exception
    //   2135	2176	1928	java/lang/Exception
    //   691	765	2179	java/lang/Exception
    //   765	779	2213	java/lang/Exception
    //   784	791	2213	java/lang/Exception
    //   796	804	2213	java/lang/Exception
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
  
  public void setTapjoyViewNotifier(TapjoyViewNotifier paramTapjoyViewNotifier)
  {
    viewNotifier = paramTapjoyViewNotifier;
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
