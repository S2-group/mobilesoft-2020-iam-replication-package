package com.yandex.metrica.impl;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.yandex.metrica.CounterConfiguration;
import com.yandex.metrica.MetricaService.a;
import com.yandex.metrica.MetricaService.d;
import com.yandex.metrica.impl.ob.br;
import com.yandex.metrica.impl.ob.ch;
import com.yandex.metrica.impl.ob.cw;
import com.yandex.metrica.impl.ob.db;
import com.yandex.metrica.impl.ob.dg;
import com.yandex.metrica.impl.ob.dl;
import com.yandex.metrica.impl.ob.do;
import com.yandex.metrica.impl.ob.ec;
import com.yandex.metrica.impl.ob.ed;
import com.yandex.metrica.impl.ob.fg;
import com.yandex.metrica.impl.ob.fh;
import com.yandex.metrica.impl.ob.fu;
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
  private static final Executor c = new ed();
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
  
  private void a(dl paramDl, dg paramDg)
  {
    if (h()) {}
    for (;;)
    {
      do.a(this.a).a(paramDg);
      return;
      paramDg = paramDl;
    }
  }
  
  private void b(Intent paramIntent, int paramInt)
  {
    int i;
    if (paramIntent != null)
    {
      paramIntent.getExtras().setClassLoader(CounterConfiguration.class.getClassLoader());
      if ((paramIntent != null) && (paramIntent.getData() != null)) {
        break label207;
      }
      i = 1;
    }
    for (;;)
    {
      i localI;
      Object localObject;
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
            break label212;
          }
          label115:
          localObject = CounterConfiguration.b((Bundle)localObject);
          if (localObject != null) {
            break label221;
          }
          i = 1;
          if (i == 0)
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
        i = 0;
        continue;
        label212:
        localObject = paramIntent.getExtras();
        break label115;
        label221:
        i = 0;
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
  
  private void b(cw paramCw)
  {
    a(dl.b(paramCw), dg.a(paramCw));
  }
  
  private void c(CounterConfiguration paramCounterConfiguration)
  {
    String str = db.h(this.a, paramCounterConfiguration.f());
    if (!TextUtils.isEmpty(str)) {
      paramCounterConfiguration.e(str);
    }
  }
  
  private void d()
  {
    do.a(this.a).b(this);
    fu.a(this.a).b();
  }
  
  private void e()
  {
    do.a(this.a).a(this);
    fu.a(this.a).a();
  }
  
  private void f()
  {
    b(new cw(ch.a(this.a).d(), this.a.getPackageName()));
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
    GoogleAdvertisingIdGetter.b.a.a(this.a);
    cw localCw = new cw(ch.a(this.a).d(), this.a.getPackageName());
    ec.a().a(this.a, localCw.b(null), localCw.i(null));
    a(localCw);
    g();
    b(localCw);
    g.a().a(this, o.class, k.a(new com.yandex.metrica.impl.ob.j()
    {
      public void a(o paramAnonymousO)
      {
        ag.a(ag.this);
        ag.a(ag.this, paramAnonymousO.a.o(), paramAnonymousO.a.S());
      }
    }).a());
    db.a().a(this.a);
  }
  
  public void a(int paramInt, Bundle paramBundle)
  {
    paramBundle.setClassLoader(CounterConfiguration.class.getClassLoader());
    a(paramInt, i.b(paramBundle), paramBundle);
  }
  
  public void a(int paramInt1, String paramString1, int paramInt2, String paramString2, Bundle paramBundle)
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
      fg.a(this.a).a();
    }
    if (this.g.size() == 1) {
      do.a(this.a).a();
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
    if (TextUtils.isEmpty(paramCounterConfiguration.h())) {
      c(paramCounterConfiguration);
    }
    for (;;)
    {
      return;
      String str = db.a().d();
      if ((TextUtils.isEmpty(str)) || (TextUtils.equals(paramCounterConfiguration.h(), str))) {}
      for (int i = 1; i == 0; i = 0)
      {
        c(paramCounterConfiguration);
        return;
      }
    }
  }
  
  void a(cw paramCw)
  {
    paramCw = paramCw.m();
    if (TextUtils.isEmpty(paramCw))
    {
      g.a().a(p.class);
      return;
    }
    try
    {
      g.a().b(new p(new fh(paramCw)));
      return;
    }
    catch (JSONException paramCw) {}
  }
  
  void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    do.a(this.a).a(this, paramBoolean1, paramBoolean2);
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
      fg.a(this.a).a();
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
      if (paramIntent.getData() == null)
      {
        paramIntent = null;
        if (!TextUtils.isEmpty((CharSequence)???))
        {
          this.g.remove(???);
          f();
        }
        if (!i()) {
          d();
        }
        if (MetricaService.a.a.contains(???)) {
          break label231;
        }
        if (h()) {
          fg.a(this.a).b();
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
        if (i == 0) {
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
      int i = 0;
      continue;
      label229:
      label231:
      return;
      label232:
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
