package com.dianxinos.lazyswipe.utils;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.List;
import java.util.Map;

class d
  implements Runnable
{
  d(c paramC) {}
  
  public void run()
  {
    List localList = c.a(this.a).getInstalledPackages(512);
    int i = 0;
    while (i < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      String str = localPackageInfo.packageName;
      if (c.b(this.a).get(str) == null)
      {
        e localE = new e(this.a, null);
        localE.b = localPackageInfo.applicationInfo.loadLabel(c.a(this.a)).toString();
        c.b(this.a).put(str, localE);
      }
      i += 1;
    }
  }
}
