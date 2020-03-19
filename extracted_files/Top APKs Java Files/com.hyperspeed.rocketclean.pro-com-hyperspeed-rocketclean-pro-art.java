package com.hyperspeed.rocketclean.pro;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public final class art
{
  private static final byte[] m = { 80, 75, 3, 4 };
  
  private art() {}
  
  public static ApplicationInfo m(Context paramContext, String paramString)
  {
    return m(paramContext.getPackageManager(), paramString);
  }
  
  private static ApplicationInfo m(PackageManager paramPackageManager, String paramString)
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
        aqs.m("PackageUtil", "getApplicationInfo", paramPackageManager);
        paramPackageManager = localObject;
      }
    }
    finally {}
  }
  
  public static PackageInfo m(PackageManager paramPackageManager, String paramString, int paramInt)
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
        aqs.m("PackageUtil", "getPackageInfo", paramPackageManager);
        paramPackageManager = localObject;
      }
    }
    finally {}
  }
  
  public static String m(PackageManager paramPackageManager, ApplicationInfo paramApplicationInfo)
  {
    try
    {
      paramPackageManager = paramApplicationInfo.loadLabel(paramPackageManager).toString();
      return paramPackageManager;
    }
    finally {}
  }
  
  public static List m(PackageManager paramPackageManager)
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
        aqs.m("PackageUtil", "getInstalledPkgInfoList", paramPackageManager);
        paramPackageManager = localArrayList;
      }
    }
    finally {}
  }
  
  private static boolean m(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {}
    do
    {
      return false;
      if (paramString.toLowerCase(Locale.US).endsWith(".apk")) {
        return true;
      }
      paramString = new File(paramString);
    } while (paramString.length() <= 4L);
    try
    {
      paramString = arr.n(paramString);
      boolean bool = Arrays.equals(m, paramString);
      return bool;
    }
    catch (IOException paramString) {}
    return false;
  }
  
  public static PackageInfo n(PackageManager paramPackageManager, String paramString, int paramInt)
  {
    for (;;)
    {
      try
      {
        if (m(paramString))
        {
          paramPackageManager = paramPackageManager.getPackageArchiveInfo(paramString, paramInt);
          return paramPackageManager;
        }
      }
      finally {}
      paramPackageManager = null;
    }
  }
}
