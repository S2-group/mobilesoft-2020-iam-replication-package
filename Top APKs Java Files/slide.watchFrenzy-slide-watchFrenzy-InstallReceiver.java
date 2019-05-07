package slide.watchFrenzy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import java.util.List;

public class InstallReceiver
  extends BroadcastReceiver
{
  public InstallReceiver() {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ("android.intent.action.PACKAGE_ADDED".equals(paramIntent.getAction()))
    {
      paramIntent = paramIntent.getData().toString().split(":");
      if (paramIntent.length >= 2)
      {
        paramIntent = paramIntent[1];
        paramContext = paramContext.getPackageManager();
        List localList = paramContext.getInstalledPackages(0);
        int i = 0;
        while (i < localList.size())
        {
          Object localObject = (PackageInfo)localList.get(i);
          if (((PackageInfo)localObject).packageName.equals(paramIntent)) {
            ((PackageInfo)localObject).applicationInfo.loadLabel(paramContext).toString();
          }
          try
          {
            localObject = paramContext.getApplicationInfo(paramIntent, 128);
            if (((ApplicationInfo)localObject).metaData != null)
            {
              boolean bool = ((ApplicationInfo)localObject).metaData.containsKey("watchmaker.watch");
              if (!bool) {}
            }
          }
          catch (PackageManager.NameNotFoundException localNameNotFoundException)
          {
            for (;;) {}
          }
          i += 1;
        }
      }
    }
  }
}
