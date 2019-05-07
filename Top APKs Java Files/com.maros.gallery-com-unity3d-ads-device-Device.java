package com.unity3d.ads.device;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ConfigurationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.unity3d.ads.log.DeviceLog;
import com.unity3d.ads.misc.Utilities;
import com.unity3d.ads.properties.ClientProperties;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Device
{
  public Device() {}
  
  public static String getAdvertisingTrackingId()
  {
    return AdvertisingId.getAdvertisingTrackingId();
  }
  
  @SuppressLint({"DefaultLocale"})
  public static String getAndroidId()
  {
    try
    {
      String str = Settings.Secure.getString(ClientProperties.getApplicationContext().getContentResolver(), "android_id");
      return str;
    }
    catch (Exception localException)
    {
      DeviceLog.exception("Problems fetching androidId", localException);
    }
    return null;
  }
  
  public static int getApiLevel()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static float getBatteryLevel()
  {
    if (ClientProperties.getApplicationContext() != null)
    {
      Intent localIntent = ClientProperties.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      if (localIntent != null)
      {
        int i = localIntent.getIntExtra("level", -1);
        int j = localIntent.getIntExtra("scale", -1);
        return i / j;
      }
    }
    return -1.0F;
  }
  
  public static int getBatteryStatus()
  {
    int j = -1;
    int i = j;
    if (ClientProperties.getApplicationContext() != null)
    {
      Intent localIntent = ClientProperties.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      i = j;
      if (localIntent != null) {
        i = localIntent.getIntExtra("status", -1);
      }
    }
    return i;
  }
  
  public static String getBoard()
  {
    return Build.BOARD;
  }
  
  public static String getBootloader()
  {
    return Build.BOOTLOADER;
  }
  
  public static String getBrand()
  {
    return Build.BRAND;
  }
  
  public static String getDevice()
  {
    return Build.DEVICE;
  }
  
  public static long getFreeMemory()
  {
    return getMemoryInfo(Device.MemoryInfoType.FREE_MEMORY);
  }
  
  public static long getFreeSpace(File paramFile)
  {
    if ((paramFile != null) && (paramFile.exists())) {
      return Math.round((float)(paramFile.getFreeSpace() / 1024L));
    }
    return -1L;
  }
  
  public static String getGLVersion()
  {
    if (ClientProperties.getApplicationContext() != null)
    {
      Object localObject = (ActivityManager)ClientProperties.getApplicationContext().getSystemService("activity");
      if (localObject != null)
      {
        localObject = ((ActivityManager)localObject).getDeviceConfigurationInfo();
        if (localObject != null) {
          return ((ConfigurationInfo)localObject).getGlEsVersion();
        }
      }
    }
    return null;
  }
  
  public static String getHardware()
  {
    return Build.HARDWARE;
  }
  
  public static String getHost()
  {
    return Build.HOST;
  }
  
  public static List getInstalledPackages(boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    if (ClientProperties.getApplicationContext() != null)
    {
      PackageManager localPackageManager = ClientProperties.getApplicationContext().getPackageManager();
      Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
      if (localIterator.hasNext())
      {
        Object localObject = (PackageInfo)localIterator.next();
        HashMap localHashMap = new HashMap();
        if (paramBoolean) {
          localHashMap.put("name", Utilities.Sha256(((PackageInfo)localObject).packageName));
        }
        for (;;)
        {
          if (((PackageInfo)localObject).firstInstallTime > 0L) {
            localHashMap.put("time", Long.valueOf(((PackageInfo)localObject).firstInstallTime));
          }
          localObject = localPackageManager.getInstallerPackageName(((PackageInfo)localObject).packageName);
          if ((localObject != null) && (!((String)localObject).isEmpty())) {
            localHashMap.put("installer", localObject);
          }
          localArrayList.add(localHashMap);
          break;
          localHashMap.put("name", ((PackageInfo)localObject).packageName);
        }
      }
    }
    return localArrayList;
  }
  
  public static String getManufacturer()
  {
    return Build.MANUFACTURER;
  }
  
  /* Error */
  private static long getMemoryInfo(Device.MemoryInfoType paramMemoryInfoType)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 7
    //   6: iconst_m1
    //   7: istore_1
    //   8: getstatic 249	com/unity3d/ads/device/Device$1:$SwitchMap$com$unity3d$ads$device$Device$MemoryInfoType	[I
    //   11: aload_0
    //   12: invokevirtual 252	com/unity3d/ads/device/Device$MemoryInfoType:ordinal	()I
    //   15: iaload
    //   16: tableswitch	default:+24->40, 1:+179->195, 2:+184->200
    //   40: new 254	java/io/RandomAccessFile
    //   43: dup
    //   44: ldc_w 256
    //   47: ldc_w 258
    //   50: invokespecial 261	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   53: astore 6
    //   55: aconst_null
    //   56: astore 5
    //   58: iconst_0
    //   59: istore_2
    //   60: iload_2
    //   61: iload_1
    //   62: if_icmpge +17 -> 79
    //   65: aload 6
    //   67: invokevirtual 264	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   70: astore 5
    //   72: iload_2
    //   73: iconst_1
    //   74: iadd
    //   75: istore_2
    //   76: goto -16 -> 60
    //   79: aload 5
    //   81: invokestatic 268	com/unity3d/ads/device/Device:getMemoryValueFromString	(Ljava/lang/String;)J
    //   84: lstore_3
    //   85: aload 6
    //   87: invokevirtual 271	java/io/RandomAccessFile:close	()V
    //   90: lload_3
    //   91: lreturn
    //   92: astore_0
    //   93: ldc_w 273
    //   96: aload_0
    //   97: invokestatic 49	com/unity3d/ads/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   100: lload_3
    //   101: lreturn
    //   102: astore 5
    //   104: aload 7
    //   106: astore 6
    //   108: aload 5
    //   110: astore 7
    //   112: aload 6
    //   114: astore 5
    //   116: new 275	java/lang/StringBuilder
    //   119: dup
    //   120: invokespecial 276	java/lang/StringBuilder:<init>	()V
    //   123: ldc_w 278
    //   126: invokevirtual 282	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   129: aload_0
    //   130: invokevirtual 285	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   133: invokevirtual 288	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   136: aload 7
    //   138: invokestatic 49	com/unity3d/ads/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   141: aload 6
    //   143: invokevirtual 271	java/io/RandomAccessFile:close	()V
    //   146: ldc2_w 135
    //   149: lreturn
    //   150: astore_0
    //   151: ldc_w 273
    //   154: aload_0
    //   155: invokestatic 49	com/unity3d/ads/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   158: goto -12 -> 146
    //   161: astore_0
    //   162: aload 5
    //   164: invokevirtual 271	java/io/RandomAccessFile:close	()V
    //   167: aload_0
    //   168: athrow
    //   169: astore 5
    //   171: ldc_w 273
    //   174: aload 5
    //   176: invokestatic 49	com/unity3d/ads/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   179: goto -12 -> 167
    //   182: astore_0
    //   183: aload 6
    //   185: astore 5
    //   187: goto -25 -> 162
    //   190: astore 7
    //   192: goto -80 -> 112
    //   195: iconst_1
    //   196: istore_1
    //   197: goto -157 -> 40
    //   200: iconst_2
    //   201: istore_1
    //   202: goto -162 -> 40
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	205	0	paramMemoryInfoType	Device.MemoryInfoType
    //   7	195	1	i	int
    //   59	17	2	j	int
    //   84	17	3	l	long
    //   1	79	5	str	String
    //   102	7	5	localIOException1	java.io.IOException
    //   114	49	5	localObject1	Object
    //   169	6	5	localIOException2	java.io.IOException
    //   185	1	5	localObject2	Object
    //   53	131	6	localObject3	Object
    //   4	133	7	localIOException3	java.io.IOException
    //   190	1	7	localIOException4	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   85	90	92	java/io/IOException
    //   40	55	102	java/io/IOException
    //   141	146	150	java/io/IOException
    //   40	55	161	finally
    //   116	141	161	finally
    //   162	167	169	java/io/IOException
    //   65	72	182	finally
    //   79	85	182	finally
    //   65	72	190	java/io/IOException
    //   79	85	190	java/io/IOException
  }
  
  private static long getMemoryValueFromString(String paramString)
  {
    if (paramString != null)
    {
      Matcher localMatcher = Pattern.compile("(\\d+)").matcher(paramString);
      for (paramString = ""; localMatcher.find(); paramString = localMatcher.group(1)) {}
      return Long.parseLong(paramString);
    }
    return -1L;
  }
  
  public static String getModel()
  {
    return Build.MODEL;
  }
  
  public static String getNetworkOperator()
  {
    if (ClientProperties.getApplicationContext() != null) {
      return ((TelephonyManager)ClientProperties.getApplicationContext().getSystemService("phone")).getNetworkOperator();
    }
    return "";
  }
  
  public static String getNetworkOperatorName()
  {
    if (ClientProperties.getApplicationContext() != null) {
      return ((TelephonyManager)ClientProperties.getApplicationContext().getSystemService("phone")).getNetworkOperatorName();
    }
    return "";
  }
  
  public static int getNetworkType()
  {
    if (ClientProperties.getApplicationContext() != null) {
      return ((TelephonyManager)ClientProperties.getApplicationContext().getSystemService("phone")).getNetworkType();
    }
    return -1;
  }
  
  @TargetApi(21)
  private static ArrayList getNewAbiList()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(Arrays.asList(Build.SUPPORTED_ABIS));
    return localArrayList;
  }
  
  private static ArrayList getOldAbiList()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(Build.CPU_ABI);
    localArrayList.add(Build.CPU_ABI2);
    return localArrayList;
  }
  
  public static String getOsVersion()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static String getProduct()
  {
    return Build.PRODUCT;
  }
  
  public static int getRingerMode()
  {
    if (ClientProperties.getApplicationContext() != null)
    {
      AudioManager localAudioManager = (AudioManager)ClientProperties.getApplicationContext().getSystemService("audio");
      if (localAudioManager != null) {
        return localAudioManager.getRingerMode();
      }
      return -2;
    }
    return -1;
  }
  
  public static int getScreenBrightness()
  {
    int i = -1;
    if (ClientProperties.getApplicationContext() != null) {
      i = Settings.System.getInt(ClientProperties.getApplicationContext().getContentResolver(), "screen_brightness", -1);
    }
    return i;
  }
  
  public static int getScreenDensity()
  {
    if (ClientProperties.getApplicationContext() != null) {
      return ClientProperties.getApplicationContext().getResources().getDisplayMetrics().densityDpi;
    }
    return -1;
  }
  
  public static int getScreenHeight()
  {
    if (ClientProperties.getApplicationContext() != null) {
      return ClientProperties.getApplicationContext().getResources().getDisplayMetrics().heightPixels;
    }
    return -1;
  }
  
  public static int getScreenLayout()
  {
    if (ClientProperties.getApplicationContext() != null) {
      return ClientProperties.getApplicationContext().getResources().getConfiguration().screenLayout;
    }
    return -1;
  }
  
  public static int getScreenWidth()
  {
    if (ClientProperties.getApplicationContext() != null) {
      return ClientProperties.getApplicationContext().getResources().getDisplayMetrics().widthPixels;
    }
    return -1;
  }
  
  public static List getSensorList()
  {
    if (ClientProperties.getApplicationContext() != null) {
      return ((SensorManager)ClientProperties.getApplicationContext().getSystemService("sensor")).getSensorList(-1);
    }
    return null;
  }
  
  public static int getStreamVolume(int paramInt)
  {
    if (ClientProperties.getApplicationContext() != null)
    {
      AudioManager localAudioManager = (AudioManager)ClientProperties.getApplicationContext().getSystemService("audio");
      if (localAudioManager != null) {
        return localAudioManager.getStreamVolume(paramInt);
      }
      return -2;
    }
    return -1;
  }
  
  public static ArrayList getSupportedAbis()
  {
    if (getApiLevel() < 21) {
      return getOldAbiList();
    }
    return getNewAbiList();
  }
  
  public static String getSystemProperty(String paramString1, String paramString2)
  {
    if (paramString2 != null) {
      return System.getProperty(paramString1, paramString2);
    }
    return System.getProperty(paramString1);
  }
  
  public static long getTotalMemory()
  {
    return getMemoryInfo(Device.MemoryInfoType.TOTAL_MEMORY);
  }
  
  public static long getTotalSpace(File paramFile)
  {
    if ((paramFile != null) && (paramFile.exists())) {
      return Math.round((float)(paramFile.getTotalSpace() / 1024L));
    }
    return -1L;
  }
  
  public static String getUniqueEventId()
  {
    return UUID.randomUUID().toString();
  }
  
  public static boolean isActiveNetworkConnected()
  {
    if (ClientProperties.getApplicationContext() != null)
    {
      Object localObject = (ConnectivityManager)ClientProperties.getApplicationContext().getSystemService("connectivity");
      if (localObject != null)
      {
        localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
        return (localObject != null) && (((NetworkInfo)localObject).isConnected());
      }
    }
    return false;
  }
  
  public static boolean isAppInstalled(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    Object localObject;
    if (ClientProperties.getApplicationContext() != null) {
      localObject = ClientProperties.getApplicationContext().getPackageManager();
    }
    try
    {
      localObject = ((PackageManager)localObject).getPackageInfo(paramString, 0);
      bool1 = bool2;
      if (localObject != null)
      {
        bool1 = bool2;
        if (((PackageInfo)localObject).packageName != null)
        {
          boolean bool3 = paramString.equals(((PackageInfo)localObject).packageName);
          bool1 = bool2;
          if (bool3) {
            bool1 = true;
          }
        }
      }
      return bool1;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      DeviceLog.exception("Couldn't find package: " + paramString, localNameNotFoundException);
    }
    return false;
  }
  
  public static boolean isLimitAdTrackingEnabled()
  {
    return AdvertisingId.getLimitedAdTracking();
  }
  
  public static boolean isRooted()
  {
    try
    {
      boolean bool = searchPathForBinary("su");
      return bool;
    }
    catch (Exception localException)
    {
      DeviceLog.exception("Rooted check failed", localException);
    }
    return false;
  }
  
  public static boolean isUsingWifi()
  {
    ConnectivityManager localConnectivityManager;
    if (ClientProperties.getApplicationContext() != null)
    {
      localConnectivityManager = (ConnectivityManager)ClientProperties.getApplicationContext().getSystemService("connectivity");
      if (localConnectivityManager != null) {
        break label25;
      }
    }
    label25:
    TelephonyManager localTelephonyManager;
    NetworkInfo localNetworkInfo;
    do
    {
      return false;
      localTelephonyManager = (TelephonyManager)ClientProperties.getApplicationContext().getSystemService("phone");
      localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
    } while ((localNetworkInfo == null) || (!localConnectivityManager.getBackgroundDataSetting()) || (!localConnectivityManager.getActiveNetworkInfo().isConnected()) || (localTelephonyManager == null));
    if ((localNetworkInfo.getType() == 1) && (localNetworkInfo.isConnected())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static boolean isWiredHeadsetOn()
  {
    if (ClientProperties.getApplicationContext() != null) {
      return ((AudioManager)ClientProperties.getApplicationContext().getSystemService("audio")).isWiredHeadsetOn();
    }
    return false;
  }
  
  private static boolean searchPathForBinary(String paramString)
  {
    boolean bool2 = false;
    String[] arrayOfString = System.getenv("PATH").split(":");
    int k = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      Object localObject;
      int m;
      int j;
      if (i < k)
      {
        localObject = new File(arrayOfString[i]);
        if ((((File)localObject).exists()) && (((File)localObject).isDirectory()))
        {
          localObject = ((File)localObject).listFiles();
          if (localObject != null)
          {
            m = localObject.length;
            j = 0;
          }
        }
      }
      else
      {
        while (j < m)
        {
          if (localObject[j].getName().equals(paramString))
          {
            bool1 = true;
            return bool1;
          }
          j += 1;
        }
      }
      i += 1;
    }
  }
}
