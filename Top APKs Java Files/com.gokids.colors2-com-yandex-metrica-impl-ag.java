package com.yandex.metrica.impl;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.MetricaService.a;
import com.yandex.metrica.MetricaService.d;
import com.yandex.metrica.impl.ob.bs;
import com.yandex.metrica.impl.ob.cq;
import com.yandex.metrica.impl.ob.dg;
import com.yandex.metrica.impl.ob.dl;
import com.yandex.metrica.impl.ob.dq;
import com.yandex.metrica.impl.ob.dv;
import com.yandex.metrica.impl.ob.eb;
import com.yandex.metrica.impl.ob.eq;
import com.yandex.metrica.impl.ob.er;
import com.yandex.metrica.impl.ob.fu;
import com.yandex.metrica.impl.ob.fv;
import com.yandex.metrica.impl.ob.g;
import com.yandex.metrica.impl.ob.gi;
import com.yandex.metrica.impl.ob.k;
import com.yandex.metrica.impl.ob.k.a;
import com.yandex.metrica.impl.ob.o;
import com.yandex.metrica.impl.ob.p;
import com.yandex.metrica.impl.ob.s;
import com.yandex.metrica.impl.ob.t;
import com.yandex.metrica.impl.ob.v;
import com.yandex.metrica.impl.utils.n;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

public class ag
  implements ae
{
  private static final Executor c = new er();
  private static final ExecutorService d = Executors.newSingleThreadExecutor(new com.yandex.metrica.impl.utils.j("YMM-MSTE"));
  private static final Map<String, v> e = new HashMap();
  private static final s f = new s();
  private Context a;
  private MetricaService.d b;
  private final Set<String> g = new HashSet();
  private Thread h;
  private eb i;
  private gi j;
  
  public ag(Context paramContext, MetricaService.d paramD)
  {
    this.a = paramContext;
    this.b = paramD;
  }
  
  private void a(int paramInt, i paramI, Bundle paramBundle)
  {
    if (!paramI.n())
    {
      paramI = new a(this.a, paramI, paramBundle, paramInt);
      d.execute(paramI);
    }
  }
  
  private void a(dv paramDv, dq paramDq)
  {
    if (g()) {}
    for (;;)
    {
      eb.a(this.a).a(paramDq);
      return;
      paramDq = paramDv;
    }
  }
  
  private void a(String paramString)
  {
    if (h())
    {
      this.i = eb.a(this.a);
      this.i.a(this);
      this.j = gi.a(this.a);
      this.j.a();
    }
    if (!g()) {
      fu.a(this.a).a();
    }
    if ((this.g.size() == 1) || ("com.yandex.metrica.ACTION_C_BG_L".equals(paramString))) {
      eb.a(this.a).a();
    }
  }
  
  private void b(Intent paramIntent, int paramInt)
  {
    int k;
    if (paramIntent != null)
    {
      paramIntent.getExtras().setClassLoader(CounterConfiguration.class.getClassLoader());
      if ((paramIntent != null) && (paramIntent.getData() != null)) {
        break label207;
      }
      k = 1;
    }
    for (;;)
    {
      i localI;
      Object localObject;
      if (k == 0)
      {
        localI = i.b(paramIntent.getExtras());
        if (localI.n())
        {
          k = paramIntent.getIntExtra("EXTRA_KEY_KEY_START_TYPE", q.a.a.a());
          localObject = paramIntent.getStringExtra("EXTRA_KEY_KEY_START_EVENT");
          localI.a(k).b((String)localObject).c("");
        }
        if (!(localI.m() | localI.n()))
        {
          localObject = paramIntent.getBundleExtra("EXTRA_KEY_LIB_CFG");
          if (localObject == null) {
            break label212;
          }
          label115:
          localObject = CounterConfiguration.b((Bundle)localObject);
          if (localObject != null) {
            break label221;
          }
          k = 1;
          if (k == 0)
          {
            paramIntent = paramIntent.getData().getEncodedAuthority();
            b((CounterConfiguration)localObject, paramIntent);
            a((CounterConfiguration)localObject);
          }
        }
      }
      try
      {
        paramIntent = t.a(this.a, (CounterConfiguration)localObject, null, paramIntent);
        paramIntent = new v(this.a, c, paramIntent, (CounterConfiguration)localObject, f);
        paramIntent.a(localI);
        paramIntent.e();
        this.b.a(paramInt);
        return;
        label207:
        k = 0;
        continue;
        label212:
        localObject = paramIntent.getExtras();
        break label115;
        label221:
        k = 0;
      }
      catch (Exception paramIntent)
      {
        for (;;) {}
      }
    }
  }
  
  private static void b(CounterConfiguration paramCounterConfiguration, String paramString)
  {
    if (TextUtils.isEmpty(paramCounterConfiguration.f())) {
      paramCounterConfiguration.c(paramString);
    }
  }
  
  private void b(dg paramDg)
  {
    a(dv.b(paramDg), dq.a(paramDg));
  }
  
  private void c(CounterConfiguration paramCounterConfiguration)
  {
    String str = dl.h(this.a, paramCounterConfiguration.f());
    if (!TextUtils.isEmpty(str)) {
      paramCounterConfiguration.e(str);
    }
  }
  
  private void d()
  {
    if (this.i != null) {
      this.i.b(this);
    }
    if (this.j != null) {
      this.j.b();
    }
  }
  
  private void e()
  {
    b(new dg(cq.a(this.a).d(), this.a.getPackageName()));
  }
  
  private void f()
  {
    this.h = com.yandex.metrica.impl.utils.j.a("YMM-CSL", new Runnable()
    {
      public void run()
      {
        ag.this.a();
      }
    });
    this.h.start();
  }
  
  private boolean g()
  {
    HashSet localHashSet = new HashSet(this.g);
    localHashSet.removeAll(MetricaService.a.a);
    return localHashSet.size() == 0;
  }
  
  private boolean h()
  {
    return (this.g.size() > 1) || ((this.g.size() == 1) && (!this.g.contains("com.yandex.metrica.ACTION_BIND_TO_LOCAL_SERVER")));
  }
  
  t a(i paramI, CounterConfiguration paramCounterConfiguration, int paramInt)
  {
    if (q.a(paramI))
    {
      paramI = paramI.l();
      paramCounterConfiguration = this.a.getPackageManager().getInstalledApplications(0).iterator();
      do
      {
        if (!paramCounterConfiguration.hasNext()) {
          break;
        }
      } while (!((ApplicationInfo)paramCounterConfiguration.next()).packageName.equals(paramI));
      for (paramInt = 1; paramInt != 0; paramInt = 0) {
        return t.a(paramI);
      }
    }
    return t.a(this.a, paramCounterConfiguration, Integer.valueOf(paramInt), null);
    return null;
  }
  
  v a(t paramT, CounterConfiguration paramCounterConfiguration, i paramI)
  {
    if (paramT != null)
    {
      v localV = (v)e.get(paramT.toString());
      if (localV == null)
      {
        paramCounterConfiguration = new v(this.a, c, paramT, paramCounterConfiguration, f);
        if ((paramI == null) || (!q.a(paramI))) {
          e.put(paramT.toString(), paramCounterConfiguration);
        }
        return paramCounterConfiguration;
      }
      localV.b(paramCounterConfiguration);
      return localV;
    }
    return null;
  }
  
  public void a()
  {
    new be(this.a).a(this.a);
    n.a().a(this.a);
    GoogleAdvertisingIdGetter.c.a.a(this.a);
    dg localDg = new dg(cq.a(this.a).d(), this.a.getPackageName());
    eq.a().a(this.a, localDg.b(null), localDg.h(null));
    a(localDg);
    f();
    b(localDg);
    g.a().a(this, o.class, k.a(new com.yandex.metrica.impl.ob.j()
    {
      public void a(o paramAnonymousO)
      {
        ag.a(ag.this);
        ag.a(ag.this, paramAnonymousO.a.o(), paramAnonymousO.a.S());
      }
    }).a());
    dl.a().a(this.a);
  }
  
  public void a(int paramInt, Bundle paramBundle)
    throws RemoteException
  {
    paramBundle.setClassLoader(CounterConfiguration.class.getClassLoader());
    a(paramInt, i.b(paramBundle), paramBundle);
  }
  
  public void a(int paramInt1, String paramString1, int paramInt2, String paramString2, Bundle paramBundle)
    throws RemoteException
  {
    paramBundle.setClassLoader(CounterConfiguration.class.getClassLoader());
    a(paramInt1, new i(paramString2, paramString1, paramInt2), paramBundle);
  }
  
  public void a(Intent paramIntent)
  {
    paramIntent = paramIntent.getAction();
    if (!TextUtils.isEmpty(paramIntent))
    {
      this.g.add(paramIntent);
      e();
    }
    a(paramIntent);
  }
  
  public void a(Intent paramIntent, int paramInt)
  {
    b(paramIntent, paramInt);
  }
  
  public void a(Intent paramIntent, int paramInt1, int paramInt2)
  {
    b(paramIntent, paramInt2);
  }
  
  void a(CounterConfiguration paramCounterConfiguration)
  {
    if (TextUtils.isEmpty(paramCounterConfiguration.h())) {
      c(paramCounterConfiguration);
    }
    for (;;)
    {
      return;
      String str = dl.a().d();
      if ((TextUtils.isEmpty(str)) || (TextUtils.equals(paramCounterConfiguration.h(), str))) {}
      for (int k = 1; k == 0; k = 0)
      {
        c(paramCounterConfiguration);
        return;
      }
    }
  }
  
  void a(dg paramDg)
  {
    paramDg = paramDg.n();
    if (TextUtils.isEmpty(paramDg))
    {
      g.a().a(p.class);
      return;
    }
    try
    {
      g.a().b(new p(new fv(paramDg)));
      return;
    }
    catch (JSONException paramDg) {}
  }
  
  void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    eb.a(this.a).a(this, paramBoolean1, paramBoolean2);
  }
  
  public void b()
  {
    d();
  }
  
  public void b(Intent paramIntent)
  {
    paramIntent = paramIntent.getAction();
    if (!bj.a(paramIntent))
    {
      this.g.add(paramIntent);
      e();
    }
    a(paramIntent);
  }
  
  public void c(Intent paramIntent)
  {
    if (paramIntent != null)
    {
      ??? = paramIntent.getAction();
      if (paramIntent.getData() == null)
      {
        paramIntent = null;
        if (!TextUtils.isEmpty((CharSequence)???))
        {
          this.g.remove(???);
          e();
        }
        if (!h()) {
          d();
        }
        if (MetricaService.a.a.contains(???)) {
          break label231;
        }
        if (g()) {
          fu.a(this.a).b();
        }
        if (paramIntent == null) {
          break label231;
        }
      }
    }
    for (;;)
    {
      synchronized (e)
      {
        Iterator localIterator = new HashMap(e).entrySet().iterator();
        if (!localIterator.hasNext()) {
          break label229;
        }
        Object localObject2 = (Map.Entry)localIterator.next();
        String str = (String)((Map.Entry)localObject2).getKey();
        localObject2 = (v)((Map.Entry)localObject2).getValue();
        if ((str == null) || (localObject2 == null)) {
          break label232;
        }
        if (!str.startsWith(paramIntent)) {
          break label224;
        }
        break label232;
        if (k == 0) {
          continue;
        }
        e.remove(str);
        if (localObject2 == null) {
          continue;
        }
        ((v)localObject2).d();
      }
      paramIntent = paramIntent.getData().getEncodedAuthority();
      break;
      label224:
      int k = 0;
      continue;
      label229:
      label231:
      return;
      label232:
      k = 1;
    }
  }
  
  final class a
    implements Runnable
  {
    private final int b;
    private final i c;
    private final Bundle d;
    private final Context e;
    
    a(Context paramContext, i paramI, Bundle paramBundle, int paramInt)
    {
      this.e = paramContext.getApplicationContext();
      this.b = paramInt;
      this.c = paramI;
      this.d = paramBundle;
    }
    
    private static CounterConfiguration a(Bundle paramBundle)
    {
      if (paramBundle != null) {}
      for (;;)
      {
        try
        {
          paramBundle = (CounterConfiguration)paramBundle.getParcelable("COUNTER_MIGRATION_CFG_OBJ");
          return paramBundle;
        }
        catch (Throwable paramBundle)
        {
          return null;
        }
        paramBundle = null;
      }
    }
    
    public void run()
    {
      CounterConfiguration localCounterConfiguration1 = CounterConfiguration.b(this.d);
      if (ag.b(localCounterConfiguration1)) {}
      Object localObject;
      do
      {
        return;
        localObject = ag.this.a(this.c, localCounterConfiguration1, this.b);
      } while (localObject == null);
      ag.a(localCounterConfiguration1, ((t)localObject).b());
      v localV;
      synchronized (ag.c())
      {
        ag.this.a(localCounterConfiguration1);
        localV = ag.this.a((t)localObject, localCounterConfiguration1, this.c);
        if ((((t)localObject).d()) && (localV.b())) {
          ag.this.a(this.e.getPackageName().equals(localCounterConfiguration1.f()), localCounterConfiguration1.m());
        }
        localObject = localCounterConfiguration1.j();
        if (this.d.containsKey("COUNTER_MIGRATION_CFG_OBJ"))
        {
          CounterConfiguration localCounterConfiguration3 = a(this.d);
          if ((localCounterConfiguration3 != null) && (localCounterConfiguration3.D()))
          {
            t localT = t.a(this.e, localCounterConfiguration3, Integer.valueOf(this.b), null);
            if (!ag.c().containsKey(localT.toString()))
            {
              localCounterConfiguration3 = new CounterConfiguration(localCounterConfiguration3);
              localCounterConfiguration3.a((String)localObject);
              ag.this.a(localT, localCounterConfiguration3, null).g();
            }
          }
        }
        if (ag.a(localV)) {
          return;
        }
      }
      if (!q.a(this.c.c())) {
        localV.a(localCounterConfiguration2);
      }
      if (!ag.a(localV, this.c)) {
        localV.a(this.c);
      }
    }
  }
}
