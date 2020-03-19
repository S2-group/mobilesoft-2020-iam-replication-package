package com.zillow.android.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import java.util.Iterator;
import java.util.List;

public class PackageUtil
{
  public PackageUtil() {}
  
  public static boolean activityExistsForIntent(Context paramContext, Intent paramIntent)
  {
    if ((paramContext == null) || (paramIntent == null)) {}
    while (paramContext.getPackageManager().queryIntentActivities(paramIntent, 0).isEmpty()) {
      return false;
    }
    return true;
  }
  
  public static boolean packageExists(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (paramString == null)) {}
    do
    {
      while (!paramContext.hasNext())
      {
        return false;
        paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
      }
    } while (!((PackageInfo)paramContext.next()).packageName.equals(paramString));
    return true;
  }
  
  public static boolean zillowActivityExistsForIntent(Context paramContext, Intent paramIntent)
  {
    if ((paramContext == null) || (paramIntent == null)) {}
    String str;
    do
    {
      while (!paramContext.hasNext())
      {
        return false;
        str = paramContext.getPackageName();
        paramContext = paramContext.getPackageManager().queryIntentActivities(paramIntent, 0).iterator();
      }
    } while (!((ResolveInfo)paramContext.next()).activityInfo.packageName.contains(str));
    return true;
  }
}
