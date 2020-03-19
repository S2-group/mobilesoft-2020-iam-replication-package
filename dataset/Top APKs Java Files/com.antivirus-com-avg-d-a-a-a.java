package com.avg.d.a.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.TrafficStats;
import android.os.Build.VERSION;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class a
{
  private static double a(Class paramClass1, Object paramObject1, Class paramClass2, Object paramObject2)
  {
    paramClass1 = paramClass1.getMethod("getAveragePower", new Class[] { String.class });
    double d1 = ((Double)paramClass1.invoke(paramObject1, new Object[] { "wifi.active" })).doubleValue() / 3600.0D;
    double d2 = ((Double)paramClass1.invoke(paramObject1, new Object[] { "radio.active" })).doubleValue() / 3600.0D;
    long l2 = ((Long)paramClass2.getMethod("getMobileTcpBytesReceived", new Class[] { Integer.TYPE }).invoke(paramObject2, new Object[] { Integer.valueOf(0) })).longValue() + ((Long)paramClass2.getMethod("getMobileTcpBytesSent", new Class[] { Integer.TYPE }).invoke(paramObject2, new Object[] { Integer.valueOf(0) })).longValue();
    long l1 = ((Long)paramClass2.getMethod("getTotalTcpBytesReceived", new Class[] { Integer.TYPE }).invoke(paramObject2, new Object[] { Integer.valueOf(0) })).longValue();
    long l3 = ((Long)paramClass2.getMethod("getTotalTcpBytesSent", new Class[] { Integer.TYPE }).invoke(paramObject2, new Object[] { Integer.valueOf(0) })).longValue() + l1 - l2;
    l1 = ((Long)paramClass2.getMethod("getRadioDataUptime", (Class[])null).invoke(paramObject2, new Object[0])).longValue() / 1000L;
    if (l1 != 0L) {}
    for (l1 = 8L * l2 * 1000L / l1;; l1 = 200000L)
    {
      d2 /= l1 / 8L;
      d1 /= 125000.0D;
      if (l3 + l2 == 0L) {
        break;
      }
      return (d2 * l2 + d1 * l3) / (l2 + l3);
    }
    return 0.0D;
  }
  
  public static HashMap a(Context paramContext, Map paramMap)
  {
    if (Build.VERSION.SDK_INT < 19) {}
    for (paramContext = b(paramContext, paramMap);; paramContext = c(paramContext, paramMap))
    {
      paramMap = paramContext;
      if (paramContext == null) {
        paramMap = new HashMap();
      }
      return paramMap;
    }
  }
  
  private static void a(String[] paramArrayOfString, HashMap paramHashMap, double paramDouble)
  {
    int j = paramArrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = paramArrayOfString[i];
      if (!paramHashMap.containsKey(str)) {
        paramHashMap.put(str, Double.valueOf(paramDouble));
      }
      i += 1;
    }
  }
  
  private static void a(String[] paramArrayOfString, Map paramMap, double paramDouble)
  {
    int j = paramArrayOfString.length;
    int i = 0;
    while (i < j)
    {
      String str = paramArrayOfString[i];
      if (paramMap.containsKey(str)) {
        ((com.avg.d.a.b.a)paramMap.get(str)).n = paramDouble;
      }
      i += 1;
    }
  }
  
  /* Error */
  public static HashMap b(Context paramContext, Map paramMap)
  {
    // Byte code:
    //   0: new 83	java/util/HashMap
    //   3: dup
    //   4: invokespecial 87	java/util/HashMap:<init>	()V
    //   7: astore 28
    //   9: new 120	java/lang/StringBuilder
    //   12: dup
    //   13: invokespecial 121	java/lang/StringBuilder:<init>	()V
    //   16: ldc 123
    //   18: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   21: ldc -127
    //   23: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   29: astore 25
    //   31: new 120	java/lang/StringBuilder
    //   34: dup
    //   35: invokespecial 121	java/lang/StringBuilder:<init>	()V
    //   38: aload 25
    //   40: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   43: ldc -121
    //   45: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   51: astore 31
    //   53: new 120	java/lang/StringBuilder
    //   56: dup
    //   57: invokespecial 121	java/lang/StringBuilder:<init>	()V
    //   60: aload 25
    //   62: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: ldc -119
    //   67: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   73: astore 30
    //   75: new 120	java/lang/StringBuilder
    //   78: dup
    //   79: invokespecial 121	java/lang/StringBuilder:<init>	()V
    //   82: ldc 123
    //   84: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: ldc -117
    //   89: invokevirtual 127	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   95: astore 29
    //   97: aconst_null
    //   98: astore 24
    //   100: ldc -115
    //   102: invokestatic 145	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   105: astore 27
    //   107: ldc -109
    //   109: invokestatic 145	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   112: astore 26
    //   114: aload 25
    //   116: invokestatic 145	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   119: astore 25
    //   121: aload 31
    //   123: invokestatic 145	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   126: astore 34
    //   128: aload 30
    //   130: invokestatic 145	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   133: astore 30
    //   135: aload 29
    //   137: invokestatic 145	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   140: astore 41
    //   142: aload 30
    //   144: ldc -107
    //   146: aconst_null
    //   147: checkcast 62	[Ljava/lang/Class;
    //   150: invokevirtual 16	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   153: astore 29
    //   155: aload 30
    //   157: ldc -105
    //   159: aconst_null
    //   160: checkcast 62	[Ljava/lang/Class;
    //   163: invokevirtual 16	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   166: astore 30
    //   168: aload 25
    //   170: ldc -103
    //   172: aconst_null
    //   173: checkcast 62	[Ljava/lang/Class;
    //   176: invokevirtual 16	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   179: astore 31
    //   181: aload 25
    //   183: ldc -101
    //   185: aconst_null
    //   186: checkcast 62	[Ljava/lang/Class;
    //   189: invokevirtual 16	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   192: astore 32
    //   194: aload 34
    //   196: ldc -99
    //   198: iconst_1
    //   199: anewarray 10	java/lang/Class
    //   202: dup
    //   203: iconst_0
    //   204: getstatic 42	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   207: aastore
    //   208: invokevirtual 16	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   211: astore 33
    //   213: aload 34
    //   215: ldc -97
    //   217: iconst_1
    //   218: anewarray 10	java/lang/Class
    //   221: dup
    //   222: iconst_0
    //   223: getstatic 42	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   226: aastore
    //   227: invokevirtual 16	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   230: astore 34
    //   232: aload 25
    //   234: ldc -95
    //   236: aconst_null
    //   237: checkcast 62	[Ljava/lang/Class;
    //   240: invokevirtual 16	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   243: astore 35
    //   245: aload 27
    //   247: ldc 8
    //   249: iconst_1
    //   250: anewarray 10	java/lang/Class
    //   253: dup
    //   254: iconst_0
    //   255: ldc 12
    //   257: aastore
    //   258: invokevirtual 16	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   261: astore 36
    //   263: aload 25
    //   265: ldc -93
    //   267: iconst_1
    //   268: anewarray 10	java/lang/Class
    //   271: dup
    //   272: iconst_0
    //   273: getstatic 42	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   276: aastore
    //   277: invokevirtual 16	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   280: astore 37
    //   282: aload 25
    //   284: ldc -91
    //   286: iconst_1
    //   287: anewarray 10	java/lang/Class
    //   290: dup
    //   291: iconst_0
    //   292: getstatic 42	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   295: aastore
    //   296: invokevirtual 16	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   299: astore 38
    //   301: aload 25
    //   303: ldc -89
    //   305: iconst_2
    //   306: anewarray 10	java/lang/Class
    //   309: dup
    //   310: iconst_0
    //   311: getstatic 168	java/lang/Long:TYPE	Ljava/lang/Class;
    //   314: aastore
    //   315: dup
    //   316: iconst_1
    //   317: getstatic 42	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   320: aastore
    //   321: invokevirtual 16	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   324: astore 39
    //   326: aload 25
    //   328: ldc -86
    //   330: aconst_null
    //   331: checkcast 62	[Ljava/lang/Class;
    //   334: invokevirtual 16	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   337: astore 40
    //   339: aload 41
    //   341: ldc -84
    //   343: iconst_2
    //   344: anewarray 10	java/lang/Class
    //   347: dup
    //   348: iconst_0
    //   349: getstatic 168	java/lang/Long:TYPE	Ljava/lang/Class;
    //   352: aastore
    //   353: dup
    //   354: iconst_1
    //   355: getstatic 42	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   358: aastore
    //   359: invokevirtual 16	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   362: astore 41
    //   364: ldc -82
    //   366: invokestatic 145	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   369: ldc -80
    //   371: iconst_1
    //   372: anewarray 10	java/lang/Class
    //   375: dup
    //   376: iconst_0
    //   377: ldc -78
    //   379: aastore
    //   380: invokevirtual 181	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   383: aconst_null
    //   384: iconst_1
    //   385: anewarray 4	java/lang/Object
    //   388: dup
    //   389: iconst_0
    //   390: ldc -73
    //   392: invokestatic 145	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   395: ldc -71
    //   397: iconst_1
    //   398: anewarray 10	java/lang/Class
    //   401: dup
    //   402: iconst_0
    //   403: ldc 12
    //   405: aastore
    //   406: invokevirtual 16	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   409: aconst_null
    //   410: iconst_1
    //   411: anewarray 4	java/lang/Object
    //   414: dup
    //   415: iconst_0
    //   416: ldc -69
    //   418: aastore
    //   419: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   422: aastore
    //   423: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   426: astore 25
    //   428: ldc -115
    //   430: invokestatic 145	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   433: iconst_1
    //   434: anewarray 10	java/lang/Class
    //   437: dup
    //   438: iconst_0
    //   439: ldc -67
    //   441: aastore
    //   442: invokevirtual 193	java/lang/Class:getConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   445: iconst_1
    //   446: anewarray 4	java/lang/Object
    //   449: dup
    //   450: iconst_0
    //   451: aload_0
    //   452: aastore
    //   453: invokevirtual 199	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   456: astore 42
    //   458: ldc -55
    //   460: invokestatic 145	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   463: ldc -53
    //   465: aconst_null
    //   466: checkcast 62	[Ljava/lang/Class;
    //   469: invokevirtual 16	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   472: aload 25
    //   474: aconst_null
    //   475: checkcast 205	[Ljava/lang/Object;
    //   478: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   481: checkcast 207	[B
    //   484: checkcast 207	[B
    //   487: astore 43
    //   489: invokestatic 213	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   492: astore 25
    //   494: aload 25
    //   496: aload 43
    //   498: iconst_0
    //   499: aload 43
    //   501: arraylength
    //   502: invokevirtual 217	android/os/Parcel:unmarshall	([BII)V
    //   505: aload 25
    //   507: iconst_0
    //   508: invokevirtual 221	android/os/Parcel:setDataPosition	(I)V
    //   511: aload 26
    //   513: ldc -33
    //   515: invokevirtual 227	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   518: invokevirtual 233	java/lang/reflect/Field:getType	()Ljava/lang/Class;
    //   521: ldc -21
    //   523: iconst_1
    //   524: anewarray 10	java/lang/Class
    //   527: dup
    //   528: iconst_0
    //   529: ldc -47
    //   531: aastore
    //   532: invokevirtual 16	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   535: aload 26
    //   537: ldc -33
    //   539: invokevirtual 227	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   542: aconst_null
    //   543: invokevirtual 236	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   546: iconst_1
    //   547: anewarray 4	java/lang/Object
    //   550: dup
    //   551: iconst_0
    //   552: aload 25
    //   554: aastore
    //   555: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   558: astore 24
    //   560: aload 27
    //   562: ldc -18
    //   564: aconst_null
    //   565: checkcast 62	[Ljava/lang/Class;
    //   568: invokevirtual 16	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   571: aload 42
    //   573: aconst_null
    //   574: checkcast 205	[Ljava/lang/Object;
    //   577: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   580: checkcast 38	java/lang/Integer
    //   583: invokevirtual 242	java/lang/Integer:intValue	()I
    //   586: istore 13
    //   588: iload 13
    //   590: newarray double
    //   592: astore 43
    //   594: iload 13
    //   596: newarray long
    //   598: astore 44
    //   600: iconst_0
    //   601: istore 10
    //   603: iload 10
    //   605: iload 13
    //   607: if_icmpge +67 -> 674
    //   610: aload 43
    //   612: iload 10
    //   614: aload 27
    //   616: ldc 8
    //   618: iconst_2
    //   619: anewarray 10	java/lang/Class
    //   622: dup
    //   623: iconst_0
    //   624: ldc 12
    //   626: aastore
    //   627: dup
    //   628: iconst_1
    //   629: getstatic 42	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   632: aastore
    //   633: invokevirtual 16	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   636: aload 42
    //   638: iconst_2
    //   639: anewarray 4	java/lang/Object
    //   642: dup
    //   643: iconst_0
    //   644: ldc -12
    //   646: aastore
    //   647: dup
    //   648: iconst_1
    //   649: iload 10
    //   651: invokestatic 46	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   654: aastore
    //   655: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   658: checkcast 26	java/lang/Double
    //   661: invokevirtual 30	java/lang/Double:doubleValue	()D
    //   664: dastore
    //   665: iload 10
    //   667: iconst_1
    //   668: iadd
    //   669: istore 10
    //   671: goto -68 -> 603
    //   674: aload 27
    //   676: aload 42
    //   678: aload 26
    //   680: aload 24
    //   682: invokestatic 246	com/avg/d/a/a/a:a	(Ljava/lang/Class;Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/Object;)D
    //   685: dstore 6
    //   687: aload 26
    //   689: ldc -8
    //   691: iconst_2
    //   692: anewarray 10	java/lang/Class
    //   695: dup
    //   696: iconst_0
    //   697: getstatic 168	java/lang/Long:TYPE	Ljava/lang/Class;
    //   700: aastore
    //   701: dup
    //   702: iconst_1
    //   703: getstatic 42	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   706: aastore
    //   707: invokevirtual 16	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   710: aload 24
    //   712: iconst_2
    //   713: anewarray 4	java/lang/Object
    //   716: dup
    //   717: iconst_0
    //   718: invokestatic 253	android/os/SystemClock:elapsedRealtime	()J
    //   721: ldc2_w 63
    //   724: lmul
    //   725: invokestatic 256	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   728: aastore
    //   729: dup
    //   730: iconst_1
    //   731: iconst_0
    //   732: invokestatic 46	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   735: aastore
    //   736: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   739: checkcast 48	java/lang/Long
    //   742: invokevirtual 52	java/lang/Long:longValue	()J
    //   745: lstore 18
    //   747: aload_0
    //   748: ldc_w 258
    //   751: invokevirtual 262	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   754: checkcast 264	android/hardware/SensorManager
    //   757: astore 45
    //   759: aload 26
    //   761: ldc -8
    //   763: iconst_2
    //   764: anewarray 10	java/lang/Class
    //   767: dup
    //   768: iconst_0
    //   769: getstatic 168	java/lang/Long:TYPE	Ljava/lang/Class;
    //   772: aastore
    //   773: dup
    //   774: iconst_1
    //   775: getstatic 42	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   778: aastore
    //   779: invokevirtual 16	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   782: aload 24
    //   784: iconst_2
    //   785: anewarray 4	java/lang/Object
    //   788: dup
    //   789: iconst_0
    //   790: invokestatic 253	android/os/SystemClock:elapsedRealtime	()J
    //   793: ldc2_w 63
    //   796: lmul
    //   797: invokestatic 256	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   800: aastore
    //   801: dup
    //   802: iconst_1
    //   803: iconst_0
    //   804: invokestatic 46	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   807: aastore
    //   808: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   811: checkcast 48	java/lang/Long
    //   814: invokevirtual 52	java/lang/Long:longValue	()J
    //   817: lstore 20
    //   819: aload 26
    //   821: ldc_w 266
    //   824: aconst_null
    //   825: checkcast 62	[Ljava/lang/Class;
    //   828: invokevirtual 16	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   831: aload 24
    //   833: aconst_null
    //   834: checkcast 205	[Ljava/lang/Object;
    //   837: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   840: checkcast 268	android/util/SparseArray
    //   843: astore 46
    //   845: aload 46
    //   847: invokevirtual 271	android/util/SparseArray:size	()I
    //   850: istore 14
    //   852: aconst_null
    //   853: astore 24
    //   855: iconst_0
    //   856: istore 10
    //   858: aconst_null
    //   859: astore 27
    //   861: iload 10
    //   863: iload 14
    //   865: if_icmpge +921 -> 1786
    //   868: aload 46
    //   870: iload 10
    //   872: invokevirtual 275	android/util/SparseArray:valueAt	(I)Ljava/lang/Object;
    //   875: astore 47
    //   877: dconst_0
    //   878: dstore_2
    //   879: aload 32
    //   881: aload 47
    //   883: aconst_null
    //   884: checkcast 205	[Ljava/lang/Object;
    //   887: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   890: checkcast 105	java/util/Map
    //   893: astore 48
    //   895: aload 31
    //   897: aload 47
    //   899: aconst_null
    //   900: checkcast 205	[Ljava/lang/Object;
    //   903: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   906: checkcast 38	java/lang/Integer
    //   909: invokevirtual 242	java/lang/Integer:intValue	()I
    //   912: istore 15
    //   914: aload 24
    //   916: astore 26
    //   918: dload_2
    //   919: dstore 4
    //   921: aload 48
    //   923: invokeinterface 276 1 0
    //   928: ifle +224 -> 1152
    //   931: aload 48
    //   933: invokeinterface 280 1 0
    //   938: invokeinterface 286 1 0
    //   943: astore 48
    //   945: aload 24
    //   947: astore 26
    //   949: dload_2
    //   950: dstore 4
    //   952: aload 48
    //   954: invokeinterface 292 1 0
    //   959: ifeq +193 -> 1152
    //   962: aload 48
    //   964: invokeinterface 296 1 0
    //   969: checkcast 298	java/util/Map$Entry
    //   972: astore 26
    //   974: aload 26
    //   976: invokeinterface 301 1 0
    //   981: checkcast 12	java/lang/String
    //   984: astore 49
    //   986: aload 26
    //   988: invokeinterface 304 1 0
    //   993: astore 26
    //   995: aload 24
    //   997: ifnonnull +825 -> 1822
    //   1000: aload 26
    //   1002: invokevirtual 307	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1005: ldc_w 309
    //   1008: iconst_2
    //   1009: anewarray 10	java/lang/Class
    //   1012: dup
    //   1013: iconst_0
    //   1014: getstatic 42	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   1017: aastore
    //   1018: dup
    //   1019: iconst_1
    //   1020: getstatic 42	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   1023: aastore
    //   1024: invokevirtual 16	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   1027: astore 24
    //   1029: aload 33
    //   1031: aload 26
    //   1033: iconst_1
    //   1034: anewarray 4	java/lang/Object
    //   1037: dup
    //   1038: iconst_0
    //   1039: iconst_0
    //   1040: invokestatic 46	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1043: aastore
    //   1044: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1047: checkcast 48	java/lang/Long
    //   1050: invokevirtual 52	java/lang/Long:longValue	()J
    //   1053: lstore 16
    //   1055: aload 34
    //   1057: aload 26
    //   1059: iconst_1
    //   1060: anewarray 4	java/lang/Object
    //   1063: dup
    //   1064: iconst_0
    //   1065: iconst_0
    //   1066: invokestatic 46	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1069: aastore
    //   1070: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1073: checkcast 48	java/lang/Long
    //   1076: invokevirtual 52	java/lang/Long:longValue	()J
    //   1079: lstore 22
    //   1081: iconst_0
    //   1082: istore 12
    //   1084: iconst_0
    //   1085: istore 11
    //   1087: iload 11
    //   1089: iload 13
    //   1091: if_icmpge +737 -> 1828
    //   1094: aload 44
    //   1096: iload 11
    //   1098: aload 24
    //   1100: aload 26
    //   1102: iconst_2
    //   1103: anewarray 4	java/lang/Object
    //   1106: dup
    //   1107: iconst_0
    //   1108: iload 11
    //   1110: invokestatic 46	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1113: aastore
    //   1114: dup
    //   1115: iconst_1
    //   1116: iconst_0
    //   1117: invokestatic 46	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1120: aastore
    //   1121: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1124: checkcast 48	java/lang/Long
    //   1127: invokevirtual 52	java/lang/Long:longValue	()J
    //   1130: lastore
    //   1131: iload 12
    //   1133: i2l
    //   1134: aload 44
    //   1136: iload 11
    //   1138: laload
    //   1139: ladd
    //   1140: l2i
    //   1141: istore 12
    //   1143: iload 11
    //   1145: iconst_1
    //   1146: iadd
    //   1147: istore 11
    //   1149: goto -62 -> 1087
    //   1152: dload 4
    //   1154: ldc2_w 310
    //   1157: ddiv
    //   1158: dstore_2
    //   1159: aload 35
    //   1161: aload 47
    //   1163: aconst_null
    //   1164: checkcast 205	[Ljava/lang/Object;
    //   1167: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1170: checkcast 105	java/util/Map
    //   1173: invokeinterface 280 1 0
    //   1178: invokeinterface 286 1 0
    //   1183: astore 48
    //   1185: lconst_0
    //   1186: lstore 16
    //   1188: aload 27
    //   1190: astore 24
    //   1192: aload 48
    //   1194: invokeinterface 292 1 0
    //   1199: ifeq +117 -> 1316
    //   1202: aload 48
    //   1204: invokeinterface 296 1 0
    //   1209: checkcast 298	java/util/Map$Entry
    //   1212: invokeinterface 304 1 0
    //   1217: astore 49
    //   1219: aload 24
    //   1221: astore 27
    //   1223: aload 24
    //   1225: ifnonnull +26 -> 1251
    //   1228: aload 49
    //   1230: invokevirtual 307	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1233: ldc_w 313
    //   1236: iconst_1
    //   1237: anewarray 10	java/lang/Class
    //   1240: dup
    //   1241: iconst_0
    //   1242: getstatic 42	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   1245: aastore
    //   1246: invokevirtual 16	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   1249: astore 27
    //   1251: aload 27
    //   1253: aload 49
    //   1255: iconst_1
    //   1256: anewarray 4	java/lang/Object
    //   1259: dup
    //   1260: iconst_0
    //   1261: iconst_0
    //   1262: invokestatic 46	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1265: aastore
    //   1266: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1269: astore 24
    //   1271: aload 24
    //   1273: ifnull +552 -> 1825
    //   1276: lload 16
    //   1278: aload 41
    //   1280: aload 24
    //   1282: iconst_2
    //   1283: anewarray 4	java/lang/Object
    //   1286: dup
    //   1287: iconst_0
    //   1288: lload 18
    //   1290: invokestatic 256	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1293: aastore
    //   1294: dup
    //   1295: iconst_1
    //   1296: iconst_0
    //   1297: invokestatic 46	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1300: aastore
    //   1301: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1304: checkcast 48	java/lang/Long
    //   1307: invokevirtual 52	java/lang/Long:longValue	()J
    //   1310: ladd
    //   1311: lstore 16
    //   1313: goto +573 -> 1886
    //   1316: lload 16
    //   1318: ldc2_w 63
    //   1321: ldiv
    //   1322: l2d
    //   1323: aload 36
    //   1325: aload 42
    //   1327: iconst_1
    //   1328: anewarray 4	java/lang/Object
    //   1331: dup
    //   1332: iconst_0
    //   1333: ldc_w 315
    //   1336: aastore
    //   1337: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1340: checkcast 26	java/lang/Double
    //   1343: invokevirtual 30	java/lang/Double:doubleValue	()D
    //   1346: dmul
    //   1347: ldc2_w 310
    //   1350: ddiv
    //   1351: dstore 4
    //   1353: aload 37
    //   1355: aload 47
    //   1357: iconst_1
    //   1358: anewarray 4	java/lang/Object
    //   1361: dup
    //   1362: iconst_0
    //   1363: iconst_0
    //   1364: invokestatic 46	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1367: aastore
    //   1368: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1371: checkcast 48	java/lang/Long
    //   1374: invokevirtual 52	java/lang/Long:longValue	()J
    //   1377: lstore 16
    //   1379: aload 38
    //   1381: aload 47
    //   1383: iconst_1
    //   1384: anewarray 4	java/lang/Object
    //   1387: dup
    //   1388: iconst_0
    //   1389: iconst_0
    //   1390: invokestatic 46	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1393: aastore
    //   1394: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1397: checkcast 48	java/lang/Long
    //   1400: invokevirtual 52	java/lang/Long:longValue	()J
    //   1403: lstore 22
    //   1405: dload_2
    //   1406: dload 4
    //   1408: dadd
    //   1409: lload 16
    //   1411: lload 22
    //   1413: ladd
    //   1414: l2d
    //   1415: dload 6
    //   1417: dmul
    //   1418: dadd
    //   1419: dstore_2
    //   1420: aload 39
    //   1422: aload 47
    //   1424: iconst_2
    //   1425: anewarray 4	java/lang/Object
    //   1428: dup
    //   1429: iconst_0
    //   1430: lload 18
    //   1432: invokestatic 256	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1435: aastore
    //   1436: dup
    //   1437: iconst_1
    //   1438: iconst_0
    //   1439: invokestatic 46	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1442: aastore
    //   1443: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1446: checkcast 48	java/lang/Long
    //   1449: invokevirtual 52	java/lang/Long:longValue	()J
    //   1452: ldc2_w 63
    //   1455: ldiv
    //   1456: l2d
    //   1457: dstore 4
    //   1459: aload 36
    //   1461: aload 42
    //   1463: iconst_1
    //   1464: anewarray 4	java/lang/Object
    //   1467: dup
    //   1468: iconst_0
    //   1469: ldc_w 317
    //   1472: aastore
    //   1473: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1476: checkcast 26	java/lang/Double
    //   1479: invokevirtual 30	java/lang/Double:doubleValue	()D
    //   1482: dstore 8
    //   1484: dload_2
    //   1485: dload 4
    //   1487: dload 8
    //   1489: dmul
    //   1490: ldc2_w 310
    //   1493: ddiv
    //   1494: dadd
    //   1495: dstore_2
    //   1496: aload 40
    //   1498: aload 47
    //   1500: iconst_0
    //   1501: anewarray 4	java/lang/Object
    //   1504: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1507: checkcast 105	java/util/Map
    //   1510: invokeinterface 280 1 0
    //   1515: invokeinterface 286 1 0
    //   1520: astore 27
    //   1522: dload_2
    //   1523: dstore 4
    //   1525: aload 27
    //   1527: invokeinterface 292 1 0
    //   1532: ifeq +198 -> 1730
    //   1535: aload 27
    //   1537: invokeinterface 296 1 0
    //   1542: checkcast 298	java/util/Map$Entry
    //   1545: invokeinterface 304 1 0
    //   1550: astore 47
    //   1552: aload 29
    //   1554: aload 47
    //   1556: iconst_0
    //   1557: anewarray 4	java/lang/Object
    //   1560: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1563: checkcast 38	java/lang/Integer
    //   1566: invokevirtual 242	java/lang/Integer:intValue	()I
    //   1569: istore 11
    //   1571: aload 41
    //   1573: aload 30
    //   1575: aload 47
    //   1577: iconst_0
    //   1578: anewarray 4	java/lang/Object
    //   1581: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1584: iconst_2
    //   1585: anewarray 4	java/lang/Object
    //   1588: dup
    //   1589: iconst_0
    //   1590: lload 20
    //   1592: invokestatic 256	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1595: aastore
    //   1596: dup
    //   1597: iconst_1
    //   1598: iconst_0
    //   1599: invokestatic 46	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1602: aastore
    //   1603: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1606: checkcast 48	java/lang/Long
    //   1609: invokevirtual 52	java/lang/Long:longValue	()J
    //   1612: lstore 16
    //   1614: dconst_0
    //   1615: dstore_2
    //   1616: iload 11
    //   1618: tableswitch	default:+275->1893, -10000:+85->1703
    //   1636: aload 45
    //   1638: iload 11
    //   1640: invokevirtual 321	android/hardware/SensorManager:getDefaultSensor	(I)Landroid/hardware/Sensor;
    //   1643: astore 47
    //   1645: aload 47
    //   1647: ifnull +10 -> 1657
    //   1650: aload 47
    //   1652: invokevirtual 327	android/hardware/Sensor:getPower	()F
    //   1655: f2d
    //   1656: dstore_2
    //   1657: dload_2
    //   1658: lload 16
    //   1660: l2d
    //   1661: dmul
    //   1662: ldc2_w 310
    //   1665: ddiv
    //   1666: dload 4
    //   1668: dadd
    //   1669: dstore 4
    //   1671: goto -146 -> 1525
    //   1674: astore 27
    //   1676: aload 27
    //   1678: invokestatic 332	com/avg/toolkit/g/a:a	(Ljava/lang/Exception;)V
    //   1681: goto -185 -> 1496
    //   1684: astore_1
    //   1685: aload 25
    //   1687: astore_0
    //   1688: aload_1
    //   1689: invokestatic 332	com/avg/toolkit/g/a:a	(Ljava/lang/Exception;)V
    //   1692: aload_0
    //   1693: ifnull +7 -> 1700
    //   1696: aload_0
    //   1697: invokevirtual 335	android/os/Parcel:recycle	()V
    //   1700: aload 28
    //   1702: areturn
    //   1703: aload 36
    //   1705: aload 42
    //   1707: iconst_1
    //   1708: anewarray 4	java/lang/Object
    //   1711: dup
    //   1712: iconst_0
    //   1713: ldc_w 337
    //   1716: aastore
    //   1717: invokevirtual 24	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1720: checkcast 26	java/lang/Double
    //   1723: invokevirtual 30	java/lang/Double:doubleValue	()D
    //   1726: dstore_2
    //   1727: goto -70 -> 1657
    //   1730: aload_0
    //   1731: invokevirtual 341	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   1734: iload 15
    //   1736: invokevirtual 347	android/content/pm/PackageManager:getPackagesForUid	(I)[Ljava/lang/String;
    //   1739: astore 27
    //   1741: aload 27
    //   1743: ifnull +153 -> 1896
    //   1746: aload_1
    //   1747: ifnonnull +15 -> 1762
    //   1750: aload 27
    //   1752: aload 28
    //   1754: dload 4
    //   1756: invokestatic 349	com/avg/d/a/a/a:a	([Ljava/lang/String;Ljava/util/HashMap;D)V
    //   1759: goto +137 -> 1896
    //   1762: aload 27
    //   1764: aload_1
    //   1765: dload 4
    //   1767: invokestatic 351	com/avg/d/a/a/a:a	([Ljava/lang/String;Ljava/util/Map;D)V
    //   1770: goto +126 -> 1896
    //   1773: astore_0
    //   1774: aload 25
    //   1776: ifnull +8 -> 1784
    //   1779: aload 25
    //   1781: invokevirtual 335	android/os/Parcel:recycle	()V
    //   1784: aload_0
    //   1785: athrow
    //   1786: aload 25
    //   1788: ifnull -88 -> 1700
    //   1791: aload 25
    //   1793: invokevirtual 335	android/os/Parcel:recycle	()V
    //   1796: aload 28
    //   1798: areturn
    //   1799: astore_0
    //   1800: aconst_null
    //   1801: astore 25
    //   1803: goto -29 -> 1774
    //   1806: astore_1
    //   1807: aload_0
    //   1808: astore 25
    //   1810: aload_1
    //   1811: astore_0
    //   1812: goto -38 -> 1774
    //   1815: astore_1
    //   1816: aload 24
    //   1818: astore_0
    //   1819: goto -131 -> 1688
    //   1822: goto -793 -> 1029
    //   1825: goto +61 -> 1886
    //   1828: iload 12
    //   1830: ifne +3 -> 1833
    //   1833: dconst_0
    //   1834: dstore 4
    //   1836: iconst_0
    //   1837: istore 11
    //   1839: iload 11
    //   1841: iload 13
    //   1843: if_icmpge +35 -> 1878
    //   1846: dload 4
    //   1848: dconst_1
    //   1849: ldc2_w 352
    //   1852: lload 16
    //   1854: lload 22
    //   1856: ladd
    //   1857: lmul
    //   1858: l2d
    //   1859: dmul
    //   1860: aload 43
    //   1862: iload 11
    //   1864: daload
    //   1865: dmul
    //   1866: dadd
    //   1867: dstore 4
    //   1869: iload 11
    //   1871: iconst_1
    //   1872: iadd
    //   1873: istore 11
    //   1875: goto -36 -> 1839
    //   1878: dload_2
    //   1879: dload 4
    //   1881: dadd
    //   1882: dstore_2
    //   1883: goto -938 -> 945
    //   1886: aload 27
    //   1888: astore 24
    //   1890: goto -698 -> 1192
    //   1893: goto -257 -> 1636
    //   1896: iload 10
    //   1898: iconst_1
    //   1899: iadd
    //   1900: istore 10
    //   1902: aload 24
    //   1904: astore 27
    //   1906: aload 26
    //   1908: astore 24
    //   1910: goto -1049 -> 861
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1913	0	paramContext	Context
    //   0	1913	1	paramMap	Map
    //   878	1005	2	d1	double
    //   919	961	4	d2	double
    //   685	731	6	d3	double
    //   1482	6	8	d4	double
    //   601	1300	10	i	int
    //   1085	789	11	j	int
    //   1082	747	12	k	int
    //   586	1258	13	m	int
    //   850	16	14	n	int
    //   912	823	15	i1	int
    //   1053	800	16	l1	long
    //   745	686	18	l2	long
    //   817	774	20	l3	long
    //   1079	776	22	l4	long
    //   98	1811	24	localObject1	Object
    //   29	1780	25	localObject2	Object
    //   112	1795	26	localObject3	Object
    //   105	1431	27	localObject4	Object
    //   1674	3	27	localException	Exception
    //   1739	166	27	localObject5	Object
    //   7	1790	28	localHashMap	HashMap
    //   95	1458	29	localObject6	Object
    //   73	1501	30	localObject7	Object
    //   51	845	31	localObject8	Object
    //   192	688	32	localMethod1	Method
    //   211	819	33	localMethod2	Method
    //   126	930	34	localObject9	Object
    //   243	917	35	localMethod3	Method
    //   261	1443	36	localMethod4	Method
    //   280	1074	37	localMethod5	Method
    //   299	1081	38	localMethod6	Method
    //   324	1097	39	localMethod7	Method
    //   337	1160	40	localMethod8	Method
    //   140	1432	41	localObject10	Object
    //   456	1250	42	localObject11	Object
    //   487	1374	43	localObject12	Object
    //   598	537	44	arrayOfLong	long[]
    //   757	880	45	localSensorManager	android.hardware.SensorManager
    //   843	26	46	localSparseArray	android.util.SparseArray
    //   875	776	47	localObject13	Object
    //   893	310	48	localObject14	Object
    //   984	270	49	localObject15	Object
    // Exception table:
    //   from	to	target	type
    //   1420	1484	1674	java/lang/Exception
    //   494	600	1684	java/lang/Exception
    //   610	665	1684	java/lang/Exception
    //   674	852	1684	java/lang/Exception
    //   868	877	1684	java/lang/Exception
    //   879	914	1684	java/lang/Exception
    //   921	945	1684	java/lang/Exception
    //   952	995	1684	java/lang/Exception
    //   1000	1029	1684	java/lang/Exception
    //   1029	1081	1684	java/lang/Exception
    //   1094	1131	1684	java/lang/Exception
    //   1152	1185	1684	java/lang/Exception
    //   1192	1219	1684	java/lang/Exception
    //   1228	1251	1684	java/lang/Exception
    //   1251	1271	1684	java/lang/Exception
    //   1276	1313	1684	java/lang/Exception
    //   1316	1405	1684	java/lang/Exception
    //   1496	1522	1684	java/lang/Exception
    //   1525	1614	1684	java/lang/Exception
    //   1636	1645	1684	java/lang/Exception
    //   1650	1657	1684	java/lang/Exception
    //   1657	1671	1684	java/lang/Exception
    //   1676	1681	1684	java/lang/Exception
    //   1703	1727	1684	java/lang/Exception
    //   1730	1741	1684	java/lang/Exception
    //   1750	1759	1684	java/lang/Exception
    //   1762	1770	1684	java/lang/Exception
    //   494	600	1773	finally
    //   610	665	1773	finally
    //   674	852	1773	finally
    //   868	877	1773	finally
    //   879	914	1773	finally
    //   921	945	1773	finally
    //   952	995	1773	finally
    //   1000	1029	1773	finally
    //   1029	1081	1773	finally
    //   1094	1131	1773	finally
    //   1152	1185	1773	finally
    //   1192	1219	1773	finally
    //   1228	1251	1773	finally
    //   1251	1271	1773	finally
    //   1276	1313	1773	finally
    //   1316	1405	1773	finally
    //   1420	1484	1773	finally
    //   1496	1522	1773	finally
    //   1525	1614	1773	finally
    //   1636	1645	1773	finally
    //   1650	1657	1773	finally
    //   1657	1671	1773	finally
    //   1676	1681	1773	finally
    //   1703	1727	1773	finally
    //   1730	1741	1773	finally
    //   1750	1759	1773	finally
    //   1762	1770	1773	finally
    //   100	494	1799	finally
    //   1688	1692	1806	finally
    //   100	494	1815	java/lang/Exception
  }
  
  private static HashMap c(Context paramContext, Map paramMap)
  {
    paramMap = new HashMap();
    for (;;)
    {
      double d;
      try
      {
        paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
        if (!paramContext.hasNext()) {
          break label115;
        }
        localObject = (ApplicationInfo)paramContext.next();
        int i = ((ApplicationInfo)localObject).uid;
        d = TrafficStats.getUidRxBytes(i) + TrafficStats.getUidTxBytes(i);
        localObject = ((ApplicationInfo)localObject).packageName;
        if (paramMap.get(localObject) == null) {
          break label122;
        }
        d = ((Double)paramMap.get(localObject)).doubleValue() + d;
      }
      catch (Exception paramContext)
      {
        Object localObject;
        com.avg.toolkit.g.a.a(paramContext);
      }
      paramMap.put(localObject, Double.valueOf(d));
      continue;
      label115:
      return paramMap;
      label122:
      do
      {
        d = 0.0D;
        break;
      } while (d <= 0.0D);
    }
  }
}
