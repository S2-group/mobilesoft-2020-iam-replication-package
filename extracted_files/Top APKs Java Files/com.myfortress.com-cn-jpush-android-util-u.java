package cn.jpush.android.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.ArrayList;
import java.util.List;

public final class u
{
  public static ArrayList<ac> a(Context paramContext, boolean paramBoolean)
  {
    localArrayList = new ArrayList();
    try
    {
      List localList = paramContext.getPackageManager().getInstalledPackages(0);
      int i = 0;
      while (i < localList.size())
      {
        PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
        ac localAc = new ac();
        localAc.a = localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
        localAc.b = localPackageInfo.packageName;
        localAc.c = localPackageInfo.versionName;
        localAc.d = localPackageInfo.versionCode;
        localArrayList.add(localAc);
        i += 1;
      }
      return localArrayList;
    }
    catch (IndexOutOfBoundsException paramContext)
    {
      paramContext.printStackTrace();
      ab.b();
      return localArrayList;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      ab.b();
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
      ab.i();
    }
    return null;
  }
}
