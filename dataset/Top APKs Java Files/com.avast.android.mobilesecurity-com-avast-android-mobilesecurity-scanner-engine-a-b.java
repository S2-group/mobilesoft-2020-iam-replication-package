package com.avast.android.mobilesecurity.scanner.engine.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.avast.android.dagger.Application;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class b
  implements a
{
  private final String a;
  private final PackageManager b;
  
  @Inject
  public b(@Application Context paramContext)
  {
    this.a = paramContext.getPackageName();
    this.b = paramContext.getPackageManager();
  }
  
  public List<PackageInfo> a()
  {
    ArrayList localArrayList = new ArrayList();
    List localList = this.b.getInstalledApplications(0);
    int j = localList.size();
    int i = 0;
    for (;;)
    {
      if (i < j)
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)localList.get(i);
        if ((!localApplicationInfo.sourceDir.startsWith("/system")) && (!localApplicationInfo.packageName.equals(this.a))) {}
        try
        {
          localArrayList.add(this.b.getPackageInfo(localApplicationInfo.packageName, 0));
          i += 1;
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          for (;;)
          {
            com.avast.android.mobilesecurity.d.a.p.d(localNameNotFoundException, "Can't find installed app: %s", new Object[] { localApplicationInfo.packageName });
          }
        }
      }
    }
    return localArrayList;
  }
}
