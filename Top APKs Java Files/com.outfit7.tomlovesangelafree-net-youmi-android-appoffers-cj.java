package net.youmi.android.appoffers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.ArrayList;
import java.util.List;

class cj
  implements Runnable
{
  private static String a = "ce3f25401e3b961039547f020c0902ca";
  private static String b = "f6850572eb11a1920c30d8a469b4a4a7";
  private static List c;
  private Context d;
  private int e;
  private StringBuilder f = new StringBuilder(512);
  
  private cj(Context paramContext, int paramInt)
  {
    this.d = paramContext.getApplicationContext();
    this.e = paramInt;
  }
  
  static void a(Context paramContext)
  {
    try
    {
      new Thread(new cj(paramContext, 0)).start();
      return;
    }
    catch (Throwable paramContext) {}
  }
  
  static void a(Context paramContext, List paramList)
  {
    if (paramContext == null) {}
    for (;;)
    {
      return;
      if (paramList == null) {
        continue;
      }
      try
      {
        if (paramList.size() == 0) {
          continue;
        }
        paramContext = new cj(paramContext, 2);
        int j = 0;
        int i = 0;
        for (;;)
        {
          if (j >= paramList.size())
          {
            if (i == 0) {
              break;
            }
            new Thread(paramContext).start();
            return;
          }
          String str = (String)paramList.get(j);
          if (!bl.e(str))
          {
            i = 1;
            paramContext.a(str);
          }
          j += 1;
        }
        return;
      }
      catch (Throwable paramContext) {}
    }
  }
  
  private void a(String paramString)
  {
    if (paramString == null) {
      return;
    }
    for (;;)
    {
      try
      {
        if (this.f == null) {
          this.f = new StringBuilder();
        }
        if (this.f.length() > 0) {
          this.f.append('|');
        }
        this.f.append(paramString);
      }
      catch (Throwable localThrowable)
      {
        continue;
      }
      try
      {
        if (c == null) {
          c = new ArrayList();
        }
        c.add(paramString);
        return;
      }
      catch (Throwable paramString) {}
    }
  }
  
  /* Error */
  private static boolean a(Context paramContext, int paramInt, String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 4
    //   3: ldc 2
    //   5: monitorenter
    //   6: aload_2
    //   7: ifnonnull +11 -> 18
    //   10: iload 4
    //   12: istore_3
    //   13: ldc 2
    //   15: monitorexit
    //   16: iload_3
    //   17: ireturn
    //   18: iload 4
    //   20: istore_3
    //   21: aload_2
    //   22: invokevirtual 105	java/lang/String:length	()I
    //   25: ifeq -12 -> 13
    //   28: invokestatic 110	net/youmi/android/appoffers/u:b	()Ljava/lang/String;
    //   31: astore 5
    //   33: aload_0
    //   34: iload_1
    //   35: aload_2
    //   36: invokestatic 115	net/youmi/android/appoffers/bm:a	(Landroid/content/Context;ILjava/lang/String;)Ljava/lang/String;
    //   39: astore_2
    //   40: new 98	java/util/ArrayList
    //   43: dup
    //   44: invokespecial 99	java/util/ArrayList:<init>	()V
    //   47: astore 6
    //   49: aload 6
    //   51: new 117	org/apache/http/message/BasicNameValuePair
    //   54: dup
    //   55: ldc 119
    //   57: aload_2
    //   58: invokespecial 122	org/apache/http/message/BasicNameValuePair:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   61: invokevirtual 123	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   64: pop
    //   65: new 125	net/youmi/android/appoffers/q
    //   68: dup
    //   69: invokespecial 126	net/youmi/android/appoffers/q:<init>	()V
    //   72: aload_0
    //   73: aload 5
    //   75: aload 6
    //   77: invokevirtual 129	net/youmi/android/appoffers/q:a	(Landroid/content/Context;Ljava/lang/String;Ljava/util/List;)Lorg/json/JSONObject;
    //   80: astore_0
    //   81: iload 4
    //   83: istore_3
    //   84: aload_0
    //   85: ifnull -72 -> 13
    //   88: iconst_1
    //   89: istore_3
    //   90: goto -77 -> 13
    //   93: astore_0
    //   94: ldc 2
    //   96: monitorexit
    //   97: aload_0
    //   98: athrow
    //   99: astore_0
    //   100: iload 4
    //   102: istore_3
    //   103: goto -90 -> 13
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	106	0	paramContext	Context
    //   0	106	1	paramInt	int
    //   0	106	2	paramString	String
    //   12	91	3	bool1	boolean
    //   1	100	4	bool2	boolean
    //   31	43	5	str	String
    //   47	29	6	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   21	81	93	finally
    //   21	81	99	java/lang/Throwable
  }
  
  static void b(Context paramContext, List paramList)
  {
    if (paramList == null) {}
    while (paramContext == null) {
      return;
    }
    for (;;)
    {
      int j;
      try
      {
        if (paramList.size() <= 0) {
          break;
        }
        paramContext = new cj(paramContext, 1);
        j = 0;
        int i = 0;
        if (j >= paramList.size())
        {
          if (i == 0) {
            break;
          }
          new Thread(paramContext).start();
          return;
        }
        String str = (String)paramList.get(j);
        if (!bl.c(str))
        {
          paramContext.a(str);
          i = 1;
        }
      }
      catch (Throwable paramContext)
      {
        return;
      }
      j += 1;
    }
  }
  
  private static boolean b(Context paramContext)
  {
    boolean bool = false;
    try
    {
      paramContext = paramContext.getSharedPreferences(a, 0);
      long l1 = System.currentTimeMillis();
      long l2 = paramContext.getLong(b, 0L);
      if (l1 - l2 < 86400000L) {
        bool = true;
      }
      return bool;
    }
    catch (Throwable paramContext) {}
    return false;
  }
  
  private static boolean c(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getSharedPreferences(a, 0);
      long l = System.currentTimeMillis();
      paramContext = paramContext.edit();
      paramContext.putLong(b, l);
      boolean bool = paramContext.commit();
      return bool;
    }
    catch (Throwable paramContext) {}
    return false;
  }
  
  /* Error */
  private static boolean d(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: aload_0
    //   4: invokestatic 166	net/youmi/android/appoffers/cj:b	(Landroid/content/Context;)Z
    //   7: istore 6
    //   9: iload 6
    //   11: ifeq +12 -> 23
    //   14: iconst_1
    //   15: istore 7
    //   17: ldc 2
    //   19: monitorexit
    //   20: iload 7
    //   22: ireturn
    //   23: aload_0
    //   24: invokevirtual 170	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   27: astore 8
    //   29: aload 8
    //   31: ifnull +606 -> 637
    //   34: aload 8
    //   36: iconst_0
    //   37: invokevirtual 176	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   40: astore 8
    //   42: new 34	java/lang/StringBuilder
    //   45: dup
    //   46: sipush 2048
    //   49: invokespecial 37	java/lang/StringBuilder:<init>	(I)V
    //   52: astore 9
    //   54: new 98	java/util/ArrayList
    //   57: dup
    //   58: bipush 55
    //   60: invokespecial 177	java/util/ArrayList:<init>	(I)V
    //   63: astore 10
    //   65: iconst_0
    //   66: istore 6
    //   68: iconst_0
    //   69: istore 7
    //   71: iconst_0
    //   72: istore_1
    //   73: aload 8
    //   75: ifnull -58 -> 17
    //   78: iconst_0
    //   79: istore_3
    //   80: iload_3
    //   81: aload 8
    //   83: invokeinterface 69 1 0
    //   88: if_icmplt +20 -> 108
    //   91: iload 6
    //   93: ifeq +465 -> 558
    //   96: aload_0
    //   97: invokestatic 179	net/youmi/android/appoffers/cj:c	(Landroid/content/Context;)Z
    //   100: pop
    //   101: iload 6
    //   103: istore 7
    //   105: goto -88 -> 17
    //   108: aload 8
    //   110: iload_3
    //   111: invokeinterface 73 2 0
    //   116: checkcast 181	android/content/pm/PackageInfo
    //   119: astore 11
    //   121: aload 11
    //   123: ifnull +510 -> 633
    //   126: iload_1
    //   127: istore_2
    //   128: aload 11
    //   130: getfield 184	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   133: astore 11
    //   135: iload_1
    //   136: istore_2
    //   137: aload 11
    //   139: invokestatic 189	net/youmi/android/appoffers/m:a	(Ljava/lang/String;)Ljava/lang/String;
    //   142: astore 12
    //   144: iconst_0
    //   145: istore_2
    //   146: iconst_0
    //   147: istore 5
    //   149: aload_0
    //   150: invokestatic 194	net/youmi/android/appoffers/bx:b	(Landroid/content/Context;)Lnet/youmi/android/appoffers/bx;
    //   153: astore 13
    //   155: iload 5
    //   157: istore 4
    //   159: aload 13
    //   161: ifnull +24 -> 185
    //   164: aload 13
    //   166: aload 12
    //   168: invokevirtual 196	net/youmi/android/appoffers/bx:a	(Ljava/lang/String;)Z
    //   171: istore 7
    //   173: iload 5
    //   175: istore 4
    //   177: iload 7
    //   179: ifeq +6 -> 185
    //   182: iconst_1
    //   183: istore 4
    //   185: aload_0
    //   186: invokestatic 198	net/youmi/android/appoffers/bx:a	(Landroid/content/Context;)Lnet/youmi/android/appoffers/bx;
    //   189: astore 13
    //   191: iload_2
    //   192: istore 5
    //   194: aload 13
    //   196: ifnull +20 -> 216
    //   199: aload 13
    //   201: aload 12
    //   203: invokevirtual 196	net/youmi/android/appoffers/bx:a	(Ljava/lang/String;)Z
    //   206: istore 7
    //   208: iload 7
    //   210: ifeq +177 -> 387
    //   213: iconst_1
    //   214: istore 5
    //   216: iload 5
    //   218: ifeq +33 -> 251
    //   221: iload 4
    //   223: ifne +28 -> 251
    //   226: aload_0
    //   227: invokestatic 194	net/youmi/android/appoffers/bx:b	(Landroid/content/Context;)Lnet/youmi/android/appoffers/bx;
    //   230: astore 13
    //   232: aload 13
    //   234: ifnull +17 -> 251
    //   237: aload 13
    //   239: aload 12
    //   241: invokevirtual 200	net/youmi/android/appoffers/bx:b	(Ljava/lang/String;)Z
    //   244: istore 7
    //   246: iload 7
    //   248: ifeq +3 -> 251
    //   251: iload_1
    //   252: istore_2
    //   253: iload 4
    //   255: ifne +10 -> 265
    //   258: iload 5
    //   260: ifeq +157 -> 417
    //   263: iload_1
    //   264: istore_2
    //   265: aload 10
    //   267: invokeinterface 69 1 0
    //   272: bipush 50
    //   274: if_icmpge +16 -> 290
    //   277: iload_3
    //   278: aload 8
    //   280: invokeinterface 69 1 0
    //   285: iconst_1
    //   286: isub
    //   287: if_icmpne +343 -> 630
    //   290: aload 9
    //   292: invokevirtual 87	java/lang/StringBuilder:length	()I
    //   295: istore_1
    //   296: iload_1
    //   297: ifle +333 -> 630
    //   300: iload 6
    //   302: istore 7
    //   304: aload_0
    //   305: iconst_0
    //   306: aload 9
    //   308: invokevirtual 203	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   311: invokestatic 205	net/youmi/android/appoffers/cj:a	(Landroid/content/Context;ILjava/lang/String;)Z
    //   314: ifeq +35 -> 349
    //   317: aload_0
    //   318: invokestatic 198	net/youmi/android/appoffers/bx:a	(Landroid/content/Context;)Lnet/youmi/android/appoffers/bx;
    //   321: astore 11
    //   323: aload_0
    //   324: invokestatic 194	net/youmi/android/appoffers/bx:b	(Landroid/content/Context;)Lnet/youmi/android/appoffers/bx;
    //   327: astore 12
    //   329: iconst_0
    //   330: istore_1
    //   331: aload 10
    //   333: invokeinterface 69 1 0
    //   338: istore 4
    //   340: iload_1
    //   341: iload 4
    //   343: if_icmplt +130 -> 473
    //   346: iconst_1
    //   347: istore 7
    //   349: aload 9
    //   351: iconst_0
    //   352: aload 9
    //   354: invokevirtual 87	java/lang/StringBuilder:length	()I
    //   357: invokevirtual 209	java/lang/StringBuilder:delete	(II)Ljava/lang/StringBuilder;
    //   360: pop
    //   361: aload 10
    //   363: invokeinterface 212 1 0
    //   368: iload 7
    //   370: istore 6
    //   372: ldc2_w 213
    //   375: invokestatic 218	java/lang/Thread:sleep	(J)V
    //   378: iload_2
    //   379: istore_1
    //   380: iload_3
    //   381: iconst_1
    //   382: iadd
    //   383: istore_3
    //   384: goto -304 -> 80
    //   387: iload_2
    //   388: istore 5
    //   390: iload 4
    //   392: ifeq -176 -> 216
    //   395: aload 13
    //   397: aload 12
    //   399: invokevirtual 200	net/youmi/android/appoffers/bx:b	(Ljava/lang/String;)Z
    //   402: pop
    //   403: iload_2
    //   404: istore 5
    //   406: goto -190 -> 216
    //   409: astore 13
    //   411: iload_2
    //   412: istore 5
    //   414: goto -198 -> 216
    //   417: iload_1
    //   418: iconst_1
    //   419: iadd
    //   420: istore_1
    //   421: iload_1
    //   422: istore_2
    //   423: aload 9
    //   425: invokevirtual 87	java/lang/StringBuilder:length	()I
    //   428: ifle +13 -> 441
    //   431: iload_1
    //   432: istore_2
    //   433: aload 9
    //   435: bipush 124
    //   437: invokevirtual 91	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   440: pop
    //   441: iload_1
    //   442: istore_2
    //   443: aload 9
    //   445: aload 11
    //   447: invokevirtual 94	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   450: pop
    //   451: iload_1
    //   452: istore_2
    //   453: aload 10
    //   455: aload 12
    //   457: invokeinterface 103 2 0
    //   462: pop
    //   463: iload_1
    //   464: istore_2
    //   465: goto -200 -> 265
    //   468: astore 11
    //   470: goto -205 -> 265
    //   473: aload 10
    //   475: iload_1
    //   476: invokeinterface 73 2 0
    //   481: checkcast 75	java/lang/String
    //   484: astore 13
    //   486: aload 13
    //   488: ifnull +41 -> 529
    //   491: aload 11
    //   493: ifnull +17 -> 510
    //   496: aload 11
    //   498: aload 13
    //   500: invokevirtual 200	net/youmi/android/appoffers/bx:b	(Ljava/lang/String;)Z
    //   503: istore 7
    //   505: iload 7
    //   507: ifeq +3 -> 510
    //   510: aload 12
    //   512: ifnull +17 -> 529
    //   515: aload 12
    //   517: aload 13
    //   519: invokevirtual 200	net/youmi/android/appoffers/bx:b	(Ljava/lang/String;)Z
    //   522: istore 7
    //   524: iload 7
    //   526: ifeq +3 -> 529
    //   529: ldc2_w 219
    //   532: invokestatic 218	java/lang/Thread:sleep	(J)V
    //   535: iload_1
    //   536: iconst_1
    //   537: iadd
    //   538: istore_1
    //   539: goto -208 -> 331
    //   542: astore 11
    //   544: iload 7
    //   546: istore 6
    //   548: goto -176 -> 372
    //   551: astore 11
    //   553: iload_2
    //   554: istore_1
    //   555: goto -175 -> 380
    //   558: iload 6
    //   560: istore 7
    //   562: iload_1
    //   563: ifgt -546 -> 17
    //   566: aload_0
    //   567: invokestatic 179	net/youmi/android/appoffers/cj:c	(Landroid/content/Context;)Z
    //   570: pop
    //   571: iload 6
    //   573: istore 7
    //   575: goto -558 -> 17
    //   578: astore_0
    //   579: aload_0
    //   580: athrow
    //   581: astore_0
    //   582: ldc 2
    //   584: monitorexit
    //   585: aload_0
    //   586: athrow
    //   587: astore 14
    //   589: goto -79 -> 510
    //   592: astore 13
    //   594: goto -65 -> 529
    //   597: astore 13
    //   599: goto -64 -> 535
    //   602: astore 11
    //   604: goto -243 -> 361
    //   607: astore 11
    //   609: iload 6
    //   611: istore 7
    //   613: goto -264 -> 349
    //   616: astore 13
    //   618: goto -367 -> 251
    //   621: astore 13
    //   623: iload 5
    //   625: istore 4
    //   627: goto -442 -> 185
    //   630: goto -258 -> 372
    //   633: goto -253 -> 380
    //   636: astore_0
    //   637: iconst_0
    //   638: istore 7
    //   640: goto -623 -> 17
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	643	0	paramContext	Context
    //   72	491	1	i	int
    //   127	427	2	j	int
    //   79	305	3	k	int
    //   157	469	4	m	int
    //   147	477	5	n	int
    //   7	603	6	bool1	boolean
    //   15	624	7	bool2	boolean
    //   27	252	8	localObject1	Object
    //   52	392	9	localStringBuilder	StringBuilder
    //   63	411	10	localArrayList	ArrayList
    //   119	327	11	localObject2	Object
    //   468	29	11	localThrowable1	Throwable
    //   542	1	11	localThrowable2	Throwable
    //   551	1	11	localThrowable3	Throwable
    //   602	1	11	localThrowable4	Throwable
    //   607	1	11	localThrowable5	Throwable
    //   142	374	12	localObject3	Object
    //   153	243	13	localBx	bx
    //   409	1	13	localThrowable6	Throwable
    //   484	34	13	str	String
    //   592	1	13	localThrowable7	Throwable
    //   597	1	13	localThrowable8	Throwable
    //   616	1	13	localThrowable9	Throwable
    //   621	1	13	localThrowable10	Throwable
    //   587	1	14	localThrowable11	Throwable
    // Exception table:
    //   from	to	target	type
    //   185	191	409	java/lang/Throwable
    //   199	208	409	java/lang/Throwable
    //   395	403	409	java/lang/Throwable
    //   128	135	468	java/lang/Throwable
    //   137	144	468	java/lang/Throwable
    //   423	431	468	java/lang/Throwable
    //   433	441	468	java/lang/Throwable
    //   443	451	468	java/lang/Throwable
    //   453	463	468	java/lang/Throwable
    //   361	368	542	java/lang/Throwable
    //   372	378	551	java/lang/Throwable
    //   3	9	578	finally
    //   23	29	578	finally
    //   34	65	578	finally
    //   80	91	578	finally
    //   96	101	578	finally
    //   108	121	578	finally
    //   128	135	578	finally
    //   137	144	578	finally
    //   149	155	578	finally
    //   164	173	578	finally
    //   185	191	578	finally
    //   199	208	578	finally
    //   226	232	578	finally
    //   237	246	578	finally
    //   265	290	578	finally
    //   290	296	578	finally
    //   304	329	578	finally
    //   331	340	578	finally
    //   349	361	578	finally
    //   361	368	578	finally
    //   372	378	578	finally
    //   395	403	578	finally
    //   423	431	578	finally
    //   433	441	578	finally
    //   443	451	578	finally
    //   453	463	578	finally
    //   473	486	578	finally
    //   496	505	578	finally
    //   515	524	578	finally
    //   529	535	578	finally
    //   566	571	578	finally
    //   579	581	581	finally
    //   496	505	587	java/lang/Throwable
    //   515	524	592	java/lang/Throwable
    //   529	535	597	java/lang/Throwable
    //   349	361	602	java/lang/Throwable
    //   304	329	607	java/lang/Throwable
    //   331	340	607	java/lang/Throwable
    //   473	486	607	java/lang/Throwable
    //   226	232	616	java/lang/Throwable
    //   237	246	616	java/lang/Throwable
    //   149	155	621	java/lang/Throwable
    //   164	173	621	java/lang/Throwable
    //   3	9	636	java/lang/Throwable
    //   23	29	636	java/lang/Throwable
    //   34	65	636	java/lang/Throwable
    //   80	91	636	java/lang/Throwable
    //   96	101	636	java/lang/Throwable
    //   108	121	636	java/lang/Throwable
    //   265	290	636	java/lang/Throwable
    //   290	296	636	java/lang/Throwable
    //   566	571	636	java/lang/Throwable
  }
  
  public void run()
  {
    for (;;)
    {
      int i;
      try
      {
        if (this.e == 0)
        {
          d(this.d);
          return;
        }
        if ((this.f != null) && (this.f.length() > 0) && (a(this.d, this.e, this.f.toString())) && (c != null))
        {
          i = 0;
          if (i < c.size())
          {
            String str = (String)c.get(i);
            if (this.e == 2)
            {
              bl.f(str);
              bl.j(str);
            }
            else
            {
              bl.d(str);
              bl.h(str);
            }
          }
        }
      }
      catch (Throwable localThrowable) {}
      return;
      i += 1;
    }
  }
}
