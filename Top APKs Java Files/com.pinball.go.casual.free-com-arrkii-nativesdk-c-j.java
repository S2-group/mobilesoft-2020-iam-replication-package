package com.arrkii.nativesdk.c;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import com.arrkii.nativesdk.d.n;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class j
  implements Runnable
{
  j(e paramE, Context paramContext) {}
  
  public final void run()
  {
    try
    {
      Object localObject1 = this.a.getPackageManager().getInstalledPackages(128);
      if ((localObject1 != null) && (((List)localObject1).size() > 0)) {
        e.b(this.b).clear();
      }
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Object localObject2 = (PackageInfo)((Iterator)localObject1).next();
        if (((PackageInfo)localObject2).packageName.equalsIgnoreCase("com.android.vending")) {
          localObject2 = ((PackageInfo)localObject2).versionName;
        } else if (((PackageInfo)localObject2).packageName.equalsIgnoreCase("com.google.android.gms")) {
          localObject2 = ((PackageInfo)localObject2).versionName;
        } else if (!n.a((PackageInfo)localObject2)) {
          e.b(this.b).put(((PackageInfo)localObject2).packageName, localObject2);
        }
      }
      return;
    }
    catch (Exception localException)
    {
      e.c(this.b).sendEmptyMessage(1);
    }
  }
}
