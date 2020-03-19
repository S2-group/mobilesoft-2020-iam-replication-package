package lb.moregame;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.umeng.analytics.MobclickAgent;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import wj.utils.WJUtils;

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
    if (s_lastPackageList != null)
    {
      int i = 0;
      int j = s_lastPackageList.size();
      while (i < j)
      {
        if (((PackageInfo)s_lastPackageList.get(i)).packageName.equals(paramString)) {
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
        localObject = new HashMap();
        ((Map)localObject).put("installedPackage", paramIntent);
        ((Map)localObject).put("device", WJUtils.getDeviceModel());
        ((Map)localObject).put("action", "installSuccess");
        MobclickAgent.onEvent(paramContext, "moregame", (Map)localObject);
      }
      refreshPackageList(paramContext);
    }
    while (!((String)localObject).equals("android.intent.action.PACKAGE_REMOVED")) {
      return;
    }
    refreshPackageList(paramContext);
  }
}
