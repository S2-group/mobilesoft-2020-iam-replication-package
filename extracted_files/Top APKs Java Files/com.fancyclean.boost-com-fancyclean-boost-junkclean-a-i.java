package com.fancyclean.boost.junkclean.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.SparseArray;
import com.fancyclean.boost.common.NativeLibHelper;
import com.fancyclean.boost.junkclean.model.c;
import com.fancyclean.boost.junkclean.model.d;
import com.google.firebase.perf.a;
import com.google.firebase.perf.metrics.Trace;
import com.thinkyeah.common.q;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class i
  implements g.a
{
  private static final q a = q.a(i.class);
  private volatile boolean b = false;
  private Context c;
  private k d;
  private final Set<String> e;
  private Set<String> f;
  private SparseArray<c> g;
  private a h;
  private final b.a i = new b.a()
  {
    public void a(d paramAnonymousD)
    {
      if (paramAnonymousD.b != 1) {
        return;
      }
      if (i.a(i.this).contains(paramAnonymousD.f)) {
        synchronized (i.b(i.this))
        {
          i.b(i.this).add(paramAnonymousD.f);
          return;
        }
      }
    }
    
    public boolean a()
    {
      return i.c(i.this);
    }
  };
  
  i(Context paramContext, SparseArray<c> paramSparseArray, Set<String> paramSet)
  {
    this.c = paramContext.getApplicationContext();
    this.g = paramSparseArray;
    this.e = paramSet;
    this.d = k.a(paramContext);
  }
  
  private a a(List<d> paramList)
  {
    a localA = new a();
    Object localObject2 = new HashMap();
    HashMap localHashMap = new HashMap();
    paramList = paramList.iterator();
    Object localObject4;
    while (paramList.hasNext())
    {
      localObject1 = (d)paramList.next();
      if (((d)localObject1).b == 2)
      {
        if (!((Map)localObject2).containsKey(((d)localObject1).c)) {
          ((Map)localObject2).put(((d)localObject1).c, new ArrayList(1));
        }
        ((List)((Map)localObject2).get(((d)localObject1).c)).add(localObject1);
      }
      else if (((d)localObject1).b == 1)
      {
        if (!localHashMap.containsKey(((d)localObject1).c)) {
          localHashMap.put(((d)localObject1).c, new ArrayList(1));
        }
        ((List)localHashMap.get(((d)localObject1).c)).add(localObject1);
      }
      else if (((d)localObject1).b == 3)
      {
        ((d)localObject1).h = 3;
        localA.c.add(Collections.singletonList(localObject1));
      }
      else if (((d)localObject1).b == 4)
      {
        ((d)localObject1).h = 4;
        localA.d.add(Collections.singletonList(localObject1));
      }
      else
      {
        localObject3 = a;
        localObject4 = new StringBuilder();
        ((StringBuilder)localObject4).append("Unexpected type: ");
        ((StringBuilder)localObject4).append(((d)localObject1).b);
        ((q)localObject3).e(((StringBuilder)localObject4).toString());
      }
    }
    paramList = new HashMap();
    Object localObject1 = new HashMap();
    Object localObject3 = ((Map)localObject2).entrySet().iterator();
    Object localObject5;
    Object localObject6;
    int j;
    while (((Iterator)localObject3).hasNext())
    {
      localObject4 = (List)((Map.Entry)((Iterator)localObject3).next()).getValue();
      localObject5 = ((List)localObject4).iterator();
      while (((Iterator)localObject5).hasNext())
      {
        localObject6 = (d)((Iterator)localObject5).next();
        if (this.f.contains(((d)localObject6).f))
        {
          j = 1;
          break label426;
        }
      }
      j = 0;
      label426:
      if (j == 0)
      {
        localObject5 = ((d)((List)localObject4).get(0)).f;
        if (!paramList.containsKey(localObject5)) {
          paramList.put(localObject5, new ArrayList(1));
        }
        ((d)((List)localObject4).get(0)).h = 2;
        ((List)paramList.get(localObject5)).add(((List)localObject4).get(0));
      }
    }
    ((Map)localObject2).clear();
    localObject2 = localHashMap.entrySet().iterator();
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (List)((Map.Entry)((Iterator)localObject2).next()).getValue();
      localObject5 = ((List)localObject3).iterator();
      while (((Iterator)localObject5).hasNext())
      {
        localObject4 = (d)((Iterator)localObject5).next();
        if (this.f.contains(((d)localObject4).f))
        {
          localObject5 = ((d)localObject4).f;
          if (!((Map)localObject1).containsKey(localObject5)) {
            ((Map)localObject1).put(localObject5, new ArrayList(1));
          }
          ((d)localObject4).h = 1;
          ((List)((Map)localObject1).get(localObject5)).add(localObject4);
          j = 1;
          break label686;
        }
      }
      j = 0;
      label686:
      if (j == 0)
      {
        localObject4 = ((d)((List)localObject3).get(0)).c;
        localObject5 = paramList.entrySet().iterator();
        int k;
        for (j = 0;; j = k)
        {
          k = j;
          if (!((Iterator)localObject5).hasNext()) {
            break;
          }
          localObject6 = ((List)((Map.Entry)((Iterator)localObject5).next()).getValue()).iterator();
          for (;;)
          {
            k = j;
            if (!((Iterator)localObject6).hasNext()) {
              break;
            }
            d localD = (d)((Iterator)localObject6).next();
            if (((String)localObject4).length() > localD.c.length())
            {
              localStringBuilder = new StringBuilder();
              localStringBuilder.append(localD.c);
              localStringBuilder.append("/");
              if (!((String)localObject4).startsWith(localStringBuilder.toString())) {}
            }
            else
            {
              while ((((String)localObject4).length() == localD.c.length()) && (((String)localObject4).equals(localD.c)))
              {
                StringBuilder localStringBuilder;
                k = 1;
                break;
              }
            }
          }
          if (k != 0) {
            break;
          }
        }
        if (k == 0)
        {
          localObject4 = ((d)((List)localObject3).get(0)).f;
          if (!paramList.containsKey(localObject4)) {
            paramList.put(localObject4, new ArrayList(1));
          }
          ((d)((List)localObject3).get(0)).h = 2;
          ((List)paramList.get(localObject4)).add(((List)localObject3).get(0));
        }
      }
    }
    localHashMap.clear();
    localA.a.addAll(paramList.values());
    localA.b.addAll(((Map)localObject1).values());
    return localA;
  }
  
  private void a(a paramA)
  {
    ExecutorService localExecutorService = Executors.newFixedThreadPool(Math.max(Runtime.getRuntime().availableProcessors() * 2 + 1, 5));
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(paramA.a);
    localArrayList.addAll(paramA.b);
    localArrayList.addAll(paramA.c);
    localArrayList.addAll(paramA.d);
    long l2 = localArrayList.size() / 10;
    long l1 = l2;
    if (localArrayList.size() % 10 != 0) {
      l1 = l2 + 1L;
    }
    int j = 0;
    while (j < l1)
    {
      paramA = this.c;
      int k = j * 10;
      localExecutorService.execute(new g(paramA, localArrayList.subList(k, Math.min(k + 10, localArrayList.size())), this.g, this));
      j += 1;
    }
    localExecutorService.shutdown();
    try
    {
      localExecutorService.awaitTermination(30L, TimeUnit.MINUTES);
      return;
    }
    catch (InterruptedException paramA)
    {
      a.a(paramA);
    }
  }
  
  private List<d> e()
  {
    long l2 = this.d.a();
    if (l2 <= 0L) {
      return null;
    }
    NativeLibHelper.a(this.c, "init");
    Object localObject2 = Environment.getExternalStorageDirectory().listFiles();
    if (localObject2 == null)
    {
      localObject1 = Environment.getExternalStorageState();
      localObject2 = a;
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("Environment.getExternalStorageDirectory().listFiles() is null, externalStorageState: ");
      ((StringBuilder)localObject3).append((String)localObject1);
      ((q)localObject2).e(((StringBuilder)localObject3).toString());
      return null;
    }
    Object localObject1 = new HashSet(localObject2.length);
    int k = localObject2.length;
    int j = 0;
    while (j < k)
    {
      ((Set)localObject1).add(localObject2[j].getName().toLowerCase());
      j += 1;
    }
    localObject2 = new ArrayList();
    Object localObject3 = Executors.newFixedThreadPool(Math.max(Runtime.getRuntime().availableProcessors() * 2 + 1, 5));
    long l1 = l2 / 100L;
    if (l2 % 100L != 0L) {
      l1 += 1L;
    }
    j = 0;
    while (j < l1)
    {
      ((ExecutorService)localObject3).execute(new b(this.c, j * 100, 100, (Set)localObject1, (List)localObject2, this.i));
      j += 1;
    }
    ((ExecutorService)localObject3).shutdown();
    try
    {
      ((ExecutorService)localObject3).awaitTermination(30L, TimeUnit.MINUTES);
      return localObject2;
    }
    catch (InterruptedException localInterruptedException)
    {
      a.a(localInterruptedException);
    }
    return localObject2;
  }
  
  private Set<String> f()
  {
    localHashSet = new HashSet();
    try
    {
      Iterator localIterator = this.c.getPackageManager().getInstalledPackages(0).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if ((localPackageInfo.applicationInfo.flags & 0x1) <= 0) {
          localHashSet.add(localPackageInfo.packageName);
        }
      }
      return localHashSet;
    }
    catch (Exception localException)
    {
      a.a(localException);
    }
  }
  
  public boolean a()
  {
    return this.b;
  }
  
  void b()
  {
    this.b = true;
  }
  
  void c()
  {
    this.f = f();
    List localList = e();
    if (localList != null)
    {
      if (localList.isEmpty()) {
        return;
      }
      if (this.b) {
        return;
      }
      this.h = a(localList);
      return;
    }
  }
  
  void d()
  {
    Trace localTrace = a.a("FindJunkWithPattern");
    if (this.h != null) {
      a(this.h);
    }
    localTrace.stop();
  }
  
  static class a
  {
    List<List<d>> a = new ArrayList();
    List<List<d>> b = new ArrayList();
    List<List<d>> c = new ArrayList();
    List<List<d>> d = new ArrayList();
    
    a() {}
  }
}
