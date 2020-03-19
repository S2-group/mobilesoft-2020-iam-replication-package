package com.avast.android.feed.internal.device.apps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.avast.android.feed.internal.Filter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultPackageNamesProvider
  extends BroadcastReceiver
  implements PackageNamesProvider
{
  private PackageManager a;
  private boolean b = true;
  private String[] c;
  
  public DefaultPackageNamesProvider(PackageManager paramPackageManager, Context paramContext)
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
  
  private String[] b(Filter<PackageInfo> paramFilter)
  {
    if (this.b)
    {
      Object localObject = this.a.getInstalledPackages(64);
      ArrayList localArrayList = new ArrayList();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
        if ((paramFilter == null) || (paramFilter.a(localPackageInfo))) {
          localArrayList.add(localPackageInfo.packageName);
        }
      }
      this.c = ((String[])localArrayList.toArray(new String[localArrayList.size()]));
      this.b = false;
    }
    return this.c;
  }
  
  public String[] a(Filter<PackageInfo> paramFilter)
  {
    return b(paramFilter);
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    a();
  }
}
