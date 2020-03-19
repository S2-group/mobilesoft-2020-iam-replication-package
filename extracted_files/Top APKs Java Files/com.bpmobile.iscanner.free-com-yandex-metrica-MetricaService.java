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
import com.yandex.metrica.impl.ob.bc;
import com.yandex.metrica.impl.ob.bp;
import com.yandex.metrica.impl.ob.bu;
import com.yandex.metrica.impl.ob.by;
import com.yandex.metrica.impl.ob.bz;
import com.yandex.metrica.impl.ob.dg;
import com.yandex.metrica.impl.ob.g;
import com.yandex.metrica.impl.ob.j;
import com.yandex.metrica.impl.p;
import com.yandex.metrica.impl.p.a;
import com.yandex.metrica.impl.utils.i;
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
  private static final Executor a = new bz();
  private static final ExecutorService b = Executors.newSingleThreadExecutor();
  private static final Map<String, j> c = new HashMap();
  private static final g d = new g();
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
    int i;
    Object localObject;
    if (paramIntent != null)
    {
      paramIntent.getExtras().setClassLoader(CounterConfiguration.class.getClassLoader());
      if (!b(paramIntent))
      {
        localH = com.yandex.metrica.impl.h.b(paramIntent.getExtras());
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
            break label186;
          }
        }
      }
    }
    for (;;)
    {
      localObject = CounterConfiguration.b((Bundle)localObject);
      if (localObject == null)
      {
        i = 1;
        if (i == 0)
        {
          paramIntent = paramIntent.getData().getEncodedAuthority();
          b((CounterConfiguration)localObject, paramIntent);
          b((CounterConfiguration)localObject);
          y.a(this).a(localH.e());
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
        label186:
        localObject = paramIntent.getExtras();
        continue;
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
      String str = bu.a().d();
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
  
  private static boolean b(Intent paramIntent)
  {
    return (paramIntent == null) || (paramIntent.getData() == null);
  }
  
  private void c(CounterConfiguration paramCounterConfiguration)
  {
    String str = bu.g(this, paramCounterConfiguration.f());
    if (!TextUtils.isEmpty(str)) {
      paramCounterConfiguration.e(str);
    }
  }
  
  com.yandex.metrica.impl.ob.h a(com.yandex.metrica.impl.h paramH, CounterConfiguration paramCounterConfiguration, int paramInt)
  {
    if (p.a(paramH))
    {
      paramH = paramH.l();
      paramCounterConfiguration = getApplicationContext().getPackageManager().getInstalledApplications(0).iterator();
      do
      {
        if (!paramCounterConfiguration.hasNext()) {
          break;
        }
      } while (!((ApplicationInfo)paramCounterConfiguration.next()).packageName.equals(paramH));
      for (paramInt = 1; paramInt != 0; paramInt = 0) {
        return com.yandex.metrica.impl.ob.h.a(paramH);
      }
    }
    return com.yandex.metrica.impl.ob.h.a(getApplicationContext(), paramCounterConfiguration, Integer.valueOf(paramInt), null);
    return null;
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    y.a(this).a(this);
    dg.a(this).a();
    return this.e;
  }
  
  public void onCreate()
  {
    super.onCreate();
    com.yandex.metrica.impl.utils.h.a(getApplicationContext());
    new az(getApplicationContext()).a(this);
    i.a().a(getApplicationContext());
    GoogleAdvertisingIdGetter.a().a(this);
    bp localBp = new bp(bc.a(getApplicationContext()).c(), getPackageName());
    by.a().a(this, localBp.b(null), localBp.h(null));
    bu.a().a(getApplicationContext());
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    y.a(this).b(this);
    dg.a(this).b();
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
        if (!localIterator.hasNext()) {
          break label151;
        }
        Object localObject2 = (Map.Entry)localIterator.next();
        String str2 = (String)((Map.Entry)localObject2).getKey();
        localObject2 = (j)((Map.Entry)localObject2).getValue();
        if ((str2 == null) || (localObject2 == null)) {
          break label155;
        }
        if (str2.startsWith(str1))
        {
          break label155;
          if (i == 0) {
            continue;
          }
          c.remove(str2);
          if (localObject2 == null) {
            continue;
          }
          ((j)localObject2).c();
        }
      }
      int i = 0;
      continue;
      label151:
      return true;
      label155:
      i = 1;
    }
  }
  
  final class a
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
      if (MetricaService.a(localCounterConfiguration1)) {
        return;
      }
      Object localObject;
      synchronized (MetricaService.b())
      {
        localObject = MetricaService.this.a(this.c, localCounterConfiguration1, this.b);
        MetricaService.a(localCounterConfiguration1, ((com.yandex.metrica.impl.ob.h)localObject).b());
        MetricaService.a(MetricaService.this, localCounterConfiguration1);
        MetricaService.a(MetricaService.this, MetricaService.this.getPackageName().equals(localCounterConfiguration1.f()), localCounterConfiguration1.m());
        String str = localCounterConfiguration1.j();
        if (this.d.containsKey("COUNTER_MIGRATION_CFG_OBJ"))
        {
          CounterConfiguration localCounterConfiguration3 = a(this.d);
          if ((localCounterConfiguration3 != null) && (localCounterConfiguration3.C()))
          {
            com.yandex.metrica.impl.ob.h localH = com.yandex.metrica.impl.ob.h.a(MetricaService.this.getApplicationContext(), localCounterConfiguration3, Integer.valueOf(this.b), null);
            if (!MetricaService.b().containsKey(localH.toString()))
            {
              localCounterConfiguration3 = new CounterConfiguration(localCounterConfiguration3);
              localCounterConfiguration3.a(str);
              MetricaService.a(MetricaService.this, localH, localCounterConfiguration3, null).f();
            }
          }
        }
        localObject = MetricaService.a(MetricaService.this, (com.yandex.metrica.impl.ob.h)localObject, localCounterConfiguration1, this.c);
        if (MetricaService.a((j)localObject)) {
          return;
        }
      }
      y.a(this.e).a(this.c.e());
      if (!p.a(this.c.c())) {
        ((j)localObject).a(localCounterConfiguration2);
      }
      if (!MetricaService.a((j)localObject, this.c)) {
        ((j)localObject).a(this.c);
      }
    }
  }
}
