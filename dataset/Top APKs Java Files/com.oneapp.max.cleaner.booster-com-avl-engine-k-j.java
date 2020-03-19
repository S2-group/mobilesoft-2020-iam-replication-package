package com.avl.engine.k;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.ArrayList;
import java.util.List;

public final class j
{
  private j() {}
  
  public static ApplicationInfo a(Context paramContext, String paramString)
  {
    return a(paramContext.getPackageManager(), paramString);
  }
  
  private static ApplicationInfo a(PackageManager paramPackageManager, String paramString)
  {
    Object localObject = null;
    try
    {
      paramPackageManager = paramPackageManager.getApplicationInfo(paramString, 128);
      return paramPackageManager;
    }
    catch (PackageManager.NameNotFoundException paramPackageManager)
    {
      for (;;)
      {
        a.a("PackageUtil", "getApplicationInfo", paramPackageManager);
        paramPackageManager = localObject;
      }
    }
    finally {}
  }
  
  public static PackageInfo a(PackageManager paramPackageManager, String paramString, int paramInt)
  {
    Object localObject = null;
    try
    {
      paramPackageManager = paramPackageManager.getPackageInfo(paramString, paramInt);
      return paramPackageManager;
    }
    catch (PackageManager.NameNotFoundException paramPackageManager)
    {
      for (;;)
      {
        a.a("PackageUtil", "getPackageInfo", paramPackageManager);
        paramPackageManager = localObject;
      }
    }
    finally {}
  }
  
  public static String a(PackageManager paramPackageManager, ApplicationInfo paramApplicationInfo)
  {
    try
    {
      paramPackageManager = paramApplicationInfo.loadLabel(paramPackageManager).toString();
      return paramPackageManager;
    }
    finally {}
  }
  
  public static List a(PackageManager paramPackageManager)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      paramPackageManager = paramPackageManager.getInstalledPackages(0);
      return paramPackageManager;
    }
    catch (RuntimeException paramPackageManager)
    {
      for (;;)
      {
        a.a("PackageUtil", "getInstalledPkgInfoList", paramPackageManager);
        paramPackageManager = localArrayList;
      }
    }
    finally {}
  }
  
  public static PackageInfo b(PackageManager paramPackageManager, String paramString, int paramInt)
  {
    try
    {
      paramPackageManager = paramPackageManager.getPackageArchiveInfo(paramString, paramInt);
      return paramPackageManager;
    }
    finally {}
  }
}
