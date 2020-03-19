package com.jkfantasy.camera.jkpmirrorcamera.e;

import android.app.Activity;
import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;

public class h
{
  public static boolean a(Activity paramActivity)
  {
    paramActivity = paramActivity.getApplication().getPackageManager().getInstalledPackages(8192).iterator();
    while (paramActivity.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)paramActivity.next();
      if ((localPackageInfo.packageName.equals("com.google.market")) || (localPackageInfo.packageName.equals("com.android.vending"))) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean b(Activity paramActivity)
  {
    paramActivity = paramActivity.getApplication().getPackageManager().getInstalledPackages(8192).iterator();
    while (paramActivity.hasNext()) {
      if (((PackageInfo)paramActivity.next()).packageName.equals("com.jkfantasy.photopoi")) {
        return true;
      }
    }
    return false;
  }
}
