package com.tools.c;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import java.util.Iterator;
import java.util.List;

class co
  extends Thread
{
  co(ck paramCk) {}
  
  public void run()
  {
    this.a.aj.sendEmptyMessage(1);
    this.a.f.clear();
    Iterator localIterator = this.a.b.getInstalledPackages(64).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      cq localCq = new cq(this.a);
      if (localCq.a(localPackageInfo)) {
        this.a.f.add(localCq);
      }
    }
    this.a.aj.sendEmptyMessage(0);
  }
}
