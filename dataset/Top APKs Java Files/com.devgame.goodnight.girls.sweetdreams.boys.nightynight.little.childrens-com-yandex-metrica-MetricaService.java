package com.yandex.metrica;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.yandex.metrica.impl.GoogleAdvertisingIdGetter;
import com.yandex.metrica.impl.az;
import com.yandex.metrica.impl.h;
import com.yandex.metrica.impl.ob.bm;
import com.yandex.metrica.impl.ob.bz;
import com.yandex.metrica.impl.ob.ce;
import com.yandex.metrica.impl.ob.ci;
import com.yandex.metrica.impl.ob.cj;
import com.yandex.metrica.impl.ob.dg;
import com.yandex.metrica.impl.ob.dh;
import com.yandex.metrica.impl.ob.du;
import com.yandex.metrica.impl.ob.g;
import com.yandex.metrica.impl.ob.o;
import com.yandex.metrica.impl.ob.q;
import com.yandex.metrica.impl.ob.r;
import com.yandex.metrica.impl.ob.t;
import com.yandex.metrica.impl.p;
import com.yandex.metrica.impl.p.a;
import com.yandex.metrica.impl.utils.i;
import com.yandex.metrica.impl.utils.j;
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
import org.json.JSONException;

public class MetricaService
  extends Service
{
  private static final Executor a = new cj();
  private static final ExecutorService b = Executors.newSingleThreadExecutor();
  private static final Map<String, t> c = new HashMap();
  private static final q d = new q();
  private final IMetricaService.Stub e = new IMetricaService.Stub()
  {
    private void a(h paramAnonymousH, Bundle paramAnonymousBundle)
    {
      if (!paramAnonymousH.n())
      {
        paramAnonymousH = new MetricaService.b(MetricaService.this, MetricaService.this, paramAnonymousH, paramAnonymousBundle, getCallingUid());
        MetricaService.a().execute(paramAnonymousH);
      }
    }
    
    public void reportData(Bundle paramAnonymousBundle)
      throws RemoteException
    {
      paramAnonymousBundle.setClassLoader(CounterConfiguration.class.getClassLoader());
      a(h.b(paramAnonymousBundle), paramAnonymousBundle);
    }
    
    public void reportEvent(String paramAnonymousString1, int paramAnonymousInt, String paramAnonymousString2, Bundle paramAnonymousBundle)
      throws RemoteException
    {
      paramAnonymousBundle.setClassLoader(CounterConfiguration.class.getClassLoader());
      a(new h(paramAnonymousString2, paramAnonymousString1, paramAnonymousInt), paramAnonymousBundle);
    }
  };
  
  public MetricaService() {}
  
  private void a(Intent paramIntent)
  {
    h localH;
    int i;
    Object localObject;
    if (paramIntent != null)
    {
      paramIntent.getExtras().setClassLoader(CounterConfiguration.class.getClassLoader());
      if (!b(paramIntent))
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
        paramIntent = r.a(this, (CounterConfiguration)localObject, null, paramIntent);
        paramIntent = new t(this, a, paramIntent, (CounterConfiguration)localObject, d);
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
      String str = ce.a().d();
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
    String str = ce.g(this, paramCounterConfiguration.f());
    if (!TextUtils.isEmpty(str)) {
      paramCounterConfiguration.e(str);
    }
  }
  
  r a(h paramH, CounterConfiguration paramCounterConfiguration, int paramInt)
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
        return r.a(paramH);
      }
    }
    return r.a(getApplicationContext(), paramCounterConfiguration, Integer.valueOf(paramInt), null);
    return null;
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    if ("com.yandex.metrica.ACTION_BIND_TO_LOCAL_SERVER".equals(paramIntent.getAction())) {
      return new a();
    }
    dg.a(this).a();
    y.a(this).a(this);
    du.a(this).a();
    return this.e;
  }
  
  public void onCreate()
  {
    super.onCreate();
    i.a(getApplicationContext());
    new az(getApplicationContext()).a(this);
    j.a().a(getApplicationContext());
    GoogleAdvertisingIdGetter.a().a(this);
    Object localObject = new bz(bm.a(getApplicationContext()).c(), getPackageName());
    ci.a().a(this, ((bz)localObject).b(null), ((bz)localObject).h(null));
    localObject = ((bz)localObject).e();
    if (localObject != null) {}
    try
    {
      g.a().b(new o(new dh((String)localObject)));
      ce.a().a(getApplicationContext());
      return;
    }
    catch (JSONException localJSONException)
    {
      for (;;) {}
    }
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    y.a(this).b(this);
    du.a(this).b();
  }
  
  public void onRebind(Intent paramIntent)
  {
    super.onRebind(paramIntent);
    dg.a(this).a();
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
    if ("com.yandex.metrica.ACTION_BIND_TO_LOCAL_SERVER".equals(???.getAction())) {
      return false;
    }
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
          break label166;
        }
        Object localObject2 = (Map.Entry)localIterator.next();
        String str2 = (String)((Map.Entry)localObject2).getKey();
        localObject2 = (t)((Map.Entry)localObject2).getValue();
        if ((str2 == null) || (localObject2 == null)) {
          break label188;
        }
        if (str2.startsWith(str1))
        {
          break label188;
          if (i == 0) {
            continue;
          }
          c.remove(str2);
          if (localObject2 == null) {
            continue;
          }
          ((t)localObject2).c();
        }
      }
      int i = 0;
      continue;
      label166:
      if (c.isEmpty()) {
        dg.a(this).b();
      }
      return true;
      label188:
      i = 1;
    }
  }
  
  static class a
    extends Binder
  {
    a() {}
  }
  
  private final class b
    implements Runnable
  {
    private final int b;
    private final h c;
    private final Bundle d;
    private final Context e;
    
    b(Context paramContext, h paramH, Bundle paramBundle, int paramInt)
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
      if (MetricaService.a(localCounterConfiguration1)) {}
      Object localObject;
      do
      {
        return;
        localObject = MetricaService.this.a(this.c, localCounterConfiguration1, this.b);
      } while (localObject == null);
      MetricaService.a(localCounterConfiguration1, ((r)localObject).b());
      synchronized (MetricaService.b())
      {
        MetricaService.a(MetricaService.this, localCounterConfiguration1);
        MetricaService.a(MetricaService.this, MetricaService.this.getPackageName().equals(localCounterConfiguration1.f()), localCounterConfiguration1.m());
        String str = localCounterConfiguration1.j();
        if (this.d.containsKey("COUNTER_MIGRATION_CFG_OBJ"))
        {
          CounterConfiguration localCounterConfiguration3 = a(this.d);
          if ((localCounterConfiguration3 != null) && (localCounterConfiguration3.C()))
          {
            r localR = r.a(MetricaService.this.getApplicationContext(), localCounterConfiguration3, Integer.valueOf(this.b), null);
            if (!MetricaService.b().containsKey(localR.toString()))
            {
              localCounterConfiguration3 = new CounterConfiguration(localCounterConfiguration3);
              localCounterConfiguration3.a(str);
              MetricaService.a(MetricaService.this, localR, localCounterConfiguration3, null).f();
            }
          }
        }
        localObject = MetricaService.a(MetricaService.this, (r)localObject, localCounterConfiguration1, this.c);
        if (MetricaService.a((t)localObject)) {
          return;
        }
      }
      y.a(this.e).a(this.c.e());
      if (!p.a(this.c.c())) {
        ((t)localObject).a(localCounterConfiguration2);
      }
      if (!MetricaService.a((t)localObject, this.c)) {
        ((t)localObject).a(this.c);
      }
    }
  }
}
