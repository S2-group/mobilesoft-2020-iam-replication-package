package io.presage.services.for;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import io.presage.services.do.d;
import io.presage.services.if.b;
import io.presage.services.if.f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class e
  extends a<d<b>>
  implements j
{
  private ActivityManager b;
  private List<b> c;
  
  public e(String paramString)
  {
    super(new d(), paramString);
  }
  
  public final void h()
  {
    int j = 0;
    if (!c()) {
      return;
    }
    if (this.b == null) {
      this.b = ((ActivityManager)d().getSystemService("activity"));
    }
    Object localObject1;
    if (this.c == null)
    {
      localObject1 = new Intent("android.intent.action.MAIN");
      ((Intent)localObject1).addCategory("android.intent.category.HOME");
      this.c = new ArrayList();
    }
    label585:
    label588:
    label591:
    for (;;)
    {
      try
      {
        localObject1 = d().getPackageManager().resolveActivity((Intent)localObject1, 0);
        Object localObject2 = new Intent("android.intent.action.MAIN", null);
        ((Intent)localObject2).addCategory("android.intent.category.LAUNCHER");
        localObject2 = d().getPackageManager().queryIntentActivities((Intent)localObject2, 0);
        List localList = d().getPackageManager().getInstalledPackages(0);
        int i = 0;
        if (i < localList.size())
        {
          PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
          ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
          Object localObject3;
          if (((((ResolveInfo)localObject1).activityInfo == null) || (!((ResolveInfo)localObject1).activityInfo.packageName.equals(localPackageInfo.packageName))) && (!"com.android.browser".equals(localPackageInfo.packageName)) && (!"com.android.chrome".equals(localPackageInfo.packageName)) && (!"com.sec.android.app.sbrowser".equals(localPackageInfo.packageName)))
          {
            localObject3 = ((List)localObject2).iterator();
            bool = false;
            if (!((Iterator)localObject3).hasNext()) {
              break label591;
            }
            ResolveInfo localResolveInfo = (ResolveInfo)((Iterator)localObject3).next();
            if (localResolveInfo.activityInfo == null) {
              break label588;
            }
            bool = localResolveInfo.activityInfo.packageName.equals(localPackageInfo.packageName);
            if (!bool) {
              break label585;
            }
            if (bool)
            {
              localObject3 = new b();
              ((b)localObject3).b(localPackageInfo.packageName);
              ((b)localObject3).c(localPackageInfo.versionName);
              ((b)localObject3).d(String.valueOf(localPackageInfo.versionCode));
              if ((localApplicationInfo.flags & 0x1) == 1)
              {
                bool = true;
                ((b)localObject3).a(bool);
                ((b)localObject3).b(((ResolveInfo)localObject1).activityInfo.packageName.equals(localPackageInfo.packageName));
                this.c.add(localObject3);
              }
            }
            else
            {
              i += 1;
            }
          }
          else
          {
            localObject3 = new StringBuilder();
            ((StringBuilder)localObject3).append(localPackageInfo.packageName);
            ((StringBuilder)localObject3).append(":");
            ((StringBuilder)localObject3).append(localPackageInfo.versionName);
            ((StringBuilder)localObject3).append(":");
            ((StringBuilder)localObject3).append(localPackageInfo.versionCode);
            ((StringBuilder)localObject3).append(":");
            ((StringBuilder)localObject3).append(((ResolveInfo)localObject1).activityInfo.packageName.equals(localPackageInfo.packageName));
            io.presage.utils.e.b(new String[] { "DEBUG", ((StringBuilder)localObject3).toString() });
            continue;
          }
          boolean bool = false;
          continue;
        }
        else
        {
          ((d)a()).a();
          i = j;
          if (i >= this.c.size()) {
            break;
          }
          localObject1 = ((b)this.c.get(i)).c();
          ((d)a()).a((f)localObject1);
          ((b)localObject1).e();
          i += 1;
          continue;
        }
        continue;
      }
      catch (Exception localException)
      {
        return;
      }
    }
  }
}
