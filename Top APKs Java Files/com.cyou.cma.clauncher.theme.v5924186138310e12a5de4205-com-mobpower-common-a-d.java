package com.mobpower.common.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.mobpower.common.d.g;
import com.mobpower.common.g.b.a;
import com.mobpower.common.g.e;
import com.mobpower.common.g.j;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class d
{
  public static HashMap<String, String> a = new HashMap();
  public static int b = b.p;
  public static int c = b.p;
  public static HashMap<String, g> d = new HashMap();
  private static d e;
  private static int f = b.q;
  private Context g;
  private String h;
  private String i;
  private Handler j = new Handler(Looper.getMainLooper());
  private String k;
  
  public d() {}
  
  public static d a()
  {
    if (e == null) {}
    try
    {
      e = new d();
      return e;
    }
    finally {}
  }
  
  public static HashMap<String, g> b()
  {
    return d;
  }
  
  public static void c(String paramString)
  {
    if (a == null) {
      a = new HashMap();
    }
    if (!a.containsKey(paramString)) {
      a.put(paramString, paramString);
    }
  }
  
  public static boolean d(String paramString)
  {
    if ((a != null) && (a.containsKey(paramString))) {
      return true;
    }
    return e(paramString);
  }
  
  public static boolean e(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    if ((d != null) && (d.size() > 0))
    {
      Iterator localIterator = d.keySet().iterator();
      while (localIterator.hasNext()) {
        if (paramString.equals(((g)d.get(localIterator.next())).b())) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static boolean k()
  {
    return f == b.p;
  }
  
  public List<String> a(boolean paramBoolean)
  {
    HashMap localHashMap = b(paramBoolean);
    ArrayList localArrayList = new ArrayList(localHashMap.size());
    localArrayList.addAll(localHashMap.keySet());
    return localArrayList;
  }
  
  public void a(Context paramContext)
  {
    this.g = paramContext;
  }
  
  public void a(Context paramContext, String paramString1, String paramString2)
  {
    if (paramContext == null) {
      return;
    }
    try
    {
      e.c("SDKContext", "init");
      a(paramContext.getApplicationContext());
      a(paramString1);
      b(paramString2);
      f = b.p;
      a(new d.1(this, paramContext), 5000L);
      a(new d.2(this), 2000L);
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public void a(Runnable paramRunnable)
  {
    this.j.post(paramRunnable);
  }
  
  public void a(Runnable paramRunnable, long paramLong)
  {
    this.j.postDelayed(paramRunnable, paramLong);
  }
  
  public void a(String paramString)
  {
    this.h = paramString;
    j.a(this.g, b.e, "appid", paramString);
  }
  
  public void a(String paramString, int paramInt)
  {
    com.mobpower.common.f.b.a(this.g).a();
    com.mobpower.common.f.d.a(this.g).c(paramString, paramInt);
  }
  
  public HashMap<String, String> b(boolean paramBoolean)
  {
    if (paramBoolean) {
      return g();
    }
    if ((a != null) && (a.size() > 0)) {
      return a;
    }
    return g();
  }
  
  public void b(Runnable paramRunnable)
  {
    a.a().a(paramRunnable);
  }
  
  public void b(Runnable paramRunnable, long paramLong)
  {
    a.a().a(paramRunnable, paramLong);
  }
  
  public void b(String paramString)
  {
    this.i = paramString;
    j.a(this.g, b.e, "appkey", paramString);
  }
  
  public Context c()
  {
    return this.g;
  }
  
  public String d()
  {
    if (TextUtils.isEmpty(this.h)) {
      this.h = j.b(this.g, b.e, "appid", "");
    }
    return this.h;
  }
  
  public String e()
  {
    if (TextUtils.isEmpty(this.i)) {
      this.i = j.b(this.g, b.e, "appkey", "");
    }
    return this.i;
  }
  
  public void f()
  {
    a.a().a(new d.3(this));
  }
  
  public HashMap<String, String> g()
  {
    Object localObject = this.g.getPackageManager().getInstalledPackages(0);
    if (a == null) {
      a = new HashMap();
    }
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((localPackageInfo.applicationInfo.flags & 0x1) <= 0) {
        a.put(localPackageInfo.packageName, localPackageInfo.packageName);
      }
    }
    return a;
  }
  
  public List<Long> h()
  {
    ArrayList localArrayList;
    try
    {
      Iterator localIterator;
      if ((d != null) && (d.size() > 0))
      {
        localIterator = d.keySet().iterator();
        localArrayList = new ArrayList();
      }
      while (localIterator.hasNext())
      {
        Object localObject = (String)localIterator.next();
        localObject = (g)d.get(localObject);
        boolean bool = localArrayList.contains(((g)localObject).a());
        if (!bool)
        {
          try
          {
            localArrayList.add(Long.valueOf(Long.parseLong(((g)localObject).a())));
          }
          catch (NumberFormatException localNumberFormatException)
          {
            localNumberFormatException.printStackTrace();
          }
          continue;
          return null;
        }
      }
    }
    catch (Throwable localThrowable) {}
    return localArrayList;
  }
  
  /* Error */
  public void i()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic 48	com/mobpower/common/a/d:d	Ljava/util/HashMap;
    //   5: ifnull +12 -> 17
    //   8: getstatic 48	com/mobpower/common/a/d:d	Ljava/util/HashMap;
    //   11: invokevirtual 98	java/util/HashMap:size	()I
    //   14: ifgt +20 -> 34
    //   17: aload_0
    //   18: getfield 66	com/mobpower/common/a/d:g	Landroid/content/Context;
    //   21: invokestatic 305	com/mobpower/common/a/c:a	(Landroid/content/Context;)Lcom/mobpower/common/a/c;
    //   24: aload_0
    //   25: getfield 73	com/mobpower/common/a/d:h	Ljava/lang/String;
    //   28: invokevirtual 308	com/mobpower/common/a/c:c	(Ljava/lang/String;)Ljava/util/HashMap;
    //   31: putstatic 48	com/mobpower/common/a/d:d	Ljava/util/HashMap;
    //   34: getstatic 48	com/mobpower/common/a/d:d	Ljava/util/HashMap;
    //   37: ifnull +14 -> 51
    //   40: getstatic 48	com/mobpower/common/a/d:d	Ljava/util/HashMap;
    //   43: invokevirtual 98	java/util/HashMap:size	()I
    //   46: istore_1
    //   47: iload_1
    //   48: ifne +6 -> 54
    //   51: aload_0
    //   52: monitorexit
    //   53: return
    //   54: new 27	java/util/HashMap
    //   57: dup
    //   58: invokespecial 30	java/util/HashMap:<init>	()V
    //   61: astore_3
    //   62: getstatic 48	com/mobpower/common/a/d:d	Ljava/util/HashMap;
    //   65: invokevirtual 102	java/util/HashMap:keySet	()Ljava/util/Set;
    //   68: invokeinterface 108 1 0
    //   73: astore 4
    //   75: aload 4
    //   77: invokeinterface 114 1 0
    //   82: istore_2
    //   83: iload_2
    //   84: ifeq +86 -> 170
    //   87: aload 4
    //   89: invokeinterface 118 1 0
    //   94: checkcast 129	java/lang/String
    //   97: astore 5
    //   99: getstatic 48	com/mobpower/common/a/d:d	Ljava/util/HashMap;
    //   102: aload 5
    //   104: invokevirtual 122	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   107: checkcast 124	com/mobpower/common/d/g
    //   110: astore 6
    //   112: getstatic 32	com/mobpower/common/a/d:a	Ljava/util/HashMap;
    //   115: ifnull -40 -> 75
    //   118: getstatic 32	com/mobpower/common/a/d:a	Ljava/util/HashMap;
    //   121: aload 6
    //   123: invokevirtual 127	com/mobpower/common/d/g:b	()Ljava/lang/String;
    //   126: invokevirtual 81	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   129: ifeq -54 -> 75
    //   132: aload 6
    //   134: invokestatic 314	java/lang/System:currentTimeMillis	()J
    //   137: invokevirtual 317	com/mobpower/common/d/g:a	(J)V
    //   140: aload_3
    //   141: aload 5
    //   143: aload 6
    //   145: invokevirtual 85	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   148: pop
    //   149: goto -74 -> 75
    //   152: astore 5
    //   154: aload 5
    //   156: invokevirtual 318	java/lang/Exception:printStackTrace	()V
    //   159: ldc -102
    //   161: ldc_w 320
    //   164: invokestatic 322	com/mobpower/common/g/e:e	(Ljava/lang/String;Ljava/lang/String;)V
    //   167: goto -92 -> 75
    //   170: getstatic 48	com/mobpower/common/a/d:d	Ljava/util/HashMap;
    //   173: ifnull +96 -> 269
    //   176: getstatic 48	com/mobpower/common/a/d:d	Ljava/util/HashMap;
    //   179: invokevirtual 102	java/util/HashMap:keySet	()Ljava/util/Set;
    //   182: invokeinterface 108 1 0
    //   187: astore 4
    //   189: aload 4
    //   191: invokeinterface 114 1 0
    //   196: ifeq +73 -> 269
    //   199: aload 4
    //   201: invokeinterface 118 1 0
    //   206: checkcast 129	java/lang/String
    //   209: astore 5
    //   211: getstatic 48	com/mobpower/common/a/d:d	Ljava/util/HashMap;
    //   214: aload 5
    //   216: invokevirtual 122	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   219: checkcast 124	com/mobpower/common/d/g
    //   222: astore 6
    //   224: aload_3
    //   225: aload 6
    //   227: invokevirtual 127	com/mobpower/common/d/g:b	()Ljava/lang/String;
    //   230: invokevirtual 81	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   233: ifne -44 -> 189
    //   236: aload 6
    //   238: invokevirtual 324	com/mobpower/common/d/g:c	()J
    //   241: invokestatic 314	java/lang/System:currentTimeMillis	()J
    //   244: ldc2_w 325
    //   247: lsub
    //   248: lcmp
    //   249: ifle -60 -> 189
    //   252: aload_3
    //   253: aload 5
    //   255: aload 6
    //   257: invokevirtual 85	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   260: pop
    //   261: goto -72 -> 189
    //   264: astore_3
    //   265: aload_0
    //   266: monitorexit
    //   267: aload_3
    //   268: athrow
    //   269: getstatic 48	com/mobpower/common/a/d:d	Ljava/util/HashMap;
    //   272: ifnull +9 -> 281
    //   275: getstatic 48	com/mobpower/common/a/d:d	Ljava/util/HashMap;
    //   278: invokevirtual 329	java/util/HashMap:clear	()V
    //   281: aload_3
    //   282: invokevirtual 98	java/util/HashMap:size	()I
    //   285: ifle +10 -> 295
    //   288: getstatic 48	com/mobpower/common/a/d:d	Ljava/util/HashMap;
    //   291: aload_3
    //   292: invokevirtual 333	java/util/HashMap:putAll	(Ljava/util/Map;)V
    //   295: aload_0
    //   296: getfield 66	com/mobpower/common/a/d:g	Landroid/content/Context;
    //   299: invokestatic 305	com/mobpower/common/a/c:a	(Landroid/content/Context;)Lcom/mobpower/common/a/c;
    //   302: getstatic 48	com/mobpower/common/a/d:d	Ljava/util/HashMap;
    //   305: invokevirtual 337	java/util/HashMap:values	()Ljava/util/Collection;
    //   308: invokevirtual 340	com/mobpower/common/a/c:a	(Ljava/util/Collection;)V
    //   311: goto -260 -> 51
    //   314: astore_3
    //   315: goto -264 -> 51
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	318	0	this	d
    //   46	2	1	m	int
    //   82	2	2	bool	boolean
    //   61	192	3	localHashMap	HashMap
    //   264	28	3	localMap	java.util.Map
    //   314	1	3	localThrowable	Throwable
    //   73	127	4	localIterator	Iterator
    //   97	45	5	str1	String
    //   152	3	5	localException	Exception
    //   209	45	5	str2	String
    //   110	146	6	localG	g
    // Exception table:
    //   from	to	target	type
    //   87	149	152	java/lang/Exception
    //   2	17	264	finally
    //   17	34	264	finally
    //   34	47	264	finally
    //   54	75	264	finally
    //   75	83	264	finally
    //   87	149	264	finally
    //   154	167	264	finally
    //   170	189	264	finally
    //   189	261	264	finally
    //   269	281	264	finally
    //   281	295	264	finally
    //   295	311	264	finally
    //   2	17	314	java/lang/Throwable
    //   17	34	314	java/lang/Throwable
    //   34	47	314	java/lang/Throwable
    //   54	75	314	java/lang/Throwable
    //   75	83	314	java/lang/Throwable
    //   87	149	314	java/lang/Throwable
    //   154	167	314	java/lang/Throwable
    //   170	189	314	java/lang/Throwable
    //   189	261	314	java/lang/Throwable
    //   269	281	314	java/lang/Throwable
    //   281	295	314	java/lang/Throwable
    //   295	311	314	java/lang/Throwable
  }
  
  public void j()
  {
    e.c("saveAppInfo-----------------addInstallApp---------------->", " " + d.size());
    try
    {
      if ((d != null) && (d.size() > 0)) {
        c.a(this.g).a(d.values());
      }
      return;
    }
    catch (Throwable localThrowable) {}
  }
}
