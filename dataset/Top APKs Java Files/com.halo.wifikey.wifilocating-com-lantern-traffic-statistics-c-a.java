package com.lantern.traffic.statistics.c;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.lantern.traffic.statistics.b.c;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public final class a
{
  public static a c;
  private static ConcurrentHashMap<String, com.lantern.traffic.statistics.b.b> f = new ConcurrentHashMap();
  public long a = 0L;
  public c b = c.c;
  private Handler d;
  private Runnable e;
  
  private a() {}
  
  public static a a()
  {
    if (c == null) {
      c = new a();
    }
    return c;
  }
  
  public final void b()
  {
    Object localObject = (ConnectivityManager)com.lantern.core.a.i().getSystemService("connectivity");
    if (localObject != null)
    {
      localObject = ((ConnectivityManager)localObject).getActiveNetworkInfo();
      if ((localObject != null) && (((NetworkInfo)localObject).isConnected()))
      {
        if (((NetworkInfo)localObject).getType() != 1) {
          break label58;
        }
        if (this.b != c.a) {
          this.b = c.a;
        }
      }
    }
    label58:
    while (this.b == c.b) {
      return;
    }
    this.b = c.b;
  }
  
  public final Runnable c()
  {
    if (this.e == null) {
      this.e = new b(this);
    }
    return this.e;
  }
  
  public final Handler d()
  {
    if (this.d == null) {
      this.d = new Handler(Looper.getMainLooper());
    }
    return this.d;
  }
  
  public final void e()
  {
    Object localObject1;
    int i;
    if (SystemClock.elapsedRealtime() - this.a < 30000L)
    {
      localObject1 = ((ConnectivityManager)com.lantern.core.a.i().getSystemService("connectivity")).getActiveNetworkInfo();
      if (localObject1 == null) {
        i = 1;
      }
      while (i != 0)
      {
        return;
        if ((localObject1 != null) && (((NetworkInfo)localObject1).isConnected()))
        {
          if ((((NetworkInfo)localObject1).getType() == 1) && (this.b == c.a))
          {
            i = 1;
            continue;
          }
          if ((((NetworkInfo)localObject1).getType() != 1) && (this.b == c.b))
          {
            i = 1;
            continue;
          }
        }
        i = 0;
      }
    }
    for (;;)
    {
      long l1;
      long l2;
      long l3;
      long l4;
      try
      {
        PackageManager localPackageManager = com.lantern.core.a.i().getPackageManager();
        localObject1 = localPackageManager.getInstalledPackages(0);
        if ((localObject1 != null) && (!((List)localObject1).isEmpty()))
        {
          Iterator localIterator = ((List)localObject1).iterator();
          if (localIterator.hasNext())
          {
            Object localObject2 = (PackageInfo)localIterator.next();
            if ((((PackageInfo)localObject2).applicationInfo.flags & 0x1) == 0) {
              break label810;
            }
            i = 1;
            if (i != 0) {
              continue;
            }
            if ((((PackageInfo)localObject2).applicationInfo.flags & 0x80) == 0) {
              break label815;
            }
            i = 1;
            if ((i != 0) || (((PackageInfo)localObject2).applicationInfo.uid < 10000)) {
              continue;
            }
            String str = ((PackageInfo)localObject2).packageName;
            if (localPackageManager.checkPermission("android.permission.INTERNET", str) != 0) {
              break label820;
            }
            i = 1;
            if (i == 0) {
              continue;
            }
            l1 = TrafficStats.getUidRxBytes(((PackageInfo)localObject2).applicationInfo.uid);
            l2 = TrafficStats.getUidTxBytes(((PackageInfo)localObject2).applicationInfo.uid);
            if (l1 == -1L) {
              continue;
            }
            if (l2 == -1L)
            {
              continue;
              if (localObject1 == null) {
                continue;
              }
              l1 = ((com.lantern.traffic.statistics.b.b)localObject1).g();
              l2 = ((com.lantern.traffic.statistics.b.b)localObject1).f();
              if ((l1 == 0L) && (l2 == 0L)) {
                continue;
              }
              localObject2 = (com.lantern.traffic.statistics.b.b)f.get(str);
              if (localObject2 != null) {
                continue;
              }
              localObject2 = com.lantern.traffic.statistics.a.a.a(str);
              if (localObject2 != null) {
                break label825;
              }
              ((com.lantern.traffic.statistics.b.b)localObject1).a(com.lantern.traffic.statistics.a.a.a((com.lantern.traffic.statistics.b.b)localObject1));
              f.put(str, localObject1);
              continue;
            }
            localObject1 = new com.lantern.traffic.statistics.b.b();
            Calendar localCalendar = Calendar.getInstance();
            ((com.lantern.traffic.statistics.b.b)localObject1).a(((PackageInfo)localObject2).packageName);
            ((com.lantern.traffic.statistics.b.b)localObject1).a(com.lantern.traffic.statistics.b.a.a);
            ((com.lantern.traffic.statistics.b.b)localObject1).a(localCalendar.get(1));
            ((com.lantern.traffic.statistics.b.b)localObject1).b(localCalendar.get(2) + 1);
            ((com.lantern.traffic.statistics.b.b)localObject1).c(localCalendar.get(5));
            ((com.lantern.traffic.statistics.b.b)localObject1).g(localCalendar.getTimeInMillis());
            ((com.lantern.traffic.statistics.b.b)localObject1).e(l1);
            ((com.lantern.traffic.statistics.b.b)localObject1).d(l2);
            ((com.lantern.traffic.statistics.b.b)localObject1).c(l1);
            ((com.lantern.traffic.statistics.b.b)localObject1).b(l2);
            ((com.lantern.traffic.statistics.b.b)localObject1).a(this.b);
            continue;
            if (localObject2 == null) {
              continue;
            }
            l3 = ((com.lantern.traffic.statistics.b.b)localObject2).g();
            l4 = ((com.lantern.traffic.statistics.b.b)localObject2).f();
            if (((com.lantern.traffic.statistics.b.b)localObject2).i() != com.lantern.traffic.statistics.b.a.b) {
              break label830;
            }
            if ((l1 >= l3) && (l2 >= l4))
            {
              if ((l1 == l3) && (l2 == l4))
              {
                ((com.lantern.traffic.statistics.b.b)localObject1).c(0L);
                ((com.lantern.traffic.statistics.b.b)localObject1).b(0L);
              }
            }
            else
            {
              ((com.lantern.traffic.statistics.b.b)localObject1).a(com.lantern.traffic.statistics.a.a.a((com.lantern.traffic.statistics.b.b)localObject1));
              f.put(str, localObject1);
              continue;
            }
            ((com.lantern.traffic.statistics.b.b)localObject1).c(l1 - l3);
            ((com.lantern.traffic.statistics.b.b)localObject1).b(l2 - l4);
            continue;
            ((com.lantern.traffic.statistics.b.b)localObject1).a(com.lantern.traffic.statistics.a.a.a((com.lantern.traffic.statistics.b.b)localObject1));
            f.put(str, localObject1);
            ((com.lantern.traffic.statistics.b.b)localObject2).a(com.lantern.traffic.statistics.b.a.b);
            com.lantern.traffic.statistics.a.a.b((com.lantern.traffic.statistics.b.b)localObject2);
            continue;
            if ((l1 == l3) && (l2 == l4))
            {
              if (i == 0) {
                continue;
              }
              f.put(str, localObject2);
              continue;
            }
            ((com.lantern.traffic.statistics.b.b)localObject1).c(l1 - l3);
            ((com.lantern.traffic.statistics.b.b)localObject1).b(l2 - l4);
            ((com.lantern.traffic.statistics.b.b)localObject1).a(com.lantern.traffic.statistics.a.a.a((com.lantern.traffic.statistics.b.b)localObject1));
            f.put(str, localObject1);
            if ((((com.lantern.traffic.statistics.b.b)localObject1).j() != ((com.lantern.traffic.statistics.b.b)localObject2).j()) || (((com.lantern.traffic.statistics.b.b)localObject1).k() != ((com.lantern.traffic.statistics.b.b)localObject2).k()) || (((com.lantern.traffic.statistics.b.b)localObject1).l() != ((com.lantern.traffic.statistics.b.b)localObject2).l())) {
              break label848;
            }
            i = 1;
            if (i != 0) {
              continue;
            }
            ((com.lantern.traffic.statistics.b.b)localObject2).a(com.lantern.traffic.statistics.b.a.b);
            com.lantern.traffic.statistics.a.a.b((com.lantern.traffic.statistics.b.b)localObject2);
            continue;
          }
        }
        this.a = SystemClock.elapsedRealtime();
        b();
        return;
        i = 0;
        continue;
        continue;
        localObject1 = null;
        continue;
        continue;
        i = 0;
      }
      catch (Exception localException)
      {
        return;
      }
      label810:
      continue;
      label815:
      i = 0;
      continue;
      label820:
      i = 0;
      continue;
      label825:
      i = 1;
      continue;
      label830:
      if (l1 >= l3) {
        if (l2 < l4)
        {
          continue;
          label848:
          i = 0;
        }
      }
    }
  }
}
