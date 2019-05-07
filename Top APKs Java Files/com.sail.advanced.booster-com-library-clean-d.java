package com.library.clean;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.MediaStore.Files;
import android.text.TextUtils;
import com.library.clean.c.b;
import com.library.clean.e.c.a;
import com.library.clean.e.e;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;

public class d
{
  private static final String b = "d";
  private static d c;
  final String[] a = { "mime_type", "_id", "_data", "_display_name", "_size", "title", "date_modified" };
  private List<String> d = e.a(c.a);
  private long e = 0L;
  private long f = 0L;
  private long g = 0L;
  private long h = 0L;
  private long i = 0L;
  private long j = 0L;
  private long k = 0L;
  private a l;
  private List<String> m = new ArrayList();
  
  private d()
  {
    Iterator localIterator = c.a.getPackageManager().getInstalledPackages(128).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      this.m.add(localPackageInfo.packageName);
    }
  }
  
  private long a(com.library.clean.d.c paramC)
  {
    long l1 = 0L;
    if (paramC == null) {
      return 0L;
    }
    if (paramC.i() == null) {
      return 0L;
    }
    paramC = paramC.i().iterator();
    while (paramC.hasNext()) {
      l1 += com.library.clean.e.a.e((File)paramC.next());
    }
    return l1;
  }
  
  public static d a()
  {
    if (c == null) {
      try
      {
        if (c == null) {
          c = new d();
        }
      }
      finally {}
    }
    return c;
  }
  
  private List<com.library.clean.d.c> a(Cursor paramCursor)
  {
    ArrayList localArrayList = new ArrayList();
    paramCursor.moveToFirst();
    while (!paramCursor.isAfterLast())
    {
      int i1 = paramCursor.getInt(paramCursor.getColumnIndex("_id"));
      String str = paramCursor.getString(paramCursor.getColumnIndex("_data"));
      Object localObject3 = new File(str);
      long l2 = paramCursor.getInt(paramCursor.getColumnIndex("_size"));
      long l1 = l2;
      if (l2 < 0L) {
        l1 = ((File)localObject3).length();
      }
      Object localObject2 = paramCursor.getString(paramCursor.getColumnIndex("_display_name"));
      Object localObject1 = localObject2;
      if (TextUtils.isEmpty((CharSequence)localObject2)) {
        localObject1 = paramCursor.getString(paramCursor.getColumnIndex("title"));
      }
      a(l1);
      b(str);
      localObject2 = new com.library.clean.d.c();
      ((com.library.clean.d.c)localObject2).a(true);
      ((com.library.clean.d.c)localObject2).c((String)localObject1);
      localObject1 = new ArrayList();
      ((List)localObject1).add(localObject3);
      ((com.library.clean.d.c)localObject2).a((List)localObject1);
      localObject1 = com.library.clean.e.c.d(str);
      int n;
      if (localObject1 != null)
      {
        ((com.library.clean.d.c)localObject2).b(((PackageInfo)localObject1).packageName);
        ((com.library.clean.d.c)localObject2).a(((PackageInfo)localObject1).versionName);
        ((com.library.clean.d.c)localObject2).a(((PackageInfo)localObject1).versionCode);
        localObject3 = com.library.clean.e.c.a(c.a, ((PackageInfo)localObject1).applicationInfo, (File)localObject3);
        ((com.library.clean.d.c)localObject2).c(((c.a)localObject3).a.toString());
        ((com.library.clean.d.c)localObject2).a(((c.a)localObject3).b);
        if (com.library.clean.e.c.c(((PackageInfo)localObject1).packageName))
        {
          n = 0;
        }
        else
        {
          ((com.library.clean.d.c)localObject2).b(1);
          break label321;
        }
      }
      else
      {
        n = 2;
      }
      ((com.library.clean.d.c)localObject2).b(n);
      label321:
      ((com.library.clean.d.c)localObject2).c(i1);
      ((com.library.clean.d.c)localObject2).a(l1);
      this.h += l1;
      localArrayList.add(localObject2);
      paramCursor.moveToNext();
    }
    return localArrayList;
  }
  
  private List<com.library.clean.d.c> a(Cursor paramCursor, String paramString1, String paramString2)
  {
    ArrayList localArrayList1 = new ArrayList();
    while (paramCursor.moveToNext())
    {
      com.library.clean.d.c localC = new com.library.clean.d.c();
      Object localObject2 = paramCursor.getString(paramCursor.getColumnIndex(paramString2)).split("#");
      localC.c(paramCursor.getString(paramCursor.getColumnIndex(paramString1)));
      localC.a(true);
      ArrayList localArrayList2 = new ArrayList();
      Iterator localIterator = this.d.iterator();
      long l2 = 0L;
      if (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        int i1 = localObject2.length;
        int n = 0;
        long l1 = l2;
        Object localObject1 = localObject2;
        for (;;)
        {
          localObject2 = localObject1;
          l2 = l1;
          if (n >= i1) {
            break;
          }
          localObject2 = localObject1[n];
          Object localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(str);
          ((StringBuilder)localObject3).append((String)localObject2);
          localObject3 = ((StringBuilder)localObject3).toString();
          if (com.library.clean.e.a.b((String)localObject3))
          {
            l2 = com.library.clean.e.a.e((String)localObject3);
            a(str, (String)localObject2);
            a(l2);
            localArrayList2.add(new File((String)localObject3));
            this.f += l2;
            l1 += l2;
          }
          n += 1;
        }
      }
      if (l2 > 0L)
      {
        localC.a(l2);
        localC.a(localArrayList2);
        localArrayList1.add(localC);
      }
    }
    return localArrayList1;
  }
  
  private void a(long paramLong)
  {
    if (this.l == null) {
      return;
    }
    this.k += paramLong;
    try
    {
      if (this.k > 10240L)
      {
        this.l.a(this.k);
        this.k = 0L;
      }
      return;
    }
    finally {}
  }
  
  private void a(String paramString1, String paramString2)
  {
    if (this.l == null) {
      return;
    }
    if (paramString2.length() < 20)
    {
      a localA = this.l;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString1);
      localStringBuilder.append(paramString2);
      localA.a(localStringBuilder.toString());
      return;
    }
    this.l.a(paramString2);
  }
  
  private void a(List<com.library.clean.d.c> paramList)
  {
    if (paramList != null)
    {
      if (paramList.isEmpty()) {
        return;
      }
      Collections.sort(paramList, new b());
    }
  }
  
  private String b(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1)) {
      return "";
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("/Android/data/");
    ((StringBuilder)localObject).append(paramString2);
    paramString2 = ((StringBuilder)localObject).toString();
    if (paramString1.contains("@1"))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString2);
      ((StringBuilder)localObject).append("/cache");
      paramString2 = ((StringBuilder)localObject).toString();
      localObject = paramString1.replace("@1", paramString2);
    }
    else
    {
      localObject = paramString1;
    }
    String str = paramString2;
    if (paramString1.contains("@2"))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramString2);
      ((StringBuilder)localObject).append("/files");
      str = ((StringBuilder)localObject).toString();
      localObject = paramString1.replace("@2", str);
    }
    if (paramString1.contains("@3"))
    {
      paramString2 = new StringBuilder();
      paramString2.append(str);
      paramString2.append("/");
      localObject = paramString1.replace("@3", paramString2.toString());
    }
    return localObject;
  }
  
  private List<com.library.clean.d.c> b(Cursor paramCursor)
  {
    ArrayList localArrayList = new ArrayList();
    paramCursor.moveToFirst();
    while (!paramCursor.isAfterLast())
    {
      int n = paramCursor.getInt(paramCursor.getColumnIndex("_id"));
      String str = paramCursor.getString(paramCursor.getColumnIndex("_data"));
      File localFile = new File(str);
      long l2 = paramCursor.getInt(paramCursor.getColumnIndex("_size"));
      long l1 = l2;
      if (l2 < 0L) {
        l1 = localFile.length();
      }
      Object localObject2 = paramCursor.getString(paramCursor.getColumnIndex("_display_name"));
      Object localObject1 = localObject2;
      if (TextUtils.isEmpty((CharSequence)localObject2)) {
        localObject1 = paramCursor.getString(paramCursor.getColumnIndex("title"));
      }
      b(str);
      localObject2 = new com.library.clean.d.c();
      ((com.library.clean.d.c)localObject2).a(false);
      ((com.library.clean.d.c)localObject2).c((String)localObject1);
      localObject1 = new ArrayList();
      ((List)localObject1).add(localFile);
      ((com.library.clean.d.c)localObject2).a((List)localObject1);
      ((com.library.clean.d.c)localObject2).c(n);
      ((com.library.clean.d.c)localObject2).a(l1);
      if (l1 > 104857600L)
      {
        this.j += l1;
        a(l1);
        localArrayList.add(localObject2);
      }
      paramCursor.moveToNext();
    }
    return localArrayList;
  }
  
  private List<com.library.clean.d.c> b(Cursor paramCursor, String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    while (paramCursor.moveToNext())
    {
      com.library.clean.d.c localC = new com.library.clean.d.c();
      String[] arrayOfString = paramCursor.getString(paramCursor.getColumnIndex(paramString2)).split("#");
      Object localObject1 = paramCursor.getString(paramCursor.getColumnIndex(paramString1));
      localC.d(paramCursor.getString(paramCursor.getColumnIndex("pkg_names")));
      localC.c((String)localObject1);
      localC.a(true);
      localObject1 = new ArrayList();
      Iterator localIterator = this.d.iterator();
      while (localIterator.hasNext())
      {
        String str1 = (String)localIterator.next();
        int i1 = arrayOfString.length;
        int n = 0;
        while (n < i1)
        {
          String str2 = arrayOfString[n];
          Object localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append(str1);
          ((StringBuilder)localObject2).append(str2);
          localObject2 = ((StringBuilder)localObject2).toString();
          if (com.library.clean.e.a.b((String)localObject2))
          {
            ((List)localObject1).add(new File((String)localObject2));
            a(str1, str2);
          }
          n += 1;
        }
      }
      localC.a((List)localObject1);
      localArrayList.add(localC);
    }
    if (localArrayList.size() == 0) {
      return localArrayList;
    }
    paramCursor = new ArrayList();
    paramString1 = localArrayList.iterator();
    while (paramString1.hasNext())
    {
      paramString2 = (com.library.clean.d.c)paramString1.next();
      if (!c(paramString2.j()))
      {
        long l1 = a(paramString2);
        if (l1 > 0L)
        {
          paramString2.a(l1);
          paramCursor.add(paramString2);
          this.i += l1;
          a(l1);
        }
      }
    }
    return paramCursor;
  }
  
  private void b(String paramString)
  {
    if (this.l == null) {
      return;
    }
    this.l.a(paramString);
  }
  
  private com.library.clean.d.c c(String paramString1, String paramString2)
  {
    for (;;)
    {
      int n;
      try
      {
        com.library.clean.d.c localC = new com.library.clean.d.c();
        localC.a(true);
        paramString1 = new JSONArray(paramString1);
        ArrayList localArrayList = new ArrayList();
        long l1 = 0L;
        n = 0;
        if (n < paramString1.length())
        {
          String str1 = paramString1.getString(n);
          Iterator localIterator = this.d.iterator();
          if (!localIterator.hasNext()) {
            break label254;
          }
          String str2 = (String)localIterator.next();
          Object localObject = new StringBuilder();
          ((StringBuilder)localObject).append(str2);
          ((StringBuilder)localObject).append(str1);
          localObject = ((StringBuilder)localObject).toString();
          if (!com.library.clean.e.a.b((String)localObject)) {
            continue;
          }
          long l2 = com.library.clean.e.a.e((String)localObject);
          a(str2, str1);
          localArrayList.addAll(d((String)localObject, "app_cache"));
          a(l2);
          l1 += l2;
          continue;
        }
        if ((localArrayList != null) && (localArrayList.size() > 0))
        {
          localC.a(localArrayList);
          localC.a(l1);
          localC.b(paramString2);
          localC.a(com.library.clean.e.c.a(paramString2));
          localC.c(com.library.clean.e.c.b(paramString2));
          this.g += localC.f();
          return localC;
        }
      }
      catch (JSONException paramString1)
      {
        paramString1.printStackTrace();
      }
      return null;
      label254:
      n += 1;
    }
  }
  
  private boolean c(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    paramString = paramString.split("#");
    int i2 = paramString.length;
    int n = 0;
    for (;;)
    {
      if (n < i2)
      {
        Object localObject = paramString[n];
        try
        {
          localObject = new JSONArray((String)localObject);
          int i1 = 0;
          while (i1 < ((JSONArray)localObject).length())
          {
            localArrayList.add(((JSONArray)localObject).getString(i1));
            i1 += 1;
          }
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
          n += 1;
        }
      }
    }
    localArrayList.retainAll(this.m);
    return localArrayList.size() > 0;
  }
  
  private List<File> d(String paramString1, String paramString2)
  {
    if (TextUtils.equals(paramString2, "apk")) {
      paramString2 = new com.library.clean.c.a();
    } else {
      paramString2 = new b();
    }
    paramString2 = com.library.clean.e.a.a(paramString1, paramString2, true);
    paramString1 = paramString2;
    if (paramString2 == null) {
      paramString1 = new ArrayList();
    }
    return paramString1;
  }
  
  private void g()
  {
    if (this.l == null) {
      return;
    }
    try
    {
      if (this.k > 0L)
      {
        this.l.a(this.k);
        this.k = 0L;
      }
      return;
    }
    finally {}
  }
  
  private String h()
  {
    StringBuilder localStringBuilder1 = new StringBuilder();
    Iterator localIterator = this.m.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append("'");
      localStringBuilder2.append(str);
      localStringBuilder2.append("',");
      localStringBuilder1.append(localStringBuilder2.toString());
    }
    if (localStringBuilder1.length() > 0) {
      return localStringBuilder1.substring(0, localStringBuilder1.length() - 1);
    }
    return "empty";
  }
  
  private int[] i()
  {
    try
    {
      if (new Random().nextInt(100) % 2 == 0) {
        return new int[] { 0, 3900 };
      }
      return new int[] { 3900, 7800 };
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return tmp61_55;
  }
  
  public List<com.library.clean.d.c> a(String paramString)
  {
    ArrayList localArrayList1 = new ArrayList();
    try
    {
      ArrayList localArrayList2 = new ArrayList();
      Object localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("select app_name , residual_junk_path from residual_junks where pkg_names like '%");
      ((StringBuilder)localObject1).append(paramString);
      ((StringBuilder)localObject1).append("%';");
      Object localObject2 = ((StringBuilder)localObject1).toString();
      localObject1 = com.library.clean.b.a.a();
      if (localObject1 != null)
      {
        localObject2 = ((SQLiteDatabase)localObject1).rawQuery((String)localObject2, null);
        while (((Cursor)localObject2).moveToNext())
        {
          com.library.clean.d.c localC = new com.library.clean.d.c();
          long l1 = 0L;
          localC.b(paramString);
          String str1 = ((Cursor)localObject2).getString(((Cursor)localObject2).getColumnIndex("residual_junk_path"));
          localC.c(((Cursor)localObject2).getString(((Cursor)localObject2).getColumnIndex("app_name")));
          Iterator localIterator = this.d.iterator();
          while (localIterator.hasNext())
          {
            String str2 = (String)localIterator.next();
            Object localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append(str2);
            ((StringBuilder)localObject3).append(str1);
            localObject3 = ((StringBuilder)localObject3).toString();
            if (com.library.clean.e.a.b((String)localObject3))
            {
              long l2 = com.library.clean.e.a.e((String)localObject3);
              a(str2, str1);
              localArrayList2.add(new File((String)localObject3));
              l1 += l2;
            }
          }
          localC.a(l1);
          localC.a(localArrayList2);
          if ((localArrayList2 != null) && (localArrayList2.size() > 0)) {
            localArrayList1.add(localC);
          }
        }
        ((Cursor)localObject2).close();
        ((SQLiteDatabase)localObject1).close();
      }
      return localArrayList1;
    }
    catch (Exception paramString) {}
    return localArrayList1;
  }
  
  public void a(a paramA)
  {
    this.l = paramA;
  }
  
  public com.library.clean.d.d b()
  {
    this.h = 0L;
    com.library.clean.d.d localD = new com.library.clean.d.d();
    localD.a("apk");
    localD.b(4);
    localD.a(0);
    Object localObject = MediaStore.Files.getContentUri("external");
    localObject = c.a.getContentResolver().query((Uri)localObject, this.a, "(_data LIKE '%.apk') and _size >1 ", null, "date_added DESC");
    if (localObject != null)
    {
      localD.a(a((Cursor)localObject));
      ((Cursor)localObject).close();
    }
    localD.a(this.h);
    g();
    a(localD.d());
    return localD;
  }
  
  /* Error */
  public com.library.clean.d.d c()
  {
    // Byte code:
    //   0: aload_0
    //   1: lconst_0
    //   2: putfield 57	com/library/clean/d:f	J
    //   5: new 421	com/library/clean/d/d
    //   8: dup
    //   9: invokespecial 422	com/library/clean/d/d:<init>	()V
    //   12: astore 5
    //   14: aload 5
    //   16: iconst_3
    //   17: invokevirtual 424	com/library/clean/d/d:b	(I)V
    //   20: invokestatic 402	com/library/clean/b/a:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   23: astore 4
    //   25: new 81	java/util/ArrayList
    //   28: dup
    //   29: invokespecial 82	java/util/ArrayList:<init>	()V
    //   32: astore_2
    //   33: aload_2
    //   34: astore_1
    //   35: aload 4
    //   37: ifnull +88 -> 125
    //   40: aload 4
    //   42: ldc_w 458
    //   45: aconst_null
    //   46: invokevirtual 408	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   49: astore_1
    //   50: aload_0
    //   51: aload_1
    //   52: ldc_w 460
    //   55: ldc_w 462
    //   58: invokespecial 464	com/library/clean/d:a	(Landroid/database/Cursor;Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;
    //   61: astore_3
    //   62: aload_1
    //   63: ifnull +9 -> 72
    //   66: aload_1
    //   67: invokeinterface 415 1 0
    //   72: aload 4
    //   74: invokevirtual 416	android/database/sqlite/SQLiteDatabase:close	()V
    //   77: aload_3
    //   78: astore_1
    //   79: goto +46 -> 125
    //   82: astore_2
    //   83: goto +6 -> 89
    //   86: astore_2
    //   87: aconst_null
    //   88: astore_1
    //   89: aload_1
    //   90: ifnull +9 -> 99
    //   93: aload_1
    //   94: invokeinterface 415 1 0
    //   99: aload 4
    //   101: invokevirtual 416	android/database/sqlite/SQLiteDatabase:close	()V
    //   104: aload_2
    //   105: athrow
    //   106: aconst_null
    //   107: astore_1
    //   108: aload_1
    //   109: ifnull +9 -> 118
    //   112: aload_1
    //   113: invokeinterface 415 1 0
    //   118: aload 4
    //   120: invokevirtual 416	android/database/sqlite/SQLiteDatabase:close	()V
    //   123: aload_2
    //   124: astore_1
    //   125: aload 5
    //   127: aload_1
    //   128: invokevirtual 450	com/library/clean/d/d:a	(Ljava/util/List;)V
    //   131: aload 5
    //   133: ldc_w 466
    //   136: invokevirtual 423	com/library/clean/d/d:a	(Ljava/lang/String;)V
    //   139: aload 5
    //   141: iconst_2
    //   142: invokevirtual 425	com/library/clean/d/d:a	(I)V
    //   145: aload 5
    //   147: aload_0
    //   148: getfield 57	com/library/clean/d:f	J
    //   151: invokevirtual 451	com/library/clean/d/d:a	(J)V
    //   154: aload_0
    //   155: invokespecial 453	com/library/clean/d:g	()V
    //   158: aload_0
    //   159: aload 5
    //   161: invokevirtual 455	com/library/clean/d/d:d	()Ljava/util/List;
    //   164: invokespecial 456	com/library/clean/d:a	(Ljava/util/List;)V
    //   167: aload 5
    //   169: areturn
    //   170: astore_1
    //   171: goto -65 -> 106
    //   174: astore_3
    //   175: goto -67 -> 108
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	178	0	this	d
    //   34	94	1	localObject1	Object
    //   170	1	1	localException1	Exception
    //   32	2	2	localArrayList	ArrayList
    //   82	1	2	localObject2	Object
    //   86	38	2	localObject3	Object
    //   61	17	3	localList	List
    //   174	1	3	localException2	Exception
    //   23	96	4	localSQLiteDatabase	SQLiteDatabase
    //   12	156	5	localD	com.library.clean.d.d
    // Exception table:
    //   from	to	target	type
    //   50	62	82	finally
    //   40	50	86	finally
    //   40	50	170	java/lang/Exception
    //   50	62	174	java/lang/Exception
  }
  
  public com.library.clean.d.d d()
  {
    this.g = 0L;
    com.library.clean.d.d localD1 = new com.library.clean.d.d();
    localD1.b(1);
    localD1.a(2);
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = h();
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("select pkg_name,app_cache_path from app_cache where pkg_name IN (");
    ((StringBuilder)localObject2).append((String)localObject1);
    ((StringBuilder)localObject2).append(");");
    localObject2 = ((StringBuilder)localObject2).toString();
    localObject1 = com.library.clean.b.a.a();
    if (localObject1 != null)
    {
      localObject2 = ((SQLiteDatabase)localObject1).rawQuery((String)localObject2, null);
      if (localObject2 != null) {
        try
        {
          while (((Cursor)localObject2).moveToNext())
          {
            Object localObject3 = ((Cursor)localObject2).getString(((Cursor)localObject2).getColumnIndex("pkg_name"));
            localObject3 = c(b(((Cursor)localObject2).getString(((Cursor)localObject2).getColumnIndex("app_cache_path")), (String)localObject3), (String)localObject3);
            if (localObject3 != null) {
              localArrayList.add(localObject3);
            }
          }
        }
        finally
        {
          ((Cursor)localObject2).close();
          ((SQLiteDatabase)localObject1).close();
        }
      }
    }
    localD2.a("app_cache");
    localD2.a(localArrayList);
    localD2.a(this.g);
    g();
    a(localD2.d());
    return localD2;
  }
  
  /* Error */
  public com.library.clean.d.d e()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 482	com/library/clean/d:i	()[I
    //   4: astore_1
    //   5: aload_0
    //   6: lconst_0
    //   7: putfield 63	com/library/clean/d:i	J
    //   10: new 421	com/library/clean/d/d
    //   13: dup
    //   14: invokespecial 422	com/library/clean/d/d:<init>	()V
    //   17: astore 5
    //   19: aload 5
    //   21: ldc_w 484
    //   24: invokevirtual 423	com/library/clean/d/d:a	(Ljava/lang/String;)V
    //   27: aload 5
    //   29: iconst_2
    //   30: invokevirtual 424	com/library/clean/d/d:b	(I)V
    //   33: invokestatic 402	com/library/clean/b/a:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   36: astore 4
    //   38: new 250	java/lang/StringBuilder
    //   41: dup
    //   42: invokespecial 251	java/lang/StringBuilder:<init>	()V
    //   45: astore_2
    //   46: aload_2
    //   47: ldc_w 486
    //   50: invokevirtual 255	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: pop
    //   54: aload_2
    //   55: aload_1
    //   56: iconst_0
    //   57: iaload
    //   58: invokevirtual 489	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   61: pop
    //   62: aload_2
    //   63: ldc_w 491
    //   66: invokevirtual 255	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: pop
    //   70: aload_2
    //   71: aload_1
    //   72: iconst_1
    //   73: iaload
    //   74: invokevirtual 489	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   77: pop
    //   78: aload_2
    //   79: invokevirtual 256	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   82: astore_3
    //   83: aconst_null
    //   84: astore_2
    //   85: aload_2
    //   86: astore_1
    //   87: aload 4
    //   89: ifnull +86 -> 175
    //   92: aload 4
    //   94: aload_3
    //   95: aconst_null
    //   96: invokevirtual 408	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   99: astore_1
    //   100: aload_0
    //   101: aload_1
    //   102: ldc_w 412
    //   105: ldc_w 410
    //   108: invokespecial 493	com/library/clean/d:b	(Landroid/database/Cursor;Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;
    //   111: astore_3
    //   112: aload_1
    //   113: ifnull +9 -> 122
    //   116: aload_1
    //   117: invokeinterface 415 1 0
    //   122: aload 4
    //   124: invokevirtual 416	android/database/sqlite/SQLiteDatabase:close	()V
    //   127: aload_3
    //   128: astore_1
    //   129: goto +46 -> 175
    //   132: astore_2
    //   133: goto +6 -> 139
    //   136: astore_2
    //   137: aconst_null
    //   138: astore_1
    //   139: aload_1
    //   140: ifnull +9 -> 149
    //   143: aload_1
    //   144: invokeinterface 415 1 0
    //   149: aload 4
    //   151: invokevirtual 416	android/database/sqlite/SQLiteDatabase:close	()V
    //   154: aload_2
    //   155: athrow
    //   156: aconst_null
    //   157: astore_1
    //   158: aload_1
    //   159: ifnull +9 -> 168
    //   162: aload_1
    //   163: invokeinterface 415 1 0
    //   168: aload 4
    //   170: invokevirtual 416	android/database/sqlite/SQLiteDatabase:close	()V
    //   173: aload_2
    //   174: astore_1
    //   175: aload 5
    //   177: aload_1
    //   178: invokevirtual 450	com/library/clean/d/d:a	(Ljava/util/List;)V
    //   181: aload 5
    //   183: aload_0
    //   184: getfield 63	com/library/clean/d:i	J
    //   187: invokevirtual 451	com/library/clean/d/d:a	(J)V
    //   190: aload 5
    //   192: iconst_2
    //   193: invokevirtual 425	com/library/clean/d/d:a	(I)V
    //   196: aload_0
    //   197: invokespecial 453	com/library/clean/d:g	()V
    //   200: aload_0
    //   201: aload 5
    //   203: invokevirtual 455	com/library/clean/d/d:d	()Ljava/util/List;
    //   206: invokespecial 456	com/library/clean/d:a	(Ljava/util/List;)V
    //   209: aload 5
    //   211: areturn
    //   212: astore_1
    //   213: goto -57 -> 156
    //   216: astore_3
    //   217: goto -59 -> 158
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	220	0	this	d
    //   4	174	1	localObject1	Object
    //   212	1	1	localException1	Exception
    //   45	41	2	localStringBuilder	StringBuilder
    //   132	1	2	localObject2	Object
    //   136	38	2	localObject3	Object
    //   82	46	3	localObject4	Object
    //   216	1	3	localException2	Exception
    //   36	133	4	localSQLiteDatabase	SQLiteDatabase
    //   17	193	5	localD	com.library.clean.d.d
    // Exception table:
    //   from	to	target	type
    //   100	112	132	finally
    //   92	100	136	finally
    //   92	100	212	java/lang/Exception
    //   100	112	216	java/lang/Exception
  }
  
  public com.library.clean.d.d f()
  {
    this.j = 0L;
    com.library.clean.d.d localD = new com.library.clean.d.d();
    new ArrayList();
    localD.a("big_file");
    localD.b(5);
    localD.a(0);
    Object localObject = MediaStore.Files.getContentUri("external");
    localObject = c.a.getContentResolver().query((Uri)localObject, this.a, "(_data NOT LIKE '%.apk') and _size >? ", new String[] { "104857600" }, "date_added DESC");
    if (localObject != null)
    {
      localD.a(b((Cursor)localObject));
      ((Cursor)localObject).close();
    }
    localD.a(this.j);
    g();
    a(localD.d());
    return localD;
  }
  
  public static abstract interface a
  {
    public abstract void a(long paramLong);
    
    public abstract void a(String paramString);
  }
  
  public class b
    implements Comparator<com.library.clean.d.c>
  {
    public b() {}
    
    public int a(com.library.clean.d.c paramC1, com.library.clean.d.c paramC2)
    {
      return -Long.compare(paramC1.f(), paramC2.f());
    }
  }
}
