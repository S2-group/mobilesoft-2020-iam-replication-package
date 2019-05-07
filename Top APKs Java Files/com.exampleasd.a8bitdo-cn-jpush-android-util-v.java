package cn.jpush.android.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.ArrayList;
import java.util.List;

public final class v
{
  public static ArrayList<ad> a(Context paramContext, boolean paramBoolean)
  {
    localArrayList = new ArrayList();
    try
    {
      List localList = paramContext.getPackageManager().getInstalledPackages(0);
      int i = 0;
      while (i < localList.size())
      {
        PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
        ad localAd = new ad();
        localAd.a = localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
        localAd.b = localPackageInfo.packageName;
        localAd.c = localPackageInfo.versionName;
        localAd.d = localPackageInfo.versionCode;
        localArrayList.add(localAd);
        i += 1;
      }
      return localArrayList;
    }
    catch (IndexOutOfBoundsException paramContext)
    {
      paramContext.printStackTrace();
      ac.b();
      return localArrayList;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      ac.b();
    }
  }
  
  public static String[] a(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramContext.getPackageName(), 4096).requestedPermissions;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      ac.i();
    }
    return null;
  }
}
