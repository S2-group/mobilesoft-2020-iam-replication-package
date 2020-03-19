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
    //   31: invokevirtual 89	java/io/InputStream:close	()V
    //   34: aload_1
    //   35: areturn
    //   36: astore_2
    //   37: aload_0
    //   38: astore_1
    //   39: aload_2
    //   40: astore_0
    //   41: goto +4 -> 45
    //   44: astore_0
    //   45: aload_1
    //   46: ifnull +7 -> 53
    //   49: aload_1
    //   50: invokevirtual 89	java/io/InputStream:close	()V
    //   53: aload_0
    //   54: athrow
    //   55: astore_0
    //   56: aload_1
    //   57: areturn
    //   58: astore_1
    //   59: goto -6 -> 53
    // Local variable table:
    //   start	length	slot	name	signature
    //   6	35	0	localObject1	Object
    //   44	10	0	localObject2	Object
    //   55	1	0	localIOException1	java.io.IOException
    //   8	49	1	localObject3	Object
    //   58	1	1	localIOException2	java.io.IOException
    //   36	4	2	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   25	30	36	finally
    //   9	25	44	finally
    //   30	34	55	java/io/IOException
    //   49	53	58	java/io/IOException
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
    if (ClientProperties.getApplicationContext() != null)
    {
      Intent localIntent = ClientProperties.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      if (localIntent != null) {
        return safedk_Intent_getIntExtra_97da7053a853e359d46ad61039c31a36(localIntent, "status", -1);
      }
    }
    return -1;
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
      if ((localObject != null) && (localObject.length > 0))
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
      while (localIterator.hasNext())
      {
        Object localObject = (PackageInfo)localIterator.next();
        HashMap localHashMap = new HashMap();
        if (paramBoolean) {
          localHashMap.put("name", Utilities.Sha256(((PackageInfo)localObject).packageName));
        } else {
          localHashMap.put("name", ((PackageInfo)localObject).packageName);
        }
        if (((PackageInfo)localObject).firstInstallTime > 0L) {
          localHashMap.put("time", Long.valueOf(((PackageInfo)localObject).firstInstallTime));
        }
        localObject = localPackageManager.getInstallerPackageName(((PackageInfo)localObject).packageName);
        if ((localObject != null) && (!((String)localObject).isEmpty())) {
          localHashMap.put("installer", localObject);
        }
        localArrayList.add(localHashMap);
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
    //   0: getstatic 352	com/unity3d/services/core/device/Device$1:$SwitchMap$com$unity3d$services$core$device$Device$MemoryInfoType	[I
    //   3: aload_0
    //   4: invokevirtual 355	com/unity3d/services/core/device/Device$MemoryInfoType:ordinal	()I
    //   7: iaload
    //   8: tableswitch	default:+24->32, 1:+34->42, 2:+29->37
    //   32: iconst_m1
    //   33: istore_1
    //   34: goto +10 -> 44
    //   37: iconst_2
    //   38: istore_1
    //   39: goto +5 -> 44
    //   42: iconst_1
    //   43: istore_1
    //   44: aconst_null
    //   45: astore 5
    //   47: aconst_null
    //   48: astore 7
    //   50: aconst_null
    //   51: astore 8
    //   53: new 357	java/io/RandomAccessFile
    //   56: dup
    //   57: ldc_w 359
    //   60: ldc_w 361
    //   63: invokespecial 364	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   66: astore 6
    //   68: iconst_0
    //   69: istore_2
    //   70: aload 8
    //   72: astore 5
    //   74: iload_2
    //   75: iload_1
    //   76: if_icmpge +17 -> 93
    //   79: aload 6
    //   81: invokevirtual 367	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   84: astore 5
    //   86: iload_2
    //   87: iconst_1
    //   88: iadd
    //   89: istore_2
    //   90: goto -16 -> 74
    //   93: aload 5
    //   95: invokestatic 371	com/unity3d/services/core/device/Device:getMemoryValueFromString	(Ljava/lang/String;)J
    //   98: lstore_3
    //   99: aload 6
    //   101: invokevirtual 372	java/io/RandomAccessFile:close	()V
    //   104: lload_3
    //   105: lreturn
    //   106: astore_0
    //   107: ldc_w 374
    //   110: aload_0
    //   111: invokestatic 53	com/unity3d/services/core/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   114: lload_3
    //   115: lreturn
    //   116: astore_0
    //   117: goto +52 -> 169
    //   120: astore 5
    //   122: aload 7
    //   124: astore 6
    //   126: aload 5
    //   128: astore 7
    //   130: aload 6
    //   132: astore 5
    //   134: ldc_w 376
    //   137: aload_0
    //   138: invokestatic 379	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   141: invokevirtual 382	java/lang/String:concat	(Ljava/lang/String;)Ljava/lang/String;
    //   144: aload 7
    //   146: invokestatic 53	com/unity3d/services/core/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   149: aload 6
    //   151: invokevirtual 372	java/io/RandomAccessFile:close	()V
    //   154: goto +11 -> 165
    //   157: astore_0
    //   158: ldc_w 374
    //   161: aload_0
    //   162: invokestatic 53	com/unity3d/services/core/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   165: ldc2_w 251
    //   168: lreturn
    //   169: aload 5
    //   171: invokevirtual 372	java/io/RandomAccessFile:close	()V
    //   174: goto +13 -> 187
    //   177: astore 5
    //   179: ldc_w 374
    //   182: aload 5
    //   184: invokestatic 53	com/unity3d/services/core/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   187: aload_0
    //   188: athrow
    //   189: astore_0
    //   190: aload 6
    //   192: astore 5
    //   194: goto -25 -> 169
    //   197: astore 7
    //   199: goto -69 -> 130
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	202	0	paramMemoryInfoType	Device.MemoryInfoType
    //   33	44	1	i	int
    //   69	21	2	j	int
    //   98	17	3	l	long
    //   45	49	5	localObject1	Object
    //   120	7	5	localIOException1	java.io.IOException
    //   132	38	5	localObject2	Object
    //   177	6	5	localIOException2	java.io.IOException
    //   192	1	5	localObject3	Object
    //   66	125	6	localObject4	Object
    //   48	97	7	localIOException3	java.io.IOException
    //   197	1	7	localIOException4	java.io.IOException
    //   51	20	8	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   99	104	106	java/io/IOException
    //   53	68	116	finally
    //   134	149	116	finally
    //   53	68	120	java/io/IOException
    //   149	154	157	java/io/IOException
    //   169	174	177	java/io/IOException
    //   79	86	189	finally
    //   93	99	189	finally
    //   79	86	197	java/io/IOException
    //   93	99	197	java/io/IOException
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
    //   0: new 303	java/util/HashMap
    //   3: dup
    //   4: invokespecial 304	java/util/HashMap:<init>	()V
    //   7: astore 4
    //   9: aconst_null
    //   10: astore_3
    //   11: aconst_null
    //   12: astore_0
    //   13: new 357	java/io/RandomAccessFile
    //   16: dup
    //   17: ldc_w 468
    //   20: ldc_w 361
    //   23: invokespecial 364	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   26: astore_1
    //   27: aload 4
    //   29: ldc_w 470
    //   32: aload_1
    //   33: invokevirtual 367	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   36: invokevirtual 316	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   39: pop
    //   40: aload_1
    //   41: invokevirtual 372	java/io/RandomAccessFile:close	()V
    //   44: aload 4
    //   46: areturn
    //   47: astore_2
    //   48: aload_1
    //   49: astore_0
    //   50: aload_2
    //   51: astore_1
    //   52: goto +43 -> 95
    //   55: astore_0
    //   56: aload_0
    //   57: astore_2
    //   58: goto +10 -> 68
    //   61: astore_1
    //   62: goto +33 -> 95
    //   65: astore_2
    //   66: aload_3
    //   67: astore_1
    //   68: aload_1
    //   69: astore_0
    //   70: ldc_w 472
    //   73: aload_2
    //   74: invokestatic 53	com/unity3d/services/core/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   77: aload_1
    //   78: invokevirtual 372	java/io/RandomAccessFile:close	()V
    //   81: aload 4
    //   83: areturn
    //   84: astore_0
    //   85: ldc_w 374
    //   88: aload_0
    //   89: invokestatic 53	com/unity3d/services/core/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   92: aload 4
    //   94: areturn
    //   95: aload_0
    //   96: invokevirtual 372	java/io/RandomAccessFile:close	()V
    //   99: goto +11 -> 110
    //   102: astore_0
    //   103: ldc_w 374
    //   106: aload_0
    //   107: invokestatic 53	com/unity3d/services/core/log/DeviceLog:exception	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   110: aload_1
    //   111: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   12	38	0	localObject1	Object
    //   55	2	0	localIOException1	java.io.IOException
    //   69	1	0	localObject2	Object
    //   84	12	0	localIOException2	java.io.IOException
    //   102	5	0	localIOException3	java.io.IOException
    //   26	26	1	localObject3	Object
    //   61	1	1	localObject4	Object
    //   67	44	1	localObject5	Object
    //   47	4	2	localObject6	Object
    //   57	1	2	localIOException4	java.io.IOException
    //   65	9	2	localIOException5	java.io.IOException
    //   10	57	3	localObject7	Object
    //   7	86	4	localHashMap	HashMap
    // Exception table:
    //   from	to	target	type
    //   27	40	47	finally
    //   27	40	55	java/io/IOException
    //   13	27	61	finally
    //   70	77	61	finally
    //   13	27	65	java/io/IOException
    //   40	44	84	java/io/IOException
    //   77	81	84	java/io/IOException
    //   95	99	102	java/io/IOException
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
    if (ClientProperties.getApplicationContext() != null) {
      return Settings.System.getInt(ClientProperties.getApplicationContext().getContentResolver(), "screen_brightness", -1);
    }
    return -1;
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
    Object localObject;
    if (ClientProperties.getApplicationContext() != null) {
      localObject = ClientProperties.getApplicationContext().getPackageManager();
    }
    try
    {
      localObject = ((PackageManager)localObject).getPackageInfo(paramString, 0);
      if ((localObject != null) && (((PackageInfo)localObject).packageName != null))
      {
        boolean bool = paramString.equals(((PackageInfo)localObject).packageName);
        if (bool) {
          return true;
        }
      }
      return false;
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
    if (ClientProperties.getApplicationContext() != null)
    {
      Intent localIntent = ClientProperties.getApplicationContext().registerReceiver(null, new IntentFilter("android.hardware.usb.action.USB_STATE"));
      if (localIntent != null) {
        return safedk_Intent_getBooleanExtra_e6068b21c912ff007af22297aa28d38e(localIntent, "connected", false);
      }
    }
    return false;
  }
  
  public static boolean isUsingWifi()
  {
    if (ClientProperties.getApplicationContext() != null)
    {
      ConnectivityManager localConnectivityManager = (ConnectivityManager)ClientProperties.getApplicationContext().getSystemService("connectivity");
      if (localConnectivityManager == null) {
        return false;
      }
      TelephonyManager localTelephonyManager = (TelephonyManager)ClientProperties.getApplicationContext().getSystemService("phone");
      NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
      if ((localNetworkInfo != null) && (localConnectivityManager.getBackgroundDataSetting()) && (localConnectivityManager.getActiveNetworkInfo().isConnected()))
      {
        if (localTelephonyManager == null) {
          return false;
        }
        return (localNetworkInfo.getType() == 1) && (localNetworkInfo.isConnected());
      }
      return false;
    }
    return false;
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
    for (;;)
    {
      try
      {
        int i = Settings.Global.getInt(ClientProperties.getApplicationContext().getContentResolver(), "adb_enabled", 0);
        bool = true;
        if (1 == i) {
          return Boolean.valueOf(bool);
        }
      }
      catch (Exception localException)
      {
        DeviceLog.exception("Problems fetching adb enabled status", localException);
        return null;
      }
      boolean bool = false;
    }
  }
  
  private static Boolean oldAdbStatus()
  {
    for (;;)
    {
      try
      {
        int i = Settings.Secure.getInt(ClientProperties.getApplicationContext().getContentResolver(), "adb_enabled", 0);
        bool = true;
        if (1 == i) {
          return Boolean.valueOf(bool);
        }
      }
      catch (Exception localException)
      {
        DeviceLog.exception("Problems fetching adb enabled status", localException);
        return null;
      }
      boolean bool = false;
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
    String[] arrayOfString = System.getenv("PATH").split(":");
    int k = arrayOfString.length;
    int i = 0;
    while (i < k)
    {
      Object localObject = new File(arrayOfString[i]);
      if ((((File)localObject).exists()) && (((File)localObject).isDirectory()))
      {
        localObject = ((File)localObject).listFiles();
        if (localObject != null)
        {
          int m = localObject.length;
          int j = 0;
          while (j < m)
          {
            if (localObject[j].getName().equals(paramString)) {
              return true;
            }
            j += 1;
          }
        }
      }
      i += 1;
    }
    return false;
  }
}
