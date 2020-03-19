package com.duapps.ad.stats;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import com.duapps.ad.base.k;
import com.duapps.ad.base.n;

public class i
{
  private static i jdField_if;
  private Context jdField_do;
  
  private i(Context paramContext)
  {
    this.jdField_do = paramContext;
  }
  
  public static i jdMethod_do(Context paramContext)
  {
    try
    {
      if (if == null) {
        if = new i(paramContext.getApplicationContext());
      }
      paramContext = if;
      return paramContext;
    }
    finally {}
  }
  
  private void jdMethod_if()
  {
    long l = System.currentTimeMillis();
    try
    {
      this.jdField_do.getContentResolver().delete(DuAdCacheProvider.jdMethod_do(this.jdField_do, 1), "ts<?", new String[] { String.valueOf(l - 86400000L) });
      return;
    }
    catch (Exception localException)
    {
      com.duapps.ad.base.i.jdMethod_if("ToolboxCacheMgr", "dumpTimeOutDatas() exception: ", localException);
    }
  }
  
  /* Error */
  public k jdMethod_do(String paramString)
  {
    // Byte code:
    //   0: new 72	com/duapps/ad/base/k
    //   3: dup
    //   4: invokespecial 73	com/duapps/ad/base/k:<init>	()V
    //   7: astore 6
    //   9: aload 6
    //   11: aload_1
    //   12: putfield 76	com/duapps/ad/base/k:do	Ljava/lang/String;
    //   15: aload 6
    //   17: iconst_0
    //   18: putfield 80	com/duapps/ad/base/k:for	I
    //   21: invokestatic 35	java/lang/System:currentTimeMillis	()J
    //   24: lstore_2
    //   25: aload_0
    //   26: getfield 15	com/duapps/ad/stats/i:do	Landroid/content/Context;
    //   29: invokevirtual 39	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   32: aload_0
    //   33: getfield 15	com/duapps/ad/stats/i:do	Landroid/content/Context;
    //   36: iconst_1
    //   37: invokestatic 44	com/duapps/ad/stats/DuAdCacheProvider:do	(Landroid/content/Context;I)Landroid/net/Uri;
    //   40: iconst_4
    //   41: anewarray 48	java/lang/String
    //   44: dup
    //   45: iconst_0
    //   46: ldc 82
    //   48: aastore
    //   49: dup
    //   50: iconst_1
    //   51: ldc 84
    //   53: aastore
    //   54: dup
    //   55: iconst_2
    //   56: ldc 86
    //   58: aastore
    //   59: dup
    //   60: iconst_3
    //   61: ldc 88
    //   63: aastore
    //   64: ldc 90
    //   66: iconst_2
    //   67: anewarray 48	java/lang/String
    //   70: dup
    //   71: iconst_0
    //   72: aload_1
    //   73: aastore
    //   74: dup
    //   75: iconst_1
    //   76: lload_2
    //   77: ldc2_w 49
    //   80: lsub
    //   81: invokestatic 54	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   84: aastore
    //   85: ldc 92
    //   87: invokevirtual 96	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   90: astore 4
    //   92: aload 4
    //   94: ifnull +80 -> 174
    //   97: aload 4
    //   99: astore_1
    //   100: aload 4
    //   102: invokeinterface 102 1 0
    //   107: ifeq +67 -> 174
    //   110: aload 4
    //   112: astore_1
    //   113: aload 6
    //   115: aload 4
    //   117: iconst_0
    //   118: invokeinterface 106 2 0
    //   123: putfield 76	com/duapps/ad/base/k:do	Ljava/lang/String;
    //   126: aload 4
    //   128: astore_1
    //   129: aload 6
    //   131: aload 4
    //   133: iconst_1
    //   134: invokeinterface 106 2 0
    //   139: putfield 108	com/duapps/ad/base/k:if	Ljava/lang/String;
    //   142: aload 4
    //   144: astore_1
    //   145: aload 6
    //   147: aload 4
    //   149: iconst_2
    //   150: invokeinterface 106 2 0
    //   155: putfield 111	com/duapps/ad/base/k:int	Ljava/lang/String;
    //   158: aload 4
    //   160: astore_1
    //   161: aload 6
    //   163: aload 4
    //   165: iconst_3
    //   166: invokeinterface 115 2 0
    //   171: putfield 80	com/duapps/ad/base/k:for	I
    //   174: aload 4
    //   176: ifnull +20 -> 196
    //   179: aload 4
    //   181: invokeinterface 118 1 0
    //   186: ifne +10 -> 196
    //   189: aload 4
    //   191: invokeinterface 121 1 0
    //   196: aload 6
    //   198: areturn
    //   199: astore 5
    //   201: aconst_null
    //   202: astore 4
    //   204: aload 4
    //   206: astore_1
    //   207: ldc 62
    //   209: ldc 123
    //   211: aload 5
    //   213: invokestatic 69	com/duapps/ad/base/i:if	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   216: aload 4
    //   218: ifnull -22 -> 196
    //   221: aload 4
    //   223: invokeinterface 118 1 0
    //   228: ifne -32 -> 196
    //   231: aload 4
    //   233: invokeinterface 121 1 0
    //   238: aload 6
    //   240: areturn
    //   241: astore 4
    //   243: aconst_null
    //   244: astore_1
    //   245: aload_1
    //   246: ifnull +18 -> 264
    //   249: aload_1
    //   250: invokeinterface 118 1 0
    //   255: ifne +9 -> 264
    //   258: aload_1
    //   259: invokeinterface 121 1 0
    //   264: aload 4
    //   266: athrow
    //   267: astore 4
    //   269: goto -24 -> 245
    //   272: astore 5
    //   274: goto -70 -> 204
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	277	0	this	i
    //   0	277	1	paramString	String
    //   24	53	2	l	long
    //   90	142	4	localCursor	android.database.Cursor
    //   241	24	4	localObject1	Object
    //   267	1	4	localObject2	Object
    //   199	13	5	localException1	Exception
    //   272	1	5	localException2	Exception
    //   7	232	6	localK	k
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
  
  public void jdMethod_do()
  {
    long l1 = n.jdMethod_goto(this.jdField_do);
    long l2 = System.currentTimeMillis();
    try
    {
      this.jdField_do.getContentResolver().delete(DuAdCacheProvider.jdMethod_do(this.jdField_do, 7), "ts<?", new String[] { String.valueOf(l2 - l1) });
      return;
    }
    catch (Exception localException)
    {
      com.duapps.ad.base.i.jdMethod_if("ToolboxCacheMgr", "removePreparseCacheTimeOutDatas exception: ", localException);
      return;
    }
    catch (Throwable localThrowable)
    {
      com.duapps.ad.base.i.jdMethod_if("ToolboxCacheMgr", "removePreparseCacheTimeOutDatas del exception: ", localThrowable);
    }
  }
  
  public void jdMethod_do(k paramK)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("_url", paramK.jdField_do);
    localContentValues.put("pkg", paramK.jdField_if);
    localContentValues.put("p_url", paramK.jdField_int);
    localContentValues.put("type", Integer.valueOf(paramK.jdField_for));
    localContentValues.put("ts", Long.valueOf(paramK.jdField_new));
    paramK = paramK.jdField_do;
    try
    {
      if (this.jdField_do.getContentResolver().update(DuAdCacheProvider.jdMethod_do(this.jdField_do, 1), localContentValues, "_url = ?", new String[] { paramK }) < 1) {
        this.jdField_do.getContentResolver().insert(DuAdCacheProvider.jdMethod_do(this.jdField_do, 1), localContentValues);
      }
      jdMethod_if();
      return;
    }
    catch (Exception paramK)
    {
      com.duapps.ad.base.i.jdMethod_if("ToolboxCacheMgr", "saveParseResult() exception: ", paramK);
    }
  }
  
  /* Error */
  public int jdMethod_for(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 15	com/duapps/ad/stats/i:do	Landroid/content/Context;
    //   4: invokestatic 131	com/duapps/ad/base/n:goto	(Landroid/content/Context;)J
    //   7: lstore_3
    //   8: invokestatic 35	java/lang/System:currentTimeMillis	()J
    //   11: lstore 5
    //   13: aload_0
    //   14: getfield 15	com/duapps/ad/stats/i:do	Landroid/content/Context;
    //   17: invokevirtual 39	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: aload_0
    //   21: getfield 15	com/duapps/ad/stats/i:do	Landroid/content/Context;
    //   24: bipush 7
    //   26: invokestatic 44	com/duapps/ad/stats/DuAdCacheProvider:do	(Landroid/content/Context;I)Landroid/net/Uri;
    //   29: iconst_1
    //   30: anewarray 48	java/lang/String
    //   33: dup
    //   34: iconst_0
    //   35: ldc 88
    //   37: aastore
    //   38: ldc -74
    //   40: iconst_2
    //   41: anewarray 48	java/lang/String
    //   44: dup
    //   45: iconst_0
    //   46: aload_1
    //   47: aastore
    //   48: dup
    //   49: iconst_1
    //   50: lload 5
    //   52: lload_3
    //   53: lsub
    //   54: invokestatic 54	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   57: aastore
    //   58: ldc 92
    //   60: invokevirtual 96	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   63: astore 7
    //   65: aload 7
    //   67: ifnull +131 -> 198
    //   70: aload 7
    //   72: astore_1
    //   73: aload 7
    //   75: invokeinterface 102 1 0
    //   80: ifeq +118 -> 198
    //   83: aload 7
    //   85: astore_1
    //   86: aload 7
    //   88: iconst_0
    //   89: invokeinterface 115 2 0
    //   94: istore_2
    //   95: aload 7
    //   97: ifnull +20 -> 117
    //   100: aload 7
    //   102: invokeinterface 118 1 0
    //   107: ifne +10 -> 117
    //   110: aload 7
    //   112: invokeinterface 121 1 0
    //   117: iload_2
    //   118: ireturn
    //   119: astore 8
    //   121: aconst_null
    //   122: astore 7
    //   124: aload 7
    //   126: astore_1
    //   127: ldc 62
    //   129: ldc -72
    //   131: aload 8
    //   133: invokestatic 69	com/duapps/ad/base/i:if	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   136: aload 7
    //   138: ifnull +58 -> 196
    //   141: aload 7
    //   143: invokeinterface 118 1 0
    //   148: ifne +48 -> 196
    //   151: aload 7
    //   153: invokeinterface 121 1 0
    //   158: iconst_0
    //   159: ireturn
    //   160: astore 7
    //   162: aconst_null
    //   163: astore_1
    //   164: aload_1
    //   165: ifnull +18 -> 183
    //   168: aload_1
    //   169: invokeinterface 118 1 0
    //   174: ifne +9 -> 183
    //   177: aload_1
    //   178: invokeinterface 121 1 0
    //   183: aload 7
    //   185: athrow
    //   186: astore 7
    //   188: goto -24 -> 164
    //   191: astore 8
    //   193: goto -69 -> 124
    //   196: iconst_0
    //   197: ireturn
    //   198: iconst_0
    //   199: istore_2
    //   200: goto -105 -> 95
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	203	0	this	i
    //   0	203	1	paramString	String
    //   94	106	2	i	int
    //   7	46	3	l1	long
    //   11	40	5	l2	long
    //   63	89	7	localCursor	android.database.Cursor
    //   160	24	7	localObject1	Object
    //   186	1	7	localObject2	Object
    //   119	13	8	localException1	Exception
    //   191	1	8	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   13	65	119	java/lang/Exception
    //   13	65	160	finally
    //   73	83	186	finally
    //   86	95	186	finally
    //   127	136	186	finally
    //   73	83	191	java/lang/Exception
    //   86	95	191	java/lang/Exception
  }
  
  /* Error */
  public int jdMethod_if(String paramString)
  {
    // Byte code:
    //   0: invokestatic 35	java/lang/System:currentTimeMillis	()J
    //   3: lstore_3
    //   4: aload_0
    //   5: getfield 15	com/duapps/ad/stats/i:do	Landroid/content/Context;
    //   8: invokevirtual 39	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   11: aload_0
    //   12: getfield 15	com/duapps/ad/stats/i:do	Landroid/content/Context;
    //   15: iconst_1
    //   16: invokestatic 44	com/duapps/ad/stats/DuAdCacheProvider:do	(Landroid/content/Context;I)Landroid/net/Uri;
    //   19: iconst_1
    //   20: anewarray 48	java/lang/String
    //   23: dup
    //   24: iconst_0
    //   25: ldc 88
    //   27: aastore
    //   28: ldc 90
    //   30: iconst_2
    //   31: anewarray 48	java/lang/String
    //   34: dup
    //   35: iconst_0
    //   36: aload_1
    //   37: aastore
    //   38: dup
    //   39: iconst_1
    //   40: lload_3
    //   41: ldc2_w 49
    //   44: lsub
    //   45: invokestatic 54	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   48: aastore
    //   49: ldc 92
    //   51: invokevirtual 96	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   54: astore 5
    //   56: aload 5
    //   58: ifnull +131 -> 189
    //   61: aload 5
    //   63: astore_1
    //   64: aload 5
    //   66: invokeinterface 102 1 0
    //   71: ifeq +118 -> 189
    //   74: aload 5
    //   76: astore_1
    //   77: aload 5
    //   79: iconst_0
    //   80: invokeinterface 115 2 0
    //   85: istore_2
    //   86: aload 5
    //   88: ifnull +20 -> 108
    //   91: aload 5
    //   93: invokeinterface 118 1 0
    //   98: ifne +10 -> 108
    //   101: aload 5
    //   103: invokeinterface 121 1 0
    //   108: iload_2
    //   109: ireturn
    //   110: astore 6
    //   112: aconst_null
    //   113: astore 5
    //   115: aload 5
    //   117: astore_1
    //   118: ldc 62
    //   120: ldc -72
    //   122: aload 6
    //   124: invokestatic 69	com/duapps/ad/base/i:if	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   127: aload 5
    //   129: ifnull +58 -> 187
    //   132: aload 5
    //   134: invokeinterface 118 1 0
    //   139: ifne +48 -> 187
    //   142: aload 5
    //   144: invokeinterface 121 1 0
    //   149: iconst_0
    //   150: ireturn
    //   151: astore 5
    //   153: aconst_null
    //   154: astore_1
    //   155: aload_1
    //   156: ifnull +18 -> 174
    //   159: aload_1
    //   160: invokeinterface 118 1 0
    //   165: ifne +9 -> 174
    //   168: aload_1
    //   169: invokeinterface 121 1 0
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
    //   0	194	0	this	i
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
  public void jdMethod_if(Context paramContext)
  {
    // Byte code:
    //   0: new 186	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 187	java/util/ArrayList:<init>	()V
    //   7: astore 6
    //   9: aload_0
    //   10: getfield 15	com/duapps/ad/stats/i:do	Landroid/content/Context;
    //   13: invokevirtual 39	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   16: aload_0
    //   17: getfield 15	com/duapps/ad/stats/i:do	Landroid/content/Context;
    //   20: bipush 7
    //   22: invokestatic 44	com/duapps/ad/stats/DuAdCacheProvider:do	(Landroid/content/Context;I)Landroid/net/Uri;
    //   25: iconst_1
    //   26: anewarray 48	java/lang/String
    //   29: dup
    //   30: iconst_0
    //   31: ldc 84
    //   33: aastore
    //   34: aconst_null
    //   35: aconst_null
    //   36: aconst_null
    //   37: invokevirtual 96	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   40: astore 4
    //   42: aload 4
    //   44: ifnull +53 -> 97
    //   47: aload 4
    //   49: astore_3
    //   50: aload 4
    //   52: invokeinterface 102 1 0
    //   57: ifeq +40 -> 97
    //   60: aload 4
    //   62: astore_3
    //   63: aload 4
    //   65: iconst_0
    //   66: invokeinterface 106 2 0
    //   71: astore 5
    //   73: aload 4
    //   75: astore_3
    //   76: aload 5
    //   78: invokestatic 193	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   81: ifne +16 -> 97
    //   84: aload 4
    //   86: astore_3
    //   87: aload 6
    //   89: aload 5
    //   91: invokeinterface 199 2 0
    //   96: pop
    //   97: aload 4
    //   99: ifnull +20 -> 119
    //   102: aload 4
    //   104: invokeinterface 118 1 0
    //   109: ifne +10 -> 119
    //   112: aload 4
    //   114: invokeinterface 121 1 0
    //   119: aload 6
    //   121: invokeinterface 203 1 0
    //   126: ifle +338 -> 464
    //   129: new 186	java/util/ArrayList
    //   132: dup
    //   133: invokespecial 187	java/util/ArrayList:<init>	()V
    //   136: astore_3
    //   137: aload_1
    //   138: invokevirtual 207	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   141: astore_1
    //   142: aload_1
    //   143: ifnull +168 -> 311
    //   146: aload_1
    //   147: sipush 256
    //   150: invokevirtual 213	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   153: astore_1
    //   154: aload 6
    //   156: invokeinterface 217 1 0
    //   161: astore 4
    //   163: aload 4
    //   165: invokeinterface 222 1 0
    //   170: ifeq +141 -> 311
    //   173: aload 4
    //   175: invokeinterface 226 1 0
    //   180: checkcast 48	java/lang/String
    //   183: astore 5
    //   185: aload_1
    //   186: invokeinterface 217 1 0
    //   191: astore 6
    //   193: aload 6
    //   195: invokeinterface 222 1 0
    //   200: ifeq -37 -> 163
    //   203: aload 6
    //   205: invokeinterface 226 1 0
    //   210: checkcast 228	android/content/pm/PackageInfo
    //   213: astore 7
    //   215: aload 7
    //   217: ifnull -24 -> 193
    //   220: aload 7
    //   222: getfield 231	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   225: aload 5
    //   227: invokevirtual 234	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   230: ifeq -37 -> 193
    //   233: aload_3
    //   234: aload 5
    //   236: invokeinterface 199 2 0
    //   241: pop
    //   242: goto -49 -> 193
    //   245: astore 5
    //   247: aconst_null
    //   248: astore 4
    //   250: aload 4
    //   252: astore_3
    //   253: ldc 62
    //   255: ldc 123
    //   257: aload 5
    //   259: invokestatic 69	com/duapps/ad/base/i:if	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   262: aload 4
    //   264: ifnull -145 -> 119
    //   267: aload 4
    //   269: invokeinterface 118 1 0
    //   274: ifne -155 -> 119
    //   277: aload 4
    //   279: invokeinterface 121 1 0
    //   284: goto -165 -> 119
    //   287: astore_1
    //   288: aconst_null
    //   289: astore_3
    //   290: aload_3
    //   291: ifnull +18 -> 309
    //   294: aload_3
    //   295: invokeinterface 118 1 0
    //   300: ifne +9 -> 309
    //   303: aload_3
    //   304: invokeinterface 121 1 0
    //   309: aload_1
    //   310: athrow
    //   311: aload_3
    //   312: invokeinterface 203 1 0
    //   317: ifle +147 -> 464
    //   320: iconst_0
    //   321: istore_2
    //   322: ldc -20
    //   324: astore_1
    //   325: iload_2
    //   326: aload_3
    //   327: invokeinterface 203 1 0
    //   332: if_icmpge +89 -> 421
    //   335: aload_3
    //   336: iload_2
    //   337: invokeinterface 240 2 0
    //   342: checkcast 48	java/lang/String
    //   345: astore 4
    //   347: iload_2
    //   348: ifne +40 -> 388
    //   351: new 242	java/lang/StringBuilder
    //   354: dup
    //   355: invokespecial 243	java/lang/StringBuilder:<init>	()V
    //   358: aload_1
    //   359: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   362: ldc -7
    //   364: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   367: aload 4
    //   369: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   372: ldc -7
    //   374: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   377: invokevirtual 253	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   380: astore_1
    //   381: iload_2
    //   382: iconst_1
    //   383: iadd
    //   384: istore_2
    //   385: goto -60 -> 325
    //   388: new 242	java/lang/StringBuilder
    //   391: dup
    //   392: invokespecial 243	java/lang/StringBuilder:<init>	()V
    //   395: aload_1
    //   396: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   399: ldc -1
    //   401: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   404: aload 4
    //   406: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   409: ldc -7
    //   411: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   414: invokevirtual 253	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   417: astore_1
    //   418: goto -37 -> 381
    //   421: new 242	java/lang/StringBuilder
    //   424: dup
    //   425: invokespecial 243	java/lang/StringBuilder:<init>	()V
    //   428: aload_1
    //   429: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   432: ldc_w 257
    //   435: invokevirtual 247	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   438: invokevirtual 253	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   441: astore_1
    //   442: aload_0
    //   443: getfield 15	com/duapps/ad/stats/i:do	Landroid/content/Context;
    //   446: invokevirtual 39	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   449: aload_0
    //   450: getfield 15	com/duapps/ad/stats/i:do	Landroid/content/Context;
    //   453: bipush 7
    //   455: invokestatic 44	com/duapps/ad/stats/DuAdCacheProvider:do	(Landroid/content/Context;I)Landroid/net/Uri;
    //   458: aload_1
    //   459: aconst_null
    //   460: invokevirtual 60	android/content/ContentResolver:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   463: pop
    //   464: return
    //   465: astore_1
    //   466: ldc 62
    //   468: ldc_w 259
    //   471: aload_1
    //   472: invokestatic 69	com/duapps/ad/base/i:if	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   475: return
    //   476: astore_1
    //   477: ldc 62
    //   479: ldc_w 261
    //   482: aload_1
    //   483: invokestatic 69	com/duapps/ad/base/i:if	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   486: return
    //   487: astore_1
    //   488: goto -198 -> 290
    //   491: astore 5
    //   493: goto -243 -> 250
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	496	0	this	i
    //   0	496	1	paramContext	Context
    //   321	64	2	i	int
    //   49	287	3	localObject1	Object
    //   40	365	4	localObject2	Object
    //   71	164	5	str	String
    //   245	13	5	localException1	Exception
    //   491	1	5	localException2	Exception
    //   7	197	6	localObject3	Object
    //   213	8	7	localPackageInfo	android.content.pm.PackageInfo
    // Exception table:
    //   from	to	target	type
    //   9	42	245	java/lang/Exception
    //   9	42	287	finally
    //   442	464	465	java/lang/Exception
    //   442	464	476	java/lang/Throwable
    //   50	60	487	finally
    //   63	73	487	finally
    //   76	84	487	finally
    //   87	97	487	finally
    //   253	262	487	finally
    //   50	60	491	java/lang/Exception
    //   63	73	491	java/lang/Exception
    //   76	84	491	java/lang/Exception
    //   87	97	491	java/lang/Exception
  }
  
  public void jdMethod_if(k paramK)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("_url", paramK.jdField_do);
    localContentValues.put("pkg", paramK.jdField_if);
    localContentValues.put("p_url", paramK.jdField_int);
    localContentValues.put("type", Integer.valueOf(paramK.jdField_for));
    localContentValues.put("ts", Long.valueOf(paramK.jdField_new));
    paramK = paramK.jdField_do;
    try
    {
      if (this.jdField_do.getContentResolver().update(DuAdCacheProvider.jdMethod_do(this.jdField_do, 7), localContentValues, "_url = ?", new String[] { paramK }) < 1) {
        this.jdField_do.getContentResolver().insert(DuAdCacheProvider.jdMethod_do(this.jdField_do, 7), localContentValues);
      }
      return;
    }
    catch (Exception paramK)
    {
      com.duapps.ad.base.i.jdMethod_if("ToolboxCacheMgr", "saveParseResult() exception: ", paramK);
    }
  }
  
  /* Error */
  public k jdMethod_int(String paramString)
  {
    // Byte code:
    //   0: new 72	com/duapps/ad/base/k
    //   3: dup
    //   4: invokespecial 73	com/duapps/ad/base/k:<init>	()V
    //   7: astore 8
    //   9: aload 8
    //   11: aload_1
    //   12: putfield 108	com/duapps/ad/base/k:if	Ljava/lang/String;
    //   15: aload 8
    //   17: iconst_0
    //   18: putfield 80	com/duapps/ad/base/k:for	I
    //   21: aload_0
    //   22: getfield 15	com/duapps/ad/stats/i:do	Landroid/content/Context;
    //   25: invokestatic 131	com/duapps/ad/base/n:goto	(Landroid/content/Context;)J
    //   28: lstore_2
    //   29: invokestatic 35	java/lang/System:currentTimeMillis	()J
    //   32: lstore 4
    //   34: aload_0
    //   35: getfield 15	com/duapps/ad/stats/i:do	Landroid/content/Context;
    //   38: invokevirtual 39	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   41: aload_0
    //   42: getfield 15	com/duapps/ad/stats/i:do	Landroid/content/Context;
    //   45: bipush 7
    //   47: invokestatic 44	com/duapps/ad/stats/DuAdCacheProvider:do	(Landroid/content/Context;I)Landroid/net/Uri;
    //   50: iconst_4
    //   51: anewarray 48	java/lang/String
    //   54: dup
    //   55: iconst_0
    //   56: ldc 82
    //   58: aastore
    //   59: dup
    //   60: iconst_1
    //   61: ldc 84
    //   63: aastore
    //   64: dup
    //   65: iconst_2
    //   66: ldc 86
    //   68: aastore
    //   69: dup
    //   70: iconst_3
    //   71: ldc 88
    //   73: aastore
    //   74: ldc -74
    //   76: iconst_2
    //   77: anewarray 48	java/lang/String
    //   80: dup
    //   81: iconst_0
    //   82: aload_1
    //   83: aastore
    //   84: dup
    //   85: iconst_1
    //   86: lload 4
    //   88: lload_2
    //   89: lsub
    //   90: invokestatic 54	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   93: aastore
    //   94: ldc 92
    //   96: invokevirtual 96	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   99: astore 6
    //   101: aload 6
    //   103: ifnull +80 -> 183
    //   106: aload 6
    //   108: astore_1
    //   109: aload 6
    //   111: invokeinterface 102 1 0
    //   116: ifeq +67 -> 183
    //   119: aload 6
    //   121: astore_1
    //   122: aload 8
    //   124: aload 6
    //   126: iconst_0
    //   127: invokeinterface 106 2 0
    //   132: putfield 76	com/duapps/ad/base/k:do	Ljava/lang/String;
    //   135: aload 6
    //   137: astore_1
    //   138: aload 8
    //   140: aload 6
    //   142: iconst_1
    //   143: invokeinterface 106 2 0
    //   148: putfield 108	com/duapps/ad/base/k:if	Ljava/lang/String;
    //   151: aload 6
    //   153: astore_1
    //   154: aload 8
    //   156: aload 6
    //   158: iconst_2
    //   159: invokeinterface 106 2 0
    //   164: putfield 111	com/duapps/ad/base/k:int	Ljava/lang/String;
    //   167: aload 6
    //   169: astore_1
    //   170: aload 8
    //   172: aload 6
    //   174: iconst_3
    //   175: invokeinterface 115 2 0
    //   180: putfield 80	com/duapps/ad/base/k:for	I
    //   183: aload 6
    //   185: ifnull +20 -> 205
    //   188: aload 6
    //   190: invokeinterface 118 1 0
    //   195: ifne +10 -> 205
    //   198: aload 6
    //   200: invokeinterface 121 1 0
    //   205: aload 8
    //   207: areturn
    //   208: astore 7
    //   210: aconst_null
    //   211: astore 6
    //   213: aload 6
    //   215: astore_1
    //   216: ldc 62
    //   218: ldc 123
    //   220: aload 7
    //   222: invokestatic 69	com/duapps/ad/base/i:if	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   225: aload 6
    //   227: ifnull -22 -> 205
    //   230: aload 6
    //   232: invokeinterface 118 1 0
    //   237: ifne -32 -> 205
    //   240: aload 6
    //   242: invokeinterface 121 1 0
    //   247: aload 8
    //   249: areturn
    //   250: astore 6
    //   252: aconst_null
    //   253: astore_1
    //   254: aload_1
    //   255: ifnull +18 -> 273
    //   258: aload_1
    //   259: invokeinterface 118 1 0
    //   264: ifne +9 -> 273
    //   267: aload_1
    //   268: invokeinterface 121 1 0
    //   273: aload 6
    //   275: athrow
    //   276: astore 6
    //   278: goto -24 -> 254
    //   281: astore 7
    //   283: goto -70 -> 213
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	286	0	this	i
    //   0	286	1	paramString	String
    //   28	61	2	l1	long
    //   32	55	4	l2	long
    //   99	142	6	localCursor	android.database.Cursor
    //   250	24	6	localObject1	Object
    //   276	1	6	localObject2	Object
    //   208	13	7	localException1	Exception
    //   281	1	7	localException2	Exception
    //   7	241	8	localK	k
    // Exception table:
    //   from	to	target	type
    //   34	101	208	java/lang/Exception
    //   34	101	250	finally
    //   109	119	276	finally
    //   122	135	276	finally
    //   138	151	276	finally
    //   154	167	276	finally
    //   170	183	276	finally
    //   216	225	276	finally
    //   109	119	281	java/lang/Exception
    //   122	135	281	java/lang/Exception
    //   138	151	281	java/lang/Exception
    //   154	167	281	java/lang/Exception
    //   170	183	281	java/lang/Exception
  }
}
