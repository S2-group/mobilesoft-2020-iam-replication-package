package com.safedk.android.internal.partials;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.safedk.android.utils.Logger;
import java.util.ArrayList;
import java.util.List;

public class OguryPackagemanagerBridge
{
  public static List<ApplicationInfo> packageManagerGetInstalledApplications(PackageManager paramPackageManager, int paramInt)
  {
    Logger.d("OguryPackagemanager|SafeDK: Partial-Packagemanager> Lcom/safedk/android/internal/partials/OguryPackagemanagerBridge;->packageManagerGetInstalledApplications(Landroid/content/pm/PackageManager;I)Ljava/util/List;");
    if (PackagemanagerBridge.isPackageManagerEnabled("io.presage")) {
      return paramPackageManager.getInstalledApplications(paramInt);
    }
    return new ArrayList();
  }
  
  public static List<PackageInfo> packageManagerGetInstalledPackages(PackageManager paramPackageManager, int paramInt)
  {
    Logger.d("OguryPackagemanager|SafeDK: Partial-Packagemanager> Lcom/safedk/android/internal/partials/OguryPackagemanagerBridge;->packageManagerGetInstalledPackages(Landroid/content/pm/PackageManager;I)Ljava/util/List;");
    if (PackagemanagerBridge.isPackageManagerEnabled("io.presage")) {
      return paramPackageManager.getInstalledPackages(paramInt);
    }
    return new ArrayList();
  }
}
