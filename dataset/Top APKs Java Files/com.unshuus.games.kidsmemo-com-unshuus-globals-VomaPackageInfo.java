package com.unshuus.globals;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VomaPackageInfo
{
  public String AppName = "";
  public String PackageName = "";
  public int VersionCode = 0;
  public String VersionName = "";
  
  public VomaPackageInfo(Context paramContext)
  {
    try
    {
      PackageManager localPackageManager = paramContext.getPackageManager();
      this.PackageName = paramContext.getPackageName();
      paramContext = localPackageManager.getPackageInfo(this.PackageName, 0);
      this.VersionName = paramContext.versionName;
      this.VersionCode = paramContext.versionCode;
      this.AppName = paramContext.applicationInfo.loadLabel(localPackageManager).toString();
      return;
    }
    catch (Exception paramContext)
    {
      VomaDebugContext.LogE();
    }
  }
  
  public VomaPackageInfo(PackageManager paramPackageManager, PackageInfo paramPackageInfo)
  {
    this.AppName = paramPackageInfo.applicationInfo.loadLabel(paramPackageManager).toString();
    this.PackageName = paramPackageInfo.packageName;
    this.VersionName = paramPackageInfo.versionName;
    this.VersionCode = paramPackageInfo.versionCode;
  }
  
  public static ArrayList<VomaPackageInfo> getInstalledApps(Context paramContext, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    paramContext = paramContext.getPackageManager();
    Iterator localIterator = paramContext.getInstalledPackages(0).iterator();
    while (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      if ((paramBoolean) || (localPackageInfo.versionName != null)) {
        localArrayList.add(new VomaPackageInfo(paramContext, localPackageInfo));
      }
    }
    return localArrayList;
  }
  
  public static boolean isInstalled(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager();
    boolean bool1 = bool2;
    if (paramContext != null) {}
    try
    {
      paramContext = paramContext.getApplicationInfo(paramString, 128);
      bool1 = bool2;
      if (paramContext != null)
      {
        boolean bool3 = paramContext.packageName.equalsIgnoreCase(paramString);
        bool1 = bool2;
        if (bool3) {
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public String toString()
  {
    return "APPNAME: " + this.AppName + "\nPACKAGE NAME: " + this.PackageName + "\nVERSION CODE: " + this.VersionCode + "\nVERSION NAME: " + this.VersionName;
  }
}
