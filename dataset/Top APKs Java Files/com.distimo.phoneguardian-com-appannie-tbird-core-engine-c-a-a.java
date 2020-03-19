package com.appannie.tbird.core.engine.c.a;

import android.content.pm.PackageManager;
import android.util.SparseArray;
import com.appannie.tbird.core.engine.b.f.f;
import com.appannie.tbird.core.engine.c.f.c;
import com.appannie.tbird.core.engine.g;
import com.appannie.tbird.core.engine.persistentStore.d;
import com.appannie.tbird.core.engine.persistentStore.entities.AppVersion;
import com.appannie.tbird.core.engine.persistentStore.entities.j;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class a
  extends com.appannie.tbird.core.engine.a
{
  private static a b;
  private PackageManager c;
  private c d;
  private com.appannie.tbird.core.engine.c.f.b e = new com.appannie.tbird.core.engine.c.f.b()
  {
    public final void a()
    {
      a.a(a.this);
    }
    
    public final void b()
    {
      a.b(a.this);
    }
    
    public final void c()
    {
      a.c(a.this);
    }
  };
  
  private a() {}
  
  private static SparseArray<com.appannie.tbird.core.engine.persistentStore.entities.b> a(d paramD, long paramLong)
  {
    SparseArray localSparseArray = new SparseArray();
    paramD = paramD.b(paramLong).iterator();
    while (paramD.hasNext())
    {
      com.appannie.tbird.core.engine.persistentStore.entities.b localB = (com.appannie.tbird.core.engine.persistentStore.entities.b)paramD.next();
      localSparseArray.put(localB.c.b.a, localB);
    }
    return localSparseArray;
  }
  
  private static com.appannie.tbird.core.engine.persistentStore.entities.b a(AppVersion paramAppVersion, SparseArray<com.appannie.tbird.core.engine.persistentStore.entities.b> paramSparseArray)
  {
    return (com.appannie.tbird.core.engine.persistentStore.entities.b)paramSparseArray.get(paramAppVersion.b.a);
  }
  
  private j a(d paramD)
  {
    Iterator localIterator = paramD.b(((com.appannie.tbird.core.engine.c.g.b)g().b(1)).b_()).iterator();
    paramD = null;
    while ((localIterator.hasNext()) && (paramD == null))
    {
      j localJ = (j)localIterator.next();
      if (localJ.c.equals("Mobile")) {
        paramD = localJ;
      }
    }
    return paramD;
  }
  
  private static void a(d paramD, j paramJ, AppVersion paramAppVersion, int paramInt, long paramLong)
  {
    com.appannie.tbird.core.engine.persistentStore.entities.b localB = new com.appannie.tbird.core.engine.persistentStore.entities.b();
    localB.b = paramJ;
    localB.c = paramAppVersion;
    localB.e = paramInt;
    localB.d = new Date(paramLong);
    paramD.a(localB);
  }
  
  private void a(boolean paramBoolean)
  {
    k().b(this.e);
    if (paramBoolean) {
      k().a(this.e);
    }
  }
  
  public static a j()
  {
    try
    {
      if (b == null)
      {
        f.a("InstallationCollector", "<--> getInstance(++ CREATED ++)");
        b = new a();
      }
      a localA = b;
      return localA;
    }
    finally {}
  }
  
  private c k()
  {
    if (this.d == null) {
      this.d = ((c)g().b(2));
    }
    return this.d;
  }
  
  /* Error */
  public final void a()
  {
    // Byte code:
    //   0: ldc -107
    //   2: ldc -95
    //   4: invokestatic 156	com/appannie/tbird/core/engine/b/f/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   7: aload_0
    //   8: monitorenter
    //   9: aload_0
    //   10: invokevirtual 164	com/appannie/tbird/core/engine/c/a/a:f_	()Z
    //   13: ifeq +8 -> 21
    //   16: aload_0
    //   17: iconst_0
    //   18: invokespecial 166	com/appannie/tbird/core/engine/c/a/a:a	(Z)V
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_0
    //   24: invokespecial 168	com/appannie/tbird/core/engine/a:a	()V
    //   27: ldc 2
    //   29: monitorenter
    //   30: aconst_null
    //   31: putstatic 147	com/appannie/tbird/core/engine/c/a/a:b	Lcom/appannie/tbird/core/engine/c/a/a;
    //   34: ldc 2
    //   36: monitorexit
    //   37: ldc -107
    //   39: ldc -86
    //   41: invokestatic 156	com/appannie/tbird/core/engine/b/f/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   44: return
    //   45: astore_1
    //   46: ldc 2
    //   48: monitorexit
    //   49: aload_1
    //   50: athrow
    //   51: astore_1
    //   52: aload_0
    //   53: monitorexit
    //   54: aload_1
    //   55: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	56	0	this	a
    //   45	5	1	localObject1	Object
    //   51	4	1	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   30	37	45	finally
    //   46	49	45	finally
    //   9	21	51	finally
    //   21	23	51	finally
    //   52	54	51	finally
  }
  
  public final void a(g paramG)
  {
    try
    {
      f.a("InstallationCollector", "--> start()");
      super.a(paramG);
      a(paramG.n().v ^ true);
      f.a("InstallationCollector", "<-- start()");
      return;
    }
    finally
    {
      paramG = finally;
      throw paramG;
    }
  }
  
  /* Error */
  protected final boolean a(android.os.Message paramMessage)
  {
    // Byte code:
    //   0: ldc -107
    //   2: ldc -62
    //   4: iconst_1
    //   5: anewarray 196	java/lang/Object
    //   8: dup
    //   9: iconst_0
    //   10: aload_1
    //   11: getfield 201	android/os/Message:what	I
    //   14: invokestatic 207	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   17: aastore
    //   18: invokestatic 210	com/appannie/tbird/core/engine/b/f/f:a	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   21: invokestatic 156	com/appannie/tbird/core/engine/b/f/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   24: aload_0
    //   25: aload_1
    //   26: invokespecial 212	com/appannie/tbird/core/engine/a:a	(Landroid/os/Message;)Z
    //   29: istore 5
    //   31: iload 5
    //   33: ifne +1447 -> 1480
    //   36: aload_1
    //   37: getfield 201	android/os/Message:what	I
    //   40: istore_2
    //   41: iload_2
    //   42: sipush 1337
    //   45: if_icmpeq +37 -> 82
    //   48: iload_2
    //   49: sipush 2002
    //   52: if_icmpeq +30 -> 82
    //   55: ldc -107
    //   57: ldc -42
    //   59: iconst_1
    //   60: anewarray 196	java/lang/Object
    //   63: dup
    //   64: iconst_0
    //   65: aload_1
    //   66: getfield 201	android/os/Message:what	I
    //   69: invokestatic 207	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   72: aastore
    //   73: invokestatic 210	com/appannie/tbird/core/engine/b/f/f:a	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   76: invokestatic 217	com/appannie/tbird/core/engine/b/f/f:f	(Ljava/lang/String;Ljava/lang/String;)V
    //   79: iload 5
    //   81: ireturn
    //   82: invokestatic 223	java/lang/System:currentTimeMillis	()J
    //   85: lstore 6
    //   87: ldc -107
    //   89: ldc -31
    //   91: invokestatic 227	com/appannie/tbird/core/engine/b/f/f:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   94: aload_0
    //   95: invokevirtual 82	com/appannie/tbird/core/engine/c/a/a:g	()Lcom/appannie/tbird/core/engine/g;
    //   98: invokeinterface 230 1 0
    //   103: astore 24
    //   105: invokestatic 223	java/lang/System:currentTimeMillis	()J
    //   108: lstore 10
    //   110: new 232	java/util/HashMap
    //   113: dup
    //   114: invokespecial 233	java/util/HashMap:<init>	()V
    //   117: astore 26
    //   119: aload 24
    //   121: invokeinterface 236 1 0
    //   126: invokeinterface 40 1 0
    //   131: astore_1
    //   132: aload_1
    //   133: invokeinterface 46 1 0
    //   138: ifeq +35 -> 173
    //   141: aload_1
    //   142: invokeinterface 50 1 0
    //   147: checkcast 57	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion
    //   150: astore 16
    //   152: aload 26
    //   154: aload 16
    //   156: getfield 60	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion:b	Lcom/appannie/tbird/core/engine/persistentStore/entities/a;
    //   159: getfield 238	com/appannie/tbird/core/engine/persistentStore/entities/a:b	Ljava/lang/String;
    //   162: aload 16
    //   164: invokeinterface 243 3 0
    //   169: pop
    //   170: goto -38 -> 132
    //   173: aload 24
    //   175: lload 10
    //   177: invokestatic 245	com/appannie/tbird/core/engine/c/a/a:a	(Lcom/appannie/tbird/core/engine/persistentStore/d;J)Landroid/util/SparseArray;
    //   180: astore 16
    //   182: aload_0
    //   183: aload 24
    //   185: invokespecial 247	com/appannie/tbird/core/engine/c/a/a:a	(Lcom/appannie/tbird/core/engine/persistentStore/d;)Lcom/appannie/tbird/core/engine/persistentStore/entities/j;
    //   188: astore 25
    //   190: aload_0
    //   191: getfield 249	com/appannie/tbird/core/engine/c/a/a:c	Landroid/content/pm/PackageManager;
    //   194: ifnonnull +19 -> 213
    //   197: aload_0
    //   198: aload_0
    //   199: invokevirtual 82	com/appannie/tbird/core/engine/c/a/a:g	()Lcom/appannie/tbird/core/engine/g;
    //   202: invokeinterface 252 1 0
    //   207: invokevirtual 258	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   210: putfield 249	com/appannie/tbird/core/engine/c/a/a:c	Landroid/content/pm/PackageManager;
    //   213: aload_0
    //   214: getfield 249	com/appannie/tbird/core/engine/c/a/a:c	Landroid/content/pm/PackageManager;
    //   217: sipush 128
    //   220: invokevirtual 264	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   223: astore 17
    //   225: aload_0
    //   226: invokespecial 136	com/appannie/tbird/core/engine/c/a/a:k	()Lcom/appannie/tbird/core/engine/c/f/c;
    //   229: astore_1
    //   230: aload_1
    //   231: monitorenter
    //   232: aload 24
    //   234: invokeinterface 266 1 0
    //   239: aload_0
    //   240: invokevirtual 82	com/appannie/tbird/core/engine/c/a/a:g	()Lcom/appannie/tbird/core/engine/g;
    //   243: astore 21
    //   245: aload_1
    //   246: astore 19
    //   248: lload 6
    //   250: lstore 12
    //   252: aload_1
    //   253: astore 20
    //   255: lload 6
    //   257: lstore 8
    //   259: aload_1
    //   260: astore 18
    //   262: aload 17
    //   264: invokeinterface 40 1 0
    //   269: astore 17
    //   271: aload_1
    //   272: astore 19
    //   274: lload 6
    //   276: lstore 12
    //   278: aload_1
    //   279: astore 20
    //   281: lload 6
    //   283: lstore 8
    //   285: aload_1
    //   286: astore 18
    //   288: aload 17
    //   290: invokeinterface 46 1 0
    //   295: ifeq +775 -> 1070
    //   298: aload_1
    //   299: astore 19
    //   301: lload 6
    //   303: lstore 12
    //   305: aload_1
    //   306: astore 20
    //   308: lload 6
    //   310: lstore 8
    //   312: aload_1
    //   313: astore 18
    //   315: aload 17
    //   317: invokeinterface 50 1 0
    //   322: checkcast 268	android/content/pm/PackageInfo
    //   325: astore 22
    //   327: aload_1
    //   328: astore 19
    //   330: lload 6
    //   332: lstore 12
    //   334: aload_1
    //   335: astore 20
    //   337: lload 6
    //   339: lstore 8
    //   341: aload_1
    //   342: astore 18
    //   344: aload 21
    //   346: invokeinterface 270 1 0
    //   351: istore 5
    //   353: iload 5
    //   355: ifeq +37 -> 392
    //   358: lload 6
    //   360: lstore 12
    //   362: aload_1
    //   363: astore 20
    //   365: new 190	com/appannie/tbird/core/a/a/c
    //   368: dup
    //   369: ldc_w 272
    //   372: invokespecial 275	com/appannie/tbird/core/a/a/c:<init>	(Ljava/lang/String;)V
    //   375: athrow
    //   376: astore 16
    //   378: aload_1
    //   379: astore 17
    //   381: aload 16
    //   383: astore_1
    //   384: goto +1062 -> 1446
    //   387: astore 16
    //   389: goto +964 -> 1353
    //   392: aload_1
    //   393: astore 19
    //   395: lload 6
    //   397: lstore 12
    //   399: aload_1
    //   400: astore 20
    //   402: lload 6
    //   404: lstore 8
    //   406: aload_1
    //   407: astore 18
    //   409: aload 22
    //   411: getfield 278	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   414: astore 27
    //   416: aload_1
    //   417: astore 19
    //   419: lload 6
    //   421: lstore 8
    //   423: aload_1
    //   424: astore 18
    //   426: aload_1
    //   427: aload 27
    //   429: invokevirtual 281	com/appannie/tbird/core/engine/c/f/c:a	(Ljava/lang/String;)Lcom/appannie/tbird/core/engine/persistentStore/entities/AppVersion;
    //   432: astore 28
    //   434: aload_1
    //   435: astore 19
    //   437: lload 6
    //   439: lstore 8
    //   441: aload_1
    //   442: astore 18
    //   444: aload 26
    //   446: aload 27
    //   448: invokeinterface 285 2 0
    //   453: pop
    //   454: aload 28
    //   456: ifnull +547 -> 1003
    //   459: aload_1
    //   460: astore 19
    //   462: lload 6
    //   464: lstore 8
    //   466: aload_1
    //   467: astore 18
    //   469: aload 22
    //   471: getfield 289	android/content/pm/PackageInfo:firstInstallTime	J
    //   474: lstore 14
    //   476: lload 6
    //   478: lstore 12
    //   480: aload_1
    //   481: astore 19
    //   483: aload 22
    //   485: getfield 292	android/content/pm/PackageInfo:lastUpdateTime	J
    //   488: lstore 8
    //   490: aload_1
    //   491: astore 19
    //   493: aload 28
    //   495: aload 16
    //   497: invokestatic 294	com/appannie/tbird/core/engine/c/a/a:a	(Lcom/appannie/tbird/core/engine/persistentStore/entities/AppVersion;Landroid/util/SparseArray;)Lcom/appannie/tbird/core/engine/persistentStore/entities/b;
    //   500: astore 20
    //   502: aload 20
    //   504: ifnonnull +9 -> 513
    //   507: iconst_0
    //   508: istore 4
    //   510: goto +13 -> 523
    //   513: aload_1
    //   514: astore 19
    //   516: aload 20
    //   518: getfield 120	com/appannie/tbird/core/engine/persistentStore/entities/b:e	I
    //   521: istore 4
    //   523: aload 17
    //   525: astore 18
    //   527: aload 20
    //   529: ifnonnull +9 -> 538
    //   532: aconst_null
    //   533: astore 17
    //   535: goto +957 -> 1492
    //   538: aload_1
    //   539: astore 19
    //   541: aload 20
    //   543: getfield 55	com/appannie/tbird/core/engine/persistentStore/entities/b:c	Lcom/appannie/tbird/core/engine/persistentStore/entities/AppVersion;
    //   546: astore 17
    //   548: goto +944 -> 1492
    //   551: aload_1
    //   552: astore 19
    //   554: aload 17
    //   556: getfield 296	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion:d	Ljava/lang/String;
    //   559: astore 17
    //   561: aload_1
    //   562: astore 19
    //   564: aload 28
    //   566: getfield 296	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion:d	Ljava/lang/String;
    //   569: astore 22
    //   571: iload 4
    //   573: tableswitch	default:+43->616, 0:+130->703, 1:+62->635, 2:+62->635, 3:+62->635, 4:+948->1521, 5:+49->622, 6:+62->635
    //   616: aload_1
    //   617: astore 17
    //   619: goto +224 -> 843
    //   622: aload 24
    //   624: aload 20
    //   626: invokeinterface 298 2 0
    //   631: pop
    //   632: goto +63 -> 695
    //   635: aload 17
    //   637: ifnull +58 -> 695
    //   640: aload 22
    //   642: ifnull +53 -> 695
    //   645: aload 17
    //   647: aload 22
    //   649: invokevirtual 109	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   652: ifne +43 -> 695
    //   655: aload 20
    //   657: getfield 128	com/appannie/tbird/core/engine/persistentStore/entities/b:d	Ljava/util/Date;
    //   660: invokevirtual 301	java/util/Date:getTime	()J
    //   663: lstore 12
    //   665: lload 12
    //   667: lload 8
    //   669: lcmp
    //   670: ifgt +18 -> 688
    //   673: lload 8
    //   675: lload 10
    //   677: lcmp
    //   678: ifge +10 -> 688
    //   681: iconst_3
    //   682: istore_3
    //   683: iconst_1
    //   684: istore_2
    //   685: goto +867 -> 1552
    //   688: iconst_1
    //   689: istore_2
    //   690: iconst_3
    //   691: istore_3
    //   692: goto +834 -> 1526
    //   695: iload 4
    //   697: istore_3
    //   698: iconst_0
    //   699: istore_2
    //   700: goto +852 -> 1552
    //   703: lload 14
    //   705: lload 8
    //   707: lcmp
    //   708: ifge +825 -> 1533
    //   711: aload_1
    //   712: astore 19
    //   714: new 57	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion
    //   717: dup
    //   718: invokespecial 302	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion:<init>	()V
    //   721: astore 20
    //   723: aload_1
    //   724: astore 19
    //   726: aload 20
    //   728: aload 28
    //   730: getfield 60	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion:b	Lcom/appannie/tbird/core/engine/persistentStore/entities/a;
    //   733: putfield 60	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion:b	Lcom/appannie/tbird/core/engine/persistentStore/entities/a;
    //   736: aload_1
    //   737: astore 19
    //   739: aload 20
    //   741: aload 28
    //   743: getfield 303	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion:c	Ljava/lang/String;
    //   746: putfield 303	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion:c	Ljava/lang/String;
    //   749: aload_1
    //   750: astore 19
    //   752: aload 20
    //   754: aload 28
    //   756: getfield 296	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion:d	Ljava/lang/String;
    //   759: putfield 296	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion:d	Ljava/lang/String;
    //   762: aload_1
    //   763: astore 19
    //   765: aload 20
    //   767: aload 28
    //   769: getfield 304	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion:e	I
    //   772: putfield 304	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion:e	I
    //   775: aload_1
    //   776: astore 19
    //   778: aload 20
    //   780: aload 28
    //   782: getfield 306	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion:f	Ljava/lang/String;
    //   785: putfield 306	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion:f	Ljava/lang/String;
    //   788: aload_1
    //   789: astore 19
    //   791: aload 20
    //   793: aconst_null
    //   794: putfield 308	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion:g	Ljava/lang/String;
    //   797: aload_1
    //   798: astore 19
    //   800: aload 20
    //   802: ldc_w 310
    //   805: invokevirtual 312	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion:c	(Ljava/lang/String;)V
    //   808: aload_1
    //   809: astore 19
    //   811: aload 24
    //   813: aload 20
    //   815: invokeinterface 315 2 0
    //   820: pop
    //   821: aload_1
    //   822: astore 17
    //   824: aload 24
    //   826: aload 25
    //   828: aload 20
    //   830: iconst_2
    //   831: lload 14
    //   833: invokestatic 317	com/appannie/tbird/core/engine/c/a/a:a	(Lcom/appannie/tbird/core/engine/persistentStore/d;Lcom/appannie/tbird/core/engine/persistentStore/entities/j;Lcom/appannie/tbird/core/engine/persistentStore/entities/AppVersion;IJ)V
    //   836: iconst_0
    //   837: istore_2
    //   838: iconst_3
    //   839: istore_3
    //   840: goto +712 -> 1552
    //   843: ldc -107
    //   845: ldc_w 319
    //   848: iconst_1
    //   849: anewarray 196	java/lang/Object
    //   852: dup
    //   853: iconst_0
    //   854: iload 4
    //   856: invokestatic 207	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   859: aastore
    //   860: invokestatic 210	com/appannie/tbird/core/engine/b/f/f:a	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   863: invokestatic 321	com/appannie/tbird/core/engine/b/f/f:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   866: iconst_0
    //   867: istore_2
    //   868: iconst_0
    //   869: istore_3
    //   870: goto +682 -> 1552
    //   873: aload 23
    //   875: astore 17
    //   877: aload 24
    //   879: aload 25
    //   881: aload 28
    //   883: iload_3
    //   884: lload 8
    //   886: invokestatic 317	com/appannie/tbird/core/engine/c/a/a:a	(Lcom/appannie/tbird/core/engine/persistentStore/d;Lcom/appannie/tbird/core/engine/persistentStore/entities/j;Lcom/appannie/tbird/core/engine/persistentStore/entities/AppVersion;IJ)V
    //   889: aload 20
    //   891: astore 19
    //   893: aload 22
    //   895: astore 17
    //   897: goto +110 -> 1007
    //   900: aload 16
    //   902: astore 17
    //   904: astore 16
    //   906: goto +122 -> 1028
    //   909: astore 19
    //   911: aload 16
    //   913: astore 17
    //   915: aload 19
    //   917: astore 16
    //   919: goto +109 -> 1028
    //   922: astore 19
    //   924: aload 17
    //   926: astore 18
    //   928: aload 16
    //   930: astore 17
    //   932: aload 19
    //   934: astore 16
    //   936: goto +92 -> 1028
    //   939: astore 20
    //   941: aload 16
    //   943: astore 18
    //   945: aload 17
    //   947: astore 19
    //   949: aload 20
    //   951: astore 16
    //   953: aload 18
    //   955: astore 17
    //   957: aload 19
    //   959: astore 18
    //   961: goto +67 -> 1028
    //   964: astore 16
    //   966: lload 12
    //   968: lstore 6
    //   970: goto +649 -> 1619
    //   973: astore 18
    //   975: goto +5 -> 980
    //   978: astore 18
    //   980: aload 16
    //   982: astore 19
    //   984: aload 17
    //   986: astore 20
    //   988: aload 18
    //   990: astore 16
    //   992: aload 19
    //   994: astore 17
    //   996: aload 20
    //   998: astore 18
    //   1000: goto +28 -> 1028
    //   1003: aload 16
    //   1005: astore 19
    //   1007: aload 19
    //   1009: astore 16
    //   1011: goto -740 -> 271
    //   1014: astore 19
    //   1016: aload 17
    //   1018: astore 18
    //   1020: aload 16
    //   1022: astore 17
    //   1024: aload 19
    //   1026: astore 16
    //   1028: aload 17
    //   1030: astore 19
    //   1032: aload_1
    //   1033: astore 17
    //   1035: ldc -107
    //   1037: ldc_w 323
    //   1040: iconst_2
    //   1041: anewarray 196	java/lang/Object
    //   1044: dup
    //   1045: iconst_0
    //   1046: aload 27
    //   1048: aastore
    //   1049: dup
    //   1050: iconst_1
    //   1051: aload 16
    //   1053: invokevirtual 327	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   1056: aastore
    //   1057: invokestatic 210	com/appannie/tbird/core/engine/b/f/f:a	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   1060: invokestatic 217	com/appannie/tbird/core/engine/b/f/f:f	(Ljava/lang/String;Ljava/lang/String;)V
    //   1063: aload 18
    //   1065: astore 17
    //   1067: goto -60 -> 1007
    //   1070: lload 6
    //   1072: lstore 8
    //   1074: aload_1
    //   1075: astore 18
    //   1077: aload 18
    //   1079: astore 17
    //   1081: aload 26
    //   1083: invokeinterface 331 1 0
    //   1088: invokeinterface 334 1 0
    //   1093: astore 19
    //   1095: aload 18
    //   1097: astore 17
    //   1099: aload 19
    //   1101: invokeinterface 46 1 0
    //   1106: ifeq +182 -> 1288
    //   1109: aload 18
    //   1111: astore 17
    //   1113: aload 19
    //   1115: invokeinterface 50 1 0
    //   1120: checkcast 57	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion
    //   1123: astore 20
    //   1125: aload 18
    //   1127: astore 17
    //   1129: aload 21
    //   1131: invokeinterface 270 1 0
    //   1136: ifeq +18 -> 1154
    //   1139: aload 18
    //   1141: astore 17
    //   1143: new 190	com/appannie/tbird/core/a/a/c
    //   1146: dup
    //   1147: ldc_w 336
    //   1150: invokespecial 275	com/appannie/tbird/core/a/a/c:<init>	(Ljava/lang/String;)V
    //   1153: athrow
    //   1154: aload 18
    //   1156: astore 17
    //   1158: aload 20
    //   1160: getfield 304	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion:e	I
    //   1163: iconst_5
    //   1164: if_icmpeq +443 -> 1607
    //   1167: aload 18
    //   1169: astore 17
    //   1171: aload 20
    //   1173: aload 16
    //   1175: invokestatic 294	com/appannie/tbird/core/engine/c/a/a:a	(Lcom/appannie/tbird/core/engine/persistentStore/entities/AppVersion;Landroid/util/SparseArray;)Lcom/appannie/tbird/core/engine/persistentStore/entities/b;
    //   1178: astore 22
    //   1180: aload 22
    //   1182: ifnull +425 -> 1607
    //   1185: aload 18
    //   1187: astore 17
    //   1189: aload 22
    //   1191: getfield 120	com/appannie/tbird/core/engine/persistentStore/entities/b:e	I
    //   1194: istore_3
    //   1195: iload_3
    //   1196: iconst_2
    //   1197: if_icmpeq +405 -> 1602
    //   1200: iload_3
    //   1201: bipush 6
    //   1203: if_icmpeq +399 -> 1602
    //   1206: iload_3
    //   1207: iconst_1
    //   1208: if_icmpeq +394 -> 1602
    //   1211: iload_3
    //   1212: iconst_3
    //   1213: if_icmpne +384 -> 1597
    //   1216: goto +386 -> 1602
    //   1219: iload_2
    //   1220: ifeq +22 -> 1242
    //   1223: aload 18
    //   1225: astore 17
    //   1227: aload 24
    //   1229: aload 25
    //   1231: aload 20
    //   1233: iconst_5
    //   1234: lload 10
    //   1236: invokestatic 317	com/appannie/tbird/core/engine/c/a/a:a	(Lcom/appannie/tbird/core/engine/persistentStore/d;Lcom/appannie/tbird/core/engine/persistentStore/entities/j;Lcom/appannie/tbird/core/engine/persistentStore/entities/AppVersion;IJ)V
    //   1239: goto -144 -> 1095
    //   1242: iload_3
    //   1243: iconst_5
    //   1244: if_icmpne -149 -> 1095
    //   1247: aload 18
    //   1249: astore 17
    //   1251: lload 10
    //   1253: aload 22
    //   1255: getfield 128	com/appannie/tbird/core/engine/persistentStore/entities/b:d	Ljava/util/Date;
    //   1258: invokevirtual 301	java/util/Date:getTime	()J
    //   1261: lsub
    //   1262: ldc2_w 337
    //   1265: lcmp
    //   1266: ifle -171 -> 1095
    //   1269: aload 18
    //   1271: astore 17
    //   1273: aload 24
    //   1275: aload 25
    //   1277: aload 20
    //   1279: iconst_4
    //   1280: lload 10
    //   1282: invokestatic 317	com/appannie/tbird/core/engine/c/a/a:a	(Lcom/appannie/tbird/core/engine/persistentStore/d;Lcom/appannie/tbird/core/engine/persistentStore/entities/j;Lcom/appannie/tbird/core/engine/persistentStore/entities/AppVersion;IJ)V
    //   1285: goto -190 -> 1095
    //   1288: aload 18
    //   1290: astore 17
    //   1292: aload 24
    //   1294: invokeinterface 340 1 0
    //   1299: lload 8
    //   1301: lstore 6
    //   1303: aload 18
    //   1305: astore_1
    //   1306: aload_1
    //   1307: astore 16
    //   1309: aload 24
    //   1311: invokeinterface 342 1 0
    //   1316: goto +82 -> 1398
    //   1319: astore 16
    //   1321: goto +298 -> 1619
    //   1324: astore_1
    //   1325: aload 19
    //   1327: astore 17
    //   1329: goto +117 -> 1446
    //   1332: lload 12
    //   1334: lstore 6
    //   1336: aload 20
    //   1338: astore_1
    //   1339: aload_1
    //   1340: astore 17
    //   1342: ldc -107
    //   1344: ldc_w 344
    //   1347: invokestatic 321	com/appannie/tbird/core/engine/b/f/f:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1350: goto -44 -> 1306
    //   1353: aload_1
    //   1354: astore 17
    //   1356: new 346	java/lang/StringBuilder
    //   1359: dup
    //   1360: ldc_w 348
    //   1363: invokespecial 349	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   1366: astore 18
    //   1368: aload_1
    //   1369: astore 17
    //   1371: aload 18
    //   1373: aload 16
    //   1375: invokevirtual 350	com/appannie/tbird/core/a/a/c:getMessage	()Ljava/lang/String;
    //   1378: invokevirtual 354	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1381: pop
    //   1382: aload_1
    //   1383: astore 17
    //   1385: ldc -107
    //   1387: aload 18
    //   1389: invokevirtual 357	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1392: invokestatic 217	com/appannie/tbird/core/engine/b/f/f:f	(Ljava/lang/String;Ljava/lang/String;)V
    //   1395: goto -89 -> 1306
    //   1398: aload_1
    //   1399: astore 16
    //   1401: aload_1
    //   1402: monitorexit
    //   1403: aload_0
    //   1404: invokevirtual 82	com/appannie/tbird/core/engine/c/a/a:g	()Lcom/appannie/tbird/core/engine/g;
    //   1407: iconst_1
    //   1408: invokeinterface 360 2 0
    //   1413: ldc -107
    //   1415: ldc_w 362
    //   1418: iconst_1
    //   1419: anewarray 196	java/lang/Object
    //   1422: dup
    //   1423: iconst_0
    //   1424: invokestatic 223	java/lang/System:currentTimeMillis	()J
    //   1427: lload 6
    //   1429: lsub
    //   1430: invokestatic 367	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1433: aastore
    //   1434: invokestatic 210	com/appannie/tbird/core/engine/b/f/f:a	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   1437: invokestatic 227	com/appannie/tbird/core/engine/b/f/f:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   1440: iconst_1
    //   1441: ireturn
    //   1442: astore_1
    //   1443: goto -114 -> 1329
    //   1446: aload 17
    //   1448: astore 16
    //   1450: aload 24
    //   1452: invokeinterface 342 1 0
    //   1457: aload 17
    //   1459: astore 16
    //   1461: aload_1
    //   1462: athrow
    //   1463: aload 17
    //   1465: astore 16
    //   1467: aload 17
    //   1469: monitorexit
    //   1470: aload_1
    //   1471: athrow
    //   1472: astore_1
    //   1473: aload 16
    //   1475: astore 17
    //   1477: goto -14 -> 1463
    //   1480: iload 5
    //   1482: ireturn
    //   1483: astore_1
    //   1484: goto -152 -> 1332
    //   1487: astore 16
    //   1489: goto -150 -> 1339
    //   1492: aload 17
    //   1494: ifnonnull -943 -> 551
    //   1497: aconst_null
    //   1498: astore 17
    //   1500: goto -939 -> 561
    //   1503: astore 16
    //   1505: goto -1116 -> 389
    //   1508: astore 17
    //   1510: aload 16
    //   1512: astore 19
    //   1514: aload 17
    //   1516: astore 16
    //   1518: goto -486 -> 1032
    //   1521: iconst_0
    //   1522: istore_2
    //   1523: bipush 6
    //   1525: istore_3
    //   1526: lload 10
    //   1528: lstore 8
    //   1530: goto +22 -> 1552
    //   1533: lload 8
    //   1535: lload 14
    //   1537: lcmp
    //   1538: ifge +10 -> 1548
    //   1541: lload 14
    //   1543: lstore 8
    //   1545: goto +3 -> 1548
    //   1548: iconst_0
    //   1549: istore_2
    //   1550: iconst_2
    //   1551: istore_3
    //   1552: aload 18
    //   1554: astore 22
    //   1556: aload_1
    //   1557: astore 23
    //   1559: aload 16
    //   1561: astore 20
    //   1563: iload 4
    //   1565: iload_3
    //   1566: if_icmpne -693 -> 873
    //   1569: aload 20
    //   1571: astore 19
    //   1573: aload 22
    //   1575: astore 17
    //   1577: iload_3
    //   1578: iconst_3
    //   1579: if_icmpne -572 -> 1007
    //   1582: aload 20
    //   1584: astore 19
    //   1586: aload 22
    //   1588: astore 17
    //   1590: iload_2
    //   1591: ifeq -584 -> 1007
    //   1594: goto -721 -> 873
    //   1597: iconst_0
    //   1598: istore_2
    //   1599: goto -380 -> 1219
    //   1602: iconst_1
    //   1603: istore_2
    //   1604: goto -385 -> 1219
    //   1607: goto -512 -> 1095
    //   1610: astore 16
    //   1612: lload 8
    //   1614: lstore 6
    //   1616: aload 18
    //   1618: astore_1
    //   1619: goto -266 -> 1353
    //   1622: astore 16
    //   1624: aload_1
    //   1625: astore 17
    //   1627: aload 16
    //   1629: astore_1
    //   1630: goto -167 -> 1463
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1633	0	this	a
    //   0	1633	1	paramMessage	android.os.Message
    //   40	1564	2	i	int
    //   682	898	3	j	int
    //   508	1059	4	k	int
    //   29	1452	5	bool	boolean
    //   85	1530	6	l1	long
    //   257	1356	8	l2	long
    //   108	1419	10	l3	long
    //   250	1083	12	l4	long
    //   474	1068	14	l5	long
    //   150	31	16	localObject1	Object
    //   376	6	16	localObject2	Object
    //   387	514	16	localC1	com.appannie.tbird.core.a.a.c
    //   904	8	16	localException1	Exception
    //   917	35	16	localObject3	Object
    //   964	17	16	localC2	com.appannie.tbird.core.a.a.c
    //   990	318	16	localObject4	Object
    //   1319	55	16	localC3	com.appannie.tbird.core.a.a.c
    //   1399	75	16	localObject5	Object
    //   1487	1	16	localException2	Exception
    //   1503	8	16	localC4	com.appannie.tbird.core.a.a.c
    //   1516	44	16	localObject6	Object
    //   1610	1	16	localC5	com.appannie.tbird.core.a.a.c
    //   1622	6	16	localObject7	Object
    //   223	1276	17	localObject8	Object
    //   1508	7	17	localException3	Exception
    //   1575	51	17	localObject9	Object
    //   260	700	18	localObject10	Object
    //   973	1	18	localException4	Exception
    //   978	11	18	localException5	Exception
    //   998	619	18	localObject11	Object
    //   246	646	19	localObject12	Object
    //   909	7	19	localException6	Exception
    //   922	11	19	localException7	Exception
    //   947	61	19	localObject13	Object
    //   1014	11	19	localException8	Exception
    //   1030	555	19	localObject14	Object
    //   253	637	20	localObject15	Object
    //   939	11	20	localException9	Exception
    //   986	597	20	localObject16	Object
    //   243	887	21	localG	g
    //   325	1262	22	localObject17	Object
    //   873	685	23	localMessage	android.os.Message
    //   103	1348	24	localD	d
    //   188	1088	25	localJ	j
    //   117	965	26	localHashMap	java.util.HashMap
    //   414	633	27	str	String
    //   432	450	28	localAppVersion	AppVersion
    // Exception table:
    //   from	to	target	type
    //   365	376	376	finally
    //   622	632	376	finally
    //   645	665	376	finally
    //   365	376	387	com/appannie/tbird/core/a/a/c
    //   824	836	900	java/lang/Exception
    //   843	866	900	java/lang/Exception
    //   877	889	900	java/lang/Exception
    //   541	548	909	java/lang/Exception
    //   554	561	909	java/lang/Exception
    //   564	571	909	java/lang/Exception
    //   714	723	909	java/lang/Exception
    //   726	736	909	java/lang/Exception
    //   739	749	909	java/lang/Exception
    //   752	762	909	java/lang/Exception
    //   765	775	909	java/lang/Exception
    //   778	788	909	java/lang/Exception
    //   791	797	909	java/lang/Exception
    //   800	808	909	java/lang/Exception
    //   811	821	909	java/lang/Exception
    //   516	523	922	java/lang/Exception
    //   493	502	939	java/lang/Exception
    //   483	490	964	com/appannie/tbird/core/a/a/c
    //   493	502	964	com/appannie/tbird/core/a/a/c
    //   516	523	964	com/appannie/tbird/core/a/a/c
    //   541	548	964	com/appannie/tbird/core/a/a/c
    //   554	561	964	com/appannie/tbird/core/a/a/c
    //   564	571	964	com/appannie/tbird/core/a/a/c
    //   714	723	964	com/appannie/tbird/core/a/a/c
    //   726	736	964	com/appannie/tbird/core/a/a/c
    //   739	749	964	com/appannie/tbird/core/a/a/c
    //   752	762	964	com/appannie/tbird/core/a/a/c
    //   765	775	964	com/appannie/tbird/core/a/a/c
    //   778	788	964	com/appannie/tbird/core/a/a/c
    //   791	797	964	com/appannie/tbird/core/a/a/c
    //   800	808	964	com/appannie/tbird/core/a/a/c
    //   811	821	964	com/appannie/tbird/core/a/a/c
    //   483	490	973	java/lang/Exception
    //   469	476	978	java/lang/Exception
    //   426	434	1014	java/lang/Exception
    //   444	454	1014	java/lang/Exception
    //   824	836	1319	com/appannie/tbird/core/a/a/c
    //   843	866	1319	com/appannie/tbird/core/a/a/c
    //   877	889	1319	com/appannie/tbird/core/a/a/c
    //   1035	1063	1319	com/appannie/tbird/core/a/a/c
    //   1081	1095	1319	com/appannie/tbird/core/a/a/c
    //   1099	1109	1319	com/appannie/tbird/core/a/a/c
    //   1113	1125	1319	com/appannie/tbird/core/a/a/c
    //   1129	1139	1319	com/appannie/tbird/core/a/a/c
    //   1143	1154	1319	com/appannie/tbird/core/a/a/c
    //   1158	1167	1319	com/appannie/tbird/core/a/a/c
    //   1171	1180	1319	com/appannie/tbird/core/a/a/c
    //   1189	1195	1319	com/appannie/tbird/core/a/a/c
    //   1227	1239	1319	com/appannie/tbird/core/a/a/c
    //   1251	1269	1319	com/appannie/tbird/core/a/a/c
    //   1273	1285	1319	com/appannie/tbird/core/a/a/c
    //   1292	1299	1319	com/appannie/tbird/core/a/a/c
    //   262	271	1324	finally
    //   288	298	1324	finally
    //   315	327	1324	finally
    //   344	353	1324	finally
    //   409	416	1324	finally
    //   426	434	1324	finally
    //   444	454	1324	finally
    //   469	476	1324	finally
    //   483	490	1324	finally
    //   493	502	1324	finally
    //   516	523	1324	finally
    //   541	548	1324	finally
    //   554	561	1324	finally
    //   564	571	1324	finally
    //   714	723	1324	finally
    //   726	736	1324	finally
    //   739	749	1324	finally
    //   752	762	1324	finally
    //   765	775	1324	finally
    //   778	788	1324	finally
    //   791	797	1324	finally
    //   800	808	1324	finally
    //   811	821	1324	finally
    //   824	836	1442	finally
    //   843	866	1442	finally
    //   877	889	1442	finally
    //   1035	1063	1442	finally
    //   1081	1095	1442	finally
    //   1099	1109	1442	finally
    //   1113	1125	1442	finally
    //   1129	1139	1442	finally
    //   1143	1154	1442	finally
    //   1158	1167	1442	finally
    //   1171	1180	1442	finally
    //   1189	1195	1442	finally
    //   1227	1239	1442	finally
    //   1251	1269	1442	finally
    //   1273	1285	1442	finally
    //   1292	1299	1442	finally
    //   1342	1350	1442	finally
    //   1356	1368	1442	finally
    //   1371	1382	1442	finally
    //   1385	1395	1442	finally
    //   1309	1316	1472	finally
    //   1401	1403	1472	finally
    //   1450	1457	1472	finally
    //   1461	1463	1472	finally
    //   1467	1470	1472	finally
    //   262	271	1483	java/lang/Exception
    //   288	298	1483	java/lang/Exception
    //   315	327	1483	java/lang/Exception
    //   344	353	1483	java/lang/Exception
    //   365	376	1483	java/lang/Exception
    //   409	416	1483	java/lang/Exception
    //   1035	1063	1487	java/lang/Exception
    //   1081	1095	1487	java/lang/Exception
    //   1099	1109	1487	java/lang/Exception
    //   1113	1125	1487	java/lang/Exception
    //   1129	1139	1487	java/lang/Exception
    //   1143	1154	1487	java/lang/Exception
    //   1158	1167	1487	java/lang/Exception
    //   1171	1180	1487	java/lang/Exception
    //   1189	1195	1487	java/lang/Exception
    //   1227	1239	1487	java/lang/Exception
    //   1251	1269	1487	java/lang/Exception
    //   1273	1285	1487	java/lang/Exception
    //   1292	1299	1487	java/lang/Exception
    //   622	632	1503	com/appannie/tbird/core/a/a/c
    //   645	665	1503	com/appannie/tbird/core/a/a/c
    //   622	632	1508	java/lang/Exception
    //   645	665	1508	java/lang/Exception
    //   262	271	1610	com/appannie/tbird/core/a/a/c
    //   288	298	1610	com/appannie/tbird/core/a/a/c
    //   315	327	1610	com/appannie/tbird/core/a/a/c
    //   344	353	1610	com/appannie/tbird/core/a/a/c
    //   409	416	1610	com/appannie/tbird/core/a/a/c
    //   426	434	1610	com/appannie/tbird/core/a/a/c
    //   444	454	1610	com/appannie/tbird/core/a/a/c
    //   469	476	1610	com/appannie/tbird/core/a/a/c
    //   232	245	1622	finally
  }
}
