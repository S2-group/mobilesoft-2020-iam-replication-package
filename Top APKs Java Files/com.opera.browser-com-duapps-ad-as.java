package com.duapps.ad;

import android.content.Context;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class as
{
  private static final String c = "as";
  private static as d;
  public Context a;
  public al b;
  private au e;
  private LinkedList<aa> f = new LinkedList();
  private ap g = new at(this, (byte)0);
  private Object h = new Object();
  private long i;
  
  private as(Context paramContext)
  {
    this.a = paramContext;
    this.b = new al(paramContext);
    this.e = new au(paramContext);
  }
  
  public static as a(Context paramContext)
  {
    try
    {
      if (d == null) {
        d = new as(paramContext.getApplicationContext());
      }
      return d;
    }
    finally {}
  }
  
  /* Error */
  public final void a()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 46	com/duapps/ad/as:h	Ljava/lang/Object;
    //   4: astore 9
    //   6: aload 9
    //   8: monitorenter
    //   9: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   12: lstore_2
    //   13: aload_0
    //   14: getfield 84	com/duapps/ad/as:i	J
    //   17: ldc2_w 85
    //   20: ladd
    //   21: lload_2
    //   22: lcmp
    //   23: ifge +597 -> 620
    //   26: aload_0
    //   27: getfield 48	com/duapps/ad/as:a	Landroid/content/Context;
    //   30: invokestatic 91	com/duapps/ad/ci:a	(Landroid/content/Context;)Lcom/duapps/ad/ci;
    //   33: astore 6
    //   35: aload 6
    //   37: getfield 93	com/duapps/ad/ci:b	Landroid/content/Context;
    //   40: invokestatic 99	com/duapps/ad/dg:A	(Landroid/content/Context;)J
    //   43: lstore_2
    //   44: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   47: lstore 4
    //   49: iconst_0
    //   50: istore_1
    //   51: aload 6
    //   53: getfield 93	com/duapps/ad/ci:b	Landroid/content/Context;
    //   56: invokevirtual 103	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   59: aload 6
    //   61: getfield 93	com/duapps/ad/ci:b	Landroid/content/Context;
    //   64: bipush 7
    //   66: invokestatic 108	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   69: ldc 110
    //   71: iconst_1
    //   72: anewarray 112	java/lang/String
    //   75: dup
    //   76: iconst_0
    //   77: lload 4
    //   79: lload_2
    //   80: lsub
    //   81: invokestatic 116	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   84: aastore
    //   85: invokevirtual 122	android/content/ContentResolver:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   88: pop
    //   89: aload_0
    //   90: getfield 48	com/duapps/ad/as:a	Landroid/content/Context;
    //   93: invokestatic 91	com/duapps/ad/ci:a	(Landroid/content/Context;)Lcom/duapps/ad/ci;
    //   96: astore 8
    //   98: aload_0
    //   99: getfield 48	com/duapps/ad/as:a	Landroid/content/Context;
    //   102: astore 11
    //   104: new 124	java/util/ArrayList
    //   107: dup
    //   108: invokespecial 125	java/util/ArrayList:<init>	()V
    //   111: astore 10
    //   113: aconst_null
    //   114: astore 7
    //   116: aload 8
    //   118: getfield 93	com/duapps/ad/ci:b	Landroid/content/Context;
    //   121: invokevirtual 103	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   124: aload 8
    //   126: getfield 93	com/duapps/ad/ci:b	Landroid/content/Context;
    //   129: bipush 7
    //   131: invokestatic 108	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   134: iconst_1
    //   135: anewarray 112	java/lang/String
    //   138: dup
    //   139: iconst_0
    //   140: ldc 127
    //   142: aastore
    //   143: aconst_null
    //   144: aconst_null
    //   145: aconst_null
    //   146: invokevirtual 131	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   149: astore 6
    //   151: aload 6
    //   153: ifnull +57 -> 210
    //   156: aload 6
    //   158: invokeinterface 137 1 0
    //   163: ifeq +47 -> 210
    //   166: aload 6
    //   168: iconst_0
    //   169: invokeinterface 141 2 0
    //   174: astore 7
    //   176: aload 7
    //   178: invokestatic 147	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   181: ifne +29 -> 210
    //   184: aload 10
    //   186: aload 7
    //   188: invokeinterface 153 2 0
    //   193: pop
    //   194: goto +16 -> 210
    //   197: astore 8
    //   199: aload 6
    //   201: astore 7
    //   203: aload 8
    //   205: astore 6
    //   207: goto +28 -> 235
    //   210: aload 6
    //   212: ifnull +66 -> 278
    //   215: aload 6
    //   217: invokeinterface 156 1 0
    //   222: ifne +56 -> 278
    //   225: aload 6
    //   227: invokeinterface 159 1 0
    //   232: goto +46 -> 278
    //   235: aload 7
    //   237: ifnull +20 -> 257
    //   240: aload 7
    //   242: invokeinterface 156 1 0
    //   247: ifne +10 -> 257
    //   250: aload 7
    //   252: invokeinterface 159 1 0
    //   257: aload 6
    //   259: athrow
    //   260: aload 6
    //   262: ifnull +16 -> 278
    //   265: aload 6
    //   267: invokeinterface 156 1 0
    //   272: ifne +6 -> 278
    //   275: goto -50 -> 225
    //   278: aload 10
    //   280: invokeinterface 163 1 0
    //   285: ifle +328 -> 613
    //   288: new 124	java/util/ArrayList
    //   291: dup
    //   292: invokespecial 125	java/util/ArrayList:<init>	()V
    //   295: astore 7
    //   297: aload 11
    //   299: invokevirtual 167	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   302: astore 6
    //   304: aload 6
    //   306: ifnull +106 -> 412
    //   309: aload 6
    //   311: sipush 256
    //   314: invokevirtual 173	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   317: astore 6
    //   319: aload 10
    //   321: invokeinterface 177 1 0
    //   326: astore 10
    //   328: aload 10
    //   330: invokeinterface 182 1 0
    //   335: ifeq +77 -> 412
    //   338: aload 10
    //   340: invokeinterface 186 1 0
    //   345: checkcast 112	java/lang/String
    //   348: astore 11
    //   350: aload 6
    //   352: invokeinterface 177 1 0
    //   357: astore 12
    //   359: aload 12
    //   361: invokeinterface 182 1 0
    //   366: ifeq -38 -> 328
    //   369: aload 12
    //   371: invokeinterface 186 1 0
    //   376: checkcast 188	android/content/pm/PackageInfo
    //   379: astore 13
    //   381: aload 13
    //   383: ifnull -24 -> 359
    //   386: aload 13
    //   388: getfield 191	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   391: aload 11
    //   393: invokevirtual 194	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   396: ifeq -37 -> 359
    //   399: aload 7
    //   401: aload 11
    //   403: invokeinterface 153 2 0
    //   408: pop
    //   409: goto -50 -> 359
    //   412: aload 7
    //   414: invokeinterface 163 1 0
    //   419: ifle +194 -> 613
    //   422: ldc -60
    //   424: astore 6
    //   426: iload_1
    //   427: aload 7
    //   429: invokeinterface 163 1 0
    //   434: if_icmpge +122 -> 556
    //   437: aload 7
    //   439: iload_1
    //   440: invokeinterface 200 2 0
    //   445: checkcast 112	java/lang/String
    //   448: astore 10
    //   450: iload_1
    //   451: ifne +54 -> 505
    //   454: new 202	java/lang/StringBuilder
    //   457: dup
    //   458: invokespecial 203	java/lang/StringBuilder:<init>	()V
    //   461: astore 11
    //   463: aload 11
    //   465: aload 6
    //   467: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   470: pop
    //   471: aload 11
    //   473: ldc -47
    //   475: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   478: pop
    //   479: aload 11
    //   481: aload 10
    //   483: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   486: pop
    //   487: aload 11
    //   489: ldc -47
    //   491: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   494: pop
    //   495: aload 11
    //   497: invokevirtual 213	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   500: astore 6
    //   502: goto +161 -> 663
    //   505: new 202	java/lang/StringBuilder
    //   508: dup
    //   509: invokespecial 203	java/lang/StringBuilder:<init>	()V
    //   512: astore 11
    //   514: aload 11
    //   516: aload 6
    //   518: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   521: pop
    //   522: aload 11
    //   524: ldc -41
    //   526: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   529: pop
    //   530: aload 11
    //   532: aload 10
    //   534: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   537: pop
    //   538: aload 11
    //   540: ldc -47
    //   542: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   545: pop
    //   546: aload 11
    //   548: invokevirtual 213	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   551: astore 6
    //   553: goto +110 -> 663
    //   556: new 202	java/lang/StringBuilder
    //   559: dup
    //   560: invokespecial 203	java/lang/StringBuilder:<init>	()V
    //   563: astore 7
    //   565: aload 7
    //   567: aload 6
    //   569: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   572: pop
    //   573: aload 7
    //   575: ldc -39
    //   577: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   580: pop
    //   581: aload 7
    //   583: invokevirtual 213	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   586: astore 6
    //   588: aload 8
    //   590: getfield 93	com/duapps/ad/ci:b	Landroid/content/Context;
    //   593: invokevirtual 103	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   596: aload 8
    //   598: getfield 93	com/duapps/ad/ci:b	Landroid/content/Context;
    //   601: bipush 7
    //   603: invokestatic 108	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   606: aload 6
    //   608: aconst_null
    //   609: invokevirtual 122	android/content/ContentResolver:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   612: pop
    //   613: aload_0
    //   614: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   617: putfield 84	com/duapps/ad/as:i	J
    //   620: aload 9
    //   622: monitorexit
    //   623: return
    //   624: astore 6
    //   626: aload 9
    //   628: monitorexit
    //   629: aload 6
    //   631: athrow
    //   632: astore 6
    //   634: goto -545 -> 89
    //   637: astore 6
    //   639: goto +18 -> 657
    //   642: astore 7
    //   644: goto -384 -> 260
    //   647: astore 6
    //   649: goto -36 -> 613
    //   652: astore 6
    //   654: goto -419 -> 235
    //   657: aconst_null
    //   658: astore 6
    //   660: goto -400 -> 260
    //   663: iload_1
    //   664: iconst_1
    //   665: iadd
    //   666: istore_1
    //   667: goto -241 -> 426
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	670	0	this	as
    //   50	617	1	j	int
    //   12	68	2	l1	long
    //   47	31	4	l2	long
    //   33	574	6	localObject1	Object
    //   624	6	6	localObject2	Object
    //   632	1	6	localException1	Exception
    //   637	1	6	localException2	Exception
    //   647	1	6	localException3	Exception
    //   652	1	6	localObject3	Object
    //   658	1	6	localObject4	Object
    //   114	468	7	localObject5	Object
    //   642	1	7	localException4	Exception
    //   96	29	8	localCi	ci
    //   197	400	8	localObject6	Object
    //   4	623	9	localObject7	Object
    //   111	422	10	localObject8	Object
    //   102	445	11	localObject9	Object
    //   357	13	12	localIterator	Iterator
    //   379	8	13	localPackageInfo	android.content.pm.PackageInfo
    // Exception table:
    //   from	to	target	type
    //   156	194	197	finally
    //   9	49	624	finally
    //   51	89	624	finally
    //   89	113	624	finally
    //   215	225	624	finally
    //   225	232	624	finally
    //   240	257	624	finally
    //   257	260	624	finally
    //   265	275	624	finally
    //   278	304	624	finally
    //   309	328	624	finally
    //   328	359	624	finally
    //   359	381	624	finally
    //   386	409	624	finally
    //   412	422	624	finally
    //   426	450	624	finally
    //   454	502	624	finally
    //   505	553	624	finally
    //   556	588	624	finally
    //   588	613	624	finally
    //   613	620	624	finally
    //   620	623	624	finally
    //   626	629	624	finally
    //   51	89	632	java/lang/Exception
    //   51	89	632	java/lang/Throwable
    //   116	151	637	java/lang/Exception
    //   156	194	642	java/lang/Exception
    //   588	613	647	java/lang/Exception
    //   588	613	647	java/lang/Throwable
    //   116	151	652	finally
  }
  
  public final void a(aa paramAa)
  {
    for (;;)
    {
      synchronized (this.h)
      {
        boolean bool1 = bs.a(this.a, paramAa.e);
        if (ci.a(this.a).c(paramAa.e) == 1)
        {
          j = 1;
          boolean bool2 = cc.a(paramAa.j);
          if ((paramAa != null) && (!TextUtils.isEmpty(paramAa.e)) && (!bool1) && (aa.b(paramAa)) && (j == 0) && (!bool2)) {
            if (paramAa.a == 0) {
              this.b.a(paramAa, paramAa.j, this.g);
            } else if (paramAa.a == 1) {
              synchronized (this.f)
              {
                if (this.f.isEmpty()) {
                  this.e.a(paramAa, paramAa.j, this.g);
                }
                this.f.add(paramAa);
              }
            }
          }
          return;
        }
      }
      int j = 0;
    }
  }
  
  public final void a(List<aa> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      a((aa)paramList.next());
    }
  }
}
