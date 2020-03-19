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
    try
    {
      try
      {
        localResources = paramContext.getResources();
      }
      catch (Exception localException1)
      {
        try
        {
          Resources localResources;
          Object localObject;
          while (!localResources.getResourceName(i).contains("string/resource_for_package_identification")) {}
          str = localResources.getResourcePackageName(i);
          b = str;
          return str;
        }
        catch (Exception localException3) {}
        localException1 = localException1;
        AppboyLogger.e(a, "General exception getting resource package name, returning Context#getPackageName().", localException1);
      }
      catch (NullPointerException localNullPointerException)
      {
        label29:
        AppboyLogger.e(a, "Got null pointer exception getting resource package name, returning Context#getPackageName().", localNullPointerException);
      }
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      for (;;) {}
    }
    try
    {
      localObject = localResources.getResourcePackageName(R.string.resource_for_package_identification);
      b = (String)localObject;
      return localObject;
    }
    catch (Exception localException2)
    {
      break label29;
      break label43;
    }
    localObject = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    label43:
    while (((Iterator)localObject).hasNext())
    {
      int i = localResources.getIdentifier("string/resource_for_package_identification", null, ((PackageInfo)((Iterator)localObject).next()).packageName);
      if (i != 0)
      {
        String str;
        AppboyLogger.i(a, "Could not find resource for package identification, returning Context#getPackageName().");
      }
    }
    paramContext = paramContext.getPackageName();
    b = paramContext;
    return paramContext;
  }
}
