package o;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import com.kavsdk.appcategorizer.AppCategorizer;
import com.kavsdk.appcontrol.impl.AppControlImpl;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class to
  implements Runnable
{
  public to(AppControlImpl paramAppControlImpl) {}
  
  public void run()
  {
    Object localObject = AppControlImpl.僅輸入原文(this.難經本義).getPackageManager().getInstalledApplications(0);
    AppCategorizer localAppCategorizer = AppCategorizer.getInstance();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      try
      {
        localAppCategorizer.getCategory(localApplicationInfo.packageName);
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
      }
    }
  }
}
