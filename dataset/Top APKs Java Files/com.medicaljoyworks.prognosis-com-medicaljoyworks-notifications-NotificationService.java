package com.medicaljoyworks.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import com.medicaljoyworks.helper.CaseDownloader;
import com.medicaljoyworks.prognosis.MainMenu;
import com.medicaljoyworks.prognosis.R.drawable;
import com.medicaljoyworks.prognosis.R.string;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

public class NotificationService
  extends Service
{
  private static final int CASE_CHECK_INTERVAL = 43200000;
  private CaseDownloader caseDownloader;
  private NotificationManager nm;
  
  public NotificationService() {}
  
  private boolean isGCMSupported()
  {
    Iterator localIterator = getPackageManager().getInstalledPackages(0).iterator();
    do
    {
      if (!localIterator.hasNext()) {
        return false;
      }
    } while (!((PackageInfo)localIterator.next()).packageName.equals("com.google.android.gsf"));
    return true;
  }
  
  private void showNotification()
  {
    Notification localNotification = new Notification(R.drawable.prognosis_icon, getString(R.string.notification_text), System.currentTimeMillis());
    PendingIntent localPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainMenu.class), 0);
    localNotification.setLatestEventInfo(this, getString(R.string.app_name), getString(R.string.notification_text), localPendingIntent);
    this.nm.notify(0, localNotification);
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    this.caseDownloader = new CaseDownloader(this);
    this.nm = ((NotificationManager)getSystemService("notification"));
  }
  
  public void onDestroy() {}
  
  public int onStartCommand(final Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (paramInt2 == 1)
    {
      System.out.println("Trying to start the notification thread.");
      if (isGCMSupported())
      {
        System.out.println("GCM is supported. Disabling the notificaiton thread");
        stopSelf();
      }
    }
    else
    {
      return 1;
    }
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          boolean bool = paramIntent.getBooleanExtra("checkImmediately", false);
          for (;;)
          {
            if (bool)
            {
              System.out.println("Checking for new cases");
              if (NotificationService.this.caseDownloader.checkNewCases()) {
                NotificationService.this.showNotification();
              }
            }
            bool = true;
            Thread.sleep(43200000L);
          }
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }).start();
    return 1;
  }
}
