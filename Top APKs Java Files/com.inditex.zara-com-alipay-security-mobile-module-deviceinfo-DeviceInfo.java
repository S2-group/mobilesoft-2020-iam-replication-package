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
import com.alipay.security.mobile.module.commonutils.CommonUtils;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DeviceInfo
{
  private static DeviceInfo a = new DeviceInfo();
  
  private DeviceInfo() {}
  
  private static float a(Context paramContext)
  {
    paramContext = paramContext.getResources().getDisplayMetrics();
    paramContext = new Point(paramContext.widthPixels, paramContext.heightPixels);
    return paramContext.y / paramContext.x;
  }
  
  /* Error */
  private static String a()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 56	java/io/FileReader
    //   5: dup
    //   6: ldc 58
    //   8: invokespecial 61	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   11: astore_1
    //   12: new 63	java/io/BufferedReader
    //   15: dup
    //   16: aload_1
    //   17: sipush 8192
    //   20: invokespecial 66	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   23: astore_2
    //   24: aload_2
    //   25: invokevirtual 69	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   28: astore 4
    //   30: aload_2
    //   31: astore_0
    //   32: aload_1
    //   33: astore_3
    //   34: aload 4
    //   36: invokestatic 75	com/alipay/security/mobile/module/commonutils/CommonUtils:isBlank	(Ljava/lang/String;)Z
    //   39: ifne +19 -> 58
    //   42: aload 4
    //   44: invokevirtual 80	java/lang/String:trim	()Ljava/lang/String;
    //   47: astore_0
    //   48: aload_2
    //   49: invokevirtual 83	java/io/BufferedReader:close	()V
    //   52: aload_1
    //   53: invokevirtual 84	java/io/FileReader:close	()V
    //   56: aload_0
    //   57: areturn
    //   58: aload_0
    //   59: invokevirtual 83	java/io/BufferedReader:close	()V
    //   62: aload_3
    //   63: invokevirtual 84	java/io/FileReader:close	()V
    //   66: aconst_null
    //   67: areturn
    //   68: astore_0
    //   69: goto +21 -> 90
    //   72: astore_0
    //   73: aload_3
    //   74: astore_2
    //   75: goto +15 -> 90
    //   78: aconst_null
    //   79: astore_0
    //   80: aload_1
    //   81: astore_3
    //   82: goto -24 -> 58
    //   85: astore_0
    //   86: aconst_null
    //   87: astore_1
    //   88: aload_3
    //   89: astore_2
    //   90: aload_2
    //   91: invokevirtual 83	java/io/BufferedReader:close	()V
    //   94: aload_1
    //   95: invokevirtual 84	java/io/FileReader:close	()V
    //   98: aload_0
    //   99: athrow
    //   100: aconst_null
    //   101: astore_0
    //   102: aload_0
    //   103: astore_3
    //   104: goto -46 -> 58
    //   107: astore_0
    //   108: goto -8 -> 100
    //   111: astore_0
    //   112: goto -34 -> 78
    //   115: astore_0
    //   116: aload_2
    //   117: astore_0
    //   118: aload_1
    //   119: astore_3
    //   120: goto -62 -> 58
    //   123: astore_2
    //   124: goto -72 -> 52
    //   127: astore_1
    //   128: aload_0
    //   129: areturn
    //   130: astore_0
    //   131: goto -69 -> 62
    //   134: astore_0
    //   135: aconst_null
    //   136: areturn
    //   137: astore_2
    //   138: goto -44 -> 94
    //   141: astore_1
    //   142: goto -44 -> 98
    // Local variable table:
    //   start	length	slot	name	signature
    //   31	28	0	localObject1	Object
    //   68	1	0	localObject2	Object
    //   72	1	0	localObject3	Object
    //   79	1	0	localObject4	Object
    //   85	14	0	localObject5	Object
    //   101	2	0	localObject6	Object
    //   107	1	0	localIOException1	java.io.IOException
    //   111	1	0	localIOException2	java.io.IOException
    //   115	1	0	localIOException3	java.io.IOException
    //   117	12	0	localObject7	Object
    //   130	1	0	localIOException4	java.io.IOException
    //   134	1	0	localIOException5	java.io.IOException
    //   11	108	1	localFileReader	java.io.FileReader
    //   127	1	1	localIOException6	java.io.IOException
    //   141	1	1	localIOException7	java.io.IOException
    //   23	94	2	localObject8	Object
    //   123	1	2	localIOException8	java.io.IOException
    //   137	1	2	localIOException9	java.io.IOException
    //   1	119	3	localObject9	Object
    //   28	15	4	str	String
    // Exception table:
    //   from	to	target	type
    //   24	30	68	finally
    //   34	48	68	finally
    //   12	24	72	finally
    //   2	12	85	finally
    //   2	12	107	java/io/IOException
    //   12	24	111	java/io/IOException
    //   24	30	115	java/io/IOException
    //   34	48	115	java/io/IOException
    //   48	52	123	java/io/IOException
    //   52	56	127	java/io/IOException
    //   58	62	130	java/io/IOException
    //   62	66	134	java/io/IOException
    //   90	94	137	java/io/IOException
    //   94	98	141	java/io/IOException
  }
  
  /* Error */
  private String a(Context paramContext, int paramInt)
  {
    // Byte code:
    //   0: ldc 91
    //   2: astore 7
    //   4: aload_1
    //   5: invokevirtual 95	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   8: invokestatic 97	com/alipay/security/mobile/module/deviceinfo/DeviceInfo:a	(Landroid/content/Context;)F
    //   11: fstore_3
    //   12: aconst_null
    //   13: astore 8
    //   15: aconst_null
    //   16: astore 6
    //   18: iload_2
    //   19: invokestatic 103	android/hardware/Camera:open	(I)Landroid/hardware/Camera;
    //   22: astore_1
    //   23: aload_1
    //   24: invokevirtual 107	android/hardware/Camera:getParameters	()Landroid/hardware/Camera$Parameters;
    //   27: invokevirtual 113	android/hardware/Camera$Parameters:getSupportedPreviewSizes	()Ljava/util/List;
    //   30: astore 6
    //   32: aload 6
    //   34: new 6	com/alipay/security/mobile/module/deviceinfo/DeviceInfo$CameraSizeComparator
    //   37: dup
    //   38: aload_0
    //   39: iconst_0
    //   40: invokespecial 116	com/alipay/security/mobile/module/deviceinfo/DeviceInfo$CameraSizeComparator:<init>	(Lcom/alipay/security/mobile/module/deviceinfo/DeviceInfo;B)V
    //   43: invokestatic 122	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
    //   46: aload 6
    //   48: invokeinterface 128 1 0
    //   53: astore 8
    //   55: iconst_0
    //   56: istore 4
    //   58: aload 8
    //   60: invokeinterface 134 1 0
    //   65: ifeq +58 -> 123
    //   68: aload 8
    //   70: invokeinterface 138 1 0
    //   75: checkcast 140	android/hardware/Camera$Size
    //   78: astore 9
    //   80: aload 9
    //   82: getfield 143	android/hardware/Camera$Size:width	I
    //   85: sipush 600
    //   88: if_icmplt +231 -> 319
    //   91: aload 9
    //   93: getfield 143	android/hardware/Camera$Size:width	I
    //   96: i2f
    //   97: aload 9
    //   99: getfield 146	android/hardware/Camera$Size:height	I
    //   102: i2f
    //   103: fdiv
    //   104: fload_3
    //   105: fsub
    //   106: invokestatic 152	java/lang/Math:abs	(F)F
    //   109: f2d
    //   110: ldc2_w 153
    //   113: dcmpg
    //   114: ifgt +197 -> 311
    //   117: iconst_1
    //   118: istore 5
    //   120: goto +194 -> 314
    //   123: iload 4
    //   125: istore 5
    //   127: iload 4
    //   129: aload 6
    //   131: invokeinterface 158 1 0
    //   136: if_icmpne +14 -> 150
    //   139: aload 6
    //   141: invokeinterface 158 1 0
    //   146: iconst_1
    //   147: isub
    //   148: istore 5
    //   150: aload 6
    //   152: iload 5
    //   154: invokeinterface 162 2 0
    //   159: checkcast 140	android/hardware/Camera$Size
    //   162: astore 8
    //   164: aload 7
    //   166: astore 6
    //   168: aload 8
    //   170: ifnull +46 -> 216
    //   173: getstatic 168	java/util/Locale:ENGLISH	Ljava/util/Locale;
    //   176: ldc -86
    //   178: iconst_3
    //   179: anewarray 4	java/lang/Object
    //   182: dup
    //   183: iconst_0
    //   184: iload_2
    //   185: invokestatic 176	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   188: aastore
    //   189: dup
    //   190: iconst_1
    //   191: aload 8
    //   193: getfield 143	android/hardware/Camera$Size:width	I
    //   196: invokestatic 176	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   199: aastore
    //   200: dup
    //   201: iconst_2
    //   202: aload 8
    //   204: getfield 146	android/hardware/Camera$Size:height	I
    //   207: invokestatic 176	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   210: aastore
    //   211: invokestatic 180	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   214: astore 6
    //   216: aload 6
    //   218: astore 8
    //   220: aload_1
    //   221: ifnull +63 -> 284
    //   224: aload_1
    //   225: invokevirtual 183	android/hardware/Camera:release	()V
    //   228: aload 6
    //   230: areturn
    //   231: astore 6
    //   233: goto +13 -> 246
    //   236: goto +21 -> 257
    //   239: goto +29 -> 268
    //   242: astore 6
    //   244: aconst_null
    //   245: astore_1
    //   246: aload_1
    //   247: ifnull +7 -> 254
    //   250: aload_1
    //   251: invokevirtual 183	android/hardware/Camera:release	()V
    //   254: aload 6
    //   256: athrow
    //   257: aload 7
    //   259: astore 8
    //   261: aload_1
    //   262: ifnull +22 -> 284
    //   265: goto +11 -> 276
    //   268: aload 7
    //   270: astore 8
    //   272: aload_1
    //   273: ifnull +11 -> 284
    //   276: aload_1
    //   277: invokevirtual 183	android/hardware/Camera:release	()V
    //   280: aload 7
    //   282: astore 8
    //   284: aload 8
    //   286: areturn
    //   287: astore_1
    //   288: aload 8
    //   290: astore_1
    //   291: goto -23 -> 268
    //   294: astore_1
    //   295: aload 6
    //   297: astore_1
    //   298: goto -41 -> 257
    //   301: astore 6
    //   303: goto -64 -> 239
    //   306: astore 6
    //   308: goto -72 -> 236
    //   311: iconst_0
    //   312: istore 5
    //   314: iload 5
    //   316: ifne -193 -> 123
    //   319: iload 4
    //   321: iconst_1
    //   322: iadd
    //   323: istore 4
    //   325: goto -267 -> 58
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	328	0	this	DeviceInfo
    //   0	328	1	paramContext	Context
    //   0	328	2	paramInt	int
    //   11	94	3	f	float
    //   56	268	4	i	int
    //   118	197	5	j	int
    //   16	213	6	localObject1	Object
    //   231	1	6	localObject2	Object
    //   242	54	6	localObject3	Object
    //   301	1	6	localRuntimeException	RuntimeException
    //   306	1	6	localException	Exception
    //   2	279	7	str	String
    //   13	276	8	localObject4	Object
    //   78	20	9	localSize	Camera.Size
    // Exception table:
    //   from	to	target	type
    //   23	55	231	finally
    //   58	117	231	finally
    //   127	150	231	finally
    //   150	164	231	finally
    //   173	216	231	finally
    //   18	23	242	finally
    //   18	23	287	java/lang/RuntimeException
    //   18	23	294	java/lang/Exception
    //   23	55	301	java/lang/RuntimeException
    //   58	117	301	java/lang/RuntimeException
    //   127	150	301	java/lang/RuntimeException
    //   150	164	301	java/lang/RuntimeException
    //   173	216	301	java/lang/RuntimeException
    //   23	55	306	java/lang/Exception
    //   58	117	306	java/lang/Exception
    //   127	150	306	java/lang/Exception
    //   150	164	306	java/lang/Exception
    //   173	216	306	java/lang/Exception
  }
  
  /* Error */
  private static String b()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 56	java/io/FileReader
    //   5: dup
    //   6: ldc -70
    //   8: invokespecial 61	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   11: astore_2
    //   12: new 63	java/io/BufferedReader
    //   15: dup
    //   16: aload_2
    //   17: sipush 8192
    //   20: invokespecial 66	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   23: astore_0
    //   24: aload_0
    //   25: invokevirtual 69	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   28: astore 4
    //   30: aload_3
    //   31: astore_1
    //   32: aload 4
    //   34: ifnull +47 -> 81
    //   37: aload 4
    //   39: invokestatic 75	com/alipay/security/mobile/module/commonutils/CommonUtils:isBlank	(Ljava/lang/String;)Z
    //   42: ifne -18 -> 24
    //   45: aload 4
    //   47: ldc -68
    //   49: invokevirtual 192	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   52: astore_1
    //   53: aload_1
    //   54: ifnull -30 -> 24
    //   57: aload_1
    //   58: arraylength
    //   59: iconst_1
    //   60: if_icmple -36 -> 24
    //   63: aload_1
    //   64: iconst_0
    //   65: aaload
    //   66: ldc -62
    //   68: invokevirtual 198	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   71: ifeq -47 -> 24
    //   74: aload_1
    //   75: iconst_1
    //   76: aaload
    //   77: invokevirtual 80	java/lang/String:trim	()Ljava/lang/String;
    //   80: astore_1
    //   81: aload_2
    //   82: invokevirtual 84	java/io/FileReader:close	()V
    //   85: aload_0
    //   86: invokevirtual 83	java/io/BufferedReader:close	()V
    //   89: aload_1
    //   90: areturn
    //   91: astore_1
    //   92: goto +19 -> 111
    //   95: astore_1
    //   96: aconst_null
    //   97: astore_0
    //   98: goto +13 -> 111
    //   101: aconst_null
    //   102: astore_0
    //   103: goto +30 -> 133
    //   106: astore_1
    //   107: aconst_null
    //   108: astore_2
    //   109: aload_2
    //   110: astore_0
    //   111: aload_2
    //   112: ifnull +7 -> 119
    //   115: aload_2
    //   116: invokevirtual 84	java/io/FileReader:close	()V
    //   119: aload_0
    //   120: ifnull +7 -> 127
    //   123: aload_0
    //   124: invokevirtual 83	java/io/BufferedReader:close	()V
    //   127: aload_1
    //   128: athrow
    //   129: aconst_null
    //   130: astore_0
    //   131: aload_0
    //   132: astore_2
    //   133: aload_2
    //   134: ifnull +7 -> 141
    //   137: aload_2
    //   138: invokevirtual 84	java/io/FileReader:close	()V
    //   141: aload_0
    //   142: ifnull +7 -> 149
    //   145: aload_0
    //   146: invokevirtual 83	java/io/BufferedReader:close	()V
    //   149: aconst_null
    //   150: areturn
    //   151: astore_0
    //   152: goto -23 -> 129
    //   155: astore_0
    //   156: goto -55 -> 101
    //   159: astore_1
    //   160: goto -27 -> 133
    //   163: astore_2
    //   164: goto -79 -> 85
    //   167: astore_0
    //   168: aload_1
    //   169: areturn
    //   170: astore_2
    //   171: goto -52 -> 119
    //   174: astore_0
    //   175: goto -48 -> 127
    //   178: astore_1
    //   179: goto -38 -> 141
    //   182: astore_0
    //   183: aconst_null
    //   184: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   23	123	0	localObject1	Object
    //   151	1	0	localIOException1	java.io.IOException
    //   155	1	0	localIOException2	java.io.IOException
    //   167	1	0	localIOException3	java.io.IOException
    //   174	1	0	localIOException4	java.io.IOException
    //   182	1	0	localIOException5	java.io.IOException
    //   31	59	1	localObject2	Object
    //   91	1	1	localObject3	Object
    //   95	1	1	localObject4	Object
    //   106	22	1	localObject5	Object
    //   159	10	1	localIOException6	java.io.IOException
    //   178	1	1	localIOException7	java.io.IOException
    //   11	127	2	localObject6	Object
    //   163	1	2	localIOException8	java.io.IOException
    //   170	1	2	localIOException9	java.io.IOException
    //   1	30	3	localObject7	Object
    //   28	18	4	str	String
    // Exception table:
    //   from	to	target	type
    //   24	30	91	finally
    //   37	53	91	finally
    //   57	81	91	finally
    //   12	24	95	finally
    //   2	12	106	finally
    //   2	12	151	java/io/IOException
    //   12	24	155	java/io/IOException
    //   24	30	159	java/io/IOException
    //   37	53	159	java/io/IOException
    //   57	81	159	java/io/IOException
    //   81	85	163	java/io/IOException
    //   85	89	167	java/io/IOException
    //   115	119	170	java/io/IOException
    //   123	127	174	java/io/IOException
    //   137	141	178	java/io/IOException
    //   145	149	182	java/io/IOException
  }
  
  public static DeviceInfo getInstance()
  {
    return a;
  }
  
  public Map<String, Integer> getAllAppName(Context paramContext)
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
  
  public String getAndroidID(Context paramContext)
  {
    try
    {
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public String getBandVer()
  {
    try
    {
      Object localObject1 = Class.forName("android.os.SystemProperties");
      Object localObject2 = ((Class)localObject1).newInstance();
      localObject1 = (String)((Class)localObject1).getMethod("get", new Class[] { String.class, String.class }).invoke(localObject2, new Object[] { "gsm.version.baseband", "no message" });
      return localObject1;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  public String getBluMac()
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
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  public boolean getBluStatus()
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
    catch (Exception localException)
    {
      for (;;) {}
    }
    return false;
  }
  
  /* Error */
  public String getCPUHardware()
  {
    // Byte code:
    //   0: ldc_w 298
    //   3: astore 4
    //   5: aconst_null
    //   6: astore_3
    //   7: aconst_null
    //   8: astore 6
    //   10: new 300	java/io/InputStreamReader
    //   13: dup
    //   14: invokestatic 306	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   17: ldc_w 308
    //   20: invokevirtual 312	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   23: invokevirtual 318	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   26: invokespecial 321	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   29: astore_2
    //   30: new 323	java/io/LineNumberReader
    //   33: dup
    //   34: aload_2
    //   35: invokespecial 326	java/io/LineNumberReader:<init>	(Ljava/io/Reader;)V
    //   38: astore 5
    //   40: iconst_1
    //   41: istore_1
    //   42: aload 4
    //   44: astore_3
    //   45: iload_1
    //   46: bipush 100
    //   48: if_icmpge +76 -> 124
    //   51: aload 5
    //   53: invokevirtual 327	java/io/LineNumberReader:readLine	()Ljava/lang/String;
    //   56: astore 6
    //   58: aload 4
    //   60: astore_3
    //   61: aload 6
    //   63: ifnull +61 -> 124
    //   66: aload 6
    //   68: ldc_w 329
    //   71: invokevirtual 333	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   74: iflt +29 -> 103
    //   77: aload 6
    //   79: aload 6
    //   81: ldc -68
    //   83: invokevirtual 333	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   86: iconst_1
    //   87: iadd
    //   88: aload 6
    //   90: invokevirtual 336	java/lang/String:length	()I
    //   93: invokevirtual 340	java/lang/String:substring	(II)Ljava/lang/String;
    //   96: invokevirtual 80	java/lang/String:trim	()Ljava/lang/String;
    //   99: astore_3
    //   100: goto +24 -> 124
    //   103: iload_1
    //   104: iconst_1
    //   105: iadd
    //   106: istore_1
    //   107: goto -65 -> 42
    //   110: astore_3
    //   111: aload 5
    //   113: astore 4
    //   115: goto +35 -> 150
    //   118: aload 5
    //   120: astore_3
    //   121: goto +51 -> 172
    //   124: aload 5
    //   126: invokevirtual 341	java/io/LineNumberReader:close	()V
    //   129: aload_2
    //   130: invokevirtual 342	java/io/InputStreamReader:close	()V
    //   133: aload_3
    //   134: areturn
    //   135: astore_3
    //   136: aload 6
    //   138: astore 4
    //   140: goto +10 -> 150
    //   143: astore_3
    //   144: aconst_null
    //   145: astore_2
    //   146: aload 6
    //   148: astore 4
    //   150: aload 4
    //   152: ifnull +8 -> 160
    //   155: aload 4
    //   157: invokevirtual 341	java/io/LineNumberReader:close	()V
    //   160: aload_2
    //   161: ifnull +7 -> 168
    //   164: aload_2
    //   165: invokevirtual 342	java/io/InputStreamReader:close	()V
    //   168: aload_3
    //   169: athrow
    //   170: aconst_null
    //   171: astore_2
    //   172: aload_3
    //   173: ifnull +7 -> 180
    //   176: aload_3
    //   177: invokevirtual 341	java/io/LineNumberReader:close	()V
    //   180: aload_2
    //   181: ifnull +9 -> 190
    //   184: aload 4
    //   186: astore_3
    //   187: goto -58 -> 129
    //   190: ldc_w 298
    //   193: areturn
    //   194: astore_2
    //   195: goto -25 -> 170
    //   198: astore 5
    //   200: goto -28 -> 172
    //   203: astore_3
    //   204: goto -86 -> 118
    //   207: astore 4
    //   209: goto -80 -> 129
    //   212: astore_2
    //   213: aload_3
    //   214: areturn
    //   215: astore 4
    //   217: goto -57 -> 160
    //   220: astore_2
    //   221: goto -53 -> 168
    //   224: astore_3
    //   225: goto -45 -> 180
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	228	0	this	DeviceInfo
    //   41	66	1	i	int
    //   29	152	2	localInputStreamReader	java.io.InputStreamReader
    //   194	1	2	localException1	Exception
    //   212	1	2	localIOException1	java.io.IOException
    //   220	1	2	localIOException2	java.io.IOException
    //   6	94	3	localObject1	Object
    //   110	1	3	localObject2	Object
    //   120	14	3	localObject3	Object
    //   135	1	3	localObject4	Object
    //   143	34	3	localObject5	Object
    //   186	1	3	localObject6	Object
    //   203	11	3	localException2	Exception
    //   224	1	3	localIOException3	java.io.IOException
    //   3	182	4	localObject7	Object
    //   207	1	4	localIOException4	java.io.IOException
    //   215	1	4	localIOException5	java.io.IOException
    //   38	87	5	localLineNumberReader	java.io.LineNumberReader
    //   198	1	5	localException3	Exception
    //   8	139	6	str	String
    // Exception table:
    //   from	to	target	type
    //   51	58	110	finally
    //   66	100	110	finally
    //   30	40	135	finally
    //   10	30	143	finally
    //   10	30	194	java/lang/Exception
    //   30	40	198	java/lang/Exception
    //   51	58	203	java/lang/Exception
    //   66	100	203	java/lang/Exception
    //   124	129	207	java/io/IOException
    //   129	133	212	java/io/IOException
    //   155	160	215	java/io/IOException
    //   164	168	220	java/io/IOException
    //   176	180	224	java/io/IOException
  }
  
  /* Error */
  public String getCPUSerial()
  {
    // Byte code:
    //   0: ldc_w 345
    //   3: astore 4
    //   5: aconst_null
    //   6: astore_3
    //   7: aconst_null
    //   8: astore 6
    //   10: new 300	java/io/InputStreamReader
    //   13: dup
    //   14: invokestatic 306	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   17: ldc_w 347
    //   20: invokevirtual 312	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   23: invokevirtual 318	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   26: invokespecial 321	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   29: astore_2
    //   30: new 323	java/io/LineNumberReader
    //   33: dup
    //   34: aload_2
    //   35: invokespecial 326	java/io/LineNumberReader:<init>	(Ljava/io/Reader;)V
    //   38: astore 5
    //   40: iconst_1
    //   41: istore_1
    //   42: aload 4
    //   44: astore_3
    //   45: iload_1
    //   46: bipush 100
    //   48: if_icmpge +76 -> 124
    //   51: aload 5
    //   53: invokevirtual 327	java/io/LineNumberReader:readLine	()Ljava/lang/String;
    //   56: astore 6
    //   58: aload 4
    //   60: astore_3
    //   61: aload 6
    //   63: ifnull +61 -> 124
    //   66: aload 6
    //   68: ldc_w 349
    //   71: invokevirtual 333	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   74: iflt +29 -> 103
    //   77: aload 6
    //   79: aload 6
    //   81: ldc -68
    //   83: invokevirtual 333	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   86: iconst_1
    //   87: iadd
    //   88: aload 6
    //   90: invokevirtual 336	java/lang/String:length	()I
    //   93: invokevirtual 340	java/lang/String:substring	(II)Ljava/lang/String;
    //   96: invokevirtual 80	java/lang/String:trim	()Ljava/lang/String;
    //   99: astore_3
    //   100: goto +24 -> 124
    //   103: iload_1
    //   104: iconst_1
    //   105: iadd
    //   106: istore_1
    //   107: goto -65 -> 42
    //   110: astore_3
    //   111: aload 5
    //   113: astore 4
    //   115: goto +35 -> 150
    //   118: aload 5
    //   120: astore_3
    //   121: goto +51 -> 172
    //   124: aload 5
    //   126: invokevirtual 341	java/io/LineNumberReader:close	()V
    //   129: aload_2
    //   130: invokevirtual 342	java/io/InputStreamReader:close	()V
    //   133: aload_3
    //   134: areturn
    //   135: astore_3
    //   136: aload 6
    //   138: astore 4
    //   140: goto +10 -> 150
    //   143: astore_3
    //   144: aconst_null
    //   145: astore_2
    //   146: aload 6
    //   148: astore 4
    //   150: aload 4
    //   152: ifnull +8 -> 160
    //   155: aload 4
    //   157: invokevirtual 341	java/io/LineNumberReader:close	()V
    //   160: aload_2
    //   161: ifnull +7 -> 168
    //   164: aload_2
    //   165: invokevirtual 342	java/io/InputStreamReader:close	()V
    //   168: aload_3
    //   169: athrow
    //   170: aconst_null
    //   171: astore_2
    //   172: aload_3
    //   173: ifnull +7 -> 180
    //   176: aload_3
    //   177: invokevirtual 341	java/io/LineNumberReader:close	()V
    //   180: aload_2
    //   181: ifnull +9 -> 190
    //   184: aload 4
    //   186: astore_3
    //   187: goto -58 -> 129
    //   190: ldc_w 345
    //   193: areturn
    //   194: astore_2
    //   195: goto -25 -> 170
    //   198: astore 5
    //   200: goto -28 -> 172
    //   203: astore_3
    //   204: goto -86 -> 118
    //   207: astore 4
    //   209: goto -80 -> 129
    //   212: astore_2
    //   213: aload_3
    //   214: areturn
    //   215: astore 4
    //   217: goto -57 -> 160
    //   220: astore_2
    //   221: goto -53 -> 168
    //   224: astore_3
    //   225: goto -45 -> 180
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	228	0	this	DeviceInfo
    //   41	66	1	i	int
    //   29	152	2	localInputStreamReader	java.io.InputStreamReader
    //   194	1	2	localException1	Exception
    //   212	1	2	localIOException1	java.io.IOException
    //   220	1	2	localIOException2	java.io.IOException
    //   6	94	3	localObject1	Object
    //   110	1	3	localObject2	Object
    //   120	14	3	localObject3	Object
    //   135	1	3	localObject4	Object
    //   143	34	3	localObject5	Object
    //   186	1	3	localObject6	Object
    //   203	11	3	localException2	Exception
    //   224	1	3	localIOException3	java.io.IOException
    //   3	182	4	localObject7	Object
    //   207	1	4	localIOException4	java.io.IOException
    //   215	1	4	localIOException5	java.io.IOException
    //   38	87	5	localLineNumberReader	java.io.LineNumberReader
    //   198	1	5	localException3	Exception
    //   8	139	6	str	String
    // Exception table:
    //   from	to	target	type
    //   51	58	110	finally
    //   66	100	110	finally
    //   30	40	135	finally
    //   10	30	143	finally
    //   10	30	194	java/lang/Exception
    //   30	40	198	java/lang/Exception
    //   51	58	203	java/lang/Exception
    //   66	100	203	java/lang/Exception
    //   124	129	207	java/io/IOException
    //   129	133	212	java/io/IOException
    //   155	160	215	java/io/IOException
    //   164	168	220	java/io/IOException
    //   176	180	224	java/io/IOException
  }
  
  public String getCpuCount()
  {
    try
    {
      int i = new File("/sys/devices/system/cpu/").listFiles(new DeviceInfo.1CpuFilter(this)).length;
      return String.valueOf(i);
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return "1";
  }
  
  public String getCpuFrequent()
  {
    String str = a();
    if (!CommonUtils.isBlank(str)) {
      return str;
    }
    return b();
  }
  
  /* Error */
  public String getCpuName()
  {
    // Byte code:
    //   0: new 56	java/io/FileReader
    //   3: dup
    //   4: ldc -70
    //   6: invokespecial 61	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   9: astore_3
    //   10: new 63	java/io/BufferedReader
    //   13: dup
    //   14: aload_3
    //   15: invokespecial 376	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   18: astore_1
    //   19: aload_1
    //   20: invokevirtual 69	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   23: ldc_w 378
    //   26: iconst_2
    //   27: invokevirtual 381	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   30: astore_2
    //   31: aload_2
    //   32: ifnull +23 -> 55
    //   35: aload_2
    //   36: arraylength
    //   37: iconst_1
    //   38: if_icmple +17 -> 55
    //   41: aload_2
    //   42: iconst_1
    //   43: aaload
    //   44: astore_2
    //   45: aload_3
    //   46: invokevirtual 84	java/io/FileReader:close	()V
    //   49: aload_1
    //   50: invokevirtual 83	java/io/BufferedReader:close	()V
    //   53: aload_2
    //   54: areturn
    //   55: aload_3
    //   56: invokevirtual 84	java/io/FileReader:close	()V
    //   59: aload_1
    //   60: invokevirtual 83	java/io/BufferedReader:close	()V
    //   63: aconst_null
    //   64: areturn
    //   65: astore_2
    //   66: goto +19 -> 85
    //   69: astore_2
    //   70: aconst_null
    //   71: astore_1
    //   72: goto +13 -> 85
    //   75: aconst_null
    //   76: astore_1
    //   77: goto +30 -> 107
    //   80: astore_2
    //   81: aconst_null
    //   82: astore_1
    //   83: aload_1
    //   84: astore_3
    //   85: aload_3
    //   86: ifnull +7 -> 93
    //   89: aload_3
    //   90: invokevirtual 84	java/io/FileReader:close	()V
    //   93: aload_1
    //   94: ifnull +7 -> 101
    //   97: aload_1
    //   98: invokevirtual 83	java/io/BufferedReader:close	()V
    //   101: aload_2
    //   102: athrow
    //   103: aconst_null
    //   104: astore_3
    //   105: aload_3
    //   106: astore_1
    //   107: aload_3
    //   108: ifnull +7 -> 115
    //   111: aload_3
    //   112: invokevirtual 84	java/io/FileReader:close	()V
    //   115: aload_1
    //   116: ifnull +6 -> 122
    //   119: goto -60 -> 59
    //   122: aconst_null
    //   123: areturn
    //   124: astore_1
    //   125: goto -22 -> 103
    //   128: astore_1
    //   129: goto -54 -> 75
    //   132: astore_2
    //   133: goto -26 -> 107
    //   136: astore_3
    //   137: goto -88 -> 49
    //   140: astore_1
    //   141: aload_2
    //   142: areturn
    //   143: astore_2
    //   144: goto -85 -> 59
    //   147: astore_1
    //   148: aconst_null
    //   149: areturn
    //   150: astore_3
    //   151: goto -58 -> 93
    //   154: astore_1
    //   155: goto -54 -> 101
    //   158: astore_2
    //   159: goto -44 -> 115
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	162	0	this	DeviceInfo
    //   18	98	1	localObject1	Object
    //   124	1	1	localIOException1	java.io.IOException
    //   128	1	1	localIOException2	java.io.IOException
    //   140	1	1	localIOException3	java.io.IOException
    //   147	1	1	localIOException4	java.io.IOException
    //   154	1	1	localIOException5	java.io.IOException
    //   30	24	2	localObject2	Object
    //   65	1	2	localObject3	Object
    //   69	1	2	localObject4	Object
    //   80	22	2	localObject5	Object
    //   132	10	2	localIOException6	java.io.IOException
    //   143	1	2	localIOException7	java.io.IOException
    //   158	1	2	localIOException8	java.io.IOException
    //   9	103	3	localObject6	Object
    //   136	1	3	localIOException9	java.io.IOException
    //   150	1	3	localIOException10	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   19	31	65	finally
    //   35	41	65	finally
    //   10	19	69	finally
    //   0	10	80	finally
    //   0	10	124	java/io/IOException
    //   10	19	128	java/io/IOException
    //   19	31	132	java/io/IOException
    //   35	41	132	java/io/IOException
    //   45	49	136	java/io/IOException
    //   49	53	140	java/io/IOException
    //   55	59	143	java/io/IOException
    //   59	63	147	java/io/IOException
    //   89	93	150	java/io/IOException
    //   97	101	154	java/io/IOException
    //   111	115	158	java/io/IOException
  }
  
  public String getIMEI(Context paramContext)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramContext != null) {}
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      localObject1 = localObject2;
      if (paramContext != null) {
        localObject1 = paramContext.getDeviceId();
      }
      return localObject1;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public String getIMSI(Context paramContext)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramContext != null) {}
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      localObject1 = localObject2;
      if (paramContext != null) {
        localObject1 = paramContext.getSubscriberId();
      }
      return localObject1;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  /* Error */
  public String getKernelVersion()
  {
    // Byte code:
    //   0: ldc 91
    //   2: astore 4
    //   4: ldc 91
    //   6: astore_1
    //   7: new 402	java/io/FileInputStream
    //   10: dup
    //   11: ldc_w 404
    //   14: invokespecial 405	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   17: astore 5
    //   19: aconst_null
    //   20: astore_2
    //   21: new 63	java/io/BufferedReader
    //   24: dup
    //   25: new 300	java/io/InputStreamReader
    //   28: dup
    //   29: aload 5
    //   31: invokespecial 321	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   34: sipush 8192
    //   37: invokespecial 66	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   40: astore_3
    //   41: aload_3
    //   42: invokevirtual 69	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   45: astore_2
    //   46: aload_2
    //   47: ifnull +37 -> 84
    //   50: new 407	java/lang/StringBuilder
    //   53: dup
    //   54: invokespecial 408	java/lang/StringBuilder:<init>	()V
    //   57: astore 6
    //   59: aload 6
    //   61: aload_1
    //   62: invokevirtual 412	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: pop
    //   66: aload 6
    //   68: aload_2
    //   69: invokevirtual 412	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: pop
    //   73: aload 6
    //   75: invokevirtual 415	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   78: astore_2
    //   79: aload_2
    //   80: astore_1
    //   81: goto -40 -> 41
    //   84: aload_1
    //   85: astore_2
    //   86: aload_3
    //   87: invokevirtual 83	java/io/BufferedReader:close	()V
    //   90: aload_1
    //   91: astore_2
    //   92: aload 5
    //   94: invokevirtual 418	java/io/InputStream:close	()V
    //   97: goto +33 -> 130
    //   100: astore_1
    //   101: goto +9 -> 110
    //   104: goto +17 -> 121
    //   107: astore_1
    //   108: aconst_null
    //   109: astore_3
    //   110: aload_3
    //   111: invokevirtual 83	java/io/BufferedReader:close	()V
    //   114: aload 5
    //   116: invokevirtual 418	java/io/InputStream:close	()V
    //   119: aload_1
    //   120: athrow
    //   121: aload_1
    //   122: astore_2
    //   123: aload_3
    //   124: invokevirtual 83	java/io/BufferedReader:close	()V
    //   127: goto -37 -> 90
    //   130: aload 4
    //   132: astore_2
    //   133: aload_1
    //   134: invokestatic 421	com/alipay/security/mobile/module/commonutils/CommonUtils:isNotBlank	(Ljava/lang/String;)Z
    //   137: ifeq +31 -> 168
    //   140: aload_1
    //   141: aload_1
    //   142: ldc_w 423
    //   145: invokevirtual 333	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   148: bipush 8
    //   150: iadd
    //   151: invokevirtual 425	java/lang/String:substring	(I)Ljava/lang/String;
    //   154: astore_1
    //   155: aload_1
    //   156: iconst_0
    //   157: aload_1
    //   158: ldc_w 427
    //   161: invokevirtual 333	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   164: invokevirtual 340	java/lang/String:substring	(II)Ljava/lang/String;
    //   167: astore_2
    //   168: aload_2
    //   169: areturn
    //   170: astore_1
    //   171: ldc 91
    //   173: areturn
    //   174: astore_3
    //   175: aload_2
    //   176: astore_3
    //   177: goto -56 -> 121
    //   180: astore_2
    //   181: goto -77 -> 104
    //   184: astore_1
    //   185: aload_2
    //   186: astore_1
    //   187: goto -57 -> 130
    //   190: astore_2
    //   191: goto -72 -> 119
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	194	0	this	DeviceInfo
    //   6	85	1	localObject1	Object
    //   100	1	1	localObject2	Object
    //   107	35	1	str1	String
    //   154	4	1	str2	String
    //   170	1	1	localFileNotFoundException	java.io.FileNotFoundException
    //   184	1	1	localIOException1	java.io.IOException
    //   186	1	1	localObject3	Object
    //   20	156	2	localObject4	Object
    //   180	6	2	localIOException2	java.io.IOException
    //   190	1	2	localIOException3	java.io.IOException
    //   40	84	3	localBufferedReader	java.io.BufferedReader
    //   174	1	3	localIOException4	java.io.IOException
    //   176	1	3	localObject5	Object
    //   2	129	4	str3	String
    //   17	98	5	localFileInputStream	java.io.FileInputStream
    //   57	17	6	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   41	46	100	finally
    //   50	79	100	finally
    //   21	41	107	finally
    //   7	19	170	java/io/FileNotFoundException
    //   133	168	170	java/lang/Exception
    //   21	41	174	java/io/IOException
    //   41	46	180	java/io/IOException
    //   50	79	180	java/io/IOException
    //   86	90	184	java/io/IOException
    //   92	97	184	java/io/IOException
    //   123	127	184	java/io/IOException
    //   110	119	190	java/io/IOException
  }
  
  public String getMACAddress(Context paramContext)
  {
    try
    {
      paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  /* Error */
  public String getMemorySize()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 9
    //   3: lconst_0
    //   4: lstore 4
    //   6: new 56	java/io/FileReader
    //   9: dup
    //   10: ldc_w 444
    //   13: invokespecial 61	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   16: astore 10
    //   18: new 63	java/io/BufferedReader
    //   21: dup
    //   22: aload 10
    //   24: sipush 8192
    //   27: invokespecial 66	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   30: astore 8
    //   32: aload 8
    //   34: invokevirtual 69	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   37: astore 9
    //   39: lload 4
    //   41: lstore_2
    //   42: aload 9
    //   44: ifnull +20 -> 64
    //   47: aload 9
    //   49: ldc_w 446
    //   52: invokevirtual 192	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   55: iconst_1
    //   56: aaload
    //   57: invokestatic 449	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   60: istore_1
    //   61: iload_1
    //   62: i2l
    //   63: lstore_2
    //   64: aload 10
    //   66: invokevirtual 84	java/io/FileReader:close	()V
    //   69: lload_2
    //   70: lstore 6
    //   72: aload 8
    //   74: invokevirtual 83	java/io/BufferedReader:close	()V
    //   77: goto +88 -> 165
    //   80: astore 9
    //   82: goto +23 -> 105
    //   85: goto +50 -> 135
    //   88: astore 9
    //   90: aconst_null
    //   91: astore 8
    //   93: goto +12 -> 105
    //   96: astore 9
    //   98: aconst_null
    //   99: astore 10
    //   101: aload 10
    //   103: astore 8
    //   105: aload 10
    //   107: ifnull +8 -> 115
    //   110: aload 10
    //   112: invokevirtual 84	java/io/FileReader:close	()V
    //   115: aload 8
    //   117: ifnull +8 -> 125
    //   120: aload 8
    //   122: invokevirtual 83	java/io/BufferedReader:close	()V
    //   125: aload 9
    //   127: athrow
    //   128: aconst_null
    //   129: astore 10
    //   131: aload 9
    //   133: astore 8
    //   135: aload 10
    //   137: ifnull +8 -> 145
    //   140: aload 10
    //   142: invokevirtual 84	java/io/FileReader:close	()V
    //   145: lload 4
    //   147: lstore_2
    //   148: aload 8
    //   150: ifnull +15 -> 165
    //   153: lload 4
    //   155: lstore 6
    //   157: aload 8
    //   159: invokevirtual 83	java/io/BufferedReader:close	()V
    //   162: lload 4
    //   164: lstore_2
    //   165: lload_2
    //   166: invokestatic 452	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   169: areturn
    //   170: astore 8
    //   172: goto -44 -> 128
    //   175: astore 8
    //   177: aload 9
    //   179: astore 8
    //   181: goto -46 -> 135
    //   184: astore 9
    //   186: goto -101 -> 85
    //   189: astore 9
    //   191: goto -122 -> 69
    //   194: astore 8
    //   196: lload 6
    //   198: lstore_2
    //   199: goto -34 -> 165
    //   202: astore 10
    //   204: goto -89 -> 115
    //   207: astore 8
    //   209: goto -84 -> 125
    //   212: astore 9
    //   214: goto -69 -> 145
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	217	0	this	DeviceInfo
    //   60	2	1	i	int
    //   41	158	2	l1	long
    //   4	159	4	l2	long
    //   70	127	6	l3	long
    //   30	128	8	localObject1	Object
    //   170	1	8	localIOException1	java.io.IOException
    //   175	1	8	localIOException2	java.io.IOException
    //   179	1	8	localObject2	Object
    //   194	1	8	localIOException3	java.io.IOException
    //   207	1	8	localIOException4	java.io.IOException
    //   1	47	9	str	String
    //   80	1	9	localObject3	Object
    //   88	1	9	localObject4	Object
    //   96	82	9	localObject5	Object
    //   184	1	9	localIOException5	java.io.IOException
    //   189	1	9	localIOException6	java.io.IOException
    //   212	1	9	localIOException7	java.io.IOException
    //   16	125	10	localFileReader	java.io.FileReader
    //   202	1	10	localIOException8	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   32	39	80	finally
    //   47	61	80	finally
    //   18	32	88	finally
    //   6	18	96	finally
    //   6	18	170	java/io/IOException
    //   18	32	175	java/io/IOException
    //   32	39	184	java/io/IOException
    //   47	61	184	java/io/IOException
    //   64	69	189	java/io/IOException
    //   72	77	194	java/io/IOException
    //   157	162	194	java/io/IOException
    //   110	115	202	java/io/IOException
    //   120	125	207	java/io/IOException
    //   140	145	212	java/io/IOException
  }
  
  public String getNetworkType(Context paramContext)
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
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public String getOperatorName(Context paramContext)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramContext != null) {}
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      localObject1 = localObject2;
      if (paramContext != null) {
        localObject1 = paramContext.getSimOperatorName();
      }
      return localObject1;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public String getOperatorType(Context paramContext)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramContext != null) {}
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      localObject1 = localObject2;
      if (paramContext != null) {
        localObject1 = paramContext.getSimOperator();
      }
      return localObject1;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public String getPhoneNumber(Context paramContext)
  {
    String str2 = "";
    String str1 = str2;
    if (paramContext != null) {}
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      str1 = str2;
      if (paramContext != null) {
        str1 = paramContext.getLine1Number();
      }
      return str1;
    }
    catch (Exception paramContext) {}
    return "";
  }
  
  public String getPropPreviewsSizeOfCamera(Context paramContext)
  {
    a(paramContext.getApplicationContext());
    StringBuilder localStringBuilder = new StringBuilder();
    int j = -1;
    int i = j;
    try
    {
      if (Integer.parseInt(Build.VERSION.SDK) > 8) {
        i = Camera.getNumberOfCameras();
      }
      j = 0;
      while (j < i)
      {
        Locale localLocale;
        String str;
        Object localObject;
        if (j == 0)
        {
          localLocale = Locale.ENGLISH;
          str = "%1$d:%2$s";
          localObject = new Object[2];
          localObject[0] = Integer.valueOf(j);
          localObject[1] = a(paramContext, j);
        }
        for (;;)
        {
          localObject = String.format(localLocale, str, (Object[])localObject);
          break;
          localLocale = Locale.ENGLISH;
          str = "#%1$d:%2$s";
          localObject = new Object[2];
          localObject[0] = Integer.valueOf(j);
          localObject[1] = a(paramContext, j);
        }
        localStringBuilder.append((String)localObject);
        j += 1;
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
  
  public String getSDCardSize()
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
    catch (Exception localException)
    {
      for (;;)
      {
        l1 = l2;
      }
    }
    return String.valueOf(l1);
  }
  
  public String getSIMSerial(Context paramContext)
  {
    try
    {
      paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getSimSerialNumber();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return "";
  }
  
  public String getScreenDpi(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getResources().getDisplayMetrics();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramContext.densityDpi);
      paramContext = localStringBuilder.toString();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public String getScreenHeight(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getResources().getDisplayMetrics();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramContext.heightPixels);
      paramContext = localStringBuilder.toString();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public String getScreenResolution(Context paramContext)
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
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public String getScreenWidth(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getResources().getDisplayMetrics();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramContext.widthPixels);
      paramContext = localStringBuilder.toString();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      for (;;) {}
    }
    return null;
  }
  
  public String getSensorDigest(Context paramContext)
  {
    Sensor localSensor = null;
    Object localObject = localSensor;
    if (paramContext != null) {}
    try
    {
      paramContext = (SensorManager)paramContext.getSystemService("sensor");
      localObject = localSensor;
      if (paramContext != null)
      {
        paramContext = paramContext.getSensorList(-1);
        localObject = localSensor;
        if (paramContext != null)
        {
          localObject = localSensor;
          if (paramContext.size() > 0)
          {
            localObject = new StringBuilder();
            paramContext = paramContext.iterator();
            while (paramContext.hasNext())
            {
              localSensor = (Sensor)paramContext.next();
              ((StringBuilder)localObject).append(localSensor.getName());
              ((StringBuilder)localObject).append(localSensor.getVersion());
              ((StringBuilder)localObject).append(localSensor.getVendor());
            }
            localObject = CommonUtils.sha1ByString(((StringBuilder)localObject).toString());
          }
        }
      }
      return localObject;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public String getSerialNumber()
  {
    try
    {
      String str = Build.SERIAL;
      return str;
    }
    catch (Exception localException) {}
    return "";
  }
  
  public String getTotalInternalMemorySize()
  {
    try
    {
      StatFs localStatFs = new StatFs(Environment.getDataDirectory().getPath());
      l = localStatFs.getBlockSize();
      int i = localStatFs.getBlockCount();
      l = i * l;
    }
    catch (Exception localException)
    {
      long l;
      for (;;) {}
    }
    l = 0L;
    return String.valueOf(l);
  }
  
  public String getVoiceMailNumber(Context paramContext)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramContext != null) {}
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      localObject1 = localObject2;
      if (paramContext != null) {
        localObject1 = paramContext.getVoiceMailNumber();
      }
      return localObject1;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public String getVoiceMailTag(Context paramContext)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramContext != null) {}
    try
    {
      paramContext = (TelephonyManager)paramContext.getSystemService("phone");
      localObject1 = localObject2;
      if (paramContext != null) {
        localObject1 = paramContext.getVoiceMailAlphaTag();
      }
      return localObject1;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public String getWifiBssid(Context paramContext)
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
    catch (Throwable paramContext)
    {
      for (;;) {}
    }
    return "";
  }
  
  private class CameraSizeComparator
    implements Comparator<Camera.Size>
  {
    private CameraSizeComparator() {}
    
    public int compare(Camera.Size paramSize1, Camera.Size paramSize2)
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
