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
    //   931: if_icmpge +1406 -> 2337
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
    //   1117: ifnonnull +1256 -> 2373
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
    //   1211: if_icmpge +1171 -> 2382
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
    //   1380: ifeq +113 -> 1493
    //   1383: aload 48
    //   1385: invokeinterface 111 1 0
    //   1390: checkcast 363	java/util/Map$Entry
    //   1393: invokeinterface 371 1 0
    //   1398: astore 26
    //   1400: aload 24
    //   1402: ifnonnull +974 -> 2376
    //   1405: aload 26
    //   1407: invokevirtual 374	java/lang/Object:getClass	()Ljava/lang/Class;
    //   1410: ldc_w 384
    //   1413: iconst_1
    //   1414: anewarray 12	java/lang/Class
    //   1417: dup
    //   1418: iconst_0
    //   1419: getstatic 44	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   1422: aastore
    //   1423: invokevirtual 18	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   1426: astore 24
    //   1428: aload 24
    //   1430: aload 26
    //   1432: iconst_1
    //   1433: anewarray 4	java/lang/Object
    //   1436: dup
    //   1437: iconst_0
    //   1438: iconst_0
    //   1439: invokestatic 48	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1442: aastore
    //   1443: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1446: astore 26
    //   1448: aload 26
    //   1450: ifnull +929 -> 2379
    //   1453: aload 41
    //   1455: aload 26
    //   1457: iconst_2
    //   1458: anewarray 4	java/lang/Object
    //   1461: dup
    //   1462: iconst_0
    //   1463: lload 18
    //   1465: invokestatic 325	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1468: aastore
    //   1469: dup
    //   1470: iconst_1
    //   1471: iconst_0
    //   1472: invokestatic 48	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1475: aastore
    //   1476: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1479: checkcast 50	java/lang/Long
    //   1482: invokevirtual 54	java/lang/Long:longValue	()J
    //   1485: lload 16
    //   1487: ladd
    //   1488: lstore 16
    //   1490: goto +942 -> 2432
    //   1493: lload 16
    //   1495: ldc2_w 65
    //   1498: ldiv
    //   1499: lstore 16
    //   1501: lload 16
    //   1503: l2d
    //   1504: aload 36
    //   1506: aload 42
    //   1508: iconst_1
    //   1509: anewarray 4	java/lang/Object
    //   1512: dup
    //   1513: iconst_0
    //   1514: ldc_w 386
    //   1517: aastore
    //   1518: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1521: checkcast 28	java/lang/Double
    //   1524: invokevirtual 32	java/lang/Double:doubleValue	()D
    //   1527: dmul
    //   1528: ldc2_w 379
    //   1531: ddiv
    //   1532: dstore 4
    //   1534: ldc -117
    //   1536: new 167	java/lang/StringBuilder
    //   1539: dup
    //   1540: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   1543: ldc_w 388
    //   1546: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1549: lload 16
    //   1551: invokevirtual 391	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1554: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1557: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   1560: pop
    //   1561: ldc -117
    //   1563: new 167	java/lang/StringBuilder
    //   1566: dup
    //   1567: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   1570: ldc_w 393
    //   1573: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1576: dload 4
    //   1578: invokevirtual 179	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   1581: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1584: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   1587: pop
    //   1588: ldc -117
    //   1590: new 167	java/lang/StringBuilder
    //   1593: dup
    //   1594: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   1597: ldc_w 395
    //   1600: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1603: lload 16
    //   1605: invokevirtual 391	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1608: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1611: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   1614: pop
    //   1615: aload 37
    //   1617: aload 47
    //   1619: iconst_1
    //   1620: anewarray 4	java/lang/Object
    //   1623: dup
    //   1624: iconst_0
    //   1625: iconst_0
    //   1626: invokestatic 48	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1629: aastore
    //   1630: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1633: checkcast 50	java/lang/Long
    //   1636: invokevirtual 54	java/lang/Long:longValue	()J
    //   1639: lstore 16
    //   1641: aload 38
    //   1643: aload 47
    //   1645: iconst_1
    //   1646: anewarray 4	java/lang/Object
    //   1649: dup
    //   1650: iconst_0
    //   1651: iconst_0
    //   1652: invokestatic 48	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1655: aastore
    //   1656: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1659: checkcast 50	java/lang/Long
    //   1662: invokevirtual 54	java/lang/Long:longValue	()J
    //   1665: lstore 22
    //   1667: lload 16
    //   1669: lload 22
    //   1671: ladd
    //   1672: l2d
    //   1673: dload 6
    //   1675: dmul
    //   1676: dload_2
    //   1677: dload 4
    //   1679: dadd
    //   1680: dadd
    //   1681: dstore_2
    //   1682: ldc -117
    //   1684: new 167	java/lang/StringBuilder
    //   1687: dup
    //   1688: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   1691: ldc_w 397
    //   1694: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1697: lload 16
    //   1699: invokevirtual 391	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1702: ldc_w 399
    //   1705: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1708: lload 22
    //   1710: invokevirtual 391	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1713: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1716: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   1719: pop
    //   1720: aload 39
    //   1722: aload 47
    //   1724: iconst_2
    //   1725: anewarray 4	java/lang/Object
    //   1728: dup
    //   1729: iconst_0
    //   1730: lload 18
    //   1732: invokestatic 325	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1735: aastore
    //   1736: dup
    //   1737: iconst_1
    //   1738: iconst_0
    //   1739: invokestatic 48	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1742: aastore
    //   1743: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1746: checkcast 50	java/lang/Long
    //   1749: invokevirtual 54	java/lang/Long:longValue	()J
    //   1752: ldc2_w 65
    //   1755: ldiv
    //   1756: l2d
    //   1757: aload 36
    //   1759: aload 42
    //   1761: iconst_1
    //   1762: anewarray 4	java/lang/Object
    //   1765: dup
    //   1766: iconst_0
    //   1767: ldc_w 401
    //   1770: aastore
    //   1771: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1774: checkcast 28	java/lang/Double
    //   1777: invokevirtual 32	java/lang/Double:doubleValue	()D
    //   1780: dmul
    //   1781: ldc2_w 379
    //   1784: ddiv
    //   1785: dstore 4
    //   1787: ldc -117
    //   1789: new 167	java/lang/StringBuilder
    //   1792: dup
    //   1793: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   1796: ldc_w 403
    //   1799: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1802: dload 4
    //   1804: invokevirtual 179	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   1807: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1810: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   1813: pop
    //   1814: dload_2
    //   1815: dload 4
    //   1817: dadd
    //   1818: dstore_2
    //   1819: aload 40
    //   1821: aload 47
    //   1823: iconst_0
    //   1824: anewarray 4	java/lang/Object
    //   1827: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1830: checkcast 192	java/util/Map
    //   1833: invokeinterface 358 1 0
    //   1838: invokeinterface 361 1 0
    //   1843: astore 26
    //   1845: dload_2
    //   1846: dstore 4
    //   1848: aload 26
    //   1850: invokeinterface 107 1 0
    //   1855: ifeq +430 -> 2285
    //   1858: aload 26
    //   1860: invokeinterface 111 1 0
    //   1865: checkcast 363	java/util/Map$Entry
    //   1868: invokeinterface 371 1 0
    //   1873: astore 47
    //   1875: aload 29
    //   1877: aload 47
    //   1879: iconst_0
    //   1880: anewarray 4	java/lang/Object
    //   1883: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1886: checkcast 40	java/lang/Integer
    //   1889: invokevirtual 311	java/lang/Integer:intValue	()I
    //   1892: istore 11
    //   1894: aload 41
    //   1896: aload 30
    //   1898: aload 47
    //   1900: iconst_0
    //   1901: anewarray 4	java/lang/Object
    //   1904: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1907: iconst_2
    //   1908: anewarray 4	java/lang/Object
    //   1911: dup
    //   1912: iconst_0
    //   1913: lload 20
    //   1915: invokestatic 325	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1918: aastore
    //   1919: dup
    //   1920: iconst_1
    //   1921: iconst_0
    //   1922: invokestatic 48	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1925: aastore
    //   1926: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   1929: checkcast 50	java/lang/Long
    //   1932: invokevirtual 54	java/lang/Long:longValue	()J
    //   1935: lstore 16
    //   1937: dconst_0
    //   1938: dstore_2
    //   1939: iload 11
    //   1941: tableswitch	default:+494->2435, -10000:+277->2218
    //   1960: aload 45
    //   1962: iload 11
    //   1964: invokevirtual 407	android/hardware/SensorManager:getDefaultSensor	(I)Landroid/hardware/Sensor;
    //   1967: astore 47
    //   1969: aload 47
    //   1971: ifnull +37 -> 2008
    //   1974: aload 47
    //   1976: invokevirtual 413	android/hardware/Sensor:getPower	()F
    //   1979: f2d
    //   1980: dstore_2
    //   1981: ldc -117
    //   1983: new 167	java/lang/StringBuilder
    //   1986: dup
    //   1987: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   1990: ldc_w 415
    //   1993: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1996: lload 16
    //   1998: invokevirtual 391	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2001: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2004: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   2007: pop
    //   2008: lload 16
    //   2010: l2d
    //   2011: dload_2
    //   2012: dmul
    //   2013: ldc2_w 379
    //   2016: ddiv
    //   2017: dstore 8
    //   2019: ldc -117
    //   2021: new 167	java/lang/StringBuilder
    //   2024: dup
    //   2025: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   2028: ldc_w 417
    //   2031: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2034: iload 11
    //   2036: invokevirtual 347	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   2039: ldc_w 419
    //   2042: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2045: dload_2
    //   2046: invokevirtual 179	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   2049: ldc_w 421
    //   2052: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2055: lload 16
    //   2057: invokevirtual 391	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2060: ldc_w 423
    //   2063: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2066: dload 8
    //   2068: invokevirtual 179	java/lang/StringBuilder:append	(D)Ljava/lang/StringBuilder;
    //   2071: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2074: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   2077: pop
    //   2078: dload 4
    //   2080: dload 8
    //   2082: dadd
    //   2083: dstore 4
    //   2085: goto -237 -> 1848
    //   2088: astore 26
    //   2090: ldc -117
    //   2092: new 167	java/lang/StringBuilder
    //   2095: dup
    //   2096: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   2099: ldc -86
    //   2101: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2104: aload 26
    //   2106: invokevirtual 426	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   2109: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2112: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2115: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   2118: pop
    //   2119: ldc -117
    //   2121: new 167	java/lang/StringBuilder
    //   2124: dup
    //   2125: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   2128: ldc_w 428
    //   2131: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2134: aload 26
    //   2136: invokevirtual 431	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2139: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2142: invokestatic 433	eu/inmite/android/fw/DebugLog:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   2145: pop
    //   2146: goto -327 -> 1819
    //   2149: astore_1
    //   2150: aload 25
    //   2152: astore_0
    //   2153: ldc -117
    //   2155: new 167	java/lang/StringBuilder
    //   2158: dup
    //   2159: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   2162: ldc -86
    //   2164: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2167: aload_1
    //   2168: invokevirtual 426	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   2171: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2174: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2177: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   2180: pop
    //   2181: ldc -117
    //   2183: new 167	java/lang/StringBuilder
    //   2186: dup
    //   2187: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   2190: ldc_w 428
    //   2193: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2196: aload_1
    //   2197: invokevirtual 431	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2200: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2203: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   2206: pop
    //   2207: aload_0
    //   2208: ifnull +7 -> 2215
    //   2211: aload_0
    //   2212: invokevirtual 436	android/os/Parcel:recycle	()V
    //   2215: aload 28
    //   2217: areturn
    //   2218: aload 36
    //   2220: aload 42
    //   2222: iconst_1
    //   2223: anewarray 4	java/lang/Object
    //   2226: dup
    //   2227: iconst_0
    //   2228: ldc_w 438
    //   2231: aastore
    //   2232: invokevirtual 26	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   2235: checkcast 28	java/lang/Double
    //   2238: invokevirtual 32	java/lang/Double:doubleValue	()D
    //   2241: dstore_2
    //   2242: ldc -117
    //   2244: new 167	java/lang/StringBuilder
    //   2247: dup
    //   2248: invokespecial 168	java/lang/StringBuilder:<init>	()V
    //   2251: ldc_w 440
    //   2254: invokevirtual 174	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2257: lload 16
    //   2259: invokevirtual 391	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   2262: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2265: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   2268: pop
    //   2269: goto -261 -> 2008
    //   2272: astore_0
    //   2273: aload 25
    //   2275: ifnull +8 -> 2283
    //   2278: aload 25
    //   2280: invokevirtual 436	android/os/Parcel:recycle	()V
    //   2283: aload_0
    //   2284: athrow
    //   2285: ldc -117
    //   2287: ldc_w 442
    //   2290: invokestatic 186	eu/inmite/android/fw/DebugLog:b	(Ljava/lang/String;Ljava/lang/String;)I
    //   2293: pop
    //   2294: aload_0
    //   2295: invokevirtual 89	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   2298: iload 15
    //   2300: invokevirtual 446	android/content/pm/PackageManager:getPackagesForUid	(I)[Ljava/lang/String;
    //   2303: astore 26
    //   2305: aload 26
    //   2307: ifnull +131 -> 2438
    //   2310: aload_1
    //   2311: ifnonnull +15 -> 2326
    //   2314: aload 26
    //   2316: aload 28
    //   2318: dload 4
    //   2320: invokestatic 448	com/avast/android/cleaner/analyzers/battery/BatteryPowerSampler:a	([Ljava/lang/String;Ljava/util/HashMap;D)V
    //   2323: goto +115 -> 2438
    //   2326: aload 26
    //   2328: aload_1
    //   2329: dload 4
    //   2331: invokestatic 450	com/avast/android/cleaner/analyzers/battery/BatteryPowerSampler:a	([Ljava/lang/String;Ljava/util/Map;D)V
    //   2334: goto +104 -> 2438
    //   2337: aload 25
    //   2339: ifnull -124 -> 2215
    //   2342: aload 25
    //   2344: invokevirtual 436	android/os/Parcel:recycle	()V
    //   2347: aload 28
    //   2349: areturn
    //   2350: astore_0
    //   2351: aconst_null
    //   2352: astore 25
    //   2354: goto -81 -> 2273
    //   2357: astore_1
    //   2358: aload_0
    //   2359: astore 25
    //   2361: aload_1
    //   2362: astore_0
    //   2363: goto -90 -> 2273
    //   2366: astore_1
    //   2367: aload 24
    //   2369: astore_0
    //   2370: goto -217 -> 2153
    //   2373: goto -1224 -> 1149
    //   2376: goto -948 -> 1428
    //   2379: goto +53 -> 2432
    //   2382: iload 12
    //   2384: ifne +3 -> 2387
    //   2387: dconst_0
    //   2388: dstore 4
    //   2390: iconst_0
    //   2391: istore 11
    //   2393: iload 11
    //   2395: iload 13
    //   2397: if_icmpge -1125 -> 1272
    //   2400: dload 4
    //   2402: dconst_1
    //   2403: ldc2_w 451
    //   2406: lload 22
    //   2408: lload 16
    //   2410: ladd
    //   2411: lmul
    //   2412: l2d
    //   2413: dmul
    //   2414: aload 43
    //   2416: iload 11
    //   2418: daload
    //   2419: dmul
    //   2420: dadd
    //   2421: dstore 4
    //   2423: iload 11
    //   2425: iconst_1
    //   2426: iadd
    //   2427: istore 11
    //   2429: goto -36 -> 2393
    //   2432: goto -1059 -> 1373
    //   2435: goto -475 -> 1960
    //   2438: iload 10
    //   2440: iconst_1
    //   2441: iadd
    //   2442: istore 10
    //   2444: aload 24
    //   2446: astore 26
    //   2448: aload 27
    //   2450: astore 24
    //   2452: goto -1525 -> 927
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2455	0	paramContext	Context
    //   0	2455	1	paramMap	Map<String, ApplicationData>
    //   944	1298	2	d1	double
    //   1008	1414	4	d2	double
    //   695	979	6	d3	double
    //   2017	64	8	d4	double
    //   610	1833	10	i	int
    //   1205	1223	11	j	int
    //   1202	1181	12	k	int
    //   595	1803	13	m	int
    //   889	43	14	n	int
    //   978	1321	15	i1	int
    //   1173	1236	16	l1	long
    //   756	975	18	l2	long
    //   856	1058	20	l3	long
    //   1199	1208	22	l4	long
    //   98	2353	24	localObject1	Object
    //   29	2331	25	localObject2	Object
    //   112	1747	26	localObject3	Object
    //   2088	47	26	localException	Exception
    //   2303	144	26	localObject4	Object
    //   105	2344	27	localObject5	Object
    //   7	2341	28	localHashMap	HashMap
    //   95	1781	29	localObject6	Object
    //   73	1824	30	localObject7	Object
    //   51	911	31	localObject8	Object
    //   192	754	32	localMethod1	Method
    //   211	939	33	localMethod2	Method
    //   126	1050	34	localObject9	Object
    //   243	1098	35	localMethod3	Method
    //   261	1958	36	localMethod4	Method
    //   280	1336	37	localMethod5	Method
    //   299	1343	38	localMethod6	Method
    //   324	1397	39	localMethod7	Method
    //   337	1483	40	localMethod8	Method
    //   140	1755	41	localObject10	Object
    //   458	1763	42	localObject11	Object
    //   491	1924	43	localObject12	Object
    //   607	648	44	arrayOfLong	long[]
    //   795	1166	45	localSensorManager	android.hardware.SensorManager
    //   882	53	46	localSparseArray	android.util.SparseArray
    //   941	1034	47	localObject13	Object
    //   959	425	48	localObject14	Object
    //   1077	18	49	str	String
    // Exception table:
    //   from	to	target	type
    //   1720	1814	2088	java/lang/Exception
    //   498	609	2149	java/lang/Exception
    //   619	675	2149	java/lang/Exception
    //   684	918	2149	java/lang/Exception
    //   934	943	2149	java/lang/Exception
    //   945	1007	2149	java/lang/Exception
    //   1014	1038	2149	java/lang/Exception
    //   1045	1115	2149	java/lang/Exception
    //   1120	1149	2149	java/lang/Exception
    //   1149	1201	2149	java/lang/Exception
    //   1214	1251	2149	java/lang/Exception
    //   1272	1299	2149	java/lang/Exception
    //   1307	1366	2149	java/lang/Exception
    //   1373	1400	2149	java/lang/Exception
    //   1405	1428	2149	java/lang/Exception
    //   1428	1448	2149	java/lang/Exception
    //   1453	1490	2149	java/lang/Exception
    //   1493	1667	2149	java/lang/Exception
    //   1682	1720	2149	java/lang/Exception
    //   1819	1845	2149	java/lang/Exception
    //   1848	1937	2149	java/lang/Exception
    //   1960	1969	2149	java/lang/Exception
    //   1974	2008	2149	java/lang/Exception
    //   2008	2078	2149	java/lang/Exception
    //   2090	2146	2149	java/lang/Exception
    //   2218	2269	2149	java/lang/Exception
    //   2285	2305	2149	java/lang/Exception
    //   2314	2323	2149	java/lang/Exception
    //   2326	2334	2149	java/lang/Exception
    //   498	609	2272	finally
    //   619	675	2272	finally
    //   684	918	2272	finally
    //   934	943	2272	finally
    //   945	1007	2272	finally
    //   1014	1038	2272	finally
    //   1045	1115	2272	finally
    //   1120	1149	2272	finally
    //   1149	1201	2272	finally
    //   1214	1251	2272	finally
    //   1272	1299	2272	finally
    //   1307	1366	2272	finally
    //   1373	1400	2272	finally
    //   1405	1428	2272	finally
    //   1428	1448	2272	finally
    //   1453	1490	2272	finally
    //   1493	1667	2272	finally
    //   1682	1720	2272	finally
    //   1720	1814	2272	finally
    //   1819	1845	2272	finally
    //   1848	1937	2272	finally
    //   1960	1969	2272	finally
    //   1974	2008	2272	finally
    //   2008	2078	2272	finally
    //   2090	2146	2272	finally
    //   2218	2269	2272	finally
    //   2285	2305	2272	finally
    //   2314	2323	2272	finally
    //   2326	2334	2272	finally
    //   100	498	2350	finally
    //   2153	2207	2357	finally
    //   100	498	2366	java/lang/Exception
  }
}
