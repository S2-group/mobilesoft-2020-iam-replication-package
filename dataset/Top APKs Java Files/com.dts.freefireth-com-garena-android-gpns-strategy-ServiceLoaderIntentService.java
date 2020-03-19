package com.garena.android.gpns.strategy;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import com.garena.android.gpns.GNotificationService;
import com.garena.android.gpns.utility.AppLogger;
import java.util.Iterator;
import java.util.List;

public class ServiceLoaderIntentService
  extends Service
  implements ServiceLoader.ServiceStatusListener
{
  public static final String SERVICE_VERSION = "com.garena.sdk.push.version";
  private PowerManager.WakeLock wakeLock;
  
  public ServiceLoaderIntentService() {}
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onOtherServiceRunning(ComponentName paramComponentName)
  {
    AppLogger.d("don't start my own service " + getPackageName());
    if (this.wakeLock.isHeld()) {
      this.wakeLock.release();
    }
    stopSelf();
  }
  
  public void onServiceStarted()
  {
    AppLogger.d("start my own service via service loader " + getPackageName());
    Intent localIntent = new Intent(getApplicationContext(), GNotificationService.class);
    getApplicationContext().startService(localIntent);
    if (this.wakeLock.isHeld()) {
      this.wakeLock.release();
    }
    stopSelf();
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    this.wakeLock = ((PowerManager)getApplicationContext().getSystemService("power")).newWakeLock(1, "ALARM_RECEIVER_WAKE_LOCK");
    this.wakeLock.acquire(60000L);
    this.wakeLock.setReferenceCounted(false);
    paramInt2 = 1;
    paramIntent = getPackageManager().getInstalledApplications(128).iterator();
    for (;;)
    {
      paramInt1 = paramInt2;
      ApplicationInfo localApplicationInfo;
      Integer localInteger;
      if (paramIntent.hasNext())
      {
        localApplicationInfo = (ApplicationInfo)paramIntent.next();
        if (localApplicationInfo.metaData == null) {
          continue;
        }
        localInteger = Integer.valueOf(localApplicationInfo.metaData.getInt("com.garena.sdk.push.version", -1));
        if ((localInteger.intValue() > 4) || ((localInteger.intValue() == 4) && (CompetitiveBootStrategy.isSuperior(localApplicationInfo.packageName, getPackageName()))))
        {
          AppLogger.d("we have a better choice now:" + localApplicationInfo.packageName);
          paramInt1 = 0;
        }
      }
      else
      {
        if (paramInt1 == 0) {
          break;
        }
        AppLogger.d("i am the better choice now:" + getPackageName());
        new ServiceLoader(getApplicationContext(), this).loadService();
        return 2;
      }
      AppLogger.d("service_version:" + localInteger + " " + localApplicationInfo.packageName);
    }
    AppLogger.d("i am quitting:" + getPackageName());
    if (this.wakeLock.isHeld()) {
      this.wakeLock.release();
    }
    stopSelf();
    return 2;
  }
}
