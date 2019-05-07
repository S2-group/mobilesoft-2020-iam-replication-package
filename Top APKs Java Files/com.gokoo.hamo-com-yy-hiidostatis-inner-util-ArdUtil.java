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
import android.telephony.TelephonyManager;
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
import java.net.NetworkInterface;
import java.util.Enumeration;
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
  private static int mCpuNum = 0;
  private static String mImei;
  private static String mImsi;
  private static String mLang;
  private static String mMacAddress;
  private static String mMacAddressV23;
  private static String mMaxpuFreq;
  private static String mNtm;
  private static String mOS;
  private static String mScreenResolution;
  private static long mTotal = 0L;
  private static long mTotalExternalStorgeSize = 0L;
  private static long mTotalInternalStorgeSize = 0L;
  private static long mTotalMem = 0L;
  private static int mVer = -1;
  private static String mVersionName;
  
  public ArdUtil() {}
  
  public static boolean checkPermissions(Context paramContext, String paramString)
  {
    boolean bool = true;
    try
    {
      if (Build.VERSION.SDK_INT >= 23)
      {
        if (((Integer)Class.forName("android.content.Context").getMethod("checkSelfPermission", new Class[] { String.class }).invoke(paramContext, new Object[] { paramString })).intValue() != 0) {
          break label90;
        }
        return bool;
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
    label90:
    bool = false;
    return bool;
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
      L.warn(ArdUtil.class, "Exception when getAndroidId %s", new Object[] { paramContext });
    }
    return mAndroidId;
  }
  
  public static long getAvailExternalStorgeSize()
  {
    try
    {
      if (Environment.getExternalStorageState().equals("mounted"))
      {
        StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long l = localStatFs.getBlockSize();
        l = localStatFs.getAvailableBlocks() * l / 1024L;
        return l;
      }
      return 0L;
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
    //   0: getstatic 215	com/yy/hiidostatis/inner/util/ArdUtil:mCpuAbi	Ljava/lang/String;
    //   3: invokestatic 221	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   6: ifne +7 -> 13
    //   9: getstatic 215	com/yy/hiidostatis/inner/util/ArdUtil:mCpuAbi	Ljava/lang/String;
    //   12: areturn
    //   13: getstatic 57	android/os/Build$VERSION:SDK_INT	I
    //   16: bipush 21
    //   18: if_icmpge +13 -> 31
    //   21: getstatic 226	android/os/Build:CPU_ABI	Ljava/lang/String;
    //   24: astore_0
    //   25: aload_0
    //   26: putstatic 215	com/yy/hiidostatis/inner/util/ArdUtil:mCpuAbi	Ljava/lang/String;
    //   29: aload_0
    //   30: areturn
    //   31: new 228	java/io/FileReader
    //   34: dup
    //   35: ldc -26
    //   37: invokespecial 231	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   40: astore_0
    //   41: new 233	java/io/BufferedReader
    //   44: dup
    //   45: aload_0
    //   46: invokespecial 236	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   49: astore 4
    //   51: aload_0
    //   52: astore_1
    //   53: aload 4
    //   55: astore_3
    //   56: aload 4
    //   58: invokevirtual 239	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   61: ldc -15
    //   63: iconst_2
    //   64: invokevirtual 245	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   67: iconst_1
    //   68: aaload
    //   69: astore_2
    //   70: aload_0
    //   71: astore_1
    //   72: aload 4
    //   74: astore_3
    //   75: aload_2
    //   76: putstatic 215	com/yy/hiidostatis/inner/util/ArdUtil:mCpuAbi	Ljava/lang/String;
    //   79: aload 4
    //   81: invokevirtual 248	java/io/BufferedReader:close	()V
    //   84: aload_0
    //   85: invokevirtual 249	java/io/FileReader:close	()V
    //   88: aload_2
    //   89: areturn
    //   90: astore_1
    //   91: aload_0
    //   92: astore_2
    //   93: aload 4
    //   95: astore_0
    //   96: aload_1
    //   97: astore 4
    //   99: goto +34 -> 133
    //   102: astore_1
    //   103: aconst_null
    //   104: astore_3
    //   105: goto +71 -> 176
    //   108: astore 4
    //   110: aconst_null
    //   111: astore_1
    //   112: aload_0
    //   113: astore_2
    //   114: aload_1
    //   115: astore_0
    //   116: goto +17 -> 133
    //   119: astore_1
    //   120: aconst_null
    //   121: astore_0
    //   122: aload_0
    //   123: astore_3
    //   124: goto +52 -> 176
    //   127: astore 4
    //   129: aconst_null
    //   130: astore_2
    //   131: aload_2
    //   132: astore_0
    //   133: aload_2
    //   134: astore_1
    //   135: aload_0
    //   136: astore_3
    //   137: ldc 2
    //   139: ldc -5
    //   141: iconst_1
    //   142: anewarray 4	java/lang/Object
    //   145: dup
    //   146: iconst_0
    //   147: aload 4
    //   149: aastore
    //   150: invokestatic 109	com/yy/hiidostatis/inner/util/log/L:warn	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   153: aload_0
    //   154: ifnull +7 -> 161
    //   157: aload_0
    //   158: invokevirtual 248	java/io/BufferedReader:close	()V
    //   161: aload_2
    //   162: ifnull +7 -> 169
    //   165: aload_2
    //   166: invokevirtual 249	java/io/FileReader:close	()V
    //   169: aconst_null
    //   170: areturn
    //   171: astore_2
    //   172: aload_1
    //   173: astore_0
    //   174: aload_2
    //   175: astore_1
    //   176: aload_3
    //   177: ifnull +7 -> 184
    //   180: aload_3
    //   181: invokevirtual 248	java/io/BufferedReader:close	()V
    //   184: aload_0
    //   185: ifnull +7 -> 192
    //   188: aload_0
    //   189: invokevirtual 249	java/io/FileReader:close	()V
    //   192: aload_1
    //   193: athrow
    //   194: astore_0
    //   195: aload_2
    //   196: areturn
    //   197: astore_0
    //   198: aconst_null
    //   199: areturn
    //   200: astore_0
    //   201: goto -9 -> 192
    // Local variable table:
    //   start	length	slot	name	signature
    //   24	165	0	localObject1	Object
    //   194	1	0	localIOException1	java.io.IOException
    //   197	1	0	localIOException2	java.io.IOException
    //   200	1	0	localIOException3	java.io.IOException
    //   52	20	1	localObject2	Object
    //   90	7	1	localThrowable1	Throwable
    //   102	1	1	localObject3	Object
    //   111	4	1	localObject4	Object
    //   119	1	1	localObject5	Object
    //   134	59	1	localObject6	Object
    //   69	97	2	localObject7	Object
    //   171	25	2	str	String
    //   55	126	3	localObject8	Object
    //   49	49	4	localObject9	Object
    //   108	1	4	localThrowable2	Throwable
    //   127	21	4	localThrowable3	Throwable
    // Exception table:
    //   from	to	target	type
    //   56	70	90	java/lang/Throwable
    //   75	79	90	java/lang/Throwable
    //   41	51	102	finally
    //   41	51	108	java/lang/Throwable
    //   31	41	119	finally
    //   31	41	127	java/lang/Throwable
    //   56	70	171	finally
    //   75	79	171	finally
    //   137	153	171	finally
    //   79	88	194	java/io/IOException
    //   157	161	197	java/io/IOException
    //   165	169	197	java/io/IOException
    //   180	184	200	java/io/IOException
    //   188	192	200	java/io/IOException
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
    try
    {
      localObject = LocaleList.getDefault().get(0);
    }
    catch (Throwable localThrowable)
    {
      Object localObject;
      for (;;) {}
    }
    localObject = Locale.getDefault();
    break label40;
    localObject = Locale.getDefault();
    label40:
    localObject = String.format("%s-%s", new Object[] { ((Locale)localObject).getLanguage(), ((Locale)localObject).getCountry() });
    mLang = (String)localObject;
    return localObject;
  }
  
  public static String getMacAddr(Context paramContext)
  {
    if (mMacAddress != null) {
      return mMacAddress;
    }
    return mMacAddress;
  }
  
  @SuppressLint({"NewApi"})
  public static String getMacAddr2()
  {
    Enumeration localEnumeration = null;
    localObject2 = null;
    String str = null;
    Object localObject1 = localEnumeration;
    try
    {
      if (Build.VERSION.SDK_INT >= 9)
      {
        localObject1 = localEnumeration;
        localEnumeration = NetworkInterface.getNetworkInterfaces();
        for (;;)
        {
          localObject1 = str;
          localObject2 = str;
          if (!localEnumeration.hasMoreElements()) {
            break;
          }
          localObject1 = str;
          localObject2 = (NetworkInterface)localEnumeration.nextElement();
          localObject1 = str;
          if (((NetworkInterface)localObject2).getName().equalsIgnoreCase("wlan0"))
          {
            localObject1 = str;
            localObject2 = ((NetworkInterface)localObject2).getHardwareAddress();
            if (localObject2 != null)
            {
              localObject1 = str;
              if (localObject2.length != 0)
              {
                localObject1 = str;
                StringBuilder localStringBuilder = new StringBuilder();
                localObject1 = str;
                int j = localObject2.length;
                int i = 0;
                while (i < j)
                {
                  localObject1 = str;
                  localStringBuilder.append(String.format("%02X:", new Object[] { Byte.valueOf(localObject2[i]) }));
                  i += 1;
                }
                localObject1 = str;
                if (localStringBuilder.length() > 0)
                {
                  localObject1 = str;
                  localStringBuilder.deleteCharAt(localStringBuilder.length() - 1);
                }
                localObject1 = str;
                str = localStringBuilder.toString();
              }
            }
          }
        }
      }
      return localObject2;
    }
    catch (Throwable localThrowable)
    {
      L.warn(ArdUtil.class, "exception on getMacAddr2 : %s", new Object[] { localThrowable });
      localObject2 = localObject1;
    }
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
      while (localFileInputStream.read(arrayOfByte) != -1) {
        ((StringBuilder)localObject).append(new String(arrayOfByte));
      }
      localFileInputStream.close();
    }
    catch (Throwable localThrowable)
    {
      L.warn(ArdUtil.class, "getMaxCpuFreq exception: %s", new Object[] { localThrowable });
    }
    localObject = ((StringBuilder)localObject).toString().trim();
    mMaxpuFreq = (String)localObject;
    return localObject;
  }
  
  public static String getMetaDataParam(Context paramContext, String paramString)
  {
    if ((paramContext != null) && (!Util.empty(paramString)))
    {
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
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append(paramContext);
              localStringBuilder.append("");
              paramContext = localStringBuilder.toString();
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
    return "";
  }
  
  public static int getNetworkType(Context paramContext)
  {
    return 0;
  }
  
  public static int getNetworkTypeNew(Context paramContext)
  {
    return 0;
  }
  
  public static String getNtm(Context paramContext)
  {
    if (mNtm != null) {
      return mNtm;
    }
    Object localObject1 = null;
    Object localObject3 = null;
    Object localObject2 = localObject3;
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext == null) {
        break label181;
      }
      localObject2 = localObject3;
      paramContext = paramContext.getSimOperator();
      localObject1 = paramContext;
      try
      {
        if (Util.empty(paramContext)) {
          break label181;
        }
        localObject1 = paramContext.split(",");
        if ((localObject1.length > 0) && (!Util.empty(localObject1[0]))) {
          paramContext = localObject1[0];
        } else if ((localObject1.length == 2) && (!Util.empty(localObject1[1]))) {
          paramContext = localObject1[1];
        }
        localObject2 = paramContext;
        if (paramContext.length() != 5)
        {
          localObject2 = paramContext;
          localObject1 = paramContext;
          if (paramContext.length() != 6) {}
        }
        else
        {
          localObject2 = paramContext;
          localObject1 = String.format("%s:%s", new Object[] { paramContext.substring(0, 3), paramContext.substring(3) });
        }
      }
      catch (Throwable localThrowable)
      {
        localObject1 = paramContext;
        paramContext = localThrowable;
      }
    }
    catch (Throwable paramContext)
    {
      localObject1 = localThrowable;
    }
    tmp174_171[0] = paramContext;
    L.warn(ArdUtil.class, "Exception when getNtm %s", tmp174_171);
    label181:
    mNtm = (String)localObject1;
    return localObject1;
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
      for (;;) {}
    }
    L.warn(ArdUtil.class, "Failed to read package Name.", new Object[0]);
    return "";
  }
  
  public static String getSceneMode(Context paramContext)
  {
    int i;
    try
    {
      paramContext = (AudioManager)paramContext.getSystemService("audio");
      i = -1;
      if (paramContext == null) {
        break label51;
      }
      i = paramContext.getRingerMode();
    }
    catch (Throwable paramContext)
    {
      L.warn(ArdUtil.class, "getSceneMode exception . %s", new Object[] { paramContext });
      return "";
    }
    paramContext = "silent";
    for (;;)
    {
      return paramContext;
      switch (i)
      {
      case 0: 
      default: 
        return "";
      case 2: 
        paramContext = "normal";
        break;
      case 1: 
        label51:
        paramContext = "vibrate";
      }
    }
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
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramContext.x);
      ((StringBuilder)localObject).append("x");
      ((StringBuilder)localObject).append(paramContext.y);
      mScreenResolution = ((StringBuilder)localObject).toString();
    }
    catch (Throwable paramContext)
    {
      L.warn(ArdUtil.class, "exception on getScreenResolution info: %s", new Object[] { paramContext });
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
    int i = TimeZone.getDefault().getRawOffset() / 60000;
    char c;
    if (i < 0)
    {
      c = '-';
      i = -i;
    }
    else
    {
      c = '+';
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("GMT");
    localStringBuilder.append(c);
    localStringBuilder.append(i / 60);
    return localStringBuilder.toString();
  }
  
  public static long getTotalExternalStorgeSize()
  {
    if (mTotalExternalStorgeSize != 0L) {
      return mTotalExternalStorgeSize;
    }
    try
    {
      if (Environment.getExternalStorageState().equals("mounted"))
      {
        StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long l = localStatFs.getBlockSize();
        mTotalExternalStorgeSize = localStatFs.getBlockCount() * l / 1024L;
      }
      else
      {
        mTotalExternalStorgeSize = 0L;
      }
    }
    catch (Throwable localThrowable)
    {
      L.warn(ArdUtil.class, "getTotalExternalStorgeSize exception . %s", new Object[] { localThrowable });
    }
    return mTotalExternalStorgeSize;
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
    }
    catch (Throwable localThrowable)
    {
      L.warn(ArdUtil.class, "getTotalInternalStorgeSize exception . %s", new Object[] { localThrowable });
    }
    return mTotalInternalStorgeSize;
  }
  
  /* Error */
  public static long getTotalMemory()
  {
    // Byte code:
    //   0: getstatic 616	com/yy/hiidostatis/inner/util/ArdUtil:mTotal	J
    //   3: lconst_0
    //   4: lcmp
    //   5: ifeq +7 -> 12
    //   8: getstatic 616	com/yy/hiidostatis/inner/util/ArdUtil:mTotal	J
    //   11: lreturn
    //   12: aconst_null
    //   13: astore_2
    //   14: aconst_null
    //   15: astore_0
    //   16: new 233	java/io/BufferedReader
    //   19: dup
    //   20: new 228	java/io/FileReader
    //   23: dup
    //   24: ldc_w 618
    //   27: invokespecial 231	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   30: bipush 8
    //   32: invokespecial 621	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   35: astore_1
    //   36: aload_1
    //   37: invokevirtual 239	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   40: astore_0
    //   41: aload_0
    //   42: ifnull +119 -> 161
    //   45: goto +3 -> 48
    //   48: aload_0
    //   49: invokestatic 456	com/yy/hiidostatis/inner/util/Util:empty	(Ljava/lang/String;)Z
    //   52: ifne +33 -> 85
    //   55: aload_0
    //   56: aload_0
    //   57: bipush 58
    //   59: invokevirtual 625	java/lang/String:indexOf	(I)I
    //   62: iconst_1
    //   63: iadd
    //   64: aload_0
    //   65: bipush 107
    //   67: invokevirtual 625	java/lang/String:indexOf	(I)I
    //   70: invokevirtual 502	java/lang/String:substring	(II)Ljava/lang/String;
    //   73: invokevirtual 449	java/lang/String:trim	()Ljava/lang/String;
    //   76: invokevirtual 449	java/lang/String:trim	()Ljava/lang/String;
    //   79: invokestatic 631	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   82: putstatic 616	com/yy/hiidostatis/inner/util/ArdUtil:mTotal	J
    //   85: aload_1
    //   86: invokevirtual 248	java/io/BufferedReader:close	()V
    //   89: goto +50 -> 139
    //   92: astore_2
    //   93: aload_1
    //   94: astore_0
    //   95: aload_2
    //   96: astore_1
    //   97: goto +46 -> 143
    //   100: astore_2
    //   101: goto +12 -> 113
    //   104: astore_1
    //   105: goto +38 -> 143
    //   108: astore_0
    //   109: aload_2
    //   110: astore_1
    //   111: aload_0
    //   112: astore_2
    //   113: aload_1
    //   114: astore_0
    //   115: ldc 2
    //   117: ldc_w 633
    //   120: iconst_1
    //   121: anewarray 4	java/lang/Object
    //   124: dup
    //   125: iconst_0
    //   126: aload_2
    //   127: aastore
    //   128: invokestatic 109	com/yy/hiidostatis/inner/util/log/L:warn	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   131: aload_1
    //   132: ifnull +7 -> 139
    //   135: aload_1
    //   136: invokevirtual 248	java/io/BufferedReader:close	()V
    //   139: getstatic 616	com/yy/hiidostatis/inner/util/ArdUtil:mTotal	J
    //   142: lreturn
    //   143: aload_0
    //   144: ifnull +7 -> 151
    //   147: aload_0
    //   148: invokevirtual 248	java/io/BufferedReader:close	()V
    //   151: aload_1
    //   152: athrow
    //   153: astore_0
    //   154: goto -15 -> 139
    //   157: astore_0
    //   158: goto -7 -> 151
    //   161: aconst_null
    //   162: astore_0
    //   163: goto -115 -> 48
    // Local variable table:
    //   start	length	slot	name	signature
    //   15	80	0	localObject1	Object
    //   108	4	0	localThrowable1	Throwable
    //   114	34	0	localObject2	Object
    //   153	1	0	localIOException1	java.io.IOException
    //   157	1	0	localIOException2	java.io.IOException
    //   162	1	0	localObject3	Object
    //   35	62	1	localObject4	Object
    //   104	1	1	localObject5	Object
    //   110	42	1	localThrowable2	Throwable
    //   13	1	2	localObject6	Object
    //   92	4	2	localObject7	Object
    //   100	10	2	localThrowable3	Throwable
    //   112	15	2	localObject8	Object
    // Exception table:
    //   from	to	target	type
    //   36	41	92	finally
    //   48	85	92	finally
    //   36	41	100	java/lang/Throwable
    //   48	85	100	java/lang/Throwable
    //   16	36	104	finally
    //   115	131	104	finally
    //   16	36	108	java/lang/Throwable
    //   85	89	153	java/io/IOException
    //   135	139	153	java/io/IOException
    //   147	151	157	java/io/IOException
  }
  
  @SuppressLint({"NewApi"})
  public static long getTotalMemory(Context paramContext)
  {
    if (mTotalMem != 0L) {
      return mTotalMem;
    }
    if ((Build.VERSION.SDK_INT >= 16) && (paramContext != null)) {
      try
      {
        ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager)paramContext.getSystemService("activity")).getMemoryInfo(localMemoryInfo);
        mTotalMem = localMemoryInfo.totalMem / 1024L;
      }
      catch (Throwable paramContext)
      {
        L.warn(ArdUtil.class, "getTotalMemory exception . %s", new Object[] { paramContext });
        mTotalMem = getTotalMemory();
      }
    } else {
      mTotalMem = getTotalMemory();
    }
    return mTotalMem;
  }
  
  /* Error */
  private static long getTotalMemoryFromFile()
  {
    // Byte code:
    //   0: new 645	java/io/RandomAccessFile
    //   3: dup
    //   4: ldc_w 618
    //   7: ldc_w 647
    //   10: invokespecial 650	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   13: astore_3
    //   14: aload_3
    //   15: astore_2
    //   16: aload_3
    //   17: invokevirtual 651	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   20: astore 4
    //   22: aload_3
    //   23: astore_2
    //   24: ldc_w 653
    //   27: invokestatic 659	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   30: aload 4
    //   32: invokevirtual 663	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   35: astore 5
    //   37: ldc -49
    //   39: astore 4
    //   41: aload_3
    //   42: astore_2
    //   43: aload 5
    //   45: invokevirtual 668	java/util/regex/Matcher:find	()Z
    //   48: ifeq +16 -> 64
    //   51: aload_3
    //   52: astore_2
    //   53: aload 5
    //   55: iconst_1
    //   56: invokevirtual 671	java/util/regex/Matcher:group	(I)Ljava/lang/String;
    //   59: astore 4
    //   61: goto -20 -> 41
    //   64: aload_3
    //   65: astore_2
    //   66: aload 4
    //   68: invokestatic 631	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   71: lstore_0
    //   72: aload_3
    //   73: invokevirtual 672	java/io/RandomAccessFile:close	()V
    //   76: lload_0
    //   77: lreturn
    //   78: astore 4
    //   80: goto +13 -> 93
    //   83: astore_2
    //   84: aconst_null
    //   85: astore_3
    //   86: goto +43 -> 129
    //   89: astore 4
    //   91: aconst_null
    //   92: astore_3
    //   93: aload_3
    //   94: astore_2
    //   95: ldc 2
    //   97: ldc_w 674
    //   100: iconst_1
    //   101: anewarray 4	java/lang/Object
    //   104: dup
    //   105: iconst_0
    //   106: aload 4
    //   108: aastore
    //   109: invokestatic 109	com/yy/hiidostatis/inner/util/log/L:warn	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   112: aload_3
    //   113: ifnull +7 -> 120
    //   116: aload_3
    //   117: invokevirtual 672	java/io/RandomAccessFile:close	()V
    //   120: lconst_0
    //   121: lreturn
    //   122: astore 4
    //   124: aload_2
    //   125: astore_3
    //   126: aload 4
    //   128: astore_2
    //   129: aload_3
    //   130: ifnull +7 -> 137
    //   133: aload_3
    //   134: invokevirtual 672	java/io/RandomAccessFile:close	()V
    //   137: aload_2
    //   138: athrow
    //   139: astore_2
    //   140: lload_0
    //   141: lreturn
    //   142: astore_2
    //   143: goto -23 -> 120
    //   146: astore_3
    //   147: goto -10 -> 137
    // Local variable table:
    //   start	length	slot	name	signature
    //   71	70	0	l	long
    //   15	51	2	localObject1	Object
    //   83	1	2	localObject2	Object
    //   94	44	2	localObject3	Object
    //   139	1	2	localThrowable1	Throwable
    //   142	1	2	localThrowable2	Throwable
    //   13	121	3	localObject4	Object
    //   146	1	3	localThrowable3	Throwable
    //   20	47	4	str	String
    //   78	1	4	localThrowable4	Throwable
    //   89	18	4	localThrowable5	Throwable
    //   122	5	4	localObject5	Object
    //   35	19	5	localMatcher	java.util.regex.Matcher
    // Exception table:
    //   from	to	target	type
    //   16	22	78	java/lang/Throwable
    //   24	37	78	java/lang/Throwable
    //   43	51	78	java/lang/Throwable
    //   53	61	78	java/lang/Throwable
    //   66	72	78	java/lang/Throwable
    //   0	14	83	finally
    //   0	14	89	java/lang/Throwable
    //   16	22	122	finally
    //   24	37	122	finally
    //   43	51	122	finally
    //   53	61	122	finally
    //   66	72	122	finally
    //   95	112	122	finally
    //   72	76	139	java/lang/Throwable
    //   116	120	142	java/lang/Throwable
    //   133	137	146	java/lang/Throwable
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
      for (;;) {}
    }
    L.warn(ArdUtil.class, "Failed to read version Name.", new Object[0]);
    mVersionName = "";
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
      for (;;) {}
    }
    L.warn(ArdUtil.class, "Failed to read version No.", new Object[0]);
    mVer = -1;
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
    paramContext = paramContext.getContentResolver();
    boolean bool = false;
    if (Settings.Secure.getInt(paramContext, "adb_enabled", 0) > 0) {
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
    try
    {
      ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
      paramContext = null;
      if (localConnectivityManager != null) {
        paramContext = localConnectivityManager.getActiveNetworkInfo();
      }
      if ((paramContext != null) && (paramContext.isConnected()))
      {
        boolean bool = paramContext.isAvailable();
        if (bool) {
          return true;
        }
      }
      return false;
    }
    catch (Throwable paramContext)
    {
      L.warn(ArdUtil.class, "isNetworkAvailable Exception: %s", new Object[] { paramContext });
    }
    return false;
  }
  
  /* Error */
  public static boolean isNetworkReach()
  {
    // Byte code:
    //   0: new 762	java/net/Socket
    //   3: dup
    //   4: invokespecial 763	java/net/Socket:<init>	()V
    //   7: astore_1
    //   8: aload_1
    //   9: new 765	java/net/InetSocketAddress
    //   12: dup
    //   13: ldc_w 767
    //   16: bipush 80
    //   18: invokespecial 770	java/net/InetSocketAddress:<init>	(Ljava/lang/String;I)V
    //   21: sipush 5000
    //   24: invokevirtual 774	java/net/Socket:connect	(Ljava/net/SocketAddress;I)V
    //   27: aload_1
    //   28: invokevirtual 775	java/net/Socket:isConnected	()Z
    //   31: istore_0
    //   32: aload_1
    //   33: invokevirtual 776	java/net/Socket:close	()V
    //   36: iload_0
    //   37: ireturn
    //   38: astore_2
    //   39: goto +26 -> 65
    //   42: astore_2
    //   43: ldc 2
    //   45: ldc_w 778
    //   48: iconst_1
    //   49: anewarray 4	java/lang/Object
    //   52: dup
    //   53: iconst_0
    //   54: aload_2
    //   55: aastore
    //   56: invokestatic 109	com/yy/hiidostatis/inner/util/log/L:warn	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   59: aload_1
    //   60: invokevirtual 776	java/net/Socket:close	()V
    //   63: iconst_0
    //   64: ireturn
    //   65: aload_1
    //   66: invokevirtual 776	java/net/Socket:close	()V
    //   69: aload_2
    //   70: athrow
    //   71: astore_1
    //   72: iload_0
    //   73: ireturn
    //   74: astore_1
    //   75: iconst_0
    //   76: ireturn
    //   77: astore_1
    //   78: goto -9 -> 69
    // Local variable table:
    //   start	length	slot	name	signature
    //   31	42	0	bool	boolean
    //   7	59	1	localSocket	java.net.Socket
    //   71	1	1	localThrowable1	Throwable
    //   74	1	1	localThrowable2	Throwable
    //   77	1	1	localThrowable3	Throwable
    //   38	1	2	localObject	Object
    //   42	28	2	localThrowable4	Throwable
    // Exception table:
    //   from	to	target	type
    //   8	32	38	finally
    //   43	59	38	finally
    //   8	32	42	java/lang/Throwable
    //   32	36	71	java/lang/Throwable
    //   59	63	74	java/lang/Throwable
    //   65	69	77	java/lang/Throwable
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
    boolean bool2 = false;
    if (paramContext == null)
    {
      L.warn(ArdUtil.class, "the Input context is null!", new Object[0]);
      return false;
    }
    try
    {
      ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
      paramContext = null;
      if (localConnectivityManager != null) {
        paramContext = localConnectivityManager.getActiveNetworkInfo();
      }
      boolean bool1 = bool2;
      if (paramContext != null)
      {
        int i = paramContext.getType();
        bool1 = bool2;
        if (i == 1) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (Throwable paramContext)
    {
      L.warn(ArdUtil.class, "isWifiActive Exception: %s", new Object[] { paramContext });
    }
    return false;
  }
}
