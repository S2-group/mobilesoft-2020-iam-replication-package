package com.advancedprocessmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.IPackageDataObserver;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.widget.Toast;
import com.tools.tools.k;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class AlarmReceiver
  extends BroadcastReceiver
{
  int a = 0;
  int b = 0;
  int c = 0;
  
  public AlarmReceiver() {}
  
  public static void a(Context paramContext)
  {
    AlarmManager localAlarmManager = (AlarmManager)paramContext.getSystemService("alarm");
    Object localObject = new Intent(paramContext, AlarmReceiver.class);
    ((Intent)localObject).putExtra("index", 0);
    ((Intent)localObject).setData(Uri.parse("Alert://widget/id/0"));
    localObject = PendingIntent.getBroadcast(paramContext, 0, (Intent)localObject, 0);
    localAlarmManager.cancel((PendingIntent)localObject);
    paramContext = paramContext.getSharedPreferences(paramContext.getPackageName(), 0);
    int i;
    if (paramContext.getBoolean("isAutoOptimization", false)) {
      switch (paramContext.getInt("interval", 2))
      {
      default: 
        i = 0;
      }
    }
    for (;;)
    {
      localAlarmManager.setRepeating(1, System.currentTimeMillis(), i, (PendingIntent)localObject);
      return;
      i = 600000;
      continue;
      i = 1800000;
      continue;
      i = 3600000;
      continue;
      i = 7200000;
      continue;
      i = 10800000;
    }
  }
  
  public static void b(Context paramContext)
  {
    AlarmManager localAlarmManager = (AlarmManager)paramContext.getSystemService("alarm");
    Object localObject = new Intent(paramContext, AlarmReceiver.class);
    ((Intent)localObject).setData(Uri.parse("Alert://widget/id/1"));
    ((Intent)localObject).putExtra("index", 1);
    localObject = PendingIntent.getBroadcast(paramContext, 0, (Intent)localObject, 0);
    localAlarmManager.cancel((PendingIntent)localObject);
    paramContext = paramContext.getSharedPreferences(paramContext.getPackageName(), 0);
    int i;
    if (paramContext.getBoolean("isCacheOptimization", false)) {
      switch (paramContext.getInt("cacheinterval", 2))
      {
      default: 
        i = 0;
      }
    }
    for (;;)
    {
      localAlarmManager.setRepeating(1, System.currentTimeMillis(), i, (PendingIntent)localObject);
      return;
      i = 21600000;
      continue;
      i = 43200000;
      continue;
      i = 86400000;
    }
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    switch (paramIntent.getIntExtra("index", -1))
    {
    }
    Object localObject1;
    Object localObject2;
    Object localObject3;
    for (;;)
    {
      return;
      k.a(paramContext.getPackageManager());
      paramIntent = bw.b(paramContext);
      if (paramContext.getSharedPreferences(paramContext.getPackageName(), 0).getBoolean("autoBoostNotification", true))
      {
        Toast.makeText(paramContext, paramContext.getString(2131099788, new Object[] { paramIntent[0], paramIntent[1] }), 1).show();
        return;
        try
        {
          this.b = 0;
          this.c = 0;
          paramIntent = paramContext.getPackageManager();
          localObject1 = new CountDownLatch(1);
          localObject2 = paramIntent.getClass().getMethod("getPackageSizeInfo", new Class[] { String.class, IPackageStatsObserver.class });
          Object localObject4 = paramIntent.getInstalledPackages(8704);
          localObject3 = ((PackageInfo)((List)localObject4).get(((List)localObject4).size() - 1)).packageName;
          localObject4 = ((List)localObject4).iterator();
          for (;;)
          {
            if (((Iterator)localObject4).hasNext())
            {
              ((Method)localObject2).invoke(paramIntent, new Object[] { ((PackageInfo)((Iterator)localObject4).next()).packageName, new b(this, (String)localObject3, (CountDownLatch)localObject1) });
              continue;
              if (!paramContext.getSharedPreferences(paramContext.getPackageName(), 0).getBoolean("autoCacheNotification", true)) {
                break;
              }
            }
          }
        }
        catch (Exception paramIntent)
        {
          paramIntent.printStackTrace();
        }
      }
    }
    for (;;)
    {
      Toast.makeText(paramContext, paramContext.getString(2131099787, new Object[] { Integer.valueOf(this.b), Integer.valueOf(this.c) }), 1).show();
      return;
      ((CountDownLatch)localObject1).await();
      localObject1 = new StatFs(Environment.getDataDirectory().getPath());
      long l1 = ((StatFs)localObject1).getBlockSize();
      long l2 = ((StatFs)localObject1).getBlockCount();
      localObject1 = new long[5];
      localObject1[0] = Long.valueOf(Long.valueOf(2147483647L).longValue()).longValue();
      localObject1[1] = Long.valueOf(Long.valueOf(2147483647L).longValue() * 20L).longValue();
      localObject1[2] = Long.valueOf(Long.valueOf(2147483647L).longValue() * 200L).longValue();
      localObject1[3] = Long.valueOf(l2 * l1 - 1L).longValue();
      localObject1[4] = Long.valueOf(Long.MAX_VALUE).longValue();
      localObject2 = Long.TYPE;
      localObject2 = paramIntent.getClass().getMethod("freeStorageAndNotify", new Class[] { localObject2, IPackageDataObserver.class });
      ((Method)localObject2).setAccessible(true);
      localObject3 = new Object[2];
      localObject3[0] = Long.valueOf(localObject1[0]);
      this.a = 0;
      localObject3[1] = new c(this, (Object[])localObject3, (long[])localObject1, (Method)localObject2, paramIntent);
      ((Method)localObject2).invoke(paramIntent, (Object[])localObject3);
    }
  }
}
