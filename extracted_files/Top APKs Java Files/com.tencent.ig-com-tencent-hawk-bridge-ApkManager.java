package com.tencent.hawk.bridge;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;

public class ApkManager
{
  public ApkManager() {}
  
  public static PackageInfo getPackageInfoByPackageName(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null)) {
      return null;
    }
    paramContext = paramContext.getPackageManager();
    if (paramContext != null)
    {
      paramContext = paramContext.getInstalledPackages(0);
      if (paramContext == null) {
        return null;
      }
      paramContext = paramContext.iterator();
    }
    PackageInfo localPackageInfo;
    do
    {
      if (!paramContext.hasNext()) {
        return null;
      }
      localPackageInfo = (PackageInfo)paramContext.next();
    } while (!paramString.equals(localPackageInfo.applicationInfo.packageName));
    return localPackageInfo;
  }
}
