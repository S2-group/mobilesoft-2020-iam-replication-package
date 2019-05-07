package com.bana.dating.lib.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.os.Build;
import android.os.Build.VERSION;
import com.bana.dating.lib.R.string;
import java.util.ArrayList;
import java.util.List;

public class PackageUtils
{
  public PackageUtils() {}
  
  public static String getUserAgent(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramContext.getResources().getString(R.string.mason_app_name)).append("/").append(getVersionName(paramContext)).append("(").append(Build.MODEL).append(";").append("Android " + Build.VERSION.RELEASE).append(";").append(10002).append(";").append("2").append(")");
    return localStringBuilder.toString().replaceAll("\n", " ");
  }
  
  public static int getVersionCode(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }
  
  public static String getVersionName(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "1.0";
  }
  
  public static boolean isPackageAvilible(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    int j = paramContext.size();
    if (paramContext != null)
    {
      int i = 0;
      while (i < j)
      {
        localArrayList.add(((PackageInfo)paramContext.get(i)).packageName);
        i += 1;
      }
    }
    return localArrayList.contains(paramString);
  }
}
