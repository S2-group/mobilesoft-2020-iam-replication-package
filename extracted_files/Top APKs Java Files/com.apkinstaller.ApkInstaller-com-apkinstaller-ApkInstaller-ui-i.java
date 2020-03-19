package com.apkinstaller.ApkInstaller.ui;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.apkinstaller.ApkInstaller.d.d;
import com.apkinstaller.ApkInstaller.i.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class i
  implements Runnable
{
  i(Manager paramManager) {}
  
  public final void run()
  {
    PackageManager localPackageManager = this.a.getPackageManager();
    List localList = localPackageManager.getInstalledApplications(8320);
    localList = Manager.a(this.a, localList);
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = localList.iterator();
    if (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      d localD = new d();
      localD.j = localApplicationInfo.packageName;
      if ((localApplicationInfo.flags & 0x1) != 0) {}
      for (boolean bool = true;; bool = false)
      {
        localD.h = bool;
        if (localD.h) {
          localD.i = localApplicationInfo.enabled;
        }
        localArrayList.add(localD);
        break;
      }
    }
    this.a.w.a(localArrayList);
    this.a.F.sendEmptyMessage(0);
    new Thread(new j(this, localList, localPackageManager)).start();
    new Thread(new k(this, localList, localPackageManager)).start();
  }
}
