package com.newbay.syncdrive.android.ui.util;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.synchronoss.android.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BaInstalledHelper
{
  private static String a = "BaInstalledHelper";
  private static final HashMap<String, String> b = new BaInstalledHelper.1();
  private final Log c;
  private final PackageManager d;
  
  @Inject
  public BaInstalledHelper(Log paramLog, PackageManager paramPackageManager)
  {
    this.c = paramLog;
    this.d = paramPackageManager;
  }
  
  public final boolean a()
  {
    this.c.a(a, "Checking for MediaHux", new Object[0]);
    if ("dlx, C811".contains(Build.DEVICE)) {
      return false;
    }
    Iterator localIterator = this.d.getInstalledApplications(0).iterator();
    ApplicationInfo localApplicationInfo;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localApplicationInfo = (ApplicationInfo)localIterator.next();
    } while ((localApplicationInfo.packageName == null) || (!b.containsKey(localApplicationInfo.packageName)));
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
}
