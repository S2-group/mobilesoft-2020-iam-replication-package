package com.lbe.parallel;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Handler;
import android.os.Message;
import com.lbe.doubleagent.utility.PackageManagerWrapper;
import com.lbe.multidroid.service.b;
import com.lbe.multidroid.service.h;
import com.lbe.parallel.install.AppInstallProvider.a;
import com.lbe.parallel.install.a;
import com.lbe.parallel.model.AppInstallInfo;
import com.lbe.parallel.model.EmptyPackageInfo.BrowserPackageInfo;
import com.lbe.parallel.model.InitModel;
import com.lbe.parallel.model.PackageData;
import com.lbe.parallel.model.PackageManagerDied;
import com.lbe.parallel.ui.theme.ThemeContract.ThemeClassification;
import com.lbe.parallel.utility.m;
import com.lbe.parallel.utility.s;
import com.lbe.parallel.utility.u;
import com.lbe.parallel.utility.z;
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

public final class jn
  extends com.lbe.parallel.ui.install.c<InitModel>
{
  private boolean d = true;
  private boolean e = false;
  private boolean f = false;
  private b g;
  private Map<String, s<PackageInfo, Boolean>> h;
  private boolean i = false;
  private AtomicBoolean j = new AtomicBoolean(true);
  private Handler k = new Handler()
  {
    public final void handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what == 999)
      {
        jn.a(jn.this).removeMessages(999);
        if (jn.b(jn.this).get()) {
          jn.this.r();
        }
      }
      else
      {
        return;
      }
      jn.a(jn.this).sendEmptyMessageDelayed(999, 500L);
    }
  };
  private kd l = new kd()
  {
    public final void a(String paramAnonymousString)
    {
      jn.this.a(true);
    }
    
    public final void a(String paramAnonymousString, boolean paramAnonymousBoolean)
    {
      jn.this.a(true);
    }
    
    public final void b(String paramAnonymousString)
    {
      jn.this.a(true);
    }
    
    public final void c(int paramAnonymousInt, String paramAnonymousString)
    {
      jn.this.a(true);
    }
    
    public final void c(String paramAnonymousString)
    {
      jn.this.a(true);
    }
    
    public final void d(int paramAnonymousInt, String paramAnonymousString)
    {
      jn.this.a(true);
    }
    
    public final void d(String paramAnonymousString)
    {
      jn.this.a(true);
    }
  };
  
  public jn(Context paramContext)
  {
    super(paramContext, AppInstallProvider.a.a);
  }
  
  public jn(Context paramContext, boolean paramBoolean)
  {
    super(paramContext, AppInstallProvider.a.a);
    this.e = paramBoolean;
  }
  
  private static void a(List<PackageData> paramList)
  {
    Object localObject1 = com.lbe.parallel.ui.theme.c.a().a(null);
    if ((localObject1 != null) && (((List)localObject1).size() > 0))
    {
      ao localAo = new ao(((List)localObject1).size());
      localObject1 = ((List)localObject1).iterator();
      Object localObject2;
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ThemeContract.ThemeClassification)((Iterator)localObject1).next();
        localAo.put(((ThemeContract.ThemeClassification)localObject2).pkgName, localObject2);
      }
      paramList = paramList.iterator();
      do
      {
        do
        {
          if (!paramList.hasNext()) {
            break;
          }
          localObject1 = (PackageData)paramList.next();
          localObject2 = ((PackageData)localObject1).getPackageName();
        } while (!localAo.containsKey(localObject2));
        ((PackageData)localObject1).setThemesInfo((ThemeContract.ThemeClassification)localAo.remove(localObject2));
      } while (localAo.size() != 0);
    }
  }
  
  private void b(boolean paramBoolean)
  {
    this.j.set(paramBoolean);
  }
  
  private InitModel v()
  {
    int m = 0;
    b(false);
    DAApp.o().p();
    if (this.g == null) {
      this.g = b.a(e());
    }
    for (;;)
    {
      Map localMap2;
      ArrayList localArrayList;
      boolean bool1;
      String str;
      synchronized (this.l)
      {
        if (!this.i)
        {
          this.g.c().b(this.l);
          this.i = true;
        }
        InitModel localInitModel1 = new InitModel();
        if (this.d)
        {
          this.h = w();
          this.d = false;
        }
        localInitModel1.setAllPackages(this.h);
        Map localMap1 = this.h;
        localMap2 = f.a.j();
        ??? = a.a().c();
        if (??? != null) {
          m = ((List)???).size();
        }
        localArrayList = new ArrayList(m);
        m = z.a().b("homepage_launch_count");
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
          break label464;
        }
        boolean bool2 = z.a().a("show_gms_packages_in_home");
        Object localObject2 = ((List)???).iterator();
        if (!((Iterator)localObject2).hasNext()) {
          break label464;
        }
        AppInstallInfo localAppInstallInfo = (AppInstallInfo)((Iterator)localObject2).next();
        str = localAppInstallInfo.getPackageName();
        ??? = (s)localMap1.get(str);
        if (??? == null)
        {
          ??? = new PackageData(str);
          if (??? == null) {
            continue;
          }
          ((PackageData)???).setAppInstallInfo(localAppInstallInfo);
          if (!bool2) {
            break label438;
          }
          localArrayList.add(???);
          if ((!str.equals("com.whatsapp")) || (z.a().a("has_add_special_app"))) {
            continue;
          }
          z.a().a("has_add_special_app", true);
          z.a().a("add_special_app_time", System.currentTimeMillis());
        }
      }
      if (((Boolean)((s)???).b).booleanValue())
      {
        ??? = new PackageData((PackageInfo)((s)???).a);
        continue;
        label438:
        if (!fy.c.contains(str))
        {
          localArrayList.add(???);
          continue;
          label464:
          if ((localMap2.size() == 0) || (!bool1)) {
            f.a.b(localArrayList);
          }
          Collections.sort(localArrayList, new a(localMap2));
          localInitModel2.setPackageDataList(localArrayList);
          if (this.e) {
            a(localArrayList);
          }
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
  
  private Map<String, s<PackageInfo, Boolean>> w()
  {
    Object localObject3 = new PackageManagerWrapper(e());
    Object localObject1 = ((PackageManagerWrapper)localObject3).getInstalledPackages(0);
    HashMap localHashMap = new HashMap(((List)localObject1).size());
    Iterator localIterator = ((List)localObject1).iterator();
    Object localObject2;
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (!f.a.a(localPackageInfo.applicationInfo))
      {
        localObject2 = u.a().b(localPackageInfo.packageName);
        localObject1 = localObject2;
        if (localObject2 == null) {
          if (((PackageManagerWrapper)localObject3).getLaunchIntentForPackage(localPackageInfo.packageName) == null) {
            break label159;
          }
        }
        label159:
        for (localObject1 = Boolean.TRUE;; localObject1 = Boolean.FALSE)
        {
          u.a().a(localPackageInfo.packageName, (Boolean)localObject1);
          localHashMap.put(localPackageInfo.packageName, new s(localPackageInfo, localObject1));
          break;
        }
      }
    }
    if (localHashMap.size() > 0)
    {
      localObject1 = he.a(e());
      localObject2 = localHashMap.values().iterator();
      long l1 = 0L;
      int m = 0;
      for (;;)
      {
        if (!((Iterator)localObject2).hasNext()) {
          break label299;
        }
        localObject3 = (PackageInfo)((s)((Iterator)localObject2).next()).a;
        try
        {
          if (!((he)localObject1).a(((PackageInfo)localObject3).packageName)) {
            ((he)localObject1).d(((PackageInfo)localObject3).packageName);
          }
        }
        catch (PackageManagerDied localPackageManagerDied)
        {
          localPackageManagerDied.printStackTrace();
          if (m >= 5) {
            break label299;
          }
        }
      }
      if (System.currentTimeMillis() - l1 < 1500L) {
        m += 1;
      }
      for (;;)
      {
        l1 = System.currentTimeMillis();
        break;
        m = 0;
      }
    }
    label299:
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
    synchronized (this.l)
    {
      if (this.i)
      {
        this.g.c().b(this.l);
        this.i = false;
      }
      return;
    }
  }
  
  public final void r()
  {
    if (this.j.get())
    {
      super.r();
      return;
    }
    this.k.sendEmptyMessageDelayed(999, 500L);
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
