package com.tapjoy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
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
  private static Vector<String> dependencyClassesRequired;
  private static String deviceCountryCode;
  private static String deviceID;
  private static String deviceLanguage;
  private static boolean deviceLocation;
  private static String deviceManufacturer;
  private static String deviceModel;
  private static String deviceOSVersion;
  private static int deviceScreenDensity;
  private static float deviceScreenDensityScale;
  private static int deviceScreenLayoutSize;
  private static String deviceType;
  private static long lastTimeStamp;
  private static String libraryVersion;
  private static String macAddress;
  private static String matchingPackageNames = "";
  private static String mobileCountryCode;
  private static String mobileNetworkCode;
  private static PackageManager packageManager;
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
    dependencyClassesRequired = new Vector(Arrays.asList(TapjoyConstants.dependencyClassNames));
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
    deviceScreenDensity = 1;
    deviceScreenDensityScale = 1.0F;
    deviceScreenLayoutSize = 1;
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
      if ((getConnectFlagValue("user_id") != null) && (getConnectFlagValue("user_id").length() > 0))
      {
        TapjoyLog.i("TapjoyConnect", "Setting userID to: " + getConnectFlagValue("user_id"));
        setUserID(getConnectFlagValue("user_id"));
      }
      this.initialized = true;
    }
  }
  
  private void checkForDependency(ActivityInfo paramActivityInfo)
  {
    if (dependencyClassesRequired.contains(paramActivityInfo.name))
    {
      int i = dependencyClassesRequired.indexOf(paramActivityInfo.name);
      Vector localVector;
      try
      {
        Class.forName((String)dependencyClassesRequired.get(i));
        localVector = new Vector();
        if ((paramActivityInfo.configChanges & 0x80) != 128) {
          localVector.add("orientation");
        }
        if ((paramActivityInfo.configChanges & 0x20) != 32) {
          localVector.add("keyboardHidden");
        }
        if ((Build.VERSION.SDK_INT >= 13) && ((paramActivityInfo.configChanges & 0x400) != 1024)) {
          localVector.add("screenSize");
        }
        if (localVector.size() == 0) {
          break label256;
        }
        if (localVector.size() == 1) {
          throw new TapjoyIntegrationException(localVector.toString() + " property is not specified in manifest configChanges for " + (String)dependencyClassesRequired.get(i));
        }
      }
      catch (ClassNotFoundException paramActivityInfo)
      {
        throw new TapjoyIntegrationException("[ClassNotFoundException] Could not find dependency class " + (String)dependencyClassesRequired.get(i));
      }
      throw new TapjoyIntegrationException(localVector.toString() + " properties are not specified in manifest configChanges for " + (String)dependencyClassesRequired.get(i));
      label256:
      if ((Build.VERSION.SDK_INT >= 11) && (paramActivityInfo.name.equals("com.tapjoy.TJAdUnitView")) && ((paramActivityInfo.flags & 0x200) != 512)) {
        throw new TapjoyIntegrationException("'hardwareAccelerated' property not specified in manifest for " + (String)dependencyClassesRequired.get(i));
      }
      dependencyClassesRequired.remove(i);
    }
  }
  
  private void checkManifestForConfigurations()
  {
    for (;;)
    {
      int i;
      try
      {
        if (packageManager == null) {
          break;
        }
        ApplicationInfo localApplicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
        if ((localApplicationInfo != null) && (localApplicationInfo.metaData != null))
        {
          String[] arrayOfString = TapjoyConnectFlag.FLAG_ARRAY;
          int j = arrayOfString.length;
          i = 0;
          if (i < j)
          {
            String str1 = arrayOfString[i];
            String str2 = localApplicationInfo.metaData.getString("tapjoy." + str1);
            if (str2 != null)
            {
              TapjoyLog.i("TapjoyConnect", "Found manifest flag: " + str1 + ", " + str2);
              connectFlags.put(str1, str2);
            }
          }
          else
          {
            TapjoyLog.i("TapjoyConnect", "Metadata successfully loaded");
          }
        }
        else
        {
          TapjoyLog.i("TapjoyConnect", "No metadata present.");
          return;
        }
      }
      catch (Exception localException)
      {
        TapjoyLog.e("TapjoyConnect", "Error reading manifest meta-data: " + localException.toString());
        return;
      }
      i += 1;
    }
  }
  
  private void checkPermissions()
  {
    int j = 0;
    Vector localVector = new Vector();
    String[] arrayOfString = TapjoyConstants.dependencyPermissions;
    int k = arrayOfString.length;
    int i = 0;
    String str;
    while (i < k)
    {
      str = arrayOfString[i];
      if (!isPermissionGranted(str)) {
        localVector.add(str);
      }
      i += 1;
    }
    if (localVector.size() != 0)
    {
      if (localVector.size() == 1) {
        throw new TapjoyIntegrationException("Missing 1 permission in manifest: " + localVector.toString());
      }
      throw new TapjoyIntegrationException("Missing " + localVector.size() + " permissions in manifest: " + localVector.toString());
    }
    localVector = new Vector();
    arrayOfString = TapjoyConstants.optionalPermissions;
    k = arrayOfString.length;
    i = j;
    while (i < k)
    {
      str = arrayOfString[i];
      if (!isPermissionGranted(str)) {
        localVector.add(str);
      }
      i += 1;
    }
    if (localVector.size() != 0)
    {
      if (localVector.size() == 1) {
        TapjoyLog.w("TapjoyConnect", "WARNING -- " + localVector.toString() + " permission was not found in manifest. The exclusion of this permission could cause problems.");
      }
    }
    else {
      return;
    }
    TapjoyLog.w("TapjoyConnect", "WARNING -- " + localVector.toString() + " permissions were not found in manifest. The exclusion of these permissions could cause problems.");
  }
  
  private void checkResourceFileForConfigurations()
  {
    int i = context.getResources().getIdentifier("raw/tapjoy_config", null, context.getPackageName());
    Properties localProperties = new Properties();
    try
    {
      localProperties.load(context.getResources().openRawResource(i));
      parsePropertiesIntoConfigFlags(localProperties);
      return;
    }
    catch (Exception localException)
    {
      TapjoyLog.i("TapjoyConnect", "No raw/tapjoy_config file present.");
    }
  }
  
  private void determineUserID()
  {
    if ((deviceID != null) && (deviceID.length() > 0))
    {
      userID = deviceID;
      return;
    }
    if ((macAddress != null) && (macAddress.length() > 0))
    {
      userID = macAddress;
      return;
    }
    if ((androidID != null) && (androidID.length() > 0))
    {
      userID = androidID;
      return;
    }
    throw new TapjoyException("ERROR -- No valid device identifier");
  }
  
  private static String generateSessionID()
  {
    TapjoyLog.i("TapjoyConnect", "generating sessionID...");
    try
    {
      str = TapjoyUtil.SHA256(System.currentTimeMillis() / 1000L + appID + deviceID);
      TapjoyLog.e("TapjoyConnect", "unable to generate session id: " + localException1.toString());
    }
    catch (Exception localException1)
    {
      try
      {
        lastTimeStamp = System.currentTimeMillis();
        return str;
      }
      catch (Exception localException2)
      {
        String str;
        for (;;) {}
      }
      localException1 = localException1;
      str = null;
    }
    return str;
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
  
  public static String getConnectFlagValue(String paramString)
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
  
  public static String getConnectionSubType()
  {
    try
    {
      Object localObject = (ConnectivityManager)context.getSystemService("connectivity");
      if (localObject != null) {
        localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo().getSubtypeName();
      }
      return "";
    }
    catch (Exception localException1)
    {
      try
      {
        TapjoyLog.i("TapjoyConnect", "connection_sub_type: " + (String)localObject);
        return localObject;
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
      localException1 = localException1;
      localObject = "";
      TapjoyLog.e("TapjoyConnect", "getConnectionSubType error: " + localException1.toString());
      return localObject;
    }
  }
  
  public static String getConnectionType()
  {
    String str1 = "";
    String str3 = str1;
    String str4;
    for (;;)
    {
      try
      {
        ConnectivityManager localConnectivityManager = (ConnectivityManager)context.getSystemService("connectivity");
        str4 = str1;
        if (localConnectivityManager == null) {
          break;
        }
        str3 = str1;
        str4 = str1;
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
          str4 = str1;
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
    return str4;
  }
  
  public static Context getContext()
  {
    return context;
  }
  
  public static String getDeviceID()
  {
    return deviceID;
  }
  
  public static float getDeviceScreenDensityScale()
  {
    return deviceScreenDensityScale;
  }
  
  public static Map<String, String> getGenericURLParams()
  {
    Map localMap = getParamsWithoutAppID();
    TapjoyUtil.safePut(localMap, "app_id", appID, true);
    return localMap;
  }
  
  public static String getHostURL()
  {
    return getConnectFlagValue("TJC_SERVICE_URL");
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
    if ((getConnectFlagValue("sha_2_udid") != null) && (getConnectFlagValue("sha_2_udid").equals("true")))
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
        break label505;
      }
      sessionID = generateSessionID();
    }
    for (;;)
    {
      TapjoyUtil.safePut(localHashMap, "session_id", sessionID, true);
      return localHashMap;
      TapjoyUtil.safePut(localHashMap, "udid", deviceID, true);
      break;
      label505:
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
    boolean bool2 = false;
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
            localObject = packageManager.getInstalledApplications(0).iterator();
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
      boolean bool1 = bool2;
      if (paramString != null)
      {
        bool1 = bool2;
        if (paramString.equals("true")) {
          bool1 = true;
        }
      }
      return bool1;
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
  
  private boolean init()
  {
    packageManager = context.getPackageManager();
    integrationCheck();
    loadConfigurations();
    obtainDeviceInformation();
    if (getConnectFlagValue("TJC_SERVICE_URL") == "") {
      setHostURL("https://ws.tapjoyads.com/");
    }
    if ((getConnectFlagValue("debug_host_url") != null) && (getConnectFlagValue("debug_host_url").length() > 0)) {
      setHostURL(getConnectFlagValue("debug_host_url"));
    }
    Object localObject1 = getConnectFlagValue("TJC_SERVICE_URL");
    redirectDomain = ((String)localObject1).substring(((String)localObject1).indexOf("//") + 2, ((String)localObject1).lastIndexOf("/"));
    Object localObject2 = new StringBuilder("deviceID: ").append(deviceID);
    if ((getConnectFlagValue("debug_device_id") != null) && (getConnectFlagValue("debug_device_id").length() > 0)) {}
    for (localObject1 = " *debug_device_id*";; localObject1 = "")
    {
      TapjoyLog.i("TapjoyConnect", (String)localObject1);
      TapjoyLog.i("TapjoyConnect", "sha2_udid: " + sha2DeviceID);
      localObject1 = getGenericURLParams().entrySet().iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Map.Entry)((Iterator)localObject1).next();
        TapjoyLog.i("TapjoyConnect", (String)((Map.Entry)localObject2).getKey() + ": " + (String)((Map.Entry)localObject2).getValue());
      }
    }
    if (connectFlags != null) {
      logConnectFlags();
    }
    return true;
  }
  
  private void integrationCheck()
  {
    try
    {
      Object localObject = Arrays.asList(packageManager.getPackageInfo(context.getPackageName(), 1).activities);
      if (localObject != null)
      {
        localObject = ((List)localObject).iterator();
        while (((Iterator)localObject).hasNext()) {
          checkForDependency((ActivityInfo)((Iterator)localObject).next());
        }
      }
      if (dependencyClassesRequired.size() == 0) {
        break label177;
      }
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      throw new TapjoyIntegrationException("NameNotFoundException: Could not find package.");
    }
    if (dependencyClassesRequired.size() == 1) {
      throw new TapjoyIntegrationException("Missing " + dependencyClassesRequired.size() + " dependency class in manifest: " + dependencyClassesRequired.toString());
    }
    throw new TapjoyIntegrationException("Missing " + dependencyClassesRequired.size() + " dependency classes in manifest: " + dependencyClassesRequired.toString());
    label177:
    checkPermissions();
    resolveJSBridgeMethods();
  }
  
  private boolean isPermissionGranted(String paramString)
  {
    return packageManager.checkPermission(paramString, context.getPackageName()) == 0;
  }
  
  private void loadConfigurations()
  {
    if (connectFlags == null) {
      connectFlags = new Hashtable();
    }
    if ((getConnectFlagValue("enable_logging") != null) && (getConnectFlagValue("enable_logging").equals("true"))) {
      TapjoyLog.enableLogging(true);
    }
    checkManifestForConfigurations();
    checkResourceFileForConfigurations();
  }
  
  private void logConnectFlags()
  {
    TapjoyLog.i("TapjoyConnect", "Connect Flags:");
    TapjoyLog.i("TapjoyConnect", "--------------------");
    Iterator localIterator = connectFlags.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      TapjoyLog.i("TapjoyConnect", "key: " + (String)localEntry.getKey() + ", value: " + Uri.encode((String)localEntry.getValue()));
      if ((((String)localEntry.getKey()).equals("sha_2_udid")) && (!sdkType.equals("connect")))
      {
        TapjoyLog.w("TapjoyConnect", "WARNING -- only the Connect/Advertiser SDK can support sha_2_udid");
        connectFlags.remove("sha_2_udid");
      }
    }
    TapjoyLog.i("TapjoyConnect", "hostURL: [" + getConnectFlagValue("TJC_SERVICE_URL") + "]");
    TapjoyLog.i("TapjoyConnect", "redirectDomain: [" + redirectDomain + "]");
    TapjoyLog.i("TapjoyConnect", "--------------------");
  }
  
  private void obtainCarrierInformation()
  {
    Object localObject = (TelephonyManager)context.getSystemService("phone");
    if (localObject != null)
    {
      carrierName = ((TelephonyManager)localObject).getNetworkOperatorName();
      carrierCountryCode = ((TelephonyManager)localObject).getNetworkCountryIso();
      if ((((TelephonyManager)localObject).getNetworkOperator() != null) && ((((TelephonyManager)localObject).getNetworkOperator().length() == 5) || (((TelephonyManager)localObject).getNetworkOperator().length() == 6)))
      {
        mobileCountryCode = ((TelephonyManager)localObject).getNetworkOperator().substring(0, 3);
        mobileNetworkCode = ((TelephonyManager)localObject).getNetworkOperator().substring(3);
      }
      if (isPermissionGranted("android.permission.READ_PHONE_STATE")) {
        for (;;)
        {
          int i;
          int j;
          try
          {
            if ((getConnectFlagValue("debug_device_id") != null) && (getConnectFlagValue("debug_device_id").length() > 0))
            {
              deviceID = getConnectFlagValue("debug_device_id");
              TapjoyLog.i("TapjoyConnect", "deviceID: " + deviceID);
              if (deviceID == null)
              {
                TapjoyLog.e("TapjoyConnect", "Device id is null.");
                i = 0;
                TapjoyLog.i("TapjoyConnect", "ANDROID SDK VERSION: " + Build.VERSION.SDK_INT);
                j = i;
                if (Build.VERSION.SDK_INT >= 9)
                {
                  TapjoyLog.i("TapjoyConnect", "TRYING TO GET SERIAL OF 2.3+ DEVICE...");
                  localObject = getSerial();
                  if (i == 0) {
                    deviceID = (String)localObject;
                  }
                  if (deviceID != null) {
                    break label353;
                  }
                  TapjoyLog.e("TapjoyConnect", "SERIAL: Device id is null.");
                  j = i;
                }
                if (j == 0) {
                  break;
                }
                sha2DeviceID = TapjoyUtil.SHA256(deviceID);
              }
            }
            else
            {
              deviceID = ((TelephonyManager)localObject).getDeviceId();
              continue;
            }
            if (deviceID.length() == 0) {
              break label323;
            }
          }
          catch (Exception localException)
          {
            TapjoyLog.e("TapjoyConnect", "Cannot get deviceID. e: " + localException.toString());
            deviceID = null;
            return;
          }
          if ((deviceID.equals("000000000000000")) || (deviceID.equals("0")))
          {
            label323:
            TapjoyLog.e("TapjoyConnect", "Device id is empty or an emulator.");
            i = 0;
          }
          else
          {
            deviceID = deviceID.toLowerCase(Locale.getDefault());
            i = 1;
            continue;
            label353:
            if ((deviceID.length() == 0) || (deviceID.equals("000000000000000")) || (deviceID.equals("0")) || (deviceID.equals("unknown")))
            {
              TapjoyLog.e("TapjoyConnect", "SERIAL: Device id is empty or an emulator.");
              j = i;
            }
            else
            {
              deviceID = deviceID.toLowerCase(Locale.getDefault());
              j = 1;
            }
          }
        }
      }
      TapjoyLog.i("TapjoyConnect", "*** ignore deviceID");
    }
  }
  
  private void obtainDeviceInformation()
  {
    String str = Settings.Secure.getString(context.getContentResolver(), "android_id");
    androidID = str;
    if (str != null) {
      androidID = androidID.toLowerCase();
    }
    try
    {
      appVersion = packageManager.getPackageInfo(context.getPackageName(), 0).versionName;
      deviceType = "android";
      platformName = "android";
      deviceModel = Build.MODEL;
      deviceManufacturer = Build.MANUFACTURER;
      deviceOSVersion = Build.VERSION.RELEASE;
      deviceCountryCode = Locale.getDefault().getCountry();
      deviceLanguage = Locale.getDefault().getLanguage();
      libraryVersion = "9.1.1";
      bridgeVersion = "1.0.4";
      obtainScreenInformation();
      obtainMacAddress();
      obtainCarrierInformation();
      determineUserID();
      setDeviceCapabilityFlags();
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      throw new TapjoyException(localNameNotFoundException.getMessage());
    }
  }
  
  private void obtainMacAddress()
  {
    if (isPermissionGranted("android.permission.ACCESS_WIFI_STATE")) {
      try
      {
        Object localObject = (WifiManager)context.getSystemService("wifi");
        if (localObject != null)
        {
          localObject = ((WifiManager)localObject).getConnectionInfo();
          if (localObject != null)
          {
            localObject = ((WifiInfo)localObject).getMacAddress();
            macAddress = (String)localObject;
            if (localObject != null) {
              macAddress = macAddress.replace(":", "").toLowerCase();
            }
          }
        }
        return;
      }
      catch (Exception localException)
      {
        TapjoyLog.e("TapjoyConnect", "Error getting device mac address: " + localException.toString());
        return;
      }
    }
    TapjoyLog.i("TapjoyConnect", "*** ignore macAddress");
  }
  
  private void obtainScreenInformation()
  {
    try
    {
      if (Build.VERSION.SDK_INT > 3)
      {
        TapjoyDisplayMetricsUtil localTapjoyDisplayMetricsUtil = new TapjoyDisplayMetricsUtil(context);
        deviceScreenDensity = localTapjoyDisplayMetricsUtil.getScreenDensityDPI();
        deviceScreenDensityScale = localTapjoyDisplayMetricsUtil.getScreenDensityScale();
        deviceScreenLayoutSize = localTapjoyDisplayMetricsUtil.getScreenLayoutSize();
      }
      return;
    }
    catch (Exception localException)
    {
      TapjoyLog.e("TapjoyConnect", "Error getting screen density/dimensions/layout: " + localException.toString());
    }
  }
  
  private void parsePropertiesIntoConfigFlags(Properties paramProperties)
  {
    Enumeration localEnumeration = paramProperties.keys();
    while (localEnumeration.hasMoreElements()) {
      try
      {
        String str1 = (String)localEnumeration.nextElement();
        String str2 = (String)paramProperties.get(str1);
        connectFlags.put(str1, str2);
      }
      catch (ClassCastException localClassCastException)
      {
        TapjoyLog.e("TapjoyConnect", "Error parsing configuration properties in tapjoy_config.txt");
      }
    }
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
  
  /* Error */
  private void resolveJSBridgeMethods()
  {
    // Byte code:
    //   0: ldc_w 1108
    //   3: invokestatic 326	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   6: astore_1
    //   7: aload_1
    //   8: ldc_w 1110
    //   11: iconst_0
    //   12: anewarray 322	java/lang/Class
    //   15: invokevirtual 1114	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   18: pop
    //   19: return
    //   20: astore_1
    //   21: new 349	com/tapjoy/TapjoyIntegrationException
    //   24: dup
    //   25: ldc_w 1116
    //   28: invokespecial 354	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   31: athrow
    //   32: astore_1
    //   33: new 349	com/tapjoy/TapjoyIntegrationException
    //   36: dup
    //   37: ldc_w 1118
    //   40: invokespecial 354	com/tapjoy/TapjoyIntegrationException:<init>	(Ljava/lang/String;)V
    //   43: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	44	0	this	TapjoyConnectCore
    //   6	2	1	localClass	Class
    //   20	1	1	localClassNotFoundException	ClassNotFoundException
    //   32	1	1	localNoSuchMethodException	NoSuchMethodException
    // Exception table:
    //   from	to	target	type
    //   0	7	20	java/lang/ClassNotFoundException
    //   7	19	32	java/lang/NoSuchMethodException
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
  
  private void setDeviceCapabilityFlags()
  {
    try
    {
      deviceLocation = detectCapability("android.hardware.location", "android.permission.ACCESS_FINE_LOCATION");
    }
    catch (Exception localException1)
    {
      try
      {
        for (;;)
        {
          shareFacebook = detectSharingApplication("com.facebook.");
          shareTwitter = detectSharingApplication("com.twitter.");
          shareGooglePlus = detectSharingApplication("com.google.android.apps.plus");
          shareLinkedIn = detectSharingApplication("com.linkedin.");
          if ((getConnectFlagValue("store_name") != null) && (getConnectFlagValue("store_name").length() > 0))
          {
            storeName = getConnectFlagValue("store_name");
            if (!new ArrayList(Arrays.asList(TapjoyConnectFlag.STORE_ARRAY)).contains(storeName)) {
              TapjoyLog.w("TapjoyConnect", "Warning -- undefined STORE_NAME: " + storeName);
            }
          }
          try
          {
            storeView = detectStore(storeName);
            return;
          }
          catch (Exception localException3)
          {
            TapjoyLog.e("TapjoyConnect", "Error trying to detect store intent on devicee: " + localException3.toString());
          }
          localException1 = localException1;
          TapjoyLog.e("TapjoyConnect", "Error trying to detect capabilities on devicee: " + localException1.toString());
        }
      }
      catch (Exception localException2)
      {
        for (;;)
        {
          TapjoyLog.e("TapjoyConnect", "Error trying to detect sharing applications installed on devicee: " + localException2.toString());
        }
      }
    }
  }
  
  public static void setHostURL(String paramString)
  {
    String str = paramString;
    if (!paramString.endsWith("/")) {
      str = paramString + "/";
    }
    connectFlags.put("TJC_SERVICE_URL", str);
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
    Iterator localIterator = packageManager.getInstalledApplications(0).iterator();
    while (localIterator.hasNext()) {
      if (((ApplicationInfo)localIterator.next()).packageName.startsWith(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  protected boolean detectCapability(String paramString1, String paramString2)
  {
    FeatureInfo[] arrayOfFeatureInfo = packageManager.getSystemAvailableFeatures();
    int j = arrayOfFeatureInfo.length;
    int i = 0;
    while (i < j)
    {
      if (arrayOfFeatureInfo[i].name.matches(paramString1))
      {
        if (paramString2 == null) {}
        while (packageManager.checkPermission(paramString2, context.getPackageName()) == 0) {
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
    Object localObject = new Intent("android.intent.action.SEND");
    ((Intent)localObject).setType("text/plain");
    localObject = packageManager.queryIntentActivities((Intent)localObject, 0).iterator();
    while (((Iterator)localObject).hasNext()) {
      if (((ResolveInfo)((Iterator)localObject).next()).activityInfo.packageName.startsWith(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  protected boolean detectStore(String paramString)
  {
    boolean bool = false;
    Intent localIntent = new Intent("android.intent.action.VIEW");
    if (paramString.length() <= 0)
    {
      localIntent.setData(Uri.parse("market://details"));
      if (packageManager.queryIntentActivities(localIntent, 0).size() > 0) {
        bool = true;
      }
    }
    do
    {
      return bool;
      if (paramString.equals("gfan")) {
        return detectApplication("com.mappn.gfan");
      }
    } while (!paramString.equals("skt"));
    return detectApplication("com.skt.skaf.TSCINSTALL");
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
  
  /* Error */
  public String getSerial()
  {
    // Byte code:
    //   0: ldc_w 1316
    //   3: invokestatic 326	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   6: ldc_w 1318
    //   9: invokevirtual 1322	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   12: astore_1
    //   13: aload_1
    //   14: invokevirtual 1327	java/lang/reflect/Field:isAccessible	()Z
    //   17: ifne +8 -> 25
    //   20: aload_1
    //   21: iconst_1
    //   22: invokevirtual 1330	java/lang/reflect/Field:setAccessible	(Z)V
    //   25: aload_1
    //   26: ldc_w 993
    //   29: invokevirtual 1331	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   32: invokevirtual 1332	java/lang/Object:toString	()Ljava/lang/String;
    //   35: astore_1
    //   36: ldc 8
    //   38: new 223	java/lang/StringBuilder
    //   41: dup
    //   42: ldc_w 1334
    //   45: invokespecial 228	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   48: aload_1
    //   49: invokevirtual 269	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: invokevirtual 240	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   55: invokestatic 246	com/tapjoy/TapjoyLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   58: aload_1
    //   59: areturn
    //   60: astore_2
    //   61: aconst_null
    //   62: astore_1
    //   63: ldc 8
    //   65: aload_2
    //   66: invokevirtual 422	java/lang/Exception:toString	()Ljava/lang/String;
    //   69: invokestatic 425	com/tapjoy/TapjoyLog:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   72: aload_1
    //   73: areturn
    //   74: astore_2
    //   75: goto -12 -> 63
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	78	0	this	TapjoyConnectCore
    //   12	61	1	localObject	Object
    //   60	6	2	localException1	Exception
    //   74	1	2	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   0	25	60	java/lang/Exception
    //   25	36	60	java/lang/Exception
    //   36	58	74	java/lang/Exception
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
