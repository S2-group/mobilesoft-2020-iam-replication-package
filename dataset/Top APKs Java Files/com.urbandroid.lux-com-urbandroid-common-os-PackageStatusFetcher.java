package com.urbandroid.common.os;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.urbandroid.common.logging.Logger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PackageStatusFetcher
{
  private final Context context;
  private final Map statuses = new HashMap();
  
  public PackageStatusFetcher(Context paramContext)
  {
    this.context = paramContext;
  }
  
  private void loadInstalledApplications()
  {
    try
    {
      Iterator localIterator = this.context.getPackageManager().getInstalledPackages(8).iterator();
      while (localIterator.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        this.statuses.put(localPackageInfo.packageName, PackageStatusFetcher.Status.INSTALLED_NOT_RUNNING);
      }
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      Logger.logWarning("Failed to fetch installed apps", localNullPointerException);
    }
  }
  
  private void loadRunningApplications()
  {
    Object localObject1 = (ActivityManager)this.context.getSystemService("activity");
    Object localObject2 = ((ActivityManager)localObject1).getRunningTasks(50).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      ActivityManager.RunningTaskInfo localRunningTaskInfo = (ActivityManager.RunningTaskInfo)((Iterator)localObject2).next();
      this.statuses.put(localRunningTaskInfo.baseActivity.getPackageName(), PackageStatusFetcher.Status.RUNNING);
    }
    localObject1 = ((ActivityManager)localObject1).getRunningServices(50).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (ActivityManager.RunningServiceInfo)((Iterator)localObject1).next();
      this.statuses.put(((ActivityManager.RunningServiceInfo)localObject2).service.getPackageName(), PackageStatusFetcher.Status.RUNNING);
    }
  }
  
  public PackageStatusFetcher.Status getPackageStatus(String paramString)
  {
    paramString = (PackageStatusFetcher.Status)this.statuses.get(paramString);
    if (paramString != null) {
      return paramString;
    }
    return PackageStatusFetcher.Status.NOT_INSTALLED;
  }
  
  public PackageStatusFetcher refreshStatus()
  {
    this.statuses.clear();
    loadInstalledApplications();
    return this;
  }
}
