package com.applift.playads.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import com.applift.playads.model.InstalledApp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InstalledAppLister
{
  private static final String[] IGNORED_PACKAGES = { "com.google", "com.android", "android", "com.samsung", "com.sec.android", "com.sony", "com.htc" };
  
  private InstalledAppLister() {}
  
  public static boolean isInstalled(Context paramContext, String paramString)
    throws Exception
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  private static boolean isSystemPackage(String paramString)
  {
    String[] arrayOfString = IGNORED_PACKAGES;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      if (paramString.startsWith(arrayOfString[i])) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  @TargetApi(9)
  public static List<InstalledApp> listInstalledApps(Context paramContext)
    throws Exception
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    while (paramContext.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramContext.next();
      if (!isSystemPackage(localPackageInfo.packageName))
      {
        long l1 = -1L;
        long l2 = -1L;
        if (Build.VERSION.SDK_INT >= 9)
        {
          l1 = localPackageInfo.firstInstallTime;
          l2 = localPackageInfo.lastUpdateTime;
        }
        localArrayList.add(new InstalledApp(localPackageInfo.packageName, localPackageInfo.versionName, l1, l2));
      }
    }
    return localArrayList;
  }
}
