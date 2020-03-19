import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;

public class azw
{
  public azv a = null;
  
  public azw(Context paramContext)
  {
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    for (;;)
    {
      if (!paramContext.hasNext()) {
        return;
      }
      ApplicationInfo localApplicationInfo = (ApplicationInfo)paramContext.next();
      if (localApplicationInfo.packageName.equals("com.vaultmicro.camerafi.live")) {
        this.a = new azv(localApplicationInfo.uid);
      }
    }
  }
}
