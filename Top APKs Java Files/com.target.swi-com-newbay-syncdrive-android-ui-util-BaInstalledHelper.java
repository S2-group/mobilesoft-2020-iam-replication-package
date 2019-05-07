package com.newbay.syncdrive.android.ui.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.synchronoss.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class BaInstalledHelper
{
  private static String a = "BaInstalledHelper";
  private static final String[] b = { "com.fusionone.android.sync.service", "com.fusionone.android.sync.baclient", "com.fusionone.android.sync.sonyericssonr800xbaclient", "com.fusionone.android.sync.motorolaflemingbaclient", "com.fusionone.android.sync.motorolapasteurbaclient", "com.fusionone.android.sync.samsungi800baclient", "com.fusionone.android.sync.samsungi815baclient", "com.fusionone.android.sync.samsungi905baclient", "com.fusionone.android.sync.ztev66baclient", "com.fusionone.android.sync.ztev68baclient" };
  private static final HashMap<String, String> c = new BaInstalledHelper.1();
  private final Log d;
  private final PackageManager e;
  private final Context f;
  
  @Inject
  public BaInstalledHelper(Log paramLog, PackageManager paramPackageManager, Context paramContext)
  {
    this.d = paramLog;
    this.e = paramPackageManager;
    this.f = paramContext;
  }
  
  public final boolean a()
  {
    this.d.a(a, "Checking for MediaHux", new Object[0]);
    if ("dlx, C811".contains(Build.DEVICE)) {
      return false;
    }
    Iterator localIterator = this.e.getInstalledApplications(0).iterator();
    ApplicationInfo localApplicationInfo;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localApplicationInfo = (ApplicationInfo)localIterator.next();
    } while ((localApplicationInfo.packageName == null) || (!c.containsKey(localApplicationInfo.packageName)));
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final boolean b()
  {
    this.d.a(a, "in checkPackagesIfBaClientInstalled", new Object[0]);
    Iterator localIterator = this.e.getInstalledApplications(0).iterator();
    boolean bool = false;
    label111:
    while (localIterator.hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localIterator.next();
      if (localApplicationInfo.packageName != null)
      {
        String[] arrayOfString = b;
        int j = arrayOfString.length;
        int i = 0;
        for (;;)
        {
          if (i >= j) {
            break label111;
          }
          String str = arrayOfString[i];
          if (localApplicationInfo.packageName.startsWith(str))
          {
            bool = true;
            break;
          }
          i += 1;
        }
      }
    }
    return bool;
  }
}
