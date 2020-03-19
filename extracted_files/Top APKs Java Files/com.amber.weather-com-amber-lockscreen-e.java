package com.amber.lockscreen;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.Method;
import java.util.List;

public class e
{
  public static String a = "LockScreenSetting";
  
  public static void a(Context paramContext)
  {
    d.a(paramContext, "true");
    new Thread(new Runnable()
    {
      public void run()
      {
        PackageManager localPackageManager = this.a.getPackageManager();
        List localList = localPackageManager.getInstalledPackages(8192);
        int i = 0;
        for (;;)
        {
          if (i >= localList.size()) {
            return;
          }
          PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
          Object localObject = null;
          try
          {
            ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo(localPackageInfo.packageName, 128);
            localObject = localApplicationInfo;
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            for (;;)
            {
              localNameNotFoundException.printStackTrace();
            }
          }
          e.a(localPackageInfo, localObject, this.a);
          i += 1;
        }
      }
    }).start();
  }
  
  private static void b(PackageInfo paramPackageInfo, ApplicationInfo paramApplicationInfo, Context paramContext)
  {
    if (paramApplicationInfo != null)
    {
      paramApplicationInfo = paramApplicationInfo.metaData;
      if ((paramApplicationInfo == null) || (TextUtils.isEmpty(paramApplicationInfo.getString("LOCK_SCREEN"))) || (paramPackageInfo.packageName.equals(paramContext.getPackageName()))) {}
    }
    try
    {
      paramApplicationInfo = paramContext.createPackageContext(paramPackageInfo.packageName, 3);
      boolean bool = d.b(paramApplicationInfo);
      Log.d(a, paramApplicationInfo.getPackageName() + ": " + bool);
      if (bool)
      {
        d.b(paramContext, paramPackageInfo.packageName);
        d.a(paramApplicationInfo, "false");
      }
      return;
    }
    catch (PackageManager.NameNotFoundException paramPackageInfo)
    {
      paramPackageInfo.printStackTrace();
    }
  }
  
  public static boolean b(Context paramContext)
  {
    if (Build.VERSION.SDK_INT < 21) {
      return true;
    }
    try
    {
      boolean bool = Settings.canDrawOverlays(paramContext);
      return bool;
    }
    catch (NoSuchMethodError localNoSuchMethodError) {}
    return c(paramContext);
  }
  
  @TargetApi(19)
  public static boolean c(Context paramContext)
  {
    try
    {
      AppOpsManager localAppOpsManager = (AppOpsManager)paramContext.getSystemService("appops");
      int i = ((Integer)AppOpsManager.class.getMethod("checkOp", new Class[] { Integer.TYPE, Integer.TYPE, String.class }).invoke(localAppOpsManager, new Object[] { Integer.valueOf(24), Integer.valueOf(Binder.getCallingUid()), paramContext.getApplicationContext().getPackageName() })).intValue();
      return i == 0;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static void d(Context paramContext)
  {
    d.a(paramContext, "false");
    Object localObject = d.c(paramContext);
    if (!TextUtils.isEmpty((CharSequence)localObject)) {}
    try
    {
      localObject = paramContext.createPackageContext((String)localObject, 3);
      boolean bool = d.b((Context)localObject);
      Log.d(a, ((Context)localObject).getPackageName() + ": " + bool);
      d.a((Context)localObject, "true");
      d.b(paramContext, "");
      return;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
  }
}
