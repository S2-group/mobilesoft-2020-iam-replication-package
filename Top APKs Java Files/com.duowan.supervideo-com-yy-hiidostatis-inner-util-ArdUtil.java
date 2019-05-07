package com.yy.hiidostatis.inner.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.LocaleList;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.CellLocation;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import com.yy.hiidostatis.inner.util.log.L;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

public class ArdUtil
{
  private static final int NET_2G = 1;
  private static final int NET_3G = 2;
  private static final int NET_4G = 4;
  private static final int NET_UNKNOWN = 0;
  private static final int NET_WIFI = 3;
  private static String mAndroidId;
  private static String mCpuAbi;
  private static String mCpuName;
  private static int mCpuNum;
  private static String mImei;
  private static String mImsi;
  private static String mLang;
  private static String mMacAddress;
  private static String mMacAddressV23;
  private static String mMaxpuFreq;
  private static String mNtm;
  private static String mOS;
  private static String mScreenResolution;
  private static long mTotal;
  private static long mTotalExternalStorgeSize = 0L;
  private static long mTotalInternalStorgeSize;
  private static long mTotalMem;
  private static int mVer = -1;
  private static String mVersionName = null;
  
  static
  {
    mAndroidId = null;
    mMacAddress = null;
    mMacAddressV23 = null;
    mImei = null;
    mOS = null;
    mScreenResolution = null;
    mTotal = 0L;
    mMaxpuFreq = null;
    mCpuNum = 0;
    mCpuAbi = null;
    mTotalMem = 0L;
    mTotalInternalStorgeSize = 0L;
  }
  
  public ArdUtil() {}
  
  public static boolean checkPermissions(Context paramContext, String paramString)
  {
    try
    {
      if (Build.VERSION.SDK_INT >= 23)
      {
        if (((Integer)Class.forName("android.content.Context").getMethod("checkSelfPermission", new Class[] { String.class }).invoke(paramContext, new Object[] { paramString })).intValue() != 0) {
          break label87;
        }
        return true;
      }
      int i = paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName());
      if (i == 0) {
        return true;
      }
    }
    catch (Throwable paramContext)
    {
      L.warn(ArdUtil.class, "checkPermissions Throwable: %s", new Object[] { paramContext });
    }
    return false;
    label87:
    return false;
  }
  
  public static String getAndroidId(Context paramContext)
  {
    try
    {
      if (mAndroidId != null) {
        return mAndroidId;
      }
      mAndroidId = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        L.warn(ArdUtil.class, "Exception when getAndroidId %s", new Object[] { paramContext });
      }
    }
    return mAndroidId;
  }
  
  public static long getAvailExternalStorgeSize()
  {
    long l = 0L;
    try
    {
      if (Environment.getExternalStorageState().equals("mounted"))
      {
        StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        l = localStatFs.getBlockSize();
        l = localStatFs.getAvailableBlocks() * l / 1024L;
      }
      return l;
    }
    catch (Throwable localThrowable)
    {
      L.warn(ArdUtil.class, "getAvailExternalStorgeSize exception . %s", new Object[] { localThrowable });
    }
    return 0L;
  }
  
  public static long getAvailInternalStorgeSize()
  {
    try
    {
      StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
      long l = localStatFs.getBlockSize();
      l = localStatFs.getAvailableBlocks() * l / 1024L;
      return l;
    }
    catch (Throwable localThrowable)
    {
      L.warn(ArdUtil.class, "getAvailInternalStorgeSize exception . %s", new Object[] { localThrowable });
    }
    return 0L;
  }
  
  public static long getAvailMemory(Context paramContext)
  {
    try
    {
      ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
      ((ActivityManager)paramContext.getSystemService("activity")).getMemoryInfo(localMemoryInfo);
      long l = localMemoryInfo.availMem / 1024L;
      return l;
    }
    catch (Throwable paramContext)
    {
      L.warn(ArdUtil.class, "getAvailMemory exception . %s", new Object[] { paramContext });
    }
    return 0L;
  }
  
  public static int getAvailableProcessors()
  {
    try
    {
      int i = Runtime.getRuntime().availableProcessors();
      return i;
    }
    catch (Throwable localThrowable)
    {
      L.warn(ArdUtil.class, "getAvailableProcessors exception . %s", new Object[] { localThrowable });
    }
    return -1;
  }
  
  public static String getBluetoothMac(Context paramContext)
  {
    return "";
  }
  
  public static CellLocation getCellId(Context paramContext)
  {
    return null;
  }
  
  public static String getCellIp()
  {
    return null;
  }
  
  /* Error */
  public static String getCpuAbi()
  {
    // Byte code:
    //   0: getstatic 65	com/yy/hiidostatis/inner/util/ArdUtil:mCpuAbi	Ljava/lang/String;
    //   3: invokestatic 244	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   6: ifne +9 -> 15
    //   9: getstatic 65	com/yy/hiidostatis/inner/util/ArdUtil:mCpuAbi	Ljava/lang/String;
    //   12: astore_1
    //   13: aload_1
    //   14: areturn
    //   15: getstatic 84	android/os/Build$VERSION:SDK_INT	I
    //   18: bipush 21
    //   20: if_icmpge +13 -> 33
    //   23: getstatic 249	android/os/Build:CPU_ABI	Ljava/lang/String;
    //   26: astore_0
    //   27: aload_0
    //   28: putstatic 65	com/yy/hiidostatis/inner/util/ArdUtil:mCpuAbi	Ljava/lang/String;
    //   31: aload_0
    //   32: areturn
    //   33: new 251	java/io/FileReader
    //   36: dup
    //   37: ldc -3
    //   39: invokespecial 254	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   42: astore_0
    //   43: new 256	java/io/BufferedReader
    //   46: dup
    //   47: aload_0
    //   48: invokespecial 259	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   51: astore 5
    //   53: aload_0
    //   54: astore_2
    //   55: aload 5
    //   57: astore_1
    //   58: aload 5
    //   60: invokevirtual 262	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   63: ldc_w 264
    //   66: iconst_2
    //   67: invokevirtual 268	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   70: iconst_1
    //   71: aaload
    //   72: astore_3
    //   73: aload_0
    //   74: astore_2
    //   75: aload 5
    //   77: astore_1
    //   78: aload_3
    //   79: putstatic 65	com/yy/hiidostatis/inner/util/ArdUtil:mCpuAbi	Ljava/lang/String;
    //   82: aload 5
    //   84: ifnull +8 -> 92
    //   87: aload 5
    //   89: invokevirtual 271	java/io/BufferedReader:close	()V
    //   92: aload_3
    //   93: astore_1
    //   94: aload_0
    //   95: ifnull -82 -> 13
    //   98: aload_0
    //   99: invokevirtual 272	java/io/FileReader:close	()V
    //   102: aload_3
    //   103: areturn
    //   104: astore_0
    //   105: aload_3
    //   106: areturn
    //   107: astore_3
    //   108: aconst_null
    //   109: astore 4
    //   111: aconst_null
    //   112: astore_0
    //   113: aload 4
    //   115: astore_2
    //   116: aload_0
    //   117: astore_1
    //   118: ldc 2
    //   120: ldc_w 274
    //   123: iconst_1
    //   124: anewarray 4	java/lang/Object
    //   127: dup
    //   128: iconst_0
    //   129: aload_3
    //   130: aastore
    //   131: invokestatic 136	com/yy/hiidostatis/inner/util/log/L:warn	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   134: aload_0
    //   135: ifnull +7 -> 142
    //   138: aload_0
    //   139: invokevirtual 271	java/io/BufferedReader:close	()V
    //   142: aload 4
    //   144: ifnull +8 -> 152
    //   147: aload 4
    //   149: invokevirtual 272	java/io/FileReader:close	()V
    //   152: aconst_null
    //   153: areturn
    //   154: astore_3
    //   155: aconst_null
    //   156: astore_0
    //   157: aconst_null
    //   158: astore_1
    //   159: aload_1
    //   160: ifnull +7 -> 167
    //   163: aload_1
    //   164: invokevirtual 271	java/io/BufferedReader:close	()V
    //   167: aload_0
    //   168: ifnull +7 -> 175
    //   171: aload_0
    //   172: invokevirtual 272	java/io/FileReader:close	()V
    //   175: aload_3
    //   176: athrow
    //   177: astore_0
    //   178: goto -3 -> 175
    //   181: astore_3
    //   182: aconst_null
    //   183: astore_1
    //   184: goto -25 -> 159
    //   187: astore_3
    //   188: aload_2
    //   189: astore_0
    //   190: goto -31 -> 159
    //   193: astore_0
    //   194: goto -42 -> 152
    //   197: astore_3
    //   198: aconst_null
    //   199: astore_1
    //   200: aload_0
    //   201: astore 4
    //   203: aload_1
    //   204: astore_0
    //   205: goto -92 -> 113
    //   208: astore_3
    //   209: aload_0
    //   210: astore 4
    //   212: aload 5
    //   214: astore_0
    //   215: goto -102 -> 113
    // Local variable table:
    //   start	length	slot	name	signature
    //   26	73	0	localObject1	Object
    //   104	1	0	localIOException1	java.io.IOException
    //   112	60	0	localObject2	Object
    //   177	1	0	localIOException2	java.io.IOException
    //   189	1	0	localObject3	Object
    //   193	8	0	localIOException3	java.io.IOException
    //   204	11	0	localObject4	Object
    //   12	192	1	localObject5	Object
    //   54	135	2	localObject6	Object
    //   72	34	3	str	String
    //   107	23	3	localThrowable1	Throwable
    //   154	22	3	localObject7	Object
    //   181	1	3	localObject8	Object
    //   187	1	3	localObject9	Object
    //   197	1	3	localThrowable2	Throwable
    //   208	1	3	localThrowable3	Throwable
    //   109	102	4	localObject10	Object
    //   51	162	5	localBufferedReader	BufferedReader
    // Exception table:
    //   from	to	target	type
    //   87	92	104	java/io/IOException
    //   98	102	104	java/io/IOException
    //   33	43	107	java/lang/Throwable
    //   33	43	154	finally
    //   163	167	177	java/io/IOException
    //   171	175	177	java/io/IOException
    //   43	53	181	finally
    //   58	73	187	finally
    //   78	82	187	finally
    //   118	134	187	finally
    //   138	142	193	java/io/IOException
    //   147	152	193	java/io/IOException
    //   43	53	197	java/lang/Throwable
    //   58	73	208	java/lang/Throwable
    //   78	82	208	java/lang/Throwable
  }
  
  public static String getCpuName()
  {
    if (!TextUtils.isEmpty(mCpuName)) {
      return mCpuName;
    }
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"));
      String[] arrayOfString = localBufferedReader.readLine().split(":\\s+", 2);
      localBufferedReader.close();
      localBufferedReader = arrayOfString[1];
      mCpuName = localBufferedReader;
      return localBufferedReader;
    }
    catch (Throwable localThrowable)
    {
      L.warn(ArdUtil.class, "getCpuName exception: %s", new Object[] { localThrowable });
    }
    return null;
  }
  
  public static int getCpuNum()
  {
    if (mCpuNum != 0) {
      return mCpuNum;
    }
    try
    {
      int i = new File("/sys/devices/system/cpu/").listFiles(new FileFilter()
      {
        public boolean accept(File paramAnonymousFile)
        {
          return Pattern.matches("cpu[0-9]", paramAnonymousFile.getName());
        }
      }).length;
      mCpuNum = i;
      return i;
    }
    catch (Throwable localThrowable)
    {
      L.warn(ArdUtil.class, "getCpuNum exception: %s", new Object[] { localThrowable });
      mCpuNum = 1;
    }
    return 1;
  }
  
  public static int getCurrAppUid(Context paramContext)
  {
    try
    {
      Iterator localIterator = paramContext.getPackageManager().getInstalledApplications(0).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (localApplicationInfo.packageName.equals(paramContext.getPackageName()))
        {
          int i = localApplicationInfo.uid;
          return i;
        }
      }
    }
    catch (Throwable paramContext)
    {
      L.warn(ArdUtil.class, "getCurrAppUid exception: %s", new Object[] { paramContext });
    }
    return -1;
  }
  
  public static int getDeviceOrientation(Context paramContext)
  {
    try
    {
      int i = paramContext.getResources().getConfiguration().orientation;
      if (i == 2) {
        return 1;
      }
      if (i == 1) {
        return 0;
      }
    }
    catch (Throwable paramContext)
    {
      L.warn(ArdUtil.class, "getDeviceOrientation exception . %s", new Object[] { paramContext });
    }
    return 0;
  }
  
  public static String getImei(Context paramContext)
  {
    if (mImei != null) {
      return mImei;
    }
    return null;
  }
  
  public static String getImsi(Context paramContext)
  {
    if (mImsi != null) {
      return mImsi;
    }
    return mImsi;
  }
  
  public static String getLang()
  {
    if (mLang != null) {
      return mLang;
    }
    if (Build.VERSION.SDK_INT >= 24) {}
    for (;;)
    {
      try
      {
        Object localObject = LocaleList.getDefault().get(0);
        localObject = String.format("%s-%s", new Object[] { ((Locale)localObject).getLanguage(), ((Locale)localObject).getCountry() });
        mLang = (String)localObject;
        return localObject;
      }
      catch (Throwable localThrowable)
      {
        localLocale = Locale.getDefault();
        continue;
      }
      Locale localLocale = Locale.getDefault();
    }
  }
  
  public static String getMacAddr(Context paramContext)
  {
    if (mMacAddress != null) {
      return mMacAddress;
    }
    return mMacAddress;
  }
  
  /* Error */
  @SuppressLint({"NewApi"})
  public static String getMacAddr2()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore 4
    //   5: getstatic 84	android/os/Build$VERSION:SDK_INT	I
    //   8: bipush 9
    //   10: if_icmplt +161 -> 171
    //   13: invokestatic 385	java/net/NetworkInterface:getNetworkInterfaces	()Ljava/util/Enumeration;
    //   16: astore 5
    //   18: aconst_null
    //   19: astore_2
    //   20: aload 5
    //   22: invokeinterface 390 1 0
    //   27: ifeq +150 -> 177
    //   30: aload 5
    //   32: invokeinterface 393 1 0
    //   37: checkcast 381	java/net/NetworkInterface
    //   40: astore_3
    //   41: aload_3
    //   42: invokevirtual 396	java/net/NetworkInterface:getName	()Ljava/lang/String;
    //   45: ldc_w 398
    //   48: invokevirtual 402	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   51: ifeq -31 -> 20
    //   54: aload_3
    //   55: invokevirtual 406	java/net/NetworkInterface:getHardwareAddress	()[B
    //   58: astore_3
    //   59: aload_3
    //   60: ifnull -40 -> 20
    //   63: aload_3
    //   64: arraylength
    //   65: ifeq -45 -> 20
    //   68: new 408	java/lang/StringBuilder
    //   71: dup
    //   72: invokespecial 409	java/lang/StringBuilder:<init>	()V
    //   75: astore 4
    //   77: aload_3
    //   78: arraylength
    //   79: istore_1
    //   80: iconst_0
    //   81: istore_0
    //   82: iload_0
    //   83: iload_1
    //   84: if_icmpge +35 -> 119
    //   87: aload 4
    //   89: ldc_w 411
    //   92: iconst_1
    //   93: anewarray 4	java/lang/Object
    //   96: dup
    //   97: iconst_0
    //   98: aload_3
    //   99: iload_0
    //   100: baload
    //   101: invokestatic 417	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
    //   104: aastore
    //   105: invokestatic 371	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   108: invokevirtual 421	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   111: pop
    //   112: iload_0
    //   113: iconst_1
    //   114: iadd
    //   115: istore_0
    //   116: goto -34 -> 82
    //   119: aload 4
    //   121: invokevirtual 424	java/lang/StringBuilder:length	()I
    //   124: ifle +16 -> 140
    //   127: aload 4
    //   129: aload 4
    //   131: invokevirtual 424	java/lang/StringBuilder:length	()I
    //   134: iconst_1
    //   135: isub
    //   136: invokevirtual 428	java/lang/StringBuilder:deleteCharAt	(I)Ljava/lang/StringBuilder;
    //   139: pop
    //   140: aload 4
    //   142: invokevirtual 431	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   145: astore_3
    //   146: aload_3
    //   147: astore_2
    //   148: goto -128 -> 20
    //   151: astore_3
    //   152: aload 4
    //   154: astore_2
    //   155: ldc 2
    //   157: ldc_w 433
    //   160: iconst_1
    //   161: anewarray 4	java/lang/Object
    //   164: dup
    //   165: iconst_0
    //   166: aload_3
    //   167: aastore
    //   168: invokestatic 136	com/yy/hiidostatis/inner/util/log/L:warn	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   171: aload_2
    //   172: areturn
    //   173: astore_3
    //   174: goto -19 -> 155
    //   177: aload_2
    //   178: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   81	35	0	i	int
    //   79	6	1	j	int
    //   1	177	2	localObject1	Object
    //   40	107	3	localObject2	Object
    //   151	16	3	localThrowable1	Throwable
    //   173	1	3	localThrowable2	Throwable
    //   3	150	4	localStringBuilder	StringBuilder
    //   16	15	5	localEnumeration	java.util.Enumeration
    // Exception table:
    //   from	to	target	type
    //   5	18	151	java/lang/Throwable
    //   20	59	173	java/lang/Throwable
    //   63	80	173	java/lang/Throwable
    //   87	112	173	java/lang/Throwable
    //   119	140	173	java/lang/Throwable
    //   140	146	173	java/lang/Throwable
  }
  
  public static String getMacAddrV23(Context paramContext)
  {
    if (isValidMac(mMacAddressV23)) {
      return mMacAddressV23;
    }
    mMacAddressV23 = getMacAddr(paramContext);
    return mMacAddressV23;
  }
  
  public static String getMaxCpuFreq()
  {
    if (!TextUtils.isEmpty(mMaxpuFreq)) {
      return mMaxpuFreq;
    }
    Object localObject = new StringBuilder("");
    try
    {
      FileInputStream localFileInputStream = new FileInputStream("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
      byte[] arrayOfByte = new byte[24];
      while (localFileInputStream.read(arrayOfByte) != -1)
      {
        ((StringBuilder)localObject).append(new String(arrayOfByte));
        continue;
        localObject = ((StringBuilder)localObject).toString().trim();
      }
    }
    catch (Throwable localThrowable)
    {
      L.warn(ArdUtil.class, "getMaxCpuFreq exception: %s", new Object[] { localThrowable });
    }
    for (;;)
    {
      mMaxpuFreq = (String)localObject;
      return localObject;
      localThrowable.close();
    }
  }
  
  public static String getMetaDataParam(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (Util.empty(paramString))) {
      return "";
    }
    try
    {
      paramContext = paramContext.getPackageManager().getApplicationInfo(paramContext.getPackageName(), 128);
      if (paramContext != null)
      {
        paramContext = paramContext.metaData;
        if (paramContext != null)
        {
          paramContext = paramContext.get(paramString);
          if (paramContext != null)
          {
            L.debug(ArdUtil.class, "meta data key[%s] value is %s", new Object[] { paramString, paramContext });
            paramContext = paramContext + "";
            return paramContext;
          }
        }
      }
    }
    catch (Throwable paramContext)
    {
      L.warn(ArdUtil.class, "read meta-data key[%s] from AndroidManifest.xml Exception.%s", new Object[] { paramString, paramContext });
    }
    return "";
  }
  
  public static int getNetworkType(Context paramContext)
  {
    int i = 1;
    if (0 == 0)
    {
      i = 0;
      return i;
    }
    throw new NullPointerException();
  }
  
  public static int getNetworkTypeNew(Context paramContext)
  {
    if (0 == 0) {
      return 0;
    }
    throw new NullPointerException();
  }
  
  /* Error */
  public static String getNtm(Context paramContext)
  {
    // Byte code:
    //   0: getstatic 499	com/yy/hiidostatis/inner/util/ArdUtil:mNtm	Ljava/lang/String;
    //   3: ifnull +7 -> 10
    //   6: getstatic 499	com/yy/hiidostatis/inner/util/ArdUtil:mNtm	Ljava/lang/String;
    //   9: areturn
    //   10: aload_0
    //   11: ldc_w 501
    //   14: invokevirtual 206	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   17: checkcast 503	android/telephony/TelephonyManager
    //   20: astore_0
    //   21: aload_0
    //   22: ifnull +168 -> 190
    //   25: aload_0
    //   26: invokevirtual 506	android/telephony/TelephonyManager:getSimOperator	()Ljava/lang/String;
    //   29: astore_2
    //   30: aload_2
    //   31: astore_1
    //   32: aload_2
    //   33: astore_3
    //   34: aload_2
    //   35: invokestatic 469	com/yy/hiidostatis/inner/util/Util:empty	(Ljava/lang/String;)Z
    //   38: ifne +88 -> 126
    //   41: aload_2
    //   42: astore_3
    //   43: aload_2
    //   44: ldc_w 508
    //   47: invokevirtual 511	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   50: astore_1
    //   51: aload_2
    //   52: astore_3
    //   53: aload_1
    //   54: arraylength
    //   55: ifle +77 -> 132
    //   58: aload_2
    //   59: astore_3
    //   60: aload_1
    //   61: iconst_0
    //   62: aaload
    //   63: invokestatic 469	com/yy/hiidostatis/inner/util/Util:empty	(Ljava/lang/String;)Z
    //   66: ifne +66 -> 132
    //   69: aload_1
    //   70: iconst_0
    //   71: aaload
    //   72: astore_0
    //   73: aload_0
    //   74: astore_3
    //   75: aload_0
    //   76: invokevirtual 512	java/lang/String:length	()I
    //   79: iconst_5
    //   80: if_icmpeq +16 -> 96
    //   83: aload_0
    //   84: astore_1
    //   85: aload_0
    //   86: astore_3
    //   87: aload_0
    //   88: invokevirtual 512	java/lang/String:length	()I
    //   91: bipush 6
    //   93: if_icmpne +33 -> 126
    //   96: aload_0
    //   97: astore_3
    //   98: ldc_w 514
    //   101: iconst_2
    //   102: anewarray 4	java/lang/Object
    //   105: dup
    //   106: iconst_0
    //   107: aload_0
    //   108: iconst_0
    //   109: iconst_3
    //   110: invokevirtual 518	java/lang/String:substring	(II)Ljava/lang/String;
    //   113: aastore
    //   114: dup
    //   115: iconst_1
    //   116: aload_0
    //   117: iconst_3
    //   118: invokevirtual 521	java/lang/String:substring	(I)Ljava/lang/String;
    //   121: aastore
    //   122: invokestatic 371	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   125: astore_1
    //   126: aload_1
    //   127: putstatic 499	com/yy/hiidostatis/inner/util/ArdUtil:mNtm	Ljava/lang/String;
    //   130: aload_1
    //   131: areturn
    //   132: aload_2
    //   133: astore_0
    //   134: aload_2
    //   135: astore_3
    //   136: aload_1
    //   137: arraylength
    //   138: iconst_2
    //   139: if_icmpne -66 -> 73
    //   142: aload_2
    //   143: astore_0
    //   144: aload_2
    //   145: astore_3
    //   146: aload_1
    //   147: iconst_1
    //   148: aaload
    //   149: invokestatic 469	com/yy/hiidostatis/inner/util/Util:empty	(Ljava/lang/String;)Z
    //   152: ifne -79 -> 73
    //   155: aload_1
    //   156: iconst_1
    //   157: aaload
    //   158: astore_0
    //   159: goto -86 -> 73
    //   162: astore_0
    //   163: aconst_null
    //   164: astore_1
    //   165: ldc 2
    //   167: ldc_w 523
    //   170: iconst_1
    //   171: anewarray 4	java/lang/Object
    //   174: dup
    //   175: iconst_0
    //   176: aload_0
    //   177: aastore
    //   178: invokestatic 136	com/yy/hiidostatis/inner/util/log/L:warn	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   181: goto -55 -> 126
    //   184: astore_0
    //   185: aload_3
    //   186: astore_1
    //   187: goto -22 -> 165
    //   190: aconst_null
    //   191: astore_1
    //   192: goto -66 -> 126
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	195	0	paramContext	Context
    //   31	161	1	localObject1	Object
    //   29	116	2	str	String
    //   33	153	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   10	21	162	java/lang/Throwable
    //   25	30	162	java/lang/Throwable
    //   34	41	184	java/lang/Throwable
    //   43	51	184	java/lang/Throwable
    //   53	58	184	java/lang/Throwable
    //   60	69	184	java/lang/Throwable
    //   75	83	184	java/lang/Throwable
    //   87	96	184	java/lang/Throwable
    //   98	126	184	java/lang/Throwable
    //   136	142	184	java/lang/Throwable
    //   146	155	184	java/lang/Throwable
  }
  
  public static String getOS()
  {
    if (mOS != null) {
      return mOS;
    }
    String str = String.format("Android%s", new Object[] { Build.VERSION.RELEASE });
    mOS = str;
    return str;
  }
  
  public static String getPackageName(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageName();
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      L.warn(ArdUtil.class, "Failed to read package Name.", new Object[0]);
    }
    return "";
  }
  
  public static String getSceneMode(Context paramContext)
  {
    try
    {
      paramContext = (AudioManager)paramContext.getSystemService("audio");
      if (paramContext == null) {
        break label47;
      }
      i = paramContext.getRingerMode();
    }
    catch (Throwable paramContext)
    {
      L.warn(ArdUtil.class, "getSceneMode exception . %s", new Object[] { paramContext });
      break label76;
      label47:
      int i = -1;
      switch (i)
      {
      case 2: 
      default: 
        label76:
        return "";
      }
    }
    return "normal";
    return "silent";
    return "vibrate";
  }
  
  public static int getScreenBrightness(Context paramContext)
  {
    try
    {
      int i = Settings.System.getInt(paramContext.getContentResolver(), "screen_brightness");
      return i;
    }
    catch (Throwable paramContext)
    {
      L.warn(ArdUtil.class, "getScreenBrightness exception . %s", new Object[] { paramContext });
    }
    return 0;
  }
  
  public static String getScreenResolution(Context paramContext)
  {
    if (mScreenResolution != null) {
      return mScreenResolution;
    }
    try
    {
      Object localObject = (WindowManager)paramContext.getSystemService("window");
      if (localObject == null)
      {
        mScreenResolution = "";
        return mScreenResolution;
      }
      paramContext = new Point();
      localObject = ((WindowManager)localObject).getDefaultDisplay();
      paramContext.x = ((Display)localObject).getWidth();
      paramContext.y = ((Display)localObject).getHeight();
      mScreenResolution = paramContext.x + "x" + paramContext.y;
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        L.warn(ArdUtil.class, "exception on getScreenResolution info: %s", new Object[] { paramContext });
      }
    }
    return mScreenResolution;
  }
  
  public static String getSjm(Context paramContext)
  {
    return Build.MODEL;
  }
  
  public static String getSjp(Context paramContext)
  {
    return Build.MANUFACTURER;
  }
  
  public static String getTimeZone()
  {
    int j = TimeZone.getDefault().getRawOffset() / 60000;
    char c = '+';
    int i = j;
    if (j < 0)
    {
      c = '-';
      i = -j;
    }
    return "GMT" + c + i / 60;
  }
  
  public static long getTotalExternalStorgeSize()
  {
    if (mTotalExternalStorgeSize != 0L) {
      return mTotalExternalStorgeSize;
    }
    try
    {
      StatFs localStatFs;
      long l;
      if (Environment.getExternalStorageState().equals("mounted"))
      {
        localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        l = localStatFs.getBlockSize();
      }
      for (mTotalExternalStorgeSize = localStatFs.getBlockCount() * l / 1024L;; mTotalExternalStorgeSize = 0L) {
        return mTotalExternalStorgeSize;
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        L.warn(ArdUtil.class, "getTotalExternalStorgeSize exception . %s", new Object[] { localThrowable });
      }
    }
  }
  
  public static long getTotalInternalStorgeSize()
  {
    if (mTotalInternalStorgeSize != 0L) {
      return mTotalInternalStorgeSize;
    }
    try
    {
      StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
      long l = localStatFs.getBlockSize();
      mTotalInternalStorgeSize = localStatFs.getBlockCount() * l / 1024L;
      return mTotalInternalStorgeSize;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        L.warn(ArdUtil.class, "getTotalInternalStorgeSize exception . %s", new Object[] { localThrowable });
      }
    }
  }
  
  /* Error */
  public static long getTotalMemory()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: getstatic 59	com/yy/hiidostatis/inner/util/ArdUtil:mTotal	J
    //   5: lconst_0
    //   6: lcmp
    //   7: ifeq +7 -> 14
    //   10: getstatic 59	com/yy/hiidostatis/inner/util/ArdUtil:mTotal	J
    //   13: lreturn
    //   14: new 256	java/io/BufferedReader
    //   17: dup
    //   18: new 251	java/io/FileReader
    //   21: dup
    //   22: ldc_w 624
    //   25: invokespecial 254	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   28: bipush 8
    //   30: invokespecial 627	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   33: astore_1
    //   34: aload_1
    //   35: astore_0
    //   36: aload_1
    //   37: invokevirtual 262	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   40: astore_3
    //   41: aload_3
    //   42: ifnull +5 -> 47
    //   45: aload_3
    //   46: astore_2
    //   47: aload_1
    //   48: astore_0
    //   49: aload_2
    //   50: invokestatic 469	com/yy/hiidostatis/inner/util/Util:empty	(Ljava/lang/String;)Z
    //   53: ifne +35 -> 88
    //   56: aload_1
    //   57: astore_0
    //   58: aload_2
    //   59: aload_2
    //   60: bipush 58
    //   62: invokevirtual 631	java/lang/String:indexOf	(I)I
    //   65: iconst_1
    //   66: iadd
    //   67: aload_2
    //   68: bipush 107
    //   70: invokevirtual 631	java/lang/String:indexOf	(I)I
    //   73: invokevirtual 518	java/lang/String:substring	(II)Ljava/lang/String;
    //   76: invokevirtual 461	java/lang/String:trim	()Ljava/lang/String;
    //   79: invokevirtual 461	java/lang/String:trim	()Ljava/lang/String;
    //   82: invokestatic 637	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   85: putstatic 59	com/yy/hiidostatis/inner/util/ArdUtil:mTotal	J
    //   88: aload_1
    //   89: ifnull +7 -> 96
    //   92: aload_1
    //   93: invokevirtual 271	java/io/BufferedReader:close	()V
    //   96: getstatic 59	com/yy/hiidostatis/inner/util/ArdUtil:mTotal	J
    //   99: lreturn
    //   100: astore_2
    //   101: aconst_null
    //   102: astore_1
    //   103: aload_1
    //   104: astore_0
    //   105: ldc 2
    //   107: ldc_w 639
    //   110: iconst_1
    //   111: anewarray 4	java/lang/Object
    //   114: dup
    //   115: iconst_0
    //   116: aload_2
    //   117: aastore
    //   118: invokestatic 136	com/yy/hiidostatis/inner/util/log/L:warn	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   121: aload_1
    //   122: ifnull -26 -> 96
    //   125: aload_1
    //   126: invokevirtual 271	java/io/BufferedReader:close	()V
    //   129: goto -33 -> 96
    //   132: astore_0
    //   133: goto -37 -> 96
    //   136: astore_1
    //   137: aconst_null
    //   138: astore_0
    //   139: aload_0
    //   140: ifnull +7 -> 147
    //   143: aload_0
    //   144: invokevirtual 271	java/io/BufferedReader:close	()V
    //   147: aload_1
    //   148: athrow
    //   149: astore_0
    //   150: goto -54 -> 96
    //   153: astore_0
    //   154: goto -7 -> 147
    //   157: astore_1
    //   158: goto -19 -> 139
    //   161: astore_2
    //   162: goto -59 -> 103
    // Local variable table:
    //   start	length	slot	name	signature
    //   35	70	0	localBufferedReader1	BufferedReader
    //   132	1	0	localIOException1	java.io.IOException
    //   138	6	0	localObject1	Object
    //   149	1	0	localIOException2	java.io.IOException
    //   153	1	0	localIOException3	java.io.IOException
    //   33	93	1	localBufferedReader2	BufferedReader
    //   136	12	1	localObject2	Object
    //   157	1	1	localObject3	Object
    //   1	67	2	localObject4	Object
    //   100	17	2	localThrowable1	Throwable
    //   161	1	2	localThrowable2	Throwable
    //   40	6	3	str	String
    // Exception table:
    //   from	to	target	type
    //   14	34	100	java/lang/Throwable
    //   125	129	132	java/io/IOException
    //   14	34	136	finally
    //   92	96	149	java/io/IOException
    //   143	147	153	java/io/IOException
    //   36	41	157	finally
    //   49	56	157	finally
    //   58	88	157	finally
    //   105	121	157	finally
    //   36	41	161	java/lang/Throwable
    //   49	56	161	java/lang/Throwable
    //   58	88	161	java/lang/Throwable
  }
  
  @SuppressLint({"NewApi"})
  public static long getTotalMemory(Context paramContext)
  {
    if (mTotalMem != 0L) {
      return mTotalMem;
    }
    if ((Build.VERSION.SDK_INT >= 16) && (paramContext != null)) {}
    for (;;)
    {
      try
      {
        ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager)paramContext.getSystemService("activity")).getMemoryInfo(localMemoryInfo);
        mTotalMem = localMemoryInfo.totalMem / 1024L;
        return mTotalMem;
      }
      catch (Throwable paramContext)
      {
        L.warn(ArdUtil.class, "getTotalMemory exception . %s", new Object[] { paramContext });
        mTotalMem = getTotalMemory();
        continue;
      }
      mTotalMem = getTotalMemory();
    }
  }
  
  /* Error */
  private static long getTotalMemoryFromFile()
  {
    // Byte code:
    //   0: lconst_0
    //   1: lstore_2
    //   2: new 649	java/io/RandomAccessFile
    //   5: dup
    //   6: ldc_w 624
    //   9: ldc_w 651
    //   12: invokespecial 654	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   15: astore 5
    //   17: aload 5
    //   19: astore 4
    //   21: aload 5
    //   23: invokevirtual 655	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   26: astore 6
    //   28: aload 5
    //   30: astore 4
    //   32: ldc_w 657
    //   35: invokestatic 663	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   38: aload 6
    //   40: invokevirtual 667	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   43: astore 7
    //   45: ldc -24
    //   47: astore 6
    //   49: aload 5
    //   51: astore 4
    //   53: aload 7
    //   55: invokevirtual 672	java/util/regex/Matcher:find	()Z
    //   58: ifeq +18 -> 76
    //   61: aload 5
    //   63: astore 4
    //   65: aload 7
    //   67: iconst_1
    //   68: invokevirtual 675	java/util/regex/Matcher:group	(I)Ljava/lang/String;
    //   71: astore 6
    //   73: goto -24 -> 49
    //   76: aload 5
    //   78: astore 4
    //   80: aload 6
    //   82: invokestatic 637	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   85: lstore_0
    //   86: lload_0
    //   87: lstore_2
    //   88: aload 5
    //   90: ifnull +10 -> 100
    //   93: aload 5
    //   95: invokevirtual 676	java/io/RandomAccessFile:close	()V
    //   98: lload_0
    //   99: lstore_2
    //   100: lload_2
    //   101: lreturn
    //   102: astore 6
    //   104: aconst_null
    //   105: astore 5
    //   107: aload 5
    //   109: astore 4
    //   111: ldc 2
    //   113: ldc_w 678
    //   116: iconst_1
    //   117: anewarray 4	java/lang/Object
    //   120: dup
    //   121: iconst_0
    //   122: aload 6
    //   124: aastore
    //   125: invokestatic 136	com/yy/hiidostatis/inner/util/log/L:warn	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   128: aload 5
    //   130: ifnull -30 -> 100
    //   133: aload 5
    //   135: invokevirtual 676	java/io/RandomAccessFile:close	()V
    //   138: lconst_0
    //   139: lreturn
    //   140: astore 4
    //   142: lconst_0
    //   143: lreturn
    //   144: astore 5
    //   146: aconst_null
    //   147: astore 4
    //   149: aload 4
    //   151: ifnull +8 -> 159
    //   154: aload 4
    //   156: invokevirtual 676	java/io/RandomAccessFile:close	()V
    //   159: aload 5
    //   161: athrow
    //   162: astore 4
    //   164: lload_0
    //   165: lreturn
    //   166: astore 4
    //   168: goto -9 -> 159
    //   171: astore 5
    //   173: goto -24 -> 149
    //   176: astore 6
    //   178: goto -71 -> 107
    // Local variable table:
    //   start	length	slot	name	signature
    //   85	80	0	l1	long
    //   1	100	2	l2	long
    //   19	91	4	localRandomAccessFile1	java.io.RandomAccessFile
    //   140	1	4	localThrowable1	Throwable
    //   147	8	4	localObject1	Object
    //   162	1	4	localThrowable2	Throwable
    //   166	1	4	localThrowable3	Throwable
    //   15	119	5	localRandomAccessFile2	java.io.RandomAccessFile
    //   144	16	5	localObject2	Object
    //   171	1	5	localObject3	Object
    //   26	55	6	str	String
    //   102	21	6	localThrowable4	Throwable
    //   176	1	6	localThrowable5	Throwable
    //   43	23	7	localMatcher	java.util.regex.Matcher
    // Exception table:
    //   from	to	target	type
    //   2	17	102	java/lang/Throwable
    //   133	138	140	java/lang/Throwable
    //   2	17	144	finally
    //   93	98	162	java/lang/Throwable
    //   154	159	166	java/lang/Throwable
    //   21	28	171	finally
    //   32	45	171	finally
    //   53	61	171	finally
    //   65	73	171	finally
    //   80	86	171	finally
    //   111	128	171	finally
    //   21	28	176	java/lang/Throwable
    //   32	45	176	java/lang/Throwable
    //   53	61	176	java/lang/Throwable
    //   65	73	176	java/lang/Throwable
    //   80	86	176	java/lang/Throwable
  }
  
  @SuppressLint({"NewApi"})
  public static long getTotalRxBytes()
  {
    if (Build.VERSION.SDK_INT >= 8) {
      return TrafficStats.getTotalRxBytes();
    }
    return 0L;
  }
  
  @SuppressLint({"NewApi"})
  public static long getTotalTxBytes()
  {
    if (Build.VERSION.SDK_INT >= 8) {
      return TrafficStats.getTotalTxBytes();
    }
    return 0L;
  }
  
  @SuppressLint({"NewApi"})
  public static long getUidRxBytes(int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 8) {
      return TrafficStats.getUidRxBytes(paramInt);
    }
    return 0L;
  }
  
  @SuppressLint({"NewApi"})
  public static long getUidTxBytes(int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 8) {
      return TrafficStats.getUidTxBytes(paramInt);
    }
    return 0L;
  }
  
  public static String getVersionName(Context paramContext)
  {
    try
    {
      if (mVersionName != null) {
        return mVersionName;
      }
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      mVersionName = paramContext;
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      L.warn(ArdUtil.class, "Failed to read version Name.", new Object[0]);
      mVersionName = "";
    }
    return "";
  }
  
  public static int getVersionNo(Context paramContext)
  {
    try
    {
      if (mVer != -1) {
        return mVer;
      }
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      mVer = i;
      return i;
    }
    catch (Throwable paramContext)
    {
      L.warn(ArdUtil.class, "Failed to read version No.", new Object[0]);
      mVer = -1;
    }
    return -1;
  }
  
  public static int getVolume(Context paramContext, int paramInt)
  {
    try
    {
      paramInt = ((AudioManager)paramContext.getSystemService("audio")).getStreamVolume(paramInt);
      return paramInt;
    }
    catch (Throwable paramContext)
    {
      L.warn(ArdUtil.class, "getVolume exception . %s", new Object[] { paramContext });
    }
    return -1;
  }
  
  public static WifiInfo getWifiInfo(Context paramContext)
  {
    return null;
  }
  
  public static boolean isDebugEnable(Context paramContext)
  {
    boolean bool = false;
    if (Settings.Secure.getInt(paramContext.getContentResolver(), "adb_enabled", 0) > 0) {
      bool = true;
    }
    return bool;
  }
  
  public static boolean isExistExternalStorage()
  {
    try
    {
      boolean bool = Environment.getExternalStorageState().equals("mounted");
      return bool;
    }
    catch (Throwable localThrowable)
    {
      L.warn(ArdUtil.class, "isExistSdCard Exception: %s", new Object[] { localThrowable });
    }
    return false;
  }
  
  public static boolean isHeadphone(Context paramContext)
  {
    try
    {
      if (checkPermissions(paramContext, "android.permission.MODIFY_AUDIO_SETTINGS"))
      {
        boolean bool = ((AudioManager)paramContext.getSystemService("audio")).isWiredHeadsetOn();
        return bool;
      }
    }
    catch (Throwable paramContext)
    {
      L.warn(ArdUtil.class, "isHeadphone exception . %s", new Object[] { paramContext });
    }
    return false;
  }
  
  public static boolean isNetworkAvailable(Context paramContext)
  {
    for (;;)
    {
      try
      {
        paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
        if (paramContext != null)
        {
          paramContext = paramContext.getActiveNetworkInfo();
          if ((paramContext != null) && (paramContext.isConnected()))
          {
            boolean bool = paramContext.isAvailable();
            if (bool) {
              return true;
            }
          }
          return false;
        }
      }
      catch (Throwable paramContext)
      {
        L.warn(ArdUtil.class, "isNetworkAvailable Exception: %s", new Object[] { paramContext });
        return false;
      }
      paramContext = null;
    }
  }
  
  /* Error */
  public static boolean isNetworkReach()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_1
    //   2: new 762	java/net/Socket
    //   5: dup
    //   6: invokespecial 763	java/net/Socket:<init>	()V
    //   9: astore_2
    //   10: aload_2
    //   11: new 765	java/net/InetSocketAddress
    //   14: dup
    //   15: ldc_w 767
    //   18: bipush 80
    //   20: invokespecial 770	java/net/InetSocketAddress:<init>	(Ljava/lang/String;I)V
    //   23: sipush 5000
    //   26: invokevirtual 774	java/net/Socket:connect	(Ljava/net/SocketAddress;I)V
    //   29: aload_2
    //   30: invokevirtual 775	java/net/Socket:isConnected	()Z
    //   33: istore_0
    //   34: iload_0
    //   35: istore_1
    //   36: aload_2
    //   37: ifnull +9 -> 46
    //   40: aload_2
    //   41: invokevirtual 776	java/net/Socket:close	()V
    //   44: iload_0
    //   45: istore_1
    //   46: iload_1
    //   47: ireturn
    //   48: astore_3
    //   49: ldc 2
    //   51: ldc_w 778
    //   54: iconst_1
    //   55: anewarray 4	java/lang/Object
    //   58: dup
    //   59: iconst_0
    //   60: aload_3
    //   61: aastore
    //   62: invokestatic 136	com/yy/hiidostatis/inner/util/log/L:warn	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   65: aload_2
    //   66: ifnull -20 -> 46
    //   69: aload_2
    //   70: invokevirtual 776	java/net/Socket:close	()V
    //   73: iconst_0
    //   74: ireturn
    //   75: astore_2
    //   76: iconst_0
    //   77: ireturn
    //   78: astore_3
    //   79: aload_2
    //   80: ifnull +7 -> 87
    //   83: aload_2
    //   84: invokevirtual 776	java/net/Socket:close	()V
    //   87: aload_3
    //   88: athrow
    //   89: astore_2
    //   90: iload_0
    //   91: ireturn
    //   92: astore_2
    //   93: goto -6 -> 87
    // Local variable table:
    //   start	length	slot	name	signature
    //   33	58	0	bool1	boolean
    //   1	46	1	bool2	boolean
    //   9	61	2	localSocket	java.net.Socket
    //   75	9	2	localThrowable1	Throwable
    //   89	1	2	localThrowable2	Throwable
    //   92	1	2	localThrowable3	Throwable
    //   48	13	3	localThrowable4	Throwable
    //   78	10	3	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   10	34	48	java/lang/Throwable
    //   69	73	75	java/lang/Throwable
    //   10	34	78	finally
    //   49	65	78	finally
    //   40	44	89	java/lang/Throwable
    //   83	87	92	java/lang/Throwable
  }
  
  public static boolean isRoot()
  {
    return (new File("/system/bin/su").exists()) || (new File("/system/xbin/su").exists());
  }
  
  public static boolean isValidMac(String paramString)
  {
    return (!TextUtils.isEmpty(paramString)) && (!paramString.equalsIgnoreCase("02:00:00:00:00:00"));
  }
  
  public static boolean isWifiActive(Context paramContext)
  {
    if (paramContext == null)
    {
      L.warn(ArdUtil.class, "the Input context is null!", new Object[0]);
      return false;
    }
    for (;;)
    {
      try
      {
        paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
        if (paramContext != null)
        {
          paramContext = paramContext.getActiveNetworkInfo();
          if (paramContext != null)
          {
            int i = paramContext.getType();
            if (i == 1)
            {
              bool = true;
              return bool;
            }
          }
          boolean bool = false;
          continue;
        }
        paramContext = null;
      }
      catch (Throwable paramContext)
      {
        L.warn(ArdUtil.class, "isWifiActive Exception: %s", new Object[] { paramContext });
        return false;
      }
    }
  }
}
