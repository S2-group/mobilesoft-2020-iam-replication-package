package com.umeng.mobclickcpp;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import javax.microedition.khronos.opengles.GL10;

public class DeviceConfig
{
  public static final int DEFAULT_TIMEZONE = 8;
  protected static final String LOG_TAG = DeviceConfig.class.getName();
  private static final String MOBILE_NETWORK = "2G/3G";
  protected static final String UNKNOW = "Unknown";
  private static final String WIFI = "Wi-Fi";
  
  public DeviceConfig() {}
  
  public static boolean checkPermission(Context paramContext, String paramString)
  {
    return paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName()) == 0;
  }
  
  public static String getAppLabel(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getApplicationInfo(paramContext.getPackageName(), 0);
      if (paramContext != null)
      {
        paramContext = localPackageManager.getApplicationLabel(paramContext);
        return (String)paramContext;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext = null;
        continue;
        paramContext = "";
      }
    }
  }
  
  public static String getAppVersionCode(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return String.valueOf(i);
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return "Unknown";
  }
  
  public static String getAppVersionName(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return "Unknown";
  }
  
  public static String getAppkey(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      if (paramContext != null)
      {
        paramContext = paramContext.metaData.getString("UMENG_APPKEY");
        if (paramContext != null) {
          return paramContext.trim();
        }
        Log.e(LOG_TAG, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.");
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        Log.e(LOG_TAG, "Could not read UMENG_APPKEY meta-data from AndroidManifest.xml.", paramContext);
      }
    }
    return null;
  }
  
  public static String getApplicationLable(Context paramContext)
  {
    return paramContext.getPackageManager().getApplicationLabel(paramContext.getApplicationInfo()).toString();
  }
  
  /* Error */
  public static String getCPU()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_0
    //   2: aconst_null
    //   3: astore_3
    //   4: aconst_null
    //   5: astore 5
    //   7: aconst_null
    //   8: astore 4
    //   10: aconst_null
    //   11: astore_2
    //   12: aconst_null
    //   13: astore_1
    //   14: new 128	java/io/FileReader
    //   17: dup
    //   18: ldc -126
    //   20: invokespecial 133	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   23: astore 6
    //   25: aload 6
    //   27: ifnull +122 -> 149
    //   30: aload 5
    //   32: astore_1
    //   33: new 135	java/io/BufferedReader
    //   36: dup
    //   37: aload 6
    //   39: sipush 1024
    //   42: invokespecial 138	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   45: astore_3
    //   46: aload 4
    //   48: astore_0
    //   49: aload_2
    //   50: astore_1
    //   51: aload_3
    //   52: invokevirtual 141	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   55: astore_2
    //   56: aload_2
    //   57: astore_0
    //   58: aload_2
    //   59: astore_1
    //   60: aload_3
    //   61: invokevirtual 144	java/io/BufferedReader:close	()V
    //   64: aload_2
    //   65: astore_0
    //   66: aload_2
    //   67: astore_1
    //   68: aload 6
    //   70: invokevirtual 145	java/io/FileReader:close	()V
    //   73: aload_2
    //   74: astore_0
    //   75: aload_0
    //   76: astore_1
    //   77: aload_0
    //   78: ifnull +16 -> 94
    //   81: aload_0
    //   82: aload_0
    //   83: bipush 58
    //   85: invokevirtual 149	java/lang/String:indexOf	(I)I
    //   88: iconst_1
    //   89: iadd
    //   90: invokevirtual 152	java/lang/String:substring	(I)Ljava/lang/String;
    //   93: astore_1
    //   94: aload_1
    //   95: invokevirtual 102	java/lang/String:trim	()Ljava/lang/String;
    //   98: areturn
    //   99: astore_2
    //   100: aload_0
    //   101: astore_1
    //   102: getstatic 28	com/umeng/mobclickcpp/DeviceConfig:LOG_TAG	Ljava/lang/String;
    //   105: ldc -102
    //   107: aload_2
    //   108: invokestatic 112	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   111: pop
    //   112: goto -37 -> 75
    //   115: astore_1
    //   116: aload_3
    //   117: astore_0
    //   118: getstatic 28	com/umeng/mobclickcpp/DeviceConfig:LOG_TAG	Ljava/lang/String;
    //   121: ldc -100
    //   123: aload_1
    //   124: invokestatic 112	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   127: pop
    //   128: goto -53 -> 75
    //   131: astore_2
    //   132: aload_1
    //   133: astore_0
    //   134: aload_2
    //   135: astore_1
    //   136: goto -18 -> 118
    //   139: astore_1
    //   140: goto -22 -> 118
    //   143: astore_2
    //   144: aload_1
    //   145: astore_0
    //   146: goto -46 -> 100
    //   149: aload_1
    //   150: astore_0
    //   151: goto -76 -> 75
    // Local variable table:
    //   start	length	slot	name	signature
    //   1	150	0	localObject1	Object
    //   13	89	1	localObject2	Object
    //   115	18	1	localFileNotFoundException1	java.io.FileNotFoundException
    //   135	1	1	localFileNotFoundException2	java.io.FileNotFoundException
    //   139	11	1	localFileNotFoundException3	java.io.FileNotFoundException
    //   11	63	2	str	String
    //   99	9	2	localIOException1	java.io.IOException
    //   131	4	2	localFileNotFoundException4	java.io.FileNotFoundException
    //   143	1	2	localIOException2	java.io.IOException
    //   3	114	3	localBufferedReader	java.io.BufferedReader
    //   8	39	4	localObject3	Object
    //   5	26	5	localObject4	Object
    //   23	46	6	localFileReader	java.io.FileReader
    // Exception table:
    //   from	to	target	type
    //   33	46	99	java/io/IOException
    //   14	25	115	java/io/FileNotFoundException
    //   33	46	131	java/io/FileNotFoundException
    //   102	112	131	java/io/FileNotFoundException
    //   51	56	139	java/io/FileNotFoundException
    //   60	64	139	java/io/FileNotFoundException
    //   68	73	139	java/io/FileNotFoundException
    //   51	56	143	java/io/IOException
    //   60	64	143	java/io/IOException
    //   68	73	143	java/io/IOException
  }
  
  public static String getChannel(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      if ((paramContext != null) && (paramContext.metaData != null))
      {
        paramContext = paramContext.metaData.get("UMENG_CHANNEL");
        if (paramContext != null)
        {
          paramContext = paramContext.toString();
          if (paramContext != null) {
            return paramContext;
          }
          Log.i(LOG_TAG, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
          return "Unknown";
        }
      }
    }
    catch (Exception paramContext)
    {
      Log.i(LOG_TAG, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
      paramContext.printStackTrace();
    }
    return "Unknown";
  }
  
  public static String getDeviceId(Context paramContext)
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
    if (localTelephonyManager == null) {
      Log.w(LOG_TAG, "No IMEI.");
    }
    Object localObject3 = "";
    Object localObject1 = localObject3;
    try
    {
      if (checkPermission(paramContext, "android.permission.READ_PHONE_STATE")) {
        localObject1 = localTelephonyManager.getDeviceId();
      }
      localObject3 = localObject1;
      if (TextUtils.isEmpty((CharSequence)localObject1))
      {
        Log.w(LOG_TAG, "No IMEI.");
        localObject1 = getMac(paramContext);
        localObject3 = localObject1;
        if (TextUtils.isEmpty((CharSequence)localObject1))
        {
          Log.w(LOG_TAG, "Failed to take mac as IMEI. Try to use Secure.ANDROID_ID instead.");
          paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
          Log.i(LOG_TAG, "getDeviceId: Secure.ANDROID_ID: " + paramContext);
          return paramContext;
        }
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.w(LOG_TAG, "No IMEI.", localException);
        Object localObject2 = localObject3;
      }
    }
    return localObject3;
  }
  
  public static String getDeviceIdUmengMD5(Context paramContext)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(getDeviceId(paramContext).getBytes());
      paramContext = ((MessageDigest)localObject).digest();
      localObject = new StringBuffer();
      int i = 0;
      for (;;)
      {
        if (i >= paramContext.length) {
          return ((StringBuffer)localObject).toString();
        }
        ((StringBuffer)localObject).append(Integer.toHexString(paramContext[i] & 0xFF));
        i += 1;
      }
      return "";
    }
    catch (NoSuchAlgorithmException paramContext)
    {
      Log.i(LOG_TAG, "getMD5 error", paramContext);
    }
  }
  
  public static String getDisplayResolution(Context paramContext)
  {
    try
    {
      DisplayMetrics localDisplayMetrics = new DisplayMetrics();
      ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
      int i = localDisplayMetrics.widthPixels;
      paramContext = String.valueOf(localDisplayMetrics.heightPixels) + "*" + String.valueOf(i);
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "Unknown";
  }
  
  public static String[] getGPU(GL10 paramGL10)
  {
    try
    {
      String str = paramGL10.glGetString(7936);
      paramGL10 = paramGL10.glGetString(7937);
      return new String[] { str, paramGL10 };
    }
    catch (Exception paramGL10)
    {
      Log.e(LOG_TAG, "Could not read gpu infor:", paramGL10);
    }
    return new String[0];
  }
  
  public static Set<String> getInstalledPackages(Context paramContext)
  {
    HashSet localHashSet = new HashSet();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      if (i >= paramContext.size()) {
        return localHashSet;
      }
      localHashSet.add(((PackageInfo)paramContext.get(i)).packageName);
      i += 1;
    }
  }
  
  public static int getIntervalSeconds(Date paramDate1, Date paramDate2)
  {
    Date localDate2 = paramDate1;
    Date localDate1 = paramDate2;
    if (paramDate1.after(paramDate2))
    {
      localDate1 = paramDate1;
      localDate2 = paramDate2;
    }
    long l = localDate2.getTime();
    return (int)((localDate1.getTime() - l) / 1000L);
  }
  
  private static Locale getLocale(Context paramContext)
  {
    localObject = null;
    try
    {
      Configuration localConfiguration = new Configuration();
      Settings.System.getConfiguration(paramContext.getContentResolver(), localConfiguration);
      paramContext = (Context)localObject;
      if (localConfiguration != null) {
        paramContext = localConfiguration.locale;
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        Log.e(LOG_TAG, "fail to read user config locale");
        paramContext = (Context)localObject;
      }
    }
    localObject = paramContext;
    if (paramContext == null) {
      localObject = Locale.getDefault();
    }
    return localObject;
  }
  
  public static String[] getLocaleInfo(Context paramContext)
  {
    String[] arrayOfString = new String[2];
    try
    {
      paramContext = getLocale(paramContext);
      if (paramContext != null)
      {
        arrayOfString[0] = paramContext.getCountry();
        arrayOfString[1] = paramContext.getLanguage();
      }
      if (TextUtils.isEmpty(arrayOfString[0])) {
        arrayOfString[0] = "Unknown";
      }
      if (TextUtils.isEmpty(arrayOfString[1])) {
        arrayOfString[1] = "Unknown";
      }
      return arrayOfString;
    }
    catch (Exception paramContext)
    {
      Log.e(LOG_TAG, "error in getLocaleInfo", paramContext);
    }
    return arrayOfString;
  }
  
  public static Location getLocation(Context paramContext)
  {
    try
    {
      LocationManager localLocationManager = (LocationManager)paramContext.getSystemService("location");
      if (checkPermission(paramContext, "android.permission.ACCESS_FINE_LOCATION"))
      {
        Location localLocation = localLocationManager.getLastKnownLocation("gps");
        if (localLocation != null)
        {
          Log.d(LOG_TAG, "get location from gps:" + localLocation.getLatitude() + "," + localLocation.getLongitude());
          return localLocation;
        }
      }
      if (checkPermission(paramContext, "android.permission.ACCESS_COARSE_LOCATION"))
      {
        paramContext = localLocationManager.getLastKnownLocation("network");
        if (paramContext != null)
        {
          Log.d(LOG_TAG, "get location from network:" + paramContext.getLatitude() + "," + paramContext.getLongitude());
          return paramContext;
        }
      }
      Log.d(LOG_TAG, "Could not get location from GPS or Cell-id, lack ACCESS_COARSE_LOCATION or ACCESS_COARSE_LOCATION permission?");
      return null;
    }
    catch (Exception paramContext)
    {
      Log.e(LOG_TAG, paramContext.getMessage());
    }
    return null;
  }
  
  public static String getMac(Context paramContext)
  {
    try
    {
      WifiManager localWifiManager = (WifiManager)paramContext.getSystemService("wifi");
      if (checkPermission(paramContext, "android.permission.ACCESS_WIFI_STATE")) {
        return localWifiManager.getConnectionInfo().getMacAddress();
      }
      Log.w(LOG_TAG, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        Log.w(LOG_TAG, "Could not get mac address." + paramContext.toString());
      }
    }
    return "";
  }
  
  public static String[] getNetworkAccessMode(Context paramContext)
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "Unknown";
    arrayOfString[1] = "Unknown";
    try
    {
      if (paramContext.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", paramContext.getPackageName()) != 0)
      {
        arrayOfString[0] = "Unknown";
        return arrayOfString;
      }
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (paramContext == null)
      {
        arrayOfString[0] = "Unknown";
        return arrayOfString;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return arrayOfString;
    }
    if (paramContext.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED)
    {
      arrayOfString[0] = "Wi-Fi";
      return arrayOfString;
    }
    paramContext = paramContext.getNetworkInfo(0);
    if (paramContext.getState() == NetworkInfo.State.CONNECTED)
    {
      arrayOfString[0] = "2G/3G";
      arrayOfString[1] = paramContext.getSubtypeName();
    }
    return arrayOfString;
  }
  
  public static String getNetworkOperatorName(Context paramContext)
  {
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext == null) {
        return "Unknown";
      }
      paramContext = paramContext.getNetworkOperatorName();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "Unknown";
  }
  
  public static String getOperator(Context paramContext)
  {
    try
    {
      paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getNetworkOperatorName();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      Log.i(LOG_TAG, "read carrier fail", paramContext);
    }
    return "Unknown";
  }
  
  public static String getPackageName(Context paramContext)
  {
    return paramContext.getPackageName();
  }
  
  public static String getResolution(Context paramContext)
  {
    for (;;)
    {
      int i;
      try
      {
        localDisplayMetrics = new DisplayMetrics();
        ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
        j = -1;
        i = -1;
        if ((paramContext.getApplicationInfo().flags & 0x2000) != 0) {
          break label130;
        }
        j = reflectMetrics(localDisplayMetrics, "noncompatWidthPixels");
        i = reflectMetrics(localDisplayMetrics, "noncompatHeightPixels");
      }
      catch (Exception paramContext)
      {
        DisplayMetrics localDisplayMetrics;
        Log.e(LOG_TAG, "read resolution fail", paramContext);
        return "Unknown";
      }
      int j = localDisplayMetrics.widthPixels;
      int k = localDisplayMetrics.heightPixels;
      paramContext = new StringBuffer();
      paramContext.append(j);
      paramContext.append("*");
      paramContext.append(k);
      paramContext = paramContext.toString();
      return paramContext;
      label130:
      if (j != -1)
      {
        k = i;
        if (i != -1) {}
      }
    }
  }
  
  public static String getTimeString(Date paramDate)
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(paramDate);
  }
  
  public static int getTimeZone(Context paramContext)
  {
    try
    {
      paramContext = Calendar.getInstance(getLocale(paramContext));
      if (paramContext != null)
      {
        int i = paramContext.getTimeZone().getRawOffset() / 3600000;
        return i;
      }
    }
    catch (Exception paramContext)
    {
      Log.i(LOG_TAG, "error in getTimeZone", paramContext);
    }
    return 8;
  }
  
  public static String getToday()
  {
    Date localDate = new Date();
    return new SimpleDateFormat("yyyy-MM-dd").format(localDate);
  }
  
  public static boolean isAppInstalled(String paramString, Context paramContext)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 1);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  public static boolean isChinese(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().locale.toString().equals(Locale.CHINA.toString());
  }
  
  public static boolean isDebug(Context paramContext)
  {
    boolean bool = false;
    try
    {
      int i = paramContext.getApplicationInfo().flags;
      if ((i & 0x2) != 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static boolean isOnline(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext != null)
      {
        boolean bool = paramContext.isConnectedOrConnecting();
        return bool;
      }
      return false;
    }
    catch (Exception paramContext) {}
    return true;
  }
  
  public static boolean isScreenPortrait(Context paramContext)
  {
    return paramContext.getResources().getConfiguration().orientation == 1;
  }
  
  public static boolean isSdCardWrittenable()
  {
    return Environment.getExternalStorageState().equals("mounted");
  }
  
  public static boolean isWiFiAvailable(Context paramContext)
  {
    return "Wi-Fi".equals(getNetworkAccessMode(paramContext)[0]);
  }
  
  private static int reflectMetrics(Object paramObject, String paramString)
  {
    try
    {
      paramString = DisplayMetrics.class.getDeclaredField(paramString);
      paramString.setAccessible(true);
      int i = paramString.getInt(paramObject);
      return i;
    }
    catch (Exception paramObject)
    {
      paramObject.printStackTrace();
    }
    return -1;
  }
  
  public static Date toTime(String paramString)
  {
    try
    {
      paramString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(paramString);
      return paramString;
    }
    catch (Exception paramString) {}
    return null;
  }
}
