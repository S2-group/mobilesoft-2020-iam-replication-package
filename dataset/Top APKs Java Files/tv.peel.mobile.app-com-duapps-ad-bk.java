package com.duapps.ad;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.duapps.ad.stats.DuAdCacheProvider;
import com.duapps.ad.stats.ToolStatsCore;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONStringer;

public class bk
{
  private static bk jdField_do_of_type_ComDuappsAdBk;
  private static final String jdField_do_of_type_JavaLangString = "bk";
  private long jdField_do_of_type_Long;
  public Context jdField_do;
  public bf jdField_do;
  private bi jdField_do_of_type_ComDuappsAdBi = new do((byte)0);
  private bl jdField_do_of_type_ComDuappsAdBl;
  private Object jdField_do_of_type_JavaLangObject = new Object();
  private LinkedList<ag> jdField_do_of_type_JavaUtilLinkedList = new LinkedList();
  
  private bk(Context paramContext)
  {
    this.jdField_do_of_type_AndroidContentContext = paramContext;
    this.jdField_do_of_type_ComDuappsAdBf = new bf(paramContext);
    this.jdField_do_of_type_ComDuappsAdBl = new bl(paramContext);
  }
  
  public static bk jdMethod_do(Context paramContext)
  {
    try
    {
      if (jdField_do_of_type_ComDuappsAdBk == null) {
        jdField_do_of_type_ComDuappsAdBk = new bk(paramContext.getApplicationContext());
      }
      return jdField_do_of_type_ComDuappsAdBk;
    }
    finally {}
  }
  
  public static <T extends ag> void jdMethod_do(Context paramContext, List<T> paramList)
  {
    paramContext = eg.jdMethod_do(paramContext);
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      ag localAg = (ag)paramList.next();
      if ((ag.jdMethod_if(localAg)) && (paramContext.jdMethod_if(localAg.jdField_if) == 2)) {
        paramList.remove();
      }
    }
  }
  
  /* Error */
  public final void jdMethod_do()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 38	com/duapps/ad/bk:jdField_do_of_type_JavaLangObject	Ljava/lang/Object;
    //   4: astore 9
    //   6: aload 9
    //   8: monitorenter
    //   9: invokestatic 113	java/lang/System:currentTimeMillis	()J
    //   12: lstore_2
    //   13: aload_0
    //   14: getfield 115	com/duapps/ad/bk:jdField_do_of_type_Long	J
    //   17: ldc2_w 116
    //   20: ladd
    //   21: lload_2
    //   22: lcmp
    //   23: ifge +597 -> 620
    //   26: aload_0
    //   27: getfield 40	com/duapps/ad/bk:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   30: invokestatic 71	com/duapps/ad/eg:do	(Landroid/content/Context;)Lcom/duapps/ad/eg;
    //   33: astore 6
    //   35: aload 6
    //   37: getfield 118	com/duapps/ad/eg:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   40: invokestatic 124	com/duapps/ad/o:g	(Landroid/content/Context;)J
    //   43: lstore_2
    //   44: invokestatic 113	java/lang/System:currentTimeMillis	()J
    //   47: lstore 4
    //   49: iconst_0
    //   50: istore_1
    //   51: aload 6
    //   53: getfield 118	com/duapps/ad/eg:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   56: invokevirtual 128	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   59: aload 6
    //   61: getfield 118	com/duapps/ad/eg:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   64: bipush 7
    //   66: invokestatic 133	com/duapps/ad/stats/DuAdCacheProvider:do	(Landroid/content/Context;I)Landroid/net/Uri;
    //   69: ldc -121
    //   71: iconst_1
    //   72: anewarray 137	java/lang/String
    //   75: dup
    //   76: iconst_0
    //   77: lload 4
    //   79: lload_2
    //   80: lsub
    //   81: invokestatic 141	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   84: aastore
    //   85: invokevirtual 147	android/content/ContentResolver:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   88: pop
    //   89: aload_0
    //   90: getfield 40	com/duapps/ad/bk:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   93: invokestatic 71	com/duapps/ad/eg:do	(Landroid/content/Context;)Lcom/duapps/ad/eg;
    //   96: astore 8
    //   98: aload_0
    //   99: getfield 40	com/duapps/ad/bk:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   102: astore 11
    //   104: new 149	java/util/ArrayList
    //   107: dup
    //   108: invokespecial 150	java/util/ArrayList:<init>	()V
    //   111: astore 10
    //   113: aconst_null
    //   114: astore 7
    //   116: aload 8
    //   118: getfield 118	com/duapps/ad/eg:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   121: invokevirtual 128	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   124: aload 8
    //   126: getfield 118	com/duapps/ad/eg:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   129: bipush 7
    //   131: invokestatic 133	com/duapps/ad/stats/DuAdCacheProvider:do	(Landroid/content/Context;I)Landroid/net/Uri;
    //   134: iconst_1
    //   135: anewarray 137	java/lang/String
    //   138: dup
    //   139: iconst_0
    //   140: ldc -104
    //   142: aastore
    //   143: aconst_null
    //   144: aconst_null
    //   145: aconst_null
    //   146: invokevirtual 156	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   149: astore 6
    //   151: aload 6
    //   153: ifnull +57 -> 210
    //   156: aload 6
    //   158: invokeinterface 161 1 0
    //   163: ifeq +47 -> 210
    //   166: aload 6
    //   168: iconst_0
    //   169: invokeinterface 165 2 0
    //   174: astore 7
    //   176: aload 7
    //   178: invokestatic 171	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   181: ifne +29 -> 210
    //   184: aload 10
    //   186: aload 7
    //   188: invokeinterface 175 2 0
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
    //   217: invokeinterface 178 1 0
    //   222: ifne +56 -> 278
    //   225: aload 6
    //   227: invokeinterface 181 1 0
    //   232: goto +46 -> 278
    //   235: aload 7
    //   237: ifnull +20 -> 257
    //   240: aload 7
    //   242: invokeinterface 178 1 0
    //   247: ifne +10 -> 257
    //   250: aload 7
    //   252: invokeinterface 181 1 0
    //   257: aload 6
    //   259: athrow
    //   260: aload 6
    //   262: ifnull +16 -> 278
    //   265: aload 6
    //   267: invokeinterface 178 1 0
    //   272: ifne +6 -> 278
    //   275: goto -50 -> 225
    //   278: aload 10
    //   280: invokeinterface 185 1 0
    //   285: ifle +328 -> 613
    //   288: new 149	java/util/ArrayList
    //   291: dup
    //   292: invokespecial 150	java/util/ArrayList:<init>	()V
    //   295: astore 7
    //   297: aload 11
    //   299: invokevirtual 189	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   302: astore 6
    //   304: aload 6
    //   306: ifnull +106 -> 412
    //   309: aload 6
    //   311: sipush 256
    //   314: invokevirtual 195	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   317: astore 6
    //   319: aload 10
    //   321: invokeinterface 77 1 0
    //   326: astore 10
    //   328: aload 10
    //   330: invokeinterface 83 1 0
    //   335: ifeq +77 -> 412
    //   338: aload 10
    //   340: invokeinterface 87 1 0
    //   345: checkcast 137	java/lang/String
    //   348: astore 11
    //   350: aload 6
    //   352: invokeinterface 77 1 0
    //   357: astore 12
    //   359: aload 12
    //   361: invokeinterface 83 1 0
    //   366: ifeq -38 -> 328
    //   369: aload 12
    //   371: invokeinterface 87 1 0
    //   376: checkcast 197	android/content/pm/PackageInfo
    //   379: astore 13
    //   381: aload 13
    //   383: ifnull -24 -> 359
    //   386: aload 13
    //   388: getfield 200	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   391: aload 11
    //   393: invokevirtual 203	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   396: ifeq -37 -> 359
    //   399: aload 7
    //   401: aload 11
    //   403: invokeinterface 175 2 0
    //   408: pop
    //   409: goto -50 -> 359
    //   412: aload 7
    //   414: invokeinterface 185 1 0
    //   419: ifle +194 -> 613
    //   422: ldc -51
    //   424: astore 6
    //   426: iload_1
    //   427: aload 7
    //   429: invokeinterface 185 1 0
    //   434: if_icmpge +122 -> 556
    //   437: aload 7
    //   439: iload_1
    //   440: invokeinterface 209 2 0
    //   445: checkcast 137	java/lang/String
    //   448: astore 10
    //   450: iload_1
    //   451: ifne +54 -> 505
    //   454: new 211	java/lang/StringBuilder
    //   457: dup
    //   458: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   461: astore 11
    //   463: aload 11
    //   465: aload 6
    //   467: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   470: pop
    //   471: aload 11
    //   473: ldc -38
    //   475: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   478: pop
    //   479: aload 11
    //   481: aload 10
    //   483: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   486: pop
    //   487: aload 11
    //   489: ldc -38
    //   491: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   494: pop
    //   495: aload 11
    //   497: invokevirtual 222	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   500: astore 6
    //   502: goto +161 -> 663
    //   505: new 211	java/lang/StringBuilder
    //   508: dup
    //   509: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   512: astore 11
    //   514: aload 11
    //   516: aload 6
    //   518: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   521: pop
    //   522: aload 11
    //   524: ldc -32
    //   526: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   529: pop
    //   530: aload 11
    //   532: aload 10
    //   534: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   537: pop
    //   538: aload 11
    //   540: ldc -38
    //   542: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   545: pop
    //   546: aload 11
    //   548: invokevirtual 222	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   551: astore 6
    //   553: goto +110 -> 663
    //   556: new 211	java/lang/StringBuilder
    //   559: dup
    //   560: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   563: astore 7
    //   565: aload 7
    //   567: aload 6
    //   569: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   572: pop
    //   573: aload 7
    //   575: ldc -30
    //   577: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   580: pop
    //   581: aload 7
    //   583: invokevirtual 222	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   586: astore 6
    //   588: aload 8
    //   590: getfield 118	com/duapps/ad/eg:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   593: invokevirtual 128	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   596: aload 8
    //   598: getfield 118	com/duapps/ad/eg:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   601: bipush 7
    //   603: invokestatic 133	com/duapps/ad/stats/DuAdCacheProvider:do	(Landroid/content/Context;I)Landroid/net/Uri;
    //   606: aload 6
    //   608: aconst_null
    //   609: invokevirtual 147	android/content/ContentResolver:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   612: pop
    //   613: aload_0
    //   614: invokestatic 113	java/lang/System:currentTimeMillis	()J
    //   617: putfield 115	com/duapps/ad/bk:jdField_do_of_type_Long	J
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
    //   0	670	0	this	bk
    //   50	617	1	i	int
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
    //   96	29	8	localEg	eg
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
  
  public final void jdMethod_do(ag paramAg)
  {
    for (;;)
    {
      synchronized (this.jdField_do_of_type_JavaLangObject)
      {
        boolean bool1 = bw.jdMethod_do(this.jdField_do_of_type_AndroidContentContext, paramAg.jdField_if);
        if (eg.jdMethod_do(this.jdField_do_of_type_AndroidContentContext).jdMethod_if(paramAg.jdField_if) == 1)
        {
          i = 1;
          boolean bool2 = ec.jdMethod_do(paramAg.d);
          if ((paramAg != null) && (!TextUtils.isEmpty(paramAg.jdField_if)) && (!bool1) && (ag.jdMethod_if(paramAg)) && (i == 0) && (!bool2)) {
            if (paramAg.jdField_do == 0) {
              this.jdField_do_of_type_ComDuappsAdBf.jdMethod_do(paramAg, paramAg.d, this.jdField_do_of_type_ComDuappsAdBi);
            } else if (paramAg.jdField_do == 1) {
              synchronized (this.jdField_do_of_type_JavaUtilLinkedList)
              {
                if (this.jdField_do_of_type_JavaUtilLinkedList.isEmpty()) {
                  this.jdField_do_of_type_ComDuappsAdBl.jdMethod_do(paramAg, paramAg.d, this.jdField_do_of_type_ComDuappsAdBi);
                }
                this.jdField_do_of_type_JavaUtilLinkedList.add(paramAg);
              }
            }
          }
          return;
        }
      }
      int i = 0;
    }
  }
  
  public final void jdMethod_do(List<ag> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext()) {
      jdMethod_do((ag)paramList.next());
    }
  }
  
  final class do
    implements bi
  {
    private do() {}
    
    public final void a(ag paramAg, bh paramBh)
    {
      if (paramAg != null)
      {
        if (paramBh == null) {
          return;
        }
        Intent localIntent = new Intent("action_notify_preparse_cache_result");
        localIntent.putExtra("ad_id", paramAg.jdField_do_of_type_Long);
        localIntent.putExtra("ad_pkgname", paramAg.jdField_if_of_type_JavaLangString);
        localIntent.putExtra("parse_result_type", paramBh.jdField_do_of_type_Int);
        LocalBroadcastManager.getInstance(bk.jdMethod_do(bk.this)).sendBroadcast(localIntent);
        return;
      }
    }
    
    public final void jdMethod_do(ag paramAg, bh paramBh)
    {
      Context localContext = bk.jdMethod_do(bk.this);
      int i = paramBh.jdField_do_of_type_Int;
      int j = paramBh.jdField_if_of_type_Int;
      long l = paramBh.jdField_do_of_type_Long;
      if (1 <= o.jdMethod_do(localContext)) {}
      try
      {
        ToolStatsCore.getInstance(localContext).reportEvent("native", new JSONStringer().object().key("key").value("tts").key("id").value(paramAg.jdField_do_of_type_Long).key("logid").value(paramAg.k).key("sid").value(paramAg.i).key("ptype").value(i).key("loop").value(j).key("tsi").value(l).key("ts").value(System.currentTimeMillis()).key("tts_t").value(paramAg.jdField_if_of_type_Int).endObject().toString(), 1);
        return;
      }
      catch (JSONException paramAg) {}
      return;
    }
    
    public final void jdMethod_if(ag paramAg, bh arg2)
    {
      eg localEg = eg.jdMethod_do(bk.jdMethod_do(bk.this));
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("_url", ???.jdField_do_of_type_JavaLangString);
      localContentValues.put("pkg", ???.jdField_if_of_type_JavaLangString);
      localContentValues.put("p_url", ???.a);
      localContentValues.put("type", Integer.valueOf(???.jdField_do_of_type_Int));
      localContentValues.put("ts", Long.valueOf(???.jdField_if_of_type_Long));
      ??? = ???.jdField_do_of_type_JavaLangString;
      try
      {
        if (localEg.jdField_do.getContentResolver().update(DuAdCacheProvider.jdMethod_do(localEg.jdField_do, 7), localContentValues, "_url = ?", new String[] { ??? }) <= 0) {
          localEg.jdField_do.getContentResolver().insert(DuAdCacheProvider.jdMethod_do(localEg.jdField_do, 7), localContentValues);
        }
        synchronized (bk.jdMethod_do(bk.this))
        {
          if (!bk.jdMethod_do(bk.this).isEmpty())
          {
            bk.jdMethod_do(bk.this).remove(paramAg);
            if (!bk.jdMethod_do(bk.this).isEmpty())
            {
              paramAg = (ag)bk.jdMethod_do(bk.this).peek();
              if (paramAg.jdField_do_of_type_Int == 1) {
                bk.jdMethod_do(bk.this).jdMethod_do(paramAg, paramAg.d, bk.jdMethod_do(bk.this));
              }
            }
          }
          return;
        }
      }
      catch (Exception ???)
      {
        for (;;) {}
      }
    }
  }
}
