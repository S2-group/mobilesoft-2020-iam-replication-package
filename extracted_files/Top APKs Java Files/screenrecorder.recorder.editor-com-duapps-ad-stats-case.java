package com.duapps.ad.stats;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import com.duapps.ad.base.goto;

public class case
{
  private static case jdField_if;
  private Context jdField_do;
  
  private case(Context paramContext)
  {
    this.jdField_do = paramContext;
  }
  
  public static case jdMethod_do(Context paramContext)
  {
    try
    {
      if (if == null) {
        if = new case(paramContext.getApplicationContext());
      }
      paramContext = if;
      return paramContext;
    }
    finally {}
  }
  
  private void jdMethod_int()
  {
    long l = System.currentTimeMillis();
    try
    {
      this.jdField_do.getContentResolver().delete(DuAdCacheProvider.jdMethod_do(this.jdField_do, 1), "ts<?", new String[] { String.valueOf(l - 86400000L) });
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  /* Error */
  public com.duapps.ad.base.case jdMethod_do(String paramString)
  {
    // Byte code:
    //   0: new 66	com/duapps/ad/base/case
    //   3: dup
    //   4: invokespecial 67	com/duapps/ad/base/case:<init>	()V
    //   7: astore 5
    //   9: aload 5
    //   11: aload_1
    //   12: putfield 70	com/duapps/ad/base/case:do	Ljava/lang/String;
    //   15: aload 5
    //   17: iconst_0
    //   18: putfield 74	com/duapps/ad/base/case:for	I
    //   21: invokestatic 36	java/lang/System:currentTimeMillis	()J
    //   24: lstore_2
    //   25: aconst_null
    //   26: astore 4
    //   28: aload_0
    //   29: getfield 15	com/duapps/ad/stats/case:do	Landroid/content/Context;
    //   32: invokevirtual 40	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   35: aload_0
    //   36: getfield 15	com/duapps/ad/stats/case:do	Landroid/content/Context;
    //   39: iconst_1
    //   40: invokestatic 45	com/duapps/ad/stats/DuAdCacheProvider:do	(Landroid/content/Context;I)Landroid/net/Uri;
    //   43: iconst_4
    //   44: anewarray 49	java/lang/String
    //   47: dup
    //   48: iconst_0
    //   49: ldc 76
    //   51: aastore
    //   52: dup
    //   53: iconst_1
    //   54: ldc 78
    //   56: aastore
    //   57: dup
    //   58: iconst_2
    //   59: ldc 80
    //   61: aastore
    //   62: dup
    //   63: iconst_3
    //   64: ldc 82
    //   66: aastore
    //   67: ldc 84
    //   69: iconst_2
    //   70: anewarray 49	java/lang/String
    //   73: dup
    //   74: iconst_0
    //   75: aload_1
    //   76: aastore
    //   77: dup
    //   78: iconst_1
    //   79: lload_2
    //   80: ldc2_w 50
    //   83: lsub
    //   84: invokestatic 55	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   87: aastore
    //   88: ldc 86
    //   90: invokevirtual 90	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   93: astore_1
    //   94: aload_1
    //   95: ifnull +60 -> 155
    //   98: aload_1
    //   99: invokeinterface 96 1 0
    //   104: ifeq +51 -> 155
    //   107: aload 5
    //   109: aload_1
    //   110: iconst_0
    //   111: invokeinterface 100 2 0
    //   116: putfield 70	com/duapps/ad/base/case:do	Ljava/lang/String;
    //   119: aload 5
    //   121: aload_1
    //   122: iconst_1
    //   123: invokeinterface 100 2 0
    //   128: putfield 102	com/duapps/ad/base/case:if	Ljava/lang/String;
    //   131: aload 5
    //   133: aload_1
    //   134: iconst_2
    //   135: invokeinterface 100 2 0
    //   140: putfield 104	com/duapps/ad/base/case:int	Ljava/lang/String;
    //   143: aload 5
    //   145: aload_1
    //   146: iconst_3
    //   147: invokeinterface 108 2 0
    //   152: putfield 74	com/duapps/ad/base/case:for	I
    //   155: aload_1
    //   156: ifnull +18 -> 174
    //   159: aload_1
    //   160: invokeinterface 111 1 0
    //   165: ifne +9 -> 174
    //   168: aload_1
    //   169: invokeinterface 114 1 0
    //   174: aload 5
    //   176: areturn
    //   177: astore_1
    //   178: aconst_null
    //   179: astore_1
    //   180: aload_1
    //   181: ifnull -7 -> 174
    //   184: aload_1
    //   185: invokeinterface 111 1 0
    //   190: ifne -16 -> 174
    //   193: aload_1
    //   194: invokeinterface 114 1 0
    //   199: aload 5
    //   201: areturn
    //   202: astore 5
    //   204: aload 4
    //   206: astore_1
    //   207: aload 5
    //   209: astore 4
    //   211: aload_1
    //   212: ifnull +18 -> 230
    //   215: aload_1
    //   216: invokeinterface 111 1 0
    //   221: ifne +9 -> 230
    //   224: aload_1
    //   225: invokeinterface 114 1 0
    //   230: aload 4
    //   232: athrow
    //   233: astore 4
    //   235: goto -24 -> 211
    //   238: astore 4
    //   240: goto -60 -> 180
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	243	0	this	case
    //   0	243	1	paramString	String
    //   24	56	2	l	long
    //   26	205	4	localObject1	Object
    //   233	1	4	localObject2	Object
    //   238	1	4	localException	Exception
    //   7	193	5	localCase	com.duapps.ad.base.case
    //   202	6	5	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   28	94	177	java/lang/Exception
    //   28	94	202	finally
    //   98	155	233	finally
    //   98	155	238	java/lang/Exception
  }
  
  public void jdMethod_do()
  {
    long l1 = goto.throws(this.jdField_do);
    long l2 = System.currentTimeMillis();
    try
    {
      this.jdField_do.getContentResolver().delete(DuAdCacheProvider.jdMethod_do(this.jdField_do, 7), "ts<?", new String[] { String.valueOf(l2 - l1) });
      return;
    }
    catch (Throwable localThrowable) {}
  }
  
  public void jdMethod_do(com.duapps.ad.base.case paramCase)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("_url", paramCase.jdField_do);
    localContentValues.put("pkg", paramCase.jdField_if);
    localContentValues.put("p_url", paramCase.jdField_int);
    localContentValues.put("type", Integer.valueOf(paramCase.jdField_for));
    localContentValues.put("ts", Long.valueOf(paramCase.jdField_new));
    paramCase = paramCase.jdField_do;
    try
    {
      if (this.jdField_do.getContentResolver().update(DuAdCacheProvider.jdMethod_do(this.jdField_do, 1), localContentValues, "_url = ?", new String[] { paramCase }) < 1) {
        this.jdField_do.getContentResolver().insert(DuAdCacheProvider.jdMethod_do(this.jdField_do, 1), localContentValues);
      }
      jdMethod_int();
      return;
    }
    catch (Throwable paramCase) {}
  }
  
  public void jdMethod_do(new paramNew)
  {
    ContentValues localContentValues = new ContentValues(5);
    localContentValues.put("logid", paramNew.jdMethod_long());
    localContentValues.put("adid", Long.valueOf(paramNew.jdMethod_if()));
    localContentValues.put("pkg", com.duapps.ad.int.for.new.jdMethod_do(paramNew.jdMethod_do()));
    localContentValues.put("show_ts", Long.valueOf(System.currentTimeMillis()));
    String str = paramNew.jdMethod_long();
    long l = paramNew.jdMethod_if();
    try
    {
      if (this.jdField_do.getContentResolver().update(DuAdCacheProvider.jdMethod_do(this.jdField_do, 8), localContentValues, "logid=? AND adid = ?", new String[] { str, String.valueOf(l) }) < 1) {
        this.jdField_do.getContentResolver().insert(DuAdCacheProvider.jdMethod_do(this.jdField_do, 8), localContentValues);
      }
      return;
    }
    catch (Exception paramNew) {}
  }
  
  /* Error */
  public int jdMethod_for(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 15	com/duapps/ad/stats/case:do	Landroid/content/Context;
    //   4: invokestatic 120	com/duapps/ad/base/goto:throws	(Landroid/content/Context;)J
    //   7: lstore_3
    //   8: invokestatic 36	java/lang/System:currentTimeMillis	()J
    //   11: lstore 5
    //   13: aconst_null
    //   14: astore 7
    //   16: aload_0
    //   17: getfield 15	com/duapps/ad/stats/case:do	Landroid/content/Context;
    //   20: invokevirtual 40	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   23: aload_0
    //   24: getfield 15	com/duapps/ad/stats/case:do	Landroid/content/Context;
    //   27: bipush 7
    //   29: invokestatic 45	com/duapps/ad/stats/DuAdCacheProvider:do	(Landroid/content/Context;I)Landroid/net/Uri;
    //   32: iconst_1
    //   33: anewarray 49	java/lang/String
    //   36: dup
    //   37: iconst_0
    //   38: ldc 82
    //   40: aastore
    //   41: ldc -64
    //   43: iconst_2
    //   44: anewarray 49	java/lang/String
    //   47: dup
    //   48: iconst_0
    //   49: aload_1
    //   50: aastore
    //   51: dup
    //   52: iconst_1
    //   53: lload 5
    //   55: lload_3
    //   56: lsub
    //   57: invokestatic 55	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   60: aastore
    //   61: ldc 86
    //   63: invokevirtual 90	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   66: astore_1
    //   67: aload_1
    //   68: ifnull +108 -> 176
    //   71: aload_1
    //   72: invokeinterface 96 1 0
    //   77: ifeq +99 -> 176
    //   80: aload_1
    //   81: iconst_0
    //   82: invokeinterface 108 2 0
    //   87: istore_2
    //   88: aload_1
    //   89: ifnull +18 -> 107
    //   92: aload_1
    //   93: invokeinterface 111 1 0
    //   98: ifne +9 -> 107
    //   101: aload_1
    //   102: invokeinterface 114 1 0
    //   107: iload_2
    //   108: ireturn
    //   109: astore_1
    //   110: aconst_null
    //   111: astore_1
    //   112: aload_1
    //   113: ifnull +61 -> 174
    //   116: aload_1
    //   117: invokeinterface 111 1 0
    //   122: ifne +52 -> 174
    //   125: aload_1
    //   126: invokeinterface 114 1 0
    //   131: iconst_0
    //   132: ireturn
    //   133: astore_1
    //   134: aload 7
    //   136: ifnull +20 -> 156
    //   139: aload 7
    //   141: invokeinterface 111 1 0
    //   146: ifne +10 -> 156
    //   149: aload 7
    //   151: invokeinterface 114 1 0
    //   156: aload_1
    //   157: athrow
    //   158: astore 8
    //   160: aload_1
    //   161: astore 7
    //   163: aload 8
    //   165: astore_1
    //   166: goto -32 -> 134
    //   169: astore 7
    //   171: goto -59 -> 112
    //   174: iconst_0
    //   175: ireturn
    //   176: iconst_0
    //   177: istore_2
    //   178: goto -90 -> 88
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	181	0	this	case
    //   0	181	1	paramString	String
    //   87	91	2	i	int
    //   7	49	3	l1	long
    //   11	43	5	l2	long
    //   14	148	7	str	String
    //   169	1	7	localException	Exception
    //   158	6	8	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   16	67	109	java/lang/Exception
    //   16	67	133	finally
    //   71	88	158	finally
    //   71	88	169	java/lang/Exception
  }
  
  public void jdMethod_for()
  {
    long l = System.currentTimeMillis();
    try
    {
      this.jdField_do.getContentResolver().delete(DuAdCacheProvider.jdMethod_do(this.jdField_do, 8), "show_ts<? ", new String[] { String.valueOf(l - 86400000L) });
      return;
    }
    catch (Throwable localThrowable) {}catch (Exception localException) {}
  }
  
  /* Error */
  public int jdMethod_if(String paramString)
  {
    // Byte code:
    //   0: invokestatic 36	java/lang/System:currentTimeMillis	()J
    //   3: lstore_3
    //   4: aconst_null
    //   5: astore 5
    //   7: aload_0
    //   8: getfield 15	com/duapps/ad/stats/case:do	Landroid/content/Context;
    //   11: invokevirtual 40	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   14: aload_0
    //   15: getfield 15	com/duapps/ad/stats/case:do	Landroid/content/Context;
    //   18: iconst_1
    //   19: invokestatic 45	com/duapps/ad/stats/DuAdCacheProvider:do	(Landroid/content/Context;I)Landroid/net/Uri;
    //   22: iconst_1
    //   23: anewarray 49	java/lang/String
    //   26: dup
    //   27: iconst_0
    //   28: ldc 82
    //   30: aastore
    //   31: ldc 84
    //   33: iconst_2
    //   34: anewarray 49	java/lang/String
    //   37: dup
    //   38: iconst_0
    //   39: aload_1
    //   40: aastore
    //   41: dup
    //   42: iconst_1
    //   43: lload_3
    //   44: ldc2_w 50
    //   47: lsub
    //   48: invokestatic 55	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   51: aastore
    //   52: ldc 86
    //   54: invokevirtual 90	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   57: astore_1
    //   58: aload_1
    //   59: ifnull +108 -> 167
    //   62: aload_1
    //   63: invokeinterface 96 1 0
    //   68: ifeq +99 -> 167
    //   71: aload_1
    //   72: iconst_0
    //   73: invokeinterface 108 2 0
    //   78: istore_2
    //   79: aload_1
    //   80: ifnull +18 -> 98
    //   83: aload_1
    //   84: invokeinterface 111 1 0
    //   89: ifne +9 -> 98
    //   92: aload_1
    //   93: invokeinterface 114 1 0
    //   98: iload_2
    //   99: ireturn
    //   100: astore_1
    //   101: aconst_null
    //   102: astore_1
    //   103: aload_1
    //   104: ifnull +61 -> 165
    //   107: aload_1
    //   108: invokeinterface 111 1 0
    //   113: ifne +52 -> 165
    //   116: aload_1
    //   117: invokeinterface 114 1 0
    //   122: iconst_0
    //   123: ireturn
    //   124: astore_1
    //   125: aload 5
    //   127: ifnull +20 -> 147
    //   130: aload 5
    //   132: invokeinterface 111 1 0
    //   137: ifne +10 -> 147
    //   140: aload 5
    //   142: invokeinterface 114 1 0
    //   147: aload_1
    //   148: athrow
    //   149: astore 6
    //   151: aload_1
    //   152: astore 5
    //   154: aload 6
    //   156: astore_1
    //   157: goto -32 -> 125
    //   160: astore 5
    //   162: goto -59 -> 103
    //   165: iconst_0
    //   166: ireturn
    //   167: iconst_0
    //   168: istore_2
    //   169: goto -90 -> 79
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	172	0	this	case
    //   0	172	1	paramString	String
    //   78	91	2	i	int
    //   3	41	3	l	long
    //   5	148	5	str	String
    //   160	1	5	localException	Exception
    //   149	6	6	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   7	58	100	java/lang/Exception
    //   7	58	124	finally
    //   62	79	149	finally
    //   62	79	160	java/lang/Exception
  }
  
  /* Error */
  public java.util.List<com.duapps.ad.entity.int> jdMethod_if()
  {
    // Byte code:
    //   0: ldc -59
    //   2: iconst_2
    //   3: anewarray 4	java/lang/Object
    //   6: dup
    //   7: iconst_0
    //   8: ldc -69
    //   10: aastore
    //   11: dup
    //   12: iconst_1
    //   13: ldc -57
    //   15: aastore
    //   16: invokestatic 203	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   19: astore 5
    //   21: ldc -59
    //   23: iconst_2
    //   24: anewarray 4	java/lang/Object
    //   27: dup
    //   28: iconst_0
    //   29: ldc -51
    //   31: aastore
    //   32: dup
    //   33: iconst_1
    //   34: ldc -49
    //   36: aastore
    //   37: invokestatic 203	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   40: astore 7
    //   42: ldc -47
    //   44: iconst_1
    //   45: anewarray 4	java/lang/Object
    //   48: dup
    //   49: iconst_0
    //   50: ldc -69
    //   52: aastore
    //   53: invokestatic 203	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   56: astore 6
    //   58: invokestatic 36	java/lang/System:currentTimeMillis	()J
    //   61: lstore_3
    //   62: new 211	java/lang/StringBuilder
    //   65: dup
    //   66: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   69: aload 6
    //   71: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: ldc -38
    //   76: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: invokevirtual 221	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   82: astore 8
    //   84: new 223	java/util/ArrayList
    //   87: dup
    //   88: invokespecial 224	java/util/ArrayList:<init>	()V
    //   91: astore 6
    //   93: aload_0
    //   94: getfield 15	com/duapps/ad/stats/case:do	Landroid/content/Context;
    //   97: invokevirtual 40	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   100: aload_0
    //   101: getfield 15	com/duapps/ad/stats/case:do	Landroid/content/Context;
    //   104: bipush 8
    //   106: invokestatic 45	com/duapps/ad/stats/DuAdCacheProvider:do	(Landroid/content/Context;I)Landroid/net/Uri;
    //   109: iconst_3
    //   110: anewarray 49	java/lang/String
    //   113: dup
    //   114: iconst_0
    //   115: ldc 78
    //   117: aastore
    //   118: dup
    //   119: iconst_1
    //   120: aload 5
    //   122: aastore
    //   123: dup
    //   124: iconst_2
    //   125: aload 7
    //   127: aastore
    //   128: ldc -30
    //   130: iconst_1
    //   131: anewarray 49	java/lang/String
    //   134: dup
    //   135: iconst_0
    //   136: lload_3
    //   137: ldc2_w 50
    //   140: lsub
    //   141: invokestatic 55	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   144: aastore
    //   145: aload 8
    //   147: invokevirtual 90	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   150: astore 5
    //   152: aload 5
    //   154: ifnull +167 -> 321
    //   157: aload 5
    //   159: invokeinterface 230 1 0
    //   164: ifle +157 -> 321
    //   167: aload 5
    //   169: invokeinterface 230 1 0
    //   174: bipush 10
    //   176: if_icmple +72 -> 248
    //   179: bipush 10
    //   181: istore_1
    //   182: goto +207 -> 389
    //   185: iload_2
    //   186: iload_1
    //   187: if_icmpge +109 -> 296
    //   190: iload_2
    //   191: ifne +68 -> 259
    //   194: aload 5
    //   196: invokeinterface 96 1 0
    //   201: pop
    //   202: aload 6
    //   204: new 232	com/duapps/ad/entity/int
    //   207: dup
    //   208: aload 5
    //   210: iconst_0
    //   211: invokeinterface 100 2 0
    //   216: aload 5
    //   218: iconst_1
    //   219: invokeinterface 108 2 0
    //   224: aload 5
    //   226: iconst_2
    //   227: invokeinterface 108 2 0
    //   232: invokespecial 235	com/duapps/ad/entity/int:<init>	(Ljava/lang/String;II)V
    //   235: invokeinterface 241 2 0
    //   240: pop
    //   241: iload_2
    //   242: iconst_1
    //   243: iadd
    //   244: istore_2
    //   245: goto -60 -> 185
    //   248: aload 5
    //   250: invokeinterface 230 1 0
    //   255: istore_1
    //   256: goto +133 -> 389
    //   259: aload 5
    //   261: invokeinterface 244 1 0
    //   266: pop
    //   267: goto -65 -> 202
    //   270: astore 6
    //   272: aload 5
    //   274: ifnull +20 -> 294
    //   277: aload 5
    //   279: invokeinterface 111 1 0
    //   284: ifne +10 -> 294
    //   287: aload 5
    //   289: invokeinterface 114 1 0
    //   294: aconst_null
    //   295: areturn
    //   296: aload 5
    //   298: ifnull +20 -> 318
    //   301: aload 5
    //   303: invokeinterface 111 1 0
    //   308: ifne +10 -> 318
    //   311: aload 5
    //   313: invokeinterface 114 1 0
    //   318: aload 6
    //   320: areturn
    //   321: aload 5
    //   323: ifnull -29 -> 294
    //   326: aload 5
    //   328: invokeinterface 111 1 0
    //   333: ifne -39 -> 294
    //   336: aload 5
    //   338: invokeinterface 114 1 0
    //   343: goto -49 -> 294
    //   346: astore 6
    //   348: aconst_null
    //   349: astore 5
    //   351: aload 5
    //   353: ifnull +20 -> 373
    //   356: aload 5
    //   358: invokeinterface 111 1 0
    //   363: ifne +10 -> 373
    //   366: aload 5
    //   368: invokeinterface 114 1 0
    //   373: aload 6
    //   375: athrow
    //   376: astore 6
    //   378: goto -27 -> 351
    //   381: astore 5
    //   383: aconst_null
    //   384: astore 5
    //   386: goto -114 -> 272
    //   389: iconst_0
    //   390: istore_2
    //   391: goto -206 -> 185
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	394	0	this	case
    //   181	75	1	i	int
    //   185	206	2	j	int
    //   61	76	3	l	long
    //   19	348	5	localObject1	Object
    //   381	1	5	localException1	Exception
    //   384	1	5	localObject2	Object
    //   56	147	6	localObject3	Object
    //   270	49	6	localException2	Exception
    //   346	28	6	localObject4	Object
    //   376	1	6	localObject5	Object
    //   40	86	7	str1	String
    //   82	64	8	str2	String
    // Exception table:
    //   from	to	target	type
    //   157	179	270	java/lang/Exception
    //   194	202	270	java/lang/Exception
    //   202	241	270	java/lang/Exception
    //   248	256	270	java/lang/Exception
    //   259	267	270	java/lang/Exception
    //   93	152	346	finally
    //   157	179	376	finally
    //   194	202	376	finally
    //   202	241	376	finally
    //   248	256	376	finally
    //   259	267	376	finally
    //   93	152	381	java/lang/Exception
  }
  
  /* Error */
  public void jdMethod_if(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 223	java/util/ArrayList
    //   5: dup
    //   6: invokespecial 224	java/util/ArrayList:<init>	()V
    //   9: astore 5
    //   11: aload_0
    //   12: getfield 15	com/duapps/ad/stats/case:do	Landroid/content/Context;
    //   15: invokevirtual 40	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   18: aload_0
    //   19: getfield 15	com/duapps/ad/stats/case:do	Landroid/content/Context;
    //   22: bipush 7
    //   24: invokestatic 45	com/duapps/ad/stats/DuAdCacheProvider:do	(Landroid/content/Context;I)Landroid/net/Uri;
    //   27: iconst_1
    //   28: anewarray 49	java/lang/String
    //   31: dup
    //   32: iconst_0
    //   33: ldc 78
    //   35: aastore
    //   36: aconst_null
    //   37: aconst_null
    //   38: aconst_null
    //   39: invokevirtual 90	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   42: astore 4
    //   44: aload 4
    //   46: astore_3
    //   47: aload_3
    //   48: ifnull +39 -> 87
    //   51: aload_3
    //   52: invokeinterface 96 1 0
    //   57: ifeq +30 -> 87
    //   60: aload_3
    //   61: iconst_0
    //   62: invokeinterface 100 2 0
    //   67: astore 4
    //   69: aload 4
    //   71: invokestatic 252	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   74: ifne +13 -> 87
    //   77: aload 5
    //   79: aload 4
    //   81: invokeinterface 241 2 0
    //   86: pop
    //   87: aload_3
    //   88: ifnull +18 -> 106
    //   91: aload_3
    //   92: invokeinterface 111 1 0
    //   97: ifne +9 -> 106
    //   100: aload_3
    //   101: invokeinterface 114 1 0
    //   106: aload 5
    //   108: invokeinterface 255 1 0
    //   113: ifle +324 -> 437
    //   116: new 223	java/util/ArrayList
    //   119: dup
    //   120: invokespecial 224	java/util/ArrayList:<init>	()V
    //   123: astore_3
    //   124: aload_1
    //   125: invokevirtual 259	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   128: astore_1
    //   129: aload_1
    //   130: ifnull +149 -> 279
    //   133: aload_1
    //   134: sipush 256
    //   137: invokevirtual 265	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   140: astore_1
    //   141: aload 5
    //   143: invokeinterface 269 1 0
    //   148: astore 4
    //   150: aload 4
    //   152: invokeinterface 274 1 0
    //   157: ifeq +122 -> 279
    //   160: aload 4
    //   162: invokeinterface 278 1 0
    //   167: checkcast 49	java/lang/String
    //   170: astore 5
    //   172: aload_1
    //   173: invokeinterface 269 1 0
    //   178: astore 6
    //   180: aload 6
    //   182: invokeinterface 274 1 0
    //   187: ifeq -37 -> 150
    //   190: aload 6
    //   192: invokeinterface 278 1 0
    //   197: checkcast 280	android/content/pm/PackageInfo
    //   200: astore 7
    //   202: aload 7
    //   204: ifnull -24 -> 180
    //   207: aload 7
    //   209: getfield 283	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   212: aload 5
    //   214: invokevirtual 286	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   217: ifeq -37 -> 180
    //   220: aload_3
    //   221: aload 5
    //   223: invokeinterface 241 2 0
    //   228: pop
    //   229: goto -49 -> 180
    //   232: astore_3
    //   233: aconst_null
    //   234: astore_3
    //   235: aload_3
    //   236: ifnull -130 -> 106
    //   239: aload_3
    //   240: invokeinterface 111 1 0
    //   245: ifne -139 -> 106
    //   248: aload_3
    //   249: invokeinterface 114 1 0
    //   254: goto -148 -> 106
    //   257: astore_1
    //   258: aload_3
    //   259: ifnull +18 -> 277
    //   262: aload_3
    //   263: invokeinterface 111 1 0
    //   268: ifne +9 -> 277
    //   271: aload_3
    //   272: invokeinterface 114 1 0
    //   277: aload_1
    //   278: athrow
    //   279: aload_3
    //   280: invokeinterface 255 1 0
    //   285: ifle +152 -> 437
    //   288: iconst_0
    //   289: istore_2
    //   290: ldc_w 288
    //   293: astore_1
    //   294: iload_2
    //   295: aload_3
    //   296: invokeinterface 255 1 0
    //   301: if_icmpge +93 -> 394
    //   304: aload_3
    //   305: iload_2
    //   306: invokeinterface 292 2 0
    //   311: checkcast 49	java/lang/String
    //   314: astore 4
    //   316: iload_2
    //   317: ifne +42 -> 359
    //   320: new 211	java/lang/StringBuilder
    //   323: dup
    //   324: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   327: aload_1
    //   328: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   331: ldc_w 294
    //   334: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   337: aload 4
    //   339: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   342: ldc_w 294
    //   345: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   348: invokevirtual 221	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   351: astore_1
    //   352: iload_2
    //   353: iconst_1
    //   354: iadd
    //   355: istore_2
    //   356: goto -62 -> 294
    //   359: new 211	java/lang/StringBuilder
    //   362: dup
    //   363: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   366: aload_1
    //   367: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   370: ldc_w 296
    //   373: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   376: aload 4
    //   378: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   381: ldc_w 294
    //   384: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   387: invokevirtual 221	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   390: astore_1
    //   391: goto -39 -> 352
    //   394: new 211	java/lang/StringBuilder
    //   397: dup
    //   398: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   401: aload_1
    //   402: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   405: ldc_w 298
    //   408: invokevirtual 216	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   411: invokevirtual 221	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   414: astore_1
    //   415: aload_0
    //   416: getfield 15	com/duapps/ad/stats/case:do	Landroid/content/Context;
    //   419: invokevirtual 40	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   422: aload_0
    //   423: getfield 15	com/duapps/ad/stats/case:do	Landroid/content/Context;
    //   426: bipush 7
    //   428: invokestatic 45	com/duapps/ad/stats/DuAdCacheProvider:do	(Landroid/content/Context;I)Landroid/net/Uri;
    //   431: aload_1
    //   432: aconst_null
    //   433: invokevirtual 61	android/content/ContentResolver:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   436: pop
    //   437: return
    //   438: astore_1
    //   439: return
    //   440: astore_1
    //   441: goto -183 -> 258
    //   444: astore 4
    //   446: goto -211 -> 235
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	449	0	this	case
    //   0	449	1	paramContext	Context
    //   289	67	2	i	int
    //   1	220	3	localObject1	Object
    //   232	1	3	localException1	Exception
    //   234	71	3	localObject2	Object
    //   42	335	4	localObject3	Object
    //   444	1	4	localException2	Exception
    //   9	213	5	localObject4	Object
    //   178	13	6	localIterator	java.util.Iterator
    //   200	8	7	localPackageInfo	android.content.pm.PackageInfo
    // Exception table:
    //   from	to	target	type
    //   11	44	232	java/lang/Exception
    //   11	44	257	finally
    //   415	437	438	java/lang/Throwable
    //   51	87	440	finally
    //   51	87	444	java/lang/Exception
  }
  
  public void jdMethod_if(com.duapps.ad.base.case paramCase)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("_url", paramCase.jdField_do);
    localContentValues.put("pkg", paramCase.jdField_if);
    localContentValues.put("p_url", paramCase.jdField_int);
    localContentValues.put("type", Integer.valueOf(paramCase.jdField_for));
    localContentValues.put("ts", Long.valueOf(paramCase.jdField_new));
    paramCase = paramCase.jdField_do;
    try
    {
      if (this.jdField_do.getContentResolver().update(DuAdCacheProvider.jdMethod_do(this.jdField_do, 7), localContentValues, "_url = ?", new String[] { paramCase }) < 1) {
        this.jdField_do.getContentResolver().insert(DuAdCacheProvider.jdMethod_do(this.jdField_do, 7), localContentValues);
      }
      return;
    }
    catch (Exception paramCase) {}
  }
  
  public void jdMethod_if(new paramNew)
  {
    ContentValues localContentValues = new ContentValues(1);
    localContentValues.put("click_ts", Long.valueOf(System.currentTimeMillis()));
    String str = paramNew.jdMethod_long();
    long l = paramNew.jdMethod_if();
    try
    {
      this.jdField_do.getContentResolver().update(DuAdCacheProvider.jdMethod_do(this.jdField_do, 8), localContentValues, "logid=? AND adid = ?", new String[] { str, String.valueOf(l) });
      return;
    }
    catch (Exception paramNew) {}
  }
  
  /* Error */
  public com.duapps.ad.base.case jdMethod_int(String paramString)
  {
    // Byte code:
    //   0: new 66	com/duapps/ad/base/case
    //   3: dup
    //   4: invokespecial 67	com/duapps/ad/base/case:<init>	()V
    //   7: astore 7
    //   9: aload 7
    //   11: aload_1
    //   12: putfield 102	com/duapps/ad/base/case:if	Ljava/lang/String;
    //   15: aload 7
    //   17: iconst_0
    //   18: putfield 74	com/duapps/ad/base/case:for	I
    //   21: aload_0
    //   22: getfield 15	com/duapps/ad/stats/case:do	Landroid/content/Context;
    //   25: invokestatic 120	com/duapps/ad/base/goto:throws	(Landroid/content/Context;)J
    //   28: lstore_2
    //   29: invokestatic 36	java/lang/System:currentTimeMillis	()J
    //   32: lstore 4
    //   34: aconst_null
    //   35: astore 6
    //   37: aload_0
    //   38: getfield 15	com/duapps/ad/stats/case:do	Landroid/content/Context;
    //   41: invokevirtual 40	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   44: aload_0
    //   45: getfield 15	com/duapps/ad/stats/case:do	Landroid/content/Context;
    //   48: bipush 7
    //   50: invokestatic 45	com/duapps/ad/stats/DuAdCacheProvider:do	(Landroid/content/Context;I)Landroid/net/Uri;
    //   53: iconst_4
    //   54: anewarray 49	java/lang/String
    //   57: dup
    //   58: iconst_0
    //   59: ldc 76
    //   61: aastore
    //   62: dup
    //   63: iconst_1
    //   64: ldc 78
    //   66: aastore
    //   67: dup
    //   68: iconst_2
    //   69: ldc 80
    //   71: aastore
    //   72: dup
    //   73: iconst_3
    //   74: ldc 82
    //   76: aastore
    //   77: ldc -64
    //   79: iconst_2
    //   80: anewarray 49	java/lang/String
    //   83: dup
    //   84: iconst_0
    //   85: aload_1
    //   86: aastore
    //   87: dup
    //   88: iconst_1
    //   89: lload 4
    //   91: lload_2
    //   92: lsub
    //   93: invokestatic 55	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   96: aastore
    //   97: ldc 86
    //   99: invokevirtual 90	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   102: astore_1
    //   103: aload_1
    //   104: ifnull +60 -> 164
    //   107: aload_1
    //   108: invokeinterface 96 1 0
    //   113: ifeq +51 -> 164
    //   116: aload 7
    //   118: aload_1
    //   119: iconst_0
    //   120: invokeinterface 100 2 0
    //   125: putfield 70	com/duapps/ad/base/case:do	Ljava/lang/String;
    //   128: aload 7
    //   130: aload_1
    //   131: iconst_1
    //   132: invokeinterface 100 2 0
    //   137: putfield 102	com/duapps/ad/base/case:if	Ljava/lang/String;
    //   140: aload 7
    //   142: aload_1
    //   143: iconst_2
    //   144: invokeinterface 100 2 0
    //   149: putfield 104	com/duapps/ad/base/case:int	Ljava/lang/String;
    //   152: aload 7
    //   154: aload_1
    //   155: iconst_3
    //   156: invokeinterface 108 2 0
    //   161: putfield 74	com/duapps/ad/base/case:for	I
    //   164: aload_1
    //   165: ifnull +18 -> 183
    //   168: aload_1
    //   169: invokeinterface 111 1 0
    //   174: ifne +9 -> 183
    //   177: aload_1
    //   178: invokeinterface 114 1 0
    //   183: aload 7
    //   185: areturn
    //   186: astore_1
    //   187: aconst_null
    //   188: astore_1
    //   189: aload_1
    //   190: ifnull -7 -> 183
    //   193: aload_1
    //   194: invokeinterface 111 1 0
    //   199: ifne -16 -> 183
    //   202: aload_1
    //   203: invokeinterface 114 1 0
    //   208: aload 7
    //   210: areturn
    //   211: astore 7
    //   213: aload 6
    //   215: astore_1
    //   216: aload 7
    //   218: astore 6
    //   220: aload_1
    //   221: ifnull +18 -> 239
    //   224: aload_1
    //   225: invokeinterface 111 1 0
    //   230: ifne +9 -> 239
    //   233: aload_1
    //   234: invokeinterface 114 1 0
    //   239: aload 6
    //   241: athrow
    //   242: astore 6
    //   244: goto -24 -> 220
    //   247: astore 6
    //   249: goto -60 -> 189
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	252	0	this	case
    //   0	252	1	paramString	String
    //   28	64	2	l1	long
    //   32	58	4	l2	long
    //   35	205	6	localObject1	Object
    //   242	1	6	localObject2	Object
    //   247	1	6	localException	Exception
    //   7	202	7	localCase	com.duapps.ad.base.case
    //   211	6	7	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   37	103	186	java/lang/Exception
    //   37	103	211	finally
    //   107	164	242	finally
    //   107	164	247	java/lang/Exception
  }
}
