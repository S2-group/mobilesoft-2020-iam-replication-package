package com.aiadmobi.sdk.core.c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.aiadmobi.sdk.entity.KSAppEntity;
import com.aiadmobi.sdk.entity.KSUserAppInfoEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class d
{
  private static volatile d a;
  private String b;
  private com.aiadmobi.sdk.common.d.a c;
  private com.aiadmobi.sdk.common.a.a d;
  
  public d(com.aiadmobi.sdk.common.d.a paramA)
  {
    this.c = paramA;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("install_app_storage_");
    localStringBuilder.append(paramA.o().getToken());
    this.b = localStringBuilder.toString();
    this.d = com.aiadmobi.sdk.common.a.a.a(paramA, this.b);
  }
  
  public static d a(com.aiadmobi.sdk.common.d.a paramA)
  {
    if (a == null) {
      try
      {
        if (a == null) {
          a = new d(paramA);
        }
      }
      finally {}
    }
    return a;
  }
  
  private List<KSUserAppInfoEntity> a(List<KSUserAppInfoEntity> paramList)
  {
    Object localObject = b.a(this.c).b("app", "days_keep_in_uninstall").trim();
    if (((String)localObject).equals("")) {}
    try
    {
      i = Integer.parseInt((String)localObject);
    }
    catch (Exception localException)
    {
      int i;
      for (;;) {}
    }
    i = 7;
    localObject = c();
    if ((paramList != null) && (!paramList.isEmpty()))
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        KSUserAppInfoEntity localKSUserAppInfoEntity = (KSUserAppInfoEntity)paramList.next();
        if (!((Map)localObject).containsKey(localKSUserAppInfoEntity.getPackageName())) {
          if (localKSUserAppInfoEntity.getIsUninstall().booleanValue())
          {
            long l = localKSUserAppInfoEntity.getUninstallTime().longValue();
            if (System.currentTimeMillis() - l < i * 86400000L) {
              ((Map)localObject).put(localKSUserAppInfoEntity.getPackageName(), localKSUserAppInfoEntity);
            }
          }
          else
          {
            localKSUserAppInfoEntity.setIsUninstall(Boolean.valueOf(true));
            localKSUserAppInfoEntity.setUninstallTime(Long.valueOf(System.currentTimeMillis()));
            ((Map)localObject).put(localKSUserAppInfoEntity.getPackageName(), localKSUserAppInfoEntity);
          }
        }
      }
    }
    return new ArrayList(((Map)localObject).values());
  }
  
  private Map<String, KSUserAppInfoEntity> c()
  {
    HashMap localHashMap = new HashMap();
    List localList = this.c.n().getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      KSUserAppInfoEntity localKSUserAppInfoEntity = new KSUserAppInfoEntity();
      localKSUserAppInfoEntity.setAppName(localPackageInfo.applicationInfo.loadLabel(this.c.n().getPackageManager()).toString());
      localKSUserAppInfoEntity.setPackageName(localPackageInfo.packageName);
      localKSUserAppInfoEntity.setVersionName(localPackageInfo.versionName);
      localKSUserAppInfoEntity.setVersionCode(Integer.valueOf(localPackageInfo.versionCode));
      localKSUserAppInfoEntity.setLastUpdateTime(Long.valueOf(localPackageInfo.lastUpdateTime));
      localKSUserAppInfoEntity.setFirstLaunchTime(Long.valueOf(localPackageInfo.firstInstallTime));
      localKSUserAppInfoEntity.setIsUninstall(Boolean.valueOf(false));
      if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
        localKSUserAppInfoEntity.setIsSystemApp(KSUserAppInfoEntity.STATE_NOT_SYSTEM_APP);
      } else {
        localKSUserAppInfoEntity.setIsSystemApp(KSUserAppInfoEntity.STATE_SYSTEM_APP);
      }
      localHashMap.put(localPackageInfo.packageName, localKSUserAppInfoEntity);
      i += 1;
    }
    return localHashMap;
  }
  
  public void a()
  {
    List localList = a(this.d.a(this.b, KSUserAppInfoEntity.class, new ArrayList()));
    this.d.a(this.b, localList);
  }
  
  public List<KSUserAppInfoEntity> b()
  {
    if (!this.d.b(this.b)) {
      a();
    }
    return this.d.a(this.b, KSUserAppInfoEntity.class, new ArrayList());
  }
}
