package com.duapps.ad.stats;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import com.duapps.ad.base.LogHelper;
import com.duapps.ad.base.k;

public class h
{
  private static h b;
  private Context a;
  
  private h(Context paramContext)
  {
    this.a = paramContext;
  }
  
  public static h a(Context paramContext)
  {
    try
    {
      if (b == null) {
        b = new h(paramContext.getApplicationContext());
      }
      paramContext = b;
      return paramContext;
    }
    finally {}
  }
  
  private void d()
  {
    long l = System.currentTimeMillis();
    try
    {
      this.a.getContentResolver().delete(DuAdCacheProvider.a(this.a, 1), "ts<?", new String[] { String.valueOf(l - 86400000L) });
      return;
    }
    catch (Exception localException)
    {
      LogHelper.d("ToolboxCacheMgr", "dumpTimeOutDatas() exception: ", localException);
      return;
    }
    catch (Throwable localThrowable)
    {
      LogHelper.d("ToolboxCacheMgr", "dumpTimeOutDatas() exception: ", localThrowable);
    }
  }
  
  /* Error */
  public com.duapps.ad.base.h a(String paramString)
  {
    // Byte code:
    //   0: new 75	com/duapps/ad/base/h
    //   3: dup
    //   4: invokespecial 76	com/duapps/ad/base/h:<init>	()V
    //   7: astore 6
    //   9: aload 6
    //   11: aload_1
    //   12: putfield 79	com/duapps/ad/base/h:a	Ljava/lang/String;
    //   15: aload 6
    //   17: iconst_0
    //   18: putfield 83	com/duapps/ad/base/h:c	I
    //   21: invokestatic 38	java/lang/System:currentTimeMillis	()J
    //   24: lstore_2
    //   25: aload_0
    //   26: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   29: invokevirtual 42	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   32: aload_0
    //   33: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   36: iconst_1
    //   37: invokestatic 47	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   40: iconst_4
    //   41: anewarray 51	java/lang/String
    //   44: dup
    //   45: iconst_0
    //   46: ldc 85
    //   48: aastore
    //   49: dup
    //   50: iconst_1
    //   51: ldc 87
    //   53: aastore
    //   54: dup
    //   55: iconst_2
    //   56: ldc 89
    //   58: aastore
    //   59: dup
    //   60: iconst_3
    //   61: ldc 91
    //   63: aastore
    //   64: ldc 93
    //   66: iconst_2
    //   67: anewarray 51	java/lang/String
    //   70: dup
    //   71: iconst_0
    //   72: aload_1
    //   73: aastore
    //   74: dup
    //   75: iconst_1
    //   76: lload_2
    //   77: ldc2_w 52
    //   80: lsub
    //   81: invokestatic 57	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   84: aastore
    //   85: ldc 95
    //   87: invokevirtual 99	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   90: astore 4
    //   92: aload 4
    //   94: ifnull +80 -> 174
    //   97: aload 4
    //   99: astore_1
    //   100: aload 4
    //   102: invokeinterface 105 1 0
    //   107: ifeq +67 -> 174
    //   110: aload 4
    //   112: astore_1
    //   113: aload 6
    //   115: aload 4
    //   117: iconst_0
    //   118: invokeinterface 109 2 0
    //   123: putfield 79	com/duapps/ad/base/h:a	Ljava/lang/String;
    //   126: aload 4
    //   128: astore_1
    //   129: aload 6
    //   131: aload 4
    //   133: iconst_1
    //   134: invokeinterface 109 2 0
    //   139: putfield 111	com/duapps/ad/base/h:b	Ljava/lang/String;
    //   142: aload 4
    //   144: astore_1
    //   145: aload 6
    //   147: aload 4
    //   149: iconst_2
    //   150: invokeinterface 109 2 0
    //   155: putfield 113	com/duapps/ad/base/h:d	Ljava/lang/String;
    //   158: aload 4
    //   160: astore_1
    //   161: aload 6
    //   163: aload 4
    //   165: iconst_3
    //   166: invokeinterface 117 2 0
    //   171: putfield 83	com/duapps/ad/base/h:c	I
    //   174: aload 4
    //   176: ifnull +20 -> 196
    //   179: aload 4
    //   181: invokeinterface 120 1 0
    //   186: ifne +10 -> 196
    //   189: aload 4
    //   191: invokeinterface 123 1 0
    //   196: aload 6
    //   198: areturn
    //   199: astore 5
    //   201: aconst_null
    //   202: astore 4
    //   204: aload 4
    //   206: astore_1
    //   207: ldc 65
    //   209: ldc 125
    //   211: aload 5
    //   213: invokestatic 72	com/duapps/ad/base/LogHelper:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   216: aload 4
    //   218: ifnull -22 -> 196
    //   221: aload 4
    //   223: invokeinterface 120 1 0
    //   228: ifne -32 -> 196
    //   231: aload 4
    //   233: invokeinterface 123 1 0
    //   238: aload 6
    //   240: areturn
    //   241: astore 4
    //   243: aconst_null
    //   244: astore_1
    //   245: aload_1
    //   246: ifnull +18 -> 264
    //   249: aload_1
    //   250: invokeinterface 120 1 0
    //   255: ifne +9 -> 264
    //   258: aload_1
    //   259: invokeinterface 123 1 0
    //   264: aload 4
    //   266: athrow
    //   267: astore 4
    //   269: goto -24 -> 245
    //   272: astore 5
    //   274: goto -70 -> 204
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	277	0	this	h
    //   0	277	1	paramString	String
    //   24	53	2	l	long
    //   90	142	4	localCursor	android.database.Cursor
    //   241	24	4	localObject1	Object
    //   267	1	4	localObject2	Object
    //   199	13	5	localException1	Exception
    //   272	1	5	localException2	Exception
    //   7	232	6	localH	com.duapps.ad.base.h
    // Exception table:
    //   from	to	target	type
    //   25	92	199	java/lang/Exception
    //   25	92	241	finally
    //   100	110	267	finally
    //   113	126	267	finally
    //   129	142	267	finally
    //   145	158	267	finally
    //   161	174	267	finally
    //   207	216	267	finally
    //   100	110	272	java/lang/Exception
    //   113	126	272	java/lang/Exception
    //   129	142	272	java/lang/Exception
    //   145	158	272	java/lang/Exception
    //   161	174	272	java/lang/Exception
  }
  
  public void a()
  {
    long l1 = k.H(this.a);
    long l2 = System.currentTimeMillis();
    try
    {
      this.a.getContentResolver().delete(DuAdCacheProvider.a(this.a, 7), "ts<?", new String[] { String.valueOf(l2 - l1) });
      return;
    }
    catch (Exception localException)
    {
      LogHelper.d("ToolboxCacheMgr", "removePreparseCacheTimeOutDatas exception: ", localException);
      return;
    }
    catch (Throwable localThrowable)
    {
      LogHelper.d("ToolboxCacheMgr", "removePreparseCacheTimeOutDatas del exception: ", localThrowable);
    }
  }
  
  public void a(com.duapps.ad.base.h paramH)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("_url", paramH.a);
    localContentValues.put("pkg", paramH.b);
    localContentValues.put("p_url", paramH.d);
    localContentValues.put("type", Integer.valueOf(paramH.c));
    localContentValues.put("ts", Long.valueOf(paramH.e));
    paramH = paramH.a;
    try
    {
      if (this.a.getContentResolver().update(DuAdCacheProvider.a(this.a, 1), localContentValues, "_url = ?", new String[] { paramH }) <= 0) {
        this.a.getContentResolver().insert(DuAdCacheProvider.a(this.a, 1), localContentValues);
      }
      d();
      return;
    }
    catch (Exception paramH)
    {
      LogHelper.d("ToolboxCacheMgr", "saveParseResult() exception: ", paramH);
      return;
    }
    catch (Throwable paramH)
    {
      LogHelper.d("ToolboxCacheMgr", "saveParseResult() exception: ", paramH);
    }
  }
  
  public void a(e paramE)
  {
    ContentValues localContentValues = new ContentValues(5);
    localContentValues.put("logid", paramE.n());
    localContentValues.put("adid", Long.valueOf(paramE.c()));
    localContentValues.put("pkg", com.duapps.ad.internal.utils.e.a(paramE.b()));
    localContentValues.put("show_ts", Long.valueOf(System.currentTimeMillis()));
    String str = paramE.n();
    long l = paramE.c();
    try
    {
      int i = this.a.getContentResolver().update(DuAdCacheProvider.a(this.a, 8), localContentValues, "logid=? AND adid = ?", new String[] { str, String.valueOf(l) });
      if (i <= 0) {
        this.a.getContentResolver().insert(DuAdCacheProvider.a(this.a, 8), localContentValues);
      }
      LogHelper.d("ToolboxCacheMgr", "saveShowTimestamp() update = " + i);
      return;
    }
    catch (Exception paramE)
    {
      LogHelper.d("ToolboxCacheMgr", "saveShowTimestamp() exception :" + paramE);
    }
  }
  
  /* Error */
  public int b(String paramString)
  {
    // Byte code:
    //   0: invokestatic 38	java/lang/System:currentTimeMillis	()J
    //   3: lstore_3
    //   4: aload_0
    //   5: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   8: invokevirtual 42	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   11: aload_0
    //   12: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   15: iconst_1
    //   16: invokestatic 47	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   19: iconst_1
    //   20: anewarray 51	java/lang/String
    //   23: dup
    //   24: iconst_0
    //   25: ldc 91
    //   27: aastore
    //   28: ldc 93
    //   30: iconst_2
    //   31: anewarray 51	java/lang/String
    //   34: dup
    //   35: iconst_0
    //   36: aload_1
    //   37: aastore
    //   38: dup
    //   39: iconst_1
    //   40: lload_3
    //   41: ldc2_w 52
    //   44: lsub
    //   45: invokestatic 57	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   48: aastore
    //   49: ldc 95
    //   51: invokevirtual 99	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   54: astore 5
    //   56: aload 5
    //   58: ifnull +131 -> 189
    //   61: aload 5
    //   63: astore_1
    //   64: aload 5
    //   66: invokeinterface 105 1 0
    //   71: ifeq +118 -> 189
    //   74: aload 5
    //   76: astore_1
    //   77: aload 5
    //   79: iconst_0
    //   80: invokeinterface 117 2 0
    //   85: istore_2
    //   86: aload 5
    //   88: ifnull +20 -> 108
    //   91: aload 5
    //   93: invokeinterface 120 1 0
    //   98: ifne +10 -> 108
    //   101: aload 5
    //   103: invokeinterface 123 1 0
    //   108: iload_2
    //   109: ireturn
    //   110: astore 6
    //   112: aconst_null
    //   113: astore 5
    //   115: aload 5
    //   117: astore_1
    //   118: ldc 65
    //   120: ldc -26
    //   122: aload 6
    //   124: invokestatic 72	com/duapps/ad/base/LogHelper:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   127: aload 5
    //   129: ifnull +58 -> 187
    //   132: aload 5
    //   134: invokeinterface 120 1 0
    //   139: ifne +48 -> 187
    //   142: aload 5
    //   144: invokeinterface 123 1 0
    //   149: iconst_0
    //   150: ireturn
    //   151: astore 5
    //   153: aconst_null
    //   154: astore_1
    //   155: aload_1
    //   156: ifnull +18 -> 174
    //   159: aload_1
    //   160: invokeinterface 120 1 0
    //   165: ifne +9 -> 174
    //   168: aload_1
    //   169: invokeinterface 123 1 0
    //   174: aload 5
    //   176: athrow
    //   177: astore 5
    //   179: goto -24 -> 155
    //   182: astore 6
    //   184: goto -69 -> 115
    //   187: iconst_0
    //   188: ireturn
    //   189: iconst_0
    //   190: istore_2
    //   191: goto -105 -> 86
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	194	0	this	h
    //   0	194	1	paramString	String
    //   85	106	2	i	int
    //   3	38	3	l	long
    //   54	89	5	localCursor	android.database.Cursor
    //   151	24	5	localObject1	Object
    //   177	1	5	localObject2	Object
    //   110	13	6	localException1	Exception
    //   182	1	6	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   4	56	110	java/lang/Exception
    //   4	56	151	finally
    //   64	74	177	finally
    //   77	86	177	finally
    //   118	127	177	finally
    //   64	74	182	java/lang/Exception
    //   77	86	182	java/lang/Exception
  }
  
  /* Error */
  public java.util.List<com.duapps.ad.entity.b> b()
  {
    // Byte code:
    //   0: ldc -23
    //   2: iconst_2
    //   3: anewarray 4	java/lang/Object
    //   6: dup
    //   7: iconst_0
    //   8: ldc -52
    //   10: aastore
    //   11: dup
    //   12: iconst_1
    //   13: ldc -21
    //   15: aastore
    //   16: invokestatic 239	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   19: astore 5
    //   21: ldc -23
    //   23: iconst_2
    //   24: anewarray 4	java/lang/Object
    //   27: dup
    //   28: iconst_0
    //   29: ldc -15
    //   31: aastore
    //   32: dup
    //   33: iconst_1
    //   34: ldc -13
    //   36: aastore
    //   37: invokestatic 239	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   40: astore 6
    //   42: ldc -11
    //   44: iconst_1
    //   45: anewarray 4	java/lang/Object
    //   48: dup
    //   49: iconst_0
    //   50: ldc -52
    //   52: aastore
    //   53: invokestatic 239	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   56: astore 7
    //   58: invokestatic 38	java/lang/System:currentTimeMillis	()J
    //   61: lstore_3
    //   62: new 208	java/lang/StringBuilder
    //   65: dup
    //   66: invokespecial 246	java/lang/StringBuilder:<init>	()V
    //   69: aload 7
    //   71: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: ldc -5
    //   76: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: invokevirtual 220	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   82: astore 8
    //   84: new 253	java/util/ArrayList
    //   87: dup
    //   88: invokespecial 254	java/util/ArrayList:<init>	()V
    //   91: astore 7
    //   93: aload_0
    //   94: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   97: invokevirtual 42	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   100: aload_0
    //   101: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   104: bipush 8
    //   106: invokestatic 47	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   109: iconst_3
    //   110: anewarray 51	java/lang/String
    //   113: dup
    //   114: iconst_0
    //   115: ldc 87
    //   117: aastore
    //   118: dup
    //   119: iconst_1
    //   120: aload 5
    //   122: aastore
    //   123: dup
    //   124: iconst_2
    //   125: aload 6
    //   127: aastore
    //   128: ldc_w 256
    //   131: iconst_1
    //   132: anewarray 51	java/lang/String
    //   135: dup
    //   136: iconst_0
    //   137: lload_3
    //   138: ldc2_w 52
    //   141: lsub
    //   142: invokestatic 57	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   145: aastore
    //   146: aload 8
    //   148: invokevirtual 99	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   151: astore 6
    //   153: aload 6
    //   155: ifnull +218 -> 373
    //   158: aload 6
    //   160: astore 5
    //   162: aload 6
    //   164: invokeinterface 260 1 0
    //   169: ifle +204 -> 373
    //   172: aload 6
    //   174: astore 5
    //   176: aload 6
    //   178: invokeinterface 260 1 0
    //   183: bipush 10
    //   185: if_icmple +80 -> 265
    //   188: bipush 10
    //   190: istore_1
    //   191: goto +250 -> 441
    //   194: iload_2
    //   195: iload_1
    //   196: if_icmpge +152 -> 348
    //   199: iload_2
    //   200: ifne +80 -> 280
    //   203: aload 6
    //   205: astore 5
    //   207: aload 6
    //   209: invokeinterface 105 1 0
    //   214: pop
    //   215: aload 6
    //   217: astore 5
    //   219: aload 7
    //   221: new 262	com/duapps/ad/entity/b
    //   224: dup
    //   225: aload 6
    //   227: iconst_0
    //   228: invokeinterface 109 2 0
    //   233: aload 6
    //   235: iconst_1
    //   236: invokeinterface 117 2 0
    //   241: aload 6
    //   243: iconst_2
    //   244: invokeinterface 117 2 0
    //   249: invokespecial 265	com/duapps/ad/entity/b:<init>	(Ljava/lang/String;II)V
    //   252: invokeinterface 271 2 0
    //   257: pop
    //   258: iload_2
    //   259: iconst_1
    //   260: iadd
    //   261: istore_2
    //   262: goto -68 -> 194
    //   265: aload 6
    //   267: astore 5
    //   269: aload 6
    //   271: invokeinterface 260 1 0
    //   276: istore_1
    //   277: goto +164 -> 441
    //   280: aload 6
    //   282: astore 5
    //   284: aload 6
    //   286: invokeinterface 274 1 0
    //   291: pop
    //   292: goto -77 -> 215
    //   295: astore 7
    //   297: aload 6
    //   299: astore 5
    //   301: ldc 65
    //   303: new 208	java/lang/StringBuilder
    //   306: dup
    //   307: ldc_w 276
    //   310: invokespecial 213	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   313: aload 7
    //   315: invokevirtual 227	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   318: invokevirtual 220	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   321: invokestatic 222	com/duapps/ad/base/LogHelper:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   324: aload 6
    //   326: ifnull +20 -> 346
    //   329: aload 6
    //   331: invokeinterface 120 1 0
    //   336: ifne +10 -> 346
    //   339: aload 6
    //   341: invokeinterface 123 1 0
    //   346: aconst_null
    //   347: areturn
    //   348: aload 6
    //   350: ifnull +20 -> 370
    //   353: aload 6
    //   355: invokeinterface 120 1 0
    //   360: ifne +10 -> 370
    //   363: aload 6
    //   365: invokeinterface 123 1 0
    //   370: aload 7
    //   372: areturn
    //   373: aload 6
    //   375: ifnull -29 -> 346
    //   378: aload 6
    //   380: invokeinterface 120 1 0
    //   385: ifne -39 -> 346
    //   388: aload 6
    //   390: invokeinterface 123 1 0
    //   395: goto -49 -> 346
    //   398: astore 6
    //   400: aconst_null
    //   401: astore 5
    //   403: aload 5
    //   405: ifnull +20 -> 425
    //   408: aload 5
    //   410: invokeinterface 120 1 0
    //   415: ifne +10 -> 425
    //   418: aload 5
    //   420: invokeinterface 123 1 0
    //   425: aload 6
    //   427: athrow
    //   428: astore 6
    //   430: goto -27 -> 403
    //   433: astore 7
    //   435: aconst_null
    //   436: astore 6
    //   438: goto -141 -> 297
    //   441: iconst_0
    //   442: istore_2
    //   443: goto -249 -> 194
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	446	0	this	h
    //   190	87	1	i	int
    //   194	249	2	j	int
    //   61	77	3	l	long
    //   19	400	5	localObject1	Object
    //   40	349	6	localObject2	Object
    //   398	28	6	localObject3	Object
    //   428	1	6	localObject4	Object
    //   436	1	6	localObject5	Object
    //   56	164	7	localObject6	Object
    //   295	76	7	localException1	Exception
    //   433	1	7	localException2	Exception
    //   82	65	8	str	String
    // Exception table:
    //   from	to	target	type
    //   162	172	295	java/lang/Exception
    //   176	188	295	java/lang/Exception
    //   207	215	295	java/lang/Exception
    //   219	258	295	java/lang/Exception
    //   269	277	295	java/lang/Exception
    //   284	292	295	java/lang/Exception
    //   93	153	398	finally
    //   162	172	428	finally
    //   176	188	428	finally
    //   207	215	428	finally
    //   219	258	428	finally
    //   269	277	428	finally
    //   284	292	428	finally
    //   301	324	428	finally
    //   93	153	433	java/lang/Exception
  }
  
  /* Error */
  public void b(Context paramContext)
  {
    // Byte code:
    //   0: new 253	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 254	java/util/ArrayList:<init>	()V
    //   7: astore 6
    //   9: aload_0
    //   10: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   13: invokevirtual 42	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   16: aload_0
    //   17: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   20: bipush 7
    //   22: invokestatic 47	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   25: iconst_1
    //   26: anewarray 51	java/lang/String
    //   29: dup
    //   30: iconst_0
    //   31: ldc 87
    //   33: aastore
    //   34: aconst_null
    //   35: aconst_null
    //   36: aconst_null
    //   37: invokevirtual 99	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   40: astore 4
    //   42: aload 4
    //   44: ifnull +53 -> 97
    //   47: aload 4
    //   49: astore_3
    //   50: aload 4
    //   52: invokeinterface 105 1 0
    //   57: ifeq +40 -> 97
    //   60: aload 4
    //   62: astore_3
    //   63: aload 4
    //   65: iconst_0
    //   66: invokeinterface 109 2 0
    //   71: astore 5
    //   73: aload 4
    //   75: astore_3
    //   76: aload 5
    //   78: invokestatic 284	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   81: ifne +16 -> 97
    //   84: aload 4
    //   86: astore_3
    //   87: aload 6
    //   89: aload 5
    //   91: invokeinterface 271 2 0
    //   96: pop
    //   97: aload 4
    //   99: ifnull +20 -> 119
    //   102: aload 4
    //   104: invokeinterface 120 1 0
    //   109: ifne +10 -> 119
    //   112: aload 4
    //   114: invokeinterface 123 1 0
    //   119: aload 6
    //   121: invokeinterface 287 1 0
    //   126: ifle +343 -> 469
    //   129: new 253	java/util/ArrayList
    //   132: dup
    //   133: invokespecial 254	java/util/ArrayList:<init>	()V
    //   136: astore_3
    //   137: aload_1
    //   138: invokevirtual 291	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   141: astore_1
    //   142: aload_1
    //   143: ifnull +168 -> 311
    //   146: aload_1
    //   147: sipush 256
    //   150: invokevirtual 297	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   153: astore_1
    //   154: aload 6
    //   156: invokeinterface 301 1 0
    //   161: astore 4
    //   163: aload 4
    //   165: invokeinterface 306 1 0
    //   170: ifeq +141 -> 311
    //   173: aload 4
    //   175: invokeinterface 310 1 0
    //   180: checkcast 51	java/lang/String
    //   183: astore 5
    //   185: aload_1
    //   186: invokeinterface 301 1 0
    //   191: astore 6
    //   193: aload 6
    //   195: invokeinterface 306 1 0
    //   200: ifeq -37 -> 163
    //   203: aload 6
    //   205: invokeinterface 310 1 0
    //   210: checkcast 312	android/content/pm/PackageInfo
    //   213: astore 7
    //   215: aload 7
    //   217: ifnull -24 -> 193
    //   220: aload 7
    //   222: getfield 315	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   225: aload 5
    //   227: invokevirtual 318	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   230: ifeq -37 -> 193
    //   233: aload_3
    //   234: aload 5
    //   236: invokeinterface 271 2 0
    //   241: pop
    //   242: goto -49 -> 193
    //   245: astore 5
    //   247: aconst_null
    //   248: astore 4
    //   250: aload 4
    //   252: astore_3
    //   253: ldc 65
    //   255: ldc 125
    //   257: aload 5
    //   259: invokestatic 72	com/duapps/ad/base/LogHelper:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   262: aload 4
    //   264: ifnull -145 -> 119
    //   267: aload 4
    //   269: invokeinterface 120 1 0
    //   274: ifne -155 -> 119
    //   277: aload 4
    //   279: invokeinterface 123 1 0
    //   284: goto -165 -> 119
    //   287: astore_1
    //   288: aconst_null
    //   289: astore_3
    //   290: aload_3
    //   291: ifnull +18 -> 309
    //   294: aload_3
    //   295: invokeinterface 120 1 0
    //   300: ifne +9 -> 309
    //   303: aload_3
    //   304: invokeinterface 123 1 0
    //   309: aload_1
    //   310: athrow
    //   311: aload_3
    //   312: invokeinterface 287 1 0
    //   317: ifle +152 -> 469
    //   320: iconst_0
    //   321: istore_2
    //   322: ldc_w 320
    //   325: astore_1
    //   326: iload_2
    //   327: aload_3
    //   328: invokeinterface 287 1 0
    //   333: if_icmpge +93 -> 426
    //   336: aload_3
    //   337: iload_2
    //   338: invokeinterface 324 2 0
    //   343: checkcast 51	java/lang/String
    //   346: astore 4
    //   348: iload_2
    //   349: ifne +42 -> 391
    //   352: new 208	java/lang/StringBuilder
    //   355: dup
    //   356: invokespecial 246	java/lang/StringBuilder:<init>	()V
    //   359: aload_1
    //   360: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   363: ldc_w 326
    //   366: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   369: aload 4
    //   371: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   374: ldc_w 326
    //   377: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   380: invokevirtual 220	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   383: astore_1
    //   384: iload_2
    //   385: iconst_1
    //   386: iadd
    //   387: istore_2
    //   388: goto -62 -> 326
    //   391: new 208	java/lang/StringBuilder
    //   394: dup
    //   395: invokespecial 246	java/lang/StringBuilder:<init>	()V
    //   398: aload_1
    //   399: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   402: ldc_w 328
    //   405: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   408: aload 4
    //   410: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   413: ldc_w 326
    //   416: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   419: invokevirtual 220	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   422: astore_1
    //   423: goto -39 -> 384
    //   426: new 208	java/lang/StringBuilder
    //   429: dup
    //   430: invokespecial 246	java/lang/StringBuilder:<init>	()V
    //   433: aload_1
    //   434: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   437: ldc_w 330
    //   440: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   443: invokevirtual 220	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   446: astore_1
    //   447: aload_0
    //   448: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   451: invokevirtual 42	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   454: aload_0
    //   455: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   458: bipush 7
    //   460: invokestatic 47	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   463: aload_1
    //   464: aconst_null
    //   465: invokevirtual 63	android/content/ContentResolver:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   468: pop
    //   469: return
    //   470: astore_1
    //   471: ldc 65
    //   473: ldc_w 332
    //   476: aload_1
    //   477: invokestatic 72	com/duapps/ad/base/LogHelper:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   480: return
    //   481: astore_1
    //   482: ldc 65
    //   484: ldc_w 334
    //   487: aload_1
    //   488: invokestatic 72	com/duapps/ad/base/LogHelper:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   491: return
    //   492: astore_1
    //   493: goto -203 -> 290
    //   496: astore 5
    //   498: goto -248 -> 250
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	501	0	this	h
    //   0	501	1	paramContext	Context
    //   321	67	2	i	int
    //   49	288	3	localObject1	Object
    //   40	369	4	localObject2	Object
    //   71	164	5	str	String
    //   245	13	5	localException1	Exception
    //   496	1	5	localException2	Exception
    //   7	197	6	localObject3	Object
    //   213	8	7	localPackageInfo	android.content.pm.PackageInfo
    // Exception table:
    //   from	to	target	type
    //   9	42	245	java/lang/Exception
    //   9	42	287	finally
    //   447	469	470	java/lang/Exception
    //   447	469	481	java/lang/Throwable
    //   50	60	492	finally
    //   63	73	492	finally
    //   76	84	492	finally
    //   87	97	492	finally
    //   253	262	492	finally
    //   50	60	496	java/lang/Exception
    //   63	73	496	java/lang/Exception
    //   76	84	496	java/lang/Exception
    //   87	97	496	java/lang/Exception
  }
  
  public void b(com.duapps.ad.base.h paramH)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("_url", paramH.a);
    localContentValues.put("pkg", paramH.b);
    localContentValues.put("p_url", paramH.d);
    localContentValues.put("type", Integer.valueOf(paramH.c));
    localContentValues.put("ts", Long.valueOf(paramH.e));
    paramH = paramH.a;
    try
    {
      if (this.a.getContentResolver().update(DuAdCacheProvider.a(this.a, 7), localContentValues, "_url = ?", new String[] { paramH }) <= 0) {
        this.a.getContentResolver().insert(DuAdCacheProvider.a(this.a, 7), localContentValues);
      }
      return;
    }
    catch (Exception paramH)
    {
      LogHelper.d("ToolboxCacheMgr", "saveParseResult() exception: ", paramH);
    }
  }
  
  public void b(e paramE)
  {
    ContentValues localContentValues = new ContentValues(1);
    localContentValues.put("click_ts", Long.valueOf(System.currentTimeMillis()));
    String str = paramE.n();
    long l = paramE.c();
    try
    {
      int i = this.a.getContentResolver().update(DuAdCacheProvider.a(this.a, 8), localContentValues, "logid=? AND adid = ?", new String[] { str, String.valueOf(l) });
      LogHelper.d("ToolboxCacheMgr", "saveClickTimestamp() update = " + i);
      return;
    }
    catch (Exception paramE)
    {
      LogHelper.d("ToolboxCacheMgr", "saveClickTimestamp() exception :" + paramE);
    }
  }
  
  /* Error */
  public int c(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   4: invokestatic 131	com/duapps/ad/base/k:H	(Landroid/content/Context;)J
    //   7: lstore_3
    //   8: invokestatic 38	java/lang/System:currentTimeMillis	()J
    //   11: lstore 5
    //   13: aload_0
    //   14: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   17: invokevirtual 42	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: aload_0
    //   21: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   24: bipush 7
    //   26: invokestatic 47	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   29: iconst_1
    //   30: anewarray 51	java/lang/String
    //   33: dup
    //   34: iconst_0
    //   35: ldc 91
    //   37: aastore
    //   38: ldc_w 340
    //   41: iconst_2
    //   42: anewarray 51	java/lang/String
    //   45: dup
    //   46: iconst_0
    //   47: aload_1
    //   48: aastore
    //   49: dup
    //   50: iconst_1
    //   51: lload 5
    //   53: lload_3
    //   54: lsub
    //   55: invokestatic 57	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   58: aastore
    //   59: ldc 95
    //   61: invokevirtual 99	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   64: astore 7
    //   66: aload 7
    //   68: ifnull +131 -> 199
    //   71: aload 7
    //   73: astore_1
    //   74: aload 7
    //   76: invokeinterface 105 1 0
    //   81: ifeq +118 -> 199
    //   84: aload 7
    //   86: astore_1
    //   87: aload 7
    //   89: iconst_0
    //   90: invokeinterface 117 2 0
    //   95: istore_2
    //   96: aload 7
    //   98: ifnull +20 -> 118
    //   101: aload 7
    //   103: invokeinterface 120 1 0
    //   108: ifne +10 -> 118
    //   111: aload 7
    //   113: invokeinterface 123 1 0
    //   118: iload_2
    //   119: ireturn
    //   120: astore 8
    //   122: aconst_null
    //   123: astore 7
    //   125: aload 7
    //   127: astore_1
    //   128: ldc 65
    //   130: ldc -26
    //   132: aload 8
    //   134: invokestatic 72	com/duapps/ad/base/LogHelper:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   137: aload 7
    //   139: ifnull +58 -> 197
    //   142: aload 7
    //   144: invokeinterface 120 1 0
    //   149: ifne +48 -> 197
    //   152: aload 7
    //   154: invokeinterface 123 1 0
    //   159: iconst_0
    //   160: ireturn
    //   161: astore 7
    //   163: aconst_null
    //   164: astore_1
    //   165: aload_1
    //   166: ifnull +18 -> 184
    //   169: aload_1
    //   170: invokeinterface 120 1 0
    //   175: ifne +9 -> 184
    //   178: aload_1
    //   179: invokeinterface 123 1 0
    //   184: aload 7
    //   186: athrow
    //   187: astore 7
    //   189: goto -24 -> 165
    //   192: astore 8
    //   194: goto -69 -> 125
    //   197: iconst_0
    //   198: ireturn
    //   199: iconst_0
    //   200: istore_2
    //   201: goto -105 -> 96
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	204	0	this	h
    //   0	204	1	paramString	String
    //   95	106	2	i	int
    //   7	47	3	l1	long
    //   11	41	5	l2	long
    //   64	89	7	localCursor	android.database.Cursor
    //   161	24	7	localObject1	Object
    //   187	1	7	localObject2	Object
    //   120	13	8	localException1	Exception
    //   192	1	8	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   13	66	120	java/lang/Exception
    //   13	66	161	finally
    //   74	84	187	finally
    //   87	96	187	finally
    //   128	137	187	finally
    //   74	84	192	java/lang/Exception
    //   87	96	192	java/lang/Exception
  }
  
  public void c()
  {
    long l = System.currentTimeMillis();
    try
    {
      int i = this.a.getContentResolver().delete(DuAdCacheProvider.a(this.a, 8), "show_ts<? ", new String[] { String.valueOf(l - 86400000L) });
      LogHelper.d("ToolboxCacheMgr", "removeInvalidBehaviorData() count =  " + i);
      return;
    }
    catch (Exception localException)
    {
      LogHelper.d("ToolboxCacheMgr", "removeInvalidBehaviorData() exception: ", localException);
      return;
    }
    catch (Throwable localThrowable)
    {
      LogHelper.d("ToolboxCacheMgr", "removeInvalidBehaviorData() del exception: ", localThrowable);
    }
  }
  
  /* Error */
  public com.duapps.ad.base.h d(String paramString)
  {
    // Byte code:
    //   0: new 75	com/duapps/ad/base/h
    //   3: dup
    //   4: invokespecial 76	com/duapps/ad/base/h:<init>	()V
    //   7: astore 8
    //   9: aload 8
    //   11: aload_1
    //   12: putfield 111	com/duapps/ad/base/h:b	Ljava/lang/String;
    //   15: aload 8
    //   17: iconst_0
    //   18: putfield 83	com/duapps/ad/base/h:c	I
    //   21: aload_0
    //   22: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   25: invokestatic 131	com/duapps/ad/base/k:H	(Landroid/content/Context;)J
    //   28: lstore_2
    //   29: invokestatic 38	java/lang/System:currentTimeMillis	()J
    //   32: lstore 4
    //   34: aload_0
    //   35: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   38: invokevirtual 42	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   41: aload_0
    //   42: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   45: bipush 7
    //   47: invokestatic 47	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   50: iconst_4
    //   51: anewarray 51	java/lang/String
    //   54: dup
    //   55: iconst_0
    //   56: ldc 85
    //   58: aastore
    //   59: dup
    //   60: iconst_1
    //   61: ldc 87
    //   63: aastore
    //   64: dup
    //   65: iconst_2
    //   66: ldc 89
    //   68: aastore
    //   69: dup
    //   70: iconst_3
    //   71: ldc 91
    //   73: aastore
    //   74: ldc_w 340
    //   77: iconst_2
    //   78: anewarray 51	java/lang/String
    //   81: dup
    //   82: iconst_0
    //   83: aload_1
    //   84: aastore
    //   85: dup
    //   86: iconst_1
    //   87: lload 4
    //   89: lload_2
    //   90: lsub
    //   91: invokestatic 57	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   94: aastore
    //   95: ldc 95
    //   97: invokevirtual 99	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   100: astore 6
    //   102: aload 6
    //   104: ifnull +80 -> 184
    //   107: aload 6
    //   109: astore_1
    //   110: aload 6
    //   112: invokeinterface 105 1 0
    //   117: ifeq +67 -> 184
    //   120: aload 6
    //   122: astore_1
    //   123: aload 8
    //   125: aload 6
    //   127: iconst_0
    //   128: invokeinterface 109 2 0
    //   133: putfield 79	com/duapps/ad/base/h:a	Ljava/lang/String;
    //   136: aload 6
    //   138: astore_1
    //   139: aload 8
    //   141: aload 6
    //   143: iconst_1
    //   144: invokeinterface 109 2 0
    //   149: putfield 111	com/duapps/ad/base/h:b	Ljava/lang/String;
    //   152: aload 6
    //   154: astore_1
    //   155: aload 8
    //   157: aload 6
    //   159: iconst_2
    //   160: invokeinterface 109 2 0
    //   165: putfield 113	com/duapps/ad/base/h:d	Ljava/lang/String;
    //   168: aload 6
    //   170: astore_1
    //   171: aload 8
    //   173: aload 6
    //   175: iconst_3
    //   176: invokeinterface 117 2 0
    //   181: putfield 83	com/duapps/ad/base/h:c	I
    //   184: aload 6
    //   186: ifnull +20 -> 206
    //   189: aload 6
    //   191: invokeinterface 120 1 0
    //   196: ifne +10 -> 206
    //   199: aload 6
    //   201: invokeinterface 123 1 0
    //   206: aload 8
    //   208: areturn
    //   209: astore 7
    //   211: aconst_null
    //   212: astore 6
    //   214: aload 6
    //   216: astore_1
    //   217: ldc 65
    //   219: ldc 125
    //   221: aload 7
    //   223: invokestatic 72	com/duapps/ad/base/LogHelper:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   226: aload 6
    //   228: ifnull -22 -> 206
    //   231: aload 6
    //   233: invokeinterface 120 1 0
    //   238: ifne -32 -> 206
    //   241: aload 6
    //   243: invokeinterface 123 1 0
    //   248: aload 8
    //   250: areturn
    //   251: astore 6
    //   253: aconst_null
    //   254: astore_1
    //   255: aload_1
    //   256: ifnull +18 -> 274
    //   259: aload_1
    //   260: invokeinterface 120 1 0
    //   265: ifne +9 -> 274
    //   268: aload_1
    //   269: invokeinterface 123 1 0
    //   274: aload 6
    //   276: athrow
    //   277: astore 6
    //   279: goto -24 -> 255
    //   282: astore 7
    //   284: goto -70 -> 214
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	287	0	this	h
    //   0	287	1	paramString	String
    //   28	62	2	l1	long
    //   32	56	4	l2	long
    //   100	142	6	localCursor	android.database.Cursor
    //   251	24	6	localObject1	Object
    //   277	1	6	localObject2	Object
    //   209	13	7	localException1	Exception
    //   282	1	7	localException2	Exception
    //   7	242	8	localH	com.duapps.ad.base.h
    // Exception table:
    //   from	to	target	type
    //   34	102	209	java/lang/Exception
    //   34	102	251	finally
    //   110	120	277	finally
    //   123	136	277	finally
    //   139	152	277	finally
    //   155	168	277	finally
    //   171	184	277	finally
    //   217	226	277	finally
    //   110	120	282	java/lang/Exception
    //   123	136	282	java/lang/Exception
    //   139	152	282	java/lang/Exception
    //   155	168	282	java/lang/Exception
    //   171	184	282	java/lang/Exception
  }
}