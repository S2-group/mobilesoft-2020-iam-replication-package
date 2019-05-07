package com.lbe.parallel;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Handler;
import android.os.Message;
import com.lbe.parallel.install.AppInstallProvider.a;
import com.lbe.parallel.install.a;
import com.lbe.parallel.model.AppInstallInfo;
import com.lbe.parallel.model.EmptyPackageInfo.BrowserPackageInfo;
import com.lbe.parallel.model.InitModel;
import com.lbe.parallel.model.PackageData;
import com.lbe.parallel.model.PackageManagerDied;
import com.lbe.parallel.ui.install.c;
import com.lbe.parallel.utility.ab;
import com.lbe.parallel.utility.m;
import com.lbe.parallel.utility.u;
import com.lbe.parallel.utility.w;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public final class kg
  extends c<InitModel>
{
  private boolean d = true;
  private boolean e = false;
  private boolean f = false;
  private Map<String, u<PackageInfo, Boolean>> g;
  private boolean h = false;
  private AtomicBoolean i = new AtomicBoolean(true);
  private Handler j = new Handler()
  {
    public final void handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what == 999)
      {
        kg.a(kg.this).removeMessages(999);
        if (kg.b(kg.this).get()) {
          kg.this.r();
        }
      }
      else
      {
        return;
      }
      kg.a(kg.this).sendEmptyMessageDelayed(999, 500L);
    }
  };
  private ld k = new ld()
  {
    public final void a(int paramAnonymousInt, String paramAnonymousString, boolean paramAnonymousBoolean)
    {
      kg.this.a(true);
    }
    
    public final void a(String paramAnonymousString)
    {
      kg.this.a(true);
    }
    
    public final void a(String paramAnonymousString, boolean paramAnonymousBoolean)
    {
      kg.this.a(true);
    }
    
    public final void b(int paramAnonymousInt, String paramAnonymousString, boolean paramAnonymousBoolean)
    {
      kg.this.a(true);
    }
    
    public final void b(String paramAnonymousString)
    {
      kg.this.a(true);
    }
    
    public final void c(String paramAnonymousString)
    {
      kg.this.a(true);
    }
    
    public final void d(String paramAnonymousString)
    {
      kg.this.a(true);
    }
  };
  
  public kg(Context paramContext)
  {
    super(paramContext, AppInstallProvider.a.a);
  }
  
  public kg(Context paramContext, boolean paramBoolean)
  {
    super(paramContext, AppInstallProvider.a.a);
    this.e = paramBoolean;
  }
  
  private void b(boolean paramBoolean)
  {
    this.i.set(paramBoolean);
  }
  
  private InitModel v()
  {
    int m = 0;
    b(false);
    ff.a(e());
    for (;;)
    {
      Map localMap2;
      ArrayList localArrayList;
      boolean bool1;
      String str;
      synchronized (this.k)
      {
        if (!this.h)
        {
          fn.a(e()).b(this.k);
          this.h = true;
        }
        InitModel localInitModel1 = new InitModel();
        if (this.d)
        {
          this.g = w();
          this.d = false;
        }
        localInitModel1.setAllPackages(this.g);
        Map localMap1 = this.g;
        localMap2 = f.a.r();
        ??? = a.a().c();
        if (??? != null) {
          m = ((List)???).size();
        }
        localArrayList = new ArrayList(m);
        m = ab.a().b("homepage_launch_count");
        bool1 = localMap2.containsKey("com.lbe.parallel.browser");
        if ((m > 1) || (this.f))
        {
          localObject2 = new PackageData(EmptyPackageInfo.BrowserPackageInfo.instance);
          localArrayList.add(localObject2);
          if (!bool1)
          {
            if (!this.f)
            {
              m.a();
              m.e("com.lbe.parallel.browser");
            }
            ((PackageData)localObject2).isFirstShow = true;
          }
        }
        if ((??? == null) || (((List)???).size() <= 0)) {
          break label444;
        }
        boolean bool2 = ab.a().a("show_gms_packages_in_home");
        Object localObject2 = ((List)???).iterator();
        if (!((Iterator)localObject2).hasNext()) {
          break label444;
        }
        AppInstallInfo localAppInstallInfo = (AppInstallInfo)((Iterator)localObject2).next();
        str = localAppInstallInfo.getPackageName();
        ??? = (u)localMap1.get(str);
        if (??? == null)
        {
          ??? = new PackageData(str);
          if (??? == null) {
            continue;
          }
          ((PackageData)???).setAppInstallInfo(localAppInstallInfo);
          if (!bool2) {
            break label418;
          }
          localArrayList.add(???);
          if ((!str.equals("com.whatsapp")) || (ab.a().a("has_add_special_app"))) {
            continue;
          }
          ab.a().a("has_add_special_app", true);
          ab.a().a("add_special_app_time", System.currentTimeMillis());
        }
      }
      if (((Boolean)((u)???).b).booleanValue())
      {
        ??? = new PackageData((PackageInfo)((u)???).a);
        continue;
        label418:
        if (!fs.c.contains(str))
        {
          localArrayList.add(???);
          continue;
          label444:
          if ((localMap2.size() == 0) || (!bool1)) {
            this.j.post(new Runnable()
            {
              public final void run()
              {
                f.a.b(kg.this);
              }
            });
          }
          Collections.sort(localArrayList, new a(localMap2));
          localInitModel2.setPackageDataList(localArrayList);
          b(true);
          return localInitModel2;
        }
      }
      else
      {
        ??? = null;
      }
    }
  }
  
  private Map<String, u<PackageInfo, Boolean>> w()
  {
    Object localObject3 = new fq(e());
    Object localObject1 = ((fq)localObject3).getInstalledPackages(0);
    HashMap localHashMap = new HashMap(((List)localObject1).size());
    Iterator localIterator = ((List)localObject1).iterator();
    Object localObject2;
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (!f.a.a(localPackageInfo.applicationInfo))
      {
        localObject2 = w.a().b(localPackageInfo.packageName);
        localObject1 = localObject2;
        if (localObject2 == null) {
          if (((fq)localObject3).getLaunchIntentForPackage(localPackageInfo.packageName) == null) {
            break label159;
          }
        }
        label159:
        for (localObject1 = Boolean.TRUE;; localObject1 = Boolean.FALSE)
        {
          w.a().a(localPackageInfo.packageName, (Boolean)localObject1);
          localHashMap.put(localPackageInfo.packageName, new u(localPackageInfo, localObject1));
          break;
        }
      }
    }
    w.a().b();
    if (localHashMap.size() > 0)
    {
      localObject1 = hq.a(e());
      localObject2 = localHashMap.values().iterator();
      long l = 0L;
      int m = 0;
      for (;;)
      {
        if (!((Iterator)localObject2).hasNext()) {
          break label305;
        }
        localObject3 = (PackageInfo)((u)((Iterator)localObject2).next()).a;
        try
        {
          if (!((hq)localObject1).a(((PackageInfo)localObject3).packageName)) {
            ((hq)localObject1).d(((PackageInfo)localObject3).packageName);
          }
        }
        catch (PackageManagerDied localPackageManagerDied)
        {
          localPackageManagerDied.printStackTrace();
          if (m >= 5) {
            break label305;
          }
        }
      }
      if (System.currentTimeMillis() - l < 1500L) {
        m += 1;
      }
      for (;;)
      {
        l = System.currentTimeMillis();
        break;
        m = 0;
      }
    }
    label305:
    return localHashMap;
  }
  
  public final void a(boolean paramBoolean)
  {
    this.d = paramBoolean;
    r();
  }
  
  protected final void p()
  {
    super.p();
    synchronized (this.k)
    {
      if (this.h)
      {
        fn.a(e()).b(this.k);
        this.h = false;
      }
      return;
    }
  }
  
  public final void r()
  {
    if (this.i.get())
    {
      super.r();
      return;
    }
    this.j.sendEmptyMessageDelayed(999, 500L);
  }
  
  public final void s()
  {
    this.e = true;
    r();
  }
  
  public final void t()
  {
    this.f = true;
    r();
  }
  
  public final boolean u()
  {
    return this.e;
  }
  
  final class a
    implements Comparator<PackageData>
  {
    private Map<String, Integer> a;
    
    a()
    {
      this.a = this$1;
    }
  }
}
