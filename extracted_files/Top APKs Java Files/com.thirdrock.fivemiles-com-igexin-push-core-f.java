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
import com.igexin.sdk.PushService;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
  private com.igexin.a.a.b.d g;
  private com.igexin.a.a.b.c h;
  private com.igexin.push.d.j i;
  private com.igexin.push.d.c j;
  private com.igexin.push.b.b k;
  private final AtomicBoolean m = new AtomicBoolean(false);
  
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
      int n;
      try
      {
        if (!p()) {
          return;
        }
        String str = this.a.getPackageName();
        List localList = this.a.getPackageManager().getInstalledPackages(4);
        if ((localList != null) && (!localList.isEmpty()))
        {
          Iterator localIterator = localList.iterator();
          if (localIterator.hasNext())
          {
            Object localObject = (PackageInfo)localIterator.next();
            if (((((PackageInfo)localObject).applicationInfo.flags & 0x1) != 0) && ((((PackageInfo)localObject).applicationInfo.flags & 0x80) != 1)) {
              continue;
            }
            ServiceInfo[] arrayOfServiceInfo = ((PackageInfo)localObject).services;
            if ((arrayOfServiceInfo == null) || (arrayOfServiceInfo.length == 0)) {
              continue;
            }
            int i1 = arrayOfServiceInfo.length;
            n = 0;
            if (n >= i1) {
              continue;
            }
            ServiceInfo localServiceInfo = arrayOfServiceInfo[n];
            if ((a.o.equals(localServiceInfo.name)) || (a.n.equals(localServiceInfo.name)) || (a.p.equals(localServiceInfo.name)))
            {
              localObject = ((PackageInfo)localObject).packageName;
              if (str.equals(localObject)) {
                continue;
              }
              com.igexin.push.core.c.f.a().d().put(localObject, localServiceInfo.name);
            }
          }
          else
          {
            localList.clear();
          }
        }
        else
        {
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        return;
      }
      n += 1;
    }
  }
  
  private boolean r()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
    localIntentFilter.addAction("com.igexin.sdk.action.snlrefresh");
    localIntentFilter.addAction("com.igexin.sdk.action.snlretire");
    localIntentFilter.addAction(g.W);
    localIntentFilter.addAction("com.igexin.sdk.action.execute");
    localIntentFilter.addAction("com.igexin.sdk.action.doaction");
    localIntentFilter.addAction("android.intent.action.TIME_SET");
    localIntentFilter.addAction("android.intent.action.SCREEN_ON");
    localIntentFilter.addAction("android.intent.action.SCREEN_OFF");
    if (com.igexin.push.f.a.c()) {
      localIntentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
    }
    if (this.a.registerReceiver(n.a(), localIntentFilter) == null) {
      com.igexin.a.a.c.a.b("CoreLogic|InternalPublicReceiver|null");
    }
    localIntentFilter = new IntentFilter();
    localIntentFilter.addDataScheme("package");
    localIntentFilter.addAction("android.intent.action.PACKAGE_ADDED");
    localIntentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
    if (this.a.registerReceiver(m.a(), localIntentFilter) == null) {
      com.igexin.a.a.c.a.b("CoreLogic|InternalPackageReceiver|null");
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
    if ((this.b != null) && (this.b.isAlive())) {}
    while (this.m.getAndSet(true)) {
      return true;
    }
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
    if (g.h.get() == true)
    {
      this.c.sendMessage(paramMessage);
      return true;
    }
    this.d.add(paramMessage);
    return true;
  }
  
  public boolean a(com.igexin.a.a.d.a.f paramF, com.igexin.a.a.d.e paramE)
  {
    if (this.e != null) {
      return this.e.a(paramF);
    }
    return false;
  }
  
  public boolean a(com.igexin.a.a.d.d paramD, com.igexin.a.a.d.e paramE)
  {
    if (this.e != null) {
      return this.e.a(paramD);
    }
    return false;
  }
  
  public boolean a(com.igexin.push.e.b.f paramF)
  {
    boolean bool = false;
    if (paramF != null) {
      bool = com.igexin.a.a.b.d.c().a(paramF, false, true);
    }
    return bool;
  }
  
  public boolean a(String paramString)
  {
    paramString = com.igexin.push.core.a.e.a().e("ss");
    Object localObject;
    if ((g.g != null) && (this.j != null))
    {
      new com.igexin.sdk.a.d(g.g).b();
      g.j = false;
      g.n = false;
      localObject = new com.igexin.push.d.a();
      ((com.igexin.push.d.a)localObject).a(c.g);
      this.j.a((com.igexin.push.d.a)localObject);
      if ((paramString == null) || (!"1".equals(paramString))) {}
    }
    try
    {
      paramString = Runtime.getRuntime().exec("ps").getInputStream();
      if (paramString != null)
      {
        localObject = g.g.getPackageName();
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramString));
        ArrayList localArrayList = new ArrayList();
        String str;
        String[] arrayOfString;
        do
        {
          str = localBufferedReader.readLine();
          if (str == null) {
            break;
          }
          arrayOfString = str.split("\\s+");
          localArrayList.add(arrayOfString);
        } while ((str.indexOf((String)localObject + "/files/gdaemon") == -1) || (arrayOfString.length <= 0));
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
      com.igexin.push.d.a localA = new com.igexin.push.d.a();
      localA.a(c.a);
      this.j.a(localA);
    }
    return true;
  }
  
  public void b()
  {
    try
    {
      com.igexin.a.a.c.a.b("CoreLogic|init starts");
      this.f = ((ConnectivityManager)this.a.getSystemService("connectivity"));
      g.a(this.a);
      this.k = new com.igexin.push.b.b(this.a);
      com.igexin.a.a.c.a.b("CoreLogic|init config");
      com.igexin.push.config.j.a().b();
      com.igexin.a.a.c.a.b("CoreLogic|init receiver");
      r();
      this.g = com.igexin.a.a.b.d.c();
      Object localObject = new com.igexin.push.c.a(this.a, j());
      this.g.a((com.igexin.a.a.d.a.b)localObject);
      this.g.a(this);
      this.g.a(this.a);
      localObject = new com.igexin.push.b.a();
      ((com.igexin.push.b.a)localObject).a(com.igexin.push.core.c.f.a());
      ((com.igexin.push.b.a)localObject).a(com.igexin.push.core.c.c.a());
      ((com.igexin.push.b.a)localObject).a(com.igexin.push.core.c.b.a());
      ((com.igexin.push.b.a)localObject).a(com.igexin.push.core.b.e.a());
      ((com.igexin.push.b.a)localObject).a(com.igexin.push.config.a.a());
      this.g.a((com.igexin.a.a.d.d)localObject, true, false);
      com.igexin.a.a.b.d.c().a(com.igexin.a.b.a.a(g.A.getBytes()));
      g.af = this.g.a(com.igexin.push.e.b.c.g(), false, true);
      g.ag = this.g.a(com.igexin.push.e.b.e.g(), true, true);
      c();
      this.e = com.igexin.push.core.a.e.a();
      d();
      this.i = new com.igexin.push.d.j();
      this.i.a(this.a, this.g, this.e);
      this.j = new com.igexin.push.d.c();
      this.j.a(this.a);
      localObject = new com.igexin.push.d.a();
      ((com.igexin.push.d.a)localObject).a(c.a);
      this.j.a((com.igexin.push.d.a)localObject);
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
      com.igexin.a.a.c.a.b("CoreLogic|init|failed");
      return;
    }
    int n = android.os.Process.myPid();
    this.e.a(n);
    q();
    com.igexin.a.a.c.a.b("CoreLogic|init extensions");
    com.igexin.push.extension.a.a().a(this.a);
    com.igexin.a.a.c.a.b("CoreLogic|init finished");
  }
  
  public boolean b(String paramString)
  {
    if ((g.g != null) && (this.j != null))
    {
      new com.igexin.sdk.a.c(g.g).b();
      g.k = false;
      g.n = false;
      paramString = new com.igexin.push.d.a();
      paramString.a(c.g);
      this.j.a(paramString);
    }
    return true;
  }
  
  public com.igexin.push.e.b.a c()
  {
    com.igexin.push.e.b.a localA = com.igexin.push.e.b.a.g();
    localA.a(new com.igexin.push.a.a.a());
    localA.a(new com.igexin.push.a.a.b());
    localA.a(new com.igexin.push.a.a.d());
    localA.a(com.igexin.push.a.a.c.c());
    g.ah = this.g.a(localA, false, true);
    this.a.sendBroadcast(new Intent());
    return localA;
  }
  
  public void d()
  {
    if (!TextUtils.isEmpty(g.x)) {}
    for (;;)
    {
      return;
      try
      {
        if (com.igexin.push.f.a.a())
        {
          WifiInfo localWifiInfo = ((WifiManager)this.a.getSystemService("wifi")).getConnectionInfo();
          if (localWifiInfo != null)
          {
            com.igexin.push.core.c.f.a().a(localWifiInfo.getMacAddress());
            return;
          }
        }
      }
      catch (Exception localException) {}
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
  
  public com.igexin.a.a.b.c f()
  {
    if (this.h == null) {
      this.h = com.igexin.push.c.a.c.a();
    }
    return this.h;
  }
  
  public com.igexin.push.d.j g()
  {
    return this.i;
  }
  
  public com.igexin.push.d.c h()
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
      this.a.unregisterReceiver(com.igexin.a.a.b.d.c());
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
      long l2 = (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024L / 1024L;
      if ((l2 > 30L) && (l1 > 100L)) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
      return false;
    }
    catch (Throwable localThrowable) {}
  }
}
