package com.cleanmaster.func.cache;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.cleanmaster.base.c;
import com.cleanmaster.base.util.system.o;
import com.keniu.security.curlmonitor.MonitorManager.a;
import com.keniu.security.d;
import com.keniu.security.monitor.MonitorManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class e
{
  private static e c = null;
  Context a = d.a().getApplicationContext();
  public b b = null;
  
  private e()
  {
    if (!o.h())
    {
      this.b = new a((byte)0);
      return;
    }
    this.b = new b((byte)0);
  }
  
  public static e a()
  {
    if (c == null) {}
    try
    {
      if (c == null) {
        c = new e();
      }
      return c;
    }
    finally {}
  }
  
  private static List<PackageInfo> b(PackageManager paramPackageManager)
  {
    try
    {
      paramPackageManager = paramPackageManager.getInstalledPackages(0);
      return paramPackageManager;
    }
    catch (Exception paramPackageManager) {}
    return null;
  }
  
  public final boolean a(String paramString)
  {
    return this.b.d(paramString);
  }
  
  public final Map<String, PackageInfo> b()
  {
    Object localObject = this.b.c();
    if ((localObject == null) || (((List)localObject).isEmpty())) {
      return null;
    }
    HashMap localHashMap = new HashMap(((List)localObject).size());
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if (localPackageInfo != null)
      {
        String str = localPackageInfo.packageName;
        if (!TextUtils.isEmpty(str)) {
          localHashMap.put(str, localPackageInfo);
        }
      }
    }
    return localHashMap;
  }
  
  public final ProviderInfo[] b(String paramString)
  {
    return this.b.c(paramString);
  }
  
  public final List<String> c()
  {
    Object localObject = b(this.b.a);
    if ((localObject == null) || (((List)localObject).isEmpty())) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if ((localPackageInfo != null) && (c.a(localPackageInfo.applicationInfo))) {
        localArrayList.add(localPackageInfo.packageName);
      }
    }
    return localArrayList;
  }
  
  final class a
    extends e.b
    implements MonitorManager.a
  {
    private int b = 1;
    private int c = 2;
    private int d = 0;
    private List<PackageInfo> e = null;
    
    private a()
    {
      super((byte)0);
    }
    
    /* Error */
    private void e(String paramString)
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_1
      //   3: ifnull +63 -> 66
      //   6: aload_0
      //   7: getfield 29	com/cleanmaster/func/cache/e$a:e	Ljava/util/List;
      //   10: astore_2
      //   11: aload_2
      //   12: ifnull +54 -> 66
      //   15: aload_0
      //   16: getfield 38	com/cleanmaster/func/cache/e$a:a	Landroid/content/pm/PackageManager;
      //   19: aload_1
      //   20: iconst_0
      //   21: invokevirtual 44	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
      //   24: astore_1
      //   25: aload_1
      //   26: ifnull +40 -> 66
      //   29: aload_0
      //   30: monitorenter
      //   31: aload_0
      //   32: getfield 29	com/cleanmaster/func/cache/e$a:e	Ljava/util/List;
      //   35: ifnull +29 -> 64
      //   38: aload_1
      //   39: ifnull +25 -> 64
      //   42: aload_0
      //   43: getfield 29	com/cleanmaster/func/cache/e$a:e	Ljava/util/List;
      //   46: aload_1
      //   47: invokeinterface 50 2 0
      //   52: pop
      //   53: aload_0
      //   54: getfield 29	com/cleanmaster/func/cache/e$a:e	Ljava/util/List;
      //   57: aload_1
      //   58: invokeinterface 53 2 0
      //   63: pop
      //   64: aload_0
      //   65: monitorexit
      //   66: aload_0
      //   67: monitorexit
      //   68: return
      //   69: astore_1
      //   70: aload_1
      //   71: invokevirtual 57	java/lang/Exception:printStackTrace	()V
      //   74: aconst_null
      //   75: astore_1
      //   76: goto -51 -> 25
      //   79: astore_1
      //   80: aload_0
      //   81: monitorexit
      //   82: aload_1
      //   83: athrow
      //   84: astore_1
      //   85: aload_0
      //   86: monitorexit
      //   87: aload_1
      //   88: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	89	0	this	a
      //   0	89	1	paramString	String
      //   10	2	2	localList	List
      // Exception table:
      //   from	to	target	type
      //   15	25	69	java/lang/Exception
      //   31	38	79	finally
      //   42	64	79	finally
      //   64	66	79	finally
      //   80	82	79	finally
      //   6	11	84	finally
      //   15	25	84	finally
      //   29	31	84	finally
      //   66	68	84	finally
      //   70	74	84	finally
      //   82	84	84	finally
      //   85	87	84	finally
    }
    
    public final int a(String paramString)
    {
      if (TextUtils.isEmpty(paramString)) {
        return -1;
      }
      try
      {
        if (this.e == null)
        {
          this.e = e.a(this.a);
          this.d = this.c;
        }
        if (this.e == null) {
          return -1;
        }
      }
      finally {}
      Iterator localIterator = this.e.iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if (paramString.equalsIgnoreCase(localPackageInfo.packageName))
        {
          int i = localPackageInfo.versionCode;
          return i;
        }
      }
      return super.a(paramString);
    }
    
    public final List<PackageInfo> a()
    {
      try
      {
        if (this.e == null)
        {
          this.e = e.a(this.a);
          this.d = this.c;
        }
        ArrayList localArrayList = null;
        if (this.e != null)
        {
          localArrayList = new ArrayList();
          localArrayList.addAll(this.e);
        }
        return localArrayList;
      }
      finally {}
    }
    
    public final List<PackageInfo> b()
    {
      try
      {
        if (this.e == null)
        {
          this.e = e.a(this.a);
          this.d = this.c;
        }
        if (this.e == null) {
          return null;
        }
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator = this.e.iterator();
        while (localIterator.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
          if (c.b(localPackageInfo.applicationInfo)) {
            localArrayList.add(localPackageInfo);
          }
        }
      }
      finally {}
      return localList;
    }
    
    public final void b(String paramString)
    {
      if (paramString != null) {}
      for (;;)
      {
        try
        {
          if (this.e != null)
          {
            Iterator localIterator = this.e.iterator();
            if (!localIterator.hasNext()) {
              break label82;
            }
            PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
            if ((localPackageInfo == null) || (!paramString.equals(localPackageInfo.packageName))) {
              continue;
            }
            paramString = localPackageInfo;
            if (paramString != null) {
              this.e.remove(paramString);
            }
          }
          return;
        }
        finally {}
        label82:
        paramString = null;
      }
    }
    
    public final List<PackageInfo> c()
    {
      try
      {
        if (this.e == null)
        {
          this.e = e.a(this.a);
          this.d = this.c;
        }
        if (this.e == null) {
          return null;
        }
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator = this.e.iterator();
        while (localIterator.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
          if (c.a(localPackageInfo.applicationInfo)) {
            localArrayList.add(localPackageInfo);
          }
        }
      }
      finally {}
      return localList;
    }
    
    public final void d()
    {
      try
      {
        if (((this.e != null) && (this.e.size() > 0)) || (this.d != 0)) {
          return;
        }
        MonitorManager.a().a(MonitorManager.b, this);
        MonitorManager.a().a(MonitorManager.c, this);
        this.d = this.b;
        new e.c((byte)0).start();
        return;
      }
      finally {}
    }
    
    public final int monitorNotify(int paramInt, Object paramObject1, Object paramObject2)
    {
      if (paramInt == MonitorManager.b)
      {
        paramObject1 = (Intent)paramObject2;
        paramObject2 = paramObject1.getData().getSchemeSpecificPart();
        if (paramObject1.getBooleanExtra("android.intent.extra.REPLACING", false))
        {
          b(paramObject2);
          e(paramObject2);
        }
      }
      boolean bool;
      do
      {
        do
        {
          return 0;
          e(paramObject2);
          return 0;
        } while (paramInt != MonitorManager.c);
        paramObject1 = (Intent)paramObject2;
        bool = paramObject1.getBooleanExtra("android.intent.extra.REPLACING", false);
        paramObject1 = paramObject1.getData().getSchemeSpecificPart();
      } while (bool);
      b(paramObject1);
      return 0;
    }
  }
  
  public class b
  {
    PackageManager a = e.this.a.getPackageManager();
    
    private b() {}
    
    public int a(String paramString)
    {
      if (TextUtils.isEmpty(paramString)) {
        return -1;
      }
      try
      {
        int i = this.a.getPackageInfo(paramString, 0).versionCode;
        return i;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
      return -1;
    }
    
    public List<PackageInfo> a()
    {
      return e.a(this.a);
    }
    
    public List<PackageInfo> b()
    {
      Object localObject = e.a(this.a);
      if (localObject == null) {
        return null;
      }
      ArrayList localArrayList = new ArrayList();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if (c.b(localPackageInfo.applicationInfo)) {
          localArrayList.add(localPackageInfo);
        }
      }
      return localArrayList;
    }
    
    public void b(String paramString) {}
    
    public List<PackageInfo> c()
    {
      Object localObject = e.a(this.a);
      if (localObject == null) {
        return null;
      }
      ArrayList localArrayList = new ArrayList();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if (c.a(localPackageInfo.applicationInfo)) {
          localArrayList.add(localPackageInfo);
        }
      }
      return localArrayList;
    }
    
    public final ProviderInfo[] c(String paramString)
    {
      try
      {
        paramString = this.a.getPackageInfo(paramString, 8).providers;
        return paramString;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
      return null;
    }
    
    public void d() {}
    
    public final boolean d(String paramString)
    {
      if (TextUtils.isEmpty(paramString)) {
        return false;
      }
      try
      {
        boolean bool = c.b(this.a.getPackageInfo(paramString, 0).applicationInfo);
        return bool;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
      return false;
    }
  }
  
  static final class c
    extends Thread
  {
    private c() {}
    
    public final void run()
    {
      e.a().b.a();
    }
  }
}
