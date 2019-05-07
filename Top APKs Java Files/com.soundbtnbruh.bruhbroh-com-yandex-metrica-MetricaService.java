package com.yandex.metrica;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.yandex.metrica.impl.GoogleAdvertisingIdGetter;
import com.yandex.metrica.impl.az;
import com.yandex.metrica.impl.be;
import com.yandex.metrica.impl.bg;
import com.yandex.metrica.impl.ob.bc;
import com.yandex.metrica.impl.ob.bm;
import com.yandex.metrica.impl.ob.br;
import com.yandex.metrica.impl.ob.bv;
import com.yandex.metrica.impl.ob.bw;
import com.yandex.metrica.impl.ob.cy;
import com.yandex.metrica.impl.ob.j;
import com.yandex.metrica.impl.p;
import com.yandex.metrica.impl.p.a;
import com.yandex.metrica.impl.utils.f;
import com.yandex.metrica.impl.y;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MetricaService
  extends Service
{
  private static final Executor a = new bw();
  private static final ExecutorService b = Executors.newSingleThreadExecutor();
  private static final Map<String, j> c = new HashMap();
  private static final com.yandex.metrica.impl.ob.g d = new com.yandex.metrica.impl.ob.g();
  private final IMetricaService.Stub e = new IMetricaService.Stub()
  {
    private void a(com.yandex.metrica.impl.h paramAnonymousH, Bundle paramAnonymousBundle)
    {
      if (!paramAnonymousH.n())
      {
        paramAnonymousH = new MetricaService.a(MetricaService.this, MetricaService.this, paramAnonymousH, paramAnonymousBundle, getCallingUid());
        MetricaService.a().execute(paramAnonymousH);
      }
    }
    
    public void reportData(Bundle paramAnonymousBundle)
      throws RemoteException
    {
      paramAnonymousBundle.setClassLoader(CounterConfiguration.class.getClassLoader());
      a(com.yandex.metrica.impl.h.b(paramAnonymousBundle), paramAnonymousBundle);
    }
    
    public void reportEvent(String paramAnonymousString1, int paramAnonymousInt, String paramAnonymousString2, Bundle paramAnonymousBundle)
      throws RemoteException
    {
      paramAnonymousBundle.setClassLoader(CounterConfiguration.class.getClassLoader());
      a(new com.yandex.metrica.impl.h(paramAnonymousString2, paramAnonymousString1, paramAnonymousInt), paramAnonymousBundle);
    }
  };
  
  public MetricaService() {}
  
  private void a(Intent paramIntent)
  {
    com.yandex.metrica.impl.h localH;
    Object localObject;
    if (paramIntent != null)
    {
      paramIntent.getExtras().setClassLoader(CounterConfiguration.class.getClassLoader());
      if (!b(paramIntent))
      {
        localH = com.yandex.metrica.impl.h.b(paramIntent.getExtras());
        int i;
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
            localObject = paramIntent.getExtras();
          }
          localObject = bg.a((Bundle)localObject);
          if (localObject == null) {
            i = 1;
          } else {
            i = 0;
          }
          if (i == 0)
          {
            paramIntent = paramIntent.getData().getEncodedAuthority();
            b((CounterConfiguration)localObject, paramIntent);
            b((CounterConfiguration)localObject);
            y.a(this).a(localH.e());
          }
        }
      }
    }
    try
    {
      paramIntent = com.yandex.metrica.impl.ob.h.a(this, (CounterConfiguration)localObject, null, paramIntent);
      paramIntent = new j(this, a, paramIntent, (CounterConfiguration)localObject, d);
      paramIntent.a(localH);
      paramIntent.d();
      stopSelf();
      return;
    }
    catch (Exception paramIntent)
    {
      for (;;) {}
    }
  }
  
  private void b(CounterConfiguration paramCounterConfiguration)
  {
    if (TextUtils.isEmpty(paramCounterConfiguration.h()))
    {
      c(paramCounterConfiguration);
      return;
    }
    String str = br.a().d();
    int i;
    if ((TextUtils.isEmpty(str)) || (TextUtils.equals(paramCounterConfiguration.h(), str))) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0) {
      c(paramCounterConfiguration);
    }
  }
  
  private static void b(CounterConfiguration paramCounterConfiguration, String paramString)
  {
    if (TextUtils.isEmpty(paramCounterConfiguration.f())) {
      paramCounterConfiguration.c(paramString);
    }
  }
  
  private static boolean b(Intent paramIntent)
  {
    return (paramIntent == null) || (paramIntent.getData() == null);
  }
  
  private void c(CounterConfiguration paramCounterConfiguration)
  {
    String str = bg.c(this, paramCounterConfiguration.f());
    if (!be.a(str)) {
      paramCounterConfiguration.e(str);
    }
  }
  
  com.yandex.metrica.impl.ob.h a(com.yandex.metrica.impl.h paramH, CounterConfiguration paramCounterConfiguration, int paramInt)
  {
    Object localObject = null;
    if (p.a(paramH))
    {
      paramCounterConfiguration = paramH.l();
      paramH = getApplicationContext().getPackageManager().getInstalledApplications(0).iterator();
      while (paramH.hasNext()) {
        if (((ApplicationInfo)paramH.next()).packageName.equals(paramCounterConfiguration))
        {
          paramInt = 1;
          break label70;
        }
      }
      paramInt = 0;
      label70:
      paramH = localObject;
      if (paramInt != 0) {
        paramH = com.yandex.metrica.impl.ob.h.a(paramCounterConfiguration);
      }
      return paramH;
    }
    return com.yandex.metrica.impl.ob.h.a(getApplicationContext(), paramCounterConfiguration, Integer.valueOf(paramInt), null);
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    y.a(this).a(this);
    cy.a(this).a();
    return this.e;
  }
  
  public void onCreate()
  {
    super.onCreate();
    f.a(getApplicationContext());
    new az(getApplicationContext()).a(this);
    com.yandex.metrica.impl.utils.g.a().a(getApplicationContext());
    GoogleAdvertisingIdGetter.a().a(this);
    bm localBm = new bm(bc.a(getApplicationContext()).c(), getPackageName());
    bv.a().a(this, localBm.b(null), localBm.h(null));
    br.a().a(getApplicationContext());
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    y.a(this).b(this);
    cy.a(this).b();
  }
  
  public void onStart(Intent paramIntent, int paramInt)
  {
    a(paramIntent);
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    a(paramIntent);
    return 2;
  }
  
  public boolean onUnbind(Intent arg1)
  {
    if (b(???)) {
      return false;
    }
    String str1 = ???.getData().getEncodedAuthority();
    for (;;)
    {
      synchronized (c)
      {
        Iterator localIterator = new HashMap(c).entrySet().iterator();
        if (localIterator.hasNext())
        {
          Object localObject2 = (Map.Entry)localIterator.next();
          String str2 = (String)((Map.Entry)localObject2).getKey();
          localObject2 = (j)((Map.Entry)localObject2).getValue();
          if ((str2 == null) || (localObject2 == null)) {
            break label153;
          }
          if (!str2.startsWith(str1)) {
            break label158;
          }
          break label153;
          if (i != 0)
          {
            c.remove(str2);
            if (localObject2 != null) {
              ((j)localObject2).c();
            }
          }
          continue;
        }
      }
      return true;
      label153:
      int i = 1;
      continue;
      label158:
      i = 0;
    }
  }
  
  private final class a
    implements Runnable
  {
    private final int b;
    private final com.yandex.metrica.impl.h c;
    private final Bundle d;
    private final Context e;
    
    a(Context paramContext, com.yandex.metrica.impl.h paramH, Bundle paramBundle, int paramInt)
    {
      this.e = paramContext.getApplicationContext();
      this.b = paramInt;
      this.c = paramH;
      this.d = paramBundle;
    }
    
    public void run()
    {
      CounterConfiguration localCounterConfiguration1 = bg.a(this.d);
      if (MetricaService.a(localCounterConfiguration1)) {
        return;
      }
      synchronized (MetricaService.b())
      {
        Object localObject2 = MetricaService.this.a(this.c, localCounterConfiguration1, this.b);
        MetricaService.a(localCounterConfiguration1, ((com.yandex.metrica.impl.ob.h)localObject2).b());
        MetricaService.a(MetricaService.this, localCounterConfiguration1);
        MetricaService.a(MetricaService.this, MetricaService.this.getPackageName().equals(localCounterConfiguration1.f()), localCounterConfiguration1.m());
        String str = localCounterConfiguration1.j();
        if (this.d.containsKey("COUNTER_MIGRATION_CFG_OBJ"))
        {
          CounterConfiguration localCounterConfiguration2 = bg.b(this.d);
          if ((localCounterConfiguration2 != null) && (localCounterConfiguration2.B()))
          {
            com.yandex.metrica.impl.ob.h localH = com.yandex.metrica.impl.ob.h.a(MetricaService.this.getApplicationContext(), localCounterConfiguration2, Integer.valueOf(this.b), null);
            if (!MetricaService.b().containsKey(localH.toString()))
            {
              localCounterConfiguration2 = new CounterConfiguration(localCounterConfiguration2);
              localCounterConfiguration2.a(str);
              MetricaService.a(MetricaService.this, localH, localCounterConfiguration2, null).f();
            }
          }
        }
        localObject2 = MetricaService.a(MetricaService.this, (com.yandex.metrica.impl.ob.h)localObject2, localCounterConfiguration1, this.c);
        boolean bool = MetricaService.a((j)localObject2);
        if (bool) {
          return;
        }
        y.a(this.e).a(this.c.e());
        if (!p.a(this.c.c())) {
          ((j)localObject2).a(localCounterConfiguration1);
        }
        if (!MetricaService.a((j)localObject2, this.c)) {
          ((j)localObject2).a(this.c);
        }
        return;
      }
    }
  }
}
