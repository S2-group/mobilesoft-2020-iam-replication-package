package com.tencent.android.tpush.service.d;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.tencent.android.tpush.data.b;
import com.tencent.android.tpush.service.cache.CacheManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class c
{
  private static c a = null;
  private Context b = null;
  private Map c = new HashMap(10);
  private Map d = new HashMap(10);
  
  private c(Context paramContext)
  {
    this.b = paramContext.getApplicationContext();
    this.d.put(Long.valueOf(-1L), "");
  }
  
  public static c a(Context paramContext)
  {
    if (a == null) {}
    try
    {
      if (a == null) {
        a = new c(paramContext);
      }
      return a;
    }
    finally {}
  }
  
  public String a(long paramLong)
  {
    if (this.d.containsKey(Long.valueOf(paramLong))) {
      return (String)this.d.get(Long.valueOf(paramLong));
    }
    Object localObject = CacheManager.getRegisterInfos(this.b);
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        String str = (String)((Iterator)localObject).next();
        b localB = CacheManager.getRegisterInfoByPkgName(str);
        if (localB != null) {
          this.d.put(Long.valueOf(localB.a), a(str));
        }
      }
    }
    if (this.d.get(Long.valueOf(paramLong)) == null) {
      return "";
    }
    return (String)this.d.get(Long.valueOf(paramLong));
  }
  
  public String a(String paramString)
  {
    if (paramString == null) {
      return "";
    }
    if (this.c.containsKey(paramString)) {
      return (String)this.c.get(paramString);
    }
    Object localObject = this.b.getPackageManager().getInstalledPackages(0);
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if (paramString.equals(localPackageInfo.packageName))
        {
          this.c.put(paramString, localPackageInfo.versionName);
          return localPackageInfo.versionName;
        }
      }
    }
    return "";
  }
}
