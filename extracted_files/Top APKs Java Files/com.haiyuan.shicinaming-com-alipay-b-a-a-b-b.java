package com.alipay.b.a.a.b;

import android.app.KeyguardManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.alipay.b.a.a.a.a;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

public final class b
{
  private static b a = new b();
  
  private b() {}
  
  public static b a()
  {
    return a;
  }
  
  private static String a(BluetoothAdapter paramBluetoothAdapter)
  {
    try
    {
      Object localObject = BluetoothAdapter.class.getDeclaredField("mService");
      ((Field)localObject).setAccessible(true);
      paramBluetoothAdapter = ((Field)localObject).get(paramBluetoothAdapter);
      if (paramBluetoothAdapter == null) {
        return null;
      }
      localObject = paramBluetoothAdapter.getClass().getDeclaredMethod("getAddress", new Class[0]);
      ((Method)localObject).setAccessible(true);
      paramBluetoothAdapter = ((Method)localObject).invoke(paramBluetoothAdapter, new Object[0]);
      if ((paramBluetoothAdapter != null) && ((paramBluetoothAdapter instanceof String)))
      {
        paramBluetoothAdapter = (String)paramBluetoothAdapter;
        return paramBluetoothAdapter;
      }
    }
    catch (Throwable paramBluetoothAdapter) {}
    return null;
  }
  
  public static String a(Context paramContext)
  {
    Object localObject;
    if (a(paramContext, "android.permission.READ_PHONE_STATE"))
    {
      localObject = "";
      return localObject;
    }
    if (paramContext != null) {}
    for (;;)
    {
      try
      {
        paramContext = (TelephonyManager)paramContext.getSystemService("phone");
        if (paramContext != null)
        {
          paramContext = paramContext.getDeviceId();
          localObject = paramContext;
          if (paramContext != null) {
            break;
          }
          return "";
        }
      }
      catch (Throwable paramContext) {}
      paramContext = null;
    }
  }
  
  private static boolean a(Context paramContext, String paramString)
  {
    if (paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName()) == 0) {}
    for (int i = 1; i == 0; i = 0) {
      return true;
    }
    return false;
  }
  
  public static String b()
  {
    long l1 = 0L;
    try
    {
      StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
      long l2 = localStatFs.getBlockSize();
      int i = localStatFs.getAvailableBlocks();
      l1 = i * l2;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return String.valueOf(l1);
  }
  
  public static String b(Context paramContext)
  {
    Object localObject;
    if (a(paramContext, "android.permission.READ_PHONE_STATE"))
    {
      localObject = "";
      return localObject;
    }
    if (paramContext != null) {}
    for (;;)
    {
      try
      {
        paramContext = (TelephonyManager)paramContext.getSystemService("phone");
        if (paramContext != null)
        {
          paramContext = paramContext.getSubscriberId();
          localObject = paramContext;
          if (paramContext != null) {
            break;
          }
          return "";
        }
      }
      catch (Throwable paramContext) {}
      paramContext = "";
    }
  }
  
  public static String c()
  {
    l2 = 0L;
    l1 = l2;
    try
    {
      if ("mounted".equals(Environment.getExternalStorageState()))
      {
        StatFs localStatFs = new StatFs(a.a().getPath());
        l1 = localStatFs.getBlockSize();
        int i = localStatFs.getAvailableBlocks();
        l1 = i * l1;
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        l1 = l2;
      }
    }
    return String.valueOf(l1);
  }
  
  public static String c(Context paramContext)
  {
    int i = 0;
    try
    {
      int j = Settings.System.getInt(paramContext.getContentResolver(), "airplane_mode_on", 0);
      i = j;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    if (i == 1) {
      return "1";
    }
    return "0";
  }
  
  /* Error */
  public static String d()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore 4
    //   6: new 157	java/io/FileInputStream
    //   9: dup
    //   10: new 104	java/io/File
    //   13: dup
    //   14: ldc -97
    //   16: invokespecial 160	java/io/File:<init>	(Ljava/lang/String;)V
    //   19: invokespecial 163	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   22: astore_1
    //   23: new 165	java/io/InputStreamReader
    //   26: dup
    //   27: aload_1
    //   28: invokespecial 168	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   31: astore_3
    //   32: new 170	java/io/LineNumberReader
    //   35: dup
    //   36: aload_3
    //   37: invokespecial 173	java/io/LineNumberReader:<init>	(Ljava/io/Reader;)V
    //   40: astore 4
    //   42: iconst_1
    //   43: istore_0
    //   44: iload_0
    //   45: bipush 100
    //   47: if_icmpge +255 -> 302
    //   50: aload 4
    //   52: invokevirtual 176	java/io/LineNumberReader:readLine	()Ljava/lang/String;
    //   55: astore_2
    //   56: aload_2
    //   57: ifnull +245 -> 302
    //   60: aload_2
    //   61: ldc -78
    //   63: invokevirtual 182	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   66: iflt +49 -> 115
    //   69: aload_2
    //   70: aload_2
    //   71: ldc -72
    //   73: invokevirtual 182	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   76: iconst_1
    //   77: iadd
    //   78: aload_2
    //   79: invokevirtual 187	java/lang/String:length	()I
    //   82: invokevirtual 191	java/lang/String:substring	(II)Ljava/lang/String;
    //   85: invokevirtual 194	java/lang/String:trim	()Ljava/lang/String;
    //   88: astore_2
    //   89: aload 4
    //   91: invokevirtual 197	java/io/LineNumberReader:close	()V
    //   94: aload_3
    //   95: invokevirtual 198	java/io/InputStreamReader:close	()V
    //   98: aload_1
    //   99: invokevirtual 199	java/io/FileInputStream:close	()V
    //   102: aload_2
    //   103: astore_1
    //   104: aload_1
    //   105: astore_2
    //   106: aload_1
    //   107: ifnonnull +6 -> 113
    //   110: ldc 66
    //   112: astore_2
    //   113: aload_2
    //   114: areturn
    //   115: iload_0
    //   116: iconst_1
    //   117: iadd
    //   118: istore_0
    //   119: goto -75 -> 44
    //   122: astore_1
    //   123: aload_2
    //   124: astore_1
    //   125: goto -21 -> 104
    //   128: astore_1
    //   129: aconst_null
    //   130: astore_1
    //   131: aconst_null
    //   132: astore_2
    //   133: aload 4
    //   135: astore_3
    //   136: aload_1
    //   137: ifnull +7 -> 144
    //   140: aload_1
    //   141: invokevirtual 197	java/io/LineNumberReader:close	()V
    //   144: aload_3
    //   145: ifnull +7 -> 152
    //   148: aload_3
    //   149: invokevirtual 198	java/io/InputStreamReader:close	()V
    //   152: aload_2
    //   153: ifnull +143 -> 296
    //   156: aload_2
    //   157: invokevirtual 199	java/io/FileInputStream:close	()V
    //   160: ldc -55
    //   162: astore_1
    //   163: goto -59 -> 104
    //   166: astore_1
    //   167: ldc -55
    //   169: astore_1
    //   170: goto -66 -> 104
    //   173: astore_2
    //   174: aconst_null
    //   175: astore_3
    //   176: aconst_null
    //   177: astore_1
    //   178: aload 5
    //   180: astore 4
    //   182: aload 4
    //   184: ifnull +8 -> 192
    //   187: aload 4
    //   189: invokevirtual 197	java/io/LineNumberReader:close	()V
    //   192: aload_3
    //   193: ifnull +7 -> 200
    //   196: aload_3
    //   197: invokevirtual 198	java/io/InputStreamReader:close	()V
    //   200: aload_1
    //   201: ifnull +7 -> 208
    //   204: aload_1
    //   205: invokevirtual 199	java/io/FileInputStream:close	()V
    //   208: aload_2
    //   209: athrow
    //   210: astore 4
    //   212: goto -118 -> 94
    //   215: astore_3
    //   216: goto -118 -> 98
    //   219: astore_1
    //   220: goto -76 -> 144
    //   223: astore_1
    //   224: goto -72 -> 152
    //   227: astore 4
    //   229: goto -37 -> 192
    //   232: astore_3
    //   233: goto -33 -> 200
    //   236: astore_1
    //   237: goto -29 -> 208
    //   240: astore_2
    //   241: aconst_null
    //   242: astore_3
    //   243: aload 5
    //   245: astore 4
    //   247: goto -65 -> 182
    //   250: astore_2
    //   251: aload 5
    //   253: astore 4
    //   255: goto -73 -> 182
    //   258: astore_2
    //   259: goto -77 -> 182
    //   262: astore_2
    //   263: aconst_null
    //   264: astore_3
    //   265: aload_1
    //   266: astore_2
    //   267: aload_3
    //   268: astore_1
    //   269: aload 4
    //   271: astore_3
    //   272: goto -136 -> 136
    //   275: astore_2
    //   276: aconst_null
    //   277: astore 4
    //   279: aload_1
    //   280: astore_2
    //   281: aload 4
    //   283: astore_1
    //   284: goto -148 -> 136
    //   287: astore_2
    //   288: aload_1
    //   289: astore_2
    //   290: aload 4
    //   292: astore_1
    //   293: goto -157 -> 136
    //   296: ldc -55
    //   298: astore_1
    //   299: goto -195 -> 104
    //   302: ldc -55
    //   304: astore_2
    //   305: goto -216 -> 89
    // Local variable table:
    //   start	length	slot	name	signature
    //   43	76	0	i	int
    //   22	85	1	localObject1	Object
    //   122	1	1	localThrowable1	Throwable
    //   124	1	1	localObject2	Object
    //   128	1	1	localThrowable2	Throwable
    //   130	33	1	str1	String
    //   166	1	1	localThrowable3	Throwable
    //   169	36	1	str2	String
    //   219	1	1	localThrowable4	Throwable
    //   223	1	1	localThrowable5	Throwable
    //   236	30	1	localThrowable6	Throwable
    //   268	31	1	localObject3	Object
    //   55	102	2	localObject4	Object
    //   173	36	2	localObject5	Object
    //   240	1	2	localObject6	Object
    //   250	1	2	localObject7	Object
    //   258	1	2	localObject8	Object
    //   262	1	2	localThrowable7	Throwable
    //   266	1	2	localThrowable8	Throwable
    //   275	1	2	localThrowable9	Throwable
    //   280	1	2	localObject9	Object
    //   287	1	2	localThrowable10	Throwable
    //   289	16	2	localObject10	Object
    //   31	166	3	localObject11	Object
    //   215	1	3	localThrowable11	Throwable
    //   232	1	3	localThrowable12	Throwable
    //   242	30	3	localObject12	Object
    //   4	184	4	localObject13	Object
    //   210	1	4	localThrowable13	Throwable
    //   227	1	4	localThrowable14	Throwable
    //   245	46	4	localObject14	Object
    //   1	251	5	localObject15	Object
    // Exception table:
    //   from	to	target	type
    //   98	102	122	java/lang/Throwable
    //   6	23	128	java/lang/Throwable
    //   156	160	166	java/lang/Throwable
    //   6	23	173	finally
    //   89	94	210	java/lang/Throwable
    //   94	98	215	java/lang/Throwable
    //   140	144	219	java/lang/Throwable
    //   148	152	223	java/lang/Throwable
    //   187	192	227	java/lang/Throwable
    //   196	200	232	java/lang/Throwable
    //   204	208	236	java/lang/Throwable
    //   23	32	240	finally
    //   32	42	250	finally
    //   50	56	258	finally
    //   60	89	258	finally
    //   23	32	262	java/lang/Throwable
    //   32	42	275	java/lang/Throwable
    //   50	56	287	java/lang/Throwable
    //   60	89	287	java/lang/Throwable
  }
  
  public static String d(Context paramContext)
  {
    int i = 1;
    JSONObject localJSONObject = new JSONObject();
    for (;;)
    {
      try
      {
        paramContext = (AudioManager)paramContext.getSystemService("audio");
        if (paramContext.getRingerMode() != 0) {
          continue;
        }
        int j = paramContext.getStreamVolume(0);
        int k = paramContext.getStreamVolume(1);
        int m = paramContext.getStreamVolume(2);
        int n = paramContext.getStreamVolume(3);
        int i1 = paramContext.getStreamVolume(4);
        localJSONObject.put("ringermode", String.valueOf(i));
        localJSONObject.put("call", String.valueOf(j));
        localJSONObject.put("system", String.valueOf(k));
        localJSONObject.put("ring", String.valueOf(m));
        localJSONObject.put("music", String.valueOf(n));
        localJSONObject.put("alarm", String.valueOf(i1));
      }
      catch (Throwable paramContext)
      {
        continue;
      }
      return localJSONObject.toString();
      i = 0;
    }
  }
  
  public static String e(Context paramContext)
  {
    if (paramContext != null) {}
    for (;;)
    {
      try
      {
        paramContext = (TelephonyManager)paramContext.getSystemService("phone");
        if (paramContext != null)
        {
          paramContext = paramContext.getNetworkOperatorName();
          Object localObject;
          if (paramContext != null)
          {
            localObject = paramContext;
            if (!"null".equals(paramContext)) {}
          }
          else
          {
            localObject = "";
          }
          return localObject;
        }
      }
      catch (Throwable paramContext) {}
      paramContext = null;
    }
  }
  
  public static String f()
  {
    String str = w();
    if (!a.a(str)) {
      return str;
    }
    return x();
  }
  
  public static String f(Context paramContext)
  {
    Object localObject;
    if (a(paramContext, "android.permission.READ_PHONE_STATE"))
    {
      localObject = "";
      return localObject;
    }
    if (paramContext != null) {}
    for (;;)
    {
      try
      {
        paramContext = (TelephonyManager)paramContext.getSystemService("phone");
        if (paramContext != null)
        {
          paramContext = paramContext.getLine1Number();
          localObject = paramContext;
          if (paramContext != null) {
            break;
          }
          return "";
        }
      }
      catch (Throwable paramContext) {}
      paramContext = "";
    }
  }
  
  /* Error */
  public static String g()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: new 259	java/io/FileReader
    //   5: dup
    //   6: ldc -97
    //   8: invokespecial 260	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   11: astore_0
    //   12: new 262	java/io/BufferedReader
    //   15: dup
    //   16: aload_0
    //   17: invokespecial 263	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   20: astore_2
    //   21: aload_2
    //   22: invokevirtual 264	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   25: ldc_w 266
    //   28: iconst_2
    //   29: invokevirtual 270	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   32: astore_1
    //   33: aload_1
    //   34: ifnull +23 -> 57
    //   37: aload_1
    //   38: arraylength
    //   39: iconst_1
    //   40: if_icmple +17 -> 57
    //   43: aload_1
    //   44: iconst_1
    //   45: aaload
    //   46: astore_1
    //   47: aload_0
    //   48: invokevirtual 271	java/io/FileReader:close	()V
    //   51: aload_2
    //   52: invokevirtual 272	java/io/BufferedReader:close	()V
    //   55: aload_1
    //   56: areturn
    //   57: aload_0
    //   58: invokevirtual 271	java/io/FileReader:close	()V
    //   61: aload_2
    //   62: invokevirtual 272	java/io/BufferedReader:close	()V
    //   65: ldc 66
    //   67: areturn
    //   68: astore_0
    //   69: aconst_null
    //   70: astore_0
    //   71: aload_0
    //   72: ifnull +7 -> 79
    //   75: aload_0
    //   76: invokevirtual 271	java/io/FileReader:close	()V
    //   79: aload_1
    //   80: ifnull -15 -> 65
    //   83: aload_1
    //   84: invokevirtual 272	java/io/BufferedReader:close	()V
    //   87: goto -22 -> 65
    //   90: astore_0
    //   91: goto -26 -> 65
    //   94: astore_1
    //   95: aconst_null
    //   96: astore_0
    //   97: aconst_null
    //   98: astore_2
    //   99: aload_0
    //   100: ifnull +7 -> 107
    //   103: aload_0
    //   104: invokevirtual 271	java/io/FileReader:close	()V
    //   107: aload_2
    //   108: ifnull +7 -> 115
    //   111: aload_2
    //   112: invokevirtual 272	java/io/BufferedReader:close	()V
    //   115: aload_1
    //   116: athrow
    //   117: astore_0
    //   118: goto -67 -> 51
    //   121: astore_0
    //   122: aload_1
    //   123: areturn
    //   124: astore_0
    //   125: goto -64 -> 61
    //   128: astore_0
    //   129: goto -64 -> 65
    //   132: astore_0
    //   133: goto -54 -> 79
    //   136: astore_0
    //   137: goto -30 -> 107
    //   140: astore_0
    //   141: goto -26 -> 115
    //   144: astore_1
    //   145: aconst_null
    //   146: astore_2
    //   147: goto -48 -> 99
    //   150: astore_1
    //   151: goto -52 -> 99
    //   154: astore_2
    //   155: goto -84 -> 71
    //   158: astore_1
    //   159: aload_2
    //   160: astore_1
    //   161: goto -90 -> 71
    // Local variable table:
    //   start	length	slot	name	signature
    //   11	47	0	localFileReader	java.io.FileReader
    //   68	1	0	localThrowable1	Throwable
    //   70	6	0	localObject1	Object
    //   90	1	0	localThrowable2	Throwable
    //   96	8	0	localObject2	Object
    //   117	1	0	localThrowable3	Throwable
    //   121	1	0	localThrowable4	Throwable
    //   124	1	0	localThrowable5	Throwable
    //   128	1	0	localThrowable6	Throwable
    //   132	1	0	localThrowable7	Throwable
    //   136	1	0	localThrowable8	Throwable
    //   140	1	0	localThrowable9	Throwable
    //   1	83	1	localObject3	Object
    //   94	29	1	str	String
    //   144	1	1	localObject4	Object
    //   150	1	1	localObject5	Object
    //   158	1	1	localThrowable10	Throwable
    //   160	1	1	localObject6	Object
    //   20	127	2	localBufferedReader	java.io.BufferedReader
    //   154	6	2	localThrowable11	Throwable
    // Exception table:
    //   from	to	target	type
    //   2	12	68	java/lang/Throwable
    //   83	87	90	java/lang/Throwable
    //   2	12	94	finally
    //   47	51	117	java/lang/Throwable
    //   51	55	121	java/lang/Throwable
    //   57	61	124	java/lang/Throwable
    //   61	65	128	java/lang/Throwable
    //   75	79	132	java/lang/Throwable
    //   103	107	136	java/lang/Throwable
    //   111	115	140	java/lang/Throwable
    //   12	21	144	finally
    //   21	33	150	finally
    //   37	43	150	finally
    //   12	21	154	java/lang/Throwable
    //   21	33	158	java/lang/Throwable
    //   37	43	158	java/lang/Throwable
  }
  
  public static String g(Context paramContext)
  {
    Object localObject;
    if (paramContext != null) {
      try
      {
        paramContext = (SensorManager)paramContext.getSystemService("sensor");
        if (paramContext != null)
        {
          localObject = paramContext.getSensorList(-1);
          if ((localObject != null) && (((List)localObject).size() > 0))
          {
            paramContext = new StringBuilder();
            localObject = ((List)localObject).iterator();
            while (((Iterator)localObject).hasNext())
            {
              Sensor localSensor = (Sensor)((Iterator)localObject).next();
              paramContext.append(localSensor.getName());
              paramContext.append(localSensor.getVersion());
              paramContext.append(localSensor.getVendor());
            }
          }
        }
        paramContext = null;
      }
      catch (Throwable paramContext) {}
    }
    for (;;)
    {
      localObject = paramContext;
      if (paramContext == null) {
        localObject = "";
      }
      return localObject;
      paramContext = a.e(paramContext.toString());
    }
  }
  
  /* Error */
  public static String h()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: lconst_0
    //   4: lstore_3
    //   5: new 259	java/io/FileReader
    //   8: dup
    //   9: ldc_w 327
    //   12: invokespecial 260	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   15: astore 6
    //   17: new 262	java/io/BufferedReader
    //   20: dup
    //   21: aload 6
    //   23: sipush 8192
    //   26: invokespecial 330	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   29: astore 7
    //   31: aload 7
    //   33: invokevirtual 264	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   36: astore 5
    //   38: lload_3
    //   39: lstore_1
    //   40: aload 5
    //   42: ifnull +20 -> 62
    //   45: aload 5
    //   47: ldc_w 332
    //   50: invokevirtual 335	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   53: iconst_1
    //   54: aaload
    //   55: invokestatic 340	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   58: istore_0
    //   59: iload_0
    //   60: i2l
    //   61: lstore_1
    //   62: aload 6
    //   64: invokevirtual 271	java/io/FileReader:close	()V
    //   67: aload 7
    //   69: invokevirtual 272	java/io/BufferedReader:close	()V
    //   72: lload_1
    //   73: invokestatic 121	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   76: areturn
    //   77: astore 6
    //   79: aconst_null
    //   80: astore 6
    //   82: aload 6
    //   84: ifnull +8 -> 92
    //   87: aload 6
    //   89: invokevirtual 271	java/io/FileReader:close	()V
    //   92: lload_3
    //   93: lstore_1
    //   94: aload 5
    //   96: ifnull -24 -> 72
    //   99: aload 5
    //   101: invokevirtual 272	java/io/BufferedReader:close	()V
    //   104: lload_3
    //   105: lstore_1
    //   106: goto -34 -> 72
    //   109: astore 5
    //   111: lload_3
    //   112: lstore_1
    //   113: goto -41 -> 72
    //   116: astore 5
    //   118: aconst_null
    //   119: astore 8
    //   121: aconst_null
    //   122: astore 6
    //   124: aload 8
    //   126: ifnull +8 -> 134
    //   129: aload 8
    //   131: invokevirtual 271	java/io/FileReader:close	()V
    //   134: aload 6
    //   136: ifnull +8 -> 144
    //   139: aload 6
    //   141: invokevirtual 272	java/io/BufferedReader:close	()V
    //   144: aload 5
    //   146: athrow
    //   147: astore 5
    //   149: goto -82 -> 67
    //   152: astore 5
    //   154: goto -82 -> 72
    //   157: astore 6
    //   159: goto -67 -> 92
    //   162: astore 7
    //   164: goto -30 -> 134
    //   167: astore 6
    //   169: goto -25 -> 144
    //   172: astore 5
    //   174: aconst_null
    //   175: astore 7
    //   177: aload 6
    //   179: astore 8
    //   181: aload 7
    //   183: astore 6
    //   185: goto -61 -> 124
    //   188: astore 5
    //   190: aload 6
    //   192: astore 8
    //   194: aload 7
    //   196: astore 6
    //   198: goto -74 -> 124
    //   201: astore 7
    //   203: goto -121 -> 82
    //   206: astore 5
    //   208: aload 7
    //   210: astore 5
    //   212: goto -130 -> 82
    // Local variable table:
    //   start	length	slot	name	signature
    //   58	2	0	i	int
    //   39	74	1	l1	long
    //   4	108	3	l2	long
    //   1	99	5	str	String
    //   109	1	5	localThrowable1	Throwable
    //   116	29	5	localObject1	Object
    //   147	1	5	localThrowable2	Throwable
    //   152	1	5	localThrowable3	Throwable
    //   172	1	5	localObject2	Object
    //   188	1	5	localObject3	Object
    //   206	1	5	localThrowable4	Throwable
    //   210	1	5	localObject4	Object
    //   15	48	6	localFileReader	java.io.FileReader
    //   77	1	6	localThrowable5	Throwable
    //   80	60	6	localObject5	Object
    //   157	1	6	localThrowable6	Throwable
    //   167	11	6	localThrowable7	Throwable
    //   183	14	6	localObject6	Object
    //   29	39	7	localBufferedReader	java.io.BufferedReader
    //   162	1	7	localThrowable8	Throwable
    //   175	20	7	localObject7	Object
    //   201	8	7	localThrowable9	Throwable
    //   119	74	8	localObject8	Object
    // Exception table:
    //   from	to	target	type
    //   5	17	77	java/lang/Throwable
    //   99	104	109	java/lang/Throwable
    //   5	17	116	finally
    //   62	67	147	java/lang/Throwable
    //   67	72	152	java/lang/Throwable
    //   87	92	157	java/lang/Throwable
    //   129	134	162	java/lang/Throwable
    //   139	144	167	java/lang/Throwable
    //   17	31	172	finally
    //   31	38	188	finally
    //   45	59	188	finally
    //   17	31	201	java/lang/Throwable
    //   31	38	206	java/lang/Throwable
    //   45	59	206	java/lang/Throwable
  }
  
  public static String h(Context paramContext)
  {
    localJSONArray = new JSONArray();
    if (paramContext != null) {
      try
      {
        paramContext = (SensorManager)paramContext.getSystemService("sensor");
        if (paramContext != null)
        {
          paramContext = paramContext.getSensorList(-1);
          if ((paramContext != null) && (paramContext.size() > 0))
          {
            paramContext = paramContext.iterator();
            while (paramContext.hasNext())
            {
              Sensor localSensor = (Sensor)paramContext.next();
              if (localSensor != null)
              {
                JSONObject localJSONObject = new JSONObject();
                localJSONObject.put("name", localSensor.getName());
                localJSONObject.put("version", localSensor.getVersion());
                localJSONObject.put("vendor", localSensor.getVendor());
                localJSONArray.put(localJSONObject);
              }
            }
          }
        }
        return localJSONArray.toString();
      }
      catch (Throwable paramContext) {}
    }
  }
  
  public static String i()
  {
    long l1 = 0L;
    try
    {
      StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
      long l2 = localStatFs.getBlockSize();
      int i = localStatFs.getBlockCount();
      l1 = i * l2;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return String.valueOf(l1);
  }
  
  public static String i(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getResources().getDisplayMetrics();
      paramContext = Integer.toString(paramContext.widthPixels) + "*" + Integer.toString(paramContext.heightPixels);
      return paramContext;
    }
    catch (Throwable paramContext) {}
    return "";
  }
  
  public static String j()
  {
    l2 = 0L;
    l1 = l2;
    try
    {
      if ("mounted".equals(Environment.getExternalStorageState()))
      {
        StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        l1 = localStatFs.getBlockSize();
        int i = localStatFs.getBlockCount();
        l1 = i * l1;
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        l1 = l2;
      }
    }
    return String.valueOf(l1);
  }
  
  public static String j(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getResources().getDisplayMetrics();
      paramContext = paramContext.widthPixels;
      return paramContext;
    }
    catch (Throwable paramContext) {}
    return "";
  }
  
  public static String k()
  {
    try
    {
      Object localObject1 = Class.forName("android.os.SystemProperties");
      Object localObject2 = ((Class)localObject1).newInstance();
      localObject1 = (String)((Class)localObject1).getMethod("get", new Class[] { String.class, String.class }).invoke(localObject2, new Object[] { "gsm.version.baseband", "no message" });
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = "";
      }
      return localObject2;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        String str = "";
      }
    }
  }
  
  public static String k(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getResources().getDisplayMetrics();
      paramContext = paramContext.heightPixels;
      return paramContext;
    }
    catch (Throwable paramContext) {}
    return "";
  }
  
  public static String l()
  {
    Object localObject1 = "";
    try
    {
      localObject2 = Build.SERIAL;
      localObject1 = localObject2;
    }
    catch (Throwable localThrowable)
    {
      Object localObject2;
      for (;;) {}
    }
    localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = "";
    }
    return localObject2;
  }
  
  public static String l(Context paramContext)
  {
    Object localObject;
    if (a(paramContext, "android.permission.ACCESS_WIFI_STATE")) {
      localObject = "";
    }
    for (;;)
    {
      return localObject;
      try
      {
        paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        if (paramContext == null) {}
      }
      catch (Throwable paramContext)
      {
        try
        {
          if (paramContext.length() != 0)
          {
            localObject = paramContext;
            if (!"02:00:00:00:00:00".equals(paramContext)) {
              continue;
            }
          }
          localObject = v();
          return localObject;
        }
        catch (Throwable localThrowable) {}
        paramContext = paramContext;
        return "";
      }
    }
    return paramContext;
  }
  
  public static String m()
  {
    Object localObject1 = "";
    try
    {
      localObject2 = Locale.getDefault().toString();
      localObject1 = localObject2;
    }
    catch (Throwable localThrowable)
    {
      Object localObject2;
      for (;;) {}
    }
    localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = "";
    }
    return localObject2;
  }
  
  /* Error */
  public static String m(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 61
    //   3: invokestatic 64	com/alipay/b/a/a/b/b:a	(Landroid/content/Context;Ljava/lang/String;)Z
    //   6: ifeq +8 -> 14
    //   9: ldc 66
    //   11: astore_1
    //   12: aload_1
    //   13: areturn
    //   14: aload_0
    //   15: ldc 68
    //   17: invokevirtual 74	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   20: checkcast 76	android/telephony/TelephonyManager
    //   23: invokevirtual 443	android/telephony/TelephonyManager:getSimSerialNumber	()Ljava/lang/String;
    //   26: astore_0
    //   27: aload_0
    //   28: ifnull +18 -> 46
    //   31: aload_0
    //   32: astore_1
    //   33: aload_0
    //   34: ifnull -22 -> 12
    //   37: aload_0
    //   38: astore_1
    //   39: aload_0
    //   40: invokevirtual 187	java/lang/String:length	()I
    //   43: ifne -31 -> 12
    //   46: ldc 66
    //   48: areturn
    //   49: astore_0
    //   50: ldc 66
    //   52: areturn
    //   53: astore_1
    //   54: aload_0
    //   55: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	56	0	paramContext	Context
    //   11	28	1	localObject	Object
    //   53	1	1	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   14	27	49	java/lang/Throwable
    //   39	46	53	java/lang/Throwable
  }
  
  public static String n()
  {
    Object localObject1 = "";
    try
    {
      localObject2 = TimeZone.getDefault().getDisplayName(false, 0);
      localObject1 = localObject2;
    }
    catch (Throwable localThrowable)
    {
      Object localObject2;
      for (;;) {}
    }
    localObject2 = localObject1;
    if (localObject1 == null) {
      localObject2 = "";
    }
    return localObject2;
  }
  
  public static String n(Context paramContext)
  {
    Object localObject = "";
    try
    {
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      localObject = paramContext;
      if (paramContext == null) {
        localObject = "";
      }
      return localObject;
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        paramContext = (Context)localObject;
      }
    }
  }
  
  public static String o()
  {
    try
    {
      long l = System.currentTimeMillis() - SystemClock.elapsedRealtime();
      String str = l - l % 1000L;
      return str;
    }
    catch (Throwable localThrowable) {}
    return "";
  }
  
  public static String o(Context paramContext)
  {
    if (a(paramContext, "android.permission.BLUETOOTH")) {
      paramContext = "";
    }
    String str2;
    for (;;)
    {
      return paramContext;
      str2 = y();
      if (str2 != null) {}
      try
      {
        String str1;
        if (str2.length() != 0)
        {
          str1 = str2;
          if (!"02:00:00:00:00:00".equals(str2)) {}
        }
        else
        {
          str1 = Settings.Secure.getString(paramContext.getContentResolver(), "bluetooth_address");
        }
        paramContext = str1;
        if (str1 == null) {
          return "";
        }
      }
      catch (Throwable paramContext) {}
    }
    return str2;
  }
  
  public static String p()
  {
    try
    {
      String str = SystemClock.elapsedRealtime();
      return str;
    }
    catch (Throwable localThrowable) {}
    return "";
  }
  
  public static String p(Context paramContext)
  {
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      if (paramContext != null)
      {
        int i = paramContext.getNetworkType();
        return String.valueOf(i);
      }
    }
    catch (Throwable paramContext) {}
    return "";
  }
  
  public static String q()
  {
    int i = 0;
    for (;;)
    {
      try
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("00" + ":");
        if (i < 7) {
          if (new File(new String[] { "/dev/qemu_pipe", "/dev/socket/qemud", "/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace", "/system/bin/qemu-props", "/dev/socket/genyd", "/dev/socket/baseband_genyd" }[i]).exists()) {
            localStringBuilder.append("1");
          } else {
            localStringBuilder.append("0");
          }
        }
      }
      catch (Throwable localThrowable)
      {
        return "";
      }
      String str = localThrowable.toString();
      return str;
      i += 1;
    }
  }
  
  public static String q(Context paramContext)
  {
    Object localObject;
    if (a(paramContext, "android.permission.ACCESS_WIFI_STATE")) {
      localObject = "";
    }
    for (;;)
    {
      return localObject;
      try
      {
        paramContext = (WifiManager)paramContext.getSystemService("wifi");
        if (paramContext.isWifiEnabled())
        {
          paramContext = paramContext.getConnectionInfo().getBSSID();
          localObject = paramContext;
          if (paramContext != null) {
            continue;
          }
          return "";
        }
      }
      catch (Throwable paramContext)
      {
        for (;;)
        {
          paramContext = "";
        }
      }
    }
  }
  
  public static String r()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("00");
    localStringBuilder.append(":");
    int i = 0;
    for (;;)
    {
      if (i <= 0)
      {
        String str = new String[] { "dalvik.system.Taint" }[0];
        try
        {
          Class.forName(str);
          localStringBuilder.append("1");
          i += 1;
        }
        catch (Throwable localThrowable)
        {
          for (;;)
          {
            localStringBuilder.append("0");
          }
        }
      }
    }
    return localStringBuilder.toString();
  }
  
  public static Map<String, Integer> r(Context paramContext)
  {
    localHashMap = new HashMap();
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0);
      if ((paramContext != null) && (paramContext.size() > 0))
      {
        paramContext = paramContext.iterator();
        while (paramContext.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
          localHashMap.put(localPackageInfo.packageName, Integer.valueOf(localPackageInfo.applicationInfo.uid));
        }
      }
      return localHashMap;
    }
    catch (Throwable paramContext) {}
  }
  
  /* Error */
  public static String s()
  {
    // Byte code:
    //   0: new 287	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 288	java/lang/StringBuilder:<init>	()V
    //   7: astore 5
    //   9: new 552	java/util/LinkedHashMap
    //   12: dup
    //   13: invokespecial 553	java/util/LinkedHashMap:<init>	()V
    //   16: astore 4
    //   18: aload 4
    //   20: ldc_w 555
    //   23: ldc_w 557
    //   26: invokeinterface 547 3 0
    //   31: pop
    //   32: aload 4
    //   34: ldc_w 559
    //   37: ldc_w 561
    //   40: invokeinterface 547 3 0
    //   45: pop
    //   46: aload 4
    //   48: ldc -97
    //   50: ldc_w 561
    //   53: invokeinterface 547 3 0
    //   58: pop
    //   59: aload 5
    //   61: new 287	java/lang/StringBuilder
    //   64: dup
    //   65: invokespecial 288	java/lang/StringBuilder:<init>	()V
    //   68: ldc_w 492
    //   71: invokevirtual 311	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: ldc -72
    //   76: invokevirtual 311	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: invokevirtual 321	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   82: invokevirtual 311	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: pop
    //   86: aload 4
    //   88: invokeinterface 565 1 0
    //   93: invokeinterface 568 1 0
    //   98: astore 6
    //   100: aload 6
    //   102: invokeinterface 298 1 0
    //   107: ifeq +140 -> 247
    //   110: aload 6
    //   112: invokeinterface 302 1 0
    //   117: checkcast 58	java/lang/String
    //   120: astore 7
    //   122: aconst_null
    //   123: astore_3
    //   124: new 170	java/io/LineNumberReader
    //   127: dup
    //   128: new 165	java/io/InputStreamReader
    //   131: dup
    //   132: new 157	java/io/FileInputStream
    //   135: dup
    //   136: aload 7
    //   138: invokespecial 569	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   141: invokespecial 168	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   144: invokespecial 173	java/io/LineNumberReader:<init>	(Ljava/io/Reader;)V
    //   147: astore_2
    //   148: aload_2
    //   149: invokevirtual 176	java/io/LineNumberReader:readLine	()Ljava/lang/String;
    //   152: astore_3
    //   153: aload_3
    //   154: ifnull +117 -> 271
    //   157: aload_3
    //   158: invokevirtual 572	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   161: aload 4
    //   163: aload 7
    //   165: invokeinterface 573 2 0
    //   170: checkcast 575	java/lang/CharSequence
    //   173: invokevirtual 579	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   176: istore_1
    //   177: iload_1
    //   178: ifeq -30 -> 148
    //   181: bipush 49
    //   183: istore_0
    //   184: aload 5
    //   186: iload_0
    //   187: invokevirtual 582	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   190: pop
    //   191: aload_2
    //   192: invokevirtual 197	java/io/LineNumberReader:close	()V
    //   195: goto -95 -> 100
    //   198: astore_2
    //   199: goto -99 -> 100
    //   202: astore_2
    //   203: aconst_null
    //   204: astore_2
    //   205: aload 5
    //   207: bipush 48
    //   209: invokevirtual 582	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   212: pop
    //   213: aload_2
    //   214: ifnull -114 -> 100
    //   217: aload_2
    //   218: invokevirtual 197	java/io/LineNumberReader:close	()V
    //   221: goto -121 -> 100
    //   224: astore_2
    //   225: goto -125 -> 100
    //   228: astore_2
    //   229: aload 5
    //   231: bipush 48
    //   233: invokevirtual 582	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   236: pop
    //   237: aload_3
    //   238: ifnull +7 -> 245
    //   241: aload_3
    //   242: invokevirtual 197	java/io/LineNumberReader:close	()V
    //   245: aload_2
    //   246: athrow
    //   247: aload 5
    //   249: invokevirtual 321	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   252: areturn
    //   253: astore_3
    //   254: goto -9 -> 245
    //   257: astore 4
    //   259: aload_2
    //   260: astore_3
    //   261: aload 4
    //   263: astore_2
    //   264: goto -35 -> 229
    //   267: astore_3
    //   268: goto -63 -> 205
    //   271: bipush 48
    //   273: istore_0
    //   274: goto -90 -> 184
    // Local variable table:
    //   start	length	slot	name	signature
    //   183	91	0	c	char
    //   176	2	1	bool	boolean
    //   147	45	2	localLineNumberReader	java.io.LineNumberReader
    //   198	1	2	localThrowable1	Throwable
    //   202	1	2	localThrowable2	Throwable
    //   204	14	2	localObject1	Object
    //   224	1	2	localThrowable3	Throwable
    //   228	32	2	localObject2	Object
    //   263	1	2	localObject3	Object
    //   123	119	3	str1	String
    //   253	1	3	localThrowable4	Throwable
    //   260	1	3	localObject4	Object
    //   267	1	3	localThrowable5	Throwable
    //   16	146	4	localLinkedHashMap	LinkedHashMap
    //   257	5	4	localObject5	Object
    //   7	241	5	localStringBuilder	StringBuilder
    //   98	13	6	localIterator	Iterator
    //   120	44	7	str2	String
    // Exception table:
    //   from	to	target	type
    //   191	195	198	java/lang/Throwable
    //   124	148	202	java/lang/Throwable
    //   217	221	224	java/lang/Throwable
    //   124	148	228	finally
    //   241	245	253	java/lang/Throwable
    //   148	153	257	finally
    //   157	177	257	finally
    //   148	153	267	java/lang/Throwable
    //   157	177	267	java/lang/Throwable
  }
  
  public static String s(Context paramContext)
  {
    String str1 = "";
    try
    {
      String str2 = v(paramContext);
      String str3 = z();
      paramContext = str1;
      if (a.b(str2))
      {
        paramContext = str1;
        if (a.b(str3)) {
          paramContext = str2 + ":" + z();
        }
      }
      return paramContext;
    }
    catch (Throwable paramContext) {}
    return "";
  }
  
  public static String t()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("00" + ":");
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    localLinkedHashMap.put("BRAND", "generic");
    localLinkedHashMap.put("BOARD", "unknown");
    localLinkedHashMap.put("DEVICE", "generic");
    localLinkedHashMap.put("HARDWARE", "goldfish");
    localLinkedHashMap.put("PRODUCT", "sdk");
    localLinkedHashMap.put("MODEL", "sdk");
    Iterator localIterator = localLinkedHashMap.keySet().iterator();
    String str2;
    if (localIterator.hasNext()) {
      str2 = (String)localIterator.next();
    }
    for (;;)
    {
      try
      {
        String str1 = (String)Build.class.getField(str2).get(null);
        str2 = (String)localLinkedHashMap.get(str2);
        if (str1 == null) {
          break label261;
        }
        str1 = str1.toLowerCase();
        if (str1 == null) {
          break label255;
        }
        boolean bool = str1.contains(str2);
        if (!bool) {
          break label255;
        }
        c = '1';
        localStringBuilder.append(c);
        break;
      }
      catch (Throwable localThrowable)
      {
        localStringBuilder.append('0');
        break;
      }
      finally
      {
        localStringBuilder.append('0');
      }
      return localStringBuilder.toString();
      label255:
      char c = '0';
      continue;
      label261:
      Object localObject2 = null;
    }
  }
  
  public static String t(Context paramContext)
  {
    for (;;)
    {
      try
      {
        if (!((KeyguardManager)paramContext.getSystemService("keyguard")).isKeyguardSecure()) {
          return "0:0";
        }
        i = 0;
        l1 = 0L;
        if (i < 5)
        {
          paramContext = new String[] { "/data/system/password.key", "/data/system/gesture.key", "/data/system/gatekeeper.password.key", "/data/system/gatekeeper.gesture.key", "/data/system/gatekeeper.pattern.key" }[i];
          l2 = -1L;
        }
      }
      catch (Throwable paramContext)
      {
        int i;
        long l1;
        long l2;
        long l3;
        return "";
      }
      try
      {
        l3 = new File(paramContext).lastModified();
        l2 = l3;
      }
      catch (Throwable paramContext)
      {
        continue;
      }
      l1 = Math.max(l2, l1);
      i += 1;
    }
    paramContext = "1:" + l1;
    return paramContext;
  }
  
  public static String u()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("00" + ":");
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    localLinkedHashMap.put("ro.hardware", "goldfish");
    localLinkedHashMap.put("ro.kernel.qemu", "1");
    localLinkedHashMap.put("ro.product.device", "generic");
    localLinkedHashMap.put("ro.product.model", "sdk");
    localLinkedHashMap.put("ro.product.brand", "generic");
    localLinkedHashMap.put("ro.product.name", "sdk");
    localLinkedHashMap.put("ro.build.fingerprint", "test-keys");
    localLinkedHashMap.put("ro.product.manufacturer", "unknow");
    Iterator localIterator = localLinkedHashMap.keySet().iterator();
    if (localIterator.hasNext())
    {
      String str2 = (String)localIterator.next();
      String str1 = (String)localLinkedHashMap.get(str2);
      str2 = a.b(str2, "");
      if ((str2 == null) || (!str2.contains(str1))) {
        break label231;
      }
    }
    label231:
    for (char c = '1';; c = '0')
    {
      localStringBuilder.append(c);
      break;
      return localStringBuilder.toString();
    }
  }
  
  public static String u(Context paramContext)
  {
    for (;;)
    {
      try
      {
        paramContext = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        j = paramContext.getIntExtra("level", -1);
        i = paramContext.getIntExtra("status", -1);
        if (i == 2) {
          break label90;
        }
        if (i != 5) {
          break label95;
        }
      }
      catch (Throwable paramContext)
      {
        int j;
        StringBuilder localStringBuilder;
        return "";
      }
      localStringBuilder = new StringBuilder();
      if (i != 0)
      {
        paramContext = "1";
        return paramContext + ":" + j;
      }
      paramContext = "0";
      continue;
      label90:
      int i = 1;
      continue;
      label95:
      i = 0;
    }
  }
  
  private static String v()
  {
    try
    {
      Object localObject1 = Collections.list(NetworkInterface.getNetworkInterfaces());
      if (localObject1 != null)
      {
        localObject1 = ((List)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
        {
          Object localObject2 = (NetworkInterface)((Iterator)localObject1).next();
          if ((localObject2 != null) && (((NetworkInterface)localObject2).getName() != null) && (((NetworkInterface)localObject2).getName().equalsIgnoreCase("wlan0")))
          {
            localObject1 = ((NetworkInterface)localObject2).getHardwareAddress();
            if (localObject1 == null) {
              return "02:00:00:00:00:00";
            }
            localObject2 = new StringBuilder();
            int j = localObject1.length;
            int i = 0;
            while (i < j)
            {
              ((StringBuilder)localObject2).append(String.format("%02X:", new Object[] { Integer.valueOf(localObject1[i] & 0xFF) }));
              i += 1;
            }
            if (((StringBuilder)localObject2).length() > 0) {
              ((StringBuilder)localObject2).deleteCharAt(((StringBuilder)localObject2).length() - 1);
            }
            localObject1 = ((StringBuilder)localObject2).toString();
            return localObject1;
          }
        }
      }
    }
    catch (Throwable localThrowable) {}
    return "02:00:00:00:00:00";
  }
  
  private static String v(Context paramContext)
  {
    Object localObject = null;
    if (a(paramContext, "android.permission.ACCESS_NETWORK_STATE"))
    {
      paramContext = "";
      return paramContext;
    }
    int i;
    label105:
    label109:
    do
    {
      try
      {
        NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
        paramContext = localObject;
        if (localNetworkInfo == null) {
          break;
        }
        if (localNetworkInfo.getType() == 1) {
          return "WIFI";
        }
        if (localNetworkInfo.getType() == 0)
        {
          i = localNetworkInfo.getSubtype();
          if ((i == 4) || (i == 1) || (i == 2) || (i == 7)) {
            break label105;
          }
          if (i != 11) {
            break label109;
          }
        }
      }
      catch (Throwable paramContext)
      {
        return null;
      }
      for (paramContext = "UNKNOW";; paramContext = null) {
        return paramContext;
      }
      return "2G";
      if ((i == 3) || (i == 5) || (i == 6) || (i == 8) || (i == 9) || (i == 10) || (i == 12) || (i == 14) || (i == 15)) {
        return "3G";
      }
    } while (i != 13);
    return "4G";
  }
  
  /* Error */
  private static String w()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: new 259	java/io/FileReader
    //   5: dup
    //   6: ldc_w 749
    //   9: invokespecial 260	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   12: astore_0
    //   13: new 262	java/io/BufferedReader
    //   16: dup
    //   17: aload_0
    //   18: sipush 8192
    //   21: invokespecial 330	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   24: astore_2
    //   25: aload_2
    //   26: invokevirtual 264	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   29: astore_1
    //   30: aload_1
    //   31: invokestatic 250	com/alipay/b/a/a/a/a:a	(Ljava/lang/String;)Z
    //   34: ifne +18 -> 52
    //   37: aload_1
    //   38: invokevirtual 194	java/lang/String:trim	()Ljava/lang/String;
    //   41: astore_1
    //   42: aload_2
    //   43: invokevirtual 272	java/io/BufferedReader:close	()V
    //   46: aload_0
    //   47: invokevirtual 271	java/io/FileReader:close	()V
    //   50: aload_1
    //   51: areturn
    //   52: aload_2
    //   53: invokevirtual 272	java/io/BufferedReader:close	()V
    //   56: aload_0
    //   57: invokevirtual 271	java/io/FileReader:close	()V
    //   60: ldc 66
    //   62: areturn
    //   63: astore_0
    //   64: aconst_null
    //   65: astore_0
    //   66: aload_1
    //   67: ifnull +7 -> 74
    //   70: aload_1
    //   71: invokevirtual 272	java/io/BufferedReader:close	()V
    //   74: aload_0
    //   75: ifnull -15 -> 60
    //   78: aload_0
    //   79: invokevirtual 271	java/io/FileReader:close	()V
    //   82: goto -22 -> 60
    //   85: astore_0
    //   86: goto -26 -> 60
    //   89: astore_1
    //   90: aconst_null
    //   91: astore_0
    //   92: aconst_null
    //   93: astore_2
    //   94: aload_2
    //   95: ifnull +7 -> 102
    //   98: aload_2
    //   99: invokevirtual 272	java/io/BufferedReader:close	()V
    //   102: aload_0
    //   103: ifnull +7 -> 110
    //   106: aload_0
    //   107: invokevirtual 271	java/io/FileReader:close	()V
    //   110: aload_1
    //   111: athrow
    //   112: astore_2
    //   113: goto -67 -> 46
    //   116: astore_0
    //   117: aload_1
    //   118: areturn
    //   119: astore_1
    //   120: goto -64 -> 56
    //   123: astore_0
    //   124: goto -64 -> 60
    //   127: astore_1
    //   128: goto -54 -> 74
    //   131: astore_2
    //   132: goto -30 -> 102
    //   135: astore_0
    //   136: goto -26 -> 110
    //   139: astore_1
    //   140: aconst_null
    //   141: astore_2
    //   142: goto -48 -> 94
    //   145: astore_1
    //   146: goto -52 -> 94
    //   149: astore_2
    //   150: goto -84 -> 66
    //   153: astore_1
    //   154: aload_2
    //   155: astore_1
    //   156: goto -90 -> 66
    // Local variable table:
    //   start	length	slot	name	signature
    //   12	45	0	localFileReader	java.io.FileReader
    //   63	1	0	localThrowable1	Throwable
    //   65	14	0	localObject1	Object
    //   85	1	0	localThrowable2	Throwable
    //   91	16	0	localObject2	Object
    //   116	1	0	localThrowable3	Throwable
    //   123	1	0	localThrowable4	Throwable
    //   135	1	0	localThrowable5	Throwable
    //   1	70	1	str1	String
    //   89	29	1	str2	String
    //   119	1	1	localThrowable6	Throwable
    //   127	1	1	localThrowable7	Throwable
    //   139	1	1	localObject3	Object
    //   145	1	1	localObject4	Object
    //   153	1	1	localThrowable8	Throwable
    //   155	1	1	localObject5	Object
    //   24	75	2	localBufferedReader	java.io.BufferedReader
    //   112	1	2	localThrowable9	Throwable
    //   131	1	2	localThrowable10	Throwable
    //   141	1	2	localObject6	Object
    //   149	6	2	localThrowable11	Throwable
    // Exception table:
    //   from	to	target	type
    //   2	13	63	java/lang/Throwable
    //   78	82	85	java/lang/Throwable
    //   2	13	89	finally
    //   42	46	112	java/lang/Throwable
    //   46	50	116	java/lang/Throwable
    //   52	56	119	java/lang/Throwable
    //   56	60	123	java/lang/Throwable
    //   70	74	127	java/lang/Throwable
    //   98	102	131	java/lang/Throwable
    //   106	110	135	java/lang/Throwable
    //   13	25	139	finally
    //   25	42	145	finally
    //   13	25	149	java/lang/Throwable
    //   25	42	153	java/lang/Throwable
  }
  
  /* Error */
  private static String x()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: ldc 66
    //   4: astore_2
    //   5: new 259	java/io/FileReader
    //   8: dup
    //   9: ldc -97
    //   11: invokespecial 260	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   14: astore_0
    //   15: new 262	java/io/BufferedReader
    //   18: dup
    //   19: aload_0
    //   20: sipush 8192
    //   23: invokespecial 330	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   26: astore_3
    //   27: aload_3
    //   28: invokevirtual 264	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   31: astore 4
    //   33: aload_2
    //   34: astore_1
    //   35: aload 4
    //   37: ifnull +48 -> 85
    //   40: aload 4
    //   42: invokestatic 250	com/alipay/b/a/a/a/a:a	(Ljava/lang/String;)Z
    //   45: ifne -18 -> 27
    //   48: aload 4
    //   50: ldc -72
    //   52: invokevirtual 335	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   55: astore_1
    //   56: aload_1
    //   57: ifnull -30 -> 27
    //   60: aload_1
    //   61: arraylength
    //   62: iconst_1
    //   63: if_icmple -36 -> 27
    //   66: aload_1
    //   67: iconst_0
    //   68: aaload
    //   69: ldc_w 751
    //   72: invokevirtual 579	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   75: ifeq -48 -> 27
    //   78: aload_1
    //   79: iconst_1
    //   80: aaload
    //   81: invokevirtual 194	java/lang/String:trim	()Ljava/lang/String;
    //   84: astore_1
    //   85: aload_0
    //   86: invokevirtual 271	java/io/FileReader:close	()V
    //   89: aload_3
    //   90: invokevirtual 272	java/io/BufferedReader:close	()V
    //   93: aload_1
    //   94: areturn
    //   95: astore_0
    //   96: aconst_null
    //   97: astore_0
    //   98: aload_1
    //   99: ifnull +7 -> 106
    //   102: aload_1
    //   103: invokevirtual 271	java/io/FileReader:close	()V
    //   106: aload_2
    //   107: astore_1
    //   108: aload_0
    //   109: ifnull -16 -> 93
    //   112: aload_0
    //   113: invokevirtual 272	java/io/BufferedReader:close	()V
    //   116: ldc 66
    //   118: areturn
    //   119: astore_0
    //   120: ldc 66
    //   122: areturn
    //   123: astore_1
    //   124: aconst_null
    //   125: astore_2
    //   126: aconst_null
    //   127: astore_0
    //   128: aload_0
    //   129: ifnull +7 -> 136
    //   132: aload_0
    //   133: invokevirtual 271	java/io/FileReader:close	()V
    //   136: aload_2
    //   137: ifnull +7 -> 144
    //   140: aload_2
    //   141: invokevirtual 272	java/io/BufferedReader:close	()V
    //   144: aload_1
    //   145: athrow
    //   146: astore_0
    //   147: goto -58 -> 89
    //   150: astore_0
    //   151: aload_1
    //   152: areturn
    //   153: astore_1
    //   154: goto -48 -> 106
    //   157: astore_0
    //   158: goto -22 -> 136
    //   161: astore_0
    //   162: goto -18 -> 144
    //   165: astore_1
    //   166: aconst_null
    //   167: astore_2
    //   168: goto -40 -> 128
    //   171: astore_1
    //   172: aload_3
    //   173: astore_2
    //   174: goto -46 -> 128
    //   177: astore_1
    //   178: aconst_null
    //   179: astore_3
    //   180: aload_0
    //   181: astore_1
    //   182: aload_3
    //   183: astore_0
    //   184: goto -86 -> 98
    //   187: astore_1
    //   188: aload_0
    //   189: astore_1
    //   190: aload_3
    //   191: astore_0
    //   192: goto -94 -> 98
    // Local variable table:
    //   start	length	slot	name	signature
    //   14	72	0	localFileReader	java.io.FileReader
    //   95	1	0	localThrowable1	Throwable
    //   97	16	0	localObject1	Object
    //   119	1	0	localThrowable2	Throwable
    //   127	6	0	localObject2	Object
    //   146	1	0	localThrowable3	Throwable
    //   150	1	0	localThrowable4	Throwable
    //   157	1	0	localThrowable5	Throwable
    //   161	20	0	localThrowable6	Throwable
    //   183	9	0	localObject3	Object
    //   1	107	1	localObject4	Object
    //   123	29	1	str1	String
    //   153	1	1	localThrowable7	Throwable
    //   165	1	1	localObject5	Object
    //   171	1	1	localObject6	Object
    //   177	1	1	localThrowable8	Throwable
    //   181	1	1	localObject7	Object
    //   187	1	1	localThrowable9	Throwable
    //   189	1	1	localObject8	Object
    //   4	170	2	localObject9	Object
    //   26	165	3	localBufferedReader	java.io.BufferedReader
    //   31	18	4	str2	String
    // Exception table:
    //   from	to	target	type
    //   5	15	95	java/lang/Throwable
    //   112	116	119	java/lang/Throwable
    //   5	15	123	finally
    //   85	89	146	java/lang/Throwable
    //   89	93	150	java/lang/Throwable
    //   102	106	153	java/lang/Throwable
    //   132	136	157	java/lang/Throwable
    //   140	144	161	java/lang/Throwable
    //   15	27	165	finally
    //   27	33	171	finally
    //   40	56	171	finally
    //   60	85	171	finally
    //   15	27	177	java/lang/Throwable
    //   27	33	187	java/lang/Throwable
    //   40	56	187	java/lang/Throwable
    //   60	85	187	java/lang/Throwable
  }
  
  private static String y()
  {
    Object localObject1 = null;
    Object localObject3;
    try
    {
      localBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
      if (localBluetoothAdapter != null)
      {
        localObject1 = localBluetoothAdapter;
        if (!localBluetoothAdapter.isEnabled()) {
          return "";
        }
      }
      localObject1 = localBluetoothAdapter;
      localObject3 = localBluetoothAdapter.getAddress();
      localObject1 = localObject3;
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        BluetoothAdapter localBluetoothAdapter;
        localObject3 = a(localBluetoothAdapter);
        label52:
        if (localObject3 != null) {
          return localObject3;
        }
        return "";
        localThrowable1 = localThrowable1;
        localObject3 = "";
        Object localObject2 = localObject1;
        localObject1 = localObject3;
      }
      catch (Throwable localThrowable2)
      {
        for (;;)
        {
          localObject3 = localObject1;
        }
      }
    }
    if (localObject1 != null)
    {
      localObject3 = localObject1;
      if (!localObject1.endsWith("00:00:00:00:00")) {
        break label52;
      }
    }
    return localObject3;
  }
  
  private static String z()
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
      } while ((localInetAddress.isLoopbackAddress()) || (!(localInetAddress instanceof Inet4Address)));
      Object localObject = localInetAddress.getHostAddress().toString();
      return localObject;
    }
    catch (Throwable localThrowable) {}
    return "";
  }
  
  public final String e()
  {
    try
    {
      int i = new File("/sys/devices/system/cpu/").listFiles(new c(this)).length;
      return String.valueOf(i);
    }
    catch (Throwable localThrowable) {}
    return "1";
  }
}
