package com.safedk.android.internal.partials;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.safedk.android.utils.Logger;
import java.util.ArrayList;
import java.util.List;

public class SoomlaPackagemanagerBridge
{
  public static List<ApplicationInfo> packageManagerGetInstalledApplications(PackageManager paramPackageManager, int paramInt)
  {
    Logger.d("SoomlaPackagemanager|SafeDK: Partial-Packagemanager> Lcom/safedk/android/internal/partials/SoomlaPackagemanagerBridge;->packageManagerGetInstalledApplications(Landroid/content/pm/PackageManager;I)Ljava/util/List;");
    if (PackagemanagerBridge.isPackageManagerEnabled("com.soomla")) {
      return paramPackageManager.getInstalledApplications(paramInt);
    }
    return new ArrayList();
  }
}
