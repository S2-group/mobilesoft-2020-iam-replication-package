package com.pingstart.adsdk.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.pingstart.adsdk.exception.ExceptionHandler;
import com.pingstart.adsdk.exception.ExceptionHandlerFactory;
import java.util.ArrayList;
import java.util.List;

public class PackageUtils
{
  public PackageUtils() {}
  
  public static ArrayList<String> getInstalledAppList(Context paramContext)
  {
    localArrayList = new ArrayList();
    if (paramContext != null) {
      try
      {
        paramContext = paramContext.getPackageManager();
        int i = 0;
        paramContext = paramContext.getInstalledPackages(0);
        while (i < paramContext.size())
        {
          PackageInfo localPackageInfo = (PackageInfo)paramContext.get(i);
          if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
            localArrayList.add(localPackageInfo.packageName);
          }
          i += 1;
        }
        return localArrayList;
      }
      catch (Exception paramContext)
      {
        ExceptionHandlerFactory.createExceptionHandler().handleException(paramContext, "");
      }
    }
  }
  
  public static int getVersionCode(Context paramContext)
  {
    if (paramContext != null) {
      try
      {
        int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
        return i;
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        ExceptionHandlerFactory.createExceptionHandler().handleException(paramContext, "");
      }
    }
    return 100;
  }
  
  public static boolean isApkInstalled(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    try
    {
      paramContext.getPackageManager().getApplicationInfo(paramString, 128);
      return true;
    }
    catch (Exception paramContext)
    {
      ExceptionHandlerFactory.createExceptionHandler().handleException(paramContext, "");
    }
    return false;
  }
}
