package com.cleanmaster.security.scan.engine;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import com.cleanmaster.security.scan.AppExploitInfo;
import com.cleanmaster.security.scan.c;
import com.cleanmaster.security.scan.e;
import com.cleanmaster.security.scan.g;
import com.cleanmaster.security.scan.g.a;
import com.cleanmaster.util.OpLog;
import java.util.List;

public final class d
  extends Thread
{
  public ISecurityScanCallback a;
  final Object b = new Object();
  public volatile boolean c;
  public long d;
  public volatile boolean e;
  e f;
  c g;
  g h;
  private Context i;
  private long j;
  private long k;
  private volatile boolean l;
  private volatile boolean m;
  private volatile boolean n;
  private final int o;
  private b p = new b(this);
  private a q = new a()
  {
    public final void a()
    {
      d.a(d.this, System.currentTimeMillis());
      d.a("onApkLeakScanStart");
      if (d.a(d.this)) {}
      for (;;)
      {
        return;
        try
        {
          if (d.b(d.this) != null)
          {
            d.b(d.this).d();
            return;
          }
        }
        catch (RemoteException localRemoteException)
        {
          localRemoteException.printStackTrace();
        }
      }
    }
    
    public final void a(AppExploitInfo paramAnonymousAppExploitInfo) {}
    
    public final void a(List<AppExploitInfo> paramAnonymousList)
    {
      if (d.a(d.this)) {}
      for (;;)
      {
        return;
        try
        {
          if (d.b(d.this) != null)
          {
            d.b(d.this).a(paramAnonymousList);
            return;
          }
        }
        catch (RemoteException paramAnonymousList)
        {
          paramAnonymousList.printStackTrace();
        }
      }
    }
    
    public final void b()
    {
      d.a("onApkLeakScanDone : " + (System.currentTimeMillis() - d.f(d.this)) + " ms");
      if (d.a(d.this)) {
        return;
      }
      try
      {
        if (d.b(d.this) != null) {
          d.b(d.this).e();
        }
        d.g(d.this);
        d.e(d.this);
        com.cleanmaster.security.scan.b.a().a = false;
        return;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          localRemoteException.printStackTrace();
        }
      }
    }
  };
  private g.a r = new g.a(this);
  
  public d(Context paramContext, ISecurityScanCallback paramISecurityScanCallback, int paramInt)
  {
    super("SecurityScanThread");
    if ((paramContext == null) || (paramISecurityScanCallback == null)) {
      throw new IllegalArgumentException("The context or callback of the SecurityScanThread can't be null.");
    }
    this.o = paramInt;
    this.i = paramContext;
    this.a = paramISecurityScanCallback;
  }
  
  public final void run()
  {
    synchronized (this.b)
    {
      if (!this.c)
      {
        this.j = System.currentTimeMillis();
        OpLog.d("Security", "onScanStart");
        int i1 = this.o;
        if (i1 != 7) {}
      }
      try
      {
        if (this.a != null) {
          this.a.a();
        }
        Object localObject1 = null;
        try
        {
          List localList = this.i.getPackageManager().getInstalledPackages(0);
          localObject1 = localList;
        }
        catch (Exception localException)
        {
          boolean bool;
          for (;;) {}
        }
        bool = com.cleanmaster.configmanager.d.a(this.i).br();
        if ((this.o & 0x2) != 0)
        {
          this.f = new e(this.i, this.p, localObject1);
          this.f.start();
        }
        if ((this.o & 0x1) != 0)
        {
          this.g = new c(this.i, this.q, localObject1, bool);
          this.g.start();
        }
        if ((this.o & 0x4) != 0)
        {
          this.h = new g(this.r);
          this.h.start();
        }
        return;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;)
        {
          localRemoteException.printStackTrace();
        }
      }
    }
  }
}
