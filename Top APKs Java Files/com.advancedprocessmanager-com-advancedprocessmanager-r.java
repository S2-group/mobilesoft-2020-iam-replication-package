package com.advancedprocessmanager;

import android.app.ProgressDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class r
  extends Thread
{
  r(IgnoreActivity paramIgnoreActivity, ProgressDialog paramProgressDialog) {}
  
  public void run()
  {
    this.b.l.clear();
    List localList = IgnoreActivity.a(this.b);
    Iterator localIterator = this.b.a.getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if (this.b.a(localPackageInfo.applicationInfo)) {
        this.b.l.add(new v(this.b, localPackageInfo, localList.contains(localPackageInfo.packageName)));
      }
    }
    Collections.sort(this.b.l, this.b.m);
    this.b.j.sendEmptyMessage(1);
    this.a.dismiss();
  }
}
