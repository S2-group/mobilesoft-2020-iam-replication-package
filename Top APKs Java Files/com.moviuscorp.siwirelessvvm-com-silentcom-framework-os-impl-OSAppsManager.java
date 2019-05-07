package com.silentcom.framework.os.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import com.silentcom.framework.os.AppsManager;
import com.silentcom.framework.os.AppsManager.AppDescriptor;
import com.silentcom.framework.os.AppsManager.AppsManagerListener;
import com.silentcom.framework.os.Logger;
import com.silentcom.framework.ui.impl.OSUIApplication;
import com.silentcom.framework.ui.impl.OSUIProcess;
import java.io.File;
import java.util.List;
import java.util.Vector;

public class OSAppsManager
  implements AppsManager
{
  private static final String LOG_TAG = "FW_OS_AM";
  public static final int SDK_ANDROID_4 = 14;
  public AppsManager.AppsManagerListener listener = null;
  
  public OSAppsManager() {}
  
  public boolean checkInstalled(String paramString)
  {
    PackageManager localPackageManager = OSContext.getContext().getPackageManager();
    try
    {
      localPackageManager.getPackageInfo(paramString, 0);
      return true;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return false;
  }
  
  public AppsManager.AppDescriptor getAppDetails(String paramString)
  {
    PackageManager localPackageManager = OSContext.getContext().getPackageManager();
    try
    {
      AppsManager.AppDescriptor localAppDescriptor = new AppsManager.AppDescriptor();
      localAppDescriptor.id = paramString;
      paramString = localPackageManager.getPackageInfo(paramString, 0);
      localAppDescriptor.version = paramString.versionName;
      localAppDescriptor.versionCode = paramString.versionCode;
      localAppDescriptor.name = paramString.applicationInfo.name;
      return localAppDescriptor;
    }
    catch (PackageManager.NameNotFoundException paramString) {}
    return null;
  }
  
  public AppsManager.AppDescriptor[] getInstalledApps()
  {
    Vector localVector = new Vector();
    Object localObject1 = OSContext.getContext().getPackageManager();
    List localList = ((PackageManager)localObject1).getInstalledApplications(0);
    int i = 0;
    for (;;)
    {
      if (i >= localList.size())
      {
        localObject1 = new AppsManager.AppDescriptor[localVector.size()];
        localVector.copyInto((Object[])localObject1);
        return localObject1;
      }
      Object localObject2 = (ApplicationInfo)localList.get(i);
      AppsManager.AppDescriptor localAppDescriptor = new AppsManager.AppDescriptor();
      localAppDescriptor.id = ((ApplicationInfo)localObject2).packageName;
      int j = ((PackageManager)localObject1).getApplicationEnabledSetting(((ApplicationInfo)localObject2).packageName);
      if (((ApplicationInfo)localObject2).sourceDir != null)
      {
        int k = ((ApplicationInfo)localObject2).sourceDir.lastIndexOf('/') + 1;
        if (k > 0) {
          localAppDescriptor.fileName = ((ApplicationInfo)localObject2).sourceDir.substring(k);
        }
      }
      localAppDescriptor.name = ((PackageManager)localObject1).getApplicationLabel((ApplicationInfo)localObject2).toString();
      try
      {
        localObject2 = ((PackageManager)localObject1).getPackageInfo(((ApplicationInfo)localObject2).packageName, 0);
        localAppDescriptor.version = ((PackageInfo)localObject2).versionName;
        localAppDescriptor.versionCode = ((PackageInfo)localObject2).versionCode;
        FactoryLogger.getLogger().write(3, "FW_OS_AM", "found app " + localAppDescriptor.id + " " + localAppDescriptor.name + " " + localAppDescriptor.version + "enabled: " + j);
        localVector.addElement(localAppDescriptor);
        i += 1;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          FactoryLogger.getLogger().write(3, "FW_OS_AM", localException);
        }
      }
    }
  }
  
  public void install(String paramString, AppsManager.AppsManagerListener paramAppsManagerListener)
  {
    this.listener = paramAppsManagerListener;
    if (Build.VERSION.SDK_INT >= 14)
    {
      paramAppsManagerListener = new Intent("android.intent.action.INSTALL_PACKAGE");
      paramAppsManagerListener.putExtra("android.intent.extra.NOT_UNKNOWN_SOURCE", true);
      paramAppsManagerListener.putExtra("android.intent.extra.ALLOW_REPLACE", true);
      paramAppsManagerListener.putExtra("android.intent.extra.RETURN_RESULT", true);
      paramAppsManagerListener.setData(Uri.fromFile(new File(paramString)));
    }
    for (paramString = paramAppsManagerListener;; paramString = paramAppsManagerListener)
    {
      paramString.putExtra("android.intent.extra.INSTALLER_PACKAGE_NAME", OSContext.getContext().getPackageName());
      paramAppsManagerListener = OSUIApplication.getCurrentActivity();
      if (paramAppsManagerListener == null) {
        break;
      }
      paramAppsManagerListener.startActivityForResult(paramString, 1001);
      return;
      paramAppsManagerListener = new Intent("android.intent.action.VIEW");
      paramAppsManagerListener.setDataAndType(Uri.fromFile(new File(paramString)), "application/vnd.android.package-archive");
    }
    paramString.addFlags(268435456);
    OSContext.getContext().startActivity(paramString);
  }
  
  public boolean launch(AppsManager.AppDescriptor paramAppDescriptor)
  {
    return true;
  }
  
  public void onInstall()
  {
    if (this.listener != null) {
      this.listener.callback();
    }
  }
  
  public void onUninstall()
  {
    if (this.listener != null) {
      this.listener.callback();
    }
  }
  
  public void openNativeAppSettings()
  {
    OSUIProcess localOSUIProcess = OSUIApplication.getCurrentActivity();
    Intent localIntent;
    if (localOSUIProcess != null)
    {
      localIntent = new Intent();
      if (!Build.VERSION.RELEASE.startsWith("4")) {
        break label42;
      }
      localIntent.setAction("android.settings.SECURITY_SETTINGS");
    }
    for (;;)
    {
      localOSUIProcess.startActivity(localIntent);
      return;
      label42:
      localIntent.setAction("android.settings.APPLICATION_SETTINGS");
    }
  }
  
  public boolean uninstall(String paramString, AppsManager.AppsManagerListener paramAppsManagerListener)
  {
    this.listener = paramAppsManagerListener;
    paramAppsManagerListener = new Intent("android.intent.action.DELETE");
    paramAppsManagerListener.setData(Uri.parse("package:" + paramString));
    paramString = OSUIApplication.getCurrentActivity();
    if (paramString != null) {
      paramString.startActivityForResult(paramAppsManagerListener, 1002);
    }
    for (;;)
    {
      return true;
      paramAppsManagerListener.addFlags(268435456);
      OSContext.getContext().startActivity(paramAppsManagerListener);
    }
  }
  
  public boolean unknownSourceAllowed()
  {
    if (Build.VERSION.SDK_INT >= 14) {}
    OSUIProcess localOSUIProcess;
    do
    {
      try
      {
        Object localObject = OSContext.getContext();
        if (localObject != null)
        {
          String str = ((Context)localObject).getApplicationContext().getPackageName();
          localObject = ((Context)localObject).getPackageManager().getApplicationInfo(str, 0);
          if (localObject != null)
          {
            int i = ((ApplicationInfo)localObject).flags;
            if ((i & 0x1) != 0) {
              return true;
            }
          }
        }
      }
      catch (Exception localException)
      {
        FactoryLogger.getLogger().write(3, "FW_OS_AM", localException);
      }
      localOSUIProcess = OSUIApplication.getCurrentActivity();
      if (localOSUIProcess == null) {
        break;
      }
    } while (Settings.Secure.getInt(localOSUIProcess.getContentResolver(), "install_non_market_apps", 0) == 1);
    return false;
    return false;
  }
}
