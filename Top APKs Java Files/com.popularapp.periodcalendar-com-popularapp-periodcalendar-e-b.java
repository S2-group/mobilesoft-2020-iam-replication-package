package com.popularapp.periodcalendar.e;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

public final class b
{
  public static boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(8192);
    if ((paramContext != null) && (paramContext.size() > 0)) {
      paramContext = paramContext.iterator();
    }
    PackageInfo localPackageInfo;
    do
    {
      if (!paramContext.hasNext()) {
        return false;
      }
      localPackageInfo = (PackageInfo)paramContext.next();
    } while ((TextUtils.isEmpty(localPackageInfo.packageName)) || (!localPackageInfo.packageName.equals(paramString)));
    return true;
  }
}
