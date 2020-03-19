package com.tapjoy;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
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
  private static int flags;
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
    secretKey = "";
    clientPackage = "";
    referralURL = "";
    plugin = "native";
    sdkType = "";
    videoEnabled = false;
    enableVideoCache = true;
    videoIDs = "";
    flags = 0;
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
    if ((flags & 0x1) != 0) {}
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
      return localObject2;
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
    //   0: getstatic 72	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   3: invokevirtual 436	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   6: astore_3
    //   7: getstatic 72	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   10: invokevirtual 489	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   13: ldc_w 491
    //   16: invokestatic 497	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   19: putstatic 80	com/tapjoy/TapjoyConnectCore:androidID	Ljava/lang/String;
    //   22: aload_3
    //   23: getstatic 72	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   26: invokevirtual 500	android/content/Context:getPackageName	()Ljava/lang/String;
    //   29: iconst_0
    //   30: invokevirtual 504	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   33: getfield 509	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   36: putstatic 106	com/tapjoy/TapjoyConnectCore:appVersion	Ljava/lang/String;
    //   39: ldc_w 511
    //   42: putstatic 96	com/tapjoy/TapjoyConnectCore:deviceType	Ljava/lang/String;
    //   45: ldc_w 511
    //   48: putstatic 116	com/tapjoy/TapjoyConnectCore:platformName	Ljava/lang/String;
    //   51: getstatic 516	android/os/Build:MODEL	Ljava/lang/String;
    //   54: putstatic 92	com/tapjoy/TapjoyConnectCore:deviceModel	Ljava/lang/String;
    //   57: getstatic 519	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   60: putstatic 94	com/tapjoy/TapjoyConnectCore:deviceManufacturer	Ljava/lang/String;
    //   63: getstatic 524	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   66: putstatic 98	com/tapjoy/TapjoyConnectCore:deviceOSVersion	Ljava/lang/String;
    //   69: invokestatic 530	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   72: invokevirtual 533	java/util/Locale:getCountry	()Ljava/lang/String;
    //   75: putstatic 100	com/tapjoy/TapjoyConnectCore:deviceCountryCode	Ljava/lang/String;
    //   78: invokestatic 530	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   81: invokevirtual 536	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   84: putstatic 102	com/tapjoy/TapjoyConnectCore:deviceLanguage	Ljava/lang/String;
    //   87: ldc_w 538
    //   90: putstatic 108	com/tapjoy/TapjoyConnectCore:libraryVersion	Ljava/lang/String;
    //   93: getstatic 72	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   96: ldc_w 300
    //   99: iconst_0
    //   100: invokevirtual 304	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   103: astore_3
    //   104: getstatic 72	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   107: ldc_w 540
    //   110: invokevirtual 258	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   113: checkcast 542	android/telephony/TelephonyManager
    //   116: astore 4
    //   118: aload 4
    //   120: ifnull +85 -> 205
    //   123: aload 4
    //   125: invokevirtual 545	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   128: putstatic 82	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   131: aload 4
    //   133: invokevirtual 548	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   136: putstatic 118	com/tapjoy/TapjoyConnectCore:carrierName	Ljava/lang/String;
    //   139: aload 4
    //   141: invokevirtual 551	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   144: putstatic 120	com/tapjoy/TapjoyConnectCore:carrierCountryCode	Ljava/lang/String;
    //   147: aload 4
    //   149: invokevirtual 554	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   152: ifnull +53 -> 205
    //   155: aload 4
    //   157: invokevirtual 554	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   160: invokevirtual 325	java/lang/String:length	()I
    //   163: iconst_5
    //   164: if_icmpeq +16 -> 180
    //   167: aload 4
    //   169: invokevirtual 554	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   172: invokevirtual 325	java/lang/String:length	()I
    //   175: bipush 6
    //   177: if_icmpne +28 -> 205
    //   180: aload 4
    //   182: invokevirtual 554	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   185: iconst_0
    //   186: iconst_3
    //   187: invokevirtual 474	java/lang/String:substring	(II)Ljava/lang/String;
    //   190: putstatic 122	com/tapjoy/TapjoyConnectCore:mobileCountryCode	Ljava/lang/String;
    //   193: aload 4
    //   195: invokevirtual 554	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   198: iconst_3
    //   199: invokevirtual 425	java/lang/String:substring	(I)Ljava/lang/String;
    //   202: putstatic 124	com/tapjoy/TapjoyConnectCore:mobileNetworkCode	Ljava/lang/String;
    //   205: ldc 19
    //   207: new 169	java/lang/StringBuilder
    //   210: dup
    //   211: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   214: ldc_w 556
    //   217: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: getstatic 82	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   223: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   226: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   229: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   232: iconst_0
    //   233: istore_1
    //   234: getstatic 82	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   237: ifnonnull +1145 -> 1382
    //   240: ldc 19
    //   242: ldc_w 558
    //   245: invokestatic 247	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   248: iconst_1
    //   249: istore_1
    //   250: ldc 19
    //   252: new 169	java/lang/StringBuilder
    //   255: dup
    //   256: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   259: ldc_w 560
    //   262: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   265: getstatic 563	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   268: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   274: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   277: iload_1
    //   278: istore_2
    //   279: getstatic 563	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   282: invokestatic 569	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   285: bipush 9
    //   287: if_icmplt +99 -> 386
    //   290: ldc 19
    //   292: ldc_w 571
    //   295: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   298: new 573	com/tapjoy/TapjoyHardwareUtil
    //   301: dup
    //   302: invokespecial 574	com/tapjoy/TapjoyHardwareUtil:<init>	()V
    //   305: invokevirtual 577	com/tapjoy/TapjoyHardwareUtil:getSerial	()Ljava/lang/String;
    //   308: putstatic 90	com/tapjoy/TapjoyConnectCore:serialID	Ljava/lang/String;
    //   311: iload_1
    //   312: ifeq +9 -> 321
    //   315: getstatic 90	com/tapjoy/TapjoyConnectCore:serialID	Ljava/lang/String;
    //   318: putstatic 82	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   321: ldc 19
    //   323: ldc_w 579
    //   326: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   329: ldc 19
    //   331: new 169	java/lang/StringBuilder
    //   334: dup
    //   335: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   338: ldc_w 581
    //   341: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   344: getstatic 82	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   347: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   350: ldc_w 583
    //   353: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   356: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   359: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   362: ldc 19
    //   364: ldc_w 579
    //   367: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   370: getstatic 82	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   373: ifnonnull +1135 -> 1508
    //   376: ldc 19
    //   378: ldc_w 585
    //   381: invokestatic 247	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   384: iconst_1
    //   385: istore_2
    //   386: iload_2
    //   387: ifeq +53 -> 440
    //   390: new 587	java/lang/StringBuffer
    //   393: dup
    //   394: invokespecial 588	java/lang/StringBuffer:<init>	()V
    //   397: astore 4
    //   399: aload 4
    //   401: ldc_w 590
    //   404: invokevirtual 593	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   407: pop
    //   408: aload_3
    //   409: ldc_w 595
    //   412: aconst_null
    //   413: invokeinterface 598 3 0
    //   418: astore 5
    //   420: aload 5
    //   422: ifnull +1306 -> 1728
    //   425: aload 5
    //   427: ldc 78
    //   429: invokevirtual 481	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   432: ifne +1296 -> 1728
    //   435: aload 5
    //   437: putstatic 82	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   440: getstatic 114	com/tapjoy/TapjoyConnectCore:userID	Ljava/lang/String;
    //   443: invokevirtual 325	java/lang/String:length	()I
    //   446: ifne +9 -> 455
    //   449: getstatic 82	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   452: putstatic 114	com/tapjoy/TapjoyConnectCore:userID	Ljava/lang/String;
    //   455: getstatic 82	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   458: invokestatic 241	com/tapjoy/TapjoyUtil:SHA256	(Ljava/lang/String;)Ljava/lang/String;
    //   461: putstatic 84	com/tapjoy/TapjoyConnectCore:sha2DeviceID	Ljava/lang/String;
    //   464: getstatic 563	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   467: invokestatic 569	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   470: iconst_3
    //   471: if_icmple +67 -> 538
    //   474: new 600	com/tapjoy/TapjoyDisplayMetricsUtil
    //   477: dup
    //   478: getstatic 72	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   481: invokespecial 602	com/tapjoy/TapjoyDisplayMetricsUtil:<init>	(Landroid/content/Context;)V
    //   484: astore 4
    //   486: new 169	java/lang/StringBuilder
    //   489: dup
    //   490: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   493: ldc 78
    //   495: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   498: aload 4
    //   500: invokevirtual 605	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensity	()I
    //   503: invokevirtual 235	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   506: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   509: putstatic 110	com/tapjoy/TapjoyConnectCore:deviceScreenDensity	Ljava/lang/String;
    //   512: new 169	java/lang/StringBuilder
    //   515: dup
    //   516: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   519: ldc 78
    //   521: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   524: aload 4
    //   526: invokevirtual 608	com/tapjoy/TapjoyDisplayMetricsUtil:getScreenLayoutSize	()I
    //   529: invokevirtual 235	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   532: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   535: putstatic 112	com/tapjoy/TapjoyConnectCore:deviceScreenLayoutSize	Ljava/lang/String;
    //   538: getstatic 72	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   541: ldc_w 276
    //   544: invokevirtual 258	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   547: checkcast 610	android/net/wifi/WifiManager
    //   550: astore 4
    //   552: aload 4
    //   554: ifnull +56 -> 610
    //   557: aload 4
    //   559: invokevirtual 614	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   562: astore 4
    //   564: aload 4
    //   566: ifnull +44 -> 610
    //   569: aload 4
    //   571: invokevirtual 619	android/net/wifi/WifiInfo:getMacAddress	()Ljava/lang/String;
    //   574: putstatic 86	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   577: getstatic 86	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   580: ifnull +30 -> 610
    //   583: getstatic 86	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   586: invokevirtual 325	java/lang/String:length	()I
    //   589: ifle +21 -> 610
    //   592: getstatic 86	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   595: invokevirtual 622	java/lang/String:toUpperCase	()Ljava/lang/String;
    //   598: putstatic 86	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   601: getstatic 86	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   604: invokestatic 625	com/tapjoy/TapjoyUtil:SHA1	(Ljava/lang/String;)Ljava/lang/String;
    //   607: putstatic 88	com/tapjoy/TapjoyConnectCore:sha1MacAddress	Ljava/lang/String;
    //   610: aload_3
    //   611: ldc_w 627
    //   614: aconst_null
    //   615: invokeinterface 598 3 0
    //   620: astore_3
    //   621: aload_3
    //   622: ifnull +16 -> 638
    //   625: aload_3
    //   626: ldc 78
    //   628: invokevirtual 481	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   631: ifne +7 -> 638
    //   634: aload_3
    //   635: putstatic 132	com/tapjoy/TapjoyConnectCore:referralURL	Ljava/lang/String;
    //   638: getstatic 72	com/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   641: invokevirtual 500	android/content/Context:getPackageName	()Ljava/lang/String;
    //   644: putstatic 130	com/tapjoy/TapjoyConnectCore:clientPackage	Ljava/lang/String;
    //   647: ldc 19
    //   649: ldc_w 629
    //   652: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   655: ldc 19
    //   657: new 169	java/lang/StringBuilder
    //   660: dup
    //   661: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   664: ldc_w 631
    //   667: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   670: getstatic 104	com/tapjoy/TapjoyConnectCore:appID	Ljava/lang/String;
    //   673: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   676: ldc_w 583
    //   679: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   682: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   685: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   688: ldc 19
    //   690: new 169	java/lang/StringBuilder
    //   693: dup
    //   694: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   697: ldc_w 633
    //   700: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   703: getstatic 80	com/tapjoy/TapjoyConnectCore:androidID	Ljava/lang/String;
    //   706: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   709: ldc_w 583
    //   712: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   715: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   718: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   721: ldc 19
    //   723: new 169	java/lang/StringBuilder
    //   726: dup
    //   727: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   730: ldc_w 635
    //   733: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   736: getstatic 130	com/tapjoy/TapjoyConnectCore:clientPackage	Ljava/lang/String;
    //   739: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   742: ldc_w 583
    //   745: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   748: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   751: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   754: ldc 19
    //   756: new 169	java/lang/StringBuilder
    //   759: dup
    //   760: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   763: ldc_w 637
    //   766: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   769: getstatic 82	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   772: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   775: ldc_w 583
    //   778: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   781: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   784: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   787: ldc 19
    //   789: new 169	java/lang/StringBuilder
    //   792: dup
    //   793: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   796: ldc_w 639
    //   799: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   802: getstatic 84	com/tapjoy/TapjoyConnectCore:sha2DeviceID	Ljava/lang/String;
    //   805: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   808: ldc_w 583
    //   811: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   814: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   817: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   820: ldc 19
    //   822: new 169	java/lang/StringBuilder
    //   825: dup
    //   826: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   829: ldc_w 641
    //   832: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   835: getstatic 90	com/tapjoy/TapjoyConnectCore:serialID	Ljava/lang/String;
    //   838: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   841: ldc_w 583
    //   844: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   847: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   850: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   853: ldc 19
    //   855: new 169	java/lang/StringBuilder
    //   858: dup
    //   859: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   862: ldc_w 643
    //   865: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   868: getstatic 86	com/tapjoy/TapjoyConnectCore:macAddress	Ljava/lang/String;
    //   871: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   874: ldc_w 583
    //   877: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   880: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   883: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   886: ldc 19
    //   888: new 169	java/lang/StringBuilder
    //   891: dup
    //   892: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   895: ldc_w 645
    //   898: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   901: getstatic 88	com/tapjoy/TapjoyConnectCore:sha1MacAddress	Ljava/lang/String;
    //   904: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   907: ldc_w 583
    //   910: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   913: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   916: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   919: ldc 19
    //   921: new 169	java/lang/StringBuilder
    //   924: dup
    //   925: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   928: ldc_w 647
    //   931: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   934: getstatic 92	com/tapjoy/TapjoyConnectCore:deviceModel	Ljava/lang/String;
    //   937: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   940: ldc_w 583
    //   943: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   946: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   949: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   952: ldc 19
    //   954: new 169	java/lang/StringBuilder
    //   957: dup
    //   958: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   961: ldc_w 649
    //   964: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   967: getstatic 94	com/tapjoy/TapjoyConnectCore:deviceManufacturer	Ljava/lang/String;
    //   970: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   973: ldc_w 583
    //   976: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   979: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   982: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   985: ldc 19
    //   987: new 169	java/lang/StringBuilder
    //   990: dup
    //   991: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   994: ldc_w 651
    //   997: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1000: getstatic 96	com/tapjoy/TapjoyConnectCore:deviceType	Ljava/lang/String;
    //   1003: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1006: ldc_w 583
    //   1009: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1012: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1015: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1018: ldc 19
    //   1020: new 169	java/lang/StringBuilder
    //   1023: dup
    //   1024: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   1027: ldc_w 653
    //   1030: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1033: getstatic 108	com/tapjoy/TapjoyConnectCore:libraryVersion	Ljava/lang/String;
    //   1036: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1039: ldc_w 583
    //   1042: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1045: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1048: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1051: ldc 19
    //   1053: new 169	java/lang/StringBuilder
    //   1056: dup
    //   1057: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   1060: ldc_w 655
    //   1063: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1066: getstatic 98	com/tapjoy/TapjoyConnectCore:deviceOSVersion	Ljava/lang/String;
    //   1069: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1072: ldc_w 583
    //   1075: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1078: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1081: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1084: ldc 19
    //   1086: new 169	java/lang/StringBuilder
    //   1089: dup
    //   1090: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   1093: ldc_w 657
    //   1096: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1099: getstatic 100	com/tapjoy/TapjoyConnectCore:deviceCountryCode	Ljava/lang/String;
    //   1102: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1105: ldc_w 583
    //   1108: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1111: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1114: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1117: ldc 19
    //   1119: new 169	java/lang/StringBuilder
    //   1122: dup
    //   1123: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   1126: ldc_w 659
    //   1129: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1132: getstatic 102	com/tapjoy/TapjoyConnectCore:deviceLanguage	Ljava/lang/String;
    //   1135: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1138: ldc_w 583
    //   1141: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1144: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1147: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1150: ldc 19
    //   1152: new 169	java/lang/StringBuilder
    //   1155: dup
    //   1156: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   1159: ldc_w 661
    //   1162: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1165: getstatic 110	com/tapjoy/TapjoyConnectCore:deviceScreenDensity	Ljava/lang/String;
    //   1168: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1171: ldc_w 583
    //   1174: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1177: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1180: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1183: ldc 19
    //   1185: new 169	java/lang/StringBuilder
    //   1188: dup
    //   1189: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   1192: ldc_w 663
    //   1195: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1198: getstatic 112	com/tapjoy/TapjoyConnectCore:deviceScreenLayoutSize	Ljava/lang/String;
    //   1201: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1204: ldc_w 583
    //   1207: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1210: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1213: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1216: ldc 19
    //   1218: new 169	java/lang/StringBuilder
    //   1221: dup
    //   1222: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   1225: ldc_w 665
    //   1228: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1231: getstatic 118	com/tapjoy/TapjoyConnectCore:carrierName	Ljava/lang/String;
    //   1234: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1237: ldc_w 583
    //   1240: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1243: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1246: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1249: ldc 19
    //   1251: new 169	java/lang/StringBuilder
    //   1254: dup
    //   1255: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   1258: ldc_w 667
    //   1261: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1264: getstatic 120	com/tapjoy/TapjoyConnectCore:carrierCountryCode	Ljava/lang/String;
    //   1267: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1270: ldc_w 583
    //   1273: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1276: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1279: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1282: ldc 19
    //   1284: new 169	java/lang/StringBuilder
    //   1287: dup
    //   1288: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   1291: ldc_w 669
    //   1294: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1297: getstatic 122	com/tapjoy/TapjoyConnectCore:mobileCountryCode	Ljava/lang/String;
    //   1300: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1303: ldc_w 583
    //   1306: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1309: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1312: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1315: ldc 19
    //   1317: new 169	java/lang/StringBuilder
    //   1320: dup
    //   1321: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   1324: ldc_w 671
    //   1327: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1330: getstatic 124	com/tapjoy/TapjoyConnectCore:mobileNetworkCode	Ljava/lang/String;
    //   1333: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1336: ldc_w 583
    //   1339: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1342: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1345: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1348: ldc 19
    //   1350: new 169	java/lang/StringBuilder
    //   1353: dup
    //   1354: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   1357: ldc_w 673
    //   1360: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1363: getstatic 132	com/tapjoy/TapjoyConnectCore:referralURL	Ljava/lang/String;
    //   1366: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1369: ldc_w 583
    //   1372: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1375: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1378: invokestatic 189	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1381: return
    //   1382: getstatic 82	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1385: invokevirtual 325	java/lang/String:length	()I
    //   1388: ifeq +27 -> 1415
    //   1391: getstatic 82	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1394: ldc_w 675
    //   1397: invokevirtual 481	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1400: ifne +15 -> 1415
    //   1403: getstatic 82	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1406: ldc_w 677
    //   1409: invokevirtual 481	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1412: ifeq +16 -> 1428
    //   1415: ldc 19
    //   1417: ldc_w 679
    //   1420: invokestatic 247	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1423: iconst_1
    //   1424: istore_1
    //   1425: goto -1175 -> 250
    //   1428: getstatic 82	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1431: invokevirtual 682	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   1434: putstatic 82	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1437: goto -1187 -> 250
    //   1440: astore 4
    //   1442: ldc 19
    //   1444: new 169	java/lang/StringBuilder
    //   1447: dup
    //   1448: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   1451: ldc_w 684
    //   1454: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1457: aload 4
    //   1459: invokevirtual 244	java/lang/Exception:toString	()Ljava/lang/String;
    //   1462: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1465: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1468: invokestatic 247	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1471: aconst_null
    //   1472: putstatic 82	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1475: goto -1035 -> 440
    //   1478: astore_3
    //   1479: ldc 19
    //   1481: new 169	java/lang/StringBuilder
    //   1484: dup
    //   1485: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   1488: ldc_w 686
    //   1491: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1494: aload_3
    //   1495: invokevirtual 244	java/lang/Exception:toString	()Ljava/lang/String;
    //   1498: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1501: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1504: invokestatic 247	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1507: return
    //   1508: getstatic 82	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1511: invokevirtual 325	java/lang/String:length	()I
    //   1514: ifeq +39 -> 1553
    //   1517: getstatic 82	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1520: ldc_w 675
    //   1523: invokevirtual 481	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1526: ifne +27 -> 1553
    //   1529: getstatic 82	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1532: ldc_w 677
    //   1535: invokevirtual 481	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1538: ifne +15 -> 1553
    //   1541: getstatic 82	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1544: ldc_w 688
    //   1547: invokevirtual 481	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1550: ifeq +16 -> 1566
    //   1553: ldc 19
    //   1555: ldc_w 690
    //   1558: invokestatic 247	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1561: iconst_1
    //   1562: istore_2
    //   1563: goto -1177 -> 386
    //   1566: getstatic 82	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1569: invokevirtual 682	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   1572: putstatic 82	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1575: iconst_0
    //   1576: istore_2
    //   1577: goto -1191 -> 386
    //   1580: iload_1
    //   1581: bipush 32
    //   1583: if_icmpge +33 -> 1616
    //   1586: aload 4
    //   1588: ldc_w 692
    //   1591: invokestatic 698	java/lang/Math:random	()D
    //   1594: ldc2_w 699
    //   1597: dmul
    //   1598: d2i
    //   1599: bipush 30
    //   1601: irem
    //   1602: invokevirtual 704	java/lang/String:charAt	(I)C
    //   1605: invokevirtual 707	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   1608: pop
    //   1609: iload_1
    //   1610: iconst_1
    //   1611: iadd
    //   1612: istore_1
    //   1613: goto -33 -> 1580
    //   1616: aload 4
    //   1618: invokevirtual 708	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   1621: invokevirtual 682	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   1624: putstatic 82	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1627: aload_3
    //   1628: invokeinterface 712 1 0
    //   1633: astore 4
    //   1635: aload 4
    //   1637: ldc_w 595
    //   1640: getstatic 82	com/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1643: invokeinterface 718 3 0
    //   1648: pop
    //   1649: aload 4
    //   1651: invokeinterface 721 1 0
    //   1656: pop
    //   1657: goto -1217 -> 440
    //   1660: astore 4
    //   1662: ldc 19
    //   1664: new 169	java/lang/StringBuilder
    //   1667: dup
    //   1668: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   1671: ldc_w 723
    //   1674: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1677: aload 4
    //   1679: invokevirtual 244	java/lang/Exception:toString	()Ljava/lang/String;
    //   1682: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1685: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1688: invokestatic 247	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1691: goto -1153 -> 538
    //   1694: astore 4
    //   1696: ldc 19
    //   1698: new 169	java/lang/StringBuilder
    //   1701: dup
    //   1702: invokespecial 170	java/lang/StringBuilder:<init>	()V
    //   1705: ldc_w 725
    //   1708: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1711: aload 4
    //   1713: invokevirtual 244	java/lang/Exception:toString	()Ljava/lang/String;
    //   1716: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1719: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1722: invokestatic 247	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1725: goto -1115 -> 610
    //   1728: iconst_0
    //   1729: istore_1
    //   1730: goto -150 -> 1580
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1733	0	this	TapjoyConnectCore
    //   233	1497	1	i	int
    //   278	1299	2	j	int
    //   6	629	3	localObject1	Object
    //   1478	150	3	localException1	Exception
    //   116	454	4	localObject2	Object
    //   1440	177	4	localException2	Exception
    //   1633	17	4	localEditor	SharedPreferences.Editor
    //   1660	18	4	localException3	Exception
    //   1694	18	4	localException4	Exception
    //   418	18	5	str	String
    // Exception table:
    //   from	to	target	type
    //   104	118	1440	java/lang/Exception
    //   123	180	1440	java/lang/Exception
    //   180	205	1440	java/lang/Exception
    //   205	232	1440	java/lang/Exception
    //   234	248	1440	java/lang/Exception
    //   250	277	1440	java/lang/Exception
    //   279	311	1440	java/lang/Exception
    //   315	321	1440	java/lang/Exception
    //   321	384	1440	java/lang/Exception
    //   390	420	1440	java/lang/Exception
    //   425	440	1440	java/lang/Exception
    //   1382	1415	1440	java/lang/Exception
    //   1415	1423	1440	java/lang/Exception
    //   1428	1437	1440	java/lang/Exception
    //   1508	1553	1440	java/lang/Exception
    //   1553	1561	1440	java/lang/Exception
    //   1566	1575	1440	java/lang/Exception
    //   1586	1609	1440	java/lang/Exception
    //   1616	1657	1440	java/lang/Exception
    //   7	104	1478	java/lang/Exception
    //   440	455	1478	java/lang/Exception
    //   455	464	1478	java/lang/Exception
    //   610	621	1478	java/lang/Exception
    //   625	638	1478	java/lang/Exception
    //   638	1381	1478	java/lang/Exception
    //   1442	1475	1478	java/lang/Exception
    //   1662	1691	1478	java/lang/Exception
    //   1696	1725	1478	java/lang/Exception
    //   464	538	1660	java/lang/Exception
    //   538	552	1694	java/lang/Exception
    //   557	564	1694	java/lang/Exception
    //   569	610	1694	java/lang/Exception
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
    requestTapjoyConnect(paramContext, paramString1, paramString2, 0);
  }
  
  public static void requestTapjoyConnect(Context paramContext, String paramString1, String paramString2, int paramInt)
  {
    appID = paramString1;
    secretKey = paramString2;
    flags = paramInt;
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
    paramString = str;
    if ((flags & 0x1) == 0) {
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
