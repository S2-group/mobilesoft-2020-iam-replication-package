package com.android.launcher3.d;

import android.content.ComponentName;
import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Point;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.launcher3.LauncherProvider;
import com.android.launcher3.ag;
import com.android.launcher3.ai;
import com.android.launcher3.ak;
import com.android.launcher3.ar;
import com.android.launcher3.au.c;
import com.android.launcher3.b.l;
import com.android.launcher3.bj;
import com.android.launcher3.util.f;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class c
{
  public static boolean a = false;
  public ArrayList<Long> b;
  final int c;
  final int d;
  private final Context e;
  private final ContentValues f = new ContentValues();
  private final HashMap<String, Point> g;
  private final ag h;
  private HashSet<String> i;
  private ArrayList<ContentProviderOperation> j;
  private ArrayList<a> k;
  private final int l;
  private final int m;
  private final boolean n;
  private final boolean o;
  
  public c(Context paramContext)
  {
    this.e = paramContext;
    paramContext = c(paramContext);
    Object localObject = c(paramContext.getString("migration_restore_src_size", ""));
    this.l = ((Point)localObject).x;
    this.m = ((Point)localObject).y;
    this.g = new HashMap();
    paramContext = paramContext.getStringSet("migration_widget_min_size", Collections.emptySet()).iterator();
    boolean bool1;
    boolean bool2;
    for (;;)
    {
      bool1 = paramContext.hasNext();
      bool2 = true;
      if (!bool1) {
        break;
      }
      localObject = ((String)paramContext.next()).split("#");
      this.g.put(localObject[0], c(localObject[1]));
    }
    this.h = ak.a().m();
    this.c = this.h.e;
    this.d = this.h.d;
    if (this.c < this.l) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    this.n = bool1;
    if (this.d < this.m) {
      bool1 = bool2;
    } else {
      bool1 = false;
    }
    this.o = bool1;
  }
  
  private ArrayList<a> a(int paramInt1, int paramInt2, ArrayList<a> paramArrayList, float[] paramArrayOfFloat)
  {
    boolean[][] arrayOfBoolean = (boolean[][])Array.newInstance(Boolean.TYPE, new int[] { this.c, this.d });
    if (!this.n) {
      paramInt1 = Integer.MAX_VALUE;
    }
    if (!this.o) {
      paramInt2 = Integer.MAX_VALUE;
    }
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      a localA = (a)paramArrayList.next();
      if (((localA.k <= paramInt1) && (localA.m + localA.k > paramInt1)) || ((localA.l <= paramInt2) && (localA.n + localA.l > paramInt2)))
      {
        localArrayList2.add(localA);
        if (localA.k >= paramInt1) {
          localA.k -= 1;
        }
        if (localA.l >= paramInt2) {
          localA.l -= 1;
        }
      }
      else
      {
        if (localA.k > paramInt1) {
          localA.k -= 1;
        }
        if (localA.l > paramInt2) {
          localA.l -= 1;
        }
        localArrayList1.add(localA);
        a(arrayOfBoolean, localA, true);
      }
    }
    paramArrayList = new b(arrayOfBoolean, localArrayList2);
    paramArrayList.a();
    localArrayList1.addAll(paramArrayList.c);
    paramArrayOfFloat[0] = paramArrayList.a;
    paramArrayOfFloat[1] = paramArrayList.b;
    return localArrayList1;
  }
  
  static ArrayList<a> a(ArrayList<a> paramArrayList)
  {
    ArrayList localArrayList = new ArrayList(paramArrayList.size());
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext()) {
      localArrayList.add(((a)paramArrayList.next()).b());
    }
    return localArrayList;
  }
  
  private void a(a paramA)
  {
    this.f.clear();
    paramA.a(this.f);
    this.j.add(ContentProviderOperation.newUpdate(au.c.a(paramA.g)).withValues(this.f).build());
  }
  
  private void a(String paramString)
    throws Exception
  {
    paramString = Intent.parseUri(paramString, 0);
    if (paramString.getComponent() != null)
    {
      b(paramString.getComponent().getPackageName());
      return;
    }
    if (paramString.getPackage() != null) {
      b(paramString.getPackage());
    }
  }
  
  public static boolean a(Context paramContext)
  {
    return TextUtils.isEmpty(c(paramContext).getString("migration_restore_src_size", "")) ^ true;
  }
  
  private void b(long paramLong)
  {
    Object localObject4 = a(paramLong);
    Object localObject5 = new float[2];
    int i1 = Integer.MAX_VALUE;
    float f1 = Float.MAX_VALUE;
    float f2 = f1;
    int i3 = 0;
    Object localObject1 = null;
    int i2 = Integer.MAX_VALUE;
    int i4;
    int i5;
    Object localObject2;
    float f3;
    for (;;)
    {
      i4 = i1;
      i5 = i2;
      localObject2 = localObject1;
      f3 = f1;
      if (i3 >= this.l) {
        break;
      }
      i4 = i1;
      i5 = 0;
      f3 = f1;
      i1 = i2;
      i2 = i4;
      i4 = i5;
      int i6;
      float f4;
      for (;;)
      {
        i6 = i2;
        i5 = i1;
        f1 = f3;
        f4 = f2;
        localObject2 = localObject1;
        if (i4 >= this.m) {
          break;
        }
        localObject3 = a(i3, i4, a((ArrayList)localObject4), (float[])localObject5);
        if (localObject5[0] >= f3)
        {
          i6 = i2;
          i5 = i1;
          f1 = f3;
          f4 = f2;
          localObject2 = localObject1;
          if (localObject5[0] == f3)
          {
            i6 = i2;
            i5 = i1;
            f1 = f3;
            f4 = f2;
            localObject2 = localObject1;
            if (localObject5[1] >= f2) {}
          }
        }
        else
        {
          f1 = localObject5[0];
          f4 = localObject5[1];
          if (this.n) {
            i2 = i3;
          }
          if (this.o) {
            i1 = i4;
          }
          localObject2 = localObject3;
          i5 = i1;
          i6 = i2;
        }
        if (!this.o) {
          break;
        }
        i4 += 1;
        i2 = i6;
        i1 = i5;
        f3 = f1;
        f2 = f4;
        localObject1 = localObject2;
      }
      i1 = i6;
      i2 = i5;
      localObject1 = localObject2;
      if (!this.n)
      {
        i4 = i1;
        i5 = i2;
        localObject2 = localObject1;
        f3 = f1;
        break;
      }
      i3 += 1;
      f2 = f4;
    }
    Log.d("MigrateFromRestoreTask", String.format("Removing row %d, column %d on screen %d", new Object[] { Integer.valueOf(i5), Integer.valueOf(i4), Long.valueOf(paramLong) }));
    localObject1 = new f();
    Object localObject3 = a((ArrayList)localObject4).iterator();
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = (a)((Iterator)localObject3).next();
      ((f)localObject1).put(((a)localObject4).g, localObject4);
    }
    localObject3 = ((ArrayList)localObject2).iterator();
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = (a)((Iterator)localObject3).next();
      localObject5 = (a)((f)localObject1).get(((a)localObject4).g);
      ((f)localObject1).remove(((a)localObject4).g);
      if (!((a)localObject4).b((a)localObject5)) {
        a((a)localObject4);
      }
    }
    localObject1 = ((f)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject3 = (a)((Iterator)localObject1).next();
      this.k.add(localObject3);
    }
    if ((!this.k.isEmpty()) && (f3 == 0.0F))
    {
      localObject1 = (boolean[][])Array.newInstance(Boolean.TYPE, new int[] { this.c, this.d });
      localObject2 = ((ArrayList)localObject2).iterator();
      while (((Iterator)localObject2).hasNext()) {
        a((boolean[][])localObject1, (a)((Iterator)localObject2).next(), true);
      }
      localObject1 = new b((boolean[][])localObject1, a(this.k), true);
      ((b)localObject1).a();
      if (((b)localObject1).a == 0.0F)
      {
        localObject1 = ((b)localObject1).c.iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (a)((Iterator)localObject1).next();
          ((a)localObject2).j = paramLong;
          a((a)localObject2);
        }
        this.k.clear();
      }
    }
  }
  
  public static void b(Context paramContext)
  {
    c(paramContext).edit().remove("migration_restore_src_size").remove("migration_widget_min_size").commit();
  }
  
  private void b(String paramString)
    throws Exception
  {
    if (!this.i.contains(paramString)) {
      throw new Exception("Package not available");
    }
  }
  
  private int c(long paramLong)
  {
    Object localObject1 = this.e.getContentResolver();
    Uri localUri = au.c.a;
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("container = ");
    ((StringBuilder)localObject2).append(paramLong);
    localObject2 = ((StringBuilder)localObject2).toString();
    localObject1 = ((ContentResolver)localObject1).query(localUri, new String[] { "_id", "intent" }, (String)localObject2, null, null, null);
    int i1 = 0;
    while (((Cursor)localObject1).moveToNext())
    {
      try
      {
        a(((Cursor)localObject1).getString(1));
        i1 += 1;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
      this.b.add(Long.valueOf(((Cursor)localObject1).getLong(0)));
    }
    return i1;
  }
  
  private static SharedPreferences c(Context paramContext)
  {
    return paramContext.getSharedPreferences(ak.i(), 0);
  }
  
  private static Point c(String paramString)
  {
    paramString = paramString.split(",");
    return new Point(Integer.parseInt(paramString[0]), Integer.parseInt(paramString[1]));
  }
  
  /* Error */
  public ArrayList<a> a(long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 51	com/android/launcher3/d/c:e	Landroid/content/Context;
    //   4: invokevirtual 367	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   7: astore 15
    //   9: getstatic 370	com/android/launcher3/au$c:a	Landroid/net/Uri;
    //   12: astore 16
    //   14: new 372	java/lang/StringBuilder
    //   17: dup
    //   18: invokespecial 373	java/lang/StringBuilder:<init>	()V
    //   21: astore 17
    //   23: aload 17
    //   25: ldc_w 428
    //   28: invokevirtual 379	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   31: pop
    //   32: aload 17
    //   34: lload_1
    //   35: invokevirtual 382	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   38: pop
    //   39: aload 17
    //   41: invokevirtual 385	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   44: astore 17
    //   46: aload 15
    //   48: aload 16
    //   50: bipush 8
    //   52: anewarray 114	java/lang/String
    //   55: dup
    //   56: iconst_0
    //   57: ldc_w 387
    //   60: aastore
    //   61: dup
    //   62: iconst_1
    //   63: ldc_w 430
    //   66: aastore
    //   67: dup
    //   68: iconst_2
    //   69: ldc_w 432
    //   72: aastore
    //   73: dup
    //   74: iconst_3
    //   75: ldc_w 434
    //   78: aastore
    //   79: dup
    //   80: iconst_4
    //   81: ldc_w 436
    //   84: aastore
    //   85: dup
    //   86: iconst_5
    //   87: ldc_w 438
    //   90: aastore
    //   91: dup
    //   92: bipush 6
    //   94: ldc_w 389
    //   97: aastore
    //   98: dup
    //   99: bipush 7
    //   101: ldc_w 440
    //   104: aastore
    //   105: aload 17
    //   107: aconst_null
    //   108: aconst_null
    //   109: aconst_null
    //   110: invokevirtual 395	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Landroid/os/CancellationSignal;)Landroid/database/Cursor;
    //   113: astore 15
    //   115: aload 15
    //   117: ldc_w 387
    //   120: invokeinterface 443 2 0
    //   125: istore 7
    //   127: aload 15
    //   129: ldc_w 430
    //   132: invokeinterface 443 2 0
    //   137: istore 6
    //   139: aload 15
    //   141: ldc_w 432
    //   144: invokeinterface 443 2 0
    //   149: istore 9
    //   151: aload 15
    //   153: ldc_w 434
    //   156: invokeinterface 443 2 0
    //   161: istore 10
    //   163: aload 15
    //   165: ldc_w 436
    //   168: invokeinterface 443 2 0
    //   173: istore 11
    //   175: aload 15
    //   177: ldc_w 438
    //   180: invokeinterface 443 2 0
    //   185: istore 12
    //   187: aload 15
    //   189: ldc_w 389
    //   192: invokeinterface 443 2 0
    //   197: istore 13
    //   199: aload 15
    //   201: ldc_w 440
    //   204: invokeinterface 443 2 0
    //   209: istore 5
    //   211: new 165	java/util/ArrayList
    //   214: dup
    //   215: invokespecial 166	java/util/ArrayList:<init>	()V
    //   218: astore 17
    //   220: aload 15
    //   222: invokeinterface 400 1 0
    //   227: ifeq +573 -> 800
    //   230: new 6	com/android/launcher3/d/c$a
    //   233: dup
    //   234: invokespecial 444	com/android/launcher3/d/c$a:<init>	()V
    //   237: astore 18
    //   239: aload 18
    //   241: aload 15
    //   243: iload 7
    //   245: invokeinterface 411 2 0
    //   250: putfield 222	com/android/launcher3/d/c$a:g	J
    //   253: aload 18
    //   255: aload 15
    //   257: iload 6
    //   259: invokeinterface 448 2 0
    //   264: putfield 450	com/android/launcher3/d/c$a:h	I
    //   267: aload 18
    //   269: aload 15
    //   271: iload 9
    //   273: invokeinterface 448 2 0
    //   278: putfield 169	com/android/launcher3/d/c$a:k	I
    //   281: aload 18
    //   283: aload 15
    //   285: iload 10
    //   287: invokeinterface 448 2 0
    //   292: putfield 171	com/android/launcher3/d/c$a:l	I
    //   295: aload 18
    //   297: aload 15
    //   299: iload 11
    //   301: invokeinterface 448 2 0
    //   306: putfield 170	com/android/launcher3/d/c$a:m	I
    //   309: aload 18
    //   311: aload 15
    //   313: iload 12
    //   315: invokeinterface 448 2 0
    //   320: putfield 173	com/android/launcher3/d/c$a:n	I
    //   323: aload 18
    //   325: lload_1
    //   326: putfield 336	com/android/launcher3/d/c$a:j	J
    //   329: aload 18
    //   331: getfield 450	com/android/launcher3/d/c$a:h	I
    //   334: istore 8
    //   336: iload 8
    //   338: iconst_4
    //   339: if_icmpeq +122 -> 461
    //   342: iload 8
    //   344: tableswitch	default:+28->372, 0:+81->425, 1:+81->425, 2:+39->383
    //   372: new 246	java/lang/Exception
    //   375: dup
    //   376: ldc_w 452
    //   379: invokespecial 360	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   382: athrow
    //   383: aload_0
    //   384: aload 18
    //   386: getfield 222	com/android/launcher3/d/c$a:g	J
    //   389: invokespecial 454	com/android/launcher3/d/c:c	(J)I
    //   392: istore 8
    //   394: iload 8
    //   396: ifne +14 -> 410
    //   399: new 246	java/lang/Exception
    //   402: dup
    //   403: ldc_w 456
    //   406: invokespecial 360	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   409: athrow
    //   410: aload 18
    //   412: ldc_w 457
    //   415: iload 8
    //   417: i2f
    //   418: fmul
    //   419: putfield 458	com/android/launcher3/d/c$a:a	F
    //   422: goto +386 -> 808
    //   425: aload_0
    //   426: aload 15
    //   428: iload 13
    //   430: invokeinterface 403 2 0
    //   435: invokespecial 405	com/android/launcher3/d/c:a	(Ljava/lang/String;)V
    //   438: aload 18
    //   440: getfield 450	com/android/launcher3/d/c$a:h	I
    //   443: iconst_1
    //   444: if_icmpne +367 -> 811
    //   447: fconst_1
    //   448: fstore_3
    //   449: goto +3 -> 452
    //   452: aload 18
    //   454: fload_3
    //   455: putfield 458	com/android/launcher3/d/c$a:a	F
    //   458: goto +350 -> 808
    //   461: aload 15
    //   463: iload 5
    //   465: invokeinterface 403 2 0
    //   470: astore 16
    //   472: aload 16
    //   474: invokestatic 462	android/content/ComponentName:unflattenFromString	(Ljava/lang/String;)Landroid/content/ComponentName;
    //   477: astore 19
    //   479: aload_0
    //   480: aload 19
    //   482: invokevirtual 262	android/content/ComponentName:getPackageName	()Ljava/lang/String;
    //   485: invokespecial 264	com/android/launcher3/d/c:b	(Ljava/lang/String;)V
    //   488: aload 18
    //   490: getfield 170	com/android/launcher3/d/c$a:m	I
    //   493: i2f
    //   494: fstore_3
    //   495: aload 18
    //   497: getfield 173	com/android/launcher3/d/c$a:n	I
    //   500: istore 8
    //   502: iload 8
    //   504: i2f
    //   505: fstore 4
    //   507: aload 18
    //   509: fconst_2
    //   510: fload 4
    //   512: ldc_w 463
    //   515: fload_3
    //   516: fmul
    //   517: fmul
    //   518: invokestatic 469	java/lang/Math:max	(FF)F
    //   521: putfield 458	com/android/launcher3/d/c$a:a	F
    //   524: aload_0
    //   525: getfield 51	com/android/launcher3/d/c:e	Landroid/content/Context;
    //   528: aload 19
    //   530: invokestatic 474	com/android/launcher3/b/o:a	()Lcom/android/launcher3/b/o;
    //   533: invokestatic 479	com/android/launcher3/ar:a	(Landroid/content/Context;Landroid/content/ComponentName;Lcom/android/launcher3/b/o;)Lcom/android/launcher3/ao;
    //   536: astore 19
    //   538: aload 19
    //   540: ifnonnull +25 -> 565
    //   543: aload_0
    //   544: getfield 84	com/android/launcher3/d/c:g	Ljava/util/HashMap;
    //   547: aload 16
    //   549: invokevirtual 482	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   552: checkcast 69	android/graphics/Point
    //   555: astore 16
    //   557: goto +23 -> 580
    //   560: astore 16
    //   562: goto +243 -> 805
    //   565: aload 19
    //   567: aload_0
    //   568: getfield 134	com/android/launcher3/d/c:h	Lcom/android/launcher3/ag;
    //   571: aload_0
    //   572: getfield 51	com/android/launcher3/d/c:e	Landroid/content/Context;
    //   575: invokevirtual 487	com/android/launcher3/ao:a	(Lcom/android/launcher3/ag;Landroid/content/Context;)Landroid/graphics/Point;
    //   578: astore 16
    //   580: aload 16
    //   582: ifnull +70 -> 652
    //   585: aload 16
    //   587: getfield 72	android/graphics/Point:x	I
    //   590: ifle +13 -> 603
    //   593: aload 16
    //   595: getfield 72	android/graphics/Point:x	I
    //   598: istore 8
    //   600: goto +10 -> 610
    //   603: aload 18
    //   605: getfield 170	com/android/launcher3/d/c$a:m	I
    //   608: istore 8
    //   610: aload 18
    //   612: iload 8
    //   614: putfield 489	com/android/launcher3/d/c$a:o	I
    //   617: aload 16
    //   619: getfield 77	android/graphics/Point:y	I
    //   622: ifle +13 -> 635
    //   625: aload 16
    //   627: getfield 77	android/graphics/Point:y	I
    //   630: istore 8
    //   632: goto +10 -> 642
    //   635: aload 18
    //   637: getfield 173	com/android/launcher3/d/c$a:n	I
    //   640: istore 8
    //   642: aload 18
    //   644: iload 8
    //   646: putfield 492	com/android/launcher3/d/c$a:p	I
    //   649: goto +15 -> 664
    //   652: aload 18
    //   654: iconst_2
    //   655: putfield 492	com/android/launcher3/d/c$a:p	I
    //   658: aload 18
    //   660: iconst_2
    //   661: putfield 489	com/android/launcher3/d/c$a:o	I
    //   664: aload 18
    //   666: getfield 489	com/android/launcher3/d/c$a:o	I
    //   669: aload_0
    //   670: getfield 140	com/android/launcher3/d/c:c	I
    //   673: if_icmpgt +37 -> 710
    //   676: aload 18
    //   678: getfield 492	com/android/launcher3/d/c$a:p	I
    //   681: istore 8
    //   683: aload_0
    //   684: getfield 143	com/android/launcher3/d/c:d	I
    //   687: istore 14
    //   689: iload 8
    //   691: iload 14
    //   693: if_icmple +6 -> 699
    //   696: goto +14 -> 710
    //   699: aload 17
    //   701: aload 18
    //   703: invokevirtual 177	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   706: pop
    //   707: goto +90 -> 797
    //   710: new 246	java/lang/Exception
    //   713: dup
    //   714: ldc_w 494
    //   717: invokespecial 360	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   720: athrow
    //   721: astore 16
    //   723: goto +15 -> 738
    //   726: astore 16
    //   728: goto +10 -> 738
    //   731: astore 16
    //   733: goto +5 -> 738
    //   736: astore 16
    //   738: new 372	java/lang/StringBuilder
    //   741: dup
    //   742: invokespecial 373	java/lang/StringBuilder:<init>	()V
    //   745: astore 19
    //   747: aload 19
    //   749: ldc_w 496
    //   752: invokevirtual 379	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   755: pop
    //   756: aload 19
    //   758: aload 18
    //   760: getfield 222	com/android/launcher3/d/c$a:g	J
    //   763: invokevirtual 382	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   766: pop
    //   767: ldc_w 286
    //   770: aload 19
    //   772: invokevirtual 385	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   775: aload 16
    //   777: invokestatic 499	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   780: pop
    //   781: aload_0
    //   782: getfield 407	com/android/launcher3/d/c:b	Ljava/util/ArrayList;
    //   785: aload 18
    //   787: getfield 222	com/android/launcher3/d/c$a:g	J
    //   790: invokestatic 299	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   793: invokevirtual 177	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   796: pop
    //   797: goto -577 -> 220
    //   800: aload 17
    //   802: areturn
    //   803: astore 16
    //   805: goto -67 -> 738
    //   808: goto -109 -> 699
    //   811: ldc_w 500
    //   814: fstore_3
    //   815: goto -363 -> 452
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	818	0	this	c
    //   0	818	1	paramLong	long
    //   448	367	3	f1	float
    //   505	6	4	f2	float
    //   209	255	5	i1	int
    //   137	121	6	i2	int
    //   125	119	7	i3	int
    //   334	360	8	i4	int
    //   149	123	9	i5	int
    //   161	125	10	i6	int
    //   173	127	11	i7	int
    //   185	129	12	i8	int
    //   197	232	13	i9	int
    //   687	7	14	i10	int
    //   7	455	15	localObject1	Object
    //   12	544	16	localObject2	Object
    //   560	1	16	localException1	Exception
    //   578	48	16	localPoint	Point
    //   721	1	16	localException2	Exception
    //   726	1	16	localException3	Exception
    //   731	1	16	localException4	Exception
    //   736	40	16	localException5	Exception
    //   803	1	16	localException6	Exception
    //   21	780	17	localObject3	Object
    //   237	549	18	localA	a
    //   477	294	19	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   543	557	560	java/lang/Exception
    //   585	600	560	java/lang/Exception
    //   603	610	560	java/lang/Exception
    //   610	632	560	java/lang/Exception
    //   635	642	560	java/lang/Exception
    //   642	649	560	java/lang/Exception
    //   652	664	721	java/lang/Exception
    //   664	689	721	java/lang/Exception
    //   710	721	721	java/lang/Exception
    //   507	538	726	java/lang/Exception
    //   565	580	726	java/lang/Exception
    //   461	502	731	java/lang/Exception
    //   329	336	736	java/lang/Exception
    //   372	383	803	java/lang/Exception
    //   383	394	803	java/lang/Exception
    //   399	410	803	java/lang/Exception
    //   410	422	803	java/lang/Exception
    //   425	447	803	java/lang/Exception
    //   452	458	803	java/lang/Exception
  }
  
  public void a()
    throws Exception
  {
    this.b = new ArrayList();
    this.k = new ArrayList();
    this.j = new ArrayList();
    this.i = new HashSet();
    Object localObject1 = this.e.getPackageManager().getInstalledPackages(0).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (PackageInfo)((Iterator)localObject1).next();
      this.i.add(((PackageInfo)localObject2).packageName);
    }
    this.i.addAll(l.a(this.e).a().keySet());
    localObject1 = ar.a(this.e);
    if (((ArrayList)localObject1).isEmpty()) {
      throw new Exception("Unable to get workspace screens");
    }
    Object localObject2 = ((ArrayList)localObject1).iterator();
    long l1;
    Object localObject3;
    while (((Iterator)localObject2).hasNext())
    {
      l1 = ((Long)((Iterator)localObject2).next()).longValue();
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("Migrating ");
      ((StringBuilder)localObject3).append(l1);
      Log.d("MigrateFromRestoreTask", ((StringBuilder)localObject3).toString());
      b(l1);
    }
    if (!this.k.isEmpty())
    {
      localObject2 = new f();
      localObject3 = this.k.iterator();
      a localA;
      while (((Iterator)localObject3).hasNext())
      {
        localA = (a)((Iterator)localObject3).next();
        ((f)localObject2).put(localA.g, localA);
      }
      do
      {
        localObject3 = new b((boolean[][])Array.newInstance(Boolean.TYPE, new int[] { this.c, this.d }), a(this.k), true);
        ((b)localObject3).a();
        if (((b)localObject3).c.size() <= 0) {
          break;
        }
        l1 = ak.h().c();
        ((ArrayList)localObject1).add(Long.valueOf(l1));
        localObject3 = ((b)localObject3).c.iterator();
        while (((Iterator)localObject3).hasNext())
        {
          localA = (a)((Iterator)localObject3).next();
          if (!this.k.remove(((f)localObject2).get(localA.g))) {
            throw new Exception("Unable to find matching items");
          }
          localA.j = l1;
          a(localA);
        }
      } while (!this.k.isEmpty());
      ak.a().g();
      ar.a(this.e, (List)localObject1);
      break label485;
      throw new Exception("None of the items can be placed on an empty screen");
    }
    label485:
    this.e.getContentResolver().applyBatch(LauncherProvider.a, this.j);
    if (!this.b.isEmpty())
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("Removing items: ");
      ((StringBuilder)localObject1).append(TextUtils.join(", ", this.b));
      Log.d("MigrateFromRestoreTask", ((StringBuilder)localObject1).toString());
      this.e.getContentResolver().delete(au.c.a, bj.a("_id", this.b), null);
    }
    localObject1 = this.e.getContentResolver().query(au.c.a, null, null, null, null);
    boolean bool = ((Cursor)localObject1).moveToNext();
    ((Cursor)localObject1).close();
    if (!bool) {
      throw new Exception("Removed every thing during grid resize");
    }
  }
  
  void a(boolean[][] paramArrayOfBoolean, a paramA, boolean paramBoolean)
  {
    int i1 = paramA.k;
    while (i1 < paramA.k + paramA.m)
    {
      int i2 = paramA.l;
      while (i2 < paramA.l + paramA.n)
      {
        paramArrayOfBoolean[i1][i2] = paramBoolean;
        i2 += 1;
      }
      i1 += 1;
    }
  }
  
  boolean a(boolean[][] paramArrayOfBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramInt1 + paramInt3 > this.c) {
      return false;
    }
    if (paramInt2 + paramInt4 > this.d) {
      return false;
    }
    int i1 = 0;
    while (i1 < paramInt3)
    {
      int i2 = 0;
      while (i2 < paramInt4)
      {
        if (paramArrayOfBoolean[(i1 + paramInt1)][(i2 + paramInt2)] != 0) {
          return false;
        }
        i2 += 1;
      }
      i1 += 1;
    }
    return true;
  }
  
  private static class a
    extends ai
    implements Comparable<a>
  {
    public float a;
    
    public a() {}
    
    public int a(a paramA)
    {
      if (this.h == 4)
      {
        if (paramA.h == 4) {
          return paramA.n * paramA.m - this.m * this.n;
        }
        return -1;
      }
      if (paramA.h == 4) {
        return 1;
      }
      return Float.compare(paramA.a, this.a);
    }
    
    public void a(ContentValues paramContentValues)
    {
      paramContentValues.put("screen", Long.valueOf(this.j));
      paramContentValues.put("cellX", Integer.valueOf(this.k));
      paramContentValues.put("cellY", Integer.valueOf(this.l));
      paramContentValues.put("spanX", Integer.valueOf(this.m));
      paramContentValues.put("spanY", Integer.valueOf(this.n));
    }
    
    public a b()
    {
      a localA = new a();
      localA.a(this);
      localA.a = this.a;
      localA.o = this.o;
      localA.p = this.p;
      return localA;
    }
    
    public boolean b(a paramA)
    {
      return (paramA.k == this.k) && (paramA.l == this.l) && (paramA.m == this.m) && (paramA.n == this.n) && (paramA.j == this.j);
    }
  }
  
  private class b
  {
    float a = Float.MAX_VALUE;
    float b = Float.MAX_VALUE;
    ArrayList<c.a> c;
    private final ArrayList<c.a> e;
    private final boolean[][] f;
    private final boolean g;
    
    public b(ArrayList<c.a> paramArrayList)
    {
      this(paramArrayList, localArrayList, false);
    }
    
    public b(ArrayList<c.a> paramArrayList, boolean paramBoolean)
    {
      this.f = paramArrayList;
      this.e = paramBoolean;
      boolean bool;
      this.g = bool;
      Collections.sort(this.e);
    }
    
    public void a()
    {
      a(0, 0.0F, 0.0F, new ArrayList());
    }
    
    public void a(int paramInt, float paramFloat1, float paramFloat2, ArrayList<c.a> paramArrayList)
    {
      float f3 = paramFloat1;
      paramFloat1 = paramFloat2;
      if (f3 < this.a)
      {
        if ((f3 == this.a) && (paramFloat1 >= this.b)) {
          return;
        }
        if (paramInt >= this.e.size())
        {
          this.a = f3;
          this.b = paramFloat1;
          this.c = c.a(paramArrayList);
          return;
        }
        c.a localA = (c.a)this.e.get(paramInt);
        int i2 = localA.k;
        int i3 = localA.l;
        ArrayList localArrayList = new ArrayList(paramArrayList.size() + 1);
        localArrayList.addAll(paramArrayList);
        localArrayList.add(localA);
        int k;
        int i;
        int j;
        int m;
        float f1;
        if ((localA.m <= 1) && (localA.n <= 1))
        {
          k = Integer.MAX_VALUE;
          i = k;
          int i1 = i;
          j = 0;
          int n = i;
          i = j;
          while (i < c.this.d)
          {
            j = 0;
            while (j < c.this.c)
            {
              if (this.f[j][i] == 0)
              {
                if (this.g) {
                  m = 0;
                } else {
                  m = (localA.k - j) * (localA.k - j) + (localA.l - i) * (localA.l - i);
                }
                if (m < i1)
                {
                  n = i;
                  k = j;
                  i1 = m;
                }
              }
              j += 1;
            }
            i += 1;
          }
          if ((k < c.this.c) && (n < c.this.d))
          {
            if (k != i2)
            {
              localA.k = k;
              paramFloat2 = paramFloat1 + 1.0F;
            }
            else
            {
              paramFloat2 = paramFloat1;
            }
            f1 = paramFloat2;
            if (n != i3)
            {
              localA.l = n;
              f1 = paramFloat2 + 1.0F;
            }
            paramFloat2 = f1;
            if (this.g) {
              paramFloat2 = paramFloat1;
            }
            c.this.a(this.f, localA, true);
            paramInt += 1;
            a(paramInt, f3, paramFloat2, localArrayList);
            c.this.a(this.f, localA, false);
            localA.k = i2;
            localA.l = i3;
            if ((paramInt < this.e.size()) && (((c.a)this.e.get(paramInt)).a >= localA.a) && (!this.g)) {
              a(paramInt, f3 + localA.a, paramFloat1, paramArrayList);
            }
          }
          else
          {
            paramInt += 1;
            while (paramInt < this.e.size())
            {
              f3 += ((c.a)this.e.get(paramInt)).a;
              paramInt += 1;
            }
            a(this.e.size(), f3 + localA.a, paramFloat1, paramArrayList);
          }
        }
        else
        {
          m = localA.m;
          i = localA.n;
          j = 0;
          while (j < c.this.d)
          {
            k = 0;
            for (;;)
            {
              paramFloat1 = paramFloat2;
              if (k >= c.this.c) {
                break;
              }
              if (k != i2)
              {
                localA.k = k;
                f1 = paramFloat1 + 1.0F;
              }
              else
              {
                f1 = paramFloat1;
              }
              float f2 = f1;
              if (j != i3)
              {
                localA.l = j;
                f2 = f1 + 1.0F;
              }
              if (!this.g) {
                paramFloat1 = f2;
              }
              if (c.this.a(this.f, k, j, m, i))
              {
                c.this.a(this.f, localA, true);
                a(paramInt + 1, f3, paramFloat1, localArrayList);
                c.this.a(this.f, localA, false);
              }
              if ((m > localA.o) && (c.this.a(this.f, k, j, m - 1, i)))
              {
                localA.m -= 1;
                c.this.a(this.f, localA, true);
                a(paramInt + 1, f3, paramFloat1 + 1.0F, localArrayList);
                c.this.a(this.f, localA, false);
                localA.m += 1;
              }
              if ((i > localA.p) && (c.this.a(this.f, k, j, m, i - 1)))
              {
                localA.n -= 1;
                c.this.a(this.f, localA, true);
                a(paramInt + 1, f3, paramFloat1 + 1.0F, localArrayList);
                c.this.a(this.f, localA, false);
                localA.n += 1;
              }
              if ((i > localA.p) && (m > localA.o) && (c.this.a(this.f, k, j, m - 1, i - 1)))
              {
                localA.m -= 1;
                localA.n -= 1;
                c.this.a(this.f, localA, true);
                a(paramInt + 1, f3, paramFloat1 + 2.0F, localArrayList);
                c.this.a(this.f, localA, false);
                localA.m += 1;
                localA.n += 1;
              }
              localA.k = i2;
              localA.l = i3;
              k += 1;
            }
            j += 1;
          }
          a(paramInt + 1, f3 + localA.a, paramFloat2, paramArrayList);
        }
        return;
      }
    }
  }
}
