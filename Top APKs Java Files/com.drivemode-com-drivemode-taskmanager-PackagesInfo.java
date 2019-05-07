package com.drivemode.taskmanager;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;

class PackagesInfo
{
  private List<ApplicationInfo> appList;
  
  public PackagesInfo(Context paramContext)
  {
    this.appList = paramContext.getApplicationContext().getPackageManager().getInstalledApplications(8192);
  }
  
  public ApplicationInfo getInfo(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    Iterator localIterator = this.appList.iterator();
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (paramString.equals(localApplicationInfo.processName)) {
        return localApplicationInfo;
      }
    }
    return null;
  }
}
