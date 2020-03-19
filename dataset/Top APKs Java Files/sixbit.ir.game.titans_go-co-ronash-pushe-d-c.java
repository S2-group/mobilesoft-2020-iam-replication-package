package co.ronash.pushe.d;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class c
{
  private Context a;
  
  public c(Context paramContext)
  {
    this.a = paramContext;
  }
  
  public List<b> a()
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = this.a.getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledPackages(0).iterator();
    for (;;)
    {
      PackageInfo localPackageInfo;
      b localB;
      if (localIterator.hasNext())
      {
        localPackageInfo = (PackageInfo)localIterator.next();
        if ((localPackageInfo.applicationInfo.flags & 0x1) == 1) {
          continue;
        }
        localB = new b();
        localB.a(localPackageInfo.packageName);
        localB.b(localPackageInfo.versionName);
        localB.a(localPackageInfo.firstInstallTime);
        localB.b(localPackageInfo.lastUpdateTime);
        localB.d(String.valueOf(localPackageInfo.applicationInfo.nonLocalizedLabel));
      }
      try
      {
        localB.c(localPackageManager.getInstallerPackageName(localPackageInfo.packageName));
        localArrayList.add(localB);
        continue;
        return localArrayList;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        for (;;) {}
      }
    }
  }
  
  public String b()
  {
    Object localObject = this.a.getPackageManager();
    try
    {
      localObject = ((PackageManager)localObject).getInstallerPackageName(this.a.getPackageName());
      return localObject;
    }
    catch (IllegalArgumentException localIllegalArgumentException) {}
    return null;
  }
}
