package com.avast.android.appinfo.c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a
{
  public static int a(ApplicationInfo paramApplicationInfo)
  {
    return paramApplicationInfo.targetSdkVersion;
  }
  
  public static com.avast.android.appinfo.a a(Context paramContext, String paramString)
  {
    try
    {
      paramContext = paramContext.getPackageManager();
      String str = paramContext.getInstallerPackageName(paramString);
      paramContext = a(paramContext.getApplicationInfo(paramString, 0), str);
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return com.avast.android.appinfo.a.UNKNOWN;
  }
  
  private static com.avast.android.appinfo.a a(ApplicationInfo paramApplicationInfo, String paramString)
  {
    if ((paramApplicationInfo.flags & 0x1) > 0) {
      return com.avast.android.appinfo.a.PREINSTALLED;
    }
    if ("com.android.vending".equals(paramString)) {
      return com.avast.android.appinfo.a.GOOGLE_PLAY;
    }
    return com.avast.android.appinfo.a.DOWNLOADED;
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
    return c.a(c.a(c.a.SHA256, new File(paramString1), 64));
  }
  
  public static boolean b(Context paramContext, String paramString)
  {
    return a(paramContext, paramString) == com.avast.android.appinfo.a.PREINSTALLED;
  }
  
  public static int c(Context paramContext, String paramString)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramString, 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return 0;
  }
  
  public static long d(Context paramContext, String paramString)
  {
    try
    {
      long l = new File(paramContext.getPackageManager().getPackageInfo(paramString, 0).applicationInfo.publicSourceDir).length();
      return l;
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return 0L;
  }
  
  public static int e(Context paramContext, String paramString)
    throws PackageManager.NameNotFoundException
  {
    return a(paramContext.getPackageManager().getPackageInfo(paramString, 0).applicationInfo);
  }
}
