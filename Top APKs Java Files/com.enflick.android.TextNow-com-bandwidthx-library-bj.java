package com.bandwidthx.library;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;

public class bj
{
  private static bk a = null;
  private static HashMap<Integer, String> b = new HashMap();
  private static Long c = Long.valueOf(0L);
  private static LinkedList<bj.a> d = new LinkedList();
  private static Boolean e = Boolean.valueOf(false);
  private static String f = "BANDWIDTHX";
  private static Boolean g = Boolean.valueOf(false);
  private static LinkedList<String> h = new LinkedList();
  private static FileOutputStream i = null;
  private static OutputStreamWriter j = null;
  private static Long k = Long.valueOf(0L);
  private static Long l = Long.valueOf(86400000L);
  private static Long m = Long.valueOf(0L);
  private static Long n = Long.valueOf(5L);
  private static Boolean o = Boolean.valueOf(false);
  private static Boolean p = Boolean.valueOf(false);
  private static String q = "";
  
  public bj(bk paramBk)
  {
    try
    {
      a = paramBk;
      return;
    }
    catch (Exception paramBk)
    {
      a(paramBk);
    }
  }
  
  public static String a(Boolean paramBoolean)
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    Object localObject2 = bk.H().b();
    Object localObject1 = localObject2;
    try
    {
      localObject2 = ((String)localObject2).substring(((String)localObject2).indexOf("//") + 2);
      localObject1 = localObject2;
      localObject2 = ((String)localObject2).substring(0, ((String)localObject2).indexOf("/"));
      localObject1 = localObject2;
    }
    catch (Exception localException)
    {
      int i1;
      StringBuilder localStringBuilder2;
      for (;;) {}
    }
    localObject2 = Calendar.getInstance();
    i1 = ((Calendar)localObject2).get(15);
    localObject2 = Long.valueOf(Long.valueOf((((Calendar)localObject2).get(16) + i1) / 1000L).longValue() / 3600L);
    localStringBuilder1.append("Date: " + ax.c(eb.d(), Boolean.valueOf(false)));
    localStringBuilder1.append("\r\nBuild: " + aa.e() + " " + aa.g() + "." + aa.f() + " " + aa.h());
    localStringBuilder1.append("\r\nToken: " + bk.H().c());
    if (!paramBoolean.booleanValue())
    {
      localStringBuilder1.append("\r\nApps: " + g());
      localStringBuilder1.append("\r\nUser: " + bk.K().d());
      localStringBuilder1.append("\r\nManufacturer: " + Build.MANUFACTURER);
      localStringBuilder1.append("\r\nModel: " + Build.MODEL);
      localStringBuilder1.append("\r\nVersion: Android " + Build.VERSION.RELEASE);
      localStringBuilder2 = new StringBuilder().append("\r\nTime Zone: GMT");
      if (((Long)localObject2).longValue() < 0L) {
        break label680;
      }
    }
    label680:
    for (paramBoolean = "+";; paramBoolean = "")
    {
      localStringBuilder1.append(paramBoolean + ((Long)localObject2).toString());
      localStringBuilder1.append("\r\nLocale: " + aa.n() + " (setting " + Locale.getDefault().toString() + ")");
      localStringBuilder1.append("\r\nMobile Home: " + bk.A().d() + " " + bk.A().e());
      localStringBuilder1.append("\r\nMobile ID: " + bk.A().k());
      localStringBuilder1.append("\r\nMobile Number: " + bk.A().l());
      localStringBuilder1.append("\r\nDevice ID: " + bk.A().j());
      localStringBuilder1.append("\r\nWifi MAC: " + bk.M().b());
      localStringBuilder1.append("\r\nServices: " + bk.u().a());
      localStringBuilder1.append("\r\nServer: " + localObject1);
      return localStringBuilder1.toString();
    }
  }
  
  /* Error */
  private static String a(String paramString, OutputStreamWriter paramOutputStreamWriter)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: new 108	java/lang/StringBuilder
    //   6: dup
    //   7: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   10: astore 7
    //   12: aload_0
    //   13: invokevirtual 301	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   16: astore 8
    //   18: aload 8
    //   20: ldc_w 303
    //   23: invokevirtual 307	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   26: astore 9
    //   28: new 309	java/util/ArrayList
    //   31: dup
    //   32: invokespecial 310	java/util/ArrayList:<init>	()V
    //   35: astore_0
    //   36: invokestatic 312	com/bandwidthx/library/bj:f	()Ljava/lang/String;
    //   39: astore_1
    //   40: aload_0
    //   41: new 108	java/lang/StringBuilder
    //   44: dup
    //   45: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   48: aload_1
    //   49: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: ldc_w 314
    //   55: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   58: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   61: invokevirtual 318	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   64: pop
    //   65: aload_0
    //   66: new 108	java/lang/StringBuilder
    //   69: dup
    //   70: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   73: aload_1
    //   74: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   77: ldc_w 320
    //   80: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   86: invokevirtual 318	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   89: pop
    //   90: aload_0
    //   91: new 108	java/lang/StringBuilder
    //   94: dup
    //   95: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   98: aload_1
    //   99: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   102: ldc_w 322
    //   105: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   108: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   111: invokevirtual 318	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   114: pop
    //   115: aload_0
    //   116: new 108	java/lang/StringBuilder
    //   119: dup
    //   120: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   123: aload_1
    //   124: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   127: ldc_w 324
    //   130: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   136: invokevirtual 318	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   139: pop
    //   140: aload_0
    //   141: invokevirtual 328	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   144: astore_1
    //   145: iconst_0
    //   146: istore_2
    //   147: aload_1
    //   148: invokeinterface 333 1 0
    //   153: ifeq +42 -> 195
    //   156: new 335	java/io/File
    //   159: dup
    //   160: aload_1
    //   161: invokeinterface 339 1 0
    //   166: checkcast 124	java/lang/String
    //   169: invokespecial 342	java/io/File:<init>	(Ljava/lang/String;)V
    //   172: astore 6
    //   174: aload 6
    //   176: invokevirtual 345	java/io/File:exists	()Z
    //   179: ifeq +407 -> 586
    //   182: iload_2
    //   183: i2l
    //   184: aload 6
    //   186: invokevirtual 348	java/io/File:length	()J
    //   189: ladd
    //   190: l2i
    //   191: istore_2
    //   192: goto +394 -> 586
    //   195: new 108	java/lang/StringBuilder
    //   198: dup
    //   199: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   202: ldc_w 350
    //   205: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   208: iload_2
    //   209: invokestatic 354	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   212: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   215: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   218: invokestatic 356	com/bandwidthx/library/bj:b	(Ljava/lang/String;)V
    //   221: new 108	java/lang/StringBuilder
    //   224: dup
    //   225: iload_2
    //   226: invokespecial 359	java/lang/StringBuilder:<init>	(I)V
    //   229: astore 6
    //   231: aload_0
    //   232: invokevirtual 328	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   235: astore 7
    //   237: aload 7
    //   239: invokeinterface 333 1 0
    //   244: ifeq +294 -> 538
    //   247: new 335	java/io/File
    //   250: dup
    //   251: aload 7
    //   253: invokeinterface 339 1 0
    //   258: checkcast 124	java/lang/String
    //   261: invokespecial 342	java/io/File:<init>	(Ljava/lang/String;)V
    //   264: astore_0
    //   265: aload_0
    //   266: invokevirtual 345	java/io/File:exists	()Z
    //   269: istore 4
    //   271: iload 4
    //   273: ifeq -36 -> 237
    //   276: new 361	java/io/FileReader
    //   279: dup
    //   280: aload_0
    //   281: invokespecial 364	java/io/FileReader:<init>	(Ljava/io/File;)V
    //   284: astore_0
    //   285: new 366	java/io/BufferedReader
    //   288: dup
    //   289: aload_0
    //   290: invokespecial 369	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   293: astore_1
    //   294: aload 8
    //   296: invokevirtual 372	java/lang/String:length	()I
    //   299: ifne +146 -> 445
    //   302: aload_1
    //   303: invokevirtual 375	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   306: astore 10
    //   308: aload 10
    //   310: ifnull +213 -> 523
    //   313: aload 6
    //   315: new 108	java/lang/StringBuilder
    //   318: dup
    //   319: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   322: aload 10
    //   324: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   327: ldc_w 377
    //   330: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   333: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   336: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   339: pop
    //   340: goto -38 -> 302
    //   343: astore 7
    //   345: aload_0
    //   346: astore 5
    //   348: aload 7
    //   350: astore_0
    //   351: aload_1
    //   352: ifnull +7 -> 359
    //   355: aload_1
    //   356: invokevirtual 380	java/io/BufferedReader:close	()V
    //   359: aload 5
    //   361: ifnull +8 -> 369
    //   364: aload 5
    //   366: invokevirtual 381	java/io/FileReader:close	()V
    //   369: aload_0
    //   370: athrow
    //   371: astore_0
    //   372: aload_0
    //   373: astore_1
    //   374: aload 6
    //   376: astore_0
    //   377: new 108	java/lang/StringBuilder
    //   380: dup
    //   381: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   384: ldc_w 383
    //   387: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   390: aload_1
    //   391: invokestatic 386	com/bandwidthx/library/ax:a	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   394: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   397: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   400: iconst_0
    //   401: invokestatic 63	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   404: iconst_0
    //   405: invokestatic 389	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   408: aconst_null
    //   409: invokestatic 392	com/bandwidthx/library/bj:a	(Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;)V
    //   412: aload_0
    //   413: new 108	java/lang/StringBuilder
    //   416: dup
    //   417: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   420: ldc_w 383
    //   423: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   426: aload_1
    //   427: invokestatic 386	com/bandwidthx/library/ax:a	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   430: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   433: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   436: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   439: pop
    //   440: aload_0
    //   441: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   444: areturn
    //   445: aload_1
    //   446: invokevirtual 375	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   449: astore 10
    //   451: aload 10
    //   453: ifnull +70 -> 523
    //   456: aload 9
    //   458: arraylength
    //   459: istore_3
    //   460: iconst_0
    //   461: istore_2
    //   462: iload_2
    //   463: iload_3
    //   464: if_icmpge -19 -> 445
    //   467: aload 9
    //   469: iload_2
    //   470: aaload
    //   471: astore 11
    //   473: aload 10
    //   475: invokevirtual 301	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   478: aload 11
    //   480: invokevirtual 396	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   483: ifeq +33 -> 516
    //   486: aload 6
    //   488: new 108	java/lang/StringBuilder
    //   491: dup
    //   492: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   495: aload 10
    //   497: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   500: ldc_w 398
    //   503: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   506: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   509: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   512: pop
    //   513: goto -68 -> 445
    //   516: iload_2
    //   517: iconst_1
    //   518: iadd
    //   519: istore_2
    //   520: goto -58 -> 462
    //   523: aload_1
    //   524: invokevirtual 380	java/io/BufferedReader:close	()V
    //   527: aload_0
    //   528: invokevirtual 381	java/io/FileReader:close	()V
    //   531: goto -294 -> 237
    //   534: astore_0
    //   535: goto -298 -> 237
    //   538: aload 6
    //   540: astore_0
    //   541: goto -101 -> 440
    //   544: astore_1
    //   545: goto -18 -> 527
    //   548: astore_1
    //   549: goto -190 -> 359
    //   552: astore_1
    //   553: goto -184 -> 369
    //   556: astore_1
    //   557: goto -117 -> 440
    //   560: astore_1
    //   561: aload 7
    //   563: astore_0
    //   564: goto -187 -> 377
    //   567: astore_0
    //   568: aconst_null
    //   569: astore_1
    //   570: goto -219 -> 351
    //   573: astore 7
    //   575: aconst_null
    //   576: astore_1
    //   577: aload_0
    //   578: astore 5
    //   580: aload 7
    //   582: astore_0
    //   583: goto -232 -> 351
    //   586: goto -439 -> 147
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	589	0	paramString	String
    //   0	589	1	paramOutputStreamWriter	OutputStreamWriter
    //   146	374	2	i1	int
    //   459	6	3	i2	int
    //   269	3	4	bool	boolean
    //   1	578	5	str1	String
    //   172	367	6	localObject1	Object
    //   10	242	7	localObject2	Object
    //   343	219	7	localObject3	Object
    //   573	8	7	localObject4	Object
    //   16	279	8	str2	String
    //   26	442	9	arrayOfString	String[]
    //   306	190	10	str3	String
    //   471	8	11	str4	String
    // Exception table:
    //   from	to	target	type
    //   294	302	343	finally
    //   302	308	343	finally
    //   313	340	343	finally
    //   445	451	343	finally
    //   456	460	343	finally
    //   473	513	343	finally
    //   231	237	371	java/lang/Exception
    //   237	271	371	java/lang/Exception
    //   369	371	371	java/lang/Exception
    //   527	531	534	java/lang/Exception
    //   523	527	544	java/lang/Exception
    //   355	359	548	java/lang/Exception
    //   364	369	552	java/lang/Exception
    //   377	440	556	java/lang/Exception
    //   28	145	560	java/lang/Exception
    //   147	192	560	java/lang/Exception
    //   195	231	560	java/lang/Exception
    //   276	285	567	finally
    //   285	294	573	finally
  }
  
  public static void a()
  {
    try
    {
      if (j != null) {
        j.close();
      }
      try
      {
        if (i != null) {
          i.close();
        }
        j = null;
        i = null;
        return;
      }
      catch (Exception localException1)
      {
        for (;;) {}
      }
    }
    catch (Exception localException2)
    {
      for (;;) {}
    }
  }
  
  public static void a(Long paramLong)
  {
    c = Long.valueOf(eb.d().longValue() + paramLong.longValue());
  }
  
  public static void a(String paramString)
  {
    if (b.get(Integer.valueOf(Process.myTid())) == null) {
      b.put(Integer.valueOf(Process.myTid()), paramString);
    }
  }
  
  public static void a(String paramString, Boolean paramBoolean)
  {
    a(paramString, paramBoolean, Integer.valueOf(0), null);
  }
  
  /* Error */
  public static void a(String paramString1, Boolean paramBoolean, Integer paramInteger, String paramString2)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 410	android/os/Process:myTid	()I
    //   6: invokestatic 354	java/lang/Integer:toString	(I)Ljava/lang/String;
    //   9: astore 4
    //   11: getstatic 45	com/bandwidthx/library/bj:b	Ljava/util/HashMap;
    //   14: invokestatic 410	android/os/Process:myTid	()I
    //   17: invokestatic 389	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   20: invokevirtual 413	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   23: ifnull +20 -> 43
    //   26: getstatic 45	com/bandwidthx/library/bj:b	Ljava/util/HashMap;
    //   29: invokestatic 410	android/os/Process:myTid	()I
    //   32: invokestatic 389	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   35: invokevirtual 413	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   38: checkcast 124	java/lang/String
    //   41: astore 4
    //   43: invokestatic 425	com/bandwidthx/library/bi:e	()Ljava/lang/Boolean;
    //   46: invokevirtual 202	java/lang/Boolean:booleanValue	()Z
    //   49: ifeq +777 -> 826
    //   52: new 108	java/lang/StringBuilder
    //   55: dup
    //   56: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   59: aload 4
    //   61: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   64: ldc_w 427
    //   67: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   73: astore 7
    //   75: ldc 95
    //   77: astore 5
    //   79: invokestatic 430	com/bandwidthx/library/bk:e	()Landroid/content/Context;
    //   82: ldc_w 432
    //   85: invokevirtual 438	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   88: checkcast 440	android/net/wifi/WifiManager
    //   91: astore 8
    //   93: aload 5
    //   95: astore 4
    //   97: aload 8
    //   99: invokevirtual 443	android/net/wifi/WifiManager:isWifiEnabled	()Z
    //   102: ifeq +252 -> 354
    //   105: aload 8
    //   107: invokevirtual 447	android/net/wifi/WifiManager:getConnectionInfo	()Landroid/net/wifi/WifiInfo;
    //   110: astore 6
    //   112: aload 5
    //   114: astore 4
    //   116: aload 6
    //   118: invokevirtual 452	android/net/wifi/WifiInfo:getBSSID	()Ljava/lang/String;
    //   121: ifnull +233 -> 354
    //   124: aload 5
    //   126: astore 4
    //   128: aload 6
    //   130: invokevirtual 456	android/net/wifi/WifiInfo:getSupplicantState	()Landroid/net/wifi/SupplicantState;
    //   133: ifnull +221 -> 354
    //   136: aload 5
    //   138: astore 4
    //   140: aload 6
    //   142: invokevirtual 459	android/net/wifi/WifiInfo:getSSID	()Ljava/lang/String;
    //   145: ifnull +209 -> 354
    //   148: aload 5
    //   150: astore 4
    //   152: aload 6
    //   154: invokevirtual 452	android/net/wifi/WifiInfo:getBSSID	()Ljava/lang/String;
    //   157: invokevirtual 372	java/lang/String:length	()I
    //   160: ifle +194 -> 354
    //   163: aload 5
    //   165: astore 4
    //   167: aload 6
    //   169: invokevirtual 459	android/net/wifi/WifiInfo:getSSID	()Ljava/lang/String;
    //   172: invokevirtual 372	java/lang/String:length	()I
    //   175: ifle +179 -> 354
    //   178: aload 6
    //   180: invokevirtual 456	android/net/wifi/WifiInfo:getSupplicantState	()Landroid/net/wifi/SupplicantState;
    //   183: getstatic 465	android/net/wifi/SupplicantState:ASSOCIATED	Landroid/net/wifi/SupplicantState;
    //   186: if_acmpeq +18 -> 204
    //   189: aload 5
    //   191: astore 4
    //   193: aload 6
    //   195: invokevirtual 456	android/net/wifi/WifiInfo:getSupplicantState	()Landroid/net/wifi/SupplicantState;
    //   198: getstatic 468	android/net/wifi/SupplicantState:COMPLETED	Landroid/net/wifi/SupplicantState;
    //   201: if_acmpne +153 -> 354
    //   204: aload 6
    //   206: invokevirtual 459	android/net/wifi/WifiInfo:getSSID	()Ljava/lang/String;
    //   209: ldc_w 470
    //   212: invokevirtual 474	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   215: ifeq +384 -> 599
    //   218: aload 6
    //   220: invokevirtual 459	android/net/wifi/WifiInfo:getSSID	()Ljava/lang/String;
    //   223: iconst_1
    //   224: aload 6
    //   226: invokevirtual 459	android/net/wifi/WifiInfo:getSSID	()Ljava/lang/String;
    //   229: invokevirtual 372	java/lang/String:length	()I
    //   232: iconst_1
    //   233: isub
    //   234: invokevirtual 137	java/lang/String:substring	(II)Ljava/lang/String;
    //   237: astore 4
    //   239: aload 4
    //   241: astore 6
    //   243: invokestatic 430	com/bandwidthx/library/bk:e	()Landroid/content/Context;
    //   246: ldc_w 476
    //   249: invokevirtual 438	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   252: checkcast 478	android/net/ConnectivityManager
    //   255: astore 9
    //   257: aload 4
    //   259: astore 5
    //   261: aload 9
    //   263: ifnull +65 -> 328
    //   266: aload 4
    //   268: astore 6
    //   270: aload 9
    //   272: iconst_1
    //   273: invokevirtual 482	android/net/ConnectivityManager:getNetworkInfo	(I)Landroid/net/NetworkInfo;
    //   276: astore 5
    //   278: aload 5
    //   280: ifnull +329 -> 609
    //   283: aload 4
    //   285: astore 6
    //   287: aload 5
    //   289: invokevirtual 487	android/net/NetworkInfo:isAvailable	()Z
    //   292: ifeq +317 -> 609
    //   295: aload 4
    //   297: astore 6
    //   299: new 108	java/lang/StringBuilder
    //   302: dup
    //   303: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   306: ldc_w 470
    //   309: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   312: aload 4
    //   314: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   317: ldc_w 470
    //   320: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   323: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   326: astore 5
    //   328: aload 5
    //   330: astore 6
    //   332: new 108	java/lang/StringBuilder
    //   335: dup
    //   336: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   339: ldc -74
    //   341: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   344: aload 5
    //   346: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   349: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   352: astore 4
    //   354: aload 4
    //   356: astore 6
    //   358: invokestatic 490	com/bandwidthx/library/bk:q	()Lcom/bandwidthx/library/ap;
    //   361: astore 4
    //   363: aload 4
    //   365: aload 4
    //   367: getfield 494	com/bandwidthx/library/ap:a	Ljava/lang/Long;
    //   370: putfield 496	com/bandwidthx/library/ap:b	Ljava/lang/Long;
    //   373: aload 4
    //   375: invokestatic 166	com/bandwidthx/library/eb:d	()Ljava/lang/Long;
    //   378: putfield 494	com/bandwidthx/library/ap:a	Ljava/lang/Long;
    //   381: invokestatic 499	com/bandwidthx/library/bk:i	()Lcom/bandwidthx/library/BxApproval;
    //   384: iconst_0
    //   385: invokestatic 63	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   388: invokevirtual 504	com/bandwidthx/library/BxApproval:b	(Ljava/lang/Boolean;)Lcom/bandwidthx/library/BxApproval$ConnectedState;
    //   391: astore 4
    //   393: invokestatic 490	com/bandwidthx/library/bk:q	()Lcom/bandwidthx/library/ap;
    //   396: astore 5
    //   398: aload 5
    //   400: aload 5
    //   402: getfield 496	com/bandwidthx/library/ap:b	Ljava/lang/Long;
    //   405: putfield 494	com/bandwidthx/library/ap:a	Ljava/lang/Long;
    //   408: new 108	java/lang/StringBuilder
    //   411: dup
    //   412: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   415: ldc -74
    //   417: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   420: astore 5
    //   422: aload 8
    //   424: invokevirtual 443	android/net/wifi/WifiManager:isWifiEnabled	()Z
    //   427: ifeq +227 -> 654
    //   430: aload 4
    //   432: invokevirtual 507	com/bandwidthx/library/BxApproval$ConnectedState:toString	()Ljava/lang/String;
    //   435: astore 4
    //   437: aload 5
    //   439: aload 4
    //   441: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   444: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   447: astore 4
    //   449: aload 4
    //   451: astore 5
    //   453: invokestatic 509	com/bandwidthx/library/bi:f	()Ljava/lang/Boolean;
    //   456: invokevirtual 202	java/lang/Boolean:booleanValue	()Z
    //   459: ifeq +26 -> 485
    //   462: new 108	java/lang/StringBuilder
    //   465: dup
    //   466: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   469: aload 4
    //   471: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   474: ldc_w 427
    //   477: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   480: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   483: astore 5
    //   485: aload 5
    //   487: astore 4
    //   489: invokestatic 166	com/bandwidthx/library/eb:d	()Ljava/lang/Long;
    //   492: invokevirtual 153	java/lang/Long:longValue	()J
    //   495: getstatic 53	com/bandwidthx/library/bj:c	Ljava/lang/Long;
    //   498: invokevirtual 153	java/lang/Long:longValue	()J
    //   501: lcmp
    //   502: ifgt +23 -> 525
    //   505: invokestatic 166	com/bandwidthx/library/eb:d	()Ljava/lang/Long;
    //   508: invokevirtual 153	java/lang/Long:longValue	()J
    //   511: getstatic 53	com/bandwidthx/library/bj:c	Ljava/lang/Long;
    //   514: invokevirtual 153	java/lang/Long:longValue	()J
    //   517: ldc2_w 510
    //   520: lsub
    //   521: lcmp
    //   522: ifge +211 -> 733
    //   525: getstatic 58	com/bandwidthx/library/bj:d	Ljava/util/LinkedList;
    //   528: invokevirtual 514	java/util/LinkedList:poll	()Ljava/lang/Object;
    //   531: checkcast 516	com/bandwidthx/library/bj$a
    //   534: astore 5
    //   536: aload 5
    //   538: ifnull +159 -> 697
    //   541: aload 5
    //   543: getfield 518	com/bandwidthx/library/bj$a:a	Ljava/lang/String;
    //   546: aload 5
    //   548: getfield 520	com/bandwidthx/library/bj$a:b	Ljava/lang/String;
    //   551: aload 5
    //   553: getfield 522	com/bandwidthx/library/bj$a:c	Ljava/lang/String;
    //   556: aload 5
    //   558: getfield 524	com/bandwidthx/library/bj$a:d	Ljava/lang/String;
    //   561: aload 5
    //   563: getfield 525	com/bandwidthx/library/bj$a:e	Ljava/lang/Boolean;
    //   566: aload 5
    //   568: getfield 528	com/bandwidthx/library/bj$a:f	Ljava/lang/Integer;
    //   571: aload 5
    //   573: getfield 530	com/bandwidthx/library/bj$a:g	Ljava/lang/String;
    //   576: aload 5
    //   578: getfield 532	com/bandwidthx/library/bj$a:h	Ljava/lang/Long;
    //   581: invokestatic 535	com/bandwidthx/library/bj:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;)V
    //   584: goto -59 -> 525
    //   587: astore_0
    //   588: aload_0
    //   589: invokestatic 386	com/bandwidthx/library/ax:a	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   592: invokestatic 537	com/bandwidthx/library/bj:c	(Ljava/lang/String;)V
    //   595: ldc 2
    //   597: monitorexit
    //   598: return
    //   599: aload 6
    //   601: invokevirtual 459	android/net/wifi/WifiInfo:getSSID	()Ljava/lang/String;
    //   604: astore 4
    //   606: goto -367 -> 239
    //   609: aload 4
    //   611: astore 6
    //   613: new 108	java/lang/StringBuilder
    //   616: dup
    //   617: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   620: ldc_w 539
    //   623: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   626: aload 4
    //   628: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   631: ldc_w 539
    //   634: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   637: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   640: astore 5
    //   642: goto -314 -> 328
    //   645: astore 4
    //   647: ldc 95
    //   649: astore 6
    //   651: goto -293 -> 358
    //   654: new 108	java/lang/StringBuilder
    //   657: dup
    //   658: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   661: ldc_w 541
    //   664: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   667: aload 4
    //   669: invokevirtual 507	com/bandwidthx/library/BxApproval$ConnectedState:toString	()Ljava/lang/String;
    //   672: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   675: ldc -2
    //   677: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   680: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   683: astore 4
    //   685: goto -248 -> 437
    //   688: astore 4
    //   690: ldc 95
    //   692: astore 4
    //   694: goto -205 -> 489
    //   697: aload 7
    //   699: aload 6
    //   701: aload 4
    //   703: aload_0
    //   704: aload_1
    //   705: aload_2
    //   706: aload_3
    //   707: invokestatic 166	com/bandwidthx/library/eb:d	()Ljava/lang/Long;
    //   710: invokestatic 535	com/bandwidthx/library/bj:a	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/Long;)V
    //   713: goto -118 -> 595
    //   716: astore_0
    //   717: aload_0
    //   718: invokestatic 386	com/bandwidthx/library/ax:a	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   721: invokestatic 537	com/bandwidthx/library/bj:c	(Ljava/lang/String;)V
    //   724: goto -129 -> 595
    //   727: astore_0
    //   728: ldc 2
    //   730: monitorexit
    //   731: aload_0
    //   732: athrow
    //   733: new 516	com/bandwidthx/library/bj$a
    //   736: dup
    //   737: invokespecial 542	com/bandwidthx/library/bj$a:<init>	()V
    //   740: astore 5
    //   742: aload 5
    //   744: aload 7
    //   746: putfield 518	com/bandwidthx/library/bj$a:a	Ljava/lang/String;
    //   749: aload 5
    //   751: aload 6
    //   753: putfield 520	com/bandwidthx/library/bj$a:b	Ljava/lang/String;
    //   756: aload 5
    //   758: aload 4
    //   760: putfield 522	com/bandwidthx/library/bj$a:c	Ljava/lang/String;
    //   763: aload 5
    //   765: aload_0
    //   766: putfield 524	com/bandwidthx/library/bj$a:d	Ljava/lang/String;
    //   769: aload 5
    //   771: aload_1
    //   772: putfield 525	com/bandwidthx/library/bj$a:e	Ljava/lang/Boolean;
    //   775: aload 5
    //   777: aload_2
    //   778: putfield 528	com/bandwidthx/library/bj$a:f	Ljava/lang/Integer;
    //   781: aload 5
    //   783: aload_3
    //   784: putfield 530	com/bandwidthx/library/bj$a:g	Ljava/lang/String;
    //   787: aload 5
    //   789: invokestatic 166	com/bandwidthx/library/eb:d	()Ljava/lang/Long;
    //   792: putfield 532	com/bandwidthx/library/bj$a:h	Ljava/lang/Long;
    //   795: getstatic 58	com/bandwidthx/library/bj:d	Ljava/util/LinkedList;
    //   798: aload 5
    //   800: invokevirtual 543	java/util/LinkedList:add	(Ljava/lang/Object;)Z
    //   803: pop
    //   804: goto -209 -> 595
    //   807: astore 5
    //   809: goto -115 -> 694
    //   812: astore 4
    //   814: goto -163 -> 651
    //   817: astore 5
    //   819: aload 4
    //   821: astore 5
    //   823: goto -495 -> 328
    //   826: aload 4
    //   828: astore 7
    //   830: goto -755 -> 75
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	833	0	paramString1	String
    //   0	833	1	paramBoolean	Boolean
    //   0	833	2	paramInteger	Integer
    //   0	833	3	paramString2	String
    //   9	618	4	localObject1	Object
    //   645	23	4	localThrowable1	Throwable
    //   683	1	4	str1	String
    //   688	1	4	localException1	Exception
    //   692	67	4	str2	String
    //   812	15	4	localThrowable2	Throwable
    //   77	722	5	localObject2	Object
    //   807	1	5	localException2	Exception
    //   817	1	5	localException3	Exception
    //   821	1	5	localThrowable3	Throwable
    //   110	642	6	localObject3	Object
    //   73	756	7	localObject4	Object
    //   91	332	8	localWifiManager	android.net.wifi.WifiManager
    //   255	16	9	localConnectivityManager	android.net.ConnectivityManager
    // Exception table:
    //   from	to	target	type
    //   3	11	587	java/lang/Exception
    //   11	43	587	java/lang/Exception
    //   43	75	587	java/lang/Exception
    //   79	93	587	java/lang/Exception
    //   97	112	587	java/lang/Exception
    //   116	124	587	java/lang/Exception
    //   128	136	587	java/lang/Exception
    //   140	148	587	java/lang/Exception
    //   152	163	587	java/lang/Exception
    //   167	189	587	java/lang/Exception
    //   193	204	587	java/lang/Exception
    //   204	239	587	java/lang/Exception
    //   332	354	587	java/lang/Exception
    //   489	525	587	java/lang/Exception
    //   525	536	587	java/lang/Exception
    //   541	584	587	java/lang/Exception
    //   599	606	587	java/lang/Exception
    //   697	713	587	java/lang/Exception
    //   733	804	587	java/lang/Exception
    //   97	112	645	java/lang/Throwable
    //   116	124	645	java/lang/Throwable
    //   128	136	645	java/lang/Throwable
    //   140	148	645	java/lang/Throwable
    //   152	163	645	java/lang/Throwable
    //   167	189	645	java/lang/Throwable
    //   193	204	645	java/lang/Throwable
    //   204	239	645	java/lang/Throwable
    //   599	606	645	java/lang/Throwable
    //   358	437	688	java/lang/Exception
    //   437	449	688	java/lang/Exception
    //   654	685	688	java/lang/Exception
    //   3	11	716	java/lang/Throwable
    //   11	43	716	java/lang/Throwable
    //   43	75	716	java/lang/Throwable
    //   79	93	716	java/lang/Throwable
    //   358	437	716	java/lang/Throwable
    //   437	449	716	java/lang/Throwable
    //   453	485	716	java/lang/Throwable
    //   489	525	716	java/lang/Throwable
    //   525	536	716	java/lang/Throwable
    //   541	584	716	java/lang/Throwable
    //   654	685	716	java/lang/Throwable
    //   697	713	716	java/lang/Throwable
    //   733	804	716	java/lang/Throwable
    //   3	11	727	finally
    //   11	43	727	finally
    //   43	75	727	finally
    //   79	93	727	finally
    //   97	112	727	finally
    //   116	124	727	finally
    //   128	136	727	finally
    //   140	148	727	finally
    //   152	163	727	finally
    //   167	189	727	finally
    //   193	204	727	finally
    //   204	239	727	finally
    //   243	257	727	finally
    //   270	278	727	finally
    //   287	295	727	finally
    //   299	328	727	finally
    //   332	354	727	finally
    //   358	437	727	finally
    //   437	449	727	finally
    //   453	485	727	finally
    //   489	525	727	finally
    //   525	536	727	finally
    //   541	584	727	finally
    //   588	595	727	finally
    //   599	606	727	finally
    //   613	642	727	finally
    //   654	685	727	finally
    //   697	713	727	finally
    //   717	724	727	finally
    //   733	804	727	finally
    //   453	485	807	java/lang/Exception
    //   243	257	812	java/lang/Throwable
    //   270	278	812	java/lang/Throwable
    //   287	295	812	java/lang/Throwable
    //   299	328	812	java/lang/Throwable
    //   332	354	812	java/lang/Throwable
    //   613	642	812	java/lang/Throwable
    //   243	257	817	java/lang/Exception
    //   270	278	817	java/lang/Exception
    //   287	295	817	java/lang/Exception
    //   299	328	817	java/lang/Exception
    //   613	642	817	java/lang/Exception
  }
  
  /* Error */
  private static void a(String paramString1, String paramString2, String paramString3, String paramString4, Boolean paramBoolean, Integer paramInteger, String paramString5, Long paramLong)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 166	com/bandwidthx/library/eb:d	()Ljava/lang/Long;
    //   6: invokevirtual 153	java/lang/Long:longValue	()J
    //   9: invokestatic 548	java/lang/System:currentTimeMillis	()J
    //   12: lsub
    //   13: ldc2_w 148
    //   16: ldiv
    //   17: invokestatic 51	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   20: astore 9
    //   22: getstatic 65	com/bandwidthx/library/bj:e	Ljava/lang/Boolean;
    //   25: invokevirtual 202	java/lang/Boolean:booleanValue	()Z
    //   28: istore 8
    //   30: iload 8
    //   32: ifeq +7 -> 39
    //   35: ldc 2
    //   37: monitorexit
    //   38: return
    //   39: iconst_1
    //   40: invokestatic 63	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   43: putstatic 65	com/bandwidthx/library/bj:e	Ljava/lang/Boolean;
    //   46: new 108	java/lang/StringBuilder
    //   49: dup
    //   50: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   53: ldc_w 550
    //   56: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: aload 7
    //   61: iconst_0
    //   62: invokestatic 63	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   65: invokestatic 552	com/bandwidthx/library/ax:b	(Ljava/lang/Long;Ljava/lang/Boolean;)Ljava/lang/String;
    //   68: invokevirtual 553	java/lang/String:toString	()Ljava/lang/String;
    //   71: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: ldc -74
    //   76: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: aload 7
    //   81: iconst_0
    //   82: invokestatic 63	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   85: invokestatic 555	com/bandwidthx/library/ax:a	(Ljava/lang/Long;Ljava/lang/Boolean;)Ljava/lang/String;
    //   88: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   91: astore 10
    //   93: aload 9
    //   95: invokevirtual 153	java/lang/Long:longValue	()J
    //   98: invokestatic 561	java/lang/Math:abs	(J)J
    //   101: lconst_1
    //   102: lcmp
    //   103: ifle +628 -> 731
    //   106: new 108	java/lang/StringBuilder
    //   109: dup
    //   110: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   113: ldc -74
    //   115: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: aload 9
    //   120: invokevirtual 239	java/lang/Long:toString	()Ljava/lang/String;
    //   123: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   129: astore 9
    //   131: aload 10
    //   133: aload 9
    //   135: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: ldc_w 563
    //   141: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   147: astore 11
    //   149: aload 11
    //   151: astore 10
    //   153: invokestatic 566	com/bandwidthx/library/bk:b	()Lcom/bandwidthx/library/bk;
    //   156: ifnull +151 -> 307
    //   159: invokestatic 566	com/bandwidthx/library/bk:b	()Lcom/bandwidthx/library/bk;
    //   162: pop
    //   163: aload 11
    //   165: astore 9
    //   167: invokestatic 570	com/bandwidthx/library/bk:s	()Lcom/bandwidthx/library/ar;
    //   170: ifnull +62 -> 232
    //   173: invokestatic 566	com/bandwidthx/library/bk:b	()Lcom/bandwidthx/library/bk;
    //   176: pop
    //   177: aload 11
    //   179: astore 9
    //   181: invokestatic 573	com/bandwidthx/library/bk:h	()Lcom/bandwidthx/library/aa;
    //   184: invokevirtual 575	com/bandwidthx/library/aa:k	()Ljava/lang/Boolean;
    //   187: invokevirtual 202	java/lang/Boolean:booleanValue	()Z
    //   190: ifne +42 -> 232
    //   193: new 108	java/lang/StringBuilder
    //   196: dup
    //   197: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   200: ldc_w 541
    //   203: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   206: aload 11
    //   208: iconst_1
    //   209: aload 11
    //   211: invokevirtual 372	java/lang/String:length	()I
    //   214: iconst_1
    //   215: isub
    //   216: invokevirtual 137	java/lang/String:substring	(II)Ljava/lang/String;
    //   219: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   222: ldc -2
    //   224: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   227: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   230: astore 9
    //   232: invokestatic 566	com/bandwidthx/library/bk:b	()Lcom/bandwidthx/library/bk;
    //   235: pop
    //   236: aload 9
    //   238: astore 10
    //   240: invokestatic 291	com/bandwidthx/library/bk:u	()Lcom/bandwidthx/library/au;
    //   243: ifnull +64 -> 307
    //   246: invokestatic 566	com/bandwidthx/library/bk:b	()Lcom/bandwidthx/library/bk;
    //   249: pop
    //   250: invokestatic 291	com/bandwidthx/library/bk:u	()Lcom/bandwidthx/library/au;
    //   253: pop
    //   254: aload 9
    //   256: astore 10
    //   258: invokestatic 576	com/bandwidthx/library/au:f	()Ljava/lang/Boolean;
    //   261: invokevirtual 202	java/lang/Boolean:booleanValue	()Z
    //   264: ifeq +43 -> 307
    //   267: new 108	java/lang/StringBuilder
    //   270: dup
    //   271: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   274: ldc_w 578
    //   277: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   280: aload 9
    //   282: iconst_1
    //   283: aload 9
    //   285: invokevirtual 372	java/lang/String:length	()I
    //   288: iconst_1
    //   289: isub
    //   290: invokevirtual 137	java/lang/String:substring	(II)Ljava/lang/String;
    //   293: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   296: ldc_w 580
    //   299: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   302: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   305: astore 10
    //   307: new 108	java/lang/StringBuilder
    //   310: dup
    //   311: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   314: aload 10
    //   316: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   319: ldc -74
    //   321: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   324: aload_0
    //   325: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   328: aload_1
    //   329: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   332: aload_2
    //   333: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   336: ldc_w 582
    //   339: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   342: aload_3
    //   343: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   346: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   349: astore_0
    //   350: aload 4
    //   352: invokevirtual 202	java/lang/Boolean:booleanValue	()Z
    //   355: ifeq +42 -> 397
    //   358: getstatic 73	com/bandwidthx/library/bj:h	Ljava/util/LinkedList;
    //   361: aload_0
    //   362: invokevirtual 543	java/util/LinkedList:add	(Ljava/lang/Object;)Z
    //   365: pop
    //   366: getstatic 71	com/bandwidthx/library/bj:g	Ljava/lang/Boolean;
    //   369: invokevirtual 202	java/lang/Boolean:booleanValue	()Z
    //   372: ifne +25 -> 397
    //   375: iconst_1
    //   376: invokestatic 63	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   379: putstatic 71	com/bandwidthx/library/bj:g	Ljava/lang/Boolean;
    //   382: new 6	com/bandwidthx/library/bj$1
    //   385: dup
    //   386: invokespecial 583	com/bandwidthx/library/bj$1:<init>	()V
    //   389: iconst_0
    //   390: anewarray 585	java/lang/Void
    //   393: invokevirtual 589	com/bandwidthx/library/bj$1:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
    //   396: pop
    //   397: aload 6
    //   399: ifnull +228 -> 627
    //   402: aload 6
    //   404: invokevirtual 372	java/lang/String:length	()I
    //   407: ifle +220 -> 627
    //   410: lconst_1
    //   411: invokestatic 51	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   414: invokestatic 594	com/bandwidthx/library/cs:a	(Ljava/lang/Long;)Ljava/lang/Boolean;
    //   417: invokevirtual 202	java/lang/Boolean:booleanValue	()Z
    //   420: ifne +207 -> 627
    //   423: invokestatic 598	com/bandwidthx/library/bk:D	()Lcom/bandwidthx/library/cu;
    //   426: ldc_w 600
    //   429: ldc 95
    //   431: invokevirtual 605	com/bandwidthx/library/cu:a	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   434: astore_1
    //   435: aload_1
    //   436: ldc_w 607
    //   439: invokevirtual 610	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   442: ifeq +64 -> 506
    //   445: lconst_0
    //   446: invokestatic 51	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   449: putstatic 79	com/bandwidthx/library/bj:k	Ljava/lang/Long;
    //   452: lconst_0
    //   453: invokestatic 51	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   456: putstatic 85	com/bandwidthx/library/bj:m	Ljava/lang/Long;
    //   459: invokestatic 598	com/bandwidthx/library/bk:D	()Lcom/bandwidthx/library/cu;
    //   462: ldc_w 612
    //   465: getstatic 79	com/bandwidthx/library/bj:k	Ljava/lang/Long;
    //   468: invokevirtual 153	java/lang/Long:longValue	()J
    //   471: invokevirtual 615	com/bandwidthx/library/cu:b	(Ljava/lang/String;J)V
    //   474: invokestatic 598	com/bandwidthx/library/bk:D	()Lcom/bandwidthx/library/cu;
    //   477: ldc_w 617
    //   480: getstatic 85	com/bandwidthx/library/bj:m	Ljava/lang/Long;
    //   483: invokevirtual 153	java/lang/Long:longValue	()J
    //   486: invokevirtual 615	com/bandwidthx/library/cu:b	(Ljava/lang/String;J)V
    //   489: invokestatic 598	com/bandwidthx/library/bk:D	()Lcom/bandwidthx/library/cu;
    //   492: ldc_w 600
    //   495: ldc 95
    //   497: invokevirtual 620	com/bandwidthx/library/cu:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   500: invokestatic 598	com/bandwidthx/library/bk:D	()Lcom/bandwidthx/library/cu;
    //   503: invokevirtual 622	com/bandwidthx/library/cu:a	()V
    //   506: aload_1
    //   507: invokestatic 194	com/bandwidthx/library/aa:h	()Ljava/lang/String;
    //   510: invokevirtual 610	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   513: ifne +114 -> 627
    //   516: getstatic 79	com/bandwidthx/library/bj:k	Ljava/lang/Long;
    //   519: invokevirtual 153	java/lang/Long:longValue	()J
    //   522: aload 7
    //   524: invokevirtual 153	java/lang/Long:longValue	()J
    //   527: getstatic 83	com/bandwidthx/library/bj:l	Ljava/lang/Long;
    //   530: invokevirtual 153	java/lang/Long:longValue	()J
    //   533: lsub
    //   534: lcmp
    //   535: ifge +15 -> 550
    //   538: aload 7
    //   540: putstatic 79	com/bandwidthx/library/bj:k	Ljava/lang/Long;
    //   543: lconst_0
    //   544: invokestatic 51	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   547: putstatic 85	com/bandwidthx/library/bj:m	Ljava/lang/Long;
    //   550: getstatic 85	com/bandwidthx/library/bj:m	Ljava/lang/Long;
    //   553: invokevirtual 153	java/lang/Long:longValue	()J
    //   556: lconst_1
    //   557: ladd
    //   558: invokestatic 51	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   561: astore_1
    //   562: aload_1
    //   563: putstatic 85	com/bandwidthx/library/bj:m	Ljava/lang/Long;
    //   566: aload_1
    //   567: invokevirtual 153	java/lang/Long:longValue	()J
    //   570: getstatic 89	com/bandwidthx/library/bj:n	Ljava/lang/Long;
    //   573: invokevirtual 153	java/lang/Long:longValue	()J
    //   576: lcmp
    //   577: ifgt +14 -> 591
    //   580: invokestatic 626	com/bandwidthx/library/bk:Z	()Lcom/bandwidthx/library/do;
    //   583: aload 5
    //   585: aload 6
    //   587: aload_0
    //   588: invokevirtual 631	com/bandwidthx/library/do:a	(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V
    //   591: invokestatic 598	com/bandwidthx/library/bk:D	()Lcom/bandwidthx/library/cu;
    //   594: ldc_w 612
    //   597: getstatic 79	com/bandwidthx/library/bj:k	Ljava/lang/Long;
    //   600: invokevirtual 153	java/lang/Long:longValue	()J
    //   603: invokevirtual 615	com/bandwidthx/library/cu:b	(Ljava/lang/String;J)V
    //   606: invokestatic 598	com/bandwidthx/library/bk:D	()Lcom/bandwidthx/library/cu;
    //   609: ldc_w 617
    //   612: getstatic 85	com/bandwidthx/library/bj:m	Ljava/lang/Long;
    //   615: invokevirtual 153	java/lang/Long:longValue	()J
    //   618: invokevirtual 615	com/bandwidthx/library/cu:b	(Ljava/lang/String;J)V
    //   621: invokestatic 598	com/bandwidthx/library/bk:D	()Lcom/bandwidthx/library/cu;
    //   624: invokevirtual 622	com/bandwidthx/library/cu:a	()V
    //   627: aload_0
    //   628: invokevirtual 372	java/lang/String:length	()I
    //   631: sipush 3000
    //   634: if_icmple +55 -> 689
    //   637: aload_0
    //   638: iconst_0
    //   639: sipush 3000
    //   642: invokevirtual 137	java/lang/String:substring	(II)Ljava/lang/String;
    //   645: invokestatic 537	com/bandwidthx/library/bj:c	(Ljava/lang/String;)V
    //   648: aload_0
    //   649: sipush 3000
    //   652: invokevirtual 132	java/lang/String:substring	(I)Ljava/lang/String;
    //   655: astore_0
    //   656: goto -29 -> 627
    //   659: astore_1
    //   660: new 108	java/lang/StringBuilder
    //   663: dup
    //   664: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   667: ldc_w 633
    //   670: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   673: aload_1
    //   674: invokevirtual 634	java/lang/Exception:toString	()Ljava/lang/String;
    //   677: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   680: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   683: invokestatic 537	com/bandwidthx/library/bj:c	(Ljava/lang/String;)V
    //   686: goto -59 -> 627
    //   689: aload_0
    //   690: invokestatic 537	com/bandwidthx/library/bj:c	(Ljava/lang/String;)V
    //   693: iconst_0
    //   694: invokestatic 63	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   697: putstatic 65	com/bandwidthx/library/bj:e	Ljava/lang/Boolean;
    //   700: goto -665 -> 35
    //   703: astore_0
    //   704: ldc 2
    //   706: monitorexit
    //   707: aload_0
    //   708: athrow
    //   709: astore_0
    //   710: aload_0
    //   711: invokestatic 386	com/bandwidthx/library/ax:a	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   714: invokestatic 537	com/bandwidthx/library/bj:c	(Ljava/lang/String;)V
    //   717: goto -24 -> 693
    //   720: astore_0
    //   721: aload_0
    //   722: invokestatic 386	com/bandwidthx/library/ax:a	(Ljava/lang/Throwable;)Ljava/lang/String;
    //   725: invokestatic 537	com/bandwidthx/library/bj:c	(Ljava/lang/String;)V
    //   728: goto -35 -> 693
    //   731: ldc 95
    //   733: astore 9
    //   735: goto -604 -> 131
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	738	0	paramString1	String
    //   0	738	1	paramString2	String
    //   0	738	2	paramString3	String
    //   0	738	3	paramString4	String
    //   0	738	4	paramBoolean	Boolean
    //   0	738	5	paramInteger	Integer
    //   0	738	6	paramString5	String
    //   0	738	7	paramLong	Long
    //   28	3	8	bool	boolean
    //   20	714	9	localObject1	Object
    //   91	224	10	localObject2	Object
    //   147	63	11	str	String
    // Exception table:
    //   from	to	target	type
    //   402	506	659	java/lang/Exception
    //   506	550	659	java/lang/Exception
    //   550	591	659	java/lang/Exception
    //   591	627	659	java/lang/Exception
    //   3	30	703	finally
    //   39	131	703	finally
    //   131	149	703	finally
    //   153	163	703	finally
    //   167	177	703	finally
    //   181	232	703	finally
    //   232	236	703	finally
    //   240	254	703	finally
    //   258	307	703	finally
    //   307	397	703	finally
    //   402	506	703	finally
    //   506	550	703	finally
    //   550	591	703	finally
    //   591	627	703	finally
    //   627	656	703	finally
    //   660	686	703	finally
    //   689	693	703	finally
    //   693	700	703	finally
    //   710	717	703	finally
    //   721	728	703	finally
    //   3	30	709	java/lang/Exception
    //   39	131	709	java/lang/Exception
    //   131	149	709	java/lang/Exception
    //   153	163	709	java/lang/Exception
    //   167	177	709	java/lang/Exception
    //   181	232	709	java/lang/Exception
    //   232	236	709	java/lang/Exception
    //   240	254	709	java/lang/Exception
    //   258	307	709	java/lang/Exception
    //   307	397	709	java/lang/Exception
    //   627	656	709	java/lang/Exception
    //   660	686	709	java/lang/Exception
    //   689	693	709	java/lang/Exception
    //   3	30	720	java/lang/Throwable
    //   39	131	720	java/lang/Throwable
    //   131	149	720	java/lang/Throwable
    //   153	163	720	java/lang/Throwable
    //   167	177	720	java/lang/Throwable
    //   181	232	720	java/lang/Throwable
    //   232	236	720	java/lang/Throwable
    //   240	254	720	java/lang/Throwable
    //   258	307	720	java/lang/Throwable
    //   307	397	720	java/lang/Throwable
    //   402	506	720	java/lang/Throwable
    //   506	550	720	java/lang/Throwable
    //   550	591	720	java/lang/Throwable
    //   591	627	720	java/lang/Throwable
    //   627	656	720	java/lang/Throwable
    //   660	686	720	java/lang/Throwable
    //   689	693	720	java/lang/Throwable
  }
  
  public static void a(String paramString, Throwable paramThrowable)
  {
    a(paramString, paramThrowable, Boolean.valueOf(true));
  }
  
  public static void a(String paramString, Throwable paramThrowable, Boolean paramBoolean)
  {
    String str2 = ax.a(paramThrowable);
    String str1 = "at UNKNOWN";
    localObject = str1;
    try
    {
      Integer localInteger1 = Integer.valueOf(str2.indexOf("at " + aa.c()));
      paramThrowable = str1;
      localObject = str1;
      if (localInteger1.intValue() > 0)
      {
        localObject = str1;
        Integer localInteger2 = Integer.valueOf(str2.indexOf(")", localInteger1.intValue()));
        paramThrowable = str1;
        localObject = str1;
        if (localInteger2.intValue() > 0)
        {
          paramThrowable = str1;
          localObject = str1;
          if (localInteger1.intValue() < localInteger2.intValue())
          {
            localObject = str1;
            str1 = str2.substring(localInteger1.intValue(), localInteger2.intValue() + 1);
            localObject = str1;
            localInteger1 = Integer.valueOf(str2.lastIndexOf("(", localInteger1.intValue()));
            paramThrowable = str1;
            localObject = str1;
            if (localInteger1.intValue() > 0)
            {
              localObject = str1;
              localInteger2 = Integer.valueOf(str2.lastIndexOf("at ", localInteger1.intValue()));
              paramThrowable = str1;
              localObject = str1;
              if (localInteger2.intValue() > 0)
              {
                paramThrowable = str1;
                localObject = str1;
                if (localInteger2.intValue() < localInteger1.intValue())
                {
                  localObject = str1;
                  paramThrowable = str1 + " calling " + str2.substring(localInteger2.intValue() + 3, localInteger1.intValue());
                }
              }
            }
          }
        }
      }
    }
    catch (Exception paramThrowable)
    {
      for (;;)
      {
        paramThrowable = (Throwable)localObject;
      }
    }
    localObject = new StringBuilder().append("UNEXPECTED EXCEPTION");
    if (paramString.length() > 0) {}
    for (paramString = " " + paramString;; paramString = "")
    {
      paramString = paramString + " (Build: " + aa.g() + " " + aa.f() + " " + aa.h() + ") " + paramThrowable + " ::: " + str2;
      if (str2.contains("OutOfMemory")) {
        paramBoolean = Boolean.valueOf(false);
      }
      if (!paramBoolean.booleanValue()) {
        break;
      }
      a(paramString, Boolean.valueOf(true), Integer.valueOf(1), "Exception");
      return;
    }
    a(paramString, Boolean.valueOf(true), Integer.valueOf(0), null);
  }
  
  public static void a(Throwable paramThrowable)
  {
    a("", paramThrowable, Boolean.valueOf(true));
  }
  
  public static void a(Throwable paramThrowable, Boolean paramBoolean)
  {
    a("", paramThrowable, paramBoolean);
  }
  
  public static void b()
  {
    k = Long.valueOf(bk.D().a("FeedbackTicks", k.longValue()));
    m = Long.valueOf(bk.D().a("FeedbackCount", m.longValue()));
  }
  
  public static void b(String paramString)
  {
    a(paramString, Boolean.valueOf(true), Integer.valueOf(0), null);
  }
  
  public static void c()
  {
    if (b.get(Integer.valueOf(Process.myTid())) != null) {
      b.remove(Integer.valueOf(Process.myTid()));
    }
  }
  
  public static void c(String paramString)
  {
    for (paramString = f;; paramString = "") {
      for (;;)
      {
        try
        {
          if (!o.booleanValue())
          {
            int i1 = bk.G().b("bx_syslog").intValue();
            if (i1 == 0) {
              continue;
            }
            paramString = bk.e().getResources().getString(i1);
            if (paramString.equalsIgnoreCase("true")) {
              p = Boolean.valueOf(true);
            }
            o = Boolean.valueOf(true);
          }
        }
        catch (Throwable paramString)
        {
          continue;
        }
        catch (Exception paramString)
        {
          continue;
        }
        try
        {
          if ((!p.booleanValue()) && (!Boolean.valueOf(false).booleanValue()) && (!aa.j().booleanValue())) {
            bk.K().c().booleanValue();
          }
          return;
        }
        catch (Throwable paramString)
        {
          return;
        }
        catch (Exception paramString)
        {
          return;
        }
      }
    }
  }
  
  public static String d()
  {
    return a("", null);
  }
  
  public static String d(String paramString)
  {
    try
    {
      if (q.length() == 0)
      {
        q = "l" + Integer.toString(0) + "G";
        q += "Forb";
        q = q + Integer.toString(4) + "ndW";
        q += "!";
        q = q + "dth" + Character.toString('X');
      }
      paramString = av.a(q, paramString, Boolean.valueOf(false));
      return paramString;
    }
    catch (Exception paramString) {}
    return "(UNABLE TO ENCRYPT)";
  }
  
  public static String e(String paramString)
  {
    String str2 = "";
    String str1 = str2;
    if (paramString.length() > 10)
    {
      String str3 = paramString.substring(0, 1);
      str1 = str2;
      if (str3.equals("[")) {
        str1 = paramString.substring(1, paramString.indexOf("]"));
      }
      if (str3.equals("(")) {
        str1 = paramString.substring(1, paramString.indexOf(")"));
      }
      if (str3.equals("<")) {
        str1 = paramString.substring(1, paramString.indexOf(">"));
      }
      if (str3.equals("{")) {
        str1 = paramString.substring(1, paramString.indexOf("}"));
      }
    }
    return str1;
  }
  
  private static String f()
  {
    String str = dc.a() + "/" + aa.c();
    File localFile = new File(str);
    if (!localFile.exists()) {
      localFile.mkdir();
    }
    return str;
  }
  
  public static String f(String paramString)
  {
    return a(paramString, null);
  }
  
  /* Error */
  private static String g()
  {
    // Byte code:
    //   0: ldc 95
    //   2: astore_2
    //   3: aload_2
    //   4: astore_3
    //   5: invokestatic 430	com/bandwidthx/library/bk:e	()Landroid/content/Context;
    //   8: invokevirtual 743	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   11: astore 5
    //   13: aload_2
    //   14: astore_3
    //   15: aload 5
    //   17: iconst_0
    //   18: invokevirtual 749	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   21: invokeinterface 752 1 0
    //   26: astore 6
    //   28: aload_2
    //   29: astore_3
    //   30: aload_2
    //   31: astore 4
    //   33: aload 6
    //   35: invokeinterface 333 1 0
    //   40: ifeq +259 -> 299
    //   43: aload_2
    //   44: astore_3
    //   45: aload 6
    //   47: invokeinterface 339 1 0
    //   52: checkcast 754	android/content/pm/ApplicationInfo
    //   55: astore 4
    //   57: invokestatic 573	com/bandwidthx/library/bk:h	()Lcom/bandwidthx/library/aa;
    //   60: pop
    //   61: aload 4
    //   63: getfield 758	android/content/pm/ApplicationInfo:uid	I
    //   66: invokestatic 389	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   69: invokestatic 761	com/bandwidthx/library/aa:a	(Ljava/lang/Integer;)Ljava/lang/String;
    //   72: astore 7
    //   74: aload 5
    //   76: aload 7
    //   78: iconst_2
    //   79: invokevirtual 765	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   82: astore 4
    //   84: aload 4
    //   86: ifnull -58 -> 28
    //   89: aload 4
    //   91: getfield 771	android/content/pm/PackageInfo:receivers	[Landroid/content/pm/ActivityInfo;
    //   94: astore_3
    //   95: aload_3
    //   96: ifnull -68 -> 28
    //   99: aload_3
    //   100: arraylength
    //   101: istore_1
    //   102: iconst_0
    //   103: istore_0
    //   104: iload_0
    //   105: iload_1
    //   106: if_icmpge -78 -> 28
    //   109: aload_3
    //   110: iload_0
    //   111: aaload
    //   112: getfield 776	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   115: ldc_w 778
    //   118: invokevirtual 396	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   121: ifeq +163 -> 284
    //   124: new 108	java/lang/StringBuilder
    //   127: dup
    //   128: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   131: aload_2
    //   132: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   135: astore 8
    //   137: aload_2
    //   138: invokevirtual 372	java/lang/String:length	()I
    //   141: ifle +164 -> 305
    //   144: ldc_w 780
    //   147: astore_3
    //   148: aload 8
    //   150: aload_3
    //   151: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   154: aload 4
    //   156: getfield 784	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   159: aload 5
    //   161: invokevirtual 788	android/content/pm/ApplicationInfo:loadLabel	(Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
    //   164: invokeinterface 791 1 0
    //   169: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: ldc -74
    //   174: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: aload 4
    //   179: getfield 794	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   182: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   185: ldc_w 796
    //   188: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   191: aload 7
    //   193: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   196: ldc_w 660
    //   199: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   202: aload 4
    //   204: getfield 800	android/content/pm/PackageInfo:lastUpdateTime	J
    //   207: ldc2_w 148
    //   210: ldiv
    //   211: invokestatic 51	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   214: invokestatic 803	com/bandwidthx/library/ax:g	(Ljava/lang/Long;)Ljava/lang/String;
    //   217: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: astore 7
    //   222: aload 4
    //   224: getfield 784	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   227: getfield 806	android/content/pm/ApplicationInfo:enabled	Z
    //   230: ifeq +81 -> 311
    //   233: ldc 95
    //   235: astore_3
    //   236: aload 7
    //   238: aload_3
    //   239: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   242: astore 7
    //   244: aload 4
    //   246: getfield 784	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   249: getfield 809	android/content/pm/ApplicationInfo:flags	I
    //   252: ldc_w 810
    //   255: iand
    //   256: ifeq +22 -> 278
    //   259: ldc_w 812
    //   262: astore_3
    //   263: aload 7
    //   265: aload_3
    //   266: invokevirtual 161	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   269: invokevirtual 174	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   272: astore_3
    //   273: aload_3
    //   274: astore_2
    //   275: goto +27 -> 302
    //   278: ldc 95
    //   280: astore_3
    //   281: goto -18 -> 263
    //   284: iload_0
    //   285: iconst_1
    //   286: iadd
    //   287: istore_0
    //   288: goto -184 -> 104
    //   291: astore_3
    //   292: goto +10 -> 302
    //   295: astore_2
    //   296: aload_3
    //   297: astore 4
    //   299: aload 4
    //   301: areturn
    //   302: goto -274 -> 28
    //   305: ldc 95
    //   307: astore_3
    //   308: goto -160 -> 148
    //   311: ldc_w 814
    //   314: astore_3
    //   315: goto -79 -> 236
    // Local variable table:
    //   start	length	slot	name	signature
    //   103	185	0	i1	int
    //   101	6	1	i2	int
    //   2	273	2	localObject1	Object
    //   295	1	2	localException1	Exception
    //   4	277	3	localObject2	Object
    //   291	6	3	localException2	Exception
    //   307	8	3	str	String
    //   31	269	4	localObject3	Object
    //   11	149	5	localPackageManager	android.content.pm.PackageManager
    //   26	20	6	localIterator	java.util.Iterator
    //   72	192	7	localObject4	Object
    //   135	14	8	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   57	84	291	java/lang/Exception
    //   89	95	291	java/lang/Exception
    //   99	102	291	java/lang/Exception
    //   109	144	291	java/lang/Exception
    //   148	233	291	java/lang/Exception
    //   236	259	291	java/lang/Exception
    //   263	273	291	java/lang/Exception
    //   5	13	295	java/lang/Exception
    //   15	28	295	java/lang/Exception
    //   33	43	295	java/lang/Exception
    //   45	57	295	java/lang/Exception
  }
}
