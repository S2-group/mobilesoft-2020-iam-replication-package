package com.libii.sdk.promo.inter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LBInterApkInstaller
  extends BroadcastReceiver
{
  private static Map<String, String> sInstallApkInfoMap = new HashMap();
  private static List<PackageInfo> sLastPackageList;
  
  public LBInterApkInstaller() {}
  
  public static void installApk(Context paramContext, String paramString1, String paramString2)
  {
    File localFile = new File(paramString1);
    if (!localFile.exists()) {
      return;
    }
    sInstallApkInfoMap.put(paramString2, paramString1);
    paramString1 = new Intent();
    paramString1.setAction("android.intent.action.VIEW");
    paramString1.setDataAndType(Uri.fromFile(localFile), "application/vnd.android.package-archive");
    paramContext.startActivity(paramString1);
  }
  
  public static boolean isAppInstalled(String paramString)
  {
    if (sLastPackageList != null)
    {
      int i = 0;
      int j = sLastPackageList.size();
      while (i < j)
      {
        if (((PackageInfo)sLastPackageList.get(i)).packageName.equals(paramString)) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  public static void refreshPackageList(Context paramContext)
  {
    try
    {
      sLastPackageList = paramContext.getPackageManager().getInstalledPackages(0);
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Object localObject = paramIntent.getAction();
    if ((((String)localObject).equals("android.intent.action.PACKAGE_ADDED")) || (((String)localObject).equals("android.intent.action.PACKAGE_REPLACED")))
    {
      paramIntent = paramIntent.getData().getSchemeSpecificPart();
      if (sInstallApkInfoMap.containsKey(paramIntent))
      {
        localObject = new File((String)sInstallApkInfoMap.get(paramIntent));
        if (((File)localObject).exists()) {
          ((File)localObject).delete();
        }
        sInstallApkInfoMap.remove(paramIntent);
      }
      refreshPackageList(paramContext);
    }
    while (!((String)localObject).equals("android.intent.action.PACKAGE_REMOVED")) {
      return;
    }
    refreshPackageList(paramContext);
  }
}
