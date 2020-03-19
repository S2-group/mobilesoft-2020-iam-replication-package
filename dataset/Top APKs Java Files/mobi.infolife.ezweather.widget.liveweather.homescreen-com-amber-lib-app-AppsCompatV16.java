package com.amber.lib.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import java.util.List;

class AppsCompatV16
  extends AppsCompat
{
  private Context mContext;
  private PackageMonitor mPackageMonitor = new PackageMonitor();
  private PackageManager mPm;
  
  AppsCompatV16(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
    paramContext = new IntentFilter("android.intent.action.PACKAGE_ADDED");
    paramContext.addAction("android.intent.action.PACKAGE_REMOVED");
    paramContext.addAction("android.intent.action.PACKAGE_CHANGED");
    paramContext.addDataScheme("package");
    this.mContext.registerReceiver(this.mPackageMonitor, paramContext);
    paramContext = new IntentFilter();
    paramContext.addAction("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE");
    paramContext.addAction("android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE");
    this.mContext.registerReceiver(this.mPackageMonitor, paramContext);
  }
  
  protected void onFindPackageInfoList()
  {
    this.mPm = this.mContext.getPackageManager();
    List localList = this.mPm.getInstalledPackages(0);
    int i = 0;
    if (i < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      if ((localPackageInfo.applicationInfo.flags & 0x1) != 0) {}
      for (;;)
      {
        i += 1;
        break;
        String str = localPackageInfo.packageName;
        if (!TextUtils.isEmpty(str)) {
          onAppAdd(str, localPackageInfo);
        }
      }
    }
  }
  
  class PackageMonitor
    extends BroadcastReceiver
  {
    PackageMonitor() {}
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      String str = paramIntent.getAction();
      if (("android.intent.action.PACKAGE_CHANGED".equals(str)) || ("android.intent.action.PACKAGE_REMOVED".equals(str)) || ("android.intent.action.PACKAGE_ADDED".equals(str))) {
        if (paramIntent.getData() != null)
        {
          paramContext = paramIntent.getData().getSchemeSpecificPart();
          paramIntent.getBooleanExtra("android.intent.extra.REPLACING", false);
          if ((paramContext != null) && (paramContext.length() != 0)) {
            break label72;
          }
        }
      }
      label72:
      while (("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE".equals(str)) || (!"android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE".equals(str)))
      {
        do
        {
          do
          {
            for (;;)
            {
              return;
              paramContext = null;
            }
          } while ("android.intent.action.PACKAGE_CHANGED".equals(str));
          if ("android.intent.action.PACKAGE_REMOVED".equals(str))
          {
            AppsCompatV16.this.onAppRemove(paramContext, null);
            return;
          }
        } while (!"android.intent.action.PACKAGE_ADDED".equals(str));
        AppsCompatV16.this.onAppAdd(paramContext, null);
        return;
      }
    }
  }
}
