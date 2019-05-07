package com.duapps.ad.stats;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import com.duapps.ad.base.LogHelper;
import com.duapps.ad.base.SharedPrefsUtils;
import com.duapps.ad.base.Utils;
import com.duapps.ad.internal.parse.ParseResult;

public class ToolboxCacheManager
{
  private static final long CACHE_TTL = 86400000L;
  static final String TAG = ToolboxCacheManager.class.getSimpleName();
  private static ToolboxCacheManager mInstance;
  private Context mCtx;
  
  private ToolboxCacheManager(Context paramContext)
  {
    this.mCtx = paramContext;
  }
  
  private void dumpTimeOutDatas()
  {
    long l = System.currentTimeMillis();
    try
    {
      this.mCtx.getContentResolver().delete(DuAdCacheProvider.getUriByType(this.mCtx, 1), "ts<?", new String[] { String.valueOf(l - 86400000L) });
      return;
    }
    catch (Exception localException)
    {
      LogHelper.d(TAG, "dumpTimeOutDatas() exception: ", localException);
      return;
    }
    catch (Throwable localThrowable)
    {
      LogHelper.d(TAG, "dumpTimeOutDatas() del exception: ", localThrowable);
    }
  }
  
  public static ToolboxCacheManager getInstance(Context paramContext)
  {
    try
    {
      if (mInstance == null) {
        mInstance = new ToolboxCacheManager(paramContext.getApplicationContext());
      }
      paramContext = mInstance;
      return paramContext;
    }
    finally {}
  }
  
  /* Error */
  public java.util.List<com.duapps.ad.entity.BehaviorRecord> getBehaviorDataByPkg()
  {
    // Byte code:
    //   0: ldc 92
    //   2: iconst_2
    //   3: anewarray 4	java/lang/Object
    //   6: dup
    //   7: iconst_0
    //   8: ldc 94
    //   10: aastore
    //   11: dup
    //   12: iconst_1
    //   13: ldc 96
    //   15: aastore
    //   16: invokestatic 100	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   19: astore 5
    //   21: ldc 92
    //   23: iconst_2
    //   24: anewarray 4	java/lang/Object
    //   27: dup
    //   28: iconst_0
    //   29: ldc 102
    //   31: aastore
    //   32: dup
    //   33: iconst_1
    //   34: ldc 104
    //   36: aastore
    //   37: invokestatic 100	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   40: astore 6
    //   42: ldc 106
    //   44: iconst_1
    //   45: anewarray 4	java/lang/Object
    //   48: dup
    //   49: iconst_0
    //   50: ldc 94
    //   52: aastore
    //   53: invokestatic 100	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   56: astore 7
    //   58: invokestatic 42	java/lang/System:currentTimeMillis	()J
    //   61: lstore_3
    //   62: new 108	java/lang/StringBuilder
    //   65: dup
    //   66: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   69: aload 7
    //   71: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: ldc 115
    //   76: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   82: astore 8
    //   84: new 120	java/util/ArrayList
    //   87: dup
    //   88: invokespecial 121	java/util/ArrayList:<init>	()V
    //   91: astore 7
    //   93: aload_0
    //   94: getfield 31	com/duapps/ad/stats/ToolboxCacheManager:mCtx	Landroid/content/Context;
    //   97: invokevirtual 48	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   100: aload_0
    //   101: getfield 31	com/duapps/ad/stats/ToolboxCacheManager:mCtx	Landroid/content/Context;
    //   104: bipush 10
    //   106: invokestatic 54	com/duapps/ad/stats/DuAdCacheProvider:getUriByType	(Landroid/content/Context;I)Landroid/net/Uri;
    //   109: iconst_3
    //   110: anewarray 58	java/lang/String
    //   113: dup
    //   114: iconst_0
    //   115: ldc 123
    //   117: aastore
    //   118: dup
    //   119: iconst_1
    //   120: aload 5
    //   122: aastore
    //   123: dup
    //   124: iconst_2
    //   125: aload 6
    //   127: aastore
    //   128: ldc 125
    //   130: iconst_1
    //   131: anewarray 58	java/lang/String
    //   134: dup
    //   135: iconst_0
    //   136: lload_3
    //   137: ldc2_w 7
    //   140: lsub
    //   141: invokestatic 62	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   144: aastore
    //   145: aload 8
    //   147: invokevirtual 129	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   150: astore 6
    //   152: aload 6
    //   154: ifnull +221 -> 375
    //   157: aload 6
    //   159: astore 5
    //   161: aload 6
    //   163: invokeinterface 135 1 0
    //   168: ifle +207 -> 375
    //   171: aload 6
    //   173: astore 5
    //   175: aload 6
    //   177: invokeinterface 135 1 0
    //   182: bipush 10
    //   184: if_icmple +80 -> 264
    //   187: bipush 10
    //   189: istore_1
    //   190: goto +253 -> 443
    //   193: iload_2
    //   194: iload_1
    //   195: if_icmpge +155 -> 350
    //   198: iload_2
    //   199: ifne +80 -> 279
    //   202: aload 6
    //   204: astore 5
    //   206: aload 6
    //   208: invokeinterface 139 1 0
    //   213: pop
    //   214: aload 6
    //   216: astore 5
    //   218: aload 7
    //   220: new 141	com/duapps/ad/entity/BehaviorRecord
    //   223: dup
    //   224: aload 6
    //   226: iconst_0
    //   227: invokeinterface 145 2 0
    //   232: aload 6
    //   234: iconst_1
    //   235: invokeinterface 149 2 0
    //   240: aload 6
    //   242: iconst_2
    //   243: invokeinterface 149 2 0
    //   248: invokespecial 152	com/duapps/ad/entity/BehaviorRecord:<init>	(Ljava/lang/String;II)V
    //   251: invokeinterface 158 2 0
    //   256: pop
    //   257: iload_2
    //   258: iconst_1
    //   259: iadd
    //   260: istore_2
    //   261: goto -68 -> 193
    //   264: aload 6
    //   266: astore 5
    //   268: aload 6
    //   270: invokeinterface 135 1 0
    //   275: istore_1
    //   276: goto +167 -> 443
    //   279: aload 6
    //   281: astore 5
    //   283: aload 6
    //   285: invokeinterface 161 1 0
    //   290: pop
    //   291: goto -77 -> 214
    //   294: astore 7
    //   296: aload 6
    //   298: astore 5
    //   300: getstatic 24	com/duapps/ad/stats/ToolboxCacheManager:TAG	Ljava/lang/String;
    //   303: new 108	java/lang/StringBuilder
    //   306: dup
    //   307: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   310: ldc -93
    //   312: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   315: aload 7
    //   317: invokevirtual 166	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   320: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   323: invokestatic 169	com/duapps/ad/base/LogHelper:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   326: aload 6
    //   328: ifnull +20 -> 348
    //   331: aload 6
    //   333: invokeinterface 172 1 0
    //   338: ifne +10 -> 348
    //   341: aload 6
    //   343: invokeinterface 175 1 0
    //   348: aconst_null
    //   349: areturn
    //   350: aload 6
    //   352: ifnull +20 -> 372
    //   355: aload 6
    //   357: invokeinterface 172 1 0
    //   362: ifne +10 -> 372
    //   365: aload 6
    //   367: invokeinterface 175 1 0
    //   372: aload 7
    //   374: areturn
    //   375: aload 6
    //   377: ifnull -29 -> 348
    //   380: aload 6
    //   382: invokeinterface 172 1 0
    //   387: ifne -39 -> 348
    //   390: aload 6
    //   392: invokeinterface 175 1 0
    //   397: goto -49 -> 348
    //   400: astore 6
    //   402: aconst_null
    //   403: astore 5
    //   405: aload 5
    //   407: ifnull +20 -> 427
    //   410: aload 5
    //   412: invokeinterface 172 1 0
    //   417: ifne +10 -> 427
    //   420: aload 5
    //   422: invokeinterface 175 1 0
    //   427: aload 6
    //   429: athrow
    //   430: astore 6
    //   432: goto -27 -> 405
    //   435: astore 7
    //   437: aconst_null
    //   438: astore 6
    //   440: goto -144 -> 296
    //   443: iconst_0
    //   444: istore_2
    //   445: goto -252 -> 193
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	448	0	this	ToolboxCacheManager
    //   189	87	1	i	int
    //   193	252	2	j	int
    //   61	76	3	l	long
    //   19	402	5	localObject1	Object
    //   40	351	6	localObject2	Object
    //   400	28	6	localObject3	Object
    //   430	1	6	localObject4	Object
    //   438	1	6	localObject5	Object
    //   56	163	7	localObject6	Object
    //   294	79	7	localException1	Exception
    //   435	1	7	localException2	Exception
    //   82	64	8	str	String
    // Exception table:
    //   from	to	target	type
    //   161	171	294	java/lang/Exception
    //   175	187	294	java/lang/Exception
    //   206	214	294	java/lang/Exception
    //   218	257	294	java/lang/Exception
    //   268	276	294	java/lang/Exception
    //   283	291	294	java/lang/Exception
    //   93	152	400	finally
    //   161	171	430	finally
    //   175	187	430	finally
    //   206	214	430	finally
    //   218	257	430	finally
    //   268	276	430	finally
    //   283	291	430	finally
    //   300	326	430	finally
    //   93	152	435	java/lang/Exception
  }
  
  /* Error */
  public ParseResult getParseResult(String paramString)
  {
    // Byte code:
    //   0: new 181	com/duapps/ad/internal/parse/ParseResult
    //   3: dup
    //   4: invokespecial 182	com/duapps/ad/internal/parse/ParseResult:<init>	()V
    //   7: astore 6
    //   9: aload 6
    //   11: aload_1
    //   12: putfield 185	com/duapps/ad/internal/parse/ParseResult:url	Ljava/lang/String;
    //   15: aload 6
    //   17: iconst_0
    //   18: putfield 189	com/duapps/ad/internal/parse/ParseResult:type	I
    //   21: invokestatic 42	java/lang/System:currentTimeMillis	()J
    //   24: lstore_2
    //   25: aload_0
    //   26: getfield 31	com/duapps/ad/stats/ToolboxCacheManager:mCtx	Landroid/content/Context;
    //   29: invokevirtual 48	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   32: aload_0
    //   33: getfield 31	com/duapps/ad/stats/ToolboxCacheManager:mCtx	Landroid/content/Context;
    //   36: iconst_1
    //   37: invokestatic 54	com/duapps/ad/stats/DuAdCacheProvider:getUriByType	(Landroid/content/Context;I)Landroid/net/Uri;
    //   40: iconst_4
    //   41: anewarray 58	java/lang/String
    //   44: dup
    //   45: iconst_0
    //   46: ldc -65
    //   48: aastore
    //   49: dup
    //   50: iconst_1
    //   51: ldc 123
    //   53: aastore
    //   54: dup
    //   55: iconst_2
    //   56: ldc -63
    //   58: aastore
    //   59: dup
    //   60: iconst_3
    //   61: ldc -62
    //   63: aastore
    //   64: ldc -60
    //   66: iconst_2
    //   67: anewarray 58	java/lang/String
    //   70: dup
    //   71: iconst_0
    //   72: aload_1
    //   73: aastore
    //   74: dup
    //   75: iconst_1
    //   76: lload_2
    //   77: ldc2_w 7
    //   80: lsub
    //   81: invokestatic 62	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   84: aastore
    //   85: ldc -58
    //   87: invokevirtual 129	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   90: astore 4
    //   92: aload 4
    //   94: ifnull +80 -> 174
    //   97: aload 4
    //   99: astore_1
    //   100: aload 4
    //   102: invokeinterface 139 1 0
    //   107: ifeq +67 -> 174
    //   110: aload 4
    //   112: astore_1
    //   113: aload 6
    //   115: aload 4
    //   117: iconst_0
    //   118: invokeinterface 145 2 0
    //   123: putfield 185	com/duapps/ad/internal/parse/ParseResult:url	Ljava/lang/String;
    //   126: aload 4
    //   128: astore_1
    //   129: aload 6
    //   131: aload 4
    //   133: iconst_1
    //   134: invokeinterface 145 2 0
    //   139: putfield 200	com/duapps/ad/internal/parse/ParseResult:pkg	Ljava/lang/String;
    //   142: aload 4
    //   144: astore_1
    //   145: aload 6
    //   147: aload 4
    //   149: iconst_2
    //   150: invokeinterface 145 2 0
    //   155: putfield 203	com/duapps/ad/internal/parse/ParseResult:parseUrl	Ljava/lang/String;
    //   158: aload 4
    //   160: astore_1
    //   161: aload 6
    //   163: aload 4
    //   165: iconst_3
    //   166: invokeinterface 149 2 0
    //   171: putfield 189	com/duapps/ad/internal/parse/ParseResult:type	I
    //   174: aload 4
    //   176: ifnull +20 -> 196
    //   179: aload 4
    //   181: invokeinterface 172 1 0
    //   186: ifne +10 -> 196
    //   189: aload 4
    //   191: invokeinterface 175 1 0
    //   196: aload 6
    //   198: areturn
    //   199: astore 5
    //   201: aconst_null
    //   202: astore 4
    //   204: aload 4
    //   206: astore_1
    //   207: getstatic 24	com/duapps/ad/stats/ToolboxCacheManager:TAG	Ljava/lang/String;
    //   210: ldc -51
    //   212: aload 5
    //   214: invokestatic 76	com/duapps/ad/base/LogHelper:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   217: aload 4
    //   219: ifnull -23 -> 196
    //   222: aload 4
    //   224: invokeinterface 172 1 0
    //   229: ifne -33 -> 196
    //   232: aload 4
    //   234: invokeinterface 175 1 0
    //   239: aload 6
    //   241: areturn
    //   242: astore 4
    //   244: aconst_null
    //   245: astore_1
    //   246: aload_1
    //   247: ifnull +18 -> 265
    //   250: aload_1
    //   251: invokeinterface 172 1 0
    //   256: ifne +9 -> 265
    //   259: aload_1
    //   260: invokeinterface 175 1 0
    //   265: aload 4
    //   267: athrow
    //   268: astore 4
    //   270: goto -24 -> 246
    //   273: astore 5
    //   275: goto -71 -> 204
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	278	0	this	ToolboxCacheManager
    //   0	278	1	paramString	String
    //   24	53	2	l	long
    //   90	143	4	localCursor	android.database.Cursor
    //   242	24	4	localObject1	Object
    //   268	1	4	localObject2	Object
    //   199	14	5	localException1	Exception
    //   273	1	5	localException2	Exception
    //   7	233	6	localParseResult	ParseResult
    // Exception table:
    //   from	to	target	type
    //   25	92	199	java/lang/Exception
    //   25	92	242	finally
    //   100	110	268	finally
    //   113	126	268	finally
    //   129	142	268	finally
    //   145	158	268	finally
    //   161	174	268	finally
    //   207	217	268	finally
    //   100	110	273	java/lang/Exception
    //   113	126	273	java/lang/Exception
    //   129	142	273	java/lang/Exception
    //   145	158	273	java/lang/Exception
    //   161	174	273	java/lang/Exception
  }
  
  /* Error */
  public int getParseResultType(String paramString)
  {
    // Byte code:
    //   0: invokestatic 42	java/lang/System:currentTimeMillis	()J
    //   3: lstore_3
    //   4: aload_0
    //   5: getfield 31	com/duapps/ad/stats/ToolboxCacheManager:mCtx	Landroid/content/Context;
    //   8: invokevirtual 48	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   11: aload_0
    //   12: getfield 31	com/duapps/ad/stats/ToolboxCacheManager:mCtx	Landroid/content/Context;
    //   15: iconst_1
    //   16: invokestatic 54	com/duapps/ad/stats/DuAdCacheProvider:getUriByType	(Landroid/content/Context;I)Landroid/net/Uri;
    //   19: iconst_1
    //   20: anewarray 58	java/lang/String
    //   23: dup
    //   24: iconst_0
    //   25: ldc -62
    //   27: aastore
    //   28: ldc -60
    //   30: iconst_2
    //   31: anewarray 58	java/lang/String
    //   34: dup
    //   35: iconst_0
    //   36: aload_1
    //   37: aastore
    //   38: dup
    //   39: iconst_1
    //   40: lload_3
    //   41: ldc2_w 7
    //   44: lsub
    //   45: invokestatic 62	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   48: aastore
    //   49: ldc -58
    //   51: invokevirtual 129	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   54: astore 5
    //   56: aload 5
    //   58: ifnull +132 -> 190
    //   61: aload 5
    //   63: astore_1
    //   64: aload 5
    //   66: invokeinterface 139 1 0
    //   71: ifeq +119 -> 190
    //   74: aload 5
    //   76: astore_1
    //   77: aload 5
    //   79: iconst_0
    //   80: invokeinterface 149 2 0
    //   85: istore_2
    //   86: aload 5
    //   88: ifnull +20 -> 108
    //   91: aload 5
    //   93: invokeinterface 172 1 0
    //   98: ifne +10 -> 108
    //   101: aload 5
    //   103: invokeinterface 175 1 0
    //   108: iload_2
    //   109: ireturn
    //   110: astore 6
    //   112: aconst_null
    //   113: astore 5
    //   115: aload 5
    //   117: astore_1
    //   118: getstatic 24	com/duapps/ad/stats/ToolboxCacheManager:TAG	Ljava/lang/String;
    //   121: ldc -47
    //   123: aload 6
    //   125: invokestatic 76	com/duapps/ad/base/LogHelper:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   128: aload 5
    //   130: ifnull +58 -> 188
    //   133: aload 5
    //   135: invokeinterface 172 1 0
    //   140: ifne +48 -> 188
    //   143: aload 5
    //   145: invokeinterface 175 1 0
    //   150: iconst_0
    //   151: ireturn
    //   152: astore 5
    //   154: aconst_null
    //   155: astore_1
    //   156: aload_1
    //   157: ifnull +18 -> 175
    //   160: aload_1
    //   161: invokeinterface 172 1 0
    //   166: ifne +9 -> 175
    //   169: aload_1
    //   170: invokeinterface 175 1 0
    //   175: aload 5
    //   177: athrow
    //   178: astore 5
    //   180: goto -24 -> 156
    //   183: astore 6
    //   185: goto -70 -> 115
    //   188: iconst_0
    //   189: ireturn
    //   190: iconst_0
    //   191: istore_2
    //   192: goto -106 -> 86
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	195	0	this	ToolboxCacheManager
    //   0	195	1	paramString	String
    //   85	107	2	i	int
    //   3	38	3	l	long
    //   54	90	5	localCursor	android.database.Cursor
    //   152	24	5	localObject1	Object
    //   178	1	5	localObject2	Object
    //   110	14	6	localException1	Exception
    //   183	1	6	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   4	56	110	java/lang/Exception
    //   4	56	152	finally
    //   64	74	178	finally
    //   77	86	178	finally
    //   118	128	178	finally
    //   64	74	183	java/lang/Exception
    //   77	86	183	java/lang/Exception
  }
  
  /* Error */
  public ParseResult getPreparseCacheResult(String paramString)
  {
    // Byte code:
    //   0: new 181	com/duapps/ad/internal/parse/ParseResult
    //   3: dup
    //   4: invokespecial 182	com/duapps/ad/internal/parse/ParseResult:<init>	()V
    //   7: astore 8
    //   9: aload 8
    //   11: aload_1
    //   12: putfield 200	com/duapps/ad/internal/parse/ParseResult:pkg	Ljava/lang/String;
    //   15: aload 8
    //   17: iconst_0
    //   18: putfield 189	com/duapps/ad/internal/parse/ParseResult:type	I
    //   21: aload_0
    //   22: getfield 31	com/duapps/ad/stats/ToolboxCacheManager:mCtx	Landroid/content/Context;
    //   25: invokestatic 215	com/duapps/ad/base/SharedPrefsUtils:getInstance	(Landroid/content/Context;)Lcom/duapps/ad/base/SharedPrefsUtils;
    //   28: invokevirtual 218	com/duapps/ad/base/SharedPrefsUtils:getPreparseCacheTimeout	()J
    //   31: lstore_2
    //   32: invokestatic 42	java/lang/System:currentTimeMillis	()J
    //   35: lstore 4
    //   37: aload_0
    //   38: getfield 31	com/duapps/ad/stats/ToolboxCacheManager:mCtx	Landroid/content/Context;
    //   41: invokevirtual 48	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   44: aload_0
    //   45: getfield 31	com/duapps/ad/stats/ToolboxCacheManager:mCtx	Landroid/content/Context;
    //   48: bipush 9
    //   50: invokestatic 54	com/duapps/ad/stats/DuAdCacheProvider:getUriByType	(Landroid/content/Context;I)Landroid/net/Uri;
    //   53: iconst_4
    //   54: anewarray 58	java/lang/String
    //   57: dup
    //   58: iconst_0
    //   59: ldc -65
    //   61: aastore
    //   62: dup
    //   63: iconst_1
    //   64: ldc 123
    //   66: aastore
    //   67: dup
    //   68: iconst_2
    //   69: ldc -63
    //   71: aastore
    //   72: dup
    //   73: iconst_3
    //   74: ldc -62
    //   76: aastore
    //   77: ldc -36
    //   79: iconst_2
    //   80: anewarray 58	java/lang/String
    //   83: dup
    //   84: iconst_0
    //   85: aload_1
    //   86: aastore
    //   87: dup
    //   88: iconst_1
    //   89: lload 4
    //   91: lload_2
    //   92: lsub
    //   93: invokestatic 62	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   96: aastore
    //   97: ldc -58
    //   99: invokevirtual 129	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   102: astore 6
    //   104: aload 6
    //   106: ifnull +80 -> 186
    //   109: aload 6
    //   111: astore_1
    //   112: aload 6
    //   114: invokeinterface 139 1 0
    //   119: ifeq +67 -> 186
    //   122: aload 6
    //   124: astore_1
    //   125: aload 8
    //   127: aload 6
    //   129: iconst_0
    //   130: invokeinterface 145 2 0
    //   135: putfield 185	com/duapps/ad/internal/parse/ParseResult:url	Ljava/lang/String;
    //   138: aload 6
    //   140: astore_1
    //   141: aload 8
    //   143: aload 6
    //   145: iconst_1
    //   146: invokeinterface 145 2 0
    //   151: putfield 200	com/duapps/ad/internal/parse/ParseResult:pkg	Ljava/lang/String;
    //   154: aload 6
    //   156: astore_1
    //   157: aload 8
    //   159: aload 6
    //   161: iconst_2
    //   162: invokeinterface 145 2 0
    //   167: putfield 203	com/duapps/ad/internal/parse/ParseResult:parseUrl	Ljava/lang/String;
    //   170: aload 6
    //   172: astore_1
    //   173: aload 8
    //   175: aload 6
    //   177: iconst_3
    //   178: invokeinterface 149 2 0
    //   183: putfield 189	com/duapps/ad/internal/parse/ParseResult:type	I
    //   186: aload 6
    //   188: ifnull +20 -> 208
    //   191: aload 6
    //   193: invokeinterface 172 1 0
    //   198: ifne +10 -> 208
    //   201: aload 6
    //   203: invokeinterface 175 1 0
    //   208: aload 8
    //   210: areturn
    //   211: astore 7
    //   213: aconst_null
    //   214: astore 6
    //   216: aload 6
    //   218: astore_1
    //   219: getstatic 24	com/duapps/ad/stats/ToolboxCacheManager:TAG	Ljava/lang/String;
    //   222: ldc -51
    //   224: aload 7
    //   226: invokestatic 76	com/duapps/ad/base/LogHelper:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   229: aload 6
    //   231: ifnull -23 -> 208
    //   234: aload 6
    //   236: invokeinterface 172 1 0
    //   241: ifne -33 -> 208
    //   244: aload 6
    //   246: invokeinterface 175 1 0
    //   251: aload 8
    //   253: areturn
    //   254: astore 6
    //   256: aconst_null
    //   257: astore_1
    //   258: aload_1
    //   259: ifnull +18 -> 277
    //   262: aload_1
    //   263: invokeinterface 172 1 0
    //   268: ifne +9 -> 277
    //   271: aload_1
    //   272: invokeinterface 175 1 0
    //   277: aload 6
    //   279: athrow
    //   280: astore 6
    //   282: goto -24 -> 258
    //   285: astore 7
    //   287: goto -71 -> 216
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	290	0	this	ToolboxCacheManager
    //   0	290	1	paramString	String
    //   31	61	2	l1	long
    //   35	55	4	l2	long
    //   102	143	6	localCursor	android.database.Cursor
    //   254	24	6	localObject1	Object
    //   280	1	6	localObject2	Object
    //   211	14	7	localException1	Exception
    //   285	1	7	localException2	Exception
    //   7	245	8	localParseResult	ParseResult
    // Exception table:
    //   from	to	target	type
    //   37	104	211	java/lang/Exception
    //   37	104	254	finally
    //   112	122	280	finally
    //   125	138	280	finally
    //   141	154	280	finally
    //   157	170	280	finally
    //   173	186	280	finally
    //   219	229	280	finally
    //   112	122	285	java/lang/Exception
    //   125	138	285	java/lang/Exception
    //   141	154	285	java/lang/Exception
    //   157	170	285	java/lang/Exception
    //   173	186	285	java/lang/Exception
  }
  
  /* Error */
  public int getPreparseCacheResultType(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 31	com/duapps/ad/stats/ToolboxCacheManager:mCtx	Landroid/content/Context;
    //   4: invokestatic 215	com/duapps/ad/base/SharedPrefsUtils:getInstance	(Landroid/content/Context;)Lcom/duapps/ad/base/SharedPrefsUtils;
    //   7: invokevirtual 218	com/duapps/ad/base/SharedPrefsUtils:getPreparseCacheTimeout	()J
    //   10: lstore_3
    //   11: invokestatic 42	java/lang/System:currentTimeMillis	()J
    //   14: lstore 5
    //   16: aload_0
    //   17: getfield 31	com/duapps/ad/stats/ToolboxCacheManager:mCtx	Landroid/content/Context;
    //   20: invokevirtual 48	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   23: aload_0
    //   24: getfield 31	com/duapps/ad/stats/ToolboxCacheManager:mCtx	Landroid/content/Context;
    //   27: bipush 9
    //   29: invokestatic 54	com/duapps/ad/stats/DuAdCacheProvider:getUriByType	(Landroid/content/Context;I)Landroid/net/Uri;
    //   32: iconst_1
    //   33: anewarray 58	java/lang/String
    //   36: dup
    //   37: iconst_0
    //   38: ldc -62
    //   40: aastore
    //   41: ldc -36
    //   43: iconst_2
    //   44: anewarray 58	java/lang/String
    //   47: dup
    //   48: iconst_0
    //   49: aload_1
    //   50: aastore
    //   51: dup
    //   52: iconst_1
    //   53: lload 5
    //   55: lload_3
    //   56: lsub
    //   57: invokestatic 62	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   60: aastore
    //   61: ldc -58
    //   63: invokevirtual 129	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   66: astore 7
    //   68: aload 7
    //   70: ifnull +132 -> 202
    //   73: aload 7
    //   75: astore_1
    //   76: aload 7
    //   78: invokeinterface 139 1 0
    //   83: ifeq +119 -> 202
    //   86: aload 7
    //   88: astore_1
    //   89: aload 7
    //   91: iconst_0
    //   92: invokeinterface 149 2 0
    //   97: istore_2
    //   98: aload 7
    //   100: ifnull +20 -> 120
    //   103: aload 7
    //   105: invokeinterface 172 1 0
    //   110: ifne +10 -> 120
    //   113: aload 7
    //   115: invokeinterface 175 1 0
    //   120: iload_2
    //   121: ireturn
    //   122: astore 8
    //   124: aconst_null
    //   125: astore 7
    //   127: aload 7
    //   129: astore_1
    //   130: getstatic 24	com/duapps/ad/stats/ToolboxCacheManager:TAG	Ljava/lang/String;
    //   133: ldc -47
    //   135: aload 8
    //   137: invokestatic 76	com/duapps/ad/base/LogHelper:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   140: aload 7
    //   142: ifnull +58 -> 200
    //   145: aload 7
    //   147: invokeinterface 172 1 0
    //   152: ifne +48 -> 200
    //   155: aload 7
    //   157: invokeinterface 175 1 0
    //   162: iconst_0
    //   163: ireturn
    //   164: astore 7
    //   166: aconst_null
    //   167: astore_1
    //   168: aload_1
    //   169: ifnull +18 -> 187
    //   172: aload_1
    //   173: invokeinterface 172 1 0
    //   178: ifne +9 -> 187
    //   181: aload_1
    //   182: invokeinterface 175 1 0
    //   187: aload 7
    //   189: athrow
    //   190: astore 7
    //   192: goto -24 -> 168
    //   195: astore 8
    //   197: goto -70 -> 127
    //   200: iconst_0
    //   201: ireturn
    //   202: iconst_0
    //   203: istore_2
    //   204: goto -106 -> 98
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	207	0	this	ToolboxCacheManager
    //   0	207	1	paramString	String
    //   97	107	2	i	int
    //   10	46	3	l1	long
    //   14	40	5	l2	long
    //   66	90	7	localCursor	android.database.Cursor
    //   164	24	7	localObject1	Object
    //   190	1	7	localObject2	Object
    //   122	14	8	localException1	Exception
    //   195	1	8	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   16	68	122	java/lang/Exception
    //   16	68	164	finally
    //   76	86	190	finally
    //   89	98	190	finally
    //   130	140	190	finally
    //   76	86	195	java/lang/Exception
    //   89	98	195	java/lang/Exception
  }
  
  @Deprecated
  public boolean isExistInGameToolbox(String paramString)
  {
    return false;
  }
  
  public void removeInvalidBehaviorData()
  {
    long l = System.currentTimeMillis();
    try
    {
      int i = this.mCtx.getContentResolver().delete(DuAdCacheProvider.getUriByType(this.mCtx, 10), "show_ts<? ", new String[] { String.valueOf(l - 86400000L) });
      LogHelper.d(TAG, "removeInvalidBehaviorData() count =  " + i);
      return;
    }
    catch (Exception localException)
    {
      LogHelper.d(TAG, "removeInvalidBehaviorData() exception: ", localException);
      return;
    }
    catch (Throwable localThrowable)
    {
      LogHelper.d(TAG, "removeInvalidBehaviorData() del exception: ", localThrowable);
    }
  }
  
  public void removePreparseCacheData(Context paramContext, String paramString)
  {
    try
    {
      this.mCtx.getContentResolver().delete(DuAdCacheProvider.getUriByType(this.mCtx, 9), "pkg<?", new String[] { paramString });
      return;
    }
    catch (Exception paramContext)
    {
      LogHelper.d(TAG, "removePreparseCacheInstalledData exception: ", paramContext);
      return;
    }
    catch (Throwable paramContext)
    {
      LogHelper.d(TAG, "removePreparseCacheInstalledData del exception: ", paramContext);
    }
  }
  
  /* Error */
  public void removePreparseCachePackageInstalledDatas(Context paramContext)
  {
    // Byte code:
    //   0: new 120	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 121	java/util/ArrayList:<init>	()V
    //   7: astore 6
    //   9: aload_0
    //   10: getfield 31	com/duapps/ad/stats/ToolboxCacheManager:mCtx	Landroid/content/Context;
    //   13: invokevirtual 48	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   16: aload_0
    //   17: getfield 31	com/duapps/ad/stats/ToolboxCacheManager:mCtx	Landroid/content/Context;
    //   20: bipush 9
    //   22: invokestatic 54	com/duapps/ad/stats/DuAdCacheProvider:getUriByType	(Landroid/content/Context;I)Landroid/net/Uri;
    //   25: iconst_1
    //   26: anewarray 58	java/lang/String
    //   29: dup
    //   30: iconst_0
    //   31: ldc 123
    //   33: aastore
    //   34: aconst_null
    //   35: aconst_null
    //   36: aconst_null
    //   37: invokevirtual 129	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   40: astore 4
    //   42: aload 4
    //   44: ifnull +53 -> 97
    //   47: aload 4
    //   49: astore_3
    //   50: aload 4
    //   52: invokeinterface 139 1 0
    //   57: ifeq +40 -> 97
    //   60: aload 4
    //   62: astore_3
    //   63: aload 4
    //   65: iconst_0
    //   66: invokeinterface 145 2 0
    //   71: astore 5
    //   73: aload 4
    //   75: astore_3
    //   76: aload 5
    //   78: invokestatic 252	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   81: ifne +16 -> 97
    //   84: aload 4
    //   86: astore_3
    //   87: aload 6
    //   89: aload 5
    //   91: invokeinterface 158 2 0
    //   96: pop
    //   97: aload 4
    //   99: ifnull +20 -> 119
    //   102: aload 4
    //   104: invokeinterface 172 1 0
    //   109: ifne +10 -> 119
    //   112: aload 4
    //   114: invokeinterface 175 1 0
    //   119: aload 6
    //   121: invokeinterface 255 1 0
    //   126: ifle +344 -> 470
    //   129: new 120	java/util/ArrayList
    //   132: dup
    //   133: invokespecial 121	java/util/ArrayList:<init>	()V
    //   136: astore_3
    //   137: aload_1
    //   138: invokevirtual 259	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   141: astore_1
    //   142: aload_1
    //   143: ifnull +169 -> 312
    //   146: aload_1
    //   147: sipush 256
    //   150: invokevirtual 265	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   153: astore_1
    //   154: aload 6
    //   156: invokeinterface 269 1 0
    //   161: astore 4
    //   163: aload 4
    //   165: invokeinterface 274 1 0
    //   170: ifeq +142 -> 312
    //   173: aload 4
    //   175: invokeinterface 278 1 0
    //   180: checkcast 58	java/lang/String
    //   183: astore 5
    //   185: aload_1
    //   186: invokeinterface 269 1 0
    //   191: astore 6
    //   193: aload 6
    //   195: invokeinterface 274 1 0
    //   200: ifeq -37 -> 163
    //   203: aload 6
    //   205: invokeinterface 278 1 0
    //   210: checkcast 280	android/content/pm/PackageInfo
    //   213: astore 7
    //   215: aload 7
    //   217: ifnull -24 -> 193
    //   220: aload 7
    //   222: getfield 283	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   225: aload 5
    //   227: invokevirtual 286	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   230: ifeq -37 -> 193
    //   233: aload_3
    //   234: aload 5
    //   236: invokeinterface 158 2 0
    //   241: pop
    //   242: goto -49 -> 193
    //   245: astore 5
    //   247: aconst_null
    //   248: astore 4
    //   250: aload 4
    //   252: astore_3
    //   253: getstatic 24	com/duapps/ad/stats/ToolboxCacheManager:TAG	Ljava/lang/String;
    //   256: ldc -51
    //   258: aload 5
    //   260: invokestatic 76	com/duapps/ad/base/LogHelper:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   263: aload 4
    //   265: ifnull -146 -> 119
    //   268: aload 4
    //   270: invokeinterface 172 1 0
    //   275: ifne -156 -> 119
    //   278: aload 4
    //   280: invokeinterface 175 1 0
    //   285: goto -166 -> 119
    //   288: astore_1
    //   289: aconst_null
    //   290: astore_3
    //   291: aload_3
    //   292: ifnull +18 -> 310
    //   295: aload_3
    //   296: invokeinterface 172 1 0
    //   301: ifne +9 -> 310
    //   304: aload_3
    //   305: invokeinterface 175 1 0
    //   310: aload_1
    //   311: athrow
    //   312: aload_3
    //   313: invokeinterface 255 1 0
    //   318: ifle +152 -> 470
    //   321: iconst_0
    //   322: istore_2
    //   323: ldc_w 288
    //   326: astore_1
    //   327: iload_2
    //   328: aload_3
    //   329: invokeinterface 255 1 0
    //   334: if_icmpge +93 -> 427
    //   337: aload_3
    //   338: iload_2
    //   339: invokeinterface 292 2 0
    //   344: checkcast 58	java/lang/String
    //   347: astore 4
    //   349: iload_2
    //   350: ifne +42 -> 392
    //   353: new 108	java/lang/StringBuilder
    //   356: dup
    //   357: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   360: aload_1
    //   361: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   364: ldc_w 294
    //   367: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   370: aload 4
    //   372: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   375: ldc_w 294
    //   378: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   381: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   384: astore_1
    //   385: iload_2
    //   386: iconst_1
    //   387: iadd
    //   388: istore_2
    //   389: goto -62 -> 327
    //   392: new 108	java/lang/StringBuilder
    //   395: dup
    //   396: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   399: aload_1
    //   400: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   403: ldc_w 296
    //   406: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   409: aload 4
    //   411: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   414: ldc_w 294
    //   417: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   420: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   423: astore_1
    //   424: goto -39 -> 385
    //   427: new 108	java/lang/StringBuilder
    //   430: dup
    //   431: invokespecial 109	java/lang/StringBuilder:<init>	()V
    //   434: aload_1
    //   435: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   438: ldc_w 298
    //   441: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   444: invokevirtual 118	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   447: astore_1
    //   448: aload_0
    //   449: getfield 31	com/duapps/ad/stats/ToolboxCacheManager:mCtx	Landroid/content/Context;
    //   452: invokevirtual 48	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   455: aload_0
    //   456: getfield 31	com/duapps/ad/stats/ToolboxCacheManager:mCtx	Landroid/content/Context;
    //   459: bipush 9
    //   461: invokestatic 54	com/duapps/ad/stats/DuAdCacheProvider:getUriByType	(Landroid/content/Context;I)Landroid/net/Uri;
    //   464: aload_1
    //   465: aconst_null
    //   466: invokevirtual 68	android/content/ContentResolver:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   469: pop
    //   470: return
    //   471: astore_1
    //   472: getstatic 24	com/duapps/ad/stats/ToolboxCacheManager:TAG	Ljava/lang/String;
    //   475: ldc_w 300
    //   478: aload_1
    //   479: invokestatic 76	com/duapps/ad/base/LogHelper:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   482: return
    //   483: astore_1
    //   484: getstatic 24	com/duapps/ad/stats/ToolboxCacheManager:TAG	Ljava/lang/String;
    //   487: ldc_w 302
    //   490: aload_1
    //   491: invokestatic 76	com/duapps/ad/base/LogHelper:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   494: return
    //   495: astore_1
    //   496: goto -205 -> 291
    //   499: astore 5
    //   501: goto -251 -> 250
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	504	0	this	ToolboxCacheManager
    //   0	504	1	paramContext	Context
    //   322	67	2	i	int
    //   49	289	3	localObject1	Object
    //   40	370	4	localObject2	Object
    //   71	164	5	str	String
    //   245	14	5	localException1	Exception
    //   499	1	5	localException2	Exception
    //   7	197	6	localObject3	Object
    //   213	8	7	localPackageInfo	android.content.pm.PackageInfo
    // Exception table:
    //   from	to	target	type
    //   9	42	245	java/lang/Exception
    //   9	42	288	finally
    //   448	470	471	java/lang/Exception
    //   448	470	483	java/lang/Throwable
    //   50	60	495	finally
    //   63	73	495	finally
    //   76	84	495	finally
    //   87	97	495	finally
    //   253	263	495	finally
    //   50	60	499	java/lang/Exception
    //   63	73	499	java/lang/Exception
    //   76	84	499	java/lang/Exception
    //   87	97	499	java/lang/Exception
  }
  
  public void removePreparseCacheTimeOutDatas()
  {
    long l1 = SharedPrefsUtils.getInstance(this.mCtx).getPreparseCacheTimeout();
    long l2 = System.currentTimeMillis();
    try
    {
      this.mCtx.getContentResolver().delete(DuAdCacheProvider.getUriByType(this.mCtx, 9), "ts<?", new String[] { String.valueOf(l2 - l1) });
      return;
    }
    catch (Exception localException)
    {
      LogHelper.d(TAG, "removePreparseCacheTimeOutDatas exception: ", localException);
      return;
    }
    catch (Throwable localThrowable)
    {
      LogHelper.d(TAG, "removePreparseCacheTimeOutDatas del exception: ", localThrowable);
    }
  }
  
  public void saveClickTimestamp(ToolDataWrapper paramToolDataWrapper)
  {
    ContentValues localContentValues = new ContentValues(1);
    localContentValues.put("click_ts", Long.valueOf(System.currentTimeMillis()));
    String str = paramToolDataWrapper.getLogId();
    long l = paramToolDataWrapper.getToolDataId();
    try
    {
      int i = this.mCtx.getContentResolver().update(DuAdCacheProvider.getUriByType(this.mCtx, 10), localContentValues, "logid=? AND adid = ?", new String[] { str, String.valueOf(l) });
      LogHelper.d(TAG, "saveClickTimestamp() update = " + i);
      return;
    }
    catch (Exception paramToolDataWrapper)
    {
      LogHelper.d(TAG, "saveClickTimestamp() exception :" + paramToolDataWrapper);
    }
  }
  
  public void saveParseResult(ParseResult paramParseResult)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("_url", paramParseResult.url);
    localContentValues.put("pkg", paramParseResult.pkg);
    localContentValues.put("p_url", paramParseResult.parseUrl);
    localContentValues.put("type", Integer.valueOf(paramParseResult.type));
    localContentValues.put("ts", Long.valueOf(paramParseResult.timestamp));
    String str = paramParseResult.url;
    LogHelper.d("parseResult", "savePreparseCacheResult : " + paramParseResult.parseUrl + "," + paramParseResult.type);
    try
    {
      if (this.mCtx.getContentResolver().update(DuAdCacheProvider.getUriByType(this.mCtx, 1), localContentValues, "_url = ?", new String[] { str }) < 1) {
        this.mCtx.getContentResolver().insert(DuAdCacheProvider.getUriByType(this.mCtx, 1), localContentValues);
      }
      dumpTimeOutDatas();
      return;
    }
    catch (Exception paramParseResult)
    {
      LogHelper.d(TAG, "saveParseResult() exception: ", paramParseResult);
    }
  }
  
  public void savePreparseCacheResult(ParseResult paramParseResult)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("_url", paramParseResult.url);
    localContentValues.put("pkg", paramParseResult.pkg);
    localContentValues.put("p_url", paramParseResult.parseUrl);
    localContentValues.put("type", Integer.valueOf(paramParseResult.type));
    localContentValues.put("ts", Long.valueOf(paramParseResult.timestamp));
    String str = paramParseResult.url;
    LogHelper.d("parseResult", "savePreparseCacheResult : " + paramParseResult.parseUrl + "," + paramParseResult.type);
    try
    {
      if (this.mCtx.getContentResolver().update(DuAdCacheProvider.getUriByType(this.mCtx, 9), localContentValues, "_url = ?", new String[] { str }) < 1) {
        this.mCtx.getContentResolver().insert(DuAdCacheProvider.getUriByType(this.mCtx, 9), localContentValues);
      }
      return;
    }
    catch (Exception paramParseResult)
    {
      LogHelper.d(TAG, "saveParseResult() exception: ", paramParseResult);
    }
  }
  
  public void saveShowTimestamp(ToolDataWrapper paramToolDataWrapper)
  {
    ContentValues localContentValues = new ContentValues(5);
    localContentValues.put("logid", paramToolDataWrapper.getLogId());
    localContentValues.put("adid", Long.valueOf(paramToolDataWrapper.getToolDataId()));
    localContentValues.put("pkg", Utils.encode(paramToolDataWrapper.getPkgName()));
    localContentValues.put("show_ts", Long.valueOf(System.currentTimeMillis()));
    String str = paramToolDataWrapper.getLogId();
    long l = paramToolDataWrapper.getToolDataId();
    try
    {
      int i = this.mCtx.getContentResolver().update(DuAdCacheProvider.getUriByType(this.mCtx, 10), localContentValues, "logid=? AND adid = ?", new String[] { str, String.valueOf(l) });
      if (i < 1) {
        this.mCtx.getContentResolver().insert(DuAdCacheProvider.getUriByType(this.mCtx, 10), localContentValues);
      }
      LogHelper.d(TAG, "saveShowTimestamp() update = " + i);
      return;
    }
    catch (Exception paramToolDataWrapper)
    {
      LogHelper.d(TAG, "saveShowTimestamp() exception :" + paramToolDataWrapper);
    }
  }
}
