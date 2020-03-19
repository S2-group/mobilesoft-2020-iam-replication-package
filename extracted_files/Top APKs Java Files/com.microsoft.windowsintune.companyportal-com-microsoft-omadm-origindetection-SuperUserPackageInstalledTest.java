package com.microsoft.omadm.origindetection;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SuperUserPackageInstalledTest
  implements IOriginTest
{
  private static final String SUPERUSER_PACKAGE_FILENAME = "Superuser.apk";
  private static final List<String> SU_PACKAGES = Arrays.asList(new String[] { "eu.chainfire.supersu", "com.noshufou.android.su", "com.koushikdutta.superuser" });
  private static final String SYSTEM_APP_DIRECTORY = "/system/app";
  protected Context context;
  
  SuperUserPackageInstalledTest(Context paramContext)
  {
    this.context = paramContext;
  }
  
  public int execute()
  {
    return executeTest(this.context.getPackageManager().getInstalledPackages(0));
  }
  
  protected int executeTest(List<PackageInfo> paramList)
  {
    if (new File("/system/app/Superuser.apk").exists()) {
      return 2;
    }
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramList.next();
      if ((localPackageInfo.versionName != null) && (SU_PACKAGES.contains(localPackageInfo.packageName))) {
        return 2;
      }
    }
    return 0;
  }
}
