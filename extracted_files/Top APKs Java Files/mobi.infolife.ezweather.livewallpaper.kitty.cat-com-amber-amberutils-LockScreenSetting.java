package com.amber.amberutils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.List;

public class LockScreenSetting
{
  public static final String LOCK_CONFIG_PASSWORD_TYPE_FOUR_CODE = "lock_config_password_type_four_code";
  public static final String LOCK_CONFIG_PASSWORD_TYPE_PATTERN = "lock_config_password_type_pattern";
  public static final String LOCK_CONFIG_SWITCH_CLOSE = "lock_config_switch_close";
  public static final String LOCK_CONFIG_SWITCH_OPEN = "lock_config_switch_open";
  public static String TAG = "LockScreenSetting";
  
  public LockScreenSetting() {}
  
  public static boolean canDrawOverlayViews(Context paramContext)
  {
    if (Build.VERSION.SDK_INT < 21) {
      return true;
    }
    try
    {
      boolean bool = Settings.canDrawOverlays(paramContext);
      return bool;
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
      for (;;) {}
    }
    return canDrawOverlaysUsingReflection(paramContext);
  }
  
  @TargetApi(19)
  public static boolean canDrawOverlaysUsingReflection(Context paramContext)
  {
    boolean bool = false;
    try
    {
      AppOpsManager localAppOpsManager = (AppOpsManager)paramContext.getSystemService("appops");
      int i = ((Integer)AppOpsManager.class.getMethod("checkOp", new Class[] { Integer.TYPE, Integer.TYPE, String.class }).invoke(localAppOpsManager, new Object[] { Integer.valueOf(24), Integer.valueOf(Binder.getCallingUid()), paramContext.getApplicationContext().getPackageName() })).intValue();
      if (i == 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext) {}
    return false;
  }
  
  public static void checkAndRequestDrawOverlayPermission(Activity paramActivity)
  {
    if ((Build.VERSION.SDK_INT >= 23) && (!canDrawOverlayViews(paramActivity)))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("package:");
      localStringBuilder.append(paramActivity.getPackageName());
      paramActivity.startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse(localStringBuilder.toString())), 0);
    }
  }
  
  public static boolean checkDeviceHasNavigationBar(Context paramContext)
  {
    paramContext = paramContext.getResources();
    int i = paramContext.getIdentifier("config_showNavigationBar", "bool", "android");
    boolean bool1;
    if (i > 0) {
      bool1 = paramContext.getBoolean(i);
    } else {
      bool1 = false;
    }
    try
    {
      paramContext = Class.forName("android.os.SystemProperties");
      paramContext = (String)paramContext.getMethod("get", new Class[] { String.class }).invoke(paramContext, new Object[] { "qemu.hw.mainkeys" });
      if ("1".equals(paramContext)) {
        return false;
      }
      boolean bool2 = "0".equals(paramContext);
      if (bool2) {
        return true;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return bool1;
  }
  
  public static void clearLockConfig(Context paramContext)
  {
    paramContext = new LockSdConfig(paramContext);
    paramContext.savePasswordCodeToSd("");
    paramContext.savePasswordTypeToSd("");
    paramContext.saveVerifyAnswerToSd("");
    paramContext.saveVerifyProblemToSd("");
    paramContext.savePasswordSwitchToSd("");
    paramContext.saveLockerSwitchToSd("lock_config_switch_open");
  }
  
  public static void closeLockScreen(Context paramContext)
  {
    LockScreenPreference.saveLockScreenSwitch(paramContext, "false");
    LockSdConfig.getInstance(paramContext).saveLockerSwitchToSd("lock_config_switch_close");
    Object localObject = LockScreenPreference.getOtherLockScreen(paramContext);
    if (!TextUtils.isEmpty((CharSequence)localObject)) {
      try
      {
        localObject = paramContext.createPackageContext((String)localObject, 3);
        boolean bool = LockScreenPreference.isLockScreenSwitch((Context)localObject);
        String str = TAG;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(((Context)localObject).getPackageName());
        localStringBuilder.append(": ");
        localStringBuilder.append(bool);
        Log.d(str, localStringBuilder.toString());
        LockScreenPreference.saveLockScreenSwitch((Context)localObject, "true");
        LockScreenPreference.saveOtherLockScreen(paramContext, "");
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localNameNotFoundException.printStackTrace();
      }
    }
    if (LockScreenPreference.hasInitSdConfig(paramContext)) {
      LockSdConfig.initSdConfig(paramContext);
    }
  }
  
  public static void closePassword(Context paramContext)
  {
    LockSdConfig.getInstance(paramContext).savePasswordSwitchToSd("lock_config_switch_close");
  }
  
  public static String getMainLocker(Context paramContext)
  {
    return LockSdConfig.getInstance(paramContext).getLockMainPkg();
  }
  
  public static String getPasswordType(Context paramContext)
  {
    return LockSdConfig.getInstance(paramContext).getPasswordType();
  }
  
  public static String getSkinLocker(Context paramContext)
  {
    return LockSdConfig.getInstance(paramContext).getLockSkinPkg();
  }
  
  public static String getVerifyProblem(Context paramContext)
  {
    return LockSdConfig.getInstance(paramContext).getVerifyProblem();
  }
  
  private static void handleLockScreenSwitch(PackageInfo paramPackageInfo, ApplicationInfo paramApplicationInfo, Context paramContext)
  {
    if (paramApplicationInfo != null)
    {
      paramApplicationInfo = paramApplicationInfo.metaData;
      if ((paramApplicationInfo == null) || (TextUtils.isEmpty(paramApplicationInfo.getString("LOCK_SCREEN"))) || (paramPackageInfo.packageName.equals(paramContext.getPackageName()))) {}
    }
    for (;;)
    {
      try
      {
        paramApplicationInfo = paramContext.createPackageContext(paramPackageInfo.packageName, 3);
        if ((paramApplicationInfo == null) || (!LockScreenPreference.isLockScreenSwitch(paramApplicationInfo))) {
          break label91;
        }
        i = 1;
        if (i != 0)
        {
          LockScreenPreference.saveOtherLockScreen(paramContext, paramPackageInfo.packageName);
          LockScreenPreference.saveLockScreenSwitch(paramApplicationInfo, "false");
          return;
        }
      }
      catch (PackageManager.NameNotFoundException paramPackageInfo)
      {
        paramPackageInfo.printStackTrace();
      }
      return;
      label91:
      int i = 0;
    }
  }
  
  public static boolean hasPassword(Context paramContext)
  {
    paramContext = LockSdConfig.getInstance(paramContext).getPasswordCode();
    if (paramContext == null) {
      return false;
    }
    if (!paramContext.equals("")) {
      return !paramContext.equals(ToolUtils.md5(""));
    }
    return false;
  }
  
  public static boolean isLockerOpened(Context paramContext)
  {
    if (LockScreenPreference.hasInitSdConfig(paramContext)) {
      LockSdConfig.initSdConfig(paramContext);
    }
    return (LockScreenPreference.isLockScreenSwitch(paramContext)) && (LockSdConfig.getInstance(paramContext).getLockMainPkg().equals(paramContext.getPackageName())) && (LockSdConfig.getInstance(paramContext).getLockerSwitch().equals("lock_config_switch_open"));
  }
  
  public static boolean isNewUser(Context paramContext)
  {
    boolean bool = false;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0);
      long l1 = paramContext.firstInstallTime;
      long l2 = paramContext.lastUpdateTime;
      if (l1 == l2) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
    return false;
  }
  
  public static boolean isPasswordOpened(Context paramContext)
  {
    return LockSdConfig.getInstance(paramContext).getPasswordSwitch().equals("lock_config_switch_open");
  }
  
  public static void openLockScreen(Context paramContext)
  {
    LockScreenPreference.saveLockScreenSwitch(paramContext, "true");
    LockSdConfig.getInstance(paramContext).saveLockerSwitchToSd("lock_config_switch_open");
    LockSdConfig.getInstance(paramContext).saveMainPkgToSd(paramContext.getPackageName());
    new Thread(new Runnable()
    {
      public void run()
      {
        PackageManager localPackageManager = this.val$context.getPackageManager();
        if (localPackageManager != null)
        {
          List localList = localPackageManager.getInstalledPackages(8192);
          int i = 0;
          while (i < localList.size())
          {
            PackageInfo localPackageInfo = (PackageInfo)localList.get(i);
            Object localObject = null;
            try
            {
              ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo(localPackageInfo.packageName, 128);
              localObject = localApplicationInfo;
            }
            catch (PackageManager.NameNotFoundException localNameNotFoundException)
            {
              localNameNotFoundException.printStackTrace();
            }
            LockScreenSetting.handleLockScreenSwitch(localPackageInfo, localObject, this.val$context);
            i += 1;
          }
        }
        if (LockScreenPreference.hasInitSdConfig(this.val$context)) {
          LockSdConfig.initSdConfig(this.val$context);
        }
      }
    }).start();
  }
  
  public static void openPassword(Context paramContext)
  {
    LockSdConfig.getInstance(paramContext).savePasswordSwitchToSd("lock_config_switch_open");
  }
  
  public static boolean passwordIsTrue(Context paramContext, String paramString)
  {
    return LockSdConfig.getInstance(paramContext).getPasswordCode().equals(ToolUtils.md5(paramString));
  }
  
  public static void setMainLocker(Context paramContext, String paramString)
  {
    LockSdConfig.getInstance(paramContext).saveMainPkgToSd(paramString);
  }
  
  public static void setPassword(Context paramContext, String paramString)
  {
    LockSdConfig.getInstance(paramContext).savePasswordCodeToSd(ToolUtils.md5(paramString));
  }
  
  public static void setPasswordType(Context paramContext, String paramString)
  {
    LockSdConfig.getInstance(paramContext).savePasswordTypeToSd(paramString);
  }
  
  public static void setSkinLocker(Context paramContext, String paramString)
  {
    LockSdConfig.getInstance(paramContext).saveSkinPkgToSd(paramString);
  }
  
  public static void setVerifyAnswer(Context paramContext, String paramString)
  {
    LockSdConfig.getInstance(paramContext).saveVerifyAnswerToSd(ToolUtils.md5(paramString));
  }
  
  public static void setVerifyProblem(Context paramContext, String paramString)
  {
    LockSdConfig.getInstance(paramContext).saveVerifyProblemToSd(paramString);
  }
  
  public static boolean verifyAnswerIsTrue(Context paramContext, String paramString)
  {
    return LockSdConfig.getInstance(paramContext).getVerifyAnswer().equals(ToolUtils.md5(paramString));
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface PasswordType {}
}
