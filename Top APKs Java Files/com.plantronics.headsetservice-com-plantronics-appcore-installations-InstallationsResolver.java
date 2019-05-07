package com.plantronics.appcore.installations;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.plantronics.appcore.debug.CoreLogTag;
import java.util.Iterator;
import java.util.List;

public class InstallationsResolver
{
  public static final String HBM_NAME = "com.plantronics.widget.ion.activities.WidgetSettingsActivity";
  public static final String HBM_PACKAGE_NAME = "com.plantronics.widget.ion";
  public static final String TAG = CoreLogTag.getGlobalTagPrefix() + InstallationsResolver.class.getSimpleName();
  private static InstallationsResolver sInstance;
  
  private InstallationsResolver() {}
  
  public static InstallationsResolver getInstance()
  {
    if (sInstance == null) {
      sInstance = new InstallationsResolver();
    }
    return sInstance;
  }
  
  public boolean isHeadsetBatteryMeterInstalled(Context paramContext)
  {
    Log.d(TAG, "Called isHeadsetBatteryMeterInstalled");
    boolean bool2 = false;
    paramContext = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    do
    {
      bool1 = bool2;
      if (!paramContext.hasNext()) {
        break;
      }
    } while (!"com.plantronics.widget.ion".equals(((ApplicationInfo)paramContext.next()).packageName));
    boolean bool1 = true;
    return bool1;
  }
}
