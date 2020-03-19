package indian.plusone.phone.launcher.utils;

import android.content.ComponentName;
import indian.plusone.phone.launcher.AppFilter;
import indian.plusone.phone.launcher.LauncherAppState;
import indian.plusone.phone.launcher.themeui.request.LoadTask;
import java.util.List;

public class MyAppFilter
  extends AppFilter
{
  public MyAppFilter() {}
  
  public boolean shouldShowApp(ComponentName paramComponentName)
  {
    try
    {
      if (!paramComponentName.getPackageName().equals("indian.plusone.phone.launcher"))
      {
        boolean bool = LoadTask.getInstalledPackages(LauncherAppState.getInstance().getContext()).contains(paramComponentName.getPackageName());
        if (!bool) {}
      }
      else
      {
        return false;
      }
    }
    catch (Exception paramComponentName) {}
    return true;
  }
}
