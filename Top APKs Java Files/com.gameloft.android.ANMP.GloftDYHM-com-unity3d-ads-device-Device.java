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
import com.unity3d.ads.log.DeviceLog;
import com.unity3d.ads.misc.Utilities;
import com.unity3d.ads.properties.ClientProperties;
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
    //   0: invokestatic 29	com/unity3d/ads/properties/ClientProperties:getApplicationContext	()Landroid/content/Context;
    //   3: invokevirtual 66	android/content/Context:getPackageCodePath	()Ljava/lang/String;
    //   6: astore_0
    //   7: new 68	java/io/FileInputStream
    //   10: dup
    //   11: new 70	java/io/File
    //   14: dup
    //   15: aload_0
    //   16: invokespecial 73	java/io/File:<init>	(Ljava/lang/String;)V
    //   19: invokespecial 76	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   22: astore_1
    //   23: aload_1
    //   24: invokestatic 82	com/unity3d/ads/misc/Utilities:Sha256	(Ljava/io/InputStream;)Ljava/lang/String;
    //   27: astore_0
    //   28: aload_1
    //   29: ifnull +7 -> 36
    //   32: aload_1
    //   33: invokevirtual 87	java/io/InputStream:close	()V
    //   36: aload_0
    //   37: areturn
    //   38: astore_0
    //   39: aconst_null
    //   40: astore_1
    //   41: aload_1
    //   42: ifnull +7 -> 49
    //   45: aload_1
    //   46: invokevirtual 87	java/io/InputStream:close	()V
    //   49: aload_0
    //   50: athrow
    //   51: astore_1
    //   52: aload_0
    //   53: areturn
    //   54: astore_1
    //   55: goto -6 -> 49
    //   58: astore_0
    //   59: goto -18 -> 41
    // Local variable table:
    //   start	length	slot	name	signature
    //   6	31	0	str1	String
    //   38	15	0	str2	String
    //   58	1	0	localObject	Object
    //   22	24	1	localFileInputStream	java.io.FileInputStream
    //   51	1	1	localIOException1	java.io.IOException
    //   54	1	1	localIOException2	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   7	23	38	finally
    //   32	36	51	java/io/IOException
    //   45	49	54	java/io/IOException
    //   23	28	58	finally
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
    Object localObject = ClientProperties.getApplicationContext().getPackageManager();
    String str = ClientProperties.getApplicationContext().getPackageName();
    try
    {
      localObject = ((PackageManager)localObject).getPackageInfo(str, 64).signatures;
      if ((localObject != null) && (localObject.length >= 1))
      {
        localObject = (X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(localObject[0].toByteArray()));
        localObject = Utilities.toHexString(MessageDigest.getInstance("SHA-1").digest(((X509Certificate)localObject).getEncoded()));
        return localObject;
      }
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
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 7
    //   6: iconst_m1
    //   7: istore_1
    //   8: getstatic 355	com/unity3d/ads/device/Device$1:$SwitchMap$com$unity3d$ads$device$Device$MemoryInfoType	[I
    //   11: aload_0
    //   12: invokevirtual 358	com/unity3d/ads/device/Device$MemoryInfoType:ordinal	()I
    //   15: iaload
    //   16: tableswitch	default:+24->40, 1:+179->195, 2:+184->200
    //   40: new 360	java/io/RandomAccessFile
    //   43: dup
    //   44: ldc_w 362
    //   47: ldc_w 364
    //   50: invokespecial 367	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   53: astore 6
    //   55: aconst_null
    //   56: astore 5
    //   58: iconst_0
    //   59: istore_2
    //   60: iload_2
    //   61: iload_1
    //   62: if_icmpge +17 -> 79
    //   65: aload 6
    //   67: invokevirtual 370	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   70: astore 5
    //   72: iload_2
    //   73: iconst_1
    //   74: iadd
    //   75: istore_2
    //   76: goto -16 -> 60
    //   79: aload 5
    //   81: invokestatic 374	com/unity3d/ads/device/Device:getMemoryValueFromString	(Ljava/lang/String;)J
    //   84: lstore_3
    //   85: aload 6
    //   87: invokevirtual 375	java/io/RandomAccessFile:close	()V
    //   90: lload_3
    //   91: lreturn
    //   92: astore_0
    //   93: ldc_w 377
    //   96: aload_0
    //   97: invokestatic 51	com/unity3d/ads/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   100: lload_3
    //   101: lreturn
    //   102: astore 5
    //   104: aload 7
    //   106: astore 6
    //   108: aload 5
    //   110: astore 7
    //   112: aload 6
    //   114: astore 5
    //   116: new 379	java/lang/StringBuilder
    //   119: dup
    //   120: invokespecial 380	java/lang/StringBuilder:<init>	()V
    //   123: ldc_w 382
    //   126: invokevirtual 386	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   129: aload_0
    //   130: invokevirtual 389	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   133: invokevirtual 392	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   136: aload 7
    //   138: invokestatic 51	com/unity3d/ads/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   141: aload 6
    //   143: invokevirtual 375	java/io/RandomAccessFile:close	()V
    //   146: ldc2_w 252
    //   149: lreturn
    //   150: astore_0
    //   151: ldc_w 377
    //   154: aload_0
    //   155: invokestatic 51	com/unity3d/ads/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   158: goto -12 -> 146
    //   161: astore_0
    //   162: aload 5
    //   164: invokevirtual 375	java/io/RandomAccessFile:close	()V
    //   167: aload_0
    //   168: athrow
    //   169: astore 5
    //   171: ldc_w 377
    //   174: aload 5
    //   176: invokestatic 51	com/unity3d/ads/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
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
  
  public static boolean getNetworkMetered()
  {
    if ((ClientProperties.getApplicationContext() != null) && (Build.VERSION.SDK_INT >= 16))
    {
      ConnectivityManager localConnectivityManager = (ConnectivityManager)ClientProperties.getApplicationContext().getSystemService("connectivity");
      if (localConnectivityManager == null) {
        return false;
      }
      return localConnectivityManager.isActiveNetworkMetered();
    }
    return false;
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
    //   8: new 360	java/io/RandomAccessFile
    //   11: dup
    //   12: ldc_w 478
    //   15: ldc_w 364
    //   18: invokespecial 367	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   21: astore_0
    //   22: aload_0
    //   23: astore_1
    //   24: aload_3
    //   25: ldc_w 480
    //   28: aload_0
    //   29: invokevirtual 370	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   32: invokevirtual 317	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   35: pop
    //   36: aload_0
    //   37: invokevirtual 375	java/io/RandomAccessFile:close	()V
    //   40: new 360	java/io/RandomAccessFile
    //   43: dup
    //   44: ldc_w 482
    //   47: ldc_w 364
    //   50: invokespecial 367	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   53: astore_1
    //   54: aload_1
    //   55: astore_0
    //   56: aload_3
    //   57: ldc_w 484
    //   60: aload_1
    //   61: invokevirtual 370	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   64: invokevirtual 317	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   67: pop
    //   68: aload_1
    //   69: invokevirtual 375	java/io/RandomAccessFile:close	()V
    //   72: aload_3
    //   73: areturn
    //   74: astore_1
    //   75: ldc_w 377
    //   78: aload_1
    //   79: invokestatic 51	com/unity3d/ads/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   82: goto -42 -> 40
    //   85: astore_2
    //   86: aconst_null
    //   87: astore_0
    //   88: aload_0
    //   89: astore_1
    //   90: ldc_w 486
    //   93: aload_2
    //   94: invokestatic 51	com/unity3d/ads/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   97: aload_0
    //   98: invokevirtual 375	java/io/RandomAccessFile:close	()V
    //   101: goto -61 -> 40
    //   104: astore_1
    //   105: ldc_w 377
    //   108: aload_1
    //   109: invokestatic 51	com/unity3d/ads/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   112: goto -72 -> 40
    //   115: astore_0
    //   116: aconst_null
    //   117: astore_1
    //   118: aload_1
    //   119: invokevirtual 375	java/io/RandomAccessFile:close	()V
    //   122: aload_0
    //   123: athrow
    //   124: astore_1
    //   125: ldc_w 377
    //   128: aload_1
    //   129: invokestatic 51	com/unity3d/ads/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   132: goto -10 -> 122
    //   135: astore_0
    //   136: ldc_w 377
    //   139: aload_0
    //   140: invokestatic 51	com/unity3d/ads/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   143: aload_3
    //   144: areturn
    //   145: astore_2
    //   146: aload_0
    //   147: astore_1
    //   148: aload_1
    //   149: astore_0
    //   150: ldc_w 486
    //   153: aload_2
    //   154: invokestatic 51	com/unity3d/ads/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   157: aload_1
    //   158: invokevirtual 375	java/io/RandomAccessFile:close	()V
    //   161: aload_3
    //   162: areturn
    //   163: astore_0
    //   164: ldc_w 377
    //   167: aload_0
    //   168: invokestatic 51	com/unity3d/ads/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   171: aload_3
    //   172: areturn
    //   173: astore_1
    //   174: aload_0
    //   175: invokevirtual 375	java/io/RandomAccessFile:close	()V
    //   178: aload_1
    //   179: athrow
    //   180: astore_0
    //   181: ldc_w 377
    //   184: aload_0
    //   185: invokestatic 51	com/unity3d/ads/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   188: goto -10 -> 178
    //   191: astore_1
    //   192: goto -18 -> 174
    //   195: astore_2
    //   196: goto -48 -> 148
    //   199: astore_0
    //   200: goto -82 -> 118
    //   203: astore_2
    //   204: goto -116 -> 88
    // Local variable table:
    //   start	length	slot	name	signature
    //   21	77	0	localObject1	Object
    //   115	8	0	localObject2	Object
    //   135	12	0	localIOException1	java.io.IOException
    //   149	1	0	localObject3	Object
    //   163	12	0	localIOException2	java.io.IOException
    //   180	5	0	localIOException3	java.io.IOException
    //   199	1	0	localObject4	Object
    //   23	46	1	localObject5	Object
    //   74	5	1	localIOException4	java.io.IOException
    //   89	1	1	localObject6	Object
    //   104	5	1	localIOException5	java.io.IOException
    //   117	2	1	localObject7	Object
    //   124	5	1	localIOException6	java.io.IOException
    //   147	11	1	localIOException7	java.io.IOException
    //   173	6	1	localObject8	Object
    //   191	1	1	localObject9	Object
    //   85	9	2	localIOException8	java.io.IOException
    //   145	9	2	localIOException9	java.io.IOException
    //   195	1	2	localIOException10	java.io.IOException
    //   203	1	2	localIOException11	java.io.IOException
    //   7	165	3	localHashMap	HashMap
    // Exception table:
    //   from	to	target	type
    //   36	40	74	java/io/IOException
    //   8	22	85	java/io/IOException
    //   97	101	104	java/io/IOException
    //   8	22	115	finally
    //   118	122	124	java/io/IOException
    //   68	72	135	java/io/IOException
    //   40	54	145	java/io/IOException
    //   157	161	163	java/io/IOException
    //   40	54	173	finally
    //   174	178	180	java/io/IOException
    //   56	68	191	finally
    //   150	157	191	finally
    //   56	68	195	java/io/IOException
    //   24	36	199	finally
    //   90	97	199	finally
    //   24	36	203	java/io/IOException
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
        bool1 = localIntent.getBooleanExtra("connected", false);
      }
    }
    return bool1;
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
