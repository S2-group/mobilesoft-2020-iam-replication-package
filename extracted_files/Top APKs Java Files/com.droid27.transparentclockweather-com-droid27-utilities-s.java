package com.droid27.utilities;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public final class s
{
  public static Drawable a(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getResourcesForApplication(paramString2);
      if (paramContext == null) {
        return null;
      }
      paramContext = paramContext.getDrawable(paramContext.getIdentifier(paramString1, "drawable", paramString2));
      return paramContext;
    }
    catch (Exception paramContext) {}
    return null;
  }
  
  public static String a(Resources paramResources, String paramString1, String paramString2, String paramString3)
  {
    try
    {
      paramResources = paramResources.getString(paramResources.getIdentifier(paramString1, paramString2, paramString3));
      return paramResources;
    }
    catch (Exception paramResources) {}
    return "";
  }
  
  public static boolean a(Context paramContext)
  {
    PackageManager localPackageManager;
    if (Build.VERSION.SDK_INT > 7) {
      localPackageManager = paramContext.getPackageManager();
    }
    for (;;)
    {
      try
      {
        int i = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).applicationInfo.flags;
        return (i & 0x40000) == 262144;
      }
      catch (Exception localException) {}
      try
      {
        paramContext = paramContext.getFilesDir().getAbsolutePath();
        if (paramContext.startsWith("/data/")) {
          return false;
        }
        if (!paramContext.contains("/mnt/"))
        {
          boolean bool = paramContext.contains("/sdcard/");
          if (bool) {}
        }
      }
      catch (Throwable paramContext)
      {
        for (;;) {}
      }
    }
    return false;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  public static int b(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getResourcesForApplication(paramString2);
      if (paramContext == null) {
        return 0;
      }
      int i = paramContext.getIdentifier(paramString1, "drawable", paramString2);
      return i;
    }
    catch (Exception paramContext) {}
    return 0;
  }
  
  public static String[] b(Resources paramResources, String paramString1, String paramString2, String paramString3)
  {
    try
    {
      paramResources = paramResources.getStringArray(paramResources.getIdentifier(paramString1, paramString2, paramString3));
      return paramResources;
    }
    catch (Exception paramResources) {}
    return tmp19_16;
  }
}
