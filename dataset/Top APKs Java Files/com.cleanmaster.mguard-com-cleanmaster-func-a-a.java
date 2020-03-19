package com.cleanmaster.func.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.cmcm.swiper.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a
{
  private static a c = null;
  Context a = c.a().a.getApplicationContext();
  public b b = null;
  
  private a() {}
  
  public static a a()
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
  
  public final List<String> b()
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
      if (localPackageInfo != null) {
        localArrayList.add(localPackageInfo.packageName);
      }
    }
    return localArrayList;
  }
  
  public class b
  {
    PackageManager a = a.this.a.getPackageManager();
    
    private b() {}
    
    public List<PackageInfo> a()
    {
      return a.a(this.a);
    }
    
    public void a(String paramString) {}
    
    public void b() {}
    
    public void c() {}
  }
}
