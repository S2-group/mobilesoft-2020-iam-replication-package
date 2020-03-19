package com.adincube.sdk.k.b;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.adincube.sdk.f.b.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class h
{
  public static List<String> a(Context paramContext)
  {
    localArrayList = new ArrayList();
    if (!b.a(com.adincube.sdk.manager.a.a().a(true, true), com.adincube.sdk.f.b.a.e)) {
      return localArrayList;
    }
    try
    {
      paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
      while (paramContext.hasNext())
      {
        PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
        if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
          localArrayList.add(localPackageInfo.packageName);
        }
      }
      return localArrayList;
    }
    catch (RuntimeException paramContext) {}
  }
}
