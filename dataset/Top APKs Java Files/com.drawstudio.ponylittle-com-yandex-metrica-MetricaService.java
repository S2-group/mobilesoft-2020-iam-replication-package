package com.yandex.metrica;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
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
  private static final Map c = new HashMap();
  private static final q d = new q();
  private final IMetricaService.Stub e = new MetricaService.1(this);
  
  public MetricaService() {}
  
  private void a(Intent paramIntent)
  {
    h localH;
    Object localObject;
    if (paramIntent != null)
    {
      paramIntent.getExtras().setClassLoader(CounterConfiguration.class.getClassLoader());
      if (!b(paramIntent))
      {
        localH = h.b(paramIntent.getExtras());
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
          localObject = CounterConfiguration.b((Bundle)localObject);
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
      paramIntent = r.a(this, (CounterConfiguration)localObject, null, paramIntent);
      paramIntent = new t(this, a, paramIntent, (CounterConfiguration)localObject, d);
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
    String str = ce.a().d();
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
    boolean bool = p.a(paramH);
    Object localObject = null;
    if (bool)
    {
      paramCounterConfiguration = paramH.l();
      paramH = getApplicationContext().getPackageManager();
      int i = 0;
      paramH = paramH.getInstalledApplications(0).iterator();
      do
      {
        paramInt = i;
        if (!paramH.hasNext()) {
          break;
        }
      } while (!((ApplicationInfo)paramH.next()).packageName.equals(paramCounterConfiguration));
      paramInt = 1;
      paramH = localObject;
      if (paramInt != 0) {
        return r.a(paramCounterConfiguration);
      }
    }
    else
    {
      paramH = r.a(getApplicationContext(), paramCounterConfiguration, Integer.valueOf(paramInt), null);
    }
    return paramH;
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    if ("com.yandex.metrica.ACTION_BIND_TO_LOCAL_SERVER".equals(paramIntent.getAction())) {
      return new MetricaService.a();
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
        boolean bool = localIterator.hasNext();
        int j = 1;
        if (bool)
        {
          Object localObject2 = (Map.Entry)localIterator.next();
          String str2 = (String)((Map.Entry)localObject2).getKey();
          localObject2 = (t)((Map.Entry)localObject2).getValue();
          i = j;
          if (str2 != null)
          {
            i = j;
            if (localObject2 != null)
            {
              if (!str2.startsWith(str1)) {
                break label199;
              }
              i = j;
            }
          }
          if (i == 0) {
            continue;
          }
          c.remove(str2);
          if (localObject2 == null) {
            continue;
          }
          ((t)localObject2).c();
        }
        else
        {
          if (c.isEmpty()) {
            dg.a(this).b();
          }
          return true;
        }
      }
      label199:
      int i = 0;
    }
  }
}
