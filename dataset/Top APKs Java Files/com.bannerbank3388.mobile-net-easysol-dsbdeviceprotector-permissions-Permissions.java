package net.easysol.dsbdeviceprotector.permissions;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Permissions
  extends Activity
{
  ListView apkList;
  String appsName;
  PackageManager packageManager;
  String[] requestedPermissions;
  
  public Permissions() {}
  
  public boolean checkPermission(String[] paramArrayOfString)
  {
    int i;
    if (paramArrayOfString != null) {
      i = 0;
    }
    for (;;)
    {
      if (i >= paramArrayOfString.length) {
        return false;
      }
      if ((paramArrayOfString[i].equals("android.permission.INTERNET")) || (paramArrayOfString[i].equals("android.permission.WRITE_EXTERNAL_STORAGE")) || (paramArrayOfString[i].equals("android.permission.ACCESS_BLUETOOTH_SHARE")) || (paramArrayOfString[i].equals("android.permission.ACCESS_BLUETOOTH_SHARE")) || (paramArrayOfString[i].equals("android.permission.ACCESS_WRITE_SETTINGS")) || (paramArrayOfString[i].equals("android.permission.READ_SMS")) || (paramArrayOfString[i].equals("android.permission.CALL_PRIVILEGED")) || (paramArrayOfString[i].equals("android.permission.ACCESS_WRITE_SETTINGS")) || (paramArrayOfString[i].equals("android.permission.CALL_PHONE")) || (paramArrayOfString[i].equals("android.permission.SEND_SMS")) || (paramArrayOfString[i].equals("android.permission.ACCESS_WRITE_SETTINGS")) || (paramArrayOfString[i].equals("android.permission.MANAGE_ACCOUNTS"))) {
        return true;
      }
      i += 1;
    }
  }
  
  public ArrayList<AppInfo> getPermissions(Context paramContext)
  {
    this.packageManager = paramContext.getPackageManager();
    Object localObject = this.packageManager.getInstalledPackages(4096);
    paramContext = new ArrayList();
    localObject = ((List)localObject).iterator();
    for (;;)
    {
      if (!((Iterator)localObject).hasNext()) {
        return paramContext;
      }
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
      if (((localApplicationInfo.flags & 0x1) == 1) && (checkPermission(localPackageInfo.requestedPermissions)))
      {
        AppInfo localAppInfo = new AppInfo();
        localAppInfo.setName(localApplicationInfo.loadLabel(this.packageManager).toString());
        localAppInfo.setPermission(localPackageInfo.requestedPermissions);
        paramContext.add(localAppInfo);
      }
    }
  }
}
