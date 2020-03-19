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
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.igexin.assist.sdk.AssistPushManager;
import com.igexin.push.c.i;
import com.igexin.push.config.k;
import com.igexin.push.core.b.af;
import com.igexin.push.e.j;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class f
  implements com.igexin.b.a.d.a.b
{
  private static f l;
  private Context a;
  private h b = new h();
  private Handler c;
  private ConcurrentLinkedQueue<Message> d = new ConcurrentLinkedQueue();
  private com.igexin.push.core.a.f e;
  private ConnectivityManager f;
  private com.igexin.b.a.b.c g;
  private com.igexin.b.a.b.b h;
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
        Object localObject = this.a.getPackageManager().getInstalledPackages(4);
        if ((localObject == null) || (((List)localObject).isEmpty())) {
          break;
        }
        ArrayList localArrayList = new ArrayList();
        if (!com.igexin.push.config.m.H.equals("none")) {
          localArrayList.addAll(Arrays.asList(com.igexin.push.config.m.H.split(",")));
        }
        localObject = ((List)localObject).iterator();
        if (!((Iterator)localObject).hasNext()) {
          break;
        }
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if ((((localPackageInfo.applicationInfo.flags & 0x1) == 0) || ((localPackageInfo.applicationInfo.flags & 0x80) != 0)) && (!com.igexin.push.util.a.a(localPackageInfo.applicationInfo.packageName, localArrayList)))
        {
          ServiceInfo[] arrayOfServiceInfo = localPackageInfo.services;
          if ((arrayOfServiceInfo != null) && (arrayOfServiceInfo.length != 0))
          {
            int i2 = arrayOfServiceInfo.length;
            int i1 = 0;
            if (i1 < i2)
            {
              ServiceInfo localServiceInfo = arrayOfServiceInfo[i1];
              if (com.igexin.push.util.a.a(localServiceInfo, localPackageInfo))
              {
                if (!str.equals(localPackageInfo.packageName)) {
                  com.igexin.push.core.b.g.a().d().put(localPackageInfo.packageName, localServiceInfo.name);
                }
              }
              else {
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
      com.igexin.b.a.c.b.a("CoreLogic|InternalPublicReceiver|Failed");
    }
    localIntentFilter = new IntentFilter();
    localIntentFilter.addDataScheme("package");
    localIntentFilter.addAction("android.intent.action.PACKAGE_ADDED");
    localIntentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
    if (this.a.registerReceiver(m.a(), localIntentFilter) == null) {
      com.igexin.b.a.c.b.a("CoreLogic|InternalPackageReceiver|Failed");
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
      com.igexin.b.a.c.b.a("CoreLogic|coreThread is alive +++++");
    }
    while (this.o.getAndSet(true)) {
      return true;
    }
    com.igexin.b.a.c.b.a("CoreLogic|start coreThread +++++");
    this.b.start();
    return true;
  }
  
  public boolean a(Message paramMessage)
  {
    if (g.g.get()) {
      this.c.sendMessage(paramMessage);
    }
    for (;;)
    {
      return true;
      this.d.add(paramMessage);
    }
  }
  
  public boolean a(com.igexin.b.a.d.a.e paramE, com.igexin.b.a.d.f paramF)
  {
    return (this.e != null) && (this.e.a(paramE));
  }
  
  public boolean a(com.igexin.b.a.d.e paramE, com.igexin.b.a.d.f paramF)
  {
    return (this.e != null) && (this.e.a(paramE));
  }
  
  public boolean a(com.igexin.push.f.b.h paramH)
  {
    return (paramH != null) && (com.igexin.b.a.b.c.b().a(paramH, false, true));
  }
  
  public boolean a(String paramString)
  {
    paramString = com.igexin.push.core.a.f.a().d("ss");
    Object localObject;
    if ((g.f != null) && (this.j != null))
    {
      new com.igexin.sdk.a.d(g.f).b();
      g.i = false;
      g.m = false;
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
        localObject = g.f.getPackageName();
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
    com.igexin.b.a.c.b.a("CoreLogic|start sdkSwitch isSlave = " + paramBoolean);
    if ((g.f != null) && (this.j != null))
    {
      new com.igexin.sdk.a.d(g.f).a();
      g.i = true;
      if (!new com.igexin.sdk.a.b(g.f).b())
      {
        new com.igexin.sdk.a.c(g.f).a();
        g.j = true;
        new com.igexin.sdk.a.b(g.f).a();
      }
      if (paramBoolean)
      {
        new com.igexin.sdk.a.c(g.f).a();
        g.j = true;
      }
      com.igexin.b.a.c.b.a("CoreLogic|snlCoordinator.doEvent start ++++");
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
      this.k = new com.igexin.push.b.b(this.a);
      this.f = ((ConnectivityManager)this.a.getSystemService("connectivity"));
      g.a(this.a);
      k.a().b();
      r();
      this.g = com.igexin.b.a.b.c.b();
      Object localObject = new com.igexin.push.d.a(this.a);
      this.g.a((com.igexin.b.a.d.a.a)localObject);
      this.g.a(this);
      this.g.a(this.a);
      localObject = new com.igexin.push.b.a();
      ((com.igexin.push.b.a)localObject).a(com.igexin.push.core.b.g.a());
      ((com.igexin.push.b.a)localObject).a(com.igexin.push.core.b.d.a());
      ((com.igexin.push.b.a)localObject).a(com.igexin.push.core.b.b.a());
      ((com.igexin.push.b.a)localObject).a(com.igexin.push.config.a.a());
      ((com.igexin.push.b.a)localObject).a(af.a());
      this.g.a((com.igexin.b.a.d.e)localObject, true, false);
      com.igexin.b.a.b.c.b().a(com.igexin.b.b.a.a(g.B.getBytes()));
      g.af = this.g.a(com.igexin.push.f.b.c.i(), false, true);
      g.ag = this.g.a(com.igexin.push.f.b.g.i(), true, true);
      i.a().c();
      c();
      this.e = com.igexin.push.core.a.f.a();
      d();
      this.i = new j();
      this.i.a(this.a, this.g, this.e);
      this.j = new com.igexin.push.e.c();
      this.j.a(this.a);
      localObject = new com.igexin.push.e.a();
      ((com.igexin.push.e.a)localObject).a(c.a);
      this.j.a((com.igexin.push.e.a)localObject);
      com.igexin.push.a.a.c.c().d();
      g.g.set(true);
      int i1 = android.os.Process.myPid();
      this.e.a(i1);
      q();
      com.igexin.push.extension.a.a().a(this.a);
      localObject = this.d.iterator();
      while (((Iterator)localObject).hasNext())
      {
        Message localMessage = (Message)((Iterator)localObject).next();
        if (this.c != null) {
          this.c.sendMessage(localMessage);
        }
      }
      com.igexin.push.core.a.f.a().t();
    }
    catch (Throwable localThrowable1)
    {
      com.igexin.b.a.c.b.a("CoreLogic|init|failed|" + localThrowable1.toString());
      return;
    }
    try
    {
      AssistPushManager.getInstance().initialize(g.f);
      AssistPushManager.getInstance().register(g.f);
      return;
    }
    catch (Throwable localThrowable2) {}
  }
  
  public boolean b(String paramString)
  {
    if ((g.f != null) && (this.j != null))
    {
      new com.igexin.sdk.a.c(g.f).b();
      g.j = false;
      g.m = false;
      paramString = new com.igexin.push.e.a();
      paramString.a(c.g);
      this.j.a(paramString);
    }
    return true;
  }
  
  public com.igexin.push.f.b.a c()
  {
    com.igexin.push.f.b.a localA = com.igexin.push.f.b.a.i();
    com.igexin.push.a.a.b localB = new com.igexin.push.a.a.b();
    localA.a(localB);
    localA.a(new com.igexin.push.a.a.a());
    localA.a(new com.igexin.push.a.a.d());
    localA.a(com.igexin.push.a.a.c.c());
    try
    {
      localB.a();
      localB.a(System.currentTimeMillis());
      g.ah = this.g.a(localA, false, true);
      try
      {
        this.a.sendBroadcast(new Intent());
        return localA;
      }
      catch (Throwable localThrowable1)
      {
        return localA;
      }
    }
    catch (Throwable localThrowable2)
    {
      for (;;) {}
    }
  }
  
  public void d()
  {
    if (!TextUtils.isEmpty(g.w)) {}
    for (;;)
    {
      return;
      try
      {
        if (!com.igexin.push.util.a.a()) {
          continue;
        }
        Object localObject1;
        if (Build.VERSION.SDK_INT >= 23)
        {
          localObject1 = NetworkInterface.getNetworkInterfaces();
          while (((Enumeration)localObject1).hasMoreElements())
          {
            Object localObject2 = (NetworkInterface)((Enumeration)localObject1).nextElement();
            if ("wlan0".equalsIgnoreCase(((NetworkInterface)localObject2).getName()))
            {
              localObject2 = ((NetworkInterface)localObject2).getHardwareAddress();
              if ((localObject2 != null) && (localObject2.length != 0))
              {
                localObject1 = new StringBuilder();
                int i2 = localObject2.length;
                int i1 = 0;
                while (i1 < i2)
                {
                  ((StringBuilder)localObject1).append(String.format("%02X:", new Object[] { Byte.valueOf(localObject2[i1]) }));
                  i1 += 1;
                }
                if (((StringBuilder)localObject1).length() > 0) {
                  ((StringBuilder)localObject1).deleteCharAt(((StringBuilder)localObject1).length() - 1);
                }
                localObject1 = ((StringBuilder)localObject1).toString();
                com.igexin.push.core.b.g.a().a((String)localObject1);
              }
            }
          }
        }
        for (;;)
        {
          com.igexin.b.a.c.b.a("CoreLogic mac:" + g.w);
          return;
          localObject1 = ((WifiManager)this.a.getSystemService("wifi")).getConnectionInfo();
          if (localObject1 != null) {
            com.igexin.push.core.b.g.a().a(((WifiInfo)localObject1).getMacAddress());
          }
        }
        return;
      }
      catch (Throwable localThrowable) {}
    }
  }
  
  public void e()
  {
    Intent localIntent = new Intent(this.a, com.igexin.push.core.a.f.a().a(g.f));
    this.a.stopService(localIntent);
  }
  
  public com.igexin.b.a.b.b f()
  {
    if (this.h == null) {
      this.h = com.igexin.push.d.a.b.a();
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
  
  public com.igexin.push.core.a.f i()
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
      this.a.unregisterReceiver(com.igexin.b.a.b.c.b());
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
      ActivityManager localActivityManager = (ActivityManager)g.f.getSystemService("activity");
      ActivityManager.MemoryInfo localMemoryInfo = new ActivityManager.MemoryInfo();
      localActivityManager.getMemoryInfo(localMemoryInfo);
      long l1 = localMemoryInfo.availMem / 1024L / 1024L;
      if (localMemoryInfo.lowMemory)
      {
        com.igexin.b.a.c.b.b("CoreLogic", "system in lowMemory, available menmory = " + l1 + "M");
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
