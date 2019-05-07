package com.arrkii.nativesdk.c;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import com.arrkii.nativesdk.d.i;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final class h
  implements Runnable
{
  h(e paramE, Context paramContext) {}
  
  public final void run()
  {
    try
    {
      Object localObject1 = this.a.getPackageManager().getInstalledPackages(128);
      if ((localObject1 != null) && (((List)localObject1).size() > 0)) {
        this.b.a.clear();
      }
      localObject1 = ((List)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Object localObject2 = (PackageInfo)((Iterator)localObject1).next();
        if (((PackageInfo)localObject2).packageName.equalsIgnoreCase("com.android.vending")) {
          localObject2 = ((PackageInfo)localObject2).versionName;
        } else if (((PackageInfo)localObject2).packageName.equalsIgnoreCase("com.google.android.gms")) {
          localObject2 = ((PackageInfo)localObject2).versionName;
        } else if (!i.a((PackageInfo)localObject2)) {
          this.b.a.put(((PackageInfo)localObject2).packageName, localObject2);
        }
      }
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    this.b.c.sendEmptyMessage(1);
  }
}
