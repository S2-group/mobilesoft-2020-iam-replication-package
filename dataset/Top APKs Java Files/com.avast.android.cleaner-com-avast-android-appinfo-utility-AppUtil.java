package com.avast.android.appinfo.utility;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import com.avast.android.appinfo.AppInfo;
import com.avast.android.appinfo.ApplicationSource;
import com.avast.android.logging.Alf;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"WrongConstant"})
public class AppUtil
{
  public static int a(ApplicationInfo paramApplicationInfo)
  {
    return paramApplicationInfo.targetSdkVersion;
  }
  
  public static ApplicationSource a(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager();
      String str = paramContext.getInstallerPackageName(paramString);
      paramContext = a(paramContext.getApplicationInfo(paramString, 0), str);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      return ApplicationSource.UNKNOWN;
    }
    catch (IllegalArgumentException paramContext)
    {
      if (!("Unknown package: " + paramString).equals(paramContext.getMessage())) {
        AppInfo.Logger.e(paramContext, "Unexpected IAE during app source check", new Object[0]);
      }
    }
    return ApplicationSource.UNKNOWN;
  }
  
  private static ApplicationSource a(ApplicationInfo paramApplicationInfo, String paramString)
  {
    if ((paramApplicationInfo.flags & 0x1) > 0) {
      return ApplicationSource.PREINSTALLED;
    }
    if ("com.android.vending".equals(paramString)) {
      return ApplicationSource.GOOGLE_PLAY;
    }
    return ApplicationSource.DOWNLOADED;
  }
  
  public static List<String> a(Context paramContext)
  {
    if (paramContext == null) {}
    do
    {
      return null;
      localObject = paramContext.getPackageManager();
    } while (localObject == null);
    paramContext = new ArrayList();
    Object localObject = ((PackageManager)localObject).getInstalledPackages(8192);
    if ((localObject != null) && (!((List)localObject).isEmpty()))
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        paramContext.add(((PackageInfo)((Iterator)localObject).next()).packageName);
      }
    }
    return paramContext;
  }
  
  public static byte[] a(String paramString1, String paramString2)
    throws NoSuchAlgorithmException
  {
    return HashManager.a(HashManager.a(HashManager.HashAlgorithm.SHA256, new File(paramString1), 64));
  }
  
  public static int b(Context paramContext, String paramString)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramString, 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return 0;
  }
  
  public static long c(Context paramContext, String paramString)
  {
    try
    {
      long l = new File(paramContext.getPackageManager().getPackageInfo(paramString, 0).applicationInfo.publicSourceDir).length();
      return l;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return 0L;
  }
  
  public static int d(Context paramContext, String paramString)
    throws PackageManager.NameNotFoundException
  {
    return a(paramContext.getPackageManager().getPackageInfo(paramString, 0).applicationInfo);
  }
  
  public static boolean e(Context paramContext, String paramString)
  {
    return paramContext.getPackageManager().getLaunchIntentForPackage(paramString) != null;
  }
}
