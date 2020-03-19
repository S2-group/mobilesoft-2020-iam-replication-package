package it.claudio.chimera.volume;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Process;
import android.view.View;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class e
  implements Runnable
{
  e(PackageManager paramPackageManager, ArrayList paramArrayList, a paramA, View paramView1, View paramView2, boolean paramBoolean) {}
  
  public void run()
  {
    Process.setThreadPriority(10);
    Iterator localIterator = this.a.getInstalledApplications(0).iterator();
    for (;;)
    {
      if (!localIterator.hasNext())
      {
        Collections.sort(this.b, new b());
        c.g.post(new f(this));
        return;
      }
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if ((this.f) || ((localApplicationInfo.flags & 0x1) == 0))
      {
        b localB = new b();
        localB.a = localApplicationInfo.loadLabel(this.a).toString();
        localB.b = localApplicationInfo.packageName;
        localB.d = new File(localApplicationInfo.sourceDir).length();
        this.b.add(localB);
      }
    }
  }
}
