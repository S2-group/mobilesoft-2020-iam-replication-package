package com.clap.find.my.mobile.alarm.sound.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import java.util.Iterator;
import java.util.List;

public final class AutoStartPermissionHelper
{
  private static AutoStartPermissionHelper instance;
  private final String BRAND_HONOR = "honor";
  private final String BRAND_LETV = "letv";
  private final String BRAND_OPPO = "oppo";
  private final String BRAND_VIVO = "vivo";
  private final String BRAND_XIAOMI = "xiaomi";
  private final String PACKAGE_HONOR_COMPONENT = "com.huawei.systemmanager.optimize.process.ProtectActivity";
  private final String PACKAGE_HONOR_MAIN = "com.huawei.systemmanager";
  private final String PACKAGE_LETV_COMPONENT = "com.letv.android.letvsafe.AutobootManageActivity";
  private final String PACKAGE_LETV_MAIN = "com.letv.android.letvsafe";
  private final String PACKAGE_OPPO_COMPONENT = "com.coloros.safecenter.permission.startup.StartupAppListActivity";
  private final String PACKAGE_OPPO_COMPONENT_FALLBACK = "com.oppo.safe.permission.startup.StartupAppListActivity";
  private final String PACKAGE_OPPO_COMPONENT_FALLBACK_A = "com.coloros.safecenter.startupapp.StartupAppListActivity";
  private final String PACKAGE_OPPO_FALLBACK = "com.oppo.safe";
  private final String PACKAGE_OPPO_MAIN = "com.coloros.safecenter";
  private final String PACKAGE_VIVO_COMPONENT = "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity";
  private final String PACKAGE_VIVO_COMPONENT_FALLBACK = "com.vivo.permissionmanager.activity.BgStartUpManagerActivity";
  private final String PACKAGE_VIVO_COMPONENT_FALLBACK_A = "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager";
  private final String PACKAGE_VIVO_FALLBACK = "com.vivo.permissionmanager";
  private final String PACKAGE_VIVO_MAIN = "com.iqoo.secure";
  private final String PACKAGE_XIAOMI_COMPONENT = "com.miui.permcenter.autostart.AutoStartManagementActivity";
  private final String PACKAGE_XIAOMI_MAIN = "com.miui.securitycenter";
  
  private AutoStartPermissionHelper() {}
  
  private void autoStartHonor(Context paramContext)
  {
    if (isPackageExists(paramContext, "com.huawei.systemmanager")) {}
    try
    {
      startIntent(paramContext, "com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity");
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private void autoStartLetv(Context paramContext)
  {
    if (isPackageExists(paramContext, "com.letv.android.letvsafe")) {}
    try
    {
      startIntent(paramContext, "com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity");
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private void autoStartOppo(Context paramContext)
  {
    if ((Build.MANUFACTURER.equalsIgnoreCase("oppo")) && ((isPackageExists(paramContext, "com.coloros.safecenter")) || (isPackageExists(paramContext, "com.oppo.safe")))) {}
    try
    {
      startIntent(paramContext, "com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity");
      return;
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
      try
      {
        startIntent(paramContext, "com.oppo.safe", "com.oppo.safe.permission.startup.StartupAppListActivity");
        return;
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
        try
        {
          startIntent(paramContext, "com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity");
          return;
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
        }
      }
    }
  }
  
  private void autoStartVivo(Context paramContext)
  {
    if ((isPackageExists(paramContext, "com.iqoo.secure")) || (isPackageExists(paramContext, "com.vivo.permissionmanager"))) {}
    try
    {
      startIntent(paramContext, "com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity");
      return;
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
      try
      {
        startIntent(paramContext, "com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity");
        return;
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
        try
        {
          startIntent(paramContext, "com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager");
          return;
        }
        catch (Exception paramContext)
        {
          paramContext.printStackTrace();
        }
      }
    }
  }
  
  private void autoStartXiaomi(Context paramContext)
  {
    if (isPackageExists(paramContext, "com.miui.securitycenter")) {}
    try
    {
      startIntent(paramContext, "com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity");
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static AutoStartPermissionHelper getInstance()
  {
    if (instance == null) {}
    try
    {
      if (instance == null) {
        instance = new AutoStartPermissionHelper();
      }
      return instance;
    }
    finally {}
  }
  
  private static boolean isPackageExists(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (paramContext.hasNext()) {
      if (((ApplicationInfo)paramContext.next()).packageName.equals(paramString)) {
        return true;
      }
    }
    return false;
  }
  
  private void startIntent(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      Intent localIntent = new Intent();
      localIntent.setComponent(new ComponentName(paramString1, paramString2));
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      throw paramContext;
    }
  }
  
  public void getAutoStartPermission(Context paramContext)
  {
    String str = Build.BRAND.toLowerCase();
    int i = -1;
    switch (str.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return;
        if (str.equals("xiaomi"))
        {
          i = 0;
          continue;
          if (str.equals("letv"))
          {
            i = 1;
            continue;
            if (str.equals("honor"))
            {
              i = 2;
              continue;
              if (str.equals("oppo"))
              {
                i = 3;
                continue;
                if (str.equals("vivo")) {
                  i = 4;
                }
              }
            }
          }
        }
        break;
      }
    }
    autoStartXiaomi(paramContext);
    return;
    autoStartLetv(paramContext);
    return;
    autoStartHonor(paramContext);
    return;
    autoStartOppo(paramContext);
    return;
    autoStartVivo(paramContext);
  }
}
