package com.avast.android.cleaner.analyzers.battery;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import com.avast.android.cleaner.analyzers.data.ApplicationData;
import com.avast.android.cleaner.batteryoptimizer.BatteryAndDataUtils;
import eu.inmite.android.fw.DebugLog;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class BatteryPowerSampler
{
  private static double a(Class<?> paramClass1, Object paramObject1, Class<?> paramClass2, Object paramObject2)
    throws Exception
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
  
  private static HashMap<String, Double> a(Context paramContext)
  {
    HashMap localHashMap = new HashMap();
    for (;;)
    {
      double d;
      try
      {
        Iterator localIterator = paramContext.getPackageManager().getInstalledApplications(0).iterator();
        if (!localIterator.hasNext()) {
          break label114;
        }
        localObject = (ApplicationInfo)localIterator.next();
        d = BatteryAndDataUtils.a(paramContext, ((ApplicationInfo)localObject).uid);
        localObject = ((ApplicationInfo)localObject).packageName;
        if (localHashMap.get(localObject) == null) {
          break label121;
        }
        d = ((Double)localHashMap.get(localObject)).doubleValue() + d;
      }
      catch (Exception paramContext)
      {
        Object localObject;
        DebugLog.e("BatteryPowerSampler", "getPowerFromNetworkOnly() throws exception: ", paramContext);
      }
      localHashMap.put(localObject, Double.valueOf(d));
      continue;
      label114:
      return localHashMap;
      label121:
      do
      {
        d = 0.0D;
        break;
      } while (d <= 0.0D);
    }
  }
  
  public static HashMap<String, Double> a(Context paramContext, Map<String, ApplicationData> paramMap)
  {
    if (Build.VERSION.SDK_INT < 19) {}
    for (paramContext = b(paramContext, paramMap);; paramContext = a(paramContext))
    {
      paramMap = paramContext;
      if (paramContext == null) {
        paramMap = new HashMap();
      }
      return paramMap;
    }
  }
  
  private static void a(String[] paramArrayOfString, HashMap<String, Double> paramHashMap, double paramDouble)
  {
    int j = paramArrayOfString.length;
    int i = 0;
    if (i < j)
    {
      String str = paramArrayOfString[i];
      if (!paramHashMap.containsKey(str))
      {
        DebugLog.b("BatteryPowerSampler", ">>>> " + str + " battery power: " + paramDouble);
        paramHashMap.put(str, Double.valueOf(paramDouble));
      }
      for (;;)
      {
        i += 1;
        break;
        DebugLog.b("BatteryPowerSampler", "!!!!!!!!!!!!!!!!! duplicate: " + str);
      }
    }
  }
  
  private static void a(String[] paramArrayOfString, Map<String, ApplicationData> paramMap, double paramDouble)
  {
    int j = paramArrayOfString.length;
    int i = 0;
    if (i < j)
    {
      String str = paramArrayOfString[i];
      if (paramMap.containsKey(str))
      {
        DebugLog.b("BatteryPowerSampler", ">>>> " + str + " battery power: " + paramDouble);
        ((ApplicationData)paramMap.get(str)).e = paramDouble;
      }
      for (;;)
      {
        i += 1;
        break;
        DebugLog.b("BatteryPowerSampler", "!!!!!!!!!!!!!!!!! duplicate: " + str);
      }
    }
  }
  
  /* Error */
  public static HashMap<String, Double> b(Context paramContext, Map<String, ApplicationData> paramMap)
  {
    // Byte code:
    //   0: new 79	java/util/HashMap
    //   3: dup
    //   4: invokespecial 83	java/util/HashMap:<init>	()V
    //   7: astore 28
    //   9: new 167	java/lang/StringBuilder
    //   12: dup
    //   13: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   16: ldc -54
    //   18: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   21: ldc -52
    //   23: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   26: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   29: astore 25
    //   31: new 167	java/lang/StringBuilder
    //   34: dup
    //   35: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   38: aload 25
    //   40: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   43: ldc -50
    //   45: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   51: astore 31
    //   53: new 167	java/lang/StringBuilder
    //   56: dup
    //   57: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   60: aload 25
    //   62: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: ldc -48
    //   67: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   73: astore 30
    //   75: new 167	java/lang/StringBuilder
    //   78: dup
    //   79: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   82: ldc -54
    //   84: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: ldc -46
    //   89: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   92: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   95: astore 29
    //   97: aconst_null
    //   98: astore 24
    //   100: ldc -44
    //   102: invokestatic 216	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   105: astore 27
    //   107: ldc -38
    //   109: invokestatic 216	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   112: astore 26
    //   114: aload 25
    //   116: invokestatic 216	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   119: astore 25
    //   121: aload 31
    //   123: invokestatic 216	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   126: astore 34
    //   128: aload 30
    //   130: invokestatic 216	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   133: astore 30
    //   135: aload 29
    //   137: invokestatic 216	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   140: astore 41
    //   142: aload 30
    //   144: ldc -36
    //   146: aconst_null
    //   147: checkcast 64	[Ljava/lang/Class;
    //   150: invokevirtual 18	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   153: astore 29
    //   155: aload 30
    //   157: ldc -34
    //   159: aconst_null
    //   160: checkcast 64	[Ljava/lang/Class;
    //   163: invokevirtual 18	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   166: astore 30
    //   168: aload 25
    //   170: ldc -32
    //   172: aconst_null
    //   173: checkcast 64	[Ljava/lang/Class;
    //   176: invokevirtual 18	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   179: astore 31
    //   181: aload 25
    //   183: ldc -30
    //   185: aconst_null
    //   186: checkcast 64	[Ljava/lang/Class;
    //   189: invokevirtual 18	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   192: astore 32
    //   194: aload 34
    //   196: ldc -28
    //   198: iconst_1
    //   199: anewarray 12	java/lang/Class
    //   202: dup
    //   203: iconst_0
    //   204: getstatic 44	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   207: aastore
    //   208: invokevirtual 18	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   211: astore 33
    //   213: aload 34
    //   215: ldc -26
    //   217: iconst_1
    //   218: anewarray 12	java/lang/Class
    //   221: dup
    //   222: iconst_0
    //   223: getstatic 44	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   226: aastore
    //   227: invokevirtual 18	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   230: astore 34
    //   232: aload 25
    //   234: ldc -24
    //   236: aconst_null
    //   237: checkcast 64	[Ljava/lang/Class;
    //   240: invokevirtual 18	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   243: astore 35
    //   245: aload 27
    //   247: ldc 10
    //   249: iconst_1
    //   250: anewarray 12	java/lang/Class
    //   253: dup
    //   254: iconst_0
    //   255: ldc 14
    //   257: aastore
    //   258: invokevirtual 18	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   261: astore 36
    //   263: aload 25
    //   265: ldc -22
    //   267: iconst_1
    //   268: anewarray 12	java/lang/Class
    //   271: dup
    //   272: iconst_0
    //   273: getstatic 44	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   276: aastore
    //   277: invokevirtual 18	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   280: astore 37
    //   282: aload 25
    //   284: ldc -20
    //   286: iconst_1
    //   287: anewarray 12	java/lang/Class
    //   290: dup
    //   291: iconst_0
    //   292: getstatic 44	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   295: aastore
    //   296: invokevirtual 18	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   299: astore 38
    //   301: aload 25
    //   303: ldc -18
    //   305: iconst_2
    //   306: anewarray 12	java/lang/Class
    //   309: dup
    //   310: iconst_0
    //   311: getstatic 239	java/lang/Long:TYPE	Ljava/lang/Class;
    //   314: aastore
    //   315: dup
    //   316: iconst_1
    //   317: getstatic 44	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   320: aastore
    //   321: invokevirtual 18	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   324: astore 39
    //   326: aload 25
    //   328: ldc -15
    //   330: aconst_null
    //   331: checkcast 64	[Ljava/lang/Class;
    //   334: invokevirtual 18	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   337: astore 40
    //   339: aload 41
    //   341: ldc -13
    //   343: iconst_2
    //   344: anewarray 12	java/lang/Class
    //   347: dup
    //   348: iconst_0
    //   349: getstatic 239	java/lang/Long:TYPE	Ljava/lang/Class;
    //   352: aastore
    //   353: dup
    //   354: iconst_1
    //   355: getstatic 44	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   358: aastore
    //   359: invokevirtual 18	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   362: astore 41
    //   364: ldc -11
    //   366: invokestatic 216	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   369: ldc -9
    //   371: iconst_1
    //   372: anewarray 12	java/lang/Class
    //   375: dup
    //   376: iconst_0
    //   377: ldc -7
    //   379: aastore
    //   380: invokevirtual 252	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   383: aconst_null
    //   384: iconst_1
    //   385: anewarray 4	java/lang/Object
    //   388: dup
    //   389: iconst_0
    //   390: ldc -2
    //   392: invokestatic 216	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   395: ldc_w 256
    //   398: iconst_1
    //   399: anewarray 12	java/lang/Class
    //   402: dup
    //   403: iconst_0
    //   404: ldc 14
    //   406: aastore
    //   407: invokevirtual 18	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   410: aconst_null
    //   411: iconst_1
    //   412: anewarray 4	java/lang/Object
    //   415: dup
    //   416: iconst_0
    //   417: ldc_w 258
    //   420: aastore
    //   421: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   424: aastore
    //   425: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   428: astore 25
    //   430: ldc -44
    //   432: invokestatic 216	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   435: iconst_1
    //   436: anewarray 12	java/lang/Class
    //   439: dup
    //   440: iconst_0
    //   441: ldc 85
    //   443: aastore
    //   444: invokevirtual 262	java/lang/Class:getConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   447: iconst_1
    //   448: anewarray 4	java/lang/Object
    //   451: dup
    //   452: iconst_0
    //   453: aload_0
    //   454: aastore
    //   455: invokevirtual 268	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   458: astore 42
    //   460: ldc_w 270
    //   463: invokestatic 216	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   466: ldc_w 272
    //   469: aconst_null
    //   470: checkcast 64	[Ljava/lang/Class;
    //   473: invokevirtual 18	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   476: aload 25
    //   478: aconst_null
    //   479: checkcast 274	[Ljava/lang/Object;
    //   482: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   485: checkcast 276	[B
    //   488: checkcast 276	[B
    //   491: astore 43
    //   493: invokestatic 282	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   496: astore 25
    //   498: aload 25
    //   500: aload 43
    //   502: iconst_0
    //   503: aload 43
    //   505: arraylength
    //   506: invokevirtual 286	android/os/Parcel:unmarshall	([BII)V
    //   509: aload 25
    //   511: iconst_0
    //   512: invokevirtual 290	android/os/Parcel:setDataPosition	(I)V
    //   515: aload 26
    //   517: ldc_w 292
    //   520: invokevirtual 296	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   523: invokevirtual 302	java/lang/reflect/Field:getType	()Ljava/lang/Class;
    //   526: ldc_w 304
    //   529: iconst_1
    //   530: anewarray 12	java/lang/Class
    //   533: dup
    //   534: iconst_0
    //   535: ldc_w 278
    //   538: aastore
    //   539: invokevirtual 18	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   542: aload 26
    //   544: ldc_w 292
    //   547: invokevirtual 296	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   550: aconst_null
    //   551: invokevirtual 305	java/lang/reflect/Field:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   554: iconst_1
    //   555: anewarray 4	java/lang/Object
    //   558: dup
    //   559: iconst_0
    //   560: aload 25
    //   562: aastore
    //   563: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   566: astore 24
    //   568: aload 27
    //   570: ldc_w 307
    //   573: aconst_null
    //   574: checkcast 64	[Ljava/lang/Class;
    //   577: invokevirtual 18	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   580: aload 42
    //   582: aconst_null
    //   583: checkcast 274	[Ljava/lang/Object;
    //   586: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   589: checkcast 40	java/lang/Integer
    //   592: invokevirtual 311	java/lang/Integer:intValue	()I
    //   595: istore 13
    //   597: iload 13
    //   599: newarray double
    //   601: astore 43
    //   603: iload 13
    //   605: newarray long
    //   607: astore 44
    //   609: iconst_0
    //   610: istore 10
    //   612: iload 10
    //   614: iload 13
    //   616: if_icmpge +68 -> 684
    //   619: aload 43
    //   621: iload 10
    //   623: aload 27
    //   625: ldc 10
    //   627: iconst_2
    //   628: anewarray 12	java/lang/Class
    //   631: dup
    //   632: iconst_0
    //   633: ldc 14
    //   635: aastore
    //   636: dup
    //   637: iconst_1
    //   638: getstatic 44	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   641: aastore
    //   642: invokevirtual 18	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   645: aload 42
    //   647: iconst_2
    //   648: anewarray 4	java/lang/Object
    //   651: dup
    //   652: iconst_0
    //   653: ldc_w 313
    //   656: aastore
    //   657: dup
    //   658: iconst_1
    //   659: iload 10
    //   661: invokestatic 48	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   664: aastore
    //   665: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   668: checkcast 28	java/lang/Double
    //   671: invokevirtual 32	java/lang/Double:doubleValue	()D
    //   674: dastore
    //   675: iload 10
    //   677: iconst_1
    //   678: iadd
    //   679: istore 10
    //   681: goto -69 -> 612
    //   684: aload 27
    //   686: aload 42
    //   688: aload 26
    //   690: aload 24
    //   692: invokestatic 315	com/avast/android/cleaner/analyzers/battery/BatteryPowerSampler:a	(Ljava/lang/Class;Ljava/lang/Object;Ljava/lang/Class;Ljava/lang/Object;)D
    //   695: dstore 6
    //   697: aload 26
    //   699: ldc_w 317
    //   702: iconst_2
    //   703: anewarray 12	java/lang/Class
    //   706: dup
    //   707: iconst_0
    //   708: getstatic 239	java/lang/Long:TYPE	Ljava/lang/Class;
    //   711: aastore
    //   712: dup
    //   713: iconst_1
    //   714: getstatic 44	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   717: aastore
    //   718: invokevirtual 18	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   721: aload 24
    //   723: iconst_2
    //   724: anewarray 4	java/lang/Object
    //   727: dup
    //   728: iconst_0
    //   729: invokestatic 322	android/os/SystemClock:elapsedRealtime	()J
    //   732: ldc2_w 65
    //   735: lmul
    //   736: invokestatic 325	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   739: aastore
    //   740: dup
    //   741: iconst_1
    //   742: iconst_0
    //   743: invokestatic 48	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   746: aastore
    //   747: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   750: checkcast 50	java/lang/Long
    //   753: invokevirtual 54	java/lang/Long:longValue	()J
    //   756: lstore 18
    //   758: ldc -117
    //   760: new 167	java/lang/StringBuilder
    //   763: dup
    //   764: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   767: ldc_w 327
    //   770: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   773: dload 6
    //   775: invokevirtual 179	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   778: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   781: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   784: pop
    //   785: aload_0
    //   786: ldc_w 329
    //   789: invokevirtual 333	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   792: checkcast 335	android/hardware/SensorManager
    //   795: astore 45
    //   797: aload 26
    //   799: ldc_w 317
    //   802: iconst_2
    //   803: anewarray 12	java/lang/Class
    //   806: dup
    //   807: iconst_0
    //   808: getstatic 239	java/lang/Long:TYPE	Ljava/lang/Class;
    //   811: aastore
    //   812: dup
    //   813: iconst_1
    //   814: getstatic 44	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   817: aastore
    //   818: invokevirtual 18	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   821: aload 24
    //   823: iconst_2
    //   824: anewarray 4	java/lang/Object
    //   827: dup
    //   828: iconst_0
    //   829: invokestatic 322	android/os/SystemClock:elapsedRealtime	()J
    //   832: ldc2_w 65
    //   835: lmul
    //   836: invokestatic 325	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   839: aastore
    //   840: dup
    //   841: iconst_1
    //   842: iconst_0
    //   843: invokestatic 48	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   846: aastore
    //   847: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   850: checkcast 50	java/lang/Long
    //   853: invokevirtual 54	java/lang/Long:longValue	()J
    //   856: lstore 20
    //   858: aload 26
    //   860: ldc_w 337
    //   863: aconst_null
    //   864: checkcast 64	[Ljava/lang/Class;
    //   867: invokevirtual 18	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   870: aload 24
    //   872: aconst_null
    //   873: checkcast 274	[Ljava/lang/Object;
    //   876: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   879: checkcast 339	android/util/SparseArray
    //   882: astore 46
    //   884: aload 46
    //   886: invokevirtual 342	android/util/SparseArray:size	()I
    //   889: istore 14
    //   891: ldc -117
    //   893: new 167	java/lang/StringBuilder
    //   896: dup
    //   897: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   900: ldc_w 344
    //   903: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   906: iload 14
    //   908: invokevirtual 347	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   911: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   914: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   917: pop
    //   918: aconst_null
    //   919: astore 24
    //   921: iconst_0
    //   922: istore 10
    //   924: aconst_null
    //   925: astore 26
    //   927: iload 10
    //   929: iload 14
    //   931: if_icmpge +1418 -> 2349
    //   934: aload 46
    //   936: iload 10
    //   938: invokevirtual 351	android/util/SparseArray:valueAt	(I)Ljava/lang/Object;
    //   941: astore 47
    //   943: dconst_0
    //   944: dstore_2
    //   945: aload 32
    //   947: aload 47
    //   949: aconst_null
    //   950: checkcast 274	[Ljava/lang/Object;
    //   953: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   956: checkcast 192	java/util/Map
    //   959: astore 48
    //   961: aload 31
    //   963: aload 47
    //   965: aconst_null
    //   966: checkcast 274	[Ljava/lang/Object;
    //   969: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   972: checkcast 40	java/lang/Integer
    //   975: invokevirtual 311	java/lang/Integer:intValue	()I
    //   978: istore 15
    //   980: ldc -117
    //   982: new 167	java/lang/StringBuilder
    //   985: dup
    //   986: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   989: ldc_w 353
    //   992: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   995: iload 15
    //   997: invokevirtual 347	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1000: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1003: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   1006: pop
    //   1007: dload_2
    //   1008: dstore 4
    //   1010: aload 24
    //   1012: astore 27
    //   1014: aload 48
    //   1016: invokeinterface 354 1 0
    //   1021: ifle +286 -> 1307
    //   1024: aload 48
    //   1026: invokeinterface 358 1 0
    //   1031: invokeinterface 361 1 0
    //   1036: astore 48
    //   1038: dload_2
    //   1039: dstore 4
    //   1041: aload 24
    //   1043: astore 27
    //   1045: aload 48
    //   1047: invokeinterface 107 1 0
    //   1052: ifeq +255 -> 1307
    //   1055: aload 48
    //   1057: invokeinterface 111 1 0
    //   1062: checkcast 363	java/util/Map$Entry
    //   1065: astore 27
    //   1067: aload 27
    //   1069: invokeinterface 366 1 0
    //   1074: checkcast 14	java/lang/String
    //   1077: astore 49
    //   1079: ldc -117
    //   1081: new 167	java/lang/StringBuilder
    //   1084: dup
    //   1085: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   1088: ldc_w 368
    //   1091: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1094: aload 49
    //   1096: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1099: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1102: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   1105: pop
    //   1106: aload 27
    //   1108: invokeinterface 371 1 0
    //   1113: astore 27
    //   1115: aload 24
    //   1117: ifnonnull +1268 -> 2385
    //   1120: aload 27
    //   1122: invokevirtual 374	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1125: ldc_w 376
    //   1128: iconst_2
    //   1129: anewarray 12	java/lang/Class
    //   1132: dup
    //   1133: iconst_0
    //   1134: getstatic 44	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   1137: aastore
    //   1138: dup
    //   1139: iconst_1
    //   1140: getstatic 44	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   1143: aastore
    //   1144: invokevirtual 18	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   1147: astore 24
    //   1149: aload 33
    //   1151: aload 27
    //   1153: iconst_1
    //   1154: anewarray 4	java/lang/Object
    //   1157: dup
    //   1158: iconst_0
    //   1159: iconst_0
    //   1160: invokestatic 48	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1163: aastore
    //   1164: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1167: checkcast 50	java/lang/Long
    //   1170: invokevirtual 54	java/lang/Long:longValue	()J
    //   1173: lstore 16
    //   1175: aload 34
    //   1177: aload 27
    //   1179: iconst_1
    //   1180: anewarray 4	java/lang/Object
    //   1183: dup
    //   1184: iconst_0
    //   1185: iconst_0
    //   1186: invokestatic 48	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1189: aastore
    //   1190: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1193: checkcast 50	java/lang/Long
    //   1196: invokevirtual 54	java/lang/Long:longValue	()J
    //   1199: lstore 22
    //   1201: iconst_0
    //   1202: istore 12
    //   1204: iconst_0
    //   1205: istore 11
    //   1207: iload 11
    //   1209: iload 13
    //   1211: if_icmpge +1180 -> 2391
    //   1214: aload 44
    //   1216: iload 11
    //   1218: aload 24
    //   1220: aload 27
    //   1222: iconst_2
    //   1223: anewarray 4	java/lang/Object
    //   1226: dup
    //   1227: iconst_0
    //   1228: iload 11
    //   1230: invokestatic 48	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1233: aastore
    //   1234: dup
    //   1235: iconst_1
    //   1236: iconst_0
    //   1237: invokestatic 48	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1240: aastore
    //   1241: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1244: checkcast 50	java/lang/Long
    //   1247: invokevirtual 54	java/lang/Long:longValue	()J
    //   1250: lastore
    //   1251: iload 12
    //   1253: i2l
    //   1254: aload 44
    //   1256: iload 11
    //   1258: laload
    //   1259: ladd
    //   1260: l2i
    //   1261: istore 12
    //   1263: iload 11
    //   1265: iconst_1
    //   1266: iadd
    //   1267: istore 11
    //   1269: goto -62 -> 1207
    //   1272: ldc -117
    //   1274: new 167	java/lang/StringBuilder
    //   1277: dup
    //   1278: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   1281: ldc_w 378
    //   1284: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1287: dload 4
    //   1289: invokevirtual 179	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   1292: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1295: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   1298: pop
    //   1299: dload_2
    //   1300: dload 4
    //   1302: dadd
    //   1303: dstore_2
    //   1304: goto -266 -> 1038
    //   1307: dload 4
    //   1309: ldc2_w 379
    //   1312: ddiv
    //   1313: dstore_2
    //   1314: ldc -117
    //   1316: new 167	java/lang/StringBuilder
    //   1319: dup
    //   1320: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   1323: ldc_w 382
    //   1326: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1329: dload_2
    //   1330: invokevirtual 179	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   1333: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1336: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   1339: pop
    //   1340: aload 35
    //   1342: aload 47
    //   1344: aconst_null
    //   1345: checkcast 274	[Ljava/lang/Object;
    //   1348: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1351: checkcast 192	java/util/Map
    //   1354: invokeinterface 358 1 0
    //   1359: invokeinterface 361 1 0
    //   1364: astore 48
    //   1366: lconst_0
    //   1367: lstore 16
    //   1369: aload 26
    //   1371: astore 24
    //   1373: aload 48
    //   1375: invokeinterface 107 1 0
    //   1380: ifeq +117 -> 1497
    //   1383: aload 48
    //   1385: invokeinterface 111 1 0
    //   1390: checkcast 363	java/util/Map$Entry
    //   1393: invokeinterface 371 1 0
    //   1398: astore 49
    //   1400: aload 24
    //   1402: astore 26
    //   1404: aload 24
    //   1406: ifnonnull +26 -> 1432
    //   1409: aload 49
    //   1411: invokevirtual 374	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1414: ldc_w 384
    //   1417: iconst_1
    //   1418: anewarray 12	java/lang/Class
    //   1421: dup
    //   1422: iconst_0
    //   1423: getstatic 44	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   1426: aastore
    //   1427: invokevirtual 18	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   1430: astore 26
    //   1432: aload 26
    //   1434: aload 49
    //   1436: iconst_1
    //   1437: anewarray 4	java/lang/Object
    //   1440: dup
    //   1441: iconst_0
    //   1442: iconst_0
    //   1443: invokestatic 48	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1446: aastore
    //   1447: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1450: astore 24
    //   1452: aload 24
    //   1454: ifnull +934 -> 2388
    //   1457: aload 41
    //   1459: aload 24
    //   1461: iconst_2
    //   1462: anewarray 4	java/lang/Object
    //   1465: dup
    //   1466: iconst_0
    //   1467: lload 18
    //   1469: invokestatic 325	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1472: aastore
    //   1473: dup
    //   1474: iconst_1
    //   1475: iconst_0
    //   1476: invokestatic 48	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1479: aastore
    //   1480: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1483: checkcast 50	java/lang/Long
    //   1486: invokevirtual 54	java/lang/Long:longValue	()J
    //   1489: lload 16
    //   1491: ladd
    //   1492: lstore 16
    //   1494: goto +947 -> 2441
    //   1497: lload 16
    //   1499: ldc2_w 65
    //   1502: ldiv
    //   1503: lstore 16
    //   1505: lload 16
    //   1507: l2d
    //   1508: dstore 4
    //   1510: aload 36
    //   1512: aload 42
    //   1514: iconst_1
    //   1515: anewarray 4	java/lang/Object
    //   1518: dup
    //   1519: iconst_0
    //   1520: ldc_w 386
    //   1523: aastore
    //   1524: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1527: checkcast 28	java/lang/Double
    //   1530: invokevirtual 32	java/lang/Double:doubleValue	()D
    //   1533: dload 4
    //   1535: dmul
    //   1536: ldc2_w 379
    //   1539: ddiv
    //   1540: dstore 4
    //   1542: ldc -117
    //   1544: new 167	java/lang/StringBuilder
    //   1547: dup
    //   1548: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   1551: ldc_w 388
    //   1554: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1557: lload 16
    //   1559: invokevirtual 391	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1562: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1565: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   1568: pop
    //   1569: ldc -117
    //   1571: new 167	java/lang/StringBuilder
    //   1574: dup
    //   1575: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   1578: ldc_w 393
    //   1581: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1584: dload 4
    //   1586: invokevirtual 179	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   1589: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1592: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   1595: pop
    //   1596: ldc -117
    //   1598: new 167	java/lang/StringBuilder
    //   1601: dup
    //   1602: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   1605: ldc_w 395
    //   1608: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1611: lload 16
    //   1613: invokevirtual 391	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1616: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1619: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   1622: pop
    //   1623: aload 37
    //   1625: aload 47
    //   1627: iconst_1
    //   1628: anewarray 4	java/lang/Object
    //   1631: dup
    //   1632: iconst_0
    //   1633: iconst_0
    //   1634: invokestatic 48	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1637: aastore
    //   1638: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1641: checkcast 50	java/lang/Long
    //   1644: invokevirtual 54	java/lang/Long:longValue	()J
    //   1647: lstore 16
    //   1649: aload 38
    //   1651: aload 47
    //   1653: iconst_1
    //   1654: anewarray 4	java/lang/Object
    //   1657: dup
    //   1658: iconst_0
    //   1659: iconst_0
    //   1660: invokestatic 48	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1663: aastore
    //   1664: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1667: checkcast 50	java/lang/Long
    //   1670: invokevirtual 54	java/lang/Long:longValue	()J
    //   1673: lstore 22
    //   1675: lload 16
    //   1677: lload 22
    //   1679: ladd
    //   1680: l2d
    //   1681: dload 6
    //   1683: dmul
    //   1684: dload_2
    //   1685: dload 4
    //   1687: dadd
    //   1688: dadd
    //   1689: dstore_2
    //   1690: ldc -117
    //   1692: new 167	java/lang/StringBuilder
    //   1695: dup
    //   1696: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   1699: ldc_w 397
    //   1702: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1705: lload 16
    //   1707: invokevirtual 391	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1710: ldc_w 399
    //   1713: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1716: lload 22
    //   1718: invokevirtual 391	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1721: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1724: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   1727: pop
    //   1728: aload 39
    //   1730: aload 47
    //   1732: iconst_2
    //   1733: anewarray 4	java/lang/Object
    //   1736: dup
    //   1737: iconst_0
    //   1738: lload 18
    //   1740: invokestatic 325	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1743: aastore
    //   1744: dup
    //   1745: iconst_1
    //   1746: iconst_0
    //   1747: invokestatic 48	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1750: aastore
    //   1751: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1754: checkcast 50	java/lang/Long
    //   1757: invokevirtual 54	java/lang/Long:longValue	()J
    //   1760: ldc2_w 65
    //   1763: ldiv
    //   1764: l2d
    //   1765: dstore 4
    //   1767: aload 36
    //   1769: aload 42
    //   1771: iconst_1
    //   1772: anewarray 4	java/lang/Object
    //   1775: dup
    //   1776: iconst_0
    //   1777: ldc_w 401
    //   1780: aastore
    //   1781: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1784: checkcast 28	java/lang/Double
    //   1787: invokevirtual 32	java/lang/Double:doubleValue	()D
    //   1790: dload 4
    //   1792: dmul
    //   1793: ldc2_w 379
    //   1796: ddiv
    //   1797: dstore 4
    //   1799: ldc -117
    //   1801: new 167	java/lang/StringBuilder
    //   1804: dup
    //   1805: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   1808: ldc_w 403
    //   1811: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1814: dload 4
    //   1816: invokevirtual 179	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   1819: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1822: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   1825: pop
    //   1826: dload_2
    //   1827: dload 4
    //   1829: dadd
    //   1830: dstore_2
    //   1831: aload 40
    //   1833: aload 47
    //   1835: iconst_0
    //   1836: anewarray 4	java/lang/Object
    //   1839: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1842: checkcast 192	java/util/Map
    //   1845: invokeinterface 358 1 0
    //   1850: invokeinterface 361 1 0
    //   1855: astore 26
    //   1857: dload_2
    //   1858: dstore 4
    //   1860: aload 26
    //   1862: invokeinterface 107 1 0
    //   1867: ifeq +430 -> 2297
    //   1870: aload 26
    //   1872: invokeinterface 111 1 0
    //   1877: checkcast 363	java/util/Map$Entry
    //   1880: invokeinterface 371 1 0
    //   1885: astore 47
    //   1887: aload 29
    //   1889: aload 47
    //   1891: iconst_0
    //   1892: anewarray 4	java/lang/Object
    //   1895: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1898: checkcast 40	java/lang/Integer
    //   1901: invokevirtual 311	java/lang/Integer:intValue	()I
    //   1904: istore 11
    //   1906: aload 41
    //   1908: aload 30
    //   1910: aload 47
    //   1912: iconst_0
    //   1913: anewarray 4	java/lang/Object
    //   1916: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1919: iconst_2
    //   1920: anewarray 4	java/lang/Object
    //   1923: dup
    //   1924: iconst_0
    //   1925: lload 20
    //   1927: invokestatic 325	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1930: aastore
    //   1931: dup
    //   1932: iconst_1
    //   1933: iconst_0
    //   1934: invokestatic 48	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1937: aastore
    //   1938: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1941: checkcast 50	java/lang/Long
    //   1944: invokevirtual 54	java/lang/Long:longValue	()J
    //   1947: lstore 16
    //   1949: dconst_0
    //   1950: dstore_2
    //   1951: iload 11
    //   1953: tableswitch	default:+495->2448, -10000:+277->2230
    //   1972: aload 45
    //   1974: iload 11
    //   1976: invokevirtual 407	android/hardware/SensorManager:getDefaultSensor	(I)Landroid/hardware/Sensor;
    //   1979: astore 47
    //   1981: aload 47
    //   1983: ifnull +37 -> 2020
    //   1986: aload 47
    //   1988: invokevirtual 413	android/hardware/Sensor:getPower	()F
    //   1991: f2d
    //   1992: dstore_2
    //   1993: ldc -117
    //   1995: new 167	java/lang/StringBuilder
    //   1998: dup
    //   1999: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   2002: ldc_w 415
    //   2005: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2008: lload 16
    //   2010: invokevirtual 391	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2013: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2016: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   2019: pop
    //   2020: lload 16
    //   2022: l2d
    //   2023: dload_2
    //   2024: dmul
    //   2025: ldc2_w 379
    //   2028: ddiv
    //   2029: dstore 8
    //   2031: ldc -117
    //   2033: new 167	java/lang/StringBuilder
    //   2036: dup
    //   2037: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   2040: ldc_w 417
    //   2043: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2046: iload 11
    //   2048: invokevirtual 347	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2051: ldc_w 419
    //   2054: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2057: dload_2
    //   2058: invokevirtual 179	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   2061: ldc_w 421
    //   2064: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2067: lload 16
    //   2069: invokevirtual 391	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2072: ldc_w 423
    //   2075: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2078: dload 8
    //   2080: invokevirtual 179	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   2083: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2086: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   2089: pop
    //   2090: dload 4
    //   2092: dload 8
    //   2094: dadd
    //   2095: dstore 4
    //   2097: goto -237 -> 1860
    //   2100: astore 26
    //   2102: ldc -117
    //   2104: new 167	java/lang/StringBuilder
    //   2107: dup
    //   2108: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   2111: ldc -86
    //   2113: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2116: aload 26
    //   2118: invokevirtual 426	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   2121: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2124: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2127: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   2130: pop
    //   2131: ldc -117
    //   2133: new 167	java/lang/StringBuilder
    //   2136: dup
    //   2137: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   2140: ldc_w 428
    //   2143: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2146: aload 26
    //   2148: invokevirtual 431	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2151: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2154: invokestatic 433	eu/inmite/android/fw/DebugLog:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   2157: pop
    //   2158: goto -327 -> 1831
    //   2161: astore_1
    //   2162: aload 25
    //   2164: astore_0
    //   2165: ldc -117
    //   2167: new 167	java/lang/StringBuilder
    //   2170: dup
    //   2171: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   2174: ldc -86
    //   2176: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2179: aload_1
    //   2180: invokevirtual 426	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   2183: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2186: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2189: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   2192: pop
    //   2193: ldc -117
    //   2195: new 167	java/lang/StringBuilder
    //   2198: dup
    //   2199: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   2202: ldc_w 428
    //   2205: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2208: aload_1
    //   2209: invokevirtual 431	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2212: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2215: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   2218: pop
    //   2219: aload_0
    //   2220: ifnull +7 -> 2227
    //   2223: aload_0
    //   2224: invokevirtual 436	android/os/Parcel:recycle	()V
    //   2227: aload 28
    //   2229: areturn
    //   2230: aload 36
    //   2232: aload 42
    //   2234: iconst_1
    //   2235: anewarray 4	java/lang/Object
    //   2238: dup
    //   2239: iconst_0
    //   2240: ldc_w 438
    //   2243: aastore
    //   2244: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   2247: checkcast 28	java/lang/Double
    //   2250: invokevirtual 32	java/lang/Double:doubleValue	()D
    //   2253: dstore_2
    //   2254: ldc -117
    //   2256: new 167	java/lang/StringBuilder
    //   2259: dup
    //   2260: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   2263: ldc_w 440
    //   2266: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2269: lload 16
    //   2271: invokevirtual 391	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2274: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2277: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   2280: pop
    //   2281: goto -261 -> 2020
    //   2284: astore_0
    //   2285: aload 25
    //   2287: ifnull +8 -> 2295
    //   2290: aload 25
    //   2292: invokevirtual 436	android/os/Parcel:recycle	()V
    //   2295: aload_0
    //   2296: athrow
    //   2297: ldc -117
    //   2299: ldc_w 442
    //   2302: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   2305: pop
    //   2306: aload_0
    //   2307: invokevirtual 89	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   2310: iload 15
    //   2312: invokevirtual 446	android/content/pm/PackageManager:getPackagesForUid	(I)[Ljava/lang/String;
    //   2315: astore 26
    //   2317: aload 26
    //   2319: ifnull +132 -> 2451
    //   2322: aload_1
    //   2323: ifnonnull +15 -> 2338
    //   2326: aload 26
    //   2328: aload 28
    //   2330: dload 4
    //   2332: invokestatic 448	com/avast/android/cleaner/analyzers/battery/BatteryPowerSampler:a	([Ljava/lang/String;Ljava/util/HashMap;D)V
    //   2335: goto +116 -> 2451
    //   2338: aload 26
    //   2340: aload_1
    //   2341: dload 4
    //   2343: invokestatic 450	com/avast/android/cleaner/analyzers/battery/BatteryPowerSampler:a	([Ljava/lang/String;Ljava/util/Map;D)V
    //   2346: goto +105 -> 2451
    //   2349: aload 25
    //   2351: ifnull -124 -> 2227
    //   2354: aload 25
    //   2356: invokevirtual 436	android/os/Parcel:recycle	()V
    //   2359: aload 28
    //   2361: areturn
    //   2362: astore_0
    //   2363: aconst_null
    //   2364: astore 25
    //   2366: goto -81 -> 2285
    //   2369: astore_1
    //   2370: aload_0
    //   2371: astore 25
    //   2373: aload_1
    //   2374: astore_0
    //   2375: goto -90 -> 2285
    //   2378: astore_1
    //   2379: aload 24
    //   2381: astore_0
    //   2382: goto -217 -> 2165
    //   2385: goto -1236 -> 1149
    //   2388: goto +53 -> 2441
    //   2391: iload 12
    //   2393: ifne +3 -> 2396
    //   2396: dconst_0
    //   2397: dstore 4
    //   2399: iconst_0
    //   2400: istore 11
    //   2402: iload 11
    //   2404: iload 13
    //   2406: if_icmpge -1134 -> 1272
    //   2409: dload 4
    //   2411: dconst_1
    //   2412: ldc2_w 451
    //   2415: lload 22
    //   2417: lload 16
    //   2419: ladd
    //   2420: lmul
    //   2421: l2d
    //   2422: dmul
    //   2423: aload 43
    //   2425: iload 11
    //   2427: daload
    //   2428: dmul
    //   2429: dadd
    //   2430: dstore 4
    //   2432: iload 11
    //   2434: iconst_1
    //   2435: iadd
    //   2436: istore 11
    //   2438: goto -36 -> 2402
    //   2441: aload 26
    //   2443: astore 24
    //   2445: goto -1072 -> 1373
    //   2448: goto -476 -> 1972
    //   2451: iload 10
    //   2453: iconst_1
    //   2454: iadd
    //   2455: istore 10
    //   2457: aload 24
    //   2459: astore 26
    //   2461: aload 27
    //   2463: astore 24
    //   2465: goto -1538 -> 927
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2468	0	paramContext	Context
    //   0	2468	1	paramMap	Map<String, ApplicationData>
    //   944	1310	2	d1	double
    //   1008	1423	4	d2	double
    //   695	987	6	d3	double
    //   2029	64	8	d4	double
    //   610	1846	10	i	int
    //   1205	1232	11	j	int
    //   1202	1190	12	k	int
    //   595	1812	13	m	int
    //   889	43	14	n	int
    //   978	1333	15	i1	int
    //   1173	1245	16	l1	long
    //   756	983	18	l2	long
    //   856	1070	20	l3	long
    //   1199	1217	22	l4	long
    //   98	2366	24	localObject1	Object
    //   29	2343	25	localObject2	Object
    //   112	1759	26	localObject3	Object
    //   2100	47	26	localException	Exception
    //   2315	145	26	localObject4	Object
    //   105	2357	27	localObject5	Object
    //   7	2353	28	localHashMap	HashMap
    //   95	1793	29	localObject6	Object
    //   73	1836	30	localObject7	Object
    //   51	911	31	localObject8	Object
    //   192	754	32	localMethod1	Method
    //   211	939	33	localMethod2	Method
    //   126	1050	34	localObject9	Object
    //   243	1098	35	localMethod3	Method
    //   261	1970	36	localMethod4	Method
    //   280	1344	37	localMethod5	Method
    //   299	1351	38	localMethod6	Method
    //   324	1405	39	localMethod7	Method
    //   337	1495	40	localMethod8	Method
    //   140	1767	41	localObject10	Object
    //   458	1775	42	localObject11	Object
    //   491	1933	43	localObject12	Object
    //   607	648	44	arrayOfLong	long[]
    //   795	1178	45	localSensorManager	android.hardware.SensorManager
    //   882	53	46	localSparseArray	android.util.SparseArray
    //   941	1046	47	localObject13	Object
    //   959	425	48	localObject14	Object
    //   1077	358	49	localObject15	Object
    // Exception table:
    //   from	to	target	type
    //   1728	1826	2100	java/lang/Exception
    //   498	609	2161	java/lang/Exception
    //   619	675	2161	java/lang/Exception
    //   684	918	2161	java/lang/Exception
    //   934	943	2161	java/lang/Exception
    //   945	1007	2161	java/lang/Exception
    //   1014	1038	2161	java/lang/Exception
    //   1045	1115	2161	java/lang/Exception
    //   1120	1149	2161	java/lang/Exception
    //   1149	1201	2161	java/lang/Exception
    //   1214	1251	2161	java/lang/Exception
    //   1272	1299	2161	java/lang/Exception
    //   1307	1366	2161	java/lang/Exception
    //   1373	1400	2161	java/lang/Exception
    //   1409	1432	2161	java/lang/Exception
    //   1432	1452	2161	java/lang/Exception
    //   1457	1494	2161	java/lang/Exception
    //   1497	1505	2161	java/lang/Exception
    //   1510	1675	2161	java/lang/Exception
    //   1690	1728	2161	java/lang/Exception
    //   1831	1857	2161	java/lang/Exception
    //   1860	1949	2161	java/lang/Exception
    //   1972	1981	2161	java/lang/Exception
    //   1986	2020	2161	java/lang/Exception
    //   2020	2090	2161	java/lang/Exception
    //   2102	2158	2161	java/lang/Exception
    //   2230	2281	2161	java/lang/Exception
    //   2297	2317	2161	java/lang/Exception
    //   2326	2335	2161	java/lang/Exception
    //   2338	2346	2161	java/lang/Exception
    //   498	609	2284	finally
    //   619	675	2284	finally
    //   684	918	2284	finally
    //   934	943	2284	finally
    //   945	1007	2284	finally
    //   1014	1038	2284	finally
    //   1045	1115	2284	finally
    //   1120	1149	2284	finally
    //   1149	1201	2284	finally
    //   1214	1251	2284	finally
    //   1272	1299	2284	finally
    //   1307	1366	2284	finally
    //   1373	1400	2284	finally
    //   1409	1432	2284	finally
    //   1432	1452	2284	finally
    //   1457	1494	2284	finally
    //   1497	1505	2284	finally
    //   1510	1675	2284	finally
    //   1690	1728	2284	finally
    //   1728	1826	2284	finally
    //   1831	1857	2284	finally
    //   1860	1949	2284	finally
    //   1972	1981	2284	finally
    //   1986	2020	2284	finally
    //   2020	2090	2284	finally
    //   2102	2158	2284	finally
    //   2230	2281	2284	finally
    //   2297	2317	2284	finally
    //   2326	2335	2284	finally
    //   2338	2346	2284	finally
    //   100	498	2362	finally
    //   2165	2219	2369	finally
    //   100	498	2378	java/lang/Exception
  }
}
