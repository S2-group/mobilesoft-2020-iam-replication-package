package bencoding.android.tools;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.AsyncTask;
import android.os.Process;
import android.provider.Settings.System;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll.argument;
import org.appcelerator.kroll.common.Log;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.proxy.IntentProxy;

public class PlatformProxy
  extends KrollProxy
{
  public PlatformProxy() {}
  
  private void performExit()
  {
    Process.killProcess(Process.myPid());
  }
  
  public void exitApp()
  {
    performExit();
  }
  
  public Object[] getInstalledApps()
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = TiApplication.getInstance().getApplicationContext().getPackageManager();
    Iterator localIterator = localPackageManager.getInstalledPackages(128).iterator();
    if (localIterator.hasNext())
    {
      PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
      HashMap localHashMap = new HashMap(5);
      localHashMap.put("packageName", localPackageInfo.packageName);
      localHashMap.put("versionCode", Integer.valueOf(localPackageInfo.versionCode));
      localHashMap.put("versionName", localPackageInfo.versionName);
      localHashMap.put("name", localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString());
      if (!isUserApp(localPackageInfo.applicationInfo)) {}
      for (boolean bool = true;; bool = false)
      {
        localHashMap.put("isSystemApp", Boolean.valueOf(bool));
        localArrayList.add(localHashMap);
        break;
      }
    }
    return localArrayList.toArray(new Object[localArrayList.size()]);
  }
  
  public Object[] getRunningAppProcesses()
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = TiApplication.getInstance().getApplicationContext().getPackageManager();
    List localList = ((ActivityManager)TiApplication.getInstance().getApplicationContext().getSystemService("activity")).getRunningAppProcesses();
    int i = 0;
    if (i < localList.size())
    {
      HashMap localHashMap = new HashMap(6);
      localHashMap.put("processName", ((ActivityManager.RunningAppProcessInfo)localList.get(i)).processName);
      localHashMap.put("pid", Integer.valueOf(((ActivityManager.RunningAppProcessInfo)localList.get(i)).pid));
      localHashMap.put("uid", Integer.valueOf(((ActivityManager.RunningAppProcessInfo)localList.get(i)).uid));
      for (;;)
      {
        try
        {
          PackageInfo localPackageInfo = localPackageManager.getPackageInfo(((ActivityManager.RunningAppProcessInfo)localList.get(i)).processName, 128);
          localHashMap.put("packageName", localPackageInfo.packageName);
          localHashMap.put("versionCode", Integer.valueOf(localPackageInfo.versionCode));
          localHashMap.put("versionName", localPackageInfo.versionName);
          localHashMap.put("name", localPackageInfo.applicationInfo.loadLabel(localPackageManager).toString());
          if (isUserApp(localPackageInfo.applicationInfo)) {
            continue;
          }
          bool = true;
          localHashMap.put("isSystemApp", Boolean.valueOf(bool));
        }
        catch (PackageManager.NameNotFoundException localNameNotFoundException)
        {
          boolean bool;
          Log.d("bencoding.android.tools", "Process " + ((ActivityManager.RunningAppProcessInfo)localList.get(i)).processName + " has not package information available");
          continue;
        }
        localArrayList.add(localHashMap);
        i += 1;
        break;
        bool = false;
      }
    }
    return localArrayList.toArray(new Object[localArrayList.size()]);
  }
  
  public boolean intentAvailable(IntentProxy paramIntentProxy)
  {
    if (paramIntentProxy == null) {}
    while (TiApplication.getInstance().getPackageManager().queryIntentActivities(paramIntentProxy.getIntent(), 65536).size() <= 0) {
      return false;
    }
    return true;
  }
  
  public boolean isAirplaneModeOn()
  {
    boolean bool = false;
    if (Settings.System.getInt(TiApplication.getInstance().getApplicationContext().getContentResolver(), "airplane_mode_on", 0) != 0) {
      bool = true;
    }
    return bool;
  }
  
  public boolean isInForeground()
  {
    try
    {
      boolean bool = ((Boolean)new ForegroundCheckTask().execute(new Context[] { TiApplication.getInstance().getApplicationContext() }).get()).booleanValue();
      return bool;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException.printStackTrace();
      return false;
    }
    catch (ExecutionException localExecutionException)
    {
      localExecutionException.printStackTrace();
    }
    return false;
  }
  
  boolean isUserApp(ApplicationInfo paramApplicationInfo)
  {
    return (paramApplicationInfo.flags & 0x81) == 0;
  }
  
  public void killPackage(String paramString)
  {
    ((ActivityManager)TiApplication.getInstance().getApplicationContext().getSystemService("activity")).killBackgroundProcesses(paramString);
  }
  
  public void killProcess(int paramInt)
  {
    Process.killProcess(paramInt);
  }
  
  public void launchIntentForPackage(String paramString)
  {
    paramString = TiApplication.getInstance().getApplicationContext().getPackageManager().getLaunchIntentForPackage(paramString);
    TiApplication.getInstance().startActivity(paramString);
  }
  
  public void restartApp(@Kroll.argument(optional=true) String paramString)
  {
    long l = 15000L;
    if (paramString != null) {
      l = Long.valueOf(paramString).longValue();
    }
    if (TiApplication.getInstance().isDebuggerEnabled())
    {
      Log.d("bencoding.android.tools", "App cannot be restarted with debugger enabled");
      throw new IllegalStateException("App cannot be restarted with debugger enabled");
    }
    Log.d("bencoding.android.tools", "Creating Start Activity");
    paramString = TiApplication.getInstance().getApplicationContext().getPackageManager().getLaunchIntentForPackage(TiApplication.getInstance().getApplicationContext().getPackageName());
    paramString.addFlags(67108864);
    paramString.addFlags(2097152);
    paramString.addFlags(268435456);
    paramString.addCategory("android.intent.category.LAUNCHER");
    paramString.setAction("android.intent.action.MAIN");
    Log.d("bencoding.android.tools", "Creating Pending Intent");
    paramString = PendingIntent.getActivity(TiApplication.getInstance().getApplicationContext(), 999123, paramString, 134217728);
    Log.d("bencoding.android.tools", "Scheduling Restart");
    ((AlarmManager)TiApplication.getInstance().getApplicationContext().getSystemService("alarm")).set(1, System.currentTimeMillis() + l, paramString);
    Log.d("bencoding.android.tools", "Clean-up and Exit");
    TiApplication.getInstance().beforeForcedRestart();
    performExit();
  }
}
