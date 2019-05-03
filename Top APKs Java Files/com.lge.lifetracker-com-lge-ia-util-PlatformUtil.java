package com.lge.ia.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.util.Iterator;
import java.util.List;

public class PlatformUtil
{
  public static final String ACTION_NAME_CHANGED = "com.lge.ia.action.CHANGED";
  public static final String ACTION_NAME_RESTARTED = "com.lge.ia.action.RESTARTED";
  public static final String KEY_CALLER = "Caller";
  public static final String KEY_EVENT = "Event";
  public static final String KEY_PACKAGE_NAME = "PackageName";
  public static final String KEY_TASK_NAME = "TaskName";
  public static final String LIA_SERVICE = "com.lge.ia";
  private static final String TAG = "PlatformUtil";
  private static Boolean isDebuggable;
  
  public PlatformUtil() {}
  
  public static Integer getVersionCode(Context paramContext, String paramString)
  {
    Object localObject = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    do
    {
      if (!((Iterator)localObject).hasNext())
      {
        paramContext = TAG;
        localObject = new StringBuilder("getVersionCode - ");
        ((StringBuilder)localObject).append(paramString);
        ((StringBuilder)localObject).append(" is not found");
        Log.i(paramContext, ((StringBuilder)localObject).toString());
        return null;
      }
      paramContext = (PackageInfo)((Iterator)localObject).next();
    } while (!paramContext.packageName.equals(paramString));
    localObject = TAG;
    StringBuilder localStringBuilder = new StringBuilder("getVersionCode - ");
    localStringBuilder.append(paramString);
    localStringBuilder.append(" version name : ");
    localStringBuilder.append(paramContext.versionName);
    localStringBuilder.append(" / version code : ");
    localStringBuilder.append(paramContext.versionCode);
    Log.i((String)localObject, localStringBuilder.toString());
    return Integer.valueOf(paramContext.versionCode);
  }
  
  public static String getVersionName(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    Object localObject;
    do
    {
      if (!paramContext.hasNext())
      {
        paramContext = TAG;
        localObject = new StringBuilder("getVersionName - ");
        ((StringBuilder)localObject).append(paramString);
        ((StringBuilder)localObject).append(" is not found");
        Log.i(paramContext, ((StringBuilder)localObject).toString());
        return null;
      }
      localObject = (PackageInfo)paramContext.next();
    } while (!((PackageInfo)localObject).packageName.equals(paramString));
    return ((PackageInfo)localObject).versionName;
  }
  
  public static Boolean isDebuggable(Context paramContext)
  {
    if (isDebuggable == null) {
      try
      {
        localObject = paramContext.getPackageManager();
        paramContext = paramContext.getPackageName();
        boolean bool = false;
        if ((((PackageManager)localObject).getPackageInfo(paramContext, 0).applicationInfo.flags & 0x2) != 0) {
          bool = true;
        }
        isDebuggable = Boolean.valueOf(bool);
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        Object localObject = TAG;
        StringBuilder localStringBuilder = new StringBuilder("isDebuggable() : NameNotFoundException - ");
        localStringBuilder.append(paramContext.getMessage());
        Log.w((String)localObject, localStringBuilder.toString());
        isDebuggable = Boolean.FALSE;
      }
    }
    return isDebuggable;
  }
  
  public static Boolean isPackageInstalled(Context paramContext, String paramString)
  {
    Object localObject = paramContext.getPackageManager().getInstalledPackages(0).iterator();
    do
    {
      if (!((Iterator)localObject).hasNext())
      {
        paramContext = TAG;
        localObject = new StringBuilder("isPackageInstalled - ");
        ((StringBuilder)localObject).append(paramString);
        ((StringBuilder)localObject).append(" is not found");
        Log.i(paramContext, ((StringBuilder)localObject).toString());
        return Boolean.FALSE;
      }
      paramContext = (PackageInfo)((Iterator)localObject).next();
    } while (!paramContext.packageName.equals(paramString));
    localObject = TAG;
    StringBuilder localStringBuilder = new StringBuilder("isPackageInstalled - ");
    localStringBuilder.append(paramString);
    localStringBuilder.append(" version name : ");
    localStringBuilder.append(paramContext.versionName);
    localStringBuilder.append(" / version code : ");
    localStringBuilder.append(paramContext.versionCode);
    Log.i((String)localObject, localStringBuilder.toString());
    return Boolean.TRUE;
  }
  
  public static void requestReconnectMe(Context paramContext, String paramString1, String paramString2, long paramLong)
  {
    String str = paramContext.getPackageName();
    Object localObject = TAG;
    StringBuilder localStringBuilder = new StringBuilder("requestReconnectMe() : ");
    localStringBuilder.append(str);
    localStringBuilder.append(", ");
    localStringBuilder.append(paramString1);
    Log.d((String)localObject, localStringBuilder.toString());
    localObject = new Intent("com.lge.ia.action.CHANGED");
    ((Intent)localObject).setPackage("com.lge.ia");
    ((Intent)localObject).putExtra("PackageName", str);
    ((Intent)localObject).putExtra("TaskName", paramString1);
    ((Intent)localObject).putExtra("Caller", paramString2);
    if (paramLong <= 0L)
    {
      paramString1 = TAG;
      paramString2 = new StringBuilder("requestReconnectMe() : ");
      paramString2.append(str);
      paramString2.append(" is reconnected right now by LIA.");
      Log.i(paramString1, paramString2.toString());
      sendBroadcast(paramContext, (Intent)localObject);
      return;
    }
    paramString1 = TAG;
    paramString2 = new StringBuilder("requestReconnectMe() : ");
    paramString2.append(str);
    paramString2.append(" will be reconnected in about ");
    paramString2.append(paramLong);
    paramString2.append("ms by LIA.");
    Log.i(paramString1, paramString2.toString());
    sendPendingBroadcast(paramContext, (Intent)localObject, paramLong);
  }
  
  public static void requestRegisterMe(Context paramContext, String paramString, long paramLong)
  {
    String str = paramContext.getPackageName();
    Object localObject = TAG;
    StringBuilder localStringBuilder = new StringBuilder("requestRegisterMe() : ");
    localStringBuilder.append(str);
    localStringBuilder.append(", ");
    localStringBuilder.append(paramString);
    Log.d((String)localObject, localStringBuilder.toString());
    localObject = new Intent("com.lge.ia.action.RESTARTED");
    ((Intent)localObject).setPackage("com.lge.ia");
    ((Intent)localObject).putExtra("Caller", paramString);
    ((Intent)localObject).putExtra("PackageName", str);
    if (paramLong <= 0L)
    {
      paramString = TAG;
      localStringBuilder = new StringBuilder("requestRegisterMe() : ");
      localStringBuilder.append(str);
      localStringBuilder.append(" is restarted right now by LIA.");
      Log.i(paramString, localStringBuilder.toString());
      sendBroadcast(paramContext, (Intent)localObject);
      return;
    }
    paramString = TAG;
    localStringBuilder = new StringBuilder("requestRegisterMe() : ");
    localStringBuilder.append(str);
    localStringBuilder.append(" will be restarted in about ");
    localStringBuilder.append(paramLong);
    localStringBuilder.append("ms by LIA.");
    Log.i(paramString, localStringBuilder.toString());
    sendPendingBroadcast(paramContext, (Intent)localObject, paramLong);
  }
  
  public static void restartSelfService(Context paramContext, long paramLong)
  {
    String str = paramContext.getPackageName();
    Intent localIntent = new Intent(str);
    localIntent.setPackage(str);
    if (paramLong <= 0L)
    {
      localObject = TAG;
      StringBuilder localStringBuilder = new StringBuilder("restartSelfService() : Trying to restart ... ");
      localStringBuilder.append(str);
      Log.i((String)localObject, localStringBuilder.toString());
      startService(paramContext, localIntent);
      return;
    }
    str = TAG;
    Object localObject = new StringBuilder("restartSelfService() : This service will be restarted in about ");
    ((StringBuilder)localObject).append(paramLong);
    ((StringBuilder)localObject).append("ms.");
    Log.i(str, ((StringBuilder)localObject).toString());
    startPendingService(paramContext, localIntent, paramLong);
  }
  
  public static void restartService(Context paramContext, Intent paramIntent, long paramLong)
  {
    if (paramLong <= 0L)
    {
      str = TAG;
      localStringBuilder = new StringBuilder("restartService() : ");
      localStringBuilder.append(paramIntent);
      localStringBuilder.append(" is restarted right now.");
      Log.i(str, localStringBuilder.toString());
      startService(paramContext, paramIntent);
      return;
    }
    String str = TAG;
    StringBuilder localStringBuilder = new StringBuilder("restartService() : ");
    localStringBuilder.append(paramIntent);
    localStringBuilder.append(" will be restarted in about ");
    localStringBuilder.append(paramLong);
    localStringBuilder.append("ms.");
    Log.i(str, localStringBuilder.toString());
    startPendingService(paramContext, paramIntent, paramLong);
  }
  
  private static void sendBroadcast(Context paramContext, Intent paramIntent)
  {
    try
    {
      paramContext.sendBroadcast(paramIntent);
      return;
    }
    catch (Exception paramContext)
    {
      str = TAG;
      localStringBuilder = new StringBuilder("sendBroadcast(");
      localStringBuilder.append(paramIntent);
      localStringBuilder.append(") : fail");
      Log.w(str, localStringBuilder.toString(), paramContext);
      return;
    }
    catch (SecurityException paramContext)
    {
      String str = TAG;
      StringBuilder localStringBuilder = new StringBuilder("sendBroadcast(");
      localStringBuilder.append(paramIntent);
      localStringBuilder.append(") : fail");
      Log.w(str, localStringBuilder.toString(), paramContext);
    }
  }
  
  private static void sendPendingBroadcast(Context paramContext, Intent paramIntent, long paramLong)
  {
    paramIntent = PendingIntent.getBroadcast(paramContext, 0, paramIntent, 0);
    ((AlarmManager)paramContext.getSystemService("alarm")).set(1, System.currentTimeMillis() + paramLong, paramIntent);
  }
  
  public static void startLIAService(Context paramContext, String paramString1, String paramString2, long paramLong)
  {
    Intent localIntent = new Intent("com.lge.ia");
    localIntent.setPackage("com.lge.ia");
    localIntent.putExtra("Caller", paramString1);
    localIntent.putExtra("Event", paramString2);
    if (paramLong <= 0L)
    {
      Log.i(TAG, "startLIAService() : Trying to start LIA service ...");
      startService(paramContext, localIntent);
      return;
    }
    paramString1 = TAG;
    paramString2 = new StringBuilder("startLIAService() : LIA service will be restarted in about ");
    paramString2.append(paramLong);
    paramString2.append("ms.");
    Log.i(paramString1, paramString2.toString());
    startPendingService(paramContext, localIntent, paramLong);
  }
  
  private static void startPendingService(Context paramContext, Intent paramIntent, long paramLong)
  {
    paramIntent = PendingIntent.getService(paramContext, 0, paramIntent, 0);
    ((AlarmManager)paramContext.getSystemService("alarm")).set(1, System.currentTimeMillis() + paramLong, paramIntent);
  }
  
  private static void startService(Context paramContext, Intent paramIntent)
  {
    try
    {
      paramContext.startService(paramIntent);
      return;
    }
    catch (Exception paramContext)
    {
      str = TAG;
      localStringBuilder = new StringBuilder("startService(");
      localStringBuilder.append(paramIntent);
      localStringBuilder.append(") : fail");
      Log.w(str, localStringBuilder.toString(), paramContext);
      return;
    }
    catch (SecurityException paramContext)
    {
      String str = TAG;
      StringBuilder localStringBuilder = new StringBuilder("startService(");
      localStringBuilder.append(paramIntent);
      localStringBuilder.append(") : fail");
      Log.w(str, localStringBuilder.toString(), paramContext);
    }
  }
}
