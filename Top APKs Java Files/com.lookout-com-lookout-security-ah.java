package com.lookout.security;

import android.content.Context;
import android.content.pm.PackageManager;
import com.lookout.aa.a.d;
import com.lookout.aa.at;
import com.lookout.aa.bi;
import com.lookout.aa.bk;
import com.lookout.android.c.i;
import com.lookout.androidsecurity.e.f;
import com.lookout.c.e.w;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import org.json.JSONObject;

public class ah
  extends com.lookout.android.c.a
{
  static ah e;
  private static final List h = Arrays.asList(new org.apache.b.e.e[] { com.lookout.o.a.d, com.lookout.o.a.b, com.lookout.o.a.i, com.lookout.o.a.e, com.lookout.o.a.l, com.lookout.o.a.f, com.lookout.o.a.g, com.lookout.o.a.h, com.lookout.o.a.j });
  private static final List i = Arrays.asList(new org.apache.b.e.e[] { com.lookout.o.a.n, com.lookout.o.a.o, com.lookout.o.a.p });
  private static final org.a.b j = org.a.c.a(ah.class);
  private static long k = TimeUnit.MILLISECONDS.convert(1L, TimeUnit.HOURS);
  e f = null;
  volatile Timer g;
  private final Context l;
  private final com.lookout.c.a m;
  private int n = 0;
  private ReentrantReadWriteLock o = new ReentrantReadWriteLock();
  private volatile boolean p;
  private bk q = null;
  private HashMap r;
  private HashMap s;
  
  protected ah()
  {
    this(com.lookout.androidsecurity.a.a().c(), com.lookout.androidsecurity.a.a().b());
  }
  
  protected ah(com.lookout.c.a paramA, Context paramContext)
  {
    super(com.lookout.security.e.a.c.a);
    this.m = paramA;
    this.l = paramContext;
  }
  
  public static ah a()
  {
    try
    {
      if (e == null) {
        e = new ah();
      }
      ah localAh = e;
      return localAh;
    }
    finally {}
  }
  
  private void a(Context paramContext)
  {
    f localF = com.lookout.androidsecurity.a.a().j();
    if (localF.b())
    {
      if (com.lookout.androidsecurity.h.a.c.i().l() + k < System.currentTimeMillis()) {
        a(paramContext, ae.b);
      }
    }
    else {
      return;
    }
    try
    {
      if (this.g != null) {
        this.g.cancel();
      }
      this.g = new Timer("AV Timer");
      this.g.schedule(new ai(this, localF, paramContext), k);
      return;
    }
    finally {}
  }
  
  public static void a(ah paramAh)
  {
    try
    {
      e = paramAh;
      return;
    }
    finally
    {
      paramAh = finally;
      throw paramAh;
    }
  }
  
  private void a(Throwable paramThrowable)
  {
    try
    {
      File localFile = d();
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("path", localFile.getAbsolutePath());
      localJSONObject.put("exists", Boolean.toString(localFile.exists()));
      localJSONObject.put("size", Long.toString(localFile.length()));
      localJSONObject.put("throwable", paramThrowable.toString());
      j.d("Cannot initialize security policy; " + localJSONObject, paramThrowable);
      return;
    }
    catch (Throwable localThrowable)
    {
      j.d("Cannot initialize security policy.", paramThrowable);
    }
  }
  
  private void b(bk paramBk)
  {
    ArrayList localArrayList = paramBk.h();
    paramBk = paramBk.i().iterator();
    while (paramBk.hasNext())
    {
      org.apache.b.e.e localE = (org.apache.b.e.e)paramBk.next();
      if (!localArrayList.contains(localE)) {
        localArrayList.add(localE);
      }
    }
  }
  
  private boolean k()
  {
    return (this.q != null) && (this.q.h() != null) && (!this.q.h().isEmpty());
  }
  
  protected List a(Context paramContext, boolean paramBoolean)
  {
    List localList = super.a(paramContext, paramBoolean);
    com.lookout.androidsecurity.a localA = com.lookout.androidsecurity.a.a();
    localList.add(new com.lookout.android.d.p(paramContext, localA.i(), localA.h()));
    return localList;
  }
  
  public void a(int paramInt)
  {
    this.n = paramInt;
  }
  
  public void a(Context paramContext, ae paramAe)
  {
    a(c(paramContext), paramAe);
  }
  
  public void a(Context paramContext, e paramE)
  {
    try
    {
      this.f = paramE;
      a(c(paramContext), ae.a);
      return;
    }
    finally {}
  }
  
  protected void a(at paramAt)
  {
    if ((paramAt instanceof com.lookout.android.d.o)) {
      ((com.lookout.android.d.o)paramAt).b();
    }
  }
  
  protected void a(bk paramBk)
  {
    this.q = paramBk;
    ArrayList localArrayList = paramBk.h();
    this.c = new com.lookout.security.filesystem.c(new com.lookout.android.c.g(paramBk.j(), localArrayList), this);
  }
  
  public void a(HashMap paramHashMap1, HashMap paramHashMap2)
  {
    this.r = paramHashMap1;
    this.s = paramHashMap2;
  }
  
  /* Error */
  boolean a(java.io.InputStream paramInputStream)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aconst_null
    //   3: astore 4
    //   5: new 349	com/lookout/security/b
    //   8: dup
    //   9: invokespecial 350	com/lookout/security/b:<init>	()V
    //   12: astore 6
    //   14: new 352	com/lookout/security/a/a
    //   17: dup
    //   18: new 354	com/lookout/security/a/b
    //   21: dup
    //   22: invokespecial 355	com/lookout/security/a/b:<init>	()V
    //   25: invokespecial 358	com/lookout/security/a/a:<init>	(Lcom/lookout/security/a/b;)V
    //   28: astore 5
    //   30: new 360	com/lookout/aa/t
    //   33: dup
    //   34: invokespecial 361	com/lookout/aa/t:<init>	()V
    //   37: astore 7
    //   39: aload 7
    //   41: aload 5
    //   43: invokevirtual 364	com/lookout/aa/t:a	(Lcom/lookout/aa/ak;)Lcom/lookout/aa/t;
    //   46: pop
    //   47: new 366	com/lookout/aa/b/i
    //   50: dup
    //   51: invokespecial 367	com/lookout/aa/b/i:<init>	()V
    //   54: astore 8
    //   56: new 369	java/io/ByteArrayOutputStream
    //   59: dup
    //   60: invokespecial 370	java/io/ByteArrayOutputStream:<init>	()V
    //   63: astore 5
    //   65: aload_0
    //   66: getfield 139	com/lookout/security/ah:l	Landroid/content/Context;
    //   69: invokestatic 375	com/lookout/androidsecurity/b/a:a	(Landroid/content/Context;)Lcom/lookout/security/b/a;
    //   72: astore 9
    //   74: aload 9
    //   76: aload_1
    //   77: aload 5
    //   79: aload 9
    //   81: invokevirtual 380	com/lookout/security/b/a:b	()Ljavax/crypto/SecretKey;
    //   84: aload 9
    //   86: invokevirtual 383	com/lookout/security/b/a:d	()Ljava/security/cert/X509Certificate;
    //   89: invokevirtual 386	com/lookout/security/b/a:a	(Ljava/io/InputStream;Ljava/io/OutputStream;Ljavax/crypto/SecretKey;Ljava/security/cert/X509Certificate;)I
    //   92: pop
    //   93: new 388	java/util/zip/GZIPInputStream
    //   96: dup
    //   97: new 390	java/io/ByteArrayInputStream
    //   100: dup
    //   101: aload 5
    //   103: invokevirtual 394	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   106: invokespecial 397	java/io/ByteArrayInputStream:<init>	([B)V
    //   109: ldc_w 398
    //   112: invokespecial 401	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;I)V
    //   115: astore 5
    //   117: aload_0
    //   118: getfield 131	com/lookout/security/ah:o	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   121: invokevirtual 405	java/util/concurrent/locks/ReentrantReadWriteLock:writeLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;
    //   124: invokevirtual 410	java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock:lock	()V
    //   127: invokestatic 415	com/lookout/security/e/a/e:a	()Lcom/lookout/security/e/a/e;
    //   130: astore 4
    //   132: aload 4
    //   134: invokevirtual 417	com/lookout/security/e/a/e:c	()V
    //   137: aload 5
    //   139: aload 6
    //   141: aload 4
    //   143: invokestatic 422	com/lookout/security/e/b/a/f:a	(Ljava/io/InputStream;Lcom/lookout/aa/bk;Lcom/lookout/security/e/a/e;)Lcom/lookout/aa/bk;
    //   146: pop
    //   147: aload 4
    //   149: invokevirtual 424	com/lookout/security/e/a/e:d	()V
    //   152: invokestatic 105	com/lookout/androidsecurity/a:a	()Lcom/lookout/androidsecurity/a;
    //   155: invokevirtual 147	com/lookout/androidsecurity/a:j	()Lcom/lookout/androidsecurity/e/f;
    //   158: aload 6
    //   160: invokevirtual 426	com/lookout/security/b:a	()J
    //   163: invokeinterface 429 3 0
    //   168: new 431	com/lookout/aa/b/c
    //   171: dup
    //   172: invokespecial 432	com/lookout/aa/b/c:<init>	()V
    //   175: astore 9
    //   177: aload 9
    //   179: aload 6
    //   181: invokevirtual 435	com/lookout/security/b:f	()Lcom/lookout/k/f;
    //   184: invokevirtual 438	com/lookout/aa/b/c:a	(Lcom/lookout/k/f;)V
    //   187: aload 9
    //   189: aload 6
    //   191: invokevirtual 441	com/lookout/security/b:c	()Lcom/lookout/aa/c/a;
    //   194: invokevirtual 444	com/lookout/aa/b/c:a	(Lcom/lookout/aa/c/a;)V
    //   197: aload_0
    //   198: aload 7
    //   200: invokevirtual 447	com/lookout/security/ah:b	(Lcom/lookout/aa/al;)V
    //   203: aload 6
    //   205: invokevirtual 449	com/lookout/security/b:g	()Lcom/lookout/k/f;
    //   208: astore_1
    //   209: aload_1
    //   210: ifnull +503 -> 713
    //   213: aload_0
    //   214: new 451	com/lookout/aa/bl
    //   217: dup
    //   218: aload_1
    //   219: invokespecial 453	com/lookout/aa/bl:<init>	(Lcom/lookout/k/f;)V
    //   222: invokevirtual 455	com/lookout/security/ah:d	(Lcom/lookout/aa/al;)V
    //   225: aload_0
    //   226: aload 6
    //   228: invokespecial 457	com/lookout/security/ah:b	(Lcom/lookout/aa/bk;)V
    //   231: aload_0
    //   232: aload 6
    //   234: invokevirtual 459	com/lookout/security/ah:a	(Lcom/lookout/aa/al;)V
    //   237: aload_0
    //   238: aload 9
    //   240: invokevirtual 461	com/lookout/security/ah:c	(Lcom/lookout/aa/al;)V
    //   243: aload_0
    //   244: aload 8
    //   246: invokevirtual 463	com/lookout/security/ah:e	(Lcom/lookout/aa/al;)V
    //   249: aload_0
    //   250: aload 6
    //   252: invokevirtual 465	com/lookout/security/ah:a	(Lcom/lookout/aa/bk;)V
    //   255: iconst_1
    //   256: istore_2
    //   257: getstatic 83	com/lookout/security/ah:j	Lorg/a/b;
    //   260: new 242	java/lang/StringBuilder
    //   263: dup
    //   264: invokespecial 243	java/lang/StringBuilder:<init>	()V
    //   267: ldc_w 467
    //   270: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   273: aload 6
    //   275: invokevirtual 426	com/lookout/security/b:a	()J
    //   278: invokevirtual 470	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   281: invokevirtual 253	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   284: invokeinterface 472 2 0
    //   289: aload_0
    //   290: getfield 137	com/lookout/security/ah:m	Lcom/lookout/c/a;
    //   293: invokevirtual 476	com/lookout/c/a:a	()Z
    //   296: istore_3
    //   297: iload_3
    //   298: ifeq +395 -> 693
    //   301: getstatic 83	com/lookout/security/ah:j	Lorg/a/b;
    //   304: new 242	java/lang/StringBuilder
    //   307: dup
    //   308: invokespecial 243	java/lang/StringBuilder:<init>	()V
    //   311: ldc_w 478
    //   314: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   317: new 480	java/util/Date
    //   320: dup
    //   321: aload 6
    //   323: invokevirtual 426	com/lookout/security/b:a	()J
    //   326: invokespecial 482	java/util/Date:<init>	(J)V
    //   329: invokevirtual 252	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   332: invokevirtual 253	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   335: invokeinterface 472 2 0
    //   340: getstatic 83	com/lookout/security/ah:j	Lorg/a/b;
    //   343: new 242	java/lang/StringBuilder
    //   346: dup
    //   347: invokespecial 243	java/lang/StringBuilder:<init>	()V
    //   350: ldc_w 484
    //   353: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   356: aload 6
    //   358: invokevirtual 485	com/lookout/security/b:b	()I
    //   361: invokevirtual 488	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   364: invokevirtual 253	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   367: invokeinterface 472 2 0
    //   372: getstatic 83	com/lookout/security/ah:j	Lorg/a/b;
    //   375: new 242	java/lang/StringBuilder
    //   378: dup
    //   379: invokespecial 243	java/lang/StringBuilder:<init>	()V
    //   382: ldc_w 490
    //   385: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   388: aload 4
    //   390: invokevirtual 491	com/lookout/security/e/a/e:b	()I
    //   393: invokevirtual 488	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   396: invokevirtual 253	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   399: invokeinterface 472 2 0
    //   404: getstatic 83	com/lookout/security/ah:j	Lorg/a/b;
    //   407: new 242	java/lang/StringBuilder
    //   410: dup
    //   411: invokespecial 243	java/lang/StringBuilder:<init>	()V
    //   414: ldc_w 493
    //   417: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   420: aload 6
    //   422: invokevirtual 441	com/lookout/security/b:c	()Lcom/lookout/aa/c/a;
    //   425: invokevirtual 497	com/lookout/aa/c/a:c	()J
    //   428: invokevirtual 470	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   431: invokevirtual 253	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   434: invokeinterface 472 2 0
    //   439: getstatic 83	com/lookout/security/ah:j	Lorg/a/b;
    //   442: new 242	java/lang/StringBuilder
    //   445: dup
    //   446: invokespecial 243	java/lang/StringBuilder:<init>	()V
    //   449: ldc_w 499
    //   452: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   455: aload 6
    //   457: invokevirtual 502	com/lookout/security/b:d	()Lcom/lookout/android/a/b/a;
    //   460: invokevirtual 505	com/lookout/android/a/b/a:c	()J
    //   463: invokevirtual 470	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   466: invokevirtual 253	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   469: invokeinterface 472 2 0
    //   474: getstatic 83	com/lookout/security/ah:j	Lorg/a/b;
    //   477: new 242	java/lang/StringBuilder
    //   480: dup
    //   481: invokespecial 243	java/lang/StringBuilder:<init>	()V
    //   484: ldc_w 507
    //   487: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   490: aload 6
    //   492: invokevirtual 435	com/lookout/security/b:f	()Lcom/lookout/k/f;
    //   495: invokevirtual 511	com/lookout/k/f:d	()I
    //   498: invokevirtual 488	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   501: invokevirtual 253	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   504: invokeinterface 472 2 0
    //   509: getstatic 83	com/lookout/security/ah:j	Lorg/a/b;
    //   512: new 242	java/lang/StringBuilder
    //   515: dup
    //   516: invokespecial 243	java/lang/StringBuilder:<init>	()V
    //   519: ldc_w 513
    //   522: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   525: aload 6
    //   527: invokevirtual 435	com/lookout/security/b:f	()Lcom/lookout/k/f;
    //   530: invokevirtual 515	com/lookout/k/f:e	()J
    //   533: invokevirtual 470	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   536: invokevirtual 253	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   539: invokeinterface 472 2 0
    //   544: getstatic 83	com/lookout/security/ah:j	Lorg/a/b;
    //   547: new 242	java/lang/StringBuilder
    //   550: dup
    //   551: invokespecial 243	java/lang/StringBuilder:<init>	()V
    //   554: ldc_w 517
    //   557: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   560: aload 6
    //   562: invokevirtual 520	com/lookout/security/b:e	()Lcom/lookout/aa/c/f;
    //   565: invokevirtual 523	com/lookout/aa/c/f:c	()J
    //   568: invokevirtual 470	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   571: invokevirtual 253	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   574: invokeinterface 472 2 0
    //   579: getstatic 83	com/lookout/security/ah:j	Lorg/a/b;
    //   582: astore 4
    //   584: new 242	java/lang/StringBuilder
    //   587: dup
    //   588: invokespecial 243	java/lang/StringBuilder:<init>	()V
    //   591: ldc_w 525
    //   594: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   597: astore 7
    //   599: aload_1
    //   600: ifnonnull +166 -> 766
    //   603: ldc_w 527
    //   606: astore_1
    //   607: aload 4
    //   609: aload 7
    //   611: aload_1
    //   612: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   615: invokevirtual 253	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   618: invokeinterface 472 2 0
    //   623: getstatic 83	com/lookout/security/ah:j	Lorg/a/b;
    //   626: new 242	java/lang/StringBuilder
    //   629: dup
    //   630: invokespecial 243	java/lang/StringBuilder:<init>	()V
    //   633: ldc_w 529
    //   636: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   639: aload 6
    //   641: invokevirtual 530	com/lookout/security/b:j	()Lorg/apache/b/e/j;
    //   644: invokevirtual 535	org/apache/b/e/j:a	()Lorg/apache/b/e/f;
    //   647: invokevirtual 540	org/apache/b/e/f:a	()Ljava/util/SortedSet;
    //   650: invokevirtual 252	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   653: invokevirtual 253	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   656: invokeinterface 472 2 0
    //   661: getstatic 83	com/lookout/security/ah:j	Lorg/a/b;
    //   664: new 242	java/lang/StringBuilder
    //   667: dup
    //   668: invokespecial 243	java/lang/StringBuilder:<init>	()V
    //   671: ldc_w 542
    //   674: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   677: aload 6
    //   679: invokevirtual 543	com/lookout/security/b:h	()Ljava/util/ArrayList;
    //   682: invokevirtual 252	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   685: invokevirtual 253	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   688: invokeinterface 472 2 0
    //   693: aload 5
    //   695: astore_1
    //   696: aload_0
    //   697: getfield 131	com/lookout/security/ah:o	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   700: invokevirtual 405	java/util/concurrent/locks/ReentrantReadWriteLock:writeLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;
    //   703: invokevirtual 546	java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock:unlock	()V
    //   706: aload 5
    //   708: invokestatic 551	com/lookout/utils/bf:a	(Ljava/io/InputStream;)V
    //   711: iconst_1
    //   712: ireturn
    //   713: getstatic 83	com/lookout/security/ah:j	Lorg/a/b;
    //   716: ldc_w 553
    //   719: invokeinterface 555 2 0
    //   724: goto -499 -> 225
    //   727: astore 4
    //   729: aload 5
    //   731: astore_1
    //   732: aload_0
    //   733: getfield 131	com/lookout/security/ah:o	Ljava/util/concurrent/locks/ReentrantReadWriteLock;
    //   736: invokevirtual 405	java/util/concurrent/locks/ReentrantReadWriteLock:writeLock	()Ljava/util/concurrent/locks/ReentrantReadWriteLock$WriteLock;
    //   739: invokevirtual 546	java/util/concurrent/locks/ReentrantReadWriteLock$WriteLock:unlock	()V
    //   742: aload 5
    //   744: astore_1
    //   745: aload 4
    //   747: athrow
    //   748: astore 4
    //   750: aload 5
    //   752: astore_1
    //   753: aload_0
    //   754: aload 4
    //   756: invokespecial 557	com/lookout/security/ah:a	(Ljava/lang/Throwable;)V
    //   759: aload 5
    //   761: invokestatic 551	com/lookout/utils/bf:a	(Ljava/io/InputStream;)V
    //   764: iload_2
    //   765: ireturn
    //   766: new 242	java/lang/StringBuilder
    //   769: dup
    //   770: invokespecial 243	java/lang/StringBuilder:<init>	()V
    //   773: ldc_w 559
    //   776: invokevirtual 249	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   779: aload_1
    //   780: invokevirtual 511	com/lookout/k/f:d	()I
    //   783: invokevirtual 488	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   786: invokevirtual 253	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   789: astore_1
    //   790: goto -183 -> 607
    //   793: astore_1
    //   794: getstatic 83	com/lookout/security/ah:j	Lorg/a/b;
    //   797: aload_1
    //   798: invokevirtual 562	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   801: aload_1
    //   802: invokeinterface 258 3 0
    //   807: goto -114 -> 693
    //   810: astore 4
    //   812: iconst_1
    //   813: istore_2
    //   814: goto -85 -> 729
    //   817: astore_1
    //   818: aload 4
    //   820: invokestatic 551	com/lookout/utils/bf:a	(Ljava/io/InputStream;)V
    //   823: aload_1
    //   824: athrow
    //   825: astore 5
    //   827: aload_1
    //   828: astore 4
    //   830: aload 5
    //   832: astore_1
    //   833: goto -15 -> 818
    //   836: astore 4
    //   838: aconst_null
    //   839: astore 5
    //   841: iconst_0
    //   842: istore_2
    //   843: goto -93 -> 750
    //   846: astore 4
    //   848: goto -98 -> 750
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	851	0	this	ah
    //   0	851	1	paramInputStream	java.io.InputStream
    //   1	842	2	bool1	boolean
    //   296	2	3	bool2	boolean
    //   3	605	4	localObject1	Object
    //   727	19	4	localObject2	Object
    //   748	7	4	localThrowable1	Throwable
    //   810	9	4	localInputStream1	java.io.InputStream
    //   828	1	4	localInputStream2	java.io.InputStream
    //   836	1	4	localThrowable2	Throwable
    //   846	1	4	localThrowable3	Throwable
    //   28	732	5	localObject3	Object
    //   825	6	5	localObject4	Object
    //   839	1	5	localObject5	Object
    //   12	666	6	localB	b
    //   37	573	7	localObject6	Object
    //   54	191	8	localI	com.lookout.aa.b.i
    //   72	167	9	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   117	209	727	finally
    //   213	225	727	finally
    //   225	255	727	finally
    //   713	724	727	finally
    //   732	742	748	java/lang/Throwable
    //   745	748	748	java/lang/Throwable
    //   301	599	793	java/lang/Throwable
    //   607	693	793	java/lang/Throwable
    //   766	790	793	java/lang/Throwable
    //   257	297	810	finally
    //   301	599	810	finally
    //   607	693	810	finally
    //   766	790	810	finally
    //   794	807	810	finally
    //   5	117	817	finally
    //   696	706	825	finally
    //   732	742	825	finally
    //   745	748	825	finally
    //   753	759	825	finally
    //   5	117	836	java/lang/Throwable
    //   696	706	846	java/lang/Throwable
  }
  
  /* Error */
  public boolean a(String paramString)
  {
    // Byte code:
    //   0: new 565	java/io/FileInputStream
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 566	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   8: astore_3
    //   9: aload_3
    //   10: astore_1
    //   11: aload_0
    //   12: aload_3
    //   13: invokevirtual 568	com/lookout/security/ah:a	(Ljava/io/InputStream;)Z
    //   16: istore_2
    //   17: aload_3
    //   18: invokestatic 551	com/lookout/utils/bf:a	(Ljava/io/InputStream;)V
    //   21: iload_2
    //   22: ireturn
    //   23: astore 4
    //   25: aconst_null
    //   26: astore_3
    //   27: aload_3
    //   28: astore_1
    //   29: getstatic 83	com/lookout/security/ah:j	Lorg/a/b;
    //   32: ldc_w 570
    //   35: aload 4
    //   37: invokeinterface 258 3 0
    //   42: aload_3
    //   43: invokestatic 551	com/lookout/utils/bf:a	(Ljava/io/InputStream;)V
    //   46: iconst_0
    //   47: ireturn
    //   48: astore_3
    //   49: aconst_null
    //   50: astore_1
    //   51: aload_1
    //   52: invokestatic 551	com/lookout/utils/bf:a	(Ljava/io/InputStream;)V
    //   55: aload_3
    //   56: athrow
    //   57: astore_3
    //   58: goto -7 -> 51
    //   61: astore 4
    //   63: goto -36 -> 27
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	66	0	this	ah
    //   0	66	1	paramString	String
    //   16	6	2	bool	boolean
    //   8	35	3	localFileInputStream	java.io.FileInputStream
    //   48	8	3	localObject1	Object
    //   57	1	3	localObject2	Object
    //   23	13	4	localThrowable1	Throwable
    //   61	1	4	localThrowable2	Throwable
    // Exception table:
    //   from	to	target	type
    //   0	9	23	java/lang/Throwable
    //   0	9	48	finally
    //   11	17	57	finally
    //   29	42	57	finally
    //   11	17	61	java/lang/Throwable
  }
  
  public boolean a(org.apache.b.e.e paramE)
  {
    return h().contains(paramE);
  }
  
  public d b(String paramString)
  {
    if ((this.m.a()) && (com.lookout.c.e.l.c())) {
      j.e("Do not call on the main thread");
    }
    j();
    if (this.c == null) {
      return null;
    }
    return this.c.a(paramString);
  }
  
  public aj b()
  {
    ak localAk = new ak();
    for (;;)
    {
      int i3;
      int i4;
      int i1;
      int i2;
      try
      {
        Object localObject = com.lookout.security.e.a.g.d();
        com.lookout.h.l localL = com.lookout.h.l.a();
        localObject = ((Set)localObject).iterator();
        if (!((Iterator)localObject).hasNext()) {
          break label225;
        }
        localG = (com.lookout.security.e.a.g)((Iterator)localObject).next();
        i3 = localL.a(com.lookout.security.e.a.c.a, localG, com.lookout.security.e.a.b.b);
        i4 = localL.b(com.lookout.security.e.a.c.a, localG, com.lookout.security.e.a.b.b);
        if ((this.r == null) || (this.r.get(localG) == null)) {
          break label280;
        }
        i1 = ((Integer)this.r.get(localG)).intValue();
        if ((this.s == null) || (this.s.get(localG) == null)) {
          break label275;
        }
        i2 = ((Integer)this.s.get(localG)).intValue();
      }
      catch (RuntimeException localRuntimeException)
      {
        com.lookout.security.e.a.g localG;
        label152:
        j.d("Failed to generate the scanResults counts", localRuntimeException);
        return localAk.a();
      }
      localAk.a(localG, Integer.valueOf(i3));
      localAk.c(localG, Integer.valueOf(i4));
      localAk.b(localG, Integer.valueOf(i2));
      localAk.b(Math.max(i1, i4));
      continue;
      label225:
      if (this.n > 0)
      {
        localAk.a(this.n);
      }
      else
      {
        localAk.a(this.l.getPackageManager().getInstalledPackages(0).size());
        continue;
        label275:
        label280:
        do
        {
          i2 = 0;
          break label152;
          i2 = 0;
          continue;
          i1 = 0;
          break;
        } while ((i1 <= 0) || (i1 < i4));
        i2 = i2 + i1 - (i4 + i3);
      }
    }
  }
  
  /* Error */
  protected e b(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 135	com/lookout/security/ah:f	Lcom/lookout/security/e;
    //   6: ifnull +12 -> 18
    //   9: aload_0
    //   10: getfield 135	com/lookout/security/ah:f	Lcom/lookout/security/e;
    //   13: astore_1
    //   14: aload_0
    //   15: monitorexit
    //   16: aload_1
    //   17: areturn
    //   18: aload_0
    //   19: new 672	com/lookout/android/d/m
    //   22: dup
    //   23: aload_1
    //   24: invokespecial 674	com/lookout/android/d/m:<init>	(Landroid/content/Context;)V
    //   27: putfield 135	com/lookout/security/ah:f	Lcom/lookout/security/e;
    //   30: aload_0
    //   31: getfield 135	com/lookout/security/ah:f	Lcom/lookout/security/e;
    //   34: astore_1
    //   35: goto -21 -> 14
    //   38: astore_1
    //   39: aload_0
    //   40: monitorexit
    //   41: aload_1
    //   42: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	43	0	this	ah
    //   0	43	1	paramContext	Context
    // Exception table:
    //   from	to	target	type
    //   2	14	38	finally
    //   18	35	38	finally
  }
  
  protected i c(Context paramContext)
  {
    return new com.lookout.android.d.o(paramContext, com.lookout.androidsecurity.a.a().k());
  }
  
  protected File c()
  {
    return w.a().a(this.l);
  }
  
  public File d()
  {
    return new File(c(), "Policy.FLX");
  }
  
  public Lock e()
  {
    return this.o.readLock();
  }
  
  public void f()
  {
    com.lookout.androidsecurity.a.a().h().a(new com.lookout.services.o(com.lookout.services.p.a));
    a(this.l);
  }
  
  public bk g()
  {
    return this.q;
  }
  
  public List h()
  {
    if (!k()) {
      return h;
    }
    return this.q.h();
  }
  
  public void j()
  {
    if (this.p) {
      return;
    }
    this.o.writeLock().lock();
    try
    {
      com.lookout.androidsecurity.a.a().f().c();
      if (!this.p)
      {
        if (com.lookout.c.e.l.c()) {
          j.d("Should not be extracting policy on the UI thread except in unit tests");
        }
        int i1 = 0;
        if (!d().exists())
        {
          com.lookout.androidsecurity.a.a().f().a(d());
          i1 = 1;
        }
        this.p = a(d().getPath());
        if ((!this.p) && (i1 == 0))
        {
          j.e("Current policy failed to load. Trying bundled default policy.");
          com.lookout.androidsecurity.a.a().f().a(d());
          this.p = a(d().getPath());
        }
      }
      boolean bool = this.p;
      if (bool) {
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        j.d("Failed to ensure policy loaded", localThrowable);
        this.o.writeLock().unlock();
      }
    }
    finally
    {
      this.o.writeLock().unlock();
    }
    throw new bi("Failed to ensure policy loaded.");
  }
}
