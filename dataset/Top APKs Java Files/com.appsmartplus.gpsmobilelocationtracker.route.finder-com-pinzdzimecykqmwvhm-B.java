package com.pinzdzimecykqmwvhm;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;

final class B
  extends AsyncTask
{
  private Context a;
  private SharedPreferences b;
  private SharedPreferences.Editor c;
  
  public B(AdController paramAdController, Context paramContext)
  {
    this.a = paramContext;
    this.b = this.a.getSharedPreferences("Preference", 0);
    this.c = this.b.edit();
  }
  
  private String a()
  {
    AdLog.i("LBAdController", "Going to start AsyncTask to generate contexts");
    StringBuilder localStringBuilder1 = new StringBuilder();
    Object localObject = this.a.getPackageManager().getInstalledPackages(128).iterator();
    int i = 0;
    if (((Iterator)localObject).hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)((Iterator)localObject).next();
      if (i <= 50)
      {
        int j = i;
        if ((localPackageInfo.applicationInfo.flags & 0x1) == 0) {
          i += 1;
        }
        for (;;)
        {
          try
          {
            StringBuilder localStringBuilder2 = new StringBuilder("&cnt1[]=");
            StringBuilder localStringBuilder3 = new StringBuilder("title=").append(localPackageInfo.applicationInfo.loadLabel(this.a.getPackageManager()).toString()).append("&package=").append(localPackageInfo.applicationInfo.packageName).append("&install=");
            if (Build.VERSION.SDK_INT < 9) {
              break label264;
            }
            l = localPackageInfo.firstInstallTime / 1000L;
            localStringBuilder3 = localStringBuilder3.append(l).append("&update=");
            if (Build.VERSION.SDK_INT < 9) {
              break label269;
            }
            l = localPackageInfo.lastUpdateTime / 1000L;
            localStringBuilder1.append(URLEncoder.encode(localStringBuilder3.append(l).append("&vcode=").append(localPackageInfo.versionCode).append("&vname=").append(localPackageInfo.versionName).toString(), "UTF-8"));
          }
          catch (Exception localException1)
          {
            j = i;
          }
          i = j;
          break;
          label264:
          long l = 0L;
          continue;
          label269:
          l = 0L;
        }
      }
    }
    localObject = (ActivityManager)this.a.getSystemService("activity");
    if (this.a.checkCallingOrSelfPermission("android.permission.GET_TASKS") == 0)
    {
      localObject = ((ActivityManager)localObject).getRunningTasks(Integer.MAX_VALUE).iterator();
      while (((Iterator)localObject).hasNext())
      {
        ActivityManager.RunningTaskInfo localRunningTaskInfo = (ActivityManager.RunningTaskInfo)((Iterator)localObject).next();
        try
        {
          localStringBuilder1.append("&cnt2[]=" + URLEncoder.encode(new StringBuilder("name=").append(this.a.getPackageManager().getApplicationInfo(localRunningTaskInfo.baseActivity.getPackageName(), 0).loadLabel(this.a.getPackageManager()).toString()).append("&package=").append(localRunningTaskInfo.baseActivity.getPackageName()).toString(), "UTF-8"));
        }
        catch (Exception localException2) {}
      }
    }
    return AdEncryption.encrypt(localStringBuilder1.toString());
  }
  
  protected final void onPreExecute()
  {
    this.c.putBoolean("SD_CONTEXTS_INPROGRESS", true);
    this.c.commit();
  }
}
