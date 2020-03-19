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
      return null;
    }
    catch (Throwable paramBluetoothAdapter) {}
    return null;
  }
  
  public static String a(Context paramContext)
  {
    if (a(paramContext, "android.permission.READ_PHONE_STATE")) {
      return "";
    }
    localObject2 = null;
    localObject1 = localObject2;
    if (paramContext != null) {}
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      localObject1 = localObject2;
      if (paramContext != null) {
        localObject1 = paramContext.getDeviceId();
      }
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        localObject1 = localObject2;
      }
    }
    paramContext = (Context)localObject1;
    if (localObject1 == null) {
      paramContext = "";
    }
    return paramContext;
  }
  
  private static boolean a(Context paramContext, String paramString)
  {
    int i;
    if (paramContext.getPackageManager().checkPermission(paramString, paramContext.getPackageName()) == 0) {
      i = 1;
    } else {
      i = 0;
    }
    return i == 0;
  }
  
  public static String b()
  {
    try
    {
      StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
      l = localStatFs.getBlockSize();
      int i = localStatFs.getAvailableBlocks();
      l = i * l;
    }
    catch (Throwable localThrowable)
    {
      long l;
      for (;;) {}
    }
    l = 0L;
    return String.valueOf(l);
  }
  
  public static String b(Context paramContext)
  {
    str2 = "";
    if (a(paramContext, "android.permission.READ_PHONE_STATE")) {
      return "";
    }
    str1 = str2;
    if (paramContext != null) {}
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      str1 = str2;
      if (paramContext != null) {
        str1 = paramContext.getSubscriberId();
      }
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        str1 = str2;
      }
    }
    paramContext = str1;
    if (str1 == null) {
      paramContext = "";
    }
    return paramContext;
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
        l1 *= i;
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
    try
    {
      i = Settings.System.getInt(paramContext.getContentResolver(), "airplane_mode_on", 0);
    }
    catch (Throwable paramContext)
    {
      int i;
      for (;;) {}
    }
    i = 0;
    if (i == 1) {
      return "1";
    }
    return "0";
  }
  
  /* Error */
  public static String d()
  {
    // Byte code:
    //   0: ldc -99
    //   2: astore_1
    //   3: aconst_null
    //   4: astore 7
    //   6: aconst_null
    //   7: astore 6
    //   9: new 159	java/io/FileInputStream
    //   12: dup
    //   13: new 104	java/io/File
    //   16: dup
    //   17: ldc -95
    //   19: invokespecial 162	java/io/File:<init>	(Ljava/lang/String;)V
    //   22: invokespecial 165	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   25: astore_2
    //   26: new 167	java/io/InputStreamReader
    //   29: dup
    //   30: aload_2
    //   31: invokespecial 170	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   34: astore 4
    //   36: new 172	java/io/LineNumberReader
    //   39: dup
    //   40: aload 4
    //   42: invokespecial 175	java/io/LineNumberReader:<init>	(Ljava/io/Reader;)V
    //   45: astore 5
    //   47: iconst_1
    //   48: istore_0
    //   49: aload_1
    //   50: astore_3
    //   51: iload_0
    //   52: bipush 100
    //   54: if_icmpge +75 -> 129
    //   57: aload 5
    //   59: invokevirtual 178	java/io/LineNumberReader:readLine	()Ljava/lang/String;
    //   62: astore 6
    //   64: aload_1
    //   65: astore_3
    //   66: aload 6
    //   68: ifnull +61 -> 129
    //   71: aload 6
    //   73: ldc -76
    //   75: invokevirtual 184	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   78: iflt +29 -> 107
    //   81: aload 6
    //   83: aload 6
    //   85: ldc -70
    //   87: invokevirtual 184	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   90: iconst_1
    //   91: iadd
    //   92: aload 6
    //   94: invokevirtual 189	java/lang/String:length	()I
    //   97: invokevirtual 193	java/lang/String:substring	(II)Ljava/lang/String;
    //   100: invokevirtual 196	java/lang/String:trim	()Ljava/lang/String;
    //   103: astore_3
    //   104: goto +25 -> 129
    //   107: iload_0
    //   108: iconst_1
    //   109: iadd
    //   110: istore_0
    //   111: goto -62 -> 49
    //   114: astore_1
    //   115: aload_2
    //   116: astore_3
    //   117: aload 4
    //   119: astore_2
    //   120: goto +87 -> 207
    //   123: aload 4
    //   125: astore_3
    //   126: goto +123 -> 249
    //   129: aload 5
    //   131: invokevirtual 199	java/io/LineNumberReader:close	()V
    //   134: aload 4
    //   136: invokevirtual 200	java/io/InputStreamReader:close	()V
    //   139: aload_3
    //   140: astore_1
    //   141: aload_2
    //   142: invokevirtual 201	java/io/FileInputStream:close	()V
    //   145: aload_1
    //   146: astore_3
    //   147: goto +135 -> 282
    //   150: astore_1
    //   151: aload 6
    //   153: astore 5
    //   155: aload_2
    //   156: astore_3
    //   157: aload 4
    //   159: astore_2
    //   160: goto +47 -> 207
    //   163: aload 7
    //   165: astore 5
    //   167: aload 4
    //   169: astore_3
    //   170: goto +79 -> 249
    //   173: astore_1
    //   174: aconst_null
    //   175: astore 4
    //   177: aload 6
    //   179: astore 5
    //   181: aload_2
    //   182: astore_3
    //   183: aload 4
    //   185: astore_2
    //   186: goto +21 -> 207
    //   189: aconst_null
    //   190: astore_3
    //   191: aload 7
    //   193: astore 5
    //   195: goto +54 -> 249
    //   198: astore_1
    //   199: aconst_null
    //   200: astore_3
    //   201: aload_3
    //   202: astore_2
    //   203: aload 6
    //   205: astore 5
    //   207: aload 5
    //   209: ifnull +11 -> 220
    //   212: aload 5
    //   214: invokevirtual 199	java/io/LineNumberReader:close	()V
    //   217: goto +3 -> 220
    //   220: aload_2
    //   221: ifnull +10 -> 231
    //   224: aload_2
    //   225: invokevirtual 200	java/io/InputStreamReader:close	()V
    //   228: goto +3 -> 231
    //   231: aload_3
    //   232: ifnull +7 -> 239
    //   235: aload_3
    //   236: invokevirtual 201	java/io/FileInputStream:close	()V
    //   239: aload_1
    //   240: athrow
    //   241: aconst_null
    //   242: astore_2
    //   243: aload_2
    //   244: astore_3
    //   245: aload 7
    //   247: astore 5
    //   249: aload 5
    //   251: ifnull +11 -> 262
    //   254: aload 5
    //   256: invokevirtual 199	java/io/LineNumberReader:close	()V
    //   259: goto +3 -> 262
    //   262: aload_3
    //   263: ifnull +10 -> 273
    //   266: aload_3
    //   267: invokevirtual 200	java/io/InputStreamReader:close	()V
    //   270: goto +3 -> 273
    //   273: aload_1
    //   274: astore_3
    //   275: aload_2
    //   276: ifnull +6 -> 282
    //   279: goto -138 -> 141
    //   282: aload_3
    //   283: astore_1
    //   284: aload_3
    //   285: ifnonnull +6 -> 291
    //   288: ldc 66
    //   290: astore_1
    //   291: aload_1
    //   292: areturn
    //   293: astore_2
    //   294: goto -53 -> 241
    //   297: astore_3
    //   298: goto -109 -> 189
    //   301: astore_3
    //   302: goto -139 -> 163
    //   305: astore_3
    //   306: goto -183 -> 123
    //   309: astore_1
    //   310: goto -176 -> 134
    //   313: astore_1
    //   314: aload_3
    //   315: astore_1
    //   316: goto -175 -> 141
    //   319: astore_2
    //   320: aload_1
    //   321: astore_3
    //   322: goto -40 -> 282
    //   325: astore 4
    //   327: goto -107 -> 220
    //   330: astore_2
    //   331: goto -100 -> 231
    //   334: astore_2
    //   335: goto -96 -> 239
    //   338: astore 4
    //   340: goto -78 -> 262
    //   343: astore_3
    //   344: goto -71 -> 273
    // Local variable table:
    //   start	length	slot	name	signature
    //   48	63	0	i	int
    //   2	63	1	str1	String
    //   114	1	1	localObject1	Object
    //   140	6	1	localObject2	Object
    //   150	1	1	localObject3	Object
    //   173	1	1	localObject4	Object
    //   198	76	1	localObject5	Object
    //   283	9	1	localObject6	Object
    //   309	1	1	localThrowable1	Throwable
    //   313	1	1	localThrowable2	Throwable
    //   315	6	1	localObject7	Object
    //   25	251	2	localObject8	Object
    //   293	1	2	localThrowable3	Throwable
    //   319	1	2	localThrowable4	Throwable
    //   330	1	2	localThrowable5	Throwable
    //   334	1	2	localThrowable6	Throwable
    //   50	235	3	localObject9	Object
    //   297	1	3	localThrowable7	Throwable
    //   301	1	3	localThrowable8	Throwable
    //   305	10	3	localThrowable9	Throwable
    //   321	1	3	localObject10	Object
    //   343	1	3	localThrowable10	Throwable
    //   34	150	4	localInputStreamReader	java.io.InputStreamReader
    //   325	1	4	localThrowable11	Throwable
    //   338	1	4	localThrowable12	Throwable
    //   45	210	5	localObject11	Object
    //   7	197	6	str2	String
    //   4	242	7	localObject12	Object
    // Exception table:
    //   from	to	target	type
    //   57	64	114	finally
    //   71	104	114	finally
    //   36	47	150	finally
    //   26	36	173	finally
    //   9	26	198	finally
    //   9	26	293	java/lang/Throwable
    //   26	36	297	java/lang/Throwable
    //   36	47	301	java/lang/Throwable
    //   57	64	305	java/lang/Throwable
    //   71	104	305	java/lang/Throwable
    //   129	134	309	java/lang/Throwable
    //   134	139	313	java/lang/Throwable
    //   141	145	319	java/lang/Throwable
    //   212	217	325	java/lang/Throwable
    //   224	228	330	java/lang/Throwable
    //   235	239	334	java/lang/Throwable
    //   254	259	338	java/lang/Throwable
    //   266	270	343	java/lang/Throwable
  }
  
  public static String d(Context paramContext)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      paramContext = (AudioManager)paramContext.getSystemService("audio");
      if (paramContext.getRingerMode() != 0) {
        break label149;
      }
      i = 1;
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        int j;
        int k;
        int m;
        int n;
        int i1;
        continue;
        int i = 0;
      }
    }
    j = paramContext.getStreamVolume(0);
    k = paramContext.getStreamVolume(1);
    m = paramContext.getStreamVolume(2);
    n = paramContext.getStreamVolume(3);
    i1 = paramContext.getStreamVolume(4);
    localJSONObject.put("ringermode", String.valueOf(i));
    localJSONObject.put("call", String.valueOf(j));
    localJSONObject.put("system", String.valueOf(k));
    localJSONObject.put("ring", String.valueOf(m));
    localJSONObject.put("music", String.valueOf(n));
    localJSONObject.put("alarm", String.valueOf(i1));
    return localJSONObject.toString();
  }
  
  public static String e(Context paramContext)
  {
    localObject2 = null;
    localObject1 = localObject2;
    if (paramContext != null) {}
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      localObject1 = localObject2;
      if (paramContext != null) {
        localObject1 = paramContext.getNetworkOperatorName();
      }
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        localObject1 = localObject2;
      }
    }
    if (localObject1 != null)
    {
      paramContext = (Context)localObject1;
      if (!"null".equals(localObject1)) {}
    }
    else
    {
      paramContext = "";
    }
    return paramContext;
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
    if (a(paramContext, "android.permission.READ_PHONE_STATE")) {
      return "";
    }
    str2 = "";
    str1 = str2;
    if (paramContext != null) {}
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      str1 = str2;
      if (paramContext != null) {
        str1 = paramContext.getLine1Number();
      }
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        str1 = str2;
      }
    }
    paramContext = str1;
    if (str1 == null) {
      paramContext = "";
    }
    return paramContext;
  }
  
  /* Error */
  public static String g()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_0
    //   2: new 259	java/io/FileReader
    //   5: dup
    //   6: ldc -95
    //   8: invokespecial 260	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   11: astore_1
    //   12: new 262	java/io/BufferedReader
    //   15: dup
    //   16: aload_1
    //   17: invokespecial 263	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   20: astore_2
    //   21: aload_2
    //   22: invokevirtual 264	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   25: ldc_w 266
    //   28: iconst_2
    //   29: invokevirtual 270	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   32: astore_0
    //   33: aload_0
    //   34: ifnull +23 -> 57
    //   37: aload_0
    //   38: arraylength
    //   39: iconst_1
    //   40: if_icmple +17 -> 57
    //   43: aload_0
    //   44: iconst_1
    //   45: aaload
    //   46: astore_0
    //   47: aload_1
    //   48: invokevirtual 271	java/io/FileReader:close	()V
    //   51: aload_2
    //   52: invokevirtual 272	java/io/BufferedReader:close	()V
    //   55: aload_0
    //   56: areturn
    //   57: aload_1
    //   58: invokevirtual 271	java/io/FileReader:close	()V
    //   61: aload_2
    //   62: invokevirtual 272	java/io/BufferedReader:close	()V
    //   65: goto +76 -> 141
    //   68: astore_0
    //   69: aload_1
    //   70: astore_3
    //   71: aload_2
    //   72: astore_1
    //   73: goto +26 -> 99
    //   76: aload_2
    //   77: astore_0
    //   78: goto +44 -> 122
    //   81: astore_0
    //   82: aconst_null
    //   83: astore_2
    //   84: aload_1
    //   85: astore_3
    //   86: aload_2
    //   87: astore_1
    //   88: goto +11 -> 99
    //   91: goto +31 -> 122
    //   94: astore_0
    //   95: aconst_null
    //   96: astore_1
    //   97: aload_1
    //   98: astore_3
    //   99: aload_3
    //   100: ifnull +10 -> 110
    //   103: aload_3
    //   104: invokevirtual 271	java/io/FileReader:close	()V
    //   107: goto +3 -> 110
    //   110: aload_1
    //   111: ifnull +7 -> 118
    //   114: aload_1
    //   115: invokevirtual 272	java/io/BufferedReader:close	()V
    //   118: aload_0
    //   119: athrow
    //   120: aconst_null
    //   121: astore_1
    //   122: aload_1
    //   123: ifnull +10 -> 133
    //   126: aload_1
    //   127: invokevirtual 271	java/io/FileReader:close	()V
    //   130: goto +3 -> 133
    //   133: aload_0
    //   134: ifnull +7 -> 141
    //   137: aload_0
    //   138: invokevirtual 272	java/io/BufferedReader:close	()V
    //   141: ldc 66
    //   143: areturn
    //   144: astore_1
    //   145: goto -25 -> 120
    //   148: astore_2
    //   149: goto -58 -> 91
    //   152: astore_0
    //   153: goto -77 -> 76
    //   156: astore_1
    //   157: goto -106 -> 51
    //   160: astore_1
    //   161: aload_0
    //   162: areturn
    //   163: astore_0
    //   164: goto -103 -> 61
    //   167: astore_0
    //   168: goto -27 -> 141
    //   171: astore_2
    //   172: goto -62 -> 110
    //   175: astore_1
    //   176: goto -58 -> 118
    //   179: astore_1
    //   180: goto -47 -> 133
    // Local variable table:
    //   start	length	slot	name	signature
    //   1	55	0	localObject1	Object
    //   68	1	0	localObject2	Object
    //   77	1	0	localObject3	Object
    //   81	1	0	localObject4	Object
    //   94	44	0	localObject5	Object
    //   152	10	0	localThrowable1	Throwable
    //   163	1	0	localThrowable2	Throwable
    //   167	1	0	localThrowable3	Throwable
    //   11	116	1	localObject6	Object
    //   144	1	1	localThrowable4	Throwable
    //   156	1	1	localThrowable5	Throwable
    //   160	1	1	localThrowable6	Throwable
    //   175	1	1	localThrowable7	Throwable
    //   179	1	1	localThrowable8	Throwable
    //   20	67	2	localBufferedReader	java.io.BufferedReader
    //   148	1	2	localThrowable9	Throwable
    //   171	1	2	localThrowable10	Throwable
    //   70	34	3	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   21	33	68	finally
    //   37	43	68	finally
    //   12	21	81	finally
    //   2	12	94	finally
    //   2	12	144	java/lang/Throwable
    //   12	21	148	java/lang/Throwable
    //   21	33	152	java/lang/Throwable
    //   37	43	152	java/lang/Throwable
    //   47	51	156	java/lang/Throwable
    //   51	55	160	java/lang/Throwable
    //   57	61	163	java/lang/Throwable
    //   61	65	167	java/lang/Throwable
    //   137	141	167	java/lang/Throwable
    //   103	107	171	java/lang/Throwable
    //   114	118	175	java/lang/Throwable
    //   126	130	179	java/lang/Throwable
  }
  
  public static String g(Context paramContext)
  {
    localObject2 = null;
    localObject1 = localObject2;
    if (paramContext != null) {}
    try
    {
      paramContext = (SensorManager)paramContext.getSystemService("sensor");
      localObject1 = localObject2;
      if (paramContext != null)
      {
        Object localObject3 = paramContext.getSensorList(-1);
        localObject1 = localObject2;
        if (localObject3 != null)
        {
          localObject1 = localObject2;
          if (((List)localObject3).size() > 0)
          {
            paramContext = new StringBuilder();
            localObject1 = ((List)localObject3).iterator();
            while (((Iterator)localObject1).hasNext())
            {
              localObject3 = (Sensor)((Iterator)localObject1).next();
              paramContext.append(((Sensor)localObject3).getName());
              paramContext.append(((Sensor)localObject3).getVersion());
              paramContext.append(((Sensor)localObject3).getVendor());
            }
            localObject1 = a.e(paramContext.toString());
          }
        }
      }
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        localObject1 = localObject2;
      }
    }
    paramContext = (Context)localObject1;
    if (localObject1 == null) {
      paramContext = "";
    }
    return paramContext;
  }
  
  /* Error */
  public static String h()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 8
    //   3: lconst_0
    //   4: lstore_3
    //   5: new 259	java/io/FileReader
    //   8: dup
    //   9: ldc_w 327
    //   12: invokespecial 260	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   15: astore 9
    //   17: new 262	java/io/BufferedReader
    //   20: dup
    //   21: aload 9
    //   23: sipush 8192
    //   26: invokespecial 330	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   29: astore 7
    //   31: aload 7
    //   33: invokevirtual 264	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   36: astore 8
    //   38: lload_3
    //   39: lstore_1
    //   40: aload 8
    //   42: ifnull +20 -> 62
    //   45: aload 8
    //   47: ldc_w 332
    //   50: invokevirtual 335	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   53: iconst_1
    //   54: aaload
    //   55: invokestatic 340	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   58: istore_0
    //   59: iload_0
    //   60: i2l
    //   61: lstore_1
    //   62: aload 9
    //   64: invokevirtual 271	java/io/FileReader:close	()V
    //   67: lload_1
    //   68: lstore 5
    //   70: aload 7
    //   72: invokevirtual 272	java/io/BufferedReader:close	()V
    //   75: goto +98 -> 173
    //   78: astore 8
    //   80: goto +30 -> 110
    //   83: goto +60 -> 143
    //   86: astore 8
    //   88: aconst_null
    //   89: astore 7
    //   91: goto +19 -> 110
    //   94: aload 8
    //   96: astore 7
    //   98: goto +45 -> 143
    //   101: astore 8
    //   103: aconst_null
    //   104: astore 9
    //   106: aload 9
    //   108: astore 7
    //   110: aload 9
    //   112: ifnull +11 -> 123
    //   115: aload 9
    //   117: invokevirtual 271	java/io/FileReader:close	()V
    //   120: goto +3 -> 123
    //   123: aload 7
    //   125: ifnull +8 -> 133
    //   128: aload 7
    //   130: invokevirtual 272	java/io/BufferedReader:close	()V
    //   133: aload 8
    //   135: athrow
    //   136: aconst_null
    //   137: astore 9
    //   139: aload 8
    //   141: astore 7
    //   143: aload 9
    //   145: ifnull +11 -> 156
    //   148: aload 9
    //   150: invokevirtual 271	java/io/FileReader:close	()V
    //   153: goto +3 -> 156
    //   156: lload_3
    //   157: lstore_1
    //   158: aload 7
    //   160: ifnull +13 -> 173
    //   163: lload_3
    //   164: lstore 5
    //   166: aload 7
    //   168: invokevirtual 272	java/io/BufferedReader:close	()V
    //   171: lload_3
    //   172: lstore_1
    //   173: lload_1
    //   174: invokestatic 121	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   177: areturn
    //   178: astore 7
    //   180: goto -44 -> 136
    //   183: astore 7
    //   185: goto -91 -> 94
    //   188: astore 8
    //   190: goto -107 -> 83
    //   193: astore 8
    //   195: goto -128 -> 67
    //   198: astore 7
    //   200: lload 5
    //   202: lstore_1
    //   203: goto -30 -> 173
    //   206: astore 9
    //   208: goto -85 -> 123
    //   211: astore 7
    //   213: goto -80 -> 133
    //   216: astore 8
    //   218: goto -62 -> 156
    // Local variable table:
    //   start	length	slot	name	signature
    //   58	2	0	i	int
    //   39	164	1	l1	long
    //   4	168	3	l2	long
    //   68	133	5	l3	long
    //   29	138	7	localObject1	Object
    //   178	1	7	localThrowable1	Throwable
    //   183	1	7	localThrowable2	Throwable
    //   198	1	7	localThrowable3	Throwable
    //   211	1	7	localThrowable4	Throwable
    //   1	45	8	str	String
    //   78	1	8	localObject2	Object
    //   86	9	8	localObject3	Object
    //   101	39	8	localObject4	Object
    //   188	1	8	localThrowable5	Throwable
    //   193	1	8	localThrowable6	Throwable
    //   216	1	8	localThrowable7	Throwable
    //   15	134	9	localFileReader	java.io.FileReader
    //   206	1	9	localThrowable8	Throwable
    // Exception table:
    //   from	to	target	type
    //   31	38	78	finally
    //   45	59	78	finally
    //   17	31	86	finally
    //   5	17	101	finally
    //   5	17	178	java/lang/Throwable
    //   17	31	183	java/lang/Throwable
    //   31	38	188	java/lang/Throwable
    //   45	59	188	java/lang/Throwable
    //   62	67	193	java/lang/Throwable
    //   70	75	198	java/lang/Throwable
    //   166	171	198	java/lang/Throwable
    //   115	120	206	java/lang/Throwable
    //   128	133	211	java/lang/Throwable
    //   148	153	216	java/lang/Throwable
  }
  
  public static String h(Context paramContext)
  {
    JSONArray localJSONArray = new JSONArray();
    if (paramContext != null) {}
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
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    return localJSONArray.toString();
  }
  
  public static String i()
  {
    try
    {
      StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
      l = localStatFs.getBlockSize();
      int i = localStatFs.getBlockCount();
      l = i * l;
    }
    catch (Throwable localThrowable)
    {
      long l;
      for (;;) {}
    }
    l = 0L;
    return String.valueOf(l);
  }
  
  public static String i(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getResources().getDisplayMetrics();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(Integer.toString(paramContext.widthPixels));
      localStringBuilder.append("*");
      localStringBuilder.append(Integer.toString(paramContext.heightPixels));
      paramContext = localStringBuilder.toString();
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
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
        l1 *= i;
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
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramContext.widthPixels);
      paramContext = localStringBuilder.toString();
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    return "";
  }
  
  public static String k()
  {
    Object localObject1 = "";
    try
    {
      localObject2 = Class.forName("android.os.SystemProperties");
      Object localObject3 = ((Class)localObject2).newInstance();
      localObject2 = (String)((Class)localObject2).getMethod("get", new Class[] { String.class, String.class }).invoke(localObject3, new Object[] { "gsm.version.baseband", "no message" });
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
  
  public static String k(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getResources().getDisplayMetrics();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramContext.heightPixels);
      paramContext = localStringBuilder.toString();
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
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
    if (a(paramContext, "android.permission.ACCESS_WIFI_STATE")) {
      return "";
    }
    try
    {
      String str = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
      if (str != null) {}
      return str;
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        try
        {
          if (str.length() != 0)
          {
            paramContext = str;
            if (!"02:00:00:00:00:00".equals(str)) {}
          }
          else
          {
            paramContext = v();
            return paramContext;
            paramContext = "";
          }
          return paramContext;
        }
        catch (Throwable paramContext) {}
        paramContext = paramContext;
      }
    }
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
  
  public static String m(Context paramContext)
  {
    if (a(paramContext, "android.permission.READ_PHONE_STATE")) {
      return "";
    }
    try
    {
      String str = ((TelephonyManager)paramContext.getSystemService("phone")).getSimSerialNumber();
      if (str != null)
      {
        paramContext = str;
        if (str == null) {
          break label47;
        }
        paramContext = str;
      }
      label47:
      return str;
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        try
        {
          if (str.length() == 0) {
            paramContext = "";
          }
          return paramContext;
        }
        catch (Throwable paramContext) {}
        paramContext = paramContext;
      }
    }
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
    try
    {
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    }
    catch (Throwable paramContext)
    {
      Object localObject;
      for (;;) {}
    }
    paramContext = "";
    localObject = paramContext;
    if (paramContext == null) {
      localObject = "";
    }
    return localObject;
  }
  
  public static String o()
  {
    try
    {
      long l = System.currentTimeMillis() - SystemClock.elapsedRealtime();
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(l - l % 1000L);
      localObject = ((StringBuilder)localObject).toString();
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return "";
  }
  
  public static String o(Context paramContext)
  {
    if (a(paramContext, "android.permission.BLUETOOTH")) {
      return "";
    }
    Object localObject2 = y();
    if (localObject2 != null) {}
    try
    {
      Object localObject1;
      if (((String)localObject2).length() != 0)
      {
        localObject1 = localObject2;
        if (!"02:00:00:00:00:00".equals(localObject2)) {}
      }
      else
      {
        localObject1 = Settings.Secure.getString(paramContext.getContentResolver(), "bluetooth_address");
      }
      localObject2 = localObject1;
      if (localObject1 == null) {
        return "";
      }
    }
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    return localObject2;
  }
  
  public static String p()
  {
    try
    {
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append(SystemClock.elapsedRealtime());
      localObject = ((StringBuilder)localObject).toString();
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
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
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    return "";
  }
  
  public static String q()
  {
    try
    {
      localStringBuilder = new StringBuilder();
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("00");
      ((StringBuilder)localObject).append(":");
      localStringBuilder.append(((StringBuilder)localObject).toString());
      i = 0;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        StringBuilder localStringBuilder;
        Object localObject;
        int i;
        continue;
        String str = "0";
        continue;
        label143:
        i += 1;
      }
    }
    if (i < 7)
    {
      if (new File(new String[] { "/dev/qemu_pipe", "/dev/socket/qemud", "/system/lib/libc_malloc_debug_qemu.so", "/sys/qemu_trace", "/system/bin/qemu-props", "/dev/socket/genyd", "/dev/socket/baseband_genyd" }[i]).exists())
      {
        localObject = "1";
        localStringBuilder.append((String)localObject);
        break label143;
      }
    }
    else
    {
      localObject = localStringBuilder.toString();
      return localObject;
      return "";
    }
  }
  
  public static String q(Context paramContext)
  {
    localObject = "";
    if (a(paramContext, "android.permission.ACCESS_WIFI_STATE")) {
      return "";
    }
    try
    {
      WifiManager localWifiManager = (WifiManager)paramContext.getSystemService("wifi");
      paramContext = (Context)localObject;
      if (localWifiManager.isWifiEnabled()) {
        paramContext = localWifiManager.getConnectionInfo().getBSSID();
      }
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        paramContext = (Context)localObject;
      }
    }
    localObject = paramContext;
    if (paramContext == null) {
      localObject = "";
    }
    return localObject;
  }
  
  public static String r()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("00");
    localStringBuilder.append(":");
    int i = 0;
    while (i <= 0)
    {
      String str = new String[] { "dalvik.system.Taint" }[0];
      try
      {
        Class.forName(str);
        localStringBuilder.append("1");
      }
      catch (Throwable localThrowable)
      {
        for (;;) {}
      }
      localStringBuilder.append("0");
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public static Map<String, Integer> r(Context paramContext)
  {
    HashMap localHashMap = new HashMap();
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
    return localHashMap;
  }
  
  /* Error */
  public static String s()
  {
    // Byte code:
    //   0: new 287	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 288	java/lang/StringBuilder:<init>	()V
    //   7: astore 6
    //   9: new 552	java/util/LinkedHashMap
    //   12: dup
    //   13: invokespecial 553	java/util/LinkedHashMap:<init>	()V
    //   16: astore 7
    //   18: aload 7
    //   20: ldc_w 555
    //   23: ldc_w 557
    //   26: invokeinterface 547 3 0
    //   31: pop
    //   32: aload 7
    //   34: ldc_w 559
    //   37: ldc_w 561
    //   40: invokeinterface 547 3 0
    //   45: pop
    //   46: aload 7
    //   48: ldc -95
    //   50: ldc_w 561
    //   53: invokeinterface 547 3 0
    //   58: pop
    //   59: new 287	java/lang/StringBuilder
    //   62: dup
    //   63: invokespecial 288	java/lang/StringBuilder:<init>	()V
    //   66: astore_3
    //   67: aload_3
    //   68: ldc_w 492
    //   71: invokevirtual 311	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: pop
    //   75: aload_3
    //   76: ldc -70
    //   78: invokevirtual 311	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: pop
    //   82: aload 6
    //   84: aload_3
    //   85: invokevirtual 321	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   88: invokevirtual 311	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   91: pop
    //   92: aload 7
    //   94: invokeinterface 565 1 0
    //   99: invokeinterface 568 1 0
    //   104: astore 8
    //   106: aload 8
    //   108: invokeinterface 298 1 0
    //   113: ifeq +160 -> 273
    //   116: aload 8
    //   118: invokeinterface 302 1 0
    //   123: checkcast 58	java/lang/String
    //   126: astore 9
    //   128: aconst_null
    //   129: astore 5
    //   131: aconst_null
    //   132: astore 4
    //   134: bipush 48
    //   136: istore_1
    //   137: new 172	java/io/LineNumberReader
    //   140: dup
    //   141: new 167	java/io/InputStreamReader
    //   144: dup
    //   145: new 159	java/io/FileInputStream
    //   148: dup
    //   149: aload 9
    //   151: invokespecial 569	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   154: invokespecial 170	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   157: invokespecial 175	java/io/LineNumberReader:<init>	(Ljava/io/Reader;)V
    //   160: astore_3
    //   161: aload_3
    //   162: invokevirtual 178	java/io/LineNumberReader:readLine	()Ljava/lang/String;
    //   165: astore 4
    //   167: iload_1
    //   168: istore_0
    //   169: aload 4
    //   171: ifnull +31 -> 202
    //   174: aload 4
    //   176: invokevirtual 572	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   179: aload 7
    //   181: aload 9
    //   183: invokeinterface 573 2 0
    //   188: checkcast 575	java/lang/CharSequence
    //   191: invokevirtual 579	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   194: istore_2
    //   195: iload_2
    //   196: ifeq -35 -> 161
    //   199: bipush 49
    //   201: istore_0
    //   202: aload 6
    //   204: iload_0
    //   205: invokevirtual 582	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   208: pop
    //   209: aload_3
    //   210: invokevirtual 199	java/io/LineNumberReader:close	()V
    //   213: goto -107 -> 106
    //   216: goto -110 -> 106
    //   219: astore 5
    //   221: aload_3
    //   222: astore 4
    //   224: aload 5
    //   226: astore_3
    //   227: goto +7 -> 234
    //   230: goto +24 -> 254
    //   233: astore_3
    //   234: aload 6
    //   236: bipush 48
    //   238: invokevirtual 582	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   241: pop
    //   242: aload 4
    //   244: ifnull +8 -> 252
    //   247: aload 4
    //   249: invokevirtual 199	java/io/LineNumberReader:close	()V
    //   252: aload_3
    //   253: athrow
    //   254: aload 6
    //   256: bipush 48
    //   258: invokevirtual 582	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   261: pop
    //   262: aload_3
    //   263: ifnull -157 -> 106
    //   266: aload_3
    //   267: invokevirtual 199	java/io/LineNumberReader:close	()V
    //   270: goto -164 -> 106
    //   273: aload 6
    //   275: invokevirtual 321	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   278: areturn
    //   279: astore_3
    //   280: aload 5
    //   282: astore_3
    //   283: goto -29 -> 254
    //   286: astore 4
    //   288: goto -58 -> 230
    //   291: astore_3
    //   292: goto -76 -> 216
    //   295: astore 4
    //   297: goto -45 -> 252
    // Local variable table:
    //   start	length	slot	name	signature
    //   168	37	0	c1	char
    //   136	32	1	c2	char
    //   194	2	2	bool	boolean
    //   66	161	3	localObject1	Object
    //   233	34	3	localObject2	Object
    //   279	1	3	localThrowable1	Throwable
    //   282	1	3	localObject3	Object
    //   291	1	3	localThrowable2	Throwable
    //   132	116	4	localObject4	Object
    //   286	1	4	localThrowable3	Throwable
    //   295	1	4	localThrowable4	Throwable
    //   129	1	5	localObject5	Object
    //   219	62	5	localObject6	Object
    //   7	267	6	localStringBuilder	StringBuilder
    //   16	164	7	localLinkedHashMap	LinkedHashMap
    //   104	13	8	localIterator	Iterator
    //   126	56	9	str	String
    // Exception table:
    //   from	to	target	type
    //   161	167	219	finally
    //   174	195	219	finally
    //   137	161	233	finally
    //   137	161	279	java/lang/Throwable
    //   161	167	286	java/lang/Throwable
    //   174	195	286	java/lang/Throwable
    //   209	213	291	java/lang/Throwable
    //   266	270	291	java/lang/Throwable
    //   247	252	295	java/lang/Throwable
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
        if (a.b(str3))
        {
          paramContext = new StringBuilder();
          paramContext.append(str2);
          paramContext.append(":");
          paramContext.append(z());
          paramContext = paramContext.toString();
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
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("00");
    ((StringBuilder)localObject1).append(":");
    localStringBuilder.append(((StringBuilder)localObject1).toString());
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    localLinkedHashMap.put("BRAND", "generic");
    localLinkedHashMap.put("BOARD", "unknown");
    localLinkedHashMap.put("DEVICE", "generic");
    localLinkedHashMap.put("HARDWARE", "goldfish");
    localLinkedHashMap.put("PRODUCT", "sdk");
    localLinkedHashMap.put("MODEL", "sdk");
    Iterator localIterator = localLinkedHashMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      c2 = '0';
      try
      {
        try
        {
          Object localObject3 = Build.class.getField(str);
          localObject1 = null;
          localObject3 = (String)((Field)localObject3).get(null);
          str = (String)localLinkedHashMap.get(str);
          if (localObject3 != null) {
            localObject1 = ((String)localObject3).toLowerCase();
          }
          c1 = c2;
          if (localObject1 != null)
          {
            boolean bool = ((String)localObject1).contains(str);
            c1 = c2;
            if (bool) {
              c1 = '1';
            }
          }
        }
        finally
        {
          localStringBuilder.append('0');
        }
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          char c1 = c2;
        }
      }
      localStringBuilder.append(c1);
    }
    return localStringBuilder.toString();
  }
  
  public static String t(Context paramContext)
  {
    for (;;)
    {
      try
      {
        boolean bool = ((KeyguardManager)paramContext.getSystemService("keyguard")).isKeyguardSecure();
        l1 = 0L;
        if (!bool) {
          return "0:0";
        }
        i = 0;
        if (i < 5)
        {
          paramContext = new String[] { "/data/system/password.key", "/data/system/gesture.key", "/data/system/gatekeeper.password.key", "/data/system/gatekeeper.gesture.key", "/data/system/gatekeeper.pattern.key" }[i];
          l2 = -1L;
        }
      }
      catch (Throwable paramContext)
      {
        long l1;
        int i;
        long l2;
        long l3;
        continue;
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
    paramContext = new StringBuilder("1:");
    paramContext.append(l1);
    paramContext = paramContext.toString();
    return paramContext;
    return "";
  }
  
  public static String u()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("00");
    ((StringBuilder)localObject).append(":");
    localStringBuilder.append(((StringBuilder)localObject).toString());
    localObject = new LinkedHashMap();
    ((Map)localObject).put("ro.hardware", "goldfish");
    ((Map)localObject).put("ro.kernel.qemu", "1");
    ((Map)localObject).put("ro.product.device", "generic");
    ((Map)localObject).put("ro.product.model", "sdk");
    ((Map)localObject).put("ro.product.brand", "generic");
    ((Map)localObject).put("ro.product.name", "sdk");
    ((Map)localObject).put("ro.build.fingerprint", "test-keys");
    ((Map)localObject).put("ro.product.manufacturer", "unknow");
    Iterator localIterator = ((Map)localObject).keySet().iterator();
    while (localIterator.hasNext())
    {
      String str2 = (String)localIterator.next();
      char c2 = '0';
      String str1 = (String)((Map)localObject).get(str2);
      str2 = a.b(str2, "");
      char c1 = c2;
      if (str2 != null)
      {
        c1 = c2;
        if (str2.contains(str1)) {
          c1 = '1';
        }
      }
      localStringBuilder.append(c1);
    }
    return localStringBuilder.toString();
  }
  
  public static String u(Context paramContext)
  {
    try
    {
      paramContext = paramContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      j = paramContext.getIntExtra("level", -1);
      i = paramContext.getIntExtra("status", -1);
      if (i == 2) {
        break label103;
      }
      if (i != 5) {
        break label98;
      }
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        int j;
        StringBuilder localStringBuilder;
        continue;
        label98:
        int i = 0;
        continue;
        label103:
        i = 1;
        continue;
        paramContext = "0";
      }
    }
    localStringBuilder = new StringBuilder();
    if (i != 0)
    {
      paramContext = "1";
      localStringBuilder.append(paramContext);
      localStringBuilder.append(":");
      localStringBuilder.append(j);
      paramContext = localStringBuilder.toString();
      return paramContext;
      return "";
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
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return "02:00:00:00:00:00";
  }
  
  private static String v(Context paramContext)
  {
    if (a(paramContext, "android.permission.ACCESS_NETWORK_STATE")) {
      return "";
    }
    Object localObject = null;
    try
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if (localNetworkInfo == null)
      {
        paramContext = localObject;
      }
      else
      {
        if (localNetworkInfo.getType() == 1) {
          return "WIFI";
        }
        paramContext = localObject;
        if (localNetworkInfo.getType() == 0)
        {
          i = localNetworkInfo.getSubtype();
          if ((i != 4) && (i != 1) && (i != 2) && (i != 7)) {
            if (i != 11) {
              break label106;
            }
          }
          return "2G";
        }
      }
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        int i;
        paramContext = localObject;
        continue;
        label106:
        if ((i == 3) || (i == 5) || (i == 6) || (i == 8) || (i == 9) || (i == 10) || (i == 12) || (i == 14) || (i == 15)) {
          break;
        }
        if (i == 13) {
          return "4G";
        }
        paramContext = "UNKNOW";
      }
    }
    return paramContext;
    return "3G";
  }
  
  /* Error */
  private static String w()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore_2
    //   4: new 259	java/io/FileReader
    //   7: dup
    //   8: ldc_w 749
    //   11: invokespecial 260	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   14: astore_0
    //   15: new 262	java/io/BufferedReader
    //   18: dup
    //   19: aload_0
    //   20: sipush 8192
    //   23: invokespecial 330	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   26: astore_1
    //   27: aload_1
    //   28: invokevirtual 264	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   31: astore_2
    //   32: aload_2
    //   33: invokestatic 250	com/alipay/b/a/a/a/a:a	(Ljava/lang/String;)Z
    //   36: ifne +18 -> 54
    //   39: aload_2
    //   40: invokevirtual 196	java/lang/String:trim	()Ljava/lang/String;
    //   43: astore_2
    //   44: aload_1
    //   45: invokevirtual 272	java/io/BufferedReader:close	()V
    //   48: aload_0
    //   49: invokevirtual 271	java/io/FileReader:close	()V
    //   52: aload_2
    //   53: areturn
    //   54: aload_1
    //   55: invokevirtual 272	java/io/BufferedReader:close	()V
    //   58: aload_0
    //   59: invokevirtual 271	java/io/FileReader:close	()V
    //   62: goto +58 -> 120
    //   65: astore_3
    //   66: aload_1
    //   67: astore_2
    //   68: aload_3
    //   69: astore_1
    //   70: goto +13 -> 83
    //   73: goto +32 -> 105
    //   76: astore_1
    //   77: goto +6 -> 83
    //   80: astore_1
    //   81: aconst_null
    //   82: astore_0
    //   83: aload_2
    //   84: ifnull +7 -> 91
    //   87: aload_2
    //   88: invokevirtual 272	java/io/BufferedReader:close	()V
    //   91: aload_0
    //   92: ifnull +7 -> 99
    //   95: aload_0
    //   96: invokevirtual 271	java/io/FileReader:close	()V
    //   99: aload_1
    //   100: athrow
    //   101: aconst_null
    //   102: astore_0
    //   103: aload_3
    //   104: astore_1
    //   105: aload_1
    //   106: ifnull +7 -> 113
    //   109: aload_1
    //   110: invokevirtual 272	java/io/BufferedReader:close	()V
    //   113: aload_0
    //   114: ifnull +6 -> 120
    //   117: goto -59 -> 58
    //   120: ldc 66
    //   122: areturn
    //   123: astore_0
    //   124: goto -23 -> 101
    //   127: astore_1
    //   128: aload_3
    //   129: astore_1
    //   130: goto -25 -> 105
    //   133: astore_2
    //   134: goto -61 -> 73
    //   137: astore_1
    //   138: goto -90 -> 48
    //   141: astore_0
    //   142: aload_2
    //   143: areturn
    //   144: astore_1
    //   145: goto -87 -> 58
    //   148: astore_0
    //   149: goto -29 -> 120
    //   152: astore_2
    //   153: goto -62 -> 91
    //   156: astore_0
    //   157: goto -58 -> 99
    //   160: astore_1
    //   161: goto -48 -> 113
    // Local variable table:
    //   start	length	slot	name	signature
    //   14	100	0	localFileReader	java.io.FileReader
    //   123	1	0	localThrowable1	Throwable
    //   141	1	0	localThrowable2	Throwable
    //   148	1	0	localThrowable3	Throwable
    //   156	1	0	localThrowable4	Throwable
    //   26	44	1	localObject1	Object
    //   76	1	1	localObject2	Object
    //   80	20	1	localObject3	Object
    //   104	6	1	localObject4	Object
    //   127	1	1	localThrowable5	Throwable
    //   129	1	1	localObject5	Object
    //   137	1	1	localThrowable6	Throwable
    //   144	1	1	localThrowable7	Throwable
    //   160	1	1	localThrowable8	Throwable
    //   3	85	2	localObject6	Object
    //   133	10	2	localThrowable9	Throwable
    //   152	1	2	localThrowable10	Throwable
    //   1	1	3	localObject7	Object
    //   65	64	3	localObject8	Object
    // Exception table:
    //   from	to	target	type
    //   27	44	65	finally
    //   15	27	76	finally
    //   4	15	80	finally
    //   4	15	123	java/lang/Throwable
    //   15	27	127	java/lang/Throwable
    //   27	44	133	java/lang/Throwable
    //   44	48	137	java/lang/Throwable
    //   48	52	141	java/lang/Throwable
    //   54	58	144	java/lang/Throwable
    //   58	62	148	java/lang/Throwable
    //   87	91	152	java/lang/Throwable
    //   95	99	156	java/lang/Throwable
    //   109	113	160	java/lang/Throwable
  }
  
  /* Error */
  private static String x()
  {
    // Byte code:
    //   0: ldc 66
    //   2: astore_1
    //   3: aconst_null
    //   4: astore_0
    //   5: new 259	java/io/FileReader
    //   8: dup
    //   9: ldc -95
    //   11: invokespecial 260	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   14: astore_2
    //   15: new 262	java/io/BufferedReader
    //   18: dup
    //   19: aload_2
    //   20: sipush 8192
    //   23: invokespecial 330	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   26: astore_3
    //   27: aload_3
    //   28: invokevirtual 264	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   31: astore 4
    //   33: aload_1
    //   34: astore_0
    //   35: aload 4
    //   37: ifnull +48 -> 85
    //   40: aload 4
    //   42: invokestatic 250	com/alipay/b/a/a/a/a:a	(Ljava/lang/String;)Z
    //   45: ifne -18 -> 27
    //   48: aload 4
    //   50: ldc -70
    //   52: invokevirtual 335	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   55: astore_0
    //   56: aload_0
    //   57: ifnull -30 -> 27
    //   60: aload_0
    //   61: arraylength
    //   62: iconst_1
    //   63: if_icmple -36 -> 27
    //   66: aload_0
    //   67: iconst_0
    //   68: aaload
    //   69: ldc_w 751
    //   72: invokevirtual 579	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   75: ifeq -48 -> 27
    //   78: aload_0
    //   79: iconst_1
    //   80: aaload
    //   81: invokevirtual 196	java/lang/String:trim	()Ljava/lang/String;
    //   84: astore_0
    //   85: aload_2
    //   86: invokevirtual 271	java/io/FileReader:close	()V
    //   89: aload_0
    //   90: astore_1
    //   91: aload_3
    //   92: invokevirtual 272	java/io/BufferedReader:close	()V
    //   95: aload_0
    //   96: areturn
    //   97: astore_1
    //   98: aload_3
    //   99: astore_0
    //   100: goto +22 -> 122
    //   103: aload_3
    //   104: astore_0
    //   105: goto +40 -> 145
    //   108: astore_1
    //   109: aconst_null
    //   110: astore_0
    //   111: goto +11 -> 122
    //   114: goto +31 -> 145
    //   117: astore_1
    //   118: aconst_null
    //   119: astore_0
    //   120: aload_0
    //   121: astore_2
    //   122: aload_2
    //   123: ifnull +10 -> 133
    //   126: aload_2
    //   127: invokevirtual 271	java/io/FileReader:close	()V
    //   130: goto +3 -> 133
    //   133: aload_0
    //   134: ifnull +7 -> 141
    //   137: aload_0
    //   138: invokevirtual 272	java/io/BufferedReader:close	()V
    //   141: aload_1
    //   142: athrow
    //   143: aconst_null
    //   144: astore_2
    //   145: aload_2
    //   146: ifnull +10 -> 156
    //   149: aload_2
    //   150: invokevirtual 271	java/io/FileReader:close	()V
    //   153: goto +3 -> 156
    //   156: aload_0
    //   157: ifnull +7 -> 164
    //   160: aload_0
    //   161: invokevirtual 272	java/io/BufferedReader:close	()V
    //   164: ldc 66
    //   166: areturn
    //   167: astore_2
    //   168: goto -25 -> 143
    //   171: astore_3
    //   172: goto -58 -> 114
    //   175: astore_0
    //   176: goto -73 -> 103
    //   179: astore_1
    //   180: goto -91 -> 89
    //   183: astore_0
    //   184: aload_1
    //   185: areturn
    //   186: astore_2
    //   187: goto -54 -> 133
    //   190: astore_0
    //   191: goto -50 -> 141
    //   194: astore_2
    //   195: goto -39 -> 156
    // Local variable table:
    //   start	length	slot	name	signature
    //   4	157	0	localObject1	Object
    //   175	1	0	localThrowable1	Throwable
    //   183	1	0	localThrowable2	Throwable
    //   190	1	0	localThrowable3	Throwable
    //   2	89	1	localObject2	Object
    //   97	1	1	localObject3	Object
    //   108	1	1	localObject4	Object
    //   117	25	1	localObject5	Object
    //   179	6	1	localThrowable4	Throwable
    //   14	136	2	localObject6	Object
    //   167	1	2	localThrowable5	Throwable
    //   186	1	2	localThrowable6	Throwable
    //   194	1	2	localThrowable7	Throwable
    //   26	78	3	localBufferedReader	java.io.BufferedReader
    //   171	1	3	localThrowable8	Throwable
    //   31	18	4	str	String
    // Exception table:
    //   from	to	target	type
    //   27	33	97	finally
    //   40	56	97	finally
    //   60	85	97	finally
    //   15	27	108	finally
    //   5	15	117	finally
    //   5	15	167	java/lang/Throwable
    //   15	27	171	java/lang/Throwable
    //   27	33	175	java/lang/Throwable
    //   40	56	175	java/lang/Throwable
    //   60	85	175	java/lang/Throwable
    //   85	89	179	java/lang/Throwable
    //   91	95	183	java/lang/Throwable
    //   160	164	183	java/lang/Throwable
    //   126	130	186	java/lang/Throwable
    //   137	141	190	java/lang/Throwable
    //   149	153	194	java/lang/Throwable
  }
  
  /* Error */
  private static String y()
  {
    // Byte code:
    //   0: ldc 66
    //   2: astore_0
    //   3: invokestatic 755	android/bluetooth/BluetoothAdapter:getDefaultAdapter	()Landroid/bluetooth/BluetoothAdapter;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnull +13 -> 21
    //   11: aload_2
    //   12: invokevirtual 758	android/bluetooth/BluetoothAdapter:isEnabled	()Z
    //   15: ifne +6 -> 21
    //   18: ldc 66
    //   20: areturn
    //   21: aload_2
    //   22: invokevirtual 760	android/bluetooth/BluetoothAdapter:getAddress	()Ljava/lang/String;
    //   25: astore_1
    //   26: aload_1
    //   27: astore_0
    //   28: goto +5 -> 33
    //   31: aconst_null
    //   32: astore_2
    //   33: aload_0
    //   34: ifnull +15 -> 49
    //   37: aload_0
    //   38: astore_1
    //   39: aload_0
    //   40: ldc_w 762
    //   43: invokevirtual 765	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   46: ifeq +8 -> 54
    //   49: aload_2
    //   50: invokestatic 767	com/alipay/b/a/a/b/b:a	(Landroid/bluetooth/BluetoothAdapter;)Ljava/lang/String;
    //   53: astore_1
    //   54: aload_1
    //   55: astore_0
    //   56: aload_1
    //   57: ifnonnull +6 -> 63
    //   60: ldc 66
    //   62: astore_0
    //   63: aload_0
    //   64: areturn
    //   65: astore_1
    //   66: goto -35 -> 31
    //   69: astore_1
    //   70: goto -37 -> 33
    //   73: astore_1
    //   74: aload_0
    //   75: astore_1
    //   76: goto -22 -> 54
    // Local variable table:
    //   start	length	slot	name	signature
    //   2	73	0	localObject1	Object
    //   25	32	1	localObject2	Object
    //   65	1	1	localThrowable1	Throwable
    //   69	1	1	localThrowable2	Throwable
    //   73	1	1	localThrowable3	Throwable
    //   75	1	1	localObject3	Object
    //   6	44	2	localBluetoothAdapter	BluetoothAdapter
    // Exception table:
    //   from	to	target	type
    //   3	7	65	java/lang/Throwable
    //   11	18	69	java/lang/Throwable
    //   21	26	69	java/lang/Throwable
    //   49	54	73	java/lang/Throwable
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
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return "";
  }
  
  public final String e()
  {
    try
    {
      int i = new File("/sys/devices/system/cpu/").listFiles(new c(this)).length;
      return String.valueOf(i);
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return "1";
  }
}
