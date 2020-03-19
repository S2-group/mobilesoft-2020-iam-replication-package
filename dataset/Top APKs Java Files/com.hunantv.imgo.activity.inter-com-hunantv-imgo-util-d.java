package com.hunantv.imgo.util;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.support.annotation.af;
import android.support.annotation.ag;
import android.support.v4.app.NotificationManagerCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.hunantv.imgo.a;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class d
{
  public static final String a = "AppBaseInfoUtil";
  private static String b;
  private static int c = 0;
  private static String d;
  private static String e = "";
  private static final int f = 32;
  private static final int g = 31;
  private static final String h = "openudid";
  private static final String i = "openudid_prefs";
  private static int j = 0;
  private static long k = 0L;
  private static long l = 0L;
  
  public d() {}
  
  public static int A()
  {
    return ah.c("playerType");
  }
  
  /* Error */
  public static String B()
  {
    // Byte code:
    //   0: ldc 32
    //   2: astore_2
    //   3: new 59	java/io/FileReader
    //   6: dup
    //   7: ldc 61
    //   9: invokespecial 64	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   12: astore 6
    //   14: new 66	java/io/BufferedReader
    //   17: dup
    //   18: aload 6
    //   20: sipush 8192
    //   23: invokespecial 69	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   26: astore 5
    //   28: aload 5
    //   30: astore 8
    //   32: aload 6
    //   34: astore 7
    //   36: aload 5
    //   38: invokevirtual 72	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   41: ldc 74
    //   43: ldc 32
    //   45: invokevirtual 80	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   48: astore 4
    //   50: aload 5
    //   52: astore 8
    //   54: aload 6
    //   56: astore 7
    //   58: aload 4
    //   60: astore_3
    //   61: aload 5
    //   63: invokevirtual 72	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   66: ldc 74
    //   68: ldc 32
    //   70: invokevirtual 80	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   73: astore 9
    //   75: aload 5
    //   77: astore 8
    //   79: aload 6
    //   81: astore 7
    //   83: aload 4
    //   85: astore_3
    //   86: aload 9
    //   88: astore_2
    //   89: aload 4
    //   91: ldc 82
    //   93: invokevirtual 86	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   96: astore 10
    //   98: aload 5
    //   100: astore 8
    //   102: aload 6
    //   104: astore 7
    //   106: aload 4
    //   108: astore_3
    //   109: aload 9
    //   111: astore_2
    //   112: aload 9
    //   114: ldc 82
    //   116: invokevirtual 86	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   119: astore 11
    //   121: aload 5
    //   123: astore 8
    //   125: aload 6
    //   127: astore 7
    //   129: aload 4
    //   131: astore_3
    //   132: aload 9
    //   134: astore_2
    //   135: aload 10
    //   137: iconst_1
    //   138: aaload
    //   139: iconst_0
    //   140: aload 10
    //   142: iconst_1
    //   143: aaload
    //   144: invokevirtual 89	java/lang/String:length	()I
    //   147: iconst_2
    //   148: isub
    //   149: invokevirtual 93	java/lang/String:substring	(II)Ljava/lang/String;
    //   152: invokestatic 97	com/hunantv/imgo/util/ae:a	(Ljava/lang/String;)I
    //   155: istore_0
    //   156: aload 5
    //   158: astore 8
    //   160: aload 6
    //   162: astore 7
    //   164: aload 4
    //   166: astore_3
    //   167: aload 9
    //   169: astore_2
    //   170: aload 11
    //   172: iconst_1
    //   173: aaload
    //   174: iconst_0
    //   175: aload 11
    //   177: iconst_1
    //   178: aaload
    //   179: invokevirtual 89	java/lang/String:length	()I
    //   182: iconst_2
    //   183: isub
    //   184: invokevirtual 93	java/lang/String:substring	(II)Ljava/lang/String;
    //   187: invokestatic 97	com/hunantv/imgo/util/ae:a	(Ljava/lang/String;)I
    //   190: istore_1
    //   191: aload 5
    //   193: astore 8
    //   195: aload 6
    //   197: astore 7
    //   199: aload 4
    //   201: astore_3
    //   202: aload 9
    //   204: astore_2
    //   205: new 99	java/lang/StringBuilder
    //   208: dup
    //   209: invokespecial 100	java/lang/StringBuilder:<init>	()V
    //   212: ldc 102
    //   214: invokevirtual 106	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   217: iload_0
    //   218: sipush 1024
    //   221: idiv
    //   222: invokevirtual 109	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   225: ldc 111
    //   227: invokevirtual 106	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   230: invokevirtual 114	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   233: astore 4
    //   235: aload 5
    //   237: astore 8
    //   239: aload 6
    //   241: astore 7
    //   243: aload 4
    //   245: astore_3
    //   246: aload 9
    //   248: astore_2
    //   249: new 99	java/lang/StringBuilder
    //   252: dup
    //   253: invokespecial 100	java/lang/StringBuilder:<init>	()V
    //   256: ldc 116
    //   258: invokevirtual 106	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   261: iload_1
    //   262: sipush 1024
    //   265: idiv
    //   266: invokevirtual 109	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   269: ldc 111
    //   271: invokevirtual 106	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   274: invokevirtual 114	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   277: astore 9
    //   279: aload 9
    //   281: astore_2
    //   282: aload 5
    //   284: astore 8
    //   286: aload 6
    //   288: astore 7
    //   290: aload 5
    //   292: invokevirtual 119	java/io/BufferedReader:close	()V
    //   295: aload 5
    //   297: ifnull +8 -> 305
    //   300: aload 5
    //   302: invokevirtual 119	java/io/BufferedReader:close	()V
    //   305: aload_2
    //   306: astore 5
    //   308: aload 4
    //   310: astore 7
    //   312: aload 6
    //   314: ifnull +15 -> 329
    //   317: aload 6
    //   319: invokevirtual 120	java/io/FileReader:close	()V
    //   322: aload 4
    //   324: astore 7
    //   326: aload_2
    //   327: astore 5
    //   329: new 99	java/lang/StringBuilder
    //   332: dup
    //   333: invokespecial 100	java/lang/StringBuilder:<init>	()V
    //   336: aload 7
    //   338: invokevirtual 106	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   341: ldc 74
    //   343: invokevirtual 106	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   346: aload 5
    //   348: invokevirtual 106	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   351: invokevirtual 114	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   354: areturn
    //   355: astore_3
    //   356: aload_3
    //   357: invokevirtual 123	java/lang/Exception:printStackTrace	()V
    //   360: goto -55 -> 305
    //   363: astore_3
    //   364: aload_3
    //   365: invokevirtual 123	java/lang/Exception:printStackTrace	()V
    //   368: aload_2
    //   369: astore 5
    //   371: aload 4
    //   373: astore 7
    //   375: goto -46 -> 329
    //   378: astore 4
    //   380: aconst_null
    //   381: astore 5
    //   383: aconst_null
    //   384: astore 6
    //   386: ldc 32
    //   388: astore_3
    //   389: ldc 32
    //   391: astore_2
    //   392: aload 5
    //   394: astore 8
    //   396: aload 6
    //   398: astore 7
    //   400: aload 4
    //   402: invokevirtual 123	java/lang/Exception:printStackTrace	()V
    //   405: aload 5
    //   407: ifnull +8 -> 415
    //   410: aload 5
    //   412: invokevirtual 119	java/io/BufferedReader:close	()V
    //   415: aload_2
    //   416: astore 5
    //   418: aload_3
    //   419: astore 7
    //   421: aload 6
    //   423: ifnull -94 -> 329
    //   426: aload 6
    //   428: invokevirtual 120	java/io/FileReader:close	()V
    //   431: aload_2
    //   432: astore 5
    //   434: aload_3
    //   435: astore 7
    //   437: goto -108 -> 329
    //   440: astore 4
    //   442: aload 4
    //   444: invokevirtual 123	java/lang/Exception:printStackTrace	()V
    //   447: aload_2
    //   448: astore 5
    //   450: aload_3
    //   451: astore 7
    //   453: goto -124 -> 329
    //   456: astore 4
    //   458: aload 4
    //   460: invokevirtual 123	java/lang/Exception:printStackTrace	()V
    //   463: goto -48 -> 415
    //   466: astore_2
    //   467: aconst_null
    //   468: astore 8
    //   470: aconst_null
    //   471: astore 6
    //   473: aload 8
    //   475: ifnull +8 -> 483
    //   478: aload 8
    //   480: invokevirtual 119	java/io/BufferedReader:close	()V
    //   483: aload 6
    //   485: ifnull +8 -> 493
    //   488: aload 6
    //   490: invokevirtual 120	java/io/FileReader:close	()V
    //   493: aload_2
    //   494: athrow
    //   495: astore_3
    //   496: aload_3
    //   497: invokevirtual 123	java/lang/Exception:printStackTrace	()V
    //   500: goto -17 -> 483
    //   503: astore_3
    //   504: aload_3
    //   505: invokevirtual 123	java/lang/Exception:printStackTrace	()V
    //   508: goto -15 -> 493
    //   511: astore_2
    //   512: aconst_null
    //   513: astore 8
    //   515: goto -42 -> 473
    //   518: astore_2
    //   519: aload 7
    //   521: astore 6
    //   523: goto -50 -> 473
    //   526: astore 4
    //   528: aconst_null
    //   529: astore 5
    //   531: ldc 32
    //   533: astore_3
    //   534: ldc 32
    //   536: astore_2
    //   537: goto -145 -> 392
    //   540: astore 4
    //   542: ldc 32
    //   544: astore_3
    //   545: ldc 32
    //   547: astore_2
    //   548: goto -156 -> 392
    //   551: astore 4
    //   553: goto -161 -> 392
    //   556: astore 7
    //   558: aload 4
    //   560: astore_3
    //   561: aload 7
    //   563: astore 4
    //   565: goto -173 -> 392
    // Local variable table:
    //   start	length	slot	name	signature
    //   155	67	0	m	int
    //   190	76	1	n	int
    //   2	446	2	localObject1	Object
    //   466	28	2	localObject2	Object
    //   511	1	2	localObject3	Object
    //   518	1	2	localObject4	Object
    //   536	12	2	str1	String
    //   60	186	3	str2	String
    //   355	2	3	localException1	Exception
    //   363	2	3	localException2	Exception
    //   388	63	3	str3	String
    //   495	2	3	localException3	Exception
    //   503	2	3	localException4	Exception
    //   533	28	3	localObject5	Object
    //   48	324	4	str4	String
    //   378	23	4	localException5	Exception
    //   440	3	4	localException6	Exception
    //   456	3	4	localException7	Exception
    //   526	1	4	localException8	Exception
    //   540	1	4	localException9	Exception
    //   551	8	4	localException10	Exception
    //   563	1	4	localException11	Exception
    //   26	504	5	localObject6	Object
    //   12	510	6	localObject7	Object
    //   34	486	7	localObject8	Object
    //   556	6	7	localException12	Exception
    //   30	484	8	localObject9	Object
    //   73	207	9	str5	String
    //   96	45	10	arrayOfString1	String[]
    //   119	57	11	arrayOfString2	String[]
    // Exception table:
    //   from	to	target	type
    //   300	305	355	java/lang/Exception
    //   317	322	363	java/lang/Exception
    //   3	14	378	java/lang/Exception
    //   426	431	440	java/lang/Exception
    //   410	415	456	java/lang/Exception
    //   3	14	466	finally
    //   478	483	495	java/lang/Exception
    //   488	493	503	java/lang/Exception
    //   14	28	511	finally
    //   36	50	518	finally
    //   61	75	518	finally
    //   89	98	518	finally
    //   112	121	518	finally
    //   135	156	518	finally
    //   170	191	518	finally
    //   205	235	518	finally
    //   249	279	518	finally
    //   290	295	518	finally
    //   400	405	518	finally
    //   14	28	526	java/lang/Exception
    //   36	50	540	java/lang/Exception
    //   61	75	551	java/lang/Exception
    //   89	98	551	java/lang/Exception
    //   112	121	551	java/lang/Exception
    //   135	156	551	java/lang/Exception
    //   170	191	551	java/lang/Exception
    //   205	235	551	java/lang/Exception
    //   249	279	551	java/lang/Exception
    //   290	295	556	java/lang/Exception
  }
  
  public static String C()
  {
    int m = 0;
    if (Environment.getExternalStorageState().equals("mounted"))
    {
      StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
      m = localStatFs.getAvailableBlocks() * localStatFs.getBlockSize() / 1024 / 1024;
    }
    return m + "MB";
  }
  
  public static String D()
  {
    int m = 2;
    String[] arrayOfString1 = new String[2];
    arrayOfString1[0] = "";
    arrayOfString1[1] = "";
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"), 8192);
      String[] arrayOfString2 = localBufferedReader.readLine().split("\\s+");
      while (m < arrayOfString2.length)
      {
        arrayOfString1[0] = (arrayOfString1[0] + arrayOfString2[m] + " ");
        m += 1;
      }
      arrayOfString2 = localBufferedReader.readLine().split("\\s+");
      arrayOfString1[1] = (arrayOfString1[1] + arrayOfString2[2]);
      localBufferedReader.close();
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return "1-cpu型号:" + arrayOfString1[0] + "2-cpu频率:" + arrayOfString1[1];
  }
  
  /* Error */
  public static String E()
  {
    // Byte code:
    //   0: new 59	java/io/FileReader
    //   3: dup
    //   4: ldc -100
    //   6: invokespecial 64	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   9: astore_3
    //   10: new 66	java/io/BufferedReader
    //   13: dup
    //   14: aload_3
    //   15: sipush 8192
    //   18: invokespecial 69	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   21: astore_2
    //   22: aload_2
    //   23: astore 5
    //   25: aload_3
    //   26: astore 4
    //   28: aload_2
    //   29: invokevirtual 72	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   32: ldc 82
    //   34: invokevirtual 86	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   37: iconst_1
    //   38: aaload
    //   39: ldc 74
    //   41: ldc 32
    //   43: invokevirtual 80	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   46: astore_0
    //   47: aload_2
    //   48: astore 5
    //   50: aload_3
    //   51: astore 4
    //   53: aload_2
    //   54: invokevirtual 119	java/io/BufferedReader:close	()V
    //   57: aload_2
    //   58: ifnull +7 -> 65
    //   61: aload_2
    //   62: invokevirtual 119	java/io/BufferedReader:close	()V
    //   65: aload_0
    //   66: astore_1
    //   67: aload_3
    //   68: ifnull +9 -> 77
    //   71: aload_3
    //   72: invokevirtual 120	java/io/FileReader:close	()V
    //   75: aload_0
    //   76: astore_1
    //   77: aload_1
    //   78: areturn
    //   79: astore_1
    //   80: aload_1
    //   81: invokevirtual 123	java/lang/Exception:printStackTrace	()V
    //   84: goto -19 -> 65
    //   87: astore_1
    //   88: aload_1
    //   89: invokevirtual 123	java/lang/Exception:printStackTrace	()V
    //   92: aload_0
    //   93: areturn
    //   94: astore_1
    //   95: aconst_null
    //   96: astore_2
    //   97: aconst_null
    //   98: astore_3
    //   99: ldc 32
    //   101: astore_0
    //   102: aload_2
    //   103: astore 5
    //   105: aload_3
    //   106: astore 4
    //   108: aload_1
    //   109: invokevirtual 123	java/lang/Exception:printStackTrace	()V
    //   112: aload_2
    //   113: ifnull +7 -> 120
    //   116: aload_2
    //   117: invokevirtual 119	java/io/BufferedReader:close	()V
    //   120: aload_0
    //   121: astore_1
    //   122: aload_3
    //   123: ifnull -46 -> 77
    //   126: aload_3
    //   127: invokevirtual 120	java/io/FileReader:close	()V
    //   130: aload_0
    //   131: areturn
    //   132: astore_1
    //   133: aload_1
    //   134: invokevirtual 123	java/lang/Exception:printStackTrace	()V
    //   137: aload_0
    //   138: areturn
    //   139: astore_1
    //   140: aload_1
    //   141: invokevirtual 123	java/lang/Exception:printStackTrace	()V
    //   144: goto -24 -> 120
    //   147: astore_0
    //   148: aconst_null
    //   149: astore 5
    //   151: aconst_null
    //   152: astore_3
    //   153: aload 5
    //   155: ifnull +8 -> 163
    //   158: aload 5
    //   160: invokevirtual 119	java/io/BufferedReader:close	()V
    //   163: aload_3
    //   164: ifnull +7 -> 171
    //   167: aload_3
    //   168: invokevirtual 120	java/io/FileReader:close	()V
    //   171: aload_0
    //   172: athrow
    //   173: astore_1
    //   174: aload_1
    //   175: invokevirtual 123	java/lang/Exception:printStackTrace	()V
    //   178: goto -15 -> 163
    //   181: astore_1
    //   182: aload_1
    //   183: invokevirtual 123	java/lang/Exception:printStackTrace	()V
    //   186: goto -15 -> 171
    //   189: astore_0
    //   190: aconst_null
    //   191: astore 5
    //   193: goto -40 -> 153
    //   196: astore_0
    //   197: aload 4
    //   199: astore_3
    //   200: goto -47 -> 153
    //   203: astore_1
    //   204: aconst_null
    //   205: astore_2
    //   206: ldc 32
    //   208: astore_0
    //   209: goto -107 -> 102
    //   212: astore_1
    //   213: ldc 32
    //   215: astore_0
    //   216: goto -114 -> 102
    //   219: astore_1
    //   220: goto -118 -> 102
    // Local variable table:
    //   start	length	slot	name	signature
    //   46	92	0	str1	String
    //   147	25	0	localObject1	Object
    //   189	1	0	localObject2	Object
    //   196	1	0	localObject3	Object
    //   208	8	0	str2	String
    //   66	12	1	str3	String
    //   79	2	1	localException1	Exception
    //   87	2	1	localException2	Exception
    //   94	15	1	localException3	Exception
    //   121	1	1	str4	String
    //   132	2	1	localException4	Exception
    //   139	2	1	localException5	Exception
    //   173	2	1	localException6	Exception
    //   181	2	1	localException7	Exception
    //   203	1	1	localException8	Exception
    //   212	1	1	localException9	Exception
    //   219	1	1	localException10	Exception
    //   21	185	2	localBufferedReader1	BufferedReader
    //   9	191	3	localObject4	Object
    //   26	172	4	localObject5	Object
    //   23	169	5	localBufferedReader2	BufferedReader
    // Exception table:
    //   from	to	target	type
    //   61	65	79	java/lang/Exception
    //   71	75	87	java/lang/Exception
    //   0	10	94	java/lang/Exception
    //   126	130	132	java/lang/Exception
    //   116	120	139	java/lang/Exception
    //   0	10	147	finally
    //   158	163	173	java/lang/Exception
    //   167	171	181	java/lang/Exception
    //   10	22	189	finally
    //   28	47	196	finally
    //   53	57	196	finally
    //   108	112	196	finally
    //   10	22	203	java/lang/Exception
    //   28	47	212	java/lang/Exception
    //   53	57	219	java/lang/Exception
  }
  
  public static String F()
  {
    String str1 = "";
    Object localObject2 = str1;
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/cpuinfo")), 1000);
      do
      {
        localObject2 = str1;
        String str2 = localBufferedReader.readLine();
        localObject1 = str1;
        if (str2 == null) {
          break;
        }
        localObject2 = str1;
        y.a("AppBaseInfoUtil", "chip:" + str2);
        localObject2 = str1;
        localObject1 = Pattern.compile("Hardware\\s+\\:\\s*(.*)").matcher(str2);
        localObject2 = str1;
      } while (!((Matcher)localObject1).find());
      localObject2 = str1;
      Object localObject1 = ((Matcher)localObject1).group(1);
      localObject2 = localObject1;
      localBufferedReader.close();
      return localObject1;
    }
    catch (IOException localIOException) {}
    return localObject2;
  }
  
  @Deprecated
  public static String G()
  {
    return Build.BRAND;
  }
  
  @Deprecated
  public static String H()
  {
    int m = a.a().getResources().getDisplayMetrics().widthPixels;
    int n = a.a().getResources().getDisplayMetrics().heightPixels;
    return String.valueOf(m) + "*" + String.valueOf(n);
  }
  
  @Deprecated
  public static int I()
  {
    if (J()) {
      return 31;
    }
    return 32;
  }
  
  public static boolean J()
  {
    Configuration localConfiguration = a.a().getResources().getConfiguration();
    try
    {
      boolean bool = ((Boolean)localConfiguration.getClass().getMethod("isLayoutSizeAtLeast", new Class[] { Integer.TYPE }).invoke(localConfiguration, new Object[] { Integer.valueOf(3) })).booleanValue();
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  public static void K()
  {
    String str2 = ah.a("user_agent");
    Object localObject = str2;
    if ((!TextUtils.isEmpty(str2)) || (Build.VERSION.SDK_INT >= 17)) {}
    for (;;)
    {
      try
      {
        localObject = WebSettings.getDefaultUserAgent(a.a());
        ah.a("user_agent", (String)localObject);
        e = (String)localObject + " ImgoTV-aphone/" + b() + "." + n.a(new SimpleDateFormat("yyMMdd", Locale.CHINA));
        return;
      }
      catch (Exception localException2) {}
      try
      {
        localObject = new WebView(a.a()).getSettings().getUserAgentString();
        localException2.printStackTrace();
      }
      catch (Exception localException3)
      {
        for (;;)
        {
          localObject = System.getProperty("http.agent");
          localException3.printStackTrace();
        }
      }
      try
      {
        localObject = Class.forName("android.webkit.WebSettingsClassic");
        Constructor localConstructor = ((Class)localObject).getDeclaredConstructor(new Class[] { Context.class, Class.forName("android.webkit.WebViewClassic") });
        localConstructor.setAccessible(true);
        localObject = (String)((Class)localObject).getMethod("getUserAgentString", new Class[0]).invoke(localConstructor.newInstance(new Object[] { a.a(), null }), new Object[0]);
      }
      catch (Exception localException1)
      {
        String str1 = new WebView(a.a()).getSettings().getUserAgentString();
      }
    }
  }
  
  public static String L()
  {
    return e;
  }
  
  @Deprecated
  public static String M()
  {
    return "";
  }
  
  @Deprecated
  public static String N()
  {
    SharedPreferences localSharedPreferences = a.a().getSharedPreferences("openudid_prefs", 0);
    Object localObject = localSharedPreferences.getString("openudid", "");
    if (!((String)localObject).equals("")) {}
    String str;
    do
    {
      return localObject;
      str = Settings.Secure.getString(a.a().getContentResolver(), "android_id");
      if ((str == null) || (str.equals("9774d56d682e549c"))) {
        break;
      }
      localObject = str;
    } while (str.length() >= 15);
    localObject = new BigInteger(64, new SecureRandom()).toString(16);
    localSharedPreferences.edit().putString("openudid", (String)localObject);
    return localObject;
  }
  
  @Deprecated
  public static int O()
  {
    return a.a().getResources().getDisplayMetrics().densityDpi;
  }
  
  @Deprecated
  @af
  public static String P()
  {
    try
    {
      String str1 = ((TelephonyManager)a.a().getSystemService("phone")).getNetworkOperator();
      String str2 = str1;
      if (str1 == null) {
        str2 = "";
      }
      return str2;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        Object localObject = null;
      }
    }
  }
  
  /* Error */
  public static String Q()
  {
    // Byte code:
    //   0: iconst_2
    //   1: istore_0
    //   2: iconst_2
    //   3: anewarray 76	java/lang/String
    //   6: astore 4
    //   8: aload 4
    //   10: iconst_0
    //   11: ldc 32
    //   13: aastore
    //   14: aload 4
    //   16: iconst_1
    //   17: ldc 32
    //   19: aastore
    //   20: new 66	java/io/BufferedReader
    //   23: dup
    //   24: new 59	java/io/FileReader
    //   27: dup
    //   28: ldc -100
    //   30: invokespecial 64	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   33: sipush 8192
    //   36: invokespecial 69	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   39: astore_2
    //   40: aload_2
    //   41: astore_1
    //   42: aload_2
    //   43: invokevirtual 72	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   46: ldc -98
    //   48: invokevirtual 86	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   51: astore_3
    //   52: aload_2
    //   53: astore_1
    //   54: iload_0
    //   55: aload_3
    //   56: arraylength
    //   57: if_icmpge +44 -> 101
    //   60: aload_2
    //   61: astore_1
    //   62: aload 4
    //   64: iconst_0
    //   65: new 99	java/lang/StringBuilder
    //   68: dup
    //   69: invokespecial 100	java/lang/StringBuilder:<init>	()V
    //   72: aload 4
    //   74: iconst_0
    //   75: aaload
    //   76: invokevirtual 106	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: aload_3
    //   80: iload_0
    //   81: aaload
    //   82: invokevirtual 106	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: ldc 74
    //   87: invokevirtual 106	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   90: invokevirtual 114	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   93: aastore
    //   94: iload_0
    //   95: iconst_1
    //   96: iadd
    //   97: istore_0
    //   98: goto -46 -> 52
    //   101: aload_2
    //   102: astore_1
    //   103: aload_2
    //   104: invokevirtual 72	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   107: ldc -98
    //   109: invokevirtual 86	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   112: astore_3
    //   113: aload_2
    //   114: astore_1
    //   115: aload 4
    //   117: iconst_1
    //   118: new 99	java/lang/StringBuilder
    //   121: dup
    //   122: invokespecial 100	java/lang/StringBuilder:<init>	()V
    //   125: aload 4
    //   127: iconst_1
    //   128: aaload
    //   129: invokevirtual 106	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   132: aload_3
    //   133: iconst_2
    //   134: aaload
    //   135: invokevirtual 106	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: invokevirtual 114	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   141: aastore
    //   142: aload_2
    //   143: ifnull +7 -> 150
    //   146: aload_2
    //   147: invokevirtual 119	java/io/BufferedReader:close	()V
    //   150: aload 4
    //   152: iconst_0
    //   153: aaload
    //   154: areturn
    //   155: astore_1
    //   156: aload_1
    //   157: invokevirtual 437	java/io/IOException:printStackTrace	()V
    //   160: goto -10 -> 150
    //   163: astore_3
    //   164: aconst_null
    //   165: astore_2
    //   166: aload_2
    //   167: astore_1
    //   168: aload_3
    //   169: invokevirtual 123	java/lang/Exception:printStackTrace	()V
    //   172: aload_2
    //   173: ifnull -23 -> 150
    //   176: aload_2
    //   177: invokevirtual 119	java/io/BufferedReader:close	()V
    //   180: goto -30 -> 150
    //   183: astore_1
    //   184: aload_1
    //   185: invokevirtual 437	java/io/IOException:printStackTrace	()V
    //   188: goto -38 -> 150
    //   191: astore_2
    //   192: aconst_null
    //   193: astore_1
    //   194: aload_1
    //   195: ifnull +7 -> 202
    //   198: aload_1
    //   199: invokevirtual 119	java/io/BufferedReader:close	()V
    //   202: aload_2
    //   203: athrow
    //   204: astore_1
    //   205: aload_1
    //   206: invokevirtual 437	java/io/IOException:printStackTrace	()V
    //   209: goto -7 -> 202
    //   212: astore_2
    //   213: goto -19 -> 194
    //   216: astore_3
    //   217: goto -51 -> 166
    // Local variable table:
    //   start	length	slot	name	signature
    //   1	97	0	m	int
    //   41	74	1	localBufferedReader1	BufferedReader
    //   155	2	1	localIOException1	IOException
    //   167	1	1	localBufferedReader2	BufferedReader
    //   183	2	1	localIOException2	IOException
    //   193	6	1	localObject1	Object
    //   204	2	1	localIOException3	IOException
    //   39	138	2	localBufferedReader3	BufferedReader
    //   191	12	2	localObject2	Object
    //   212	1	2	localObject3	Object
    //   51	82	3	arrayOfString1	String[]
    //   163	6	3	localException1	Exception
    //   216	1	3	localException2	Exception
    //   6	145	4	arrayOfString2	String[]
    // Exception table:
    //   from	to	target	type
    //   146	150	155	java/io/IOException
    //   20	40	163	java/lang/Exception
    //   176	180	183	java/io/IOException
    //   20	40	191	finally
    //   198	202	204	java/io/IOException
    //   42	52	212	finally
    //   54	60	212	finally
    //   62	94	212	finally
    //   103	113	212	finally
    //   115	142	212	finally
    //   168	172	212	finally
    //   42	52	216	java/lang/Exception
    //   54	60	216	java/lang/Exception
    //   62	94	216	java/lang/Exception
    //   103	113	216	java/lang/Exception
    //   115	142	216	java/lang/Exception
  }
  
  @Deprecated
  public static String R()
  {
    try
    {
      String str = v();
      if (str == null) {
        return "";
      }
      str = str.replace(":", "");
      return str;
    }
    catch (Exception localException) {}
    return "";
  }
  
  public static boolean S()
  {
    try
    {
      String str1 = a.a().getPackageName();
      String str2 = e(a.a());
      System.out.println("packageName=" + str1 + ",topActivityClassName=" + str2);
      if ((str1 != null) && (str2 != null) && (str2.startsWith("com.mgtv.ui.play")))
      {
        System.out.println("---> isRunningForeGround");
        return true;
      }
      System.out.println("---> isRunningBackGround");
      return false;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }
  
  public static String T()
  {
    return ah.b("recommend_guid", "");
  }
  
  public static void U()
  {
    k = ah.b("key_app_runs_times", 0L);
    l = ah.b(b(), 0L);
    if (k < 0L) {
      k = 0L;
    }
    if (k < 9223372036854775806L) {}
    for (k += 1L;; k = 2L)
    {
      ah.a("key_app_runs_times", k);
      return;
    }
  }
  
  public static boolean V()
  {
    return k == 1L;
  }
  
  public static long W()
  {
    return k;
  }
  
  public static void X()
  {
    l = ah.b(Integer.toString(e()), 0L);
    if (l < 0L) {
      l = 0L;
    }
    if (l < 9223372036854775806L) {}
    for (l += 1L;; l = 2L)
    {
      ah.a(Integer.toString(e()), l);
      return;
    }
  }
  
  public static boolean Y()
  {
    return l == 1L;
  }
  
  public static long Z()
  {
    return l;
  }
  
  public static int a(Context paramContext)
  {
    if ((paramContext == null) || (paramContext.getResources() == null)) {
      return 1;
    }
    return paramContext.getResources().getConfiguration().orientation;
  }
  
  public static String a()
  {
    return a.a().getPackageName();
  }
  
  @af
  public static String a(int paramInt)
  {
    return (paramInt & 0xFF) + "." + (paramInt >> 8 & 0xFF) + "." + (paramInt >> 16 & 0xFF) + "." + (paramInt >> 24 & 0xFF);
  }
  
  public static String a(boolean paramBoolean)
  {
    if (paramBoolean) {
      return "imgotv-aphone-" + b() + ".1";
    }
    return "imgotv-aphone-" + b();
  }
  
  public static void a(String paramString)
  {
    b = paramString;
  }
  
  public static boolean aa()
  {
    if (TextUtils.isEmpty(d)) {
      return false;
    }
    return d.equalsIgnoreCase("googleplay");
  }
  
  public static final String ab()
  {
    return ah.b("mfToken", "");
  }
  
  @ag
  public static String ac()
  {
    Object localObject1 = a.a();
    Object localObject3 = (ConnectivityManager)((Context)localObject1).getSystemService("connectivity");
    if (localObject3 == null) {
      return null;
    }
    localObject3 = ((ConnectivityManager)localObject3).getActiveNetworkInfo();
    if ((localObject3 != null) && (((NetworkInfo)localObject3).isConnected())) {
      try
      {
        int m = ((NetworkInfo)localObject3).getType();
        if (m == 0) {
          try
          {
            InetAddress localInetAddress;
            do
            {
              localObject1 = NetworkInterface.getNetworkInterfaces();
              while (!((Enumeration)localObject3).hasMoreElements())
              {
                if (!((Enumeration)localObject1).hasMoreElements()) {
                  break;
                }
                localObject3 = ((NetworkInterface)((Enumeration)localObject1).nextElement()).getInetAddresses();
              }
              localInetAddress = (InetAddress)((Enumeration)localObject3).nextElement();
            } while ((localInetAddress.isLoopbackAddress()) || (!(localInetAddress instanceof Inet4Address)));
            localObject1 = localInetAddress.getHostAddress();
            return localObject1;
          }
          catch (SocketException localSocketException)
          {
            localSocketException.printStackTrace();
          }
        }
        if (((NetworkInfo)localObject3).getType() == 1)
        {
          Object localObject2 = (WifiManager)localSocketException.getApplicationContext().getSystemService("wifi");
          if (localObject2 == null) {
            return null;
          }
          localObject2 = a(((WifiManager)localObject2).getConnectionInfo().getIpAddress());
          return localObject2;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return null;
  }
  
  public static final String ad()
  {
    if (NotificationManagerCompat.from(a.a()).areNotificationsEnabled()) {
      return "1";
    }
    return "0";
  }
  
  public static boolean ae()
  {
    return a.b;
  }
  
  public static String af()
  {
    if (ae()) {
      return "intelmgtv";
    }
    return "mgtv";
  }
  
  private static String ag()
  {
    Object localObject3 = null;
    Object localObject1 = "";
    try
    {
      LineNumberReader localLineNumberReader = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ").getInputStream()));
      do
      {
        localObject2 = localObject3;
        if (localObject1 == null) {
          break;
        }
        localObject2 = localLineNumberReader.readLine();
        localObject1 = localObject2;
      } while (localObject2 == null);
      Object localObject2 = ((String)localObject2).trim();
      return localObject2;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return null;
  }
  
  @Deprecated
  public static int b(Context paramContext)
  {
    switch (a(paramContext))
    {
    case 1: 
    default: 
      return 0;
    case 0: 
      return 90;
    case 8: 
      return 270;
    }
    return 180;
  }
  
  public static String b()
  {
    if (b == null) {}
    try
    {
      b = a.a().getPackageManager().getPackageInfo(a.a().getPackageName(), 16384).versionName;
      return b;
    }
    catch (Exception localException)
    {
      Log.e("AppBaseInfoUtil", localException.getMessage(), localException);
    }
    return "1.0";
  }
  
  public static void b(String paramString)
  {
    d = paramString;
  }
  
  @Deprecated
  public static int c(Context paramContext)
  {
    if (j != 0) {
      return j;
    }
    j = ah.c("uniqid");
    if ((j == -1) || (j == 0))
    {
      j = e(s());
      ah.a("uniqid", j);
    }
    return j;
  }
  
  public static String c()
  {
    StringBuffer localStringBuffer = new StringBuffer("aphone-");
    if (b == null) {}
    try
    {
      b = a.a().getPackageManager().getPackageInfo(a.a().getPackageName(), 16384).versionName;
      return b;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "1.0";
  }
  
  public static boolean c(String paramString)
  {
    List localList = a.a().getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (localList != null)
    {
      int m = 0;
      while (m < localList.size())
      {
        localArrayList.add(((PackageInfo)localList.get(m)).packageName);
        m += 1;
      }
    }
    return localArrayList.contains(paramString);
  }
  
  public static String d()
  {
    return "imgotv-aphone-" + b();
  }
  
  public static final void d(String paramString)
  {
    ah.a("mfToken", paramString);
  }
  
  public static int e()
  {
    if (c <= 0) {}
    try
    {
      c = a.a().getPackageManager().getPackageInfo(a.a().getPackageName(), 16384).versionCode;
      return c;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        c = 0;
      }
    }
  }
  
  private static int e(String paramString)
  {
    int m = 0;
    int n = 0;
    while (m < paramString.length())
    {
      n = n * 131 + paramString.charAt(m);
      m += 1;
    }
    return 0x7FFFFFFF & n;
  }
  
  private static String e(Context paramContext)
  {
    try
    {
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1);
      if (paramContext != null)
      {
        paramContext = ((ActivityManager.RunningTaskInfo)paramContext.get(0)).topActivity.getClassName();
        return paramContext;
      }
    }
    catch (SecurityException paramContext)
    {
      paramContext.printStackTrace();
      return "";
    }
    return null;
  }
  
  public static String f()
  {
    return "API" + Build.VERSION.SDK_INT;
  }
  
  @af
  public static String g()
  {
    try
    {
      String str1 = ((TelephonyManager)a.a().getSystemService("phone")).getSubscriberId();
      String str2 = str1;
      if (str1 == null) {
        str2 = "";
      }
      return str2;
    }
    catch (SecurityException localSecurityException)
    {
      for (;;)
      {
        localSecurityException.printStackTrace();
        Object localObject = null;
      }
    }
  }
  
  public static String h()
  {
    try
    {
      String str = ((TelephonyManager)a.a().getSystemService("phone")).getSubscriberId();
      y.a(d.class, "imsi=" + str);
      if (str == null) {
        return null;
      }
      if ((!str.startsWith("46000")) && (!str.startsWith("46002")) && (!str.startsWith("46007")))
      {
        if ((str.startsWith("46001")) || (str.startsWith("46006"))) {
          break label147;
        }
        if ((str.startsWith("46003")) || (str.startsWith("46005")) || (str.startsWith("46011"))) {
          break label151;
        }
        return "";
      }
    }
    catch (Exception localException)
    {
      return "";
    }
    return "chinaMobile";
    label147:
    return "chinaUnicon";
    label151:
    return "chinaGo";
  }
  
  public static String i()
  {
    return o.a().c();
  }
  
  public static String j()
  {
    return ah.b("ticket", "");
  }
  
  @Deprecated
  public static int k()
  {
    return ae.a(ah.b("uid", ""));
  }
  
  public static String l()
  {
    return ah.b("uuid", "");
  }
  
  public static String m()
  {
    return ah.b("username", "");
  }
  
  public static String n()
  {
    return ah.b("nickname", "");
  }
  
  public static String o()
  {
    return Build.MODEL;
  }
  
  public static String p()
  {
    return Build.VERSION.RELEASE;
  }
  
  public static String q()
  {
    return "aphone-" + p();
  }
  
  public static String r()
  {
    return Build.MANUFACTURER;
  }
  
  public static String s()
  {
    return o.a().b();
  }
  
  public static String t()
  {
    return ah.a("push_cid");
  }
  
  public static String u()
  {
    Object localObject;
    if (ae()) {
      localObject = "";
    }
    for (;;)
    {
      return localObject;
      try
      {
        String str = Settings.System.getString(a.a().getContentResolver(), "android_id");
        localObject = str;
        if (str == null) {
          return "";
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return null;
  }
  
  public static String v()
  {
    Object localObject;
    if (ae()) {
      localObject = s();
    }
    for (;;)
    {
      return localObject;
      try
      {
        String str = ((WifiManager)a.a().getApplicationContext().getSystemService("wifi")).getConnectionInfo().getMacAddress();
        localObject = str;
        if (TextUtils.isEmpty(str))
        {
          localObject = ag();
          return localObject;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return null;
  }
  
  public static String w()
  {
    return d;
  }
  
  public static String x()
  {
    return ah.a("mp_version");
  }
  
  public static int y()
  {
    return ah.b("is_soft", 0);
  }
  
  public static String z()
  {
    return ah.a("decoder_type");
  }
  
  public String d(Context paramContext)
  {
    try
    {
      int m = android.os.Process.myPid();
      paramContext = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
      while (paramContext.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        if (localRunningAppProcessInfo.pid == m) {
          return localRunningAppProcessInfo.processName;
        }
      }
      return "";
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }
}
