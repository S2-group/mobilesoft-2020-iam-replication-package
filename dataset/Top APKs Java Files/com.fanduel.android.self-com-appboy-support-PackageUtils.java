package com.appboy.support;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import com.appboy.Constants;
import com.appboy.R.string;
import java.util.Iterator;
import java.util.List;

public class PackageUtils
{
  private static final String a = String.format("%s.%s", new Object[] { Constants.APPBOY_LOG_TAG_PREFIX, PackageUtils.class.getName() });
  private static String b;
  
  public PackageUtils() {}
  
  public static String getResourcePackageName(Context paramContext)
  {
    if (b != null) {
      return b;
    }
    do
    {
      try
      {
        localResources = paramContext.getResources();
        try
        {
          b = localResources.getResourcePackageName(R.string.resource_for_package_identification);
          String str1 = b;
          return str1;
        }
        catch (Exception localException2)
        {
          localIterator = paramContext.getPackageManager().getInstalledPackages(0).iterator();
        }
      }
      catch (Resources.NotFoundException localNotFoundException)
      {
        try
        {
          Resources localResources;
          Iterator localIterator;
          int i;
          while (!localResources.getResourceName(i).contains("string/resource_for_package_identification")) {}
          b = localResources.getResourcePackageName(i);
          String str2 = b;
          return str2;
        }
        catch (Exception localException3) {}
        localNotFoundException = localNotFoundException;
        AppboyLogger.i(a, "Could not find resource for package identification, returning Context#getPackageName().");
        b = paramContext.getPackageName();
        return b;
      }
      catch (NullPointerException localNullPointerException)
      {
        for (;;)
        {
          AppboyLogger.e(a, "Got null pointer exception getting resource package name, returning Context#getPackageName().", localNullPointerException);
        }
      }
      catch (Exception localException1)
      {
        for (;;)
        {
          AppboyLogger.e(a, "General exception getting resource package name, returning Context#getPackageName().", localException1);
        }
      }
      if (!localIterator.hasNext()) {
        break;
      }
      i = localResources.getIdentifier("string/resource_for_package_identification", null, ((PackageInfo)localIterator.next()).packageName);
    } while (i == 0);
  }
  
  public static void setResourcePackageName(String paramString)
  {
    if (!StringUtils.isNullOrBlank(paramString))
    {
      b = paramString;
      return;
    }
    AppboyLogger.e(a, "Package name may not be null or blank");
  }
}
