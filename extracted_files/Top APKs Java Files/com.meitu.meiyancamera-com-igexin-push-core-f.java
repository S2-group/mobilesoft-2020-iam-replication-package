package com.igexin.push.core;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.igexin.push.c.i;
import com.igexin.push.config.k;
import com.igexin.push.core.b.y;
import com.igexin.push.e.j;
import com.igexin.sdk.PushService;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class f
  implements com.igexin.a.a.d.a.c
{
  private static f l;
  private Context a;
  private h b = new h();
  private Handler c;
  private ConcurrentLinkedQueue d = new ConcurrentLinkedQueue();
  private com.igexin.push.core.a.e e;
  private ConnectivityManager f;
  private com.igexin.a.a.b.c g;
  private com.igexin.a.a.b.b h;
  private j i;
  private com.igexin.push.e.c j;
  private com.igexin.push.b.b k;
  private final int m = 100;
  private final int n = 30;
  private final AtomicBoolean o = new AtomicBoolean(false);
  
  private f() {}
  
  public static f a()
  {
    if (l == null) {
      l = new f();
    }
    return l;
  }
  
  private void q()
  {
    for (;;)
    {
      try
      {
        if (!com.igexin.push.config.m.p) {
          break;
        }
        if (!p()) {
          return;
        }
        String str = this.a.getPackageName();
        Object localObject1 = this.a.getPackageManager().getInstalledPackages(4);
        if ((localObject1 == null) || (((List)localObject1).isEmpty())) {
          break;
        }
        localObject1 = ((List)localObject1).iterator();
        if (!((Iterator)localObject1).hasNext()) {
          break;
        }
        Object localObject2 = (PackageInfo)((Iterator)localObject1).next();
        if (((((PackageInfo)localObject2).applicationInfo.flags & 0x1) == 0) || ((((PackageInfo)localObject2).applicationInfo.flags & 0x80) == 1))
        {
          ServiceInfo[] arrayOfServiceInfo = ((PackageInfo)localObject2).services;
          if ((arrayOfServiceInfo != null) && (arrayOfServiceInfo.length != 0))
          {
            int i2 = arrayOfServiceInfo.length;
            int i1 = 0;
            if (i1 < i2)
            {
              ServiceInfo localServiceInfo = arrayOfServiceInfo[i1];
              if ((a.o.equals(localServiceInfo.name)) || (a.n.equals(localServiceInfo.name)) || (a.p.equals(localServiceInfo.name)))
              {
                localObject2 = ((PackageInfo)localObject2).packageName;
                if (!str.equals(localObject2)) {
                  com.igexin.push.core.b.f.a().d().put(localObject2, localServiceInfo.name);
                }
              }
              else
              {
                i1 += 1;
              }
            }
          }
        }
      }
      catch (Throwable localThrowable)
      {
        return;
      }
    }
  }
  
  private boolean r()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
    localIntentFilter.addAction("com.igexin.sdk.action.snlrefresh");
    localIntentFilter.addAction("com.igexin.sdk.action.snlretire");
    localIntentFilter.addAction(g.V);
    localIntentFilter.addAction("com.igexin.sdk.action.execute");
    localIntentFilter.addAction("com.igexin.sdk.action.doaction");
    localIntentFilter.addAction("android.intent.action.TIME_SET");
    localIntentFilter.addAction("android.intent.action.SCREEN_ON");
    localIntentFilter.addAction("android.intent.action.SCREEN_OFF");
    if (com.igexin.push.util.a.c()) {
      localIntentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
    }
    if (this.a.registerReceiver(n.a(), localIntentFilter) == null) {
      com.igexin.a.a.c.a.b("CoreLogic|InternalPublicReceiver|Failed");
    }
    localIntentFilter = new IntentFilter();
    localIntentFilter.addDataScheme("package");
    localIntentFilter.addAction("android.intent.action.PACKAGE_ADDED");
    localIntentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
    if (this.a.registerReceiver(m.a(), localIntentFilter) == null) {
      com.igexin.a.a.c.a.b("CoreLogic|InternalPackageReceiver|Failed");
    }
    return true;
  }
  
  public void a(e paramE)
  {
    this.c = paramE;
  }
  
  public boolean a(Context paramContext)
  {
    this.a = paramContext;
    if ((this.b != null) && (this.b.isAlive())) {
      com.igexin.a.a.c.a.b("CoreLogic|coreThread is alive +++++");
    }
    while (this.o.getAndSet(true)) {
      return true;
    }
    com.igexin.a.a.c.a.b("CoreLogic|start coreThread +++++");
    this.b.start();
    return true;
  }
  
  public boolean a(Intent paramIntent)
  {
    if (g.g != null)
    {
      g.g.sendBroadcast(paramIntent);
      return true;
    }
    return false;
  }
  
  public boolean a(Message paramMessage)
  {
    if (g.h.get()) {
      this.c.sendMessage(paramMessage);
    }
    for (;;)
    {
      return true;
      this.d.add(paramMessage);
    }
  }
  
  public boolean a(com.igexin.a.a.d.a.f paramF, com.igexin.a.a.d.e paramE)
  {
    return (this.e != null) && (this.e.a(paramF));
  }
  
  public boolean a(com.igexin.a.a.d.d paramD, com.igexin.a.a.d.e paramE)
  {
    return (this.e != null) && (this.e.a(paramD));
  }
  
  public boolean a(com.igexin.push.f.b.f paramF)
  {
    return (paramF != null) && (com.igexin.a.a.b.c.b().a(paramF, false, true));
  }
  
  public boolean a(String paramString)
  {
    paramString = com.igexin.push.core.a.e.a().d("ss");
    Object localObject;
    if ((g.g != null) && (this.j != null))
    {
      new com.igexin.sdk.a.d(g.g).b();
      g.j = false;
      g.o = false;
      localObject = new com.igexin.push.e.a();
      ((com.igexin.push.e.a)localObject).a(c.g);
      this.j.a((com.igexin.push.e.a)localObject);
      if ((paramString == null) || (!"1".equals(paramString))) {}
    }
    try
    {
      paramString = Runtime.getRuntime().exec("ps").getInputStream();
      if (paramString != null)
      {
        localObject = g.g.getPackageName();
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramString));
        String str;
        String[] arrayOfString;
        do
        {
          str = localBufferedReader.readLine();
          if (str == null) {
            break;
          }
          arrayOfString = str.split("\\s+");
        } while ((!str.contains((String)localObject + "/files/gdaemon")) || (arrayOfString.length <= 0));
        android.os.Process.killProcess(Integer.valueOf(arrayOfString[1]).intValue());
        localBufferedReader.close();
        paramString.close();
      }
    }
    catch (Exception paramString)
    {
      for (;;) {}
    }
    e();
    return true;
  }
  
  public boolean a(boolean paramBoolean)
  {
    if ((g.g != null) && (this.j != null))
    {
      new com.igexin.sdk.a.d(g.g).a();
      g.j = true;
      if (!new com.igexin.sdk.a.b(g.g).b())
      {
        new com.igexin.sdk.a.c(g.g).a();
        g.k = true;
        new com.igexin.sdk.a.b(g.g).a();
      }
      if (paramBoolean)
      {
        new com.igexin.sdk.a.c(g.g).a();
        g.k = true;
      }
      com.igexin.push.e.a localA = new com.igexin.push.e.a();
      localA.a(c.a);
      this.j.a(localA);
    }
    return true;
  }
  
  public void b()
  {
    try
    {
      this.f = ((ConnectivityManager)this.a.getSystemService("connectivity"));
      g.a(this.a);
      this.k = new com.igexin.push.b.b(this.a);
      k.a().b();
      r();
      this.g = com.igexin.a.a.b.c.b();
      Object localObject = new com.igexin.push.d.a(this.a, j());
      this.g.a((com.igexin.a.a.d.a.b)localObject);
      this.g.a(this);
      this.g.a(this.a);
      localObject = new com.igexin.push.b.a();
      ((com.igexin.push.b.a)localObject).a(com.igexin.push.core.b.f.a());
      ((com.igexin.push.b.a)localObject).a(com.igexin.push.core.b.c.a());
      ((com.igexin.push.b.a)localObject).a(com.igexin.push.core.b.b.a());
      ((com.igexin.push.b.a)localObject).a(com.igexin.push.config.a.a());
      ((com.igexin.push.b.a)localObject).a(y.a());
      this.g.a((com.igexin.a.a.d.d)localObject, true, false);
      com.igexin.a.a.b.c.b().a(com.igexin.a.b.a.a(g.B.getBytes()));
      g.ae = this.g.a(com.igexin.push.f.b.c.g(), false, true);
      g.af = this.g.a(com.igexin.push.f.b.e.g(), true, true);
      i.a().c();
      c();
      this.e = com.igexin.push.core.a.e.a();
      d();
      this.i = new j();
      this.i.a(this.a, this.g, this.e);
      this.j = new com.igexin.push.e.c();
      this.j.a(this.a);
      localObject = new com.igexin.push.e.a();
      ((com.igexin.push.e.a)localObject).a(c.a);
      this.j.a((com.igexin.push.e.a)localObject);
      com.igexin.push.a.a.c.c().d();
      g.h.set(true);
      localObject = this.d.iterator();
      while (((Iterator)localObject).hasNext())
      {
        Message localMessage = (Message)((Iterator)localObject).next();
        if (this.c != null) {
          this.c.sendMessage(localMessage);
        }
      }
      com.igexin.push.core.a.e.a().t();
    }
    catch (Exception localException)
    {
      com.igexin.a.a.c.a.b("CoreLogic CoreLogic|init|failed");
      return;
    }
    int i1 = android.os.Process.myPid();
    this.e.a(i1);
    q();
    com.igexin.push.extension.a.a().a(this.a);
  }
  
  public boolean b(String paramString)
  {
    if ((g.g != null) && (this.j != null))
    {
      new com.igexin.sdk.a.c(g.g).b();
      g.k = false;
      g.o = false;
      paramString = new com.igexin.push.e.a();
      paramString.a(c.g);
      this.j.a(paramString);
    }
    return true;
  }
  
  public com.igexin.push.f.b.a c()
  {
    com.igexin.push.f.b.a localA = com.igexin.push.f.b.a.g();
    localA.a(new com.igexin.push.a.a.a());
    localA.a(new com.igexin.push.a.a.b());
    localA.a(new com.igexin.push.a.a.d());
    localA.a(com.igexin.push.a.a.c.c());
    g.ag = this.g.a(localA, false, true);
    this.a.sendBroadcast(new Intent());
    return localA;
  }
  
  public void d()
  {
    if (!TextUtils.isEmpty(g.y)) {}
    for (;;)
    {
      return;
      try
      {
        if (com.igexin.push.util.a.a())
        {
          WifiInfo localWifiInfo = ((WifiManager)this.a.getSystemService("wifi")).getConnectionInfo();
          if (localWifiInfo != null) {
            com.igexin.push.core.b.f.a().a(localWifiInfo.getMacAddress());
          }
          com.igexin.a.a.c.a.b("CoreLogic mac:" + g.y);
          return;
        }
      }
      catch (Throwable localThrowable) {}
    }
  }
  
  public void e()
  {
    Intent localIntent = new Intent(this.a.getApplicationContext(), PushService.class);
    localIntent.putExtra("action", "stopUserService");
    this.a.getApplicationContext().startService(localIntent);
    localIntent = new Intent(this.a, PushService.class);
    this.a.stopService(localIntent);
  }
  
  public com.igexin.a.a.b.b f()
  {
    if (this.h == null) {
      this.h = com.igexin.push.d.a.c.a();
    }
    return this.h;
  }
  
  public j g()
  {
    return this.i;
  }
  
  public com.igexin.push.e.c h()
  {
    return this.j;
  }
  
  public com.igexin.push.core.a.e i()
  {
    return this.e;
  }
  
  public ConnectivityManager j()
  {
    return this.f;
  }
  
  public com.igexin.push.b.b k()
  {
    return this.k;
  }
  
  public void l()
  {
    try
    {
      this.a.unregisterReceiver(com.igexin.a.a.b.c.b());
      try
      {
        this.a.unregisterReceiver(m.a());
        try
        {
          this.a.unregisterReceiver(n.a());
          try
          {
            com.igexin.push.extension.a.a().b();
            return;
          }
          catch (Throwable localThrowable) {}
        }
        catch (Exception localException1)
        {
          for (;;) {}
        }
      }
      catch (Exception localException2)
      {
        for (;;) {}
      }
    }
    catch (Exception localException3)
    {
      for (;;) {}
    }
  }
  
  public String m()
  {
    if (this.f == null) {}
    NetworkInfo localNetworkInfo;
    do
    {
      do
      {
        return null;
        localNetworkInfo = this.f.getActiveNetworkInfo();
      } while (localNetworkInfo == null);
      if (localNetworkInfo.getType() == 1) {
        return "wifi";
      }
    } while (localNetworkInfo.getType() != 0);
    return "mobile";
  }
  
  public boolean n()
  {
    return true;
  }
  
  public long o()
  {
    return 94808L;
  }
  
  public boolean p()
  {
    try
    {
      ActivityManager localActivityManager = (ActivityManager)g.g.getSystemService("activity");
      ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
      localActivityManager.getMemoryInfo(localMemoryInfo);
      long l1 = localMemoryInfo.availMem / 1024L / 1024L;
      if (localMemoryInfo.lowMemory)
      {
        com.igexin.a.a.c.a.b("CoreLogic", "system in lowMemory, available menmory = " + l1 + "M");
        return false;
      }
      if (l1 >= 100L)
      {
        l1 = (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024L / 1024L;
        if (l1 > 30L) {}
        for (boolean bool = true;; bool = false) {
          return bool;
        }
      }
      return false;
    }
    catch (Throwable localThrowable) {}
  }
}
