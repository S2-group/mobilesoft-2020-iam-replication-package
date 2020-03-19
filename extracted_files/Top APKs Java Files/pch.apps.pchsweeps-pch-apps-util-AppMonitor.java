package pch.apps.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import pch.apps.util.analytics.Analytics;
import pch.apps.util.analytics.AnalyticsFactory;
import pch.apps.util.preferences.PCHPreferences;
import pch.apps.util.preferences.PCHPreferencesFactory;
import pch.sweeps.contests.EntryConfirmationChecker;
import pch.sweeps.walls.SweepstakesEntryWall;

public class AppMonitor
  extends BroadcastReceiver
{
  private Analytics analytics;
  String appName;
  int count;
  private Bundle extras;
  boolean isAppInstalled;
  boolean isAppInstalling;
  boolean isAppRunning;
  private Context mContext;
  private PCHPreferences mPrefs;
  String pkg;
  String url;
  
  public AppMonitor() {}
  
  private void StartingPCH()
  {
    final Handler localHandler = new Handler();
    localHandler.post(new Runnable()
    {
      public void run()
      {
        localHandler.postDelayed(new Runnable()
        {
          public void run()
          {
            AppMonitor.this.appIsRunning();
            AppMonitor.this.End();
            AppMonitor.this.triggerRedemptionPush(AppMonitor.this.appName);
          }
        }, 1000L);
      }
    });
  }
  
  public void End()
  {
    this.mPrefs.store("app_downloading", false);
    this.mPrefs.store("app_installed", false);
    this.mPrefs.store("app_running", false);
    this.mPrefs.store("app_bonus_complete", true);
    this.mPrefs.store("app_looper_end", true);
  }
  
  public void appIsDownloading()
  {
    this.mPrefs.store("app_downloading", true);
  }
  
  public void appIsInstalled()
  {
    this.mPrefs.store("app_installed", true);
  }
  
  public void appIsRunning()
  {
    this.mPrefs.store("app_running", true);
  }
  
  public void cancelNotifierAlarm()
  {
    Object localObject = new Intent(this.mContext, AppMonitorMessenger.class);
    localObject = PendingIntent.getBroadcast(this.mContext, 11643, (Intent)localObject, 268435456);
    ((AlarmManager)this.mContext.getSystemService("alarm")).cancel((PendingIntent)localObject);
  }
  
  public boolean checkRunningApps(String paramString)
  {
    List localList = ((ActivityManager)this.mContext.getSystemService("activity")).getRunningTasks(Integer.MAX_VALUE);
    int i = 0;
    for (;;)
    {
      if (i >= localList.size()) {
        return false;
      }
      if (((ActivityManager.RunningTaskInfo)localList.get(i)).baseActivity.getPackageName().equals(paramString)) {
        return true;
      }
      i += 1;
    }
  }
  
  public void forceReferrer(String paramString, Context paramContext)
  {
    Intent localIntent = new Intent("com.android.vending.INSTALL_REFERRER");
    localIntent.setPackage(paramString);
    paramContext.sendBroadcast(localIntent);
  }
  
  public String getAppPKG(String paramString)
  {
    String str = "null";
    PackageManager localPackageManager = this.mContext.getPackageManager();
    Object localObject = localPackageManager.getInstalledApplications(0);
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    localObject = ((List)localObject).iterator();
    int i;
    if (!((Iterator)localObject).hasNext()) {
      i = 0;
    }
    for (;;)
    {
      if (i >= localArrayList1.size())
      {
        return str;
        ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
        if ((localApplicationInfo.flags & 0x80) == 1)
        {
          localArrayList2.add(localApplicationInfo.packageName);
          localArrayList1.add(localApplicationInfo.loadLabel(localPackageManager).toString());
          break;
        }
        if ((localApplicationInfo.flags & 0x1) == 1) {
          break;
        }
        localArrayList2.add(localApplicationInfo.packageName);
        localApplicationInfo.loadLabel(localPackageManager).toString();
        localArrayList1.add(localApplicationInfo.loadLabel(localPackageManager).toString());
        break;
      }
      if (((String)localArrayList1.get(i)).equals(paramString)) {
        str = ((String)localArrayList2.get(i)).toString();
      }
      i += 1;
    }
  }
  
  public List<String> getInstalledApps()
  {
    localPackageManager = this.mContext.getPackageManager();
    localObject = new ArrayList();
    localArrayList1 = new ArrayList();
    localArrayList2 = new ArrayList();
    localApplicationInfo2 = new ApplicationInfo();
    localApplicationInfo2.packageName = "null.package.exception";
    try
    {
      List localList = localPackageManager.getInstalledApplications(0);
      localObject = localList;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        ((List)localObject).add(localApplicationInfo2);
        continue;
        ApplicationInfo localApplicationInfo1 = (ApplicationInfo)((Iterator)localObject).next();
        if ((localApplicationInfo1.flags & 0x80) == 1)
        {
          localArrayList2.add(localApplicationInfo1.packageName);
          localArrayList1.add(localApplicationInfo1.loadLabel(localPackageManager).toString());
        }
        else if ((localApplicationInfo1.flags & 0x1) != 1)
        {
          localArrayList2.add(localApplicationInfo1.packageName);
          localArrayList1.add(localApplicationInfo1.loadLabel(localPackageManager).toString());
        }
      }
    }
    localObject = ((List)localObject).iterator();
    if (!((Iterator)localObject).hasNext()) {
      return localArrayList2;
    }
  }
  
  public boolean isAppDownloading(Context paramContext)
  {
    return this.mPrefs.get("app_downloading", false);
  }
  
  public boolean isAppInstallFinished(String paramString)
  {
    List localList = getInstalledApps();
    if (localList.contains(paramString))
    {
      appIsInstalled();
      startApp(paramString);
    }
    return localList.contains(paramString);
  }
  
  public boolean isAppInstalled(Context paramContext)
  {
    return this.mPrefs.get("app_installed", false);
  }
  
  public boolean isAppRunning(String paramString)
  {
    return checkRunningApps(paramString);
  }
  
  public boolean isPackageInstalled(String paramString)
  {
    PackageManager localPackageManager = this.mContext.getPackageManager();
    Object localObject = localPackageManager.getInstalledApplications(0);
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    localObject = ((List)localObject).iterator();
    for (;;)
    {
      if (!((Iterator)localObject).hasNext())
      {
        boolean bool = false;
        if (localArrayList2.contains(paramString)) {
          bool = true;
        }
        return bool;
      }
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      if ((localApplicationInfo.flags & 0x80) == 1)
      {
        localArrayList2.add(localApplicationInfo.packageName);
        localArrayList1.add(localApplicationInfo.loadLabel(localPackageManager).toString());
      }
      else if ((localApplicationInfo.flags & 0x1) != 1)
      {
        localArrayList2.add(localApplicationInfo.packageName);
        localApplicationInfo.loadLabel(localPackageManager).toString();
        localArrayList1.add(localApplicationInfo.loadLabel(localPackageManager).toString());
      }
    }
  }
  
  public boolean isSweepsOpen()
  {
    return this.mPrefs.get("app_looper_end", false);
  }
  
  @SuppressLint({"InflateParams"})
  public void makeStatusToast(String paramString1, String paramString2, Context paramContext)
  {
    View localView = LayoutInflater.from(paramContext).inflate(2130903077, null);
    ((TextView)localView.findViewById(2131034238)).setText(paramString1);
    ((TextView)localView.findViewById(2131034232)).setText(paramString2);
    paramString1 = new Toast(paramContext);
    paramString1.setView(localView);
    paramString1.show();
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    this.extras = paramIntent.getExtras();
    this.pkg = this.extras.getString("app_pkg");
    this.appName = this.extras.getString("app_name");
    this.url = this.extras.getString("app_url");
    this.mContext = paramContext;
    this.mPrefs = PCHPreferencesFactory.createPreferences(paramContext);
    this.analytics = AnalyticsFactory.getInstance(paramContext);
    if (!isSweepsOpen())
    {
      if (!checkRunningApps(this.pkg)) {
        break label234;
      }
      makeStatusToast("Great job!", "Check out " + this.appName + " and then return to PCH Sweeps to get more entries!", paramContext);
      this.mPrefs.store("app_bonus_complete", true);
      paramContext = new Intent(this.mContext, AppMonitorMessenger.class);
      paramContext = PendingIntent.getBroadcast(this.mContext, 11643, paramContext, 268435456);
      ((AlarmManager)this.mContext.getSystemService("alarm")).cancel(paramContext);
      StartingPCH();
    }
    for (;;)
    {
      this.count = Integer.parseInt(this.mPrefs.get("checkEntryNotifierCounter", "0"));
      if (this.count + 1 < 100) {
        break;
      }
      this.mPrefs.store("checkEntryNotifierCounter", null);
      cancelNotifierAlarm();
      return;
      label234:
      if (isPackageInstalled(this.pkg))
      {
        makeStatusToast(this.appName + " has installed", "Time to open it!", paramContext);
        triggerOpenAppPush(this.pkg);
        appIsInstalled();
        startApp(this.pkg);
      }
      else if (isAppDownloading(paramContext))
      {
        appIsDownloading();
        triggerInstallPush();
        this.mPrefs.store("app_installed", false);
      }
    }
    this.mPrefs.store("checkEntryNotifierCounter", this.count + 1);
  }
  
  public void startApp(final String paramString)
  {
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        new Intent();
        Intent localIntent = AppMonitor.this.mContext.getPackageManager().getLaunchIntentForPackage(paramString);
        localIntent.addCategory("android.intent.category.LAUNCHER");
        localIntent.addFlags(268435456);
        AppMonitor.this.mContext.startActivity(localIntent);
      }
    }, 1000L);
  }
  
  public void triggerInstallPush()
  {
    Object localObject1 = Calendar.getInstance();
    this.mPrefs.store("install_start_time", ((Calendar)localObject1).getTimeInMillis());
    localObject1 = new NotificationCompat.Builder(this.mContext).setSmallIcon(2130837758).setContentTitle(this.mContext.getResources().getString(2131230790)).setContentText(this.mContext.getResources().getString(2131230873) + this.appName + "!");
    Object localObject2 = new Intent("android.intent.action.VIEW", Uri.parse(this.url));
    ((Intent)localObject2).addCategory("android.intent.category.BROWSABLE");
    ((Intent)localObject2).addFlags(268435456);
    TaskStackBuilder localTaskStackBuilder = TaskStackBuilder.create(this.mContext);
    localTaskStackBuilder.addParentStack(SweepstakesEntryWall.class);
    localTaskStackBuilder.addNextIntent((Intent)localObject2);
    ((NotificationCompat.Builder)localObject1).setContentIntent(localTaskStackBuilder.getPendingIntent(0, 134217728));
    localObject2 = new RemoteViews(this.mContext.getPackageName(), 2130903116);
    ((RemoteViews)localObject2).setTextViewText(2131034177, this.mContext.getResources().getString(2131230790));
    ((RemoteViews)localObject2).setTextViewText(2131034345, this.mContext.getResources().getString(2131230873) + this.appName + "!");
    ((NotificationCompat.Builder)localObject1).setContent((RemoteViews)localObject2);
    localObject1 = ((NotificationCompat.Builder)localObject1).build();
    ((Notification)localObject1).contentView = ((RemoteViews)localObject2);
    if (!this.mPrefs.get("push_app_download_installing", false))
    {
      localObject2 = (NotificationManager)this.mContext.getSystemService("notification");
      ((NotificationManager)localObject2).cancel(1337);
      ((NotificationManager)localObject2).notify(1337, (Notification)localObject1);
      this.mPrefs.store("push_app_download_installing", true);
    }
  }
  
  public void triggerOpenAppPush(String paramString)
  {
    NotificationCompat.Builder localBuilder = new NotificationCompat.Builder(this.mContext).setSmallIcon(2130837758).setAutoCancel(true).setContentTitle(this.mContext.getResources().getString(2131230790)).setContentText(this.mContext.getResources().getString(2131230874) + this.appName + "!");
    Object localObject = new RemoteViews(this.mContext.getPackageName(), 2130903118);
    ((RemoteViews)localObject).setTextViewText(2131034177, this.mContext.getResources().getString(2131230790));
    ((RemoteViews)localObject).setTextViewText(2131034345, this.mContext.getResources().getString(2131230874) + this.appName + "!");
    localBuilder.setContent((RemoteViews)localObject);
    new Intent(this.mContext, SweepstakesEntryWall.class).setFlags(872415232);
    paramString = this.mContext.getPackageManager().getLaunchIntentForPackage(paramString);
    paramString.setFlags(872415232);
    paramString.setPackage(null);
    TaskStackBuilder localTaskStackBuilder = TaskStackBuilder.create(this.mContext);
    localTaskStackBuilder.addParentStack(SweepstakesEntryWall.class);
    localTaskStackBuilder.addNextIntent(paramString);
    localBuilder.setContentIntent(localTaskStackBuilder.getPendingIntent(0, 134217728));
    boolean bool = this.mPrefs.get("push_app_installed", false);
    paramString = localBuilder.build();
    paramString.contentView = ((RemoteViews)localObject);
    if (!bool)
    {
      localObject = (NotificationManager)this.mContext.getSystemService("notification");
      ((NotificationManager)localObject).cancel(1337);
      ((NotificationManager)localObject).notify(1337, paramString);
      this.mPrefs.store("push_app_download_installed", true);
    }
  }
  
  public void triggerRedemptionPush(String paramString)
  {
    Object localObject1 = Long.valueOf(12345678910L);
    long l = this.mPrefs.get("install_start_time", ((Long)localObject1).longValue());
    Object localObject2 = Calendar.getInstance();
    if (l != ((Long)localObject1).longValue())
    {
      int i = (int)((((Calendar)localObject2).getTimeInMillis() - l) / 60000L % 60L);
      this.analytics.tag("App Open Time: " + i);
    }
    localObject2 = new NotificationCompat.Builder(this.mContext).setSmallIcon(2130837758).setAutoCancel(true).setContentTitle(this.mContext.getResources().getString(2131230790)).setContentText(this.mContext.getResources().getString(2131230875) + paramString + "!");
    localObject1 = new RemoteViews(this.mContext.getPackageName(), 2130903117);
    ((RemoteViews)localObject1).setTextViewText(2131034177, this.mContext.getResources().getString(2131230790));
    ((RemoteViews)localObject1).setTextViewText(2131034345, this.mContext.getResources().getString(2131230875) + paramString + "!");
    ((NotificationCompat.Builder)localObject2).setContent((RemoteViews)localObject1);
    paramString = new Intent(this.mContext, SweepstakesEntryWall.class);
    paramString.setFlags(872415232);
    paramString.setAction("android.intent.action.MAIN");
    paramString.addCategory("android.intent.category.LAUNCHER");
    TaskStackBuilder localTaskStackBuilder = TaskStackBuilder.create(this.mContext);
    localTaskStackBuilder.addParentStack(SweepstakesEntryWall.class);
    localTaskStackBuilder.addNextIntent(paramString);
    ((NotificationCompat.Builder)localObject2).setContentIntent(localTaskStackBuilder.getPendingIntent(0, 134217728));
    paramString = ((NotificationCompat.Builder)localObject2).build();
    paramString.contentView = ((RemoteViews)localObject1);
    if (!this.mPrefs.get("push_app_download_complete", false))
    {
      localObject1 = (NotificationManager)this.mContext.getSystemService("notification");
      ((NotificationManager)localObject1).cancel(1337);
      ((NotificationManager)localObject1).cancel(1337);
      ((NotificationManager)localObject1).notify(1337, paramString);
      this.mPrefs.store("push_app_download_complete", false);
      this.mPrefs.store("push_app_download_installed", false);
      this.mPrefs.store("push_app_download_installing", false);
    }
    paramString = (AlarmManager)this.mContext.getSystemService("alarm");
    localObject1 = Calendar.getInstance();
    localObject2 = new Intent(this.mContext, EntryConfirmationChecker.class);
    ((Intent)localObject2).putExtras(this.extras);
    localObject2 = PendingIntent.getBroadcast(this.mContext, 1907, (Intent)localObject2, 268435456);
    paramString.setRepeating(0, ((Calendar)localObject1).getTimeInMillis(), 30000L, (PendingIntent)localObject2);
  }
}
