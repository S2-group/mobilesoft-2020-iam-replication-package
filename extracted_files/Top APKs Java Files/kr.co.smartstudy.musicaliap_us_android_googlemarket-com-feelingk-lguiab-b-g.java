package com.feelingk.lguiab.b;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.List;

public final class g
{
  public static final int a = 0;
  public static final int b = 1;
  public static final int c = 2;
  private static final String d = "android.lgt.appstore";
  private static final String e = "com.lguplus.appstore";
  private Context f = null;
  
  public g(Context paramContext)
  {
    this.f = paramContext;
  }
  
  public final int a()
  {
    List localList = this.f.getPackageManager().getInstalledPackages(0);
    int i = 0;
    for (;;)
    {
      if (i >= localList.size()) {
        return 0;
      }
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      new StringBuilder("PackageName : ").append(localPackageInfo.packageName.toString()).append(" version :").append(localPackageInfo.versionName).toString();
      if (localPackageInfo.packageName.equals("android.lgt.appstore")) {
        return 1;
      }
      if (localPackageInfo.packageName.equals("com.lguplus.appstore")) {
        return 2;
      }
      i += 1;
    }
  }
}
