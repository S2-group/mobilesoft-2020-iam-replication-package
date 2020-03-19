package com.avl.engine.h;

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

public final class j
{
  private static final byte[] a = { 80, 75, 3, 4 };
  
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
  
  private static boolean a(String paramString)
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
      paramString = h.b(paramString);
      boolean bool = Arrays.equals(a, paramString);
      return bool;
    }
    catch (IOException paramString) {}
    return false;
  }
  
  public static PackageInfo b(PackageManager paramPackageManager, String paramString, int paramInt)
  {
    for (;;)
    {
      try
      {
        if (a(paramString))
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
