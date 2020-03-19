package com.cleanmaster.security.scan;

import android.content.Context;
import android.content.pm.PackageInfo;
import com.cleanmaster.security.scan.engine.a;
import java.util.List;

public final class c
  extends Thread
{
  private static boolean e = false;
  public volatile boolean a;
  private Context b;
  private a c;
  private List<PackageInfo> d = null;
  private boolean f = false;
  
  public c(Context paramContext, a paramA, List<PackageInfo> paramList, boolean paramBoolean)
  {
    super("ApkLeakScanThread");
    if ((paramContext == null) || (paramA == null)) {
      throw new IllegalArgumentException("The context or callback of the ApkLeakScanThread can't be null.");
    }
    this.b = paramContext;
    this.c = paramA;
    this.f = paramBoolean;
    this.d = paramList;
  }
  
  /* Error */
  public final void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 29	com/cleanmaster/security/scan/c:d	Ljava/util/List;
    //   4: astore 5
    //   6: aload_0
    //   7: aconst_null
    //   8: putfield 29	com/cleanmaster/security/scan/c:d	Ljava/util/List;
    //   11: aload 5
    //   13: ifnull +17 -> 30
    //   16: aload 5
    //   18: astore 4
    //   20: aload 5
    //   22: invokeinterface 51 1 0
    //   27: ifeq +16 -> 43
    //   30: aload_0
    //   31: getfield 38	com/cleanmaster/security/scan/c:b	Landroid/content/Context;
    //   34: invokevirtual 57	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   37: iconst_0
    //   38: invokevirtual 63	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   41: astore 4
    //   43: aload 4
    //   45: ifnull +13 -> 58
    //   48: aload 4
    //   50: invokeinterface 51 1 0
    //   55: ifeq +20 -> 75
    //   58: aload_0
    //   59: getfield 40	com/cleanmaster/security/scan/c:c	Lcom/cleanmaster/security/scan/engine/a;
    //   62: ifnull +12 -> 74
    //   65: aload_0
    //   66: getfield 40	com/cleanmaster/security/scan/c:c	Lcom/cleanmaster/security/scan/engine/a;
    //   69: invokeinterface 67 1 0
    //   74: return
    //   75: aload_0
    //   76: getfield 40	com/cleanmaster/security/scan/c:c	Lcom/cleanmaster/security/scan/engine/a;
    //   79: ifnull +12 -> 91
    //   82: aload_0
    //   83: getfield 40	com/cleanmaster/security/scan/c:c	Lcom/cleanmaster/security/scan/engine/a;
    //   86: invokeinterface 69 1 0
    //   91: new 71	java/util/ArrayList
    //   94: dup
    //   95: invokespecial 73	java/util/ArrayList:<init>	()V
    //   98: astore 8
    //   100: getstatic 19	com/cleanmaster/security/scan/c:e	Z
    //   103: ifeq +10 -> 113
    //   106: aload_0
    //   107: getfield 31	com/cleanmaster/security/scan/c:f	Z
    //   110: ifeq +494 -> 604
    //   113: iconst_1
    //   114: putstatic 19	com/cleanmaster/security/scan/c:e	Z
    //   117: invokestatic 78	com/cleanmaster/service/b:a	()Lcom/cleanmaster/service/b;
    //   120: pop
    //   121: invokestatic 81	com/cleanmaster/service/b:b	()Ljava/util/HashMap;
    //   124: astore 9
    //   126: aload 4
    //   128: invokeinterface 85 1 0
    //   133: astore 10
    //   135: aconst_null
    //   136: astore 4
    //   138: iconst_0
    //   139: istore_1
    //   140: aload 10
    //   142: invokeinterface 90 1 0
    //   147: ifeq +419 -> 566
    //   150: aload 10
    //   152: invokeinterface 94 1 0
    //   157: checkcast 96	android/content/pm/PackageInfo
    //   160: astore 11
    //   162: aload_0
    //   163: getfield 98	com/cleanmaster/security/scan/c:a	Z
    //   166: istore_3
    //   167: iload_3
    //   168: ifne +398 -> 566
    //   171: aload 11
    //   173: getfield 102	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   176: getfield 108	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   179: astore 7
    //   181: aload 7
    //   183: invokestatic 113	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   186: ifne -46 -> 140
    //   189: aload 7
    //   191: invokestatic 118	android/support/percent/a:a	(Ljava/lang/String;)Ljava/lang/String;
    //   194: astore 5
    //   196: aload 5
    //   198: invokestatic 113	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   201: ifne -61 -> 140
    //   204: aload 9
    //   206: aload 5
    //   208: invokevirtual 124	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   211: ifeq -71 -> 140
    //   214: aload 9
    //   216: aload 5
    //   218: invokevirtual 128	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   221: checkcast 130	com/cleanmaster/ui/app/data/HighRiskInfo
    //   224: astore 12
    //   226: aload 12
    //   228: ifnull -88 -> 140
    //   231: aload 12
    //   233: aload 5
    //   235: aload 11
    //   237: getfield 134	android/content/pm/PackageInfo:versionCode	I
    //   240: aload_0
    //   241: getfield 38	com/cleanmaster/security/scan/c:b	Landroid/content/Context;
    //   244: aload 7
    //   246: invokestatic 140	LibcoreWrapper/a:w	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   249: invokevirtual 143	com/cleanmaster/ui/app/data/HighRiskInfo:a	(Ljava/lang/String;ILjava/lang/String;)Z
    //   252: istore_3
    //   253: aload 4
    //   255: astore 6
    //   257: iload_3
    //   258: ifeq +429 -> 687
    //   261: aload 4
    //   263: ifnonnull +421 -> 684
    //   266: new 145	com/hoi/antivirus/AntiVirusFunc
    //   269: dup
    //   270: invokespecial 146	com/hoi/antivirus/AntiVirusFunc:<init>	()V
    //   273: astore 5
    //   275: aload 5
    //   277: astore 4
    //   279: aload 4
    //   281: astore 5
    //   283: iconst_0
    //   284: putstatic 19	com/cleanmaster/security/scan/c:e	Z
    //   287: invokestatic 151	com/cleanmaster/func/cache/c:b	()Lcom/cleanmaster/func/cache/c;
    //   290: aload 7
    //   292: aconst_null
    //   293: invokevirtual 154	com/cleanmaster/func/cache/c:b	(Ljava/lang/String;Landroid/content/pm/PackageInfo;)Ljava/lang/String;
    //   296: astore 6
    //   298: aload 6
    //   300: astore 4
    //   302: aload 6
    //   304: invokestatic 113	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   307: ifeq +7 -> 314
    //   310: aload 7
    //   312: astore 4
    //   314: aload 12
    //   316: aload 4
    //   318: putfield 156	com/cleanmaster/ui/app/data/HighRiskInfo:a	Ljava/lang/String;
    //   321: aload 12
    //   323: aload 7
    //   325: invokevirtual 158	com/cleanmaster/ui/app/data/HighRiskInfo:b	(Ljava/lang/String;)V
    //   328: ldc -96
    //   330: astore 4
    //   332: aload 12
    //   334: getfield 163	com/cleanmaster/ui/app/data/HighRiskInfo:k	Ljava/lang/String;
    //   337: astore 6
    //   339: aload 6
    //   341: invokestatic 113	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   344: ifne +11 -> 355
    //   347: aload 6
    //   349: invokestatic 166	android/text/TextUtils:isDigitsOnly	(Ljava/lang/CharSequence;)Z
    //   352: ifne +139 -> 491
    //   355: aload_0
    //   356: getfield 38	com/cleanmaster/security/scan/c:b	Landroid/content/Context;
    //   359: ldc -89
    //   361: invokevirtual 171	android/content/Context:getString	(I)Ljava/lang/String;
    //   364: astore 4
    //   366: aload 12
    //   368: aload 4
    //   370: putfield 173	com/cleanmaster/ui/app/data/HighRiskInfo:e	Ljava/lang/String;
    //   373: new 175	com/cleanmaster/security/scan/AppExploitInfo
    //   376: dup
    //   377: invokespecial 176	com/cleanmaster/security/scan/AppExploitInfo:<init>	()V
    //   380: astore 7
    //   382: aload 7
    //   384: aload 12
    //   386: putfield 179	com/cleanmaster/security/scan/AppExploitInfo:b	Lcom/cleanmaster/ui/app/data/HighRiskInfo;
    //   389: aload 5
    //   391: ifnull +25 -> 416
    //   394: aload 5
    //   396: aload 11
    //   398: getfield 102	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   401: getfield 182	android/content/pm/ApplicationInfo:publicSourceDir	Ljava/lang/String;
    //   404: invokevirtual 183	com/hoi/antivirus/AntiVirusFunc:a	(Ljava/lang/String;)Ljava/lang/String;
    //   407: astore 4
    //   409: aload 7
    //   411: aload 4
    //   413: invokevirtual 185	com/cleanmaster/security/scan/AppExploitInfo:a	(Ljava/lang/String;)V
    //   416: aload 5
    //   418: astore 6
    //   420: aload_0
    //   421: getfield 40	com/cleanmaster/security/scan/c:c	Lcom/cleanmaster/security/scan/engine/a;
    //   424: ifnull +263 -> 687
    //   427: aload_0
    //   428: getfield 40	com/cleanmaster/security/scan/c:c	Lcom/cleanmaster/security/scan/engine/a;
    //   431: aload 7
    //   433: invokeinterface 188 2 0
    //   438: iload_1
    //   439: iconst_1
    //   440: iadd
    //   441: istore_2
    //   442: aload 8
    //   444: aload 7
    //   446: invokeinterface 191 2 0
    //   451: pop
    //   452: iload_2
    //   453: istore_1
    //   454: aload 5
    //   456: astore 4
    //   458: iload_2
    //   459: bipush 19
    //   461: if_icmple +230 -> 691
    //   464: aload_0
    //   465: getfield 40	com/cleanmaster/security/scan/c:c	Lcom/cleanmaster/security/scan/engine/a;
    //   468: aload 8
    //   470: invokeinterface 194 2 0
    //   475: aload 8
    //   477: invokeinterface 197 1 0
    //   482: iconst_0
    //   483: istore_1
    //   484: aload 5
    //   486: astore 4
    //   488: goto +203 -> 691
    //   491: aload 6
    //   493: invokestatic 203	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   496: invokevirtual 207	java/lang/Integer:intValue	()I
    //   499: tableswitch	default:+195->694, 1:+25->524, 2:+39->538, 3:+53->552
    //   524: aload_0
    //   525: getfield 38	com/cleanmaster/security/scan/c:b	Landroid/content/Context;
    //   528: ldc -48
    //   530: invokevirtual 171	android/content/Context:getString	(I)Ljava/lang/String;
    //   533: astore 4
    //   535: goto -169 -> 366
    //   538: aload_0
    //   539: getfield 38	com/cleanmaster/security/scan/c:b	Landroid/content/Context;
    //   542: ldc -47
    //   544: invokevirtual 171	android/content/Context:getString	(I)Ljava/lang/String;
    //   547: astore 4
    //   549: goto -183 -> 366
    //   552: aload_0
    //   553: getfield 38	com/cleanmaster/security/scan/c:b	Landroid/content/Context;
    //   556: ldc -89
    //   558: invokevirtual 171	android/content/Context:getString	(I)Ljava/lang/String;
    //   561: astore 4
    //   563: goto -197 -> 366
    //   566: aload 8
    //   568: invokeinterface 212 1 0
    //   573: ifle +14 -> 587
    //   576: aload_0
    //   577: getfield 40	com/cleanmaster/security/scan/c:c	Lcom/cleanmaster/security/scan/engine/a;
    //   580: aload 8
    //   582: invokeinterface 194 2 0
    //   587: getstatic 19	com/cleanmaster/security/scan/c:e	Z
    //   590: ifeq +14 -> 604
    //   593: aload_0
    //   594: getfield 38	com/cleanmaster/security/scan/c:b	Landroid/content/Context;
    //   597: invokestatic 217	com/cleanmaster/configmanager/d:a	(Landroid/content/Context;)Lcom/cleanmaster/configmanager/d;
    //   600: iconst_0
    //   601: invokevirtual 221	com/cleanmaster/configmanager/d:u	(Z)V
    //   604: aload_0
    //   605: getfield 40	com/cleanmaster/security/scan/c:c	Lcom/cleanmaster/security/scan/engine/a;
    //   608: ifnull -534 -> 74
    //   611: aload_0
    //   612: getfield 40	com/cleanmaster/security/scan/c:c	Lcom/cleanmaster/security/scan/engine/a;
    //   615: invokeinterface 67 1 0
    //   620: return
    //   621: astore 4
    //   623: aload_0
    //   624: getfield 40	com/cleanmaster/security/scan/c:c	Lcom/cleanmaster/security/scan/engine/a;
    //   627: ifnull -553 -> 74
    //   630: aload_0
    //   631: getfield 40	com/cleanmaster/security/scan/c:c	Lcom/cleanmaster/security/scan/engine/a;
    //   634: invokeinterface 67 1 0
    //   639: return
    //   640: astore 4
    //   642: aload_0
    //   643: getfield 40	com/cleanmaster/security/scan/c:c	Lcom/cleanmaster/security/scan/engine/a;
    //   646: ifnull +12 -> 658
    //   649: aload_0
    //   650: getfield 40	com/cleanmaster/security/scan/c:c	Lcom/cleanmaster/security/scan/engine/a;
    //   653: invokeinterface 67 1 0
    //   658: aload 4
    //   660: athrow
    //   661: astore 5
    //   663: aload 4
    //   665: astore 5
    //   667: goto -384 -> 283
    //   670: astore 5
    //   672: goto -532 -> 140
    //   675: astore 4
    //   677: aload 5
    //   679: astore 4
    //   681: goto -638 -> 43
    //   684: goto -405 -> 279
    //   687: aload 6
    //   689: astore 4
    //   691: goto -551 -> 140
    //   694: goto -328 -> 366
    //   697: astore 4
    //   699: aconst_null
    //   700: astore 4
    //   702: goto -293 -> 409
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	705	0	this	c
    //   139	345	1	i	int
    //   441	21	2	j	int
    //   166	92	3	bool	boolean
    //   18	544	4	localObject1	Object
    //   621	1	4	localException1	Exception
    //   640	24	4	localObject2	Object
    //   675	1	4	localException2	Exception
    //   679	11	4	localObject3	Object
    //   697	1	4	localException3	Exception
    //   700	1	4	localObject4	Object
    //   4	481	5	localObject5	Object
    //   661	1	5	localException4	Exception
    //   665	1	5	localObject6	Object
    //   670	8	5	localException5	Exception
    //   255	433	6	localObject7	Object
    //   179	266	7	localObject8	Object
    //   98	483	8	localArrayList	java.util.ArrayList
    //   124	91	9	localHashMap	java.util.HashMap
    //   133	18	10	localIterator	java.util.Iterator
    //   160	237	11	localPackageInfo	PackageInfo
    //   224	161	12	localHighRiskInfo	com.cleanmaster.ui.app.data.HighRiskInfo
    // Exception table:
    //   from	to	target	type
    //   100	113	621	java/lang/Exception
    //   113	135	621	java/lang/Exception
    //   140	167	621	java/lang/Exception
    //   181	226	621	java/lang/Exception
    //   231	253	621	java/lang/Exception
    //   283	298	621	java/lang/Exception
    //   302	310	621	java/lang/Exception
    //   314	328	621	java/lang/Exception
    //   332	355	621	java/lang/Exception
    //   355	366	621	java/lang/Exception
    //   366	389	621	java/lang/Exception
    //   409	416	621	java/lang/Exception
    //   420	438	621	java/lang/Exception
    //   442	452	621	java/lang/Exception
    //   464	482	621	java/lang/Exception
    //   491	524	621	java/lang/Exception
    //   524	535	621	java/lang/Exception
    //   538	549	621	java/lang/Exception
    //   552	563	621	java/lang/Exception
    //   566	587	621	java/lang/Exception
    //   587	604	621	java/lang/Exception
    //   100	113	640	finally
    //   113	135	640	finally
    //   140	167	640	finally
    //   171	181	640	finally
    //   181	226	640	finally
    //   231	253	640	finally
    //   266	275	640	finally
    //   283	298	640	finally
    //   302	310	640	finally
    //   314	328	640	finally
    //   332	355	640	finally
    //   355	366	640	finally
    //   366	389	640	finally
    //   394	409	640	finally
    //   409	416	640	finally
    //   420	438	640	finally
    //   442	452	640	finally
    //   464	482	640	finally
    //   491	524	640	finally
    //   524	535	640	finally
    //   538	549	640	finally
    //   552	563	640	finally
    //   566	587	640	finally
    //   587	604	640	finally
    //   266	275	661	java/lang/Exception
    //   171	181	670	java/lang/Exception
    //   30	43	675	java/lang/Exception
    //   394	409	697	java/lang/Exception
  }
}
