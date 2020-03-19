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
import com.yandex.metrica.MetricaService.b;
import com.yandex.metrica.impl.ob.bp;
import com.yandex.metrica.impl.ob.cd;
import com.yandex.metrica.impl.ob.ci;
import com.yandex.metrica.impl.ob.co;
import com.yandex.metrica.impl.ob.cp;
import com.yandex.metrica.impl.ob.dr;
import com.yandex.metrica.impl.ob.ds;
import com.yandex.metrica.impl.ob.ef;
import com.yandex.metrica.impl.ob.g;
import com.yandex.metrica.impl.ob.o;
import com.yandex.metrica.impl.ob.q;
import com.yandex.metrica.impl.ob.r;
import com.yandex.metrica.impl.ob.t;
import com.yandex.metrica.impl.utils.k;
import java.util.HashMap;
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
  private static final Executor c = new cp();
  private static final ExecutorService d = Executors.newSingleThreadExecutor();
  private static final Map<String, t> e = new HashMap();
  private static final q f = new q();
  private Context a;
  private MetricaService.b b;
  
  public ag(Context paramContext, MetricaService.b paramB)
  {
    this.a = paramContext;
    this.b = paramB;
  }
  
  private void a(int paramInt, h paramH, Bundle paramBundle)
  {
    if (!paramH.n())
    {
      paramH = new a(this.a, paramH, paramBundle, paramInt);
      d.execute(paramH);
    }
  }
  
  private void b(Intent paramIntent, int paramInt)
  {
    int i;
    if (paramIntent != null)
    {
      paramIntent.getExtras().setClassLoader(CounterConfiguration.class.getClassLoader());
      if ((paramIntent != null) && (paramIntent.getData() != null)) {
        break label222;
      }
      i = 1;
    }
    for (;;)
    {
      h localH;
      Object localObject;
      if (i == 0)
      {
        localH = h.b(paramIntent.getExtras());
        if (localH.n())
        {
          i = paramIntent.getIntExtra("EXTRA_KEY_KEY_START_TYPE", p.a.a.a());
          localObject = paramIntent.getStringExtra("EXTRA_KEY_KEY_START_EVENT");
          localH.a(i).b((String)localObject).c("");
        }
        if (!(localH.m() | localH.n()))
        {
          localObject = paramIntent.getBundleExtra("EXTRA_KEY_LIB_CFG");
          if (localObject == null) {
            break label227;
          }
          label115:
          localObject = CounterConfiguration.b((Bundle)localObject);
          if (localObject != null) {
            break label236;
          }
          i = 1;
          if (i == 0)
          {
            paramIntent = paramIntent.getData().getEncodedAuthority();
            b((CounterConfiguration)localObject, paramIntent);
            b((CounterConfiguration)localObject);
            y.a(this.a).a(localH.e());
          }
        }
      }
      try
      {
        paramIntent = r.a(this.a, (CounterConfiguration)localObject, null, paramIntent);
        paramIntent = new t(this.a, c, paramIntent, (CounterConfiguration)localObject, f);
        paramIntent.a(localH);
        paramIntent.d();
        this.b.a(paramInt);
        return;
        label222:
        i = 0;
        continue;
        label227:
        localObject = paramIntent.getExtras();
        break label115;
        label236:
        i = 0;
      }
      catch (Exception paramIntent)
      {
        for (;;) {}
      }
    }
  }
  
  private void b(CounterConfiguration paramCounterConfiguration)
  {
    if (TextUtils.isEmpty(paramCounterConfiguration.h())) {
      c(paramCounterConfiguration);
    }
    for (;;)
    {
      return;
      String str = ci.a().d();
      if ((TextUtils.isEmpty(str)) || (TextUtils.equals(paramCounterConfiguration.h(), str))) {}
      for (int i = 1; i == 0; i = 0)
      {
        c(paramCounterConfiguration);
        return;
      }
    }
  }
  
  private static void b(CounterConfiguration paramCounterConfiguration, String paramString)
  {
    if (TextUtils.isEmpty(paramCounterConfiguration.f())) {
      paramCounterConfiguration.c(paramString);
    }
  }
  
  private void c(CounterConfiguration paramCounterConfiguration)
  {
    String str = ci.g(this.a, paramCounterConfiguration.f());
    if (!TextUtils.isEmpty(str)) {
      paramCounterConfiguration.e(str);
    }
  }
  
  r a(h paramH, CounterConfiguration paramCounterConfiguration, int paramInt)
  {
    if (p.a(paramH))
    {
      paramH = paramH.l();
      paramCounterConfiguration = this.a.getPackageManager().getInstalledApplications(0).iterator();
      do
      {
        if (!paramCounterConfiguration.hasNext()) {
          break;
        }
      } while (!((ApplicationInfo)paramCounterConfiguration.next()).packageName.equals(paramH));
      for (paramInt = 1; paramInt != 0; paramInt = 0) {
        return r.a(paramH);
      }
    }
    return r.a(this.a, paramCounterConfiguration, Integer.valueOf(paramInt), null);
    return null;
  }
  
  public void a()
  {
    new bd(this.a).a(this.a);
    k.a().a(this.a);
    GoogleAdvertisingIdGetter.b.a.a(this.a);
    cd localCd = new cd(bp.a(this.a).d(), this.a.getPackageName());
    co.a().a(this.a, localCd.b(null), localCd.h(null));
    a(localCd);
    ci.a().a(this.a);
  }
  
  public void a(int paramInt, Bundle paramBundle)
    throws RemoteException
  {
    paramBundle.setClassLoader(CounterConfiguration.class.getClassLoader());
    a(paramInt, h.b(paramBundle), paramBundle);
  }
  
  public void a(int paramInt1, String paramString1, int paramInt2, String paramString2, Bundle paramBundle)
    throws RemoteException
  {
    paramBundle.setClassLoader(CounterConfiguration.class.getClassLoader());
    a(paramInt1, new h(paramString2, paramString1, paramInt2), paramBundle);
  }
  
  public void a(Intent paramIntent)
  {
    dr.a(this.a).a();
    y.a(this.a).a(this);
    ef.a(this.a).a();
  }
  
  public void a(Intent paramIntent, int paramInt)
  {
    b(paramIntent, paramInt);
  }
  
  public void a(Intent paramIntent, int paramInt1, int paramInt2)
  {
    b(paramIntent, paramInt2);
  }
  
  void a(cd paramCd)
  {
    paramCd = paramCd.l();
    if (TextUtils.isEmpty(paramCd))
    {
      g.a().a(o.class);
      return;
    }
    try
    {
      g.a().b(new o(new ds(paramCd)));
      return;
    }
    catch (JSONException paramCd) {}
  }
  
  public void b()
  {
    y.a(this.a).b(this);
    ef.a(this.a).b();
  }
  
  public void b(Intent paramIntent)
  {
    dr.a(this.a).a();
  }
  
  public void c(Intent arg1)
  {
    String str1 = ???.getData().getEncodedAuthority();
    for (;;)
    {
      synchronized (e)
      {
        Iterator localIterator = new HashMap(e).entrySet().iterator();
        if (!localIterator.hasNext()) {
          break label142;
        }
        Object localObject2 = (Map.Entry)localIterator.next();
        String str2 = (String)((Map.Entry)localObject2).getKey();
        localObject2 = (t)((Map.Entry)localObject2).getValue();
        if ((str2 == null) || (localObject2 == null)) {
          break label166;
        }
        if (str2.startsWith(str1))
        {
          break label166;
          if (i == 0) {
            continue;
          }
          e.remove(str2);
          if (localObject2 == null) {
            continue;
          }
          ((t)localObject2).c();
        }
      }
      int i = 0;
      continue;
      label142:
      if (e.isEmpty()) {
        dr.a(this.a).b();
      }
      return;
      label166:
      i = 1;
    }
  }
  
  private final class a
    implements Runnable
  {
    private final int b;
    private final h c;
    private final Bundle d;
    private final Context e;
    
    a(Context paramContext, h paramH, Bundle paramBundle, int paramInt)
    {
      this.e = paramContext.getApplicationContext();
      this.b = paramInt;
      this.c = paramH;
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
      if (ag.a(localCounterConfiguration1)) {}
      Object localObject;
      do
      {
        return;
        localObject = ag.this.a(this.c, localCounterConfiguration1, this.b);
      } while (localObject == null);
      ag.a(localCounterConfiguration1, ((r)localObject).b());
      synchronized (ag.c())
      {
        ag.a(ag.this, localCounterConfiguration1);
        ag.a(ag.this, this.e.getPackageName().equals(localCounterConfiguration1.f()), localCounterConfiguration1.m());
        String str = localCounterConfiguration1.j();
        if (this.d.containsKey("COUNTER_MIGRATION_CFG_OBJ"))
        {
          CounterConfiguration localCounterConfiguration3 = a(this.d);
          if ((localCounterConfiguration3 != null) && (localCounterConfiguration3.D()))
          {
            r localR = r.a(this.e, localCounterConfiguration3, Integer.valueOf(this.b), null);
            if (!ag.c().containsKey(localR.toString()))
            {
              localCounterConfiguration3 = new CounterConfiguration(localCounterConfiguration3);
              localCounterConfiguration3.a(str);
              ag.a(ag.this, localR, localCounterConfiguration3, null).f();
            }
          }
        }
        localObject = ag.a(ag.this, (r)localObject, localCounterConfiguration1, this.c);
        if (ag.a((t)localObject)) {
          return;
        }
      }
      y.a(this.e).a(this.c.e());
      if (!p.a(this.c.c())) {
        ((t)localObject).a(localCounterConfiguration2);
      }
      if (!ag.a((t)localObject, this.c)) {
        ((t)localObject).a(this.c);
      }
    }
  }
}
