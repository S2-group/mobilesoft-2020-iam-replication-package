package com.sense360.android.quinoa.lib.components.installedapps;

import android.annotation.TargetApi;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppsInfoLoader
{
  private InfoLevel infoLevel;
  private PackageManager packageManager;
  
  public AppsInfoLoader(PackageManager paramPackageManager, InfoLevel paramInfoLevel)
  {
    this.packageManager = paramPackageManager;
    this.infoLevel = paramInfoLevel;
  }
  
  private AppInfo convert(PackageInfo paramPackageInfo)
  {
    AppInfo localAppInfo = new AppInfo(paramPackageInfo.firstInstallTime, paramPackageInfo.lastUpdateTime, paramPackageInfo.packageName, paramPackageInfo.versionCode, paramPackageInfo.versionName, paramPackageInfo.applicationInfo.processName, paramPackageInfo.applicationInfo.targetSdkVersion);
    if (Build.VERSION.SDK_INT >= 21) {
      localAppInfo.setInstallLocation(Integer.valueOf(paramPackageInfo.installLocation));
    }
    if (Build.VERSION.SDK_INT >= 24) {
      localAppInfo.setMinSdkVersion(Integer.valueOf(paramPackageInfo.applicationInfo.minSdkVersion));
    }
    if (this.infoLevel == InfoLevel.VERBOSE)
    {
      localAppInfo.setPermissions(getPermissions(paramPackageInfo));
      localAppInfo.setPermission(paramPackageInfo.applicationInfo.permission);
      if (Build.VERSION.SDK_INT >= 16) {
        localAppInfo.setRequestedPermissions(getRequestedPermissions(paramPackageInfo));
      }
    }
    return localAppInfo;
  }
  
  private List<PermissionInfo> getPermissions(PackageInfo paramPackageInfo)
  {
    paramPackageInfo = paramPackageInfo.permissions;
    if (paramPackageInfo == null) {
      return null;
    }
    ArrayList localArrayList = new ArrayList();
    int j = paramPackageInfo.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramPackageInfo[i];
      PermissionInfo localPermissionInfo = new PermissionInfo(localObject.name);
      localPermissionInfo.setProtectionLevel(Integer.valueOf(localObject.protectionLevel));
      localArrayList.add(localPermissionInfo);
      i += 1;
    }
    return localArrayList;
  }
  
  @TargetApi(16)
  private List<PermissionInfo> getRequestedPermissions(PackageInfo paramPackageInfo)
  {
    String[] arrayOfString = paramPackageInfo.requestedPermissions;
    paramPackageInfo = paramPackageInfo.requestedPermissionsFlags;
    if ((arrayOfString != null) && (paramPackageInfo != null) && (arrayOfString.length == paramPackageInfo.length))
    {
      ArrayList localArrayList = new ArrayList();
      int i = 0;
      while (i < arrayOfString.length)
      {
        PermissionInfo localPermissionInfo = new PermissionInfo(arrayOfString[i]);
        localPermissionInfo.setFlag(Integer.valueOf(paramPackageInfo[i]));
        localArrayList.add(localPermissionInfo);
        i += 1;
      }
      return localArrayList;
    }
    return null;
  }
  
  InfoLevel getInfoLevel()
  {
    return this.infoLevel;
  }
  
  public List<AppInfo> getInstalledApps()
  {
    Object localObject = this.packageManager.getInstalledPackages(4224);
    ArrayList localArrayList = new ArrayList();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      localArrayList.add(convert((PackageInfo)((Iterator)localObject).next()));
    }
    return localArrayList;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("AppsInfoLoader{packageManager=");
    localStringBuilder.append(this.packageManager);
    localStringBuilder.append(", infoLevel=");
    localStringBuilder.append(this.infoLevel);
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
}
