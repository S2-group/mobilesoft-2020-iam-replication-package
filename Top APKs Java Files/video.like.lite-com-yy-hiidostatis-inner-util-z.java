package com.yy.hiidostatis.inner.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.bluetooth.BluetoothAdapter;
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
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import com.yy.hiidostatis.api.HiidoSDK;
import com.yy.hiidostatis.api.HiidoSDK.z;
import com.yy.hiidostatis.inner.util.y.b;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class z
{
  private static String a = null;
  private static String b = null;
  private static String c = null;
  private static String d = null;
  private static String e = null;
  private static long f = 0L;
  private static String g = null;
  private static int h = 0;
  private static String i = null;
  private static long j = 0L;
  private static long k = 0L;
  private static long l = 0L;
  private static String u;
  private static String v;
  private static String w;
  private static String x;
  private static String y;
  private static int z = -1;
  
  static
  {
    y = null;
    v = null;
  }
  
  public z() {}
  
  public static int a()
  {
    if (h != 0) {
      return h;
    }
    try
    {
      int m = new File("/sys/devices/system/cpu/").listFiles(new y()).length;
      h = m;
      return m;
    }
    catch (Throwable localThrowable)
    {
      b.v(z.class, "getCpuNum exception: %s", new Object[] { localThrowable });
      h = 1;
    }
    return 1;
  }
  
  public static String a(Context paramContext)
  {
    try
    {
      if (v != null) {
        return v;
      }
      v = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        b.v(z.class, "Exception when getAndroidId %s", new Object[] { paramContext });
      }
    }
    return v;
  }
  
  @SuppressLint({"NewApi"})
  public static long b()
  {
    if (Build.VERSION.SDK_INT >= 8) {
      return TrafficStats.getTotalTxBytes();
    }
    return 0L;
  }
  
  /* Error */
  public static String b(Context paramContext)
  {
    // Byte code:
    //   0: getstatic 119	com/yy/hiidostatis/inner/util/z:u	Ljava/lang/String;
    //   3: ifnull +7 -> 10
    //   6: getstatic 119	com/yy/hiidostatis/inner/util/z:u	Ljava/lang/String;
    //   9: areturn
    //   10: aload_0
    //   11: ldc 121
    //   13: invokevirtual 125	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   16: checkcast 127	android/telephony/TelephonyManager
    //   19: astore_0
    //   20: aload_0
    //   21: ifnull +165 -> 186
    //   24: aload_0
    //   25: invokevirtual 131	android/telephony/TelephonyManager:getSimOperator	()Ljava/lang/String;
    //   28: astore_2
    //   29: aload_2
    //   30: astore_1
    //   31: aload_2
    //   32: astore_3
    //   33: aload_2
    //   34: invokestatic 136	com/yy/hiidostatis/inner/util/g:z	(Ljava/lang/String;)Z
    //   37: ifne +86 -> 123
    //   40: aload_2
    //   41: astore_3
    //   42: aload_2
    //   43: ldc -118
    //   45: invokevirtual 144	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   48: astore_1
    //   49: aload_2
    //   50: astore_3
    //   51: aload_1
    //   52: arraylength
    //   53: ifle +76 -> 129
    //   56: aload_2
    //   57: astore_3
    //   58: aload_1
    //   59: iconst_0
    //   60: aaload
    //   61: invokestatic 136	com/yy/hiidostatis/inner/util/g:z	(Ljava/lang/String;)Z
    //   64: ifne +65 -> 129
    //   67: aload_1
    //   68: iconst_0
    //   69: aaload
    //   70: astore_0
    //   71: aload_0
    //   72: astore_3
    //   73: aload_0
    //   74: invokevirtual 147	java/lang/String:length	()I
    //   77: iconst_5
    //   78: if_icmpeq +16 -> 94
    //   81: aload_0
    //   82: astore_1
    //   83: aload_0
    //   84: astore_3
    //   85: aload_0
    //   86: invokevirtual 147	java/lang/String:length	()I
    //   89: bipush 6
    //   91: if_icmpne +32 -> 123
    //   94: aload_0
    //   95: astore_3
    //   96: ldc -107
    //   98: iconst_2
    //   99: anewarray 4	java/lang/Object
    //   102: dup
    //   103: iconst_0
    //   104: aload_0
    //   105: iconst_0
    //   106: iconst_3
    //   107: invokevirtual 153	java/lang/String:substring	(II)Ljava/lang/String;
    //   110: aastore
    //   111: dup
    //   112: iconst_1
    //   113: aload_0
    //   114: iconst_3
    //   115: invokevirtual 156	java/lang/String:substring	(I)Ljava/lang/String;
    //   118: aastore
    //   119: invokestatic 160	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   122: astore_1
    //   123: aload_1
    //   124: putstatic 119	com/yy/hiidostatis/inner/util/z:u	Ljava/lang/String;
    //   127: aload_1
    //   128: areturn
    //   129: aload_2
    //   130: astore_0
    //   131: aload_2
    //   132: astore_3
    //   133: aload_1
    //   134: arraylength
    //   135: iconst_2
    //   136: if_icmpne -65 -> 71
    //   139: aload_2
    //   140: astore_0
    //   141: aload_2
    //   142: astore_3
    //   143: aload_1
    //   144: iconst_1
    //   145: aaload
    //   146: invokestatic 136	com/yy/hiidostatis/inner/util/g:z	(Ljava/lang/String;)Z
    //   149: ifne -78 -> 71
    //   152: aload_1
    //   153: iconst_1
    //   154: aaload
    //   155: astore_0
    //   156: goto -85 -> 71
    //   159: astore_0
    //   160: aconst_null
    //   161: astore_1
    //   162: ldc 2
    //   164: ldc -94
    //   166: iconst_1
    //   167: anewarray 4	java/lang/Object
    //   170: dup
    //   171: iconst_0
    //   172: aload_0
    //   173: aastore
    //   174: invokestatic 85	com/yy/hiidostatis/inner/util/y/b:v	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   177: goto -54 -> 123
    //   180: astore_0
    //   181: aload_3
    //   182: astore_1
    //   183: goto -21 -> 162
    //   186: aconst_null
    //   187: astore_1
    //   188: goto -65 -> 123
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	191	0	paramContext	Context
    //   30	158	1	localObject1	Object
    //   28	114	2	str	String
    //   32	150	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   10	20	159	java/lang/Throwable
    //   24	29	159	java/lang/Throwable
    //   33	40	180	java/lang/Throwable
    //   42	49	180	java/lang/Throwable
    //   51	56	180	java/lang/Throwable
    //   58	67	180	java/lang/Throwable
    //   73	81	180	java/lang/Throwable
    //   85	94	180	java/lang/Throwable
    //   96	123	180	java/lang/Throwable
    //   133	139	180	java/lang/Throwable
    //   143	152	180	java/lang/Throwable
  }
  
  @SuppressLint({"NewApi"})
  public static long c()
  {
    if (Build.VERSION.SDK_INT >= 8) {
      return TrafficStats.getTotalRxBytes();
    }
    return 0L;
  }
  
  public static String c(Context paramContext)
  {
    if (z(b)) {
      return b;
    }
    paramContext = r(paramContext);
    b = paramContext;
    if (!z(paramContext))
    {
      paramContext = m();
      if (z(paramContext)) {
        b = paramContext;
      }
    }
    return b;
  }
  
  public static String d(Context paramContext)
  {
    if (c != null) {
      return c;
    }
    try
    {
      if ((!HiidoSDK.z().y().d) && (z(paramContext, "android.permission.READ_PHONE_STATE")))
      {
        paramContext = (TelephonyManager)paramContext.getSystemService("phone");
        if (paramContext != null)
        {
          paramContext = paramContext.getDeviceId();
          c = paramContext;
          return paramContext;
        }
      }
    }
    catch (Throwable paramContext)
    {
      b.v(z.class, "exception on getIMEI : %s", new Object[] { paramContext });
    }
    return null;
  }
  
  public static boolean d()
  {
    return (new File("/system/bin/su").exists()) || (new File("/system/xbin/su").exists());
  }
  
  /* Error */
  public static String e()
  {
    // Byte code:
    //   0: getstatic 51	com/yy/hiidostatis/inner/util/z:i	Ljava/lang/String;
    //   3: invokestatic 211	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   6: ifne +9 -> 15
    //   9: getstatic 51	com/yy/hiidostatis/inner/util/z:i	Ljava/lang/String;
    //   12: astore_1
    //   13: aload_1
    //   14: areturn
    //   15: getstatic 111	android/os/Build$VERSION:SDK_INT	I
    //   18: bipush 21
    //   20: if_icmpge +13 -> 33
    //   23: getstatic 216	android/os/Build:CPU_ABI	Ljava/lang/String;
    //   26: astore_0
    //   27: aload_0
    //   28: putstatic 51	com/yy/hiidostatis/inner/util/z:i	Ljava/lang/String;
    //   31: aload_0
    //   32: areturn
    //   33: new 218	java/io/FileReader
    //   36: dup
    //   37: ldc -36
    //   39: invokespecial 221	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   42: astore_0
    //   43: new 223	java/io/BufferedReader
    //   46: dup
    //   47: aload_0
    //   48: invokespecial 226	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   51: astore 5
    //   53: aload_0
    //   54: astore_2
    //   55: aload 5
    //   57: astore_1
    //   58: aload 5
    //   60: invokevirtual 229	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   63: ldc -25
    //   65: iconst_2
    //   66: invokevirtual 234	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   69: iconst_1
    //   70: aaload
    //   71: astore_3
    //   72: aload_0
    //   73: astore_2
    //   74: aload 5
    //   76: astore_1
    //   77: aload_3
    //   78: putstatic 51	com/yy/hiidostatis/inner/util/z:i	Ljava/lang/String;
    //   81: aload 5
    //   83: ifnull +8 -> 91
    //   86: aload 5
    //   88: invokevirtual 237	java/io/BufferedReader:close	()V
    //   91: aload_3
    //   92: astore_1
    //   93: aload_0
    //   94: ifnull -81 -> 13
    //   97: aload_0
    //   98: invokevirtual 238	java/io/FileReader:close	()V
    //   101: aload_3
    //   102: areturn
    //   103: astore_0
    //   104: aload_3
    //   105: areturn
    //   106: astore_3
    //   107: aconst_null
    //   108: astore 4
    //   110: aconst_null
    //   111: astore_0
    //   112: aload 4
    //   114: astore_2
    //   115: aload_0
    //   116: astore_1
    //   117: ldc 2
    //   119: ldc -16
    //   121: iconst_1
    //   122: anewarray 4	java/lang/Object
    //   125: dup
    //   126: iconst_0
    //   127: aload_3
    //   128: aastore
    //   129: invokestatic 85	com/yy/hiidostatis/inner/util/y/b:v	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   132: aload_0
    //   133: ifnull +7 -> 140
    //   136: aload_0
    //   137: invokevirtual 237	java/io/BufferedReader:close	()V
    //   140: aload 4
    //   142: ifnull +8 -> 150
    //   145: aload 4
    //   147: invokevirtual 238	java/io/FileReader:close	()V
    //   150: aconst_null
    //   151: areturn
    //   152: astore_3
    //   153: aconst_null
    //   154: astore_0
    //   155: aconst_null
    //   156: astore_1
    //   157: aload_1
    //   158: ifnull +7 -> 165
    //   161: aload_1
    //   162: invokevirtual 237	java/io/BufferedReader:close	()V
    //   165: aload_0
    //   166: ifnull +7 -> 173
    //   169: aload_0
    //   170: invokevirtual 238	java/io/FileReader:close	()V
    //   173: aload_3
    //   174: athrow
    //   175: astore_0
    //   176: goto -26 -> 150
    //   179: astore_0
    //   180: goto -7 -> 173
    //   183: astore_3
    //   184: aconst_null
    //   185: astore_1
    //   186: goto -29 -> 157
    //   189: astore_3
    //   190: aload_2
    //   191: astore_0
    //   192: goto -35 -> 157
    //   195: astore_3
    //   196: aconst_null
    //   197: astore_1
    //   198: aload_0
    //   199: astore 4
    //   201: aload_1
    //   202: astore_0
    //   203: goto -91 -> 112
    //   206: astore_3
    //   207: aload_0
    //   208: astore 4
    //   210: aload 5
    //   212: astore_0
    //   213: goto -101 -> 112
    // Local variable table:
    //   start	length	slot	name	signature
    //   26	72	0	localObject1	Object
    //   103	1	0	localIOException1	java.io.IOException
    //   111	59	0	localObject2	Object
    //   175	1	0	localIOException2	java.io.IOException
    //   179	1	0	localIOException3	java.io.IOException
    //   191	22	0	localObject3	Object
    //   12	190	1	localObject4	Object
    //   54	137	2	localObject5	Object
    //   71	34	3	str	String
    //   106	22	3	localThrowable1	Throwable
    //   152	22	3	localObject6	Object
    //   183	1	3	localObject7	Object
    //   189	1	3	localObject8	Object
    //   195	1	3	localThrowable2	Throwable
    //   206	1	3	localThrowable3	Throwable
    //   108	101	4	localObject9	Object
    //   51	160	5	localBufferedReader	java.io.BufferedReader
    // Exception table:
    //   from	to	target	type
    //   86	91	103	java/io/IOException
    //   97	101	103	java/io/IOException
    //   33	43	106	java/lang/Throwable
    //   33	43	152	finally
    //   136	140	175	java/io/IOException
    //   145	150	175	java/io/IOException
    //   161	165	179	java/io/IOException
    //   169	173	179	java/io/IOException
    //   43	53	183	finally
    //   58	72	189	finally
    //   77	81	189	finally
    //   117	132	189	finally
    //   43	53	195	java/lang/Throwable
    //   58	72	206	java/lang/Throwable
    //   77	81	206	java/lang/Throwable
  }
  
  public static String e(Context paramContext)
  {
    if (e != null) {
      return e;
    }
    Object localObject;
    try
    {
      localObject = (WindowManager)paramContext.getSystemService("window");
      if (localObject == null)
      {
        e = "";
        return "";
      }
    }
    catch (Throwable paramContext)
    {
      b.v(z.class, "exception on getScreenResolution info: %s", new Object[] { paramContext });
    }
    for (;;)
    {
      return e;
      paramContext = new Point();
      localObject = ((WindowManager)localObject).getDefaultDisplay();
      paramContext.x = ((Display)localObject).getWidth();
      paramContext.y = ((Display)localObject).getHeight();
      e = paramContext.x + "x" + paramContext.y;
    }
  }
  
  public static int f(Context paramContext)
  {
    try
    {
      paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (paramContext == null) {
        return 0;
      }
    }
    catch (Throwable paramContext)
    {
      b.v(z.class, "exception on get network info: %s", new Object[] { paramContext });
      return 0;
    }
    int m = paramContext.getType();
    if (m == 1) {
      return 3;
    }
    if (m == 0)
    {
      switch (paramContext.getSubtype())
      {
      default: 
        return 0;
      case 1: 
      case 2: 
      case 4: 
      case 7: 
      case 11: 
      case 16: 
        return 1;
      case 3: 
      case 5: 
      case 6: 
      case 8: 
      case 9: 
      case 10: 
      case 12: 
      case 14: 
      case 15: 
      case 17: 
      case 18: 
        return 2;
      }
      return 4;
    }
    return 0;
  }
  
  public static String f()
  {
    int n = TimeZone.getDefault().getRawOffset() / 60000;
    char c1 = '+';
    int m = n;
    if (n < 0)
    {
      c1 = '-';
      m = -n;
    }
    return "GMT" + c1 + m / 60;
  }
  
  public static int g(Context paramContext)
  {
    try
    {
      Iterator localIterator = paramContext.getPackageManager().getInstalledApplications(0).iterator();
      while (localIterator.hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
        if (localApplicationInfo.packageName.equals(paramContext.getPackageName()))
        {
          int m = localApplicationInfo.uid;
          return m;
        }
      }
      return -1;
    }
    catch (Throwable paramContext)
    {
      b.v(z.class, "getCurrAppUid exception: %s", new Object[] { paramContext });
    }
    return -1;
  }
  
  public static String g()
  {
    try
    {
      InetAddress localInetAddress;
      do
      {
        localObject = NetworkInterface.getNetworkInterfaces();
        Enumeration localEnumeration;
        while (!localEnumeration.hasMoreElements())
        {
          if (!((Enumeration)localObject).hasMoreElements()) {
            break;
          }
          localEnumeration = ((NetworkInterface)((Enumeration)localObject).nextElement()).getInetAddresses();
        }
        localInetAddress = (InetAddress)localEnumeration.nextElement();
      } while (localInetAddress.isLoopbackAddress());
      Object localObject = localInetAddress.getHostAddress();
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      b.v(z.class, "getCellIp exception . %s", new Object[] { localThrowable });
    }
    return null;
  }
  
  public static long h()
  {
    if (k != 0L) {
      return k;
    }
    try
    {
      StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
      long l1 = localStatFs.getBlockSize();
      k = localStatFs.getBlockCount() * l1 / 1024L;
      return k;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        b.v(z.class, "getTotalInternalStorgeSize exception . %s", new Object[] { localThrowable });
      }
    }
  }
  
  public static WifiInfo h(Context paramContext)
  {
    try
    {
      paramContext = (WifiManager)paramContext.getSystemService("wifi");
      if (paramContext != null)
      {
        paramContext = paramContext.getConnectionInfo();
        return paramContext;
      }
    }
    catch (Throwable paramContext)
    {
      b.v(z.class, "getWifiInfo exception . %s", new Object[] { paramContext });
    }
    return null;
  }
  
  public static int i(Context paramContext)
  {
    try
    {
      int m = Settings.System.getInt(paramContext.getContentResolver(), "screen_brightness");
      return m;
    }
    catch (Throwable paramContext)
    {
      b.v(z.class, "getScreenBrightness exception . %s", new Object[] { paramContext });
    }
    return 0;
  }
  
  public static long i()
  {
    try
    {
      StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
      long l1 = localStatFs.getBlockSize();
      l1 = localStatFs.getAvailableBlocks() * l1 / 1024L;
      return l1;
    }
    catch (Throwable localThrowable)
    {
      b.v(z.class, "getAvailInternalStorgeSize exception . %s", new Object[] { localThrowable });
    }
    return 0L;
  }
  
  public static long j()
  {
    if (l != 0L) {
      return l;
    }
    try
    {
      StatFs localStatFs;
      long l1;
      if (Environment.getExternalStorageState().equals("mounted"))
      {
        localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        l1 = localStatFs.getBlockSize();
      }
      for (l = localStatFs.getBlockCount() * l1 / 1024L;; l = 0L) {
        return l;
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        b.v(z.class, "getTotalExternalStorgeSize exception . %s", new Object[] { localThrowable });
      }
    }
  }
  
  public static boolean j(Context paramContext)
  {
    boolean bool = false;
    if (Settings.Secure.getInt(paramContext.getContentResolver(), "adb_enabled", 0) > 0) {
      bool = true;
    }
    return bool;
  }
  
  public static long k()
  {
    long l1 = 0L;
    try
    {
      if (Environment.getExternalStorageState().equals("mounted"))
      {
        StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        l1 = localStatFs.getBlockSize();
        l1 = localStatFs.getAvailableBlocks() * l1 / 1024L;
      }
      return l1;
    }
    catch (Throwable localThrowable)
    {
      b.v(z.class, "getAvailExternalStorgeSize exception . %s", new Object[] { localThrowable });
    }
    return 0L;
  }
  
  public static boolean k(Context paramContext)
  {
    try
    {
      if (z(paramContext, "android.permission.MODIFY_AUDIO_SETTINGS"))
      {
        boolean bool = ((AudioManager)paramContext.getSystemService("audio")).isWiredHeadsetOn();
        return bool;
      }
    }
    catch (Throwable paramContext)
    {
      b.v(z.class, "isHeadphone exception . %s", new Object[] { paramContext });
    }
    return false;
  }
  
  public static int l()
  {
    try
    {
      int m = Runtime.getRuntime().availableProcessors();
      return m;
    }
    catch (Throwable localThrowable)
    {
      b.v(z.class, "getAvailableProcessors exception . %s", new Object[] { localThrowable });
    }
    return -1;
  }
  
  public static int l(Context paramContext)
  {
    try
    {
      int m = paramContext.getResources().getConfiguration().orientation;
      if (m == 2) {
        return 1;
      }
      if (m == 1) {
        return 0;
      }
    }
    catch (Throwable paramContext)
    {
      b.v(z.class, "getDeviceOrientation exception . %s", new Object[] { paramContext });
    }
    return 0;
  }
  
  @SuppressLint({"NewApi"})
  public static long m(Context paramContext)
  {
    if (j != 0L) {
      return j;
    }
    if ((Build.VERSION.SDK_INT >= 16) && (paramContext != null)) {}
    for (;;)
    {
      try
      {
        ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager)paramContext.getSystemService("activity")).getMemoryInfo(localMemoryInfo);
        j = localMemoryInfo.totalMem / 1024L;
        return j;
      }
      catch (Throwable paramContext)
      {
        b.v(z.class, "getTotalMemory exception . %s", new Object[] { paramContext });
      }
      j = n();
    }
  }
  
  /* Error */
  @SuppressLint({"NewApi"})
  private static String m()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore 4
    //   5: getstatic 111	android/os/Build$VERSION:SDK_INT	I
    //   8: bipush 9
    //   10: if_icmplt +161 -> 171
    //   13: invokestatic 364	java/net/NetworkInterface:getNetworkInterfaces	()Ljava/util/Enumeration;
    //   16: astore 5
    //   18: aconst_null
    //   19: astore_2
    //   20: aload 5
    //   22: invokeinterface 369 1 0
    //   27: ifeq +150 -> 177
    //   30: aload 5
    //   32: invokeinterface 372 1 0
    //   37: checkcast 360	java/net/NetworkInterface
    //   40: astore_3
    //   41: aload_3
    //   42: invokevirtual 513	java/net/NetworkInterface:getName	()Ljava/lang/String;
    //   45: ldc_w 515
    //   48: invokevirtual 518	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   51: ifeq -31 -> 20
    //   54: aload_3
    //   55: invokevirtual 522	java/net/NetworkInterface:getHardwareAddress	()[B
    //   58: astore_3
    //   59: aload_3
    //   60: ifnull -40 -> 20
    //   63: aload_3
    //   64: arraylength
    //   65: ifeq -45 -> 20
    //   68: new 269	java/lang/StringBuilder
    //   71: dup
    //   72: invokespecial 270	java/lang/StringBuilder:<init>	()V
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
    //   89: ldc_w 524
    //   92: iconst_1
    //   93: anewarray 4	java/lang/Object
    //   96: dup
    //   97: iconst_0
    //   98: aload_3
    //   99: iload_0
    //   100: baload
    //   101: invokestatic 530	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
    //   104: aastore
    //   105: invokestatic 160	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   108: invokevirtual 278	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   111: pop
    //   112: iload_0
    //   113: iconst_1
    //   114: iadd
    //   115: istore_0
    //   116: goto -34 -> 82
    //   119: aload 4
    //   121: invokevirtual 531	java/lang/StringBuilder:length	()I
    //   124: ifle +16 -> 140
    //   127: aload 4
    //   129: aload 4
    //   131: invokevirtual 531	java/lang/StringBuilder:length	()I
    //   134: iconst_1
    //   135: isub
    //   136: invokevirtual 534	java/lang/StringBuilder:deleteCharAt	(I)Ljava/lang/StringBuilder;
    //   139: pop
    //   140: aload 4
    //   142: invokevirtual 281	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   145: astore_3
    //   146: aload_3
    //   147: astore_2
    //   148: goto -128 -> 20
    //   151: astore_3
    //   152: aload 4
    //   154: astore_2
    //   155: ldc 2
    //   157: ldc_w 536
    //   160: iconst_1
    //   161: anewarray 4	java/lang/Object
    //   164: dup
    //   165: iconst_0
    //   166: aload_3
    //   167: aastore
    //   168: invokestatic 85	com/yy/hiidostatis/inner/util/y/b:v	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   171: aload_2
    //   172: areturn
    //   173: astore_3
    //   174: goto -19 -> 155
    //   177: aload_2
    //   178: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   81	35	0	m	int
    //   79	6	1	n	int
    //   1	177	2	localObject1	Object
    //   40	107	3	localObject2	Object
    //   151	16	3	localThrowable1	Throwable
    //   173	1	3	localThrowable2	Throwable
    //   3	150	4	localStringBuilder	StringBuilder
    //   16	15	5	localEnumeration	Enumeration
    // Exception table:
    //   from	to	target	type
    //   5	18	151	java/lang/Throwable
    //   20	59	173	java/lang/Throwable
    //   63	80	173	java/lang/Throwable
    //   87	112	173	java/lang/Throwable
    //   119	140	173	java/lang/Throwable
    //   140	146	173	java/lang/Throwable
  }
  
  /* Error */
  private static long n()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: getstatic 45	com/yy/hiidostatis/inner/util/z:f	J
    //   5: lconst_0
    //   6: lcmp
    //   7: ifeq +7 -> 14
    //   10: getstatic 45	com/yy/hiidostatis/inner/util/z:f	J
    //   13: lreturn
    //   14: new 223	java/io/BufferedReader
    //   17: dup
    //   18: new 218	java/io/FileReader
    //   21: dup
    //   22: ldc_w 538
    //   25: invokespecial 221	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   28: bipush 8
    //   30: invokespecial 541	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   33: astore_1
    //   34: aload_1
    //   35: astore_0
    //   36: aload_1
    //   37: invokevirtual 229	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   40: astore_3
    //   41: aload_3
    //   42: ifnull +5 -> 47
    //   45: aload_3
    //   46: astore_2
    //   47: aload_1
    //   48: astore_0
    //   49: aload_2
    //   50: invokestatic 136	com/yy/hiidostatis/inner/util/g:z	(Ljava/lang/String;)Z
    //   53: ifne +35 -> 88
    //   56: aload_1
    //   57: astore_0
    //   58: aload_2
    //   59: aload_2
    //   60: bipush 58
    //   62: invokevirtual 545	java/lang/String:indexOf	(I)I
    //   65: iconst_1
    //   66: iadd
    //   67: aload_2
    //   68: bipush 107
    //   70: invokevirtual 545	java/lang/String:indexOf	(I)I
    //   73: invokevirtual 153	java/lang/String:substring	(II)Ljava/lang/String;
    //   76: invokevirtual 548	java/lang/String:trim	()Ljava/lang/String;
    //   79: invokevirtual 548	java/lang/String:trim	()Ljava/lang/String;
    //   82: invokestatic 554	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   85: putstatic 45	com/yy/hiidostatis/inner/util/z:f	J
    //   88: aload_1
    //   89: ifnull +7 -> 96
    //   92: aload_1
    //   93: invokevirtual 237	java/io/BufferedReader:close	()V
    //   96: getstatic 45	com/yy/hiidostatis/inner/util/z:f	J
    //   99: lreturn
    //   100: astore_2
    //   101: aconst_null
    //   102: astore_1
    //   103: aload_1
    //   104: astore_0
    //   105: ldc 2
    //   107: ldc_w 556
    //   110: iconst_1
    //   111: anewarray 4	java/lang/Object
    //   114: dup
    //   115: iconst_0
    //   116: aload_2
    //   117: aastore
    //   118: invokestatic 85	com/yy/hiidostatis/inner/util/y/b:v	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)V
    //   121: aload_1
    //   122: ifnull -26 -> 96
    //   125: aload_1
    //   126: invokevirtual 237	java/io/BufferedReader:close	()V
    //   129: goto -33 -> 96
    //   132: astore_0
    //   133: goto -37 -> 96
    //   136: astore_1
    //   137: aconst_null
    //   138: astore_0
    //   139: aload_0
    //   140: ifnull +7 -> 147
    //   143: aload_0
    //   144: invokevirtual 237	java/io/BufferedReader:close	()V
    //   147: aload_1
    //   148: athrow
    //   149: astore_0
    //   150: goto -3 -> 147
    //   153: astore_1
    //   154: goto -15 -> 139
    //   157: astore_2
    //   158: goto -55 -> 103
    // Local variable table:
    //   start	length	slot	name	signature
    //   35	70	0	localBufferedReader1	java.io.BufferedReader
    //   132	1	0	localIOException1	java.io.IOException
    //   138	6	0	localObject1	Object
    //   149	1	0	localIOException2	java.io.IOException
    //   33	93	1	localBufferedReader2	java.io.BufferedReader
    //   136	12	1	localObject2	Object
    //   153	1	1	localObject3	Object
    //   1	67	2	localObject4	Object
    //   100	17	2	localThrowable1	Throwable
    //   157	1	2	localThrowable2	Throwable
    //   40	6	3	str	String
    // Exception table:
    //   from	to	target	type
    //   14	34	100	java/lang/Throwable
    //   92	96	132	java/io/IOException
    //   125	129	132	java/io/IOException
    //   14	34	136	finally
    //   143	147	149	java/io/IOException
    //   36	41	153	finally
    //   49	56	153	finally
    //   58	88	153	finally
    //   105	121	153	finally
    //   36	41	157	java/lang/Throwable
    //   49	56	157	java/lang/Throwable
    //   58	88	157	java/lang/Throwable
  }
  
  public static long n(Context paramContext)
  {
    try
    {
      ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
      ((ActivityManager)paramContext.getSystemService("activity")).getMemoryInfo(localMemoryInfo);
      long l1 = localMemoryInfo.availMem / 1024L;
      return l1;
    }
    catch (Throwable paramContext)
    {
      b.v(z.class, "getAvailMemory exception . %s", new Object[] { paramContext });
    }
    return 0L;
  }
  
  public static int o(Context paramContext)
  {
    try
    {
      int m = ((AudioManager)paramContext.getSystemService("audio")).getStreamVolume(1);
      return m;
    }
    catch (Throwable paramContext)
    {
      b.v(z.class, "getVolume exception . %s", new Object[] { paramContext });
    }
    return -1;
  }
  
  public static String p(Context paramContext)
  {
    try
    {
      paramContext = (AudioManager)paramContext.getSystemService("audio");
      if (paramContext == null) {
        break label47;
      }
      m = paramContext.getRingerMode();
    }
    catch (Throwable paramContext)
    {
      b.v(z.class, "getSceneMode exception . %s", new Object[] { paramContext });
      break label76;
      label47:
      int m = -1;
      switch (m)
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
  
  public static String q(Context paramContext)
  {
    try
    {
      if (z(paramContext, "android.permission.BLUETOOTH"))
      {
        paramContext = BluetoothAdapter.getDefaultAdapter();
        if (paramContext != null) {
          return paramContext.getAddress();
        }
        return "";
      }
    }
    catch (Throwable paramContext)
    {
      b.v(z.class, "getBluetoothMac exception . %s", new Object[] { paramContext });
    }
    return "";
  }
  
  private static String r(Context paramContext)
  {
    if (a != null) {
      return a;
    }
    for (;;)
    {
      try
      {
        paramContext = (WifiManager)paramContext.getSystemService("wifi");
        if (paramContext != null)
        {
          paramContext = paramContext.getConnectionInfo();
          if (paramContext != null) {
            continue;
          }
          paramContext = null;
          a = paramContext;
        }
      }
      catch (Throwable paramContext)
      {
        b.v(z.class, "exception on getMacAddr : %s", new Object[] { paramContext });
        continue;
      }
      return a;
      paramContext = paramContext.getMacAddress();
    }
  }
  
  public static String u()
  {
    if (!TextUtils.isEmpty(g)) {
      return g;
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
      b.v(z.class, "getMaxCpuFreq exception: %s", new Object[] { localThrowable });
    }
    for (;;)
    {
      g = (String)localObject;
      return localObject;
      localThrowable.close();
    }
  }
  
  public static String u(Context paramContext)
  {
    if (w != null) {
      return w;
    }
    try
    {
      if (z(paramContext, "android.permission.READ_PHONE_STATE")) {
        w = ((TelephonyManager)paramContext.getSystemService("phone")).getSubscriberId();
      }
      return w;
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        b.v(z.class, "Exception when getImsi %s", new Object[] { paramContext });
      }
    }
  }
  
  public static String v()
  {
    if (d != null) {
      return d;
    }
    String str = String.format("Android%s", new Object[] { Build.VERSION.RELEASE });
    d = str;
    return str;
  }
  
  public static String v(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getPackageName();
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      b.v(z.class, "Failed to read package Name.", new Object[0]);
    }
    return "";
  }
  
  public static String w()
  {
    return Build.MODEL;
  }
  
  public static String w(Context paramContext)
  {
    try
    {
      if (y != null) {
        return y;
      }
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      y = paramContext;
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      b.v(z.class, "Failed to read version Name.", new Object[0]);
      y = "";
    }
    return "";
  }
  
  public static int x(Context paramContext)
  {
    try
    {
      if (z != -1) {
        return z;
      }
      int m = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      z = m;
      return m;
    }
    catch (Throwable paramContext)
    {
      b.v(z.class, "Failed to read version No.", new Object[0]);
      z = -1;
    }
    return -1;
  }
  
  public static String x()
  {
    return Build.MANUFACTURER;
  }
  
  @SuppressLint({"NewApi"})
  public static long y(int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 8) {
      return TrafficStats.getUidRxBytes(paramInt);
    }
    return 0L;
  }
  
  public static String y()
  {
    if (x != null) {
      return x;
    }
    Object localObject = Locale.getDefault();
    localObject = String.format("%s-%s", new Object[] { ((Locale)localObject).getLanguage(), ((Locale)localObject).getCountry() });
    x = (String)localObject;
    return localObject;
  }
  
  public static String y(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (g.z(paramString))) {
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
            b.y(z.class, "meta data key[%s] value is %s", new Object[] { paramString, paramContext });
            paramContext = String.valueOf(paramContext);
            return paramContext;
          }
        }
      }
    }
    catch (Throwable paramContext)
    {
      b.v(z.class, "read meta-data key[%s] from AndroidManifest.xml Exception.%s", new Object[] { paramString, paramContext });
    }
    return "";
  }
  
  public static boolean y(Context paramContext)
  {
    if (paramContext == null)
    {
      b.v(z.class, "the Input context is null!", new Object[0]);
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
            int m = paramContext.getType();
            if (m == 1) {
              return true;
            }
          }
          return false;
        }
      }
      catch (Throwable paramContext)
      {
        b.v(z.class, "isWifiActive Exception: %s", new Object[] { paramContext });
        return false;
      }
      paramContext = null;
    }
  }
  
  @SuppressLint({"NewApi"})
  public static long z(int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 8) {
      return TrafficStats.getUidTxBytes(paramInt);
    }
    return 0L;
  }
  
  public static boolean z()
  {
    boolean bool2 = false;
    Socket localSocket = new Socket();
    try
    {
      localSocket.connect(new InetSocketAddress("www.baidu.com", 80), 5000);
      boolean bool1 = localSocket.isConnected();
      bool2 = bool1;
      try
      {
        localThrowable1.close();
        throw localObject;
      }
      catch (Throwable localThrowable2)
      {
        for (;;) {}
      }
    }
    catch (Throwable localThrowable3)
    {
      try
      {
        localSocket.close();
        return bool1;
      }
      catch (Throwable localThrowable1)
      {
        return bool2;
      }
      localThrowable3 = localThrowable3;
      b.v(z.class, "isNetworkReach Exception: %s", new Object[] { localThrowable3 });
      localSocket.close();
      return false;
    }
    finally {}
  }
  
  public static boolean z(Context paramContext)
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
        b.v(z.class, "isNetworkAvailable Exception: %s", new Object[] { paramContext });
        return false;
      }
      paramContext = null;
    }
  }
  
  public static boolean z(Context paramContext, String paramString)
  {
    for (boolean bool = true;; bool = false) {
      try
      {
        if (Build.VERSION.SDK_INT >= 23)
        {
          if (((Integer)Class.forName("android.content.Context").getMethod("checkSelfPermission", new Class[] { String.class }).invoke(paramContext, new Object[] { paramString })).intValue() != 0) {
            break;
          }
          return true;
        }
        int m = paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName());
        if (m == 0) {
          return bool;
        }
      }
      catch (Throwable paramContext)
      {
        b.v(z.class, "checkPermissions Throwable: %s", new Object[] { paramContext });
        return false;
      }
    }
    return false;
  }
  
  public static boolean z(String paramString)
  {
    return (!TextUtils.isEmpty(paramString)) && (!paramString.equalsIgnoreCase("02:00:00:00:00:00"));
  }
}
