package com.nativex.monetization.j;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.HandlerThread;
import com.nativex.monetization.b.b;
import com.nativex.monetization.h.k;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class f
{
  private static f a;
  private static HandlerThread b;
  private static Handler c;
  private static List<d> d;
  private static a e;
  
  private f()
  {
    b = new HandlerThread("SmartOffers-Thread");
    b.start();
    c = new Handler(b.getLooper());
    e = new a(null);
  }
  
  public static f a()
  {
    if (a == null) {
      a = new f();
    }
    return a;
  }
  
  private static final void d(List<d> paramList)
  {
    Object localObject1 = k.a();
    if (localObject1 == null) {}
    ArrayList localArrayList;
    do
    {
      return;
      Object localObject2 = ((Context)localObject1).getPackageManager();
      localObject1 = new HashMap();
      localArrayList = new ArrayList();
      Object localObject3;
      try
      {
        localObject2 = ((PackageManager)localObject2).getInstalledPackages(8193).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (PackageInfo)((Iterator)localObject2).next();
          if (((PackageInfo)localObject3).packageName != null) {
            ((HashMap)localObject1).put(((PackageInfo)localObject3).packageName, localObject3);
          }
        }
        paramList = paramList.iterator();
      }
      catch (Exception paramList)
      {
        paramList.printStackTrace();
        return;
      }
      while (paramList.hasNext())
      {
        localObject3 = (d)paramList.next();
        localObject2 = new e();
        ((e)localObject2).a(((d)localObject3).a());
        if (((HashMap)localObject1).containsKey(((d)localObject3).b()))
        {
          localObject3 = (PackageInfo)((HashMap)localObject1).get(((d)localObject3).b());
          if (localObject3 != null)
          {
            ((e)localObject2).a(Boolean.valueOf(true));
            ((e)localObject2).b(Long.toString(((PackageInfo)localObject3).firstInstallTime));
          }
          for (;;)
          {
            localArrayList.add(localObject2);
            break;
            ((e)localObject2).a(Boolean.valueOf(false));
          }
        }
      }
    } while ((localArrayList == null) || (localArrayList.size() <= 0));
    b.a().a(localArrayList);
  }
  
  public final void a(List<d> paramList)
  {
    if ((paramList != null) && (paramList.size() > 0))
    {
      d = paramList;
      c.post(e);
    }
  }
  
  public final void b()
  {
    b.a().d();
  }
  
  private static class a
    implements Runnable
  {
    private a() {}
    
    public void run()
    {
      f.b(f.c());
      f.c(null);
    }
  }
}
