package com.gi.remoteconfig.tapjoy;

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
  private static String libraryVersion;
  private static String matchingPackageNames = "";
  private static String mobileCountryCode;
  private static String mobileNetworkCode;
  private static String paidAppActionID;
  private static String platformName;
  private static String plugin;
  private static String referralURL;
  private static String sdkType;
  private static String secretKey;
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
    localObject1 = (String)localObject1 + "udid=" + Uri.encode(deviceID) + "&";
    localObject1 = (String)localObject1 + "device_name=" + Uri.encode(deviceModel) + "&";
    localObject1 = (String)localObject1 + "device_manufacturer=" + Uri.encode(deviceManufacturer) + "&";
    localObject1 = (String)localObject1 + "device_type=" + Uri.encode(deviceType) + "&";
    localObject1 = (String)localObject1 + "os_version=" + Uri.encode(deviceOSVersion) + "&";
    localObject1 = (String)localObject1 + "country_code=" + Uri.encode(deviceCountryCode) + "&";
    localObject1 = (String)localObject1 + "language_code=" + Uri.encode(deviceLanguage) + "&";
    localObject1 = (String)localObject1 + "app_version=" + Uri.encode(appVersion) + "&";
    localObject1 = (String)localObject1 + "library_version=" + Uri.encode(libraryVersion) + "&";
    localObject1 = (String)localObject1 + "platform=" + Uri.encode(platformName) + "&";
    Object localObject2 = (String)localObject1 + "display_multiplier=" + Uri.encode(Float.toString(currencyMultiplier));
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
    //   0: getstatic 66	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   3: invokevirtual 414	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   6: astore_3
    //   7: getstatic 66	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   10: invokevirtual 470	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   13: ldc_w 472
    //   16: invokestatic 478	android/provider/Settings$Secure:getString	(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;
    //   19: putstatic 74	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:androidID	Ljava/lang/String;
    //   22: aload_3
    //   23: getstatic 66	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   26: invokevirtual 481	android/content/Context:getPackageName	()Ljava/lang/String;
    //   29: iconst_0
    //   30: invokevirtual 485	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   33: getfield 490	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   36: putstatic 92	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:appVersion	Ljava/lang/String;
    //   39: ldc_w 492
    //   42: putstatic 82	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceType	Ljava/lang/String;
    //   45: ldc_w 492
    //   48: putstatic 102	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:platformName	Ljava/lang/String;
    //   51: getstatic 497	android/os/Build:MODEL	Ljava/lang/String;
    //   54: putstatic 78	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceModel	Ljava/lang/String;
    //   57: getstatic 500	android/os/Build:MANUFACTURER	Ljava/lang/String;
    //   60: putstatic 80	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceManufacturer	Ljava/lang/String;
    //   63: getstatic 505	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   66: putstatic 84	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceOSVersion	Ljava/lang/String;
    //   69: invokestatic 511	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   72: invokevirtual 514	java/util/Locale:getCountry	()Ljava/lang/String;
    //   75: putstatic 86	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceCountryCode	Ljava/lang/String;
    //   78: invokestatic 511	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   81: invokevirtual 517	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   84: putstatic 88	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceLanguage	Ljava/lang/String;
    //   87: ldc_w 519
    //   90: putstatic 94	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:libraryVersion	Ljava/lang/String;
    //   93: getstatic 66	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   96: ldc_w 284
    //   99: iconst_0
    //   100: invokevirtual 288	android/content/Context:getSharedPreferences	(Ljava/lang/String;I)Landroid/content/SharedPreferences;
    //   103: astore_3
    //   104: getstatic 66	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   107: ldc_w 521
    //   110: invokevirtual 242	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   113: checkcast 523	android/telephony/TelephonyManager
    //   116: astore 4
    //   118: aload 4
    //   120: ifnull +85 -> 205
    //   123: aload 4
    //   125: invokevirtual 526	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   128: putstatic 76	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   131: aload 4
    //   133: invokevirtual 529	android/telephony/TelephonyManager:getNetworkOperatorName	()Ljava/lang/String;
    //   136: putstatic 104	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:carrierName	Ljava/lang/String;
    //   139: aload 4
    //   141: invokevirtual 532	android/telephony/TelephonyManager:getNetworkCountryIso	()Ljava/lang/String;
    //   144: putstatic 106	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:carrierCountryCode	Ljava/lang/String;
    //   147: aload 4
    //   149: invokevirtual 535	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   152: ifnull +53 -> 205
    //   155: aload 4
    //   157: invokevirtual 535	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   160: invokevirtual 334	java/lang/String:length	()I
    //   163: iconst_5
    //   164: if_icmpeq +16 -> 180
    //   167: aload 4
    //   169: invokevirtual 535	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   172: invokevirtual 334	java/lang/String:length	()I
    //   175: bipush 6
    //   177: if_icmpne +28 -> 205
    //   180: aload 4
    //   182: invokevirtual 535	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   185: iconst_0
    //   186: iconst_3
    //   187: invokevirtual 455	java/lang/String:substring	(II)Ljava/lang/String;
    //   190: putstatic 108	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:mobileCountryCode	Ljava/lang/String;
    //   193: aload 4
    //   195: invokevirtual 535	android/telephony/TelephonyManager:getNetworkOperator	()Ljava/lang/String;
    //   198: iconst_3
    //   199: invokevirtual 403	java/lang/String:substring	(I)Ljava/lang/String;
    //   202: putstatic 110	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:mobileNetworkCode	Ljava/lang/String;
    //   205: ldc 19
    //   207: new 153	java/lang/StringBuilder
    //   210: dup
    //   211: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   214: ldc_w 537
    //   217: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: getstatic 76	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   223: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   226: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   229: invokestatic 173	com/gi/remoteconfig/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   232: iconst_0
    //   233: istore_1
    //   234: getstatic 76	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   237: ifnonnull +928 -> 1165
    //   240: ldc 19
    //   242: ldc_w 539
    //   245: invokestatic 231	com/gi/remoteconfig/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   248: iconst_1
    //   249: istore_1
    //   250: ldc 19
    //   252: new 153	java/lang/StringBuilder
    //   255: dup
    //   256: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   259: ldc_w 541
    //   262: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   265: getstatic 544	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   268: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   274: invokestatic 173	com/gi/remoteconfig/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   277: iload_1
    //   278: istore_2
    //   279: iload_1
    //   280: ifeq +102 -> 382
    //   283: iload_1
    //   284: istore_2
    //   285: getstatic 544	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   288: invokestatic 550	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   291: bipush 9
    //   293: if_icmplt +89 -> 382
    //   296: ldc 19
    //   298: ldc_w 552
    //   301: invokestatic 173	com/gi/remoteconfig/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   304: new 554	com/gi/remoteconfig/tapjoy/TapjoyHardwareUtil
    //   307: dup
    //   308: invokespecial 555	com/gi/remoteconfig/tapjoy/TapjoyHardwareUtil:<init>	()V
    //   311: invokevirtual 558	com/gi/remoteconfig/tapjoy/TapjoyHardwareUtil:getSerial	()Ljava/lang/String;
    //   314: putstatic 76	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   317: ldc 19
    //   319: ldc_w 560
    //   322: invokestatic 173	com/gi/remoteconfig/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   325: ldc 19
    //   327: new 153	java/lang/StringBuilder
    //   330: dup
    //   331: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   334: ldc_w 562
    //   337: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   340: getstatic 76	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   343: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   346: ldc_w 564
    //   349: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   352: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   355: invokestatic 173	com/gi/remoteconfig/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   358: ldc 19
    //   360: ldc_w 560
    //   363: invokestatic 173	com/gi/remoteconfig/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   366: getstatic 76	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   369: ifnonnull +902 -> 1271
    //   372: ldc 19
    //   374: ldc_w 566
    //   377: invokestatic 231	com/gi/remoteconfig/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   380: iconst_1
    //   381: istore_2
    //   382: iload_2
    //   383: ifeq +53 -> 436
    //   386: new 568	java/lang/StringBuffer
    //   389: dup
    //   390: invokespecial 569	java/lang/StringBuffer:<init>	()V
    //   393: astore 4
    //   395: aload 4
    //   397: ldc_w 571
    //   400: invokevirtual 574	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   403: pop
    //   404: aload_3
    //   405: ldc_w 576
    //   408: aconst_null
    //   409: invokeinterface 579 3 0
    //   414: astore 5
    //   416: aload 5
    //   418: ifnull +1039 -> 1457
    //   421: aload 5
    //   423: ldc 72
    //   425: invokevirtual 462	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   428: ifne +1029 -> 1457
    //   431: aload 5
    //   433: putstatic 76	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   436: getstatic 100	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:userID	Ljava/lang/String;
    //   439: invokevirtual 334	java/lang/String:length	()I
    //   442: ifne +9 -> 451
    //   445: getstatic 76	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   448: putstatic 100	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:userID	Ljava/lang/String;
    //   451: getstatic 544	android/os/Build$VERSION:SDK	Ljava/lang/String;
    //   454: invokestatic 550	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   457: iconst_3
    //   458: if_icmple +67 -> 525
    //   461: new 581	com/gi/remoteconfig/tapjoy/TapjoyDisplayMetricsUtil
    //   464: dup
    //   465: getstatic 66	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   468: invokespecial 583	com/gi/remoteconfig/tapjoy/TapjoyDisplayMetricsUtil:<init>	(Landroid/content/Context;)V
    //   471: astore 4
    //   473: new 153	java/lang/StringBuilder
    //   476: dup
    //   477: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   480: ldc 72
    //   482: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   485: aload 4
    //   487: invokevirtual 586	com/gi/remoteconfig/tapjoy/TapjoyDisplayMetricsUtil:getScreenDensity	()I
    //   490: invokevirtual 219	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   493: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   496: putstatic 96	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceScreenDensity	Ljava/lang/String;
    //   499: new 153	java/lang/StringBuilder
    //   502: dup
    //   503: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   506: ldc 72
    //   508: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   511: aload 4
    //   513: invokevirtual 589	com/gi/remoteconfig/tapjoy/TapjoyDisplayMetricsUtil:getScreenLayoutSize	()I
    //   516: invokevirtual 219	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   519: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   522: putstatic 98	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceScreenLayoutSize	Ljava/lang/String;
    //   525: aload_3
    //   526: ldc_w 591
    //   529: aconst_null
    //   530: invokeinterface 579 3 0
    //   535: astore_3
    //   536: aload_3
    //   537: ifnull +16 -> 553
    //   540: aload_3
    //   541: ldc 72
    //   543: invokevirtual 462	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   546: ifne +7 -> 553
    //   549: aload_3
    //   550: putstatic 118	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:referralURL	Ljava/lang/String;
    //   553: getstatic 66	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:context	Landroid/content/Context;
    //   556: invokevirtual 481	android/content/Context:getPackageName	()Ljava/lang/String;
    //   559: putstatic 116	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:clientPackage	Ljava/lang/String;
    //   562: ldc 19
    //   564: ldc_w 593
    //   567: invokestatic 173	com/gi/remoteconfig/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   570: ldc 19
    //   572: new 153	java/lang/StringBuilder
    //   575: dup
    //   576: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   579: ldc_w 595
    //   582: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   585: getstatic 90	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:appID	Ljava/lang/String;
    //   588: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   591: ldc_w 564
    //   594: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   597: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   600: invokestatic 173	com/gi/remoteconfig/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   603: ldc 19
    //   605: new 153	java/lang/StringBuilder
    //   608: dup
    //   609: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   612: ldc_w 597
    //   615: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   618: getstatic 74	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:androidID	Ljava/lang/String;
    //   621: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   624: ldc_w 564
    //   627: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   630: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   633: invokestatic 173	com/gi/remoteconfig/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   636: ldc 19
    //   638: new 153	java/lang/StringBuilder
    //   641: dup
    //   642: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   645: ldc_w 599
    //   648: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   651: getstatic 116	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:clientPackage	Ljava/lang/String;
    //   654: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   657: ldc_w 564
    //   660: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   663: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   666: invokestatic 173	com/gi/remoteconfig/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   669: ldc 19
    //   671: new 153	java/lang/StringBuilder
    //   674: dup
    //   675: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   678: ldc_w 601
    //   681: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   684: getstatic 76	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   687: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   690: ldc_w 564
    //   693: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   696: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   699: invokestatic 173	com/gi/remoteconfig/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   702: ldc 19
    //   704: new 153	java/lang/StringBuilder
    //   707: dup
    //   708: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   711: ldc_w 603
    //   714: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   717: getstatic 78	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceModel	Ljava/lang/String;
    //   720: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   723: ldc_w 564
    //   726: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   729: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   732: invokestatic 173	com/gi/remoteconfig/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   735: ldc 19
    //   737: new 153	java/lang/StringBuilder
    //   740: dup
    //   741: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   744: ldc_w 605
    //   747: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   750: getstatic 80	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceManufacturer	Ljava/lang/String;
    //   753: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   756: ldc_w 564
    //   759: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   762: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   765: invokestatic 173	com/gi/remoteconfig/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   768: ldc 19
    //   770: new 153	java/lang/StringBuilder
    //   773: dup
    //   774: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   777: ldc_w 607
    //   780: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   783: getstatic 82	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceType	Ljava/lang/String;
    //   786: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   789: ldc_w 564
    //   792: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   795: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   798: invokestatic 173	com/gi/remoteconfig/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   801: ldc 19
    //   803: new 153	java/lang/StringBuilder
    //   806: dup
    //   807: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   810: ldc_w 609
    //   813: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   816: getstatic 94	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:libraryVersion	Ljava/lang/String;
    //   819: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   822: ldc_w 564
    //   825: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   828: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   831: invokestatic 173	com/gi/remoteconfig/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   834: ldc 19
    //   836: new 153	java/lang/StringBuilder
    //   839: dup
    //   840: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   843: ldc_w 611
    //   846: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   849: getstatic 84	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceOSVersion	Ljava/lang/String;
    //   852: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   855: ldc_w 564
    //   858: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   861: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   864: invokestatic 173	com/gi/remoteconfig/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   867: ldc 19
    //   869: new 153	java/lang/StringBuilder
    //   872: dup
    //   873: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   876: ldc_w 613
    //   879: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   882: getstatic 86	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceCountryCode	Ljava/lang/String;
    //   885: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   888: ldc_w 564
    //   891: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   894: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   897: invokestatic 173	com/gi/remoteconfig/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   900: ldc 19
    //   902: new 153	java/lang/StringBuilder
    //   905: dup
    //   906: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   909: ldc_w 615
    //   912: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   915: getstatic 88	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceLanguage	Ljava/lang/String;
    //   918: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   921: ldc_w 564
    //   924: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   927: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   930: invokestatic 173	com/gi/remoteconfig/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   933: ldc 19
    //   935: new 153	java/lang/StringBuilder
    //   938: dup
    //   939: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   942: ldc_w 617
    //   945: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   948: getstatic 96	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceScreenDensity	Ljava/lang/String;
    //   951: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   954: ldc_w 564
    //   957: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   960: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   963: invokestatic 173	com/gi/remoteconfig/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   966: ldc 19
    //   968: new 153	java/lang/StringBuilder
    //   971: dup
    //   972: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   975: ldc_w 619
    //   978: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   981: getstatic 98	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceScreenLayoutSize	Ljava/lang/String;
    //   984: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   987: ldc_w 564
    //   990: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   993: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   996: invokestatic 173	com/gi/remoteconfig/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   999: ldc 19
    //   1001: new 153	java/lang/StringBuilder
    //   1004: dup
    //   1005: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   1008: ldc_w 621
    //   1011: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1014: getstatic 104	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:carrierName	Ljava/lang/String;
    //   1017: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1020: ldc_w 564
    //   1023: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1026: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1029: invokestatic 173	com/gi/remoteconfig/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1032: ldc 19
    //   1034: new 153	java/lang/StringBuilder
    //   1037: dup
    //   1038: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   1041: ldc_w 623
    //   1044: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1047: getstatic 106	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:carrierCountryCode	Ljava/lang/String;
    //   1050: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1053: ldc_w 564
    //   1056: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1059: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1062: invokestatic 173	com/gi/remoteconfig/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1065: ldc 19
    //   1067: new 153	java/lang/StringBuilder
    //   1070: dup
    //   1071: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   1074: ldc_w 625
    //   1077: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1080: getstatic 108	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:mobileCountryCode	Ljava/lang/String;
    //   1083: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1086: ldc_w 564
    //   1089: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1092: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1095: invokestatic 173	com/gi/remoteconfig/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1098: ldc 19
    //   1100: new 153	java/lang/StringBuilder
    //   1103: dup
    //   1104: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   1107: ldc_w 627
    //   1110: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1113: getstatic 110	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:mobileNetworkCode	Ljava/lang/String;
    //   1116: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1119: ldc_w 564
    //   1122: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1125: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1128: invokestatic 173	com/gi/remoteconfig/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1131: ldc 19
    //   1133: new 153	java/lang/StringBuilder
    //   1136: dup
    //   1137: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   1140: ldc_w 629
    //   1143: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1146: getstatic 118	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:referralURL	Ljava/lang/String;
    //   1149: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1152: ldc_w 564
    //   1155: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1158: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1161: invokestatic 173	com/gi/remoteconfig/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1164: return
    //   1165: getstatic 76	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1168: invokevirtual 334	java/lang/String:length	()I
    //   1171: ifeq +27 -> 1198
    //   1174: getstatic 76	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1177: ldc_w 631
    //   1180: invokevirtual 462	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1183: ifne +15 -> 1198
    //   1186: getstatic 76	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1189: ldc_w 633
    //   1192: invokevirtual 462	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1195: ifeq +16 -> 1211
    //   1198: ldc 19
    //   1200: ldc_w 635
    //   1203: invokestatic 231	com/gi/remoteconfig/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1206: iconst_1
    //   1207: istore_1
    //   1208: goto -958 -> 250
    //   1211: getstatic 76	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1214: invokevirtual 638	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   1217: putstatic 76	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1220: goto -970 -> 250
    //   1223: astore 4
    //   1225: ldc 19
    //   1227: new 153	java/lang/StringBuilder
    //   1230: dup
    //   1231: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   1234: ldc_w 640
    //   1237: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1240: aload 4
    //   1242: invokevirtual 228	java/lang/Exception:toString	()Ljava/lang/String;
    //   1245: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1248: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1251: invokestatic 231	com/gi/remoteconfig/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1254: aconst_null
    //   1255: putstatic 76	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1258: goto -822 -> 436
    //   1261: astore_3
    //   1262: ldc 19
    //   1264: ldc_w 642
    //   1267: invokestatic 231	com/gi/remoteconfig/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1270: return
    //   1271: getstatic 76	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1274: invokevirtual 334	java/lang/String:length	()I
    //   1277: ifeq +39 -> 1316
    //   1280: getstatic 76	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1283: ldc_w 631
    //   1286: invokevirtual 462	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1289: ifne +27 -> 1316
    //   1292: getstatic 76	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1295: ldc_w 633
    //   1298: invokevirtual 462	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1301: ifne +15 -> 1316
    //   1304: getstatic 76	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1307: ldc_w 644
    //   1310: invokevirtual 462	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1313: ifeq +16 -> 1329
    //   1316: ldc 19
    //   1318: ldc_w 646
    //   1321: invokestatic 231	com/gi/remoteconfig/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1324: iconst_1
    //   1325: istore_2
    //   1326: goto -944 -> 382
    //   1329: getstatic 76	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1332: invokevirtual 638	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   1335: putstatic 76	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1338: iconst_0
    //   1339: istore_2
    //   1340: goto -958 -> 382
    //   1343: iload_1
    //   1344: bipush 32
    //   1346: if_icmpge +33 -> 1379
    //   1349: aload 4
    //   1351: ldc_w 648
    //   1354: invokestatic 654	java/lang/Math:random	()D
    //   1357: ldc2_w 655
    //   1360: dmul
    //   1361: d2i
    //   1362: bipush 30
    //   1364: irem
    //   1365: invokevirtual 660	java/lang/String:charAt	(I)C
    //   1368: invokevirtual 663	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
    //   1371: pop
    //   1372: iload_1
    //   1373: iconst_1
    //   1374: iadd
    //   1375: istore_1
    //   1376: goto -33 -> 1343
    //   1379: aload 4
    //   1381: invokevirtual 664	java/lang/StringBuffer:toString	()Ljava/lang/String;
    //   1384: invokevirtual 638	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   1387: putstatic 76	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1390: aload_3
    //   1391: invokeinterface 668 1 0
    //   1396: astore 4
    //   1398: aload 4
    //   1400: ldc_w 576
    //   1403: getstatic 76	com/gi/remoteconfig/tapjoy/TapjoyConnectCore:deviceID	Ljava/lang/String;
    //   1406: invokeinterface 674 3 0
    //   1411: pop
    //   1412: aload 4
    //   1414: invokeinterface 677 1 0
    //   1419: pop
    //   1420: goto -984 -> 436
    //   1423: astore 4
    //   1425: ldc 19
    //   1427: new 153	java/lang/StringBuilder
    //   1430: dup
    //   1431: invokespecial 154	java/lang/StringBuilder:<init>	()V
    //   1434: ldc_w 679
    //   1437: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1440: aload 4
    //   1442: invokevirtual 228	java/lang/Exception:toString	()Ljava/lang/String;
    //   1445: invokevirtual 160	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1448: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1451: invokestatic 231	com/gi/remoteconfig/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1454: goto -929 -> 525
    //   1457: iconst_0
    //   1458: istore_1
    //   1459: goto -116 -> 1343
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1462	0	this	TapjoyConnectCore
    //   233	1226	1	i	int
    //   278	1062	2	j	int
    //   6	544	3	localObject1	Object
    //   1261	130	3	localException1	Exception
    //   116	396	4	localObject2	Object
    //   1223	157	4	localException2	Exception
    //   1396	17	4	localEditor	SharedPreferences.Editor
    //   1423	18	4	localException3	Exception
    //   414	18	5	str	String
    // Exception table:
    //   from	to	target	type
    //   104	118	1223	java/lang/Exception
    //   123	180	1223	java/lang/Exception
    //   180	205	1223	java/lang/Exception
    //   205	232	1223	java/lang/Exception
    //   234	248	1223	java/lang/Exception
    //   250	277	1223	java/lang/Exception
    //   285	380	1223	java/lang/Exception
    //   386	416	1223	java/lang/Exception
    //   421	436	1223	java/lang/Exception
    //   1165	1198	1223	java/lang/Exception
    //   1198	1206	1223	java/lang/Exception
    //   1211	1220	1223	java/lang/Exception
    //   1271	1316	1223	java/lang/Exception
    //   1316	1324	1223	java/lang/Exception
    //   1329	1338	1223	java/lang/Exception
    //   1349	1372	1223	java/lang/Exception
    //   1379	1420	1223	java/lang/Exception
    //   7	104	1261	java/lang/Exception
    //   436	451	1261	java/lang/Exception
    //   525	536	1261	java/lang/Exception
    //   540	553	1261	java/lang/Exception
    //   553	1164	1261	java/lang/Exception
    //   1225	1258	1261	java/lang/Exception
    //   1425	1454	1261	java/lang/Exception
    //   451	525	1423	java/lang/Exception
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
    appID = paramString1;
    secretKey = paramString2;
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
    paramString = paramString + getParamsWithoutAppID();
    paramString = paramString + "&publisher_user_id=" + getUserID();
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
