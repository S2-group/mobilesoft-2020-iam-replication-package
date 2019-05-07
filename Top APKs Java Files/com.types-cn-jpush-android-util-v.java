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
    ArrayList localArrayList = new ArrayList();
    try
    {
      Object localObject = paramContext.getPackageManager();
      int i = 0;
      localObject = ((PackageManager)localObject).getInstalledPackages(0);
      while (i < ((List)localObject).size())
      {
        PackageInfo localPackageInfo = (PackageInfo)((List)localObject).get(i);
        ad localAd = new ad();
        localAd.a = localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
        localAd.b = localPackageInfo.packageName;
        localAd.c = localPackageInfo.versionName;
        localAd.d = localPackageInfo.versionCode;
        localArrayList.add(localAd);
        i += 1;
        continue;
        ac.b();
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    catch (IndexOutOfBoundsException paramContext)
    {
      paramContext.printStackTrace();
    }
    return localArrayList;
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
      for (;;) {}
    }
    ac.i();
    return null;
  }
}
