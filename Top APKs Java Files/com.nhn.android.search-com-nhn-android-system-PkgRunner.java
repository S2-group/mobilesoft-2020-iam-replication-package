package com.nhn.android.system;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;

public class PkgRunner
{
  public PkgRunner() {}
  
  public static boolean isInstalled(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString) == true) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean startPackage(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (paramString != null) {
      try
      {
        Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage(paramString);
        if (localIntent != null)
        {
          paramContext.startActivity(localIntent);
          return true;
        }
        if ((paramBoolean == true) && (!isInstalled(paramContext, paramString)))
        {
          paramContext.startActivity(Intent.parseUri("market://details?id=" + paramString, 268435456));
          return true;
        }
      }
      catch (Exception paramContext) {}
    }
    return false;
  }
}
