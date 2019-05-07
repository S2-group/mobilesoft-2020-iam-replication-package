package com.bandwidthx.library;

import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.Iterator;

public class ao
{
  private static bk p = null;
  private static aw.b t = null;
  String a = "";
  Long b = Long.valueOf(0L);
  Integer c = Integer.valueOf(0);
  String d = "";
  Long e = Long.valueOf(0L);
  Long f = Long.valueOf(0L);
  Boolean g = Boolean.valueOf(false);
  Boolean h = Boolean.valueOf(false);
  Boolean i = Boolean.valueOf(false);
  Boolean j = Boolean.valueOf(false);
  Boolean k = Boolean.valueOf(false);
  Boolean l = Boolean.valueOf(false);
  protected Long m = Long.valueOf(0L);
  protected Long n = Long.valueOf(10000L);
  protected Long o = Long.valueOf(10000L);
  private Boolean q = Boolean.valueOf(true);
  private Boolean r = Boolean.valueOf(true);
  private Long s = Long.valueOf(0L);
  
  public ao(bk paramBk)
  {
    try
    {
      p = paramBk;
      if (t == null)
      {
        t = new aw.b() {};
        bk.v();
        aw.a(t);
      }
      return;
    }
    catch (Exception paramBk)
    {
      bj.a(paramBk);
    }
  }
  
  private Boolean g()
  {
    Object localObject1 = this.q;
    try
    {
      Object localObject2;
      Long localLong;
      Double localDouble1;
      Double localDouble2;
      Object localObject3;
      if (this.m.longValue() < eb.d().longValue() - this.n.longValue())
      {
        this.q = Boolean.valueOf(true);
        this.m = eb.d();
        localObject2 = eb.d();
        localLong = Long.valueOf(((Long)localObject2).longValue() / 86400000L * 86400000L);
        localDouble1 = bk.y().h();
        localDouble2 = bk.y().i();
        if (bk.I().a(Integer.valueOf(62)).booleanValue())
        {
          this.q = Boolean.valueOf(false);
          localObject3 = bk.I().a(Integer.valueOf(63));
          localObject4 = bk.I().b(Integer.valueOf(64));
          localObject5 = "|" + bk.I().d(Integer.valueOf(65)).toLowerCase() + "|";
          if (!((Boolean)localObject3).booleanValue()) {
            break label1176;
          }
          bk.A();
          if (!cq.i().booleanValue()) {
            break label1176;
          }
        }
      }
      for (this.q = Boolean.valueOf(true);; this.q = Boolean.valueOf(true))
      {
        Object localObject6;
        if (!this.q.booleanValue())
        {
          localObject3 = bk.A().n();
          if (((String)localObject3).length() > 0)
          {
            localObject4 = ((String)localObject3).split(":");
            localObject3 = localObject4[0];
            localObject4 = localObject4[1];
            localObject5 = bk.I().e(Integer.valueOf(66)).iterator();
            while (((Iterator)localObject5).hasNext())
            {
              localObject6 = (ao.a)((Iterator)localObject5).next();
              if ((((Long)localObject2).longValue() >= localLong.longValue() + ((ao.a)localObject6).b.longValue()) && (((Long)localObject2).longValue() <= localLong.longValue() + ((ao.a)localObject6).c.longValue()))
              {
                localObject6 = "|" + ((ao.a)localObject6).a + "|";
                if ((((String)localObject6).contains("|" + (String)localObject3 + "|")) || (((String)localObject6).contains("|" + (String)localObject3 + ":" + (String)localObject4 + "|"))) {
                  this.q = Boolean.valueOf(true);
                }
              }
            }
          }
        }
        if (!this.q.booleanValue())
        {
          localObject3 = bk.I().f(Integer.valueOf(67)).iterator();
          while (((Iterator)localObject3).hasNext())
          {
            localObject4 = (ao.b)((Iterator)localObject3).next();
            if ((((Long)localObject2).longValue() >= localLong.longValue() + ((ao.b)localObject4).e.longValue()) && (((Long)localObject2).longValue() <= localLong.longValue() + ((ao.b)localObject4).f.longValue()) && (localDouble1.doubleValue() >= ((ao.b)localObject4).b.doubleValue()) && (localDouble1.doubleValue() <= ((ao.b)localObject4).d.doubleValue()) && (localDouble2.doubleValue() >= ((ao.b)localObject4).a.doubleValue()) && (localDouble2.doubleValue() <= ((ao.b)localObject4).c.doubleValue())) {
              this.q = Boolean.valueOf(true);
            }
          }
        }
        if (bk.I().a(Integer.valueOf(68)).booleanValue())
        {
          localObject3 = bk.I().a(Integer.valueOf(69));
          localObject4 = bk.I().b(Integer.valueOf(70));
          localObject5 = "|" + bk.I().d(Integer.valueOf(71)).toLowerCase() + "|";
          if (!((Boolean)localObject3).booleanValue()) {
            break label1280;
          }
          bk.A();
          if (!cq.i().booleanValue()) {
            break label1280;
          }
          this.q = Boolean.valueOf(false);
          if (this.q.booleanValue())
          {
            localObject3 = bk.A().n();
            if (((String)localObject3).length() > 0)
            {
              localObject4 = ((String)localObject3).split(":");
              localObject3 = localObject4[0];
              localObject4 = localObject4[1];
              localObject5 = bk.I().e(Integer.valueOf(72)).iterator();
              while (((Iterator)localObject5).hasNext())
              {
                localObject6 = (ao.a)((Iterator)localObject5).next();
                if ((((Long)localObject2).longValue() >= localLong.longValue() + ((ao.a)localObject6).b.longValue()) && (((Long)localObject2).longValue() <= localLong.longValue() + ((ao.a)localObject6).c.longValue()))
                {
                  localObject6 = "|" + ((ao.a)localObject6).a + "|";
                  if ((((String)localObject6).contains("|" + (String)localObject3 + "|")) || (((String)localObject6).contains("|" + (String)localObject3 + ":" + (String)localObject4 + "|"))) {
                    this.q = Boolean.valueOf(false);
                  }
                }
              }
            }
          }
          if (this.q.booleanValue())
          {
            localObject3 = bk.I().f(Integer.valueOf(73)).iterator();
            while (((Iterator)localObject3).hasNext())
            {
              localObject4 = (ao.b)((Iterator)localObject3).next();
              if ((((Long)localObject2).longValue() >= localLong.longValue() + ((ao.b)localObject4).e.longValue()) && (((Long)localObject2).longValue() <= localLong.longValue() + ((ao.b)localObject4).f.longValue()) && (localDouble1.doubleValue() >= ((ao.b)localObject4).b.doubleValue()) && (localDouble1.doubleValue() <= ((ao.b)localObject4).d.doubleValue()) && (localDouble2.doubleValue() >= ((ao.b)localObject4).a.doubleValue()) && (localDouble2.doubleValue() <= ((ao.b)localObject4).c.doubleValue())) {
                this.q = Boolean.valueOf(false);
              }
            }
          }
        }
        if (localObject1 != this.q)
        {
          localObject2 = new StringBuilder().append("APP Policy is now ");
          if (!this.q.booleanValue()) {
            break label1376;
          }
          localObject1 = "resume";
          bj.b((String)localObject1);
        }
        return this.q;
        label1176:
        if ((((Integer)localObject4).intValue() >= 0) || (((Integer)localObject4).intValue() > bk.A().o().intValue())) {
          break;
        }
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        Object localObject4;
        Object localObject5;
        bj.a(localException);
        continue;
        if ((((String)localObject5).length() > 2) && (((String)localObject5).contains("|" + bk.A().g().toLowerCase() + "|")))
        {
          this.q = Boolean.valueOf(true);
          continue;
          label1280:
          if ((((Integer)localObject4).intValue() < 0) && (((Integer)localObject4).intValue() <= bk.A().o().intValue()))
          {
            this.q = Boolean.valueOf(false);
          }
          else if ((((String)localObject5).length() > 2) && (((String)localObject5).contains("|" + bk.A().g().toLowerCase() + "|")))
          {
            this.q = Boolean.valueOf(false);
            continue;
            label1376:
            localObject1 = "suspend";
          }
        }
      }
    }
  }
  
  public final Boolean a()
  {
    if (c().longValue() > 0L) {}
    for (boolean bool = true;; bool = false) {
      return Boolean.valueOf(bool);
    }
  }
  
  /* Error */
  public final Boolean a(Boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 99	com/bandwidthx/library/ao:r	Ljava/lang/Boolean;
    //   6: astore 10
    //   8: aload_1
    //   9: invokevirtual 159	java/lang/Boolean:booleanValue	()Z
    //   12: ifeq +32 -> 44
    //   15: invokestatic 275	com/bandwidthx/library/bk:h	()Lcom/bandwidthx/library/aa;
    //   18: invokevirtual 279	com/bandwidthx/library/aa:k	()Ljava/lang/Boolean;
    //   21: invokevirtual 159	java/lang/Boolean:booleanValue	()Z
    //   24: ifeq +20 -> 44
    //   27: aload_0
    //   28: lconst_0
    //   29: invokestatic 57	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   32: putfield 101	com/bandwidthx/library/ao:s	Ljava/lang/Long;
    //   35: iconst_1
    //   36: invokestatic 77	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   39: astore_1
    //   40: aload_0
    //   41: monitorexit
    //   42: aload_1
    //   43: areturn
    //   44: invokestatic 150	com/bandwidthx/library/bk:I	()Lcom/bandwidthx/library/db;
    //   47: bipush 88
    //   49: invokestatic 64	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   52: invokevirtual 174	com/bandwidthx/library/db:d	(Ljava/lang/Integer;)Ljava/lang/String;
    //   55: invokevirtual 197	java/lang/String:length	()I
    //   58: ifle +600 -> 658
    //   61: invokestatic 150	com/bandwidthx/library/bk:I	()Lcom/bandwidthx/library/db;
    //   64: bipush 88
    //   66: invokestatic 64	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   69: invokevirtual 174	com/bandwidthx/library/db:d	(Ljava/lang/Integer;)Ljava/lang/String;
    //   72: invokestatic 285	java/util/regex/Pattern:compile	(Ljava/lang/String;)Ljava/util/regex/Pattern;
    //   75: astore 11
    //   77: aload_0
    //   78: getfield 101	com/bandwidthx/library/ao:s	Ljava/lang/Long;
    //   81: invokevirtual 128	java/lang/Long:longValue	()J
    //   84: invokestatic 133	com/bandwidthx/library/eb:d	()Ljava/lang/Long;
    //   87: invokevirtual 128	java/lang/Long:longValue	()J
    //   90: aload_0
    //   91: getfield 103	com/bandwidthx/library/ao:o	Ljava/lang/Long;
    //   94: invokevirtual 128	java/lang/Long:longValue	()J
    //   97: lsub
    //   98: lcmp
    //   99: ifge +501 -> 600
    //   102: aload_0
    //   103: iconst_1
    //   104: invokestatic 77	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   107: putfield 99	com/bandwidthx/library/ao:r	Ljava/lang/Boolean;
    //   110: aload_0
    //   111: invokestatic 133	com/bandwidthx/library/eb:d	()Ljava/lang/Long;
    //   114: putfield 101	com/bandwidthx/library/ao:s	Ljava/lang/Long;
    //   117: lconst_0
    //   118: invokestatic 57	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   121: astore 6
    //   123: ldc 49
    //   125: astore 7
    //   127: invokestatic 288	com/bandwidthx/library/bk:e	()Landroid/content/Context;
    //   130: invokevirtual 294	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   133: astore 12
    //   135: aload 12
    //   137: iconst_0
    //   138: invokevirtual 300	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   141: invokeinterface 303 1 0
    //   146: astore 13
    //   148: ldc 49
    //   150: astore_1
    //   151: aload 13
    //   153: invokeinterface 217 1 0
    //   158: ifeq +353 -> 511
    //   161: aload 13
    //   163: invokeinterface 221 1 0
    //   168: checkcast 305	android/content/pm/ApplicationInfo
    //   171: astore 8
    //   173: invokestatic 275	com/bandwidthx/library/bk:h	()Lcom/bandwidthx/library/aa;
    //   176: pop
    //   177: aload 8
    //   179: getfield 308	android/content/pm/ApplicationInfo:uid	I
    //   182: invokestatic 64	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   185: invokestatic 310	com/bandwidthx/library/aa:a	(Ljava/lang/Integer;)Ljava/lang/String;
    //   188: astore 9
    //   190: aload 11
    //   192: aload 9
    //   194: invokevirtual 314	java/util/regex/Pattern:matcher	(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
    //   197: invokevirtual 319	java/util/regex/Matcher:matches	()Z
    //   200: ifeq +285 -> 485
    //   203: aload 12
    //   205: aload 9
    //   207: iconst_2
    //   208: invokevirtual 323	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   211: astore 14
    //   213: aload 14
    //   215: ifnull +270 -> 485
    //   218: aload 14
    //   220: getfield 329	android/content/pm/PackageInfo:receivers	[Landroid/content/pm/ActivityInfo;
    //   223: astore 8
    //   225: aload 8
    //   227: ifnull +258 -> 485
    //   230: aload 8
    //   232: arraylength
    //   233: istore_3
    //   234: iconst_0
    //   235: istore_2
    //   236: iload_2
    //   237: iload_3
    //   238: if_icmpge +247 -> 485
    //   241: aload 8
    //   243: iload_2
    //   244: aaload
    //   245: getfield 334	android/content/pm/ActivityInfo:name	Ljava/lang/String;
    //   248: ldc_w 336
    //   251: invokevirtual 231	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   254: ifeq +224 -> 478
    //   257: aload 14
    //   259: getfield 340	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   262: getfield 344	android/content/pm/ApplicationInfo:enabled	Z
    //   265: ifeq +220 -> 485
    //   268: aload 14
    //   270: getfield 340	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   273: getfield 347	android/content/pm/ApplicationInfo:flags	I
    //   276: ldc_w 348
    //   279: iand
    //   280: ifne +205 -> 485
    //   283: invokestatic 288	com/bandwidthx/library/bk:e	()Landroid/content/Context;
    //   286: aload 9
    //   288: invokestatic 353	com/bandwidthx/library/BxService:a	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/Boolean;
    //   291: invokevirtual 159	java/lang/Boolean:booleanValue	()Z
    //   294: ifeq +111 -> 405
    //   297: new 164	java/lang/StringBuilder
    //   300: dup
    //   301: invokespecial 165	java/lang/StringBuilder:<init>	()V
    //   304: aload_1
    //   305: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   308: astore 15
    //   310: aload_1
    //   311: invokevirtual 197	java/lang/String:length	()I
    //   314: ifle +84 -> 398
    //   317: ldc_w 355
    //   320: astore 8
    //   322: aload 15
    //   324: aload 8
    //   326: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   329: aload 9
    //   331: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   334: ldc_w 357
    //   337: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   340: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   343: astore 8
    //   345: aload 8
    //   347: astore_1
    //   348: aload_1
    //   349: astore 8
    //   351: aload 6
    //   353: invokevirtual 128	java/lang/Long:longValue	()J
    //   356: aload 14
    //   358: getfield 361	android/content/pm/PackageInfo:lastUpdateTime	J
    //   361: lcmp
    //   362: ifge +126 -> 488
    //   365: aload 14
    //   367: getfield 361	android/content/pm/PackageInfo:lastUpdateTime	J
    //   370: lstore 4
    //   372: lload 4
    //   374: invokestatic 57	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   377: astore 7
    //   379: aload 9
    //   381: astore 6
    //   383: aload 7
    //   385: astore 8
    //   387: aload 6
    //   389: astore 7
    //   391: aload 8
    //   393: astore 6
    //   395: goto -244 -> 151
    //   398: ldc 49
    //   400: astore 8
    //   402: goto -80 -> 322
    //   405: new 164	java/lang/StringBuilder
    //   408: dup
    //   409: invokespecial 165	java/lang/StringBuilder:<init>	()V
    //   412: aload_1
    //   413: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   416: astore 14
    //   418: aload_1
    //   419: invokevirtual 197	java/lang/String:length	()I
    //   422: ifle +49 -> 471
    //   425: ldc_w 355
    //   428: astore 8
    //   430: aload 14
    //   432: aload 8
    //   434: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   437: aload 9
    //   439: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   442: ldc_w 363
    //   445: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   448: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   451: astore 9
    //   453: aload 6
    //   455: astore 8
    //   457: aload 9
    //   459: astore_1
    //   460: aload 7
    //   462: astore 6
    //   464: aload 8
    //   466: astore 7
    //   468: goto -85 -> 383
    //   471: ldc 49
    //   473: astore 8
    //   475: goto -45 -> 430
    //   478: iload_2
    //   479: iconst_1
    //   480: iadd
    //   481: istore_2
    //   482: goto -246 -> 236
    //   485: aload_1
    //   486: astore 8
    //   488: aload 8
    //   490: astore_1
    //   491: goto -340 -> 151
    //   494: astore 8
    //   496: aload 6
    //   498: astore 8
    //   500: aload 7
    //   502: astore 6
    //   504: aload 8
    //   506: astore 7
    //   508: goto -125 -> 383
    //   511: aload 7
    //   513: invokevirtual 197	java/lang/String:length	()I
    //   516: ifle +22 -> 538
    //   519: aload 7
    //   521: invokestatic 365	com/bandwidthx/library/aa:c	()Ljava/lang/String;
    //   524: invokevirtual 369	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   527: ifeq +81 -> 608
    //   530: aload_0
    //   531: iconst_1
    //   532: invokestatic 77	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   535: putfield 99	com/bandwidthx/library/ao:r	Ljava/lang/Boolean;
    //   538: aload 10
    //   540: aload_0
    //   541: getfield 99	com/bandwidthx/library/ao:r	Ljava/lang/Boolean;
    //   544: if_acmpeq +56 -> 600
    //   547: new 164	java/lang/StringBuilder
    //   550: dup
    //   551: invokespecial 165	java/lang/StringBuilder:<init>	()V
    //   554: ldc_w 371
    //   557: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   560: astore 8
    //   562: aload_0
    //   563: getfield 99	com/bandwidthx/library/ao:r	Ljava/lang/Boolean;
    //   566: invokevirtual 159	java/lang/Boolean:booleanValue	()Z
    //   569: ifeq +63 -> 632
    //   572: ldc_w 373
    //   575: astore 6
    //   577: aload 8
    //   579: aload 6
    //   581: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   584: ldc_w 375
    //   587: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   590: aload_1
    //   591: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   594: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   597: invokestatic 259	com/bandwidthx/library/bj:b	(Ljava/lang/String;)V
    //   600: aload_0
    //   601: getfield 99	com/bandwidthx/library/ao:r	Ljava/lang/Boolean;
    //   604: astore_1
    //   605: goto -565 -> 40
    //   608: aload_0
    //   609: iconst_0
    //   610: invokestatic 77	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   613: putfield 99	com/bandwidthx/library/ao:r	Ljava/lang/Boolean;
    //   616: goto -78 -> 538
    //   619: astore_1
    //   620: aload_1
    //   621: invokestatic 122	com/bandwidthx/library/bj:a	(Ljava/lang/Throwable;)V
    //   624: goto -24 -> 600
    //   627: astore_1
    //   628: aload_0
    //   629: monitorexit
    //   630: aload_1
    //   631: athrow
    //   632: new 164	java/lang/StringBuilder
    //   635: dup
    //   636: invokespecial 165	java/lang/StringBuilder:<init>	()V
    //   639: ldc_w 377
    //   642: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   645: aload 7
    //   647: invokevirtual 171	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   650: invokevirtual 183	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   653: astore 6
    //   655: goto -78 -> 577
    //   658: aload_0
    //   659: iconst_1
    //   660: invokestatic 77	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   663: putfield 99	com/bandwidthx/library/ao:r	Ljava/lang/Boolean;
    //   666: aload 10
    //   668: invokevirtual 159	java/lang/Boolean:booleanValue	()Z
    //   671: ifne -71 -> 600
    //   674: ldc_w 379
    //   677: invokestatic 259	com/bandwidthx/library/bj:b	(Ljava/lang/String;)V
    //   680: goto -80 -> 600
    //   683: astore 8
    //   685: goto -189 -> 496
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	688	0	this	ao
    //   0	688	1	paramBoolean	Boolean
    //   235	247	2	i1	int
    //   233	6	3	i2	int
    //   370	3	4	l1	long
    //   121	533	6	localObject1	Object
    //   125	521	7	localObject2	Object
    //   171	318	8	localObject3	Object
    //   494	1	8	localException1	Exception
    //   498	80	8	localObject4	Object
    //   683	1	8	localException2	Exception
    //   188	270	9	str	String
    //   6	661	10	localBoolean	Boolean
    //   75	116	11	localPattern	java.util.regex.Pattern
    //   133	71	12	localPackageManager	android.content.pm.PackageManager
    //   146	16	13	localIterator	Iterator
    //   211	220	14	localObject5	Object
    //   308	15	15	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   173	213	494	java/lang/Exception
    //   218	225	494	java/lang/Exception
    //   230	234	494	java/lang/Exception
    //   241	317	494	java/lang/Exception
    //   322	345	494	java/lang/Exception
    //   405	425	494	java/lang/Exception
    //   430	453	494	java/lang/Exception
    //   117	123	619	java/lang/Exception
    //   127	148	619	java/lang/Exception
    //   151	173	619	java/lang/Exception
    //   511	538	619	java/lang/Exception
    //   538	572	619	java/lang/Exception
    //   577	600	619	java/lang/Exception
    //   608	616	619	java/lang/Exception
    //   632	655	619	java/lang/Exception
    //   2	35	627	finally
    //   44	117	627	finally
    //   117	123	627	finally
    //   127	148	627	finally
    //   151	173	627	finally
    //   173	213	627	finally
    //   218	225	627	finally
    //   230	234	627	finally
    //   241	317	627	finally
    //   322	345	627	finally
    //   351	372	627	finally
    //   405	425	627	finally
    //   430	453	627	finally
    //   511	538	627	finally
    //   538	572	627	finally
    //   577	600	627	finally
    //   600	605	627	finally
    //   608	616	627	finally
    //   620	624	627	finally
    //   632	655	627	finally
    //   658	680	627	finally
    //   351	372	683	java/lang/Exception
  }
  
  public final void a(Long paramLong, Integer paramInteger)
  {
    a(paramLong, "", paramInteger, "");
  }
  
  public final void a(Long paramLong, String paramString1, Integer paramInteger, String paramString2)
  {
    if ((bk.I().a(Integer.valueOf(78)).booleanValue()) && (paramInteger.intValue() != 0))
    {
      bj.b("APP Won't suspend due to test mode");
      return;
    }
    Boolean localBoolean = a();
    this.a = paramString2;
    this.c = paramInteger;
    if (paramLong.longValue() == Long.MAX_VALUE) {}
    for (this.b = paramLong;; this.b = Long.valueOf(eb.d().longValue() + paramLong.longValue()))
    {
      this.d = paramString1;
      this.e = Long.valueOf(0L);
      this.f = this.b;
      this.g = Boolean.valueOf(false);
      this.h = Boolean.valueOf(false);
      this.i = Boolean.valueOf(false);
      this.j = Boolean.valueOf(false);
      this.k = Boolean.valueOf(false);
      this.l = Boolean.valueOf(false);
      bk.D().b("AutoWifiComment", this.a);
      bk.D().b("AutoWifiSuspendedReason", this.c.intValue());
      bk.D().b("AutoWifiSuspendedTicks", this.b.longValue());
      bk.D().b("XAutoWifiSuspendedSSID", this.d, Boolean.valueOf(true));
      bk.D().b("AutoWifiSuspendedSSIDTicks", this.f.longValue());
      bk.D().b("AutoWifiSuspendedScreenOn", this.g.booleanValue());
      bk.D().b("AutoWifiSuspendedScreenOff", this.h.booleanValue());
      bk.D().b("AutoWifiSuspendedForeground", this.i.booleanValue());
      bk.D().b("AutoWifiSuspendedRadioOn", this.j.booleanValue());
      bk.D().b("AutoWifiSuspendedRadioOff", this.k.booleanValue());
      bk.D().b("AutoWifiSuspendedConnected", this.l.booleanValue());
      bk.D().a();
      bk.l();
      ah.m();
      bk.l().j();
      if (!localBoolean.booleanValue()) {
        aw.j();
      }
      bk.H().a(Boolean.valueOf(true), null);
      if (bk.g() == null) {
        break;
      }
      bk.g().f();
      return;
    }
  }
  
  public final void a(String paramString)
  {
    Boolean localBoolean = a();
    this.a = paramString;
    this.c = Integer.valueOf(0);
    this.b = Long.valueOf(0L);
    this.d = "";
    this.e = Long.valueOf(0L);
    this.f = Long.valueOf(0L);
    this.g = Boolean.valueOf(false);
    this.h = Boolean.valueOf(false);
    this.i = Boolean.valueOf(false);
    this.j = Boolean.valueOf(false);
    this.k = Boolean.valueOf(false);
    this.l = Boolean.valueOf(false);
    bk.D().b("AutoWifiComment", this.a);
    bk.D().b("AutoWifiSuspendedReason", this.c.intValue());
    bk.D().b("AutoWifiSuspendedTicks", this.b.longValue());
    bk.D().b("XAutoWifiSuspendedSSID", this.d, Boolean.valueOf(true));
    bk.D().b("AutoWifiSuspendedSSIDTicks", this.f.longValue());
    bk.D().b("AutoWifiSuspendedScreenOn", this.g.booleanValue());
    bk.D().b("AutoWifiSuspendedScreenOff", this.h.booleanValue());
    bk.D().b("AutoWifiSuspendedForeground", this.i.booleanValue());
    bk.D().b("AutoWifiSuspendedRadioOn", this.j.booleanValue());
    bk.D().b("AutoWifiSuspendedRadioOff", this.k.booleanValue());
    bk.D().b("AutoWifiSuspendedConnected", this.l.booleanValue());
    bk.D().a();
    if (localBoolean.booleanValue()) {
      aw.i();
    }
    bk.H().a(Boolean.valueOf(true), null);
    if (bk.g() != null) {
      bk.g().a(Boolean.valueOf(false));
    }
  }
  
  public final Integer b()
  {
    if ((this.b.longValue() == 0L) && (!g().booleanValue())) {
      return Integer.valueOf(4);
    }
    return this.c;
  }
  
  public final Long c()
  {
    d();
    if (this.b.longValue() == Long.MAX_VALUE) {
      return this.b;
    }
    if (this.b.longValue() > eb.d().longValue()) {
      return Long.valueOf(this.b.longValue() - eb.d().longValue());
    }
    if (!g().booleanValue()) {
      return Long.valueOf(1L);
    }
    if ((this.d.length() > 0) && (this.b.longValue() > 0L))
    {
      if ((this.e.longValue() > 0L) && (this.e.longValue() < eb.d().longValue() - bk.I().c(Integer.valueOf(8)).longValue())) {
        this.e = Long.valueOf(0L);
      }
      if (this.e.longValue() == 0L)
      {
        this.e = eb.d();
        this.b = Long.valueOf(eb.d().longValue() + bk.I().c(Integer.valueOf(8)).longValue());
        bk.D().b("AutoWifiSuspendedTicks", this.b.longValue());
        bk.D().a();
        new AsyncTask()
        {
          private Void a()
          {
            bj.a("CheckSuspension");
            try
            {
              if (ao.a(ao.this).length() > 0)
              {
                bj.b("APP Suspension period has ended, check to see if " + ao.a(ao.this) + " is still available");
                bk.i().j();
              }
              bj.c();
              return null;
            }
            catch (Exception localException)
            {
              for (;;)
              {
                bj.a(localException, Boolean.valueOf(false));
              }
            }
          }
        }.execute(new Void[0]);
      }
      return Long.valueOf(1L);
    }
    if (this.c.intValue() == 3)
    {
      bk.u();
      if (au.c().intValue() < bk.I().b(Integer.valueOf(4)).intValue())
      {
        this.b = Long.valueOf(eb.d().longValue() + bk.I().c(Integer.valueOf(3)).longValue());
        bk.D().b("AutoWifiSuspendedTicks", this.b.longValue());
        bk.D().a();
        Object localObject = new StringBuilder().append("APP Suspend due to battery level: ");
        bk.u();
        StringBuilder localStringBuilder = ((StringBuilder)localObject).append(au.c().toString());
        bk.u();
        if (au.b().booleanValue()) {}
        for (localObject = "*";; localObject = "")
        {
          bj.b((String)localObject);
          return Long.valueOf(this.b.longValue() - eb.d().longValue());
        }
      }
    }
    return Long.valueOf(0L);
  }
  
  public final void d()
  {
    try
    {
      if (this.f.longValue() == 0L)
      {
        this.f = this.b;
        bk.D().b("AutoWifiSuspendedSSIDTicks", this.f.longValue());
        bk.D().a();
      }
      if ((this.b.longValue() > 0L) && (this.d.length() > 0))
      {
        bk.M();
        if (ef.c().booleanValue())
        {
          bk.M();
          Object localObject = this.d;
          Integer localInteger = Integer.valueOf(0);
          Iterator localIterator = bk.M().a(Boolean.valueOf(false)).iterator();
          for (;;)
          {
            if (localIterator.hasNext())
            {
              ef.a localA = (ef.a)localIterator.next();
              if ((("".length() == 0) || ("".equalsIgnoreCase(localA.a))) && ((((String)localObject).length() == 0) || (((String)localObject).equalsIgnoreCase(localA.b)))) {
                if ((localInteger.intValue() == 0) || (localA.c.intValue() > localInteger.intValue())) {
                  localObject = Boolean.valueOf(true);
                }
              }
            }
          }
          while (((Boolean)localObject).booleanValue())
          {
            this.f = eb.d();
            bk.D().b("AutoWifiSuspendedSSIDTicks", this.f.longValue());
            bk.D().a();
            return;
            localObject = Boolean.valueOf(false);
            continue;
            localObject = Boolean.valueOf(false);
          }
        }
      }
      return;
    }
    catch (Exception localException)
    {
      bj.a(localException, Boolean.valueOf(false));
    }
  }
  
  public final void e()
  {
    try
    {
      if (this.b.longValue() > 0L)
      {
        Boolean localBoolean2 = Boolean.valueOf(false);
        d();
        bk.M();
        Boolean localBoolean1;
        if ((ef.p().booleanValue()) && (this.c.intValue() == 2))
        {
          bj.b("APP Access point mode, so resume");
          localBoolean1 = Boolean.valueOf(true);
        }
        while (localBoolean1.booleanValue())
        {
          this.b = Long.valueOf(Long.MAX_VALUE);
          bk.D().b("AutoWifiSuspendedTicks", this.b.longValue());
          bk.D().a();
          a("");
          bk.i().a(Long.valueOf(0L));
          return;
          if (this.g.booleanValue())
          {
            bk.u();
            if (au.e().booleanValue())
            {
              bj.b("APP Screen is on, so resume");
              localBoolean1 = Boolean.valueOf(true);
              continue;
            }
          }
          if (this.h.booleanValue())
          {
            bk.u();
            if (au.f().booleanValue())
            {
              bj.b("APP Screen is off, so resume");
              localBoolean1 = Boolean.valueOf(true);
              continue;
            }
          }
          if ((this.i.booleanValue()) && (bk.h().k().booleanValue()))
          {
            bj.b("APP App is in foreground, so resume");
            localBoolean1 = Boolean.valueOf(true);
          }
          else
          {
            if (this.j.booleanValue())
            {
              bk.M();
              if (ef.c().booleanValue())
              {
                bj.b("APP Radio is on, so resume");
                localBoolean1 = Boolean.valueOf(true);
                continue;
              }
            }
            if (this.k.booleanValue())
            {
              bk.M();
              if (!ef.c().booleanValue())
              {
                bj.b("APP Radio is off, so resume");
                localBoolean1 = Boolean.valueOf(true);
                continue;
              }
            }
            if ((this.l.booleanValue()) && (bk.M().g().booleanValue()))
            {
              bj.b("APP Device is connected, so resume");
              localBoolean1 = Boolean.valueOf(true);
            }
            else
            {
              localBoolean1 = localBoolean2;
              if (this.d.length() > 0)
              {
                localBoolean1 = localBoolean2;
                if (this.f.longValue() < eb.d().longValue() - bk.I().c(Integer.valueOf(9)).longValue())
                {
                  bj.b("APP Hotspot " + this.d + " has not been seen for " + ax.d(bk.I().c(Integer.valueOf(9)), Boolean.valueOf(false)) + ", last seen " + ax.a(this.f) + ", so resume");
                  localBoolean1 = Boolean.valueOf(true);
                }
              }
            }
          }
        }
      }
      return;
    }
    catch (Exception localException)
    {
      bj.a(localException, Boolean.valueOf(false));
    }
  }
  
  public final void f()
  {
    this.q = Boolean.valueOf(true);
    this.m = Long.valueOf(0L);
  }
}
