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
import com.yandex.metrica.impl.ob.ci;
import com.yandex.metrica.impl.ob.cx;
import com.yandex.metrica.impl.ob.dc;
import com.yandex.metrica.impl.ob.dh;
import com.yandex.metrica.impl.ob.dm;
import com.yandex.metrica.impl.ob.dp;
import com.yandex.metrica.impl.ob.ed;
import com.yandex.metrica.impl.ob.ee;
import com.yandex.metrica.impl.ob.fh;
import com.yandex.metrica.impl.ob.fi;
import com.yandex.metrica.impl.ob.fv;
import com.yandex.metrica.impl.ob.g;
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
  private static final Executor c = new ee();
  private static final ExecutorService d = Executors.newSingleThreadExecutor(new com.yandex.metrica.impl.utils.j("AppMetrica-MetricaServiceTasks"));
  private static final Map<String, v> e = new HashMap();
  private static final s f = new s();
  private Context a;
  private MetricaService.d b;
  private final Set<String> g = new HashSet();
  private Thread h;
  
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
  
  private void a(dm paramDm, dh paramDh)
  {
    if (h()) {
      paramDm = paramDh;
    }
    dp.a(this.a).a(paramDm);
  }
  
  private void b(Intent paramIntent, int paramInt)
  {
    i localI;
    Object localObject;
    if (paramIntent != null)
    {
      paramIntent.getExtras().setClassLoader(CounterConfiguration.class.getClassLoader());
      int j = 1;
      int i;
      if ((paramIntent != null) && (paramIntent.getData() != null)) {
        i = 0;
      } else {
        i = 1;
      }
      if (i == 0)
      {
        localI = i.b(paramIntent.getExtras());
        if (localI.n())
        {
          i = paramIntent.getIntExtra("EXTRA_KEY_KEY_START_TYPE", q.a.a.a());
          localObject = paramIntent.getStringExtra("EXTRA_KEY_KEY_START_EVENT");
          localI.a(i).b((String)localObject).c("");
        }
        if (!(localI.m() | localI.n()))
        {
          localObject = paramIntent.getBundleExtra("EXTRA_KEY_LIB_CFG");
          if (localObject == null) {
            localObject = paramIntent.getExtras();
          }
          localObject = CounterConfiguration.b((Bundle)localObject);
          if (localObject == null) {
            i = j;
          } else {
            i = 0;
          }
          if (i == 0)
          {
            paramIntent = paramIntent.getData().getEncodedAuthority();
            b((CounterConfiguration)localObject, paramIntent);
            a((CounterConfiguration)localObject);
          }
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
    }
    catch (Exception paramIntent)
    {
      for (;;) {}
    }
  }
  
  private static void b(CounterConfiguration paramCounterConfiguration, String paramString)
  {
    if (TextUtils.isEmpty(paramCounterConfiguration.f())) {
      paramCounterConfiguration.c(paramString);
    }
  }
  
  private void b(cx paramCx)
  {
    a(dm.b(paramCx), dh.a(paramCx));
  }
  
  private void c(CounterConfiguration paramCounterConfiguration)
  {
    String str = dc.h(this.a, paramCounterConfiguration.f());
    if (!TextUtils.isEmpty(str)) {
      paramCounterConfiguration.e(str);
    }
  }
  
  private void d()
  {
    dp.a(this.a).b(this);
    fv.a(this.a).b();
  }
  
  private void e()
  {
    dp.a(this.a).a(this);
    fv.a(this.a).a();
  }
  
  private void f()
  {
    b(new cx(ci.a(this.a).d(), this.a.getPackageName()));
  }
  
  private void g()
  {
    this.h = com.yandex.metrica.impl.utils.j.a("AppMetrica-ConfigServiceLauncher", new Runnable()
    {
      public void run()
      {
        ag.this.a();
      }
    });
    this.h.start();
  }
  
  private boolean h()
  {
    HashSet localHashSet = new HashSet(this.g);
    localHashSet.removeAll(MetricaService.a.a);
    return localHashSet.size() == 0;
  }
  
  private boolean i()
  {
    if (this.g.size() <= 1) {
      return (this.g.size() == 1) && (!this.g.contains("com.yandex.metrica.ACTION_BIND_TO_LOCAL_SERVER"));
    }
    return true;
  }
  
  t a(i paramI, CounterConfiguration paramCounterConfiguration, int paramInt)
  {
    boolean bool = q.a(paramI);
    Object localObject = null;
    if (bool)
    {
      paramCounterConfiguration = paramI.l();
      paramI = this.a.getPackageManager();
      int i = 0;
      paramI = paramI.getInstalledApplications(0).iterator();
      do
      {
        paramInt = i;
        if (!paramI.hasNext()) {
          break;
        }
      } while (!((ApplicationInfo)paramI.next()).packageName.equals(paramCounterConfiguration));
      paramInt = 1;
      paramI = localObject;
      if (paramInt != 0) {
        return t.a(paramCounterConfiguration);
      }
    }
    else
    {
      paramI = t.a(this.a, paramCounterConfiguration, Integer.valueOf(paramInt), null);
    }
    return paramI;
  }
  
  v a(t paramT, CounterConfiguration paramCounterConfiguration, i paramI)
  {
    if (paramT != null)
    {
      v localV = (v)e.get(paramT.toString());
      if (localV == null)
      {
        localV = new v(this.a, c, paramT, paramCounterConfiguration, f);
        if (paramI != null)
        {
          paramCounterConfiguration = localV;
          if (q.a(paramI)) {}
        }
        else
        {
          e.put(paramT.toString(), localV);
          return localV;
        }
      }
      else
      {
        localV.b(paramCounterConfiguration);
        paramCounterConfiguration = localV;
      }
      return paramCounterConfiguration;
    }
    return null;
  }
  
  public void a()
  {
    new be(this.a).a(this.a);
    n.a().a(this.a);
    GoogleAdvertisingIdGetter.b.a.a(this.a);
    cx localCx = new cx(ci.a(this.a).d(), this.a.getPackageName());
    ed.a().a(this.a, localCx.b(null), localCx.h(null));
    a(localCx);
    g();
    b(localCx);
    g.a().a(this, o.class, k.a(new com.yandex.metrica.impl.ob.j()
    {
      public void a(o paramAnonymousO)
      {
        ag.a(ag.this);
        ag.a(ag.this, paramAnonymousO.a.o(), paramAnonymousO.a.S());
      }
    }).a());
    dc.a().a(this.a);
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
      f();
    }
    if (i()) {
      e();
    }
    if (!h()) {
      fh.a(this.a).a();
    }
    if (this.g.size() == 1) {
      dp.a(this.a).a();
    }
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
    if (TextUtils.isEmpty(paramCounterConfiguration.h()))
    {
      c(paramCounterConfiguration);
      return;
    }
    String str = dc.a().d();
    int i;
    if ((!TextUtils.isEmpty(str)) && (!TextUtils.equals(paramCounterConfiguration.h(), str))) {
      i = 0;
    } else {
      i = 1;
    }
    if (i == 0) {
      c(paramCounterConfiguration);
    }
  }
  
  void a(cx paramCx)
  {
    paramCx = paramCx.o();
    if (TextUtils.isEmpty(paramCx))
    {
      g.a().a(p.class);
      return;
    }
    try
    {
      g.a().b(new p(new fi(paramCx)));
      return;
    }
    catch (JSONException paramCx) {}
  }
  
  void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    dp.a(this.a).a(this, paramBoolean1, paramBoolean2);
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
      f();
    }
    if (!h()) {
      fh.a(this.a).a();
    }
    if (i()) {
      e();
    }
  }
  
  public void c(Intent paramIntent)
  {
    if (paramIntent != null)
    {
      ??? = paramIntent.getAction();
      if (paramIntent.getData() == null) {
        paramIntent = null;
      } else {
        paramIntent = paramIntent.getData().getEncodedAuthority();
      }
      if (!TextUtils.isEmpty((CharSequence)???))
      {
        this.g.remove(???);
        f();
      }
      if (!i()) {
        d();
      }
      if (!MetricaService.a.a.contains(???))
      {
        if (h()) {
          fh.a(this.a).b();
        }
        if (paramIntent == null) {}
      }
    }
    for (;;)
    {
      synchronized (e)
      {
        Iterator localIterator = new HashMap(e).entrySet().iterator();
        if (localIterator.hasNext())
        {
          Object localObject2 = (Map.Entry)localIterator.next();
          String str = (String)((Map.Entry)localObject2).getKey();
          localObject2 = (v)((Map.Entry)localObject2).getValue();
          if ((str == null) || (localObject2 == null)) {
            break label233;
          }
          if (!str.startsWith(paramIntent)) {
            break label228;
          }
          break label233;
          if (i == 0) {
            continue;
          }
          e.remove(str);
          if (localObject2 == null) {
            continue;
          }
          ((v)localObject2).d();
          continue;
        }
        return;
      }
      return;
      label228:
      int i = 0;
      continue;
      label233:
      i = 1;
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
      try
      {
        paramBundle = (CounterConfiguration)paramBundle.getParcelable("COUNTER_MIGRATION_CFG_OBJ");
        return paramBundle;
      }
      catch (Throwable paramBundle) {}
      return null;
      return null;
    }
    
    public void run()
    {
      CounterConfiguration localCounterConfiguration1 = CounterConfiguration.b(this.d);
      if (ag.b(localCounterConfiguration1)) {
        return;
      }
      Object localObject2 = ag.this.a(this.c, localCounterConfiguration1, this.b);
      if (localObject2 == null) {
        return;
      }
      ag.a(localCounterConfiguration1, ((t)localObject2).b());
      synchronized (ag.c())
      {
        ag.this.a(localCounterConfiguration1);
        v localV = ag.this.a((t)localObject2, localCounterConfiguration1, this.c);
        if ((((t)localObject2).d()) && (localV.b())) {
          ag.this.a(this.e.getPackageName().equals(localCounterConfiguration1.f()), localCounterConfiguration1.m());
        }
        localObject2 = localCounterConfiguration1.j();
        if (this.d.containsKey("COUNTER_MIGRATION_CFG_OBJ"))
        {
          CounterConfiguration localCounterConfiguration2 = a(this.d);
          if ((localCounterConfiguration2 != null) && (localCounterConfiguration2.D()))
          {
            t localT = t.a(this.e, localCounterConfiguration2, Integer.valueOf(this.b), null);
            if (!ag.c().containsKey(localT.toString()))
            {
              localCounterConfiguration2 = new CounterConfiguration(localCounterConfiguration2);
              localCounterConfiguration2.a((String)localObject2);
              ag.this.a(localT, localCounterConfiguration2, null).g();
            }
          }
        }
        if (ag.a(localV)) {
          return;
        }
        if (!q.a(this.c.c())) {
          localV.a(localCounterConfiguration1);
        }
        if (!ag.a(localV, this.c)) {
          localV.a(this.c);
        }
        return;
      }
    }
  }
}
