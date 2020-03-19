package com.antivirus.apploader;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

public class f
{
  SQLiteDatabase a = null;
  e b = null;
  
  public f() {}
  
  /* Error */
  public String a(Context paramContext, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: getfield 14	com/antivirus/apploader/f:a	Landroid/database/sqlite/SQLiteDatabase;
    //   7: ifnonnull +30 -> 37
    //   10: aload_0
    //   11: new 22	com/antivirus/apploader/e
    //   14: dup
    //   15: aload_1
    //   16: ldc 24
    //   18: aconst_null
    //   19: iconst_1
    //   20: invokespecial 27	com/antivirus/apploader/e:<init>	(Landroid/content/Context;Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)V
    //   23: putfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   26: aload_0
    //   27: aload_0
    //   28: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   31: invokevirtual 31	com/antivirus/apploader/e:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   34: putfield 14	com/antivirus/apploader/f:a	Landroid/database/sqlite/SQLiteDatabase;
    //   37: aload_0
    //   38: getfield 14	com/antivirus/apploader/f:a	Landroid/database/sqlite/SQLiteDatabase;
    //   41: ldc 33
    //   43: iconst_1
    //   44: anewarray 35	java/lang/String
    //   47: dup
    //   48: iconst_0
    //   49: ldc 37
    //   51: aastore
    //   52: ldc 39
    //   54: iconst_1
    //   55: anewarray 35	java/lang/String
    //   58: dup
    //   59: iconst_0
    //   60: aload_2
    //   61: aastore
    //   62: aconst_null
    //   63: aconst_null
    //   64: aconst_null
    //   65: invokevirtual 45	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   68: astore_1
    //   69: aload_3
    //   70: astore_2
    //   71: aload_1
    //   72: ifnull +44 -> 116
    //   75: aload_3
    //   76: astore_2
    //   77: aload_1
    //   78: invokeinterface 51 1 0
    //   83: ifle +33 -> 116
    //   86: aload_1
    //   87: invokeinterface 55 1 0
    //   92: pop
    //   93: aload_3
    //   94: astore_2
    //   95: aload_1
    //   96: iconst_0
    //   97: invokeinterface 59 2 0
    //   102: invokestatic 65	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   105: ifne +11 -> 116
    //   108: aload_1
    //   109: iconst_0
    //   110: invokeinterface 59 2 0
    //   115: astore_2
    //   116: aload_1
    //   117: ifnull +9 -> 126
    //   120: aload_1
    //   121: invokeinterface 68 1 0
    //   126: aload_0
    //   127: invokevirtual 70	com/antivirus/apploader/f:a	()V
    //   130: aload_2
    //   131: areturn
    //   132: astore_1
    //   133: aconst_null
    //   134: astore_1
    //   135: aload_1
    //   136: ifnull +9 -> 145
    //   139: aload_1
    //   140: invokeinterface 68 1 0
    //   145: aload_0
    //   146: invokevirtual 70	com/antivirus/apploader/f:a	()V
    //   149: aload_3
    //   150: areturn
    //   151: astore_2
    //   152: aload 4
    //   154: astore_1
    //   155: aload_1
    //   156: ifnull +9 -> 165
    //   159: aload_1
    //   160: invokeinterface 68 1 0
    //   165: aload_0
    //   166: invokevirtual 70	com/antivirus/apploader/f:a	()V
    //   169: aload_2
    //   170: athrow
    //   171: astore_2
    //   172: goto -17 -> 155
    //   175: astore_2
    //   176: goto -41 -> 135
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	179	0	this	f
    //   0	179	1	paramContext	Context
    //   0	179	2	paramString1	String
    //   0	179	3	paramString2	String
    //   1	152	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   37	69	132	java/lang/Exception
    //   37	69	151	finally
    //   77	93	171	finally
    //   95	116	171	finally
    //   77	93	175	java/lang/Exception
    //   95	116	175	java/lang/Exception
  }
  
  public void a()
  {
    if ((this.a != null) && (this.a.isOpen()))
    {
      this.a.close();
      this.a = null;
    }
    if (this.b != null) {
      this.b.close();
    }
  }
  
  /* Error */
  public void a(Context paramContext, java.util.TreeMap paramTreeMap, int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: new 78	android/content/ContentValues
    //   6: dup
    //   7: invokespecial 79	android/content/ContentValues:<init>	()V
    //   10: astore 5
    //   12: aload_0
    //   13: new 22	com/antivirus/apploader/e
    //   16: dup
    //   17: aload_1
    //   18: ldc 24
    //   20: aconst_null
    //   21: iconst_1
    //   22: invokespecial 27	com/antivirus/apploader/e:<init>	(Landroid/content/Context;Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)V
    //   25: putfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   28: aload_0
    //   29: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   32: invokevirtual 31	com/antivirus/apploader/e:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   35: astore_1
    //   36: aload_1
    //   37: invokevirtual 82	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   40: iconst_2
    //   41: anewarray 35	java/lang/String
    //   44: astore 4
    //   46: aload_2
    //   47: invokevirtual 88	java/util/TreeMap:keySet	()Ljava/util/Set;
    //   50: invokeinterface 94 1 0
    //   55: astore 6
    //   57: aload 6
    //   59: invokeinterface 99 1 0
    //   64: ifeq +90 -> 154
    //   67: aload 6
    //   69: invokeinterface 103 1 0
    //   74: checkcast 35	java/lang/String
    //   77: astore 7
    //   79: aload 5
    //   81: ldc 105
    //   83: iload_3
    //   84: invokestatic 111	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   87: invokevirtual 115	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   90: aload 4
    //   92: iconst_0
    //   93: aload 7
    //   95: aastore
    //   96: aload 4
    //   98: iconst_1
    //   99: aload_2
    //   100: aload 7
    //   102: invokevirtual 119	java/util/TreeMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   105: checkcast 35	java/lang/String
    //   108: aastore
    //   109: aload_1
    //   110: ldc 121
    //   112: aload 5
    //   114: ldc 123
    //   116: aload 4
    //   118: invokevirtual 127	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   121: pop
    //   122: aload 5
    //   124: invokevirtual 130	android/content/ContentValues:clear	()V
    //   127: goto -70 -> 57
    //   130: astore_2
    //   131: aload_1
    //   132: ifnull +7 -> 139
    //   135: aload_1
    //   136: invokevirtual 74	android/database/sqlite/SQLiteDatabase:close	()V
    //   139: aload_0
    //   140: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   143: ifnull +10 -> 153
    //   146: aload_0
    //   147: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   150: invokevirtual 75	com/antivirus/apploader/e:close	()V
    //   153: return
    //   154: aload_1
    //   155: invokevirtual 133	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   158: aload_1
    //   159: invokevirtual 136	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   162: aload_1
    //   163: invokevirtual 74	android/database/sqlite/SQLiteDatabase:close	()V
    //   166: aload_0
    //   167: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   170: invokevirtual 75	com/antivirus/apploader/e:close	()V
    //   173: aload_1
    //   174: ifnull +7 -> 181
    //   177: aload_1
    //   178: invokevirtual 74	android/database/sqlite/SQLiteDatabase:close	()V
    //   181: aload_0
    //   182: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   185: ifnull -32 -> 153
    //   188: aload_0
    //   189: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   192: invokevirtual 75	com/antivirus/apploader/e:close	()V
    //   195: return
    //   196: astore_1
    //   197: aconst_null
    //   198: astore 4
    //   200: aload_1
    //   201: astore_2
    //   202: aload 4
    //   204: ifnull +8 -> 212
    //   207: aload 4
    //   209: invokevirtual 74	android/database/sqlite/SQLiteDatabase:close	()V
    //   212: aload_0
    //   213: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   216: ifnull +10 -> 226
    //   219: aload_0
    //   220: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   223: invokevirtual 75	com/antivirus/apploader/e:close	()V
    //   226: aload_2
    //   227: athrow
    //   228: astore_2
    //   229: aload_1
    //   230: astore 4
    //   232: goto -30 -> 202
    //   235: astore_1
    //   236: aload 4
    //   238: astore_1
    //   239: goto -108 -> 131
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	242	0	this	f
    //   0	242	1	paramContext	Context
    //   0	242	2	paramTreeMap	java.util.TreeMap
    //   0	242	3	paramInt	int
    //   1	236	4	localObject	Object
    //   10	113	5	localContentValues	ContentValues
    //   55	13	6	localIterator	java.util.Iterator
    //   77	24	7	str	String
    // Exception table:
    //   from	to	target	type
    //   36	57	130	java/lang/Exception
    //   57	90	130	java/lang/Exception
    //   96	127	130	java/lang/Exception
    //   154	173	130	java/lang/Exception
    //   12	36	196	finally
    //   36	57	228	finally
    //   57	90	228	finally
    //   96	127	228	finally
    //   154	173	228	finally
    //   12	36	235	java/lang/Exception
  }
  
  /* Error */
  public boolean a(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_0
    //   3: getfield 14	com/antivirus/apploader/f:a	Landroid/database/sqlite/SQLiteDatabase;
    //   6: ifnonnull +30 -> 36
    //   9: aload_0
    //   10: new 22	com/antivirus/apploader/e
    //   13: dup
    //   14: aload_1
    //   15: ldc 24
    //   17: aconst_null
    //   18: iconst_1
    //   19: invokespecial 27	com/antivirus/apploader/e:<init>	(Landroid/content/Context;Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)V
    //   22: putfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   25: aload_0
    //   26: aload_0
    //   27: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   30: invokevirtual 31	com/antivirus/apploader/e:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   33: putfield 14	com/antivirus/apploader/f:a	Landroid/database/sqlite/SQLiteDatabase;
    //   36: aload_0
    //   37: getfield 14	com/antivirus/apploader/f:a	Landroid/database/sqlite/SQLiteDatabase;
    //   40: ldc 33
    //   42: iconst_1
    //   43: anewarray 35	java/lang/String
    //   46: dup
    //   47: iconst_0
    //   48: ldc 37
    //   50: aastore
    //   51: ldc 39
    //   53: iconst_1
    //   54: anewarray 35	java/lang/String
    //   57: dup
    //   58: iconst_0
    //   59: ldc -117
    //   61: aastore
    //   62: aconst_null
    //   63: aconst_null
    //   64: aconst_null
    //   65: invokevirtual 45	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   68: astore_1
    //   69: aload_1
    //   70: ifnull +104 -> 174
    //   73: aload_1
    //   74: invokeinterface 51 1 0
    //   79: ifle +95 -> 174
    //   82: aload_1
    //   83: invokeinterface 55 1 0
    //   88: pop
    //   89: aload_1
    //   90: iconst_0
    //   91: invokeinterface 59 2 0
    //   96: ldc -115
    //   98: invokevirtual 145	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   101: istore_2
    //   102: iload_2
    //   103: ifeq +71 -> 174
    //   106: iconst_1
    //   107: istore_2
    //   108: aload_1
    //   109: ifnull +9 -> 118
    //   112: aload_1
    //   113: invokeinterface 68 1 0
    //   118: aload_0
    //   119: invokevirtual 70	com/antivirus/apploader/f:a	()V
    //   122: iload_2
    //   123: ireturn
    //   124: astore_1
    //   125: aconst_null
    //   126: astore_1
    //   127: aload_1
    //   128: ifnull +9 -> 137
    //   131: aload_1
    //   132: invokeinterface 68 1 0
    //   137: aload_0
    //   138: invokevirtual 70	com/antivirus/apploader/f:a	()V
    //   141: iconst_0
    //   142: ireturn
    //   143: astore_1
    //   144: aload_3
    //   145: ifnull +9 -> 154
    //   148: aload_3
    //   149: invokeinterface 68 1 0
    //   154: aload_0
    //   155: invokevirtual 70	com/antivirus/apploader/f:a	()V
    //   158: aload_1
    //   159: athrow
    //   160: astore 4
    //   162: aload_1
    //   163: astore_3
    //   164: aload 4
    //   166: astore_1
    //   167: goto -23 -> 144
    //   170: astore_3
    //   171: goto -44 -> 127
    //   174: iconst_0
    //   175: istore_2
    //   176: goto -68 -> 108
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	179	0	this	f
    //   0	179	1	paramContext	Context
    //   101	75	2	bool	boolean
    //   1	163	3	localContext	Context
    //   170	1	3	localException	Exception
    //   160	5	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   36	69	124	java/lang/Exception
    //   36	69	143	finally
    //   73	102	160	finally
    //   73	102	170	java/lang/Exception
  }
  
  public boolean a(Context paramContext, String paramString)
  {
    Object localObject2 = null;
    ContentValues localContentValues;
    if (!TextUtils.isEmpty(paramString))
    {
      localContentValues = new ContentValues();
      localObject1 = localObject2;
    }
    try
    {
      this.b = new e(paramContext, "uppdb", null, 1);
      localObject1 = localObject2;
      paramContext = this.b.getReadableDatabase();
      localObject1 = paramContext;
      if (localObject1 == null) {
        break label185;
      }
    }
    catch (Exception paramContext)
    {
      do
      {
        paramContext = paramContext;
        if (localObject1 != null) {
          ((SQLiteDatabase)localObject1).close();
        }
      } while (this.b == null);
      this.b.close();
      return false;
    }
    finally
    {
      localObject1 = null;
      paramString = paramContext;
    }
    ((SQLiteDatabase)localObject1).close();
    label185:
    if (this.b != null) {
      this.b.close();
    }
    throw paramString;
  }
  
  /* Error */
  public String b(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: getfield 14	com/antivirus/apploader/f:a	Landroid/database/sqlite/SQLiteDatabase;
    //   6: ifnonnull +30 -> 36
    //   9: aload_0
    //   10: new 22	com/antivirus/apploader/e
    //   13: dup
    //   14: aload_1
    //   15: ldc 24
    //   17: aconst_null
    //   18: iconst_1
    //   19: invokespecial 27	com/antivirus/apploader/e:<init>	(Landroid/content/Context;Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)V
    //   22: putfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   25: aload_0
    //   26: aload_0
    //   27: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   30: invokevirtual 31	com/antivirus/apploader/e:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   33: putfield 14	com/antivirus/apploader/f:a	Landroid/database/sqlite/SQLiteDatabase;
    //   36: aload_0
    //   37: getfield 14	com/antivirus/apploader/f:a	Landroid/database/sqlite/SQLiteDatabase;
    //   40: ldc 121
    //   42: iconst_1
    //   43: anewarray 35	java/lang/String
    //   46: dup
    //   47: iconst_0
    //   48: ldc -105
    //   50: aastore
    //   51: ldc -103
    //   53: aconst_null
    //   54: aconst_null
    //   55: aconst_null
    //   56: aconst_null
    //   57: invokevirtual 45	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   60: astore_1
    //   61: aload_1
    //   62: ifnull +101 -> 163
    //   65: aload_1
    //   66: astore_2
    //   67: aload_1
    //   68: invokeinterface 51 1 0
    //   73: ifle +90 -> 163
    //   76: aload_1
    //   77: astore_2
    //   78: aload_1
    //   79: invokeinterface 55 1 0
    //   84: pop
    //   85: aload_1
    //   86: astore_2
    //   87: aload_1
    //   88: iconst_0
    //   89: invokeinterface 59 2 0
    //   94: astore_3
    //   95: aload_3
    //   96: astore_2
    //   97: aload_1
    //   98: ifnull +9 -> 107
    //   101: aload_1
    //   102: invokeinterface 68 1 0
    //   107: aload_0
    //   108: invokevirtual 70	com/antivirus/apploader/f:a	()V
    //   111: aload_2
    //   112: areturn
    //   113: astore_3
    //   114: aconst_null
    //   115: astore_1
    //   116: aload_1
    //   117: astore_2
    //   118: aload_3
    //   119: invokestatic 158	com/avg/toolkit/g/a:a	(Ljava/lang/Exception;)V
    //   122: aload_1
    //   123: ifnull +9 -> 132
    //   126: aload_1
    //   127: invokeinterface 68 1 0
    //   132: aload_0
    //   133: invokevirtual 70	com/antivirus/apploader/f:a	()V
    //   136: aconst_null
    //   137: areturn
    //   138: astore_1
    //   139: aload_2
    //   140: ifnull +9 -> 149
    //   143: aload_2
    //   144: invokeinterface 68 1 0
    //   149: aload_0
    //   150: invokevirtual 70	com/antivirus/apploader/f:a	()V
    //   153: aload_1
    //   154: athrow
    //   155: astore_1
    //   156: goto -17 -> 139
    //   159: astore_3
    //   160: goto -44 -> 116
    //   163: aconst_null
    //   164: astore_2
    //   165: goto -68 -> 97
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	168	0	this	f
    //   0	168	1	paramContext	Context
    //   1	164	2	localObject	Object
    //   94	2	3	str	String
    //   113	6	3	localException1	Exception
    //   159	1	3	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   36	61	113	java/lang/Exception
    //   36	61	138	finally
    //   67	76	155	finally
    //   78	85	155	finally
    //   87	95	155	finally
    //   118	122	155	finally
    //   67	76	159	java/lang/Exception
    //   78	85	159	java/lang/Exception
    //   87	95	159	java/lang/Exception
  }
  
  public void b(Context paramContext, String paramString1, String paramString2)
  {
    Object localObject2 = null;
    ContentValues localContentValues = new ContentValues();
    Object localObject1 = localObject2;
    try
    {
      this.b = new e(paramContext, "uppdb", null, 1);
      localObject1 = localObject2;
      paramContext = this.b.getReadableDatabase();
      localObject1 = paramContext;
      if (paramString2 == null) {
        break label211;
      }
    }
    catch (Exception paramContext)
    {
      do
      {
        paramContext = paramContext;
        if (localObject1 != null) {
          ((SQLiteDatabase)localObject1).close();
        }
      } while (this.b == null);
      this.b.close();
      return;
    }
    finally
    {
      paramString2 = null;
      paramString1 = paramContext;
    }
    paramString2.close();
    label211:
    if (this.b != null) {
      this.b.close();
    }
    throw paramString1;
  }
  
  /* Error */
  public boolean c(Context paramContext)
  {
    // Byte code:
    //   0: new 78	android/content/ContentValues
    //   3: dup
    //   4: invokespecial 79	android/content/ContentValues:<init>	()V
    //   7: astore_2
    //   8: aload_0
    //   9: new 22	com/antivirus/apploader/e
    //   12: dup
    //   13: aload_1
    //   14: ldc 24
    //   16: aconst_null
    //   17: iconst_1
    //   18: invokespecial 27	com/antivirus/apploader/e:<init>	(Landroid/content/Context;Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)V
    //   21: putfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   24: aload_0
    //   25: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   28: invokevirtual 31	com/antivirus/apploader/e:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   31: astore_1
    //   32: aload_1
    //   33: invokevirtual 82	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   36: aload_2
    //   37: invokevirtual 130	android/content/ContentValues:clear	()V
    //   40: aload_2
    //   41: ldc 105
    //   43: iconst_0
    //   44: invokestatic 111	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   47: invokevirtual 115	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   50: aload_1
    //   51: ldc 121
    //   53: aload_2
    //   54: ldc -85
    //   56: aconst_null
    //   57: invokevirtual 127	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   60: pop
    //   61: aload_1
    //   62: invokevirtual 133	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   65: aload_1
    //   66: invokevirtual 136	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   69: aload_1
    //   70: invokevirtual 74	android/database/sqlite/SQLiteDatabase:close	()V
    //   73: aload_0
    //   74: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   77: invokevirtual 75	com/antivirus/apploader/e:close	()V
    //   80: iconst_0
    //   81: ifeq +11 -> 92
    //   84: new 173	java/lang/NullPointerException
    //   87: dup
    //   88: invokespecial 174	java/lang/NullPointerException:<init>	()V
    //   91: athrow
    //   92: aload_1
    //   93: ifnull +7 -> 100
    //   96: aload_1
    //   97: invokevirtual 74	android/database/sqlite/SQLiteDatabase:close	()V
    //   100: aload_0
    //   101: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   104: ifnull +10 -> 114
    //   107: aload_0
    //   108: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   111: invokevirtual 75	com/antivirus/apploader/e:close	()V
    //   114: iconst_0
    //   115: ireturn
    //   116: astore_1
    //   117: aconst_null
    //   118: astore_1
    //   119: iconst_0
    //   120: ifeq +11 -> 131
    //   123: new 173	java/lang/NullPointerException
    //   126: dup
    //   127: invokespecial 174	java/lang/NullPointerException:<init>	()V
    //   130: athrow
    //   131: aload_1
    //   132: ifnull +7 -> 139
    //   135: aload_1
    //   136: invokevirtual 74	android/database/sqlite/SQLiteDatabase:close	()V
    //   139: aload_0
    //   140: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   143: ifnull -29 -> 114
    //   146: aload_0
    //   147: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   150: invokevirtual 75	com/antivirus/apploader/e:close	()V
    //   153: iconst_0
    //   154: ireturn
    //   155: astore_2
    //   156: aconst_null
    //   157: astore_1
    //   158: iconst_0
    //   159: ifeq +11 -> 170
    //   162: new 173	java/lang/NullPointerException
    //   165: dup
    //   166: invokespecial 174	java/lang/NullPointerException:<init>	()V
    //   169: athrow
    //   170: aload_1
    //   171: ifnull +7 -> 178
    //   174: aload_1
    //   175: invokevirtual 74	android/database/sqlite/SQLiteDatabase:close	()V
    //   178: aload_0
    //   179: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   182: ifnull +10 -> 192
    //   185: aload_0
    //   186: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   189: invokevirtual 75	com/antivirus/apploader/e:close	()V
    //   192: aload_2
    //   193: athrow
    //   194: astore_2
    //   195: goto -37 -> 158
    //   198: astore_2
    //   199: goto -80 -> 119
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	202	0	this	f
    //   0	202	1	paramContext	Context
    //   7	47	2	localContentValues	ContentValues
    //   155	38	2	localObject1	Object
    //   194	1	2	localObject2	Object
    //   198	1	2	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   8	32	116	java/lang/Exception
    //   8	32	155	finally
    //   32	80	194	finally
    //   32	80	198	java/lang/Exception
  }
  
  /* Error */
  public void d(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore 5
    //   5: aconst_null
    //   6: astore 4
    //   8: new 78	android/content/ContentValues
    //   11: dup
    //   12: invokespecial 79	android/content/ContentValues:<init>	()V
    //   15: astore 8
    //   17: aload_0
    //   18: new 22	com/antivirus/apploader/e
    //   21: dup
    //   22: aload_1
    //   23: ldc 24
    //   25: aconst_null
    //   26: iconst_1
    //   27: invokespecial 27	com/antivirus/apploader/e:<init>	(Landroid/content/Context;Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)V
    //   30: putfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   33: aload_0
    //   34: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   37: invokevirtual 31	com/antivirus/apploader/e:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   40: astore_3
    //   41: aload_3
    //   42: invokevirtual 82	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   45: aload 8
    //   47: invokevirtual 130	android/content/ContentValues:clear	()V
    //   50: aload 8
    //   52: ldc 37
    //   54: ldc -115
    //   56: invokevirtual 164	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   59: aload 8
    //   61: ldc -95
    //   63: ldc -117
    //   65: invokevirtual 164	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   68: aload_3
    //   69: ldc 33
    //   71: aconst_null
    //   72: aload 8
    //   74: invokevirtual 168	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   77: pop2
    //   78: aload 8
    //   80: invokevirtual 130	android/content/ContentValues:clear	()V
    //   83: aload 8
    //   85: ldc 37
    //   87: ldc -78
    //   89: invokevirtual 164	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   92: aload 8
    //   94: ldc -95
    //   96: ldc -76
    //   98: invokevirtual 164	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   101: aload_3
    //   102: ldc 33
    //   104: aconst_null
    //   105: aload 8
    //   107: invokevirtual 168	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   110: pop2
    //   111: aload_1
    //   112: invokevirtual 186	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   115: iconst_0
    //   116: invokevirtual 192	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   119: invokeinterface 195 1 0
    //   124: astore 9
    //   126: aconst_null
    //   127: astore_2
    //   128: aload 4
    //   130: astore_1
    //   131: aload 9
    //   133: invokeinterface 99 1 0
    //   138: ifeq +447 -> 585
    //   141: aload 9
    //   143: invokeinterface 103 1 0
    //   148: checkcast 197	android/content/pm/PackageInfo
    //   151: astore 6
    //   153: aload 8
    //   155: invokevirtual 130	android/content/ContentValues:clear	()V
    //   158: new 199	java/io/File
    //   161: dup
    //   162: aload 6
    //   164: getfield 203	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   167: getfield 209	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   170: invokespecial 212	java/io/File:<init>	(Ljava/lang/String;)V
    //   173: astore 4
    //   175: aload 4
    //   177: ifnull +623 -> 800
    //   180: aload 4
    //   182: invokevirtual 215	java/io/File:exists	()Z
    //   185: ifeq +615 -> 800
    //   188: aload 4
    //   190: invokevirtual 218	java/io/File:canRead	()Z
    //   193: ifeq +607 -> 800
    //   196: new 220	java/util/zip/ZipFile
    //   199: dup
    //   200: aload 4
    //   202: invokespecial 223	java/util/zip/ZipFile:<init>	(Ljava/io/File;)V
    //   205: astore 4
    //   207: aload_1
    //   208: astore_2
    //   209: aload_1
    //   210: astore 5
    //   212: aload 4
    //   214: ldc -31
    //   216: invokevirtual 229	java/util/zip/ZipFile:getEntry	(Ljava/lang/String;)Ljava/util/zip/ZipEntry;
    //   219: astore 7
    //   221: aload_1
    //   222: astore_2
    //   223: aload 7
    //   225: ifnull +163 -> 388
    //   228: aload_1
    //   229: astore_2
    //   230: aload_1
    //   231: astore 5
    //   233: aload 4
    //   235: aload 7
    //   237: invokevirtual 233	java/util/zip/ZipFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   240: astore_1
    //   241: aload_1
    //   242: astore_2
    //   243: aload_1
    //   244: ifnull +144 -> 388
    //   247: aload_1
    //   248: astore_2
    //   249: aload_1
    //   250: astore 5
    //   252: aload 8
    //   254: ldc -21
    //   256: aload_1
    //   257: invokestatic 240	a/a/a/a/b/a:b	(Ljava/io/InputStream;)Ljava/lang/String;
    //   260: invokevirtual 164	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   263: aload_1
    //   264: astore_2
    //   265: aload_1
    //   266: astore 5
    //   268: aload 8
    //   270: ldc -14
    //   272: aload 6
    //   274: getfield 245	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   277: invokevirtual 164	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   280: aload_1
    //   281: astore_2
    //   282: aload_1
    //   283: astore 5
    //   285: aload 8
    //   287: ldc -105
    //   289: aload 6
    //   291: getfield 203	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   294: getfield 209	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   297: invokevirtual 164	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   300: aload_1
    //   301: astore_2
    //   302: aload_1
    //   303: astore 5
    //   305: aload 8
    //   307: ldc -9
    //   309: new 249	java/lang/StringBuilder
    //   312: dup
    //   313: invokespecial 250	java/lang/StringBuilder:<init>	()V
    //   316: aload 6
    //   318: getfield 254	android/content/pm/PackageInfo:versionCode	I
    //   321: invokevirtual 258	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   324: ldc_w 260
    //   327: invokevirtual 263	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   330: invokevirtual 267	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   333: invokevirtual 164	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   336: aload_1
    //   337: astore_2
    //   338: aload_1
    //   339: astore 5
    //   341: aload 8
    //   343: ldc 105
    //   345: iconst_0
    //   346: invokestatic 111	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   349: invokevirtual 115	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   352: aload_1
    //   353: astore_2
    //   354: aload_1
    //   355: astore 5
    //   357: aload 8
    //   359: ldc_w 269
    //   362: invokestatic 275	java/lang/System:currentTimeMillis	()J
    //   365: invokestatic 280	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   368: invokevirtual 283	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Long;)V
    //   371: aload_1
    //   372: astore_2
    //   373: aload_1
    //   374: astore 5
    //   376: aload_3
    //   377: ldc 121
    //   379: aconst_null
    //   380: aload 8
    //   382: invokevirtual 168	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   385: pop2
    //   386: aload_1
    //   387: astore_2
    //   388: aload_2
    //   389: astore_1
    //   390: aload 4
    //   392: astore_2
    //   393: aload_1
    //   394: ifnull +13 -> 407
    //   397: aload_1
    //   398: astore 5
    //   400: aload_2
    //   401: astore 7
    //   403: aload_1
    //   404: invokevirtual 286	java/io/InputStream:close	()V
    //   407: aload_1
    //   408: astore 4
    //   410: aload_2
    //   411: astore 5
    //   413: aload_2
    //   414: ifnull +25 -> 439
    //   417: aload_1
    //   418: astore 4
    //   420: aload_2
    //   421: astore 6
    //   423: aload_1
    //   424: astore 5
    //   426: aload_2
    //   427: astore 7
    //   429: aload_2
    //   430: invokevirtual 287	java/util/zip/ZipFile:close	()V
    //   433: aload_2
    //   434: astore 5
    //   436: aload_1
    //   437: astore 4
    //   439: aload 5
    //   441: astore_2
    //   442: aload 4
    //   444: astore_1
    //   445: goto -314 -> 131
    //   448: astore_1
    //   449: aload_2
    //   450: astore_1
    //   451: aload 4
    //   453: astore_2
    //   454: aload_1
    //   455: ifnull +13 -> 468
    //   458: aload_1
    //   459: astore 5
    //   461: aload_2
    //   462: astore 7
    //   464: aload_1
    //   465: invokevirtual 286	java/io/InputStream:close	()V
    //   468: aload_1
    //   469: astore 4
    //   471: aload_2
    //   472: astore 5
    //   474: aload_2
    //   475: ifnull -36 -> 439
    //   478: aload_1
    //   479: astore 4
    //   481: aload_2
    //   482: astore 6
    //   484: aload_1
    //   485: astore 5
    //   487: aload_2
    //   488: astore 7
    //   490: aload_2
    //   491: invokevirtual 287	java/util/zip/ZipFile:close	()V
    //   494: aload_1
    //   495: astore 4
    //   497: aload_2
    //   498: astore 5
    //   500: goto -61 -> 439
    //   503: astore_1
    //   504: aload 6
    //   506: astore_2
    //   507: aload 4
    //   509: astore_1
    //   510: aload_3
    //   511: ifnull +7 -> 518
    //   514: aload_3
    //   515: invokevirtual 74	android/database/sqlite/SQLiteDatabase:close	()V
    //   518: aload_0
    //   519: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   522: ifnull +10 -> 532
    //   525: aload_0
    //   526: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   529: invokevirtual 75	com/antivirus/apploader/e:close	()V
    //   532: aload_1
    //   533: ifnull +7 -> 540
    //   536: aload_1
    //   537: invokevirtual 286	java/io/InputStream:close	()V
    //   540: aload_2
    //   541: ifnull +7 -> 548
    //   544: aload_2
    //   545: invokevirtual 287	java/util/zip/ZipFile:close	()V
    //   548: return
    //   549: astore 6
    //   551: aload 4
    //   553: astore_2
    //   554: aload 5
    //   556: astore_1
    //   557: aload 6
    //   559: astore 4
    //   561: aload_1
    //   562: ifnull +7 -> 569
    //   565: aload_1
    //   566: invokevirtual 286	java/io/InputStream:close	()V
    //   569: aload_2
    //   570: ifnull +7 -> 577
    //   573: aload_2
    //   574: invokevirtual 287	java/util/zip/ZipFile:close	()V
    //   577: aload 4
    //   579: athrow
    //   580: astore 4
    //   582: goto -72 -> 510
    //   585: aload_3
    //   586: invokevirtual 133	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   589: aload_3
    //   590: invokevirtual 136	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   593: aload_3
    //   594: invokevirtual 74	android/database/sqlite/SQLiteDatabase:close	()V
    //   597: aload_0
    //   598: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   601: invokevirtual 75	com/antivirus/apploader/e:close	()V
    //   604: aload_3
    //   605: ifnull +7 -> 612
    //   608: aload_3
    //   609: invokevirtual 74	android/database/sqlite/SQLiteDatabase:close	()V
    //   612: aload_0
    //   613: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   616: ifnull +10 -> 626
    //   619: aload_0
    //   620: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   623: invokevirtual 75	com/antivirus/apploader/e:close	()V
    //   626: aload_1
    //   627: ifnull +7 -> 634
    //   630: aload_1
    //   631: invokevirtual 286	java/io/InputStream:close	()V
    //   634: aload_2
    //   635: ifnull -87 -> 548
    //   638: aload_2
    //   639: invokevirtual 287	java/util/zip/ZipFile:close	()V
    //   642: return
    //   643: astore_1
    //   644: return
    //   645: astore 4
    //   647: aconst_null
    //   648: astore_2
    //   649: aconst_null
    //   650: astore_3
    //   651: aload 5
    //   653: astore_1
    //   654: aload_3
    //   655: ifnull +7 -> 662
    //   658: aload_3
    //   659: invokevirtual 74	android/database/sqlite/SQLiteDatabase:close	()V
    //   662: aload_0
    //   663: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   666: ifnull +10 -> 676
    //   669: aload_0
    //   670: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   673: invokevirtual 75	com/antivirus/apploader/e:close	()V
    //   676: aload_1
    //   677: ifnull +7 -> 684
    //   680: aload_1
    //   681: invokevirtual 286	java/io/InputStream:close	()V
    //   684: aload_2
    //   685: ifnull +7 -> 692
    //   688: aload_2
    //   689: invokevirtual 287	java/util/zip/ZipFile:close	()V
    //   692: aload 4
    //   694: athrow
    //   695: astore 4
    //   697: goto -290 -> 407
    //   700: astore 4
    //   702: goto -234 -> 468
    //   705: astore 5
    //   707: goto -138 -> 569
    //   710: astore_1
    //   711: goto -77 -> 634
    //   714: astore_1
    //   715: goto -175 -> 540
    //   718: astore_1
    //   719: return
    //   720: astore_1
    //   721: goto -37 -> 684
    //   724: astore_1
    //   725: goto -33 -> 692
    //   728: astore 4
    //   730: aconst_null
    //   731: astore_2
    //   732: aload 5
    //   734: astore_1
    //   735: goto -81 -> 654
    //   738: astore 4
    //   740: aload 7
    //   742: astore_2
    //   743: aload 5
    //   745: astore_1
    //   746: goto -92 -> 654
    //   749: astore 4
    //   751: goto -97 -> 654
    //   754: astore 4
    //   756: goto -102 -> 654
    //   759: astore_1
    //   760: aconst_null
    //   761: astore 4
    //   763: aconst_null
    //   764: astore_3
    //   765: aload_2
    //   766: astore_1
    //   767: aload 4
    //   769: astore_2
    //   770: goto -260 -> 510
    //   773: astore_1
    //   774: aconst_null
    //   775: astore 4
    //   777: aload_2
    //   778: astore_1
    //   779: aload 4
    //   781: astore_2
    //   782: goto -272 -> 510
    //   785: astore 4
    //   787: goto -277 -> 510
    //   790: astore 4
    //   792: goto -231 -> 561
    //   795: astore 4
    //   797: goto -343 -> 454
    //   800: goto -407 -> 393
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	803	0	this	f
    //   0	803	1	paramContext	Context
    //   1	781	2	localObject1	Object
    //   40	725	3	localSQLiteDatabase	SQLiteDatabase
    //   6	572	4	localObject2	Object
    //   580	1	4	localException1	Exception
    //   645	48	4	localObject3	Object
    //   695	1	4	localException2	Exception
    //   700	1	4	localException3	Exception
    //   728	1	4	localObject4	Object
    //   738	1	4	localObject5	Object
    //   749	1	4	localObject6	Object
    //   754	1	4	localObject7	Object
    //   761	19	4	localObject8	Object
    //   785	1	4	localException4	Exception
    //   790	1	4	localObject9	Object
    //   795	1	4	localException5	Exception
    //   3	649	5	localObject10	Object
    //   705	39	5	localException6	Exception
    //   151	354	6	localObject11	Object
    //   549	9	6	localObject12	Object
    //   219	522	7	localObject13	Object
    //   15	366	8	localContentValues	ContentValues
    //   124	18	9	localIterator	java.util.Iterator
    // Exception table:
    //   from	to	target	type
    //   212	221	448	java/lang/Exception
    //   233	241	448	java/lang/Exception
    //   252	263	448	java/lang/Exception
    //   268	280	448	java/lang/Exception
    //   285	300	448	java/lang/Exception
    //   305	336	448	java/lang/Exception
    //   341	352	448	java/lang/Exception
    //   357	371	448	java/lang/Exception
    //   376	386	448	java/lang/Exception
    //   429	433	503	java/lang/Exception
    //   490	494	503	java/lang/Exception
    //   212	221	549	finally
    //   233	241	549	finally
    //   252	263	549	finally
    //   268	280	549	finally
    //   285	300	549	finally
    //   305	336	549	finally
    //   341	352	549	finally
    //   357	371	549	finally
    //   376	386	549	finally
    //   573	577	580	java/lang/Exception
    //   577	580	580	java/lang/Exception
    //   638	642	643	java/lang/Exception
    //   17	41	645	finally
    //   403	407	695	java/lang/Exception
    //   464	468	700	java/lang/Exception
    //   565	569	705	java/lang/Exception
    //   630	634	710	java/lang/Exception
    //   536	540	714	java/lang/Exception
    //   544	548	718	java/lang/Exception
    //   680	684	720	java/lang/Exception
    //   688	692	724	java/lang/Exception
    //   41	126	728	finally
    //   403	407	738	finally
    //   429	433	738	finally
    //   464	468	738	finally
    //   490	494	738	finally
    //   565	569	749	finally
    //   573	577	749	finally
    //   577	580	749	finally
    //   131	153	754	finally
    //   585	604	754	finally
    //   17	41	759	java/lang/Exception
    //   41	126	773	java/lang/Exception
    //   131	153	785	java/lang/Exception
    //   585	604	785	java/lang/Exception
    //   153	175	790	finally
    //   180	207	790	finally
    //   153	175	795	java/lang/Exception
    //   180	207	795	java/lang/Exception
  }
  
  /* Error */
  public java.util.TreeMap e(Context paramContext)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: new 84	java/util/TreeMap
    //   5: dup
    //   6: invokespecial 290	java/util/TreeMap:<init>	()V
    //   9: astore 4
    //   11: aload_0
    //   12: getfield 14	com/antivirus/apploader/f:a	Landroid/database/sqlite/SQLiteDatabase;
    //   15: ifnonnull +30 -> 45
    //   18: aload_0
    //   19: new 22	com/antivirus/apploader/e
    //   22: dup
    //   23: aload_1
    //   24: ldc 24
    //   26: aconst_null
    //   27: iconst_1
    //   28: invokespecial 27	com/antivirus/apploader/e:<init>	(Landroid/content/Context;Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)V
    //   31: putfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   34: aload_0
    //   35: aload_0
    //   36: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   39: invokevirtual 31	com/antivirus/apploader/e:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   42: putfield 14	com/antivirus/apploader/f:a	Landroid/database/sqlite/SQLiteDatabase;
    //   45: aload_0
    //   46: getfield 14	com/antivirus/apploader/f:a	Landroid/database/sqlite/SQLiteDatabase;
    //   49: ldc 121
    //   51: iconst_2
    //   52: anewarray 35	java/lang/String
    //   55: dup
    //   56: iconst_0
    //   57: ldc -14
    //   59: aastore
    //   60: dup
    //   61: iconst_1
    //   62: ldc -21
    //   64: aastore
    //   65: ldc_w 292
    //   68: aconst_null
    //   69: aconst_null
    //   70: aconst_null
    //   71: aconst_null
    //   72: invokevirtual 45	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   75: astore_1
    //   76: aload_1
    //   77: ifnull +55 -> 132
    //   80: aload_1
    //   81: invokeinterface 51 1 0
    //   86: ifle +46 -> 132
    //   89: iconst_0
    //   90: istore_2
    //   91: aload_1
    //   92: invokeinterface 55 1 0
    //   97: ifeq +35 -> 132
    //   100: iload_2
    //   101: iconst_5
    //   102: if_icmpge +30 -> 132
    //   105: aload 4
    //   107: aload_1
    //   108: iconst_0
    //   109: invokeinterface 59 2 0
    //   114: aload_1
    //   115: iconst_1
    //   116: invokeinterface 59 2 0
    //   121: invokevirtual 295	java/util/TreeMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   124: pop
    //   125: iload_2
    //   126: iconst_1
    //   127: iadd
    //   128: istore_2
    //   129: goto -38 -> 91
    //   132: aload_1
    //   133: ifnull +9 -> 142
    //   136: aload_1
    //   137: invokeinterface 68 1 0
    //   142: aload_0
    //   143: invokevirtual 70	com/antivirus/apploader/f:a	()V
    //   146: aload 4
    //   148: areturn
    //   149: astore_1
    //   150: aconst_null
    //   151: astore_1
    //   152: aload_1
    //   153: ifnull +9 -> 162
    //   156: aload_1
    //   157: invokeinterface 68 1 0
    //   162: aload_0
    //   163: invokevirtual 70	com/antivirus/apploader/f:a	()V
    //   166: aload 4
    //   168: areturn
    //   169: astore 4
    //   171: aload_3
    //   172: astore_1
    //   173: aload 4
    //   175: astore_3
    //   176: aload_1
    //   177: ifnull +9 -> 186
    //   180: aload_1
    //   181: invokeinterface 68 1 0
    //   186: aload_0
    //   187: invokevirtual 70	com/antivirus/apploader/f:a	()V
    //   190: aload_3
    //   191: athrow
    //   192: astore_3
    //   193: goto -17 -> 176
    //   196: astore_3
    //   197: goto -45 -> 152
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	200	0	this	f
    //   0	200	1	paramContext	Context
    //   90	39	2	i	int
    //   1	190	3	localObject1	Object
    //   192	1	3	localObject2	Object
    //   196	1	3	localException	Exception
    //   9	158	4	localTreeMap	java.util.TreeMap
    //   169	5	4	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   45	76	149	java/lang/Exception
    //   45	76	169	finally
    //   80	89	192	finally
    //   91	100	192	finally
    //   105	125	192	finally
    //   80	89	196	java/lang/Exception
    //   91	100	196	java/lang/Exception
    //   105	125	196	java/lang/Exception
  }
  
  /* Error */
  public void f(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: new 22	com/antivirus/apploader/e
    //   4: dup
    //   5: aload_1
    //   6: ldc 24
    //   8: aconst_null
    //   9: iconst_1
    //   10: invokespecial 27	com/antivirus/apploader/e:<init>	(Landroid/content/Context;Ljava/lang/String;Landroid/database/sqlite/SQLiteDatabase$CursorFactory;I)V
    //   13: putfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   16: aload_0
    //   17: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   20: invokevirtual 31	com/antivirus/apploader/e:getReadableDatabase	()Landroid/database/sqlite/SQLiteDatabase;
    //   23: astore_1
    //   24: aload_1
    //   25: invokevirtual 82	android/database/sqlite/SQLiteDatabase:beginTransaction	()V
    //   28: aload_1
    //   29: ldc_w 298
    //   32: invokevirtual 301	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   35: aload_1
    //   36: invokevirtual 133	android/database/sqlite/SQLiteDatabase:setTransactionSuccessful	()V
    //   39: aload_1
    //   40: invokevirtual 136	android/database/sqlite/SQLiteDatabase:endTransaction	()V
    //   43: aload_1
    //   44: invokevirtual 74	android/database/sqlite/SQLiteDatabase:close	()V
    //   47: aload_0
    //   48: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   51: invokevirtual 75	com/antivirus/apploader/e:close	()V
    //   54: iconst_0
    //   55: ifeq +11 -> 66
    //   58: new 173	java/lang/NullPointerException
    //   61: dup
    //   62: invokespecial 174	java/lang/NullPointerException:<init>	()V
    //   65: athrow
    //   66: aload_1
    //   67: ifnull +7 -> 74
    //   70: aload_1
    //   71: invokevirtual 74	android/database/sqlite/SQLiteDatabase:close	()V
    //   74: aload_0
    //   75: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   78: ifnull +10 -> 88
    //   81: aload_0
    //   82: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   85: invokevirtual 75	com/antivirus/apploader/e:close	()V
    //   88: return
    //   89: astore_1
    //   90: aconst_null
    //   91: astore_1
    //   92: iconst_0
    //   93: ifeq +11 -> 104
    //   96: new 173	java/lang/NullPointerException
    //   99: dup
    //   100: invokespecial 174	java/lang/NullPointerException:<init>	()V
    //   103: athrow
    //   104: aload_1
    //   105: ifnull +7 -> 112
    //   108: aload_1
    //   109: invokevirtual 74	android/database/sqlite/SQLiteDatabase:close	()V
    //   112: aload_0
    //   113: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   116: ifnull -28 -> 88
    //   119: aload_0
    //   120: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   123: invokevirtual 75	com/antivirus/apploader/e:close	()V
    //   126: return
    //   127: astore_2
    //   128: aconst_null
    //   129: astore_1
    //   130: iconst_0
    //   131: ifeq +11 -> 142
    //   134: new 173	java/lang/NullPointerException
    //   137: dup
    //   138: invokespecial 174	java/lang/NullPointerException:<init>	()V
    //   141: athrow
    //   142: aload_1
    //   143: ifnull +7 -> 150
    //   146: aload_1
    //   147: invokevirtual 74	android/database/sqlite/SQLiteDatabase:close	()V
    //   150: aload_0
    //   151: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   154: ifnull +10 -> 164
    //   157: aload_0
    //   158: getfield 16	com/antivirus/apploader/f:b	Lcom/antivirus/apploader/e;
    //   161: invokevirtual 75	com/antivirus/apploader/e:close	()V
    //   164: aload_2
    //   165: athrow
    //   166: astore_2
    //   167: goto -37 -> 130
    //   170: astore_2
    //   171: goto -79 -> 92
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	174	0	this	f
    //   0	174	1	paramContext	Context
    //   127	38	2	localObject1	Object
    //   166	1	2	localObject2	Object
    //   170	1	2	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   0	24	89	java/lang/Exception
    //   0	24	127	finally
    //   24	54	166	finally
    //   24	54	170	java/lang/Exception
  }
  
  public void g(Context paramContext)
  {
    localContext2 = null;
    Context localContext1 = localContext2;
    try
    {
      this.b = new e(paramContext, "uppdb", null, 1);
      localContext1 = localContext2;
      paramContext = this.b.getWritableDatabase();
      localContext1 = paramContext;
      if (localContext2 == null) {
        break label99;
      }
    }
    catch (Exception paramContext)
    {
      do
      {
        paramContext = paramContext;
        if (localContext1 != null) {
          localContext1.close();
        }
      } while (this.b == null);
      this.b.close();
      return;
    }
    finally
    {
      localContext2 = null;
      localContext1 = paramContext;
    }
    localContext2.close();
    label99:
    if (this.b != null) {
      this.b.close();
    }
    throw localContext1;
  }
}
