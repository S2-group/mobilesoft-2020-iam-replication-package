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

public class bl
{
  private static bl jdField_do_of_type_ComDuappsAdBl;
  private static final String jdField_do_of_type_JavaLangString = "bl";
  private long jdField_do_of_type_Long;
  public Context jdField_do;
  public bg jdField_do;
  private bj jdField_do_of_type_ComDuappsAdBj = new do((byte)0);
  private bm jdField_do_of_type_ComDuappsAdBm;
  private final Object jdField_do_of_type_JavaLangObject = new Object();
  private LinkedList<ag> jdField_do_of_type_JavaUtilLinkedList = new LinkedList();
  
  private bl(Context paramContext)
  {
    this.jdField_do_of_type_AndroidContentContext = paramContext;
    this.jdField_do_of_type_ComDuappsAdBg = new bg(paramContext);
    this.jdField_do_of_type_ComDuappsAdBm = new bm(paramContext);
  }
  
  public static bl jdMethod_do(Context paramContext)
  {
    try
    {
      if (jdField_do_of_type_ComDuappsAdBl == null) {
        jdField_do_of_type_ComDuappsAdBl = new bl(paramContext.getApplicationContext());
      }
      return jdField_do_of_type_ComDuappsAdBl;
    }
    finally {}
  }
  
  public static <T extends ag> void jdMethod_do(Context paramContext, List<T> paramList)
  {
    paramContext = ck.jdMethod_do(paramContext);
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
    //   1: getfield 38	com/duapps/ad/bl:jdField_do_of_type_JavaLangObject	Ljava/lang/Object;
    //   4: astore 9
    //   6: aload 9
    //   8: monitorenter
    //   9: invokestatic 113	java/lang/System:currentTimeMillis	()J
    //   12: lstore_2
    //   13: aload_0
    //   14: getfield 115	com/duapps/ad/bl:jdField_do_of_type_Long	J
    //   17: ldc2_w 116
    //   20: ladd
    //   21: lload_2
    //   22: lcmp
    //   23: ifge +610 -> 633
    //   26: aload_0
    //   27: getfield 40	com/duapps/ad/bl:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   30: invokestatic 71	com/duapps/ad/ck:do	(Landroid/content/Context;)Lcom/duapps/ad/ck;
    //   33: astore 6
    //   35: aload 6
    //   37: getfield 118	com/duapps/ad/ck:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   40: invokestatic 124	com/duapps/ad/m:g	(Landroid/content/Context;)J
    //   43: lstore_2
    //   44: invokestatic 113	java/lang/System:currentTimeMillis	()J
    //   47: lstore 4
    //   49: iconst_0
    //   50: istore_1
    //   51: aload 6
    //   53: getfield 118	com/duapps/ad/ck:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   56: invokevirtual 128	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   59: aload 6
    //   61: getfield 118	com/duapps/ad/ck:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
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
    //   90: getfield 40	com/duapps/ad/bl:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   93: invokestatic 71	com/duapps/ad/ck:do	(Landroid/content/Context;)Lcom/duapps/ad/ck;
    //   96: astore 8
    //   98: aload_0
    //   99: getfield 40	com/duapps/ad/bl:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   102: astore 11
    //   104: new 149	java/util/ArrayList
    //   107: dup
    //   108: invokespecial 150	java/util/ArrayList:<init>	()V
    //   111: astore 10
    //   113: aconst_null
    //   114: astore 7
    //   116: aload 8
    //   118: getfield 118	com/duapps/ad/ck:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   121: invokevirtual 128	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   124: aload 8
    //   126: getfield 118	com/duapps/ad/ck:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
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
    //   285: ifle +341 -> 626
    //   288: new 149	java/util/ArrayList
    //   291: dup
    //   292: invokespecial 150	java/util/ArrayList:<init>	()V
    //   295: astore 7
    //   297: aload 11
    //   299: invokevirtual 189	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   302: astore 6
    //   304: goto +13 -> 317
    //   307: astore 6
    //   309: aload 6
    //   311: invokevirtual 192	java/lang/Exception:printStackTrace	()V
    //   314: aconst_null
    //   315: astore 6
    //   317: aload 6
    //   319: ifnull +106 -> 425
    //   322: aload 6
    //   324: sipush 256
    //   327: invokevirtual 198	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   330: astore 6
    //   332: aload 10
    //   334: invokeinterface 77 1 0
    //   339: astore 10
    //   341: aload 10
    //   343: invokeinterface 83 1 0
    //   348: ifeq +77 -> 425
    //   351: aload 10
    //   353: invokeinterface 87 1 0
    //   358: checkcast 137	java/lang/String
    //   361: astore 11
    //   363: aload 6
    //   365: invokeinterface 77 1 0
    //   370: astore 12
    //   372: aload 12
    //   374: invokeinterface 83 1 0
    //   379: ifeq -38 -> 341
    //   382: aload 12
    //   384: invokeinterface 87 1 0
    //   389: checkcast 200	android/content/pm/PackageInfo
    //   392: astore 13
    //   394: aload 13
    //   396: ifnull -24 -> 372
    //   399: aload 13
    //   401: getfield 203	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   404: aload 11
    //   406: invokevirtual 206	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   409: ifeq -37 -> 372
    //   412: aload 7
    //   414: aload 11
    //   416: invokeinterface 175 2 0
    //   421: pop
    //   422: goto -50 -> 372
    //   425: aload 7
    //   427: invokeinterface 185 1 0
    //   432: ifle +194 -> 626
    //   435: ldc -48
    //   437: astore 6
    //   439: iload_1
    //   440: aload 7
    //   442: invokeinterface 185 1 0
    //   447: if_icmpge +122 -> 569
    //   450: aload 7
    //   452: iload_1
    //   453: invokeinterface 212 2 0
    //   458: checkcast 137	java/lang/String
    //   461: astore 10
    //   463: iload_1
    //   464: ifne +54 -> 518
    //   467: new 214	java/lang/StringBuilder
    //   470: dup
    //   471: invokespecial 215	java/lang/StringBuilder:<init>	()V
    //   474: astore 11
    //   476: aload 11
    //   478: aload 6
    //   480: invokevirtual 219	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   483: pop
    //   484: aload 11
    //   486: ldc -35
    //   488: invokevirtual 219	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   491: pop
    //   492: aload 11
    //   494: aload 10
    //   496: invokevirtual 219	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   499: pop
    //   500: aload 11
    //   502: ldc -35
    //   504: invokevirtual 219	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   507: pop
    //   508: aload 11
    //   510: invokevirtual 225	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   513: astore 6
    //   515: goto +161 -> 676
    //   518: new 214	java/lang/StringBuilder
    //   521: dup
    //   522: invokespecial 215	java/lang/StringBuilder:<init>	()V
    //   525: astore 11
    //   527: aload 11
    //   529: aload 6
    //   531: invokevirtual 219	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   534: pop
    //   535: aload 11
    //   537: ldc -29
    //   539: invokevirtual 219	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   542: pop
    //   543: aload 11
    //   545: aload 10
    //   547: invokevirtual 219	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   550: pop
    //   551: aload 11
    //   553: ldc -35
    //   555: invokevirtual 219	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   558: pop
    //   559: aload 11
    //   561: invokevirtual 225	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   564: astore 6
    //   566: goto +110 -> 676
    //   569: new 214	java/lang/StringBuilder
    //   572: dup
    //   573: invokespecial 215	java/lang/StringBuilder:<init>	()V
    //   576: astore 7
    //   578: aload 7
    //   580: aload 6
    //   582: invokevirtual 219	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   585: pop
    //   586: aload 7
    //   588: ldc -27
    //   590: invokevirtual 219	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   593: pop
    //   594: aload 7
    //   596: invokevirtual 225	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   599: astore 6
    //   601: aload 8
    //   603: getfield 118	com/duapps/ad/ck:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   606: invokevirtual 128	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   609: aload 8
    //   611: getfield 118	com/duapps/ad/ck:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   614: bipush 7
    //   616: invokestatic 133	com/duapps/ad/stats/DuAdCacheProvider:do	(Landroid/content/Context;I)Landroid/net/Uri;
    //   619: aload 6
    //   621: aconst_null
    //   622: invokevirtual 147	android/content/ContentResolver:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   625: pop
    //   626: aload_0
    //   627: invokestatic 113	java/lang/System:currentTimeMillis	()J
    //   630: putfield 115	com/duapps/ad/bl:jdField_do_of_type_Long	J
    //   633: aload 9
    //   635: monitorexit
    //   636: return
    //   637: astore 6
    //   639: aload 9
    //   641: monitorexit
    //   642: aload 6
    //   644: athrow
    //   645: astore 6
    //   647: goto -558 -> 89
    //   650: astore 6
    //   652: goto +18 -> 670
    //   655: astore 7
    //   657: goto -397 -> 260
    //   660: astore 6
    //   662: goto -36 -> 626
    //   665: astore 6
    //   667: goto -432 -> 235
    //   670: aconst_null
    //   671: astore 6
    //   673: goto -413 -> 260
    //   676: iload_1
    //   677: iconst_1
    //   678: iadd
    //   679: istore_1
    //   680: goto -241 -> 439
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	683	0	this	bl
    //   50	630	1	i	int
    //   12	68	2	l1	long
    //   47	31	4	l2	long
    //   33	270	6	localObject1	Object
    //   307	3	6	localException1	Exception
    //   315	305	6	localObject2	Object
    //   637	6	6	localObject3	Object
    //   645	1	6	localException2	Exception
    //   650	1	6	localException3	Exception
    //   660	1	6	localException4	Exception
    //   665	1	6	localObject4	Object
    //   671	1	6	localObject5	Object
    //   114	481	7	localObject6	Object
    //   655	1	7	localException5	Exception
    //   96	29	8	localCk	ck
    //   197	413	8	localObject7	Object
    //   4	636	9	localObject8	Object
    //   111	435	10	localObject9	Object
    //   102	458	11	localObject10	Object
    //   370	13	12	localIterator	Iterator
    //   392	8	13	localPackageInfo	android.content.pm.PackageInfo
    // Exception table:
    //   from	to	target	type
    //   156	194	197	finally
    //   297	304	307	java/lang/Exception
    //   9	49	637	finally
    //   51	89	637	finally
    //   89	113	637	finally
    //   215	225	637	finally
    //   225	232	637	finally
    //   240	257	637	finally
    //   257	260	637	finally
    //   265	275	637	finally
    //   278	297	637	finally
    //   297	304	637	finally
    //   309	314	637	finally
    //   322	341	637	finally
    //   341	372	637	finally
    //   372	394	637	finally
    //   399	422	637	finally
    //   425	435	637	finally
    //   439	463	637	finally
    //   467	515	637	finally
    //   518	566	637	finally
    //   569	601	637	finally
    //   601	626	637	finally
    //   626	633	637	finally
    //   633	636	637	finally
    //   639	642	637	finally
    //   51	89	645	java/lang/Exception
    //   51	89	645	java/lang/Throwable
    //   116	151	650	java/lang/Exception
    //   156	194	655	java/lang/Exception
    //   601	626	660	java/lang/Exception
    //   601	626	660	java/lang/Throwable
    //   116	151	665	finally
  }
  
  public final void jdMethod_do(ag paramAg)
  {
    if (paramAg == null) {
      return;
    }
    for (;;)
    {
      synchronized (this.jdField_do_of_type_JavaLangObject)
      {
        boolean bool1 = bx.jdMethod_do(this.jdField_do_of_type_AndroidContentContext, paramAg.jdField_if);
        if (ck.jdMethod_do(this.jdField_do_of_type_AndroidContentContext).jdMethod_if(paramAg.jdField_if) == 1)
        {
          i = 1;
          boolean bool2 = cg.jdMethod_do(paramAg.d);
          if ((!TextUtils.isEmpty(paramAg.jdField_if)) && (!bool1) && (ag.jdMethod_if(paramAg)) && (i == 0) && (!bool2)) {
            if (paramAg.jdField_do == 0) {
              this.jdField_do_of_type_ComDuappsAdBg.jdMethod_do(paramAg, paramAg.d, this.jdField_do_of_type_ComDuappsAdBj);
            } else if (paramAg.jdField_do == 1) {
              synchronized (this.jdField_do_of_type_JavaUtilLinkedList)
              {
                if (this.jdField_do_of_type_JavaUtilLinkedList.isEmpty()) {
                  this.jdField_do_of_type_ComDuappsAdBm.jdMethod_do(paramAg, paramAg.d, this.jdField_do_of_type_ComDuappsAdBj);
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
    implements bj
  {
    private do() {}
    
    public final void a(ag paramAg, bi paramBi)
    {
      if (paramAg != null)
      {
        if (paramBi == null) {
          return;
        }
        Intent localIntent = new Intent("action_notify_preparse_cache_result");
        localIntent.putExtra("ad_id", paramAg.jdField_do_of_type_Long);
        localIntent.putExtra("ad_pkgname", paramAg.jdField_if_of_type_JavaLangString);
        localIntent.putExtra("parse_result_type", paramBi.jdField_do_of_type_Int);
        LocalBroadcastManager.getInstance(bl.jdMethod_do(bl.this)).sendBroadcast(localIntent);
        return;
      }
    }
    
    public final void jdMethod_do(ag paramAg, bi paramBi)
    {
      Context localContext = bl.jdMethod_do(bl.this);
      int i = paramBi.jdField_do_of_type_Int;
      int j = paramBi.jdField_if_of_type_Int;
      long l = paramBi.jdField_do_of_type_Long;
      if (1 <= m.jdMethod_do(localContext)) {}
      try
      {
        ToolStatsCore.getInstance(localContext).reportEvent("native", new JSONStringer().object().key("key").value("tts").key("id").value(paramAg.jdField_do_of_type_Long).key("logid").value(paramAg.p).key("sid").value(paramAg.m).key("ptype").value(i).key("loop").value(j).key("tsi").value(l).key("ts").value(System.currentTimeMillis()).key("tts_t").value(paramAg.jdField_if_of_type_Int).endObject().toString(), 1);
        return;
      }
      catch (JSONException paramAg) {}
      return;
    }
    
    public final void jdMethod_if(ag paramAg, bi arg2)
    {
      ck localCk = ck.jdMethod_do(bl.jdMethod_do(bl.this));
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("_url", ???.jdField_do_of_type_JavaLangString);
      localContentValues.put("pkg", ???.jdField_if_of_type_JavaLangString);
      localContentValues.put("p_url", ???.a);
      localContentValues.put("type", Integer.valueOf(???.jdField_do_of_type_Int));
      localContentValues.put("ts", Long.valueOf(???.jdField_if_of_type_Long));
      ??? = ???.jdField_do_of_type_JavaLangString;
      try
      {
        if (localCk.jdField_do.getContentResolver().update(DuAdCacheProvider.jdMethod_do(localCk.jdField_do, 7), localContentValues, "_url = ?", new String[] { ??? }) <= 0) {
          localCk.jdField_do.getContentResolver().insert(DuAdCacheProvider.jdMethod_do(localCk.jdField_do, 7), localContentValues);
        }
        synchronized (bl.jdMethod_do(bl.this))
        {
          if (!bl.jdMethod_do(bl.this).isEmpty())
          {
            bl.jdMethod_do(bl.this).remove(paramAg);
            if (!bl.jdMethod_do(bl.this).isEmpty())
            {
              paramAg = (ag)bl.jdMethod_do(bl.this).peek();
              if (paramAg.jdField_do_of_type_Int == 1) {
                bl.jdMethod_do(bl.this).jdMethod_do(paramAg, paramAg.d, bl.jdMethod_do(bl.this));
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
