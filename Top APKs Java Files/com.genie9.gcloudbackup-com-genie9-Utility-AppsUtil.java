package com.genie9.Utility;

import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import com.genie9.GService.AlarmReceiver;
import com.genie9.GService.BackupSchedulerService;
import com.genie9.Managers.UserManager;
import com.genie9.subscribtion.Base64DecoderException;
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppsUtil
{
  public AppsUtil() {}
  
  public static Drawable getAppIcon(PackageManager paramPackageManager, String paramString)
  {
    try
    {
      paramPackageManager = paramPackageManager.getApplicationIcon(paramString);
      return paramPackageManager;
    }
    catch (PackageManager.NameNotFoundException paramPackageManager)
    {
      paramPackageManager.printStackTrace();
    }
    return null;
  }
  
  public static ArrayList<String[]> getAppsInstalled(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator1 = paramContext.getPackageManager().getInstalledApplications(0).iterator();
    while (localIterator1.hasNext())
    {
      Object localObject = (ApplicationInfo)localIterator1.next();
      if ((!((ApplicationInfo)localObject).sourceDir.startsWith("/system")) && (((ApplicationInfo)localObject).flags != 1))
      {
        String str = ((ApplicationInfo)localObject).loadLabel(paramContext.getPackageManager()).toString();
        localObject = ((ApplicationInfo)localObject).packageName;
        int j = 0;
        Iterator localIterator2 = G9Constant.arlExcludedPackages.iterator();
        do
        {
          i = j;
          if (!localIterator2.hasNext()) {
            break;
          }
        } while (!((String)localObject).contains((String)localIterator2.next()));
        int i = 1;
        if (i == 0) {
          localArrayList.add(new String[] { localObject, str });
        }
      }
    }
    return localArrayList;
  }
  
  public static ArrayList<String> getPackagesAppsInstalled(Context paramContext)
  {
    Object localObject = getAppsInstalled(paramContext);
    paramContext = new ArrayList();
    localObject = ((ArrayList)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      paramContext.add(((String[])localObject.next())[0]);
    }
    return paramContext;
  }
  
  public static void registerFCMTokenOnG9ServerIfNeeded(Context paramContext)
  {
    final String str1 = FirebaseInstanceId.getInstance().getToken();
    String str2 = SharedPrefUtil.getFCMToken(paramContext);
    if ((!GSUtilities.isNullOrEmpty(str1)) && (!str2.equals(str1))) {
      new Thread(new Runnable()
      {
        public void run()
        {
          new UserManager(this.val$context).UpdateUserGoogleDeviceID(str1);
        }
      }).start();
    }
  }
  
  public static String sDecodeBase64(String paramString)
  {
    try
    {
      paramString = new String(Base64.decode(paramString.getBytes()));
      return paramString;
    }
    catch (Base64DecoderException paramString)
    {
      paramString.printStackTrace();
    }
    return "";
  }
  
  public static void startJobScheduled(Context paramContext, long paramLong)
  {
    startJobScheduled(paramContext, paramLong, false);
  }
  
  public static void startJobScheduled(Context paramContext, long paramLong, boolean paramBoolean)
  {
    G9Log localG9Log = new G9Log();
    localG9Log.prepareLogSession(AlarmReceiver.class);
    localG9Log.Log("------- Schedule :: startJobScheduled :: Start");
    JobScheduler localJobScheduler;
    if (Build.VERSION.SDK_INT >= 24)
    {
      localJobScheduler = (JobScheduler)paramContext.getSystemService("jobscheduler");
      if (!paramBoolean) {
        break label226;
      }
      localJobScheduler.cancelAll();
      bool = false;
      localG9Log.Log("------- Schedule :: startJobScheduled :: BACKUP_JOB_ID  = " + 10);
      localG9Log.Log("------- Schedule :: startJobScheduled :: ForceStart = " + paramBoolean + " , isSchedulerRunning = " + bool);
      if (!bool)
      {
        localG9Log.Log("------- Schedule :: startJobScheduled :: setting schedule");
        paramContext = new JobInfo.Builder(10, new ComponentName(paramContext, BackupSchedulerService.class));
        paramContext.setRequiredNetworkType(1);
        paramContext.setRequiresCharging(true);
        paramContext.setPersisted(true);
        paramContext.setMinimumLatency(paramLong);
        paramContext.setOverrideDeadline(10800000L);
        localJobScheduler.schedule(paramContext.build());
        int i = localJobScheduler.schedule(paramContext.build());
        localG9Log.Log("------- Schedule :: startJobScheduled :: settings schedule result  = " + i);
      }
    }
    return;
    label226:
    if (localJobScheduler.getPendingJob(10) != null) {}
    for (boolean bool = true;; bool = false) {
      break;
    }
  }
}
