package com.xiaomi.gamecenter.sdk;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.alipay.security.mobile.module.commonutils.a;
import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class t
{
  private static t a = new t();
  
  private t() {}
  
  public static t a()
  {
    return a;
  }
  
  public static String a(Context paramContext)
  {
    Context localContext = null;
    if (paramContext != null) {}
    for (;;)
    {
      try
      {
        paramContext = (TelephonyManager)paramContext.getSystemService("phone");
        if (paramContext != null)
        {
          paramContext = paramContext.getDeviceId();
          localContext = paramContext;
          return localContext;
        }
      }
      catch (Exception paramContext)
      {
        return null;
      }
      paramContext = null;
    }
  }
  
  /* Error */
  public static String b()
  {
    // Byte code:
    //   0: ldc 38
    //   2: astore_2
    //   3: new 40	java/io/InputStreamReader
    //   6: dup
    //   7: invokestatic 46	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   10: ldc 48
    //   12: invokevirtual 52	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   15: invokevirtual 58	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   18: invokespecial 61	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   21: astore_3
    //   22: new 63	java/io/LineNumberReader
    //   25: dup
    //   26: aload_3
    //   27: invokespecial 66	java/io/LineNumberReader:<init>	(Ljava/io/Reader;)V
    //   30: astore 4
    //   32: iconst_1
    //   33: istore_0
    //   34: aload_2
    //   35: astore_1
    //   36: iload_0
    //   37: bipush 100
    //   39: if_icmpge +50 -> 89
    //   42: aload 4
    //   44: invokevirtual 69	java/io/LineNumberReader:readLine	()Ljava/lang/String;
    //   47: astore 5
    //   49: aload_2
    //   50: astore_1
    //   51: aload 5
    //   53: ifnull +36 -> 89
    //   56: aload 5
    //   58: ldc 71
    //   60: invokevirtual 77	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   63: iflt +37 -> 100
    //   66: aload 5
    //   68: aload 5
    //   70: ldc 79
    //   72: invokevirtual 77	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   75: iconst_1
    //   76: iadd
    //   77: aload 5
    //   79: invokevirtual 83	java/lang/String:length	()I
    //   82: invokevirtual 87	java/lang/String:substring	(II)Ljava/lang/String;
    //   85: invokevirtual 90	java/lang/String:trim	()Ljava/lang/String;
    //   88: astore_1
    //   89: aload 4
    //   91: invokevirtual 93	java/io/LineNumberReader:close	()V
    //   94: aload_3
    //   95: invokevirtual 94	java/io/InputStreamReader:close	()V
    //   98: aload_1
    //   99: areturn
    //   100: iload_0
    //   101: iconst_1
    //   102: iadd
    //   103: istore_0
    //   104: goto -70 -> 34
    //   107: astore_1
    //   108: aconst_null
    //   109: astore_1
    //   110: aconst_null
    //   111: astore_3
    //   112: aload_1
    //   113: ifnull +7 -> 120
    //   116: aload_1
    //   117: invokevirtual 93	java/io/LineNumberReader:close	()V
    //   120: aload_2
    //   121: astore_1
    //   122: aload_3
    //   123: ifnull -25 -> 98
    //   126: aload_3
    //   127: invokevirtual 94	java/io/InputStreamReader:close	()V
    //   130: ldc 38
    //   132: areturn
    //   133: astore_1
    //   134: ldc 38
    //   136: areturn
    //   137: astore_1
    //   138: aconst_null
    //   139: astore_2
    //   140: aconst_null
    //   141: astore_3
    //   142: aload_2
    //   143: ifnull +7 -> 150
    //   146: aload_2
    //   147: invokevirtual 93	java/io/LineNumberReader:close	()V
    //   150: aload_3
    //   151: ifnull +7 -> 158
    //   154: aload_3
    //   155: invokevirtual 94	java/io/InputStreamReader:close	()V
    //   158: aload_1
    //   159: athrow
    //   160: astore_2
    //   161: goto -67 -> 94
    //   164: astore_2
    //   165: aload_1
    //   166: areturn
    //   167: astore_1
    //   168: goto -48 -> 120
    //   171: astore_2
    //   172: goto -22 -> 150
    //   175: astore_2
    //   176: goto -18 -> 158
    //   179: astore_1
    //   180: aconst_null
    //   181: astore_2
    //   182: goto -40 -> 142
    //   185: astore_1
    //   186: aload 4
    //   188: astore_2
    //   189: goto -47 -> 142
    //   192: astore_1
    //   193: aconst_null
    //   194: astore_1
    //   195: goto -83 -> 112
    //   198: astore_1
    //   199: aload 4
    //   201: astore_1
    //   202: goto -90 -> 112
    // Local variable table:
    //   start	length	slot	name	signature
    //   33	71	0	i	int
    //   35	64	1	str1	String
    //   107	1	1	localException1	Exception
    //   109	13	1	str2	String
    //   133	1	1	localIOException1	java.io.IOException
    //   137	29	1	str3	String
    //   167	1	1	localIOException2	java.io.IOException
    //   179	1	1	localObject1	Object
    //   185	1	1	localObject2	Object
    //   192	1	1	localException2	Exception
    //   194	1	1	localObject3	Object
    //   198	1	1	localException3	Exception
    //   201	1	1	localLineNumberReader1	java.io.LineNumberReader
    //   2	145	2	str4	String
    //   160	1	2	localIOException3	java.io.IOException
    //   164	1	2	localIOException4	java.io.IOException
    //   171	1	2	localIOException5	java.io.IOException
    //   175	1	2	localIOException6	java.io.IOException
    //   181	8	2	localObject4	Object
    //   21	134	3	localInputStreamReader	java.io.InputStreamReader
    //   30	170	4	localLineNumberReader2	java.io.LineNumberReader
    //   47	31	5	str5	String
    // Exception table:
    //   from	to	target	type
    //   3	22	107	java/lang/Exception
    //   126	130	133	java/io/IOException
    //   3	22	137	finally
    //   89	94	160	java/io/IOException
    //   94	98	164	java/io/IOException
    //   116	120	167	java/io/IOException
    //   146	150	171	java/io/IOException
    //   154	158	175	java/io/IOException
    //   22	32	179	finally
    //   42	49	185	finally
    //   56	89	185	finally
    //   22	32	192	java/lang/Exception
    //   42	49	198	java/lang/Exception
    //   56	89	198	java/lang/Exception
  }
  
  public static String b(Context paramContext)
  {
    Context localContext = null;
    if (paramContext != null) {}
    for (;;)
    {
      try
      {
        paramContext = (TelephonyManager)paramContext.getSystemService("phone");
        if (paramContext != null)
        {
          paramContext = paramContext.getSubscriberId();
          localContext = paramContext;
          return localContext;
        }
      }
      catch (Exception paramContext)
      {
        return null;
      }
      paramContext = null;
    }
  }
  
  public static String c(Context paramContext)
  {
    Object localObject = "";
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
          return localObject;
        }
      }
      catch (Exception paramContext)
      {
        return "";
      }
      paramContext = "";
    }
  }
  
  public static String d()
  {
    String str = k();
    if (!a.a(str)) {
      return str;
    }
    return l();
  }
  
  public static String d(Context paramContext)
  {
    if (paramContext != null) {
      try
      {
        paramContext = (SensorManager)paramContext.getSystemService("sensor");
        if (paramContext != null)
        {
          Object localObject = paramContext.getSensorList(-1);
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
        for (paramContext = a.c(paramContext.toString());; paramContext = null) {
          return paramContext;
        }
        return null;
      }
      catch (Exception paramContext) {}
    }
  }
  
  /* Error */
  public static String e()
  {
    // Byte code:
    //   0: lconst_0
    //   1: lstore_3
    //   2: new 172	java/io/FileReader
    //   5: dup
    //   6: ldc -82
    //   8: invokespecial 177	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   11: astore 6
    //   13: new 179	java/io/BufferedReader
    //   16: dup
    //   17: aload 6
    //   19: sipush 8192
    //   22: invokespecial 182	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   25: astore 7
    //   27: aload 7
    //   29: invokevirtual 183	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   32: astore 5
    //   34: lload_3
    //   35: lstore_1
    //   36: aload 5
    //   38: ifnull +19 -> 57
    //   41: aload 5
    //   43: ldc -71
    //   45: invokevirtual 189	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   48: iconst_1
    //   49: aaload
    //   50: invokestatic 194	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   53: istore_0
    //   54: iload_0
    //   55: i2l
    //   56: lstore_1
    //   57: aload 6
    //   59: invokevirtual 195	java/io/FileReader:close	()V
    //   62: aload 7
    //   64: invokevirtual 196	java/io/BufferedReader:close	()V
    //   67: lload_1
    //   68: invokestatic 200	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   71: areturn
    //   72: astore 5
    //   74: aconst_null
    //   75: astore 6
    //   77: aconst_null
    //   78: astore 5
    //   80: aload 6
    //   82: ifnull +8 -> 90
    //   85: aload 6
    //   87: invokevirtual 195	java/io/FileReader:close	()V
    //   90: lload_3
    //   91: lstore_1
    //   92: aload 5
    //   94: ifnull -27 -> 67
    //   97: aload 5
    //   99: invokevirtual 196	java/io/BufferedReader:close	()V
    //   102: lload_3
    //   103: lstore_1
    //   104: goto -37 -> 67
    //   107: astore 5
    //   109: lload_3
    //   110: lstore_1
    //   111: goto -44 -> 67
    //   114: astore 5
    //   116: aconst_null
    //   117: astore 8
    //   119: aconst_null
    //   120: astore 6
    //   122: aload 8
    //   124: ifnull +8 -> 132
    //   127: aload 8
    //   129: invokevirtual 195	java/io/FileReader:close	()V
    //   132: aload 6
    //   134: ifnull +8 -> 142
    //   137: aload 6
    //   139: invokevirtual 196	java/io/BufferedReader:close	()V
    //   142: aload 5
    //   144: athrow
    //   145: astore 5
    //   147: goto -85 -> 62
    //   150: astore 5
    //   152: goto -85 -> 67
    //   155: astore 6
    //   157: goto -67 -> 90
    //   160: astore 7
    //   162: goto -30 -> 132
    //   165: astore 6
    //   167: goto -25 -> 142
    //   170: astore 5
    //   172: aconst_null
    //   173: astore 7
    //   175: aload 6
    //   177: astore 8
    //   179: aload 7
    //   181: astore 6
    //   183: goto -61 -> 122
    //   186: astore 5
    //   188: aload 6
    //   190: astore 8
    //   192: aload 7
    //   194: astore 6
    //   196: goto -74 -> 122
    //   199: astore 5
    //   201: aconst_null
    //   202: astore 5
    //   204: goto -124 -> 80
    //   207: astore 5
    //   209: aload 7
    //   211: astore 5
    //   213: goto -133 -> 80
    // Local variable table:
    //   start	length	slot	name	signature
    //   53	2	0	i	int
    //   35	76	1	l1	long
    //   1	109	3	l2	long
    //   32	10	5	str	String
    //   72	1	5	localIOException1	java.io.IOException
    //   78	20	5	localObject1	Object
    //   107	1	5	localIOException2	java.io.IOException
    //   114	29	5	localObject2	Object
    //   145	1	5	localIOException3	java.io.IOException
    //   150	1	5	localIOException4	java.io.IOException
    //   170	1	5	localObject3	Object
    //   186	1	5	localObject4	Object
    //   199	1	5	localIOException5	java.io.IOException
    //   202	1	5	localObject5	Object
    //   207	1	5	localIOException6	java.io.IOException
    //   211	1	5	localObject6	Object
    //   11	127	6	localFileReader	java.io.FileReader
    //   155	1	6	localIOException7	java.io.IOException
    //   165	11	6	localIOException8	java.io.IOException
    //   181	14	6	localObject7	Object
    //   25	38	7	localBufferedReader	java.io.BufferedReader
    //   160	1	7	localIOException9	java.io.IOException
    //   173	37	7	localObject8	Object
    //   117	74	8	localObject9	Object
    // Exception table:
    //   from	to	target	type
    //   2	13	72	java/io/IOException
    //   97	102	107	java/io/IOException
    //   2	13	114	finally
    //   57	62	145	java/io/IOException
    //   62	67	150	java/io/IOException
    //   85	90	155	java/io/IOException
    //   127	132	160	java/io/IOException
    //   137	142	165	java/io/IOException
    //   13	27	170	finally
    //   27	34	186	finally
    //   41	54	186	finally
    //   13	27	199	java/io/IOException
    //   27	34	207	java/io/IOException
    //   41	54	207	java/io/IOException
  }
  
  public static String e(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getResources().getDisplayMetrics();
      paramContext = Integer.toString(paramContext.widthPixels) + "*" + Integer.toString(paramContext.heightPixels);
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String f()
  {
    long l1 = 0L;
    try
    {
      StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
      long l2 = localStatFs.getBlockSize();
      int i = localStatFs.getBlockCount();
      l1 = i * l2;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return String.valueOf(l1);
  }
  
  public static String f(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getResources().getDisplayMetrics();
      paramContext = paramContext.widthPixels;
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String g()
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
    catch (Exception localException)
    {
      for (;;)
      {
        l1 = l2;
      }
    }
    return String.valueOf(l1);
  }
  
  public static String g(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getResources().getDisplayMetrics();
      paramContext = paramContext.heightPixels;
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String h()
  {
    try
    {
      Object localObject = BluetoothAdapter.getDefaultAdapter();
      if ((localObject != null) && (!((BluetoothAdapter)localObject).isEnabled())) {
        return "";
      }
      localObject = ((BluetoothAdapter)localObject).getAddress();
      return localObject;
    }
    catch (Exception localException) {}
    return null;
  }
  
  public static String h(Context paramContext)
  {
    try
    {
      paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String i()
  {
    try
    {
      Object localObject1 = Class.forName("android.os.SystemProperties");
      Object localObject2 = ((Class)localObject1).newInstance();
      localObject1 = (String)((Class)localObject1).getMethod("get", new Class[] { String.class, String.class }).invoke(localObject2, new Object[] { "gsm.version.baseband", "no message" });
      return localObject1;
    }
    catch (Exception localException) {}
    return null;
  }
  
  public static String i(Context paramContext)
  {
    try
    {
      paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getSimSerialNumber();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "";
  }
  
  public static String j()
  {
    try
    {
      String str = Build.SERIAL;
      return str;
    }
    catch (Exception localException) {}
    return "";
  }
  
  public static String j(Context paramContext)
  {
    try
    {
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  /* Error */
  private static String k()
  {
    // Byte code:
    //   0: new 172	java/io/FileReader
    //   3: dup
    //   4: ldc_w 336
    //   7: invokespecial 177	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   10: astore_1
    //   11: new 179	java/io/BufferedReader
    //   14: dup
    //   15: aload_1
    //   16: sipush 8192
    //   19: invokespecial 182	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   22: astore_0
    //   23: aload_0
    //   24: invokevirtual 183	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   27: astore_2
    //   28: aload_2
    //   29: invokestatic 112	com/alipay/security/mobile/module/commonutils/a:a	(Ljava/lang/String;)Z
    //   32: ifne +18 -> 50
    //   35: aload_2
    //   36: invokevirtual 90	java/lang/String:trim	()Ljava/lang/String;
    //   39: astore_2
    //   40: aload_0
    //   41: invokevirtual 196	java/io/BufferedReader:close	()V
    //   44: aload_1
    //   45: invokevirtual 195	java/io/FileReader:close	()V
    //   48: aload_2
    //   49: areturn
    //   50: aload_0
    //   51: invokevirtual 196	java/io/BufferedReader:close	()V
    //   54: aload_1
    //   55: invokevirtual 195	java/io/FileReader:close	()V
    //   58: aconst_null
    //   59: areturn
    //   60: astore_0
    //   61: aconst_null
    //   62: areturn
    //   63: astore_0
    //   64: aconst_null
    //   65: astore_0
    //   66: aconst_null
    //   67: astore_1
    //   68: aload_0
    //   69: invokevirtual 196	java/io/BufferedReader:close	()V
    //   72: aload_1
    //   73: invokevirtual 195	java/io/FileReader:close	()V
    //   76: aconst_null
    //   77: areturn
    //   78: astore_0
    //   79: aconst_null
    //   80: areturn
    //   81: astore_0
    //   82: aconst_null
    //   83: astore_1
    //   84: aconst_null
    //   85: astore_2
    //   86: aload_1
    //   87: invokevirtual 196	java/io/BufferedReader:close	()V
    //   90: aload_2
    //   91: invokevirtual 195	java/io/FileReader:close	()V
    //   94: aload_0
    //   95: athrow
    //   96: astore_0
    //   97: goto -53 -> 44
    //   100: astore_0
    //   101: aload_2
    //   102: areturn
    //   103: astore_0
    //   104: goto -50 -> 54
    //   107: astore_0
    //   108: goto -36 -> 72
    //   111: astore_1
    //   112: goto -22 -> 90
    //   115: astore_1
    //   116: goto -22 -> 94
    //   119: astore_0
    //   120: aconst_null
    //   121: astore_3
    //   122: aload_1
    //   123: astore_2
    //   124: aload_3
    //   125: astore_1
    //   126: goto -40 -> 86
    //   129: astore_3
    //   130: aload_0
    //   131: astore 4
    //   133: aload_1
    //   134: astore_2
    //   135: aload_3
    //   136: astore_0
    //   137: aload 4
    //   139: astore_1
    //   140: goto -54 -> 86
    //   143: astore_0
    //   144: aconst_null
    //   145: astore_0
    //   146: goto -78 -> 68
    //   149: astore_2
    //   150: goto -82 -> 68
    // Local variable table:
    //   start	length	slot	name	signature
    //   22	29	0	localBufferedReader	java.io.BufferedReader
    //   60	1	0	localIOException1	java.io.IOException
    //   63	1	0	localIOException2	java.io.IOException
    //   65	4	0	localObject1	Object
    //   78	1	0	localIOException3	java.io.IOException
    //   81	14	0	localObject2	Object
    //   96	1	0	localIOException4	java.io.IOException
    //   100	1	0	localIOException5	java.io.IOException
    //   103	1	0	localIOException6	java.io.IOException
    //   107	1	0	localIOException7	java.io.IOException
    //   119	12	0	localObject3	Object
    //   136	1	0	localObject4	Object
    //   143	1	0	localIOException8	java.io.IOException
    //   145	1	0	localObject5	Object
    //   10	77	1	localFileReader	java.io.FileReader
    //   111	1	1	localIOException9	java.io.IOException
    //   115	8	1	localIOException10	java.io.IOException
    //   125	15	1	localObject6	Object
    //   27	108	2	localObject7	Object
    //   149	1	2	localIOException11	java.io.IOException
    //   121	4	3	localObject8	Object
    //   129	7	3	localObject9	Object
    //   131	7	4	localObject10	Object
    // Exception table:
    //   from	to	target	type
    //   54	58	60	java/io/IOException
    //   0	11	63	java/io/IOException
    //   72	76	78	java/io/IOException
    //   0	11	81	finally
    //   40	44	96	java/io/IOException
    //   44	48	100	java/io/IOException
    //   50	54	103	java/io/IOException
    //   68	72	107	java/io/IOException
    //   86	90	111	java/io/IOException
    //   90	94	115	java/io/IOException
    //   11	23	119	finally
    //   23	40	129	finally
    //   11	23	143	java/io/IOException
    //   23	40	149	java/io/IOException
  }
  
  public static String k(Context paramContext)
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
    catch (Exception paramContext) {}
    return null;
  }
  
  /* Error */
  private static String l()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 4
    //   5: new 172	java/io/FileReader
    //   8: dup
    //   9: ldc_w 343
    //   12: invokespecial 177	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   15: astore_1
    //   16: new 179	java/io/BufferedReader
    //   19: dup
    //   20: aload_1
    //   21: sipush 8192
    //   24: invokespecial 182	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   27: astore_2
    //   28: aload_2
    //   29: invokevirtual 183	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   32: astore 5
    //   34: aload 4
    //   36: astore_0
    //   37: aload 5
    //   39: ifnull +48 -> 87
    //   42: aload 5
    //   44: invokestatic 112	com/alipay/security/mobile/module/commonutils/a:a	(Ljava/lang/String;)Z
    //   47: ifne -19 -> 28
    //   50: aload 5
    //   52: ldc 79
    //   54: invokevirtual 189	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   57: astore_0
    //   58: aload_0
    //   59: ifnull -31 -> 28
    //   62: aload_0
    //   63: arraylength
    //   64: iconst_1
    //   65: if_icmple -37 -> 28
    //   68: aload_0
    //   69: iconst_0
    //   70: aaload
    //   71: ldc_w 345
    //   74: invokevirtual 349	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   77: ifeq -49 -> 28
    //   80: aload_0
    //   81: iconst_1
    //   82: aaload
    //   83: invokevirtual 90	java/lang/String:trim	()Ljava/lang/String;
    //   86: astore_0
    //   87: aload_1
    //   88: invokevirtual 195	java/io/FileReader:close	()V
    //   91: aload_2
    //   92: invokevirtual 196	java/io/BufferedReader:close	()V
    //   95: aload_0
    //   96: astore_1
    //   97: aload_1
    //   98: areturn
    //   99: astore_0
    //   100: aconst_null
    //   101: astore_0
    //   102: aconst_null
    //   103: astore_1
    //   104: aload_1
    //   105: ifnull +7 -> 112
    //   108: aload_1
    //   109: invokevirtual 195	java/io/FileReader:close	()V
    //   112: aload_3
    //   113: astore_1
    //   114: aload_0
    //   115: ifnull -18 -> 97
    //   118: aload_0
    //   119: invokevirtual 196	java/io/BufferedReader:close	()V
    //   122: aconst_null
    //   123: areturn
    //   124: astore_0
    //   125: aconst_null
    //   126: areturn
    //   127: astore_0
    //   128: aconst_null
    //   129: astore_1
    //   130: aconst_null
    //   131: astore_2
    //   132: aload_2
    //   133: ifnull +7 -> 140
    //   136: aload_2
    //   137: invokevirtual 195	java/io/FileReader:close	()V
    //   140: aload_1
    //   141: ifnull +7 -> 148
    //   144: aload_1
    //   145: invokevirtual 196	java/io/BufferedReader:close	()V
    //   148: aload_0
    //   149: athrow
    //   150: astore_1
    //   151: goto -60 -> 91
    //   154: astore_1
    //   155: aload_0
    //   156: areturn
    //   157: astore_1
    //   158: goto -46 -> 112
    //   161: astore_2
    //   162: goto -22 -> 140
    //   165: astore_1
    //   166: goto -18 -> 148
    //   169: astore_0
    //   170: aconst_null
    //   171: astore_3
    //   172: aload_1
    //   173: astore_2
    //   174: aload_3
    //   175: astore_1
    //   176: goto -44 -> 132
    //   179: astore_0
    //   180: aload_2
    //   181: astore_3
    //   182: aload_1
    //   183: astore_2
    //   184: aload_3
    //   185: astore_1
    //   186: goto -54 -> 132
    //   189: astore_0
    //   190: aconst_null
    //   191: astore_0
    //   192: goto -88 -> 104
    //   195: astore_0
    //   196: aload_2
    //   197: astore_0
    //   198: goto -94 -> 104
    // Local variable table:
    //   start	length	slot	name	signature
    //   36	60	0	localObject1	Object
    //   99	1	0	localIOException1	java.io.IOException
    //   101	18	0	localObject2	Object
    //   124	1	0	localIOException2	java.io.IOException
    //   127	29	0	str1	String
    //   169	1	0	localObject3	Object
    //   179	1	0	localObject4	Object
    //   189	1	0	localIOException3	java.io.IOException
    //   191	1	0	localObject5	Object
    //   195	1	0	localIOException4	java.io.IOException
    //   197	1	0	localObject6	Object
    //   15	130	1	localObject7	Object
    //   150	1	1	localIOException5	java.io.IOException
    //   154	1	1	localIOException6	java.io.IOException
    //   157	1	1	localIOException7	java.io.IOException
    //   165	8	1	localIOException8	java.io.IOException
    //   175	11	1	localObject8	Object
    //   27	110	2	localBufferedReader	java.io.BufferedReader
    //   161	1	2	localIOException9	java.io.IOException
    //   173	24	2	localObject9	Object
    //   1	184	3	localObject10	Object
    //   3	32	4	localObject11	Object
    //   32	19	5	str2	String
    // Exception table:
    //   from	to	target	type
    //   5	16	99	java/io/IOException
    //   118	122	124	java/io/IOException
    //   5	16	127	finally
    //   87	91	150	java/io/IOException
    //   91	95	154	java/io/IOException
    //   108	112	157	java/io/IOException
    //   136	140	161	java/io/IOException
    //   144	148	165	java/io/IOException
    //   16	28	169	finally
    //   28	34	179	finally
    //   42	58	179	finally
    //   62	87	179	finally
    //   16	28	189	java/io/IOException
    //   28	34	195	java/io/IOException
    //   42	58	195	java/io/IOException
    //   62	87	195	java/io/IOException
  }
  
  public static String l(Context paramContext)
  {
    try
    {
      paramContext = (WifiManager)paramContext.getSystemService("wifi");
      if (paramContext.isWifiEnabled())
      {
        paramContext = paramContext.getConnectionInfo().getBSSID();
        return paramContext;
      }
    }
    catch (Throwable paramContext) {}
    return "";
  }
  
  public static Map<String, Integer> m(Context paramContext)
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
  
  public final String c()
  {
    try
    {
      int i = new File("/sys/devices/system/cpu/").listFiles(new u(this)).length;
      return String.valueOf(i);
    }
    catch (Exception localException) {}
    return "1";
  }
}
