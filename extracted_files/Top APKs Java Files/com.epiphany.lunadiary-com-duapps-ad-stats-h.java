package com.duapps.ad.stats;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import com.duapps.ad.base.g;
import com.duapps.ad.base.i;
import com.duapps.ad.base.l;
import com.duapps.ad.internal.utils.d;

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
      g.b("ToolboxCacheMgr", "dumpTimeOutDatas() exception: ", localException);
    }
  }
  
  /* Error */
  public i a(String paramString)
  {
    // Byte code:
    //   0: new 73	com/duapps/ad/base/i
    //   3: dup
    //   4: invokespecial 74	com/duapps/ad/base/i:<init>	()V
    //   7: astore 6
    //   9: aload 6
    //   11: aload_1
    //   12: putfield 77	com/duapps/ad/base/i:a	Ljava/lang/String;
    //   15: aload 6
    //   17: iconst_0
    //   18: putfield 81	com/duapps/ad/base/i:c	I
    //   21: invokestatic 36	java/lang/System:currentTimeMillis	()J
    //   24: lstore_2
    //   25: aconst_null
    //   26: astore 4
    //   28: aconst_null
    //   29: astore 5
    //   31: aload_0
    //   32: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   35: invokevirtual 40	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   38: aload_0
    //   39: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   42: iconst_1
    //   43: invokestatic 45	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   46: iconst_4
    //   47: anewarray 49	java/lang/String
    //   50: dup
    //   51: iconst_0
    //   52: ldc 83
    //   54: aastore
    //   55: dup
    //   56: iconst_1
    //   57: ldc 85
    //   59: aastore
    //   60: dup
    //   61: iconst_2
    //   62: ldc 87
    //   64: aastore
    //   65: dup
    //   66: iconst_3
    //   67: ldc 89
    //   69: aastore
    //   70: ldc 91
    //   72: iconst_2
    //   73: anewarray 49	java/lang/String
    //   76: dup
    //   77: iconst_0
    //   78: aload_1
    //   79: aastore
    //   80: dup
    //   81: iconst_1
    //   82: lload_2
    //   83: ldc2_w 50
    //   86: lsub
    //   87: invokestatic 55	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   90: aastore
    //   91: ldc 93
    //   93: invokevirtual 97	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   96: astore_1
    //   97: aload_1
    //   98: ifnull +90 -> 188
    //   101: aload_1
    //   102: astore 5
    //   104: aload_1
    //   105: astore 4
    //   107: aload_1
    //   108: invokeinterface 103 1 0
    //   113: ifeq +75 -> 188
    //   116: aload_1
    //   117: astore 5
    //   119: aload_1
    //   120: astore 4
    //   122: aload 6
    //   124: aload_1
    //   125: iconst_0
    //   126: invokeinterface 107 2 0
    //   131: putfield 77	com/duapps/ad/base/i:a	Ljava/lang/String;
    //   134: aload_1
    //   135: astore 5
    //   137: aload_1
    //   138: astore 4
    //   140: aload 6
    //   142: aload_1
    //   143: iconst_1
    //   144: invokeinterface 107 2 0
    //   149: putfield 109	com/duapps/ad/base/i:b	Ljava/lang/String;
    //   152: aload_1
    //   153: astore 5
    //   155: aload_1
    //   156: astore 4
    //   158: aload 6
    //   160: aload_1
    //   161: iconst_2
    //   162: invokeinterface 107 2 0
    //   167: putfield 111	com/duapps/ad/base/i:d	Ljava/lang/String;
    //   170: aload_1
    //   171: astore 5
    //   173: aload_1
    //   174: astore 4
    //   176: aload 6
    //   178: aload_1
    //   179: iconst_3
    //   180: invokeinterface 115 2 0
    //   185: putfield 81	com/duapps/ad/base/i:c	I
    //   188: aload_1
    //   189: ifnull +56 -> 245
    //   192: aload_1
    //   193: invokeinterface 118 1 0
    //   198: ifne +47 -> 245
    //   201: goto +38 -> 239
    //   204: astore_1
    //   205: goto +43 -> 248
    //   208: astore_1
    //   209: aload 4
    //   211: astore 5
    //   213: ldc 63
    //   215: ldc 120
    //   217: aload_1
    //   218: invokestatic 70	com/duapps/ad/base/g:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   221: aload 4
    //   223: ifnull +22 -> 245
    //   226: aload 4
    //   228: invokeinterface 118 1 0
    //   233: ifne +12 -> 245
    //   236: aload 4
    //   238: astore_1
    //   239: aload_1
    //   240: invokeinterface 123 1 0
    //   245: aload 6
    //   247: areturn
    //   248: aload 5
    //   250: ifnull +20 -> 270
    //   253: aload 5
    //   255: invokeinterface 118 1 0
    //   260: ifne +10 -> 270
    //   263: aload 5
    //   265: invokeinterface 123 1 0
    //   270: aload_1
    //   271: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	272	0	this	h
    //   0	272	1	paramString	String
    //   24	59	2	l	long
    //   26	211	4	str1	String
    //   29	235	5	str2	String
    //   7	239	6	localI	i
    // Exception table:
    //   from	to	target	type
    //   31	97	204	finally
    //   107	116	204	finally
    //   122	134	204	finally
    //   140	152	204	finally
    //   158	170	204	finally
    //   176	188	204	finally
    //   213	221	204	finally
    //   31	97	208	java/lang/Exception
    //   107	116	208	java/lang/Exception
    //   122	134	208	java/lang/Exception
    //   140	152	208	java/lang/Exception
    //   158	170	208	java/lang/Exception
    //   176	188	208	java/lang/Exception
  }
  
  public void a()
  {
    long l1 = l.k(this.a);
    long l2 = System.currentTimeMillis();
    try
    {
      this.a.getContentResolver().delete(DuAdCacheProvider.a(this.a, 7), "ts<?", new String[] { String.valueOf(l2 - l1) });
      return;
    }
    catch (Throwable localThrowable)
    {
      g.b("ToolboxCacheMgr", "removePreparseCacheTimeOutDatas del exception: ", localThrowable);
      return;
    }
    catch (Exception localException)
    {
      g.b("ToolboxCacheMgr", "removePreparseCacheTimeOutDatas exception: ", localException);
    }
  }
  
  public void a(i paramI)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("_url", paramI.a);
    localContentValues.put("pkg", paramI.b);
    localContentValues.put("p_url", paramI.d);
    localContentValues.put("type", Integer.valueOf(paramI.c));
    localContentValues.put("ts", Long.valueOf(paramI.e));
    paramI = paramI.a;
    try
    {
      if (this.a.getContentResolver().update(DuAdCacheProvider.a(this.a, 1), localContentValues, "_url = ?", new String[] { paramI }) < 1) {
        this.a.getContentResolver().insert(DuAdCacheProvider.a(this.a, 1), localContentValues);
      }
      d();
      return;
    }
    catch (Exception paramI)
    {
      g.b("ToolboxCacheMgr", "saveParseResult() exception: ", paramI);
    }
  }
  
  public void a(e paramE)
  {
    Object localObject = new ContentValues(5);
    ((ContentValues)localObject).put("logid", paramE.m());
    ((ContentValues)localObject).put("adid", Long.valueOf(paramE.c()));
    ((ContentValues)localObject).put("pkg", d.a(paramE.b()));
    ((ContentValues)localObject).put("show_ts", Long.valueOf(System.currentTimeMillis()));
    String str = paramE.m();
    long l = paramE.c();
    try
    {
      int i = this.a.getContentResolver().update(DuAdCacheProvider.a(this.a, 8), (ContentValues)localObject, "logid=? AND adid = ?", new String[] { str, String.valueOf(l) });
      if (i < 1) {
        this.a.getContentResolver().insert(DuAdCacheProvider.a(this.a, 8), (ContentValues)localObject);
      }
      paramE = new StringBuilder();
      paramE.append("saveShowTimestamp() update = ");
      paramE.append(i);
      g.c("ToolboxCacheMgr", paramE.toString());
      return;
    }
    catch (Exception paramE)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("saveShowTimestamp() exception :");
      ((StringBuilder)localObject).append(paramE);
      g.c("ToolboxCacheMgr", ((StringBuilder)localObject).toString());
    }
  }
  
  /* Error */
  public int b(String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 4
    //   3: iconst_0
    //   4: istore 5
    //   6: iconst_0
    //   7: istore_3
    //   8: invokestatic 36	java/lang/System:currentTimeMillis	()J
    //   11: lstore 6
    //   13: aconst_null
    //   14: astore 8
    //   16: aconst_null
    //   17: astore 9
    //   19: aload_0
    //   20: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   23: invokevirtual 40	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   26: aload_0
    //   27: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   30: iconst_1
    //   31: invokestatic 45	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   34: iconst_1
    //   35: anewarray 49	java/lang/String
    //   38: dup
    //   39: iconst_0
    //   40: ldc 89
    //   42: aastore
    //   43: ldc 91
    //   45: iconst_2
    //   46: anewarray 49	java/lang/String
    //   49: dup
    //   50: iconst_0
    //   51: aload_1
    //   52: aastore
    //   53: dup
    //   54: iconst_1
    //   55: lload 6
    //   57: ldc2_w 50
    //   60: lsub
    //   61: invokestatic 55	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   64: aastore
    //   65: ldc 93
    //   67: invokevirtual 97	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   70: astore_1
    //   71: iload_3
    //   72: istore_2
    //   73: aload_1
    //   74: ifnull +34 -> 108
    //   77: iload_3
    //   78: istore_2
    //   79: aload_1
    //   80: astore 9
    //   82: aload_1
    //   83: astore 8
    //   85: aload_1
    //   86: invokeinterface 103 1 0
    //   91: ifeq +17 -> 108
    //   94: aload_1
    //   95: astore 9
    //   97: aload_1
    //   98: astore 8
    //   100: aload_1
    //   101: iconst_0
    //   102: invokeinterface 115 2 0
    //   107: istore_2
    //   108: iload_2
    //   109: istore_3
    //   110: aload_1
    //   111: ifnull +69 -> 180
    //   114: iload_2
    //   115: istore_3
    //   116: aload_1
    //   117: invokeinterface 118 1 0
    //   122: ifne +58 -> 180
    //   125: aload_1
    //   126: invokeinterface 123 1 0
    //   131: iload_2
    //   132: ireturn
    //   133: astore_1
    //   134: goto +48 -> 182
    //   137: astore_1
    //   138: aload 8
    //   140: astore 9
    //   142: ldc 63
    //   144: ldc -25
    //   146: aload_1
    //   147: invokestatic 70	com/duapps/ad/base/g:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   150: iload 5
    //   152: istore_3
    //   153: aload 8
    //   155: ifnull +25 -> 180
    //   158: iload 5
    //   160: istore_3
    //   161: aload 8
    //   163: invokeinterface 118 1 0
    //   168: ifne +12 -> 180
    //   171: iload 4
    //   173: istore_2
    //   174: aload 8
    //   176: astore_1
    //   177: goto -52 -> 125
    //   180: iload_3
    //   181: ireturn
    //   182: aload 9
    //   184: ifnull +20 -> 204
    //   187: aload 9
    //   189: invokeinterface 118 1 0
    //   194: ifne +10 -> 204
    //   197: aload 9
    //   199: invokeinterface 123 1 0
    //   204: aload_1
    //   205: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	206	0	this	h
    //   0	206	1	paramString	String
    //   72	102	2	i	int
    //   7	174	3	j	int
    //   1	171	4	k	int
    //   4	155	5	m	int
    //   11	45	6	l	long
    //   14	161	8	str1	String
    //   17	181	9	str2	String
    // Exception table:
    //   from	to	target	type
    //   19	71	133	finally
    //   85	94	133	finally
    //   100	108	133	finally
    //   142	150	133	finally
    //   19	71	137	java/lang/Exception
    //   85	94	137	java/lang/Exception
    //   100	108	137	java/lang/Exception
  }
  
  /* Error */
  public java.util.List<com.duapps.ad.entity.b> b()
  {
    // Byte code:
    //   0: ldc -22
    //   2: iconst_2
    //   3: anewarray 4	java/lang/Object
    //   6: dup
    //   7: iconst_0
    //   8: ldc -52
    //   10: aastore
    //   11: dup
    //   12: iconst_1
    //   13: ldc -20
    //   15: aastore
    //   16: invokestatic 240	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   19: astore 5
    //   21: ldc -22
    //   23: iconst_2
    //   24: anewarray 4	java/lang/Object
    //   27: dup
    //   28: iconst_0
    //   29: ldc -14
    //   31: aastore
    //   32: dup
    //   33: iconst_1
    //   34: ldc -12
    //   36: aastore
    //   37: invokestatic 240	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   40: astore 6
    //   42: ldc -10
    //   44: iconst_1
    //   45: anewarray 4	java/lang/Object
    //   48: dup
    //   49: iconst_0
    //   50: ldc -52
    //   52: aastore
    //   53: invokestatic 240	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   56: astore 7
    //   58: invokestatic 36	java/lang/System:currentTimeMillis	()J
    //   61: lstore_3
    //   62: new 208	java/lang/StringBuilder
    //   65: dup
    //   66: invokespecial 209	java/lang/StringBuilder:<init>	()V
    //   69: astore 8
    //   71: aload 8
    //   73: aload 7
    //   75: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: pop
    //   79: aload 8
    //   81: ldc -8
    //   83: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   86: pop
    //   87: aload 8
    //   89: invokevirtual 221	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   92: astore 8
    //   94: new 250	java/util/ArrayList
    //   97: dup
    //   98: invokespecial 251	java/util/ArrayList:<init>	()V
    //   101: astore 7
    //   103: aload_0
    //   104: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   107: invokevirtual 40	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   110: aload_0
    //   111: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   114: bipush 8
    //   116: invokestatic 45	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   119: iconst_3
    //   120: anewarray 49	java/lang/String
    //   123: dup
    //   124: iconst_0
    //   125: ldc 85
    //   127: aastore
    //   128: dup
    //   129: iconst_1
    //   130: aload 5
    //   132: aastore
    //   133: dup
    //   134: iconst_2
    //   135: aload 6
    //   137: aastore
    //   138: ldc -3
    //   140: iconst_1
    //   141: anewarray 49	java/lang/String
    //   144: dup
    //   145: iconst_0
    //   146: lload_3
    //   147: ldc2_w 50
    //   150: lsub
    //   151: invokestatic 55	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   154: aastore
    //   155: aload 8
    //   157: invokevirtual 97	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   160: astore 5
    //   162: aload 5
    //   164: ifnull +172 -> 336
    //   167: aload 5
    //   169: astore 6
    //   171: aload 5
    //   173: invokeinterface 257 1 0
    //   178: ifle +158 -> 336
    //   181: aload 5
    //   183: astore 6
    //   185: aload 5
    //   187: invokeinterface 257 1 0
    //   192: istore_2
    //   193: bipush 10
    //   195: istore_1
    //   196: iload_2
    //   197: bipush 10
    //   199: if_icmple +6 -> 205
    //   202: goto +270 -> 472
    //   205: aload 5
    //   207: astore 6
    //   209: aload 5
    //   211: invokeinterface 257 1 0
    //   216: istore_1
    //   217: goto +255 -> 472
    //   220: iload_2
    //   221: iload_1
    //   222: if_icmpge +84 -> 306
    //   225: iload_2
    //   226: ifne +18 -> 244
    //   229: aload 5
    //   231: astore 6
    //   233: aload 5
    //   235: invokeinterface 103 1 0
    //   240: pop
    //   241: goto +15 -> 256
    //   244: aload 5
    //   246: astore 6
    //   248: aload 5
    //   250: invokeinterface 260 1 0
    //   255: pop
    //   256: aload 5
    //   258: astore 6
    //   260: aload 7
    //   262: new 262	com/duapps/ad/entity/b
    //   265: dup
    //   266: aload 5
    //   268: iconst_0
    //   269: invokeinterface 107 2 0
    //   274: aload 5
    //   276: iconst_1
    //   277: invokeinterface 115 2 0
    //   282: aload 5
    //   284: iconst_2
    //   285: invokeinterface 115 2 0
    //   290: invokespecial 265	com/duapps/ad/entity/b:<init>	(Ljava/lang/String;II)V
    //   293: invokeinterface 271 2 0
    //   298: pop
    //   299: iload_2
    //   300: iconst_1
    //   301: iadd
    //   302: istore_2
    //   303: goto -83 -> 220
    //   306: aload 5
    //   308: ifnull +20 -> 328
    //   311: aload 5
    //   313: invokeinterface 118 1 0
    //   318: ifne +10 -> 328
    //   321: aload 5
    //   323: invokeinterface 123 1 0
    //   328: aload 7
    //   330: areturn
    //   331: astore 7
    //   333: goto +40 -> 373
    //   336: aload 5
    //   338: ifnull +105 -> 443
    //   341: aload 5
    //   343: invokeinterface 118 1 0
    //   348: ifne +95 -> 443
    //   351: aload 5
    //   353: invokeinterface 123 1 0
    //   358: aconst_null
    //   359: areturn
    //   360: astore 5
    //   362: aconst_null
    //   363: astore 6
    //   365: goto +82 -> 447
    //   368: astore 7
    //   370: aconst_null
    //   371: astore 5
    //   373: aload 5
    //   375: astore 6
    //   377: new 208	java/lang/StringBuilder
    //   380: dup
    //   381: invokespecial 209	java/lang/StringBuilder:<init>	()V
    //   384: astore 8
    //   386: aload 5
    //   388: astore 6
    //   390: aload 8
    //   392: ldc_w 273
    //   395: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   398: pop
    //   399: aload 5
    //   401: astore 6
    //   403: aload 8
    //   405: aload 7
    //   407: invokevirtual 228	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   410: pop
    //   411: aload 5
    //   413: astore 6
    //   415: ldc 63
    //   417: aload 8
    //   419: invokevirtual 221	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   422: invokestatic 223	com/duapps/ad/base/g:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   425: aload 5
    //   427: ifnull +16 -> 443
    //   430: aload 5
    //   432: invokeinterface 118 1 0
    //   437: ifne +6 -> 443
    //   440: goto -89 -> 351
    //   443: aconst_null
    //   444: areturn
    //   445: astore 5
    //   447: aload 6
    //   449: ifnull +20 -> 469
    //   452: aload 6
    //   454: invokeinterface 118 1 0
    //   459: ifne +10 -> 469
    //   462: aload 6
    //   464: invokeinterface 123 1 0
    //   469: aload 5
    //   471: athrow
    //   472: iconst_0
    //   473: istore_2
    //   474: goto -254 -> 220
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	477	0	this	h
    //   195	28	1	i	int
    //   192	282	2	j	int
    //   61	86	3	l	long
    //   19	333	5	localObject1	Object
    //   360	1	5	localObject2	Object
    //   371	60	5	localObject3	Object
    //   445	25	5	localObject4	Object
    //   40	423	6	localObject5	Object
    //   56	273	7	localObject6	Object
    //   331	1	7	localException1	Exception
    //   368	38	7	localException2	Exception
    //   69	349	8	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   171	181	331	java/lang/Exception
    //   185	193	331	java/lang/Exception
    //   209	217	331	java/lang/Exception
    //   233	241	331	java/lang/Exception
    //   248	256	331	java/lang/Exception
    //   260	299	331	java/lang/Exception
    //   103	162	360	finally
    //   103	162	368	java/lang/Exception
    //   171	181	445	finally
    //   185	193	445	finally
    //   209	217	445	finally
    //   233	241	445	finally
    //   248	256	445	finally
    //   260	299	445	finally
    //   377	386	445	finally
    //   390	399	445	finally
    //   403	411	445	finally
    //   415	425	445	finally
  }
  
  /* Error */
  public void b(Context paramContext)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: new 250	java/util/ArrayList
    //   5: dup
    //   6: invokespecial 251	java/util/ArrayList:<init>	()V
    //   9: astore 6
    //   11: aload_0
    //   12: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   15: invokevirtual 40	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   18: aload_0
    //   19: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   22: bipush 7
    //   24: invokestatic 45	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   27: iconst_1
    //   28: anewarray 49	java/lang/String
    //   31: dup
    //   32: iconst_0
    //   33: ldc 85
    //   35: aastore
    //   36: aconst_null
    //   37: aconst_null
    //   38: aconst_null
    //   39: invokevirtual 97	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   42: astore_3
    //   43: aload_3
    //   44: ifnull +59 -> 103
    //   47: aload_3
    //   48: astore 4
    //   50: aload_3
    //   51: invokeinterface 103 1 0
    //   56: ifeq +47 -> 103
    //   59: aload_3
    //   60: astore 4
    //   62: aload_3
    //   63: iconst_0
    //   64: invokeinterface 107 2 0
    //   69: astore 5
    //   71: aload_3
    //   72: astore 4
    //   74: aload 5
    //   76: invokestatic 281	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   79: ifne +24 -> 103
    //   82: aload_3
    //   83: astore 4
    //   85: aload 6
    //   87: aload 5
    //   89: invokeinterface 271 2 0
    //   94: pop
    //   95: goto +8 -> 103
    //   98: astore 5
    //   100: goto +30 -> 130
    //   103: aload_3
    //   104: ifnull +57 -> 161
    //   107: aload_3
    //   108: invokeinterface 118 1 0
    //   113: ifne +48 -> 161
    //   116: goto +39 -> 155
    //   119: astore_1
    //   120: aconst_null
    //   121: astore 4
    //   123: goto +382 -> 505
    //   126: astore 5
    //   128: aconst_null
    //   129: astore_3
    //   130: aload_3
    //   131: astore 4
    //   133: ldc 63
    //   135: ldc 120
    //   137: aload 5
    //   139: invokestatic 70	com/duapps/ad/base/g:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   142: aload_3
    //   143: ifnull +18 -> 161
    //   146: aload_3
    //   147: invokeinterface 118 1 0
    //   152: ifne +9 -> 161
    //   155: aload_3
    //   156: invokeinterface 123 1 0
    //   161: aload 6
    //   163: invokeinterface 284 1 0
    //   168: ifle +335 -> 503
    //   171: new 250	java/util/ArrayList
    //   174: dup
    //   175: invokespecial 251	java/util/ArrayList:<init>	()V
    //   178: astore_3
    //   179: aload_1
    //   180: invokevirtual 288	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   183: astore_1
    //   184: aload_1
    //   185: ifnull +102 -> 287
    //   188: aload_1
    //   189: sipush 256
    //   192: invokevirtual 294	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   195: astore_1
    //   196: aload 6
    //   198: invokeinterface 298 1 0
    //   203: astore 4
    //   205: aload 4
    //   207: invokeinterface 303 1 0
    //   212: ifeq +75 -> 287
    //   215: aload 4
    //   217: invokeinterface 307 1 0
    //   222: checkcast 49	java/lang/String
    //   225: astore 5
    //   227: aload_1
    //   228: invokeinterface 298 1 0
    //   233: astore 6
    //   235: aload 6
    //   237: invokeinterface 303 1 0
    //   242: ifeq -37 -> 205
    //   245: aload 6
    //   247: invokeinterface 307 1 0
    //   252: checkcast 309	android/content/pm/PackageInfo
    //   255: astore 7
    //   257: aload 7
    //   259: ifnull -24 -> 235
    //   262: aload 7
    //   264: getfield 312	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   267: aload 5
    //   269: invokevirtual 315	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   272: ifeq -37 -> 235
    //   275: aload_3
    //   276: aload 5
    //   278: invokeinterface 271 2 0
    //   283: pop
    //   284: goto -49 -> 235
    //   287: aload_3
    //   288: invokeinterface 284 1 0
    //   293: ifle +210 -> 503
    //   296: ldc_w 317
    //   299: astore_1
    //   300: iload_2
    //   301: aload_3
    //   302: invokeinterface 284 1 0
    //   307: if_icmpge +125 -> 432
    //   310: aload_3
    //   311: iload_2
    //   312: invokeinterface 321 2 0
    //   317: checkcast 49	java/lang/String
    //   320: astore 4
    //   322: iload_2
    //   323: ifne +54 -> 377
    //   326: new 208	java/lang/StringBuilder
    //   329: dup
    //   330: invokespecial 209	java/lang/StringBuilder:<init>	()V
    //   333: astore 5
    //   335: aload 5
    //   337: aload_1
    //   338: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   341: pop
    //   342: aload 5
    //   344: ldc_w 323
    //   347: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   350: pop
    //   351: aload 5
    //   353: aload 4
    //   355: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   358: pop
    //   359: aload 5
    //   361: ldc_w 323
    //   364: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   367: pop
    //   368: aload 5
    //   370: invokevirtual 221	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   373: astore_1
    //   374: goto +51 -> 425
    //   377: new 208	java/lang/StringBuilder
    //   380: dup
    //   381: invokespecial 209	java/lang/StringBuilder:<init>	()V
    //   384: astore 5
    //   386: aload 5
    //   388: aload_1
    //   389: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   392: pop
    //   393: aload 5
    //   395: ldc_w 325
    //   398: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   401: pop
    //   402: aload 5
    //   404: aload 4
    //   406: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   409: pop
    //   410: aload 5
    //   412: ldc_w 323
    //   415: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   418: pop
    //   419: aload 5
    //   421: invokevirtual 221	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   424: astore_1
    //   425: iload_2
    //   426: iconst_1
    //   427: iadd
    //   428: istore_2
    //   429: goto -129 -> 300
    //   432: new 208	java/lang/StringBuilder
    //   435: dup
    //   436: invokespecial 209	java/lang/StringBuilder:<init>	()V
    //   439: astore_3
    //   440: aload_3
    //   441: aload_1
    //   442: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   445: pop
    //   446: aload_3
    //   447: ldc_w 327
    //   450: invokevirtual 215	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   453: pop
    //   454: aload_3
    //   455: invokevirtual 221	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   458: astore_1
    //   459: aload_0
    //   460: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   463: invokevirtual 40	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   466: aload_0
    //   467: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   470: bipush 7
    //   472: invokestatic 45	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   475: aload_1
    //   476: aconst_null
    //   477: invokevirtual 61	android/content/ContentResolver:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   480: pop
    //   481: return
    //   482: astore_1
    //   483: ldc 63
    //   485: ldc_w 329
    //   488: aload_1
    //   489: invokestatic 70	com/duapps/ad/base/g:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   492: return
    //   493: astore_1
    //   494: ldc 63
    //   496: ldc_w 331
    //   499: aload_1
    //   500: invokestatic 70	com/duapps/ad/base/g:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   503: return
    //   504: astore_1
    //   505: aload 4
    //   507: ifnull +20 -> 527
    //   510: aload 4
    //   512: invokeinterface 118 1 0
    //   517: ifne +10 -> 527
    //   520: aload 4
    //   522: invokeinterface 123 1 0
    //   527: aload_1
    //   528: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	529	0	this	h
    //   0	529	1	paramContext	Context
    //   1	428	2	i	int
    //   42	413	3	localObject1	Object
    //   48	473	4	localObject2	Object
    //   69	19	5	str	String
    //   98	1	5	localException1	Exception
    //   126	12	5	localException2	Exception
    //   225	195	5	localObject3	Object
    //   9	237	6	localObject4	Object
    //   255	8	7	localPackageInfo	android.content.pm.PackageInfo
    // Exception table:
    //   from	to	target	type
    //   50	59	98	java/lang/Exception
    //   62	71	98	java/lang/Exception
    //   74	82	98	java/lang/Exception
    //   85	95	98	java/lang/Exception
    //   11	43	119	finally
    //   11	43	126	java/lang/Exception
    //   459	481	482	java/lang/Throwable
    //   459	481	493	java/lang/Exception
    //   50	59	504	finally
    //   62	71	504	finally
    //   74	82	504	finally
    //   85	95	504	finally
    //   133	142	504	finally
  }
  
  public void b(i paramI)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("_url", paramI.a);
    localContentValues.put("pkg", paramI.b);
    localContentValues.put("p_url", paramI.d);
    localContentValues.put("type", Integer.valueOf(paramI.c));
    localContentValues.put("ts", Long.valueOf(paramI.e));
    paramI = paramI.a;
    try
    {
      if (this.a.getContentResolver().update(DuAdCacheProvider.a(this.a, 7), localContentValues, "_url = ?", new String[] { paramI }) < 1)
      {
        this.a.getContentResolver().insert(DuAdCacheProvider.a(this.a, 7), localContentValues);
        return;
      }
    }
    catch (Exception paramI)
    {
      g.b("ToolboxCacheMgr", "saveParseResult() exception: ", paramI);
    }
  }
  
  public void b(e paramE)
  {
    Object localObject = new ContentValues(1);
    ((ContentValues)localObject).put("click_ts", Long.valueOf(System.currentTimeMillis()));
    String str = paramE.m();
    long l = paramE.c();
    try
    {
      int i = this.a.getContentResolver().update(DuAdCacheProvider.a(this.a, 8), (ContentValues)localObject, "logid=? AND adid = ?", new String[] { str, String.valueOf(l) });
      paramE = new StringBuilder();
      paramE.append("saveClickTimestamp() update = ");
      paramE.append(i);
      g.c("ToolboxCacheMgr", paramE.toString());
      return;
    }
    catch (Exception paramE)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("saveClickTimestamp() exception :");
      ((StringBuilder)localObject).append(paramE);
      g.c("ToolboxCacheMgr", ((StringBuilder)localObject).toString());
    }
  }
  
  /* Error */
  public int c(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   4: invokestatic 131	com/duapps/ad/base/l:k	(Landroid/content/Context;)J
    //   7: lstore 6
    //   9: iconst_0
    //   10: istore 4
    //   12: iconst_0
    //   13: istore 5
    //   15: iconst_0
    //   16: istore_3
    //   17: invokestatic 36	java/lang/System:currentTimeMillis	()J
    //   20: lstore 8
    //   22: aconst_null
    //   23: astore 10
    //   25: aconst_null
    //   26: astore 11
    //   28: aload_0
    //   29: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   32: invokevirtual 40	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   35: aload_0
    //   36: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   39: bipush 7
    //   41: invokestatic 45	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   44: iconst_1
    //   45: anewarray 49	java/lang/String
    //   48: dup
    //   49: iconst_0
    //   50: ldc 89
    //   52: aastore
    //   53: ldc_w 337
    //   56: iconst_2
    //   57: anewarray 49	java/lang/String
    //   60: dup
    //   61: iconst_0
    //   62: aload_1
    //   63: aastore
    //   64: dup
    //   65: iconst_1
    //   66: lload 8
    //   68: lload 6
    //   70: lsub
    //   71: invokestatic 55	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   74: aastore
    //   75: ldc 93
    //   77: invokevirtual 97	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   80: astore_1
    //   81: iload_3
    //   82: istore_2
    //   83: aload_1
    //   84: ifnull +34 -> 118
    //   87: iload_3
    //   88: istore_2
    //   89: aload_1
    //   90: astore 11
    //   92: aload_1
    //   93: astore 10
    //   95: aload_1
    //   96: invokeinterface 103 1 0
    //   101: ifeq +17 -> 118
    //   104: aload_1
    //   105: astore 11
    //   107: aload_1
    //   108: astore 10
    //   110: aload_1
    //   111: iconst_0
    //   112: invokeinterface 115 2 0
    //   117: istore_2
    //   118: iload_2
    //   119: istore_3
    //   120: aload_1
    //   121: ifnull +69 -> 190
    //   124: iload_2
    //   125: istore_3
    //   126: aload_1
    //   127: invokeinterface 118 1 0
    //   132: ifne +58 -> 190
    //   135: aload_1
    //   136: invokeinterface 123 1 0
    //   141: iload_2
    //   142: ireturn
    //   143: astore_1
    //   144: goto +48 -> 192
    //   147: astore_1
    //   148: aload 10
    //   150: astore 11
    //   152: ldc 63
    //   154: ldc -25
    //   156: aload_1
    //   157: invokestatic 70	com/duapps/ad/base/g:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   160: iload 5
    //   162: istore_3
    //   163: aload 10
    //   165: ifnull +25 -> 190
    //   168: iload 5
    //   170: istore_3
    //   171: aload 10
    //   173: invokeinterface 118 1 0
    //   178: ifne +12 -> 190
    //   181: iload 4
    //   183: istore_2
    //   184: aload 10
    //   186: astore_1
    //   187: goto -52 -> 135
    //   190: iload_3
    //   191: ireturn
    //   192: aload 11
    //   194: ifnull +20 -> 214
    //   197: aload 11
    //   199: invokeinterface 118 1 0
    //   204: ifne +10 -> 214
    //   207: aload 11
    //   209: invokeinterface 123 1 0
    //   214: aload_1
    //   215: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	216	0	this	h
    //   0	216	1	paramString	String
    //   82	102	2	i	int
    //   16	175	3	j	int
    //   10	172	4	k	int
    //   13	156	5	m	int
    //   7	62	6	l1	long
    //   20	47	8	l2	long
    //   23	162	10	str1	String
    //   26	182	11	str2	String
    // Exception table:
    //   from	to	target	type
    //   28	81	143	finally
    //   95	104	143	finally
    //   110	118	143	finally
    //   152	160	143	finally
    //   28	81	147	java/lang/Exception
    //   95	104	147	java/lang/Exception
    //   110	118	147	java/lang/Exception
  }
  
  public void c()
  {
    long l = System.currentTimeMillis();
    try
    {
      int i = this.a.getContentResolver().delete(DuAdCacheProvider.a(this.a, 8), "show_ts<? ", new String[] { String.valueOf(l - 86400000L) });
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("removeInvalidBehaviorData() count =  ");
      localStringBuilder.append(i);
      g.c("ToolboxCacheMgr", localStringBuilder.toString());
      return;
    }
    catch (Throwable localThrowable)
    {
      g.b("ToolboxCacheMgr", "removeInvalidBehaviorData() del exception: ", localThrowable);
      return;
    }
    catch (Exception localException)
    {
      g.b("ToolboxCacheMgr", "removeInvalidBehaviorData() exception: ", localException);
    }
  }
  
  /* Error */
  public i d(String paramString)
  {
    // Byte code:
    //   0: new 73	com/duapps/ad/base/i
    //   3: dup
    //   4: invokespecial 74	com/duapps/ad/base/i:<init>	()V
    //   7: astore 8
    //   9: aload 8
    //   11: aload_1
    //   12: putfield 109	com/duapps/ad/base/i:b	Ljava/lang/String;
    //   15: aload 8
    //   17: iconst_0
    //   18: putfield 81	com/duapps/ad/base/i:c	I
    //   21: aload_0
    //   22: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   25: invokestatic 131	com/duapps/ad/base/l:k	(Landroid/content/Context;)J
    //   28: lstore_2
    //   29: invokestatic 36	java/lang/System:currentTimeMillis	()J
    //   32: lstore 4
    //   34: aconst_null
    //   35: astore 6
    //   37: aconst_null
    //   38: astore 7
    //   40: aload_0
    //   41: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   44: invokevirtual 40	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   47: aload_0
    //   48: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   51: bipush 7
    //   53: invokestatic 45	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   56: iconst_4
    //   57: anewarray 49	java/lang/String
    //   60: dup
    //   61: iconst_0
    //   62: ldc 83
    //   64: aastore
    //   65: dup
    //   66: iconst_1
    //   67: ldc 85
    //   69: aastore
    //   70: dup
    //   71: iconst_2
    //   72: ldc 87
    //   74: aastore
    //   75: dup
    //   76: iconst_3
    //   77: ldc 89
    //   79: aastore
    //   80: ldc_w 337
    //   83: iconst_2
    //   84: anewarray 49	java/lang/String
    //   87: dup
    //   88: iconst_0
    //   89: aload_1
    //   90: aastore
    //   91: dup
    //   92: iconst_1
    //   93: lload 4
    //   95: lload_2
    //   96: lsub
    //   97: invokestatic 55	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   100: aastore
    //   101: ldc 93
    //   103: invokevirtual 97	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   106: astore_1
    //   107: aload_1
    //   108: ifnull +90 -> 198
    //   111: aload_1
    //   112: astore 7
    //   114: aload_1
    //   115: astore 6
    //   117: aload_1
    //   118: invokeinterface 103 1 0
    //   123: ifeq +75 -> 198
    //   126: aload_1
    //   127: astore 7
    //   129: aload_1
    //   130: astore 6
    //   132: aload 8
    //   134: aload_1
    //   135: iconst_0
    //   136: invokeinterface 107 2 0
    //   141: putfield 77	com/duapps/ad/base/i:a	Ljava/lang/String;
    //   144: aload_1
    //   145: astore 7
    //   147: aload_1
    //   148: astore 6
    //   150: aload 8
    //   152: aload_1
    //   153: iconst_1
    //   154: invokeinterface 107 2 0
    //   159: putfield 109	com/duapps/ad/base/i:b	Ljava/lang/String;
    //   162: aload_1
    //   163: astore 7
    //   165: aload_1
    //   166: astore 6
    //   168: aload 8
    //   170: aload_1
    //   171: iconst_2
    //   172: invokeinterface 107 2 0
    //   177: putfield 111	com/duapps/ad/base/i:d	Ljava/lang/String;
    //   180: aload_1
    //   181: astore 7
    //   183: aload_1
    //   184: astore 6
    //   186: aload 8
    //   188: aload_1
    //   189: iconst_3
    //   190: invokeinterface 115 2 0
    //   195: putfield 81	com/duapps/ad/base/i:c	I
    //   198: aload_1
    //   199: ifnull +56 -> 255
    //   202: aload_1
    //   203: invokeinterface 118 1 0
    //   208: ifne +47 -> 255
    //   211: goto +38 -> 249
    //   214: astore_1
    //   215: goto +43 -> 258
    //   218: astore_1
    //   219: aload 6
    //   221: astore 7
    //   223: ldc 63
    //   225: ldc 120
    //   227: aload_1
    //   228: invokestatic 70	com/duapps/ad/base/g:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   231: aload 6
    //   233: ifnull +22 -> 255
    //   236: aload 6
    //   238: invokeinterface 118 1 0
    //   243: ifne +12 -> 255
    //   246: aload 6
    //   248: astore_1
    //   249: aload_1
    //   250: invokeinterface 123 1 0
    //   255: aload 8
    //   257: areturn
    //   258: aload 7
    //   260: ifnull +20 -> 280
    //   263: aload 7
    //   265: invokeinterface 118 1 0
    //   270: ifne +10 -> 280
    //   273: aload 7
    //   275: invokeinterface 123 1 0
    //   280: aload_1
    //   281: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	282	0	this	h
    //   0	282	1	paramString	String
    //   28	68	2	l1	long
    //   32	62	4	l2	long
    //   35	212	6	str1	String
    //   38	236	7	str2	String
    //   7	249	8	localI	i
    // Exception table:
    //   from	to	target	type
    //   40	107	214	finally
    //   117	126	214	finally
    //   132	144	214	finally
    //   150	162	214	finally
    //   168	180	214	finally
    //   186	198	214	finally
    //   223	231	214	finally
    //   40	107	218	java/lang/Exception
    //   117	126	218	java/lang/Exception
    //   132	144	218	java/lang/Exception
    //   150	162	218	java/lang/Exception
    //   168	180	218	java/lang/Exception
    //   186	198	218	java/lang/Exception
  }
}
