package com.lionmobi.powerclean.quietnotifications;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import com.lionmobi.util.o;
import de.greenrobot.event.c;
import java.util.ArrayList;
import java.util.List;

final class m
  extends Thread
  implements Runnable
{
  private e b;
  private List c = new ArrayList();
  
  m(QuietNotificationSettingsActivity paramQuietNotificationSettingsActivity) {}
  
  public final void run()
  {
    super.run();
    PackageManager localPackageManager = this.a.getPackageManager();
    List localList = localPackageManager.getInstalledPackages(0);
    int i = 0;
    if (i < localList.size())
    {
      Object localObject2 = (PackageInfo)localList.get(i);
      int j = ((PackageInfo)localObject2).applicationInfo.flags;
      Object localObject1 = ((PackageInfo)localObject2).applicationInfo;
      label84:
      String str;
      Object localObject3;
      if (((j & 0x1) <= 0) || ((((PackageInfo)localObject2).applicationInfo.flags & 0x80) != 0))
      {
        j = 1;
        localObject1 = ((PackageInfo)localObject2).packageName;
        if (((j != 0) || (o.getQuietWhiteList().contains(localObject1))) && (!o.getIgnoreNotificationList().contains(localObject1)))
        {
          str = localPackageManager.getApplicationLabel(((PackageInfo)localObject2).applicationInfo).toString();
          localObject2 = localPackageManager.getApplicationIcon(((PackageInfo)localObject2).applicationInfo);
          this.b = new e();
          localObject3 = r.getWhiteList(this.a);
          if (!((String)localObject3).contains("%%%%")) {
            break label296;
          }
          j = 0;
          label180:
          if (j < QuietNotificationSettingsActivity.h(this.a).size())
          {
            if (!((String)localObject1).contains((CharSequence)QuietNotificationSettingsActivity.h(this.a).get(j))) {
              break label289;
            }
            this.b.setIsCherk(true);
            this.b.setSorting(1);
          }
        }
      }
      label289:
      label296:
      label398:
      for (;;)
      {
        this.b.setName(str);
        this.b.setIcon((Drawable)localObject2);
        this.b.setPackageName((String)localObject1);
        this.c.add(this.b);
        i += 1;
        break;
        j = 0;
        break label84;
        j += 1;
        break label180;
        ArrayList localArrayList = new ArrayList();
        localObject3 = ((String)localObject3).split(",");
        j = 0;
        while (j < localObject3.length)
        {
          localArrayList.add(localObject3[j]);
          j += 1;
        }
        j = 0;
        for (;;)
        {
          if (j >= localArrayList.size()) {
            break label398;
          }
          if (((String)localObject1).contains((CharSequence)localArrayList.get(j)))
          {
            this.b.setIsCherk(true);
            this.b.setSorting(1);
            break;
          }
          j += 1;
        }
      }
    }
    c.getDefault().post(new d(this.c));
  }
}
