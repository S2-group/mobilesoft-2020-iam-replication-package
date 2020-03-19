package com.zenprise.eas;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.Button;
import com.citrix.work.log.Logger;
import com.zenprise.communication.KernelService;
import com.zenprise.configuration.PackageInstallation;
import com.zenprise.gui.NPNotification;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ConnectionForAppStoreInternal
  implements Runnable
{
  private static int cpt = 0;
  private static Context ctx = null;
  private static Runnable upDateButtonInstall = new Runnable()
  {
    public void run()
    {
      PackageInstallation.erasePackageInstallList();
      KernelService.listPocessusPendingInstall = new HashMap();
      Object localObject1 = new ArrayList();
      Object localObject2 = KernelService.appButtonPendingInstall.iterator();
      while (((Iterator)localObject2).hasNext())
      {
        ButtonAppStoreState localButtonAppStoreState = (ButtonAppStoreState)((Iterator)localObject2).next();
        localButtonAppStoreState.getButtonAppStoreState().setText(2131493235);
        localButtonAppStoreState.getButtonAppStoreState().setClickable(true);
        localButtonAppStoreState.getButtonAppStoreState().setEnabled(true);
        localButtonAppStoreState.getButtonAppStoreState().refreshDrawableState();
        ((ArrayList)localObject1).add(localButtonAppStoreState);
      }
      localObject1 = ((ArrayList)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (ButtonAppStoreState)((Iterator)localObject1).next();
        KernelService.appButtonPendingInstall.remove(localObject2);
      }
    }
  };
  private String buttonId = null;
  Logger logger = Logger.getLogger(getClass());
  
  public ConnectionForAppStoreInternal(Context paramContext, String paramString)
  {
    ctx = paramContext;
    this.buttonId = paramString;
  }
  
  private void runOnMainScreen(Runnable paramRunnable)
  {
    if (ApplicationStore.me != null) {
      ApplicationStore.me.runOnUiThread(paramRunnable);
    }
  }
  
  public void run()
  {
    for (;;)
    {
      int k;
      try
      {
        cpt = 0;
        cpt += 1;
        Thread.sleep(2000L);
        if (cpt == 10)
        {
          j = 0;
          ArrayList localArrayList = (ArrayList)ctx.getPackageManager().getInstalledPackages(0);
          int m = localArrayList.size();
          i = 0;
          if (i >= m) {
            break label196;
          }
          PackageInfo localPackageInfo = (PackageInfo)localArrayList.get(i);
          ApplicationInfo localApplicationInfo = localPackageInfo.applicationInfo;
          k = j;
          if (localApplicationInfo == null) {
            break label187;
          }
          k = j;
          if (localApplicationInfo.sourceDir.startsWith("/system")) {
            break label187;
          }
          int n = localPackageInfo.versionCode;
          k = j;
          if (!(localPackageInfo.packageName + "_" + String.valueOf(n)).equals(this.buttonId)) {
            break label187;
          }
          k = 1;
          break label187;
          runOnMainScreen(upDateButtonInstall);
          return;
          if (NPNotification.transferring) {
            cpt = 0;
          }
        }
        int i = cpt;
        if (i < 20) {
          continue;
        }
        continue;
        i += 1;
      }
      catch (InterruptedException localInterruptedException)
      {
        return;
      }
      label187:
      int j = k;
      continue;
      label196:
      if (j == 0) {}
    }
  }
}
