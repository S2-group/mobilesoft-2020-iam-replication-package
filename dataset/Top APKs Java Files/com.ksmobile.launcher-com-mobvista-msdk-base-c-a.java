package com.mobvista.msdk.base.c;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.util.Log;
import com.mobvista.msdk.base.entity.c;
import com.mobvista.msdk.base.utils.e;
import com.mobvista.msdk.base.utils.i;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class a
{
  public static final String a = a.class.getSimpleName();
  public static List<String> b = new ArrayList();
  private static a c;
  private static CopyOnWriteArrayList<c> i = new CopyOnWriteArrayList();
  private Context d;
  private String e;
  private String f;
  private String g;
  private boolean h = false;
  private String j;
  private Location k;
  
  private a() {}
  
  public static List<c> b()
  {
    return i;
  }
  
  public static a c()
  {
    if (c == null) {}
    try
    {
      if (c == null) {
        c = new a();
      }
      return c;
    }
    finally {}
  }
  
  public static List<Long> g()
  {
    if ((i != null) && (i.size() > 0))
    {
      Iterator localIterator = i.iterator();
      ArrayList localArrayList = new ArrayList();
      while (localIterator.hasNext())
      {
        c localC = (c)localIterator.next();
        if (!localArrayList.contains(localC.a())) {
          try
          {
            localArrayList.add(Long.valueOf(Long.parseLong(localC.a())));
          }
          catch (NumberFormatException localNumberFormatException)
          {
            localNumberFormatException.printStackTrace();
          }
        }
      }
      return localArrayList;
    }
    return null;
  }
  
  public final String a()
  {
    return this.j;
  }
  
  public final void a(Context paramContext)
  {
    this.d = paramContext;
  }
  
  public final void a(String paramString)
  {
    this.j = paramString;
  }
  
  public final void b(String paramString)
  {
    this.e = paramString;
  }
  
  public final void c(String paramString)
  {
    this.f = paramString;
  }
  
  public final void d()
  {
    if (this.h == true) {
      return;
    }
    new Thread(new a.1(this)).start();
    this.h = true;
  }
  
  public final void d(String paramString)
  {
    this.g = paramString;
  }
  
  public final void e()
  {
    label198:
    for (;;)
    {
      try
      {
        Object localObject1 = i.a(this.d).a(this.f);
        i = (CopyOnWriteArrayList)localObject1;
        int m;
        if (localObject1 != null)
        {
          m = i.size();
          if (m != 0) {}
        }
        else
        {
          return;
        }
        localObject1 = i.iterator();
        int n = 0;
        boolean bool = ((Iterator)localObject1).hasNext();
        if (bool)
        {
          int i2 = n;
          try
          {
            c localC = (c)((Iterator)localObject1).next();
            int i1 = 0;
            m = n;
            n = m;
            i2 = m;
            if (i1 >= b.size()) {
              continue;
            }
            i2 = m;
            if (!((String)b.get(i1)).equals(localC.b())) {
              break label198;
            }
            i2 = m;
            i.remove(localC);
            m = 1;
            i1 += 1;
            continue;
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
            Log.e(a, "remove list error");
            n = i2;
          }
        }
        if (n == 0) {
          continue;
        }
      }
      finally {}
      i.a(this.d).a(i);
    }
  }
  
  public final void f()
  {
    if ((i != null) && (i.size() > 0)) {
      i.a(this.d).a(i);
    }
  }
  
  public final Location h()
  {
    return this.k;
  }
  
  public final Context i()
  {
    return this.d;
  }
  
  public final String j()
  {
    return this.e;
  }
  
  public final String k()
  {
    return this.f;
  }
  
  public final String l()
  {
    return this.g;
  }
  
  public final List<String> m()
  {
    try
    {
      if (b != null) {
        return b;
      }
      List localList = this.d.getPackageManager().getInstalledPackages(0);
      int m = 0;
      while (m < localList.size())
      {
        b.add(((PackageInfo)localList.get(m)).packageName);
        m += 1;
      }
      localList = b;
      return localList;
    }
    catch (Exception localException)
    {
      e.d(a, "get package info list error");
    }
    return null;
  }
}
