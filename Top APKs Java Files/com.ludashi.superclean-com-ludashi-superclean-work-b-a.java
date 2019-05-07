package com.ludashi.superclean.work.b;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import com.ludashi.framework.utils.c.e;
import com.ludashi.framework.utils.m;
import com.ludashi.superclean.work.notification.core.b;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class a
{
  private static volatile a a;
  private List<ApplicationInfo> b = null;
  private List<PackageInfo> c = null;
  private List<com.ludashi.superclean.work.model.a> d = null;
  private Queue<Integer> e = new LinkedList();
  private PackageManager f = com.ludashi.framework.utils.c.a().getPackageManager();
  
  private a() {}
  
  public static a a()
  {
    if (a == null) {}
    try
    {
      if (a == null) {
        a = new a();
      }
      return a;
    }
    finally {}
  }
  
  private com.ludashi.superclean.work.model.a a(ApplicationInfo paramApplicationInfo)
  {
    com.ludashi.superclean.work.model.a localA = new com.ludashi.superclean.work.model.a();
    localA.a = paramApplicationInfo.loadLabel(this.f).toString();
    localA.b = paramApplicationInfo.packageName;
    localA.c = paramApplicationInfo.loadIcon(this.f);
    return localA;
  }
  
  /* Error */
  private List<ApplicationInfo> a(int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: iload_1
    //   4: ifeq +10 -> 14
    //   7: iload_1
    //   8: sipush 8192
    //   11: if_icmpne +188 -> 199
    //   14: aload_0
    //   15: getfield 28	com/ludashi/superclean/work/b/a:b	Ljava/util/List;
    //   18: ifnull +15 -> 33
    //   21: aload_0
    //   22: getfield 28	com/ludashi/superclean/work/b/a:b	Ljava/util/List;
    //   25: invokeinterface 95 1 0
    //   30: ifne +152 -> 182
    //   33: aload_0
    //   34: new 97	java/util/ArrayList
    //   37: dup
    //   38: invokespecial 98	java/util/ArrayList:<init>	()V
    //   41: putfield 28	com/ludashi/superclean/work/b/a:b	Ljava/util/List;
    //   44: aload_0
    //   45: iload_1
    //   46: iload_2
    //   47: invokespecial 100	com/ludashi/superclean/work/b/a:b	(II)Ljava/util/List;
    //   50: astore_3
    //   51: aload_3
    //   52: ifnull +95 -> 147
    //   55: aload_3
    //   56: invokeinterface 95 1 0
    //   61: ifle +86 -> 147
    //   64: aload_3
    //   65: invokeinterface 104 1 0
    //   70: astore_3
    //   71: aload_3
    //   72: invokeinterface 110 1 0
    //   77: ifeq +88 -> 165
    //   80: aload_3
    //   81: invokeinterface 114 1 0
    //   86: checkcast 116	android/content/pm/PackageInfo
    //   89: astore 4
    //   91: aload_0
    //   92: getfield 28	com/ludashi/superclean/work/b/a:b	Ljava/util/List;
    //   95: aload 4
    //   97: getfield 120	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   100: invokeinterface 124 2 0
    //   105: pop
    //   106: goto -35 -> 71
    //   109: astore_3
    //   110: ldc 2
    //   112: monitorexit
    //   113: aload_3
    //   114: athrow
    //   115: astore_3
    //   116: aload_0
    //   117: getfield 28	com/ludashi/superclean/work/b/a:b	Ljava/util/List;
    //   120: ifnull +93 -> 213
    //   123: aload_0
    //   124: getfield 28	com/ludashi/superclean/work/b/a:b	Ljava/util/List;
    //   127: invokeinterface 95 1 0
    //   132: ifle +81 -> 213
    //   135: new 97	java/util/ArrayList
    //   138: dup
    //   139: aload_0
    //   140: getfield 28	com/ludashi/superclean/work/b/a:b	Ljava/util/List;
    //   143: invokespecial 127	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   146: areturn
    //   147: aload_0
    //   148: getfield 28	com/ludashi/superclean/work/b/a:b	Ljava/util/List;
    //   151: aload_0
    //   152: getfield 50	com/ludashi/superclean/work/b/a:f	Landroid/content/pm/PackageManager;
    //   155: iconst_0
    //   156: invokevirtual 133	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   159: invokeinterface 137 2 0
    //   164: pop
    //   165: new 97	java/util/ArrayList
    //   168: dup
    //   169: aload_0
    //   170: getfield 28	com/ludashi/superclean/work/b/a:b	Ljava/util/List;
    //   173: invokespecial 127	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   176: astore_3
    //   177: ldc 2
    //   179: monitorexit
    //   180: aload_3
    //   181: areturn
    //   182: aload_0
    //   183: getfield 37	com/ludashi/superclean/work/b/a:e	Ljava/util/Queue;
    //   186: iload_2
    //   187: invokestatic 143	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   190: invokeinterface 148 2 0
    //   195: pop
    //   196: goto -31 -> 165
    //   199: aload_0
    //   200: getfield 50	com/ludashi/superclean/work/b/a:f	Landroid/content/pm/PackageManager;
    //   203: iload_1
    //   204: invokevirtual 133	android/content/pm/PackageManager:getInstalledApplications	(I)Ljava/util/List;
    //   207: astore_3
    //   208: ldc 2
    //   210: monitorexit
    //   211: aload_3
    //   212: areturn
    //   213: new 97	java/util/ArrayList
    //   216: dup
    //   217: invokespecial 98	java/util/ArrayList:<init>	()V
    //   220: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	221	0	this	a
    //   0	221	1	paramInt1	int
    //   0	221	2	paramInt2	int
    //   50	31	3	localObject1	Object
    //   109	5	3	localObject2	Object
    //   115	1	3	localThrowable	Throwable
    //   176	36	3	localObject3	Object
    //   89	7	4	localPackageInfo	PackageInfo
    // Exception table:
    //   from	to	target	type
    //   14	33	109	finally
    //   33	51	109	finally
    //   55	71	109	finally
    //   71	106	109	finally
    //   110	113	109	finally
    //   147	165	109	finally
    //   165	180	109	finally
    //   182	196	109	finally
    //   199	211	109	finally
    //   0	3	115	java/lang/Throwable
    //   113	115	115	java/lang/Throwable
  }
  
  private List<com.ludashi.superclean.work.model.a> a(List<com.ludashi.superclean.work.model.a> paramList)
  {
    if ((paramList == null) || (paramList.isEmpty())) {
      return paramList;
    }
    a localA = new a();
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      com.ludashi.superclean.work.model.a localA1 = (com.ludashi.superclean.work.model.a)paramList.next();
      if (localA1.d) {
        localArrayList2.add(localA1);
      } else {
        localArrayList3.add(localA1);
      }
    }
    Collections.sort(localArrayList2, localA);
    Collections.sort(localArrayList3, localA);
    localArrayList1.addAll(localArrayList3);
    localArrayList1.addAll(localArrayList2);
    return localArrayList1;
  }
  
  private void a(List<com.ludashi.superclean.work.model.a> paramList, Set<String> paramSet, com.ludashi.superclean.work.model.a paramA)
  {
    String str = c.b();
    if ((paramSet != null) && (paramSet.contains(paramA.b)))
    {
      if (!new HashSet(Arrays.asList(str.split("#"))).contains(paramA.b)) {
        c.a(str + "#" + paramA.b);
      }
      paramList.add(0, paramA);
      paramA.d = true;
      return;
    }
    paramList.add(paramA);
  }
  
  private List<PackageInfo> b(int paramInt1, int paramInt2)
  {
    this.e.offer(Integer.valueOf(paramInt2));
    if (this.e.size() > 100) {
      this.e.poll();
    }
    try
    {
      if ((paramInt1 == 0) || (paramInt1 == 8192)) {}
      try
      {
        if ((this.c == null) || (this.c.size() == 0))
        {
          this.c = new ArrayList();
          this.c.addAll(this.f.getInstalledPackages(0));
        }
        localObject1 = new ArrayList(this.c);
        return localObject1;
      }
      finally {}
      Object localObject1 = this.f.getInstalledPackages(paramInt1);
      return localObject1;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      if ((this.c != null) && (this.c.size() > 0)) {
        return new ArrayList(this.c);
      }
    }
    return new ArrayList();
  }
  
  private List<com.ludashi.superclean.work.model.a> d()
  {
    long l = System.currentTimeMillis();
    ArrayList localArrayList = new ArrayList();
    Object localObject2 = a(0, 141);
    Object localObject1 = c.b();
    if (TextUtils.isEmpty((CharSequence)localObject1))
    {
      localObject1 = b.a();
      c.a(b.c());
    }
    for (;;)
    {
      localObject2 = ((List)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject2).next();
        if (this.f.getLaunchIntentForPackage(localApplicationInfo.packageName) != null) {
          a(localArrayList, (Set)localObject1, a(localApplicationInfo));
        }
      }
      localObject1 = new HashSet(Arrays.asList(((String)localObject1).split("#")));
    }
    e.b("AppListManager", new Object[] { "getPackageData method waste of time:" + (System.currentTimeMillis() - l) });
    return a(localArrayList);
  }
  
  public Drawable a(String paramString)
  {
    if ((TextUtils.isEmpty(paramString)) || (this.b == null) || (this.b.size() == 0)) {
      return null;
    }
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (paramString.equals(localApplicationInfo.packageName)) {
        return localApplicationInfo.loadIcon(this.f);
      }
    }
    return null;
  }
  
  public void b()
  {
    m.b(new Runnable()
    {
      public void run()
      {
        a.a(a.this, 0, 141);
      }
    });
  }
  
  public List<com.ludashi.superclean.work.model.a> c()
  {
    if ((this.d == null) || (this.d.isEmpty())) {}
    for (this.d = d();; this.d = a(this.d)) {
      return this.d;
    }
  }
  
  class a
    implements Comparator<com.ludashi.superclean.work.model.a>
  {
    a() {}
    
    public int a(com.ludashi.superclean.work.model.a paramA1, com.ludashi.superclean.work.model.a paramA2)
    {
      return paramA1.a(paramA1.a).compareTo(paramA2.a(paramA2.a));
    }
  }
}
