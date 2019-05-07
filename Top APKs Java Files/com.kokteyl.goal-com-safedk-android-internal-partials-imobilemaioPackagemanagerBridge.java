package com.safedk.android.internal.partials;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.safedk.android.utils.Logger;
import java.util.ArrayList;
import java.util.List;

public class imobilemaioPackagemanagerBridge
{
  public static List<ApplicationInfo> packageManagerGetInstalledApplications(PackageManager paramPackageManager, int paramInt)
  {
    Logger.d("imobilemaioPackagemanager|SafeDK: Partial-Packagemanager> Lcom/safedk/android/internal/partials/imobilemaioPackagemanagerBridge;->packageManagerGetInstalledApplications(Landroid/content/pm/PackageManager;I)Ljava/util/List;");
    if (PackagemanagerBridge.isPackageManagerEnabled("jp.maio.sdk.android")) {
      return paramPackageManager.getInstalledApplications(paramInt);
    }
    return new ArrayList();
  }
}
