package com.amber.lib.widget.status;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class AppsCompatV16
  extends AppsCompat
{
  AppsCompatV16(Context paramContext, AppsCompat.OnAppChange paramOnAppChange)
  {
    super(paramContext, paramOnAppChange);
    this.mPm = paramContext.getPackageManager();
    paramOnAppChange = new IntentFilter();
    paramOnAppChange.addAction("android.intent.action.PACKAGE_REMOVED");
    paramOnAppChange.addAction("android.intent.action.PACKAGE_ADDED");
    paramOnAppChange.addAction("android.intent.action.INSTALL_PACKAGE");
    paramOnAppChange.addAction("android.intent.action.UNINSTALL_PACKAGE");
    paramOnAppChange.addDataScheme("package");
    paramContext.registerReceiver(new AppChangeReceiver(paramContext, this), paramOnAppChange);
    paramContext = new ArrayList();
    paramOnAppChange = this.mPm.getInstalledPackages(128);
    int i = 0;
    if (i < paramOnAppChange.size())
    {
      Object localObject = (PackageInfo)paramOnAppChange.get(i);
      if ((((PackageInfo)localObject).applicationInfo.flags & 0x1) != 0) {}
      for (;;)
      {
        i += 1;
        break;
        localObject = transformToAddApp(((PackageInfo)localObject).packageName, (PackageInfo)localObject);
        if (localObject != null) {
          paramContext.add(localObject);
        }
      }
    }
    setApps(paramContext);
  }
  
  static class AppChangeReceiver
    extends BroadcastReceiver
  {
    private Context mApplicationContext;
    private WeakReference<AppsCompat> mAppsCompat;
    
    public AppChangeReceiver(Context paramContext, AppsCompat paramAppsCompat)
    {
      this.mApplicationContext = paramContext;
      this.mAppsCompat = new WeakReference(paramAppsCompat);
    }
    
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent == null) {}
      AppsCompat localAppsCompat;
      do
      {
        do
        {
          return;
          paramContext = null;
        } while (this.mAppsCompat == null);
        localAppsCompat = (AppsCompat)this.mAppsCompat.get();
        if (localAppsCompat == null)
        {
          if (this.mApplicationContext != null) {
            this.mApplicationContext.unregisterReceiver(this);
          }
          this.mAppsCompat = null;
          return;
        }
        Uri localUri = paramIntent.getData();
        if (localUri != null) {
          paramContext = localUri.getSchemeSpecificPart();
        }
        if ((TextUtils.equals("android.intent.action.PACKAGE_ADDED", paramIntent.getAction())) || (TextUtils.equals("android.intent.action.INSTALL_PACKAGE", paramIntent.getAction())))
        {
          localAppsCompat.onAddPkg(paramContext);
          return;
        }
      } while ((!TextUtils.equals("android.intent.action.PACKAGE_REMOVED", paramIntent.getAction())) && (!TextUtils.equals("android.intent.action.UNINSTALL_PACKAGE", paramIntent.getAction())));
      localAppsCompat.onRemovePkg(paramContext);
    }
  }
}
