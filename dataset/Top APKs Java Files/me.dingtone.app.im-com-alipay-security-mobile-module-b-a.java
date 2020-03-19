package com.alipay.security.mobile.module.b;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class a
{
  private static a a = new a();
  
  private a() {}
  
  public static a a()
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
  private String a(Context paramContext, int paramInt)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 42	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   4: invokestatic 46	com/alipay/security/mobile/module/b/a:o	(Landroid/content/Context;)F
    //   7: fstore_3
    //   8: aconst_null
    //   9: astore_1
    //   10: iload_2
    //   11: invokestatic 52	android/hardware/Camera:open	(I)Landroid/hardware/Camera;
    //   14: astore 6
    //   16: aload 6
    //   18: astore_1
    //   19: aload_1
    //   20: invokevirtual 56	android/hardware/Camera:getParameters	()Landroid/hardware/Camera$Parameters;
    //   23: invokevirtual 62	android/hardware/Camera$Parameters:getSupportedPreviewSizes	()Ljava/util/List;
    //   26: astore 6
    //   28: aload 6
    //   30: new 6	com/alipay/security/mobile/module/b/a$a
    //   33: dup
    //   34: aload_0
    //   35: iconst_0
    //   36: invokespecial 65	com/alipay/security/mobile/module/b/a$a:<init>	(Lcom/alipay/security/mobile/module/b/a;B)V
    //   39: invokestatic 71	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
    //   42: iconst_0
    //   43: istore 4
    //   45: aload 6
    //   47: invokeinterface 77 1 0
    //   52: astore 7
    //   54: aload 7
    //   56: invokeinterface 83 1 0
    //   61: ifeq +58 -> 119
    //   64: aload 7
    //   66: invokeinterface 87 1 0
    //   71: checkcast 89	android/hardware/Camera$Size
    //   74: astore 8
    //   76: aload 8
    //   78: getfield 93	android/hardware/Camera$Size:width	I
    //   81: sipush 600
    //   84: if_icmplt +214 -> 298
    //   87: aload 8
    //   89: getfield 93	android/hardware/Camera$Size:width	I
    //   92: i2f
    //   93: aload 8
    //   95: getfield 96	android/hardware/Camera$Size:height	I
    //   98: i2f
    //   99: fdiv
    //   100: fload_3
    //   101: fsub
    //   102: invokestatic 102	java/lang/Math:abs	(F)F
    //   105: f2d
    //   106: ldc2_w 103
    //   109: dcmpg
    //   110: ifgt +197 -> 307
    //   113: iconst_1
    //   114: istore 5
    //   116: goto +177 -> 293
    //   119: iload 4
    //   121: aload 6
    //   123: invokeinterface 108 1 0
    //   128: if_icmpne +162 -> 290
    //   131: aload 6
    //   133: invokeinterface 108 1 0
    //   138: iconst_1
    //   139: isub
    //   140: istore 4
    //   142: aload 6
    //   144: iload 4
    //   146: invokeinterface 112 2 0
    //   151: checkcast 89	android/hardware/Camera$Size
    //   154: astore 6
    //   156: aload 6
    //   158: ifnull +125 -> 283
    //   161: getstatic 118	java/util/Locale:ENGLISH	Ljava/util/Locale;
    //   164: ldc 120
    //   166: iconst_3
    //   167: anewarray 4	java/lang/Object
    //   170: dup
    //   171: iconst_0
    //   172: iload_2
    //   173: invokestatic 126	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   176: aastore
    //   177: dup
    //   178: iconst_1
    //   179: aload 6
    //   181: getfield 93	android/hardware/Camera$Size:width	I
    //   184: invokestatic 126	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   187: aastore
    //   188: dup
    //   189: iconst_2
    //   190: aload 6
    //   192: getfield 96	android/hardware/Camera$Size:height	I
    //   195: invokestatic 126	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   198: aastore
    //   199: invokestatic 132	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   202: astore 6
    //   204: aload_1
    //   205: ifnull +7 -> 212
    //   208: aload_1
    //   209: invokevirtual 135	android/hardware/Camera:release	()V
    //   212: aload 6
    //   214: areturn
    //   215: astore 6
    //   217: aload_1
    //   218: ifnull +62 -> 280
    //   221: aload_1
    //   222: invokevirtual 135	android/hardware/Camera:release	()V
    //   225: ldc -119
    //   227: areturn
    //   228: astore_1
    //   229: aconst_null
    //   230: astore_1
    //   231: aload_1
    //   232: ifnull +48 -> 280
    //   235: aload_1
    //   236: invokevirtual 135	android/hardware/Camera:release	()V
    //   239: ldc -119
    //   241: areturn
    //   242: astore_1
    //   243: aconst_null
    //   244: astore 7
    //   246: aload_1
    //   247: astore 6
    //   249: aload 7
    //   251: ifnull +8 -> 259
    //   254: aload 7
    //   256: invokevirtual 135	android/hardware/Camera:release	()V
    //   259: aload 6
    //   261: athrow
    //   262: astore 6
    //   264: aload_1
    //   265: astore 7
    //   267: goto -18 -> 249
    //   270: astore 6
    //   272: goto -41 -> 231
    //   275: astore 6
    //   277: goto -60 -> 217
    //   280: ldc -119
    //   282: areturn
    //   283: ldc -119
    //   285: astore 6
    //   287: goto -83 -> 204
    //   290: goto -148 -> 142
    //   293: iload 5
    //   295: ifne -176 -> 119
    //   298: iload 4
    //   300: iconst_1
    //   301: iadd
    //   302: istore 4
    //   304: goto -250 -> 54
    //   307: iconst_0
    //   308: istore 5
    //   310: goto -17 -> 293
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	313	0	this	a
    //   0	313	1	paramContext	Context
    //   0	313	2	paramInt	int
    //   7	94	3	f	float
    //   43	260	4	i	int
    //   114	195	5	j	int
    //   14	199	6	localObject1	Object
    //   215	1	6	localRuntimeException1	RuntimeException
    //   247	13	6	localContext	Context
    //   262	1	6	localObject2	Object
    //   270	1	6	localException	Exception
    //   275	1	6	localRuntimeException2	RuntimeException
    //   285	1	6	str	String
    //   52	214	7	localObject3	Object
    //   74	20	8	localSize	Camera.Size
    // Exception table:
    //   from	to	target	type
    //   10	16	215	java/lang/RuntimeException
    //   10	16	228	java/lang/Exception
    //   10	16	242	finally
    //   19	42	262	finally
    //   45	54	262	finally
    //   54	113	262	finally
    //   119	142	262	finally
    //   142	156	262	finally
    //   161	204	262	finally
    //   19	42	270	java/lang/Exception
    //   45	54	270	java/lang/Exception
    //   54	113	270	java/lang/Exception
    //   119	142	270	java/lang/Exception
    //   142	156	270	java/lang/Exception
    //   161	204	270	java/lang/Exception
    //   19	42	275	java/lang/RuntimeException
    //   45	54	275	java/lang/RuntimeException
    //   54	113	275	java/lang/RuntimeException
    //   119	142	275	java/lang/RuntimeException
    //   142	156	275	java/lang/RuntimeException
    //   161	204	275	java/lang/RuntimeException
  }
  
  /* Error */
  public static String b()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: ldc -114
    //   4: astore_3
    //   5: new 144	java/io/InputStreamReader
    //   8: dup
    //   9: invokestatic 150	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   12: ldc -104
    //   14: invokevirtual 156	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   17: invokevirtual 162	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   20: invokespecial 165	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   23: astore_1
    //   24: new 167	java/io/LineNumberReader
    //   27: dup
    //   28: aload_1
    //   29: invokespecial 170	java/io/LineNumberReader:<init>	(Ljava/io/Reader;)V
    //   32: astore 4
    //   34: iconst_1
    //   35: istore_0
    //   36: aload_3
    //   37: astore_2
    //   38: iload_0
    //   39: bipush 100
    //   41: if_icmpge +50 -> 91
    //   44: aload 4
    //   46: invokevirtual 173	java/io/LineNumberReader:readLine	()Ljava/lang/String;
    //   49: astore 5
    //   51: aload_3
    //   52: astore_2
    //   53: aload 5
    //   55: ifnull +36 -> 91
    //   58: aload 5
    //   60: ldc -81
    //   62: invokevirtual 179	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   65: iflt +37 -> 102
    //   68: aload 5
    //   70: aload 5
    //   72: ldc -75
    //   74: invokevirtual 179	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   77: iconst_1
    //   78: iadd
    //   79: aload 5
    //   81: invokevirtual 184	java/lang/String:length	()I
    //   84: invokevirtual 188	java/lang/String:substring	(II)Ljava/lang/String;
    //   87: invokevirtual 191	java/lang/String:trim	()Ljava/lang/String;
    //   90: astore_2
    //   91: aload 4
    //   93: invokevirtual 194	java/io/LineNumberReader:close	()V
    //   96: aload_1
    //   97: invokevirtual 195	java/io/InputStreamReader:close	()V
    //   100: aload_2
    //   101: areturn
    //   102: iload_0
    //   103: iconst_1
    //   104: iadd
    //   105: istore_0
    //   106: goto -70 -> 36
    //   109: astore_1
    //   110: aconst_null
    //   111: astore 4
    //   113: aload_2
    //   114: astore_1
    //   115: aload 4
    //   117: astore_2
    //   118: aload_2
    //   119: ifnull +7 -> 126
    //   122: aload_2
    //   123: invokevirtual 194	java/io/LineNumberReader:close	()V
    //   126: aload_3
    //   127: astore_2
    //   128: aload_1
    //   129: ifnull -29 -> 100
    //   132: aload_1
    //   133: invokevirtual 195	java/io/InputStreamReader:close	()V
    //   136: ldc -114
    //   138: areturn
    //   139: astore_1
    //   140: ldc -114
    //   142: areturn
    //   143: astore_2
    //   144: aconst_null
    //   145: astore_3
    //   146: aconst_null
    //   147: astore_1
    //   148: aload_3
    //   149: ifnull +7 -> 156
    //   152: aload_3
    //   153: invokevirtual 194	java/io/LineNumberReader:close	()V
    //   156: aload_1
    //   157: ifnull +7 -> 164
    //   160: aload_1
    //   161: invokevirtual 195	java/io/InputStreamReader:close	()V
    //   164: aload_2
    //   165: athrow
    //   166: astore_3
    //   167: goto -71 -> 96
    //   170: astore_1
    //   171: aload_2
    //   172: areturn
    //   173: astore_2
    //   174: goto -48 -> 126
    //   177: astore_3
    //   178: goto -22 -> 156
    //   181: astore_1
    //   182: goto -18 -> 164
    //   185: astore_2
    //   186: aconst_null
    //   187: astore_3
    //   188: goto -40 -> 148
    //   191: astore_2
    //   192: aload 4
    //   194: astore_3
    //   195: goto -47 -> 148
    //   198: astore_2
    //   199: aconst_null
    //   200: astore_2
    //   201: goto -83 -> 118
    //   204: astore_2
    //   205: aload 4
    //   207: astore_2
    //   208: goto -90 -> 118
    // Local variable table:
    //   start	length	slot	name	signature
    //   35	71	0	i	int
    //   23	74	1	localInputStreamReader	java.io.InputStreamReader
    //   109	1	1	localException1	Exception
    //   114	19	1	localObject1	Object
    //   139	1	1	localIOException1	java.io.IOException
    //   147	14	1	localObject2	Object
    //   170	1	1	localIOException2	java.io.IOException
    //   181	1	1	localIOException3	java.io.IOException
    //   1	127	2	localObject3	Object
    //   143	29	2	str1	String
    //   173	1	2	localIOException4	java.io.IOException
    //   185	1	2	localObject4	Object
    //   191	1	2	localObject5	Object
    //   198	1	2	localException2	Exception
    //   200	1	2	localObject6	Object
    //   204	1	2	localException3	Exception
    //   207	1	2	localObject7	Object
    //   4	149	3	str2	String
    //   166	1	3	localIOException5	java.io.IOException
    //   177	1	3	localIOException6	java.io.IOException
    //   187	8	3	localObject8	Object
    //   32	174	4	localLineNumberReader	java.io.LineNumberReader
    //   49	31	5	str3	String
    // Exception table:
    //   from	to	target	type
    //   5	24	109	java/lang/Exception
    //   132	136	139	java/io/IOException
    //   5	24	143	finally
    //   91	96	166	java/io/IOException
    //   96	100	170	java/io/IOException
    //   122	126	173	java/io/IOException
    //   152	156	177	java/io/IOException
    //   160	164	181	java/io/IOException
    //   24	34	185	finally
    //   44	51	191	finally
    //   58	91	191	finally
    //   24	34	198	java/lang/Exception
    //   44	51	204	java/lang/Exception
    //   58	91	204	java/lang/Exception
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
    Context localContext = null;
    if (paramContext != null) {}
    for (;;)
    {
      try
      {
        paramContext = (TelephonyManager)paramContext.getSystemService("phone");
        if (paramContext != null)
        {
          paramContext = paramContext.getLine1Number();
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
  
  public static String d()
  {
    String str = j();
    if (!com.alipay.security.mobile.module.a.a.a(str)) {
      return str;
    }
    return k();
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
        for (paramContext = com.alipay.security.mobile.module.a.a.b(paramContext.toString());; paramContext = null) {
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
    //   0: aconst_null
    //   1: astore 5
    //   3: lconst_0
    //   4: lstore_3
    //   5: new 252	java/io/FileReader
    //   8: dup
    //   9: ldc -2
    //   11: invokespecial 257	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   14: astore 6
    //   16: new 259	java/io/BufferedReader
    //   19: dup
    //   20: aload 6
    //   22: sipush 8192
    //   25: invokespecial 262	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   28: astore 7
    //   30: aload 7
    //   32: invokevirtual 263	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   35: astore 5
    //   37: lload_3
    //   38: lstore_1
    //   39: aload 5
    //   41: ifnull +20 -> 61
    //   44: aload 5
    //   46: ldc_w 265
    //   49: invokevirtual 269	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   52: iconst_1
    //   53: aaload
    //   54: invokestatic 272	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   57: istore_0
    //   58: iload_0
    //   59: i2l
    //   60: lstore_1
    //   61: aload 6
    //   63: invokevirtual 273	java/io/FileReader:close	()V
    //   66: aload 7
    //   68: invokevirtual 274	java/io/BufferedReader:close	()V
    //   71: lload_1
    //   72: invokestatic 277	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   75: areturn
    //   76: astore 6
    //   78: aconst_null
    //   79: astore 6
    //   81: aload 6
    //   83: ifnull +8 -> 91
    //   86: aload 6
    //   88: invokevirtual 273	java/io/FileReader:close	()V
    //   91: lload_3
    //   92: lstore_1
    //   93: aload 5
    //   95: ifnull -24 -> 71
    //   98: aload 5
    //   100: invokevirtual 274	java/io/BufferedReader:close	()V
    //   103: lload_3
    //   104: lstore_1
    //   105: goto -34 -> 71
    //   108: astore 5
    //   110: lload_3
    //   111: lstore_1
    //   112: goto -41 -> 71
    //   115: astore 5
    //   117: aconst_null
    //   118: astore 8
    //   120: aconst_null
    //   121: astore 6
    //   123: aload 8
    //   125: ifnull +8 -> 133
    //   128: aload 8
    //   130: invokevirtual 273	java/io/FileReader:close	()V
    //   133: aload 6
    //   135: ifnull +8 -> 143
    //   138: aload 6
    //   140: invokevirtual 274	java/io/BufferedReader:close	()V
    //   143: aload 5
    //   145: athrow
    //   146: astore 5
    //   148: goto -82 -> 66
    //   151: astore 5
    //   153: goto -82 -> 71
    //   156: astore 6
    //   158: goto -67 -> 91
    //   161: astore 7
    //   163: goto -30 -> 133
    //   166: astore 6
    //   168: goto -25 -> 143
    //   171: astore 5
    //   173: aconst_null
    //   174: astore 7
    //   176: aload 6
    //   178: astore 8
    //   180: aload 7
    //   182: astore 6
    //   184: goto -61 -> 123
    //   187: astore 5
    //   189: aload 6
    //   191: astore 8
    //   193: aload 7
    //   195: astore 6
    //   197: goto -74 -> 123
    //   200: astore 7
    //   202: goto -121 -> 81
    //   205: astore 5
    //   207: aload 7
    //   209: astore 5
    //   211: goto -130 -> 81
    // Local variable table:
    //   start	length	slot	name	signature
    //   57	2	0	i	int
    //   38	74	1	l1	long
    //   4	107	3	l2	long
    //   1	98	5	str	String
    //   108	1	5	localIOException1	java.io.IOException
    //   115	29	5	localObject1	Object
    //   146	1	5	localIOException2	java.io.IOException
    //   151	1	5	localIOException3	java.io.IOException
    //   171	1	5	localObject2	Object
    //   187	1	5	localObject3	Object
    //   205	1	5	localIOException4	java.io.IOException
    //   209	1	5	localObject4	Object
    //   14	48	6	localFileReader	java.io.FileReader
    //   76	1	6	localIOException5	java.io.IOException
    //   79	60	6	localObject5	Object
    //   156	1	6	localIOException6	java.io.IOException
    //   166	11	6	localIOException7	java.io.IOException
    //   182	14	6	localObject6	Object
    //   28	39	7	localBufferedReader	java.io.BufferedReader
    //   161	1	7	localIOException8	java.io.IOException
    //   174	20	7	localObject7	Object
    //   200	8	7	localIOException9	java.io.IOException
    //   118	74	8	localObject8	Object
    // Exception table:
    //   from	to	target	type
    //   5	16	76	java/io/IOException
    //   98	103	108	java/io/IOException
    //   5	16	115	finally
    //   61	66	146	java/io/IOException
    //   66	71	151	java/io/IOException
    //   86	91	156	java/io/IOException
    //   128	133	161	java/io/IOException
    //   138	143	166	java/io/IOException
    //   16	30	171	finally
    //   30	37	187	finally
    //   44	58	187	finally
    //   16	30	200	java/io/IOException
    //   30	37	205	java/io/IOException
    //   44	58	205	java/io/IOException
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
    return null;
  }
  
  /* Error */
  private static String j()
  {
    // Byte code:
    //   0: new 252	java/io/FileReader
    //   3: dup
    //   4: ldc_w 392
    //   7: invokespecial 257	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   10: astore_2
    //   11: new 259	java/io/BufferedReader
    //   14: dup
    //   15: aload_2
    //   16: sipush 8192
    //   19: invokespecial 262	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   22: astore_1
    //   23: aload_1
    //   24: invokevirtual 263	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   27: astore_0
    //   28: aload_0
    //   29: invokestatic 211	com/alipay/security/mobile/module/a/a:a	(Ljava/lang/String;)Z
    //   32: ifne +18 -> 50
    //   35: aload_0
    //   36: invokevirtual 191	java/lang/String:trim	()Ljava/lang/String;
    //   39: astore_0
    //   40: aload_1
    //   41: invokevirtual 274	java/io/BufferedReader:close	()V
    //   44: aload_2
    //   45: invokevirtual 273	java/io/FileReader:close	()V
    //   48: aload_0
    //   49: areturn
    //   50: aload_1
    //   51: invokevirtual 274	java/io/BufferedReader:close	()V
    //   54: aload_2
    //   55: invokevirtual 273	java/io/FileReader:close	()V
    //   58: aconst_null
    //   59: areturn
    //   60: astore_0
    //   61: aconst_null
    //   62: areturn
    //   63: astore_0
    //   64: aconst_null
    //   65: astore_0
    //   66: aconst_null
    //   67: astore_2
    //   68: aload_0
    //   69: invokevirtual 274	java/io/BufferedReader:close	()V
    //   72: aload_2
    //   73: invokevirtual 273	java/io/FileReader:close	()V
    //   76: aconst_null
    //   77: areturn
    //   78: astore_0
    //   79: aconst_null
    //   80: areturn
    //   81: astore_0
    //   82: aconst_null
    //   83: astore_2
    //   84: aconst_null
    //   85: astore_1
    //   86: aload_1
    //   87: invokevirtual 274	java/io/BufferedReader:close	()V
    //   90: aload_2
    //   91: invokevirtual 273	java/io/FileReader:close	()V
    //   94: aload_0
    //   95: athrow
    //   96: astore_1
    //   97: goto -53 -> 44
    //   100: astore_1
    //   101: aload_0
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
    //   121: astore_1
    //   122: goto -36 -> 86
    //   125: astore_0
    //   126: goto -40 -> 86
    //   129: astore_0
    //   130: aconst_null
    //   131: astore_0
    //   132: goto -64 -> 68
    //   135: astore_0
    //   136: aload_1
    //   137: astore_0
    //   138: goto -70 -> 68
    // Local variable table:
    //   start	length	slot	name	signature
    //   27	22	0	str1	String
    //   60	1	0	localIOException1	java.io.IOException
    //   63	1	0	localIOException2	java.io.IOException
    //   65	4	0	localObject1	Object
    //   78	1	0	localIOException3	java.io.IOException
    //   81	21	0	str2	String
    //   103	1	0	localIOException4	java.io.IOException
    //   107	1	0	localIOException5	java.io.IOException
    //   119	1	0	localObject2	Object
    //   125	1	0	localObject3	Object
    //   129	1	0	localIOException6	java.io.IOException
    //   131	1	0	localObject4	Object
    //   135	1	0	localIOException7	java.io.IOException
    //   137	1	0	localObject5	Object
    //   22	65	1	localBufferedReader	java.io.BufferedReader
    //   96	1	1	localIOException8	java.io.IOException
    //   100	1	1	localIOException9	java.io.IOException
    //   111	1	1	localIOException10	java.io.IOException
    //   115	1	1	localIOException11	java.io.IOException
    //   121	16	1	localObject6	Object
    //   10	81	2	localFileReader	java.io.FileReader
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
    //   23	40	125	finally
    //   11	23	129	java/io/IOException
    //   23	40	135	java/io/IOException
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
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 4
    //   5: new 252	java/io/FileReader
    //   8: dup
    //   9: ldc_w 406
    //   12: invokespecial 257	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   15: astore_2
    //   16: new 259	java/io/BufferedReader
    //   19: dup
    //   20: aload_2
    //   21: sipush 8192
    //   24: invokespecial 262	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   27: astore_1
    //   28: aload_1
    //   29: invokevirtual 263	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   32: astore 5
    //   34: aload 4
    //   36: astore_0
    //   37: aload 5
    //   39: ifnull +48 -> 87
    //   42: aload 5
    //   44: invokestatic 211	com/alipay/security/mobile/module/a/a:a	(Ljava/lang/String;)Z
    //   47: ifne -19 -> 28
    //   50: aload 5
    //   52: ldc -75
    //   54: invokevirtual 269	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
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
    //   71: ldc_w 408
    //   74: invokevirtual 412	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   77: ifeq -49 -> 28
    //   80: aload_0
    //   81: iconst_1
    //   82: aaload
    //   83: invokevirtual 191	java/lang/String:trim	()Ljava/lang/String;
    //   86: astore_0
    //   87: aload_2
    //   88: invokevirtual 273	java/io/FileReader:close	()V
    //   91: aload_1
    //   92: invokevirtual 274	java/io/BufferedReader:close	()V
    //   95: aload_0
    //   96: astore_1
    //   97: aload_1
    //   98: areturn
    //   99: astore_0
    //   100: aconst_null
    //   101: astore_0
    //   102: aconst_null
    //   103: astore_2
    //   104: aload_2
    //   105: ifnull +7 -> 112
    //   108: aload_2
    //   109: invokevirtual 273	java/io/FileReader:close	()V
    //   112: aload_3
    //   113: astore_1
    //   114: aload_0
    //   115: ifnull -18 -> 97
    //   118: aload_0
    //   119: invokevirtual 274	java/io/BufferedReader:close	()V
    //   122: aconst_null
    //   123: areturn
    //   124: astore_0
    //   125: aconst_null
    //   126: areturn
    //   127: astore_0
    //   128: aconst_null
    //   129: astore_2
    //   130: aconst_null
    //   131: astore_1
    //   132: aload_2
    //   133: ifnull +7 -> 140
    //   136: aload_2
    //   137: invokevirtual 273	java/io/FileReader:close	()V
    //   140: aload_1
    //   141: ifnull +7 -> 148
    //   144: aload_1
    //   145: invokevirtual 274	java/io/BufferedReader:close	()V
    //   148: aload_0
    //   149: athrow
    //   150: astore_2
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
    //   171: astore_1
    //   172: goto -40 -> 132
    //   175: astore_0
    //   176: goto -44 -> 132
    //   179: astore_0
    //   180: aconst_null
    //   181: astore_0
    //   182: goto -78 -> 104
    //   185: astore_0
    //   186: aload_1
    //   187: astore_0
    //   188: goto -84 -> 104
    // Local variable table:
    //   start	length	slot	name	signature
    //   36	60	0	localObject1	Object
    //   99	1	0	localIOException1	java.io.IOException
    //   101	18	0	localObject2	Object
    //   124	1	0	localIOException2	java.io.IOException
    //   127	29	0	str1	String
    //   169	1	0	localObject3	Object
    //   175	1	0	localObject4	Object
    //   179	1	0	localIOException3	java.io.IOException
    //   181	1	0	localObject5	Object
    //   185	1	0	localIOException4	java.io.IOException
    //   187	1	0	localObject6	Object
    //   27	118	1	localObject7	Object
    //   154	1	1	localIOException5	java.io.IOException
    //   157	1	1	localIOException6	java.io.IOException
    //   165	1	1	localIOException7	java.io.IOException
    //   171	16	1	localObject8	Object
    //   15	122	2	localFileReader	java.io.FileReader
    //   150	1	2	localIOException8	java.io.IOException
    //   161	1	2	localIOException9	java.io.IOException
    //   1	112	3	localObject9	Object
    //   3	32	4	localObject10	Object
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
    //   28	34	175	finally
    //   42	58	175	finally
    //   62	87	175	finally
    //   16	28	179	java/io/IOException
    //   28	34	185	java/io/IOException
    //   42	58	185	java/io/IOException
    //   62	87	185	java/io/IOException
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
  
  private static float o(Context paramContext)
  {
    paramContext = paramContext.getResources().getDisplayMetrics();
    paramContext = new Point(paramContext.widthPixels, paramContext.heightPixels);
    return paramContext.y / paramContext.x;
  }
  
  public final String c()
  {
    try
    {
      int i = new File("/sys/devices/system/cpu/").listFiles(new b(this)).length;
      return String.valueOf(i);
    }
    catch (Exception localException) {}
    return "1";
  }
  
  public final String n(Context paramContext)
  {
    o(paramContext.getApplicationContext());
    int j = -1;
    StringBuilder localStringBuilder = new StringBuilder();
    int i = j;
    try
    {
      if (Integer.parseInt(Build.VERSION.SDK) > 8) {
        i = Camera.getNumberOfCameras();
      }
      j = 0;
      if (j < i)
      {
        if (j == 0) {}
        for (String str = String.format(Locale.ENGLISH, "%1$d:%2$s", new Object[] { Integer.valueOf(j), a(paramContext, j) });; str = String.format(Locale.ENGLISH, "#%1$d:%2$s", new Object[] { Integer.valueOf(j), a(paramContext, j) }))
        {
          localStringBuilder.append(str);
          j += 1;
          break;
        }
      }
      return localStringBuilder.toString();
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        i = j;
      }
    }
  }
  
  private final class a
    implements Comparator<Camera.Size>
  {
    private a() {}
  }
}
