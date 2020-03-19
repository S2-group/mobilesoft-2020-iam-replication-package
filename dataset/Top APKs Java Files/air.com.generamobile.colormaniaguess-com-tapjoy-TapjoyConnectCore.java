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
    String str2 = "";
    localObject2 = str2;
    for (;;)
    {
      try
      {
        localConnectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        str1 = str2;
        if (localConnectivityManager == null) {
          continue;
        }
        localObject2 = str2;
        str1 = str2;
        if (localConnectivityManager.getActiveNetworkInfo() == null) {
          continue;
        }
        localObject2 = str2;
        switch (localConnectivityManager.getActiveNetworkInfo().getType())
        {
        }
      }
      catch (Exception localException)
      {
        ConnectivityManager localConnectivityManager;
        String str1;
        TapjoyLog.e("TapjoyConnect", "getConnectionType error: " + localException.toString());
        Object localObject1 = localObject2;
        continue;
        localObject1 = "mobile";
        continue;
        return localObject1;
      }
      localObject2 = str1;
      TapjoyLog.i("TapjoyConnect", "connectivity: " + localConnectivityManager.getActiveNetworkInfo().getType());
      localObject2 = str1;
      TapjoyLog.i("TapjoyConnect", "connection_type: " + str1);
      continue;
      str1 = "wifi";
    }
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
    if (connectFlags != null) {}
    for (paramString = (String)connectFlags.get(paramString);; paramString = "")
    {
      String str = paramString;
      if (paramString == null) {
        str = "";
      }
      return str;
    }
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
      if ((paramString != null) && (paramString.equals("true"))) {
        return true;
      }
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
    //   44: ifnull +1826 -> 1870
    //   47: aload 4
    //   49: getfield 529	android/content/pm/ApplicationInfo:metaData	Landroid/os/Bundle;
    //   52: ifnull +1818 -> 1870
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
    //   334: ifnull +1581 -> 1915
    //   337: ldc_w 614
    //   340: invokestatic 204	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   343: invokevirtual 210	java/lang/String:length	()I
    //   346: ifle +1569 -> 1915
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
    //   459: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   462: ifnonnull +1502 -> 1964
    //   465: ldc 19
    //   467: ldc_w 627
    //   470: invokestatic 263	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   473: iconst_1
    //   474: istore_1
    //   475: ldc 19
    //   477: new 175	java/lang/StringBuilder
    //   480: dup
    //   481: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   484: ldc_w 629
    //   487: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   490: getstatic 632	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   493: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   496: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   499: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   502: iload_1
    //   503: istore_2
    //   504: getstatic 632	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   507: invokestatic 638	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   510: bipush 9
    //   512: if_icmplt +99 -> 611
    //   515: ldc 19
    //   517: ldc_w 640
    //   520: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   523: new 642	com/tapjoy/TapjoyHardwareUtil
    //   526: dup
    //   527: invokespecial 643	com/tapjoy/TapjoyHardwareUtil:<init>	()V
    //   530: invokevirtual 646	com/tapjoy/TapjoyHardwareUtil:getSerial	()Ljava/lang/String;
    //   533: putstatic 96	com/tapjoy/TapjoyConnectCore:serialID	Ljava/lang/String;
    //   536: iload_1
    //   537: ifeq +9 -> 546
    //   540: getstatic 96	com/tapjoy/TapjoyConnectCore:serialID	Ljava/lang/String;
    //   543: putstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   546: ldc 19
    //   548: ldc_w 648
    //   551: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   554: ldc 19
    //   556: new 175	java/lang/StringBuilder
    //   559: dup
    //   560: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   563: ldc_w 650
    //   566: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   569: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   572: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   575: ldc_w 652
    //   578: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   581: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   584: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   587: ldc 19
    //   589: ldc_w 648
    //   592: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   595: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   598: ifnonnull +1429 -> 2027
    //   601: ldc 19
    //   603: ldc_w 654
    //   606: invokestatic 263	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   609: iconst_1
    //   610: istore_2
    //   611: iload_2
    //   612: ifeq +53 -> 665
    //   615: new 656	java/lang/StringBuffer
    //   618: dup
    //   619: invokespecial 657	java/lang/StringBuffer:<init>	()V
    //   622: astore 4
    //   624: aload 4
    //   626: ldc_w 659
    //   629: invokevirtual 662	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   632: pop
    //   633: aload_3
    //   634: ldc_w 664
    //   637: aconst_null
    //   638: invokeinterface 667 3 0
    //   643: astore 5
    //   645: aload 5
    //   647: ifnull +1613 -> 2260
    //   650: aload 5
    //   652: ldc 86
    //   654: invokevirtual 350	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   657: ifne +1603 -> 2260
    //   660: aload 5
    //   662: putstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   665: getstatic 120	com/tapjoy/TapjoyConnectCore:userID	Ljava/lang/String;
    //   668: invokevirtual 210	java/lang/String:length	()I
    //   671: ifne +9 -> 680
    //   674: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   677: putstatic 120	com/tapjoy/TapjoyConnectCore:userID	Ljava/lang/String;
    //   680: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   683: invokestatic 257	com/tapjoy/TapjoyUtil:SHA256	(Ljava/lang/String;)Ljava/lang/String;
    //   686: putstatic 92	com/tapjoy/TapjoyConnectCore:sha2DeviceID	Ljava/lang/String;
    //   689: getstatic 632	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   692: invokestatic 638	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   695: iconst_3
    //   696: if_icmple +67 -> 763
    //   699: new 669	com/tapjoy/TapjoyDisplayMetricsUtil
    //   702: dup
    //   703: getstatic 76	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   706: invokespecial 671	com/tapjoy/TapjoyDisplayMetricsUtil:<init>	(Landroid/content/Context;)V
    //   709: astore 4
    //   711: new 175	java/lang/StringBuilder
    //   714: dup
    //   715: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   718: ldc 86
    //   720: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   723: aload 4
    //   725: invokevirtual 674	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensity	()I
    //   728: invokevirtual 252	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   731: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   734: putstatic 116	com/tapjoy/TapjoyConnectCore:deviceScreenDensity	Ljava/lang/String;
    //   737: new 175	java/lang/StringBuilder
    //   740: dup
    //   741: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   744: ldc 86
    //   746: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   749: aload 4
    //   751: invokevirtual 677	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenLayoutSize	()I
    //   754: invokevirtual 252	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   757: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   760: putstatic 118	com/tapjoy/TapjoyConnectCore:deviceScreenLayoutSize	Ljava/lang/String;
    //   763: getstatic 76	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   766: ldc_w 291
    //   769: invokevirtual 274	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   772: checkcast 679	android/net/wifi/WifiManager
    //   775: astore 4
    //   777: aload 4
    //   779: ifnull +23 -> 802
    //   782: aload 4
    //   784: invokevirtual 683	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   787: astore 4
    //   789: aload 4
    //   791: ifnull +11 -> 802
    //   794: aload 4
    //   796: invokevirtual 688	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   799: putstatic 94	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   802: ldc_w 690
    //   805: invokestatic 204	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   808: ifnull +74 -> 882
    //   811: ldc_w 690
    //   814: invokestatic 204	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   817: invokevirtual 210	java/lang/String:length	()I
    //   820: ifle +62 -> 882
    //   823: ldc_w 690
    //   826: invokestatic 204	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   829: putstatic 134	com/tapjoy/TapjoyConnectCore:storeName	Ljava/lang/String;
    //   832: new 692	java/util/ArrayList
    //   835: dup
    //   836: getstatic 695	com/tapjoy/TapjoyConnectFlag:STORE_ARRAY	[Ljava/lang/String;
    //   839: invokestatic 701	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   842: invokespecial 704	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   845: getstatic 134	com/tapjoy/TapjoyConnectCore:storeName	Ljava/lang/String;
    //   848: invokevirtual 705	java/util/ArrayList:contains	(Ljava/lang/Object;)Z
    //   851: ifne +31 -> 882
    //   854: ldc 19
    //   856: new 175	java/lang/StringBuilder
    //   859: dup
    //   860: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   863: ldc_w 707
    //   866: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   869: getstatic 134	com/tapjoy/TapjoyConnectCore:storeName	Ljava/lang/String;
    //   872: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   875: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   878: invokestatic 713	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   881: pop
    //   882: aload_3
    //   883: ldc_w 715
    //   886: aconst_null
    //   887: invokeinterface 667 3 0
    //   892: astore_3
    //   893: aload_3
    //   894: ifnull +16 -> 910
    //   897: aload_3
    //   898: ldc 86
    //   900: invokevirtual 350	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   903: ifne +7 -> 910
    //   906: aload_3
    //   907: putstatic 140	com/tapjoy/TapjoyConnectCore:referralURL	Ljava/lang/String;
    //   910: getstatic 76	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   913: invokevirtual 521	android/content/Context:getPackageName	()Ljava/lang/String;
    //   916: putstatic 138	com/tapjoy/TapjoyConnectCore:clientPackage	Ljava/lang/String;
    //   919: ldc 19
    //   921: new 175	java/lang/StringBuilder
    //   924: dup
    //   925: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   928: ldc_w 717
    //   931: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   934: getstatic 110	com/tapjoy/TapjoyConnectCore:appID	Ljava/lang/String;
    //   937: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   940: ldc_w 652
    //   943: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   946: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   949: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   952: ldc 19
    //   954: new 175	java/lang/StringBuilder
    //   957: dup
    //   958: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   961: ldc_w 719
    //   964: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   967: getstatic 88	com/tapjoy/TapjoyConnectCore:androidID	Ljava/lang/String;
    //   970: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   973: ldc_w 652
    //   976: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   979: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   982: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   985: ldc 19
    //   987: new 175	java/lang/StringBuilder
    //   990: dup
    //   991: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   994: ldc_w 721
    //   997: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1000: getstatic 138	com/tapjoy/TapjoyConnectCore:clientPackage	Ljava/lang/String;
    //   1003: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1006: ldc_w 652
    //   1009: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1012: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1015: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1018: new 175	java/lang/StringBuilder
    //   1021: dup
    //   1022: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1025: ldc_w 723
    //   1028: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1031: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1034: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1037: ldc_w 652
    //   1040: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1043: astore 4
    //   1045: ldc_w 614
    //   1048: invokestatic 204	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1051: ifnull +1196 -> 2247
    //   1054: ldc_w 614
    //   1057: invokestatic 204	com/tapjoy/TapjoyConnectCore:getFlagValue	(Ljava/lang/String;)Ljava/lang/String;
    //   1060: invokevirtual 210	java/lang/String:length	()I
    //   1063: ifle +1184 -> 2247
    //   1066: ldc_w 725
    //   1069: astore_3
    //   1070: ldc 19
    //   1072: aload 4
    //   1074: aload_3
    //   1075: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1078: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1081: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1084: ldc 19
    //   1086: new 175	java/lang/StringBuilder
    //   1089: dup
    //   1090: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1093: ldc_w 727
    //   1096: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1099: getstatic 92	com/tapjoy/TapjoyConnectCore:sha2DeviceID	Ljava/lang/String;
    //   1102: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1105: ldc_w 652
    //   1108: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1111: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1114: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1117: ldc 19
    //   1119: new 175	java/lang/StringBuilder
    //   1122: dup
    //   1123: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1126: ldc_w 729
    //   1129: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1132: getstatic 96	com/tapjoy/TapjoyConnectCore:serialID	Ljava/lang/String;
    //   1135: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1138: ldc_w 652
    //   1141: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1144: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1147: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1150: ldc 19
    //   1152: new 175	java/lang/StringBuilder
    //   1155: dup
    //   1156: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1159: ldc_w 731
    //   1162: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1165: getstatic 94	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   1168: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1171: ldc_w 652
    //   1174: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1177: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1180: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1183: ldc 19
    //   1185: new 175	java/lang/StringBuilder
    //   1188: dup
    //   1189: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1192: ldc_w 733
    //   1195: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1198: getstatic 98	com/tapjoy/TapjoyConnectCore:deviceModel	Ljava/lang/String;
    //   1201: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1204: ldc_w 652
    //   1207: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1210: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1213: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1216: ldc 19
    //   1218: new 175	java/lang/StringBuilder
    //   1221: dup
    //   1222: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1225: ldc_w 735
    //   1228: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1231: getstatic 100	com/tapjoy/TapjoyConnectCore:deviceManufacturer	Ljava/lang/String;
    //   1234: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1237: ldc_w 652
    //   1240: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1243: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1246: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1249: ldc 19
    //   1251: new 175	java/lang/StringBuilder
    //   1254: dup
    //   1255: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1258: ldc_w 737
    //   1261: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1264: getstatic 102	com/tapjoy/TapjoyConnectCore:deviceType	Ljava/lang/String;
    //   1267: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1270: ldc_w 652
    //   1273: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1276: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1279: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1282: ldc 19
    //   1284: new 175	java/lang/StringBuilder
    //   1287: dup
    //   1288: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1291: ldc_w 739
    //   1294: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1297: getstatic 114	com/tapjoy/TapjoyConnectCore:libraryVersion	Ljava/lang/String;
    //   1300: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1303: ldc_w 652
    //   1306: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1309: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1312: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1315: ldc 19
    //   1317: new 175	java/lang/StringBuilder
    //   1320: dup
    //   1321: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1324: ldc_w 741
    //   1327: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1330: getstatic 104	com/tapjoy/TapjoyConnectCore:deviceOSVersion	Ljava/lang/String;
    //   1333: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1336: ldc_w 652
    //   1339: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1342: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1345: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1348: ldc 19
    //   1350: new 175	java/lang/StringBuilder
    //   1353: dup
    //   1354: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1357: ldc_w 743
    //   1360: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1363: getstatic 106	com/tapjoy/TapjoyConnectCore:deviceCountryCode	Ljava/lang/String;
    //   1366: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1369: ldc_w 652
    //   1372: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1375: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1378: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1381: ldc 19
    //   1383: new 175	java/lang/StringBuilder
    //   1386: dup
    //   1387: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1390: ldc_w 745
    //   1393: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1396: getstatic 108	com/tapjoy/TapjoyConnectCore:deviceLanguage	Ljava/lang/String;
    //   1399: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1402: ldc_w 652
    //   1405: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1408: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1411: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1414: ldc 19
    //   1416: new 175	java/lang/StringBuilder
    //   1419: dup
    //   1420: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1423: ldc_w 747
    //   1426: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1429: getstatic 116	com/tapjoy/TapjoyConnectCore:deviceScreenDensity	Ljava/lang/String;
    //   1432: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1435: ldc_w 652
    //   1438: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1441: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1444: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1447: ldc 19
    //   1449: new 175	java/lang/StringBuilder
    //   1452: dup
    //   1453: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1456: ldc_w 749
    //   1459: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1462: getstatic 118	com/tapjoy/TapjoyConnectCore:deviceScreenLayoutSize	Ljava/lang/String;
    //   1465: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1468: ldc_w 652
    //   1471: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1474: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1477: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1480: ldc 19
    //   1482: new 175	java/lang/StringBuilder
    //   1485: dup
    //   1486: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1489: ldc_w 751
    //   1492: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1495: getstatic 124	com/tapjoy/TapjoyConnectCore:carrierName	Ljava/lang/String;
    //   1498: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1501: ldc_w 652
    //   1504: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1507: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1510: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1513: ldc 19
    //   1515: new 175	java/lang/StringBuilder
    //   1518: dup
    //   1519: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1522: ldc_w 753
    //   1525: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1528: getstatic 126	com/tapjoy/TapjoyConnectCore:carrierCountryCode	Ljava/lang/String;
    //   1531: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1534: ldc_w 652
    //   1537: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1540: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1543: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1546: ldc 19
    //   1548: new 175	java/lang/StringBuilder
    //   1551: dup
    //   1552: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1555: ldc_w 755
    //   1558: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1561: getstatic 128	com/tapjoy/TapjoyConnectCore:mobileCountryCode	Ljava/lang/String;
    //   1564: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1567: ldc_w 652
    //   1570: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1573: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1576: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1579: ldc 19
    //   1581: new 175	java/lang/StringBuilder
    //   1584: dup
    //   1585: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1588: ldc_w 757
    //   1591: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1594: getstatic 130	com/tapjoy/TapjoyConnectCore:mobileNetworkCode	Ljava/lang/String;
    //   1597: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1600: ldc_w 652
    //   1603: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1606: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1609: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1612: ldc 19
    //   1614: new 175	java/lang/StringBuilder
    //   1617: dup
    //   1618: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1621: ldc_w 759
    //   1624: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1627: getstatic 134	com/tapjoy/TapjoyConnectCore:storeName	Ljava/lang/String;
    //   1630: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1633: ldc_w 652
    //   1636: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1639: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1642: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1645: ldc 19
    //   1647: new 175	java/lang/StringBuilder
    //   1650: dup
    //   1651: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1654: ldc_w 761
    //   1657: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1660: getstatic 140	com/tapjoy/TapjoyConnectCore:referralURL	Ljava/lang/String;
    //   1663: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1666: ldc_w 652
    //   1669: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1672: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1675: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1678: getstatic 156	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   1681: ifnull +188 -> 1869
    //   1684: ldc 19
    //   1686: ldc_w 763
    //   1689: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1692: ldc 19
    //   1694: ldc_w 765
    //   1697: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1700: getstatic 156	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   1703: invokevirtual 769	java/util/Hashtable:entrySet	()Ljava/util/Set;
    //   1706: invokeinterface 772 1 0
    //   1711: astore_3
    //   1712: aload_3
    //   1713: invokeinterface 488 1 0
    //   1718: ifeq +151 -> 1869
    //   1721: aload_3
    //   1722: invokeinterface 492 1 0
    //   1727: checkcast 774	java/util/Map$Entry
    //   1730: astore 4
    //   1732: ldc 19
    //   1734: new 175	java/lang/StringBuilder
    //   1737: dup
    //   1738: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1741: ldc_w 776
    //   1744: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1747: aload 4
    //   1749: invokeinterface 779 1 0
    //   1754: checkcast 206	java/lang/String
    //   1757: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1760: ldc_w 781
    //   1763: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1766: aload 4
    //   1768: invokeinterface 784 1 0
    //   1773: checkcast 206	java/lang/String
    //   1776: invokestatic 314	android/net/Uri:encode	(Ljava/lang/String;)Ljava/lang/String;
    //   1779: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1782: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1785: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1788: aload 4
    //   1790: invokeinterface 779 1 0
    //   1795: checkcast 206	java/lang/String
    //   1798: ldc_w 344
    //   1801: invokevirtual 350	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1804: ifeq -92 -> 1712
    //   1807: getstatic 146	com/tapjoy/TapjoyConnectCore:sdkType	Ljava/lang/String;
    //   1810: ldc_w 786
    //   1813: invokevirtual 350	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1816: ifne -104 -> 1712
    //   1819: ldc 19
    //   1821: ldc_w 788
    //   1824: invokestatic 790	com/tapjoy/TapjoyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   1827: getstatic 156	com/tapjoy/TapjoyConnectCore:connectFlags	Ljava/util/Hashtable;
    //   1830: ldc_w 344
    //   1833: invokevirtual 793	java/util/Hashtable:remove	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1836: pop
    //   1837: goto -125 -> 1712
    //   1840: astore_3
    //   1841: ldc 19
    //   1843: new 175	java/lang/StringBuilder
    //   1846: dup
    //   1847: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1850: ldc_w 795
    //   1853: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1856: aload_3
    //   1857: invokevirtual 260	java/lang/Exception:toString	()Ljava/lang/String;
    //   1860: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1863: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1866: invokestatic 263	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1869: return
    //   1870: ldc 19
    //   1872: ldc_w 797
    //   1875: invokestatic 195	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1878: goto -1694 -> 184
    //   1881: astore 4
    //   1883: ldc 19
    //   1885: new 175	java/lang/StringBuilder
    //   1888: dup
    //   1889: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1892: ldc_w 799
    //   1895: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1898: aload 4
    //   1900: invokevirtual 260	java/lang/Exception:toString	()Ljava/lang/String;
    //   1903: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1906: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1909: invokestatic 263	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1912: goto -1728 -> 184
    //   1915: aload 4
    //   1917: invokevirtual 802	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   1920: putstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1923: goto -1565 -> 358
    //   1926: astore 4
    //   1928: ldc 19
    //   1930: new 175	java/lang/StringBuilder
    //   1933: dup
    //   1934: invokespecial 176	java/lang/StringBuilder:<init>	()V
    //   1937: ldc_w 804
    //   1940: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1943: aload 4
    //   1945: invokevirtual 260	java/lang/Exception:toString	()Ljava/lang/String;
    //   1948: invokevirtual 182	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1951: invokevirtual 189	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1954: invokestatic 263	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1957: aconst_null
    //   1958: putstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1961: goto -1296 -> 665
    //   1964: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1967: invokevirtual 210	java/lang/String:length	()I
    //   1970: ifeq +27 -> 1997
    //   1973: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1976: ldc_w 806
    //   1979: invokevirtual 350	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1982: ifne +15 -> 1997
    //   1985: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1988: ldc_w 808
    //   1991: invokevirtual 350	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1994: ifeq +16 -> 2010
    //   1997: ldc 19
    //   1999: ldc_w 810
    //   2002: invokestatic 263	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   2005: iconst_1
    //   2006: istore_1
    //   2007: goto -1532 -> 475
    //   2010: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2013: invokestatic 600	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   2016: invokevirtual 814	java/lang/String:toLowerCase	(Ljava/util/Locale;)Ljava/lang/String;
    //   2019: putstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2022: iconst_0
    //   2023: istore_1
    //   2024: goto -1549 -> 475
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
    //   2082: goto -1471 -> 611
    //   2085: getstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2088: invokevirtual 820	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   2091: putstatic 90	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   2094: iconst_0
    //   2095: istore_2
    //   2096: goto -1485 -> 611
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
    //   2176: goto -1511 -> 665
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
    //   2210: goto -1447 -> 763
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
    //   2244: goto -1442 -> 802
    //   2247: ldc 86
    //   2249: astore_3
    //   2250: goto -1180 -> 1070
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
    //   6	1716	3	localObject1	Object
    //   1840	307	3	localException1	Exception
    //   2249	1	3	str1	String
    //   40	1749	4	localObject2	Object
    //   1881	35	4	localException2	Exception
    //   1926	210	4	localException3	Exception
    //   2152	17	4	localEditor	SharedPreferences.Editor
    //   2179	18	4	localException4	Exception
    //   2213	18	4	localException5	Exception
    //   58	603	5	localObject3	Object
    //   75	91	6	str2	String
    //   106	62	7	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   212	309	1840	java/lang/Exception
    //   665	680	1840	java/lang/Exception
    //   680	689	1840	java/lang/Exception
    //   802	882	1840	java/lang/Exception
    //   882	893	1840	java/lang/Exception
    //   897	910	1840	java/lang/Exception
    //   910	1066	1840	java/lang/Exception
    //   1070	1712	1840	java/lang/Exception
    //   1712	1837	1840	java/lang/Exception
    //   1928	1961	1840	java/lang/Exception
    //   2181	2210	1840	java/lang/Exception
    //   2215	2244	1840	java/lang/Exception
    //   7	23	1881	java/lang/Exception
    //   27	42	1881	java/lang/Exception
    //   47	64	1881	java/lang/Exception
    //   77	108	1881	java/lang/Exception
    //   113	120	1881	java/lang/Exception
    //   125	173	1881	java/lang/Exception
    //   176	184	1881	java/lang/Exception
    //   1870	1878	1881	java/lang/Exception
    //   309	323	1926	java/lang/Exception
    //   328	358	1926	java/lang/Exception
    //   358	407	1926	java/lang/Exception
    //   407	432	1926	java/lang/Exception
    //   432	473	1926	java/lang/Exception
    //   475	502	1926	java/lang/Exception
    //   504	536	1926	java/lang/Exception
    //   540	546	1926	java/lang/Exception
    //   546	609	1926	java/lang/Exception
    //   615	645	1926	java/lang/Exception
    //   650	665	1926	java/lang/Exception
    //   1915	1923	1926	java/lang/Exception
    //   1964	1997	1926	java/lang/Exception
    //   1997	2005	1926	java/lang/Exception
    //   2010	2022	1926	java/lang/Exception
    //   2027	2072	1926	java/lang/Exception
    //   2072	2080	1926	java/lang/Exception
    //   2085	2094	1926	java/lang/Exception
    //   2105	2128	1926	java/lang/Exception
    //   2135	2176	1926	java/lang/Exception
    //   689	763	2179	java/lang/Exception
    //   763	777	2213	java/lang/Exception
    //   782	789	2213	java/lang/Exception
    //   794	802	2213	java/lang/Exception
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
