package com.a.a;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.DeadObjectException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class i
  extends SQLiteOpenHelper
{
  private static final int a = Integer.parseInt("1.4.4".replaceAll("\\.", ""));
  private static i b;
  private static SQLiteDatabase c = null;
  
  private i(Context paramContext)
  {
    super(paramContext, "mologiq", null, a);
  }
  
  static SQLiteDatabase a()
  {
    if (c == null) {
      return b.getWritableDatabase();
    }
    return c;
  }
  
  static i a(Context paramContext)
  {
    try
    {
      if (b == null)
      {
        b = new i(paramContext);
        if (c == null) {
          c = a();
        }
      }
      paramContext = b;
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      for (;;)
      {
        y.a(y.a(paramContext));
      }
    }
    finally {}
  }
  
  private void a(String paramString1, String paramString2, int paramInt)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put(paramString1, paramString2);
      paramString1 = a();
      c = paramString1;
      paramString1.update("appRequestResponse", localContentValues, "id= ?", new String[] { String.valueOf(paramInt) });
      return;
    }
    finally
    {
      paramString1 = finally;
      throw paramString1;
    }
  }
  
  private int b(f paramF)
  {
    try
    {
      c = a();
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("id", Integer.valueOf(paramF.c()));
      localContentValues.put("name", paramF.b());
      localContentValues.put("classification_id", Integer.valueOf(paramF.a()));
      int i = c.update("applist", localContentValues, "id = ?", new String[] { String.valueOf(paramF.c()) });
      return i;
    }
    finally
    {
      paramF = finally;
      throw paramF;
    }
  }
  
  private int b(String paramString1, String paramString2)
  {
    try
    {
      ContentValues localContentValues = new ContentValues();
      localContentValues.put(paramString1, paramString2);
      paramString1 = a();
      c = paramString1;
      long l = paramString1.insert("appRequestResponse", null, localContentValues);
      int i = (int)l;
      return i;
    }
    finally
    {
      paramString1 = finally;
      throw paramString1;
    }
  }
  
  /* Error */
  private f b(int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: monitorenter
    //   4: invokestatic 57	com/a/a/i:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   7: astore_3
    //   8: aload_3
    //   9: putstatic 36	com/a/a/i:c	Landroid/database/sqlite/SQLiteDatabase;
    //   12: aload_3
    //   13: ldc 114
    //   15: iconst_3
    //   16: anewarray 22	java/lang/String
    //   19: dup
    //   20: iconst_0
    //   21: ldc 92
    //   23: aastore
    //   24: dup
    //   25: iconst_1
    //   26: ldc 105
    //   28: aastore
    //   29: dup
    //   30: iconst_2
    //   31: ldc 110
    //   33: aastore
    //   34: ldc 124
    //   36: iconst_1
    //   37: anewarray 22	java/lang/String
    //   40: dup
    //   41: iconst_0
    //   42: iload_1
    //   43: invokestatic 83	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   46: aastore
    //   47: aconst_null
    //   48: aconst_null
    //   49: aconst_null
    //   50: aconst_null
    //   51: invokevirtual 128	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   54: astore_3
    //   55: aload_3
    //   56: ifnull +116 -> 172
    //   59: aload_3
    //   60: invokeinterface 134 1 0
    //   65: ifeq +107 -> 172
    //   68: aload_3
    //   69: invokeinterface 137 1 0
    //   74: ifle +98 -> 172
    //   77: new 94	com/a/a/f
    //   80: dup
    //   81: invokespecial 138	com/a/a/f:<init>	()V
    //   84: astore 4
    //   86: aload 4
    //   88: aload_3
    //   89: iconst_0
    //   90: invokeinterface 141 2 0
    //   95: invokestatic 32	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   98: invokevirtual 144	com/a/a/f:b	(I)V
    //   101: aload 4
    //   103: aload_3
    //   104: iconst_1
    //   105: invokeinterface 141 2 0
    //   110: invokevirtual 145	com/a/a/f:a	(Ljava/lang/String;)V
    //   113: aload 4
    //   115: aload_3
    //   116: iconst_2
    //   117: invokeinterface 141 2 0
    //   122: invokestatic 32	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   125: invokevirtual 147	com/a/a/f:a	(I)V
    //   128: aload 4
    //   130: astore_2
    //   131: aload_3
    //   132: ifnull +12 -> 144
    //   135: aload_3
    //   136: invokeinterface 150 1 0
    //   141: aload 4
    //   143: astore_2
    //   144: aload_0
    //   145: monitorexit
    //   146: aload_2
    //   147: areturn
    //   148: astore 4
    //   150: aload_2
    //   151: astore_3
    //   152: aload 4
    //   154: astore_2
    //   155: aload_3
    //   156: ifnull +9 -> 165
    //   159: aload_3
    //   160: invokeinterface 150 1 0
    //   165: aload_2
    //   166: athrow
    //   167: astore_2
    //   168: aload_0
    //   169: monitorexit
    //   170: aload_2
    //   171: athrow
    //   172: aload_3
    //   173: ifnull +9 -> 182
    //   176: aload_3
    //   177: invokeinterface 150 1 0
    //   182: aconst_null
    //   183: astore_2
    //   184: goto -40 -> 144
    //   187: astore_2
    //   188: goto -33 -> 155
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	191	0	this	i
    //   0	191	1	paramInt	int
    //   1	165	2	localObject1	Object
    //   167	4	2	localObject2	Object
    //   183	1	2	localObject3	Object
    //   187	1	2	localObject4	Object
    //   7	170	3	localObject5	Object
    //   84	58	4	localF	f
    //   148	5	4	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   4	55	148	finally
    //   135	141	167	finally
    //   159	165	167	finally
    //   165	167	167	finally
    //   176	182	167	finally
    //   59	128	187	finally
  }
  
  /* Error */
  private k.a c(int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: monitorenter
    //   4: invokestatic 57	com/a/a/i:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   7: astore_3
    //   8: aload_3
    //   9: putstatic 36	com/a/a/i:c	Landroid/database/sqlite/SQLiteDatabase;
    //   12: aload_3
    //   13: ldc -103
    //   15: iconst_3
    //   16: anewarray 22	java/lang/String
    //   19: dup
    //   20: iconst_0
    //   21: ldc 92
    //   23: aastore
    //   24: dup
    //   25: iconst_1
    //   26: ldc -101
    //   28: aastore
    //   29: dup
    //   30: iconst_2
    //   31: ldc -99
    //   33: aastore
    //   34: ldc 124
    //   36: iconst_1
    //   37: anewarray 22	java/lang/String
    //   40: dup
    //   41: iconst_0
    //   42: iload_1
    //   43: invokestatic 83	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   46: aastore
    //   47: aconst_null
    //   48: aconst_null
    //   49: aconst_null
    //   50: aconst_null
    //   51: invokevirtual 128	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   54: astore_3
    //   55: aload_3
    //   56: ifnull +171 -> 227
    //   59: aload_3
    //   60: invokeinterface 134 1 0
    //   65: ifeq +162 -> 227
    //   68: aload_3
    //   69: invokeinterface 137 1 0
    //   74: ifle +153 -> 227
    //   77: invokestatic 162	com/a/a/k:a	()Lcom/a/a/k;
    //   80: astore_2
    //   81: aload_2
    //   82: invokevirtual 168	java/lang/Object:getClass	()Ljava/lang/Class;
    //   85: pop
    //   86: new 170	com/a/a/k$a
    //   89: dup
    //   90: aload_2
    //   91: invokespecial 173	com/a/a/k$a:<init>	(Lcom/a/a/k;)V
    //   94: astore 4
    //   96: aload 4
    //   98: aload_3
    //   99: iconst_0
    //   100: invokeinterface 177 2 0
    //   105: invokevirtual 178	com/a/a/k$a:a	(I)V
    //   108: aload_3
    //   109: iconst_1
    //   110: invokeinterface 141 2 0
    //   115: ldc -76
    //   117: invokevirtual 184	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   120: astore_2
    //   121: new 186	java/util/ArrayList
    //   124: dup
    //   125: invokespecial 187	java/util/ArrayList:<init>	()V
    //   128: astore 5
    //   130: iconst_1
    //   131: istore_1
    //   132: iload_1
    //   133: aload_2
    //   134: arraylength
    //   135: iconst_1
    //   136: isub
    //   137: if_icmplt +42 -> 179
    //   140: aload 4
    //   142: aload 5
    //   144: invokevirtual 190	com/a/a/k$a:a	(Ljava/util/List;)V
    //   147: aload 4
    //   149: aload_3
    //   150: iconst_2
    //   151: invokeinterface 177 2 0
    //   156: invokevirtual 191	com/a/a/k$a:b	(I)V
    //   159: aload 4
    //   161: astore_2
    //   162: aload_3
    //   163: ifnull +12 -> 175
    //   166: aload_3
    //   167: invokeinterface 150 1 0
    //   172: aload 4
    //   174: astore_2
    //   175: aload_0
    //   176: monitorexit
    //   177: aload_2
    //   178: areturn
    //   179: aload 5
    //   181: aload_2
    //   182: iload_1
    //   183: aaload
    //   184: invokestatic 32	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   187: invokestatic 100	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   190: invokeinterface 197 2 0
    //   195: pop
    //   196: iload_1
    //   197: iconst_1
    //   198: iadd
    //   199: istore_1
    //   200: goto -68 -> 132
    //   203: astore 4
    //   205: aload_2
    //   206: astore_3
    //   207: aload 4
    //   209: astore_2
    //   210: aload_3
    //   211: ifnull +9 -> 220
    //   214: aload_3
    //   215: invokeinterface 150 1 0
    //   220: aload_2
    //   221: athrow
    //   222: astore_2
    //   223: aload_0
    //   224: monitorexit
    //   225: aload_2
    //   226: athrow
    //   227: aload_3
    //   228: ifnull +9 -> 237
    //   231: aload_3
    //   232: invokeinterface 150 1 0
    //   237: aconst_null
    //   238: astore_2
    //   239: goto -64 -> 175
    //   242: astore_2
    //   243: goto -33 -> 210
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	246	0	this	i
    //   0	246	1	paramInt	int
    //   1	220	2	localObject1	Object
    //   222	4	2	localObject2	Object
    //   238	1	2	localObject3	Object
    //   242	1	2	localObject4	Object
    //   7	225	3	localObject5	Object
    //   94	79	4	localA	k.a
    //   203	5	4	localObject6	Object
    //   128	52	5	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   4	55	203	finally
    //   166	172	222	finally
    //   214	220	222	finally
    //   220	222	222	finally
    //   231	237	222	finally
    //   59	130	242	finally
    //   132	159	242	finally
    //   179	196	242	finally
  }
  
  /* Error */
  private k.b i()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic 162	com/a/a/k:a	()Lcom/a/a/k;
    //   5: astore_2
    //   6: aload_2
    //   7: invokevirtual 168	java/lang/Object:getClass	()Ljava/lang/Class;
    //   10: pop
    //   11: new 202	com/a/a/k$b
    //   14: dup
    //   15: aload_2
    //   16: invokespecial 203	com/a/a/k$b:<init>	(Lcom/a/a/k;)V
    //   19: astore_3
    //   20: new 186	java/util/ArrayList
    //   23: dup
    //   24: invokespecial 187	java/util/ArrayList:<init>	()V
    //   27: astore 4
    //   29: invokestatic 57	com/a/a/i:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   32: astore_2
    //   33: aload_2
    //   34: putstatic 36	com/a/a/i:c	Landroid/database/sqlite/SQLiteDatabase;
    //   37: aload_2
    //   38: ldc -103
    //   40: aconst_null
    //   41: aconst_null
    //   42: aconst_null
    //   43: aconst_null
    //   44: aconst_null
    //   45: aconst_null
    //   46: invokevirtual 206	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   49: astore_2
    //   50: aload_2
    //   51: ifnull +133 -> 184
    //   54: aload_2
    //   55: invokeinterface 134 1 0
    //   60: ifeq +124 -> 184
    //   63: aload_2
    //   64: invokeinterface 137 1 0
    //   69: ifle +115 -> 184
    //   72: invokestatic 162	com/a/a/k:a	()Lcom/a/a/k;
    //   75: astore 5
    //   77: aload 5
    //   79: invokevirtual 168	java/lang/Object:getClass	()Ljava/lang/Class;
    //   82: pop
    //   83: new 170	com/a/a/k$a
    //   86: dup
    //   87: aload 5
    //   89: invokespecial 173	com/a/a/k$a:<init>	(Lcom/a/a/k;)V
    //   92: astore 5
    //   94: aload 5
    //   96: aload_2
    //   97: iconst_0
    //   98: invokeinterface 177 2 0
    //   103: invokevirtual 178	com/a/a/k$a:a	(I)V
    //   106: aload_2
    //   107: iconst_1
    //   108: invokeinterface 141 2 0
    //   113: ldc -76
    //   115: invokevirtual 184	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   118: astore 6
    //   120: new 186	java/util/ArrayList
    //   123: dup
    //   124: invokespecial 187	java/util/ArrayList:<init>	()V
    //   127: astore 7
    //   129: iconst_1
    //   130: istore_1
    //   131: iload_1
    //   132: aload 6
    //   134: arraylength
    //   135: iconst_1
    //   136: isub
    //   137: if_icmplt +82 -> 219
    //   140: aload 5
    //   142: aload 7
    //   144: invokevirtual 190	com/a/a/k$a:a	(Ljava/util/List;)V
    //   147: aload 5
    //   149: aload_2
    //   150: iconst_2
    //   151: invokeinterface 177 2 0
    //   156: invokevirtual 191	com/a/a/k$a:b	(I)V
    //   159: aload 4
    //   161: aload 5
    //   163: invokeinterface 197 2 0
    //   168: pop
    //   169: aload_2
    //   170: invokeinterface 209 1 0
    //   175: ifne -103 -> 72
    //   178: aload_3
    //   179: aload 4
    //   181: invokevirtual 210	com/a/a/k$b:a	(Ljava/util/List;)V
    //   184: aload_2
    //   185: ifnull +9 -> 194
    //   188: aload_2
    //   189: invokeinterface 150 1 0
    //   194: aload_0
    //   195: invokevirtual 213	com/a/a/i:e	()Lcom/a/a/k$b;
    //   198: astore_2
    //   199: aload_3
    //   200: aload_2
    //   201: invokevirtual 215	com/a/a/k$b:a	()Ljava/lang/String;
    //   204: invokevirtual 216	com/a/a/k$b:a	(Ljava/lang/String;)V
    //   207: aload_3
    //   208: aload_2
    //   209: invokevirtual 218	com/a/a/k$b:b	()I
    //   212: invokevirtual 219	com/a/a/k$b:a	(I)V
    //   215: aload_0
    //   216: monitorexit
    //   217: aload_3
    //   218: areturn
    //   219: aload 7
    //   221: aload 6
    //   223: iload_1
    //   224: aaload
    //   225: invokestatic 32	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   228: invokestatic 100	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   231: invokeinterface 197 2 0
    //   236: pop
    //   237: iload_1
    //   238: iconst_1
    //   239: iadd
    //   240: istore_1
    //   241: goto -110 -> 131
    //   244: astore_3
    //   245: aconst_null
    //   246: astore_2
    //   247: aload_2
    //   248: ifnull +9 -> 257
    //   251: aload_2
    //   252: invokeinterface 150 1 0
    //   257: aload_3
    //   258: athrow
    //   259: astore_2
    //   260: aload_0
    //   261: monitorexit
    //   262: aload_2
    //   263: athrow
    //   264: astore_3
    //   265: goto -18 -> 247
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	268	0	this	i
    //   130	111	1	i	int
    //   5	247	2	localObject1	Object
    //   259	4	2	localObject2	Object
    //   19	199	3	localB	k.b
    //   244	14	3	localObject3	Object
    //   264	1	3	localObject4	Object
    //   27	153	4	localArrayList1	ArrayList
    //   75	87	5	localObject5	Object
    //   118	104	6	arrayOfString	String[]
    //   127	93	7	localArrayList2	ArrayList
    // Exception table:
    //   from	to	target	type
    //   29	50	244	finally
    //   2	29	259	finally
    //   188	194	259	finally
    //   194	215	259	finally
    //   251	257	259	finally
    //   257	259	259	finally
    //   54	72	264	finally
    //   72	129	264	finally
    //   131	184	264	finally
    //   219	237	264	finally
  }
  
  /* Error */
  private int j()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: monitorenter
    //   5: iconst_m1
    //   6: istore_2
    //   7: aload 4
    //   9: astore_3
    //   10: invokestatic 57	com/a/a/i:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   13: putstatic 36	com/a/a/i:c	Landroid/database/sqlite/SQLiteDatabase;
    //   16: aload 4
    //   18: astore_3
    //   19: getstatic 36	com/a/a/i:c	Landroid/database/sqlite/SQLiteDatabase;
    //   22: ldc -34
    //   24: aconst_null
    //   25: invokevirtual 226	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   28: astore 4
    //   30: iload_2
    //   31: istore_1
    //   32: aload 4
    //   34: ifnull +38 -> 72
    //   37: iload_2
    //   38: istore_1
    //   39: aload 4
    //   41: astore_3
    //   42: aload 4
    //   44: invokeinterface 134 1 0
    //   49: ifeq +23 -> 72
    //   52: aload 4
    //   54: astore_3
    //   55: aload 4
    //   57: aload 4
    //   59: ldc 92
    //   61: invokeinterface 229 2 0
    //   66: invokeinterface 177 2 0
    //   71: istore_1
    //   72: aload 4
    //   74: ifnull +10 -> 84
    //   77: aload 4
    //   79: invokeinterface 150 1 0
    //   84: aload_0
    //   85: monitorexit
    //   86: iload_1
    //   87: ireturn
    //   88: astore 4
    //   90: aload_3
    //   91: ifnull +9 -> 100
    //   94: aload_3
    //   95: invokeinterface 150 1 0
    //   100: aload 4
    //   102: athrow
    //   103: astore_3
    //   104: aload_0
    //   105: monitorexit
    //   106: aload_3
    //   107: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	108	0	this	i
    //   31	56	1	i	int
    //   6	32	2	j	int
    //   9	86	3	localCursor1	Cursor
    //   103	4	3	localObject1	Object
    //   1	77	4	localCursor2	Cursor
    //   88	13	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   10	16	88	finally
    //   19	30	88	finally
    //   42	52	88	finally
    //   55	72	88	finally
    //   77	84	103	finally
    //   94	100	103	finally
    //   100	103	103	finally
  }
  
  final String a(String paramString)
  {
    Object localObject3 = null;
    try
    {
      c = a();
      localObject1 = "SELECT " + paramString + " FROM appRequestResponse";
      localObject1 = c.rawQuery((String)localObject1, null);
      Object localObject2 = localObject3;
      if (localObject1 != null) {
        localObject2 = localObject3;
      }
      if (localObject1 == null) {
        break label102;
      }
    }
    finally
    {
      for (;;)
      {
        Object localObject1;
        try
        {
          if (((Cursor)localObject1).moveToFirst()) {
            localObject2 = ((Cursor)localObject1).getString(((Cursor)localObject1).getColumnIndex(paramString));
          }
          if (localObject1 == null) {}
        }
        finally {}
        try
        {
          ((Cursor)localObject1).close();
          return localObject2;
        }
        finally {}
      }
      paramString = finally;
      localObject1 = null;
    }
    ((Cursor)localObject1).close();
    label102:
    throw paramString;
  }
  
  /* Error */
  final void a(int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 10
    //   3: aconst_null
    //   4: astore 6
    //   6: aload_0
    //   7: monitorenter
    //   8: iload_1
    //   9: aload_0
    //   10: invokevirtual 248	com/a/a/i:d	()I
    //   13: if_icmpgt +774 -> 787
    //   16: iload_1
    //   17: ifle +770 -> 787
    //   20: aload_0
    //   21: invokespecial 250	com/a/a/i:i	()Lcom/a/a/k$b;
    //   24: astore 8
    //   26: aload 8
    //   28: ifnull +307 -> 335
    //   31: invokestatic 57	com/a/a/i:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   34: putstatic 36	com/a/a/i:c	Landroid/database/sqlite/SQLiteDatabase;
    //   37: getstatic 36	com/a/a/i:c	Landroid/database/sqlite/SQLiteDatabase;
    //   40: ldc -4
    //   42: aconst_null
    //   43: invokevirtual 226	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   46: astore 9
    //   48: aload 10
    //   50: astore 7
    //   52: aload 9
    //   54: ifnull +172 -> 226
    //   57: new 232	java/lang/StringBuilder
    //   60: dup
    //   61: ldc -2
    //   63: invokespecial 236	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   66: aload 9
    //   68: invokeinterface 137 1 0
    //   73: invokevirtual 257	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   76: invokevirtual 245	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   79: invokestatic 65	com/a/a/y:a	(Ljava/lang/String;)V
    //   82: aload 10
    //   84: astore 7
    //   86: aload 9
    //   88: invokeinterface 134 1 0
    //   93: ifeq +133 -> 226
    //   96: new 94	com/a/a/f
    //   99: dup
    //   100: invokespecial 138	com/a/a/f:<init>	()V
    //   103: astore 10
    //   105: aload 10
    //   107: aload 9
    //   109: aload 9
    //   111: ldc 92
    //   113: invokeinterface 229 2 0
    //   118: invokeinterface 177 2 0
    //   123: invokevirtual 144	com/a/a/f:b	(I)V
    //   126: aload 10
    //   128: aload 9
    //   130: aload 9
    //   132: ldc 110
    //   134: invokeinterface 229 2 0
    //   139: invokeinterface 177 2 0
    //   144: invokevirtual 147	com/a/a/f:a	(I)V
    //   147: aload 10
    //   149: aload 9
    //   151: aload 9
    //   153: ldc 105
    //   155: invokeinterface 229 2 0
    //   160: invokeinterface 141 2 0
    //   165: invokevirtual 145	com/a/a/f:a	(Ljava/lang/String;)V
    //   168: aload 6
    //   170: astore 7
    //   172: aload 10
    //   174: invokevirtual 112	com/a/a/f:a	()I
    //   177: ifle +31 -> 208
    //   180: iconst_0
    //   181: istore_2
    //   182: aload 8
    //   184: invokevirtual 260	com/a/a/k$b:c	()Ljava/util/List;
    //   187: invokeinterface 264 1 0
    //   192: astore 11
    //   194: aload 11
    //   196: invokeinterface 269 1 0
    //   201: ifne +137 -> 338
    //   204: aload 6
    //   206: astore 7
    //   208: aload 9
    //   210: invokeinterface 209 1 0
    //   215: istore 5
    //   217: aload 7
    //   219: astore 6
    //   221: iload 5
    //   223: ifne -127 -> 96
    //   226: aload 9
    //   228: ifnull +10 -> 238
    //   231: aload 9
    //   233: invokeinterface 150 1 0
    //   238: aload 8
    //   240: ifnull +95 -> 335
    //   243: aload 7
    //   245: ifnull +90 -> 335
    //   248: new 271	java/util/HashMap
    //   251: dup
    //   252: invokespecial 272	java/util/HashMap:<init>	()V
    //   255: astore 6
    //   257: aload 8
    //   259: invokevirtual 260	com/a/a/k$b:c	()Ljava/util/List;
    //   262: invokeinterface 264 1 0
    //   267: astore 8
    //   269: aload 8
    //   271: invokeinterface 269 1 0
    //   276: ifne +289 -> 565
    //   279: aload 6
    //   281: getstatic 275	com/a/a/y:b	Z
    //   284: invokestatic 278	com/a/a/y:a	(Ljava/util/Map;Z)Ljava/util/Map;
    //   287: astore 6
    //   289: invokestatic 57	com/a/a/i:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   292: astore 7
    //   294: aload 7
    //   296: putstatic 36	com/a/a/i:c	Landroid/database/sqlite/SQLiteDatabase;
    //   299: aload 7
    //   301: ldc_w 280
    //   304: invokevirtual 283	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   307: aload 6
    //   309: invokeinterface 289 1 0
    //   314: invokeinterface 292 1 0
    //   319: astore 6
    //   321: aload 6
    //   323: invokeinterface 269 1 0
    //   328: istore 5
    //   330: iload 5
    //   332: ifne +369 -> 701
    //   335: aload_0
    //   336: monitorexit
    //   337: return
    //   338: aload 11
    //   340: invokeinterface 296 1 0
    //   345: checkcast 170	com/a/a/k$a
    //   348: astore 7
    //   350: aload 7
    //   352: invokevirtual 297	com/a/a/k$a:a	()I
    //   355: istore_3
    //   356: iload_2
    //   357: istore_1
    //   358: aload 10
    //   360: invokevirtual 112	com/a/a/f:a	()I
    //   363: iload_3
    //   364: if_icmpne +27 -> 391
    //   367: aload 7
    //   369: invokevirtual 298	com/a/a/k$a:c	()Ljava/util/List;
    //   372: invokeinterface 264 1 0
    //   377: astore 7
    //   379: aload 7
    //   381: invokeinterface 269 1 0
    //   386: ifne +124 -> 510
    //   389: iload_2
    //   390: istore_1
    //   391: iload_1
    //   392: istore_2
    //   393: iload_1
    //   394: ifeq -200 -> 194
    //   397: aload 6
    //   399: astore 7
    //   401: aload 6
    //   403: ifnonnull +12 -> 415
    //   406: new 271	java/util/HashMap
    //   409: dup
    //   410: invokespecial 272	java/util/HashMap:<init>	()V
    //   413: astore 7
    //   415: aload 7
    //   417: aload 10
    //   419: invokevirtual 112	com/a/a/f:a	()I
    //   422: invokestatic 100	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   425: invokeinterface 301 2 0
    //   430: ifeq +106 -> 536
    //   433: aload 7
    //   435: aload 10
    //   437: invokevirtual 112	com/a/a/f:a	()I
    //   440: invokestatic 100	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   443: invokeinterface 305 2 0
    //   448: checkcast 28	java/lang/Integer
    //   451: invokevirtual 308	java/lang/Integer:intValue	()I
    //   454: istore_2
    //   455: aload 7
    //   457: aload 10
    //   459: invokevirtual 112	com/a/a/f:a	()I
    //   462: invokestatic 100	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   465: iload_2
    //   466: iconst_1
    //   467: iadd
    //   468: invokestatic 100	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   471: invokeinterface 311 3 0
    //   476: pop
    //   477: aload 7
    //   479: astore 6
    //   481: iload_1
    //   482: istore_2
    //   483: goto -289 -> 194
    //   486: astore 6
    //   488: aload 9
    //   490: ifnull +10 -> 500
    //   493: aload 9
    //   495: invokeinterface 150 1 0
    //   500: aload 6
    //   502: athrow
    //   503: astore 6
    //   505: aload_0
    //   506: monitorexit
    //   507: aload 6
    //   509: athrow
    //   510: aload 7
    //   512: invokeinterface 296 1 0
    //   517: checkcast 28	java/lang/Integer
    //   520: invokevirtual 308	java/lang/Integer:intValue	()I
    //   523: aload 10
    //   525: invokevirtual 97	com/a/a/f:c	()I
    //   528: if_icmpne -149 -> 379
    //   531: iconst_1
    //   532: istore_1
    //   533: goto -142 -> 391
    //   536: aload 7
    //   538: aload 10
    //   540: invokevirtual 112	com/a/a/f:a	()I
    //   543: invokestatic 100	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   546: iconst_1
    //   547: invokestatic 100	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   550: invokeinterface 311 3 0
    //   555: pop
    //   556: aload 7
    //   558: astore 6
    //   560: iload_1
    //   561: istore_2
    //   562: goto -368 -> 194
    //   565: aload 8
    //   567: invokeinterface 296 1 0
    //   572: checkcast 170	com/a/a/k$a
    //   575: astore 9
    //   577: aload 9
    //   579: invokevirtual 297	com/a/a/k$a:a	()I
    //   582: istore_1
    //   583: aload 7
    //   585: iload_1
    //   586: invokestatic 100	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   589: invokeinterface 301 2 0
    //   594: ifeq -325 -> 269
    //   597: aload 7
    //   599: aload 9
    //   601: invokevirtual 297	com/a/a/k$a:a	()I
    //   604: invokestatic 100	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   607: invokeinterface 305 2 0
    //   612: checkcast 28	java/lang/Integer
    //   615: invokevirtual 308	java/lang/Integer:intValue	()I
    //   618: istore_2
    //   619: aload 9
    //   621: invokevirtual 312	com/a/a/k$a:b	()I
    //   624: istore_3
    //   625: iload_2
    //   626: iload_3
    //   627: isub
    //   628: istore 4
    //   630: new 232	java/lang/StringBuilder
    //   633: dup
    //   634: ldc_w 314
    //   637: invokespecial 236	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   640: iload_1
    //   641: invokevirtual 257	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   644: ldc_w 316
    //   647: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   650: iload_2
    //   651: invokevirtual 257	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   654: ldc_w 318
    //   657: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   660: iload_3
    //   661: invokevirtual 257	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   664: ldc_w 320
    //   667: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   670: iload 4
    //   672: invokevirtual 257	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   675: invokevirtual 245	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   678: invokestatic 65	com/a/a/y:a	(Ljava/lang/String;)V
    //   681: aload 6
    //   683: iload_1
    //   684: invokestatic 100	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   687: iload 4
    //   689: invokestatic 100	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   692: invokeinterface 311 3 0
    //   697: pop
    //   698: goto -429 -> 269
    //   701: aload 6
    //   703: invokeinterface 296 1 0
    //   708: checkcast 322	java/util/Map$Entry
    //   711: invokeinterface 325 1 0
    //   716: checkcast 28	java/lang/Integer
    //   719: invokevirtual 308	java/lang/Integer:intValue	()I
    //   722: istore_1
    //   723: new 232	java/lang/StringBuilder
    //   726: dup
    //   727: ldc_w 327
    //   730: invokespecial 236	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   733: iload_1
    //   734: invokevirtual 257	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   737: invokevirtual 245	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   740: invokestatic 65	com/a/a/y:a	(Ljava/lang/String;)V
    //   743: new 69	android/content/ContentValues
    //   746: dup
    //   747: invokespecial 71	android/content/ContentValues:<init>	()V
    //   750: astore 7
    //   752: aload 7
    //   754: ldc 92
    //   756: iload_1
    //   757: invokestatic 100	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   760: invokevirtual 103	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   763: getstatic 36	com/a/a/i:c	Landroid/database/sqlite/SQLiteDatabase;
    //   766: ldc_w 329
    //   769: aconst_null
    //   770: aload 7
    //   772: invokevirtual 121	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   775: pop2
    //   776: goto -455 -> 321
    //   779: astore 6
    //   781: aconst_null
    //   782: astore 9
    //   784: goto -296 -> 488
    //   787: aconst_null
    //   788: astore 8
    //   790: goto -764 -> 26
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	793	0	this	i
    //   0	793	1	paramInt	int
    //   181	470	2	i	int
    //   355	306	3	j	int
    //   628	60	4	k	int
    //   215	116	5	bool	boolean
    //   4	476	6	localObject1	Object
    //   486	15	6	localObject2	Object
    //   503	5	6	localObject3	Object
    //   558	144	6	localObject4	Object
    //   779	1	6	localObject5	Object
    //   50	721	7	localObject6	Object
    //   24	765	8	localObject7	Object
    //   46	737	9	localObject8	Object
    //   1	538	10	localF	f
    //   192	147	11	localIterator	Iterator
    // Exception table:
    //   from	to	target	type
    //   57	82	486	finally
    //   86	96	486	finally
    //   96	168	486	finally
    //   172	180	486	finally
    //   182	194	486	finally
    //   194	204	486	finally
    //   208	217	486	finally
    //   338	356	486	finally
    //   358	379	486	finally
    //   379	389	486	finally
    //   406	415	486	finally
    //   415	477	486	finally
    //   510	531	486	finally
    //   536	556	486	finally
    //   8	16	503	finally
    //   20	26	503	finally
    //   231	238	503	finally
    //   248	269	503	finally
    //   269	321	503	finally
    //   321	330	503	finally
    //   493	500	503	finally
    //   500	503	503	finally
    //   565	625	503	finally
    //   630	698	503	finally
    //   701	776	503	finally
    //   31	48	779	finally
  }
  
  final void a(final Context paramContext, final int paramInt)
  {
    try
    {
      new Thread(new Runnable()
      {
        public final void run()
        {
          for (;;)
          {
            try
            {
              i.a(i.a());
              localObject = paramContext.getPackageManager().getInstalledApplications(128);
              i.h().execSQL("DELETE FROM installapplist");
              localObject = ((List)localObject).iterator();
              boolean bool = ((Iterator)localObject).hasNext();
              if (bool) {}
            }
            catch (Throwable localThrowable1)
            {
              Object localObject;
              ApplicationInfo localApplicationInfo;
              ContentValues localContentValues;
              y.a("===== Background Thread " + y.a(localThrowable1));
              continue;
            }
            try
            {
              i.this.a(paramInt);
              return;
            }
            catch (Throwable localThrowable2)
            {
              y.a("===== Background Thread " + y.a(localThrowable2));
            }
            localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
            if (i.h() != null)
            {
              localContentValues = new ContentValues();
              localContentValues.put("packagename", localApplicationInfo.packageName);
              i.h().insert("installapplist", null, localContentValues);
            }
          }
        }
      }).start();
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  /* Error */
  final void a(f paramF)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokevirtual 97	com/a/a/f:c	()I
    //   7: invokespecial 344	com/a/a/i:b	(I)Lcom/a/a/f;
    //   10: ifnonnull +67 -> 77
    //   13: new 69	android/content/ContentValues
    //   16: dup
    //   17: invokespecial 71	android/content/ContentValues:<init>	()V
    //   20: astore_2
    //   21: aload_2
    //   22: ldc 92
    //   24: aload_1
    //   25: invokevirtual 97	com/a/a/f:c	()I
    //   28: invokestatic 100	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   31: invokevirtual 103	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   34: aload_2
    //   35: ldc 105
    //   37: aload_1
    //   38: invokevirtual 108	com/a/a/f:b	()Ljava/lang/String;
    //   41: invokevirtual 75	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   44: aload_2
    //   45: ldc 110
    //   47: aload_1
    //   48: invokevirtual 112	com/a/a/f:a	()I
    //   51: invokestatic 100	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   54: invokevirtual 103	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   57: invokestatic 57	com/a/a/i:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   60: astore_1
    //   61: aload_1
    //   62: putstatic 36	com/a/a/i:c	Landroid/database/sqlite/SQLiteDatabase;
    //   65: aload_1
    //   66: ldc 114
    //   68: aconst_null
    //   69: aload_2
    //   70: invokevirtual 121	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   73: pop2
    //   74: aload_0
    //   75: monitorexit
    //   76: return
    //   77: aload_0
    //   78: aload_1
    //   79: invokespecial 346	com/a/a/i:b	(Lcom/a/a/f;)I
    //   82: pop
    //   83: goto -9 -> 74
    //   86: astore_1
    //   87: aload_0
    //   88: monitorexit
    //   89: aload_1
    //   90: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	91	0	this	i
    //   0	91	1	paramF	f
    //   20	50	2	localContentValues	ContentValues
    // Exception table:
    //   from	to	target	type
    //   2	74	86	finally
    //   77	83	86	finally
  }
  
  /* Error */
  final void a(k.a paramA)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokevirtual 297	com/a/a/k$a:a	()I
    //   7: invokespecial 349	com/a/a/i:c	(I)Lcom/a/a/k$a;
    //   10: ifnonnull +89 -> 99
    //   13: new 69	android/content/ContentValues
    //   16: dup
    //   17: invokespecial 71	android/content/ContentValues:<init>	()V
    //   20: astore_3
    //   21: aload_3
    //   22: ldc 92
    //   24: aload_1
    //   25: invokevirtual 297	com/a/a/k$a:a	()I
    //   28: invokestatic 100	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   31: invokevirtual 103	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   34: new 232	java/lang/StringBuilder
    //   37: dup
    //   38: invokespecial 350	java/lang/StringBuilder:<init>	()V
    //   41: astore 4
    //   43: iconst_0
    //   44: istore_2
    //   45: iload_2
    //   46: aload_1
    //   47: invokevirtual 298	com/a/a/k$a:c	()Ljava/util/List;
    //   50: invokeinterface 353 1 0
    //   55: if_icmplt +47 -> 102
    //   58: aload_3
    //   59: ldc -101
    //   61: aload 4
    //   63: invokevirtual 245	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   66: invokevirtual 75	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   69: aload_3
    //   70: ldc -99
    //   72: aload_1
    //   73: invokevirtual 312	com/a/a/k$a:b	()I
    //   76: invokestatic 100	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   79: invokevirtual 103	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   82: invokestatic 57	com/a/a/i:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   85: astore_1
    //   86: aload_1
    //   87: putstatic 36	com/a/a/i:c	Landroid/database/sqlite/SQLiteDatabase;
    //   90: aload_1
    //   91: ldc -103
    //   93: aconst_null
    //   94: aload_3
    //   95: invokevirtual 121	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   98: pop2
    //   99: aload_0
    //   100: monitorexit
    //   101: return
    //   102: aload 4
    //   104: aload_1
    //   105: invokevirtual 298	com/a/a/k$a:c	()Ljava/util/List;
    //   108: iload_2
    //   109: invokeinterface 356 2 0
    //   114: invokevirtual 359	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   117: pop
    //   118: iload_2
    //   119: aload_1
    //   120: invokevirtual 298	com/a/a/k$a:c	()Ljava/util/List;
    //   123: invokeinterface 353 1 0
    //   128: iconst_1
    //   129: isub
    //   130: if_icmpeq +11 -> 141
    //   133: aload 4
    //   135: ldc -76
    //   137: invokevirtual 240	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: pop
    //   141: iload_2
    //   142: iconst_1
    //   143: iadd
    //   144: istore_2
    //   145: goto -100 -> 45
    //   148: astore_1
    //   149: aload_0
    //   150: monitorexit
    //   151: aload_1
    //   152: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	153	0	this	i
    //   0	153	1	paramA	k.a
    //   44	101	2	i	int
    //   20	75	3	localContentValues	ContentValues
    //   41	93	4	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   2	43	148	finally
    //   45	99	148	finally
    //   102	141	148	finally
  }
  
  /* Error */
  final void a(k.b paramB)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 69	android/content/ContentValues
    //   5: dup
    //   6: invokespecial 71	android/content/ContentValues:<init>	()V
    //   9: astore_2
    //   10: aload_2
    //   11: ldc_w 362
    //   14: aload_1
    //   15: invokevirtual 215	com/a/a/k$b:a	()Ljava/lang/String;
    //   18: invokevirtual 75	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   21: aload_2
    //   22: ldc_w 364
    //   25: aload_1
    //   26: invokevirtual 218	com/a/a/k$b:b	()I
    //   29: invokestatic 100	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   32: invokevirtual 103	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   35: aload_0
    //   36: invokevirtual 213	com/a/a/i:e	()Lcom/a/a/k$b;
    //   39: astore_3
    //   40: aload_3
    //   41: ifnonnull +24 -> 65
    //   44: invokestatic 57	com/a/a/i:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   47: astore_1
    //   48: aload_1
    //   49: putstatic 36	com/a/a/i:c	Landroid/database/sqlite/SQLiteDatabase;
    //   52: aload_1
    //   53: ldc_w 366
    //   56: aconst_null
    //   57: aload_2
    //   58: invokevirtual 121	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   61: pop2
    //   62: aload_0
    //   63: monitorexit
    //   64: return
    //   65: aload_3
    //   66: invokevirtual 215	com/a/a/k$b:a	()Ljava/lang/String;
    //   69: aload_1
    //   70: invokevirtual 215	com/a/a/k$b:a	()Ljava/lang/String;
    //   73: invokevirtual 370	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   76: ifne -14 -> 62
    //   79: invokestatic 57	com/a/a/i:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   82: astore_1
    //   83: aload_1
    //   84: putstatic 36	com/a/a/i:c	Landroid/database/sqlite/SQLiteDatabase;
    //   87: aload_1
    //   88: ldc_w 372
    //   91: invokevirtual 283	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   94: getstatic 36	com/a/a/i:c	Landroid/database/sqlite/SQLiteDatabase;
    //   97: ldc_w 366
    //   100: aload_2
    //   101: aconst_null
    //   102: aconst_null
    //   103: invokevirtual 89	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   106: pop
    //   107: goto -45 -> 62
    //   110: astore_1
    //   111: aload_0
    //   112: monitorexit
    //   113: aload_1
    //   114: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	115	0	this	i
    //   0	115	1	paramB	k.b
    //   9	92	2	localContentValues	ContentValues
    //   39	27	3	localB	k.b
    // Exception table:
    //   from	to	target	type
    //   2	40	110	finally
    //   44	62	110	finally
    //   65	107	110	finally
  }
  
  final void a(String paramString1, String paramString2)
  {
    for (;;)
    {
      try
      {
        int i = j();
        if (i == -1) {
          continue;
        }
        a(paramString1, paramString2, i);
        y.a("============Updated " + paramString1);
      }
      catch (DeadObjectException paramString1)
      {
        y.a(y.a(paramString1));
        continue;
      }
      finally {}
      return;
      b(paramString1, paramString2);
      y.a("==========Inserted " + paramString1);
    }
  }
  
  final int b()
  {
    Cursor localCursor2 = null;
    int j = 0;
    Cursor localCursor1 = localCursor2;
    for (;;)
    {
      int i;
      try
      {
        SQLiteDatabase localSQLiteDatabase = a();
        localCursor1 = localCursor2;
        c = localSQLiteDatabase;
        localCursor1 = localCursor2;
        localCursor2 = localSQLiteDatabase.rawQuery("SELECT  * FROM applist WHERE id = (SELECT MAX(id) FROM applist)", null);
        if (localCursor2 != null)
        {
          localCursor1 = localCursor2;
          if (localCursor2.moveToFirst())
          {
            localCursor1 = localCursor2;
            j = Integer.parseInt(localCursor2.getString(0));
            i = j;
            if (localCursor2 == null) {}
          }
        }
        i = j;
      }
      finally
      {
        try
        {
          localCursor2.close();
          i = j;
          return i;
        }
        finally {}
        localObject2 = finally;
        if (localCursor1 != null) {
          localCursor1.close();
        }
      }
      if (localObject2 != null)
      {
        localObject2.close();
        i = j;
      }
    }
  }
  
  final int c()
  {
    Cursor localCursor2 = null;
    localCursor1 = localCursor2;
    for (;;)
    {
      try
      {
        SQLiteDatabase localSQLiteDatabase = a();
        localCursor1 = localCursor2;
        c = localSQLiteDatabase;
        localCursor1 = localCursor2;
        localCursor2 = localSQLiteDatabase.rawQuery("SELECT  * FROM applist", null);
        if (localCursor2 == null)
        {
          i = 0;
          if (localCursor2 == null) {}
        }
      }
      finally
      {
        int i;
        if (localCursor1 != null) {
          localCursor1.close();
        }
      }
      try
      {
        localCursor2.close();
        return i;
      }
      finally {}
      localCursor1 = localCursor2;
      i = localCursor2.getCount();
    }
  }
  
  final int d()
  {
    try
    {
      localObject1 = a();
      c = (SQLiteDatabase)localObject1;
      localObject1 = ((SQLiteDatabase)localObject1).query("meanlist", null, null, null, null, null, null);
      if (localObject1 != null) {
        break label43;
      }
      i = 0;
    }
    finally
    {
      for (;;)
      {
        Object localObject1;
        int i;
        label43:
        label66:
        try
        {
          ((Cursor)localObject1).close();
          return i;
        }
        finally {}
        try
        {
          i = ((Cursor)localObject1).getCount();
        }
        finally {}
      }
      localObject3 = finally;
      localObject1 = null;
      if (localObject1 == null) {
        break label66;
      }
      ((Cursor)localObject1).close();
      throw localObject3;
    }
    if (localObject1 == null) {}
  }
  
  /* Error */
  final k.b e()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: monitorenter
    //   4: invokestatic 57	com/a/a/i:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   7: astore_2
    //   8: aload_2
    //   9: putstatic 36	com/a/a/i:c	Landroid/database/sqlite/SQLiteDatabase;
    //   12: aload_2
    //   13: ldc_w 366
    //   16: aconst_null
    //   17: aconst_null
    //   18: aconst_null
    //   19: aconst_null
    //   20: aconst_null
    //   21: aconst_null
    //   22: invokevirtual 206	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   25: astore_2
    //   26: aload_2
    //   27: ifnull +117 -> 144
    //   30: aload_2
    //   31: invokeinterface 134 1 0
    //   36: ifeq +108 -> 144
    //   39: aload_2
    //   40: invokeinterface 137 1 0
    //   45: ifle +99 -> 144
    //   48: invokestatic 162	com/a/a/k:a	()Lcom/a/a/k;
    //   51: astore_1
    //   52: aload_1
    //   53: invokevirtual 168	java/lang/Object:getClass	()Ljava/lang/Class;
    //   56: pop
    //   57: new 202	com/a/a/k$b
    //   60: dup
    //   61: aload_1
    //   62: invokespecial 203	com/a/a/k$b:<init>	(Lcom/a/a/k;)V
    //   65: astore_3
    //   66: aload_3
    //   67: aload_2
    //   68: aload_2
    //   69: ldc_w 362
    //   72: invokeinterface 229 2 0
    //   77: invokeinterface 141 2 0
    //   82: invokevirtual 216	com/a/a/k$b:a	(Ljava/lang/String;)V
    //   85: aload_3
    //   86: aload_2
    //   87: aload_2
    //   88: ldc_w 364
    //   91: invokeinterface 229 2 0
    //   96: invokeinterface 177 2 0
    //   101: invokevirtual 219	com/a/a/k$b:a	(I)V
    //   104: aload_3
    //   105: astore_1
    //   106: aload_2
    //   107: ifnull +11 -> 118
    //   110: aload_2
    //   111: invokeinterface 150 1 0
    //   116: aload_3
    //   117: astore_1
    //   118: aload_0
    //   119: monitorexit
    //   120: aload_1
    //   121: areturn
    //   122: astore_3
    //   123: aload_1
    //   124: astore_2
    //   125: aload_3
    //   126: astore_1
    //   127: aload_2
    //   128: ifnull +9 -> 137
    //   131: aload_2
    //   132: invokeinterface 150 1 0
    //   137: aload_1
    //   138: athrow
    //   139: astore_1
    //   140: aload_0
    //   141: monitorexit
    //   142: aload_1
    //   143: athrow
    //   144: aload_2
    //   145: ifnull +9 -> 154
    //   148: aload_2
    //   149: invokeinterface 150 1 0
    //   154: aconst_null
    //   155: astore_1
    //   156: goto -38 -> 118
    //   159: astore_1
    //   160: goto -33 -> 127
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	163	0	this	i
    //   1	137	1	localObject1	Object
    //   139	4	1	localObject2	Object
    //   155	1	1	localObject3	Object
    //   159	1	1	localObject4	Object
    //   7	142	2	localObject5	Object
    //   65	52	3	localB	k.b
    //   122	4	3	localObject6	Object
    // Exception table:
    //   from	to	target	type
    //   4	26	122	finally
    //   110	116	139	finally
    //   131	137	139	finally
    //   137	139	139	finally
    //   148	154	139	finally
    //   30	104	159	finally
  }
  
  /* Error */
  final List<Integer> f()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_0
    //   4: monitorenter
    //   5: new 186	java/util/ArrayList
    //   8: dup
    //   9: invokespecial 187	java/util/ArrayList:<init>	()V
    //   12: astore 5
    //   14: aload 4
    //   16: astore_3
    //   17: invokestatic 57	com/a/a/i:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   20: astore 6
    //   22: aload 4
    //   24: astore_3
    //   25: aload 6
    //   27: putstatic 36	com/a/a/i:c	Landroid/database/sqlite/SQLiteDatabase;
    //   30: aload 4
    //   32: astore_3
    //   33: aload 6
    //   35: ldc -4
    //   37: aconst_null
    //   38: invokevirtual 226	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   41: astore 4
    //   43: aload 4
    //   45: ifnull +89 -> 134
    //   48: aload 4
    //   50: astore_3
    //   51: aload 4
    //   53: invokeinterface 134 1 0
    //   58: ifeq +76 -> 134
    //   61: aload 4
    //   63: astore_3
    //   64: aload 4
    //   66: aload 4
    //   68: ldc 92
    //   70: invokeinterface 229 2 0
    //   75: invokeinterface 177 2 0
    //   80: istore_1
    //   81: aload 4
    //   83: astore_3
    //   84: new 232	java/lang/StringBuilder
    //   87: dup
    //   88: ldc_w 391
    //   91: invokespecial 236	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   94: iload_1
    //   95: invokevirtual 257	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   98: invokevirtual 245	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   101: invokestatic 65	com/a/a/y:a	(Ljava/lang/String;)V
    //   104: aload 4
    //   106: astore_3
    //   107: aload 5
    //   109: iload_1
    //   110: invokestatic 100	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   113: invokeinterface 197 2 0
    //   118: pop
    //   119: aload 4
    //   121: astore_3
    //   122: aload 4
    //   124: invokeinterface 209 1 0
    //   129: istore_2
    //   130: iload_2
    //   131: ifne -70 -> 61
    //   134: aload 4
    //   136: ifnull +10 -> 146
    //   139: aload 4
    //   141: invokeinterface 150 1 0
    //   146: aload_0
    //   147: monitorexit
    //   148: aload 5
    //   150: areturn
    //   151: astore 4
    //   153: aload_3
    //   154: ifnull +9 -> 163
    //   157: aload_3
    //   158: invokeinterface 150 1 0
    //   163: aload 4
    //   165: athrow
    //   166: astore_3
    //   167: aload_0
    //   168: monitorexit
    //   169: aload_3
    //   170: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	171	0	this	i
    //   80	30	1	i	int
    //   129	2	2	bool	boolean
    //   16	142	3	localCursor1	Cursor
    //   166	4	3	localObject1	Object
    //   1	139	4	localCursor2	Cursor
    //   151	13	4	localObject2	Object
    //   12	137	5	localArrayList	ArrayList
    //   20	14	6	localSQLiteDatabase	SQLiteDatabase
    // Exception table:
    //   from	to	target	type
    //   17	22	151	finally
    //   25	30	151	finally
    //   33	43	151	finally
    //   51	61	151	finally
    //   64	81	151	finally
    //   84	104	151	finally
    //   107	119	151	finally
    //   122	130	151	finally
    //   5	14	166	finally
    //   139	146	166	finally
    //   157	163	166	finally
    //   163	166	166	finally
  }
  
  final List<Integer> g()
  {
    try
    {
      ArrayList localArrayList = new ArrayList();
      try
      {
        Object localObject1 = a();
        c = (SQLiteDatabase)localObject1;
        localObject1 = ((SQLiteDatabase)localObject1).query("audience", null, null, null, null, null, null);
        boolean bool;
        if ((localObject1 == null) || (localObject1 != null)) {
          ((Cursor)localObject1).close();
        }
      }
      finally
      {
        try
        {
          if (((Cursor)localObject1).moveToFirst()) {
            do
            {
              localArrayList.add(Integer.valueOf(((Cursor)localObject1).getInt(((Cursor)localObject1).getColumnIndex("id"))));
              bool = ((Cursor)localObject1).moveToNext();
            } while (bool);
          }
          if (localObject1 != null) {
            ((Cursor)localObject1).close();
          }
          localObject1 = a();
          c = (SQLiteDatabase)localObject1;
          ((SQLiteDatabase)localObject1).execSQL("DELETE FROM audience");
          return localArrayList;
        }
        finally
        {
          for (;;) {}
        }
        localObject3 = finally;
        localObject1 = null;
      }
      throw localObject3;
    }
    finally {}
  }
  
  public final void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("CREATE TABLE applist(id INTEGER,name TEXT,classification_id INTEGER)");
    paramSQLiteDatabase.execSQL("CREATE TABLE meanlist(id INTEGER,apps TEXT,mean INTEGER)");
    paramSQLiteDatabase.execSQL("CREATE TABLE meanInfo(version TEXT, appcount INTEGER)");
    paramSQLiteDatabase.execSQL("CREATE TABLE installapplist(packagename TEXT )");
    paramSQLiteDatabase.execSQL("CREATE TABLE audience(id INTEGER )");
    paramSQLiteDatabase.execSQL("CREATE TABLE appRequestResponse(id INTEGER PRIMARY KEY  AUTOINCREMENT, deviceEventRequest TEXT ,deviceEventResponse TEXT ,enhanceParamRequest TEXT )");
  }
  
  public final void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS applist");
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS meanlist");
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS meanInfo");
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS installapplist");
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS audience");
    paramSQLiteDatabase.execSQL("DROP TABLE IF EXISTS appRequestResponse");
    onCreate(paramSQLiteDatabase);
  }
}
