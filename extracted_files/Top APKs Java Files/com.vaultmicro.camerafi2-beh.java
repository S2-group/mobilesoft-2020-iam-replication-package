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
import android.hardware.Sensor;
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
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class beh
{
  public beh() {}
  
  public static String A()
  {
    return Build.BOARD;
  }
  
  public static String B()
  {
    return Build.BOOTLOADER;
  }
  
  public static String C()
  {
    return Build.BRAND;
  }
  
  public static String D()
  {
    return Build.DEVICE;
  }
  
  public static String E()
  {
    return Build.HARDWARE;
  }
  
  public static String F()
  {
    return Build.HOST;
  }
  
  public static String G()
  {
    return Build.PRODUCT;
  }
  
  public static ArrayList<String> H()
  {
    if (a() < 21) {
      return J();
    }
    return K();
  }
  
  public static List<Sensor> I()
  {
    if (bfb.b() != null) {
      return ((SensorManager)bfb.b().getSystemService("sensor")).getSensorList(-1);
    }
    return null;
  }
  
  private static ArrayList<String> J()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(Build.CPU_ABI);
    localArrayList.add(Build.CPU_ABI2);
    return localArrayList;
  }
  
  @TargetApi(21)
  private static ArrayList<String> K()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(Arrays.asList(Build.SUPPORTED_ABIS));
    return localArrayList;
  }
  
  public static int a()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public static int a(int paramInt)
  {
    if (bfb.b() != null)
    {
      AudioManager localAudioManager = (AudioManager)bfb.b().getSystemService("audio");
      if (localAudioManager != null) {
        return localAudioManager.getStreamVolume(paramInt);
      }
      return -2;
    }
    return -1;
  }
  
  /* Error */
  private static long a(beh.a paramA)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 7
    //   6: iconst_m1
    //   7: istore_1
    //   8: getstatic 132	beh$1:a	[I
    //   11: aload_0
    //   12: invokevirtual 135	beh$a:ordinal	()I
    //   15: iaload
    //   16: tableswitch	default:+24->40, 1:+173->189, 2:+178->194
    //   40: new 137	java/io/RandomAccessFile
    //   43: dup
    //   44: ldc -117
    //   46: ldc -115
    //   48: invokespecial 144	java/io/RandomAccessFile:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   51: astore 6
    //   53: aconst_null
    //   54: astore 5
    //   56: iconst_0
    //   57: istore_2
    //   58: iload_2
    //   59: iload_1
    //   60: if_icmpge +17 -> 77
    //   63: aload 6
    //   65: invokevirtual 147	java/io/RandomAccessFile:readLine	()Ljava/lang/String;
    //   68: astore 5
    //   70: iload_2
    //   71: iconst_1
    //   72: iadd
    //   73: istore_2
    //   74: goto -16 -> 58
    //   77: aload 5
    //   79: invokestatic 150	beh:b	(Ljava/lang/String;)J
    //   82: lstore_3
    //   83: aload 6
    //   85: invokevirtual 153	java/io/RandomAccessFile:close	()V
    //   88: lload_3
    //   89: lreturn
    //   90: astore_0
    //   91: ldc -101
    //   93: aload_0
    //   94: invokestatic 160	beq:a	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   97: lload_3
    //   98: lreturn
    //   99: astore 5
    //   101: aload 7
    //   103: astore 6
    //   105: aload 5
    //   107: astore 7
    //   109: aload 6
    //   111: astore 5
    //   113: new 162	java/lang/StringBuilder
    //   116: dup
    //   117: invokespecial 163	java/lang/StringBuilder:<init>	()V
    //   120: ldc -91
    //   122: invokevirtual 169	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   125: aload_0
    //   126: invokevirtual 172	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   129: invokevirtual 175	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   132: aload 7
    //   134: invokestatic 160	beq:a	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   137: aload 6
    //   139: invokevirtual 153	java/io/RandomAccessFile:close	()V
    //   142: ldc2_w 176
    //   145: lreturn
    //   146: astore_0
    //   147: ldc -101
    //   149: aload_0
    //   150: invokestatic 160	beq:a	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   153: goto -11 -> 142
    //   156: astore_0
    //   157: aload 5
    //   159: invokevirtual 153	java/io/RandomAccessFile:close	()V
    //   162: aload_0
    //   163: athrow
    //   164: astore 5
    //   166: ldc -101
    //   168: aload 5
    //   170: invokestatic 160	beq:a	(Ljava/lang/String;Ljava/lang/Exception;)V
    //   173: goto -11 -> 162
    //   176: astore_0
    //   177: aload 6
    //   179: astore 5
    //   181: goto -24 -> 157
    //   184: astore 7
    //   186: goto -77 -> 109
    //   189: iconst_1
    //   190: istore_1
    //   191: goto -151 -> 40
    //   194: iconst_2
    //   195: istore_1
    //   196: goto -156 -> 40
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	199	0	paramA	beh.a
    //   7	189	1	i	int
    //   57	17	2	j	int
    //   82	16	3	l	long
    //   1	77	5	str	String
    //   99	7	5	localIOException1	java.io.IOException
    //   111	47	5	localObject1	Object
    //   164	5	5	localIOException2	java.io.IOException
    //   179	1	5	localObject2	Object
    //   51	127	6	localObject3	Object
    //   4	129	7	localIOException3	java.io.IOException
    //   184	1	7	localIOException4	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   83	88	90	java/io/IOException
    //   40	53	99	java/io/IOException
    //   137	142	146	java/io/IOException
    //   40	53	156	finally
    //   113	137	156	finally
    //   157	162	164	java/io/IOException
    //   63	70	176	finally
    //   77	83	176	finally
    //   63	70	184	java/io/IOException
    //   77	83	184	java/io/IOException
  }
  
  public static long a(File paramFile)
  {
    if ((paramFile != null) && (paramFile.exists())) {
      return Math.round((float)(paramFile.getFreeSpace() / 1024L));
    }
    return -1L;
  }
  
  public static String a(String paramString1, String paramString2)
  {
    if (paramString2 != null) {
      return System.getProperty(paramString1, paramString2);
    }
    return System.getProperty(paramString1);
  }
  
  public static List<Map<String, Object>> a(boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    if (bfb.b() != null)
    {
      PackageManager localPackageManager = bfb.b().getPackageManager();
      Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
      if (localIterator.hasNext())
      {
        Object localObject = (PackageInfo)localIterator.next();
        HashMap localHashMap = new HashMap();
        if (paramBoolean) {
          localHashMap.put("name", bey.a(((PackageInfo)localObject).packageName));
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
  
  public static boolean a(String paramString)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    Object localObject;
    if (bfb.b() != null) {
      localObject = bfb.b().getPackageManager();
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
      beq.a("Couldn't find package: " + paramString, localNameNotFoundException);
    }
    return false;
  }
  
  public static long b(File paramFile)
  {
    if ((paramFile != null) && (paramFile.exists())) {
      return Math.round((float)(paramFile.getTotalSpace() / 1024L));
    }
    return -1L;
  }
  
  private static long b(String paramString)
  {
    if (paramString != null)
    {
      Matcher localMatcher = Pattern.compile("(\\d+)").matcher(paramString);
      for (paramString = ""; localMatcher.find(); paramString = localMatcher.group(1)) {}
      return Long.parseLong(paramString);
    }
    return -1L;
  }
  
  public static String b()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static String c()
  {
    return Build.MANUFACTURER;
  }
  
  private static boolean c(String paramString)
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
  
  public static String d()
  {
    return Build.MODEL;
  }
  
  public static int e()
  {
    if (bfb.b() != null) {
      return bfb.b().getResources().getConfiguration().screenLayout;
    }
    return -1;
  }
  
  @SuppressLint({"DefaultLocale"})
  public static String f()
  {
    try
    {
      String str = Settings.Secure.getString(bfb.b().getContentResolver(), "android_id");
      return str;
    }
    catch (Exception localException)
    {
      beq.a("Problems fetching androidId", localException);
    }
    return null;
  }
  
  public static String g()
  {
    return beg.a();
  }
  
  public static boolean h()
  {
    return beg.b();
  }
  
  public static boolean i()
  {
    ConnectivityManager localConnectivityManager;
    if (bfb.b() != null)
    {
      localConnectivityManager = (ConnectivityManager)bfb.b().getSystemService("connectivity");
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
      localTelephonyManager = (TelephonyManager)bfb.b().getSystemService("phone");
      localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
    } while ((localNetworkInfo == null) || (!localConnectivityManager.getBackgroundDataSetting()) || (!localConnectivityManager.getActiveNetworkInfo().isConnected()) || (localTelephonyManager == null));
    if ((localNetworkInfo.getType() == 1) && (localNetworkInfo.isConnected())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public static int j()
  {
    if (bfb.b() != null) {
      return ((TelephonyManager)bfb.b().getSystemService("phone")).getNetworkType();
    }
    return -1;
  }
  
  public static String k()
  {
    if (bfb.b() != null) {
      return ((TelephonyManager)bfb.b().getSystemService("phone")).getNetworkOperator();
    }
    return "";
  }
  
  public static String l()
  {
    if (bfb.b() != null) {
      return ((TelephonyManager)bfb.b().getSystemService("phone")).getNetworkOperatorName();
    }
    return "";
  }
  
  public static int m()
  {
    if (bfb.b() != null) {
      return bfb.b().getResources().getDisplayMetrics().densityDpi;
    }
    return -1;
  }
  
  public static int n()
  {
    if (bfb.b() != null) {
      return bfb.b().getResources().getDisplayMetrics().widthPixels;
    }
    return -1;
  }
  
  public static int o()
  {
    if (bfb.b() != null) {
      return bfb.b().getResources().getDisplayMetrics().heightPixels;
    }
    return -1;
  }
  
  public static boolean p()
  {
    if (bfb.b() != null)
    {
      Object localObject = (ConnectivityManager)bfb.b().getSystemService("connectivity");
      if (localObject != null)
      {
        localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
        return (localObject != null) && (((NetworkInfo)localObject).isConnected());
      }
    }
    return false;
  }
  
  public static String q()
  {
    return UUID.randomUUID().toString();
  }
  
  public static boolean r()
  {
    if (bfb.b() != null) {
      return ((AudioManager)bfb.b().getSystemService("audio")).isWiredHeadsetOn();
    }
    return false;
  }
  
  public static int s()
  {
    if (bfb.b() != null)
    {
      AudioManager localAudioManager = (AudioManager)bfb.b().getSystemService("audio");
      if (localAudioManager != null) {
        return localAudioManager.getRingerMode();
      }
      return -2;
    }
    return -1;
  }
  
  public static int t()
  {
    int i = -1;
    if (bfb.b() != null) {
      i = Settings.System.getInt(bfb.b().getContentResolver(), "screen_brightness", -1);
    }
    return i;
  }
  
  public static float u()
  {
    if (bfb.b() != null)
    {
      Intent localIntent = bfb.b().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      if (localIntent != null)
      {
        int i = localIntent.getIntExtra("level", -1);
        int j = localIntent.getIntExtra("scale", -1);
        return i / j;
      }
    }
    return -1.0F;
  }
  
  public static int v()
  {
    int j = -1;
    int i = j;
    if (bfb.b() != null)
    {
      Intent localIntent = bfb.b().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      i = j;
      if (localIntent != null) {
        i = localIntent.getIntExtra("status", -1);
      }
    }
    return i;
  }
  
  public static long w()
  {
    return a(beh.a.a);
  }
  
  public static long x()
  {
    return a(beh.a.b);
  }
  
  public static boolean y()
  {
    try
    {
      boolean bool = c("su");
      return bool;
    }
    catch (Exception localException)
    {
      beq.a("Rooted check failed", localException);
    }
    return false;
  }
  
  public static String z()
  {
    if (bfb.b() != null)
    {
      Object localObject = (ActivityManager)bfb.b().getSystemService("activity");
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
  
  public static enum a
  {
    private a() {}
  }
}
