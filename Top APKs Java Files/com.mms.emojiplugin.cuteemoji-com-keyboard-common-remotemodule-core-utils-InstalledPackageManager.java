package com.keyboard.common.remotemodule.core.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.List;

public class InstalledPackageManager
{
  private static final InstalledPackageManager mIPM = new InstalledPackageManager();
  private List<ApplicationInfo> mApplicationInfoList;
  private List<PackageInfo> mPackageInfoList;
  
  public InstalledPackageManager() {}
  
  public static InstalledPackageManager getInstance()
  {
    return mIPM;
  }
  
  public List<ApplicationInfo> getInstalledApplicationInfoList(Context paramContext)
  {
    try
    {
      if (this.mApplicationInfoList == null)
      {
        this.mApplicationInfoList = new ArrayList();
        this.mApplicationInfoList = paramContext.getPackageManager().getInstalledApplications(128);
      }
      paramContext = this.mApplicationInfoList;
      return paramContext;
    }
    finally {}
  }
  
  public List<PackageInfo> getInstalledPackageInfoList(Context paramContext)
  {
    try
    {
      if (this.mPackageInfoList == null)
      {
        this.mPackageInfoList = new ArrayList();
        this.mPackageInfoList = paramContext.getPackageManager().getInstalledPackages(0);
      }
      paramContext = this.mPackageInfoList;
      return paramContext;
    }
    finally {}
  }
  
  public void release()
  {
    if (this.mApplicationInfoList != null) {
      this.mApplicationInfoList.clear();
    }
    if (this.mPackageInfoList != null) {
      this.mPackageInfoList.clear();
    }
    this.mApplicationInfoList = null;
    this.mPackageInfoList = null;
  }
}
