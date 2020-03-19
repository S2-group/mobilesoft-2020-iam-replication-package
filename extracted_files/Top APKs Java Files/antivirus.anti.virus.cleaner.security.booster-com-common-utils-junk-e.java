package com.common.utils.junk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Process;
import com.antivirus.anti.virus.entity.AppProcessInfo;
import com.antivirus.anti.virus.utils.d.b;
import com.antivirus.anti.virus.utils.d.c;
import com.common.utils.k;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class e
{
  private WeakReference<Context> a;
  private d b;
  private com.antivirus.anti.virus.utils.d c;
  private c d;
  private com.common.utils.junk.c.a e;
  private com.common.utils.junk.f.b f;
  private a g;
  
  public e(Context paramContext)
  {
    this.a = new WeakReference(paramContext);
    this.b = new d(paramContext);
    this.c = new com.antivirus.anti.virus.utils.d(paramContext, new e(null));
    this.d = new c((Context)this.a.get(), new c(null));
    this.e = new com.common.utils.junk.c.a((Context)this.a.get(), new b(null));
    this.f = new com.common.utils.junk.f.b((Context)this.a.get(), new f(null));
    this.g = new a((Context)this.a.get(), new a(null));
  }
  
  public void a()
  {
    this.b.removeCallbacksAndMessages(null);
    e();
    g();
    i();
    k();
  }
  
  public void a(g paramG)
  {
    this.b.a(paramG);
    b();
    c();
    d();
    f();
    h();
    j();
  }
  
  public void a(List<com.common.utils.junk.g.a> paramList)
  {
    new Thread(new g(paramList)).start();
  }
  
  public void a(List<com.antivirus.anti.virus.entity.a> paramList, f paramF)
  {
    if ((paramList == null) || (paramList.isEmpty())) {
      return;
    }
    this.b.a(paramF);
    paramF = new ArrayList();
    ArrayList localArrayList1 = new ArrayList();
    final ArrayList localArrayList2 = new ArrayList();
    final ArrayList localArrayList3 = new ArrayList();
    final ArrayList localArrayList4 = new ArrayList();
    final ArrayList localArrayList5 = new ArrayList();
    final ArrayList localArrayList6 = new ArrayList();
    final ArrayList localArrayList7 = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      com.antivirus.anti.virus.entity.a localA = (com.antivirus.anti.virus.entity.a)paramList.next();
      if ((localA instanceof com.common.utils.junk.g.a)) {
        paramF.add((com.common.utils.junk.g.a)localA);
      } else if ((localA instanceof com.common.utils.junk.e.a)) {
        localArrayList1.add((com.common.utils.junk.e.a)localA);
      } else if ((localA instanceof com.common.utils.junk.h.a)) {
        localArrayList2.add((com.common.utils.junk.h.a)localA);
      } else if ((localA instanceof com.common.utils.junk.b.a)) {
        localArrayList3.add((com.common.utils.junk.b.a)localA);
      } else if ((localA instanceof com.common.utils.junk.file.a)) {
        localArrayList4.add((com.common.utils.junk.file.a)localA);
      } else if ((localA instanceof com.common.utils.junk.f.a.a)) {
        localArrayList5.add((com.common.utils.junk.f.a.a)localA);
      } else if ((localA instanceof com.common.utils.junk.a.a.a)) {
        localArrayList6.add((com.common.utils.junk.a.a.a)localA);
      } else if ((localA instanceof com.common.utils.junk.c.a.a)) {
        localArrayList7.add((com.common.utils.junk.c.a.a)localA);
      }
    }
    a(paramF);
    b(localArrayList1);
    new Thread(new Runnable()
    {
      public void run()
      {
        Process.setThreadPriority(10);
        e.this.c(localArrayList2);
        e.this.d(localArrayList3);
        e.this.e(localArrayList4);
        e.this.f(localArrayList5);
        e.this.g(localArrayList6);
        e.this.h(localArrayList7);
      }
    }).start();
  }
  
  public void b()
  {
    this.b.sendEmptyMessage(0);
    new Thread(new h(null)).start();
  }
  
  public void b(List<com.common.utils.junk.e.a> paramList)
  {
    if ((Context)this.a.get() == null) {
      return;
    }
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      com.common.utils.junk.e.a localA = (com.common.utils.junk.e.a)paramList.next();
      AppProcessInfo localAppProcessInfo = new AppProcessInfo();
      localAppProcessInfo.a = localA.b();
      localAppProcessInfo.b = localA.e();
      localAppProcessInfo.f = localA.a();
      localArrayList.add(localAppProcessInfo);
    }
    this.c.a(localArrayList, new d(null));
  }
  
  public void c()
  {
    if ((Context)this.a.get() == null) {}
    while (this.c == null) {
      return;
    }
    this.c.a();
  }
  
  public void c(List<com.common.utils.junk.h.a> paramList)
  {
    if ((paramList != null) && (!paramList.isEmpty()))
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Object localObject = (com.common.utils.junk.h.a)paramList.next();
        try
        {
          localObject = new File(((com.common.utils.junk.h.a)localObject).e());
          if (!((File)localObject).isFile()) {
            break label74;
          }
          k.e((File)localObject);
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
        continue;
        label74:
        k.d(localException);
      }
    }
  }
  
  public void d()
  {
    if (this.d != null) {
      this.d.a();
    }
  }
  
  public void d(List<com.common.utils.junk.b.a> paramList)
  {
    if ((paramList != null) && (!paramList.isEmpty()))
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Object localObject = (com.common.utils.junk.b.a)paramList.next();
        try
        {
          localObject = new File(((com.common.utils.junk.b.a)localObject).e());
          if (!((File)localObject).isFile()) {
            break label74;
          }
          k.e((File)localObject);
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
        continue;
        label74:
        k.d(localException);
      }
    }
  }
  
  public void e()
  {
    if (this.d != null) {
      this.d.b();
    }
  }
  
  public void e(List<com.common.utils.junk.file.a> paramList)
  {
    if ((paramList != null) && (!paramList.isEmpty()))
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Object localObject = (com.common.utils.junk.file.a)paramList.next();
        try
        {
          localObject = new File(((com.common.utils.junk.file.a)localObject).e());
          if (!((File)localObject).isFile()) {
            break label74;
          }
          k.e((File)localObject);
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
        continue;
        label74:
        k.d(localException);
      }
    }
  }
  
  public void f()
  {
    if (this.e != null) {
      this.e.a();
    }
  }
  
  public void f(List<com.common.utils.junk.f.a.a> paramList)
  {
    if ((paramList != null) && (!paramList.isEmpty()))
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Object localObject1 = ((com.common.utils.junk.f.a.a)paramList.next()).f();
        if ((localObject1 != null) && (!((List)localObject1).isEmpty()))
        {
          localObject1 = ((List)localObject1).iterator();
          while (((Iterator)localObject1).hasNext())
          {
            Object localObject2 = (String)((Iterator)localObject1).next();
            try
            {
              localObject2 = new File(Environment.getExternalStorageDirectory(), (String)localObject2);
              if (!((File)localObject2).isFile()) {
                break label116;
              }
              k.e((File)localObject2);
            }
            catch (Exception localException)
            {
              localException.printStackTrace();
            }
            continue;
            label116:
            k.d(localException);
          }
        }
      }
    }
  }
  
  public void g()
  {
    if (this.e != null) {
      this.e.b();
    }
  }
  
  public void g(List<com.common.utils.junk.a.a.a> paramList)
  {
    if ((paramList != null) && (!paramList.isEmpty()))
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Object localObject = (com.common.utils.junk.a.a.a)paramList.next();
        try
        {
          localObject = new File(Environment.getExternalStorageDirectory(), ((com.common.utils.junk.a.a.a)localObject).e());
          if (!((File)localObject).isFile()) {
            break label77;
          }
          k.e((File)localObject);
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
        continue;
        label77:
        k.d(localException);
      }
    }
  }
  
  public void h()
  {
    if (this.f != null) {
      this.f.a();
    }
  }
  
  public void h(List<com.common.utils.junk.c.a.a> paramList)
  {
    if ((paramList != null) && (!paramList.isEmpty()))
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Iterator localIterator = ((com.common.utils.junk.c.a.a)paramList.next()).f().iterator();
        while (localIterator.hasNext())
        {
          Object localObject = (String)localIterator.next();
          try
          {
            localObject = new File(Environment.getExternalStorageDirectory(), (String)localObject);
            if (!((File)localObject).isFile()) {
              break label101;
            }
            k.e((File)localObject);
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
          continue;
          label101:
          k.d(localException);
        }
      }
    }
  }
  
  public void i()
  {
    if (this.f != null) {
      this.f.b();
    }
  }
  
  public void j()
  {
    if (this.g != null) {
      this.g.a();
    }
  }
  
  public void k()
  {
    if (this.g != null) {
      this.g.b();
    }
  }
  
  private class a
    implements a.a
  {
    private a() {}
    
    public void a()
    {
      e.b(e.this).sendEmptyMessage(23);
    }
    
    public void a(com.common.utils.junk.a.a.a paramA)
    {
      e.b(e.this).sendMessage(e.b(e.this).obtainMessage(24, paramA));
    }
    
    public void a(List<com.common.utils.junk.a.a.a> paramList)
    {
      e.b(e.this).sendMessage(e.b(e.this).obtainMessage(25, paramList));
    }
  }
  
  private class b
    implements com.common.utils.junk.c.a.a
  {
    private b() {}
    
    public void a()
    {
      e.b(e.this).sendEmptyMessage(17);
    }
    
    public void a(com.common.utils.junk.c.a.a paramA)
    {
      e.b(e.this).sendMessage(e.b(e.this).obtainMessage(18, paramA));
    }
    
    public void a(List<com.common.utils.junk.c.a.a> paramList)
    {
      ArrayList localArrayList = new ArrayList();
      localArrayList.addAll(paramList);
      e.b(e.this).sendMessage(e.b(e.this).obtainMessage(19, localArrayList));
    }
  }
  
  private class c
    implements c.b
  {
    private c() {}
    
    public void a()
    {
      e.b(e.this).sendEmptyMessage(12);
    }
    
    public void a(com.common.utils.junk.b.a paramA)
    {
      e.b(e.this).sendMessage(e.b(e.this).obtainMessage(15, paramA));
    }
    
    public void a(com.common.utils.junk.file.a paramA)
    {
      e.b(e.this).sendMessage(e.b(e.this).obtainMessage(16, paramA));
    }
    
    public void a(com.common.utils.junk.h.a paramA)
    {
      e.b(e.this).sendMessage(e.b(e.this).obtainMessage(14, paramA));
    }
    
    public void a(List<com.common.utils.junk.b.a> paramList, List<com.common.utils.junk.h.a> paramList1, List<com.common.utils.junk.file.a> paramList2)
    {
      ArrayList localArrayList = new ArrayList();
      localArrayList.addAll(paramList);
      e.b(e.this).a(localArrayList);
      paramList = new ArrayList();
      paramList.addAll(paramList1);
      e.b(e.this).b(paramList);
      paramList = new ArrayList();
      paramList.addAll(paramList2);
      e.b(e.this).c(paramList);
      e.b(e.this).sendEmptyMessage(13);
    }
  }
  
  private class d
    implements d.b
  {
    private d() {}
  }
  
  private class e
    implements d.c
  {
    private e() {}
    
    public void a(Context paramContext)
    {
      e.b(e.this).sendEmptyMessage(6);
    }
    
    public void a(Context paramContext, int paramInt1, int paramInt2)
    {
      e.b(e.this).sendMessage(e.b(e.this).obtainMessage(7, 0, paramInt2));
    }
    
    public void a(Context paramContext, long paramLong)
    {
      e.b(e.this).sendMessage(e.b(e.this).obtainMessage(8, Long.valueOf(paramLong)));
    }
    
    public void a(Context paramContext, List<AppProcessInfo> paramList)
    {
      if (paramList != null)
      {
        paramContext = new ArrayList();
        paramList = paramList.iterator();
        while (paramList.hasNext())
        {
          AppProcessInfo localAppProcessInfo = (AppProcessInfo)paramList.next();
          com.common.utils.junk.e.a localA = new com.common.utils.junk.e.a();
          localA.a(localAppProcessInfo.a);
          localA.a(localAppProcessInfo.f);
          localA.b(localAppProcessInfo.b);
          paramContext.add(localA);
        }
        e.b(e.this).sendMessage(e.b(e.this).obtainMessage(9, paramContext));
      }
    }
  }
  
  private class f
    implements com.common.utils.junk.f.b.a
  {
    private f() {}
    
    public void a()
    {
      e.b(e.this).sendEmptyMessage(20);
    }
    
    public void a(com.common.utils.junk.f.a.a paramA)
    {
      e.b(e.this).sendMessage(e.b(e.this).obtainMessage(21, paramA));
    }
    
    public void a(List<com.common.utils.junk.f.a.a> paramList)
    {
      e.b(e.this).sendMessage(e.b(e.this).obtainMessage(22, paramList));
    }
  }
  
  private class g
    implements Runnable
  {
    private List<com.common.utils.junk.g.a> b;
    
    public g()
    {
      Object localObject;
      this.b = localObject;
    }
    
    public void run()
    {
      Process.setThreadPriority(10);
      Context localContext = (Context)e.a(e.this).get();
      if (localContext == null) {
        return;
      }
      e.b(e.this).sendEmptyMessage(4);
      Iterator localIterator = this.b.iterator();
      while (localIterator.hasNext())
      {
        com.common.utils.junk.g.a localA = (com.common.utils.junk.g.a)localIterator.next();
        com.common.utils.junk.g.b.a(localContext).a(localA);
      }
      long l = com.common.utils.junk.g.b.a(localContext).a();
      e.b(e.this).sendMessage(e.b(e.this).obtainMessage(5, Long.valueOf(l)));
    }
  }
  
  private class h
    implements Runnable
  {
    private int b = 0;
    
    private h() {}
    
    public void run()
    {
      Process.setThreadPriority(10);
      Context localContext = (Context)e.a(e.this).get();
      if (localContext == null) {
        return;
      }
      final List localList = localContext.getPackageManager().getInstalledApplications(128);
      e.b(e.this).sendMessage(e.b(e.this).obtainMessage(1, 0, localList.size()));
      final CountDownLatch localCountDownLatch = new CountDownLatch(localList.size());
      final ArrayList localArrayList = new ArrayList();
      try
      {
        Iterator localIterator = localList.iterator();
        for (;;)
        {
          if (localIterator.hasNext())
          {
            final ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
            if (!com.common.utils.a.f.a(localContext, localApplicationInfo.packageName))
            {
              com.common.utils.junk.g.b.a(localContext).a(localApplicationInfo.packageName, new com.common.utils.junk.g.b.a()
              {
                public void a(com.common.utils.junk.g.a arg1)
                {
                  synchronized (localArrayList)
                  {
                    e.b(e.this).sendMessage(e.b(e.this).obtainMessage(1, e.h.a(e.h.this), localList.size()));
                    if ((???.a() > 0L) && (!com.common.utils.a.f.a(localApplicationInfo.packageName)))
                    {
                      e.b(e.this).sendMessage(e.b(e.this).obtainMessage(2, Long.valueOf(???.a())));
                      localArrayList.add(???);
                    }
                    synchronized (localCountDownLatch)
                    {
                      localCountDownLatch.countDown();
                      return;
                    }
                  }
                }
                
                public void a(Exception arg1)
                {
                  synchronized (localCountDownLatch)
                  {
                    localCountDownLatch.countDown();
                    return;
                  }
                }
              });
              continue;
              e.b(e.this).sendMessage(e.b(e.this).obtainMessage(3, localArrayList));
            }
          }
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException.printStackTrace();
      }
      for (;;)
      {
        return;
        try
        {
          localInterruptedException.countDown();
          break;
        }
        finally {}
        localInterruptedException.await();
      }
    }
  }
}
