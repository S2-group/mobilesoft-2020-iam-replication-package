package lb.moregame;

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

public class LBMoreGameApkInstaller
  extends BroadcastReceiver
{
  private static Map<String, String> s_installApkInfoMap = new HashMap();
  private static List<PackageInfo> s_lastPackageList;
  
  public LBMoreGameApkInstaller() {}
  
  public static void installApk(Context paramContext, String paramString1, String paramString2)
  {
    File localFile = new File(paramString1);
    if (!localFile.exists()) {
      return;
    }
    s_installApkInfoMap.put(paramString2, paramString1);
    paramString1 = new Intent();
    paramString1.setAction("android.intent.action.VIEW");
    paramString1.setDataAndType(Uri.fromFile(localFile), "application/vnd.android.package-archive");
    paramContext.startActivity(paramString1);
  }
  
  public static boolean isAppInstalled(String paramString)
  {
    int i;
    int j;
    if (s_lastPackageList != null)
    {
      i = 0;
      j = s_lastPackageList.size();
    }
    for (;;)
    {
      if (i >= j) {
        return false;
      }
      if (((PackageInfo)s_lastPackageList.get(i)).packageName.equals(paramString)) {
        return true;
      }
      i += 1;
    }
  }
  
  public static void refreshPackageList(Context paramContext)
  {
    try
    {
      s_lastPackageList = paramContext.getPackageManager().getInstalledPackages(0);
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
      if (s_installApkInfoMap.containsKey(paramIntent))
      {
        localObject = new File((String)s_installApkInfoMap.get(paramIntent));
        if (((File)localObject).exists()) {
          ((File)localObject).delete();
        }
        s_installApkInfoMap.remove(paramIntent);
      }
      refreshPackageList(paramContext);
    }
    while (!((String)localObject).equals("android.intent.action.PACKAGE_REMOVED")) {
      return;
    }
    refreshPackageList(paramContext);
  }
}
