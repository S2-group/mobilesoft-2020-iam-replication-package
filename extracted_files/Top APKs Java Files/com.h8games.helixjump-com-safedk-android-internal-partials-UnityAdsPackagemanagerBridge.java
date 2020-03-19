package com.safedk.android.internal.partials;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.safedk.android.utils.Logger;
import java.util.ArrayList;
import java.util.List;

public class UnityAdsPackagemanagerBridge
{
  public static List<PackageInfo> packageManagerGetInstalledPackages(PackageManager paramPackageManager, int paramInt)
  {
    Logger.d("UnityAdsPackagemanager|SafeDK: Partial-Packagemanager> Lcom/safedk/android/internal/partials/UnityAdsPackagemanagerBridge;->packageManagerGetInstalledPackages(Landroid/content/pm/PackageManager;I)Ljava/util/List;");
    if (PackagemanagerBridge.isPackageManagerEnabled("com.unity3d.ads")) {
      return paramPackageManager.getInstalledPackages(paramInt);
    }
    return new ArrayList();
  }
}
