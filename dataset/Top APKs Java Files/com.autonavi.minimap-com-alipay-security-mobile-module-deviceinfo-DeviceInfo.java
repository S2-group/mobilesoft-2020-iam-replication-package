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
    //   0: new 58	java/io/FileReader
    //   3: dup
    //   4: ldc 60
    //   6: invokespecial 63	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   9: astore_2
    //   10: new 65	java/io/BufferedReader
    //   13: dup
    //   14: aload_2
    //   15: sipush 8192
    //   18: invokespecial 68	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   21: astore_1
    //   22: aload_1
    //   23: invokevirtual 71	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   26: astore_0
    //   27: aload_0
    //   28: invokestatic 77	com/alipay/security/mobile/module/commonutils/CommonUtils:isBlank	(Ljava/lang/String;)Z
    //   31: ifne +18 -> 49
    //   34: aload_0
    //   35: invokevirtual 82	java/lang/String:trim	()Ljava/lang/String;
    //   38: astore_0
    //   39: aload_1
    //   40: invokevirtual 85	java/io/BufferedReader:close	()V
    //   43: aload_2
    //   44: invokevirtual 86	java/io/FileReader:close	()V
    //   47: aload_0
    //   48: areturn
    //   49: aload_1
    //   50: invokevirtual 85	java/io/BufferedReader:close	()V
    //   53: aload_2
    //   54: invokevirtual 86	java/io/FileReader:close	()V
    //   57: aconst_null
    //   58: areturn
    //   59: astore_0
    //   60: aconst_null
    //   61: areturn
    //   62: astore_0
    //   63: aconst_null
    //   64: astore_0
    //   65: aconst_null
    //   66: astore_2
    //   67: aload_0
    //   68: invokevirtual 85	java/io/BufferedReader:close	()V
    //   71: aload_2
    //   72: invokevirtual 86	java/io/FileReader:close	()V
    //   75: aconst_null
    //   76: areturn
    //   77: astore_0
    //   78: aconst_null
    //   79: areturn
    //   80: astore_0
    //   81: aconst_null
    //   82: astore_2
    //   83: aconst_null
    //   84: astore_1
    //   85: aload_1
    //   86: invokevirtual 85	java/io/BufferedReader:close	()V
    //   89: aload_2
    //   90: invokevirtual 86	java/io/FileReader:close	()V
    //   93: aload_0
    //   94: athrow
    //   95: astore_1
    //   96: goto -53 -> 43
    //   99: astore_1
    //   100: aload_0
    //   101: areturn
    //   102: astore_0
    //   103: goto -50 -> 53
    //   106: astore_0
    //   107: goto -36 -> 71
    //   110: astore_1
    //   111: goto -22 -> 89
    //   114: astore_1
    //   115: goto -22 -> 93
    //   118: astore_0
    //   119: aconst_null
    //   120: astore_1
    //   121: goto -36 -> 85
    //   124: astore_0
    //   125: goto -40 -> 85
    //   128: astore_0
    //   129: aconst_null
    //   130: astore_0
    //   131: goto -64 -> 67
    //   134: astore_0
    //   135: aload_1
    //   136: astore_0
    //   137: goto -70 -> 67
    // Local variable table:
    //   start	length	slot	name	signature
    //   26	22	0	str1	String
    //   59	1	0	localIOException1	java.io.IOException
    //   62	1	0	localIOException2	java.io.IOException
    //   64	4	0	localObject1	Object
    //   77	1	0	localIOException3	java.io.IOException
    //   80	21	0	str2	String
    //   102	1	0	localIOException4	java.io.IOException
    //   106	1	0	localIOException5	java.io.IOException
    //   118	1	0	localObject2	Object
    //   124	1	0	localObject3	Object
    //   128	1	0	localIOException6	java.io.IOException
    //   130	1	0	localObject4	Object
    //   134	1	0	localIOException7	java.io.IOException
    //   136	1	0	localObject5	Object
    //   21	65	1	localBufferedReader	java.io.BufferedReader
    //   95	1	1	localIOException8	java.io.IOException
    //   99	1	1	localIOException9	java.io.IOException
    //   110	1	1	localIOException10	java.io.IOException
    //   114	1	1	localIOException11	java.io.IOException
    //   120	16	1	localObject6	Object
    //   9	81	2	localFileReader	java.io.FileReader
    // Exception table:
    //   from	to	target	type
    //   53	57	59	java/io/IOException
    //   0	10	62	java/io/IOException
    //   71	75	77	java/io/IOException
    //   0	10	80	finally
    //   39	43	95	java/io/IOException
    //   43	47	99	java/io/IOException
    //   49	53	102	java/io/IOException
    //   67	71	106	java/io/IOException
    //   85	89	110	java/io/IOException
    //   89	93	114	java/io/IOException
    //   10	22	118	finally
    //   22	39	124	finally
    //   10	22	128	java/io/IOException
    //   22	39	134	java/io/IOException
  }
  
  /* Error */
  private String a(Context paramContext, int paramInt)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 95	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   4: invokestatic 97	com/alipay/security/mobile/module/deviceinfo/DeviceInfo:a	(Landroid/content/Context;)F
    //   7: fstore_3
    //   8: aconst_null
    //   9: astore_1
    //   10: iload_2
    //   11: invokestatic 103	android/hardware/Camera:open	(I)Landroid/hardware/Camera;
    //   14: astore 6
    //   16: aload 6
    //   18: astore_1
    //   19: aload_1
    //   20: invokevirtual 107	android/hardware/Camera:getParameters	()Landroid/hardware/Camera$Parameters;
    //   23: invokevirtual 113	android/hardware/Camera$Parameters:getSupportedPreviewSizes	()Ljava/util/List;
    //   26: astore 6
    //   28: aload 6
    //   30: new 8	com/alipay/security/mobile/module/deviceinfo/DeviceInfo$CameraSizeComparator
    //   33: dup
    //   34: aload_0
    //   35: iconst_0
    //   36: invokespecial 116	com/alipay/security/mobile/module/deviceinfo/DeviceInfo$CameraSizeComparator:<init>	(Lcom/alipay/security/mobile/module/deviceinfo/DeviceInfo;B)V
    //   39: invokestatic 122	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
    //   42: iconst_0
    //   43: istore 4
    //   45: aload 6
    //   47: invokeinterface 128 1 0
    //   52: astore 7
    //   54: aload 7
    //   56: invokeinterface 134 1 0
    //   61: ifeq +58 -> 119
    //   64: aload 7
    //   66: invokeinterface 138 1 0
    //   71: checkcast 140	android/hardware/Camera$Size
    //   74: astore 8
    //   76: aload 8
    //   78: getfield 143	android/hardware/Camera$Size:width	I
    //   81: sipush 600
    //   84: if_icmplt +214 -> 298
    //   87: aload 8
    //   89: getfield 143	android/hardware/Camera$Size:width	I
    //   92: i2f
    //   93: aload 8
    //   95: getfield 146	android/hardware/Camera$Size:height	I
    //   98: i2f
    //   99: fdiv
    //   100: fload_3
    //   101: fsub
    //   102: invokestatic 152	java/lang/Math:abs	(F)F
    //   105: f2d
    //   106: ldc2_w 153
    //   109: dcmpg
    //   110: ifgt +197 -> 307
    //   113: iconst_1
    //   114: istore 5
    //   116: goto +177 -> 293
    //   119: iload 4
    //   121: aload 6
    //   123: invokeinterface 158 1 0
    //   128: if_icmpne +162 -> 290
    //   131: aload 6
    //   133: invokeinterface 158 1 0
    //   138: iconst_1
    //   139: isub
    //   140: istore 4
    //   142: aload 6
    //   144: iload 4
    //   146: invokeinterface 162 2 0
    //   151: checkcast 140	android/hardware/Camera$Size
    //   154: astore 6
    //   156: aload 6
    //   158: ifnull +125 -> 283
    //   161: getstatic 168	java/util/Locale:ENGLISH	Ljava/util/Locale;
    //   164: ldc -86
    //   166: iconst_3
    //   167: anewarray 4	java/lang/Object
    //   170: dup
    //   171: iconst_0
    //   172: iload_2
    //   173: invokestatic 176	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   176: aastore
    //   177: dup
    //   178: iconst_1
    //   179: aload 6
    //   181: getfield 143	android/hardware/Camera$Size:width	I
    //   184: invokestatic 176	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   187: aastore
    //   188: dup
    //   189: iconst_2
    //   190: aload 6
    //   192: getfield 146	android/hardware/Camera$Size:height	I
    //   195: invokestatic 176	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   198: aastore
    //   199: invokestatic 180	java/lang/String:format	(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   202: astore 6
    //   204: aload_1
    //   205: ifnull +7 -> 212
    //   208: aload_1
    //   209: invokevirtual 183	android/hardware/Camera:release	()V
    //   212: aload 6
    //   214: areturn
    //   215: astore 6
    //   217: aload_1
    //   218: ifnull +62 -> 280
    //   221: aload_1
    //   222: invokevirtual 183	android/hardware/Camera:release	()V
    //   225: ldc -71
    //   227: areturn
    //   228: astore_1
    //   229: aconst_null
    //   230: astore_1
    //   231: aload_1
    //   232: ifnull +48 -> 280
    //   235: aload_1
    //   236: invokevirtual 183	android/hardware/Camera:release	()V
    //   239: ldc -71
    //   241: areturn
    //   242: astore_1
    //   243: aconst_null
    //   244: astore 7
    //   246: aload_1
    //   247: astore 6
    //   249: aload 7
    //   251: ifnull +8 -> 259
    //   254: aload 7
    //   256: invokevirtual 183	android/hardware/Camera:release	()V
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
    //   280: ldc -71
    //   282: areturn
    //   283: ldc -71
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
    //   0	313	0	this	DeviceInfo
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
  private static String b()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore 4
    //   5: new 58	java/io/FileReader
    //   8: dup
    //   9: ldc -68
    //   11: invokespecial 63	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   14: astore_2
    //   15: new 65	java/io/BufferedReader
    //   18: dup
    //   19: aload_2
    //   20: sipush 8192
    //   23: invokespecial 68	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   26: astore_1
    //   27: aload_1
    //   28: invokevirtual 71	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   31: astore 5
    //   33: aload 4
    //   35: astore_0
    //   36: aload 5
    //   38: ifnull +47 -> 85
    //   41: aload 5
    //   43: invokestatic 77	com/alipay/security/mobile/module/commonutils/CommonUtils:isBlank	(Ljava/lang/String;)Z
    //   46: ifne -19 -> 27
    //   49: aload 5
    //   51: ldc -66
    //   53: invokevirtual 194	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   56: astore_0
    //   57: aload_0
    //   58: ifnull -31 -> 27
    //   61: aload_0
    //   62: arraylength
    //   63: iconst_1
    //   64: if_icmple -37 -> 27
    //   67: aload_0
    //   68: iconst_0
    //   69: aaload
    //   70: ldc -60
    //   72: invokevirtual 200	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   75: ifeq -48 -> 27
    //   78: aload_0
    //   79: iconst_1
    //   80: aaload
    //   81: invokevirtual 82	java/lang/String:trim	()Ljava/lang/String;
    //   84: astore_0
    //   85: aload_2
    //   86: invokevirtual 86	java/io/FileReader:close	()V
    //   89: aload_1
    //   90: invokevirtual 85	java/io/BufferedReader:close	()V
    //   93: aload_0
    //   94: astore_1
    //   95: aload_1
    //   96: areturn
    //   97: astore_0
    //   98: aconst_null
    //   99: astore_0
    //   100: aconst_null
    //   101: astore_2
    //   102: aload_2
    //   103: ifnull +7 -> 110
    //   106: aload_2
    //   107: invokevirtual 86	java/io/FileReader:close	()V
    //   110: aload_3
    //   111: astore_1
    //   112: aload_0
    //   113: ifnull -18 -> 95
    //   116: aload_0
    //   117: invokevirtual 85	java/io/BufferedReader:close	()V
    //   120: aconst_null
    //   121: areturn
    //   122: astore_0
    //   123: aconst_null
    //   124: areturn
    //   125: astore_0
    //   126: aconst_null
    //   127: astore_2
    //   128: aconst_null
    //   129: astore_1
    //   130: aload_2
    //   131: ifnull +7 -> 138
    //   134: aload_2
    //   135: invokevirtual 86	java/io/FileReader:close	()V
    //   138: aload_1
    //   139: ifnull +7 -> 146
    //   142: aload_1
    //   143: invokevirtual 85	java/io/BufferedReader:close	()V
    //   146: aload_0
    //   147: athrow
    //   148: astore_2
    //   149: goto -60 -> 89
    //   152: astore_1
    //   153: aload_0
    //   154: areturn
    //   155: astore_1
    //   156: goto -46 -> 110
    //   159: astore_2
    //   160: goto -22 -> 138
    //   163: astore_1
    //   164: goto -18 -> 146
    //   167: astore_0
    //   168: aconst_null
    //   169: astore_1
    //   170: goto -40 -> 130
    //   173: astore_0
    //   174: goto -44 -> 130
    //   177: astore_0
    //   178: aconst_null
    //   179: astore_0
    //   180: goto -78 -> 102
    //   183: astore_0
    //   184: aload_1
    //   185: astore_0
    //   186: goto -84 -> 102
    // Local variable table:
    //   start	length	slot	name	signature
    //   35	59	0	localObject1	Object
    //   97	1	0	localIOException1	java.io.IOException
    //   99	18	0	localObject2	Object
    //   122	1	0	localIOException2	java.io.IOException
    //   125	29	0	str1	String
    //   167	1	0	localObject3	Object
    //   173	1	0	localObject4	Object
    //   177	1	0	localIOException3	java.io.IOException
    //   179	1	0	localObject5	Object
    //   183	1	0	localIOException4	java.io.IOException
    //   185	1	0	localObject6	Object
    //   26	117	1	localObject7	Object
    //   152	1	1	localIOException5	java.io.IOException
    //   155	1	1	localIOException6	java.io.IOException
    //   163	1	1	localIOException7	java.io.IOException
    //   169	16	1	localObject8	Object
    //   14	121	2	localFileReader	java.io.FileReader
    //   148	1	2	localIOException8	java.io.IOException
    //   159	1	2	localIOException9	java.io.IOException
    //   1	110	3	localObject9	Object
    //   3	31	4	localObject10	Object
    //   31	19	5	str2	String
    // Exception table:
    //   from	to	target	type
    //   5	15	97	java/io/IOException
    //   116	120	122	java/io/IOException
    //   5	15	125	finally
    //   85	89	148	java/io/IOException
    //   89	93	152	java/io/IOException
    //   106	110	155	java/io/IOException
    //   134	138	159	java/io/IOException
    //   142	146	163	java/io/IOException
    //   15	27	167	finally
    //   27	33	173	finally
    //   41	57	173	finally
    //   61	85	173	finally
    //   15	27	177	java/io/IOException
    //   27	33	183	java/io/IOException
    //   41	57	183	java/io/IOException
    //   61	85	183	java/io/IOException
  }
  
  public static DeviceInfo getInstance()
  {
    return a;
  }
  
  public Map<String, Integer> getAllAppName(Context paramContext)
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
  
  public String getAndroidID(Context paramContext)
  {
    try
    {
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      return paramContext;
    }
    catch (Exception paramContext) {}
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
    catch (Exception localException) {}
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
    catch (Exception localException) {}
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
    catch (Exception localException) {}
    return false;
  }
  
  /* Error */
  public String getCPUHardware()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: ldc_w 300
    //   5: astore 4
    //   7: new 302	java/io/InputStreamReader
    //   10: dup
    //   11: invokestatic 308	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   14: ldc_w 310
    //   17: invokevirtual 314	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   20: invokevirtual 320	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   23: invokespecial 323	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   26: astore_2
    //   27: new 325	java/io/LineNumberReader
    //   30: dup
    //   31: aload_2
    //   32: invokespecial 328	java/io/LineNumberReader:<init>	(Ljava/io/Reader;)V
    //   35: astore 5
    //   37: iconst_1
    //   38: istore_1
    //   39: aload 4
    //   41: astore_3
    //   42: iload_1
    //   43: bipush 100
    //   45: if_icmpge +52 -> 97
    //   48: aload 5
    //   50: invokevirtual 329	java/io/LineNumberReader:readLine	()Ljava/lang/String;
    //   53: astore 6
    //   55: aload 4
    //   57: astore_3
    //   58: aload 6
    //   60: ifnull +37 -> 97
    //   63: aload 6
    //   65: ldc_w 331
    //   68: invokevirtual 335	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   71: iflt +37 -> 108
    //   74: aload 6
    //   76: aload 6
    //   78: ldc -66
    //   80: invokevirtual 335	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   83: iconst_1
    //   84: iadd
    //   85: aload 6
    //   87: invokevirtual 338	java/lang/String:length	()I
    //   90: invokevirtual 342	java/lang/String:substring	(II)Ljava/lang/String;
    //   93: invokevirtual 82	java/lang/String:trim	()Ljava/lang/String;
    //   96: astore_3
    //   97: aload 5
    //   99: invokevirtual 343	java/io/LineNumberReader:close	()V
    //   102: aload_2
    //   103: invokevirtual 344	java/io/InputStreamReader:close	()V
    //   106: aload_3
    //   107: areturn
    //   108: iload_1
    //   109: iconst_1
    //   110: iadd
    //   111: istore_1
    //   112: goto -73 -> 39
    //   115: astore_2
    //   116: aconst_null
    //   117: astore 5
    //   119: aload_3
    //   120: astore_2
    //   121: aload 5
    //   123: astore_3
    //   124: aload_3
    //   125: ifnull +7 -> 132
    //   128: aload_3
    //   129: invokevirtual 343	java/io/LineNumberReader:close	()V
    //   132: aload 4
    //   134: astore_3
    //   135: aload_2
    //   136: ifnull -30 -> 106
    //   139: aload_2
    //   140: invokevirtual 344	java/io/InputStreamReader:close	()V
    //   143: ldc_w 300
    //   146: areturn
    //   147: astore_2
    //   148: ldc_w 300
    //   151: areturn
    //   152: astore_3
    //   153: aconst_null
    //   154: astore 4
    //   156: aconst_null
    //   157: astore_2
    //   158: aload 4
    //   160: ifnull +8 -> 168
    //   163: aload 4
    //   165: invokevirtual 343	java/io/LineNumberReader:close	()V
    //   168: aload_2
    //   169: ifnull +7 -> 176
    //   172: aload_2
    //   173: invokevirtual 344	java/io/InputStreamReader:close	()V
    //   176: aload_3
    //   177: athrow
    //   178: astore 4
    //   180: goto -78 -> 102
    //   183: astore_2
    //   184: aload_3
    //   185: areturn
    //   186: astore_3
    //   187: goto -55 -> 132
    //   190: astore 4
    //   192: goto -24 -> 168
    //   195: astore_2
    //   196: goto -20 -> 176
    //   199: astore_3
    //   200: aconst_null
    //   201: astore 4
    //   203: goto -45 -> 158
    //   206: astore_3
    //   207: aload 5
    //   209: astore 4
    //   211: goto -53 -> 158
    //   214: astore_3
    //   215: aconst_null
    //   216: astore_3
    //   217: goto -93 -> 124
    //   220: astore_3
    //   221: aload 5
    //   223: astore_3
    //   224: goto -100 -> 124
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	227	0	this	DeviceInfo
    //   38	74	1	i	int
    //   26	77	2	localInputStreamReader	java.io.InputStreamReader
    //   115	1	2	localException1	Exception
    //   120	20	2	localObject1	Object
    //   147	1	2	localIOException1	java.io.IOException
    //   157	16	2	localObject2	Object
    //   183	1	2	localIOException2	java.io.IOException
    //   195	1	2	localIOException3	java.io.IOException
    //   1	134	3	localObject3	Object
    //   152	33	3	str1	String
    //   186	1	3	localIOException4	java.io.IOException
    //   199	1	3	localObject4	Object
    //   206	1	3	localObject5	Object
    //   214	1	3	localException2	Exception
    //   216	1	3	localObject6	Object
    //   220	1	3	localException3	Exception
    //   223	1	3	localObject7	Object
    //   5	159	4	str2	String
    //   178	1	4	localIOException5	java.io.IOException
    //   190	1	4	localIOException6	java.io.IOException
    //   201	9	4	localObject8	Object
    //   35	187	5	localLineNumberReader	java.io.LineNumberReader
    //   53	33	6	str3	String
    // Exception table:
    //   from	to	target	type
    //   7	27	115	java/lang/Exception
    //   139	143	147	java/io/IOException
    //   7	27	152	finally
    //   97	102	178	java/io/IOException
    //   102	106	183	java/io/IOException
    //   128	132	186	java/io/IOException
    //   163	168	190	java/io/IOException
    //   172	176	195	java/io/IOException
    //   27	37	199	finally
    //   48	55	206	finally
    //   63	97	206	finally
    //   27	37	214	java/lang/Exception
    //   48	55	220	java/lang/Exception
    //   63	97	220	java/lang/Exception
  }
  
  /* Error */
  public String getCPUSerial()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: ldc_w 347
    //   5: astore 4
    //   7: new 302	java/io/InputStreamReader
    //   10: dup
    //   11: invokestatic 308	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   14: ldc_w 349
    //   17: invokevirtual 314	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   20: invokevirtual 320	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   23: invokespecial 323	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   26: astore_2
    //   27: new 325	java/io/LineNumberReader
    //   30: dup
    //   31: aload_2
    //   32: invokespecial 328	java/io/LineNumberReader:<init>	(Ljava/io/Reader;)V
    //   35: astore 5
    //   37: iconst_1
    //   38: istore_1
    //   39: aload 4
    //   41: astore_3
    //   42: iload_1
    //   43: bipush 100
    //   45: if_icmpge +52 -> 97
    //   48: aload 5
    //   50: invokevirtual 329	java/io/LineNumberReader:readLine	()Ljava/lang/String;
    //   53: astore 6
    //   55: aload 4
    //   57: astore_3
    //   58: aload 6
    //   60: ifnull +37 -> 97
    //   63: aload 6
    //   65: ldc_w 351
    //   68: invokevirtual 335	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   71: iflt +37 -> 108
    //   74: aload 6
    //   76: aload 6
    //   78: ldc -66
    //   80: invokevirtual 335	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   83: iconst_1
    //   84: iadd
    //   85: aload 6
    //   87: invokevirtual 338	java/lang/String:length	()I
    //   90: invokevirtual 342	java/lang/String:substring	(II)Ljava/lang/String;
    //   93: invokevirtual 82	java/lang/String:trim	()Ljava/lang/String;
    //   96: astore_3
    //   97: aload 5
    //   99: invokevirtual 343	java/io/LineNumberReader:close	()V
    //   102: aload_2
    //   103: invokevirtual 344	java/io/InputStreamReader:close	()V
    //   106: aload_3
    //   107: areturn
    //   108: iload_1
    //   109: iconst_1
    //   110: iadd
    //   111: istore_1
    //   112: goto -73 -> 39
    //   115: astore_2
    //   116: aconst_null
    //   117: astore 5
    //   119: aload_3
    //   120: astore_2
    //   121: aload 5
    //   123: astore_3
    //   124: aload_3
    //   125: ifnull +7 -> 132
    //   128: aload_3
    //   129: invokevirtual 343	java/io/LineNumberReader:close	()V
    //   132: aload 4
    //   134: astore_3
    //   135: aload_2
    //   136: ifnull -30 -> 106
    //   139: aload_2
    //   140: invokevirtual 344	java/io/InputStreamReader:close	()V
    //   143: ldc_w 347
    //   146: areturn
    //   147: astore_2
    //   148: ldc_w 347
    //   151: areturn
    //   152: astore_3
    //   153: aconst_null
    //   154: astore 4
    //   156: aconst_null
    //   157: astore_2
    //   158: aload 4
    //   160: ifnull +8 -> 168
    //   163: aload 4
    //   165: invokevirtual 343	java/io/LineNumberReader:close	()V
    //   168: aload_2
    //   169: ifnull +7 -> 176
    //   172: aload_2
    //   173: invokevirtual 344	java/io/InputStreamReader:close	()V
    //   176: aload_3
    //   177: athrow
    //   178: astore 4
    //   180: goto -78 -> 102
    //   183: astore_2
    //   184: aload_3
    //   185: areturn
    //   186: astore_3
    //   187: goto -55 -> 132
    //   190: astore 4
    //   192: goto -24 -> 168
    //   195: astore_2
    //   196: goto -20 -> 176
    //   199: astore_3
    //   200: aconst_null
    //   201: astore 4
    //   203: goto -45 -> 158
    //   206: astore_3
    //   207: aload 5
    //   209: astore 4
    //   211: goto -53 -> 158
    //   214: astore_3
    //   215: aconst_null
    //   216: astore_3
    //   217: goto -93 -> 124
    //   220: astore_3
    //   221: aload 5
    //   223: astore_3
    //   224: goto -100 -> 124
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	227	0	this	DeviceInfo
    //   38	74	1	i	int
    //   26	77	2	localInputStreamReader	java.io.InputStreamReader
    //   115	1	2	localException1	Exception
    //   120	20	2	localObject1	Object
    //   147	1	2	localIOException1	java.io.IOException
    //   157	16	2	localObject2	Object
    //   183	1	2	localIOException2	java.io.IOException
    //   195	1	2	localIOException3	java.io.IOException
    //   1	134	3	localObject3	Object
    //   152	33	3	str1	String
    //   186	1	3	localIOException4	java.io.IOException
    //   199	1	3	localObject4	Object
    //   206	1	3	localObject5	Object
    //   214	1	3	localException2	Exception
    //   216	1	3	localObject6	Object
    //   220	1	3	localException3	Exception
    //   223	1	3	localObject7	Object
    //   5	159	4	str2	String
    //   178	1	4	localIOException5	java.io.IOException
    //   190	1	4	localIOException6	java.io.IOException
    //   201	9	4	localObject8	Object
    //   35	187	5	localLineNumberReader	java.io.LineNumberReader
    //   53	33	6	str3	String
    // Exception table:
    //   from	to	target	type
    //   7	27	115	java/lang/Exception
    //   139	143	147	java/io/IOException
    //   7	27	152	finally
    //   97	102	178	java/io/IOException
    //   102	106	183	java/io/IOException
    //   128	132	186	java/io/IOException
    //   163	168	190	java/io/IOException
    //   172	176	195	java/io/IOException
    //   27	37	199	finally
    //   48	55	206	finally
    //   63	97	206	finally
    //   27	37	214	java/lang/Exception
    //   48	55	220	java/lang/Exception
    //   63	97	220	java/lang/Exception
  }
  
  public String getCpuCount()
  {
    try
    {
      int i = new File("/sys/devices/system/cpu/").listFiles(new DeviceInfo.1CpuFilter(this)).length;
      return String.valueOf(i);
    }
    catch (Exception localException) {}
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
    //   0: aconst_null
    //   1: astore 4
    //   3: new 58	java/io/FileReader
    //   6: dup
    //   7: ldc -68
    //   9: invokespecial 63	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   12: astore_3
    //   13: new 65	java/io/BufferedReader
    //   16: dup
    //   17: aload_3
    //   18: invokespecial 378	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   21: astore_2
    //   22: aload_2
    //   23: invokevirtual 71	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   26: ldc_w 380
    //   29: iconst_2
    //   30: invokevirtual 383	java/lang/String:split	(Ljava/lang/String;I)[Ljava/lang/String;
    //   33: astore_1
    //   34: aload_1
    //   35: ifnull +25 -> 60
    //   38: aload_1
    //   39: arraylength
    //   40: iconst_1
    //   41: if_icmple +19 -> 60
    //   44: aload_1
    //   45: iconst_1
    //   46: aaload
    //   47: astore_1
    //   48: aload_3
    //   49: invokevirtual 86	java/io/FileReader:close	()V
    //   52: aload_2
    //   53: invokevirtual 85	java/io/BufferedReader:close	()V
    //   56: aload_1
    //   57: astore_2
    //   58: aload_2
    //   59: areturn
    //   60: aload_3
    //   61: invokevirtual 86	java/io/FileReader:close	()V
    //   64: aload_2
    //   65: invokevirtual 85	java/io/BufferedReader:close	()V
    //   68: aconst_null
    //   69: areturn
    //   70: astore_1
    //   71: aconst_null
    //   72: areturn
    //   73: astore_1
    //   74: aconst_null
    //   75: astore_1
    //   76: aconst_null
    //   77: astore_3
    //   78: aload_3
    //   79: ifnull +7 -> 86
    //   82: aload_3
    //   83: invokevirtual 86	java/io/FileReader:close	()V
    //   86: aload 4
    //   88: astore_2
    //   89: aload_1
    //   90: ifnull -32 -> 58
    //   93: aload_1
    //   94: invokevirtual 85	java/io/BufferedReader:close	()V
    //   97: aconst_null
    //   98: areturn
    //   99: astore_1
    //   100: aconst_null
    //   101: areturn
    //   102: astore_1
    //   103: aconst_null
    //   104: astore_3
    //   105: aconst_null
    //   106: astore_2
    //   107: aload_3
    //   108: ifnull +7 -> 115
    //   111: aload_3
    //   112: invokevirtual 86	java/io/FileReader:close	()V
    //   115: aload_2
    //   116: ifnull +7 -> 123
    //   119: aload_2
    //   120: invokevirtual 85	java/io/BufferedReader:close	()V
    //   123: aload_1
    //   124: athrow
    //   125: astore_3
    //   126: goto -74 -> 52
    //   129: astore_2
    //   130: aload_1
    //   131: areturn
    //   132: astore_1
    //   133: goto -69 -> 64
    //   136: astore_2
    //   137: goto -51 -> 86
    //   140: astore_3
    //   141: goto -26 -> 115
    //   144: astore_2
    //   145: goto -22 -> 123
    //   148: astore_1
    //   149: aconst_null
    //   150: astore_2
    //   151: goto -44 -> 107
    //   154: astore_1
    //   155: goto -48 -> 107
    //   158: astore_1
    //   159: aconst_null
    //   160: astore_1
    //   161: goto -83 -> 78
    //   164: astore_1
    //   165: aload_2
    //   166: astore_1
    //   167: goto -89 -> 78
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	170	0	this	DeviceInfo
    //   33	24	1	localObject1	Object
    //   70	1	1	localIOException1	java.io.IOException
    //   73	1	1	localIOException2	java.io.IOException
    //   75	19	1	localObject2	Object
    //   99	1	1	localIOException3	java.io.IOException
    //   102	29	1	str	String
    //   132	1	1	localIOException4	java.io.IOException
    //   148	1	1	localObject3	Object
    //   154	1	1	localObject4	Object
    //   158	1	1	localIOException5	java.io.IOException
    //   160	1	1	localObject5	Object
    //   164	1	1	localIOException6	java.io.IOException
    //   166	1	1	localObject6	Object
    //   21	99	2	localObject7	Object
    //   129	1	2	localIOException7	java.io.IOException
    //   136	1	2	localIOException8	java.io.IOException
    //   144	1	2	localIOException9	java.io.IOException
    //   150	16	2	localObject8	Object
    //   12	100	3	localFileReader	java.io.FileReader
    //   125	1	3	localIOException10	java.io.IOException
    //   140	1	3	localIOException11	java.io.IOException
    //   1	86	4	localObject9	Object
    // Exception table:
    //   from	to	target	type
    //   64	68	70	java/io/IOException
    //   3	13	73	java/io/IOException
    //   93	97	99	java/io/IOException
    //   3	13	102	finally
    //   48	52	125	java/io/IOException
    //   52	56	129	java/io/IOException
    //   60	64	132	java/io/IOException
    //   82	86	136	java/io/IOException
    //   111	115	140	java/io/IOException
    //   119	123	144	java/io/IOException
    //   13	22	148	finally
    //   22	34	154	finally
    //   38	44	154	finally
    //   13	22	158	java/io/IOException
    //   22	34	164	java/io/IOException
    //   38	44	164	java/io/IOException
  }
  
  public String getIMEI(Context paramContext)
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
  
  public String getIMSI(Context paramContext)
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
  
  /* Error */
  public String getKernelVersion()
  {
    // Byte code:
    //   0: ldc -71
    //   2: astore_3
    //   3: ldc -71
    //   5: astore_1
    //   6: new 404	java/io/FileInputStream
    //   9: dup
    //   10: ldc_w 406
    //   13: invokespecial 407	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   16: astore 5
    //   18: new 65	java/io/BufferedReader
    //   21: dup
    //   22: new 302	java/io/InputStreamReader
    //   25: dup
    //   26: aload 5
    //   28: invokespecial 323	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   31: sipush 8192
    //   34: invokespecial 68	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   37: astore_2
    //   38: aload_2
    //   39: invokevirtual 71	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   42: astore 4
    //   44: aload 4
    //   46: ifnull +30 -> 76
    //   49: new 409	java/lang/StringBuilder
    //   52: dup
    //   53: invokespecial 410	java/lang/StringBuilder:<init>	()V
    //   56: aload_1
    //   57: invokevirtual 414	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   60: aload 4
    //   62: invokevirtual 414	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: invokevirtual 417	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   68: astore 4
    //   70: aload 4
    //   72: astore_1
    //   73: goto -35 -> 38
    //   76: aload_2
    //   77: invokevirtual 85	java/io/BufferedReader:close	()V
    //   80: aload 5
    //   82: invokevirtual 420	java/io/InputStream:close	()V
    //   85: aload_3
    //   86: astore_2
    //   87: aload_1
    //   88: invokestatic 423	com/alipay/security/mobile/module/commonutils/CommonUtils:isNotBlank	(Ljava/lang/String;)Z
    //   91: ifeq +31 -> 122
    //   94: aload_1
    //   95: aload_1
    //   96: ldc_w 425
    //   99: invokevirtual 335	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   102: bipush 8
    //   104: iadd
    //   105: invokevirtual 427	java/lang/String:substring	(I)Ljava/lang/String;
    //   108: astore_1
    //   109: aload_1
    //   110: iconst_0
    //   111: aload_1
    //   112: ldc_w 429
    //   115: invokevirtual 335	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   118: invokevirtual 342	java/lang/String:substring	(II)Ljava/lang/String;
    //   121: astore_2
    //   122: aload_2
    //   123: areturn
    //   124: astore_2
    //   125: aconst_null
    //   126: astore_2
    //   127: aload_2
    //   128: invokevirtual 85	java/io/BufferedReader:close	()V
    //   131: aload 5
    //   133: invokevirtual 420	java/io/InputStream:close	()V
    //   136: goto -51 -> 85
    //   139: astore_2
    //   140: goto -55 -> 85
    //   143: astore_1
    //   144: aconst_null
    //   145: astore_2
    //   146: aload_2
    //   147: invokevirtual 85	java/io/BufferedReader:close	()V
    //   150: aload 5
    //   152: invokevirtual 420	java/io/InputStream:close	()V
    //   155: aload_1
    //   156: athrow
    //   157: astore_1
    //   158: ldc -71
    //   160: areturn
    //   161: astore_2
    //   162: goto -7 -> 155
    //   165: astore_1
    //   166: goto -20 -> 146
    //   169: astore 4
    //   171: goto -44 -> 127
    //   174: astore_2
    //   175: goto -90 -> 85
    //   178: astore_1
    //   179: ldc -71
    //   181: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	182	0	this	DeviceInfo
    //   5	107	1	localObject1	Object
    //   143	13	1	localObject2	Object
    //   157	1	1	localException	Exception
    //   165	1	1	localObject3	Object
    //   178	1	1	localFileNotFoundException	java.io.FileNotFoundException
    //   37	86	2	localObject4	Object
    //   124	1	2	localIOException1	java.io.IOException
    //   126	2	2	localObject5	Object
    //   139	1	2	localIOException2	java.io.IOException
    //   145	2	2	localObject6	Object
    //   161	1	2	localIOException3	java.io.IOException
    //   174	1	2	localIOException4	java.io.IOException
    //   2	84	3	str1	String
    //   42	29	4	str2	String
    //   169	1	4	localIOException5	java.io.IOException
    //   16	135	5	localFileInputStream	java.io.FileInputStream
    // Exception table:
    //   from	to	target	type
    //   18	38	124	java/io/IOException
    //   127	136	139	java/io/IOException
    //   18	38	143	finally
    //   87	122	157	java/lang/Exception
    //   146	155	161	java/io/IOException
    //   38	44	165	finally
    //   49	70	165	finally
    //   38	44	169	java/io/IOException
    //   49	70	169	java/io/IOException
    //   76	85	174	java/io/IOException
    //   6	18	178	java/io/FileNotFoundException
  }
  
  public String getMACAddress(Context paramContext)
  {
    try
    {
      paramContext = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getMacAddress();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  /* Error */
  public String getMemorySize()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: lconst_0
    //   4: lstore 4
    //   6: new 58	java/io/FileReader
    //   9: dup
    //   10: ldc_w 446
    //   13: invokespecial 63	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   16: astore 7
    //   18: new 65	java/io/BufferedReader
    //   21: dup
    //   22: aload 7
    //   24: sipush 8192
    //   27: invokespecial 68	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   30: astore 8
    //   32: aload 8
    //   34: invokevirtual 71	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   37: astore 6
    //   39: lload 4
    //   41: lstore_2
    //   42: aload 6
    //   44: ifnull +20 -> 64
    //   47: aload 6
    //   49: ldc_w 448
    //   52: invokevirtual 194	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   55: iconst_1
    //   56: aaload
    //   57: invokestatic 451	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   60: istore_1
    //   61: iload_1
    //   62: i2l
    //   63: lstore_2
    //   64: aload 7
    //   66: invokevirtual 86	java/io/FileReader:close	()V
    //   69: aload 8
    //   71: invokevirtual 85	java/io/BufferedReader:close	()V
    //   74: lload_2
    //   75: invokestatic 454	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   78: areturn
    //   79: astore 7
    //   81: aconst_null
    //   82: astore 7
    //   84: aload 7
    //   86: ifnull +8 -> 94
    //   89: aload 7
    //   91: invokevirtual 86	java/io/FileReader:close	()V
    //   94: lload 4
    //   96: lstore_2
    //   97: aload 6
    //   99: ifnull -25 -> 74
    //   102: aload 6
    //   104: invokevirtual 85	java/io/BufferedReader:close	()V
    //   107: lload 4
    //   109: lstore_2
    //   110: goto -36 -> 74
    //   113: astore 6
    //   115: lload 4
    //   117: lstore_2
    //   118: goto -44 -> 74
    //   121: astore 6
    //   123: aconst_null
    //   124: astore 9
    //   126: aconst_null
    //   127: astore 7
    //   129: aload 9
    //   131: ifnull +8 -> 139
    //   134: aload 9
    //   136: invokevirtual 86	java/io/FileReader:close	()V
    //   139: aload 7
    //   141: ifnull +8 -> 149
    //   144: aload 7
    //   146: invokevirtual 85	java/io/BufferedReader:close	()V
    //   149: aload 6
    //   151: athrow
    //   152: astore 6
    //   154: goto -85 -> 69
    //   157: astore 6
    //   159: goto -85 -> 74
    //   162: astore 7
    //   164: goto -70 -> 94
    //   167: astore 8
    //   169: goto -30 -> 139
    //   172: astore 7
    //   174: goto -25 -> 149
    //   177: astore 6
    //   179: aconst_null
    //   180: astore 8
    //   182: aload 7
    //   184: astore 9
    //   186: aload 8
    //   188: astore 7
    //   190: goto -61 -> 129
    //   193: astore 6
    //   195: aload 7
    //   197: astore 9
    //   199: aload 8
    //   201: astore 7
    //   203: goto -74 -> 129
    //   206: astore 8
    //   208: goto -124 -> 84
    //   211: astore 6
    //   213: aload 8
    //   215: astore 6
    //   217: goto -133 -> 84
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	220	0	this	DeviceInfo
    //   60	2	1	i	int
    //   41	77	2	l1	long
    //   4	112	4	l2	long
    //   1	102	6	str	String
    //   113	1	6	localIOException1	java.io.IOException
    //   121	29	6	localObject1	Object
    //   152	1	6	localIOException2	java.io.IOException
    //   157	1	6	localIOException3	java.io.IOException
    //   177	1	6	localObject2	Object
    //   193	1	6	localObject3	Object
    //   211	1	6	localIOException4	java.io.IOException
    //   215	1	6	localObject4	Object
    //   16	49	7	localFileReader	java.io.FileReader
    //   79	1	7	localIOException5	java.io.IOException
    //   82	63	7	localObject5	Object
    //   162	1	7	localIOException6	java.io.IOException
    //   172	11	7	localIOException7	java.io.IOException
    //   188	14	7	localObject6	Object
    //   30	40	8	localBufferedReader	java.io.BufferedReader
    //   167	1	8	localIOException8	java.io.IOException
    //   180	20	8	localObject7	Object
    //   206	8	8	localIOException9	java.io.IOException
    //   124	74	9	localObject8	Object
    // Exception table:
    //   from	to	target	type
    //   6	18	79	java/io/IOException
    //   102	107	113	java/io/IOException
    //   6	18	121	finally
    //   64	69	152	java/io/IOException
    //   69	74	157	java/io/IOException
    //   89	94	162	java/io/IOException
    //   134	139	167	java/io/IOException
    //   144	149	172	java/io/IOException
    //   18	32	177	finally
    //   32	39	193	finally
    //   47	61	193	finally
    //   18	32	206	java/io/IOException
    //   32	39	211	java/io/IOException
    //   47	61	211	java/io/IOException
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
    catch (Exception paramContext) {}
    return null;
  }
  
  public String getOperatorName(Context paramContext)
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
  
  public String getOperatorType(Context paramContext)
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
  
  public String getPhoneNumber(Context paramContext)
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
  
  public String getPropPreviewsSizeOfCamera(Context paramContext)
  {
    a(paramContext.getApplicationContext());
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
  
  public String getSIMSerial(Context paramContext)
  {
    try
    {
      paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getSimSerialNumber();
      return paramContext;
    }
    catch (Exception paramContext) {}
    return "";
  }
  
  public String getScreenDpi(Context paramContext)
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
  
  public String getScreenHeight(Context paramContext)
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
  
  public String getScreenResolution(Context paramContext)
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
  
  public String getScreenWidth(Context paramContext)
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
  
  public String getSensorDigest(Context paramContext)
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
        for (paramContext = CommonUtils.sha1ByString(paramContext.toString());; paramContext = null) {
          return paramContext;
        }
        return null;
      }
      catch (Exception paramContext) {}
    }
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
  
  public String getVoiceMailNumber(Context paramContext)
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
  
  public String getVoiceMailTag(Context paramContext)
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
    catch (Throwable paramContext) {}
    return "";
  }
  
  class CameraSizeComparator
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
