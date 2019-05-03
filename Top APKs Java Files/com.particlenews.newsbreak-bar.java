import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.hipu.yidian.HipuApplication;
import java.util.Iterator;
import java.util.List;

public final class bar
{
  public static String a()
  {
    try
    {
      Object localObject = HipuApplication.a().getPackageManager().getInstalledApplications(128);
      StringBuilder localStringBuilder = new StringBuilder();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
        if ((localApplicationInfo.flags & 0x1) == 0) {
          localStringBuilder.append(localApplicationInfo.packageName).append(",");
        }
      }
      str = localException.toString();
    }
    catch (Exception localException)
    {
      return "";
    }
    String str;
    return str;
  }
  
  public static String b()
  {
    try
    {
      Object localObject = ((ActivityManager)HipuApplication.a().getSystemService("activity")).getRunningTasks(Integer.MAX_VALUE);
      StringBuilder localStringBuilder = new StringBuilder();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        localStringBuilder.append(((ActivityManager.RunningTaskInfo)((Iterator)localObject).next()).topActivity.getPackageName()).append(",");
      }
      str = localException.toString();
    }
    catch (Exception localException)
    {
      return "";
    }
    String str;
    return str;
  }
}
