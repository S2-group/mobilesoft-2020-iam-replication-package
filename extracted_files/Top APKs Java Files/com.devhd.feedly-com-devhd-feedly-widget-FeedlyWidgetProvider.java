package com.devhd.feedly.widget;

import android.annotation.SuppressLint;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.PersistableBundle;
import com.devhd.feedly.widget.entry.FeedlyEntry;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FeedlyWidgetProvider
  extends AppWidgetProvider
{
  static final String CAT_WIDGET = "homescreen_widget";
  private static int FLEX_INTERVAL = 900000;
  private static int JOB_ID = 0;
  private static int JOB_INTERVAL = 0;
  public static int SHORT_DELAY = 120000;
  private static final Logger sLog = Logger.getLogger("widget.provider");
  private static final String sPackageName = "com.devhd.feedly";
  private Boolean fAppInstalled = null;
  private Intent fIntent;
  
  static
  {
    JOB_ID = 1;
    JOB_INTERVAL = 1800000;
  }
  
  public FeedlyWidgetProvider() {}
  
  public static void askRefreshWidgets(Context paramContext, long paramLong)
  {
    Object localObject = new ComponentName(paramContext, FeedlyWidgetJobService.class);
    localObject = new JobInfo.Builder(JOB_ID, (ComponentName)localObject);
    ((JobInfo.Builder)localObject).setRequiresCharging(false);
    if (Build.VERSION.SDK_INT >= 26) {
      ((JobInfo.Builder)localObject).setRequiresBatteryNotLow(true);
    }
    if (paramLong >= 0L) {
      ((JobInfo.Builder)localObject).setMinimumLatency(paramLong);
    }
    ((JobInfo.Builder)localObject).setRequiredNetworkType(1);
    ((JobInfo.Builder)localObject).setRequiresCharging(false);
    ((JobScheduler)paramContext.getSystemService("jobscheduler")).schedule(((JobInfo.Builder)localObject).build());
  }
  
  private void askTransitionNext(Context paramContext, int paramInt)
  {
    try
    {
      new FeedlyWidgetUpdaterTask(paramContext, paramInt, true)
      {
        protected void onPostExecute(Boolean paramAnonymousBoolean) {}
      }.execute(new Void[0]);
      return;
    }
    catch (Exception paramContext)
    {
      sLog.e(new Object[] { ">>> widget:askTransitionNext:failed -- ", paramContext });
    }
  }
  
  private void askUpdateWidgets(Context paramContext, int[] paramArrayOfInt)
  {
    try
    {
      PersistableBundle localPersistableBundle = new PersistableBundle();
      localPersistableBundle.putIntArray("widgetIds", paramArrayOfInt);
      paramArrayOfInt = new ComponentName(paramContext, FeedlyWidgetJobService.class);
      paramArrayOfInt = new JobInfo.Builder(JOB_ID, paramArrayOfInt);
      paramArrayOfInt.setRequiredNetworkType(3);
      paramArrayOfInt.setExtras(localPersistableBundle);
      ((JobScheduler)paramContext.getSystemService("jobscheduler")).schedule(paramArrayOfInt.build());
      return;
    }
    catch (Exception paramContext)
    {
      sLog.e(new Object[] { ">>> widget:askUpdateWidgets:failed -- ", paramContext });
    }
  }
  
  private void openApp(Context paramContext, int paramInt)
  {
    Object localObject;
    if (this.fAppInstalled == null)
    {
      localObject = paramContext.getPackageManager().getInstalledApplications(0).iterator();
      while (((Iterator)localObject).hasNext()) {
        if (((ApplicationInfo)((Iterator)localObject).next()).packageName.equals("com.devhd.feedly")) {
          this.fAppInstalled = Boolean.valueOf(true);
        }
      }
    }
    if (this.fAppInstalled == null) {
      this.fAppInstalled = Boolean.valueOf(false);
    }
    if (this.fAppInstalled.booleanValue())
    {
      localObject = paramContext.getPackageManager().getLaunchIntentForPackage("com.devhd.feedly");
    }
    else
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("market://details?id=com.devhd.feedly&referrer=utm_source%3Dwidget_icon%26utm_medium%3D");
      ((StringBuilder)localObject).append(paramContext.getPackageName());
      ((StringBuilder)localObject).append("%26utm_campaign%3Dwidget");
      localObject = new Intent("android.intent.action.VIEW", Uri.parse(((StringBuilder)localObject).toString()));
      ((Intent)localObject).setFlags(268435456);
    }
    try
    {
      paramContext.startActivity((Intent)localObject);
      return;
    }
    catch (Exception paramContext) {}
  }
  
  public static void startJobScheduler(Context paramContext)
  {
    JobScheduler localJobScheduler = (JobScheduler)paramContext.getSystemService("jobscheduler");
    if (Build.VERSION.SDK_INT >= 24)
    {
      if (localJobScheduler.getPendingJob(JOB_ID) == null) {}
    }
    else {
      localJobScheduler.cancel(JOB_ID);
    }
    paramContext = new ComponentName(paramContext, FeedlyWidgetJobService.class);
    paramContext = new JobInfo.Builder(JOB_ID, paramContext);
    if (Build.VERSION.SDK_INT >= 26) {
      paramContext.setRequiresBatteryNotLow(true);
    }
    if (Build.VERSION.SDK_INT >= 24) {
      paramContext.setPeriodic(JOB_INTERVAL, FLEX_INTERVAL);
    } else {
      paramContext.setPeriodic(JOB_INTERVAL);
    }
    paramContext.setRequiredNetworkType(1);
    paramContext.setRequiresCharging(false);
    localJobScheduler.schedule(paramContext.build());
  }
  
  public static void stopJobScheduler(Context paramContext)
  {
    ((JobScheduler)paramContext.getSystemService("jobscheduler")).cancel(JOB_ID);
  }
  
  @SuppressLint({"NewApi"})
  public void onAppWidgetOptionsChanged(Context paramContext, AppWidgetManager paramAppWidgetManager, int paramInt, Bundle paramBundle)
  {
    super.onAppWidgetOptionsChanged(paramContext, paramAppWidgetManager, paramInt, paramBundle);
    try
    {
      new FeedlyWidgetUpdaterTask(paramContext, paramInt, false)
      {
        protected void onPostExecute(Boolean paramAnonymousBoolean) {}
      }.execute(new Void[0]);
      return;
    }
    catch (Exception paramContext)
    {
      sLog.e(new Object[] { ">>> widget:onAppWidgetOptionsChanged:failed -- ", paramContext });
    }
  }
  
  public void onDeleted(Context paramContext, int[] paramArrayOfInt)
  {
    super.onDeleted(paramContext, paramArrayOfInt);
    int j = paramArrayOfInt.length;
    int i = 0;
    while (i < j)
    {
      FeedlyWidgetData.getWidgetData(paramContext, paramArrayOfInt[i]).delete(paramContext);
      i += 1;
    }
  }
  
  public void onDisabled(Context paramContext)
  {
    super.onDisabled(paramContext);
  }
  
  public void onEnabled(Context paramContext)
  {
    super.onEnabled(paramContext);
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getAction();
    this.fIntent = paramIntent;
    if ("android.intent.action.BOOT_COMPLETED".equals(paramIntent.getAction()))
    {
      paramIntent = FeedlyWidgetData.getConfiguredWidgetIds(paramContext);
      if ((paramIntent != null) && (paramIntent.length > 0)) {
        startJobScheduler(paramContext);
      }
    }
    else if ((str.startsWith("com.devhd.feedly.widget")) && (paramIntent.getExtras() != null))
    {
      paramIntent = FeedlyWidgetUtils.getAppWidgetIds(paramIntent);
      if ((paramIntent != null) && (paramIntent.length > 0)) {
        onUpdate(paramContext, AppWidgetManager.getInstance(paramContext), paramIntent);
      }
    }
    else
    {
      super.onReceive(paramContext, paramIntent);
    }
  }
  
  public void onUpdate(Context paramContext, AppWidgetManager paramAppWidgetManager, int[] paramArrayOfInt)
  {
    if (paramArrayOfInt != null)
    {
      if (paramArrayOfInt.length == 0) {
        return;
      }
      paramAppWidgetManager = this.fIntent.getAction();
      if ("android.appwidget.action.APPWIDGET_UPDATE".equals(paramAppWidgetManager))
      {
        askUpdateWidgets(paramContext, paramArrayOfInt);
        return;
      }
      if (paramAppWidgetManager.equals(FeedlyWidgetUtils.INTENT_NEXT))
      {
        askTransitionNext(paramContext, paramArrayOfInt[0]);
        return;
      }
      if (paramAppWidgetManager.equals(FeedlyWidgetUtils.INTENT_REFRESH))
      {
        askUpdateWidgets(paramContext, paramArrayOfInt);
        return;
      }
      if (paramAppWidgetManager.equals(FeedlyWidgetUtils.INTENT_TRIGGER_REFRESH))
      {
        askUpdateWidgets(paramContext, paramArrayOfInt);
        return;
      }
      if (paramAppWidgetManager.equals(FeedlyWidgetUtils.INTENT_OPEN))
      {
        openArticle(paramContext, paramArrayOfInt[0]);
        return;
      }
      if (paramAppWidgetManager.equals(FeedlyWidgetUtils.INTENT_OPEN_APP))
      {
        openApp(paramContext, paramArrayOfInt[0]);
        return;
      }
      if (paramAppWidgetManager.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
        askUpdateWidgets(paramContext, paramArrayOfInt);
      }
      return;
    }
  }
  
  public void openArticle(Context paramContext, int paramInt)
  {
    Object localObject1 = FeedlyWidgetData.getWidgetData(paramContext, paramInt);
    Object localObject2 = ((FeedlyWidgetData)localObject1).getFeedlyEntries();
    int i = ((FeedlyWidgetData)localObject1).getCurrentArticlePos();
    if (i >= 0) {}
    try
    {
      localObject2 = (FeedlyEntry)((LinkedList)localObject2).get(i);
      localObject1 = new Intent("com.devhd.feedly.SHOW");
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("https://feedly.com/i/entry/");
      localStringBuilder.append(((FeedlyEntry)localObject2).getId());
      localObject2 = localStringBuilder.toString();
      ((Intent)localObject1).setData(Uri.parse((String)localObject2));
      ((Intent)localObject1).setComponent(new ComponentName("com.devhd.feedly", "com.devhd.feedly.Main"));
      ((Intent)localObject1).putExtra("com.devhd.feedly.feedly_start", (String)localObject2);
      ((Intent)localObject1).putExtra("com.devhd.feedly.source", "widget");
      ((Intent)localObject1).setFlags(268435456);
      paramContext.startActivity((Intent)localObject1);
      return;
    }
    catch (Exception paramContext) {}
    sLog.e(new Object[] { "widget[", Integer.valueOf(paramInt), "]:no current article position!" });
    return;
  }
}
