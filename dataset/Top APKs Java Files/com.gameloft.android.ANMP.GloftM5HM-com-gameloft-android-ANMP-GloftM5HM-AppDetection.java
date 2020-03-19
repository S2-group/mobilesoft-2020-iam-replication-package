package com.gameloft.android.ANMP.GloftM5HM;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.List;

public class AppDetection
{
  private static Context a;
  private static List<String> b;
  
  public AppDetection() {}
  
  private static List<ApplicationInfo> getApplications()
  {
    if (a != null) {
      return a.getPackageManager().getInstalledApplications(128);
    }
    return new ArrayList();
  }
  
  public static ArrayList<String> getGameloftPackages()
  {
    List localList = getPackages();
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < localList.size())
    {
      Object localObject = (PackageInfo)localList.get(i);
      if (((PackageInfo)localObject).versionName != null)
      {
        localObject = ((PackageInfo)localObject).packageName;
        if (((String)localObject).startsWith("com.gameloft")) {
          localArrayList.add(localObject);
        }
      }
      i += 1;
    }
    return localArrayList;
  }
  
  public static String[] getInstalledPackages()
  {
    if (b == null) {
      b = getNonSystemPackageNames();
    }
    return (String[])b.toArray(new String[b.size()]);
  }
  
  public static ArrayList<String> getNonSystemPackageNames()
  {
    List localList = getApplications();
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if (i < localList.size())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)localList.get(i);
      if ((localApplicationInfo.flags & 0x1) != 0) {}
      for (;;)
      {
        i += 1;
        break;
        localArrayList.add(localApplicationInfo.packageName);
      }
    }
    return localArrayList;
  }
  
  private static List<PackageInfo> getPackages()
  {
    if (a != null) {
      return a.getPackageManager().getInstalledPackages(0);
    }
    return new ArrayList();
  }
  
  public static void init(Context paramContext)
  {
    a = paramContext;
  }
  
  public static boolean isAppInstalled(String paramString)
  {
    if (b == null) {
      b = getNonSystemPackageNames();
    }
    return b.contains(paramString);
  }
}
