package com.alipay.security.mobile.module.deviceinfo;

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
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.alipay.security.mobile.module.commonutils.a;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class b
{
  private static b a = new b();
  
  private b() {}
  
  public static b a()
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
    //   1: invokevirtual 44	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   4: invokestatic 48	com/alipay/security/mobile/module/deviceinfo/b:t	(Landroid/content/Context;)F
    //   7: fstore_3
    //   8: aconst_null
    //   9: astore_1
    //   10: iload_2
    //   11: invokestatic 54	android/hardware/Camera:open	(I)Landroid/hardware/Camera;
    //   14: astore 6
    //   16: aload 6
    //   18: astore_1
    //   19: aload_1
    //   20: invokevirtual 58	android/hardware/Camera:getParameters	()Landroid/hardware/Camera$Parameters;
    //   23: invokevirtual 64	android/hardware/Camera$Parameters:getSupportedPreviewSizes	()Ljava/util/List;
    //   26: astore 6
    //   28: aload 6
    //   30: new 8	com/alipay/security/mobile/module/deviceinfo/b$a
    //   33: dup
    //   34: aload_0
    //   35: iconst_0
    //   36: invokespecial 67	com/alipay/security/mobile/module/deviceinfo/b$a:<init>	(Lcom/alipay/security/mobile/module/deviceinfo/b;B)V
    //   39: invokestatic 73	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
    //   42: iconst_0
    //   43: istore 4
    //   45: aload 6
    //   47: invokeinterface 79 1 0
    //   52: astore 7
    //   54: aload 7
    //   56: invokeinterface 85 1 0
    //   61: ifeq +58 -> 119
    //   64: aload 7
    //   66: invokeinterface 89 1 0
    //   71: checkcast 91	android/hardware/Camera$Size
    //   74: astore 8
    //   76: aload 8
    //   78: getfield 95	android/hardware/Camera$Size:width	I
    //   81: sipush 600
    //   84: if_icmplt +214 -> 298
    //   87: aload 8
    //   89: getfield 95	android/hardware/Camera$Size:width	I
    //   92: i2f
    //   93: aload 8
    //   95: getfield 98	android/hardware/Camera$Size:height	I
    //   98: i2f
    //   99: fdiv
    //   100: fload_3
    //   101: fsub
    //   102: invokestatic 104	java/lang/Math:abs	(F)F
    //   105: f2d
    //   106: ldc2_w 105
    //   109: dcmpg
    //   110: ifgt +197 -> 307
    //   113: iconst_1
    //   114: istore 5
    //   116: goto +177 -> 293
    //   119: iload 4
    //   121: aload 6
    //   123: invokeinterface 110 1 0
    //   128: if_icmpne +162 -> 290
    //   131: aload 6
    //   133: invokeinterface 110 1 0
    //   138: iconst_1
    //   139: isub
    //   140: istore 4
    //   142: aload 6
    //   144: iload 4
    //   146: invokeinterface 114 2 0
    //   151: checkcast 91	android/hardware/Camera$Size
    //   154: astore 6
    //   156: aload 6
    //   158: ifnull +125 -> 283
    //   161: getstatic 120	java/util/Locale:ENGLISH	Ljava/util/Locale;
    //   164: ldc 122
    //   166: iconst_3
    //   167: anewarray 4	java/lang/Object
    //   170: dup
    //   171: iconst_0
    //   172: iload_2
    //   173: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   176: aastore
    //   177: dup
    //   178: iconst_1
    //   179: aload 6
    //   181: getfield 95	android/hardware/Camera$Size:width	I
    //   184: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   187: aastore
    //   188: dup
    //   189: iconst_2
    //   190: aload 6
    //   192: getfield 98	android/hardware/Camera$Size:height	I
    //   195: invokestatic 128	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   198: aastore
    //   199: invokestatic 134	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   202: astore 6
    //   204: aload_1
    //   205: ifnull +7 -> 212
    //   208: aload_1
    //   209: invokevirtual 137	android/hardware/Camera:release	()V
    //   212: aload 6
    //   214: areturn
    //   215: astore 6
    //   217: aload_1
    //   218: ifnull +62 -> 280
    //   221: aload_1
    //   222: invokevirtual 137	android/hardware/Camera:release	()V
    //   225: ldc -117
    //   227: areturn
    //   228: astore_1
    //   229: aconst_null
    //   230: astore_1
    //   231: aload_1
    //   232: ifnull +48 -> 280
    //   235: aload_1
    //   236: invokevirtual 137	android/hardware/Camera:release	()V
    //   239: ldc -117
    //   241: areturn
    //   242: astore_1
    //   243: aconst_null
    //   244: astore 7
    //   246: aload_1
    //   247: astore 6
    //   249: aload 7
    //   251: ifnull +8 -> 259
    //   254: aload 7
    //   256: invokevirtual 137	android/hardware/Camera:release	()V
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
    //   280: ldc -117
    //   282: areturn
    //   283: ldc -117
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
    //   0	313	0	this	b
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
    //   2: ldc -112
    //   4: astore_3
    //   5: new 146	java/io/InputStreamReader
    //   8: dup
    //   9: invokestatic 152	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   12: ldc -102
    //   14: invokevirtual 158	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   17: invokevirtual 164	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   20: invokespecial 167	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   23: astore_1
    //   24: new 169	java/io/LineNumberReader
    //   27: dup
    //   28: aload_1
    //   29: invokespecial 172	java/io/LineNumberReader:<init>	(Ljava/io/Reader;)V
    //   32: astore 4
    //   34: iconst_1
    //   35: istore_0
    //   36: aload_3
    //   37: astore_2
    //   38: iload_0
    //   39: bipush 100
    //   41: if_icmpge +50 -> 91
    //   44: aload 4
    //   46: invokevirtual 175	java/io/LineNumberReader:readLine	()Ljava/lang/String;
    //   49: astore 5
    //   51: aload_3
    //   52: astore_2
    //   53: aload 5
    //   55: ifnull +36 -> 91
    //   58: aload 5
    //   60: ldc -79
    //   62: invokevirtual 181	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   65: iflt +37 -> 102
    //   68: aload 5
    //   70: aload 5
    //   72: ldc -73
    //   74: invokevirtual 181	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   77: iconst_1
    //   78: iadd
    //   79: aload 5
    //   81: invokevirtual 186	java/lang/String:length	()I
    //   84: invokevirtual 190	java/lang/String:substring	(II)Ljava/lang/String;
    //   87: invokevirtual 193	java/lang/String:trim	()Ljava/lang/String;
    //   90: astore_2
    //   91: aload 4
    //   93: invokevirtual 196	java/io/LineNumberReader:close	()V
    //   96: aload_1
    //   97: invokevirtual 197	java/io/InputStreamReader:close	()V
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
    //   123: invokevirtual 196	java/io/LineNumberReader:close	()V
    //   126: aload_3
    //   127: astore_2
    //   128: aload_1
    //   129: ifnull -29 -> 100
    //   132: aload_1
    //   133: invokevirtual 197	java/io/InputStreamReader:close	()V
    //   136: ldc -112
    //   138: areturn
    //   139: astore_1
    //   140: ldc -112
    //   142: areturn
    //   143: astore_2
    //   144: aconst_null
    //   145: astore_3
    //   146: aconst_null
    //   147: astore_1
    //   148: aload_3
    //   149: ifnull +7 -> 156
    //   152: aload_3
    //   153: invokevirtual 196	java/io/LineNumberReader:close	()V
    //   156: aload_1
    //   157: ifnull +7 -> 164
    //   160: aload_1
    //   161: invokevirtual 197	java/io/InputStreamReader:close	()V
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
    String str = l();
    if (!a.a(str)) {
      return str;
    }
    return m();
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
        for (paramContext = a.d(paramContext.toString());; paramContext = null) {
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
    //   5: new 254	java/io/FileReader
    //   8: dup
    //   9: ldc_w 256
    //   12: invokespecial 259	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   15: astore 6
    //   17: new 261	java/io/BufferedReader
    //   20: dup
    //   21: aload 6
    //   23: sipush 8192
    //   26: invokespecial 264	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   29: astore 7
    //   31: aload 7
    //   33: invokevirtual 265	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   36: astore 5
    //   38: lload_3
    //   39: lstore_1
    //   40: aload 5
    //   42: ifnull +20 -> 62
    //   45: aload 5
    //   47: ldc_w 267
    //   50: invokevirtual 271	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   53: iconst_1
    //   54: aaload
    //   55: invokestatic 274	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   58: istore_0
    //   59: iload_0
    //   60: i2l
    //   61: lstore_1
    //   62: aload 6
    //   64: invokevirtual 275	java/io/FileReader:close	()V
    //   67: aload 7
    //   69: invokevirtual 276	java/io/BufferedReader:close	()V
    //   72: lload_1
    //   73: invokestatic 279	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   76: areturn
    //   77: astore 6
    //   79: aconst_null
    //   80: astore 6
    //   82: aload 6
    //   84: ifnull +8 -> 92
    //   87: aload 6
    //   89: invokevirtual 275	java/io/FileReader:close	()V
    //   92: lload_3
    //   93: lstore_1
    //   94: aload 5
    //   96: ifnull -24 -> 72
    //   99: aload 5
    //   101: invokevirtual 276	java/io/BufferedReader:close	()V
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
    //   131: invokevirtual 275	java/io/FileReader:close	()V
    //   134: aload 6
    //   136: ifnull +8 -> 144
    //   139: aload 6
    //   141: invokevirtual 276	java/io/BufferedReader:close	()V
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
    //   109	1	5	localIOException1	java.io.IOException
    //   116	29	5	localObject1	Object
    //   147	1	5	localIOException2	java.io.IOException
    //   152	1	5	localIOException3	java.io.IOException
    //   172	1	5	localObject2	Object
    //   188	1	5	localObject3	Object
    //   206	1	5	localIOException4	java.io.IOException
    //   210	1	5	localObject4	Object
    //   15	48	6	localFileReader	java.io.FileReader
    //   77	1	6	localIOException5	java.io.IOException
    //   80	60	6	localObject5	Object
    //   157	1	6	localIOException6	java.io.IOException
    //   167	11	6	localIOException7	java.io.IOException
    //   183	14	6	localObject6	Object
    //   29	39	7	localBufferedReader	java.io.BufferedReader
    //   162	1	7	localIOException8	java.io.IOException
    //   175	20	7	localObject7	Object
    //   201	8	7	localIOException9	java.io.IOException
    //   119	74	8	localObject8	Object
    // Exception table:
    //   from	to	target	type
    //   5	17	77	java/io/IOException
    //   99	104	109	java/io/IOException
    //   5	17	116	finally
    //   62	67	147	java/io/IOException
    //   67	72	152	java/io/IOException
    //   87	92	157	java/io/IOException
    //   129	134	162	java/io/IOException
    //   139	144	167	java/io/IOException
    //   17	31	172	finally
    //   31	38	188	finally
    //   45	59	188	finally
    //   17	31	201	java/io/IOException
    //   31	38	206	java/io/IOException
    //   45	59	206	java/io/IOException
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
    //   0: aconst_null
    //   1: astore_2
    //   2: ldc_w 414
    //   5: astore_3
    //   6: new 146	java/io/InputStreamReader
    //   9: dup
    //   10: invokestatic 152	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   13: ldc_w 416
    //   16: invokevirtual 158	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   19: invokevirtual 164	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   22: invokespecial 167	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   25: astore_1
    //   26: new 169	java/io/LineNumberReader
    //   29: dup
    //   30: aload_1
    //   31: invokespecial 172	java/io/LineNumberReader:<init>	(Ljava/io/Reader;)V
    //   34: astore 4
    //   36: iconst_1
    //   37: istore_0
    //   38: aload_3
    //   39: astore_2
    //   40: iload_0
    //   41: bipush 100
    //   43: if_icmpge +51 -> 94
    //   46: aload 4
    //   48: invokevirtual 175	java/io/LineNumberReader:readLine	()Ljava/lang/String;
    //   51: astore 5
    //   53: aload_3
    //   54: astore_2
    //   55: aload 5
    //   57: ifnull +37 -> 94
    //   60: aload 5
    //   62: ldc_w 418
    //   65: invokevirtual 181	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   68: iflt +37 -> 105
    //   71: aload 5
    //   73: aload 5
    //   75: ldc -73
    //   77: invokevirtual 181	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   80: iconst_1
    //   81: iadd
    //   82: aload 5
    //   84: invokevirtual 186	java/lang/String:length	()I
    //   87: invokevirtual 190	java/lang/String:substring	(II)Ljava/lang/String;
    //   90: invokevirtual 193	java/lang/String:trim	()Ljava/lang/String;
    //   93: astore_2
    //   94: aload 4
    //   96: invokevirtual 196	java/io/LineNumberReader:close	()V
    //   99: aload_1
    //   100: invokevirtual 197	java/io/InputStreamReader:close	()V
    //   103: aload_2
    //   104: areturn
    //   105: iload_0
    //   106: iconst_1
    //   107: iadd
    //   108: istore_0
    //   109: goto -71 -> 38
    //   112: astore_1
    //   113: aconst_null
    //   114: astore 4
    //   116: aload_2
    //   117: astore_1
    //   118: aload 4
    //   120: astore_2
    //   121: aload_2
    //   122: ifnull +7 -> 129
    //   125: aload_2
    //   126: invokevirtual 196	java/io/LineNumberReader:close	()V
    //   129: aload_3
    //   130: astore_2
    //   131: aload_1
    //   132: ifnull -29 -> 103
    //   135: aload_1
    //   136: invokevirtual 197	java/io/InputStreamReader:close	()V
    //   139: ldc_w 414
    //   142: areturn
    //   143: astore_1
    //   144: ldc_w 414
    //   147: areturn
    //   148: astore_2
    //   149: aconst_null
    //   150: astore_3
    //   151: aconst_null
    //   152: astore_1
    //   153: aload_3
    //   154: ifnull +7 -> 161
    //   157: aload_3
    //   158: invokevirtual 196	java/io/LineNumberReader:close	()V
    //   161: aload_1
    //   162: ifnull +7 -> 169
    //   165: aload_1
    //   166: invokevirtual 197	java/io/InputStreamReader:close	()V
    //   169: aload_2
    //   170: athrow
    //   171: astore_3
    //   172: goto -73 -> 99
    //   175: astore_1
    //   176: aload_2
    //   177: areturn
    //   178: astore_2
    //   179: goto -50 -> 129
    //   182: astore_3
    //   183: goto -22 -> 161
    //   186: astore_1
    //   187: goto -18 -> 169
    //   190: astore_2
    //   191: aconst_null
    //   192: astore_3
    //   193: goto -40 -> 153
    //   196: astore_2
    //   197: aload 4
    //   199: astore_3
    //   200: goto -47 -> 153
    //   203: astore_2
    //   204: aconst_null
    //   205: astore_2
    //   206: goto -85 -> 121
    //   209: astore_2
    //   210: aload 4
    //   212: astore_2
    //   213: goto -92 -> 121
    // Local variable table:
    //   start	length	slot	name	signature
    //   37	72	0	i	int
    //   25	75	1	localInputStreamReader	java.io.InputStreamReader
    //   112	1	1	localException1	Exception
    //   117	19	1	localObject1	Object
    //   143	1	1	localIOException1	java.io.IOException
    //   152	14	1	localObject2	Object
    //   175	1	1	localIOException2	java.io.IOException
    //   186	1	1	localIOException3	java.io.IOException
    //   1	130	2	localObject3	Object
    //   148	29	2	str1	String
    //   178	1	2	localIOException4	java.io.IOException
    //   190	1	2	localObject4	Object
    //   196	1	2	localObject5	Object
    //   203	1	2	localException2	Exception
    //   205	1	2	localObject6	Object
    //   209	1	2	localException3	Exception
    //   212	1	2	localObject7	Object
    //   5	153	3	str2	String
    //   171	1	3	localIOException5	java.io.IOException
    //   182	1	3	localIOException6	java.io.IOException
    //   192	8	3	localObject8	Object
    //   34	177	4	localLineNumberReader	java.io.LineNumberReader
    //   51	32	5	str3	String
    // Exception table:
    //   from	to	target	type
    //   6	26	112	java/lang/Exception
    //   135	139	143	java/io/IOException
    //   6	26	148	finally
    //   94	99	171	java/io/IOException
    //   99	103	175	java/io/IOException
    //   125	129	178	java/io/IOException
    //   157	161	182	java/io/IOException
    //   165	169	186	java/io/IOException
    //   26	36	190	finally
    //   46	53	196	finally
    //   60	94	196	finally
    //   26	36	203	java/lang/Exception
    //   46	53	209	java/lang/Exception
    //   60	94	209	java/lang/Exception
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
    //   0: new 254	java/io/FileReader
    //   3: dup
    //   4: ldc_w 425
    //   7: invokespecial 259	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   10: astore_2
    //   11: new 261	java/io/BufferedReader
    //   14: dup
    //   15: aload_2
    //   16: sipush 8192
    //   19: invokespecial 264	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   22: astore_1
    //   23: aload_1
    //   24: invokevirtual 265	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   27: astore_0
    //   28: aload_0
    //   29: invokestatic 213	com/alipay/security/mobile/module/commonutils/a:a	(Ljava/lang/String;)Z
    //   32: ifne +18 -> 50
    //   35: aload_0
    //   36: invokevirtual 193	java/lang/String:trim	()Ljava/lang/String;
    //   39: astore_0
    //   40: aload_1
    //   41: invokevirtual 276	java/io/BufferedReader:close	()V
    //   44: aload_2
    //   45: invokevirtual 275	java/io/FileReader:close	()V
    //   48: aload_0
    //   49: areturn
    //   50: aload_1
    //   51: invokevirtual 276	java/io/BufferedReader:close	()V
    //   54: aload_2
    //   55: invokevirtual 275	java/io/FileReader:close	()V
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
    //   69: invokevirtual 276	java/io/BufferedReader:close	()V
    //   72: aload_2
    //   73: invokevirtual 275	java/io/FileReader:close	()V
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
    //   87: invokevirtual 276	java/io/BufferedReader:close	()V
    //   90: aload_2
    //   91: invokevirtual 275	java/io/FileReader:close	()V
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
  
  /* Error */
  private static String m()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 4
    //   5: new 254	java/io/FileReader
    //   8: dup
    //   9: ldc_w 435
    //   12: invokespecial 259	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   15: astore_2
    //   16: new 261	java/io/BufferedReader
    //   19: dup
    //   20: aload_2
    //   21: sipush 8192
    //   24: invokespecial 264	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   27: astore_1
    //   28: aload_1
    //   29: invokevirtual 265	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   32: astore 5
    //   34: aload 4
    //   36: astore_0
    //   37: aload 5
    //   39: ifnull +48 -> 87
    //   42: aload 5
    //   44: invokestatic 213	com/alipay/security/mobile/module/commonutils/a:a	(Ljava/lang/String;)Z
    //   47: ifne -19 -> 28
    //   50: aload 5
    //   52: ldc -73
    //   54: invokevirtual 271	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
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
    //   71: ldc_w 437
    //   74: invokevirtual 441	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   77: ifeq -49 -> 28
    //   80: aload_0
    //   81: iconst_1
    //   82: aaload
    //   83: invokevirtual 193	java/lang/String:trim	()Ljava/lang/String;
    //   86: astore_0
    //   87: aload_2
    //   88: invokevirtual 275	java/io/FileReader:close	()V
    //   91: aload_1
    //   92: invokevirtual 276	java/io/BufferedReader:close	()V
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
    //   109: invokevirtual 275	java/io/FileReader:close	()V
    //   112: aload_3
    //   113: astore_1
    //   114: aload_0
    //   115: ifnull -18 -> 97
    //   118: aload_0
    //   119: invokevirtual 276	java/io/BufferedReader:close	()V
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
    //   137: invokevirtual 275	java/io/FileReader:close	()V
    //   140: aload_1
    //   141: ifnull +7 -> 148
    //   144: aload_1
    //   145: invokevirtual 276	java/io/BufferedReader:close	()V
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
  
  /* Error */
  private static String n()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 254	java/io/FileReader
    //   5: dup
    //   6: ldc_w 435
    //   9: invokespecial 259	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   12: astore_2
    //   13: new 261	java/io/BufferedReader
    //   16: dup
    //   17: aload_2
    //   18: invokespecial 478	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   21: astore_1
    //   22: aload_1
    //   23: invokevirtual 265	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   26: ldc_w 480
    //   29: iconst_2
    //   30: invokevirtual 483	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   33: astore_0
    //   34: aload_0
    //   35: ifnull +25 -> 60
    //   38: aload_0
    //   39: arraylength
    //   40: iconst_1
    //   41: if_icmple +19 -> 60
    //   44: aload_0
    //   45: iconst_1
    //   46: aaload
    //   47: astore_0
    //   48: aload_2
    //   49: invokevirtual 275	java/io/FileReader:close	()V
    //   52: aload_1
    //   53: invokevirtual 276	java/io/BufferedReader:close	()V
    //   56: aload_0
    //   57: astore_1
    //   58: aload_1
    //   59: areturn
    //   60: aload_2
    //   61: invokevirtual 275	java/io/FileReader:close	()V
    //   64: aload_1
    //   65: invokevirtual 276	java/io/BufferedReader:close	()V
    //   68: aconst_null
    //   69: areturn
    //   70: astore_0
    //   71: aconst_null
    //   72: areturn
    //   73: astore_0
    //   74: aconst_null
    //   75: astore_0
    //   76: aconst_null
    //   77: astore_2
    //   78: aload_2
    //   79: ifnull +7 -> 86
    //   82: aload_2
    //   83: invokevirtual 275	java/io/FileReader:close	()V
    //   86: aload_3
    //   87: astore_1
    //   88: aload_0
    //   89: ifnull -31 -> 58
    //   92: aload_0
    //   93: invokevirtual 276	java/io/BufferedReader:close	()V
    //   96: aconst_null
    //   97: areturn
    //   98: astore_0
    //   99: aconst_null
    //   100: areturn
    //   101: astore_0
    //   102: aconst_null
    //   103: astore_2
    //   104: aconst_null
    //   105: astore_1
    //   106: aload_2
    //   107: ifnull +7 -> 114
    //   110: aload_2
    //   111: invokevirtual 275	java/io/FileReader:close	()V
    //   114: aload_1
    //   115: ifnull +7 -> 122
    //   118: aload_1
    //   119: invokevirtual 276	java/io/BufferedReader:close	()V
    //   122: aload_0
    //   123: athrow
    //   124: astore_2
    //   125: goto -73 -> 52
    //   128: astore_1
    //   129: aload_0
    //   130: areturn
    //   131: astore_0
    //   132: goto -68 -> 64
    //   135: astore_1
    //   136: goto -50 -> 86
    //   139: astore_2
    //   140: goto -26 -> 114
    //   143: astore_1
    //   144: goto -22 -> 122
    //   147: astore_0
    //   148: aconst_null
    //   149: astore_1
    //   150: goto -44 -> 106
    //   153: astore_0
    //   154: goto -48 -> 106
    //   157: astore_0
    //   158: aconst_null
    //   159: astore_0
    //   160: goto -82 -> 78
    //   163: astore_0
    //   164: aload_1
    //   165: astore_0
    //   166: goto -88 -> 78
    // Local variable table:
    //   start	length	slot	name	signature
    //   33	24	0	localObject1	Object
    //   70	1	0	localIOException1	java.io.IOException
    //   73	1	0	localIOException2	java.io.IOException
    //   75	18	0	localObject2	Object
    //   98	1	0	localIOException3	java.io.IOException
    //   101	29	0	str	String
    //   131	1	0	localIOException4	java.io.IOException
    //   147	1	0	localObject3	Object
    //   153	1	0	localObject4	Object
    //   157	1	0	localIOException5	java.io.IOException
    //   159	1	0	localObject5	Object
    //   163	1	0	localIOException6	java.io.IOException
    //   165	1	0	localObject6	Object
    //   21	98	1	localObject7	Object
    //   128	1	1	localIOException7	java.io.IOException
    //   135	1	1	localIOException8	java.io.IOException
    //   143	1	1	localIOException9	java.io.IOException
    //   149	16	1	localObject8	Object
    //   12	99	2	localFileReader	java.io.FileReader
    //   124	1	2	localIOException10	java.io.IOException
    //   139	1	2	localIOException11	java.io.IOException
    //   1	86	3	localObject9	Object
    // Exception table:
    //   from	to	target	type
    //   64	68	70	java/io/IOException
    //   2	13	73	java/io/IOException
    //   92	96	98	java/io/IOException
    //   2	13	101	finally
    //   48	52	124	java/io/IOException
    //   52	56	128	java/io/IOException
    //   60	64	131	java/io/IOException
    //   82	86	135	java/io/IOException
    //   110	114	139	java/io/IOException
    //   118	122	143	java/io/IOException
    //   13	22	147	finally
    //   22	34	153	finally
    //   38	44	153	finally
    //   13	22	157	java/io/IOException
    //   22	34	163	java/io/IOException
    //   38	44	163	java/io/IOException
  }
  
  private static String n(Context paramContext)
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
          paramContext = paramContext.getSimOperator();
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
  
  private static String o(Context paramContext)
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
          paramContext = paramContext.getSimOperatorName();
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
  
  private static boolean o()
  {
    try
    {
      BluetoothAdapter localBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
      if (localBluetoothAdapter != null)
      {
        boolean bool = localBluetoothAdapter.isEnabled();
        if (bool) {
          return true;
        }
      }
    }
    catch (Exception localException) {}
    return false;
  }
  
  /* Error */
  private static String p()
  {
    // Byte code:
    //   0: ldc -117
    //   2: astore_2
    //   3: ldc -117
    //   5: astore_0
    //   6: new 495	java/io/FileInputStream
    //   9: dup
    //   10: ldc_w 497
    //   13: invokespecial 498	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   16: astore 4
    //   18: new 261	java/io/BufferedReader
    //   21: dup
    //   22: new 146	java/io/InputStreamReader
    //   25: dup
    //   26: aload 4
    //   28: invokespecial 167	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   31: sipush 8192
    //   34: invokespecial 264	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   37: astore_1
    //   38: aload_1
    //   39: invokevirtual 265	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   42: astore_3
    //   43: aload_3
    //   44: ifnull +27 -> 71
    //   47: new 226	java/lang/StringBuilder
    //   50: dup
    //   51: invokespecial 227	java/lang/StringBuilder:<init>	()V
    //   54: aload_0
    //   55: invokevirtual 236	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   58: aload_3
    //   59: invokevirtual 236	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: invokevirtual 248	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   65: astore_3
    //   66: aload_3
    //   67: astore_0
    //   68: goto -30 -> 38
    //   71: aload_1
    //   72: invokevirtual 276	java/io/BufferedReader:close	()V
    //   75: aload 4
    //   77: invokevirtual 501	java/io/InputStream:close	()V
    //   80: aload_2
    //   81: astore_1
    //   82: aload_0
    //   83: invokestatic 503	com/alipay/security/mobile/module/commonutils/a:b	(Ljava/lang/String;)Z
    //   86: ifeq +31 -> 117
    //   89: aload_0
    //   90: aload_0
    //   91: ldc_w 505
    //   94: invokevirtual 181	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   97: bipush 8
    //   99: iadd
    //   100: invokevirtual 507	java/lang/String:substring	(I)Ljava/lang/String;
    //   103: astore_0
    //   104: aload_0
    //   105: iconst_0
    //   106: aload_0
    //   107: ldc_w 509
    //   110: invokevirtual 181	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   113: invokevirtual 190	java/lang/String:substring	(II)Ljava/lang/String;
    //   116: astore_1
    //   117: aload_1
    //   118: areturn
    //   119: astore_1
    //   120: aconst_null
    //   121: astore_1
    //   122: aload_1
    //   123: invokevirtual 276	java/io/BufferedReader:close	()V
    //   126: aload 4
    //   128: invokevirtual 501	java/io/InputStream:close	()V
    //   131: goto -51 -> 80
    //   134: astore_1
    //   135: goto -55 -> 80
    //   138: astore_0
    //   139: aconst_null
    //   140: astore_1
    //   141: aload_1
    //   142: invokevirtual 276	java/io/BufferedReader:close	()V
    //   145: aload 4
    //   147: invokevirtual 501	java/io/InputStream:close	()V
    //   150: aload_0
    //   151: athrow
    //   152: astore_0
    //   153: ldc -117
    //   155: areturn
    //   156: astore_1
    //   157: goto -7 -> 150
    //   160: astore_0
    //   161: goto -20 -> 141
    //   164: astore_3
    //   165: goto -43 -> 122
    //   168: astore_1
    //   169: goto -89 -> 80
    //   172: astore_0
    //   173: ldc -117
    //   175: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   5	102	0	localObject1	Object
    //   138	13	0	localObject2	Object
    //   152	1	0	localException	Exception
    //   160	1	0	localObject3	Object
    //   172	1	0	localFileNotFoundException	java.io.FileNotFoundException
    //   37	81	1	localObject4	Object
    //   119	1	1	localIOException1	java.io.IOException
    //   121	2	1	localObject5	Object
    //   134	1	1	localIOException2	java.io.IOException
    //   140	2	1	localObject6	Object
    //   156	1	1	localIOException3	java.io.IOException
    //   168	1	1	localIOException4	java.io.IOException
    //   2	79	2	str1	String
    //   42	25	3	str2	String
    //   164	1	3	localIOException5	java.io.IOException
    //   16	130	4	localFileInputStream	java.io.FileInputStream
    // Exception table:
    //   from	to	target	type
    //   18	38	119	java/io/IOException
    //   122	131	134	java/io/IOException
    //   18	38	138	finally
    //   82	117	152	java/lang/Exception
    //   141	150	156	java/io/IOException
    //   38	43	160	finally
    //   47	66	160	finally
    //   38	43	164	java/io/IOException
    //   47	66	164	java/io/IOException
    //   71	80	168	java/io/IOException
    //   6	18	172	java/io/FileNotFoundException
  }
  
  private static String p(Context paramContext)
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
          paramContext = paramContext.getVoiceMailNumber();
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
  
  private static String q(Context paramContext)
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
          paramContext = paramContext.getVoiceMailAlphaTag();
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
  
  private static String r(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getResources().getDisplayMetrics();
      paramContext = paramContext.densityDpi;
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  private String s(Context paramContext)
  {
    t(paramContext.getApplicationContext());
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
  
  private static float t(Context paramContext)
  {
    paramContext = paramContext.getResources().getDisplayMetrics();
    paramContext = new Point(paramContext.widthPixels, paramContext.heightPixels);
    return paramContext.y / paramContext.x;
  }
  
  public final String c()
  {
    try
    {
      int i = new File("/sys/devices/system/cpu/").listFiles(new c(this)).length;
      return String.valueOf(i);
    }
    catch (Exception localException) {}
    return "1";
  }
  
  private final class a
    implements Comparator<Camera.Size>
  {
    private a() {}
    
    private static int a(Camera.Size paramSize1, Camera.Size paramSize2)
    {
      if (paramSize1.width == paramSize2.width) {
        return 0;
      }
      if (paramSize1.width > paramSize2.width) {
        return 1;
      }
      return -1;
    }
  }
}
