package com.safedk.android.internal.partials;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.safedk.android.utils.Logger;
import java.util.ArrayList;
import java.util.List;

public class MintegralPackagemanagerBridge
{
  public static List<PackageInfo> packageManagerGetInstalledPackages(PackageManager paramPackageManager, int paramInt)
  {
    Logger.d("MintegralPackagemanager|SafeDK: Partial-Packagemanager> Lcom/safedk/android/internal/partials/MintegralPackagemanagerBridge;->packageManagerGetInstalledPackages(Landroid/content/pm/PackageManager;I)Ljava/util/List;");
    if (PackagemanagerBridge.isPackageManagerEnabled("com.mintegral.msdk")) {
      return paramPackageManager.getInstalledPackages(paramInt);
    }
    return new ArrayList();
  }
}
