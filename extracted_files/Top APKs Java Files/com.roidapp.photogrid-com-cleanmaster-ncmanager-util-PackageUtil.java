package com.cleanmaster.ncmanager.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.provider.Settings.Secure;
import java.util.List;

public class PackageUtil
{
  public PackageUtil() {}
  
  public static Intent getAppIntentWithPackageName(Context paramContext, String paramString)
  {
    Object localObject = paramContext.getPackageManager();
    try
    {
      paramContext = ((PackageManager)localObject).getPackageInfo(paramString, 0);
      if (paramContext == null) {
        return null;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = null;
      }
    }
    catch (Exception paramContext)
    {
      for (;;)
      {
        paramContext.printStackTrace();
        paramContext = null;
      }
      paramString = new Intent("android.intent.action.MAIN", null);
      paramString.addCategory("android.intent.category.LAUNCHER");
      paramString.setPackage(paramContext.packageName);
      try
      {
        paramContext = ((PackageManager)localObject).queryIntentActivities(paramString, 0);
        if ((paramContext != null) && (!paramContext.isEmpty()))
        {
          paramString = (ResolveInfo)paramContext.get(0);
          if (paramString != null)
          {
            paramContext = paramString.activityInfo.packageName;
            paramString = paramString.activityInfo.name;
            localObject = new Intent("android.intent.action.MAIN");
            ((Intent)localObject).addFlags(268435456);
            ((Intent)localObject).addCategory("android.intent.category.LAUNCHER");
            ((Intent)localObject).setComponent(new ComponentName(paramContext, paramString));
            return localObject;
          }
        }
      }
      catch (Exception paramContext)
      {
        for (;;)
        {
          paramContext.printStackTrace();
          paramContext = null;
        }
      }
    }
    return null;
  }
  
  public static String getDefaultSMSApp(Context paramContext)
  {
    if (paramContext == null) {
      return null;
    }
    try
    {
      paramContext = Settings.Secure.getString(paramContext.getContentResolver(), "sms_default_application");
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static List<PackageInfo> getInstalledPackagesNoThrow(PackageManager paramPackageManager, int paramInt)
  {
    try
    {
      paramPackageManager = paramPackageManager.getInstalledPackages(paramInt);
      return paramPackageManager;
    }
    catch (Exception paramPackageManager) {}
    return null;
  }
}
