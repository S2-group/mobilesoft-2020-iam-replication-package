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

final class n
  extends SQLiteOpenHelper
{
  private static final int a = Integer.parseInt("1.4.4".replaceAll("\\.", ""));
  private static n b;
  private static SQLiteDatabase c = null;
  
  private n(Context paramContext)
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
  
  static n a(Context paramContext)
  {
    try
    {
      if (b == null)
      {
        b = new n(paramContext);
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
        aj.a(paramContext);
        aj.a();
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
  
  private int b(k paramK)
  {
    try
    {
      c = a();
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("id", Integer.valueOf(paramK.c()));
      localContentValues.put("name", paramK.b());
      localContentValues.put("classification_id", Integer.valueOf(paramK.a()));
      int i = c.update("applist", localContentValues, "id = ?", new String[] { String.valueOf(paramK.c()) });
      return i;
    }
    finally
    {
      paramK = finally;
      throw paramK;
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
  private k b(int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: monitorenter
    //   4: invokestatic 57	com/a/a/n:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   7: astore_3
    //   8: aload_3
    //   9: putstatic 36	com/a/a/n:c	Landroid/database/sqlite/SQLiteDatabase;
    //   12: aload_3
    //   13: ldc 113
    //   15: iconst_3
    //   16: anewarray 22	java/lang/String
    //   19: dup
    //   20: iconst_0
    //   21: ldc 91
    //   23: aastore
    //   24: dup
    //   25: iconst_1
    //   26: ldc 104
    //   28: aastore
    //   29: dup
    //   30: iconst_2
    //   31: ldc 109
    //   33: aastore
    //   34: ldc 123
    //   36: iconst_1
    //   37: anewarray 22	java/lang/String
    //   40: dup
    //   41: iconst_0
    //   42: iload_1
    //   43: invokestatic 82	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   46: aastore
    //   47: aconst_null
    //   48: aconst_null
    //   49: aconst_null
    //   50: aconst_null
    //   51: invokevirtual 127	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   54: astore_3
    //   55: aload_3
    //   56: ifnull +116 -> 172
    //   59: aload_3
    //   60: invokeinterface 133 1 0
    //   65: ifeq +107 -> 172
    //   68: aload_3
    //   69: invokeinterface 136 1 0
    //   74: ifle +98 -> 172
    //   77: new 93	com/a/a/k
    //   80: dup
    //   81: invokespecial 137	com/a/a/k:<init>	()V
    //   84: astore 4
    //   86: aload 4
    //   88: aload_3
    //   89: iconst_0
    //   90: invokeinterface 140 2 0
    //   95: invokestatic 32	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   98: invokevirtual 143	com/a/a/k:b	(I)V
    //   101: aload 4
    //   103: aload_3
    //   104: iconst_1
    //   105: invokeinterface 140 2 0
    //   110: invokevirtual 146	com/a/a/k:a	(Ljava/lang/String;)V
    //   113: aload 4
    //   115: aload_3
    //   116: iconst_2
    //   117: invokeinterface 140 2 0
    //   122: invokestatic 32	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   125: invokevirtual 148	com/a/a/k:a	(I)V
    //   128: aload 4
    //   130: astore_2
    //   131: aload_3
    //   132: ifnull +12 -> 144
    //   135: aload_3
    //   136: invokeinterface 151 1 0
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
    //   160: invokeinterface 151 1 0
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
    //   177: invokeinterface 151 1 0
    //   182: aconst_null
    //   183: astore_2
    //   184: goto -40 -> 144
    //   187: astore_2
    //   188: goto -33 -> 155
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	191	0	this	n
    //   0	191	1	paramInt	int
    //   1	165	2	localObject1	Object
    //   167	4	2	localObject2	Object
    //   183	1	2	localObject3	Object
    //   187	1	2	localObject4	Object
    //   7	170	3	localObject5	Object
    //   84	58	4	localK	k
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
  private q c(int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: monitorenter
    //   4: invokestatic 57	com/a/a/n:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   7: astore_3
    //   8: aload_3
    //   9: putstatic 36	com/a/a/n:c	Landroid/database/sqlite/SQLiteDatabase;
    //   12: aload_3
    //   13: ldc -102
    //   15: iconst_3
    //   16: anewarray 22	java/lang/String
    //   19: dup
    //   20: iconst_0
    //   21: ldc 91
    //   23: aastore
    //   24: dup
    //   25: iconst_1
    //   26: ldc -100
    //   28: aastore
    //   29: dup
    //   30: iconst_2
    //   31: ldc -98
    //   33: aastore
    //   34: ldc 123
    //   36: iconst_1
    //   37: anewarray 22	java/lang/String
    //   40: dup
    //   41: iconst_0
    //   42: iload_1
    //   43: invokestatic 82	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   46: aastore
    //   47: aconst_null
    //   48: aconst_null
    //   49: aconst_null
    //   50: aconst_null
    //   51: invokevirtual 127	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   54: astore_3
    //   55: aload_3
    //   56: ifnull +171 -> 227
    //   59: aload_3
    //   60: invokeinterface 133 1 0
    //   65: ifeq +162 -> 227
    //   68: aload_3
    //   69: invokeinterface 136 1 0
    //   74: ifle +153 -> 227
    //   77: invokestatic 163	com/a/a/p:a	()Lcom/a/a/p;
    //   80: astore_2
    //   81: aload_2
    //   82: invokevirtual 169	java/lang/Object:getClass	()Ljava/lang/Class;
    //   85: pop
    //   86: new 171	com/a/a/q
    //   89: dup
    //   90: aload_2
    //   91: invokespecial 174	com/a/a/q:<init>	(Lcom/a/a/p;)V
    //   94: astore 4
    //   96: aload 4
    //   98: aload_3
    //   99: iconst_0
    //   100: invokeinterface 178 2 0
    //   105: invokevirtual 179	com/a/a/q:a	(I)V
    //   108: aload_3
    //   109: iconst_1
    //   110: invokeinterface 140 2 0
    //   115: ldc -75
    //   117: invokevirtual 185	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   120: astore_2
    //   121: new 187	java/util/ArrayList
    //   124: dup
    //   125: invokespecial 188	java/util/ArrayList:<init>	()V
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
    //   144: invokevirtual 191	com/a/a/q:a	(Ljava/util/List;)V
    //   147: aload 4
    //   149: aload_3
    //   150: iconst_2
    //   151: invokeinterface 178 2 0
    //   156: invokevirtual 192	com/a/a/q:b	(I)V
    //   159: aload 4
    //   161: astore_2
    //   162: aload_3
    //   163: ifnull +12 -> 175
    //   166: aload_3
    //   167: invokeinterface 151 1 0
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
    //   187: invokestatic 99	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   190: invokeinterface 198 2 0
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
    //   215: invokeinterface 151 1 0
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
    //   232: invokeinterface 151 1 0
    //   237: aconst_null
    //   238: astore_2
    //   239: goto -64 -> 175
    //   242: astore_2
    //   243: goto -33 -> 210
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	246	0	this	n
    //   0	246	1	paramInt	int
    //   1	220	2	localObject1	Object
    //   222	4	2	localObject2	Object
    //   238	1	2	localObject3	Object
    //   242	1	2	localObject4	Object
    //   7	225	3	localObject5	Object
    //   94	79	4	localQ	q
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
  private r i()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic 163	com/a/a/p:a	()Lcom/a/a/p;
    //   5: astore_2
    //   6: aload_2
    //   7: invokevirtual 169	java/lang/Object:getClass	()Ljava/lang/Class;
    //   10: pop
    //   11: new 203	com/a/a/r
    //   14: dup
    //   15: aload_2
    //   16: invokespecial 204	com/a/a/r:<init>	(Lcom/a/a/p;)V
    //   19: astore_3
    //   20: new 187	java/util/ArrayList
    //   23: dup
    //   24: invokespecial 188	java/util/ArrayList:<init>	()V
    //   27: astore 4
    //   29: invokestatic 57	com/a/a/n:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   32: astore_2
    //   33: aload_2
    //   34: putstatic 36	com/a/a/n:c	Landroid/database/sqlite/SQLiteDatabase;
    //   37: aload_2
    //   38: ldc -102
    //   40: aconst_null
    //   41: aconst_null
    //   42: aconst_null
    //   43: aconst_null
    //   44: aconst_null
    //   45: aconst_null
    //   46: invokevirtual 207	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   49: astore_2
    //   50: aload_2
    //   51: ifnull +133 -> 184
    //   54: aload_2
    //   55: invokeinterface 133 1 0
    //   60: ifeq +124 -> 184
    //   63: aload_2
    //   64: invokeinterface 136 1 0
    //   69: ifle +115 -> 184
    //   72: invokestatic 163	com/a/a/p:a	()Lcom/a/a/p;
    //   75: astore 5
    //   77: aload 5
    //   79: invokevirtual 169	java/lang/Object:getClass	()Ljava/lang/Class;
    //   82: pop
    //   83: new 171	com/a/a/q
    //   86: dup
    //   87: aload 5
    //   89: invokespecial 174	com/a/a/q:<init>	(Lcom/a/a/p;)V
    //   92: astore 5
    //   94: aload 5
    //   96: aload_2
    //   97: iconst_0
    //   98: invokeinterface 178 2 0
    //   103: invokevirtual 179	com/a/a/q:a	(I)V
    //   106: aload_2
    //   107: iconst_1
    //   108: invokeinterface 140 2 0
    //   113: ldc -75
    //   115: invokevirtual 185	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   118: astore 6
    //   120: new 187	java/util/ArrayList
    //   123: dup
    //   124: invokespecial 188	java/util/ArrayList:<init>	()V
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
    //   144: invokevirtual 191	com/a/a/q:a	(Ljava/util/List;)V
    //   147: aload 5
    //   149: aload_2
    //   150: iconst_2
    //   151: invokeinterface 178 2 0
    //   156: invokevirtual 192	com/a/a/q:b	(I)V
    //   159: aload 4
    //   161: aload 5
    //   163: invokeinterface 198 2 0
    //   168: pop
    //   169: aload_2
    //   170: invokeinterface 210 1 0
    //   175: ifne -103 -> 72
    //   178: aload_3
    //   179: aload 4
    //   181: invokevirtual 211	com/a/a/r:a	(Ljava/util/List;)V
    //   184: aload_2
    //   185: ifnull +9 -> 194
    //   188: aload_2
    //   189: invokeinterface 151 1 0
    //   194: aload_0
    //   195: invokevirtual 214	com/a/a/n:e	()Lcom/a/a/r;
    //   198: astore_2
    //   199: aload_3
    //   200: aload_2
    //   201: invokevirtual 216	com/a/a/r:a	()Ljava/lang/String;
    //   204: invokevirtual 217	com/a/a/r:a	(Ljava/lang/String;)V
    //   207: aload_3
    //   208: aload_2
    //   209: invokevirtual 219	com/a/a/r:b	()I
    //   212: invokevirtual 220	com/a/a/r:a	(I)V
    //   215: aload_0
    //   216: monitorexit
    //   217: aload_3
    //   218: areturn
    //   219: aload 7
    //   221: aload 6
    //   223: iload_1
    //   224: aaload
    //   225: invokestatic 32	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   228: invokestatic 99	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   231: invokeinterface 198 2 0
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
    //   252: invokeinterface 151 1 0
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
    //   0	268	0	this	n
    //   130	111	1	i	int
    //   5	247	2	localObject1	Object
    //   259	4	2	localObject2	Object
    //   19	199	3	localR	r
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
    //   10: invokestatic 57	com/a/a/n:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   13: putstatic 36	com/a/a/n:c	Landroid/database/sqlite/SQLiteDatabase;
    //   16: aload 4
    //   18: astore_3
    //   19: getstatic 36	com/a/a/n:c	Landroid/database/sqlite/SQLiteDatabase;
    //   22: ldc -33
    //   24: aconst_null
    //   25: invokevirtual 227	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
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
    //   44: invokeinterface 133 1 0
    //   49: ifeq +23 -> 72
    //   52: aload 4
    //   54: astore_3
    //   55: aload 4
    //   57: aload 4
    //   59: ldc 91
    //   61: invokeinterface 230 2 0
    //   66: invokeinterface 178 2 0
    //   71: istore_1
    //   72: aload 4
    //   74: ifnull +10 -> 84
    //   77: aload 4
    //   79: invokeinterface 151 1 0
    //   84: aload_0
    //   85: monitorexit
    //   86: iload_1
    //   87: ireturn
    //   88: astore 4
    //   90: aload_3
    //   91: ifnull +9 -> 100
    //   94: aload_3
    //   95: invokeinterface 151 1 0
    //   100: aload 4
    //   102: athrow
    //   103: astore_3
    //   104: aload_0
    //   105: monitorexit
    //   106: aload_3
    //   107: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	108	0	this	n
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
    //   10: invokevirtual 249	com/a/a/n:d	()I
    //   13: if_icmpgt +748 -> 761
    //   16: iload_1
    //   17: ifle +744 -> 761
    //   20: aload_0
    //   21: invokespecial 251	com/a/a/n:i	()Lcom/a/a/r;
    //   24: astore 8
    //   26: aload 8
    //   28: ifnull +300 -> 328
    //   31: invokestatic 57	com/a/a/n:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   34: putstatic 36	com/a/a/n:c	Landroid/database/sqlite/SQLiteDatabase;
    //   37: getstatic 36	com/a/a/n:c	Landroid/database/sqlite/SQLiteDatabase;
    //   40: ldc -3
    //   42: aconst_null
    //   43: invokevirtual 227	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   46: astore 9
    //   48: aload 10
    //   50: astore 7
    //   52: aload 9
    //   54: ifnull +170 -> 224
    //   57: new 233	java/lang/StringBuilder
    //   60: dup
    //   61: ldc -1
    //   63: invokespecial 237	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   66: aload 9
    //   68: invokeinterface 136 1 0
    //   73: invokevirtual 258	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   76: pop
    //   77: invokestatic 64	com/a/a/aj:a	()V
    //   80: aload 10
    //   82: astore 7
    //   84: aload 9
    //   86: invokeinterface 133 1 0
    //   91: ifeq +133 -> 224
    //   94: new 93	com/a/a/k
    //   97: dup
    //   98: invokespecial 137	com/a/a/k:<init>	()V
    //   101: astore 10
    //   103: aload 10
    //   105: aload 9
    //   107: aload 9
    //   109: ldc 91
    //   111: invokeinterface 230 2 0
    //   116: invokeinterface 178 2 0
    //   121: invokevirtual 143	com/a/a/k:b	(I)V
    //   124: aload 10
    //   126: aload 9
    //   128: aload 9
    //   130: ldc 109
    //   132: invokeinterface 230 2 0
    //   137: invokeinterface 178 2 0
    //   142: invokevirtual 148	com/a/a/k:a	(I)V
    //   145: aload 10
    //   147: aload 9
    //   149: aload 9
    //   151: ldc 104
    //   153: invokeinterface 230 2 0
    //   158: invokeinterface 140 2 0
    //   163: invokevirtual 146	com/a/a/k:a	(Ljava/lang/String;)V
    //   166: aload 6
    //   168: astore 7
    //   170: aload 10
    //   172: invokevirtual 111	com/a/a/k:a	()I
    //   175: ifle +31 -> 206
    //   178: iconst_0
    //   179: istore_2
    //   180: aload 8
    //   182: invokevirtual 261	com/a/a/r:c	()Ljava/util/List;
    //   185: invokeinterface 265 1 0
    //   190: astore 11
    //   192: aload 11
    //   194: invokeinterface 270 1 0
    //   199: ifne +132 -> 331
    //   202: aload 6
    //   204: astore 7
    //   206: aload 9
    //   208: invokeinterface 210 1 0
    //   213: istore 5
    //   215: aload 7
    //   217: astore 6
    //   219: iload 5
    //   221: ifne -127 -> 94
    //   224: aload 9
    //   226: ifnull +10 -> 236
    //   229: aload 9
    //   231: invokeinterface 151 1 0
    //   236: aload 7
    //   238: ifnull +90 -> 328
    //   241: new 272	java/util/HashMap
    //   244: dup
    //   245: invokespecial 273	java/util/HashMap:<init>	()V
    //   248: astore 6
    //   250: aload 8
    //   252: invokevirtual 261	com/a/a/r:c	()Ljava/util/List;
    //   255: invokeinterface 265 1 0
    //   260: astore 8
    //   262: aload 8
    //   264: invokeinterface 270 1 0
    //   269: ifne +289 -> 558
    //   272: aload 6
    //   274: getstatic 276	com/a/a/aj:b	Z
    //   277: invokestatic 279	com/a/a/aj:a	(Ljava/util/Map;Z)Ljava/util/Map;
    //   280: astore 6
    //   282: invokestatic 57	com/a/a/n:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   285: astore 7
    //   287: aload 7
    //   289: putstatic 36	com/a/a/n:c	Landroid/database/sqlite/SQLiteDatabase;
    //   292: aload 7
    //   294: ldc_w 281
    //   297: invokevirtual 284	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   300: aload 6
    //   302: invokeinterface 290 1 0
    //   307: invokeinterface 293 1 0
    //   312: astore 6
    //   314: aload 6
    //   316: invokeinterface 270 1 0
    //   321: istore 5
    //   323: iload 5
    //   325: ifne +367 -> 692
    //   328: aload_0
    //   329: monitorexit
    //   330: return
    //   331: aload 11
    //   333: invokeinterface 297 1 0
    //   338: checkcast 171	com/a/a/q
    //   341: astore 7
    //   343: aload 7
    //   345: invokevirtual 298	com/a/a/q:a	()I
    //   348: istore_3
    //   349: iload_2
    //   350: istore_1
    //   351: aload 10
    //   353: invokevirtual 111	com/a/a/k:a	()I
    //   356: iload_3
    //   357: if_icmpne +27 -> 384
    //   360: aload 7
    //   362: invokevirtual 299	com/a/a/q:c	()Ljava/util/List;
    //   365: invokeinterface 265 1 0
    //   370: astore 7
    //   372: aload 7
    //   374: invokeinterface 270 1 0
    //   379: ifne +124 -> 503
    //   382: iload_2
    //   383: istore_1
    //   384: iload_1
    //   385: istore_2
    //   386: iload_1
    //   387: ifeq -195 -> 192
    //   390: aload 6
    //   392: astore 7
    //   394: aload 6
    //   396: ifnonnull +12 -> 408
    //   399: new 272	java/util/HashMap
    //   402: dup
    //   403: invokespecial 273	java/util/HashMap:<init>	()V
    //   406: astore 7
    //   408: aload 7
    //   410: aload 10
    //   412: invokevirtual 111	com/a/a/k:a	()I
    //   415: invokestatic 99	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   418: invokeinterface 302 2 0
    //   423: ifeq +106 -> 529
    //   426: aload 7
    //   428: aload 10
    //   430: invokevirtual 111	com/a/a/k:a	()I
    //   433: invokestatic 99	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   436: invokeinterface 306 2 0
    //   441: checkcast 28	java/lang/Integer
    //   444: invokevirtual 309	java/lang/Integer:intValue	()I
    //   447: istore_2
    //   448: aload 7
    //   450: aload 10
    //   452: invokevirtual 111	com/a/a/k:a	()I
    //   455: invokestatic 99	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   458: iload_2
    //   459: iconst_1
    //   460: iadd
    //   461: invokestatic 99	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   464: invokeinterface 312 3 0
    //   469: pop
    //   470: aload 7
    //   472: astore 6
    //   474: iload_1
    //   475: istore_2
    //   476: goto -284 -> 192
    //   479: astore 6
    //   481: aload 9
    //   483: ifnull +10 -> 493
    //   486: aload 9
    //   488: invokeinterface 151 1 0
    //   493: aload 6
    //   495: athrow
    //   496: astore 6
    //   498: aload_0
    //   499: monitorexit
    //   500: aload 6
    //   502: athrow
    //   503: aload 7
    //   505: invokeinterface 297 1 0
    //   510: checkcast 28	java/lang/Integer
    //   513: invokevirtual 309	java/lang/Integer:intValue	()I
    //   516: aload 10
    //   518: invokevirtual 96	com/a/a/k:c	()I
    //   521: if_icmpne -149 -> 372
    //   524: iconst_1
    //   525: istore_1
    //   526: goto -142 -> 384
    //   529: aload 7
    //   531: aload 10
    //   533: invokevirtual 111	com/a/a/k:a	()I
    //   536: invokestatic 99	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   539: iconst_1
    //   540: invokestatic 99	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   543: invokeinterface 312 3 0
    //   548: pop
    //   549: aload 7
    //   551: astore 6
    //   553: iload_1
    //   554: istore_2
    //   555: goto -363 -> 192
    //   558: aload 8
    //   560: invokeinterface 297 1 0
    //   565: checkcast 171	com/a/a/q
    //   568: astore 9
    //   570: aload 9
    //   572: invokevirtual 298	com/a/a/q:a	()I
    //   575: istore_1
    //   576: aload 7
    //   578: iload_1
    //   579: invokestatic 99	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   582: invokeinterface 302 2 0
    //   587: ifeq -325 -> 262
    //   590: aload 7
    //   592: aload 9
    //   594: invokevirtual 298	com/a/a/q:a	()I
    //   597: invokestatic 99	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   600: invokeinterface 306 2 0
    //   605: checkcast 28	java/lang/Integer
    //   608: invokevirtual 309	java/lang/Integer:intValue	()I
    //   611: istore_2
    //   612: aload 9
    //   614: invokevirtual 313	com/a/a/q:b	()I
    //   617: istore_3
    //   618: iload_2
    //   619: iload_3
    //   620: isub
    //   621: istore 4
    //   623: new 233	java/lang/StringBuilder
    //   626: dup
    //   627: ldc_w 315
    //   630: invokespecial 237	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   633: iload_1
    //   634: invokevirtual 258	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   637: ldc_w 317
    //   640: invokevirtual 241	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   643: iload_2
    //   644: invokevirtual 258	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   647: ldc_w 319
    //   650: invokevirtual 241	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   653: iload_3
    //   654: invokevirtual 258	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   657: ldc_w 321
    //   660: invokevirtual 241	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   663: iload 4
    //   665: invokevirtual 258	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   668: pop
    //   669: invokestatic 64	com/a/a/aj:a	()V
    //   672: aload 6
    //   674: iload_1
    //   675: invokestatic 99	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   678: iload 4
    //   680: invokestatic 99	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   683: invokeinterface 312 3 0
    //   688: pop
    //   689: goto -427 -> 262
    //   692: aload 6
    //   694: invokeinterface 297 1 0
    //   699: checkcast 323	java/util/Map$Entry
    //   702: invokeinterface 326 1 0
    //   707: checkcast 28	java/lang/Integer
    //   710: invokevirtual 309	java/lang/Integer:intValue	()I
    //   713: istore_1
    //   714: invokestatic 64	com/a/a/aj:a	()V
    //   717: new 68	android/content/ContentValues
    //   720: dup
    //   721: invokespecial 70	android/content/ContentValues:<init>	()V
    //   724: astore 7
    //   726: aload 7
    //   728: ldc 91
    //   730: iload_1
    //   731: invokestatic 99	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   734: invokevirtual 102	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   737: getstatic 36	com/a/a/n:c	Landroid/database/sqlite/SQLiteDatabase;
    //   740: ldc_w 328
    //   743: aconst_null
    //   744: aload 7
    //   746: invokevirtual 120	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   749: pop2
    //   750: goto -436 -> 314
    //   753: astore 6
    //   755: aconst_null
    //   756: astore 9
    //   758: goto -277 -> 481
    //   761: aconst_null
    //   762: astore 8
    //   764: goto -738 -> 26
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	767	0	this	n
    //   0	767	1	paramInt	int
    //   179	465	2	i	int
    //   348	306	3	j	int
    //   621	58	4	k	int
    //   213	111	5	bool	boolean
    //   4	469	6	localObject1	Object
    //   479	15	6	localObject2	Object
    //   496	5	6	localObject3	Object
    //   551	142	6	localObject4	Object
    //   753	1	6	localObject5	Object
    //   50	695	7	localObject6	Object
    //   24	739	8	localObject7	Object
    //   46	711	9	localObject8	Object
    //   1	531	10	localK	k
    //   190	142	11	localIterator	Iterator
    // Exception table:
    //   from	to	target	type
    //   57	80	479	finally
    //   84	94	479	finally
    //   94	166	479	finally
    //   170	178	479	finally
    //   180	192	479	finally
    //   192	202	479	finally
    //   206	215	479	finally
    //   331	349	479	finally
    //   351	372	479	finally
    //   372	382	479	finally
    //   399	408	479	finally
    //   408	470	479	finally
    //   503	524	479	finally
    //   529	549	479	finally
    //   8	16	496	finally
    //   20	26	496	finally
    //   229	236	496	finally
    //   241	262	496	finally
    //   262	314	496	finally
    //   314	323	496	finally
    //   486	493	496	finally
    //   493	496	496	finally
    //   558	618	496	finally
    //   623	689	496	finally
    //   692	750	496	finally
    //   31	48	753	finally
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
              n.a(n.a());
              localObject = paramContext.getPackageManager().getInstalledApplications(128);
              n.h().execSQL("DELETE FROM installapplist");
              localObject = ((List)localObject).iterator();
              boolean bool = ((Iterator)localObject).hasNext();
              if (bool) {}
            }
            catch (Throwable localThrowable1)
            {
              Object localObject;
              ApplicationInfo localApplicationInfo;
              ContentValues localContentValues;
              new StringBuilder("===== Background Thread ").append(aj.a(localThrowable1));
              aj.a();
              continue;
            }
            try
            {
              n.this.a(paramInt);
              return;
            }
            catch (Throwable localThrowable2)
            {
              new StringBuilder("===== Background Thread ").append(aj.a(localThrowable2));
              aj.a();
            }
            localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
            if (n.h() != null)
            {
              localContentValues = new ContentValues();
              localContentValues.put("packagename", localApplicationInfo.packageName);
              n.h().insert("installapplist", null, localContentValues);
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
  final void a(k paramK)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokevirtual 96	com/a/a/k:c	()I
    //   7: invokespecial 343	com/a/a/n:b	(I)Lcom/a/a/k;
    //   10: ifnonnull +67 -> 77
    //   13: new 68	android/content/ContentValues
    //   16: dup
    //   17: invokespecial 70	android/content/ContentValues:<init>	()V
    //   20: astore_2
    //   21: aload_2
    //   22: ldc 91
    //   24: aload_1
    //   25: invokevirtual 96	com/a/a/k:c	()I
    //   28: invokestatic 99	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   31: invokevirtual 102	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   34: aload_2
    //   35: ldc 104
    //   37: aload_1
    //   38: invokevirtual 107	com/a/a/k:b	()Ljava/lang/String;
    //   41: invokevirtual 74	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   44: aload_2
    //   45: ldc 109
    //   47: aload_1
    //   48: invokevirtual 111	com/a/a/k:a	()I
    //   51: invokestatic 99	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   54: invokevirtual 102	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   57: invokestatic 57	com/a/a/n:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   60: astore_1
    //   61: aload_1
    //   62: putstatic 36	com/a/a/n:c	Landroid/database/sqlite/SQLiteDatabase;
    //   65: aload_1
    //   66: ldc 113
    //   68: aconst_null
    //   69: aload_2
    //   70: invokevirtual 120	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   73: pop2
    //   74: aload_0
    //   75: monitorexit
    //   76: return
    //   77: aload_0
    //   78: aload_1
    //   79: invokespecial 345	com/a/a/n:b	(Lcom/a/a/k;)I
    //   82: pop
    //   83: goto -9 -> 74
    //   86: astore_1
    //   87: aload_0
    //   88: monitorexit
    //   89: aload_1
    //   90: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	91	0	this	n
    //   0	91	1	paramK	k
    //   20	50	2	localContentValues	ContentValues
    // Exception table:
    //   from	to	target	type
    //   2	74	86	finally
    //   77	83	86	finally
  }
  
  /* Error */
  final void a(q paramQ)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokevirtual 298	com/a/a/q:a	()I
    //   7: invokespecial 348	com/a/a/n:c	(I)Lcom/a/a/q;
    //   10: ifnonnull +89 -> 99
    //   13: new 68	android/content/ContentValues
    //   16: dup
    //   17: invokespecial 70	android/content/ContentValues:<init>	()V
    //   20: astore_3
    //   21: aload_3
    //   22: ldc 91
    //   24: aload_1
    //   25: invokevirtual 298	com/a/a/q:a	()I
    //   28: invokestatic 99	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   31: invokevirtual 102	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   34: new 233	java/lang/StringBuilder
    //   37: dup
    //   38: invokespecial 349	java/lang/StringBuilder:<init>	()V
    //   41: astore 4
    //   43: iconst_0
    //   44: istore_2
    //   45: iload_2
    //   46: aload_1
    //   47: invokevirtual 299	com/a/a/q:c	()Ljava/util/List;
    //   50: invokeinterface 352 1 0
    //   55: if_icmplt +47 -> 102
    //   58: aload_3
    //   59: ldc -100
    //   61: aload 4
    //   63: invokevirtual 246	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   66: invokevirtual 74	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   69: aload_3
    //   70: ldc -98
    //   72: aload_1
    //   73: invokevirtual 313	com/a/a/q:b	()I
    //   76: invokestatic 99	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   79: invokevirtual 102	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   82: invokestatic 57	com/a/a/n:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   85: astore_1
    //   86: aload_1
    //   87: putstatic 36	com/a/a/n:c	Landroid/database/sqlite/SQLiteDatabase;
    //   90: aload_1
    //   91: ldc -102
    //   93: aconst_null
    //   94: aload_3
    //   95: invokevirtual 120	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   98: pop2
    //   99: aload_0
    //   100: monitorexit
    //   101: return
    //   102: aload 4
    //   104: aload_1
    //   105: invokevirtual 299	com/a/a/q:c	()Ljava/util/List;
    //   108: iload_2
    //   109: invokeinterface 355 2 0
    //   114: invokevirtual 358	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   117: pop
    //   118: iload_2
    //   119: aload_1
    //   120: invokevirtual 299	com/a/a/q:c	()Ljava/util/List;
    //   123: invokeinterface 352 1 0
    //   128: iconst_1
    //   129: isub
    //   130: if_icmpeq +11 -> 141
    //   133: aload 4
    //   135: ldc -75
    //   137: invokevirtual 241	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
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
    //   0	153	0	this	n
    //   0	153	1	paramQ	q
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
  final void a(r paramR)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 68	android/content/ContentValues
    //   5: dup
    //   6: invokespecial 70	android/content/ContentValues:<init>	()V
    //   9: astore_2
    //   10: aload_2
    //   11: ldc_w 361
    //   14: aload_1
    //   15: invokevirtual 216	com/a/a/r:a	()Ljava/lang/String;
    //   18: invokevirtual 74	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   21: aload_2
    //   22: ldc_w 363
    //   25: aload_1
    //   26: invokevirtual 219	com/a/a/r:b	()I
    //   29: invokestatic 99	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   32: invokevirtual 102	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/Integer;)V
    //   35: aload_0
    //   36: invokevirtual 214	com/a/a/n:e	()Lcom/a/a/r;
    //   39: astore_3
    //   40: aload_3
    //   41: ifnonnull +24 -> 65
    //   44: invokestatic 57	com/a/a/n:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   47: astore_1
    //   48: aload_1
    //   49: putstatic 36	com/a/a/n:c	Landroid/database/sqlite/SQLiteDatabase;
    //   52: aload_1
    //   53: ldc_w 365
    //   56: aconst_null
    //   57: aload_2
    //   58: invokevirtual 120	android/database/sqlite/SQLiteDatabase:insert	(Ljava/lang/String;Ljava/lang/String;Landroid/content/ContentValues;)J
    //   61: pop2
    //   62: aload_0
    //   63: monitorexit
    //   64: return
    //   65: aload_3
    //   66: invokevirtual 216	com/a/a/r:a	()Ljava/lang/String;
    //   69: aload_1
    //   70: invokevirtual 216	com/a/a/r:a	()Ljava/lang/String;
    //   73: invokevirtual 369	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   76: ifne -14 -> 62
    //   79: invokestatic 57	com/a/a/n:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   82: astore_1
    //   83: aload_1
    //   84: putstatic 36	com/a/a/n:c	Landroid/database/sqlite/SQLiteDatabase;
    //   87: aload_1
    //   88: ldc_w 371
    //   91: invokevirtual 284	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;)V
    //   94: getstatic 36	com/a/a/n:c	Landroid/database/sqlite/SQLiteDatabase;
    //   97: ldc_w 365
    //   100: aload_2
    //   101: aconst_null
    //   102: aconst_null
    //   103: invokevirtual 88	android/database/sqlite/SQLiteDatabase:update	(Ljava/lang/String;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   106: pop
    //   107: goto -45 -> 62
    //   110: astore_1
    //   111: aload_0
    //   112: monitorexit
    //   113: aload_1
    //   114: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	115	0	this	n
    //   0	115	1	paramR	r
    //   9	92	2	localContentValues	ContentValues
    //   39	27	3	localR	r
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
        aj.a();
      }
      catch (DeadObjectException paramString1)
      {
        aj.a(paramString1);
        aj.a();
        continue;
      }
      finally {}
      return;
      b(paramString1, paramString2);
      aj.a();
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
  final r e()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: monitorenter
    //   4: invokestatic 57	com/a/a/n:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   7: astore_2
    //   8: aload_2
    //   9: putstatic 36	com/a/a/n:c	Landroid/database/sqlite/SQLiteDatabase;
    //   12: aload_2
    //   13: ldc_w 365
    //   16: aconst_null
    //   17: aconst_null
    //   18: aconst_null
    //   19: aconst_null
    //   20: aconst_null
    //   21: aconst_null
    //   22: invokevirtual 207	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   25: astore_2
    //   26: aload_2
    //   27: ifnull +117 -> 144
    //   30: aload_2
    //   31: invokeinterface 133 1 0
    //   36: ifeq +108 -> 144
    //   39: aload_2
    //   40: invokeinterface 136 1 0
    //   45: ifle +99 -> 144
    //   48: invokestatic 163	com/a/a/p:a	()Lcom/a/a/p;
    //   51: astore_1
    //   52: aload_1
    //   53: invokevirtual 169	java/lang/Object:getClass	()Ljava/lang/Class;
    //   56: pop
    //   57: new 203	com/a/a/r
    //   60: dup
    //   61: aload_1
    //   62: invokespecial 204	com/a/a/r:<init>	(Lcom/a/a/p;)V
    //   65: astore_3
    //   66: aload_3
    //   67: aload_2
    //   68: aload_2
    //   69: ldc_w 361
    //   72: invokeinterface 230 2 0
    //   77: invokeinterface 140 2 0
    //   82: invokevirtual 217	com/a/a/r:a	(Ljava/lang/String;)V
    //   85: aload_3
    //   86: aload_2
    //   87: aload_2
    //   88: ldc_w 363
    //   91: invokeinterface 230 2 0
    //   96: invokeinterface 178 2 0
    //   101: invokevirtual 220	com/a/a/r:a	(I)V
    //   104: aload_3
    //   105: astore_1
    //   106: aload_2
    //   107: ifnull +11 -> 118
    //   110: aload_2
    //   111: invokeinterface 151 1 0
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
    //   132: invokeinterface 151 1 0
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
    //   149: invokeinterface 151 1 0
    //   154: aconst_null
    //   155: astore_1
    //   156: goto -38 -> 118
    //   159: astore_1
    //   160: goto -33 -> 127
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	163	0	this	n
    //   1	137	1	localObject1	Object
    //   139	4	1	localObject2	Object
    //   155	1	1	localObject3	Object
    //   159	1	1	localObject4	Object
    //   7	142	2	localObject5	Object
    //   65	52	3	localR	r
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
    //   5: new 187	java/util/ArrayList
    //   8: dup
    //   9: invokespecial 188	java/util/ArrayList:<init>	()V
    //   12: astore 5
    //   14: aload 4
    //   16: astore_3
    //   17: invokestatic 57	com/a/a/n:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   20: astore 6
    //   22: aload 4
    //   24: astore_3
    //   25: aload 6
    //   27: putstatic 36	com/a/a/n:c	Landroid/database/sqlite/SQLiteDatabase;
    //   30: aload 4
    //   32: astore_3
    //   33: aload 6
    //   35: ldc -3
    //   37: aconst_null
    //   38: invokevirtual 227	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   41: astore 4
    //   43: aload 4
    //   45: ifnull +72 -> 117
    //   48: aload 4
    //   50: astore_3
    //   51: aload 4
    //   53: invokeinterface 133 1 0
    //   58: ifeq +59 -> 117
    //   61: aload 4
    //   63: astore_3
    //   64: aload 4
    //   66: aload 4
    //   68: ldc 91
    //   70: invokeinterface 230 2 0
    //   75: invokeinterface 178 2 0
    //   80: istore_1
    //   81: aload 4
    //   83: astore_3
    //   84: invokestatic 64	com/a/a/aj:a	()V
    //   87: aload 4
    //   89: astore_3
    //   90: aload 5
    //   92: iload_1
    //   93: invokestatic 99	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   96: invokeinterface 198 2 0
    //   101: pop
    //   102: aload 4
    //   104: astore_3
    //   105: aload 4
    //   107: invokeinterface 210 1 0
    //   112: istore_2
    //   113: iload_2
    //   114: ifne -53 -> 61
    //   117: aload 4
    //   119: ifnull +10 -> 129
    //   122: aload 4
    //   124: invokeinterface 151 1 0
    //   129: aload_0
    //   130: monitorexit
    //   131: aload 5
    //   133: areturn
    //   134: astore 4
    //   136: aload_3
    //   137: ifnull +9 -> 146
    //   140: aload_3
    //   141: invokeinterface 151 1 0
    //   146: aload 4
    //   148: athrow
    //   149: astore_3
    //   150: aload_0
    //   151: monitorexit
    //   152: aload_3
    //   153: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	154	0	this	n
    //   80	13	1	i	int
    //   112	2	2	bool	boolean
    //   16	125	3	localCursor1	Cursor
    //   149	4	3	localObject1	Object
    //   1	122	4	localCursor2	Cursor
    //   134	13	4	localObject2	Object
    //   12	120	5	localArrayList	ArrayList
    //   20	14	6	localSQLiteDatabase	SQLiteDatabase
    // Exception table:
    //   from	to	target	type
    //   17	22	134	finally
    //   25	30	134	finally
    //   33	43	134	finally
    //   51	61	134	finally
    //   64	81	134	finally
    //   84	87	134	finally
    //   90	102	134	finally
    //   105	113	134	finally
    //   5	14	149	finally
    //   122	129	149	finally
    //   140	146	149	finally
    //   146	149	149	finally
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
