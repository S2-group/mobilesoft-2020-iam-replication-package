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
  private static final String jdField_do_of_type_JavaLangString = bk.class.getSimpleName();
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
    //   0: aconst_null
    //   1: astore 7
    //   3: aload_0
    //   4: getfield 44	com/duapps/ad/bk:jdField_do_of_type_JavaLangObject	Ljava/lang/Object;
    //   7: astore 9
    //   9: aload 9
    //   11: monitorenter
    //   12: invokestatic 119	java/lang/System:currentTimeMillis	()J
    //   15: lstore_2
    //   16: aload_0
    //   17: getfield 121	com/duapps/ad/bk:jdField_do_of_type_Long	J
    //   20: ldc2_w 122
    //   23: ladd
    //   24: lload_2
    //   25: lcmp
    //   26: ifge +553 -> 579
    //   29: aload_0
    //   30: getfield 46	com/duapps/ad/bk:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   33: invokestatic 77	com/duapps/ad/eg:do	(Landroid/content/Context;)Lcom/duapps/ad/eg;
    //   36: astore 6
    //   38: aload 6
    //   40: getfield 124	com/duapps/ad/eg:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   43: invokestatic 130	com/duapps/ad/o:g	(Landroid/content/Context;)J
    //   46: lstore_2
    //   47: invokestatic 119	java/lang/System:currentTimeMillis	()J
    //   50: lstore 4
    //   52: aload 6
    //   54: getfield 124	com/duapps/ad/eg:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   57: invokevirtual 134	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   60: aload 6
    //   62: getfield 124	com/duapps/ad/eg:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   65: bipush 7
    //   67: invokestatic 139	com/duapps/ad/stats/DuAdCacheProvider:do	(Landroid/content/Context;I)Landroid/net/Uri;
    //   70: ldc -115
    //   72: iconst_1
    //   73: anewarray 143	java/lang/String
    //   76: dup
    //   77: iconst_0
    //   78: lload 4
    //   80: lload_2
    //   81: lsub
    //   82: invokestatic 147	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   85: aastore
    //   86: invokevirtual 153	android/content/ContentResolver:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   89: pop
    //   90: aload_0
    //   91: getfield 46	com/duapps/ad/bk:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   94: invokestatic 77	com/duapps/ad/eg:do	(Landroid/content/Context;)Lcom/duapps/ad/eg;
    //   97: astore 8
    //   99: aload_0
    //   100: getfield 46	com/duapps/ad/bk:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   103: astore 11
    //   105: new 155	java/util/ArrayList
    //   108: dup
    //   109: invokespecial 156	java/util/ArrayList:<init>	()V
    //   112: astore 10
    //   114: aload 8
    //   116: getfield 124	com/duapps/ad/eg:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   119: invokevirtual 134	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   122: aload 8
    //   124: getfield 124	com/duapps/ad/eg:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   127: bipush 7
    //   129: invokestatic 139	com/duapps/ad/stats/DuAdCacheProvider:do	(Landroid/content/Context;I)Landroid/net/Uri;
    //   132: iconst_1
    //   133: anewarray 143	java/lang/String
    //   136: dup
    //   137: iconst_0
    //   138: ldc -98
    //   140: aastore
    //   141: aconst_null
    //   142: aconst_null
    //   143: aconst_null
    //   144: invokevirtual 162	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   147: astore 6
    //   149: aload 6
    //   151: ifnull +41 -> 192
    //   154: aload 6
    //   156: invokeinterface 167 1 0
    //   161: ifeq +31 -> 192
    //   164: aload 6
    //   166: iconst_0
    //   167: invokeinterface 171 2 0
    //   172: astore 7
    //   174: aload 7
    //   176: invokestatic 177	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   179: ifne +13 -> 192
    //   182: aload 10
    //   184: aload 7
    //   186: invokeinterface 181 2 0
    //   191: pop
    //   192: aload 6
    //   194: ifnull +20 -> 214
    //   197: aload 6
    //   199: invokeinterface 184 1 0
    //   204: ifne +10 -> 214
    //   207: aload 6
    //   209: invokeinterface 187 1 0
    //   214: aload 10
    //   216: invokeinterface 191 1 0
    //   221: ifle +351 -> 572
    //   224: new 155	java/util/ArrayList
    //   227: dup
    //   228: invokespecial 156	java/util/ArrayList:<init>	()V
    //   231: astore 7
    //   233: aload 11
    //   235: invokevirtual 195	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   238: astore 6
    //   240: aload 6
    //   242: ifnull +169 -> 411
    //   245: aload 6
    //   247: sipush 256
    //   250: invokevirtual 201	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   253: astore 6
    //   255: aload 10
    //   257: invokeinterface 83 1 0
    //   262: astore 10
    //   264: aload 10
    //   266: invokeinterface 89 1 0
    //   271: ifeq +140 -> 411
    //   274: aload 10
    //   276: invokeinterface 93 1 0
    //   281: checkcast 143	java/lang/String
    //   284: astore 11
    //   286: aload 6
    //   288: invokeinterface 83 1 0
    //   293: astore 12
    //   295: aload 12
    //   297: invokeinterface 89 1 0
    //   302: ifeq -38 -> 264
    //   305: aload 12
    //   307: invokeinterface 93 1 0
    //   312: checkcast 203	android/content/pm/PackageInfo
    //   315: astore 13
    //   317: aload 13
    //   319: ifnull -24 -> 295
    //   322: aload 13
    //   324: getfield 206	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   327: aload 11
    //   329: invokevirtual 209	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   332: ifeq -37 -> 295
    //   335: aload 7
    //   337: aload 11
    //   339: invokeinterface 181 2 0
    //   344: pop
    //   345: goto -50 -> 295
    //   348: astore 6
    //   350: aload 9
    //   352: monitorexit
    //   353: aload 6
    //   355: athrow
    //   356: astore 6
    //   358: aconst_null
    //   359: astore 6
    //   361: aload 6
    //   363: ifnull -149 -> 214
    //   366: aload 6
    //   368: invokeinterface 184 1 0
    //   373: ifne -159 -> 214
    //   376: aload 6
    //   378: invokeinterface 187 1 0
    //   383: goto -169 -> 214
    //   386: aload 7
    //   388: ifnull +20 -> 408
    //   391: aload 7
    //   393: invokeinterface 184 1 0
    //   398: ifne +10 -> 408
    //   401: aload 7
    //   403: invokeinterface 187 1 0
    //   408: aload 6
    //   410: athrow
    //   411: aload 7
    //   413: invokeinterface 191 1 0
    //   418: ifle +154 -> 572
    //   421: ldc -45
    //   423: astore 6
    //   425: iconst_0
    //   426: istore_1
    //   427: iload_1
    //   428: aload 7
    //   430: invokeinterface 191 1 0
    //   435: if_icmpge +90 -> 525
    //   438: aload 7
    //   440: iload_1
    //   441: invokeinterface 215 2 0
    //   446: checkcast 143	java/lang/String
    //   449: astore 10
    //   451: iload_1
    //   452: ifne +38 -> 490
    //   455: new 217	java/lang/StringBuilder
    //   458: dup
    //   459: invokespecial 218	java/lang/StringBuilder:<init>	()V
    //   462: aload 6
    //   464: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   467: ldc -32
    //   469: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   472: aload 10
    //   474: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   477: ldc -32
    //   479: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   482: invokevirtual 227	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   485: astore 6
    //   487: goto +139 -> 626
    //   490: new 217	java/lang/StringBuilder
    //   493: dup
    //   494: invokespecial 218	java/lang/StringBuilder:<init>	()V
    //   497: aload 6
    //   499: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   502: ldc -27
    //   504: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   507: aload 10
    //   509: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   512: ldc -32
    //   514: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   517: invokevirtual 227	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   520: astore 6
    //   522: goto +104 -> 626
    //   525: new 217	java/lang/StringBuilder
    //   528: dup
    //   529: invokespecial 218	java/lang/StringBuilder:<init>	()V
    //   532: aload 6
    //   534: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   537: ldc -25
    //   539: invokevirtual 222	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   542: invokevirtual 227	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   545: astore 6
    //   547: aload 8
    //   549: getfield 124	com/duapps/ad/eg:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   552: invokevirtual 134	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   555: aload 8
    //   557: getfield 124	com/duapps/ad/eg:jdField_do_of_type_AndroidContentContext	Landroid/content/Context;
    //   560: bipush 7
    //   562: invokestatic 139	com/duapps/ad/stats/DuAdCacheProvider:do	(Landroid/content/Context;I)Landroid/net/Uri;
    //   565: aload 6
    //   567: aconst_null
    //   568: invokevirtual 153	android/content/ContentResolver:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   571: pop
    //   572: aload_0
    //   573: invokestatic 119	java/lang/System:currentTimeMillis	()J
    //   576: putfield 121	com/duapps/ad/bk:jdField_do_of_type_Long	J
    //   579: aload 9
    //   581: monitorexit
    //   582: return
    //   583: astore 6
    //   585: goto -13 -> 572
    //   588: astore 6
    //   590: goto -18 -> 572
    //   593: astore 8
    //   595: aload 6
    //   597: astore 7
    //   599: aload 8
    //   601: astore 6
    //   603: goto -217 -> 386
    //   606: astore 7
    //   608: goto -247 -> 361
    //   611: astore 6
    //   613: goto -523 -> 90
    //   616: astore 6
    //   618: goto -528 -> 90
    //   621: astore 6
    //   623: goto -237 -> 386
    //   626: iload_1
    //   627: iconst_1
    //   628: iadd
    //   629: istore_1
    //   630: goto -203 -> 427
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	633	0	this	bk
    //   426	204	1	i	int
    //   15	66	2	l1	long
    //   50	29	4	l2	long
    //   36	251	6	localObject1	Object
    //   348	6	6	localObject2	Object
    //   356	1	6	localException1	Exception
    //   359	207	6	str	String
    //   583	1	6	localThrowable1	Throwable
    //   588	8	6	localException2	Exception
    //   601	1	6	localObject3	Object
    //   611	1	6	localThrowable2	Throwable
    //   616	1	6	localException3	Exception
    //   621	1	6	localObject4	Object
    //   1	597	7	localObject5	Object
    //   606	1	7	localException4	Exception
    //   97	459	8	localEg	eg
    //   593	7	8	localObject6	Object
    //   7	573	9	localObject7	Object
    //   112	396	10	localObject8	Object
    //   103	235	11	localObject9	Object
    //   293	13	12	localIterator	Iterator
    //   315	8	13	localPackageInfo	android.content.pm.PackageInfo
    // Exception table:
    //   from	to	target	type
    //   12	52	348	finally
    //   52	90	348	finally
    //   90	114	348	finally
    //   197	214	348	finally
    //   214	240	348	finally
    //   245	264	348	finally
    //   264	295	348	finally
    //   295	317	348	finally
    //   322	345	348	finally
    //   350	353	348	finally
    //   366	383	348	finally
    //   391	408	348	finally
    //   408	411	348	finally
    //   411	421	348	finally
    //   427	451	348	finally
    //   455	487	348	finally
    //   490	522	348	finally
    //   525	547	348	finally
    //   547	572	348	finally
    //   572	579	348	finally
    //   579	582	348	finally
    //   114	149	356	java/lang/Exception
    //   547	572	583	java/lang/Throwable
    //   547	572	588	java/lang/Exception
    //   154	192	593	finally
    //   154	192	606	java/lang/Exception
    //   52	90	611	java/lang/Throwable
    //   52	90	616	java/lang/Exception
    //   114	149	621	finally
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
          if ((paramAg != null) && (!TextUtils.isEmpty(paramAg.jdField_if)) && (!bool1) && (ag.jdMethod_if(paramAg)) && (i == 0) && (!bool2))
          {
            if (paramAg.jdField_do == 0) {
              this.jdField_do_of_type_ComDuappsAdBf.jdMethod_do(paramAg, paramAg.d, this.jdField_do_of_type_ComDuappsAdBi);
            }
          }
          else {
            return;
          }
          if (paramAg.jdField_do != 1) {
            continue;
          }
          synchronized (this.jdField_do_of_type_JavaUtilLinkedList)
          {
            if (this.jdField_do_of_type_JavaUtilLinkedList.isEmpty()) {
              this.jdField_do_of_type_ComDuappsAdBl.jdMethod_do(paramAg, paramAg.d, this.jdField_do_of_type_ComDuappsAdBi);
            }
            this.jdField_do_of_type_JavaUtilLinkedList.add(paramAg);
          }
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
      if ((paramAg == null) || (paramBh == null)) {
        return;
      }
      Intent localIntent = new Intent("action_notify_preparse_cache_result");
      localIntent.putExtra("ad_id", paramAg.jdField_do_of_type_Long);
      localIntent.putExtra("ad_pkgname", paramAg.jdField_if_of_type_JavaLangString);
      localIntent.putExtra("parse_result_type", paramBh.jdField_do_of_type_Int);
      LocalBroadcastManager.getInstance(bk.jdMethod_do(bk.this)).sendBroadcast(localIntent);
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
