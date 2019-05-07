package huya.com.libcommon.utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProcessUtil
{
  public ProcessUtil() {}
  
  public static String getCurrentProcessName(Context paramContext)
  {
    int i = Process.myPid();
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    if (paramContext != null)
    {
      paramContext = paramContext.getRunningAppProcesses();
      if (paramContext != null)
      {
        paramContext = paramContext.iterator();
        while (paramContext.hasNext())
        {
          ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
          if (localRunningAppProcessInfo.pid == i) {
            return localRunningAppProcessInfo.processName;
          }
        }
      }
    }
    return null;
  }
  
  @NonNull
  public static List<String> getInstalledPackages(PackageManager paramPackageManager)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramPackageManager != null)
    {
      paramPackageManager = paramPackageManager.getInstalledPackages(0);
      if (paramPackageManager != null)
      {
        paramPackageManager = paramPackageManager.iterator();
        while (paramPackageManager.hasNext())
        {
          PackageInfo localPackageInfo = (PackageInfo)paramPackageManager.next();
          if ((localPackageInfo != null) && (localPackageInfo.applicationInfo != null) && ((localPackageInfo.applicationInfo.flags & 0x1) == 0)) {
            localArrayList.add(localPackageInfo.packageName);
          }
        }
      }
    }
    return localArrayList;
  }
  
  public static boolean isMainProcess(Context paramContext)
  {
    return (getCurrentProcessName(paramContext) == null) || (paramContext.getPackageName().equals(getCurrentProcessName(paramContext)));
  }
}
