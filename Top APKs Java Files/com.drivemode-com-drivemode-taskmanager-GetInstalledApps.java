package com.drivemode.taskmanager;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetInstalledApps
{
  public GetInstalledApps() {}
  
  private static ArrayList<PackageInfo> getInstalledApps(boolean paramBoolean, Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getApplicationContext().getPackageManager().getInstalledPackages(0);
    int i = 0;
    while (i < paramContext.size())
    {
      localArrayList.add((PackageInfo)paramContext.get(i));
      i += 1;
    }
    return localArrayList;
  }
  
  public static HashMap<String, PackageInfo> getInstalledApps(boolean paramBoolean1, boolean paramBoolean2, Context paramContext)
  {
    HashMap localHashMap = new HashMap();
    paramContext = paramContext.getApplicationContext().getPackageManager();
    List localList = paramContext.getInstalledPackages(0);
    int i = 0;
    if (i < localList.size())
    {
      PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
      if (paramBoolean2) {
        localHashMap.put("" + localPackageInfo.applicationInfo.loadLabel(paramContext), localPackageInfo);
      }
      for (;;)
      {
        i += 1;
        break;
        localHashMap.put(localPackageInfo.packageName, localPackageInfo);
      }
    }
    return localHashMap;
  }
  
  public ArrayList<PackageInfo> getPackages(Context paramContext)
  {
    return getInstalledApps(false, paramContext);
  }
}
