package com.alarmclock.xtreme.free.o;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class bzg
  extends BroadcastReceiver
  implements bzi
{
  private PackageManager a;
  private boolean b = true;
  private String[] c;
  
  public bzg(PackageManager paramPackageManager, Context paramContext)
  {
    this.a = paramPackageManager;
    a(paramContext);
  }
  
  private void a()
  {
    this.b = true;
  }
  
  private void a(Context paramContext)
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.PACKAGE_ADDED");
    localIntentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
    localIntentFilter.addDataScheme("package");
    paramContext.registerReceiver(this, localIntentFilter);
  }
  
  private String[] b(bwz<PackageInfo> paramBwz)
  {
    if (this.b)
    {
      Object localObject = this.a.getInstalledPackages(64);
      ArrayList localArrayList = new ArrayList();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if ((paramBwz == null) || (paramBwz.a(localPackageInfo))) {
          localArrayList.add(localPackageInfo.packageName);
        }
      }
      this.c = ((String[])localArrayList.toArray(new String[localArrayList.size()]));
      this.b = false;
    }
    return this.c;
  }
  
  public String[] a(bwz<PackageInfo> paramBwz)
  {
    return b(paramBwz);
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    a();
  }
}
