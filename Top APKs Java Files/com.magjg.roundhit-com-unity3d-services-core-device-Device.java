package com.unity3d.services.core.device;

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
import android.content.pm.Signature;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.provider.Settings.Global;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.safedk.android.utils.Logger;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.properties.ClientProperties;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
  
  /* Error */
  public static String getApkDigest()
    throws Exception
  {
    // Byte code:
    //   0: invokestatic 31	com/unity3d/services/core/properties/ClientProperties:getApplicationContext	()Landroid/content/Context;
    //   3: invokevirtual 68	android/content/Context:getPackageCodePath	()Ljava/lang/String;
    //   6: astore_0
    //   7: aconst_null
    //   8: astore_1
    //   9: new 70	java/io/FileInputStream
    //   12: dup
    //   13: new 72	java/io/File
    //   16: dup
    //   17: aload_0
    //   18: invokespecial 75	java/io/File:<init>	(Ljava/lang/String;)V
    //   21: invokespecial 78	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   24: astore_0
    //   25: aload_0
    //   26: invokestatic 84	com/unity3d/services/core/misc/Utilities:Sha256	(Ljava/io/InputStream;)Ljava/lang/String;
    //   29: astore_1
    //   30: aload_0
    //   31: ifnull +7 -> 38
    //   34: aload_0
    //   35: invokevirtual 89	java/io/InputStream:close	()V
    //   38: aload_1
    //   39: areturn
    //   40: astore_0
    //   41: aload_1
    //   42: ifnull +7 -> 49
    //   45: aload_1
    //   46: invokevirtual 89	java/io/InputStream:close	()V
    //   49: aload_0
    //   50: athrow
    //   51: astore_0
    //   52: aload_1
    //   53: areturn
    //   54: astore_1
    //   55: goto -6 -> 49
    //   58: astore_2
    //   59: aload_0
    //   60: astore_1
    //   61: aload_2
    //   62: astore_0
    //   63: goto -22 -> 41
    // Local variable table:
    //   start	length	slot	name	signature
    //   6	29	0	localObject1	Object
    //   40	10	0	localObject2	Object
    //   51	9	0	localIOException1	java.io.IOException
    //   62	1	0	localObject3	Object
    //   8	45	1	str	String
    //   54	1	1	localIOException2	java.io.IOException
    //   60	1	1	localIOException3	java.io.IOException
    //   58	4	2	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   9	25	40	finally
    //   34	38	51	java/io/IOException
    //   45	49	54	java/io/IOException
    //   25	30	58	finally
  }
  
  public static float getBatteryLevel()
  {
    if (ClientProperties.getApplicationContext() != null)
    {
      Intent localIntent = ClientProperties.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      if (localIntent != null)
      {
        int i = safedk_Intent_getIntExtra_97da7053a853e359d46ad61039c31a36(localIntent, "level", -1);
        int j = safedk_Intent_getIntExtra_97da7053a853e359d46ad61039c31a36(localIntent, "scale", -1);
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
        i = safedk_Intent_getIntExtra_97da7053a853e359d46ad61039c31a36(localIntent, "status", -1);
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
  
  public static String getBuildId()
  {
    return Build.ID;
  }
  
  public static String getBuildVersionIncremental()
  {
    return Build.VERSION.INCREMENTAL;
  }
  
  public static long getCPUCount()
  {
    return Runtime.getRuntime().availableProcessors();
  }
  
  public static String getCertificateFingerprint()
  {
    Object localObject2 = null;
    Object localObject1 = ClientProperties.getApplicationContext().getPackageManager();
    Object localObject3 = ClientProperties.getApplicationContext().getPackageName();
    try
    {
      localObject3 = ((PackageManager)localObject1).getPackageInfo((String)localObject3, 64).signatures;
      localObject1 = localObject2;
      if (localObject3 != null)
      {
        localObject1 = localObject2;
        if (localObject3.length >= 1)
        {
          localObject1 = (X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(localObject3[0].toByteArray()));
          localObject1 = Utilities.toHexString(MessageDigest.getInstance("SHA-1").digest(((X509Certificate)localObject1).getEncoded()));
        }
      }
      return localObject1;
    }
    catch (Exception localException)
    {
      DeviceLog.exception("Exception when signing certificate fingerprint", localException);
    }
    return null;
  }
  
  public static String getDevice()
  {
    return Build.DEVICE;
  }
  
  public static long getElapsedRealtime()
  {
    return SystemClock.elapsedRealtime();
  }
  
  public static String getFingerprint()
  {
    return Build.FINGERPRINT;
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
  
  public static List<Map<String, Object>> getInstalledPackages(boolean paramBoolean)
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
    //   0: iconst_m1
    //   1: istore_1
    //   2: getstatic 353	com/unity3d/services/core/device/Device$1:$SwitchMap$com$unity3d$services$core$device$Device$MemoryInfoType	[I
    //   5: aload_0
    //   6: invokevirtual 356	com/unity3d/services/core/device/Device$MemoryInfoType:ordinal	()I
    //   9: iaload
    //   10: tableswitch	default:+22->32, 1:+188->198, 2:+193->203
    //   32: aconst_null
    //   33: astore 5
    //   35: aconst_null
    //   36: astore 7
    //   38: aconst_null
    //   39: astore 8
    //   41: new 358	java/io/RandomAccessFile
    //   44: dup
    //   45: ldc_w 360
    //   48: ldc_w 362
    //   51: invokespecial 365	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   54: astore 6
    //   56: iconst_0
    //   57: istore_2
    //   58: aload 8
    //   60: astore 5
    //   62: iload_2
    //   63: iload_1
    //   64: if_icmpge +17 -> 81
    //   67: aload 6
    //   69: invokevirtual 368	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   72: astore 5
    //   74: iload_2
    //   75: iconst_1
    //   76: iadd
    //   77: istore_2
    //   78: goto -16 -> 62
    //   81: aload 5
    //   83: invokestatic 372	com/unity3d/services/core/device/Device:getMemoryValueFromString	(Ljava/lang/String;)J
    //   86: lstore_3
    //   87: aload 6
    //   89: invokevirtual 373	java/io/RandomAccessFile:close	()V
    //   92: lload_3
    //   93: lreturn
    //   94: astore_0
    //   95: ldc_w 375
    //   98: aload_0
    //   99: invokestatic 53	com/unity3d/services/core/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   102: goto -10 -> 92
    //   105: astore 5
    //   107: aload 7
    //   109: astore 6
    //   111: aload 5
    //   113: astore 7
    //   115: aload 6
    //   117: astore 5
    //   119: new 377	java/lang/StringBuilder
    //   122: dup
    //   123: invokespecial 378	java/lang/StringBuilder:<init>	()V
    //   126: ldc_w 380
    //   129: invokevirtual 384	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: aload_0
    //   133: invokevirtual 387	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   136: invokevirtual 390	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   139: aload 7
    //   141: invokestatic 53	com/unity3d/services/core/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   144: aload 6
    //   146: invokevirtual 373	java/io/RandomAccessFile:close	()V
    //   149: ldc2_w 252
    //   152: lreturn
    //   153: astore_0
    //   154: ldc_w 375
    //   157: aload_0
    //   158: invokestatic 53	com/unity3d/services/core/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   161: goto -12 -> 149
    //   164: astore_0
    //   165: aload 5
    //   167: invokevirtual 373	java/io/RandomAccessFile:close	()V
    //   170: aload_0
    //   171: athrow
    //   172: astore 5
    //   174: ldc_w 375
    //   177: aload 5
    //   179: invokestatic 53	com/unity3d/services/core/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   182: goto -12 -> 170
    //   185: astore_0
    //   186: aload 6
    //   188: astore 5
    //   190: goto -25 -> 165
    //   193: astore 7
    //   195: goto -80 -> 115
    //   198: iconst_1
    //   199: istore_1
    //   200: goto -168 -> 32
    //   203: iconst_2
    //   204: istore_1
    //   205: goto -173 -> 32
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	208	0	paramMemoryInfoType	Device.MemoryInfoType
    //   1	204	1	i	int
    //   57	21	2	j	int
    //   86	7	3	l	long
    //   33	49	5	localObject1	Object
    //   105	7	5	localIOException1	java.io.IOException
    //   117	49	5	localObject2	Object
    //   172	6	5	localIOException2	java.io.IOException
    //   188	1	5	localObject3	Object
    //   54	133	6	localObject4	Object
    //   36	104	7	localIOException3	java.io.IOException
    //   193	1	7	localIOException4	java.io.IOException
    //   39	20	8	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   87	92	94	java/io/IOException
    //   41	56	105	java/io/IOException
    //   144	149	153	java/io/IOException
    //   41	56	164	finally
    //   119	144	164	finally
    //   165	170	172	java/io/IOException
    //   67	74	185	finally
    //   81	87	185	finally
    //   67	74	193	java/io/IOException
    //   81	87	193	java/io/IOException
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
  
  public static boolean getNetworkMetered()
  {
    ConnectivityManager localConnectivityManager;
    if ((ClientProperties.getApplicationContext() != null) && (Build.VERSION.SDK_INT >= 16))
    {
      localConnectivityManager = (ConnectivityManager)ClientProperties.getApplicationContext().getSystemService("connectivity");
      if (localConnectivityManager != null) {}
    }
    else
    {
      return false;
    }
    return localConnectivityManager.isActiveNetworkMetered();
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
  private static ArrayList<String> getNewAbiList()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(Arrays.asList(Build.SUPPORTED_ABIS));
    return localArrayList;
  }
  
  private static ArrayList<String> getOldAbiList()
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
  
  /* Error */
  public static Map<String, String> getProcessInfo()
  {
    // Byte code:
    //   0: new 304	java/util/HashMap
    //   3: dup
    //   4: invokespecial 305	java/util/HashMap:<init>	()V
    //   7: astore_3
    //   8: aconst_null
    //   9: astore_0
    //   10: aconst_null
    //   11: astore_2
    //   12: new 358	java/io/RandomAccessFile
    //   15: dup
    //   16: ldc_w 476
    //   19: ldc_w 362
    //   22: invokespecial 365	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   25: astore_1
    //   26: aload_3
    //   27: ldc_w 478
    //   30: aload_1
    //   31: invokevirtual 368	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   34: invokevirtual 317	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   37: pop
    //   38: aload_1
    //   39: invokevirtual 373	java/io/RandomAccessFile:close	()V
    //   42: aload_3
    //   43: areturn
    //   44: astore_0
    //   45: ldc_w 375
    //   48: aload_0
    //   49: invokestatic 53	com/unity3d/services/core/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   52: aload_3
    //   53: areturn
    //   54: astore_0
    //   55: aload_2
    //   56: astore_1
    //   57: aload_0
    //   58: astore_2
    //   59: aload_1
    //   60: astore_0
    //   61: ldc_w 480
    //   64: aload_2
    //   65: invokestatic 53	com/unity3d/services/core/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   68: aload_1
    //   69: invokevirtual 373	java/io/RandomAccessFile:close	()V
    //   72: aload_3
    //   73: areturn
    //   74: astore_0
    //   75: ldc_w 375
    //   78: aload_0
    //   79: invokestatic 53	com/unity3d/services/core/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   82: aload_3
    //   83: areturn
    //   84: astore_1
    //   85: aload_0
    //   86: invokevirtual 373	java/io/RandomAccessFile:close	()V
    //   89: aload_1
    //   90: athrow
    //   91: astore_0
    //   92: ldc_w 375
    //   95: aload_0
    //   96: invokestatic 53	com/unity3d/services/core/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   99: goto -10 -> 89
    //   102: astore_2
    //   103: aload_1
    //   104: astore_0
    //   105: aload_2
    //   106: astore_1
    //   107: goto -22 -> 85
    //   110: astore_2
    //   111: goto -52 -> 59
    // Local variable table:
    //   start	length	slot	name	signature
    //   9	1	0	localObject1	Object
    //   44	5	0	localIOException1	java.io.IOException
    //   54	4	0	localIOException2	java.io.IOException
    //   60	1	0	localObject2	Object
    //   74	12	0	localIOException3	java.io.IOException
    //   91	5	0	localIOException4	java.io.IOException
    //   104	1	0	localObject3	Object
    //   25	44	1	localObject4	Object
    //   84	20	1	localObject5	Object
    //   106	1	1	localObject6	Object
    //   11	54	2	localIOException5	java.io.IOException
    //   102	4	2	localObject7	Object
    //   110	1	2	localIOException6	java.io.IOException
    //   7	76	3	localHashMap	HashMap
    // Exception table:
    //   from	to	target	type
    //   38	42	44	java/io/IOException
    //   12	26	54	java/io/IOException
    //   68	72	74	java/io/IOException
    //   12	26	84	finally
    //   61	68	84	finally
    //   85	89	91	java/io/IOException
    //   26	38	102	finally
    //   26	38	110	java/io/IOException
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
  
  public static List<Sensor> getSensorList()
  {
    if (ClientProperties.getApplicationContext() != null) {
      return ((SensorManager)ClientProperties.getApplicationContext().getSystemService("sensor")).getSensorList(-1);
    }
    return null;
  }
  
  public static int getStreamMaxVolume(int paramInt)
  {
    if (ClientProperties.getApplicationContext() != null)
    {
      AudioManager localAudioManager = (AudioManager)ClientProperties.getApplicationContext().getSystemService("audio");
      if (localAudioManager != null) {
        return localAudioManager.getStreamMaxVolume(paramInt);
      }
      return -2;
    }
    return -1;
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
  
  public static ArrayList<String> getSupportedAbis()
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
  
  public static long getUptime()
  {
    return SystemClock.uptimeMillis();
  }
  
  public static boolean isActiveNetworkConnected()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (ClientProperties.getApplicationContext() != null)
    {
      Object localObject = (ConnectivityManager)ClientProperties.getApplicationContext().getSystemService("connectivity");
      bool1 = bool2;
      if (localObject != null)
      {
        localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
        bool1 = bool2;
        if (localObject != null)
        {
          bool1 = bool2;
          if (((NetworkInfo)localObject).isConnected()) {
            bool1 = true;
          }
        }
      }
    }
    return bool1;
  }
  
  public static Boolean isAdbEnabled()
  {
    if (getApiLevel() < 17) {
      return oldAdbStatus();
    }
    return newAdbStatus();
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
    catch (PackageManager.NameNotFoundException paramString) {}
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
  
  public static boolean isUSBConnected()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (ClientProperties.getApplicationContext() != null)
    {
      Intent localIntent = ClientProperties.getApplicationContext().registerReceiver(null, new IntentFilter("android.hardware.usb.action.USB_STATE"));
      bool1 = bool2;
      if (localIntent != null) {
        bool1 = safedk_Intent_getBooleanExtra_e6068b21c912ff007af22297aa28d38e(localIntent, "connected", false);
      }
    }
    return bool1;
  }
  
  public static boolean isUsingWifi()
  {
    boolean bool = true;
    ConnectivityManager localConnectivityManager;
    if (ClientProperties.getApplicationContext() != null)
    {
      localConnectivityManager = (ConnectivityManager)ClientProperties.getApplicationContext().getSystemService("connectivity");
      if (localConnectivityManager != null) {
        break label27;
      }
    }
    label27:
    TelephonyManager localTelephonyManager;
    NetworkInfo localNetworkInfo;
    do
    {
      return false;
      localTelephonyManager = (TelephonyManager)ClientProperties.getApplicationContext().getSystemService("phone");
      localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
    } while ((localNetworkInfo == null) || (!localConnectivityManager.getBackgroundDataSetting()) || (!localConnectivityManager.getActiveNetworkInfo().isConnected()) || (localTelephonyManager == null));
    if ((localNetworkInfo.getType() == 1) && (localNetworkInfo.isConnected())) {}
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  public static boolean isWiredHeadsetOn()
  {
    if (ClientProperties.getApplicationContext() != null) {
      return ((AudioManager)ClientProperties.getApplicationContext().getSystemService("audio")).isWiredHeadsetOn();
    }
    return false;
  }
  
  @TargetApi(17)
  private static Boolean newAdbStatus()
  {
    boolean bool = true;
    try
    {
      if (1 == Settings.Global.getInt(ClientProperties.getApplicationContext().getContentResolver(), "adb_enabled", 0)) {}
      for (;;)
      {
        return Boolean.valueOf(bool);
        bool = false;
      }
      return null;
    }
    catch (Exception localException)
    {
      DeviceLog.exception("Problems fetching adb enabled status", localException);
    }
  }
  
  private static Boolean oldAdbStatus()
  {
    boolean bool = true;
    try
    {
      if (1 == Settings.Secure.getInt(ClientProperties.getApplicationContext().getContentResolver(), "adb_enabled", 0)) {}
      for (;;)
      {
        return Boolean.valueOf(bool);
        bool = false;
      }
      return null;
    }
    catch (Exception localException)
    {
      DeviceLog.exception("Problems fetching adb enabled status", localException);
    }
  }
  
  public static boolean safedk_Intent_getBooleanExtra_e6068b21c912ff007af22297aa28d38e(Intent paramIntent, String paramString, boolean paramBoolean)
  {
    Logger.d("SafeDK-Special|SafeDK: Call> Landroid/content/Intent;->getBooleanExtra(Ljava/lang/String;Z)Z");
    if (paramIntent == null) {
      return false;
    }
    return paramIntent.getBooleanExtra(paramString, paramBoolean);
  }
  
  public static int safedk_Intent_getIntExtra_97da7053a853e359d46ad61039c31a36(Intent paramIntent, String paramString, int paramInt)
  {
    Logger.d("SafeDK-Special|SafeDK: Call> Landroid/content/Intent;->getIntExtra(Ljava/lang/String;I)I");
    if (paramIntent == null) {
      return 0;
    }
    return paramIntent.getIntExtra(paramString, paramInt);
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
