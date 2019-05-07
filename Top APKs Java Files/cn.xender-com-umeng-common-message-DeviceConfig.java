package com.umeng.common.message;

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
import android.view.Display;
import android.view.WindowManager;
import com.umeng.common.message.a.g;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import javax.microedition.khronos.opengles.GL10;
import org.agoo.ut.device.UTDevice;

public class DeviceConfig
{
  public static final int DEFAULT_TIMEZONE = 8;
  protected static final String a = DeviceConfig.class.getName();
  protected static final String b = "Unknown";
  private static final String c = "2G/3G";
  private static final String d = "Wi-Fi";
  
  public DeviceConfig() {}
  
  private static int a(Object paramObject, String paramString)
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
  
  private static Locale a(Context paramContext)
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
        Log.b(a, "fail to read user config locale");
        paramContext = (Context)localObject;
      }
    }
    localObject = paramContext;
    if (paramContext == null) {
      localObject = Locale.getDefault();
    }
    return localObject;
  }
  
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
    return getMetaData(paramContext, "UMENG_APPKEY");
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
    //   2: new 158	java/io/FileReader
    //   5: dup
    //   6: ldc -96
    //   8: invokespecial 163	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   11: astore_1
    //   12: aload_1
    //   13: ifnull +28 -> 41
    //   16: new 165	java/io/BufferedReader
    //   19: dup
    //   20: aload_1
    //   21: sipush 1024
    //   24: invokespecial 168	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   27: astore_2
    //   28: aload_2
    //   29: invokevirtual 171	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   32: astore_0
    //   33: aload_2
    //   34: invokevirtual 174	java/io/BufferedReader:close	()V
    //   37: aload_1
    //   38: invokevirtual 175	java/io/FileReader:close	()V
    //   41: aload_0
    //   42: astore_1
    //   43: aload_0
    //   44: ifnull +16 -> 60
    //   47: aload_0
    //   48: aload_0
    //   49: bipush 58
    //   51: invokevirtual 179	java/lang/String:indexOf	(I)I
    //   54: iconst_1
    //   55: iadd
    //   56: invokevirtual 182	java/lang/String:substring	(I)Ljava/lang/String;
    //   59: astore_1
    //   60: aload_1
    //   61: invokevirtual 185	java/lang/String:trim	()Ljava/lang/String;
    //   64: areturn
    //   65: astore_1
    //   66: aconst_null
    //   67: astore_0
    //   68: getstatic 28	com/umeng/common/message/DeviceConfig:a	Ljava/lang/String;
    //   71: ldc -69
    //   73: aload_1
    //   74: invokestatic 190	com/umeng/common/message/Log:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
    //   77: goto -36 -> 41
    //   80: astore_1
    //   81: aconst_null
    //   82: astore_0
    //   83: getstatic 28	com/umeng/common/message/DeviceConfig:a	Ljava/lang/String;
    //   86: ldc -64
    //   88: aload_1
    //   89: invokestatic 190	com/umeng/common/message/Log:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Exception;)V
    //   92: goto -51 -> 41
    //   95: astore_1
    //   96: goto -13 -> 83
    //   99: astore_1
    //   100: goto -17 -> 83
    //   103: astore_1
    //   104: goto -36 -> 68
    // Local variable table:
    //   start	length	slot	name	signature
    //   1	82	0	str	String
    //   11	50	1	localObject	Object
    //   65	9	1	localIOException1	java.io.IOException
    //   80	9	1	localFileNotFoundException1	java.io.FileNotFoundException
    //   95	1	1	localFileNotFoundException2	java.io.FileNotFoundException
    //   99	1	1	localFileNotFoundException3	java.io.FileNotFoundException
    //   103	1	1	localIOException2	java.io.IOException
    //   27	7	2	localBufferedReader	java.io.BufferedReader
    // Exception table:
    //   from	to	target	type
    //   16	33	65	java/io/IOException
    //   2	12	80	java/io/FileNotFoundException
    //   16	33	80	java/io/FileNotFoundException
    //   33	41	95	java/io/FileNotFoundException
    //   68	77	99	java/io/FileNotFoundException
    //   33	41	103	java/io/IOException
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
          Log.a(a, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
          return "Unknown";
        }
      }
    }
    catch (Exception paramContext)
    {
      Log.a(a, "Could not read UMENG_CHANNEL meta-data from AndroidManifest.xml.");
      paramContext.printStackTrace();
    }
    return "Unknown";
  }
  
  public static String getDeviceId(Context paramContext)
  {
    Object localObject = (TelephonyManager)paramContext.getSystemService("phone");
    if (localObject == null) {
      Log.e(a, "No IMEI.");
    }
    try
    {
      if (checkPermission(paramContext, "android.permission.READ_PHONE_STATE"))
      {
        str = ((TelephonyManager)localObject).getDeviceId();
        localObject = str;
        if (TextUtils.isEmpty(str))
        {
          Log.e(a, "No IMEI.");
          str = getMac(paramContext);
          localObject = str;
          if (TextUtils.isEmpty(str))
          {
            Log.e(a, "Failed to take mac as IMEI. Try to use Secure.ANDROID_ID instead.");
            localObject = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
            Log.a(a, "getDeviceId: Secure.ANDROID_ID: " + (String)localObject);
          }
        }
        return localObject;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Log.e(a, "No IMEI.", localException);
        String str = "";
      }
    }
  }
  
  public static String getDeviceIdMD5(Context paramContext)
  {
    return g.a(getDeviceId(paramContext));
  }
  
  public static String getDeviceIdUmengMD5(Context paramContext)
  {
    return g.b(getDeviceId(paramContext));
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
      Log.b(a, "Could not read gpu infor:", paramGL10);
    }
    return new String[0];
  }
  
  public static Set<String> getInstalledPackages(Context paramContext)
  {
    int i = 0;
    HashSet localHashSet = new HashSet();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
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
    if (paramDate1.after(paramDate2)) {}
    for (;;)
    {
      long l = paramDate2.getTime();
      return (int)((paramDate1.getTime() - l) / 1000L);
      Date localDate = paramDate1;
      paramDate1 = paramDate2;
      paramDate2 = localDate;
    }
  }
  
  public static String[] getLocaleInfo(Context paramContext)
  {
    String[] arrayOfString = new String[2];
    try
    {
      paramContext = a(paramContext);
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
      Log.b(a, "error in getLocaleInfo", paramContext);
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
          Log.c(a, "get location from gps:" + localLocation.getLatitude() + "," + localLocation.getLongitude());
          return localLocation;
        }
      }
      if (checkPermission(paramContext, "android.permission.ACCESS_COARSE_LOCATION"))
      {
        paramContext = localLocationManager.getLastKnownLocation("network");
        if (paramContext != null)
        {
          Log.c(a, "get location from network:" + paramContext.getLatitude() + "," + paramContext.getLongitude());
          return paramContext;
        }
      }
    }
    catch (Exception paramContext)
    {
      Log.b(a, paramContext.getMessage());
      return null;
    }
    Log.c(a, "Could not get location from GPS or Cell-id, lack ACCESS_COARSE_LOCATION or ACCESS_COARSE_LOCATION permission?");
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
      Log.e(a, "Could not get mac address.[no permission android.permission.ACCESS_WIFI_STATE");
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        Log.e(a, "Could not get mac address." + paramContext.toString());
      }
    }
    return "";
  }
  
  public static String getMetaData(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      if (paramContext != null)
      {
        paramContext = paramContext.metaData.getString(paramString);
        if (paramContext != null)
        {
          paramContext = paramContext.trim();
          return paramContext;
        }
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      Log.b(a, String.format("Could not read meta-data %s from AndroidManifest.xml.", new Object[] { paramString }));
    }
    return null;
  }
  
  public static String[] getNetworkAccessMode(Context paramContext)
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "Unknown";
    arrayOfString[1] = "Unknown";
    try
    {
      paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (paramContext == null) {
        return arrayOfString;
      }
      NetworkInfo localNetworkInfo = paramContext.getNetworkInfo(1);
      if ((localNetworkInfo != null) && (localNetworkInfo.getState() == NetworkInfo.State.CONNECTED))
      {
        arrayOfString[0] = "Wi-Fi";
        return arrayOfString;
      }
      paramContext = paramContext.getNetworkInfo(0);
      if ((paramContext != null) && (paramContext.getState() == NetworkInfo.State.CONNECTED))
      {
        arrayOfString[0] = "2G/3G";
        arrayOfString[1] = paramContext.getSubtypeName();
      }
      return arrayOfString;
    }
    catch (Exception paramContext) {}
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
      Log.a(a, "read carrier fail", paramContext);
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
      try
      {
        localDisplayMetrics = new DisplayMetrics();
        ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
        if ((paramContext.getApplicationInfo().flags & 0x2000) != 0) {
          break label134;
        }
        j = a(localDisplayMetrics, "noncompatWidthPixels");
        i = a(localDisplayMetrics, "noncompatHeightPixels");
      }
      catch (Exception paramContext)
      {
        DisplayMetrics localDisplayMetrics;
        label80:
        Log.b(a, "read resolution fail", paramContext);
        return "Unknown";
      }
      int i = localDisplayMetrics.widthPixels;
      int j = localDisplayMetrics.heightPixels;
      int k = i;
      i = j;
      paramContext = new StringBuffer();
      paramContext.append(k);
      paramContext.append("*");
      paramContext.append(i);
      paramContext = paramContext.toString();
      return paramContext;
      label134:
      do
      {
        k = j;
        break label80;
        i = -1;
        j = -1;
        if (j == -1) {
          break;
        }
      } while (i != -1);
    }
  }
  
  public static String getTimeString(Date paramDate)
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(paramDate);
  }
  
  public static int getTimeZone(Context paramContext)
  {
    try
    {
      paramContext = Calendar.getInstance(a(paramContext));
      if (paramContext != null)
      {
        int i = paramContext.getTimeZone().getRawOffset() / 3600000;
        return i;
      }
    }
    catch (Exception paramContext)
    {
      Log.a(a, "error in getTimeZone", paramContext);
    }
    return 8;
  }
  
  public static String getToday()
  {
    Date localDate = new Date();
    return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(localDate);
  }
  
  public static String getUtdid(Context paramContext)
  {
    try
    {
      paramContext = UTDevice.getUtdid(paramContext);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      Log.b(a, "fail to get utdid. " + paramContext.getMessage());
    }
    return "";
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
    try
    {
      int i = paramContext.getApplicationInfo().flags;
      return (i & 0x2) != 0;
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
