package com.netqin.ps.plugin;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.netqin.t;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class a
{
  private static a c;
  Context a;
  ArrayList<a> b;
  
  private a(Context paramContext)
  {
    this.a = paramContext.getApplicationContext();
    paramContext = new ArrayList();
    paramContext.add(new a(1, "keyboard", (byte)0));
    paramContext.add(new a(268435456, "test", (byte)0));
    this.b = paramContext;
  }
  
  public static a a(Context paramContext)
  {
    if (c == null) {
      c = new a(paramContext);
    }
    return c;
  }
  
  final a a()
  {
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext())
    {
      a localA = (a)localIterator.next();
      if (localA.a == 1) {
        return localA;
      }
    }
    return null;
  }
  
  public final boolean a(b paramB)
  {
    boolean bool;
    if (paramB == null)
    {
      bool = t.g;
      return false;
    }
    if ((paramB.a & 0x1) == 0)
    {
      bool = t.g;
      return false;
    }
    a localA = a();
    if (localA == null)
    {
      bool = t.g;
      return false;
    }
    SharedPreferences localSharedPreferences = localA.c.a.getSharedPreferences("PlugInPre", 0);
    if (paramB == null) {
      localSharedPreferences.edit().remove(localA.b).commit();
    }
    for (;;)
    {
      return true;
      localSharedPreferences.edit().putString(localA.b, paramB.c.packageName).commit();
    }
  }
  
  public final List<b> b()
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = this.a.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (localPackageInfo.packageName.startsWith("com.netqin.plugin"))
      {
        if (t.g) {
          new StringBuilder("getPlugInList find plugin ").append(localPackageInfo.packageName);
        }
        localArrayList.add(new b(localPackageManager, localPackageInfo, (byte)0));
      }
    }
    return localArrayList;
  }
  
  public final b c()
  {
    a localA = a();
    if (localA == null)
    {
      boolean bool = t.g;
      return null;
    }
    SharedPreferences localSharedPreferences = localA.c.a.getSharedPreferences("PlugInPre", 0);
    Object localObject1 = localSharedPreferences.getString(localA.b, null);
    if (localObject1 != null)
    {
      localObject2 = localA.c;
      PackageManager localPackageManager = ((a)localObject2).a.getPackageManager();
      Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
      for (;;)
      {
        if (localIterator.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
          if (localPackageInfo.packageName.equals(localObject1))
          {
            localObject1 = new b((a)localObject2, localPackageManager, localPackageInfo, (byte)0);
            localObject2 = localObject1;
            if (localObject1 == null) {
              localSharedPreferences.edit().remove(localA.b).commit();
            }
          }
        }
      }
    }
    for (Object localObject2 = localObject1;; localObject2 = null)
    {
      return localObject2;
      localObject1 = null;
      break;
    }
  }
  
  private final class a
  {
    int a;
    String b;
    
    private a(int paramInt, String paramString)
    {
      this.a = paramInt;
      this.b = paramString;
    }
  }
  
  public final class b
  {
    int a;
    PackageManager b;
    PackageInfo c;
    
    private b(PackageManager paramPackageManager, PackageInfo paramPackageInfo)
    {
      this.b = paramPackageManager;
      this.c = paramPackageInfo;
      paramPackageManager = this.c.packageName.split("\\.");
      if (t.g) {
        new StringBuilder("ifVaild split ").append(this.c.packageName).append(" len ").append(paramPackageManager.length);
      }
      if (paramPackageManager.length >= 3)
      {
        if (t.g) {
          new StringBuilder("ifVaild check string ").append(paramPackageManager[2]);
        }
        paramPackageManager = paramPackageManager[2].split("_");
        int i = 1;
        while (i < paramPackageManager.length)
        {
          if (t.g) {
            new StringBuilder("ifVaild check substring ").append(paramPackageManager[i]);
          }
          paramPackageInfo = a.this.b.iterator();
          while (paramPackageInfo.hasNext())
          {
            a.a localA = (a.a)paramPackageInfo.next();
            if (localA.b.equals(paramPackageManager[i]))
            {
              int j = this.a;
              this.a = (localA.a | j);
            }
          }
          i += 1;
        }
      }
      if (t.g) {
        new StringBuilder("  setindex ").append(this.a);
      }
    }
    
    public final String a()
    {
      return (String)this.c.applicationInfo.loadLabel(this.b);
    }
    
    public final String b()
    {
      return (String)this.c.applicationInfo.loadDescription(this.b);
    }
    
    public final String c()
    {
      a.a localA = a.this.a();
      boolean bool;
      if (localA == null)
      {
        bool = t.g;
        return null;
      }
      if ((this.a & 0x1) == 0)
      {
        bool = t.g;
        return null;
      }
      return this.c.packageName + "." + localA.b;
    }
  }
}
