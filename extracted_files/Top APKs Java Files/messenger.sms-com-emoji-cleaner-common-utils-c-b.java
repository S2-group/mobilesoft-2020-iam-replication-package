package com.emoji.cleaner.common.utils.c;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.util.Log;
import com.emoji.cleaner.clean.entitiy.AppProcessInfo;
import com.emoji.cleaner.clean.service.ProcessTaskService;
import com.emoji.cleaner.clean.service.ProcessTaskService.b;
import com.emoji.cleaner.clean.service.ProcessTaskService.c;
import com.emoji.cleaner.clean.service.ProcessTaskService.d;
import com.emoji.cleaner.common.utils.d.e;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class b
{
  private WeakReference<Context> a;
  private a b;
  private ProcessTaskService c;
  private boolean d;
  private boolean e;
  private boolean f;
  private ServiceConnection g = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
    {
      b.a(b.this, ((ProcessTaskService.d)paramAnonymousIBinder).a());
      b.d(b.this).a(new b.b(b.this, null));
      b.d(b.this).a(0);
      if (!b.d(b.this).b()) {
        b.d(b.this).a();
      }
    }
    
    public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
    {
      b.d(b.this).a(null);
      b.a(b.this, null);
    }
  };
  
  public b(Context paramContext)
  {
    this.a = new WeakReference(paramContext);
    this.b = new a(paramContext);
  }
  
  public void a()
  {
    this.b.removeCallbacksAndMessages(null);
    d();
  }
  
  public void a(d paramD)
  {
    this.b.a(paramD);
    this.d = true;
    b();
    c();
  }
  
  public void a(List<com.emoji.cleaner.common.utils.c.b.a> paramList)
  {
    new Thread(new c(paramList)).start();
  }
  
  public void a(List<com.emoji.cleaner.clean.entitiy.a> paramList, c paramC)
  {
    if ((paramList == null) || (paramList.isEmpty())) {
      return;
    }
    this.b.a(paramC);
    paramC = new ArrayList();
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      com.emoji.cleaner.clean.entitiy.a localA = (com.emoji.cleaner.clean.entitiy.a)paramList.next();
      if ((localA instanceof com.emoji.cleaner.common.utils.c.b.a)) {
        paramC.add((com.emoji.cleaner.common.utils.c.b.a)localA);
      } else if ((localA instanceof com.emoji.cleaner.common.utils.c.a.a)) {
        localArrayList.add((com.emoji.cleaner.common.utils.c.a.a)localA);
      }
    }
    a(paramC);
    b(localArrayList);
  }
  
  public void b()
  {
    this.b.sendEmptyMessage(0);
    new Thread(new d(null)).start();
  }
  
  public void b(List<com.emoji.cleaner.common.utils.c.a.a> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      com.emoji.cleaner.common.utils.c.a.a localA = (com.emoji.cleaner.common.utils.c.a.a)paramList.next();
      AppProcessInfo localAppProcessInfo = new AppProcessInfo();
      localAppProcessInfo.a = localA.c();
      localAppProcessInfo.b = localA.b();
      localAppProcessInfo.f = localA.a();
      localArrayList.add(localAppProcessInfo);
    }
    this.c.a(new a(null));
    this.c.a(localArrayList);
  }
  
  public void c()
  {
    Context localContext = (Context)this.a.get();
    if (localContext == null) {
      return;
    }
    localContext.bindService(new Intent(localContext, ProcessTaskService.class), this.g, 1);
  }
  
  public void d()
  {
    Context localContext = (Context)this.a.get();
    if (localContext == null) {
      return;
    }
    localContext.unbindService(this.g);
  }
  
  public boolean e()
  {
    return this.d;
  }
  
  private class a
    implements ProcessTaskService.b
  {
    private a() {}
    
    public void a(Context paramContext)
    {
      b.b(b.this).sendEmptyMessage(10);
    }
    
    public void a(Context paramContext, long paramLong)
    {
      b.b(b.this).sendMessage(b.b(b.this).obtainMessage(11, Long.valueOf(paramLong)));
    }
  }
  
  private class b
    implements ProcessTaskService.c
  {
    private b() {}
    
    public void a(Context paramContext)
    {
      b.b(b.this).sendEmptyMessage(6);
    }
    
    public void a(Context paramContext, int paramInt1, int paramInt2)
    {
      b.b(b.this).sendMessage(b.b(b.this).obtainMessage(7, 0, paramInt2));
    }
    
    public void a(Context paramContext, long paramLong)
    {
      b.b(b.this).sendMessage(b.b(b.this).obtainMessage(8, Long.valueOf(paramLong)));
    }
    
    public void a(Context paramContext, List<AppProcessInfo> paramList)
    {
      b.c(b.this, true);
      if (b.e(b.this)) {
        b.b(b.this, false);
      }
      if (paramList != null)
      {
        ArrayList localArrayList = new ArrayList();
        paramList = paramList.iterator();
        while (paramList.hasNext())
        {
          AppProcessInfo localAppProcessInfo = (AppProcessInfo)paramList.next();
          com.emoji.cleaner.common.utils.c.a.a localA = new com.emoji.cleaner.common.utils.c.a.a();
          localA.b(localAppProcessInfo.a);
          localA.a(localAppProcessInfo.f);
          localA.a(com.emoji.cleaner.common.utils.a.a(paramContext, localAppProcessInfo.b));
          localA.a(localAppProcessInfo.b);
          localArrayList.add(localA);
        }
        b.b(b.this).sendMessage(b.b(b.this).obtainMessage(9, localArrayList));
      }
    }
  }
  
  private class c
    implements Runnable
  {
    private List<com.emoji.cleaner.common.utils.c.b.a> b;
    
    public c()
    {
      Object localObject;
      this.b = localObject;
    }
    
    public void run()
    {
      Context localContext = (Context)b.a(b.this).get();
      if (localContext == null) {
        return;
      }
      b.b(b.this).sendEmptyMessage(4);
      Iterator localIterator = this.b.iterator();
      while (localIterator.hasNext())
      {
        com.emoji.cleaner.common.utils.c.b.a localA = (com.emoji.cleaner.common.utils.c.b.a)localIterator.next();
        com.emoji.cleaner.common.utils.c.b.b.a(localContext).a(localA);
      }
      long l = com.emoji.cleaner.common.utils.c.b.b.a(localContext).a();
      b.b(b.this).sendMessage(b.b(b.this).obtainMessage(5, Long.valueOf(l)));
    }
  }
  
  private class d
    implements Runnable
  {
    private int b = 0;
    
    private d() {}
    
    public void run()
    {
      Context localContext = (Context)b.a(b.this).get();
      if (localContext == null) {
        return;
      }
      for (;;)
      {
        final CountDownLatch localCountDownLatch;
        try
        {
          final List localList = localContext.getPackageManager().getInstalledApplications(128);
          b.b(b.this).sendMessage(b.b(b.this).obtainMessage(1, 0, localList.size()));
          localCountDownLatch = new CountDownLatch(localList.size());
          final ArrayList localArrayList = new ArrayList();
          try
          {
            Iterator localIterator = localList.iterator();
            if (!localIterator.hasNext()) {
              break label210;
            }
            final ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
            com.emoji.cleaner.common.utils.c.b.b.a(localContext).a(localApplicationInfo.packageName, new com.emoji.cleaner.common.utils.c.b.b.a()
            {
              public void a(com.emoji.cleaner.common.utils.c.b.a arg1)
              {
                synchronized (localArrayList)
                {
                  b.b(b.this).sendMessage(b.b(b.this).obtainMessage(1, b.d.a(b.d.this), localList.size()));
                  if ((???.a() > 0L) && (!e.a(localApplicationInfo.packageName)))
                  {
                    b.b(b.this).sendMessage(b.b(b.this).obtainMessage(2, Long.valueOf(???.a())));
                    localArrayList.add(???);
                  }
                  synchronized (localCountDownLatch)
                  {
                    localCountDownLatch.countDown();
                    return;
                  }
                }
              }
            });
            continue;
            b.a(b.this, true);
          }
          catch (InterruptedException localInterruptedException)
          {
            localInterruptedException.printStackTrace();
          }
          b.b(b.this).sendMessage(b.b(b.this).obtainMessage(3, localArrayList));
          if (!b.c(b.this)) {
            break;
          }
          b.b(b.this, false);
          return;
        }
        catch (Exception localException)
        {
          Log.e("JunkManager", localException.getMessage());
          return;
        }
        label210:
        localCountDownLatch.await();
      }
    }
  }
}
