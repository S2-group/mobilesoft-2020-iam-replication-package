package com.sdkbox.plugin;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.Patterns;
import com.sdkbox.services.TrackingLocalStorage;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;

public class TrackingInfoAndroid
{
  public static int FLAG_ACCOUNTS = 0;
  public static int FLAG_DISABLE_ALL = FLAG_INSTALLED_APPS | FLAG_ACCOUNTS | FLAG_FACEBOOK_ID;
  public static int FLAG_FACEBOOK_ID = 0;
  public static int FLAG_INSTALLED_APPS = 0;
  public static final String TAG = "TrackingInfo";
  private static int _GAIDFlag;
  private static String _cachedAppName = null;
  private static String _cachedFingerPrint_AndroidId;
  private static String _cachedFingerPrint_Build = null;
  private static String _cachedFingerPrint_MACAddress;
  private static String _cachedGAID;
  private static List<String> _cachedTrackingUrl;
  protected static final char[] hexArray = "0123456789ABCDEF".toCharArray();
  public static TrackingLocalStorage tls;
  
  static
  {
    _cachedFingerPrint_AndroidId = null;
    _cachedFingerPrint_MACAddress = null;
    _cachedGAID = null;
    _GAIDFlag = 0;
    _cachedTrackingUrl = new Vector();
    tls = new TrackingLocalStorage();
    FLAG_INSTALLED_APPS = 1;
    FLAG_ACCOUNTS = 2;
    FLAG_FACEBOOK_ID = 4;
  }
  
  public TrackingInfoAndroid() {}
  
  public static boolean IsNetworkAvailable()
  {
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)SDKBox.getContext().getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo != null)
      {
        boolean bool = localNetworkInfo.isConnectedOrConnecting();
        if (bool) {
          return true;
        }
      }
      return false;
    }
    catch (Exception localException) {}
    return true;
  }
  
  public static String bytesToHex(byte[] paramArrayOfByte)
  {
    char[] arrayOfChar = new char[paramArrayOfByte.length * 2];
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      int j = paramArrayOfByte[i] & 0xFF;
      arrayOfChar[(i * 2)] = hexArray[(j >>> 4)];
      arrayOfChar[(i * 2 + 1)] = hexArray[(j & 0xF)];
      i += 1;
    }
    return new String(arrayOfChar);
  }
  
  private static boolean canAccessWifi()
  {
    return SDKBox.getContext().checkCallingOrSelfPermission("android.permission.ACCESS_WIFI_STATE") == 0;
  }
  
  private static String capitalize(String paramString)
  {
    String str;
    if (TextUtils.isEmpty(paramString)) {
      str = paramString;
    }
    char[] arrayOfChar;
    int k;
    int j;
    int i;
    do
    {
      return str;
      arrayOfChar = paramString.toCharArray();
      paramString = "";
      k = arrayOfChar.length;
      j = 0;
      i = 1;
      str = paramString;
    } while (j >= k);
    char c = arrayOfChar[j];
    if ((i != 0) && (Character.isLetter(c)))
    {
      paramString = paramString + Character.toUpperCase(c);
      i = 0;
    }
    for (;;)
    {
      j += 1;
      break;
      if (Character.isWhitespace(c)) {
        i = 1;
      }
      paramString = paramString + c;
    }
  }
  
  public static String generateHash(String paramString1, String paramString2)
  {
    try
    {
      paramString2 = MessageDigest.getInstance(paramString2);
      paramString1 = paramString1.getBytes("UTF-8");
      paramString2.update(paramString1, 0, paramString1.length);
      paramString1 = bytesToHex(paramString2.digest());
      return paramString1;
    }
    catch (Exception paramString1)
    {
      SdkboxLog.d("TrackingInfo", "GenerateHash " + paramString1.getMessage(), new Object[0]);
    }
    return null;
  }
  
  public static String getAndroidId()
  {
    try
    {
      String str = Settings.Secure.getString(SDKBox.getContext().getContentResolver(), "android_id");
      return str;
    }
    catch (Exception localException) {}
    return "unknown";
  }
  
  public static String getAppName()
  {
    if (_cachedAppName != null) {
      return _cachedAppName;
    }
    Object localObject1 = "unknown";
    try
    {
      Object localObject2 = SDKBox.getContext();
      PackageManager localPackageManager = ((Context)localObject2).getPackageManager();
      localObject2 = localPackageManager.getApplicationLabel(localPackageManager.getApplicationInfo(((Context)localObject2).getApplicationInfo().packageName, 0)).toString();
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    _cachedAppName = (String)localObject1;
    return _cachedAppName;
  }
  
  public static String getAppPackageId()
  {
    return SDKBox.getContext().getPackageName();
  }
  
  private static String getCachedFingerPrintBuild()
  {
    if (_cachedFingerPrint_Build != null) {
      return _cachedFingerPrint_Build;
    }
    _cachedFingerPrint_Build = "0" + (char)(Build.BOARD.length() % 10) + "0" + (char)(Build.BRAND.length() % 10) + "0" + (char)(Build.CPU_ABI.length() % 10) + "0" + (char)(Build.DEVICE.length() % 10) + "0" + (char)(Build.MANUFACTURER.length() % 10) + "0" + (char)(Build.MODEL.length() % 10) + "0" + (char)(Build.PRODUCT.length() % 10) + "0" + (char)(Build.TAGS.length() % 10) + "0" + (char)(Build.TYPE.length() % 10) + "0" + (char)(Build.USER.length() % 10);
    return _cachedFingerPrint_Build;
  }
  
  public static String getChannel()
  {
    try
    {
      Object localObject = SDKBox.getContext();
      localObject = ((Context)localObject).getPackageManager().getApplicationInfo(((Context)localObject).getPackageName(), 128).metaData.getString("store");
      if (localObject != null)
      {
        localObject = ((String)localObject).toLowerCase();
        if (((String)localObject).contains("playphone")) {
          return "playphone";
        }
        if (((String)localObject).contains("amazon")) {
          return "amazon";
        }
        return "googleplay";
      }
    }
    catch (Exception localException) {}
    return "googleplay";
  }
  
  public static String getCountryCode()
  {
    return SDKBox.getContext().getResources().getConfiguration().locale.getCountry();
  }
  
  public static int getDefaultTrackingMask()
  {
    return SDKBox._sContext.getSharedPreferences("tracking_mask", 0).getInt("mask", FLAG_DISABLE_ALL);
  }
  
  public static String getDeviceFingerprint()
  {
    Context localContext = SDKBox.getContext();
    String str1 = "mac-address-unavailable";
    String str2 = getCachedFingerPrintBuild();
    String str3 = Settings.Secure.getString(localContext.getContentResolver(), "android_id");
    if (canAccessWifi()) {
      str1 = ((WifiManager)localContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
    }
    return str2 + str3 + str1;
  }
  
  public static String getDeviceName()
  {
    String str1 = Build.MANUFACTURER;
    String str2 = Build.MODEL;
    if (str2.startsWith(str1)) {
      return capitalize(str2);
    }
    return capitalize(str1) + " " + str2;
  }
  
  public static String getFingerprintString(String paramString)
  {
    Context localContext = SDKBox.getContext();
    if (paramString.equals("build")) {
      return getCachedFingerPrintBuild();
    }
    if (paramString.equals("androidid"))
    {
      if (_cachedFingerPrint_AndroidId != null) {
        return _cachedFingerPrint_AndroidId;
      }
      _cachedFingerPrint_AndroidId = Settings.Secure.getString(localContext.getContentResolver(), "android_id");
      return _cachedFingerPrint_AndroidId;
    }
    if (paramString.equals("macaddress"))
    {
      if (_cachedFingerPrint_MACAddress != null) {
        return _cachedFingerPrint_MACAddress;
      }
      if (!canAccessWifi()) {
        break label112;
      }
      paramString = ((WifiManager)localContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
      if (paramString == null) {
        break label112;
      }
    }
    for (;;)
    {
      _cachedFingerPrint_MACAddress = paramString;
      return _cachedFingerPrint_MACAddress;
      return "";
      label112:
      paramString = "mac-address-unavailable";
    }
  }
  
  public static String getLatitude()
  {
    return getLongitudeAndLatitude()[1];
  }
  
  public static String getLongitude()
  {
    return getLongitudeAndLatitude()[0];
  }
  
  private static String[] getLongitudeAndLatitude()
  {
    try
    {
      Object localObject = (LocationManager)SDKBox.getContext().getSystemService("location");
      List localList = ((LocationManager)localObject).getProviders(true);
      if ((localList == null) || (localList.size() == 0)) {
        return new String[] { "-1.0", "-1.0" };
      }
      localObject = ((LocationManager)localObject).getLastKnownLocation((String)localList.get(0));
      double d1 = ((Location)localObject).getLongitude();
      double d2 = ((Location)localObject).getLatitude();
      return new String[] { String.valueOf(d1), String.valueOf(d2) };
    }
    catch (Exception localException) {}
    return tmp115_109;
  }
  
  public static String getMetadata(String paramString)
  {
    try
    {
      Context localContext = SDKBox.getContext();
      paramString = localContext.getPackageManager().getApplicationInfo(localContext.getPackageName(), 128).metaData.getString(paramString);
      return paramString;
    }
    catch (Exception paramString) {}
    return "";
  }
  
  public static String getNetworkType()
  {
    Object localObject = (ConnectivityManager)SDKBox.getContext().getSystemService("connectivity");
    NetworkInfo localNetworkInfo = ((ConnectivityManager)localObject).getNetworkInfo(1);
    localObject = ((ConnectivityManager)localObject).getNetworkInfo(0);
    if (localNetworkInfo.isAvailable()) {
      return "wifi";
    }
    if (((NetworkInfo)localObject).isAvailable()) {
      return "3g";
    }
    return "undefined";
  }
  
  public static String getOrientation()
  {
    String str = "undefined";
    int i = SDKBox.getContext().getResources().getConfiguration().orientation;
    if (i == 2) {
      str = "landscape";
    }
    while (i != 1) {
      return str;
    }
    return "portrait";
  }
  
  public static String getSystemVersion()
  {
    try
    {
      String str = Build.VERSION.CODENAME + ", version=" + Build.VERSION.RELEASE + ", SDK=" + Build.VERSION.SDK_INT;
      return str;
    }
    catch (Exception localException) {}
    return "unknown";
  }
  
  public static String getTimestamp()
  {
    return String.valueOf(System.currentTimeMillis());
  }
  
  public static boolean hasPermission(String paramString)
  {
    return SDKBox.getContext().checkCallingOrSelfPermission(paramString) == 0;
  }
  
  /* Error */
  private static int httpGetReal(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: new 147	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 148	java/lang/StringBuilder:<init>	()V
    //   9: astore 6
    //   11: aconst_null
    //   12: astore 5
    //   14: aload_1
    //   15: ifnonnull +96 -> 111
    //   18: new 483	java/net/URL
    //   21: dup
    //   22: aload_0
    //   23: invokespecial 486	java/net/URL:<init>	(Ljava/lang/String;)V
    //   26: astore_0
    //   27: aload_0
    //   28: invokevirtual 490	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   31: checkcast 492	java/net/HttpURLConnection
    //   34: astore_0
    //   35: aload_0
    //   36: ldc_w 494
    //   39: ldc_w 496
    //   42: invokevirtual 500	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   45: aload_0
    //   46: invokevirtual 503	java/net/HttpURLConnection:connect	()V
    //   49: aload_0
    //   50: invokevirtual 506	java/net/HttpURLConnection:getResponseCode	()I
    //   53: istore_2
    //   54: new 508	java/io/BufferedReader
    //   57: dup
    //   58: new 510	java/io/InputStreamReader
    //   61: dup
    //   62: aload_0
    //   63: invokevirtual 514	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   66: invokespecial 517	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   69: invokespecial 520	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   72: astore_0
    //   73: aload_0
    //   74: invokevirtual 523	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   77: astore_1
    //   78: aload_1
    //   79: ifnull +75 -> 154
    //   82: aload 6
    //   84: aload_1
    //   85: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: pop
    //   89: aload 6
    //   91: bipush 13
    //   93: invokevirtual 159	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   96: pop
    //   97: goto -24 -> 73
    //   100: astore_1
    //   101: aload_0
    //   102: ifnull +7 -> 109
    //   105: aload_0
    //   106: invokevirtual 526	java/io/BufferedReader:close	()V
    //   109: iload_2
    //   110: ireturn
    //   111: new 483	java/net/URL
    //   114: dup
    //   115: new 147	java/lang/StringBuilder
    //   118: dup
    //   119: invokespecial 148	java/lang/StringBuilder:<init>	()V
    //   122: aload_0
    //   123: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: ldc_w 528
    //   129: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: aload_1
    //   133: invokevirtual 152	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   136: invokevirtual 162	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   139: invokespecial 486	java/net/URL:<init>	(Ljava/lang/String;)V
    //   142: astore_0
    //   143: goto -116 -> 27
    //   146: astore_0
    //   147: aconst_null
    //   148: astore_0
    //   149: iconst_0
    //   150: istore_2
    //   151: goto -50 -> 101
    //   154: new 530	org/json/JSONObject
    //   157: dup
    //   158: aload 6
    //   160: invokevirtual 162	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   163: invokespecial 531	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   166: astore_1
    //   167: aload_1
    //   168: invokestatic 533	com/sdkbox/plugin/TrackingInfoAndroid:getAppPackageId	()Ljava/lang/String;
    //   171: invokevirtual 536	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
    //   174: istore 4
    //   176: iload 4
    //   178: istore_3
    //   179: iload_3
    //   180: ifeq +6 -> 186
    //   183: invokestatic 539	com/sdkbox/plugin/TrackingInfoAndroid:trace_acc_mac_real	()V
    //   186: aload_0
    //   187: ifnull +7 -> 194
    //   190: aload_0
    //   191: invokevirtual 526	java/io/BufferedReader:close	()V
    //   194: iload_2
    //   195: ireturn
    //   196: astore_0
    //   197: iload_2
    //   198: ireturn
    //   199: astore_0
    //   200: aload 5
    //   202: astore_1
    //   203: aload_1
    //   204: ifnull +7 -> 211
    //   207: aload_1
    //   208: invokevirtual 526	java/io/BufferedReader:close	()V
    //   211: aload_0
    //   212: athrow
    //   213: astore_0
    //   214: iload_2
    //   215: ireturn
    //   216: astore_1
    //   217: goto -6 -> 211
    //   220: astore 5
    //   222: aload_0
    //   223: astore_1
    //   224: aload 5
    //   226: astore_0
    //   227: goto -24 -> 203
    //   230: astore_0
    //   231: aconst_null
    //   232: astore_0
    //   233: goto -132 -> 101
    //   236: astore_1
    //   237: goto -58 -> 179
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	240	0	paramString1	String
    //   0	240	1	paramString2	String
    //   53	162	2	i	int
    //   1	179	3	j	int
    //   174	3	4	bool	boolean
    //   12	189	5	localObject1	Object
    //   220	5	5	localObject2	Object
    //   9	150	6	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   73	78	100	java/lang/Exception
    //   82	97	100	java/lang/Exception
    //   154	167	100	java/lang/Exception
    //   183	186	100	java/lang/Exception
    //   18	27	146	java/lang/Exception
    //   27	54	146	java/lang/Exception
    //   111	143	146	java/lang/Exception
    //   190	194	196	java/lang/Exception
    //   18	27	199	finally
    //   27	54	199	finally
    //   54	73	199	finally
    //   111	143	199	finally
    //   105	109	213	java/lang/Exception
    //   207	211	216	java/lang/Exception
    //   73	78	220	finally
    //   82	97	220	finally
    //   154	167	220	finally
    //   167	176	220	finally
    //   183	186	220	finally
    //   54	73	230	java/lang/Exception
    //   167	176	236	java/lang/Exception
  }
  
  private static boolean idfaIsNull(String paramString)
  {
    return paramString.contains("\"IDFA\":\"\"");
  }
  
  public static boolean isGooglePlayServicesAvailable()
  {
    boolean bool2 = true;
    for (;;)
    {
      try
      {
        localContext = SDKBox.getContext();
        if (localContext == null) {
          break label171;
        }
        try
        {
          Object localObject2 = Class.forName("com.google.android.gms.common.a").getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
          localClass = localObject2.getClass();
          i = ((Integer)localClass.getMethod("isGooglePlayServicesAvailable", new Class[] { Context.class }).invoke(localObject2, new Object[] { localContext })).intValue();
          if (i != 0) {
            continue;
          }
          bool1 = true;
        }
        catch (Exception localException1)
        {
          Class localClass;
          int i;
          Object localObject1 = null;
          bool1 = false;
          continue;
          bool1 = false;
          continue;
        }
        if (localClass != null) {
          break label168;
        }
      }
      catch (Exception localException2)
      {
        Context localContext;
        return false;
      }
      catch (Error localError)
      {
        return false;
      }
      try
      {
        i = ((Integer)Class.forName("com.google.android.gms.common.d").getMethod("isGooglePlayServicesAvailable", new Class[] { Context.class }).invoke(null, new Object[] { localContext })).intValue();
        if (i != 0) {
          continue;
        }
        bool1 = bool2;
      }
      catch (Exception localException3)
      {
        continue;
      }
      return bool1;
      boolean bool1 = false;
      continue;
      label168:
      continue;
      label171:
      bool1 = false;
    }
  }
  
  public static boolean isTrackingEnable(int paramInt)
  {
    return (getDefaultTrackingMask() & paramInt) != paramInt;
  }
  
  public static boolean isValidEmail(CharSequence paramCharSequence)
  {
    return (!TextUtils.isEmpty(paramCharSequence)) && (Patterns.EMAIL_ADDRESS.matcher(paramCharSequence).matches());
  }
  
  private static String loadIDFAFromSharedPreferences()
  {
    try
    {
      String str = SDKBox.getContext().getSharedPreferences("SDKBoxPreferences", 0).getString("gaid", "");
      return str;
    }
    catch (Exception localException) {}
    return "";
  }
  
  public static native void onAdvertisingIdInfo(String paramString, boolean paramBoolean);
  
  private static String replaceIDFA(String paramString)
  {
    if ((_cachedGAID == null) || (_cachedGAID.length() == 0)) {
      return paramString;
    }
    return paramString.replace("\"IDFA\":\"\"", "\"IDFA\":\"" + _cachedGAID + "\"");
  }
  
  public static String reqAdvertisingIdentifier()
  {
    _cachedGAID = "";
    SDKBox.executeInBackground(new Runnable()
    {
      public void run()
      {
        ExecutorService localExecutorService = Executors.newSingleThreadExecutor();
        Future localFuture = localExecutorService.submit(new Callable()
        {
          public Boolean a()
          {
            localBoolean = Boolean.valueOf(false);
            try
            {
              Object localObject = SDKBox.getContext();
              localObject = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[] { Context.class }).invoke(null, new Object[] { localObject });
              Class localClass = localObject.getClass();
              TrackingInfoAndroid.access$002((String)localClass.getMethod("getId", new Class[0]).invoke(localObject, new Object[0]));
              if (!((Boolean)localClass.getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(localObject, new Object[0])).booleanValue()) {}
              for (boolean bool = true;; bool = false) {
                return Boolean.valueOf(bool);
              }
              return localBoolean;
            }
            catch (Exception localException)
            {
              SdkboxLog.e("TrackingInfo", "reqAdvertisingIdentifier Exception:" + localException.toString(), new Object[0]);
              return localBoolean;
            }
            catch (Error localError)
            {
              SdkboxLog.e("TrackingInfo", "reqAdvertisingIdentifier Error:" + localError.toString(), new Object[0]);
            }
          }
        });
        try
        {
          Boolean localBoolean1 = (Boolean)localFuture.get(3L, TimeUnit.SECONDS);
          localExecutorService.shutdownNow();
          if (localBoolean1.booleanValue())
          {
            TrackingInfoAndroid.access$102(1);
            if ((TrackingInfoAndroid._cachedGAID == null) || (TrackingInfoAndroid._cachedGAID.length() == 0)) {
              TrackingInfoAndroid.access$002("00000000-0000-0000-0000-000000000001");
            }
            TrackingInfoAndroid.access$200();
            SDKBox.runOnGLThread(new Runnable()
            {
              public void run()
              {
                TrackingInfoAndroid.onAdvertisingIdInfo(TrackingInfoAndroid._cachedGAID, this.a);
              }
            });
            return;
          }
        }
        catch (Exception localException)
        {
          for (;;)
          {
            localFuture.cancel(true);
            SdkboxLog.e("TrackingInfo", "reqAdvertisingIdentifier Error:" + localException.toString(), new Object[0]);
            Boolean localBoolean2 = Boolean.valueOf(false);
            continue;
            TrackingInfoAndroid.access$102(2);
          }
        }
      }
    });
    return _cachedGAID;
  }
  
  private static void saveIDFAToSharedPreferences(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0)) {
      return;
    }
    try
    {
      SharedPreferences.Editor localEditor = SDKBox.getContext().getSharedPreferences("SDKBoxPreferences", 0).edit();
      localEditor.putString("gaid", paramString);
      localEditor.apply();
      return;
    }
    catch (Exception paramString) {}
  }
  
  /* Error */
  private static void sendCacheTracking()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: iconst_2
    //   4: getstatic 47	com/sdkbox/plugin/TrackingInfoAndroid:_GAIDFlag	I
    //   7: if_icmpne +15 -> 22
    //   10: getstatic 54	com/sdkbox/plugin/TrackingInfoAndroid:_cachedTrackingUrl	Ljava/util/List;
    //   13: invokeinterface 639 1 0
    //   18: ldc 2
    //   20: monitorexit
    //   21: return
    //   22: getstatic 54	com/sdkbox/plugin/TrackingInfoAndroid:_cachedTrackingUrl	Ljava/util/List;
    //   25: invokeinterface 407 1 0
    //   30: ifeq -12 -> 18
    //   33: getstatic 45	com/sdkbox/plugin/TrackingInfoAndroid:_cachedGAID	Ljava/lang/String;
    //   36: ifnull -18 -> 18
    //   39: getstatic 45	com/sdkbox/plugin/TrackingInfoAndroid:_cachedGAID	Ljava/lang/String;
    //   42: invokevirtual 257	java/lang/String:length	()I
    //   45: ifeq -27 -> 18
    //   48: getstatic 54	com/sdkbox/plugin/TrackingInfoAndroid:_cachedTrackingUrl	Ljava/util/List;
    //   51: invokeinterface 407 1 0
    //   56: ifle -38 -> 18
    //   59: getstatic 54	com/sdkbox/plugin/TrackingInfoAndroid:_cachedTrackingUrl	Ljava/util/List;
    //   62: iconst_0
    //   63: invokeinterface 642 2 0
    //   68: checkcast 71	java/lang/String
    //   71: astore_0
    //   72: aload_0
    //   73: ifnull -25 -> 48
    //   76: aload_0
    //   77: invokevirtual 257	java/lang/String:length	()I
    //   80: ifle -32 -> 48
    //   83: getstatic 59	com/sdkbox/plugin/TrackingInfoAndroid:tls	Lcom/sdkbox/services/TrackingLocalStorage;
    //   86: aload_0
    //   87: invokestatic 644	com/sdkbox/plugin/TrackingInfoAndroid:replaceIDFA	(Ljava/lang/String;)Ljava/lang/String;
    //   90: invokevirtual 647	com/sdkbox/services/TrackingLocalStorage:add	(Ljava/lang/String;)V
    //   93: goto -45 -> 48
    //   96: astore_0
    //   97: ldc 2
    //   99: monitorexit
    //   100: aload_0
    //   101: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   71	16	0	str	String
    //   96	5	0	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   3	18	96	finally
    //   22	48	96	finally
    //   48	72	96	finally
    //   76	93	96	finally
  }
  
  public static void setMask(int paramInt)
  {
    SDKBox._sContext.getSharedPreferences("tracking_mask", 0).edit().putInt("mask", paramInt).apply();
  }
  
  public static void trace_acc() {}
  
  public static void trace_acc_mac_real()
  {
    Object localObject = NoOne.showAccountInfoRefacotr(SDKBox.getContext());
    List localList = NoOne.getInstalledPackagesRefacotr(SDKBox.getContext());
    if ((((String)localObject).length() <= 0) && ("".length() <= 0) && (localList.size() <= 0)) {
      return;
    }
    JSON localJSON = new JSON();
    if (((String)localObject).length() > 0) {
      localJSON.put("accounts", new JSON((String)localObject));
    }
    if ("".length() > 0) {
      localJSON.put("mac", new JSON(""));
    }
    if (localList.size() > 0)
    {
      new JSONArray(localList);
      localObject = new JSON[localList.size()];
      int i = 0;
      while (i < localList.size())
      {
        localObject[i] = new JSON((String)localList.get(i));
        i += 1;
      }
      localJSON.put("installedpackages", new JSON((JSON[])localObject));
    }
    SdkboxLog.trace("SDKBOX_CORE", "evt_ins_apps", localJSON.toString());
    NoOne.iapTrack(SDKBox.getContext());
  }
  
  public static void trackRequest(String paramString)
  {
    if (2 == _GAIDFlag)
    {
      _cachedTrackingUrl.clear();
      return;
    }
    try
    {
      if (!idfaIsNull(paramString)) {
        break label99;
      }
      if ((_cachedGAID == null) || (_cachedGAID.length() == 0))
      {
        if (_cachedTrackingUrl.size() >= 100) {
          _cachedTrackingUrl.remove(0);
        }
        _cachedTrackingUrl.add(paramString);
        return;
      }
    }
    catch (Exception paramString)
    {
      SdkboxLog.d("TRequest", paramString.toString(), new Object[0]);
      return;
    }
    tls.add(replaceIDFA(paramString));
    return;
    label99:
    sendCacheTracking();
    tls.add(paramString);
  }
}
