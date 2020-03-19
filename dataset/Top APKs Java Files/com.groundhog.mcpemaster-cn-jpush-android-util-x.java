package cn.jpush.android.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.ArrayList;
import java.util.List;

public final class x
{
  public static ArrayList<af> a(Context paramContext, boolean paramBoolean)
  {
    localArrayList = new ArrayList();
    try
    {
      List localList = paramContext.getPackageManager().getInstalledPackages(0);
      int i = 0;
      while (i < localList.size())
      {
        PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
        af localAf = new af();
        localAf.a = localPackageInfo.applicationInfo.loadLabel(paramContext.getPackageManager()).toString();
        localAf.b = localPackageInfo.packageName;
        localAf.c = localPackageInfo.versionName;
        localAf.d = localPackageInfo.versionCode;
        localArrayList.add(localAf);
        i += 1;
      }
      return localArrayList;
    }
    catch (IndexOutOfBoundsException paramContext)
    {
      paramContext.printStackTrace();
      ae.b();
      return localArrayList;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      ae.b();
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
      ae.i();
    }
    return null;
  }
}
