package com.avast.android.feed.internal.device.apps;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultAvastAppsProvider
  implements AvastAppsProvider
{
  private final PackageManager a;
  private List<AvastApplication> b;
  
  public DefaultAvastAppsProvider(PackageManager paramPackageManager)
  {
    this.a = paramPackageManager;
  }
  
  private List<AvastApplication> a()
  {
    Object localObject1 = null;
    Iterator localIterator = this.a.getInstalledPackages(64).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if ((!TextUtils.isEmpty(localPackageInfo.packageName)) && (!TextUtils.isEmpty(localPackageInfo.versionName)) && (localPackageInfo.packageName.startsWith("com.avast")))
      {
        Object localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = new ArrayList();
        }
        ((List)localObject2).add(new AvastApplication(localPackageInfo.packageName, localPackageInfo.versionName, localPackageInfo.versionCode));
        localObject1 = localObject2;
      }
    }
    this.b = localObject1;
    return this.b;
  }
  
  public List<AvastApplication> a(boolean paramBoolean)
  {
    if ((this.b == null) || (!paramBoolean)) {
      a();
    }
    return this.b;
  }
}
