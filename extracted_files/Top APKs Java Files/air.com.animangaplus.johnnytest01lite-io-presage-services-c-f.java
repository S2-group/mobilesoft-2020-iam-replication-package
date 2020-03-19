package io.presage.services.c;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import io.presage.services.a.d;
import io.presage.services.b.b;
import io.presage.utils.i;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class f
  extends a
  implements k
{
  private ActivityManager b;
  private List c;
  
  public f(String paramString)
  {
    super(new d(), paramString);
  }
  
  public final void g()
  {
    int j = 0;
    if (!c()) {}
    for (;;)
    {
      return;
      if (this.b == null) {
        this.b = ((ActivityManager)d().getSystemService("activity"));
      }
      Object localObject1;
      if (this.c == null)
      {
        localObject1 = new Intent("android.intent.action.MAIN");
        ((Intent)localObject1).addCategory("android.intent.category.HOME");
        localObject1 = d().getPackageManager().resolveActivity((Intent)localObject1, 0);
        Object localObject2 = new Intent("android.intent.action.MAIN", null);
        ((Intent)localObject2).addCategory("android.intent.category.LAUNCHER");
        localObject2 = d().getPackageManager().queryIntentActivities((Intent)localObject2, 0);
        this.c = new ArrayList();
        List localList = d().getPackageManager().getInstalledPackages(0);
        i = 0;
        if (i < localList.size())
        {
          PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
          ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
          Object localObject3;
          if ((!((ResolveInfo)localObject1).activityInfo.packageName.equals(localPackageInfo.packageName)) && (!"com.android.browser".equals(localPackageInfo.packageName)) && (!"com.android.chrome".equals(localPackageInfo.packageName)) && (!"com.sec.android.app.sbrowser".equals(localPackageInfo.packageName)))
          {
            localObject3 = ((List)localObject2).iterator();
            bool1 = false;
            while (((Iterator)localObject3).hasNext())
            {
              boolean bool2 = ((ResolveInfo)((Iterator)localObject3).next()).activityInfo.packageName.equals(localPackageInfo.packageName);
              bool1 = bool2;
              if (bool2) {
                bool1 = bool2;
              }
            }
            if (bool1)
            {
              label286:
              localObject3 = new b();
              ((b)localObject3).b(localPackageInfo.packageName);
              ((b)localObject3).c(localPackageInfo.versionName);
              ((b)localObject3).d(String.valueOf(localPackageInfo.versionCode));
              if ((localApplicationInfo.flags & 0x1) != 1) {
                break label498;
              }
            }
          }
          label498:
          for (boolean bool1 = true;; bool1 = false)
          {
            ((b)localObject3).a(bool1);
            ((b)localObject3).b(((ResolveInfo)localObject1).activityInfo.packageName.equals(localPackageInfo.packageName));
            this.c.add(localObject3);
            i += 1;
            break;
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append(localPackageInfo.packageName);
            ((StringBuilder)localObject3).append(":");
            ((StringBuilder)localObject3).append(localPackageInfo.versionName);
            ((StringBuilder)localObject3).append(":");
            ((StringBuilder)localObject3).append(localPackageInfo.versionCode);
            ((StringBuilder)localObject3).append(":");
            ((StringBuilder)localObject3).append(((ResolveInfo)localObject1).activityInfo.packageName.equals(localPackageInfo.packageName));
            i.b(new String[] { "DEBUG", ((StringBuilder)localObject3).toString() });
            break label286;
          }
        }
      }
      ((d)a()).a();
      int i = j;
      while (i < this.c.size())
      {
        localObject1 = ((b)this.c.get(i)).c();
        ((d)a()).a((io.presage.services.b.f)localObject1);
        ((b)localObject1).e();
        i += 1;
      }
    }
  }
}
