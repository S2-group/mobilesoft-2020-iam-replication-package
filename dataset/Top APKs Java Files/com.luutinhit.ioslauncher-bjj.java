import android.content.ComponentName;
import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Point;
import android.net.Uri;
import android.text.TextUtils;
import com.luutinhit.launcher3.LauncherProvider;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;

public final class bjj
{
  public static boolean a = bhr.d;
  private final Context b;
  private final bgk c;
  private final HashMap<String, Point> d = new HashMap();
  private final ContentValues e = new ContentValues();
  private final ArrayList<Long> f = new ArrayList();
  private final ArrayList<ContentProviderOperation> g = new ArrayList();
  private final ArrayList<bjj.a> h = new ArrayList();
  private final HashSet<String> i;
  private final int j;
  private final int k;
  private final int l;
  private final int m;
  private final boolean n;
  private final boolean o;
  private final int p;
  private final int q;
  private final int r;
  private final int s;
  
  private bjj(Context paramContext, bgk paramBgk, HashSet<String> paramHashSet, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.b = paramContext;
    this.c = paramBgk;
    this.i = paramHashSet;
    this.p = paramInt1;
    this.q = paramInt2;
    this.r = paramInt3;
    this.s = paramInt4;
    this.m = -1;
    this.l = -1;
    this.k = -1;
    this.j = -1;
    this.o = false;
    this.n = false;
  }
  
  private bjj(Context paramContext, bgk paramBgk, HashSet<String> paramHashSet, HashMap<String, Point> paramHashMap, Point paramPoint1, Point paramPoint2)
  {
    this.b = paramContext;
    this.i = paramHashSet;
    this.d.putAll(paramHashMap);
    this.c = paramBgk;
    this.j = paramPoint1.x;
    this.k = paramPoint1.y;
    this.l = paramPoint2.x;
    this.m = paramPoint2.y;
    int i1 = this.l;
    int i2 = this.j;
    boolean bool2 = false;
    if (i1 < i2) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    this.n = bool1;
    boolean bool1 = bool2;
    if (this.m < this.k) {
      bool1 = true;
    }
    this.o = bool1;
    this.s = -1;
    this.r = -1;
    this.q = -1;
    this.p = -1;
  }
  
  private static String a(int paramInt1, int paramInt2)
  {
    return String.format(Locale.ENGLISH, "%d,%d", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
  }
  
  private void a(long paramLong)
  {
    Object localObject3 = b(paramLong);
    Object localObject4 = new float[2];
    Object localObject1 = null;
    int i1 = 0;
    int i2 = Integer.MAX_VALUE;
    int i3 = Integer.MAX_VALUE;
    float f1 = Float.MAX_VALUE;
    float f2 = Float.MAX_VALUE;
    int i4;
    int i5;
    Object localObject2;
    float f3;
    Object localObject5;
    for (;;)
    {
      i4 = i2;
      i5 = i3;
      localObject2 = localObject1;
      f3 = f1;
      if (i1 >= this.j) {
        break;
      }
      f3 = f1;
      i5 = 0;
      f1 = f2;
      f2 = f3;
      i4 = i3;
      i3 = i2;
      i2 = i5;
      while (i2 < this.k)
      {
        Object localObject6 = b((ArrayList)localObject3);
        localObject5 = (boolean[][])Array.newInstance(Boolean.TYPE, new int[] { this.l, this.m });
        if (this.n) {
          i5 = i1;
        } else {
          i5 = Integer.MAX_VALUE;
        }
        int i6;
        if (this.o) {
          i6 = i2;
        } else {
          i6 = Integer.MAX_VALUE;
        }
        localObject2 = new ArrayList();
        ArrayList localArrayList = new ArrayList();
        localObject6 = ((ArrayList)localObject6).iterator();
        if (((Iterator)localObject6).hasNext())
        {
          bjj.a localA = (bjj.a)((Iterator)localObject6).next();
          if (localA.k <= i5) {
            if (localA.m + localA.k > i5) {
              break label264;
            }
          }
          if ((localA.l <= i6) && (localA.n + localA.l > i6))
          {
            label264:
            localArrayList.add(localA);
            if (localA.k >= i5) {
              localA.k -= 1;
            }
            if (localA.l >= i6) {
              localA.l -= 1;
            }
          }
          for (;;)
          {
            break;
            if (localA.k > i5) {
              localA.k -= 1;
            }
            if (localA.l > i6) {
              localA.l -= 1;
            }
            ((ArrayList)localObject2).add(localA);
            b((boolean[][])localObject5, localA, true);
          }
        }
        localObject5 = new bjj.b((boolean[][])localObject5, localArrayList);
        ((bjj.b)localObject5).a();
        ((ArrayList)localObject2).addAll(((bjj.b)localObject5).c);
        localObject4[0] = ((bjj.b)localObject5).a;
        localObject4[1] = ((bjj.b)localObject5).b;
        if ((localObject4[0] >= f2) && ((localObject4[0] != f2) || (localObject4[1] >= f1))) {}
        for (;;)
        {
          break;
          f2 = localObject4[0];
          f1 = localObject4[1];
          if (this.n) {
            i3 = i1;
          }
          if (this.o) {
            i4 = i2;
          }
          localObject1 = localObject2;
        }
        if (this.o)
        {
          i2 += 1;
        }
        else
        {
          i2 = i3;
          i3 = i4;
          f3 = f1;
          f1 = f2;
          f2 = f3;
          break label566;
        }
      }
      f3 = f2;
      i2 = i3;
      i3 = i4;
      f2 = f1;
      f1 = f3;
      label566:
      i4 = i2;
      i5 = i3;
      localObject2 = localObject1;
      f3 = f1;
      if (!this.n) {
        break;
      }
      i1 += 1;
    }
    String.format("Removing row %d, column %d on screen %d", new Object[] { Integer.valueOf(i5), Integer.valueOf(i4), Long.valueOf(paramLong) });
    localObject1 = new bjw();
    localObject3 = b((ArrayList)localObject3).iterator();
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = (bjj.a)((Iterator)localObject3).next();
      ((bjw)localObject1).put(((bjj.a)localObject4).g, localObject4);
    }
    localObject3 = ((ArrayList)localObject2).iterator();
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = (bjj.a)((Iterator)localObject3).next();
      localObject5 = (bjj.a)((bjw)localObject1).get(((bjj.a)localObject4).g);
      ((bjw)localObject1).remove(((bjj.a)localObject4).g);
      if ((((bjj.a)localObject5).k == ((bjj.a)localObject4).k) && (((bjj.a)localObject5).l == ((bjj.a)localObject4).l) && (((bjj.a)localObject5).m == ((bjj.a)localObject4).m) && (((bjj.a)localObject5).n == ((bjj.a)localObject4).n) && (((bjj.a)localObject5).j == ((bjj.a)localObject4).j)) {
        i1 = 1;
      } else {
        i1 = 0;
      }
      if (i1 == 0) {
        a((bjj.a)localObject4);
      }
    }
    localObject1 = ((bjw)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject3 = (bjj.a)((Iterator)localObject1).next();
      this.h.add(localObject3);
    }
    if ((!this.h.isEmpty()) && (f3 == 0.0F))
    {
      localObject1 = (boolean[][])Array.newInstance(Boolean.TYPE, new int[] { this.l, this.m });
      localObject2 = ((ArrayList)localObject2).iterator();
      while (((Iterator)localObject2).hasNext()) {
        b((boolean[][])localObject1, (bjj.a)((Iterator)localObject2).next(), true);
      }
      localObject1 = new bjj.b((boolean[][])localObject1, b(this.h), true);
      ((bjj.b)localObject1).a();
      if (((bjj.b)localObject1).a == 0.0F)
      {
        localObject1 = ((bjj.b)localObject1).c.iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (bjj.a)((Iterator)localObject1).next();
          ((bjj.a)localObject2).j = paramLong;
          a((bjj.a)localObject2);
        }
        this.h.clear();
      }
    }
  }
  
  public static void a(Context paramContext, HashSet<String> paramHashSet, bil.b paramB)
  {
    bhr.b(paramContext).edit().putString("migration_src_workspace_size", a((int)paramB.c, (int)paramB.b)).putString("migration_src_hotseat_size", a((int)paramB.d, paramB.e)).putStringSet("migration_widget_min_size", paramHashSet).apply();
  }
  
  private void a(bjj.a paramA)
  {
    this.e.clear();
    paramA.a(this.e);
    this.g.add(ContentProviderOperation.newUpdate(bgw.d.a(paramA.g)).withValues(this.e).build());
  }
  
  private void a(String paramString)
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
  
  private boolean a()
  {
    if (!this.g.isEmpty()) {
      this.b.getContentResolver().applyBatch(LauncherProvider.a, this.g);
    }
    if (!this.f.isEmpty())
    {
      new StringBuilder("Removing items: ").append(TextUtils.join(", ", this.f));
      this.b.getContentResolver().delete(bgw.d.a, bhr.a("_id", this.f), null);
    }
    return (!this.g.isEmpty()) || (!this.f.isEmpty());
  }
  
  /* Error */
  public static boolean a(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 247	bhr:b	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   4: astore 7
    //   6: invokestatic 386	bgp:a	()Lbgp;
    //   9: getfield 388	bgp:g	Lbgk;
    //   12: astore 11
    //   14: aload 11
    //   16: getfield 392	bgk:d	I
    //   19: aload 11
    //   21: getfield 394	bgk:c	I
    //   24: invokestatic 262	bjj:a	(II)Ljava/lang/String;
    //   27: astore 8
    //   29: aload 11
    //   31: getfield 395	bgk:k	I
    //   34: aload 11
    //   36: getfield 396	bgk:l	I
    //   39: invokestatic 262	bjj:a	(II)Ljava/lang/String;
    //   42: astore 9
    //   44: aload 8
    //   46: aload 7
    //   48: ldc -1
    //   50: ldc_w 398
    //   53: invokeinterface 402 3 0
    //   58: invokevirtual 405	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   61: ifeq +26 -> 87
    //   64: aload 9
    //   66: aload 7
    //   68: ldc_w 270
    //   71: ldc_w 398
    //   74: invokeinterface 402 3 0
    //   79: invokevirtual 405	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   82: ifeq +5 -> 87
    //   85: iconst_1
    //   86: ireturn
    //   87: invokestatic 411	java/lang/System:currentTimeMillis	()J
    //   90: lstore_3
    //   91: new 413	java/util/HashSet
    //   94: dup
    //   95: invokespecial 414	java/util/HashSet:<init>	()V
    //   98: astore 10
    //   100: aload_0
    //   101: invokevirtual 418	android/content/Context:getPackageManager	()Landroid/content/pm/PackageManager;
    //   104: iconst_0
    //   105: invokevirtual 424	android/content/pm/PackageManager:getInstalledPackages	(I)Ljava/util/List;
    //   108: invokeinterface 427 1 0
    //   113: astore 12
    //   115: aload 12
    //   117: invokeinterface 169 1 0
    //   122: ifeq +25 -> 147
    //   125: aload 10
    //   127: aload 12
    //   129: invokeinterface 173 1 0
    //   134: checkcast 429	android/content/pm/PackageInfo
    //   137: getfield 432	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   140: invokevirtual 433	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   143: pop
    //   144: goto -29 -> 115
    //   147: aload 10
    //   149: aload_0
    //   150: invokestatic 438	biw:a	(Landroid/content/Context;)Lbiw;
    //   153: invokevirtual 441	biw:a	()Ljava/util/HashMap;
    //   156: invokevirtual 445	java/util/HashMap:keySet	()Ljava/util/Set;
    //   159: invokevirtual 446	java/util/HashSet:addAll	(Ljava/util/Collection;)Z
    //   162: pop
    //   163: aload 7
    //   165: ldc_w 270
    //   168: aload 9
    //   170: invokeinterface 402 3 0
    //   175: invokestatic 449	bjj:c	(Ljava/lang/String;)Landroid/graphics/Point;
    //   178: astore 12
    //   180: aload 12
    //   182: getfield 110	android/graphics/Point:x	I
    //   185: aload 11
    //   187: getfield 395	bgk:k	I
    //   190: if_icmpne +19 -> 209
    //   193: aload 12
    //   195: getfield 113	android/graphics/Point:y	I
    //   198: aload 11
    //   200: getfield 396	bgk:l	I
    //   203: if_icmpeq +679 -> 882
    //   206: goto +3 -> 209
    //   209: new 2	bjj
    //   212: dup
    //   213: aload_0
    //   214: invokestatic 386	bgp:a	()Lbgp;
    //   217: getfield 388	bgp:g	Lbgk;
    //   220: aload 10
    //   222: aload 12
    //   224: getfield 110	android/graphics/Point:x	I
    //   227: aload 12
    //   229: getfield 113	android/graphics/Point:y	I
    //   232: aload 11
    //   234: getfield 395	bgk:k	I
    //   237: aload 11
    //   239: getfield 396	bgk:l	I
    //   242: invokespecial 451	bjj:<init>	(Landroid/content/Context;Lbgk;Ljava/util/HashSet;IIII)V
    //   245: invokespecial 453	bjj:b	()Z
    //   248: istore 5
    //   250: new 107	android/graphics/Point
    //   253: dup
    //   254: aload 11
    //   256: getfield 392	bgk:d	I
    //   259: aload 11
    //   261: getfield 394	bgk:c	I
    //   264: invokespecial 456	android/graphics/Point:<init>	(II)V
    //   267: astore 12
    //   269: aload 7
    //   271: ldc -1
    //   273: aload 8
    //   275: invokeinterface 402 3 0
    //   280: invokestatic 449	bjj:c	(Ljava/lang/String;)Landroid/graphics/Point;
    //   283: astore 13
    //   285: aload 12
    //   287: aload 13
    //   289: invokevirtual 457	android/graphics/Point:equals	(Ljava/lang/Object;)Z
    //   292: ifne +606 -> 898
    //   295: new 65	java/util/ArrayList
    //   298: dup
    //   299: invokespecial 66	java/util/ArrayList:<init>	()V
    //   302: astore 11
    //   304: aload 11
    //   306: new 107	android/graphics/Point
    //   309: dup
    //   310: iconst_3
    //   311: iconst_2
    //   312: invokespecial 456	android/graphics/Point:<init>	(II)V
    //   315: invokevirtual 182	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   318: pop
    //   319: aload 11
    //   321: new 107	android/graphics/Point
    //   324: dup
    //   325: iconst_3
    //   326: iconst_3
    //   327: invokespecial 456	android/graphics/Point:<init>	(II)V
    //   330: invokevirtual 182	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   333: pop
    //   334: aload 11
    //   336: new 107	android/graphics/Point
    //   339: dup
    //   340: iconst_4
    //   341: iconst_3
    //   342: invokespecial 456	android/graphics/Point:<init>	(II)V
    //   345: invokevirtual 182	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   348: pop
    //   349: aload 11
    //   351: new 107	android/graphics/Point
    //   354: dup
    //   355: iconst_4
    //   356: iconst_4
    //   357: invokespecial 456	android/graphics/Point:<init>	(II)V
    //   360: invokevirtual 182	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   363: pop
    //   364: aload 11
    //   366: new 107	android/graphics/Point
    //   369: dup
    //   370: iconst_5
    //   371: iconst_5
    //   372: invokespecial 456	android/graphics/Point:<init>	(II)V
    //   375: invokevirtual 182	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   378: pop
    //   379: aload 11
    //   381: new 107	android/graphics/Point
    //   384: dup
    //   385: bipush 6
    //   387: iconst_5
    //   388: invokespecial 456	android/graphics/Point:<init>	(II)V
    //   391: invokevirtual 182	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   394: pop
    //   395: aload 11
    //   397: new 107	android/graphics/Point
    //   400: dup
    //   401: bipush 6
    //   403: bipush 6
    //   405: invokespecial 456	android/graphics/Point:<init>	(II)V
    //   408: invokevirtual 182	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   411: pop
    //   412: aload 11
    //   414: new 107	android/graphics/Point
    //   417: dup
    //   418: bipush 7
    //   420: bipush 7
    //   422: invokespecial 456	android/graphics/Point:<init>	(II)V
    //   425: invokevirtual 182	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   428: pop
    //   429: aload 11
    //   431: aload 13
    //   433: invokevirtual 461	java/util/ArrayList:indexOf	(Ljava/lang/Object;)I
    //   436: istore_1
    //   437: aload 11
    //   439: aload 12
    //   441: invokevirtual 461	java/util/ArrayList:indexOf	(Ljava/lang/Object;)I
    //   444: istore_2
    //   445: iload_1
    //   446: iflt +153 -> 599
    //   449: iload_2
    //   450: iflt +149 -> 599
    //   453: new 55	java/util/HashMap
    //   456: dup
    //   457: invokespecial 56	java/util/HashMap:<init>	()V
    //   460: astore 12
    //   462: aload_0
    //   463: invokestatic 247	bhr:b	(Landroid/content/Context;)Landroid/content/SharedPreferences;
    //   466: ldc_w 276
    //   469: invokestatic 466	java/util/Collections:emptySet	()Ljava/util/Set;
    //   472: invokeinterface 470 3 0
    //   477: invokeinterface 473 1 0
    //   482: astore 13
    //   484: aload 13
    //   486: invokeinterface 169 1 0
    //   491: ifeq +397 -> 888
    //   494: aload 13
    //   496: invokeinterface 173 1 0
    //   501: checkcast 132	java/lang/String
    //   504: ldc_w 475
    //   507: invokevirtual 479	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   510: astore 14
    //   512: aload 12
    //   514: aload 14
    //   516: iconst_0
    //   517: aaload
    //   518: aload 14
    //   520: iconst_1
    //   521: aaload
    //   522: invokestatic 449	bjj:c	(Ljava/lang/String;)Landroid/graphics/Point;
    //   525: invokevirtual 482	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   528: pop
    //   529: goto -45 -> 484
    //   532: iload 5
    //   534: istore 6
    //   536: iload_2
    //   537: iload_1
    //   538: if_icmpge +106 -> 644
    //   541: aload 11
    //   543: iload_1
    //   544: iconst_1
    //   545: isub
    //   546: invokevirtual 485	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   549: checkcast 107	android/graphics/Point
    //   552: astore 13
    //   554: aload 11
    //   556: iload_1
    //   557: invokevirtual 485	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   560: checkcast 107	android/graphics/Point
    //   563: astore 14
    //   565: new 2	bjj
    //   568: dup
    //   569: aload_0
    //   570: invokestatic 386	bgp:a	()Lbgp;
    //   573: getfield 388	bgp:g	Lbgk;
    //   576: aload 10
    //   578: aload 12
    //   580: aload 14
    //   582: aload 13
    //   584: invokespecial 487	bjj:<init>	(Landroid/content/Context;Lbgk;Ljava/util/HashSet;Ljava/util/HashMap;Landroid/graphics/Point;Landroid/graphics/Point;)V
    //   587: invokespecial 489	bjj:c	()Z
    //   590: ifeq +301 -> 891
    //   593: iconst_1
    //   594: istore 5
    //   596: goto +295 -> 891
    //   599: new 350	java/lang/StringBuilder
    //   602: dup
    //   603: ldc_w 491
    //   606: invokespecial 354	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   609: astore_0
    //   610: aload_0
    //   611: aload 13
    //   613: invokevirtual 494	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   616: pop
    //   617: aload_0
    //   618: ldc_w 496
    //   621: invokevirtual 366	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   624: pop
    //   625: aload_0
    //   626: aload 12
    //   628: invokevirtual 494	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   631: pop
    //   632: new 381	java/lang/Exception
    //   635: dup
    //   636: aload_0
    //   637: invokevirtual 499	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   640: invokespecial 500	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   643: athrow
    //   644: iload 6
    //   646: ifeq +51 -> 697
    //   649: aload_0
    //   650: invokevirtual 337	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   653: getstatic 369	bgw$d:a	Landroid/net/Uri;
    //   656: aconst_null
    //   657: aconst_null
    //   658: aconst_null
    //   659: aconst_null
    //   660: invokevirtual 504	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   663: astore_0
    //   664: aload_0
    //   665: invokeinterface 509 1 0
    //   670: istore 5
    //   672: aload_0
    //   673: invokeinterface 512 1 0
    //   678: iload 5
    //   680: ifeq +6 -> 686
    //   683: goto +14 -> 697
    //   686: new 381	java/lang/Exception
    //   689: dup
    //   690: ldc_w 514
    //   693: invokespecial 500	java/lang/Exception:<init>	(Ljava/lang/String;)V
    //   696: athrow
    //   697: new 350	java/lang/StringBuilder
    //   700: dup
    //   701: ldc_w 516
    //   704: invokespecial 354	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   707: invokestatic 411	java/lang/System:currentTimeMillis	()J
    //   710: lload_3
    //   711: lsub
    //   712: invokevirtual 519	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   715: pop
    //   716: aload 7
    //   718: invokeinterface 253 1 0
    //   723: ldc -1
    //   725: aload 8
    //   727: invokeinterface 268 3 0
    //   732: ldc_w 270
    //   735: aload 9
    //   737: invokeinterface 268 3 0
    //   742: ldc_w 276
    //   745: invokeinterface 522 2 0
    //   750: invokeinterface 283 1 0
    //   755: iconst_1
    //   756: ireturn
    //   757: astore_0
    //   758: new 350	java/lang/StringBuilder
    //   761: dup
    //   762: ldc_w 516
    //   765: invokespecial 354	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   768: invokestatic 411	java/lang/System:currentTimeMillis	()J
    //   771: lload_3
    //   772: lsub
    //   773: invokevirtual 519	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   776: pop
    //   777: aload 7
    //   779: invokeinterface 253 1 0
    //   784: ldc -1
    //   786: aload 8
    //   788: invokeinterface 268 3 0
    //   793: ldc_w 270
    //   796: aload 9
    //   798: invokeinterface 268 3 0
    //   803: ldc_w 276
    //   806: invokeinterface 522 2 0
    //   811: invokeinterface 283 1 0
    //   816: aload_0
    //   817: athrow
    //   818: new 350	java/lang/StringBuilder
    //   821: dup
    //   822: ldc_w 516
    //   825: invokespecial 354	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   828: invokestatic 411	java/lang/System:currentTimeMillis	()J
    //   831: lload_3
    //   832: lsub
    //   833: invokevirtual 519	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   836: pop
    //   837: aload 7
    //   839: invokeinterface 253 1 0
    //   844: ldc -1
    //   846: aload 8
    //   848: invokeinterface 268 3 0
    //   853: ldc_w 270
    //   856: aload 9
    //   858: invokeinterface 268 3 0
    //   863: ldc_w 276
    //   866: invokeinterface 522 2 0
    //   871: invokeinterface 283 1 0
    //   876: iconst_0
    //   877: ireturn
    //   878: astore_0
    //   879: goto -61 -> 818
    //   882: iconst_0
    //   883: istore 5
    //   885: goto -635 -> 250
    //   888: goto -356 -> 532
    //   891: iload_1
    //   892: iconst_1
    //   893: isub
    //   894: istore_1
    //   895: goto -363 -> 532
    //   898: iload 5
    //   900: istore 6
    //   902: goto -258 -> 644
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	905	0	paramContext	Context
    //   436	459	1	i1	int
    //   444	95	2	i2	int
    //   90	742	3	l1	long
    //   248	651	5	bool1	boolean
    //   534	367	6	bool2	boolean
    //   4	834	7	localSharedPreferences	SharedPreferences
    //   27	820	8	str1	String
    //   42	815	9	str2	String
    //   98	479	10	localHashSet	HashSet
    //   12	543	11	localObject1	Object
    //   113	514	12	localObject2	Object
    //   283	329	13	localObject3	Object
    //   510	71	14	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   91	115	757	finally
    //   115	144	757	finally
    //   147	206	757	finally
    //   209	250	757	finally
    //   250	445	757	finally
    //   453	484	757	finally
    //   484	529	757	finally
    //   541	565	757	finally
    //   565	593	757	finally
    //   599	644	757	finally
    //   649	678	757	finally
    //   686	697	757	finally
    //   91	115	878	java/lang/Exception
    //   115	144	878	java/lang/Exception
    //   147	206	878	java/lang/Exception
    //   209	250	878	java/lang/Exception
    //   250	445	878	java/lang/Exception
    //   453	484	878	java/lang/Exception
    //   484	529	878	java/lang/Exception
    //   541	565	878	java/lang/Exception
    //   565	593	878	java/lang/Exception
    //   599	644	878	java/lang/Exception
    //   649	678	878	java/lang/Exception
    //   686	697	878	java/lang/Exception
  }
  
  private ArrayList<bjj.a> b(long paramLong)
  {
    Object localObject1 = this.b.getContentResolver();
    Object localObject2 = bgw.d.a;
    Object localObject3 = "container = -100 AND screen = ".concat(String.valueOf(paramLong));
    localObject2 = ((ContentResolver)localObject1).query((Uri)localObject2, new String[] { "_id", "itemType", "cellX", "cellY", "spanX", "spanY", "intent", "appWidgetProvider", "appWidgetId" }, (String)localObject3, null, null, null);
    int i1 = ((Cursor)localObject2).getColumnIndexOrThrow("_id");
    int i2 = ((Cursor)localObject2).getColumnIndexOrThrow("itemType");
    int i3 = ((Cursor)localObject2).getColumnIndexOrThrow("cellX");
    int i4 = ((Cursor)localObject2).getColumnIndexOrThrow("cellY");
    int i5 = ((Cursor)localObject2).getColumnIndexOrThrow("spanX");
    int i6 = ((Cursor)localObject2).getColumnIndexOrThrow("spanY");
    int i7 = ((Cursor)localObject2).getColumnIndexOrThrow("intent");
    ((Cursor)localObject2).getColumnIndexOrThrow("appWidgetProvider");
    ((Cursor)localObject2).getColumnIndexOrThrow("appWidgetId");
    localObject1 = new ArrayList();
    for (;;)
    {
      if (((Cursor)localObject2).moveToNext())
      {
        localObject3 = new bjj.a();
        ((bjj.a)localObject3).g = ((Cursor)localObject2).getLong(i1);
        ((bjj.a)localObject3).h = ((Cursor)localObject2).getInt(i2);
        ((bjj.a)localObject3).k = ((Cursor)localObject2).getInt(i3);
        ((bjj.a)localObject3).l = ((Cursor)localObject2).getInt(i4);
        ((bjj.a)localObject3).m = ((Cursor)localObject2).getInt(i5);
        ((bjj.a)localObject3).n = ((Cursor)localObject2).getInt(i6);
        ((bjj.a)localObject3).j = paramLong;
      }
      try
      {
        switch (((bjj.a)localObject3).h)
        {
        case 2: 
          break label445;
          int i8 = c(((bjj.a)localObject3).g);
          if (i8 != 0) {
            ((bjj.a)localObject3).a = (i8 * 0.5F);
          } else {
            throw new Exception("Folder is empty");
          }
        case 0: 
        case 1: 
          a(((Cursor)localObject2).getString(i7));
          if (((bjj.a)localObject3).h == 1)
          {
            f1 = 1.0F;
            ((bjj.a)localObject3).a = f1;
            ((ArrayList)localObject1).add(localObject3);
            continue;
            label445:
            throw new Exception("Invalid item type");
            new StringBuilder("Removing item ").append(((bjj.a)localObject3).g);
            this.f.add(Long.valueOf(((bjj.a)localObject3).g));
            continue;
            ((Cursor)localObject2).close();
            return localObject1;
          }
          break;
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          continue;
          continue;
          float f1 = 0.8F;
        }
      }
    }
  }
  
  private static ArrayList<bjj.a> b(ArrayList<bjj.a> paramArrayList)
  {
    ArrayList localArrayList = new ArrayList(paramArrayList.size());
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext()) {
      localArrayList.add(((bjj.a)paramArrayList.next()).d());
    }
    return localArrayList;
  }
  
  private void b(String paramString)
  {
    if (this.i.contains(paramString)) {
      return;
    }
    throw new Exception("Package not available");
  }
  
  private static void b(boolean[][] paramArrayOfBoolean, bjj.a paramA, boolean paramBoolean)
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
  
  private boolean b()
  {
    ArrayList localArrayList = d();
    int i1 = this.r;
    bjj.a localA;
    while (localArrayList.size() > i1 - 1)
    {
      localObject = (bjj.a)localArrayList.get(localArrayList.size() / 2);
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
      {
        localA = (bjj.a)localIterator.next();
        if (localA.a < ((bjj.a)localObject).a) {
          localObject = localA;
        }
      }
      this.f.add(Long.valueOf(((bjj.a)localObject).g));
      localArrayList.remove(localObject);
    }
    Object localObject = localArrayList.iterator();
    i1 = 0;
    while (((Iterator)localObject).hasNext())
    {
      localA = (bjj.a)((Iterator)localObject).next();
      long l1 = localA.j;
      long l2 = i1;
      if (l1 != l2)
      {
        localA.j = l2;
        localA.k = i1;
        localA.l = 0;
        a(localA);
      }
      int i2 = i1 + 1;
      i1 = i2;
      if (i2 == this.s) {
        i1 = i2 + 1;
      }
    }
    return a();
  }
  
  private int c(long paramLong)
  {
    Object localObject = this.b.getContentResolver();
    Uri localUri = bgw.d.a;
    String str = "container = ".concat(String.valueOf(paramLong));
    localObject = ((ContentResolver)localObject).query(localUri, new String[] { "_id", "intent" }, str, null, null, null);
    int i1 = 0;
    while (((Cursor)localObject).moveToNext())
    {
      try
      {
        a(((Cursor)localObject).getString(1));
        i1 += 1;
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
      this.f.add(Long.valueOf(((Cursor)localObject).getLong(0)));
    }
    ((Cursor)localObject).close();
    return i1;
  }
  
  private static Point c(String paramString)
  {
    paramString = paramString.split(",");
    return new Point(Integer.parseInt(paramString[0]), Integer.parseInt(paramString[1]));
  }
  
  private boolean c()
  {
    ArrayList localArrayList = bgt.a(this.b);
    if (!localArrayList.isEmpty())
    {
      Object localObject1 = localArrayList.iterator();
      while (((Iterator)localObject1).hasNext()) {
        a(((Long)((Iterator)localObject1).next()).longValue());
      }
      if (!this.h.isEmpty())
      {
        localObject1 = new bjw();
        Object localObject2 = this.h.iterator();
        bjj.a localA;
        while (((Iterator)localObject2).hasNext())
        {
          localA = (bjj.a)((Iterator)localObject2).next();
          ((bjw)localObject1).put(localA.g, localA);
        }
        do
        {
          localObject2 = new bjj.b((boolean[][])Array.newInstance(Boolean.TYPE, new int[] { this.l, this.m }), b(this.h), true);
          ((bjj.b)localObject2).a();
          if (((bjj.b)localObject2).c.size() <= 0) {
            break;
          }
          long l1 = bgp.c().c();
          localArrayList.add(Long.valueOf(l1));
          localObject2 = ((bjj.b)localObject2).c.iterator();
          while (((Iterator)localObject2).hasNext())
          {
            localA = (bjj.a)((Iterator)localObject2).next();
            if (this.h.remove(((bjw)localObject1).get(localA.g)))
            {
              localA.j = l1;
              a(localA);
            }
            else
            {
              throw new Exception("Unable to find matching items");
            }
          }
        } while (!this.h.isEmpty());
        localObject1 = bgw.f.a;
        this.g.add(ContentProviderOperation.newDelete((Uri)localObject1).build());
        int i2 = localArrayList.size();
        int i1 = 0;
        while (i1 < i2)
        {
          localObject2 = new ContentValues();
          ((ContentValues)localObject2).put("_id", Long.valueOf(((Long)localArrayList.get(i1)).longValue()));
          ((ContentValues)localObject2).put("screenRank", Integer.valueOf(i1));
          this.g.add(ContentProviderOperation.newInsert((Uri)localObject1).withValues((ContentValues)localObject2).build());
          i1 += 1;
        }
        throw new Exception("None of the items can be placed on an empty screen");
      }
      return a();
    }
    throw new Exception("Unable to get workspace screens");
  }
  
  private ArrayList<bjj.a> d()
  {
    Cursor localCursor = this.b.getContentResolver().query(bgw.d.a, new String[] { "_id", "itemType", "intent", "screen" }, "container = -101", null, null, null);
    int i1 = localCursor.getColumnIndexOrThrow("_id");
    int i2 = localCursor.getColumnIndexOrThrow("itemType");
    int i3 = localCursor.getColumnIndexOrThrow("intent");
    int i4 = localCursor.getColumnIndexOrThrow("screen");
    ArrayList localArrayList = new ArrayList();
    for (;;)
    {
      bjj.a localA;
      if (localCursor.moveToNext())
      {
        localA = new bjj.a();
        localA.g = localCursor.getLong(i1);
        localA.h = localCursor.getInt(i2);
        localA.j = localCursor.getLong(i4);
        if (localA.j >= this.p)
        {
          label177:
          this.f.add(Long.valueOf(localA.g));
          continue;
        }
      }
      try
      {
        switch (localA.h)
        {
        case 2: 
          break label317;
          int i5 = c(localA.g);
          if (i5 != 0) {
            localA.a = (i5 * 0.5F);
          } else {
            throw new Exception("Folder is empty");
          }
        case 0: 
        case 1: 
          a(localCursor.getString(i3));
          if (localA.h == 1)
          {
            f1 = 1.0F;
            localA.a = f1;
            localArrayList.add(localA);
            continue;
            label317:
            throw new Exception("Invalid item type");
            new StringBuilder("Removing item ").append(localA.g);
            break label177;
            localCursor.close();
            return localArrayList;
          }
          break;
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          continue;
          continue;
          float f1 = 0.8F;
        }
      }
    }
  }
  
  static final class a
    extends bgm
    implements Comparable<a>
  {
    public float a;
    
    public a() {}
    
    public final void a(ContentValues paramContentValues)
    {
      paramContentValues.put("screen", Long.valueOf(this.j));
      paramContentValues.put("cellX", Integer.valueOf(this.k));
      paramContentValues.put("cellY", Integer.valueOf(this.l));
      paramContentValues.put("spanX", Integer.valueOf(this.m));
      paramContentValues.put("spanY", Integer.valueOf(this.n));
    }
    
    public final a d()
    {
      a localA = new a();
      localA.a(this);
      localA.a = this.a;
      localA.o = this.o;
      localA.p = this.p;
      return localA;
    }
  }
  
  final class b
  {
    float a = Float.MAX_VALUE;
    float b = Float.MAX_VALUE;
    ArrayList<bjj.a> c;
    private final ArrayList<bjj.a> e;
    private final boolean[][] f;
    private final boolean g;
    
    public b(ArrayList<bjj.a> paramArrayList)
    {
      this(paramArrayList, localArrayList, false);
    }
    
    public b(ArrayList<bjj.a> paramArrayList, boolean paramBoolean)
    {
      this.f = paramArrayList;
      this.e = paramBoolean;
      boolean bool;
      this.g = bool;
      Collections.sort(this.e);
    }
    
    private void a(int paramInt, float paramFloat1, float paramFloat2, ArrayList<bjj.a> paramArrayList)
    {
      for (;;)
      {
        float f1 = paramFloat2;
        if (paramFloat1 >= this.a) {
          break;
        }
        if ((paramFloat1 == this.a) && (f1 >= this.b)) {
          return;
        }
        if (paramInt >= this.e.size())
        {
          this.a = paramFloat1;
          this.b = f1;
          this.c = bjj.a(paramArrayList);
          return;
        }
        bjj.a localA = (bjj.a)this.e.get(paramInt);
        int i5 = localA.k;
        int i6 = localA.l;
        ArrayList localArrayList = new ArrayList(paramArrayList.size() + 1);
        localArrayList.addAll(paramArrayList);
        localArrayList.add(localA);
        int i;
        int m;
        int j;
        int k;
        float f2;
        float f3;
        if ((localA.m <= 1) && (localA.n <= 1))
        {
          i = 0;
          int i1 = Integer.MAX_VALUE;
          int n = Integer.MAX_VALUE;
          m = Integer.MAX_VALUE;
          while (i < bjj.a(bjj.this))
          {
            j = 0;
            while (j < bjj.b(bjj.this))
            {
              int i4 = i1;
              int i3 = n;
              int i2 = m;
              if (this.f[j][i] == 0)
              {
                if (this.g) {
                  k = 0;
                } else {
                  k = (localA.k - j) * (localA.k - j) + (localA.l - i) * (localA.l - i);
                }
                i4 = i1;
                i3 = n;
                i2 = m;
                if (k < m)
                {
                  i3 = i;
                  i4 = j;
                  i2 = k;
                }
              }
              j += 1;
              i1 = i4;
              n = i3;
              m = i2;
            }
            i += 1;
          }
          f2 = paramFloat1;
          i = paramInt;
          if (i1 < bjj.b(bjj.this))
          {
            f2 = paramFloat1;
            i = paramInt;
            if (n < bjj.a(bjj.this))
            {
              if (i1 != i5)
              {
                localA.k = i1;
                f2 = f1 + 1.0F;
              }
              else
              {
                f2 = f1;
              }
              f3 = f2;
              if (n != i6)
              {
                localA.l = n;
                f3 = f2 + 1.0F;
              }
              if (this.g) {
                f3 = f1;
              }
              bjj.a(this.f, localA, true);
              paramInt += 1;
              a(paramInt, paramFloat1, f3, localArrayList);
              bjj.a(this.f, localA, false);
              localA.k = i5;
              localA.l = i6;
              if ((paramInt < this.e.size()) && (((bjj.a)this.e.get(paramInt)).a >= localA.a) && (!this.g)) {
                break label565;
              }
              return;
            }
          }
          for (;;)
          {
            i += 1;
            if (i >= this.e.size()) {
              break;
            }
            f2 += ((bjj.a)this.e.get(i)).a;
          }
          paramInt = this.e.size();
          paramFloat1 = f2;
          label565:
          paramFloat1 += localA.a;
        }
        else
        {
          k = localA.m;
          m = localA.n;
          i = 0;
          while (i < bjj.a(bjj.this))
          {
            j = 0;
            for (;;)
            {
              f1 = paramFloat2;
              if (j < bjj.b(bjj.this))
              {
                if (j != i5)
                {
                  localA.k = j;
                  f2 = f1 + 1.0F;
                }
                else
                {
                  f2 = f1;
                }
                f3 = f2;
                if (i != i6)
                {
                  localA.l = i;
                  f3 = f2 + 1.0F;
                }
                if (!this.g) {
                  f1 = f3;
                }
                if (bjj.a(bjj.this, this.f, j, i, k, m))
                {
                  bjj.a(this.f, localA, true);
                  a(paramInt + 1, paramFloat1, f1, localArrayList);
                  bjj.a(this.f, localA, false);
                }
                if ((k > localA.o) && (bjj.a(bjj.this, this.f, j, i, k - 1, m)))
                {
                  localA.m -= 1;
                  bjj.a(this.f, localA, true);
                  a(paramInt + 1, paramFloat1, f1 + 1.0F, localArrayList);
                  bjj.a(this.f, localA, false);
                  localA.m += 1;
                }
                if ((m > localA.p) && (bjj.a(bjj.this, this.f, j, i, k, m - 1)))
                {
                  localA.n -= 1;
                  bjj.a(this.f, localA, true);
                  a(paramInt + 1, paramFloat1, f1 + 1.0F, localArrayList);
                  bjj.a(this.f, localA, false);
                  localA.n += 1;
                }
                if ((m > localA.p) && (k > localA.o) && (bjj.a(bjj.this, this.f, j, i, k - 1, m - 1)))
                {
                  localA.m -= 1;
                  localA.n -= 1;
                  bjj.a(this.f, localA, true);
                }
              }
              try
              {
                a(paramInt + 1, paramFloat1, f1 + 2.0F, localArrayList);
                bjj.a(this.f, localA, false);
                localA.m += 1;
                localA.n += 1;
                localA.k = i5;
                localA.l = i6;
                j += 1;
              }
              catch (Throwable paramArrayList)
              {
                throw paramArrayList;
              }
            }
            i += 1;
          }
          paramInt += 1;
          paramFloat1 += localA.a;
        }
      }
    }
    
    public final void a()
    {
      a(0, 0.0F, 0.0F, new ArrayList());
    }
  }
}
