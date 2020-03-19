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
    catch (Throwable localThrowable)
    {
      LogHelper.d("ToolboxCacheMgr", "dumpTimeOutDatas() exception: ", localThrowable);
      return;
    }
    catch (Exception localException)
    {
      LogHelper.d("ToolboxCacheMgr", "dumpTimeOutDatas() exception: ", localException);
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
    //   90: astore_1
    //   91: aload_1
    //   92: ifnull +99 -> 191
    //   95: aload_1
    //   96: astore 4
    //   98: aload_1
    //   99: invokeinterface 105 1 0
    //   104: ifeq +87 -> 191
    //   107: aload_1
    //   108: astore 4
    //   110: aload_1
    //   111: iconst_0
    //   112: invokeinterface 109 2 0
    //   117: astore 7
    //   119: aload 6
    //   121: astore 5
    //   123: aload_1
    //   124: astore 4
    //   126: aload 5
    //   128: aload 7
    //   130: putfield 79	com/duapps/ad/base/h:a	Ljava/lang/String;
    //   133: aload_1
    //   134: astore 4
    //   136: aload 5
    //   138: aload_1
    //   139: iconst_1
    //   140: invokeinterface 109 2 0
    //   145: putfield 111	com/duapps/ad/base/h:b	Ljava/lang/String;
    //   148: aload_1
    //   149: astore 4
    //   151: aload 5
    //   153: aload_1
    //   154: iconst_2
    //   155: invokeinterface 109 2 0
    //   160: putfield 113	com/duapps/ad/base/h:d	Ljava/lang/String;
    //   163: aload_1
    //   164: astore 4
    //   166: aload 5
    //   168: aload_1
    //   169: iconst_3
    //   170: invokeinterface 117 2 0
    //   175: putfield 83	com/duapps/ad/base/h:c	I
    //   178: goto +13 -> 191
    //   181: astore 5
    //   183: goto +35 -> 218
    //   186: astore 5
    //   188: goto +30 -> 218
    //   191: aload_1
    //   192: ifnull +57 -> 249
    //   195: aload_1
    //   196: invokeinterface 120 1 0
    //   201: ifne +48 -> 249
    //   204: goto +39 -> 243
    //   207: astore_1
    //   208: aconst_null
    //   209: astore 4
    //   211: goto +45 -> 256
    //   214: astore 5
    //   216: aconst_null
    //   217: astore_1
    //   218: aload_1
    //   219: astore 4
    //   221: ldc 65
    //   223: ldc 122
    //   225: aload 5
    //   227: invokestatic 72	com/duapps/ad/base/LogHelper:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   230: aload_1
    //   231: ifnull +18 -> 249
    //   234: aload_1
    //   235: invokeinterface 120 1 0
    //   240: ifne +9 -> 249
    //   243: aload_1
    //   244: invokeinterface 125 1 0
    //   249: aload 6
    //   251: areturn
    //   252: astore_1
    //   253: goto -42 -> 211
    //   256: aload 4
    //   258: ifnull +20 -> 278
    //   261: aload 4
    //   263: invokeinterface 120 1 0
    //   268: ifne +10 -> 278
    //   271: aload 4
    //   273: invokeinterface 125 1 0
    //   278: aload_1
    //   279: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	280	0	this	h
    //   0	280	1	paramString	String
    //   24	53	2	l	long
    //   96	176	4	str1	String
    //   121	46	5	localH1	com.duapps.ad.base.h
    //   181	1	5	localException1	Exception
    //   186	1	5	localException2	Exception
    //   214	12	5	localException3	Exception
    //   7	243	6	localH2	com.duapps.ad.base.h
    //   117	12	7	str2	String
    // Exception table:
    //   from	to	target	type
    //   126	133	181	java/lang/Exception
    //   136	148	181	java/lang/Exception
    //   151	163	181	java/lang/Exception
    //   166	178	181	java/lang/Exception
    //   98	107	186	java/lang/Exception
    //   110	119	186	java/lang/Exception
    //   25	91	207	finally
    //   25	91	214	java/lang/Exception
    //   98	107	252	finally
    //   110	119	252	finally
    //   126	133	252	finally
    //   136	148	252	finally
    //   151	163	252	finally
    //   166	178	252	finally
    //   221	230	252	finally
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
    catch (Throwable localThrowable)
    {
      LogHelper.d("ToolboxCacheMgr", "removePreparseCacheTimeOutDatas del exception: ", localThrowable);
      return;
    }
    catch (Exception localException)
    {
      LogHelper.d("ToolboxCacheMgr", "removePreparseCacheTimeOutDatas exception: ", localException);
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
      if (this.a.getContentResolver().update(DuAdCacheProvider.a(this.a, 1), localContentValues, "_url = ?", new String[] { paramH }) < 1) {
        this.a.getContentResolver().insert(DuAdCacheProvider.a(this.a, 1), localContentValues);
      }
      d();
      return;
    }
    catch (Throwable paramH)
    {
      LogHelper.d("ToolboxCacheMgr", "saveParseResult() exception: ", paramH);
      return;
    }
    catch (Exception paramH)
    {
      LogHelper.d("ToolboxCacheMgr", "saveParseResult() exception: ", paramH);
    }
  }
  
  public void a(e paramE)
  {
    Object localObject = new ContentValues(5);
    ((ContentValues)localObject).put("logid", paramE.m());
    ((ContentValues)localObject).put("adid", Long.valueOf(paramE.b()));
    ((ContentValues)localObject).put("pkg", com.duapps.ad.internal.utils.e.a(paramE.a()));
    ((ContentValues)localObject).put("show_ts", Long.valueOf(System.currentTimeMillis()));
    String str = paramE.m();
    long l = paramE.b();
    try
    {
      int i = this.a.getContentResolver().update(DuAdCacheProvider.a(this.a, 8), (ContentValues)localObject, "logid=? AND adid = ?", new String[] { str, String.valueOf(l) });
      if (i < 1) {
        this.a.getContentResolver().insert(DuAdCacheProvider.a(this.a, 8), (ContentValues)localObject);
      }
      paramE = new StringBuilder();
      paramE.append("saveShowTimestamp() update = ");
      paramE.append(i);
      LogHelper.d("ToolboxCacheMgr", paramE.toString());
      return;
    }
    catch (Exception paramE)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("saveShowTimestamp() exception :");
      ((StringBuilder)localObject).append(paramE);
      LogHelper.d("ToolboxCacheMgr", ((StringBuilder)localObject).toString());
    }
  }
  
  /* Error */
  public int b(String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 4
    //   3: iconst_0
    //   4: istore_3
    //   5: invokestatic 38	java/lang/System:currentTimeMillis	()J
    //   8: lstore 5
    //   10: aconst_null
    //   11: astore 8
    //   13: aconst_null
    //   14: astore 7
    //   16: aload_0
    //   17: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   20: invokevirtual 42	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   23: aload_0
    //   24: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   27: iconst_1
    //   28: invokestatic 47	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   31: iconst_1
    //   32: anewarray 51	java/lang/String
    //   35: dup
    //   36: iconst_0
    //   37: ldc 91
    //   39: aastore
    //   40: ldc 93
    //   42: iconst_2
    //   43: anewarray 51	java/lang/String
    //   46: dup
    //   47: iconst_0
    //   48: aload_1
    //   49: aastore
    //   50: dup
    //   51: iconst_1
    //   52: lload 5
    //   54: ldc2_w 52
    //   57: lsub
    //   58: invokestatic 57	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   61: aastore
    //   62: ldc 95
    //   64: invokevirtual 99	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   67: astore_1
    //   68: iload_3
    //   69: istore_2
    //   70: aload_1
    //   71: ifnull +45 -> 116
    //   74: iload_3
    //   75: istore_2
    //   76: aload_1
    //   77: invokeinterface 105 1 0
    //   82: ifeq +34 -> 116
    //   85: aload_1
    //   86: iconst_0
    //   87: invokeinterface 117 2 0
    //   92: istore_2
    //   93: goto +23 -> 116
    //   96: astore 7
    //   98: aload_1
    //   99: astore 8
    //   101: aload 7
    //   103: astore_1
    //   104: aload 8
    //   106: astore 7
    //   108: goto +88 -> 196
    //   111: astore 8
    //   113: goto +41 -> 154
    //   116: iload_2
    //   117: istore_3
    //   118: aload_1
    //   119: ifnull +75 -> 194
    //   122: iload_2
    //   123: istore_3
    //   124: aload_1
    //   125: invokeinterface 120 1 0
    //   130: ifne +64 -> 194
    //   133: aload_1
    //   134: invokeinterface 125 1 0
    //   139: iload_2
    //   140: ireturn
    //   141: astore_1
    //   142: goto +54 -> 196
    //   145: astore 7
    //   147: aload 8
    //   149: astore_1
    //   150: aload 7
    //   152: astore 8
    //   154: aload_1
    //   155: astore 7
    //   157: ldc 65
    //   159: ldc -25
    //   161: aload 8
    //   163: invokestatic 72	com/duapps/ad/base/LogHelper:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   166: iload 4
    //   168: istore_3
    //   169: aload_1
    //   170: ifnull +24 -> 194
    //   173: iload 4
    //   175: istore_3
    //   176: aload_1
    //   177: invokeinterface 120 1 0
    //   182: ifne +12 -> 194
    //   185: aload_1
    //   186: invokeinterface 125 1 0
    //   191: iload 4
    //   193: istore_3
    //   194: iload_3
    //   195: ireturn
    //   196: aload 7
    //   198: ifnull +20 -> 218
    //   201: aload 7
    //   203: invokeinterface 120 1 0
    //   208: ifne +10 -> 218
    //   211: aload 7
    //   213: invokeinterface 125 1 0
    //   218: aload_1
    //   219: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	220	0	this	h
    //   0	220	1	paramString	String
    //   69	71	2	i	int
    //   4	191	3	j	int
    //   1	191	4	k	int
    //   8	45	5	l	long
    //   14	1	7	localObject1	Object
    //   96	6	7	localObject2	Object
    //   106	1	7	str1	String
    //   145	6	7	localException1	Exception
    //   155	57	7	str2	String
    //   11	94	8	str3	String
    //   111	37	8	localException2	Exception
    //   152	10	8	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   76	93	96	finally
    //   76	93	111	java/lang/Exception
    //   16	68	141	finally
    //   157	166	141	finally
    //   16	68	145	java/lang/Exception
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
    //   58: invokestatic 38	java/lang/System:currentTimeMillis	()J
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
    //   107: invokevirtual 42	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   110: aload_0
    //   111: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   114: bipush 8
    //   116: invokestatic 47	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   119: iconst_3
    //   120: anewarray 51	java/lang/String
    //   123: dup
    //   124: iconst_0
    //   125: ldc 87
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
    //   141: anewarray 51	java/lang/String
    //   144: dup
    //   145: iconst_0
    //   146: lload_3
    //   147: ldc2_w 52
    //   150: lsub
    //   151: invokestatic 57	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   154: aastore
    //   155: aload 8
    //   157: invokevirtual 99	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
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
    //   235: invokeinterface 105 1 0
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
    //   269: invokeinterface 109 2 0
    //   274: aload 5
    //   276: iconst_1
    //   277: invokeinterface 117 2 0
    //   282: aload 5
    //   284: iconst_2
    //   285: invokeinterface 117 2 0
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
    //   313: invokeinterface 120 1 0
    //   318: ifne +10 -> 328
    //   321: aload 5
    //   323: invokeinterface 125 1 0
    //   328: aload 7
    //   330: areturn
    //   331: astore 7
    //   333: goto +40 -> 373
    //   336: aload 5
    //   338: ifnull +105 -> 443
    //   341: aload 5
    //   343: invokeinterface 120 1 0
    //   348: ifne +95 -> 443
    //   351: aload 5
    //   353: invokeinterface 125 1 0
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
    //   415: ldc 65
    //   417: aload 8
    //   419: invokevirtual 221	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   422: invokestatic 223	com/duapps/ad/base/LogHelper:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   425: aload 5
    //   427: ifnull +16 -> 443
    //   430: aload 5
    //   432: invokeinterface 120 1 0
    //   437: ifne +6 -> 443
    //   440: goto -89 -> 351
    //   443: aconst_null
    //   444: areturn
    //   445: astore 5
    //   447: aload 6
    //   449: ifnull +20 -> 469
    //   452: aload 6
    //   454: invokeinterface 120 1 0
    //   459: ifne +10 -> 469
    //   462: aload 6
    //   464: invokeinterface 125 1 0
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
    //   15: invokevirtual 42	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   18: aload_0
    //   19: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   22: bipush 7
    //   24: invokestatic 47	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   27: iconst_1
    //   28: anewarray 51	java/lang/String
    //   31: dup
    //   32: iconst_0
    //   33: ldc 87
    //   35: aastore
    //   36: aconst_null
    //   37: aconst_null
    //   38: aconst_null
    //   39: invokevirtual 99	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   42: astore_3
    //   43: aload_3
    //   44: ifnull +59 -> 103
    //   47: aload_3
    //   48: astore 4
    //   50: aload_3
    //   51: invokeinterface 105 1 0
    //   56: ifeq +47 -> 103
    //   59: aload_3
    //   60: astore 4
    //   62: aload_3
    //   63: iconst_0
    //   64: invokeinterface 109 2 0
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
    //   108: invokeinterface 120 1 0
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
    //   133: ldc 65
    //   135: ldc 122
    //   137: aload 5
    //   139: invokestatic 72	com/duapps/ad/base/LogHelper:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   142: aload_3
    //   143: ifnull +18 -> 161
    //   146: aload_3
    //   147: invokeinterface 120 1 0
    //   152: ifne +9 -> 161
    //   155: aload_3
    //   156: invokeinterface 125 1 0
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
    //   222: checkcast 51	java/lang/String
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
    //   317: checkcast 51	java/lang/String
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
    //   463: invokevirtual 42	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   466: aload_0
    //   467: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   470: bipush 7
    //   472: invokestatic 47	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   475: aload_1
    //   476: aconst_null
    //   477: invokevirtual 63	android/content/ContentResolver:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   480: pop
    //   481: return
    //   482: astore_1
    //   483: ldc 65
    //   485: ldc_w 329
    //   488: aload_1
    //   489: invokestatic 72	com/duapps/ad/base/LogHelper:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   492: return
    //   493: astore_1
    //   494: ldc 65
    //   496: ldc_w 331
    //   499: aload_1
    //   500: invokestatic 72	com/duapps/ad/base/LogHelper:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   503: return
    //   504: astore_1
    //   505: aload 4
    //   507: ifnull +20 -> 527
    //   510: aload 4
    //   512: invokeinterface 120 1 0
    //   517: ifne +10 -> 527
    //   520: aload 4
    //   522: invokeinterface 125 1 0
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
      if (this.a.getContentResolver().update(DuAdCacheProvider.a(this.a, 7), localContentValues, "_url = ?", new String[] { paramH }) < 1)
      {
        this.a.getContentResolver().insert(DuAdCacheProvider.a(this.a, 7), localContentValues);
        return;
      }
    }
    catch (Exception paramH)
    {
      LogHelper.d("ToolboxCacheMgr", "saveParseResult() exception: ", paramH);
    }
  }
  
  public void b(e paramE)
  {
    Object localObject = new ContentValues(1);
    ((ContentValues)localObject).put("click_ts", Long.valueOf(System.currentTimeMillis()));
    String str = paramE.m();
    long l = paramE.b();
    try
    {
      int i = this.a.getContentResolver().update(DuAdCacheProvider.a(this.a, 8), (ContentValues)localObject, "logid=? AND adid = ?", new String[] { str, String.valueOf(l) });
      paramE = new StringBuilder();
      paramE.append("saveClickTimestamp() update = ");
      paramE.append(i);
      LogHelper.d("ToolboxCacheMgr", paramE.toString());
      return;
    }
    catch (Exception paramE)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("saveClickTimestamp() exception :");
      ((StringBuilder)localObject).append(paramE);
      LogHelper.d("ToolboxCacheMgr", ((StringBuilder)localObject).toString());
    }
  }
  
  /* Error */
  public int c(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   4: invokestatic 131	com/duapps/ad/base/k:H	(Landroid/content/Context;)J
    //   7: lstore 5
    //   9: iconst_0
    //   10: istore 4
    //   12: iconst_0
    //   13: istore_3
    //   14: invokestatic 38	java/lang/System:currentTimeMillis	()J
    //   17: lstore 7
    //   19: aconst_null
    //   20: astore 10
    //   22: aconst_null
    //   23: astore 9
    //   25: aload_0
    //   26: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   29: invokevirtual 42	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   32: aload_0
    //   33: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   36: bipush 7
    //   38: invokestatic 47	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   41: iconst_1
    //   42: anewarray 51	java/lang/String
    //   45: dup
    //   46: iconst_0
    //   47: ldc 91
    //   49: aastore
    //   50: ldc_w 337
    //   53: iconst_2
    //   54: anewarray 51	java/lang/String
    //   57: dup
    //   58: iconst_0
    //   59: aload_1
    //   60: aastore
    //   61: dup
    //   62: iconst_1
    //   63: lload 7
    //   65: lload 5
    //   67: lsub
    //   68: invokestatic 57	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   71: aastore
    //   72: ldc 95
    //   74: invokevirtual 99	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   77: astore_1
    //   78: iload_3
    //   79: istore_2
    //   80: aload_1
    //   81: ifnull +45 -> 126
    //   84: iload_3
    //   85: istore_2
    //   86: aload_1
    //   87: invokeinterface 105 1 0
    //   92: ifeq +34 -> 126
    //   95: aload_1
    //   96: iconst_0
    //   97: invokeinterface 117 2 0
    //   102: istore_2
    //   103: goto +23 -> 126
    //   106: astore 9
    //   108: aload_1
    //   109: astore 10
    //   111: aload 9
    //   113: astore_1
    //   114: aload 10
    //   116: astore 9
    //   118: goto +88 -> 206
    //   121: astore 10
    //   123: goto +41 -> 164
    //   126: iload_2
    //   127: istore_3
    //   128: aload_1
    //   129: ifnull +75 -> 204
    //   132: iload_2
    //   133: istore_3
    //   134: aload_1
    //   135: invokeinterface 120 1 0
    //   140: ifne +64 -> 204
    //   143: aload_1
    //   144: invokeinterface 125 1 0
    //   149: iload_2
    //   150: ireturn
    //   151: astore_1
    //   152: goto +54 -> 206
    //   155: astore 9
    //   157: aload 10
    //   159: astore_1
    //   160: aload 9
    //   162: astore 10
    //   164: aload_1
    //   165: astore 9
    //   167: ldc 65
    //   169: ldc -25
    //   171: aload 10
    //   173: invokestatic 72	com/duapps/ad/base/LogHelper:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   176: iload 4
    //   178: istore_3
    //   179: aload_1
    //   180: ifnull +24 -> 204
    //   183: iload 4
    //   185: istore_3
    //   186: aload_1
    //   187: invokeinterface 120 1 0
    //   192: ifne +12 -> 204
    //   195: aload_1
    //   196: invokeinterface 125 1 0
    //   201: iload 4
    //   203: istore_3
    //   204: iload_3
    //   205: ireturn
    //   206: aload 9
    //   208: ifnull +20 -> 228
    //   211: aload 9
    //   213: invokeinterface 120 1 0
    //   218: ifne +10 -> 228
    //   221: aload 9
    //   223: invokeinterface 125 1 0
    //   228: aload_1
    //   229: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	230	0	this	h
    //   0	230	1	paramString	String
    //   79	71	2	i	int
    //   13	192	3	j	int
    //   10	192	4	k	int
    //   7	59	5	l1	long
    //   17	47	7	l2	long
    //   23	1	9	localObject1	Object
    //   106	6	9	localObject2	Object
    //   116	1	9	str1	String
    //   155	6	9	localException1	Exception
    //   165	57	9	str2	String
    //   20	95	10	str3	String
    //   121	37	10	localException2	Exception
    //   162	10	10	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   86	103	106	finally
    //   86	103	121	java/lang/Exception
    //   25	78	151	finally
    //   167	176	151	finally
    //   25	78	155	java/lang/Exception
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
      LogHelper.d("ToolboxCacheMgr", localStringBuilder.toString());
      return;
    }
    catch (Throwable localThrowable)
    {
      LogHelper.d("ToolboxCacheMgr", "removeInvalidBehaviorData() del exception: ", localThrowable);
      return;
    }
    catch (Exception localException)
    {
      LogHelper.d("ToolboxCacheMgr", "removeInvalidBehaviorData() exception: ", localException);
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
    //   34: aconst_null
    //   35: astore 9
    //   37: aconst_null
    //   38: astore 6
    //   40: aload_0
    //   41: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   44: invokevirtual 42	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   47: aload_0
    //   48: getfield 15	com/duapps/ad/stats/h:a	Landroid/content/Context;
    //   51: bipush 7
    //   53: invokestatic 47	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   56: iconst_4
    //   57: anewarray 51	java/lang/String
    //   60: dup
    //   61: iconst_0
    //   62: ldc 85
    //   64: aastore
    //   65: dup
    //   66: iconst_1
    //   67: ldc 87
    //   69: aastore
    //   70: dup
    //   71: iconst_2
    //   72: ldc 89
    //   74: aastore
    //   75: dup
    //   76: iconst_3
    //   77: ldc 91
    //   79: aastore
    //   80: ldc_w 337
    //   83: iconst_2
    //   84: anewarray 51	java/lang/String
    //   87: dup
    //   88: iconst_0
    //   89: aload_1
    //   90: aastore
    //   91: dup
    //   92: iconst_1
    //   93: lload 4
    //   95: lload_2
    //   96: lsub
    //   97: invokestatic 57	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   100: aastore
    //   101: ldc 95
    //   103: invokevirtual 99	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   106: astore 7
    //   108: aload 7
    //   110: ifnull +97 -> 207
    //   113: aload 7
    //   115: invokeinterface 105 1 0
    //   120: ifeq +87 -> 207
    //   123: aload 7
    //   125: iconst_0
    //   126: invokeinterface 109 2 0
    //   131: astore 6
    //   133: aload 8
    //   135: astore_1
    //   136: aload_1
    //   137: aload 6
    //   139: putfield 79	com/duapps/ad/base/h:a	Ljava/lang/String;
    //   142: aload_1
    //   143: aload 7
    //   145: iconst_1
    //   146: invokeinterface 109 2 0
    //   151: putfield 111	com/duapps/ad/base/h:b	Ljava/lang/String;
    //   154: aload_1
    //   155: aload 7
    //   157: iconst_2
    //   158: invokeinterface 109 2 0
    //   163: putfield 113	com/duapps/ad/base/h:d	Ljava/lang/String;
    //   166: aload_1
    //   167: aload 7
    //   169: iconst_3
    //   170: invokeinterface 117 2 0
    //   175: putfield 83	com/duapps/ad/base/h:c	I
    //   178: goto +29 -> 207
    //   181: astore_1
    //   182: goto +12 -> 194
    //   185: astore_1
    //   186: aload 7
    //   188: astore 6
    //   190: goto +43 -> 233
    //   193: astore_1
    //   194: aload 7
    //   196: astore 6
    //   198: aload_1
    //   199: astore 7
    //   201: aload 6
    //   203: astore_1
    //   204: goto +37 -> 241
    //   207: aload 7
    //   209: ifnull +63 -> 272
    //   212: aload 7
    //   214: invokeinterface 120 1 0
    //   219: ifne +53 -> 272
    //   222: aload 7
    //   224: invokeinterface 125 1 0
    //   229: aload 8
    //   231: areturn
    //   232: astore_1
    //   233: goto +42 -> 275
    //   236: astore 7
    //   238: aload 9
    //   240: astore_1
    //   241: aload_1
    //   242: astore 6
    //   244: ldc 65
    //   246: ldc 122
    //   248: aload 7
    //   250: invokestatic 72	com/duapps/ad/base/LogHelper:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   253: aload_1
    //   254: ifnull +18 -> 272
    //   257: aload_1
    //   258: invokeinterface 120 1 0
    //   263: ifne +9 -> 272
    //   266: aload_1
    //   267: invokeinterface 125 1 0
    //   272: aload 8
    //   274: areturn
    //   275: aload 6
    //   277: ifnull +20 -> 297
    //   280: aload 6
    //   282: invokeinterface 120 1 0
    //   287: ifne +10 -> 297
    //   290: aload 6
    //   292: invokeinterface 125 1 0
    //   297: aload_1
    //   298: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	299	0	this	h
    //   0	299	1	paramString	String
    //   28	68	2	l1	long
    //   32	62	4	l2	long
    //   38	253	6	localObject1	Object
    //   106	117	7	localObject2	Object
    //   236	13	7	localException	Exception
    //   7	266	8	localH	com.duapps.ad.base.h
    //   35	204	9	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   136	178	181	java/lang/Exception
    //   113	133	185	finally
    //   136	178	185	finally
    //   113	133	193	java/lang/Exception
    //   40	108	232	finally
    //   244	253	232	finally
    //   40	108	236	java/lang/Exception
  }
}
