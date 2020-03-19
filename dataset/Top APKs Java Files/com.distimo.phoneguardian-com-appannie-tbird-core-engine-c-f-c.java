package com.appannie.tbird.core.engine.c.f;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Message;
import android.util.SparseArray;
import com.appannie.tbird.core.engine.g;
import com.appannie.tbird.core.engine.persistentStore.entities.AppVersion;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class c
  extends com.appannie.tbird.core.engine.c
  implements a
{
  private static c c;
  private PackageManager d;
  private Map<String, AppVersion> e;
  private Map<String, AppVersion> f;
  private SparseArray<AppVersion> g;
  private d h;
  
  c() {}
  
  private Map<String, com.appannie.tbird.core.engine.persistentStore.entities.a> a(com.appannie.tbird.core.engine.persistentStore.d paramD)
  {
    HashMap localHashMap = new HashMap();
    try
    {
      paramD = paramD.a(this).iterator();
      while (paramD.hasNext())
      {
        com.appannie.tbird.core.engine.persistentStore.entities.a localA = (com.appannie.tbird.core.engine.persistentStore.entities.a)paramD.next();
        if (b()) {
          break;
        }
        localHashMap.put(localA.b, localA);
      }
      return localHashMap;
    }
    catch (com.appannie.tbird.core.a.a.c paramD) {}
    return localHashMap;
  }
  
  public static c l()
  {
    try
    {
      if (c == null)
      {
        com.appannie.tbird.core.engine.b.f.f.a("InstalledPackageMonitor", "<--> getInstance(++ CREATED ++)");
        c = new c();
      }
      c localC = c;
      return localC;
    }
    finally {}
  }
  
  private PackageManager p()
  {
    if (this.d == null) {
      this.d = g().c().getPackageManager();
    }
    return this.d;
  }
  
  private void q()
  {
    try
    {
      this.e = null;
      this.f = null;
      this.g = null;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  /* Error */
  private void r()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic 115	java/lang/System:currentTimeMillis	()J
    //   5: lstore_1
    //   6: ldc 74
    //   8: ldc 117
    //   10: invokestatic 119	com/appannie/tbird/core/engine/b/f/f:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   13: aload_0
    //   14: invokevirtual 89	com/appannie/tbird/core/engine/c/f/c:g	()Lcom/appannie/tbird/core/engine/g;
    //   17: invokeinterface 122 1 0
    //   22: astore 7
    //   24: aload_0
    //   25: new 30	java/util/HashMap
    //   28: dup
    //   29: invokespecial 31	java/util/HashMap:<init>	()V
    //   32: putfield 104	com/appannie/tbird/core/engine/c/f/c:f	Ljava/util/Map;
    //   35: aload_0
    //   36: new 124	android/util/SparseArray
    //   39: dup
    //   40: invokespecial 125	android/util/SparseArray:<init>	()V
    //   43: putfield 106	com/appannie/tbird/core/engine/c/f/c:g	Landroid/util/SparseArray;
    //   46: aload_0
    //   47: invokespecial 127	com/appannie/tbird/core/engine/c/f/c:p	()Landroid/content/pm/PackageManager;
    //   50: sipush 128
    //   53: invokevirtual 133	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   56: astore 6
    //   58: aload_0
    //   59: aload 7
    //   61: invokespecial 135	com/appannie/tbird/core/engine/c/f/c:a	(Lcom/appannie/tbird/core/engine/persistentStore/d;)Ljava/util/Map;
    //   64: astore 8
    //   66: aload_0
    //   67: invokevirtual 57	com/appannie/tbird/core/engine/c/f/c:b	()Z
    //   70: istore_3
    //   71: iload_3
    //   72: ifeq +6 -> 78
    //   75: aload_0
    //   76: monitorexit
    //   77: return
    //   78: aload 7
    //   80: invokeinterface 137 1 0
    //   85: invokestatic 142	com/appannie/tbird/core/engine/b/b/b$a:a	()Ljava/util/ArrayList;
    //   88: invokevirtual 145	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   91: astore 9
    //   93: aload 9
    //   95: invokeinterface 48 1 0
    //   100: istore_3
    //   101: aconst_null
    //   102: astore 5
    //   104: iload_3
    //   105: ifeq +179 -> 284
    //   108: aload 9
    //   110: invokeinterface 52 1 0
    //   115: checkcast 139	com/appannie/tbird/core/engine/b/b/b$a
    //   118: astore 10
    //   120: aload_0
    //   121: invokevirtual 57	com/appannie/tbird/core/engine/c/f/c:b	()Z
    //   124: ifne +160 -> 284
    //   127: aload_0
    //   128: getfield 147	com/appannie/tbird/core/engine/c/f/c:h	Lcom/appannie/tbird/core/engine/c/f/d;
    //   131: astore 11
    //   133: aload_0
    //   134: invokevirtual 89	com/appannie/tbird/core/engine/c/f/c:g	()Lcom/appannie/tbird/core/engine/g;
    //   137: invokeinterface 94 1 0
    //   142: pop
    //   143: aload 10
    //   145: getfield 150	com/appannie/tbird/core/engine/b/b/b$a:i	Ljava/lang/String;
    //   148: astore 12
    //   150: aload 12
    //   152: invokestatic 156	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   155: ifeq +31 -> 186
    //   158: ldc 74
    //   160: ldc -98
    //   162: iconst_1
    //   163: anewarray 160	java/lang/Object
    //   166: dup
    //   167: iconst_0
    //   168: aload 10
    //   170: getfield 163	com/appannie/tbird/core/engine/b/b/b$a:j	Ljava/lang/String;
    //   173: aastore
    //   174: invokestatic 166	com/appannie/tbird/core/engine/b/f/f:a	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   177: invokestatic 168	com/appannie/tbird/core/engine/b/f/f:f	(Ljava/lang/String;Ljava/lang/String;)V
    //   180: aconst_null
    //   181: astore 4
    //   183: goto +39 -> 222
    //   186: new 54	com/appannie/tbird/core/engine/persistentStore/entities/a
    //   189: dup
    //   190: invokespecial 169	com/appannie/tbird/core/engine/persistentStore/entities/a:<init>	()V
    //   193: astore 4
    //   195: aload 4
    //   197: aload 10
    //   199: getfield 163	com/appannie/tbird/core/engine/b/b/b$a:j	Ljava/lang/String;
    //   202: putfield 60	com/appannie/tbird/core/engine/persistentStore/entities/a:b	Ljava/lang/String;
    //   205: aload 4
    //   207: aload 12
    //   209: putfield 171	com/appannie/tbird/core/engine/persistentStore/entities/a:d	Ljava/lang/String;
    //   212: aload 4
    //   214: aload 10
    //   216: getfield 174	com/appannie/tbird/core/engine/b/b/b$a:h	I
    //   219: putfield 176	com/appannie/tbird/core/engine/persistentStore/entities/a:c	I
    //   222: aload 4
    //   224: ifnull +46 -> 270
    //   227: new 178	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion
    //   230: dup
    //   231: invokespecial 179	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion:<init>	()V
    //   234: astore 5
    //   236: aload 5
    //   238: aload 4
    //   240: invokevirtual 182	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion:a	(Lcom/appannie/tbird/core/engine/persistentStore/entities/a;)V
    //   243: aload 5
    //   245: aload 10
    //   247: getfield 185	com/appannie/tbird/core/engine/b/b/b$a:k	Ljava/lang/String;
    //   250: invokevirtual 188	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion:c	(Ljava/lang/String;)V
    //   253: aload 5
    //   255: invokestatic 194	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   258: invokevirtual 198	java/util/Locale:toString	()Ljava/lang/String;
    //   261: invokevirtual 200	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion:b	(Ljava/lang/String;)V
    //   264: aload 5
    //   266: iconst_5
    //   267: invokevirtual 203	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion:a	(I)V
    //   270: aload 11
    //   272: aload 7
    //   274: aload 5
    //   276: aload 8
    //   278: invokevirtual 208	com/appannie/tbird/core/engine/c/f/d:a	(Lcom/appannie/tbird/core/engine/persistentStore/d;Lcom/appannie/tbird/core/engine/persistentStore/entities/AppVersion;Ljava/util/Map;)V
    //   281: goto -188 -> 93
    //   284: aload 6
    //   286: invokeinterface 42 1 0
    //   291: astore 9
    //   293: aload 9
    //   295: invokeinterface 48 1 0
    //   300: ifeq +213 -> 513
    //   303: aload 9
    //   305: invokeinterface 52 1 0
    //   310: checkcast 210	android/content/pm/PackageInfo
    //   313: astore 5
    //   315: aload_0
    //   316: invokevirtual 57	com/appannie/tbird/core/engine/c/f/c:b	()Z
    //   319: ifne +194 -> 513
    //   322: aload_0
    //   323: getfield 147	com/appannie/tbird/core/engine/c/f/c:h	Lcom/appannie/tbird/core/engine/c/f/d;
    //   326: astore 10
    //   328: aload_0
    //   329: invokespecial 127	com/appannie/tbird/core/engine/c/f/c:p	()Landroid/content/pm/PackageManager;
    //   332: aload 5
    //   334: invokestatic 215	com/appannie/tbird/core/engine/b/f/c:a	(Landroid/content/pm/PackageManager;Landroid/content/pm/PackageInfo;)Ljava/lang/String;
    //   337: astore 6
    //   339: aload 6
    //   341: invokestatic 156	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   344: ifeq +31 -> 375
    //   347: ldc 74
    //   349: ldc -98
    //   351: iconst_1
    //   352: anewarray 160	java/lang/Object
    //   355: dup
    //   356: iconst_0
    //   357: aload 5
    //   359: getfield 218	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   362: aastore
    //   363: invokestatic 166	com/appannie/tbird/core/engine/b/f/f:a	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   366: invokestatic 168	com/appannie/tbird/core/engine/b/f/f:f	(Ljava/lang/String;Ljava/lang/String;)V
    //   369: aconst_null
    //   370: astore 4
    //   372: goto +42 -> 414
    //   375: new 54	com/appannie/tbird/core/engine/persistentStore/entities/a
    //   378: dup
    //   379: invokespecial 169	com/appannie/tbird/core/engine/persistentStore/entities/a:<init>	()V
    //   382: astore 4
    //   384: aload 4
    //   386: aload 5
    //   388: getfield 218	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   391: putfield 60	com/appannie/tbird/core/engine/persistentStore/entities/a:b	Ljava/lang/String;
    //   394: aload 4
    //   396: aload 6
    //   398: putfield 171	com/appannie/tbird/core/engine/persistentStore/entities/a:d	Ljava/lang/String;
    //   401: aload 4
    //   403: aload 5
    //   405: getfield 222	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   408: getfield 227	android/content/pm/ApplicationInfo:uid	I
    //   411: putfield 176	com/appannie/tbird/core/engine/persistentStore/entities/a:c	I
    //   414: aload 4
    //   416: ifnull +186 -> 602
    //   419: new 178	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion
    //   422: dup
    //   423: invokespecial 179	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion:<init>	()V
    //   426: astore 6
    //   428: aload 6
    //   430: aload 4
    //   432: invokevirtual 182	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion:a	(Lcom/appannie/tbird/core/engine/persistentStore/entities/a;)V
    //   435: aload 5
    //   437: getfield 230	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   440: invokestatic 156	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   443: ifeq +10 -> 453
    //   446: ldc -24
    //   448: astore 5
    //   450: goto +10 -> 460
    //   453: aload 5
    //   455: getfield 230	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   458: astore 5
    //   460: aload 6
    //   462: aload 5
    //   464: invokevirtual 188	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion:c	(Ljava/lang/String;)V
    //   467: aload 6
    //   469: invokestatic 194	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   472: invokevirtual 198	java/util/Locale:toString	()Ljava/lang/String;
    //   475: invokevirtual 200	com/appannie/tbird/core/engine/persistentStore/entities/AppVersion:b	(Ljava/lang/String;)V
    //   478: aload_0
    //   479: invokespecial 127	com/appannie/tbird/core/engine/c/f/c:p	()Landroid/content/pm/PackageManager;
    //   482: aload 4
    //   484: getfield 60	com/appannie/tbird/core/engine/persistentStore/entities/a:b	Ljava/lang/String;
    //   487: aload 6
    //   489: invokestatic 235	com/appannie/tbird/core/engine/b/f/c:a	(Landroid/content/pm/PackageManager;Ljava/lang/String;Lcom/appannie/tbird/core/engine/d/d;)V
    //   492: aload 6
    //   494: astore 4
    //   496: goto +3 -> 499
    //   499: aload 10
    //   501: aload 7
    //   503: aload 4
    //   505: aload 8
    //   507: invokevirtual 208	com/appannie/tbird/core/engine/c/f/d:a	(Lcom/appannie/tbird/core/engine/persistentStore/d;Lcom/appannie/tbird/core/engine/persistentStore/entities/AppVersion;Ljava/util/Map;)V
    //   510: goto -217 -> 293
    //   513: aload_0
    //   514: invokevirtual 57	com/appannie/tbird/core/engine/c/f/c:b	()Z
    //   517: ifne +10 -> 527
    //   520: aload 7
    //   522: invokeinterface 237 1 0
    //   527: aload 7
    //   529: invokeinterface 239 1 0
    //   534: goto +18 -> 552
    //   537: astore 4
    //   539: goto +41 -> 580
    //   542: ldc 74
    //   544: ldc -15
    //   546: invokestatic 243	com/appannie/tbird/core/engine/b/f/f:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   549: goto -22 -> 527
    //   552: ldc 74
    //   554: ldc -11
    //   556: iconst_1
    //   557: anewarray 160	java/lang/Object
    //   560: dup
    //   561: iconst_0
    //   562: invokestatic 115	java/lang/System:currentTimeMillis	()J
    //   565: lload_1
    //   566: lsub
    //   567: invokestatic 251	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   570: aastore
    //   571: invokestatic 166	com/appannie/tbird/core/engine/b/f/f:a	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   574: invokestatic 119	com/appannie/tbird/core/engine/b/f/f:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   577: aload_0
    //   578: monitorexit
    //   579: return
    //   580: aload 7
    //   582: invokeinterface 239 1 0
    //   587: aload 4
    //   589: athrow
    //   590: astore 4
    //   592: aload_0
    //   593: monitorexit
    //   594: aload 4
    //   596: athrow
    //   597: astore 4
    //   599: goto -57 -> 542
    //   602: aconst_null
    //   603: astore 4
    //   605: goto -106 -> 499
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	608	0	this	c
    //   5	561	1	l	long
    //   70	35	3	bool	boolean
    //   181	323	4	localObject1	Object
    //   537	51	4	localObject2	Object
    //   590	5	4	localObject3	Object
    //   597	1	4	localException	Exception
    //   603	1	4	localObject4	Object
    //   102	361	5	localObject5	Object
    //   56	437	6	localObject6	Object
    //   22	559	7	localD	com.appannie.tbird.core.engine.persistentStore.d
    //   64	442	8	localMap	Map
    //   91	213	9	localIterator	Iterator
    //   118	382	10	localObject7	Object
    //   131	140	11	localD1	d
    //   148	60	12	str	String
    // Exception table:
    //   from	to	target	type
    //   85	93	537	finally
    //   93	101	537	finally
    //   108	180	537	finally
    //   186	222	537	finally
    //   227	270	537	finally
    //   270	281	537	finally
    //   284	293	537	finally
    //   293	369	537	finally
    //   375	414	537	finally
    //   419	446	537	finally
    //   453	460	537	finally
    //   460	492	537	finally
    //   499	510	537	finally
    //   513	527	537	finally
    //   542	549	537	finally
    //   2	71	590	finally
    //   78	85	590	finally
    //   527	534	590	finally
    //   552	577	590	finally
    //   580	590	590	finally
    //   85	93	597	java/lang/Exception
    //   93	101	597	java/lang/Exception
    //   108	180	597	java/lang/Exception
    //   186	222	597	java/lang/Exception
    //   227	270	597	java/lang/Exception
    //   270	281	597	java/lang/Exception
    //   284	293	597	java/lang/Exception
    //   293	369	597	java/lang/Exception
    //   375	414	597	java/lang/Exception
    //   419	446	597	java/lang/Exception
    //   453	460	597	java/lang/Exception
    //   460	492	597	java/lang/Exception
    //   499	510	597	java/lang/Exception
    //   513	527	597	java/lang/Exception
  }
  
  public final AppVersion a(int paramInt)
  {
    try
    {
      AppVersion localAppVersion = (AppVersion)o().get(paramInt);
      if (localAppVersion == null) {
        com.appannie.tbird.core.engine.b.f.f.f("InstalledPackageMonitor", com.appannie.tbird.core.engine.b.f.f.a("<--> getAppVersionForUid(%d) failed to find matching entity", new Object[] { Integer.valueOf(paramInt) }));
      }
      return localAppVersion;
    }
    finally {}
  }
  
  public final AppVersion a(String paramString)
  {
    try
    {
      AppVersion localAppVersion = (AppVersion)n().get(paramString);
      if (localAppVersion == null) {
        com.appannie.tbird.core.engine.b.f.f.f("InstalledPackageMonitor", String.format("<--> getAppVersionForPackageNameAndLocale(%s) failed to find matching entity", new Object[] { paramString }));
      }
      return localAppVersion;
    }
    finally {}
  }
  
  /* Error */
  public final void a()
  {
    // Byte code:
    //   0: ldc 74
    //   2: ldc_w 284
    //   5: invokestatic 81	com/appannie/tbird/core/engine/b/f/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   8: aload_0
    //   9: monitorenter
    //   10: aload_0
    //   11: invokevirtual 287	com/appannie/tbird/core/engine/c/f/c:f_	()Z
    //   14: ifeq +7 -> 21
    //   17: aload_0
    //   18: invokespecial 289	com/appannie/tbird/core/engine/c/f/c:q	()V
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_0
    //   24: invokespecial 291	com/appannie/tbird/core/engine/c:a	()V
    //   27: ldc 2
    //   29: monitorenter
    //   30: aconst_null
    //   31: putstatic 72	com/appannie/tbird/core/engine/c/f/c:c	Lcom/appannie/tbird/core/engine/c/f/c;
    //   34: ldc 2
    //   36: monitorexit
    //   37: ldc 74
    //   39: ldc_w 293
    //   42: invokestatic 81	com/appannie/tbird/core/engine/b/f/f:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   45: return
    //   46: astore_1
    //   47: ldc 2
    //   49: monitorexit
    //   50: aload_1
    //   51: athrow
    //   52: astore_1
    //   53: aload_0
    //   54: monitorexit
    //   55: aload_1
    //   56: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	57	0	this	c
    //   46	5	1	localObject1	Object
    //   52	4	1	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   30	37	46	finally
    //   47	50	46	finally
    //   10	21	52	finally
    //   21	23	52	finally
    //   53	55	52	finally
  }
  
  public final void a(g paramG)
  {
    try
    {
      com.appannie.tbird.core.engine.b.f.f.a("InstalledPackageMonitor", "--> start()");
      super.a(paramG);
      this.h = new d();
      r();
      com.appannie.tbird.core.engine.b.f.f.a("InstalledPackageMonitor", "<-- start()");
      return;
    }
    finally
    {
      paramG = finally;
      throw paramG;
    }
  }
  
  protected final boolean a(Message paramMessage)
  {
    com.appannie.tbird.core.engine.b.f.f.a("InstalledPackageMonitor", com.appannie.tbird.core.engine.b.f.f.a("--> processMessage(%d)", new Object[] { Integer.valueOf(paramMessage.what) }));
    if (!super.a(paramMessage)) {
      if (paramMessage.what != 1001) {
        com.appannie.tbird.core.engine.b.f.f.f("InstalledPackageMonitor", com.appannie.tbird.core.engine.b.f.f.a("[%d] was unexpected", new Object[] { Integer.valueOf(paramMessage.what) }));
      } else {
        b(e_());
      }
    }
    com.appannie.tbird.core.engine.b.f.f.a("InstalledPackageMonitor", "<-- processMessage()");
    return true;
  }
  
  public final void b(Intent arg1)
  {
    super.b(???);
    ??? = com.appannie.tbird.core.a.a.d.a(???.getAction());
    int i = ???.hashCode();
    if (i != -810471698)
    {
      if (i != -19011148)
      {
        if (i != 525384130)
        {
          if ((i == 1544582882) && (???.equals("android.intent.action.PACKAGE_ADDED")))
          {
            i = 0;
            break label111;
          }
        }
        else if (???.equals("android.intent.action.PACKAGE_REMOVED"))
        {
          i = 2;
          break label111;
        }
      }
      else if (???.equals("android.intent.action.LOCALE_CHANGED"))
      {
        i = 3;
        break label111;
      }
    }
    else if (???.equals("android.intent.action.PACKAGE_REPLACED"))
    {
      i = 1;
      break label111;
    }
    i = -1;
    switch (i)
    {
    default: 
      com.appannie.tbird.core.engine.b.f.f.d("InstalledPackageMonitor", com.appannie.tbird.core.engine.b.f.f.a("[%s] was unexpected", new Object[] { ??? }));
      return;
    case 3: 
      q();
      r();
      return;
    case 2: 
      r();
      synchronized (this.a)
      {
        Iterator localIterator1 = this.a.iterator();
        while (localIterator1.hasNext()) {
          ((b)localIterator1.next()).c();
        }
        return;
      }
    case 1: 
      label111:
      r();
      synchronized (this.a)
      {
        Iterator localIterator2 = this.a.iterator();
        while (localIterator2.hasNext()) {
          ((b)localIterator2.next()).b();
        }
        return;
      }
    }
    r();
    synchronized (this.a)
    {
      Iterator localIterator3 = this.a.iterator();
      while (localIterator3.hasNext()) {
        ((b)localIterator3.next()).a();
      }
      return;
    }
  }
  
  final Map<String, AppVersion> m()
  {
    try
    {
      if (this.e == null)
      {
        localObject1 = g().g();
        this.e = new HashMap();
        localObject1 = ((com.appannie.tbird.core.engine.persistentStore.d)localObject1).e().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          AppVersion localAppVersion = (AppVersion)((Iterator)localObject1).next();
          if (b()) {
            break;
          }
          this.e.put(localAppVersion.a(), localAppVersion);
        }
      }
      Object localObject1 = this.e;
      return localObject1;
    }
    finally {}
  }
  
  final Map<String, AppVersion> n()
  {
    try
    {
      if (this.f == null) {
        r();
      }
      Map localMap = this.f;
      return localMap;
    }
    finally {}
  }
  
  final SparseArray<AppVersion> o()
  {
    try
    {
      if (this.g == null) {
        r();
      }
      SparseArray localSparseArray = this.g;
      return localSparseArray;
    }
    finally {}
  }
}
