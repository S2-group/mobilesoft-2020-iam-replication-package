package com.cleanmaster.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.keniu.security.b.f;
import com.keniu.security.core.MoSecurityApplication;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class bf
{
  private static bf b = new bf();
  Context a = MoSecurityApplication.a().getApplicationContext();
  private bi c = null;
  
  private bf()
  {
    if (!f.f())
    {
      this.c = new bh(this, null);
      return;
    }
    this.c = new bi(this, null);
  }
  
  public static bf a()
  {
    return b;
  }
  
  private List<PackageInfo> a(PackageManager paramPackageManager, int paramInt)
  {
    try
    {
      paramPackageManager = paramPackageManager.getInstalledPackages(paramInt);
      return paramPackageManager;
    }
    catch (Exception paramPackageManager) {}
    return null;
  }
  
  public List<PackageInfo> b()
  {
    return this.c.a();
  }
  
  public Map<String, PackageInfo> c()
  {
    Object localObject = d();
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
  
  public List<PackageInfo> d()
  {
    return this.c.b();
  }
  
  public void e()
  {
    this.c.c();
  }
}
