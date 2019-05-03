package com.duapps.ad.stats;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import com.duapps.ad.base.h;
import com.duapps.ad.base.j;
import com.duapps.ad.base.m;

public class i
{
  private static i b;
  private Context a;
  
  private i(Context paramContext)
  {
    this.a = paramContext;
  }
  
  public static i a(Context paramContext)
  {
    try
    {
      if (b == null) {
        b = new i(paramContext.getApplicationContext());
      }
      paramContext = b;
      return paramContext;
    }
    finally {}
  }
  
  private void b()
  {
    long l = System.currentTimeMillis();
    try
    {
      this.a.getContentResolver().delete(DuAdCacheProvider.a(this.a, 1), "ts<?", new String[] { String.valueOf(l - 86400000L) });
      return;
    }
    catch (Exception localException)
    {
      h.b("ToolboxCacheMgr", "dumpTimeOutDatas() exception: ", localException);
    }
  }
  
  /* Error */
  public j a(String paramString)
  {
    // Byte code:
    //   0: new 72	com/duapps/ad/base/j
    //   3: dup
    //   4: invokespecial 73	com/duapps/ad/base/j:<init>	()V
    //   7: astore 6
    //   9: aload 6
    //   11: aload_1
    //   12: putfield 76	com/duapps/ad/base/j:a	Ljava/lang/String;
    //   15: aload 6
    //   17: iconst_0
    //   18: putfield 80	com/duapps/ad/base/j:c	I
    //   21: invokestatic 35	java/lang/System:currentTimeMillis	()J
    //   24: lstore_2
    //   25: aload_0
    //   26: getfield 15	com/duapps/ad/stats/i:a	Landroid/content/Context;
    //   29: invokevirtual 39	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   32: aload_0
    //   33: getfield 15	com/duapps/ad/stats/i:a	Landroid/content/Context;
    //   36: iconst_1
    //   37: invokestatic 44	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
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
    //   123: putfield 76	com/duapps/ad/base/j:a	Ljava/lang/String;
    //   126: aload 4
    //   128: astore_1
    //   129: aload 6
    //   131: aload 4
    //   133: iconst_1
    //   134: invokeinterface 106 2 0
    //   139: putfield 108	com/duapps/ad/base/j:b	Ljava/lang/String;
    //   142: aload 4
    //   144: astore_1
    //   145: aload 6
    //   147: aload 4
    //   149: iconst_2
    //   150: invokeinterface 106 2 0
    //   155: putfield 111	com/duapps/ad/base/j:d	Ljava/lang/String;
    //   158: aload 4
    //   160: astore_1
    //   161: aload 6
    //   163: aload 4
    //   165: iconst_3
    //   166: invokeinterface 115 2 0
    //   171: putfield 80	com/duapps/ad/base/j:c	I
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
    //   213: invokestatic 69	com/duapps/ad/base/h:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
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
    //   7	232	6	localJ	j
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
    long l1 = m.k(this.a);
    long l2 = System.currentTimeMillis();
    try
    {
      this.a.getContentResolver().delete(DuAdCacheProvider.a(this.a, 7), "ts<?", new String[] { String.valueOf(l2 - l1) });
      return;
    }
    catch (Exception localException)
    {
      h.b("ToolboxCacheMgr", "removePreparseCacheTimeOutDatas exception: ", localException);
      return;
    }
    catch (Throwable localThrowable)
    {
      h.b("ToolboxCacheMgr", "removePreparseCacheTimeOutDatas del exception: ", localThrowable);
    }
  }
  
  public void a(j paramJ)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("_url", paramJ.a);
    localContentValues.put("pkg", paramJ.b);
    localContentValues.put("p_url", paramJ.d);
    localContentValues.put("type", Integer.valueOf(paramJ.c));
    localContentValues.put("ts", Long.valueOf(paramJ.e));
    paramJ = paramJ.a;
    try
    {
      if (this.a.getContentResolver().update(DuAdCacheProvider.a(this.a, 1), localContentValues, "_url = ?", new String[] { paramJ }) < 1) {
        this.a.getContentResolver().insert(DuAdCacheProvider.a(this.a, 1), localContentValues);
      }
      b();
      return;
    }
    catch (Exception paramJ)
    {
      h.b("ToolboxCacheMgr", "saveParseResult() exception: ", paramJ);
    }
  }
  
  /* Error */
  public int b(String paramString)
  {
    // Byte code:
    //   0: invokestatic 35	java/lang/System:currentTimeMillis	()J
    //   3: lstore_3
    //   4: aload_0
    //   5: getfield 15	com/duapps/ad/stats/i:a	Landroid/content/Context;
    //   8: invokevirtual 39	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   11: aload_0
    //   12: getfield 15	com/duapps/ad/stats/i:a	Landroid/content/Context;
    //   15: iconst_1
    //   16: invokestatic 44	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
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
    //   120: ldc -74
    //   122: aload 6
    //   124: invokestatic 69	com/duapps/ad/base/h:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
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
  public void b(Context paramContext)
  {
    // Byte code:
    //   0: new 184	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 185	java/util/ArrayList:<init>	()V
    //   7: astore 6
    //   9: aload_0
    //   10: getfield 15	com/duapps/ad/stats/i:a	Landroid/content/Context;
    //   13: invokevirtual 39	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   16: aload_0
    //   17: getfield 15	com/duapps/ad/stats/i:a	Landroid/content/Context;
    //   20: bipush 7
    //   22: invokestatic 44	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
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
    //   78: invokestatic 191	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   81: ifne +16 -> 97
    //   84: aload 4
    //   86: astore_3
    //   87: aload 6
    //   89: aload 5
    //   91: invokeinterface 197 2 0
    //   96: pop
    //   97: aload 4
    //   99: ifnull +20 -> 119
    //   102: aload 4
    //   104: invokeinterface 118 1 0
    //   109: ifne +10 -> 119
    //   112: aload 4
    //   114: invokeinterface 121 1 0
    //   119: aload 6
    //   121: invokeinterface 201 1 0
    //   126: ifle +337 -> 463
    //   129: new 184	java/util/ArrayList
    //   132: dup
    //   133: invokespecial 185	java/util/ArrayList:<init>	()V
    //   136: astore_3
    //   137: aload_1
    //   138: invokevirtual 205	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   141: astore_1
    //   142: aload_1
    //   143: ifnull +168 -> 311
    //   146: aload_1
    //   147: sipush 256
    //   150: invokevirtual 211	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   153: astore_1
    //   154: aload 6
    //   156: invokeinterface 215 1 0
    //   161: astore 4
    //   163: aload 4
    //   165: invokeinterface 220 1 0
    //   170: ifeq +141 -> 311
    //   173: aload 4
    //   175: invokeinterface 224 1 0
    //   180: checkcast 48	java/lang/String
    //   183: astore 5
    //   185: aload_1
    //   186: invokeinterface 215 1 0
    //   191: astore 6
    //   193: aload 6
    //   195: invokeinterface 220 1 0
    //   200: ifeq -37 -> 163
    //   203: aload 6
    //   205: invokeinterface 224 1 0
    //   210: checkcast 226	android/content/pm/PackageInfo
    //   213: astore 7
    //   215: aload 7
    //   217: ifnull -24 -> 193
    //   220: aload 7
    //   222: getfield 229	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   225: aload 5
    //   227: invokevirtual 232	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   230: ifeq -37 -> 193
    //   233: aload_3
    //   234: aload 5
    //   236: invokeinterface 197 2 0
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
    //   259: invokestatic 69	com/duapps/ad/base/h:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
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
    //   312: invokeinterface 201 1 0
    //   317: ifle +146 -> 463
    //   320: iconst_0
    //   321: istore_2
    //   322: ldc -22
    //   324: astore_1
    //   325: iload_2
    //   326: aload_3
    //   327: invokeinterface 201 1 0
    //   332: if_icmpge +89 -> 421
    //   335: aload_3
    //   336: iload_2
    //   337: invokeinterface 238 2 0
    //   342: checkcast 48	java/lang/String
    //   345: astore 4
    //   347: iload_2
    //   348: ifne +40 -> 388
    //   351: new 240	java/lang/StringBuilder
    //   354: dup
    //   355: invokespecial 241	java/lang/StringBuilder:<init>	()V
    //   358: aload_1
    //   359: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   362: ldc -9
    //   364: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   367: aload 4
    //   369: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   372: ldc -9
    //   374: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   377: invokevirtual 251	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   380: astore_1
    //   381: iload_2
    //   382: iconst_1
    //   383: iadd
    //   384: istore_2
    //   385: goto -60 -> 325
    //   388: new 240	java/lang/StringBuilder
    //   391: dup
    //   392: invokespecial 241	java/lang/StringBuilder:<init>	()V
    //   395: aload_1
    //   396: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   399: ldc -3
    //   401: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   404: aload 4
    //   406: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   409: ldc -9
    //   411: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   414: invokevirtual 251	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   417: astore_1
    //   418: goto -37 -> 381
    //   421: new 240	java/lang/StringBuilder
    //   424: dup
    //   425: invokespecial 241	java/lang/StringBuilder:<init>	()V
    //   428: aload_1
    //   429: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   432: ldc -1
    //   434: invokevirtual 245	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   437: invokevirtual 251	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   440: astore_1
    //   441: aload_0
    //   442: getfield 15	com/duapps/ad/stats/i:a	Landroid/content/Context;
    //   445: invokevirtual 39	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   448: aload_0
    //   449: getfield 15	com/duapps/ad/stats/i:a	Landroid/content/Context;
    //   452: bipush 7
    //   454: invokestatic 44	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   457: aload_1
    //   458: aconst_null
    //   459: invokevirtual 60	android/content/ContentResolver:delete	(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I
    //   462: pop
    //   463: return
    //   464: astore_1
    //   465: ldc 62
    //   467: ldc_w 257
    //   470: aload_1
    //   471: invokestatic 69	com/duapps/ad/base/h:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   474: return
    //   475: astore_1
    //   476: ldc 62
    //   478: ldc_w 259
    //   481: aload_1
    //   482: invokestatic 69	com/duapps/ad/base/h:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   485: return
    //   486: astore_1
    //   487: goto -197 -> 290
    //   490: astore 5
    //   492: goto -242 -> 250
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	495	0	this	i
    //   0	495	1	paramContext	Context
    //   321	64	2	i	int
    //   49	287	3	localObject1	Object
    //   40	365	4	localObject2	Object
    //   71	164	5	str	String
    //   245	13	5	localException1	Exception
    //   490	1	5	localException2	Exception
    //   7	197	6	localObject3	Object
    //   213	8	7	localPackageInfo	android.content.pm.PackageInfo
    // Exception table:
    //   from	to	target	type
    //   9	42	245	java/lang/Exception
    //   9	42	287	finally
    //   441	463	464	java/lang/Exception
    //   441	463	475	java/lang/Throwable
    //   50	60	486	finally
    //   63	73	486	finally
    //   76	84	486	finally
    //   87	97	486	finally
    //   253	262	486	finally
    //   50	60	490	java/lang/Exception
    //   63	73	490	java/lang/Exception
    //   76	84	490	java/lang/Exception
    //   87	97	490	java/lang/Exception
  }
  
  public void b(j paramJ)
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("_url", paramJ.a);
    localContentValues.put("pkg", paramJ.b);
    localContentValues.put("p_url", paramJ.d);
    localContentValues.put("type", Integer.valueOf(paramJ.c));
    localContentValues.put("ts", Long.valueOf(paramJ.e));
    paramJ = paramJ.a;
    try
    {
      if (this.a.getContentResolver().update(DuAdCacheProvider.a(this.a, 7), localContentValues, "_url = ?", new String[] { paramJ }) < 1) {
        this.a.getContentResolver().insert(DuAdCacheProvider.a(this.a, 7), localContentValues);
      }
      return;
    }
    catch (Exception paramJ)
    {
      h.b("ToolboxCacheMgr", "saveParseResult() exception: ", paramJ);
    }
  }
  
  /* Error */
  public int c(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 15	com/duapps/ad/stats/i:a	Landroid/content/Context;
    //   4: invokestatic 131	com/duapps/ad/base/m:k	(Landroid/content/Context;)J
    //   7: lstore_3
    //   8: invokestatic 35	java/lang/System:currentTimeMillis	()J
    //   11: lstore 5
    //   13: aload_0
    //   14: getfield 15	com/duapps/ad/stats/i:a	Landroid/content/Context;
    //   17: invokevirtual 39	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   20: aload_0
    //   21: getfield 15	com/duapps/ad/stats/i:a	Landroid/content/Context;
    //   24: bipush 7
    //   26: invokestatic 44	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
    //   29: iconst_1
    //   30: anewarray 48	java/lang/String
    //   33: dup
    //   34: iconst_0
    //   35: ldc 88
    //   37: aastore
    //   38: ldc_w 261
    //   41: iconst_2
    //   42: anewarray 48	java/lang/String
    //   45: dup
    //   46: iconst_0
    //   47: aload_1
    //   48: aastore
    //   49: dup
    //   50: iconst_1
    //   51: lload 5
    //   53: lload_3
    //   54: lsub
    //   55: invokestatic 54	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   58: aastore
    //   59: ldc 92
    //   61: invokevirtual 96	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   64: astore 7
    //   66: aload 7
    //   68: ifnull +131 -> 199
    //   71: aload 7
    //   73: astore_1
    //   74: aload 7
    //   76: invokeinterface 102 1 0
    //   81: ifeq +118 -> 199
    //   84: aload 7
    //   86: astore_1
    //   87: aload 7
    //   89: iconst_0
    //   90: invokeinterface 115 2 0
    //   95: istore_2
    //   96: aload 7
    //   98: ifnull +20 -> 118
    //   101: aload 7
    //   103: invokeinterface 118 1 0
    //   108: ifne +10 -> 118
    //   111: aload 7
    //   113: invokeinterface 121 1 0
    //   118: iload_2
    //   119: ireturn
    //   120: astore 8
    //   122: aconst_null
    //   123: astore 7
    //   125: aload 7
    //   127: astore_1
    //   128: ldc 62
    //   130: ldc -74
    //   132: aload 8
    //   134: invokestatic 69	com/duapps/ad/base/h:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   137: aload 7
    //   139: ifnull +58 -> 197
    //   142: aload 7
    //   144: invokeinterface 118 1 0
    //   149: ifne +48 -> 197
    //   152: aload 7
    //   154: invokeinterface 121 1 0
    //   159: iconst_0
    //   160: ireturn
    //   161: astore 7
    //   163: aconst_null
    //   164: astore_1
    //   165: aload_1
    //   166: ifnull +18 -> 184
    //   169: aload_1
    //   170: invokeinterface 118 1 0
    //   175: ifne +9 -> 184
    //   178: aload_1
    //   179: invokeinterface 121 1 0
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
    //   0	204	0	this	i
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
  
  /* Error */
  public j d(String paramString)
  {
    // Byte code:
    //   0: new 72	com/duapps/ad/base/j
    //   3: dup
    //   4: invokespecial 73	com/duapps/ad/base/j:<init>	()V
    //   7: astore 8
    //   9: aload 8
    //   11: aload_1
    //   12: putfield 108	com/duapps/ad/base/j:b	Ljava/lang/String;
    //   15: aload 8
    //   17: iconst_0
    //   18: putfield 80	com/duapps/ad/base/j:c	I
    //   21: aload_0
    //   22: getfield 15	com/duapps/ad/stats/i:a	Landroid/content/Context;
    //   25: invokestatic 131	com/duapps/ad/base/m:k	(Landroid/content/Context;)J
    //   28: lstore_2
    //   29: invokestatic 35	java/lang/System:currentTimeMillis	()J
    //   32: lstore 4
    //   34: aload_0
    //   35: getfield 15	com/duapps/ad/stats/i:a	Landroid/content/Context;
    //   38: invokevirtual 39	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   41: aload_0
    //   42: getfield 15	com/duapps/ad/stats/i:a	Landroid/content/Context;
    //   45: bipush 7
    //   47: invokestatic 44	com/duapps/ad/stats/DuAdCacheProvider:a	(Landroid/content/Context;I)Landroid/net/Uri;
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
    //   74: ldc_w 261
    //   77: iconst_2
    //   78: anewarray 48	java/lang/String
    //   81: dup
    //   82: iconst_0
    //   83: aload_1
    //   84: aastore
    //   85: dup
    //   86: iconst_1
    //   87: lload 4
    //   89: lload_2
    //   90: lsub
    //   91: invokestatic 54	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   94: aastore
    //   95: ldc 92
    //   97: invokevirtual 96	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   100: astore 6
    //   102: aload 6
    //   104: ifnull +80 -> 184
    //   107: aload 6
    //   109: astore_1
    //   110: aload 6
    //   112: invokeinterface 102 1 0
    //   117: ifeq +67 -> 184
    //   120: aload 6
    //   122: astore_1
    //   123: aload 8
    //   125: aload 6
    //   127: iconst_0
    //   128: invokeinterface 106 2 0
    //   133: putfield 76	com/duapps/ad/base/j:a	Ljava/lang/String;
    //   136: aload 6
    //   138: astore_1
    //   139: aload 8
    //   141: aload 6
    //   143: iconst_1
    //   144: invokeinterface 106 2 0
    //   149: putfield 108	com/duapps/ad/base/j:b	Ljava/lang/String;
    //   152: aload 6
    //   154: astore_1
    //   155: aload 8
    //   157: aload 6
    //   159: iconst_2
    //   160: invokeinterface 106 2 0
    //   165: putfield 111	com/duapps/ad/base/j:d	Ljava/lang/String;
    //   168: aload 6
    //   170: astore_1
    //   171: aload 8
    //   173: aload 6
    //   175: iconst_3
    //   176: invokeinterface 115 2 0
    //   181: putfield 80	com/duapps/ad/base/j:c	I
    //   184: aload 6
    //   186: ifnull +20 -> 206
    //   189: aload 6
    //   191: invokeinterface 118 1 0
    //   196: ifne +10 -> 206
    //   199: aload 6
    //   201: invokeinterface 121 1 0
    //   206: aload 8
    //   208: areturn
    //   209: astore 7
    //   211: aconst_null
    //   212: astore 6
    //   214: aload 6
    //   216: astore_1
    //   217: ldc 62
    //   219: ldc 123
    //   221: aload 7
    //   223: invokestatic 69	com/duapps/ad/base/h:b	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   226: aload 6
    //   228: ifnull -22 -> 206
    //   231: aload 6
    //   233: invokeinterface 118 1 0
    //   238: ifne -32 -> 206
    //   241: aload 6
    //   243: invokeinterface 121 1 0
    //   248: aload 8
    //   250: areturn
    //   251: astore 6
    //   253: aconst_null
    //   254: astore_1
    //   255: aload_1
    //   256: ifnull +18 -> 274
    //   259: aload_1
    //   260: invokeinterface 118 1 0
    //   265: ifne +9 -> 274
    //   268: aload_1
    //   269: invokeinterface 121 1 0
    //   274: aload 6
    //   276: athrow
    //   277: astore 6
    //   279: goto -24 -> 255
    //   282: astore 7
    //   284: goto -70 -> 214
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	287	0	this	i
    //   0	287	1	paramString	String
    //   28	62	2	l1	long
    //   32	56	4	l2	long
    //   100	142	6	localCursor	android.database.Cursor
    //   251	24	6	localObject1	Object
    //   277	1	6	localObject2	Object
    //   209	13	7	localException1	Exception
    //   282	1	7	localException2	Exception
    //   7	242	8	localJ	j
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
