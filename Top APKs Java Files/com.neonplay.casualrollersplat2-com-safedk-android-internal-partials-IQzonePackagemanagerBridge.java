package com.safedk.android.internal.partials;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.safedk.android.utils.Logger;
import java.util.ArrayList;
import java.util.List;

public class IQzonePackagemanagerBridge
{
  public static List<ApplicationInfo> packageManagerGetInstalledApplications(PackageManager paramPackageManager, int paramInt)
  {
    Logger.d("IQzonePackagemanager|SafeDK: Partial-Packagemanager> Lcom/safedk/android/internal/partials/IQzonePackagemanagerBridge;->packageManagerGetInstalledApplications(Landroid/content/pm/PackageManager;I)Ljava/util/List;");
    if (PackagemanagerBridge.isPackageManagerEnabled("com.IQzone")) {
      return paramPackageManager.getInstalledApplications(paramInt);
    }
    return new ArrayList();
  }
}
