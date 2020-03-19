package com.e.a.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.ihs.app.framework.b;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class a
{
  private static volatile a a;
  private List<PackageInfo> b;
  private Set<String> c = new HashSet();
  private List<a> d;
  private PackageManager e = b.a().getPackageManager();
  
  private a()
  {
    try
    {
      this.b = this.e.getInstalledPackages(0);
      if ((this.b != null) && (this.b.size() > 0))
      {
        localObject1 = this.b.iterator();
        while (((Iterator)localObject1).hasNext())
        {
          localObject2 = (PackageInfo)((Iterator)localObject1).next();
          this.c.add(((PackageInfo)localObject2).packageName);
        }
      }
    }
    catch (Exception localException)
    {
      Object localObject1;
      Object localObject2;
      for (;;) {}
    }
    localObject1 = new IntentFilter();
    ((IntentFilter)localObject1).addAction("android.intent.action.PACKAGE_ADDED");
    ((IntentFilter)localObject1).addAction("android.intent.action.PACKAGE_REMOVED");
    ((IntentFilter)localObject1).addDataScheme("package");
    localObject2 = new b(null);
    b.a().registerReceiver((BroadcastReceiver)localObject2, (IntentFilter)localObject1);
  }
  
  public static a a()
  {
    if (a == null) {
      try
      {
        if (a == null) {
          a = new a();
        }
      }
      finally {}
    }
    return a;
  }
  
  private void b(String paramString)
  {
    int j = 1;
    try
    {
      Iterator localIterator = this.b.iterator();
      do
      {
        i = j;
        if (!localIterator.hasNext()) {
          break;
        }
      } while (!paramString.equals(((PackageInfo)localIterator.next()).packageName));
      int i = 0;
      if (i != 0)
      {
        paramString = this.e.getPackageInfo(paramString, 0);
        this.b.add(paramString);
        return;
      }
    }
    catch (Exception paramString)
    {
      ThrowableExtension.printStackTrace(paramString);
    }
  }
  
  private void c()
  {
    if (this.d != null)
    {
      Iterator localIterator = this.d.iterator();
      while (localIterator.hasNext()) {
        ((a)localIterator.next()).a();
      }
    }
  }
  
  private void c(String paramString)
  {
    Object localObject2 = null;
    try
    {
      Iterator localIterator = this.b.iterator();
      Object localObject1;
      do
      {
        localObject1 = localObject2;
        if (!localIterator.hasNext()) {
          break;
        }
        localObject1 = (PackageInfo)localIterator.next();
      } while (!paramString.equals(((PackageInfo)localObject1).packageName));
      if (localObject1 != null)
      {
        this.b.remove(localObject1);
        return;
      }
    }
    catch (Exception paramString)
    {
      ThrowableExtension.printStackTrace(paramString);
    }
  }
  
  private void d()
  {
    if (this.d != null)
    {
      Iterator localIterator = this.d.iterator();
      while (localIterator.hasNext()) {
        ((a)localIterator.next()).b();
      }
    }
  }
  
  private void e()
  {
    if (this.d != null)
    {
      Iterator localIterator = this.d.iterator();
      while (localIterator.hasNext()) {
        ((a)localIterator.next()).c();
      }
    }
  }
  
  public boolean a(String paramString)
  {
    return this.c.contains(paramString);
  }
  
  public List<PackageInfo> b()
  {
    if (this.b != null) {
      return new ArrayList(this.b);
    }
    return Collections.emptyList();
  }
  
  public static abstract interface a
  {
    public abstract void a();
    
    public abstract void b();
    
    public abstract void c();
  }
  
  private class b
    extends BroadcastReceiver
  {
    private b() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      paramContext = paramIntent.getAction();
      String str = paramIntent.getData().getEncodedSchemeSpecificPart();
      if (str == null) {
        return;
      }
      boolean bool = paramIntent.getBooleanExtra("android.intent.extra.REPLACING", false);
      if ("android.intent.action.PACKAGE_ADDED".equals(paramContext))
      {
        if (bool)
        {
          a.a(a.this);
          return;
        }
        a.b(a.this).add(str);
        a.a(a.this, str);
        a.c(a.this);
        return;
      }
      if ("android.intent.action.PACKAGE_REMOVED".equals(paramContext))
      {
        a.b(a.this).remove(str);
        a.b(a.this, str);
        a.d(a.this);
      }
    }
  }
}
