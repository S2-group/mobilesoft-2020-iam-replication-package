package com.lge.media.musicflow.applist.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class b
  extends android.support.v4.c.a<List<a>>
{
  private static final Comparator<a> j = new c();
  final PackageManager f = n().getPackageManager();
  private List<a> g;
  private com.lge.media.musicflow.applist.b.a h;
  private com.lge.media.musicflow.applist.b.b i;
  
  public b(Context paramContext)
  {
    super(paramContext);
  }
  
  private void c(List<a> paramList) {}
  
  public void a(List<a> paramList)
  {
    if ((r()) && (paramList != null)) {
      c(paramList);
    }
    List localList;
    do
    {
      return;
      localList = this.g;
      this.g = paramList;
      if (p()) {
        super.b(paramList);
      }
    } while ((localList == null) || (localList == paramList));
    c(localList);
  }
  
  public void b(List<a> paramList)
  {
    super.a(paramList);
    c(paramList);
  }
  
  public List<a> h()
  {
    int k = 0;
    for (;;)
    {
      try
      {
        Object localObject1 = this.f.getInstalledApplications(0);
        if (localObject1 == null)
        {
          localObject1 = new ArrayList();
          localObject3 = ((List)localObject1).iterator();
          if (((Iterator)localObject3).hasNext())
          {
            localObject4 = (ApplicationInfo)((Iterator)localObject3).next();
            if (this.f.getLaunchIntentForPackage(((ApplicationInfo)localObject4).packageName) != null) {
              continue;
            }
            ((Iterator)localObject3).remove();
          }
        }
      }
      catch (Exception localException)
      {
        Object localObject4;
        localException.printStackTrace();
        Object localObject2 = null;
        continue;
        Object localObject3 = new ArrayList(localObject2.size());
        if (k < localObject2.size())
        {
          localObject4 = new a(this, (ApplicationInfo)localObject2.get(k));
          ((a)localObject4).a(n());
          ((List)localObject3).add(localObject4);
          k += 1;
        }
        else
        {
          Collections.sort((List)localObject3, j);
          return localObject3;
        }
      }
    }
  }
  
  protected void i()
  {
    if (this.g != null) {
      a(this.g);
    }
    if (this.h == null) {
      this.h = new com.lge.media.musicflow.applist.b.a(this);
    }
    if (this.i == null) {
      this.i = new com.lge.media.musicflow.applist.b.b(this);
    }
    if (z()) {
      u();
    }
    while (this.g != null) {
      return;
    }
    u();
  }
  
  protected void j()
  {
    t();
  }
  
  protected void k()
  {
    j();
    if (this.g != null)
    {
      c(this.g);
      this.g = null;
    }
    if (this.h != null)
    {
      n().unregisterReceiver(this.h);
      this.h = null;
    }
    if (this.i != null)
    {
      n().unregisterReceiver(this.i);
      this.i = null;
    }
  }
  
  public void u()
  {
    super.u();
  }
}
