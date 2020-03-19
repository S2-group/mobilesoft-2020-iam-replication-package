package com.amber.lib.applive.util.apps;

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

public class AppsCompatV16
  extends AppsCompat
{
  private Context mContext;
  private PackageMonitor mPackageMonitor = new PackageMonitor();
  private PackageManager mPm;
  
  AppsCompatV16(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
    initData();
  }
  
  private void initData()
  {
    this.mPm = this.mContext.getPackageManager();
    Object localObject1 = this.mPm.getInstalledPackages(0);
    int i = 0;
    if (i < ((List)localObject1).size())
    {
      Object localObject2 = (PackageInfo)((List)localObject1).get(i);
      if ((((PackageInfo)localObject2).applicationInfo.flags & 0x1) != 0) {}
      for (;;)
      {
        i += 1;
        break;
        localObject2 = ((PackageInfo)localObject2).packageName;
        if (!TextUtils.isEmpty((CharSequence)localObject2)) {
          addPackageName((String)localObject2);
        }
      }
    }
    localObject1 = new IntentFilter("android.intent.action.PACKAGE_ADDED");
    ((IntentFilter)localObject1).addAction("android.intent.action.PACKAGE_REMOVED");
    ((IntentFilter)localObject1).addAction("android.intent.action.PACKAGE_CHANGED");
    ((IntentFilter)localObject1).addDataScheme("package");
    this.mContext.registerReceiver(this.mPackageMonitor, (IntentFilter)localObject1);
    localObject1 = new IntentFilter();
    ((IntentFilter)localObject1).addAction("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE");
    ((IntentFilter)localObject1).addAction("android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE");
    this.mContext.registerReceiver(this.mPackageMonitor, (IntentFilter)localObject1);
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
            AppsCompatV16.this.removePackageName(paramContext);
            return;
          }
        } while (!"android.intent.action.PACKAGE_ADDED".equals(str));
        AppsCompatV16.this.addPackageName(paramContext);
        return;
      }
    }
  }
}
