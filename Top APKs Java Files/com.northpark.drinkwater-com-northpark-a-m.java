package com.northpark.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.util.Iterator;
import java.util.List;

public class m
{
  public static boolean a(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(8192);
    if ((paramContext != null) && (paramContext.size() > 0))
    {
      paramContext = paramContext.iterator();
      while (paramContext.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
        if ((!TextUtils.isEmpty(localPackageInfo.packageName)) && (localPackageInfo.packageName.equals("com.google.android.gm"))) {
          return true;
        }
      }
    }
    return false;
  }
}
