package com.mcafee.AppPrivacy.cloudscan;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.mcafee.cloudscan.mc20.e;
import com.mcafee.cloudscan.mc20.m;
import com.mcafee.cloudscan.mc20.o.a;
import com.mcafee.cloudscan.mc20.o.b;
import com.mcafee.cloudscan.mc20.o.c;
import com.mcafee.cloudscan.mc20.x;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class b
{
  private static b c = null;
  private static Object d = new Object();
  private Context a = null;
  private boolean b = false;
  private Object e = new Object();
  private b f = null;
  private b g = null;
  private ExecutorService h = Executors.newCachedThreadPool();
  private ExecutorService i = Executors.newSingleThreadExecutor();
  
  private b() {}
  
  public static b a(Context paramContext)
  {
    synchronized (d)
    {
      if (c == null)
      {
        if (paramContext == null) {
          return null;
        }
        c = new b();
        c.a = paramContext.getApplicationContext();
      }
      paramContext = c;
      return paramContext;
    }
  }
  
  private void a(final int paramInt1, final int paramInt2, final c paramC)
  {
    this.i.execute(new Runnable()
    {
      public void run()
      {
        b.a(b.this, paramInt1, paramInt2, paramC);
      }
    });
  }
  
  private void a(int paramInt1, x paramX, int paramInt2, int paramInt3)
  {
    switch (paramInt1)
    {
    }
    do
    {
      for (;;)
      {
        return;
        if ((this.f == null) || (this.f.b == null) || (this.f.b.size() == 0))
        {
          com.mcafee.android.e.o.b("FullScanMgr", "No listener to notity full scan REPU RECEIVED.");
          return;
        }
        for (Object localObject = new LinkedList(this.f.b); localObject != null; localObject = new LinkedList(this.g.b))
        {
          localObject = ((List)localObject).iterator();
          while (((Iterator)localObject).hasNext()) {
            ((d.a)((Iterator)localObject).next()).a(paramX, paramInt2, paramInt3);
          }
          if ((this.g == null) || (this.g.b == null) || (this.g.b.size() == 0))
          {
            com.mcafee.android.e.o.b("FullScanMgr", "No listener to notity full scan REPU RECEIVED.");
            return;
          }
        }
      }
    } while ((paramX == null) || (!com.mcafee.android.e.o.a("FullScanMgr", 3)));
    com.mcafee.android.e.o.b("FullScanMgr", paramX.a + " REPU RECEIVED Notified.");
  }
  
  private void b(int paramInt1, int paramInt2, c paramC)
  {
    switch (paramInt1)
    {
    default: 
      return;
    case 1: 
      if ((this.f == null) || (this.f.b == null) || (this.f.b.size() == 0))
      {
        com.mcafee.android.e.o.b("FullScanMgr", "No listener to notity full scan FINISH.");
        return;
      }
      break;
    }
    for (Object localObject = new LinkedList(this.f.b); localObject != null; localObject = new LinkedList(this.g.b))
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        ((d.a)((Iterator)localObject).next()).a(paramInt2, paramC);
      }
      if ((this.g == null) || (this.g.b == null) || (this.g.b.size() == 0))
      {
        com.mcafee.android.e.o.b("FullScanMgr", "No listener to notity full scan FINISH.");
        return;
      }
    }
    com.mcafee.android.e.o.b("FullScanMgr", "FINISH Notified.");
  }
  
  private void d(final int paramInt)
  {
    this.i.execute(new Runnable()
    {
      public void run()
      {
        b.a(b.this, paramInt);
      }
    });
  }
  
  private void e(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return;
    case 1: 
      if ((this.f == null) || (this.f.b == null) || (this.f.b.size() == 0))
      {
        com.mcafee.android.e.o.b("FullScanMgr", "No listener to notity full scan START.");
        return;
      }
      break;
    }
    for (Object localObject = new LinkedList(this.f.b); localObject != null; localObject = new LinkedList(this.g.b))
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        ((d.a)((Iterator)localObject).next()).ae_();
      }
      if ((this.g == null) || (this.g.b == null) || (this.g.b.size() == 0))
      {
        com.mcafee.android.e.o.b("FullScanMgr", "No listener to notity full scan START.");
        return;
      }
    }
    com.mcafee.android.e.o.b("FullScanMgr", "START Notified.");
  }
  
  public a a(int paramInt, d.a paramA)
  {
    boolean bool = true;
    for (;;)
    {
      a localA;
      synchronized (this.e)
      {
        localA = new a(this.a, paramInt);
        switch (paramInt)
        {
        case 2: 
          return paramA;
          if ((paramA != null) && (!this.f.b.contains(paramA))) {
            this.f.b.add(paramA);
          }
          if (this.f.a != null)
          {
            paramA = localA;
            if (!this.f.a.e()) {
              continue;
            }
          }
          com.mcafee.android.e.o.b("FullScanMgr", "Create new ODS scan thread.");
          this.f.a = new a(paramInt, bool);
          this.h.submit(this.f.a);
          paramA = localA;
        }
      }
      if ((paramA != null) && (!this.g.b.contains(paramA))) {
        this.g.b.add(paramA);
      }
      if (this.g.a != null)
      {
        paramA = localA;
        if (!this.g.a.e()) {}
      }
      else
      {
        com.mcafee.android.e.o.b("FullScanMgr", "Create new OSS scan thread.");
        this.g.a = new a(paramInt);
        this.h.submit(this.g.a);
        paramA = localA;
        continue;
        bool = false;
        continue;
        paramA = null;
        continue;
        paramInt = 1;
      }
    }
  }
  
  /* Error */
  public c a(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 45	com/mcafee/AppPrivacy/cloudscan/b:e	Ljava/lang/Object;
    //   4: astore_3
    //   5: aload_3
    //   6: monitorenter
    //   7: aconst_null
    //   8: astore_2
    //   9: iload_1
    //   10: tableswitch	default:+22->32, 1:+26->36, 2:+50->60
    //   32: aload_3
    //   33: monitorexit
    //   34: aload_2
    //   35: areturn
    //   36: aload_0
    //   37: getfield 47	com/mcafee/AppPrivacy/cloudscan/b:f	Lcom/mcafee/AppPrivacy/cloudscan/b$b;
    //   40: getfield 192	com/mcafee/AppPrivacy/cloudscan/b$b:a	Lcom/mcafee/AppPrivacy/cloudscan/b$a;
    //   43: ifnull -11 -> 32
    //   46: aload_0
    //   47: getfield 47	com/mcafee/AppPrivacy/cloudscan/b:f	Lcom/mcafee/AppPrivacy/cloudscan/b$b;
    //   50: getfield 192	com/mcafee/AppPrivacy/cloudscan/b$b:a	Lcom/mcafee/AppPrivacy/cloudscan/b$a;
    //   53: invokevirtual 210	com/mcafee/AppPrivacy/cloudscan/b$a:a	()Lcom/mcafee/AppPrivacy/cloudscan/c;
    //   56: astore_2
    //   57: goto -25 -> 32
    //   60: aload_0
    //   61: getfield 49	com/mcafee/AppPrivacy/cloudscan/b:g	Lcom/mcafee/AppPrivacy/cloudscan/b$b;
    //   64: getfield 192	com/mcafee/AppPrivacy/cloudscan/b$b:a	Lcom/mcafee/AppPrivacy/cloudscan/b$a;
    //   67: ifnull -35 -> 32
    //   70: aload_0
    //   71: getfield 49	com/mcafee/AppPrivacy/cloudscan/b:g	Lcom/mcafee/AppPrivacy/cloudscan/b$b;
    //   74: getfield 192	com/mcafee/AppPrivacy/cloudscan/b$b:a	Lcom/mcafee/AppPrivacy/cloudscan/b$a;
    //   77: invokevirtual 210	com/mcafee/AppPrivacy/cloudscan/b$a:a	()Lcom/mcafee/AppPrivacy/cloudscan/c;
    //   80: astore_2
    //   81: goto -49 -> 32
    //   84: astore_2
    //   85: aload_3
    //   86: monitorexit
    //   87: aload_2
    //   88: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	89	0	this	b
    //   0	89	1	paramInt	int
    //   8	73	2	localC	c
    //   84	4	2	localObject1	Object
    //   4	82	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   32	34	84	finally
    //   36	57	84	finally
    //   60	81	84	finally
    //   85	87	84	finally
  }
  
  public void a()
  {
    synchronized (this.e)
    {
      if (!this.b)
      {
        if (this.f == null)
        {
          this.f = new b();
          this.f.b = new LinkedList();
        }
        if (this.g == null)
        {
          this.g = new b();
          this.g.b = new LinkedList();
        }
        this.b = true;
      }
      return;
    }
  }
  
  public a b(int paramInt)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if ((paramInt & 0x1) != 0)
    {
      if ((this.f.a != null) && (!this.f.a.e()))
      {
        localObject1 = new a(this.a, 1);
        com.mcafee.android.e.o.b("FullScanMgr", "Get an ODS scan executor.");
      }
    }
    else {
      if (localObject1 == null) {
        break label71;
      }
    }
    label71:
    while ((paramInt & 0x2) == 0)
    {
      return localObject1;
      com.mcafee.android.e.o.b("FullScanMgr", "Get no ODS scan executor, no ODS is running.");
      localObject1 = localObject2;
      break;
    }
    if ((this.g.a != null) && (!this.g.a.e()))
    {
      localObject1 = new a(this.a, 2);
      com.mcafee.android.e.o.b("FullScanMgr", "Get an OSS scan executor.");
      return localObject1;
    }
    com.mcafee.android.e.o.b("FullScanMgr", "Get no OSS scan executor, no OSS is running.");
    return localObject1;
  }
  
  public void b()
  {
    synchronized (this.e)
    {
      if (this.b == true) {
        this.b = false;
      }
      return;
    }
  }
  
  /* Error */
  public boolean b(int paramInt, d.a paramA)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 45	com/mcafee/AppPrivacy/cloudscan/b:e	Ljava/lang/Object;
    //   4: astore 5
    //   6: aload 5
    //   8: monitorenter
    //   9: iconst_1
    //   10: istore 4
    //   12: iload_1
    //   13: tableswitch	default:+23->36, 1:+30->43, 2:+99->112
    //   36: iconst_0
    //   37: istore_3
    //   38: aload 5
    //   40: monitorexit
    //   41: iload_3
    //   42: ireturn
    //   43: aload_0
    //   44: getfield 47	com/mcafee/AppPrivacy/cloudscan/b:f	Lcom/mcafee/AppPrivacy/cloudscan/b$b;
    //   47: getfield 85	com/mcafee/AppPrivacy/cloudscan/b$b:b	Ljava/util/List;
    //   50: ifnonnull +17 -> 67
    //   53: aload_0
    //   54: getfield 47	com/mcafee/AppPrivacy/cloudscan/b:f	Lcom/mcafee/AppPrivacy/cloudscan/b$b;
    //   57: new 102	java/util/LinkedList
    //   60: dup
    //   61: invokespecial 212	java/util/LinkedList:<init>	()V
    //   64: putfield 85	com/mcafee/AppPrivacy/cloudscan/b$b:b	Ljava/util/List;
    //   67: iload 4
    //   69: istore_3
    //   70: aload_0
    //   71: getfield 47	com/mcafee/AppPrivacy/cloudscan/b:f	Lcom/mcafee/AppPrivacy/cloudscan/b$b;
    //   74: getfield 85	com/mcafee/AppPrivacy/cloudscan/b$b:b	Ljava/util/List;
    //   77: aload_2
    //   78: invokeinterface 186 2 0
    //   83: ifne -45 -> 38
    //   86: aload_0
    //   87: getfield 47	com/mcafee/AppPrivacy/cloudscan/b:f	Lcom/mcafee/AppPrivacy/cloudscan/b$b;
    //   90: getfield 85	com/mcafee/AppPrivacy/cloudscan/b$b:b	Ljava/util/List;
    //   93: aload_2
    //   94: invokeinterface 189 2 0
    //   99: pop
    //   100: iload 4
    //   102: istore_3
    //   103: goto -65 -> 38
    //   106: astore_2
    //   107: aload 5
    //   109: monitorexit
    //   110: aload_2
    //   111: athrow
    //   112: aload_0
    //   113: getfield 49	com/mcafee/AppPrivacy/cloudscan/b:g	Lcom/mcafee/AppPrivacy/cloudscan/b$b;
    //   116: getfield 85	com/mcafee/AppPrivacy/cloudscan/b$b:b	Ljava/util/List;
    //   119: ifnonnull +17 -> 136
    //   122: aload_0
    //   123: getfield 49	com/mcafee/AppPrivacy/cloudscan/b:g	Lcom/mcafee/AppPrivacy/cloudscan/b$b;
    //   126: new 102	java/util/LinkedList
    //   129: dup
    //   130: invokespecial 212	java/util/LinkedList:<init>	()V
    //   133: putfield 85	com/mcafee/AppPrivacy/cloudscan/b$b:b	Ljava/util/List;
    //   136: iload 4
    //   138: istore_3
    //   139: aload_0
    //   140: getfield 49	com/mcafee/AppPrivacy/cloudscan/b:g	Lcom/mcafee/AppPrivacy/cloudscan/b$b;
    //   143: getfield 85	com/mcafee/AppPrivacy/cloudscan/b$b:b	Ljava/util/List;
    //   146: aload_2
    //   147: invokeinterface 186 2 0
    //   152: ifne -114 -> 38
    //   155: aload_0
    //   156: getfield 49	com/mcafee/AppPrivacy/cloudscan/b:g	Lcom/mcafee/AppPrivacy/cloudscan/b$b;
    //   159: getfield 85	com/mcafee/AppPrivacy/cloudscan/b$b:b	Ljava/util/List;
    //   162: aload_2
    //   163: invokeinterface 189 2 0
    //   168: pop
    //   169: iload 4
    //   171: istore_3
    //   172: goto -134 -> 38
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	175	0	this	b
    //   0	175	1	paramInt	int
    //   0	175	2	paramA	d.a
    //   37	135	3	bool1	boolean
    //   10	160	4	bool2	boolean
    //   4	104	5	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   38	41	106	finally
    //   43	67	106	finally
    //   70	100	106	finally
    //   107	110	106	finally
    //   112	136	106	finally
    //   139	169	106	finally
  }
  
  /* Error */
  public boolean c(int paramInt)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_2
    //   2: aload_0
    //   3: getfield 45	com/mcafee/AppPrivacy/cloudscan/b:e	Ljava/lang/Object;
    //   6: astore_3
    //   7: aload_3
    //   8: monitorenter
    //   9: iload_1
    //   10: tableswitch	default:+22->32, 1:+28->38, 2:+71->81
    //   32: iconst_0
    //   33: istore_2
    //   34: aload_3
    //   35: monitorexit
    //   36: iload_2
    //   37: ireturn
    //   38: aload_0
    //   39: getfield 47	com/mcafee/AppPrivacy/cloudscan/b:f	Lcom/mcafee/AppPrivacy/cloudscan/b$b;
    //   42: getfield 192	com/mcafee/AppPrivacy/cloudscan/b$b:a	Lcom/mcafee/AppPrivacy/cloudscan/b$a;
    //   45: ifnull -13 -> 32
    //   48: aload_0
    //   49: getfield 47	com/mcafee/AppPrivacy/cloudscan/b:f	Lcom/mcafee/AppPrivacy/cloudscan/b$b;
    //   52: getfield 192	com/mcafee/AppPrivacy/cloudscan/b$b:a	Lcom/mcafee/AppPrivacy/cloudscan/b$a;
    //   55: invokevirtual 194	com/mcafee/AppPrivacy/cloudscan/b$a:e	()Z
    //   58: ifne -26 -> 32
    //   61: aload_0
    //   62: getfield 47	com/mcafee/AppPrivacy/cloudscan/b:f	Lcom/mcafee/AppPrivacy/cloudscan/b$b;
    //   65: getfield 192	com/mcafee/AppPrivacy/cloudscan/b$b:a	Lcom/mcafee/AppPrivacy/cloudscan/b$a;
    //   68: invokevirtual 225	com/mcafee/AppPrivacy/cloudscan/b$a:c	()V
    //   71: goto -37 -> 34
    //   74: astore 4
    //   76: aload_3
    //   77: monitorexit
    //   78: aload 4
    //   80: athrow
    //   81: aload_0
    //   82: getfield 49	com/mcafee/AppPrivacy/cloudscan/b:g	Lcom/mcafee/AppPrivacy/cloudscan/b$b;
    //   85: getfield 192	com/mcafee/AppPrivacy/cloudscan/b$b:a	Lcom/mcafee/AppPrivacy/cloudscan/b$a;
    //   88: ifnull -56 -> 32
    //   91: aload_0
    //   92: getfield 49	com/mcafee/AppPrivacy/cloudscan/b:g	Lcom/mcafee/AppPrivacy/cloudscan/b$b;
    //   95: getfield 192	com/mcafee/AppPrivacy/cloudscan/b$b:a	Lcom/mcafee/AppPrivacy/cloudscan/b$a;
    //   98: invokevirtual 194	com/mcafee/AppPrivacy/cloudscan/b$a:e	()Z
    //   101: ifne -69 -> 32
    //   104: aload_0
    //   105: getfield 49	com/mcafee/AppPrivacy/cloudscan/b:g	Lcom/mcafee/AppPrivacy/cloudscan/b$b;
    //   108: getfield 192	com/mcafee/AppPrivacy/cloudscan/b$b:a	Lcom/mcafee/AppPrivacy/cloudscan/b$a;
    //   111: invokevirtual 225	com/mcafee/AppPrivacy/cloudscan/b$a:c	()V
    //   114: goto -80 -> 34
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	117	0	this	b
    //   0	117	1	paramInt	int
    //   1	36	2	bool	boolean
    //   6	71	3	localObject1	Object
    //   74	5	4	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   34	36	74	finally
    //   38	71	74	finally
    //   76	78	74	finally
    //   81	114	74	finally
  }
  
  /* Error */
  public boolean c(int paramInt, d.a paramA)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 45	com/mcafee/AppPrivacy/cloudscan/b:e	Ljava/lang/Object;
    //   4: astore 7
    //   6: aload 7
    //   8: monitorenter
    //   9: iconst_1
    //   10: istore 4
    //   12: aconst_null
    //   13: astore 6
    //   15: iload_1
    //   16: tableswitch	default:+24->40, 1:+40->56, 2:+86->102
    //   40: iconst_0
    //   41: istore_3
    //   42: aload 6
    //   44: astore 5
    //   46: aload 5
    //   48: ifnonnull +100 -> 148
    //   51: aload 7
    //   53: monitorexit
    //   54: iload_3
    //   55: ireturn
    //   56: aload 6
    //   58: astore 5
    //   60: iload 4
    //   62: istore_3
    //   63: aload_0
    //   64: getfield 47	com/mcafee/AppPrivacy/cloudscan/b:f	Lcom/mcafee/AppPrivacy/cloudscan/b$b;
    //   67: ifnull -21 -> 46
    //   70: aload 6
    //   72: astore 5
    //   74: iload 4
    //   76: istore_3
    //   77: aload_0
    //   78: getfield 47	com/mcafee/AppPrivacy/cloudscan/b:f	Lcom/mcafee/AppPrivacy/cloudscan/b$b;
    //   81: getfield 85	com/mcafee/AppPrivacy/cloudscan/b$b:b	Ljava/util/List;
    //   84: ifnull -38 -> 46
    //   87: aload_0
    //   88: getfield 47	com/mcafee/AppPrivacy/cloudscan/b:f	Lcom/mcafee/AppPrivacy/cloudscan/b$b;
    //   91: getfield 85	com/mcafee/AppPrivacy/cloudscan/b$b:b	Ljava/util/List;
    //   94: astore 5
    //   96: iload 4
    //   98: istore_3
    //   99: goto -53 -> 46
    //   102: aload 6
    //   104: astore 5
    //   106: iload 4
    //   108: istore_3
    //   109: aload_0
    //   110: getfield 49	com/mcafee/AppPrivacy/cloudscan/b:g	Lcom/mcafee/AppPrivacy/cloudscan/b$b;
    //   113: ifnull -67 -> 46
    //   116: aload 6
    //   118: astore 5
    //   120: iload 4
    //   122: istore_3
    //   123: aload_0
    //   124: getfield 49	com/mcafee/AppPrivacy/cloudscan/b:g	Lcom/mcafee/AppPrivacy/cloudscan/b$b;
    //   127: getfield 85	com/mcafee/AppPrivacy/cloudscan/b$b:b	Ljava/util/List;
    //   130: ifnull -84 -> 46
    //   133: aload_0
    //   134: getfield 49	com/mcafee/AppPrivacy/cloudscan/b:g	Lcom/mcafee/AppPrivacy/cloudscan/b$b;
    //   137: getfield 85	com/mcafee/AppPrivacy/cloudscan/b$b:b	Ljava/util/List;
    //   140: astore 5
    //   142: iload 4
    //   144: istore_3
    //   145: goto -99 -> 46
    //   148: aload 5
    //   150: invokeinterface 109 1 0
    //   155: astore 5
    //   157: aload 5
    //   159: invokeinterface 115 1 0
    //   164: ifeq +34 -> 198
    //   167: aload 5
    //   169: invokeinterface 119 1 0
    //   174: checkcast 121	com/mcafee/AppPrivacy/cloudscan/d$a
    //   177: aload_2
    //   178: invokevirtual 228	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   181: ifeq -24 -> 157
    //   184: ldc 93
    //   186: ldc -26
    //   188: invokestatic 100	com/mcafee/android/e/o:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   191: aload 5
    //   193: invokeinterface 233 1 0
    //   198: aload 7
    //   200: monitorexit
    //   201: iload_3
    //   202: ireturn
    //   203: astore_2
    //   204: aload 7
    //   206: monitorexit
    //   207: aload_2
    //   208: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	209	0	this	b
    //   0	209	1	paramInt	int
    //   0	209	2	paramA	d.a
    //   41	161	3	bool1	boolean
    //   10	133	4	bool2	boolean
    //   44	148	5	localObject1	Object
    //   13	104	6	localObject2	Object
    //   4	201	7	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   51	54	203	finally
    //   63	70	203	finally
    //   77	96	203	finally
    //   109	116	203	finally
    //   123	142	203	finally
    //   148	157	203	finally
    //   157	198	203	finally
    //   198	201	203	finally
    //   204	207	203	finally
  }
  
  private class a
    implements Callable<Object>
  {
    LinkedList<x> a = new LinkedList();
    private int c = 0;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private Object i = new Object();
    private AtomicInteger j = new AtomicInteger(2);
    private c k = null;
    private boolean l;
    
    a(int paramInt)
    {
      this.c = paramInt;
      this.l = false;
    }
    
    a(int paramInt, boolean paramBoolean)
    {
      this.c = paramInt;
      this.l = paramBoolean;
    }
    
    private void a(long paramLong)
    {
      for (;;)
      {
        synchronized (this.i)
        {
          int m = this.j.get();
          if ((m != 0) || (paramLong > 0L)) {}
          try
          {
            this.i.wait(paramLong);
          }
          catch (InterruptedException localInterruptedException) {}
          this.i.wait();
        }
        return;
      }
    }
    
    private void g()
    {
      synchronized (this.i)
      {
        this.i.notify();
        return;
      }
    }
    
    private List<String> h()
    {
      for (;;)
      {
        Object localObject2;
        boolean bool;
        try
        {
          Object localObject1 = b.a(b.this).getPackageManager().getInstalledApplications(0);
          LinkedList localLinkedList = new LinkedList();
          com.mcafee.android.e.o.b("FullScanMgr", "scanType = " + this.c);
          localObject2 = com.mcafee.AppPrivacy.a.b.a(b.a(b.this));
          if (this.c != 1) {
            break label222;
          }
          if (this.l)
          {
            bool = ((com.mcafee.AppPrivacy.a.b)localObject2).e();
            com.mcafee.android.e.o.b("FullScanMgr", "lScanDownloadedApps = " + bool);
            localObject1 = ((List)localObject1).iterator();
            if (!((Iterator)localObject1).hasNext()) {
              break label242;
            }
            localObject2 = (ApplicationInfo)((Iterator)localObject1).next();
            if ((bool == true) && ((((ApplicationInfo)localObject2).flags & 0x1) != 0) && ((((ApplicationInfo)localObject2).flags & 0x80) == 0)) {
              continue;
            }
            com.mcafee.android.e.o.b("FullScanMgr", "info.packageName = " + ((ApplicationInfo)localObject2).packageName);
            localLinkedList.add(((ApplicationInfo)localObject2).packageName);
            continue;
          }
          bool = ((com.mcafee.AppPrivacy.a.b)localObject2).d();
        }
        catch (Exception localException)
        {
          com.mcafee.android.e.o.b("FullScanMgr", "getAppList exception", localException);
          return null;
        }
        continue;
        label222:
        if (this.c == 2)
        {
          bool = ((com.mcafee.AppPrivacy.a.b)localObject2).c().d;
          continue;
          label242:
          return localException;
        }
        else
        {
          bool = false;
        }
      }
    }
    
    c a()
    {
      return new c(this.d, this.h, this.f, this.g, this.a);
    }
    
    void a(int paramInt)
    {
      com.mcafee.android.e.o.b("FullScanMgr", "stopped");
      synchronized (this.i)
      {
        this.j.set(2);
        b.b(b.this, this.c, paramInt, this.k);
      }
      synchronized (b.b(b.this))
      {
        if (this.c == 2) {
          if (b.c(b.this) != null) {
            b.c(b.this).a = null;
          }
        }
        while ((this.c != 1) || (b.d(b.this) == null))
        {
          return;
          localObject2 = finally;
          throw localObject2;
        }
        b.d(b.this).a = null;
      }
    }
    
    public c b()
    {
      if (!d())
      {
        this.k = new c(0, 0, 0, 0, null);
        a(32);
        return this.k;
      }
      Object localObject = h();
      if (localObject != null)
      {
        int m = ((List)localObject).size();
        this.d = m;
        if (m != 0) {}
      }
      else
      {
        this.k = new c(0, 0, 0, 0, null);
        a(64);
        return this.k;
      }
      com.mcafee.android.e.o.b("FullScanMgr", "call scan start.");
      o.a localA = new o.a();
      if ((!this.l) && (this.c == 1)) {}
      for (boolean bool = true;; bool = false)
      {
        localA.a = bool;
        try
        {
          localObject = m.a(b.a(b.this)).e().a((List)localObject, localA, new a());
          a(0L);
          if (this.j.get() == 1)
          {
            if (com.mcafee.android.e.o.a("FullScanMgr", 3)) {
              com.mcafee.android.e.o.b("FullScanMgr", "Ask CloudScan to cancel scan.2 :" + this);
            }
            ((o.c)localObject).a();
          }
          return this.k;
        }
        catch (Exception localException)
        {
          com.mcafee.android.e.o.b("FullScanMgr", "call scan fail.");
          this.k = new c(this.d, this.d, 0, 0, null);
          a(64);
        }
      }
      return this.k;
    }
    
    void c()
    {
      com.mcafee.android.e.o.b("FullScanMgr", "cancelling");
      synchronized (this.i)
      {
        this.j.set(1);
        g();
        return;
      }
    }
    
    boolean d()
    {
      com.mcafee.android.e.o.b("FullScanMgr", "started");
      synchronized (this.i)
      {
        if (this.j.get() == 2)
        {
          this.j.set(0);
          b.b(b.this, this.c);
          return true;
        }
        return false;
      }
    }
    
    boolean e()
    {
      for (;;)
      {
        synchronized (this.i)
        {
          if (this.j.get() == 2)
          {
            bool = true;
            return bool;
          }
        }
        boolean bool = false;
      }
    }
    
    public boolean f()
    {
      for (;;)
      {
        synchronized (this.i)
        {
          if (this.j.get() == 0)
          {
            bool = true;
            return bool;
          }
        }
        boolean bool = false;
      }
    }
    
    class a
      implements o.b
    {
      a() {}
      
      public void a(int paramInt)
      {
        if (com.mcafee.android.e.o.a("FullScanMgr", 3)) {
          com.mcafee.android.e.o.b("FullScanMgr", "Full scan...onFinish: " + paramInt);
        }
        b.a.a(b.a.this, new c(b.a.a(b.a.this), b.a.b(b.a.this), b.a.c(b.a.this), b.a.d(b.a.this), b.a.this.a));
        b.a.this.a(paramInt);
        b.a.e(b.a.this);
      }
      
      public void a(int paramInt, String paramString, e paramE)
      {
        if (!b.a.this.f()) {
          return;
        }
        b.a.f(b.a.this);
        if (paramE != null) {
          b.a.this.a.add(paramE.b);
        }
        if (paramInt != 0)
        {
          b.a.g(b.a.this);
          if (com.mcafee.android.e.o.a("FullScanMgr", 3)) {
            com.mcafee.android.e.o.b("FullScanMgr", "Full scan...onScanResult: scanType: " + b.a.j(b.a.this) + "." + paramString + ". receivedCount: " + b.a.k(b.a.this) + ". failedCount: " + b.a.b(b.a.this) + ". riskyCount: " + b.a.d(b.a.this) + ". safeCount: " + b.a.c(b.a.this));
          }
          if (paramE == null) {
            break label254;
          }
        }
        label254:
        for (paramString = paramE.b;; paramString = null)
        {
          b.a(b.this, b.a.j(b.a.this), paramString, b.a.a(b.a.this), b.a.k(b.a.this));
          return;
          if ((paramE != null) && (paramE.b != null) && (paramE.b.p == 0) && (paramE.b.n == 1))
          {
            b.a.h(b.a.this);
            break;
          }
          b.a.i(b.a.this);
          break;
        }
      }
    }
  }
  
  private static class b
  {
    b.a a = null;
    List<d.a> b = null;
    
    public b() {}
  }
}
